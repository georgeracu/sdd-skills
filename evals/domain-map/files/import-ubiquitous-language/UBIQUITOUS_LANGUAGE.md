# Ubiquitous Language

## Usage

| Term | Definition | Aliases to avoid |
|------|------------|------------------|
| **Metered_Event** | One recorded API call made with an Account's API key | hit, request |
| **Usage_Meter** | The running count of Metered_Events for an Account in the current Billing_Period | counter |
| **Quota** | The number of Metered_Events an Account may record in one Billing_Period | limit |

## Accounts

| Term | Definition | Aliases to avoid |
|------|------------|------------------|
| **Account** | A paying customer that owns API keys and exactly one Plan | customer, tenant |
| **Account_Owner** | The user who receives account-level notifications | contact |
| **Plan** | The tier an Account is on; it fixes the Quota | tier |

## Relationships

- An **Account** has exactly one **Plan**
- A **Plan** fixes the **Quota**

## Example dialogue

> **Dev:** "When the counter passes the limit, do we block the request?"
> **Domain expert:** "When the Usage_Meter passes the Quota, yes, we reject the Metered_Event."

## Flagged ambiguities

- "usage" was used to mean both the **Usage_Meter** and a single **Metered_Event**. Use the specific term.
- "period" was used for both the **Billing_Period** and the digest week. **Billing_Period** is not defined yet.
