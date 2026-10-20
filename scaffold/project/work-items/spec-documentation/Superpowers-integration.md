# Superpowers Integration

SDD wraps two superpowers skills (`subagent-driven-development`, `finishing-a-development-branch`) but doesn't modify them. The integration happens through explicit override rules in `sdd:spec-workflow`; step 1 is `sdd:spec-discovery`, which replaces superpowers' `brainstorming` for SDD features.

## The two handoff gaps

If superpowers ran with its default chaining, two transitions would land in the wrong place:

| After... | Default superpowers next step | SDD override |
|---|---|---|
| `brainstorming` (for SDD features) | `writing-plans` | `sdd:spec-discovery`, then `sdd:spec-generator` |
| `subagent-driven-development` | `finishing-a-development-branch` | `sdd:spec-implementation-audit` |

The `sdd:spec-workflow` skill documents these overrides. Its description is written for Claude Search Optimisation so it loads whenever SDD work is happening. Once loaded, its rules take precedence over the default chaining.

## Full override rules

- For SDD features use `sdd:spec-discovery`, not `brainstorming`. After either → `sdd:spec-generator` (NOT `writing-plans`)
- After `subagent-driven-development` → `sdd:spec-implementation-audit` (NOT `finishing-a-development-branch`)
- After `sdd:spec-qa-review` review mode passes → `sdd:spec-maintenance`
- After `sdd:spec-maintenance` → `finishing-a-development-branch`

**Exception:** the bugfix pipeline follows default chaining. `subagent-driven-development` → `finishing-a-development-branch` directly, no audit, no maintenance.

## Why not just modify the superpowers skills?

Two reasons:

1. **Updates.** Superpowers skills evolve independently. Forking them creates a maintenance burden every time upstream changes.
2. **Reuse.** The superpowers skills work fine in non-SDD contexts (e.g. exploratory work that doesn't need a spec). Keeping them unmodified means they're still usable outside the pipeline.

The override pattern (a reference skill that loads via description triggers) is the cleanest way to extend behaviour without forking.

## What this means for agents

- If you're about to invoke `brainstorming` or `writing-plans` for a feature on this project — stop. Start with `sdd:spec-discovery`; the step after it is `sdd:spec-generator`.
- If you're about to invoke `finishing-a-development-branch` after implementation — stop unless this is a bugfix. The next step is `sdd:spec-implementation-audit`.
- Load `sdd:spec-workflow` whenever a user says "create a feature", "implement this spec", or anything signalling SDD work.

## What this means for the pipeline

The pipeline has the right number of artefacts and gates because superpowers handles the bookend tasks (implementation, PR creation) and `sdd:spec-discovery` opens the pipeline while SDD owns the spec-shaped middle. Neither side has to know much about the other beyond the override rules.
