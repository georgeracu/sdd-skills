# Eval results — iteration-18

**What this iteration tests:** #37 round 2: case 5 fixture round 11 (miss bound, Validates line, AccountRepository signature), A/A at main

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
| judge_tokens | 354629 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 309edd1b510d7e63 |
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
| label_pass_rates | A: 0.7778 (14/18), B: 0.7222 (13/18) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.6923 (±0.0, n=1) | 240.4677 (±0.0, n=1) | 457,255 (±0, n=1) |
| old_skill | 0.8462 (±0.0, n=1) | 292.078 (±0.0, n=1) | 416,331 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate -0.1539, time_seconds -51.61, tokens +40,924

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2176 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `bugfix-design-approved` — Output does not include: MAJOR-001: 0/3 vs 3/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 0/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 0/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 1/3 vs 2/3

with_skill scored above baseline:

- `bugfix-design-approved` — Output includes: Critical discrepancies: 0: 3/3 vs 2/3
- `bugfix-design-approved` — Output includes: **Status:** APPROVED: 3/3 vs 2/3
- `bugfix-design-approved` — Output does not include: CRITICAL-001: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| bugfix-design-approved | with_skill | 27 | 12 | 9/13, 9/13, 9/13 |
| bugfix-design-approved | old_skill | 33 | 6 | 13/13, 7/13, 13/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 2/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 3/3 | 2/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 2/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 0/3 | 3/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 0/3 | 3/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 0/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 1/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 2/3 | 2/3 |
