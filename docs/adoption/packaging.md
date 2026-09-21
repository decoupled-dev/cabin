# Packaging

How Cabin is distributed and how to pick **only what you need**. Cabin
supports **dual distribution** from one source tree:

| Distribution | Build | Typical consumer |
| --- | --- | --- |
| **Maven AARs** | Gradle | App developers, Gradle-built OEM feature apps |
| **Soong modules** | `Android.bp` in the Android / AAOS tree | SystemUI, CarLauncher, platform media, vendor chrome |

Coordinates and Soong names below are **planned**.

> **Platform / SystemUI:** do **not** consume Cabin via Gradle
> `implementation(...)`. Use Soong module dependencies after syncing Cabin
> into the tree. See [build-tree](build-tree.md) and
> [soong](../platforms/soong.md).

## Dual distribution

```text
                 same Kotlin / Java / resources
                            │
            ┌───────────────┴───────────────┐
            ▼                               ▼
     Gradle (Maven AAR)              Soong (android_library)
  cabin-tokens / -compliance         CabinTokens / CabinCompliance
  cabin-views / -compose / -foundation / -gauges
            │                               │
            ▼                               ▼
      App developers                 SystemUI / build-tree
```

- **One source of truth** — co-locate `build.gradle.kts` and `Android.bp` per
  module ([architecture](../architecture.md)).
- **Views-first on platform** — SystemUI depends on Views + tokens +
  compliance only; Compose is not a gate ([build-tree](build-tree.md)).
- **Prebuilts secondary** — `android_library_import` of AARs is allowed for
  constrained vendor partitions; prefer source-in-tree for SystemUI.

## Planned Maven artifacts (app / Gradle consumers)

| Artifact | Coordinate | Typical consumer |
| --- | --- | --- |
| Tokens | `dev.decoupled.cabin:cabin-tokens` | Apps; OEM overlays |
| Compliance | `dev.decoupled.cabin:cabin-compliance` | Apps |
| Foundation | `dev.decoupled.cabin:cabin-foundation` | Shared State/Action; size classes |
| Compose UI | `dev.decoupled.cabin:cabin-compose` | Feature apps |
| Views UI | `dev.decoupled.cabin:cabin-views` | Legacy / Views apps built with Gradle; SystemUI via Soong |
| Gauges | `dev.decoupled.cabin:cabin-gauges` | Cluster/HUD apps only |

Group ID `dev.decoupled.cabin` is illustrative and may be finalized at first
publish.

## Planned Soong modules (build-tree consumers)

| Soong `name` | Maps to | Platform role |
| --- | --- | --- |
| `CabinTokens` | `cabin-tokens` | Required |
| `CabinCompliance` | `cabin-compliance` | Required with UI |
| `CabinFoundation` | `cabin-foundation` | Transitive via Views / Compose |
| `CabinViews` | `cabin-views` | **Primary** for SystemUI / chrome |
| `CabinCompose` | `cabin-compose` | Opt-in only |
| `CabinGauges` | `cabin-gauges` | Opt-in; never SystemUI |

Alpha `Android.bp` scaffolding is co-located with each module. Full guide:
[build-tree](build-tree.md). SystemUI fragment:
[sketches/systemui-cabin](sketches/systemui-cabin/).

## What is not shipped on product images

- `samples/*`
- `catalog`
- `website`
- Internal screenshot test fixtures (may ship as opt-in `cabin-*-test` later)

## Dependency graph (reminder)

```text
cabin-tokens / CabinTokens
     ▲
cabin-compliance / CabinCompliance
     ▲
cabin-foundation / CabinFoundation
     ▲
 ┌───┴───────────────┐
compose            views
     ▲
gauges (opt-in; never SystemUI)
```

`cabin-compose` ⊀ `cabin-views` (no dependency either way). Same rule for
`CabinCompose` / `CabinViews`. `CabinGauges` must never appear on SystemUI.

## Selecting modules — Gradle (apps)

### Compose-only app

```kotlin
implementation("dev.decoupled.cabin:cabin-compose:<version>")
```

### Views-only app (Gradle-built)

```kotlin
implementation("dev.decoupled.cabin:cabin-views:<version>")
```

Use this for application modules that still use Views **and** are built with
Gradle. For SystemUI and other AOSP build-tree targets, use Soong instead —
[build-tree](build-tree.md).

### OEM overlay library (Gradle)

```kotlin
implementation("dev.decoupled.cabin:cabin-tokens:<version>")
// optionally: compliance if overlay ships default policies
```

### Full cabin feature with chrome previews (apps)

```kotlin
implementation("dev.decoupled.cabin:cabin-compose:<version>")
implementation("dev.decoupled.cabin:cabin-views:<version>")
```

Use sparingly in a single APK; prefer split across system vs app partitions.

## Selecting modules — Soong (platform)

```bp
// SystemUI / chrome: thin Views path only
static_libs: [
    "CabinTokens",
    "CabinCompliance",
    "CabinViews",
]
```

Do not add `CabinCompose`, `CabinGauges`, catalog, or samples to SystemUI. Sketch + manifest
sync: [build-tree](build-tree.md) ·
[systemui-cabin](sketches/systemui-cabin/).

## Versioning (planned)

- SemVer once stable (Maven).
- Platform pins Cabin via repo manifest revision/tag.
- Compliance + tokens may version independently but UI releases declare
  compatible token/compliance ranges.
- Document breaking token renames prominently — tokens are the long-term
  contract.

## AAOS image considerations

| Partition / image piece | Suggested Cabin content | Consumption |
| --- | --- | --- |
| System UI APK | `CabinViews` + tokens + compliance | **Soong** |
| CarLauncher / platform media | Views and/or Compose per existing stack | **Soong** |
| Gradle-built bundled apps | Maven `cabin-compose` and/or `cabin-views` | **Gradle** |
| Vendor brand | RROs + optional token overlay module | Overlay / Soong |

Do not install the catalog app on production user images.

## Anti-bloat rules

1. No “umbrella” AAR or Soong meta-module that pulls both stacks and samples
   by default.
2. SystemUI never depends on Compose, gauges, catalog, or samples.
3. Optional domain packs (future) only if metrics show need — start coarse
   and split later carefully.
4. Keep transitive deps minimal (no forcing full Material into system UI
   without review).
5. Prefer generating resources from tokens over shipping large unused brand
   packs; prefer RROs over forking.

## Related

- [Build-tree / Soong adoption](build-tree.md)
- [Soong engineering](../platforms/soong.md)
- [Architecture](../architecture.md)
- [Integration](integration.md)
- [Roadmap](../roadmap.md)
