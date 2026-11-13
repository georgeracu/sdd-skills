# Eval results — iteration-21

**What this iteration tests:** #37 round 2: full pack after case 5 fixture rounds 11 and 12, A/A at main, not bare (user CLAUDE.md reaches the arms)

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.318 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 1663869 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 05cc3f880de63074 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 614047698ccabe43172cacbf8fda00076052872e4868b61f935194735540fb8b |
| bundle_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| snapshot_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| input_drift_allowed | none |
| cases_total | 9 |
| cases_included | 7 |
| cases_degraded | latency-constraint-unsupported-blocked, second-block-escalates, unauthenticated-read-blocked |
| judge_failures | none |
| label_pass_rates | A: 0.7863 (92/117), B: 0.7863 (92/117) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7884 (±0.1595, n=7) | 218.4613 (±69.4946, n=7) | 397,766 (±49,443, n=7) |
| old_skill | 0.8784 (±0.1677, n=7) | 208.2162 (±55.0633, n=7) | 435,476 (±55,857, n=7) |

**Delta (with_skill − old_skill):** pass_rate -0.0900, time_seconds +10.25, tokens -37,710

*measured over 7 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4714 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `aligned-design-approved` — Output includes: Major discrepancies: 0: 2/3 vs 3/3
- `aligned-design-approved` — Output does not include: MAJOR-001: 2/3 vs 3/3
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Output includes: CRITICAL-001: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Output includes: **Status:** BLOCKED: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Output does not include: Critical discrepancies: 0: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Run produced file: design-review-summary.md: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Overall status is BLOCKED: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Raises a critical discrepancy that the design calls SES directly and synchronously from the ingest Lambda instead of using the Notification_Service and its SQS queue: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Cites ARCHITECTURE.md: callers pass a template id and payload and never a rendered email, and delivery is asynchronous: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Fixes it by routing the quota-threshold template id and payload through the Notification_Service, with the design author as owner: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Does not claim a requirements coverage gap, since every requirement is addressed: 2/3 vs 3/3
- `synchronous-ses-call-blocked` — Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run: 2/3 vs 3/3
- `bugfix-design-approved` — Output includes: Critical discrepancies: 0: 1/3 vs 3/3
- `bugfix-design-approved` — Output includes: **Status:** APPROVED: 1/3 vs 3/3
- `bugfix-design-approved` — Output does not include: CRITICAL-001: 1/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 1/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 1/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 1/3 vs 3/3
- `prose-only-drift-minor` — Output includes: MINOR-001: 1/3 vs 3/3
- `prose-only-drift-minor` — Output includes: Critical discrepancies: 0: 2/3 vs 3/3
- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 2/3 vs 3/3
- `prose-only-drift-minor` — Output includes: **Status:** APPROVED WITH ISSUES: 1/3 vs 3/3
- `prose-only-drift-minor` — Output does not include: CRITICAL-001: 2/3 vs 3/3
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 2/3 vs 3/3
- `prose-only-drift-minor` — Run produced file: design-review-summary.md: 2/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 2/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 1/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR: 0/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency: 2/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 2/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Allows continuation to tasks.md generation: 2/3 vs 3/3

with_skill scored above baseline:

- `aligned-design-approved` — Output does not include: MINOR-001: 3/3 vs 1/3
- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned: 2/3 vs 1/3
- `naming-drift-major` — Output includes: Critical discrepancies: 0: 3/3 vs 2/3
- `naming-drift-major` — Output includes: **Status:** APPROVED WITH ISSUES: 3/3 vs 2/3
- `naming-drift-major` — Output does not include: CRITICAL-001: 3/3 vs 2/3
- `naming-drift-major` — Run produced file: design-review-summary.md: 3/3 vs 2/3
- `naming-drift-major` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 3/3 vs 2/3
- `naming-drift-major` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 3/3 vs 2/3
- `naming-drift-major` — Meets criterion: Reports no critical discrepancies: 3/3 vs 2/3
- `naming-drift-major` — Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold: 1/3 vs 0/3
- `naming-drift-major` — Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first: 2/3 vs 1/3
- `bugfix-design-approved` — Output does not include: MAJOR-001: 3/3 vs 2/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 35 | 7 | 14/14, 14/14, 7/14 |
| aligned-design-approved | old_skill | 32 | 10 | 14/14, 9/14, 9/14 |
| requirement-without-design-blocked | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| requirement-without-design-blocked | old_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| naming-drift-major | with_skill | 26 | 7 | 8/11, 11/11, 7/11 |
| naming-drift-major | old_skill | 17 | 16 | 0/11, 7/11, 10/11 |
| synchronous-ses-call-blocked | with_skill | 22 | 11 | 11/11, 0/11, 11/11 |
| synchronous-ses-call-blocked | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| bugfix-design-approved | with_skill | 27 | 12 | 13/13, 7/13, 7/13 |
| bugfix-design-approved | old_skill | 36 | 3 | 13/13, 10/13, 13/13 |
| prose-only-drift-minor | with_skill | 21 | 18 | 9/13, 12/13, 0/13 |
| prose-only-drift-minor | old_skill | 37 | 2 | 12/13, 13/13, 12/13 |
| second-block-escalates | with_skill | 10 | 0 | 10/10 |
| second-block-escalates | old_skill | 10 | 0 | 10/10 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 2/3 | 3/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 2/3 | 3/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 3/3 | 1/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 2/3 | 1/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 2/3 | 3/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
| requirement-without-design-blocked | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| requirement-without-design-blocked | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| requirement-without-design-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| requirement-without-design-blocked | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Raises a critical discrepancy that names Requirement 3 (suspended accounts) as having no design coverage, citing ACs 3.1 and 3.2 | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Gives an actionable fix, such as a status check in the evaluator with a correctness property and a test, and names the design author as owner | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Confirms that Requirements 1, 2, 4 and 5 are covered rather than flagging them | 3/3 | 3/3 |
| requirement-without-design-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and tells the author to fix design.md and re-run the review | 3/3 | 3/3 |
| naming-drift-major | Output includes: MAJOR-001 | 1/3 | 1/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 3/3 | 2/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 2/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 3/3 | 2/3 |
| naming-drift-major | Run produced file: design-review-summary.md | 3/3 | 2/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 2/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 3/3 | 2/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 3/3 | 2/3 |
| naming-drift-major | Meets criterion: Raises a major discrepancy for the drifted identifier UsageCounterRepository against Usage_Meter and UsageMeterRepository; raising usageCount against metered is optional | 1/3 | 1/3 |
| naming-drift-major | Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 1/3 | 0/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 2/3 | 1/3 |
| synchronous-ses-call-blocked | Output includes: CRITICAL-001 | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Output includes: **Status:** BLOCKED | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Output does not include: Critical discrepancies: 0 | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Run produced file: design-review-summary.md | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Overall status is BLOCKED | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Raises a critical discrepancy that the design calls SES directly and synchronously from the ingest Lambda instead of using the Notification_Service and its SQS queue | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Cites ARCHITECTURE.md: callers pass a template id and payload and never a rendered email, and delivery is asynchronous | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Fixes it by routing the quota-threshold template id and payload through the Notification_Service, with the design author as owner | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Does not claim a requirements coverage gap, since every requirement is addressed | 2/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 1/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 1/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 1/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 3/3 | 2/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 2/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 1/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 1/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 1/3 | 3/3 |
| prose-only-drift-minor | Output includes: MINOR-001 | 1/3 | 3/3 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 2/3 | 3/3 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 2/3 | 3/3 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 1/3 | 3/3 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 2/3 | 3/3 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 2/3 | 3/3 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 2/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 1/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 0/3 | 1/3 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 2/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 2/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 2/3 | 3/3 |
| second-block-escalates | Output includes: CRITICAL-001 | 1/1 | 1/1 |
| second-block-escalates | Output includes: **Status:** BLOCKED | 1/1 | 1/1 |
| second-block-escalates | Output does not include: Critical discrepancies: 0 | 1/1 | 1/1 |
| second-block-escalates | Run produced file: design-review-summary.md | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Overall status is BLOCKED | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Raises a critical discrepancy naming Requirement 3 (ACs 3.1 and 3.2) as still having no design support | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Recognises from the previous summary that this is the second BLOCKED run on the same issue | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Stops and asks the user to decide, rather than only telling the author to fix design.md and re-run the review | 1/1 | 1/1 |
| second-block-escalates | Meets criterion: Does not generate tasks.md | 1/1 | 1/1 |

## Excluded from the aggregate

These cases did not complete and are not counted above:

- `eval-7`
- `eval-8`
