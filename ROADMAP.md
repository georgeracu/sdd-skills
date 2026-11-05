# Roadmap

These are candidates, not commitments. Each one names the gap in the current skills that it would close. The order is not a priority order.

## Skills

### A property-based testing skill

`design.md` already states correctness properties, and `spec-generator` can add optional property-based test tasks, but no skill turns those properties into tests and runs them. A skill between the QA plan and implementation would make the properties executable.

### Cumulative drift detection

`spec-maintenance` compares one new work item with the older ones, so it sees only the impacts that one item caused. It cannot see an old spec that no single change invalidated but several changes did together. Detecting that needs a check across many work items, not one run.

### The audit travels with the work item on merge

Nothing stops a work item from reaching `main` without its `implementation-audit.md`. The candidate is a merge rule, not a skill change: a work item directory merges with its audit, or the branch does not merge.

### An accessibility layer in the test plan

`spec-qa-review` plans five layers: unit, integration, end-to-end, performance and security. No layer covers accessibility.

### Proof that the design review blocks

The eval pack for `spec-design-review` checks that clean designs pass. The next step is a deliberately broken design that must return CRITICAL and block task generation. Related: [#37](https://github.com/georgeracu/sdd-skills/issues/37).

## Documentation site

### A Japanese locale

The site is configured so that a `ja` locale can be added without changing the existing URLs.
