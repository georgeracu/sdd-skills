# AGENTS.md

How this repository works, for a person or an agent about to change it. Terms in bold are defined in [GLOSSARY.md](GLOSSARY.md).

## Layout

| Path | What it holds |
|---|---|
| `skills/<name>/SKILL.md` | The skills. Each `SKILL.md` is the single source of truth for what that skill does. Some skills also have a `references/` folder, and `setup` has the `scaffold/` it copies into a project. |
| `skills/.claude-plugin/plugin.json` | The Claude Code plugin manifest. It holds the only copy of the version. `skills/` is the plugin root, so an install copies only the skills. |
| `.claude-plugin/marketplace.json` | The marketplace entry. Its plugin source is `./skills`, and it has no version of its own. |
| `evals/<name>/` | The **eval pack** for one skill: `evals.json`, the input files under `files/`, and the committed results under `results/`. The packs live outside `skills/` so that no install copies them. |
| `.claude/skills/sdd/`, `.agents/skills/sdd/` | Real directories that hold one symlink per skill, pointing to `skills/<name>`, so an agent opening this repository can use the skills on the repository itself. |
| `examples/38-source-fidelity-and-accuracy-sla/` | A real **work item** that went through the pipeline. The site's worked example uses it. |
| `site/` | The documentation site (Astro and Starlight). Its skill pages and its Evals page are generated, so do not edit `site/src/content/docs/skills/` or `site/src/content/docs/evals.md`. |
| `ROADMAP.md`, `CHANGELOG.md` | Candidate work, and the record of each release. The site renders both. |

### The `sdd:` namespace

The skills call each other by their namespaced names, such as `sdd:spec-generator` and `sdd:spec-design-review`. The plugin name `sdd` gives that prefix to a plugin install, and the manual copy into `.claude/skills/sdd/` keeps it. Write every cross-reference between skills with the `sdd:` prefix, or the call does not resolve. The two superpowers skills the pipeline uses, `subagent-driven-development` and `finishing-a-development-branch`, have no prefix.

## Changing a skill

A change to `skills/<name>/SKILL.md`, or to a file it reads, also needs:

1. **Evidence from the skill's eval pack**, for a change in behaviour: an **iteration** before the change and one after it, with both results committed under `evals/<name>/results/`. See [Running the evals](#running-the-evals).
2. **A line in the `Unreleased` section of `CHANGELOG.md`.** Mark a breaking change with **Breaking**, such as a changed `**Status:**` value that other tools read.
3. **`site/skills.yaml`**, when you add, rename or remove a skill, or change its pipeline position. The site build fails when a skill folder has no entry.
4. **A symlink in `.claude/skills/sdd/` and in `.agents/skills/sdd/`**, when you add a skill.
5. **A kind in the front matter**, when you add a skill. See [Choosing a kind](#choosing-a-kind).

`setup` and `spec-workflow` have no eval pack. A change to either needs a manual check instead: run the changed skill in a scratch project and paste what it did into the pull request.

Keep wording changes to the smallest unit that changes. The eval results and the site link to lines in these files.

### Choosing a kind

Every `SKILL.md` records its kind in `metadata.sdd-kind`. The site shows the kind as a badge, and the build fails when a skill has no kind. Choose the kind as follows:

- **user-invoked**, when no skill, scaffold doc or pipeline instruction tells the agent to start the skill. Add `disable-model-invocation: true` for Claude Code and Copilot, and `agents/openai.yaml` with `policy.allow_implicit_invocation: false` for Codex.
- **reference**, when the agent reads the skill for rules and the skill does no work of its own.
- **model-invoked**, for all other skills. Add no invocation key.

Before you mark a skill user-invoked, search for `sdd:<name>` in `skills/` and `evals/<name>/evals.json`. Claude Code blocks every call from the agent to a user-invoked skill, so each of those references stops working.

## Running the evals

The packs run with [skill-eval](https://github.com/bareword/skill-eval) v0.2.0 or later. It needs Python 3.10 or later and the `claude` CLI. Install it from a checkout:

```bash
git clone --branch v0.2.0 https://github.com/bareword/skill-eval.git ../skill-eval
python3 -m venv ../skill-eval/.venv
../skill-eval/.venv/bin/pip install -e ../skill-eval
```

The commands below assume `skill-eval` is on your `PATH`, for example after `export PATH="$PWD/../skill-eval/.venv/bin:$PATH"`. skill-eval also installs as a Claude Code plugin; see its README.

Every command takes the skill and its pack. `validate` and `preflight` take the skill path as a positional argument; the other commands take `--skill`.

```bash
skill-eval validate skills/<name> --pack evals/<name>
skill-eval preflight skills/<name> --pack evals/<name>
```

`validate` checks `evals.json`. `preflight` reports the pack state as JSON, including the next iteration number, and creates the workspace `evals/<name>-workspace/`, which git ignores.

### Choosing the baseline

Each iteration runs every case in two **arms**: one with the skill, and a **baseline** without it.

- `--baseline none` (the default) runs the baseline with no skill. It measures the skill against having nothing.
- `--baseline snapshot` runs the baseline with an earlier copy of the skill. It measures whether a change helped. Take the snapshot **before** you edit the skill:

  ```bash
  skill-eval snapshot --skill skills/<name> --pack evals/<name>
  ```

### Before a paid run

`iterate` calls the model for every case, every arm and every run, and a judge model for the checks that need judgement. It prints a spend estimate and then starts at once, with no confirmation. So work out the size of the run first, and get the maintainer's agreement on it:

1. Read the number of cases from `evals/<name>/evals.json`.
2. Multiply: cases × 2 arms × `--runs`. That is the number of runner calls.
3. Read the token figures from the last committed `evals/<name>/results/iteration-N.json` to estimate the cost.
4. State the number of runs and the estimated cost, and wait for a yes.

Then run:

```bash
skill-eval iterate --skill skills/<name> --pack evals/<name> --model <model> --runs <n> \
  --baseline none --note "<what this iteration tests>"
```

`iterate` writes `evals/<name>/results/iteration-N.md` and `iteration-N.json`. Commit both. Each iteration gets the next unused number, so a new run never overwrites a committed one.

## Building the site and checking the plugin

```bash
cd site
npm ci
npm run build
```

`npm run build` first runs `npm run sync`, which generates the skill pages from `skills/*/SKILL.md` and the Evals page from `evals/*/results/`. The build fails on a broken link, and on a skill folder with no entry in `site/skills.yaml`. The Evals page dates each result from git history, so the build refuses to run in a shallow clone. Use `npm run dev` to preview locally.

Check both manifests after a change to either, or after you add a skill:

```bash
claude plugin validate . --strict
claude plugin validate skills --strict
```

The first command checks the marketplace, and the second checks the plugin.

## Releasing

The version lives only in `skills/.claude-plugin/plugin.json`. To release version `X.Y.Z`:

1. In a pull request, set `"version": "X.Y.Z"` in `skills/.claude-plugin/plugin.json`.
2. In the same pull request, rename `## [Unreleased]` in `CHANGELOG.md` to `## [X.Y.Z] - YYYY-MM-DD`, add a new empty `## [Unreleased]` above it, and update the compare links at the bottom of the file.
3. The `Release` workflow runs on any pull request that changes the manifest, `CHANGELOG.md` or the workflow. It fails when the manifest version has no `## [X.Y.Z] - ` section.
4. After the merge, tag the merge commit on `main` as `vX.Y.Z` and push the tag. The workflow checks that the tag matches the manifest version and publishes a GitHub release with that CHANGELOG section as the notes.

While the version is 0.x, a minor version can contain breaking changes and a patch version does not.

## Style

- British English, direct prose, and no em-dashes in new text. Some older skill text still has em-dashes; do not copy them.
- Use the vocabulary in [GLOSSARY.md](GLOSSARY.md). In particular, keep each gate's **severity** labels (CRITICAL, MAJOR, MINOR) and **status** values exactly as that gate's `SKILL.md` spells them, because later steps read the `**Status:**` line.
- In a skill, write instructions to the agent in the imperative, and name the file the agent writes.

## Contributing

Issues and pull requests follow [CONTRIBUTING.md](CONTRIBUTING.md).
