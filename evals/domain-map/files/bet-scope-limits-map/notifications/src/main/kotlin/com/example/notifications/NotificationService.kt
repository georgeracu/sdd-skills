package com.example.notifications

/** Renders a named template and sends it through SES. */
class NotificationService {
    fun send(templateId: String, to: String, payload: Map<String, String>): Unit = TODO("SES")
}
