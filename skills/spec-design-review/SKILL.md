---
name: spec-design-review
description: Use when design.md has been generated and approved during the SDD pipeline. Validates alignment between requirements.md and design.md, checks architecture consistency with existing codebase. Quality gate between design and task generation.
---

# Spec Design Review - Quality Gate

Validate alignment between business requirements and technical design to ensure the proposed solution is architecturally sound, consistent with the existing system, and follows best practices.

## Overview

The spec-design-review skill acts as a **quality gate** between design creation and task creation within the spec-generator workflow. It ensures that:
- The design document addresses ALL requirements from requirements.md (or bugfix.md)
- The proposed architecture is consistent with the existing system architecture
- APIs, data models, and components follow established patterns and conventions
- There are no gaps, contradictions, or architectural anti-patterns
- Best practices for security, performance, and maintainability are followed

**Workflow Position:** Runs AFTER design.md is approved by the user and BEFORE tasks.md is generated.

```
sdd:spec-generator Phase 1 → sdd:spec-generator Phase 2 (design.md) → sdd:spec-design-review → sdd:spec-generator Phase 3 (tasks.md)
```

## When to Use This Skill

This skill is triggered automatically by the spec-generator skill after design.md is created. It can also be triggered manually when:
- User asks to "review the design" or "check design alignment"
- User says "validate the design" or "check architecture consistency"
- A design document is complete and ready for task generation
- You need to verify a design fits the existing system before implementation

## Prerequisites

Before running this review, verify these files exist:

1. **requirements.md** or **bugfix.md** (Phase 1 output from spec-generator)
   - Location: `project/work-items/[spec-name]/requirements.md` or `bugfix.md`
   - Contains: User stories, acceptance criteria, glossary

2. **design.md** (Phase 2 output from spec-generator)
   - Location: `project/work-items/[spec-name]/design.md`
   - Contains: Architecture, components, data models, correctness properties

3. **Existing codebase** (for architecture consistency checks)
   - Key files to reference for architectural patterns (read as needed during review)

## Review Process

### Step 1: Read Input Documents

Read the spec documents from the work item directory:

```bash
Read: project/work-items/[spec-name]/requirements.md  # or bugfix.md
Read: project/work-items/[spec-name]/design.md
```

### Step 2: Gather Architectural Context

Before reviewing, gather context about the existing system by reading relevant files based on what the design touches. This is **not** an exhaustive audit — read only what's needed to validate the design's architectural decisions.

**Examples of files to check based on design scope:**
- If the design adds API endpoints → read `openapi.yaml` and existing handler patterns
- If the design adds DynamoDB tables → read existing CloudFormation templates
- If the design adds frontend components → read existing component patterns
- If the design modifies auth flows → read existing auth service code
- If the design adds Lambda functions → read existing Lambda patterns

Use the MEMORY.md and CLAUDE.md files for high-level architectural context.

### Step 3: Perform Comprehensive Review

Check alignment across these six critical dimensions:

#### 3.1 Requirements Coverage

**Check:** Every requirement/acceptance criterion in requirements.md has corresponding design support.

**Validation:**
- Extract all requirements and their acceptance criteria
- For each acceptance criterion, verify design.md includes components, interfaces, or data models that enable it
- Verify correctness properties trace back to requirements (`**Validates: Requirements N.N**`)
- Identify any requirements without design coverage

**Example discrepancy:**
```
CRITICAL: Requirement 2 AC 2.3 "System shall display error message within 2 seconds" has no design support
   - Missing: Error handling component for this scenario
   - Missing: Performance constraint in component specification
```

#### 3.2 Architecture Consistency

**Check:** The proposed design is consistent with the existing system architecture.

**Validation:**
- Compare proposed components with existing architectural patterns
- Verify naming conventions match existing code (package names, class names, file locations)
- Check that new services follow the same patterns as existing ones (e.g., handler → service → repository)
- Verify infrastructure choices align with existing stack (e.g., DynamoDB, Lambda, S3)
- Ensure proposed API paths follow existing conventions

**Example discrepancy:**
```
MAJOR: Design proposes REST controller pattern but existing backend uses http4k handler pattern
   - Existing: Handler classes implementing HttpHandler interface
   - Proposed: Spring-style @RestController annotations
   - Fix: Redesign using http4k handler pattern
```

#### 3.3 API Design Quality

**Check:** All proposed APIs are well-designed, complete, and consistent with existing APIs.

**Validation:**
- Request/response schemas are fully defined with types
- HTTP methods and status codes follow REST conventions
- Error responses follow the existing error format
- Authentication/authorization requirements are specified
- API paths follow existing naming conventions (e.g., `/api/v1/...`)
- New endpoints are consistent with `openapi.yaml` patterns

**Example discrepancy:**
```
MAJOR: POST /api/v1/scan endpoint missing error response schema
   - Existing pattern: All endpoints define 400, 401, 403, 500 responses
   - Proposed: Only defines 200 success response
```

#### 3.4 Data Model Consistency

**Check:** Proposed data models are consistent with existing models and follow established patterns.

**Validation:**
- DynamoDB table designs follow existing patterns (PK/SK naming, GSI conventions)
- Entity names use consistent terminology with the glossary and existing codebase
- Data types match existing conventions (e.g., ISO 8601 dates, enum formats)
- Relationships between new and existing entities are clear
- No duplicate storage of data that already exists in another table

**Example discrepancy:**
```
MAJOR: Design proposes "city_id" field but existing system uses "municipalityId"
   - Existing convention: camelCase field names, "municipality" terminology
   - Fix: Rename to "municipalityId" for consistency
```

#### 3.5 Security & Best Practices

**Check:** The design follows security best practices and established security patterns.

**Validation:**
- Authentication is specified for all endpoints that need it
- Authorization checks are included where needed
- Input validation is defined at system boundaries
- No sensitive data exposure in logs or responses
- OWASP top 10 considerations addressed where relevant
- IAM permissions follow least privilege principle

**Example discrepancy:**
```
MAJOR: Design stores user email in scan results DynamoDB table
   - Issue: PII data replicated unnecessarily
   - Existing pattern: Store only userId, resolve email from Cognito when needed
```

#### 3.6 Testing Strategy Completeness

**Check:** The testing strategy covers all requirements and follows existing test patterns.

**Validation:**
- Unit tests cover core business logic
- Integration tests cover component interactions
- Property-based tests align with correctness properties
- Test file locations follow existing conventions
- Mock/stub patterns match existing test infrastructure

**Example discrepancy:**
```
MINOR: Testing strategy mentions Mockito but existing backend tests use mockk
   - Existing pattern: mockk for Kotlin test mocking
   - Fix: Update testing strategy to use mockk
```

### Step 4: Classify Severity Levels

Assign severity to each discrepancy:

**CRITICAL** - Blocks task creation and implementation
- Requirements without any design support
- Fundamental architectural misalignment with existing system
- Security vulnerabilities in the design
- Missing core components needed for functionality

**MAJOR** - Should be fixed for quality and consistency
- Inconsistent patterns with existing codebase
- Incomplete API specifications
- Data model naming inconsistencies
- Missing error handling design
- Best practice violations

**MINOR** - Can be noted and addressed during implementation
- Minor naming inconsistencies
- Missing performance targets
- Incomplete test scenarios
- Documentation gaps in the design

### Step 5: Generate Fix Suggestions

For each discrepancy, provide specific, actionable recommendations:

**Good suggestions:**
```
Suggestion: Align handler pattern with existing codebase
- Change from proposed Spring controller to http4k HttpHandler
- Follow pattern in src/main/kotlin/<package>/api/handlers/
- Implement Handler interface with invoke(request: Request): Response
- Owner: Design author
```

**Bad suggestions:**
```
Suggestion: Fix the architecture
```

### Step 6: Create Review Summary

Write a comprehensive `design-review-summary.md` file in the **same directory** as the input files.

**File location:** `project/work-items/[spec-name]/design-review-summary.md`

**Structure:**

```markdown
# Design Review Summary: [Spec Name]

**Review Date:** [YYYY-MM-DD]
**Reviewer:** Claude (spec-design-review skill)
**Documents Reviewed:**
- requirements.md (or bugfix.md)
- design.md

---

## Overall Status

[One of: APPROVED | APPROVED WITH MINOR ISSUES | BLOCKED]

**Summary:** [1-2 sentences about overall design quality and alignment]

**Statistics:**
- Requirements reviewed: [N]
- Acceptance criteria checked: [N]
- Critical discrepancies: [N]
- Major discrepancies: [N]
- Minor discrepancies: [N]

---

## Review Results

### Alignments Confirmed

[List areas where requirements and design are well-aligned]

### Critical Discrepancies (Must fix before creating tasks)

[List all critical issues with fix suggestions, or "No critical discrepancies found."]

#### CRITICAL-NNN: [Short description]

**Issue:** [Detailed description]

**Impact:** [Why this blocks implementation]

**Fix Suggestion:** [Specific, actionable recommendation]

**Owner:** [Design author]

### Major Discrepancies (Should fix for quality)

[List all major issues with fix suggestions, or "No major discrepancies found."]

#### MAJOR-NNN: [Short description]

**Issue:** [Detailed description]

**Impact:** [How this impacts implementation quality]

**Fix Suggestion:** [Specific recommendation]

**Owner:** [Design author]

### Minor Issues (Can defer to implementation)

[List all minor issues, or "No minor issues found."]

#### MINOR-NNN: [Short description]

**Issue:** [Brief description]

**Recommendation:** [Optional fix or note]

---

## Architecture Consistency Assessment

[Summary of how well the design fits with the existing system architecture, noting specific patterns that were validated]

---

## Recommendations

**If BLOCKED:**
1. [Specific actions needed to unblock]
2. [Priority order for fixes]
3. Design author must address critical issues and request re-review

**If APPROVED WITH ISSUES:**
1. [Which issues should be addressed before task creation]
2. [Which can be deferred to implementation]

**If APPROVED:**
- All alignment and architecture checks passed
- Ready to proceed with tasks.md generation
- Next step: Generate implementation tasks

---

## Review Checklist

- [ ] Requirements coverage: All acceptance criteria have design support
- [ ] Architecture consistency: Design follows existing system patterns
- [ ] API design quality: All endpoints fully specified and consistent
- [ ] Data model consistency: No naming or structural contradictions
- [ ] Security & best practices: No vulnerabilities or anti-patterns
- [ ] Testing strategy: Covers all requirements with appropriate test types

---

## Next Steps

[Clear guidance based on review status]
```

### Step 7: Determine Workflow Action

Based on severity:

**If any CRITICAL discrepancies:**
- Status: BLOCKED
- Action: STOP workflow — do NOT proceed to tasks.md generation
- Message to user: "Critical design issues found. Please address critical issues before generating tasks. Return to fix documents, re-run `sdd:spec-design-review`."
- The design author must fix issues in design.md and the review must be re-run

**If only MAJOR or MINOR discrepancies:**
- Status: APPROVED WITH MINOR ISSUES
- Action: Warn user but allow continuation to tasks.md
- Message to user: "Design review complete with [N] issues found. Recommend addressing major issues, but you may proceed to task generation. Proceed to `sdd:spec-generator` Phase 3: tasks.md generation."

**If no discrepancies:**
- Status: APPROVED
- Action: Proceed to tasks.md generation
- Message to user: "All design alignment and architecture checks passed. Proceed to `sdd:spec-generator` Phase 3: tasks.md generation."

## Output Location

Save the review summary in the **same directory** as the input files:

```
project/work-items/[spec-name]/design-review-summary.md
```

## Review Criteria & Common Patterns

See [references/review-criteria.md](references/review-criteria.md) for detailed checklists (requirements coverage, architecture consistency, API design, data model, security) and common discrepancy patterns.

## Integration with Spec Generator Workflow

### Before sdd:spec-design-review:

**Input documents exist:**
- requirements.md or bugfix.md (Phase 1, user-approved)
- design.md (Phase 2, user-approved)

### After sdd:spec-design-review:

**If APPROVED:**
- design-review-summary.md created
- No critical discrepancies
- Proceed to `sdd:spec-generator` Phase 3: tasks.md generation

**If BLOCKED:**
- design-review-summary.md created with critical issues
- Critical discrepancies must be fixed in design.md
- User must review and approve updated design.md
- Return to fix documents, re-run `sdd:spec-design-review`
- Do NOT proceed to tasks.md until APPROVED

## Gate Failure Behaviour

If BLOCKED, fix the identified issues in `design.md` or `requirements.md` and re-run this review. If blocked twice on the same issues, stop and ask the user.

## Quality Standards

A high-quality review:

- Checks ALL requirements for design coverage (not just a sample)
- Validates architecture against actual codebase patterns (not assumptions)
- Verifies API specs are implementation-ready
- Identifies both gaps AND contradictions
- Provides actionable fix suggestions with clear ownership
- Uses severity levels appropriately
- Blocks workflow when critical issues exist
- Generates clear, well-structured summary document

## Tips for Effective Reviews

1. **Read the codebase:** Don't review in isolation — check actual existing patterns
2. **Be specific:** Cite exact requirement numbers, component names, and file paths
3. **Provide context:** Explain why a discrepancy matters and reference existing code
4. **Suggest solutions:** Show what the fix should look like with concrete examples
5. **Think like a developer:** Could someone implement this design without ambiguity?
6. **Check consistency:** Ensure the design's terminology matches the project glossary
7. **Verify completeness:** Missing information is as problematic as wrong information
8. **Respect existing patterns:** The existing codebase is the authority on conventions
