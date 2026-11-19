# Discovery: Notification settings page

**Date:** 2026-11-10
**Work item:** 07-notification-settings
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Approved 2026-11-12
**Sources consulted:** ARCHITECTURE.md, project/domain/glossary.md, project/work-items/
**Sources unavailable:** openapi.yaml

## Problem

Account owners cannot turn individual emails off, so they filter them out and then miss payment failures.

## Scope

In scope:

1. A Dashboard page where the Account_Owner switches each kind of email on or off.
2. The payment-failed email cannot be switched off.

## Terms

- `Account_Owner`: the user who receives account-level notifications. Existing.
- `Notification_Preference`: whether an Account receives one kind of email. New.

## Open questions

None.
