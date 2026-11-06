# Requirements Document

## Introduction

Account_Owners need advance notice before API usage reaches the Account's Quota. Quota alerts email the Account_Owner when usage crosses configured percentages during a Billing_Period, and the thresholds are managed through the API and the Dashboard.

## Glossary

- **Account_Owner**: The user who receives account-level notifications.
- **Account**: A paying customer with one Plan and one Usage_Meter per Billing_Period.
- **Quota**: The usage limit fixed by an Account's Plan.
- **Usage_Meter**: The per-Account, per-Billing_Period running count of Metered_Events.
- **Alert_Threshold**: An integer percentage from 1 through 100 of Quota.
- **Threshold_Set**: The one to five distinct Alert_Threshold values configured for an Account.
- **Quota_Alert**: The email sent when a Usage_Meter first crosses an Alert_Threshold in a Billing_Period.
- **Notification_Service**: The existing component that emails Account_Owners.
- **Dashboard**: The TypeScript frontend where an Account_Owner manages the Account.

## Requirements

### Requirement 1: Configure thresholds

**User Story:** As an Account_Owner, I want default and configurable thresholds, so that alerts match my usage policy.

#### Acceptance Criteria

1. THE Account SHALL use a Threshold_Set of 80 and 100 percent until an Account_Owner replaces it.
2. THE Account_Owner SHALL be able to replace the Threshold_Set with one to five distinct integer Alert_Threshold values between 1 and 100.
3. IF a replacement has fewer than one or more than five values, duplicates, non-integers or out-of-range values, THEN THE Account API SHALL reject it with a validation error and leave the Threshold_Set unchanged.

### Requirement 2: Evaluate usage crossings

**User Story:** As an Account_Owner, I want one alert per threshold when usage crosses it, so that I am warned without duplicate emails.

#### Acceptance Criteria

1. WHEN the ingest path increments a Usage_Meter, THE Account SHALL evaluate the new usage percentage against each Alert_Threshold.
2. WHEN a Usage_Meter crosses an Alert_Threshold for the first time in a Billing_Period, THE Notification_Service SHALL send one Quota_Alert to the Account_Owner.
3. THE Usage_Meter SHALL retain which Alert_Threshold values have fired and SHALL reset that state with the Billing_Period.
4. IF an Account is suspended, THEN THE Notification_Service SHALL NOT send Quota_Alerts for it.

### Requirement 3: Alert content

**User Story:** As an Account_Owner, I want the alert to say what was crossed, so that I can act on it.

#### Acceptance Criteria

1. THE Quota_Alert SHALL use the `quota-threshold` template and include the account id, threshold percentage, metered count, Quota and Billing_Period.

### Requirement 4: Threshold API

**User Story:** As an Account_Owner, I want to manage thresholds through the API, so that automation can keep the policy in step.

#### Acceptance Criteria

1. THE Account API SHALL expose read and replace operations for the Threshold_Set as a `thresholds` sub-resource shaped like the existing `plan` sub-resource.
2. IF a request names an unknown Account, THEN THE Account API SHALL return its standard not-found error without changing any Threshold_Set.

### Requirement 5: Dashboard settings

**User Story:** As an Account_Owner, I want to edit thresholds on the Dashboard, so that I do not need to call the API by hand.

#### Acceptance Criteria

1. WHEN an Account_Owner opens the Account settings, THE Dashboard SHALL show the current Threshold_Set and allow it to be replaced.
2. IF the Account API rejects a replacement, THEN THE Dashboard SHALL show the validation message beside the threshold field and keep the entered values.
