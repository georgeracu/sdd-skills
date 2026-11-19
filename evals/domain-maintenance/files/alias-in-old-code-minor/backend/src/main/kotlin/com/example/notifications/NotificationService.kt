package com.example.notifications

/** Publishes a SendNotification command to the notifications queue. */
class NotificationService {
    fun send(templateId: String, to: String, payload: Map<String, String>): Unit = TODO("SQS publish")
}
