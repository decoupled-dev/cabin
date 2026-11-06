# AOSP upstream path (`external/cabin`)

**Status:** compile-verified against a local AOSP 17 tree (`CP2A.260605.016`,
2026-11-06). All seven Soong modules compile with the tree's own toolchain
(Kotlin 2.2 + Compose plugin, Material 3 1.5.0-alpha16, `system_current`,
`android.car`) via `tools/aosp_compile_check.py`. A full `m` build is still
pending: that tree's whole-tree analysis fails on unrelated untracked files in
`hardware/interfaces`.

```bash
python3 tools/aosp_compile_check.py --aosp /path/to/aosp   # no writes to the tree
```

Cabin's long-term target is to be good enough for AAOS to carry it by default,
as `external/cabin`, next to `car-ui-lib` and `com.android.oem.tokens`. Code
alone can't guarantee that. What we can do is make adoption a drop-in with no
rework. This page is the checklist.

## Drop-in shape

```
external/cabin/
  Android.bp                 # CabinAndroidLibraryDefaults (no umbrella module)
  METADATA  OWNERS  NOTICE  MODULE_LICENSE_APACHE2
  <module>/AndroidManifest.xml  # Soong manifest with package (Gradle uses src/main + namespace)
  cabin-tokens/Android.bp    # CabinTokens      (no UI deps)
  cabin-compliance/Android.bp# CabinCompliance  (libs: android.car, optional)
  cabin-foundation/Android.bp# CabinFoundation  (no UI deps)
  cabin-views/Android.bp     # CabinViews       (SystemUI-safe)
  cabin-compose/Android.bp   # CabinCompose     (apps only; Material 3)
  cabin-screens/Android.bp   # CabinScreens     (apps only; reference screens)
```

Samples, catalog, `apps/www` and `website/` never get an `Android.bp`
(`tools/check_soong_thin_deps.py` enforces it).

## Checklist

| Area | Requirement | State |
| --- | --- | --- |
| Build | Same sources build with Gradle and Soong | Gradle verified; AOSP 17 toolchain compile verified (`aosp_compile_check.py`); full `m` pending |
| Deps | AOSP prebuilts only: `androidx.compose.runtime_runtime`, `androidx.compose.ui_ui`, `androidx.compose.foundation_foundation`, `androidx.compose.material3_material3`; compiler plugin `kotlin-compose-compiler-plugin` | Names verified in the AOSP 17 tree |
| Car APIs | `android.car` as an optional `libs` entry, never packaged | `cabin-compliance` |
| Tokens | Roles named after Material 3 roles, the same vocabulary as the AAOS OEM design tokens | Names verified (see mapping below); RRO generation planned |
| Safety | Locked warning/error/charging; 4.5:1 text contrast; 76 dp targets; Restriction Engine gates | Enforced at codegen and in tests |
| Assets | No GMS, no network, no downloadable fonts; libraries ship no fonts | Showcase bundles OFL fonts only |
| Licensing | Apache-2.0 header on new source files | New kit and screens files |
| API | Android API and Compose API guidelines: `modifier` first optional parameter, slot APIs, hoisted state | Kit and screens |
| SystemUI | Views-first (ADR 0002); Compose modules never in SystemUI `static_libs` | Checked by `check_soong_thin_deps.py` |

## Mapping to AAOS OEM design tokens

Verified against `packages/services/Car/car_product/rro/oem-design-tokens` in
AOSP 17. AAOS names are `oemColor<M3 role>`, `oemShapeCorner*` and
`oemTextAppearance<M3 type role>`.

| Cabin look role | AAOS OEM token |
| --- | --- |
| `background` | `oemColorSurface` (AAOS has no background role) |
| `surface` | `oemColorSurfaceContainer` |
| `surfaceContainerHigh` | `oemColorSurfaceContainerHigh` |
| `outline` | `oemColorOutline` |
| `onSurface` / `onSurfaceVariant` | `oemColorOnSurface` / `oemColorOnSurfaceVariant` |
| `onSurfaceMuted` | none (Cabin extension) |
| `primary`, `onPrimary`, `primaryContainer`, `onPrimaryContainer` | `oemColorPrimary` … `oemColorOnPrimaryContainer` |
| safety `error` / `errorContainer` | `oemColorError` / `oemColorErrorContainer` |
| safety `warning`, `charging` | none (Cabin extension, locked) |
| shape `large` / `medium` / `control` | `oemShapeCornerLarge` / `oemShapeCornerMedium` / `oemShapeCornerFull` (pill) or `oemShapeCornerSmall` |

Next step (planned): generate one OEM design token RRO per look from
`cabin.tokens.json`, so a device can apply a Cabin look to stock AAOS apps too.

## Not done yet

- A full build in a clean AOSP tree (`m CabinCompose CabinScreens`)
- Public API dump (`api/current.txt`) and compatibility checks
- `METADATA` / `OWNERS` files (they need real maintainer names)
- Rotary focus pass with `FocusArea` from `car-ui-lib`
