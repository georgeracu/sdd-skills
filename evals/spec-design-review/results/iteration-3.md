# Eval results — iteration-3

**What this iteration tests:** iteration 3 (grounding rule): Step 4 raises a discrepancy only with a named basis (acceptance criterion, ARCHITECTURE.md, glossary, API contract, or a defect that makes the implementation wrong). Chosen by George after iteration 2 showed 0 of 6 aligned runs reaching zero findings under D1. Bugfix fixture also fixed again (strongly consistent reads, explicit ingest retry on TransactionConflictException, IAM per action, glossary update). Cases 1, 3 and 5. Baseline is the D1 and D2 skill; global CLAUDE.md set aside

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
| judge_tokens | 667333 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 3f10deb0b1f3155b |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | d75fd61e3dd7d216628237c510cf1e0997a7ffadc57f518ef11b4b4e2bb9fd51 |
| bundle_hash | 2b397ee51d24aa74b4e7c052f4486f89770bc2e507d882dd243159410f0137c3 |
| snapshot_hash | 4f93ec5d53ad9b6fa33af9c42516bdb85806047abb9d5b35b90962c21edab220 |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.5926 (32/54), B: 0.6667 (36/54) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.6099 (±0.1496, n=3) | 169.6729 (±30.5783, n=3) | 360,729 (±51,217, n=3) |
| old_skill | 0.7081 (±0.17, n=3) | 168.0589 (±14.684, n=3) | 345,624 (±21,305, n=3) |

**Delta (with_skill − old_skill):** pass_rate -0.0982, time_seconds +1.61, tokens +15,104

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4286 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `aligned-design-approved` — Output includes: Critical discrepancies: 0: 2/3 vs 3/3
- `aligned-design-approved` — Output includes: **Status:** APPROVED: 2/3 vs 3/3
- `aligned-design-approved` — Output does not include: CRITICAL-001: 2/3 vs 3/3
- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 2/3 vs 3/3
- `naming-drift-major` — Output includes: MAJOR-001: 2/3 vs 3/3
- `naming-drift-major` — Output includes: Critical discrepancies: 0: 2/3 vs 3/3
- `naming-drift-major` — Output includes: **Status:** APPROVED WITH ISSUES: 2/3 vs 3/3
- `naming-drift-major` — Output does not include: CRITICAL-001: 2/3 vs 3/3
- `naming-drift-major` — Run produced file: design-review-summary.md: 2/3 vs 3/3
- `naming-drift-major` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 2/3 vs 3/3
- `naming-drift-major` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 2/3 vs 3/3
- `naming-drift-major` — Meets criterion: Reports no critical discrepancies: 2/3 vs 3/3
- `naming-drift-major` — Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 1/3 vs 2/3

with_skill scored above baseline:

- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan: 3/3 vs 2/3
- `bugfix-design-approved` — Output does not include: **Status:** APPROVED WITH: 1/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 18 | 24 | 7/14, 4/14, 7/14 |
| aligned-design-approved | old_skill | 20 | 22 | 7/14, 6/14, 7/14 |
| naming-drift-major | with_skill | 20 | 13 | 10/11, 0/11, 10/11 |
| naming-drift-major | old_skill | 29 | 4 | 10/11, 9/11, 10/11 |
| bugfix-design-approved | with_skill | 31 | 8 | 13/13, 9/13, 9/13 |
| bugfix-design-approved | old_skill | 30 | 9 | 11/13, 9/13, 10/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 2/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 0/3 | 0/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 2/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 2/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan | 3/3 | 2/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 2/3 | 3/3 |
| naming-drift-major | Output includes: MAJOR-001 | 2/3 | 3/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 2/3 | 3/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 2/3 | 3/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 2/3 | 3/3 |
| naming-drift-major | Run produced file: design-review-summary.md | 2/3 | 3/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 2/3 | 3/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 2/3 | 3/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 2/3 | 3/3 |
| naming-drift-major | Meets criterion: Raises major discrepancies for the drifted identifiers UsageCounterRepository and usageCount against Usage_Meter, UsageMeterRepository and metered | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 2/3 | 2/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: bugfix.md | 3/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 1/3 | 1/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 1/3 | 0/3 |
| bugfix-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 1/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 3/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 1/3 | 2/3 |
