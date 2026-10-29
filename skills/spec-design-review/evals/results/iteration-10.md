# Eval results — iteration-10

**What this iteration tests:** iteration C (D6 from #25): the gate rule is stated once, in the Step 7 BLOCKED bullet, which now carries stop, fix, re-run and the second-run escalation (an earlier summary BLOCKED on the same issues: stop and ask the user). The Integration BLOCKED block and Gate Failure Behaviour are deleted. Baseline is the B3 skill (D3-D5 with sharpened D4). Fixture round 8: any other error on the firedThresholds write is logged and the threshold stays unfired. Cases 2, 4 and 9.

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.310 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 670675 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a738b26f3cc73f1d |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | ea4c43a7277c34d4eedeaeda9f7219f75614be830e8092586dacfa5766213dd6 |
| bundle_hash | 182c668242bf2135703833a25c0bf5afc30ff24882f2eb4315ccb7534955ece1 |
| snapshot_hash | acda71811ad4f300dde99cde6f2100e53d52091f84905e2915fde4a1b1a497ea |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.9649 (55/57), B: 1.0 (57/57) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=3) | 135.2807 (±40.47, n=3) | 351,375 (±16,645, n=3) |
| old_skill | 0.9778 (±0.0314, n=3) | 139.1253 (±37.7491, n=3) | 331,537 (±20,567, n=3) |

**Delta (with_skill − old_skill):** pass_rate +0.0222, time_seconds -3.84, tokens +19,838

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0471 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `second-block-escalates` — Meets criterion: Stops and asks the user to decide, rather than only telling the author to fix design.md and re-run the review: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirement-without-design-blocked | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| requirement-without-design-blocked | old_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| synchronous-ses-call-blocked | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| synchronous-ses-call-blocked | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| second-block-escalates | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| second-block-escalates | old_skill | 28 | 2 | 9/10, 10/10, 9/10 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
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
| synchronous-ses-call-blocked | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Raises a critical discrepancy that the design calls SES directly and synchronously from the ingest Lambda instead of using the Notification_Service and its SQS queue | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Cites ARCHITECTURE.md: callers pass a template id and payload and never a rendered email, and delivery is asynchronous | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Fixes it by routing the quota-threshold template id and payload through the Notification_Service, with the design author as owner | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Does not claim a requirements coverage gap, since every requirement is addressed | 3/3 | 3/3 |
| synchronous-ses-call-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 3/3 | 3/3 |
| second-block-escalates | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| second-block-escalates | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| second-block-escalates | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| second-block-escalates | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Raises a critical discrepancy naming Requirement 3 (ACs 3.1 and 3.2) as still having no design support | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Recognises from the previous summary that this is the second BLOCKED run on the same issue | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Stops and asks the user to decide, rather than only telling the author to fix design.md and re-run the review | 3/3 | 1/3 |
| second-block-escalates | Meets criterion: Does not generate tasks.md | 3/3 | 3/3 |
