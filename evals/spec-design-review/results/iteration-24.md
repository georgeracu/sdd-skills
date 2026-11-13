# Eval results — iteration-24

**What this iteration tests:** #56: cases 1 and 6, 6 runs an arm, check 3.7 with the related-thing exclusion, against the snapshot before 3.7

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.322 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 939293 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 5d39e29c8d011707 |
| runs_per_arm | 6 |
| parallel | 3 |
| pack_hash | 8b56426fbdbe229f3d2381c683ef313d63b69d3fb09b762faf4e611dc1a175f7 |
| bundle_hash | 37720cd00677ab8b9551af21e55d4adfbfb651c2ed0740dce65e58dc22bd8c74 |
| snapshot_hash | 5bba6ba4e0642a2c5c09f507638103a01e309055bad93b35c3f926843ccffdb1 |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6111 (44/72), B: 0.5 (36/72) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7046 (±0.1927, n=2) | 212.3857 (±29.2405, n=2) | 395,123 (±64,774, n=2) |
| old_skill | 0.467 (±0.1099, n=2) | 169.2523 (±13.0051, n=2) | 320,454 (±19,237, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.2376, time_seconds +43.13, tokens +74,669

*measured over 2 cases × 6 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4160 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `aligned-design-approved` — Output includes: Critical discrepancies: 0: 4/6 vs 3/6
- `aligned-design-approved` — Output includes: Major discrepancies: 0: 4/6 vs 2/6
- `aligned-design-approved` — Output includes: **Status:** APPROVED: 4/6 vs 3/6
- `aligned-design-approved` — Output does not include: CRITICAL-001: 4/6 vs 3/6
- `aligned-design-approved` — Output does not include: MAJOR-001: 4/6 vs 2/6
- `aligned-design-approved` — Run produced file: design-review-summary.md: 4/6 vs 3/6
- `aligned-design-approved` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 4/6 vs 3/6
- `aligned-design-approved` — Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan: 4/6 vs 2/6
- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 4/6 vs 2/6
- `prose-only-drift-minor` — Output includes: MINOR-001: 6/6 vs 3/6
- `prose-only-drift-minor` — Output includes: Critical discrepancies: 0: 6/6 vs 4/6
- `prose-only-drift-minor` — Output includes: **Status:** APPROVED WITH ISSUES: 6/6 vs 3/6
- `prose-only-drift-minor` — Output does not include: CRITICAL-001: 6/6 vs 4/6
- `prose-only-drift-minor` — Run produced file: design-review-summary.md: 6/6 vs 4/6
- `prose-only-drift-minor` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 6/6 vs 4/6
- `prose-only-drift-minor` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 6/6 vs 3/6
- `prose-only-drift-minor` — Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR: 6/6 vs 0/6
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 5/6 vs 4/6
- `prose-only-drift-minor` — Meets criterion: Allows continuation to tasks.md generation: 5/6 vs 4/6

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 43 | 41 | 9/14, 9/14, 1/14, 14/14, 9/14, 1/14 |
| aligned-design-approved | old_skill | 30 | 54 | 5/14, 9/14, 1/14, 1/14, 0/14, 14/14 |
| prose-only-drift-minor | with_skill | 70 | 8 | 13/13, 13/13, 13/13, 13/13, 10/13, 8/13 |
| prose-only-drift-minor | old_skill | 45 | 33 | 0/13, 9/13, 0/13, 12/13, 12/13, 12/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 4/6 | 3/6 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 4/6 | 2/6 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 4/6 | 3/6 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 4/6 | 3/6 |
| aligned-design-approved | Output does not include: MAJOR-001 | 4/6 | 2/6 |
| aligned-design-approved | Output does not include: MINOR-001 | 1/6 | 1/6 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 1/6 | 1/6 |
| aligned-design-approved | Run produced file: design-review-summary.md | 4/6 | 3/6 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 4/6 | 3/6 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 1/6 | 1/6 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 1/6 | 1/6 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 4/6 | 2/6 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 3/6 | 3/6 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 4/6 | 2/6 |
| prose-only-drift-minor | Output includes: MINOR-001 | 6/6 | 3/6 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 6/6 | 4/6 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 4/6 | 4/6 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 6/6 | 3/6 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 6/6 | 4/6 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 4/6 | 4/6 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 6/6 | 4/6 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 6/6 | 4/6 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 6/6 | 3/6 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 6/6 | 0/6 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 4/6 | 4/6 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 5/6 | 4/6 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 5/6 | 4/6 |
