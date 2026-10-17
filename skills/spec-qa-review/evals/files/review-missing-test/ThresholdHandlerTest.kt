package example.usage.api.handlers

import example.usage.repository.DynamoAccountRepository
import example.usage.testsupport.DynamoLocal
import org.http4k.core.Method.GET
import org.http4k.core.Method.PUT
import org.http4k.core.Request
import org.http4k.core.Status
import kotlin.test.Test
import kotlin.test.assertEquals

class ThresholdHandlerTest {
    private val accounts = DynamoAccountRepository(DynamoLocal.client())
    private val handler = ThresholdHandler(accounts)

    @Test fun `GET returns the default set for an account with none stored`() {
        val response = handler.get(Request(GET, "/accounts/acc_abcdefghij01/thresholds"))
        assertEquals(Status.OK, response.status)
        assertEquals("""{"thresholds":[80,100]}""", response.bodyString())
    }

    @Test fun `PUT replaces the set and GET reads it back from DynamoDB`() {
        handler.replace(Request(PUT, "/accounts/acc_abcdefghij01/thresholds").body("""{"thresholds":[50,90]}"""))
        assertEquals("""{"thresholds":[50,90]}""", handler.get(Request(GET, "/accounts/acc_abcdefghij01/thresholds")).bodyString())
    }

    @Test fun `PUT with a duplicate returns 400 threshold-set-invalid`() {
        val response = handler.replace(Request(PUT, "/accounts/acc_abcdefghij01/thresholds").body("""{"thresholds":[50,50]}"""))
        assertEquals(Status.BAD_REQUEST, response.status)
    }

    @Test fun `unknown account returns 404`() {
        assertEquals(Status.NOT_FOUND, handler.get(Request(GET, "/accounts/acc_zzzzzzzzzzzz/thresholds")).status)
    }
}
