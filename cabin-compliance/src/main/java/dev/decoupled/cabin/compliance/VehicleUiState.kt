package dev.decoupled.cabin.compliance

/**
 * Adapter-owned vehicle / UX signals used to derive [CabinUiMode].
 *
 * Cabin does not call Car APIs directly — platform / app adapters map into this
 * model (see docs/compliance/restriction-states.md).
 *
 * @property parkMode `true` when gear/park indicates parked; `null` if unknown
 * @property speedKph vehicle speed in km/h when known
 * @property isDriving adapter hint that the vehicle is in purposeful motion
 * @property uxRestrictions active UX restriction flags from the platform profile
 * @property movingSpeedThresholdKph speeds at or above this count as moving
 */
data class VehicleUiState(
    val parkMode: Boolean? = null,
    val speedKph: Float? = null,
    val isDriving: Boolean? = null,
    val uxRestrictions: Set<UxRestriction> = emptySet(),
    val movingSpeedThresholdKph: Float = DEFAULT_MOVING_SPEED_THRESHOLD_KPH,
    /** Optional fixture label for tests / debugging. */
    val label: String? = null,
) {
    override fun toString(): String = label ?: "VehicleUiState"

    companion object {
        const val DEFAULT_MOVING_SPEED_THRESHOLD_KPH: Float = 1.0f

        /** Test / demo helpers aligned with restriction-states fixtures. */
        fun parked(): VehicleUiState =
            VehicleUiState(
                parkMode = true,
                speedKph = 0f,
                isDriving = false,
                label = "Parked",
            )

        fun idling(): VehicleUiState =
            VehicleUiState(
                parkMode = false,
                speedKph = 0f,
                isDriving = false,
                label = "Idling",
            )

        fun moving(speedKph: Float = 40f): VehicleUiState =
            VehicleUiState(
                parkMode = false,
                speedKph = speedKph,
                isDriving = true,
                label = "Moving",
            )

        fun restricted(speedKph: Float = 40f): VehicleUiState =
            VehicleUiState(
                parkMode = false,
                speedKph = speedKph,
                isDriving = true,
                uxRestrictions = setOf(UxRestriction.DistractionOptimized),
                label = "Restricted",
            )

        fun unknown(): VehicleUiState = VehicleUiState(label = "Unknown")
    }
}
