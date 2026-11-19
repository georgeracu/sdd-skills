package com.example.metering.digest

import com.example.notifications.NotificationService

/** Sends one Usage_Digest per active Account every Monday. */
class UsageDigestJob(private val notifications: NotificationService) {
    fun run(): Unit = TODO("build and send")
}
