---
name: domain-maintenance
description: After sdd:spec-maintenance, reconcile project/domain/ with the finished work item. Promotes the item's proposed terms and relationships to agreed, deprecates renamed terms, checks glossary contradictions, avoided words in new code and specs, and cross-context dependencies missing from the context map, then writes domain-maintenance-report.md. Skips when project/domain/ does not exist. Also runs a repo-wide drift check when the user asks for one.
metadata:
  sdd-kind: model-invoked
---

# Domain Maintenance

Keep the domain model true after each work item. The glossary and the context map are only useful while the specs and the code still use them.

**Workflow Position:** Runs AFTER `sdd:spec-maintenance` and BEFORE `sdd:spec-finish`. Repo mode runs on its own, when the user asks for a domain drift check across the whole repository.

**Trigger:** No pipeline skill names this one. In Claude Code the sdd hook starts it when `spec-maintenance-report.md` is written and `project/domain/` exists, and stops the session from ending while it has not run. In other agents, `sdd:spec-workflow` lists the same condition. This skill reads and writes only the files it names here and in `references/formats.md`.

## Formats

Read `references/formats.md` before you change any file under `project/domain/`. When the project has a `pom.xml`, `build.gradle` or `build.gradle.kts`, also read `references/jvm.md`.

## Skip Rule

If `project/domain/` does not exist, skip: write no report, edit no file, say that the project has no domain model, and invoke `sdd:spec-finish`. A skipped run counts as a pass.

## Work-Item Mode

### 1. Scope

- Read the current work item's `requirements.md`, `design.md`, `tasks.md` and `implementation-audit.md`.
- Read every glossary, `context-map.md`, and the `CHANGELOG.md` entries that name this work item.
- List the files the work item changed, and the lines it added in them. Take the first source that exists:
  1. A diff the caller or the user names.
  2. Commits whose subject contains `(spec-{NN})`: `git diff -U0` over them.
  3. `git diff -U0 $(git merge-base HEAD main)...HEAD`, with the default branch name in place of `main`.
  4. No git history: the files that `tasks.md` and `implementation-audit.md` name, with every line counted as added. Say in the report that no diff was available.

### 2. Scan

Run the term scanner over the work item and the changed files:

```bash
python3 <this skill's directory>/scripts/term_scan.py --domain project/domain \
  project/work-items/{NN}-{name} <changed files>
```

It prints JSON with `glossary_problems`, `alias_hits` (each with a `kind` of spec, contract, code or code-comment) and `term_hits`. If Python 3 is not available, make the same checks by reading and searching, and say so in the report.

### 3. Check

Record one finding per problem, with its file and line. Severities:

**CRITICAL**, blocks the gate:

- **D1 Glossary contradiction.** Any entry in `glossary_problems`: a term defined twice in one glossary, a word that is both a live term and an avoided word, or a word avoided under two terms. An `alias-is-term` entry for a rename that this work item's `CHANGELOG.md` entry records is not a finding: it is the rename step 4 completes.
- **D2 Unmapped dependency.** The design or the added code makes one context depend on another, through an import, a call, an event, a command or a shared table, and `context-map.md` has no row that allows it. Use the `Code location` column to tell which context a file belongs to. Skip D2 when there is no `context-map.md`.

**MAJOR**, reported, does not block:

- **D3 Avoided word in a name.** An `alias_hits` entry of kind code or contract on a line the work item added, or of kind spec in this work item's files.
- **D4 Undefined term.** A business noun that `requirements.md` or `design.md` uses as a name, which no glossary defines and this work item's `CHANGELOG.md` entry does not propose. Technical words (cache, queue, retry, DTO, controller) are not findings.

**MINOR**, reported, does not block:

- **D5 Avoided word in prose.** An `alias_hits` entry of kind code-comment, or any hit on a line the work item did not add.
- **D6 Unused proposal.** A term or relationship this work item proposed that its specs and code never use.

D7, rot candidates, is a repo-mode check only: a work item's files are too few to show that a term is unused.

### 4. Update the Domain Model

Apply these edits only when there is no CRITICAL finding, and make the smallest edit each time:

- Set `**Status:** agreed` on every term and relationship with `Since: work-item {NN}` and `proposed`, unless it is a D6 finding. A D6 item stays proposed.
- For each rename this work item's `CHANGELOG.md` entry records, deprecate the old entry: strike through its definition, add `Use {new term}.`, and set `**Status:** deprecated *(deprecated, see work-item {NN})*`. Keep the entry.
- Add a `Code:` line to a term only when the work item created the class, package or module that carries its name.
- Regenerate the `context-map.md` diagram if any row changed.
- Append a `CHANGELOG.md` entry headed `## {YYYY-MM-DD}, work-item {NN}, maintenance`, one line per change.

### 5. Report

Write `project/work-items/{NN}-{name}/domain-maintenance-report.md`:

```markdown
# Domain Maintenance Report: {Work Item Name}

**Date:** YYYY-MM-DD
**Triggered by:** work-item {NN}-{name}
**Status:** PASSED

## Domain Changes

| Change | Item | Context |
|---|---|---|
| Agreed | Policyholder | Underwriting |
| Deprecated | Client, see Policyholder | Underwriting |
| Agreed relationship | Payments Provider → Billing, Anticorruption Layer | Billing |

## Findings

| ID | Severity | Check | Where | Detail |
|---|---|---|---|---|
| 1 | MAJOR | D3 | `src/main/kotlin/.../ClientRepository.kt:3` | Uses Client; the Underwriting glossary says Policyholder |

## Verdict

{One sentence: what passed, what is left, and the next step.}
```

With no findings, write `No findings.` under `## Findings`. With no changes, write `No domain changes.` under `## Domain Changes`.

### 6. Status and Next Step

- Any CRITICAL finding: `**Status:** FAILED`. Change nothing under `project/domain/`. Follow the gate failure behaviour in `sdd:spec-workflow`. D1 and D2 need a decision: ask the user one question with lettered options, recommended first, such as "(a) add the row Ordering → Billing, Customer-Supplier, `InvoiceRequested` (command); (b) remove the dependency from the design". Apply the answer, then run this skill again. If this is the second FAILED report for the work item, stop and ask the user instead.
- Otherwise: `**Status:** PASSED`. Repeat the MAJOR findings in your closing reply, then invoke `sdd:spec-finish`.

## Repo Mode

Run when the user asks for a domain drift check, a glossary audit or a check of the whole repository.

1. Scan `project/` and every source root (`src/`, module directories, `docs/`, API contract files) with `term_scan.py`.
2. Run D1, D2 (over all code, not only added lines), D3 and D5 (reporting every hit, grouped by file), and D7:
   - **D7 Rot candidate**, MINOR. An agreed term with `unused: true` across the whole repository. Propose deprecation in the report; never deprecate it yourself.
3. Write `project/domain/drift-report.md` in the report shape above, with `**Triggered by:** repo check` and `## Domain Changes` left out. Replace the previous drift report.
4. Change no glossary, context map or code. Summarise the counts per check in the reply and recommend the three findings worth fixing first.

## What Not To Do

- Never delete a term, a relationship or a `CHANGELOG.md` line.
- Never change a definition. Only the user decides what a word means.
- Never rename identifiers in code or specs; report them.
- Never create a context, a canvas or a glossary file.
- Never set a term to agreed that this work item did not propose.

## Next Step

On PASSED, invoke `sdd:spec-finish`. On a skip, invoke `sdd:spec-finish`. In repo mode, stop after the report.
