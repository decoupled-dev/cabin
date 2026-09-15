---
name: cabin-compose
description: >-
  Implement Cabin Jetpack Compose UI — CabinTheme, token usage, compliance
  locals, UDF, and parity contracts with Views. Use when writing Compose
  components, app screens, or Maven cabin-compose consumers — not for SystemUI
  Soong adoption (use cabin-views and cabin-soong instead).
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin Compose

## When to use Compose

- Gradle/Maven feature apps and media experiences
- Platform apps that **already** use Compose

**Not** a prerequisite for SystemUI — use Views + Soong there.

## Planned artifact

`dev.decoupled.cabin:cabin-compose` → depends on tokens + compliance.
Must **not** depend on `cabin-views`.

## Guidelines

- `CabinTheme` + composition locals for tokens and compliance (**planned**)
- Colors/type/space from tokens only
- UDF; exhaustive vehicle `Signal` handling
- Public composables accept `Modifier`
- Gate interactions via compliance — skill `cabin-compliance`
- Semantics / content descriptions for icon-only controls

→ [docs/platforms/compose.md](../../docs/platforms/compose.md)

## Parity with Views

Same states, actions, min sizes, gating, unavailable UI, day/night roles.
API shape differs; behavior must not.

## Do not

- Depend on Views modules from Compose
- Force Compose into SystemUI bp
- Skip driving substitutes

Related: `cabin-views` · `cabin-component-spec` · [android/skills](https://github.com/android/skills) for general Compose craft
