# Soong / platform engineering

Engineering notes for building and consuming Cabin inside an Android /
AAOS source tree with **Soong**. Product adoption flow lives in
[build-tree](../adoption/build-tree.md). Module names below are **planned**.

## Dual build systems

Cabin libraries are authored once and exposed through:

| Build system | Artifact | Consumer |
| --- | --- | --- |
| Gradle | Maven AAR (`dev.decoupled.cabin:…`, planned) | App developers |
| Soong | `android_library` (e.g. `CabinViews`) | SystemUI, CarLauncher, platform apps |

Keep `build.gradle.kts` and `Android.bp` co-located with the same `srcs` /
`res` so behavior cannot diverge by accident ([architecture](../architecture.md)).

## Planned bp module types

| Type | Cabin use |
| --- | --- |
| `android_library` | Primary form for `CabinTokens`, `CabinCompliance`, `CabinViews`, `CabinCompose` |
| `android_library_import` | Secondary prebuilt AAR import for constrained vendor partitions |
| `runtime_resource_overlay` | OEM brand RROs targeting Cabin / SystemUI themes |
| `java_library` | Only if a pure-JVM helper is later required (avoid for UI) |

Do not introduce a Soong “fat” module that aggregates Compose + Views +
samples for convenience.

## Resource packaging

- Cabin Views resources (themes, attrs, drawables, dimens) ship inside
  `CabinViews` / `CabinTokens` resource dirs.
- Prefer **token-backed attributes** so RROs remap roles instead of one-off
  colors ([tokens](../design-language/tokens.md)).
- Avoid resource name collisions with SystemUI by using a stable `cabin_` /
  attr namespace convention (finalize at implementation).
- Day/night: rely on standard `values` / `values-night` (or Cabin scheme
  resources) consistent with Gradle packaging.

## API stability across platform branches

Platform images pin Cabin by **manifest revision**, not by floating `main`.

| Concern | Practice |
| --- | --- |
| SemVer / tags | Tag releases that declare compatible token + compliance + views sets |
| Branch backports | Cherry-pick carefully; do not silently change safety color locks |
| Public vs system SDK | Choose `sdk_version` appropriate to the host (system UI often needs system APIs) — document per release |
| Deprecation | Prefer additive attrs/APIs; document removals in release notes |

Apps on Maven may move faster than a frozen platform pin; that is expected.
Parity contracts still define meaning ([views](views.md),
[compose](compose.md)).

## Compose on platform (caveats)

- Compose support in the Android tree varies by branch and product
  configuration.
- **SystemUI must not treat Compose as a prerequisite** for Cabin.
- `CabinCompose` Soong module is opt-in for platform apps that already ship
  Compose runtimes/static_libs on that branch.
- If Compose is unavailable, Views path remains complete for chrome and for
  domain screens that choose Views.

## Testing on device images

| Layer | Approach |
| --- | --- |
| Host unit | Shared fixtures for tokens/compliance (also run in Gradle CI) |
| Instrumentation | On-device tests in a debug/userdebug image against SystemUI / sample targets |
| Visual | Screenshot or manual glance QA day/night on target density |
| Gating | Drive-state fixtures for Restriction Engine in platform integration tests |
| Prebuilt path | Verify imported AARs match the tagged source revision |

Catalog and samples stay off production images; use userdebug or separate
test apps ([packaging](../adoption/packaging.md)).

## Thin dependency checklist (Soong)

- [ ] `CabinViews` static_libs: tokens + compliance only (plus approved Android
      support libs)
- [ ] SystemUI does not reference `CabinCompose`
- [ ] No `Makedirs`/defaults that pull catalog or samples into `PRODUCT_PACKAGES`
- [ ] RRO packages listed explicitly; not “all overlays in tree”

## Related

- [Build-tree adoption](../adoption/build-tree.md)
- [Views](views.md)
- [Compose](compose.md)
- [Architecture](../architecture.md)
