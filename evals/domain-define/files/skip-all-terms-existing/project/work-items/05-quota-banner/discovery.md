# Discovery: Quota warning banner

**Date:** 2026-11-10
**Work item:** 05-quota-banner
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Approved 2026-11-12
**Sources consulted:** ARCHITECTURE.md, project/domain/glossary.md, project/work-items/
**Sources unavailable:** openapi.yaml

## Problem

Account owners do not notice that the Usage_Meter is close to the Quota until requests start failing.

## Scope

In scope:

1. A Dashboard banner shown to the Account_Owner when the Usage_Meter passes 80% of the Quota.

## Terms

- `Account_Owner`: the user who receives account-level notifications. Existing.
- `Usage_Meter`: the running count of Metered_Events in the current Billing_Period. Existing.
- `Quota`: the number of Metered_Events an Account may record in one Billing_Period. Existing.

## Open questions

None.
