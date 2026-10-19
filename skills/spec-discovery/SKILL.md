---
name: spec-discovery
description: Use when starting a new feature, behaviour change or technical change in the SDD pipeline, before any spec exists. Agrees problem, scope, approach, decisions and constraints with the user one question at a time, writes discovery.md into the work item, and stops for the user's approval before handing off to sdd:spec-generator. Not for bug reports, and not for changing a spec that already exists.
---

# Spec Discovery

Agree what to build with the user before any spec is written, and record the agreement in one file, `discovery.md`, that `sdd:spec-generator` reads as context.

**Workflow Position:** Step 1 of the feature pipeline. Runs BEFORE `sdd:spec-generator`.

You use only reading, searching, writing one file and asking the user.

## Inputs

Read these in order without asking anything. Note each missing input on the `Sources unavailable` line of the artefact; a missing input is never a reason to stop.

1. The user's request. If it is absent or a single phrase, the first question asks for the problem.
2. `AGENTS.md`, `CLAUDE.md`, `README.md` at the root: purpose and conventions.
3. `**/UBIQUITOUS_LANGUAGE.md`: existing terms to reuse.
4. `**/ARCHITECTURE.md`, `project/architecture/**`, `docs/architecture/**`: components the user may want reused.
5. `**/openapi.yaml`, `docs/apis/**/*.yaml`: the existing contract, so decisions do not contradict it.
6. `project/work-items/[0-9]*/`: overlap with existing work, draft `discovery.md` files, and the highest number in use.
7. `knowledge-base/**/*.md` and any evidence file the user names.

## Procedure

1. Read the inputs.
2. If the request describes existing behaviour broken against its spec, ask: "(a) new or changed behaviour, or (b) a defect in existing behaviour?" On (b), stop and tell the user to run `sdd:spec-generator` in bugfix mode. Write nothing.
3. If an existing work item covers the topic, ask: "(a) start a new work item, or (b) update `{NN}-{name}` through `sdd:spec-generator` update mode?" If the match is a directory holding only a `discovery.md` with `Status: Draft`, ask instead: "(a) resume that draft, or (b) start afresh?" On an update, stop and hand off.
4. If the request already answers problem, scope, approach, decisions, constraints and edge states, say so and skip to step 6. Write every variable it still leaves open under Open questions with a recommended answer instead of asking; the review gate is where the user corrects them.
5. Otherwise ask the questions below, one per message, in this order. Where the answers can be listed, give lettered options with the recommended one first. Skip a question the request or the inputs already answer, and state the inferred answer at the top of the next question so the user can correct it.
   1. Problem and users: who is affected, what goes wrong today, why now, what each role gets.
   2. Split, only when the request spans parts that could ship independently: "(a) one work item, (b) split into X and Y, starting with X". Continue with the chosen part only.
   3. In scope: propose the smallest numbered list of capabilities that solves the problem; the user confirms, cuts or adds. For every addition, ask what breaks without it.
   4. Out of scope: propose the adjacent items you expect to be excluded; confirm.
   5. Approach: two or three approaches with a one-line trade-off each, recommended first.
   6. Decisions: one question per open variable that a test needs a value for (default, limit, timing, trigger, frequency, uniqueness, ordering), each with two to four values and a recommendation. Ask at most six; move the rest to Open questions with a recommended answer.
   7. Constraints: what must be reused, must not be added, or must stay compatible.
   8. Edge and failure states: propose a list (empty, duplicate, stale, unavailable, unauthorised, partial); confirm.
6. Propose the work-item name in kebab case and the next number in one message; the user confirms or changes both. A name the user already gave in the request counts as confirmed, and you propose only the number.
7. Create `project/work-items/{NN}-{name}/` and write `discovery.md` with `Status: Draft`.
8. Check the draft and fix it in place before the gate:
   - no placeholder text and no vague words ("appropriate", "should", "etc.", "as needed")
   - every decision holds a concrete value
   - no two sections contradict each other
   - every in-scope item is covered by a decision, a constraint or an edge state
   - every actor appears under Terms
   - nothing names files, classes or code
   - nothing is in scope that the problem does not need
9. Present the review gate and stop.
10. On requested changes, edit the file, say what changed in one line per change, and present the gate again.
11. On explicit approval, set `Status: Approved {YYYY-MM-DD}`, give the handoff sentence and invoke `sdd:spec-generator` with the work-item path.

## Numbering

Take the highest existing number in `project/work-items/[0-9]*/` and add one, written with two digits. Do not count directories, because numbers have gaps. No match, or no `project/work-items/` tree at all, means work item `01`. Create `project/work-items/` if it does not exist. Never write spec documents in the repository root.

## Workflow

Infer the workflow and state it in the header: Requirements-First by default, Design-First when the request is contract-led or the user says so. Spec type is `feature`. Write Riskiest assumption and Success measures only when the user volunteers them or asks for them; do not spend a question on either.

## Artefact: discovery.md

| Section | Holds |
|---|---|
| Header | Title, Date, Work item `{NN}-{name}`, Spec type, Workflow, Status, Sources consulted, Sources unavailable |
| Problem | Who is affected, what goes wrong, why now |
| Users and outcomes | One bullet per role |
| Scope | In scope (numbered) and Out of scope |
| Approach | Chosen approach, alternatives, why they lost |
| Decisions | Numbered, one concrete value each: defaults, limits, timings, triggers, uniqueness |
| Constraints | Reuse, must-not-add, compatibility |
| Edge and failure states | Each state and the agreed behaviour |
| Terms | `Term_Name`: definition, marked existing or new |
| Open questions | Each with a recommended answer and the phase that settles it |
| Riskiest assumption | Optional: assumption, cheapest test, decision rule |
| Success measures | Optional: one to three |

## Question Format

Each message holds one question and nothing else beyond the inferred-answer line. Options are lettered, the recommended one comes first and is marked, and the user may answer with a letter or in their own words:

```
Which approach should the digest use?
(a) Recommended: build it from the existing usage records, no new storage; cheapest, but limited to what is already recorded.
(b) Keep a separate digest table; supports history, adds a store to maintain.
(c) Something else, tell me.
```

Do not bundle a second question, a preview of later questions or a progress list into the same message. When the user's answer opens a new variable, ask about it next rather than guessing a value.

## Header

Open `discovery.md` with a header of this shape:

```markdown
# Discovery: {Title}

**Date:** YYYY-MM-DD
**Work item:** {NN}-{name}
**Spec type:** feature
**Workflow:** Requirements-First
**Status:** Draft
**Sources consulted:** {files read}
**Sources unavailable:** {inputs that were missing, or none}
```

## Review Gate

After step 9 stop with exactly this message, with `{path}` filled in:

> `discovery.md` is written to `{path}`. Review it and reply "approved", or tell me what to change. I will not start the spec until you approve.

Anything other than an explicit approval is a change request. On approval, say exactly this, filling in the braces:

> Discovery approved. Next: `sdd:spec-generator` for work item `project/work-items/{NN}-{name}/`, feature, {workflow}; it reads `discovery.md` as context for `requirements.md`, or for `design.md` under Design-First.

## Prohibitions

- Write no file other than `discovery.md` and its directory: no requirements, design, tasks or code.
- Put no value in Decisions, Constraints or Edge states that the user did not give or confirm.
- Ask one question per message.
- Add nothing to scope that the problem does not need; cut before adding.
- Invoke no skill other than `sdd:spec-generator`, and only after explicit approval.
- Start no servers, scripts, browsers or subagents, and make no network calls.
- Commit, push and delete nothing. If the user abandons the discovery, say which file and directory remain.
- Name a language, framework, cloud or vendor only when the project already uses it.

## Next Step

On approval, invoke `sdd:spec-generator` with the work-item path.
