# Eval results — iteration-2

**What this iteration tests:** T1 (Status section) + template X3, fixture round 1: cases 1-4 against 50054f0

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
| judge_tokens | 938360 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | c437f9cdf8af4242 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | ce496b7a947efa9d6da86b73b74db78090555cd01b68fcd7b2ade25bc9a376aa |
| bundle_hash | f57f8cc23a472e1789d0b0f9beebe2d750edc1b754eb031796cdff5d244b9f09 |
| snapshot_hash | 9b3b7063c6770420f256a2462c15567b2d18cdcd79c4b390ef8523e99a99ddcc |
| input_drift_allowed | none |
| cases_total | 4 |
| cases_included | 4 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7308 (57/78), B: 0.7564 (59/78) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8684 (±0.0743, n=4) | 99.7202 (±1.6822, n=4) | 321,574 (±53,872, n=4) |
| old_skill | 0.7551 (±0.1267, n=4) | 109.8231 (±14.1489, n=4) | 277,887 (±25,170, n=4) |

**Delta (with_skill − old_skill):** pass_rate +0.1133, time_seconds -10.10, tokens +43,688

*measured over 4 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2224 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `frontend-before-contract` — Meets criterion: Reports no CRITICAL issues and shows the coverage matrix fully covered: 2/3 vs 3/3

with_skill scored above baseline:

- `complete-tasks-approved` — Output includes: ## Design Coverage: 1/3 vs 0/3
- `complete-tasks-approved` — Output does not include: **Status:** APPROVED WITH ISSUES: 2/3 vs 0/3
- `complete-tasks-approved` — Output does not include: ### MAJOR-: 2/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: Writes task-review-summary.md with Status APPROVED: 2/3 vs 0/3
- `complete-tasks-approved` — Meets criterion: Reports zero CRITICAL and zero MAJOR issues: 2/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: The design coverage table maps each design element to an implementing task: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: Names sdd:spec-qa-review in plan mode as the next step: 2/3 vs 1/3
- `requirement-without-task-blocked` — Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered: 3/3 vs 2/3
- `frontend-before-contract` — Output includes: **Status:** APPROVED WITH ISSUES: 3/3 vs 1/3
- `frontend-before-contract` — Output does not include: **Status:** BLOCKED: 3/3 vs 1/3
- `frontend-before-contract` — Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES: 3/3 vs 1/3
- `frontend-before-contract` — Meets criterion: Proposes moving the frontend task after the OpenAPI, service and handler tasks: 2/3 vs 0/3
- `task-references-undefined-component` — Meets criterion: Gives a specific fix, such as stating the Problem body for 400 and 404 directly or defining the mapper in the design: 2/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| complete-tasks-approved | with_skill | 36 | 12 | 14/16, 7/16, 15/16 |
| complete-tasks-approved | old_skill | 27 | 21 | 8/16, 7/16, 12/16 |
| requirement-without-task-blocked | with_skill | 36 | 3 | 12/13, 12/13, 12/13 |
| requirement-without-task-blocked | old_skill | 35 | 4 | 12/13, 11/13, 12/13 |
| frontend-before-contract | with_skill | 31 | 2 | 10/11, 11/11, 10/11 |
| frontend-before-contract | old_skill | 24 | 9 | 7/11, 10/11, 7/11 |
| task-references-undefined-component | with_skill | 31 | 5 | 11/12, 10/12, 10/12 |
| task-references-undefined-component | old_skill | 30 | 6 | 10/12, 11/12, 9/12 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| complete-tasks-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: spec-qa-review | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: ## Design Coverage | 1/3 | 0/3 |
| complete-tasks-approved | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: **Status:** APPROVED WITH ISSUES | 2/3 | 0/3 |
| complete-tasks-approved | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: ### MAJOR- | 2/3 | 1/3 |
| complete-tasks-approved | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: Writes task-review-summary.md with Status APPROVED | 2/3 | 0/3 |
| complete-tasks-approved | Meets criterion: Reports zero CRITICAL and zero MAJOR issues | 2/3 | 1/3 |
| complete-tasks-approved | Meets criterion: The coverage matrix traces every acceptance criterion from 1.1 to 5.2 to at least one task, with no gap marked | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: The design coverage table maps each design element to an implementing task | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: The sequencing review confirms the OpenAPI task precedes the handler and the frontend task | 2/3 | 2/3 |
| complete-tasks-approved | Meets criterion: All mandatory ending tasks (integration tests, E2E, documentation, final validation) are marked present | 2/3 | 2/3 |
| complete-tasks-approved | Meets criterion: Names sdd:spec-qa-review in plan mode as the next step | 2/3 | 1/3 |
| complete-tasks-approved | Meets criterion: Deletes discovery.md, as the review passed | 0/3 | 0/3 |
| requirement-without-task-blocked | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: CRITICAL | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: ## Design Coverage | 0/3 | 0/3 |
| requirement-without-task-blocked | Output does not include: **Status:** APPROVED | 3/3 | 3/3 |
| requirement-without-task-blocked | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Writes task-review-summary.md with Status BLOCKED | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Raises a CRITICAL finding that names acceptance criterion 2.4 (no alerts for a suspended Account) as having no task | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered | 3/3 | 2/3 |
| requirement-without-task-blocked | Meets criterion: Gives a specific fix to tasks.md, such as adding the suspended check and a test to the AlertEvaluator task and tracing 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: States that tasks.md must be fixed and the review re-run before implementation, and does not approve | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Does not invent further CRITICAL or MAJOR findings about sequencing or the ending tasks | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Keeps discovery.md, as the review is blocked | 3/3 | 3/3 |
| frontend-before-contract | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 1/3 |
| frontend-before-contract | Output includes: MAJOR | 3/3 | 3/3 |
| frontend-before-contract | Output includes: generate | 3/3 | 3/3 |
| frontend-before-contract | Output does not include: **Status:** BLOCKED | 3/3 | 1/3 |
| frontend-before-contract | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| frontend-before-contract | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 1/3 |
| frontend-before-contract | Meets criterion: Raises a MAJOR sequencing finding that the ThresholdSettings frontend task comes before the OpenAPI task and the handler | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Gives the mechanism that the generated ThresholdSet types do not exist yet, so the frontend has nothing to import | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Proposes moving the frontend task after the OpenAPI, service and handler tasks | 2/3 | 0/3 |
| frontend-before-contract | Meets criterion: Reports no CRITICAL issues and shows the coverage matrix fully covered | 2/3 | 3/3 |
| task-references-undefined-component | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| task-references-undefined-component | Output includes: ProblemMapper | 3/3 | 3/3 |
| task-references-undefined-component | Output includes: MAJOR | 3/3 | 3/3 |
| task-references-undefined-component | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| task-references-undefined-component | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| task-references-undefined-component | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Raises a MAJOR implementability finding on the ThresholdsHandler task | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Identifies ProblemMapper as a component that design.md does not define | 0/3 | 0/3 |
| task-references-undefined-component | Meets criterion: Identifies 'add appropriate error handling' as an ambiguous instruction | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Gives a specific fix, such as stating the Problem body for 400 and 404 directly or defining the mapper in the design | 2/3 | 1/3 |
| task-references-undefined-component | Meets criterion: Reports no CRITICAL issues and no sequencing finding | 2/3 | 2/3 |
