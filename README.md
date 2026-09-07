# SDD Skills

Seven agent skills that implement a spec-driven development pipeline: EARS requirements, design, gated reviews, traced tasks, implementation audit, QA review and maintenance. Written as `SKILL.md` files for coding agents that load skills this way, such as Claude Code, Codex and pi.

## The pipeline

| Order | Skill | Purpose |
|---|---|---|
| 1 | `spec-workflow` | Reference document, not a pipeline step in its own right: maps the full ordering and documents the override rules that take precedence over superpowers' default chaining. |
| 2 | `spec-generator` | Generates `requirements.md`, `design.md` and `tasks.md` in three phases, with a user review gate between each. |
| 3 | `spec-design-review` (gate) | Validates `design.md` against `requirements.md` and existing architecture before tasks are generated. |
| 4 | `spec-task-review` (gate) | Validates `tasks.md` covers every requirement and design element, is correctly sequenced and is implementable, before execution starts. |
| 5 | `spec-qa-review` | Plan mode writes `test-plan.md` before implementation; review mode audits actual test coverage against that plan afterwards. |
| 6 | `spec-implementation-audit` (gate) | Verifies the finished implementation matches requirements, design and tasks, backed by pasted build/test evidence. |
| 7 | `spec-maintenance` | After QA review passes, checks whether the new work invalidates anything in previously implemented specs and annotates them. |

Three of the seven are explicit blocking gates on CRITICAL findings: `spec-design-review`, `spec-task-review` and `spec-implementation-audit`. `spec-qa-review` reports gaps but doesn't itself block, and `spec-maintenance` has no gate behaviour at all (it always passes, by design).

## What the skills assume about your repo

These are the conventions the skills read from or write to, taken directly from the skill text:

- `docs/superpowers/specs/{topic}-design.md`: output of the superpowers `brainstorming` skill. `spec-generator` finds the most recent matching file and copies it into the new work item as `brainstorm.md`.
- `project/work-items/{NN}-{name}/`: the work item directory. `requirements.md`, `design.md`, `tasks.md` and every downstream review document live here, numbered sequentially.
- `project/work-items/template-spec/`: structural templates for `requirements.md`, `design.md` and `tasks.md`.
- `project/work-items/spec-documentation/`: detailed process documentation, read by `spec-generator` before it writes anything.
- `.kiro/specs/`: completed specs kept as content-quality reference examples.
- `UBIQUITOUS_LANGUAGE.md` (glob `**/UBIQUITOUS_LANGUAGE.md`): domain terminology, read for consistency.
- `knowledge-base/**/*.md` and `knowledge-base/README.md`: domain knowledge base, read during generation and updated as a mandatory ending task.
- `openapi.yaml`: read for API consistency checks, and updated as a task whenever a spec touches an API.
- `scripts/generate-api-types.sh`: regenerates API types from `openapi.yaml`; a clean diff from this script is evidence in `spec-implementation-audit`.
- `**/ARCHITECTURE.md`, `project/architecture/**`, `docs/architecture/**`: architecture documentation, read for context.
- `AGENTS.md`: commit conventions, referenced by `spec-generator`'s execution step.
- `MEMORY.md` and `CLAUDE.md`: read by `spec-design-review` for high-level architectural context.
- `e2e/`: directory for end-to-end tests, referenced throughout as the home for E2E test tasks and runs.
- auto-memory: updated as a mandatory ending task whenever project conventions change (the skill text doesn't fix a path for this, just the behaviour).

## Dependency on superpowers

The full pipeline hands off to and from the [superpowers](https://github.com/obra/superpowers) plugin: `brainstorming` feeds `spec-generator`, `subagent-driven-development` executes the tasks, and `finishing-a-development-branch` closes out the branch after `spec-maintenance`. `spec-workflow` documents override rules that replace superpowers' default chaining (after `brainstorming`, invoke `sdd:spec-generator` rather than `writing-plans`; after `subagent-driven-development`, invoke `sdd:spec-implementation-audit` rather than `finishing-a-development-branch` directly), with one exception: the bugfix pipeline follows superpowers' default chaining throughout, since there are no quality gates on bugfixes.

superpowers is required to run the pipeline end to end. The individual review and generator skills work without it: bugfix specs never touch brainstorming, `spec-generator`'s brainstorming handoff only fires when a matching file already exists under `docs/superpowers/specs/`, and every review skill (`spec-design-review`, `spec-task-review`, `spec-qa-review`, `spec-implementation-audit`, `spec-maintenance`) works from the spec documents in `project/work-items/`, however those were produced.

## Installation

Copy or symlink each `skills/<name>` directory into `.claude/skills/sdd/<name>` for Claude Code, or `.agents/skills/sdd/<name>` for agents that read the agentskills layout. This repo dogfoods the symlink form itself, for example:

```
ln -s ../../../skills/spec-generator .claude/skills/sdd/spec-generator
ln -s ../../../skills/spec-generator .agents/skills/sdd/spec-generator
```

repeated for each of the seven directories. The skills refer to each other with the `sdd:` prefix (`sdd:spec-generator`, `sdd:spec-design-review` and so on), so keep that namespace when installing.

## Status

Published as a reference implementation, taken from a working private project. Issues are welcome. There is no roadmap and no promise of support: the skills will keep evolving alongside the project they came from, so pin a tag if you depend on a specific version.

## Licence

MIT. See `LICENSE`.
