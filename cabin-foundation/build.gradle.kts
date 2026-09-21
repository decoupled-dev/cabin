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
