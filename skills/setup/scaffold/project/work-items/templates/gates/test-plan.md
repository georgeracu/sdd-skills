<!--
  Test Plan Template

  Produced by: sdd:spec-qa-review plan mode (fills Expected column)
  Updated by:  sdd:spec-qa-review review mode (fills Actual column, updates gap log)

  Never replace — only update. The Expected column is locked in plan mode.
  The Actual column and Gap Log are updated in review mode.

  Severity scale (Gap Log): CRITICAL | MAJOR | MINOR
  Status: PASSED | FAILED, set in review mode. Any open CRITICAL or MAJOR
  gap gives FAILED; MINOR gaps alone give PASSED.

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real plan.
-->

# Test Plan — [Spec Name]

**Created:** [YYYY-MM-DD]
**Last Updated:** [YYYY-MM-DD]
**Stage:** [Draft | Active | Complete]
**Status:** [PASSED | FAILED, set in review mode]

---

## Coverage Matrix

<!--
  Five layers, every one analysed every pass. Mark N/A with justification
  ONLY for layers with no applicable test surface (e.g., pure infra spec
  has no Unit layer). Never silently skip.

  Status: ✓ every expected test present | ⚠ some present | ✗ none present,
  or a listed test file absent | N/A no test surface, with the reason
-->

| Layer       | Expected                                  | Actual                                    | Status |
|-------------|-------------------------------------------|-------------------------------------------|--------|
| Unit        | [Functions, components, edge cases]       | [Filled in review mode]                   |        |
| Integration | [Service boundaries, API contracts]       | [Filled in review mode]                   |        |
| E2E         | [User journeys, critical failure paths]   | [Filled in review mode]                   |        |
| Performance | [Load assumptions, latency budgets]       | [Filled in review mode]                   |        |
| Security    | [Auth, input validation, OWASP relevance] | [Filled in review mode]                   |        |

**Unit Coverage Gate:** 85% lines and branches — [Pass / Fail (actual: _% lines, _% branches)]

---

## Gap Log

<!--
  Status: Open | Fixed | Deferred
-->

| #   | Description                       | Severity  | Status | Opened       | Closed       |
|-----|-----------------------------------|-----------|--------|--------------|--------------|
| 1   | [Description of gap]              | CRITICAL  | Open   | YYYY-MM-DD   |              |
| 2   | [Description of gap]              | MAJOR     | Fixed  | YYYY-MM-DD   | YYYY-MM-DD   |

---

## Test Inventory

<!--
  Built up in review mode. Maps existing test files to what they cover.
-->

| File                                          | Covers                                    |
|-----------------------------------------------|-------------------------------------------|
| [path/to/test/file.kt]                        | [requirements / components covered]       |
| [path/to/test/file.test.ts]                   | [requirements / components covered]       |
