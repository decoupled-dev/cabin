@file:JvmName("CabinTokensGenerated")

package dev.decoupled.cabin.tokens

/**
 * Public Cabin token constants generated from `tokens/cabin.tokens.json`.
 *
 * Do not edit by hand -- run `python3 tools/generate_cabin_tokens.py`.
 * Stub version: 0.1.1-stub
 *
 * This module has no Compose, Views widget, AppCompat, or Material dependencies.
 */
object CabinTokens {
    const val SOURCE_VERSION: String = "0.1.1-stub"
    const val SOURCE_PATH: String = "tokens/cabin.tokens.json"

    /** Semantic color role names locked as safety-adjacent. */
    val safetyLockedSemanticColors: Set<String> = setOf("warning", "error")

    /** Night scheme roles with locked contrast (no soft-wash). */
    val nightContrastLockedSchemeColors: Set<String> = setOf("warning", "error", "charging")

    /** Resolve day/night scheme chrome + feedback roles. */
    fun colorScheme(scheme: CabinColorScheme): CabinColorSchemeColors = Color.scheme(scheme)

    object Color {
        object Semantic {
            val primary: CabinColor = CabinColor(argb = -16028081, hex = "#0B6E4F", resourceName = "cabin_color_semantic_primary", lock = null)
            val onPrimary: CabinColor = CabinColor(argb = -1, hex = "#FFFFFF", resourceName = "cabin_color_semantic_onPrimary", lock = null)
            val secondary: CabinColor = CabinColor(argb = -12756389, hex = "#3D5A5B", resourceName = "cabin_color_semantic_secondary", lock = null)
            val onSecondary: CabinColor = CabinColor(argb = -1, hex = "#FFFFFF", resourceName = "cabin_color_semantic_onSecondary", lock = null)
            val surface: CabinColor = CabinColor(argb = -15592942, hex = "#121212", resourceName = "cabin_color_semantic_surface", lock = null)
            val onSurface: CabinColor = CabinColor(argb = -855310, hex = "#F2F2F2", resourceName = "cabin_color_semantic_onSurface", lock = null)
            val surfaceVariant: CabinColor = CabinColor(argb = -14803426, hex = "#1E1E1E", resourceName = "cabin_color_semantic_surfaceVariant", lock = null)
            val outline: CabinColor = CabinColor(argb = -10724260, hex = "#5C5C5C", resourceName = "cabin_color_semantic_outline", lock = null)
            val success: CabinColor = CabinColor(argb = -13730510, hex = "#2E7D32", resourceName = "cabin_color_semantic_success", lock = null)
            val warning: CabinColor = CabinColor(argb = -415707, hex = "#F9A825", resourceName = "cabin_color_semantic_warning", lock = "safety-adjacent")
            val error: CabinColor = CabinColor(argb = -2937041, hex = "#D32F2F", resourceName = "cabin_color_semantic_error", lock = "safety-adjacent")
            val charging: CabinColor = CabinColor(argb = -16742021, hex = "#00897B", resourceName = "cabin_color_semantic_charging", lock = null)
            val climate: CabinColor = CabinColor(argb = -16611119, hex = "#0288D1", resourceName = "cabin_color_semantic_climate", lock = null)
            val mediaAccent: CabinColor = CabinColor(argb = -8497214, hex = "#7E57C2", resourceName = "cabin_color_semantic_mediaAccent", lock = null)
            val scrim: CabinColor = CabinColor(argb = -1728053248, hex = "#99000000", resourceName = "cabin_color_semantic_scrim", lock = null)
        }

        private val schemeDay = CabinColorSchemeColors(
            surface = CabinColor(argb = -657931, hex = "#F5F5F5", resourceName = "cabin_color_scheme_surface", lock = null),
            onSurface = CabinColor(argb = -15592942, hex = "#121212", resourceName = "cabin_color_scheme_onSurface", lock = null),
            surfaceVariant = CabinColor(argb = -1513240, hex = "#E8E8E8", resourceName = "cabin_color_scheme_surfaceVariant", lock = null),
            outline = CabinColor(argb = -9079435, hex = "#757575", resourceName = "cabin_color_scheme_outline", lock = null),
            container = CabinColor(argb = -1, hex = "#FFFFFF", resourceName = "cabin_color_scheme_container", lock = null),
            onContainer = CabinColor(argb = -15592942, hex = "#121212", resourceName = "cabin_color_scheme_onContainer", lock = null),
            warning = CabinColor(argb = -415707, hex = "#F9A825", resourceName = "cabin_color_scheme_warning", lock = "safety-adjacent"),
            error = CabinColor(argb = -2937041, hex = "#D32F2F", resourceName = "cabin_color_scheme_error", lock = "safety-adjacent"),
            charging = CabinColor(argb = -16742021, hex = "#00897B", resourceName = "cabin_color_scheme_charging", lock = null),
        )
        private val schemeNight = CabinColorSchemeColors(
            surface = CabinColor(argb = -15592942, hex = "#121212", resourceName = "cabin_color_scheme_surface", lock = null),
            onSurface = CabinColor(argb = -855310, hex = "#F2F2F2", resourceName = "cabin_color_scheme_onSurface", lock = null),
            surfaceVariant = CabinColor(argb = -14803426, hex = "#1E1E1E", resourceName = "cabin_color_scheme_surfaceVariant", lock = null),
            outline = CabinColor(argb = -7697782, hex = "#8A8A8A", resourceName = "cabin_color_scheme_outline", lock = null),
            container = CabinColor(argb = -15066598, hex = "#1A1A1A", resourceName = "cabin_color_scheme_container", lock = null),
            onContainer = CabinColor(argb = -855310, hex = "#F2F2F2", resourceName = "cabin_color_scheme_onContainer", lock = null),
            warning = CabinColor(argb = -19712, hex = "#FFB300", resourceName = "cabin_color_scheme_warning", lock = "safety-adjacent"),
            error = CabinColor(argb = -44462, hex = "#FF5252", resourceName = "cabin_color_scheme_error", lock = "safety-adjacent"),
            charging = CabinColor(argb = -14816842, hex = "#1DE9B6", resourceName = "cabin_color_scheme_charging", lock = "safety-adjacent"),
        )

        fun scheme(scheme: CabinColorScheme): CabinColorSchemeColors = when (scheme) {
            CabinColorScheme.Day -> schemeDay
            CabinColorScheme.Night -> schemeNight
        }
    }

    object Type {
        object Family {
            const val plain: String = "CabinSans"
            const val plainResourceName: String = "cabin_type_family_plain"
        }
        object Role {
            object Display {
                val size: CabinMeasure = CabinMeasure(40f, CabinUnit.SP, "cabin_type_role_display_size")
                val weight: Int = 600 // resource "cabin_type_role_display_weight"
                val lineHeight: CabinMeasure = CabinMeasure(48f, CabinUnit.SP, "cabin_type_role_display_lineHeight")
            }
            object Headline {
                val size: CabinMeasure = CabinMeasure(28f, CabinUnit.SP, "cabin_type_role_headline_size")
                val weight: Int = 600 // resource "cabin_type_role_headline_weight"
                val lineHeight: CabinMeasure = CabinMeasure(36f, CabinUnit.SP, "cabin_type_role_headline_lineHeight")
            }
            object Title {
                val size: CabinMeasure = CabinMeasure(22f, CabinUnit.SP, "cabin_type_role_title_size")
                val weight: Int = 600 // resource "cabin_type_role_title_weight"
                val lineHeight: CabinMeasure = CabinMeasure(28f, CabinUnit.SP, "cabin_type_role_title_lineHeight")
            }
            object Body {
                val size: CabinMeasure = CabinMeasure(18f, CabinUnit.SP, "cabin_type_role_body_size")
                val weight: Int = 400 // resource "cabin_type_role_body_weight"
                val lineHeight: CabinMeasure = CabinMeasure(24f, CabinUnit.SP, "cabin_type_role_body_lineHeight")
            }
            object Label {
                val size: CabinMeasure = CabinMeasure(16f, CabinUnit.SP, "cabin_type_role_label_size")
                val weight: Int = 600 // resource "cabin_type_role_label_weight"
                val lineHeight: CabinMeasure = CabinMeasure(20f, CabinUnit.SP, "cabin_type_role_label_lineHeight")
            }
            object Status {
                val size: CabinMeasure = CabinMeasure(14f, CabinUnit.SP, "cabin_type_role_status_size")
                val weight: Int = 500 // resource "cabin_type_role_status_weight"
                val lineHeight: CabinMeasure = CabinMeasure(18f, CabinUnit.SP, "cabin_type_role_status_lineHeight")
            }
        }
    }

    object Space {
        val xs: CabinMeasure = CabinMeasure(4f, CabinUnit.DP, "cabin_space_xs")
        val sm: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_space_sm")
        val md: CabinMeasure = CabinMeasure(16f, CabinUnit.DP, "cabin_space_md")
        val lg: CabinMeasure = CabinMeasure(24f, CabinUnit.DP, "cabin_space_lg")
        val xl: CabinMeasure = CabinMeasure(32f, CabinUnit.DP, "cabin_space_xl")
    }

    object Size {
        object Touch {
            val minimum: CabinMeasure = CabinMeasure(76f, CabinUnit.DP, "cabin_size_touch_minimum")
        }
        object Icon {
            val status: CabinMeasure = CabinMeasure(24f, CabinUnit.DP, "cabin_size_icon_status")
            val systemBar: CabinMeasure = CabinMeasure(28f, CabinUnit.DP, "cabin_size_icon_systemBar")
        }
    }

    object Elevation {
        val level0: CabinMeasure = CabinMeasure(0f, CabinUnit.DP, "cabin_elevation_level0")
        val level1: CabinMeasure = CabinMeasure(1f, CabinUnit.DP, "cabin_elevation_level1")
        val level2: CabinMeasure = CabinMeasure(3f, CabinUnit.DP, "cabin_elevation_level2")
    }

    object Motion {
        object Fast {
            const val durationMs: Int = 100
            const val durationResourceName: String = "cabin_motion_fast_duration"
        }
        object Medium {
            const val durationMs: Int = 200
            const val durationResourceName: String = "cabin_motion_medium_duration"
        }
        object Slow {
            const val durationMs: Int = 300
            const val durationResourceName: String = "cabin_motion_slow_duration"
        }
    }

    object Component {
        object SystemBar {
            val height: CabinMeasure = CabinMeasure(80f, CabinUnit.DP, "cabin_component_system_bar_height")
            val iconSize: CabinMeasure = CabinMeasure(28f, CabinUnit.DP, "cabin_component_system_bar_iconSize")
            val itemMinSize: CabinMeasure = CabinMeasure(76f, CabinUnit.DP, "cabin_component_system_bar_itemMinSize")
            val gap: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_component_system_bar_gap")
        }
        object StatusBar {
            val height: CabinMeasure = CabinMeasure(48f, CabinUnit.DP, "cabin_component_status_bar_height")
            val iconSize: CabinMeasure = CabinMeasure(24f, CabinUnit.DP, "cabin_component_status_bar_iconSize")
            val itemGap: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_component_status_bar_itemGap")
        }
        object ClimateTile {
            val controlMinSize: CabinMeasure = CabinMeasure(76f, CabinUnit.DP, "cabin_component_climate_tile_controlMinSize")
            val gap: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_component_climate_tile_gap")
            val padding: CabinMeasure = CabinMeasure(16f, CabinUnit.DP, "cabin_component_climate_tile_padding")
            val cornerRadius: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_component_climate_tile_cornerRadius")
        }
        object MediaNowPlaying {
            val artworkSize: CabinMeasure = CabinMeasure(120f, CabinUnit.DP, "cabin_component_media_now_playing_artworkSize")
            val transportMinSize: CabinMeasure = CabinMeasure(76f, CabinUnit.DP, "cabin_component_media_now_playing_transportMinSize")
            val gap: CabinMeasure = CabinMeasure(16f, CabinUnit.DP, "cabin_component_media_now_playing_gap")
            val padding: CabinMeasure = CabinMeasure(16f, CabinUnit.DP, "cabin_component_media_now_playing_padding")
            val cornerRadius: CabinMeasure = CabinMeasure(8f, CabinUnit.DP, "cabin_component_media_now_playing_cornerRadius")
        }
    }
}
