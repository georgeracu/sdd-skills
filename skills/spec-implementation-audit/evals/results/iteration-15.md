# Eval results — iteration-15

**What this iteration tests:** iteration 15: cases 2 and 3 only, the read-only fix. Step 4 now says the audit never edits tasks.md, reads each checkbox as it was when the audit started, and does not count a box ticked during the audit. In iterations 12 to 14 the 1b0462f skill wrote Status PASSED on case 2 in 2 of 9 runs, each after the agent ticked tasks.md itself. Baseline is the skill at 1b0462f; case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 504699 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 44e5d0e2da1b83a9 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | a02b4fee5a48aa8c1e08e5a54c9aacbdea65e3bf9891aae6a43c4d91c108f3e9 |
| bundle_hash | a9df0389969a15ddc396d89bca8cfbaeac544977cd5cc3e156d0ad29b254ab5a |
| snapshot_hash | a99df0d9673c611538c9329a10dd62a72ad7ffeb1612d70d2ba74bdb9b8513f7 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (36/36), B: 1.0 (36/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=2) | 118.8833 (±14.249, n=2) | 429,144 (±66,440, n=2) |
| old_skill | 1.0 (±0.0, n=2) | 123.9148 (±0.7862, n=2) | 349,243 (±17,729, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0000, time_seconds -5.03, tokens +79,901

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0000 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| unticked-tasks-no-table | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| unticked-tasks-no-table | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| status-table-over-unticked-boxes | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| status-table-over-unticked-boxes | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| unticked-tasks-no-table | Output includes: CRITICAL | 3/3 | 3/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output includes: CRITICAL | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output includes: FAILED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output does not include: Status: PASSED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Raises CRITICAL findings for the five unchecked non-optional tasks (one each or one covering tasks 1 to 5) | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Task Completion reports five incomplete rather than five complete | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not treat the status table or the commit hashes as satisfying the task completion rule | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: May note that the hashes resolve in git-log.txt and that ticking the boxes would clear the finding, but still states FAILED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not raise a CRITICAL finding for the skipped optional task 6 | 3/3 | 3/3 |
