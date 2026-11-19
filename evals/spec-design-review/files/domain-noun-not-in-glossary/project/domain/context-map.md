# Context Map

The tables are the source of truth. The diagram is generated from them; do not edit it by hand.

## Contexts

| Context | Purpose | Classification | Code location | Glossary |
|---|---|---|---|---|
| Metering | Counts usage against each Account's Quota | core | `com.example.metering` | [glossary](metering/glossary.md) |
| Notifications | Emails Account_Owners | supporting | `com.example.notifications` | [glossary](notifications/glossary.md) |
| Billing | Issues Invoices for each Billing_Period | supporting | `com.example.billing` | [glossary](billing/glossary.md) |

## Relationships

| Upstream | Downstream | Pattern | Messages | Status | Since |
|---|---|---|---|---|---|
| Notifications | Metering | Open Host Service | `send notification` (command) | agreed | import |

## Diagram

```mermaid
flowchart LR
  Notifications -- "OHS: send notification" --> Metering
```
