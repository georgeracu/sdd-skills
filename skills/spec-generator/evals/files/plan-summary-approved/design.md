# Design Document: Plan Summary API

## Overview

Add an http4k endpoint that reads the existing Account and Plan repositories and returns a stable JSON summary. The endpoint follows the existing API Gateway and Lambda handler layout.

## Components and Interfaces

- **PlanSummaryHandler**: An http4k `HttpHandler` mounted at `GET /accounts/{accountId}/plan/summary`; validates the path, maps domain errors to Problem responses, and serializes the response.
- **PlanSummaryService**: Loads the Account and active Plan, then constructs a `PlanSummary` value with the requested fields.
- **PlanSummary**: A generated API type containing `accountId`, `planName`, `monthlyLimit`, and `billingPeriod`.

## Data Flow

1. API Gateway routes the request to `PlanSummaryHandler`.
2. The handler invokes `PlanSummaryService`.
3. The service reads the Account and active Plan repositories.
4. The handler serializes `PlanSummary` as JSON or returns the mapped Problem response.

## API Specifications

`GET /accounts/{accountId}/plan/summary`

- Path parameter: `accountId` (string)
- Response 200: `PlanSummary` JSON (`accountId`, `planName`, `monthlyLimit`, `billingPeriod`)
- Response 404: Problem with code `account-not-found`
- Response 409: Problem with code `plan-missing`

## Testing Strategy

- Unit tests for `PlanSummaryService` covering a valid Plan, an unknown Account, and a missing Plan.
- Handler integration tests covering JSON serialization and each Problem response.
- E2E coverage exercises the Dashboard request against a deployed API.

## Operational Notes

The endpoint is read-only and uses existing repositories. No new persistence or background job is required. Update the API contract and regenerate API types before implementation so the handler and tests compile against the same schema.
