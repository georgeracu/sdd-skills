<!--
  Test Plan Template

  Produced by: sdd:spec-qa-review plan mode (fills Expected column)
  Updated by:  sdd:spec-qa-review review mode (fills Actual column, updates gap log)

  Never replace — only update. The Expected column is locked in plan mode.
  The Actual column and Gap Log are updated in review mode.

  Severity scale (Gap Log): Critical | Important | Minor
  (NOT CRITICAL/MAJOR/MINOR — that scale is for the gate review summaries.)

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real plan.
-->

# Test Plan — [Spec Name]

**Created:** [YYYY-MM-DD]
**Last Updated:** [YYYY-MM-DD]
**Status:** [Draft | Active | Complete]

---

## Coverage Matrix

<!--
  Five layers, every one analysed every pass. Mark N/A with justification
  ONLY for layers with no applicable test surface (e.g., pure infra spec
  has no Unit layer). Never silently skip.

  Status: ✓ (meets expected) | ⚠ (partial) | ✗ (gap) | N/A
-->

| Layer       | Expected                                  | Actual                                    | Status |
|-------------|-------------------------------------------|-------------------------------------------|--------|
| Unit        | [Functions, components, edge cases]       | [Filled in review mode]                   |        |
| Integration | [Service boundaries, API contracts]       | [Filled in review mode]                   |        |
| E2E         | [User journeys, critical failure paths]   | [Filled in review mode]                   |        |
| Performance | [Load assumptions, latency budgets]       | [Filled in review mode]                   |        |
| Security    | [Auth, input validation, OWASP relevance] | [Filled in review mode]                   |        |

**Unit Coverage Gate:** 85% — [Pass / Fail (actual: _%)]

---

## Gap Log

<!--
  Severity: Critical | Important | Minor
  - Critical: missing coverage for key user flows, or unit coverage < 85%
  - Important: missing error paths, no performance assertions, incomplete security checks
  - Minor: test quality issues, duplication, missing edge cases

  Status: Open | Fixed | Deferred
-->

| #   | Description                       | Severity  | Status | Opened       | Closed       |
|-----|-----------------------------------|-----------|--------|--------------|--------------|
| 1   | [Description of gap]              | Critical  | Open   | YYYY-MM-DD   |              |
| 2   | [Description of gap]              | Important | Fixed  | YYYY-MM-DD   | YYYY-MM-DD   |

---

## Test Inventory

<!--
  Built up in review mode. Maps existing test files to what they cover.
-->

| File                                          | Covers                                    |
|-----------------------------------------------|-------------------------------------------|
| [path/to/test/file.kt]                        | [requirements / components covered]       |
| [path/to/test/file.test.ts]                   | [requirements / components covered]       |
