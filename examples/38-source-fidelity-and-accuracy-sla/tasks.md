# Tasks — Spec 38 (Source-Fidelity & Accuracy-Update SLA)

> **Phase 5 — SDD pipeline**
> Author: Tech Lead ([AIW-64](/AIW/issues/AIW-64))
> Worktree: `.worktree/38-source-fidelity-and-accuracy-sla` (branch `spec/38-source-fidelity-and-accuracy-sla`)
> Phase 4 gate: PASS, signed off by Senior Product Owner in [AIW-63](/AIW/issues/AIW-63). Four MINOR findings carried forward — see §Phase 4 Findings Carried.
> Each task traces to acceptance criteria via `_Requirements: N.M_` from [requirements.md](./requirements.md) and to design elements in [design.md](./design.md).

---

## Slice plan

Two inseparable deliverable slices share one data contract (`MunicipalityRulesResponse.freshnessMeta`). They run **in parallel** once the OpenAPI contract is locked (Task 1).

- **Slice A — Citation UI (Frontend / Content):** `SourceCitation` component, `ScanResultsPage` integration, `AccuracySlaPage`, i18n keys, axe a11y, mock server.
- **Slice B — Freshness Pipeline (Backend / Infra):** `FreshnessService`, `RulesService` extension, META item read/write, `gomi-verification-audit-{stage}` table, `AdminRouter`, IAM `gomi-content-ops-{stage}` role + Cognito deny statements, SSM params, scan-feedback DTO extension, observability.

Sequence: **Task 1 (OpenAPI codegen)** unblocks both slices → Slice A and Slice B run in parallel → Task 18 (E2E) and Task 19 (final validation) close once both slices land.

---

## Task list

- [ ] **1. Lock the API contract — extend `openapi.yaml` and regenerate types**
  - Add `freshnessMeta` (object with `lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, `slaState` enum `within_sla|stale|unavailable`) to the existing `MunicipalityRulesResponse` schema
  - Add `PUT /api/v1/admin/municipalities/{municipalityId}/freshness` with `VerificationWriteRequest` body, IAM auth, 200/400/403/404/500 responses; response excludes `verifierIdentity`
  - Extend `ScanFeedbackRequest` schema with optional nullable `slaState` enum
  - Run `scripts/generate-api-types.sh`; commit regenerated `generated/typescript/api-types.ts` + Kotlin sources
  - _Requirements: 1.1, 1.2, 2.4, 2.5, 4.1, 5.1, 7.3_
  - _Design: §API Specifications, §Components and Interfaces_
  - _Owner: Senior Backend Engineer (contract author); both BE + FE consume the regenerated types_
  - _Blocks: Tasks 2, 3, 4, 5, 6, 9, 10, 11_

### Slice B — Freshness Pipeline (Backend / Infra)

- [ ] **2. Provision DynamoDB audit table, SSM parameters, and IAM scope changes**
  - Add `VerificationAuditTable` (`gomi-verification-audit-{stage}`) to `backend/infrastructure/template.yaml`: PK `municipalityId`, SK `VERIFICATION#{ISO-8601}#{uuidv4}`, on-demand billing, PITR enabled, AWS-managed KMS (CMK for prod)
  - Add SSM parameters `/gomi/{stage}/sla-baseline-days` (default 90) and `/gomi/{stage}/sla-change-response-days` (default 14)
  - Scope `ImageScanLambdaExecutionRole` for the audit table to `dynamodb:PutItem` only (append-only — no `GetItem`/`Query`/`Scan`)
  - Add `ssm:GetParameter` on the two SSM params to the Lambda role
  - _Requirements: 2.3, 3.1, 3.2, 8.1, 8.2_
  - _Design: §Data Architecture, §Security Architecture §2_
  - _Owner: Senior Backend Engineer_

- [ ] **3. Provision content-ops IAM role + Cognito deny for admin route**
  - Create IAM role `gomi-content-ops-{stage}` with `GomiContentOpsPolicy` allowing `execute-api:Invoke` on `PUT /api/v1/admin/municipalities/*/freshness` only (session ≤ 1 h)
  - Add explicit `Deny` statement on `PUT /api/v1/admin/*` to both `CognitoAuthenticatedRole` and `CognitoUnauthenticatedRole` inline policies
  - Register admin route in API Gateway with `Auth: AWS_IAM`
  - Document `sts:AssumeRole` flow for content-ops in `knowledge-base/runbooks/content-ops-access.md`
  - _Requirements: 2.4, 7.3, 8.3_
  - _Design: §Security Architecture §3_
  - _Owner: Senior Backend Engineer_

- [ ] **4. Implement `FreshnessService` (slaState compute + SSM thresholds)**
  - `service/FreshnessService.kt`: `computeSlaState(lastVerifiedAt, publishedRuleChangeAt)`, `isBaselineBreach`, `isChangeResponseBreach`
  - Reads SSM thresholds once, caches in Lambda instance; falls back to compiled-in defaults (90 / 14) on SSM failure with WARN log
  - Unit tests: `within_sla` at 89 d, `stale` at 91 d baseline, `stale` at change-response breach (`publishedRuleChangeAt`=13 d ago + `lastVerifiedAt`=15 d ago), `within_sla` when re-verified same day as change, `unavailable` when `lastVerifiedAt` null, SSM-default fallback path
  - _Requirements: 3.1, 3.2, 3.3, 4.1, 4.2_
  - _Design: §Components and Interfaces (FreshnessService), §Correctness Properties 2_
  - _Owner: Senior Backend Engineer_
  - _Depends on: Task 1_

- [ ] **5. Extend `RulesService` + `RulesRepository` for freshness read path**
  - `model/DataModels.kt`: add `FreshnessMeta` Kotlin data class (no `verifierIdentity` property — compile-time isolation per Correctness Property 1); extend `MunicipalityRulesResponse` with `freshnessMeta: FreshnessMeta`
  - `repository/RulesRepository.kt`: extend META item read to project freshness attributes (`lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, `sourceSnapshotRef`, `publishedRuleChangeAt`). **Do not** project `verifierIdentity` in the read path used by `RulesService`
  - `service/RulesService.kt`: build `FreshnessMeta` from the META item, call `FreshnessService.computeSlaState`; wrap freshness read in try/catch → on error, return `slaState=UNAVAILABLE` (never propagate 5xx)
  - Emit best-effort `sla_breach_observed` analytics event + `SlaBreachObserved` CW metric on `STALE` once per `(municipalityId, calendar day UTC)` per Lambda instance — **task-note: Phase 4 MINOR-001** (best-effort dedup acknowledged; Enterprise Architect amends Req 4.4 wording in Phase 11)
  - Emit `FreshnessUnavailableServed` metric on `UNAVAILABLE` responses
  - Unit tests on `RulesServiceTest.kt`: `slaState` present in response; `verifierIdentity` absent (assert via Jackson serialised JSON); freshness read error → `slaState: unavailable` (no exception); `sla_breach_observed` emitted on first stale; not re-emitted on second call same day
  - _Requirements: 1.1, 1.2, 1.4, 2.4, 2.5, 4.1, 4.2, 4.3, 4.4, 7.3_
  - _Design: §Components and Interfaces, §Sequence Diagrams (Happy Path, Stale, Unavailable), §Error Handling, §Correctness Properties 1, 2, 3_
  - _Owner: Senior Backend Engineer_
  - _Depends on: Tasks 1, 4_

- [ ] **6. Implement `AdminRouter` + `VerificationAuditRepository`**
  - `model/VerificationModels.kt`: `VerificationWriteRequest`, `VerificationAuditEntry`
  - `repository/VerificationAuditRepository.kt`: `appendAuditRow(entry)` — `PutItem` on `gomi-verification-audit-{stage}` with SK `VERIFICATION#{ISO-8601}#{uuidv4}`
  - `repository/RulesRepository.kt`: add `readFreshnessFields(municipalityId)` (returns previous `lastVerifiedAt`) and `writeFreshnessFields(municipalityId, request)` (UpdateItem on META)
  - `router/AdminRouter.kt`: `PUT /api/v1/admin/municipalities/{municipalityId}/freshness`
    - Validate non-blank `lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, `verifierIdentity`; ISO-8601 dates
    - **Reject** `sourceUrl` values whose query params contain any key in `{userId, scanId, sessionId, identityId, token, auth, jwt, sig}` (Security §1)
    - Verify caller principal ARN begins with `arn:aws:sts::{AccountId}:assumed-role/gomi-content-ops-` (belt-and-suspenders); else `403`
    - Read previous `lastVerifiedAt` → write META → append audit row; on META success + audit failure, log + emit `VerificationAuditWriteFailed` metric, still return 200 (audit recoverable from logs)
    - Response: `FreshnessMeta` (no `verifierIdentity`, no `sourceSnapshotRef`)
  - Unit tests `AdminRouterTest.kt`: 200 happy + audit row written; 400 blank required field; 400 invalid ISO-8601; 400 PII-shaped query param in `sourceUrl`; 403 non-content-ops principal; 404 unknown municipality; 500 DynamoDB; audit write failure leaves META committed
  - **No `verifierIdentity` in any log line** — code-review checklist
  - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.6, 7.3, 8.3_
  - _Design: §Components and Interfaces (AdminRouter), §Sequence Diagrams (Verification Write), §Security Architecture §1, §2, §3, §Correctness Properties 1, 5_
  - _Owner: Senior Backend Engineer_
  - _Depends on: Tasks 1, 2, 3_

- [ ] **7. Extend scan-feedback DTO with `slaState`**
  - `model/ScanModels.kt`: add nullable `slaState: String?` to `ScanFeedbackRequest`
  - `service/ScanFeedbackService.kt`: validate value is one of `within_sla|stale|unavailable` or null; reject invalid with `400`
  - Persist `slaState` on the `gomi-scan-feedback-{stage}` item (schemaless — no migration)
  - Unit tests on `ScanFeedbackServiceTest.kt`: valid values accepted, null accepted, invalid string rejected
  - _Requirements: 5.1, 5.2, 5.3_
  - _Design: §API Specifications (Extended scan-feedback), §Data Models, §Correctness Properties 7_
  - _Owner: Senior Backend Engineer_
  - _Depends on: Task 1_

- [ ] **8. Observability — logs, metrics, alarm, dashboard wiring**
  - Structured INFO log on every rules fetch with `municipalityId, slaState, daysSinceVerification`
  - Structured WARN on freshness absent / SLA breach observed / SSM threshold fallback
  - CW metrics: `SlaBreachObserved` (per-municipality), `FreshnessUnavailableServed`, `VerificationWritten`, `VerificationAuditWriteFailed`
  - `SlaBreachAlarm` (≥1 breach for any municipality in 24 h) → SNS `AlarmNotificationTopic`
  - Runbook entry in `knowledge-base/runbooks/cloudwatch-alarms.md` covering response steps
  - **Phase 4 MINOR-004 coordination task:** open a follow-up ticket with the spec-37 ([AIW-37](/AIW/issues/AIW-37)) accuracy-dashboard owner to surface a segmented `userReportedWrongRate` by `slaState`. No paging alarm for v1.
  - _Requirements: 4.4, 5.3, 8.1, 8.2_
  - _Design: §Observability, §Phase 4 Findings Carried (MINOR-004)_
  - _Owner: Senior Backend Engineer_
  - _Depends on: Tasks 5, 6_

### Slice A — Citation UI (Frontend / Content)

- [ ] **9. Author bilingual EN/JA microcopy for `source.*`, `sla.*`, `onboarding.sourceFidelityIntro`**
  - Add the 11 keys from §Copy Intent (`source.citation.label`, `source.citation.languageDisclosure.jaOnly`, `source.citation.lastVerified`, `source.stale.label`, `source.stale.confirmationPrompt`, `source.unavailable.label`, `sla.page.title`, `sla.page.commitment`, `sla.page.breachPosture`, `sla.page.activelyCoveredMunicipalities`, `onboarding.sourceFidelityIntro`) to `frontend/src/i18n/en.json` and `ja.json`
  - Verify EN ↔ JA key parity (no orphaned keys); JA renders comfortably at 375 px
  - Update voice/tone guide: add the four new terms from §Copy Intent (Municipal source / Not recently verified / AI estimate / Ward or city office)
  - _Requirements: 1.3, 3.4, 3.5, 6.1, 6.2, 6.3_
  - _Design: §Copy Intent_
  - _Owner: Senior Content Writer_

- [ ] **10. Implement `SourceCitation` React component**
  - `frontend/src/components/SourceCitation.tsx`: three states (within_sla / stale / unavailable) per design
  - **Outbound link hygiene:** static `rel="noopener noreferrer"`; never append query params to `sourceUrl`; assert via URL regex in tests
  - JA-only disclosure rendered only when `sourceLanguage === 'ja' && locale === 'en'`
  - `aria-live="polite"` for state changes; WCAG 2.1 AA contrast on all three states
  - Defensive render: missing `freshnessMeta` or unknown `slaState` → render `unavailable`
  - Unit tests `SourceCitation.test.tsx`: all three states render; `rel="noopener noreferrer"` asserted; no query params on link (regex); JA-only disclosure on EN locale only; `aria-live` region present; axe scan 0 violations in EN and JA renders; defensive default on bad input
  - _Requirements: 1.1, 1.2, 1.3, 1.4, 4.1, 4.2, 4.3, 4.5, 6.1, 6.2, 6.3, 7.1, 7.2_
  - _Design: §Components and Interfaces (SourceCitation), §Copy Intent, §Correctness Properties 4, 6, §Security Architecture §1_
  - _Owner: Senior Frontend Engineer_
  - _Depends on: Tasks 1, 9_

- [ ] **11. Integrate `SourceCitation` into `ScanResultsPage`**
  - Render `SourceCitation` inline per result, using `freshnessMeta` returned by `GET /rules/{cityId}`
  - Pass currently-rendered `slaState` through to the `ScanFeedbackPrompt` payload (Correctness Property 7) so the value submitted matches the value the user saw
  - Stale state must **not block** rendering of classification or category info (Correctness Property 6)
  - Update component-level tests on `ScanResultsPage` to assert the citation block is mounted for each result variant
  - _Requirements: 1.1, 1.4, 4.3, 5.1_
  - _Design: §Components and Interfaces, §Correctness Properties 6, 7_
  - _Owner: Senior Frontend Engineer_
  - _Depends on: Task 10_

- [ ] **12. Build `AccuracySlaPage` (in-app /about route)**
  - `frontend/src/pages/AccuracySlaPage.tsx`, route `/about/accuracy-sla`
  - Render `sla.page.title`, `sla.page.commitment`, `sla.page.breachPosture`, `sla.page.activelyCoveredMunicipalities` (EN + JA)
  - **Phase 4 MINOR-003 — actively-covered municipality list (resolved per PO disposition):** consume a build-time static JSON file at `frontend/src/content/actively-covered-municipalities.json` (schema: `[{ id, nameEn, nameJa }]`). Seed initial entries from the current production `gomi-bunrui-rules` META scan; document update procedure in `knowledge-base/runbooks/actively-covered-municipalities.md` (content-ops edits JSON → frontend release)
  - Footer link from `/about` and (Task 13) marketing-site footer
  - Unit tests: page renders EN and JA; static list renders; axe 0 violations
  - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5_
  - _Design: §Architecture (System Context), §Copy Intent, §Phase 4 Findings Carried (MINOR-003)_
  - _Owner: Senior Frontend Engineer + Senior Content Writer (seed list)_
  - _Depends on: Task 9_

- [ ] **13. Marketing-site accuracy commitment page**
  - `website/accuracy-commitment.html`: static HTML page (Vite + Tailwind + i18n script tags) mirroring in-app commitment copy from Task 9 (EN + JA via the existing website i18n pattern) *(Note: file is `.html`, not `.astro` — the marketing site is Vite + Tailwind, not an Astro project. Original task wording said `AccuracySla.astro`; MINOR-008 corrects to `accuracy-commitment.html`. See also §Architecture System Context.)*
  - Link from website footer
  - _Requirements: 3.4, 3.5_
  - _Design: §Architecture (System Context — Marketing site)_
  - _Owner: Senior Frontend Engineer + Senior Content Writer_
  - _Depends on: Task 9_

- [ ] **14. Wire `slaState` into scan-feedback submission**
  - `frontend/src/components/ScanFeedbackPrompt.tsx`: include the `slaState` value rendered by `SourceCitation` for the same result in the `POST /scan-feedback` payload
  - Update unit tests to assert payload includes `slaState` matching the citation render
  - On 400 due to invalid `slaState`, surface via existing `error-invalid-category` path (graceful — do not block user)
  - _Requirements: 5.1, 5.2, 5.4_
  - _Design: §API Specifications (Extended scan-feedback), §Correctness Properties 7_
  - _Owner: Senior Frontend Engineer_
  - _Depends on: Tasks 1, 7, 11_

- [ ] **15. Update mock server**
  - `mock-server/server.js`: return `freshnessMeta` on `GET /api/v1/rules/{cityId}` with seeded fixtures covering all three states (within_sla / stale / unavailable)
  - Implement `PUT /api/v1/admin/municipalities/{id}/freshness` (200 + 400 fixtures); not IAM-enforced in mock but reject malformed payloads
  - Accept `slaState` on `POST /api/v1/scan-feedback`
  - _Requirements: 2.6, 4.1, 5.1_
  - _Design: §API Specifications_
  - _Owner: Senior Frontend Engineer_
  - _Depends on: Task 1_

### Cross-slice — verification, docs, validation

- [ ] **16. E2E suite — citation + accuracy-sla page**
  - `e2e/tests/flows/source-citation.spec.ts`:
    - Fresh citation: link present + `rel` attribute + date
    - Stale: amber indicator + confirm-ward prompt
    - Unavailable: "AI estimate" label, no link
    - Citation link has no PII query params (URL regex assertion)
    - JA-only disclosure shown on EN locale when source is JA
  - `e2e/tests/flows/accuracy-sla-page.spec.ts`: `/about/accuracy-sla` renders in EN and JA; footer link reachable; marketing-site equivalent reachable
  - _Requirements: 1.1, 1.3, 1.4, 3.4, 3.5, 4.1, 4.2, 7.1_
  - _Design: §Testing Strategy (E2E)_
  - _Owner: Senior QA Engineer_
  - _Depends on: Tasks 11, 12, 13_

- [ ] **17. Accessibility regression — axe at 375 px × 3 slaState × 2 locales**
  - Run axe on `ScanResultsPage` for each `slaState` (within_sla / stale / unavailable) × locale (EN / JA) — 6 combos, 0 violations
  - JA layout check: source label + freshness date do not truncate under 40-character municipality names
  - _Requirements: 6.1, 6.2, 6.3_
  - _Design: §Testing Strategy (Accessibility Regression)_
  - _Owner: Senior QA Engineer_
  - _Depends on: Task 11_

- [ ] **18. Documentation, knowledge base, runbooks, auto-memory**
  - `knowledge-base/runbooks/cloudwatch-alarms.md`: add `SlaBreachAlarm` and `VerificationAuditWriteFailed`
  - `knowledge-base/runbooks/content-ops-access.md`: `sts:AssumeRole` flow for `gomi-content-ops-{stage}`
  - `knowledge-base/runbooks/actively-covered-municipalities.md`: how to update the static JSON file
  - `knowledge-base/runbooks/audit-table-erasure.md`: APPI right-of-deletion procedure for `verifierIdentity` (per Security §2)
  - `docs/`: how to interpret `slaState` in `GET /rules/{cityId}`; admin verification write usage
  - `MEMORY.md`: new audit table name, admin endpoint, content-ops role name, SSM threshold params, static list path
  - _Requirements: 8.1, 8.2_
  - _Design: §Security Architecture §2, §Observability_
  - _Owner: Senior Content Writer + Senior Backend Engineer_

- [x] **19. Final validation — build, tests, smoke, OpenAPI freshness**
  - `cd backend && ./gradlew build` green
  - `cd frontend && npm run build && npm test` green
  - `cd e2e && npm test` green (new flows + regression suite)
  - OpenAPI freshness check green in CI
  - Production smoke checks: `GET /rules/{cityId}` returns `freshnessMeta`; `AccuracySlaPage` reachable in EN + JA
  - All Phase 4 MINOR findings dispositioned (see §Phase 4 Findings Carried)
  - _Requirements: Completion Criteria_
  - _Owner: Senior QA Engineer + Tech Lead_
  - _Depends on: Tasks 2–17_

---

## Phase 4 Findings Carried

| ID | Finding | Disposition | Tracking |
|---|---|---|---|
| MINOR-001 | `sla_breach_observed` dedup is best-effort per Lambda instance — over-counts on cold starts | Accepted as task-note in Task 5; operational metric, not billing/compliance signal. Enterprise Architect amends Req 4.4 wording in Phase 11. | Task 5 note + Phase 11 EA action |
| MINOR-002 | Req 2.5 says "omit `freshnessMeta` when unavailable" vs. design's always-present `{ ..., slaState: unavailable }` shape | Design is canonical (always present, nulls + `unavailable`). EA updates Req 2.5 in Phase 11. | Phase 11 EA action; tests in Tasks 5, 10 assert always-present shape |
| MINOR-003 | `AccuracySlaPage` actively-covered municipality list — source of truth mechanism | **Resolved in Phase 5**: build-time static JSON at `frontend/src/content/actively-covered-municipalities.json`, updated via release. | Task 12 |
| MINOR-004 | Req 5.3 — counter-metric alert wiring | Roadmap-review item; segment `userReportedWrongRate` by `slaState` on the spec-37 accuracy dashboard ([AIW-37](/AIW/issues/AIW-37)). No paging alarm for v1. | [AIW-107](/AIW/issues/AIW-107) (coordination ticket) |

---

## Dependencies graph

```
Task 1 (OpenAPI + codegen)
   ├── Slice B
   │     ├── Task 2 (audit table + SSM + IAM)
   │     ├── Task 3 (content-ops IAM + Cognito deny)
   │     ├── Task 4 (FreshnessService)        ──┐
   │     ├── Task 5 (RulesService extend)     ──┼── Task 8 (observability)
   │     ├── Task 6 (AdminRouter + audit repo)──┘
   │     └── Task 7 (scan-feedback DTO extend)
   │
   └── Slice A
         ├── Task 9 (i18n copy)
         ├── Task 10 (SourceCitation)          ── depends on 9
         ├── Task 11 (ScanResultsPage integrate)── depends on 10
         ├── Task 12 (AccuracySlaPage)         ── depends on 9
         ├── Task 13 (marketing-site page)      ── depends on 9
         ├── Task 14 (slaState in feedback)    ── depends on 7, 11
         └── Task 15 (mock server)

Cross-slice
   ├── Task 16 (E2E)            depends on 11, 12, 13
   ├── Task 17 (axe regression) depends on 11
   ├── Task 18 (docs / KB)
   └── Task 19 (final validation) depends on 2–17
```

Slice A and Slice B run **in parallel** after Task 1.

---

## Status Summary

| # | Task | Slice | Status | Commit(s) | Owner |
|---|---|---|---|---|---|
| 1 | OpenAPI contract + codegen | shared | ✅ done | 62e727f | BE |
| 2 | Audit table + SSM + IAM | B | ✅ done | be7b2ed | BE |
| 3 | content-ops IAM + Cognito deny | B | ✅ done | be7b2ed | BE |
| 4 | `FreshnessService` | B | ✅ done | 89a89ab | BE |
| 5 | `RulesService` extend | B | ✅ done | 0a2c216 | BE |
| 6 | `AdminRouter` + audit repo | B | ✅ done | b4d2744 | BE |
| 7 | scan-feedback DTO extend | B | ✅ done | 6a9d867 | BE |
| 8 | Observability + alarm + dashboard ticket | B | ✅ done ([AIW-107](/AIW/issues/AIW-107) opened) | be7b2ed | BE |
| 9 | i18n copy (`source.*`, `sla.*`) | A | ✅ done | e9b5791 | FE |
| 10 | `SourceCitation` component | A | ✅ done | e9b5791 | FE |
| 11 | `ScanResultsPage` integration | A | ✅ done | e9b5791 | FE |
| 12 | `AccuracySlaPage` (in-app) | A | ✅ done | 5e52950 | FE |
| 13 | Marketing-site `accuracy-commitment.html` | A | ✅ done | 5e52950 | FE |
| 14 | `slaState` in scan-feedback payload | A | ✅ done | e9b5791 | FE |
| 15 | Mock server | A | ✅ done | 5e52950 | FE |
| 16 | E2E suite | x-slice | ✅ done | f96ff59 | QA |
| 17 | Axe regression | x-slice | ✅ done | f96ff59 | QA |
| 18 | Docs / KB / MEMORY | x-slice | ✅ done | AIW-134 | Content |
| 19 | Final validation | x-slice | ✅ done | AIW-141 | QA + TL |

---

## Handoff

```
spec: 38-source-fidelity-and-accuracy-sla
worktreePath: .worktree/38-source-fidelity-and-accuracy-sla
workItemPath: project/work-items/38-source-fidelity-and-accuracy-sla/
previousArtifact: design-review-summary.md
nextArtifactExpected: test-plan.md (Senior QA Engineer — Phase 5 task review / test coverage matrix)
gateResult: pending (Phase 5 gate signed by Senior Product Owner after QA)
```
