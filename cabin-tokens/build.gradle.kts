plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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
