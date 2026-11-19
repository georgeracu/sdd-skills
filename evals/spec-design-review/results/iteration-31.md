# Eval results — iteration-31

**What this iteration tests:** PR B: new domain-model case only (id), 1 run an arm, the changed skill against the main skill

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.4.0 |
| adapter | claude_code |
| runtime_version | 2.1.328 (Claude Code) |
| runner_model | claude-sonnet-5-5 |
| runner_model_canonical | claude-sonnet-5-5 |
| runner_model_requested | claude-sonnet-5-5 |
| judge_model | claude-opus-5-5 |
| judge_model_requested | claude-opus-5-5 |
| judge_tokens | 123407 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | e66696c08f4f0a59 |
| runs_per_arm | 1 |
| parallel | 1 |
| pack_hash | 3c9cff143a2b7bda293007de658f05b8d8ff8fe0e5a9eee97ecd7400a862a9d7 |
| bundle_hash | f6d677b0c5ccda02ede69cbd6c04cca8c9e22af3038f2f060277714b1df57172 |
| snapshot_hash | 4049873b9c1d1b0f9d89574470bf5bbce48357169b5b9cdc04cf29574445e71b |
| input_drift_allowed | none |
| cases_total | 2 |
| cases_included | 2 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7273 (8/11), B: 0.8182 (9/11) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=2) | 140.3265 (±22.0055, n=2) | 316,670 (±42,768, n=2) |
| old_skill | 0.5512 (±0.1761, n=2) | 129.4485 (±4.0115, n=2) | 284,634 (±20,188, n=2) |

**Delta (with_skill − old_skill):** pass_rate +0.4488, time_seconds +10.88, tokens +32,036

*measured over 2 cases × 1 run.*

## Regressions and gains

with_skill scored above baseline:

- `domain-noun-not-in-glossary-major` — Output includes: MAJOR-001: 1/1 vs 0/1
- `domain-noun-not-in-glossary-major` — Meets criterion: Raises a MAJOR discrepancy that names Alert_Threshold as a business noun that is neither in the domain glossary nor proposed by the design: 1/1 vs 0/1
- `domain-noun-not-in-glossary-major` — Meets criterion: Allows continuation to tasks.md while recommending that the major issue be fixed first: 1/1 vs 0/1
- `cross-context-call-without-row-blocked` — Output includes: CRITICAL-001: 1/1 vs 0/1
- `cross-context-call-without-row-blocked` — Output includes: **Status:** BLOCKED: 1/1 vs 0/1
- `cross-context-call-without-row-blocked` — Meets criterion: Overall status is BLOCKED: 1/1 vs 0/1
- `cross-context-call-without-row-blocked` — Meets criterion: Raises a CRITICAL discrepancy for the call from the evaluator to com.example.billing.InvoiceFlagger, with the missing context-map row between Metering and Billing as its basis: 1/1 vs 0/1
- `cross-context-call-without-row-blocked` — Meets criterion: Does not allow continuation to tasks.md and says what must change before the design is reviewed again: 1/1 vs 0/1

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| domain-noun-not-in-glossary-major | with_skill | 11 | 0 | 11/11 |
| domain-noun-not-in-glossary-major | old_skill | 8 | 3 | 8/11 |
| cross-context-call-without-row-blocked | with_skill | 8 | 0 | 8/8 |
| cross-context-call-without-row-blocked | old_skill | 3 | 5 | 3/8 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-10 | with_skill | 1 | 0 | 0 |
| eval-10 | old_skill | 1 | 0 | 0 |
| eval-11 | with_skill | 1 | 0 | 0 |
| eval-11 | old_skill | 1 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| domain-noun-not-in-glossary-major | Output includes: MAJOR-001 | 1/1 | 0/1 |
| domain-noun-not-in-glossary-major | Output includes: Critical discrepancies: 0 | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Output includes: **Status:** APPROVED WITH ISSUES | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Output does not include: CRITICAL-001 | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Run produced file: design-review-summary.md | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Writes design-review-summary.md for the work item rather than only replying in chat | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Overall status is APPROVED WITH ISSUES, not BLOCKED and not plain APPROVED | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Reports no critical discrepancies | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Raises a MAJOR discrepancy that names Alert_Threshold as a business noun that is neither in the domain glossary nor proposed by the design | 1/1 | 0/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Does not raise a discrepancy against Threshold_Set or Quota_Alert, which the design proposes | 1/1 | 1/1 |
| domain-noun-not-in-glossary-major | Meets criterion: Allows continuation to tasks.md while recommending that the major issue be fixed first | 1/1 | 0/1 |
| cross-context-call-without-row-blocked | Output includes: CRITICAL-001 | 1/1 | 0/1 |
| cross-context-call-without-row-blocked | Output includes: **Status:** BLOCKED | 1/1 | 0/1 |
| cross-context-call-without-row-blocked | Run produced file: design-review-summary.md | 1/1 | 1/1 |
| cross-context-call-without-row-blocked | Meets criterion: Writes design-review-summary.md for the work item rather than only replying in chat | 1/1 | 1/1 |
| cross-context-call-without-row-blocked | Meets criterion: Overall status is BLOCKED | 1/1 | 0/1 |
| cross-context-call-without-row-blocked | Meets criterion: Raises a CRITICAL discrepancy for the call from the evaluator to com.example.billing.InvoiceFlagger, with the missing context-map row between Metering and Billing as its basis | 1/1 | 0/1 |
| cross-context-call-without-row-blocked | Meets criterion: Does not raise a discrepancy against the call to Notification_Service, because the context map has a Notifications to Metering row | 1/1 | 1/1 |
| cross-context-call-without-row-blocked | Meets criterion: Does not allow continuation to tasks.md and says what must change before the design is reviewed again | 1/1 | 0/1 |
