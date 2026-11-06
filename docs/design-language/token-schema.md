# Token schema

Documents the machine-readable token stub and how it becomes `cabin-tokens`.

## Source of truth (stub)

| File | Role |
| --- | --- |
| [`tokens/cabin.tokens.json`](../../tokens/cabin.tokens.json) | **Stub** source of truth for semantic + component tokens |

This JSON is **not** a runtime Android format. Codegen
(`tools/generate_cabin_tokens.py`) emits:

- Android resources (`values` / `values-night`, dimens, attrs)
- Kotlin token objects for `cabin-tokens`
- Compose theme Color mappings for `CabinTheme`
- CSS variables for `apps/www` (+ `tokens/generated/`)

Values are baseline defaults — OEMs overlay brand; programs may tune touch
minima via compliance profiles. See [token-codegen.md](token-codegen.md).

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

Stub includes `cabin.color.scheme.day` and `.night` with chrome + feedback
roles Theme Kit / System·Status bars need:

| Role | Purpose |
| --- | --- |
| `surface` / `onSurface` | Base background + body text (neutral only) |
| `surfaceVariant` | Recessed panels / wells |
| `outline` | Separators, unselected chrome |
| `container` / `onContainer` | System/Status bar container fill + content |
| `warning` / `error` / `charging` | Status feedback; **night values are contrast-locked** |

Codegen emits `values` / `values-night` (same resource names) so UiMode
resolves schemes. Night `warning` / `error` / `charging` keep
`extensions.cabin.lock: "safety-adjacent"` — do not soft-wash. Domain accents
(`charging`, `climate`, `mediaAccent`) must not be used as body-text roles
(`onSurface`, `onContainer`).

## Module boundary

`cabin-tokens` / `CabinTokens`:

- **No** Compose or Views widget dependencies ([ADR 0004](../adr/0004-compliance-separate-module.md) sibling rule for compliance; tokens same thinness)
- Consumed by Theme Kit, Views, Compose, and OEM overlays

## Related

- [Token codegen](token-codegen.md) — regenerate, drift check, add a token
- [Foundations](foundations.md)
- [Tokens overview](tokens.md)
- [MVP](../mvp.md)
- [API contracts](../api-contracts.md)

## Looks and the safety tier (v0.2, ADR 0006)

`cabin.look.<name>` holds one preset per look: `quiet` (default), `precision`,
`warm`, `glass`, `graphic`, `mono`. Each has:

| Group | Keys |
| --- | --- |
| `scheme.{night,day}` | `background`, `surface`, `surfaceContainerHigh`, `outline`, `onSurface`, `onSurfaceVariant`, `onSurfaceMuted`, `primary`, `onPrimary`, `primaryContainer`, `onPrimaryContainer` (+ `backdropGlowStart/End` for Glass) |
| `type` | `family.{ui,display,label}` (names only), `displayWeight`, `titleWeight`, `labelWeight`, `labelAllCaps`, `labelTracking` |
| `shape` | `large`, `medium`, `control` (dp; ≥ 999 = pill) |
| `style` | `activeStyle` (`tonal` / `underline` / `solid` / `invert`), `edge` (`none` / `line` / `glass`) |

Role names follow Material 3, the vocabulary `com.android.oem.tokens` also
uses. `onSurfaceMuted` is a Cabin extension.

`cabin.safety.{night,day}` holds `warning`, `error`, `charging` and their
`*Container` roles. They're shared by every look and carry
`extensions["cabin.lock"] = "safety"`.

Codegen (`tools/cabin_looks.py`) fails when:

- a look defines a safety role, or a safety token loses its lock
- a look is missing a role
- a text role is below 4.5:1 on its surface (translucent surfaces are
  composited over the background first)
- a safety color is below 4.5:1 on any look's background or surface

Outputs: `CabinLooks.generated.kt` (framework-free, `cabin-tokens`) and
`tokens/generated/cabin.looks.css`.

The contrast check corrected two canvas values: day warning `#9A6400` → `#905D00`
and day charging `#1E8C4E` → `#1A7742` (both were under 4.5:1 on light backgrounds).
