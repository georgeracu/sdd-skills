# Eval results — iteration-25

**What this iteration tests:** #37 and #56 diagnostic: cases 1 and 5, 3 runs an arm, check 3.7 on fix-56 against the main skill, outputs kept to class each finding

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.4.0 |
| adapter | claude_code |
| runtime_version | 2.1.325 (Claude Code) |
| runner_model | claude-sonnet-5-5 |
| runner_model_canonical | claude-sonnet-5-5 |
| runner_model_requested | claude-sonnet-5-5 |
| judge_model | claude-opus-5-5 |
| judge_model_requested | claude-opus-5-5 |
| judge_tokens | 406784 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 6a25a22a6ceae37d |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 0e0099b75ea0e6024fe675c9917bc98fad2a2cfcdd4b3560f0e31a062049226b |
| bundle_hash | 37720cd00677ab8b9551af21e55d4adfbfb651c2ed0740dce65e58dc22bd8c74 |
| snapshot_hash | 5bba6ba4e0642a2c5c09f507638103a01e309055bad93b35c3f926843ccffdb1 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8889 (32/36), B: 0.9444 (34/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8975 (±0.1025, n=2) | 121.0035 (±8.7298, n=2) | 250,306 (±22,925, n=2) |
| old_skill | 0.9487 (±0.0513, n=2) | 97.6292 (±0.0528, n=2) | 214,767 (±15,455, n=2) |

**Delta (with_skill − old_skill):** pass_rate -0.0512, time_seconds +23.37, tokens +35,539

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1451 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `bugfix-design-approved` — Output does not include: MAJOR-001: 1/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 1/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 1/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| aligned-design-approved | old_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| bugfix-design-approved | with_skill | 31 | 8 | 9/13, 9/13, 13/13 |
| bugfix-design-approved | old_skill | 35 | 4 | 13/13, 12/13, 10/13 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-1 | with_skill | 3 | 0 | 0 |
| eval-1 | old_skill | 3 | 0 | 0 |
| eval-5 | with_skill | 3 | 0 | 0 |
| eval-5 | old_skill | 3 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 3/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 1/3 | 3/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 1/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 1/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 1/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
