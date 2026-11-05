# Requirements Document

## Introduction

Usage exports move from the shared `exports` bucket to a dedicated `usage-archive` bucket with its own encryption key. The export content and its loader do not change.

## Glossary

- **ExportService**: The component that writes the CSV file.
- **Usage_Archive**: The `usage-archive` bucket, encrypted with the `usage-archive` KMS key.

## Requirements

### Requirement 1: Store exports in the Usage_Archive

**User Story:** As a security officer, I want usage exports in a bucket with its own key, so that access to them is audited separately.

#### Acceptance Criteria

1. WHEN the ExportService writes a CSV file, THE ExportService SHALL store it in the `usage-archive` bucket instead of the `exports` bucket.
2. THE `usage-archive` bucket SHALL expire files after seven days through a bucket lifecycle rule.
3. THE ExportService SHALL keep loading the day's Usage_Meter rows and writing one CSV row per day, unchanged.
