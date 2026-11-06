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
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.decoupled.cabin.foundation"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(project(":cabin-tokens"))
    api(project(":cabin-compliance"))
    implementation(libs.androidx.annotation)

    testImplementation(libs.junit)
}

tasks.register<Exec>("generateCabinComponents") {
    group = "cabin"
    description = "Codegen Experimental component scaffolds from components/cabin.components.yaml"
    workingDir = rootProject.projectDir
    commandLine("python3", "tools/generate_cabin_components.py")
}

tasks.register<Exec>("checkCabinComponentDrift") {
    group = "verification"
    description = "Fail if generated component scaffolds drift from cabin.components.yaml"
    workingDir = rootProject.projectDir
    commandLine("python3", "tools/generate_cabin_components.py", "--check")
}
