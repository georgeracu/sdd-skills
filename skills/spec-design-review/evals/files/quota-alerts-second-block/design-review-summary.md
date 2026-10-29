# Design Review Summary: Quota Alerts

**Review Date:** 2026-10-27
**Reviewer:** sdd:spec-design-review skill
**Documents Reviewed:**
- requirements.md
- design.md

---

## Overall Status

BLOCKED

**Summary:** The design covers threshold configuration, evaluation, change handling and alert content, but Requirement 3 (suspended accounts) has no design support.

**Statistics:**
- Requirements reviewed: 5
- Acceptance criteria checked: 15
- Critical discrepancies: 1
- Major discrepancies: 0
- Minor discrepancies: 0

---

## Review Results

### Alignments Confirmed

- Requirement 1: the `thresholds` sub-resource follows `plan`, with 400 and 404 responses and the default set `{80, 100}`.
- Requirement 2: evaluation on the ingest increment, with a conditional write to `firedThresholds` on the `usage-meters` item.
- Requirement 4: plan and threshold changes are read from the Account item on the next increment.
- Requirement 5: delivery through the Notification_Service with the `quota-threshold` template and a five-field payload.

### Critical Discrepancies (Must fix before creating tasks)

#### CRITICAL-001: Requirement 3 has no design support

**Issue:** Nothing in design.md reads `account.status`. The evaluator sends alerts and records fired thresholds for suspended Accounts, and no correctness property or test covers ACs 3.1 and 3.2.

**Impact:** A suspended Account receives Quota_Alerts and has thresholds recorded as fired, against Requirement 3.

**Fix Suggestion:** Have `QuotaAlertEvaluator.evaluate` return before any write or send when `account.status` is `suspended`, add a correctness property validating Requirements 3.1 and 3.2, and add a unit test for the suspended case.

**Owner:** Design author

### Major Discrepancies (Should fix for quality)

No major discrepancies found.

### Minor Issues (Can defer to implementation)

No minor issues found.

---

## Architecture Consistency Assessment

The design follows the handler, service and repository layout, reuses the ingest Lambda's Account read, and delivers through the Notification_Service and its queue.

---

## Recommendations

1. Add the suspended-account check, property and test described in CRITICAL-001.
2. Re-run the design review.

---

## Next Steps

Blocked. Fix CRITICAL-001 in design.md and re-run `sdd:spec-design-review` before generating tasks.md.
