# Bugfix Pipeline

The light SDD pipeline for bug fixes. Three steps, no quality gates, no audits.

## Pipeline overview

```
spec-generator (bugfix mode) ──► bugfix.md, design.md, tasks.md
    │
    ▼
subagent-driven-development ──► implementation
    │
    ▼
finishing-a-development-branch ──► PR
```

## Why no gates?

Bug fixes are surgical by definition. The work has a known target: identified defect → root cause → fix → regression check. Quality gates add overhead without catching issues the spec format already prevents.

The bugfix spec format itself encodes regression protection: it requires explicit documentation of behaviour that must NOT change.

## Steps

### 1. spec-generator (bugfix mode)

Generates three documents in sequence within `project/work-items/{NN}-{name}/`:

#### bugfix.md

Captures the bug in three sections:

- **Current behaviour (defect)** — `WHEN [condition] THEN the system [incorrect behavior]`
- **Expected behaviour (correct)** — `WHEN [condition] THEN the system SHALL [correct behavior]`
- **Unchanged behaviour (regression prevention)** — `WHEN [condition] THEN the system SHALL CONTINUE TO [existing behavior]`

The unchanged section is the critical part: it tells the agent what NOT to touch.

#### design.md

Root cause analysis, fix approach, correctness properties to test for:

- Current implementation produces incorrect behaviour (validates the bug exists)
- Fixed implementation produces correct behaviour (validates the fix works)
- Unchanged implementation continues working (prevents regressions)

#### tasks.md

Implementation tasks with regression tests. The unchanged-behaviour section drives test cases that lock down existing functionality before the fix is applied.

User reviews each document and approves before the next is generated.

### 2. subagent-driven-development (superpowers)

Standard implementation phase. Same as feature pipeline.

### 3. finishing-a-development-branch (superpowers)

Creates the PR. No maintenance step — bug fixes rarely supersede other specs.

## When to use the bugfix pipeline

- Bug is in a critical code path where regressions are costly
- Previous fix attempts caused regressions
- The root cause isn't immediately obvious
- The fix needs documentation for compliance or team knowledge

For trivial typos, one-line obvious fixes, or exploratory hacks — skip SDD entirely and just make the change.

## When NOT to use the bugfix pipeline

If root cause analysis reveals the "bug" actually requires significant new functionality, abandon the bugfix spec and start a feature spec. Don't try to convert one to the other.
