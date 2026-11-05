# Changelog

Every notable change to the skills is recorded here. The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project uses [Semantic Versioning](https://semver.org/spec/v2.0.0.html). While the version is 0.x, a minor version can contain breaking changes and a patch version does not.

## [Unreleased]

### Added

- Claude Code plugin `sdd` in the `georgeracu` marketplace: `claude plugin marketplace add georgeracu/sdd-skills`, then `claude plugin install sdd@georgeracu`.
- `sdd:setup`, which copies the scaffold into a project, records the build and test commands in `AGENTS.md` and reports the optional inputs it found.
- Documentation site at https://georgeracu.github.io/sdd-skills/, with a page for each skill, the worked example, this changelog and an RSS feed.
- `ROADMAP.md` and `CONTRIBUTING.md`.
- `spec-implementation-audit` checks every correctness property in `design.md` against the code that enforces it and the test that exercises it.

### Changed

- The five review skills share one verdict model: a single `**Status:**` line, a status that follows the highest severity, and a second blocking run that asks the user how to continue (#24).
- `spec-design-review`: adds APPROVED WITH ISSUES, every finding names its source, identifier drift is MAJOR, and an unsupported latency criterion, a data exposure or an auth bypass is CRITICAL (#25).
- `spec-task-review`: a trace from a test task alone does not cover a criterion, sequencing names the artefact and the tasks that create and use it, and `discovery.md` is deleted on both passing verdicts (#26).
- `spec-qa-review`: review mode sets PASSED or FAILED, unit coverage is gated on line and branch coverage at 85% each, and the severity scale is CRITICAL, MAJOR and MINOR (#27).
- `spec-implementation-audit`: FAILED on any CRITICAL or MAJOR finding, evidence blocks hold the command and its verbatim output, only a ticked box completes a task, and the audit stops after writing a FAILED report (#28).
- `spec-maintenance`: the peer set is every other work item with a PASSED audit, a run with no peers writes nothing, annotations take one shape, and the drift warning counts one impact per bullet (#29).
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

[Unreleased]: https://github.com/georgeracu/sdd-skills/compare/v0.1.3...HEAD
[0.1.3]: https://github.com/georgeracu/sdd-skills/compare/v0.1.2...v0.1.3
[0.1.2]: https://github.com/georgeracu/sdd-skills/compare/v0.1.1...v0.1.2
[0.1.1]: https://github.com/georgeracu/sdd-skills/compare/v0.1.0...v0.1.1
[0.1.0]: https://github.com/georgeracu/sdd-skills/releases/tag/v0.1.0
