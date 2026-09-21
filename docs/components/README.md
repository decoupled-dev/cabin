# Components

Cabin component inventory and taxonomy for AAOS cabin HMI. Every component is
specified for **Compose and Views**, shares **tokens**, and declares
**compliance** hooks.

## Taxonomy

| Tier | Meaning | Examples |
| --- | --- | --- |
| **Primitive** | Low-level building blocks | Button, IconButton, Chip, ListItem, Banner |
| **Chrome** | System-level frames | System bars, status bars |
| **Layout** | Automotive size classes and scaffolds | Adaptive scaffold, insets |
| **Domain** | Cabin feature surfaces | Media, EV, HVAC, vehicle controls |
| **Pattern** | Multi-component flows | Restricted browse, fault recovery |
| **Gauges** | Cluster / HUD (separate module) | Radial gauge, speedometer |

Coverage for the Experimental kit (generated scaffolds + handwritten Alpha
chrome) lives in **[coverage.generated.md](coverage.generated.md)**. Inventory
source: [`components/cabin.components.yaml`](../../components/cabin.components.yaml).

Primitives and domain widgets that are not the four handwritten specs are
**Experimental scaffolds** (`@CabinScaffold`): they render, expose `Activate`,
and honor the Restriction Engine. They are not production-complete.

## Classification (safety)

Each component documents one of:

- **Informational** — glance status
- **Convenience** — comfort / media / settings
- **Safety-critical** — see [safety-critical](../compliance/safety-critical.md)

## Inventory map

| Area | Doc | Priority for early phases |
| --- | --- | --- |
| System bars | [system-bars.md](system-bars.md) · **[MVP spec](specs/system-bar.md)** | **MVP** (Views-first) |
| Status bars | [status-bars.md](status-bars.md) · **[MVP spec](specs/status-bar.md)** | **MVP** (Views-first) |
| ClimateTile | [hvac.md](hvac.md) · **[spec](specs/climate-tile.md)** | Layer 2 (Alpha Views / Experimental Compose) |
| MediaNowPlaying | [media.md](media.md) · **[spec](specs/media-now-playing.md)** | Layer 2 (Alpha Views / Experimental Compose) |
| EV / energy | [ev.md](ev.md) | Later (EvEnergyGlance planned) |
| HVAC (full pack) | [hvac.md](hvac.md) | After ClimateTile |
| Vehicle controls | [vehicle-controls.md](vehicle-controls.md) | Later (VehicleQuickControls planned) |
| OEM extensions | [extension-model.md](extension-model.md) | Continuous |
| Kit coverage (generated) | **[coverage.generated.md](coverage.generated.md)** | Experimental scaffold |

## Dual-stack contract template

Every component page includes (or will include at implementation):

1. Purpose and placement in the cabin
2. States / signals
3. Compliance (driving, UX, a11y, safety class)
4. Token dependencies
5. **Planned** Compose API
6. **Planned** Views API
7. Acceptance criteria / parity notes

## Planned module ownership

| Component family | Primary modules |
| --- | --- |
| Chrome (system/status) | `cabin-views` first; Compose mirrors for apps |
| Build-tree subset (dock, list, button, switch, slider, dialog, prefs, QS) | `cabin-views` + Compose |
| App kit (full inventory) | `cabin-compose` Experimental scaffolds |
| Cluster / HUD | `cabin-gauges` (never SystemUI) |
| Shared state / size classes | `cabin-foundation` |

## Related

- [Design language](../design-language/README.md)
- [Compliance](../compliance/README.md)
- [Architecture](../architecture.md)
