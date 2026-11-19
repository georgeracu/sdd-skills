package com.example.billing

/** The party that pays: legal name, VAT number and payment method. Not the Metering Account. */
data class BillingAccount(val id: String, val legalName: String, val vatNumber: String?, val paymentMethodId: String)
