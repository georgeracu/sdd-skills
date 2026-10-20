# Eval results — iteration-5

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
| judge_tokens | 1196538 |
| timeout_seconds | 900.0 |
| judge_timeout_seconds | 180.0 |
| sandbox | tmpdir + acceptEdits |
| network_isolated | false |
| seed | fcb7a2907c54819f |
| runs_per_arm | 3 |
| parallel | 3 |
| pack_hash | aedde3c370113442ead75c15a3e31dca6d607b8dc4dffcbf2497d9a911232665 |
| bundle_hash | 9bb67c4e05c1568f31e883c5026324966847404046972ee57fd131e5064be4a6 |
| snapshot_hash | c492f17db3b0ca6d8b7c742ea55e2f134a1564680c15ee38427011609036d614 |
| input_drift_allowed | none |
| cases_total | 9 |
| cases_included | 9 |
| cases_degraded | none |
| judge_failures | requirements-from-discovery: the judge did not return a verdict this harness can read |
| label_pass_rates | A: 0.6454 (91/141), B: 0.6454 (91/141) |
| judge_adapter | claude_code |

## Summary

| Configuration | Pass rate | Time (s) | Tokens |
| --- | --- | --- | --- |
| with_skill | 0.7344 (±0.1472, n=9) | 111.1813 (±33.5896, n=9) | 351,295 (±114,779, n=9) |
| without_skill | 0.5381 (±0.136, n=9) | 87.6737 (±47.9159, n=9) | 232,367 (±123,260, n=9) |

**Delta (with_skill − without_skill):** pass_rate +0.1963, time_seconds +23.51, tokens +118,927

*measured over 9 cases × 3 runs. Run-to-run spread observed this iteration on identical content (same arm, repeated): pass_rate ±0.4028 -- see the A/A recipe in WORKFLOW.md to measure your own pack's floor.*

## Regressions and gains

with_skill scored below baseline:

- `tasks-behind-blocked-review` — Meets criterion: Does not generate tasks.md and says plainly that no task list is generated behind a BLOCKED design review: 2/3 vs 3/3
- `discovery-handoff-with-existing-work-items` — Meets criterion: Creates no new work-item directory and makes no copy of the discovery file.: 1/3 vs 3/3
- `update-existing-spec-with-completed-tasks` — Meets criterion: Updates the supplied existing work item in place and asks no questions already answered by the prompt.: 2/3 vs 3/3

with_skill scored above baseline:

- `requirements-from-discovery` — Meets criterion: Writes requirements.md in the existing 01-quota-alerts work-item directory, beside the existing discovery.md, without creating a new directory or copying the discovery: 1/2 vs 0/2
- `requirements-from-discovery` — Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced: 2/2 vs 1/2
- `requirements-from-discovery` — Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL): 2/2 vs 0/2
- `requirements-from-discovery` — Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system': 2/2 vs 0/2
- `tasks-behind-blocked-review` — Meets criterion: Proposes concrete design.md revisions for the billing-period finding: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Writes a bugfix requirements document in a numbered directory for work item 08: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced: 3/3 vs 1/3
- `bugfix-spec-from-issue` — Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL: 2/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x: 3/3 vs 0/3
- `bugfix-spec-from-issue` — Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing: 1/3 vs 0/3
- `tasks-from-approved-design` — Meets criterion: Tasks are dependency-ordered with the OpenAPI update and API type regeneration before implementation, and implementation tasks include build/test subtasks.: 3/3 vs 1/3
- `tasks-from-approved-design` — Meets criterion: The ending tasks include the API contract/type regeneration requirement, E2E tests under e2e/, knowledge-base/documentation updates, and final full validation.: 3/3 vs 0/3
- `tasks-from-approved-design` — Meets criterion: The assistant stops and asks for review without invoking sdd:spec-task-review.: 3/3 vs 1/3
- `design-from-approved-requirements` — Meets criterion: Includes the applicable design-document-sections.md sections, with named components and explicit requirement tracing.: 3/3 vs 2/3
- `design-from-approved-requirements` — Meets criterion: Covers correctness properties, error handling, and unit, integration, and E2E testing.: 2/3 vs 1/3
- `design-from-approved-requirements` — Meets criterion: Does not invoke sdd:spec-design-review; explains it will run after design approval.: 2/3 vs 0/3
- `bugfix-design-from-approved-analysis` — Meets criterion: Defines Bug_Condition, Property, and Preservation glossary terms and formally states the fault condition.: 1/3 vs 0/3
- `bugfix-design-from-approved-analysis` — Meets criterion: Testing strategy covers exploratory reproduction, fix verification, and preservation/regression checks, without invoking a review skill.: 1/3 vs 0/3
- `discovery-handoff-with-existing-work-items` — Run produced file: 06-invoice-reminders/requirements.md: 3/3 vs 0/3
- `discovery-handoff-with-existing-work-items` — Meets criterion: Derives an EARS-style requirements.md carrying all agreed decisions and constraints: seven-day unpaid-invoice reminders through the existing Notification_Service, daily scheduling, no new table, idempotency per invoice and Billing_Period, and skipping paid or cancelled invoices.: 1/3 vs 0/3

## Per case

| Case | Configuration | Passed | Failed | Runs |
| --- | --- | --- | --- | --- |
| requirements-from-discovery | with_skill | 17 | 4 | 0/1, 9/10, 8/10 |
| requirements-from-discovery | without_skill | 11 | 10 | 0/1, 6/10, 5/10 |
| tasks-behind-blocked-review | with_skill | 17 | 1 | 6/6, 6/6, 5/6 |
| tasks-behind-blocked-review | without_skill | 15 | 3 | 5/6, 5/6, 5/6 |
| bugfix-spec-from-issue | with_skill | 20 | 4 | 6/8, 6/8, 8/8 |
| bugfix-spec-from-issue | without_skill | 9 | 15 | 3/8, 3/8, 3/8 |
| tasks-from-approved-design | with_skill | 12 | 3 | 4/5, 4/5, 4/5 |
| tasks-from-approved-design | without_skill | 5 | 10 | 2/5, 2/5, 1/5 |
| design-from-approved-requirements | with_skill | 12 | 3 | 4/5, 5/5, 3/5 |
| design-from-approved-requirements | without_skill | 8 | 7 | 3/5, 4/5, 1/5 |
| bugfix-design-from-approved-analysis | with_skill | 11 | 4 | 5/5, 3/5, 3/5 |
| bugfix-design-from-approved-analysis | without_skill | 9 | 6 | 3/5, 3/5, 3/5 |
| design-first-feature | with_skill | 6 | 6 | 2/4, 2/4, 2/4 |
| design-first-feature | without_skill | 6 | 6 | 2/4, 2/4, 2/4 |
| discovery-handoff-with-existing-work-items | with_skill | 13 | 5 | 4/6, 3/6, 6/6 |
| discovery-handoff-with-existing-work-items | without_skill | 11 | 7 | 4/6, 3/6, 4/6 |
| update-existing-spec-with-completed-tasks | with_skill | 7 | 8 | 2/5, 2/5, 3/5 |
| update-existing-spec-with-completed-tasks | without_skill | 8 | 7 | 3/5, 2/5, 3/5 |

## Per check

| Case | Check | with_skill | without_skill |
| --- | --- | --- | --- |
| requirements-from-discovery | Run produced file: 01-quota-alerts/requirements.md | 0/3 | 0/3 |
| requirements-from-discovery | Meets criterion: Writes requirements.md in the existing 01-quota-alerts work-item directory, beside the existing discovery.md, without creating a new directory or copying the discovery | 1/2 | 0/2 |
| requirements-from-discovery | Meets criterion: Stops after requirements.md and asks for review before any design work; no design.md or tasks.md is produced | 2/2 | 1/2 |
| requirements-from-discovery | Meets criterion: The document opens with an introduction and a glossary | 2/2 | 2/2 |
| requirements-from-discovery | Meets criterion: Each requirement is a user story with numbered acceptance criteria in EARS form (WHEN, IF ... THEN, THE ... SHALL) | 2/2 | 0/2 |
| requirements-from-discovery | Meets criterion: The subject of each acceptance criterion is a glossary term such as Account_Owner, Alert_Threshold or Notification_Service, not 'the system' | 2/2 | 0/2 |
| requirements-from-discovery | Meets criterion: Carries the discovery's threshold decisions: defaults of 80% and 100%, one to five distinct percentages, one alert per threshold per billing period | 2/2 | 2/2 |
| requirements-from-discovery | Meets criterion: Carries the discovery's evaluation decisions: evaluation on the ingest increment with fired state kept on the usage-meters item, plan changes and lowered thresholds taking effect on the next increment, no alerts for suspended accounts | 2/2 | 2/2 |
| requirements-from-discovery | Meets criterion: Requires the new quota-threshold notification template | 2/2 | 2/2 |
| requirements-from-discovery | Meets criterion: Covers reading and replacing the threshold set through the API, following the existing plan sub-resource, with error cases for an invalid list | 2/2 | 2/2 |
| tasks-behind-blocked-review | Meets criterion: Does not generate tasks.md and says plainly that no task list is generated behind a BLOCKED design review | 2/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Sets out both critical findings in its own words: no per-event data source for the export, and no way to select or validate a billing period | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Puts the first decision to the user: whether requirement 3 or the design gives way | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Proposes concrete design.md revisions for the billing-period finding | 3/3 | 0/3 |
| tasks-behind-blocked-review | Meets criterion: Folds in the two major findings: the http4k handler pattern and the suspended-account contradiction | 3/3 | 3/3 |
| tasks-behind-blocked-review | Meets criterion: Says the design review is re-run before tasks are generated | 3/3 | 3/3 |
| bugfix-spec-from-issue | Output does not include: Hypothesized Root Cause | 3/3 | 3/3 |
| bugfix-spec-from-issue | Output does not include: # Implementation Plan | 3/3 | 3/3 |
| bugfix-spec-from-issue | Meets criterion: Writes a bugfix requirements document in a numbered directory for work item 08 | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Stops after the bugfix document and asks for review; no design.md or tasks.md is produced | 3/3 | 1/3 |
| bugfix-spec-from-issue | Meets criterion: Opens with an introduction saying where the bug occurs and what it costs customers | 2/3 | 2/3 |
| bugfix-spec-from-issue | Meets criterion: Section 1 (current behaviour) covers the stale quota and quotaExceeded after an upgrade and the mirror-image downgrade case, in WHEN ... THEN lines numbered 1.x without SHALL | 2/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 2 (expected behaviour) states that a plan change shows in the very next usage read, in WHEN ... THEN the system SHALL lines numbered 2.x | 3/3 | 0/3 |
| bugfix-spec-from-issue | Meets criterion: Section 3 (unchanged behaviour) uses SHALL CONTINUE TO in lines numbered 3.x and names all three preserved behaviours: the quota is still cached for reads with no plan change in between, ingest keeps recording during any window, and the plan-changed notification keeps firing | 1/3 | 0/3 |
| tasks-from-approved-design | Meets criterion: Creates tasks.md in a sequentially numbered work-item directory and preserves the approved requirements and design inputs. | 0/3 | 0/3 |
| tasks-from-approved-design | Meets criterion: Every requirement 1.1 through 1.4 is traced by one or more tasks, and tasks name the corresponding PlanSummaryHandler, PlanSummaryService, or PlanSummary design component. | 3/3 | 3/3 |
| tasks-from-approved-design | Meets criterion: Tasks are dependency-ordered with the OpenAPI update and API type regeneration before implementation, and implementation tasks include build/test subtasks. | 3/3 | 1/3 |
| tasks-from-approved-design | Meets criterion: The ending tasks include the API contract/type regeneration requirement, E2E tests under e2e/, knowledge-base/documentation updates, and final full validation. | 3/3 | 0/3 |
| tasks-from-approved-design | Meets criterion: The assistant stops and asks for review without invoking sdd:spec-task-review. | 3/3 | 1/3 |
| design-from-approved-requirements | Meets criterion: Creates a comprehensive design.md next to the approved requirements and stops for user review. | 2/3 | 2/3 |
| design-from-approved-requirements | Meets criterion: Includes the applicable design-document-sections.md sections, with named components and explicit requirement tracing. | 3/3 | 2/3 |
| design-from-approved-requirements | Meets criterion: Defines the threshold API as a plan-shaped sub-resource and respects the no-new-table constraint with fired state on usage-meters. | 3/3 | 3/3 |
| design-from-approved-requirements | Meets criterion: Covers correctness properties, error handling, and unit, integration, and E2E testing. | 2/3 | 1/3 |
| design-from-approved-requirements | Meets criterion: Does not invoke sdd:spec-design-review; explains it will run after design approval. | 2/3 | 0/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Creates a design.md beside the approved bugfix analysis and stops for user review. | 3/3 | 3/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Defines Bug_Condition, Property, and Preservation glossary terms and formally states the fault condition. | 1/3 | 0/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Identifies the one-hour Quota_Cache TTL and absent plan-change invalidation as the root cause, naming QuotaService.kt and its affected functions. | 3/3 | 3/3 |
| bugfix-design-from-approved-analysis | Meets criterion: States correctness properties for immediate plan-change freshness and preserved caching when no plan changes occur. | 3/3 | 3/3 |
| bugfix-design-from-approved-analysis | Meets criterion: Testing strategy covers exploratory reproduction, fix verification, and preservation/regression checks, without invoking a review skill. | 1/3 | 0/3 |
| design-first-feature | Meets criterion: Creates only design.md first in a new sequentially numbered work-item directory; no requirements.md is generated. | 0/3 | 0/3 |
| design-first-feature | Meets criterion: Treats webhook-contract.md as authoritative, preserving payload fields, 204 success, 400 malformed-input behavior, retries, and event_id idempotency. | 3/3 | 3/3 |
| design-first-feature | Meets criterion: Aligns the design with the supplied architecture and OpenAPI context and is implementation-ready. | 3/3 | 3/3 |
| design-first-feature | Meets criterion: Stops for user review and explains that requirements.md follows design approval and sdd:spec-design-review runs once both documents exist. | 0/3 | 0/3 |
| discovery-handoff-with-existing-work-items | Run produced file: 06-invoice-reminders/requirements.md | 3/3 | 0/3 |
| discovery-handoff-with-existing-work-items | Meets criterion: Uses the named work item project/work-items/06-invoice-reminders and its discovery.md. | 2/3 | 2/3 |
| discovery-handoff-with-existing-work-items | Meets criterion: Ignores the unrelated 07-dark-mode discovery. | 3/3 | 3/3 |
| discovery-handoff-with-existing-work-items | Meets criterion: Creates no new work-item directory and makes no copy of the discovery file. | 1/3 | 3/3 |
| discovery-handoff-with-existing-work-items | Meets criterion: Derives an EARS-style requirements.md carrying all agreed decisions and constraints: seven-day unpaid-invoice reminders through the existing Notification_Service, daily scheduling, no new table, idempotency per invoice and Billing_Period, and skipping paid or cancelled invoices. | 1/3 | 0/3 |
| discovery-handoff-with-existing-work-items | Meets criterion: Stops for user review before beginning design work. | 3/3 | 3/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Updates the supplied existing work item in place and asks no questions already answered by the prompt. | 2/3 | 3/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Adds a numbered EARS requirement and corresponding design support for the timezone response field. | 3/3 | 3/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Preserves tasks 1 through 4 as completed [x] and does not rewrite already-merged work unless required. | 2/3 | 2/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Places the openapi.yaml update and API type regeneration as the first new task, with traced implementation tasks, build/test subtasks, and a checkpoint. | 0/3 | 0/3 |
| update-existing-spec-with-completed-tasks | Meets criterion: Keeps the mandatory ending tasks last: E2E tests under e2e/, documentation/knowledge-base update, and final full validation; stops for review without executing anything. | 0/3 | 0/3 |
