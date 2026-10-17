# Implementation Plan: Threshold Alerts

## Tasks

- [x] 1. Extend `openapi.yaml` with `PUT /accounts/{accountId}/thresholds` and regenerate API types
  - Add `ThresholdReplacement` and the `thresholds` property on `Account`
  - Run `scripts/generate-api-types.sh` and commit the result
  - _Requirements: 1.1, 1.2, 1.3_

- [x] 2. Implement `ThresholdValidator` with unit tests
  - _Requirements: 1.1, 1.2_
  - _Design: Property 2_

- [x] 3. Implement `ThresholdsHandler` for the PUT route with unit tests
  - _Requirements: 1.1, 1.2, 1.3_

- [x] 4. Implement `AlertEvaluator` with fired-state recording through `DynamoUsageMeterRepository` (conditional write) and the suspended-Account check, wire it into `IngestHandler`, with unit tests
  - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_
  - _Design: Property 1, Property 3_

- [x] 5. Document the `quota-threshold` template and the new tables' attributes in `ARCHITECTURE.md`
  - _Requirements: 2.2, 2.3_

- [ ]* 6. Add a property-based test for `ThresholdValidator`
  - _Design: Property 2_
