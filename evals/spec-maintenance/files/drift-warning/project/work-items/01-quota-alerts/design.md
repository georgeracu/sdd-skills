# Design Document: Quota Alerts

## Components and Interfaces

### AlertEvaluator
- Runs on each ingest increment
- Reads the Usage_Meter from the `usage-meters` table after the increment
- Records fired thresholds on the `usage-meters` item
- Writes the fired state with a conditional update on the `usage-meters` item
