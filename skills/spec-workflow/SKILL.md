---
name: spec-workflow
description: Use when creating a feature, creating a spec, implementing a spec, starting a new work item, or any task involving the spec-driven development pipeline. Provides pipeline ordering and superpowers override rules.
---

# SDD Workflow Reference

Pipeline map for Spec-Driven Development. This skill is a reference — it documents ordering and override rules but does not orchestrate.

## Feature Pipeline

| Step | Skill | Output | Next |
|------|-------|--------|------|
| 1 | `brainstorming` (superpowers) | `docs/superpowers/specs/{topic}-design.md` | `sdd:spec-generator` |
| 2 | `sdd:spec-generator` Phase 1 | `requirements.md` | Phase 2 |
| 3 | `sdd:spec-generator` Phase 2 | `design.md` | `sdd:spec-design-review` |
| 4 | `sdd:spec-design-review` (gate) | `design-review-summary.md` | Phase 3 on pass |
| 5 | `sdd:spec-generator` Phase 3 | `tasks.md` | `sdd:spec-task-review` |
| 6 | `sdd:spec-task-review` (gate) | `task-review-summary.md` | `sdd:spec-qa-review` plan mode on pass |
| 7 | `sdd:spec-qa-review` plan mode | `test-plan.md` | `subagent-driven-development` |
| 8 | `subagent-driven-development` (superpowers) | Implementation | `sdd:spec-implementation-audit` |
| 9 | `sdd:spec-implementation-audit` (gate) | `implementation-audit.md` | `sdd:spec-qa-review` review mode on pass |
| 10 | `sdd:spec-qa-review` review mode | Updated `test-plan.md` | `sdd:spec-maintenance` on pass |
| 11 | `sdd:spec-maintenance` | Updated previous specs, `spec-maintenance-report.md` | `finishing-a-development-branch` |
| 12 | `finishing-a-development-branch` (superpowers) | PR | Done |

## Bugfix Pipeline

| Step | Skill | Output | Next |
|------|-------|--------|------|
| 1 | `sdd:spec-generator` bugfix | `bugfix.md`, `design.md`, `tasks.md` | `subagent-driven-development` |
| 2 | `subagent-driven-development` (superpowers) | Implementation | `finishing-a-development-branch` |
| 3 | `finishing-a-development-branch` (superpowers) | PR | Done |

No quality gate skills for bugfixes. User still reviews each phase document interactively.

## Superpowers Override Rules

These rules take precedence over default superpowers skill chaining:

- After `brainstorming`: invoke `sdd:spec-generator` (NOT `writing-plans`)
- After `subagent-driven-development`: invoke `sdd:spec-implementation-audit` (NOT `finishing-a-development-branch`)
- After `sdd:spec-qa-review` review mode passes: invoke `sdd:spec-maintenance`
- After `sdd:spec-maintenance`: invoke `finishing-a-development-branch`

**Exception:** Bugfix pipeline follows default chaining — `subagent-driven-development` → `finishing-a-development-branch` directly.

## Gate Failure Behaviour

All gates follow the same pattern:

1. Gate identifies specific gaps or failures
2. Dispatch fix subagents for the specific issues
3. Re-run the failed gate
4. If the fix loop fails twice on the same gate, stop and ask the user

## Brainstorming → Spec-Generator Handoff

When `sdd:spec-generator` starts after brainstorming:

1. Find the most recent brainstorming output in `docs/superpowers/specs/`
2. Determine the next sequential work-item number in `project/work-items/`
3. Create `project/work-items/{NN}-{name}/`
4. Copy the brainstorming output to `brainstorm.md` in that directory
5. Read `brainstorm.md` as context for generating `requirements.md`

After `sdd:spec-task-review` passes, `brainstorm.md` is deleted.

## Work-Item Identifier Rules

Every change that ships behaviour (feature, bugfix, refactor, infra evolution) **must** live under a numbered SDD spec directory. The spec number is the primary identifier; the Paperclip ticket (AIW-*) is a secondary cross-reference only.

### Required: numbered spec directory

- Directory: `project/work-items/{NN}-{name}/` where `NN` is the next sequential integer.
- Commit scopes: `feat(spec-41)`, `fix(spec-38)`, etc. — the spec number, not the ticket id.
- PR title/description: reference the spec as `spec {NN}`. Include the Paperclip ticket as `(AIW-NNN)` parenthetically if one exists.
- Paperclip issue body: include `spec: {NN}` so the crosswalk stays up to date.

### Acceptable: ticket-scoped `AIW-*` directory (no spec number)

An `AIW-*` directory is acceptable only when the work is:

- A **throwaway spike** that informs a future decision but ships no behaviour change (e.g. accuracy eval runs, market research briefs, data-acquisition discoveries).
- A **board report** or governance document (e.g. full app audit, crosswalk maintenance).
- A **single-commit trivial bugfix** on the eval harness or golden-set data that does not warrant a spec lifecycle.

If the spike result ships into production code, promote it to a numbered spec before merging.

### When in doubt

If you are unsure whether the work warrants a spec: if it changes any user-visible behaviour or any production infrastructure, it gets a spec. Spikes stay `AIW-*` only while they remain exploratory.

## Artifacts

### Feature Work Item

| File | Created by | Lifecycle |
|------|-----------|-----------|
| `brainstorm.md` | `sdd:spec-generator` (copied from brainstorming output) | Deleted after spec-task-review |
| `requirements.md` | `sdd:spec-generator` | Permanent |
| `design.md` | `sdd:spec-generator` | Permanent |
| `design-review-summary.md` | `sdd:spec-design-review` | Permanent |
| `tasks.md` | `sdd:spec-generator` | Permanent |
| `task-review-summary.md` | `sdd:spec-task-review` | Permanent |
| `test-plan.md` | `sdd:spec-qa-review` | Permanent (updated in review mode) |
| `implementation-audit.md` | `sdd:spec-implementation-audit` | Permanent |
| `spec-maintenance-report.md` | `sdd:spec-maintenance` | Permanent |

### Bugfix Work Item

| File | Created by | Lifecycle |
|------|-----------|-----------|
| `bugfix.md` | `sdd:spec-generator` | Permanent |
| `design.md` | `sdd:spec-generator` | Permanent |
| `tasks.md` | `sdd:spec-generator` | Permanent |
