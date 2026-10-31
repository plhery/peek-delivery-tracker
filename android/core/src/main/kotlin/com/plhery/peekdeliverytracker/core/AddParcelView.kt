package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponseAmazonShippingStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingProviderCheckOutcome

// The logic of AddParcelView.swift's `suggestionLabel`, without SwiftUI's AttributedString: the label's text
// with where the number stands and which of its characters the suggestion changes.

object AddParcelView {
    /**
     * "Did you mean …?": the sentence with the number in it. `number` is where the number stands, set in
     * monospace; `marked` are the runs of characters the suggestion changes, as on the front door. Ranges are
     * UTF-16 offsets into `text`.
     */
    data class SuggestionLabel(val text: String, val number: IntRange, val marked: List<IntRange>)

    fun suggestionLabel(suggestion: String, typed: String, sentence: (String) -> String): SuggestionLabel {
        val parts = sentence("\u0000").split("\u0000")
        val before = parts.firstOrNull() ?: ""
        val typedCharacters = typed.characters()
        val text = StringBuilder(before)
        val marked = mutableListOf<IntRange>()
        for ((index, character) in suggestion.characters().withIndex()) {
            val start = text.length
            text.append(character)
            if (index >= typedCharacters.size || typedCharacters[index] != character) {
                // Neighbouring marked characters make one run, as AttributedString merges them.
                val last = marked.lastOrNull()
                if (last != null && last.last + 1 == start) marked[marked.size - 1] = last.first until text.length
                else marked += start until text.length
            }
        }
        val number = before.length until text.length
        text.append(parts.drop(1).joinToString(""))
        return SuggestionLabel(text.toString(), number, marked)
    }
}

/**
 * The postcode a field was filled with from those given before. While the field still holds it, it can be forgotten;
 * then no other fills the field until its details are prepared again. A value, as the Swift struct is: each change
 * gives a new one.
 */
data class PostcodeFill(val postcode: String? = null, val forgotten: Boolean = false) {
    /** What may fill the field: nothing just after a postcode was forgotten in it. */
    fun offering(suggestion: String?): String? = if (forgotten) null else suggestion

    /** Filled with `suggestion`, which the field takes as it is. */
    fun fill(suggestion: String): PostcodeFill = copy(postcode = suggestion)

    fun holds(field: String): Boolean = field.isNotEmpty() && postcode.equalsCanonically(field)

    /** The postcode to forget, when the field still holds it, and the fill once it is forgotten. */
    fun forget(field: String): Pair<String, PostcodeFill>? {
        val postcode = postcode?.takeIf { holds(field) } ?: return null
        return postcode to copy(postcode = null, forgotten = true)
    }

    /** Another carrier's details: what was given before may fill the field again. */
    fun prepare(): PostcodeFill = copy(forgotten = false)
}

/**
 * What the Add sheet (`AddParcelView`) holds and works out from it: the field's text, the number chosen among several,
 * the carrier picked by hand, the number added as typed despite its check digit, the carrier check's answers, the
 * Amazon number's verification, the link and the postcode typed and the postcode's country chosen. The view's own
 * state (focus, saving, the error) is the app's; this is its arithmetic, with [region] the phone's country
 * (`CarrierCatalog.deviceCountry()`).
 */
data class ParcelAddition(
    val trackingInput: String = "",
    /** The number chosen among several in one text (`pick`). */
    val pick: String? = null,
    val carrierOverride: CarrierId? = null,
    /** The number whose check digit was questioned and then added as typed. */
    val asTyped: String? = null,
    val recognition: CarrierRecognition = CarrierRecognition(),
    /** The refusal question the server said yes to (`refusedQuestion`). */
    val refusedQuestion: NumberRefusalRequest? = null,
    val verifiedCarrier: CarrierDetectionResponse? = null,
    val trackingUrl: String = "",
    val deliveryPostcode: String = "",
    /** The delivery country chosen beside the postcode, kept for each carrier whose field takes it. */
    val chosenPostcodeCountry: String? = null,
    val demo: Boolean = false,
    val catalog: CarrierCatalog = CarrierCatalog.shared,
    val region: String? = CarrierCatalog.deviceCountry(),
) {
    /** The fields `submitTracking()` moves the focus between (`Field`). */
    enum class Field { Label, Tracking, TrackingUrl, DeliveryPostcode }

    val cleanedInput: String get() = trackingInput.trimmingWhitespacesAndNewlines()

    /** Every number the field names with certainty, when it names two or more: one is to be chosen. */
    val several: List<NumberInText> get() = catalog.numbers(trackingInput)

    /** The number chosen among several: the first, unless another was chosen. */
    val picked: NumberInText? get() = several.let { numbers -> numbers.firstOrNull { it.normalized == pick } ?: numbers.firstOrNull() }

    /**
     * The shop whose order number the field holds. No carrier tracks one, so nothing asks about it and it is not saved.
     * A text that names several numbers is about them.
     */
    val order: OrderShop? get() = if (picked == null) catalog.order(trackingInput) else null

    /** The number in play: the one chosen among several, or none for an order number. */
    val parsed: TrackingInputMatch
        get() = picked?.match ?: if (order == null) catalog.read(trackingInput) else CarrierCatalog.emptyMatch

    val normalizedNumber: String get() = CarrierCatalog.normalize(parsed.trackingNumber)

    /** The field holds something, but no number to add (`add.notFound`). */
    val notFound: Boolean get() = parsed.trackingNumber.isEmpty() && cleanedInput.isNotEmpty() && order == null

    /** The number as it stands once settled: what leaving the field settles. */
    val settled: Boolean get() = recognition.settledNumber == normalizedNumber

    val amazonNumber: Boolean get() = catalog.isAmazonTrackingNumber(parsed.trackingNumber)

    /**
     * A postal number whose check digit doesn't add up, until it is added as typed. A number chosen among several is
     * certain of its carrier.
     */
    val typo: CheckDigitTrouble?
        get() {
            val number = normalizedNumber
            return if (number.isEmpty() || picked != null || asTyped == number) null else catalog.checkDigitTrouble(number)
        }

    /** Six digits are a pickup code unless a carrier picked by hand takes them as its number. */
    val pickupCode: Boolean get() = carrierOverride == null && CarrierCatalog.isPickupCode(normalizedNumber)

    /**
     * A placeholder, such as the first screen's demo number, is held back in the same way, whichever the carrier: every
     * lookup refuses it. The demo looks nothing up.
     */
    val placeholderNumber: Boolean get() = !demo && !pickupCode && CarrierCatalog.isPlaceholderNumber(normalizedNumber)

    val currentVerification: CarrierDetectionResponse?
        get() = verifiedCarrier?.takeIf { it.trackingNumber == normalizedNumber }

    val recognizable: Boolean get() = CarrierRecognition.applies(parsed, amazonNumber, demo)

    val recognitionRequest: String? get() = recognition.request(normalizedNumber, recognizable)

    val recognitionStatus: CarrierRecognition.Status get() = recognition.status(normalizedNumber, recognizable)

    val checking: Boolean get() = carrierOverride == null && recognitionStatus is CarrierRecognition.Status.Asking

    /** Unrelated carriers all know the number: automatic detection can still save it, but the user should choose. */
    val choosingCarrier: Boolean get() = carrierOverride == null && recognitionStatus is CarrierRecognition.Status.Several

    val lineCarrier: CarrierId? get() = resolvedCarrier.takeIf { it != CarrierId.Unknown }

    /** The carrier a high-confidence rule gives the number, before any check. */
    val numberCarrier: CarrierId?
        get() = parsed.carrier.takeIf {
            parsed.confidence == CarrierMatch.Confidence.High && it != CarrierId.Unknown && it != CarrierId.InternationalPost
        }

    /** What tracking services said about the number in the field while no carrier knows it. */
    fun preflightDetail(localizer: Localizer): String? {
        val answer = recognition.answer
        if (!recognizable || answer == null || answer.trackingNumber != normalizedNumber || answer.carrier != CarrierId.Unknown) return null
        if (answer.trackingFound == true) return localizer.text("add.historyFound")
        if (answer.providers?.any { it.outcome == TrackingProviderCheckOutcome.InputRequired } == true) {
            return localizer.text("add.providerInputNeeded")
        }
        return null
    }

    /** A carrier picked by hand, once the number is settled. */
    val refusalQuestion: NumberRefusalRequest?
        get() = if (amazonNumber || !settled) null else NumberRefusals.question(carrierOverride, normalizedNumber, demo)

    /** A carrier picked by hand that issues no number of this shape is pointed out before the parcel is added. */
    val refused: Boolean get() = refusalQuestion.let { it != null && refusedQuestion == it }

    /** The carrier line's name: the carrier once one is known, else "add.detect". */
    fun lineName(localizer: Localizer): String =
        lineCarrier?.let { catalog.info(it, localizer.language).displayName } ?: localizer.text("add.detect")

    /** The carrier line's detail: what the carrier check has found so far, or how the carrier was given. */
    fun lineDetail(localizer: Localizer): String? {
        if (carrierOverride != null) return localizer.text(if (refused) "add.line.refused" else "add.line.chosen")
        val status = recognitionStatus
        if (status is CarrierRecognition.Status.Recognized) return localizer.text("add.line.found")
        preflightDetail(localizer)?.let { return it }
        val carrier = lineCarrier
        if (amazonNumber || (carrier != null && carrier != CarrierId.InternationalPost)) return localizer.text("add.detectedCarrier")
        return when (status) {
            is CarrierRecognition.Status.Asking -> localizer.text("add.checkingServices")
            is CarrierRecognition.Status.Several -> localizer.text("add.line.several", mapOf("carriers" to carrierNames(status.carriers, localizer)))
            is CarrierRecognition.Status.NotFound -> localizer.text("add.line.none")
            is CarrierRecognition.Status.Failed -> localizer.text("add.line.failed")
            CarrierRecognition.Status.Unasked -> localizer.text("add.line.later")
            CarrierRecognition.Status.Idle, is CarrierRecognition.Status.Recognized -> null
        }
    }

    /** What automatic detection does, in the picker: what the check found, or the carrier the number names. */
    fun automaticDescription(localizer: Localizer): String {
        preflightDetail(localizer)?.let { return it }
        return when (val status = recognitionStatus) {
            is CarrierRecognition.Status.Asking -> localizer.text("add.checkingServices")
            is CarrierRecognition.Status.Recognized ->
                localizer.text("add.recognized", mapOf("carrier" to catalog.info(status.carrier, localizer.language).displayName))
            is CarrierRecognition.Status.Several -> localizer.text("picker.auto.several", mapOf("carriers" to carrierNames(status.carriers, localizer)))
            is CarrierRecognition.Status.NotFound ->
                if (status.services) localizer.text("picker.auto.servicesNone")
                else localizer.text("picker.auto.none", mapOf("carriers" to carrierNames(status.carriers, localizer)))
            is CarrierRecognition.Status.Failed -> when {
                status.services -> localizer.text("picker.auto.servicesFailed")
                status.carriers.isEmpty() -> localizer.text("add.line.failed")
                else -> localizer.text("picker.auto.failed", mapOf("carriers" to carrierNames(status.carriers, localizer)))
            }
            CarrierRecognition.Status.Idle, CarrierRecognition.Status.Unasked -> numberCarrier?.let {
                localizer.text("picker.auto.detected", mapOf("carrier" to catalog.info(it, localizer.language).displayName))
            } ?: localizer.text("picker.auto.later")
        }
    }

    /** The picker leads with the carriers that know the number, then those that fit it, then the ones used before. */
    fun pickerSections(parcels: List<Parcel>): List<CarrierChange.PickerSection> {
        val knowing = (recognitionStatus as? CarrierRecognition.Status.Several)?.carriers ?: emptyList()
        val fitting = (numberCarrier?.let { listOf(it) } ?: parsed.candidates).filter { catalog.info(it).selectable }
        return listOf(
            CarrierChange.PickerSection("known", "picker.section.known", knowing),
            CarrierChange.PickerSection(
                "fits", if (numberCarrier == null) "picker.section.fits" else "picker.section.detected", fitting.filter { it !in knowing },
            ),
            CarrierChange.PickerSection(
                "used", "picker.section.used",
                CarrierPickerSearch.usedCarriers(parcels, catalog).filter { it !in knowing && it !in fitting },
            ),
        )
    }

    /** The carriers the picker tags, each with its tag's key: the one that found the number, or those that know it. */
    val pickerTags: Map<CarrierId, String>
        get() = when (val status = recognitionStatus) {
            is CarrierRecognition.Status.Recognized -> mapOf(status.carrier to "picker.tag.found")
            is CarrierRecognition.Status.Several -> status.carriers.associateWith { "picker.tag.knows" }
            else -> emptyMap()
        }

    /** The carriers' names as a list in the app's language (`ListFormatter`). */
    fun carrierNames(carriers: List<CarrierId>, localizer: Localizer): String =
        Icu.lists.join(carriers.map { catalog.info(it, localizer.language).displayName }, localizer.language.locale)

    val shippingConfirmed: Boolean
        get() {
            val verification = currentVerification ?: return false
            return amazonNumber && verification.carrier == CarrierId.AmazonShipping &&
                (verification.amazonShippingStatus == CarrierDetectionResponseAmazonShippingStatus.Available ||
                    verification.amazonShippingStatus == CarrierDetectionResponseAmazonShippingStatus.Expired)
        }

    val accountRequired: Boolean
        get() = if (amazonNumber) !shippingConfirmed else catalog.requiresAmazonAccount(carrierOverride ?: parsed.carrier)

    val resolvedCarrier: CarrierId
        get() {
            if (amazonNumber) return if (shippingConfirmed) CarrierId.AmazonShipping else CarrierId.AmazonLogistics
            carrierOverride?.let { return it }
            (recognitionStatus as? CarrierRecognition.Status.Recognized)?.let { return it.carrier }
            return parsed.carrier
        }

    /** The line under the carrier: how it tracks, or what Amazon said, where the carrier doesn't track by itself. */
    fun hint(localizer: Localizer): String? {
        val confirmed = shippingConfirmed
        if (catalog.tracksAutomatically(resolvedCarrier) && !confirmed) return null
        val key = when {
            !confirmed -> catalog.trackingHintKey(resolvedCarrier)
            currentVerification?.amazonShippingStatus == CarrierDetectionResponseAmazonShippingStatus.Expired -> "add.amazonHistoryExpired"
            else -> "add.amazonShippingConfirmed"
        }
        return localizer.text(key, mapOf("carrier" to catalog.info(resolvedCarrier, localizer.language).displayName))
    }

    /** Amazon's check couldn't answer: it can be asked again. */
    val amazonCheckUnavailable: Boolean
        get() = amazonNumber && currentVerification?.amazonShippingStatus == CarrierDetectionResponseAmazonShippingStatus.Unavailable

    /** Amazon numbers must be verified before they can be added. */
    val lookupTrackingNumber: String? get() = if (!demo && amazonNumber) normalizedNumber else null

    /**
     * For a carrier picked by hand that the number cannot belong to (a forwarder such as Asendia for a DPD number), the
     * carrier recognition found stays a hint.
     */
    val knownElsewhere: CarrierId?
        get() {
            val override = carrierOverride ?: return null
            val carrier = (recognitionStatus as? CarrierRecognition.Status.Recognized)?.carrier ?: return null
            return carrier.takeIf { it != override }
        }

    /** The carrier's fields, the postcode's shaped for the country beside it. */
    val requirements: List<CarrierRequirement>
        get() {
            val country = postcodeCountry
            return catalog.requirements(resolvedCarrier, parsed.trackingNumber).map { catalog.postcodeRequirement(it, resolvedCarrier, country) }
        }

    val trackingUrlRequirement: CarrierRequirement?
        get() = requirements.firstOrNull { it.field == CarrierRequirement.Field.TrackingUrl }

    /** The carrier's postcode field, before the country beside it shapes it. */
    val carrierPostcodeRequirement: CarrierRequirement?
        get() = catalog.requirements(resolvedCarrier, parsed.trackingNumber).firstOrNull { it.field == CarrierRequirement.Field.DpdPostcode }

    /** The country the postcode is from: the one chosen while this field takes it, otherwise where the field starts. */
    val postcodeCountry: String?
        get() {
            val requirement = carrierPostcodeRequirement ?: return null
            val chosen = chosenPostcodeCountry
            if (chosen != null && chosen in catalog.postcodeCountries(resolvedCarrier, requirement)) return chosen
            return catalog.initialPostcodeCountry(resolvedCarrier, requirement, region = region)
        }

    /** The postcode's country, offered beside the field when it takes more than one. */
    val offeredPostcodeCountry: String?
        get() {
            val requirement = carrierPostcodeRequirement ?: return null
            if (catalog.postcodeCountries(resolvedCarrier, requirement).size <= 1) return null
            return postcodeCountry
        }

    val postcodeRequirement: CarrierRequirement?
        get() = requirements.firstOrNull { it.field == CarrierRequirement.Field.DpdPostcode }

    /** The link's field shows: the carrier needs one, and the text had none. */
    val asksTrackingUrl: Boolean get() = trackingUrlRequirement != null && parsed.trackingUrl == null

    val needsRequiredDetails: Boolean get() = asksTrackingUrl || postcodeRequirement != null

    /** The postcode field's example: the country's beside it where the field names one, else the reader's. */
    fun postcodeExample(requirement: CarrierRequirement): String? =
        catalog.postcodeExample(resolvedCarrier, requirement, if (requirement.readsCountry == true) postcodeCountry else region)

    /**
     * The number pad for a numeric field, the number keys first where the example is all digits, else the letters
     * (`keyboardType`).
     */
    fun postcodeKeys(requirement: CarrierRequirement): CarrierChange.PostcodeKeys = when {
        requirement.inputMode == "numeric" -> CarrierChange.PostcodeKeys.NumberPad
        requirement.startsWithNumberKeys(postcodeExample(requirement)) -> CarrierChange.PostcodeKeys.NumbersAndPunctuation
        else -> CarrierChange.PostcodeKeys.AsciiCapable
    }

    val canSave: Boolean
        get() {
            if (accountRequired || pickupCode || placeholderNumber || parsed.trackingNumber.isEmpty()) return false
            val lookup = lookupTrackingNumber
            if (lookup != null && verifiedCarrier?.trackingNumber != lookup) return false
            for (requirement in requirements) {
                when (requirement.field) {
                    CarrierRequirement.Field.TrackingUrl -> {
                        val value = requirement.normalizedValue(parsed.trackingUrl ?: trackingUrl)
                        val url = Url.parse(value)
                        if (!requirement.accepts(value) || url == null || url.scheme != "https" || url.host == null) return false
                    }
                    CarrierRequirement.Field.DpdPostcode -> if (!requirement.isSatisfied(deliveryPostcode)) return false
                }
            }
            return true
        }

    /** Where Next in the tracking field goes: the link, then the postcode, while either still wants something, else the name. */
    val nextField: Field
        get() {
            val urlRequirement = trackingUrlRequirement
            val postcode = postcodeRequirement
            return when {
                urlRequirement != null && parsed.trackingUrl == null && !urlRequirement.accepts(trackingUrl) -> Field.TrackingUrl
                postcode != null && !postcode.accepts(deliveryPostcode) -> Field.DeliveryPostcode
                else -> Field.Label
            }
        }

    /** The link `save()` sends: the text's own, else the one typed, where the carrier needs one. */
    val sentTrackingUrl: String? get() = if (trackingUrlRequirement != null) parsed.trackingUrl ?: trackingUrl else null

    /** The postcode `save()` sends, as the field takes it. */
    val sentPostcode: String? get() = postcodeRequirement?.normalizedValue(deliveryPostcode)

    /** The country sent with the postcode: none without one, nor for the carrier's home country. */
    val sentPostcodeCountry: String? get() = catalog.sentPostcodeCountry(postcodeCountry, sentPostcode, resolvedCarrier)

    /** The last postcode given that fits the field, this carrier's first (`previousPostcode(for:requirement:)`). */
    fun previousPostcode(requirement: CarrierRequirement, given: List<GivenPostcode>): String? =
        requirement.suggestedPostcode(resolvedCarrier, given)

    /**
     * The field's text changed (`onChange(of: trackingInput)`): no carrier picked by hand any more, and a number chosen
     * among several stays chosen while the text still names it.
     */
    fun typing(text: String): ParcelAddition {
        val next = copy(trackingInput = text, carrierOverride = null)
        return if (next.several.any { it.normalized == pick }) next else next.copy(pick = null)
    }

    /** The number settled as it stands in `text`: a paste, a scan, a draft, a suggestion. */
    fun settling(text: String): ParcelAddition = copy(recognition = recognition.copy(settledNumber = trackingNumber(text, catalog)))

    companion object {
        /** The number a text settles on: the first of several, else the one it names, none for an order number. */
        fun trackingNumber(input: String, catalog: CarrierCatalog = CarrierCatalog.shared): String {
            catalog.numbers(input).firstOrNull()?.let { return it.normalized }
            return if (catalog.order(input) == null) CarrierCatalog.normalize(catalog.read(input).trackingNumber) else ""
        }

        /** The longest name a parcel takes (`label.prefix(80)`). */
        const val LABEL_LIMIT = 80

        /** The name field as it takes `typed` (`onChange(of: label)`): its first [LABEL_LIMIT] characters. */
        fun label(typed: String): String = typed.characterPrefix(LABEL_LIMIT)

        /** A check's answer as text, for a window to keep through being made again, as the iPhone's `@State` stays. */
        fun keptAnswer(answer: CarrierDetectionResponse): String = ApiJson.encodeToString(CarrierDetectionResponse.serializer(), answer)

        /** The answer [keptAnswer] kept; none where it no longer reads. */
        fun answer(kept: String): CarrierDetectionResponse? =
            runCatching { ApiJson.decodeFromString(CarrierDetectionResponse.serializer(), kept) }.getOrNull()

        /** The question a carrier picked by hand was asked, as text for a window to keep ([keptAnswer]). */
        fun keptQuestion(question: NumberRefusalRequest): String = ApiJson.encodeToString(NumberRefusalRequest.serializer(), question)

        /** The question [keptQuestion] kept; none where it no longer reads. */
        fun question(kept: String): NumberRefusalRequest? =
            runCatching { ApiJson.decodeFromString(NumberRefusalRequest.serializer(), kept) }.getOrNull()
    }
}
