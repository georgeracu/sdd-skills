# Eval results — iteration-15

**What this iteration tests:** #37 fixture round 10 + MINOR grounding: cases 1, 5 against 4203e9a

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.312 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 490359 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 6c7acb9916490b92 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 88bf4d5d36d5a4885a5a10301c8c2a762856ed201b585c2082d108ea68a0bdb6 |
| bundle_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| snapshot_hash | f031cd22c342d6b211a19ff7bb08324b5b19475ce6c699afb28281767608e18a |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8333 (30/36), B: 0.8056 (29/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=2) | 209.5537 (±35.9897, n=2) | 388,944 (±43,563, n=2) |
| old_skill | 0.695 (±0.0999, n=2) | 211.8977 (±15.4553, n=2) | 367,716 (±21,899, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.3050, time_seconds -2.34, tokens +21,227

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0674 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `aligned-design-approved` — Output includes: Major discrepancies: 0: 3/3 vs 2/3
- `aligned-design-approved` — Output does not include: MAJOR-001: 3/3 vs 2/3
- `aligned-design-approved` — Output does not include: MINOR-001: 3/3 vs 0/3
- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 3/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 3/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics: 3/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned: 3/3 vs 0/3
- `bugfix-design-approved` — Output does not include: MAJOR-001: 3/3 vs 2/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 3/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected: 3/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| aligned-design-approved | old_skill | 25 | 17 | 9/14, 9/14, 7/14 |
| bugfix-design-approved | with_skill | 39 | 0 | 13/13, 13/13, 13/13 |
| bugfix-design-approved | old_skill | 31 | 8 | 10/13, 10/13, 11/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 3/3 | 2/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 3/3 | 2/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 3/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 0/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 3/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 3/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 3/3 | 0/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 3/3 | 2/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 0/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 3/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
