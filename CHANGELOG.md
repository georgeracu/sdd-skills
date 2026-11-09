# Changelog

Every notable change to the skills is recorded here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project uses [Semantic Versioning](https://semver.org/spec/v2.0.0.html). While the version is 0.x, a minor version can contain breaking changes and a patch version does not.

## [Unreleased]

### Added

- `sdd:spec-finish`, step 12 of the feature pipeline and step 3 of the bugfix pipeline. It checks the exact `**Status:**` line of every gate, recomputes the `spec-maintenance` peer rule when there is no maintenance report, stops when the tree has uncommitted files, and runs the `AGENTS.md` build and test commands. Then it writes and commits `pr.md` in the work item, with the gate table, the test evidence and the commit it ran on, and opens the pull request with `gh` or `glab` when one is available. It never merges, never force-pushes and never pushes to the default branch.
- An optional GitHub Actions merge check, `sdd-work-item-check.yml`, that `/sdd:setup` offers to install. It fails a pull request that changes a feature work item without a PASSED `implementation-audit.md`, or a bugfix work item without `bugfix.md`, `design.md` and `tasks.md`. This was the roadmap item "The audit travels with the work item on merge".
- An eval pack for `spec-finish`.

### Changed

- `spec-maintenance`, `spec-workflow`, the scaffold docs and the maintenance report template hand over to `sdd:spec-finish` instead of superpowers' `finishing-a-development-branch`, which stays as an alternative. The bugfix pipeline now ends with `sdd:spec-finish` too. A feature no longer needs superpowers at all.
- The `spec-maintenance` eval pack expects the hand-over to `sdd:spec-finish`.

## [0.4.0] - 2026-11-08

### Added

- Contributor docs: `AGENTS.md` (with a `CLAUDE.md` that imports it) for the layout, changing a skill, running the evals, building the site and releasing, and `GLOSSARY.md` for the pipeline and eval terms. `CONTRIBUTING.md` points to them and to the two issue forms.
- Each skill records its kind (user-invoked, model-invoked or reference) in `metadata.sdd-kind`. The site shows the kind on each skill page and as a sidebar badge, and the README pipeline table has a Kind column. `AGENTS.md` gives the rule for choosing a kind.
- `setup` has `agents/openai.yaml`, so Codex leaves it out of its list of skills.
- `sdd:spec-implement`, step 8 of the feature pipeline. It checks that the gates and the test plan are in place, runs each task of `tasks.md` in a fresh subagent in task order, verifies it with the `AGENTS.md` build and test commands, and makes one commit per task with the tick in it. On a second failure of a task it stops and asks. It then hands over to `sdd:spec-implementation-audit`. A feature no longer needs superpowers before step 12.
- An eval pack for `spec-implement`. Its cases run a build and tests, so it needs skill-eval v0.3.0 or later, which reads the pack's `runner-settings.json`.

### Changed

- `spec-qa-review` plan mode, `spec-workflow` step 8 and `spec-implementation-audit` now name `sdd:spec-implement` as the implementation step. `subagent-driven-development` stays as an alternative, with the same override rules. No existing eval pack checks these next-step lines.
- `spec-generator` Step 8 starts with a line that invokes `sdd:spec-implement` for a feature work item. Its execution rules stay for bugfix work items and for implementation without the skill.
- The README, the getting-started page, the scaffold docs and the templates say that superpowers is needed only to open the pull request at step 12, and for bugfix implementation.

## [0.3.0] - 2026-11-07

### Added

- Evals page on the documentation site, generated from the committed results: the latest iteration for each skill, its baseline, the pass rates, the run-to-run spread and the checks that scored below the baseline. Each skill page links to its row.
- A release workflow. A pull request that changes the plugin manifest or `CHANGELOG.md` fails when the manifest version has no CHANGELOG section, and a `v*` tag that matches the manifest publishes a GitHub release with that section as the notes.
- Issue forms for a bug report and for a skill that behaved unexpectedly.
- A social preview image, also used as the site's `og:image`.

### Changed

- The eval packs moved from `skills/<name>/evals/` to `evals/<name>/`, and the plugin root is now `skills/`. A plugin install or an `npx skills` install now copies only the skills, without the eval packs, the site or the examples. Run a pack with skill-eval v0.2.0 or later and `--pack evals/<name>`.
- The manual-copy install command leaves out the plugin manifest in `skills/.claude-plugin/`.
- The site's home page has a searchable title, a sourced Kiro column dated November 2026, links to the superpowers skills in steps 8 and 12, and a star count only from 50 stars. The theme credits are in the site footer.
- GitHub's language bar no longer counts the Kotlin eval fixtures.

## [0.2.0] - 2026-11-05

Breaking changes are marked **Breaking**. A gate's status values changed, so update anything that reads a `**Status:**` line.

### Added

- Claude Code plugin `sdd` in the `georgeracu` marketplace: `claude plugin marketplace add georgeracu/sdd-skills`, then `claude plugin install sdd@georgeracu`.
- `sdd:setup`, which copies the scaffold into a project, records the build and test commands in `AGENTS.md` and reports the optional inputs it found.
- Documentation site at https://georgeracu.github.io/sdd-skills/, with a page for each skill, the worked example, this changelog and an RSS feed.
- `ROADMAP.md` and `CONTRIBUTING.md`.
- `spec-implementation-audit` checks every correctness property in `design.md` against the code that enforces it and the test that exercises it.

### Changed

- **Breaking:** the scaffold moved from `scaffold/` to `skills/setup/scaffold/`, so every install route carries it. Run `/sdd:setup` instead of copying it by hand.
- The five review skills share one verdict model: a single `**Status:**` line, a status that follows the highest severity, and a second blocking run that asks the user how to continue (#24).
- **Breaking:** `spec-design-review` renames the status APPROVED WITH MINOR ISSUES to APPROVED WITH ISSUES. Every finding names its source, identifier drift is MAJOR, and an unsupported latency criterion, a data exposure or an auth bypass is CRITICAL (#25).
- `spec-task-review`: a trace from a test task alone does not cover a criterion, sequencing names the artefact and the tasks that create and use it, and `discovery.md` is deleted on both passing verdicts (#26).
- **Breaking:** `spec-qa-review` uses the `**Status:**` line for PASSED or FAILED, and moves the lifecycle value (Draft, Active, Complete) to a `**Stage:**` line. Unit coverage is gated on line and branch coverage at 85% each, and the severity scale is CRITICAL, MAJOR and MINOR (#27).
- **Breaking:** `spec-implementation-audit` returns FAILED on any CRITICAL or MAJOR finding, where the earlier version did not say which severities fail, and stops after writing a FAILED report instead of fixing the findings. Evidence blocks hold the command and its verbatim output, and only a ticked box completes a task (#28).
- **Breaking:** `spec-maintenance` counts a work item as implemented only when its `implementation-audit.md` says `**Status:** PASSED`, where any audit file counted before, and the current item's own audit no longer counts. A run with no such peers writes no report. Annotations take one shape, and the drift warning counts one impact per bullet (#29).
- The README replaces "no promise of support" with a stability promise.

## [0.1.3] - 2026-10-20

### Added

- `sdd:spec-discovery`, which agrees the problem, scope and approach with the user and writes `discovery.md`. It replaces the dependency on superpowers' `brainstorming` skill for step 1.
- `scaffold/`, the `project/work-items/` layout with process documents and templates, ready to copy into a project.
- The spec 38 worked example in `examples/`, with a README that maps each artefact to the skill that produced it.
- Eval packs for the five gate skills.

### Changed

- The skills are stack-neutral: they read the build, test and type-generation commands from `AGENTS.md` instead of assuming Gradle or npm.

### Fixed

- `spec-generator` creates `project/work-items/` when it is absent.
- `spec-discovery` skips questions the user has already answered.

## [0.1.2] - 2026-09-07

### Changed

- `spec-generator`: the bugfix template enumerates the neighbouring behaviours that must not change (#14), and the path after a BLOCKED design review drafts the fixes (#13).
- The `spec-generator` eval pack grows to nine cases with per-case criteria.

## [0.1.1] - 2026-09-07

### Added

- The first eval pack for `spec-generator`.

## [0.1.0] - 2026-09-07

### Added

- Seven skills extracted from a private project: `spec-workflow`, `spec-generator`, `spec-design-review`, `spec-task-review`, `spec-qa-review`, `spec-implementation-audit` and `spec-maintenance`.

[Unreleased]: https://github.com/georgeracu/sdd-skills/compare/v0.4.0...HEAD
[0.4.0]: https://github.com/georgeracu/sdd-skills/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/georgeracu/sdd-skills/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/georgeracu/sdd-skills/compare/v0.1.3...v0.2.0
[0.1.3]: https://github.com/georgeracu/sdd-skills/compare/v0.1.2...v0.1.3
[0.1.2]: https://github.com/georgeracu/sdd-skills/compare/v0.1.1...v0.1.2
[0.1.1]: https://github.com/georgeracu/sdd-skills/compare/v0.1.0...v0.1.1
[0.1.0]: https://github.com/georgeracu/sdd-skills/releases/tag/v0.1.0
