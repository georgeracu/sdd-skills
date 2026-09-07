# Review Criteria Reference

## Requirements Coverage Checklist

For each requirement in requirements.md:

- [ ] At least one component in design.md addresses this requirement
- [ ] All acceptance criteria have corresponding design elements
- [ ] Correctness properties trace back to requirements
- [ ] Edge cases from requirements are handled in the design

## Architecture Consistency Checklist

- [ ] Component patterns match existing codebase (handler/service/repository)
- [ ] Package/module structure follows existing conventions
- [ ] Infrastructure choices align with existing stack
- [ ] Naming conventions match existing code
- [ ] Error handling follows established patterns

## API Design Checklist

- [ ] All endpoints have request/response schemas
- [ ] HTTP methods and paths follow REST conventions
- [ ] Error responses match existing format
- [ ] Authentication requirements specified
- [ ] Consistent with existing openapi.yaml patterns

## Data Model Checklist

- [ ] Field names follow existing naming conventions
- [ ] DynamoDB table design follows existing patterns
- [ ] Entity terminology matches glossary and codebase
- [ ] No unnecessary data duplication
- [ ] Indexes support all access patterns

## Security Checklist

- [ ] Authentication specified for protected endpoints
- [ ] Authorization checks included where needed
- [ ] Input validation at system boundaries
- [ ] No PII exposure in logs or unnecessary storage
- [ ] IAM permissions follow least privilege

## Common Discrepancy Patterns

### Pattern 1: Design Doesn't Cover a Requirement

**Symptoms:**
- Acceptance criterion exists in requirements.md
- No component, interface, or data model in design.md addresses it

**Root cause:** Design author overlooked a requirement

**Fix:** Add design elements to cover the missing requirement

### Pattern 2: Architecture Mismatch

**Symptoms:**
- Design uses patterns not found in existing codebase
- Class names, package structures, or framework choices differ from existing code

**Root cause:** Design author unfamiliar with existing conventions

**Fix:** Align design with existing patterns by referencing actual codebase examples

### Pattern 3: Incomplete API Specification

**Symptoms:**
- Endpoint mentioned but request/response not fully specified
- Missing error responses or status codes

**Root cause:** Design focused on happy path only

**Fix:** Add complete API specification including error scenarios

### Pattern 4: Data Model Drift

**Symptoms:**
- Design uses different field names than existing codebase
- New entity doesn't reference existing related entities correctly

**Root cause:** Terminology not aligned with existing code

**Fix:** Align naming with existing codebase conventions and glossary

### Pattern 5: Missing Security Considerations

**Symptoms:**
- New endpoints without authentication specification
- Sensitive data stored without encryption or access controls

**Root cause:** Security not considered during design

**Fix:** Add security specifications following existing patterns
