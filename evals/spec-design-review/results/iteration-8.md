# Eval results — iteration-8

**What this iteration tests:** iteration B2: new step 3.7 Glossary Consistency (read UBIQUITOUS_LANGUAGE.md, list prose domain nouns and identifiers including parameters, compare each with the glossary term and the existing name) and a Step 2 line to always read the glossary. Iteration B (D3-D5) showed the misses are detection, not severity: case 6 prose drift unseen 0/6, case 3 usageCount unseen 0/6. Baseline is the D3-D5 skill. Cases 3 and 6.

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
| judge_tokens | 498906 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 3e467806e07dc9b1 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | bc1f1fc30a98e41fab09b1091ba7d5c1d29c953e387d9f2d03af2d9f046880e8 |
| bundle_hash | 76f512e29b70ada57cd9b3eeb9191d78900a4136e89546f5e8463e59cc4f87de |
| snapshot_hash | 7df5d1efab2b78d3db204f691e180bffe5a24a4d00daeef0c9462abaef447b69 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6667 (24/36), B: 0.6111 (22/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7891 (±0.0314, n=2) | 186.5473 (±13.831, n=2) | 371,121 (±25,278, n=2) |
| old_skill | 0.7425 (±0.0758, n=2) | 190.0095 (±3.7008, n=2) | 379,394 (±40,980, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0466, time_seconds -3.46, tokens -8,273

*measured over 2 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2019 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `naming-drift-major` — Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold: 0/3 vs 2/3

with_skill scored above baseline:

- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 2/3 vs 1/3
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 2/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR: 1/3 vs 0/3
- `prose-only-drift-minor` — Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency: 2/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 2/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Allows continuation to tasks.md generation: 2/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| naming-drift-major | with_skill | 25 | 8 | 9/11, 7/11, 9/11 |
| naming-drift-major | old_skill | 27 | 6 | 10/11, 10/11, 7/11 |
| prose-only-drift-minor | with_skill | 32 | 7 | 7/13, 12/13, 13/13 |
| prose-only-drift-minor | old_skill | 26 | 13 | 12/13, 7/13, 7/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| naming-drift-major | Output includes: MAJOR-001 | 2/3 | 2/3 |
| naming-drift-major | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| naming-drift-major | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| naming-drift-major | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| naming-drift-major | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Reports no critical discrepancies | 3/3 | 3/3 |
| naming-drift-major | Meets criterion: Raises major discrepancies for the drifted identifiers UsageCounterRepository and usageCount against Usage_Meter, UsageMeterRepository and metered | 0/3 | 0/3 |
| naming-drift-major | Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold | 0/3 | 2/3 |
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 2/3 | 2/3 |
| prose-only-drift-minor | Output includes: MINOR-001 | 3/3 | 3/3 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 2/3 | 1/3 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 3/3 | 3/3 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 2/3 | 1/3 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 3/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 1/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 2/3 | 1/3 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 2/3 | 1/3 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 2/3 | 1/3 |
