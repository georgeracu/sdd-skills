---
name: spec-maintenance
description: After sdd:spec-qa-review review mode passes, annotate older implemented work items that the new item supersedes, modifies or deprecates, and write spec-maintenance-report.md; skips when no other work item holds a PASSED implementation-audit.md.
---

# Spec Maintenance

After a spec is fully implemented and QA-reviewed, check whether it invalidates or changes anything in previously implemented specs.

**Workflow Position:** Runs AFTER `sdd:spec-qa-review` review mode passes and BEFORE `finishing-a-development-branch`.

## Peer Set

The peer set is every directory in `project/work-items/` other than the current item that holds an `implementation-audit.md` with `**Status:** PASSED`. The current item's own audit does not count. A work item with no audit, or with an audit of any other status, is not a peer.

If the peer set is empty, skip this skill: write no report, edit no file, say that no implemented peer exists, and invoke `finishing-a-development-branch`. A skipped run counts as a pass.

## Process

### 1. Identify Scope

- Read the current spec's `requirements.md`, `design.md`, and `tasks.md`
- List the peer set
- Build a summary of what the current spec changed: new/modified endpoints, data models, components, business rules, infrastructure

### 2. Search for Impacts

Search only the peer set's directories. For each change in the current spec, query previous specs for overlapping concerns:

- **Endpoints**: Search for API paths that the current spec added, modified, or removed
- **Data models**: Search for entity names, table names, field names that changed
- **Components**: Search for component names, service names, handler names that were modified
- **Business rules**: Search for behaviour descriptions that the current implementation altered
- **Infrastructure**: Search for resource names, configuration keys that changed

For each hit, read the section of the peer spec that holds it before you classify it. Classify only items from sections you read.

The search is complete when every peer work item is either annotated or listed under No Impact.

### 3. Classify Impact Type

For each impacted item, classify it. If any part of the item survives, classify it Modified and strike through only the changed words. Superseded needs the whole item gone. A part that moves to a new place survives: a rule moved to another bucket is Modified, not Superseded.

**Superseded** — the item is no longer accurate because the current spec replaced it entirely.
- Mark with strikethrough + annotation
- Example: `~~Task 3: Implement prompt template loader~~ *(superseded by work-item 36)*`

**Modified** — the item is partially accurate but specific details changed.
- Keep the item, annotate the specific changed properties
- Example: `- Response includes ~~categoryId~~ `classificationId` field *(modified in work-item 36, renamed)*`

**Deprecated** — the item describes functionality that still exists but is scheduled for removal or is no longer the recommended approach.
- Mark with annotation only (no strikethrough)
- Example: `- Task 5: Implement legacy prompt format *(deprecated, see work-item 36)*`

### 4. Apply Annotations

Every annotation has the shape `*(<verb> work-item NN)*` or `*(<verb> work-item NN, <short reason>)*`. The verb is one of `superseded by`, `modified in` or `deprecated, see`. NN is the work item number alone, and the reason follows a comma, never a dash.

Edit the line in place in whichever of `tasks.md`, `design.md` or `requirements.md` holds it. In a partly modified task, annotate the sub-item that changed, not the task line.

### 5. Generate Maintenance Report

Write `project/work-items/{NN}-{name}/spec-maintenance-report.md`. Use `project/work-items/templates/gates/spec-maintenance-report.md` as the structure when it exists; otherwise use the outline below.

```markdown
# Spec Maintenance Report: {Current Spec Name}

**Date:** YYYY-MM-DD
**Triggered by:** work-item {NN}-{name}
**Status:** PASSED

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

- **Strike through the smallest unit that changed.** A single bullet point, a single acceptance criterion, a single sub-task — not the parent heading or task.
- **Always include the work-item reference.** Every annotation must say which work item caused the change.
- **Preserve git-blame usefulness.** Make minimal edits — don't reformat surrounding text.

## Severity

Result: always PASSED. Stale specs are informational. After the report, invoke `finishing-a-development-branch`.

Count one impact per Impact Details bullet, however many lines it annotates. A task with its sub-items, one acceptance criterion or one design bullet is one bullet. If the count is over 10 across two or more work items, fill the report's `## Architectural Drift Warning` section and repeat the warning in the closing reply. Otherwise leave that section out of the report.
