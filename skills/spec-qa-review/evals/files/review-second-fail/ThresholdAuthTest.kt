package example.usage.api.handlers

import example.usage.testsupport.DynamoLocal
import example.usage.testsupport.withAuthoriser
import org.http4k.core.Method.PUT
import org.http4k.core.Request
import org.http4k.core.Status
import kotlin.test.Test
import kotlin.test.assertEquals

class ThresholdAuthTest {
    private val accounts = example.usage.repository.DynamoAccountRepository(DynamoLocal.client())
    private val app = withAuthoriser(ThresholdHandler(accounts))

    @Test fun `an unauthenticated PUT is rejected and leaves the stored set unchanged`() {
        val response = app(Request(PUT, "/accounts/acc_abcdefghij01/thresholds").body("""{"thresholds":[1]}"""))
        assertEquals(Status.UNAUTHORIZED, response.status)
        assertEquals(setOf(80, 100), accounts.find("acc_abcdefghij01")!!.thresholds.values)
    }

    @Test fun `a caller for another account is forbidden`() {
        val response = app(Request(PUT, "/accounts/acc_abcdefghij01/thresholds").header("x-test-account", "acc_other0000000").body("""{"thresholds":[1]}"""))
        assertEquals(Status.FORBIDDEN, response.status)
    }

    @Test fun `the quota-threshold payload carries no owner email`() {
        val payload = example.usage.testsupport.lastQuotaAlertPayload()
        assertEquals(setOf("accountId", "threshold", "metered", "quota", "billingPeriod"), payload.keys)
    }
}
