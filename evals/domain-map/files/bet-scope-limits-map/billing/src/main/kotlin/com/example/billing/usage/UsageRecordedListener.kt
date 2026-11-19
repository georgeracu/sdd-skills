package com.example.billing.usage

import org.springframework.kafka.annotation.KafkaListener

/** Adds each UsageRecorded event to the open Invoice. */
class UsageRecordedListener {
    @KafkaListener(topics = ["usage-recorded"])
    fun on(message: String): Unit = TODO("parse and add an invoice line")
}
