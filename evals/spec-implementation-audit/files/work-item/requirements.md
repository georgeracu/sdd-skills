# Requirements Document

## Introduction

Account_Owners need a warning before an Account reaches its Quota. This work item lets an Account_Owner replace the Account's Threshold_Set and sends one Quota_Alert when usage first crosses each Alert_Threshold in a Billing_Period.

## Glossary

- **Account_Owner**: The user who receives account notifications.
- **Account**: A paying customer with one Plan.
- **Quota**: The Metered_Event limit fixed by an Account's Plan.
- **Usage_Meter**: The per-Account, per-Billing_Period count of Metered_Events.
- **Alert_Threshold**: An integer percentage from 1 through 100 of Quota.
- **Threshold_Set**: The one to five distinct Alert_Threshold values of an Account, 80 and 100 by default.
- **Quota_Alert**: The notification sent when a Usage_Meter first crosses an Alert_Threshold.
- **Billing_Period**: One calendar month in UTC.
- **Notification_Service**: The existing component that emails Account_Owners.

## Requirements

### Requirement 1: Replace the Threshold_Set

**User Story:** As an Account_Owner, I want to set my own thresholds, so that alerts match my usage policy.

#### Acceptance Criteria

1. WHEN an Account_Owner replaces the Threshold_Set with one to five distinct integers between 1 and 100, THE Account API SHALL store them in ascending order and return the updated Account.
2. IF a replacement has fewer than one or more than five values, a duplicate, or a value outside 1 to 100, THEN THE Account API SHALL respond 400 with the problem code `invalid_thresholds` and SHALL NOT change the Threshold_Set.
3. IF the Account does not exist, THEN THE Account API SHALL respond 404 with the problem code `account_not_found`.

### Requirement 2: Send Quota_Alerts

**User Story:** As an Account_Owner, I want one email per threshold when usage crosses it, so that I am warned without duplicates.

#### Acceptance Criteria

1. WHEN the ingest path increments a Usage_Meter, THE Account SHALL compare the new usage percentage of Quota with each Alert_Threshold.
2. WHEN a Usage_Meter reaches an Alert_Threshold for the first time in a Billing_Period, THE Notification_Service SHALL send one Quota_Alert using the `quota-threshold` template with the account id, threshold, metered count, Quota and Billing_Period.
3. THE Usage_Meter SHALL record which Alert_Threshold values have fired, and that record SHALL be scoped to the Billing_Period.
4. IF an Alert_Threshold has already fired in the Billing_Period, THEN THE Notification_Service SHALL NOT send another Quota_Alert for it.
5. IF an Account is suspended, THEN THE Notification_Service SHALL NOT send a Quota_Alert for it.
