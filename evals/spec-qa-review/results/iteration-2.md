# Eval results — iteration-2

**What this iteration tests:** Re-run of iteration 1 after fixture round 1 (send-failure design row, PUT 404 test, repository branches, case 6 forbidden test, outputs path in prompts): Q1, X1-X3, X6 (cases 3,4,5,6,8)

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
| judge_tokens | 1191485 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 1e713db3e6014ad6 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | c6c1ccb9c5cb724a348af42a076df125e394003fa4e91b0ea20bb6a0b8081450 |
| bundle_hash | 4381ad638029261f7544babc5ad483ac9277e196a6cfa050309ecba178394e99 |
| snapshot_hash | 87e04f527cfbf713851324d7d98f0f2b6a3b388748943635136dc4ed67453854 |
| input_drift_allowed | none |
| cases_total | 5 |
| cases_included | 5 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7368 (84/114), B: 0.6842 (78/114) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.858 (±0.1091, n=5) | 139.279 (±17.0028, n=5) | 410,672 (±41,836, n=5) |
| old_skill | 0.6799 (±0.1498, n=5) | 124.9962 (±24.3264, n=5) | 416,829 (±18,140, n=5) |

**Delta (with_skill − old_skill):** pass_rate +0.1781, time_seconds +14.28, tokens -6,157

*measured over 5 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0810 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `review-mode-unit-coverage-below-bar` — Meets criterion: The Unit Actual entry lists only the tests that exist and names the missing planned cases (for example suspended account, plan change, duplicate or out-of-range rejection): 2/3 vs 3/3

with_skill scored above baseline:

- `review-mode-coverage-meets-bar` — Output includes: **Status:** PASSED: 2/3 vs 1/3
- `review-mode-coverage-meets-bar` — Output does not include: **Status:** FAILED: 2/3 vs 1/3
- `review-mode-coverage-meets-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Output does not include: \| Critical \|: 3/3 vs 2/3
- `review-mode-coverage-meets-bar` — Meets criterion: Writes test-plan.md with Status PASSED: 2/3 vs 1/3
- `review-mode-coverage-meets-bar` — Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented: 2/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items: 2/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-unit-coverage-below-bar` — Output includes: QA Gap Report: 3/3 vs 1/3
- `review-mode-unit-coverage-below-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-unit-coverage-below-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output includes: MAJOR: 3/3 vs 1/3
- `review-mode-planned-test-missing` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output does not include: \| Critical \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: A new gap for the missing security tests is added to the gap log with severity MAJOR and listed under MAJOR in the gap report: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Output includes: MINOR: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Output includes: ⚠: 3/3 vs 2/3
- `review-mode-security-partial-passes` — Output includes: spec-maintenance: 1/3 vs 0/3
- `review-mode-security-partial-passes` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Output does not include: \| Critical \|: 2/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: The Security row is marked ⚠ (partial), and its Actual entry names the two tests present and the missing other-Account (forbidden) check: 2/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-second-fail-escalates` — Output does not include: \| Important \|: 3/3 vs 2/3
- `review-mode-second-fail-escalates` — Meets criterion: Stops and asks the user how to proceed, rather than only dispatching fix subagents and re-running review mode: 3/3 vs 2/3
- `review-mode-second-fail-escalates` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| review-mode-coverage-meets-bar | with_skill | 55 | 8 | 19/21, 16/21, 20/21 |
| review-mode-coverage-meets-bar | old_skill | 41 | 22 | 16/21, 12/21, 13/21 |
| review-mode-unit-coverage-below-bar | with_skill | 53 | 7 | 18/20, 18/20, 17/20 |
| review-mode-unit-coverage-below-bar | old_skill | 46 | 14 | 16/20, 15/20, 15/20 |
| review-mode-planned-test-missing | with_skill | 60 | 3 | 20/21, 20/21, 20/21 |
| review-mode-planned-test-missing | old_skill | 46 | 17 | 15/21, 15/21, 16/21 |
| review-mode-security-partial-passes | with_skill | 35 | 19 | 12/18, 11/18, 12/18 |
| review-mode-security-partial-passes | old_skill | 22 | 32 | 7/18, 7/18, 8/18 |
| review-mode-second-fail-escalates | with_skill | 42 | 3 | 14/15, 14/15, 14/15 |
| review-mode-second-fail-escalates | old_skill | 38 | 7 | 12/15, 13/15, 13/15 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| review-mode-coverage-meets-bar | Output includes: **Status:** PASSED | 2/3 | 1/3 |
| review-mode-coverage-meets-bar | Output includes: Pass | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: Fixed | 1/3 | 1/3 |
| review-mode-coverage-meets-bar | Output includes: EvaluatorBudgetTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: ThresholdAuthTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: QA Gap Report | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: **Status:** FAILED | 2/3 | 1/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Critical \| | 3/3 | 2/3 |
| review-mode-coverage-meets-bar | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: Writes test-plan.md with Status PASSED | 2/3 | 1/3 |
| review-mode-coverage-meets-bar | Meets criterion: Works in review mode and updates test-plan.md in the outputs rather than creating a differently structured document | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Actual column is filled in for all five layers, naming the tests found, and the Expected column is preserved unchanged | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure taken from the coverage report (92% lines, 86% branches) | 2/3 | 2/3 |
| review-mode-coverage-meets-bar | Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented | 2/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Test Inventory table lists the test files and what each covers | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items | 2/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: Fail | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: CRITICAL | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: 56% | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: QA Gap Report | 3/3 | 1/3 |
| review-mode-unit-coverage-below-bar | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Output does not include: \| Critical \| | 0/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: Writes test-plan.md with Status FAILED | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: Works in review mode and updates test-plan.md in the outputs, filling the Actual column for all five layers | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The Unit Coverage Gate is marked Fail with the actual figure from the report (56% lines, 38% branches), not Pass | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: A CRITICAL gap for unit coverage below the 85% gate is added to the gap log and listed under CRITICAL in the gap report | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The Unit Actual entry lists only the tests that exist and names the missing planned cases (for example suspended account, plan change, duplicate or out-of-range rejection) | 2/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The Integration, E2E, Performance and Security layers are not marked as gaps, since their planned tests exist | 0/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The reply states that the work item does not pass and does not hand on to sdd:spec-maintenance, pointing to fixing the coverage gaps and re-running review mode | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-planned-test-missing | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: Security | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: MAJOR | 3/3 | 1/3 |
| review-mode-planned-test-missing | Output includes: ThresholdAuthTest | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: QA Gap Report | 3/3 | 3/3 |
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
| review-mode-planned-test-missing | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists, and the Performance row is not marked as a gap | 0/3 | 0/3 |
| review-mode-planned-test-missing | Meets criterion: Does not hand on to sdd:spec-maintenance, since the review failed | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output includes: **Status:** PASSED | 0/3 | 0/3 |
| review-mode-security-partial-passes | Output includes: MINOR | 3/3 | 2/3 |
| review-mode-security-partial-passes | Output includes: ⚠ | 3/3 | 2/3 |
| review-mode-security-partial-passes | Output includes: spec-maintenance | 1/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: **Status:** FAILED | 0/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: \| Critical \| | 2/3 | 0/3 |
| review-mode-security-partial-passes | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Writes test-plan.md with Status PASSED | 0/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: The Security row is marked ⚠ (partial), and its Actual entry names the two tests present and the missing other-Account (forbidden) check | 2/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: A MINOR gap for the missing other-Account check is added to the gap log, and no CRITICAL or MAJOR gap is raised | 0/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure from the report | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Hands on to sdd:spec-maintenance as the next step | 0/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-second-fail-escalates | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output includes: 56% | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output includes: CRITICAL | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: \| Important \| | 3/3 | 2/3 |
| review-mode-second-fail-escalates | Output does not include: \| Critical \| | 0/3 | 0/3 |
| review-mode-second-fail-escalates | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Writes test-plan.md with Status FAILED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: The Unit Coverage Gate is marked Fail with 56% lines and 38% branches, and gap 2 stays Open as CRITICAL | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Recognises from the previous Status in test-plan.md that this is the second failed review | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Stops and asks the user how to proceed, rather than only dispatching fix subagents and re-running review mode | 3/3 | 2/3 |
| review-mode-second-fail-escalates | Meets criterion: Does not hand on to sdd:spec-maintenance | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 1/3 |
