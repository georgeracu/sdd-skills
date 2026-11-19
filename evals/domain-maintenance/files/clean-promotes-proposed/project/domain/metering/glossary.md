# Glossary: Metering

Terms of the Metering context.

## Terms

### Account_Owner

The user who receives account-level notifications. Every Account has exactly one.

- **Avoid:** Customer Contact, Billing Contact
- **Status:** agreed
- **Since:** import

### Quota

The number of Metered_Events an Account may record in one Billing_Period, as fixed by its Plan.

- **Avoid:** Limit, Allowance
- **Status:** agreed
- **Since:** import

### Usage_Digest

The weekly email that tells an Account_Owner how much of the Quota the Account has used in the current Billing_Period.

- **Avoid:** Weekly Report, Usage Email
- **Status:** proposed
- **Since:** work-item 04

### Usage_Meter

The running count of Metered_Events for an Account in the current Billing_Period.

- **Avoid:** Counter, Usage Count
- **Status:** agreed
- **Since:** import
