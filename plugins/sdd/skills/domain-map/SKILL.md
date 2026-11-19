---
name: domain-map
description: Run when a project adopts the domain skills, when shaping a bet that may cross contexts, or when splitting a service. Imports an existing UBIQUITOUS_LANGUAGE.md or glossary, proposes bounded contexts and their relationships from the code with the evidence for each, and writes the first project/domain/ after the user confirms.
disable-model-invocation: true
metadata:
  sdd-kind: user-invoked
---

# Domain Map

Give a project its first domain model, or redraw the map for a large change. Explore first, then present what you found, then write only what the user confirms. Run it again at any time: it never overwrites a term, a row or a `CHANGELOG.md` line that already exists.

## Formats

Read `references/formats.md` before you write anything. Read `references/context-map-patterns.md` before you propose a relationship, and `references/migration.md` when an older glossary exists.

## Scope

Ask nothing before you explore. Take the scope from the user's request:

- **Whole project**, the default.
- **A bet or pitch**: a pitch file or a description the user names. Map only the contexts the bet touches and their direct neighbours.
- **A split**: one service or module the user wants to break up. Map only inside it.

## 1. Explore

Look, and change nothing yet.

**Existing model.** Everything under `project/domain/`. In a re-run, the existing contexts and rows are the starting point, not candidates.

**Older glossaries.** `**/UBIQUITOUS_LANGUAGE.md`, a `GLOSSARY.md` or `CONTEXT.md` whose entries describe the business rather than the repository's own tooling, `GLOSSARY-MAP.md`, and Contextive files `**/*.glossary.yml`.

**Structure.** Build modules (`settings.gradle(.kts)`, `<modules>` in `pom.xml`, workspace files), top-level packages under the root package, services in the deployment manifests, and `@BoundedContext` or Spring Modulith modules if present.

**Messages.** Event and command class names, topic and queue names, `@EventListener` and `@KafkaListener` targets, HTTP clients that call another service of this project, and tables written by one module and read by another.

**Language.** Names that mean different things in different modules (`Account` in billing and in identity), and the same concept under different names across modules.

**Specs.** `project/work-items/*/requirements.md` and `design.md` for terms and boundaries the team already agreed.

## 2. Present

Report the findings in four short sections, one message each, and lead each with what you recommend. Wait for the user's answer before the next section. Skip a section with nothing to report, and say so in one line at the top of the next section.

1. **Import.** The older glossary files, their term counts, and how each maps to the new layout (see `references/migration.md`). Recommend importing every file that describes the business.
2. **Contexts.** Each candidate context with its purpose in one line, its classification (core, supporting or generic), its code location, and its evidence:
   - **strong**: its own module or package, and its own language
   - **latent**: the language diverges, but the code is one module
   - **speculative**: a hunch with no evidence in code or specs
   Recommend only strong candidates. If there is one, recommend the single-context layout and say so plainly: most services have one context, and that is fine.
3. **Relationships.** For each pair of contexts that depend on each other in code: upstream, downstream, the pattern you recommend with a one-line reason, and the messages, each with the file that shows it. List dependencies in both directions between the same two contexts as a finding.
4. **Canvases.** Recommend a `canvas.md` for each core context only.

Use lettered options where the answer can be listed, recommended first. The user may accept a section with "yes", or change any item.

## 3. Confirm

Show the exact list of files you will create or change, and for each, the terms, contexts and rows it will hold. Wait for the user's approval. Anything other than an explicit approval is a change request.

## 4. Write

1. Write the files in the confirmed layout, creating only files with content.
2. Set the status of each item as follows:
   - imported terms: `agreed`, `Since: import`
   - contexts, relationships and terms the user confirmed one by one in step 2: `agreed`, `Since: domain-map {YYYY-MM-DD}`
   - everything accepted as part of a whole section: `proposed`, `Since: domain-map {YYYY-MM-DD}`
3. Turn every flagged ambiguity from an imported file into an open question in the owning context's `canvas.md`, or in the `CHANGELOG.md` entry when there is no canvas.
4. Generate the `context-map.md` diagram from its tables.
5. Append a `CHANGELOG.md` entry headed `## {YYYY-MM-DD}, domain-map`, one line per context, relationship and import, with the evidence for each context.
6. Leave every older glossary file in place and unchanged.

## 5. Done

Tell the user:

- What you wrote, and what stays `proposed`.
- Which older glossary files can be deleted once the other `sdd:` skills read `project/domain/` (see `references/migration.md`).
- That `sdd:domain-define` now runs in the pipeline when a work item changes a term, and `sdd:domain-maintenance` runs at the end of each work item.
- That a repo-wide drift check is available by asking `sdd:domain-maintenance` for one, and that it is a good baseline to run now.

## What Not To Model

- No aggregates, entities, value objects or repositories. Those belong in each work item's `design.md`.
- No context without evidence in code or specs. A speculative context goes in the reply as a question, not in a file.
- No context per microservice by reflex. A bounded context is a language boundary; one context can span several services, and one service can hold more than one context.
- No EventStorming transcript. Record the outcome (contexts, messages, terms), not the session.

## Prohibitions

- Write nothing before the user approves the list in step 3.
- Write no file outside `project/domain/`.
- Overwrite, delete or redefine no existing term, row or `CHANGELOG.md` line. Propose changes to them in the reply instead.
- Edit no code, spec or older glossary file.
- Invoke no other skill, start nothing, and make no network calls.
