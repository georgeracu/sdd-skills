# SDD Skills

Eight agent skills that implement a spec-driven development pipeline: EARS requirements, design, gated reviews, traced tasks, implementation audit, QA review and maintenance. Written as `SKILL.md` files for coding agents that load skills this way, such as Claude Code, Codex and pi.

## The pipeline

| Order | Skill | Purpose |
|---|---|---|
| 1 | `spec-discovery` | Agrees the work-item name with the user, discovers intent, constraints and approach, writes `discovery.md` in the work item and stops at a review gate before invoking `spec-generator`. |
| 2 | `spec-workflow` | Reference document, not a pipeline step in its own right: maps the full ordering and documents the override rules that take precedence over superpowers' default chaining. |
| 3 | `spec-generator` | Generates `requirements.md`, `design.md` and `tasks.md` in three phases, with a user review gate between each. |
| 4 | `spec-design-review` (gate) | Validates `design.md` against `requirements.md` and existing architecture before tasks are generated. |
| 5 | `spec-task-review` (gate) | Validates `tasks.md` covers every requirement and design element, is correctly sequenced and is implementable, before execution starts. |
| 6 | `spec-qa-review` | Plan mode writes `test-plan.md` before implementation; review mode audits actual test coverage against that plan afterwards. |
| 7 | `spec-implementation-audit` (gate) | Verifies the finished implementation matches requirements, design and tasks, backed by pasted build/test evidence. |
| 8 | `spec-maintenance` | After QA review passes, checks whether the new work invalidates anything in previously implemented specs and annotates them. |

Three of the eight are explicit blocking gates on CRITICAL findings: `spec-design-review`, `spec-task-review` and `spec-implementation-audit`. `spec-qa-review` reports gaps but doesn't itself block, and `spec-maintenance` has no gate behaviour at all (it always passes, by design).

## Expected project structure

The skills read from and write to a fixed layout. `scaffold/` in this repo holds that layout with the templates and process documents already filled in, so rather than typing the structure out by hand, copy it into the root of your project.

```
.claude/skills/sdd/<skill-name>/            # the eight skills, installed here (see Installation)
project/work-items/README.md                # orientation for the pipeline, shipped in the scaffold
project/work-items/spec-documentation/
    Agent-roles.md                          # optional persona-per-step model, read by spec-generator
    Artifacts.md                            # process doc, read by spec-generator before it writes anything
    Bugfix-pipeline.md                      # process doc, read by spec-generator before it writes anything
    Feature-pipeline.md                     # process doc, read by spec-generator before it writes anything
    Quality-gates.md                        # process doc, read by spec-generator before it writes anything
    README.md                               # process doc, read by spec-generator before it writes anything
    Superpowers-integration.md              # process doc, read by spec-generator before it writes anything
project/work-items/templates/
    project/work-items/templates/README.md  # how to use the templates
    feature/requirements.md                 # shape for a feature's requirements.md
    feature/design.md                       # shape for a feature's design.md
    feature/tasks.md                        # shape for a feature's tasks.md
    bugfix/bugfix.md                        # shape for a bugfix's bugfix.md
    bugfix/design.md                        # shape for a bugfix's design.md
    bugfix/tasks.md                         # shape for a bugfix's tasks.md
    gates/design-review-summary.md          # shape for spec-design-review's output
    gates/task-review-summary.md            # shape for spec-task-review's output
    gates/test-plan.md                      # shape for spec-qa-review's output
    gates/implementation-audit.md           # shape for spec-implementation-audit's output
    gates/spec-maintenance-report.md        # shape for spec-maintenance's output
project/work-items/{NN}-{name}/             # one directory per work item, created by spec-discovery
```

### Bootstrap

1. Install the skills (see Installation, below).
2. Copy the scaffold into your project, from a clone of this repo:

   ```
   cp -R scaffold/. /path/to/your-project/
   ```

3. In your project's `AGENTS.md` (or README), note the build, test and type-generation commands: the skills read them from there rather than assuming gradlew or npm.
4. Install superpowers if you want the end-to-end pipeline (see Dependency on superpowers, below).

### Optional inputs the skills look for when present

- `openapi.yaml` (glob `**/openapi.yaml`, or `docs/apis/**/*.yaml`): read for API consistency checks.
- `**/ARCHITECTURE.md`, `project/architecture/**`, `docs/architecture/**`: architecture documentation.
- `**/UBIQUITOUS_LANGUAGE.md`: domain terminology.
- `knowledge-base/**/*.md`: domain knowledge base.
- `AGENTS.md`, `MEMORY.md` and `CLAUDE.md`: commit conventions and architectural context.
- `e2e/`: home for end-to-end test tasks and runs.

A missing optional input is recorded in the spec as a note, not treated as an error.

`project/work-items/spec-documentation/Agent-roles.md` describes an optional persona-per-step team model; the skills don't depend on it.

## Dependency on superpowers

Step 1 (`spec-discovery`) and every gate are self-contained. Implementation and branch close-out hand off to the [superpowers](https://github.com/obra/superpowers) plugin's `subagent-driven-development` and `finishing-a-development-branch`, or to any equivalent. `spec-workflow` documents the override rule that still applies after `subagent-driven-development`: invoke `sdd:spec-implementation-audit` rather than `finishing-a-development-branch` directly. The bugfix pipeline follows superpowers' default chaining throughout, since there are no quality gates on bugfixes.

Without superpowers the pipeline runs from discovery to the test plan, and resumes at the implementation audit once the tasks are implemented. Every review skill (`spec-design-review`, `spec-task-review`, `spec-qa-review`, `spec-implementation-audit`, `spec-maintenance`) works from the spec documents in `project/work-items/`, however those were produced.

## Worked example

`examples/38-source-fidelity-and-accuracy-sla/` holds one feature carried through the pipeline in the private project the skills came from: brainstorm, requirements, design, design review, tasks, test plan, implementation audit, QA review and maintenance report, copied as they are. Its README maps each file to the skill that produced it, records the gate verdicts, and lists where the artefacts diverge from the skill text (the task-review gate was skipped, and the QA review sits in its own file rather than inside the test plan). Start with `design-review-summary.md` if you want to see what a gate report looks like.

Its artefacts follow the same layout that `scaffold/` provides, so it doubles as a filled-in reference for the templates.

## Installation

Copy or symlink each `skills/<name>` directory into `.claude/skills/sdd/<name>` for Claude Code, or `.agents/skills/sdd/<name>` for agents that read the agentskills layout. This repo dogfoods the symlink form itself, for example:

```
ln -s ../../../skills/spec-generator .claude/skills/sdd/spec-generator
ln -s ../../../skills/spec-generator .agents/skills/sdd/spec-generator
```

repeated for each of the eight directories. The skills refer to each other with the `sdd:` prefix (`sdd:spec-generator`, `sdd:spec-design-review` and so on), so keep that namespace when installing.

See Bootstrap, above, for copying `scaffold/` into your project once the skills are installed.

## Status

Published as a reference implementation, taken from a working private project. Issues are welcome. There is no roadmap and no promise of support: the skills will keep evolving alongside the project they came from, so pin a tag if you depend on a specific version.

## Licence

MIT. See `LICENSE`.
