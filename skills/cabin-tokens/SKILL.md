---
name: cabin-tokens
description: >-
  Cabin design token model — semantic and component tokens, OEM theming and RROs,
  day/night schemes, safety color locks, and zero UI-framework dependencies. Use
  when adding colors/type/space, creating OEM brand overlays, generating theme
  resources, or preventing hardcoded hex/sp in components.
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin tokens

## Rules

1. Components consume **semantic** / **component** tokens — never raw hex or magic `sp`/`dp` in call sites.
2. `cabin-tokens` / `CabinTokens` has **no** Compose, AppCompat, or Material dependency.
3. OEM brand = overlay (Gradle token overlay or platform **RRO**), not a fork of components.
4. Safety-adjacent roles (`error`, `warning`, charging fault meaning) must not be remapped for decoration.
5. Day/night schemes are first-class — include chrome roles (`outline`,
   `container`) and locked night contrast for `warning` / `error` / `charging`.
6. Domain accents stay off body-text roles (`onSurface`, `onContainer`).

## Layers

```text
Reference → Semantic → Component
```

Components bind to component/semantic tokens only.

## Dual stack

One token source (`tokens/cabin.tokens.json`) feeds Compose theme, Views
attrs/resources, and CSS via `tools/generate_cabin_tokens.py`
([token-codegen](../../docs/design-language/token-codegen.md),
[tokens.md](../../docs/design-language/tokens.md)). CI runs `--check` for drift.

## Platform theming

Prefer RROs + optional Soong token overlay modules for images
([docs/adoption/build-tree.md](../../docs/adoption/build-tree.md)).

## Planned names (illustrative)

`cabin.color.semantic.primary`, `cabin.size.touch.minimum`,
`cabin.component.media.transport.size` — schema lands in Phase 2.

## Do not

- Put OEM brand hex inside widget source
- Add UI toolkit deps to the tokens module
- Ship all unused brand packs in the default artifact

Related: [foundations](../../docs/design-language/foundations.md) · skill `cabin-compliance` for locked safety meaning
