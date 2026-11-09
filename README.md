[![Docs](https://github.com/georgeracu/sdd-skills/actions/workflows/docs.yml/badge.svg)](https://github.com/georgeracu/sdd-skills/actions/workflows/docs.yml)

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
rsync -a --exclude .claude-plugin sdd-skills/skills/ .claude/skills/sdd/
```

Choose one route per agent. Two routes install every skill twice.

Then run `/sdd:setup` once in your project. It copies the work-item scaffold, records your build and test commands in `AGENTS.md` and reports the optional inputs it found. The feature pipeline runs from discovery to the pull request with these skills alone. The bugfix pipeline implements its tasks with the [superpowers](https://github.com/obra/superpowers) plugin or by hand; [Getting started](https://georgeracu.github.io/sdd-skills/getting-started/) describes both.

## Who it's for

Teams that need an audit trail and gates that block. Every work item keeps its requirements, design, review verdicts, test plan and an implementation audit backed by pasted build and test output, as files in `project/work-items/`. The design review, the task review and the implementation audit each return a blocking status.

## The pipeline

| Step | Skill | Kind | Writes |
|---|---|---|---|
| Once | `setup` | user-invoked | Scaffold, `AGENTS.md` commands |
| 1 | `spec-discovery` | model-invoked | `discovery.md` |
| 2, 3 | `spec-generator` | model-invoked | `requirements.md`, `design.md` |
| 4 | `spec-design-review` (gate) | model-invoked | `design-review-summary.md` |
| 5 | `spec-generator` | model-invoked | `tasks.md` |
| 6 | `spec-task-review` (gate) | model-invoked | `task-review-summary.md` |
| 7 | `spec-qa-review` plan mode | model-invoked | `test-plan.md` |
| 8 | `spec-implement` | model-invoked | Implementation, one commit per task |
| 9 | `spec-implementation-audit` (gate) | model-invoked | `implementation-audit.md` |
| 10 | `spec-qa-review` review mode | model-invoked | Updated `test-plan.md` |
| 11 | `spec-maintenance` | model-invoked | `spec-maintenance-report.md`, annotated older specs |
| 12 | `spec-finish` | model-invoked | `pr.md`, pull request |

`spec-workflow` is the reference for this order and for the bugfix pipeline. Its kind is reference: the agent reads it, and it does no work of its own. The [worked example](https://georgeracu.github.io/sdd-skills/worked-example/) follows one real feature through every step.

You start a user-invoked skill yourself, for example with `/sdd:setup`. The agent starts a model-invoked skill when the pipeline reaches it. Claude Code and GitHub Copilot CLI read the `disable-model-invocation` key that marks a user-invoked skill, and Codex reads `agents/openai.yaml` in the skill folder. Each of these agents leaves `setup` out of its list of skills, so it does not start `setup` from the description. The opt-out does not hide the files. When the skills are installed inside the project, for example with `npx skills add`, Codex and Copilot CLI can find `setup/SKILL.md` while they explore the project, and follow it. Gemini CLI has no per-skill opt-out. A claude.ai upload or the Skills API rejects a skill that has this key, so remove the key from `setup` before you upload it.

## Stability

Releases are tagged, and the plugin carries the same version, so pin a tag if you depend on specific behaviour. While the version is 0.x, breaking changes come only in minor versions, such as 0.2.0 to 0.3.0, and patch versions do not break anything. The [changelog](CHANGELOG.md) marks every breaking change.

## Roadmap

[ROADMAP.md](ROADMAP.md) lists the candidates for future work.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## Licence

MIT. See `LICENSE`.
