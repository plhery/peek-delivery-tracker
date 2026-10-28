package com.plhery.peekdeliverytracker.core

import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

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

        /** The language saved by the app, else the first of the device's languages Peek speaks, else English. */
        fun languageCode(saved: String?, preferred: List<String>, available: Set<String>): String {
            val available = available.mapTo(HashSet()) { it.nfc() }
            return (listOfNotNull(saved) + preferred.map(::primaryLanguage)).firstOrNull { it.nfc() in available } ?: "en"
        }
    }
}

/**
 * `tag.split(separator: "-").first.map(String.init)?.lowercased() ?? ""`: the language of a BCP 47 tag, split on
 * Characters, so a hyphen with a combining mark on it splits nothing.
 */
internal fun primaryLanguage(tag: String): String = tag.splitOnCharacter("-").firstOrNull()?.lowercased() ?: ""
