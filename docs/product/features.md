# Feature plan

Layered product plan for Cabin. Layers build upward: later layers may not
invent foundations the earlier layers do not provide. Aligns with
[pillars](pillars.md) and the [roadmap](../roadmap.md).

> Module names and APIs below are **planned** unless noted.

## Layer 0 — Foundations

Shared meaning before chrome or apps. **Included in frozen [MVP v0.1](../mvp.md).**

| Feature | Intent | Primary docs / modules |
| --- | --- | --- |
| **Cabin Tokens** | Semantic + component tokens; OEM overlay without fork | [tokens](../design-language/tokens.md), [token schema](../design-language/token-schema.md), `tokens/cabin.tokens.json` |
| **Restriction Engine** | Driving / UX / safety policy as injectable gates | [restriction-states](../compliance/restriction-states.md), `cabin-compliance` |
| **Theme Kit** | Day/night schemes resolving tokens into Views themes (Compose mirror optional) | [theme-kit](../adoption/theme-kit.md), [foundations](../design-language/foundations.md) — **Alpha** |

**Exit for Layer 0:** Theme Kit can resolve tokens; Restriction Engine answers
allow/substitute/block for chrome interactions.

## Layer 1 — System surfaces

Persistent cabin chrome. **MVP includes System Bar + Status Bar (Views-first)
only.** Overlay & Toast full pack is later.

| Feature | Intent | Primary docs | MVP |
| --- | --- | --- | --- |
| **System Bar** | Wayfinding slots, large targets, restriction-aware entries | [spec](../components/specs/system-bar.md) — **Alpha** | **Yes** |
| **Status Bar** | Glanceable vehicle/system status; honest unavailable/fault | [spec](../components/specs/status-bar.md) — **Alpha** | **Yes** |
| **Overlay & Toast** | Transient feedback that does not trap drivers | Patterns + safety docs | Later |

**Exit for Layer 1 (MVP):** OEM can slot brand chrome via tokens/RRO without
forking bar widgets; SystemUI consumes thin Soong deps.

## Layer 2 — Core screens

Domain surfaces every cabin program expects. **Explicitly after MVP** — do not
start until [mvp.md](../mvp.md) success criteria pass.

| Feature | Intent | Primary docs |
| --- | --- | --- |
| **Media** | Now-playing, transport, restriction-aware browse | [media](../components/media.md) |
| **HVAC** | Zones, steppers, defrost always available | [hvac](../components/hvac.md) |
| **EV Energy** | SOC, range, charge session, charge faults | [ev](../components/ev.md) |
| **Vehicle Controls** | Locks, lights, drive modes, hazards; extension tiles | [vehicle controls](../components/vehicle-controls.md) |

**Exit for Layer 2:** Each domain has Compose + Views contracts, compliance
hooks, and dual-stack parity for critical states.

## Layer 3 — Patterns

Reusable interaction grammar across domains.

| Feature | Intent |
| --- | --- |
| **Primary Action** | One obvious action cluster; glance-sized; gated cleanly — **[Button / IconButton](../components/specs/button.md)** (Alpha Views / Experimental Compose) |
| **Confirm Destructive** | Rare, large-target confirmation; never blocks safety paths — **later** (not this slice) |
| **Cabin List / Grid** | Low-density, restriction-aware collection layouts — **[ListItem](../components/specs/list-item.md)** (Alpha Views / Experimental Compose); full Grid later |
| **Empty & Error** | Calm empty states; exhaustive fault/unavailable patterns |

Patterns compose Layer 0–2 pieces; they do not bypass the Restriction Engine.

## Layer 4 — Craft & platform

How the product is built, verified, taught, and adopted into OEM images.

| Feature | Intent | Primary docs / modules |
| --- | --- | --- |
| **Compose Kit** | `cabin-compose` / `CabinCompose` theme + System/Status bars (**Experimental**) | [compose](../platforms/compose.md) |
| **Views Kit** | `cabin-views` / `CabinViews` theme + components (SystemUI primary) | [views](../platforms/views.md) |
| **Build-tree / Soong distribution** | Same sources as Maven AARs; Soong modules for SystemUI, CarLauncher, platform media; RROs for brand; thin Views-first deps | [build-tree](../adoption/build-tree.md), [soong](../platforms/soong.md), [packaging](../adoption/packaging.md) |
| **Agent skills + AGENTS.md** | Modular Agent Skills and hard rules so coding agents implement thin, compliant, dual-stack Cabin correctly | [AGENTS.md](../../AGENTS.md), [docs/agents](../agents/README.md), [skills/](../../skills/) |
| **Catalog app** | Interactive reference for parity and adoption | [site plan](../website/site-plan.md) |
| **Docs site** | Public Material-class IA | [site plan](../website/site-plan.md) |

Catalog and docs site ship after enough Layer 1–2 surface exists to showcase
honestly — not as empty shells. Build-tree distribution is required for OEM
chrome adoption; Gradle-only packaging is insufficient for SystemUI. Agent
affordances ship in Phase 1 docs so later implementation stays aligned.

## Explicitly not in v1

| Out of scope | Why |
| --- | --- |
| **Vanity animation** | Motion budgets favor trust over spectacle; driving reduces decoration |
| **Phone-first patterns** | Handheld navigation, dense sheets, and tiny targets fail glance/quiet/safe |
| **Kitchen-sink packs** | Umbrella AARs violate thin-by-design ([packaging](../adoption/packaging.md)) |
| **OEM forks as default** | Brand and features extend via tokens, slots, and policy overlays ([extension model](../components/extension-model.md)) |

Also deferred: full OEM launcher as the product, every vehicle domain, and
regional regulation packs as a substitute for program policy overlays
([vision](../vision.md) non-goals).

## Mapping layers → roadmap

| Roadmap beat | Feature layers |
| --- | --- |
| Docs + pre-implementation | Stance, pillars, this plan, [MVP freeze](../mvp.md) |
| **MVP v0.1** | Layer 0 + System/Status bars (Views) |
| Media + HVAC | Layer 2 (first pair) — after MVP |
| EV + Vehicle Controls | Layer 2 (second pair) |
| Catalog | Layer 4 catalog |
| Website | Layer 4 docs site |

Compose Kit and Views Kit grow with Layers 1–2 rather than as a separate
“UI framework phase” detached from surfaces. See [roadmap](../roadmap.md).

## Related

- [Stance](stance.md)
- [Pillars](pillars.md)
- [Architecture](../architecture.md)
- [Components inventory](../components/README.md)
