# Requirements Document

## Introduction

Usage counts move from the `usage-meters` table to a new `usage-counters` table, so that counters can be sharded by day.

## Glossary

- **Usage_Meter**: The running count of Metered_Events for an Account in the current Billing_Period.
- **Usage_Counter**: One day's shard of a Usage_Meter.

## Requirements

### Requirement 1: Store counts in usage-counters

**User Story:** As an operator, I want daily counter shards, so that hot accounts do not throttle one item.

#### Acceptance Criteria

1. THE ingest Lambda SHALL increment the Usage_Counter for the current day in the `usage-counters` table.
2. THE `usage-counters` table SHALL use PK `accountId` and SK `billingPeriod#day`.
3. THE `usage-meters` table SHALL be deleted after migration.
