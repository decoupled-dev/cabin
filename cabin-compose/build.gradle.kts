plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    alias(libs.plugins.kotlin.compose)
}


android {
    namespace = "dev.decoupled.cabin.compose"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Compose System/Status bars: tokens + Restriction Engine only (no cabin-views).
    api(project(":cabin-tokens"))
    api(project(":cabin-compliance"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.annotation)

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.compose.ui.test.manifest)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = providers.gradleProperty("cabin.groupId").orElse("dev.decoupled.cabin").get()
                artifactId = project.name
                version = providers.gradleProperty("cabin.version").orElse("0.1.0").get()
                pom {
                    name.set(project.name)
                    description.set("Cabin AAOS design language — ${project.name} (Alpha)")
                    url.set("https://github.com/decoupled-dev/cabin")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    scm {
                        url.set("https://github.com/decoupled-dev/cabin")
                        connection.set("scm:git:https://github.com/decoupled-dev/cabin.git")
                        developerConnection.set(
                            "scm:git:ssh://git@github.com/decoupled-dev/cabin.git",
                        )
                    }
                }
            }
        }
    }
}
