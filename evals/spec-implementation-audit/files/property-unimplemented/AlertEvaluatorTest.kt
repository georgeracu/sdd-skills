package com.example.metering.service

import com.example.metering.repository.Account
import com.example.metering.repository.AccountRepository
import com.example.metering.repository.NotificationService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AlertEvaluatorTest : StringSpec({
    class Fixture(status: String = "active") {
        val account = Account("acc_aaaaaaaaaaaa", "starter", "owner@example.test", status)
        val sent = mutableListOf<Pair<String, Map<String, Any>>>()
        val evaluator = AlertEvaluator(
            object : AccountRepository {
                override fun get(accountId: String) = account
                override fun save(account: Account) = Unit
            },
            object : NotificationService {
                override fun publish(templateId: String, payload: Map<String, Any>) { sent += templateId to payload }
            },
        )
    }

    "sends nothing below the lowest threshold" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 7_999)
        f.sent.size shouldBe 0
    }
    "sends a quota-threshold alert with the full payload at 80 percent" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 8_000)
        f.sent.single().first shouldBe "quota-threshold"
        f.sent.single().second["threshold"] shouldBe 80
        f.sent.single().second["billingPeriod"] shouldBe "2026-10"
    }
    "sends both alerts when one increment crosses 80 and 100" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 10_000)
        f.sent.map { it.second["threshold"] } shouldBe listOf(80, 100)
    }
    "never alerts a suspended account" {
        val f = Fixture(status = "suspended")
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 10_000)
        f.sent.size shouldBe 0
    }
})
