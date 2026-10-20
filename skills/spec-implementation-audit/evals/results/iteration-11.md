# Eval results — iteration-11

**What this iteration tests:** iteration 11: cases 1 and 4 only. Fix for the iteration-10 case 4 miss (PASSED (conditional) with NOT RUN blocks and no MAJOR): the Status value is exactly PASSED or FAILED; every NOT RUN block is MAJOR; a command the project does not have reads NOT APPLICABLE and is no finding. clean-pass runs as the guard because five of its six iteration-10 runs wrote NOT RUN for E2E. Baseline is the iteration-10 skill (A1, A3, A2); case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 494361 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 7fe9aa90257f2548 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 28914b8907ecf30e65c320e8d9ac37c92b2fcc8432e1e5808ec58dcec736bd35 |
| bundle_hash | a99df0d9673c611538c9329a10dd62a72ad7ffeb1612d70d2ba74bdb9b8513f7 |
| snapshot_hash | c7aada1966f192e6cdc3cdcfb3348bc5fec636789a862162fbd5345e4857476f |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (33/33), B: 0.9394 (31/33) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9861 (±0.0139, n=2) | 144.4577 (±31.9123, n=2) | 540,680 (±123,068, n=2) |
| old_skill | 0.9861 (±0.0139, n=2) | 137.1637 (±10.2513, n=2) | 391,188 (±21,586, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0000, time_seconds +7.29, tokens +149,493

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0393 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `no-build-output` — Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence: 2/3 vs 3/3

with_skill scored above baseline:

- `clean-pass` — Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| clean-pass | with_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| clean-pass | old_skill | 35 | 1 | 12/12, 12/12, 11/12 |
| no-build-output | with_skill | 35 | 1 | 12/12, 11/12, 12/12 |
| no-build-output | old_skill | 36 | 0 | 12/12, 12/12, 12/12 |

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
| clean-pass | Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file | 3/3 | 3/3 |
| clean-pass | Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped | 3/3 | 3/3 |
| clean-pass | Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 21 tests completed and the 'no diff' line), not a paraphrase | 3/3 | 3/3 |
| clean-pass | Meets criterion: Reports no CRITICAL or MAJOR discrepancy | 3/3 | 3/3 |
| clean-pass | Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step | 3/3 | 2/3 |
| no-build-output | Output includes: MAJOR | 3/3 | 3/3 |
| no-build-output | Output includes: FAILED | 3/3 | 3/3 |
| no-build-output | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| no-build-output | Output does not include: Status: PASSED | 3/3 | 3/3 |
| no-build-output | Output does not include: BUILD SUCCESSFUL | 3/3 | 3/3 |
| no-build-output | Output does not include: tests completed, 0 failed | 3/3 | 3/3 |
| no-build-output | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| no-build-output | Meets criterion: Writes implementation-audit.md with Status FAILED because evidence is missing | 3/3 | 3/3 |
| no-build-output | Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence | 2/3 | 3/3 |
| no-build-output | Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass | 3/3 | 3/3 |
| no-build-output | Meets criterion: The Evidence sections are empty or marked as missing and contain no invented command output | 3/3 | 3/3 |
| no-build-output | Meets criterion: Still reports the requirement rows and task completion from the files it can read, with no CRITICAL finding invented to compensate | 3/3 | 3/3 |
