---
title: Worked example
description: Spec 38 carried through the pipeline, artefact by artefact, as the files sit in the project the skills came from.
---

Spec 38 is one feature carried through the whole pipeline in [Gomi Bunrui](https://gomi-bunrui.app/app/), a bilingual (English and Japanese) waste-sorting app for residents in Japan. The files are copied without rewriting, and the [example's README](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/README.md) records the one path substitution made for publication.

:::note
The artefacts were produced with an earlier snapshot of the skills, dated 2026-04-25. The skills have changed since then, so read the [skill pages](/sdd-skills/skills/spec-workflow/) for current behaviour. Use this example to see the shape of each document.
:::

## The feature

Every AI classification answer must cite the municipal rule it draws from and carry a last-verified date. The cadence for re-verifying those rules is published as an accuracy-update SLA. When a municipality has not been re-verified inside the SLA, the answer shows a "not recently verified" state instead of hiding the gap.

The work spans a React component, a Kotlin Lambda service, a new DynamoDB audit table, an IAM-protected admin route and bilingual copy.

## The artefacts in pipeline order

### Step 1: `brainstorm.md`

[brainstorm.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/brainstorm.md) was written on 2026-05-13 by superpowers' `brainstorming` skill and copied into the work item by `spec-generator`. Today [`spec-discovery`](/sdd-skills/skills/spec-discovery/) does this step and writes `discovery.md`.

### Step 2: `requirements.md`

[requirements.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/requirements.md) is [`spec-generator`](/sdd-skills/skills/spec-generator/) Phase 1, written on 2026-05-13 and amended on 2026-05-23. Read it for the EARS acceptance criteria that every later document traces back to.

### Step 3: `design.md`

[design.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/design.md) is `spec-generator` Phase 2, written on 2026-05-13 and amended on 2026-05-23. Collaborator roles added the Data Architecture, Security Architecture and Copy Intent sections.

### Step 4: `design-review-summary.md`

[design-review-summary.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/design-review-summary.md) is the [`spec-design-review`](/sdd-skills/skills/spec-design-review/) gate, run on 2026-05-13. The verdict was PASS with four MINOR findings, MINOR-001 to MINOR-004, and no CRITICAL or MAJOR. Start here if you want to see what a gate report looks like.

### Step 5: `tasks.md`

[tasks.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/tasks.md) is `spec-generator` Phase 3. Its Status Summary table was updated during implementation.

### Step 6: no task review

The [`spec-task-review`](/sdd-skills/skills/spec-task-review/) gate was not run on this feature, so there is no `task-review-summary.md`. One visible result: `brainstorm.md` is still present, because the gate deletes it on a pass.

### Step 7: `test-plan.md`

[test-plan.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/test-plan.md) is [`spec-qa-review`](/sdd-skills/skills/spec-qa-review/) in plan mode, written on 2026-05-13.

### Step 8: implementation

Implementation ran through superpowers and left no file in this directory.

### Step 9: `implementation-audit.md`

[implementation-audit.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/implementation-audit.md) is the [`spec-implementation-audit`](/sdd-skills/skills/spec-implementation-audit/) gate, run on 2026-05-23. The verdict was PASS with four MINOR findings, MINOR-005 to MINOR-008, all drift between spec and code in naming and wording. Every commit hash claimed in `tasks.md` was verified against the branch.

### Step 10: `qa-review.md`

[qa-review.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/qa-review.md) is `spec-qa-review` in review mode, run on 2026-05-23. The verdict was PASS with one new MINOR finding, MINOR-009. The 85 percent unit-coverage gate was not met on the spec's own classes, and the reviewer classified that as MINOR because the end-to-end suite was green.

### Step 11: `spec-maintenance-report.md`

[spec-maintenance-report.md](https://github.com/georgeracu/sdd-skills/blob/main/examples/38-source-fidelity-and-accuracy-sla/spec-maintenance-report.md) is [`spec-maintenance`](/sdd-skills/skills/spec-maintenance/), run on 2026-05-23. Six MINOR findings were resolved by amending `requirements.md`, `design.md` and `tasks.md`, and three earlier specs were annotated.

### Step 12: the pull request

The pull request left no file in this directory.

## Where the artefacts differ from the skills

The README reports these differences and does not reconcile them:

1. There is no `task-review-summary.md`, because step 6 was skipped.
2. `qa-review.md` is a separate file. The review mode is meant to update `test-plan.md` in place, and here `test-plan.md` still reads "Pending" throughout.
3. `requirements.md`, `design.md` and `tasks.md` are the versions after maintenance. Step 11 amended them in place and marked each amendment inline. The originals from steps 2 and 3 are not kept.
4. The checkboxes in `tasks.md` disagree with its Status Summary. Tasks 1 to 18 are unticked while the summary marks them done with commit hashes. Today's implementation audit would fail those tasks, because only a ticked box completes a task.
5. Role bylines, `AIW-nnn` issue links and the hand-off blocks come from the orchestration layer of the private project, not from the skills. The `AIW-nnn` links do not resolve here.

No gate on this feature returned BLOCKED, so the example does not show a blocking verdict.
