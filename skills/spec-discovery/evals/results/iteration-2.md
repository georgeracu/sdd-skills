# Eval results — iteration-2

**What this iteration tests:** step 4: skip questions when the request answers them all, open variables to Open questions; baseline = iteration-1 skill; eval fixes: case 4 stages only the work-item tree, case 2 criterion

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
| judge_tokens | 583018 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 2315140a040ec611 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 7ed288781debf753ed926663dce98d53ea9f54ee832e48670446293d80e670d1 |
| bundle_hash | 47fe6b4192dfbfa0a30c3710f9309563fe987e629d2374b892f0099de8cf965d |
| snapshot_hash | 7a5ea362c44794be21d53d0240edb331b02ce4b6368a7a1f56eb6fd5509bc640 |
| input_drift_allowed | none |
| cases_total | 6 |
| cases_included | 6 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6979 (67/96), B: 0.6667 (64/96) |
| judge_adapter | claude_code |

## Summary

_old_skill is the snapshot taken before the last accepted diff._

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7722 (±0.1936, n=6) | 34.497 (±26.2622, n=6) | 172,133 (±72,977, n=6) |
| old_skill | 0.6995 (±0.2933, n=6) | 34.2418 (±22.2301, n=6) | 172,079 (±70,114, n=6) |

**Delta (with_skill − old_skill):** pass_rate +0.0727, time_seconds +0.26, tokens +54

*measured over 6 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4125 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `draft-discovery-offers-resume` — Meets criterion: Identifies project/work-items/03-usage-digest/discovery.md as a draft on the same topic: 2/3 vs 3/3
- `draft-discovery-offers-resume` — Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply: 2/3 vs 3/3

with_skill scored above baseline:

- `fully-specified-request-writes-discovery` — Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Decisions carry the given values: Monday 08:00 account timezone, previous seven days, one digest per account per week, enabled by default, zero-usage meters omitted: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Edge states carry the given behaviour: no usage, suspended account, retry hourly at most three times: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new: 1/3 vs 0/3
- `fully-specified-request-writes-discovery` — Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve.: 1/3 vs 0/3
- `proposes-next-number-across-gaps` — Meets criterion: Proposes a kebab-case name in the form 06-<name>: 3/3 vs 2/3
- `proposes-next-number-across-gaps` — Meets criterion: Asks the user to confirm or change the name and number before creating anything: 3/3 vs 2/3
- `approval-sets-status-and-hands-off` — Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form: 1/3 vs 0/3
- `approval-sets-status-and-hands-off` — Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| vague-request-asks-one-question | with_skill | 14 | 1 | 5/5, 5/5, 4/5 |
| vague-request-asks-one-question | old_skill | 14 | 1 | 5/5, 5/5, 4/5 |
| fully-specified-request-writes-discovery | with_skill | 10 | 14 | 1/8, 1/8, 8/8 |
| fully-specified-request-writes-discovery | old_skill | 3 | 21 | 1/8, 1/8, 1/8 |
| defect-report-routes-to-bugfix | with_skill | 9 | 3 | 3/4, 3/4, 3/4 |
| defect-report-routes-to-bugfix | old_skill | 9 | 3 | 3/4, 3/4, 3/4 |
| proposes-next-number-across-gaps | with_skill | 12 | 0 | 4/4, 4/4, 4/4 |
| proposes-next-number-across-gaps | old_skill | 10 | 2 | 2/4, 4/4, 4/4 |
| approval-sets-status-and-hands-off | with_skill | 12 | 6 | 5/6, 4/6, 3/6 |
| approval-sets-status-and-hands-off | old_skill | 10 | 8 | 4/6, 4/6, 2/6 |
| draft-discovery-offers-resume | with_skill | 13 | 2 | 3/5, 5/5, 5/5 |
| draft-discovery-offers-resume | old_skill | 15 | 0 | 5/5, 5/5, 5/5 |

## Per check

| Case | Check | with_skill | old_skill |
| --- | --- | --- | --- |
| vague-request-asks-one-question | Meets criterion: Asks exactly one question in the reply | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: The question is about the problem and users, the first in the sequence | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: Options, if offered, are lettered with the recommended one first | 2/3 | 2/3 |
| vague-request-asks-one-question | Meets criterion: Writes no file and creates no directory | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: Does not invoke sdd:spec-generator | 3/3 | 3/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed, asking no further question; any variable the request leaves open goes under Open questions with a recommended answer | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Decisions carry the given values: Monday 08:00 account timezone, previous seven days, one digest per account per week, enabled by default, zero-usage meters omitted | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Edge states carry the given behaviour: no usage, suspended account, retry hourly at most three times | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve. | 1/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes no requirements.md, design.md or tasks.md and does not invoke sdd:spec-generator | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Identifies the request as a defect in existing behaviour rather than a new feature | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Tells the user to run sdd:spec-generator in bugfix mode | 0/3 | 0/3 |
| defect-report-routes-to-bugfix | Meets criterion: Writes no file and creates no directory, including no discovery.md | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Does not run a feature discovery interview | 3/3 | 3/3 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a work item numbered 06, not 03 and not 04 | 3/3 | 3/3 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a kebab-case name in the form 06-<name> | 3/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Asks the user to confirm or change the name and number before creating anything | 3/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Creates no directory and writes no discovery.md in this reply | 3/3 | 3/3 |
| approval-sets-status-and-hands-off | Meets criterion: Treats the reply as explicit approval and asks no further discovery questions | 3/3 | 3/3 |
| approval-sets-status-and-hands-off | Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form | 1/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged | 2/3 | 2/3 |
| approval-sets-status-and-hands-off | Meets criterion: Gives the handoff sentence: Discovery approved. Next: `sdd:spec-generator` for work item `project/work-items/04-usage-digest/`, feature, Requirements-First; it reads `discovery.md` as context for `requirements.md`, or for `design.md` under Design-First. | 0/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/ | 3/3 | 2/3 |
| approval-sets-status-and-hands-off | Meets criterion: Writes no requirements.md, design.md or tasks.md under the spec-discovery skill | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Identifies project/work-items/03-usage-digest/discovery.md as a draft on the same topic | 2/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply | 2/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Does not ask the problem-and-users question or any later discovery question | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Does not propose a new work-item name or the number 04 | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Writes no file, edits no file and creates no directory | 3/3 | 3/3 |
