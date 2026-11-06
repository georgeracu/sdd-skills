# Eval results — iteration-7

**What this iteration tests:** iteration B (D3, D4, D5 from #25): Step 4 severity lists. Identifier drift (component, interface, data model, attribute, API path) is MAJOR and glossary drift confined to prose is MINOR; a performance constraint an acceptance criterion states with no design support is CRITICAL; data exposure or an auth bypass is CRITICAL and over-long retention is MAJOR; missing performance targets are MINOR only when no requirement states one. Baseline is the grounding-rule skill. Fixture round 6 applied before this run (IAM basis, GetItem consistency, concurrency test, requirement 1.5 wording). Cases 3, 6, 7 and 8.

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
| judge_tokens | 997571 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 11c03a9d533fd4be |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 17d2231d587b2141bbe46238472a42327a9fe66c42ea12f5e1b1334a701ba87e |
| bundle_hash | 7df5d1efab2b78d3db204f691e180bffe5a24a4d00daeef0c9462abaef447b69 |
| snapshot_hash | 2b397ee51d24aa74b4e7c052f4486f89770bc2e507d882dd243159410f0137c3 |
| input_drift_allowed | none |
| cases_total | 4 |
| cases_included | 4 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7222 (52/72), B: 0.75 (54/72) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7844 (±0.1523, n=4) | 182.3388 (±14.4263, n=4) | 347,609 (±21,721, n=4) |
| old_skill | 0.7984 (±0.1673, n=4) | 175.0204 (±13.2227, n=4) | 337,977 (±40,019, n=4) |

**Delta (with_skill − old_skill):** pass_rate -0.0140, time_seconds +7.32, tokens +9,632

*measured over 4 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3236 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `naming-drift-major` — Meets criterion: Names the prose drift (Account_Admin, Usage_Counter, Alert_Level) against the glossary terms Account_Owner, Usage_Meter and Alert_Threshold: 1/3 vs 2/3
- `naming-drift-major` — Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first: 2/3 vs 3/3
- `prose-only-drift-minor` — Output includes: MINOR-001: 1/3 vs 2/3
- `prose-only-drift-minor` — Output includes: Major discrepancies: 0: 2/3 vs 3/3
- `prose-only-drift-minor` — Output does not include: MAJOR-001: 2/3 vs 3/3
- `prose-only-drift-minor` — Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency: 2/3 vs 3/3

with_skill scored above baseline:

- `prose-only-drift-minor` — Output includes: **Status:** APPROVED WITH ISSUES: 2/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED: 2/3 vs 1/3
- `prose-only-drift-minor` — Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model: 3/3 vs 2/3
- `latency-constraint-unsupported-blocked` — Meets criterion: Explains why the design puts it at risk, such as synchronous conditional writes and a send on the ingest path, and gives a concrete fix with a way to verify it: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| naming-drift-major | with_skill | 27 | 6 | 9/11, 10/11, 8/11 |
| naming-drift-major | old_skill | 29 | 4 | 10/11, 9/11, 10/11 |
| prose-only-drift-minor | with_skill | 29 | 10 | 12/13, 8/13, 9/13 |
| prose-only-drift-minor | old_skill | 30 | 9 | 10/13, 12/13, 8/13 |
| latency-constraint-unsupported-blocked | with_skill | 19 | 14 | 4/11, 11/11, 4/11 |
| latency-constraint-unsupported-blocked | old_skill | 18 | 15 | 3/11, 11/11, 4/11 |
| unauthenticated-read-blocked | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| unauthenticated-read-blocked | old_skill | 33 | 0 | 11/11, 11/11, 11/11 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
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
| naming-drift-major | Meets criterion: Gives concrete rename fixes and allows continuation to tasks.md while recommending the major issues be fixed first | 2/3 | 3/3 |
| prose-only-drift-minor | Output includes: MINOR-001 | 1/3 | 2/3 |
| prose-only-drift-minor | Output includes: Critical discrepancies: 0 | 3/3 | 3/3 |
| prose-only-drift-minor | Output includes: Major discrepancies: 0 | 2/3 | 3/3 |
| prose-only-drift-minor | Output includes: **Status:** APPROVED WITH ISSUES | 2/3 | 1/3 |
| prose-only-drift-minor | Output does not include: CRITICAL-001 | 3/3 | 3/3 |
| prose-only-drift-minor | Output does not include: MAJOR-001 | 2/3 | 3/3 |
| prose-only-drift-minor | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 2/3 | 1/3 |
| prose-only-drift-minor | Meets criterion: Raises the prose drift (account administrator, alert level, usage counter) against Account_Owner, Alert_Threshold and Usage_Meter as MINOR | 0/3 | 0/3 |
| prose-only-drift-minor | Meets criterion: Raises no critical or major discrepancy, and does not class the prose drift as an identifier or data-model inconsistency | 2/3 | 3/3 |
| prose-only-drift-minor | Meets criterion: Confirms the identifiers (QuotaAlertEvaluator, firedThresholds, metered, the thresholds sub-resource) match the glossary and the existing model | 3/3 | 2/3 |
| prose-only-drift-minor | Meets criterion: Allows continuation to tasks.md generation | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Output includes: CRITICAL-001 | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Output includes: 2.5 | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Output includes: **Status:** BLOCKED | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Output does not include: Critical discrepancies: 0 | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Overall status is BLOCKED | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Raises a critical discrepancy naming AC 2.5 (50 ms p99 on the ingest call) as having no design support | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Explains why the design puts it at risk, such as synchronous conditional writes and a send on the ingest path, and gives a concrete fix with a way to verify it | 3/3 | 2/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Does not downgrade the constraint to a minor missing performance target | 1/3 | 1/3 |
| latency-constraint-unsupported-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 1/3 | 1/3 |
| unauthenticated-read-blocked | Output includes: CRITICAL-001 | 3/3 | 3/3 |
| unauthenticated-read-blocked | Output includes: ownerEmail | 3/3 | 3/3 |
| unauthenticated-read-blocked | Output includes: **Status:** BLOCKED | 3/3 | 3/3 |
| unauthenticated-read-blocked | Output does not include: Critical discrepancies: 0 | 3/3 | 3/3 |
| unauthenticated-read-blocked | Run produced file: design-review-summary.md | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Writes design-review-summary.md for the work item (01-quota-alerts) rather than only replying in chat | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Overall status is BLOCKED | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Raises a critical discrepancy that the thresholds read is registered without the authoriser | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Names the ownerEmail exposure in the response as part of the security finding | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Fixes it by keeping the route behind the authoriser and removing ownerEmail from ThresholdSet, with the design author as owner | 3/3 | 3/3 |
| unauthenticated-read-blocked | Meets criterion: Stops the workflow: does not generate tasks.md and asks for the design to be fixed and the review re-run | 3/3 | 3/3 |
