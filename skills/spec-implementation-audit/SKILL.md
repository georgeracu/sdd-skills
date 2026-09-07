---
name: spec-implementation-audit
description: Use after all implementation tasks are complete to verify the codebase matches the spec. Runs once after subagent-driven-development finishes all tasks. Checks feature completeness, behaviour correctness, edge case handling, task completion, documentation updates, and collects proof of implementation.
---

# Spec Implementation Audit — Quality Gate

Full verification that completed implementation matches the specification. Runs once after all tasks are done.

**Workflow Position:** Runs AFTER `subagent-driven-development` completes all tasks and BEFORE `sdd:spec-qa-review` review mode.

## Prerequisites

Verify these files exist in `project/work-items/{spec-name}/`:

1. `requirements.md` — the source of truth for what was requested
2. `design.md` — the source of truth for how it should be built
3. `tasks.md` — all non-optional tasks should be checked off

## Audit Process

### 1. Feature Exists

For every requirement in `requirements.md`, locate corresponding code in the codebase:

- Frontend components in `frontend/src/`
- Backend handlers in `backend/src/main/kotlin/`
- Infrastructure in CloudFormation templates
- API contracts in `openapi.yaml`

**Flag as CRITICAL:** Any requirement with no corresponding implementation. Partially implemented requirements (code exists but incomplete) are also CRITICAL.

### 2. Behaviour Matches

For each implemented requirement, verify the logic matches the spec:

- API contracts match `design.md` endpoint specifications
- Data models match `design.md` schemas
- Business rules match acceptance criteria
- Error responses match specified error codes and messages

**Flag as CRITICAL:** Implementation contradicts spec.

### 3. Edge Cases Handled

For each requirement's acceptance criteria, verify error states and edge cases:

- Error paths from `requirements.md` are implemented
- Validation logic exists at system boundaries
- Graceful degradation where specified

**Flag as MAJOR:** Missing error handling for specified edge cases.

### 4. Tasks Completed

Read `tasks.md` and verify:

- All non-optional tasks (`- [ ]` without `*` prefix) are checked off (`- [x]`)
- Optional tasks are noted as skipped or completed

**Flag as CRITICAL:** Non-optional tasks still unchecked.

### 5. Documentation Updated

Verify:

- Architecture docs reflect changes (if system design changed)
- `openapi.yaml` is up to date (run `scripts/generate-api-types.sh` and check for diff)
- Knowledge base updated if new patterns were introduced
- Auto-memory updated if project conventions changed

**Flag as MAJOR:** Stale documentation.

### 6. Proof of Implementation

Collect and paste evidence:

- **Test results**: Run `cd backend && ./gradlew build` and `cd frontend && npm run build && npm test`
- **E2E results**: Run `cd e2e && npm test` (if applicable)
- **API type freshness**: Run `scripts/generate-api-types.sh` and verify no diff
- **Build output**: Paste actual command output showing pass/fail

Evidence must be actual pasted output, not claims like "tests pass".

## Severity Levels

- **CRITICAL**: Missing implementation, contradicts spec, uncompleted tasks
- **MAJOR**: Missing edge cases, stale docs, missing evidence
- **MINOR**: Style issues, minor naming inconsistencies

## Output

Write `project/work-items/{spec-name}/implementation-audit.md`:

````
# Implementation Audit: {Spec Name}

**Audit Date:** YYYY-MM-DD
**Status:** PASSED | FAILED

## Per-Requirement Status

| Requirement | AC | Status | Evidence |
|------------|-----|--------|----------|
| Req 1 | 1.1 | ✅ Implemented | Handler at backend/src/.../Handler.kt |
| Req 1 | 1.2 | ❌ Missing | No error handling for X |

## Task Completion

- Total tasks: N
- Completed: N
- Skipped (optional): N
- Incomplete: N

## Discrepancies

### CRITICAL-NNN: [Title]
**Requirement:** [Reference]
**Expected:** [From spec]
**Actual:** [From codebase]
**Action:** [Fix needed]

## Evidence

### Backend Build & Tests
```
[Pasted output from ./gradlew build]
```

### Frontend Build & Tests
```
[Pasted output from npm run build && npm test]
```

### E2E Tests
```
[Pasted output from npm test in e2e/]
```

### API Type Freshness
```
[Output of generate-api-types.sh showing no diff, or diff if stale]
```

## Verdict

[PASSED/FAILED reasoning]
````

## Gate Behaviour

- **PASSED** → proceed to `sdd:spec-qa-review` review mode
- **FAILED** → dispatch fix subagents for specific gaps via `subagent-driven-development`, re-run this audit. If fails twice, ask the user.

## Next Step

On pass → invoke `sdd:spec-qa-review` in review mode.
