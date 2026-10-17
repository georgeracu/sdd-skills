# Eval results — iteration-6

**What this iteration tests:** iteration 6: clean-pass case only, after the fourth fixture fix (markFired passes a Number Set to ADD and a scalar to contains, as two expression values); global CLAUDE.md set aside; the other four cases are unchanged since iteration 2

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
| judge_tokens | 136437 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 15f162fa42da5053 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 514ac464fde9eddf6f846241e98d2e91274062cec4d0567be402e8e147b3b5e2 |
| bundle_hash | e9d2646741c759d6be7f97512e99bd4bdf87fc2bf0fff43b4414ed8ed890bff2 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.5556 (10/18), B: 0.4444 (8/18) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9167 (±0.0, n=1) | 133.6047 (±0.0, n=1) | 409,668 (±0, n=1) |
| without_skill | 0.3889 (±0.0, n=1) | 140.4033 (±0.0, n=1) | 334,682 (±0, n=1) |

**Delta (with_skill − without_skill):** pass_rate +0.5278, time_seconds -6.80, tokens +74,986

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1416 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `clean-pass` — Output includes: PASSED: 3/3 vs 2/3
- `clean-pass` — Output includes: 21 tests completed: 3/3 vs 0/3
- `clean-pass` — Output includes: BUILD SUCCESSFUL: 3/3 vs 1/3
- `clean-pass` — Run produced file: implementation-audit.md: 3/3 vs 2/3
- `clean-pass` — Meets criterion: Writes implementation-audit.md in the working directory following the template structure (Status, Per-Requirement Status, Task Completion, Evidence, Verdict): 3/3 vs 0/3
- `clean-pass` — Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file: 3/3 vs 2/3
- `clean-pass` — Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped: 3/3 vs 0/3
- `clean-pass` — Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 21 tests completed and the 'no diff' line), not a paraphrase: 3/3 vs 0/3
- `clean-pass` — Meets criterion: Reports no CRITICAL or MAJOR discrepancy: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| clean-pass | with_skill | 33 | 3 | 11/12, 11/12, 11/12 |
| clean-pass | without_skill | 14 | 22 | 3/12, 7/12, 4/12 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| clean-pass | Output includes: PASSED | 3/3 | 2/3 |
| clean-pass | Output includes: 21 tests completed | 3/3 | 0/3 |
| clean-pass | Output includes: BUILD SUCCESSFUL | 3/3 | 1/3 |
| clean-pass | Output does not include: CRITICAL-0 | 3/3 | 3/3 |
| clean-pass | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| clean-pass | Run produced file: implementation-audit.md | 3/3 | 2/3 |
| clean-pass | Meets criterion: Writes implementation-audit.md in the working directory following the template structure (Status, Per-Requirement Status, Task Completion, Evidence, Verdict) | 3/3 | 0/3 |
| clean-pass | Meets criterion: Per-Requirement Status has a row for every acceptance criterion of Requirements 1 and 2, each marked implemented with evidence naming a code file | 3/3 | 2/3 |
| clean-pass | Meets criterion: Task Completion reports five non-optional tasks complete, none incomplete, and the optional task 6 as skipped | 3/3 | 0/3 |
| clean-pass | Meets criterion: The Evidence section contains the pasted build, test and type-freshness output (for example the 21 tests completed and the 'no diff' line), not a paraphrase | 3/3 | 0/3 |
| clean-pass | Meets criterion: Reports no CRITICAL or MAJOR discrepancy | 3/3 | 1/3 |
| clean-pass | Meets criterion: States a PASSED verdict and names sdd:spec-qa-review review mode as the next step | 0/3 | 0/3 |
