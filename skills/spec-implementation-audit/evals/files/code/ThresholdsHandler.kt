package com.example.metering.api.handlers

import com.example.metering.repository.AccountRepository
import com.example.metering.service.ThresholdResult
import com.example.metering.service.ThresholdValidator
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.Jackson
import org.http4k.routing.path

data class ThresholdReplacement(val thresholds: List<Int>)

class ThresholdsHandler(private val accounts: AccountRepository) : HttpHandler {
    override fun invoke(request: Request): Response {
        val account = request.path("accountId")?.let(accounts::get)
            ?: return problem(Status.NOT_FOUND, "account_not_found", "No such account")

        val body = runCatching { Jackson.asA(request.bodyString(), ThresholdReplacement::class) }
            .getOrElse { return problem(Status.BAD_REQUEST, "invalid_thresholds", "thresholds must be a list of integers") }

        return when (val result = ThresholdValidator.validate(body.thresholds)) {
            is ThresholdResult.Invalid -> problem(Status.BAD_REQUEST, "invalid_thresholds", result.message)
            is ThresholdResult.Valid -> {
                val updated = account.copy(thresholds = result.thresholds)
                accounts.save(updated)
                Response(Status.OK).body(Jackson.asFormatString(updated))
            }
        }
    }

    private fun problem(status: Status, code: String, message: String) =
        Response(status).body(Jackson.asFormatString(mapOf("code" to code, "message" to message)))
}
