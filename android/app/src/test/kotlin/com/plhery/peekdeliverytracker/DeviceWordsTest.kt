package com.plhery.peekdeliverytracker

import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * scripts/native-localization.mjs's rule for the iPad and the Mac, for Android: copy the app shows that names the
 * iPhone, an Apple app or Apple's names for the home screen needs a `.android` variant in every language, which the
 * app's Localizer reads instead. "Continue with Apple" stays: it is Apple's sign-in on Android too.
 */
class DeviceWordsTest {
    private val catalogs = ExtensionLocalizer.bundledDictionaries
    private val apple = Regex("""iPhon|iPad|\bMac(?:u|a)?\b|App Store|Safari|iCloud|Home Screen|Lock Screen|Dynamic Island|Live Activit""")

    // Copy the store keeps for what only an iPhone has, which Android never shows: Settings shows Live Activities'
    // message only where it offers them (`ParcelStore.offersLiveActivities`), and Android has none.
    private val iPhoneOnlyKeys = setOf("liveActivity.systemDisabled")

    /** The keys the Kotlin sources of the app and its core name, found as the iOS generator finds them in Swift. */
    private fun referencedKeys(): Set<String> {
        val module = File(System.getProperty("user.dir"))
        val sources = listOf(module.resolve("src/main/kotlin"), module.resolve("../core/src/main/kotlin"))
            .flatMap { directory -> directory.walk().filter { it.extension == "kt" }.toList() }
        assertTrue("No Kotlin sources under $module", sources.size > 10)
        val prefixes = catalogs.getValue("en").keys.map { it.substringBefore('.') }.toSet()
        val key = Regex(""""([a-z][A-Za-z0-9_-]*(?:\.[A-Za-z0-9_-]+)+)"""")
        return sources.flatMap { file -> key.findAll(file.readText()).map { it.groupValues[1] } }
            .filter { it.substringBefore('.') in prefixes }
            .toSet()
    }

    @Test
    fun everyKeyTheAppNamesIsInTheCatalog() {
        val missing = referencedKeys().filter { it !in catalogs.getValue("en") }
        assertEquals("Keys missing from Localization.json", emptyList<String>(), missing.sorted())
    }

    @Test
    fun copyNamingTheIPhoneHasAnAndroidVariantInEveryLanguage() {
        val keys = referencedKeys().filter { !it.endsWith(".android") && it !in iPhoneOnlyKeys }
        assertTrue(keys.contains("native.notificationsDenied"))
        val withoutVariant = keys
            .filter { key -> catalogs.values.any { catalog -> catalog[key]?.let(apple::containsMatchIn) == true } }
            .filter { key -> !catalogs.values.all { catalog -> catalog["$key.android"] != null } }
        assertEquals("Give these keys an .android variant", emptyList<String>(), withoutVariant.sorted())
    }

    @Test
    fun theAndroidVariantsNameNoAppleDevice() {
        for ((language, catalog) in catalogs) {
            for ((key, text) in catalog.filterKeys { it.endsWith(".android") }) {
                assertTrue("$language.$key: $text", !apple.containsMatchIn(text))
            }
        }
    }
}
