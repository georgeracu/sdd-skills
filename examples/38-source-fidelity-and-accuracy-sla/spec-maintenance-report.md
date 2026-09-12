# Spec Maintenance Report — Spec 38: Source-Fidelity & Accuracy-Update SLA

**Phase:** 11 — Spec Maintenance
**Author:** Enterprise Architect
**Date:** 2026-05-23
**Input artefact:** `qa-review.md` (Phase 10 gate: pass)
**Issue:** [AIW-140](/AIW/issues/AIW-140)

---

## Summary

Spec 38 introduced per-result source citations, municipal freshness metadata, a public Accuracy-Update SLA, breach detection, and scan-feedback counter-metric integration. Six MINOR findings carried from Phases 4, 9, and 10 were resolved by amending `requirements.md`, `design.md`, and `tasks.md` in the spec 38 work item. Cross-spec annotations were applied to three prior specs (23, 34, 37) whose interfaces are extended or modified by spec 38.

**Drift warning:** No drift warning raised. Three prior work items impacted — well below the 10-item threshold.

---

## MINOR Finding Resolutions

### MINOR-001 — `sla_breach_observed` best-effort dedup (Phase 4 → Req 4.4)

**Finding:** Req 4.4 used "each unique `(municipalityId, day)`" implying exact-once semantics. Design §Observability and Key Decision #6 implement this as best-effort per Lambda instance, acknowledging over-counting on cold starts.

**Action applied:** `requirements.md` Req 4.4 updated to state: "best-effort-deduplicated per `(municipalityId, calendar day UTC)` within a single Lambda instance; over-counting on cold starts is operationally acceptable for a directional metric." The rationale — exact-once dedup would require an additional DynamoDB conditional write disproportionate to the metric's non-billing, non-compliance nature — is now in the requirement text.

**Status:** Resolved — `requirements.md` (Req 4.4) updated.

---

### MINOR-002 — Req 2.5 "omit" vs. design always-present `freshnessMeta` shape (Phase 4)

**Finding:** Req 2.5 said the API "SHALL omit citation fields from the response" when freshness metadata is absent. The design and implementation always return `freshnessMeta` with null fields and `slaState: "unavailable"`.

**Action applied:** `requirements.md` Req 2.5 updated to state the always-present shape: "return `freshnessMeta` with null citation fields (`lastVerifiedAt`, `sourceUrl`, `sourceLanguage`) and `slaState: 'unavailable'`." The "omit" wording is replaced. Design is canonical; always-present is more robust for frontend defensive defaults.

**Status:** Resolved — `requirements.md` (Req 2.5) updated.

---

### MINOR-005 — CP7: raw API value vs. post-defensive-normalised render value (Phase 9)

**Finding:** Design §Correctness Properties CP7 said `slaState` persisted in feedback is "the value rendered to the user." `ScanResultsPage` actually passes the raw `freshnessMeta.slaState` API value to `ScanFeedbackPrompt`, not the value `SourceCitation` may have defensively normalised to `unavailable` for an unrecognised input.

**Action applied:** CP7 in `design.md` §Correctness Properties updated to clarify: the value is "the raw API value from `freshnessMeta.slaState`, passed through unchanged from `ScanResultsPage` to `ScanFeedbackPrompt`." Note added that `SourceCitation` may normalise an unrecognised value for rendering, but the feedback payload reflects the API value. Backend validates against the enum; invalid values rejected with 400 (gracefully handled). Real-world risk near-zero since the only source for this value is the same API response.

**Status:** Resolved — `design.md` (Correctness Property 7) updated.

---

### MINOR-006 — Resource naming: `${StackName}` suffix, not `{stage}` (Phase 9)

**Finding:** Earlier spec wording used `{stage}` as a suffix in DynamoDB table names and IAM role names (`gomi-verification-audit-{stage}`, `gomi-content-ops-{stage}`). Actual CloudFormation implementation uses `${AWS::StackName}` suffix: `${StackName}-verification-audit`, `gomi-content-ops-${StackName}` (e.g., `gomi-bunrui-backend-verification-audit`, `gomi-content-ops-gomi-bunrui-backend`).

**Action applied:** All occurrences in `design.md` (§Architecture System Context, §Data Architecture, §Security Architecture, §Sequence Diagrams, §Observability) updated to `${StackName}` convention. Also captured in project `MEMORY.md`.

**Status:** Resolved — `design.md` updated (applied in prior maintenance pass; confirmed consistent throughout worktree).

---

### MINOR-007 — `isChangeResponseBreach` semantics stricter than Req 4.1 (Phase 9)

**Finding:** Req 4.1 stated stale when `lastVerifiedAt` "exceeds 14 days after a recorded published rule change." Implementation (`FreshnessService.kt:71–75`) sets stale immediately when `publishedRuleChangeAt > lastVerifiedAt`, with no 14-day grace period. The `changeResponseDays` parameter is retained for future urgency grading but does not affect the stale determination.

**Action applied:** `requirements.md` Req 4.1 updated to state the simplified semantics: "OR whose recorded `publishedRuleChangeAt` is more recent than `lastVerifiedAt` (any published rule change that post-dates the last verification renders the municipality stale immediately, with no grace period)." The 14-day figure in Req 3.1 and the public SLA page is described as a content-ops response target, not a detection threshold.

**Status:** Resolved — `requirements.md` (Req 4.1) updated with accurate as-built semantics.

---

### MINOR-008 — Marketing page filename: `.html` not `.astro` (Phase 9)

**Finding:** `design.md` §Architecture Module Decomposition listed `website/src/pages/AccuracySla.astro`. The actual implementation is `website/accuracy-commitment.html` (Vite + Tailwind + i18n script tags). `tasks.md` Task 13 description also referenced `AccuracySla.astro`.

**Action applied:**
- `design.md` §Architecture updated: `accuracy-sla.html` with a MINOR-008 note.
- `tasks.md` Task 13 description updated: corrected to `website/accuracy-commitment.html` with explanatory note about the static HTML + Tailwind marketing site stack.

**Status:** Resolved — `design.md` and `tasks.md` (Task 13) updated.

---

### MINOR-009 — RS-1–5 missing from `RulesServiceTest.kt` (Phase 10 — coverage debt)

**Finding:** Spec-38 freshness test scenarios RS-1–RS-5 (slaState in response, verifierIdentity absent, freshness error → unavailable, breach metric emitted/deduped) were specified in `design.md` §Testing Strategy but not added to `RulesServiceTest.kt`. `RulesService` has 0% JaCoCo line coverage for spec-38 paths. E2E gate is green across all scenarios.

**Action applied:** No spec-doc change required. Coverage debt documented here and in the inline table note in `design.md` §Testing Strategy.

**Recommendation:** Tech Lead create a follow-up issue for Senior Backend Engineer to add RS-1–RS-5 as mock-based Kotest tests before the next Bedrock model or Lambda runtime change.

**Status:** Documented — follow-up issue recommended.

---

## Open Gaps (informational — not blocking Phase 12)

| ID | Description | Owner | Status |
|----|-------------|-------|--------|
| G-1 | SEC-5 negative test: IAM policy simulator confirming Cognito roles cannot invoke admin route (staging-only, not automated) | Security Architect / DevOps | Open — acceptable for v1 |
| G-2 | AX-10 screen-reader reading order (VoiceOver manual pass not completed) | Senior QA Engineer | Open — schedule before public launch |
| G-3 | Req 5.3 counter-metric alert to Senior PO — deferred to spec-37 dashboard (AIW-107) | Senior PO / spec-37 | Deferred by design |

---

## Cross-Spec Annotations Applied

### Spec 23 — Recycling Rules API

**`project/work-items/23-recycling-rules-api/tasks.md`** — `## Cross-Spec Annotations` section appended (prior maintenance pass) noting that spec 38 extends the OpenAPI contract (`MunicipalityRulesResponse` gains `freshnessMeta`), the infrastructure (new `${StackName}-verification-audit` table, admin IAM route, `gomi-content-ops-${StackName}` role), and the `RulesService` + `RulesRepository` implementation.

**`project/work-items/23-recycling-rules-api/design.md`** — Two inline annotations applied:
- `MunicipalityRulesResponse` data class: comment noting `freshnessMeta: FreshnessMeta` added by spec 38 (non-nullable; contains `lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, `slaState`; `verifierIdentity` absent at type level).
- DynamoDB META item schema: inline note that six freshness attributes were added by spec 38 to the existing `PK=MUNICIPALITY#{id}, SK=META` item.

### Spec 37 — Classification Feedback

**`project/work-items/37-classification-feedback/design.md`** — Two inline annotations applied:
- `ScanFeedbackRequest` DTO: comment noting `slaState: String?` added by spec 38 (nullable; values `within_sla|stale|unavailable|null`; invalid values rejected 400).
- `gomi-scan-feedback-{stage}` table schema: `slaState` row added to the DynamoDB attributes table with spec 38 attribution; explanatory note about the additive, schemaless extension and analytics purpose.

### Spec 34 — Municipality Data Seeding

**`project/work-items/34-municipality-data-seeding-and-schema-update/tasks.md`** — `## Cross-Spec Annotations` section present (applied in prior maintenance pass, confirmed on disk). Notes that seeded META items have null freshness fields and present as `slaState: unavailable` until a content-ops operator writes freshness data via the admin endpoint. No automated backfill.

---

## Prior Specs Scanned — No Annotation Needed

| Work item | Reason scanned | Annotation needed? |
|-----------|---------------|-------------------|
| `22-municipality-recycling-rules` | `RecyclingRulesPage` consumes `GET /rules/{cityId}` but does not display `freshnessMeta`; citation block is on `ScanResultsPage`. No spec 22 requirements superseded. | No |
| `25-guest-access` | Guest users see `SourceCitation` identically to registered users; no guest-specific API contract change. | No |
| `36-externalize-bedrock-prompts` | No interface overlap with freshness pipeline or citation UI. | No |
| `35-unified-seed-utility` | Freshness writes use the admin endpoint, not the seed utility. | No |
| `26-openapi-codegen` | The codegen pipeline was used by spec 38 (Task 1 extended `openapi.yaml`) but the pipeline spec itself is unchanged. | No |

---

## Impacted Work Items Summary

| Work Item | Files Annotated | Impacts |
|-----------|----------------|---------|
| `23-recycling-rules-api` | `tasks.md` (prior pass), `design.md` (this pass) | `MunicipalityRulesResponse` extended; META item extended |
| `37-classification-feedback` | `design.md` (this pass) | `ScanFeedbackRequest` DTO extended; scan-feedback table `slaState` added |
| `34-municipality-data-seeding-and-schema-update` | `tasks.md` (prior pass, confirmed present) | Freshness backfill responsibility documented |
| `38-source-fidelity-and-accuracy-sla` (own files) | `requirements.md`, `design.md`, `tasks.md` | MINOR-001/002/005/006/007/008 applied |

---

## Architectural Drift Warning

No drift warning. 3 prior work items impacted across 2 interface boundaries (API contract, feedback schema). Threshold for drift warning is >10 items.

---

## Result

10 items annotated or amended across 4 work items (3 prior specs + spec 38 own documents).

---

## Next Step

Phase 12 — Tech Lead opens PR for branch `spec/38-source-fidelity-and-accuracy-sla` → `main`.

```
spec: 38-source-fidelity-and-accuracy-sla
worktreePath: .worktree/38-source-fidelity-and-accuracy-sla
workItemPath: project/work-items/38-source-fidelity-and-accuracy-sla/
previousArtifact: qa-review.md
thisArtifact: spec-maintenance-report.md
gateResult: n/a (informational)
nextAssignee: Tech Lead — Phase 12 (PR)
nextArtifactExpected: PR opened against main
```
