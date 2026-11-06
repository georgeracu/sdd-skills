# Design Document: Usage Digest

## Overview

A weekly schedule triggers DigestBuilder, which reads each active Account and hands a payload to the Notification_Service.

## Components and Interfaces

### DigestBuilder
- Triggered by the `usage-digest-weekly` EventBridge rule
- Reads `accounts` and `usage-meters` and builds one payload per active Account
- Publishes the `usage-digest` template through the Notification_Service
- Sets the Quota_Cache TTL to five minutes (was one hour)
- Keeps the `export-ready` template working but recommends the digest for announcing exports

## Testing Strategy

Unit-test DigestBuilder; integration-test the schedule against the mock queue.
