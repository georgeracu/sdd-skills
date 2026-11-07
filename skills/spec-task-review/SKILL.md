---
name: spec-task-review
description: Use when tasks.md is approved and implementation is about to start, or when a BLOCKED task review needs a re-run after tasks.md was fixed. Checks requirement and design coverage, sequencing and implementability, then writes task-review-summary.md.
metadata:
  sdd-kind: model-invoked
---

# Spec Task Review — Quality Gate

Validate that `tasks.md` is complete, correctly sequenced, and implementable before starting development.

**Workflow Position:** Runs AFTER `sdd:spec-generator` Phase 3 (tasks.md) and BEFORE `sdd:spec-qa-review` plan mode.

The work item lives in `project/work-items/{NN}-{name}/`.

## Review Process

### 1. Requirements Coverage

Extract every acceptance criterion from `requirements.md`. For each, verify at least one task in `tasks.md` has a matching `_Requirements: N.N_` trace. A single task may trace to multiple criteria via comma-separated values (e.g., `_Requirements: 1.1, 1.3_`). A trace from a test, checkpoint or validation task alone does not cover a criterion: an implementation task must build it.

**Flag as CRITICAL:** Any acceptance criterion with no corresponding task.

### 2. Design Coverage

Extract every component, interface, endpoint, and data model from `design.md`. For each, verify at least one task covers its implementation.

**Flag as CRITICAL:** Any design component with no corresponding task.

### 3. Sequencing

Verify task ordering respects dependencies:

- A task that imports, calls or builds on an artefact (generated type, endpoint, schema, table) comes after the task that creates it
- Unit tests within each task, integration/E2E tests at the end
- Checkpoint tasks every 3-5 implementation tasks

**Flag as MAJOR:** A task that uses an artefact a later task creates. Name the artefact, the creating task and the consuming task.

### 4. Implementability

For each task, verify:

- **Defined**: every component, helper or endpoint the task names exists in `design.md`, the shared docs or the repository
- **Unambiguous**: no instruction leaves a choice open, such as "add appropriate error handling"
- **File paths**: each path is marked as new, exists on disk, or appears in `design.md` or the shared docs. With no repository available, record the path as unverified and raise no finding
- **Scope**: one subagent session covers one component, or one layer of one endpoint, with its unit tests. Ending tasks are checked in step 5, not here

**Flag as MAJOR:** An undefined name, an ambiguous instruction, or a task spanning several components.
**Flag as MINOR:** A task that could be split for clarity.

### 5. Ending Tasks

Verify the last tasks cover:

- Integration tests
- E2E tests, when `design.md` has a user-facing flow
- Documentation update
- Final validation (full build and all tests)

**Flag as MAJOR:** Missing mandatory ending tasks.
**Flag as MINOR:** One task that combines two ending tasks.

## Status

Write exactly one `**Status:**` line, set by the findings:

- **BLOCKED**: any CRITICAL (missing requirement coverage, missing design coverage)
- **APPROVED WITH ISSUES**: no CRITICAL and at least one MAJOR (sequencing errors, undefined names, ambiguous instructions, oversized tasks, missing mandatory tasks)
- **APPROVED**: only MINOR (a task that could be split for clarity, combined ending tasks) or no findings

## Output

Write `project/work-items/{NN}-{name}/task-review-summary.md` from `project/work-items/templates/gates/task-review-summary.md`. Without the template, use these headings in order: Coverage Matrix, Design Coverage, Sequencing Review, Implementability Review, Mandatory Ending Tasks, Issues Found, Verdict, Next Steps. Under Issues Found, give each finding its own heading, `### CRITICAL-001: {title}`, `### MAJOR-001: {title}` or `### MINOR-001: {title}`.

## Gate

Before writing, read any `task-review-summary.md` already in the work-item directory.

- **APPROVED** → delete `discovery.md` from the work-item directory and invoke `sdd:spec-qa-review` in plan mode.
- **APPROVED WITH ISSUES** → warn the user with the MAJOR findings, delete `discovery.md` and proceed as for APPROVED.
- **BLOCKED** → keep `discovery.md`, present the issues, fix `tasks.md` and re-run this review. If the previous summary is already BLOCKED, stop and ask the user how to proceed.
