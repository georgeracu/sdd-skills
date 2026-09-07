---
name: spec-maintenance
description: Use after sdd:spec-qa-review review mode passes to detect whether the just-completed implementation impacts any previously implemented specs. Strikes through and annotates affected tasks, design elements, and requirements in older work items.
---

# Spec Maintenance

After a spec is fully implemented and QA-reviewed, check whether it invalidates or changes anything in previously implemented specs. Keep historical specs honest by marking what changed.

**Workflow Position:** Runs AFTER `sdd:spec-qa-review` review mode passes and BEFORE `finishing-a-development-branch`.

## Prerequisites

1. Current spec has passed `sdd:spec-qa-review` review mode
2. At least one other implemented work item exists in `project/work-items/`

If no other implemented work items exist, skip this skill and proceed to `finishing-a-development-branch`.

## What "Implemented" Means

A work item is considered implemented if its `project/work-items/{NN}-{name}/` directory contains an `implementation-audit.md` file. Work items still in progress (no audit file) are excluded from analysis.

## Process

### 1. Identify Scope

- Read the current spec's `requirements.md`, `design.md`, and `tasks.md`
- List all other implemented work items (have `implementation-audit.md`)
- Build a summary of what the current spec changed: new/modified endpoints, data models, components, business rules, infrastructure

### 2. Search for Impacts

Use `codebase-search` skill to search documentation efficiently. For each change in the current spec, query previous specs for overlapping concerns:

- **Endpoints**: Search for API paths that the current spec added, modified, or removed
- **Data models**: Search for entity names, table names, field names that changed
- **Components**: Search for component names, service names, handler names that were modified
- **Business rules**: Search for behaviour descriptions that the current implementation altered
- **Infrastructure**: Search for resource names, configuration keys that changed

For each hit, read the specific section of the previous spec to determine if it's actually impacted.

### 3. Classify Impact Type

For each impacted item, classify it:

**Superseded** — the item is no longer accurate because the current spec replaced it entirely.
- Mark with strikethrough + annotation
- Example: `~~Task 3: Implement prompt template loader~~ *(superseded by work-item 36)*`

**Modified** — the item is partially accurate but specific details changed.
- Keep the item, annotate the specific changed properties
- Example: `- Response includes ~~categoryId~~ `classificationId` field *(renamed in work-item 36)*`

**Deprecated** — the item describes functionality that still exists but is scheduled for removal or is no longer the recommended approach.
- Mark with annotation only (no strikethrough)
- Example: `- Task 5: Implement legacy prompt format *(deprecated — see work-item 36 for new approach)*`

### 4. Apply Annotations

For each impacted previous spec, edit the actual files:

**In `tasks.md`:**
```markdown
# Before
- [x] Task 3: Implement prompt template loader

# After — fully superseded
- [x] ~~Task 3: Implement prompt template loader~~ *(superseded by work-item 36)*

# After — partially modified (annotate sub-items, not the task itself)
- [x] Task 3: Implement prompt template loader
  - ~~Loads templates from S3 bucket~~ *(changed to DynamoDB in work-item 36)*
```

**In `design.md`:**
```markdown
# Before
### Prompt Service
- Loads templates from S3
- Caches templates for 5 minutes

# After — specific property changed
### Prompt Service
- ~~Loads templates from S3~~ *(moved to DynamoDB in work-item 36)*
- Caches templates for 5 minutes
```

**In `requirements.md`:**
```markdown
# Before
**AC 2.1:** System shall load prompt templates from S3

# After
**AC 2.1:** ~~System shall load prompt templates from S3~~ *(superseded by work-item 36 — templates now in DynamoDB)*
```

### 5. Generate Maintenance Report

Write `project/work-items/{current-spec}/spec-maintenance-report.md`:

```markdown
# Spec Maintenance Report: {Current Spec Name}

**Date:** YYYY-MM-DD
**Triggered by:** work-item {NN}-{name}

## Impacted Work Items

| Work Item | Files Changed | Impacts |
|-----------|--------------|---------|
| {NN}-{name} | tasks.md, design.md | 3 superseded, 1 modified |
| {NN}-{name} | design.md | 1 modified |

## Impact Details

### work-item {NN}-{name}

#### tasks.md
- **Task 3** — SUPERSEDED: Prompt template loader replaced by new approach
- **Task 5, sub-item 2** — MODIFIED: S3 path changed to DynamoDB table name

#### design.md
- **Prompt Service > template loading** — MODIFIED: Storage backend changed from S3 to DynamoDB

### work-item {NN}-{name}
...

## No Impact

Work items analysed but not impacted:
- {NN}-{name} — no overlapping concerns
```

If no work items were impacted, the report should state that clearly:

```markdown
## Result

No previously implemented specs were impacted by this implementation.
```

## Granularity Rules

- **Never strike through an entire spec file.** If a whole spec is obsolete, that's a new spec's job to declare, not this skill's.
- **Strike through the smallest unit that changed.** A single bullet point, a single acceptance criterion, a single sub-task — not the parent heading or task.
- **Always include the work-item reference.** Every annotation must say which work item caused the change.
- **Preserve git-blame usefulness.** Make minimal edits — don't reformat surrounding text.

## Severity

This skill does not have CRITICAL/MAJOR/MINOR severity levels or gate behaviour. It always passes. Stale specs are informational, not blocking.

If the number of impacts is large (>10 items across multiple specs), warn the user — this may indicate architectural drift that deserves attention.

## Next Step

After completing the maintenance report, invoke `finishing-a-development-branch`.
