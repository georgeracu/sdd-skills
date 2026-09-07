# Requirements Document

## Introduction

Account_Owners need a quick view of the active plan and its limits without loading the full Dashboard. This work item adds a small API response that can be used by the Dashboard and automation.

## Glossary

- **Account_Owner**: A user who manages an Account.
- **Account**: A paying customer with one active Plan.
- **Plan**: The subscription tier and quota assigned to an Account.
- **Plan_Summary**: The API representation of an Account's active Plan and quota limits.

## Requirements

### Requirement 1: Retrieve a plan summary

**User Story:** As an Account_Owner, I want to retrieve my active plan summary, so that I can display current limits in the Dashboard.

#### Acceptance Criteria

1. WHEN an Account_Owner requests `GET /accounts/{accountId}/plan/summary`, THE Plan_Summary API SHALL return the Account's active Plan and quota limits as JSON.
2. THE Plan_Summary API SHALL include `accountId`, `planName`, `monthlyLimit`, and `billingPeriod` in a successful response.
3. IF the requested Account does not exist, THEN THE Plan_Summary API SHALL return a 404 Problem response with code `account-not-found`.
4. IF the Account has no active Plan, THEN THE Plan_Summary API SHALL return a 409 Problem response with code `plan-missing`.
