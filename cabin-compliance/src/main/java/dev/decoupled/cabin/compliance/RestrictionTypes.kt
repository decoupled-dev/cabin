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

package dev.decoupled.cabin.compliance

/**
 * Vehicle UI modes for the Restriction Engine (MVP).
 *
 * See docs/compliance/restriction-states.md.
 */
enum class CabinUiMode {
    Parked,
    Idling,
    Moving,
    Restricted,
    /** Fail-safe: treat as Restricted for complex chrome entry points. */
    Unknown,
}

/**
 * Interaction categories declared by chrome / components.
 *
 * Widgets declare these; [CabinCompliance] returns allow / substitute / block.
 */
enum class CabinInteraction {
    Glance,
    NavigateSimple,
    OpenComplexApp,
    OpenKeyboard,
    FilterOrSort,
    MediaTransport,
    HvacPeek,
    /**
     * Single-tap climate controls: temperature step, fan level, seat heat /
     * vent level, defrost, A/C, recirculation. Allowed in every mode — these
     * are glanceable, single-step tasks, and defrost must never be blocked.
     */
    HvacQuick,
    /**
     * Multi-step climate setup (zone sync setup, schedules, preconditioning,
     * the v0.1 ClimateTile). Fail-closed while Moving / Restricted / Unknown.
     * Single-tap controls use [HvacQuick].
     */
    HvacAdjust,
    /**
     * Complex media interactions from now-playing: source picker, scrub/seek,
     * queue / browse entry. Transport remains [MediaTransport].
     */
    MediaComplex,
    /**
     * Status bar deep link to informational detail
     * (`StatusDeepLink → informational` in restriction-states.md).
     */
    StatusDeepLinkInformational,
    /**
     * Status bar deep link to settings / setup
     * (`StatusDeepLink → settings / setup` in restriction-states.md).
     */
    StatusDeepLinkSettings,
    /**
     * Parked-only content (lyrics, video, EULA, 3D vehicle). Fail-closed unless Parked.
     */
    ParkedOnly,
    /**
     * Body / vehicle adjustments (locks, windows, drive mode). Same matrix as HvacAdjust.
     */
    VehicleAdjust,
}

/** Gate result for a declared interaction under the current UI mode. */
enum class GateDisposition {
    Allow,
    Substitute,
    Block,
}

/**
 * UX restriction flags adapted from platform signals (e.g. CarUxRestrictions).
 * Cabin does not own vehicle services — adapters populate these.
 */
enum class UxRestriction {
    NoSetup,
    NoFiltering,
    NoKeyboard,
    NoVideo,
    LimitStringLength,
    /** Explicit distraction-optimization / UX restriction profile. */
    DistractionOptimized,
}
