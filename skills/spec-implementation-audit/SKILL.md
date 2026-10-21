---
name: spec-implementation-audit
description: Implementation audit gate. Use when every task in a work item's tasks.md is done and before sdd:spec-qa-review review mode, or when re-running after a FAILED audit. Compares the code with requirements.md and design.md and writes implementation-audit.md.
---

# Spec Implementation Audit — Quality Gate

**Workflow Position:** Runs AFTER `subagent-driven-development` completes all tasks and BEFORE `sdd:spec-qa-review` review mode.

## Prerequisites

Verify these files exist in `project/work-items/{NN}-{name}/`:

1. `requirements.md` (or `bugfix.md` for a bugfix work item): the source of truth for what was requested
2. `design.md` — the source of truth for how it should be built

## Audit Process

The audit reads the work item and the code and changes neither. The only file it writes is `implementation-audit.md`.

### 1. Feature Exists

For every requirement in `requirements.md`, locate corresponding code in the codebase:

- Frontend source
- Backend source
- Infrastructure-as-code files (e.g. CloudFormation or Terraform templates)
- API contracts in `openapi.yaml`

**Flag as CRITICAL:** Any requirement with no corresponding implementation. Partially implemented requirements (code exists but incomplete) are also CRITICAL.

### 2. Behaviour Matches

For each implemented requirement, verify the logic matches the spec:

- API contracts match `design.md` endpoint specifications
- Data models match `design.md` schemas
- Business rules match acceptance criteria
- Error responses match specified error codes and messages

**Flag as CRITICAL:** Implementation contradicts spec.

### 3. Correctness Properties

For every property under Correctness Properties in `design.md`, name the acceptance criteria its `Validates` line cites, the code that enforces it and the test that exercises it. Name the property by its number in each finding.

**Flag as CRITICAL:** a property with no enforcing code, or code that contradicts it.
**Flag as MAJOR:** a property with no enforcing test; a property with no `Validates` line, or whose `Validates` line names an acceptance criterion that `requirements.md` does not have.

### 4. Edge Cases Handled

For each requirement's acceptance criteria, verify error states and edge cases:

- Error paths from `requirements.md` are implemented
- Validation logic exists at system boundaries
- Graceful degradation where specified

**Flag as MAJOR:** Missing error handling for specified edge cases.

### 5. Tasks Completed

Read `tasks.md` and count. The audit never edits `tasks.md`: it reads each checkbox as it was when the audit started, and a box ticked during the audit does not count.

- A non-optional task (`- [ ]` without `*` prefix) is complete only when its checkbox is `- [x]`. A status table, a commit hash or the user's word that the task is done is context for the finding, not completion.
- An unticked optional task counts as Skipped, with no finding.

**Flag as CRITICAL:** each non-optional task still unchecked. The action is to tick it or finish it, then re-run the audit.

### 6. Documentation Updated

Verify:

- Architecture docs reflect changes (if system design changed)
- `openapi.yaml` is up to date (run the project's type-generation script, if API types are generated from `openapi.yaml`, and check for diff)
- Knowledge base updated if new patterns were introduced
- Auto-memory updated if project conventions changed

**Flag as MAJOR:** Stale documentation.

### 7. Proof of Implementation

Collect and paste evidence:

- **Test results**: Run the project's backend and frontend build and test commands, as documented in `AGENTS.md` or the README (each if the project has one)
- **E2E results**: Run the project's E2E test command (if the project has one)
- **API type freshness**: Run the project's type-generation script (if API types are generated from `openapi.yaml`) and verify no diff
- **Build output**: Paste actual command output showing pass/fail

Every evidence block holds the command line and its verbatim output. Run each command that `AGENTS.md` or the README names. Output the user supplies counts when it shows the command and its result; the user's word that tests pass does not count. A command the project does not have reads `NOT APPLICABLE: [where AGENTS.md or the README says so]` and is no finding. A command the project has, with no output, reads `NOT RUN: [reason]` and nothing else, and is a MAJOR finding whatever the reason.

## Output

Write `project/work-items/{NN}-{name}/implementation-audit.md` from `project/work-items/templates/gates/implementation-audit.md`. If the template is missing, use these headings: Status, Per-Requirement Status, Task Completion, Discrepancies, Evidence, Verdict.

- Per-Requirement Status: one row per acceptance criterion, with the code file that implements it as evidence.
- Verdict: the outcome and the next step from the Gate.

## Gate

Set the one `**Status:**` line from the findings. Its value is exactly `PASSED` or `FAILED`, with nothing after it. Every `NOT RUN` evidence block is a MAJOR finding. A naming or style issue is a MINOR finding.

- **FAILED** on any CRITICAL or MAJOR finding. Stop once the report is written: the audit does not fix findings, tick tasks or re-run itself. The caller dispatches fix subagents for the specific gaps via `subagent-driven-development`, then re-runs this audit. If the `implementation-audit.md` already in the work item directory had `**Status:** FAILED` before this audit, the caller asks the user instead of dispatching again.
- **PASSED** on MINOR findings only, or none. Invoke `sdd:spec-qa-review` in review mode.
