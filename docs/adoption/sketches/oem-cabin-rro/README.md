# OEM Cabin RRO sketch (illustrative)

Documentation-only sketch for a platform **Runtime Resource Overlay** that
rebrands Cabin Theme Kit without forking widgets ([ADR 0003](../../adr/0003-tokens-via-overlay-rro.md),
[theme-kit](../theme-kit.md)).

Not a buildable product module in this repo — copy into the OEM Android tree.

## Layout

```text
OemCabinThemeRRO/
├── Android.bp
├── AndroidManifest.xml
└── res/values/colors.xml
```

## Android.bp

```bp
runtime_resource_overlay {
    name: "OemCabinThemeRRO",
    resource_dirs: ["res"],
    manifest: "AndroidManifest.xml",
    sdk_version: "system_current",
}
```

## AndroidManifest.xml

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.oem.cabin.theme.rro">
    <application android:hasCode="false" />
    <!-- targetPackage: the APK that hosts Theme.Cabin / CabinViews resources -->
    <overlay
        android:targetPackage="com.android.systemui"
        android:isStatic="true"
        android:priority="10" />
</manifest>
```

## res/values/colors.xml

```xml
<resources>
    <!-- Brand-facing only -->
    <color name="cabin_color_semantic_primary">#1565C0</color>
    <color name="cabin_color_semantic_onPrimary">#FFFFFF</color>

    <!-- Do NOT override for decoration:
         cabin_color_scheme_warning
         cabin_color_scheme_error
         cabin_color_semantic_warning
         cabin_color_semantic_error
         night contrast-locked charging -->
</resources>
```

Widgets keep resolving `?attr/cabin_colorPrimary` via Theme Kit; the RRO
changes the underlying resource.
