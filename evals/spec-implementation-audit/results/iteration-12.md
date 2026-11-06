# Eval results — iteration-12

**What this iteration tests:** iteration 12: full pack, one run for the #28 trim. A5 (Severity Levels list removed, MINOR rule added to the Gate), the cuts (line 8, the tasks.md prerequisite on line 18, the stack example paths in step 1) and A6 (inline output outline replaced by a pointer to templates/gates/implementation-audit.md with a fallback list of headings). A5 and the cuts have no run of their own. The fixtures stage no template, so every run takes the fallback. Baseline is the skill at 1b0462f, before A5; case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 1217317 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 43b28dc353d1cc41 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | f3b3596b8cd34fee4118ceb76c46452e6e5d3c7d43c6aa5dd8960b9017865011 |
| bundle_hash | a6129cf096c8fdfde47ae2d30c7e8e19f82abad6d4969a25d604dfdb8f783ece |
| snapshot_hash | a99df0d9673c611538c9329a10dd62a72ad7ffeb1612d70d2ba74bdb9b8513f7 |
| input_drift_allowed | none |
| cases_total | 5 |
| cases_included | 5 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.931 (81/87), B: 0.8621 (75/87) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9111 (±0.1259, n=5) | 135.6892 (±33.5789, n=5) | 499,612 (±116,168, n=5) |
| old_skill | 0.9636 (±0.0727, n=5) | 136.1653 (±21.0978, n=5) | 469,477 (±108,923, n=5) |

**Delta (with_skill − old_skill):** pass_rate -0.0525, time_seconds -0.48, tokens +30,135

*measured over 5 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2572 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `clean-pass` — Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file: 1/3 vs 3/3
- `clean-pass` — Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step: 2/3 vs 3/3
- `unticked-tasks-no-table` — Output does not include: **Status:** PASSED: 1/3 vs 2/3
- `unticked-tasks-no-table` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED: 1/3 vs 2/3
- `unticked-tasks-no-table` — Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete: 1/3 vs 2/3
- `unticked-tasks-no-table` — Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit: 1/3 vs 2/3
- `unticked-tasks-no-table` — Meets criterion: Does not present the build output as a reason to override the unchecked tasks: 1/3 vs 2/3
- `property-without-implementation` — Meets criterion: Pastes the build output from build-output.txt in the Evidence section: 2/3 vs 3/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| clean-pass | with_skill | 33 | 3 | 10/12, 12/12, 11/12 |
| clean-pass | old_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| unticked-tasks-no-table | with_skill | 22 | 11 | 6/11, 11/11, 5/11 |
| unticked-tasks-no-table | old_skill | 27 | 6 | 11/11, 11/11, 5/11 |
| status-table-over-unticked-boxes | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| status-table-over-unticked-boxes | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| no-build-output | with_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| no-build-output | old_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| property-without-implementation | with_skill | 35 | 1 | 11/12, 12/12, 12/12 |
| property-without-implementation | old_skill | 36 | 0 | 12/12, 12/12, 12/12 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| clean-pass | Output includes: PASSED | 3/3 | 3/3 |
| clean-pass | Output includes: 21 tests completed | 3/3 | 3/3 |
| clean-pass | Output includes: BUILD SUCCESSFUL | 3/3 | 3/3 |
| clean-pass | Output does not include: CRITICAL-0 | 3/3 | 3/3 |
| clean-pass | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| clean-pass | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| clean-pass | Meets criterion: Writes implementation-audit.md in the working directory following the template structure (Status, Per-Requirement Status, Task Completion, Evidence, Verdict) | 3/3 | 3/3 |
| clean-pass | Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file | 1/3 | 3/3 |
| clean-pass | Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped | 3/3 | 3/3 |
| clean-pass | Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 21 tests completed and the 'no diff' line), not a paraphrase | 3/3 | 3/3 |
| clean-pass | Meets criterion: Reports no CRITICAL or MAJOR discrepancy | 3/3 | 3/3 |
| clean-pass | Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step | 2/3 | 3/3 |
| unticked-tasks-no-table | Output includes: CRITICAL | 3/3 | 3/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 1/3 | 2/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 1/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 2/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 1/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 1/3 | 2/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 1/3 | 2/3 |
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
| no-build-output | Output includes: MAJOR | 3/3 | 3/3 |
| no-build-output | Output includes: FAILED | 3/3 | 3/3 |
| no-build-output | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| no-build-output | Output does not include: Status: PASSED | 3/3 | 3/3 |
| no-build-output | Output does not include: BUILD SUCCESSFUL | 3/3 | 3/3 |
| no-build-output | Output does not include: tests completed, 0 failed | 3/3 | 3/3 |
| no-build-output | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| no-build-output | Meets criterion: Writes implementation-audit.md with Status FAILED because evidence is missing | 3/3 | 3/3 |
| no-build-output | Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence | 3/3 | 3/3 |
| no-build-output | Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass | 3/3 | 3/3 |
| no-build-output | Meets criterion: The Evidence sections are empty or marked as missing and contain no invented command output | 3/3 | 3/3 |
| no-build-output | Meets criterion: Still reports the requirement rows and task completion from the files it can read, with no CRITICAL finding invented to compensate | 3/3 | 3/3 |
| property-without-implementation | Output includes: CRITICAL | 3/3 | 3/3 |
| property-without-implementation | Output includes: FAILED | 3/3 | 3/3 |
| property-without-implementation | Output includes: Property 1 | 3/3 | 3/3 |
| property-without-implementation | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| property-without-implementation | Output does not include: Status: PASSED | 3/3 | 3/3 |
| property-without-implementation | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Raises a CRITICAL discrepancy for acceptance criteria 2.3 and 2.4: no fired-state is recorded or checked, so alerts repeat | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Connects the finding to Property 1 in design.md, naming that nothing implements it and no test covers it | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Marks the AC 2.3 and 2.4 rows as missing or partial, and AC 2.5, 2.2 and Requirement 1 rows as implemented | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: The Verdict says a green build does not offset the missing behaviour, and the action is to implement the fired-state recording and re-run the audit | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Pastes the build output from build-output.txt in the Evidence section | 2/3 | 3/3 |
