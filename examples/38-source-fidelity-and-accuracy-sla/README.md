# Worked example: spec 38, source-fidelity and accuracy-update SLA

One feature carried through the whole pipeline in the private project these skills came from. The project is [Gomi Bunrui](https://gomi-bunrui.app/app/), a bilingual (EN/JA) waste-sorting app for residents in Japan. The files here are the artefacts as they sit in that repository today, copied without rewriting. One path substitution is the only edit, described under Provenance.

## The feature

Every AI classification answer must cite the municipal rule it draws from and carry a last-verified date, and the cadence for re-verifying those rules is published as an accuracy-update SLA. Where a municipality has not been re-verified inside the SLA, the answer degrades honestly to a "not recently verified" state rather than hiding the gap. The work spans a React component, a Kotlin Lambda service, a new DynamoDB audit table, an IAM-protected admin route and bilingual copy.

## Files and the skill that produced each

Step numbers follow the twelve-step feature pipeline documented in `skills/spec-workflow`.

| File | Produced by | Step | Written |
|---|---|---|---|
| `brainstorm.md` | superpowers `brainstorming`; copied into the work item by `spec-generator` | 1 | 2026-05-13 |
| `requirements.md` | `spec-generator` Phase 1 | 2 | 2026-05-13, amended 2026-05-23 |
| `design.md` | `spec-generator` Phase 2, with Data Architecture, Security Architecture and Copy Intent sections added by collaborator roles | 3 | 2026-05-13, amended 2026-05-23 |
| `design-review-summary.md` | `spec-design-review` (gate) | 4 | 2026-05-13 |
| `tasks.md` | `spec-generator` Phase 3; Status Summary table updated during implementation | 5 | 2026-05-13, amended 2026-05-23 |
| `test-plan.md` | `spec-qa-review` plan mode | 7 | 2026-05-13 |
| `implementation-audit.md` | `spec-implementation-audit` (gate) | 9 | 2026-05-23 |
| `qa-review.md` | `spec-qa-review` review mode | 10 | 2026-05-23 |
| `spec-maintenance-report.md` | `spec-maintenance` | 11 | 2026-05-23 |

Steps 6 (task review), 8 (implementation) and 12 (PR) left no file in this directory. Step 6 is discussed below.

## Gate verdicts

- **Design review (step 4):** PASS with four MINOR findings, MINOR-001 to MINOR-004. No CRITICAL or MAJOR.
- **Implementation audit (step 9):** PASS with four MINOR findings, MINOR-005 to MINOR-008, all spec-versus-code drift in naming and wording. Every commit hash claimed in `tasks.md` was verified against the branch.
- **QA review (step 10):** PASS with one new MINOR finding, MINOR-009. The 85 percent unit-coverage gate was not met on the spec's own classes; the reviewer classified that MINOR because the end-to-end suite was green.
- **Maintenance (step 11):** informational. Six MINOR findings resolved by amending `requirements.md`, `design.md` and `tasks.md`; three earlier specs annotated.

No gate on this feature returned BLOCKED, so this example does not show what a blocking verdict looks like.

## Where the artefacts diverge from the skill text

These are reported, not reconciled. They are what the pipeline actually left behind.

1. **No `task-review-summary.md`.** The `spec-task-review` gate (step 6) was not run on this feature. One visible consequence: `brainstorm.md` is still present, because that gate deletes it on pass.
2. **`qa-review.md` is a separate file.** The skill's review mode is meant to update `test-plan.md` in place, filling the Actual column. Here `test-plan.md` still reads "Pending" throughout and the review verdict sits in its own file with its own coverage matrix.
3. **`requirements.md`, `design.md` and `tasks.md` are the post-maintenance versions.** Step 11 amended them in place, and the amendments are marked inline, for example "(Amended from Phase 4 MINOR-002 ...)". The step 2 and step 3 originals are not preserved separately.
4. **`tasks.md` checkboxes disagree with its Status Summary.** Tasks 1 to 18 are unticked while the Status Summary marks them done with commit hashes; only task 19 is ticked.
5. **Role bylines and `AIW-nnn` issue links come from the orchestration layer**, not from the skills. In the private project each role (Senior Product Owner, Application Architect, Tech Lead, Senior QA Engineer, Enterprise Architect) ran as a Paperclip agent that loaded the SKILL.md for its step. The `AIW-nnn` links point at that project's private tracker and do not resolve here. The hand-off blocks at the end of each file are also from that layer.

## Provenance

- **Harness:** Paperclip agents, one per role, each loading the relevant SKILL.md. The model used is not recorded in the artefacts or in the project repository, so it is not stated here.
- **Skill versions:** produced with the private project's skill snapshot dated 2026-04-25. Compared with `skills/` at the commit that added this example: `spec-task-review`, `spec-qa-review`, `spec-implementation-audit` and `spec-maintenance` are identical; `spec-design-review` differs by one line, a project-specific path made generic; `spec-generator` has since gained first-adoption handling for repositories without the expected directories, the rule that every EARS criterion's subject is a glossary term, a bugfix path that skips the design gate, and a step-by-step procedure for a BLOCKED design review; `spec-workflow` has gained work-item identifier rules. None of these later changes alter the shape of the documents shown here.
- **Edits made for publication:** one substitution. The absolute worktree path in the hand-off blocks of `tasks.md`, `implementation-audit.md`, `qa-review.md` and `spec-maintenance-report.md` (five occurrences) was replaced with the repository-relative `.worktree/38-source-fidelity-and-accuracy-sla`. Nothing else was changed.
