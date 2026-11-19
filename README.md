[![Docs](https://github.com/georgeracu/sdd-skills/actions/workflows/docs.yml/badge.svg)](https://github.com/georgeracu/sdd-skills/actions/workflows/docs.yml)

# SDD Skills

Agent skills for spec-driven development: EARS requirements, a reviewed design, traced tasks and an audited implementation, with quality gates that block and an audit trail that stays in the repository.

**Documentation:** https://georgeracu.com/sdd-skills/

## Install

**Claude Code** is the tested route: the evals run against it.

```
claude plugin marketplace add georgeracu/sdd-skills
claude plugin install sdd@georgeracu
```

**Agent Plugins 1.0 clients** can install `plugins/sdd` as a plugin. I smoke-tested two clients on a scratch repository, and each started `spec-discovery`, which asked its first question.

| Client | Version tested | Install | Skill names |
|---|---|---|---|
| Codex CLI | 0.170.0 | `git clone https://github.com/georgeracu/sdd-skills.git`, then `codex plugin marketplace add ./sdd-skills` and `codex plugin add sdd@georgeracu` | `sdd:spec-discovery` |
| GitHub Copilot CLI | 1.0.107 | `copilot --plugin-dir ./sdd-skills/plugins/sdd`, for one session | `spec-discovery` |

Cursor, Kiro, VS Code and ChatGPT are untested. `copilot plugin install georgeracu/sdd-skills:plugins/sdd` is also untested.

**Any other Agent Skills client** can use `npx skills`. It lists all fourteen skills from this repository, and I have not run them in those clients, such as Gemini CLI, pi or Grok Build.

```
npx skills@latest add georgeracu/sdd-skills
```

**Manual copy** keeps the `sdd:` prefix in Claude Code:

```
git clone https://github.com/georgeracu/sdd-skills.git
mkdir -p .claude/skills/sdd
rsync -a sdd-skills/plugins/sdd/skills/ .claude/skills/sdd/
```

Choose one route per agent. Two routes install every skill twice.

The skills call each other as `sdd:<name>`, such as `sdd:spec-generator`. That prefix survives only in a plugin install in Claude Code and Codex CLI, and in the manual copy above. Copilot CLI and `npx skills` list the skills by their bare names, such as `spec-generator`, and I did not run a hand-off between skills there.

Then run `/sdd:spec-setup` once in your project. In Copilot CLI and `npx skills` installs, run `/spec-setup`. It copies the work-item scaffold, records your build and test commands in `AGENTS.md` and reports the optional inputs it found. The feature pipeline runs from discovery to the pull request with these skills alone. The bugfix pipeline implements its tasks with the [superpowers](https://github.com/obra/superpowers) plugin or by hand; [Getting started](https://georgeracu.com/sdd-skills/getting-started/) describes both.

## Who it's for

Teams that need an audit trail and gates that block. Every work item keeps its requirements, design, review verdicts, test plan and an implementation audit backed by pasted build and test output, as files in `project/work-items/`. The design review, the task review and the implementation audit each return a blocking status.

## The pipeline

| Step | Skill | Kind | Writes |
|---|---|---|---|
| Once | `spec-setup` | user-invoked | Scaffold, `AGENTS.md` commands |
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

`spec-workflow` is the reference for this order and for the bugfix pipeline. Its kind is reference: the agent reads it, and it does no work of its own. The [worked example](https://georgeracu.com/sdd-skills/worked-example/) follows one real feature through every step.

You start a user-invoked skill yourself, for example with `/sdd:spec-setup`. The agent starts a model-invoked skill when the pipeline reaches it. Claude Code reads the `disable-model-invocation` key that marks a user-invoked skill, and Codex reads `agents/openai.yaml` in the skill folder. I tested the opt-out in Codex CLI 0.170.0 and Copilot CLI 1.0.114 with three fresh sessions each, for `spec-setup` and for `domain-map`. In none of the twelve sessions did the agent start either skill from its description. Given "prepare this project for spec-driven development", Copilot CLI started `spec-workflow`, and Codex CLI read `spec-workflow` and then `spec-setup/SKILL.md` from the plugin folder, which `spec-workflow` points to. Given "set up the domain model for this repository", both started `domain-define`. Asked to call `spec-setup` or `domain-map` by name, the Copilot CLI skill tool refuses with "cannot be invoked by the model because disable-model-invocation is enabled". So the opt-out stops a client from starting the skill from its description, but it does not hide the files: an agent that reads the plugin folder can still follow `spec-setup/SKILL.md`, as Codex did in all three sessions. Codex CLI has no skill tool to probe, so the test shows what it did and not why. Gemini CLI has no per-skill opt-out. A claude.ai upload or the Skills API rejects a skill that has this key, so remove the key from `spec-setup` before you upload it. The Agent Skills reference validator, `skills-ref`, reports the key as an unexpected field, and Codex CLI 0.170.0 and Copilot CLI 1.0.107 load the skill anyway.

## Domain skills

| Skill | Kind | Writes |
|---|---|---|
| `domain-map` | user-invoked | First `project/domain/**`, imports of older glossaries |
| `domain-define` | model-invoked | New terms in `project/domain/**`, as proposed |
| `domain-maintenance` | model-invoked | `domain-maintenance-report.md`, promotions and deprecations in `project/domain/**` |

They keep a domain model in `project/domain/`, and the pipeline does not start them yet.

## Stability

Releases are tagged, and the plugin carries the same version, so pin a tag if you depend on specific behaviour. While the version is 0.x, breaking changes come only in minor versions, such as 0.2.0 to 0.3.0, and patch versions do not break anything. The [changelog](CHANGELOG.md) marks every breaking change.

## Roadmap

[ROADMAP.md](ROADMAP.md) lists the candidates for future work.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## Licence

MIT. See `LICENSE`.
