# SystemUI ← Cabin wiring sketch (illustrative)

Documentation-only fragment for a **SystemUI-shaped** Soong target that
consumes Cabin on-tree ([ADR 0001](../../../adr/0001-dual-gradle-soong.md),
[ADR 0002](../../../adr/0002-views-first-platform.md),
[build-tree](../../build-tree.md)).

Not a buildable product module in this repo — copy the `static_libs` into the
host SystemUI (or vendor chrome) `Android.bp` after syncing Cabin (e.g.
`external/cabin`).

The file is named `Android.bp.fragment` so a full-tree Soong walk does **not**
register a fake module from `docs/`.

## Thin dependency rule

| Include | Exclude |
| --- | --- |
| `CabinTokens` | `CabinCompose` |
| `CabinCompliance` | catalog / samples / website |
| `CabinViews` | any umbrella `CabinAll` |

Upcoming Views primitives (Button, ListItem, …) land **inside** `CabinViews`
— no new Soong names per widget.

## Layout

```text
systemui-cabin/                 # docs sketch only
├── README.md
└── Android.bp.fragment
```

## Fragment

See [`Android.bp.fragment`](Android.bp.fragment). Condensed:

```bp
android_library {
    name: "SystemUI-Cabin-sketch",
    // Host SystemUI already owns srcs / manifest / platform deps.
    static_libs: [
        "CabinTokens",
        "CabinCompliance",
        "CabinViews",
        // Do NOT add CabinCompose, catalog, or samples
    ],
}
```

Prefer editing the real SystemUI module’s `static_libs` over creating a
wrapper library, unless the product already uses a thin “chrome deps”
aggregation target.

## Manifest sync (reminder)

```xml
<!-- Planned — product manifest fragment -->
<project
    path="external/cabin"
    name="decoupled-dev/cabin"
    revision="refs/tags/cabin-x.y.z" />
```

## Brand

Remap brand colors with an RRO (or optional OEM token overlay module) — do
not fork `CabinViews`. Sketch:
[`../oem-cabin-rro/`](../oem-cabin-rro/README.md).

## Verify (repo-side, no AAOS tree)

```bash
python3 tools/check_soong_thin_deps.py
```

Partner trees still own full Soong / image builds.
