# Design: Weekly usage digest

## Components

- `UsageDigestJob` (Metering): scheduled every Monday at 08:00 UTC by EventBridge. Lists active Accounts and builds one Usage_Digest each.
- `UsageDigestBuilder` (Metering): reads the Usage_Meter and the Quota for an Account and builds the digest payload.
- `SpikeDetector` (Metering): raises a Spike_Alert in the digest when this week's Metered_Events are more than three times last week's.
- Sends through Notification_Service with the new `usage-digest` template. Retries are handled by the existing SQS queue.

## Data

No new table. The Quota is read through the existing Quota_Cache.
