# Eval results — iteration-2

**What this iteration tests:** iteration 2: pack fixed after iteration 1 (expected_files flat; clean-pass fixture gains IngestHandler and DynamoUsageMeterRepository); global CLAUDE.md set aside

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.293 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 553945 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 10ea8a49a63595e7 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | aa9243ed5f0dcefe774f2370d8d83b192ec7ed47aa1f609db569f61dc59583b4 |
| bundle_hash | e9d2646741c759d6be7f97512e99bd4bdf87fc2bf0fff43b4414ed8ed890bff2 |
| input_drift_allowed | none |
| cases_total | 5 |
| cases_included | 5 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.4023 (35/87), B: 0.4598 (40/87) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.695 (±0.1907, n=5) | 154.3367 (±20.318, n=5) | 351,587 (±36,032, n=5) |
| without_skill | 0.4657 (±0.1004, n=5) | 174.5371 (±41.3041, n=5) | 354,426 (±50,325, n=5) |

**Delta (with_skill − without_skill):** pass_rate +0.2293, time_seconds -20.20, tokens -2,839

*measured over 5 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2143 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `clean-pass` — Output does not include: CRITICAL-0: 2/3 vs 3/3
- `clean-pass` — Output does not include: **Status:** FAILED: 2/3 vs 3/3
- `clean-pass` — Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file: 2/3 vs 3/3
- `clean-pass` — Meets criterion: Reports no CRITICAL or MAJOR discrepancy: 0/3 vs 1/3
- `unticked-tasks-no-table` — Output does not include: **Status:** PASSED: 2/3 vs 3/3
- `status-table-over-unticked-boxes` — Output does not include: **Status:** PASSED: 0/3 vs 3/3
- `no-build-output` — Output does not include: **Status:** PASSED: 0/3 vs 3/3
- `no-build-output` — Output does not include: Status: PASSED: 2/3 vs 3/3
- `no-build-output` — Output does not include: BUILD SUCCESSFUL: 2/3 vs 3/3
- `no-build-output` — Output does not include: tests completed, 0 failed: 2/3 vs 3/3
- `no-build-output` — Meets criterion: Does not state PASSED; the Status is FAILED, or the audit explicitly withholds a pass because evidence is missing: 0/3 vs 1/3

with_skill scored above baseline:

- `clean-pass` — Output includes: PASSED: 3/3 vs 2/3
- `clean-pass` — Output includes: 20 tests completed: 3/3 vs 0/3
- `clean-pass` — Output includes: BUILD SUCCESSFUL: 3/3 vs 0/3
- `clean-pass` — Meets criterion: Writes implementation-audit.md in the working directory following the template structure (Status, Per-Requirement Status, Task Completion, Evidence, Verdict): 3/3 vs 0/3
- `clean-pass` — Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped: 3/3 vs 0/3
- `clean-pass` — Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 20 tests completed and the 'no diff' line), not a paraphrase: 3/3 vs 0/3
- `clean-pass` — Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step: 1/3 vs 0/3
- `unticked-tasks-no-table` — Output includes: CRITICAL: 3/3 vs 0/3
- `unticked-tasks-no-table` — Output includes: FAILED: 3/3 vs 2/3
- `unticked-tasks-no-table` — Run produced file: implementation-audit.md: 3/3 vs 1/3
- `unticked-tasks-no-table` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED: 2/3 vs 0/3
- `status-table-over-unticked-boxes` — Output includes: CRITICAL: 2/3 vs 0/3
- `status-table-over-unticked-boxes` — Output includes: FAILED: 3/3 vs 1/3
- `status-table-over-unticked-boxes` — Run produced file: implementation-audit.md: 3/3 vs 2/3
- `no-build-output` — Output includes: MAJOR: 3/3 vs 0/3
- `no-build-output` — Run produced file: implementation-audit.md: 2/3 vs 0/3
- `no-build-output` — Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence: 3/3 vs 0/3
- `no-build-output` — Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass: 2/3 vs 0/3
- `no-build-output` — Meets criterion: Still reports the requirement rows and task completion from the files it can read, with no CRITICAL finding invented to compensate: 3/3 vs 1/3
- `property-without-implementation` — Output includes: FAILED: 3/3 vs 0/3
- `property-without-implementation` — Run produced file: implementation-audit.md: 3/3 vs 2/3
- `property-without-implementation` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build: 3/3 vs 1/3
- `property-without-implementation` — Meets criterion: Marks the AC 2.3 and 2.4 rows as missing or partial, and AC 2.5, 2.2 and Requirement 1 rows as implemented: 3/3 vs 2/3
- `property-without-implementation` — Meets criterion: The Verdict says a green build does not offset the missing behaviour, and the action is to implement the fired-state recording and re-run the audit: 3/3 vs 0/3
- `property-without-implementation` — Meets criterion: Pastes the build output from build-output.txt in the Evidence section: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| clean-pass | with_skill | 28 | 8 | 11/12, 10/12, 7/12 |
| clean-pass | without_skill | 15 | 21 | 5/12, 5/12, 5/12 |
| unticked-tasks-no-table | with_skill | 20 | 13 | 7/11, 5/11, 8/11 |
| unticked-tasks-no-table | without_skill | 13 | 20 | 5/11, 4/11, 4/11 |
| status-table-over-unticked-boxes | with_skill | 14 | 19 | 5/11, 4/11, 5/11 |
| status-table-over-unticked-boxes | without_skill | 12 | 21 | 3/11, 5/11, 4/11 |
| no-build-output | with_skill | 22 | 11 | 9/11, 4/11, 9/11 |
| no-build-output | without_skill | 17 | 16 | 6/11, 5/11, 6/11 |
| property-without-implementation | with_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| property-without-implementation | without_skill | 23 | 13 | 8/12, 7/12, 8/12 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| clean-pass | Output includes: PASSED | 3/3 | 2/3 |
| clean-pass | Output includes: 20 tests completed | 3/3 | 0/3 |
| clean-pass | Output includes: BUILD SUCCESSFUL | 3/3 | 0/3 |
| clean-pass | Output does not include: CRITICAL-0 | 2/3 | 3/3 |
| clean-pass | Output does not include: **Status:** FAILED | 2/3 | 3/3 |
| clean-pass | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| clean-pass | Meets criterion: Writes implementation-audit.md in the working directory following the template structure (Status, Per-Requirement Status, Task Completion, Evidence, Verdict) | 3/3 | 0/3 |
| clean-pass | Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file | 2/3 | 3/3 |
| clean-pass | Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped | 3/3 | 0/3 |
| clean-pass | Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 20 tests completed and the 'no diff' line), not a paraphrase | 3/3 | 0/3 |
| clean-pass | Meets criterion: Reports no CRITICAL or MAJOR discrepancy | 0/3 | 1/3 |
| clean-pass | Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step | 1/3 | 0/3 |
| unticked-tasks-no-table | Output includes: CRITICAL | 3/3 | 0/3 |
| unticked-tasks-no-table | Output includes: FAILED | 3/3 | 2/3 |
| unticked-tasks-no-table | Output does not include: **Status:** PASSED | 2/3 | 3/3 |
| unticked-tasks-no-table | Output does not include: Status: PASSED | 3/3 | 3/3 |
| unticked-tasks-no-table | Run produced file: implementation-audit.md | 3/3 | 1/3 |
| unticked-tasks-no-table | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 2/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Raises CRITICAL findings for the unchecked non-optional tasks 3 and 4, naming both tasks | 0/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Task Completion shows two incomplete tasks and three completed, and treats the unticked optional task 6 as skipped rather than incomplete | 0/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Does not raise a CRITICAL finding for task 6 or for tasks 1, 2 and 5 | 3/3 | 3/3 |
| unticked-tasks-no-table | Meets criterion: The Verdict explains that the failure is the uncompleted tasks, and the action is to complete or tick them and re-run the audit | 0/3 | 0/3 |
| unticked-tasks-no-table | Meets criterion: Does not present the build output as a reason to override the unchecked tasks | 1/3 | 1/3 |
| status-table-over-unticked-boxes | Output includes: CRITICAL | 2/3 | 0/3 |
| status-table-over-unticked-boxes | Output includes: FAILED | 3/3 | 1/3 |
| status-table-over-unticked-boxes | Output does not include: **Status:** PASSED | 0/3 | 3/3 |
| status-table-over-unticked-boxes | Output does not include: Status: PASSED | 3/3 | 3/3 |
| status-table-over-unticked-boxes | Run produced file: implementation-audit.md | 3/3 | 2/3 |
| status-table-over-unticked-boxes | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED | 0/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: Raises CRITICAL findings for the five unchecked non-optional tasks (one each or one covering tasks 1 to 5) | 0/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: Task Completion reports five incomplete rather than five complete | 0/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not treat the status table or the commit hashes as satisfying the task completion rule | 0/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: May note that the hashes resolve in git-log.txt and that ticking the boxes would clear the finding, but still states FAILED | 0/3 | 0/3 |
| status-table-over-unticked-boxes | Meets criterion: Does not raise a CRITICAL finding for the skipped optional task 6 | 3/3 | 3/3 |
| no-build-output | Output includes: MAJOR | 3/3 | 0/3 |
| no-build-output | Output does not include: **Status:** PASSED | 0/3 | 3/3 |
| no-build-output | Output does not include: Status: PASSED | 2/3 | 3/3 |
| no-build-output | Output does not include: BUILD SUCCESSFUL | 2/3 | 3/3 |
| no-build-output | Output does not include: tests completed, 0 failed | 2/3 | 3/3 |
| no-build-output | Run produced file: implementation-audit.md | 2/3 | 0/3 |
| no-build-output | Meets criterion: Does not state PASSED; the Status is FAILED, or the audit explicitly withholds a pass because evidence is missing | 0/3 | 1/3 |
| no-build-output | Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence | 3/3 | 0/3 |
| no-build-output | Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass | 2/3 | 0/3 |
| no-build-output | Meets criterion: The Evidence sections are empty or marked as missing and contain no invented command output | 3/3 | 3/3 |
| no-build-output | Meets criterion: Still reports the requirement rows and task completion from the files it can read, with no CRITICAL finding invented to compensate | 3/3 | 1/3 |
| property-without-implementation | Output includes: CRITICAL | 3/3 | 3/3 |
| property-without-implementation | Output includes: FAILED | 3/3 | 0/3 |
| property-without-implementation | Output includes: Property 1 | 3/3 | 3/3 |
| property-without-implementation | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| property-without-implementation | Output does not include: Status: PASSED | 3/3 | 3/3 |
| property-without-implementation | Run produced file: implementation-audit.md | 3/3 | 2/3 |
| property-without-implementation | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build | 3/3 | 1/3 |
| property-without-implementation | Meets criterion: Raises a CRITICAL discrepancy for acceptance criteria 2.3 and 2.4: no fired-state is recorded or checked, so alerts repeat | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Connects the finding to Property 1 in design.md, naming that nothing implements it and no test covers it | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Marks the AC 2.3 and 2.4 rows as missing or partial, and AC 2.5, 2.2 and Requirement 1 rows as implemented | 3/3 | 2/3 |
| property-without-implementation | Meets criterion: The Verdict says a green build does not offset the missing behaviour, and the action is to implement the fired-state recording and re-run the audit | 3/3 | 0/3 |
| property-without-implementation | Meets criterion: Pastes the build output from build-output.txt in the Evidence section | 3/3 | 0/3 |
