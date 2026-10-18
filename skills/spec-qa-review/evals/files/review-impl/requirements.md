# Requirements Document

## Introduction

Account_Owners need advance notice before API usage reaches the Account's Quota. Quota alerts notify them when usage crosses configured percentages during a Billing_Period.

## Glossary

- **Account_Owner**: The user who manages an Account and receives account notifications.
- **Account**: A paying customer with one Plan and one usage meter.
- **Quota**: The usage limit supplied by an Account's Plan.
- **Usage_Meter**: The per-Account, per-Billing_Period usage record.
- **Alert_Threshold**: A distinct integer percentage from 1 through 100 of Quota.
- **Quota_Alert**: A notification sent when a Usage_Meter crosses an Alert_Threshold.
- **Billing_Period**: One calendar month in UTC.
- **Notification_Service**: The existing email notification service.
- **Threshold_Set**: The one-to-five Alert_Threshold values configured for an Account.

## Requirements

### Requirement 1: Configure thresholds

**User Story:** As an Account_Owner, I want sensible default and configurable thresholds, so that alerts match my usage policy.

#### Acceptance Criteria

1. THE Account SHALL use a Threshold_Set containing 80 and 100 percent by default.
2. THE Account_Owner SHALL be able to replace the Threshold_Set with one to five distinct integer Alert_Threshold values between 1 and 100.
3. IF a replacement contains fewer than one, more than five, duplicate, non-integer, or out-of-range values, THEN THE Account API SHALL reject it with a validation error.
4. WHEN an Account_Owner reads the Account, THE Account API SHALL return the current Threshold_Set.

### Requirement 2: Evaluate usage crossings

**User Story:** As an Account_Owner, I want one alert per threshold when usage crosses it, so that I am warned without duplicate emails.

#### Acceptance Criteria

1. WHEN the ingest path increments a Usage_Meter, THE Account SHALL evaluate the new usage percentage against each Alert_Threshold.
2. WHEN a Usage_Meter crosses an Alert_Threshold for the first time in a Billing_Period, THE Notification_Service SHALL send one Quota_Alert to the Account_Owner.
3. THE Usage_Meter SHALL retain which Alert_Threshold values fired, resetting that state with the Billing_Period.
4. IF an Alert_Threshold has already fired in the Billing_Period, THEN THE Account SHALL NOT send another Quota_Alert for that threshold.
5. IF an Account is suspended, THEN THE Notification_Service SHALL NOT send Quota_Alerts for it.

### Requirement 3: Handle changes

**User Story:** As an Account_Owner, I want plan and threshold changes to take effect predictably, so that alerts reflect the next recorded usage.

#### Acceptance Criteria

1. WHEN an Account's Plan changes mid-period, THE Account SHALL evaluate the next ingest increment against the new Quota and SHALL NOT retroactively send an alert at change time.
2. WHEN an Account_Owner lowers an Alert_Threshold below current usage, THE Account SHALL evaluate and, if applicable, send the Quota_Alert on the next ingest increment.
3. THE Quota_Alert SHALL use the `quota-threshold` template and include account id, threshold percentage, metered count, Quota, and Billing_Period.

### Requirement 4: Threshold API

**User Story:** As an Account_Owner, I want to manage thresholds through the API, so that automation can keep alert policy synchronized.

#### Acceptance Criteria

1. THE Account API SHALL expose read and replace operations for the Threshold_Set as a sub-resource shaped like the existing `plan` sub-resource.
2. IF the request is unauthorized or names an unknown Account, THEN THE Account API SHALL return its standard error response without changing the Threshold_Set.
