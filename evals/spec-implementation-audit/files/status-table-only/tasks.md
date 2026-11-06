# Implementation Plan: Threshold Alerts

## Tasks

- [ ] 1. Extend `openapi.yaml` with `PUT /accounts/{accountId}/thresholds` and regenerate API types
  - Add `ThresholdReplacement` and the `thresholds` property on `Account`
  - Run `scripts/generate-api-types.sh` and commit the result
  - _Requirements: 1.1, 1.2, 1.3_

- [ ] 2. Implement `ThresholdValidator` with unit tests
  - _Requirements: 1.1, 1.2_
  - _Design: Property 2_

- [ ] 3. Implement `ThresholdsHandler` for the PUT route with unit tests
  - _Requirements: 1.1, 1.2, 1.3_

- [ ] 4. Implement `AlertEvaluator` with fired-state recording through `DynamoUsageMeterRepository` (conditional write) and the suspended-Account check, wire it into `IngestHandler`, with unit tests
  - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_
  - _Design: Property 1, Property 3_

- [ ] 5. Document the `quota-threshold` template and the new tables' attributes in `ARCHITECTURE.md`
  - _Requirements: 2.2, 2.3_

- [ ]* 6. Add a property-based test for `ThresholdValidator`
  - _Design: Property 2_

---

## Status Summary

| # | Task | Status | Commit(s) | Owner |
|---|---|---|---|---|
| 1 | OpenAPI contract and codegen | ✅ done | 3f9a1c2 | BE |
| 2 | `ThresholdValidator` | ✅ done | 8b41d07 | BE |
| 3 | `ThresholdsHandler` | ✅ done | c05e6a9 | BE |
| 4 | `AlertEvaluator` | ✅ done | 1d7b3e4 | BE |
| 5 | `ARCHITECTURE.md` update | ✅ done | e92f058 | BE |
| 6 | Property-based validator test (optional) | skipped | n/a | BE |
