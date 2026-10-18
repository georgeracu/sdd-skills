# Ubiquitous Language

Terms used as subjects in requirements and design documents. Use them exactly as written, underscore included, so that a requirement, a design component and a test name all point at the same thing.

| Term | Definition |
|---|---|
| Account | A paying customer. Owns one or more API keys and exactly one Plan at any time. |
| Account_Owner | The user who receives account-level notifications. Every Account has exactly one. |
| Plan | The tier an Account is on. A Plan fixes the Quota. Current Plans: `starter` (10,000), `team` (250,000), `scale` (2,000,000). |
| Quota | The number of Metered_Events an Account may record in one Billing_Period, as fixed by its Plan. |
| Billing_Period | One calendar month in UTC. Usage resets at the start of each Billing_Period. |
| Metered_Event | One recorded API call made with an Account's API key. |
| Usage_Meter | The running count of Metered_Events for an Account in the current Billing_Period. |
| Usage_Summary | The response object of `GET /accounts/{accountId}/usage`: metered count, Quota and whether the Quota is exceeded. |
| Quota_Cache | The in-process cache of Plan Quotas keyed by account id, held in the usage Lambda between invocations. |
| Notification_Service | The existing component that emails Account_Owners. Accepts a template id and a payload; delivery is asynchronous through SQS and SES. |
| Dashboard | The TypeScript frontend where an Account_Owner reads usage and manages the Account. |
