# SDD Skills

Agent skills for spec-driven development: EARS requirements, a reviewed design, traced tasks and an audited implementation, with quality gates that block and an audit trail that stays in the repository.

**Documentation:** https://georgeracu.github.io/sdd-skills/

## Install

Claude Code:

```
claude plugin marketplace add georgeracu/sdd-skills
claude plugin install sdd@georgeracu
```

Other agents, such as Codex or Cursor:

```
npx skills@latest add georgeracu/sdd-skills
```

Manual copy, which keeps the `sdd:` prefix the skills use to call each other:

```
git clone https://github.com/georgeracu/sdd-skills.git
mkdir -p .claude/skills/sdd
cp -R sdd-skills/skills/. .claude/skills/sdd/
```

Choose one route per agent. Two routes install every skill twice.

Then run `/sdd:setup` once in your project. It copies the work-item scaffold, records your build and test commands in `AGENTS.md` and reports the optional inputs it found. The full feature pipeline also needs the [superpowers](https://github.com/obra/superpowers) plugin for implementation and branch close-out; [Getting started](https://georgeracu.github.io/sdd-skills/getting-started/) describes the path without it.

## Who it's for

Teams that need an audit trail and gates that block. Every work item keeps its requirements, design, review verdicts, test plan and an implementation audit backed by pasted build and test output, as files in `project/work-items/`. The design review, the task review and the implementation audit each return a blocking status.

## The pipeline

| Step | Skill | Writes |
|---|---|---|
| Once | `setup` | Scaffold, `AGENTS.md` commands |
| 1 | `spec-discovery` | `discovery.md` |
| 2, 3 | `spec-generator` | `requirements.md`, `design.md` |
| 4 | `spec-design-review` (gate) | `design-review-summary.md` |
| 5 | `spec-generator` | `tasks.md` |
| 6 | `spec-task-review` (gate) | `task-review-summary.md` |
| 7 | `spec-qa-review` plan mode | `test-plan.md` |
| 8 | superpowers `subagent-driven-development` | Implementation |
| 9 | `spec-implementation-audit` (gate) | `implementation-audit.md` |
| 10 | `spec-qa-review` review mode | Updated `test-plan.md` |
| 11 | `spec-maintenance` | `spec-maintenance-report.md`, annotated older specs |
| 12 | superpowers `finishing-a-development-branch` | Pull request |

`spec-workflow` is the reference for this order and for the bugfix pipeline. The [worked example](https://georgeracu.github.io/sdd-skills/worked-example/) follows one real feature through every step.

## Stability

Releases are tagged, and the plugin carries the same version, so pin a tag if you depend on specific behaviour. While the version is 0.x, breaking changes come only in minor versions, such as 0.2.0 to 0.3.0, and patch versions do not break anything. The [changelog](CHANGELOG.md) marks every breaking change.

## Roadmap

[ROADMAP.md](ROADMAP.md) lists the candidates for future work.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## Licence

MIT. See `LICENSE`.
