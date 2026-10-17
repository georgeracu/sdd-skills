package example.usage.repository

import example.usage.testsupport.DynamoLocal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FiredStateRepositoryTest {
    private val meters = DynamoUsageMeterRepository(DynamoLocal.client())

    @Test fun `markFired succeeds once per threshold and billing period`() {
        assertTrue(meters.markFired("acc_abcdefghij01", "2026-10", 80))
        assertFalse(meters.markFired("acc_abcdefghij01", "2026-10", 80))
    }

    @Test fun `fired state resets with the billing period`() {
        meters.markFired("acc_abcdefghij01", "2026-10", 80)
        assertTrue(meters.markFired("acc_abcdefghij01", "2026-11", 80))
        assertEquals(setOf(80), meters.find("acc_abcdefghij01", "2026-11")!!.fired)
    }
}
