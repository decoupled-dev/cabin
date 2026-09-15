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
     * Status bar deep link to informational detail
     * (`StatusDeepLink → informational` in restriction-states.md).
     */
    StatusDeepLinkInformational,
    /**
     * Status bar deep link to settings / setup
     * (`StatusDeepLink → settings / setup` in restriction-states.md).
     */
    StatusDeepLinkSettings,
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
