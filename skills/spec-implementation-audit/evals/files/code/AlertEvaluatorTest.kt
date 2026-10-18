package com.example.metering.service

import com.example.metering.repository.Account
import com.example.metering.repository.AccountRepository
import com.example.metering.repository.NotificationService
import com.example.metering.repository.UsageMeterRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AlertEvaluatorTest : StringSpec({
    class Fixture(status: String = "active") {
        val account = Account("acc_aaaaaaaaaaaa", "starter", "owner@example.test", status)
        val fired = mutableSetOf<Pair<String, Int>>()
        val sent = mutableListOf<Pair<String, Map<String, Any>>>()
        val evaluator = AlertEvaluator(
            object : AccountRepository {
                override fun get(accountId: String) = account
                override fun save(account: Account) = Unit
            },
            object : UsageMeterRepository {
                override fun increment(accountId: String, billingPeriod: String, events: Long) = events
                override fun firedThresholds(accountId: String, billingPeriod: String) =
                    fired.filter { it.first == billingPeriod }.map { it.second }.toSet()
                override fun markFired(accountId: String, billingPeriod: String, threshold: Int) =
                    fired.add(billingPeriod to threshold)
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
    "sends one quota-threshold alert with the full payload at 80 percent" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 8_000)
        f.sent.single().first shouldBe "quota-threshold"
        f.sent.single().second["threshold"] shouldBe 80
        f.sent.single().second["quota"] shouldBe 10_000L
        f.sent.single().second["billingPeriod"] shouldBe "2026-10"
    }
    "does not repeat an alert that already fired this period" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 8_000)
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 8_001)
        f.sent.size shouldBe 1
    }
    "alerts again for the same threshold in the next billing period" {
        val f = Fixture()
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-10", 8_000)
        f.evaluator.onIncrement("acc_aaaaaaaaaaaa", "2026-11", 8_000)
        f.sent.size shouldBe 2
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
