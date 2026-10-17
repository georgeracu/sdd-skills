# Requirements Document

## Introduction

Account_Owners receive a weekly Usage_Digest email that summarises the Usage_Meter for the closing week.

## Glossary

- **Account_Owner**: The user who receives account-level notifications.
- **Usage_Digest**: The weekly email carrying the Usage_Meter count, the Quota and the percentage used.
- **Notification_Service**: The existing component that emails Account_Owners.

## Requirements

### Requirement 1: Send the weekly digest

**User Story:** As an Account_Owner, I want a weekly usage email, so that I notice growth before the Quota is reached.

#### Acceptance Criteria

1. WHEN the `usage-digest-weekly` schedule fires on Monday at 06:00 UTC, THE Notification_Service SHALL send the `usage-digest` template to the Account_Owner of each active Account.
2. THE Usage_Digest payload SHALL carry the Usage_Meter count, the Quota and the percentage used.
3. IF an Account is suspended, THEN THE Notification_Service SHALL NOT send a Usage_Digest for it.

### Requirement 2: One scheduled trigger

**User Story:** As an operator, I want a single schedule for usage emails, so that there is one place to change it.

#### Acceptance Criteria

1. THE `usage-digest-weekly` rule SHALL replace the `export-nightly` rule, and THE `export-nightly` rule SHALL be deleted.
