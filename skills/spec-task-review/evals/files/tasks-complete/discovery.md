# Discovery: Quota Alerts

**Status:** Approved
**Spec type:** Feature
**Workflow:** Requirements-First

## Problem

Account_Owners learn that they have run out of Quota only when ingest starts rejecting usage. They want a warning before that point.

## Scope

Email alerts when a Usage_Meter crosses configurable percentages of the Plan Quota, and a Dashboard setting to change the percentages. Out of scope: SMS, webhooks, and alerts on anything other than the Quota.

## Approach

Evaluate thresholds on the ingest path after the atomic increment, record fired thresholds on the per-period Usage_Meter item, and send through the existing Notification_Service.

## Decisions

- Default thresholds are 80 and 100 percent.
- At most five thresholds, each an integer from 1 to 100.
- Suspended Accounts receive no alerts.

## Constraints

- No new table; the `accounts` and `usage-meters` items carry the new attributes.
- Types are generated from `openapi.yaml`, never written by hand.
