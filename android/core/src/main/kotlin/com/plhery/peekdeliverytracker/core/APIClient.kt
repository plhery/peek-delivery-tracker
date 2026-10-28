package com.plhery.peekdeliverytracker.core

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
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.NumberRefusalResponse
import com.plhery.peekdeliverytracker.core.api.OkResponse
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.PackageNotificationRequest
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
import com.plhery.peekdeliverytracker.core.api.SyncJobListResponse
import com.plhery.peekdeliverytracker.core.api.SyncJobStatus
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.seconds
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
}

/**
 * URLSession's `data(for:)`, which the app implements with OkHttp. Every status is an answer, never an exception. A
 * request that could not be sent, a network failure and a connection that timed out throw an `IOException` (Swift's
 * `URLError`); a call the app cancelled ends in a `CancellationException`.
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
    class Service(val serviceMessage: String) : DeliveryApiError(serviceMessage)
    class ServiceFailed(val status: Int) : DeliveryApiError("Delivery service failed ($status).")
}

/**
 * The account's requests. Used from the main thread, as the iPhone's client is `@MainActor`; a large answer is read
 * off it. `deviceName` is the name the account's device list shows for this one (`UIDevice.current.name`); `region`
 * the device's region at the moment, whose country a detection sends (`ParcelLookupCountry.hint` says which locale);
 * `debug` says the build's push tokens are development ones (`#if DEBUG`); `analytics` counts the catalog's
 * operations (`DeliveryAnalytics.shared.action`). `verification` is the app's one NativeVerification.
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
    private val now: () -> Instant = Instant::now,
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

    suspend fun detectCarrier(trackingNumber: String): CarrierDetectionResponse = request(
        "/api/carriers/detect", CarrierDetectionResponse.serializer(), "POST",
        encode(
            CarrierDetectionRequest.serializer(),
            CarrierDetectionRequest(trackingNumber = trackingNumber, lookupCountryHint = ParcelLookupCountry.hint(region())),
        ),
    )

    suspend fun add(input: CreatePackageRequest): CreatePackageResponse =
        request("/api/packages", CreatePackageResponse.serializer(), "POST", encode(CreatePackageRequest.serializer(), input))

    suspend fun rename(id: UUID, label: String): Parcel = request(
        "/api/packages/${id.uuidString}", Parcel.serializer(), "PATCH",
        encode(RenamePackageRequest.serializer(), RenamePackageRequest(label = label)),
    )

    suspend fun changeCarrier(
        id: UUID,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String? = null,
    ): ChangePackageCarrierResponse = request(
        "/api/packages/${id.uuidString}/carrier", ChangePackageCarrierResponse.serializer(), "PATCH",
        encode(
            ChangePackageCarrierRequest.serializer(),
            ChangePackageCarrierRequest(
                carrier = carrier, trackingUrl = trackingUrl, dpdPostcode = dpdPostcode,
                dpdPostcodeCountry = dpdPostcodeCountry, providerPostcode = providerPostcode,
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

    suspend fun permanentlyDelete(id: UUID) {
        request("/api/packages/${id.uuidString}/permanent", OkResponse.serializer(), "DELETE")
    }

    /** Queues a check of every parcel; follow it with `waitForJobs`. */
    suspend fun queueRefreshAll(): List<UUID> = request("/api/sync", QueueResponse.serializer(), "POST").jobIds

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

    suspend fun saveNotificationPreferences(value: NotificationPreferences): NotificationPreferences {
        // Whether the server can email the account is the server's to say.
        val sent = value.copy(emailAvailable = null)
        return request("/api/push/preferences", NotificationPreferences.serializer(), "PATCH", encode(NotificationPreferences.serializer(), sent))
    }

    suspend fun registerNativePushToken(token: String, installationId: UUID, language: AppLanguage, sendTest: Boolean): Boolean {
        val response = request(
            "/api/push/devices", PushSubscriptionResponse.serializer(), "POST",
            encode(
                NativePushDeviceRequest.serializer(),
                NativePushDeviceRequest(
                    token = token, installationId = installationId, environment = NativePushEnvironment.current(debug),
                    locale = NativePushLocale(language), deviceName = deviceName, sendTest = sendTest,
                ),
            ),
        )
        return response.testSent
    }

    suspend fun unregisterNativePushToken(token: String) {
        request(
            "/api/push/devices", OkResponse.serializer(), "DELETE",
            encode(DeleteNativePushDeviceRequest.serializer(), DeleteNativePushDeviceRequest(token = token)),
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

    private fun <T> encode(serializer: SerializationStrategy<T>, value: T): ByteArray = JsonEncoder.deliveryTracker.encode(serializer, value)

    private suspend fun <T> request(path: String, response: DeserializationStrategy<T>, method: String = "GET", body: ByteArray? = null): T {
        val data = rawRequest(path, method, body).body
        return try {
            // A full parcel list with its histories is large; decode it off the main thread.
            withContext(Dispatchers.Default) { decodeSwiftKeys(response, data) }
        } catch (error: CancellationException) {
            throw error
        } catch (error: Exception) {
            throw DeliveryApiError.InvalidResponse()
        }
    }

    private suspend fun performRawRequest(path: String, method: String = "GET", body: ByteArray? = null): HttpResponse {
        val generation = session.generation
        session.checkGeneration(generation)
        if (!session.signedIn) throw DeliveryApiError.AuthenticationExpired()
        val url = configuration.apiBaseUrl.resolvingPath(path) ?: throw DeliveryApiError.InvalidResponse()
        val signs = signsWithAppAttest(path, method)

        suspend fun perform(token: String?): HttpResponse {
            session.checkGeneration(generation)
            val headers = linkedMapOf("X-Requested-With" to "XMLHttpRequest", "Accept" to "application/json")
            if (body != null) headers["Content-Type"] = "application/json"
            if (token != null) headers["Authorization"] = "Bearer $token"
            val request = HttpRequest(url, method, headers, body, timeout = 30.0)
            val result = if (signs) verification.sendSignedIn(request, configuration.apiBaseUrl, using = transport) else transport.data(request)
            session.checkGeneration(generation)
            // An App Attest refusal says nothing about the session, so it never signs the user out.
            if (NativeVerification.needsVerification(result)) throw NativeVerificationFailed()
            return result
        }

        var token = session.accessToken()
        session.checkGeneration(generation)
        var result = perform(token)
        if (result.statusCode == 401 || result.statusCode == 403) {
            token = session.accessToken(forceRefresh = true)
            session.checkGeneration(generation)
            if (token != null) result = perform(token)
        }
        session.checkGeneration(generation)
        if (result.statusCode == 401 || result.statusCode == 403) {
            session.forceSignOut()
            throw DeliveryApiError.AuthenticationExpired()
        }
        if (result.statusCode !in 200 until 300) {
            if (result.statusCode == 429) throw DeliveryApiError.RateLimited(retryAfterSeconds(result.header("Retry-After"), now()))
            // The plain JSONDecoder, as on the iPhone: the keys exactly as the contract names them.
            val error = try {
                decodeJson(ErrorResponse.serializer(), result.body)
            } catch (_: Exception) {
                null
            }
            val packageId = error?.packageId
            if (result.statusCode == 409 && packageId != null) throw DeliveryApiError.DuplicateTracking(packageId)
            error?.error?.let { throw DeliveryApiError.Service(it) }
            throw DeliveryApiError.ServiceFailed(result.statusCode)
        }
        return result
    }

    private suspend fun rawRequest(path: String, method: String = "GET", body: ByteArray? = null): HttpResponse {
        val event = AnalyticsCatalog.bundled?.event(path, method, body)
        try {
            val result = performRawRequest(path, method, body)
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
            val headers = linkedMapOf("Accept" to "application/json")
            if (key != null) headers["X-Parcel-Key"] = key
            val request = HttpRequest(
                url = configuration.apiBaseUrl.appendingPath("api/public/parcels").appendingPath(linkId),
                headers = headers,
                timeout = 15.0,
                handlesCookies = false,
            )
            val response = transport.data(request)
            return when (response.statusCode) {
                200 -> try {
                    decodeSwiftKeys(PublicParcelResponse.serializer(), response.body)
                } catch (_: Exception) {
                    throw DeliveryApiError.InvalidResponse()
                }
                404 -> null
                410 -> throw ParcelLinkStopped()
                429 -> throw DeliveryApiError.RateLimited(retryAfterSeconds(response.header("Retry-After")))
                else -> throw DeliveryApiError.ServiceFailed(response.statusCode)
            }
        }

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
        fun retryAfterSeconds(value: String?, now: Instant = Instant.now()): Double {
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

/** The APNs environment this build's tokens belong to: development in a debug build. */
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
