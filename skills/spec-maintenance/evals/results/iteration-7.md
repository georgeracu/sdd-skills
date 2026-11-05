# Eval results — iteration-7

**What this iteration tests:** S6: Result always PASSED with a Status line, positive granularity rule; cuts and description (cases 3, 4)

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
| judge_tokens | 469932 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 0443979604a0c7e0 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | bcdfe6f01adfd530ad1a785e01be97e42c74f70619e9209ede99d0e505ce9eaa |
| bundle_hash | 6b63852c0b88ff8fa6c82980ee17fc13b40be98862bd9868c28beab0488245fd |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8788 (29/33), B: 0.8485 (28/33) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9474 (±0.0526, n=2) | 83.1277 (±35.4777, n=2) | 644,193 (±131,302, n=2) |
| old_skill | 0.7708 (±0.0514, n=2) | 107.3375 (±34.6342, n=2) | 813,948 (±258,802, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.1766, time_seconds -24.21, tokens -169,756

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0629 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `drift-warning-still-passes` — Output includes: finishing-a-development-branch: 0/3 vs 3/3
- `drift-warning-still-passes` — Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch (naming it as the next step counts, including when the run says the skill is not available in its session): 0/3 vs 3/3

with_skill scored above baseline:

- `modified-and-deprecated-shapes` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `modified-and-deprecated-shapes` — Output includes: **Status:** PASSED: 3/3 vs 0/3
- `modified-and-deprecated-shapes` — Meets criterion: Edits the Quota_Cache TTL bullet in 02-usage-export/design.md so only the old value is struck through, the new value (five minutes) is given, and the annotation names work-item 04: 3/3 vs 2/3
- `modified-and-deprecated-shapes` — Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged: 3/3 vs 2/3
- `drift-warning-still-passes` — Output includes: ## Architectural Drift Warning: 3/3 vs 0/3
- `drift-warning-still-passes` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `drift-warning-still-passes` — Output includes: **Status:** PASSED: 3/3 vs 0/3
- `drift-warning-still-passes` — Output does not include: *(superseded by: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: Fills the report's Architectural Drift Warning section and repeats the warning in the closing reply, because more than ten impacts land across several specs: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| modified-and-deprecated-shapes | with_skill | 45 | 0 | 15/15, 15/15, 15/15 |
| modified-and-deprecated-shapes | old_skill | 37 | 8 | 11/15, 13/15, 13/15 |
| drift-warning-still-passes | with_skill | 51 | 6 | 17/19, 17/19, 17/19 |
| drift-warning-still-passes | old_skill | 41 | 16 | 14/19, 14/19, 13/19 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| modified-and-deprecated-shapes | Output includes: *(deprecated | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: one hour~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| modified-and-deprecated-shapes | Output includes: **Status:** PASSED | 3/3 | 0/3 |
| modified-and-deprecated-shapes | Output does not include: ~~Notification_Service sends | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: # ~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: *(superseded by | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Edits the Quota_Cache TTL bullet in 02-usage-export/design.md so only the old value is struck through, the new value (five minutes) is given, and the annotation names work-item 04 | 3/3 | 2/3 |
| modified-and-deprecated-shapes | Meets criterion: Annotates the export-ready bullet as deprecated with a reference to work-item 04 and does not strike through any part of that line | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Uses two different annotation shapes for the two impacts, not one shape for both | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Leaves 01-quota-alerts untouched because it has no implementation-audit.md, and lists 03-profile-summary under No Impact | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: The report classifies the TTL change as MODIFIED and the export-ready bullet as DEPRECATED, with 02-usage-export design.md as the file changed | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged | 3/3 | 2/3 |
| drift-warning-still-passes | Output includes: ## Architectural Drift Warning | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: finishing-a-development-branch | 0/3 | 3/3 |
| drift-warning-still-passes | Output includes: **Status:** PASSED | 3/3 | 0/3 |
| drift-warning-still-passes | Output does not include: # ~~ | 3/3 | 3/3 |
| drift-warning-still-passes | Output does not include: *(superseded by | 3/3 | 0/3 |
| drift-warning-still-passes | Run produced file: project/work-items/04-usage-counters/spec-maintenance-report.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/requirements.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04 | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files | 3/3 | 0/3 |
| drift-warning-still-passes | Meets criterion: The report's impact table lists 01-quota-alerts, 02-usage-export and 03-usage-history with counts that total more than ten | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Fills the report's Architectural Drift Warning section and repeats the warning in the closing reply, because more than ten impacts land across several specs | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch (naming it as the next step counts, including when the run says the skill is not available in its session) | 0/3 | 3/3 |
