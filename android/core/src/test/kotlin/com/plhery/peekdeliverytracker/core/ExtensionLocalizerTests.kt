package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/** ExtensionLocalizerTests.swift: the language the widget and share extension speak, and what they show for a missing key. */
class ExtensionLocalizerTests {
    private val available: Set<String> = setOf("en", "de", "fr", "it", "es", "pt", "pt-br", "pl", "ru", "zh")

    private fun language(saved: String? = null, preferred: List<String> = emptyList()): String =
        ExtensionLocalizer.languageCode(saved, preferred, available)

    @Test
    fun theLanguageSavedInTheAppWins() {
        assertEquals("pl", language(saved = "pl", preferred = listOf("fr-FR", "de-DE")))
        assertEquals("en", language(saved = "en", preferred = listOf("es-ES")))
    }

    @Test
    fun anUnsupportedSavedLanguageFallsThroughToTheDevice() {
        for (saved in listOf("xx", "", "garbage", "pl-PL", "pt-BR", "zh-Hant")) {
            assertEquals(saved, "it", language(saved = saved, preferred = listOf("it-IT")))
            assertEquals(saved, "en", language(saved = saved))
        }
    }

    @Test
    fun deviceLanguagesMapToTheLanguagesPeekSpeaks() {
        assertEquals("es", language(preferred = listOf("es-ES")))
        assertEquals("es", language(preferred = listOf("es-419")))
        assertEquals("pt", language(preferred = listOf("pt-PT")))
        assertEquals("pt-br", language(preferred = listOf("pt-BR")))
        assertEquals("pl", language(preferred = listOf("pl-PL")))
        assertEquals("ru", language(preferred = listOf("ru-RU")))
        assertEquals("zh", language(preferred = listOf("zh-Hans-CN")))
        assertEquals("de", language(preferred = listOf("de")))
        assertEquals("fr", language(preferred = listOf("FR-ca")))
    }

    /** Tags read as the web's `tagLocale` reads them: Brazil's Portuguese apart, and Chinese only in Simplified characters. */
    @Test
    fun tagsReadAsTheWebReadsThem() {
        val tags = mapOf(
            "pt-BR" to "pt-br", "pt_br" to "pt-br", "pt-PT" to "pt", "pt" to "pt",
            "zh-Hans-CN" to "zh", "zh-Hans" to "zh", "zh-CN" to "zh", "zh" to "zh", "zh-Hans-HK" to "zh",
            "zh-Hant-TW" to null, "zh-Hant" to null, "zh-TW" to null, "zh-HK" to null, "zh-MO" to null,
            "ru-RU" to "ru", "en-US" to "en", " DE-ch " to "de", "nl-NL" to "nl", "" to null, "-" to null,
        )
        for ((tag, expected) in tags) {
            assertEquals(tag, expected, ExtensionLocalizer.language(tag))
        }
    }

    /**
     * The tags a phone gives, as the iPhone's `language(tag:)` reads them on this Mac: a script decides Chinese before
     * a region does, and Traditional Chinese is no language of Peek's.
     */
    @Test
    fun phoneTagsReadAsTheIphoneReadsThem() {
        val tags = mapOf(
            "pt-BR" to "pt-br", "pt_BR" to "pt-br", "pt-PT" to "pt", "pt" to "pt", "pt--BR" to "pt-br",
            "zh" to "zh", "zh-CN" to "zh", "zh-Hans" to "zh", "zh-Hans-HK" to "zh", "zh-Hans-TW" to "zh",
            "zh-Hans_TW" to "zh", "zh-Latn-TW" to "zh", "ZH" to "zh",
            "zh-TW" to null, "zh-HK" to null, "zh-MO" to null, "zh-Hant" to null, "zh-Hant-CN" to null, "zh-hant" to null,
            "zh-HANT-TW" to null, "zh-TW-Hant" to null, " zh-tw\n" to null, "zh--TW" to null,
            "en-US" to "en", "x-private" to "x", "" to null, "-fr-CA" to null,
        )
        for ((tag, expected) in tags) {
            assertEquals(tag, expected, ExtensionLocalizer.language(tag))
        }
    }

    /** A phone set to Traditional Chinese goes on to its next language, never to Simplified Chinese. */
    @Test
    fun traditionalChineseFallsThroughToTheNextLanguage() {
        assertEquals("en", language(preferred = listOf("zh-TW", "en")))
        assertEquals("de", language(preferred = listOf("zh-HK", "de")))
        assertEquals("en", language(preferred = listOf("zh-Hant")))
        assertEquals("zh", language(preferred = listOf("zh-Hans-TW")))
    }

    @Test
    fun theFirstDeviceLanguagePeekSpeaksWins() {
        assertEquals("pl", language(preferred = listOf("zh-Hant", "ja-JP", "pl-PL", "de-DE")))
        assertEquals("zh", language(preferred = listOf("zh-TW", "nl-NL", "zh-CN", "de-DE")))
    }

    @Test
    fun englishWhenNothingIsSupported() {
        assertEquals("en", language())
        assertEquals("en", language(preferred = listOf("zh-Hant", "zh-HK", "ja-JP", "nl-NL")))
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
    fun datesAndNumbersTakeTheLanguagesOwnLocale() {
        val locales = mapOf("pt" to "pt-PT", "pt-br" to "pt-BR", "zh" to "zh-Hans", "ru" to "ru", "de" to "de", "en" to "en")
        for ((language, identifier) in locales) {
            assertEquals(language, identifier, ExtensionLocalizer.locale(language).toLanguageTag())
        }
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

    /**
     * Swift compares by Characters and canonical equivalence; these answers are the iPhone's, from its own code. A tag
     * keeps its empty subtags, so "-fr-CA" names no language.
     */
    @Test
    fun languagesAndKeysAreComparedAsSwiftComparesThem() {
        assertEquals("en", language(preferred = listOf("fr-\u0301CA")))
        assertEquals("en", language(preferred = listOf("-fr-CA")))
        assertEquals("en", language(saved = "p\u0301l"))
        val tags = mapOf(
            "fr-\u0301CA" to "fr-\u0301ca", "fr_\u0301CA" to "fr_\u0301ca", "zh_\u0301TW" to "zh_\u0301tw",
            "pt-\u0301BR" to "pt-\u0301br", "pt-BR\u0301" to "pt", "pt\u0301-BR" to "pt\u0301", "\u0301pt" to "\u0301pt",
            "zh-Hant\u0301" to "zh", "zh-T\u0301W" to "zh", "zh-tw\u200D" to "zh", "zh-\uFF34\uFF37" to "zh", "zh\u0000-TW" to "zh\u0000",
        )
        for ((tag, expected) in tags) {
            assertEquals(tag, expected, ExtensionLocalizer.language(tag))
        }
        val english = ExtensionLocalizer(languageCode = "en")
        assertEquals(english.text("add.carrierKnownElsewhere"), english.text("add.carrier\u212AnownElsewhere"))
        assertEquals("missing.\u212Aey", english.text("missing.\u212Aey"))
    }
}
