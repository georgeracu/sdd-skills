# Invoice Reminder Brainstorm

Agreed direction: send Account_Owners an email reminder seven days before an unpaid invoice is due. Use the existing Notification_Service and invoices data; do not add a new table. A daily scheduled job may query due invoices. The reminder must be idempotent per invoice and Billing_Period, and cancelled or paid invoices must be skipped.
