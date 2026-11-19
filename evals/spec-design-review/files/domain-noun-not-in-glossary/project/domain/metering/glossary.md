# Glossary: Metering

Terms of the Metering context. One term per concept. Definitions say what the thing is in the business, not how it is stored.

## Terms

### Account

A paying customer. Owns one or more API keys and exactly one Plan at any time.

- **Avoid:** Customer, Tenant
- **Status:** agreed
- **Since:** import

### Account_Owner

The user who receives account-level notifications. Every Account has exactly one.

- **Avoid:** Customer Contact, Billing Contact
- **Status:** agreed
- **Since:** import

### Billing_Period

One calendar month in UTC. Usage resets at the start of each Billing_Period.

- **Status:** agreed
- **Since:** import

### Metered_Event

One recorded API call made with an Account's API key.

- **Avoid:** Hit, Request Count
- **Status:** agreed
- **Since:** import

### Plan

The tier an Account is on. A Plan fixes the Quota.

- **Avoid:** Tier, Package
- **Status:** agreed
- **Since:** import

### Quota

The number of Metered_Events an Account may record in one Billing_Period, as fixed by its Plan.

- **Avoid:** Limit, Allowance
- **Status:** agreed
- **Since:** import

### Quota_Cache

The in-process cache of Plan Quotas keyed by account id, held in the usage Lambda between invocations.

- **Status:** agreed
- **Since:** import

### Usage_Meter

The running count of Metered_Events for an Account in the current Billing_Period.

- **Avoid:** Counter, Usage Counter, Usage Count
- **Status:** agreed
- **Since:** import

### Usage_Summary

The response object of the usage read: metered count, Quota and whether the Quota is exceeded.

- **Status:** agreed
- **Since:** import

### Threshold_Set

The one to five Alert_Thresholds that an Account has chosen.

- **Status:** proposed
- **Since:** work-item 01

### Quota_Alert

The Notification sent when usage first reaches an Alert_Threshold in a Billing_Period.

- **Status:** proposed
- **Since:** work-item 01
