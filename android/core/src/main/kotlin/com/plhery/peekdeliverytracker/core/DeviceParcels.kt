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
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.PublicLookupRequest
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.api.UpdateParcelLinkRequest
import com.plhery.peekdeliverytracker.core.api.UuidSerializer
import java.time.Instant
import java.util.UUID
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
    /** What its notifications on this phone tell, chosen when they were first turned on. */
    val alertPreset: ParcelAlertPreset? = null,
    /** The device token the service last answered about for this parcel's notifications. */
    val alertToken: String? = null,
) {
    val isViewer: Boolean get() = viewer == true
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

    /** The server sends no notifications to phones. */
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
    fun remember(postcode: String, carrier: CarrierId) {
        val postcode = postcode.trimmingWhitespacesAndNewlines().nonEmpty ?: return
        write(
            listOf(GivenPostcode(carrier = carrier, postcode = postcode)) +
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
 * How the device's parcels reach the service: no sign-in and no cookies, the link's id and its owner key only.
 *
 * Each call throws as the iPhone's does: `DeliveryApiError` for the service's answers, `DeviceLookupsSpent` once the
 * day's lookups are spent, an `IOException` for a network failure or a connection that timed out, and a
 * `CancellationException` when the call was cancelled.
 */
class DeviceParcelClient(
    val lookup: suspend (PublicLookupRequest) -> PublicLookupResponse,
    /** Nil when the service no longer has the parcel, or no longer shares it with a reader without the key. */
    val read: suspend (linkId: String, key: String?) -> PublicParcelResponse?,
    val forget: suspend (linkId: String, key: String) -> Unit,
    /** Changes what the link shows to others, or stops and resumes its sharing. */
    val update: suspend (linkId: String, key: String, UpdateParcelLinkRequest) -> PublicParcelResponse,
    /** `prefixOfSaved`: the number is the start of a longer one this phone follows, most likely that number typed again. */
    val detect: suspend (trackingNumber: String, prefixOfSaved: Boolean) -> CarrierDetectionResponse,
    /** What a reader says of the parcel behind a link. The owner key goes along when this phone holds it. */
    val feedback: suspend (linkId: String, key: String?, ParcelFeedbackRequest) -> Unit = { _, _, _ -> },
    /** Turns this phone's notifications on for the parcel behind a link, with the owner key when this phone holds it. */
    val setAlert: suspend (linkId: String, key: String?, ParcelAppAlertRequest) -> DeviceAlertAnswer = { _, _, _ -> DeviceAlertAnswer.Unavailable },
    /** Turns them off. Anyone with the link and the token may. */
    val removeAlert: suspend (linkId: String, DeleteParcelAppAlertRequest) -> Unit = { _, _ -> },
) {
    companion object {
        /**
         * The service's public parcel API, as the iPhone app calls it. Every request asks for JSON past any cache, with a
         * 30-second timeout, no cookies (none sent, none kept) and no `Authorization`; `X-Parcel-Key` carries the owner
         * key, only when there is one. A lookup and a detection go through `verification`, and a lookup alone may
         * present the browser check. `lookupCountry` is the device's region, which a detection sends.
         */
        fun api(
            configuration: AppConfiguration,
            transport: HttpTransport,
            verification: NativeVerification,
            lookupCountry: () -> String? = { ParcelLookupCountry.hint() },
        ): DeviceParcelClient {
            suspend fun send(path: String, method: String, key: String? = null, body: ByteArray? = null): HttpResponse {
                val headers = linkedMapOf("Accept" to "application/json")
                if (key != null) headers["X-Parcel-Key"] = key
                if (body != null) headers["Content-Type"] = "application/json"
                val request = HttpRequest(configuration.apiBaseUrl.appendingPath(path), method, headers, body, timeout = 30.0, handlesCookies = false)
                if (method == "POST" && (path == "api/public/parcels" || path == "api/public/detect")) {
                    return verification.send(request, configuration.apiBaseUrl, allowPresentation = path == "api/public/parcels", using = transport)
                }
                return transport.data(request)
            }

            fun failure(response: HttpResponse): Exception {
                val payload = JsonSerialization.stringsOfObject(response.body)
                if (response.statusCode == 429) {
                    if (payload?.get("scope") == "daily") return DeviceLookupsSpent()
                    return DeliveryApiError.RateLimited(response.header("Retry-After")?.let(::swiftDouble) ?: 60.0)
                }
                val message = payload?.get("error")
                if (response.statusCode == 400 && message != null) return DeliveryApiError.Service(message)
                return DeliveryApiError.ServiceFailed(response.statusCode)
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
                    if (response.statusCode == 404 || response.statusCode == 410) {
                        null
                    } else {
                        if (response.statusCode != 200) throw failure(response)
                        decode(PublicParcelResponse.serializer(), response)
                    }
                },
                forget = { linkId, key ->
                    val response = send("api/public/parcels/$linkId", "DELETE", key)
                    if (response.statusCode != 204 && response.statusCode != 404) throw failure(response)
                },
                update = { linkId, key, change ->
                    val response = send("api/public/parcels/$linkId", "PATCH", key, encoder.encode(UpdateParcelLinkRequest.serializer(), change))
                    if (response.statusCode != 200) throw failure(response)
                    decode(PublicParcelResponse.serializer(), response)
                },
                detect = { trackingNumber, prefixOfSaved ->
                    val body = encoder.encode(
                        CarrierDetectionRequest.serializer(),
                        CarrierDetectionRequest(trackingNumber = trackingNumber, lookupCountryHint = lookupCountry(), prefixOfSaved = if (prefixOfSaved) true else null),
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
                        404 -> DeviceAlertAnswer.Gone
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

    fun save(parcels: List<DeviceParcel>, keys: Map<String, String>)

    /** The parcels, and the owner keys by link id. */
    data class Contents(val parcels: List<DeviceParcel>, val keys: Map<String, String>)
}

/**
 * The file the device's parcels are kept in, which the app implements: on the iPhone `DeviceParcels/parcels.json` in
 * Application Support, readable once the phone was first unlocked after a restart. On Android a file in the app's own
 * storage (`filesDir`, which the phone encrypts with the user's credentials, not the device-protected storage), such as
 * `filesDir/DeviceParcels/parcels.json`, its folder made when it is first written.
 */
interface DeviceParcelFile {
    /** The file's bytes, or nil when there is no file or it cannot be read. */
    fun read(): ByteArray?

    /**
     * Replaces the file with `data` at once: written beside it, then renamed over it (`AtomicFile`), so a reader finds
     * the old bytes or the new ones, never part of either. Throws when it could not be written, leaving the old file.
     */
    fun write(data: ByteArray)

    /** Removes the file, if there is one. */
    fun delete()
}

/**
 * The device's parcels as the iPhone app keeps them: the owner keys in `keychain`, a secret store of their own
 * (KeychainStore on the iPhone, service "com.plhery.PeekDeliveryTracker.deviceParcels", kept on this device alone and
 * readable after the first unlock), as the JSON object JsonEncoder.deliveryTracker writes; and the parcels in `file`,
 * as the iPhone's JSONEncoder.deliveryTracker writes its models (`JsonEncoder` with `swiftKeys`). A backup that brings
 * the file back without the keys brings back someone else's parcels alone (`matched`).
 */
class DeviceParcelFiles(private val keychain: SessionPersistence, private val file: DeviceParcelFile) : DeviceParcelStorage {
    override fun load(): DeviceParcelStorage.Contents {
        val keys = keychain.load()?.let { data ->
            try {
                decodeJson(keysSerializer, data)
            } catch (_: Exception) {
                null
            }
        } ?: emptyMap()
        val parcels = file.read()?.let { data ->
            try {
                decodeSwiftKeys(parcelsSerializer, data)
            } catch (_: Exception) {
                null
            }
        } ?: return DeviceParcelStorage.Contents(emptyList(), emptyMap())
        return matched(parcels, keys)
    }

    override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>) {
        if (parcels.isEmpty()) {
            keychain.delete()
            try {
                file.delete()
            } catch (_: Exception) {
            }
            return
        }
        // The keys go first: a parcel on file always has its key.
        if (keys.isEmpty()) {
            keychain.delete()
        } else {
            try {
                keychain.save(JsonEncoder.deliveryTracker.encode(keysSerializer, keys))
            } catch (_: Exception) {
                return
            }
        }
        try {
            file.write(encoder.encode(parcelsSerializer, parcels))
        } catch (_: Exception) {
        }
    }

    companion object {
        private val keysSerializer = MapSerializer(String.serializer(), String.serializer())
        private val parcelsSerializer = ListSerializer(DeviceParcel.serializer())

        /** The iPhone's JSONEncoder.deliveryTracker on its own models: keys sorted, as the Swift models name them. */
        internal val encoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        /**
         * A parcel of this phone's own without its key cannot be read or forgotten, and a key without its parcel opens
         * nothing. A parcel followed through someone else's link needs no key.
         */
        fun matched(parcels: List<DeviceParcel>, keys: Map<String, String>): DeviceParcelStorage.Contents {
            val kept = parcels.filter { it.isViewer || keys.keyOf(it.linkId) != null }
            return DeviceParcelStorage.Contents(kept, keys.filterKeys { key -> kept.any { it.linkId.equalsCanonically(key) && !it.isViewer } })
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
 * nothing cancels: the app's own, on the main thread with a `SupervisorJob` (`ProcessLifecycleOwner`'s
 * `lifecycleScope`, or a `MainScope()` the application keeps). A build without a server has no `client` and follows
 * nothing. `debug` says whether this is a debug build, whose notification tokens are development ones.
 */
class DeviceParcels(
    private val client: DeviceParcelClient?,
    private val storage: DeviceParcelStorage,
    /** The postcodes its lookups were given, kept apart from the parcels. */
    val postcodes: PostcodeMemory,
    private val scope: CoroutineScope,
    private val now: () -> Instant = Instant::now,
    private val debug: Boolean = false,
    /** The device's region, which a carrier change sends as its lookup's country hint. */
    private val lookupCountry: () -> String? = { ParcelLookupCountry.hint() },
) {
    private var entries: List<DeviceParcel>
    private var keys: Map<String, String>

    /** The token and language a round of notification requests is under way for. */
    private var alertRound: String? = null

    /**
     * The token and language each parcel's notifications were asked for since launch. Each launch asks again, in case
     * the service lost them.
     */
    private var alertsAsked: Map<String, String> = emptyMap()

    init {
        val loaded = storage.load()
        entries = loaded.parcels
        keys = loaded.keys
    }

    val isEmpty: Boolean get() = entries.isEmpty()

    /** The parcels as the list shows them, under the names this device gave them. */
    fun list(): List<Parcel> = entries.map(::shown)

    /** Whether a parcel still waits for its carrier's first answer, which is worth asking about again soon. */
    val isWaiting: Boolean get() = entries.any { waiting(it.parcel) }

    suspend fun detect(trackingNumber: String): CarrierDetectionResponse {
        val client = client ?: throw DeliveryApiError.ServiceFailed(503)
        val count = trackingNumber.characterCount
        val prefixOfSaved = entries.any {
            it.parcel.trackingNumber.characterCount > count && it.parcel.trackingNumber.hasCanonicalPrefix(trackingNumber)
        }
        return client.detect(trackingNumber, prefixOfSaved)
    }

    suspend fun add(request: CreatePackageRequest): Parcel = lookUp(request, replacing = null)

    /** Looks the request up. The parcel it replaces, if any, does not count as already followed. */
    private suspend fun lookUp(request: CreatePackageRequest, replacing: UUID?): Parcel {
        val client = client ?: throw DeliveryApiError.ServiceFailed(503)
        entries.firstOrNull {
            it.parcel.id != replacing && it.parcel.trackingNumber.equalsCanonically(request.trackingNumber) &&
                it.parcel.carrier.rawValue.equalsCanonically(request.carrier?.rawValue)
        }?.let { existing -> throw DeliveryApiError.DuplicateTracking(existing.parcel.id) }
        val response = client.lookup(
            PublicLookupRequest(
                trackingNumber = request.trackingNumber, carrier = request.carrier,
                trackingUrl = request.trackingUrl, dpdPostcode = request.dpdPostcode,
                dpdPostcodeCountry = request.dpdPostcodeCountry, lookupCountryHint = request.lookupCountryHint,
            ),
        )
        if (!ParcelLinkRoute.validId(response.link.id)) throw DeliveryApiError.InvalidResponse()
        var entry = DeviceParcel(
            linkId = response.link.id, label = request.label ?: "", archivedAt = null,
            parcel = Parcel(shared = response.parcel), checkedAt = now().timeIntervalSinceReferenceDate,
        )
        // The service gives one parcel one link for each lookup; a second lookup of a parcel takes the first one's place.
        val twin = entries.indexOfFirst { it.parcel.id == response.parcel.id }
        if (twin >= 0) {
            val old = entries[twin]
            entries = entries.filterIndexed { index, _ -> index != twin }
            val key = keys.keyOf(old.linkId)
            keys = keys.setting(old.linkId, null)
            if (key != null || old.alertToken != null) {
                scope.launch {
                    old.alertToken?.let { token -> attempt { client.removeAlert(old.linkId, DeleteParcelAppAlertRequest(token = token)) } }
                    if (key != null) attempt { client.forget(old.linkId, key) }
                }
            }
            // The parcel being replaced came back unchanged: only its link is new.
            if (old.parcel.id == replacing) entry = entry.copy(label = old.label, archivedAt = old.archivedAt, replaced = old.replaced)
        }
        entries = entries + entry
        keys = keys.setting(entry.linkId, response.key)
        save()
        request.dpdPostcode?.let { postcodes.remember(it, request.carrier ?: response.parcel.carrier) }
        return shown(entry)
    }

    /**
     * Reads every parcel whose answer has gone stale, or every one when `all`. A parcel the service no longer has leaves
     * the list. The first trouble ends the round and is thrown; the list keeps what the device has.
     */
    suspend fun refresh(all: Boolean = false, only: UUID? = null) {
        val client = client ?: return
        var trouble: Exception? = null
        for (entry in entries) {
            if (only != null && entry.parcel.id != only) continue
            val key = keys.keyOf(entry.linkId)
            if (key == null && !entry.isViewer) continue
            val age = now().timeIntervalSinceReferenceDate - entry.checkedAt
            val fresh = if (waiting(entry.parcel)) waitingFresh else if (entry.parcel.currentStage?.isFinal == true) settledFresh else fresh
            if (!(all || only != null || age < 0 || age >= fresh)) continue
            try {
                val response = client.read(entry.linkId, key)
                val index = entries.indexOfFirst { it.linkId.equalsCanonically(entry.linkId) }
                if (index < 0) continue
                if (response != null) {
                    change(index) {
                        it.copy(
                            parcel = Parcel(shared = response.parcel), checkedAt = now().timeIntervalSinceReferenceDate,
                            canKeep = if (entry.isViewer) response.link.canKeep else it.canKeep,
                        )
                    }
                } else {
                    entries = entries.filterIndexed { position, _ -> position != index }
                    keys = keys.setting(entry.linkId, null)
                }
            } catch (error: Exception) {
                trouble = error
                break
            }
        }
        save()
        trouble?.let { throw it }
    }

    fun rename(id: UUID, label: String): Parcel = update(id) { it.copy(label = label) }

    fun setArchived(id: UUID, archived: Boolean): Parcel = update(id) { it.copy(archivedAt = if (archived) DateParser.isoString(now()) else null) }

    /**
     * The service forgets the parcel, then the device does. Someone else's parcel is forgotten here alone: its
     * notifications on this phone stop, and its link goes on working for others.
     */
    suspend fun forget(id: UUID) {
        val entry = entries.firstOrNull { it.parcel.id == id } ?: return
        val client = client
        if (client != null && entry.alertToken != null) {
            attempt { client.removeAlert(entry.linkId, DeleteParcelAppAlertRequest(token = entry.alertToken)) }
        }
        val key = keys.keyOf(entry.linkId)
        if (client != null && key != null) client.forget(entry.linkId, key)
        entries = entries.filter { !it.linkId.equalsCanonically(entry.linkId) }
        keys = keys.setting(entry.linkId, null)
        save()
    }

    /** Forgets every parcel. The ones the service could not be told about stay, and the trouble is thrown. */
    suspend fun forgetAll() {
        var trouble: Exception? = null
        for (entry in entries) {
            try {
                forget(entry.parcel.id)
            } catch (error: Exception) {
                trouble = error
            }
        }
        trouble?.let { throw it }
    }

    /** Follows the same number with another carrier, link or postcode: a new lookup, which takes the old one's place and name. */
    suspend fun changeCarrier(id: UUID, carrier: CarrierId, trackingUrl: String?, dpdPostcode: String?, dpdPostcodeCountry: String? = null): Parcel {
        // Someone else's parcel is followed as its link shows it, and its carrier stays theirs to change.
        val entry = entries.firstOrNull { it.parcel.id == id }
        if (entry == null || entry.isViewer) throw DeliveryApiError.ParcelMissing()
        val added = lookUp(
            CreatePackageRequest(
                trackingNumber = entry.parcel.trackingNumber, label = entry.label, carrier = carrier,
                trackingUrl = trackingUrl, dpdPostcode = dpdPostcode, dpdPostcodeCountry = dpdPostcodeCountry,
                lookupCountryHint = lookupCountry(),
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
     * What an account needs to take the device's parcels over: its own with their keys, and someone else's that the
     * link lets an account keep.
     */
    val claims: List<ClaimParcelLink>
        get() = entries.mapNotNull { entry ->
            if (entry.isViewer) {
                if (entry.canKeep == true) ClaimParcelLink(id = entry.linkId, label = entry.label.nonEmpty) else null
            } else {
                keys.keyOf(entry.linkId)?.let { ClaimParcelLink(id = entry.linkId, key = it, label = entry.label.nonEmpty) }
            }
        }

    /** Whether the parcel behind this link is someone else's, followed here as its reader. */
    fun isViewer(linkId: String): Boolean = entries.firstOrNull { it.linkId.equalsCanonically(linkId) }?.isViewer == true

    /** The link of a parcel followed here through someone else's link, which is all this phone may share of it. */
    fun viewerLink(id: UUID): String? = entries.firstOrNull { it.parcel.id == id && it.isViewer }?.linkId

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
                for ((linkId, token) in alerts) attempt { client.removeAlert(linkId, DeleteParcelAppAlertRequest(token = token)) }
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
        val index = entries.indexOfFirst { it.linkId.equalsCanonically(linkId) || it.parcel.id == response.parcel.id }
        if (index >= 0) {
            if (key == null || !entries[index].isViewer) return Followed(entries[index].parcel.id, false)
            val old = entries[index]
            var owned = old.copy(
                linkId = linkId, viewer = null, canKeep = null, parcel = Parcel(shared = response.parcel),
                checkedAt = now().timeIntervalSinceReferenceDate,
            )
            if (!old.linkId.equalsCanonically(linkId)) {
                // Another link's notifications give way to this one's, asked for on the next round.
                owned = owned.copy(alertToken = null)
                val client = client
                val token = old.alertToken
                if (client != null && token != null) {
                    scope.launch { attempt { client.removeAlert(old.linkId, DeleteParcelAppAlertRequest(token = token)) } }
                }
            }
            change(index) { owned }
            keys = keys.setting(linkId, key)
            save()
            return Followed(entries[index].parcel.id, false)
        }
        var entry = DeviceParcel(
            linkId = linkId, label = label?.nonEmpty ?: "", archivedAt = null,
            parcel = Parcel(shared = response.parcel), checkedAt = now().timeIntervalSinceReferenceDate,
        )
        if (key != null) {
            keys = keys.setting(linkId, key)
        } else {
            entry = entry.copy(viewer = true, canKeep = response.link.canKeep)
        }
        entries = entries + entry
        save()
        return Followed(entry.parcel.id, true)
    }

    // Notifications

    /** Whether a parcel is still on its way, with something left to tell. */
    val hasParcelsOnTheirWay: Boolean get() = entries.any { it.parcel.currentStage?.isFinal != true }

    /**
     * Turns notifications on, on this phone's token and in its language, for every parcel still on its way: once a
     * launch, and again when the token or the language changes. A parcel that arrived needs none. A round is short and
     * quiet: a few parcels at most, and it ends at the first sign that the service wants a pause or sends no
     * notifications. A link that leads nowhere, has no room or is no longer shared is left until the next launch.
     */
    suspend fun registerAlerts(token: String, locale: NativePushLocale, environment: NativePushEnvironment = NativePushEnvironment.current(debug)) {
        val asked = "$token ${locale.rawValue}"
        val client = client ?: return
        if (alertRound.equalsCanonically(asked)) return
        alertRound = asked
        try {
            val due = entries.filter { !asked.equalsCanonically(alertsAsked.keyOf(it.linkId)) && it.parcel.currentStage?.isFinal != true }.take(alertsPerRound)
            for (entry in due) {
                val preset = entry.alertPreset ?: alertPreset(entry.parcel)
                val answer = try {
                    client.setAlert(
                        entry.linkId, keys.keyOf(entry.linkId),
                        ParcelAppAlertRequest(token = token, environment = environment, preset = preset, locale = locale),
                    )
                } catch (_: Exception) {
                    break
                }
                if (answer == DeviceAlertAnswer.Unavailable) break
                alertsAsked = alertsAsked.setting(entry.linkId, asked)
                val index = entries.indexOfFirst { it.linkId.equalsCanonically(entry.linkId) }
                if (answer != DeviceAlertAnswer.On || index < 0) continue
                val old = entries[index].alertToken
                change(index) { it.copy(alertToken = token, alertPreset = preset) }
                save()
                // The token changed: the old one hears nothing more of this parcel.
                if (old != null && !old.equalsCanonically(token)) {
                    attempt { client.removeAlert(entry.linkId, DeleteParcelAppAlertRequest(token = old)) }
                }
            }
        } finally {
            alertRound = null
        }
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

    private fun save() = storage.save(entries, keys)

    companion object {
        /**
         * How long an answer is good for, in seconds: a parcel on its way moves, one that arrived rests, and one its
         * carrier has not answered about yet is asked about again at once.
         */
        const val fresh: Double = 5.0 * 60
        const val settledFresh: Double = 12.0 * 60 * 60
        const val waitingFresh: Double = 1.5

        /** How many parcels one round asks notifications for; the rest wait for the next round. */
        const val alertsPerRound = 25

        /**
         * What a parcel's notifications tell when they are first turned on, as on the site: every scan while nothing has
         * moved yet, the steps that matter after that.
         */
        fun alertPreset(parcel: Parcel): ParcelAlertPreset =
            if (!parcel.hasCarrierUpdate || parcel.currentStage == TrackingStage.Registered) ParcelAlertPreset.All else ParcelAlertPreset.Important

        private fun waiting(parcel: Parcel): Boolean = parcel.syncStatus == SyncStatus.Pending || parcel.syncStatus == SyncStatus.Syncing

        private fun share(link: ParcelLink): ParcelShare? {
            if (link.shared == false) return null
            return ParcelShare(id = link.id, showNumber = link.showNumber ?: false, gift = link.gift ?: false, createdAt = link.createdAt, giftWords = link.giftWords)
        }

        private fun shown(entry: DeviceParcel): Parcel {
            val parcel = entry.parcel.copy(label = entry.label, archivedAt = entry.archivedAt)
            val replaced = entry.replaced ?: return parcel
            return parcel.copy(carrierData = (parcel.carrierData ?: CarrierData()).copy(originalPackageId = replaced))
        }
    }
}

/** Swift's `try?` on a call whose failure changes nothing: whatever it throws, a cancellation too, is let go. */
private inline fun attempt(block: () -> Unit) {
    try {
        block()
    } catch (_: Exception) {
    }
}

/** Swift's `keys[linkID]`: a Dictionary finds the key canonically equivalent to the one asked for. */
private fun Map<String, String>.keyOf(linkId: String): String? = this[linkId] ?: entries.firstOrNull { it.key.equalsCanonically(linkId) }?.value

/**
 * Swift's `keys[linkID] = key` on a copy: a key canonically equivalent already there keeps its spelling and takes the
 * value, and nil removes it.
 */
private fun Map<String, String>.setting(linkId: String, key: String?): Map<String, String> {
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
