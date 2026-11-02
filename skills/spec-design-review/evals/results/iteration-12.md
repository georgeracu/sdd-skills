# Eval results — iteration-12

**What this iteration tests:** #37 fixture round 9: A/A on case 1 (both arms at 4203e9a)

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
| judge_tokens | 276792 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | a5b20b36bbdc206c |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 7b377eecd1b0db54868ddce2325800852122a549b6c4459537ba5cfaa801f804 |
| bundle_hash | f031cd22c342d6b211a19ff7bb08324b5b19475ce6c699afb28281767608e18a |
| snapshot_hash | f031cd22c342d6b211a19ff7bb08324b5b19475ce6c699afb28281767608e18a |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.4444 (8/18), B: 0.5 (9/18) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.5952 (±0.0, n=1) | 210.3553 (±0.0, n=1) | 357,953 (±0, n=1) |
| old_skill | 0.5714 (±0.0, n=1) | 179.2353 (±0.0, n=1) | 369,612 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.0238, time_seconds +31.12, tokens -11,659

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.1010 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `aligned-design-approved` — Meets criterion: Says the work item may proceed to tasks.md generation: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| aligned-design-approved | with_skill | 25 | 17 | 9/14, 7/14, 9/14 |
| aligned-design-approved | old_skill | 24 | 18 | 6/14, 9/14, 9/14 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| aligned-design-approved | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| aligned-design-approved | Output includes: Major discrepancies: 0 | 2/3 | 2/3 |
| aligned-design-approved | Output includes: **Status:** APPROVED | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| aligned-design-approved | Output does not include: MAJOR-001 | 2/3 | 2/3 |
| aligned-design-approved | Output does not include: MINOR-001 | 0/3 | 0/3 |
| aligned-design-approved | Output does not include: **Status:** APPROVED WITH | 0/3 | 0/3 |
| aligned-design-approved | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Overall status is APPROVED, not BLOCKED and not APPROVED WITH ISSUES | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Reports zero critical, major and minor discrepancies and covers all five requirements in the statistics | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Confirms the alignments that carry the design, such as ingest-time evaluation, Notification_Service delivery with the quota-threshold template and the thresholds sub-resource placed like plan | 3/3 | 3/3 |
| aligned-design-approved | Meets criterion: Does not invent discrepancies or demand changes to a design that is aligned | 0/3 | 0/3 |
| aligned-design-approved | Meets criterion: Says the work item may proceed to tasks.md generation | 3/3 | 2/3 |
