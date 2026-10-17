# Requirements Document

## Introduction

Account_Owners can read the usage of earlier Billing_Periods.

## Glossary

- **Usage_History**: The list of Usage_Summary values for earlier Billing_Periods.

## Requirements

### Requirement 1: Read history

**User Story:** As an Account_Owner, I want to see earlier months, so that I can compare them.

#### Acceptance Criteria

1. WHEN an Account_Owner requests the Usage_History, THE UsageHistoryHandler SHALL return one Usage_Summary per earlier Billing_Period.
