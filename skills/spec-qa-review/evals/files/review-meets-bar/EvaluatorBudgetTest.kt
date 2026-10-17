package example.usage.service

import kotlin.system.measureNanoTime
import kotlin.test.Test
import kotlin.test.assertTrue

class EvaluatorBudgetTest {
    @Test fun `evaluating five thresholds stays inside the 5 ms ingest budget`() {
        val account = Account("acc_abcdefghij01", "starter", "active", ThresholdSet.of(listOf(10, 30, 50, 80, 100)).getOrThrow())
        val evaluator = QuotaAlertEvaluator(
            object : QuotaLookup { override fun quotaFor(plan: String) = 10_000L },
            object : UsageMeterRepository { override fun markFired(accountId: String, billingPeriod: String, threshold: Int) = true },
            object : NotificationService { override fun send(templateId: String, payload: Map<String, Any>) {} },
        )
        val worst = (1..1000).maxOf { measureNanoTime { evaluator.evaluate(account, UsageMeter(account.accountId, "2026-10", 10_000, emptySet())) } }
        assertTrue(worst < 5_000_000, "worst case $worst ns")
    }
}
