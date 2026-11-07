# Glossary

Each term is defined once, from the skills. Where a skill gives a term a narrower meaning, the entry names the skill. [AGENTS.md](AGENTS.md) links here.

## Pipeline terms

### Work item

One unit of change, kept as a numbered directory `project/work-items/{NN}-{name}/`. It holds every document the pipeline writes for that change. Every change that ships behaviour lives in a work item, and the spec number `NN` is its primary identifier; an issue-tracker ticket is a secondary cross-reference. Source: `spec-workflow`, Work-Item Identifier Rules.

### Artefact

The skills use the word in two senses:

1. **A pipeline document** that a skill writes into a work item, such as `discovery.md`, `design.md` or `implementation-audit.md`. `spec-workflow` lists them under Artifacts, with the skill that creates each one and how long it lives.
2. **A code artefact** that a task creates and a later task uses, such as a generated type, an endpoint, a schema or a table. `spec-task-review` uses this sense when it checks task order.

### Gate

The skills use the word in two senses:

1. **A quality gate**: a skill that reviews a document or the implementation and sets a blocking status. The quality gates are `spec-design-review`, `spec-task-review` and `spec-implementation-audit`, which `spec-workflow` marks "(gate)", and `spec-qa-review` in review mode, which sets PASSED or FAILED.
2. **A review gate**: the point where a skill stops and waits for the user to approve a document before the next phase. `spec-discovery` presents one before `spec-generator` starts, and `spec-generator` has one between its phases.

### Severity

The weight of one finding. Every quality gate uses three labels. What each label covers differs by gate, so read the gate's own `SKILL.md` for the full list.

- **CRITICAL**: a finding that stops the pipeline. In `spec-design-review` it "blocks task creation and implementation", for example a requirement with no design support.
- **MAJOR**: a finding that should be fixed for quality and consistency, for example an identifier that differs from the glossary term. In `spec-implementation-audit` every `NOT RUN` evidence block is MAJOR, and it fails the audit.
- **MINOR**: a finding that can be noted and fixed later, for example glossary drift confined to prose.

### Status

The outcome of one skill run, written as a single `**Status:**` line in the document the skill writes. Later steps read this line, so its values are fixed. Each quality gate maps the highest severity it found to a status in its own way:

| Gate | Any CRITICAL | MAJOR, no CRITICAL | MINOR only | No findings |
|---|---|---|---|---|
| `spec-design-review` | BLOCKED | APPROVED WITH ISSUES | APPROVED WITH ISSUES | APPROVED |
| `spec-task-review` | BLOCKED | APPROVED WITH ISSUES | APPROVED | APPROVED |
| `spec-implementation-audit` | FAILED | FAILED | PASSED | PASSED |
| `spec-qa-review`, review mode | FAILED | FAILED | PASSED | PASSED |

`spec-qa-review` counts open gaps in its gap log. `spec-maintenance` always writes **PASSED**, because stale specs are informational.

The status values are:

- **APPROVED**: the document can go to the next step.
- **APPROVED WITH ISSUES**: the document can go to the next step, and the findings are reported. `spec-task-review` warns the user with the MAJOR findings.
- **BLOCKED**: the pipeline stops until the document is fixed and the gate runs again.
- **PASSED**: the implementation or the tests meet the gate.
- **FAILED**: the gate found an open CRITICAL or MAJOR finding. The caller fixes the specific gaps and runs the gate again.

Two other lines carry a status-like value and are not gate statuses. `discovery.md` has `Status: Draft` until the user approves it, then `Status: Approved {YYYY-MM-DD}`. `test-plan.md` has a separate `**Stage:**` line, with the value Draft, Active or Complete.

### Verdict

The section of a gate's report that holds the status and what happens next. `spec-task-review` and `spec-implementation-audit` have a Verdict heading. `spec-design-review` puts its status under Overall Status and its actions under Next Steps.

### Two-strike rule

When a gate fails twice on the same work item, stop and ask the user instead of trying a third fix. `spec-workflow` states it for every gate: "If the fix loop fails twice on the same gate, stop and ask the user." Each gate states the second strike in its own terms:

- `spec-design-review`: an earlier `design-review-summary.md` for this work item was BLOCKED on the same issues.
- `spec-task-review`: the previous `task-review-summary.md` is already BLOCKED.
- `spec-implementation-audit`: the `implementation-audit.md` already in the work item had `**Status:** FAILED` before this audit.
- `spec-qa-review`, review mode: the `**Status:**` line in `test-plan.md` read FAILED before this run.

### Override rule

A rule in `spec-workflow` that replaces the default chaining of the superpowers skills inside the feature pipeline. For example, after `subagent-driven-development` the pipeline invokes `sdd:spec-implementation-audit`, not `finishing-a-development-branch`. The bugfix pipeline follows the default chaining.

### Plan mode

The `spec-qa-review` mode that runs when the spec exists and no implementation does. It writes `test-plan.md` and fills the Expected column for each of the five test layers. The Expected column is then locked.

### Review mode

The `spec-qa-review` mode that runs when the implementation is present. It fills the Actual column, compares it with Expected, updates the gap log and sets the status. It updates `test-plan.md` and never replaces it.

## Eval terms

These terms come from [skill-eval](https://github.com/bareword/skill-eval), the harness that runs the eval packs. No `SKILL.md` uses them, because they describe how the skills are measured, not what the skills do.

### Eval pack

The test cases for one skill, kept in `evals/<name>/`: `evals.json` holds each case's prompt, its expected outcome and the checks to grade, `files/` holds the input files a case stages, and `results/` holds the committed results of each iteration.

### Arm

One configuration in which every case runs. The skill arm has the skill installed; the baseline arm does not. The harness keeps the arm label out of every prompt, sandbox path and judge call, so the judge grades an answer without knowing which arm produced it.

### Baseline

The arm a skill is compared with. With `--baseline none` it is the same task with no skill (`without_skill`). With `--baseline snapshot` it is an earlier copy of the same skill (`old_skill`), taken with `skill-eval snapshot` before the change. A no-skill baseline measures the skill against having nothing; a snapshot baseline measures whether a change helped.

### Iteration

One full run of an eval pack: both arms for every case, the grading and the judging. Iterations are numbered in order, and each one commits `results/iteration-N.md` and `results/iteration-N.json`. The Evals page on the site shows the latest iteration for each skill.
