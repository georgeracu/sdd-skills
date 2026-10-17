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

### Requirement 2: Fresh Quota

**User Story:** As an Account_Owner, I want the digest to show my current Quota, so that a recent plan change is reflected.

#### Acceptance Criteria

1. THE Quota_Cache TTL SHALL be five minutes, replacing the one hour TTL.
2. THE `export-ready` template SHALL remain available, and new export completions SHOULD be announced in the next Usage_Digest instead.
