# Roadmap

These are candidates, not commitments. Each one names the gap in the current skills that it would close. The order is not a priority order.

## Skills

### A property-based testing skill

`design.md` already states correctness properties, and `spec-generator` can add optional property-based test tasks, but no skill turns those properties into tests and runs them. A skill between the QA plan and implementation would make the properties executable.

### Start the domain skills outside Claude Code

The plugin hook starts `domain-define` and `domain-maintenance` in Claude Code only. Codex CLI 0.170.0 and Copilot CLI 1.0.114 did not run it, so in those clients an agent has only the Triggers section of `spec-workflow`, and no eval measures whether it follows that section.

### Cumulative drift detection

`spec-maintenance` compares one new work item with the older ones, so it sees only the impacts that one item caused. It cannot see an old spec that no single change invalidated but several changes did together. Detecting that needs a check across many work items, not one run. The repo-wide mode of `domain-maintenance` covers drift in domain language and context dependencies, not in the rest of the spec.

### An accessibility layer in the test plan

`spec-qa-review` plans five layers: unit, integration, end-to-end, performance and security. No layer covers accessibility.

### Clean designs reach APPROVED

The eval pack for `spec-design-review` already shows that broken designs return BLOCKED. The open gap is the other direction: a design with no real defect must reach APPROVED. The aligned clean-pass case now does, but the bugfix and prose-drift cases still return APPROVED WITH ISSUES. See [#37](https://github.com/georgeracu/sdd-skills/issues/37).

### Audit findings as tasks

When `spec-implementation-audit` returns FAILED, the caller fixes the findings by hand or with its own subagents. `spec-implement` could take the findings as its task list and fix them under the same verify and two-strike rules.

## Documentation site

### A Japanese locale

The site is configured so that a `ja` locale can be added without changing the existing URLs.
