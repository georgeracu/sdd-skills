# Discovery: Weekly usage digest

**Date:** 2026-11-10
**Work item:** 04-usage-digest
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Approved 2026-11-12
**Sources consulted:** ARCHITECTURE.md, project/domain/glossary.md, project/work-items/
**Sources unavailable:** openapi.yaml

## Problem

Account owners see usage only when they log in to the Dashboard, so they miss usage spikes until the invoice arrives.

## Scope

In scope:

1. A weekly email digest of the Usage_Meter for each Account.
2. An opt-out setting per Account.

## Terms

- `Account_Owner`: the user who receives account-level notifications. Existing.
- `Account_Admin`: a user who manages account settings. New.
- `Usage_Digest`: the weekly email summary of usage. New.

## Open questions

None.
