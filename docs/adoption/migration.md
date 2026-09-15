# Migration

Guidance for migrating from stock Material Design components and default AAOS
widgets to Cabin — without a big-bang rewrite.

## Strategy

1. **Tokens first** — introduce Cabin semantic colors/type beside existing
   themes; map gradually.
2. **Chrome or one domain** — pick system/status **or** media/HVAC/EV as the
   pilot.
3. **Compliance wiring** — replace ad-hoc `UxRestrictions` checks with Cabin
   policies as you touch screens.
4. **Second stack** — if you piloted Compose, add Views for system UI (or
   reverse) using parity fixtures.

## From Material (Compose Material3)

| Material concept | Cabin approach |
| --- | --- |
| `MaterialTheme` | `CabinTheme` (planned) |
| Color scheme | Cabin semantic + domain accents |
| Buttons / lists | Cabin primitives sized for cabin |
| Navigation bars | [System bars](../components/system-bars.md) slots |

You may keep Material for non-cabin phone companion apps; do not assume
Material sizes meet cabin touch minima.

### Incremental pattern

```kotlin
// Planned hybrid during migration
CabinTheme {
    // New Cabin media surface
    MediaNowPlaying(/* … */)
    // Legacy Material dialog still parked-only
}
```

Wrap edges carefully so compliance locals still apply to Cabin children.

## From AAOS / Car UI lib / vendor Views

| Legacy | Cabin |
| --- | --- |
| Vendor status layouts | [Status bars](../components/status-bars.md) |
| Custom climate ViewGroups | [HVAC](../components/hvac.md) |
| One-off touch target dims | Token `cabin.size.touch.minimum` |

Migration tip: introduce `CabinComplianceHost` at activity/system root, then
swap widgets leaf-first or branch-first depending on risk.

## Mapping UX restrictions

Replace scattered:

```kotlin
if (uxRestrictions.isRequiresDistractionOptimization) { /* … */ }
```

with declared interactions and policy checks
([driving restrictions](../compliance/driving-restrictions.md)). Behavior
should become more consistent, not merely relocated.

## Risk controls

| Risk | Mitigation |
| --- | --- |
| Visual regression | Screenshot parity vs golden before/after |
| Missed gating | Compliance tests per interaction |
| APK growth | Depend only on needed artifacts ([packaging](packaging.md)) |
| System UI crash | Prefer Views path; staged rollout flags |

## Exit criteria for a migrated screen

- [ ] Cabin theme/tokens only (no raw Material cabin sizing)
- [ ] Compliance policy attached
- [ ] Driving substitute verified
- [ ] Day/night verified
- [ ] Dual-stack plan documented if chrome is involved

## Related

- [Integration](integration.md)
- [Packaging](packaging.md)
- [Principles](../principles.md)
