# External Webhook Contract

The Acme Billing provider will POST exactly this JSON payload to our receiver:

```json
{"event_id":"evt_123","invoice_id":"inv_456","status":"paid","occurred_at":"2026-04-01T12:00:00Z"}
```

The receiver must accept `Content-Type: application/json`, return HTTP 204 with an empty body on success, and return HTTP 400 for malformed JSON or missing fields. The provider retries non-2xx responses, so handling must be idempotent by `event_id` and must not alter the payload field names or response status.
