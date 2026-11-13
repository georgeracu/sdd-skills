# Eval results — iteration-19

**What this iteration tests:** #37 round 2: case 6 at 6 runs an arm, A/A at main, to tell a skill miss from noise

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
| judge_tokens | 489094 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | db3e4348d9946e66 |
| runs_per_arm | 6 |
| parallel | 3 |
| pack_hash | 34ab762a96b44dcb809074fa20f26b94feefd8341b5c44bdb7c65cc8ebec59bc |
| bundle_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| snapshot_hash | ef25a6cd612623a2022f2b731bdea324ebdf5f9e86302b971630750f826dda11 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6389 (23/36), B: 0.7222 (26/36) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.6795 (±0.0, n=1) | 195.9447 (±0.0, n=1) | 437,365 (±0, n=1) |
| old_skill | 0.8077 (±0.0, n=1) | 230.6687 (±0.0, n=1) | 409,680 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate -0.1282, time_seconds -34.72, tokens +27,685

*measured over 1 case × 6 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3185 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `prose-only-drift-minor` — Output includes: Critical discrepancies: 0: 5/6 vs 6/6
- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 4/6 vs 6/6
- `prose-only-drift-minor` — Output does not include: CRITICAL-001: 5/6 vs 6/6
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 4/6 vs 6/6
- `prose-only-drift-minor` — Run produced file: design-review-summary.md: 5/6 vs 6/6
- `prose-only-drift-minor` — Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat: 5/6 vs 6/6
- `prose-only-drift-minor` — Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency: 4/6 vs 6/6
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 4/6 vs 6/6
- `prose-only-drift-minor` — Meets criterion: Allows continuation to tasks.md generation: 5/6 vs 6/6

with_skill scored above baseline:

- `prose-only-drift-minor` — Output includes: MINOR-001: 4/6 vs 3/6
- `prose-only-drift-minor` — Output includes: **Status:** APPROVED WITH ISSUES: 4/6 vs 3/6
- `prose-only-drift-minor` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 4/6 vs 3/6

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| prose-only-drift-minor | with_skill | 53 | 25 | 9/13, 9/13, 0/13, 11/13, 12/13, 12/13 |
| prose-only-drift-minor | old_skill | 63 | 15 | 12/13, 9/13, 9/13, 9/13, 12/13, 12/13 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| prose-only-drift-minor | Output includes: MINOR-001 | 4/6 | 3/6 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 5/6 | 6/6 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 4/6 | 6/6 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 4/6 | 3/6 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 5/6 | 6/6 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 4/6 | 6/6 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 5/6 | 6/6 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 5/6 | 6/6 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 4/6 | 3/6 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 0/6 | 0/6 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 4/6 | 6/6 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 4/6 | 6/6 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 5/6 | 6/6 |
