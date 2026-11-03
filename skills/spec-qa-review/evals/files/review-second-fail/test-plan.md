# Test Plan: Quota Alerts

**Created:** 2026-10-12
**Last Updated:** 2026-10-14
**Stage:** Active
**Status:** FAILED

---

## Coverage Matrix

| Layer       | Expected | Actual | Status |
|-------------|----------|--------|--------|
| Unit        | `ThresholdSetTest`: default 80/100, accepts 1 to 5 distinct integers, rejects empty, more than five, duplicates, out-of-range and non-integer values. `QuotaAlertEvaluatorTest`: first crossing sends one `quota-threshold` alert with the full payload, already-fired threshold not re-sent, two thresholds crossed by one increment, suspended account silent, plan change evaluated against the new Quota, lowered threshold fires on next increment, lost conditional write sends nothing | `ThresholdSetTest`: default set, accepts one to five distinct integers, rejects empty (3 cases). `QuotaAlertEvaluatorTest`: first crossing, already-fired not re-sent (2 cases). Missing: more than five, duplicate, out-of-range and non-integer rejections; two thresholds in one increment, suspended account, plan change, lowered threshold, lost conditional write | ⚠ |
| Integration | `ThresholdHandlerTest`: GET default set, PUT then GET round trip, 400 `threshold-set-invalid` for a duplicate, 404 for an unknown Account on GET and on PUT, with nothing written. `FiredStateRepositoryTest`: `markFired` once per threshold and period, reset on the next Billing_Period | `ThresholdHandlerTest`: default GET, PUT then GET, 400 on duplicate, 404 unknown Account on GET and PUT. `FiredStateRepositoryTest`: once per threshold and period, reset on the next Billing_Period | ✓ |
| E2E | `quota-alerts.spec.ts` through the mock server: replace and read back thresholds, invalid list rejected with the stored set unchanged | `quota-alerts.spec.ts`: replace and read back, invalid list rejected with the set unchanged | ✓ |
| Performance | `EvaluatorBudgetTest`: five thresholds evaluated inside the 5 ms ingest budget, no extra DynamoDB read | `EvaluatorBudgetTest`: five thresholds inside the 5 ms ingest budget | ✓ |
| Security | `ThresholdAuthTest`: unauthenticated PUT rejected with the set unchanged, caller for another Account forbidden, `quota-threshold` payload carries no owner email | `ThresholdAuthTest`: unauthenticated PUT rejected with the set unchanged, other Account forbidden, payload carries no owner email | ✓ |

**Unit Coverage Gate:** 85%: Fail (actual: 56% lines, 38% branches)

---

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|-------------|----------|--------|--------|--------|
| 1 | Plan lists no performance assertion for the ingest path | MAJOR | Fixed | 2026-10-12 | 2026-10-14 |
| 2 | Unit coverage 56% lines and 38% branches, below the 85% gate; ThresholdSet.of and QuotaAlertEvaluator branches untested | CRITICAL | Open | 2026-10-14 | |

---

## Test Inventory

| File | Covers |
|------|--------|
| `ThresholdSetTest.kt` | ThresholdSet default and accepted lists |
| `QuotaAlertEvaluatorTest.kt` | first crossing, no re-send |
| `ThresholdHandlerTest.kt` | thresholds API against DynamoDB Local |
| `FiredStateRepositoryTest.kt` | fired-state write and reset |
| `quota-alerts.spec.ts` | thresholds journey through the mock server |
| `EvaluatorBudgetTest.kt` | ingest-path latency budget |
| `ThresholdAuthTest.kt` | authorisation and payload content |
