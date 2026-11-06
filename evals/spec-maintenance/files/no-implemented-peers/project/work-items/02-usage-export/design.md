# Design Document: Usage Export

## Components and Interfaces

### ExportService
- Writes one CSV row per day to the `exports` bucket
- Reads the Plan Quota for the last CSV column through the Quota_Cache with a TTL of one hour
- Notification_Service sends the `export-ready` template to the Account_Owner when the file is ready
