plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

/** Publish all cabin-* AARs to ~/.m2 (not catalog). */
tasks.register("publishCabinToMavenLocal") {
    group = "publishing"
    val cabinVersion = providers.gradleProperty("cabin.version").orElse("0.1.0")
    description =
        "Publish cabin-tokens/compliance/views/compose to Maven Local as ${cabinVersion.get()}"
    dependsOn(
        ":cabin-tokens:publishReleasePublicationToMavenLocal",
        ":cabin-compliance:publishReleasePublicationToMavenLocal",
        ":cabin-views:publishReleasePublicationToMavenLocal",
        ":cabin-compose:publishReleasePublicationToMavenLocal",
    )
}
