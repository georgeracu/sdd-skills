# Bugfix Design Document: Empty Billing_Period Usage

## Overview

The ingest Lambda creates an Account's `usage-meters` item for a Billing_Period with its first `ADD metered`, so until the first Metered_Event of the Billing_Period there is no item. `QuotaService.usageFor` assumes the item exists and fails when it does not. The fix reads a missing item as `metered` 0 in `usageFor`. Nothing else changes.

## Root Cause

`usageFor` reads the item with `meters.find(accountId, billingPeriod)!!.metered`. `UsageMeterRepository.find` returns `null` when the item does not exist, so the `!!` throws `NullPointerException`. The usage handler does not catch it, and like every operation it answers an unhandled error with a 500 `Problem`.

## Key Design Decisions

- **Read a missing item as 0.** No item means no Metered_Event in the Billing_Period, which is a Usage_Meter of 0. `usageFor` uses `?.metered ?: 0` in place of `!!.metered` (Requirements 2.1, 2.2).
- **Do not write on the read path.** The read does not create the missing item. The ingest Lambda stays the only writer of `usage-meters`, so the fix adds no write, no transaction and no new IAM permission.
- **Load the Quota before the item, as today.** `usageFor` already takes the Quota from the Quota_Cache, or from the `accounts` table on a miss, before it reads the item. An unknown account id therefore still fails on the `accounts` load with `AccountNotFound` and gets the 404, and the new 0 path does not hide it (Requirement 3.3).
- **Keep the Quota_Cache and the `accounts` load.** The fix does not touch `QuotaCache`, its one-hour TTL or `loadAndCache`, so each Usage_Read makes the same table reads as today (Requirement 3.2).

## Components and Interfaces

Only the body of `QuotaService.usageFor` changes.

```kotlin
class QuotaService(
    private val accounts: AccountRepository,
    private val meters: UsageMeterRepository,
    private val cache: QuotaCache,
) {
    fun usageFor(accountId: String, billingPeriod: String): UsageSummary {
        val quota = cache.get(accountId) ?: loadAndCache(accountId)
        val metered = meters.find(accountId, billingPeriod)?.metered ?: 0  // was: !!.metered
        return UsageSummary(accountId, billingPeriod, metered, quota, metered > quota)
    }

    private fun loadAndCache(accountId: String): Int  // unchanged: accounts.find, then cache.put
}

class AccountRepository {
    fun find(accountId: String): Account  // unchanged: throws AccountNotFound when there is no item
}

class UsageMeterRepository {
    fun find(accountId: String, billingPeriod: String): UsageMeter?  // unchanged: null when there is no item
}

class QuotaCache {
    fun get(accountId: String): Int?  // unchanged: null when there is no entry or it is one hour old or older
    fun put(accountId: String, quota: Int)  // unchanged
}
```

The usage handler is unchanged. It passes the current Billing_Period in UTC to `usageFor`, maps `AccountNotFound` to the 404 `NotFound` response, and returns the Usage_Summary with 200.

## Data Models

No change. The `usage-meters` and `accounts` items keep their attributes, and the fix stores nothing new.

The `usage-meters` entry in ARCHITECTURE.md gains one sentence: the first increment in a Billing_Period creates the item, and a Usage_Read reads a missing item as `metered` 0. UBIQUITOUS_LANGUAGE.md and `openapi.yaml` need no change, because `UsageSummary.metered` already allows 0.

## Correctness Properties

- _For any_ Account and Billing_Period with no `usage-meters` item, the Usage_Read SHALL report `metered` 0. **Validates: Requirements 2.1**
- _For any_ Account and Billing_Period with no `usage-meters` item, the Usage_Read SHALL report `quotaExceeded: false` and the Quota that `cache.get` or `loadAndCache` returns. **Validates: Requirements 2.2**
- _For any_ `usage-meters` item, the Usage_Read SHALL report its `metered` value and `quotaExceeded` equal to `metered > quota`. **Validates: Requirements 3.1**
- _For any_ two Usage_Reads of one Account less than one hour apart, the second SHALL take the Quota from the Quota_Cache with no `accounts` read, and a Usage_Read with no entry or an expired entry SHALL load the Quota from the `accounts` table. **Validates: Requirements 3.2**
- _For any_ account id with no `accounts` item, the Usage_Read SHALL throw `AccountNotFound` and the usage endpoint SHALL answer 404. **Validates: Requirements 3.3**

## Error Handling

A missing `usage-meters` item stops being an error. All other errors keep their current handling: `AccountNotFound` gives the 404 `NotFound` response, and a DynamoDB error gives the 500 `Problem`, as every unhandled error does. The fix adds no status code and no API path, and `openapi.yaml` is unchanged.

## Testing Strategy

- Unit test of `usageFor` with no `usage-meters` item: the Usage_Summary has `metered` 0, the Quota and `quotaExceeded: false`, both with a Quota_Cache entry and with an empty Quota_Cache, where the Quota comes from `loadAndCache` (Requirements 2.1, 2.2).
- Unit tests of `usageFor` with an item below the Quota, an item equal to it and an item above it: the Usage_Summary reports the item's `metered` and the matching `quotaExceeded` (Requirement 3.1).
- Unit test that two Usage_Reads less than one hour apart make one `accounts` read, and that a Usage_Read after the hour makes a second one (Requirement 3.2).
- Unit test that an unknown account id with no `usage-meters` item throws `AccountNotFound` (Requirement 3.3).
- Handler tests of `getUsage`: an Account with no `usage-meters` item gets 200 and a Usage_Summary with `metered` 0, which covers defect 1.1, and an unknown account id gets the 404 `NotFound` response (Requirements 2.1, 3.3).
