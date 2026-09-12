# Brainstorm: Source-Fidelity & Accuracy-Update SLA as a Product Commitment

**Date:** 2026-05-13
**Author:** Senior Product Owner
**Phase:** SDD Phase 1 (Brainstorm)
**Source:** [AIW-55](/AIW/issues/AIW-55), derived from Finding 3 of [AIW-47](/AIW/issues/AIW-47)
**Next phase:** Phase 2 → `project/work-items/38-source-fidelity-and-accuracy-sla/requirements.md`

---

## Why this work, in one paragraph

The dominant failure mode for any waste-classification app is *incorrect sorting guidance*. AIW-47's competitive scan established that no JA waste-app competitor — municipal, indie, or AI-camera — publishes either the source they draw rules from or the cadence on which they re-verify them. プラ新法 (April 2022) and ongoing ward-level plastic-rule revisions guarantee that any unmaintained ruleset drifts within 12–24 months. The product opportunity is to make *visible, sourced, dated* classification answers a first-class commitment — both as an in-product trust surface (every answer carries a citation and a last-verified date) and as a public SLA (we commit to re-verifying rules within N days of a published municipal rule change, and we surface a breach when we miss). This brainstorm scopes that commitment so Phase 2 can write testable requirements.

## Lenses applied

- **Real-world-harm vector.** Bad sorting guidance has externalised cost: rejected trash, neighbour disputes, municipal fines, eventual app uninstall. Source-fidelity and a freshness clock are the trust mechanisms that bound that harm. They do not eliminate the AI being wrong, but they let a sceptical user verify the underlying *rule* in one tap and let the *content pipeline* be held to a measurable promise. We are willing to ship a slower, smaller-coverage app to earn the trust premium.
- **Jobs-to-be-done.** The headline JTBD remains "tell me which bin this goes in, in 10 seconds, before the truck comes." Source-fidelity does not change *that* job — it changes the **adjacent** job: "convince me the answer you just gave me is not made up." For foreign-resident power users (the AIW-47-prioritised segment), that adjacent job is load-bearing for retention. For casual JA users, it is invisible until they hit a dispute, then it is decisive.
- **Kano.** Source citation per result is likely a **performance** attribute for foreign-resident power users — more visibility → more trust, monotonic. Public SLA copy is a **delighter** for marketing/onboarding but only if the SLA is real and observable. A *missing* citation, once users learn we usually have one, becomes a **dissatisfier** — so partial coverage must be honest ("not yet verified for this municipality") rather than hidden.
- **Bilingual parity (EN/JA).** Citation labels, source URLs (which point to JA-only municipal portals in most cases), and SLA copy must work in both languages. The municipal source itself will be JA; the EN surface must say "source (in Japanese)" honestly rather than imply an EN page exists. The Senior Content Writer owns the wording on both surfaces.
- **Cost of delay.** プラ新法 ward-level rule churn is *already* happening; every quarter we delay shipping source-fidelity, we ship answers we cannot defend. Competitors are *not* moving here yet (AIW-47 Finding 3) — first-mover trust is available for ~2–4 quarters before someone notices.
- **Build–Measure–Learn.** Success cannot be "we shipped citations." It must be: (a) trust score for cited vs. uncited answers in moderated test, (b) classification-result page tap-through rate on the citation link, (c) opt-in / opt-out behaviour from users shown the SLA copy in onboarding, (d) on the content-ops side, breach rate and median time-to-re-verify after a municipal rule change.

## Riskiest assumption

**Stated:** *"Foreign-resident power users (and a meaningful share of JA users) will both notice citations and let citations change their behaviour — i.e. trust the cited answer more, return to the app more, and tolerate an honest 'not yet verified' state."*

If this is wrong, we have built operational machinery (rule-change monitoring, re-verification cadence, breach surfacing) that costs real content-ops budget and earns us zero product lift. The cheapest invalidation:

- **Prototype-first test.** Build a *static* citation surface and a static "last verified" pill on the ScanResultPage in a feature-flagged build. No backend SLA, no rule-change monitor. Show it to 5 foreign-resident users and 5 JA users in moderated remote sessions. Measure: do they notice it unprompted? Does it shift their trust rating of the answer (5-point Likert before/after)? Would they tap through to the source? Do they understand "not yet verified for your ward" as honesty rather than as failure?
- **Decision rule.** If ≥ 6/10 users (≥ 3/5 in each segment) notice the citation unprompted *and* shift trust by ≥ 1 Likert point on a hard disposal question, proceed to Phase 2 close → Application Architect for full design. If not, we re-scope: maybe source-fidelity is a *recovery* surface (only shown after a "this is wrong" report from AIW-37 feedback), not a default surface — that is cheaper and only blocks rule-touching changes, not every classification.

Phase 2 requirements below assume the test will validate the default-on hypothesis but are written so that a recovery-only fallback is a stage 2 narrowing, not a re-spec.

## Scope decision — one spec or two?

**Decision: one spec, two delivery slices.**

Tempting alternatives:

- *Two specs* — UI surface (38-source-fidelity-display) and content-ops commitment (39-accuracy-update-sla). Lets the two teams move independently.
- *One spec* — keeps the user-visible promise and the operational machinery in the same acceptance criteria so neither half can ship dishonestly.

I'm choosing **one spec** because the failure mode of decoupling is ugly: the UI ships citations without a backing freshness signal (so "last verified" is fabricated), or the SLA is published without the in-product surface that makes it observable. Both halves are part of the same product commitment to the user. Tech Lead can decompose the implementation across teams at Phase 5 (`tasks.md`); the *contract* stays one document.

In-scope for spec 38:

1. **Per-result citation surface** on `ScanResultPage` — what the user sees: source label, municipal portal link (or "source unavailable" honest state), last-verified date, language disclosure.
2. **Per-municipality freshness metadata** stored alongside the rule content — last-verified-at, source-url, source-snapshot-id, verifier (Content Writer or content-ops agent).
3. **Public accuracy-update SLA** — committed cadence (proposal: re-verify within 14 days of a published municipal rule change for active-coverage wards; quarterly baseline re-verification otherwise), with the breach posture stated publicly.
4. **Breach detection and surfacing** — when a municipality's `lastVerifiedAt` exceeds SLA, the per-result citation surface degrades honestly ("verified more than 90 days ago — confirm with your ward"); a breach is recorded for content-ops follow-up.
5. **Onboarding copy** introducing the commitment in EN and JA — Senior Content Writer owns the wording; Phase 2 spec calls out *that copy must exist* and points to its location.
6. **Counter-metric integration** — citation tap-through and breach rate feed the same accuracy KPI dashboard already targeted by [AIW-37](/AIW/issues/AIW-37) scan feedback.

Out of scope for this spec:

- Automated rule-change *detection* (web-scraping municipal portals). This is a content-ops process question that may become a future tool spec; Phase 2 lists it as a manual/Content-Writer-driven check.
- Coverage expansion to new municipalities (AIW-47 follow-up).
- Language expansion beyond EN/JA (separate discovery).
- Monetisation framing of the SLA (e.g. tiered cadence).

## Open questions for Phase 2

1. **Default cadence.** Is 14 days post-published-change defensible? AIW-47 Finding 3 implies *no* competitor publishes any cadence — even 30 days is differentiating. Recommend Phase 2 acceptance criteria express the cadence as a *configurable parameter* with the v1 launch value set to 14 days post-published-change for actively-covered wards and 90 days baseline elsewhere.
2. **Active-coverage definition.** Phase 2 must define which municipalities count as "actively-covered" for the tight SLA — currently the only seeded municipalities are those in [AIW-22](/AIW/issues/AIW-22) / [AIW-34](/AIW/issues/AIW-34). Recommend: any municipality whose rules are queryable via the recycling-rules API.
3. **Breach surfacing UX.** Two options to test at design time: (a) inline degradation on every stale answer ("verified 91 days ago — please confirm"), (b) one banner per-session on the home/scan tab when any ward the user follows is in breach. Phase 2 expresses the *requirement* (user must be made aware before they act on a stale answer); Phase 3 design picks the surface.
4. **Source-unavailable state.** Some answers will be AI-only (e.g. an item not yet rule-mapped). Phase 2 must define the honest state — recommend explicit "no municipal source for this item — AI estimate only" label rather than silently omitting the citation.
5. **JA portal linking.** Most municipal source URLs are JA-only. EN UI must disclose that fact next to the link rather than imply an EN destination. Phase 2 includes a bilingual-parity AC for this.

## Dependencies and handoffs

- **Senior Content Writer.** Owns: (a) public SLA copy in EN and JA, (b) citation-surface labels in both languages, (c) breach-state copy, (d) onboarding intro. Loop in before Phase 2 close (this brainstorm comments to Senior Content Writer alongside the Application Architect handoff).
- **Application Architect.** Owns Phase 3 design — particularly the freshness-metadata data model and how it joins the existing municipality-rules service (AIW-22 / AIW-34).
- **Data Architect.** Owns Phase 3 data model sign-off; the new `lastVerifiedAt`/`sourceUrl` columns sit on the municipality-rules entity.
- **Tech Lead.** Phase 4/5 decomposition into UI, backend metadata, and content-ops tooling slices.
- **Security Architect.** Light touch — the only new surface is a per-result outbound link to a third-party (municipal) URL; we must ensure no PII flows in the link. Phase 3 confirms.
- **QA.** Phase 5/6 — test plan must cover the honest "stale" and "unavailable" states, not only the happy "fresh citation" state.

## Success and learning metrics

- **Learning (riskiest-assumption test):** ≥ 60 % of moderated-test participants notice the citation unprompted; ≥ 60 % shift trust rating ≥ 1 Likert point when citation is present vs. absent on the same answer.
- **Behavioural (post-launch):** citation-tap-through rate ≥ 5 % on cited results; 0 user-reported "the app made this up" support tickets per 10 000 classifications on cited answers.
- **Operational (post-launch):** median time-from-municipal-rule-change to re-verification ≤ 14 days for actively-covered wards over a rolling 90-day window; zero silent breaches (every breach surfaced in-product).
- **Counter-metric:** user-reported-wrong-bin rate (from AIW-37 feedback) on *cited* answers must not exceed the rate on *uncited* answers by more than 2 percentage points — if citations are pulling stale rules, the feedback will catch it.

## Handoff

Phase 2 work-item: `project/work-items/38-source-fidelity-and-accuracy-sla/`. Application Architect picks up after `requirements.md` is filed and Senior Content Writer signs off on the copy intent.
