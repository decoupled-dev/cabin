plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}


android {
    namespace = "dev.decoupled.cabin.tokens"
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = false
    }
}

dependencies {
    testImplementation(libs.junit)
}

/**
 * Regenerate Kotlin + resources from the token stub.
 * Source of truth: tokens/cabin.tokens.json
 */
tasks.register<Exec>("generateCabinTokens") {
    group = "cabin"
    description = "Codegen Android + Compose + CSS tokens from tokens/cabin.tokens.json"
    workingDir = rootProject.projectDir
    commandLine("python3", "tools/generate_cabin_tokens.py")
}


tasks.register<Exec>("checkCabinTokenDrift") {
    group = "verification"
    description = "Fail if Android/Compose/CSS token outputs drift from cabin.tokens.json"
    workingDir = rootProject.projectDir
    commandLine("python3", "tools/generate_cabin_tokens.py", "--check")
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
