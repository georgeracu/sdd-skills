# Discovery: Several people per Account in the Dashboard

**Date:** 2026-11-10
**Work item:** 06-team-sign-in
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Approved 2026-11-12
**Sources consulted:** ARCHITECTURE.md, project/domain/glossary.md, project/work-items/
**Sources unavailable:** openapi.yaml

## Problem

Only the Account_Owner can sign in to the Dashboard, so teams share one password.

## Scope

In scope:

1. Each person on a team signs in to the Dashboard with their own Account.
2. The Account_Owner invites people by email.

## Terms

- `Account`: a person's sign-in to the Dashboard, with an email address and a password. New.
- `Account_Owner`: the user who receives account-level notifications. Existing.

## Open questions

None.
