package com.example.metering.repository

data class Account(
    val accountId: String,
    val plan: String,
    val ownerEmail: String,
    val status: String,
    val thresholds: List<Int> = listOf(80, 100),
)

object Plans {
    fun quota(plan: String): Long = when (plan) {
        "starter" -> 10_000
        "team" -> 250_000
        "scale" -> 2_000_000
        else -> error("unknown plan $plan")
    }
}

interface AccountRepository {
    fun get(accountId: String): Account?
    fun save(account: Account)
}

interface UsageMeterRepository {
    /** Atomic add to the Usage_Meter for the period; returns the new metered total. */
    fun increment(accountId: String, billingPeriod: String, events: Long): Long

    fun metered(accountId: String, billingPeriod: String): Long
}

interface NotificationService {
    fun publish(templateId: String, payload: Map<String, Any>)
}
