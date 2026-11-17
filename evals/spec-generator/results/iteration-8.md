# Eval results — iteration-8

**What this iteration tests:** #15 gate: case 7 (design-first-feature) only, rewritten in #66 so one turn can answer it; same arms and models as iteration 3

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.4.0 |
| adapter | claude_code |
| runtime_version | 2.1.325 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 339716 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 0587891318c2e880 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 18f78e36711b7825ba8a352519c53283e578024d73e244fc7ed54b18f88a673b |
| bundle_hash | 13e242edf1c19157668b2a2d487ddf4ef9582d8b9a70c42019917e0879f65d3c |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.75 (9/12), B: 0.3333 (4/12) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.75 (±0.0, n=1) | 174.337 (±0.0, n=1) | 630,915 (±0, n=1) |
| without_skill | 0.3333 (±0.0, n=1) | 150.709 (±0.0, n=1) | 417,956 (±0, n=1) |

**Delta (with_skill − without_skill):** pass_rate +0.4167, time_seconds +23.63, tokens +212,958

*measured over 1 case × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2357 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `design-first-feature` — Meets criterion: Creates only design.md first in a new sequentially numbered work-item directory; no requirements.md is generated; the outputs/ copy at the same path is expected and is not an extra directory.: 3/3 vs 0/3
- `design-first-feature` — Meets criterion: Treats webhook-contract.md as authoritative, preserving payload fields, 204 success, 400 malformed-input behavior, retries, and event_id idempotency.: 3/3 vs 2/3
- `design-first-feature` — Meets criterion: Aligns the design with the supplied architecture and OpenAPI context and is implementation-ready.: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| design-first-feature | with_skill | 9 | 3 | 3/4, 3/4, 3/4 |
| design-first-feature | without_skill | 4 | 8 | 0/4, 2/4, 2/4 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-7 | with_skill | 3 | 0 | 0 |
| eval-7 | without_skill | 0 | 3 | 0 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| design-first-feature | Meets criterion: Creates only design.md first in a new sequentially numbered work-item directory; no requirements.md is generated; the outputs/ copy at the same path is expected and is not an extra directory. | 3/3 | 0/3 |
| design-first-feature | Meets criterion: Treats webhook-contract.md as authoritative, preserving payload fields, 204 success, 400 malformed-input behavior, retries, and event_id idempotency. | 3/3 | 2/3 |
| design-first-feature | Meets criterion: Aligns the design with the supplied architecture and OpenAPI context and is implementation-ready. | 3/3 | 2/3 |
| design-first-feature | Meets criterion: Stops for user review and explains that requirements.md follows design approval and sdd:spec-design-review runs once both documents exist. | 0/3 | 0/3 |
