package com.example.metering.api.handlers

import com.example.metering.repository.AccountRepository
import com.example.metering.repository.UsageMeterRepository
import com.example.metering.service.AlertEvaluator
import java.time.Clock
import java.time.YearMonth
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.Jackson
import org.http4k.routing.path

data class UsageIncrement(val events: Long)

/** Internal route for the ingest Lambda; not part of the public contract in openapi.yaml. */
class IngestHandler(
    private val accounts: AccountRepository,
    private val meters: UsageMeterRepository,
    private val evaluator: AlertEvaluator,
    private val clock: Clock = Clock.systemUTC(),
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val accountId = request.path("accountId")
        if (accountId == null || accounts.get(accountId) == null) {
            return problem(Status.NOT_FOUND, "account_not_found", "No such account")
        }

        val body = runCatching { Jackson.asA(request.bodyString(), UsageIncrement::class) }
            .getOrNull()
            ?.takeIf { it.events > 0 }
            ?: return problem(Status.BAD_REQUEST, "invalid_increment", "events must be a positive integer")

        val billingPeriod = YearMonth.now(clock).toString()
        val newMeteredTotal = meters.increment(accountId, billingPeriod, body.events)
        evaluator.onIncrement(accountId, billingPeriod, newMeteredTotal)

        return Response(Status.OK).body(Jackson.asFormatString(mapOf("metered" to newMeteredTotal)))
    }

    private fun problem(status: Status, code: String, message: String) =
        Response(status).body(Jackson.asFormatString(mapOf("code" to code, "message" to message)))
}
