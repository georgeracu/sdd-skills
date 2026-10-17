# Tasks: Quota Alerts

Each task traces to acceptance criteria through `_Requirements: N.N_` and to design elements through `_Design:_`. Unit tests are written within each task; integration and end-to-end tests come last.

## Task list

- [ ] **1. Add the threshold data model and repository methods**
  - In `backend/src/main/kotlin/<package>/repository/` add an optional `thresholds` integer list to the `accounts` item model and a `firedThresholds` number set to the `usage-meters` item model (both edits to existing files)
  - Add `getThresholds`, `replaceThresholds` and `markFired(accountId, billingPeriod, threshold)`; `markFired` uses a conditional update and returns true only for the caller that added the value
  - An absent `thresholds` attribute reads as `[80, 100]`
  - Unit tests: default read, replace then read, `markFired` returns true once and false on repeat, a new Billing_Period starts empty
  - _Requirements: 1.1, 2.3_
  - _Design: accounts.thresholds, usage-meters.firedThresholds_

- [ ] **2. Build the ThresholdSettings component**
  - Create `frontend/src/components/ThresholdSettings.tsx` (new file) and mount it on the Account settings page
  - Import the generated `ThresholdSet` type from the generated TypeScript API types and call `GET` and `PUT /accounts/{accountId}/thresholds` through the generated client
  - On a 400, show the returned `Problem.message` beside the threshold field and keep the entered values
  - Jest and React Testing Library tests: renders the stored set, submits a replacement, shows the validation message on a 400
  - _Requirements: 5.1, 5.2_
  - _Design: ThresholdSettings component_

- [ ] **3. Extend the API contract and regenerate types**
  - Edit `openapi.yaml`: add the `ThresholdSet` schema (`thresholds`, array of integers, `minItems` 1, `maxItems` 5, `uniqueItems` true, each from 1 to 100)
  - Add `GET` (`getThresholds`) and `PUT` (`replaceThresholds`) on `/accounts/{accountId}/thresholds`, following `changePlan`: 200 returns `ThresholdSet`, 400 uses `BadRequest`, 404 uses `NotFound`
  - Run `scripts/generate-api-types.sh` and commit the regenerated Kotlin and TypeScript types
  - _Requirements: 4.1, 4.2_
  - _Design: ThresholdSet schema, GET and PUT /accounts/{accountId}/thresholds_

- [ ] **4. Implement ThresholdValidator and ThresholdService**
  - Create `service/ThresholdValidator.kt` and `service/ThresholdService.kt` (new files)
  - The validator rejects an empty list, more than five values, duplicates, non-integers and values below 1 or above 100, returning the first failing rule as a message
  - `ThresholdService` reads through the repository and replaces only after validation passes
  - Unit tests: one per rejection rule, a valid five-value list, a failed replace leaves the stored set unchanged
  - _Requirements: 1.2, 1.3_
  - _Design: ThresholdValidator, ThresholdService_

- [ ] **5. Implement ThresholdsHandler**
  - Create `api/handlers/ThresholdsHandler.kt` (new file) with `GET` and `PUT` on `/accounts/{accountId}/thresholds` using the generated types
  - Return 400 with the standard `Problem` body carrying the validator message, and 404 with the standard `Problem` body for an unknown Account
  - Register the route in the accounts Lambda router
  - Handler unit tests: 200 read, 200 replace, 400 for a duplicate list, 404 for an unknown account id
  - _Requirements: 1.3, 4.1, 4.2_
  - _Design: ThresholdsHandler_

- [ ] **6. Checkpoint: backend build**
  - Run `./gradlew build` from `backend/` and `scripts/generate-api-types.sh`; fix failures and confirm the generator produces no diff
  - _Requirements: 4.1_

- [ ] **7. Add the quota-threshold notification template**
  - In the notification renderer Lambda, add the template id `quota-threshold` (new template file beside `payment-failed`, `plan-changed` and `api-key-rotated`) rendering `accountId`, `thresholdPercent`, `metered`, `quota` and `billingPeriod`
  - Unit test: the rendered email contains all five values and rejects a payload missing any of them
  - _Requirements: 3.1_
  - _Design: quota-threshold template_

- [ ] **8. Implement AlertEvaluator and hook it into ingest**
  - Create `service/AlertEvaluator.kt` (new file) and call it from `service/UsageIngestService.kt` immediately after the atomic increment
  - Compute the usage percentage as `metered * 100 / quota` using the Plan Quota; for each stored Alert_Threshold at or below it, call `markFired`; for each threshold it returns true for, send one `quota-threshold` message through Notification_Service
  - Skip sending for an Account whose `status` in the `accounts` item is `suspended`
  - Unit tests: crossing 80 sends one alert, a repeat increment sends none, crossing 80 and 100 in one increment sends two, a suspended Account sends none
  - _Requirements: 2.1, 2.2, 2.3, 2.4_
  - _Design: AlertEvaluator_

- [ ] **9. Backend integration tests for the alert flow**
  - Create `backend/src/test/kotlin/<package>/QuotaAlertIntegrationTest.kt` (new file) against local DynamoDB and a fake Notification_Service
  - Cases: an increment crossing 80 percent sends one `quota-threshold` message and a second increment sends none; a suspended Account sends none; a replaced Threshold_Set is the one evaluated on the next increment
  - Run `./gradlew build` and confirm all tests pass
  - _Requirements: 1.2, 2.2, 2.3, 2.4, 4.1_

- [ ] **10. Playwright end-to-end test for the settings flow**
  - Create `e2e/tests/threshold-settings.spec.ts` (new file) against the mock server, adding the `thresholds` routes to the mock
  - Scenarios: the Account_Owner opens settings and sees 80 and 100, replaces them with 50, 90 and 100 and sees them saved, enters a duplicate and sees the validation message with the values kept
  - Run with `npm test` in `e2e/`
  - _Requirements: 5.1, 5.2_

- [ ] **11. Update documentation**
  - Create `docs/quota-alerts.md` (new file) describing the default thresholds, the `thresholds` sub-resource with request and response examples, and the alert email fields
  - Add a link to it from the README documentation index
  - _Requirements: 1.1, 3.1, 4.1_

- [ ] **12. Final validation**
  - Run `./gradlew build` in `backend/`, `npm run build` and `npm test` in `frontend/`, and `npm test` in `e2e/`; all must pass with no generated-types diff
  - Confirm every acceptance criterion in `requirements.md` is traced by a completed task
  - _Requirements: 1.1, 1.2, 1.3, 2.1, 2.2, 2.3, 2.4, 3.1, 4.1, 4.2, 5.1, 5.2_
