package example.usage.api.handlers

import example.usage.repository.AccountRepository
import example.usage.service.ThresholdSet
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status

class ThresholdHandler(private val accounts: AccountRepository) {
    fun get(request: Request): Response {
        val account = accounts.find(request.accountId()) ?: return problem(Status.NOT_FOUND, "account-not-found")
        return json(Status.OK, mapOf("thresholds" to account.thresholds.values.sorted()))
    }

    fun replace(request: Request): Response {
        val account = accounts.find(request.accountId()) ?: return problem(Status.NOT_FOUND, "account-not-found")
        val raw = parseThresholds(request.bodyString())
            ?: return problem(Status.BAD_REQUEST, "threshold-set-invalid")
        val set = ThresholdSet.of(raw).getOrElse { return problem(Status.BAD_REQUEST, "threshold-set-invalid") }
        accounts.save(account.copy(thresholds = set))
        return json(Status.OK, mapOf("thresholds" to set.values.sorted()))
    }
}
