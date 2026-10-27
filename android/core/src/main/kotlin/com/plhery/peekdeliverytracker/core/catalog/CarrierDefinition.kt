package com.plhery.peekdeliverytracker.core.catalog

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarrierRequirement(
    val field: Field,
    val validator: String? = null,
    val whenTrackingNumber: String? = null,
    val optional: Boolean? = null,
    val label: String,
    val type: String,
    val placeholder: String? = null,
    val help: String? = null,
    val pattern: String? = null,
    val maxLength: Int? = null,
    val inputMode: String? = null,
    val autoComplete: String? = null,
) {
    @Serializable
    enum class Field(val rawValue: String) {
        @SerialName("trackingUrl") TrackingUrl("trackingUrl"),
        @SerialName("dpdPostcode") DpdPostcode("dpdPostcode");

        companion object {
            fun fromRawValue(rawValue: String): Field? = entries.firstOrNull { it.rawValue == rawValue }
        }
    }

    fun normalizedValue(rawValue: String): String {
        var value = rawValue.trimmingWhitespacesAndNewlines()
        if (inputMode == "numeric") {
            value = value.filteringNumbers()
        } else if (validator == "paackPostcode") {
            value = value.uppercase().removingWhitespace()
        } else if (takesAnyPostcode) {
            // As the country writes it: capitals, one space between groups.
            value = value.uppercase().splitOnWhitespace().joinToString(" ")
        }
        if (maxLength != null) {
            value = value.characterPrefix(maxLength)
        }
        return value
    }

    /** The carrier delivers abroad too and takes any country's postcode. */
    val takesAnyPostcode: Boolean get() = validator == "internationalPostcode"

    /**
     * The value a field keeps while it is typed: `normalizedValue`, with the space a postcode in two groups
     * ("SW1A 1AA") needs before its second group.
     */
    fun typedValue(rawValue: String): String {
        val value = normalizedValue(rawValue)
        if (!takesAnyPostcode || rawValue.lastCharacter()?.isWhitespaceCharacter != true ||
            value.isEmpty() || value.characterCount >= (maxLength ?: Int.MAX_VALUE)
        ) return value
        return "$value "
    }

    /** Number keys first where the example is all digits; letters stay one tap away. */
    fun startsWithNumberKeys(example: String?): Boolean =
        takesAnyPostcode && example?.characters()?.any { it.isLetterCharacter } != true

    /** The parcel can be saved without it; a value that is typed is still checked. */
    val isOptional: Boolean get() = optional == true

    fun accepts(rawValue: String): Boolean {
        var value = rawValue.trimmingWhitespacesAndNewlines()
        if (inputMode == "numeric") {
            value = value.filteringNumbers()
        }
        if (value.isEmpty()) return false
        if (maxLength != null && value.characterCount > maxLength) return false
        if (pattern.isNullOrEmpty()) return true
        return CatalogExpressions.foundationMatches(value, pattern)
    }

    fun isSatisfied(rawValue: String): Boolean {
        if (isOptional && rawValue.trimmingWhitespacesAndNewlines().isEmpty()) return true
        return accepts(rawValue)
    }

    /**
     * The postcode this field suggests for `carrier`, from the postcodes given before in the order they come:
     * the first given to that carrier that fits, otherwise the first given to any carrier that fits, since
     * most parcels go to the same address. Returned as the field takes it.
     */
    fun suggestedPostcode(carrier: CarrierId, given: List<GivenPostcode>): String? {
        val fitting = given.mapNotNull { entry -> fitted(entry.postcode)?.let { entry.carrier to it } }
        return (fitting.firstOrNull { it.first == carrier } ?: fitting.firstOrNull())?.second
    }

    /**
     * A postcode as this field takes it, or nil when it does not fit as written: "1012 AB" is not "1012" in a
     * four-digit field. Like the web, the pattern is tried on the postcode in capitals with single spaces.
     */
    private fun fitted(postcode: String): String? {
        val value = postcode.uppercase().splitOnWhitespace().joinToString(" ")
        if (value.isEmpty()) return null
        val pattern = pattern?.nonEmpty
        if (pattern != null && !CatalogExpressions.foundationMatches(value, pattern)) return null
        return normalizedValue(value)
    }

    companion object {
        /** Any country's postcode, as the service checks one from another country than the carrier's own. */
        const val anyPostcode = "^(?=.{3,12}$)(?=.*[0-9])[A-Za-z0-9]+(?:[ -][A-Za-z0-9]+)*$"
    }
}

/** A postcode given to a carrier for an earlier parcel. */
@Serializable
data class GivenPostcode(
    val carrier: CarrierId,
    val postcode: String,
) {
    companion object {
        /**
         * What the add sheet suggests from: the postcodes of the account's parcels, newest parcel first, then
         * the ones this phone remembers, in their order.
         */
        fun candidates(parcels: List<Parcel>, remembered: List<GivenPostcode>): List<GivenPostcode> =
            parcels.sortedWith { left, right -> right.createdAt.compareTo(left.createdAt) }
                .mapNotNull { parcel -> parcel.dpdPostcode?.let { GivenPostcode(parcel.carrier, it) } } + remembered
    }
}

@Serializable
data class CarrierDefinition(
    val displayName: String,
    val displayNames: Map<String, String>? = null,
    /** Other names the carrier is known by, searched by the carrier picker. */
    val aliases: List<String>? = null,
    /** Where it delivers under its own name, home country first. */
    val countries: List<String>? = null,
    val trackingSiteName: String? = null,
    val color: String,
    val selectable: Boolean,
    val timezone: String,
    val tracking: Tracking,
    val trackingUrlTemplate: String? = null,
    /** The carrier's page for typing a number in, linked when a parcel link hides the number. */
    val trackingSearchUrl: String? = null,
    val linkRules: List<LinkRule>,
    val detectionRules: List<DetectionRule>,
) {
    @Serializable
    data class Tracking(
        val mode: String,
        val adapter: String? = null,
        val requirements: List<CarrierRequirement>? = null,
        /** Present when the carrier can recognize a number; no two carriers share a rank. */
        val recognitionRank: Int? = null,
        /** The numbers that recognition can answer; the carrier's rules only suggest it for others. */
        val recognitionPattern: String? = null,
        /** Recognition asks the carrier only for a country hint among its countries. */
        val recognitionServedCountriesOnly: Boolean? = null,
        val browserRecognitionRank: Int? = null,
    )

    @Serializable
    data class LinkRule(
        val domains: List<String>,
        val params: List<String>? = null,
        /** Captures the number from the whole query, for portals without named parameters. */
        val query: String? = null,
        val path: String? = null,
        val pathPattern: String? = null,
        val fragment: String? = null,
        val detectFromNumber: Boolean? = null,
        /** Names the carrier only for a number its own rules offer; another number is detected on its own. */
        val ownNumbersOnly: Boolean? = null,
        val keepsCapabilityUrl: Boolean? = null,
    )

    @Serializable
    data class DetectionRule(
        val pattern: String,
        val rawPattern: String? = null,
        val confidence: String,
        val checksum: String? = null,
        /** Low-confidence number evidence: the carrier is listed first among suggestions. */
        val preferred: Boolean? = null,
    )
}

data class CarrierMatch(
    val carrier: CarrierId,
    val confidence: Confidence,
    val candidates: List<CarrierId>,
    /** Candidates a `preferred` rule backs with number evidence, listed first. */
    val preferred: List<CarrierId> = emptyList(),
) {
    enum class Confidence(val rawValue: String) {
        High("high"), Low("low"), None("none");

        companion object {
            fun fromRawValue(rawValue: String): Confidence? = entries.firstOrNull { it.rawValue == rawValue }
        }
    }
}

data class TrackingInputMatch(
    val trackingNumber: String,
    val carrier: CarrierId,
    val confidence: CarrierMatch.Confidence,
    val candidates: List<CarrierId>,
    val trackingUrl: String?,
    val source: Source,
) {
    enum class Source(val rawValue: String) {
        Number("number"), Link("link"), Text("text"), None("none");

        companion object {
            fun fromRawValue(rawValue: String): Source? = entries.firstOrNull { it.rawValue == rawValue }
        }
    }
}

/** A shop whose order numbers Add tells from tracking numbers, as the front door does (`shared/reading.json`). */
@Serializable
enum class OrderShop(val rawValue: String) {
    @SerialName("amazon") Amazon("amazon"),
    @SerialName("shein") Shein("shein"),
    @SerialName("shopee") Shopee("shopee"),
    @SerialName("temu") Temu("temu");

    companion object {
        fun fromRawValue(rawValue: String): OrderShop? = entries.firstOrNull { it.rawValue == rawValue }
    }
}

/** One tracking number a text names, with what its shape says about the carrier. */
data class NumberInText(
    /** The number without spaces, dots or dashes: what makes two spellings the same number. */
    val normalized: String,
    val match: TrackingInputMatch,
)

/** A postal number whose check digit doesn't add up, which Add asks about as the front door does. */
data class CheckDigitTrouble(
    /** The number with a lookalike letter read as its digit, or back, when that one adds up. */
    val suggestion: String?,
)

/** Swift's `CarrierCatalogRefreshResult`. */
enum class CarrierCatalogRefreshResult {
    Updated, NotModified, Skipped, Failed,
}
