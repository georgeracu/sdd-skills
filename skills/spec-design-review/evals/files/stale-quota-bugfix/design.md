# Bugfix Design Document: Stale Quota Cache

## Overview

The usage endpoint serves Quotas from the Quota_Cache for up to one hour, so a Plan_Change is invisible to Usage_Read until the entry expires. The fix lets a Plan_Change mark the current Billing_Period's `usage-meters` item with a `planVersion`, and lets every Usage_Read compare that version with the one stored in its cached entry. A mismatch discards the entry and loads the new Plan's Quota; a match keeps serving the cache.

## Root Cause

The Quota_Cache is held in the usage Lambda container, while Plan_Change runs in the `plan` handler's Lambda, so the handler cannot clear the cache directly. Nothing in the usage read path observes that the Account's Plan has moved on.

## Key Design Decisions

- **Version marker on an item the read already loads.** Usage_Read already reads the `usage-meters` item for `metered`. Carrying `planVersion` on that item gives the read a freshness signal at no extra table access, which keeps the `accounts` table load unchanged (Requirements 2.1, 2.2, 3.1).
- **Keep the cache and its one-hour TTL.** Entries are discarded only on a version or Billing_Period mismatch or on expiry, so Accounts with no Plan_Change in the period are served exactly as today (Requirement 3.1).
- **Save the Plan and bump the version together.** `PlanService.changePlan`, which the `plan` handler already calls, writes the new Plan to the `accounts` item and `ADD planVersion :one` to the current `usage-meters` item in one `TransactWriteItems` call, so both land or neither does (Requirements 2.1, 2.2).
- **Key the cache by Billing_Period.** `planVersion` lives on the per-period item and starts again at 0 in a new period, so a cache entry carries the period it was loaded in and a new period always reloads once. `changePlan` and `usageFor` both take the Billing_Period from the current UTC time, as the glossary defines it.
- **Keep the increment and the notification as they are.** The version bump touches a separate attribute from `metered`, so the `ADD metered` expression is unchanged; the ingest path gains only the conflict retry in Error Handling (Requirement 3.2). The `plan-changed` notification call is untouched (Requirement 3.3).

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
        val quota = cache.get(accountId, billingPeriod, version) ?: loadAndCache(accountId, billingPeriod, version)
        val metered = meter?.metered ?: 0
        return UsageSummary(accountId, billingPeriod, metered, quota, metered > quota)
    }
}

class UsageMeterRepository {
    fun find(accountId: String, billingPeriod: String): UsageMeter?  // strongly consistent
    fun increment(accountId: String, billingPeriod: String)  // retries TransactionConflictException
}

class PlanService {
    fun changePlan(accountId: String, plan: Plan): Account  // one TransactWriteItems call, then the plan-changed notification
}

class QuotaCache {
    fun get(accountId: String, billingPeriod: String, planVersion: Long): Int?
    fun put(accountId: String, billingPeriod: String, planVersion: Long, quota: Int)
}
```

`QuotaCache.get` returns `null` when the entry is older than one hour or was stored under a different Billing_Period or `planVersion`. `PlanService.changePlan` emits the `plan-changed` notification after the transaction commits, as it does today after the save; the `usage-meters` update creates the item if the period has none.

Both reads in `usageFor` are strongly consistent. A strongly consistent read costs twice the read units; `usage-meters` is on on-demand capacity, so this is a per-read cost and not a capacity limit, and the `accounts` table on provisioned capacity is read only on a cache miss, as today; each miss now costs twice the read units, which the table's provisioned read capacity covers, as ARCHITECTURE.md states. The reads are strongly consistent for these reasons: `meters.find` so that the next Usage_Read sees a committed bump, and the `accounts` read in `loadAndCache` so that a read which sees the new `planVersion` also loads the new Plan. The transaction makes the Plan and the version visible together, so no read can cache the old Quota under the new version.

An ingest `ADD metered` that meets the transaction on the same item fails with `TransactionConflictException`. `UsageMeterRepository.increment` catches that exception and retries the `ADD` up to five times with jittered backoff, so the increment is delayed by milliseconds and not lost (Requirement 3.2); if all five fail, `increment` throws `IngestUnavailable`, which the ingest handler maps to 503, the status it answers to a throttled increment, and the client retries; a Plan_Change is rare and holds the item only for the length of one transaction. DynamoDB does not fail a `GetItem` on an item in an ongoing transaction, whether the read is eventually or strongly consistent; it returns the last committed value. So neither the ingest authentication read of the `accounts` item nor the two reads in `usageFor` need a retry.

IAM authorises each write inside `TransactWriteItems` by its own action, so the `plan` Lambda's role gains `dynamodb:UpdateItem` on `usage-meters` beside the `dynamodb:UpdateItem` on `accounts` it already holds. DynamoDB has no IAM action named `TransactWriteItems`; a policy can single out a transactional write only through the `dynamodb:EnclosingOperation` condition key, which this role does not use.

## Data Models

`usage-meters` (existing, one new attribute):

| Attribute | Type | Notes |
|---|---|---|
| `accountId` | S | Partition key |
| `billingPeriod` | S | Sort key, `YYYY-MM` |
| `metered` | N | Running count, atomic increments from the ingest Lambda |
| `planVersion` | N | Optional; incremented by each Plan_Change in the period, absent means 0 |

The `usage-meters` entry in ARCHITECTURE.md gains `planVersion`, and the Quota_Cache definition in UBIQUITOUS_LANGUAGE.md becomes "keyed by account id, Billing_Period and `planVersion`".

## Correctness Properties

- _For any_ Plan_Change followed by a Usage_Read, the Usage_Read SHALL report the new Plan's Quota. **Validates: Requirements 2.1**
- _For any_ Plan_Change followed by a Usage_Read, `quotaExceeded` SHALL equal `metered > newQuota`. **Validates: Requirements 2.2, 1.2, 1.3**
- _For any_ Account with no Plan_Change between two Usage_Read calls in one Billing_Period inside the TTL, the second SHALL be served from the Quota_Cache. **Validates: Requirements 3.1**
- _For any_ Plan_Change concurrent with ingest increments, every increment SHALL either be recorded or be rejected to the client with 503, never silently dropped. **Validates: Requirements 3.2**
- _For any_ Plan_Change, the `plan-changed` notification SHALL still be emitted exactly once. **Validates: Requirements 3.3**

## Error Handling

If the transaction fails, neither the Plan nor `planVersion` changes, and the `plan` handler returns the 500 `Problem` that every operation already returns for an unhandled error without emitting the `plan-changed` notification, so no reader can see the new Plan with the old Quota or the reverse. Two Plan_Changes on the same Account at once conflict inside DynamoDB; the second fails with `TransactionCanceledException` and gets the same 500, with nothing written. No new status codes or API paths are introduced, and `openapi.yaml` is unchanged.

## Testing Strategy

- Unit tests for `QuotaCache.get` (fresh entry, expired entry, version mismatch, Billing_Period change) and for `QuotaService.usageFor` before and after a version bump, for both an upgrade and a downgrade.
- Unit test that `increment` retries a `TransactionConflictException` and throws `IngestUnavailable` after the fifth failure, and that the ingest handler maps `IngestUnavailable` to 503.
- Integration test that a Plan_Change that conflicts with a concurrent Plan_Change gets `TransactionCanceledException` and the 500, with nothing written.
- Regression test that two reads inside the TTL with no bump perform a single `accounts` read.
- Integration test against DynamoDB Local that ingest increments running during plan-change transactions leave `metered` and `planVersion` both correct.
- Integration test that the `plan-changed` message is still published once per Plan_Change, and not at all when the transaction fails.
