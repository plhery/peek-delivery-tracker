package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.CatalogExpressions.capture
import com.plhery.peekdeliverytracker.core.CatalogExpressions.foundationMatches
import com.plhery.peekdeliverytracker.core.CatalogExpressions.foundationReplacing
import com.plhery.peekdeliverytracker.core.CatalogExpressions.hits
import com.plhery.peekdeliverytracker.core.CatalogExpressions.matches
import com.plhery.peekdeliverytracker.core.CatalogExpressions.matchesIn
import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.CodingErrorAction
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.time.Duration
import java.time.Instant
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

/**
 * The carrier catalog: the shared engine's carriers, their tracking-number shapes and links, read from the
 * bundled CarrierCatalog.json and refreshed from /api/carriers. `definitionsFlow` publishes each change.
 */
class CarrierCatalog private constructor(
    initial: Initial,
    private val cacheFile: File?,
    private val loader: Loader?,
) {
    /** The HTTP call behind `refresh`, which the app makes. */
    fun interface Loader {
        suspend fun load(request: Request): Response
    }

    data class Request(val url: String, val headers: Map<String, String>, val timeoutSeconds: Long)

    class Response(val statusCode: Int, val headers: Map<String, String> = emptyMap(), val body: ByteArray) {
        fun header(name: String): String? = headers.entries.firstOrNull { it.key.equals(name, ignoreCase = true) }?.value
    }

    private class Initial(val definitions: Map<CarrierId, CarrierDefinition>, val etag: String?)

    private class Contract(
        val carriers: Map<String, CarrierDefinition>,
        /** Carriers this build cannot read, such as one asking for an input it does not know. */
        val unreadable: Set<String>,
    )

    private class CatalogException : Exception("Invalid carrier catalog")

    /** The bundled catalog, or the one cached by a refresh in `cacheFile`. */
    constructor(cacheFile: File? = null, loader: Loader? = null) :
        this(cachedContract(cacheFile) ?: Initial(bundledDefinitions(), null), cacheFile, loader)

    /** A catalog read from `data`; throws when it is not one. */
    constructor(data: ByteArray, cacheFile: File? = null, loader: Loader? = null) :
        this(Initial(validatedDefinitions(decodeContract(data).carriers), null), cacheFile, loader)

    private val state = MutableStateFlow(initial.definitions)

    /** Swift's `@Published definitions`. */
    val definitionsFlow: StateFlow<Map<CarrierId, CarrierDefinition>> = state.asStateFlow()

    val definitions: Map<CarrierId, CarrierDefinition> get() = state.value

    private fun publish(definitions: Map<CarrierId, CarrierDefinition>) {
        state.value = definitions
        memos = Memos()
    }

    /** Views ask for the same input's result many times per render and keystroke. */
    private class Memo<V> {
        private class Box<V>(val value: V)

        private val values = ConcurrentHashMap<String, Box<V>>()

        fun getOrPut(key: String, compute: () -> V): V {
            values[key]?.let { return it.value }
            val value = compute()
            if (values.size >= MEMO_LIMIT) values.clear()
            values[key] = Box(value)
            return value
        }
    }

    private class Memos {
        val detections = Memo<CarrierMatch>()
        val parsedInputs = Memo<TrackingInputMatch>()
        val readings = Memo<TrackingInputMatch>()
        val orders = Memo<OrderShop?>()
        val numbersRead = Memo<List<NumberInText>>()
    }

    @Volatile private var memos = Memos()
    @Volatile private var cachedETag: String? = initial.etag
    @Volatile private var lastRefreshAttempt: Instant? = null

    suspend fun refresh(
        apiBaseUrl: String,
        force: Boolean = false,
        now: Instant = Instant.now(),
    ): CarrierCatalogRefreshResult {
        synchronized(this) {
            val lastRefreshAttempt = lastRefreshAttempt
            if (!force && lastRefreshAttempt != null && Duration.between(lastRefreshAttempt, now) < refreshInterval) {
                return CarrierCatalogRefreshResult.Skipped
            }
            this.lastRefreshAttempt = now
        }

        val headers = linkedMapOf("Accept" to "application/json")
        cachedETag?.let { headers["If-None-Match"] = it }
        val request = Request(url = carriersUrl(apiBaseUrl), headers = headers, timeoutSeconds = 15)

        val loader = loader ?: return CarrierCatalogRefreshResult.Failed
        return try {
            val response = loader.load(request)
            if (response.statusCode == 304) return CarrierCatalogRefreshResult.NotModified
            if (response.statusCode != 200 || response.body.size > maximumResponseBytes) {
                return CarrierCatalogRefreshResult.Failed
            }
            val contract = decodeContract(response.body)
            val carriers = LinkedHashMap(contract.carriers)
            // A carrier this build cannot read keeps the definition it has; the others refresh.
            for (key in contract.unreadable) {
                definitions[CarrierId(key)]?.let { carriers[key] = it }
            }
            val refreshed = validatedDefinitions(carriers)
            val etag = response.header("ETag")
            publish(refreshed)
            cachedETag = etag
            saveCache(carriers, etag)
            CarrierCatalogRefreshResult.Updated
        } catch (cancellation: CancellationException) {
            throw cancellation
        } catch (_: Exception) {
            CarrierCatalogRefreshResult.Failed
        }
    }

    fun info(carrier: CarrierId, language: AppLanguage? = null): CarrierDefinition {
        val definition = definitions[carrier] ?: definitions[CarrierId.Unknown] ?: fallbackDefinitions.getValue(CarrierId.Unknown)
        val name = language?.let { definition.displayNames?.get(it.rawValue) } ?: return definition
        return definition.copy(displayName = name)
    }

    val selectableCarriers: List<CarrierId>
        get() {
            val comparator = LocalizedComparison.CaseInsensitive.comparator(Locale.getDefault())
            return definitions.filter { it.value.selectable }.keys
                .sortedWith { left, right -> comparator.compare(info(left).displayName, info(right).displayName) }
        }

    fun tracksAutomatically(carrier: CarrierId): Boolean = info(carrier).tracking.mode == "automatic"

    /**
     * The carriers the detect route asks about an ambiguous number, best first: number evidence, then a
     * passing check digit, then the catalog's recognition rank. A carrier whose recognition names the numbers
     * it answers is asked only about those, and one asked only for its own countries is not asked: this build
     * gives no country hint. Mirrors `recognitionAskedCarriers`; the detection golden file keeps them in step.
     */
    fun recognitionCandidates(raw: String, browser: Boolean = false): List<CarrierId> {
        val match = detect(raw)
        val unknownPostalCarrier = match.carrier == CarrierId.InternationalPost
        if (match.confidence != CarrierMatch.Confidence.Low && !unknownPostalCarrier) return emptyList()
        val definitions = definitions
        var candidates = match.candidates
        var preferred = match.preferred
        val number = normalize(raw)
        val printed = raw.trimmingWhitespacesAndNewlines().uppercase()
        if (unknownPostalCarrier) {
            // The generic postal match already checked S10. Recover low rules it hid; each carrier still has
            // to confirm the whole identity. A rule gated by another check, known to this build or not, stays
            // out, as in the shared engine: S10 does not vouch for it.
            val postalMatches = definitions.mapNotNull { (carrier, definition) ->
                val rule = definition.detectionRules.firstOrNull { rule ->
                    rule.confidence == "low" && (rule.checksum == null || rule.checksum == "s10") &&
                        matches(number, rule.pattern) &&
                        (rule.rawPattern?.let { matches(printed, it) } ?: true)
                } ?: return@mapNotNull null
                carrier to (rule.preferred == true)
            }
            candidates = postalMatches.map { it.first }
            preferred = postalMatches.filter { it.second }.map { it.first }
        }
        fun rank(carrier: CarrierId): Int? =
            if (browser) definitions[carrier]?.tracking?.browserRecognitionRank else definitions[carrier]?.tracking?.recognitionRank
        // A preferred carrier that cannot be asked (DPD France) keeps its brand's other networks out: DPD's
        // guest API also answers for DPD France parcels.
        val shadowed = preferred.filter { rank(it) == null }.mapNotNull { networkBrand(it) }.toSet()
        val eligible = candidates.filter { carrier ->
            val definition = definitions[carrier] ?: return@filter false
            if (rank(carrier) == null || definition.tracking.mode != "automatic" || definition.tracking.adapter == "universal") {
                return@filter false
            }
            val pattern = definition.tracking.recognitionPattern
            if (!browser && pattern != null && !matches(number, pattern)) return@filter false
            if (!browser && definition.tracking.recognitionServedCountriesOnly == true) return@filter false
            if (browser && requirements(carrier, raw).any { it.optional != true }) return@filter false
            networkBrand(carrier)?.let { it !in shadowed } ?: true
        }
        val checked = eligible.filter { passesCheckDigit(it, number, printed) }.toSet()
        // Ranks are unique, so the order does not depend on the catalog's key order.
        val ordered = eligible.sortedWith { left, right ->
            val leftPreferred = left in preferred
            val leftChecked = left in checked
            when {
                leftPreferred != (right in preferred) -> if (leftPreferred) -1 else 1
                leftChecked != (right in checked) -> if (leftChecked) -1 else 1
                else -> (rank(right) ?: 0).compareTo(rank(left) ?: 0)
            }
        }
        return ordered.take(if (browser) 2 else maximumRecognitions)
    }

    /**
     * Whether the rule that offered a carrier for this number, its first rule that fits as in `detect`, passed
     * a check digit. A check this build cannot run passes nothing.
     */
    private fun passesCheckDigit(carrier: CarrierId, number: String, printed: String): Boolean {
        val rule = definitions[carrier]?.detectionRules?.firstOrNull { rule ->
            matches(number, rule.pattern) &&
                (rule.rawPattern?.let { matches(printed, it) } ?: true) &&
                (rule.checksum?.let { checksumPasses(it, number) != false } ?: true)
        }
        return rule?.checksum?.let { checksumPasses(it, number) } == true
    }

    fun discoveryCandidates(raw: String): List<CarrierId> {
        val http = recognitionCandidates(raw)
        return http + recognitionCandidates(raw, browser = true).filter { it !in http }
    }

    fun isAmazonTrackingNumber(raw: String): Boolean {
        val pattern = definitions[CarrierId.AmazonLogistics]?.detectionRules?.firstOrNull()?.pattern ?: return false
        return matches(normalize(raw), pattern)
    }

    fun requiresAmazonAccount(carrier: CarrierId, trackingNumber: String = ""): Boolean =
        carrier != CarrierId.AmazonShipping && (carrier == CarrierId.AmazonLogistics || isAmazonTrackingNumber(trackingNumber))

    fun trackingHintKey(carrier: CarrierId): String {
        if (requiresAmazonAccount(carrier)) return "add.amazonAccount"
        return if (tracksAutomatically(carrier)) "add.autoSync" else "add.linkSync"
    }

    /**
     * The example in a carrier's field. A national carrier states its own postcode, and a field for another
     * country's postcode that country's, or none (an empty placeholder). One that takes any country's leaves
     * it out and shows the region given, or the carrier's own country when that has no example.
     */
    fun postcodeExample(
        carrier: CarrierId,
        requirement: CarrierRequirement,
        region: String? = currentRegion(),
    ): String? {
        requirement.placeholder?.let { return it.nonEmpty }
        if (requirement.field != CarrierRequirement.Field.DpdPostcode) return null
        return (listOfNotNull(region?.uppercase()) + (info(carrier).countries ?: emptyList()))
            .firstNotNullOfOrNull { postcodeExamples[it] }
    }

    /** The country a postcode given for the carrier is from unless another is named: its first. */
    fun homeCountry(carrier: CarrierId): String? = info(carrier).countries?.firstOrNull()

    /**
     * The countries a postcode for this field can be from: any when the carrier takes any country's, otherwise
     * the carrier's own, whose shapes its check reads.
     */
    fun postcodeCountries(carrier: CarrierId, requirement: CarrierRequirement): List<String> =
        if (requirement.takesAnyPostcode) countries else info(carrier).countries ?: emptyList()

    /** Swift's `(own:, others:)`. */
    data class PostcodeCountryChoices(val own: List<String>, val others: List<String>)

    /** The same countries as a menu lists them: the carrier's own, home first, then the others by name. */
    fun postcodeCountryChoices(
        carrier: CarrierId,
        requirement: CarrierRequirement,
        language: AppLanguage,
    ): PostcodeCountryChoices {
        val own = info(carrier).countries ?: emptyList()
        if (!requirement.takesAnyPostcode) return PostcodeCountryChoices(own, emptyList())
        return PostcodeCountryChoices(own, countriesByName(language).filter { it !in own })
    }

    /**
     * The country a postcode field starts on. The parcel's own postcode for the carrier keeps the country
     * stored with it, or the carrier's home when none was; otherwise the first the field takes of the parcel's
     * destination and the device's region, else the carrier's home.
     */
    fun initialPostcodeCountry(
        carrier: CarrierId,
        requirement: CarrierRequirement,
        parcel: Parcel? = null,
        region: String? = currentRegion(),
    ): String? {
        val home = homeCountry(carrier)
        val postcode = parcel?.dpdPostcode?.nonEmpty
        val known: List<String?> = if (parcel != null && parcel.carrier == carrier && postcode != null) {
            val stored = parcel.carrierData?.postcodeCountry
            listOf(if (stored?.postcode == postcode) stored.country else home)
        } else {
            listOf(parcel?.carrierData?.destinationCountry, region)
        }
        val countries = postcodeCountries(carrier, requirement)
        return known.mapNotNull { it?.uppercase() }.firstOrNull { it in countries } ?: home
    }

    /**
     * The field for a postcode from `country`: the carrier's own for its home country, or when its check reads
     * only its own countries' shapes; otherwise any country's shape, with that country's example or none,
     * never the carrier's.
     */
    fun postcodeRequirement(requirement: CarrierRequirement, carrier: CarrierId, country: String?): CarrierRequirement {
        if (requirement.field != CarrierRequirement.Field.DpdPostcode || !requirement.takesAnyPostcode ||
            country == null || country == homeCountry(carrier)
        ) return requirement
        return requirement.copy(
            pattern = CarrierRequirement.anyPostcode,
            maxLength = 12,
            inputMode = "text",
            placeholder = postcodeExamples[country] ?: "",
        )
    }

    /**
     * The country sent with a postcode: none without one, nor for the carrier's home country, so a postcode
     * from there goes as it always has.
     */
    fun sentPostcodeCountry(country: String?, postcode: String?, carrier: CarrierId): String? {
        if (postcode?.trimmingWhitespacesAndNewlines()?.nonEmpty == null || country == homeCountry(carrier)) return null
        return country
    }

    fun requirements(carrier: CarrierId, trackingNumber: String): List<CarrierRequirement> {
        val normalized = normalize(trackingNumber)
        return (info(carrier).tracking.requirements ?: emptyList()).filter { requirement ->
            val pattern = requirement.whenTrackingNumber ?: return@filter true
            matches(normalized, pattern)
        }
    }

    fun detect(raw: String): CarrierMatch {
        val number = normalize(raw)
        if (number.isEmpty()) return CarrierMatch(CarrierId.Unknown, CarrierMatch.Confidence.None, emptyList())
        val printed = raw.trimmingWhitespacesAndNewlines().uppercase()
        return memos.detections.getOrPut(printed) { detectNormalized(number, printed) }
    }

    /**
     * The one carrier a number's shape and check digit name, when it is not the parcel's own and the reader
     * can choose it; nil when no single carrier claims the number.
     */
    fun claimingCarrier(trackingNumber: String, besides: CarrierId): CarrierId? {
        val match = detect(trackingNumber)
        if (match.confidence != CarrierMatch.Confidence.High || match.carrier == besides ||
            match.carrier == CarrierId.Unknown || match.carrier == CarrierId.InternationalPost ||
            !info(match.carrier).selectable
        ) return null
        return match.carrier
    }

    private class RuleMatch(val carrier: CarrierId, val confidence: CarrierMatch.Confidence, val preferred: Boolean)

    private fun detectNormalized(number: String, printed: String): CarrierMatch {
        val found = mutableListOf<RuleMatch>()
        for ((carrier, definition) in definitions) {
            for (rule in definition.detectionRules) {
                if (!matches(number, rule.pattern)) continue
                val rawPattern = rule.rawPattern
                if (rawPattern != null && !matches(printed, rawPattern)) continue
                var confidence = if (rule.confidence == "high") CarrierMatch.Confidence.High else CarrierMatch.Confidence.Low
                var preferred = rule.preferred == true
                val checksum = rule.checksum
                if (checksum != null) {
                    when (checksumPasses(checksum, number)) {
                        true -> Unit
                        false -> continue
                        null -> {
                            // A check this build cannot run proves nothing about a mistyped number: the shape
                            // still suggests the carrier, but never selects it or lists it first.
                            confidence = CarrierMatch.Confidence.Low
                            preferred = false
                        }
                    }
                }
                found += RuleMatch(carrier, confidence, preferred)
                break
            }
        }
        val high = found.filter { it.confidence == CarrierMatch.Confidence.High }
        val ranked = high.ifEmpty { found }
        // Number evidence first; the catalog's order is not the shared engine's, so sort each group.
        val preferred = ranked.filter { it.preferred }.map { it.carrier }.sortedBy { it.rawValue }
        val candidates = preferred + ranked.filter { !it.preferred }.map { it.carrier }.sortedBy { it.rawValue }
        if (high.size == 1) {
            return CarrierMatch(high[0].carrier, CarrierMatch.Confidence.High, candidates, preferred)
        }
        return CarrierMatch(
            carrier = CarrierId.Unknown,
            confidence = if (found.isEmpty()) CarrierMatch.Confidence.None else CarrierMatch.Confidence.Low,
            candidates = candidates,
            preferred = preferred,
        )
    }

    fun parse(raw: String): TrackingInputMatch {
        val input = raw.trimmingWhitespacesAndNewlines()
        if (input.isEmpty()) return emptyMatch
        return memos.parsedInputs.getOrPut(input) { parseTrimmed(input) }
    }

    /**
     * The number Add reads out of a text, as the front door reads it (`readText` in src/peek/lookup/reading.ts):
     * the parser's, or else the one number a message introduces though its shape proves no carrier.
     */
    fun read(raw: String): TrackingInputMatch {
        val parsed = parse(raw)
        if (parsed.trackingNumber.isNotEmpty()) return parsed
        return memos.readings.getOrPut(raw) { uncertainNumber(raw) ?: parsed }
    }

    /**
     * The shop whose order number the input is, read as the front door reads it: a number a carrier claims
     * with certainty wins; otherwise the number read, or the whole text, is tried. A message with a number of
     * its own beside the order number is that number's.
     */
    fun order(raw: String): OrderShop? = memos.orders.getOrPut(raw) {
        val match = read(raw)
        if (match.confidence == CarrierMatch.Confidence.High) null else orderShop(match.trackingNumber.nonEmpty ?: raw)
    }

    private class Candidate(val start: Int, val end: Int, val number: String)

    /**
     * Every number a text names with certainty, in the order written, when it names two or more: Add lists
     * them to choose from, as the front door does (`numbersInText` in src/peek/lookup/reading.ts). Links count
     * through their own number; each line is read on its own, so numbers on neighbouring lines never run
     * together.
     */
    fun numbers(raw: String): List<NumberInText> = memos.numbersRead.getOrPut(raw) {
        val found = mutableListOf<NumberInText>()
        fun keep(match: TrackingInputMatch) {
            val normalized = normalize(match.trackingNumber)
            if (normalized.isEmpty() || found.any { it.normalized == normalized }) return
            found += NumberInText(normalized, match)
        }
        for (line in raw.split("\n")) {
            for (link in matchesIn(line, linkPattern, caseInsensitive = true)) {
                val match = parse(link)
                if (match.trackingNumber.isNotEmpty()) keep(match)
            }
            val prose = blanking(linkPattern, line, caseInsensitive = true)
            val candidates = mutableListOf<Candidate>()
            for (shape in candidatePatterns) {
                for (hit in hits(prose, shape.pattern, shape.caseInsensitive)) {
                    val number = hit.text.trimmingWhitespacesAndNewlines()
                    if (detect(number).confidence != CarrierMatch.Confidence.High) continue
                    // A shape inside another certain one is the same number read twice.
                    val overlaps = candidates.any { hit.start < it.end && hit.end > it.start }
                    if (!overlaps) candidates += Candidate(hit.start, hit.end, number)
                }
            }
            for (candidate in candidates.sortedBy { it.start }) {
                keep(makeMatch(candidate.number, TrackingInputMatch.Source.Text))
            }
        }
        if (found.size > 1) found else emptyList()
    }

    private fun parseTrimmed(input: String): TrackingInputMatch {
        for (pasted in matchesIn(input, linkPattern, caseInsensitive = true)) {
            val cleaned = pasted.trimmingScalars { isWhitespaceOrNewline(it) || it in linkPunctuation }
            val components = UrlComponents.parse(cleaned) ?: continue
            val host = components.host?.lowercase() ?: continue
            val matchingRules = mutableListOf<Pair<CarrierId, CarrierDefinition.LinkRule>>()
            for ((carrier, definition) in definitions) {
                for (rule in definition.linkRules) {
                    if (rule.domains.none { host == it || host.endsWith(".$it") }) continue
                    val pathPattern = rule.pathPattern
                    if (pathPattern != null && matchesIn(components.path, pathPattern, caseInsensitive = true).isEmpty()) continue
                    matchingRules += carrier to rule
                }
            }
            fun specificity(rule: CarrierDefinition.LinkRule): Int =
                rule.domains.filter { host == it || host.endsWith(".$it") }.maxOfOrNull { it.characterCount } ?: 0
            matchingRules.sortWith { left, right ->
                val leftSpecificity = specificity(left.second)
                val rightSpecificity = specificity(right.second)
                if (leftSpecificity == rightSpecificity) left.first.rawValue.compareTo(right.first.rawValue)
                else rightSpecificity.compareTo(leftSpecificity)
            }
            for (firstMatch in matchingRules) {
                val rule = firstMatch.second
                val queryValue = components.queryItems?.firstOrNull { item ->
                    rule.params?.any { it.equalsFoldingCase(item.name) } == true
                }?.value
                val bareQueryValue = rule.query?.let { capture(components.query ?: "", it, caseInsensitive = true) }
                val pathValue = rule.path?.let { capture(components.path, it, caseInsensitive = true) }
                val fragmentValue = rule.fragment?.let { capture(components.fragment ?: "", it, caseInsensitive = true) }
                val candidate = (queryValue ?: bareQueryValue ?: pathValue ?: fragmentValue ?: "")
                    .split(',', '|').firstOrNull { it.isNotEmpty() } ?: ""
                if (valid(candidate)) {
                    val detected = detect(candidate)
                    val suggested = matchingRules.filter { it.first in detected.candidates }
                    val uniqueSuggestions = suggested.map { it.first }.toSet()
                    if (rule.detectFromNumber == true &&
                        (detected.confidence == CarrierMatch.Confidence.High ||
                            (detected.confidence == CarrierMatch.Confidence.Low && uniqueSuggestions.size > 1))
                    ) {
                        return makeMatch(candidate, TrackingInputMatch.Source.Link)
                    }
                    val selected = if (detected.confidence == CarrierMatch.Confidence.High) {
                        matchingRules.firstOrNull { it.first == detected.carrier } ?: firstMatch
                    } else {
                        if (uniqueSuggestions.size == 1) suggested[0] else firstMatch
                    }
                    if (selected.second.ownNumbersOnly == true && selected.first !in detected.candidates) {
                        return makeMatch(candidate, TrackingInputMatch.Source.Link)
                    }
                    // A host that is also the number's carrier's own page, as Royal Mail's is Parcelforce's,
                    // names that carrier.
                    val carrier = if (detected.confidence == CarrierMatch.Confidence.High &&
                        coversTrackingPage(selected.second, detected.carrier)
                    ) detected.carrier else selected.first
                    return TrackingInputMatch(
                        trackingNumber = candidate,
                        carrier = carrier,
                        confidence = CarrierMatch.Confidence.High,
                        candidates = listOf(carrier),
                        trackingUrl = if (selected.second.keepsCapabilityUrl == true) cleaned else null,
                        source = TrackingInputMatch.Source.Link,
                    )
                }
            }
            val decoded = cleaned.removingPercentEncoding() ?: cleaned
            recognizedNumber(decoded)?.let { return makeMatch(it, TrackingInputMatch.Source.Link) }
        }

        // No part of a shop's order number is read as a tracking number.
        val prose = withoutOrders(input)
        recognizedNumber(prose)?.let { recognized ->
            return makeMatch(recognized, if (input == recognized) TrackingInputMatch.Source.Number else TrackingInputMatch.Source.Text)
        }

        capture(prose, labelledNumberPattern, caseInsensitive = true)?.let { labelled ->
            // A sentence can end right after its number.
            val keyword = foundationReplacing(labelled, "[.-]+$", "")
            if (validInText(keyword)) return makeMatch(keyword, TrackingInputMatch.Source.Text)
        }
        numberEnteredWhole(input)?.let { whole ->
            return makeMatch(whole.number, if (whole.labelled) TrackingInputMatch.Source.Text else TrackingInputMatch.Source.Number)
        }
        return emptyMatch
    }

    private class WholeNumber(val number: String, val labelled: Boolean)

    /**
     * The number a short entry holds whole, groups a label prints apart included, as in
     * "1Z 999 AA1 01 2345 6784", as the shared engine reads it (`numberEnteredWhole`). A word offering a
     * second number leaves it unread, and a label word before the digits is no part of it, unless a carrier
     * confidently claims the two together.
     */
    private fun numberEnteredWhole(input: String): WholeNumber? {
        if (input.contains("://")) return null
        val alternatives = mutableListOf("")
        for (word in input.splitOnWhitespace()) {
            if (word.lowercase() in alternativeWords) {
                alternatives += ""
            } else {
                alternatives[alternatives.size - 1] += word
            }
        }
        if (alternatives.count { matches(it, "[0-9]") } > 1) return null
        val number = capture(input, entryLabelPattern, caseInsensitive = true)
        if (number != null && detect(input).confidence != CarrierMatch.Confidence.High && valid(number)) {
            return WholeNumber(number, true)
        }
        return if (valid(input)) WholeNumber(input, false) else null
    }

    /**
     * A number in prose whose shape fits a carrier without proving one: "Track it with DHL: 1234567899". The
     * parser leaves these alone, since a phone number can look the same; Add takes one when the text holds no
     * other candidate, or when only one stands on a line that speaks of tracking, and the carriers are then
     * asked. A candidate holds a digit: some shapes also fit a word, and a word in a message is not a number.
     */
    private fun uncertainNumber(text: String): TrackingInputMatch? {
        val candidates = LinkedHashMap<String, Pair<String, Boolean>>()
        for (line in text.split("\n")) {
            val prose = withoutOrders(blanking(linkPattern, line, caseInsensitive = true))
            val introduced = numberWords?.let { matches(prose, it, caseInsensitive = true) } ?: false
            for (shape in candidatePatterns) {
                for (hit in matchesIn(prose, shape.pattern, shape.caseInsensitive)) {
                    val number = hit.trimmingWhitespacesAndNewlines()
                    if (!matches(number, "[0-9]") || detect(number).confidence != CarrierMatch.Confidence.Low) continue
                    val normalized = normalize(number)
                    candidates[normalized] = number to (introduced || candidates[normalized]?.second == true)
                }
            }
        }
        val introduced = candidates.values.filter { it.second }
        val picked = when {
            candidates.size == 1 -> candidates.values.first()
            introduced.size == 1 -> introduced.first()
            else -> return null
        }
        return makeMatch(picked.first, TrackingInputMatch.Source.Text)
    }

    /** The followed carrier's pages first, and then the named delivering carrier's, for its own number. */
    fun trackingLinks(parcel: Parcel, language: AppLanguage): List<ParcelTrackingLink> {
        val links = followedTrackingLinks(parcel, language)
        val named = parcel.namedDelivery ?: return links
        val number = named.number ?: return links
        val delivery = parcel.copy(carrier = named.carrier, trackingNumber = number, trackingUrl = null, carrierData = null)
        // The parcel is not followed there yet.
        return links + followedTrackingLinks(delivery, language).filter { it.carrier == named.carrier }
            .map { it.copy(role = ParcelTrackingLink.Role.Waiting) }
    }

    /** Where a link that hides the number leads: the carrier's page for typing one in, or nowhere. */
    fun searchLink(parcel: Parcel, language: AppLanguage): ParcelTrackingLink? {
        val carrier = parcel.activeTrackingCarrier
        val definition = info(carrier, language)
        val raw = definition.trackingSearchUrl ?: return null
        val url = localizedUrl(raw, carrier, language) ?: return null
        return ParcelTrackingLink(carrier, definition.trackingSiteName ?: definition.displayName, url, ParcelTrackingLink.Role.Active)
    }

    private fun followedTrackingLinks(parcel: Parcel, language: AppLanguage): List<ParcelTrackingLink> {
        if (requiresAmazonAccount(parcel.carrier, parcel.trackingNumber)) {
            return listOf(
                ParcelTrackingLink(
                    carrier = CarrierId.AmazonLogistics,
                    name = info(CarrierId.AmazonLogistics, language).displayName,
                    url = amazonOrdersUrl(parcel.trackingNumber, parcel.carrierData?.destinationCountry),
                    role = ParcelTrackingLink.Role.Active,
                ),
            )
        }
        val links = carrierTrackingLinks(parcel, language)
        val data = parcel.carrierData
        val lookupNumber = if (data?.originalCarrier != null && data.activeTrackingCarrier != null) {
            data.activeTrackingNumber?.nonEmpty ?: parcel.trackingNumber
        } else {
            parcel.trackingNumber
        }
        val number = urlEncode(lookupNumber)
        // A carrier that answered the same check keeps the link, even when its clock sent the result elsewhere.
        val provider = data?.trackingProvider
        if (provider == null || data.carrierAnswered == true) return links
        val raw = when (provider) {
            "17TRACK" -> "https://t.17track.net/en#nums=$number"
            "ParcelsApp" -> "https://parcelsapp.com/en/tracking/$number"
            "Ship24" -> "https://www.ship24.com/tracking?p=$number"
            "UPU" -> "https://globaltracktrace.ptc.post/gtt.web/Search.aspx"
            // Public entry point, without guessing a private/session-specific URL.
            "Postal Ninja" -> "https://postal.ninja/en/track"
            else -> return links
        }
        val url = localizedUrl(raw, CarrierId.Unknown, language) ?: return links
        val primary = ParcelTrackingLink(CarrierId.Unknown, provider, url, ParcelTrackingLink.Role.Active)
        return listOf(primary) + links.filter { it.role != ParcelTrackingLink.Role.Active && it.url != url }
    }

    private fun carrierTrackingLinks(parcel: Parcel, language: AppLanguage): List<ParcelTrackingLink> {
        val data = parcel.carrierData
        val originalCarrier = data?.originalCarrier
        val originalNumber = data?.originalTrackingNumber?.nonEmpty
        if (originalCarrier != null && originalNumber != null) {
            var delivery = parcel.copy(
                carrier = parcel.activeTrackingCarrier,
                trackingNumber = data.activeTrackingNumber?.nonEmpty ?: parcel.trackingNumber,
                carrierData = null,
            )
            if (delivery.carrier != parcel.carrier || delivery.trackingNumber != parcel.trackingNumber) {
                delivery = delivery.copy(trackingUrl = null)
            }
            val original = delivery.copy(carrier = originalCarrier, trackingNumber = originalNumber, trackingUrl = data.originalTrackingUrl)
            val history = trackingLinks(original, language).map { it.copy(role = ParcelTrackingLink.Role.History) }
            return trackingLinks(delivery, language) + history
        }
        if (!supportsSwissPostHandoff(parcel.trackingNumber)) {
            val carrier = parcel.activeTrackingCarrier
            val number = data?.activeTrackingNumber?.nonEmpty ?: parcel.trackingNumber
            val definition = info(carrier, language)
            // Replace obsolete generated links saved by earlier app versions.
            val savedPostNLUrl = parcel.trackingUrl?.let { UrlComponents.parse(it) }
            // `URL.path` drops the slashes that end the path.
            val obsoletePostNLLink = parcel.carrier == CarrierId.SpringGDS &&
                savedPostNLUrl?.host?.lowercase() == "postnl.post" &&
                savedPostNLUrl.path.trimEnd('/').startsWith("/details/")
            val savedUrl = if (requiresAmazonAccount(carrier, number) || carrier == CarrierId.InternationalPost ||
                obsoletePostNLLink || carrier != parcel.carrier || number != parcel.trackingNumber
            ) null else parcel.trackingUrl
            val raw = savedUrl ?: renderTrackingUrl(definition.trackingUrlTemplate, carrier, number) ?: return emptyList()
            val url = localizedUrl(raw, carrier, language) ?: return emptyList()
            return listOf(
                ParcelTrackingLink(carrier, definition.trackingSiteName ?: definition.displayName, url, ParcelTrackingLink.Role.Active),
            )
        }

        val active = parcel.activeTrackingCarrier
        val ready = parcel.swissPostReady || active == CarrierId.SwissPost
        return listOf(CarrierId.Aliexpress, CarrierId.SwissPost).mapNotNull { carrier ->
            val definition = info(carrier, language)
            val template = definition.trackingUrlTemplate ?: return@mapNotNull null
            val url = localizedUrl(template.replace("{trackingNumber}", urlEncode(parcel.trackingNumber)), carrier, language)
                ?: return@mapNotNull null
            val role = when {
                carrier == active -> ParcelTrackingLink.Role.Active
                carrier == CarrierId.SwissPost && !ready -> ParcelTrackingLink.Role.Waiting
                else -> ParcelTrackingLink.Role.History
            }
            ParcelTrackingLink(carrier, definition.trackingSiteName ?: definition.displayName, url, role)
        }.sortedBy { if (it.role == ParcelTrackingLink.Role.Active) 0 else 1 }
    }

    /** The first candidate in the text that one carrier claims with certainty. */
    private fun recognizedNumber(text: String): String? {
        for (shape in candidatePatterns) {
            for (candidate in matchesIn(text, shape.pattern, shape.caseInsensitive)) {
                if (detect(candidate).confidence == CarrierMatch.Confidence.High) return candidate.trimmingWhitespaces()
            }
        }
        return null
    }

    /** The rule's link covers the host of the carrier's own tracking page. */
    private fun coversTrackingPage(rule: CarrierDefinition.LinkRule, carrier: CarrierId): Boolean {
        val template = definitions[carrier]?.trackingUrlTemplate ?: return false
        val host = UrlComponents.parse(template.replace("{trackingNumber}", ""))?.host?.lowercase() ?: return false
        return rule.domains.any { host == it || host.endsWith(".$it") }
    }

    private fun makeMatch(number: String, source: TrackingInputMatch.Source): TrackingInputMatch {
        val result = detect(number)
        return TrackingInputMatch(
            trackingNumber = number,
            carrier = result.carrier,
            confidence = result.confidence,
            candidates = result.candidates,
            trackingUrl = null,
            source = source,
        )
    }

    private fun localizedUrl(raw: String, carrier: CarrierId, language: AppLanguage): String? {
        val components = UrlComponents.parse(raw) ?: return null
        if (carrier == CarrierId.SwissPost) {
            val siteLanguage = if (language in listOf(AppLanguage.En, AppLanguage.De, AppLanguage.Fr, AppLanguage.It)) language else AppLanguage.En
            components.queryItems = (components.queryItems ?: emptyList()).filter { it.name != "lang" } +
                UrlComponents.QueryItem("lang", siteLanguage.rawValue)
        } else if (components.host == "t.17track.net" || components.host == "parcelsapp.com") {
            // Parcels has no Polish page; 17TRACK supports all app languages.
            val siteLanguage = if (components.host == "parcelsapp.com" && language == AppLanguage.Pl) AppLanguage.En else language
            components.path = foundationReplacing(components.path, "^/[a-z]{2}(?=/|$)", "/${siteLanguage.rawValue}")
        }
        return components.url
    }

    /** A postal number (2 letters, 9 digits, 2 letters) whose check digit doesn't add up, as the front door
     * reads it (`checkDigitTrouble` in src/peek/lookup/reading.ts). A letter standing where a digit belongs has
     * one likely correction; a wrong digit has many, each as likely as the next, so none is offered. Carriers
     * that reuse the shape without the check digit exist, so this is a question, never a refusal. */
    fun checkDigitTrouble(number: String): CheckDigitTrouble? {
        val value = normalize(number)
        if (!matches(value, "^[A-Z0-9]{13}$") || isValidS10(value) || detect(value).confidence == CarrierMatch.Confidence.High) {
            return null
        }
        val postal = StringBuilder()
        for ((index, character) in value.withIndex()) {
            val digit = character in '0'..'9'
            val read = if (index in 2..10) {
                if (digit) character.toString() else digitForLetter[character.toString()]
            } else {
                if (digit) letterForDigit[character.toString()] else character.toString()
            }
            postal.append(read ?: return null)
        }
        if (postal.toString() == value) return CheckDigitTrouble(suggestion = null)
        return if (isValidS10(postal.toString())) CheckDigitTrouble(suggestion = postal.toString()) else null
    }

    /** A number entered whole or carried by a carrier's link. Without a digit it must be six to ten unbroken
     * letters that a carrier's detection rule claims, as GLS issues six-letter Track IDs: a word no carrier
     * uses is not a number. */
    private fun valid(raw: String): Boolean {
        val value = normalize(raw)
        if (!shaped(value)) return false
        if (matches(value, "\\d")) return true
        return matches(raw.trimmingWhitespacesAndNewlines(), "^[A-Za-z]{6,10}$") &&
            detect(value).confidence != CarrierMatch.Confidence.None
    }

    private fun saveCache(carriers: Map<String, CarrierDefinition>, etag: String?) {
        val cacheFile = cacheFile ?: return
        try {
            val record = buildMap<String, JsonElement> {
                etag?.let { put("etag", JsonPrimitive(it)) }
                put("contract", JsonObject(mapOf("x-carriers" to encodeCarriers(carriers))))
            }
            val directory = cacheFile.absoluteFile.parentFile
            directory.mkdirs()
            val temporary = File.createTempFile(".${cacheFile.name}", ".tmp", directory)
            try {
                temporary.writeText(JsonObject(record).toString())
                Files.move(temporary.toPath(), cacheFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
            } finally {
                temporary.delete()
            }
        } catch (_: Exception) {
            // The catalog in memory is current; the next launch reads the bundled one or an older cache.
        }
    }

    /** Swift's `(shop:, pattern:)`. */
    data class OrderNumber(val shop: OrderShop, val pattern: String)

    /** Swift's `(pattern:, caseInsensitive:)`. */
    data class CandidatePattern(val pattern: String, val caseInsensitive: Boolean)

    /** How the front door reads a text (`shared/reading.json`), with the shapes of a number in a text that
     * the shared engine tries, in its order. */
    @Serializable
    private class ReadingRules(
        val shops: List<Shop>,
        val numberWords: String,
        val lookalikes: Lookalikes,
        val candidates: List<Shape>,
    ) {
        @Serializable
        class Shop(val shop: OrderShop, val pattern: String)

        @Serializable
        class Shape(val source: String, val flags: String)

        @Serializable
        class Lookalikes(val digitForLetter: Map<String, String>, val letterForDigit: Map<String, String>)
    }

    companion object {
        private const val MEMO_LIMIT = 4_096
        private val refreshInterval: Duration = Duration.ofMinutes(15)

        /** Far above the catalog, which grows with each scraper release: a larger answer is refused and the
         * app keeps the catalog it has. */
        const val maximumResponseBytes = 4 * 1_024 * 1_024

        @Volatile private var installed: CarrierCatalog? = null

        /** The app's catalog: the bundled one until the app installs one with its cache and loader. */
        val shared: CarrierCatalog
            get() = installed ?: synchronized(this) { installed ?: CarrierCatalog().also { installed = it } }

        fun install(catalog: CarrierCatalog) {
            installed = catalog
        }

        /** Where the app keeps the refreshed catalog in its caches directory. The cache is re-encoded from the
         * decoded classes, so a file written by a build that did not know a catalog field has lost it. Bump
         * the name when one is added. */
        fun cacheFile(cachesDirectory: File): File =
            File(File(cachesDirectory, "delivery-tracker"), "carrier-catalog-v5.json")

        /** Carriers the detect route asks at once. */
        const val maximumRecognitions = 5

        /** Brands with several catalog networks ("DPD" is `dpd` and `dpd-fr`), as in Universal Parcel
         * Scraper's catalog networks. */
        private val networkBrands = listOf("dhl", "dpd", "gls", "hermes", "evri", "gofo")

        fun networkBrand(carrier: CarrierId): String? =
            networkBrands.firstOrNull { carrier.rawValue == it || carrier.rawValue.startsWith("$it-") }

        // Amazon sends visitors from a European country without a store of its own to amazon.de (Iceland's
        // to amazon.co.uk), so its parcels' orders are there.
        private val amazonStores = mapOf(
            "FR" to "fr", "DE" to "de", "AT" to "de", "BE" to "com.be", "UK" to "co.uk", "GB" to "co.uk",
            "IT" to "it", "ES" to "es", "PT" to "es", "NL" to "nl", "IE" to "ie", "PL" to "pl", "SE" to "se", "TR" to "com.tr",
            "CH" to "de", "LI" to "de", "LU" to "de", "DK" to "de", "NO" to "de", "FI" to "de", "CZ" to "de", "HU" to "de",
            "RO" to "de", "HR" to "de", "SI" to "de", "EE" to "de", "LV" to "de", "LT" to "de", "GR" to "de", "IS" to "co.uk",
        )

        /** The store whose order history holds the parcel. TBA names no country and amazon.ca orders ship
         * under it too, so the delivery country decides there, and when there is no number. */
        fun amazonMarketplace(number: String, country: String? = null): String {
            val normalized = normalize(number)
            val destination = (country ?: "").trimmingWhitespaces().uppercase()
            if (normalized.startsWith("TBC")) return "ca"
            if (normalized.startsWith("TBM")) return "com.mx"
            if (normalized.startsWith("TBA")) return if (destination == "CA") "ca" else "com"
            if (normalized.isEmpty()) {
                return mapOf("CA" to "ca", "MX" to "com.mx")[destination] ?: amazonStores[destination] ?: "com"
            }
            return amazonStores[normalized.characterPrefix(2)] ?: "com"
        }

        fun amazonOrdersUrl(number: String, country: String? = null): String =
            "https://www.amazon.${amazonMarketplace(number, country)}/gp/your-account/order-history"

        fun amazonShippingUrl(number: String): String {
            val normalized = normalize(number)
            val domains = mapOf("IT" to "it", "ES" to "es", "UK" to "co.uk", "GB" to "co.uk", "TB" to "com")
            val domain = domains[normalized.characterPrefix(2)] ?: "fr"
            return "https://track.amazon.$domain/tracking/${urlEncode(normalized)}"
        }

        /** One postcode per country, shown as the example in a field that takes any country's. */
        val postcodeExamples: Map<String, String> by lazy {
            runCatching {
                ApiJson.decodeFromString(
                    MapSerializer(String.serializer(), String.serializer()),
                    SharedResources.text("PostcodeExamples.json"),
                )
            }.getOrElse { emptyMap() }
        }

        /** Every country a delivery postcode can be from: ISO 3166-1's, and Kosovo, as the service takes them.
         * Apple's regions also name unions, groupings and reserved codes, which are left out. */
        val countries: List<String> by lazy {
            val others = setOf("AC", "CP", "CQ", "DG", "EA", "EU", "EZ", "IC", "QO", "TA", "UN", "XA", "XB", "ZZ")
            TrackingLocation.regions.filter { code ->
                code.length == 2 && code.all { it in 'A'..'Z' } && code !in others
            }.sorted()
        }

        private val countryOrders = ConcurrentHashMap<AppLanguage, List<String>>()

        /** Every country, by its name in the language. A menu of them is drawn again with each keystroke
         * beside it. */
        fun countriesByName(language: AppLanguage): List<String> = countryOrders.getOrPut(language) {
            val names = countries.associateWith { TrackingLocation.countryName(it, language) }
            val comparator = LocalizedComparison.Plain.comparator(language.locale)
            countries.sortedWith { left, right -> comparator.compare(names[left] ?: left, names[right] ?: right) }
        }

        private fun currentRegion(): String? = Locale.getDefault().country.nonEmpty

        private fun carriersUrl(apiBaseUrl: String): String =
            if (apiBaseUrl.endsWith("/")) "${apiBaseUrl}api/carriers" else "$apiBaseUrl/api/carriers"

        /** Words that offer a second number: "1234567899 or 9987654321" holds two. */
        private val alternativeWords = setOf("or", "oder", "ou", "o", "of", "lub")

        /** A word naming the code after it, alone or after another word ("Pickup code"), and the rest of the
         * entry. German and Dutch join theirs into one word: Abholcode, Sendungsnummer. French, Spanish,
         * Italian and Portuguese name what the number tracks after a preposition ("N° de suivi", "número de
         * seguimiento", "numero di tracciamento"), Polish after the word ("numer przesyłki"), and the tracking
         * word can stand alone ("Suivi"). "N°" can touch its digits. */
        private val entryLabelPattern: String = run {
            val tracked = "suivi|seguimiento|tracciamento|rastreio|rastreamento|tracking"
            "^(?:\\p{L}+\\s+)?(?:(?:\\p{L}*(?:code|nummer)|number|numer|numero|número|numéro|nr|n\\.?[°º]|kod|codice|codigo|código)" +
                "(?:\\s+(?:(?:de|du|del|di|do|da)\\s+(?:$tracked|colis|envío|envio|spedizione)|przesyłki|paczki))?|$tracked)" +
                "(?:[.:#\\s]+|(?<=[°º]))(?=[0-9])([\\s\\S]*)"
        }

        /** The characters a link pasted in prose can carry around it. */
        private val linkPunctuation = "<>'\"()[],;.!?".map { it.code }.toSet()

        /** JavaScript's `\s`, which the shared engine strips. ICU's `\s` misses U+FEFF, which copied text can
         * carry, and adds U+0085. */
        private const val separatorPattern =
            "[\\t\\n\\x{0B}\\f\\r\\x{20}\\x{A0}\\x{1680}\\x{2000}-\\x{200A}\\x{2028}\\x{2029}\\x{202F}\\x{205F}\\x{3000}\\x{FEFF}.-]"

        /** Capitals without separators. A label prints its SSCC behind the bracketed GS1 identifier,
         * "(00) 3 7012345 678901234 7", which carriers track as the twenty digits without brackets; other
         * bracketed identifiers stay as typed, since (420), for one, carries a ZIP. A NO LIMIT shipment number,
         * P/1234/LABC/2026, reads the same without its slashes, which are dropped. */
        fun normalize(raw: String): String {
            val value = raw.uppercase()
            val compact = CatalogExpressions.replacing(separatorPattern, value, "") ?: return value
            if (matches(compact, "^\\(00\\)[0-9]{18}$")) return "00" + compact.substring(4)
            if ('/' in compact) noLimitShipmentNumber(compact)?.let { return it }
            return compact
        }

        /** A NO LIMIT shipment number without its slashes (`noLimitShipmentNumber`): P and a serial, L and the
         * branch's three letters, and the year. */
        private fun noLimitShipmentNumber(value: String): String? {
            val matcher = CatalogExpressions.expression("^P/?([0-9]{1,6})/?L([A-Z]{3})/?(20[0-9]{2})$")?.matcher(value)
            if (matcher == null || !matcher.find()) return null
            return "P${matcher.group(1)}L${matcher.group(2)}${matcher.group(3)}"
        }

        /** Six digits and nothing else, normalized: the code that opens a parcel locker or collects a parcel at
         * a pickup point, which no carrier's detection claims. Only a carrier picked by hand takes one as its
         * number. */
        fun isPickupCode(normalized: String): Boolean = matches(normalized, "^[0-9]{6}$")

        private val readingRules: ReadingRules? by lazy {
            runCatching { ApiJson.decodeFromString(ReadingRules.serializer(), SharedResources.text("Reading.json")) }.getOrNull()
        }

        /** Each shop's order number, tried in turn and read without regard to case: the front door's patterns. */
        val orderNumbers: List<OrderNumber> by lazy {
            readingRules?.shops?.map { OrderNumber(it.shop, it.pattern) } ?: emptyList()
        }

        /** The shapes of a number in a text, as the shared engine tries them (`TRACKING_CANDIDATE_PATTERNS`). */
        val candidatePatterns: List<CandidatePattern> by lazy {
            readingRules?.candidates?.map { CandidatePattern(it.source, it.flags.contains("i")) } ?: emptyList()
        }

        /** Words that introduce a tracking number in the app's languages. */
        private val numberWords: String? by lazy { readingRules?.numberWords }

        private const val linkPattern = "https?://[^\\s<>\\\"']+"

        /** Letters a scanner or a tired eye reads where a label prints a digit, and the other way around. */
        private val digitForLetter: Map<String, String> by lazy { characters(readingRules?.lookalikes?.digitForLetter) }
        private val letterForDigit: Map<String, String> by lazy { characters(readingRules?.lookalikes?.letterForDigit) }

        private fun characters(map: Map<String, String>?): Map<String, String> = buildMap {
            for ((key, value) in map ?: emptyMap()) {
                val first = key.characters().firstOrNull() ?: continue
                put(first, value.characters().firstOrNull() ?: continue)
            }
        }

        /** The shop whose order number a text holds. */
        fun orderShop(text: String): OrderShop? =
            orderNumbers.firstOrNull { matches(text, it.pattern, caseInsensitive = true) }?.shop

        /** The text without its order numbers, so that no part of one is read as a tracking number. */
        private fun withoutOrders(text: String): String =
            orderNumbers.fold(text) { rest, order -> blanking(order.pattern, rest, caseInsensitive = true) }

        /** The text with a space for each match of the pattern. */
        private fun blanking(pattern: String, text: String, caseInsensitive: Boolean = false): String =
            CatalogExpressions.replacing(pattern, text, " ", caseInsensitive) ?: text

        fun format(raw: String, carrier: CarrierId? = null): String {
            val value = normalize(raw)
            if (carrier == CarrierId.Postlogistics) {
                val printed = raw.trimmingWhitespacesAndNewlines()
                if (matches(printed, "^[0-9]{11}$")) return "${printed.take(8)}-${printed.takeLast(3)}"
                return printed
            }
            val characters = value.characters()
            fun slice(from: Int, to: Int = characters.size) = characters.subList(from, to).joinToString("")
            if (matches(value, "^99990\\d{8}$")) return "${slice(0, 3)}.${slice(3, 5)}.${slice(5)}"
            if (matches(value, "^\\d{18}$")) return "${slice(0, 2)}.${slice(2, 4)}.${slice(4, 10)}.${slice(10)}"
            return value
        }

        /** A number introduced by a "tracking number:" style label, as the web engine reads it. "No" and "ID"
         * also start numbers, so they belong to the label only when they stand apart from what follows. The
         * token holds a digit, so a label followed by a plain word does not end the search. */
        private val labelledNumberPattern: String = run {
            val suffix = "(?:numbers?(?![A-Z])|no\\.|(?:no|id)(?![A-Z0-9.]))"
            "(?:track(?:ing)?(?:\\s*$suffix|(?![A-Z]))" +
                "|(?:parcel|shipment)(?:\\s+(?:tracking(?![A-Z])|$suffix)|(?![A-Z])))" +
                "(?:\\s+is(?![A-Z0-9]))?\\s*[:#-]?\\s*" +
                "((?=[A-Z0-9.-]{0,39}\\d)[A-Z0-9][A-Z0-9.-]{3,39})"
        }

        private fun shaped(value: String): Boolean =
            value.characterCount in 4..40 && (matches(value, "^[A-Z0-9]+$") || matches(value, "^\\d{4}/\\d{8}$"))

        /** A number pulled out of prose always holds a digit: a word there is not a number. */
        private fun validInText(raw: String): Boolean {
            val value = normalize(raw)
            return shaped(value) && matches(value, "\\d")
        }

        private fun urlEncode(value: String): String = value.addingPercentEncoding(UrlComponents.URL_QUERY_ALLOWED)

        private fun renderTrackingUrl(template: String?, carrier: CarrierId, trackingNumber: String): String? {
            if (carrier == CarrierId.AmazonLogistics) return amazonOrdersUrl(trackingNumber)
            if (carrier == CarrierId.AmazonShipping) return amazonShippingUrl(trackingNumber)
            val normalized = normalize(trackingNumber)
            // The country in these families chooses Ninja Van's lookup page, not the destination.
            if (carrier == CarrierId.NinjaVan) {
                var country = "my"
                if (foundationMatches(normalized, "^NJVTT[0-9]{11}$")) {
                    country = "id"
                } else if (foundationMatches(
                        normalized,
                        "^(?:NL(SG|MY|ID|PH|TH|VN)[A-Z]{1,2}[0-9]{8,10}|NV(SG|MY|ID|PH|TH|VN)(?!STAMP[A-Z0-9]{9}$)[A-Z0-9]{8,20})$",
                    )
                ) {
                    country = normalized.drop(2).take(2).lowercase()
                }
                val host = if (country == "id") "www.ninjaxpress.co" else "www.ninjavan.co"
                return "https://$host/en-$country/tracking?id=${urlEncode(normalized)}"
            }
            if (template == null) return null
            var linkNumber = trackingNumber
            if (carrier == CarrierId.MondialRelay && isValidMondialRelayBarcode(normalized)) linkNumber = normalized.take(12)
            if (carrier == CarrierId.CChezVous && foundationMatches(normalized, "^[A-Z0-9]{11}[0-9]{5}$")) {
                linkNumber = "${normalized.take(11)}--${normalized.drop(11)}"
            }
            if (carrier == CarrierId.TheCourierGuy && foundationMatches(normalized, "^(DD|LD)[A-Z0-9]{6}$")) {
                linkNumber = "${normalized.take(2)}-${normalized.drop(2)}"
            }
            if (carrier == CarrierId.Postlogistics) linkNumber = format(trackingNumber, carrier)
            // CDL's page finds an order number only with the hyphen before its last digit.
            if (carrier == CarrierId.Cdl && foundationMatches(normalized, "^[0-9]{10}$")) {
                linkNumber = "${normalized.take(9)}-${normalized.takeLast(1)}"
            }
            // GOFO France's page keeps a shipper reference's hyphens.
            if (carrier == CarrierId.GofoFr) {
                linkNumber = if (foundationMatches(normalized, "^PK[0-9]{20}$")) {
                    "PK-${normalized.drop(2).dropLast(1)}-${normalized.takeLast(1)}"
                } else {
                    normalized
                }
            }
            // Ecoscooting's page finds a Cainiao reference only with its CN.
            if (carrier == CarrierId.Ecoscooting &&
                foundationMatches(normalized, "^(?:ESP[0-9]{5}1234[0-9]{11}|PRT[0-9]{7}1234[0-9]{9})$")
            ) {
                linkNumber = "CN$normalized"
            }
            // InPost's Polish page does not find Yodel's layouts; InPost UK's page does.
            if (carrier == CarrierId.Inpost &&
                foundationMatches(normalized, "^(J?JD[0-9]{16}|8YDR[0-9]{9}|[0-9]{2}[A-Z]{3}[0-9]{7}A[0-9]{3})$")
            ) {
                return "https://inpost.co.uk/tracking/result?parcel_code=${urlEncode(normalized)}"
            }
            return template.replace("{trackingNumber}", urlEncode(linkNumber))
        }

        private fun bundledDefinitions(): Map<CarrierId, CarrierDefinition> = try {
            val data = SharedResources.open("CarrierCatalog.json").use { it.readBytes() }
            validatedDefinitions(decodeContract(data).carriers)
        } catch (_: Exception) {
            fallbackDefinitions
        }

        private fun cachedContract(file: File?): Initial? {
            file ?: return null
            return try {
                val record = parseObject(file.readBytes())
                val etag = when (val value = record["etag"]) {
                    null, JsonNull -> null
                    is JsonPrimitive -> if (value.isString) value.content else throw CatalogException()
                    else -> throw CatalogException()
                }
                val contract = contract(record.getValue("contract").jsonObject)
                Initial(validatedDefinitions(contract.carriers), etag)
            } catch (_: Exception) {
                null
            }
        }

        /** The bytes as JSON text: a catalog that is not UTF-8 is no catalog. */
        private fun parseObject(data: ByteArray): JsonObject {
            val text = Charsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT)
                .decode(ByteBuffer.wrap(data)).toString()
            return ApiJson.parseToJsonElement(text).jsonObject
        }

        private fun decodeContract(data: ByteArray): Contract = contract(parseObject(data))

        /** One carrier at a time: a newer catalog may write one in a way this build cannot read. */
        private fun contract(json: JsonObject): Contract {
            val entries = json.getValue("x-carriers").jsonObject
            val carriers = LinkedHashMap<String, CarrierDefinition>()
            val unreadable = LinkedHashSet<String>()
            for ((key, entry) in entries) {
                try {
                    carriers[key] = ApiJson.decodeFromJsonElement(CarrierDefinition.serializer(), entry)
                } catch (_: Exception) {
                    unreadable += key
                }
            }
            return Contract(carriers, unreadable)
        }

        private fun encodeCarriers(carriers: Map<String, CarrierDefinition>): JsonObject =
            JsonObject(carriers.mapValues { ApiJson.encodeToJsonElement(CarrierDefinition.serializer(), it.value) })

        private fun validatedDefinitions(carriers: Map<String, CarrierDefinition>): Map<CarrierId, CarrierDefinition> {
            val definitions = LinkedHashMap<CarrierId, CarrierDefinition>()
            for ((key, value) in carriers) {
                if (matches(key, "^[a-z0-9]+(?:-[a-z0-9]+)*$") && key.characterCount <= 64) definitions[CarrierId(key)] = value
            }
            if (definitions[CarrierId.Unknown] == null || definitions.values.none { it.selectable }) throw CatalogException()
            return definitions
        }

        val emptyMatch = TrackingInputMatch(
            trackingNumber = "",
            carrier = CarrierId.Unknown,
            confidence = CarrierMatch.Confidence.None,
            candidates = emptyList(),
            trackingUrl = null,
            source = TrackingInputMatch.Source.None,
        )

        private val fallbackDefinitions: Map<CarrierId, CarrierDefinition> by lazy {
            mapOf(
                CarrierId.Unknown to CarrierDefinition(
                    displayName = "Unknown carrier",
                    displayNames = mapOf(
                        "en" to "Unknown carrier", "de" to "Paketdienst unbekannt",
                        "fr" to "Transporteur inconnu", "it" to "Corriere sconosciuto",
                    ),
                    trackingSiteName = null,
                    color = CarrierVisualIdentity.defaultCarrierColor,
                    selectable = false,
                    timezone = "UTC",
                    tracking = CarrierDefinition.Tracking(mode = "link-only", adapter = null, requirements = null),
                    trackingUrlTemplate = null,
                    linkRules = emptyList(),
                    detectionRules = emptyList(),
                ),
            )
        }
    }
}
