package com.example.metering.digest

import com.example.metering.account.AccountRepository
import com.example.notifications.NotificationService

/** Sends one Usage_Digest per active Account every Monday at 08:00 UTC. */
class UsageDigestJob(
    private val accounts: AccountRepository,
    private val builder: UsageDigestBuilder,
    private val notifications: NotificationService,
) {
    fun run() {
        accounts.active().forEach { account ->
            val digest = builder.build(account.id) ?: return@forEach
            notifications.send("usage-digest", account.ownerEmail, digest.toPayload())
        }
    }
}

private fun UsageDigest.toPayload() = mapOf(
    "used" to used.toString(),
    "quota" to quota.toString(),
    "percentOfQuota" to percentOfQuota.toString(),
)
