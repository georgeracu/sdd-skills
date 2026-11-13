# Eval results — iteration-22

**What this iteration tests:** #37 round 3: full pack after case 5 fixture rounds 11 and 12, skill at main against no skill, not bare (user CLAUDE.md reaches the arms)

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.322 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 2539119 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a76f3129ea198e1c |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | fa9ea33e4fce3aedcebd4ee62d6d0c2ce56d85b5dbeaf2458b9e4ce630103c46 |
| bundle_hash | 5bba6ba4e0642a2c5c09f507638103a01e309055bad93b35c3f926843ccffdb1 |
| snapshot_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| input_drift_allowed | none |
| cases_total | 9 |
| cases_included | 9 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.4788 (79/165), B: 0.6727 (111/165) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7633 (±0.1708, n=9) | 178.4076 (±42.98, n=9) | 407,098 (±54,051, n=9) |
| without_skill | 0.3484 (±0.1834, n=9) | 143.9323 (±22.8227, n=9) | 293,981 (±14,733, n=9) |

**Delta (with_skill − without_skill):** pass_rate +0.4149, time_seconds +34.48, tokens +113,117

*measured over 9 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4714 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `requirement-without-design-blocked` — Output does not include: Critical discrepancies: 0: 2/3 vs 3/3
- `requirement-without-design-blocked` — Meets criterion: Raises a critical discrepancy that names Requirement 3 (suspended accounts) as having no design coverage, citing ACs 3.1 and 3.2: 2/3 vs 3/3
- `requirement-without-design-blocked` — Meets criterion: Confirms that Requirements 1, 2, 4 and 5 are covered rather than flagging them: 2/3 vs 3/3
- `bugfix-design-approved` — Output does not include: MAJOR-001: 1/3 vs 2/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 0/3 vs 1/3

with_skill scored above baseline:

- `aligned-design-approved` — Output includes: Critical discrepancies: 0: 2/3 vs 0/3
- `aligned-design-approved` — Output includes: Major discrepancies: 0: 2/3 vs 0/3
- `aligned-design-approved` — Output includes: **Status:** APPROVED: 2/3 vs 0/3
- `aligned-design-approved` — Output does not include: CRITICAL-001: 2/3 vs 1/3
- `aligned-design-approved` — Output does not include: MAJOR-001: 2/3 vs 1/3
- `aligned-design-approved` — Run produced file: design-review-summary.md: 2/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 2/3 vs 0/3
- `requirement-without-design-blocked` — Output includes: CRITICAL-001: 2/3 vs 0/3
- `requirement-without-design-blocked` — Output includes: **Status:** BLOCKED: 2/3 vs 0/3
- `requirement-without-design-blocked` — Run produced file: design-review-summary.md: 2/3 vs 0/3
- `requirement-without-design-blocked` — Meets criterion: Gives an actionable fix, such as a status check in the evaluator with a correctness property and a test, and names the design author as owner: 2/3 vs 0/3
- `requirement-without-design-blocked` — Meets criterion: Stops the workflow: does not generate tasks.md and tells the author to fix design.md and re-run the review: 2/3 vs 1/3
- `naming-drift-major` — Output includes: MAJOR-001: 1/3 vs 0/3
- `naming-drift-major` — Output includes: Critical discrepancies: 0: 2/3 vs 0/3
- `naming-drift-major` — Output includes: **Status:** APPROVED WITH ISSUES: 2/3 vs 0/3
- `naming-drift-major` — Run produced file: design-review-summary.md: 2/3 vs 0/3
- `naming-drift-major` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 2/3 vs 0/3
- `naming-drift-major` — Meets criterion: Reports no critical discrepancies: 2/3 vs 1/3
- `naming-drift-major` — Meets criterion: Raises a major discrepancy for the drifted identifier UsageCounterRepository against Usage_Meter and UsageMeterRepository; raising usageCount against metered is optional: 1/3 vs 0/3
- `naming-drift-major` — Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first: 2/3 vs 0/3
- `synchronous-ses-call-blocked` — Output includes: CRITICAL-001: 3/3 vs 0/3
- `synchronous-ses-call-blocked` — Output includes: **Status:** BLOCKED: 3/3 vs 0/3
- `synchronous-ses-call-blocked` — Run produced file: design-review-summary.md: 3/3 vs 0/3
- `synchronous-ses-call-blocked` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 3/3 vs 1/3
- `synchronous-ses-call-blocked` — Meets criterion: Overall status is BLOCKED: 3/3 vs 0/3
- `synchronous-ses-call-blocked` — Meets criterion: Fixes it by routing the quota-threshold template id and payload through the Notification_Service, with the design author as owner: 3/3 vs 0/3
- `synchronous-ses-call-blocked` — Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run: 3/3 vs 0/3
- `bugfix-design-approved` — Output includes: Critical discrepancies: 0: 2/3 vs 0/3
- `bugfix-design-approved` — Output includes: bugfix.md: 3/3 vs 1/3
- `bugfix-design-approved` — Output includes: **Status:** APPROVED: 2/3 vs 0/3
- `bugfix-design-approved` — Run produced file: design-review-summary.md: 3/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item: 3/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed: 3/3 vs 1/3
- `bugfix-design-approved` — Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected: 3/3 vs 1/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 2/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 2/3 vs 0/3
- `prose-only-drift-minor` — Output includes: Critical discrepancies: 0: 3/3 vs 0/3
- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 3/3 vs 0/3
- `prose-only-drift-minor` — Output does not include: CRITICAL-001: 3/3 vs 2/3
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 3/3 vs 2/3
- `prose-only-drift-minor` — Run produced file: design-review-summary.md: 3/3 vs 0/3
- `prose-only-drift-minor` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 3/3 vs 2/3
- `prose-only-drift-minor` — Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency: 3/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 2/3 vs 0/3
- `prose-only-drift-minor` — Meets criterion: Allows continuation to tasks.md generation: 3/3 vs 0/3
- `latency-constraint-unsupported-blocked` — Output includes: CRITICAL-001: 2/3 vs 0/3
- `latency-constraint-unsupported-blocked` — Output includes: **Status:** BLOCKED: 2/3 vs 0/3
- `latency-constraint-unsupported-blocked` — Run produced file: design-review-summary.md: 2/3 vs 0/3
- `latency-constraint-unsupported-blocked` — Meets criterion: Overall status is BLOCKED: 2/3 vs 0/3
- `latency-constraint-unsupported-blocked` — Meets criterion: Does not downgrade the constraint to a minor missing performance target: 2/3 vs 1/3
- `latency-constraint-unsupported-blocked` — Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run: 2/3 vs 0/3
- `unauthenticated-read-blocked` — Output includes: CRITICAL-001: 3/3 vs 0/3
- `unauthenticated-read-blocked` — Output includes: ownerEmail: 3/3 vs 2/3
- `unauthenticated-read-blocked` — Output includes: **Status:** BLOCKED: 3/3 vs 0/3
- `unauthenticated-read-blocked` — Output does not include: Critical discrepancies: 0: 3/3 vs 2/3
- `unauthenticated-read-blocked` — Run produced file: design-review-summary.md: 3/3 vs 0/3
- `unauthenticated-read-blocked` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 3/3 vs 1/3
- `unauthenticated-read-blocked` — Meets criterion: Overall status is BLOCKED: 3/3 vs 0/3
- `unauthenticated-read-blocked` — Meets criterion: Raises a critical discrepancy that the thresholds read is registered without the authoriser: 3/3 vs 2/3
- `unauthenticated-read-blocked` — Meets criterion: Names the ownerEmail exposure in the response as part of the security finding: 3/3 vs 2/3
- `unauthenticated-read-blocked` — Meets criterion: Fixes it by keeping the route behind the authoriser and removing ownerEmail from ThresholdSet, with the design author as owner: 3/3 vs 1/3
- `unauthenticated-read-blocked` — Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run: 3/3 vs 0/3
- `second-block-escalates` — Output includes: **Status:** BLOCKED: 3/3 vs 0/3
- `second-block-escalates` — Meets criterion: Stops and asks the user to decide, rather than only telling the author to fix design.md and re-run the review: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 24 | 18 | 14/14, 1/14, 9/14 |
| aligned-design-approved | without_skill | 8 | 34 | 6/14, 1/14, 1/14 |
| requirement-without-design-blocked | with_skill | 20 | 10 | 10/10, 0/10, 10/10 |
| requirement-without-design-blocked | without_skill | 14 | 16 | 4/10, 6/10, 4/10 |
| naming-drift-major | with_skill | 20 | 13 | 9/11, 11/11, 0/11 |
| naming-drift-major | without_skill | 7 | 26 | 0/11, 3/11, 4/11 |
| synchronous-ses-call-blocked | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| synchronous-ses-call-blocked | without_skill | 13 | 20 | 4/11, 4/11, 5/11 |
| bugfix-design-approved | with_skill | 27 | 12 | 7/13, 9/13, 11/13 |
| bugfix-design-approved | without_skill | 10 | 29 | 0/13, 3/13, 7/13 |
| prose-only-drift-minor | with_skill | 26 | 13 | 9/13, 9/13, 8/13 |
| prose-only-drift-minor | without_skill | 7 | 32 | 4/13, 3/13, 0/13 |
| latency-constraint-unsupported-blocked | with_skill | 22 | 11 | 11/11, 11/11, 0/11 |
| latency-constraint-unsupported-blocked | without_skill | 11 | 22 | 6/11, 0/11, 5/11 |
| unauthenticated-read-blocked | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| unauthenticated-read-blocked | without_skill | 10 | 23 | 5/11, 5/11, 0/11 |
| second-block-escalates | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| second-block-escalates | without_skill | 24 | 6 | 8/10, 8/10, 8/10 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 2/3 | 0/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 2/3 | 0/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 2/3 | 0/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 2/3 | 1/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 2/3 | 1/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 1/3 | 1/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 1/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 2/3 | 0/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 2/3 | 2/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 2/3 | 0/3 |
| requirement-without-design-blocked | Output includes: CRITICAL-001 | 2/3 | 0/3 |
| requirement-without-design-blocked | Output includes: **Status:** BLOCKED | 2/3 | 0/3 |
| requirement-without-design-blocked | Output does not include: Critical discrepancies: 0 | 2/3 | 3/3 |
| requirement-without-design-blocked | Run produced file: design-review-summary.md | 2/3 | 0/3 |
| requirement-without-design-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 2/3 |
| requirement-without-design-blocked | Meets criterion: Overall status is BLOCKED | 2/3 | 2/3 |
| requirement-without-design-blocked | Meets criterion: Raises a critical discrepancy that names Requirement 3 (suspended accounts) as having no design coverage, citing ACs 3.1 and 3.2 | 2/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Gives an actionable fix, such as a status check in the evaluator with a correctness property and a test, and names the design author as owner | 2/3 | 0/3 |
| requirement-without-design-blocked | Meets criterion: Confirms that Requirements 1, 2, 4 and 5 are covered rather than flagging them | 2/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and tells the author to fix design.md and re-run the review | 2/3 | 1/3 |
| naming-drift-major | Output includes: MAJOR-001 | 1/3 | 0/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 2/3 | 0/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 2/3 | 0/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 2/3 | 2/3 |
| naming-drift-major | Run produced file: design-review-summary.md | 2/3 | 0/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 2/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 2/3 | 0/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 2/3 | 1/3 |
| naming-drift-major | Meets criterion: Raises a major discrepancy for the drifted identifier UsageCounterRepository against Usage_Meter and UsageMeterRepository; raising usageCount against metered is optional | 1/3 | 0/3 |
| naming-drift-major | Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 2/3 | 2/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 2/3 | 0/3 |
| synchronous-ses-call-blocked | Output includes: CRITICAL-001 | 3/3 | 0/3 |
| synchronous-ses-call-blocked | Output includes: **Status:** BLOCKED | 3/3 | 0/3 |
| synchronous-ses-call-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Run produced file: design-review-summary.md | 3/3 | 0/3 |
| synchronous-ses-call-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 1/3 |
| synchronous-ses-call-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 0/3 |
| synchronous-ses-call-blocked | Meets criterion: Raises a critical discrepancy that the design calls SES directly and synchronously from the ingest Lambda instead of using the Notification_Service and its SQS queue | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Cites ARCHITECTURE.md: callers pass a template id and payload and never a rendered email, and delivery is asynchronous | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Fixes it by routing the quota-threshold template id and payload through the Notification_Service, with the design author as owner | 3/3 | 0/3 |
| synchronous-ses-call-blocked | Meets criterion: Does not claim a requirements coverage gap, since every requirement is addressed | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 3/3 | 0/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 2/3 | 0/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 1/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 2/3 | 0/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 2/3 | 2/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 1/3 | 2/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 2/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 1/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 1/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 0/3 | 1/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 2/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 2/3 | 0/3 |
| prose-only-drift-minor | Output includes: MINOR-001 | 0/3 | 0/3 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 3/3 | 0/3 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 3/3 | 0/3 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 0/3 | 0/3 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 3/3 | 2/3 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 3/3 | 2/3 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 3/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 2/3 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 0/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 0/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 3/3 | 1/3 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 2/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 3/3 | 0/3 |
| latency-constraint-unsupported-blocked | Output includes: CRITICAL-001 | 2/3 | 0/3 |
| latency-constraint-unsupported-blocked | Output includes: 2.5 | 2/3 | 2/3 |
| latency-constraint-unsupported-blocked | Output includes: **Status:** BLOCKED | 2/3 | 0/3 |
| latency-constraint-unsupported-blocked | Output does not include: Critical discrepancies: 0 | 2/3 | 2/3 |
| latency-constraint-unsupported-blocked | Run produced file: design-review-summary.md | 2/3 | 0/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 2/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Overall status is BLOCKED | 2/3 | 0/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Raises a critical discrepancy naming AC 2.5 (50 ms p99 on the ingest call) as having no design support | 2/3 | 2/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Explains why the design puts it at risk, such as synchronous conditional writes and a send on the ingest path, and gives a concrete fix with a way to verify it | 2/3 | 2/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Does not downgrade the constraint to a minor missing performance target | 2/3 | 1/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 2/3 | 0/3 |
| unauthenticated-read-blocked | Output includes: CRITICAL-001 | 3/3 | 0/3 |
| unauthenticated-read-blocked | Output includes: ownerEmail | 3/3 | 2/3 |
| unauthenticated-read-blocked | Output includes: **Status:** BLOCKED | 3/3 | 0/3 |
| unauthenticated-read-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 2/3 |
| unauthenticated-read-blocked | Run produced file: design-review-summary.md | 3/3 | 0/3 |
| unauthenticated-read-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 1/3 |
| unauthenticated-read-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 0/3 |
| unauthenticated-read-blocked | Meets criterion: Raises a critical discrepancy that the thresholds read is registered without the authoriser | 3/3 | 2/3 |
| unauthenticated-read-blocked | Meets criterion: Names the ownerEmail exposure in the response as part of the security finding | 3/3 | 2/3 |
| unauthenticated-read-blocked | Meets criterion: Fixes it by keeping the route behind the authoriser and removing ownerEmail from ThresholdSet, with the design author as owner | 3/3 | 1/3 |
| unauthenticated-read-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 3/3 | 0/3 |
| second-block-escalates | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| second-block-escalates | Output includes: **Status:** BLOCKED | 3/3 | 0/3 |
| second-block-escalates | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| second-block-escalates | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Raises a critical discrepancy naming Requirement 3 (ACs 3.1 and 3.2) as still having no design support | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Recognises from the previous summary that this is the second BLOCKED run on the same issue | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Stops and asks the user to decide, rather than only telling the author to fix design.md and re-run the review | 3/3 | 0/3 |
| second-block-escalates | Meets criterion: Does not generate tasks.md | 3/3 | 3/3 |
