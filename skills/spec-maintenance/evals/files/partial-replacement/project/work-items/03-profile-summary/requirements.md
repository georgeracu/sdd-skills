# Requirements Document

## Introduction

Account_Owners can read a profile summary through the API.

## Glossary

- **Profile_Summary**: The API response describing an Account profile.

## Requirements

### Requirement 1: Read profile summary

**User Story:** As an Account_Owner, I want to read my profile summary, so that I can verify account details.

#### Acceptance Criteria

1. WHEN an Account_Owner requests the profile summary, THE ProfileSummaryHandler SHALL return the Profile_Summary as JSON.
