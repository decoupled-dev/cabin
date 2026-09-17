---
name: cabin-soong
description: >-
  Integrate Cabin into AOSP/AAOS via Soong — Android.bp modules, repo manifest
  sync, thin SystemUI dependencies, RROs, and secondary AAR prebuilts. Use when
  editing platform build files, SystemUI/CarLauncher deps, external/cabin
  manifests, or correcting Gradle-only platform guidance.
license: Apache-2.0
metadata:
  audience: agents
  project: cabin
---

# Cabin Soong / build-tree

## Dual consumption

Same sources: Gradle for apps, Soong for platform. Co-locate `Android.bp` with
`build.gradle.kts` per module (**Alpha scaffolding** in-repo).

Root [`Android.bp`](../../Android.bp) defines `CabinAndroidLibraryDefaults`
only — never an umbrella `CabinAll`.

## Soong names

`CabinTokens` · `CabinCompliance` · `CabinViews` · `CabinCompose` (opt-in)

Upcoming Views primitives (Button, ListItem, …) stay inside **`CabinViews`** —
do not add per-widget Soong modules.

## SystemUI thin deps

```bp
// Host SystemUI / chrome — only these
static_libs: ["CabinTokens", "CabinCompliance", "CabinViews"]
```

Sketch: [docs/adoption/sketches/systemui-cabin](../../docs/adoption/sketches/systemui-cabin/).

**Never** add `CabinCompose`, catalog, or samples to SystemUI.

## Guard

```bash
python3 tools/check_soong_thin_deps.py
```

## Manifest

Sync Cabin (e.g. `external/cabin`) via repo manifest; pin revision/tag.
Prefer **source-in-tree** for SystemUI; `android_library_import` of AARs is
secondary for constrained vendor partitions.

## Theming

RROs + optional OEM token overlay bp modules — no core forks.

## Docs

- [docs/adoption/build-tree.md](../../docs/adoption/build-tree.md)
- [docs/platforms/soong.md](../../docs/platforms/soong.md)
- [docs/adoption/packaging.md](../../docs/adoption/packaging.md)

## vs car-ui-lib

Familiar Soong library shape. Cabin does **not** replace Car framework APIs or
claim to supersede `car-ui-lib`.

Related: `cabin-views` · [AGENTS.md](../../AGENTS.md)
