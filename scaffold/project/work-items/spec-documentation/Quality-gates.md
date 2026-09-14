# Quality Gates

The feature pipeline has three quality gates. They run between phases and catch issues before they propagate. The bugfix pipeline has no gates.

## The three gates

| Gate | When | Validates |
|---|---|---|
| `spec-design-review` | After `design.md` | Design covers every requirement; decisions are justified |
| `spec-task-review` | After `tasks.md` | Tasks cover every requirement and design element; sequencing is correct; implementability is realistic |
| `spec-implementation-audit` | After implementation | Code matches the spec; all required tasks are checked off; docs are updated |

`spec-qa-review` does not gate in either mode: plan mode runs earlier in the pipeline and produces an artefact rather than gating, setting test expectations before code is written; review mode runs after the implementation audit and reports gaps by severity (Critical / Important / Minor) rather than blocking the pipeline.

## Gate states

Each gate produces one of three outcomes:

- **APPROVED** — all checks pass, pipeline advances
- **APPROVED WITH ISSUES** — minor issues noted, pipeline advances with warnings recorded in the gate's summary file
- **BLOCKED** — gate fails, pipeline pauses

## Fail loop — the two-strike rule

When a gate is BLOCKED:

1. Gate identifies specific gaps or failures
2. Dispatch fix subagents (via `subagent-driven-development`) for the specific issues
3. Re-run the failed gate
4. If the fix loop fails twice on the same gate, **stop and ask the user**

The two-strike rule prevents infinite fix loops. If two rounds of automated fixes haven't resolved the gap, the issue is either ambiguous, contested, or out of scope — and the user needs to decide.

## What each gate produces

### spec-design-review

Output: `design-review-summary.md`

Sections:
- Requirements coverage matrix (which design elements cover which requirements)
- Unjustified design decisions
- Missing non-functional considerations
- Gate verdict

### spec-task-review

Output: `task-review-summary.md`

Sections:
- Requirements coverage check (every AC has at least one task)
- Design coverage check (every component, interface, endpoint has tasks)
- Sequencing review (dependencies are correct)
- Implementability review (file paths real, scope realistic)
- Completeness check (mandatory ending tasks exist)
- Gate verdict

On APPROVED: deletes `brainstorm.md` (no longer needed once tasks are locked in).

### spec-implementation-audit

Output: `implementation-audit.md`

Sections:
- Per-requirement status: implemented / partial / missing
- Discrepancies list (code doesn't match spec)
- Evidence section (pasted test output, build results, API responses)
- Action items for any gaps
- Gate verdict

### spec-qa-review (review mode)

Output: updated `test-plan.md` + gap report in conversation

5-layer coverage matrix:

| Layer | Gate |
|---|---|
| Unit | ≥85% line/branch coverage. Mark N/A with justification for specs with no unit-testable code. |
| Integration | Service boundaries and API contracts tested |
| E2E | Critical user journeys covered |
| Performance | Load assumptions, response time budgets verified |
| Security | Auth boundaries, input validation, OWASP Top 10 relevance |

All five layers analysed every pass. No skipping without explicit N/A justification.

Severity labels for gap log: **Critical / Important / Minor** only. Not High/Medium/Low. Not P0/P1/P2.

## Why gates and not just review pauses

The user already reviews each artefact (`requirements.md`, `design.md`, `tasks.md`) interactively before approving. The gates exist because:

- Humans miss systematic checks (coverage matrices, sequencing dependencies)
- Gates produce documented artefacts that travel with the spec
- Gates dispatch automated fixes for narrow gaps without bothering the user
- The two-strike escalation surfaces real disagreements quickly

## Where to look when a gate fails

The gate's summary file lists specific issues. Don't re-run the gate before addressing them — re-running won't change the verdict.

Common patterns:

| Failure type | Where to fix |
|---|---|
| Missing requirements coverage | Add tasks or design elements; re-run gate |
| Bad task sequencing | Re-order `tasks.md`; re-run `spec-task-review` |
| Implementation gap | Dispatch fix subagent for the specific gap; re-run audit |
| Coverage shortfall | Add tests for the gap; re-run `spec-qa-review` review mode |
