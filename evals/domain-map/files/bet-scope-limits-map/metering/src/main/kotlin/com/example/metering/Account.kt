package com.example.metering

/** A paying customer, as Metering sees it: API keys and a Plan. */
data class Account(val id: String, val apiKeys: List<String>, val plan: String)
