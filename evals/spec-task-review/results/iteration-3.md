# Eval results — iteration-3

**What this iteration tests:** T2 (unverifiable paths) + T3 (artefact sequencing rule), fixture round 2: cases 1, 3 against T1

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
| judge_tokens | 412364 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 3b29cbd4763d905e |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | ff25fc9a9115124c036a6f86a3653eda5b823afaf2002f9a8a0454f797849c70 |
| bundle_hash | 247135e596bf4ea70d891b99a65f32e183d76defcdd810139fdf043e02ad5b49 |
| snapshot_hash | f57f8cc23a472e1789d0b0f9beebe2d750edc1b754eb031796cdff5d244b9f09 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8718 (34/39), B: 0.7179 (28/39) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9281 (±0.0114, n=2) | 92.2027 (±12.39, n=2) | 273,769 (±753, n=2) |
| old_skill | 0.8343 (±0.1051, n=2) | 103.3547 (±1.458, n=2) | 295,123 (±18,136, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0938, time_seconds -11.15, tokens -21,354

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2125 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `complete-tasks-approved` — Output includes: ## Design Coverage: 2/3 vs 1/3
- `complete-tasks-approved` — Output does not include: **Status:** APPROVED WITH ISSUES: 3/3 vs 2/3
- `complete-tasks-approved` — Output does not include: ### MAJOR-: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: Writes task-review-summary.md with Status APPROVED: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: Reports zero CRITICAL and zero MAJOR issues: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: The design coverage table maps each design element to an implementing task: 3/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: The sequencing review confirms the OpenAPI task precedes the handler and the frontend task: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: All mandatory ending tasks (integration tests, E2E, documentation, final validation) are marked present: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| complete-tasks-approved | with_skill | 44 | 4 | 14/16, 15/16, 15/16 |
| complete-tasks-approved | old_skill | 35 | 13 | 15/16, 7/16, 13/16 |
| frontend-before-contract | with_skill | 31 | 2 | 11/11, 10/11, 10/11 |
| frontend-before-contract | old_skill | 31 | 2 | 11/11, 10/11, 10/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| complete-tasks-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: spec-qa-review | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: ## Design Coverage | 2/3 | 1/3 |
| complete-tasks-approved | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: **Status:** APPROVED WITH ISSUES | 3/3 | 2/3 |
| complete-tasks-approved | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: ### MAJOR- | 3/3 | 2/3 |
| complete-tasks-approved | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: Writes task-review-summary.md with Status APPROVED | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: Reports zero CRITICAL and zero MAJOR issues | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: The coverage matrix traces every acceptance criterion from 1.1 to 5.2 to at least one task, with no gap marked | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: The design coverage table maps each design element to an implementing task | 3/3 | 1/3 |
| complete-tasks-approved | Meets criterion: The sequencing review confirms the OpenAPI task precedes the handler and the frontend task | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: All mandatory ending tasks (integration tests, E2E, documentation, final validation) are marked present | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: Names sdd:spec-qa-review in plan mode as the next step | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: Deletes discovery.md, as the review passed | 0/3 | 0/3 |
| frontend-before-contract | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| frontend-before-contract | Output includes: MAJOR | 3/3 | 3/3 |
| frontend-before-contract | Output includes: generate | 3/3 | 3/3 |
| frontend-before-contract | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| frontend-before-contract | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| frontend-before-contract | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Raises a MAJOR sequencing finding that the ThresholdSettings frontend task comes before the OpenAPI task and the handler | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Gives the mechanism that the generated ThresholdSet types do not exist yet, so the frontend has nothing to import | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Proposes moving the frontend task after the OpenAPI, service and handler tasks | 1/3 | 1/3 |
| frontend-before-contract | Meets criterion: Reports no CRITICAL issues and shows the coverage matrix fully covered | 3/3 | 3/3 |
