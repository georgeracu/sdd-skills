# Eval results — iteration-1

**What this iteration tests:** First iteration of spec-finish against no skill

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.322 (Claude Code) |
| runner_model | claude-sonnet-5-5 |
| runner_model_canonical | claude-sonnet-5-5 |
| runner_model_requested | claude-sonnet-5-5 |
| judge_model | claude-opus-5-5 |
| judge_model_requested | claude-opus-5-5 |
| judge_tokens | 1053339 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits + runner settings |
| runner_settings | runner-settings.json |
| runner_settings_hash | ff308c1131d0d23f98bad03b79ab69d3944a13a14dfd3d033a9a82922afb2214 |
| network_isolated | false |
| seed | 3c0682ec37de0ed6 |
| runs_per_arm | 3 |
| parallel | 1 |
| pack_hash | 387fc7c5fd3fdd8d1a1654ca1c4f1aefa188e24b898ff015862bd6531b446823 |
| bundle_hash | ced41f1c0bcd330319eff3f78994a732f84a19c27b88d64f6e0874b69e12c1f1 |
| input_drift_allowed | none |
| cases_total | 6 |
| cases_included | 6 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7931 (69/87), B: 0.4368 (38/87) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=6) | 39.3534 (±13.5528, n=6) | 715,169 (±240,380, n=6) |
| without_skill | 0.45 (±0.2179, n=6) | 43.0387 (±9.3564, n=6) | 716,265 (±161,361, n=6) |

**Delta (with_skill − without_skill):** pass_rate +0.5500, time_seconds -3.69, tokens -1,096

*measured over 6 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0785 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `complete-feature` — Output includes: pr.md: 3/3 vs 0/3
- `complete-feature` — Meets criterion: pr.md has a title line that names spec 01 and word-count, not the template heading Requirements Document: 3/3 vs 0/3
- `complete-feature` — Meets criterion: outputs/work-item/pr.md exists and links to requirements.md, design.md and tasks.md with relative links: 3/3 vs 0/3
- `complete-feature` — Meets criterion: pr.md has a table of the gates with their status: design review APPROVED WITH ISSUES, task review APPROVED, implementation audit PASSED, test plan PASSED, and spec maintenance shown as skipped or not needed because no other work item is implemented: 3/3 vs 0/3
- `complete-feature` — Meets criterion: pr.md quotes test evidence from a run (6 tests, OK) and a commit SHA: 3/3 vs 0/3
- `complete-feature` — Meets criterion: pr.md has a section for reviewers that names the MAJOR design-review finding about files that are not UTF-8: 3/3 vs 0/3
- `complete-feature` — Meets criterion: outputs/git.txt shows a commit after the base commit that adds pr.md and nothing else, and an empty status after the log: 3/3 vs 0/3
- `complete-feature` — Meets criterion: The final reply says that no remote exists or that the pull request could not be opened, and says where pr.md is; it does not claim a pull request was opened: 3/3 vs 0/3
- `missing-audit` — Meets criterion: The final reply says the implementation audit is missing and names the implementation audit (sdd:spec-implementation-audit) as the step to run: 3/3 vs 0/3
- `missing-audit` — Meets criterion: outputs/git.txt shows only the base commit, so the run made no commit: 3/3 vs 0/3
- `missing-audit` — Meets criterion: The reply does not treat the test plan's placeholder Status line as a pass: 3/3 vs 0/3
- `audit-failed` — Meets criterion: The final reply says the implementation audit status is FAILED and that the pull request is not opened: 3/3 vs 2/3
- `audit-failed` — Meets criterion: outputs/git.txt shows only the base commit: 3/3 vs 0/3
- `uncommitted-gate-reports` — Output includes: pr.md: 3/3 vs 0/3
- `uncommitted-gate-reports` — Meets criterion: outputs/git.txt shows a commit after the base commit that holds test-plan.md and implementation-audit.md, and a later commit that adds pr.md: 3/3 vs 0/3
- `uncommitted-gate-reports` — Meets criterion: outputs/work-item/pr.md exists and shows the implementation audit and the test plan as PASSED: 3/3 vs 0/3
- `uncommitted-gate-reports` — Meets criterion: The final reply says where pr.md is and does not claim a pull request was opened: 3/3 vs 0/3
- `complete-bugfix` — Output includes: pr.md: 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: outputs/work-item/pr.md exists and links to bugfix.md, design.md and tasks.md: 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: pr.md does not ask for or list feature gates (design review, task review, implementation audit) as missing for this bugfix: 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: pr.md quotes test evidence from a run (6 tests, OK): 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: pr.md tells reviewers that task 3 in tasks.md is not ticked: 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: outputs/git.txt shows a commit after the base commit that adds pr.md and nothing else: 3/3 vs 0/3
- `complete-bugfix` — Meets criterion: The final reply says where pr.md is and does not claim a pull request was opened: 3/3 vs 0/3
- `stray-file` — Meets criterion: The final reply names README.md as an uncommitted or modified file and says the pull request is not opened because of it: 3/3 vs 0/3
- `stray-file` — Meets criterion: The final reply asks the user to commit, stash or remove README.md, and does not commit it: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| complete-feature | with_skill | 30 | 0 | 10/10, 10/10, 10/10 |
| complete-feature | without_skill | 6 | 24 | 2/10, 2/10, 2/10 |
| missing-audit | with_skill | 18 | 0 | 6/6, 6/6, 6/6 |
| missing-audit | without_skill | 9 | 9 | 3/6, 3/6, 3/6 |
| audit-failed | with_skill | 18 | 0 | 6/6, 6/6, 6/6 |
| audit-failed | without_skill | 14 | 4 | 5/6, 5/6, 4/6 |
| uncommitted-gate-reports | with_skill | 18 | 0 | 6/6, 6/6, 6/6 |
| uncommitted-gate-reports | without_skill | 6 | 12 | 2/6, 2/6, 2/6 |
| complete-bugfix | with_skill | 27 | 0 | 9/9, 9/9, 9/9 |
| complete-bugfix | without_skill | 6 | 21 | 2/9, 2/9, 2/9 |
| stray-file | with_skill | 18 | 0 | 6/6, 6/6, 6/6 |
| stray-file | without_skill | 12 | 6 | 4/6, 4/6, 4/6 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| complete-feature | Output includes: pr.md | 3/3 | 0/3 |
| complete-feature | Output does not include: spec 01: Requirements Document | 3/3 | 3/3 |
| complete-feature | Run produced file: git.txt | 3/3 | 3/3 |
| complete-feature | Meets criterion: pr.md has a title line that names spec 01 and word-count, not the template heading Requirements Document | 3/3 | 0/3 |
| complete-feature | Meets criterion: outputs/work-item/pr.md exists and links to requirements.md, design.md and tasks.md with relative links | 3/3 | 0/3 |
| complete-feature | Meets criterion: pr.md has a table of the gates with their status: design review APPROVED WITH ISSUES, task review APPROVED, implementation audit PASSED, test plan PASSED, and spec maintenance shown as skipped or not needed because no other work item is implemented | 3/3 | 0/3 |
| complete-feature | Meets criterion: pr.md quotes test evidence from a run (6 tests, OK) and a commit SHA | 3/3 | 0/3 |
| complete-feature | Meets criterion: pr.md has a section for reviewers that names the MAJOR design-review finding about files that are not UTF-8 | 3/3 | 0/3 |
| complete-feature | Meets criterion: outputs/git.txt shows a commit after the base commit that adds pr.md and nothing else, and an empty status after the log | 3/3 | 0/3 |
| complete-feature | Meets criterion: The final reply says that no remote exists or that the pull request could not be opened, and says where pr.md is; it does not claim a pull request was opened | 3/3 | 0/3 |
| missing-audit | Output does not include: pr.md | 3/3 | 3/3 |
| missing-audit | Run produced file: git.txt | 3/3 | 3/3 |
| missing-audit | Meets criterion: The final reply says the implementation audit is missing and names the implementation audit (sdd:spec-implementation-audit) as the step to run | 3/3 | 0/3 |
| missing-audit | Meets criterion: outputs/work-item has no pr.md | 3/3 | 3/3 |
| missing-audit | Meets criterion: outputs/git.txt shows only the base commit, so the run made no commit | 3/3 | 0/3 |
| missing-audit | Meets criterion: The reply does not treat the test plan's placeholder Status line as a pass | 3/3 | 0/3 |
| audit-failed | Output does not include: pr.md | 3/3 | 3/3 |
| audit-failed | Run produced file: git.txt | 3/3 | 3/3 |
| audit-failed | Meets criterion: The final reply says the implementation audit status is FAILED and that the pull request is not opened | 3/3 | 2/3 |
| audit-failed | Meets criterion: The final reply names at least one of the two MAJOR findings, or says the findings must be fixed and the audit run again | 3/3 | 3/3 |
| audit-failed | Meets criterion: outputs/work-item has no pr.md | 3/3 | 3/3 |
| audit-failed | Meets criterion: outputs/git.txt shows only the base commit | 3/3 | 0/3 |
| uncommitted-gate-reports | Output includes: pr.md | 3/3 | 0/3 |
| uncommitted-gate-reports | Run produced file: git.txt | 3/3 | 3/3 |
| uncommitted-gate-reports | Meets criterion: outputs/git.txt shows a commit after the base commit that holds test-plan.md and implementation-audit.md, and a later commit that adds pr.md | 3/3 | 0/3 |
| uncommitted-gate-reports | Meets criterion: outputs/git.txt ends with an empty status, so nothing is left uncommitted | 3/3 | 3/3 |
| uncommitted-gate-reports | Meets criterion: outputs/work-item/pr.md exists and shows the implementation audit and the test plan as PASSED | 3/3 | 0/3 |
| uncommitted-gate-reports | Meets criterion: The final reply says where pr.md is and does not claim a pull request was opened | 3/3 | 0/3 |
| complete-bugfix | Output includes: pr.md | 3/3 | 0/3 |
| complete-bugfix | Output includes: bugfix.md | 3/3 | 3/3 |
| complete-bugfix | Run produced file: git.txt | 3/3 | 3/3 |
| complete-bugfix | Meets criterion: outputs/work-item/pr.md exists and links to bugfix.md, design.md and tasks.md | 3/3 | 0/3 |
| complete-bugfix | Meets criterion: pr.md does not ask for or list feature gates (design review, task review, implementation audit) as missing for this bugfix | 3/3 | 0/3 |
| complete-bugfix | Meets criterion: pr.md quotes test evidence from a run (6 tests, OK) | 3/3 | 0/3 |
| complete-bugfix | Meets criterion: pr.md tells reviewers that task 3 in tasks.md is not ticked | 3/3 | 0/3 |
| complete-bugfix | Meets criterion: outputs/git.txt shows a commit after the base commit that adds pr.md and nothing else | 3/3 | 0/3 |
| complete-bugfix | Meets criterion: The final reply says where pr.md is and does not claim a pull request was opened | 3/3 | 0/3 |
| stray-file | Output does not include: pr.md | 3/3 | 3/3 |
| stray-file | Run produced file: git.txt | 3/3 | 3/3 |
| stray-file | Meets criterion: The final reply names README.md as an uncommitted or modified file and says the pull request is not opened because of it | 3/3 | 0/3 |
| stray-file | Meets criterion: outputs/git.txt shows only the base commit, and its status lists README.md | 3/3 | 3/3 |
| stray-file | Meets criterion: outputs/work-item has no pr.md | 3/3 | 3/3 |
| stray-file | Meets criterion: The final reply asks the user to commit, stash or remove README.md, and does not commit it | 3/3 | 0/3 |
