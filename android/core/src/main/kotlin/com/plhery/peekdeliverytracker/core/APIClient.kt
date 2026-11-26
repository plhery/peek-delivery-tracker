package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.AddByEmailResponse
import com.plhery.peekdeliverytracker.core.api.AddPackageFileResponse
import com.plhery.peekdeliverytracker.core.api.AssistantsResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionRequest
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ChangePackageCarrierRequest
import com.plhery.peekdeliverytracker.core.api.ChangePackageCarrierResponse
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsResponse
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageResponse
import com.plhery.peekdeliverytracker.core.api.DeleteAccountRequest
import com.plhery.peekdeliverytracker.core.api.DeleteLiveActivityDeviceRequest
import com.plhery.peekdeliverytracker.core.api.DeleteLiveActivityUpdateTokenRequest
import com.plhery.peekdeliverytracker.core.api.DeleteNativePushDeviceRequest
import com.plhery.peekdeliverytracker.core.api.ErrorResponse
import com.plhery.peekdeliverytracker.core.api.ErrorResponseCode
import com.plhery.peekdeliverytracker.core.api.ErrorResponseScope
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendsSnapshot
import com.plhery.peekdeliverytracker.core.api.LiveActivityDeviceRequest
import com.plhery.peekdeliverytracker.core.api.LiveActivityUpdateTokenRequest
import com.plhery.peekdeliverytracker.core.api.NativePushDeviceRequest
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesClock
import com.plhery.peekdeliverytracker.core.api.NotificationPreferencesDistance
import com.plhery.peekdeliverytracker.core.api.NotificationStage
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.NumberRefusalResponse
import com.plhery.peekdeliverytracker.core.api.OkResponse
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.PackageNotificationRequest
import com.plhery.peekdeliverytracker.core.api.PackageSeenRequest
import com.plhery.peekdeliverytracker.core.api.PackageSeenResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.ParcelShareResponse
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.PushSubscriptionResponse
import com.plhery.peekdeliverytracker.core.api.QueueResponse
import com.plhery.peekdeliverytracker.core.api.RenamePackageRequest
import com.plhery.peekdeliverytracker.core.api.RevokeLiveActivityDeviceRequest
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.core.api.ShopWait
import com.plhery.peekdeliverytracker.core.api.SyncJobListResponse
import com.plhery.peekdeliverytracker.core.api.SyncJobStatus
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

// APIClient.swift. URLSession is the app's: :core builds every request and reads every answer as the iPhone app does,
// and the app sends them through an `HttpTransport` it makes of OkHttp.

/**
 * A request as URLRequest holds it. Header names are matched without regard to case, as `setValue(_:forHTTPHeaderField:)`
 * matches them, and a value with a line break in it is never set, as URLRequest ignores one. Other values go as
 * given, characters outside ASCII included (OkHttp's `addUnsafeNonAscii`, where `header` would throw). The transport
 * must never answer from a cache: every request here asks past it.
 */
class HttpRequest(
    val url: String,
    val method: String = "GET",
    headers: Map<String, String> = emptyMap(),
    val body: ByteArray? = null,
    /** `timeoutInterval`, in seconds: how long the request may wait for the server's next byte, not for its answer. */
    val timeout: Double = 60.0,
    /** `httpShouldHandleCookies`: false sends no cookie and keeps none the answer sets. */
    val handlesCookies: Boolean = true,
) {
    val headers: Map<String, String> = LinkedHashMap(headers.filterValues { it.isHeaderValue() })

    /** `value(forHTTPHeaderField:)`. */
    fun header(name: String): String? = headers.entries.firstOrNull { it.key.equals(name, ignoreCase = true) }?.value

    /** `setValue(_:forHTTPHeaderField:)` on a copy: nil removes the field, and a value with a line break changes nothing. */
    fun setting(name: String, value: String?): HttpRequest {
        if (value != null && !value.isHeaderValue()) return this
        val fields = LinkedHashMap(headers.filterKeys { !it.equals(name, ignoreCase = true) })
        if (value != null) fields[name] = value
        return HttpRequest(url, method, fields, body, timeout, handlesCookies)
    }

    private fun String.isHeaderValue(): Boolean = '\r' !in this && '\n' !in this
}

/**
 * An answer as HTTPURLResponse holds it. A field the server sent more than once is one value, joined by ", ", as
 * CFNetwork joins them (two `Retry-After` fields read as no number).
 */
class HttpResponse(val statusCode: Int, headers: Map<String, String> = emptyMap(), val body: ByteArray = ByteArray(0)) {
    val headers: Map<String, String> = LinkedHashMap(headers)

    /** `value(forHTTPHeaderField:)`. */
    fun header(name: String): String? = headers.entries.firstOrNull { it.key.equals(name, ignoreCase = true) }?.value

    /**
     * The service's own answer that what was asked for isn't there: a 404 with its JSON error. Any other 404, such as
     * the proxy's text while the server is up and the app isn't yet, is the server failing, and forgets nothing.
     */
    val isServiceNotFound: Boolean
        get() = statusCode == 404 && JsonSerialization.stringsOfObject(body)?.get("error") == "Parcel unavailable"
}

/**
 * URLSession's `data(for:)`, which the app implements with OkHttp. Every status is an answer, never an exception. A
 * request that could not be sent, a network failure and a connection that timed out throw an `IOException` (Swift's
 * `URLError`); a call the app cancelled ends in a `CancellationException`, or in the IOException OkHttp ends it with once
 * its coroutine is cancelled.
 *
 * What the OkHttp transport must do:
 * - Send a POST, PUT or PATCH whose `body` is null (`queueRefreshAll`, `refresh`, `restore`) with an empty body:
 *   OkHttp's `method("POST", null)` throws an IllegalArgumentException, which is no IOException.
 * - Make each body without a media type (`body.toRequestBody(null)`, or an empty one), so the request's own
 *   `Content-Type`, or none, is what goes.
 * - Time out in the transport, by `timeout` as connect, read and write timeouts, so a timeout is an IOException. A
 *   `withTimeout` around a client call ends in a TimeoutCancellationException instead, which is a cancellation: not
 *   counted as an analytics error, where the iPhone counts `URLError.timedOut`.
 * - Redact `Authorization`, `X-Parcel-Key`, `X-App-Attest-Key-Id`, `X-App-Attest-Challenge`, `X-App-Attest-Assertion`
 *   and `X-Lookup-Proof` in any logging (`HttpLoggingInterceptor.redactHeader`), and log no body: the browser check's
 *   token and the push and revocation tokens go in bodies.
 */
fun interface HttpTransport {
    suspend fun data(request: HttpRequest): HttpResponse
}

/**
 * The transport with the app's `User-Agent` on each request that sets none, as URLSession adds its own. The server
 * tells the app's requests from a browser's by it, for counting and, once enforced, for asking an app request for its
 * attestation: `NATIVE_USER_AGENT` in src/server/nativeVerification.ts matches the iPhone's alone
 * (`PeekDeliveryTracker/<build> CFNetwork/<version> Darwin/<version>`), whose requests must carry App Attest. The
 * Android app sends its own, which must not match that pattern, or its account requests would be counted, and once
 * enforced refused, as an iPhone's that never signs: `PeekDeliveryTracker/<versionCode> okhttp/<version>
 * Android/<release>`, say.
 */
fun HttpTransport.identifying(userAgent: String): HttpTransport = HttpTransport { request ->
    data(if (request.header("User-Agent") == null) request.setting("User-Agent", userAgent) else request)
}

/**
 * The inputs beyond the delivery postcode this app can ask a reader for, named in `X-Peek-Inputs` on each request for
 * parcels, links and detections, with an account or without: the service suggests an input that may find a parcel
 * (`inputSuggested`) only to the apps that name it (src/server/suggestedInputs.ts).
 */
const val CLIENT_INPUTS = "phoneDigits"

/** How long a note's file may take to go up, in seconds. */
private const val UPLOAD_TIMEOUT = 120.0

/** The server refused a note's file, with its status and the code that says why. */
private class FileRefused(val status: Int, val code: ErrorResponseCode?) : Exception("The file was refused ($status)")

/**
 * The `User-Agent` URLSession sends for the iPhone app: its bundle's name and build, then CFNetwork's and Darwin's
 * versions. Never the Android app's (see `identifying`); the tests and the differential harness compare with it.
 */
internal fun iosUserAgent(build: String, cfNetwork: String, darwin: String): String =
    "PeekDeliveryTracker/$build CFNetwork/$cfNetwork Darwin/$darwin"

/** The device's country preference helps order carrier queries; it does not select a carrier. */
object ParcelLookupCountry {
    // `Locale.Region.isoRegions` with two letters and no regions under them: the groupings EU, EZ, QO and UN have some.
    private val countries: Set<String> by lazy {
        TrackingLocation.regions.filter { it.length == 2 && it !in setOf("EU", "EZ", "QO", "UN") }.toSet()
    }

    // Foundation reads these retired codes as the ones that replaced them.
    private val aliases = mapOf("CS" to "RS", "TP" to "TL", "UK" to "GB", "YU" to "RS")

    /**
     * `locale.region` on the iPhone's `Locale.current`: the first two characters of an `rg` extension of three or more
     * ("gbzzzz" is GB, "ukzzzz" is UK, which is no country), else the locale's own region, retired codes read as their
     * successors. `locale` is the device's region, which the app gives: on the iPhone it is the Region setting, which
     * the app's own language leaves alone, so on Android it is the system's first locale
     * (`Resources.getSystem().configuration.locales[0]`), never `Locale.getDefault()`, which follows a per-app language.
     */
    fun hint(locale: Locale): String? {
        val override = locale.getUnicodeLocaleType("rg")?.takeIf { it.length > 2 }?.substring(0, 2)?.uppercase(Locale.ROOT)
        val country = override ?: locale.country.uppercase(Locale.ROOT).let { aliases[it] ?: it }
        if (!country.all { it in 'A'..'Z' } || country !in countries) return null
        return country
    }

    /**
     * The countries the Add sheet offers to look a number up for: [home], the device's ([hint]), first, then every
     * other country the service takes, by its name in the language (the web's `LookupCountrySentence`).
     */
    fun choices(home: String?, language: AppLanguage): List<String> =
        listOfNotNull(home) + CarrierCatalog.countriesByName(language).filter { it != home }
}

/**
 * The number as typed, sent beside the normalized one when it spells it differently (`CarrierDetectionRequest.typedNumber`,
 * src/lib/carrierDetection.ts `typedNumber`): its dashes and spaces tell some carriers' numbers apart (a detection
 * rule's `rawPattern`, an R+L PRO number's dash). Kept only when it normalizes to the number asked about and is 80
 * characters at most, as the service reads no other.
 */
fun typedTrackingNumber(typed: String?, trackingNumber: String): String? =
    typed?.trimmingWhitespacesAndNewlines()?.takeIf {
        it != trackingNumber && it.length <= 80 && CarrierCatalog.normalize(it) == trackingNumber
    }

sealed class DeliveryApiError(message: String) : Exception(message) {
    class AuthenticationExpired : DeliveryApiError("Your sign-in expired. Please sign in again.")
    class DuplicateTracking(val parcelId: UUID) : DeliveryApiError("This tracking number is already in your delivery box.")
    class InvalidResponse : DeliveryApiError("The delivery service returned an invalid response.")
    class LabelTooLong : DeliveryApiError("Parcel names can be at most 80 characters.")
    class NotificationsDenied : DeliveryApiError("Notifications were not allowed.")
    class ParcelMissing : DeliveryApiError("This parcel is no longer in your delivery box.")
    class PushTokenUnavailable :
        DeliveryApiError("Apple did not return a notification token. Try again on a signed development build.")
    class RefreshFailed : DeliveryApiError("Tracking refresh failed. Try again.")
    class RefreshTimeout : DeliveryApiError("The tracking refresh is taking longer than expected.")
    /** `retryAfter` in seconds. */
    class RateLimited(val retryAfter: Double) : DeliveryApiError("Too many requests. Try again shortly.")
    /** The account's tracking allowance for the day is used up: it lasts until tomorrow, not a moment. */
    class AllowanceUsed : DeliveryApiError("Today’s tracking allowance is used up. Try again tomorrow.")
    class Service(val serviceMessage: String) : DeliveryApiError(serviceMessage)
    class ServiceFailed(val status: Int) : DeliveryApiError("Delivery service failed ($status).")
}

/** What the edge in front of the server says about a maintenance: announced or not, and the end it gave, if any. */
data class MaintenanceNotice(val planned: Boolean, val backAround: Instant? = null) {
    companion object {
        val Unannounced = MaintenanceNotice(planned = false)
    }
}

/**
 * The server is down for a few minutes of maintenance: the machine restarting, or its database being upgraded. Screens
 * keep what they show, say so calmly and ask again by themselves; nothing signs out or forgets a parcel for it.
 * `retryAfter` is what the server asked for before asking again, in seconds, 0 when it said nothing; `notice` what the
 * edge's own answer announced, which a database's or a proxy's does not.
 */
data class ServerDown(val retryAfter: Double = 0.0, val notice: MaintenanceNotice? = null) : Exception("Peek is down for maintenance")

/** Tells the server down for maintenance from the server refusing or failing, as the web app does (docs/ARCHITECTURE.md). */
object Maintenance {
    /** Statuses a proxy or Cloudflare answers with for a server that does not answer itself. */
    val downStatuses = setOf(502, 503, 504, 520, 521, 522, 523, 524, 525, 526, 527, 530)

    /** The app's own 503s about one part it can't use now, which it words itself: not the server down. */
    private val partUnavailable = Regex("not configured|amazon|carrier|verification", RegexOption.IGNORE_CASE)

    /**
     * Whether the phone has a network, which the app tells from its connectivity: a request that got no answer while it
     * has one means the server is down, and one while it has none that the phone is offline, which keeps saying so.
     */
    @Volatile
    var deviceOnline: () -> Boolean = { true }

    /**
     * Whether an answer means the server is down rather than refusing: a proxy's or Cloudflare's error status (the
     * database's 502 and the sign-in server's 503 too), or a 404 the app did not write, such as the proxy's "404 page
     * not found" while the app starts.
     */
    fun isServerDown(status: Int, body: ByteArray): Boolean {
        val message = JsonSerialization.stringsOfObject(body)?.get("error")
        if (status == 404) return message == null
        if (status !in downStatuses) return false
        if (status != 503 || message == null || notice(body) != null) return true
        return !partUnavailable.containsMatchIn(message)
    }

    /** The server down, with what it asked for and what the edge announced, or nil for any other answer. */
    fun serverDown(response: HttpResponse): ServerDown? {
        if (!isServerDown(response.statusCode, response.body)) return null
        return ServerDown(DeliveryApiClient.retryAfterSeconds(response.header("Retry-After")), notice(response.body))
    }

    /** The maintenance an answer's `maintenance` object announces, or nil. */
    fun notice(body: ByteArray): MaintenanceNotice? = (objectOf(body)?.get("maintenance") as? JsonObject)?.let(::notice)

    private fun notice(value: JsonObject): MaintenanceNotice {
        val planned = (value["planned"] as? JsonPrimitive)?.takeIf { !it.isString }?.booleanOrNull == true
        val back = (value["backAround"] as? JsonPrimitive)?.takeIf { planned && it.isString }?.content?.let(DateParser::date)
        return MaintenanceNotice(planned, back)
    }

    private fun objectOf(body: ByteArray): JsonObject? = try {
        Json.parseToJsonElement(body.decodeToString()) as? JsonObject
    } catch (_: Exception) {
        null
    }

    /**
     * The error to throw for a request that failed on its way: the server down when it got no answer while the phone is
     * online. Not one that was cancelled, and not a phone that is offline.
     */
    suspend fun failure(error: Exception): Exception =
        if (error is IOException && currentCoroutineContext().isActive && deviceOnline()) ServerDown() else error

    /** How long to wait before asking again, in seconds: what the server asked for and the backoff from 5 s, whichever is longer, at most a minute. */
    fun retryDelay(attempt: Int, retryAfter: Double = 0.0): Double {
        val backoff = minOf(60.0, 5.0 * 2.0.pow(attempt.coerceIn(0, 10)))
        return minOf(60.0, maxOf(backoff, retryAfter))
    }

    /**
     * What `/maintenance.json` says, asked only once something failed: nil when it is not there to say (the edge not in
     * front yet answers the app's own 404 page) or says nothing readable. Never cached, as no request here is.
     */
    suspend fun read(configuration: AppConfiguration, transport: HttpTransport): MaintenanceNotice? {
        val request = HttpRequest(
            url = configuration.apiBaseUrl.appendingPath("/maintenance.json"),
            headers = mapOf("Accept" to "application/json"),
            timeout = 10.0,
            handlesCookies = false,
        )
        val response = try {
            transport.data(request)
        } catch (error: CancellationException) {
            throw error
        } catch (_: Exception) {
            return null
        }
        if (response.statusCode != 200 && response.statusCode != 503) return null
        val payload = objectOf(response.body) ?: return null
        (payload["maintenance"] as? JsonObject)?.let { return notice(it) }
        return if ((payload["planned"] as? JsonPrimitive)?.takeIf { !it.isString }?.booleanOrNull != null) notice(payload) else null
    }
}

/**
 * The account's requests. Used from the main thread, as the iPhone's client is `@MainActor`; a large answer is read
 * off it. `deviceName` is the name the account's device list shows for this one (`UIDevice.current.name`); `region`
 * the device's region at the moment, whose country a detection sends (`ParcelLookupCountry.hint` says which locale);
 * `debug` says the build's push tokens are development ones (`#if DEBUG`); `analytics` counts the catalog's
 * operations (`DeliveryAnalytics.shared.action`). `verification` is the app's one NativeVerification. A large answer is
 * read on `decoding`.
 */
class DeliveryApiClient(
    private val configuration: AppConfiguration,
    private val session: Session,
    private val transport: HttpTransport,
    private val verification: NativeVerification,
    private val deviceName: String,
    private val region: () -> Locale,
    private val debug: Boolean = false,
    private val analytics: (event: String, outcome: DeliveryAnalytics.Outcome) -> Unit = { _, _ -> },
    private val now: () -> Instant = AppClock::now,
    private val decoding: CoroutineDispatcher = Dispatchers.Default,
) {
    /** What the client needs of SessionStore, which answers on its own thread whichever thread asks. */
    interface Session {
        /** A new value each time the signed-in identity changes: work begun for one never finishes for another. */
        val generation: UUID

        /** Swift's `user != nil`. */
        val signedIn: Boolean

        /** Nil without a session; refreshed when it is close to expiring, or always with `forceRefresh`. */
        suspend fun accessToken(forceRefresh: Boolean = false): String?

        /** Forgets the session on this device, without asking the server: done before it returns. */
        suspend fun forceSignOut()
    }

    suspend fun friendsSnapshot(): FriendsSnapshot = request("/api/friends", FriendsSnapshot.serializer())

    suspend fun friendsActivity(): FriendsActivity = request("/api/friends/activity", FriendsActivity.serializer())

    suspend fun friendsAction(value: FriendsActionRequest): FriendsActionResponse =
        request("/api/friends", FriendsActionResponse.serializer(), "POST", encode(FriendsActionRequest.serializer(), value))

    suspend fun claimParcels(value: ClaimParcelsRequest): ClaimParcelsResponse =
        request("/api/packages/claim", ClaimParcelsResponse.serializer(), "POST", encode(ClaimParcelsRequest.serializer(), value))

    /** The link one of the account's parcels is shared through, or nil while it is not shared. */
    suspend fun parcelShare(id: UUID): ParcelShare? =
        request("/api/packages/${id.uuidString}/share", ParcelShareResponse.serializer()).link

    /** Makes the parcel's link when it has none, else changes what the link shows. */
    suspend fun shareParcel(id: UUID, value: ShareParcelRequest): ParcelShare {
        val response = request(
            "/api/packages/${id.uuidString}/share", ParcelShareResponse.serializer(), "PUT", encode(ShareParcelRequest.serializer(), value),
        )
        val link = response.link
        if (link == null || !ParcelLinkRoute.validId(link.id)) throw DeliveryApiError.InvalidResponse()
        return link
    }

    /** The link goes blank for good. The answer has no body. */
    suspend fun stopSharingParcel(id: UUID) {
        rawRequest("/api/packages/${id.uuidString}/share", "DELETE")
    }

    /** What its reader says of one of the account's parcels. The answer has no body. */
    suspend fun sendFeedback(id: UUID, value: ParcelFeedbackRequest) {
        rawRequest("/api/packages/${id.uuidString}/feedback", "POST", encode(ParcelFeedbackRequest.serializer(), value))
    }

    suspend fun listPackages(): List<Parcel> =
        request("/api/packages?includeArchived=true", PackageListResponse.serializer()).packages

    /** `typed`: the number as typed, which goes beside it when it differs ([typedTrackingNumber]). */
    /** The carrier check for the number, asked for [country], the one picked on the Add sheet, else the device's. */
    suspend fun detectCarrier(trackingNumber: String, typed: String? = null, country: String? = null): CarrierDetectionResponse = request(
        "/api/carriers/detect", CarrierDetectionResponse.serializer(), "POST",
        encode(
            CarrierDetectionRequest.serializer(),
            CarrierDetectionRequest(
                trackingNumber = trackingNumber, lookupCountryHint = country ?: ParcelLookupCountry.hint(region()),
                typedNumber = typedTrackingNumber(typed, trackingNumber),
            ),
        ),
    )

    suspend fun add(input: CreatePackageRequest): CreatePackageResponse =
        request("/api/packages", CreatePackageResponse.serializer(), "POST", encode(CreatePackageRequest.serializer(), input))

    suspend fun rename(id: UUID, label: String): Parcel = request(
        "/api/packages/${id.uuidString}", Parcel.serializer(), "PATCH",
        encode(RenamePackageRequest.serializer(), RenamePackageRequest(label = label)),
    )

    /** The parcel's private note; null deletes it (an empty note, which the server takes as none). */
    suspend fun setNote(id: UUID, note: String?): Parcel = request(
        "/api/packages/${id.uuidString}", Parcel.serializer(), "PATCH",
        encode(RenamePackageRequest.serializer(), RenamePackageRequest(note = note ?: "")),
    )

    /**
     * Adds a photo or PDF to the parcel's note: its bytes as they are, its kind as their type and its name in
     * `X-File-Name`, percent-encoded. A refusal says why ([NoteFileError]).
     */
    suspend fun addFile(id: UUID, upload: NoteFileUpload): AddPackageFileResponse {
        val path = "/api/packages/${id.uuidString}/files"
        val headers = mapOf(
            "Content-Type" to upload.type.rawValue,
            "X-File-Name" to java.net.URLEncoder.encode(upload.name, "UTF-8").replace("+", "%20"),
        )
        val response = try {
            rawRequest(path, "POST", upload.bytes, missing = 404, headers = headers, timeout = UPLOAD_TIMEOUT)
        } catch (error: FileRefused) {
            throw NoteFileError(NoteFileProblem.upload(error.status, error.code), size = upload.bytes.size.toLong(), status = error.status)
        }
        if (response.statusCode == 404) throw DeliveryApiError.ParcelMissing()
        return decodeAnswer(AddPackageFileResponse.serializer(), response.body)
    }

    /** Removes a file from the parcel's note; one already gone is gone. */
    suspend fun removeFile(id: UUID, fileId: UUID) {
        rawRequest("/api/packages/${id.uuidString}/files/${fileId.uuidString}", "DELETE", missing = 404)
    }

    /**
     * The bytes at a note's file's signed address (`/api/files/{id}?…`, its photo, its thumbnail or its PDF); null once
     * the file has gone.
     */
    suspend fun fileBytes(address: String): ByteArray? {
        val response = rawRequest(address, missing = 404, headers = mapOf("Accept" to "*/*"), timeout = UPLOAD_TIMEOUT)
        return if (response.statusCode == 404) null else response.body
    }

    suspend fun changeCarrier(
        id: UUID,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String? = null,
        phoneDigits: String? = null,
    ): ChangePackageCarrierResponse = request(
        "/api/packages/${id.uuidString}/carrier", ChangePackageCarrierResponse.serializer(), "PATCH",
        encode(
            ChangePackageCarrierRequest.serializer(),
            ChangePackageCarrierRequest(
                carrier = carrier, trackingUrl = trackingUrl, dpdPostcode = dpdPostcode,
                dpdPostcodeCountry = dpdPostcodeCountry, phoneDigits = phoneDigits, providerPostcode = providerPostcode,
            ),
        ),
    )

    suspend fun setMuted(id: UUID, muted: Boolean): Parcel = request(
        "/api/packages/${id.uuidString}/notifications", Parcel.serializer(), "PATCH",
        encode(PackageNotificationRequest.serializer(), PackageNotificationRequest(muted = muted)),
    )

    /** The parcel's delivery email alone: its notifications stay as they are. */
    suspend fun setEmailMuted(id: UUID, muted: Boolean): Parcel = request(
        "/api/packages/${id.uuidString}/notifications", Parcel.serializer(), "PATCH",
        encode(PackageNotificationRequest.serializer(), PackageNotificationRequest(emailMuted = muted)),
    )

    suspend fun archive(id: UUID) {
        request("/api/packages/${id.uuidString}", OkResponse.serializer(), "DELETE")
    }

    suspend fun restore(id: UUID): Parcel = request("/api/packages/${id.uuidString}/restore", Parcel.serializer(), "POST")

    /**
     * The parcel opened by its owner (#72), seen as of the `news_at` its page showed, so that a scan stored meanwhile
     * stays new: the moment the server keeps as its `seen_at`.
     */
    suspend fun markSeen(id: UUID, newsAt: String): String = request(
        "/api/packages/${id.uuidString}/seen", PackageSeenResponse.serializer(), "POST",
        encode(PackageSeenRequest.serializer(), PackageSeenRequest(newsAt = newsAt)),
    ).seenAt

    suspend fun permanentlyDelete(id: UUID) {
        request("/api/packages/${id.uuidString}/permanent", OkResponse.serializer(), "DELETE")
    }

    /** Queues a check of every parcel; follow it with `waitForJobs`. */
    suspend fun queueRefreshAll(): List<UUID> = request("/api/sync", QueueResponse.serializer(), "POST").jobIds

    /** What the edge says of the maintenance under way, asked once something failed ([Maintenance.read]). */
    suspend fun maintenance(): MaintenanceNotice? = Maintenance.read(configuration, transport)

    suspend fun refresh(id: UUID) {
        val generation = session.generation
        val queued = request("/api/packages/${id.uuidString}/sync", QueueResponse.serializer(), "POST")
        session.checkGeneration(generation)
        waitForJobs(queued.jobIds)
    }

    /**
     * Polls the jobs until each has succeeded, for two minutes at most. The first twenty still pending are asked each
     * time: Swift takes them from a Set, in an order of its own each launch, where this keeps the order given.
     */
    suspend fun waitForJobs(jobIds: List<UUID>) {
        val generation = session.generation
        val pending = LinkedHashSet(jobIds)
        val deadline = now().plusSeconds(120)
        var interval = 1.0
        while (pending.isNotEmpty() && now() < deadline) {
            session.checkGeneration(generation)
            var retryAfter = 0.0
            try {
                val ids = pending.take(20)
                val query = ids.joinToString(",") { it.uuidString }
                val response = request("/api/sync/jobs?ids=$query", SyncJobListResponse.serializer())
                session.checkGeneration(generation)
                if (response.jobs.map { it.id }.toSet() != ids.toSet()) throw DeliveryApiError.InvalidResponse()
                for (job in response.jobs) {
                    if (job.status == SyncJobStatus.Failed || (job.result?.errors ?: 0) > 0) {
                        job.error?.let { throw DeliveryApiError.Service(it) }
                        throw DeliveryApiError.RefreshFailed()
                    }
                    if (job.status == SyncJobStatus.Succeeded) pending.remove(job.id)
                }
            } catch (error: DeliveryApiError.RateLimited) {
                retryAfter = error.retryAfter
            }
            if (pending.isEmpty()) return
            delay(minOf(maxOf(interval, retryAfter), maxOf(0.0, deadline.timeIntervalSince(now()))).seconds)
            interval = minOf(interval * 1.5, 10.0)
        }
        if (pending.isNotEmpty()) throw DeliveryApiError.RefreshTimeout()
    }

    suspend fun notificationPreferences(): NotificationPreferences =
        request("/api/push/preferences", NotificationPreferences.serializer())

    /**
     * Saves the display choices that changed, and the phone's time zone (`timezone`), which the delivery email and the
     * notifications write their times in, as the web's saves do.
     */
    suspend fun saveDisplayPreferences(
        markNewUpdates: Boolean? = null, clock: NotificationPreferencesClock? = null, distance: NotificationPreferencesDistance? = null,
        timezone: String, showPeekAi: Boolean? = null,
    ): NotificationPreferences {
        val patch = kotlinx.serialization.json.buildJsonObject {
            markNewUpdates?.let { put("markNewUpdates", JsonPrimitive(it)) }
            showPeekAi?.let { put("showPeekAi", JsonPrimitive(it)) }
            clock?.let { put("clock", JsonPrimitive(it.rawValue)) }
            distance?.let { put("distance", JsonPrimitive(it.rawValue)) }
            put("timezone", JsonPrimitive(timezone))
        }
        return request("/api/push/preferences", NotificationPreferences.serializer(), "PATCH", patch.toString().encodeToByteArray())
    }

    /**
     * Saves the events alone, and clears the retired quiet hours: the delivery email and the display choices save on
     * their own, so a change made to them elsewhere stays.
     */
    suspend fun saveNotificationEvents(stages: List<NotificationStage>, timezone: String): NotificationPreferences {
        val patch = kotlinx.serialization.json.buildJsonObject {
            put("enabledStages", JsonArray(stages.map { JsonPrimitive(it.rawValue) }))
            put("quietHoursStart", JsonNull)
            put("quietHoursEnd", JsonNull)
            put("timezone", JsonPrimitive(timezone))
        }
        return request("/api/push/preferences", NotificationPreferences.serializer(), "PATCH", patch.toString().encodeToByteArray())
    }

    /** Switches the delivery email alone, so events chosen elsewhere stay. */
    suspend fun saveEmailOnDelivery(enabled: Boolean, timezone: String): NotificationPreferences {
        val patch = kotlinx.serialization.json.buildJsonObject {
            put("emailOnDelivery", JsonPrimitive(enabled))
            put("timezone", JsonPrimitive(timezone))
        }
        return request("/api/push/preferences", NotificationPreferences.serializer(), "PATCH", patch.toString().encodeToByteArray())
    }

    /**
     * This phone's address for notifications ([PushAddress]): its Firebase Cloud Messaging registration token, or, in
     * the build without Google's services, its installation token with its UnifiedPush endpoint and keys or for its
     * inbox. None of them has more than one environment, so a debug build's address is a production one too.
     */
    suspend fun registerNativePushToken(token: String, installationId: UUID, language: AppLanguage, sendTest: Boolean): Boolean {
        val address = PushAddress.of(token)
        val response = request(
            "/api/push/devices", PushSubscriptionResponse.serializer(), "POST",
            encode(
                NativePushDeviceRequest.serializer(),
                NativePushDeviceRequest(
                    token = address.token, installationId = installationId, environment = NativePushEnvironment.Production,
                    locale = NativePushLocale(language), deviceName = deviceName, sendTest = sendTest,
                    transport = address.transport, endpoint = address.endpoint, keys = address.keys,
                ),
            ),
        )
        return response.testSent
    }

    suspend fun unregisterNativePushToken(token: String) {
        val address = PushAddress.of(token)
        request(
            "/api/push/devices", OkResponse.serializer(), "DELETE",
            encode(
                DeleteNativePushDeviceRequest.serializer(),
                DeleteNativePushDeviceRequest(token = address.token, transport = address.transport),
            ),
        )
    }

    suspend fun registerLiveActivityDevice(token: String, installationId: UUID, language: AppLanguage, revocationToken: String) {
        request(
            "/api/live-activities/devices", OkResponse.serializer(), "POST",
            encode(
                LiveActivityDeviceRequest.serializer(),
                LiveActivityDeviceRequest(
                    installationId = installationId, token = token, environment = NativePushEnvironment.current(debug),
                    locale = NativePushLocale(language), revocationToken = revocationToken,
                ),
            ),
        )
    }

    suspend fun unregisterLiveActivityDevice(installationId: UUID) {
        request(
            "/api/live-activities/devices", OkResponse.serializer(), "DELETE",
            encode(DeleteLiveActivityDeviceRequest.serializer(), DeleteLiveActivityDeviceRequest(installationId = installationId)),
        )
    }

    suspend fun registerLiveActivityUpdateToken(activityId: String, parcelId: UUID, token: String, installationId: UUID, language: AppLanguage) {
        request(
            "/api/live-activities/activities", OkResponse.serializer(), "POST",
            encode(
                LiveActivityUpdateTokenRequest.serializer(),
                LiveActivityUpdateTokenRequest(
                    installationId = installationId, activityId = activityId, parcelId = parcelId, token = token,
                    environment = NativePushEnvironment.current(debug), locale = NativePushLocale(language),
                ),
            ),
        )
    }

    suspend fun unregisterLiveActivityUpdateToken(activityId: String, installationId: UUID) {
        request(
            "/api/live-activities/activities", OkResponse.serializer(), "DELETE",
            encode(
                DeleteLiveActivityUpdateTokenRequest.serializer(),
                DeleteLiveActivityUpdateTokenRequest(installationId = installationId, activityId = activityId),
            ),
        )
    }

    suspend fun exportAccount(): ByteArray = rawRequest("/api/account/export").body

    suspend fun deleteAccount(confirmation: String, appleAuthorizationCode: String? = null) {
        request(
            "/api/account", OkResponse.serializer(), "DELETE",
            encode(DeleteAccountRequest.serializer(), DeleteAccountRequest(confirmation = confirmation, appleAuthorizationCode = appleAuthorizationCode)),
        )
    }

    /**
     * The account's forwarding address for adding parcels by email ([AddByEmailResponseReader]), or null when the server
     * offers this account none: it answers 404 until the feature is set up and open to the account.
     */
    suspend fun readAddByEmail(): AddByEmailResponse? {
        val response = rawRequest(ADD_BY_EMAIL, missing = 404)
        if (response.statusCode == 404) return null
        return decodeAnswer(AddByEmailResponseReader, response.body)
    }

    /** Makes the forwarding address, or with `renew` a new one in its place, whose old one stops working at once. */
    suspend fun openAddByEmail(renew: Boolean = false): AddByEmailResponse = request(
        ADD_BY_EMAIL, AddByEmailResponseReader, "POST", (if (renew) """{"renew":true}""" else "{}").toByteArray(Charsets.UTF_8),
    )

    /** Turns adding by email off: emails to the address are dropped. The answer has no body. */
    suspend fun closeAddByEmail() {
        rawRequest(ADD_BY_EMAIL, "DELETE")
    }

    /** Switches the notification of each parcel an email adds on or off: the address as it then is. */
    suspend fun setAddByEmailNotify(notify: Boolean): AddByEmailResponse = request(
        ADD_BY_EMAIL, AddByEmailResponseReader, "PATCH", """{"notify":$notify}""".toByteArray(Charsets.UTF_8),
    )

    /**
     * Peek's address for assistants and the ones connected to the account ([AssistantsResponseReader]), or null when
     * assistants may connect to no account or not to this one: the server answers 404.
     */
    suspend fun readAssistants(): AssistantsResponse? {
        val response = rawRequest(ASSISTANTS, missing = 404)
        if (response.statusCode == 404) return null
        return decodeAnswer(AssistantsResponseReader, response.body)
    }

    /** Switches the notification of each parcel an assistant adds on or off: the offer as it then is. */
    suspend fun setAssistantsNotify(on: Boolean): AssistantsResponse = request(
        ASSISTANTS, AssistantsResponseReader, "PATCH", """{"notifyAdded":$on}""".toByteArray(Charsets.UTF_8),
    )

    /** Disconnects an assistant: its tokens stop working at once. One already gone (404) counts as done. The answer has no body. */
    suspend fun revokeAssistant(id: UUID) {
        rawRequest("$ASSISTANTS/${id.toString().lowercase()}", "DELETE", missing = 404)
    }

    /** The account's waits for shops that haven't shipped yet ([ShopWaitsResponseReader]). */
    suspend fun shopWaits(): List<ShopWait> = request(SHOP_WAITS, ShopWaitsResponseReader).shopWaits

    /** Stops waiting for a shop: Peek asks it no more. One already gone (404) counts as done. */
    suspend fun removeShopWait(id: UUID) {
        rawRequest("$SHOP_WAITS/${id.toString().lowercase()}", "DELETE", missing = 404)
    }

    private fun <T> encode(serializer: SerializationStrategy<T>, value: T): ByteArray = JsonEncoder.deliveryTracker.encode(serializer, value)

    private suspend fun <T> request(path: String, response: DeserializationStrategy<T>, method: String = "GET", body: ByteArray? = null): T =
        decodeAnswer(response, rawRequest(path, method, body).body)

    private suspend fun <T> decodeAnswer(response: DeserializationStrategy<T>, data: ByteArray): T =
        try {
            // A full parcel list with its histories is large; decode it off the main thread.
            withContext(decoding) { decodeSwiftKeys(response, data) }
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            throw DeliveryApiError.InvalidResponse()
        }

    /** `missing`: a status that means the thing asked for isn't there, answered rather than thrown. */
    private suspend fun performRawRequest(
        path: String,
        method: String = "GET",
        body: ByteArray? = null,
        missing: Int? = null,
        headers: Map<String, String> = emptyMap(),
        timeout: Double = 30.0,
    ): HttpResponse {
        val generation = session.generation
        session.checkGeneration(generation)
        if (!session.signedIn) throw DeliveryApiError.AuthenticationExpired()
        val url = configuration.apiBaseUrl.resolvingPath(path) ?: throw DeliveryApiError.InvalidResponse()
        val signs = signsWithAppAttest(path, method)

        suspend fun perform(token: String?): HttpResponse {
            session.checkGeneration(generation)
            val sent = linkedMapOf("X-Requested-With" to "XMLHttpRequest", "Accept" to "application/json", "X-Peek-Inputs" to CLIENT_INPUTS)
            if (body != null) sent["Content-Type"] = "application/json"
            sent.putAll(headers)
            if (token != null) sent["Authorization"] = "Bearer $token"
            val request = HttpRequest(url, method, sent, body, timeout = timeout)
            val result = try {
                if (signs) verification.sendSignedIn(request, configuration.apiBaseUrl, using = transport) else transport.data(request)
            } catch (error: Exception) {
                throw Maintenance.failure(error)
            }
            session.checkGeneration(generation)
            // An App Attest refusal says nothing about the session, so it never signs the user out.
            if (NativeVerification.needsVerification(result)) throw NativeVerificationFailed()
            return result
        }

        var token = session.accessToken()
        session.checkGeneration(generation)
        var result = perform(token)
        // Only the service's 401 is about the session: its own 403s ask for the device's check, handled above, and a
        // 403 from the network in front of it, such as a firewall's page, says nothing about the sign-in.
        if (result.statusCode == 401) {
            token = session.accessToken(forceRefresh = true)
            session.checkGeneration(generation)
            if (token != null) result = perform(token)
        }
        session.checkGeneration(generation)
        if (result.statusCode == 401) {
            session.forceSignOut()
            throw DeliveryApiError.AuthenticationExpired()
        }
        if (result.statusCode !in 200 until 300 && result.statusCode != missing) {
            Maintenance.serverDown(result)?.let { throw it }
            // The plain JSONDecoder, as on the iPhone: the keys exactly as the contract names them.
            val error = try {
                decodeJson(ErrorResponse.serializer(), result.body)
            } catch (_: Exception) {
                null
            }
            if (result.statusCode == 429) {
                if (error != null && allowanceUsed(error)) throw DeliveryApiError.AllowanceUsed()
                throw DeliveryApiError.RateLimited(retryAfterSeconds(result.header("Retry-After"), now()))
            }
            val packageId = error?.packageId
            if (result.statusCode == 409 && packageId != null) throw DeliveryApiError.DuplicateTracking(packageId)
            // A note's file the server refused: too big, of another kind, or no room left.
            val code = error?.code
            if (code != null && code != ErrorResponseCode.RecentSignIn || (path.endsWith("/files") && result.statusCode in setOf(413, 415))) {
                throw FileRefused(result.statusCode, code)
            }
            error?.error?.let { throw DeliveryApiError.Service(it) }
            throw DeliveryApiError.ServiceFailed(result.statusCode)
        }
        return result
    }

    private suspend fun rawRequest(
        path: String,
        method: String = "GET",
        body: ByteArray? = null,
        missing: Int? = null,
        headers: Map<String, String> = emptyMap(),
        timeout: Double = 30.0,
    ): HttpResponse {
        val event = AnalyticsCatalog.bundled?.event(path, method, body.takeIf { headers["Content-Type"] == null })
        try {
            val result = performRawRequest(path, method, body, missing, headers, timeout)
            if (event != null) analytics(event, if (result.statusCode == 202) DeliveryAnalytics.Outcome.Accepted else DeliveryAnalytics.Outcome.Success)
            return result
        } catch (error: Exception) {
            if (event != null && error !is CancellationException) analytics(event, DeliveryAnalytics.Outcome.Error)
            throw error
        }
    }

    companion object {
        /** Asks whether an invitation code leads to someone, and their name. No sign-in. */
        suspend fun invitationPreview(code: String, configuration: AppConfiguration, transport: HttpTransport): String {
            val request = HttpRequest(
                url = configuration.apiBaseUrl.appendingPath("api/friends/invite-preview"),
                method = "POST",
                headers = mapOf("Content-Type" to "application/json"),
                body = jsonObject(mapOf("code" to code)),
                timeout = 15.0,
            )
            val response = transport.data(request)
            if (response.statusCode == 400 || response.statusCode == 404) throw DeliveryApiError.Service("Invitation unavailable")
            if (response.statusCode != 200) throw DeliveryApiError.ServiceFailed(response.statusCode)
            // As in Swift, an answer that is not one throws the decoder's error: a DecodingError, which quotes nothing.
            val result = decodeSwiftKeys(FriendsActionResponse.serializer(), response.body)
            val name = result.previewNickname
            if (name == null || name.isEmpty() || name.codePointCount(0, name.length) > 24) throw DeliveryApiError.InvalidResponse()
            return name
        }

        /**
         * Whether a carrier picked by hand refuses the number as one it does not issue. The server runs the carrier's
         * own check, which sends the number nowhere: no sign-in or cookies.
         */
        suspend fun numberRefusal(value: NumberRefusalRequest, configuration: AppConfiguration, transport: HttpTransport): Boolean {
            val request = HttpRequest(
                url = configuration.apiBaseUrl.appendingPath("api/public/refusal"),
                method = "POST",
                headers = linkedMapOf("Accept" to "application/json", "Content-Type" to "application/json"),
                body = JsonEncoder.deliveryTracker.encode(NumberRefusalRequest.serializer(), value),
                timeout = 15.0,
                handlesCookies = false,
            )
            val response = transport.data(request)
            if (response.statusCode != 200) throw DeliveryApiError.ServiceFailed(response.statusCode)
            return try {
                decodeSwiftKeys(NumberRefusalResponse.serializer(), response.body).refused
            } catch (_: Exception) {
                throw DeliveryApiError.InvalidResponse()
            }
        }

        /**
         * Reads a parcel link: no sign-in and no cookies. Nil when the link leads nowhere, whether it was forgotten or
         * never existed. A link whose owner stopped sharing it throws `ParcelLinkStopped`. `key`: the link's owner key,
         * only when the site handed the parcel over to this phone. It goes in a header alone.
         */
        suspend fun publicParcel(linkId: String, key: String? = null, configuration: AppConfiguration, transport: HttpTransport): PublicParcelResponse? {
            val headers = linkedMapOf("Accept" to "application/json", "X-Peek-Inputs" to CLIENT_INPUTS)
            if (key != null) headers["X-Parcel-Key"] = key
            val request = HttpRequest(
                url = configuration.apiBaseUrl.appendingPath("api/public/parcels").appendingPath(linkId),
                headers = headers,
                timeout = 15.0,
                handlesCookies = false,
            )
            val response = try {
                transport.data(request)
            } catch (error: Exception) {
                throw Maintenance.failure(error)
            }
            return when (response.statusCode) {
                200 -> try {
                    decodeSwiftKeys(PublicParcelResponse.serializer(), response.body)
                } catch (_: Exception) {
                    throw DeliveryApiError.InvalidResponse()
                }
                404 -> if (response.isServiceNotFound) null else throw Maintenance.serverDown(response) ?: DeliveryApiError.ServiceFailed(404)
                410 -> throw ParcelLinkStopped()
                429 -> throw DeliveryApiError.RateLimited(retryAfterSeconds(response.header("Retry-After")))
                else -> throw Maintenance.serverDown(response) ?: DeliveryApiError.ServiceFailed(response.statusCode)
            }
        }

        /** Whether a refusal is the day's allowance used up, which lasts until tomorrow, as the web's `userErrorKey` tells it. */
        fun allowanceUsed(error: ErrorResponse): Boolean =
            error.scope == ErrorResponseScope.Daily || error.error.contains("allowance is used up", ignoreCase = true)

        /** Requests that have carriers asked or checks queued; the server counts their App Attest signatures. */
        fun signsWithAppAttest(path: String, method: String): Boolean {
            if (method != "POST") return false
            val parts = path.split('/').filter { it.isNotEmpty() }
            return path in listOf("/api/packages", "/api/carriers/detect", "/api/sync") ||
                (parts.size == 4 && parts[0] == "api" && parts[1] == "packages" && uuid(parts[2]) != null && parts[3] == "sync")
        }

        // ASCII digits, as `TimeInterval(_:)` reads them: Android's regular expressions are ICU's, whose `\d` is any digit.
        private val decimal = Regex("^[+-]?(?:[0-9]+\\.?[0-9]*|\\.[0-9]+)(?:[eE][+-]?[0-9]+)?$")
        // strtod's hexadecimal floats, which `TimeInterval(_:)` takes with a lowercase x only.
        private val hexadecimal = Regex("^[+-]?0x(?:[0-9a-fA-F]+\\.?[0-9a-fA-F]*|\\.[0-9a-fA-F]+)(?:[pP][+-]?[0-9]+)?$")

        /**
         * `TimeInterval(_:)`: the text up to any NUL, as C's strtod reads it whole. Nil for inf and nan too, which
         * it reads but the caller never takes.
         */
        private fun timeInterval(value: String): Double? {
            val text = value.substringBefore('\u0000')
            return when {
                decimal.matches(text) -> text.toDouble()
                hexadecimal.matches(text) -> (if ('p' in text || 'P' in text) text else text + "p0").toDouble()
                else -> null
            }.takeIf { it?.isFinite() == true }
        }

        /** Swift's `max(0, value)`, which keeps -0.0 and gives 0 for NaN. */
        private fun nonNegative(value: Double): Double = if (value >= 0.0) value else 0.0

        /**
         * A `Retry-After` header in seconds, given either as seconds or as an HTTP date. Read as the iPhone reads
         * it: a hexadecimal number counts, and the date is DateFormatter's ("EEE, dd MMM yyyy HH:mm:ss zzz").
         */
        fun retryAfterSeconds(value: String?, now: Instant = AppClock.now()): Double {
            if (value == null) return 0.0
            timeInterval(value)?.let { return nonNegative(it) }
            val date = FoundationDates.httpDate(value) ?: return 0.0
            return nonNegative(date - now.timeIntervalSinceReferenceDate)
        }
    }
}

/** SessionStore's `checkGeneration`: a cancelled coroutine, or an identity changed since `expected`, ends as a cancellation. */
suspend fun DeliveryApiClient.Session.checkGeneration(expected: UUID) {
    currentCoroutineContext().ensureActive()
    if (generation != expected) throw CancellationException("The signed-in account changed.")
}

/**
 * Contains only a capability to DELETE one registration, never login credentials. Persist before sending
 * registration so even an interrupted response is recoverable. Used from the main thread, as on the iPhone, where an
 * actor keeps it to one thread; here each change of the registrations is made under a lock, which no request is sent
 * under, so a drain or retry on another thread loses none.
 */
class LiveActivityRevocations(
    private val configuration: AppConfiguration,
    private val transport: HttpTransport,
    private val persistence: SessionPersistence,
) {
    @Serializable
    data class Registration(
        @SerialName("ownerID") @Serializable(with = UuidSerializer::class) val ownerId: UUID,
        @SerialName("installationID") @Serializable(with = UuidSerializer::class) val installationId: UUID,
        val revocationToken: String,
        val pending: Boolean,
    ) {
        /** The token is a capability: it never goes in a log. */
        override fun toString(): String =
            "Registration(ownerId=$ownerId, installationId=$installationId, revocationToken=<redacted>, pending=$pending)"
    }

    private val format = ListSerializer(Registration.serializer())
    private val lock = Any()

    // Read as KeychainStore reads it, with JSONDecoder.deliveryTracker.
    private var registrations: List<Registration> =
        persistence.load()?.let { data -> runCatching { decodeSwiftKeys(format, data) }.getOrNull() } ?: emptyList()
    private var retryTask: Job? = null

    fun registration(ownerId: UUID, installationId: UUID): Registration {
        synchronized(lock) {
            registrations.firstOrNull { !it.pending && it.ownerId == ownerId && it.installationId == installationId }?.let { return it }
            val token = (UUID.randomUUID().uuidString + UUID.randomUUID().uuidString).replace("-", "").lowercase(Locale.ROOT)
            val value = Registration(ownerId = ownerId, installationId = installationId, revocationToken = token, pending = false)
            persist(registrations + value)
            return value
        }
    }

    fun queue(except: UUID? = null) {
        synchronized(lock) {
            val queued = registrations.map { if (it.ownerId != except) it.copy(pending = true) else it }
            // Polls with Live Activities off ask again every time; skip the Keychain write.
            if (queued == registrations) return
            persist(queued)
        }
    }

    /** Under the lock. */
    private fun persist(values: List<Registration>) {
        persistence.save(JsonEncoder.deliveryTracker.encode(format, values))
        registrations = values
    }

    suspend fun drain() {
        for (value in synchronized(lock) { registrations.filter { it.pending } }) {
            if (!currentCoroutineContext().isActive) return
            try {
                val request = HttpRequest(
                    url = configuration.apiBaseUrl.appendingPath("api/live-activities/revoke"),
                    method = "POST",
                    headers = mapOf("Content-Type" to "application/json"),
                    body = JsonEncoder.deliveryTracker.encode(
                        RevokeLiveActivityDeviceRequest.serializer(),
                        RevokeLiveActivityDeviceRequest(installationId = value.installationId, revocationToken = value.revocationToken),
                    ),
                    timeout = 10.0,
                )
                val response = transport.data(request)
                if (response.statusCode !in 200..299) continue
                synchronized(lock) { persist(registrations.filter { it.revocationToken != value.revocationToken }) }
            } catch (_: Exception) {
                // Keep the durable revocation for a later network opportunity.
            }
        }
    }

    /**
     * Drains now and every 30 seconds while anything is pending, in `scope` (Swift's `Task`, on the main actor): the
     * app's own, on the main thread, which nothing cancels while the app runs (the `MainScope()` the application keeps
     * and gives every store). However the loop ends, a scope cancelled before it
     * began included, the next call may start another.
     */
    fun retry(scope: CoroutineScope) {
        val task = synchronized(lock) {
            if (retryTask != null || registrations.none { it.pending }) return
            scope.launch(start = CoroutineStart.LAZY) {
                while (isActive) {
                    drain()
                    // Seen and let go at once, so a revocation queued meanwhile never finds a loop about to end.
                    val done = synchronized(lock) {
                        registrations.none { it.pending }.also { done -> if (done && retryTask === coroutineContext.job) retryTask = null }
                    }
                    if (done) break
                    delay(30.seconds)
                }
            }.also { task ->
                retryTask = task
                task.invokeOnCompletion { synchronized(lock) { if (retryTask === task) retryTask = null } }
            }
        }
        // Started outside the lock: a dispatcher that runs it at once would otherwise send under it.
        task.start()
    }
}

/** The APNs environment this build's Live Activity tokens belong to: development in a debug build. */
fun NativePushEnvironment.Companion.current(debug: Boolean): NativePushEnvironment =
    if (debug) NativePushEnvironment.Development else NativePushEnvironment.Production

/** The language notifications are written in: the app's own. */
fun NativePushLocale(language: AppLanguage): NativePushLocale = NativePushLocale.fromRawValue(language.rawValue) ?: NativePushLocale.En

/** `JSONSerialization.data(withJSONObject:)` for an object of strings, in the order given. */
internal fun jsonObject(values: Map<String, String>): ByteArray =
    JsonEncoder().encode(MapSerializer(String.serializer(), String.serializer()), values)

private fun ByteArray.startsWith(vararg prefix: Int): Boolean =
    size >= prefix.size && prefix.indices.all { this[it].toInt() and 0xFF == prefix[it] }

/**
 * The text of a JSON body as JSONDecoder finds it: UTF-8, UTF-16 or UTF-32 by a byte order mark, which is skipped,
 * else by where the first bytes are zero. A UTF-16 or UTF-32 body is nil when it is not text in its encoding, and
 * its last bytes that make no whole unit are left out.
 */
internal fun jsonText(data: ByteArray): String? {
    val (unit, bigEndian, skip) = when {
        data.startsWith(0xEF, 0xBB, 0xBF) -> Triple(1, true, 3)
        data.startsWith(0x00, 0x00, 0xFE, 0xFF) -> Triple(4, true, 4)
        data.startsWith(0xFF, 0xFE) -> Triple(2, false, 2)
        data.startsWith(0xFE, 0xFF) -> Triple(2, true, 2)
        data.size >= 4 -> {
            val zero = BooleanArray(4) { data[it].toInt() == 0 }
            when {
                zero[0] && zero[1] && zero[2] -> Triple(4, true, 0)
                zero[1] && zero[2] && zero[3] -> Triple(4, false, 0)
                zero[0] && zero[2] -> Triple(2, true, 0)
                zero[1] && zero[3] -> Triple(2, false, 0)
                else -> Triple(1, true, 0)
            }
        }
        // Two bytes are read by the zero one; three never are.
        data.size == 2 && data[0].toInt() == 0 -> Triple(2, true, 0)
        data.size == 2 && data[1].toInt() == 0 -> Triple(2, false, 0)
        else -> Triple(1, true, 0)
    }
    // JSONDecoder checks UTF-8 only in the keys and strings it reads: bytes that are not UTF-8 in a field it
    // skips pass. Read as U+FFFD, they pass the same way.
    if (unit == 1) return String(data, skip, data.size - skip, Charsets.UTF_8)
    val text = StringBuilder()
    var pendingHigh = -1
    for (start in skip until data.size - (data.size - skip) % unit step unit) {
        var value = 0L
        for (index in 0 until unit) {
            value = value shl 8 or (data[start + if (bigEndian) index else unit - 1 - index].toLong() and 0xFF)
        }
        val surrogate = value in 0xD800L..0xDFFFL
        if (unit == 4) {
            if (surrogate || value > 0x10FFFF) return null
            text.appendCodePoint(value.toInt())
        } else if (pendingHigh >= 0) {
            if (value !in 0xDC00L..0xDFFFL) return null
            text.append(pendingHigh.toChar()).append(value.toInt().toChar())
            pendingHigh = -1
        } else if (value in 0xD800L..0xDBFFL) {
            pendingHigh = value.toInt()
        } else if (surrogate) {
            return null
        } else {
            text.append(value.toInt().toChar())
        }
    }
    return if (pendingHigh >= 0) null else text.toString()
}

/**
 * `URL(string: path, relativeTo: base)?.absoluteURL` for a path from the root, as the client's are: the base's scheme
 * and authority, the path's own path and query. Nil when the base is no URL.
 */
internal fun String.resolvingPath(path: String): String? {
    val base = UrlComponents.parse(this) ?: return null
    val queryStart = path.indexOf('?').let { if (it < 0) path.length else it }
    base.percentEncodedPath = path.substring(0, queryStart)
    base.percentEncodedQuery = if (queryStart < path.length) path.substring(queryStart + 1) else null
    base.percentEncodedFragment = null
    return base.url
}
