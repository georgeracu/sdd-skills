package com.example.metering.usage

/** Reads the Usage_Meter from the usage-meters table. */
class UsageMeterRepository {
    fun currentPeriod(accountId: String): Long = TODO("DynamoDB read")
}
