# Test Plan: Quota Alerts

**Created:** 2026-10-12
**Last Updated:** 2026-10-12
**Status:** Active

---

## Coverage Matrix

| Layer       | Expected | Actual | Status |
|-------------|----------|--------|--------|
| Unit        | `ThresholdSetTest`: default 80/100, accepts 1 to 5 distinct integers, rejects empty, more than five, duplicates, out-of-range and non-integer values. `QuotaAlertEvaluatorTest`: first crossing sends one `quota-threshold` alert with the full payload, already-fired threshold not re-sent, two thresholds crossed by one increment, suspended account silent, plan change evaluated against the new Quota, lowered threshold fires on next increment, lost conditional write sends nothing | [Filled in review mode] | |
| Integration | `ThresholdHandlerTest`: GET default set, PUT then GET round trip, 400 `threshold-set-invalid` for a duplicate, 404 for an unknown Account. `FiredStateRepositoryTest`: `markFired` once per threshold and period, reset on the next Billing_Period | [Filled in review mode] | |
| E2E | `quota-alerts.spec.ts` through the mock server: replace and read back thresholds, invalid list rejected with the stored set unchanged | [Filled in review mode] | |
| Performance | `EvaluatorBudgetTest`: five thresholds evaluated inside the 5 ms ingest budget, no extra DynamoDB read | [Filled in review mode] | |
| Security | `ThresholdAuthTest`: unauthenticated PUT rejected with the set unchanged, caller for another Account forbidden, `quota-threshold` payload carries no owner email | [Filled in review mode] | |

**Unit Coverage Gate:** 85%: [Pass / Fail (actual: _%)]

---

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|-------------|----------|--------|--------|--------|
| 1 | Plan lists no performance assertion for the ingest path | Important | Open | 2026-10-12 | |

---

## Test Inventory

| File | Covers |
|------|--------|
