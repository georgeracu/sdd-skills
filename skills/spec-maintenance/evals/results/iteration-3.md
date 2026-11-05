# Eval results — iteration-3

**What this iteration tests:** S2 re-run on the fixed pack: Status PASSED audits, outputs/ paths in prompts (cases 2, 3)

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
| judge_tokens | 367924 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 50e0a56e6cb838ca |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | bdb1a5cb136e130ed3a26d4c95268c90468683c85f05b2e39146d42ade7e7bbd |
| bundle_hash | ee2bcaeb326f1f8a60ef51a3784b43304b52f3f09e58a48265d6453ad04848cc |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.9697 (32/33), B: 0.9394 (31/33) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8948 (±0.0338, n=2) | 68.4687 (±12.35, n=2) | 596,765 (±12,797, n=2) |
| old_skill | 0.9206 (±0.0318, n=2) | 63.166 (±3.7373, n=2) | 546,580 (±49,106, n=2) |

**Delta (with_skill − old_skill):** pass_rate -0.0258, time_seconds +5.30, tokens +50,186

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0393 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `superseded-task-only` — Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule: 1/3 vs 2/3
- `modified-and-deprecated-shapes` — Output includes: work-item 04)*: 0/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| superseded-task-only | with_skill | 31 | 5 | 11/12, 10/12, 10/12 |
| superseded-task-only | old_skill | 32 | 4 | 11/12, 10/12, 11/12 |
| modified-and-deprecated-shapes | with_skill | 39 | 3 | 13/14, 13/14, 13/14 |
| modified-and-deprecated-shapes | old_skill | 40 | 2 | 13/14, 14/14, 13/14 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| superseded-task-only | Output includes: *(superseded by work-item 04)* | 0/3 | 0/3 |
| superseded-task-only | Output includes: 1 superseded | 3/3 | 3/3 |
| superseded-task-only | Output does not include: # ~~ | 3/3 | 3/3 |
| superseded-task-only | Output does not include: ~~Task 3: | 3/3 | 3/3 |
| superseded-task-only | Output does not include: *(deprecated | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| superseded-task-only | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Edits Task 4 of 02-usage-export/tasks.md so the whole task line is struck through and followed by an annotation naming work-item 04 | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Strikes through only that task line: no heading, other task, requirement or design bullet is struck through, and surrounding text is not reformatted | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: Does not read or edit anything in 01-quota-alerts, which has no implementation-audit.md, despite its mention of the export-nightly rule | 1/3 | 2/3 |
| superseded-task-only | Meets criterion: The report lists 02-usage-export with one superseded impact in tasks.md and 03-profile-summary under No Impact | 3/3 | 3/3 |
| superseded-task-only | Meets criterion: The report does not include an architectural drift warning, and the run ends by pointing to finishing-a-development-branch | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: *(deprecated | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: one hour~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output includes: work-item 04)* | 0/3 | 1/3 |
| modified-and-deprecated-shapes | Output does not include: ~~Notification_Service sends | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: # ~~ | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Output does not include: *(superseded by work-item 04)* | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/04-usage-digest/spec-maintenance-report.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Edits the Quota_Cache TTL bullet in 02-usage-export/design.md so only the old value is struck through, the new value (five minutes) is given, and the annotation names work-item 04 | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Annotates the export-ready bullet as deprecated with a reference to work-item 04 and does not strike through any part of that line | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Uses two different annotation shapes for the two impacts, not one shape for both | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Leaves 01-quota-alerts untouched because it has no implementation-audit.md, and lists 03-profile-summary under No Impact | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: The report classifies the TTL change as MODIFIED and the export-ready bullet as DEPRECATED, with 02-usage-export design.md as the file changed | 3/3 | 3/3 |
| modified-and-deprecated-shapes | Meets criterion: Makes minimal in-place edits: the rest of each bullet and the surrounding design text are unchanged | 3/3 | 3/3 |
