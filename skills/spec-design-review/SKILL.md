---
name: spec-design-review
description: Use after design.md is approved in the spec-generator pipeline and before tasks.md is written, or when asked to review or validate a design against requirements.md or bugfix.md. Writes design-review-summary.md and blocks task generation on critical findings.
---

# Spec Design Review - Quality Gate

Validate alignment between business requirements and technical design to ensure the proposed solution is architecturally sound, consistent with the existing system, and follows best practices.

**Workflow Position:** Runs AFTER design.md is approved by the user and BEFORE tasks.md is generated.

```
sdd:spec-generator Phase 1 → sdd:spec-generator Phase 2 (design.md) → sdd:spec-design-review → sdd:spec-generator Phase 3 (tasks.md)
```

## Prerequisites

Before running this review, verify these files exist:

1. **requirements.md** or **bugfix.md** (Phase 1 output from spec-generator)
   - Location: `project/work-items/{NN}-{name}/requirements.md` or `bugfix.md`
   - Contains: User stories, acceptance criteria, glossary

2. **design.md** (Phase 2 output from spec-generator)
   - Location: `project/work-items/{NN}-{name}/design.md`
   - Contains: Architecture, components, data models, correctness properties

3. **Existing codebase** (for architecture consistency checks)
   - Key files to reference for architectural patterns (read as needed during review)

## Review Process

### Step 1: Read Input Documents

Read requirements.md (or bugfix.md) and design.md from the work item directory.

### Step 2: Gather Architectural Context

Before reviewing, gather context about the existing system by reading relevant files based on what the design touches. This is **not** an exhaustive audit — read only what's needed to validate the design's architectural decisions.

Read ARCHITECTURE.md, the project glossary and the API contract, then the existing code that each part of the design touches.

### Step 3: Perform Comprehensive Review

Check alignment across these six critical dimensions:

#### 3.1 Requirements Coverage

**Check:** Every requirement/acceptance criterion in requirements.md has corresponding design support.

**Validation:**
- Extract all requirements and their acceptance criteria
- For each acceptance criterion, verify design.md includes components, interfaces, or data models that enable it
- Verify correctness properties trace back to requirements (`**Validates: Requirements N.N**`)
- Identify any requirements without design coverage

#### 3.2 Architecture Consistency

**Check:** The proposed design is consistent with the existing system architecture.

**Validation:**
- Compare proposed components with existing architectural patterns
- Verify naming conventions match existing code (package names, class names, file locations)
- Check that new services follow the same patterns as existing ones (e.g., handler → service → repository)
- Verify infrastructure choices align with existing stack (e.g., DynamoDB, Lambda, S3)
- Ensure proposed API paths follow existing conventions

#### 3.3 API Design Quality

**Check:** All proposed APIs are well-designed, complete, and consistent with existing APIs.

**Validation:**
- Request/response schemas are fully defined with types
- HTTP methods and status codes follow REST conventions
- Error responses follow the existing error format
- Authentication/authorization requirements are specified
- API paths follow existing naming conventions (e.g., `/api/v1/...`)
- New endpoints are consistent with `openapi.yaml` patterns

#### 3.4 Data Model Consistency

**Check:** Proposed data models are consistent with existing models and follow established patterns.

**Validation:**
- Database schema designs follow existing patterns (key naming, index conventions)
- Entity names use consistent terminology with the glossary and existing codebase
- Data types match existing conventions (e.g., ISO 8601 dates, enum formats)
- Relationships between new and existing entities are clear
- No duplicate storage of data that already exists in another table

#### 3.5 Security & Best Practices

**Check:** The design follows security best practices and established security patterns.

**Validation:**
- Authentication is specified for all endpoints that need it
- Authorization checks are included where needed
- Input validation is defined at system boundaries
- No sensitive data exposure in logs or responses
- OWASP top 10 considerations addressed where relevant
- Cloud IAM permissions follow least privilege principle

#### 3.6 Testing Strategy Completeness

**Check:** The testing strategy covers all requirements and follows existing test patterns.

**Validation:**
- Unit tests cover core business logic
- Integration tests cover component interactions
- Property-based tests align with correctness properties
- Test file locations follow existing conventions
- Mock/stub patterns match existing test infrastructure

### Step 4: Classify Severity Levels

Raise a discrepancy only when you can name its basis: an acceptance criterion, a statement in ARCHITECTURE.md, the glossary or the API contract, or a defect in design.md that would make the implementation wrong. Do not raise preferences, alternative designs, gaps that no source requires, or details that implementation decides, such as file layout, deployment units and test technique. Write the basis in each finding's Issue. This rule applies to every severity: do not raise a MINOR finding that has no basis.

Assign severity to each discrepancy:

**CRITICAL** - Blocks task creation and implementation
- Requirements without any design support
- Fundamental architectural misalignment with existing system
- Data exposure or an authentication or authorisation bypass in the design
- A performance constraint that an acceptance criterion states, when design.md gives no budget or measurement that shows the design meets it
- Missing core components needed for functionality

**MAJOR** - Should be fixed for quality and consistency
- Inconsistent patterns with existing codebase
- Incomplete API specifications
- An identifier (component, interface, data model, attribute or API path) that differs from the glossary term or the existing name
- Data retained longer than any requirement needs
- Missing error handling design
- Best practice violations

**MINOR** - Can be noted and addressed during implementation
- Glossary drift confined to prose
- An acceptance criterion that the Testing Strategy names no test for
- An update to ARCHITECTURE.md, the glossary or the API contract that the design needs and does not name

### Step 5: Generate Fix Suggestions

For each discrepancy, provide specific, actionable recommendations:

**Example:**
```
Suggestion: Align handler pattern with existing codebase
- Change from proposed Spring controller to http4k HttpHandler
- Follow pattern in src/main/kotlin/<package>/api/handlers/
- Implement Handler interface with invoke(request: Request): Response
- Owner: Design author
```

### Step 6: Create Review Summary

Copy `project/work-items/templates/gates/design-review-summary.md` to `project/work-items/{NN}-{name}/design-review-summary.md` and fill every placeholder. When the project has no template, write this outline:

```markdown
# Design Review Summary: [Spec Name]

**Review Date:** [YYYY-MM-DD]
**Documents Reviewed:** [requirements.md or bugfix.md, design.md, and the context files read]

## Overall Status

**Status:** [APPROVED | APPROVED WITH ISSUES | BLOCKED]

**Summary:** [1-2 sentences]

**Statistics:**
- Requirements reviewed: [N]
- Acceptance criteria checked: [N]
- Critical discrepancies: [N]
- Major discrepancies: [N]
- Minor discrepancies: [N]

## Review Results

### Alignments Confirmed
### Critical Discrepancies (Must fix before creating tasks)
### Major Discrepancies (Should fix for quality)
### Minor Issues (Can defer to implementation)

[Each finding: `#### CRITICAL-NNN:`, `#### MAJOR-NNN:` or `#### MINOR-NNN:` and a short description, then Issue (with its basis), Impact, Fix Suggestion and Owner. An empty section says "No critical discrepancies found." or the same for its level.]

## Architecture Consistency Assessment

## Recommendations

[Numbered actions for the status above, most important first. BLOCKED: the fixes that unblock, then a re-run of this review. APPROVED WITH ISSUES: which issues to fix before tasks.md and which can wait for implementation. APPROVED: "None."]

## Next Steps
```

### Step 7: Determine Workflow Action

Set the status from the highest severity found: any CRITICAL gives BLOCKED, any MAJOR or MINOR gives APPROVED WITH ISSUES, none gives APPROVED. Write it as the single `**Status:**` line under Overall Status.

**If any CRITICAL discrepancies:**
- Status: BLOCKED
- Action: STOP workflow — do NOT proceed to tasks.md generation
- Message to user: "Critical design issues found. Fix them in design.md or requirements.md, then re-run `sdd:spec-design-review`."
- Second BLOCKED run: if a design-review-summary.md from an earlier run of this work item was BLOCKED on the same issues, do not ask for another fix. Stop and ask the user how to proceed.

**If only MAJOR or MINOR discrepancies:**
- Status: APPROVED WITH ISSUES
- Action: Warn user but allow continuation to tasks.md
- Message to user: "Design review complete with [N] issues found. Recommend addressing major issues, but you may proceed to task generation. Proceed to `sdd:spec-generator` Phase 3: tasks.md generation."

**If no discrepancies:**
- Status: APPROVED
- Action: Proceed to tasks.md generation
- Message to user: "All design alignment and architecture checks passed. Proceed to `sdd:spec-generator` Phase 3: tasks.md generation."

## Review Criteria & Common Patterns

See [references/review-criteria.md](references/review-criteria.md) for detailed checklists (requirements coverage, architecture consistency, API design, data model, security) and common discrepancy patterns.
