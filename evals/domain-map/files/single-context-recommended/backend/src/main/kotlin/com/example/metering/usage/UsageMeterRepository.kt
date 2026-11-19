package com.example.metering.usage

/** Reads and increments the Usage_Meter in the usage-meters table. */
class UsageMeterRepository {
    fun increment(accountId: String): Long = TODO("DynamoDB update")
    fun currentPeriod(accountId: String): Long = TODO("DynamoDB read")
}
