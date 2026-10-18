# Requirements Document

## Introduction

Account_Owners can export the daily Usage_Meter counts for the current Billing_Period as CSV.

## Glossary

- **Account_Owner**: The user who receives account-level notifications.
- **ExportService**: The component that writes the CSV file.

## Requirements

### Requirement 1: Export daily usage

**User Story:** As an Account_Owner, I want a CSV of daily usage, so that I can analyse it elsewhere.

#### Acceptance Criteria

1. WHEN an Account_Owner requests an export from the Dashboard, THE ExportService SHALL write one CSV row per day of the current Billing_Period.
