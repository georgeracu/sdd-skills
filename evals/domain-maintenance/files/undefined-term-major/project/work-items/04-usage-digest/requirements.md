# Requirements: Weekly usage digest

## Requirement 1

**User Story:** As an Account_Owner, I want a weekly Usage_Digest, so that I see usage spikes before the Invoice arrives.

1. WHEN it is Monday 08:00 UTC THE system SHALL send each active Account's Account_Owner one Usage_Digest.
2. THE Usage_Digest SHALL show the Usage_Meter for the current Billing_Period and its percentage of the Quota.
3. WHEN the Usage_Meter is zero THE system SHALL NOT send a Usage_Digest.
4. WHEN this week's Metered_Events are more than three times last week's THE Usage_Digest SHALL include a Spike_Alert.
