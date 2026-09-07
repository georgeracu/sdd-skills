---
name: spec-generator
description: 'Use when creating a feature spec or bugfix spec as part of the SDD pipeline. Generates structured specs under project/work-items/ following a three-phase workflow (requirements → design → tasks) with quality gates. Triggered after brainstorming or directly for bugfixes.'
user-invocable: true
---

# Spec Generator

## Overview

Generate or update structured specifications under `project/work-items/` following the Kiro spec workflow. This skill supports:

1.  **New Specs**: Creating Feature or Bugfix specs from scratch.
2.  **Existing Specs**: Reviewing and updating existing specs to reflect new requirements or discovered changes.

Each spec produces or maintains three documents through an interactive three-phase process with user review gates between phases.

## Brainstorming Handoff (Feature Specs Only)

When starting a feature spec after brainstorming:

1. Find the most recent file in `docs/superpowers/specs/` matching the topic
2. Determine the next sequential work-item number in `project/work-items/`
3. Create `project/work-items/{NN}-{name}/`
4. Copy the brainstorming output to `brainstorm.md` in that directory
5. Read `brainstorm.md` as context for generating `requirements.md`

The brainstorming doc is reference material — it captures agreed direction and constraints but is not a formal spec document. It will be deleted after `sdd:spec-task-review` passes.

For bugfix specs, skip this section entirely — there is no brainstorming output.

## Updating Existing Specs

When the user asks to "update" or "review" an existing spec:

1.  **Identify the Spec**: Ask for the spec number or directory name.
2.  **Verify State**: Read the existing `requirements.md` (or `bugfix.md`), `design.md`, and `tasks.md`.
3.  **Identify Changes**: Ask the user what needs to be changed or updated.
4.  **Interactive Update**: Follow the same three-phase workflow (Requirements/Bugfix -> Design -> Tasks), but instead of creating new files, update the existing ones while maintaining the project's formatting and tracing standards.
5.  **Task Management**: If updating `tasks.md`, ensure any completed tasks remain marked as completed (`[x]`) unless the change requires re-implementing them. Add new tasks following the **Task Sequencing Rules**.

## Spec Types

| Type                         | Workflow                      | Documents                                  |
| ---------------------------- | ----------------------------- | ------------------------------------------ |
| Feature (Requirements-First) | Requirements -> Design -> Tasks | `requirements.md`, `design.md`, `tasks.md` |
| Feature (Design-First)       | Design -> Requirements -> Tasks | `design.md`, `requirements.md`, `tasks.md` |
| Bugfix                       | Bug Analysis -> Design -> Tasks | `bugfix.md`, `design.md`, `tasks.md`       |

## Reference Materials

Before generating any spec, read these for format and process guidance:

- **Templates**: `project/work-items/template-spec/` -- structural templates for each document
- **Spec Documentation**: `project/work-items/spec-documentation/` -- detailed process documentation
- **Existing Specs**: `.kiro/specs/` -- real examples of completed specs for content quality reference

A repository adopting the pipeline for the first time has none of these. When a reference location is missing, say so once in your reply and carry on: the document structures in this skill and in `references/` are the templates, and `.kiro/specs/` is only a quality reference. Do not stop to ask for the missing reference directories and do not create them. The work item directory under `project/work-items/` is different: Step 1 always creates it, brainstorm copy included, even when it is the first one in the repository.

## Step 1: Gather Input & Determine Spec Type

Ask the user:

1. **Description**: What feature or bug are you speccing?
2. **Spec type**: Feature or Bug?
3. **Workflow** (features only): Requirements-First or Design-First?

Then determine the next available sequential number:

```bash
# Scan existing directories in project/work-items/ for numbered prefixes
ls -d project/work-items/[0-9]*/ 2>/dev/null | sort -t/ -k3 -V | tail -1
```

Without a shell, glob `project/work-items/[0-9]*/` instead. No match means this is work item `01`.

Create the directory with the next sequential number:

- Features: `{NN}-{kebab-case-name}/` (e.g., `01-user-authentication/`)
- Bugs: `{NN}-{kebab-case-name}-bugfix/` (e.g., `02-null-pointer-bugfix/`)

## Step 2: Gather Existing Context

Before generating any document, read relevant project documentation to ensure accuracy and consistency with the existing codebase.

### Required Context Gathering

1. **OpenAPI Specification**: Read the project's API spec to understand existing endpoints, schemas, and patterns:
   ```bash
   # Find the OpenAPI spec
   Glob pattern: **/openapi.yaml
   Glob pattern: docs/apis/**/*.yaml
   ```

2. **Architecture Documentation**: Understand the current system architecture:
   ```bash
   Glob pattern: **/ARCHITECTURE.md
   Glob pattern: project/architecture/**
   Glob pattern: docs/architecture/**
   ```

3. **Domain Terminology**: Use correct domain language throughout specs:
   ```bash
   Glob pattern: **/UBIQUITOUS_LANGUAGE.md
   ```

4. **Existing Specs**: Review completed specs for content quality and style reference:
   ```bash
   Glob pattern: project/work-items/[0-9]*/*.md
   Glob pattern: .kiro/specs/**/*.md
   ```

5. **Existing Tests**: Understand current test patterns and coverage:
   ```bash
   Glob pattern: **/*Test*.kt
   Glob pattern: **/*.test.ts
   Glob pattern: **/*.spec.ts
   ```

6. **Knowledge Base**: Check for relevant domain knowledge:
   ```bash
   Glob pattern: knowledge-base/**/*.md
   ```

### When Context Is Missing

A glob that matches nothing is information, not a blocker. Record in the document's Introduction which sources were unavailable (for example, no `openapi.yaml` yet, so API consistency could not be checked against an existing contract) and generate from what the user supplied. Only stop to ask when the description itself is too thin to write requirements from.

### Why This Matters

- Prevents designing APIs that conflict with existing endpoints
- Ensures data models align with existing schemas
- Maintains consistent naming conventions across the codebase
- Avoids duplicating or contradicting existing architecture decisions
- Identifies reusable components and patterns

## Step 3: Phase 1 -- First Document

Generate the first document based on spec type. **Present to user for review. Iterate until approved.**

The user review stop applies to every spec type. A bugfix spec skips the review skills, not the user reviews: it stops after `bugfix.md` and again after `design.md`.

### Requirements-First Feature -> `requirements.md`

Follow the template at `project/work-items/template-spec/requirements.md`.

Structure:

```markdown
# Requirements Document

## Introduction

[Brief description of the feature, its purpose, and context within the project.]

## Glossary

- **Term_Name**: Definition used throughout this document

## Requirements

### Requirement 1: [Requirement Title]

**User Story:** As a [role], I want [capability], so that [benefit].

#### Acceptance Criteria

1. THE [Glossary_Term] SHALL [behavior]
2. WHEN [condition], THE [Glossary_Term] SHALL [behavior]
3. IF [condition], THEN THE [Glossary_Term] SHALL [behavior]
```

Key conventions:

- Use **EARS notation** (Easy Approach to Requirements Syntax): `WHEN`, `THE [Glossary_Term] SHALL`, `IF...THEN`
- The subject of every acceptance criterion is a glossary term (`THE Notification_Service SHALL`), never `the system`, so that requirements, design components and tests name the same thing
- Every requirement has a **User Story** with acceptance criteria
- Acceptance criteria are **numbered** within each requirement (1.1, 1.2, ... 2.1, 2.2, ...)
- Define a **Glossary** of domain terms used as subjects in EARS statements
- Include edge cases and error handling requirements

### Design-First Feature -> `design.md`

Follow the template at `project/work-items/template-spec/design.md`.

See **Design Document Sections** below for the full structure and required sections.

### Bugfix -> `bugfix.md`

Follow the template structure from existing bugfix specs (e.g., `.kiro/specs/16-httpmethod-null-bugfix/bugfix.md`).

Structure:

```markdown
# Bugfix Requirements Document

## Introduction

[Brief description of the bug, where it occurs, and its impact.]

## Bug Analysis

### Current Behavior (Defect)

1.1 WHEN [condition] THEN the system [incorrect behavior]
1.2 WHEN [condition] THEN the system [incorrect behavior]

### Expected Behavior (Correct)

2.1 WHEN [condition] THEN the system SHALL [correct behavior]
2.2 WHEN [condition] THEN the system SHALL [correct behavior]

### Unchanged Behavior (Regression Prevention)

3.1 WHEN [condition] THEN the system SHALL CONTINUE TO [existing behavior]
3.2 WHEN [condition] THEN the system SHALL CONTINUE TO [existing behavior]
```

Key conventions:

- **Current Behavior** describes what's broken (the defect)
- **Expected Behavior** describes the correct behavior using `SHALL`
- **Unchanged Behavior** documents what must NOT change using `SHALL CONTINUE TO`
- Enumerate every existing neighboring behavior the fix could plausibly disturb, drawing from the issue report, affected service, and architecture notes—not only behaviors the reporter explicitly named.
- Number items with section prefix (1.1, 1.2, 2.1, 2.2, 3.1, 3.2)

## Step 4: Phase 2 -- Second Document

Generate the second document derived from the approved first document. **Present to user for review. Iterate until approved.**

### Requirements-First Feature -> `design.md`

Derive the technical design from the approved requirements:

- Architecture that satisfies all requirements
- Components mapped to requirement areas
- Correctness Properties that validate requirements (with `**Validates: Requirements N.N**` tracing)
- Testing strategy including property-based tests

### Design-First Feature -> `requirements.md`

Derive feasible requirements from the approved design:

- Requirements that are achievable given the architecture
- EARS notation requirements traced to design components
- User stories that match the system's capabilities

### Bugfix -> `design.md`

Derive the fix design from the approved bug analysis:

- **Glossary** with Bug_Condition, Property, and Preservation terms
- **Bug Details** with fault condition and formal specification
- **Hypothesized Root Cause** with code-level analysis
- **Correctness Properties**:
  - Property 1: Fault Condition -- bug condition inputs produce expected behavior
  - Property 2: Preservation -- non-bug inputs produce same behavior as before
- **Fix Implementation** with specific file/function/line changes
- **Testing Strategy** with exploration, fix checking, and preservation checking

## Design Document Sections

See [references/design-document-sections.md](references/design-document-sections.md) for the full list of required and optional sections, and design quality standards.

## Step 5: Design Review Gate (Feature Specs Only)

After the user approves design.md AND both requirements.md and design.md exist, invoke the **sdd:spec-design-review** skill as a quality gate before generating tasks.

**Timing by workflow type:**
- **Requirements-First**: After Phase 2 (design.md) is approved
- **Design-First**: After Phase 2 (requirements.md) is approved (both documents now exist)
- **Bugfix**: Skip this gate. Present design.md to the user for review and, once approved, proceed to tasks.md

**Process:**

1. Invoke the `sdd:spec-design-review` skill
2. The skill validates alignment between requirements.md and design.md
3. It also checks architecture consistency with the existing codebase
4. A `design-review-summary.md` is generated in the spec directory

**Based on review outcome:**

- **APPROVED**: Proceed to tasks.md generation
- **APPROVED WITH MINOR ISSUES**: Inform the user of issues found. Recommend addressing major issues but allow proceeding to tasks.md if the user chooses
- **BLOCKED**: Do NOT proceed to tasks.md. Present the critical issues to the user. When a critical finding has an obvious design-side fix, draft the concrete revision to the affected document (for example, the exact API parameter, validation, or component change) in the same turn rather than only listing the issue and waiting. State explicitly that the design review will be re-run after the revision and before tasks.md generation continues; iterate until the review passes.

```
Both documents approved -> sdd:spec-design-review -> [APPROVED] -> tasks.md
                                                   -> [BLOCKED]  -> Fix documents -> Re-review
```

## Step 6: Phase 3 -- Tasks

Generate `tasks.md` derived from both approved documents. **Present to user for review.**

Follow the template at `project/work-items/template-spec/tasks.md`.

### Task Sequencing Rules

1.  **API-First**: If the spec involves changes to existing APIs or new endpoints, the **first task** MUST be updating the OpenAPI spec file (`openapi.yaml`) and regenerating types (`scripts/generate-api-types.sh`). Implementation tasks MUST follow this and build against the updated spec.

2.  **Build-and-Test After Each Task**: Every implementation task MUST include a sub-task to build the project and run relevant tests. This catches regressions early and ensures incremental correctness.

3.  **Test Coverage Tasks**: Include explicit tasks for adding or updating tests at each layer:
    - **Unit tests**: For new/modified services, handlers, components
    - **Integration tests**: For cross-component interactions (e.g., Lambda handler -> service -> repository)
    - **E2E tests**: For user-facing flows (in the `e2e/` directory)

4.  **Mandatory Ending Tasks**: Every `tasks.md` MUST conclude with the following tasks in order:
    - **Update OpenAPI spec** (if API changes were made but the spec wasn't updated as the first task)
    - **Add or update E2E tests** in the `e2e/` directory (if applicable)
    - **Update documentation and knowledge base**: Update relevant docs (architecture, README, `knowledge-base/README.md`), add/update auto-memory if patterns changed
    - **Final validation**: Run full build and ALL tests (backend + frontend + E2E) to ensure the system compiles and there are no failures

### Checkpoint Tasks

Insert checkpoint tasks every 3-5 implementation tasks. Checkpoints MUST:

- Run the full project build (`backend` and `frontend`)
- Run all tests (unit, integration)
- Verify no regressions in existing functionality
- Pause for user review if questions arise

### Structure

```markdown
# Implementation Plan

## Overview

[Brief description of the implementation approach.]

## Tasks

- [ ] 1. Update OpenAPI spec and regenerate types
  - Update `openapi.yaml` with new/modified endpoints and schemas
  - Run `scripts/generate-api-types.sh` to regenerate TypeScript and Kotlin types
  - Verify generated types compile
  - _Requirements: N.N, N.N_

- [ ] 2. [Backend implementation task]
  - [Sub-task description]
  - Write unit tests for new/modified code
  - Run backend build and tests: `cd backend && ./gradlew build`
  - _Requirements: N.N_

- [ ] 3. [Frontend implementation task]
  - [Sub-task description]
  - Write unit tests for new/modified components
  - Run frontend build and tests: `cd frontend && npm run build && npm test`
  - _Requirements: N.N_

- [ ] 4. Checkpoint - [Milestone description]
  - Run full backend build and tests
  - Run full frontend build and tests
  - Verify no regressions

... [Remaining tasks] ...

- [ ] N-3. Add/update integration tests
  - [Test scenarios covering cross-component interactions]
  - _Requirements: N.N_

- [ ] N-2. Add/update E2E tests
  - Add/update tests in `e2e/` directory
  - Run E2E tests against mock server
  - _Requirements: N.N_

- [ ] N-1. Update documentation and knowledge base
  - Update architecture docs if system design changed
  - Update `knowledge-base/README.md` with new patterns or decisions
  - Update auto-memory if project conventions changed
  - _Requirements: N.N_

- [ ] N. Final validation
  - Run full backend build and tests: `cd backend && ./gradlew build`
  - Run full frontend build and tests: `cd frontend && npm run build && npm test`
  - Run E2E tests: `cd e2e && npm test`
  - Verify OpenAPI spec is up to date: `scripts/generate-api-types.sh` produces no diff
  - Confirm all tests pass with zero failures
```

Key conventions:

- **Numbered tasks** with checkbox format: `- [ ] N. [Task title]`
- **Sub-tasks** as indented bullet points under each task
- **Requirement tracing**: `_Requirements: N.N, N.N_` on each task
- **Build/test sub-tasks**: Every implementation task includes a build+test step
- **Optional tasks** marked with `*` prefix on the task number: `- [ ] *N. [Task title]`
- **Checkpoint milestones** every 3-5 tasks: `- [ ] N. Checkpoint - [description]`
- **Property-based test tasks** (optional, marked with `*`): write PBT tests for correctness properties
- **Notes section** with implementation guidelines
- **Status Summary** table tracking overall progress

## Step 6b: Task Review Gate (Feature Specs Only)

After the user approves `tasks.md` for a feature spec, invoke the **sdd:spec-task-review** skill as a quality gate before implementation.

**For bugfix specs:** Skip this gate entirely. Proceed directly from tasks.md to execution.

**Process:**

1. Invoke the `sdd:spec-task-review` skill
2. The skill validates requirements coverage, design coverage, sequencing, implementability, and completeness
3. A `task-review-summary.md` is generated in the spec directory

**Based on review outcome:**

- **APPROVED**: `brainstorm.md` is deleted. Proceed to `sdd:spec-qa-review` plan mode.
- **APPROVED WITH ISSUES**: Inform user. Recommend fixes but allow proceeding.
- **BLOCKED**: Fix `tasks.md` issues, re-run review.

## Step 7: Quality Checklist

Before finalizing each document, verify against the checklists in [references/quality-checklist.md](references/quality-checklist.md) covering requirements, design, and tasks documents.

## Step 8: Execution Support

When the user asks to implement tasks:

1.  Tasks can be executed **one at a time** or **all at once** (only incomplete required tasks).
2.  **Post-Task Validation & Commit**: After completing every top-level task (e.g., Task 1, Task 2):
    - Run the project build and all relevant tests.
    - If the build and tests pass, **commit the changes** using the project's commit conventions (see `AGENTS.md`).
    - If the build or tests fail, **fix the issue before proceeding** to the next task.
3.  Update task checkboxes as work progresses: `- [ ]` -> `- [x]`
4.  Update the **Status Summary** section to reflect current progress.
5.  At **Checkpoint** tasks, run full builds and tests and verify before proceeding.
6.  **Never skip test or build steps** -- these are not optional, they catch regressions early.

### Build & Test Commands Reference

Keep these handy during execution:

| What                          | Command                                             |
| ----------------------------- | --------------------------------------------------- |
| Backend build + tests         | `cd backend && ./gradlew build`                     |
| Frontend build                | `cd frontend && npm run build`                      |
| Frontend tests                | `cd frontend && npm test`                           |
| E2E tests (mock)              | `cd e2e && npm test`                                |
| E2E tests (production)        | `cd e2e && BASE_URL=https://<production-url> npm run test:e2e:smoke` |
| Regenerate API types          | `scripts/generate-api-types.sh`                     |
| Check OpenAPI spec freshness  | Run generate script and verify no git diff           |

## Workflow Summary

```
User describes feature/bug
        |
        v
  Determine spec type
  & create directory
        |
        v
  Brainstorming handoff       <-- Copy brainstorm.md (features only)
  (Feature specs only)
        |
        v
  Gather existing context
        |
        v
+-- Feature (Req-First) --> requirements.md --> design.md --> sdd:spec-design-review --> tasks.md --> sdd:spec-task-review
|
+-- Feature (Design-First) -> design.md --> sdd:spec-design-review --> requirements.md --> tasks.md --> sdd:spec-task-review
|
+-- Bugfix -----------------> bugfix.md --> design.md --> tasks.md (no review-skill gates)
                                |              |              |
                                v              v              v
                           User Review    User Review    User Review
                           & Iterate      & Iterate      & Iterate
```
