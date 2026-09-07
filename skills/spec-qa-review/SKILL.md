---
name: spec-qa-review
description: Use in plan mode after sdd:spec-task-review passes to create test-plan.md before implementation. Use in review mode after sdd:spec-implementation-audit passes to audit test coverage against the plan. Finds gaps across unit, integration, E2E, performance, and security layers.
---

# QA Review

## Overview

Structured quality assurance across two lifecycle phases: planning (before implementation) and review (after implementation).

**Core principle:** Test coverage gaps found before writing code cost nothing to fix. Gaps found after cost a lot.

## Modes

**Plan mode** — reads spec documents, fills the Expected column of the coverage matrix, creates `test-plan.md`.
Trigger: spec exists (`requirements.md` / `design.md`), implementation has not started.

**Review mode** — reads existing tests + source code, fills the Actual column, diffs against Expected, produces gap report and updates `test-plan.md`.
Trigger: implementation is present.

**Force a mode:** "qa-review plan" / "qa-review review"
**When ambiguous** (partial implementation): ask the user which mode to use.

## IMPORTANT: What This Produces

This skill does NOT produce a test specification document. It produces a **coverage analysis** using the 5-layer matrix below.

Do NOT write test case tables, requirement traceability matrices, or property-based test lists.
Write a coverage matrix that answers: "what IS tested / SHOULD be tested at each layer?"

## Coverage Matrix

| Layer | What to analyse |
|---|---|
| **Unit** | Functions, components, edge cases, error paths. **Gate: ≥85% line/branch coverage.** Mark N/A with justification for specs with no unit-testable code (e.g. pure infrastructure). |
| **Integration** | Service boundaries, API contracts, data flow between layers |
| **E2E** | User journeys, happy paths, critical failure paths |
| **Performance** | Load assumptions, response time budgets, bundle/memory size |
| **Security** | Auth boundaries, input validation, sensitive data exposure, OWASP Top 10 relevance |

All five layers are analysed every pass. Do not skip a layer without marking it N/A.

## Plan Mode Process

1. Read `project/work-items/{spec-name}/requirements.md` and `design.md`
2. For each layer, determine what SHOULD be tested — be specific (name the functions, flows, and boundaries)
3. Flag any layer with zero expected coverage as a gap immediately
4. Create `project/work-items/{spec-name}/test-plan.md` with the Expected column populated

**File location is mandatory:** `project/work-items/{spec-name}/test-plan.md` — do not save elsewhere.

## Review Mode Process

1. Read test files for the spec's implementation
2. For each layer, document what IS tested
3. Check unit test coverage report if available; if not, estimate from test count vs code paths
4. Compare Actual vs Expected (if `test-plan.md` exists) or infer Expected from the codebase
5. Update `test-plan.md` — fill Actual column, update gap log (mark Fixed gaps, add new ones)
6. Output gap report in the conversation

**Always update `test-plan.md` in Review mode.** A gap report that disappears from the conversation is not QA — it is noise. The persistent document is the point.

## Test Plan Document

**Location:** `project/work-items/{spec-name}/test-plan.md`
**Never replace — only update.** Created in Plan mode, updated in Review mode.

```markdown
# Test Plan — {Spec Name}

**Created:** YYYY-MM-DD
**Last Updated:** YYYY-MM-DD
**Status:** Draft | Active | Complete

## Coverage Matrix

| Layer       | Expected | Actual | Status |
|-------------|----------|--------|--------|
| Unit        |          |        |        |
| Integration |          |        |        |
| E2E         |          |        |        |
| Performance |          |        |        |
| Security    |          |        |        |

**Unit Coverage Gate:** 85% — Pass / Fail (actual: _%)

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|-------------|----------|--------|--------|--------|

## Test Inventory

| File | Covers |
|------|--------|
```

## Gap Report Format

Output in conversation after every Review mode pass:

```
## QA Gap Report — {spec-name}

### Coverage Summary
| Layer       | Expected | Actual   | Status  |
|-------------|----------|----------|---------|
| Unit        |          |          | ✓/⚠/✗  |
| Integration |          |          | ✓/⚠/✗  |
| E2E         |          |          | ✓/⚠/✗  |
| Performance |          |          | ✓/⚠/✗  |
| Security    |          |          | ✓/⚠/✗  |

Unit coverage: X% (gate: 85%) ✓/✗

### Action Items
**Critical**
- [ ] ...

**Important**
- [ ] ...

**Minor**
- [ ] ...
```

**Severity — use exactly these labels:**
- **Critical** — missing coverage for key user flows, or unit coverage below 85% gate
- **Important** — missing error paths, no performance assertions, incomplete security checks
- **Minor** — test quality issues, duplication, missing edge cases

Do NOT use High/Medium/Low, P0/P1/P2, or any other severity system. Use Critical/Important/Minor only.

Action items are also written to the gap log in `test-plan.md`.

## Common Mistakes

**Never run a partial matrix.** A partial review is not a QA review — it is a guess.
If time is constrained, run the full matrix and note which layers need deeper follow-up.

**Never skip updating `test-plan.md` in Review mode.** The gap report in the conversation is ephemeral.
The persistent document in `test-plan.md` is the deliverable.

## SDD Pipeline Integration

### Plan Mode

**Triggered by:** `sdd:spec-task-review` passes.
**Next step:** `subagent-driven-development` (documented in `sdd:spec-workflow`).

### Review Mode

**Triggered by:** `sdd:spec-implementation-audit` passes.
**On pass:** Invoke `sdd:spec-maintenance`.
**On fail:** Dispatch fix subagents for coverage gaps, re-run review mode. If fails twice, ask the user.
