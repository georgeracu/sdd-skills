# Quality Checklist

Before finalizing each document, verify against this checklist:

## Requirements / Bugfix Document
- [ ] All user stories have acceptance criteria with EARS notation
- [ ] Edge cases and error scenarios are covered
- [ ] Glossary terms are defined and used consistently
- [ ] No ambiguous or vague requirements ("should", "might", "appropriately")

## Design Document
- [ ] All API endpoints fully specified with request/response schemas
- [ ] Data models include all fields with types and constraints
- [ ] Mermaid diagrams render correctly (architecture, sequence)
- [ ] Correctness properties trace back to requirements
- [ ] Error handling strategy is comprehensive (not just happy path)
- [ ] AWS configurations include all necessary settings (if applicable)
- [ ] Security considerations addressed (auth, input validation, encryption)
- [ ] Performance considerations noted where relevant
- [ ] Consistent with project's existing API patterns (`openapi.yaml`)
- [ ] Uses correct domain terminology (from ubiquitous language)
- [ ] Code examples are syntactically correct in the project's language
- [ ] Implementation-ready level of detail (developers can start coding)

## Tasks Document
- [ ] OpenAPI spec update is the first task (if API changes exist)
- [ ] Every implementation task includes build+test sub-task
- [ ] Unit test tasks exist for new/modified code
- [ ] Integration test tasks exist for cross-component interactions
- [ ] E2E test task exists (in `e2e/` directory)
- [ ] Documentation update task exists
- [ ] Final validation task runs ALL builds and tests
- [ ] Checkpoints every 3-5 tasks
- [ ] All tasks trace back to requirements
- [ ] Task ordering respects dependencies
