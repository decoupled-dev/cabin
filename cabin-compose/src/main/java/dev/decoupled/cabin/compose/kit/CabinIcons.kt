/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.decoupled.cabin.compose.kit

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/**
 * Cabin's line icon set: 24 × 24 grid, 2 dp round strokes, drawn in the
 * content color. Self-contained so the kit doesn't pull in the large
 * material-icons artifact; hosts can pass any [ImageVector] instead.
 */
object CabinIcons {
    val Home: ImageVector by lazy { line("Home", "M3 11l9-7 9 7v9a1 1 0 0 1-1 1h-5v-6h-6v6H4a1 1 0 0 1-1-1z") }
    val Navigation: ImageVector by lazy { line("Navigation", "M12 2l7 19-7-4-7 4z") }
    val Music: ImageVector by lazy {
        line("Music", "M9 18V5l11-2v13M9 18a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM20 16a3 3 0 1 1-6 0 3 3 0 0 1 6 0z")
    }
    val Phone: ImageVector by lazy {
        line("Phone", "M5 3h4l2 5-3 2a12 12 0 0 0 6 6l2-3 5 2v4a2 2 0 0 1-2 2A17 17 0 0 1 3 5a2 2 0 0 1 2-2z")
    }
    val Apps: ImageVector by lazy { line("Apps", "M4 4h6v6H4zM14 4h6v6h-6zM4 14h6v6H4zM14 14h6v6h-6z") }
    val Fan: ImageVector by lazy { line("Fan", "M3 8h11a3 3 0 1 0-3-3M3 12h16a3 3 0 1 1-3 3M3 16h8") }
    val SeatHeat: ImageVector by lazy {
        line("SeatHeat", "M7 3c-2 2 2 4 0 6s2 4 0 6M12 3c-2 2 2 4 0 6s2 4 0 6M17 3c-2 2 2 4 0 6s2 4 0 6M4 21h16")
    }
    val Lock: ImageVector by lazy { line("Lock", "M5 11h14v10H5zM8 11V8a4 4 0 0 1 8 0v3") }
    val Defrost: ImageVector by lazy {
        line("Defrost", "M3 18c3-9 15-9 18 0zM9 8c-1-1.5 1-2.5 0-4M12 8c-1-1.5 1-2.5 0-4M15 8c-1-1.5 1-2.5 0-4")
    }
    val RearDefrost: ImageVector by lazy { line("RearDefrost", "M4 6h16v12H4zM8 10h8M8 14h8") }
    val Lights: ImageVector by lazy {
        line("Lights", "M14 5c4 0 7 3 7 7s-3 7-7 7c-1.5 0-2.5-3-2.5-7s1-7 2.5-7zM2 8h7M2 12h7M2 16h7")
    }
    val DriveMode: ImageVector by lazy { line("DriveMode", "M3.5 18a9 9 0 1 1 17 0M12 14l4-4") }
    val TurnRight: ImageVector by lazy { line("TurnRight", "M6 21v-7a4 4 0 0 1 4-4h9M15 6l4 4-4 4", width = 2.4f) }
    val TurnLeft: ImageVector by lazy { line("TurnLeft", "M18 21v-7a4 4 0 0 0-4-4H5M9 6l-4 4 4 4", width = 2.4f) }
    val Straight: ImageVector by lazy { line("Straight", "M12 21V4M7 9l5-5 5 5", width = 2.4f) }
    val Arrive: ImageVector by lazy { line("Arrive", "M12 21s-7-6.2-7-12a7 7 0 0 1 14 0c0 5.8-7 12-7 12zM12 7a2 2 0 1 0 0 4 2 2 0 0 0 0-4z") }
    val Minus: ImageVector by lazy { line("Minus", "M5 12h14", width = 2.4f) }
    val Plus: ImageVector by lazy { line("Plus", "M5 12h14M12 5v14", width = 2.4f) }
    val Previous: ImageVector by lazy { filled("Previous", "M19 5L9 12l10 7zM5 5v14") }
    val Next: ImageVector by lazy { filled("Next", "M5 5l10 7-10 7zM19 5v14") }
    val Pause: ImageVector by lazy { line("Pause", "M8 5v14M16 5v14", width = 4f) }
    val Play: ImageVector by lazy { filled("Play", "M7 4l13 8-13 8z") }
    val Sun: ImageVector by lazy {
        line(
            "Sun",
            "M12 8a4 4 0 1 0 0 8 4 4 0 0 0 0-8zM12 2v2M12 20v2M4.9 4.9l1.4 1.4M17.7 17.7l1.4 1.4" +
                "M2 12h2M20 12h2M4.9 19.1l1.4-1.4M17.7 6.3l1.4-1.4",
        )
    }
    val Bluetooth: ImageVector by lazy { line("Bluetooth", "M7 7l10 10-5 5V2l5 5L7 17") }
    val Signal: ImageVector by lazy { line("Signal", "M4 20v-3M9 20v-7M14 20v-11M19 20V4", width = 2.4f) }
    val Bolt: ImageVector by lazy { line("Bolt", "M13 2L4 14h7l-1 8 9-12h-7z") }
    val Mic: ImageVector by lazy { line("Mic", "M9 3h6v10H9zM5 11a7 7 0 0 0 14 0M12 18v3") }
    val AirConditioning: ImageVector by lazy { line("AirConditioning", "M12 2v20M4 6.5l16 11M20 6.5l-16 11") }
    val Recirculation: ImageVector by lazy {
        line("Recirculation", "M4 12a8 8 0 0 1 14-5.3M20 12a8 8 0 0 1-14 5.3M18 3v4h-4M6 21v-4h4")
    }
    val Sync: ImageVector by lazy { line("Sync", "M4 9h13l-3-3M20 15H7l3 3") }
    val Queue: ImageVector by lazy { line("Queue", "M4 6h16M4 12h16M4 18h10") }
    val Source: ImageVector by lazy { line("Source", "M5 4h14v16H5zM9 8h6M9 12h6M9 16h3") }
    val Car: ImageVector by lazy { line("Car", "M5 11l2-5h10l2 5M3 11h18v6H3zM6 17v2M18 17v2") }

    private fun line(name: String, path: String, width: Float = 2f): ImageVector =
        ImageVector.Builder(
            name = "Cabin.$name",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).addPath(
            pathData = addPathNodes(path),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = width,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        ).build()

    private fun filled(name: String, path: String): ImageVector =
        ImageVector.Builder(
            name = "Cabin.$name",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).addPath(
            pathData = addPathNodes(path),
            fill = SolidColor(Color.Black),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        ).build()
}
