# Requirements Document

## Introduction

Account_Owners can view a profile summary through the API.

## Glossary

- **Account_Owner**: A user who manages an Account.
- **Profile_Summary**: The API response describing an Account profile.

## Requirements

### Requirement 1: Read profile summary

**User Story:** As an Account_Owner, I want to read my profile summary, so that I can verify account details.

#### Acceptance Criteria

1. THE Profile_Summary API SHALL return the account identifier and display name.
2. IF the account does not exist, THEN THE Profile_Summary API SHALL return a 404 response.
