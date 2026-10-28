package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationStage
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PostcodeCountry
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

// From ParcelStore.swift: the demo's parcels, kept in UserDefaults and written in the app's language.

/**
 * The demo's box: its sample parcels, and what someone did to them, kept in `defaults` (the iPhone's
 * `UserDefaults.standard`) under the iPhone app's keys. `language` is the app's language as it is now
 * (`AppLanguage.current` on the iPhone, the Localizer's on Android), which the saved text is written in.
 * `importCharges` is the debug build's setting that gives a parcel import charges (`ImportChargesPreview`); nil in a
 * release build. Used from the main thread, as the iPhone's store is.
 */
class DemoRepository(
    private val defaults: UserDefaults,
    private val language: () -> AppLanguage,
    private val now: () -> Instant = Instant::now,
    private val importCharges: () -> String? = { null },
) {
    private val catalogKey = "sdt.native.demo.catalog.v2"
    private val placesKey = "sdt.native.demo.places.v1"
    private val key = "sdt.native.demo.parcels.v1"
    private val preferencesKey = "sdt.native.demo.preferences.v1"

    /** The language the saved demo text is written in; English when absent. */
    private val languageKey = "sdt.native.demo.language.v1"

    val notificationPreferences: NotificationPreferences
        get() {
            val data = defaults.data(forKey = preferencesKey) ?: return defaultPreferences
            return try {
                decodeSwiftKeys(NotificationPreferences.serializer(), data)
            } catch (_: Exception) {
                defaultPreferences
            }
        }

    fun saveNotificationPreferences(value: NotificationPreferences): NotificationPreferences {
        store(encoder.encode(NotificationPreferences.serializer(), value), preferencesKey)
        return value
    }

    fun list(): List<Parcel> {
        val parcels = load().sortedByDescending { it.createdAt }
        return ImportChargesPreview.charging(parcels, importCharges())
    }

    fun add(request: CreatePackageRequest): Parcel {
        val carrier = request.carrier ?: throw DeliveryApiError.InvalidResponse()
        val all = load().toMutableList()
        all.firstOrNull { it.trackingNumber.equalsCanonically(request.trackingNumber) }?.let { existing ->
            throw DeliveryApiError.DuplicateTracking(existing.id)
        }
        val id = UUID.randomUUID()
        val now = DateParser.isoString(now())
        val event = TrackingEvent(
            id = UUID.randomUUID(), packageId = id, stage = TrackingStage.Pending,
            description = "Tracking added; the carrier has not announced it yet", location = null, occurredAt = now,
        )
        val parcel = Parcel(
            id = id,
            trackingNumber = request.trackingNumber,
            label = request.label ?: "",
            carrier = carrier,
            createdAt = now,
            expectedDelivery = null,
            lastStatusText = null,
            lastSyncedAt = now,
            syncStatus = SyncStatus.Ok,
            syncError = null,
            trackingUrl = request.trackingUrl,
            dpdPostcode = request.dpdPostcode,
            carrierData = startingCarrierData(request.trackingNumber, postcode = request.dpdPostcode, country = request.dpdPostcodeCountry),
            archivedAt = null,
            notificationsMuted = false,
            trackingEvents = listOf(event),
        )
        all += parcel
        save(all)
        return parcel
    }

    fun rename(id: UUID, label: String): Parcel {
        if (label.characterCount() > 80) throw DeliveryApiError.LabelTooLong()
        return update(id) { it.copy(label = label) }
    }

    fun changeCarrier(id: UUID, carrier: CarrierId, trackingUrl: String?, dpdPostcode: String?, dpdPostcodeCountry: String?): Parcel =
        update(id) { parcel ->
            val now = DateParser.isoString(now())
            parcel.copy(
                carrier = carrier,
                trackingUrl = trackingUrl,
                dpdPostcode = dpdPostcode,
                expectedDelivery = null,
                lastStatusText = null,
                lastSyncedAt = null,
                syncStatus = SyncStatus.Pending,
                syncError = null,
                carrierData = startingCarrierData(parcel.trackingNumber, postcode = dpdPostcode, country = dpdPostcodeCountry),
                trackingEvents = listOf(
                    TrackingEvent(
                        id = UUID.randomUUID(), packageId = id, stage = TrackingStage.Pending,
                        description = "Carrier changed; waiting for tracking", location = null, occurredAt = now,
                    ),
                ),
            )
        }

    fun setMuted(id: UUID, muted: Boolean): Parcel = update(id) { it.copy(notificationsMuted = muted) }

    fun setEmailMuted(id: UUID, muted: Boolean): Parcel = update(id) { it.copy(emailMuted = muted) }

    fun archive(id: UUID) {
        update(id) { it.copy(archivedAt = DateParser.isoString(now())) }
    }

    fun restore(id: UUID): Parcel = update(id) { it.copy(archivedAt = null) }

    fun permanentlyDelete(id: UUID) {
        val all = load()
        if (all.none { it.id == id }) throw DeliveryApiError.ParcelMissing()
        save(all.filter { it.id != id })
    }

    fun refreshAll(): List<Parcel> {
        val updated = load().map(::advance)
        save(updated)
        return updated.sortedByDescending { it.createdAt }
    }

    fun refresh(id: UUID): Parcel = update(id, ::advance)

    fun reset() {
        defaults.removeObject(forKey = key)
        defaults.removeObject(forKey = preferencesKey)
        defaults.removeObject(forKey = languageKey)
    }

    /**
     * The saved parcels with the demo's own text in the app's language. Names someone edited and text the demo did not
     * write match nothing and stay.
     */
    private fun load(): List<Parcel> {
        val parcels = saved()
        val target = language().rawValue
        val written = defaults.string(forKey = languageKey) ?: "en"
        if (written == target) return parcels
        val wanted = translations[target] ?: emptyMap()
        val english = originals[written] ?: emptyMap()
        fun say(text: String): String {
            val original = english[text.nfc()] ?: text
            return wanted[original.nfc()] ?: original
        }
        val translated = parcels.map { parcel ->
            parcel.copy(
                label = say(parcel.label),
                lastStatusText = parcel.lastStatusText?.let(::say),
                trackingEvents = parcel.trackingEvents.map { event ->
                    event.copy(description = say(event.description), location = event.location?.let(::say))
                },
            )
        }
        save(translated)
        defaults.set(target, forKey = languageKey)
        return translated
    }

    /** A simulated scan's text, said in the language the demo is written in. */
    private fun said(text: String): String = translations[language().rawValue]?.get(text.nfc()) ?: text

    private fun saved(): List<Parcel> {
        val parcels = defaults.data(forKey = key)?.let { data ->
            try {
                decodeSwiftKeys(format, data)
            } catch (_: Exception) {
                null
            }
        }
        if (parcels != null) {
            if (!defaults.bool(forKey = catalogKey)) {
                val legacyNumbers = listOf("12345678901234", "993412345678901234", "1Z999AA10123456784", "RR123456785DE", "443412345678901234")
                if (parcels.any { parcel -> legacyNumbers.any { it.equalsCanonically(parcel.trackingNumber) } }) {
                    val existing = parcels.map { it.trackingNumber.nfc() }.toSet()
                    val upgraded = withPlaces(parcels) + seed(now()).filter { it.trackingNumber.nfc() !in existing }
                    save(upgraded)
                    return upgraded
                }
                defaults.set(true, forKey = catalogKey)
            }
            if (!defaults.bool(forKey = placesKey)) {
                // Demo parcels saved before scans had places get them from the same samples.
                val located = withPlaces(parcels)
                save(located)
                return located
            }
            return parcels
        }
        val seeded = seed(now())
        save(seeded)
        defaults.removeObject(forKey = languageKey)
        return seeded
    }

    private fun save(parcels: List<Parcel>) {
        store(encoder.encode(format, parcels), key)
        defaults.set(true, forKey = catalogKey)
        defaults.set(true, forKey = placesKey)
    }

    /** `defaults.set(try? encode(…), forKey:)`: what could not be encoded leaves nothing under the key. */
    private fun store(data: ByteArray?, forKey: String) {
        if (data == null) defaults.removeObject(forKey = forKey) else defaults.set(data, forKey = forKey)
    }

    private fun update(id: UUID, change: (Parcel) -> Parcel): Parcel {
        val all = load().toMutableList()
        val index = all.indexOfFirst { it.id == id }
        if (index < 0) throw DeliveryApiError.ParcelMissing()
        all[index] = change(all[index])
        save(all)
        return all[index]
    }

    private fun advance(parcel: Parcel): Parcel {
        val stage = parcel.currentStage
        if (!parcel.isActive || stage == null) return parcel
        val next = next(stage) ?: return parcel
        val update = updates[next] ?: Update("Tracking updated", null, null)
        val timestamp = DateParser.isoString(now())
        val description = said(update.description)
        var copy = parcel.copy(
            trackingEvents = parcel.trackingEvents + TrackingEvent(
                id = UUID.randomUUID(), packageId = parcel.id, stage = next,
                description = description, location = update.location?.let(::said), occurredAt = timestamp, place = update.place,
            ),
            lastSyncedAt = timestamp,
            lastStatusText = description,
        )
        if (CarrierCatalog.supportsSwissPostHandoff(copy.trackingNumber) &&
            next in listOf(TrackingStage.InTransit, TrackingStage.Customs, TrackingStage.OutForDelivery, TrackingStage.Delivered)
        ) {
            copy = copy.copy(carrierData = CarrierData(activeTrackingCarrier = CarrierId.SwissPost, swissPostReady = true))
        }
        return copy
    }

    /** The scan a simulated refresh adds. */
    private class Update(val description: String, val location: String?, val place: EventPlace?)

    /** The sample parcels, in English, and each language's translation of their text. */
    @Serializable
    private class Catalog(val parcels: List<Sample>, val translations: Map<String, Map<String, String>>) {
        @Serializable
        class Sample(
            val label: String,
            val trackingNumber: String,
            val carrier: CarrierId,
            val expectedInDays: Int? = null,
            val archivedHoursAgo: Double? = null,
            val senderName: String? = null,
            val pickupPoint: String? = null,
            val weightKg: Double? = null,
            val events: List<Event>,
        ) {
            @Serializable
            class Event(
                val stage: TrackingStage,
                val hoursAgo: Double,
                val description: String,
                val location: String? = null,
                val place: EventPlace? = null,
            )
        }
    }

    companion object {
        private val format = ListSerializer(Parcel.serializer())

        /** The iPhone's JSONEncoder.deliveryTracker on its own models: keys sorted, as the Swift models name them. */
        private val encoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        private val catalog: Catalog by lazy {
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString(Catalog.serializer(), SharedResources.text("DeliveryDemo.json"))
            }.getOrElse { Catalog(emptyList(), emptyMap()) }
        }

        // Keyed as Swift's dictionaries compare their String keys, canonically: by their NFC form.

        /** Each language's translation of the English text. */
        private val translations: Map<String, Map<String, String>> by lazy {
            catalog.translations.mapValues { (_, values) ->
                HashMap<String, String>().also { keyed -> for ((english, translation) in values) keyed.putIfAbsent(english.nfc(), translation) }
            }
        }

        /** The English text of each language's translation. */
        private val originals: Map<String, Map<String, String>> by lazy {
            catalog.translations.mapValues { (_, values) ->
                HashMap<String, String>().also { keyed -> for ((english, translation) in values) keyed.putIfAbsent(translation.nfc(), english) }
            }
        }

        /** A parcel's carrier data as the service starts it: Swiss Post's handoff, and the country of a postcode from abroad. */
        private fun startingCarrierData(trackingNumber: String, postcode: String?, country: String?): CarrierData? {
            var data = if (CarrierCatalog.supportsSwissPostHandoff(trackingNumber)) {
                CarrierData(activeTrackingCarrier = CarrierId.Aliexpress, swissPostReady = false)
            } else {
                CarrierData()
            }
            if (postcode != null && country != null) data = data.copy(postcodeCountry = PostcodeCountry(postcode = postcode, country = country))
            return if (data == CarrierData()) null else data
        }

        private fun withPlaces(parcels: List<Parcel>): List<Parcel> {
            val known = HashMap<String, EventPlace>()
            for (event in seed().flatMap { it.trackingEvents }) {
                val location = event.location
                val place = event.place
                if (location != null && place != null) known[location.nfc()] = place
            }
            for (update in updates.values) {
                if (update.location != null && update.place != null) known[update.location.nfc()] = update.place
            }
            return parcels.map { parcel ->
                parcel.copy(
                    trackingEvents = parcel.trackingEvents.map { event ->
                        val location = event.location
                        val place = location?.let { known[it.nfc()] }
                        if (event.place != null || place == null) event else event.copy(place = place)
                    },
                )
            }
        }

        /** The scan each simulated refresh adds, placed where the server would place it. */
        private val updates: Map<TrackingStage, Update> by lazy {
            val zurich = EventPlace(latitude = 47.367, longitude = 8.55, precision = EventPlacePrecision.City, country = "CH", name = "Zürich")
            val harkingen = EventPlace(latitude = 47.305, longitude = 7.821, precision = EventPlacePrecision.City, country = "CH", name = "Härkingen")
            mapOf(
                TrackingStage.Registered to Update("The sender created a shipping label", null, null),
                TrackingStage.Accepted to Update("Parcel accepted at the counter", "Zürich-Mülligen", zurich),
                TrackingStage.InTransit to Update("Sorted at the parcel centre", "Härkingen", harkingen),
                TrackingStage.OutForDelivery to Update("With the courier for delivery today", "Your neighbourhood", null),
                TrackingStage.Delivered to Update("Delivered to your letterbox", "Home", null),
                TrackingStage.ReadyForPickup to Update("Ready for pickup at your branch", "Post branch", null),
                TrackingStage.Exception to Update("A problem is holding up the parcel", "Härkingen", harkingen),
            )
        }

        private fun next(stage: TrackingStage): TrackingStage? = when (stage) {
            TrackingStage.Pending -> TrackingStage.Registered
            TrackingStage.Registered -> TrackingStage.Accepted
            TrackingStage.Accepted, TrackingStage.Customs, TrackingStage.Exception -> TrackingStage.InTransit
            TrackingStage.InTransit -> TrackingStage.OutForDelivery
            TrackingStage.OutForDelivery, TrackingStage.ReadyForPickup -> TrackingStage.Delivered
            TrackingStage.FailedAttempt -> TrackingStage.ReadyForPickup
            TrackingStage.Delivered, TrackingStage.Returned -> null
        }

        // A `static let`: the zone the process first asks for.
        private val defaultPreferences: NotificationPreferences by lazy {
            NotificationPreferences(
                enabledStages = listOf(
                    NotificationStage.Registered, NotificationStage.Accepted, NotificationStage.InTransit, NotificationStage.Customs,
                    NotificationStage.Exception, NotificationStage.OutForDelivery, NotificationStage.FailedAttempt,
                    NotificationStage.ReadyForPickup, NotificationStage.Delivered, NotificationStage.Returned,
                ),
                quietHoursStart = null,
                quietHoursEnd = null,
                timezone = ZoneId.systemDefault().id,
            )
        }

        /** The sample parcels in English; `list()` writes them in the app's language. */
        fun seed(now: Instant = Instant.now()): List<Parcel> {
            fun iso(hoursAgo: Double): String = DateParser.isoString(now.minusNanos((hoursAgo * 3_600 * 1_000_000_000).toLong()))
            return catalog.parcels.map { sample ->
                val id = UUID.randomUUID()
                val history = sample.events.map { event ->
                    TrackingEvent(
                        id = UUID.randomUUID(), packageId = id, stage = event.stage, description = event.description,
                        location = event.location, occurredAt = iso(event.hoursAgo), place = event.place,
                    )
                }
                val expected = sample.expectedInDays?.let { days ->
                    ParcelOrganizer.dayKey(now.atZone(ZoneId.systemDefault()).plusDays(days.toLong()).toInstant())
                }
                Parcel(
                    id = id, trackingNumber = sample.trackingNumber, label = sample.label, carrier = sample.carrier,
                    createdAt = iso(sample.events.maxOfOrNull { it.hoursAgo } ?: 0.0), expectedDelivery = expected,
                    lastStatusText = history.sortedByDescending { it.occurredAt }.firstOrNull()?.description,
                    lastSyncedAt = iso(0.2), syncStatus = SyncStatus.Ok, syncError = null,
                    trackingUrl = null, dpdPostcode = if (sample.carrier == CarrierId.Dpd) "8000" else null,
                    carrierData = if (sample.senderName == null && sample.pickupPoint == null && sample.weightKg == null) {
                        null
                    } else {
                        CarrierData(senderName = sample.senderName, pickupPoint = sample.pickupPoint, weightKg = sample.weightKg)
                    },
                    archivedAt = sample.archivedHoursAgo?.let(::iso), notificationsMuted = false,
                    trackingEvents = history,
                )
            }
        }
    }
}
