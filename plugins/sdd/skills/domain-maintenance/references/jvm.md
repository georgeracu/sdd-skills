# JVM projects

Read this when the project has a `pom.xml`, `build.gradle` or `build.gradle.kts`. It turns check D2, unmapped dependency, from reading imports into a test the build runs.

## Order of preference

1. **The project uses Spring Modulith.** Each top-level application module is a candidate context. Compare `ApplicationModules.of(Application::class.java)` with the `Contexts` table: a module with no row, or a row whose `Code location` matches no module, is a finding to report. Keep Modulith's own `verify()` test; it already fails on cycles and on access to internals.
2. **The project uses jMolecules.** A package annotated `@BoundedContext` should have a row in the `Contexts` table with the same name. Report any mismatch as D2.
3. **Neither.** Offer the ArchUnit test below. Add it only when the user agrees, in a task of the current work item or in a new one; this skill never edits code itself.

When the test exists, check D2 by running the project's test command from `AGENTS.md` and reading the `ContextMapTest` result, instead of reading imports.

## The context map test

Fails when code in one context depends on another context with no `context-map.md` row that allows it. A row lets the downstream context depend on the upstream one; `Shared Kernel` and `Partnership` allow both directions. Contexts with no `Code location` (external systems) are ignored. Code locations must not nest: `com.acme` and `com.acme.billing` cannot both be contexts.

Dependency, Gradle Kotlin DSL (the build below ran with ArchUnit 1.5.1, the current 1.x release; use the current 1.x release):

```kotlin
dependencies {
    testImplementation("com.tngtech.archunit:archunit-junit5:1.5.1")
}

tasks.test {
    val contextMap = rootProject.file("project/domain/context-map.md")
    inputs.file(contextMap)
    systemProperty("contextMap", contextMap.path)
}
```

`src/test/kotlin/architecture/ContextMapTest.kt`:

```kotlin
package architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.nio.file.Files
import java.nio.file.Path

/**
 * Checks the code against project/domain/context-map.md.
 *
 * One dynamic test per bounded context: its classes may depend only on its own
 * package and on the contexts the map lists as its upstreams.
 */
class ContextMapTest {

    private val contextMap = ContextMap.parse(
        Path.of(System.getProperty("contextMap", "project/domain/context-map.md"))
    )

    private val classes: JavaClasses = ClassFileImporter()
        .withImportOption(ImportOption.DoNotIncludeTests())
        .importPackages(*contextMap.codeLocations().toTypedArray())

    @TestFactory
    fun `each context depends only on what the context map allows`(): List<DynamicTest> =
        contextMap.contexts
            .filter { it.codeLocation != null }
            .map { context ->
                DynamicTest.dynamicTest(context.name) {
                    val forbidden = contextMap.forbiddenPackagesFor(context)
                    if (forbidden.isNotEmpty()) {
                        noClasses()
                            .that().resideInAPackage("${context.codeLocation}..")
                            .should().dependOnClassesThat()
                            .resideInAnyPackage(*forbidden.map { "$it.." }.toTypedArray())
                            .because(
                                "project/domain/context-map.md has no relationship that lets " +
                                    "${context.name} depend on these contexts"
                            )
                            .check(classes)
                    }
                }
            }
}

/** A bounded context row from the Contexts table. */
data class BoundedContext(val name: String, val codeLocation: String?)

/** A row from the Relationships table. The downstream context depends on the upstream one. */
data class Relationship(val upstream: String, val downstream: String, val pattern: String)

/** The two tables of context-map.md, in the format of sdd:domain-define's formats.md. */
class ContextMap(val contexts: List<BoundedContext>, val relationships: List<Relationship>) {

    fun codeLocations(): List<String> = contexts.mapNotNull { it.codeLocation }

    /** Packages of every other context that [context] has no relationship allowing it to use. */
    fun forbiddenPackagesFor(context: BoundedContext): List<String> {
        val allowed = relationships.flatMap { relationship ->
            when {
                relationship.downstream == context.name -> listOf(relationship.upstream)
                relationship.upstream == context.name && relationship.pattern in SYMMETRIC ->
                    listOf(relationship.downstream)
                else -> emptyList()
            }
        }.toSet()
        return contexts
            .filter { it.name != context.name && it.name !in allowed }
            .mapNotNull { it.codeLocation }
    }

    companion object {
        private val SYMMETRIC = setOf("Shared Kernel", "Partnership")
        private val SEPARATOR_CELL = Regex(":?-{3,}:?")

        fun parse(path: Path): ContextMap {
            require(Files.exists(path)) { "No context map at $path" }
            val tables = readTables(Files.readAllLines(path))
            val contexts = tables["Contexts"].orEmpty().map { row ->
                BoundedContext(
                    name = row.getValue("Context"),
                    codeLocation = row["Code location"]?.trim('`', ' ')?.ifBlank { null },
                )
            }
            val relationships = tables["Relationships"].orEmpty().map { row ->
                Relationship(
                    upstream = row.getValue("Upstream"),
                    downstream = row.getValue("Downstream"),
                    pattern = row.getValue("Pattern"),
                )
            }
            return ContextMap(contexts, relationships)
        }

        /** Reads each pipe table under a "## Heading" into rows keyed by column name. */
        private fun readTables(lines: List<String>): Map<String, List<Map<String, String>>> {
            val tables = mutableMapOf<String, MutableList<Map<String, String>>>()
            var heading: String? = null
            var header: List<String>? = null
            for (raw in lines) {
                val line = raw.trim()
                if (line.startsWith("## ")) {
                    heading = line.removePrefix("## ").trim()
                    header = null
                    continue
                }
                if (!line.startsWith("|")) {
                    header = null
                    continue
                }
                val currentHeading = heading ?: continue
                val row = cells(line)
                val currentHeader = header
                if (currentHeader == null) {
                    header = row
                } else if (row.all { SEPARATOR_CELL.matches(it) }) {
                    continue
                } else {
                    tables.getOrPut(currentHeading) { mutableListOf() }
                        .add(currentHeader.zip(row).toMap())
                }
            }
            return tables
        }

        private fun cells(row: String): List<String> =
            row.removePrefix("|").removeSuffix("|").split("|").map { it.trim() }
    }
}
```

The template compiled and ran in a scratch project with Kotlin 2.4.21, Gradle 9.9.1, JDK 21, JUnit 5.14.4 and ArchUnit 1.5.1: it passed with a Metering to Billing row and failed for Billing with the `because` message when the row was removed. The `inputs.file` line matters, because without it Gradle reports the test as up to date after the map changes and skips it. No eval case runs the template.

## Mapping terms to code

When a work item adds a class for a glossary term, these are the shapes to recommend in `design.md`. They are options, not rules; `sdd:spec-design-review` decides.

| Glossary concept | Kotlin shape | jMolecules annotation |
|---|---|---|
| A value with no identity, such as `Policy Number` | `@JvmInline value class PolicyNumber(val value: String)` with validation in `init` | `@ValueObject` |
| A thing with identity and a lifecycle, such as `Policy` | A class whose `equals` uses only the id | `@Entity` or `@AggregateRoot` |
| Something that happened, such as `Policy Issued` | A `data class PolicyIssued(...)`, named in the past tense | `@DomainEvent` |

Class names use the glossary term with spaces removed: `Usage Digest` becomes `UsageDigest`. A class named after an avoided word is a D3 finding.
