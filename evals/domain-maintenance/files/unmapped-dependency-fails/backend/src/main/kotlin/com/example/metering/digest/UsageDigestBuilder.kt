package com.example.metering.digest

import com.example.metering.quota.QuotaCache
import com.example.metering.usage.UsageMeterRepository
import com.example.billing.invoice.InvoiceRepository

/** Builds the Usage_Digest payload for one Account. */
class UsageDigestBuilder(
    private val usageMeters: UsageMeterRepository,
    private val quotas: QuotaCache,
    private val invoices: InvoiceRepository,
) {
    fun build(accountId: String): UsageDigest? {
        val used = usageMeters.currentPeriod(accountId)
        if (used == 0L) return null
        val quota = quotas.quotaFor(accountId)
        return UsageDigest(accountId, used, quota, used * 100 / quota)
    }
}

data class UsageDigest(val accountId: String, val used: Long, val quota: Long, val percentOfQuota: Long, val openInvoiceAmount: Long)
