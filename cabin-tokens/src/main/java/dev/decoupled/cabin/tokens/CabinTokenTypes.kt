package dev.decoupled.cabin.tokens

/**
 * ARGB color token with optional safety lock metadata.
 *
 * @property lock non-null when `extensions.cabin.lock` is set in the token stub
 *   (e.g. `"safety-adjacent"` for warning/error).
 */
data class CabinColor(
    val argb: Int,
    val hex: String,
    val resourceName: String,
    val lock: String? = null,
) {
    val isSafetyLocked: Boolean get() = lock == LOCK_SAFETY_ADJACENT

    companion object {
        const val LOCK_SAFETY_ADJACENT: String = "safety-adjacent"
    }
}

enum class CabinUnit {
    DP,
    SP,
    MS,
}

data class CabinMeasure(
    val value: Float,
    val unit: CabinUnit,
    val resourceName: String,
) {
    val dp: Float
        get() {
            require(unit == CabinUnit.DP) { "Measure $resourceName is $unit, not DP" }
            return value
        }

    val sp: Float
        get() {
            require(unit == CabinUnit.SP) { "Measure $resourceName is $unit, not SP" }
            return value
        }

    val ms: Int
        get() {
            require(unit == CabinUnit.MS) { "Measure $resourceName is $unit, not MS" }
            return value.toInt()
        }
}

/** Day / night cabin color schemes from `cabin.color.scheme.*`. */
enum class CabinColorScheme {
    Day,
    Night,
}

data class CabinColorSchemeColors(
    val surface: CabinColor,
    val onSurface: CabinColor,
)
