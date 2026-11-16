# Eval results — iteration-26

**What this iteration tests:** #56 PR 1 gate: cases 1 and 6, 9 runs an arm, check 3.7 narrowed to words the glossary term can replace, against the main skill

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.4.0 |
| adapter | claude_code |
| runtime_version | 2.1.325 (Claude Code) |
| runner_model | claude-sonnet-5-5 |
| runner_model_canonical | claude-sonnet-5-5 |
| runner_model_requested | claude-sonnet-5-5 |
| judge_model | claude-opus-5-5 |
| judge_model_requested | claude-opus-5-5 |
| judge_tokens | 1299068 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | cbede38bb54a31f2 |
| runs_per_arm | 9 |
| parallel | 3 |
| pack_hash | 8b56426fbdbe229f3d2381c683ef313d63b69d3fb09b762faf4e611dc1a175f7 |
| bundle_hash | 4049873b9c1d1b0f9d89574470bf5bbce48357169b5b9cdc04cf29574445e71b |
| snapshot_hash | 5bba6ba4e0642a2c5c09f507638103a01e309055bad93b35c3f926843ccffdb1 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.9074 (98/108), B: 0.9259 (100/108) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9872 (±0.0128, n=2) | 113.8371 (±0.1011, n=2) | 231,119 (±9,934, n=2) |
| old_skill | 0.9079 (±0.0665, n=2) | 101.9022 (±10.0241, n=2) | 222,643 (±813, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.0793, time_seconds +11.93, tokens +8,476

*measured over 2 cases × 9 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1774 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 6/9 vs 9/9

with_skill scored above baseline:

- `aligned-design-approved` — Output does not include: MINOR-001: 9/9 vs 5/9
- `aligned-design-approved` — Output does not include: **Status:** APPROVED WITH: 9/9 vs 5/9
- `aligned-design-approved` — Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES: 9/9 vs 5/9
- `aligned-design-approved` — Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics: 9/9 vs 5/9
- `aligned-design-approved` — Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned: 9/9 vs 5/9
- `prose-only-drift-minor` — Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR: 9/9 vs 6/9

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 126 | 0 | 14/14, 14/14, 14/14, 14/14, 14/14, 14/14, 14/14, 14/14, 14/14 |
| aligned-design-approved | old_skill | 106 | 20 | 14/14, 9/14, 14/14, 9/14, 9/14, 14/14, 9/14, 14/14, 14/14 |
| prose-only-drift-minor | with_skill | 114 | 3 | 13/13, 13/13, 13/13, 12/13, 13/13, 13/13, 12/13, 13/13, 12/13 |
| prose-only-drift-minor | old_skill | 114 | 3 | 13/13, 12/13, 13/13, 13/13, 13/13, 13/13, 12/13, 12/13, 13/13 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-1 | with_skill | 9 | 0 | 0 |
| eval-1 | old_skill | 9 | 0 | 0 |
| eval-6 | with_skill | 9 | 0 | 0 |
| eval-6 | old_skill | 9 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 9/9 | 9/9 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 9/9 | 9/9 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 9/9 | 9/9 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 9/9 | 9/9 |
| aligned-design-approved | Output does not include: MAJOR-001 | 9/9 | 9/9 |
| aligned-design-approved | Output does not include: MINOR-001 | 9/9 | 5/9 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 9/9 | 5/9 |
| aligned-design-approved | Run produced file: design-review-summary.md | 9/9 | 9/9 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 9/9 | 9/9 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 9/9 | 5/9 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 9/9 | 5/9 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 9/9 | 9/9 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 9/9 | 5/9 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 9/9 | 9/9 |
| prose-only-drift-minor | Output includes: MINOR-001 | 9/9 | 9/9 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 9/9 | 9/9 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 9/9 | 9/9 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 9/9 | 9/9 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 9/9 | 9/9 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 9/9 | 9/9 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 9/9 | 9/9 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 9/9 | 9/9 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 9/9 | 9/9 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 9/9 | 6/9 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 9/9 | 9/9 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 6/9 | 9/9 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 9/9 | 9/9 |
