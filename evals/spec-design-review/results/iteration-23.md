# Eval results — iteration-23

**What this iteration tests:** #56: case 6 alone, 6 runs an arm, check 3.7 (glossary terms in prose) against the snapshot before it

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.322 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | none |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 0 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | dfcdd0d6aaf8c3e0 |
| runs_per_arm | 6 |
| parallel | 3 |
| pack_hash | c77bf4439e4c8602cf18ccd00fe707cdf23728b6719a7b585fef1de12309628a |
| bundle_hash | 9fcaca7507cc833443f5adfe1357b25a60e7a8f23ea474662a2a579b13aad1a5 |
| snapshot_hash | 5bba6ba4e0642a2c5c09f507638103a01e309055bad93b35c3f926843ccffdb1 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | prose-only-drift-minor |
| judge_failures | prose-only-drift-minor: exit 1, prose-only-drift-minor: exit 1, prose-only-drift-minor: exit 1, prose-only-drift-minor: timed out after 180s |
| label_pass_rates | none |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.75 (±0.0, n=1) | 207.74 (±0.0, n=1) | 381,271 (±0, n=1) |
| old_skill | 1.0 (±0.0, n=1) | 264.6003 (±0.0, n=1) | 457,826 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate -0.2500, time_seconds -56.86, tokens -76,556

*measured over 1 case × 4 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4330 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `prose-only-drift-minor` — Output includes: MINOR-001: 3/4 vs 4/4
- `prose-only-drift-minor` — Output includes: Critical discrepancies: 0: 3/4 vs 4/4
- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 3/4 vs 4/4
- `prose-only-drift-minor` — Output includes: **Status:** APPROVED WITH ISSUES: 3/4 vs 4/4
- `prose-only-drift-minor` — Output does not include: CRITICAL-001: 3/4 vs 4/4
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 3/4 vs 4/4
- `prose-only-drift-minor` — Run produced file: design-review-summary.md: 3/4 vs 4/4

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| prose-only-drift-minor | with_skill | 21 | 7 | 7/7, 7/7, 0/7, 7/7 |
| prose-only-drift-minor | old_skill | 28 | 0 | 7/7, 7/7, 7/7, 7/7 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| prose-only-drift-minor | Output includes: MINOR-001 | 3/4 | 4/4 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 3/4 | 4/4 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 3/4 | 4/4 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 3/4 | 4/4 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 3/4 | 4/4 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 3/4 | 4/4 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 3/4 | 4/4 |
