# Migrating an older glossary

How to bring an existing glossary into `project/domain/`. The older file stays in place; it is the user's to delete.

## UBIQUITOUS_LANGUAGE.md

The file written by Matt Pocock's earlier `ubiquitous-language` skill. Recognise it by tables with the columns Term, Definition and Aliases to avoid, often grouped under headings, followed by Relationships, an Example dialogue and Flagged ambiguities sections.

| Source | Destination |
|---|---|
| Each table row | One glossary entry: Term → heading, Definition → definition, Aliases to avoid → `Avoid:` |
| Group headings | Ignored, unless the user confirms that a group is a separate context |
| Relationships | `Related` facts go into the definitions they explain, one clause each; drop the rest |
| Example dialogue | Not imported |
| Flagged ambiguities | Open questions, see `SKILL.md` step 4, item 3 |

Every imported term gets `**Status:** agreed` and `**Since:** import`.

## GLOSSARY.md and GLOSSARY-MAP.md (or CONTEXT.md and CONTEXT-MAP.md)

The files written by Matt Pocock's `domain-modeling` skill. Check first that the file describes the business; a repository's own `GLOSSARY.md` about its tooling is not a domain glossary.

| Source | Destination |
|---|---|
| `GLOSSARY.md` terms | Glossary entries; `_Avoid_` lists become `Avoid:` |
| `GLOSSARY-MAP.md` contexts | Rows in the `Contexts` table, and one `<context>/glossary.md` each |
| `GLOSSARY-MAP.md` relationships | Rows in the `Relationships` table, with the pattern the user confirms |
| ADRs | Not imported. Link them from `project/domain/README.md` |

## Contextive (`*.glossary.yml`)

| Source | Destination |
|---|---|
| `contexts[].name` | A context, with `Code location` taken from the folder the file sits in |
| `terms[].name`, `definition` | Glossary entries |
| `terms[].aliases` | `Avoid:` |

Contextive shows terms on hover in the IDE. If the team uses it, mention that an export from `project/domain/` to Contextive format is a candidate feature, rather than keeping both by hand.

## Conflicts during import

- The same term in two files with different definitions: ask the user which one wins, or whether the two belong to different contexts.
- A word that one file lists as a term and another lists as an alias: ask which is canonical.
- Never merge two definitions into one without the user's confirmation.

## After the import

Until the other `sdd:` skills read `project/domain/**`, they still look for `**/UBIQUITOUS_LANGUAGE.md`. Tell the user to keep the older file until the release that switches those skills over, then delete it.
