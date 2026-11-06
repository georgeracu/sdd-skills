# Issue #412: usage still reports quotaExceeded after a plan upgrade

**Reported by:** support, from three customer tickets in one week
**Severity:** high. Customers who pay for an upgrade see no change for up to an hour and assume the payment failed.

## Steps to reproduce

1. Account `acc_7f3k2m9p1q4z` on `starter` (Quota 10,000) with 10,240 metered events this period. `GET /accounts/acc_7f3k2m9p1q4z/usage` returns `quotaExceeded: true`, `quota: 10000`. Correct.
2. `PUT /accounts/acc_7f3k2m9p1q4z/plan` with `{"plan": "team"}`. Returns 200 with `plan: team`. Correct.
3. `GET /accounts/acc_7f3k2m9p1q4z/usage` again, immediately and for the next 40 to 60 minutes. Returns `quotaExceeded: true`, `quota: 10000`.
4. After roughly an hour the same call returns `quotaExceeded: false`, `quota: 250000`, with nothing else having changed.

## Expected

Step 3 returns `quota: 250000` and `quotaExceeded: false` straight after the plan change.

## What we know

- `GET /accounts/{accountId}` returns the new plan immediately, so the `accounts` table is updated. Only the usage endpoint is stale.
- The staleness window is never longer than an hour and is shorter when the usage Lambda has been cold-started recently, which points at the Quota_Cache.
- Ingest is unaffected: events are still recorded during the window. Only the reported Quota and `quotaExceeded` are wrong.
- Downgrades have the mirror-image problem: an account moved from `team` to `starter` keeps reporting `quotaExceeded: false` and is not rate-limited for up to an hour. Nobody has complained about that one, for obvious reasons.

## Notes

The plan-change handler and the usage handler are separate Lambdas, so an in-process cache in one cannot be cleared by the other. Whatever the fix is, the `plan-changed` notification already fires from the plan-change handler, so there is an existing hook on the write side.
