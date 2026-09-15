# Token schema

Documents the machine-readable token stub and how it becomes `cabin-tokens`.

## Source of truth (stub)

| File | Role |
| --- | --- |
| [`tokens/cabin.tokens.json`](../../tokens/cabin.tokens.json) | **Stub** source of truth for semantic + component tokens |

This JSON is **not** a runtime Android format. It is intended as input to
**Style Dictionary** (or equivalent codegen) that emits:

- Android resources (`values` / `values-night`, dimens, attrs)
- Kotlin token objects for `cabin-tokens` (**planned**)
- Optional Compose `CabinTokens` mirror later

Until codegen lands, treat values as **illustrative baselines** — OEMs overlay
brand; programs may tune touch minima via compliance profiles.

## Naming

```text
cabin.{category}.{tier}.{name}
```

Examples:

- `cabin.color.semantic.primary`
- `cabin.type.role.status.size`
- `cabin.space.md`
- `cabin.size.touch.minimum`
- `cabin.component.systemBar.height`

Categories in the stub: `color`, `type`, `space`, `size`, `elevation`,
`motion`, `component`.

## Tiers

| Tier | Meaning |
| --- | --- |
| Reference (future) | Raw palettes / ramps |
| **Semantic** | Intent (`primary`, `surface`, `warning`) |
| **Component** | Widget-local bindings (`systemBar.height`) |

Components must not hardcode hex/sp; they reference semantic or component
tokens ([tokens.md](tokens.md)).

## Entry shape

Each leaf token in the stub uses:

```json
{
  "value": "<literal or ref>",
  "type": "<color|fontSizes|spacing|sizing|…>",
  "unit": "<dp|sp|ms>",
  "comment": "optional",
  "extensions": { "cabin.lock": "safety-adjacent" }
}
```

`$ref` pointers (JSON Pointer within the file) allow component tokens to alias
shared sizes (e.g. touch minimum).

## OEM overlay rules

1. Overlays remap **brand-facing** semantics (`primary`, type family, optional
   radii later) — not safety meaning.
2. Tokens with `extensions.cabin.lock: "safety-adjacent"` (`warning`, `error`)
   must not be remapped for decoration.
3. Domain accents (`charging`, `climate`) stay recognizable under brand.
4. Apps: Gradle token overlay module. Platform: **RRO** + optional Soong
   overlay module ([ADR 0003](../adr/0003-tokens-via-overlay-rro.md),
   [build-tree](../adoption/build-tree.md)).

## RRO mapping notes

| Token role | Typical Android target |
| --- | --- |
| `color.semantic.*` / `color.scheme.*` | Theme attrs / color resources |
| `type.role.*` | `TextAppearance` / dimen + font |
| `space.*` / `size.*` | `dimen` |
| `component.systemBar.*` | Component-specific attrs on `CabinSystemBarView` |

RROs should override **attributes/resources**, not copy entire layouts.
Prefer stable `cabin_` attr names (finalize at implementation).

## Day / night

Stub includes `cabin.color.scheme.day` and `.night` surface pairs. Codegen
should emit `values` / `values-night` (or equivalent) so Theme Kit resolves
with UiMode.

## Module boundary

`cabin-tokens` / `CabinTokens`:

- **No** Compose or Views widget dependencies ([ADR 0004](../adr/0004-compliance-separate-module.md) sibling rule for compliance; tokens same thinness)
- Consumed by Theme Kit, Views, Compose, and OEM overlays

## Related

- [Foundations](foundations.md)
- [Tokens overview](tokens.md)
- [MVP](../mvp.md)
- [API contracts](../api-contracts.md)
