# Build-tree / Soong adoption

OEM programs adopt Cabin in **AOSP build-tree** apps — SystemUI, CarLauncher,
platform media, and vendor chrome — via **Soong** (`Android.bp`), not Gradle.
Gradle Maven AARs remain the path for app developers. Both paths compile the
**same** Kotlin/Java/resources source of truth.

> Module names, paths, and `Android.bp` sketches below are **planned**. No
> real `Android.bp` files ship in this docs phase.

## Problem statement

If Cabin were Gradle-only:

- SystemUI, CarLauncher, and other `Android.bp` targets could not depend on
  Cabin the way platform code depends on other `android_library` modules.
- Teams would vendor JARs/AARs ad hoc, drift from source, or fork UI into the
  tree.
- “Thin by design” would fail: images would pull Compose or samples just to
  get Views chrome.

Cabin therefore requires **dual consumption**: Gradle for apps, Soong for
platform. See [packaging](packaging.md) and [architecture](../architecture.md).

## Dual-path model

| | **Gradle consumers** | **Soong / build-tree consumers** |
| --- | --- | --- |
| Who | App developers, OEM feature APKs built with Gradle | SystemUI, CarLauncher, platform media, vendor UI in the Android tree |
| Build | `build.gradle.kts` → Maven AARs | `Android.bp` → `android_library` (and related) |
| Depend on | Maven coordinates (`dev.decoupled.cabin:…`, **planned**) | Soong module names (`CabinViews`, …, **planned**) |
| Primary UI stack | Compose and/or Views | **Views-first** (`CabinViews` + tokens + compliance) |
| Compose | First-class | Optional — only if the platform app already uses Compose; **not** a gate for SystemUI |
| Sync into tree | Not required | Repo **manifest** project (e.g. `external/cabin`) |
| Theming | Token overlay modules / theme APIs | **RROs** + optional token overlay bp modules |

One source tree; two build graphs. No divergent “platform fork” of Cabin UI.

## Views-first for build-tree

Platform chrome is Views/XML today. Cabin’s primary build-tree surface is:

```text
CabinTokens + CabinCompliance + CabinViews
```

Compose (`CabinCompose`, **planned**) remains first-class for:

- Gradle app developers
- Platform apps that already ship Compose

Compose is **not** required to adopt Cabin in SystemUI. See
[views](../platforms/views.md) and [soong](../platforms/soong.md).

## Planned repo layout (dual build files)

Each library module carries **both** Gradle and Soong metadata beside the same
sources:

```text
cabin/                                    # synced into Android tree via manifest
├── cabin-tokens/
│   ├── src/main/…                        # shared source
│   ├── build.gradle.kts                  # Maven publishing (apps)
│   └── Android.bp                        # Soong android_library (platform)
├── cabin-compliance/
│   ├── build.gradle.kts
│   └── Android.bp
├── cabin-views/
│   ├── build.gradle.kts
│   └── Android.bp
├── cabin-compose/                        # apps + Compose-capable platform apps
│   ├── build.gradle.kts
│   └── Android.bp
├── samples/                              # Gradle-oriented; not on device images
├── catalog/
├── website/
└── docs/
```

Illustrative tree paths after sync: `external/cabin` or
`packages/apps/Cabin` (product choice).

## Planned Soong module names

| Gradle module | Planned Soong `name` | Platform role |
| --- | --- | --- |
| `cabin-tokens` | `CabinTokens` | Always |
| `cabin-compliance` | `CabinCompliance` | Always with UI |
| `cabin-views` | `CabinViews` | **Primary** for SystemUI / chrome |
| `cabin-compose` | `CabinCompose` | Opt-in; never required by SystemUI |

Hard rule: `CabinViews` must not depend on `CabinCompose`. SystemUI bp must
not list `CabinCompose`, catalog, samples, or website modules.

## Manifest project (illustrative)

```xml
<!-- Planned — product manifest fragment -->
<project
    path="external/cabin"
    name="decoupled-dev/cabin"
    revision="refs/tags/cabin-x.y.z" />
```

Products pin a revision/tag; platform `Android.bp` files `static_libs` /
`libs` the Soong module names above. App developers continue to use Maven
without cloning the full platform tree.

## Planned `Android.bp` sketches

### Library modules

```bp
// Planned — cabin-tokens/Android.bp
android_library {
    name: "CabinTokens",
    srcs: ["src/main/java/**/*.kt"],
    resource_dirs: ["src/main/res"],
    sdk_version: "system_current", // product-specific; finalize per branch
    min_sdk_version: "29",
}

// Planned — cabin-compliance/Android.bp
android_library {
    name: "CabinCompliance",
    srcs: ["src/main/java/**/*.kt"],
    static_libs: ["CabinTokens"],
    sdk_version: "system_current",
}

// Planned — cabin-views/Android.bp
android_library {
    name: "CabinViews",
    srcs: ["src/main/java/**/*.kt"],
    resource_dirs: ["src/main/res"],
    static_libs: [
        "CabinTokens",
        "CabinCompliance",
    ],
    sdk_version: "system_current",
}

// Planned — cabin-compose/Android.bp (opt-in; not for SystemUI)
android_library {
    name: "CabinCompose",
    srcs: ["src/main/java/**/*.kt"],
    static_libs: [
        "CabinTokens",
        "CabinCompliance",
        // Compose runtime libs as required by the platform branch
    ],
    sdk_version: "system_current",
}
```

Exact `sdk_version`, Kotlin flags, and Compose static_libs vary by Android
branch — see [soong](../platforms/soong.md).

### SystemUI dependency (thin)

```bp
// Planned — fragment inside SystemUI's Android.bp
android_library {
    name: "SystemUI-Cabin",
    // …
    static_libs: [
        "CabinTokens",
        "CabinCompliance",
        "CabinViews",
        // Do NOT add CabinCompose, catalog, or samples
    ],
}
```

## Per-target guidance

### SystemUI / status + system bars

- Depend on `CabinTokens`, `CabinCompliance`, `CabinViews` only.
- Prefer Cabin status/system bar components and slots
  ([system bars](../components/system-bars.md),
  [status bars](../components/status-bars.md)).
- Brand with RROs / token overlays — not forks.
- Keep compliance host at a system root so gated interactions are consistent.

### CarLauncher

- Same thin Views deps for chrome and launcher surfaces built as Views.
- If a launcher subtree is already Compose, `CabinCompose` may be added
  **locally** to that module — still keep SystemUI free of Compose.

### Platform media

- Choose stack per existing app: Views → `CabinViews`; Compose →
  `CabinCompose`.
- Share tokens/compliance either way; do not duplicate restriction logic.
- Media domain contracts: [media](../components/media.md).

## OEM theming on platform

| Mechanism | Use |
| --- | --- |
| **Runtime Resource Overlays (RROs)** | Remap theme attributes / resources on device images without editing Cabin sources |
| **Token overlay bp module** | Optional `OemCabinTokens` (planned) that depends on `CabinTokens` and supplies brand mappings |
| **Policy overlay** | Tighten Restriction Engine baselines in product config ([compliance](../compliance/README.md)) |

Do not remap safety semantic colors for decoration
([tokens](../design-language/tokens.md)). Extension stays in product modules
([extension model](../components/extension-model.md)).

## Prebuilts (secondary)

Some vendor partitions cannot sync full source. Cabin allows
`android_library_import` of published AARs as a **secondary** path:

```bp
// Planned — constrained vendor use only
android_library_import {
    name: "CabinViews-prebuilt",
    aars: ["prebuilts/CabinViews.aar"],
}
```

**Prefer source-in-tree** for SystemUI and other privileged chrome so platform
branches can patch, overlay, and debug with the rest of the image. Prebuilts
must still obey thin-dep rules (no Compose smuggled into SystemUI).

## Anti-bloat rules (platform images)

1. SystemUI bp lists only tokens + compliance + views.
2. Never install catalog, samples, or website packages on user/system images.
3. Do not create an umbrella `CabinAll` Soong module for platform.
4. Compose stays opt-in per app module.
5. Prefer RROs over copying Cabin res into vendor overlays that drift forever.

## Relation to `car-ui-lib` and Car APIs

Platform teams may already consume **Android Automotive** libraries such as
`car-ui-lib` (and Car framework APIs) through Soong. Cabin’s consumption shape
is intentionally familiar: library modules in the tree, thin static_libs,
RRO-friendly resources.

Cabin is **not** a claim to replace Car framework APIs or to supersede
`car-ui-lib` as a policy. Cabin is an open **design language + dual-stack UI
kit** (tokens, compliance, Compose + Views) that products can adopt beside
existing Car APIs. Map `CarUxRestrictions` (and related signals) into Cabin’s
Restriction Engine adapters — do not reimplement vehicle services inside Cabin.

## Checklist

- [ ] Manifest pins Cabin revision into the tree
- [ ] SystemUI depends on Views path only
- [ ] RROs / token overlays own brand
- [ ] No catalog/samples on the image
- [ ] Prebuilts only where source sync is impossible
- [ ] Parity fixtures still apply when a second stack exists in apps

## Related

- [Soong engineering notes](../platforms/soong.md)
- [Packaging](packaging.md)
- [Integration](integration.md)
- [Views](../platforms/views.md)
- [Architecture](../architecture.md)
- [Product features](../product/features.md)
