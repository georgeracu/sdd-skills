package example.usage.service

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ThresholdSetTest {
    @Test fun `default set is 80 and 100`() = assertEquals(setOf(80, 100), ThresholdSet.DEFAULT.values)
    @Test fun `accepts one to five distinct integers`() = assertEquals(setOf(50), ThresholdSet.of(listOf(50)).getOrThrow().values)
    @Test fun `rejects an empty list`() = assertTrue(ThresholdSet.of(emptyList()).isFailure)
}
