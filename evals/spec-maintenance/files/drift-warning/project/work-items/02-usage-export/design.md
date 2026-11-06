# Design Document: Usage Export

## Components and Interfaces

### ExportService
- Queries the `usage-meters` table by `accountId` and `billingPeriod`
- Writes one CSV row per day to the `exports` bucket
- Grants the export Lambda read access to the `usage-meters` table
