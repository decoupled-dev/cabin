# Driving restrictions

UX while the vehicle is in motion must minimize distraction. Cabin models
driving restrictions as **gated interactions**: components expose capabilities;
compliance allows, denies, or substitutes them based on vehicle UI state.

## Goals

- Reduce eyes-off-road and complex multi-step tasks while driving.
- Keep essential cabin controls available with glanceable affordances.
- Provide deterministic gating shared by Compose and Views.
- Allow OEM programs to tighten Cabin baselines without forking widgets.

## Interaction classes

| Class | Examples | Typical driving policy |
| --- | --- | --- |
| **Glance** | Speed, range, now-playing title | Always allowed; keep short |
| **Simple control** | Play/pause, next track, fan ± | Allowed with large targets |
| **Configuration** | Deep settings, pairing, sorting | Blocked or deferred while driving |
| **Text entry** | Keyboard, search typing | Restricted / voice-first |
| **Video / animation-heavy** | Music videos, elaborate motion | Restricted |
| **Safety-critical** | Hazards, defrost — see [safety-critical](safety-critical.md) | Always available; distinct styling |

Components must declare their class in documentation (and later, in metadata).

## Gating patterns

### Disable vs hide vs substitute

| Pattern | When to use |
| --- | --- |
| **Disable** | Control remains visible for spatial memory; not activatable |
| **Hide** | Non-essential chrome that adds clutter |
| **Substitute** | Replace dense UI with a driving-safe variant (e.g. list → limited list) |

Cabin prefers **substitute** for primary tasks (media browse) and **disable**
for secondary controls that should keep layout stability.

### Planned API sketch

```kotlin
// Planned
sealed interface CabinInteraction {
    data object OpenKeyboard : CabinInteraction
    data object FilterList : CabinInteraction
    data class NavigateTo(val destination: String) : CabinInteraction
    data class AdjustContinuous(val id: String) : CabinInteraction
}

fun CabinCompliance.gated(
    interaction: CabinInteraction,
    state: VehicleUiState,
    allowed: () -> Unit,
    denied: () -> Unit = {},
)
```

Compose (**planned**): gated clickable modifiers / composable wrappers.  
Views (**planned**): `CabinGatedClickListener` / enabled-state binding.

## Distraction minimization rules

1. **One primary action per glance cluster** — avoid competing CTAs in the
   driver’s primary visual field.
2. **Short labels** — prefer icon + short text; enforce max string lengths when
   UX restrictions require it ([UX restrictions](ux-restrictions.md)).
3. **No surprise navigation** while driving — confirm only when legally /
   program-required; prefer undo-safe actions.
4. **Motion budget** — reduce decorative animation when `isDriving`
   ([foundations](../design-language/foundations.md)).
5. **No modal traps** — dialogs must be dismissible with large targets; avoid
   stacked modals.

## Mapping to platform UX restrictions

Android Automotive exposes `CarUxRestrictions`. Cabin will adapt those signals
into `VehicleUiState` / `UxRestriction` (**planned** Phase 5) so apps are not
forced to parse framework details in every screen.

Programs may add stricter rules (e.g. block certain OEM screens above a speed
threshold) via policy injection ([architecture](../architecture.md)).

## Dual-stack expectations

| Requirement | Compose | Views |
| --- | --- | --- |
| Same allow/deny for an interaction | Yes | Yes |
| Visible disabled affordance option | Yes | Yes |
| Driving-safe alternate layout | `*Driving` composable variant | alternate layout XML / `CabinDrivingLayout` |
| Logging/analytics hook on deny (optional) | Callback | Callback |

## Acceptance criteria

- [ ] Component documents interaction classes
- [ ] Denied interactions cannot be invoked via click, key, or rotary
- [ ] Driving-safe substitute exists for primary user journeys
- [ ] Behavior identical on Compose and Views for the same state fixture
- [ ] Missing/unknown drive state follows
  [safety-critical](safety-critical.md) defaults

## Related

- [Compliance overview](README.md)
- [UX restrictions](ux-restrictions.md)
- [Media components](../components/media.md)
- [Compose](../platforms/compose.md) · [Views](../platforms/views.md)
