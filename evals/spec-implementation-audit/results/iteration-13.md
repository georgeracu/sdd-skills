# Eval results — iteration-13

**What this iteration tests:** iteration 13: case 2 only, rerun of the iteration-12 miss. The skill and the baseline are the same as in iteration 12 (the #28 trim against the skill at 1b0462f). In iteration 12, case 2 wrote Status PASSED in 2 of 3 skill-arm runs and 1 of 3 baseline runs, each after the agent ticked tasks.md itself. This run checks whether that miss is noise; case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 214831 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 308080ed22b582e4 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | b1f10cd5a1c44664044790b50192d58476e8476df64e787109fb099e18a7df4b |
| bundle_hash | a6129cf096c8fdfde47ae2d30c7e8e19f82abad6d4969a25d604dfdb8f783ece |
| snapshot_hash | a99df0d9673c611538c9329a10dd62a72ad7ffeb1612d70d2ba74bdb9b8513f7 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (18/18), B: 0.7222 (13/18) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8182 (±0.0, n=1) | 104.209 (±0.0, n=1) | 376,576 (±0, n=1) |
| old_skill | 1.0 (±0.0, n=1) | 104.3677 (±0.0, n=1) | 360,647 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate -0.1818, time_seconds -0.16, tokens +15,928

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2572 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `unticked-tasks-no-table` — Output does not include: **Status:** PASSED: 2/3 vs 3/3
- `unticked-tasks-no-table` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED: 2/3 vs 3/3
- `unticked-tasks-no-table` — Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks: 2/3 vs 3/3
- `unticked-tasks-no-table` — Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete: 2/3 vs 3/3
- `unticked-tasks-no-table` — Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit: 2/3 vs 3/3
- `unticked-tasks-no-table` — Meets criterion: Does not present the build output as a reason to override the unchecked tasks: 2/3 vs 3/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| unticked-tasks-no-table | with_skill | 27 | 6 | 5/11, 11/11, 11/11 |
| unticked-tasks-no-table | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| unticked-tasks-no-table | Output includes: CRITICAL | 3/3 | 3/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 2/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 2/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 2/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 2/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 2/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 2/3 | 3/3 |
