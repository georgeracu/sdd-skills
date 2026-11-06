# Eval results — iteration-4

**What this iteration tests:** Full pack: all #27 edits (Q1-Q7, X1-X3, X5, X6, cuts, description) against the skill at 650a6f4

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
| judge_tokens | 2167871 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 9aa4a2597275303d |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 42491893e85f9ecb469219fae8ea2e80739755c8788508a6f6146f47b624c34d |
| bundle_hash | 804d86aa001d690f6685b415b30cbdc5b6749c8feedf9930f4736376c9d2a682 |
| snapshot_hash | 87e04f527cfbf713851324d7d98f0f2b6a3b388748943635136dc4ed67453854 |
| input_drift_allowed | none |
| cases_total | 8 |
| cases_included | 8 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7778 (140/180), B: 0.8167 (147/180) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9122 (±0.0425, n=8) | 125.6146 (±18.2944, n=8) | 439,201 (±72,626, n=8) |
| old_skill | 0.7767 (±0.0844, n=8) | 115.4775 (±29.3506, n=8) | 389,887 (±79,094, n=8) |

**Delta (with_skill − old_skill):** pass_rate +0.1355, time_seconds +10.14, tokens +49,314

*measured over 8 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0939 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `plan-mode-full-matrix` — Output includes: \| Unit: 2/3 vs 3/3
- `plan-mode-full-matrix` — Output includes: \| Integration: 2/3 vs 3/3
- `plan-mode-full-matrix` — Output includes: \| E2E: 2/3 vs 3/3
- `plan-mode-full-matrix` — Output includes: \| Performance: 2/3 vs 3/3
- `plan-mode-full-matrix` — Output includes: \| Security: 2/3 vs 3/3
- `plan-mode-full-matrix` — Run produced file: 01-quota-alerts/test-plan.md: 0/3 vs 1/3
- `plan-mode-full-matrix` — Meets criterion: Works in plan mode and writes test-plan.md under the 01-quota-alerts work-item directory in the outputs: 2/3 vs 3/3
- `plan-mode-design-omits-layers` — Output includes: \| Security: 2/3 vs 3/3
- `plan-mode-design-omits-layers` — Output includes: \| Performance: 2/3 vs 3/3
- `review-mode-unit-coverage-below-bar` — Meets criterion: The reply states that the work item does not pass and does not hand on to sdd:spec-maintenance, pointing to fixing the coverage gaps and re-running review mode: 2/3 vs 3/3

with_skill scored above baseline:

- `plan-mode-full-matrix` — Meets criterion: Any gap log entry uses only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `plan-mode-design-omits-layers` — Meets criterion: The omission of security and performance from the design is flagged explicitly, in the plan, the gap log or the reply, as a gap: 3/3 vs 2/3
- `plan-mode-design-omits-layers` — Meets criterion: Gap severities use only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `plan-mode-design-omits-layers` — Meets criterion: Writes no PASSED or FAILED Status, since plan mode runs no gate: 3/3 vs 1/3
- `review-mode-coverage-meets-bar` — Output includes: **Status:** PASSED: 3/3 vs 2/3
- `review-mode-coverage-meets-bar` — Output includes: Fixed: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Output does not include: **Status:** FAILED: 3/3 vs 2/3
- `review-mode-coverage-meets-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Output does not include: \| Critical \|: 3/3 vs 2/3
- `review-mode-coverage-meets-bar` — Meets criterion: Writes test-plan.md with Status PASSED: 3/3 vs 2/3
- `review-mode-coverage-meets-bar` — Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items: 3/3 vs 0/3
- `review-mode-coverage-meets-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-unit-coverage-below-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-unit-coverage-below-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output includes: MAJOR: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Output does not include: \| Critical \|: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: A new gap for the missing security tests is added to the gap log with severity MAJOR and listed under MAJOR in the gap report: 3/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists, and the Performance row is not marked as a gap: 1/3 vs 0/3
- `review-mode-planned-test-missing` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Output includes: spec-maintenance: 2/3 vs 0/3
- `review-mode-security-partial-passes` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: A MINOR gap for the missing payload check is added to the gap log, and no CRITICAL or MAJOR gap is raised: 2/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: Hands on to sdd:spec-maintenance as the next step: 2/3 vs 0/3
- `review-mode-security-partial-passes` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-branch-coverage-below-bar` — Output does not include: \| Important \|: 3/3 vs 0/3
- `review-mode-branch-coverage-below-bar` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `review-mode-second-fail-escalates` — Output does not include: \| Important \|: 3/3 vs 1/3
- `review-mode-second-fail-escalates` — Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| plan-mode-full-matrix | with_skill | 60 | 9 | 17/23, 22/23, 21/23 |
| plan-mode-full-matrix | old_skill | 64 | 5 | 21/23, 21/23, 22/23 |
| plan-mode-design-omits-layers | with_skill | 49 | 5 | 17/18, 15/18, 17/18 |
| plan-mode-design-omits-layers | old_skill | 45 | 9 | 16/18, 14/18, 15/18 |
| review-mode-coverage-meets-bar | with_skill | 62 | 1 | 20/21, 21/21, 21/21 |
| review-mode-coverage-meets-bar | old_skill | 43 | 20 | 15/21, 12/21, 16/21 |
| review-mode-unit-coverage-below-bar | with_skill | 53 | 7 | 18/20, 18/20, 17/20 |
| review-mode-unit-coverage-below-bar | old_skill | 48 | 12 | 16/20, 16/20, 16/20 |
| review-mode-planned-test-missing | with_skill | 61 | 2 | 20/21, 21/21, 20/21 |
| review-mode-planned-test-missing | old_skill | 45 | 18 | 15/21, 15/21, 15/21 |
| review-mode-security-partial-passes | with_skill | 48 | 6 | 15/18, 15/18, 18/18 |
| review-mode-security-partial-passes | old_skill | 36 | 18 | 12/18, 11/18, 13/18 |
| review-mode-branch-coverage-below-bar | with_skill | 44 | 7 | 15/17, 14/17, 15/17 |
| review-mode-branch-coverage-below-bar | old_skill | 38 | 13 | 12/17, 13/17, 13/17 |
| review-mode-second-fail-escalates | with_skill | 42 | 3 | 14/15, 14/15, 14/15 |
| review-mode-second-fail-escalates | old_skill | 38 | 7 | 14/15, 12/15, 12/15 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| plan-mode-full-matrix | Output includes: ## Coverage Matrix | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: Unit Coverage Gate | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: 85% | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Unit | 2/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Integration | 2/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| E2E | 2/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Performance | 2/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Security | 2/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: Traceability Matrix | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: P0 | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: \| High \| | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: \| Medium \| | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| plan-mode-full-matrix | Run produced file: 01-quota-alerts/test-plan.md | 0/3 | 1/3 |
| plan-mode-full-matrix | Meets criterion: Works in plan mode and writes test-plan.md under the 01-quota-alerts work-item directory in the outputs | 2/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: The coverage matrix has all five layers (Unit, Integration, E2E, Performance, Security), each with an Expected entry and none silently skipped | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Expected entries are specific, naming components and flows from the design (for example ThresholdSet, QuotaAlertEvaluator, the thresholds handler, the fired-state write) rather than generic phrases | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: The Actual column is empty or still a placeholder, and no Pass or Fail result is claimed for the Unit Coverage Gate | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: The Unit Coverage Gate states the 85% bar | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Does not produce a per-requirement traceability matrix or a list of numbered test-case tables in place of the layer matrix | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Any gap log entry uses only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| plan-mode-full-matrix | Meets criterion: Writes no PASSED or FAILED Status, since plan mode runs no gate | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: \| Security | 2/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: \| Performance | 2/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: Gap Log | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| High \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| Medium \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| Low \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: P1 | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Run produced file: 01-quota-alerts/test-plan.md | 0/3 | 0/3 |
| plan-mode-design-omits-layers | Meets criterion: Works in plan mode and writes test-plan.md under the 01-quota-alerts work-item directory in the outputs | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The matrix still contains the Security row with a specific Expected entry derived from the requirements (unauthorised or unknown-Account calls leave the Threshold_Set unchanged), not left blank or silently dropped | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The matrix still contains the Performance row, either with an Expected entry about the ingest path or flagged as a gap, and is not marked N/A without a justification | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The omission of security and performance from the design is flagged explicitly, in the plan, the gap log or the reply, as a gap | 3/3 | 2/3 |
| plan-mode-design-omits-layers | Meets criterion: Gap severities use only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| plan-mode-design-omits-layers | Meets criterion: The Actual column is empty or a placeholder because no tests exist | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Does not modify or regenerate design.md | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Writes no PASSED or FAILED Status, since plan mode runs no gate | 3/3 | 1/3 |
| review-mode-coverage-meets-bar | Output includes: **Status:** PASSED | 3/3 | 2/3 |
| review-mode-coverage-meets-bar | Output includes: Pass | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: Fixed | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Output includes: EvaluatorBudgetTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: ThresholdAuthTest | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output includes: QA Gap Report | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Output does not include: **Status:** FAILED | 3/3 | 2/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Output does not include: \| Critical \| | 3/3 | 2/3 |
| review-mode-coverage-meets-bar | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: Writes test-plan.md with Status PASSED | 3/3 | 2/3 |
| review-mode-coverage-meets-bar | Meets criterion: Works in review mode and updates test-plan.md in the outputs rather than creating a differently structured document | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Actual column is filled in for all five layers, naming the tests found, and the Expected column is preserved unchanged | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure taken from the coverage report (92% lines, 86% branches) | 2/3 | 2/3 |
| review-mode-coverage-meets-bar | Meets criterion: Gap 1 is marked Fixed with a Closed date, citing EvaluatorBudgetTest, and no new CRITICAL or MAJOR gap is invented | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: The Test Inventory table lists the test files and what each covers | 3/3 | 3/3 |
| review-mode-coverage-meets-bar | Meets criterion: The conversation includes a QA Gap Report with the Coverage Summary table, a unit coverage line against the 85% gate, and an Action Items section with no CRITICAL or MAJOR items | 3/3 | 0/3 |
| review-mode-coverage-meets-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: Fail | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: CRITICAL | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: 56% | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Output includes: QA Gap Report | 3/3 | 3/3 |
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
| review-mode-unit-coverage-below-bar | Meets criterion: The Unit Actual entry lists only the tests that exist and names the missing planned cases (for example suspended account, plan change, duplicate or out-of-range rejection) | 3/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The Integration, E2E, Performance and Security layers are not marked as gaps, since their planned tests exist | 0/3 | 0/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: The reply states that the work item does not pass and does not hand on to sdd:spec-maintenance, pointing to fixing the coverage gaps and re-running review mode | 2/3 | 3/3 |
| review-mode-unit-coverage-below-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-planned-test-missing | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: Security | 3/3 | 3/3 |
| review-mode-planned-test-missing | Output includes: MAJOR | 3/3 | 0/3 |
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
| review-mode-planned-test-missing | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists, and the Performance row is not marked as a gap | 1/3 | 0/3 |
| review-mode-planned-test-missing | Meets criterion: Does not hand on to sdd:spec-maintenance, since the review failed | 3/3 | 3/3 |
| review-mode-planned-test-missing | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output includes: MINOR | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output includes: ⚠ | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output includes: spec-maintenance | 2/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: [Filled in review mode] | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-security-partial-passes | Output does not include: \| Important \| | 3/3 | 0/3 |
| review-mode-security-partial-passes | Output does not include: \| Critical \| | 3/3 | 3/3 |
| review-mode-security-partial-passes | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Writes test-plan.md with Status PASSED | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: The Security row is marked ⚠ (partial), and its Actual entry names the two tests present and the missing owner-email payload check | 2/3 | 2/3 |
| review-mode-security-partial-passes | Meets criterion: A MINOR gap for the missing payload check is added to the gap log, and no CRITICAL or MAJOR gap is raised | 2/3 | 0/3 |
| review-mode-security-partial-passes | Meets criterion: The Unit Coverage Gate is marked Pass with the actual figure from the report | 1/3 | 1/3 |
| review-mode-security-partial-passes | Meets criterion: Gap 1 is marked Fixed because EvaluatorBudgetTest exists | 3/3 | 3/3 |
| review-mode-security-partial-passes | Meets criterion: Hands on to sdd:spec-maintenance as the next step | 2/3 | 0/3 |
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
| review-mode-branch-coverage-below-bar | Meets criterion: Does not hand on to sdd:spec-maintenance, and points to closing the coverage gap and re-running review mode | 2/3 | 2/3 |
| review-mode-branch-coverage-below-bar | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| review-mode-second-fail-escalates | Output includes: **Status:** FAILED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output includes: 56% | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output includes: CRITICAL | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: \| High \| | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: P0 | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Output does not include: \| Important \| | 3/3 | 1/3 |
| review-mode-second-fail-escalates | Output does not include: \| Critical \| | 0/3 | 0/3 |
| review-mode-second-fail-escalates | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Writes test-plan.md with Status FAILED | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: The Unit Coverage Gate is marked Fail with 56% lines and 38% branches, and gap 2 stays Open as CRITICAL | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Recognises from the previous Status in test-plan.md that this is the second failed review | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Stops and asks the user how to proceed, rather than only dispatching fix subagents and re-running review mode | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Does not hand on to sdd:spec-maintenance | 3/3 | 3/3 |
| review-mode-second-fail-escalates | Meets criterion: Severity labels are only CRITICAL, MAJOR or MINOR | 3/3 | 1/3 |
