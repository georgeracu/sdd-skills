package com.example.metering.service

import com.example.metering.repository.AccountRepository
import com.example.metering.repository.NotificationService
import com.example.metering.repository.Plans
import com.example.metering.repository.UsageMeterRepository

class AlertEvaluator(
    private val accounts: AccountRepository,
    private val meters: UsageMeterRepository,
    private val notifications: NotificationService,
) {
    fun onIncrement(accountId: String, billingPeriod: String, metered: Long) {
        val account = accounts.get(accountId) ?: return
        if (account.status == "suspended") return

        val quota = Plans.quota(account.plan)
        val percent = metered * 100 / quota
        val fired = meters.firedThresholds(accountId, billingPeriod)

        account.thresholds
            .filter { it <= percent && it !in fired }
            .forEach { threshold ->
                if (meters.markFired(accountId, billingPeriod, threshold)) {
                    notifications.publish(
                        "quota-threshold",
                        mapOf(
                            "accountId" to accountId,
                            "threshold" to threshold,
                            "metered" to metered,
                            "quota" to quota,
                            "billingPeriod" to billingPeriod,
                        ),
                    )
                }
            }
    }
}
