package example.usage.service

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ThresholdSetTest {
    @Test fun `default set is 80 and 100`() = assertEquals(setOf(80, 100), ThresholdSet.DEFAULT.values)
    @Test fun `accepts one to five distinct integers`() = assertEquals(setOf(50), ThresholdSet.of(listOf(50)).getOrThrow().values)
    @Test fun `rejects an empty list`() = assertTrue(ThresholdSet.of(emptyList()).isFailure)
    @Test fun `rejects more than five`() = assertTrue(ThresholdSet.of(listOf(10, 20, 30, 40, 50, 60)).isFailure)
    @Test fun `rejects duplicates`() = assertTrue(ThresholdSet.of(listOf(50, 50)).isFailure)
    @Test fun `rejects out of range`() = assertTrue(ThresholdSet.of(listOf(0)).isFailure && ThresholdSet.of(listOf(101)).isFailure)
    @Test fun `rejects non-integers`() = assertTrue(ThresholdSet.of(listOf(12.5)).isFailure)
}
