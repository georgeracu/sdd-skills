# Eval results — iteration-14

**What this iteration tests:** iteration 14: case 2 only, A6 alone. Iterations 12 and 13 showed a case 2 regression (skill arm Status PASSED in 3 of 6 runs, baseline 1 of 6) with A5, the cuts and A6 applied together. A5 and the cuts are restored; only A6 (inline outline replaced by the template pointer and fallback headings) remains. Baseline is the skill at 1b0462f; case content unchanged; global CLAUDE.md set aside

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.303 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 216808 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | dcb5ad5edb09b90d |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | b1f10cd5a1c44664044790b50192d58476e8476df64e787109fb099e18a7df4b |
| bundle_hash | 1d81b40cca4f9bb85b2797e14c2564343712cc92582273430a091ab66f85aa9f |
| snapshot_hash | a99df0d9673c611538c9329a10dd62a72ad7ffeb1612d70d2ba74bdb9b8513f7 |
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
| with_skill | 0.8485 (±0.0, n=1) | 132.8047 (±0.0, n=1) | 503,106 (±0, n=1) |
| old_skill | 0.8182 (±0.0, n=1) | 100.7207 (±0.0, n=1) | 391,599 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.0303, time_seconds +32.08, tokens +111,507

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2572 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `unticked-tasks-no-table` — Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| unticked-tasks-no-table | with_skill | 28 | 5 | 11/11, 11/11, 6/11 |
| unticked-tasks-no-table | old_skill | 27 | 6 | 11/11, 11/11, 5/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| unticked-tasks-no-table | Output includes: CRITICAL | 3/3 | 3/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 2/3 | 2/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 2/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 3/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 2/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 2/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 2/3 | 2/3 |
