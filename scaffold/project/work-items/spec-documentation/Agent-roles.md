# Agent Roles

This team model is optional. The seven skills do not depend on it.

This document maps a team of agent personas to the Spec-Driven Development (SDD) pipeline. It is the canonical reference for **who does what** during SDD work.

**Source of truth:** if you adopt this model, put the persona files under `.claude/agents/` (or your agent runtime's equivalent). This document describes how they interact. The persona files override anything written here if they disagree.

## The team

Eleven personas. Each has a primary mandatory skill (the one that bootstraps their work) and a set of supporting skills they may invoke as needed.

| # | Persona | Slug | Primary skills |
|---|---|---|---|
| 1 | Senior Product Owner | `senior-product-owner` | `brainstorming`, `sdd:spec-generator` (Phase 1) |
| 2 | Tech Lead | `tech-lead` | `sdd:spec-workflow`, `sdd:spec-task-review`, `sdd:spec-implementation-audit`, `tech-lead` |
| 3 | Application Architect | `application-architect` | `sdd:spec-generator` (Phase 2), `sdd:spec-design-review`, `technical-analysis` |
| 4 | Data Architect | `data-architect` | `sdd:spec-generator` (Phase 2 — Data Models) |
| 5 | Security Architect | `security-architect` | `sdd:spec-generator` (Phase 2 — Security Architecture), `sdd:spec-design-review` |
| 6 | Enterprise Architect | `enterprise-architect` | `sdd:spec-maintenance` |
| 7 | Senior Backend Engineer | `senior-backend-engineer` | `backend-dev`, `subagent-driven-development`, `test-driven-development` |
| 8 | Senior Frontend Engineer | `senior-frontend-engineer` | `frontend-dev`, `frontend-design`, `subagent-driven-development`, `test-driven-development` |
| 9 | Senior QA Engineer | `senior-qa-engineer` | `sdd:spec-qa-review`, `qa-review` |
| 10 | Senior Content Writer | `senior-content-writer` | `strategic-tech-writing`, `diataxis`, `tech-docs` |
| 11 | Senior Market Researcher | `senior-market-researcher` | `brainstorming` (advisor), market-research domain skills |

The **Senior Market Researcher** sits outside the SDD pipeline itself. They produce market briefs, competitor scans, and consumer research that feed the **Senior PO**'s Phase 1 brainstorm as evidence. Their output is committed to a location of your choosing, for example `docs/market-research/` (briefs, competitor scans, trends, opportunities, quarterly state), not into `project/work-items/`, which is reserved for SDD artefacts.

---

## Entry workflow

The Board (you, the human) is the only role that assigns top-level work. There are exactly two entry points:

```
                                ┌──────────────┐
                                │    BOARD     │
                                └───────┬──────┘
                                        │
                  ┌─────────────────────┴─────────────────────┐
                  │                                           │
       (anything that is NOT a bug)                   (bug report)
                  │                                           │
                  ▼                                           ▼
       ┌──────────────────────┐                  ┌──────────────────────┐
       │ Senior Product Owner │                  │      Tech Lead       │
       │       (intake)       │                  │      (triage)        │
       └──────────┬───────────┘                  └──────────┬───────────┘
                  │                                         │
                  ▼                                         ▼
          brainstorming                             classify defect,
          (superpowers)                             reproduce,
                  │                                 confirm bug
                  ▼                                         │
       sdd:spec-workflow                                    ▼
       (feature mode)                            sdd:spec-workflow
                                                 (bugfix mode)
```

### Senior PO intake (default)

The PO is the **front door for everything that is not a bug**:

- New product features
- UX/content changes
- Technical changes (refactors, migrations, infra evolution) — the PO is still the intake; the Tech Lead and architects join during brainstorming to ensure technical framing is sound
- Anything requiring user-facing acceptance criteria

The PO always starts with `brainstorming` (superpowers), which produces a design at `docs/superpowers/specs/{topic}-design.md`. The brainstorm is then copied into a new work item, and `sdd:spec-workflow` takes over in **feature mode**.

### Tech Lead triage (bugs only)

The TL is the **front door for bug reports**. The triage step is short:

1. Reproduce the defect
2. Confirm it is a bug, not a misunderstood feature request (if the latter, route to the PO)
3. Identify the affected components
4. Invoke `sdd:spec-workflow` in **bugfix mode**

No brainstorming, no gates, no audits — the bugfix pipeline is intentionally light.

---

## Per-step ownership — feature pipeline

The 12-step feature pipeline from [Feature-pipeline.md](Feature-pipeline.md), with ownership annotated:

| # | Step | Primary | Reviewers / Contributors |
|---|---|---|---|
| 1 | Brainstorming | Senior PO | Tech Lead, Senior Content Writer (refines wording), Enterprise Architect (sanity-checks fit with prior specs) |
| 2 | `requirements.md` | Senior PO | Tech Lead |
| 3 | `design.md` | Application Architect | Data Architect (Data Models section), Security Architect (Security Architecture, when in scope), Senior Backend/Frontend Engineer (feasibility) |
| 4 | `spec-design-review` gate | Tech Lead (verdict) | Application + Data + Security Architects sign; Enterprise Architect flags cross-spec impact |
| 5 | `tasks.md` | Tech Lead | Senior Backend/Frontend Engineer (sequencing sanity), Senior QA Engineer (test tasks present) |
| 6 | `spec-task-review` gate | Tech Lead (verdict) | Senior QA Engineer (coverage tasks), Senior PO (requirement tracing) |
| 7 | `test-plan.md` (plan mode) | Senior QA Engineer | Tech Lead |
| 8 | Implementation | Senior Backend Engineer + Senior Frontend Engineer (as subagents) | Tech Lead dispatches and unblocks |
| 9 | `implementation-audit.md` gate | Tech Lead (verdict) | Senior QA Engineer (evidence), Senior Backend/Frontend (discrepancy triage) |
| 10 | `spec-qa-review` review mode | Senior QA Engineer | Tech Lead |
| 11 | `spec-maintenance` | Enterprise Architect | Application Architect (when designs annotated), Senior PO (when requirements deprecated) |
| 12 | `finishing-a-development-branch` | Tech Lead | Senior Content Writer (changelog and knowledge-base updates) |

## Per-step ownership — bugfix pipeline

| # | Step | Primary | Reviewers / Contributors |
|---|---|---|---|
| 1 | `bugfix.md` | Tech Lead | Senior Backend or Frontend Engineer (whoever owns the affected code) |
| 2 | Implementation | Senior Backend or Frontend Engineer | Senior QA Engineer writes the regression test first |
| 3 | PR | Tech Lead | — |

---

## Authority model

**Single accountability — Tech Lead holds every gate verdict.** Architects, QA, and PO *sign* (their input is recorded in the gate output), but the Tech Lead writes the PASS / FAIL decision. This avoids deadlocks when reviewers disagree.

**Split architecture decisions.** Application, Data, and Security Architects own different sections of `design.md` and sign the design-review gate independently. Enterprise Architect owns cross-spec drift and is the primary author of `spec-maintenance-report.md` — they do not sign the design gate to keep their cross-cutting view fresh.

**PO owns the WHAT.** Requirements (user stories, AC, glossary) are the PO's authorship. Tech Lead reviews but does not write requirements. This keeps the customer-value framing distinct from the implementation framing.

**Engineers are subagents during implementation.** Backend and Frontend Engineers run under the `subagent-driven-development` pattern dispatched by the Tech Lead. The Tech Lead does not write production code.

---

## Handoffs

Each handoff is an explicit boundary. The outgoing role hands a completed artefact; the incoming role reads it before acting.

```
Board ─── task ───► Senior PO (or Tech Lead for bugs)

[FEATURE PIPELINE]
Senior PO ─── brainstorm output ───► Senior PO writes requirements.md
Senior PO ─── requirements.md ───► Application Architect drafts design.md
App Architect ─── design.md ───► Data + Security Architects fill their sections
Architects ─── design.md ───► Tech Lead opens spec-design-review
Tech Lead ─── PASSED gate ───► Tech Lead writes tasks.md
Tech Lead ─── tasks.md ───► Tech Lead opens spec-task-review
Tech Lead ─── PASSED gate ───► Senior QA writes test-plan.md (plan mode)
Senior QA ─── test-plan.md ───► Tech Lead dispatches Backend + Frontend subagents
Engineers ─── implementation ───► Tech Lead opens implementation-audit
Tech Lead ─── PASSED audit ───► Senior QA runs spec-qa-review (review mode)
Senior QA ─── coverage report ───► Enterprise Architect runs spec-maintenance
Enterprise Architect ─── maintenance-report ───► Tech Lead runs finishing-a-development-branch
Tech Lead ─── PR ───► Board

[BUGFIX PIPELINE]
Board ─── bug report ───► Tech Lead triages and writes bugfix.md
Tech Lead ─── bugfix.md ───► Engineer (BE or FE) implements with QA regression tests
Engineer ─── fix + tests passing ───► Tech Lead opens PR
Tech Lead ─── PR ───► Board
```

---

## How to invoke a role

You (the Board) invoke a persona by name or slug. Examples:

- "Senior PO, take this feature: [description]" → loads `senior-product-owner` persona
- "Tech Lead, triage this bug: [report]" → loads `tech-lead` persona
- "App Architect, draft the design for work item 38" → loads `application-architect` persona

The persona's first action is always to invoke its primary skill — e.g. the Senior PO immediately calls `brainstorming`, the Tech Lead immediately calls `sdd:spec-workflow`. Persona files contain the exact bootstrap sequence.

## Reference

- Persona files: if you adopt this model, put them under `.claude/agents/` (or your agent runtime's equivalent).
- Pipeline definition: `.claude/skills/sdd/spec-workflow/SKILL.md`
- Skill inventory: `.claude/skills/`
- Feature pipeline doc: [Feature-pipeline.md](Feature-pipeline.md)
- Bugfix pipeline doc: [Bugfix-pipeline.md](Bugfix-pipeline.md)
- Quality gates: [Quality-gates.md](Quality-gates.md)
- Running the roles as autonomous agents: the same personas can operate as autonomous agents rather than a human-in-the-loop team
