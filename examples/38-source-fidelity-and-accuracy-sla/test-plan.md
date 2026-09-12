# Test Plan — Source-Fidelity & Accuracy-Update SLA (Spec 38)

**Created:** 2026-05-13
**Last Updated:** 2026-05-13
**Status:** Draft (Plan mode — pre-implementation)
**Author:** Senior QA Engineer ([AIW-65](/AIW/issues/AIW-65))
**Source artifacts:** `requirements.md`, `design.md`, `design-review-summary.md`, `tasks.md`

---

## Coverage Matrix

| Layer | Expected | Actual | Status |
|---|---|---|---|
| Unit | See §Unit | — | Pending |
| Contract | See §Contract | — | Pending |
| Integration | See §Integration | — | Pending |
| E2E | See §E2E | — | Pending |
| axe-a11y | See §Accessibility | — | Pending |

**Unit Coverage Gate:** ≥ 85% line/branch — Pending (actual: —%)

---

## §Unit

### Backend (Kotlin)

#### `FreshnessServiceTest.kt` — Correctness Property 2

| # | Scenario | Expected |
|---|----------|----------|
| FS-1 | `lastVerifiedAt` = 89 days ago, no `publishedRuleChangeAt` | `WITHIN_SLA` |
| FS-2 | `lastVerifiedAt` = 91 days ago, no `publishedRuleChangeAt` | `STALE` (baseline breach) |
| FS-3 | `publishedRuleChangeAt` = 13 days ago, `lastVerifiedAt` = 15 days ago | `STALE` (change-response breach) |
| FS-4 | `publishedRuleChangeAt` = 13 days ago, `lastVerifiedAt` = same day as change | `WITHIN_SLA` |
| FS-5 | `lastVerifiedAt` = null | `UNAVAILABLE` |
| FS-6 | SSM parameter read throws; defaults (90/14) applied | `WITHIN_SLA` at 89 days (SSM fallback path) |
| FS-7 | SSM parameter read throws; defaults (90/14) applied | `STALE` at 91 days (SSM fallback path) |

**Coverage note:** `isBaselineBreach` and `isChangeResponseBreach` must each be exercised independently to reach branch coverage.

#### `RulesServiceTest.kt` (extended) — Correctness Properties 1, 2, 3

| # | Scenario | Expected |
|---|----------|----------|
| RS-1 | Successful rules fetch with freshness data | `slaState` present in JSON serialised response |
| RS-2 | Successful rules fetch with freshness data | `verifierIdentity` **absent** from JSON serialised response (Jackson serialisation assertion — Correctness Property 1) |
| RS-3 | DynamoDB freshness read throws | Response still returned; `slaState = unavailable`; no exception propagated (Correctness Property 3) |
| RS-4 | First stale result for `(municipalityId, day)` | `sla_breach_observed` metric emitted (Correctness Property 2) |
| RS-5 | Second stale result same Lambda instance, same day, same municipality | `sla_breach_observed` NOT re-emitted |

#### `AdminRouterTest.kt` — Security §1, §3; Correctness Properties 1, 5

| # | Scenario | Expected |
|---|----------|----------|
| AR-1 | Valid `VerificationWriteRequest`, content-ops principal | `200 OK`; updated `FreshnessMeta` returned; audit row written |
| AR-2 | Valid request | `verifierIdentity` absent from `200` response body (Correctness Property 1) |
| AR-3 | Blank `lastVerifiedAt` | `400 VALIDATION_ERROR` with field list |
| AR-4 | Blank `sourceUrl` | `400 VALIDATION_ERROR` |
| AR-5 | Blank `verifierIdentity` | `400 VALIDATION_ERROR` |
| AR-6 | Invalid ISO-8601 on `lastVerifiedAt` | `400 INVALID_DATE` |
| AR-7 | Invalid ISO-8601 on `publishedRuleChangeAt` | `400 INVALID_DATE` |
| AR-8 | `sourceUrl` contains query param `userId` | `400` (Security §1 deny-list) |
| AR-9 | `sourceUrl` contains query param `scanId` | `400` (Security §1 deny-list) |
| AR-10 | `sourceUrl` contains query param `token` | `400` (Security §1 deny-list) |
| AR-11 | `sourceUrl` contains query param `jwt` | `400` (Security §1 deny-list) |
| AR-12 | `sourceUrl` contains query param `sessionId` | `400` (Security §1 deny-list) |
| AR-13 | Caller principal ARN does not begin with `arn:aws:sts::{AccountId}:assumed-role/gomi-content-ops-` | `403 Forbidden` (Security §3 belt-and-suspenders) |
| AR-14 | `municipalityId` not found in `gomi-bunrui-rules` | `404 NOT_FOUND` |
| AR-15 | DynamoDB write failure on META update | `500 INTERNAL_ERROR` |
| AR-16 | META update succeeds; audit `PutItem` fails | `200 OK` returned; `VerificationAuditWriteFailed` metric emitted; error logged without `verifierIdentity` (Correctness Property 5) |
| AR-17 | Log assertions across all happy and error paths | No `verifierIdentity` or PII in any log line |

**Coverage note:** AR-8 through AR-12 must use URL-parsed key extraction, not string-contains, to match the implementation pattern.

#### `ScanFeedbackServiceTest.kt` (extended) — Req 5.1–5.3

| # | Scenario | Expected |
|---|----------|----------|
| SF-1 | `slaState = "within_sla"` | Accepted; persisted |
| SF-2 | `slaState = "stale"` | Accepted; persisted |
| SF-3 | `slaState = "unavailable"` | Accepted; persisted |
| SF-4 | `slaState = null` | Accepted; persisted as null |
| SF-5 | `slaState = "invalid_value"` | `400` rejected |

### Frontend (Vitest / React Testing Library)

#### `SourceCitation.test.tsx` — Correctness Properties 4, 6; Req 1, 6, 7

| # | Scenario | Expected |
|---|----------|----------|
| SC-1 | `slaState = within_sla` | Source label, link, date rendered |
| SC-2 | `slaState = stale` | Amber variant; `source.stale.label` with `{{date}}`; `source.stale.confirmationPrompt`; link rendered |
| SC-3 | `slaState = unavailable` | `source.unavailable.label` rendered; no link element |
| SC-4 | Link element on SC-1 | `rel="noopener noreferrer"` attribute present (Correctness Property 4) |
| SC-5 | Link element on SC-1 | URL regex: no query parameters on `sourceUrl` output (Correctness Property 4) |
| SC-6 | `sourceLanguage = "ja"`, `locale = "en"` | `source.citation.languageDisclosure.jaOnly` copy present |
| SC-7 | `sourceLanguage = "ja"`, `locale = "ja"` | JA-only disclosure absent (Req 6.2) |
| SC-8 | `sourceLanguage = "en"`, `locale = "en"` | JA-only disclosure absent |
| SC-9 | `aria-live="polite"` region | Present in DOM |
| SC-10 | `freshnessMeta` absent/null | Renders `unavailable` state (defensive default) |
| SC-11 | `slaState = "unexpected_value"` | Renders `unavailable` state (defensive default) |
| SC-12 | axe scan — EN locale render | 0 violations |
| SC-13 | axe scan — JA locale render | 0 violations |
| SC-14 | `slaState = stale` does not suppress classification result | Classification result still rendered (Correctness Property 6) |

#### `AccuracySlaPage.test.tsx`

| # | Scenario | Expected |
|---|----------|----------|
| SLA-1 | Renders EN locale | `sla.page.title`, `sla.page.commitment`, `sla.page.breachPosture`, `sla.page.activelyCoveredMunicipalities` rendered |
| SLA-2 | Renders JA locale | Same four keys rendered in JA |
| SLA-3 | Static municipality list | List items rendered from `actively-covered-municipalities.json` |
| SLA-4 | axe scan | 0 violations |

#### `ScanFeedbackPrompt.test.tsx` (extended) — Correctness Property 7

| # | Scenario | Expected |
|---|----------|----------|
| FB-1 | User submits feedback | `POST /scan-feedback` payload includes `slaState` matching the `slaState` rendered by `SourceCitation` (Correctness Property 7) |
| FB-2 | `400` returned by server on `slaState` | Feedback fails gracefully; does not block user; `error-invalid-category` path surfaced |

---

## §Contract

OpenAPI contract freshness is a CI gate on every PR. These checks confirm the contract is complete and correctly regenerated.

| # | Check | Expected |
|---|-------|----------|
| CT-1 | `openapi.yaml` schema — `freshnessMeta` on `MunicipalityRulesResponse` | `lastVerifiedAt` (nullable string), `sourceUrl` (nullable string), `sourceLanguage` (nullable string), `slaState` (enum: `within_sla`, `stale`, `unavailable`) |
| CT-2 | `openapi.yaml` schema — `PUT /api/v1/admin/municipalities/{municipalityId}/freshness` | Request body: `VerificationWriteRequest` (required fields present); responses: 200/400/403/404/500; `Auth: AWS_IAM`; response shape excludes `verifierIdentity` |
| CT-3 | `openapi.yaml` schema — `ScanFeedbackRequest` extension | `slaState` field present; nullable; enum constrained |
| CT-4 | `generated/typescript/api-types.ts` | Freshness types exported; `slaState` enum typed |
| CT-5 | `generated/kotlin/...` sources | `FreshnessMeta` Kotlin class present; no `verifierIdentity` field (compile-time isolation assertion) |
| CT-6 | CI: `scripts/generate-api-types.sh` regeneration | Generated files match checked-in files; CI step fails on diff |
| CT-7 | `PUT /admin/...` response schema | `verifierIdentity` and `sourceSnapshotRef` absent from response schema |

---

## §Integration

These tests verify service-boundary interactions: Lambda → DynamoDB, Lambda → SSM, Lambda → CloudWatch.

| # | Boundary | Scenario | Expected |
|---|----------|----------|----------|
| IT-1 | `RulesRepository` → DynamoDB `gomi-bunrui-rules` | Read META item with all freshness fields present | All six freshness attributes returned and mapped |
| IT-2 | `RulesRepository` → DynamoDB `gomi-bunrui-rules` | Read META item with no freshness fields | Null values mapped; `FreshnessService` returns `UNAVAILABLE` |
| IT-3 | `FreshnessService` → SSM | SSM parameter `/gomi/{stage}/sla-baseline-days` read | Cached after first call; subsequent calls use cache |
| IT-4 | `FreshnessService` → SSM | SSM parameter read fails | Compiled-in defaults applied; WARN logged |
| IT-5 | `VerificationAuditRepository` → DynamoDB `gomi-verification-audit-{stage}` | `appendAuditRow` | PutItem with correct PK/SK format `VERIFICATION#{ISO}#{uuid}` |
| IT-6 | `RulesRepository` → DynamoDB `gomi-bunrui-rules` | `writeFreshnessFields` | UpdateItem on META with correct attribute names |
| IT-7 | `RulesService` → CloudWatch | First stale result | `SlaBreachObserved` metric emitted with `MunicipalityId` + `Stage` dimensions |
| IT-8 | `RulesService` → CloudWatch | `slaState = unavailable` served | `FreshnessUnavailableServed` metric emitted |
| IT-9 | `AdminRouter` → CloudWatch | Successful verification write | `VerificationWritten` metric emitted |
| IT-10 | `AdminRouter` → CloudWatch | Audit write failure | `VerificationAuditWriteFailed` metric emitted |
| IT-11 | OpenAPI contract conformance | `GET /api/v1/rules/{cityId}` response | Response body validates against `openapi.yaml` `MunicipalityRulesResponse` schema |
| IT-12 | OpenAPI contract conformance | `PUT /api/v1/admin/municipalities/{id}/freshness` happy path | Request and response validate against `openapi.yaml` schema |
| IT-13 | IAM Cognito deny — integration assertion | `PUT /admin/...` called with Cognito-authenticated credentials | `403` from API Gateway before Lambda is invoked |

---

## §E2E

All Playwright tests run from `e2e/` against the mock server (local) or staging (smoke).

### `e2e/tests/flows/source-citation.spec.ts` (Task 16)

| # | Scenario | Expected |
|---|----------|----------|
| E2E-1 | Fresh citation (`slaState = within_sla`) | `SourceCitation` block visible; municipal source label present; tappable link with `href`; `lastVerifiedAt` date formatted |
| E2E-2 | Stale citation (`slaState = stale`) | Amber indicator visible; `source.stale.label` text present with date; `source.stale.confirmationPrompt` text present |
| E2E-3 | Unavailable citation (`slaState = unavailable`) | "AI estimate" label visible; no link element |
| E2E-4 | Citation link (`slaState = within_sla`) | Link element has `rel` attribute containing `noopener` and `noreferrer` |
| E2E-5 | Citation link URL | URL regex: no query parameters on `href` value (no `userId`, `scanId`, `sessionId`, etc.) |
| E2E-6 | EN locale, JA source | `(Japanese only)` disclosure visible |
| E2E-7 | Classification result is accessible when stale | AI category answer visible alongside stale citation (non-blocking — Correctness Property 6) |

### `e2e/tests/flows/accuracy-sla-page.spec.ts` (Task 16)

| # | Scenario | Expected |
|---|----------|----------|
| E2E-8 | Navigate to `/about/accuracy-sla` in EN | Page renders; `Our accuracy commitment` heading present; 14-day and 90-day values visible |
| E2E-9 | Navigate to `/about/accuracy-sla` in JA | JA heading and body copy rendered |
| E2E-10 | Link from `/about` footer | `/about/accuracy-sla` link navigable |
| E2E-11 | Marketing-site footer | `AccuracySla.astro` page reachable from marketing site footer link |

---

## §Accessibility

Run axe at 375 px viewport width — 3 `slaState` values × 2 locales = 6 mandatory combinations.

| # | Page / Component | Viewport | slaState | Locale | Expected |
|---|------|------|----------|--------|----------|
| AX-1 | `ScanResultsPage` | 375 px | `within_sla` | EN | 0 axe violations |
| AX-2 | `ScanResultsPage` | 375 px | `within_sla` | JA | 0 axe violations |
| AX-3 | `ScanResultsPage` | 375 px | `stale` | EN | 0 axe violations |
| AX-4 | `ScanResultsPage` | 375 px | `stale` | JA | 0 axe violations |
| AX-5 | `ScanResultsPage` | 375 px | `unavailable` | EN | 0 axe violations |
| AX-6 | `ScanResultsPage` | 375 px | `unavailable` | JA | 0 axe violations |
| AX-7 | `ScanResultsPage` | 375 px | `stale` JA | JA | Source label + freshness date NOT truncated under 40-char municipality name |
| AX-8 | `AccuracySlaPage` | 375 px | N/A | EN | 0 axe violations |
| AX-9 | `AccuracySlaPage` | 375 px | N/A | JA | 0 axe violations |
| AX-10 | Screen-reader reading order | Any | `within_sla` | EN | Source label → freshness state → link disclosure (Req 1.6) |

**Note:** AX-1 through AX-6 are the mandatory 6-combo set per design §Testing Strategy. AX-7 exercises JA layout robustness (Req 6.5). AX-10 must be verified via manual screen-reader pass (VoiceOver / TalkBack).

---

## Correctness Property Cross-Reference

Every Correctness Property from `design.md` maps to at least one test:

| Property | Description | Covered by |
|----------|-------------|-----------|
| CP-1 | `verifierIdentity` isolation — absent from DTO at type level | RS-2, AR-2, CT-5, AR-17 |
| CP-2 | `slaState` server authority — never influenced by client | FS-1 through FS-7, RS-4 |
| CP-3 | Non-blocking freshness failure — exception produces `unavailable`, not 5xx | RS-3 |
| CP-4 | Outbound-link hygiene — static `rel`, no query params | SC-4, SC-5, E2E-4, E2E-5 |
| CP-5 | Audit trail completeness — META commit + audit failure emits metric | AR-16 |
| CP-6 | Stale does not block scan result | SC-14, E2E-7 |
| CP-7 | `slaState` capture in feedback — value matches rendered state | FB-1 |

---

## Security Coverage

| # | Security Control | Test(s) |
|---|-----------------|---------|
| SEC-1 | `sourceUrl` query-param deny-list at write time (Security §1) | AR-8 through AR-12 |
| SEC-2 | `verifierIdentity` never logged or serialised | AR-17, RS-2, AR-2 |
| SEC-3 | Admin route: non-content-ops principal → `403` | AR-13 |
| SEC-4 | Admin route: Cognito roles explicitly denied | IT-13 |
| SEC-5 | DynamoDB audit table: Lambda role scoped to `PutItem` only | IT-5 (positive); a negative `GetItem` call from Lambda role should return `AccessDeniedException` — verify via IAM policy simulator or staging integration test |
| SEC-6 | `VerificationAuditWriteFailed` — no PII in log on failure | AR-16 (log assertion) |

---

## Phase 4 MINOR Findings Disposition

| ID | Finding | tasks.md Disposition | Test coverage |
|----|---------|---------------------|---------------|
| MINOR-001 | `sla_breach_observed` dedup is best-effort — over-counts on cold starts | Accepted as task-note in Task 5; RS-4/RS-5 validate best-effort dedup path | RS-4, RS-5 |
| MINOR-002 | Req 2.5 "omit" vs. design's always-present `{ slaState: unavailable }` shape | Design canonical; EA to update Req 2.5 in Phase 11 | CT-1 (always-present shape), SC-10 (frontend defensive default on absent) |
| MINOR-003 | AccuracySlaPage municipality list mechanism | Resolved in Phase 5: build-time static JSON `frontend/src/content/actively-covered-municipalities.json` | SLA-3 |
| MINOR-004 | Req 5.3 counter-metric alert wiring | Roadmap item; Task 8 coordination ticket with spec-37 dashboard owner; no paging alarm for v1 | No new test for Phase 8 (out of scope v1); SF-1 through SF-5 cover the `slaState` persistence that feeds the metric |

All four MINOR findings are addressed in `tasks.md`. No CRITICAL or MAJOR gaps remain.

---

## Owners per Layer

| Layer | Owner | Notes |
|-------|-------|-------|
| Unit — Kotlin | Senior Backend Engineer | `FreshnessServiceTest.kt`, `RulesServiceTest.kt`, `AdminRouterTest.kt`, `ScanFeedbackServiceTest.kt` |
| Unit — React | Senior Frontend Engineer | `SourceCitation.test.tsx`, `AccuracySlaPage.test.tsx`, `ScanFeedbackPrompt.test.tsx` |
| Contract | Senior Backend Engineer + Senior Frontend Engineer | `openapi.yaml`; CI freshness check; Task 1 |
| Integration | Senior Backend Engineer | Lambda → DynamoDB, SSM, CloudWatch boundaries |
| E2E | Senior QA Engineer | `e2e/tests/flows/source-citation.spec.ts`, `e2e/tests/flows/accuracy-sla-page.spec.ts` (Tasks 16–17) |
| axe-a11y | Senior QA Engineer | Playwright axe runs; manual screen-reader pass |

---

## Gap Log

| # | Description | Severity | Status | Opened | Closed |
|---|-------------|----------|--------|--------|--------|
| G-1 | SEC-5 negative test (Lambda role denied `GetItem` on audit table) requires staging integration run or IAM policy simulator — cannot be exercised by unit tests alone | Important | Open | 2026-05-13 | — |
| G-2 | AX-10 screen-reader reading-order check requires manual VoiceOver/TalkBack pass — no automated equivalent for reading-order assertion | Important | Open | 2026-05-13 | — |
| G-3 | Req 5.3 alert mechanism (counter-metric differential > 2pp triggers PO review) is deferred to spec-37 dashboard; no automated test in this spec's scope | Minor | Open | 2026-05-13 | — |

---

## Test Inventory (Expected — to be filled on Review mode pass)

| File | Layer | Covers |
|------|-------|--------|
| `backend/.../FreshnessServiceTest.kt` | Unit | FS-1 through FS-7 |
| `backend/.../RulesServiceTest.kt` | Unit | RS-1 through RS-5 |
| `backend/.../AdminRouterTest.kt` | Unit | AR-1 through AR-17 |
| `backend/.../ScanFeedbackServiceTest.kt` | Unit | SF-1 through SF-5 |
| `frontend/src/components/SourceCitation.test.tsx` | Unit | SC-1 through SC-14 |
| `frontend/src/pages/AccuracySlaPage.test.tsx` | Unit | SLA-1 through SLA-4 |
| `frontend/src/components/ScanFeedbackPrompt.test.tsx` | Unit | FB-1, FB-2 |
| CI: `scripts/generate-api-types.sh` | Contract | CT-6 |
| Integration tests (staging) | Integration | IT-1 through IT-13 |
| `e2e/tests/flows/source-citation.spec.ts` | E2E | E2E-1 through E2E-7 |
| `e2e/tests/flows/accuracy-sla-page.spec.ts` | E2E | E2E-8 through E2E-11 |
| Playwright axe runs (Task 17) | axe-a11y | AX-1 through AX-9 |
| Manual VoiceOver/TalkBack pass | axe-a11y | AX-10 |

---

## Gate Sign-off (Phase 10 — to be completed after implementation)

| Layer | Result | Evidence | Notes |
|-------|--------|----------|-------|
| Unit | — | — | Gate ≥ 85% |
| Contract | — | — | |
| Integration | — | — | |
| E2E | — | — | |
| axe-a11y | — | — | 6-combo mandatory |
| **Overall gate** | **Pending** | | |
