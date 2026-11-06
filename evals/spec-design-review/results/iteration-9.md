# Eval results — iteration-9

**What this iteration tests:** iteration B3: D4 sharpened. A performance constraint an acceptance criterion states is CRITICAL when design.md gives no budget or measurement that shows the design meets it (was: with no design support). Iteration B: every run found the latency gap but 4 of 6 rated it MAJOR or MINOR as unverified. Step 3.7 from B2 reverted (no gain). Fixture round 7: evaluate takes firedThresholds. Baseline is the D3-D5 skill. Case 7.

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
| judge_tokens | 249989 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 29ba76e6fdaa0a20 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 158be0ee2f40d4c3257a405631f44aad8ebe957f0481fcaf1233997f79e7cac1 |
| bundle_hash | acda71811ad4f300dde99cde6f2100e53d52091f84905e2915fde4a1b1a497ea |
| snapshot_hash | 7df5d1efab2b78d3db204f691e180bffe5a24a4d00daeef0c9462abaef447b69 |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (18/18), B: 1.0 (18/18) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=1) | 178.7287 (±0.0, n=1) | 376,914 (±0, n=1) |
| old_skill | 1.0 (±0.0, n=1) | 183.2673 (±0.0, n=1) | 350,997 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.0000, time_seconds -4.54, tokens +25,917

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.0000 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| latency-constraint-unsupported-blocked | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| latency-constraint-unsupported-blocked | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| latency-constraint-unsupported-blocked | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Output includes: 2.5 | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Raises a critical discrepancy naming AC 2.5 (50 ms p99 on the ingest call) as having no design support | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Explains why the design puts it at risk, such as synchronous conditional writes and a send on the ingest path, and gives a concrete fix with a way to verify it | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Does not downgrade the constraint to a minor missing performance target | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 3/3 | 3/3 |
