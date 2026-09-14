# SDD Documentation

This directory documents this project's Spec-Driven Development (SDD) workflow — how a feature or bug travels from idea to merged PR.

**Source of truth:** the SDD skills live under `.claude/skills/sdd/`. These documents explain how they fit together. The `sdd:spec-workflow` skill is the canonical pipeline map and overrides anything written here if they disagree.

## Where to start

| If you want to... | Read |
|---|---|
| Build a new feature | [Feature-pipeline.md](Feature-pipeline.md) |
| Fix a bug | [Bugfix-pipeline.md](Bugfix-pipeline.md) |
| Understand the quality gates | [Quality-gates.md](Quality-gates.md) |
| Know what files end up where | [Artifacts.md](Artifacts.md) |
| Know which agent owns each step | [Agent-roles.md](Agent-roles.md) |
| Understand how SDD plays with superpowers | [Superpowers-integration.md](Superpowers-integration.md) |
| See the shape of any output document | [../templates/](../templates/README.md) |

## The 30-second summary

SDD is a documented pipeline of skills that chain together with quality gates between phases. Two pipelines:

- **Feature pipeline** (12 steps): brainstorming → spec generation → design review → task review → test plan → implementation → audit → coverage review → maintenance → PR
- **Bugfix pipeline** (3 steps): spec generation → implementation → PR. No gates, no audits.

Every work item produces artifacts under `project/work-items/{NN}-{name}/`. Artifacts are version-controlled and travel with the code.

## Core principles

- **Brainstorming is the front door for features.** Ideas are refined before specs are written.
- **Specs drive implementation.** Requirements, design, and tasks are formalised before code.
- **Quality gates catch issues early.** Design alignment, task completeness, and implementation correctness are verified at stage boundaries.
- **Superpowers skills stay unmodified.** SDD skills own the chaining and override default superpowers transitions via `sdd:spec-workflow`.
- **Bugfixes get a lighter pipeline.** No brainstorming, no quality gates, straight through.

## Entry point

When agents start work on a new feature or bug, they should load `sdd:spec-workflow`. Its description triggers on phrases like "create a feature", "create a spec", "implement this spec", "new work item".

For features, this kicks off `brainstorming` (superpowers) first.
For bugs, it goes straight to `sdd:spec-generator` in bugfix mode.

