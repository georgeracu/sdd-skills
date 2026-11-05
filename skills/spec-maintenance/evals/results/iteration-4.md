# Eval results — iteration-4

**What this iteration tests:** S3: annotation shape *(<verb> work-item NN[, reason])*, comma only; S2 search scope to peer set (cases 2, 3, 4, new comma case 5)

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
| judge_tokens | 906654 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 7e7d747fbe8b821f |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 01af7d9570404c203449586ab638065c456de8d0c6cc1539e4301915a4da1b68 |
| bundle_hash | cdb24be828037e1c93bf9fe338e64f68ccfaf7ae09952581dba870a902c37ec3 |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 4 |
| cases_included | 4 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6508 (41/63), B: 0.9524 (60/63) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9768 (±0.0241, n=4) | 73.6027 (±33.7836, n=4) | 643,975 (±135,461, n=4) |
| old_skill | 0.7312 (±0.2554, n=4) | 76.0153 (±24.4648, n=4) | 601,339 (±58,162, n=4) |

**Delta (with_skill − old_skill):** pass_rate +0.2456, time_seconds -2.41, tokens +42,636

*measured over 4 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0628 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `superseded-task-only` — Meets criterion: The report does not include an architectural drift warning, and the run ends by pointing to finishing-a-development-branch: 2/3 vs 3/3
- `drift-warning-still-passes` — Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch: 2/3 vs 3/3

with_skill scored above baseline:

- `superseded-task-only` — Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule: 2/3 vs 1/3
- `modified-and-deprecated-shapes` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `drift-warning-still-passes` — Output includes: drift: 2/3 vs 0/3
- `drift-warning-still-passes` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `drift-warning-still-passes` — Output does not include: *(superseded by: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04: 3/3 vs 2/3
- `drift-warning-still-passes` — Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files: 3/3 vs 0/3
- `drift-warning-still-passes` — Meets criterion: Includes an architectural drift warning (or tells the user in the reply) because more than ten impacts land across several specs: 3/3 vs 2/3
- `annotation-comma-form` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `annotation-comma-form` — Output includes: *(deprecated, see work-item 04: 3/3 vs 0/3
- `annotation-comma-form` — Output does not include: *(deprecated —: 3/3 vs 0/3
- `annotation-comma-form` — Output does not include: work-item 04-usage-digest)*: 3/3 vs 0/3
- `annotation-comma-form` — Output does not include: work-item 04-usage-digest,: 3/3 vs 1/3
- `annotation-comma-form` — Output does not include: *(changed: 3/3 vs 1/3
- `annotation-comma-form` — Meets criterion: The TTL annotation starts *(modified in work-item 04 and any reason follows a comma: 3/3 vs 0/3
- `annotation-comma-form` — Meets criterion: The export-ready annotation starts *(deprecated, see work-item 04 and any reason follows a comma: 3/3 vs 0/3
- `annotation-comma-form` — Meets criterion: No annotation contains an em-dash or a spaced hyphen as a separator: 3/3 vs 0/3
- `annotation-comma-form` — Meets criterion: No annotation names the work item as 04-usage-digest; it names it as work-item 04: 3/3 vs 0/3
- `annotation-comma-form` — Meets criterion: No annotation uses a verb other than superseded by, modified in or deprecated, see (for example changed in, renamed in or lowered to): 3/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| superseded-task-only | with_skill | 34 | 2 | 12/12, 11/12, 11/12 |
| superseded-task-only | old_skill | 34 | 2 | 11/12, 12/12, 11/12 |
| modified-and-deprecated-shapes | with_skill | 42 | 0 | 14/14, 14/14, 14/14 |
| modified-and-deprecated-shapes | old_skill | 39 | 3 | 13/14, 13/14, 13/14 |
| drift-warning-still-passes | with_skill | 52 | 2 | 18/18, 17/18, 17/18 |
| drift-warning-still-passes | old_skill | 40 | 14 | 14/18, 14/18, 12/18 |
| annotation-comma-form | with_skill | 45 | 0 | 15/15, 15/15, 15/15 |
| annotation-comma-form | old_skill | 14 | 31 | 4/15, 4/15, 6/15 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| superseded-task-only | Output includes: ~~ *(superseded by work-item 04 | 3/3 | 3/3 |
| superseded-task-only | Output includes: 1 superseded | 3/3 | 3/3 |
| superseded-task-only | Output does not include: # ~~ | 3/3 | 3/3 |
| superseded-task-only | Output does not include: ~~Task 3: | 3/3 | 3/3 |
| superseded-task-only | Output does not include: *(deprecated | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Edits Task 4 of 02-usage-export/tasks.md so the whole task line is struck through and followed by an annotation naming work-item 04 | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Strikes through only that task line: no heading, other task, requirement or design bullet is struck through, and surrounding text is not reformatted | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule | 2/3 | 1/3 |
| superseded-task-only | Meets criterion: The report lists 02-usage-export with one superseded impact in tasks.md and 03-profile-summary under No Impact | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: The report does not include an architectural drift warning, and the run ends by pointing to finishing-a-development-branch | 2/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(deprecated | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: one hour~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
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
| drift-warning-still-passes | Output includes: drift | 2/3 | 0/3 |
| drift-warning-still-passes | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| drift-warning-still-passes | Output includes: finishing-a-development-branch | 3/3 | 3/3 |
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
| drift-warning-still-passes | Meets criterion: Annotates the usage-meters references in all three older work items with the rename and a reference to work-item 04 | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Uses the modified shape (old name struck through, new name given) rather than striking through whole lines, headings or files | 3/3 | 0/3 |
| drift-warning-still-passes | Meets criterion: The report's impact table lists 01-quota-alerts, 02-usage-export and 03-usage-history with counts that total more than ten | 3/3 | 3/3 |
| drift-warning-still-passes | Meets criterion: Includes an architectural drift warning (or tells the user in the reply) because more than ten impacts land across several specs | 3/3 | 2/3 |
| drift-warning-still-passes | Meets criterion: Treats the warning as informational: the skill still passes and the reply hands over to finishing-a-development-branch | 2/3 | 3/3 |
| annotation-comma-form | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| annotation-comma-form | Output includes: *(deprecated, see work-item 04 | 3/3 | 0/3 |
| annotation-comma-form | Output does not include: work-item 04 — | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: *(deprecated — | 3/3 | 0/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest)* | 3/3 | 0/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest, | 3/3 | 1/3 |
| annotation-comma-form | Output does not include: work-item 04-usage-digest — | 3/3 | 3/3 |
| annotation-comma-form | Output does not include: *(changed | 3/3 | 1/3 |
| annotation-comma-form | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| annotation-comma-form | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| annotation-comma-form | Meets criterion: The TTL annotation starts *(modified in work-item 04 and any reason follows a comma | 3/3 | 0/3 |
| annotation-comma-form | Meets criterion: The export-ready annotation starts *(deprecated, see work-item 04 and any reason follows a comma | 3/3 | 0/3 |
| annotation-comma-form | Meets criterion: No annotation contains an em-dash or a spaced hyphen as a separator | 3/3 | 0/3 |
| annotation-comma-form | Meets criterion: No annotation names the work item as 04-usage-digest; it names it as work-item 04 | 3/3 | 0/3 |
| annotation-comma-form | Meets criterion: No annotation uses a verb other than superseded by, modified in or deprecated, see (for example changed in, renamed in or lowered to) | 3/3 | 0/3 |
