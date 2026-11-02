plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.roborazzi)
}

// The iPhone build's configuration: ios/Configuration/Shared.xcconfig, then a developer's own Local.xcconfig (git
// ignores it), so both apps talk to the same server with the same switches. Only public client settings live there.
val xcconfig: Map<String, String> = listOf("Shared.xcconfig", "Local.xcconfig")
    .flatMap { name ->
        val file = rootProject.layout.projectDirectory.file("../ios/Configuration/$name")
        providers.fileContents(file).asText.orNull.orEmpty().lines()
    }
    .map { it.trim() }
    .filter { '=' in it && !it.startsWith("//") && !it.startsWith("#") }
    // `$()` is empty, which keeps `//` in a URL from starting a comment.
    .associate { it.substringBefore('=').trim() to it.substringAfter('=').substringBefore("//").trim().replace("$()", "") }

/** The settings AppConfiguration reads, by their Info.plist keys (ios/PeekDeliveryTracker/Info.plist). */
val iosSettings = mapOf(
    "SDTUseAPI" to "SDT_USE_API",
    "SDTAPIBaseURL" to "SDT_API_BASE_URL",
    "SDTSupabaseURL" to "SDT_SUPABASE_URL",
    "SDTSupabasePublishableKey" to "SDT_SUPABASE_PUBLISHABLE_KEY",
    "SDTGoogleAuthEnabled" to "SDT_GOOGLE_AUTH_ENABLED",
    "SDTAppleAuthEnabled" to "SDT_APPLE_AUTH_ENABLED",
    "SDTEmailOTPEnabled" to "SDT_EMAIL_OTP_ENABLED",
    "SDTAppGroupIdentifier" to "SDT_APP_GROUP_IDENTIFIER",
    "SDTLinkHosts" to "SDT_LINK_HOSTS",
    "SDTOIDCProvider" to "SDT_OIDC_PROVIDER",
    "SDTOIDCName" to "SDT_OIDC_NAME",
)

android {
    namespace = "com.plhery.peekdeliverytracker"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.plhery.peekdeliverytracker"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
        // What only a device can show (src/androidTest): the WebView's own engine behind the browser check.
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // The iPhone build's settings, under the names its Info.plist gives them (AppConfiguration.from).
        for ((key, setting) in iosSettings) {
            val value = xcconfig[setting].orEmpty().replace("\\", "\\\\").replace("\"", "\\\"")
            buildConfigField("String", key, "\"$value\"")
        }
        // The hosts whose /p/ links open the parcel's sheet, as the iPhone's associated domains name them.
        manifestPlaceholders["linkHost"] = xcconfig["SDT_ASSOCIATED_DOMAIN"].orEmpty().removePrefix("applinks:")
        manifestPlaceholders["linkHostLegacy"] = xcconfig["SDT_ASSOCIATED_DOMAIN_LEGACY"].orEmpty().removePrefix("applinks:")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    androidResources {
        // The map's detail pack is mapped into memory as it is (WorldDetailPacks), which a compressed asset can't be.
        noCompress += "pack"
    }

    packaging {
        resources {
            // :core's copy of the shared data; the app reads the assets below instead.
            excludes += "peek/**"
        }
    }

    sourceSets {
        // The stores in memory, the demo's parcels and the root host the JVM tests draw on, which the device tests
        // draw the same screens with (src/sharedTest).
        for (tests in listOf("test", "androidTest")) {
            named(tests) { kotlin.directories += "src/sharedTest/kotlin" }
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
            // Every test runs in one JVM, and an iPad-sized screenshot compares images of 20 MB and more: Gradle's
            // default 512 MB runs out.
            it.maxHeapSize = "2g"
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

/**
 * The words Android shows for the app outside it, as resources written at build time from the shared copy
 * (Localization.json) in each language the app speaks, so that a change of copy reaches them as it reaches the
 * iPhone's InfoPlist.strings: [strings] names each resource and its key, [qualifiers] each language's resource folder,
 * English the default one, and the task fails for a language of the copy without one. Nothing of it is committed.
 */
open class SystemStrings @Inject constructor(objects: ObjectFactory) : DefaultTask() {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val sharedResources: ConfigurableFileCollection = objects.fileCollection()

    @get:Input
    val strings: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java)

    @get:Input
    val qualifiers: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java)

    @get:OutputDirectory
    val outputDirectory: DirectoryProperty = objects.directoryProperty()

    @TaskAction
    fun write() {
        val file = sharedResources.asFileTree.matching { include("peek/Localization.json") }.singleFile
        @Suppress("UNCHECKED_CAST")
        val copy = groovy.json.JsonSlurper().parse(file, "UTF-8") as Map<String, Map<String, String>>
        // A language the copy gains needs its folder here, or the app would name itself in English in it.
        val unplaced = copy.keys - qualifiers.get().keys
        if (unplaced.isNotEmpty()) throw GradleException("systemStrings has no resource folder for ${unplaced.joinToString()} in Localization.json")
        val output = outputDirectory.get().asFile
        output.deleteRecursively()
        for ((language, qualifier) in qualifiers.get()) {
            val values = copy[language] ?: throw GradleException("Localization.json has no $language")
            val folder = output.resolve(if (qualifier.isEmpty()) "values" else "values-$qualifier").apply { mkdirs() }
            val lines = strings.get().map { (name, key) ->
                val text = values[key] ?: copy.getValue("en")[key] ?: throw GradleException("Localization.json has no $key")
                "    <string name=\"$name\">${resourceText(text)}</string>"
            }
            folder.resolve("system_strings.xml").writeText(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n${lines.joinToString("\n")}\n</resources>\n",
            )
        }
    }

    /** `text` as a string resource holds it: aapt's escapes, then XML's. */
    private fun resourceText(text: String): String {
        val escaped = text.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("\n", "\\n")
            .let { if (it.startsWith("@") || it.startsWith("?")) "\\$it" else it }
        return escaped.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
    }
}

val systemStrings = tasks.register<SystemStrings>("systemStrings") {
    sharedResources.from(coreSharedResources)
    // The share target's name, `CFBundleDisplayName` of the iPhone's ShareExtension.
    strings.put("share_title", "shareExtension.title")
    // The widget's name and description in the widget gallery (`configurationDisplayName`, `description`).
    strings.put("widget_gallery_name", "widget.galleryName")
    strings.put("widget_gallery_description", "widget.galleryDescription")
    // Localization.json's languages, as the iPhone's .lproj folders name them.
    qualifiers.putAll(
        mapOf(
            "en" to "", "de" to "de", "fr" to "fr", "it" to "it", "es" to "es", "pt" to "pt", "pt-br" to "pt-rBR",
            "pl" to "pl", "ru" to "ru", "zh" to "b+zh+Hans",
        ),
    )
    outputDirectory.set(layout.buildDirectory.dir("generated/systemStrings"))
}

/**
 * The widget gallery's sample, which the iPhone's tests draw into its widget's folder (WidgetSample): the demo's next
 * deliveries in each language, and their cards' pictures. The widget reads it from the assets ([widgetSampleAssets]);
 * the preview layout Android shows before 15, which can only name resources, takes the first card as resources: its
 * pictures light and dark, its words in each of [qualifiers]' languages and its colours. Nothing of it is committed.
 */
open class WidgetSampleResources @Inject constructor(objects: ObjectFactory) : DefaultTask() {
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val sample: DirectoryProperty = objects.directoryProperty()

    @get:Input
    val qualifiers: MapProperty<String, String> = objects.mapProperty(String::class.java, String::class.java)

    @get:OutputDirectory
    val outputDirectory: DirectoryProperty = objects.directoryProperty()

    @TaskAction
    fun write() {
        val folder = sample.get().asFile
        @Suppress("UNCHECKED_CAST")
        val snapshots = groovy.json.JsonSlurper().parse(folder.resolve("snapshots.json"), "UTF-8") as Map<String, Map<String, Any>>
        val output = outputDirectory.get().asFile
        output.deleteRecursively()
        fun first(language: String): Map<String, Any> {
            @Suppress("UNCHECKED_CAST")
            val parcels = (snapshots[language] ?: snapshots.getValue("en"))["parcels"] as List<Map<String, Any>>
            return parcels.first()
        }
        for ((language, qualifier) in qualifiers.get()) {
            val parcel = first(language)
            @Suppress("UNCHECKED_CAST")
            val card = parcel["card"] as Map<String, Any>
            val detail = parcel["detail"] as String
            val status = card["status"] as String
            val strings = mapOf(
                "widget_sample_label" to parcel["label"] as String,
                "widget_sample_carrier" to parcel["carrier"] as String,
                // `DeliveryWidgetCardView`'s status line.
                "widget_sample_status" to if (status == detail) status else "$status · $detail",
            )
            val values = output.resolve(if (qualifier.isEmpty()) "values" else "values-$qualifier").apply { mkdirs() }
            values.resolve("widget_sample.xml").writeText(resources(strings.map { (name, text) -> "    <string name=\"$name\">${resourceText(text)}</string>" }))
        }
        @Suppress("UNCHECKED_CAST")
        val card = first("en")["card"] as Map<String, Any>
        @Suppress("UNCHECKED_CAST")
        fun pair(name: String) = card[name] as List<String>
        for ((index, mode) in listOf("", "-night").withIndex()) {
            val colors = mapOf("widget_sample_surface" to pair("surface")[index], "widget_sample_ink" to pair("ink")[index],
                "widget_sample_primary" to if (index == 0) "#000000" else "#FFFFFF")
            val values = output.resolve("values$mode").apply { mkdirs() }
            values.resolve("widget_sample_colors.xml").writeText(resources(colors.map { (name, hex) -> "    <color name=\"$name\">$hex</color>" }))
            val drawables = output.resolve("drawable$mode-nodpi").apply { mkdirs() }
            val look = if (index == 0) "light" else "dark"
            folder.resolve("sample-0-small-$look.png").copyTo(drawables.resolve("widget_sample_route.png"))
            folder.resolve("sample-0-mark-$look.png").copyTo(drawables.resolve("widget_sample_mark.png"))
        }
    }

    private fun resources(lines: List<String>) =
        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n${lines.joinToString("\n")}\n</resources>\n"

    /** `text` as a string resource holds it: aapt's escapes, then XML's. */
    private fun resourceText(text: String): String {
        val escaped = text.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("\n", "\\n")
            .let { if (it.startsWith("@") || it.startsWith("?")) "\\$it" else it }
        return escaped.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
    }
}

val widgetSample: Directory = rootProject.layout.projectDirectory.dir("../ios/DeliveryWidgetExtension/WidgetSample")

val widgetSampleResources = tasks.register<WidgetSampleResources>("widgetSampleResources") {
    sample.set(widgetSample)
    qualifiers.set(systemStrings.flatMap { it.qualifiers })
    outputDirectory.set(layout.buildDirectory.dir("generated/widgetSample/res"))
}

val widgetSampleAssets = tasks.register<Sync>("widgetSampleAssets") {
    from(widgetSample) {
        into("WidgetSample")
    }
}

androidComponents {
    onVariants { variant ->
        variant.sources.res?.addGeneratedSourceDirectory(systemStrings) { it.outputDirectory }
        variant.sources.res?.addGeneratedSourceDirectory(widgetSampleResources) { it.outputDirectory }
        variant.sources.assets?.addGeneratedSourceDirectory(widgetSampleAssets) { it.destinationDirectory }
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
    // URLSession's stand-in: the API, sign-in, catalog and link requests (platform/OkHttpTransport.kt).
    implementation(libs.okhttp)
    // The deliveries' stack, the list and a parcel's page beside it or over it, drawn from their navigation
    // (DeliveriesTab.kt).
    implementation(libs.navigation3.runtime)
    implementation(libs.navigation3.ui)
    // A foldable's hinge, which the tab bar keeps clear of in book posture (FoldingFeature).
    implementation(libs.window)
    // StoreKit's `requestReview`: the rating prompt, through Play In-App Review (platform/PlayReviews.kt).
    implementation(libs.play.review.ktx)
    // WKWebView's script message handler for the browser check: WebViewCompat's message listener and document-start
    // script (ui/TurnstileSheet.kt, platform/WebViewTurnstilePages.kt).
    implementation(libs.webkit)
    // ASWebAuthenticationSession's stand-in: web sign-in in a Custom Tab, which comes back through the app's own
    // address (platform/CustomTabSignIn.kt).
    implementation(libs.browser)
    // DataScannerViewController's stand-in for the label scanner: CameraX's preview, analysis and zoom, and ML Kit's
    // barcode model bundled in the app, read on the device with no account, key or download (platform/CameraLabelScanner.kt).
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.camera.mlkit.vision)
    implementation(libs.mlkit.barcode.scanning)
    // WidgetKit's stand-in: the home screen widget, its RemoteViews written from Compose-like code (widget/).
    implementation(libs.glance.appwidget)
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
    testImplementation(libs.mockwebserver)
    testImplementation(libs.window.testing)
    // The widget's content as Glance composes it, without a launcher (runGlanceAppWidgetUnitTest).
    testImplementation(libs.glance.appwidget.testing)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    // The label scanner's cover over this device's camera, found and tapped by its semantics.
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    // Compose's test rule idles through Espresso, whose own 3.5 no longer finds Android 17's input manager.
    androidTestImplementation(libs.espresso.core)
}
