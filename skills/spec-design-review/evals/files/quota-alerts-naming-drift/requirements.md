# Requirements Document

## Introduction

Account_Owners need advance notice before API usage reaches the Account's Quota. Quota alerts notify them when usage crosses configured percentages during a Billing_Period.

## Glossary

- **Account_Owner**: The user who receives account-level notifications. Every Account has exactly one.
- **Account**: A paying customer. Owns one or more API keys and exactly one Plan at any time.
- **Quota**: The number of Metered_Events an Account may record in one Billing_Period, as fixed by its Plan.
- **Usage_Meter**: The running count of Metered_Events for an Account in the current Billing_Period.
- **Alert_Threshold**: A distinct integer percentage from 1 through 100 of Quota.
- **Quota_Alert**: A notification sent when a Usage_Meter crosses an Alert_Threshold.
- **Billing_Period**: One calendar month in UTC. Usage resets at the start of each Billing_Period.
- **Notification_Service**: The existing component that emails Account_Owners. Accepts a template id and a payload; delivery is asynchronous through SQS and SES.
- **Threshold_Set**: The one-to-five Alert_Threshold values configured for an Account.

## Requirements

### Requirement 1: Configure thresholds

**User Story:** As an Account_Owner, I want sensible default and configurable thresholds, so that alerts match my usage policy.

#### Acceptance Criteria

1. THE Account SHALL use a Threshold_Set containing 80 and 100 percent by default.
2. WHEN an Account_Owner replaces the Threshold_Set, THE Account API SHALL store one to five distinct integer Alert_Threshold values between 1 and 100.
3. IF a replacement contains fewer than one, more than five, duplicate, non-integer or out-of-range values, THEN THE Account API SHALL reject it with a validation error and leave the Threshold_Set unchanged.
4. WHEN an Account_Owner reads the Threshold_Set, THE Account API SHALL return the current values.
5. THE Account API SHALL expose read and replace operations for the Threshold_Set as a sub-resource under the Account, placed like the existing `plan` sub-resource, with its `AccountId` parameter and its standard error responses.

### Requirement 2: Evaluate usage crossings

**User Story:** As an Account_Owner, I want one alert per threshold when usage crosses it, so that I am warned without duplicate emails.

#### Acceptance Criteria

1. WHEN the ingest path increments a Usage_Meter, THE Account SHALL evaluate the new usage percentage against each Alert_Threshold.
2. WHEN a Usage_Meter reaches an Alert_Threshold for the first time in a Billing_Period, THE Notification_Service SHALL send one Quota_Alert to the Account_Owner. A Quota_Alert that fails to send is logged and not retried.
3. THE Usage_Meter SHALL retain which Alert_Threshold values have fired, resetting that state with the Billing_Period.
4. IF an Alert_Threshold has already fired in the Billing_Period, THEN THE Account SHALL NOT send another Quota_Alert for that threshold.

### Requirement 3: Suspended accounts

**User Story:** As an Account_Owner of a suspended Account, I want no usage alerts while the Account is suspended, so that I am not emailed about an Account I cannot use.

#### Acceptance Criteria

1. IF an Account is suspended, THEN THE Notification_Service SHALL NOT send Quota_Alerts for it.
2. IF an Account is suspended, THEN THE Account SHALL NOT record an Alert_Threshold as fired.

### Requirement 4: Handle changes

**User Story:** As an Account_Owner, I want plan and threshold changes to take effect predictably, so that alerts reflect the next recorded usage.

#### Acceptance Criteria

1. WHEN an Account's Plan changes mid-period, THE Account SHALL evaluate the next ingest increment against the new Quota and SHALL NOT send an alert at change time.
2. WHEN an Account_Owner lowers an Alert_Threshold below current usage, THE Account SHALL evaluate and, if applicable, send the Quota_Alert on the next ingest increment.

### Requirement 5: Alert content

**User Story:** As an Account_Owner, I want each alert to say which threshold was crossed and how much I have used, so that I can act without opening the Dashboard.

#### Acceptance Criteria

1. THE Notification_Service SHALL send each Quota_Alert using a new `quota-threshold` template.
2. THE Quota_Alert SHALL carry the account id, threshold percentage, metered count, Quota and Billing_Period.
