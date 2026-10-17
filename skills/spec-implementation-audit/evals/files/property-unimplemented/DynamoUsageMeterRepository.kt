package com.example.metering.repository

import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.ReturnValue
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest

class DynamoUsageMeterRepository(
    private val dynamo: DynamoDbClient,
    private val table: String = "usage-meters",
) : UsageMeterRepository {

    override fun increment(accountId: String, billingPeriod: String, events: Long): Long =
        dynamo.updateItem(
            UpdateItemRequest.builder()
                .tableName(table)
                .key(key(accountId, billingPeriod))
                .updateExpression("ADD metered :n")
                .expressionAttributeValues(mapOf(":n" to AttributeValue.fromN(events.toString())))
                .returnValues(ReturnValue.UPDATED_NEW)
                .build(),
        ).attributes().getValue("metered").n().toLong()

    override fun metered(accountId: String, billingPeriod: String): Long =
        dynamo.getItem(
            GetItemRequest.builder()
                .tableName(table)
                .key(key(accountId, billingPeriod))
                .consistentRead(true)
                .build(),
        ).item()["metered"]?.n()?.toLong() ?: 0L

    private fun key(accountId: String, billingPeriod: String) = mapOf(
        "accountId" to AttributeValue.fromS(accountId),
        "billingPeriod" to AttributeValue.fromS(billingPeriod),
    )
}
