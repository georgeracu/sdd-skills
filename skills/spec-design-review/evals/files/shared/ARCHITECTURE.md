# Architecture

Usage metering service for API customers. One repository, three deployable parts.

## Backend (`backend/`)

Kotlin 2.x on AWS Lambda behind API Gateway (HTTP API). One Lambda per resource, http4k handler pattern; every operation answers an unhandled error with a 500 `Problem`, which `openapi.yaml` does not list: `src/main/kotlin/<package>/api/handlers/` holds one handler per resource, which serves each of its routes, `service/` holds the domain services, `repository/` holds the DynamoDB access. Build and tests with `./gradlew build`.

DynamoDB tables:

- `accounts`: PK `accountId`. Holds `plan`, `ownerEmail`, `status` (`active` | `suspended`).
- `usage-meters`: PK `accountId`, SK `billingPeriod` (`YYYY-MM`). Holds `metered` (number), updated by atomic increments from the ingest Lambda. The ingest Lambda authenticates each call by loading the `accounts` item that owns the API key, before it increments. It answers 503 when an increment is throttled, and clients retry the call.

`usage-meters` is on on-demand capacity. Plan Quotas are read from the `accounts` table and held in the `Quota_Cache` for the life of the Lambda container, TTL one hour, because the usage endpoint is read on every Dashboard load and the `accounts` table is on provisioned capacity. Its read capacity is set at twice the peak eventually consistent load.

## Notification_Service (`backend/src/main/kotlin/<package>/notifications/`)

Publishes a message to the `notifications` SQS queue; a separate Lambda renders the named template and sends through SES. Callers pass a template id and a payload map, never a rendered email. Existing templates: `payment-failed`, `plan-changed`, `api-key-rotated`.

## Frontend (`frontend/`)

React and TypeScript Dashboard. API types are generated from `openapi.yaml`, never hand-written. Build with `npm run build`, tests with `npm test`.

## Contract

`openapi.yaml` at the repository root is the single contract. `scripts/generate-api-types.sh` regenerates Kotlin and TypeScript types from it; CI fails if the script produces a diff.

## End-to-end tests (`e2e/`)

Playwright against a mock server (`npm test`) and a smoke subset against production (`npm run test:e2e:smoke`).
