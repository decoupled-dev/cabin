# Product pillars

Every Cabin feature — foundation, chrome, screen, or pattern — must pass these
five pillars. If it fails one, it does not ship as Cabin.

## 1. Glanceable

Readable in under a second, at speed, in day and night.

- Primary values use glance roles and stable layout
  ([accessibility & glanceability](../compliance/accessibility-glanceability.md)).
- Hierarchy is obvious without scanning the whole screen.
- Night schemes do not wash out status; day schemes do not lose contrast to
  glare assumptions.

**Fail example:** A dense settings wall as the default driver landing surface.

## 2. Quiet

Fewer elements. Stronger hierarchy.

- One primary action cluster per glance region.
- Secondary detail recessed; chrome does not compete with content.
- Motion confirms state; it does not entertain while driving
  ([foundations](../design-language/foundations.md)).

**Fail example:** Competing CTAs, badge stacks, and decorative panels in the
first viewport of HVAC or media.

## 3. Safe by default

Driving restrictions and UX rules **shape** the UI — they are not an afterthought
toggle.

- Interactions declare classes and gate cleanly
  ([driving restrictions](../compliance/driving-restrictions.md)).
- Denied capabilities substitute or disable honestly; they do not soft-fail
  into tiny targets.
- Safety-critical affordances remain reachable and deterministic
  ([safety-critical](../compliance/safety-critical.md)).

**Fail example:** A demo that disables the compliance local “so the UI looks
complete.”

## 4. One language, two skins

Compose and Views share **meaning**; OEM brand sits on top via tokens.

- Same states, actions, minima, and gating fixtures across stacks
  ([compose](../platforms/compose.md), [views](../platforms/views.md)).
- Brand remaps semantic tokens without forking components
  ([tokens](../design-language/tokens.md)).
- API shape may differ; behavior must not.

**Fail example:** A Compose-only “source of truth” that Views approximate later.

## 5. Thin by design

Teams adopt one module without dragging the rest.

- Tokens, compliance, Compose, and Views remain separable artifacts
  ([packaging](../adoption/packaging.md)).
- Samples, catalog, and website never leak into product images.
- No kitchen-sink AAR; domain expansion does not force unused surface area.

**Fail example:** An umbrella dependency that pulls both stacks, samples, and
catalog into system UI.

## Using the pillars

| Stage | Application |
| --- | --- |
| Proposal | Score the idea against all five before writing APIs |
| Design review | Reject quiet or glance failures even if tokens look polished |
| Implementation | Parity + compliance tests encode pillars 3–4 |
| Packaging | Artifact boundaries encode pillar 5 |

Feature sequencing lives in [features](features.md); delivery order in
[roadmap](../roadmap.md).

## Related

- [Stance](stance.md)
- [Features](features.md)
- [Principles](../principles.md)
- [Architecture](../architecture.md)
