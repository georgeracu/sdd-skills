# Design Document: Usage History

## Components and Interfaces

### UsageHistoryHandler
- Queries the `usage-meters` table for all billing periods of an Account
- Returns at most twelve Billing_Periods, newest first
- Treats a missing `usage-meters` item as zero metered events
