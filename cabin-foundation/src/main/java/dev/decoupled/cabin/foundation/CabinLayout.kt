package dev.decoupled.cabin.foundation

/**
 * Automotive window / display size classes — not phone WindowSizeClass.
 *
 * Experimental scaffold: values are a contract for adaptive layouts. OEM
 * programs map physical millimeters and viewing distance onto these.
 */
enum class CabinWindowSizeClass {
    Cluster,
    CompactCenter,
    StandardLandscape,
    LargeLandscape,
    TallPortrait,
    Ultrawide,
    RearSeat,
    Passenger,
}

/** Which occupant zone a surface is attached to. */
enum class CabinOccupantZone {
    Driver,
    Passenger,
    RearLeft,
    RearRight,
    Unspecified,
}

/** Last-used (or active) input modality for adaptive focus visuals. */
enum class CabinInputModality {
    Touch,
    Rotary,
    Dpad,
    SteeringKey,
    Voice,
}

/** Display orientation, including fixed-portrait head units. */
enum class CabinDisplayOrientation {
    Landscape,
    Portrait,
    FixedPortrait,
    FixedLandscape,
}

/**
 * Catalog / emulator display profile: size class, density, orientation, occupant.
 *
 * Not a WindowMetrics replacement — adapters populate this from the host display.
 */
data class CabinDisplayProfile(
    val sizeClass: CabinWindowSizeClass = CabinWindowSizeClass.StandardLandscape,
    val densityDpi: Int = 160,
    val orientation: CabinDisplayOrientation = CabinDisplayOrientation.Landscape,
    val occupantZone: CabinOccupantZone = CabinOccupantZone.Driver,
    val occupantZoneId: Int = 0,
) {
    companion object {
        val StandardCenter: CabinDisplayProfile = CabinDisplayProfile()
        val Cluster: CabinDisplayProfile = CabinDisplayProfile(
            sizeClass = CabinWindowSizeClass.Cluster,
            occupantZone = CabinOccupantZone.Driver,
        )
        val Ultrawide: CabinDisplayProfile = CabinDisplayProfile(
            sizeClass = CabinWindowSizeClass.Ultrawide,
        )
        val RearSeat: CabinDisplayProfile = CabinDisplayProfile(
            sizeClass = CabinWindowSizeClass.RearSeat,
            occupantZone = CabinOccupantZone.RearLeft,
        )
    }
}

/**
 * Placeholder insets for system chrome (top / bottom / side / dock) and
 * curved or pillar-to-pillar no-go zones. Values are dp; OEM cutout math is
 * out of scope for the scaffold pass.
 */
data class CabinScaffoldInsets(
    val topDp: Int = 0,
    val bottomDp: Int = 0,
    val startDp: Int = 0,
    val endDp: Int = 0,
    val dockDp: Int = 0,
    val curvedNoGoStartDp: Int = 0,
    val curvedNoGoEndDp: Int = 0,
) {
    companion object {
        val None: CabinScaffoldInsets = CabinScaffoldInsets()
    }
}

/** Adaptive scaffold kinds for app frames. */
enum class CabinAdaptiveScaffoldKind {
    SinglePane,
    ListDetail,
    SupportingPane,
    TwoPane,
    ThreePane,
    SplitScreen,
    DashboardGrid,
    Immersive,
    PortraitStacked,
}
