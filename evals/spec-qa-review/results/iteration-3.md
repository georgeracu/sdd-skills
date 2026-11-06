# Eval results — iteration-3

**What this iteration tests:** Q2 status symbols and Q3 line-and-branch bar, after fixture round 2 (review-only openapi.yaml with the thresholds path; case 6 drops the payload check) (cases 3,5,6,7)

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.316 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 952063 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 47820fb4e058c26e |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | f71f741b1d1e10009ccd0412bb5f10b07a9c427e48df59d6b1b0d2ddd5521c63 |
| bundle_hash | 3fe4832ca1ff4b070492830bb3d3b85b2d33bfc1c69fc8d4f33fcb0a44742e41 |
| snapshot_hash | 87e04f527cfbf713851324d7d98f0f2b6a3b388748943635136dc4ed67453854 |
| input_drift_allowed | none |
| cases_total | 4 |
| cases_included | 4 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6778 (61/90), B: 0.8333 (75/90) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9283 (±0.0286, n=4) | 129.629 (±16.0171, n=4) | 426,717 (±22,759, n=4) |
| old_skill | 0.7216 (±0.0536, n=4) | 138.7636 (±6.8587, n=4) | 440,411 (±41,629, n=4) |

**Delta (with_skill − old_skill):** pass_rate +0.2067, time_seconds -9.13, tokens -13,694

*measured over 4 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0944 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `review-mode-planned-test-missing` — Output includes: QA Gap Report: 2/3 vs 3/3

with_skill scored above baseline:

- `review-mode-coverage-meets-bar` — Output includes: Fixed: 1/3 vs 0/3
- `review-mode-coverage-meets-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output includes: MAJOR: 3/3 vs 2/3
- `review-mode-planned-test-missing` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output does not include: \| Critical \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: A new gap for the missing security tests is added to the gap log with severity MAJOR and listed under MAJOR in the gap report: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists, and the Performance row is not marked as a gap: 1/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Output includes: **Status:** PASSED: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Output includes: MINOR: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Output does not include: **Status:** FAILED: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Output does not include: \| Critical \|: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Meets criterion: Writes test-plan.md with Status PASSED: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Meets criterion: The Security row is marked ⚠ (partial), and its Actual entry names the two tests present and the missing owner-email payload check: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Meets criterion: A MINOR gap for the missing payload check is added to the gap log, and no CRITICAL or MAJOR gap is raised: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: Hands on to sdd:spec-maintenance as the next step: 1/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-branch-coverage-below-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-branch-coverage-below-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| review-mode-coverage-meets-bar | with_skill | 60 | 3 | 19/21, 20/21, 21/21 |
| review-mode-coverage-meets-bar | old_skill | 47 | 16 | 15/21, 16/21, 16/21 |
| review-mode-planned-test-missing | with_skill | 60 | 3 | 20/21, 20/21, 20/21 |
| review-mode-planned-test-missing | old_skill | 47 | 16 | 16/21, 16/21, 15/21 |
| review-mode-security-partial-passes | with_skill | 50 | 4 | 16/18, 18/18, 16/18 |
| review-mode-security-partial-passes | old_skill | 34 | 20 | 13/18, 12/18, 9/18 |
| review-mode-branch-coverage-below-bar | with_skill | 45 | 6 | 15/17, 15/17, 15/17 |
| review-mode-branch-coverage-below-bar | old_skill | 39 | 12 | 13/17, 13/17, 13/17 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| review-mode-coverage-meets-bar | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: Pass | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: Fixed | 1/3 | 0/3 |
| review-mode-coverage-meets-bar | Output includes: EvaluatorBudgetTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: ThresholdAuthTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: QA Gap Report | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Critical \| | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: Writes test-plan.md with Status PASSED | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: Works in review mode and updates test-plan.md in the outputs rather than creating a differently structured document | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Actual column is filled in for all five layers, naming the tests found, and the Expected column is preserved unchanged | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure taken from the coverage report (92% lines, 86% branches) | 2/3 | 2/3 |
| review-mode-coverage-meets-bar | Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Test Inventory table lists the test files and what each covers | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-planned-test-missing | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: Security | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: MAJOR | 3/3 | 2/3 |
| review-mode-planned-test-missing | Output includes: ThresholdAuthTest | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: QA Gap Report | 2/3 | 3/3 |
| review-mode-planned-test-missing | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-planned-test-missing | Output does not include: \| Critical \| | 3/3 | 0/3 |
| review-mode-planned-test-missing | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Writes test-plan.md with Status FAILED | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Works in review mode and updates test-plan.md in the outputs, filling the Actual column | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: The Security Actual entry states that no security tests exist (ThresholdAuthTest is absent from the code tree) and the Security row is marked ✗, not as meeting Expected | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: ThresholdAuthTest is not listed in the Test Inventory as an existing file | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: A new gap for the missing security tests is added to the gap log with severity MAJOR and listed under MAJOR in the gap report | 3/3 | 0/3 |
| review-mode-planned-test-missing | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure from the report | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists, and the Performance row is not marked as a gap | 1/3 | 0/3 |
| review-mode-planned-test-missing | Meets criterion: Does not hand on to sdd:spec-maintenance, since the review failed | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output includes: **Status:** PASSED | 3/3 | 2/3 |
| review-mode-security-partial-passes | Output includes: MINOR | 3/3 | 2/3 |
| review-mode-security-partial-passes | Output includes: ⚠ | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output includes: spec-maintenance | 1/3 | 1/3 |
| review-mode-security-partial-passes | Output does not include: **Status:** FAILED | 3/3 | 2/3 |
| review-mode-security-partial-passes | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: \| Critical \| | 3/3 | 2/3 |
| review-mode-security-partial-passes | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Writes test-plan.md with Status PASSED | 3/3 | 2/3 |
| review-mode-security-partial-passes | Meets criterion: The Security row is marked ⚠ (partial), and its Actual entry names the two tests present and the missing owner-email payload check | 3/3 | 2/3 |
| review-mode-security-partial-passes | Meets criterion: A MINOR gap for the missing payload check is added to the gap log, and no CRITICAL or MAJOR gap is raised | 3/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure from the report | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Hands on to sdd:spec-maintenance as the next step | 1/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-branch-coverage-below-bar | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output includes: 80% | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output includes: CRITICAL | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output includes: Fail | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-branch-coverage-below-bar | Output does not include: \| Critical \| | 0/3 | 0/3 |
| review-mode-branch-coverage-below-bar | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: Writes test-plan.md with Status FAILED | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: The Unit Coverage Gate is marked Fail, citing 80% branch coverage against the 85% bar, and is not passed on the 92% line figure | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: A CRITICAL gap for branch coverage below 85% is added to the gap log | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: The other layers are not marked as gaps, since their planned tests exist | 0/3 | 0/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: Does not hand on to sdd:spec-maintenance, and points to closing the coverage gap and re-running review mode | 3/3 | 3/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
