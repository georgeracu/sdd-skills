# Eval results — iteration-5

**What this iteration tests:** S4: partial replacement is Modified, strike only the changed words (new case 6)

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
| judge_tokens | 264706 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | e6a8ceba0405736f |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 13effab48bc05a42373d2cdb85b605dc31a268e409d6059a7352598d9e802318 |
| bundle_hash | 2f391173059f3b3e7b9d0c7232df5ea4fe5ec80e378eceb95930b19b85541d6c |
| snapshot_hash | 1c82b591e427a3bdb94317c9daa582d87aa679d58eb63cde02e1f9e68bd859cb |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.2 (3/15), B: 0.7333 (11/15) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8444 (±0.0, n=1) | 74.094 (±0.0, n=1) | 561,096 (±0, n=1) |
| old_skill | 0.4444 (±0.0, n=1) | 60.6503 (±0.0, n=1) | 553,811 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.4000, time_seconds +13.44, tokens +7,285

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2200 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `partial-replacement-modified` — Output includes: *(modified in work-item 04: 3/3 vs 0/3
- `partial-replacement-modified` — Output does not include: ~~Task 3:: 2/3 vs 0/3
- `partial-replacement-modified` — Output does not include: ~~Writes one CSV: 3/3 vs 2/3
- `partial-replacement-modified` — Output does not include: *(superseded by: 2/3 vs 0/3
- `partial-replacement-modified` — Output does not include: SUPERSEDED: 2/3 vs 0/3
- `partial-replacement-modified` — Meets criterion: Does not strike through the whole of Task 1, Task 3 or any design bullet: 2/3 vs 0/3
- `partial-replacement-modified` — Meets criterion: Annotates Task 3 and the design bullet that writes to the `exports` bucket as Modified, each with the `usage-archive` bucket given: 2/3 vs 0/3
- `partial-replacement-modified` — Meets criterion: Classifies nothing as Superseded: 2/3 vs 0/3
- `partial-replacement-modified` — Meets criterion: The report lists the impacts in 02-usage-export as MODIFIED and lists 03-profile-summary under No Impact: 2/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| partial-replacement-modified | with_skill | 38 | 7 | 15/15, 15/15, 8/15 |
| partial-replacement-modified | old_skill | 20 | 25 | 6/15, 7/15, 7/15 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| partial-replacement-modified | Output includes: *(modified in work-item 04 | 3/3 | 0/3 |
| partial-replacement-modified | Output includes: usage-archive | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: ~~Task 1: | 3/3 | 3/3 |
| partial-replacement-modified | Output does not include: ~~Task 3: | 2/3 | 0/3 |
| partial-replacement-modified | Output does not include: ~~Writes one CSV | 3/3 | 2/3 |
| partial-replacement-modified | Output does not include: *(superseded by | 2/3 | 0/3 |
| partial-replacement-modified | Output does not include: SUPERSEDED | 2/3 | 0/3 |
| partial-replacement-modified | Run produced file: project/work-items/04-export-archive/spec-maintenance-report.md | 3/3 | 3/3 |
| partial-replacement-modified | Run produced file: project/work-items/02-usage-export/tasks.md | 3/3 | 3/3 |
| partial-replacement-modified | Run produced file: project/work-items/02-usage-export/design.md | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Classifies Task 1 of 02-usage-export as Modified, because its loader and writer survive, and strikes through only the `exports` bucket name in it | 3/3 | 3/3 |
| partial-replacement-modified | Meets criterion: Does not strike through the whole of Task 1, Task 3 or any design bullet | 2/3 | 0/3 |
| partial-replacement-modified | Meets criterion: Annotates Task 3 and the design bullet that writes to the `exports` bucket as Modified, each with the `usage-archive` bucket given | 2/3 | 0/3 |
| partial-replacement-modified | Meets criterion: Classifies nothing as Superseded | 2/3 | 0/3 |
| partial-replacement-modified | Meets criterion: The report lists the impacts in 02-usage-export as MODIFIED and lists 03-profile-summary under No Impact | 2/3 | 0/3 |
