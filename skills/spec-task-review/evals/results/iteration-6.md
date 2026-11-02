# Eval results — iteration-6

**What this iteration tests:** Full-pack gaps: test-only trace does not cover, combined ending task is MINOR, one SEVERITY-NNN heading per finding: cases 2, 5, 6 against the iteration-5 skill

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
| judge_tokens | 739486 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 9e9c28fa86f8d367 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 53a370a372bbd3bc8acf42165a24682d3696b83719eea6c0bd22347431977c1b |
| bundle_hash | 325ba258a117370cbfa09cfc4fb7148e508079a0aa11dfde0845a6b35d4a69e4 |
| snapshot_hash | 83918d6da7c90983940166b42e2f1b329ac0f2d45393a738d51a30312ea36ef9 |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8235 (42/51), B: 0.8235 (42/51) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9293 (±0.1, n=3) | 114.895 (±10.4811, n=3) | 351,853 (±24,772, n=3) |
| old_skill | 0.7932 (±0.0898, n=3) | 115.6633 (±22.2181, n=3) | 283,658 (±23,630, n=3) |

**Delta (with_skill − old_skill):** pass_rate +0.1361, time_seconds -0.77, tokens +68,195

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2176 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `combined-tests-minor` — Output does not include: ### MAJOR-: 2/3 vs 3/3

with_skill scored above baseline:

- `requirement-without-task-blocked` — Output includes: **Status:** BLOCKED: 3/3 vs 2/3
- `requirement-without-task-blocked` — Output does not include: **Status:** APPROVED: 3/3 vs 2/3
- `requirement-without-task-blocked` — Meets criterion: Writes task-review-summary.md with Status BLOCKED: 3/3 vs 2/3
- `requirement-without-task-blocked` — Meets criterion: Raises a CRITICAL finding that names acceptance criterion 2.4 (no alerts for a suspended Account) as having no task: 3/3 vs 2/3
- `requirement-without-task-blocked` — Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered: 3/3 vs 2/3
- `requirement-without-task-blocked` — Meets criterion: States that tasks.md must be fixed and the review re-run before implementation, and does not approve: 3/3 vs 2/3
- `oversized-task` — Output includes: **Status:** APPROVED WITH ISSUES: 3/3 vs 2/3
- `oversized-task` — Output includes: ### MAJOR-: 3/3 vs 0/3
- `combined-tests-minor` — Output does not include: **Status:** APPROVED WITH ISSUES: 2/3 vs 1/3
- `combined-tests-minor` — Meets criterion: Writes task-review-summary.md with Status APPROVED: 2/3 vs 1/3
- `combined-tests-minor` — Meets criterion: Reports zero CRITICAL and zero MAJOR issues: 2/3 vs 1/3
- `combined-tests-minor` — Meets criterion: Treats the combined test task as at most a MINOR suggestion to split it: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirement-without-task-blocked | with_skill | 39 | 0 | 13/13, 13/13, 13/13 |
| requirement-without-task-blocked | old_skill | 33 | 6 | 13/13, 13/13, 7/13 |
| oversized-task | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| oversized-task | old_skill | 26 | 4 | 8/10, 9/10, 9/10 |
| combined-tests-minor | with_skill | 26 | 7 | 10/11, 10/11, 6/11 |
| combined-tests-minor | old_skill | 22 | 11 | 10/11, 6/11, 6/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| requirement-without-task-blocked | Output includes: **Status:** BLOCKED | 3/3 | 2/3 |
| requirement-without-task-blocked | Output includes: 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: CRITICAL | 3/3 | 3/3 |
| requirement-without-task-blocked | Output includes: ## Design Coverage | 3/3 | 3/3 |
| requirement-without-task-blocked | Output does not include: **Status:** APPROVED | 3/3 | 2/3 |
| requirement-without-task-blocked | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Writes task-review-summary.md with Status BLOCKED | 3/3 | 2/3 |
| requirement-without-task-blocked | Meets criterion: Raises a CRITICAL finding that names acceptance criterion 2.4 (no alerts for a suspended Account) as having no task | 3/3 | 2/3 |
| requirement-without-task-blocked | Meets criterion: The coverage matrix marks AC 2.4 as uncovered and the other criteria as covered | 3/3 | 2/3 |
| requirement-without-task-blocked | Meets criterion: Gives a specific fix to tasks.md, such as adding the suspended check and a test to the AlertEvaluator task and tracing 2.4 | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: States that tasks.md must be fixed and the review re-run before implementation, and does not approve | 3/3 | 2/3 |
| requirement-without-task-blocked | Meets criterion: Does not invent further CRITICAL or MAJOR findings about sequencing or the ending tasks | 3/3 | 3/3 |
| requirement-without-task-blocked | Meets criterion: Keeps discovery.md, as the review is blocked | 3/3 | 3/3 |
| oversized-task | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 2/3 |
| oversized-task | Output includes: ### MAJOR- | 3/3 | 0/3 |
| oversized-task | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| oversized-task | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| oversized-task | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| oversized-task | Meets criterion: Writes task-review-summary.md with Status APPROVED WITH ISSUES | 3/3 | 3/3 |
| oversized-task | Meets criterion: Raises a MAJOR scope finding on Task 3 | 3/3 | 3/3 |
| oversized-task | Meets criterion: Names the components Task 3 spans, including the frontend ThresholdSettings component and at least one backend component | 3/3 | 3/3 |
| oversized-task | Meets criterion: Proposes splitting Task 3 into separate tasks by component or layer | 3/3 | 3/3 |
| oversized-task | Meets criterion: Reports no CRITICAL issues | 3/3 | 3/3 |
| combined-tests-minor | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: **Status:** APPROVED WITH ISSUES | 2/3 | 1/3 |
| combined-tests-minor | Output does not include: **Status:** BLOCKED | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: ### CRITICAL- | 3/3 | 3/3 |
| combined-tests-minor | Output does not include: ### MAJOR- | 2/3 | 3/3 |
| combined-tests-minor | Run produced file: task-review-summary.md | 3/3 | 3/3 |
| combined-tests-minor | Meets criterion: Writes task-review-summary.md with Status APPROVED | 2/3 | 1/3 |
| combined-tests-minor | Meets criterion: Reports zero CRITICAL and zero MAJOR issues | 2/3 | 1/3 |
| combined-tests-minor | Meets criterion: Marks the integration test and the end-to-end test ending tasks as present | 3/3 | 3/3 |
| combined-tests-minor | Meets criterion: Treats the combined test task as at most a MINOR suggestion to split it | 3/3 | 1/3 |
| combined-tests-minor | Meets criterion: Names sdd:spec-qa-review in plan mode as the next step | 0/3 | 0/3 |
