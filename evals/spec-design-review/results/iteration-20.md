# Eval results — iteration-20

**What this iteration tests:** #37 round 2: case 5 fixture round 12 (miss share of peak, repository transaction methods), A/A at main, not bare

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
| judge_tokens | 310279 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | e3d15e3b0295090a |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | f7b096583b7db24361f6436d35c77170a96c78bd2e263e2f92b302001b4ed2d8 |
| bundle_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| snapshot_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8889 (16/18), B: 0.8889 (16/18) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9744 (±0.0, n=1) | 166.5753 (±0.0, n=1) | 354,998 (±0, n=1) |
| old_skill | 0.8462 (±0.0, n=1) | 238.7613 (±0.0, n=1) | 426,985 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.1282, time_seconds -72.19, tokens -71,986

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1256 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `bugfix-design-approved` — Output does not include: MAJOR-001: 3/3 vs 2/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 3/3 vs 1/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| bugfix-design-approved | with_skill | 38 | 1 | 13/13, 12/13, 13/13 |
| bugfix-design-approved | old_skill | 33 | 6 | 9/13, 13/13, 11/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 3/3 | 2/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 1/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 3/3 | 1/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 2/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 3/3 |
