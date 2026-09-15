# Compliance layer

Cabin’s compliance layer encodes automotive UI constraints as **shared
contracts** consumed by both Compose and Views. Compliance is not a PDF
checklist bolted on after visual design — it is a first-class module
(`cabin-compliance`, **planned**) that gates interaction and presentation.

## Why compliance is a library concern

OEMs and app teams repeatedly re-implement:

- Distraction limits while driving
- Minimum touch targets and contrast
- Glanceable typography
- Fail-safe behavior when signals are missing

Cabin centralizes baselines and lets programs **tighten** policy without
forking components. See [vision](../vision.md) and [architecture](../architecture.md).

## Document map

| Doc | Scope |
| --- | --- |
| [Restriction states](restriction-states.md) | MVP state machine + System/Status Bar matrix |
| [Driving restrictions](driving-restrictions.md) | Motion / distraction gating |
| [UX restrictions](ux-restrictions.md) | Targets, density, contrast, day/night |
| [Accessibility & glanceability](accessibility-glanceability.md) | Readability, glance patterns |
| [Safety-critical UI](safety-critical.md) | Determinism, fail-safes |

## Design model (planned)

```text
VehicleUiState ──► DrivingRestrictionPolicy ──► allowed interactions
                 ─► UxRestrictionProfile    ──► layout / density / contrast
                 ─► GlanceabilityRules      ──► type / motion budgets
                 ─► SafetyDefaults          ──► fallback presentation
```

Components query policies; they do not embed OEM-specific if/else trees.

### Planned types (illustrative)

```kotlin
// Planned — cabin-compliance
data class VehicleUiState(
    val isDriving: Boolean,
    val speedKph: Float?,
    val parkMode: Boolean,
    val uxRestrictions: Set<UxRestriction>,
)

enum class UxRestriction {
    NoSetup, NoFiltering, NoKeyboard, NoVideo, LimitStringLength, /* … */
}

interface CabinCompliance {
    fun allows(interaction: CabinInteraction, state: VehicleUiState): Boolean
    fun typographyScale(state: VehicleUiState): TypographyScale
    fun touchTargetMinDp(state: VehicleUiState): Int
}
```

Exact mapping to Android Automotive `UxRestrictions` will be defined in Phase 5;
Cabin will adapt platform signals rather than replace them.

## Dual-stack enforcement

| Stack | Planned enforcement |
| --- | --- |
| Compose | `LocalCabinCompliance` + gated composables / modifiers |
| Views | `CabinComplianceHost` / wrapper widgets + attribute defaults |

Parity rule: the same `CabinInteraction` is allowed or denied on both stacks
for the same `VehicleUiState`.

## Baselines vs program overlays

1. **Cabin baseline** — sensible AAOS-oriented defaults documented here.
2. **Program overlay** — OEM/Tier-1 tightening (never silently loosening
   safety-critical defaults without explicit policy).
3. **App request** — apps may request interactions; compliance allows/denies.

## Acceptance criteria for compliant components

A component is compliance-ready when:

- [ ] Declares which interactions it exposes
- [ ] Disables or substitutes UI when policy denies
- [ ] Meets minimum touch target and contrast rules
- [ ] Typography uses glanceable roles from tokens
- [ ] Defines fail-safe UI for missing vehicle state
- [ ] Has Compose and Views parity notes

## Related

- [Driving restrictions](driving-restrictions.md)
- [Principles](../principles.md)
- [Tokens](../design-language/tokens.md)
- [Platforms](../platforms/compose.md)
