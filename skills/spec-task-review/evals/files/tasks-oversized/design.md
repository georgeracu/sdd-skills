# Design Document

## Overview

Quota alerts extend the usage metering service. The ingest Lambda already increments the Usage_Meter atomically; after the increment it calls a new AlertEvaluator, which publishes a `quota-threshold` message through the existing Notification_Service. Thresholds are stored on the `accounts` item and managed through a new `thresholds` sub-resource and a Dashboard settings panel.

## Components and Interfaces

### API contract (`openapi.yaml`)

- `ThresholdSet` schema: `thresholds`, an array of one to five unique integers from 1 to 100.
- `GET /accounts/{accountId}/thresholds` (`getThresholds`): returns `ThresholdSet`, 404 for an unknown Account.
- `PUT /accounts/{accountId}/thresholds` (`replaceThresholds`): body `ThresholdSet`, returns `ThresholdSet`, 400 for an invalid list, 404 for an unknown Account. Shaped like `changePlan`.

### Backend (`backend/`)

- `ThresholdValidator` (`service/`): rejects fewer than one or more than five values, duplicates, non-integers and values outside 1 to 100.
- `ThresholdService` (`service/`): reads and replaces the Threshold_Set, returning 80 and 100 when none is stored.
- `ThresholdsHandler` (`api/handlers/`): http4k handler for the two operations above, returning the standard `Problem` body on 400 and 404.
- `AlertEvaluator` (`service/`): called by the existing ingest Lambda (`service/UsageIngestService.kt`) after each atomic increment. Computes the usage percentage against the Plan Quota, finds Alert_Thresholds at or below it that have not fired, records each as fired, and for each newly fired threshold sends one Quota_Alert unless the Account `status` is `suspended`.
- `quota-threshold` template: a new template id in the notification renderer Lambda with payload fields `accountId`, `thresholdPercent`, `metered`, `quota`, `billingPeriod`.

### Frontend (`frontend/`)

- `ThresholdSettings` component (`src/components/ThresholdSettings.tsx`): form on the Account settings page that reads and replaces the Threshold_Set through the generated TypeScript client and types, showing server validation messages beside the field.

## Data Models

- `accounts` item: new optional `thresholds` list of integers. Absent means the default 80 and 100.
- `usage-meters` item: new `firedThresholds` number set, written by a conditional update so that exactly one caller wins each threshold. It lives on the per-period item and so resets with the Billing_Period.

## Testing Strategy

Unit tests per service and handler, Jest and React Testing Library for the component, backend integration tests over the ingest path, and Playwright for the settings flow against the mock server.
