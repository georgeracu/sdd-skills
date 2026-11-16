# Eval results — iteration-4

**What this iteration tests:** skill-eval 0.4.0 in use: per-arm skill activation; every case prompt names the active bet so the user-level bet check does not stop the run; baseline = no skill

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.4.0 |
| adapter | claude_code |
| runtime_version | 2.1.325 (Claude Code) |
| runner_model | claude-sonnet-5-5 |
| runner_model_canonical | claude-sonnet-5-5 |
| runner_model_requested | claude-sonnet-5-5 |
| judge_model | claude-opus-5-5 |
| judge_model_requested | claude-opus-5-5 |
| judge_tokens | 365565 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 3a81a38d97a7df85 |
| runs_per_arm | 1 |
| parallel | 1 |
| pack_hash | ebd2422e0269e0ab25f12c623e98aa3e9ba5183819af622e8f26d1326d0888c9 |
| bundle_hash | 4d905584496ced9fb0046677f8f6dd4f374a6689da4fb6e51a84bc4fbd79fd8b |
| snapshot_hash | 7a5ea362c44794be21d53d0240edb331b02ce4b6368a7a1f56eb6fd5509bc640 |
| input_drift_allowed | none |
| cases_total | 6 |
| cases_included | 6 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7812 (25/32), B: 0.5312 (17/32) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.9722 (±0.0621, n=6) | 22.7 (±16.5135, n=6) | 182,556 (±68,102, n=6) |
| without_skill | 0.3625 (±0.1352, n=6) | 28.9127 (±10.0129, n=6) | 173,017 (±40,134, n=6) |

**Delta (with_skill − without_skill):** pass_rate +0.6097, time_seconds -6.21, tokens +9,540

*measured over 6 cases × 1 run.*

## Regressions and gains

with_skill scored above baseline:

- `vague-request-asks-one-question` — Meets criterion: Asks exactly one question in the reply: 1/1 vs 0/1
- `vague-request-asks-one-question` — Meets criterion: Options, if offered, are lettered with the recommended one first: 1/1 vs 0/1
- `vague-request-asks-one-question` — Meets criterion: Writes no file and creates no directory: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Decisions carry the given values: Monday 08:00 UTC, the current Billing_Period to date with its percentage of the Quota, one digest per account per week, enabled by default: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Edge states carry the given behaviour: zero Usage_Meter, suspended account, retry hourly at most three times: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve.: 1/1 vs 0/1
- `fully-specified-request-writes-discovery` — Meets criterion: Writes no requirements.md, design.md or tasks.md and does not invoke sdd:spec-generator: 1/1 vs 0/1
- `defect-report-routes-to-bugfix` — Meets criterion: Tells the user to run sdd:spec-generator in bugfix mode: 1/1 vs 0/1
- `defect-report-routes-to-bugfix` — Meets criterion: Writes no file and creates no directory, including no discovery.md: 1/1 vs 0/1
- `defect-report-routes-to-bugfix` — Meets criterion: Does not run a feature discovery interview: 1/1 vs 0/1
- `proposes-next-number-across-gaps` — Meets criterion: Asks the user to confirm or change the name and number before creating anything: 1/1 vs 0/1
- `proposes-next-number-across-gaps` — Meets criterion: Creates no directory and writes no discovery.md in this reply: 1/1 vs 0/1
- `approval-sets-status-and-hands-off` — Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form: 1/1 vs 0/1
- `approval-sets-status-and-hands-off` — Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/: 1/1 vs 0/1
- `draft-discovery-offers-resume` — Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply: 1/1 vs 0/1
- `draft-discovery-offers-resume` — Meets criterion: Does not ask the problem-and-users question or any later discovery question: 1/1 vs 0/1
- `draft-discovery-offers-resume` — Meets criterion: Writes no file, edits no file and creates no directory: 1/1 vs 0/1

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| vague-request-asks-one-question | with_skill | 5 | 0 | 5/5 |
| vague-request-asks-one-question | without_skill | 2 | 3 | 2/5 |
| fully-specified-request-writes-discovery | with_skill | 8 | 0 | 8/8 |
| fully-specified-request-writes-discovery | without_skill | 1 | 7 | 1/8 |
| defect-report-routes-to-bugfix | with_skill | 4 | 0 | 4/4 |
| defect-report-routes-to-bugfix | without_skill | 1 | 3 | 1/4 |
| proposes-next-number-across-gaps | with_skill | 4 | 0 | 4/4 |
| proposes-next-number-across-gaps | without_skill | 2 | 2 | 2/4 |
| approval-sets-status-and-hands-off | with_skill | 5 | 1 | 5/6 |
| approval-sets-status-and-hands-off | without_skill | 3 | 3 | 3/6 |
| draft-discovery-offers-resume | with_skill | 5 | 0 | 5/5 |
| draft-discovery-offers-resume | without_skill | 2 | 3 | 2/5 |

## Skill activation

| Case | Configuration | Loaded | Not loaded | Unknown |
| --- | --- | --- | --- | --- |
| eval-1 | with_skill | 1 | 0 | 0 |
| eval-1 | without_skill | 0 | 1 | 0 |
| eval-2 | with_skill | 1 | 0 | 0 |
| eval-2 | without_skill | 0 | 1 | 0 |
| eval-3 | with_skill | 1 | 0 | 0 |
| eval-3 | without_skill | 0 | 1 | 0 |
| eval-4 | with_skill | 1 | 0 | 0 |
| eval-4 | without_skill | 0 | 1 | 0 |
| eval-5 | with_skill | 1 | 0 | 0 |
| eval-5 | without_skill | 0 | 1 | 0 |
| eval-6 | with_skill | 1 | 0 | 0 |
| eval-6 | without_skill | 0 | 1 | 0 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| vague-request-asks-one-question | Meets criterion: Asks exactly one question in the reply | 1/1 | 0/1 |
| vague-request-asks-one-question | Meets criterion: The question is about the problem and users, the first in the sequence | 1/1 | 1/1 |
| vague-request-asks-one-question | Meets criterion: Options, if offered, are lettered with the recommended one first | 1/1 | 0/1 |
| vague-request-asks-one-question | Meets criterion: Writes no file and creates no directory | 1/1 | 0/1 |
| vague-request-asks-one-question | Meets criterion: Does not invoke sdd:spec-generator | 1/1 | 1/1 |
| fully-specified-request-writes-discovery | Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: Decisions carry the given values: Monday 08:00 UTC, the current Billing_Period to date with its percentage of the Quota, one digest per account per week, enabled by default | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: Edge states carry the given behaviour: zero Usage_Meter, suspended account, retry hourly at most three times | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new | 1/1 | 1/1 |
| fully-specified-request-writes-discovery | Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve. | 1/1 | 0/1 |
| fully-specified-request-writes-discovery | Meets criterion: Writes no requirements.md, design.md or tasks.md and does not invoke sdd:spec-generator | 1/1 | 0/1 |
| defect-report-routes-to-bugfix | Meets criterion: Identifies the request as a defect in existing behaviour rather than a new feature | 1/1 | 1/1 |
| defect-report-routes-to-bugfix | Meets criterion: Tells the user to run sdd:spec-generator in bugfix mode | 1/1 | 0/1 |
| defect-report-routes-to-bugfix | Meets criterion: Writes no file and creates no directory, including no discovery.md | 1/1 | 0/1 |
| defect-report-routes-to-bugfix | Meets criterion: Does not run a feature discovery interview | 1/1 | 0/1 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a work item numbered 06, not 03 and not 04 | 1/1 | 1/1 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a kebab-case name in the form 06-<name> | 1/1 | 1/1 |
| proposes-next-number-across-gaps | Meets criterion: Asks the user to confirm or change the name and number before creating anything | 1/1 | 0/1 |
| proposes-next-number-across-gaps | Meets criterion: Creates no directory and writes no discovery.md in this reply | 1/1 | 0/1 |
| approval-sets-status-and-hands-off | Meets criterion: Treats the reply as explicit approval and asks no further discovery questions | 1/1 | 1/1 |
| approval-sets-status-and-hands-off | Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form | 1/1 | 0/1 |
| approval-sets-status-and-hands-off | Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged | 1/1 | 1/1 |
| approval-sets-status-and-hands-off | Meets criterion: Gives the handoff sentence: Discovery approved. Next: `sdd:spec-generator` for work item `project/work-items/04-usage-digest/`, feature, Requirements-First; it reads `discovery.md` as context for `requirements.md`, or for `design.md` under Design-First. | 0/1 | 0/1 |
| approval-sets-status-and-hands-off | Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/ | 1/1 | 0/1 |
| approval-sets-status-and-hands-off | Meets criterion: Writes no requirements.md, design.md or tasks.md under the spec-discovery skill | 1/1 | 1/1 |
| draft-discovery-offers-resume | Meets criterion: Identifies project/work-items/03-usage-digest/discovery.md as a draft on the same topic | 1/1 | 1/1 |
| draft-discovery-offers-resume | Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply | 1/1 | 0/1 |
| draft-discovery-offers-resume | Meets criterion: Does not ask the problem-and-users question or any later discovery question | 1/1 | 0/1 |
| draft-discovery-offers-resume | Meets criterion: Does not propose a new work-item name or the number 04 | 1/1 | 1/1 |
| draft-discovery-offers-resume | Meets criterion: Writes no file, edits no file and creates no directory | 1/1 | 0/1 |
