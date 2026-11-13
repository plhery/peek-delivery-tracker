// The app's macrobenchmarks (cold start, the list's scrolling) and the collector of its baseline profile, run on a
// device or emulator against the app's benchmark build types (android/README.md).
plugins {
    alias(libs.plugins.android.test)
}

android {
    namespace = "com.plhery.peekdeliverytracker.baselineprofile"
    compileSdk = 37

    defaultConfig {
        minSdk = 31
        targetSdk = 37
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // Tests the app's own benchmark build type; the same APK drives its unshrunk one.
        create("benchmark") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += "release"
        }
    }

    targetProjectPath = ":app"
    // The tests run in their own process, so the app can be stopped and started under them.
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

kotlin {
    jvmToolchain(21)
}

androidComponents {
    beforeVariants { it.enable = it.buildType == "benchmark" }
}

dependencies {
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.benchmark.macro.junit4)
    implementation(libs.uiautomator)
}
