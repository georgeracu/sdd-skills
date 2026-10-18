# Design Document: Usage Export

## Components and Interfaces

### ExportService
- Writes one CSV row per day to the `exports` bucket
- Expires files after seven days through a bucket lifecycle rule
