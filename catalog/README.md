# Cabin Catalog (sample)

Thin interactive catalog for Cabin MVP chrome — **not** a product library and
**not** a SystemUI / Soong dependency.

## What it demos

| Area | Stack | Notes |
| --- | --- | --- |
| Theme Kit day/night | Compose colors + Views `Theme.Cabin` | outline, container, locked warning / error / charging |
| System Bar + Status Bar | **Views (Alpha)** via `AndroidView` | Restriction Engine–gated |
| System Bar + Status Bar | **Compose (Experimental)** | Same fixtures; parity smoke |
| Restriction Engine | Parked / Idling / Moving / Restricted | Gates activations on both stacks |
| Signals + tones | Status items | unavailable / stale / fault + Warning / Charging |

**Out of scope:** media, HVAC, EV, vehicle-controls screens.

## Packaging

```text
catalog  →  cabin-compose
         →  cabin-views  →  cabin-compliance → cabin-tokens
```

`cabin-*` modules never depend on `catalog`. Do not install this APK on
production user images ([packaging](../docs/adoption/packaging.md)).

## Run

Requires JDK 17+ and Android SDK (`local.properties` → `sdk.dir=`).

```bash
# From repo root
./gradlew :catalog:assembleDebug

# Install on a device / emulator (API 29+)
./gradlew :catalog:installDebug
adb shell am start -n dev.decoupled.cabin.catalog/.CatalogActivity
```

Android Studio: open the repo, select the **catalog** run configuration, Run.

## Tests

```bash
./gradlew :catalog:test
```

Covers demo fixtures (signals / tones / interactions), day-night Theme Kit
resolution for the Views host context, Restriction Engine fixture modes, and a
packaging guard that product modules do not depend on `:catalog`.

## Layout

Compose shell hosts both stacks in one activity (cleaner than two APKs for this
thin slice). Views chrome is embedded with `AndroidView` under
`Theme.Cabin` + explicit day/night `Configuration`.
