# Requirements Document

## Introduction

Account_Owners are emailed when usage crosses an Alert_Threshold.

## Glossary

- **Alert_Threshold**: A percentage of the Quota at which an alert fires.
- **Account_Owner**: The user who receives account-level notifications.

## Requirements

### Requirement 1: Alert on threshold

**User Story:** As an Account_Owner, I want an alert at 80% and 100% of my Quota, so that I can react.

#### Acceptance Criteria

1. WHEN the Usage_Meter crosses an Alert_Threshold, THE Notification_Service SHALL send the `quota-threshold` template once per threshold per Billing_Period.
