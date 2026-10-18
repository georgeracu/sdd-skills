# Bugfix Design Document: Stale Quota Cache

## Overview

The usage endpoint serves Quotas from the Quota_Cache for up to one hour, so a Plan_Change is invisible to Usage_Read until the entry expires. The fix lets a Plan_Change mark the current Billing_Period's `usage-meters` item with a `planVersion`, and lets every Usage_Read compare that version with the one stored in its cached entry. A mismatch discards the entry and loads the new Plan's Quota; a match keeps serving the cache.

## Root Cause

The Quota_Cache is held in the usage Lambda container, while Plan_Change runs in the `plan` handler's Lambda, so the handler cannot clear the cache directly. Nothing in the usage read path observes that the Account's Plan has moved on.

## Key Design Decisions

- **Version marker on an item the read already loads.** Usage_Read already reads the `usage-meters` item for `metered`. Carrying `planVersion` on that item gives the read a freshness signal at no extra table access, which keeps the `accounts` table load unchanged (Requirements 2.1, 2.2, 3.1).
- **Keep the cache and its one-hour TTL.** Entries are discarded only on a version mismatch or on expiry, so Accounts with no Plan_Change are served exactly as today (Requirement 3.1).
- **Leave the ingest path and the notification alone.** The plan handler adds one `UpdateItem` that sets `planVersion` with an increment on a separate attribute from `metered`, so concurrent atomic increments from the ingest Lambda are unaffected (Requirement 3.2). The `plan-changed` notification call is untouched (Requirement 3.3).

## Components and Interfaces

```kotlin
class QuotaService(
    private val accounts: AccountRepository,
    private val meters: UsageMeterRepository,
    private val cache: QuotaCache,
) {
    fun usageFor(accountId: String, billingPeriod: String): UsageSummary {
        val meter = meters.find(accountId, billingPeriod)
        val version = meter?.planVersion ?: 0
        val quota = cache.get(accountId, version) ?: loadAndCache(accountId, version)
        val metered = meter?.metered ?: 0
        return UsageSummary(accountId, billingPeriod, metered, quota, metered > quota)
    }
}

class QuotaCache {
    fun get(accountId: String, planVersion: Long): Int?
    fun put(accountId: String, planVersion: Long, quota: Int)
}
```

`QuotaCache.get` returns `null` when the entry is older than one hour or was stored under a different `planVersion`. The `plan` handler calls `UsageMeterRepository.bumpPlanVersion(accountId, billingPeriod)` after `AccountRepository` has saved the new Plan and before it emits the `plan-changed` notification; the bump uses an `UpdateItem` with `ADD planVersion :one` and creates the item if the period has none.

## Data Models

`usage-meters` (existing, one new attribute):

| Attribute | Type | Notes |
|---|---|---|
| `accountId` | S | Partition key |
| `billingPeriod` | S | Sort key, `YYYY-MM` |
| `metered` | N | Running count, atomic increments from the ingest Lambda |
| `planVersion` | N | Optional; incremented by each Plan_Change in the period, absent means 0 |

## Correctness Properties

- _For any_ Plan_Change followed by a Usage_Read, the Usage_Read SHALL report the new Plan's Quota. **Validates: Requirements 2.1**
- _For any_ Plan_Change followed by a Usage_Read, `quotaExceeded` SHALL equal `metered > newQuota`. **Validates: Requirements 2.2, 1.2, 1.3**
- _For any_ Account with no Plan_Change between two Usage_Read calls inside the TTL, the second SHALL be served from the Quota_Cache. **Validates: Requirements 3.1**
- _For any_ Plan_Change concurrent with ingest increments, no increment SHALL be lost. **Validates: Requirements 3.2**
- _For any_ Plan_Change, the `plan-changed` notification SHALL still be emitted exactly once. **Validates: Requirements 3.3**

## Error Handling

If `bumpPlanVersion` fails, the `plan` handler returns its existing 500 `Problem` and does not emit the `plan-changed` notification, so the Account is not reported as changed while readers still see the old Quota. No new status codes or API paths are introduced, and `openapi.yaml` is unchanged.

## Testing Strategy

- Unit tests for `QuotaCache.get` (fresh entry, expired entry, version mismatch) and for `QuotaService.usageFor` before and after a version bump, for both an upgrade and a downgrade.
- Regression test that two reads inside the TTL with no bump perform a single `accounts` read.
- Integration test against DynamoDB Local that a concurrent `ADD metered` and `ADD planVersion` leave both attributes correct.
- Integration test that the `plan-changed` message is still published once per Plan_Change.
