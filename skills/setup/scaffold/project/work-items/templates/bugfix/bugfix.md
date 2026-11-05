<!--
  Bugfix Document Template (SDD Bugfix Pipeline)

  Produced by: sdd:spec-generator bugfix mode
  Replaces: requirements.md (bugfix specs use bugfix.md instead)

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real spec.

  The Unchanged Behaviour section is the critical part: it tells the
  agent what NOT to touch. Surgical fixes depend on this being thorough.
-->

# Bugfix: [Bug Title]

## Summary

<!--
  One-paragraph description of the bug:
  - What is the user-visible symptom?
  - When does it occur?
  - Severity / scope of impact
-->

[Brief description of the bug, its symptom, scope, and severity.]

## Reproduction

<!--
  Steps to reliably reproduce the bug.
  Include environment details if relevant (browser, OS, account type, data state).
-->

1. [Step 1]
2. [Step 2]
3. [Step 3]
4. Observed: [incorrect behaviour]
5. Expected: [correct behaviour]

**Environment:** [browser, OS, account type, etc.]

## Current Behaviour (Defect)

<!--
  Document the broken behaviour using EARS notation.
  Each statement describes what the system does today (incorrectly).
-->

1.1 WHEN [condition/event], THE [Component] [incorrect behavior]
1.2 WHEN [condition/event], THE [Component] [incorrect behavior]

## Expected Behaviour (Correct)

<!--
  Document the correct behaviour using EARS notation.
  Each statement is numbered to match the corresponding Current Behaviour entry.
  These N.M IDs are referenced downstream by tasks (_Requirements: N.M_)
  and by the implementation audit.
-->

2.1 WHEN [condition/event], THE [Component] SHALL [correct behavior]
2.2 WHEN [condition/event], THE [Component] SHALL [correct behavior]

## Unchanged Behaviour (Regression Prevention)

<!--
  CRITICAL SECTION — explicitly document behaviour that must NOT change.
  This locks down adjacent functionality and prevents the fix from
  introducing regressions. Be thorough — list every related behaviour
  that could plausibly be affected by the fix.
-->

3.1 WHEN [condition/event], THE [Component] SHALL CONTINUE TO [existing behavior]
3.2 WHEN [condition/event], THE [Component] SHALL CONTINUE TO [existing behavior]
3.3 THE [Component] SHALL CONTINUE TO [existing behavior]

## Affected Components

<!--
  List of components, files, or services involved in the bug or fix.
  Helps the design phase scope the root cause investigation.
-->

- [Component / file path]
- [Component / file path]

## Out of Scope

<!--
  Explicitly list nearby concerns that are NOT part of this fix.
  Prevents scope creep during implementation.
-->

- [Related issue that is intentionally not addressed here]
- [Refactor that is tempting but deferred]
