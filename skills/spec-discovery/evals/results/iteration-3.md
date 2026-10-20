# Eval results — iteration-3

**What this iteration tests:** eval fix only: case 2 prompt consistent with the shared fixtures (Usage_Meter, UTC, Billing_Period to date, no plan sub-resource); skill unchanged from iteration 2; baseline = iteration-1 skill

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.303 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 607252 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 373f138dccbe3ca6 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 5829cb6b4f115f7a1dccbe02894482106f4655b0a8fe603816555809a2a371ab |
| bundle_hash | 47fe6b4192dfbfa0a30c3710f9309563fe987e629d2374b892f0099de8cf965d |
| snapshot_hash | 7a5ea362c44794be21d53d0240edb331b02ce4b6368a7a1f56eb6fd5509bc640 |
| input_drift_allowed | none |
| cases_total | 6 |
| cases_included | 6 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.7083 (68/96), B: 0.7708 (74/96) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8389 (±0.1366, n=6) | 33.6434 (±28.0398, n=6) | 202,611 (±86,939, n=6) |
| old_skill | 0.7319 (±0.2903, n=6) | 30.8607 (±20.6833, n=6) | 177,303 (±72,443, n=6) |

**Delta (with_skill − old_skill):** pass_rate +0.1070, time_seconds +2.78, tokens +25,308

*measured over 6 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3864 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `vague-request-asks-one-question` — Meets criterion: Asks exactly one question in the reply: 2/3 vs 3/3
- `approval-sets-status-and-hands-off` — Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/: 1/3 vs 3/3

with_skill scored above baseline:

- `fully-specified-request-writes-discovery` — Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Decisions carry the given values: Monday 08:00 UTC, the current Billing_Period to date with its percentage of the Quota, one digest per account per week, enabled by default: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Edge states carry the given behaviour: zero Usage_Meter, suspended account, retry hourly at most three times: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new: 2/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve.: 1/3 vs 0/3
- `proposes-next-number-across-gaps` — Meets criterion: Proposes a kebab-case name in the form 06-<name>: 3/3 vs 2/3
- `proposes-next-number-across-gaps` — Meets criterion: Asks the user to confirm or change the name and number before creating anything: 3/3 vs 2/3
- `approval-sets-status-and-hands-off` — Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form: 2/3 vs 1/3
- `approval-sets-status-and-hands-off` — Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| vague-request-asks-one-question | with_skill | 13 | 2 | 5/5, 3/5, 5/5 |
| vague-request-asks-one-question | old_skill | 14 | 1 | 5/5, 5/5, 4/5 |
| fully-specified-request-writes-discovery | with_skill | 16 | 8 | 1/8, 8/8, 7/8 |
| fully-specified-request-writes-discovery | old_skill | 3 | 21 | 1/8, 1/8, 1/8 |
| defect-report-routes-to-bugfix | with_skill | 10 | 2 | 4/4, 3/4, 3/4 |
| defect-report-routes-to-bugfix | old_skill | 10 | 2 | 4/4, 3/4, 3/4 |
| proposes-next-number-across-gaps | with_skill | 12 | 0 | 4/4, 4/4, 4/4 |
| proposes-next-number-across-gaps | old_skill | 10 | 2 | 4/4, 4/4, 2/4 |
| approval-sets-status-and-hands-off | with_skill | 12 | 6 | 5/6, 4/6, 3/6 |
| approval-sets-status-and-hands-off | old_skill | 12 | 6 | 3/6, 5/6, 4/6 |
| draft-discovery-offers-resume | with_skill | 15 | 0 | 5/5, 5/5, 5/5 |
| draft-discovery-offers-resume | old_skill | 15 | 0 | 5/5, 5/5, 5/5 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| vague-request-asks-one-question | Meets criterion: Asks exactly one question in the reply | 2/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: The question is about the problem and users, the first in the sequence | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: Options, if offered, are lettered with the recommended one first | 2/3 | 2/3 |
| vague-request-asks-one-question | Meets criterion: Writes no file and creates no directory | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: Does not invoke sdd:spec-generator | 3/3 | 3/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Decisions carry the given values: Monday 08:00 UTC, the current Billing_Period to date with its percentage of the Quota, one digest per account per week, enabled by default | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Edge states carry the given behaviour: zero Usage_Meter, suspended account, retry hourly at most three times | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new | 2/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve. | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes no requirements.md, design.md or tasks.md and does not invoke sdd:spec-generator | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Identifies the request as a defect in existing behaviour rather than a new feature | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Tells the user to run sdd:spec-generator in bugfix mode | 1/3 | 1/3 |
| defect-report-routes-to-bugfix | Meets criterion: Writes no file and creates no directory, including no discovery.md | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Does not run a feature discovery interview | 3/3 | 3/3 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a work item numbered 06, not 03 and not 04 | 3/3 | 3/3 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a kebab-case name in the form 06-<name> | 3/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Asks the user to confirm or change the name and number before creating anything | 3/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Creates no directory and writes no discovery.md in this reply | 3/3 | 3/3 |
| approval-sets-status-and-hands-off | Meets criterion: Treats the reply as explicit approval and asks no further discovery questions | 3/3 | 3/3 |
| approval-sets-status-and-hands-off | Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form | 2/3 | 1/3 |
| approval-sets-status-and-hands-off | Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged | 3/3 | 2/3 |
| approval-sets-status-and-hands-off | Meets criterion: Gives the handoff sentence: Discovery approved. Next: `sdd:spec-generator` for work item `project/work-items/04-usage-digest/`, feature, Requirements-First; it reads `discovery.md` as context for `requirements.md`, or for `design.md` under Design-First. | 0/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/ | 1/3 | 3/3 |
| approval-sets-status-and-hands-off | Meets criterion: Writes no requirements.md, design.md or tasks.md under the spec-discovery skill | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Identifies project/work-items/03-usage-digest/discovery.md as a draft on the same topic | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Does not ask the problem-and-users question or any later discovery question | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Does not propose a new work-item name or the number 04 | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Writes no file, edits no file and creates no directory | 3/3 | 3/3 |
