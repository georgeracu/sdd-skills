# Design Document: Quota Alerts

## Components and Interfaces

### AlertEvaluator
- Runs on each ingest increment
- Reads the Plan Quota through the Quota_Cache with a TTL of one hour
- Records fired thresholds on the `usage-meters` item
