# Eval results — iteration-1

**What this iteration tests:** iteration 1: first three-case pack, skill after EARS-subject fix, missing-context fallbacks and shell-less work-item scan; global CLAUDE.md set aside

## Provenance

| Field | Value |
| --- | --- |
| harness_version | 0.1.0 |
| adapter | claude_code |
| runtime_version | 2.1.263 (Claude Code) |
| runner_model | claude-sonnet-5 |
| runner_model_canonical | claude-sonnet-5 |
| runner_model_requested | claude-sonnet-5 |
| judge_model | claude-opus-5 |
| judge_model_requested | claude-opus-5 |
| judge_tokens | 227628 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | f0e6b9c267559602 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | a47506cf4bc10d6c1f278a97547e882a78eb65c641ee9ce035cc7eb67cce2f2e |
| bundle_hash | 128a438070d4c27bcd760bf12d86a4786025a089aa79668f90e9a2b71a3507db |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.4444 (4/9), B: 0.4444 (4/9) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7778 (±0.3143, n=3) | 104.1317 (±69.3519, n=3) | 272,164 (±110,125, n=3) |
| without_skill | 0.1111 (±0.1571, n=3) | 72.4312 (±43.4685, n=3) | 141,671 (±39,680, n=3) |

**Delta (with_skill − without_skill):** pass_rate +0.6667, time_seconds +31.70, tokens +130,493

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4714 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored above baseline:

- `requirements-from-brainstorm` — Meets the expected outcome: The reply should produce a requirements document for quota alerts as the first document of the spec, in a numbered work-item directory with the brainstorm copied in beside it, and stop there asking me to review it before any design work starts. The document should open with an introduction and a glossary, then state each requirement as a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL) whose subjects are glossary terms such as Account_Owner, Alert_Threshold and Notification_Service. It should carry the brainstorm's decisions through as requirements: default thresholds of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period, evaluation on the ingest increment with the fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts, and the new quota-threshold notification template. It should also cover reading and replacing the threshold set through the API, following the existing plan sub-resource, with the error cases for an invalid list.: 3/3 vs 0/3
- `tasks-behind-blocked-review` — Meets the expected outcome: The reply should hold off on tasks.md because the design review came back BLOCKED, say plainly that no task list gets generated behind a blocked review, and set out the two critical findings in its own words: the export has no per-event data source, and the endpoint cannot select or validate a billing period. It should put the first decision to me, whether requirement 3 or the design gives way, propose the concrete design.md revisions for the second, fold in the two major findings on the http4k handler pattern and the suspended-account contradiction while it is there, and say the design review is re-run before tasks are generated.: 3/3 vs 1/3
- `bugfix-spec-from-issue` — Meets the expected outcome: The reply should produce a bugfix requirements document as the first document of the spec, in a directory named for work item 08 with a -bugfix suffix, and stop for my review before any design. The document should open with an introduction saying where the bug occurs and what it costs customers, then analyse it in three numbered sections: the current behaviour, covering the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case; the expected behaviour, that a plan change shows in the very next usage read; and the behaviour that must stay the same, that the quota is still cached for reads with no plan change in between, that ingest keeps recording during any window, and that the plan-changed notification keeps firing. Each line should be a WHEN ... THEN the system SHALL statement, using SHALL CONTINUE TO in the third section, numbered 1.x, 2.x and 3.x.: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirements-from-brainstorm | with_skill | 3 | 0 | 1/1, 1/1, 1/1 |
| requirements-from-brainstorm | without_skill | 0 | 3 | 0/1, 0/1, 0/1 |
| tasks-behind-blocked-review | with_skill | 3 | 0 | 1/1, 1/1, 1/1 |
| tasks-behind-blocked-review | without_skill | 1 | 2 | 0/1, 1/1, 0/1 |
| bugfix-spec-from-issue | with_skill | 1 | 2 | 1/1, 0/1, 0/1 |
| bugfix-spec-from-issue | without_skill | 0 | 3 | 0/1, 0/1, 0/1 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| requirements-from-brainstorm | Meets the expected outcome: The reply should produce a requirements document for quota alerts as the first document of the spec, in a numbered work-item directory with the brainstorm copied in beside it, and stop there asking me to review it before any design work starts. The document should open with an introduction and a glossary, then state each requirement as a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL) whose subjects are glossary terms such as Account_Owner, Alert_Threshold and Notification_Service. It should carry the brainstorm's decisions through as requirements: default thresholds of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period, evaluation on the ingest increment with the fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts, and the new quota-threshold notification template. It should also cover reading and replacing the threshold set through the API, following the existing plan sub-resource, with the error cases for an invalid list. | 3/3 | 0/3 |
| tasks-behind-blocked-review | Meets the expected outcome: The reply should hold off on tasks.md because the design review came back BLOCKED, say plainly that no task list gets generated behind a blocked review, and set out the two critical findings in its own words: the export has no per-event data source, and the endpoint cannot select or validate a billing period. It should put the first decision to me, whether requirement 3 or the design gives way, propose the concrete design.md revisions for the second, fold in the two major findings on the http4k handler pattern and the suspended-account contradiction while it is there, and say the design review is re-run before tasks are generated. | 3/3 | 1/3 |
| bugfix-spec-from-issue | Meets the expected outcome: The reply should produce a bugfix requirements document as the first document of the spec, in a directory named for work item 08 with a -bugfix suffix, and stop for my review before any design. The document should open with an introduction saying where the bug occurs and what it costs customers, then analyse it in three numbered sections: the current behaviour, covering the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case; the expected behaviour, that a plan change shows in the very next usage read; and the behaviour that must stay the same, that the quota is still cached for reads with no plan change in between, that ingest keeps recording during any window, and that the plan-changed notification keeps firing. Each line should be a WHEN ... THEN the system SHALL statement, using SHALL CONTINUE TO in the third section, numbered 1.x, 2.x and 3.x. | 1/3 | 0/3 |
