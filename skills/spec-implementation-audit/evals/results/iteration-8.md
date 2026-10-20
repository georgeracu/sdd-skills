# Eval results — iteration-8

**What this iteration tests:** iteration 8: cases 2 and 3 only (the pack is cut to the two cases this edit targets, to save tokens). Edit A3 option A from #28 plus a read-only rule (the audit changes no work-item or code file and writes only implementation-audit.md); an unticked non-optional box is incomplete whatever a status table, commit hash or the user says. Baseline is the iteration-7 skill (A1 applied); case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 498784 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | d4bdacbcb2eebeba |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | ddf106103c906776237366e7c3fdeb5aad86a985642d245b6496abb9b5709aeb |
| bundle_hash | 336adeec354802096372db7604c26a23459dca96c3282007a5b3127bb0bdc74e |
| snapshot_hash | 8a727112fed957d502d501572b7f03159c8daebc88ab622b65ce69c8a97fcee3 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.5556 (20/36), B: 0.5833 (21/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.894 (±0.106, n=2) | 120.009 (±3.463, n=2) | 421,818 (±20,216, n=2) |
| old_skill | 0.5151 (±0.0909, n=2) | 147.529 (±0.813, n=2) | 412,203 (±14,224, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.3789, time_seconds -27.52, tokens +9,614

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3000 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `unticked-tasks-no-table` — Output does not include: **Status:** PASSED: 2/3 vs 0/3
- `unticked-tasks-no-table` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED: 2/3 vs 0/3
- `unticked-tasks-no-table` — Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks: 2/3 vs 0/3
- `unticked-tasks-no-table` — Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete: 2/3 vs 0/3
- `unticked-tasks-no-table` — Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit: 2/3 vs 0/3
- `unticked-tasks-no-table` — Meets criterion: Does not present the build output as a reason to override the unchecked tasks: 2/3 vs 0/3
- `status-table-over-unticked-boxes` — Output does not include: **Status:** PASSED: 3/3 vs 1/3
- `status-table-over-unticked-boxes` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED: 3/3 vs 1/3
- `status-table-over-unticked-boxes` — Meets criterion: Raises CRITICAL findings for the five unchecked non-optional tasks (one each or one covering tasks 1 to 5): 3/3 vs 1/3
- `status-table-over-unticked-boxes` — Meets criterion: Task Completion reports five incomplete rather than five complete: 3/3 vs 0/3
- `status-table-over-unticked-boxes` — Meets criterion: Does not treat the status table or the commit hashes as satisfying the task completion rule: 3/3 vs 1/3
- `status-table-over-unticked-boxes` — Meets criterion: May note that the hashes resolve in git-log.txt and that ticking the boxes would clear the finding, but still states FAILED: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| unticked-tasks-no-table | with_skill | 26 | 7 | 4/11, 11/11, 11/11 |
| unticked-tasks-no-table | old_skill | 14 | 19 | 5/11, 4/11, 5/11 |
| status-table-over-unticked-boxes | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| status-table-over-unticked-boxes | old_skill | 20 | 13 | 5/11, 5/11, 10/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| unticked-tasks-no-table | Output includes: CRITICAL | 2/3 | 2/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 2/3 | 0/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 2/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 2/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 2/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 2/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 2/3 | 0/3 |
| status-table-over-unticked-boxes | Output includes: CRITICAL | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output includes: FAILED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Output does not include: **Status:** PASSED | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Output does not include: Status: PASSED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Meets criterion: Raises CRITICAL findings for the five unchecked non-optional tasks (one each or one covering tasks 1 to 5) | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Meets criterion: Task Completion reports five incomplete rather than five complete | 3/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not treat the status table or the commit hashes as satisfying the task completion rule | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Meets criterion: May note that the hashes resolve in git-log.txt and that ticking the boxes would clear the finding, but still states FAILED | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not raise a CRITICAL finding for the skipped optional task 6 | 3/3 | 3/3 |
