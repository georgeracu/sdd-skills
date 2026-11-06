package com.example.metering.repository

import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException
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

    override fun firedThresholds(accountId: String, billingPeriod: String): Set<Int> =
        dynamo.getItem(
            GetItemRequest.builder()
                .tableName(table)
                .key(key(accountId, billingPeriod))
                .consistentRead(true)
                .build(),
        ).item()["firedThresholds"]?.ns()?.map { it.toInt() }?.toSet() ?: emptySet()

    override fun markFired(accountId: String, billingPeriod: String, threshold: Int): Boolean =
        try {
            dynamo.updateItem(
                UpdateItemRequest.builder()
                    .tableName(table)
                    .key(key(accountId, billingPeriod))
                    .updateExpression("ADD firedThresholds :t")
                    .conditionExpression("attribute_not_exists(firedThresholds) OR NOT contains(firedThresholds, :n)")
                    .expressionAttributeValues(
                        mapOf(
                            ":t" to AttributeValue.fromNs(listOf(threshold.toString())),
                            ":n" to AttributeValue.fromN(threshold.toString()),
                        ),
                    )
                    .build(),
            )
            true
        } catch (e: ConditionalCheckFailedException) {
            false
        }

    private fun key(accountId: String, billingPeriod: String) = mapOf(
        "accountId" to AttributeValue.fromS(accountId),
        "billingPeriod" to AttributeValue.fromS(billingPeriod),
    )
}
