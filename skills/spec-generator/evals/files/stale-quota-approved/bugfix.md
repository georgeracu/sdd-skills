# Bugfix Requirements Document

## Introduction

The usage endpoint caches an Account's Quota for one hour in `QuotaService`, so a plan upgrade leaves customers seeing the old quota and `quotaExceeded` value even though the account was updated. Downgrades have the inverse safety problem and may fail to enforce the lower quota during the same window.

## Glossary

- **Bug_Condition**: A plan change occurs and the next usage read uses a stale cached Quota.
- **Quota_Cache**: The in-process one-hour cache in `QuotaService` keyed by account id.
- **Usage_Read**: A call to `QuotaService.usageFor` that reports metered usage, Quota, and `quotaExceeded`.
- **Plan_Change**: A successful update from one Plan to another.
- **Plan_Changed_Notification**: The existing notification emitted by the plan-change handler.

## Bug Analysis

### Current Behavior (Defect)

1.1 WHEN a Plan_Change upgrades an Account, THEN the next Usage_Read SHALL currently use the old Quota from the Quota_Cache for up to one hour.

1.2 WHEN a Plan_Change upgrades an Account above its current usage, THEN the Usage_Read SHALL currently continue reporting `quotaExceeded: true` until the Quota_Cache expires.

1.3 WHEN a Plan_Change downgrades an Account, THEN the next Usage_Read SHALL currently use the old higher Quota and may incorrectly report `quotaExceeded: false` for up to one hour.

### Expected Behavior (Correct)

2.1 WHEN a Plan_Change succeeds, THEN the next Usage_Read SHALL load and report the new Plan's Quota immediately.

2.2 WHEN a Plan_Change succeeds, THEN the next Usage_Read SHALL calculate `quotaExceeded` from the new Quota without waiting for the one-hour Quota_Cache TTL.

### Unchanged Behavior (Regression Prevention)

3.1 WHEN an Account has no Plan_Change between Usage_Read calls, THEN the QuotaService SHALL CONTINUE TO serve a cached Quota until its one-hour TTL expires.

3.2 WHEN a Plan_Change occurs, THEN the ingest path SHALL CONTINUE TO record events during any read-cache transition.

3.3 WHEN a Plan_Change occurs, THEN the Plan_Changed_Notification SHALL CONTINUE TO fire.
