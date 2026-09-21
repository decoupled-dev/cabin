pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "cabin"

include(":cabin-tokens")
include(":cabin-compliance")
include(":cabin-foundation")
include(":cabin-views")
include(":cabin-compose")
include(":cabin-gauges")
// Sample only — never a cabin-* publish / SystemUI dependency.
include(":catalog")
