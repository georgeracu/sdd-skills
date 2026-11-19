# Context map patterns

Use these exact names in the `Pattern` column of `context-map.md`. They are the relationship patterns from Eric Evans' *Domain-Driven Design* and his *DDD Reference* (CC BY 4.0, domainlanguage.com/ddd/reference), restated briefly. Pick the pattern that describes how the two teams and models actually relate today, not the one you would like.

| Pattern | What it means | Recommend when | Shape in code |
|---|---|---|---|
| Partnership | Two contexts succeed or fail together, and their teams plan changes jointly | Two teams ship one capability and talk every week | Coordinated releases; contract tests on both sides |
| Shared Kernel | Two contexts share a small, explicitly owned part of the model | The shared part is small, stable and both teams accept a joint change process | A shared module with its own owners; keep it tiny |
| Customer-Supplier | The upstream team takes the downstream team's needs into its planning | The downstream depends on the upstream and the upstream will negotiate | Consumer-driven contract tests run in the upstream build |
| Conformist | The downstream adopts the upstream model as it is | The upstream will not change for you and its model is good enough | The downstream uses the upstream types directly |
| Anticorruption Layer | The downstream translates the upstream model into its own | The upstream model is poor, legacy or external, and would leak into yours | An adapter package that owns all translation; no upstream types beyond it |
| Open Host Service | The upstream offers one published protocol for all its consumers | Several downstreams need the same capability | A versioned API or event stream, designed for consumers |
| Published Language | A documented exchange format both sides use | Usually with Open Host Service; the format outlives either model | An OpenAPI, AsyncAPI, Avro or JSON Schema document under version control |
| Separate Ways | The contexts do not integrate at all | Integration costs more than duplication | No row in the map is needed; record the decision in `CHANGELOG.md` |
| Big Ball of Mud | A part of the system with no clear model boundary | Mapping an existing system honestly | Draw a boundary around it and protect new contexts with an Anticorruption Layer |

## Choosing

- Name the pattern from the downstream side's point of view: what does the downstream team have to do to use the upstream?
- Open Host Service and Published Language often appear together. Record the one that matters most in the row, and the other in `Messages` (for example, `PolicyIssued (event, Avro schema)`).
- External systems get a row with an empty `Code location` in the `Contexts` table. Most of them deserve an Anticorruption Layer; a stable, well-designed external API can be Conformist.
- Two contexts that depend on each other in both directions are a finding, not a pattern. Unless the relationship is Partnership or Shared Kernel, raise it with the user.

## Further reading

- Eric Evans, *Domain-Driven Design Reference*, section "Context Mapping for Strategic Design".
- ddd-crew, *Context Mapping* (github.com/ddd-crew/context-mapping), for the team-relationship view.
- Vlad Khononov, *Learning Domain-Driven Design*, chapter 4, for choosing between the patterns.
