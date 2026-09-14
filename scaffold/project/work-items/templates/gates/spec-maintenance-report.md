<!--
  Spec Maintenance Report Template

  Produced by: sdd:spec-maintenance
  Triggered by: sdd:spec-qa-review review mode passing

  This skill always "passes" — stale spec annotations are informational,
  not blocking. The report documents which prior specs were touched
  and what was annotated.

  Annotations go directly into older specs' tasks.md / design.md /
  requirements.md with strikethrough + work-item reference.

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real report.
-->

# Spec Maintenance Report: [Current Spec Name]

**Date:** [YYYY-MM-DD]
**Triggered by:** work-item [NN]-[name]

---

## Summary

[1-2 sentences describing what the current spec changed and how it affected previously implemented specs.]

## Scope

What the current spec introduced or modified:

- [New/modified endpoint, e.g., `POST /api/v1/...`]
- [New/modified data model, e.g., `ScanResult` schema]
- [Modified component, e.g., `PromptService`]
- [Changed business rule, e.g., classification confidence threshold]
- [Modified infrastructure, e.g., one storage service replaced with another]

---

## Impacted Work Items

| Work Item        | Files Changed             | Impacts                          |
|------------------|---------------------------|----------------------------------|
| [NN]-[name]      | tasks.md, design.md       | 3 superseded, 1 modified         |
| [NN]-[name]      | design.md                 | 1 modified                       |
| [NN]-[name]      | requirements.md           | 1 deprecated                     |

---

## Impact Details

### work-item [NN]-[name]

#### tasks.md
- **Task 3** — SUPERSEDED: [reason]
- **Task 5, sub-item 2** — MODIFIED: [what changed]

#### design.md
- **[Section heading] > [specific element]** — MODIFIED: [what changed]

### work-item [NN]-[name]

#### requirements.md
- **AC 2.1** — DEPRECATED: [reason — functionality still exists but replaced going forward]

---

## No Impact

Work items analysed but not impacted:

- [NN]-[name] — no overlapping concerns
- [NN]-[name] — no overlapping concerns

---

## Architectural Drift Warning

<!--
  Include this section only if >10 items were impacted across multiple
  specs. Suggests the current spec may have broader implications than
  a single work item normally would.
-->

[Optional — only when impacts are large enough to warrant attention.]

---

## Result

[If at least one impact: "N items annotated across M work items."]
[If no impact: "No previously implemented specs were impacted by this implementation."]

## Next Step

Proceed to `finishing-a-development-branch`.
