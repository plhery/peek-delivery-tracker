plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(21)
}

// The app reads the same generated data the iPhone app bundles: `npm run ios:resources` writes it from shared/,
// contracts/ and the scraper, and the daily scraper adoption keeps it current. Copying it at build time means every
// adoption reaches Android without a second copy in git.
val iosResources = rootDir.resolve("../ios/PeekDeliveryTracker/Resources")

val sharedResourcesDir = layout.buildDirectory.dir("generated/sharedResources/main")

val syncSharedResources by tasks.registering(Sync::class) {
    from(iosResources) {
        include(
            "Analytics.json",
            "Brand.json",
            "CarrierCatalog.json",
            "ContractFixtures.json",
            "DeliveryDemo.json",
            "FriendsDemo.json",
            "Localization.json",
            "PostcodeExamples.json",
            "Reading.json",
            "TrackingMessages.json",
            "World.json",
            "WorldDetail.pack",
        )
    }
    into(sharedResourcesDir.map { it.dir("peek") })
}

// The same files for :app's assets, as this build's output rather than a path into its build directory.
val sharedResources by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(sharedResources.name, sharedResourcesDir) { builtBy(syncSharedResources) }
}

val syncSharedTestResources by tasks.registering(Sync::class) {
    from(iosResources) {
        include("ChecksumVectors.json", "DetectionGolden.json")
    }
    into(layout.buildDirectory.dir("generated/sharedResources/test/peek"))
}

sourceSets {
    main {
        resources.srcDir(syncSharedResources.map { it.destinationDir.parentFile })
    }
    test {
        resources.srcDir(syncSharedTestResources.map { it.destinationDir.parentFile })
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    // A phone sorts and reads host names with ICU (android.icu), as iOS does; the JDK's own differ. With ICU4J on
    // the tests' class path, `Icu` takes it, so they see what a phone does.
    testImplementation(libs.icu4j)
}

tasks.test {
    // Dates are shown in the device's zone, as on the iPhone. CI runs in UTC; TEST_TIME_ZONE checks other zones.
    providers.environmentVariable("TEST_TIME_ZONE").orNull?.let { systemProperty("user.timezone", it) }
}
