---
name: domain-define
description: Use when a work item introduces, renames or redefines a business term, a bounded context or a message between contexts, or when the user asks to define domain language, a glossary or a context map. Agrees each term with the user one question at a time and writes it to project/domain/ as proposed. Runs after sdd:spec-discovery approval and before sdd:spec-generator. Not for code-level aggregate design, which belongs in design.md.
metadata:
  sdd-kind: model-invoked
---

# Domain Define

Agree the words a work item adds or changes, and record them in `project/domain/` before any spec uses them. You change the domain model; you do not explain DDD.

**Workflow Position:** Runs AFTER `sdd:spec-discovery` approval and BEFORE `sdd:spec-generator`, when `discovery.md` holds a term that needs a decision. Also runs on its own when the user asks to define or change a term, a context or a relationship.

**Trigger:** No pipeline skill names this one. In Claude Code the sdd hook starts it when an approved `discovery.md` marks a term as new and `project/domain/` exists. In other agents, `sdd:spec-workflow` lists the same condition. This skill reads and writes only the files it names here and in `references/formats.md`.

You use only reading, searching, writing files under `project/domain/`, one edit to `discovery.md`, and asking the user.

## Formats

Read `references/formats.md` before you write anything. Every file you write follows it exactly.

## Inputs

Read these without asking anything. Note each missing input in your summary; a missing input is never a reason to stop.

1. `project/work-items/{NN}-{name}/discovery.md`, when you run in the pipeline: its `Terms` section, Problem and Scope.
2. The user's request, when you run on your own.
3. `project/domain/**`: every glossary, `context-map.md`, `README.md` and the last five `CHANGELOG.md` entries.
4. `**/UBIQUITOUS_LANGUAGE.md`, only when `project/domain/` does not exist. Treat its terms as existing. Do not edit it; at the end, suggest `/sdd:domain-map` to import it.

## Skip Rule

In the pipeline, skip when every `Terms` entry in `discovery.md` is marked existing, matches a glossary heading, and keeps the glossary's meaning. Write nothing, say "Domain terms unchanged", and invoke `sdd:spec-generator` with the work-item path.

On its own, skip when the request changes no business term, context or relationship, such as a purely technical change. Write nothing, say "No domain change" and why in one sentence, and stop.

A skipped run counts as a pass.

## Procedure

1. Read the formats and the inputs.
2. **Build the decision list.** Classify each term from `discovery.md` or the request:
   - **new**: no glossary has it.
   - **conflict**: a glossary has the word with a different meaning.
   - **synonym**: a glossary already has the concept under another name.
   - **avoided**: the word is in an `Avoid:` list.
   - **rename**: the user wants to replace an existing term.
   Add one entry for each new context, and for each new message or dependency between contexts that the scope implies.
3. **Find the owning context.** With no `context-map.md`, the project has one context and every term goes in `project/domain/glossary.md`. With a context map, take the context from the scope; ask only when two contexts could own the term.
4. **Ask, one decision per message**, in the order of the list. Give lettered options with the recommended one first, using the question format below. For a term, settle the canonical name, the definition, the words to avoid and the owning context in one question when you can propose all four, and in two when the user rejects the name.
   - For a **synonym** or **avoided** word, recommend the existing term.
   - For a **conflict** in a one-context project, ask whether the two meanings belong to (a) one context with two different words, recommended, or (b) two contexts. Only (b) creates a second context; then confirm the context names and move the existing glossary to `project/domain/<context>/glossary.md`.
   - For a **relationship**, propose upstream, downstream, pattern and messages in one question.
   - Skip a question the user's message already answers in full (name, definition, words to avoid and context for a term), and state the answer you took in the summary. Ask about anything it leaves open.
5. **Write**, creating files only when they have content:
   - Each agreed term as an entry with `**Status:** proposed` and `**Since:** work-item {NN}` (or the date and `on request` when you run on your own).
   - For a rename, the new term as proposed, the old name added to its `Avoid:` list, and nothing else changed in the old entry. `sdd:domain-maintenance` deprecates it when the work item finishes.
   - Each new relationship as a `context-map.md` row with `proposed`, then regenerate the diagram.
   - One `CHANGELOG.md` entry headed `## {YYYY-MM-DD}, work-item {NN}`, one line per decision, giving the reason for every choice between words.
6. **Align discovery.** In the pipeline, replace each non-canonical term in `discovery.md`'s `Terms` section with the canonical one and mark it existing. Change no other section.
7. **Check** the files you wrote and fix them in place:
   - every definition says what the thing is, in one or two sentences, with no table, class, endpoint or framework
   - no word is both a heading and in an `Avoid:` list in the same glossary, except the old name in a rename you just recorded
   - no word is in two `Avoid:` lists in the same glossary
   - every term, context and row you wrote was confirmed by the user
   - no file exists that has nothing in it
8. **Hand off.** Give the summary below. In the pipeline, invoke `sdd:spec-generator` with the work-item path.

## Question Format

One question per message, and nothing else beyond the inferred-answer line:

```
Discovery uses "client" for the person who holds a policy. Which term should the specs use?
(a) Recommended: Policyholder. "The person or organisation that holds a Policy and is entitled to claim under it." Avoid: Client, Insured. Context: Underwriting.
(b) Client, with the same definition. Billing already uses Customer, so Client stays unambiguous.
(c) Something else, tell me.
```

## Summary

End with this shape, filling in the braces:

> Domain updated for work item {NN}: {n} terms proposed, {n} relationships proposed, {n} renames recorded. Files: {paths}. These stay proposed until `sdd:domain-maintenance` runs at the end of the work item. Next: `sdd:spec-generator` for `project/work-items/{NN}-{name}/`.

## What Not To Model

Most work items need no domain change. Do not:

- create a second context, a `context-map.md` or a `canvas.md` unless a word has to mean two things
- add terms for technical concepts (cache, queue, DTO, retry) or for anything the business would not say
- write aggregates, entities, value objects, repositories or invariants; those belong in `design.md`
- define a term the work item does not use

## Prohibitions

- Write no file outside `project/domain/`, except the `Terms` lines of `discovery.md`.
- Put no term, definition, alias or relationship in a file that the user did not confirm.
- Never set `**Status:** agreed` or `deprecated`; only `sdd:domain-maintenance` and `sdd:domain-map` do that.
- Never delete a term or a `CHANGELOG.md` line.
- Ask one question per message.
- Invoke no skill other than `sdd:spec-generator`.
- Commit, push and run nothing.

## Next Step

In the pipeline, invoke `sdd:spec-generator` with the work-item path. On its own, stop after the summary.
