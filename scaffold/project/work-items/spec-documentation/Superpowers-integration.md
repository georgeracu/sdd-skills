# Superpowers Integration

SDD wraps three superpowers skills (`brainstorming`, `subagent-driven-development`, `finishing-a-development-branch`) but doesn't modify them. The integration happens through two mechanisms: the brainstorming-to-spec handoff, and explicit override rules in `sdd:spec-workflow`.

## The two handoff gaps

If superpowers ran with its default chaining, two transitions would land in the wrong place:

| After... | Default superpowers next step | SDD override |
|---|---|---|
| `brainstorming` | `writing-plans` | `sdd:spec-generator` |
| `subagent-driven-development` | `finishing-a-development-branch` | `sdd:spec-implementation-audit` |

The `sdd:spec-workflow` skill documents these overrides. Its description is written for Claude Search Optimisation so it loads whenever SDD work is happening. Once loaded, its rules take precedence over the default chaining.

## Full override rules

- After `brainstorming` → `sdd:spec-generator` (NOT `writing-plans`)
- After `subagent-driven-development` → `sdd:spec-implementation-audit` (NOT `finishing-a-development-branch`)
- After `sdd:spec-qa-review` review mode passes → `sdd:spec-maintenance`
- After `sdd:spec-maintenance` → `finishing-a-development-branch`

**Exception:** the bugfix pipeline follows default chaining. `subagent-driven-development` → `finishing-a-development-branch` directly, no audit, no maintenance.

## Brainstorming → spec-generator handoff

`brainstorming` produces `docs/superpowers/specs/{topic}-design.md`. This is conversation context — the agreed direction.

When `sdd:spec-generator` starts:

1. Find the most recent brainstorming output in `docs/superpowers/specs/`
2. Determine the next sequential work-item number in `project/work-items/`
3. Create `project/work-items/{NN}-{name}/`
4. Copy the brainstorming output to `brainstorm.md` in that directory
5. Read `brainstorm.md` as context for `requirements.md`

The copy is what gets deleted after `spec-task-review` passes. The original at `docs/superpowers/specs/` stays — superpowers owns that location.

## Why not just modify the superpowers skills?

Two reasons:

1. **Updates.** Superpowers skills evolve independently. Forking them creates a maintenance burden every time upstream changes.
2. **Reuse.** The superpowers skills work fine in non-SDD contexts (e.g. exploratory work that doesn't need a spec). Keeping them unmodified means they're still usable outside the pipeline.

The override pattern (a reference skill that loads via description triggers) is the cleanest way to extend behaviour without forking.

## What this means for agents

- If you're about to invoke `writing-plans` after brainstorming on this project — stop. The next step is `sdd:spec-generator`.
- If you're about to invoke `finishing-a-development-branch` after implementation — stop unless this is a bugfix. The next step is `sdd:spec-implementation-audit`.
- Load `sdd:spec-workflow` whenever a user says "create a feature", "implement this spec", or anything signalling SDD work.

## What this means for the pipeline

The pipeline has the right number of artefacts and gates because superpowers handles the bookend tasks (brainstorming, implementation, PR creation) while SDD owns the spec-shaped middle. Neither side has to know much about the other beyond the override rules.
