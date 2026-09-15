# UX restrictions

Automotive UX constraints beyond raw driving gates: touch targets, density,
contrast, illumination, and input methods. These rules apply to **both**
Compose and Views implementations.

## Touch targets

| Attribute | Cabin baseline (planned) | Notes |
| --- | --- | --- |
| Minimum touch target | **≥ 76 dp** for primary in-cabin controls (program-tunable) | Larger than handheld Material minima |
| Spacing between targets | **≥ 8 dp** clear gap | Prevent mis-taps with gloves / motion |
| Edge affordances | Avoid critical actions in extreme display corners | Bezel / reachability |

Components must use tokenized min sizes (`cabin.size.touch.minimum`,
naming TBD in [tokens](../design-language/tokens.md)) rather than magic
numbers.

## Density and layout

- Prefer **low information density** in the driver-oriented zone.
- Passenger / rear screens may opt into richer density profiles via compliance
  profiles — never by hardcoding in core components.
- Lists: limited visible complexity while restrictions are active; see
  [driving restrictions](driving-restrictions.md).
- Avoid nested scroll arenas that fight rotary / DPAD navigation.

## Contrast and color

- Body text and icons meet **WCAG-informed** contrast baselines for cabin
  lighting; exact ratios live in token validation (Phase 2).
- Do not convey critical state by color alone — pair with icon/text
  ([accessibility](accessibility-glanceability.md)).
- Semantic colors (`error`, `warning`, `charging`, `climate`) stay consistent
  across OEM brand overlays; brands remap neutrals and primaries, not meaning.

## Day / night and cabin illumination

| Mode | Expectations |
| --- | --- |
| **Day** | Higher luminance surfaces OK; watch glare on glossy clusters |
| **Night** | Dimmed surfaces, reduced bloom, no large pure-white fields |
| **Transition** | Theme changes must not flash distractingly |

Cabin tokens include day/night schemes ([foundations](../design-language/foundations.md)).
System UI and apps should share the same scheme signal.

## Input modalities

AAOS HMI may include touch, rotary, DPAD, steering-wheel controls, and voice:

- Every interactive component needs a non-touch focus path.
- Focus order follows reading/glance priority, not XML declaration accidents.
- Voice invocation should target the same actions as primary buttons where
  feasible (media transport, climate presets).

## Keyboard and setup flows

When UX restrictions disallow keyboards or setup:

- Hide or substitute search fields with restricted browse patterns.
- Defer Bluetooth pairing / account login walls until Park.
- Persist partial setup safely; never block climate or safety controls behind
  setup ([safety-critical](safety-critical.md)).

## String length and chrome

- Enforce max lengths for titles in driver templates when required by
  restrictions.
- Prefer truncation strategies that keep the leading meaningful tokens
  (“Charging · 80%” over ellipsis mid-word chaos).

## Dual-stack notes

| Concern | Compose | Views |
| --- | --- | --- |
| Min size | Theme/`Modifier` defaults from tokens | `minWidth`/`minHeight` from styles |
| Focus | Focus APIs + rotary support | `focusable`, `nextFocus*` |
| Night | Cabin theme scheme | Theme overlay / UiMode night |

## Acceptance criteria

- [ ] Primary controls meet min touch target tokens
- [ ] Contrast validated for day and night schemes
- [ ] Focus path documented and tested
- [ ] Restriction-aware string limits applied where required
- [ ] Parity across stacks for the same configuration

## Related

- [Compliance overview](README.md)
- [Accessibility & glanceability](accessibility-glanceability.md)
- [Foundations](../design-language/foundations.md)
