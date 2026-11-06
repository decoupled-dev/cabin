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

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.binary.compatibility.validator)
}

// ---------------------------------------------------------------- quality
// Rules and rationale: docs/quality.md. One entry point: tools/quality.sh.

/** Published library modules (samples are excluded from API and static gates). */
val libraryModules = setOf(
    "cabin-tokens", "cabin-compliance", "cabin-foundation", "cabin-views",
    "cabin-compose", "cabin-screens", "cabin-gauges",
)

apiValidation {
    // Public API dumps live in <module>/api/<module>.api (Cabin's api/current.txt).
    ignoredProjects.addAll(listOf("catalog", "kitchen-sink"))
}

subprojects {
    if (name in libraryModules) {
        apply(plugin = "io.gitlab.arturbosch.detekt")
        extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
            buildUponDefaultConfig = true
            config.setFrom(rootProject.file("config/detekt/detekt.yml"))
            baseline = file("detekt-baseline.xml")
            parallel = true
        }
        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            exclude("**/*.generated.kt", "**/build/**")
            jvmTarget = "17"
            reports {
                html.required.set(true)
                sarif.required.set(true)
                xml.required.set(false)
                txt.required.set(false)
                md.required.set(false)
            }
        }
        tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
            exclude("**/*.generated.kt", "**/build/**")
            jvmTarget = "17"
        }
        dependencies {
            add("detektPlugins", rootProject.libs.detekt.formatting)
        }
    }

    // Android Lint: same config for every Android module; existing issues live in
    // <module>/lint-baseline.xml so only new issues fail.
    plugins.withId("com.android.library") {
        extensions.configure<com.android.build.api.dsl.LibraryExtension> {
            lint {
                lintConfig = rootProject.file("config/lint/lint.xml")
                baseline = file("lint-baseline.xml")
                abortOnError = true
                warningsAsErrors = false
                checkDependencies = false
                sarifReport = true
                htmlReport = true
                xmlReport = false
            }
        }
    }
    plugins.withId("com.android.application") {
        extensions.configure<com.android.build.api.dsl.ApplicationExtension> {
            lint {
                lintConfig = rootProject.file("config/lint/lint.xml")
                baseline = file("lint-baseline.xml")
                abortOnError = true
                checkReleaseBuilds = false
                sarifReport = true
                htmlReport = true
                xmlReport = false
            }
        }
    }
}
