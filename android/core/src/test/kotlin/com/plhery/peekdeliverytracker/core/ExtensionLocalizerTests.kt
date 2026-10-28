package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/** ExtensionLocalizerTests.swift: the language the widget and share extension speak, and what they show for a missing key. */
class ExtensionLocalizerTests {
    private val available: Set<String> = setOf("en", "de", "fr", "it", "es", "pt", "pl")

    private fun language(saved: String? = null, preferred: List<String> = emptyList()): String =
        ExtensionLocalizer.languageCode(saved, preferred, available)

    @Test
    fun theLanguageSavedInTheAppWins() {
        assertEquals("pl", language(saved = "pl", preferred = listOf("fr-FR", "de-DE")))
        assertEquals("en", language(saved = "en", preferred = listOf("es-ES")))
    }

    @Test
    fun anUnsupportedSavedLanguageFallsThroughToTheDevice() {
        for (saved in listOf("xx", "", "garbage", "pl-PL", "zh")) {
            assertEquals(saved, "it", language(saved = saved, preferred = listOf("it-IT")))
            assertEquals(saved, "en", language(saved = saved))
        }
    }

    @Test
    fun deviceLanguagesMapToTheLanguagesPeekSpeaks() {
        assertEquals("es", language(preferred = listOf("es-ES")))
        assertEquals("es", language(preferred = listOf("es-419")))
        assertEquals("pt", language(preferred = listOf("pt-PT")))
        assertEquals("pt", language(preferred = listOf("pt-BR")))
        assertEquals("pl", language(preferred = listOf("pl-PL")))
        assertEquals("de", language(preferred = listOf("de")))
        assertEquals("fr", language(preferred = listOf("FR-ca")))
    }

    @Test
    fun theFirstDeviceLanguagePeekSpeaksWins() {
        assertEquals("pl", language(preferred = listOf("zh-Hans", "ja-JP", "pl-PL", "de-DE")))
    }

    @Test
    fun englishWhenNothingIsSupported() {
        assertEquals("en", language())
        assertEquals("en", language(preferred = listOf("zh-Hans", "ja-JP")))
        assertEquals("en", ExtensionLocalizer.languageCode(saved = "pl", preferred = listOf("pl-PL"), available = emptySet()))
    }

    @Test
    fun regionAndScriptVariantsAndOddValuesDoNotCrash() {
        for (preferred in listOf("zh-Hant-TW", "sr-Latn-RS", "zh-Hans-CN", "-", "--", "", "en_US", "x-private")) {
            language(preferred = listOf(preferred))
        }
        assertEquals("en", language(preferred = listOf("zh-Hant-TW", "sr-Latn-RS", "-", "")))
        assertEquals("it", language(preferred = listOf("zh-Hant-TW", "it-Latn-IT")))
    }

    @Test
    fun aMissingKeyFallsBackToEnglishThenToTheKey() {
        val dictionaries = mapOf(
            "en" to mapOf("shared" to "Shared", "englishOnly" to "English only"),
            "pl" to mapOf("shared" to "Wspólne"),
        )
        val polish = ExtensionLocalizer(languageCode = "pl", dictionaries = dictionaries)
        assertEquals("Wspólne", polish.text("shared"))
        assertEquals("English only", polish.text("englishOnly"))
        assertEquals("missing.key", polish.text("missing.key"))

        val unknown = ExtensionLocalizer(languageCode = "xx", dictionaries = dictionaries)
        assertEquals("Shared", unknown.text("shared"))
        assertEquals("shared", ExtensionLocalizer(languageCode = "pl", dictionaries = emptyMap()).text("shared"))
    }

    /** The device's languages are the app's to read, so a phone set to Swiss French, then English, stands in for them. */
    @Test
    fun theBundledCatalogDrivesTheChoice() {
        val bundled = ExtensionLocalizer.bundledDictionaries
        assertEquals(available, bundled.keys)

        val device = listOf("fr-CH", "en")
        val polish = ExtensionLocalizer(savedLanguageCode = "pl", preferredLanguages = device)
        assertEquals("pl", polish.languageCode)
        assertEquals(bundled["pl"]!!["shareExtension.title"]!!, polish.text("shareExtension.title"))
        assertNotEquals("shareExtension.title", polish.text("shareExtension.title"))

        val language = ExtensionLocalizer.languageCode(saved = null, preferred = device, available = available)
        assertEquals(language, ExtensionLocalizer(savedLanguageCode = null, preferredLanguages = device).languageCode)
        assertEquals(language, ExtensionLocalizer(savedLanguageCode = "garbage", preferredLanguages = device).languageCode)
    }

    /** Swift compares by Characters and canonical equivalence; these answers are the iPhone's, from its own code. */
    @Test
    fun languagesAndKeysAreComparedAsSwiftComparesThem() {
        assertEquals("en", language(preferred = listOf("fr-\u0301CA")))
        assertEquals("fr", language(preferred = listOf("-fr-CA")))
        assertEquals("en", language(saved = "p\u0301l"))
        val english = ExtensionLocalizer(languageCode = "en")
        assertEquals(english.text("add.carrierKnownElsewhere"), english.text("add.carrier\u212AnownElsewhere"))
        assertEquals("missing.\u212Aey", english.text("missing.\u212Aey"))
    }
}
