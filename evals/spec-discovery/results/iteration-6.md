# Eval results — iteration-6

**What this iteration tests:** PR B: domain-model-terms-reused with the prompt no longer naming project/domain, 1 run an arm, the changed skill against the main skill

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
| judge_tokens | 84123 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | c7ec313bbc5bc555 |
| runs_per_arm | 1 |
| parallel | 1 |
| pack_hash | a37457d6b639c91c5c3607cf6f796344da9412f65f4ca813aedc3d19552e0373 |
| bundle_hash | 96f6cbb4adb5844d066f5dead1aa7a1889a0c7d39a9dac8d7043e31da07f9914 |
| snapshot_hash | 4d905584496ced9fb0046677f8f6dd4f374a6689da4fb6e51a84bc4fbd79fd8b |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 1.0 (7/7), B: 0.7143 (5/7) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=1) | 56.774 (±0.0, n=1) | 284,545 (±0, n=1) |
| old_skill | 0.7143 (±0.0, n=1) | 60.149 (±0.0, n=1) | 199,953 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.2857, time_seconds -3.38, tokens +84,592

*measured over 1 case × 1 run.*

## Regressions and gains

with_skill scored above baseline:

- `domain-model-terms-reused` — Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and asks no further question: 1/1 vs 0/1
- `domain-model-terms-reused` — Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve.: 1/1 vs 0/1

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| domain-model-terms-reused | with_skill | 7 | 0 | 7/7 |
| domain-model-terms-reused | old_skill | 5 | 2 | 5/7 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-7 | with_skill | 1 | 0 | 0 |
| eval-7 | old_skill | 1 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| domain-model-terms-reused | Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and asks no further question | 1/1 | 0/1 |
| domain-model-terms-reused | Meets criterion: Terms lists Account_Owner and Usage_Meter as existing, and lists Account_Admin and Usage_Digest as new | 1/1 | 1/1 |
| domain-model-terms-reused | Meets criterion: Does not list Usage_Counter, Usage Counter or Counter as a term of its own, new or existing | 1/1 | 1/1 |
| domain-model-terms-reused | Meets criterion: Names the running count with the glossary term Usage_Meter in Decisions or Edge and failure states | 1/1 | 1/1 |
| domain-model-terms-reused | Meets criterion: The Sources consulted line names project/domain, and the Sources unavailable line does not say that a glossary or domain model is missing | 1/1 | 1/1 |
| domain-model-terms-reused | Meets criterion: Writes no file under project/domain/ | 1/1 | 1/1 |
| domain-model-terms-reused | Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve. | 1/1 | 0/1 |
