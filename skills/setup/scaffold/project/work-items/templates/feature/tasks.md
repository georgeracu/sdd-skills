<!--
  Tasks Document Template (SDD Feature Pipeline)

  Produced by: sdd:spec-generator Phase 3
  Consumed by: sdd:spec-task-review, subagent-driven-development,
               sdd:spec-implementation-audit

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real spec.

  Conventions:
  - Numbered tasks with checkbox format: - [ ] N. [Title]
  - Sub-tasks as indented bullets under each task
  - Requirement tracing: _Requirements: N.N, N.N_  (mandatory)
  - Optional tasks prefixed with *: - [ ] *N. [Title]
  - Checkpoint milestones every 3-5 tasks
  - Mark completed tasks: - [x] N. [Title]

  Task Sequencing Rules (validated by sdd:spec-task-review):
  1. API-First: If API changes exist, first task MUST update openapi.yaml + regenerate types
  2. Build-and-Test: Every implementation task MUST include a build+test sub-task
  3. Test Coverage: Include explicit tasks for unit, integration, and E2E tests
  4. Mandatory Ending Tasks (in order):
     - Integration tests
     - E2E tests (in e2e/ directory)
     - Documentation and knowledge base updates
     - Final validation (ALL builds and tests)
-->

# Implementation Plan

## Overview

[Brief description of the implementation approach, methodology (e.g., TDD), and overall strategy.]

## Tasks

<!--
  If the feature involves API changes, this MUST be the first task.
  Remove this task if no API changes are needed.
-->

- [ ] 1. Update OpenAPI spec and regenerate types
  - Update `openapi.yaml` with new/modified endpoints and schemas
  - Regenerate API types with the project's type-generation script
  - Verify generated types compile: `[project build command, e.g. from AGENTS.md]`
  - _Requirements: N.N, N.N_

- [ ] 2. [Backend implementation task]
  - [Sub-task description]
  - [Sub-task description]
  - Write unit tests for new/modified code
  - Run backend build and tests: `[backend build+test command, e.g. from AGENTS.md]`
  - _Requirements: N.N_

- [ ] 3. [Backend or frontend implementation task]
  - [Sub-task description]
  - Write unit tests for new/modified code
  - Run build and tests
  - _Requirements: N.N_

- [ ] 4. Checkpoint - [Milestone description]
  - Run full backend build and tests: `[backend build+test command, e.g. from AGENTS.md]`
  - Run full frontend build and tests: `[frontend build+test command, e.g. from AGENTS.md]`
  - Verify no regressions in existing functionality
  - Ask the user if questions arise

- [ ] 5. [Frontend implementation task]
  - [Sub-task description]
  - Write unit tests for new/modified components
  - Run frontend build and tests: `[frontend build+test command, e.g. from AGENTS.md]`
  - _Requirements: N.N_

- [ ] 6. [Implementation task]
  - [Sub-task description]
  - Write unit tests for new/modified code
  - Run build and tests
  - _Requirements: N.N, N.N_

- [ ] *7. [Optional task title]
  - [Optional sub-task description]
  - _Requirements: N.N_

- [ ] 8. Checkpoint - [Milestone description]
  - Run full backend build and tests
  - Run full frontend build and tests
  - Verify no regressions

<!--
  Mandatory ending tasks. These MUST appear at the end of every tasks.md.
  Adjust N-3, N-2, N-1, N numbering to match your actual task count.
  sdd:spec-task-review flags missing mandatory tasks as MAJOR.
-->

- [ ] N-3. Add/update integration tests
  - [Test scenarios covering cross-component interactions]
  - Run backend tests: `[backend build+test command, e.g. from AGENTS.md]`
  - _Requirements: N.N_

- [ ] N-2. Add/update E2E tests
  - Add/update tests in `e2e/` directory
  - Run E2E tests against mock server: `[e2e test command, e.g. from AGENTS.md]`
  - _Requirements: N.N_

- [ ] N-1. Update documentation and knowledge base
  - Update architecture docs if system design changed
  - Update `knowledge-base/README.md` with new patterns or decisions
  - Update auto-memory if project conventions changed
  - _Requirements: N.N_

- [ ] N. Final validation
  - Run full backend build and tests: `[backend build+test command, e.g. from AGENTS.md]`
  - Run full frontend build and tests: `[frontend build+test command, e.g. from AGENTS.md]`
  - Run E2E tests: `[e2e test command, e.g. from AGENTS.md]`
  - Verify OpenAPI spec is up to date: run `[type-generation script, e.g. from AGENTS.md]` and confirm no git diff
  - Confirm all tests pass with zero failures

<!--
  Task guidelines:
  - Each task should be independently completable by a single subagent
  - Sub-tasks provide implementation detail
  - Every implementation task includes build+test as a sub-task
  - Checkpoints verify progress and catch issues early
  - Optional tasks (*) can be skipped without breaking the feature
  - Keep tasks focused: one logical unit of work per task
  - Never skip test or build steps
-->

## Notes

<!--
  Include implementation notes:
  - Development methodology (TDD, etc.)
  - Key constraints or conventions
  - Dependencies on external systems
  - Performance considerations
-->

- [Implementation note]
- [Convention or constraint]

## Status Summary

<!--
  Track overall progress. Update as tasks are completed.
-->

| Phase                  | Status      | Details                              |
| ---------------------- | ----------- | ------------------------------------ |
| Requirements (Phase 1) | Not Started | requirements.md                      |
| Design (Phase 2)       | Not Started | design.md + design-review-summary.md |
| Tasks (Phase 3)        | Not Started | tasks.md + task-review-summary.md    |
| Test Plan              | Not Started | test-plan.md (Expected column)       |
| Implementation         | Not Started | 0/N tasks complete                   |
| Audit                  | Not Started | implementation-audit.md              |
| QA Review              | Not Started | test-plan.md (Actual column)         |
| Maintenance            | Not Started | spec-maintenance-report.md           |
