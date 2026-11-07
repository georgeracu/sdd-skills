# Scope

SDD Skills is a file-based spec pipeline for coding agents, with gates that block and an audit trail that stays in the repository. This file states what the project will and will not do, so that a request outside it gets a clear answer.

## In scope

- **The spec pipeline**: discovery, requirements, design, tasks, the test plan, the implementation audit and spec maintenance, as skills that write documents into `project/work-items/`.
- **Gates that block**, with a written status and findings, kept as files in the work item so the audit trail survives the session.
- **The project scaffold** that `setup` copies into a project, and the build and test commands it records.
- **Planned pipeline skills** that close a gap in the pipeline itself: an implementation step, a close-out step and merge check, a router that names the next step, a standards gate, executable correctness properties, and a retrospective drawn from gate history.
- **One-way mirroring to an issue tracker**: a copy of the work item in the tracker, written from the files.
- **Eval packs** that measure each skill against a baseline, with the results committed and shown on the documentation site.
- **Install routes** for agents that read skills, kept so that the skills can still call each other after an install.

## Out of scope

Each item has a file in [`.out-of-scope/`](.out-of-scope/) that says what to use instead.

- [An IDE or editor extension](.out-of-scope/ide-extension.md)
- [A hosted service](.out-of-scope/hosted-service.md)
- [A glossary or ubiquitous-language generator](.out-of-scope/glossary-generator.md)
- [General productivity skills](.out-of-scope/general-productivity-skills.md)
- [An issue tracker as the source of truth](.out-of-scope/tracker-as-source-of-truth.md)

## Proposing a change to this scope

Open an issue that names the gap in the current pipeline the change would close. A request that matches an item above gets a link to its file in `.out-of-scope/`. When a rejected idea comes back with a new reason, the file gets updated rather than a second one.
