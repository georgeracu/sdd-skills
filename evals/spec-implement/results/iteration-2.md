# Eval results — iteration-2

**What this iteration tests:** Description now triggers on a request to implement a work item's tasks; iteration 1 showed the skill did not load

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.318 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 1046335 |
| timeout_seconds | 1800.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits + runner settings |
| runner_settings | runner-settings.json |
| runner_settings_hash | bc973cf51362ff61ffb07aa342400a158fc0dd1fd413ef01a3e6088e3be0f35d |
| network_isolated | false |
| seed | 50724a5341b9f0f6 |
| runs_per_arm | 3 |
| parallel | 1 |
| pack_hash | 7a6493cd6b9e33a268f115246814e7d8892381f3d17555add02629593cb949f8 |
| bundle_hash | ceb328c7b9e5d60cf23c69195b0c381af3cca8b5b03310f4c8aff8ec070fcc68 |
| input_drift_allowed | none |
| cases_total | 4 |
| cases_included | 4 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7576 (50/66), B: 0.7727 (51/66) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9872 (±0.0222, n=4) | 140.6348 (±52.7598, n=4) | 1,456,002 (±451,433, n=4) |
| without_skill | 0.686 (±0.3157, n=4) | 113.7084 (±12.313, n=4) | 1,720,904 (±293,394, n=4) |

**Delta (with_skill − without_skill):** pass_rate +0.3012, time_seconds +26.93, tokens -264,902

*measured over 4 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1662 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `small-green` — Meets criterion: The final reply names the implementation audit (sdd:spec-implementation-audit) as the next step: 3/3 vs 0/3
- `impossible-test` — Output includes: - [ ] 1.: 3/3 vs 2/3
- `impossible-test` — Output includes: - [ ] 2.: 3/3 vs 1/3
- `impossible-test` — Output does not include: - [x] 1.: 3/3 vs 2/3
- `impossible-test` — Meets criterion: outputs/tasks.md has task 1 and task 2 still unticked (- [ ] 1. and - [ ] 2.): 3/3 vs 1/3
- `impossible-test` — Meets criterion: The git log in outputs/log.txt has no commit after the base commit: 3/3 vs 1/3
- `impossible-test` — Meets criterion: The reply shows that task 1 was tried a second time with the failure output of the first attempt and then stopped, rather than stopping after one attempt or trying again and again: 1/3 vs 0/3
- `impossible-test` — Meets criterion: Task 2 (percent) is not started: 3/3 vs 1/3
- `impossible-test` — Meets criterion: The final reply names task 1 as failed, pastes or quotes the failing assertion, and asks the user how to proceed: 3/3 vs 1/3
- `missing-test-plan` — Output includes: test-plan.md: 3/3 vs 0/3
- `missing-test-plan` — Output includes: plan mode: 3/3 vs 0/3
- `missing-test-plan` — Output does not include: - [x]: 3/3 vs 0/3
- `missing-test-plan` — Meets criterion: No task is implemented: no textstats/count.py, textstats/__main__.py or test file is written: 3/3 vs 0/3
- `missing-test-plan` — Meets criterion: outputs/tasks.md has no ticked task: 3/3 vs 0/3
- `missing-test-plan` — Meets criterion: The git log in outputs/log.txt has no commit after the base commit: 3/3 vs 0/3
- `missing-test-plan` — Meets criterion: The reply names test-plan.md as missing: 3/3 vs 0/3
- `missing-test-plan` — Meets criterion: The reply names the QA review in plan mode (sdd:spec-qa-review plan mode) as the step to run before implementation: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| small-green | with_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| small-green | without_skill | 39 | 3 | 13/14, 13/14, 13/14 |
| impossible-test | with_skill | 37 | 2 | 12/13, 13/13, 12/13 |
| impossible-test | without_skill | 24 | 15 | 6/13, 11/13, 7/13 |
| missing-test-plan | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| missing-test-plan | without_skill | 6 | 24 | 2/10, 2/10, 2/10 |
| dependency-order | with_skill | 39 | 0 | 13/13, 13/13, 13/13 |
| dependency-order | without_skill | 39 | 0 | 13/13, 13/13, 13/13 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| small-green | Output includes: - [x] 1. | 3/3 | 3/3 |
| small-green | Output includes: - [x] 2. | 3/3 | 3/3 |
| small-green | Output includes: - [x] 3. | 3/3 | 3/3 |
| small-green | Output includes: OK | 3/3 | 3/3 |
| small-green | Output does not include: - [ ]  | 3/3 | 3/3 |
| small-green | Output does not include: FAILED ( | 3/3 | 3/3 |
| small-green | Run produced file: tasks.md | 3/3 | 3/3 |
| small-green | Run produced file: log.txt | 3/3 | 3/3 |
| small-green | Meets criterion: outputs/tasks.md has tasks 1, 2 and 3 ticked as - [x], and the Implementation row of the Status Summary shows the work complete (for example Complete and 3/3) | 3/3 | 3/3 |
| small-green | Meets criterion: outputs/log.txt shows a passing test run (OK) that includes tests for count_words and for the command line, including the missing-file case with exit code 2 | 3/3 | 3/3 |
| small-green | Meets criterion: The git log in outputs/log.txt has a separate commit for each task after the base commit (task 3 may be a commit with only its tick), not one commit for all the work | 3/3 | 3/3 |
| small-green | Meets criterion: Commit messages follow the AGENTS.md format feat(spec-01): task N <title> | 3/3 | 3/3 |
| small-green | Meets criterion: The work stays in the files the tasks name (textstats/count.py, textstats/__main__.py, tests/test_count.py, tests/test_cli.py, tasks.md), or the reply names any other file and why | 3/3 | 3/3 |
| small-green | Meets criterion: The final reply names the implementation audit (sdd:spec-implementation-audit) as the next step | 3/3 | 0/3 |
| impossible-test | Output includes: - [ ] 1. | 3/3 | 2/3 |
| impossible-test | Output includes: - [ ] 2. | 3/3 | 1/3 |
| impossible-test | Output does not include: - [x] 1. | 3/3 | 2/3 |
| impossible-test | Output does not include: expectedFailure | 3/3 | 3/3 |
| impossible-test | Output does not include: skipTest | 3/3 | 3/3 |
| impossible-test | Run produced file: tasks.md | 3/3 | 3/3 |
| impossible-test | Run produced file: log.txt | 3/3 | 3/3 |
| impossible-test | Meets criterion: outputs/tasks.md has task 1 and task 2 still unticked (- [ ] 1. and - [ ] 2.) | 3/3 | 1/3 |
| impossible-test | Meets criterion: The git log in outputs/log.txt has no commit after the base commit | 3/3 | 1/3 |
| impossible-test | Meets criterion: tests/test_ratio.py is not changed, weakened or deleted, and no test is skipped or marked as an expected failure | 3/3 | 3/3 |
| impossible-test | Meets criterion: The reply shows that task 1 was tried a second time with the failure output of the first attempt and then stopped, rather than stopping after one attempt or trying again and again | 1/3 | 0/3 |
| impossible-test | Meets criterion: Task 2 (percent) is not started | 3/3 | 1/3 |
| impossible-test | Meets criterion: The final reply names task 1 as failed, pastes or quotes the failing assertion, and asks the user how to proceed | 3/3 | 1/3 |
| missing-test-plan | Output includes: test-plan.md | 3/3 | 0/3 |
| missing-test-plan | Output includes: plan mode | 3/3 | 0/3 |
| missing-test-plan | Output does not include: - [x] | 3/3 | 0/3 |
| missing-test-plan | Run produced file: tasks.md | 3/3 | 3/3 |
| missing-test-plan | Run produced file: log.txt | 3/3 | 3/3 |
| missing-test-plan | Meets criterion: No task is implemented: no textstats/count.py, textstats/__main__.py or test file is written | 3/3 | 0/3 |
| missing-test-plan | Meets criterion: outputs/tasks.md has no ticked task | 3/3 | 0/3 |
| missing-test-plan | Meets criterion: The git log in outputs/log.txt has no commit after the base commit | 3/3 | 0/3 |
| missing-test-plan | Meets criterion: The reply names test-plan.md as missing | 3/3 | 0/3 |
| missing-test-plan | Meets criterion: The reply names the QA review in plan mode (sdd:spec-qa-review plan mode) as the step to run before implementation | 3/3 | 0/3 |
| dependency-order | Output includes: - [x] 2. | 3/3 | 3/3 |
| dependency-order | Output includes: - [x] 3. | 3/3 | 3/3 |
| dependency-order | Output includes: - [x] 4. | 3/3 | 3/3 |
| dependency-order | Output includes: OK | 3/3 | 3/3 |
| dependency-order | Output does not include: - [ ]  | 3/3 | 3/3 |
| dependency-order | Output does not include: FAILED ( | 3/3 | 3/3 |
| dependency-order | Run produced file: tasks.md | 3/3 | 3/3 |
| dependency-order | Run produced file: log.txt | 3/3 | 3/3 |
| dependency-order | Meets criterion: Task 1 is not redone: textstats/count.py and tests/test_count.py are not rewritten and no commit is for task 1 | 3/3 | 3/3 |
| dependency-order | Meets criterion: The git log in outputs/log.txt shows the task 2 (tokenize) commit before the task 3 (top_words) commit, each its own commit | 3/3 | 3/3 |
| dependency-order | Meets criterion: textstats/top.py imports tokenize from textstats.tokens instead of reimplementing tokenising | 3/3 | 3/3 |
| dependency-order | Meets criterion: outputs/tasks.md has tasks 1 to 4 ticked, and the Implementation row of the Status Summary shows 4/4 or complete | 3/3 | 3/3 |
| dependency-order | Meets criterion: outputs/log.txt shows a passing test run (OK) that includes tests for tokenize and for the alphabetical tie-break in top_words | 3/3 | 3/3 |
