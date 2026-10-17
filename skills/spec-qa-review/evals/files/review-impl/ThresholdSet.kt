package example.usage.service

class ThresholdSet private constructor(val values: Set<Int>) {
    companion object {
        val DEFAULT = ThresholdSet(setOf(80, 100))

        fun of(raw: List<Any>): Result<ThresholdSet> {
            if (raw.isEmpty() || raw.size > 5) return Result.failure(IllegalArgumentException("one to five thresholds required"))
            val ints = raw.map { it as? Int ?: return Result.failure(IllegalArgumentException("thresholds must be integers")) }
            if (ints.toSet().size != ints.size) return Result.failure(IllegalArgumentException("thresholds must be distinct"))
            if (ints.any { it !in 1..100 }) return Result.failure(IllegalArgumentException("thresholds must be between 1 and 100"))
            return Result.success(ThresholdSet(ints.toSet()))
        }
    }
}
