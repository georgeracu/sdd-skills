# Feature Pipeline

The full SDD pipeline for new features. Twelve steps, three quality gates, three artefact-producing skills.

The canonical ordering lives in `.claude/skills/sdd/spec-workflow/SKILL.md`. This document explains what each step does and what the user reviews.

## Pipeline overview

```
brainstorming
    │
    ▼
spec-generator (Phase 1) ──► requirements.md
    │
    ▼
spec-generator (Phase 2) ──► design.md
    │
    ▼
spec-design-review (GATE) ──► design-review-summary.md
    │
    ▼
spec-generator (Phase 3) ──► tasks.md
    │
    ▼
spec-task-review (GATE) ──► task-review-summary.md
    │
    ▼
spec-qa-review (plan mode) ──► test-plan.md
    │
    ▼
subagent-driven-development ──► implementation
    │
    ▼
spec-implementation-audit (GATE) ──► implementation-audit.md
    │
    ▼
spec-qa-review (review mode) ──► updated test-plan.md
    │
    ▼
spec-maintenance ──► spec-maintenance-report.md (+ annotations on older specs)
    │
    ▼
finishing-a-development-branch ──► PR
```

## Steps

### 1. Brainstorming (superpowers)

Explore the problem space before writing a spec. Produces a design doc under `docs/superpowers/specs/{topic}-design.md`.

Output is a conversation artefact — captured direction and constraints, not a formal spec.

### 2. spec-generator — Phase 1: Requirements

Creates `project/work-items/{NN}-{name}/` with the next sequential number. Copies the brainstorming output to `brainstorm.md` for context.

Generates `requirements.md`: user stories, acceptance criteria in EARS notation (`WHEN ... THE [Glossary_Term] SHALL ...`), glossary.

User reviews and iterates before approval.

### 3. spec-generator — Phase 2: Design

Generates `design.md`: architecture, components, interfaces, data models, sequence diagrams (Mermaid), error handling, testing strategy.

User reviews and iterates before approval.

### 4. spec-design-review (GATE)

Validates `design.md` against `requirements.md`. Checks that every requirement is addressed, design decisions are justified, and non-functional requirements are covered.

Produces `design-review-summary.md`. See [Quality-gates.md](Quality-gates.md) for gate states and failure behaviour.

### 5. spec-generator — Phase 3: Tasks

Generates `tasks.md`: numbered tasks with checkboxes, sub-tasks, requirement tracing (`_Requirements: N.N_`), checkpoint milestones, optional tasks marked with `*`.

### 6. spec-task-review (GATE)

Validates `tasks.md` for:

- **Requirements coverage** — every acceptance criterion has at least one task
- **Design coverage** — every component, interface, endpoint has corresponding tasks
- **Sequencing** — task dependencies are correct (API types before handlers, backend before frontend)
- **Implementability** — no ambiguity, real file paths, realistic scope per task
- **Completeness** — mandatory ending tasks exist (E2E tests, docs update, final validation)

Produces `task-review-summary.md`. On pass, deletes `brainstorm.md`.

### 7. spec-qa-review (plan mode)

Produces `test-plan.md` with a 5-layer coverage matrix (Unit, Integration, E2E, Performance, Security). Fills the **Expected** column — what SHOULD be tested.

Unit coverage gate: ≥85% line/branch.

### 8. subagent-driven-development (superpowers)

Implementation phase. Tasks from `tasks.md` are dispatched to subagents. Checkboxes are updated as work completes.

### 9. spec-implementation-audit (GATE)

Full verification that the implementation matches the spec. Checks:

- Every requirement has corresponding code
- Implementation logic matches spec (API contracts, data models, business rules)
- Edge cases and error states are implemented
- All non-optional tasks are checked off
- Knowledge base / architecture docs are updated

Produces `implementation-audit.md` with per-requirement status, discrepancies, and evidence (test output, build results).

### 10. spec-qa-review (review mode)

Reads actual tests + source code. Fills the **Actual** column of the coverage matrix. Compares against Expected. Updates `test-plan.md` with gap log entries (Critical / Important / Minor).

### 11. spec-maintenance

Checks whether the just-completed implementation invalidates anything in previously implemented specs (specs with `implementation-audit.md`). Annotates affected items in older `tasks.md` / `design.md` / `requirements.md` files with strikethrough + work-item reference.

Produces `spec-maintenance-report.md`.

This skill always passes — stale spec annotations are informational, not blocking.

### 12. finishing-a-development-branch (superpowers)

Creates the PR.

## What the user reviews

Every artefact-producing step pauses for user review:

- `requirements.md` (Phase 1)
- `design.md` (Phase 2)
- `tasks.md` (Phase 3)

The user iterates with the agent until the document is right, then approves and the pipeline advances. Gates also produce reports the user can read, but they're automated checks, not review pauses.

## When to use the feature pipeline

- New capabilities or behaviour
- Multi-task work that spans backend + frontend
- Work that needs documentation for collaboration or compliance

For bugs, use [Bugfix-pipeline.md](Bugfix-pipeline.md) instead.
