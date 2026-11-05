# Eval results — iteration-6

**What this iteration tests:** S5: drift count one per Impact Details bullet; over 10 across two or more items fills the section and repeats it in the reply (case 4, new case 7)

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
| judge_tokens | 568012 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 78412b57b916f3ed |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 1b22c0036dd8696fd9baa7aa05f517ddffe959f67727e704a2c868819f6d0f7e |
| bundle_hash | 77b2e4f32c72f7bd977732defb28cf7092880606c0fbc6966f1b8e4b9eafd266 |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6333 (19/30), B: 0.7667 (23/30) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9281 (±0.0207, n=2) | 139.1045 (±29.0912, n=2) | 749,927 (±77,204, n=2) |
| old_skill | 0.7279 (±0.0869, n=2) | 123.5932 (±3.8698, n=2) | 681,946 (±12,971, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.2002, time_seconds +15.51, tokens +67,980

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1047 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `drift-warning-still-passes` — Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch: 0/3 vs 3/3
- `twelve-lines-six-items-no-warning` — Meets criterion: The reply hands over to finishing-a-development-branch: 1/3 vs 3/3

with_skill scored above baseline:

- `drift-warning-still-passes` — Output includes: ## Architectural Drift Warning: 3/3 vs 0/3
- `drift-warning-still-passes` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: The report's impact table lists 01-quota-alerts, 02-usage-export and 03-usage-history with counts that total more than ten: 3/3 vs 2/3
- `drift-warning-still-passes` — Meets criterion: Fills the report's Architectural Drift Warning section and repeats the warning in the closing reply, because more than ten impacts land across several specs: 3/3 vs 2/3
- `twelve-lines-six-items-no-warning` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `twelve-lines-six-items-no-warning` — Output does not include: *(superseded by: 3/3 vs 1/3
- `twelve-lines-six-items-no-warning` — Meets criterion: Annotates all twelve usage-meters sub-item lines in 01-quota-alerts and 02-usage-export with the rename and a reference to work-item 04: 3/3 vs 2/3
- `twelve-lines-six-items-no-warning` — Meets criterion: Strikes through only the table name, not whole lines, tasks, headings or files: 3/3 vs 1/3
- `twelve-lines-six-items-no-warning` — Meets criterion: The report lists one Impact Details bullet per task, six in all, rather than one per annotated line: 3/3 vs 0/3
- `twelve-lines-six-items-no-warning` — Meets criterion: The report has no Architectural Drift Warning section and the reply gives no drift warning, because six impacts is not over ten: 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| drift-warning-still-passes | with_skill | 49 | 5 | 17/18, 17/18, 15/18 |
| drift-warning-still-passes | old_skill | 44 | 10 | 16/18, 16/18, 12/18 |
| twelve-lines-six-items-no-warning | with_skill | 37 | 2 | 12/13, 13/13, 12/13 |
| twelve-lines-six-items-no-warning | old_skill | 25 | 14 | 7/13, 8/13, 10/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| drift-warning-still-passes | Output includes: ## Architectural Drift Warning | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: finishing-a-development-branch | 3/3 | 3/3 |
| drift-warning-still-passes | Output does not include: # ~~ | 3/3 | 3/3 |
| drift-warning-still-passes | Output does not include: *(superseded by | 2/3 | 2/3 |
| drift-warning-still-passes | Run produced file: project/work-items/04-usage-counters/spec-maintenance-report.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/requirements.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04 | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files | 2/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: The report's impact table lists 01-quota-alerts, 02-usage-export and 03-usage-history with counts that total more than ten | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Fills the report's Architectural Drift Warning section and repeats the warning in the closing reply, because more than ten impacts land across several specs | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch | 0/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| twelve-lines-six-items-no-warning | Output includes: finishing-a-development-branch | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: Architectural Drift Warning | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: # ~~ | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: *(superseded by | 3/3 | 1/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/04-usage-counters/spec-maintenance-report.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/01-quota-alerts/tasks.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Meets criterion: Annotates all twelve usage-meters sub-item lines in 01-quota-alerts and 02-usage-export with the rename and a reference to work-item 04 | 3/3 | 2/3 |
| twelve-lines-six-items-no-warning | Meets criterion: Strikes through only the table name, not whole lines, tasks, headings or files | 3/3 | 1/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The report lists one Impact Details bullet per task, six in all, rather than one per annotated line | 3/3 | 0/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The report has no Architectural Drift Warning section and the reply gives no drift warning, because six impacts is not over ten | 3/3 | 0/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The reply hands over to finishing-a-development-branch | 1/3 | 3/3 |
