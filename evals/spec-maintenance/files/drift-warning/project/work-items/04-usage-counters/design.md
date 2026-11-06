# Design Document: Usage Counters

## Components and Interfaces

### UsageCounterRepository
- Replaces the `usage-meters` table with `usage-counters`
- Sums the daily shards to answer `GET /accounts/{accountId}/usage`
- Keeps the response shape of Usage_Summary unchanged
