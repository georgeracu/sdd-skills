# Eval results — iteration-30

**What this iteration tests:** #61 PR 2 gate: case 5 only, 6 runs an arm, the smaller empty-period-usage bugfix fixture against the main skill

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
| judge_tokens | 364528 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 410f5afd342525e2 |
| runs_per_arm | 6 |
| parallel | 3 |
| pack_hash | 796f0a72e9236e64d5c333e16dc0c6470f6c4dde924bc752cd2bc30d8a72e6b4 |
| bundle_hash | 4049873b9c1d1b0f9d89574470bf5bbce48357169b5b9cdc04cf29574445e71b |
| snapshot_hash | 4049873b9c1d1b0f9d89574470bf5bbce48357169b5b9cdc04cf29574445e71b |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (36/36), B: 0.9722 (35/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9744 (±0.0, n=1) | 54.1872 (±0.0, n=1) | 199,450 (±0, n=1) |
| old_skill | 1.0 (±0.0, n=1) | 48.8077 (±0.0, n=1) | 182,455 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate -0.0256, time_seconds +5.38, tokens +16,996

*measured over 1 case × 6 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0573 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 5/6 vs 6/6
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 5/6 vs 6/6

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| bugfix-design-approved | with_skill | 76 | 2 | 11/13, 13/13, 13/13, 13/13, 13/13, 13/13 |
| bugfix-design-approved | old_skill | 78 | 0 | 13/13, 13/13, 13/13, 13/13, 13/13, 13/13 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-5 | with_skill | 6 | 0 | 0 |
| eval-5 | old_skill | 6 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 6/6 | 6/6 |
| bugfix-design-approved | Output includes: bugfix.md | 6/6 | 6/6 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 6/6 | 6/6 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 6/6 | 6/6 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 6/6 | 6/6 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 5/6 | 6/6 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 6/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-empty-period-usage-bugfix work item | 6/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 6/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 6/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 5/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 6/6 | 6/6 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 6/6 | 6/6 |
