---
name: cabin-overview
description: >-
  Orient to Cabin — AAOS design language, dual Compose+Views stack, compliance-first
  product pillars, module map, and dual Gradle/Soong distribution. Use when starting
  work on Cabin, choosing modules, explaining what Cabin is, or deciding Gradle vs
  Soong / Compose vs Views adoption paths.
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin overview

## What Cabin is

Open-source **Android Automotive OS** design language and dual UI component
library: **Jetpack Compose** + **legacy Views**, with a shared **token** and
**compliance** layer. Audience: OEMs, Tier-1s, app developers.

Not “Material with bigger buttons.” Cabin encodes glanceability, driving
restrictions, and safety-adjacent UI.

Docs Phase 1 may mean **no library source yet** — treat APIs as **planned**.

## Pillars (must pass)

Glanceable · Quiet · Safe by default · One language, two skins · Thin by design

→ [docs/product/pillars.md](../../docs/product/pillars.md)

## Module map (planned)

| Module | Role |
| --- | --- |
| `cabin-tokens` / `CabinTokens` | Design tokens only (Alpha) |
| `cabin-compliance` / `CabinCompliance` | Restriction Engine / policies (Alpha) |
| `cabin-compose` / `CabinCompose` | Compose UI — Theme + System/Status bars (Experimental) |
| `cabin-views` / `CabinViews` | Views UI — Theme Kit + System/Status bars (Alpha) |

Samples, catalog, website: **not** product runtime deps.

## Dual distribution

| Consumer | Path |
| --- | --- |
| Apps | Gradle → Maven AARs |
| SystemUI / build-tree | Soong → `Android.bp` modules |

Same source. Views-first on platform. → [docs/adoption/build-tree.md](../../docs/adoption/build-tree.md)

## Next skills

- Tokens → `cabin-tokens`
- Gating → `cabin-compliance`
- UI → `cabin-compose` or `cabin-views`
- Platform → `cabin-soong`
- New widget → `cabin-component-spec`

Hard rules: [AGENTS.md](../../AGENTS.md)
