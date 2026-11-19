# Architecture

Usage metering service for API customers. Kotlin 2.x on AWS Lambda behind API Gateway, with DynamoDB.

- `backend/src/main/kotlin/com/example/metering/`: usage ingest, the Usage_Meter and Quota checks.
- `backend/src/main/kotlin/com/example/notifications/`: Notification_Service. Publishes to the `notifications` SQS queue; a separate Lambda renders the named template and sends through SES. Callers pass a template id and a payload map.
- `frontend/`: the React Dashboard.
