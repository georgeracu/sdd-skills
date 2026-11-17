# Webhook Receiver Decisions

These decisions are settled. The design takes them as given.

## Status values

The Acme Billing provider sends one of three `status` values. The receiver acts on each as follows.

| Status | Downstream action |
|---|---|
| `paid` | Set `accounts.status` to `active`. No notification. |
| `failed` | Set `accounts.status` to `suspended` and ask the Notification_Service to send the existing `payment-failed` template to the Account_Owner, with the `invoice_id` and the Billing_Period in the payload. |
| `refunded` | Record the event only. No change to `accounts.status` and no notification. |

A `status` outside this list is logged and returns 204 with no further action, because a retry cannot make the value valid.

## Resolving invoice_id to accountId

The receiver has no way to find an Account from an `invoice_id` today, because the `accounts` table holds no invoice data. The invoice-issuing flow is out of scope and already knows the Account for each invoice. This feature adds one DynamoDB table, `invoice-accounts`, with PK `invoiceId` and an `accountId` attribute, which that flow writes when it issues an invoice. The receiver only reads it.

An `invoice_id` with no row in `invoice-accounts` is logged and returns 204 with no further action, because a retry cannot make the row appear.
