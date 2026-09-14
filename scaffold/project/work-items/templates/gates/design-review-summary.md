<!--
  Design Review Summary Template

  Produced by: sdd:spec-design-review (quality gate)
  Reviews: requirements.md (or bugfix.md) + design.md
  Severity scale: CRITICAL | MAJOR | MINOR

  Replace all [bracketed] placeholders with actual content.
  Remove these HTML comments when creating a real summary.
-->

# Design Review Summary: [Spec Name]

**Review Date:** [YYYY-MM-DD]
**Reviewer:** sdd:spec-design-review skill
**Documents Reviewed:**
- requirements.md (or bugfix.md)
- design.md

---

## Overall Status

[APPROVED | APPROVED WITH MINOR ISSUES | BLOCKED]

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

- [Confirmed alignment]
- [Confirmed alignment]

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
