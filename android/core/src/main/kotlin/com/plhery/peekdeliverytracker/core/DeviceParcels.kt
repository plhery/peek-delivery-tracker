package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionRequest
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.DeleteParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.ParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkCarrierRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLinkPostcodeRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.PublicLookupRequest
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.api.UpdateParcelLinkRequest
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

// DeviceParcels.swift. The keychain item and the file are the app's: :core reads and writes the bytes they hold as the
// iPhone app does, through a `SessionPersistence` and a `DeviceParcelFile` the app gives `DeviceParcelFiles`.

/**
 * A parcel followed on this phone without an account: the link the service made for it, the key that owns the link,
 * and what this device knows about the parcel. A parcel followed through someone else's link has no key: this phone
 * reads it as anyone with the link may. On file its keys are the Swift model's (`linkID`, and the parcel's in
 * camelCase): see `DeviceParcelFiles`.
 */
@Serializable
data class DeviceParcel(
    @SerialName("linkID") val linkId: String,
    /** The name given on this device; the service never learns it. */
    val label: String,
    val archivedAt: String?,
    val parcel: Parcel,
    /** When the service last answered for it: Swift's `Date`, the seconds since 2001 it holds and JSON writes. */
    val checkedAt: Double,
    /** The parcel this one took the place of when its carrier was changed, so an open page keeps showing it. */
    @Serializable(with = UuidSerializer::class) val replaced: UUID? = null,
    /** Followed through someone else's link: read without a key, and forgotten on this phone alone. */
    val viewer: Boolean? = null,
    /** Whether an account may keep the parcel behind someone else's link, as the link last said. */
    val canKeep: Boolean? = null,
    /**
     * What its notifications on this phone tell, as last asked for: the preset chosen for every parcel on the Delivery
     * updates page, or while none is, the one picked when they were first turned on.
     */
    val alertPreset: ParcelAlertPreset? = null,
    /** The device token the service last answered about for this parcel's notifications. */
    val alertToken: String? = null,
    /**
     * Someone else's parcel forgotten here before its notifications could be turned off: out of the list, and kept only
     * until they are.
     */
    val forgotten: Boolean? = null,
    /**
     * Whether a postcode went with the lookup that made the parcel, which its link never shows: yes or no only, never
     * the postcode. Null where this phone doesn't know, such as a parcel it kept before it noted this.
     */
    val postcodeGiven: Boolean? = null,
    /**
     * The parcel's news this phone counts as seen (#72), Swift's `openedNews`: the `news_at` it had when it was last
     * opened here, or when it started as seen. Null on a parcel kept before this was noted, which shows no dot and
     * starts as seen with its next answer: what an older version kept has no `news_at` to start from.
     */
    val seenNewsAt: String? = null,
    /**
     * When the service forgot this lookup of the phone's own (30 days after delivery, or 90 without news), as the
     * website notes it: its last answer stays here, no longer updated, without its key or notifications, and is never
     * read again. A new lookup of the number takes its place.
     */
    val frozen: String? = null,
    /**
     * Its notifications turned off on this phone from its page: the service is told so, and rounds pass it over until
     * they are turned on again.
     */
    val muted: Boolean? = null,
) {
    val isViewer: Boolean get() = viewer == true

    val isFrozen: Boolean get() = frozen != null

    val isMuted: Boolean get() = muted == true

    /** The parcel as it stands, seen: what it tells now is no news. */
    fun startingSeen(): DeviceParcel = copy(seenNewsAt = parcel.newsAt ?: SEEN_BEFORE_ANY_NEWS)

    /**
     * Takes the service's newer answer. A parcel's first answer is no news, as the service starts an account's parcel:
     * the first one with a check landed, also after a change of carrier or number.
     */
    fun taking(answer: Parcel): DeviceParcel {
        val first = parcel.lastSyncedAt == null && answer.lastSyncedAt != null
        val taken = copy(parcel = answer)
        return if (first || seenNewsAt == null) taken.startingSeen() else taken
    }

    /** Leaves out the link id, which opens the parcel to anyone, and the device token. */
    override fun toString(): String = "DeviceParcel(parcel=${parcel.id}, viewer=$viewer, archived=${archivedAt != null})"

    companion object {
        /** What [seenNewsAt] holds for a parcel seen before its first scan: earlier than any news. */
        const val SEEN_BEFORE_ANY_NEWS = "1970-01-01T00:00:00Z"
    }
}

/** How the service answered when this phone asked for a parcel's notifications. */
enum class DeviceAlertAnswer {
    /** On, or the journey is over and there is nothing left to tell. */
    On,

    /** The link leads nowhere now. */
    Gone,

    /** The link has as many alerts as it may. */
    Full,

    /** Its owner stopped sharing it. */
    Stopped,

    /** The server sends no notifications to phones, or can't for now. */
    Unavailable,
}

/**
 * The last postcodes given for lookups without an account, with their carriers, newest first. They belong to this
 * phone rather than to a parcel: they outlast forgotten parcels and a sign-in, and fill in the next parcel's field. An
 * account's postcodes stay on its parcels. `defaults` is the app's standard UserDefaults stand-in.
 */
class PostcodeMemory(private val defaults: UserDefaults) {
    val entries: List<GivenPostcode>
        get() = defaults.data(key)?.let { data ->
            try {
                decodeJson(ListSerializer(GivenPostcode.serializer()), data)
            } catch (_: Exception) {
                null
            }
        } ?: emptyList()

    /** The pair goes first; given before, it moves there rather than coming twice. */
    fun remember(postcode: String, carrier: CarrierId, country: String? = null) {
        val postcode = postcode.trimmingWhitespacesAndNewlines().nonEmpty ?: return
        write(
            listOf(GivenPostcode(carrier = carrier, postcode = postcode, country = country)) +
                entries.filter { !it.carrier.rawValue.equalsCanonically(carrier.rawValue) || !bare(it.postcode).equalsCanonically(bare(postcode)) },
        )
    }

    /** Forgets the postcode for every carrier it was given to, however its spaces were written. */
    fun forget(postcode: String) {
        write(entries.filter { !bare(it.postcode).equalsCanonically(bare(postcode)) })
    }

    private fun write(entries: List<GivenPostcode>) {
        if (entries.isEmpty()) {
            defaults.removeObject(key)
            return
        }
        // Swift's plain JSONEncoder writes its keys in an order of its own each launch; here they follow the declaration.
        defaults.set(JsonEncoder().encode(ListSerializer(GivenPostcode.serializer()), entries.take(limit)), key)
    }

    companion object {
        private const val key = "sdt.postcodeMemory.v1"

        /** Most parcels go to the same few addresses. */
        const val limit = 5

        private fun bare(postcode: String): String = postcode.uppercased().removingWhitespace()
    }
}

/** Lookups are counted for each network; the day's count is spent. */
class DeviceLookupsSpent : Exception() {
    override fun equals(other: Any?): Boolean = other is DeviceLookupsSpent
    override fun hashCode(): Int = DeviceLookupsSpent::class.hashCode()
}

/**
 * Why the delivery postcode a link asks for filed nothing ([DeviceParcelClient.addPostcode]), with the carrier it was
 * given for, which the message names: the website's `link.postcode.*` lines.
 */
sealed class DevicePostcodeRefusal(val carrier: CarrierId) : Exception() {
    /** The carrier has no parcel of the number for that postcode (422). */
    class Refused(carrier: CarrierId) : DevicePostcodeRefusal(carrier)

    /** Too many postcodes were tried for the link, or the carrier takes none for a while (429): until [until]. */
    class Tries(carrier: CarrierId, val until: Instant) : DevicePostcodeRefusal(carrier)

    /** The carrier couldn't be asked just now (503). */
    class Unreachable(carrier: CarrierId) : DevicePostcodeRefusal(carrier)
}

/**
 * How the device's parcels reach the service: no sign-in and no cookies, the link's id and its owner key only.
 *
 * Each call throws as the iPhone's does: `DeliveryApiError` for the service's answers, `DeviceLookupsSpent` once the
 * day's lookups are spent, an `IOException` for a network failure or a connection that timed out, and a
 * `CancellationException` when the call was cancelled.
 */
class DeviceParcelClient(
    val lookup: suspend (PublicLookupRequest) -> PublicLookupResponse,
    /**
     * Nil when the service no longer has the parcel, or no longer shares it with a reader without the key: its own 404 or
     * 410. A proxy's 404 while the server restarts throws, and the parcel stays.
     */
    val read: suspend (linkId: String, key: String?) -> PublicParcelResponse?,
    val forget: suspend (linkId: String, key: String) -> Unit,
    /** Changes what the link shows to others, or stops and resumes its sharing. */
    val update: suspend (linkId: String, key: String, UpdateParcelLinkRequest) -> PublicParcelResponse,
    /**
     * `prefixOfSaved`: the number is the start of a longer one this phone follows, most likely that number typed again.
     * `typedNumber`: the number as typed, when it differs ([typedTrackingNumber]).
     */
    val detect: suspend (trackingNumber: String, prefixOfSaved: Boolean, typedNumber: String?) -> CarrierDetectionResponse,
    /** What a reader says of the parcel behind a link. The owner key goes along when this phone holds it. */
    val feedback: suspend (linkId: String, key: String?, ParcelFeedbackRequest) -> Unit = { _, _, _ -> },
    /** Turns this phone's notifications on for the parcel behind a link, with the owner key when this phone holds it. */
    val setAlert: suspend (linkId: String, key: String?, ParcelAppAlertRequest) -> DeviceAlertAnswer = { _, _, _ -> DeviceAlertAnswer.Unavailable },
    /** Turns them off. Anyone with the link and the token may. */
    val removeAlert: suspend (linkId: String, DeleteParcelAppAlertRequest) -> Unit = { _, _ -> },
    /**
     * Moves the owner's link to another carrier, link, postcode or phone digits, a lookup that keeps the link: null when
     * the service has no such link for the key (its 404), and none for a client that can't, which looks the number up
     * again instead.
     */
    val changeCarrier: (suspend (linkId: String, key: String, ParcelLinkCarrierRequest) -> PublicParcelResponse?)? = null,
    /**
     * The owner's read by hand, as [read] answers: it asks the service to check the parcel before a check is due
     * (`refresh=true`), which the service does when the last one is old enough and the carrier allows. None for a
     * client that can't, which reads as usual.
     */
    val check: (suspend (linkId: String, key: String) -> PublicParcelResponse?)? = null,
    /**
     * Gives the delivery postcode the owner's link asks for (`link.inputNeeded`), which the service tries with that
     * carrier before it keeps anything: the link as it then stands, its parcel filed under the carrier, or as it stands
     * already when nothing is asked any more; null when the service has no such link for the key (its 404). A postcode
     * the carrier refuses, too many tried or a carrier that can't be asked throw [DevicePostcodeRefusal]. None for a
     * client that can't, which moves the link to the carrier instead.
     */
    val addPostcode: (suspend (linkId: String, key: String, ParcelLinkPostcodeRequest) -> PublicParcelResponse?)? = null,
) {
    companion object {
        /**
         * The service's public parcel API, as the iPhone app calls it. Every request asks for JSON past any cache, with a
         * 30-second timeout, no cookies (none sent, none kept) and no `Authorization`; `X-Parcel-Key` carries the owner
         * key, only when there is one. A lookup and a detection go through `verification`, and a lookup alone may
         * present the browser check. A detection sends `region`'s country as its hint: the device's region,
         * `Resources.getSystem().configuration.locales[0]`, which the app's own language leaves as it is (never
         * `Locale.getDefault()`).
         */
        fun api(
            configuration: AppConfiguration,
            transport: HttpTransport,
            verification: NativeVerification,
            region: () -> Locale,
        ): DeviceParcelClient {
            suspend fun send(
                path: String,
                method: String,
                key: String? = null,
                body: ByteArray? = null,
                query: List<UrlComponents.QueryItem> = emptyList(),
            ): HttpResponse {
                val headers = linkedMapOf("Accept" to "application/json")
                if (key != null) headers["X-Parcel-Key"] = key
                if (body != null) headers["Content-Type"] = "application/json"
                val url = configuration.apiBaseUrl.appendingPath(path)
                val address = if (query.isEmpty()) url else url.appendingQueryItems(query)
                val request = HttpRequest(address, method, headers, body, timeout = 30.0, handlesCookies = false)
                // A new carrier for a link asks the carrier as a lookup does, and takes the same proof.
                val moving = method == "PATCH" && path.endsWith("/carrier")
                if (moving || method == "POST" && (path == "api/public/parcels" || path == "api/public/detect")) {
                    return verification.send(request, configuration.apiBaseUrl, allowPresentation = path != "api/public/detect", using = transport)
                }
                return try {
                    transport.data(request)
                } catch (error: Exception) {
                    throw Maintenance.failure(error)
                }
            }

            fun failure(response: HttpResponse): Exception {
                val payload = JsonSerialization.stringsOfObject(response.body)
                if (response.statusCode == 429) {
                    if (payload?.get("scope") == "daily") return DeviceLookupsSpent()
                    return DeliveryApiError.RateLimited(response.header("Retry-After")?.let(::swiftDouble) ?: 60.0)
                }
                val message = payload?.get("error")
                if (response.statusCode == 400 && message != null) return DeliveryApiError.Service(message)
                return Maintenance.serverDown(response) ?: DeliveryApiError.ServiceFailed(response.statusCode)
            }

            // JSONDecoder.deliveryTracker reads each key convertFromSnakeCase, as decodeSwiftKeys does.
            fun <T> decode(deserializer: DeserializationStrategy<T>, response: HttpResponse): T =
                try {
                    decodeSwiftKeys(deserializer, response.body)
                } catch (_: Exception) {
                    throw DeliveryApiError.InvalidResponse()
                }

            val encoder = JsonEncoder.deliveryTracker
            return DeviceParcelClient(
                lookup = { request ->
                    val response = send("api/public/parcels", "POST", body = encoder.encode(PublicLookupRequest.serializer(), request))
                    if (response.statusCode != 201 && response.statusCode != 200) throw failure(response)
                    decode(PublicLookupResponse.serializer(), response)
                },
                read = { linkId, key ->
                    val response = send("api/public/parcels/$linkId", "GET", key)
                    if (response.statusCode == 410 || response.isServiceNotFound) {
                        null
                    } else {
                        if (response.statusCode != 200) throw failure(response)
                        decode(PublicParcelResponse.serializer(), response)
                    }
                },
                check = { linkId, key ->
                    val refresh = listOf(UrlComponents.QueryItem("refresh", "true"))
                    val response = send("api/public/parcels/$linkId", "GET", key, query = refresh)
                    if (response.statusCode == 410 || response.isServiceNotFound) {
                        null
                    } else {
                        if (response.statusCode != 200) throw failure(response)
                        decode(PublicParcelResponse.serializer(), response)
                    }
                },
                forget = { linkId, key ->
                    val response = send("api/public/parcels/$linkId", "DELETE", key)
                    if (response.statusCode != 204 && !response.isServiceNotFound) throw failure(response)
                },
                update = { linkId, key, change ->
                    val response = send("api/public/parcels/$linkId", "PATCH", key, encoder.encode(UpdateParcelLinkRequest.serializer(), change))
                    if (response.statusCode != 200) throw failure(response)
                    decode(PublicParcelResponse.serializer(), response)
                },
                detect = { trackingNumber, prefixOfSaved, typedNumber ->
                    val body = encoder.encode(
                        CarrierDetectionRequest.serializer(),
                        CarrierDetectionRequest(
                            trackingNumber = trackingNumber, lookupCountryHint = ParcelLookupCountry.hint(region()),
                            prefixOfSaved = if (prefixOfSaved) true else null, typedNumber = typedNumber,
                        ),
                    )
                    val response = send("api/public/detect", "POST", body = body)
                    if (response.statusCode != 200) throw failure(response)
                    decode(CarrierDetectionResponse.serializer(), response)
                },
                feedback = { linkId, key, answer ->
                    val response = send("api/public/parcels/$linkId/feedback", "POST", key, encoder.encode(ParcelFeedbackRequest.serializer(), answer))
                    if (response.statusCode != 204) throw failure(response)
                },
                setAlert = { linkId, key, alert ->
                    val response = send("api/public/parcels/$linkId/app-alerts", "PUT", key, encoder.encode(ParcelAppAlertRequest.serializer(), alert))
                    when (response.statusCode) {
                        204 -> DeviceAlertAnswer.On
                        404 -> if (response.isServiceNotFound) DeviceAlertAnswer.Gone else throw failure(response)
                        409 -> DeviceAlertAnswer.Full
                        410 -> DeviceAlertAnswer.Stopped
                        503 -> DeviceAlertAnswer.Unavailable
                        else -> throw failure(response)
                    }
                },
                removeAlert = { linkId, alert ->
                    val response = send("api/public/parcels/$linkId/app-alerts", "DELETE", body = encoder.encode(DeleteParcelAppAlertRequest.serializer(), alert))
                    if (response.statusCode != 204) throw failure(response)
                },
                changeCarrier = { linkId, key, change ->
                    val response = send("api/public/parcels/$linkId/carrier", "PATCH", key, encoder.encode(ParcelLinkCarrierRequest.serializer(), change))
                    if (response.isServiceNotFound) {
                        null
                    } else {
                        if (response.statusCode != 200) throw failure(response)
                        decode(PublicParcelResponse.serializer(), response)
                    }
                },
                addPostcode = { linkId, key, request ->
                    val response = send("api/public/parcels/$linkId/postcode", "POST", key, encoder.encode(ParcelLinkPostcodeRequest.serializer(), request))
                    when {
                        response.statusCode == 200 -> decode(PublicParcelResponse.serializer(), response)
                        response.isServiceNotFound -> null
                        // Nothing is asked any more: the parcel as it now stands.
                        response.statusCode == 409 -> {
                            val current = send("api/public/parcels/$linkId", "GET", key)
                            if (current.statusCode == 410 || current.isServiceNotFound) {
                                null
                            } else {
                                if (current.statusCode != 200) throw failure(current)
                                decode(PublicParcelResponse.serializer(), current)
                            }
                        }
                        response.statusCode == 422 -> throw DevicePostcodeRefusal.Refused(request.carrier)
                        // Too many postcodes tried for the link, or the carrier takes none for a while after wrong ones.
                        response.statusCode == 429 && JsonSerialization.stringsOfObject(response.body)?.get("scope") != "daily" -> {
                            val wait = response.header("Retry-After")?.let(::swiftDouble) ?: 60.0
                            throw DevicePostcodeRefusal.Tries(request.carrier, AppClock.now().plusMillis((wait * 1_000).toLong()))
                        }
                        // The carrier couldn't be asked; the service itself down says so as everywhere else.
                        response.statusCode == 503 && Maintenance.serverDown(response) == null -> throw DevicePostcodeRefusal.Unreachable(request.carrier)
                        else -> throw failure(response)
                    }
                },
            )
        }
    }
}

/**
 * Where the device's parcels are kept: what is known about each in a protected file, and the owner keys, which are
 * credentials, in the keychain.
 */
interface DeviceParcelStorage {
    fun load(): Contents

    fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String> = emptyMap())

    /**
     * The parcels, the owner keys by link id, and the links forgotten here that the service is still to be told about,
     * with their keys.
     */
    data class Contents(val parcels: List<DeviceParcel>, val keys: Map<String, String>, val pendingForgets: Map<String, String> = emptyMap()) {
        /** Leaves out the keys, which are credentials, and the links. */
        override fun toString(): String = "Contents(parcels=$parcels, keys=${keys.size}, pendingForgets=${pendingForgets.size})"
    }
}

/**
 * The file the device's parcels are kept in, which the app implements: on the iPhone `DeviceParcels/parcels.json` in
 * Application Support, readable once the phone was first unlocked after a restart. On Android a file in the app's own
 * storage (`filesDir`, which the phone encrypts with the user's credentials, not the device-protected storage), such as
 * `filesDir/DeviceParcels/parcels.json`, its folder made when it is first written.
 */
interface DeviceParcelFile {
    /**
     * The file's bytes, or nil when there is no file or it cannot be read. It goes into backups and transfers to a
     * new phone, as Application Support does on the iPhone, and the keys' secret store does not
     * (`dataExtractionRules`): a file brought back without its keys keeps the parcels followed through someone else's
     * link alone (`matched`).
     */
    fun read(): ByteArray?

    /**
     * Replaces the file with `data` at once: written beside it, synced, then renamed over it, so a reader finds the old
     * bytes or the new ones, never part of either. Throws when any step failed, leaving the old file.
     */
    fun write(data: ByteArray)

    /** Removes the file, if there is one. */
    fun delete()
}

/**
 * The device's parcels as the iPhone app keeps them: the owner keys in `keychain`, a secret store of their own
 * (KeychainStore on the iPhone, service "com.plhery.PeekDeliveryTracker.deviceParcels", kept on this device alone and
 * readable after the first unlock), as the JSON object JsonEncoder.deliveryTracker writes; the links forgotten here
 * that the service is still to be told about, with their keys, the same way in `forgets` (service
 * "com.plhery.PeekDeliveryTracker.deviceParcelForgets"); and the parcels in `file`, as the iPhone's
 * JSONEncoder.deliveryTracker writes its models (`JsonEncoder` with `swiftKeys`). A backup that brings the file back
 * without the keys brings back someone else's parcels alone (`matched`). `log` hears how many saved parcels this build
 * could not read, never which.
 */
class DeviceParcelFiles(
    private val keychain: SessionPersistence,
    private val file: DeviceParcelFile,
    private val forgets: SessionPersistence,
    private val log: (String) -> Unit = {},
) : DeviceParcelStorage {
    /** What `forgets` holds as far as this store knows, so that a save leaves it alone when it is the same; nil, unknown. */
    @Volatile private var forgetsOnFile: Map<String, String>? = null

    override fun load(): DeviceParcelStorage.Contents {
        // As KeychainStore's and Data(contentsOf:)'s `try?`: a store or file the system fails to read reads as missing,
        // so a lost Keystore key or a broken disk never stops the app from opening.
        val keys = secrets(keychain) ?: emptyMap()
        val pendingForgets = secrets(forgets)
        forgetsOnFile = pendingForgets
        return opened(quietly { file.read() }, keys, log).copy(pendingForgets = pendingForgets ?: emptyMap())
    }

    override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String>) {
        // The links still to be forgotten go first: one left among the keys too is still to be forgotten.
        if (pendingForgets != forgetsOnFile) {
            if (pendingForgets.isEmpty()) {
                quietly { forgets.delete() }
            } else {
                try {
                    forgets.save(JsonEncoder.deliveryTracker.encode(keysSerializer, pendingForgets))
                } catch (_: Exception) {
                    forgetsOnFile = null
                    return
                }
            }
            forgetsOnFile = pendingForgets
        }
        if (parcels.isEmpty() && keys.isEmpty()) {
            quietly { keychain.delete() }
            quietly { file.delete() }
            return
        }
        // The keys go first: a parcel on file always has its key.
        if (keys.isEmpty()) {
            quietly { keychain.delete() }
        } else {
            try {
                keychain.save(JsonEncoder.deliveryTracker.encode(keysSerializer, keys))
            } catch (_: Exception) {
                return
            }
        }
        // Keys alone are those of parcels whose saved copy couldn't be read: their keys read them again, and the file
        // holds the list as it is, even empty, as on the iPhone, so a parcel forgotten meanwhile stays forgotten.
        quietly { file.write(encoder.encode(parcelsSerializer, parcels)) }
    }

    private inline fun <T> quietly(block: () -> T): T? = try {
        block()
    } catch (_: Exception) {
        null
    }

    /** Keys by link id in `store`, or nil when there are none or they can't be read. */
    private fun secrets(store: SessionPersistence): Map<String, String>? = quietly { store.load() }?.let { data ->
        try {
            decodeJson(keysSerializer, data)
        } catch (_: Exception) {
            null
        }
    }

    companion object {
        private val keysSerializer = MapSerializer(String.serializer(), String.serializer())
        private val parcelsSerializer = ListSerializer(DeviceParcel.serializer())

        /** The iPhone's JSONEncoder.deliveryTracker on its own models: keys sorted, as the Swift models name them. */
        internal val encoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        /**
         * The parcels on file, with their keys. Each parcel is read on its own: one this build can't read (an update
         * gave the model a field it lacks, say) is left out alone, and a file that is no list at all leaves them all
         * out. Their keys stay, and read their parcels again from the service (`DeviceParcels.refresh`): what was
         * saved here is a copy, and losing it changes nothing there.
         */
        fun opened(file: ByteArray?, keys: Map<String, String>, log: (String) -> Unit = {}): DeviceParcelStorage.Contents {
            if (file == null) return DeviceParcelStorage.Contents(emptyList(), emptyMap())
            val rows = decodeSwiftKeysEach(DeviceParcel.serializer(), file)
            if (rows == null) {
                log("The saved parcels could not be read; their keys read them again.")
                return DeviceParcelStorage.Contents(emptyList(), keys)
            }
            val parcels = rows.filterNotNull()
            if (parcels.size < rows.size) log("${rows.size - parcels.size} of ${rows.size} saved parcels could not be read; their keys read them again.")
            return matched(parcels, keys)
        }

        /**
         * A parcel of this phone's own without its key cannot be read or forgotten, and a key beside someone else's
         * parcel opens nothing. A key without its parcel stays: its parcel is read again with it. A parcel followed
         * through someone else's link needs no key, nor does a copy the service has forgotten.
         */
        fun matched(parcels: List<DeviceParcel>, keys: Map<String, String>): DeviceParcelStorage.Contents {
            val kept = parcels.filter { it.isViewer || it.isFrozen || keys.keyOf(it.linkId) != null }
            fun theirs(linkId: String): Boolean {
                val beside = kept.filter { it.linkId.equalsCanonically(linkId) }
                return beside.isNotEmpty() && beside.all { it.isViewer }
            }
            return DeviceParcelStorage.Contents(kept, keys.filterKeys { !theirs(it) })
        }
    }
}

/**
 * The parcels someone follows before signing in. They live on this phone; the service keeps each behind a link only
 * this device's key owns, and forgets it some time after it arrives. A parcel followed through someone else's link is
 * read as its reader, with no key.
 *
 * Used from the main thread, as the iPhone's store is `@MainActor`. What the iPhone leaves to a `Task` (telling the
 * service a replaced or released parcel's notifications and link are no longer this phone's) runs in `scope`, which
 * nothing cancels: the one `MainScope()` the application keeps and gives every store (`Dispatchers.Main` with a
 * `SupervisorJob`, never `Main.immediate`: a Swift `Task` starts once the code that made it has returned). A build without a server has no `client` and follows
 * nothing.
 */
class DeviceParcels(
    private val client: DeviceParcelClient?,
    private val storage: DeviceParcelStorage,
    /** The postcodes its lookups were given, kept apart from the parcels. */
    val postcodes: PostcodeMemory,
    private val scope: CoroutineScope,
    /**
     * The device's region, whose country a carrier change sends as its lookup's hint:
     * `Resources.getSystem().configuration.locales[0]`, never `Locale.getDefault()`, which follows the app's language.
     */
    private val region: () -> Locale,
    /**
     * The app's language as it is now, which a lookup asks carriers to word its scans in: `AppLanguage.current` on the
     * iPhone, the Localizer's language here.
     */
    private val language: () -> AppLanguage,
    private val now: () -> Instant = AppClock::now,
) {
    private var entries: List<DeviceParcel>
    private var keys: Map<String, String>

    /** Parcels forgotten here whose notifications are still to be turned off, on file beside the others. */
    private var forgotten: List<DeviceParcel>

    /**
     * This phone's own links forgotten here, or replaced by a new lookup, that the service has not forgotten yet, with
     * their keys: out of the list, and asked about again on each refresh until it answers. The only links this phone
     * asks the service to forget.
     */
    private var pendingForgets: Map<String, String>

    /**
     * The tokens and languages rounds of notification requests are under way for, kept in NFC so that canonically
     * equivalent spellings are one round, as Swift's `Set<String>` compares them.
     */
    private var desiredAlert: String? = null
    private var alertGeneration = 0L
    private val alertRounds = mutableSetOf<Long>()

    /**
     * The token and language each parcel's notifications were asked for since launch, and the preset when one is chosen
     * for every parcel ([alertKey]). Each launch asks again, in case the service lost them.
     */
    private var alertsAsked: Map<String, String> = emptyMap()

    /** The last round, when it was cut short: tried again on its token and in its language once its time comes. */
    private var alertRetry: AlertRetry? = null

    /**
     * The links whose answer said a check of the parcel is queued or under way (`checkQueued`): read again soon, as the
     * website does, until it lands or [checkReadFor] has gone by.
     */
    private var queued: Map<String, QueuedCheck> = emptyMap()

    /** When a link's check was first said to be on its way, and when its parcel was last checked then. */
    private class QueuedCheck(val since: Instant, val synced: String?)

    /**
     * The token and language of the last round, which a parcel's notifications turned on again are asked for on. None
     * once they are turned off for every parcel ([alertsOff]).
     */
    private var alertsWanted: Pair<String, NativePushLocale>? = null

    private class AlertRetry(val token: String, val locale: NativePushLocale, val at: Instant, val wait: Double)

    init {
        val loaded = storage.load()
        pendingForgets = loaded.pendingForgets
        // A queued revocation supersedes a stale owner row. A later viewer follow can coexist with it.
        fun pending(linkId: String) = pendingForgets.keyOf(linkId) != null
        entries = loaded.parcels.filter { it.forgotten != true && (it.isViewer || !pending(it.linkId)) }
        forgotten = loaded.parcels.filter { it.forgotten == true }
        keys = loaded.keys.filterKeys { !pending(it) }
    }

    val isEmpty: Boolean get() = entries.isEmpty()

    val count: Int get() = entries.size

    /**
     * How many of this phone's own parcels are kept by their key alone, their saved copy lost and still to be read
     * again: not listed, yet Forget all forgets them too.
     */
    val unlistedCount: Int get() = keys.keys.count { linkId -> entries.none { it.linkId.equalsCanonically(linkId) } }

    /** The parcels as the list shows them, under the names this device gave them. */
    fun list(): List<Parcel> = entries.map(::shown)

    /** When the service last answered about one of the parcels: how old what the list shows is at most. */
    val lastCheckedAt: Instant? get() = entries.filter { !it.isFrozen }.maxOfOrNull { it.checkedAt }?.let(::referenceDate)

    /** Whether a parcel still waits for its carrier's first answer, which is worth asking about again soon. */
    val isWaiting: Boolean get() = entries.any { !it.isFrozen && (waiting(it.parcel) || awaitsCheck(it.linkId)) }

    /** Whether a check the service said it queued, such as one asked for by hand, is still to land. */
    val isChecking: Boolean get() = entries.any { !it.isFrozen && awaitsCheck(it.linkId) }

    /** A check of the link's parcel was said to be on its way less than [checkReadFor] ago, and hasn't landed yet. */
    private fun awaitsCheck(linkId: String): Boolean {
        val check = queued.keyOf(linkId) ?: return false
        return now().isBefore(check.since.plusSeconds(checkReadFor.toLong()))
    }

    /**
     * Notes what an answer says of the parcel's check: one on its way is followed until the parcel's last check
     * changes, which is the check landing, or until [checkReadFor] has gone by. A check said to be on its way after the
     * last one landed or ran out of time is followed from then.
     */
    private fun noteCheck(linkId: String, response: PublicParcelResponse) {
        val check = queued.keyOf(linkId)
        val synced = response.parcel.lastSyncedAt
        queued = when {
            response.checkQueued == true ->
                if (check != null && synced == check.synced && awaitsCheck(linkId)) queued
                else queued.setting(linkId, QueuedCheck(now(), synced))
            check != null && (synced != check.synced || !awaitsCheck(linkId)) -> queued.setting(linkId, null)
            else -> queued
        }
    }

    /** `typed`: the number as typed, which goes beside it when it differs ([typedTrackingNumber]). */
    suspend fun detect(trackingNumber: String, typed: String? = null): CarrierDetectionResponse {
        val client = client ?: throw DeliveryApiError.ServiceFailed(503)
        val count = trackingNumber.characterCount
        val prefixOfSaved = entries.any {
            it.parcel.trackingNumber.characterCount > count && it.parcel.trackingNumber.hasCanonicalPrefix(trackingNumber)
        }
        return client.detect(trackingNumber, prefixOfSaved, typedTrackingNumber(typed, trackingNumber))
    }

    suspend fun add(request: CreatePackageRequest): Parcel = lookUp(request, replacing = null)

    /** Looks the request up. The parcel it replaces, if any, does not count as already followed. */
    private suspend fun lookUp(request: CreatePackageRequest, replacing: UUID?): Parcel {
        val client = client ?: throw DeliveryApiError.ServiceFailed(503)
        entries.firstOrNull {
            it.parcel.id != replacing && !it.isFrozen && it.parcel.trackingNumber.equalsCanonically(request.trackingNumber) &&
                it.parcel.carrier.rawValue.equalsCanonically(request.carrier?.rawValue)
        }?.let { existing -> throw DeliveryApiError.DuplicateTracking(existing.parcel.id) }
        // A carrier that words its scans in several languages answers the parcel in the app's.
        val response = client.lookup(
            PublicLookupRequest(
                trackingNumber = request.trackingNumber, carrier = request.carrier,
                trackingUrl = request.trackingUrl, dpdPostcode = request.dpdPostcode,
                dpdPostcodeCountry = request.dpdPostcodeCountry, phoneDigits = request.phoneDigits,
                lookupCountryHint = request.lookupCountryHint, language = language().rawValue,
            ),
        )
        if (!ParcelLinkRoute.validId(response.link.id)) throw DeliveryApiError.InvalidResponse()
        var entry = DeviceParcel(
            linkId = response.link.id, label = request.label ?: "", archivedAt = null,
            parcel = Parcel(shared = response.parcel, link = response.link), checkedAt = now().timeIntervalSinceReferenceDate,
            postcodeGiven = request.dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty != null,
        ).startingSeen()
        // A copy the service forgot of the same parcel or number makes way for the new lookup, as on the website, and
        // leaves it its name.
        supersede(response.parcel, request.trackingNumber).name?.let { name -> if (entry.label.isEmpty()) entry = entry.copy(label = name) }
        // The service gives one parcel one link for each lookup; a second lookup of a parcel takes the first one's place.
        removeTwin(response.parcel.id, besides = null, client)?.let { old ->
            // The parcel being replaced came back unchanged: only its link is new.
            if (old.parcel.id == replacing) {
                entry = entry.copy(label = old.label, archivedAt = old.archivedAt, replaced = old.replaced, muted = old.muted)
            }
        }
        // A carrier changed by a new lookup keeps the notifications as they were set on the parcel's page, before a
        // round can ask for them.
        if (replacing != null && entry.muted == null) entry = entry.copy(muted = entries.firstOrNull { it.parcel.id == replacing }?.muted)
        entries = entries + entry
        keys = keys.setting(entry.linkId, response.key)
        pendingForgets = pendingForgets.setting(entry.linkId, null)
        save()
        request.dpdPostcode?.let { postcodes.remember(it, request.carrier ?: response.parcel.carrier, request.dpdPostcodeCountry) }
        // Phone digits go to the service alone: no parcel's next field starts from them, and the link never shows them.
        return shown(entry)
    }

    /**
     * Reads every parcel whose answer has gone stale, or every one when `all`. A parcel the service no longer has leaves
     * the list, unless it is this phone's own lookup: its last answer stays, no longer updated ([DeviceParcel.frozen]).
     * The first trouble ends the round and is thrown; the list keeps what the device has. A parcel whose check is on
     * its way is stale after [waitingFresh]. `check` is the reader's refresh by hand, of one parcel or the list: this
     * phone's own lookups ask for a check before one is due ([DeviceParcelClient.check]); someone else's parcel is read
     * as on screen.
     *
     * First the service is told again of the links forgotten here that it could not be told about, and, unless `only`
     * one parcel is read, the keys whose parcels were lost on this phone (a file this build could not read, say) read
     * their parcels again. A key that could not be read is trouble too, thrown once the round is done: with only such
     * keys left, a phone offline or out of lookups hears so, not that nothing changed.
     *
     * Returns whether the service answered anything, a forget or a notification turned off included: a guest who
     * removed their last parcel while it was down has nothing left to read.
     */
    suspend fun refresh(all: Boolean = false, only: UUID? = null, check: Boolean = false): Boolean {
        val client = client ?: return false
        var told = stopForgottenAlerts(client)
        val recovery = try {
            if (tellPendingForgets(client)) told = true
            if (only == null) recoverParcels(client) else Recovery(emptySet(), null)
        } catch (cancellation: CancellationException) {
            // What was already told or read stays so on file, as the parcels' round keeps what it read.
            save()
            throw cancellation
        }
        val recovered = recovery.read
        var contacted = told || recovered.isNotEmpty()
        var trouble: Exception? = null
        for (entry in entries) {
            if (only != null && entry.parcel.id != only) continue
            if (recovered.any { it.equalsCanonically(entry.linkId) }) continue
            // A copy the service forgot is never read again.
            if (entry.isFrozen) continue
            val key = keys.keyOf(entry.linkId)
            if (key == null && !entry.isViewer) continue
            val age = now().timeIntervalSinceReferenceDate - entry.checkedAt
            val settled = entry.parcel.isOver
            val fresh = if (waiting(entry.parcel) || awaitsCheck(entry.linkId)) waitingFresh else if (settled) settledFresh else fresh
            if (!(all || only != null || age < 0 || age >= fresh)) continue
            try {
                val forced = client.check.takeIf { check && !entry.isViewer }
                val response = if (forced != null && key != null) forced(entry.linkId, key) else client.read(entry.linkId, key)
                contacted = true
                val index = entries.indexOfFirst { it.linkId.equalsCanonically(entry.linkId) }
                if (index < 0) continue
                // A check asked for by hand is waited for a minute of its own.
                if (forced != null && key != null) queued = queued.setting(entry.linkId, null)
                if (response != null) noteCheck(entry.linkId, response) else queued = queued.setting(entry.linkId, null)
                if (response != null) {
                    change(index) {
                        it.taking(Parcel(shared = response.parcel, link = response.link)).copy(
                            checkedAt = now().timeIntervalSinceReferenceDate,
                            canKeep = if (entry.isViewer) response.link.canKeep else it.canKeep,
                        )
                    }
                } else if (entry.isViewer) {
                    // Someone else's link goes: its 404 doesn't say why it ended.
                    entries = entries.filterIndexed { position, _ -> position != index }
                    keys = keys.setting(entry.linkId, null)
                } else {
                    // The service forgot this phone's own lookup, and its notifications with it: its last answer stays.
                    change(index) { it.copy(frozen = DateParser.isoString(now()), alertToken = null, alertPreset = null) }
                    keys = keys.setting(entry.linkId, null)
                    alertsAsked = alertsAsked.setting(entry.linkId, null)
                }
            } catch (error: Exception) {
                trouble = error
                break
            }
        }
        save()
        (trouble ?: recovery.trouble)?.let { throw it }
        return contacted
    }

    /** The links `recoverParcels` read again, and the trouble that ended its round. */
    private class Recovery(val read: Set<String>, val trouble: Exception?)

    fun rename(id: UUID, label: String): Parcel = update(id) { it.copy(label = label) }

    fun setArchived(id: UUID, archived: Boolean): Parcel = update(id) { it.copy(archivedAt = if (archived) DateParser.isoString(now()) else null) }

    /**
     * The parcel leaves the list, then the service is told. Someone else's parcel is forgotten here alone: its
     * notifications on this phone stop, and its link goes on working for others. When the service can't be told yet,
     * each refresh tells it again: this phone's own link is kept with its key until the service has forgotten it,
     * someone else's until its notifications are off. Out of the list before the service is asked, a parcel that a
     * round of sign-ups on a new token answers for meanwhile has that token's notifications turned off too. Returns
     * whether the service answered.
     */
    suspend fun forget(id: UUID): Boolean {
        val entry = entries.firstOrNull { it.parcel.id == id } ?: return false
        val client = client
        val key = keys.keyOf(entry.linkId)
        entries = entries.filter { !it.linkId.equalsCanonically(entry.linkId) }
        keys = keys.setting(entry.linkId, null)
        // Followed again, it is signed up again.
        alertsAsked = alertsAsked.setting(entry.linkId, null)
        // On file as still to be forgotten before the service is asked, so that nothing stopping the app loses it.
        if (client != null && key != null) pendingForgets = pendingForgets.setting(entry.linkId, key)
        // A forgotten link of this phone's own takes its notifications with it; someone else's goes on, and stays on
        // file until its notifications here are turned off.
        val token = if (client == null) null else entry.alertToken
        if (token != null && entry.isViewer) forgotten = forgotten + entry.copy(forgotten = true)
        save()
        client ?: return false
        var answered = false
        if (token != null && succeeds { client.removeAlert(entry.linkId, removal(token)) }) {
            answered = true
            if (entry.isViewer) {
                forgotten = forgotten.filter { !(it.linkId.equalsCanonically(entry.linkId) && it.alertToken.equalsCanonically(token)) }
                save()
            }
        }
        if (key != null && forgetIfPending(entry.linkId, key, client)) answered = true
        return answered
    }

    /** Tells the service to forget a link still to be forgotten with this key, and returns whether it answered. */
    private suspend fun forgetIfPending(linkId: String, key: String, client: DeviceParcelClient): Boolean {
        if (pendingForgets.keyOf(linkId) != key) return false
        if (!succeeds { client.forget(linkId, key) }) return false
        // Followed again while the service was told: the link stays.
        if (pendingForgets.keyOf(linkId) != key) return true
        pendingForgets = pendingForgets.setting(linkId, null)
        save()
        return true
    }

    /**
     * Turns off the notifications of parcels forgotten while the service couldn't be told, and returns whether it
     * answered. The first trouble ends the round; the rest wait for the next.
     */
    private suspend fun stopForgottenAlerts(client: DeviceParcelClient): Boolean {
        var answered = false
        for (entry in forgotten) {
            val token = entry.alertToken
            if (token != null) {
                if (!succeeds { client.removeAlert(entry.linkId, removal(token)) }) return answered
                answered = true
            }
            forgotten = forgotten.filter { !(it.linkId.equalsCanonically(entry.linkId) && it.alertToken.equalsCanonically(entry.alertToken)) }
            save()
        }
        return answered
    }

    /**
     * Tells the service what was forgotten here while it couldn't be told, the notifications to stop and this phone's
     * own links, without reading any parcel: signed in, the account's list stands in for this phone's, and this goes on
     * at each of its loads rather than waiting for a sign-out's first refresh.
     */
    suspend fun tellForgotten() {
        val client = client ?: return
        if (forgotten.isEmpty() && pendingForgets.isEmpty()) return
        stopForgottenAlerts(client)
        try {
            tellPendingForgets(client)
        } finally {
            save()
        }
    }

    /**
     * Tells the service of the links forgotten here while it couldn't be told, and returns whether it answered. One it
     * has forgotten, or no longer has, is done with; the others wait for the next refresh.
     */
    private suspend fun tellPendingForgets(client: DeviceParcelClient): Boolean {
        var answered = false
        for ((linkId, key) in pendingForgets) {
            if (forgetIfPending(linkId, key, client)) answered = true
        }
        return answered
    }

    /**
     * Reads again, with its key, each parcel of this phone's own whose saved copy was lost (a file this build could not
     * read, or a key saved without its file), and keeps it as a lookup made here, without the name it was given. A key
     * whose link leads nowhere, or that the service no longer takes as the owner's, goes; one the service could not
     * answer for stays for the next refresh. Never a key still to be forgotten: those are not among the keys. The first
     * trouble ends the round and is returned with the links read; the rest wait, as on the iPhone.
     */
    private suspend fun recoverParcels(client: DeviceParcelClient): Recovery {
        val recovered = mutableSetOf<String>()
        for ((linkId, key) in keys) {
            if (entries.any { it.linkId.equalsCanonically(linkId) }) continue
            val response = try {
                client.read(linkId, key)
            } catch (cancellation: CancellationException) {
                throw cancellation
            } catch (error: Exception) {
                return Recovery(recovered, error)
            }
            recovered += linkId
            // Followed or forgotten meanwhile.
            if (entries.any { it.linkId.equalsCanonically(linkId) } || keys.keyOf(linkId) != key) continue
            if (response == null || !response.link.id.equalsCanonically(linkId) || response.link.role != ParcelLinkRole.Owner) {
                // Only this phone's copy goes: the link stays as it is on the service.
                keys = keys.setting(linkId, null)
                continue
            }
            // A copy the service forgot of the same parcel makes way for it, and leaves it its name.
            val name = supersede(response.parcel).name
            val index = entries.indexOfFirst { it.parcel.id == response.parcel.id }
            if (index >= 0) {
                if (entries[index].isViewer) {
                    upgradeViewer(index, response, key)
                } else {
                    keys = keys.setting(linkId, null)
                }
                continue
            }
            entries = entries + DeviceParcel(
                linkId = linkId, label = name ?: "", archivedAt = null, parcel = Parcel(shared = response.parcel, link = response.link),
                checkedAt = now().timeIntervalSinceReferenceDate,
            ).startingSeen()
        }
        return Recovery(recovered, null)
    }

    /**
     * Forgets every parcel, those whose saved copy was lost and is still to be read again included. The ones the
     * service could not be told about leave the list too, and each refresh tells it again. The keys of the lost ones
     * are set aside before any call to the service: a refresh that runs meanwhile no longer reads them again, and a
     * read already under way lands for a key that has gone and is dropped (`recoverParcels`). Returns whether the
     * service answered.
     */
    suspend fun forgetAll(): Boolean {
        val client = client
        val unread = keys.filterKeys { linkId -> entries.none { it.linkId.equalsCanonically(linkId) } }
        if (unread.isNotEmpty()) {
            for ((linkId, key) in unread) {
                keys = keys.setting(linkId, null)
                if (client != null) pendingForgets = pendingForgets.setting(linkId, key)
            }
            save()
        }
        var answered = false
        for (entry in entries) if (forget(entry.parcel.id)) answered = true
        if (unread.isNotEmpty() && client != null) {
            if (tellPendingForgets(client)) answered = true
            save()
        }
        return answered
    }

    /**
     * Follows the same number with another carrier, link, postcode or phone digits. The parcel's own link moves to it
     * (`PATCH …/carrier`), keeping its address, sharing and notifications, as on the website; where the service no
     * longer has that link for its key, a new lookup takes the old one's place and name. The postcode the link asks
     * for, given alone for the carrier that asks, goes to the link instead (`POST …/postcode`), as on the website: the
     * service tries it with that carrier first, and one it refuses changes nothing ([DevicePostcodeRefusal]).
     */
    suspend fun changeCarrier(
        id: UUID,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String? = null,
        phoneDigits: String? = null,
    ): Parcel {
        // Someone else's parcel is followed as its link shows it, and its carrier stays theirs to change.
        val entry = entries.firstOrNull { it.parcel.id == id }
        if (entry == null || entry.isViewer || entry.isFrozen) throw DeliveryApiError.ParcelMissing()
        val key = keys.keyOf(entry.linkId)
        val move = client?.changeCarrier
        val postcode = dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty
        val give = client?.addPostcode?.takeIf {
            postcode != null && entry.parcel.inputNeeded?.carrier == carrier &&
                trackingUrl?.trimmingWhitespacesAndNewlines()?.nonEmpty == null && phoneDigits?.trimmingWhitespacesAndNewlines()?.nonEmpty == null
        }
        if (key != null && (move != null || give != null)) {
            entries.firstOrNull {
                it.parcel.id != id && !it.isFrozen && it.parcel.trackingNumber.equalsCanonically(entry.parcel.trackingNumber) &&
                    it.parcel.carrier.rawValue.equalsCanonically(carrier.rawValue)
            }?.let { existing -> throw DeliveryApiError.DuplicateTracking(existing.parcel.id) }
        }
        if (key != null && give != null && postcode != null) {
            val response = give(entry.linkId, key, ParcelLinkPostcodeRequest(carrier = carrier, postcode = postcode, postcodeCountry = dpdPostcodeCountry))
            if (response != null) {
                val parcel = refiled(entry.linkId, id, response, postcodeGiven = true)
                postcodes.remember(postcode, carrier, dpdPostcodeCountry)
                return parcel
            }
        }
        if (key != null && move != null) {
            val change = ParcelLinkCarrierRequest(
                carrier = carrier, trackingUrl = trackingUrl, dpdPostcode = dpdPostcode, dpdPostcodeCountry = dpdPostcodeCountry,
                phoneDigits = phoneDigits, lookupCountryHint = ParcelLookupCountry.hint(region()), language = language().rawValue,
            )
            val response = move(entry.linkId, key, change)
            if (response != null) {
                val parcel = refiled(entry.linkId, id, response, postcodeGiven = postcode != null)
                dpdPostcode?.let { postcodes.remember(it, carrier, dpdPostcodeCountry) }
                return parcel
            }
        }
        val added = lookUp(
            CreatePackageRequest(
                trackingNumber = entry.parcel.trackingNumber, label = entry.label, carrier = carrier,
                trackingUrl = trackingUrl, dpdPostcode = dpdPostcode, dpdPostcodeCountry = dpdPostcodeCountry,
                phoneDigits = phoneDigits, lookupCountryHint = ParcelLookupCountry.hint(region()),
            ),
            replacing = id,
        )
        if (added.id != id) {
            attempt { forget(id) }
            val index = entries.indexOfFirst { it.parcel.id == added.id }
            if (index >= 0) {
                change(index) { it.copy(replaced = entry.replaced ?: id) }
                save()
                return shown(entries[index])
            }
        }
        return added
    }

    /**
     * The owner's link moved to another carrier, or filed under one with its postcode: its answer takes the parcel's
     * place, and a page open on [id] follows it where the link now reaches another parcel of the number, whose answer so
     * far is no news, as a new lookup's. That parcel followed here under another link, and a copy the service forgot of
     * it or of the number, make way, the copy leaving its name to a parcel without one.
     */
    private fun refiled(linkId: String, id: UUID, response: PublicParcelResponse, postcodeGiven: Boolean): Parcel {
        // Forgotten here while the service was asked: it stays forgotten.
        val index = entries.indexOfFirst { it.linkId.equalsCanonically(linkId) }
        if (index < 0) throw DeliveryApiError.ParcelMissing()
        val number = entries[index].parcel.trackingNumber
        change(index) {
            val answer = Parcel(shared = response.parcel, link = response.link)
            val stamped = { entry: DeviceParcel -> entry.copy(checkedAt = now().timeIntervalSinceReferenceDate, postcodeGiven = postcodeGiven) }
            // The link reaches another parcel of the number: the page open on the old one follows it.
            if (response.parcel.id != id) stamped(it.copy(parcel = answer).startingSeen()).copy(replaced = it.replaced ?: id) else stamped(it.taking(answer))
        }
        val name = supersede(response.parcel, number).name
        client?.let { removeTwin(response.parcel.id, besides = linkId, it) }
        val moved = entries.indexOfFirst { it.linkId.equalsCanonically(linkId) }
        if (name != null && entries[moved].label.isEmpty()) change(moved) { it.copy(label = name) }
        save()
        return shown(entries[moved])
    }

    /** The parcel's link as others may open it, or nil once its sharing was stopped. */
    suspend fun share(id: UUID): ParcelShare? {
        val client = client ?: throw DeliveryApiError.ParcelMissing()
        val entry = entries.firstOrNull { it.parcel.id == id } ?: throw DeliveryApiError.ParcelMissing()
        val key = keys.keyOf(entry.linkId) ?: throw DeliveryApiError.ParcelMissing()
        val response = client.read(entry.linkId, key) ?: throw DeliveryApiError.ParcelMissing()
        return share(response.link)
    }

    /** Changes what the link shows, resuming its sharing if it was stopped; nil stops it. */
    suspend fun setShare(id: UUID, showNumber: Boolean, gift: Boolean, shared: Boolean = true, giftWords: GiftWords? = null): ParcelShare? {
        val client = client ?: throw DeliveryApiError.ParcelMissing()
        val entry = entries.firstOrNull { it.parcel.id == id } ?: throw DeliveryApiError.ParcelMissing()
        val key = keys.keyOf(entry.linkId) ?: throw DeliveryApiError.ParcelMissing()
        val change = if (shared) {
            UpdateParcelLinkRequest(showNumber = showNumber, gift = gift, shared = true, giftWords = giftWords)
        } else {
            UpdateParcelLinkRequest(shared = false)
        }
        return share(client.update(entry.linkId, key, change).link)
    }

    /** What its reader says of a parcel this phone follows. */
    suspend fun sendFeedback(feedback: ParcelFeedbackRequest, id: UUID) {
        val entry = entries.firstOrNull { it.parcel.id == id } ?: throw DeliveryApiError.ParcelMissing()
        sendFeedback(feedback, entry.linkId)
    }

    /** The same of a parcel behind any link: someone else's goes without a key. */
    suspend fun sendFeedback(feedback: ParcelFeedbackRequest, linkId: String) {
        val client = client ?: throw DeliveryApiError.ServiceFailed(503)
        client.feedback(linkId, keys.keyOf(linkId), feedback)
    }

    /** Whether any of the device's parcels is archived, for the account that takes them over. */
    fun isArchived(linkId: String): Boolean = entries.firstOrNull { it.linkId.equalsCanonically(linkId) }?.archivedAt != null

    /**
     * Whether the parcel behind this link had its notifications turned off here with its bell, for the account that takes
     * it over.
     */
    fun isMuted(linkId: String): Boolean = entries.firstOrNull { it.linkId.equalsCanonically(linkId) }?.isMuted == true

    /**
     * What an account needs to take the device's parcels over: its own with their keys, those whose saved copy was lost
     * included, and someone else's that the link lets an account keep. Never a link still to be forgotten.
     */
    val claims: List<ClaimParcelLink>
        get() = entries.mapNotNull { entry ->
            if (entry.isViewer) {
                if (entry.canKeep == true) ClaimParcelLink(id = entry.linkId, label = entry.label.nonEmpty) else null
            } else {
                keys.keyOf(entry.linkId)?.let { ClaimParcelLink(id = entry.linkId, key = it, label = entry.label.nonEmpty) }
            }
        } + keys.mapNotNull { (linkId, key) ->
            if (entries.any { it.linkId.equalsCanonically(linkId) }) null else ClaimParcelLink(id = linkId, key = key)
        }

    /** Whether the parcel behind this link is someone else's, followed here as its reader. */
    fun isViewer(linkId: String): Boolean = entries.firstOrNull { it.linkId.equalsCanonically(linkId) }?.isViewer == true

    /** The link of a parcel followed here through someone else's link, which is all this phone may share of it. */
    fun viewerLink(id: UUID): String? = entries.firstOrNull { it.parcel.id == id && it.isViewer }?.linkId

    /** Whether the service forgot this parcel of the phone's own, whose last answer stays here ([DeviceParcel.frozen]). */
    fun isFrozen(id: UUID): Boolean = entries.firstOrNull { it.parcel.id == id }?.isFrozen == true

    /** Whether a postcode went with the lookup that made the parcel; null when this phone doesn't know. */
    fun postcodeGiven(id: UUID): Boolean? = entries.firstOrNull { it.parcel.id == id }?.postcodeGiven

    /** The parcel this phone follows through a link, as the list shows it. */
    fun parcelId(linkId: String): UUID? = entries.firstOrNull { it.linkId.equalsCanonically(linkId) }?.parcel?.id

    /**
     * The account has these parcels now, or their links lead nowhere: the device lets go of them, and of their
     * notifications here, which the account's own take over.
     */
    fun release(linkIds: Set<String>) {
        fun released(linkId: String): Boolean = linkIds.any { it.equalsCanonically(linkId) }
        val alerts = entries.mapNotNull { entry -> if (released(entry.linkId)) entry.alertToken?.let { entry.linkId to it } else null }
        entries = entries.filter { !released(it.linkId) }
        for (id in linkIds) keys = keys.setting(id, null)
        save()
        val client = client
        if (client != null && alerts.isNotEmpty()) {
            scope.launch {
                for ((linkId, token) in alerts) attempt { client.removeAlert(linkId, removal(token)) }
            }
        }
    }

    // Following a link

    /** What `follow` did: the parcel, and whether it is new here rather than here already. */
    data class Followed(val id: UUID, val added: Boolean)

    /**
     * Keeps the parcel behind a link on this phone. With the link's owner key, handed over by the site, the parcel is
     * this phone's own, as a lookup made here would be; without it, it is followed as its reader. A parcel here already
     * stays as it is, unless the key makes someone else's parcel this phone's own. `added` is false when it was here
     * already.
     */
    fun follow(response: PublicParcelResponse, key: String?, label: String?): Followed {
        val linkId = response.link.id
        if (!ParcelLinkRoute.validId(linkId)) throw DeliveryApiError.InvalidResponse()
        val key = key?.takeIf { response.link.role == ParcelLinkRole.Owner && ParcelLinkRoute.validKey(it) }
        // A copy the service forgot of the same parcel or number, or of this link, makes way for a link that still
        // answers, and leaves it its name.
        val superseded = supersede(response.parcel, linkId = linkId)
        val index = entries.indexOfFirst { it.linkId.equalsCanonically(linkId) || it.parcel.id == response.parcel.id }
        if (index >= 0) {
            if (key == null || !entries[index].isViewer) {
                if (superseded.any) save()
                return Followed(entries[index].parcel.id, false)
            }
            upgradeViewer(index, response, key)
            save()
            return Followed(entries[index].parcel.id, false)
        }
        var entry = DeviceParcel(
            linkId = linkId, label = label?.nonEmpty ?: superseded.name ?: "", archivedAt = null,
            parcel = Parcel(shared = response.parcel, link = response.link), checkedAt = now().timeIntervalSinceReferenceDate,
        ).startingSeen()
        // Followed again before its notifications were turned off: they go on, and are its own again.
        forgotten.firstOrNull { it.linkId.equalsCanonically(linkId) }?.let { left ->
            entry = entry.copy(alertToken = left.alertToken, alertPreset = left.alertPreset)
            forgotten = forgotten.filter { !it.linkId.equalsCanonically(linkId) }
        }
        if (key != null) {
            pendingForgets = pendingForgets.setting(linkId, null)
            keys = keys.setting(linkId, key)
        } else {
            entry = entry.copy(viewer = true, canKeep = response.link.canKeep)
        }
        entries = entries + entry
        // Opening the link queued a check of the parcel: its answer is read as it lands.
        noteCheck(linkId, response)
        save()
        return Followed(entry.parcel.id, true)
    }

    private fun upgradeViewer(index: Int, response: PublicParcelResponse, key: String) {
        val linkId = response.link.id
        val old = entries[index]
        var owned = old.taking(Parcel(shared = response.parcel, link = response.link)).copy(
            linkId = linkId, viewer = null, canKeep = null, checkedAt = now().timeIntervalSinceReferenceDate,
        )
        if (!old.linkId.equalsCanonically(linkId)) {
            // Another link's notifications give way to this one's, asked for on the next round.
            owned = owned.copy(alertToken = null)
            val client = client
            val token = old.alertToken
            if (client != null && token != null) {
                scope.launch { attempt { client.removeAlert(old.linkId, removal(token)) } }
            }
        }
        change(index) { owned }
        pendingForgets = pendingForgets.setting(linkId, null)
        keys = keys.setting(linkId, key)
    }

    // Notifications

    /**
     * Whether a parcel is still on its way, with something left to tell: a held return still is, a copy the service
     * forgot never is.
     */
    val hasParcelsOnTheirWay: Boolean get() = entries.any { !it.isFrozen && !it.parcel.isOver }

    /** What tells the service that this phone's address ([PushAddress], its [token]) hears nothing more of a parcel. */
    private fun removal(token: String) = PushAddress.of(token).let { DeleteParcelAppAlertRequest(token = it.token, transport = it.transport) }

    /**
     * What [alertsAsked] notes for a link asked for on [token] and in [locale], with [preset] when one is chosen for
     * every parcel: a new choice asks every link again.
     */
    private fun alertKey(token: String, locale: NativePushLocale, preset: ParcelAlertPreset?): String =
        "$token ${locale.rawValue}" + (preset?.let { " ${it.rawValue}" } ?: "")

    /**
     * Turns notifications on, on this phone's token and in its language, for every parcel still on its way: once a
     * launch, and again when the token or the language changes. A parcel that arrived needs none; a held return still
     * does. A round is short and quiet: a few parcels at most, and it ends at the first sign that the service wants a
     * pause, sends no notifications or can't be reached; [retryAlerts] tries it again later. A link that leads nowhere,
     * has no room or is no longer shared is left until the next launch.
     *
     * Each parcel's notifications tell what [preset] says, the one chosen for every parcel on the Delivery updates
     * page: a new one asks again for those already on, and the service replaces what they told. Until one is chosen
     * (null), a parcel keeps what it was first asked for, or is given [alertPreset]'s.
     *
     * On Android the token is this phone's address ([PushAddress]), which has one environment. Called from the main
     * thread: `FirebaseMessagingService.onNewToken` runs on another, and the app hands its token over to the main one.
     */
    suspend fun registerAlerts(token: String, locale: NativePushLocale, preset: ParcelAlertPreset? = null) {
        val asked = alertKey(token, locale, preset)
        val client = client ?: return
        alertsWanted = token to locale
        if (desiredAlert != asked) {
            desiredAlert = asked
            alertGeneration++
            alertRetry = null
        }
        val round = alertGeneration
        if (!alertRounds.add(round)) return
        val address = PushAddress.of(token)
        // How long the service asked to be left alone, in seconds, when the round is cut short.
        var pause: Double? = null
        try {
            val waiting = entries.filter {
                !it.isFrozen && !it.isMuted && !asked.equalsCanonically(alertsAsked.keyOf(it.linkId)) && !it.parcel.isOver
            }
            val due = waiting.take(alertsPerRound)
            for (entry in due) {
                if (round != alertGeneration) return
                // Forgotten, or followed through another link, since the round began.
                if (entries.none { it.linkId.equalsCanonically(entry.linkId) }) continue
                val sent = preset ?: entry.alertPreset ?: alertPreset(entry.parcel)
                val answer = try {
                    client.setAlert(
                        entry.linkId, keys.keyOf(entry.linkId),
                        ParcelAppAlertRequest(
                            token = address.token, environment = NativePushEnvironment.Production, preset = sent, locale = locale,
                            transport = address.transport, endpoint = address.endpoint, keys = address.keys,
                        ),
                    )
                } catch (cancellation: CancellationException) {
                    throw cancellation
                } catch (error: Exception) {
                    pause = (error as? DeliveryApiError.RateLimited)?.retryAfter ?: 0.0
                    break
                }
                if (round != alertGeneration) {
                    // Turned off for every parcel while the service was asked: this one hears nothing after all.
                    if (desiredAlert == null && answer == DeviceAlertAnswer.On) attempt { client.removeAlert(entry.linkId, removal(token)) }
                    return
                }
                if (answer == DeviceAlertAnswer.Unavailable) {
                    pause = 0.0
                    break
                }
                val index = entries.indexOfFirst { it.linkId.equalsCanonically(entry.linkId) }
                if (index < 0) {
                    // Forgotten, or followed through another link, while the service was asked: the link hears nothing
                    // more here.
                    if (answer == DeviceAlertAnswer.On) stopAlert(entry, token, client)
                    continue
                }
                alertsAsked = alertsAsked.setting(entry.linkId, asked)
                if (answer != DeviceAlertAnswer.On) continue
                // Turned off on its page while the service was asked: it hears nothing after all.
                if (entries[index].isMuted) {
                    attempt { client.removeAlert(entry.linkId, removal(token)) }
                    continue
                }
                val old = entries[index].alertToken
                change(index) { it.copy(alertToken = token, alertPreset = sent) }
                save()
                // The token changed: the old one hears nothing more of this parcel. A new way to the same phone (from
                // its inbox to UnifiedPush) has replaced the old one already.
                if (old != null && !PushAddress.sameDevice(old, token)) {
                    attempt { client.removeAlert(entry.linkId, removal(old)) }
                }
            }
            // Parcels past this round, and those added or turned back on while it ran, wait for the next one.
            if (round == alertGeneration) holdAlerts(pause, token, locale, more = !alertsSettled(token, locale, preset))
        } finally {
            alertRounds.remove(round)
        }
    }

    /**
     * Whether every parcel that can still hear news has asked for it on this token, language and preset; frozen copies,
     * muted parcels and parcels that are over need nothing.
     */
    fun alertsSettled(token: String, locale: NativePushLocale, preset: ParcelAlertPreset? = null): Boolean = entries.all {
        it.isFrozen || it.isMuted || it.parcel.isOver || alertsAsked.keyOf(it.linkId) == alertKey(token, locale, preset)
    }

    /**
     * Turns a parcel's notifications on this phone off, or back on, from its page. Off, the service is told first, and
     * a failure leaves them on and is thrown; back on, they are asked for on the next round ([alertsAgain]).
     */
    suspend fun setMuted(id: UUID, muted: Boolean): Parcel {
        val entry = entries.firstOrNull { it.parcel.id == id } ?: throw DeliveryApiError.ParcelMissing()
        if (!muted) {
            alertsAsked = alertsAsked.setting(entry.linkId, null)
            return update(id) { it.copy(muted = null) }
        }
        val token = entry.alertToken
        val client = client
        if (token != null && client != null) client.removeAlert(entry.linkId, removal(token))
        // A round that signed the link up meanwhile left its own token: that one hears nothing either.
        val signedUp = entries.firstOrNull { it.parcel.id == id }?.alertToken
        if (signedUp != null && signedUp != token && client != null) attempt { client.removeAlert(entry.linkId, removal(signedUp)) }
        return update(id) { it.copy(muted = true, alertToken = null) }
    }

    /**
     * Asks again for the notifications still missing, on the last round's token and language, where one ran, with the
     * preset chosen for every parcel ([registerAlerts]).
     */
    suspend fun alertsAgain(preset: ParcelAlertPreset? = null) {
        val (token, locale) = alertsWanted ?: return
        registerAlerts(token, locale, preset)
    }

    /**
     * Tries the last round again when it was cut short and its time has come: an outage or a pause on the service's
     * side leaves no parcel without notifications until the next launch. The list calls it as it refreshes.
     */
    suspend fun retryAlerts(preset: ParcelAlertPreset? = null) {
        val retry = alertRetry ?: return
        if (now().isBefore(retry.at)) return
        registerAlerts(retry.token, retry.locale, preset)
    }

    /**
     * Turns every parcel's notifications on this phone off, from the switch on the Delivery updates page: the service is
     * told about each link signed up here, and no round asks for any until [registerAlerts] is called again; a round
     * under way stops, and a link it signed up meanwhile is told too. A parcel turned off on its own page stays so.
     *
     * A link the service can't be told about yet keeps its token, and the next call tells it again; the first failure
     * ends the call, as the service may want a pause. Nothing is thrown.
     */
    suspend fun alertsOff() {
        desiredAlert = null
        alertGeneration++
        alertRetry = null
        alertsWanted = null
        alertsAsked = emptyMap()
        val client = client ?: return
        for (entry in entries.filter { it.alertToken != null }) {
            val token = entry.alertToken ?: continue
            if (!succeeds { client.removeAlert(entry.linkId, removal(token)) }) return
            // Turned on again meanwhile: the new round asks for every link, and replaces the token it finds.
            if (desiredAlert != null) return
            val index = entries.indexOfFirst { it.linkId.equalsCanonically(entry.linkId) }
            if (index < 0 || entries[index].alertToken != token) continue
            change(index) { it.copy(alertToken = null) }
            save()
        }
    }

    /**
     * Notes how a round ended: one cut short is tried again after a wait, longer each time it fails again; one that
     * went through leaves nothing to try again, unless [more] parcels than a round asks for wait: the next round asks
     * for them as soon as the list refreshes.
     */
    private fun holdAlerts(pause: Double?, token: String, locale: NativePushLocale, more: Boolean = false) {
        if (pause == null) {
            alertRetry = if (more) AlertRetry(token, locale, now(), alertRetryFirst) else null
            return
        }
        val last = alertRetry
        val wait = if (last != null && last.token == token && last.locale == locale) minOf(last.wait * 2, alertRetryLongest) else alertRetryFirst
        alertRetry = AlertRetry(token, locale, now().plusMillis((maxOf(wait, pause) * 1000).toLong()), wait)
    }

    /**
     * Turns off a link's notifications on [token], for a link no longer followed here. Someone else's link the service
     * can't be told about yet waits with the parcels forgotten here, and each refresh tells it again; a link of this
     * phone's own takes its notifications with it when the service forgets it.
     */
    private suspend fun stopAlert(entry: DeviceParcel, token: String, client: DeviceParcelClient) {
        if (succeeds { client.removeAlert(entry.linkId, removal(token)) } || !entry.isViewer) return
        if (entries.any { it.linkId.equalsCanonically(entry.linkId) }) return
        forgotten = forgotten.filter { !(it.linkId.equalsCanonically(entry.linkId) && it.alertToken.equalsCanonically(token)) } +
            entry.copy(alertToken = token, forgotten = true)
        save()
    }

    private fun update(id: UUID, change: (DeviceParcel) -> DeviceParcel): Parcel {
        val index = entries.indexOfFirst { it.parcel.id == id }
        if (index < 0) throw DeliveryApiError.ParcelMissing()
        change(index, change)
        save()
        return shown(entries[index])
    }

    private fun change(index: Int, change: (DeviceParcel) -> DeviceParcel) {
        entries = entries.toMutableList().also { it[index] = change(it[index]) }
    }

    /**
     * Takes out the copies the service forgot that `answer` takes the place of ([superseded]), and the one of link
     * `linkId` if it forgot that too, and gives the first name among them, which a parcel without one takes.
     */
    private fun supersede(answer: PublicPackage, number: String? = null, linkId: String? = null): Superseded {
        val gone = entries.filter { superseded(it, answer, number) || (it.isFrozen && linkId != null && it.linkId.equalsCanonically(linkId)) }
        if (gone.isNotEmpty()) entries = entries.filter { entry -> gone.none { it === entry } }
        return Superseded(gone.isNotEmpty(), gone.firstNotNullOfOrNull { it.label.nonEmpty })
    }

    private data class Superseded(val any: Boolean, val name: String?)

    /**
     * Takes out the entry of parcel `parcelId` under another link than `linkId`: one parcel is followed here once. Its
     * link of this phone's own is forgotten as one forgotten here would be, on file until the service has been told, and
     * its notifications are turned off.
     */
    private fun removeTwin(parcelId: UUID, besides: String?, client: DeviceParcelClient): DeviceParcel? {
        val twin = entries.indexOfFirst { it.parcel.id == parcelId && (besides == null || !it.linkId.equalsCanonically(besides)) }
        if (twin < 0) return null
        val old = entries[twin]
        entries = entries.filterIndexed { index, _ -> index != twin }
        val key = keys.keyOf(old.linkId)
        keys = keys.setting(old.linkId, null)
        if (key != null) pendingForgets = pendingForgets.setting(old.linkId, key)
        if (key != null || old.alertToken != null) {
            scope.launch {
                old.alertToken?.let { token -> attempt { client.removeAlert(old.linkId, removal(token)) } }
                if (key != null) forgetIfPending(old.linkId, key, client)
            }
        }
        return old
    }

    /** The parcel was opened on this phone (#72): what it tells now is seen, and its dot goes. Nothing leaves the device. */
    fun markSeen(id: UUID, newsAt: String) {
        val index = entries.indexOfFirst { it.parcel.id == id }
        if (index < 0) return
        val marked = DateParser.date(newsAt) ?: return
        val previous = entries[index].seenNewsAt?.let(DateParser::date)
        if (previous != null && previous >= marked) return
        val seen = entries[index].copy(seenNewsAt = newsAt)
        if (seen == entries[index]) return
        change(index) { seen }
        save()
    }

    private fun save() {
        storage.save(entries + forgotten, keys, pendingForgets)
    }

    companion object {
        /**
         * How long an answer is good for, in seconds: a parcel on its way moves, one that arrived rests, and one its
         * carrier has not answered about yet is asked about again at once. A held return is on its way: the server still
         * follows it.
         */
        const val fresh: Double = 5.0 * 60
        const val settledFresh: Double = 12.0 * 60 * 60
        const val waitingFresh: Double = 1.5

        /** How long a check said to be on its way is waited for, in seconds, as the website does. */
        const val checkReadFor: Double = 60.0

        /** How many parcels one round asks notifications for; the rest wait for the next round, at the list's next refresh. */
        const val alertsPerRound = 25

        /**
         * How long a round cut short waits before it is tried again, in seconds: a minute the first time, then twice as
         * long each time, up to an hour.
         */
        const val alertRetryFirst: Double = 60.0
        const val alertRetryLongest: Double = 60.0 * 60

        /**
         * What a parcel's notifications tell when they are first turned on, as on the site: every scan while nothing has
         * moved yet, the steps that matter after that.
         */
        fun alertPreset(parcel: Parcel): ParcelAlertPreset =
            if (!parcel.hasCarrierUpdate || parcel.currentStage == TrackingStage.Registered) ParcelAlertPreset.All else ParcelAlertPreset.Important

        /**
         * A copy the service forgot of the parcel `answer` is for, or of its number (or `number`, the one asked about,
         * which a link may hide), which a lookup or link that answers replaces.
         */
        private fun superseded(entry: DeviceParcel, answer: PublicPackage, number: String? = null): Boolean {
            if (!entry.isFrozen) return false
            if (entry.parcel.id == answer.id) return true
            return listOfNotNull(answer.trackingNumber?.nonEmpty, number?.nonEmpty).any { entry.parcel.trackingNumber.equalsCanonically(it) }
        }

        private fun waiting(parcel: Parcel): Boolean = parcel.syncStatus == SyncStatus.Pending || parcel.syncStatus == SyncStatus.Syncing

        private fun share(link: ParcelLink): ParcelShare? {
            if (link.shared == false) return null
            return ParcelShare(id = link.id, showNumber = link.showNumber ?: false, gift = link.gift ?: false, createdAt = link.createdAt, giftWords = link.giftWords)
        }

        /**
         * As the list shows it: under this phone's name, and seen as far as this phone has seen it (#72), the news it
         * had when it was last opened here; all of it on a parcel kept before this was noted.
         */
        private fun shown(entry: DeviceParcel): Parcel {
            val seen = entry.seenNewsAt ?: entry.parcel.newsAt
            val parcel = entry.parcel.copy(label = entry.label, archivedAt = entry.archivedAt, seenAt = seen, notificationsMuted = entry.isMuted)
            val replaced = entry.replaced ?: return parcel
            return parcel.copy(carrierData = (parcel.carrierData ?: CarrierData()).copy(originalPackageId = replaced))
        }
    }
}

/**
 * Swift's `try?` on a call whose failure changes nothing: whatever it throws is let go, but a cancellation, which ends
 * the caller as it would any other call.
 */
private inline fun attempt(block: () -> Unit) {
    try {
        block()
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (_: Exception) {
    }
}

/** Swift's `(try? …) != nil`: whether the call went through. A cancellation ends the caller, as in `attempt`. */
private inline fun succeeds(block: () -> Unit): Boolean =
    try {
        block()
        true
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (_: Exception) {
        false
    }

/** Swift's `keys[linkID]`: a Dictionary finds the key canonically equivalent to the one asked for. */
private fun <V> Map<String, V>.keyOf(linkId: String): V? = this[linkId] ?: entries.firstOrNull { it.key.equalsCanonically(linkId) }?.value

/**
 * Swift's `keys[linkID] = key` on a copy: a key canonically equivalent already there keeps its spelling and takes the
 * value, and nil removes it.
 */
private fun <V> Map<String, V>.setting(linkId: String, key: V?): Map<String, V> {
    val existing = keys.firstOrNull { it.equalsCanonically(linkId) }
    if (key == null && existing == null) return this
    val result = LinkedHashMap(this)
    if (key == null) result.remove(existing) else result[existing ?: linkId] = key
    return result
}

/**
 * JSONSerialization's `jsonObject(with:)`, as the iPhone's Foundation reads a body, then `as? [String: Any]`: the
 * object at the top, or nil. It keeps the object's strings, and nil for a value of another kind; a key given twice keeps
 * its first value.
 *
 * As Foundation: UTF-8 unless a byte order mark, or the zero bytes of a body longer than four, says UTF-16 or UTF-32
 * (whose last bytes that make no whole unit are left out); only JSON's own whitespace around the values; a comma may
 * end an object or an array; a string holds no control character, no lone surrogate and no bytes that are not text in
 * its encoding; a number past the largest Double is no number; and no value lies more than 512 deep.
 */
internal object JsonSerialization {
    fun stringsOfObject(data: ByteArray): Map<String, String?>? {
        val text = text(data) ?: return null
        val reader = Reader(text)
        return try {
            reader.whitespace()
            if (reader.peek() != '{') return null
            val result = reader.topObject()
            reader.whitespace()
            if (reader.index != text.length) null else result
        } catch (_: Invalid) {
            null
        }
    }

    private class Invalid : Exception()

    private fun ByteArray.startsWith(vararg prefix: Int): Boolean =
        size >= prefix.size && prefix.indices.all { this[it].toInt() and 0xFF == prefix[it] }

    private fun text(data: ByteArray): String? {
        val (unit, bigEndian, skip) = when {
            data.startsWith(0xEF, 0xBB, 0xBF) -> Triple(1, true, 3)
            data.startsWith(0x00, 0x00, 0xFE, 0xFF) -> Triple(4, true, 4)
            data.startsWith(0xFF, 0xFE) -> Triple(2, false, 2)
            data.startsWith(0xFE, 0xFF) -> Triple(2, true, 2)
            data.size > 4 -> {
                val zero = BooleanArray(4) { data[it].toInt() == 0 }
                when {
                    zero[0] && zero[1] && zero[2] -> Triple(4, true, 0)
                    zero[1] && zero[2] && zero[3] -> Triple(4, false, 0)
                    zero[0] && zero[2] -> Triple(2, true, 0)
                    zero[1] && zero[3] -> Triple(2, false, 0)
                    else -> Triple(1, true, 0)
                }
            }
            else -> Triple(1, true, 0)
        }
        if (unit == 1) return utf8(data, skip)
        val text = StringBuilder()
        var index = skip
        while (index + unit <= data.size) {
            var value = 0L
            for (byte in 0 until unit) {
                val part = (data[index + if (bigEndian) byte else unit - 1 - byte].toLong() and 0xFF)
                value = value shl 8 or part
            }
            index += unit
            if (unit == 4) {
                if (value > 0x10FFFF || value in 0xD800..0xDFFF) return null
                text.appendCodePoint(value.toInt())
            } else {
                text.append(value.toInt().toChar())
            }
        }
        // A UTF-16 body is text only with its surrogates paired.
        var position = 0
        while (position < text.length) {
            val char = text[position]
            if (char.isHighSurrogate() && position + 1 < text.length && text[position + 1].isLowSurrogate()) {
                position += 2
                continue
            }
            if (char.isSurrogate()) return null
            position++
        }
        return text.toString()
    }

    /** Strict UTF-8: no overlong forms, surrogates or scalars past U+10FFFF. */
    private fun utf8(data: ByteArray, start: Int): String? {
        val text = StringBuilder(data.size)
        var index = start
        while (index < data.size) {
            val first = data[index].toInt() and 0xFF
            val (length, minimum) = when {
                first < 0x80 -> 1 to 0
                first in 0xC2..0xDF -> 2 to 0x80
                first in 0xE0..0xEF -> 3 to 0x800
                first in 0xF0..0xF4 -> 4 to 0x10000
                else -> return null
            }
            if (index + length > data.size) return null
            var scalar = if (length == 1) first else first and (0xFF shr (length + 1))
            for (next in 1 until length) {
                val byte = data[index + next].toInt() and 0xFF
                if (byte and 0xC0 != 0x80) return null
                scalar = scalar shl 6 or (byte and 0x3F)
            }
            if (scalar < minimum || scalar > 0x10FFFF || scalar in 0xD800..0xDFFF) return null
            text.appendCodePoint(scalar)
            index += length
        }
        return text.toString()
    }

    private class Reader(val text: String) {
        var index = 0

        fun peek(): Char? = text.getOrNull(index)

        fun whitespace() {
            while (index < text.length && text[index].let { it == ' ' || it == '\t' || it == '\n' || it == '\r' }) index++
        }

        private fun expect(char: Char) {
            if (peek() != char) throw Invalid()
            index++
        }

        fun topObject(): Map<String, String?> {
            val result = LinkedHashMap<String, String?>()
            members(depth = 1) { key, value -> if (key !in result) result[key] = value }
            return result
        }

        /** An object's members, its `{` next; `depth` counts it. */
        private fun members(depth: Int, member: (String, String?) -> Unit) {
            expect('{')
            whitespace()
            if (peek() == '}') {
                index++
                return
            }
            while (true) {
                if (peek() != '"') throw Invalid()
                val key = string()
                whitespace()
                expect(':')
                whitespace()
                val value = if (peek() == '"') {
                    if (depth > 512) throw Invalid()
                    string()
                } else {
                    value(depth)
                    null
                }
                member(key, value)
                whitespace()
                when (peek()) {
                    ',' -> {
                        index++
                        whitespace()
                        // A comma may end the object.
                        if (peek() == '}') {
                            index++
                            return
                        }
                    }
                    '}' -> {
                        index++
                        return
                    }
                    else -> throw Invalid()
                }
            }
        }

        /** A value inside `depth` containers. */
        private fun value(depth: Int) {
            if (depth > 512) throw Invalid()
            when (peek()) {
                '{' -> members(depth + 1) { _, _ -> }
                '[' -> elements(depth + 1)
                '"' -> string()
                't' -> literal("true")
                'f' -> literal("false")
                'n' -> literal("null")
                else -> number()
            }
        }

        private fun elements(depth: Int) {
            expect('[')
            whitespace()
            if (peek() == ']') {
                index++
                return
            }
            while (true) {
                value(depth)
                whitespace()
                when (peek()) {
                    ',' -> {
                        index++
                        whitespace()
                        // A comma may end the array.
                        if (peek() == ']') {
                            index++
                            return
                        }
                    }
                    ']' -> {
                        index++
                        return
                    }
                    else -> throw Invalid()
                }
            }
        }

        private fun literal(word: String) {
            if (!text.startsWith(word, index)) throw Invalid()
            index += word.length
        }

        private fun number() {
            val start = index
            if (peek() == '-') index++
            when (peek()) {
                '0' -> index++
                in '1'..'9' -> while (peek() in '0'..'9') index++
                else -> throw Invalid()
            }
            if (peek() == '.') {
                index++
                if (peek() !in '0'..'9') throw Invalid()
                while (peek() in '0'..'9') index++
            }
            if (peek() == 'e' || peek() == 'E') {
                index++
                if (peek() == '+' || peek() == '-') index++
                if (peek() !in '0'..'9') throw Invalid()
                while (peek() in '0'..'9') index++
            }
            if (text.substring(start, index).toDouble() == Double.POSITIVE_INFINITY) throw Invalid()
        }

        fun string(): String {
            expect('"')
            val result = StringBuilder()
            while (true) {
                val char = peek() ?: throw Invalid()
                index++
                when {
                    char == '"' -> break
                    char < ' ' -> throw Invalid()
                    char != '\\' -> result.append(char)
                    else -> {
                        val escaped = peek() ?: throw Invalid()
                        index++
                        when (escaped) {
                            '"', '\\', '/' -> result.append(escaped)
                            'b' -> result.append('\b')
                            'f' -> result.append('\u000C')
                            'n' -> result.append('\n')
                            'r' -> result.append('\r')
                            't' -> result.append('\t')
                            'u' -> result.append(hex())
                            else -> throw Invalid()
                        }
                    }
                }
            }
            // An escaped surrogate stands only in a pair.
            var position = 0
            while (position < result.length) {
                val char = result[position]
                if (char.isHighSurrogate() && position + 1 < result.length && result[position + 1].isLowSurrogate()) {
                    position += 2
                    continue
                }
                if (char.isSurrogate()) throw Invalid()
                position++
            }
            return result.toString()
        }

        private fun hex(): Char {
            if (index + 4 > text.length) throw Invalid()
            var value = 0
            repeat(4) {
                val digit = Character.digit(text[index], 16)
                if (digit < 0 || text[index] > 'f') throw Invalid()
                value = value * 16 + digit
                index++
            }
            return value.toChar()
        }
    }
}
