# Implementation Plan

- [x] Task 1: Implement AlertEvaluator
  - Read the Usage_Meter from the `usage-meters` table after each increment
  - Add a unit test that stubs the `usage-meters` read
- [x] Task 2: Record fired thresholds
  - Add a `firedThresholds` attribute to the `usage-meters` item
  - Write the attribute with a conditional update on the `usage-meters` item
- [x] Task 3: Reset thresholds at the start of a Billing_Period
  - Clear `firedThresholds` on the `usage-meters` item when the period rolls over
  - Add an integration test that seeds a `usage-meters` item from the previous period
