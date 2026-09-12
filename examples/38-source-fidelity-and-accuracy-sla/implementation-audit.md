# Implementation Audit — Spec 38 (Source-Fidelity & Accuracy-Update SLA)

> **Phase 9 — SDD pipeline**
> Auditor: Tech Lead ([AIW-70](/AIW/issues/AIW-70))
> Spec: `38-source-fidelity-and-accuracy-sla`
> Branch: `spec/38-source-fidelity-and-accuracy-sla`
> Inputs: `requirements.md`, `design.md`, `tasks.md`, `test-plan.md`; slice PRs from [AIW-67](/AIW/issues/AIW-67), [AIW-68](/AIW/issues/AIW-68), [AIW-69](/AIW/issues/AIW-69).

## Verdict

**`gateResult: pass`** — 0 CRITICAL, 0 MAJOR, 4 MINOR findings (all naming / wording reconciliations queued for Phase 11 Spec Maintenance).

## Commit-hash verification

All 10 commit hashes in `tasks.md` Status Summary resolve against `git log main..HEAD`:

| Task | Claimed | Verified |
|---|---|---|
| 1 | `62e727f` | feat(AIW-64): lock API contract for spec 38 source fidelity SLA ✅ |
| 2, 3, 8 | `be7b2ed` | feat(AIW-76): Tasks 2, 3, 8 — SAM infra + observability runbooks ✅ |
| 4 | `89a89ab` | feat(AIW-76): Task 4 — FreshnessService ✅ |
| 5 | `0a2c216` | feat(AIW-76): Task 5 — freshness read path in RulesService + RulesRepository ✅ |
| 6 | `b4d2744` | feat(AIW-76): Task 6 — AdminRouter + VerificationAuditRepository ✅ |
| 7 | `6a9d867` | feat(AIW-76): Task 7 — scan-feedback pipeline with slaState ✅ |
| 9–11, 14 | `e9b5791` | feat(AIW-64): Tasks 9–11, 14 — SourceCitation + i18n + ScanResultsPage ✅ |
| 12, 13, 15 | `5e52950` | feat(AIW-64): Tasks 12, 13, 15 — AccuracySlaPage, marketing site, mock server ✅ |
| 16, 17 | `f96ff59` | test(AIW-64): Tasks 16–17 E2E citation + axe regression ✅ |
| 18 | `a2c5cd3` | docs(AIW-64): add runbooks and how-to for spec 38 ✅ |

Task 19 (final validation) is correctly left `todo` — Phase 9 audit precedes Phase 10 QA review which precedes final validation.

## Correctness Properties verification

| # | Property | Evidence | Status |
|---|---|---|---|
| 1 | `verifierIdentity` never returned in any API response | `model/DataModels.kt:66–71` — `FreshnessMeta` has no `verifierIdentity` field (compile-time isolation). Comment at L62–64 names CP1. `VerificationWriteRequest`/`VerificationAuditEntry` (`VerificationModels.kt:13,32`) are write-path/audit-only types. | ✅ |
| 2 | `slaState` correctly computed from `lastVerifiedAt`, `publishedRuleChangeAt`, SSM thresholds | `service/FreshnessService.kt:39–60` — null/parse failure → `unavailable`; change-after-verification → `stale`; baseline-breach → `stale`; else `within_sla`. SSM cached per-instance (L77–89), defaults on SSM failure (L96–107). | ✅ |
| 3 | Freshness read errors return `unavailable`; never propagate 5xx | `service/RulesService.kt:146–174` — `buildFreshnessMetaFromFields` wraps `computeSlaState` in try/catch; on exception logs WARN, emits `FreshnessUnavailableServed`, returns `FreshnessMeta(slaState="unavailable")`. | ✅ |
| 4 | `SourceCitation` defaults to `unavailable` on missing/unknown `slaState` | `frontend/src/components/SourceCitation.tsx:26–31` — explicit allow-list with fallback to `'unavailable'`. | ✅ |
| 5 | META success + audit failure → log + metric + return 200 (audit recoverable from logs) | `router/AdminRouter.kt:128–140` — `VerificationAuditRepositoryException` caught, logged, `VerificationAuditWriteFailed` emitted; control flow continues to 200 response. | ✅ |
| 6 | Stale state does not block classification rendering | `pages/ScanResultsPage.tsx:340–375` — `SummaryCard` + `ComponentList` rendered unconditionally before `SourceCitation`; citation is a sibling block, not a parent gate. | ✅ |
| 7 | `slaState` in scan-feedback payload matches what the user saw | `pages/ScanResultsPage.tsx:65, 374` — `renderedSlaState` derived once and passed to both `SourceCitation` and `ScanFeedbackPrompt`. `ScanFeedbackPrompt.tsx:36, 72, 89` forwards into payload. *Minor wording note — see MINOR-005 below.* | ✅ (with MINOR-005 note) |

## Deliverable spot-checks

- **Infra (Task 2)** — `VerificationAuditTable` (`backend/infrastructure/template.yaml:713`) has PK `municipalityId` (S), SK `SK` (S), PAY_PER_REQUEST, PITR enabled, KMS SSE. Lambda role grants `dynamodb:PutItem` only (L1075–1077) — no Get/Query/Scan. SSM params present (L677, L690) with GetParameter scoped (L1066–1071).
- **Cognito deny (Task 3)** — Explicit `Deny` on `PUT /api/v1/admin/*` present on both `CognitoUnauthenticatedRole` (L225–230) and `CognitoAuthenticatedRole` (L264–269). `GomiContentOpsRole` (L985) with `execute-api:Invoke` scoped to PUT freshness route (L1005).
- **AdminRouter (Task 6)** — PII query-param regex (`PII_QUERY_PARAM_KEYS`, L47–48) covers all 8 spec'd keys. ARN prefix check `arn:aws:sts::{AccountId}:assumed-role/gomi-content-ops-` enforced (L211–215). `verifierIdentity` never logged (only municipalityId / sk appear in log lines).
- **Frontend (Tasks 9–14)** — `SourceCitation` 3-state UI with static `rel="noopener noreferrer"` (L73, L116), `aria-live="polite"` on all states (L34, L60, L107), JA-only disclosure gated on `sourceLanguage==='ja' && !isJapanese`. URL not mutated. `ScanFeedbackPrompt.slaState` plumbed into payload.
- **AccuracySlaPage (Task 12)** — page + static `frontend/src/content/actively-covered-municipalities.json` present.
- **Mock server (Task 15)** — `mock-server/server.js` returns `freshnessMeta`, accepts `slaState` on scan-feedback, implements admin freshness route.
- **Docs/runbooks (Task 18)** — all four runbooks present: `cloudwatch-alarms.md`, `content-ops-access.md`, `actively-covered-municipalities.md`, `audit-table-erasure.md`; `docs/how-to/freshness-verification.md` present.
- **Tests** — `AdminRouterTest` 12 cases, `FreshnessServiceTest` 10 cases, `RulesServiceTest` 8 cases, `ScanFeedbackServiceTest` 10 cases (Kotest StringSpec). `SourceCitation.test.tsx` 20 cases. E2E `source-citation.spec.ts` + `accuracy-sla-page.spec.ts` present.

## Findings

### MINOR-005 — `renderedSlaState` is the raw API value, not the value `SourceCitation` actually rendered

**Lens:** Cross-stack contract (CP7).
**Where:** `frontend/src/pages/ScanResultsPage.tsx:65` reads `rulesData?.freshnessMeta?.slaState` directly. `SourceCitation` may defensively render `unavailable` when given an unrecognised value (`SourceCitation.tsx:26–31`), but `ScanFeedbackPrompt` would still receive the original raw string.
**Impact:** Bounded — backend `ScanFeedbackService` validates against `{within_sla, stale, unavailable}` and returns 400 (gracefully handled per Task 14 wiring). Real-world risk is near zero because the only source for this value is the same API response.
**Disposition:** Note for Phase 11 EA — either tighten CP7 wording to "passed through unchanged from the API" or have `ScanResultsPage` reuse `SourceCitation`'s normalisation helper. No code change required to pass this gate.

### MINOR-006 — Audit table & content-ops role names use `${StackName}` suffix, not `{stage}`

**Lens:** Spec-vs-code drift.
**Where:** `template.yaml:715` (`${AWS::StackName}-verification-audit`) and L988 (`gomi-content-ops-${AWS::StackName}`). Tasks.md and design.md state `gomi-verification-audit-{stage}` / `gomi-content-ops-{stage}`.
**Impact:** None functionally — current convention is `${StackName}`-suffixed and is already captured in `MEMORY.md` ("gomi-bunrui-backend-verification-audit", "gomi-content-ops-gomi-bunrui-backend"). The spec wording is the outlier.
**Disposition:** Phase 11 EA — update §Data Architecture and §Security Architecture wording to match the `${StackName}` convention.

### MINOR-007 — `isChangeResponseBreach` semantics simplified vs. tasks.md wording

**Lens:** Spec-vs-code drift; ties to MINOR-001.
**Where:** `FreshnessService.kt:71–75` — any rule change after the last verification triggers `stale`, regardless of the `changeResponseDays` window. The `changeResponseDays` parameter is retained for future urgency grading but does not affect the stale determination.
**Impact:** Behaviourally stricter than spec ("change-response breach" was 14-day grace per Req 3.2). The implementation flags faster, never slower — safer default.
**Disposition:** Inline comment at L67–70 references MINOR-001 rationale. Phase 11 EA to reconcile Req 3.2 wording (likely accept the simplified semantics).

### MINOR-008 — Marketing accuracy page is `.html`, not `.astro`

**Lens:** Spec-vs-code drift.
**Where:** `website/accuracy-commitment.html` (HTML + Tailwind + i18n script tags). Task 13 wording said `website/src/pages/AccuracySla.astro`.
**Impact:** None — the marketing site is a Vite + Tailwind + i18n static site (per `MEMORY.md`), not an Astro project. The spec wording was incorrect from inception.
**Disposition:** Phase 11 EA — update Task 13 wording (or §Architecture System Context note) to reflect the actual stack.

## Severity tally

| Severity | Count |
|---|---|
| CRITICAL | 0 |
| MAJOR | 0 |
| MINOR | 4 (MINOR-005 through MINOR-008) |

Per Phase 9 severity rules, MINOR findings do not block the gate. Carried forward to Phase 11 EA spec maintenance.

## Handoff

```
spec: 38-source-fidelity-and-accuracy-sla
worktreePath: .worktree/38-source-fidelity-and-accuracy-sla
workItemPath: project/work-items/38-source-fidelity-and-accuracy-sla/
previousArtifact: tasks.md (Status Summary)
thisArtifact: implementation-audit.md
gateResult: pass
nextAssignee: Senior QA Engineer — Phase 10 QA Review
nextArtifactExpected: qa-review.md
findingsCarried:
  - MINOR-005 (CP7 wording / raw-vs-rendered slaState)
  - MINOR-006 (table & role name convention)
  - MINOR-007 (isChangeResponseBreach semantics; ties to MINOR-001)
  - MINOR-008 (marketing page filename .html vs .astro)
```
