# Components

Cabin component inventory and taxonomy for AAOS cabin HMI. Every component is
specified for **Compose and Views**, shares **tokens**, and declares
**compliance** hooks.

## Taxonomy

| Tier | Meaning | Examples |
| --- | --- | --- |
| **Primitive** | Low-level building blocks | Button, IconButton, Chip, ListItem, Banner |
| **Chrome** | System-level frames | System bars, status bars |
| **Domain** | Cabin feature surfaces | Media, EV, HVAC, vehicle controls |
| **Pattern** | Multi-component flows | Restricted browse, fault recovery |

Primitives will be detailed as implementation proceeds; this phase focuses on
chrome and domain coverage OEMs expect on day one.

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
| Button / IconButton | **[spec](specs/button.md)** | Post-MVP primitive (Alpha Views / Experimental Compose) |
| ListItem | **[spec](specs/list-item.md)** | Post-MVP primitive (Alpha Views / Experimental Compose) |
| Media | [media.md](media.md) | After MVP |
| EV / energy | [ev.md](ev.md) | After MVP |
| HVAC | [hvac.md](hvac.md) | After MVP |
| Vehicle controls | [vehicle-controls.md](vehicle-controls.md) | After MVP |
| OEM extensions | [extension-model.md](extension-model.md) | Continuous |

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
| Primitives (Button, IconButton, ListItem) | `cabin-views` first; Compose parity in `cabin-compose` |
| Media / EV / HVAC / controls | `cabin-compose` + `cabin-views` |
| Shared banners, buttons | both |

## Related

- [Design language](../design-language/README.md)
- [Compliance](../compliance/README.md)
- [Architecture](../architecture.md)
