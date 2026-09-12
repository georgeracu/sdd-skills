# Requirements Document

## Introduction

Every AI classification answer Gomi Bunrui returns must be **sourced** (cite the municipal rule it draws from) and **dated** (carry a last-verified timestamp), and the cadence on which we re-verify those rules must be a **public, observable SLA** — not a private content-ops habit. This commitment is derived from Finding 3 of [AIW-47](/AIW/issues/AIW-47): no JA waste-app competitor documents either source or update cadence, and プラ新法 ward-level rule churn guarantees that unmaintained rulesets drift within 12–24 months. Brainstorm is at [`brainstorm.md`](./brainstorm.md) and `docs/superpowers/specs/2026-05-13-source-fidelity-and-accuracy-sla-design.md`.

In scope for v1:

1. Per-result citation surface on `ScanResultPage` (source label, link, last-verified date, language disclosure, honest stale/unavailable states).
2. Per-municipality freshness metadata stored on the recycling-rules entity (last-verified-at, source-url, verifier identity, source-snapshot reference).
3. A public **Accuracy-Update SLA** with a stated cadence and a stated breach posture, surfaced both in onboarding/marketing copy and in-product when breached.
4. Breach detection and honest in-product degradation when freshness exceeds SLA.
5. Bilingual (EN/JA) parity on every user-visible surface, including honest disclosure that municipal source URLs are JA-only when that is the case.
6. Counter-metric integration with [AIW-37](/AIW/issues/AIW-37) scan-feedback so cited-answer accuracy is observable.

Out of scope for v1: automated rule-change *detection* (web-scraping municipal portals — manual/Content-Writer-driven for v1); coverage expansion to new municipalities; language expansion beyond EN/JA; monetisation tiers on cadence.

Applies to both **guest** and **registered** users; both **EN** and **JA** locales; all municipalities currently served by the recycling-rules service.

## Glossary

- **Source_Citation**: The user-visible label, optional outbound link, and language disclosure attached to a classification result, identifying the municipal rule or AI-estimate basis for that result.
- **Last_Verified_At**: The ISO-8601 timestamp at which a human verifier or content-ops agent last confirmed the municipality's rules against the cited source.
- **Source_Snapshot_Ref**: A stable reference (URL + retrieved-at + content hash, or attachment id) to the municipal-portal content the verifier consulted; used so a future reader can compare against today's portal.
- **Verifier_Identity**: The agent or human who performed the last verification; recorded but **not** displayed to the end user.
- **Freshness_Metadata**: The tuple `{lastVerifiedAt, sourceUrl, sourceLanguage, sourceSnapshotRef, verifierIdentity}` attached to a municipality's rules.
- **Accuracy_Update_SLA**: The public commitment to re-verify rules within a stated number of calendar days. v1 launch values: **14 days** after a *published* municipal rule change for actively-covered wards; **90 days** rolling baseline re-verification for every actively-covered ward regardless of known change.
- **Actively_Covered_Municipality**: Any municipality whose rules are queryable via the recycling-rules API ([AIW-23](/AIW/issues/AIW-23)).
- **SLA_Breach**: A municipality whose `lastVerifiedAt` is older than the SLA threshold for its actively-covered state.
- **Source_Unavailable_State**: The honest UI state for AI-only answers with no rule-mapped municipal source.
- **Stale_Verification_State**: The honest UI state for cited answers whose municipality is in SLA_Breach.
- **Accuracy_SLA_Page**: The public page (in the marketing site or in-app `/about` surface) where the SLA is published in both EN and JA.

## Requirements

### Requirement 1: Per-result Source_Citation on every classification

**User Story:** As a resident reading a classification result, I want to see *where* the answer comes from and *when* it was last verified, so that I can decide how much to trust it before I act.

#### Acceptance Criteria

1. THE `ScanResultPage` SHALL render a Source_Citation block on every classification result.
2. WHEN the user's municipality has Freshness_Metadata AND `lastVerifiedAt` is within SLA, THE Source_Citation SHALL display the municipal source label, an outbound link to the source URL, and the `lastVerifiedAt` date formatted in the user's locale.
3. WHEN the source URL points to a JA-only municipal portal AND the user's locale is EN, THE Source_Citation SHALL append an explicit "(in Japanese)" disclosure next to the link.
4. WHEN the classification has no rule-mapped municipal source (AI-only answer), THE Source_Citation SHALL render the Source_Unavailable_State with copy "AI estimate — no municipal source mapped for this item yet" (EN) / equivalent JA copy.
5. WHEN the user's municipality is in SLA_Breach, THE Source_Citation SHALL render the Stale_Verification_State, including the actual `lastVerifiedAt` date and copy that prompts the user to confirm with their ward.
6. THE Source_Citation SHALL meet WCAG 2.1 AA contrast and SHALL announce its content to screen readers in reading order: source label → freshness state → link disclosure.
7. THE Source_Citation outbound link SHALL open with `rel="noopener noreferrer"` and SHALL NOT include any user-identifying query parameters.

### Requirement 2: Freshness_Metadata on the recycling-rules entity

**User Story:** As the content-ops verifier, I need every municipality's rule set to carry the metadata I need to defend the citation, so that the in-product surface is backed by real records.

#### Acceptance Criteria

1. THE recycling-rules entity for every Actively_Covered_Municipality SHALL carry a Freshness_Metadata tuple with non-null `lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, and `verifierIdentity`.
2. THE recycling-rules entity SHALL carry an optional `sourceSnapshotRef` and SHALL be populated for every verification performed after the v1 launch date.
3. WHEN a verifier records a new verification, THE recycling-rules write path SHALL update `lastVerifiedAt` to the verification timestamp and SHALL append a verification audit row capturing the previous and new values.
4. THE `verifierIdentity` field SHALL be stored but SHALL NOT be returned in any user-facing API response.
5. IF a municipality lacks Freshness_Metadata at read time, THEN the recycling-rules API SHALL return `freshnessMeta` with null citation fields (`lastVerifiedAt`, `sourceUrl`, `sourceLanguage`) and `slaState: "unavailable"`; the front end SHALL render the Source_Unavailable_State for that municipality's results. *(Amended from Phase 4 MINOR-002: the design's always-present `freshnessMeta` shape is canonical over the original "omit" wording; always-present is more robust for frontend defensive defaults.)*
6. THE recycling-rules API SHALL expose `lastVerifiedAt`, `sourceUrl`, `sourceLanguage`, and a derived `slaState ∈ {within_sla, stale, unavailable}` for each rule lookup.

### Requirement 3: Public Accuracy_Update_SLA copy

**User Story:** As a prospective or new user reading about Gomi Bunrui, I want to see the commitment the team is willing to make publicly about rule freshness, so that I know what I am signing up for and can hold the team to it.

#### Acceptance Criteria

1. THE Accuracy_SLA_Page SHALL state the v1 launch SLA values in plain language in both EN and JA: re-verify within **14 days** of a published municipal rule change for actively-covered wards; **90 days** rolling baseline re-verification for every actively-covered ward.
2. THE Accuracy_SLA_Page SHALL state the breach posture: when the team misses the SLA, the affected ward's in-product answers display the Stale_Verification_State until re-verification completes.
3. THE Accuracy_SLA_Page SHALL be linked from the in-app `/about` (or equivalent) surface AND from the marketing-site footer.
4. THE Accuracy_SLA_Page copy SHALL be authored or signed off by the Senior Content Writer ([AIW-22](/AIW/issues/AIW-22)).
5. THE Accuracy_SLA_Page SHALL ship EN and JA together; neither locale SHALL be published without the other.
6. THE Accuracy_SLA_Page SHALL state which municipalities are Actively_Covered at the time of publication, and SHALL be updated whenever that set changes.

### Requirement 4: SLA_Breach detection and in-product surfacing

**User Story:** As a resident about to act on a sorting answer, I want the app to tell me honestly when the underlying rule has not been re-verified recently, so that I do not act on stale information without knowing.

#### Acceptance Criteria

1. THE recycling-rules service SHALL compute `slaState = stale` for any Actively_Covered_Municipality whose `lastVerifiedAt` exceeds **90 days** at request time (baseline breach), OR whose recorded `publishedRuleChangeAt` is more recent than `lastVerifiedAt` (any published rule change that post-dates the last verification renders the municipality stale immediately, with no grace period). *(Amended from Phase 9 MINOR-007: `isChangeResponseBreach` implementation triggers stale as soon as `publishedRuleChangeAt > lastVerifiedAt`. The 14-day figure in Req 3.1 and the public SLA page is a content-ops response target, not a detection grace period.)*
2. WHEN `slaState = stale`, THE `ScanResultPage` SHALL render the Stale_Verification_State on the affected result before the user can act on it.
3. THE Stale_Verification_State SHALL NOT block the user from seeing the AI answer; it SHALL provide context, not a hard gate.
4. THE recycling-rules service SHALL emit a `sla_breach_observed` analytics event on each stale lookup, best-effort-deduplicated per `(municipalityId, calendar day UTC)` within a single Lambda instance; over-counting on cold starts is operationally acceptable for a directional metric. *(Amended from Phase 4 MINOR-001: exact-once-per-day dedup would require an additional DynamoDB conditional write, which is disproportionate for a non-billing, non-compliance signal.)*
5. THE Stale_Verification_State copy SHALL be authored or signed off by the Senior Content Writer ([AIW-22](/AIW/issues/AIW-22)) in both EN and JA.

### Requirement 5: Counter-metric integration with scan feedback

**User Story:** As the product owner of the accuracy KPI, I want to know whether cited answers are *more or less* often reported wrong than uncited answers, so that the SLA is a measurable promise and not a feel-good label.

#### Acceptance Criteria

1. WHEN a scan-feedback event is recorded ([AIW-37](/AIW/issues/AIW-37)), THE Scan_Feedback_Service SHALL persist the `slaState` (`within_sla` | `stale` | `unavailable`) of the citation that was displayed at decision time.
2. THE accuracy-KPI dashboard SHALL expose `userReportedWrongRate` segmented by `slaState`.
3. IF the `userReportedWrongRate` on `slaState = within_sla` answers exceeds the rate on `slaState = unavailable` answers by more than 2 percentage points over any rolling 30-day window, THEN the Senior Product Owner SHALL be alerted and the SLA cadence SHALL be reviewed at the next roadmap checkpoint.

### Requirement 6: Bilingual parity and accessibility

**User Story:** As an EN-speaking resident and as a JA-speaking resident, I want the same citation and SLA surfaces with locale-honest copy, so that the trust commitment works in either language.

#### Acceptance Criteria

1. EVERY user-visible surface introduced by this spec (Source_Citation, Stale_Verification_State, Source_Unavailable_State, Accuracy_SLA_Page, onboarding intro copy) SHALL ship EN and JA together.
2. WHEN a source URL points to a JA-only municipal portal, THE EN surface SHALL disclose that explicitly; THE EN surface SHALL NOT imply an EN destination exists.
3. ALL copy on these surfaces SHALL be authored or signed off by the Senior Content Writer ([AIW-22](/AIW/issues/AIW-22)).
4. ALL surfaces SHALL pass axe accessibility scans with zero violations at the `ScanResultPage` viewport (375 px wide) and at the marketing-site viewport.
5. THE Source_Citation block SHALL render correctly when JA characters expand the layout (longer source labels) without truncating the freshness state or hit-target sizes.

### Requirement 7: Privacy and outbound-link hygiene

**User Story:** As a privacy-conscious resident, I want clicking the source link not to leak that I use Gomi Bunrui or anything about my scan, so that consulting a municipal source is private.

#### Acceptance Criteria

1. THE Source_Citation outbound link SHALL include `rel="noopener noreferrer"`.
2. THE Source_Citation outbound link SHALL NOT append any query parameters identifying the user, the session, the scan, or the classification.
3. THE recycling-rules API response containing source metadata SHALL NOT include any verifier PII (names, emails) — only the `verifierIdentity` opaque reference, which is server-side only.

## Bilingual considerations (EN/JA)

Captured throughout but summarised here:

- All copy ships EN+JA together (Req 3.5, Req 6.1).
- Honest "JA-only source" disclosure on EN UI (Req 1.3, Req 6.2).
- Senior Content Writer signs off on every copy surface (Req 3.4, Req 4.5, Req 6.3).

## Municipal variance considerations

- The SLA distinguishes Actively_Covered_Municipality from the rest (Req 2.1, Req 4.1). Newly-onboarded municipalities enter the SLA on the day they become queryable.
- The Accuracy_SLA_Page must enumerate Actively_Covered_Municipality and update with coverage changes (Req 3.6).
- Each Actively_Covered_Municipality carries its own Freshness_Metadata — no global "last verified" — because プラ新法 ward-level rule churn is asynchronous.

## Accessibility considerations

- Screen-reader reading order (Req 1.6).
- WCAG 2.1 AA contrast (Req 1.6).
- axe zero violations on `ScanResultPage` and marketing surface (Req 6.4).
- Layout robustness under JA expansion (Req 6.5).

## Success and learning metrics

**Learning (validates riskiest assumption — pre-build moderated test):**

- ≥ 60 % of moderated-test participants (10 total: 5 foreign-resident, 5 JA) notice the citation unprompted.
- ≥ 60 % shift trust rating by ≥ 1 Likert point when citation is present vs. absent on the same hard disposal answer.
- ≥ 60 % understand the Stale_Verification_State as honesty rather than as failure.

**Behavioural (post-launch, 30-day rolling):**

- Citation tap-through rate ≥ 5 % on cited results.
- Zero "the app made this up" support tickets per 10 000 cited classifications.
- North-star unaffected or improved: user-reported-wrong-bin rate not worse than pre-launch baseline.

**Operational (post-launch, 90-day rolling):**

- Median time-from-known-published-rule-change to re-verification ≤ 14 days for Actively_Covered_Municipality.
- Zero silent SLA_Breach incidents (every breach observed in-product or via the `sla_breach_observed` event).

**Counter-metric:**

- `userReportedWrongRate` on `slaState = within_sla` MUST NOT exceed rate on `slaState = unavailable` by > 2 percentage points (Req 5.3) — guards against citing stale rules without surfacing them.

## Open questions for Phase 3 (Design)

These are explicitly deferred to the Application Architect, not blockers on Phase 2 sign-off:

1. **Stale-surface UX choice.** Inline degradation on every stale answer (Option A) vs. one-banner-per-session on the home/scan tab (Option B). Requirements demand the user be made aware before acting; design picks the surface.
2. **Where Freshness_Metadata lives.** Same DynamoDB row as the rule set, separate table joined by `municipalityId`, or a versioned `verification_audit` table feeding a materialised view. Data Architect's call.
3. **Verifier interface.** A CLI for content-ops? A small admin UI? Out of scope for the user-facing requirements above but must be planned during Phase 5 task decomposition.
4. **Manual vs. automated rule-change detection.** v1 manual; the spec leaves room for a later automation tool without re-spec.

## Dependencies

- [AIW-22](/AIW/issues/AIW-22) — municipality rules content model (extends with Freshness_Metadata).
- [AIW-23](/AIW/issues/AIW-23) — recycling-rules API (extends with `slaState` field).
- [AIW-34](/AIW/issues/AIW-34) — municipality data seeding (must backfill Freshness_Metadata for already-seeded wards).
- [AIW-37](/AIW/issues/AIW-37) — scan feedback (extends event schema with `slaState`).
- [AIW-47](/AIW/issues/AIW-47) — competitive baseline (source of Finding 3 motivating this spec).

## Handoff

Next phase: SDD Phase 3 (Design) — Application Architect.

```
spec: 38-source-fidelity-and-accuracy-sla
worktreePath: n/a (still on main)
workItemPath: project/work-items/38-source-fidelity-and-accuracy-sla/
previousArtifact: requirements.md
nextArtifactExpected: design.md
gateResult: n/a
```

Senior Content Writer ([AIW-22](/AIW/issues/AIW-22)) is a required Phase 3 collaborator for copy intent on Source_Citation labels, Stale_Verification_State, Source_Unavailable_State, Accuracy_SLA_Page, and onboarding intro.
