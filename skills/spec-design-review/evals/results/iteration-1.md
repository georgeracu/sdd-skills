# Eval results — iteration-1

**What this iteration tests:** iteration 1 (A, verdict): D1 APPROVED WITH ISSUES and the status-from-highest-severity rule, D2 single **Status:** line and one Recommendations section, from #25. Cases 1, 3 and 5. Baseline is the skill at 6a94dd5; global CLAUDE.md set aside

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
| judge_tokens | 843801 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 34b4a4846d6ac910 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 6bc5c060d9b14c66af1d17867060d81b661ba1638ea0a253a3c80a4ca2c22461 |
| bundle_hash | 4f93ec5d53ad9b6fa33af9c42516bdb85806047abb9d5b35b90962c21edab220 |
| snapshot_hash | 9c78b064d9193c2b727f6f4ec81531d82c6ab05363d9551784bfb7891765b53c |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.3333 (18/54), B: 0.3889 (21/54) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.3385 (±0.0469, n=3) | 161.9826 (±6.71, n=3) | 329,059 (±29,981, n=3) |
| old_skill | 0.2979 (±0.0435, n=3) | 154.0006 (±27.7484, n=3) | 325,396 (±46,783, n=3) |

**Delta (with_skill − old_skill):** pass_rate +0.0406, time_seconds +7.98, tokens +3,662

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1468 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 0/3 vs 2/3
- `aligned-design-approved` — Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned: 0/3 vs 1/3
- `bugfix-design-approved` — Output includes: bugfix.md: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed: 2/3 vs 3/3
- `bugfix-design-approved` — Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected: 0/3 vs 2/3

with_skill scored above baseline:

- `aligned-design-approved` — Output includes: Critical discrepancies: 0: 3/3 vs 2/3
- `aligned-design-approved` — Output includes: **Status:** APPROVED: 3/3 vs 0/3
- `aligned-design-approved` — Output does not include: CRITICAL-001: 3/3 vs 2/3
- `aligned-design-approved` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 3/3 vs 2/3
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan: 2/3 vs 1/3
- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 3/3 vs 1/3
- `naming-drift-major` — Meets criterion: Raises major discrepancies that name the drifted terms (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold: 1/3 vs 0/3
- `bugfix-design-approved` — Output does not include: CRITICAL-001: 1/3 vs 0/3
- `bugfix-design-approved` — Output does not include: MAJOR-001: 1/3 vs 0/3
- `bugfix-design-approved` — Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 17 | 25 | 6/14, 6/14, 5/14 |
| aligned-design-approved | old_skill | 11 | 31 | 1/14, 6/14, 4/14 |
| naming-drift-major | with_skill | 10 | 23 | 3/11, 3/11, 4/11 |
| naming-drift-major | old_skill | 9 | 24 | 3/11, 3/11, 3/11 |
| bugfix-design-approved | with_skill | 12 | 27 | 5/13, 3/13, 4/13 |
| bugfix-design-approved | old_skill | 14 | 25 | 5/13, 4/13, 5/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 2/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 0/3 | 0/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 0/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 2/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 0/3 | 2/3 |
| aligned-design-approved | Run produced file: 01-quota-alerts/design-review-summary.md | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 2/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource shaped like plan | 2/3 | 1/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 0/3 | 1/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 1/3 |
| naming-drift-major | Output includes: MAJOR-001 | 3/3 | 3/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 0/3 | 0/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 0/3 | 0/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 0/3 | 0/3 |
| naming-drift-major | Run produced file: 01-quota-alerts/design-review-summary.md | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Raises major discrepancies that name the drifted terms (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 1/3 | 0/3 |
| naming-drift-major | Meets criterion: Flags usageCount as inconsistent with the existing metered attribute and suggests renaming it | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 0/3 | 0/3 |
| bugfix-design-approved | Output includes: Critical discrepancies: 0 | 0/3 | 0/3 |
| bugfix-design-approved | Output includes: bugfix.md | 2/3 | 3/3 |
| bugfix-design-approved | Output includes: **Status:** APPROVED | 0/3 | 0/3 |
| bugfix-design-approved | Output does not include: CRITICAL-001 | 1/3 | 0/3 |
| bugfix-design-approved | Output does not include: MAJOR-001 | 1/3 | 0/3 |
| bugfix-design-approved | Output does not include: **Status:** APPROVED WITH | 3/3 | 3/3 |
| bugfix-design-approved | Run produced file: 05-stale-quota-cache-bugfix/design-review-summary.md | 0/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Writes design-review-summary.md for the 05-stale-quota-cache-bugfix work item | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Lists bugfix.md, not requirements.md, among the documents reviewed | 2/3 | 3/3 |
| bugfix-design-approved | Meets criterion: Checks coverage of the expected behaviour (2.1, 2.2) and of the unchanged-behaviour items (3.1, 3.2, 3.3), confirming each is protected | 0/3 | 2/3 |
| bugfix-design-approved | Meets criterion: Overall status is APPROVED, with no critical or major discrepancies raised against a sound design | 0/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Confirms the fix fits ARCHITECTURE.md, including that the cache and the accounts table load are preserved and no API change is needed | 1/3 | 0/3 |
| bugfix-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 0/3 | 0/3 |
