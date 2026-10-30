package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.util.Locale

// ExtensionLocalizer.swift. On Android the share target is an activity of the app itself, which could reach its
// Localizer; this keeps the iPhone's extension copy as it is: one language, a missing key in English, then the key.

/**
 * The app's copy in its extensions, which can't reach the app's Localizer. Each extension bundles its own
 * Localization.json; a key missing in one language falls back to English.
 */
class ExtensionLocalizer(
    val languageCode: String,
    dictionaries: Map<String, Map<String, String>> = bundledDictionaries,
) {
    // Swift's Dictionary finds a key by canonical equivalence: "add.carrierKnownElsewhere" with a Kelvin sign for its K.
    private val dictionaries: Map<String, Map<String, String>> =
        dictionaries.entries.associate { (language, values) -> language.nfc() to values.mapKeys { it.key.nfc() } }

    /** `init(savedLanguageCode:)`, with the device's languages (`Locale.preferredLanguages`) given. */
    constructor(savedLanguageCode: String?, preferredLanguages: List<String>) :
        this(languageCode(savedLanguageCode, preferredLanguages, bundledDictionaries.keys))

    fun text(key: String): String {
        val canonical = key.nfc()
        return dictionaries[languageCode.nfc()]?.get(canonical)
            ?: dictionaries["en"]?.get(canonical)
            ?: key
    }

    companion object {
        val bundledDictionaries: Map<String, Map<String, String>> by lazy {
            runCatching {
                Json.decodeFromString(
                    MapSerializer(String.serializer(), MapSerializer(String.serializer(), String.serializer())),
                    SharedResources.text("Localization.json"),
                )
            }.getOrElse { emptyMap() }
        }

        /**
         * The language saved by the app, else the first of the device's languages Peek speaks ([language]), else
         * English.
         */
        fun languageCode(saved: String?, preferred: List<String>, available: Set<String>): String {
            val available = available.mapTo(HashSet()) { it.nfc() }
            return (listOfNotNull(saved) + preferred.mapNotNull(::language)).firstOrNull { it.nfc() in available } ?: "en"
        }

        /**
         * `language(tag:)`: the language a device's language tag names, as Peek's languages are named and as the web
         * reads a tag (`tagLocale`): Portuguese from Brazil is `pt-br` and any other `pt`; Chinese is `zh`, Simplified
         * Chinese, unless it is written in Traditional characters (`Hant`, or no script and Taiwan, Hong Kong or
         * Macau), which Peek doesn't speak; any other language is its first subtag, which Peek may not speak either.
         * The tag is trimmed, lowered and split on Characters, as Swift splits it: an underscore or a hyphen carrying
         * a combining mark is neither, and an empty subtag counts ("-fr" names no language).
         */
        fun language(tag: String): String? {
            val subtags = mutableListOf(StringBuilder())
            for (character in tag.trimmingWhitespacesAndNewlines().lowercased().characters()) {
                if (character == "-" || character == "_") subtags += StringBuilder() else subtags.last().append(character)
            }
            val language = subtags.first().toString().ifEmpty { return null }
            val rest = subtags.drop(1).map { it.toString() }
            return when (language) {
                "pt" -> if ("br" in rest) "pt-br" else "pt"
                "zh" -> {
                    // Four Characters that are each an ASCII letter: a script.
                    val script = rest.firstOrNull { it.length == 4 && it.all { letter -> letter in 'a'..'z' } }
                    val traditional = script?.let { it == "hant" } ?: rest.any { it in setOf("tw", "hk", "mo") }
                    if (traditional) null else "zh"
                }
                else -> language
            }
        }

        /**
         * `locale(for:)`: the locale dates and numbers take in a language: Portuguese is Portugal's, Brazilian
         * Portuguese Brazil's and Chinese Simplified Chinese.
         */
        fun locale(languageCode: String): Locale = Locale.forLanguageTag(
            when (languageCode) {
                "pt" -> "pt-PT"
                "pt-br" -> "pt-BR"
                "zh" -> "zh-Hans"
                else -> languageCode
            },
        )
    }
}
