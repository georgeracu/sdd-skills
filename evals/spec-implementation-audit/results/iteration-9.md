# Eval results — iteration-9

**What this iteration tests:** iteration 9: case 4 only (the pack is cut to the case this edit targets). Edit A2 from #28: every evidence block holds the command line and its verbatim output; run each command AGENTS.md or the README names; user-supplied output counts only with the command shown; a block with no output reads NOT RUN: [reason] and is MAJOR. Baseline is the iteration-8 skill (A1 and A3 applied); case content unchanged; global CLAUDE.md set aside

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
| judge_tokens | 277961 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 73712f1ba61b3129 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 88b29578767ebef603b73121fac63359d2442ea53764d30f6ae2e52f0df13e33 |
| bundle_hash | c7aada1966f192e6cdc3cdcfb3348bc5fec636789a862162fbd5345e4857476f |
| snapshot_hash | 336adeec354802096372db7604c26a23459dca96c3282007a5b3127bb0bdc74e |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.4 (6/15), B: 1.0 (15/15) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=1) | 163.37 (±0.0, n=1) | 565,090 (±0, n=1) |
| old_skill | 0.6111 (±0.0, n=1) | 150.6327 (±0.0, n=1) | 488,712 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.3889, time_seconds +12.74, tokens +76,378

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0393 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `no-build-output` — Output includes: FAILED: 3/3 vs 1/3
- `no-build-output` — Output does not include: **Status:** PASSED: 3/3 vs 0/3
- `no-build-output` — Meets criterion: Writes implementation-audit.md with Status FAILED because evidence is missing: 3/3 vs 0/3
- `no-build-output` — Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence: 3/3 vs 0/3
- `no-build-output` — Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| no-build-output | with_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| no-build-output | old_skill | 22 | 14 | 7/12, 8/12, 7/12 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| no-build-output | Output includes: MAJOR | 3/3 | 3/3 |
| no-build-output | Output includes: FAILED | 3/3 | 1/3 |
| no-build-output | Output does not include: **Status:** PASSED | 3/3 | 0/3 |
| no-build-output | Output does not include: Status: PASSED | 3/3 | 3/3 |
| no-build-output | Output does not include: BUILD SUCCESSFUL | 3/3 | 3/3 |
| no-build-output | Output does not include: tests completed, 0 failed | 3/3 | 3/3 |
| no-build-output | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| no-build-output | Meets criterion: Writes implementation-audit.md with Status FAILED because evidence is missing | 3/3 | 0/3 |
| no-build-output | Meets criterion: Raises a MAJOR discrepancy for missing build, test or type-freshness evidence | 3/3 | 0/3 |
| no-build-output | Meets criterion: Says what the rule requires: actual pasted command output from the backend build and tests, the frontend build and tests and the API type freshness check, not a claim that tests pass | 3/3 | 0/3 |
| no-build-output | Meets criterion: The Evidence sections are empty or marked as missing and contain no invented command output | 3/3 | 3/3 |
| no-build-output | Meets criterion: Still reports the requirement rows and task completion from the files it can read, with no CRITICAL finding invented to compensate | 3/3 | 3/3 |
