package com.example.metering.repository

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.net.URI
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition
import software.amazon.awssdk.services.dynamodb.model.BillingMode
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement
import software.amazon.awssdk.services.dynamodb.model.KeyType
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType

class DynamoUsageMeterRepositoryTest : StringSpec({
    val container = GenericContainer(DockerImageName.parse("amazon/dynamodb-local:2.5.2")).withExposedPorts(8000)
    container.start()
    val client = DynamoDbClient.builder()
        .endpointOverride(URI.create("http://${container.host}:${container.getMappedPort(8000)}"))
        .region(Region.EU_WEST_1)
        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("test", "test")))
        .build()
    client.createTable(
        CreateTableRequest.builder()
            .tableName("usage-meters")
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .attributeDefinitions(
                AttributeDefinition.builder().attributeName("accountId").attributeType(ScalarAttributeType.S).build(),
                AttributeDefinition.builder().attributeName("billingPeriod").attributeType(ScalarAttributeType.S).build(),
            )
            .keySchema(
                KeySchemaElement.builder().attributeName("accountId").keyType(KeyType.HASH).build(),
                KeySchemaElement.builder().attributeName("billingPeriod").keyType(KeyType.RANGE).build(),
            )
            .build(),
    )
    val repository = DynamoUsageMeterRepository(client)

    afterSpec { container.stop() }

    "increment returns the new metered total" {
        repository.increment("acc_aaaaaaaaaaaa", "2026-10", 100) shouldBe 100L
        repository.increment("acc_aaaaaaaaaaaa", "2026-10", 50) shouldBe 150L
    }
    "metered is zero for a new period" {
        repository.metered("acc_aaaaaaaaaaaa", "2026-11") shouldBe 0L
    }
})
