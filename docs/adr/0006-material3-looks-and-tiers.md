# ADR 0006: Material 3 base, look presets, three tiers, AOSP-upstream bar

- **Status:** Accepted
- **Date:** 2026-11-06
- **Partially supersedes:** [ADR 0005](0005-compose-first-kit-foundation.md) (generated scaffolds are frozen, not extended)

## Context

The Compose kit is a set of generated, grey Experimental scaffolds over one
token palette. It renders, but it can't show what Cabin is for: an
opinionated, safety-aware AAOS default that OEMs theme instead of redesign.
Stock AAOS already ships Material-based apps, `car-ui-lib`, and
`com.android.oem.tokens`. A parallel component world would not be adopted, and
it would not be acceptable upstream.

The maintainer chose six looks from the design canvas (Quiet, Precision, Warm,
Glass, Graphic, Mono) with **Quiet as the default**, and a first vertical
slice: **Home (H1 Split)**, **Media (M2 Art hero)**, **Climate (C4 Panel)**.
The long-term target is an AOSP `external/cabin` drop that AAOS can ship by
default.

## Decision

1. **Compose kit is built on Material 3.** `CabinTheme` builds a
   `MaterialTheme` (color scheme, typography, shapes) from the active look,
   and adds car-only extensions (`LocalCabinLook`) for things M3 has no slot
   for: active-state style, panel edge, locked safety colors, touch minimum.
2. **A look is a token preset over one schema.** `cabin.look.<name>` defines
   night/day roles named after Material 3 roles (so they line up with
   `com.android.oem.tokens`), type, shape, and style. The six presets also show
   the "theme, don't redesign" story: one OEM, one RRO or one `CabinLook`.
3. **Safety tier is locked across looks.** `cabin.safety.{night,day}`
   (warning, error, charging + containers) is shared. A look that defines
   those roles fails codegen. Text roles must meet 4.5:1 on their surfaces,
   checked at codegen. Touch minimum stays 76 dp.
4. **Three tiers.** Primitives and cards live in `cabin-compose`
   (`dev.decoupled.cabin.compose.kit`). Reference screens live in a new
   opt-in module, `cabin-screens` (`CabinScreens`), that depends on
   `cabin-compose` only.
5. **Generated scaffolds are frozen.** They keep compiling and stay
   `@CabinScaffold` Experimental. New work is hand-written, token-only kit
   code. Trimming the inventory is a later decision.
6. **AOSP-upstream bar.** Every new module has an `Android.bp` that builds
   against AOSP prebuilts (`androidx.compose.*`, `androidx.compose.material3_material3`).
   No GMS, no downloadable fonts, no bundled fonts in libraries (samples may
   bundle OFL fonts). Apache-2.0 headers on new files. See
   [aosp-upstream.md](../adoption/aosp-upstream.md).
7. **Unchanged:** ADR 0002 (SystemUI stays Views-first; `CabinCompose` and
   `CabinScreens` are never SystemUI deps), ADR 0003 (RRO path), ADR 0004
   (compliance is its own module; widgets ask the Restriction Engine).

## Consequences

- `cabin-compose` gains a Material 3 dependency.
- `cabin-compliance` gains a `CarUxRestrictions` adapter compiled against the
  optional `android.car` library (not packaged into the AAR).
- MVP v0.1 stays the Views gate. A v0.2 track adds the Compose slice.
- The catalog app gains a showcase (look × day/night × drive state).

## Links

- [ADR 0002](0002-views-first-platform.md) · [ADR 0003](0003-tokens-via-overlay-rro.md) · [ADR 0005](0005-compose-first-kit-foundation.md)
- [mvp.md](../mvp.md) · [token-schema.md](../design-language/token-schema.md)
- [aosp-upstream.md](../adoption/aosp-upstream.md)
