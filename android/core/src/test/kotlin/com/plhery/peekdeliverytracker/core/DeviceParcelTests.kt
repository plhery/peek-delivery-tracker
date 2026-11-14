package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionRequest
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ChangePackageCarrierRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResult
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResultOutcome
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsResponse
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageResponse
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.ParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicLookupRequest
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Instant
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/**
 * DeviceParcelTests.swift: parcels followed on the phone without an account. What the Swift tests leave to a `Task`
 * runs in the test's scope, and `advanceUntilIdle` stands for their waiting on it.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DeviceParcelTests {
    private class Memory : DeviceParcelStorage {
        var parcels: List<DeviceParcel> = emptyList()
        var keys: Map<String, String> = emptyMap()
        var pendingForgets: Map<String, String> = emptyMap()

        override fun load(): DeviceParcelStorage.Contents = DeviceParcelStorage.Contents(parcels, keys, pendingForgets)

        override fun save(parcels: List<DeviceParcel>, keys: Map<String, String>, pendingForgets: Map<String, String>) {
            this.parcels = parcels
            this.keys = keys
            this.pendingForgets = pendingForgets
        }
    }

    /** What the service was asked, in order. */
    private class Calls {
        private val log = mutableListOf<String>()
        private val countryHints = mutableListOf<String?>()
        private val postcodes = mutableListOf<String?>()
        private val languages = mutableListOf<String?>()
        private val digits = mutableListOf<String?>()

        fun add(call: String) = synchronized(this) { log += call }
        fun lookupCountry(hint: String?) = synchronized(this) { countryHints += hint }
        fun lookupPostcode(postcode: String?) = synchronized(this) { postcodes += postcode }
        fun lookupLanguage(language: String?) = synchronized(this) { languages += language }
        fun lookupPhoneDigits(phoneDigits: String?) = synchronized(this) { digits += phoneDigits }
        val all: List<String> get() = synchronized(this) { log.toList() }
        val lookupCountries: List<String?> get() = synchronized(this) { countryHints.toList() }
        val lookupPostcodes: List<String?> get() = synchronized(this) { postcodes.toList() }
        val lookupLanguages: List<String?> get() = synchronized(this) { languages.toList() }
        val lookupPhoneDigits: List<String?> get() = synchronized(this) { digits.toList() }
    }

    private val key = "k".repeat(43)

    private fun given(carrier: CarrierId, postcode: String) = GivenPostcode(carrier = carrier, postcode = postcode)

    /** Swift's `Date` in a DeviceParcel: seconds since 2001. */
    private fun date(instant: Instant = Instant.now()): Double = instant.timeIntervalSinceReferenceDate

    private fun pkg(
        id: UUID,
        number: String = "TESTPARCEL123789",
        carrier: CarrierId = CarrierId.Dhl,
        stage: TrackingStage? = TrackingStage.InTransit,
        sync: SyncStatus = SyncStatus.Ok,
    ): PublicPackage = PublicPackage(
        id = id, trackingNumber = number, label = "", carrier = carrier, createdAt = "2026-10-01T08:00:00Z",
        syncStatus = sync, carrierData = PublicPackageCarrierData(), notificationsMuted = false,
        trackingEvents = stage?.let {
            listOf(TrackingEvent(id = UUID.randomUUID(), packageId = id, stage = it, description = "", occurredAt = "2026-10-02T08:00:00Z"))
        } ?: emptyList(),
    )

    private fun link(id: String, shared: Boolean = true) = ParcelLink(
        id = id, role = ParcelLinkRole.Owner, kind = ParcelLinkKind.Lookup, createdAt = "2026-10-01T08:00:00Z", numberShown = true,
        canKeep = true, showNumber = false, gift = false, shared = shared,
    )

    private fun reader(id: String, canKeep: Boolean = true) = ParcelLink(
        id = id, role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Shared, createdAt = "2026-10-01T08:00:00Z", numberShown = false,
        canKeep = canKeep,
    )

    private fun client(
        calls: Calls,
        lookup: PublicLookupResponse? = null,
        read: (String) -> PublicParcelResponse? = { null },
        alert: (String) -> DeviceAlertAnswer = { DeviceAlertAnswer.On },
        unalert: (String) -> Unit = {},
    ) = DeviceParcelClient(
        lookup = { request ->
            calls.add("lookup ${request.trackingNumber} ${request.carrier?.rawValue ?: "-"}")
            calls.lookupCountry(request.lookupCountryHint)
            calls.lookupPostcode(request.dpdPostcode)
            calls.lookupLanguage(request.language)
            calls.lookupPhoneDigits(request.phoneDigits)
            lookup ?: throw DeviceLookupsSpent()
        },
        read = { linkId, key ->
            calls.add("read $linkId ${(key ?: "-").take(1)}")
            read(linkId)
        },
        forget = { linkId, _ -> calls.add("forget $linkId") },
        update = { linkId, _, change ->
            calls.add("update $linkId shared=${change.shared?.toString() ?: "-"}")
            PublicParcelResponse(
                link = ParcelLink(
                    id = linkId, role = ParcelLinkRole.Owner, kind = ParcelLinkKind.Lookup, createdAt = "2026-10-01T08:00:00Z",
                    numberShown = true, canKeep = true, showNumber = change.showNumber ?: false, gift = change.gift ?: false,
                    shared = change.shared ?: true,
                ),
                parcel = PublicPackage(
                    id = UUID.randomUUID(), label = "", carrier = CarrierId.Dhl, createdAt = "2026-10-01T08:00:00Z", syncStatus = SyncStatus.Ok,
                    carrierData = PublicPackageCarrierData(), notificationsMuted = false, trackingEvents = emptyList(),
                ),
            )
        },
        detect = { number, prefixOfSaved, typed ->
            calls.add("detect $number${if (prefixOfSaved) " prefix" else ""}${typed?.let { " as $it" } ?: ""}")
            throw DeliveryApiError.ServiceFailed(503)
        },
        setAlert = { linkId, key, request ->
            calls.add(
                "alert $linkId ${(key ?: "-").take(1)} ${request.preset.rawValue} ${request.token} ${request.locale.rawValue} ${request.environment.rawValue} ${request.transport?.rawValue}",
            )
            alert(linkId)
        },
        removeAlert = { linkId, request ->
            calls.add("unalert $linkId ${request.token} ${request.transport?.rawValue}")
            unalert(linkId)
        },
    )

    /** Swift's `var client = …` with one of its calls set anew: the same client, the calls given replaced. */
    private fun DeviceParcelClient.replacing(
        forget: suspend (String, String) -> Unit = this.forget,
        setAlert: suspend (String, String?, ParcelAppAlertRequest) -> DeviceAlertAnswer = this.setAlert,
    ) = DeviceParcelClient(lookup, read, forget, update, detect, feedback, setAlert, removeAlert)

    private fun device(
        client: DeviceParcelClient?,
        storage: DeviceParcelStorage,
        scope: CoroutineScope,
        postcodes: PostcodeMemory = PostcodeMemory(MemoryDefaults()),
        now: () -> Instant = Instant::now,
        region: Locale = Locale.forLanguageTag("fr-CH"),
        language: AppLanguage = AppLanguage.En,
    ) = DeviceParcels(client, storage, postcodes, scope, region = { region }, language = { language }, now = now)

    private fun parcel(linkId: String, label: String, id: UUID, at: Double = date(), pkg: PublicPackage = pkg(id)) =
        DeviceParcel(linkId = linkId, label = label, archivedAt = null, parcel = Parcel(shared = pkg), checkedAt = at)

    /** The store's notifications when the phone has them off, so no test asks. */
    private fun quietNotifications() =
        DeviceNotificationAccess(status = { NotificationAuthorizationStatus.Denied }, request = {}, register = {}, token = { null })

    private val postcodeConfiguration = AppConfiguration(
        mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://postcodes.test", supabaseUrl = "https://postcodes.test",
        supabasePublishableKey = "public", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
        appGroupIdentifier = "postcodes.test",
    )

    private suspend fun assertThrows(block: suspend () -> Unit) {
        try {
            block()
        } catch (_: Exception) {
            return
        }
        fail("No error was thrown")
    }

    @Test
    fun aLookupIsKeptWithItsKeyUnderTheNameGivenHere() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        val calls = Calls()
        val device = device(client(calls, lookup = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(id))), storage, this)

        val parcel = device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", label = "New sneakers", carrier = CarrierId.Dhl))

        assertEquals(id, parcel.id)
        assertEquals("New sneakers", parcel.label)
        assertEquals(listOf("New sneakers"), device.list().map { it.label })
        assertEquals(mapOf("22222222222A" to key), storage.keys)
        // The service never learns the name: it stays beside the parcel on the device.
        assertEquals("", storage.parcels.first().parcel.label)
        assertEquals(listOf(ClaimParcelLink(id = "22222222222A", key = key, label = "New sneakers")), device.claims)
        assertEquals(listOf("lookup TESTPARCEL123789 dhl"), calls.all)
        assertEquals(listOf<String?>(null), calls.lookupCountries)
    }

    @Test
    fun countryHintUsesTheDeviceRegionRatherThanItsLanguage() {
        fun locale(identifier: String): Locale = Locale.forLanguageTag(identifier.replace('_', '-'))
        assertEquals("CH", ParcelLookupCountry.hint(locale("en_CH")))
        assertEquals("US", ParcelLookupCountry.hint(locale("fr_US")))
        assertEquals("GB", ParcelLookupCountry.hint(locale("de_GB")))
        assertEquals("GB", ParcelLookupCountry.hint(locale("en_UK")))
        for (identifier in listOf("en", "en_001", "es_419", "en_EU", "en_UN", "en_ZZ", "en_AA")) {
            assertNull(identifier, ParcelLookupCountry.hint(locale(identifier)))
        }
    }

    @Test
    fun aNewGuestLookupForwardsItsCountryHint() = runTest {
        val calls = Calls()
        // The app's language, here Polish, as the iPhone's AppLanguage.current.
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(UUID.randomUUID()))), Memory(), this,
            language = AppLanguage.Pl,
        )

        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl, lookupCountryHint = "CH"))

        assertEquals(listOf<String?>("CH"), calls.lookupCountries)
        // A carrier that words its scans in several languages answers the parcel in the app's.
        assertEquals(listOf<String?>(AppLanguage.Pl.rawValue), calls.lookupLanguages)
        assertEquals(listOf("lookup TESTPARCEL123789 dhl"), calls.all)
    }

    @Test
    fun changingCarrierSendsTheDeviceCountryAndKeepsTheParcelName() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId))
        val calls = Calls()
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(nextId, carrier = CarrierId.Dpd))),
            storage, this,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(CarrierId.Dpd, parcel.carrier)
        // The region the app gives, here French in Switzerland.
        assertEquals(listOf<String?>("CH"), calls.lookupCountries)
        assertEquals(listOf("lookup TESTPARCEL123789 dpd", "forget 22222222222A"), calls.all)
        assertEquals(listOf(previousId), storage.parcels.map { it.replaced })
        assertNull(storage.keys["22222222222A"])
    }

    @Test
    fun aNewPostcodeForTheSameCarrierIsALookupThatTakesTheParcelsPlace() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId, pkg = pkg(previousId, carrier = CarrierId.Dpd)))
        val calls = Calls()
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(nextId, carrier = CarrierId.Dpd))),
            storage, this, postcodes = PostcodeMemory(MemoryDefaults()),
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = "75002")

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(previousId, parcel.carrierData?.originalPackageId)
        assertEquals(listOf("lookup TESTPARCEL123789 dpd", "forget 22222222222A"), calls.all)
        assertEquals(listOf<String?>("75002"), calls.lookupPostcodes)
        assertEquals(listOf(nextId), device.list().map { it.id })
        assertEquals(listOf(previousId), storage.parcels.map { it.replaced })
        assertEquals(mapOf("22222222222B" to key), storage.keys)
    }

    @Test
    fun theSameDetailsGivenAgainKeepWhatTheDeviceKnowsOfTheParcel() = runTest {
        val id = UUID.randomUUID()
        val earlierId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(
            DeviceParcel(
                linkId = "22222222222A", label = "Moon lamp", archivedAt = "2026-10-03T08:00:00Z",
                parcel = Parcel(shared = pkg(id, carrier = CarrierId.Dpd)), checkedAt = date(), replaced = earlierId,
            ),
        )
        val calls = Calls()
        // The service finds the parcel it already has and gives it one more link.
        val device = device(
            client(
                calls,
                lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(id, carrier = CarrierId.Dpd, stage = TrackingStage.OutForDelivery)),
            ),
            storage, this, postcodes = PostcodeMemory(MemoryDefaults()),
        )

        val parcel = device.changeCarrier(id = id, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = "75001")

        assertEquals(id, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals("2026-10-03T08:00:00Z", parcel.archivedAt)
        assertEquals(earlierId, parcel.carrierData?.originalPackageId)
        assertEquals(TrackingStage.OutForDelivery, parcel.currentStage)
        assertEquals(listOf(parcel), device.list())
        assertEquals(listOf("22222222222B"), storage.parcels.map { it.linkId })
        assertEquals(mapOf("22222222222B" to key), storage.keys)
        advanceUntilIdle()
        assertEquals(listOf("lookup TESTPARCEL123789 dpd", "forget 22222222222A"), calls.all)
    }

    @Test
    fun changingToTheNumberAndCarrierOfAnotherParcelIsRefused() = runTest {
        val changing = UUID.randomUUID()
        val other = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", changing, pkg = pkg(changing, carrier = CarrierId.Dhl)),
            parcel("22222222222B", "", other, pkg = pkg(other, carrier = CarrierId.Dpd)),
        )
        val calls = Calls()
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222C"), key = key, parcel = pkg(UUID.randomUUID(), carrier = CarrierId.Dpd))),
            storage, this,
        )

        try {
            device.changeCarrier(id = changing, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)
            fail("A second lookup went out")
        } catch (error: DeliveryApiError.DuplicateTracking) {
            assertEquals(other, error.parcelId)
        }
        assertEquals(emptyList<String>(), calls.all)
        assertEquals(listOf(changing, other), device.list().map { it.id })
    }

    @Test
    fun phoneDigitsGoInARequestOnlyWhenGiven() {
        fun <T> body(serializer: SerializationStrategy<T>, request: T): JsonObject =
            Json.parseToJsonElement(JsonEncoder.deliveryTracker.encode(serializer, request).decodeToString()).jsonObject
        fun JsonObject.digits() = this["phoneDigits"]?.jsonPrimitive?.content
        val number = "JMX000000000001"
        val mx = CarrierId.JAndTMx
        assertNull(body(CreatePackageRequest.serializer(), CreatePackageRequest(trackingNumber = number, carrier = mx)).digits())
        assertEquals(
            "0472",
            body(CreatePackageRequest.serializer(), CreatePackageRequest(trackingNumber = number, carrier = mx, phoneDigits = "0472")).digits(),
        )
        assertNull(body(ChangePackageCarrierRequest.serializer(), ChangePackageCarrierRequest(carrier = mx)).digits())
        assertEquals(
            "0472",
            body(ChangePackageCarrierRequest.serializer(), ChangePackageCarrierRequest(carrier = mx, phoneDigits = "0472")).digits(),
        )
        assertNull(body(PublicLookupRequest.serializer(), PublicLookupRequest(trackingNumber = number, carrier = mx)).digits())
        assertEquals(
            "0472",
            body(PublicLookupRequest.serializer(), PublicLookupRequest(trackingNumber = number, carrier = mx, phoneDigits = "0472")).digits(),
        )
    }

    @Test
    fun aLookupGivesItsCarrierThePhoneDigitsAndThePhoneKeepsNone() = runTest {
        val memory = PostcodeMemory(MemoryDefaults())
        val storage = Memory()
        val calls = Calls()
        // A set clock, a set id and no events: the file then holds only what the test chose, where a date or a random
        // id could hold the digits by chance.
        val found = PublicLookupResponse(
            link = link("22222222222A"), key = key,
            parcel = pkg(UUID(0, 1), number = "JMX000000000001", carrier = CarrierId.JAndTMx, stage = null),
        )
        val device = device(client(calls, lookup = found), storage, this, postcodes = memory, now = { Instant.parse("2026-10-02T09:00:00Z") })

        val parcel = device.add(CreatePackageRequest(trackingNumber = "JMX000000000001", carrier = CarrierId.JAndTMx, phoneDigits = "0472"))

        assertEquals(listOf<String?>("0472"), calls.lookupPhoneDigits)
        // The link never shows them, and the phone keeps none: not with the parcel, nor for the next one's field.
        assertNull(parcel.phoneDigits)
        val stored = JsonEncoder.deliveryTracker.encode(ListSerializer(DeviceParcel.serializer()), storage.parcels).decodeToString()
        assertFalse(stored.contains("0472"))
        assertEquals(emptyList<GivenPostcode>(), memory.entries)
    }

    @Test
    fun changingTheCarrierOfAParcelFollowedHereSendsTheDigitsGivenForIt() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId, pkg = pkg(previousId, number = "JMX000000000001")))
        val calls = Calls()
        val device = device(
            client(
                calls,
                lookup = PublicLookupResponse(
                    link = link("22222222222B"), key = key, parcel = pkg(nextId, number = "JMX000000000001", carrier = CarrierId.JAndTMx),
                ),
            ),
            storage, this,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.JAndTMx, trackingUrl = null, dpdPostcode = null, phoneDigits = "0472")

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(listOf<String?>("0472"), calls.lookupPhoneDigits)
        assertEquals(listOf("lookup JMX000000000001 j-and-t-mx", "forget 22222222222A"), calls.all)
    }

    @Test
    fun theCountryHintIsOptionalInDetectionAndAddRequests() {
        fun <T> body(serializer: SerializationStrategy<T>, request: T): Map<String, String> {
            val json = Json.parseToJsonElement(JsonEncoder.deliveryTracker.encode(serializer, request).decodeToString()).jsonObject
            return json.mapValues { (_, value) -> value.jsonPrimitive.also { assertTrue(it.isString) }.content }
        }
        assertNull(body(CarrierDetectionRequest.serializer(), CarrierDetectionRequest(trackingNumber = "SYNTHETIC"))["lookupCountryHint"])
        assertEquals(
            "CH",
            body(CarrierDetectionRequest.serializer(), CarrierDetectionRequest(trackingNumber = "SYNTHETIC", lookupCountryHint = "CH"))["lookupCountryHint"],
        )
        assertEquals(
            "US",
            body(CreatePackageRequest.serializer(), CreatePackageRequest(trackingNumber = "SYNTHETIC", lookupCountryHint = "US"))["lookupCountryHint"],
        )
        assertEquals(
            "GB",
            body(PublicLookupRequest.serializer(), PublicLookupRequest(trackingNumber = "SYNTHETIC", lookupCountryHint = "GB"))["lookupCountryHint"],
        )
    }

    @Test
    fun detectionSendsTheNumberAsTypedWhenItSpellsTheNumberDifferently() = runTest {
        assertEquals("test-1234-5", typedTrackingNumber(" test-1234-5 ", "TEST12345"))
        assertEquals("TEST 1234 5", typedTrackingNumber("TEST 1234 5", "TEST12345"))
        assertNull(typedTrackingNumber("TEST12345", "TEST12345"))
        assertNull("Another number is not this one as typed", typedTrackingNumber("TEST-1234-6", "TEST12345"))
        assertNull(typedTrackingNumber(null, "TEST12345"))
        assertNull(typedTrackingNumber("1-".repeat(41), "1".repeat(41)))

        val body = Json.parseToJsonElement(
            JsonEncoder.deliveryTracker.encode(
                CarrierDetectionRequest.serializer(), CarrierDetectionRequest(trackingNumber = "TEST12345", typedNumber = "TEST-1234-5"),
            ).decodeToString(),
        ).jsonObject
        assertEquals("TEST12345", body["trackingNumber"]?.jsonPrimitive?.content)
        assertEquals("TEST-1234-5", body["typedNumber"]?.jsonPrimitive?.content)
        val bare = Json.parseToJsonElement(
            JsonEncoder.deliveryTracker.encode(CarrierDetectionRequest.serializer(), CarrierDetectionRequest(trackingNumber = "TEST12345")).decodeToString(),
        ).jsonObject
        assertNull(bare["typedNumber"])

        val calls = Calls()
        val device = device(client(calls), Memory(), this)
        runCatching { device.detect(trackingNumber = "TEST12345", typed = "TEST-1234-5") }
        runCatching { device.detect(trackingNumber = "TEST12345") }
        assertEquals(listOf("detect TEST12345 as TEST-1234-5", "detect TEST12345"), calls.all)
    }

    @Test
    fun aDetectionGivesTheNumberAsTypedWhenItDiffers() = runTest {
        val calls = Calls()
        val device = device(client(calls), Memory(), this)

        runCatching { device.detect(trackingNumber = "314159265", typed = " 31415926-5 ") }
        runCatching { device.detect(trackingNumber = "314159265", typed = "314159265") }
        runCatching { device.detect(trackingNumber = "314159265") }

        assertEquals(listOf("detect 314159265 as 31415926-5", "detect 314159265", "detect 314159265"), calls.all)
    }

    @Test
    fun theStartOfANumberThisIPhoneFollowsSaysSoWhenAsked() = runTest {
        val calls = Calls()
        val device = device(client(calls, lookup = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(UUID.randomUUID()))), Memory(), this)
        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl))

        runCatching { device.detect(trackingNumber = "TESTPARCEL12") }
        runCatching { device.detect(trackingNumber = "TESTPARCEL123789") }
        runCatching { device.detect(trackingNumber = "OTHERPARCEL1") }

        assertEquals(
            listOf("lookup TESTPARCEL123789 dhl", "detect TESTPARCEL12 prefix", "detect TESTPARCEL123789", "detect OTHERPARCEL1"),
            calls.all,
        )
    }

    @Test
    fun theSameNumberIsNotLookedUpTwice() = runTest {
        val id = UUID.randomUUID()
        val calls = Calls()
        val device = device(client(calls, lookup = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(id))), Memory(), this)
        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl))

        try {
            device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl))
            fail("A second lookup went out")
        } catch (error: DeliveryApiError.DuplicateTracking) {
            assertEquals(id, error.parcelId)
        }
        assertEquals(1, calls.all.size)
    }

    @Test
    fun aSpentAllowanceIsToldApart() = runTest {
        val device = device(client(Calls()), Memory(), this)
        try {
            device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl))
            fail("The lookup went through")
        } catch (error: Exception) {
            assertTrue(error is DeviceLookupsSpent)
        }
        assertTrue(device.isEmpty)
    }

    @Test
    fun onlyStaleParcelsAreReadAgainAndAForgottenOneLeaves() = runTest {
        val moving = UUID.randomUUID()
        val arrived = UUID.randomUUID()
        val gone = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Moving", moving, date(now)),
            parcel("22222222222B", "Arrived", arrived, date(now), pkg(arrived, stage = TrackingStage.Delivered)),
            parcel("22222222222C", "Gone", gone, date(now)),
        )
        val calls = Calls()
        val answers = mapOf(
            "22222222222A" to PublicParcelResponse(link = link("22222222222A"), parcel = pkg(moving, stage = TrackingStage.OutForDelivery)),
        )
        val device = device(client(calls, read = { answers[it] }), storage, this, now = { now })

        device.refresh()
        assertEquals("Fresh answers are not asked for again", emptyList<String>(), calls.all)

        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        device.refresh()
        // A parcel that arrived rests for hours; the one the service forgot leaves the list.
        assertEquals(listOf("read 22222222222A k", "read 22222222222C k"), calls.all)
        assertEquals(listOf("Moving", "Arrived"), device.list().map { it.label })
        assertEquals(TrackingStage.OutForDelivery, device.list().first().currentStage)
        assertNull(storage.keys["22222222222C"])
    }

    @Test
    fun aParcelWaitingForItsCarrierIsAskedAboutAtOnce() = runTest {
        val id = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", id, date(now), pkg(id, stage = null, sync = SyncStatus.Pending)))
        val calls = Calls()
        val answered = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(id))
        val device = device(client(calls, read = { answered }), storage, this, now = { now })
        assertTrue(device.isWaiting)

        now = now.plusSeconds(2)
        device.refresh()

        assertEquals(listOf("read 22222222222A k"), calls.all)
        assertFalse(device.isWaiting)
    }

    @Test
    fun troubleKeepsWhatTheDeviceHas() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        // Date.distantPast.
        storage.parcels = listOf(parcel("22222222222A", "Kept", id, -63_114_076_800.0))
        val device = device(client(Calls(), read = { throw IOException("The Internet connection appears to be offline.") }), storage, this)

        try {
            device.refresh()
            fail("The trouble was swallowed")
        } catch (error: Exception) {
            assertTrue(error is IOException)
        }

        assertEquals(listOf("Kept"), device.list().map { it.label })
        assertEquals(1, storage.keys.size)
    }

    @Test
    fun namesAndTheArchiveStayOnTheDeviceAndForgettingTellsTheService() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", id))
        val calls = Calls()
        val device = device(client(calls), storage, this)

        assertEquals("Moon lamp", device.rename(id = id, label = "Moon lamp").label)
        assertNotNull(device.setArchived(id = id, archived = true).archivedAt)
        assertTrue(device.isArchived(linkId = "22222222222A"))
        assertNull(device.setArchived(id = id, archived = false).archivedAt)
        assertEquals("Neither a name nor the archive is the service's to know", emptyList<String>(), calls.all)

        device.forget(id = id)
        assertEquals(listOf("forget 22222222222A"), calls.all)
        assertTrue(device.isEmpty)
        assertTrue(storage.parcels.isEmpty())
        assertTrue(storage.keys.isEmpty())
    }

    @Test
    fun theLastFivePostcodesAreRememberedNewestFirst() {
        val defaults = MemoryDefaults()
        val memory = PostcodeMemory(defaults)
        assertEquals(emptyList<GivenPostcode>(), memory.entries)

        memory.remember("75001", CarrierId.Dpd)
        memory.remember(" 8000 ", CarrierId.GlsCh)
        memory.remember("75001", CarrierId.MondialRelay)
        memory.remember("  ", CarrierId.Dpd)
        assertEquals(listOf(given(CarrierId.MondialRelay, "75001"), given(CarrierId.GlsCh, "8000"), given(CarrierId.Dpd, "75001")), memory.entries)

        // Given again, a pair moves to the front rather than coming twice.
        memory.remember("75001", CarrierId.Dpd)
        assertEquals(listOf(given(CarrierId.Dpd, "75001"), given(CarrierId.MondialRelay, "75001"), given(CarrierId.GlsCh, "8000")), memory.entries)

        memory.remember("1012 AB", CarrierId.Dpd)
        memory.remember("3000", CarrierId.GlsCh)
        memory.remember("75002", CarrierId.Heppner)
        val kept = listOf(
            given(CarrierId.Heppner, "75002"), given(CarrierId.GlsCh, "3000"), given(CarrierId.Dpd, "1012 AB"),
            given(CarrierId.Dpd, "75001"), given(CarrierId.MondialRelay, "75001"),
        )
        assertEquals("The oldest leaves", kept, memory.entries)
        assertEquals("Kept on the device", kept, PostcodeMemory(defaults).entries)

        // Forgetting a postcode forgets it for every carrier, however its spaces were written.
        memory.forget("75001")
        memory.forget("1012ab")
        assertEquals(listOf(given(CarrierId.Heppner, "75002"), given(CarrierId.GlsCh, "3000")), memory.entries)
        memory.forget("75002")
        memory.forget("3000")
        assertEquals(emptyList<GivenPostcode>(), memory.entries)
        assertTrue("Nothing is left behind", defaults.values.isEmpty())
    }

    @Test
    fun aGuestLookupRemembersItsPostcodeApartFromTheParcel() = runTest {
        val memory = PostcodeMemory(MemoryDefaults())
        val id = UUID.randomUUID()
        val storage = Memory()
        val calls = Calls()
        val found = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(id, carrier = CarrierId.Dpd))
        val device = device(client(calls, lookup = found), storage, this, postcodes = memory)

        val parcel = device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dpd, dpdPostcode = "75001 "))

        assertEquals(listOf<String?>("75001 "), calls.lookupPostcodes)
        assertEquals(listOf(given(CarrierId.Dpd, "75001")), memory.entries)
        assertNull(parcel.dpdPostcode)
        assertNull(storage.parcels.first().parcel.dpdPostcode)

        // A carrier changed is a new lookup, and its postcode comes first.
        device.changeCarrier(id = id, carrier = CarrierId.MondialRelay, trackingUrl = null, dpdPostcode = "75002")
        assertEquals(listOf(given(CarrierId.MondialRelay, "75002"), given(CarrierId.Dpd, "75001")), memory.entries)
        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL999", carrier = CarrierId.Heppner))
        assertEquals("A lookup without a postcode leaves the memory alone", 2, memory.entries.size)

        val failed = device(client(Calls()), Memory(), this, postcodes = memory)
        runCatching { failed.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.GlsCh, dpdPostcode = "8000")) }
        assertEquals("Only a lookup that went through is remembered", 2, memory.entries.size)
    }

    // Whether a lookup had a postcode

    /** A DPD parcel its carrier has answered for: DPD shows more with the delivery postcode. */
    private fun checkedDpd(id: UUID): PublicPackage =
        pkg(id, number = "06080000000002", carrier = CarrierId.Dpd).copy(lastSyncedAt = "2026-10-02T08:00:00Z")

    @Test
    fun aLookupWithAPostcodeNotesOnlyThatItHadOneAndAsksForNone() = runTest {
        val catalog = CarrierCatalog(cacheFile = null)
        val id = UUID.randomUUID()
        val storage = Memory()
        val found = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = checkedDpd(id))
        val device = device(client(Calls(), lookup = found), storage, this, postcodes = PostcodeMemory(MemoryDefaults()))

        val parcel = device.add(CreatePackageRequest(trackingNumber = "06080000000002", carrier = CarrierId.Dpd, dpdPostcode = " QX7 7QX "))

        assertEquals(listOf<Boolean?>(true), storage.parcels.map { it.postcodeGiven })
        assertEquals(true, device.postcodeGiven(id))
        // Yes or no only: the postcode stays off the parcel and its row.
        assertNull(parcel.dpdPostcode)
        assertFalse(DeviceParcelFiles.encoder.encode(parcels, storage.parcels).decodeToString().contains("QX7"))
        assertTrue("The parcel alone can't tell", parcel.showsMoreWithPostcode(catalog))
        assertFalse(parcel.showsMoreWithPostcode(catalog, postcodeGiven = true))
    }

    @Test
    fun aLookupWithoutAPostcodeStillAsksForOneItsCarrierShowsMoreWith() = runTest {
        val catalog = CarrierCatalog(cacheFile = null)
        for (postcode in listOf(null, " ")) {
            val id = UUID.randomUUID()
            val storage = Memory()
            val found = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = checkedDpd(id))
            val device = device(client(Calls(), lookup = found), storage, this, postcodes = PostcodeMemory(MemoryDefaults()))

            val parcel = device.add(
                CreatePackageRequest(trackingNumber = "06080000000002", carrier = CarrierId.Dpd, dpdPostcode = postcode),
            )

            assertEquals(postcode.toString(), listOf<Boolean?>(false), storage.parcels.map { it.postcodeGiven })
            assertTrue(postcode.toString(), parcel.showsMoreWithPostcode(catalog, postcodeGiven = device.postcodeGiven(id) == true))
        }
    }

    @Test
    fun changingTheCarrierWithoutAPostcodeNotesThatTheNewLookupHadNone() = runTest {
        val catalog = CarrierCatalog(cacheFile = null)
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Moon lamp", previousId, pkg = pkg(previousId, number = "06080000000002", carrier = CarrierId.GlsCh))
                .copy(postcodeGiven = true),
        )
        val calls = Calls()
        val found = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = checkedDpd(nextId))
        val device = device(client(calls, lookup = found), storage, this)

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)

        assertEquals(nextId, parcel.id)
        assertEquals(listOf<String?>(null), calls.lookupPostcodes)
        assertEquals(listOf("22222222222B"), storage.parcels.map { it.linkId })
        assertEquals(listOf<Boolean?>(false), storage.parcels.map { it.postcodeGiven })
        assertEquals(false, device.postcodeGiven(nextId))
        assertTrue(parcel.showsMoreWithPostcode(catalog, postcodeGiven = device.postcodeGiven(nextId) == true))
    }

    @Test
    fun anEntryKeptBeforeLookupsNotedTheirPostcodeStillAsksForOne() = runTest {
        val catalog = CarrierCatalog(cacheFile = null)
        val id = UUID.randomUUID()
        val noted = parcel("22222222222A", "Moon lamp", id, date(Instant.ofEpochSecond(1_800_000_000)), pkg = checkedDpd(id))
            .copy(postcodeGiven = true)
        val row = rows(listOf(noted)).single()
        assertEquals(JsonPrimitive(true), row["postcodeGiven"])
        val older = JsonArray(listOf(JsonObject(row - "postcodeGiven"))).toString().toByteArray()

        val opened = DeviceParcelFiles.opened(older, keys = mapOf("22222222222A" to key))

        assertEquals(listOf("22222222222A"), opened.parcels.map { it.linkId })
        assertEquals(listOf<Boolean?>(null), opened.parcels.map { it.postcodeGiven })
        val storage = Memory()
        storage.parcels = opened.parcels
        storage.keys = opened.keys
        val device = device(client(Calls()), storage, this)
        assertNull(device.postcodeGiven(id))
        // Unknown, as before: the note still shows.
        val parcel = device.list().first()
        assertTrue(parcel.showsMoreWithPostcode(catalog, postcodeGiven = device.postcodeGiven(id) == true))
    }

    @Test
    fun forgettingParcelsKeepsTheirPostcodes() = runTest {
        val memory = PostcodeMemory(MemoryDefaults())
        memory.remember("8000", CarrierId.GlsCh)
        memory.remember("75001", CarrierId.Dpd)
        val first = UUID.randomUUID()
        val second = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", first, pkg = pkg(first, carrier = CarrierId.Dpd)),
            parcel("22222222222B", "", second, pkg = pkg(second, number = "TESTPARCEL999", carrier = CarrierId.GlsCh)),
        )
        val device = device(client(Calls()), storage, this, postcodes = memory)

        device.forget(id = first)
        assertEquals(listOf(given(CarrierId.Dpd, "75001"), given(CarrierId.GlsCh, "8000")), memory.entries)
        device.forgetAll()
        assertTrue(storage.parcels.isEmpty())
        assertEquals(listOf(given(CarrierId.Dpd, "75001"), given(CarrierId.GlsCh, "8000")), memory.entries)
    }

    @Test
    fun onlyALookupWithoutAnAccountIsRememberedAndSigningOutKeepsIt() = runTest {
        val defaults = MemoryDefaults()
        val memory = PostcodeMemory(defaults)
        val user = AuthUser(id = UUID.randomUUID(), email = "test@example.com", isAnonymous = false)
        val signedIn = JsonEncoder.deliveryTracker.encode(
            AuthSession.serializer(),
            AuthSession(
                accessToken = "access", tokenType = "bearer", expiresIn = 3600, expiresAt = Instant.now().epochSecond + 3600,
                refreshToken = "refresh", user = user,
            ),
        ).decodeToString()
        val accountParcel = Parcel(
            id = UUID.randomUUID(), trackingNumber = "TESTPARCEL999", label = "", carrier = CarrierId.GlsCh, createdAt = "2026-10-02T08:00:00Z",
            syncStatus = SyncStatus.Pending, dpdPostcode = "3000", notificationsMuted = false,
        )
        val added = JsonEncoder.deliveryTracker.encode(CreatePackageResponse.serializer(), CreatePackageResponse(accountParcel, emptyList())).decodeToString()
        val transport = RecordingTransport { request ->
            when (request.method to request.path) {
                "POST" to "/auth/v1/verify" -> jsonResponse(200, signedIn)
                "POST" to "/api/packages" -> jsonResponse(201, added)
                else -> jsonResponse(404)
            }
        }
        val id = UUID.randomUUID()
        val found = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(id, carrier = CarrierId.Dpd))
        val answered = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(id, carrier = CarrierId.Dpd))
        val device = device(client(Calls(), lookup = found, read = { answered }), Memory(), backgroundScope, postcodes = memory)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport, defaults = defaults)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults, device = device, deviceNotifications = quietNotifications(),
        )
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)

        store.add(trackingNumber = "TESTPARCEL123789", label = "", carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = " 75001 ")
        assertEquals(listOf(given(CarrierId.Dpd, "75001")), memory.entries)
        assertEquals(listOf(given(CarrierId.Dpd, "75001")), store.givenPostcodes)
        assertTrue(store.postcodeGiven(store.parcels.first { it.id == id }))

        // An account's postcode stays on its parcel, and the account's parcels come first.
        session.verifyCode(email = "test@example.com", code = "123456")
        store.add(trackingNumber = "TESTPARCEL999", label = "", carrier = CarrierId.GlsCh, trackingUrl = null, dpdPostcode = "3000")
        assertEquals(listOf(given(CarrierId.Dpd, "75001")), memory.entries)
        assertEquals(listOf(given(CarrierId.GlsCh, "3000"), given(CarrierId.Dpd, "75001")), store.givenPostcodes)
        assertFalse("An account's parcel holds its own postcode", store.postcodeGiven(accountParcel))

        session.forceSignOut()
        assertEquals(listOf(given(CarrierId.Dpd, "75001")), memory.entries)
        store.forgetPostcode("75001")
        assertEquals(emptyList<GivenPostcode>(), memory.entries)
    }

    /**
     * Add's save on this phone through a browser check the reader closes, as AddParcelView's `save()` takes it (`catch is
     * CancellationError`: saving ends, with no error): the presenter's CancellationException comes up through
     * NativeVerification.send, the device's lookup and the store's add as it is, never as an error with a message, and
     * nothing is added or exchanged.
     */
    @Test
    fun aBrowserCheckClosedEndsAnAddOnThisPhoneAsACancellationWithNoErrorAndNothingAdded() = runTest {
        val transport = RecordingTransport { request ->
            when (request.method to request.path) {
                "GET" to "/api/public/native/verification" -> jsonResponse(200, """{"appAttestAppId":null,"turnstile":true}""")
                "POST" to "/api/public/parcels" -> jsonResponse(403, """{"error":"Please verify"}""", mapOf("X-Lookup-Verification" to "required"))
                else -> jsonResponse(404)
            }
        }
        val presented = mutableListOf<String>()
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { url ->
                presented += url
                throw CancellationException("The browser check was cancelled.")
            },
            emptyMap(), {}, language = { AppLanguage.En },
        )
        val storage = Memory()
        val device = DeviceParcels(
            DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK }), storage,
            PostcodeMemory(MemoryDefaults()), backgroundScope, region = { Locale.UK }, language = { AppLanguage.En },
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        assertTrue(store.isGuest)

        val ended = try {
            store.add(trackingNumber = "TESTPARCEL123789", label = "Desk lamp", carrier = CarrierId.Dhl, trackingUrl = null, dpdPostcode = null)
            null
        } catch (error: CancellationException) {
            error
        }

        assertEquals("The browser check was cancelled.", ended?.message)
        assertEquals(listOf("https://postcodes.test/api/public/native/turnstile?language=en"), presented)
        assertNull(store.errorMessage)
        assertTrue(store.parcels.isEmpty())
        assertTrue(storage.parcels.isEmpty())
        assertFalse(transport.requests.any { it.path == "/api/public/verification" })
        // C-ST30: the lookup waits 30 s for the server, with no cookies, as DeviceParcels.swift's requests.
        val lookup = transport.requests.first { it.method == "POST" && it.path == "/api/public/parcels" }
        assertEquals(30.0, lookup.timeout, 0.0)
        assertFalse(lookup.handlesCookies)
    }

    @Test
    fun aForgottenPostcodeLeavesTheFieldUntilItsDetailsArePreparedAgain() {
        var fill = PostcodeFill()
        assertEquals("75001", fill.offering("75001"))
        val field = "75001"
        fill = fill.fill(field)
        assertTrue(fill.holds(field))
        assertFalse("Typed over, it is the user's own", fill.holds("75002"))
        assertNull(fill.forget("75002"))

        val (forgotten, after) = fill.forget(field) ?: error("The field held it")
        assertEquals("75001", forgotten)
        fill = after
        assertFalse(fill.holds(""))
        assertFalse(fill.holds(field))
        assertNull("No other postcode fills the field straight away", fill.offering("8000"))

        fill = fill.prepare()
        assertEquals("8000", fill.offering("8000"))
    }

    @Test
    fun aFileWithAPostcodeOnItsParcelsStillOpens() {
        val saved = parcel("22222222222A", "Moon lamp", UUID.randomUUID(), date(Instant.ofEpochSecond(1_800_000_000)))
        val file = Json.parseToJsonElement(DeviceParcelFiles.encoder.encode(parcels, listOf(saved)).decodeToString()).jsonArray
        val withPostcode = JsonArray(listOf(JsonObject(file[0].jsonObject + ("postcode" to JsonPrimitive("75001")))))

        val opened = decodeSwiftKeys(parcels, withPostcode.toString().toByteArray())

        assertEquals(listOf(saved), opened)
    }

    @Test
    fun anAccountTakesTheParcelsOverAndTheDeviceLetsGo() = runTest {
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Kept", UUID.randomUUID()),
            UUID.randomUUID().let { parcel("22222222222B", "", it, pkg = pkg(it, number = "TESTPARCEL999")) },
        )
        val device = device(client(Calls()), storage, this)
        assertEquals(listOf(ClaimParcelLink(id = "22222222222A", key = key, label = "Kept"), ClaimParcelLink(id = "22222222222B", key = key)), device.claims)

        device.release(setOf("22222222222A"))

        assertEquals(listOf("22222222222B"), device.claims.map { it.id })
        assertEquals(listOf("22222222222B"), storage.keys.keys.sorted())
    }

    /**
     * An account with room for `room` more parcels. Each claim is answered for the links it sends, in their order: kept
     * while there is room, `quota` once there is none.
     */
    private class AccountRoom(room: Int) {
        private var left = room
        private val sent = mutableListOf<List<String>>()

        var room: Int
            get() = synchronized(this) { left }
            set(value) = synchronized(this) { left = value }
        val claims: List<List<String>> get() = synchronized(this) { sent.toList() }

        fun answer(request: ClaimParcelsRequest): ClaimParcelsResponse = synchronized(this) {
            sent += request.links.map { it.id }
            ClaimParcelsResponse(
                request.links.map { link ->
                    if (left > 0) {
                        left -= 1
                        ClaimParcelResult(id = link.id, outcome = ClaimParcelResultOutcome.Kept, packageId = UUID.randomUUID())
                    } else {
                        ClaimParcelResult(id = link.id, outcome = ClaimParcelResultOutcome.Quota)
                    }
                },
            )
        }
    }

    /** This phone following `links` as its own, and a sign-in into `account`, whose list is empty. */
    private class SigningIn(val transport: RecordingTransport, val device: DeviceParcels, val defaults: MemoryDefaults, val persistence: MemorySessionPersistence)

    private fun signingIn(links: List<String>, account: AccountRoom, scope: CoroutineScope): SigningIn {
        val user = AuthUser(id = UUID.randomUUID(), email = "test@example.com", isAnonymous = false)
        val signedIn = JsonEncoder.deliveryTracker.encode(
            AuthSession.serializer(),
            AuthSession(
                accessToken = "access", tokenType = "bearer", expiresIn = 3600, expiresAt = Instant.now().epochSecond + 3600,
                refreshToken = "refresh", user = user,
            ),
        ).decodeToString()
        val list = JsonEncoder.deliveryTracker.encode(PackageListResponse.serializer(), PackageListResponse(emptyList())).decodeToString()
        val transport = RecordingTransport { request ->
            when (request.method to request.path) {
                "POST" to "/auth/v1/verify" -> jsonResponse(200, signedIn)
                "POST" to "/api/packages/claim" -> {
                    val claim = ApiJson.decodeFromString(ClaimParcelsRequest.serializer(), request.body!!.decodeToString())
                    jsonResponse(200, ApiJson.encodeToString(ClaimParcelsResponse.serializer(), account.answer(claim)))
                }
                "GET" to "/api/packages" -> jsonResponse(200, list)
                else -> jsonResponse(404)
            }
        }
        val storage = Memory()
        storage.keys = links.associateWith { key }
        storage.parcels = links.mapIndexed { index, link ->
            UUID.randomUUID().let { parcel(link, "", it, pkg = pkg(it, number = "TESTPARCEL12300$index")) }
        }
        return SigningIn(transport, device(client(Calls()), storage, scope), MemoryDefaults(), MemorySessionPersistence())
    }

    private fun SigningIn.session(scope: CoroutineScope) =
        testSessionStore(postcodeConfiguration, scope, persistence = persistence, transport = transport, defaults = defaults)

    private fun SigningIn.store(session: SessionStore, scope: CoroutineScope) = testParcelStore(
        postcodeConfiguration, session, transport, scope, defaults, device = device, deviceNotifications = quietNotifications(),
    ).apply {
        setDeliveryWidgetEnabled(false)
        setDeliveryLiveActivitiesEnabled(false)
    }

    @Test
    fun aParcelAFullAccountRefusesStaysHereAndTheListIsToldOnceASignIn() = runTest {
        val account = AccountRoom(1)
        val phone = signingIn(listOf("22222222222A", "22222222222B", "22222222222C"), account, backgroundScope)
        val session = phone.session(backgroundScope)
        session.showWelcome()
        val store = phone.store(session, backgroundScope)

        // Signing in brings this phone's parcels along: the account takes one, and the two it has no room for stay.
        session.verifyCode(email = "test@example.com", code = "123456")
        store.start()
        assertEquals(listOf(listOf("22222222222A", "22222222222B", "22222222222C")), account.claims)
        assertEquals(listOf("22222222222B", "22222222222C"), phone.device.claims.map { it.id })
        assertEquals("Nothing of this phone's shows among the account's parcels", emptyList<Parcel>(), store.parcels)
        assertEquals(2, store.leftOnDevice?.count)
        // The list says it, and lets the news go.
        store.leftOnDevice = null

        // Room for one more: the next start brings it, and the one that stays is news.
        account.room = 1
        store.start()
        assertEquals(listOf("22222222222B", "22222222222C"), account.claims.last())
        assertEquals(listOf("22222222222C"), phone.device.claims.map { it.id })
        assertEquals(1, store.leftOnDevice?.count)
        store.leftOnDevice = null

        // The next sign-in is told again.
        session.forceSignOut()
        assertNull(store.leftOnDevice)
        session.verifyCode(email = "test@example.com", code = "123456")
        store.start()
        assertEquals(listOf("22222222222C"), phone.device.claims.map { it.id })
        assertEquals(1, store.leftOnDevice?.count)
        store.leftOnDevice = null

        // Once the account takes the last, nothing stays and nothing is said.
        account.room = 1
        store.start()
        assertEquals(emptyList<ClaimParcelLink>(), phone.device.claims)
        assertNull(store.leftOnDevice)
        session.forceSignOut()
    }

    @Test
    fun aRefreshOrARelaunchWhileTheAccountIsStillFullSaysNothingAgain() = runTest {
        val account = AccountRoom(0)
        val phone = signingIn(listOf("22222222222A"), account, backgroundScope)
        val session = phone.session(backgroundScope)
        session.showWelcome()
        val store = phone.store(session, backgroundScope)
        session.verifyCode(email = "test@example.com", code = "123456")
        store.start()
        assertEquals(1, store.leftOnDevice?.count)
        store.leftOnDevice = null

        // A refresh reads the account's list and brings nothing along.
        store.load(showSpinner = false)
        assertEquals(1, account.claims.size)
        assertNull(store.leftOnDevice)
        // The list starting again, as an invitation comes or goes, asks again and hears the same.
        store.start()
        assertEquals(2, account.claims.size)
        assertEquals(listOf("22222222222A"), phone.device.claims.map { it.id })
        assertNull(store.leftOnDevice)

        // A relaunch reopens the account and asks again: still nothing new.
        val reopened = phone.session(backgroundScope)
        val relaunched = phone.store(reopened, backgroundScope)
        reopened.bootstrap()
        assertEquals(session.user?.id, reopened.user?.id)
        relaunched.start()
        assertEquals(3, account.claims.size)
        assertEquals(listOf("22222222222A"), phone.device.claims.map { it.id })
        assertNull(relaunched.leftOnDevice)
        reopened.forceSignOut()
    }

    @Test
    fun aLinkIsSharedUntilItsSharingIsStopped() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", id))
        val calls = Calls()
        val shown = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(id))
        val device = device(client(calls, read = { shown }), storage, this)

        val current = device.share(id = id)
        assertEquals("22222222222A", current?.id)
        val changed = device.setShare(id = id, showNumber = true, gift = false)
        assertEquals(true, changed?.showNumber)
        val stopped = device.setShare(id = id, showNumber = false, gift = false, shared = false)
        assertNull(stopped)
        assertEquals(listOf("read 22222222222A k", "update 22222222222A shared=true", "update 22222222222A shared=false"), calls.all)
    }

    // Following a link

    @Test
    fun aParcelFollowedThroughSomeoneElsesLinkNeedsNoKeyAndIsForgottenHereAlone() = runTest {
        val id = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        val calls = Calls()
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id))
        val device = device(client(calls), storage, this, now = { now })

        val followed = device.follow(shown, key = null, label = "Books for Ana")
        assertEquals(id, followed.id)
        assertTrue(followed.added)
        assertFalse("Followed once", device.follow(shown, key = null, label = "Again").added)
        assertEquals(listOf("Books for Ana"), device.list().map { it.label })
        assertTrue(storage.keys.isEmpty())
        assertEquals(listOf(true), storage.parcels.map { it.isViewer })
        assertEquals("33333333333A", device.viewerLink(id = id))
        assertEquals(id, device.parcelId(linkId = "33333333333A"))
        // An account may keep it as the link allows, with no key.
        assertEquals(listOf(ClaimParcelLink(id = "33333333333A", label = "Books for Ana")), device.claims)
        assertThrows { device.follow(PublicParcelResponse(link = reader("not-a-link"), parcel = pkg(UUID.randomUUID())), key = null, label = null) }

        // After a restart it is read as anyone with the link may, and the link says whether an account may keep it.
        val moved = PublicParcelResponse(link = reader("33333333333A", canKeep = false), parcel = pkg(id, stage = TrackingStage.OutForDelivery))
        val again = device(client(calls, read = { moved }), storage, this, now = { now })
        assertEquals(listOf("Books for Ana"), again.list().map { it.label })
        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        again.refresh()
        assertEquals(listOf("read 33333333333A -"), calls.all)
        assertEquals(TrackingStage.OutForDelivery, again.list().first().currentStage)
        assertEquals("Its link no longer lets an account keep it", emptyList<ClaimParcelLink>(), again.claims)
        again.rename(id = id, label = "Ana's books")
        assertThrows { again.changeCarrier(id = id, carrier = CarrierId.Ups, trackingUrl = null, dpdPostcode = null) }

        // Forgotten here alone: the link is not this phone's to forget.
        again.forget(id = id)
        assertEquals(listOf("read 33333333333A -"), calls.all)
        assertTrue(again.isEmpty)
        assertTrue(storage.parcels.isEmpty())
    }

    @Test
    fun onlyParcelsOfThisIPhonesOwnNeedTheirKeysOnFile() {
        val own = parcel("22222222222A", "", UUID.randomUUID())
        val lost = parcel("22222222222B", "", UUID.randomUUID())
        val followed = parcel("33333333333A", "", UUID.randomUUID()).copy(viewer = true)
        val loaded = DeviceParcelFiles.matched(listOf(own, lost, followed), mapOf("22222222222A" to key, "33333333333A" to key, "22222222222C" to key))
        assertEquals(listOf("22222222222A", "33333333333A"), loaded.parcels.map { it.linkId })
        // A key beside someone else's parcel opens nothing; one beside none reads its parcel again.
        assertEquals(mapOf("22222222222A" to key, "22222222222C" to key), loaded.keys)
        assertEquals(listOf(followed), DeviceParcelFiles.matched(listOf(followed), emptyMap()).parcels)

        // A file written before parcels could be followed this way still opens, every parcel its own.
        val file = DeviceParcelFiles.encoder.encode(parcels, listOf(own))
        assertFalse(file.decodeToString().contains("viewer"))
        assertEquals(listOf(false), decodeSwiftKeys(parcels, file).map { it.isViewer })
        val saved = decodeSwiftKeys(parcels, DeviceParcelFiles.encoder.encode(parcels, listOf(followed)))
        assertEquals(listOf(followed), saved)
    }

    /** The keychain item holding `keys`, as DeviceParcelFiles writes it. */
    private fun savedKeys(keys: Map<String, String>) = MemorySessionPersistence().apply {
        save(JsonEncoder.deliveryTracker.encode(MapSerializer(String.serializer(), String.serializer()), keys))
    }

    /** The rows of `saved` as the file holds them, for a test to spoil. */
    private fun rows(saved: List<DeviceParcel>): List<JsonObject> =
        Json.parseToJsonElement(DeviceParcelFiles.encoder.encode(parcels, saved).decodeToString()).jsonArray.map { it.jsonObject }

    /** The row with its parcel changed by `change`. */
    private fun JsonObject.withParcel(change: (JsonObject) -> Map<String, JsonElement>): JsonObject =
        JsonObject(this + ("parcel" to JsonObject(change(getValue("parcel").jsonObject))))

    @Test
    fun theSavedListIsReadRowByRowAndARowLeftOutIsReadAgainThroughItsKey() = runTest {
        val lamp = UUID.randomUUID()
        val books = UUID.randomUUID()
        val shoes = UUID.randomUUID()
        val saved = rows(listOf(parcel("22222222222A", "Lamp", lamp), parcel("22222222222B", "Books", books), parcel("22222222222C", "Shoes", shoes)))
        // The middle row is one this build can't read: its date is text.
        val file = JsonArray(listOf(saved[0], JsonObject(saved[1] + ("checkedAt" to JsonPrimitive("yesterday"))), saved[2]))
        val keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key)
        val logged = mutableListOf<String>()
        val storage = DeviceParcelFiles(savedKeys(keys), MemoryFile(file.toString().toByteArray()), MemorySessionPersistence(), log = { logged += it })

        val loaded = storage.load()
        assertEquals(listOf("22222222222A", "22222222222C"), loaded.parcels.map { it.linkId })
        assertEquals(keys, loaded.keys)
        assertEquals(1, logged.size)
        assertFalse("The log names no link", logged.single().contains("22222222222"))

        val calls = Calls()
        val device = device(client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(books)) }), storage, this)
        assertEquals(listOf("Lamp", "Shoes"), device.list().map { it.label })
        // Its key goes to an account like the others, before it is read again.
        assertEquals(listOf("22222222222A", "22222222222C", "22222222222B"), device.claims.map { it.id })
        device.refresh()
        // Read again as the lookup it was, without the name it had here. The service is asked to forget nothing.
        assertEquals(listOf("read 22222222222B k"), calls.all)
        assertEquals(listOf("Lamp", "Shoes", ""), device.list().map { it.label })
        assertEquals(books, device.parcelId(linkId = "22222222222B"))
        assertFalse(device.isViewer(linkId = "22222222222B"))
        assertEquals(keys, storage.load().keys)
        assertEquals(listOf(lamp, shoes, books), storage.load().parcels.map { it.parcel.id })
    }

    @Test
    fun aFileThatIsNoListKeepsItsKeysAndTheirParcelsAreReadAgain() = runTest {
        val keys = mapOf("22222222222C" to key, "22222222222D" to key)
        val unreadable = DeviceParcelFiles.opened("""{"linkID":""".toByteArray(), keys = keys)
        assertTrue(unreadable.parcels.isEmpty())
        assertEquals(keys, unreadable.keys)
        assertEquals("No file, no parcels to read again", emptyMap<String, String>(), DeviceParcelFiles.opened(null, keys = keys).keys)

        val storage = Memory()
        storage.keys = unreadable.keys
        val ids = mapOf("22222222222C" to UUID.randomUUID(), "22222222222D" to UUID.randomUUID())
        val calls = Calls()
        val read = { linkId: String -> PublicParcelResponse(link = link(linkId), parcel = pkg(ids.getValue(linkId), number = "TESTPARCEL$linkId")) }
        val device = device(client(calls, read = read), storage, this)
        assertTrue(device.isEmpty)
        // A parcel's own page reads that parcel alone.
        device.refresh(only = UUID.randomUUID())
        assertEquals(emptyList<String>(), calls.all)
        device.refresh()

        assertEquals(listOf("read 22222222222C k", "read 22222222222D k"), calls.all)
        assertEquals(listOf(ids["22222222222C"], ids["22222222222D"]), device.list().map { it.id })
        assertEquals(listOf(ClaimParcelLink(id = "22222222222C", key = key), ClaimParcelLink(id = "22222222222D", key = key)), device.claims)
        assertEquals(keys, storage.keys)
        assertEquals(listOf(false, false), storage.parcels.map { it.isViewer })
    }

    @Test
    fun aKeyWhoseLinkLeadsNowhereIsLetGoAndOneTheServiceCouldNotAnswerForStays() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222C" to key, "22222222222D" to key, "22222222222E" to key)
        val calls = Calls()
        var offline = true
        val device = device(
            client(calls, read = { linkId ->
                when (linkId) {
                    // The service no longer has it (404), or it is no longer shared (410).
                    "22222222222C" -> null
                    // The service no longer takes the key as the owner's.
                    "22222222222E" -> PublicParcelResponse(link = reader(linkId), parcel = pkg(UUID.randomUUID()))
                    else -> if (offline) throw IOException("The Internet connection appears to be offline.") else PublicParcelResponse(link = link(linkId), parcel = pkg(id))
                }
            }),
            storage, this,
        )

        // The read that could not be answered ends the round, which fails: the rest wait, as on the iPhone.
        assertThrows { device.refresh() }
        assertEquals(listOf("read 22222222222C k", "read 22222222222D k"), calls.all)
        assertEquals(mapOf("22222222222D" to key, "22222222222E" to key), storage.keys)
        assertTrue(device.isEmpty)

        offline = false
        device.refresh()
        assertEquals(listOf("read 22222222222D k", "read 22222222222E k"), calls.all.drop(2))
        assertEquals(listOf(id), device.list().map { it.id })
        assertEquals(mapOf("22222222222D" to key), storage.keys)
        assertFalse("Only an explicit forget asks the service to forget", calls.all.any { it.startsWith("forget") })
    }

    @Test
    fun aRefreshWithOnlyAnUnreadableCopyLeftFailsWhenItsParcelCannotBeReadAgain() = runTest {
        // A copy this build could not read: its key is kept, its parcel isn't listed.
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key)
        val calls = Calls()
        val device = device(client(calls, read = { throw IOException("The Internet connection appears to be offline.") }), storage, this)
        assertTrue(device.isEmpty)

        var thrown: Exception? = null
        try {
            device.refresh(all = true)
        } catch (error: IOException) {
            thrown = error
        }
        assertEquals("The Internet connection appears to be offline.", thrown?.message)
        assertEquals(listOf("read 22222222222B k"), calls.all)
        // Nothing is let go for it: the next refresh asks again.
        assertEquals(mapOf("22222222222B" to key), storage.keys)
        assertTrue(device.isEmpty)
    }

    @Test
    fun aParcelForgottenOfflineLeavesAtOnceAndTheServiceIsToldOnALaterRefresh() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Lamp", id))
        val calls = Calls()
        var offline = true
        val flaky = client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(id)) }).replacing(forget = { linkId, _ ->
            calls.add("forget $linkId")
            if (offline) throw IOException("The Internet connection appears to be offline.")
        })
        val device = device(flaky, storage, this)

        device.forget(id = id)
        assertTrue(device.isEmpty)
        assertEquals(emptyList<ClaimParcelLink>(), device.claims)
        assertTrue(storage.parcels.isEmpty())
        assertTrue(storage.keys.isEmpty())
        assertEquals(mapOf("22222222222A" to key), storage.pendingForgets)

        // Still offline: the service is asked again, and the parcel is not read back.
        device.refresh(all = true)
        assertEquals(listOf("forget 22222222222A", "forget 22222222222A"), calls.all)
        assertTrue(device.isEmpty)

        // Stopped while it was being forgotten, its entry and key still saved beside it: forgetting wins.
        storage.parcels = listOf(parcel("22222222222A", "Lamp", id))
        storage.keys = mapOf("22222222222A" to key)
        offline = false
        val relaunched = device(flaky, storage, this)
        assertTrue(relaunched.isEmpty)
        relaunched.refresh(all = true)
        relaunched.refresh(all = true)
        assertEquals(listOf("forget 22222222222A", "forget 22222222222A", "forget 22222222222A"), calls.all)
        assertTrue(relaunched.isEmpty)
        assertTrue(storage.pendingForgets.isEmpty())
        assertTrue(storage.keys.isEmpty())
    }

    @Test
    fun aForgetCutShortIsStillToldOnALaterRefresh() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Lamp", id))
        val calls = Calls()
        val leaving = client(calls).replacing(forget = { linkId, _ ->
            calls.add("forget $linkId")
            throw CancellationException("Left")
        })
        try {
            device(leaving, storage, this).forget(id = id)
            fail("The forget went on as though nothing happened")
        } catch (_: CancellationException) {
        }
        // On file as still to be forgotten before the service was asked.
        assertTrue(storage.parcels.isEmpty())
        assertTrue(storage.keys.isEmpty())
        assertEquals(mapOf("22222222222A" to key), storage.pendingForgets)

        device(client(calls), storage, this).refresh()
        assertEquals(listOf("forget 22222222222A", "forget 22222222222A"), calls.all)
        assertTrue(storage.pendingForgets.isEmpty())
    }

    @Test
    fun theLinkASecondLookupReplacesIsForgottenThroughTheSameQueue() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Lamp", id, pkg = pkg(id, carrier = CarrierId.Dpd)))
        val calls = Calls()
        var offline = true
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(id, carrier = CarrierId.Dpd)))
                .replacing(forget = { linkId, _ ->
                    calls.add("forget $linkId")
                    if (offline) throw IOException("The Internet connection appears to be offline.")
                }),
            storage, this,
        )

        device.changeCarrier(id = id, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = "75001")
        assertEquals(mapOf("22222222222A" to key), storage.pendingForgets)
        advanceUntilIdle()
        assertEquals(listOf("lookup TESTPARCEL123789 dpd", "forget 22222222222A"), calls.all)
        assertEquals(mapOf("22222222222A" to key), storage.pendingForgets)
        assertEquals(mapOf("22222222222B" to key), storage.keys)

        offline = false
        device.refresh()
        assertEquals("forget 22222222222A", calls.all.last())
        assertTrue(storage.pendingForgets.isEmpty())
        assertEquals(listOf("22222222222B"), storage.parcels.map { it.linkId })
    }

    @Test
    fun forgettingEveryParcelForgetsTheKeysStillToBeReadAgain() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222C" to key)
        storage.parcels = listOf(parcel("22222222222A", "Lamp", id))
        val calls = Calls()
        val device = device(client(calls), storage, this)

        device.forgetAll()
        assertEquals(listOf("forget 22222222222A", "forget 22222222222C"), calls.all)
        assertTrue(device.isEmpty)
        assertEquals(emptyList<ClaimParcelLink>(), device.claims)
        assertTrue(storage.keys.isEmpty())
        assertTrue(storage.pendingForgets.isEmpty())
    }

    @Test
    fun aLinkFollowedAgainBeforeTheServiceWasToldToForgetItStays() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.pendingForgets = mapOf("22222222222A" to key)
        val calls = Calls()
        val device = device(client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(id)) }), storage, this)

        assertTrue(device.follow(PublicParcelResponse(link = link("22222222222A"), parcel = pkg(id)), key = key, label = "Lamp").added)
        assertTrue(storage.pendingForgets.isEmpty())
        device.refresh(all = true)
        assertEquals(listOf("read 22222222222A k"), calls.all)
        assertEquals(listOf("Lamp"), device.list().map { it.label })
    }

    @Test
    fun aParcelSavedBeforeAnUpdateChangedItsModelIsReadAgainThroughItsKey() = runTest {
        val older = UUID.randomUUID()
        val newer = UUID.randomUUID()
        val current = UUID.randomUUID()
        val saved = rows(
            listOf(
                parcel("22222222222A", "Lamp", older), parcel("22222222222B", "Books", newer), parcel("22222222222C", "Shoes", current),
                parcel("33333333333A", "Ana's", UUID.randomUUID()).copy(viewer = true),
            ),
        )
        // Written before the model needed `notificationsMuted`, or with a stage this build does not know.
        val withoutField = saved[0].withParcel { it - "notificationsMuted" }
        val withUnknownStage = saved[1].withParcel { parcel ->
            val event = parcel.getValue("trackingEvents").jsonArray.single().jsonObject
            parcel + ("trackingEvents" to JsonArray(listOf(JsonObject(event + ("stage" to JsonPrimitive("teleported"))))))
        }
        // Someone else's parcel no key reads: left out on this phone alone.
        val theirs = saved[3].withParcel { it - "notificationsMuted" }
        val file = JsonArray(listOf(withoutField, withUnknownStage, saved[2], theirs)).toString().toByteArray()
        val keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key)
        val storage = DeviceParcelFiles(savedKeys(keys), MemoryFile(file), MemorySessionPersistence())
        val calls = Calls()
        val device = device(
            client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(if (linkId == "22222222222A") older else newer)) }),
            storage, this,
        )
        assertEquals(listOf("Shoes"), device.list().map { it.label })

        device.refresh()
        assertEquals(listOf("read 22222222222A k", "read 22222222222B k"), calls.all)
        assertEquals(listOf(current, older, newer), device.list().map { it.id })
        assertEquals(keys, storage.load().keys)
    }

    @Test
    fun aRoundEndingLeavesAnotherUnderWayMarked() = runTest {
        val calls = Calls()
        val gate = AlertGate()
        val held = client(calls, lookup = PublicLookupResponse(link = link("22222222222A"), key = key, parcel = pkg(UUID.randomUUID())))
            .replacing(setAlert = { linkId, _, request ->
                calls.add("alert $linkId ${request.token}")
                gate.pass(request.token)
                DeviceAlertAnswer.On
            })
        val device = device(held, Memory(), this)
        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", label = "", carrier = CarrierId.Dhl))
        val first = launch { device.registerAlerts(token = "old-token", locale = NativePushLocale.En) }
        gate.arrived("old-token")
        val second = launch { device.registerAlerts(token = "new-token", locale = NativePushLocale.En) }
        gate.arrived("new-token")
        gate.release("old-token")
        first.join()
        // The second round is still under way: the same round does not start again beside it.
        device.registerAlerts(token = "new-token", locale = NativePushLocale.En)
        gate.release("new-token")
        second.join()
        assertEquals(listOf("alert 22222222222A old-token", "alert 22222222222A new-token"), calls.all.filter { it.startsWith("alert") })
    }

    @Test
    fun aHandedOverKeyMakesTheParcelThisIPhonesOwn() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        val calls = Calls()
        val device = device(client(calls), storage, this)
        device.follow(PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id)), key = null, label = null)

        // A key the service does not own the link with, or that is no key at all, changes nothing.
        assertFalse(device.follow(PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id)), key = key, label = null).added)
        assertFalse(device.follow(PublicParcelResponse(link = link("33333333333A"), parcel = pkg(id)), key = "short", label = null).added)
        assertTrue(storage.keys.isEmpty())
        assertTrue(device.isViewer(linkId = "33333333333A"))

        val owned = device.follow(PublicParcelResponse(link = link("33333333333A"), parcel = pkg(id)), key = key, label = null)
        assertEquals(id, owned.id)
        assertFalse(owned.added)
        assertEquals(mapOf("33333333333A" to key), storage.keys)
        assertFalse(device.isViewer(linkId = "33333333333A"))
        assertNull(device.viewerLink(id = id))
        assertEquals(listOf(ClaimParcelLink(id = "33333333333A", key = key)), device.claims)

        // A key handed over for a parcel not here yet makes it this phone's own from the start.
        val other = UUID.randomUUID()
        assertTrue(device.follow(PublicParcelResponse(link = link("22222222222B"), parcel = pkg(other, number = "TESTPARCEL999")), key = key, label = "Lamp").added)
        assertEquals(key, storage.keys["22222222222B"])
        device.forget(id = other)
        assertEquals(listOf("forget 22222222222B"), calls.all)
    }

    // Notifications

    @Test
    fun notificationsAreAskedForOnceForEveryParcelOnItsWayWithTheKeyOnlyForThisIPhonesOwn() = runTest {
        val mine = UUID.randomUUID()
        val theirs = UUID.randomUUID()
        val arrived = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222C" to key)
        val followed = parcel("33333333333B", "", theirs, pkg = pkg(theirs, stage = TrackingStage.Registered)).copy(viewer = true)
        storage.parcels = listOf(
            parcel("22222222222A", "", mine),
            followed,
            parcel("22222222222C", "", arrived, pkg = pkg(arrived, stage = TrackingStage.Delivered)),
        )
        val calls = Calls()
        val device = device(client(calls), storage, this)
        assertTrue(device.hasParcelsOnTheirWay)

        device.registerAlerts(token = "token1", locale = NativePushLocale.Fr)
        // Every scan while nothing has moved yet, the steps that matter after; nothing for a parcel that arrived.
        assertEquals(listOf("alert 22222222222A k important token1 fr production fcm", "alert 33333333333B - all token1 fr production fcm"), calls.all)
        assertEquals(listOf("token1", "token1", null), storage.parcels.map { it.alertToken })
        assertEquals(listOf(ParcelAlertPreset.Important, ParcelAlertPreset.All, null), storage.parcels.map { it.alertPreset })

        device.registerAlerts(token = "token1", locale = NativePushLocale.Fr)
        assertEquals("Asked once a launch", 2, calls.all.size)

        // A new token takes the old one's place, and the preset first chosen stays.
        device.registerAlerts(token = "token2", locale = NativePushLocale.Fr)
        assertEquals(
            listOf(
                "alert 22222222222A k important token2 fr production fcm", "unalert 22222222222A token1 fcm",
                "alert 33333333333B - all token2 fr production fcm", "unalert 33333333333B token1 fcm",
            ),
            calls.all.drop(2),
        )
        // Another language asks again.
        device.registerAlerts(token = "token2", locale = NativePushLocale.De)
        assertEquals(8, calls.all.size)

        // A launch asks again, in case the service lost them.
        val relaunched = device(client(calls), storage, this)
        relaunched.registerAlerts(token = "token2", locale = NativePushLocale.De)
        assertEquals(10, calls.all.size)
        assertFalse(calls.all.contains("unalert 22222222222A token2 fcm"))

        // Forgetting a parcel, or letting an account take it over, stops its notifications here.
        relaunched.forget(id = theirs)
        assertEquals("unalert 33333333333B token2 fcm", calls.all.last())
        relaunched.release(setOf("22222222222A"))
        advanceUntilIdle()
        assertEquals("unalert 22222222222A token2 fcm", calls.all.last())
    }

    @Test
    fun someoneElsesParcelLeavesTheListAtOnceAndItsNotificationsStopWhenTheServiceCanBeTold() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        val calls = Calls()
        val shown = PublicParcelResponse(link = reader("33333333333B"), parcel = pkg(id))
        val offline = device(client(calls, unalert = { throw DeliveryApiError.ServiceFailed(503) }), storage, this)
        offline.follow(shown, key = null, label = "Books for Ana")
        offline.registerAlerts(token = "token1", locale = NativePushLocale.Fr)

        // The service can't turn its notifications off yet: the parcel leaves the list all the same,
        // an account can't take it over, and each refresh asks again.
        offline.forget(id = id)
        assertTrue(offline.list().isEmpty())
        assertEquals(emptyList<ClaimParcelLink>(), offline.claims)
        assertNull(offline.parcelId(linkId = "33333333333B"))
        assertFalse(offline.hasParcelsOnTheirWay)
        offline.refresh(all = true)
        // Followed again meanwhile, it keeps the notifications it still has, and forgetting it asks again.
        assertTrue(offline.follow(shown, key = null, label = "Books for Ana").added)
        offline.forget(id = id)
        assertEquals(
            listOf(
                "alert 33333333333B - important token1 fr production fcm",
                "unalert 33333333333B token1 fcm", "unalert 33333333333B token1 fcm", "unalert 33333333333B token1 fcm",
            ),
            calls.all,
        )

        // After a restart it is still gone, and the next refresh turns them off for good.
        val relaunched = device(client(calls), storage, this)
        assertTrue(relaunched.list().isEmpty())
        relaunched.refresh()
        relaunched.refresh()
        assertEquals(5, calls.all.size)
        assertEquals("unalert 33333333333B token1 fcm", calls.all.last())
        assertTrue(storage.parcels.isEmpty())
    }

    @Test
    fun aRoundOfNotificationRequestsEndsQuietlyWhenTheServiceWantsAPause() = runTest {
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", UUID.randomUUID()),
            UUID.randomUUID().let { parcel("22222222222B", "", it, pkg = pkg(it, number = "TESTPARCEL999")) },
        )
        val calls = Calls()
        val off = device(client(calls, alert = { DeviceAlertAnswer.Unavailable }), storage, this)
        off.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals("A server without notifications for phones is asked once", 1, calls.all.size)
        val busy = device(client(calls, alert = { throw DeliveryApiError.RateLimited(60.0) }), storage, this)
        busy.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(2, calls.all.size)
        assertEquals(emptyList<String>(), storage.parcels.mapNotNull { it.alertToken })

        // A link with no room, or no longer shared, is left alone until the next launch.
        val full = device(client(calls, alert = { if (it == "22222222222A") DeviceAlertAnswer.Full else DeviceAlertAnswer.Stopped }), storage, this)
        full.registerAlerts(token = "token1", locale = NativePushLocale.En)
        full.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(4, calls.all.size)
        assertEquals(emptyList<String>(), storage.parcels.mapNotNull { it.alertToken })
    }

    @Test
    fun aParcelsNotificationsTellEveryScanUntilItsCarrierHasNews() {
        fun preset(stage: TrackingStage?): ParcelAlertPreset = DeviceParcels.alertPreset(Parcel(shared = pkg(UUID.randomUUID(), stage = stage)))
        assertEquals(ParcelAlertPreset.All, preset(null))
        assertEquals(ParcelAlertPreset.All, preset(TrackingStage.Pending))
        assertEquals(ParcelAlertPreset.All, preset(TrackingStage.Registered))
        assertEquals(ParcelAlertPreset.Important, preset(TrackingStage.Accepted))
        assertEquals(ParcelAlertPreset.Important, preset(TrackingStage.InTransit))
    }

    @Test
    fun signedOutACheckWithOnlyAnUnreadableCopyLeftFailsOfflineRatherThanSayingNothingChanged() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key)
        val calls = Calls()
        val device = device(client(calls, read = { throw IOException("The Internet connection appears to be offline.") }), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val localizer = Localizer(AppLanguage.En)
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = localizer, device = device, deviceNotifications = quietNotifications(),
        )
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        assertTrue(store.isGuest)
        assertTrue(store.parcels.isEmpty())

        val start = store.refreshAll()

        assertEquals(
            ParcelStore.RefreshStart.Completed(ParcelStore.RefreshResult.Failed(localizer.errorMessage(IOException("offline")))),
            start,
        )
        assertTrue(calls.all.contains("read 22222222222B k"))
        assertEquals(mapOf("22222222222B" to key), storage.keys)
        // Settings offers Forget all for it, and counts it.
        assertEquals(1, store.unlistedDeviceParcelsFlow.value)
    }

    @Test
    fun signedOutForgetAllForgetsTheParcelsStillToBeReadAgainAndTheCountClears() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key, "22222222222C" to key)
        val calls = Calls()
        val device = device(client(calls, read = { throw IOException("The Internet connection appears to be offline.") }), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        // Opening the app loads the list, its copies unreadable offline.
        store.load()
        assertTrue(store.parcels.isEmpty())
        assertEquals(2, store.unlistedDeviceParcelsFlow.value)

        store.forgetDeviceParcels()

        assertEquals(0, store.unlistedDeviceParcelsFlow.value)
        assertTrue(storage.keys.isEmpty())
        assertEquals(listOf("forget 22222222222B", "forget 22222222222C"), calls.all.filter { it.startsWith("forget") })
    }

    @Test
    fun followingALinkWithoutAnAccountAsksForNotificationsOnce() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id, stage = TrackingStage.Registered))
        val calls = Calls()
        val device = device(client(calls, read = { shown }), Memory(), backgroundScope)
        var status = NotificationAuthorizationStatus.NotDetermined
        val asked = mutableListOf<String>()
        val notifications = DeviceNotificationAccess(
            status = { asked += "status"; status },
            request = { asked += "request"; status = NotificationAuthorizationStatus.Authorized },
            register = { asked += "register" },
            token = { "token1" },
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        val localizer = Localizer(AppLanguage.En)
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = localizer, device = device, deviceNotifications = notifications,
        )
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        assertNull(store.followedHere(shown))

        val claim = store.follow(ParcelLinkRoute(id = "33333333333A", name = "Books"), shown)

        assertEquals(ParcelLinkClaim.Added(id), claim)
        assertTrue("The sign-in screen gives way to the parcels on this iPhone", session.isGuest)
        assertEquals(listOf("Books"), store.parcels.map { it.label })
        assertEquals(id, store.followedHere(shown))
        assertEquals(id, store.followedHere(linkId = "33333333333A"))
        assertEquals("33333333333A", store.viewerLink(store.parcels.first()))
        val alert = "alert 33333333333A - all token1 ${NativePushLocale(localizer.language).rawValue} production fcm"
        runCurrent()
        assertEquals(listOf(alert), calls.all.filter { it.startsWith("alert") })
        assertEquals(listOf("status", "request", "status", "register"), asked)
        val again = store.follow(ParcelLinkRoute(id = "33333333333A"), shown)
        assertEquals(ParcelLinkClaim.Already(id), again)
    }

    @Test
    fun theSampleJourneyAddsOneScanAtATime() {
        val now = Instant.ofEpochSecond(1_800_000_000)
        val stages = (1..FirstOpenJourney.steps).map { FirstOpenJourney.parcel(step = it, name = "New sneakers", now = now).currentStage }
        assertEquals(
            listOf(TrackingStage.Accepted, TrackingStage.InTransit, TrackingStage.InTransit, TrackingStage.OutForDelivery, TrackingStage.Delivered),
            stages,
        )
        val first = FirstOpenJourney.parcel(step = 1, name = "New sneakers", now = now)
        val last = FirstOpenJourney.parcel(step = 9, name = "New sneakers", now = now)
        assertEquals("The same parcel all along, so its map flies rather than starts over", first.id, last.id)
        assertEquals(FirstOpenJourney.steps, last.trackingEvents.size)
        assertEquals("CN", last.stampOrigin)
    }

    /** The iPhone's `Localizer()` on an English device. */
    @Test
    fun theSampleOpensOnItsWholeStory() {
        val localizer = Localizer(AppLanguage.En)
        val delivered = FirstOpenJourney.delivered(localizer = localizer, now = Instant.ofEpochSecond(1_800_000_000))
        // The page a tap opens shows the same parcel the picture follows, picked up.
        assertEquals(FirstOpenJourney.parcel(step = 1, name = "", now = Instant.now()).id, delivered.id)
        assertEquals(FirstOpenJourney.parcelId, delivered.id)
        assertEquals(TrackingStage.Delivered, delivered.currentStage)
        assertEquals("123EXAMPLE", delivered.trackingNumber)
        assertEquals("Kiosk im Hauptbahnhof", PickupPoint(delivered.carrierData?.pickupPoint)?.name)
        assertNotNull(delivered.carrierData?.senderName)
        assertEquals("CN", delivered.stampOrigin)
        // Every scan has its own words, and every placed one a country the history flags.
        for (event in delivered.trackingEvents) {
            assertFalse(event.description.isEmpty())
            assertFalse(event.description, event.description.startsWith("sample."))
            event.location?.let { assertNotNull(it, TrackingLocation.place(it).country) }
        }
        assertEquals(setOf("CN", "DE", "CH"), delivered.trackingEvents.mapNotNull { it.location }.mapNotNull { TrackingLocation.place(it).country }.toSet())
    }

    /**
     * Android's own, in a build without Firebase Cloud Messaging: a phone without pushes never asks a guest for
     * notifications, at launch, on adding a parcel or on following a link, and so never reads the permission or
     * registers.
     */
    @Test
    fun aPhoneWithoutPushNeverAsksAGuestForNotifications() = runTest {
        val storage = Memory().apply {
            parcels = listOf(parcel("22222222222A", "Desk lamp", UUID.randomUUID()))
            keys = mapOf("22222222222A" to key)
        }
        val added = UUID.randomUUID()
        val found = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(added, number = "TESTPARCEL456"))
        val followed = UUID.randomUUID()
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(followed, stage = TrackingStage.Registered))
        val device = device(client(Calls(), lookup = found, read = { shown }), storage, backgroundScope)
        val notifications = StubPushNotifications(granted = true, currentDeviceToken = "token1", available = false)
        val transport = RecordingTransport { jsonResponse(404) }
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, notifications = notifications)
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)

        store.start()
        store.add(trackingNumber = "TESTPARCEL456", label = "", carrier = CarrierId.Dhl, trackingUrl = null, dpdPostcode = null)
        store.follow(ParcelLinkRoute(id = "33333333333A", name = "Books"), shown)
        runCurrent()

        assertEquals(3, store.parcels.size)
        assertEquals(emptyList<String>(), notifications.calls)
    }

    // Android alone: the stores the app gives DeviceParcelFiles, which the iPhone's tests can't reach (its Keychain is
    // real) and which can fail where the iPhone's `try?` reads nothing; and a cancellation ends the caller rather than
    // being let go.

    @Test
    fun storesThatCannotBeReadOpenEmptyRatherThanStopTheApp() = runTest {
        val lostKeystore = object : SessionPersistence {
            override fun save(data: ByteArray) = throw java.security.GeneralSecurityException("Keystore key lost")
            override fun load(): ByteArray = throw java.security.GeneralSecurityException("Keystore key lost")
            override fun delete() = throw IllegalStateException("Keystore key lost")
        }
        val brokenFile = object : DeviceParcelFile {
            override fun read(): ByteArray = throw IOException("Unreadable")
            override fun write(data: ByteArray) = throw IOException("Unwritable")
            override fun delete() = throw IOException("Undeletable")
        }
        val storage = DeviceParcelFiles(lostKeystore, brokenFile, lostKeystore)
        assertEquals(DeviceParcelStorage.Contents(emptyList(), emptyMap()), storage.load())
        storage.save(emptyList(), emptyMap())
        storage.save(listOf(parcel("22222222222A", "", UUID.randomUUID())), mapOf("22222222222A" to key))
        assertTrue(device(client(Calls()), storage, this).isEmpty)
    }

    @Test
    fun aCancelledRoundOfNotificationRequestsEndsInItsCancellation() = runTest {
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()))
        val calls = Calls()
        var cancelled = true
        val device = device(client(calls, alert = { if (cancelled) throw CancellationException("Left") else DeviceAlertAnswer.On }), storage, this)
        try {
            device.registerAlerts(token = "token1", locale = NativePushLocale.En)
            fail("The round went on as though nothing happened")
        } catch (_: CancellationException) {
        }
        // The round is over: the next one asks again.
        cancelled = false
        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(2, calls.all.size)
        assertEquals(listOf("token1"), storage.parcels.mapNotNull { it.alertToken })
    }

    @Test
    fun aParcelWhoseForgettingIsCancelledStaysOnThePhone() = runTest {
        val storage = Memory()
        val id = UUID.randomUUID()
        storage.parcels = listOf(parcel("33333333333A", "", id).copy(viewer = true, alertToken = "token1"))
        val base = client(Calls())
        val leaving = DeviceParcelClient(
            lookup = base.lookup, read = base.read, forget = base.forget, update = base.update, detect = base.detect,
            removeAlert = { _, _ -> throw CancellationException("Left") },
        )
        val device = device(leaving, storage, this)
        try {
            device.forget(id = id)
            fail("The parcel was forgotten though its forgetting was cancelled")
        } catch (_: CancellationException) {
        }
        assertEquals(listOf(id), device.list().map { it.id })
        assertEquals(1, storage.parcels.size)
    }

    @Test
    fun keysWithoutTheirParcelsAreSavedWithAnEmptyList() {
        val keys = mapOf("22222222222C" to key)
        val keychain = MemorySessionPersistence()
        keychain.save(JsonEncoder.deliveryTracker.encode(MapSerializer(String.serializer(), String.serializer()), keys))
        assertEquals("No file, no parcels to read again", DeviceParcelStorage.Contents(emptyList(), emptyMap()), DeviceParcelFiles(keychain, MemoryFile(null), MemorySessionPersistence()).load())

        val unreadable = "not JSON".toByteArray()
        val file = MemoryFile(unreadable)
        val forgets = MemorySessionPersistence()
        val storage = DeviceParcelFiles(keychain, file, forgets)
        assertEquals(DeviceParcelStorage.Contents(emptyList(), keys), storage.load())
        // The list is written as it is, empty: a parcel forgotten meanwhile stays forgotten, as on the iPhone.
        storage.save(emptyList(), keys)
        assertEquals("[]", file.data?.decodeToString())
        assertEquals(DeviceParcelStorage.Contents(emptyList(), keys), storage.load())

        // The links still to be forgotten are kept in a store of their own, whatever becomes of the file.
        storage.save(emptyList(), keys, pendingForgets = mapOf("22222222222D" to key))
        assertNotNull(forgets.data)
        assertEquals(mapOf("22222222222D" to key), DeviceParcelFiles(keychain, MemoryFile(null), forgets).load().pendingForgets)

        // Once their parcels are read again or gone, and the service told, nothing is left.
        storage.save(emptyList(), emptyMap())
        assertNull(file.data)
        assertNull(keychain.data)
        assertNull(forgets.data)
    }

    @Test
    fun aCancelledRefreshEndsInItsCancellationAndLeavesWhatItHadToTell() = runTest {
        val storage = Memory()
        storage.pendingForgets = mapOf("22222222222C" to key)
        storage.parcels = listOf(parcel("33333333333B", "", UUID.randomUUID()).copy(viewer = true, alertToken = "token1", forgotten = true))
        val calls = Calls()
        var unalertLeaves = true
        var forgetLeaves = true
        val leaving = client(calls, unalert = { if (unalertLeaves) throw CancellationException("Left") }).replacing(forget = { linkId, _ ->
            calls.add("forget $linkId")
            if (forgetLeaves) throw CancellationException("Left")
        })
        val device = device(leaving, storage, this)
        suspend fun cancelledRefresh() {
            try {
                device.refresh()
                fail("The refresh went on as though nothing happened")
            } catch (_: CancellationException) {
            }
        }

        cancelledRefresh()
        assertEquals(listOf("unalert 33333333333B token1 fcm"), calls.all)
        unalertLeaves = false
        cancelledRefresh()
        assertEquals(listOf("unalert 33333333333B token1 fcm", "unalert 33333333333B token1 fcm", "forget 22222222222C"), calls.all)
        assertTrue(storage.parcels.isEmpty())
        assertEquals(mapOf("22222222222C" to key), storage.pendingForgets)
        forgetLeaves = false
        device.refresh()
        assertEquals("forget 22222222222C", calls.all.last())
        assertTrue(storage.pendingForgets.isEmpty())
    }

    @Test
    fun aRefreshCancelledAmongPendingForgetsKeepsTheLinksItForgotForgotten() = runTest {
        val storage = Memory()
        storage.pendingForgets = mapOf("22222222222C" to key, "22222222222D" to key)
        val calls = Calls()
        val leaving = client(calls).replacing(forget = { linkId, _ ->
            calls.add("forget $linkId")
            if (linkId == "22222222222D") throw CancellationException("Left")
        })
        try {
            device(leaving, storage, this).refresh()
            fail("The refresh went on as though nothing happened")
        } catch (_: CancellationException) {
        }
        assertEquals(listOf("forget 22222222222C", "forget 22222222222D"), calls.all)
        assertEquals(mapOf("22222222222D" to key), storage.pendingForgets)
    }

    @Test
    fun keysAndTokensStayOutOfWhatIsLogged() {
        val entry = parcel("22222222222A", "", UUID.randomUUID()).copy(alertToken = "token1")
        val written = "$entry ${DeviceParcelStorage.Contents(listOf(entry), mapOf("22222222222A" to key))}"
        assertFalse(written, written.contains(key) || written.contains("token1") || written.contains("22222222222A"))
    }

    /**
     * C-D14, `beginDevicePolling`: while a parcel on this phone waits for its carrier's first answer, its link is read
     * again soon, 2·1.5^n s apart for six rounds, then after the list's 30 s, and the soon rounds start again; behind
     * the app the rounds pass without a read.
     */
    @Test
    fun aParcelWaitingForItsCarrierIsReadAgainSoonThenAfterThirtySeconds() = runTest {
        val start = Instant.parse("2026-10-02T08:00:00Z")
        val clock = { start.plusMillis(testScheduler.currentTime) }
        val id = UUID.randomUUID()
        val waiting = pkg(id, stage = null, sync = SyncStatus.Pending)
        val reads = mutableListOf<Long>()
        val storage = Memory().apply {
            parcels = listOf(parcel("22222222222A", "Desk lamp", id, at = date(start), pkg = waiting))
            keys = mapOf("22222222222A" to key)
        }
        val client = client(Calls(), read = {
            reads += testScheduler.currentTime
            PublicParcelResponse(link = link("22222222222A"), parcel = waiting)
        })
        val device = device(client, storage, backgroundScope, now = clock)
        val transport = RecordingTransport()
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        assertTrue(store.isGuest)
        store.start()
        runCurrent()
        assertEquals(emptyList<Long>(), reads)
        advanceTimeBy(73_563)
        runCurrent()
        assertEquals(listOf(2_000L, 5_000L, 9_500L, 16_250L, 26_375L, 41_563L, 71_563L, 73_563L), reads)
        store.setActive(false)
        advanceTimeBy(60_000)
        runCurrent()
        assertEquals(8, reads.size)
    }

    /** A DeviceParcelFile in memory. */
    private class MemoryFile(var data: ByteArray?) : DeviceParcelFile {
        override fun read(): ByteArray? = data

        override fun write(data: ByteArray) {
            this.data = data
        }

        override fun delete() {
            data = null
        }
    }

    private companion object {
        val parcels = ListSerializer(DeviceParcel.serializer())
    }
}

/**
 * Holds the first notification request on each token until it is released; later ones go through. The test's one
 * thread stands for the Swift actor.
 */
private class AlertGate {
    private val seen = mutableSetOf<String>()
    private val held = mutableMapOf<String, CompletableDeferred<Unit>>()
    private val arrivals = mutableMapOf<String, CompletableDeferred<Unit>>()

    suspend fun pass(token: String) {
        if (!seen.add(token)) return
        val release = CompletableDeferred<Unit>()
        held[token] = release
        arrival(token).complete(Unit)
        release.await()
    }

    /** Returns once the first request on the token is held. */
    suspend fun arrived(token: String) = arrival(token).await()

    fun release(token: String) {
        held.remove(token)?.complete(Unit)
    }

    private fun arrival(token: String): CompletableDeferred<Unit> = arrivals.getOrPut(token) { CompletableDeferred() }
}
