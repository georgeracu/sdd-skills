# Design Review Summary: 09-usage-export

**Review Date:** 2026-03-09
**Reviewer:** Claude (spec-design-review skill)
**Documents Reviewed:**
- requirements.md
- design.md

---

## Overall Status

BLOCKED

**Summary:** The design is internally consistent but does not implement the requirements as written: it exports an aggregate count where the requirements ask for one row per Metered_Event, and it has no way to select a Billing_Period. Two of three requirements have no design support.

**Statistics:**
- Requirements reviewed: 3
- Acceptance criteria checked: 12
- Critical discrepancies: 2
- Major discrepancies: 2
- Minor discrepancies: 1

---

## Review Results

### Alignments Confirmed

- Requirement 1.1 and 1.2: the Dashboard download action and the file naming are covered by the frontend component description.
- Requirement 3.3: the header-only export for an empty period is designed and has a correctness property.
- No new storage, matching the architecture constraint on provisioned capacity.

### Critical Discrepancies (Must fix before creating tasks)

#### CRITICAL-001: Export content has no data source

**Issue:** Requirement 3.1 asks for one row per Metered_Event with `timestamp`, `apiKeyId`, `route` and `statusCode`. The design reads the `usage-meters` table, which holds a single `metered` count per Account per Billing_Period and none of those four fields. `UsageExportService.csvFor` emits one aggregate row with an `account_id,billing_period,metered` header, which is a different document from the one the requirements describe.

**Impact:** Requirement 3 (3.1, 3.2, 3.4) cannot be implemented from this design. Per-event data is not stored anywhere today; the design has to either introduce an event store on the ingest path or the requirements have to be renegotiated to an aggregate export.

**Fix Suggestion:** Decide with the user which document is wrong. If per-event rows stand, design the storage (an `metered-events` table or S3 object per Account per period written by the ingest Lambda, with the capacity implications stated) and revise the Data Models, Architecture and Correctness Properties sections. If the aggregate is what is wanted, revise Requirement 3 first and re-review.

**Owner:** Design author

#### CRITICAL-002: No Billing_Period selection or range validation

**Issue:** Requirements 2.1 to 2.4 define an Export_Request for a named Billing_Period, a thirteen-period window, and two distinct 400 responses (`billing-period-out-of-range`, `billing-period-malformed`). The endpoint in the design takes only `accountId` and always exports the current period. Neither 400 appears in Error Handling or API Specifications.

**Impact:** Requirements 2.1 to 2.4 have no design support, and Requirement 1.1 (selecting a Billing_Period on the usage page) cannot be wired to the API.

**Fix Suggestion:** Add a required `billingPeriod` query parameter (`^[0-9]{4}-[0-9]{2}$`, matching the `UsageSummary.billingPeriod` pattern in `openapi.yaml`), validate the format then the range against the current period, and add both 400 conditions to Error Handling and API Specifications with their `Problem.code` values.

**Owner:** Design author

### Major Discrepancies (Should fix for quality)

#### MAJOR-001: Controller pattern does not match the backend

**Issue:** The design proposes a Spring `@RestController` with `@GetMapping`. The backend uses the http4k handler pattern (`ARCHITECTURE.md`, `src/main/kotlin/<package>/api/handlers/`), and no Spring dependency exists in `backend/`.

**Impact:** Introduces a second web framework for one endpoint, or forces a rewrite at implementation time.

**Fix Suggestion:** Design the endpoint as an http4k `HttpHandler` in `api/handlers/`, following the existing `GetUsageHandler`.

**Owner:** Design author

#### MAJOR-002: Suspended accounts contradict Requirement 2.5

**Issue:** Error Handling returns 403 `account-suspended` for suspended Accounts. Requirement 2.5 requires exports to be served for suspended Accounts.

**Impact:** Implementing the design as written violates an explicit requirement.

**Fix Suggestion:** Remove the 403 row and state in Key Design Decisions that suspension does not gate exports.

**Owner:** Design author

### Minor Issues (Can defer to implementation)

#### MINOR-001: Header naming

**Issue:** The CSV header uses `account_id` and `billing_period` where every API field is camelCase (`accountId`, `billingPeriod`).

**Recommendation:** Use camelCase headers; moot if CRITICAL-001 replaces the header entirely.

---

## Architecture Consistency Assessment

The synchronous `text/csv` response through API Gateway fits the existing Lambda-per-resource layout and the no-new-table constraint holds. The framework choice does not: http4k is the only handler pattern in the backend. The design does not reference `openapi.yaml`, and the new endpoint is not yet added to it.

---

## Recommendations

**If BLOCKED:**
1. Resolve CRITICAL-001 with the user first, as the answer decides whether the design or the requirements change.
2. Then address CRITICAL-002 in `design.md`.
3. Fold MAJOR-001 and MAJOR-002 into the same revision.
4. Request a re-review before any tasks are generated.
