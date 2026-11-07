---
name: spec-qa-review
description: "Use for plan mode, before implementation: write test-plan.md across unit, integration, E2E, performance and security, after sdd:spec-task-review passes. Use for review mode, after sdd:spec-implementation-audit passes: audit tests against the plan and gate on coverage."
metadata:
  sdd-kind: model-invoked
---

# QA Review

The output is `test-plan.md`, beside `requirements.md` in `project/work-items/{NN}-{name}/`. It is a coverage analysis: for each of the five layers below, what is tested against what is expected. Plan mode creates it; review mode updates it and never replaces it.

## Modes

**Plan mode** fills the Expected column. Run it when the spec exists and no implementation does.

**Review mode** fills the Actual column, compares it with Expected, updates the gap log and sets the Status. Run it when the implementation is present.

The user can force a mode ("qa-review plan" or "qa-review review"). When the implementation is partial, ask the user which mode to run.

## Coverage Matrix

| Layer | What to analyse |
|---|---|
| Unit | Functions, components, edge cases, error paths. **Gate: line coverage and branch coverage each ≥85%; Fail when either is lower** (project default; adjust in the template if your project sets a different bar). Mark N/A with the reason for specs with no unit-testable code (e.g. pure infrastructure). |
| Integration | Service boundaries, API contracts, data flow between layers |
| E2E | User journeys, happy paths, critical failure paths |
| Performance | Load assumptions, response time budgets, bundle/memory size |
| Security | Auth boundaries, input validation, sensitive data exposure, OWASP Top 10 relevance |

Analyse all five layers on every pass, and mark a layer N/A only with the reason. When time is short, still run the full matrix and note which layers need deeper follow-up.

Mark each layer's Status:

- **✓** every expected test is present.
- **⚠** some expected tests are present.
- **✗** no expected test is present, or a test file the plan lists is absent.
- **N/A** the layer has no test surface; give the reason.

An expected test that is absent from the code tree is missing, whatever `test-plan.md` says.

## Plan Mode

1. Read `requirements.md` (or `bugfix.md` for a bugfix work item) and `design.md`.
2. For each layer, write the Expected entry: name the functions, flows and boundaries to test.
3. When the requirements imply a layer that `design.md` omits, write the Expected entry from `requirements.md`, add a MAJOR gap row saying the design omits it, and name it in the reply. Do not edit `design.md`.
4. Flag any layer with no expected coverage as a gap.
5. Create `test-plan.md` with the Expected column filled and the Actual column empty.

## Review Mode

1. Read the `**Status:**` line in `test-plan.md` before you change the file; the Gate section needs it.
2. Read the test files for the implementation, and fill the Actual column with what each layer tests.
3. Read the unit coverage report. If there is none, estimate from the test count against the code paths.
4. Compare Actual with Expected, mark each layer's Status, and set the Unit Coverage Gate with both figures.
5. Update the gap log and the Test Inventory, then set the `**Status:**` line.
6. Write the gap report in the reply.

Plan mode locks the Expected column. Review mode changes only the Actual and Status columns, the Unit Coverage Gate, the gap log, the Test Inventory and the `**Status:**` line. Close a fixed gap with Status Fixed and a Closed date, and write every action item as a gap-log row.

## Severity and Status

Use exactly these labels:

- **CRITICAL**: unit coverage below the gate, or no coverage for a key user flow.
- **MAJOR**: a layer marked ✗, or (plan mode) a layer the design omits.
- **MINOR**: a layer marked ⚠, test quality issues, duplication, missing edge cases.

In review mode, set the `**Status:**` line to one value:

- **FAILED**: the gap log holds an open CRITICAL or MAJOR gap.
- **PASSED**: otherwise. MINOR gaps alone do not fail the review.

Plan mode writes the line as `**Status:** [PASSED | FAILED, set in review mode]`.

## Output

Use `project/work-items/templates/gates/test-plan.md` as the structure. If it is absent, use these headings: a title, `**Created:**`, `**Last Updated:**`, `**Stage:** Draft | Active | Complete`, `**Status:** [PASSED | FAILED, set in review mode]`, then `## Coverage Matrix` (Layer, Expected, Actual, Status) followed by `**Unit Coverage Gate:**` with the line and branch figures, `## Gap Log` (#, Description, Severity, Status, Opened, Closed) and `## Test Inventory` (File, Covers).

After every review-mode pass, write this gap report in the reply:

```
## QA Gap Report: {NN}-{name}

**Status:** PASSED | FAILED

### Coverage Summary
| Layer | Expected | Actual | Status |
(one row per layer, Status ✓, ⚠, ✗ or N/A)

Unit coverage: X% lines, Y% branches (gate: 85% each) ✓/✗

### Action Items
**CRITICAL** / **MAJOR** / **MINOR**, each a checkbox list
```

## Gate

**Plan mode:** the next step is `subagent-driven-development` (documented in `sdd:spec-workflow`).

**Review mode, PASSED:** invoke `sdd:spec-maintenance`.

**Review mode, FAILED:** if the `**Status:**` line read FAILED before this run, stop and ask the user how to proceed. Otherwise dispatch fix subagents for the open CRITICAL and MAJOR gaps, then re-run review mode.
