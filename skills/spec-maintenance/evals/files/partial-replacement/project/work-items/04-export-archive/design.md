# Design Document: Export Archive

## Overview

The ExportService keeps its loader and CSV writer and changes only the bucket it writes to.

## Components and Interfaces

### ExportService
- Loads the day's Usage_Meter rows, unchanged
- Writes the CSV file to the `usage-archive` bucket instead of the `exports` bucket
- The seven day lifecycle rule moves from the `exports` bucket to the `usage-archive` bucket

## Testing Strategy

Integration-test that a new export lands in `usage-archive` and that nothing lands in `exports`.
