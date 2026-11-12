# Eval results — iteration-10

**What this iteration tests:** The hand-off after maintenance is now sdd:spec-finish instead of finishing-a-development-branch, with 3 runs to measure the spread

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
| judge_tokens | 971726 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 1ed87baab8472671 |
| runs_per_arm | 3 |
| parallel | 1 |
| pack_hash | dd1bbc8af161e04befab6d459735af5270091f11893037e59ea9e74bf7ce048d |
| bundle_hash | df42996c3f6c95acff1a61754810e5067abf766c7fabbb2869165010cba2d185 |
| snapshot_hash | 2f38fe10635d80afae615374eff25a6a4dd1d0bf98ec3f894bc97e1afa57b540 |
| input_drift_allowed | none |
| cases_total | 7 |
| cases_included | 7 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.9444 (102/108), B: 0.9074 (98/108) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9945 (±0.0087, n=7) | 31.3194 (±11.4302, n=7) | 311,541 (±76,336, n=7) |
| old_skill | 0.9104 (±0.082, n=7) | 34.5229 (±17.3822, n=7) | 333,666 (±128,453, n=7) |

**Delta (with_skill − old_skill):** pass_rate +0.0841, time_seconds -3.20, tokens -22,125

*measured over 7 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0496 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `skip-no-implemented-peers` — Output includes: sdd:spec-finish: 3/3 vs 0/3
- `skip-no-implemented-peers` — Meets criterion: Hands over to sdd:spec-finish as the next step (naming it as the next step counts, including when the run says the skill is not available in its session): 3/3 vs 0/3
- `superseded-task-only` — Meets criterion: The report has no Architectural Drift Warning section, and the run ends by pointing to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session): 3/3 vs 0/3
- `drift-warning-still-passes` — Output includes: sdd:spec-finish: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04: 3/3 vs 2/3
- `drift-warning-still-passes` — Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session): 3/3 vs 0/3
- `twelve-lines-six-items-no-warning` — Output includes: sdd:spec-finish: 3/3 vs 0/3
- `twelve-lines-six-items-no-warning` — Meets criterion: Annotates all twelve usage-meters sub-item lines in 01-quota-alerts and 02-usage-export with the rename and a reference to work-item 04: 3/3 vs 2/3
- `twelve-lines-six-items-no-warning` — Meets criterion: The reply hands over to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session): 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| skip-no-implemented-peers | with_skill | 27 | 0 | 9/9, 9/9, 9/9 |
| skip-no-implemented-peers | old_skill | 21 | 6 | 7/9, 7/9, 7/9 |
| superseded-task-only | with_skill | 39 | 0 | 13/13, 13/13, 13/13 |
| superseded-task-only | old_skill | 36 | 3 | 12/13, 12/13, 12/13 |
| modified-and-deprecated-shapes | with_skill | 45 | 0 | 15/15, 15/15, 15/15 |
| modified-and-deprecated-shapes | old_skill | 45 | 0 | 15/15, 15/15, 15/15 |
| drift-warning-still-passes | with_skill | 56 | 1 | 19/19, 19/19, 18/19 |
| drift-warning-still-passes | old_skill | 49 | 8 | 15/19, 17/19, 17/19 |
| annotation-comma-form | with_skill | 48 | 0 | 16/16, 16/16, 16/16 |
| annotation-comma-form | old_skill | 48 | 0 | 16/16, 16/16, 16/16 |
| partial-replacement-modified | with_skill | 47 | 1 | 16/16, 15/16, 16/16 |
| partial-replacement-modified | old_skill | 47 | 1 | 16/16, 16/16, 15/16 |
| twelve-lines-six-items-no-warning | with_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| twelve-lines-six-items-no-warning | old_skill | 35 | 7 | 12/14, 12/14, 11/14 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| skip-no-implemented-peers | Output includes: sdd:spec-finish | 3/3 | 0/3 |
| skip-no-implemented-peers | Output does not include: ## Impacted Work Items | 3/3 | 3/3 |
| skip-no-implemented-peers | Output does not include: *(superseded by work-item 04)* | 3/3 | 3/3 |
| skip-no-implemented-peers | Output does not include: *(deprecated | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: States that no other implemented work item exists, because neither 01-quota-alerts nor 02-usage-export holds an implementation-audit.md | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Skips the skill instead of scanning the older work items for impacts | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Does not write a spec-maintenance-report.md, not even one that says nothing was impacted | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Does not edit or annotate any file in the older work items | 3/3 | 3/3 |
| skip-no-implemented-peers | Meets criterion: Hands over to sdd:spec-finish as the next step (naming it as the next step counts, including when the run says the skill is not available in its session) | 3/3 | 0/3 |
| superseded-task-only | Output includes: ~~ *(superseded by work-item 04 | 3/3 | 3/3 |
| superseded-task-only | Output includes: 1 superseded | 3/3 | 3/3 |
| superseded-task-only | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| superseded-task-only | Output does not include: # ~~ | 3/3 | 3/3 |
| superseded-task-only | Output does not include: ~~Task 3: | 3/3 | 3/3 |
| superseded-task-only | Output does not include: *(deprecated | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Edits Task 4 of 02-usage-export/tasks.md so the whole task line is struck through and followed by an annotation naming work-item 04 | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Strikes through only that task line: no heading, other task, requirement or design bullet is struck through, and surrounding text is not reformatted | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: The report lists 02-usage-export with one superseded impact in tasks.md and 03-profile-summary under No Impact | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: The report has no Architectural Drift Warning section, and the run ends by pointing to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session) | 3/3 | 0/3 |
| modified-and-deprecated-shapes | Output includes: *(deprecated | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: one hour~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(modified in work-item 04 | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: ~~Notification_Service sends | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: # ~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: *(superseded by | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Edits the Quota_Cache TTL bullet in 02-usage-export/design.md so only the old value is struck through, the new value (five minutes) is given, and the annotation names work-item 04 | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Annotates the export-ready bullet as deprecated with a reference to work-item 04 and does not strike through any part of that line | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Uses two different annotation shapes for the two impacts, not one shape for both | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Leaves 01-quota-alerts untouched because it has no implementation-audit.md, and lists 03-profile-summary under No Impact | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: The report classifies the TTL change as MODIFIED and the export-ready bullet as DEPRECATED, with 02-usage-export design.md as the file changed | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged | 3/3 | 3/3 |
| drift-warning-still-passes | Output includes: ## Architectural Drift Warning | 3/3 | 3/3 |
| drift-warning-still-passes | Output includes: *(modified in work-item 04 | 3/3 | 3/3 |
| drift-warning-still-passes | Output includes: sdd:spec-finish | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| drift-warning-still-passes | Output does not include: # ~~ | 3/3 | 3/3 |
| drift-warning-still-passes | Output does not include: *(superseded by | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/04-usage-counters/spec-maintenance-report.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/01-quota-alerts/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/requirements.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/design.md | 3/3 | 3/3 |
| drift-warning-still-passes | Run produced file: project/work-items/03-usage-history/tasks.md | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04 | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files | 2/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: The report's impact table lists 01-quota-alerts, 02-usage-export and 03-usage-history with counts that total more than ten | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Fills the report's Architectural Drift Warning section and repeats the warning in the closing reply, because more than ten impacts land across several specs | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session) | 3/3 | 0/3 |
| annotation-comma-form | Output includes: *(modified in work-item 04 | 3/3 | 3/3 |
| annotation-comma-form | Output includes: *(deprecated, see work-item 04 | 3/3 | 3/3 |
| annotation-comma-form | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: work-item 04 — | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: *(deprecated — | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest)* | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest, | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest — | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: *(changed | 3/3 | 3/3 |
| annotation-comma-form | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| annotation-comma-form | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: The TTL annotation starts *(modified in work-item 04 and any reason follows a comma | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: The export-ready annotation starts *(deprecated, see work-item 04 and any reason follows a comma | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: No annotation contains an em-dash or a spaced hyphen as a separator | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: No annotation names the work item as 04-usage-digest; it names it as work-item 04 | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: No annotation uses a verb other than superseded by, modified in or deprecated, see (for example changed in, renamed in or lowered to) | 3/3 | 3/3 |
| partial-replacement-modified | Output includes: *(modified in work-item 04 | 3/3 | 3/3 |
| partial-replacement-modified | Output includes: usage-archive | 3/3 | 3/3 |
| partial-replacement-modified | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: ~~Task 1: | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: ~~Task 3: | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: ~~Writes one CSV | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: *(superseded by | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: SUPERSEDED | 2/3 | 2/3 |
| partial-replacement-modified | Run produced file: project/work-items/04-export-archive/spec-maintenance-report.md | 3/3 | 3/3 |
| partial-replacement-modified | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| partial-replacement-modified | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Classifies Task 1 of 02-usage-export as Modified, because its loader and writer survive, and strikes through only the `exports` bucket name in it | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Does not strike through the whole of Task 1, Task 3 or any design bullet | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Annotates Task 3 and the design bullet that writes to the `exports` bucket as Modified, each with the `usage-archive` bucket given | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Classifies nothing as Superseded | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: The report lists the impacts in 02-usage-export as MODIFIED and lists 03-profile-summary under No Impact | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output includes: *(modified in work-item 04 | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output includes: sdd:spec-finish | 3/3 | 0/3 |
| twelve-lines-six-items-no-warning | Output includes: **Status:** PASSED | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: Architectural Drift Warning | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: # ~~ | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Output does not include: *(superseded by | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/04-usage-counters/spec-maintenance-report.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/01-quota-alerts/tasks.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Meets criterion: Annotates all twelve usage-meters sub-item lines in 01-quota-alerts and 02-usage-export with the rename and a reference to work-item 04 | 3/3 | 2/3 |
| twelve-lines-six-items-no-warning | Meets criterion: Strikes through only the table name, not whole lines, tasks, headings or files | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The report lists one Impact Details bullet per task, six in all, rather than one per annotated line | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The report has no Architectural Drift Warning section and the reply gives no drift warning, because six impacts is not over ten | 3/3 | 3/3 |
| twelve-lines-six-items-no-warning | Meets criterion: The reply hands over to sdd:spec-finish (naming it as the next step counts, including when the run says the skill is not available in its session) | 3/3 | 0/3 |
