import org.jetbrains.kotlin.gradle.tasks.BaseKotlinCompile

// :core's JUnit tests, run on a device or emulator (connectedDebugAndroidTest) so they meet the phone's own ICU, regex,
// number parsing, normalizer and zone data rather than the JVM's. The tests are compiled from core/src/test, never
// copied; this module has no code of its own but the runner.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.plhery.peekdeliverytracker.coredevice"
    compileSdk = 37

    defaultConfig {
        minSdk = 31
        // Sets the zone given as `timeZone`, as TEST_TIME_ZONE does for :core:test.
        testInstrumentationRunner = "com.plhery.peekdeliverytracker.coredevice.CoreTestRunner"
    }

    // The tests run from API 31 up: lint fails on a test that calls a newer API.
    lint {
        checkTestSources = true
    }
}

kotlin {
    jvmToolchain(21)
}

// The iPhone app's test goldens, as :core syncs them for its own tests (see core/build.gradle.kts).
val coreTestGoldens = tasks.register<Sync>("coreTestGoldens") {
    dependsOn(":core:syncSharedTestResources")
    from(rootDir.resolve("core/build/generated/sharedResources/test"))
}

// :core's tests and their resources are the device tests' own.
androidComponents {
    onVariants { variant ->
        val tests = variant.androidTest?.sources ?: return@onVariants
        tests.kotlin?.addStaticSourceDirectory("../core/src/test/kotlin")
        tests.resources?.addStaticSourceDirectory("../core/src/test/resources")
        tests.resources?.addGeneratedSourceDirectory(coreTestGoldens) { it.destinationDirectory }
    }
}

// The tests reach :core's internal declarations, as its own test compilation does.
tasks.withType<BaseKotlinCompile>().configureEach {
    if (name.contains("AndroidTest")) {
        val core = rootDir.resolve("core")
        friendPaths.from(libraries.filter { it.startsWith(core) })
    }
}

dependencies {
    // Its main resources (peek/…) come along as Java resources, where SharedResources reads them.
    implementation(project(":core"))

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.serialization.json)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    // For the tests that check the core's Unicode data against ICU4J's; `Icu` still takes android.icu on a device.
    androidTestImplementation(libs.icu4j)
}
