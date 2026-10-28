plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.plhery.peekdeliverytracker"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.plhery.peekdeliverytracker"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            // :core's copy of the shared data; the app reads the assets below instead.
            excludes += "peek/**"
        }
    }

    testOptions {
        // Robolectric reads the app's manifest, resources and assets.
        unitTests.isIncludeAndroidResources = true
        unitTests.all {
            // Robolectric's newest Android sets up its shared memory through FileDescriptor's internals.
            it.jvmArgs("--add-exports=java.base/jdk.internal.access=ALL-UNNAMED", "--add-opens=java.base/java.io=ALL-UNNAMED")
            // Screenshots draw through Android's own renderer (Roborazzi).
            it.systemProperty("robolectric.pixelCopyRenderMode", "hardware")
        }
    }
}

kotlin {
    jvmToolchain(21)
}

roborazzi {
    // The baselines, recorded with recordRoborazziDebug and checked with verifyRoborazziDebug.
    outputDir.set(file("src/test/snapshots"))
}

// The data the iPhone app bundles, as assets under peek/: the files :core syncs from ios/PeekDeliveryTracker/Resources
// (see core/build.gradle.kts) and its committed country names. SharedResources reads them through the AssetManager.
val coreSharedResources by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

val sharedAssets = tasks.register<Sync>("sharedAssets") {
    from(coreSharedResources) {
        exclude("peek/ContractFixtures.json")
    }
    from(rootDir.resolve("core/src/main/resources")) {
        include("peek/CountryNames.json")
    }
}

// The contract fixtures feed only the debug previews (ParcelLinkStore.debugPreview), so only debug builds carry them.
val debugSharedAssets = tasks.register<Sync>("debugSharedAssets") {
    from(coreSharedResources) {
        include("peek/ContractFixtures.json")
    }
}

androidComponents {
    onVariants { variant ->
        variant.sources.assets?.addGeneratedSourceDirectory(sharedAssets) { it.destinationDirectory }
        if (variant.buildType == "debug") {
            variant.sources.assets?.addGeneratedSourceDirectory(debugSharedAssets) { it.destinationDirectory }
        }
    }
}

dependencies {
    implementation(project(":core"))
    coreSharedResources(project(path = ":core", configuration = "sharedResources"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.core.splashscreen)
    implementation(libs.lifecycle.runtime.compose)
    // The iPhone's materials: a blur of what lies behind (Materials.kt).
    implementation(libs.haze)
    implementation(libs.haze.blur)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(platform(libs.compose.bom))
    testImplementation(libs.compose.ui.test.junit4)
    // Compose's test rule idles through Espresso; the one it brings looks for an InputManager newer Androids lack.
    testImplementation(libs.espresso.core)
    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.junit.rule)
}
