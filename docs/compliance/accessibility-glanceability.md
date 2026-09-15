# Accessibility and glanceability

Cabin UI must be readable at a glance for drivers and comfortable for
passengers — without turning every screen into a billboard.

## Glanceability principles

1. **Recognize in under a glance** — primary status (range, temp, now playing)
   identifiable in a short eyes-on-display budget.
2. **Hierarchy over density** — one dominant value; secondary detail recessed.
3. **Stable layout** — values update in place; avoid layout thrash that forces
   re-scanning.
4. **Motion supports meaning** — use motion for state change confirmation, not
   decoration while driving ([foundations](../design-language/foundations.md)).

## Typography

| Role (planned) | Use | Glance notes |
| --- | --- | --- |
| `display` | Rare hero values (e.g. SOC %) | High contrast; short |
| `headline` | Screen title | Not for rapidly changing values |
| `title` | Section / card title | Keep to one line when driving |
| `body` | Supporting copy | Avoid long paragraphs in driver UI |
| `label` | Buttons, chips | Large enough for glance + touch |
| `status` | Status bar / HUD-like | Optimized for distance |

Baselines favor **larger effective sizes** than handheld Material. Exact sp
values live in [tokens](../design-language/tokens.md) and may scale with
compliance profiles ([compliance](README.md)).

### Readability rules

- Prefer wider tracking for small status labels if needed for legibility.
- Avoid ultra-light weights for critical values.
- Do not place critical text over busy imagery without scrims.
- Support system font scale where AAOS programs enable it; test at large
  scales without clipping essential controls.

## Color and non-visual cues

- Pair color with icons and text for states (charging, fault, climate).
- Provide content descriptions for icon-only controls.
- Keep selected/pressed states obvious in day and night schemes.

## Driver vs passenger surfaces

| Surface | Glanceability priority | Density |
| --- | --- | --- |
| Driver cluster / center information | Highest | Lowest |
| Center stack primary | High | Low–medium |
| Passenger entertainment | Medium | Medium–higher (still Cabin tokens) |
| Rear seat | Medium | Higher OK |

Compliance profiles select density; components should not assume passenger
freedom on a driver-primary display.

## Accessibility services and input

- Touch exploration / talkback analogs used in AAOS programs must still reach
  safety-critical controls.
- Rotary: announce focus clearly; large focus highlights from tokens.
- Do not rely on hover.

## Motion and glance

| Situation | Motion policy |
| --- | --- |
| Parked | Richer transitions allowed |
| Driving | Short, purposeful; no looping decoration |
| Safety alerts | Immediate, high contrast; limited animation frames |

## Dual-stack parity

Typography roles and content descriptions must match across Compose and Views
for the same component state. Catalog checks (planned) compare:

- Text role mapping
- Ellipsize / max-line behavior
- Content description strings

## Acceptance criteria

- [ ] Uses Cabin type roles (not ad-hoc sp)
- [ ] Critical values remain legible at night and large font scale
- [ ] Icon-only actions have descriptions
- [ ] Layout remains stable as values update
- [ ] Driving motion budget respected

## Related

- [UX restrictions](ux-restrictions.md)
- [Safety-critical](safety-critical.md)
- [Foundations](../design-language/foundations.md)
- [Status bars](../components/status-bars.md)
