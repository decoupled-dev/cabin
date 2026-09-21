# ADR 0005: Compose-first app kit, Views for build-tree, shared foundation

- **Status:** Accepted
- **Date:** 2026-09-21

## Context

Cabin v0.1 froze Views-first **platform chrome** (ADR 0002): SystemUI consumes
`CabinViews` + tokens + compliance. App surfaces still need a full dual-stack
kit. Duplicating `*State` / `*Action` in Compose and Views (ClimateTile,
MediaNowPlaying) already drifted. `car-ui-lib` remains a Views/XML + RRO
chassis library; its Compose path is an alpha plugin `ComposeView`, not a
component kit. AAOS 25Q4 adds platform rotary for Compose on Android 14+.

## Decision

1. **Compose-first for application surfaces.** The Experimental kit in
   `cabin-compose` scaffolds the full component inventory.
2. **Native Views only for Soong / SystemUI / build-tree** needs: chrome
   already shipped, plus dock, app bar, list item, paged list, button, switch,
   slider, stepper, dialog, preference rows, quick settings, focus-area helper.
   Not 1:1 XML for climate/EV/ADAS/cluster.
3. **`cabin-foundation`** holds size classes, display profiles, and shared
   State/Action types. No Compose or Views widget dependencies.
4. **`cabin-gauges`** is a separate opt-in module. Never a SystemUI
   `static_libs` entry.
5. Inventory file [`components/cabin.components.yaml`](../../components/cabin.components.yaml)
   plus [`tools/generate_cabin_components.py`](../../tools/generate_cabin_components.py)
   emit Experimental scaffolds. Handwritten bodies remain for the four
   implementable specs (System Bar, Status Bar, ClimateTile, MediaNowPlaying).
6. **Coexist with `car-ui-lib`.** Map `CarUxRestrictions` into `VehicleUiState`.
   Do not replace CarUiRecyclerView / Toolbar / RotaryService. Hosts may keep
   `FocusParkingView` / `FocusArea` from car-ui-lib.

Scaffolds are **Experimental** (`@CabinScaffold`). They render, expose actions,
and honor the Restriction Engine. They are not production-complete visuals.

## Consequences

- SystemUI sketch stays tokens + compliance + views (ADR 0002 unchanged)
- Compose ⊀ Views and Views ⊀ Compose still hold
- No umbrella `CabinAll`
- OEM apps depend on `cabin-compose` (+ foundation transitively); platform
  chrome stays on `CabinViews`
- ClimateTile / MediaNowPlaying state types live in foundation; Compose and
  Views import those types (Kotlin typealiases cannot re-export sealed nested
  members such as `TempUp` / `PlayPause`)

## Links

- [0002-views-first-platform.md](0002-views-first-platform.md)
- [architecture.md](../architecture.md)
- [api-contracts.md](../api-contracts.md)
- [components/README.md](../components/README.md)
- [coverage.generated.md](../components/coverage.generated.md)
