# Discovery: Weekly usage digest

**Date:** 2026-10-14
**Work item:** 04-usage-digest
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Draft
**Sources consulted:** ARCHITECTURE.md, UBIQUITOUS_LANGUAGE.md, project/work-items/
**Sources unavailable:** openapi.yaml

## Problem

Account owners see usage only when they log in to the Dashboard, so they miss usage spikes until the invoice arrives. Support handles these complaints after the billing period closes, when nothing can be done.

## Users and outcomes

- Account_Owner: receives a weekly summary of usage per meter without logging in.
- Account_Admin: can switch the digest off for the account.

## Scope

In scope:

1. A weekly email digest of usage per meter.
2. An opt-out setting per account.

Out of scope: per-user digests, custom schedules, PDF export.

## Approach

Build the digest from the existing usage records and send it through Notification_Service with a new template. Rejected: a separate digest table, because it adds a store to maintain for history nobody asked for.

## Decisions

1. The digest is sent every Monday at 08:00 in the account's timezone.
2. The digest covers the previous seven days.
3. At most one digest per account per week.
4. The digest is enabled by default.
5. Meters with zero usage are omitted.

## Constraints

- Reuse Notification_Service; callers pass a template id and a payload map.
- Add no new datastore.

## Edge and failure states

- An account with no usage in the week gets no digest.
- A suspended account gets no digest.
- If Notification_Service is unavailable, the send is retried the next hour, at most three times.

## Terms

- `Account_Owner`: the user who receives account-level notifications. Existing.
- `Account_Admin`: a user who manages account settings. New.
- `Usage_Digest`: the weekly email summary of usage per meter. New.

## Open questions

- Should the digest link to the Dashboard usage page? Recommended: yes. Settled in design.
