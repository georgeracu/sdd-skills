# Design Review Summary — Source-Fidelity & Accuracy-Update SLA (Spec 38)

**Review Date:** 2026-05-13
**Reviewer:** Tech Lead (SDD Phase 4 gate authority)
**Documents Reviewed:**

- [`requirements.md`](./requirements.md) (Phase 2 output)
- [`design.md`](./design.md) (Phase 3 output, with Data / Security / Content Writer sign-offs)

**Source issue:** [AIW-63](/AIW/issues/AIW-63) · Parent: [AIW-56](/AIW/issues/AIW-56)

---

## Overall Status

**PASS — APPROVED WITH MINOR ISSUES**

The design fully covers all 7 requirements with traceable components, data models, and correctness properties. Architect sign-offs are recorded in §Data Architecture ([AIW-58](/AIW/issues/AIW-58)), §Security Architecture ([AIW-59](/AIW/issues/AIW-59)), and §Copy Intent ([AIW-60](/AIW/issues/AIW-60)). No CRITICAL or MAJOR findings. Four MINOR findings are filed below for Phase 5 (tasks) to absorb — none block the gate.

**Statistics:**

- Requirements reviewed: 7
- Acceptance criteria checked: 33
- Critical discrepancies: 0
- Major discrepancies: 0
- Minor discrepancies: 4

---

## Alignments Confirmed

### Requirements Coverage (7/7)

| Req | Coverage in design.md |
|-----|----------------------|
| 1 — Per-result Source_Citation | `SourceCitation` component renders three states; `aria-live`, reading order, `rel="noopener noreferrer"`, no PII in URL — §Components, §Testing, Correctness Property 4 |
| 2 — Freshness_Metadata on rules entity | `FreshnessMeta` DTO + META item extension; `verifierIdentity` excluded from DTO at type level; `AdminRouter` write path + `VerificationAuditRepository` for audit rows — §Data Architecture confirmed |
| 3 — Public Accuracy_Update_SLA copy | `AccuracySlaPage` (in-app) + `AccuracySla.astro` (marketing); 14/90 day values stated in `sla.page.commitment`; breach posture in `sla.page.breachPosture`; bilingual EN+JA ship-together — §Copy Intent signed off |
| 4 — SLA_Breach detection | `FreshnessService.computeSlaState` with baseline + change-response thresholds via SSM; `sla_breach_observed` analytics event; stale state non-blocking (Correctness Property 6) |
| 5 — Counter-metric integration | `ScanFeedbackRequest.slaState` extension; server-side enum validation; tied to spec 37 / [AIW-37](/AIW/issues/AIW-37) |
| 6 — Bilingual parity + a11y | All 11 i18n keys authored EN+JA in §Copy Intent; axe regression test plan at 375 px viewport on all three states × two locales |
| 7 — Privacy + outbound-link hygiene | `rel="noopener noreferrer"` static attribute (Correctness Property 4); `sourceUrl` query-parameter deny-list at write time (Security §1); `verifierIdentity` never projected (Correctness Property 1) |

### Architecture Consistency

- **Backend pattern:** Router → Service → Repository matches existing `image-scan-lambda` layout; new `AdminRouter`, `FreshnessService`, `VerificationAuditRepository` slot into the existing package structure.
- **API path convention:** `/api/v1/admin/municipalities/{id}/freshness` follows the existing `/api/v1/...` pattern; admin namespace is new but logically scoped.
- **DynamoDB pattern:** Single-table META item extension reuses the existing `PK=MUNICIPALITY#{id}, SK=META` schema with zero additional I/O on the hot path. Separate audit table follows the existing per-domain table convention (cf. `gomi-scan-feedback-{stage}`, `gomi-guest-sessions-{stage}`).
- **Auth pattern:** AWS IAM auth on the admin route is consistent with the project-wide SigV4 baseline (see CLAUDE.md: "All protected API endpoints use IAM auth (SigV4), not JWT"). Explicit `Deny` on both Cognito roles is a defence-in-depth control that fits the existing pool wildcard grant model.
- **SSM-parameter pattern:** `/gomi/{stage}/sla-baseline-days` and `/gomi/{stage}/sla-change-response-days` follow the existing `/gomi/${Stage}/guest-scan-limit` pattern.
- **i18n pattern:** `source.*` and `sla.*` namespaces fit the existing `en.json` / `ja.json` layout; placeholder syntax `{{date}}` matches i18next conventions already in use.

### Security Posture

Security Architect's verdict is recorded in §Security Architecture and is internally consistent with project security baselines:

- Outbound-link hygiene (`rel="noopener noreferrer"` + no query params) is sufficient for v1; CSP introduction is correctly out of scope.
- `verifierIdentity` PII treatment is enforced at three layers: type system (DTO omission), IAM (audit table is append-only for the Lambda role), and log discipline (§Observability prohibits the field in log lines).
- Dedicated `gomi-content-ops-{stage}` IAM role + explicit `Deny` on Cognito roles is the right defence model.
- APPI right-of-erasure obligation is correctly flagged for the production operational runbook.

### Copy Quality

All 11 user-facing copy slots are authored in EN + JA, with explicit lens citations (Plain Language, Bilingual Parity, Information Scent, Cultural Specificity, Inclusion, Microcopy Fundamentals, Ethics). Voice/tone additions ("not recently verified", "AI estimate", "ward or city office") are documented for downstream consistency. JA-only disclosure logic (suppress on JA locale) is correctly defined.

### Testing Strategy

Covers unit (Kotlin), unit (React/Vitest), E2E (Playwright), and a11y (axe) layers. Test names map to specific requirements and correctness properties (e.g., `SourceCitation.test.tsx` validates Correctness Property 4; `FreshnessServiceTest.kt` validates Property 2 and 3).

---

## Critical Discrepancies

**None found.**

---

## Major Discrepancies

**None found.**

---

## Minor Issues

### MINOR-001 — `sla_breach_observed` dedup deviates from "each unique" wording in Req 4.4

**Issue:** Req 4.4 says the service "SHALL emit a `sla_breach_observed` analytics event each unique `(municipalityId, day)` it serves a stale lookup." Design §Observability and §Key Design Decisions #6 implement this as "best-effort per Lambda instance per `(municipalityId, calendar day UTC)`" and explicitly accept "over-counting on cold starts."

**Impact:** Operationally negligible (this is a directional metric, not a billing or compliance signal — Decision #6 calls this out). However, the requirement reads as a stricter "exactly-once-per-day" guarantee than the design delivers.

**Recommendation:** No code change required. During Phase 5, surface the deviation in `tasks.md` and confirm with the Senior PO that the trade-off is acceptable as-stated. If exact-once is needed, an additional task to back the dedup with a small DynamoDB conditional-write per `(municipalityId, day)` key would be required — but this is unlikely to be worth the extra I/O.

**Owner:** Tech Lead (carry into Phase 5 task notes).

---

### MINOR-002 — Req 2.5 wording ("omit") vs. design return shape (`null`)

**Issue:** Req 2.5 says "the recycling-rules API SHALL omit citation fields from the response" when freshness metadata is missing. The design returns the fields with `null` values and `slaState: "unavailable"` (§API Specifications, second JSON example).

**Impact:** Functionally equivalent for the frontend's defensive default (Error Handling row: "freshnessMeta absent → render unavailable state"), and arguably more honest because the `slaState` is always present. But it is a literal departure from the requirement wording.

**Recommendation:** Treat the design's approach as the canonical contract — always-present `freshnessMeta` with `slaState` is more robust than schema branching. Update `requirements.md` (or capture an ADR / spec note) during Phase 11 (Spec Maintenance) so the requirement and contract agree. Senior PO sign-off on this gate verdict should include explicit acceptance of this interpretation.

**Owner:** Senior PO confirms; Enterprise Architect to capture in Phase 11.

---

### MINOR-003 — `Actively_Covered_Municipality` list maintenance on AccuracySlaPage (Req 3.6)

**Issue:** Req 3.6 says the SLA page "SHALL state which municipalities are Actively_Covered… and SHALL be updated whenever that set changes." Design §Copy Intent (`sla.page.activelyCoveredMunicipalities`) defines the intro label and notes "a dynamic list managed by content-ops" — but the design does not specify the mechanism (build-time data file? runtime fetch from the rules table? content-ops PR?).

**Impact:** Implementation ambiguity for Phase 5 tasks. Three viable approaches; pick one before estimating.

**Recommendation:** During Phase 5 task generation, add an explicit decision task (5-min: Tech Lead) to choose between: (a) static list rendered at build time from a JSON file under `frontend/src/content/`, (b) runtime fetch from `GET /api/v1/rules` index, or (c) content-ops PR cadence. Recommend (a) for v1 — lowest moving parts; the list of actively-covered municipalities changes monthly at most.

**Owner:** Tech Lead (resolve during Phase 5 task decomposition).

---

### MINOR-004 — Req 5.3 alert mechanism not specified

**Issue:** Req 5.3 says the Senior PO SHALL be alerted if `userReportedWrongRate` on `within_sla` answers exceeds the rate on `unavailable` answers by >2pp over a 30-day window. Design §Observability defines the `SlaBreachAlarm` (CloudWatch) but does not specify how the counter-metric differential alert is wired.

**Impact:** The KPI dashboard from spec 37 likely owns this view, and the alert is a quarterly roadmap-review item rather than a paging alarm. Not implementation-blocking for v1, but the wiring needs an owner.

**Recommendation:** During Phase 5, add a single task for the dashboard owner (likely tied to spec 37 / [AIW-37](/AIW/issues/AIW-37)) to surface the segmented `userReportedWrongRate` in the existing accuracy dashboard. No paging alarm needed for v1 — manual review at roadmap checkpoints is acceptable for the stated SLA cadence.

**Owner:** Tech Lead (Phase 5); coordinates with whoever owns the spec-37 dashboard.

---

## Architecture Consistency Assessment

The design lands cleanly inside the existing stack:

- **http4k + Kotlin Lambda** — new router/service/repository classes mirror existing patterns; no framework deviation.
- **DynamoDB single-table META extension** — re-uses existing `gomi-bunrui-rules` key schema; new audit table follows the per-domain table convention; sort-key shape (`VERIFICATION#{ISO}#{uuid}`) is consistent with existing time-ordered audit patterns.
- **IAM/SigV4 auth model** — admin route fits the project-wide "all protected endpoints use IAM" baseline; the new content-ops role is a clean addition.
- **React component pattern** — `SourceCitation.tsx` is a presentational component with explicit props; matches existing component shape in `frontend/src/components/`.
- **i18n key namespacing** — `source.*` and `sla.*` namespaces fit the existing flat `en.json` / `ja.json` layout.
- **SSM parameter pattern** — extends the same convention as `/gomi/${Stage}/guest-scan-limit`.

No new architectural paradigms; nothing to escalate to Head of Technology.

---

## Recommendations

**Status:** APPROVED WITH MINOR ISSUES.

1. **Proceed to Senior PO sign-off** on this Phase 4 gate verdict. The four MINOR items should be acknowledged but do not block.
2. **On PO sign-off, Tech Lead proceeds to Phase 5** (`tasks.md` generation) with the following Phase 5 inputs:
   - Resolve MINOR-003 (AccuracySlaPage list mechanism) during decomposition.
   - Carry MINOR-001 forward as a task-level note.
   - Coordinate MINOR-004 wiring with the spec-37 dashboard owner.
   - Capture MINOR-002 (Req 2.5 wording vs. design) for Enterprise Architect's Phase 11 maintenance pass.
3. **Provision worktree** after Phase 5 produces `tasks.md`: `git worktree add .worktree/38-source-fidelity-and-accuracy-sla -b spec/38-source-fidelity-and-accuracy-sla` from `main`.

---

## Review Checklist

- [x] Requirements coverage — all 33 acceptance criteria across 7 requirements have design support
- [x] Architecture consistency — design follows established http4k / DynamoDB / SigV4 / i18n / SSM patterns
- [x] API design quality — request/response schemas fully specified, status codes enumerated, auth model explicit
- [x] Data model consistency — meta-item extension and audit table conform to existing key schema patterns
- [x] Security & best practices — Security Architect signed off; PII handling, IAM scope, deny-list, KMS all addressed
- [x] Testing strategy — unit, E2E, and a11y layers cover all correctness properties and key requirements
- [x] Copy intent — all 11 user-facing copy slots authored EN+JA, lens-driven, Senior Content Writer signed off

---

## Next Steps

1. **Tech Lead** (this issue, [AIW-63](/AIW/issues/AIW-63)) routes to **Senior Product Owner** for Phase 4 gate sign-off on this `design-review-summary.md`.
2. **Senior PO** reviews, accepts (or requests change), and signs off via comment on this issue.
3. On PO sign-off, **Tech Lead** moves to Phase 5 (`tasks.md` generation) and provisions the worktree.

```
spec: 38-source-fidelity-and-accuracy-sla
worktreePath: n/a (still on main; provision after Phase 5)
workItemPath: project/work-items/38-source-fidelity-and-accuracy-sla/
previousArtifact: design.md
nextArtifactExpected: tasks.md
gateResult: pass (with 4 MINOR findings — no critical or major blockers)
```
