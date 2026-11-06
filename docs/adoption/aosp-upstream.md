# AOSP upstream path (`external/cabin`)

**Status:** planned. Nothing here has been built inside a real AOSP tree yet.

Cabin's long-term target is to be good enough for AAOS to carry it by default,
as `external/cabin`, next to `car-ui-lib` and `com.android.oem.tokens`. Code
alone can't guarantee that. What we can do is make adoption a drop-in with no
rework. This page is the checklist.

## Drop-in shape

```
external/cabin/
  Android.bp                 # CabinAndroidLibraryDefaults (no umbrella module)
  METADATA  OWNERS  NOTICE  MODULE_LICENSE_APACHE2
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
| Build | Same sources build with Gradle and Soong | Gradle verified; Soong unverified |
| Deps | AOSP prebuilts only: `androidx.compose.runtime_runtime`, `androidx.compose.ui_ui`, `androidx.compose.foundation_foundation`, `androidx.compose.material3_material3` | Declared in `Android.bp` |
| Car APIs | `android.car` as an optional `libs` entry, never packaged | `cabin-compliance` |
| Tokens | Roles named after Material 3 roles, the same vocabulary `com.android.oem.tokens` uses, so one RRO can theme both | Mapping is planned; verify names against the target branch |
| Safety | Locked warning/error/charging; 4.5:1 text contrast; 76 dp targets; Restriction Engine gates | Enforced at codegen and in tests |
| Assets | No GMS, no network, no downloadable fonts; libraries ship no fonts | Showcase bundles OFL fonts only |
| Licensing | Apache-2.0 header on new source files | New kit and screens files |
| API | Android API and Compose API guidelines: `modifier` first optional parameter, slot APIs, hoisted state | Kit and screens |
| SystemUI | Views-first (ADR 0002); Compose modules never in SystemUI `static_libs` | Checked by `check_soong_thin_deps.py` |

## Not done yet

- A build in a real AOSP tree (`m CabinCompose CabinScreens`)
- Public API dump (`api/current.txt`) and compatibility checks
- `METADATA` / `OWNERS` files (they need real maintainer names)
- Rotary focus pass with `FocusArea` from `car-ui-lib`
