package example.usage.service

data class Account(val accountId: String, val plan: String, val status: String, val thresholds: ThresholdSet)

data class UsageMeter(val accountId: String, val billingPeriod: String, val metered: Long, val fired: Set<Int>)

interface QuotaLookup { fun quotaFor(plan: String): Long }

interface UsageMeterRepository {
    fun markFired(accountId: String, billingPeriod: String, threshold: Int): Boolean
}

interface NotificationService { fun send(templateId: String, payload: Map<String, Any>) }

class QuotaAlertEvaluator(
    private val quotas: QuotaLookup,
    private val meters: UsageMeterRepository,
    private val notifications: NotificationService,
) {
    fun evaluate(account: Account, meter: UsageMeter) {
        if (account.status == "suspended") return
        val quota = quotas.quotaFor(account.plan)
        val percent = meter.metered * 100 / quota
        account.thresholds.values.sorted()
            .filter { it <= percent && it !in meter.fired }
            .forEach { threshold ->
                if (!meters.markFired(meter.accountId, meter.billingPeriod, threshold)) return@forEach
                notifications.send(
                    "quota-threshold",
                    mapOf(
                        "accountId" to meter.accountId,
                        "threshold" to threshold,
                        "metered" to meter.metered,
                        "quota" to quota,
                        "billingPeriod" to meter.billingPeriod,
                    ),
                )
            }
    }
}
