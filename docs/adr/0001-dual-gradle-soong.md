# ADR 0001: Dual Gradle + Soong distribution

- **Status:** Accepted
- **Date:** 2026-09-15

## Context

OEM SystemUI, CarLauncher, and platform media live in the AOSP **build-tree**
and depend on libraries through **Soong** (`Android.bp`). App developers
consume libraries through **Gradle / Maven**. A Gradle-only Cabin would block
platform adoption or force brittle prebuilt forks.

## Decision

Cabin ships **one source tree** compiled two ways:

1. Gradle → Maven AARs (`dev.decoupled.cabin:cabin-*`, planned)
2. Soong → `android_library` modules (`CabinTokens`, `CabinCompliance`,
   `CabinViews`, `CabinCompose`, planned)

Co-locate `build.gradle.kts` and `Android.bp` per module. Prefer
**source-in-tree** for SystemUI; AAR `android_library_import` is secondary.

## Consequences

- Agents must not document SystemUI adoption as Gradle `implementation`
- Thin Soong deps are enforceable in platform bp files
- CI may be Gradle-first; platform verification is partner/tree-owned for MVP

## Links

- [build-tree.md](../adoption/build-tree.md)
- [api-contracts.md](../api-contracts.md)
- [packaging.md](../adoption/packaging.md)
