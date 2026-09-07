# Quota Alerts Design

Brainstorming output, 2026-03-02. Agreed direction and constraints for the quota alerts feature. Reference material for the spec, not the spec itself.

## Problem

Account_Owners find out they have exceeded their Quota when API calls start failing with 429. Support gets a ticket a day about it. The Dashboard shows the Usage_Meter, but nobody watches a dashboard. We want to tell them before it happens.

## Agreed approach

Alert on threshold crossings, evaluated on the existing ingest path rather than on a schedule. Every Account gets a set of Alert_Thresholds expressed as percentages of Quota. When the Usage_Meter crosses a threshold within a Billing_Period, send one Quota_Alert to the Account_Owner through the Notification_Service.

## Decisions

1. Default thresholds are 80% and 100%. Account_Owners can replace the set with any list of one to five distinct integer percentages between 1 and 100, through the Dashboard and the API.
2. At most one Quota_Alert per threshold per Billing_Period. Crossing 80% twice in a month (possible after a Plan downgrade mid-period) sends one email, not two.
3. Evaluation happens when the ingest Lambda increments the Usage_Meter. No scheduled job. The increment already returns the new count, so the comparison is free.
4. The alert state (which thresholds have fired this period) lives on the `usage-meters` item, not in a new table. Resets with the Billing_Period like everything else on that item.
5. A Plan change mid-period changes the Quota and therefore the percentage. We do not retroactively fire alerts on a Plan change; the next increment evaluates against the new Quota. If a downgrade puts usage past a threshold that has not fired this period, the next increment fires it.
6. Lowering a threshold below current usage fires on the next increment, same rule as above. We considered firing immediately on save and rejected it: it needs a second evaluation path for one edge case.
7. Suspended Accounts never receive Quota_Alerts. The ingest path already rejects their events, so this mostly falls out, but the requirement is stated so nobody removes the guard.
8. New Notification_Service template `quota-threshold`, payload: account id, threshold percentage, metered count, Quota, Billing_Period.
9. API: read and replace the threshold set on the Account resource. Follow the existing `plan` sub-resource shape.

## Out of scope

- Webhooks or Slack. Email through the Notification_Service only.
- Per-API-key thresholds. Account level only.
- Hard-stopping ingest at 100%. That is the existing Quota behaviour and does not change.
- Alert history in the Dashboard. The `notifications` Lambda already logs sends.

## Constraints

- No new DynamoDB table. Provisioned capacity budget is spent.
- Reuse the Notification_Service as is; no new delivery channel.
- The ingest Lambda has a p99 budget of 40 ms and the evaluation has to fit inside it, so no extra table read on the hot path beyond what the increment already does.
