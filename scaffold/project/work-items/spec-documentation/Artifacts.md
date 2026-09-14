# Artefacts

Every work item produces a set of files under `project/work-items/{NN}-{name}/`. This document describes what each file contains, who produces it, and how long it lives.

## Directory layout

```
project/work-items/
├── {NN}-{name}/
│   ├── brainstorm.md              (features only — deleted after task-review)
│   ├── requirements.md            (features) / bugfix.md (bugs)
│   ├── design.md
│   ├── design-review-summary.md   (features only)
│   ├── tasks.md
│   ├── task-review-summary.md     (features only)
│   ├── test-plan.md               (features; bugfixes only if QA review is run)
│   ├── implementation-audit.md    (features only)
│   └── spec-maintenance-report.md (features only)
├── README.md                      (this directory's entry point)
├── {your-backlog-file}.md         (optional: a project may keep its own backlog file here, name not fixed)
├── spec-documentation/            (this documentation)
└── templates/                     (reference templates for every artefact)
```

For the shape of each document, see the matching template under `templates/`.

## File reference

### `brainstorm.md` (features only)

**Created by:** `spec-generator` (copied from brainstorming output at `docs/superpowers/specs/`)
**Lifecycle:** Deleted after `spec-task-review` passes
**Purpose:** Context input for requirements generation. Not a formal spec — a conversation artefact.

### `requirements.md`

**Created by:** `spec-generator` Phase 1
**Lifecycle:** Permanent
**Format:** EARS notation (`WHEN ... THE [Glossary_Term] SHALL ...`)

Sections:
- Introduction (feature description, context, stakeholders)
- User stories
- Acceptance criteria (numbered for tracing — e.g. `AC 2.1`)
- Glossary

Acceptance criteria are referenced throughout the pipeline via `_Requirements: N.N_` tags in `tasks.md` and per-requirement status in `implementation-audit.md`.

### `bugfix.md` (bugs only — replaces requirements.md)

**Created by:** `spec-generator` bugfix mode
**Lifecycle:** Permanent

Sections:
- Current behaviour (defect)
- Expected behaviour (correct)
- Unchanged behaviour (regression prevention) — **critical for surgical fixes**

### `design.md`

**Created by:** `spec-generator` Phase 2
**Lifecycle:** Permanent

Required sections:
- Overview
- Key Design Decisions
- Architecture
- Components and Interfaces
- Data Models
- Correctness Properties
- Error Handling
- Testing Strategy

Conditional sections (include when applicable):
- API Specifications (when endpoints change)
- Sequence Diagrams (Mermaid, when multi-component interactions exist)
- Cloud Service Integration
- Security Architecture (auth, user data, external input)
- Performance Considerations

### `design-review-summary.md` (features only)

**Created by:** `spec-design-review` gate
**Lifecycle:** Permanent

Records the gate verdict. See [Quality-gates.md](Quality-gates.md).

### `tasks.md`

**Created by:** `spec-generator` Phase 3
**Lifecycle:** Permanent (checkboxes updated during implementation)
**Format:** Numbered tasks with checkboxes

Conventions:
- `- [ ] N. [Title]` for tasks
- Sub-tasks as indented bullets
- `_Requirements: N.N, N.N_` for requirement tracing
- `- [ ] *N. [Title]` for optional tasks
- Checkpoint milestones every 3–5 tasks
- `- [x] N. [Title]` for completed tasks

Sequencing rules:
1. **API-first** — if API changes exist, first task MUST update `openapi.yaml` + regenerate types
2. **Build-and-test** — every implementation task MUST include a build+test sub-task
3. **Test coverage** — explicit tasks for unit, integration, and E2E tests
4. **Mandatory ending tasks** — E2E tests, docs update, final validation

### `task-review-summary.md` (features only)

**Created by:** `spec-task-review` gate
**Lifecycle:** Permanent

Records gate verdict. On pass, deletes `brainstorm.md`.

### `test-plan.md` (features only)

**Created by:** `spec-qa-review` plan mode
**Updated by:** `spec-qa-review` review mode (NEVER replaced)
**Lifecycle:** Permanent

Structure:
- 5-layer coverage matrix (Expected → Actual → Status)
- Unit coverage gate: 85%
- Gap log (with severity: Critical / Important / Minor)
- Test inventory (file → covers)

### `implementation-audit.md` (features only)

**Created by:** `spec-implementation-audit` gate
**Lifecycle:** Permanent

Sections:
- Per-requirement status (implemented / partial / missing)
- Discrepancies list
- Evidence section (test output, build results, API responses)
- Action items

The presence of `implementation-audit.md` is what `spec-maintenance` uses to identify "implemented" work items.

### `spec-maintenance-report.md` (features only, conditional)

**Created by:** `spec-maintenance`
**Lifecycle:** Permanent

Documents which prior work items were impacted by this implementation. Includes annotations made to older `tasks.md` / `design.md` / `requirements.md` files (strikethrough + work-item reference).

If no prior specs were impacted, the report states that clearly.

## What lives OUTSIDE the work-item directory

| Artefact | Location | Reason |
|---|---|---|
| Brainstorming output | `docs/superpowers/specs/{topic}-design.md` | Owned by the `brainstorming` superpowers skill |
| OpenAPI spec | `openapi.yaml` (or the project's API contract location) | Code, not spec |
| Knowledge base updates | `knowledge-base/` | Project-wide docs, not per-work-item |
| ADRs | `docs/` or `knowledge-base/` | Cross-cutting decisions |

The `brainstorm.md` file inside a work item is a COPY of the brainstorming output — the original stays in `docs/superpowers/specs/` and is owned by superpowers.
