package com.example.metering.service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ThresholdValidatorTest : StringSpec({
    "accepts one to five distinct values and sorts them" {
        ThresholdValidator.validate(listOf(100, 50, 80)) shouldBe ThresholdResult.Valid(listOf(50, 80, 100))
    }
    "rejects an empty list" {
        ThresholdValidator.validate(emptyList()).shouldBeInstanceOf<ThresholdResult.Invalid>()
    }
    "rejects more than five values" {
        ThresholdValidator.validate(listOf(10, 20, 30, 40, 50, 60)).shouldBeInstanceOf<ThresholdResult.Invalid>()
    }
    "rejects zero and 101" {
        ThresholdValidator.validate(listOf(0)).shouldBeInstanceOf<ThresholdResult.Invalid>()
        ThresholdValidator.validate(listOf(101)).shouldBeInstanceOf<ThresholdResult.Invalid>()
    }
    "rejects duplicates" {
        ThresholdValidator.validate(listOf(80, 80)).shouldBeInstanceOf<ThresholdResult.Invalid>()
    }
    "accepts the boundaries 1 and 100" {
        ThresholdValidator.validate(listOf(1, 100)) shouldBe ThresholdResult.Valid(listOf(1, 100))
    }
})
