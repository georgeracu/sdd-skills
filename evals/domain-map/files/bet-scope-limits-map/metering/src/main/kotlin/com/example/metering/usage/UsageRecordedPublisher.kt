package com.example.metering.usage

/** Publishes UsageRecorded to the usage-recorded topic after each increment. */
class UsageRecordedPublisher {
    fun publish(event: UsageRecorded): Unit = TODO("Kafka send to usage-recorded")
}

data class UsageRecorded(val accountId: String, val billingPeriod: String, val metered: Long)
