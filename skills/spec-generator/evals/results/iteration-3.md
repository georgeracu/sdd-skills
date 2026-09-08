# Eval results — iteration-3

**What this iteration tests:** iteration 3: full 9-case pack; cases 4-9 measured for the first time; global CLAUDE.md set aside

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
| judge_tokens | 853919 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | 43204378f669ced3 |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | 3550ec7c80772aef5569bf728214ebe669f6dbfcdab07735ae7632db86196490 |
| bundle_hash | c492f17db3b0ca6d8b7c742ea55e2f134a1564680c15ee38427011609036d614 |
| input_drift_allowed | none |
| cases_total | 9 |
| cases_included | 9 |
| cases_degraded | none |
| judge_failures | tasks-from-approved-design: the judge did not return a verdict this harness can read |
| label_pass_rates | A: 0.662 (94/142), B: 0.5563 (79/142) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.775 (±0.2992, n=9) | 85.2227 (±42.8966, n=9) | 287,912 (±110,004, n=9) |
| without_skill | 0.418 (±0.2533, n=9) | 68.0601 (±34.3099, n=9) | 152,608 (±34,224, n=9) |

**Delta (with_skill − without_skill):** pass_rate +0.3570, time_seconds +17.16, tokens +135,305

*measured over 9 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.2572 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `tasks-behind-blocked-review` — Meets criterion: Proposes concrete design.md revisions for the billing-period finding: 0/3 vs 1/3

with_skill scored above baseline:

- `requirements-from-brainstorm` — Run produced file: 01-quota-alerts/requirements.md: 3/3 vs 0/3
- `requirements-from-brainstorm` — Run produced file: 01-quota-alerts/brainstorm.md: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Writes requirements.md inside a numbered work-item directory with the brainstorm copied in beside it: 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced: 3/3 vs 1/3
- `requirements-from-brainstorm` — Meets criterion: The document opens with an introduction and a glossary: 3/3 vs 1/3
- `requirements-from-brainstorm` — Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL): 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system': 3/3 vs 0/3
- `requirements-from-brainstorm` — Meets criterion: Carries the brainstorm's threshold decisions: defaults of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period: 3/3 vs 1/3
- `requirements-from-brainstorm` — Meets criterion: Carries the brainstorm's evaluation decisions: evaluation on the ingest increment with fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts: 3/3 vs 1/3
- `requirements-from-brainstorm` — Meets criterion: Requires the new quota-threshold notification template: 3/3 vs 1/3
- `requirements-from-brainstorm` — Meets criterion: Covers reading and replacing the threshold set through the API, following the existing plan sub-resource, with error cases for an invalid list: 3/3 vs 1/3
- `bugfix-spec-from-issue` — Meets criterion: Writes a bugfix requirements document in a directory named for work item 08 with a -bugfix suffix: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced: 2/3 vs 1/3
- `bugfix-spec-from-issue` — Meets criterion: Opens with an introduction saying where the bug occurs and what it costs customers: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing: 3/3 vs 0/3
- `tasks-from-approved-design` — Meets criterion: Tasks are dependency-ordered with the OpenAPI update and scripts/generate-api-types.sh before implementation, and implementation tasks include build/test subtasks.: 2/2 vs 1/2
- `tasks-from-approved-design` — Meets criterion: The ending tasks include the API contract/type regeneration requirement, E2E tests under e2e/, knowledge-base/documentation updates, and final full validation.: 2/2 vs 0/2
- `tasks-from-approved-design` — Meets criterion: The assistant stops and asks for review without invoking sdd:spec-task-review.: 2/2 vs 0/2
- `design-from-approved-requirements` — Meets criterion: Includes the applicable design-document-sections.md sections, with named components and explicit requirement tracing.: 3/3 vs 2/3
- `design-from-approved-requirements` — Meets criterion: Covers correctness properties, error handling, and unit, integration, and E2E testing.: 3/3 vs 0/3
- `design-from-approved-requirements` — Meets criterion: Does not invoke sdd:spec-design-review; explains it will run after design approval.: 1/3 vs 0/3
- `bugfix-design-from-approved-analysis` — Meets criterion: Defines Bug_Condition, Property, and Preservation glossary terms and formally states the fault condition.: 3/3 vs 0/3
- `bugfix-design-from-approved-analysis` — Meets criterion: Identifies the one-hour Quota_Cache TTL and absent plan-change invalidation as the root cause, naming QuotaService.kt and its affected functions.: 3/3 vs 2/3
- `bugfix-design-from-approved-analysis` — Meets criterion: Testing strategy covers exploratory reproduction, fix verification, and preservation/regression checks, without invoking a review skill.: 3/3 vs 0/3
- `brainstorm-handoff-with-existing-work-items` — Meets criterion: Scans existing work-item numbers numerically and creates 06-invoice-reminders, not a count-based or 03 directory.: 3/3 vs 2/3
- `brainstorm-handoff-with-existing-work-items` — Meets criterion: Copies the selected brainstorm as brainstorm.md and derives an EARS-style requirements.md carrying all agreed decisions and constraints.: 3/3 vs 0/3
- `brainstorm-handoff-with-existing-work-items` — Meets criterion: Stops for user review before beginning design work.: 3/3 vs 2/3
- `update-existing-spec-with-completed-tasks` — Meets criterion: Updates the supplied existing work item in place and asks no questions already answered by the prompt.: 3/3 vs 2/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirements-from-brainstorm | with_skill | 33 | 0 | 11/11, 11/11, 11/11 |
| requirements-from-brainstorm | without_skill | 6 | 27 | 0/11, 0/11, 6/11 |
| tasks-behind-blocked-review | with_skill | 15 | 3 | 5/6, 5/6, 5/6 |
| tasks-behind-blocked-review | without_skill | 16 | 2 | 5/6, 6/6, 5/6 |
| bugfix-spec-from-issue | with_skill | 21 | 3 | 8/8, 5/8, 8/8 |
| bugfix-spec-from-issue | without_skill | 5 | 19 | 3/8, 0/8, 2/8 |
| tasks-from-approved-design | with_skill | 8 | 2 | 4/5, 4/5 |
| tasks-from-approved-design | without_skill | 3 | 7 | 2/5, 1/5 |
| design-from-approved-requirements | with_skill | 13 | 2 | 4/5, 5/5, 4/5 |
| design-from-approved-requirements | without_skill | 8 | 7 | 2/5, 3/5, 3/5 |
| bugfix-design-from-approved-analysis | with_skill | 15 | 0 | 5/5, 5/5, 5/5 |
| bugfix-design-from-approved-analysis | without_skill | 8 | 7 | 2/5, 3/5, 3/5 |
| design-first-feature | with_skill | 0 | 12 | 0/4, 0/4, 0/4 |
| design-first-feature | without_skill | 0 | 12 | 0/4, 0/4, 0/4 |
| brainstorm-handoff-with-existing-work-items | with_skill | 12 | 0 | 4/4, 4/4, 4/4 |
| brainstorm-handoff-with-existing-work-items | without_skill | 7 | 5 | 1/4, 3/4, 3/4 |
| update-existing-spec-with-completed-tasks | with_skill | 9 | 6 | 3/5, 3/5, 3/5 |
| update-existing-spec-with-completed-tasks | without_skill | 8 | 7 | 3/5, 2/5, 3/5 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| requirements-from-brainstorm | Run produced file: 01-quota-alerts/requirements.md | 3/3 | 0/3 |
| requirements-from-brainstorm | Run produced file: 01-quota-alerts/brainstorm.md | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Writes requirements.md inside a numbered work-item directory with the brainstorm copied in beside it | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced | 3/3 | 1/3 |
| requirements-from-brainstorm | Meets criterion: The document opens with an introduction and a glossary | 3/3 | 1/3 |
| requirements-from-brainstorm | Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL) | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system' | 3/3 | 0/3 |
| requirements-from-brainstorm | Meets criterion: Carries the brainstorm's threshold decisions: defaults of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period | 3/3 | 1/3 |
| requirements-from-brainstorm | Meets criterion: Carries the brainstorm's evaluation decisions: evaluation on the ingest increment with fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts | 3/3 | 1/3 |
| requirements-from-brainstorm | Meets criterion: Requires the new quota-threshold notification template | 3/3 | 1/3 |
| requirements-from-brainstorm | Meets criterion: Covers reading and replacing the threshold set through the API, following the existing plan sub-resource, with error cases for an invalid list | 3/3 | 1/3 |
| tasks-behind-blocked-review | Meets criterion: Does not generate tasks.md and says plainly that no task list is generated behind a BLOCKED design review | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Sets out both critical findings in its own words: no per-event data source for the export, and no way to select or validate a billing period | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Puts the first decision to the user: whether requirement 3 or the design gives way | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Proposes concrete design.md revisions for the billing-period finding | 0/3 | 1/3 |
| tasks-behind-blocked-review | Meets criterion: Folds in the two major findings: the http4k handler pattern and the suspended-account contradiction | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Says the design review is re-run before tasks are generated | 3/3 | 3/3 |
| bugfix-spec-from-issue | Output does not include: Hypothesized Root Cause | 2/3 | 2/3 |
| bugfix-spec-from-issue | Output does not include: # Implementation Plan | 2/3 | 2/3 |
| bugfix-spec-from-issue | Meets criterion: Writes a bugfix requirements document in a directory named for work item 08 with a -bugfix suffix | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced | 2/3 | 1/3 |
| bugfix-spec-from-issue | Meets criterion: Opens with an introduction saying where the bug occurs and what it costs customers | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing | 3/3 | 0/3 |
| tasks-from-approved-design | Meets criterion: Creates tasks.md in a sequentially numbered work-item directory and preserves the approved requirements and design inputs. | 0/2 | 0/2 |
| tasks-from-approved-design | Meets criterion: Every requirement 1.1 through 1.4 is traced by one or more tasks, and tasks name the corresponding PlanSummaryHandler, PlanSummaryService, or PlanSummary design component. | 2/2 | 2/2 |
| tasks-from-approved-design | Meets criterion: Tasks are dependency-ordered with the OpenAPI update and scripts/generate-api-types.sh before implementation, and implementation tasks include build/test subtasks. | 2/2 | 1/2 |
| tasks-from-approved-design | Meets criterion: The ending tasks include the API contract/type regeneration requirement, E2E tests under e2e/, knowledge-base/documentation updates, and final full validation. | 2/2 | 0/2 |
| tasks-from-approved-design | Meets criterion: The assistant stops and asks for review without invoking sdd:spec-task-review. | 2/2 | 0/2 |
| design-from-approved-requirements | Meets criterion: Creates a comprehensive design.md next to the approved requirements and stops for user review. | 3/3 | 3/3 |
| design-from-approved-requirements | Meets criterion: Includes the applicable design-document-sections.md sections, with named components and explicit requirement tracing. | 3/3 | 2/3 |
| design-from-approved-requirements | Meets criterion: Defines the threshold API as a plan-shaped sub-resource and respects the no-new-table constraint with fired state on usage-meters. | 3/3 | 3/3 |
| design-from-approved-requirements | Meets criterion: Covers correctness properties, error handling, and unit, integration, and E2E testing. | 3/3 | 0/3 |
| design-from-approved-requirements | Meets criterion: Does not invoke sdd:spec-design-review; explains it will run after design approval. | 1/3 | 0/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Creates a design.md beside the approved bugfix analysis and stops for user review. | 3/3 | 3/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Defines Bug_Condition, Property, and Preservation glossary terms and formally states the fault condition. | 3/3 | 0/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Identifies the one-hour Quota_Cache TTL and absent plan-change invalidation as the root cause, naming QuotaService.kt and its affected functions. | 3/3 | 2/3 |
| bugfix-design-from-approved-analysis | Meets criterion: States correctness properties for immediate plan-change freshness and preserved caching when no plan changes occur. | 3/3 | 3/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Testing strategy covers exploratory reproduction, fix verification, and preservation/regression checks, without invoking a review skill. | 3/3 | 0/3 |
| design-first-feature | Meets criterion: Creates only design.md first in a new sequentially numbered work-item directory; no requirements.md is generated. | 0/3 | 0/3 |
| design-first-feature | Meets criterion: Treats webhook-contract.md as authoritative, preserving payload fields, 204 success, 400 malformed-input behavior, retries, and event_id idempotency. | 0/3 | 0/3 |
| design-first-feature | Meets criterion: Aligns the design with the supplied architecture and OpenAPI context and is implementation-ready. | 0/3 | 0/3 |
| design-first-feature | Meets criterion: Stops for user review and explains that requirements.md follows design approval and sdd:spec-design-review runs once both documents exist. | 0/3 | 0/3 |
| brainstorm-handoff-with-existing-work-items | Meets criterion: Finds the topic-matching invoice-reminders brainstorm and ignores the unrelated dark-mode brainstorm. | 3/3 | 3/3 |
| brainstorm-handoff-with-existing-work-items | Meets criterion: Scans existing work-item numbers numerically and creates 06-invoice-reminders, not a count-based or 03 directory. | 3/3 | 2/3 |
| brainstorm-handoff-with-existing-work-items | Meets criterion: Copies the selected brainstorm as brainstorm.md and derives an EARS-style requirements.md carrying all agreed decisions and constraints. | 3/3 | 0/3 |
| brainstorm-handoff-with-existing-work-items | Meets criterion: Stops for user review before beginning design work. | 3/3 | 2/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Updates the supplied existing work item in place and asks no questions already answered by the prompt. | 3/3 | 2/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Adds a numbered EARS requirement and corresponding design support for the timezone response field. | 3/3 | 3/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Preserves tasks 1 through 4 as completed [x] and does not rewrite already-merged work unless required. | 3/3 | 3/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Places openapi.yaml update and scripts/generate-api-types.sh as the first new task, with traced implementation tasks, build/test subtasks, and a checkpoint. | 0/3 | 0/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Keeps the mandatory ending tasks last: E2E tests under e2e/, documentation/knowledge-base update, and final full validation; stops for review without executing anything. | 0/3 | 0/3 |
