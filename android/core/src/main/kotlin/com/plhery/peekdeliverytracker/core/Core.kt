package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.io.IOException
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.chrono.IsoChronology
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.util.Locale
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

// Core.swift without what is the platform's: DeviceIdiom, Clipboard and Haptics are the app's. The JSONDecoder settings
// are ApiJson; `JsonEncoder` writes the bytes Swift's JSONEncoder does.

data class AppConfiguration(
    val mode: Mode,
    val apiBaseUrl: String,
    val supabaseUrl: String?,
    val supabasePublishableKey: String,
    val googleAuthEnabled: Boolean,
    val appleAuthEnabled: Boolean,
    val emailOtpEnabled: Boolean,
    val appGroupIdentifier: String,
    /** Hosts the site answered on before. Their links still open here; nothing is sent to them. */
    val linkHosts: List<String> = emptyList(),
    val oidcProvider: String = "",
    val oidcName: String = "",
) {
    enum class Mode { Demo, Api }

    val authenticationConfigured: Boolean
        get() = supabaseUrl != null && supabasePublishableKey.isNotEmpty() && oidcConfigurationValid

    val oidcConfigurationValid: Boolean
        get() {
            if (oidcProvider.isEmpty() && oidcName.isEmpty()) return true
            return OAuthFlow.validCustomProvider(oidcProvider) && oidcName.trimmingWhitespacesAndNewlines().isNotEmpty()
        }

    val oidcAuthEnabled: Boolean get() = oidcConfigurationValid && oidcProvider.isNotEmpty()

    val privacyUrl: String get() = apiBaseUrl.appendingPath("privacy.html")

    /** The site's landing at its own address, which shows it to someone signed in there too. */
    val homePageUrl: String get() = apiBaseUrl.appendingPath("home")

    companion object {
        /** Where Peek's code is published. */
        const val sourceUrl = "https://github.com/plhery/peek-delivery-tracker"

        /**
         * Swift's `current`: the configuration from the build's settings, under the iPhone app's Info.plist keys
         * (`SDTUseAPI`, `SDTAPIBaseURL`…), which the app supplies. A setting the build left unset (`$(…)`) is empty.
         */
        fun from(setting: (String) -> String?): AppConfiguration {
            fun value(key: String): String {
                val trimmed = setting(key)?.trimmingWhitespacesAndNewlines() ?: return ""
                return if (trimmed.contains("$(")) "" else trimmed
            }
            // `URL(string:)`: nothing for an empty value, invalid characters encoded.
            fun url(key: String): String? = value(key).nonEmpty?.let { UrlComponents.parse(it)?.url }
            val baseUrl = url("SDTAPIBaseURL")
            val supabase = url("SDTSupabaseURL")
            return AppConfiguration(
                mode = if (value("SDTUseAPI").uppercase() == "YES") Mode.Api else Mode.Demo,
                apiBaseUrl = baseUrl ?: "https://peektracker.com",
                supabaseUrl = supabase,
                supabasePublishableKey = value("SDTSupabasePublishableKey"),
                googleAuthEnabled = value("SDTGoogleAuthEnabled").uppercase() == "YES",
                appleAuthEnabled = value("SDTAppleAuthEnabled").uppercase() == "YES",
                emailOtpEnabled = value("SDTEmailOTPEnabled").uppercase() != "NO",
                appGroupIdentifier = value("SDTAppGroupIdentifier").nonEmpty ?: "group.com.plhery.PeekDeliveryTracker",
                linkHosts = linkHosts(value("SDTLinkHosts")),
                oidcProvider = value("SDTOIDCProvider"),
                oidcName = value("SDTOIDCName"),
            )
        }

        // As `range(of:options: .regularExpression)` reads it: `$` is the end of the input.
        private val hostName = Regex("^[a-z0-9]([a-z0-9-]*[a-z0-9])?(\\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)+\\z")

        /** Host names separated by spaces or commas. Anything that is not a plain host name is left out. */
        fun linkHosts(list: String): List<String> =
            list.lowercase().split(' ', ',').filter { it.isNotEmpty() && hostName.containsMatchIn(it) }

        /** A mail to Peek. Its subject names the version it is about, and nothing else goes with it. */
        fun feedbackUrl(version: String): String {
            val mail = UrlComponents.parse("mailto:hello@peektracker.com")!!
            mail.queryItems = listOf(UrlComponents.QueryItem("subject", "Peek $version feedback"))
            return mail.url!!
        }
    }
}

internal val String.nonEmpty: String? get() = ifEmpty { null }

/** Foundation's `uuidString`: upper case, which orders as the lower case form does. */
internal val UUID.uuidString: String get() = toString().uppercase()

private val uuidFormat = Regex("[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}")

/**
 * Foundation's `UUID(uuidString:)`: 8-4-4-4-12 hex digits in either case, nothing else. It reads the string as C
 * does, so a NUL ends it.
 */
internal fun uuid(uuidString: String): UUID? {
    val text = uuidString.substringBefore('\u0000')
    return if (uuidFormat.matches(text)) UUID.fromString(text) else null
}

enum class AppLanguage(val rawValue: String) {
    En("en"), De("de"), Fr("fr"), It("it"), Es("es"), Pt("pt"), Pl("pl");

    val id: String get() = rawValue
    val locale: Locale get() = Locale.forLanguageTag(if (this == Pt) "pt-PT" else rawValue)

    val nativeName: String
        get() = when (this) {
            En -> "English"
            De -> "Deutsch"
            Fr -> "Français"
            It -> "Italiano"
            Es -> "Español"
            Pt -> "Português"
            Pl -> "Polski"
        }

    companion object {
        const val storageKey = "deliveryTrackerLocale"

        fun fromRawValue(rawValue: String): AppLanguage? = entries.firstOrNull { it.rawValue == rawValue }

        /**
         * The chosen language, the value saved under `storageKey`, else the first of the device's languages the app
         * speaks, else English. The device's languages are BCP 47 tags in the order it prefers them, all of
         * `LocaleList.getDefault()` as `Locale.preferredLanguages` gives them on iOS: a phone set to Dutch, then
         * German, gets German.
         */
        fun current(saved: String?, preferredLanguages: List<String>): AppLanguage {
            val preferred = preferredLanguages.firstNotNullOfOrNull {
                fromRawValue(it.split('-').firstOrNull { part -> part.isNotEmpty() }?.lowercase() ?: "")
            }
            return saved?.let(::fromRawValue) ?: preferred ?: En
        }
    }
}

/**
 * The app's copy in its languages (Localization.json) and its date, time and money formats. Where Core.swift's
 * Localizer starts from `AppLanguage.current` and saves each language chosen (UserDefaults, and the widget's app
 * group), the app passes `AppLanguage.current` of its saved choice and the device's languages, and saves the
 * language from `languageFlow`.
 */
class Localizer(
    language: AppLanguage,
    /** Where the copy names the device, a variant such as `ipad` replaces the key when it has one. */
    private val variant: String? = null,
) {
    private val languageState = MutableStateFlow(language)

    /** The language as it changes, for the screens and for saving it. */
    val languageFlow: StateFlow<AppLanguage> = languageState.asStateFlow()

    var language: AppLanguage
        get() = languageState.value
        set(value) {
            languageState.value = value
        }

    /** The key's device variant, then its plural form for a `count`, then English, then the key itself. */
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
        var result = messages[selectedKey] ?: dictionaries["en"]?.get(selectedKey) ?: variantKey
        for ((name, value) in variables) {
            result = result.replace("{{$name}}", value.toString())
        }
        return result
    }

    /** What to tell someone about an error. A connection failure is an `IOException`, as OkHttp throws it. */
    fun errorMessage(error: Throwable): String {
        if (error is NativeVerificationFailed) return text("door.verification.body")
        if (error is DeliveryApiError) {
            return when (error) {
                is DeliveryApiError.AuthenticationExpired -> text("native.error.authenticationExpired")
                is DeliveryApiError.DuplicateTracking -> text("native.error.duplicateTracking")
                is DeliveryApiError.InvalidResponse -> text("native.error.invalidResponse")
                is DeliveryApiError.LabelTooLong -> text("native.error.labelTooLong")
                is DeliveryApiError.NotificationsDenied -> text("native.notificationsDenied")
                is DeliveryApiError.ParcelMissing -> text("native.parcelMissing")
                is DeliveryApiError.PushTokenUnavailable -> text("native.apnsTokenError")
                is DeliveryApiError.RefreshFailed -> text("native.error.refreshFailed")
                is DeliveryApiError.RefreshTimeout -> text("app.refreshTimeout")
                is DeliveryApiError.RateLimited -> text("native.error.rateLimited")
                is DeliveryApiError.Service -> serviceErrorMessage(error.serviceMessage)
                is DeliveryApiError.ServiceFailed -> text("native.error.serviceFailed", mapOf("status" to error.status))
            }
        }
        if (error is DeviceLookupsSpent) return text("peek.dailyLimit")
        if (error is AuthenticationError) {
            return when (error) {
                is AuthenticationError.NotConfigured -> text("native.auth.notConfigured")
                is AuthenticationError.InvalidResponse -> text("native.auth.invalidResponse")
                is AuthenticationError.MissingSession -> text("native.auth.missingSession")
                is AuthenticationError.OauthCancelled -> text("native.auth.cancelled")
                is AuthenticationError.RequestFailed -> text("native.auth.requestFailed", mapOf("status" to error.status))
                is AuthenticationError.SecureRequestFailed -> text("native.auth.secureRequest")
                is AuthenticationError.SessionSaveFailed -> text("native.auth.saveSession")
                is AuthenticationError.OtherAppleAccount -> text("native.auth.otherAppleAccount")
                is AuthenticationError.Server -> serviceErrorMessage(error.serverMessage, fallback = "auth.verifyFailed")
            }
        }
        if (error is IOException) return text("error.connection")
        return text("error.generic")
    }

    private fun serviceErrorMessage(message: String, fallback: String = "error.generic"): String {
        val value = message.lowercase()
        for ((pattern, key) in serviceErrorRules) {
            if (pattern.containsMatchIn(value)) return text(key)
        }
        return text(fallback)
    }

    fun eventDescription(description: String): String {
        val event = trackingMessages.events[description] ?: return description
        return text(event.key, event.variables)
    }

    fun trackingFailureMessage(error: String): String {
        val kind = if (error.startsWith("carrier:")) error.removePrefix("carrier:") else ""
        return text(trackingMessages.failures[kind] ?: "detail.trackingUnavailable")
    }

    fun deliveryWindow(from: String?, to: String, now: Instant = Instant.now()): String {
        val end = expectedDelivery(to, now)
        if (from == null) return end
        val startDate = DateParser.deliveryDate(from) ?: return end
        val endDate = DateParser.deliveryDate(to) ?: return end
        if (startDate >= endDate) return end
        return "${expectedDelivery(from, now)} – $end"
    }

    fun relativeTime(from: String, now: Instant = Instant.now()): String {
        val date = DateParser.date(from) ?: return ""
        val seconds = now.timeIntervalSince(date)
        if (seconds < 60) return text("time.justNow")
        if (seconds < 3_600) return text("time.minutesAgo", mapOf("count" to maxOf(1, (seconds / 60).toInt())))
        if (seconds < 86_400) return text("time.hoursAgo", mapOf("count" to maxOf(1, (seconds / 3_600).toInt())))
        if (seconds < 604_800) return text("time.daysAgo", mapOf("count" to maxOf(1, (seconds / 86_400).toInt())))
        return shortDate(date)
    }

    /** How long ago a scan happened, or its day when the carrier gave no clock time. */
    fun relativeTime(of: TrackingEvent, now: Instant = Instant.now()): String {
        of.calendarDay?.let { return deliveryDate(it, now) }
        return relativeTime(from = of.occurredAt, now = now)
    }

    /** One formatter per pattern and language: patterns are parsed once, and the time zone is the current one. */
    private fun format(pattern: String, date: Instant, posix: Boolean = false): String {
        val key = "${if (posix) "posix" else language.rawValue}|$pattern"
        val formatter = formatters.getOrPut(key) { DateTimeFormatter.ofPattern(pattern, if (posix) Locale.US else language.locale) }
        return formatter.format(date.atZone(ZoneId.systemDefault()))
    }

    /** Hours and minutes in the app language, as event rows show them. */
    fun clockTime(date: Instant): String = format("HH:mm", date)

    fun expectedDelivery(value: String, now: Instant = Instant.now()): String {
        val match = deliveryWindowPattern.matcher(value)
        if (match.find()) {
            val day = expectedDelivery(match.group(1), now)
            var window = match.group(2)
            match.group(3)?.let { window += "–$it" }
            return "$day, $window"
        }

        val date = DateParser.deliveryDate(value) ?: return value
        if (value.contains("T")) {
            DateParser.date(value)?.let { timestamp ->
                val day = format("yyyy-MM-dd", timestamp, posix = true)
                return "${expectedDelivery(day, now)}, ${clockTime(timestamp)}"
            }
        }
        return deliveryDate(date, now)
    }

    fun deliveryDate(date: Instant, now: Instant = Instant.now()): String {
        val zone = ZoneId.systemDefault()
        val day = date.atZone(zone).toLocalDate()
        val today = now.atZone(zone).toLocalDate()
        if (day == today) return text("time.today")
        if (day == today.minusDays(1)) return text("time.yesterday")
        if (day == today.plusDays(1)) return text("time.tomorrow")
        return shortDate(date)
    }

    /**
     * Compact dates with explicit weekdays and months: "Sat 12 Sep", "Sa 12. Sept", "sam 12 sept". Names keep the
     * language's own case, since the date often sits mid-sentence, and the day keeps the period German writes after it.
     */
    fun shortDate(date: Instant): String {
        val weekday = format("EEE", date).replace(".", "")
        val day = format("d", date)
        val month = format("MMM", date).replace(".", "")
        return "$weekday $day$dayPeriod $month"
    }

    /**
     * The period the language's own day-and-month pattern puts after the day, as German "12. Sept" does. java.time
     * cannot look up the `dMMM` template Swift reads, so the long date pattern, which writes the day as it does,
     * stands in for it.
     */
    private val dayPeriod: String
        get() = dayPeriods.getOrPut(language) {
            val pattern = DateTimeFormatterBuilder.getLocalizedDateTimePattern(FormatStyle.LONG, null, IsoChronology.INSTANCE, language.locale)
            if (pattern.contains("d.")) "." else ""
        }

    fun dateTime(value: String): String {
        val date = DateParser.date(value) ?: return value
        return "${shortDate(date)}, ${clockTime(date)}"
    }

    fun parcelStatus(parcel: Parcel): String = text(parcel.displayStatus.key)

    fun parcelDeliveryEstimate(parcel: Parcel, now: Instant = Instant.now()): String? {
        val value = parcel.expectedDelivery ?: return null
        val stage = parcel.currentStage
        if (stage?.isFinal == true || stage == TrackingStage.ReadyForPickup || stage == TrackingStage.FailedAttempt ||
            stage == TrackingStage.Exception
        ) return null
        val date = DateParser.deliveryDate(value) ?: return null
        val zone = ZoneId.systemDefault()
        if (date.atZone(zone).toLocalDate() < now.atZone(zone).toLocalDate()) return null
        return deliveryWindow(parcel.carrierData?.expectedDeliveryFrom, value, now)
    }

    fun parcelCompletionDate(parcel: Parcel, now: Instant = Instant.now()): String? {
        val event = parcel.currentEvent ?: return null
        if (!event.stage.isFinal) return null
        val date = event.displayDate ?: return null
        return deliveryDate(date, now)
    }

    /**
     * What importing the parcel costs: customs with the carrier's fee on delivery, and its lower online price while
     * it can still be paid that way. Paid charges say so and keep their prices. None when the carrier lists neither
     * an amount nor a payment.
     */
    fun importCharges(parcel: Parcel): ImportChargesLine? {
        val charges = parcel.carrierData?.importCharges ?: return null
        val paid = charges.paid == true
        val customs = charges.customs ?: 0.0
        val amount = if (charges.customs == null && charges.carrierFee == null) null else customs + (charges.carrierFee ?: 0.0)
        val lower = charges.carrierFeeOnline?.let { customs + it }
        // Once delivered, unpaid charges were settled at the door or not at all: the online price no longer applies.
        val online = if (amount != null && lower != null && lower < amount && (paid || parcel.currentStage != TrackingStage.Delivered)) {
            lower
        } else {
            null
        }
        val money = { value: Double -> money(value, charges.currency) }
        if (paid) {
            if (amount == null) return ImportChargesLine(text("detail.importChargesPaid"), payableOnline = false)
            if (online == null) {
                return ImportChargesLine(text("detail.importChargesPaidAmount", mapOf("amount" to money(amount))), payableOnline = false)
            }
            return ImportChargesLine(
                text("detail.importChargesPaidAmounts", mapOf("amount" to money(amount), "online" to money(online))),
                payableOnline = false,
            )
        }
        if (amount == null) return null
        if (online == null) return ImportChargesLine(money(amount), payableOnline = false)
        return ImportChargesLine(
            text("detail.importChargesOnline", mapOf("amount" to money(amount), "online" to money(online))),
            payableOnline = true,
        )
    }

    /**
     * `value.formatted(.currency(code:).locale(_:))`: ICU's currency format in the app language. Foundation hands
     * ICU the code inside a number skeleton, where it is three of ICU's invariant characters in either case, up
     * to the white space that ends the option; anything else is no currency, and the number is written as Swift
     * describes it.
     */
    private fun money(value: Double, code: String): String {
        val match = currencyCode.matcher(code)
        if (!match.matches()) return value.swiftDescription()
        val token = match.group(1).uppercase(Locale.ROOT)
        if (token.all { it in 'A'..'Z' }) {
            return Icu.currencies.format(value, token, language.locale, if (token in wholeCurrencies) 0 else null)
        }
        // ICU's Currency takes letters alone; any other code is one ICU has no data for, written as it is given,
        // which ICU keeps apart from the digits unless it ends in a symbol ("EU+34.41").
        val text = Icu.currencies.format(value, unknownCurrency, language.locale, null).replace(unknownCurrency, token)
        return if (Character.getType(token.last()) == Character.MATH_SYMBOL.toInt()) text.replace("$token\u00A0", token) else text
    }

    @Serializable
    private data class TrackingMessages(val events: Map<String, Event>, val failures: Map<String, String>) {
        @Serializable
        data class Event(val key: String, val variables: Map<String, String>)
    }

    private companion object {
        val currencyCode: Pattern =
            Pattern.compile("([\"%&'()*+,\\-.0-9:;<=>?A-Z_a-z]{3})[\\t\\n\\x{0B}\\f\\r \\x{85}\\x{200E}\\x{200F}\\x{2028}\\x{2029}]*")

        // Apple writes these without decimals, where CLDR gives them two.
        val wholeCurrencies = setOf("COP", "HUF", "IDR", "PKR")

        // A code no currency has, which ICU writes as it is.
        const val unknownCurrency = "QQQ"

        val dictionaries: Map<String, Map<String, String>> by lazy {
            runCatching {
                Json.decodeFromString(
                    MapSerializer(String.serializer(), MapSerializer(String.serializer(), String.serializer())),
                    SharedResources.text("Localization.json"),
                )
            }.getOrElse { mapOf("en" to emptyMap()) }
        }

        val trackingMessages: TrackingMessages by lazy {
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString(TrackingMessages.serializer(), SharedResources.text("TrackingMessages.json"))
            }.getOrElse { TrackingMessages(emptyMap(), emptyMap()) }
        }

        val serviceErrorRules: List<Pair<Regex, String>> = listOf(
            "rate.?limit|too many requests" to "error.rateLimited",
            "otp_expired|invalid.*(code|token)|(code|token).*expired" to "error.invalidCode",
            "email.*(invalid|valid)|invalid.*email" to "error.invalidEmail",
            "amazon shipping could not be checked" to "add.amazonCheckUnavailable",
            "amazon logistics.*amazon account" to "add.amazonAccount",
            "postcode|postal code" to "error.postcode",
            "tracking.*(url|link)|complete.*link" to "error.trackingLink",
            "tracking (number|input)|parcel number" to "error.trackingNumber",
            "80 characters|label.*long" to "error.nameTooLong",
            "package not found|parcel.*no longer" to "native.parcelMissing",
        ).map { (pattern, key) -> Regex(pattern) to key }

        val deliveryWindowPattern: Pattern = Pattern.compile(
            "^(\\d{4}-\\d{2}-\\d{2})[ T]+(\\d{2}:\\d{2})(?:[–-](\\d{2}:\\d{2}))?$",
            unicodeCharacterClass,
        )

        val formatters = ConcurrentHashMap<String, DateTimeFormatter>()
        val dayPeriods = ConcurrentHashMap<AppLanguage, String>()
    }
}

/** The line a parcel's import charges read as, and whether they can still be paid online. */
data class ImportChargesLine(val text: String, val payableOnline: Boolean)

val Parcel.trackingSource: CarrierId? get() = carrierData?.activeTrackingCarrier
val Parcel.swissPostReady: Boolean get() = carrierData?.swissPostReady == true

/**
 * The day of a scan the carrier dated by its day alone, as a local calendar day. Its `occurredAt` then only places
 * it in order, from the start of that day in the carrier's zone.
 */
val TrackingEvent.calendarDay: Instant? get() = day?.let(DateParser::deliveryDate)

/** The date to show for the scan: its day when it has no clock time, else its moment. */
val TrackingEvent.displayDate: Instant? get() = calendarDay ?: DateParser.date(occurredAt)

/** Foundation's `timeIntervalSince`: the seconds from `other` to this instant. */
internal fun Instant.timeIntervalSince(other: Instant): Double =
    (epochSecond - other.epochSecond) + (nano - other.nano) / 1_000_000_000.0

/** Foundation's `timeIntervalSinceReferenceDate`: the seconds since 2001-01-01, as a `Date` holds them and JSON writes them. */
internal val Instant.timeIntervalSinceReferenceDate: Double
    get() = (epochSecond - 978_307_200) + nano / 1_000_000_000.0

object DateParser {
    private val unparsed = Instant.MIN

    // Rendering asks for the same event timestamps repeatedly. Day-only estimates such as "2026-09-27" never parse
    // as timestamps, and are remembered as such.
    private val parsedDates = ConcurrentHashMap<String, Instant>()

    fun date(value: String): Instant? {
        parsedDates[value]?.let { return if (it == unparsed) null else it }
        val date = FoundationDates.internetDateTime(value, fractionalSeconds = true)
            ?: FoundationDates.internetDateTime(value, fractionalSeconds = false)
        if (parsedDates.size >= 4_096) parsedDates.clear()
        parsedDates[value] = date ?: unparsed
        return date
    }

    /** A timestamp, or the start of the local day a date names in its first ten characters. */
    fun deliveryDate(value: String): Instant? =
        // Days are local, so follow a time zone change while the app runs.
        date(value) ?: FoundationDates.day(value.characterPrefix(10), ZoneId.systemDefault())

    private val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).withZone(ZoneOffset.UTC)

    /** ISO8601DateFormatter's default: whole seconds, in UTC. */
    fun isoString(date: Instant): String = isoFormatter.format(date)
}

/**
 * Swift's `JSONEncoder`: the bytes the iPhone app writes for a value. Nil properties are left out (but for the types
 * in `typesEncodingNil`, whose own `encode(to:)` writes them as null), a UUID is its `uuidString` in capitals, `/` is
 * written `\/`, control characters are escaped and the rest written as it is, and numbers as Swift prints a Double.
 * The keys follow the declaration unless sorted, by their Unicode scalars: Swift's own unsorted order is a hash order no
 * two runs share.
 */
class JsonEncoder(private val sortedKeys: Boolean = false) {
    fun <T> encode(serializer: SerializationStrategy<T>, value: T): ByteArray {
        val element = tree.encodeToJsonElement(serializer, value).asSwiftWrites(serializer.descriptor)
        return StringBuilder().also { it.write(element) }.toString().toByteArray(Charsets.UTF_8)
    }

    private fun JsonElement.asSwiftWrites(descriptor: SerialDescriptor): JsonElement = when {
        this is JsonObject && descriptor.kind == StructureKind.CLASS -> {
            val nil = descriptor.serialName.removeSuffix("?") in typesEncodingNil
            val fields = LinkedHashMap<String, JsonElement>()
            for (index in 0 until descriptor.elementsCount) {
                val name = descriptor.getElementName(index)
                val field = this[name]
                if (field != null) fields[name] = field.asSwiftWrites(descriptor.getElementDescriptor(index)) else if (nil) fields[name] = JsonNull
            }
            JsonObject(fields)
        }
        this is JsonObject && descriptor.kind == StructureKind.MAP ->
            JsonObject(mapValues { it.value.asSwiftWrites(descriptor.getElementDescriptor(1)) })
        this is JsonArray && descriptor.kind == StructureKind.LIST -> JsonArray(map { it.asSwiftWrites(descriptor.getElementDescriptor(0)) })
        this is JsonPrimitive && isString && descriptor.serialName.removeSuffix("?") == UuidSerializer.descriptor.serialName ->
            JsonPrimitive(content.uppercase(Locale.ROOT))
        else -> this
    }

    private fun StringBuilder.write(element: JsonElement) {
        when (element) {
            is JsonObject -> {
                append('{')
                val keys = if (sortedKeys) element.keys.sortedWith(byScalars) else element.keys
                for ((index, key) in keys.withIndex()) {
                    if (index > 0) append(',')
                    quote(key)
                    append(':')
                    write(element.getValue(key))
                }
                append('}')
            }
            is JsonArray -> {
                append('[')
                for ((index, item) in element.withIndex()) {
                    if (index > 0) append(',')
                    write(item)
                }
                append(']')
            }
            is JsonPrimitive -> when {
                element.isString -> quote(element.content)
                element is JsonNull || element.content == "true" || element.content == "false" -> append(element.content)
                // Swift writes a Double's description, less a whole number's ".0".
                element.content.any { it == '.' || it == 'e' || it == 'E' } -> append(element.content.toDouble().swiftDescription().removeSuffix(".0"))
                else -> append(element.content)
            }
        }
    }

    private fun StringBuilder.quote(text: String) {
        append('"')
        for (char in text) {
            when (char) {
                '"' -> append("\\\"")
                '\\' -> append("\\\\")
                '/' -> append("\\/")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                '\b' -> append("\\b")
                '\u000C' -> append("\\f")
                else -> if (char < ' ') append("\\u00").append(String.format(Locale.ROOT, "%02x", char.code)) else append(char)
            }
        }
        append('"')
    }

    companion object {
        /** `JSONEncoder.deliveryTracker`: keys sorted, as the API's requests go. */
        val deliveryTracker = JsonEncoder(sortedKeys = true)

        /** UTF-16 order puts the scalars above U+FFFF before U+E000 to U+FFFF; Swift does not. */
        private val byScalars = Comparator<String> { left, right ->
            var i = 0
            var j = 0
            while (i < left.length && j < right.length) {
                val a = left.codePointAt(i)
                val b = right.codePointAt(j)
                if (a != b) return@Comparator a.compareTo(b)
                i += Character.charCount(a)
                j += Character.charCount(b)
            }
            (left.length - i).compareTo(right.length - j)
        }

        private val tree = Json {
            encodeDefaults = true
            explicitNulls = false
        }
    }
}
