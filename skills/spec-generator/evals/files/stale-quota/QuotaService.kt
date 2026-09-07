package io.example.metering.service

import io.example.metering.repository.AccountRepository
import io.example.metering.repository.UsageMeterRepository
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneOffset
import java.util.concurrent.ConcurrentHashMap

data class UsageSummary(
    val accountId: String,
    val billingPeriod: String,
    val metered: Long,
    val quota: Long,
    val quotaExceeded: Boolean,
)

class QuotaService(
    private val accounts: AccountRepository,
    private val meters: UsageMeterRepository,
    private val clock: Clock = Clock.systemUTC(),
    private val cacheTtl: Duration = Duration.ofHours(1),
) {
    private data class CachedQuota(val quota: Long, val expiresAt: Instant)

    // Quota_Cache: accounts is on provisioned capacity and usage is read on every Dashboard load.
    private val quotaCache = ConcurrentHashMap<String, CachedQuota>()

    fun usageFor(accountId: String): UsageSummary {
        val period = YearMonth.now(clock.withZone(ZoneOffset.UTC)).toString()
        val metered = meters.metered(accountId, period)
        val quota = quotaFor(accountId)
        return UsageSummary(
            accountId = accountId,
            billingPeriod = period,
            metered = metered,
            quota = quota,
            quotaExceeded = metered > quota,
        )
    }

    fun quotaFor(accountId: String): Long {
        val now = clock.instant()
        val cached = quotaCache[accountId]
        if (cached != null && cached.expiresAt.isAfter(now)) {
            return cached.quota
        }
        val account = accounts.find(accountId) ?: throw AccountNotFound(accountId)
        val quota = Plan.valueOf(account.plan.uppercase()).quota
        quotaCache[accountId] = CachedQuota(quota, now.plus(cacheTtl))
        return quota
    }
}

enum class Plan(val quota: Long) {
    STARTER(10_000),
    TEAM(250_000),
    SCALE(2_000_000),
}

class AccountNotFound(accountId: String) : RuntimeException("No such account: $accountId")
