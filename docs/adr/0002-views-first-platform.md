# ADR 0002: Views-first for platform / SystemUI

- **Status:** Accepted
- **Date:** 2026-09-15

## Context

AAOS SystemUI and much platform chrome remain **Views/XML**. Compose
availability varies by branch. Making Compose a gate for Cabin would delay or
block OEM adoption of status/system bars.

## Decision

- **Primary** platform surface: `CabinViews` + `CabinTokens` + `CabinCompliance`
- Compose is first-class for apps and Compose-capable platform apps
- Compose is **not** required for SystemUI or for calling **v0.1 MVP** done
- `CabinViews` must not depend on `CabinCompose`

## Consequences

- MVP bars ship Views-first; Compose bar parity may trail as experimental
- SystemUI `static_libs` must not list `CabinCompose`
- Domain Compose screens (media/HVAC/…) stay post-MVP

## Links

- [mvp.md](../mvp.md)
- [views.md](../platforms/views.md)
- [0001-dual-gradle-soong.md](0001-dual-gradle-soong.md)
