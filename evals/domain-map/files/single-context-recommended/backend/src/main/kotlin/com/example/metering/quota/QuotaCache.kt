package com.example.metering.quota

/** Holds each Account's Quota for the life of the Lambda container. */
class QuotaCache {
    fun quotaFor(accountId: String): Long = TODO("read accounts table")
}
