# Eval results — iteration-2

**What this iteration tests:** iteration 2: pack re-baselined with criteria on all three cases, corrected bugfix EARS clause and scoped must_not_include on the bugfix case; measures the v0.1.1 bugfix gate fix (no design review for bugfixes); global CLAUDE.md set aside

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
| judge_tokens | 223238 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 38c69dc1a5de8941 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 4eb93555393df108d993afd52ff3304e28ba8c9a24f35cb8ab1bc2963832ad50 |
| bundle_hash | 2c1eb2d4d6a46c159f55e2f894653da1e26605a7535ebe560df53805d248aea0 |
| input_drift_allowed | none |
| cases_total | 3 |
| cases_included | 3 |
| cases_degraded | none |
| judge_failures | none |
| label_pass_rates | A: 0.6825 (43/63), B: 0.4286 (27/63) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.8133 (±0.0845, n=3) | 86.6552 (±48.4554, n=3) | 271,244 (±104,238, n=3) |
| without_skill | 0.3565 (±0.3208, n=3) | 61.1883 (±22.8263, n=3) | 128,397 (±22,543, n=3) |

**Delta (with_skill − without_skill):** pass_rate +0.4568, time_seconds +25.47, tokens +142,846

*measured over 3 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2125 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `tasks-behind-blocked-review` — Meets criterion: Proposes concrete design.md revisions for the billing-period finding: 0/3 vs 1/3

with_skill scored above baseline:

- `requirements-from-brainstorm` — Meets criterion: Writes requirements.md inside a numbered work-item directory with the brainstorm copied in beside it: 1/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: The document opens with an introduction and a glossary: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL): 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system': 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Carries the brainstorm's threshold decisions: defaults of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Carries the brainstorm's evaluation decisions: evaluation on the ingest increment with fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Requires the new quota-threshold notification template: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Covers reading and replacing the threshold set through the API, following the existing plan sub-resource, with error cases for an invalid list: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Writes a bugfix requirements document in a directory named for work item 08 with a -bugfix suffix: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced: 2/3 vs 1/3
- `bugfix-spec-from-issue` — Meets criterion: Opens with an introduction saying where the bug occurs and what it costs customers: 3/3 vs 2/3
- `bugfix-spec-from-issue` — Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirements-from-brainstorm | with_skill | 25 | 2 | 8/9, 9/9, 8/9 |
| requirements-from-brainstorm | without_skill | 0 | 27 | 0/9, 0/9, 0/9 |
| tasks-behind-blocked-review | with_skill | 13 | 5 | 4/6, 4/6, 5/6 |
| tasks-behind-blocked-review | without_skill | 14 | 4 | 5/6, 4/6, 5/6 |
| bugfix-spec-from-issue | with_skill | 19 | 5 | 4/8, 7/8, 8/8 |
| bugfix-spec-from-issue | without_skill | 7 | 17 | 4/8, 3/8, 0/8 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| requirements-from-brainstorm | Meets criterion: Writes requirements.md inside a numbered work-item directory with the brainstorm copied in beside it | 1/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: The document opens with an introduction and a glossary | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL) | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system' | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Carries the brainstorm's threshold decisions: defaults of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Carries the brainstorm's evaluation decisions: evaluation on the ingest increment with fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Requires the new quota-threshold notification template | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Covers reading and replacing the threshold set through the API, following the existing plan sub-resource, with error cases for an invalid list | 3/3 | 0/3 |
| tasks-behind-blocked-review | Meets criterion: Does not generate tasks.md and says plainly that no task list is generated behind a BLOCKED design review | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Sets out both critical findings in its own words: no per-event data source for the export, and no way to select or validate a billing period | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Puts the first decision to the user: whether requirement 3 or the design gives way | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Proposes concrete design.md revisions for the billing-period finding | 0/3 | 1/3 |
| tasks-behind-blocked-review | Meets criterion: Folds in the two major findings: the http4k handler pattern and the suspended-account contradiction | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Says the design review is re-run before tasks are generated | 1/3 | 1/3 |
| bugfix-spec-from-issue | Output does not include: Hypothesized Root Cause | 2/3 | 2/3 |
| bugfix-spec-from-issue | Output does not include: # Implementation Plan | 2/3 | 2/3 |
| bugfix-spec-from-issue | Meets criterion: Writes a bugfix requirements document in a directory named for work item 08 with a -bugfix suffix | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced | 2/3 | 1/3 |
| bugfix-spec-from-issue | Meets criterion: Opens with an introduction saying where the bug occurs and what it costs customers | 3/3 | 2/3 |
| bugfix-spec-from-issue | Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing | 1/3 | 0/3 |
