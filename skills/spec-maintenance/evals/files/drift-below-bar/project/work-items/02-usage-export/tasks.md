# Implementation Plan

- [x] Task 1: Implement the ExportService CSV writer
  - Query the `usage-meters` table by `accountId` and `billingPeriod`
  - Map each `usage-meters` item to one CSV row
- [x] Task 2: Add the Dashboard export button
- [x] Task 3: Grant the export Lambda its data access
  - Add read access to `usage-meters` to the export Lambda role
  - Add the `usage-meters` table ARN to the role's resource list
- [x] Task 4: Handle empty periods
  - Treat a missing `usage-meters` item as zero metered events
  - Add a unit test for an Account with no `usage-meters` item
