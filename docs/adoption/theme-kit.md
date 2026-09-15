# Theme Kit (Views-first)

Alpha **Theme Kit** lives in `cabin-views` / `CabinViews`. It resolves
`cabin-tokens` semantic and day/night scheme roles onto theme attributes so
widgets never hardcode hex and OEMs brand without forking chrome
([ADR 0003](../adr/0003-tokens-via-overlay-rro.md)).

> System Bar / Status Bar Views chrome also lives in `cabin-views` and consumes
> Theme Kit for day/night container / outline / safety roles (MVP #4).
> Compose mirrors the same roles via `resolveCabinColors` / `CabinTheme` in
> `cabin-compose` (**Experimental**; no Views theme attrs).

## Artifacts

| Path | Identifier |
| --- | --- |
| Gradle | `dev.decoupled.cabin:cabin-views` (Alpha source module) |
| Soong | `CabinViews` |
| Package | `dev.decoupled.cabin.views.theme` |

Theme Kit itself needs `cabin-tokens` / `CabinTokens` only. Full Views chrome
(bars) also depends on `cabin-compliance` / `CabinCompliance`. **No** AppCompat,
Material, or Compose.

## What resolves

| Theme attr | Token resource | Day/night |
| --- | --- | --- |
| `cabin_colorPrimary` / `OnPrimary` | `cabin_color_semantic_*` | Semantic (stub baseline) |
| `cabin_colorSecondary` / `OnSecondary` | semantic | Semantic |
| `cabin_colorSurface` / `OnSurface` / `SurfaceVariant` | `cabin_color_scheme_*` | **Yes** (`values-night`) |
| `cabin_colorOutline` | scheme | **Yes** |
| `cabin_colorContainer` / `OnContainer` | scheme | **Yes** |
| `cabin_colorWarning` / `Error` / `Charging` | scheme | **Yes** (night contrast-locked) |
| `cabin_colorSuccess` / `Climate` / `MediaAccent` / `Scrim` | semantic | Semantic |

Styles:

- `Theme.Cabin` / `Theme.Cabin.DayNight` — platform DayNight parent + token attrs
- `ThemeOverlay.Cabin` — layer Cabin attrs on an existing host theme (SystemUI)
- `ThemeOverlay.Cabin.OemBrandDemo` — illustrative brand remap (primary only)

Kotlin: `CabinThemes`, `CabinThemeResolver.resolveColors(context)`.

## Apply (Gradle apps)

```xml
<application android:theme="@style/Theme.Cabin">
```

```kotlin
val colors = CabinThemeResolver.resolveColors(context)
// colors.outline / container flip with UiMode night
```

SystemUI and other build-tree targets use Soong `CabinViews` + theme overlay —
not Gradle `implementation` of Cabin AARs ([build-tree](build-tree.md)).

## OEM overlay / RRO (demoable without forking widgets)

Brand at the **token / theme boundary**. Widgets keep resolving `cabin_*`
attrs; products never copy Theme Kit or future bar sources.

### Apps (Gradle theme overlay)

1. Create an OEM module that depends only on `cabin-tokens` (optional thin
   overlay AAR) **or** ship overlay styles in the product APK.
2. Remap brand-facing roles:

```xml
<style name="ThemeOverlay.Oem.Cabin" parent="">
    <item name="cabin_colorPrimary">@color/oem_brand_primary</item>
    <item name="cabin_colorOnPrimary">@color/oem_brand_on_primary</item>
    <!-- Do NOT remap cabin_colorWarning / cabin_colorError for decoration -->
</style>
```

3. Apply over Cabin:

```xml
android:theme="@style/Theme.Cabin"
<!-- activity / ContextThemeWrapper also applies ThemeOverlay.Oem.Cabin -->
```

Cabin ships `ThemeOverlay.Cabin.OemBrandDemo` as a **demoable** pattern —
Robolectric tests prove primary remaps while warning/error/charging stay
locked.

### Platform (RRO + optional Soong token overlay)

Prefer **Runtime Resource Overlays** on device images:

```bp
// Illustrative — product tree, not Cabin core
runtime_resource_overlay {
    name: "OemCabinThemeRRO",
    resource_dirs: ["res"],
    manifest: "AndroidManifest.xml",
    sdk_version: "system_current",
    // target package: SystemUI or the package hosting Theme.Cabin
}
```

RRO `res/values/colors.xml` overrides CabinTokens color resources (or product
theme overlays remapping the same `cabin_*` attrs). Do **not** overlay layout
XML for bars just to change brand colors.

Optional: `OemCabinTokens` Soong module depending on `CabinTokens` that
supplies brand color resources; Theme Kit attrs continue to point at stable
resource names.

Sketch XML for an RRO color remap:
[`sketches/oem-cabin-rro/`](sketches/oem-cabin-rro/README.md).

### Locked roles

| Role | Rule |
| --- | --- |
| `warning` / `error` | Safety-adjacent — no decorative remaps |
| Night `warning` / `error` / `charging` | Contrast-locked (no soft-wash) |
| `onSurface` / `onContainer` | Neutral body text — never domain accents |

See [token-schema](../design-language/token-schema.md).

## Thin deps

SystemUI-shaped targets pull tokens + compliance + views:

```bp
static_libs: ["CabinTokens", "CabinCompliance", "CabinViews"]
// Do NOT add CabinCompose, catalog, or samples
```

Theme-only experiments can omit `CabinCompliance`; restriction-aware bars need it.

## Verification

```bash
./gradlew :cabin-views:test
```

Day/night scheme resolution + OEM overlay smoke are covered by Robolectric
unit tests ([testing](../testing.md)).

## Related

- [MVP #3](../mvp.md)
- [ADR 0003](../adr/0003-tokens-via-overlay-rro.md)
- [Views platform](../platforms/views.md)
- [Build-tree](build-tree.md)
- [API contracts](../api-contracts.md)
