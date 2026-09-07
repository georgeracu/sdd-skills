# Requirements Document

## Introduction

Account_Owners reconcile their invoice against their own logs at the end of each Billing_Period and today do that by screenshotting the Dashboard. This feature lets an Account_Owner download the Metered_Events for a Billing_Period as a CSV file from the Dashboard and from the API, so the reconciliation can be scripted.

## Glossary

- **Account**: A paying customer. Owns API keys and exactly one Plan.
- **Account_Owner**: The user who receives account-level notifications and operates the Dashboard.
- **Billing_Period**: One calendar month in UTC.
- **Metered_Event**: One recorded API call made with an Account's API key.
- **Usage_Export**: A CSV file holding the Metered_Events of one Account for one Billing_Period.
- **Export_Request**: The API call or Dashboard action that asks for a Usage_Export.

## Requirements

### Requirement 1: Download an export from the Dashboard

**User Story:** As an Account_Owner, I want to download this month's usage as a CSV from the Dashboard, so that I can reconcile it against my own logs without screenshots.

#### Acceptance Criteria

1. WHEN an Account_Owner selects a Billing_Period on the usage page, THE Dashboard SHALL offer a download action for that Billing_Period.
2. WHEN the download action is selected, THE Dashboard SHALL request the Usage_Export through the API and save the response as a file named `usage-{accountId}-{billingPeriod}.csv`.
3. IF the Export_Request fails, THEN THE Dashboard SHALL show the error message returned by the API and leave the page otherwise unchanged.

### Requirement 2: Request an export through the API

**User Story:** As an Account_Owner, I want to fetch the export from a script, so that reconciliation runs on a schedule.

#### Acceptance Criteria

1. THE API SHALL accept an Export_Request for a given Account and Billing_Period.
2. THE API SHALL serve Usage_Exports for the current Billing_Period and the twelve Billing_Periods before it.
3. IF an Export_Request names a Billing_Period outside that range, THEN THE API SHALL reject it with a 400 response and a `Problem` body whose code is `billing-period-out-of-range`.
4. IF an Export_Request names a Billing_Period that is not `YYYY-MM`, THEN THE API SHALL reject it with a 400 response and a `Problem` body whose code is `billing-period-malformed`.
5. IF the Account is suspended, THEN THE API SHALL still serve Usage_Exports for it, as suspended Account_Owners need their history to dispute an invoice.

### Requirement 3: Export content

**User Story:** As an Account_Owner, I want one row per API call, so that I can match rows to my own request logs.

#### Acceptance Criteria

1. THE Usage_Export SHALL contain one row per Metered_Event with columns `timestamp`, `apiKeyId`, `route` and `statusCode`, in that order, after a header row.
2. THE Usage_Export SHALL order rows by `timestamp` ascending.
3. WHEN a Billing_Period has no Metered_Events, THE Usage_Export SHALL contain the header row only.
4. THE Usage_Export SHALL use RFC 4180 quoting for any field containing a comma, quote or newline.
