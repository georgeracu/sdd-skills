# Eval results — iteration-4

**What this iteration tests:** iteration 4 (grounding rule, rerun): same skill and baseline as iteration 3, after fixture round 3 (service layer returns domain types, default set on the evaluation path, retry after a failed send, operationIds, glossary additions, one Usage_Meter definition, the ingest Account read in ARCHITECTURE.md, usage-meters on-demand; bugfix read cost, retry in the interface, concurrent plan changes) and prompts without the work-item path, which made one iteration-3 run stop looking for files. Cases 1, 3 and 5. Baseline is the D1 and D2 skill; global CLAUDE.md set aside

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.310 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 832289 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a5140d7cfd4fbd2c |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 53b4aecc95773f58d42675d79b59a3dcce89b591dd4b776c0af617b90a99da34 |
| bundle_hash | 2b397ee51d24aa74b4e7c052f4486f89770bc2e507d882dd243159410f0137c3 |
| snapshot_hash | 4f93ec5d53ad9b6fa33af9c42516bdb85806047abb9d5b35b90962c21edab220 |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.5926 (32/54), B: 0.6296 (34/54) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7334 (±0.1076, n=3) | 206.2454 (±25.0527, n=3) | 373,402 (±11,161, n=3) |
| old_skill | 0.6837 (±0.1886, n=3) | 171.8913 (±11.5784, n=3) | 334,374 (±12,914, n=3) |

**Delta (with_skill − old_skill):** pass_rate +0.0497, time_seconds +34.35, tokens +39,028

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2206 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `naming-drift-major` — Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold: 1/3 vs 2/3
- `bugfix-design-approved` — Output includes: Critical discrepancies: 0: 2/3 vs 3/3
- `bugfix-design-approved` — Output includes: **Status:** APPROVED: 2/3 vs 3/3
- `bugfix-design-approved` — Run produced file: design-review-summary.md: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected: 1/3 vs 2/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 1/3 vs 2/3

with_skill scored above baseline:

- `aligned-design-approved` — Output includes: Major discrepancies: 0: 3/3 vs 0/3
- `aligned-design-approved` — Output does not include: MAJOR-001: 3/3 vs 0/3
- `aligned-design-approved` — Output does not include: MINOR-001: 1/3 vs 0/3
- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan: 3/3 vs 2/3
- `aligned-design-approved` — Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 3/3 vs 1/3
- `bugfix-design-approved` — Output does not include: MAJOR-001: 2/3 vs 1/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 32 | 10 | 9/14, 9/14, 14/14 |
| aligned-design-approved | old_skill | 18 | 24 | 5/14, 7/14, 6/14 |
| naming-drift-major | with_skill | 28 | 5 | 9/11, 10/11, 9/11 |
| naming-drift-major | old_skill | 29 | 4 | 10/11, 10/11, 9/11 |
| bugfix-design-approved | with_skill | 23 | 16 | 8/13, 11/13, 4/13 |
| bugfix-design-approved | old_skill | 29 | 10 | 9/13, 11/13, 9/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 3/3 | 0/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 3/3 | 0/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 1/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan | 3/3 | 2/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 1/3 | 0/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 1/3 |
| naming-drift-major | Output includes: MAJOR-001 | 3/3 | 3/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| naming-drift-major | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Raises major discrepancies for the drifted identifiers UsageCounterRepository and usageCount against Usage_Meter, UsageMeterRepository and metered | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 1/3 | 2/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 2/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 2/3 | 1/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 1/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 0/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 1/3 | 2/3 |
