# Eval results — iteration-1

**What this iteration tests:** S1: peer set excludes the current item; empty peer set writes nothing and hands over (case 1)

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
| judge_tokens | 156552 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a214bda0721f795a |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 8a44a4e0c4817b93c2cd420fab807f46ebecf58ccd47279403967ae3411e6ddc |
| bundle_hash | 99828d4917b3a92d7f70d651c09ec34abcb977911fd36c92f165755b88390771 |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8667 (13/15), B: 1.0 (15/15) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=1) | 38.8423 (±0.0, n=1) | 255,863 (±0, n=1) |
| old_skill | 0.9259 (±0.0, n=1) | 38.1193 (±0.0, n=1) | 331,583 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.0741, time_seconds +0.72, tokens -75,721

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0524 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `skip-no-implemented-peers` — Meets criterion: Does not write a spec-maintenance-report.md, not even one that says nothing was impacted: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| skip-no-implemented-peers | with_skill | 27 | 0 | 9/9, 9/9, 9/9 |
| skip-no-implemented-peers | old_skill | 25 | 2 | 9/9, 8/9, 8/9 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| skip-no-implemented-peers | Output includes: finishing-a-development-branch | 3/3 | 3/3 |
| skip-no-implemented-peers | Output does not include: ## Impacted Work Items | 3/3 | 3/3 |
| skip-no-implemented-peers | Output does not include: *(superseded by work-item 04)* | 3/3 | 3/3 |
| skip-no-implemented-peers | Output does not include: *(deprecated | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: States that no other implemented work item exists, because neither 01-quota-alerts nor 02-usage-export holds an implementation-audit.md | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Skips the skill instead of scanning the older work items for impacts | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Does not write a spec-maintenance-report.md, not even one that says nothing was impacted | 3/3 | 1/3 |
| skip-no-implemented-peers | Meets criterion: Does not edit or annotate any file in the older work items | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Hands over to finishing-a-development-branch as the next step | 3/3 | 3/3 |
