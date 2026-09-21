package dev.decoupled.cabin.foundation

/**
 * Marks Experimental scaffold APIs generated from `components/cabin.components.yaml`.
 *
 * These APIs render, expose actions, and honor the Restriction Engine. Craft,
 * size-class layouts, and Stable contracts land in later passes. Do not treat
 * scaffold types as production-complete.
 */
@RequiresOptIn(
    message = "Experimental Cabin scaffold API. May change without notice.",
)
@Retention(AnnotationRetention.BINARY)
annotation class CabinScaffold
