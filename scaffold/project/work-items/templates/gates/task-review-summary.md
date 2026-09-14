<!--
  Task Review Summary Template

  Produced by: sdd:spec-task-review (quality gate)
  Reviews: requirements.md + design.md + tasks.md
  Severity scale: CRITICAL | MAJOR | MINOR

  On APPROVED, this gate deletes brainstorm.md from the work-item directory.

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real summary.
-->

# Task Review Summary: [Spec Name]

**Review Date:** [YYYY-MM-DD]
**Status:** [APPROVED | APPROVED WITH ISSUES | BLOCKED]

**Statistics:**
- Requirements checked: [N]
- Tasks reviewed: [N]
- Critical: [N] | Major: [N] | Minor: [N]

---

## Coverage Matrix

<!--
  One row per acceptance criterion. Every AC must trace to at least one task.
  Missing coverage is CRITICAL.
-->

| Requirement | Task(s) | Status |
|------------|---------|--------|
| AC 1.1     | Task 2  | ✅     |
| AC 1.2     | Task 2, Task 5 | ✅ |
| AC 1.3     | —       | ❌     |
| AC 2.1     | Task 3  | ✅     |

## Design Coverage

<!--
  Every component, interface, endpoint, and data model in design.md
  must have at least one implementing task.
-->

| Design Element | Task(s) | Status |
|----------------|---------|--------|
| [Component A] | Task 2 | ✅ |
| [Endpoint POST /...] | Task 1, Task 3 | ✅ |

## Sequencing Review

[Notes on task ordering, dependency correctness, and any flagged issues]

- ✅ OpenAPI updated before handler implementation
- ✅ Backend complete before frontend integration
- ⚠️ [Issue if any]

## Implementability Review

[Notes on whether tasks are self-contained and unambiguous]

- ✅ All file paths reference real or clearly-marked-as-new locations
- ✅ Task scope is realistic for single-subagent execution
- ⚠️ [Issue if any]

## Mandatory Ending Tasks

- [✓ / ✗] Integration test task present
- [✓ / ✗] E2E test task present
- [✓ / ✗] Documentation update task present
- [✓ / ✗] Final validation task present

---

## Issues Found

### CRITICAL-NNN: [Title]
**Issue:** [Description]
**Affected:** [Which AC or design element]
**Fix:** [Specific action to take in tasks.md]

### MAJOR-NNN: [Title]
**Issue:** [Description]
**Fix:** [Specific action]

### MINOR-NNN: [Title]
**Issue:** [Description]
**Recommendation:** [Optional fix]

---

## Verdict

[APPROVED / APPROVED WITH ISSUES / BLOCKED — with reasoning]

## Next Steps

- **If APPROVED:** brainstorm.md deleted. Proceed to `sdd:spec-qa-review` plan mode.
- **If APPROVED WITH ISSUES:** Address Major issues if possible, then proceed to plan mode.
- **If BLOCKED:** Fix tasks.md per the issues above. Re-run `sdd:spec-task-review`. If blocked twice, escalate to user.
