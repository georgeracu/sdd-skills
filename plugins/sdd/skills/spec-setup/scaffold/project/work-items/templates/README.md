# SDD Document Templates

Reference templates for every document the SDD pipeline produces. The `sdd:spec-generator` and gate skills already know these formats — these templates exist so humans and agents can see the expected shape at a glance.

## Layout

```
templates/
├── feature/                       # spec-generator outputs (feature pipeline)
│   ├── requirements.md
│   ├── design.md
│   └── tasks.md
├── bugfix/                        # spec-generator outputs (bugfix pipeline)
│   ├── bugfix.md
│   ├── design.md
│   └── tasks.md
└── gates/                         # gate skill outputs
    ├── design-review-summary.md
    ├── task-review-summary.md
    ├── test-plan.md
    ├── implementation-audit.md
    └── spec-maintenance-report.md
```

## How to use

You usually don't copy these manually — `sdd:spec-generator` and the gate skills produce documents in these formats automatically. Use the templates when:

- **Reviewing an artefact** — check whether a generated document is missing a section
- **Starting outside the pipeline** — you have a partial spec from another source and want to bring it into shape
- **Updating skills** — when adjusting a skill's output, the template doubles as a worked example

The skills themselves remain the source of truth. If a template disagrees with the relevant SKILL.md, the skill wins.

## Conventions across all templates

- `[bracketed]` placeholders are filled in when the document is real
- HTML comments (`<!-- ... -->`) explain context — remove them in real documents
- Requirement IDs trace forward: an AC numbered `2.1` in `requirements.md` is referenced as `_Requirements: 2.1_` in `tasks.md` and tracked per-AC in `implementation-audit.md`
- Severity in gate outputs uses CRITICAL / MAJOR / MINOR for gate reviews; QA gap log uses Critical / Important / Minor
