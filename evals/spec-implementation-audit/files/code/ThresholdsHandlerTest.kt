package com.example.metering.api.handlers

import com.example.metering.repository.Account
import com.example.metering.repository.AccountRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.routing.RoutedRequest
import org.http4k.routing.UriTemplate

class ThresholdsHandlerTest : StringSpec({
    val store = mutableMapOf("acc_aaaaaaaaaaaa" to Account("acc_aaaaaaaaaaaa", "team", "owner@example.test", "active"))
    val repo = object : AccountRepository {
        override fun get(accountId: String) = store[accountId]
        override fun save(account: Account) { store[account.accountId] = account }
    }
    val handler = ThresholdsHandler(repo)

    fun put(id: String, body: String) = handler(
        RoutedRequest(Request(Method.PUT, "/accounts/$id/thresholds").body(body), UriTemplate.from("/accounts/{accountId}/thresholds")),
    )

    "stores a valid set in ascending order" {
        val response = put("acc_aaaaaaaaaaaa", """{"thresholds":[90,50]}""")
        response.status shouldBe Status.OK
        store.getValue("acc_aaaaaaaaaaaa").thresholds shouldBe listOf(50, 90)
    }
    "rejects an invalid set with invalid_thresholds and leaves the stored set alone" {
        val response = put("acc_aaaaaaaaaaaa", """{"thresholds":[0]}""")
        response.status shouldBe Status.BAD_REQUEST
        response.bodyString() shouldContain "invalid_thresholds"
        store.getValue("acc_aaaaaaaaaaaa").thresholds shouldBe listOf(50, 90)
    }
    "rejects a non-integer value" {
        put("acc_aaaaaaaaaaaa", """{"thresholds":["x"]}""").status shouldBe Status.BAD_REQUEST
    }
    "returns 404 account_not_found for an unknown account" {
        val response = put("acc_zzzzzzzzzzzz", """{"thresholds":[80]}""")
        response.status shouldBe Status.NOT_FOUND
        response.bodyString() shouldContain "account_not_found"
    }
})
