# Eval results — iteration-6

**What this iteration tests:** iteration 6 (A/A fixture check, same skill both arms): fixture round 5. Aligned designs: the evaluator skips thresholds already in firedThresholds (ADD returns ALL_NEW) and treats ConditionalCheckFailedException as a concurrent fire; replace validates before it writes; request body required; rejection test. Bugfix: the key decision no longer says the ingest path is untouched, increment throws IngestUnavailable mapped to 503, the doubled accounts read cost has a basis in ARCHITECTURE.md, the ingest GetItem does not conflict with the transaction, tests for both. Cases 1 and 5.

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
| judge_tokens | 464600 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 5d20ec7eeade4add |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 9d8f27704f26e075a26eb3b62452098721d76e358920b1c960c75146386ac6f4 |
| bundle_hash | 2b397ee51d24aa74b4e7c052f4486f89770bc2e507d882dd243159410f0137c3 |
| snapshot_hash | 2b397ee51d24aa74b4e7c052f4486f89770bc2e507d882dd243159410f0137c3 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.5 (18/36), B: 0.7222 (26/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.6273 (±0.0632, n=2) | 201.3938 (±35.2175, n=2) | 367,177 (±20,917, n=2) |
| old_skill | 0.7317 (±0.0888, n=2) | 188.9313 (±19.324, n=2) | 328,346 (±15,409, n=2) |

**Delta (with_skill − old_skill):** pass_rate -0.1044, time_seconds +12.46, tokens +38,831

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2378 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `bugfix-design-approved` — Output includes: Critical discrepancies: 0: 2/3 vs 3/3
- `bugfix-design-approved` — Output includes: bugfix.md: 2/3 vs 3/3
- `bugfix-design-approved` — Output includes: **Status:** APPROVED: 2/3 vs 3/3
- `bugfix-design-approved` — Run produced file: design-review-summary.md: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 0/3 vs 1/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 1/3 vs 3/3

with_skill scored above baseline:

- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 1/3 vs 0/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 29 | 13 | 9/14, 9/14, 11/14 |
| aligned-design-approved | old_skill | 27 | 15 | 9/14, 9/14, 9/14 |
| bugfix-design-approved | with_skill | 22 | 17 | 10/13, 9/13, 3/13 |
| bugfix-design-approved | old_skill | 32 | 7 | 12/13, 10/13, 10/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 2/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 1/3 | 1/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 0/3 | 1/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 1/3 | 3/3 |
