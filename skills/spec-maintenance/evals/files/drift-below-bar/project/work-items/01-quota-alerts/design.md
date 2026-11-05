# Design Document: Quota Alerts

## Components and Interfaces

### AlertEvaluator
- Runs on each ingest increment
- Compares the Usage_Meter with the 80% and 100% thresholds of the Quota
- Sends each threshold once per Billing_Period through the Notification_Service
