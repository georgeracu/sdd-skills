<!--
  Tasks Document Template (SDD Bugfix Pipeline)

  Produced by: sdd:spec-generator bugfix mode (after bugfix.md and design.md)
  Consumed by: subagent-driven-development

  Bugfix task lists are smaller than feature task lists. No quality gates
  run on them, so the structure must be self-explanatory.

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real spec.

  Conventions:
  - Numbered tasks with checkbox format: - [ ] N. [Title]
  - Sub-tasks as indented bullets under each task
  - Requirement tracing: _Bugfix: Current N | Expected N | Unchanged N_
  - Mark completed tasks: - [x] N. [Title]

  Required ordering:
  1. Lock down unchanged behaviour FIRST (regression tests before fix)
  2. Reproduce the bug in a test (proves the defect exists)
  3. Apply the fix
  4. Verify both regression tests and reproduction test pass
  5. Final validation
-->

# Bugfix Implementation Plan

## Overview

[Brief description of the fix approach. Reference design.md.]

## Tasks

- [ ] 1. Lock down unchanged behaviour with regression tests
  - Add tests for each Unchanged Behaviour entry from bugfix.md
  - Tests must PASS against current (buggy) implementation
  - These tests are the safety net for the fix
  - Run tests: `[build/test command]`
  - _Bugfix: Unchanged 1, 2, 3_

- [ ] 2. Reproduce the bug in a test
  - Add a failing test that asserts Expected Behaviour
  - Test should FAIL against current implementation (proves the bug)
  - _Bugfix: Current 1, Expected 1_

- [ ] 3. Apply the fix
  - Implement changes per design.md > Changes Required
  - [Specific file/component changes]
  - Run tests — reproduction test should now PASS, regression tests should STILL PASS
  - Run build: `[build command]`
  - _Bugfix: Expected 1, 2_

- [ ] 4. Verify no broader regressions
  - Run full backend build and tests: `[backend build+test command, e.g. from AGENTS.md]`
  - Run full frontend build and tests: `[frontend build+test command, e.g. from AGENTS.md]`
  - Run E2E tests if the fix touches user flows: `[e2e test command, e.g. from AGENTS.md]`
  - Confirm zero failures

- [ ] 5. Update documentation
  - Update knowledge base if the fix corrects documented behaviour
  - Update changelog or release notes if customer-visible
  - Update auto-memory if the bug revealed a project-wide pattern to avoid

<!--
  Bugfixes typically do NOT need:
  - Mandatory E2E task (covered by Task 4 if user-facing)
  - Separate integration test task (covered by Task 1 or 3)
  - Final validation task (covered by Task 4)

  Add them if the fix is large enough to warrant separation.
-->

## Notes

- [Implementation note, e.g., TDD ordering, specific constraints]
- [Dependencies, e.g., must deploy backend before frontend]

## Status Summary

| Phase            | Status      | Details                |
| ---------------- | ----------- | ---------------------- |
| Bugfix (Phase 1) | Not Started | bugfix.md              |
| Design (Phase 2) | Not Started | design.md              |
| Implementation  | Not Started | 0/N tasks complete     |
