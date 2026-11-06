# Eval results — iteration-5

**What this iteration tests:** Full pack: all #26 edits (T1-T8, cuts, description) against 50054f0, cases 1-7

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
| judge_tokens | 1520323 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 650ab46cf423355a |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 6abf7912bfb1e8fd38f1d9c6af2edd712a39f5c61c8f73d5aca1b5488e4ed559 |
| bundle_hash | 83918d6da7c90983940166b42e2f1b329ac0f2d45393a738d51a30312ea36ef9 |
| snapshot_hash | 9b3b7063c6770420f256a2462c15567b2d18cdcd79c4b390ef8523e99a99ddcc |
| input_drift_allowed | none |
| cases_total | 7 |
| cases_included | 7 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8049 (99/123), B: 0.813 (100/123) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8979 (±0.0933, n=7) | 101.7498 (±8.8468, n=7) | 294,198 (±24,085, n=7) |
| old_skill | 0.8407 (±0.1504, n=7) | 101.2311 (±14.3123, n=7) | 309,350 (±29,886, n=7) |

**Delta (with_skill − old_skill):** pass_rate +0.0572, time_seconds +0.52, tokens -15,151

*measured over 7 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2176 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `requirement-without-task-blocked` — Output includes: **Status:** BLOCKED: 2/3 vs 3/3
- `requirement-without-task-blocked` — Output does not include: **Status:** APPROVED: 2/3 vs 3/3
- `requirement-without-task-blocked` — Meets criterion: Writes task-review-summary.md with Status BLOCKED: 2/3 vs 3/3
- `requirement-without-task-blocked` — Meets criterion: Raises a CRITICAL finding that names acceptance criterion 2.4 (no alerts for a suspended Account) as having no task: 2/3 vs 3/3
- `requirement-without-task-blocked` — Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered: 2/3 vs 3/3
- `requirement-without-task-blocked` — Meets criterion: States that tasks.md must be fixed and the review re-run before implementation, and does not approve: 2/3 vs 3/3
- `oversized-task` — Output includes: ### MAJOR-: 0/3 vs 3/3
- `combined-tests-minor` — Meets criterion: Treats the combined test task as at most a MINOR suggestion to split it: 1/3 vs 2/3

with_skill scored above baseline:

- `complete-tasks-approved` — Output includes: ## Design Coverage: 3/3 vs 1/3
- `complete-tasks-approved` — Output does not include: **Status:** APPROVED WITH ISSUES: 3/3 vs 0/3
- `complete-tasks-approved` — Output does not include: ### MAJOR-: 3/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: Writes task-review-summary.md with Status APPROVED: 3/3 vs 0/3
- `complete-tasks-approved` — Meets criterion: Reports zero CRITICAL and zero MAJOR issues: 3/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: The design coverage table maps each design element to an implementing task: 3/3 vs 2/3
- `complete-tasks-approved` — Meets criterion: All mandatory ending tasks (integration tests, E2E, documentation, final validation) are marked present: 3/3 vs 1/3
- `complete-tasks-approved` — Meets criterion: Deletes discovery.md, as the review passed: 1/3 vs 0/3
- `requirement-without-task-blocked` — Output includes: ## Design Coverage: 3/3 vs 1/3
- `frontend-before-contract` — Meets criterion: Proposes moving the frontend task after the OpenAPI, service and handler tasks: 1/3 vs 0/3
- `task-references-undefined-component` — Meets criterion: Identifies ProblemMapper as a component that design.md does not define: 2/3 vs 0/3
- `task-references-undefined-component` — Meets criterion: Gives a specific fix, such as stating the Problem body for 400 and 404 directly or defining the mapper in the design: 2/3 vs 0/3
- `combined-tests-minor` — Output does not include: **Status:** APPROVED WITH ISSUES: 1/3 vs 0/3
- `combined-tests-minor` — Output does not include: ### MAJOR-: 3/3 vs 1/3
- `combined-tests-minor` — Meets criterion: Writes task-review-summary.md with Status APPROVED: 1/3 vs 0/3
- `second-block-escalates` — Meets criterion: Stops and asks the user how to proceed, rather than only telling the author to fix tasks.md and re-run the review: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| complete-tasks-approved | with_skill | 46 | 2 | 15/16, 16/16, 15/16 |
| complete-tasks-approved | old_skill | 30 | 18 | 9/16, 13/16, 8/16 |
| requirement-without-task-blocked | with_skill | 33 | 6 | 13/13, 13/13, 7/13 |
| requirement-without-task-blocked | old_skill | 37 | 2 | 12/13, 12/13, 13/13 |
| frontend-before-contract | with_skill | 31 | 2 | 11/11, 10/11, 10/11 |
| frontend-before-contract | old_skill | 30 | 3 | 10/11, 10/11, 10/11 |
| task-references-undefined-component | with_skill | 34 | 2 | 12/12, 12/12, 10/12 |
| task-references-undefined-component | old_skill | 30 | 6 | 10/12, 10/12, 10/12 |
| oversized-task | with_skill | 27 | 3 | 9/10, 9/10, 9/10 |
| oversized-task | old_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| combined-tests-minor | with_skill | 23 | 10 | 6/11, 7/11, 10/11 |
| combined-tests-minor | old_skill | 20 | 13 | 9/11, 6/11, 5/11 |
| second-block-escalates | with_skill | 27 | 0 | 9/9, 9/9, 9/9 |
| second-block-escalates | old_skill | 26 | 1 | 9/9, 8/9, 9/9 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| complete-tasks-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: spec-qa-review | 3/3 | 3/3 |
| complete-tasks-approved | Output includes: ## Design Coverage | 3/3 | 1/3 |
| complete-tasks-approved | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: **Status:** APPROVED WITH ISSUES | 3/3 | 0/3 |
| complete-tasks-approved | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| complete-tasks-approved | Output does not include: ### MAJOR- | 3/3 | 1/3 |
| complete-tasks-approved | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: Writes task-review-summary.md with Status APPROVED | 3/3 | 0/3 |
| complete-tasks-approved | Meets criterion: Reports zero CRITICAL and zero MAJOR issues | 3/3 | 1/3 |
| complete-tasks-approved | Meets criterion: The coverage matrix traces every acceptance criterion from 1.1 to 5.2 to at least one task, with no gap marked | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: The design coverage table maps each design element to an implementing task | 3/3 | 2/3 |
| complete-tasks-approved | Meets criterion: The sequencing review confirms the OpenAPI task precedes the handler and the frontend task | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: All mandatory ending tasks (integration tests, E2E, documentation, final validation) are marked present | 3/3 | 1/3 |
| complete-tasks-approved | Meets criterion: Names sdd:spec-qa-review in plan mode as the next step | 3/3 | 3/3 |
| complete-tasks-approved | Meets criterion: Deletes discovery.md, as the review passed | 1/3 | 0/3 |
| requirement-without-task-blocked | Output includes: **Status:** BLOCKED | 2/3 | 3/3 |
| requirement-without-task-blocked | Output includes: 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: CRITICAL | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: ## Design Coverage | 3/3 | 1/3 |
| requirement-without-task-blocked | Output does not include: **Status:** APPROVED | 2/3 | 3/3 |
| requirement-without-task-blocked | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Writes task-review-summary.md with Status BLOCKED | 2/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Raises a CRITICAL finding that names acceptance criterion 2.4 (no alerts for a suspended Account) as having no task | 2/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered | 2/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Gives a specific fix to tasks.md, such as adding the suspended check and a test to the AlertEvaluator task and tracing 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: States that tasks.md must be fixed and the review re-run before implementation, and does not approve | 2/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Does not invent further CRITICAL or MAJOR findings about sequencing or the ending tasks | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Keeps discovery.md, as the review is blocked | 3/3 | 3/3 |
| frontend-before-contract | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| frontend-before-contract | Output includes: MAJOR | 3/3 | 3/3 |
| frontend-before-contract | Output includes: generate | 3/3 | 3/3 |
| frontend-before-contract | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| frontend-before-contract | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| frontend-before-contract | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Raises a MAJOR sequencing finding that the ThresholdSettings frontend task comes before the OpenAPI task and the handler | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Gives the mechanism that the generated ThresholdSet types do not exist yet, so the frontend has nothing to import | 3/3 | 3/3 |
| frontend-before-contract | Meets criterion: Proposes moving the frontend task after the OpenAPI, service and handler tasks | 1/3 | 0/3 |
| frontend-before-contract | Meets criterion: Reports no CRITICAL issues and shows the coverage matrix fully covered | 3/3 | 3/3 |
| task-references-undefined-component | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| task-references-undefined-component | Output includes: ProblemMapper | 3/3 | 3/3 |
| task-references-undefined-component | Output includes: MAJOR | 3/3 | 3/3 |
| task-references-undefined-component | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| task-references-undefined-component | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| task-references-undefined-component | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Raises a MAJOR implementability finding on the ThresholdsHandler task | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Identifies ProblemMapper as a component that design.md does not define | 2/3 | 0/3 |
| task-references-undefined-component | Meets criterion: Identifies 'add appropriate error handling' as an ambiguous instruction | 3/3 | 3/3 |
| task-references-undefined-component | Meets criterion: Gives a specific fix, such as stating the Problem body for 400 and 404 directly or defining the mapper in the design | 2/3 | 0/3 |
| task-references-undefined-component | Meets criterion: Reports no CRITICAL issues and no sequencing finding | 3/3 | 3/3 |
| oversized-task | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| oversized-task | Output includes: ### MAJOR- | 0/3 | 3/3 |
| oversized-task | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| oversized-task | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| oversized-task | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| oversized-task | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| oversized-task | Meets criterion: Raises a MAJOR scope finding on Task 3 | 3/3 | 3/3 |
| oversized-task | Meets criterion: Names the components Task 3 spans, including the frontend ThresholdSettings component and at least one backend component | 3/3 | 3/3 |
| oversized-task | Meets criterion: Proposes splitting Task 3 into separate tasks by component or layer | 3/3 | 3/3 |
| oversized-task | Meets criterion: Reports no CRITICAL issues | 3/3 | 3/3 |
| combined-tests-minor | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: **Status:** APPROVED WITH ISSUES | 1/3 | 0/3 |
| combined-tests-minor | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: ### MAJOR- | 3/3 | 1/3 |
| combined-tests-minor | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| combined-tests-minor | Meets criterion: Writes task-review-summary.md with Status APPROVED | 1/3 | 0/3 |
| combined-tests-minor | Meets criterion: Reports zero CRITICAL and zero MAJOR issues | 1/3 | 1/3 |
| combined-tests-minor | Meets criterion: Marks the integration test and the end-to-end test ending tasks as present | 3/3 | 3/3 |
| combined-tests-minor | Meets criterion: Treats the combined test task as at most a MINOR suggestion to split it | 1/3 | 2/3 |
| combined-tests-minor | Meets criterion: Names sdd:spec-qa-review in plan mode as the next step | 1/3 | 1/3 |
| second-block-escalates | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| second-block-escalates | Output includes: 2.4 | 3/3 | 3/3 |
| second-block-escalates | Output does not include: **Status:** APPROVED | 3/3 | 3/3 |
| second-block-escalates | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Writes task-review-summary.md with Status BLOCKED | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Raises a CRITICAL finding naming AC 2.4 (suspended Account) as still having no implementing task | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Recognises from the previous summary that this is the second BLOCKED run on the same issue | 3/3 | 3/3 |
| second-block-escalates | Meets criterion: Stops and asks the user how to proceed, rather than only telling the author to fix tasks.md and re-run the review | 3/3 | 2/3 |
| second-block-escalates | Meets criterion: Does not proceed to sdd:spec-qa-review | 3/3 | 3/3 |
