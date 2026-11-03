package example.usage.service

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QuotaAlertEvaluatorTest {
    private val sent = mutableListOf<Pair<String, Map<String, Any>>>()
    private val fired = mutableSetOf<Int>()
    private val evaluator = QuotaAlertEvaluator(
        quotas = object : QuotaLookup { override fun quotaFor(plan: String) = if (plan == "scale") 2_000_000L else 10_000L },
        meters = object : UsageMeterRepository {
            override fun markFired(accountId: String, billingPeriod: String, threshold: Int) = fired.add(threshold)
        },
        notifications = object : NotificationService {
            override fun send(templateId: String, payload: Map<String, Any>) { sent += templateId to payload }
        },
    )
    private val account = Account("acc_abcdefghij01", "starter", "active", ThresholdSet.DEFAULT)

    private fun meter(metered: Long, firedAlready: Set<Int> = emptySet()) =
        UsageMeter(account.accountId, "2026-10", metered, firedAlready)

    @Test fun `first crossing sends one quota-threshold alert with the full payload`() {
        evaluator.evaluate(account, meter(8_000))
        assertEquals(1, sent.size)
        assertEquals("quota-threshold", sent[0].first)
        assertEquals(mapOf("accountId" to account.accountId, "threshold" to 80, "metered" to 8_000L, "quota" to 10_000L, "billingPeriod" to "2026-10"), sent[0].second)
    }

    @Test fun `an already fired threshold is not sent again`() {
        evaluator.evaluate(account, meter(8_500, setOf(80)))
        assertTrue(sent.isEmpty())
    }
}
