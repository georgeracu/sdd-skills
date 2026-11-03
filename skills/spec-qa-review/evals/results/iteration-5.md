# Eval results — iteration-5

**What this iteration tests:** Plan-mode format fixes: layer names unbolded in the skill's matrix table, exact Status placeholder, output path in the plan prompts (cases 1,2)

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
| judge_tokens | 484898 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 0d87bdae1e3dcfbe |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | a690e9892f2691e3d1c7f2635ea9346ee04726a19eb97ede04f17f0675b89fe6 |
| bundle_hash | fd266ee20518061013478465353827095bfc42cfecc9834c154342ea42070a60 |
| snapshot_hash | 87e04f527cfbf713851324d7d98f0f2b6a3b388748943635136dc4ed67453854 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8958 (43/48), B: 0.9375 (45/48) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=2) | 96.3407 (±11.5537, n=2) | 317,938 (±12,154, n=2) |
| old_skill | 0.936 (±0.0085, n=2) | 75.069 (±8.6837, n=2) | 287,040 (±10,309, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0640, time_seconds +21.27, tokens +30,898

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0205 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `plan-mode-full-matrix` — Meets criterion: The Actual column is empty or still a placeholder, and no Pass or Fail result is claimed for the Unit Coverage Gate: 3/3 vs 2/3
- `plan-mode-full-matrix` — Meets criterion: Does not produce a per-requirement traceability matrix or a list of numbered test-case tables in place of the layer matrix: 3/3 vs 2/3
- `plan-mode-full-matrix` — Meets criterion: Any gap log entry uses only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3
- `plan-mode-design-omits-layers` — Meets criterion: Gap severities use only CRITICAL, MAJOR or MINOR: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| plan-mode-full-matrix | with_skill | 69 | 0 | 23/23, 23/23, 23/23 |
| plan-mode-full-matrix | old_skill | 64 | 5 | 21/23, 21/23, 22/23 |
| plan-mode-design-omits-layers | with_skill | 54 | 0 | 18/18, 18/18, 18/18 |
| plan-mode-design-omits-layers | old_skill | 51 | 3 | 17/18, 17/18, 17/18 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| plan-mode-full-matrix | Output includes: ## Coverage Matrix | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: Unit Coverage Gate | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: 85% | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Unit | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Integration | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| E2E | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Performance | 3/3 | 3/3 |
| plan-mode-full-matrix | Output includes: \| Security | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: Traceability Matrix | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: P0 | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: \| High \| | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: \| Medium \| | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| plan-mode-full-matrix | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| plan-mode-full-matrix | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Works in plan mode and writes test-plan.md under the 01-quota-alerts work-item directory in the outputs | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: The coverage matrix has all five layers (Unit, Integration, E2E, Performance, Security), each with an Expected entry and none silently skipped | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Expected entries are specific, naming components and flows from the design (for example ThresholdSet, QuotaAlertEvaluator, the thresholds handler, the fired-state write) rather than generic phrases | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: The Actual column is empty or still a placeholder, and no Pass or Fail result is claimed for the Unit Coverage Gate | 3/3 | 2/3 |
| plan-mode-full-matrix | Meets criterion: The Unit Coverage Gate states the 85% bar | 3/3 | 3/3 |
| plan-mode-full-matrix | Meets criterion: Does not produce a per-requirement traceability matrix or a list of numbered test-case tables in place of the layer matrix | 3/3 | 2/3 |
| plan-mode-full-matrix | Meets criterion: Any gap log entry uses only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| plan-mode-full-matrix | Meets criterion: Writes no PASSED or FAILED Status, since plan mode runs no gate | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: \| Security | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: \| Performance | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output includes: Gap Log | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| High \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| Medium \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: \| Low \| | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: P1 | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: **Status:** PASSED | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Output does not include: **Status:** FAILED | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Run produced file: 01-quota-alerts/test-plan.md | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Works in plan mode and writes test-plan.md under the 01-quota-alerts work-item directory in the outputs | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The matrix still contains the Security row with a specific Expected entry derived from the requirements (unauthorised or unknown-Account calls leave the Threshold_Set unchanged), not left blank or silently dropped | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The matrix still contains the Performance row, either with an Expected entry about the ingest path or flagged as a gap, and is not marked N/A without a justification | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: The omission of security and performance from the design is flagged explicitly, in the plan, the gap log or the reply, as a gap | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Gap severities use only CRITICAL, MAJOR or MINOR | 3/3 | 0/3 |
| plan-mode-design-omits-layers | Meets criterion: The Actual column is empty or a placeholder because no tests exist | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Does not modify or regenerate design.md | 3/3 | 3/3 |
| plan-mode-design-omits-layers | Meets criterion: Writes no PASSED or FAILED Status, since plan mode runs no gate | 3/3 | 3/3 |
