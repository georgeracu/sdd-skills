# Eval results — iteration-2

**What this iteration tests:** S2: completion when every peer is annotated or under No Impact; classify only items from sections read (cases 2, 3)

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
| judge_tokens | 497888 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | abe752caf9c2bb5e |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | d37b5c3be9ad9078e4d516d0e4848d76885170628020d47ceeb08a799f2aea7e |
| bundle_hash | ee2bcaeb326f1f8a60ef51a3784b43304b52f3f09e58a48265d6453ad04848cc |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.697 (23/33), B: 0.7879 (26/33) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.5159 (±0.0397, n=2) | 77.971 (±8.6977, n=2) | 495,188 (±35,010, n=2) |
| old_skill | 0.5675 (±0.2104, n=2) | 58.2763 (±5.9887, n=2) | 452,934 (±14,222, n=2) |

**Delta (with_skill − old_skill):** pass_rate -0.0516, time_seconds +19.69, tokens +42,253

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3417 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `superseded-task-only` — Output includes: 1 superseded: 2/3 vs 3/3
- `superseded-task-only` — Output does not include: # ~~: 2/3 vs 3/3
- `superseded-task-only` — Output does not include: ~~Task 3:: 2/3 vs 3/3
- `superseded-task-only` — Output does not include: *(deprecated: 2/3 vs 3/3
- `superseded-task-only` — Run produced file: project/work-items/02-usage-export/tasks.md: 0/3 vs 1/3
- `superseded-task-only` — Meets criterion: Edits Task 4 of 02-usage-export/tasks.md so the whole task line is struck through and followed by an annotation naming work-item 04: 2/3 vs 3/3
- `superseded-task-only` — Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule: 1/3 vs 2/3
- `superseded-task-only` — Meets criterion: The report lists 02-usage-export with one superseded impact in tasks.md and 03-profile-summary under No Impact: 2/3 vs 3/3
- `modified-and-deprecated-shapes` — Meets criterion: Leaves 01-quota-alerts untouched because it has no implementation-audit.md, and lists 03-profile-summary under No Impact: 2/3 vs 3/3
- `modified-and-deprecated-shapes` — Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged: 2/3 vs 3/3

with_skill scored above baseline:

- `modified-and-deprecated-shapes` — Output includes: *(deprecated: 1/3 vs 0/3
- `modified-and-deprecated-shapes` — Output includes: one hour~~: 1/3 vs 0/3
- `modified-and-deprecated-shapes` — Output does not include: ~~Notification_Service sends: 2/3 vs 1/3
- `modified-and-deprecated-shapes` — Output does not include: # ~~: 2/3 vs 1/3
- `modified-and-deprecated-shapes` — Output does not include: *(superseded by work-item 04)*: 2/3 vs 1/3
- `modified-and-deprecated-shapes` — Meets criterion: Annotates the export-ready bullet as deprecated with a reference to work-item 04 and does not strike through any part of that line: 2/3 vs 1/3
- `modified-and-deprecated-shapes` — Meets criterion: Uses two different annotation shapes for the two impacts, not one shape for both: 2/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| superseded-task-only | with_skill | 20 | 16 | 9/12, 8/12, 3/12 |
| superseded-task-only | old_skill | 28 | 8 | 9/12, 10/12, 9/12 |
| modified-and-deprecated-shapes | with_skill | 20 | 22 | 9/14, 0/14, 11/14 |
| modified-and-deprecated-shapes | old_skill | 15 | 27 | 3/14, 7/14, 5/14 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| superseded-task-only | Output includes: *(superseded by work-item 04)* | 0/3 | 0/3 |
| superseded-task-only | Output includes: 1 superseded | 2/3 | 3/3 |
| superseded-task-only | Output does not include: # ~~ | 2/3 | 3/3 |
| superseded-task-only | Output does not include: ~~Task 3: | 2/3 | 3/3 |
| superseded-task-only | Output does not include: *(deprecated | 2/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 1/3 | 1/3 |
| superseded-task-only | Run produced file: project/work-items/02-usage-export/tasks.md | 0/3 | 1/3 |
| superseded-task-only | Meets criterion: Edits Task 4 of 02-usage-export/tasks.md so the whole task line is struck through and followed by an annotation naming work-item 04 | 2/3 | 3/3 |
| superseded-task-only | Meets criterion: Strikes through only that task line: no heading, other task, requirement or design bullet is struck through, and surrounding text is not reformatted | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule | 1/3 | 2/3 |
| superseded-task-only | Meets criterion: The report lists 02-usage-export with one superseded impact in tasks.md and 03-profile-summary under No Impact | 2/3 | 3/3 |
| superseded-task-only | Meets criterion: The report does not include an architectural drift warning, and the run ends by pointing to finishing-a-development-branch | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(deprecated | 1/3 | 0/3 |
| modified-and-deprecated-shapes | Output includes: one hour~~ | 1/3 | 0/3 |
| modified-and-deprecated-shapes | Output includes: work-item 04)* | 0/3 | 0/3 |
| modified-and-deprecated-shapes | Output does not include: ~~Notification_Service sends | 2/3 | 1/3 |
| modified-and-deprecated-shapes | Output does not include: # ~~ | 2/3 | 1/3 |
| modified-and-deprecated-shapes | Output does not include: *(superseded by work-item 04)* | 2/3 | 1/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 0/3 | 0/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/02-usage-export/design.md | 0/3 | 0/3 |
| modified-and-deprecated-shapes | Meets criterion: Edits the Quota_Cache TTL bullet in 02-usage-export/design.md so only the old value is struck through, the new value (five minutes) is given, and the annotation names work-item 04 | 2/3 | 2/3 |
| modified-and-deprecated-shapes | Meets criterion: Annotates the export-ready bullet as deprecated with a reference to work-item 04 and does not strike through any part of that line | 2/3 | 1/3 |
| modified-and-deprecated-shapes | Meets criterion: Uses two different annotation shapes for the two impacts, not one shape for both | 2/3 | 1/3 |
| modified-and-deprecated-shapes | Meets criterion: Leaves 01-quota-alerts untouched because it has no implementation-audit.md, and lists 03-profile-summary under No Impact | 2/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: The report classifies the TTL change as MODIFIED and the export-ready bullet as DEPRECATED, with 02-usage-export design.md as the file changed | 2/3 | 2/3 |
| modified-and-deprecated-shapes | Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged | 2/3 | 3/3 |
