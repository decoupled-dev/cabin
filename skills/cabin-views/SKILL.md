---
name: cabin-views
description: >-
  Implement Cabin Views/XML UI for SystemUI, status/system bars, CarLauncher,
  platform media, and Gradle legacy apps. Use when building View widgets,
  styleables, RROs, or any build-tree chrome — pair with cabin-soong for
  Android.bp deps (Views-first; Compose not required).
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin Views

## Primary consumers

| Target | How |
| --- | --- |
| SystemUI / bars | Soong: `CabinTokens` + `CabinCompliance` + `CabinViews` |
| CarLauncher / platform media | Soong Views path (Compose only if already present) |
| Gradle legacy apps | Maven `cabin-views` |

Compose is **not** a gate for SystemUI.

## Planned artifacts

- Maven: `dev.decoupled.cabin:cabin-views` (Alpha — Theme Kit + System/Status bars)
- Soong: `CabinViews` (`cabin-views/Android.bp`)

No dependency on Compose modules. No AppCompat/Material for Theme Kit.

## Theme Kit (Alpha)

- `Theme.Cabin` / `ThemeOverlay.Cabin` bind `cabin_*` attrs → token colors
- Day/night scheme roles via CabinTokens `values` / `values-night`
- `CabinThemeResolver` for widgets; OEM overlay / RRO without forks
- Docs: [docs/adoption/theme-kit.md](../../docs/adoption/theme-kit.md)

## System / Status bars (Alpha)

- `CabinSystemBarView` — slotted leading/center/trailing; token sizes
- `CabinStatusBarView` — ordered glyphs; exhaustive `Signal` presentation
- `CabinComplianceHost` — Restriction Engine gating (allow/substitute/block)
- Depends on `cabin-compliance` / `CabinCompliance` (thin; no Compose)

## Guidelines

- Styleables map to token roles, not raw colors
- Explicit `bind()` / adapters; no hidden theme singletons
- Focus / rotary paths; large touch minima from tokens
- `CabinComplianceHost` for gating — never hardcode driving policy in widgets
- Day/night and config changes without losing vehicle state
- Prefer **RROs** for OEM brand on images

→ [docs/platforms/views.md](../../docs/platforms/views.md) ·
[docs/adoption/build-tree.md](../../docs/adoption/build-tree.md)
## Parity with Compose

Shared state/action fixtures; same gating and unavailable UI.

## Do not

- Document SystemUI adoption as Gradle `implementation` of Cabin
- Pull `CabinCompose`, catalog, or samples into SystemUI
- Encode OEM brand in widget source

Related: `cabin-soong` · `cabin-compliance` · `cabin-component-spec`
