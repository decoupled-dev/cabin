# Cabin kitchen sink (sample)

Interactive Android app that **uses** the Cabin libraries: inspect every
inventory component, then open screens composed from those components.

Not a product / SystemUI / Soong dependency. Do not install on user images.

## Destinations

| Route | What it is |
| --- | --- |
| Home | Entry to Components and Screens |
| Components | Family index → one-component inspector (states, variants, RE) |
| Screens | Dashboard, now playing, climate, charge, settings, vehicle |

Day/Night and Parked/Moving sit on every route.

## Run

```bash
./gradlew :samples:kitchen-sink:assembleDebug
./gradlew :samples:kitchen-sink:installDebug
adb shell am start -n dev.decoupled.cabin.sink/.KitchenSinkActivity
```

## Tests

```bash
./gradlew :samples:kitchen-sink:test
```

## Packaging

```text
samples/kitchen-sink  →  cabin-compose / cabin-views / cabin-gauges
```

`cabin-*` modules never depend on this sample. **No `Android.bp`.**
