package com.plhery.peekdeliverytracker.core.catalog

import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.Locale
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

// The helpers from Core.swift that the carrier catalog uses.

internal val String.nonEmpty: String? get() = ifEmpty { null }

enum class AppLanguage(val rawValue: String) {
    En("en"), De("de"), Fr("fr"), It("it"), Es("es"), Pt("pt"), Pl("pl");

    val id: String get() = rawValue
    val locale: Locale get() = Locale.forLanguageTag(if (this == Pt) "pt-PT" else rawValue)

    companion object {
        fun fromRawValue(rawValue: String): AppLanguage? = entries.firstOrNull { it.rawValue == rawValue }

        /** The first of the device's languages the app speaks, else English. */
        val current: AppLanguage get() = fromRawValue(Locale.getDefault().language.lowercase()) ?: En
    }
}

val Parcel.trackingSource: CarrierId? get() = carrierData?.activeTrackingCarrier
val Parcel.swissPostReady: Boolean get() = carrierData?.swissPostReady == true

/**
 * The app's copy in its languages (Localization.json), as Core.swift's `Localizer.text` picks it: the key's
 * device variant, then its plural form for a `count`, then English, then the key itself.
 */
class Localizer(
    var language: AppLanguage = AppLanguage.current,
    /** Where the copy names the device, a variant such as `ipad` replaces the key when it has one. */
    private val variant: String? = null,
) {
    fun text(key: String, variables: Map<String, Any> = emptyMap()): String {
        val messages = dictionaries[language.rawValue] ?: dictionaries["en"] ?: emptyMap()
        val variantKey = variant?.let { if (messages["$key.$it"] != null) "$key.$it" else null } ?: key
        val count = variables["count"]?.toString()?.toDoubleOrNull()
        val category = if (count == 1.0) {
            "one"
        } else if (language == AppLanguage.Pl && count != null && count.isFinite() && count >= 0 &&
            kotlin.math.truncate(count) == count
        ) {
            // Polish integer counts: 2–4, except 12–14, use the few form.
            val last = count % 10
            val lastTwo = count % 100
            if (last in 2.0..4.0 && lastTwo !in 12.0..14.0) "few" else "many"
        } else {
            "other"
        }
        val baseKey = listOf("one", "few", "many").firstOrNull { variantKey.endsWith(".$it") }
            ?.let { variantKey.dropLast(it.length + 1) } ?: variantKey
        val pluralKey = "$baseKey.$category"
        val selectedKey = if (category != "other" && messages[pluralKey] != null) pluralKey else variantKey
        var result = messages[selectedKey] ?: dictionaries["en"]?.get(selectedKey) ?: selectedKey
        for ((name, value) in variables) {
            result = result.replace("{{$name}}", value.toString())
        }
        return result
    }

    private companion object {
        val dictionaries: Map<String, Map<String, String>> by lazy {
            runCatching {
                Json.decodeFromString(
                    MapSerializer(String.serializer(), MapSerializer(String.serializer(), String.serializer())),
                    SharedResources.text("Localization.json"),
                )
            }.getOrElse { mapOf("en" to emptyMap()) }
        }
    }
}
