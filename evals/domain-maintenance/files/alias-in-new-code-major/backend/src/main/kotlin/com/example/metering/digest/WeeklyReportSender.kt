package com.example.metering.digest

import com.example.notifications.NotificationService

class WeeklyReportSender(private val notifications: NotificationService) {
    fun send(to: String, digest: UsageDigest) =
        notifications.send("usage-digest", to, mapOf("used" to digest.used.toString()))
}
