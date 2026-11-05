# Work Items

This directory contains all Spec-Driven Development (SDD) work items for this project — features, bug fixes, and the documentation that describes how the pipeline works.

## Layout

```
project/work-items/
├── README.md                # This file
├── {your-backlog-file}.md   # Optional: a project may keep its own backlog file here (name not fixed)
├── spec-documentation/      # SDD pipeline documentation (start here)
├── templates/               # Document templates for every artefact
└── {NN}-{name}/             # Individual work item directories
    ├── requirements.md      # or bugfix.md
    ├── design.md
    ├── tasks.md
    └── ...                  # gate summaries, test plan, audit, etc.
```

## Where to start

| If you want to... | Read |
|---|---|
| Understand the SDD workflow | [`spec-documentation/README.md`](spec-documentation/README.md) |
| Keep a backlog | A project may keep its own backlog file here; the name isn't fixed |
| Build a new feature | [`spec-documentation/Feature-pipeline.md`](spec-documentation/Feature-pipeline.md) |
| Fix a bug | [`spec-documentation/Bugfix-pipeline.md`](spec-documentation/Bugfix-pipeline.md) |
| Look up an artefact | [`spec-documentation/Artifacts.md`](spec-documentation/Artifacts.md) |
| Know which agent owns each step | [`spec-documentation/Agent-roles.md`](spec-documentation/Agent-roles.md) |
| Understand quality gates | [`spec-documentation/Quality-gates.md`](spec-documentation/Quality-gates.md) |
| See the shape of an output document | [`templates/`](templates/README.md) |

## Work item naming

Sequential two-digit prefix + kebab-case slug:

```
01-user-preferences-sync
05-lambda-cold-start-optimization
27-line-login
```

Numbers are assigned in order of creation.

## Source of truth

The canonical pipeline definition is the `sdd:spec-workflow` skill at `.claude/skills/sdd/spec-workflow/SKILL.md`. The documents in `spec-documentation/` explain the pipeline but defer to the skill if anything disagrees.

For the full skills inventory, see `.claude/skills/sdd/`.
