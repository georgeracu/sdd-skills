package com.example.billing.invoice

/** What a BillingAccount owes for one Billing_Period. */
data class Invoice(val billingAccountId: String, val billingPeriod: String, val lines: List<InvoiceLine>)

data class InvoiceLine(val description: String, val amountPence: Long)
