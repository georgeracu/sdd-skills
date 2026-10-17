package com.example.metering.service

sealed interface ThresholdResult {
    data class Valid(val thresholds: List<Int>) : ThresholdResult
    data class Invalid(val message: String) : ThresholdResult
}

object ThresholdValidator {
    fun validate(values: List<Int>): ThresholdResult = when {
        values.size !in 1..5 -> ThresholdResult.Invalid("thresholds must contain one to five values")
        values.any { it !in 1..100 } -> ThresholdResult.Invalid("each threshold must be between 1 and 100")
        values.toSet().size != values.size -> ThresholdResult.Invalid("thresholds must be distinct")
        else -> ThresholdResult.Valid(values.sorted())
    }
}
