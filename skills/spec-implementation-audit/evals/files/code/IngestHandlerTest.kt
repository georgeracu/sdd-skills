package com.example.metering.api.handlers

import com.example.metering.repository.Account
import com.example.metering.repository.AccountRepository
import com.example.metering.repository.NotificationService
import com.example.metering.repository.UsageMeterRepository
import com.example.metering.service.AlertEvaluator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.routing.RoutedRequest
import org.http4k.routing.UriTemplate

class IngestHandlerTest : StringSpec({
    val accounts = object : AccountRepository {
        private val store = mapOf("acc_aaaaaaaaaaaa" to Account("acc_aaaaaaaaaaaa", "starter", "owner@example.test", "active"))
        override fun get(accountId: String) = store[accountId]
        override fun save(account: Account) = Unit
    }
    var total = 7_900L
    val fired = mutableSetOf<Int>()
    val sent = mutableListOf<Map<String, Any>>()
    val meters = object : UsageMeterRepository {
        override fun increment(accountId: String, billingPeriod: String, events: Long): Long { total += events; return total }
        override fun firedThresholds(accountId: String, billingPeriod: String): Set<Int> = fired.toSet()
        override fun markFired(accountId: String, billingPeriod: String, threshold: Int) = fired.add(threshold)
    }
    val notifications = object : NotificationService {
        override fun publish(templateId: String, payload: Map<String, Any>) { sent += payload }
    }
    val clock = Clock.fixed(Instant.parse("2026-10-17T09:00:00Z"), ZoneOffset.UTC)
    val handler = IngestHandler(accounts, meters, AlertEvaluator(accounts, meters, notifications), clock)

    fun post(id: String, body: String) = handler(
        RoutedRequest(Request(Method.POST, "/ingest/accounts/$id/increments").body(body), UriTemplate.from("/ingest/accounts/{accountId}/increments")),
    )

    "increments the meter then calls the evaluator with the new total" {
        val response = post("acc_aaaaaaaaaaaa", """{"events":100}""")
        response.status shouldBe Status.OK
        response.bodyString() shouldContain "8000"
        sent.single()["metered"] shouldBe 8_000L
        sent.single()["billingPeriod"] shouldBe "2026-10"
    }
    "returns 404 account_not_found for an unknown account" {
        val response = post("acc_zzzzzzzzzzzz", """{"events":1}""")
        response.status shouldBe Status.NOT_FOUND
        response.bodyString() shouldContain "account_not_found"
        total shouldBe 8_000L
    }
})
