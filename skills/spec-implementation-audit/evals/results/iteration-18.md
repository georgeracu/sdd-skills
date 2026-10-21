# Eval results — iteration-18

**What this iteration tests:** iteration 18: cases 5 and 6 only, A4. A new step 3 (Correctness Properties) names each design.md property's cited criteria, enforcing code and test; no enforcing code or contradicting code is CRITICAL; no test, no Validates line or a Validates line naming a criterion requirements.md lacks is MAJOR. Case 5 expected output now names the A4 rule; case 6 is new (Property 4 cites the nonexistent Requirement 2.6, implemented and tested; expects MAJOR and FAILED). Baseline is the skill at main 0aec35f; global CLAUDE.md set aside

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
| judge_tokens | 471598 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a8897b7abb7e52d7 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 0bd80453bc4ef21ad2a18391d2dd3e321355995daced83738d30a286fce1c885 |
| bundle_hash | 0758e2d19ae194607389e3227e737c3813dfe9a79da0f11f9cdb32197e201e46 |
| snapshot_hash | 31995c2204fbea6dcc5abe9d04048bb2ffead8f78de506dbcbb2707469fc50b4 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.9444 (34/36), B: 0.7778 (28/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9722 (±0.0278, n=2) | 145.8608 (±5.7045, n=2) | 430,490 (±9,116, n=2) |
| old_skill | 0.8611 (±0.0833, n=2) | 101.9738 (±5.1438, n=2) | 387,648 (±13,468, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.1111, time_seconds +43.89, tokens +42,841

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1571 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `property-without-implementation` — Meets criterion: Pastes the build output from build-output.txt in the Evidence section: 2/3 vs 3/3

with_skill scored above baseline:

- `property-without-implementation` — Meets criterion: Marks the AC 2.3 and 2.4 rows as missing or partial, and AC 2.5, 2.2 and Requirement 1 rows as implemented: 2/3 vs 1/3
- `property-without-backing-criterion` — Output does not include: **Status:** PASSED: 3/3 vs 1/3
- `property-without-backing-criterion` — Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build: 3/3 vs 1/3
- `property-without-backing-criterion` — Meets criterion: Raises a MAJOR finding that names Property 4 and says its Validates line cites Requirement 2.6, which requirements.md does not have: 3/3 vs 1/3
- `property-without-backing-criterion` — Meets criterion: The Verdict states FAILED and the action is to add the backing criterion to requirements.md or remove Property 4, then re-run the audit: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| property-without-implementation | with_skill | 34 | 2 | 11/12, 12/12, 11/12 |
| property-without-implementation | old_skill | 34 | 2 | 12/12, 11/12, 11/12 |
| property-without-backing-criterion | with_skill | 36 | 0 | 12/12, 12/12, 12/12 |
| property-without-backing-criterion | old_skill | 28 | 8 | 8/12, 8/12, 12/12 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| property-without-implementation | Output includes: CRITICAL | 3/3 | 3/3 |
| property-without-implementation | Output includes: FAILED | 3/3 | 3/3 |
| property-without-implementation | Output includes: Property 1 | 3/3 | 3/3 |
| property-without-implementation | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| property-without-implementation | Output does not include: Status: PASSED | 3/3 | 3/3 |
| property-without-implementation | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Raises a CRITICAL discrepancy for acceptance criteria 2.3 and 2.4: no fired-state is recorded or checked, so alerts repeat | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Raises a CRITICAL finding that names Property 1 in design.md, saying that nothing implements it and no test covers it | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Marks the AC 2.3 and 2.4 rows as missing or partial, and AC 2.5, 2.2 and Requirement 1 rows as implemented | 2/3 | 1/3 |
| property-without-implementation | Meets criterion: The Verdict says a green build does not offset the missing behaviour, and the action is to implement the fired-state recording and re-run the audit | 3/3 | 3/3 |
| property-without-implementation | Meets criterion: Pastes the build output from build-output.txt in the Evidence section | 2/3 | 3/3 |
| property-without-backing-criterion | Output includes: MAJOR | 3/3 | 3/3 |
| property-without-backing-criterion | Output includes: FAILED | 3/3 | 3/3 |
| property-without-backing-criterion | Output includes: Property 4 | 3/3 | 3/3 |
| property-without-backing-criterion | Output does not include: **Status:** PASSED | 3/3 | 1/3 |
| property-without-backing-criterion | Output does not include: CRITICAL-0 | 3/3 | 3/3 |
| property-without-backing-criterion | Run produced file: implementation-audit.md | 3/3 | 3/3 |
| property-without-backing-criterion | Meets criterion: Writes implementation-audit.md in the working directory with Status FAILED despite all tasks ticked and a green build | 3/3 | 1/3 |
| property-without-backing-criterion | Meets criterion: Raises a MAJOR finding that names Property 4 and says its Validates line cites Requirement 2.6, which requirements.md does not have | 3/3 | 1/3 |
| property-without-backing-criterion | Meets criterion: Does not raise a CRITICAL finding: Property 4 is implemented by AlertEvaluator and tested by AlertEvaluatorTest | 3/3 | 3/3 |
| property-without-backing-criterion | Meets criterion: Per-Requirement Status marks every acceptance criterion of Requirements 1 and 2 implemented, with evidence naming a code file | 3/3 | 3/3 |
| property-without-backing-criterion | Meets criterion: Does not flag Properties 1, 2 or 3 | 3/3 | 3/3 |
| property-without-backing-criterion | Meets criterion: The Verdict states FAILED and the action is to add the backing criterion to requirements.md or remove Property 4, then re-run the audit | 3/3 | 1/3 |
