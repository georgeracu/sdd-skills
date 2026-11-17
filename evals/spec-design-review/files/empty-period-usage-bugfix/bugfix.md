# Bugfix Requirements Document

## Introduction

The usage endpoint answers 500 for an Account that has no Metered_Event yet in the current Billing_Period. This happens to every Account at the start of each Billing_Period, and to a new Account, until its first Metered_Event. The Dashboard then shows an error in place of the Account's usage.

## Glossary

- **Bug_Condition**: A Usage_Read for an Account that has an `accounts` item, and a Billing_Period for which no `usage-meters` item exists.
- **Usage_Read**: A call to `QuotaService.usageFor` that builds the Usage_Summary for one Account and one Billing_Period.

## Bug Analysis

### Current Behavior (Defect)

1.1 WHEN a Usage_Read meets the Bug_Condition, THEN the usage endpoint currently answers 500 with a `Problem` instead of a Usage_Summary.

### Expected Behavior (Correct)

2.1 WHEN a Usage_Read meets the Bug_Condition, THEN the Usage_Summary SHALL report `metered` as 0.

2.2 WHEN a Usage_Read meets the Bug_Condition, THEN the Usage_Summary SHALL report `quotaExceeded: false`, with the Quota taken as for any other Usage_Read.

### Unchanged Behavior (Regression Prevention)

3.1 WHEN a Usage_Read is for a Billing_Period that has a `usage-meters` item, THEN the Usage_Summary SHALL CONTINUE TO report that item's `metered` value and `quotaExceeded` as `metered` greater than the Quota.

3.2 WHEN a Usage_Read runs, THEN the QuotaService SHALL CONTINUE TO take the Quota from the Quota_Cache while the entry is less than one hour old, and SHALL CONTINUE TO load it from the `accounts` table only when the Quota_Cache has no entry or the entry has expired.

3.3 WHEN a Usage_Read is for an account id that has no `accounts` item, THEN the usage endpoint SHALL CONTINUE TO answer 404 with the `NotFound` response.
