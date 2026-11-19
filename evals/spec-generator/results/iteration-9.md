# Eval results — iteration-9

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
| judge_tokens | 80007 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | e5d9b060022e5e8b |
| runs_per_arm | 1 |
| parallel | 1 |
| pack_hash | 10b9d1bc86abfa92bade45303dc03c87d1b13308f51cbe2dd6c11929e36575e2 |
| bundle_hash | 8ad43f20b68aa58be2a29a23bb9938c364ce2d784ed6013a7c1a139456999f0e |
| snapshot_hash | 13e242edf1c19157668b2a2d487ddf4ef9582d8b9a70c42019917e0879f65d3c |
| input_drift_allowed | none |
| cases_total | 1 |
| cases_included | 1 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.8 (4/5), B: 1.0 (5/5) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 1.0 (±0.0, n=1) | 66.089 (±0.0, n=1) | 419,920 (±0, n=1) |
| old_skill | 0.8333 (±0.0, n=1) | 74.46 (±0.0, n=1) | 227,277 (±0, n=1) |

**Delta (with_skill − old_skill):** pass_rate +0.1667, time_seconds -8.37, tokens +192,643

*measured over 1 case × 1 run.*

## Regressions and gains

with_skill scored above baseline:

- `requirements-use-domain-glossary` — Meets criterion: Acceptance criteria use Account, Account_Owner and Usage_Meter as subjects and names: 1/1 vs 0/1

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirements-use-domain-glossary | with_skill | 6 | 0 | 6/6 |
| requirements-use-domain-glossary | old_skill | 5 | 1 | 5/6 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-10 | with_skill | 1 | 0 | 0 |
| eval-10 | old_skill | 1 | 0 | 0 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| requirements-use-domain-glossary | Run produced file: project/work-items/04-usage-digest/requirements.md | 1/1 | 1/1 |
| requirements-use-domain-glossary | Meets criterion: Writes requirements.md in project/work-items/04-usage-digest/ beside the existing discovery.md and stops for user review before design work starts | 1/1 | 1/1 |
| requirements-use-domain-glossary | Meets criterion: Acceptance criteria use Account, Account_Owner and Usage_Meter as subjects and names | 1/1 | 0/1 |
| requirements-use-domain-glossary | Meets criterion: Does not use Customer, Usage Counter or Counter as a name for an Account or a Usage_Meter anywhere in the requirements | 1/1 | 1/1 |
| requirements-use-domain-glossary | Meets criterion: The Glossary section does not redefine Account, Account_Owner, Billing_Period, Quota or Usage_Meter with a different meaning | 1/1 | 1/1 |
| requirements-use-domain-glossary | Meets criterion: Carries the discovery decisions: Monday 08:00 in the account's timezone, previous seven days, at most one digest per account per week, enabled by default, retry the next hour at most three times, suspended accounts skipped | 1/1 | 1/1 |
