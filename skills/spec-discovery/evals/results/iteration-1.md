# Eval results — iteration-1

**What this iteration tests:** spec-discovery build: global CLAUDE.md and brainstorming skill set aside

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
| judge_tokens | 581375 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | ebf6a346a9961393 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | fb588463a57dea26d3f37beabf529e89a4c6e6bd55a6813421c078cfb4ef5856 |
| bundle_hash | 7a5ea362c44794be21d53d0240edb331b02ce4b6368a7a1f56eb6fd5509bc640 |
| input_drift_allowed | none |
| cases_total | 6 |
| cases_included | 6 |
| cases_degraded | none |
| judge_failures | defect-report-routes-to-bugfix: the judge did not return a verdict this harness can read |
| label_pass_rates | A: 0.4565 (42/92), B: 0.5109 (47/92) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.632 (±0.3389, n=6) | 39.981 (±32.614, n=6) | 182,488 (±75,286, n=6) |
| without_skill | 0.4292 (±0.2071, n=6) | 23.2124 (±17.9907, n=6) | 170,280 (±101,189, n=6) |

**Delta (with_skill − without_skill):** pass_rate +0.2028, time_seconds +16.77, tokens +12,208

*measured over 6 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.3118 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `proposes-next-number-across-gaps` — Meets criterion: Proposes a work item numbered 06, not 03 and not 04: 0/3 vs 2/3
- `proposes-next-number-across-gaps` — Meets criterion: Proposes a kebab-case name in the form 06-<name>: 0/3 vs 2/3
- `proposes-next-number-across-gaps` — Meets criterion: Asks the user to confirm or change the name and number before creating anything: 0/3 vs 1/3

with_skill scored above baseline:

- `vague-request-asks-one-question` — Meets criterion: Asks exactly one question in the reply: 3/3 vs 2/3
- `vague-request-asks-one-question` — Meets criterion: The question is about the problem and users, the first in the sequence: 3/3 vs 0/3
- `vague-request-asks-one-question` — Meets criterion: Options, if offered, are lettered with the recommended one first: 3/3 vs 0/3
- `defect-report-routes-to-bugfix` — Meets criterion: Identifies the request as a defect in existing behaviour rather than a new feature: 2/2 vs 0/2
- `proposes-next-number-across-gaps` — Meets criterion: Creates no directory and writes no discovery.md in this reply: 3/3 vs 2/3
- `approval-sets-status-and-hands-off` — Meets criterion: Treats the reply as explicit approval and asks no further discovery questions: 3/3 vs 0/3
- `approval-sets-status-and-hands-off` — Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form: 1/3 vs 0/3
- `approval-sets-status-and-hands-off` — Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged: 2/3 vs 0/3
- `approval-sets-status-and-hands-off` — Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/: 3/3 vs 0/3
- `draft-discovery-offers-resume` — Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply: 3/3 vs 0/3
- `draft-discovery-offers-resume` — Meets criterion: Does not ask the problem-and-users question or any later discovery question: 3/3 vs 1/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| vague-request-asks-one-question | with_skill | 15 | 0 | 5/5, 5/5, 5/5 |
| vague-request-asks-one-question | without_skill | 8 | 7 | 3/5, 2/5, 3/5 |
| fully-specified-request-writes-discovery | with_skill | 3 | 21 | 1/8, 1/8, 1/8 |
| fully-specified-request-writes-discovery | without_skill | 3 | 21 | 1/8, 1/8, 1/8 |
| defect-report-routes-to-bugfix | with_skill | 6 | 2 | 3/4, 3/4 |
| defect-report-routes-to-bugfix | without_skill | 4 | 4 | 2/4, 2/4 |
| proposes-next-number-across-gaps | with_skill | 3 | 9 | 1/4, 1/4, 1/4 |
| proposes-next-number-across-gaps | without_skill | 7 | 5 | 4/4, 1/4, 2/4 |
| approval-sets-status-and-hands-off | with_skill | 12 | 6 | 3/6, 5/6, 4/6 |
| approval-sets-status-and-hands-off | without_skill | 3 | 15 | 1/6, 1/6, 1/6 |
| draft-discovery-offers-resume | with_skill | 15 | 0 | 5/5, 5/5, 5/5 |
| draft-discovery-offers-resume | without_skill | 10 | 5 | 3/5, 3/5, 4/5 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| vague-request-asks-one-question | Meets criterion: Asks exactly one question in the reply | 3/3 | 2/3 |
| vague-request-asks-one-question | Meets criterion: The question is about the problem and users, the first in the sequence | 3/3 | 0/3 |
| vague-request-asks-one-question | Meets criterion: Options, if offered, are lettered with the recommended one first | 3/3 | 0/3 |
| vague-request-asks-one-question | Meets criterion: Writes no file and creates no directory | 3/3 | 3/3 |
| vague-request-asks-one-question | Meets criterion: Does not invoke sdd:spec-generator | 3/3 | 3/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes project/work-items/03-usage-digest/discovery.md and treats the given name and number as confirmed | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: The header carries Status: Draft, spec type feature and workflow Requirements-First | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Includes Problem, Users and outcomes, Scope, Approach, Decisions, Constraints, Edge and failure states, Terms and Open questions sections | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Decisions carry the given values: Monday 08:00 account timezone, previous seven days, one digest per account per week, enabled by default, zero-usage meters omitted | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Edge states carry the given behaviour: no usage, suspended account, retry hourly at most three times | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Terms lists Account_Owner and Account_Admin, marking each as existing or new | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Stops with the message: `discovery.md` is written to `project/work-items/03-usage-digest/discovery.md`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve. | 0/3 | 0/3 |
| fully-specified-request-writes-discovery | Meets criterion: Writes no requirements.md, design.md or tasks.md and does not invoke sdd:spec-generator | 3/3 | 3/3 |
| defect-report-routes-to-bugfix | Meets criterion: Identifies the request as a defect in existing behaviour rather than a new feature | 2/2 | 0/2 |
| defect-report-routes-to-bugfix | Meets criterion: Tells the user to run sdd:spec-generator in bugfix mode | 0/2 | 0/2 |
| defect-report-routes-to-bugfix | Meets criterion: Writes no file and creates no directory, including no discovery.md | 2/2 | 2/2 |
| defect-report-routes-to-bugfix | Meets criterion: Does not run a feature discovery interview | 2/2 | 2/2 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a work item numbered 06, not 03 and not 04 | 0/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Proposes a kebab-case name in the form 06-<name> | 0/3 | 2/3 |
| proposes-next-number-across-gaps | Meets criterion: Asks the user to confirm or change the name and number before creating anything | 0/3 | 1/3 |
| proposes-next-number-across-gaps | Meets criterion: Creates no directory and writes no discovery.md in this reply | 3/3 | 2/3 |
| approval-sets-status-and-hands-off | Meets criterion: Treats the reply as explicit approval and asks no further discovery questions | 3/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Sets the Status line of discovery.md to Approved followed by a date in YYYY-MM-DD form | 1/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Leaves the Problem, Scope, Decisions, Constraints and Edge and failure states sections unchanged | 2/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Gives the handoff sentence: Discovery approved. Next: `sdd:spec-generator` for work item `project/work-items/04-usage-digest/`, feature, Requirements-First; it reads `discovery.md` as context for `requirements.md`, or for `design.md` under Design-First. | 0/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Names sdd:spec-generator as the next skill and passes the work-item path project/work-items/04-usage-digest/ | 3/3 | 0/3 |
| approval-sets-status-and-hands-off | Meets criterion: Writes no requirements.md, design.md or tasks.md under the spec-discovery skill | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Identifies project/work-items/03-usage-digest/discovery.md as a draft on the same topic | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Asks whether to (a) resume that draft or (b) start afresh, as the only question in the reply | 3/3 | 0/3 |
| draft-discovery-offers-resume | Meets criterion: Does not ask the problem-and-users question or any later discovery question | 3/3 | 1/3 |
| draft-discovery-offers-resume | Meets criterion: Does not propose a new work-item name or the number 04 | 3/3 | 3/3 |
| draft-discovery-offers-resume | Meets criterion: Writes no file, edits no file and creates no directory | 3/3 | 3/3 |
