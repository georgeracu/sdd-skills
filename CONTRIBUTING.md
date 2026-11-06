# Contributing

Issues are welcome. A good issue shows one observed problem: the skill, the input, what the agent did and what the skill text says it should have done. Paste the relevant part of the artefact the skill wrote.

## Changing a skill

`skills/<name>/SKILL.md` is the single source of truth for what a skill does. The documentation site generates its skill pages from these files, so do not edit `site/src/content/docs/skills/`.

A behaviour change needs evidence from the skill's eval pack in `evals/<name>/`: a run before the change and a run after it, with the results committed under `evals/<name>/results/`. Open an issue before a large change, so that the approach can be agreed first.

The packs live outside `skills/` so that no install copies them. Run them with [skill-eval](https://github.com/bareword/skill-eval) v0.2.0 or later and pass the pack with `--pack`:

```
skill-eval iterate --skill skills/<name> --pack evals/<name> --model <model>
```

Input files in a pack are relative to the pack and must be under its `files/` folder. The workspace is `evals/<name>-workspace/`, which git ignores.

## Checks before a pull request

1. Run `npm ci` and `npm run build` in `site/`. The build fails on a broken link, and on a skill folder with no entry in `site/skills.yaml`.
2. Run `claude plugin validate . --strict` and `claude plugin validate skills --strict` if you changed `.claude-plugin/`, `skills/.claude-plugin/` or added a skill.
3. Add a line to the `Unreleased` section of `CHANGELOG.md`.

## Writing style

British English, direct prose, and no em-dashes.

## Adding a skill

Add the folder under `skills/`, an entry in `site/skills.yaml`, and symlinks in `.claude/skills/sdd/` and `.agents/skills/sdd/` so this repository can use the skill on itself.
