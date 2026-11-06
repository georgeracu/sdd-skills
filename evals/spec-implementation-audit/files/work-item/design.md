# Design Document

## Overview

A `PUT /accounts/{accountId}/thresholds` handler validates and stores the Threshold_Set. `AlertEvaluator` runs after each ingest increment, skips suspended Accounts, and publishes `quota-threshold` through the Notification_Service for each Alert_Threshold reached that has not yet fired. Fired state lives on the `usage-meters` item and is written with a conditional write so that a concurrent increment cannot publish the same alert twice.

## Components and Interfaces

- `ThresholdValidator`: pure function from a list of integers to a valid sorted Threshold_Set or an error message.
- `ThresholdsHandler`: http4k handler for the PUT route; maps validation errors to 400 `invalid_thresholds` and unknown Accounts to 404 `account_not_found`.
- `IngestHandler`: http4k handler for the internal ingest route; increments the `usage-meters` item atomically, then calls `AlertEvaluator.onIncrement` with the new metered total.
- `AlertEvaluator`: `onIncrement(accountId, billingPeriod, metered)`.
- `UsageMeterRepository.markFired(accountId, billingPeriod, threshold)`: conditional add to `firedThresholds`; returns true only for the caller that added it.
- `DynamoUsageMeterRepository`: the DynamoDB implementation of `UsageMeterRepository`; `markFired` is the conditional write (`UpdateItem` with a condition expression) that backs Property 1.

## Data Models

- `accounts.thresholds`: set of one to five integers, default 80 and 100.
- `usage-meters.firedThresholds`: number set per (accountId, billingPeriod).

## Correctness Properties

### Property 1: At most one alert per threshold per period

For any Account, Billing_Period and Alert_Threshold, the Notification_Service receives at most one `quota-threshold` message, however many increments cross that threshold or run concurrently. _Validates: Requirements 2.3, 2.4_

### Property 2: Stored Threshold_Sets are always valid

For any request, a stored Threshold_Set holds one to five distinct integers between 1 and 100 in ascending order. _Validates: Requirements 1.1, 1.2_

### Property 3: Suspended Accounts never alert

For any increment on a suspended Account, no `quota-threshold` message is published. _Validates: Requirement 2.5_

## Error Handling

| Condition | Response |
|---|---|
| Invalid replacement | 400 `invalid_thresholds` |
| Unknown Account | 404 `account_not_found` |

## Testing Strategy

Kotest unit tests per component; the evaluator is tested against in-memory repositories.
