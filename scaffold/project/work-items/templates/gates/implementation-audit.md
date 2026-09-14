<!--
  Implementation Audit Template

  Produced by: sdd:spec-implementation-audit (quality gate)
  Inputs: requirements.md + design.md + tasks.md + actual codebase
  Severity scale: CRITICAL | MAJOR | MINOR

  The presence of this file is what marks a work item as "implemented" —
  sdd:spec-maintenance uses it to identify implementation-audit candidates.

  Evidence sections must contain ACTUAL pasted output, not claims.

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real audit.
-->

# Implementation Audit: [Spec Name]

**Audit Date:** [YYYY-MM-DD]
**Status:** [PASSED | FAILED]

---

## Per-Requirement Status

<!--
  One row per acceptance criterion.
  Status: ✅ Implemented | ⚠️ Partial | ❌ Missing
-->

| Requirement | AC  | Status        | Evidence                                            |
|-------------|-----|---------------|-----------------------------------------------------|
| Req 1       | 1.1 | ✅ Implemented | Handler at `backend/src/.../Handler.kt:42`         |
| Req 1       | 1.2 | ⚠️ Partial    | Logic implemented but missing error handling for X |
| Req 2       | 2.1 | ❌ Missing    | No code found                                       |

## Task Completion

- Total tasks: [N]
- Completed: [N]
- Skipped (optional): [N]
- Incomplete: [N]

[List any incomplete non-optional tasks here.]

## Documentation Status

- [ ] Architecture docs updated (where applicable)
- [ ] `openapi.yaml` up to date (no diff from the type-generation script)
- [ ] Knowledge base updated (where applicable)
- [ ] Auto-memory updated (where conventions changed)

---

## Discrepancies

### CRITICAL-NNN: [Title]

**Requirement:** [Reference, e.g., AC 1.2]
**Expected:** [What the spec said]
**Actual:** [What the codebase does]
**Action:** [Fix needed]

### MAJOR-NNN: [Title]

**Requirement:** [Reference]
**Expected:** [From spec]
**Actual:** [From codebase]
**Action:** [Fix needed]

### MINOR-NNN: [Title]

**Description:** [Brief note]

---

## Evidence

<!--
  Paste ACTUAL command output here. Do not summarise. Do not claim.
  The whole point of this section is independently verifiable proof.
-->

### Backend Build & Tests

```
[Pasted output from the backend build+test command, e.g. from AGENTS.md]
```

### Frontend Build & Tests

```
[Pasted output from the frontend build+test command, e.g. from AGENTS.md]
```

### E2E Tests

```
[Pasted output from the e2e test command, e.g. from AGENTS.md]
```

### API Type Freshness

```
[Output of the type-generation script showing no diff, or the diff if stale]
```

### Smoke Tests (if applicable)

```
[Pasted output from smoke test run]
```

---

## Verdict

[PASSED / FAILED — with reasoning]

## Next Steps

- **If PASSED:** Proceed to `sdd:spec-qa-review` review mode.
- **If FAILED:** Dispatch fix subagents for the discrepancies above via `subagent-driven-development`. Re-run this audit. If it fails twice, escalate to user.
