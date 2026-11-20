package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionRequest
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.ChangePackageCarrierRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResult
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResultOutcome
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsResponse
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageResponse
import com.plhery.peekdeliverytracker.core.api.DeleteParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.NativePushTransport
import com.plhery.peekdeliverytracker.core.api.PackageListResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.ParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkCarrierRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLinkInputNeeded
import com.plhery.peekdeliverytracker.core.api.ParcelLinkInputNeededField
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkPostcodeRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.PublicLookupRequest
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.PushSubscriptionKeys
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
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
        check: ((String) -> PublicParcelResponse?)? = null,
        alert: (String) -> DeviceAlertAnswer = { DeviceAlertAnswer.On },
        unalert: (String) -> Unit = {},
        move: ((String, ParcelLinkCarrierRequest) -> PublicParcelResponse?)? = null,
        postcode: ((String, ParcelLinkPostcodeRequest) -> PublicParcelResponse?)? = null,
        forget: (String) -> Unit = {},
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
        check = check?.let { answer ->
            { linkId, key ->
                calls.add("check $linkId ${key.take(1)}")
                answer(linkId)
            }
        },
        forget = { linkId, _ ->
            calls.add("forget $linkId")
            forget(linkId)
        },
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
        changeCarrier = move?.let { answer ->
            { linkId, key, change ->
                calls.add("carrier $linkId ${key.take(1)} ${change.carrier.rawValue}")
                calls.lookupCountry(change.lookupCountryHint)
                calls.lookupPostcode(change.dpdPostcode)
                calls.lookupLanguage(change.language)
                calls.lookupPhoneDigits(change.phoneDigits)
                answer(linkId, change)
            }
        },
        addPostcode = postcode?.let { answer ->
            { linkId, key, request ->
                calls.add("postcode $linkId ${key.take(1)} ${request.carrier.rawValue} ${request.postcode} ${request.postcodeCountry ?: "-"}")
                answer(linkId, request)
            }
        },
    )

    /** Swift's `var client = …` with one of its calls set anew: the same client, the calls given replaced. */
    private fun DeviceParcelClient.replacing(
        forget: suspend (String, String) -> Unit = this.forget,
        setAlert: suspend (String, String?, ParcelAppAlertRequest) -> DeviceAlertAnswer = this.setAlert,
        read: suspend (String, String?) -> PublicParcelResponse? = this.read,
        removeAlert: suspend (String, DeleteParcelAppAlertRequest) -> Unit = this.removeAlert,
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

    /** A parcel muted on its page and looked up again with another carrier stays muted, also for a round meanwhile. */
    @Test
    fun aMutedParcelLookedUpWithAnotherCarrierStaysMuted() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId).copy(muted = true))
        val calls = Calls()
        val base = client(calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(nextId, carrier = CarrierId.Dpd)))
        val forgetting = CompletableDeferred<Unit>()
        val device = device(base.replacing(forget = { linkId, k -> forgetting.await(); base.forget(linkId, k) }), storage, this)

        val change = async { device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null) }
        runCurrent()
        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        forgetting.complete(Unit)
        assertTrue(change.await().notificationsMuted)
        assertFalse(calls.all.toString(), calls.all.any { it.startsWith("alert") })
        assertEquals(listOf(true), storage.parcels.map { it.muted })
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

    /**
     * The website's carrier change (3e35f22cd): the parcel's own link moves to the new carrier and keeps its address, its
     * key and this phone's notifications, so nothing is looked up afresh, forgotten or signed up again.
     */
    @Test
    fun aNewCarrierMovesTheParcelsOwnLinkWithItsNotifications() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId).copy(alertToken = "token1", alertPreset = ParcelAlertPreset.All))
        val calls = Calls()
        val memory = PostcodeMemory(MemoryDefaults())
        val device = device(
            client(calls, move = { linkId, _ -> PublicParcelResponse(link = link(linkId), parcel = pkg(nextId, carrier = CarrierId.Dpd)) }),
            storage, this, postcodes = memory,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = "75002")
        advanceUntilIdle()

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(CarrierId.Dpd, parcel.carrier)
        assertEquals(previousId, parcel.carrierData?.originalPackageId)
        assertEquals(listOf("carrier 22222222222A k dpd"), calls.all)
        assertEquals(listOf<String?>("CH"), calls.lookupCountries)
        assertEquals(listOf<String?>("75002"), calls.lookupPostcodes)
        assertEquals(listOf<String?>(AppLanguage.En.rawValue), calls.lookupLanguages)
        assertEquals(mapOf("22222222222A" to key), storage.keys)
        val kept = storage.parcels.single()
        assertEquals("22222222222A", kept.linkId)
        assertEquals("token1", kept.alertToken)
        assertEquals(ParcelAlertPreset.All, kept.alertPreset)
        assertEquals(listOf("75002"), memory.entries.map { it.postcode })
    }

    /** A link the service no longer has for this phone's key: the number is looked up afresh, as before. */
    @Test
    fun aLinkTheServiceNoLongerHasForItsKeyIsLookedUpAfresh() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId))
        val calls = Calls()
        val device = device(
            client(
                calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(nextId, carrier = CarrierId.Dpd)),
                move = { _, _ -> null },
            ),
            storage, this,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(listOf("carrier 22222222222A k dpd", "lookup TESTPARCEL123789 dpd", "forget 22222222222A"), calls.all)
        assertEquals(mapOf("22222222222B" to key), storage.keys)
    }

    @Test
    fun movingALinkToTheNumberAndCarrierOfAnotherParcelIsRefused() = runTest {
        val changing = UUID.randomUUID()
        val other = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", changing, pkg = pkg(changing, carrier = CarrierId.Dhl)),
            parcel("22222222222B", "", other, pkg = pkg(other, carrier = CarrierId.Dpd)),
        )
        val calls = Calls()
        val device = device(client(calls, move = { linkId, _ -> PublicParcelResponse(link = link(linkId), parcel = pkg(other, carrier = CarrierId.Dpd)) }), storage, this)

        try {
            device.changeCarrier(id = changing, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)
            fail("The link moved onto another parcel's")
        } catch (error: DeliveryApiError.DuplicateTracking) {
            assertEquals(other, error.parcelId)
        }
        assertEquals(emptyList<String>(), calls.all)
    }

    /** The move asks the carrier as a lookup does: the same verification, the key, and the service's own 404 as no link. */
    @Test
    fun theServiceIsAskedToMoveTheLinkWithItsKeyAndTheLookupsVerification() = runTest {
        val moved = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(UUID.randomUUID(), carrier = CarrierId.Dpd))
        var answer = jsonResponse(200, JsonEncoder.deliveryTracker.encode(PublicParcelResponse.serializer(), moved).decodeToString())
        val transport = RecordingTransport { answer }
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val move = DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK }).changeCarrier!!

        val response = move("22222222222A", key, ParcelLinkCarrierRequest(carrier = CarrierId.Dpd, lookupCountryHint = "GB"))

        assertEquals(moved.parcel.id, response?.parcel?.id)
        val request = transport.requests.single()
        assertEquals("PATCH", request.method)
        assertEquals("/api/public/parcels/22222222222A/carrier", request.path)
        assertEquals(key, request.headers["X-Parcel-Key"])
        assertEquals("1", request.headers["X-Native-Verification"])
        val body = Json.parseToJsonElement(request.body!!.decodeToString()).jsonObject
        assertEquals(setOf("carrier", "lookupCountryHint"), body.keys)

        answer = jsonResponse(404, """{"error":"Parcel unavailable","code":"parcel_unavailable"}""")
        assertNull(move("22222222222A", key, ParcelLinkCarrierRequest(carrier = CarrierId.Dpd)))
        answer = jsonResponse(429, """{"error":"Daily lookups used up","scope":"daily"}""")
        try {
            move("22222222222A", key, ParcelLinkCarrierRequest(carrier = CarrierId.Dpd))
            fail("Expected the day's lookups spent")
        } catch (error: DeviceLookupsSpent) {
            assertEquals(DeviceLookupsSpent(), error)
        }
    }

    /** The owner's link asking for the delivery postcode [carrier] needs. */
    private fun asking(id: String, carrier: CarrierId = CarrierId.Dpd) =
        link(id).copy(inputNeeded = ParcelLinkInputNeeded(carrier = carrier, field = ParcelLinkInputNeededField.DpdPostcode))

    /**
     * The postcode a lookup's link asks its owner for shows on the parcel, as an account's carrier asks, and stays with
     * it on the phone; given alone for that carrier, it goes to the link (`POST …/postcode`), not as a move.
     */
    @Test
    fun thePostcodeALinkAsksForShowsOnTheParcelAndGoesToTheLink() = runTest {
        val id = UUID.randomUUID()
        val filedId = UUID.randomUUID()
        val storage = Memory()
        val calls = Calls()
        val postcodes = PostcodeMemory(MemoryDefaults())
        val filed = { linkId: String -> PublicParcelResponse(link = link(linkId), parcel = pkg(filedId, carrier = CarrierId.Dpd)) }
        val device = device(
            client(
                calls, lookup = PublicLookupResponse(link = asking("22222222222A"), key = key, parcel = pkg(id)),
                postcode = { linkId, _ -> filed(linkId) }, move = { linkId, _ -> filed(linkId) },
            ),
            storage, this, postcodes = postcodes,
        )
        device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", label = "Moon lamp"))
        assertEquals(CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode), device.list().single().inputNeededPrompt)
        assertEquals(CarrierId.Dpd, storage.parcels.single().parcel.inputNeeded?.carrier)

        val parcel = device.changeCarrier(id = id, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = " 75002 ", dpdPostcodeCountry = "FR")

        assertEquals(listOf("lookup TESTPARCEL123789 -", "postcode 22222222222A k dpd 75002 FR"), calls.all)
        assertEquals(filedId, parcel.id)
        assertEquals(CarrierId.Dpd, parcel.carrier)
        assertEquals("Moon lamp", parcel.label)
        assertEquals(id, parcel.carrierData?.originalPackageId)
        assertNull(parcel.inputNeededPrompt)
        assertEquals(true, storage.parcels.single().postcodeGiven)
        assertEquals(listOf(GivenPostcode(CarrierId.Dpd, "75002", "FR")), postcodes.entries)
    }

    /** Another carrier than the one that asks, or no postcode, moves the link as before; a viewer's link asks nothing. */
    @Test
    fun anotherCarrierOrNoPostcodeMovesTheLinkAndAViewersLinkAsksNothing() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(
            DeviceParcel(linkId = "22222222222A", label = "", archivedAt = null, parcel = Parcel(shared = pkg(id), link = asking("22222222222A")), checkedAt = date()),
        )
        val calls = Calls()
        val device = device(
            client(
                calls,
                postcode = { linkId, _ -> PublicParcelResponse(link = link(linkId), parcel = pkg(id, carrier = CarrierId.Dpd)) },
                move = { linkId, change -> PublicParcelResponse(link = asking(linkId), parcel = pkg(id, carrier = change.carrier)) },
            ),
            storage, this,
        )

        device.changeCarrier(id = id, carrier = CarrierId.GlsDe, trackingUrl = null, dpdPostcode = "75002")
        device.changeCarrier(id = id, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = " ")

        assertEquals(listOf("carrier 22222222222A k gls-de", "carrier 22222222222A k dpd"), calls.all)
        val viewer = reader("22222222222B").copy(inputNeeded = ParcelLinkInputNeeded(CarrierId.Dpd, ParcelLinkInputNeededField.DpdPostcode))
        assertNull(Parcel(shared = pkg(id), link = viewer).inputNeeded)
    }

    /**
     * The postcode goes to the link with its key and without the lookup's verification; nothing asked any more reads
     * the link, and each refusal says why in the website's words.
     */
    @Test
    fun thePostcodeGoesToTheLinkWithItsKeyAndEachRefusalSaysWhy() = runTest {
        val filed = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(UUID.randomUUID(), carrier = CarrierId.Dpd))
        val body = JsonEncoder.deliveryTracker.encode(PublicParcelResponse.serializer(), filed).decodeToString()
        var answer = jsonResponse(200, body)
        val transport = RecordingTransport { request -> if (request.method == "GET") jsonResponse(200, body) else answer }
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val give = DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK }).addPostcode!!
        val request = ParcelLinkPostcodeRequest(carrier = CarrierId.Dpd, postcode = "75002", postcodeCountry = "FR")
        val english = Localizer(AppLanguage.En)

        assertEquals(filed.parcel.id, give("22222222222A", key, request)?.parcel?.id)
        val sent = transport.requests.single()
        assertEquals("POST", sent.method)
        assertEquals("/api/public/parcels/22222222222A/postcode", sent.path)
        assertEquals(key, sent.headers["X-Parcel-Key"])
        assertNull(sent.headers["X-Native-Verification"])
        assertEquals(setOf("carrier", "postcode", "postcodeCountry"), Json.parseToJsonElement(sent.body!!.decodeToString()).jsonObject.keys)

        answer = jsonResponse(409, """{"error":"No postcode is asked for this parcel"}""")
        assertEquals(filed.parcel.id, give("22222222222A", key, request)?.parcel?.id)
        assertEquals(listOf("POST", "GET"), transport.requests.takeLast(2).map { it.method })

        answer = jsonResponse(404, """{"error":"Parcel unavailable","code":"parcel_unavailable"}""")
        assertNull(give("22222222222A", key, request))

        answer = jsonResponse(422, """{"error":"The carrier does not have the parcel for this postcode"}""")
        val refused = thrown<DevicePostcodeRefusal.Refused> { give("22222222222A", key, request) }
        assertEquals("DPD Switzerland doesn’t have this parcel for that postcode. Check it and try again.", english.errorMessage(refused))

        answer = jsonResponse(429, """{"error":"Too many postcodes were tried for this parcel"}""", mapOf("Retry-After" to "120"))
        val before = AppClock.now()
        val tries = thrown<DevicePostcodeRefusal.Tries> { give("22222222222A", key, request) }
        assertFalse(tries.until.isBefore(before.plusSeconds(120)) || tries.until.isAfter(AppClock.now().plusSeconds(120)))
        assertEquals("Too many postcodes were tried. Try again after ${english.clockTime(tries.until)}.", english.errorMessage(tries))

        answer = jsonResponse(503, """{"error":"The carrier could not be asked. Try again later."}""")
        val unreachable = thrown<DevicePostcodeRefusal.Unreachable> { give("22222222222A", key, request) }
        assertEquals("DPD Switzerland couldn’t be asked just now. Try again in a moment.", english.errorMessage(unreachable))
        // The service itself down is no answer of the carrier's.
        answer = jsonResponse(503)
        thrown<ServerDown> { give("22222222222A", key, request) }
    }

    private suspend inline fun <reified T : Throwable> thrown(block: suspend () -> Unit): T {
        try {
            block()
        } catch (error: Throwable) {
            if (error is T) return error
            throw error
        }
        throw AssertionError("Expected ${T::class.simpleName}")
    }

    /** The owner's read by hand is the link's read with `refresh=true` and the key, and the service's 404 as no link. */
    @Test
    fun theOwnersReadByHandAsksTheServiceForACheck() = runTest {
        val read = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(UUID.randomUUID()), checkQueued = true)
        var answer = jsonResponse(200, JsonEncoder.deliveryTracker.encode(PublicParcelResponse.serializer(), read).decodeToString())
        val transport = RecordingTransport { answer }
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val check = DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK }).check!!

        assertEquals(true, check("22222222222A", key)?.checkQueued)
        val request = transport.requests.single()
        assertEquals("GET", request.method)
        assertEquals("/api/public/parcels/22222222222A", request.path)
        assertEquals("refresh=true", Url.parse(request.url)?.query)
        assertEquals(key, request.headers["X-Parcel-Key"])

        answer = jsonResponse(404, """{"error":"Parcel unavailable","code":"parcel_unavailable"}""")
        assertNull(check("22222222222A", key))
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
    fun onlyStaleParcelsAreReadAgainAndAForgottenOneStaysNoLongerUpdated() = runTest {
        val moving = UUID.randomUUID()
        val arrived = UUID.randomUUID()
        val gone = UUID.randomUUID()
        val held = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key, "22222222222D" to key)
        // A return the server still follows, as the scans after it carry the parcel back, is read as one on its way.
        val heldReturn = pkg(held, stage = TrackingStage.Returned).copy(carrierData = PublicPackageCarrierData(returnHeld = true))
        storage.parcels = listOf(
            parcel("22222222222A", "Moving", moving, date(now)),
            parcel("22222222222B", "Arrived", arrived, date(now), pkg(arrived, stage = TrackingStage.Delivered)),
            parcel("22222222222C", "Gone", gone, date(now)),
            parcel("22222222222D", "Held", held, date(now), heldReturn),
        )
        val calls = Calls()
        val answers = mapOf(
            "22222222222A" to PublicParcelResponse(link = link("22222222222A"), parcel = pkg(moving, stage = TrackingStage.OutForDelivery)),
            "22222222222D" to PublicParcelResponse(link = link("22222222222D"), parcel = heldReturn),
        )
        val device = device(client(calls, read = { answers[it] }), storage, this, now = { now })

        device.refresh()
        assertEquals("Fresh answers are not asked for again", emptyList<String>(), calls.all)

        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        device.refresh()
        // A parcel that arrived rests for hours; the one the service forgot stays, as it last was, without its key.
        assertEquals(listOf("read 22222222222A k", "read 22222222222C k", "read 22222222222D k"), calls.all)
        assertEquals(listOf("Moving", "Arrived", "Gone", "Held"), device.list().map { it.label })
        assertEquals(TrackingStage.OutForDelivery, device.list().first().currentStage)
        assertNull(storage.keys["22222222222C"])
        assertTrue(device.isFrozen(gone))
        assertFalse(device.isFrozen(moving))
        assertEquals(date(now), storage.parcels.first { it.linkId == "22222222222C" }.frozen?.let { date(DateParser.date(it)!!) })
    }

    @Test
    fun aRefreshByHandAsksForACheckOfThisPhonesOwnLookupAndReadsItSoonUntilItLands() = runTest {
        val mine = UUID.randomUUID()
        val theirs = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", mine, date(now)),
            parcel("33333333333B", "", theirs, date(now)).copy(viewer = true),
        )
        val calls = Calls()
        var landed: String? = null
        fun answer(linkId: String, queued: Boolean? = null) = if (linkId == "22222222222A") {
            PublicParcelResponse(link = link(linkId), parcel = pkg(mine).copy(lastSyncedAt = landed), checkQueued = queued)
        } else {
            PublicParcelResponse(link = reader(linkId), parcel = pkg(theirs), checkQueued = queued)
        }
        val device = device(client(calls, read = { answer(it) }, check = { answer(it, queued = true) }), storage, this, now = { now })
        assertFalse(device.isWaiting)

        device.refresh(only = mine, check = true)
        assertEquals(listOf("check 22222222222A k"), calls.all)
        assertTrue(device.isWaiting)

        // An answer before the check lands keeps it waited for; the list's next round reads only that one again.
        now = now.plusSeconds(2)
        device.refresh()
        assertEquals("read 22222222222A k", calls.all.last())
        assertTrue(device.isWaiting)

        landed = "2026-11-19T20:00:00Z"
        now = now.plusSeconds(2)
        device.refresh()
        assertFalse(device.isWaiting)
        now = now.plusSeconds(2)
        device.refresh()
        assertEquals(3, calls.all.size)

        // Someone else's parcel reads as usual; a check that never lands is waited for a minute.
        device.refresh(only = theirs, check = true)
        assertEquals("read 33333333333B -", calls.all.last())
        device.refresh(only = mine, check = true)
        assertTrue(device.isWaiting)
        now = now.plusSeconds(DeviceParcels.checkReadFor.toLong())
        assertFalse(device.isWaiting)
        // Asked again by hand once that minute is over, the new check is waited for a minute of its own.
        device.refresh(only = mine, check = true)
        assertTrue(device.isWaiting)
    }

    @Test
    fun aCopyTheServiceForgotIsNeverReadAgainAndOpensAgainWithoutItsKey() = runTest {
        val id = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        val first = DeviceParcel(
            linkId = "22222222222A", label = "Shoes", archivedAt = null, parcel = Parcel(shared = pkg(id)), checkedAt = date(now),
            alertToken = "token", alertPreset = ParcelAlertPreset.Important,
        )
        storage.parcels = listOf(first)
        val calls = Calls()
        val device = device(client(calls), storage, this, now = { now })

        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        device.refresh()
        // Its notifications went with the link: nothing is asked to turn them off, and none is asked for again.
        val kept = storage.parcels.single()
        assertNotNull(kept.frozen)
        assertNull(kept.alertToken)
        assertNull(kept.alertPreset)
        assertEquals(emptyMap<String, String>(), storage.keys)
        assertFalse(device.isWaiting)
        assertEquals(TrackingStage.InTransit, device.list().single().currentStage)

        now = now.plusSeconds(DeviceParcels.fresh.toLong() * 10)
        device.refresh(all = true)
        device.refresh(only = id)
        device.registerAlerts("token-two", NativePushLocale.En)
        assertTrue(device.alertsSettled("token-two", NativePushLocale.En))
        assertEquals(listOf("read 22222222222A k"), calls.all)

        // Opened again, it stays without a key, as it is: no key needs to read it.
        val again = device(client(calls), storage, this, now = { now })
        assertEquals(listOf("Shoes"), again.list().map { it.label })
        assertTrue(again.isFrozen(id))
        assertEquals(emptyList<ClaimParcelLink>(), again.claims)
        assertThrows { again.changeCarrier(id, CarrierId.Ups, null, null) }
        assertThrows { again.share(id) }

        // Removing it asks nothing of the service.
        again.forget(id)
        assertTrue(again.isEmpty)
        assertEquals(emptyMap<String, String>(), storage.pendingForgets)
        assertEquals(listOf("read 22222222222A k"), calls.all)
    }

    @Test
    fun someoneElsesLinkTheServiceNoLongerHasLeaves() = runTest {
        val id = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.parcels = listOf(parcel("22222222222A", "", id, date(now)).copy(viewer = true, canKeep = true))
        val device = device(client(Calls()), storage, this, now = { now })

        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        device.refresh()

        // The 404 doesn't say why the link ended.
        assertTrue(device.isEmpty)
    }

    @Test
    fun aNewLookupOrLinkOfTheNumberTakesTheCopysPlace() = runTest {
        val old = UUID.randomUUID()
        val fresh = UUID.randomUUID()
        val storage = Memory()
        val frozen = parcel("22222222222A", "Shoes", old, pkg = pkg(old, number = "TESTPARCEL123789")).copy(frozen = "2026-11-01T08:00:00Z")
        val other = parcel("22222222222B", "Coat", UUID.randomUUID(), pkg = pkg(UUID.randomUUID(), number = "TESTPARCEL555000"))
            .copy(frozen = "2026-11-01T08:00:00Z")
        storage.parcels = listOf(frozen, other)
        val calls = Calls()
        val lookup = PublicLookupResponse(link = link("22222222222C"), key = key, parcel = pkg(fresh, number = "TESTPARCEL123789"))
        val device = device(client(calls, lookup = lookup), storage, this)

        // The same number and carrier is no duplicate of a copy no longer updated, which leaves the lookup its name.
        val added = device.add(CreatePackageRequest(trackingNumber = "TESTPARCEL123789", carrier = CarrierId.Dhl))
        assertEquals(fresh, added.id)
        assertEquals("Shoes", added.label)
        assertEquals(listOf("Coat", "Shoes"), device.list().map { it.label })
        assertFalse(device.isFrozen(fresh))

        // So does someone else's link to it.
        val shared = PublicParcelResponse(link = reader("22222222222D"), parcel = pkg(UUID.randomUUID(), number = "TESTPARCEL555000"))
        assertTrue(device.follow(shared, null, null).added)
        assertEquals(listOf("Shoes", "Coat"), device.list().map { it.label })
        assertEquals(listOf("22222222222C", "22222222222D"), storage.parcels.map { it.linkId })
    }

    @Test
    fun aLinkToTheSameParcelThatHidesItsNumberTakesTheCopysPlaceAndName() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id).copy(frozen = "2026-11-01T08:00:00Z"))
        val device = device(client(Calls()), storage, this)

        val shared = PublicParcelResponse(link = reader("22222222222D"), parcel = pkg(id).copy(trackingNumber = null))
        assertEquals(DeviceParcels.Followed(id, true), device.follow(shared, null, null))

        assertEquals(listOf("Shoes"), device.list().map { it.label })
        assertFalse(device.isFrozen(id))
        assertEquals(listOf("22222222222D"), storage.parcels.map { it.linkId })
    }

    @Test
    fun aLinkOfAParcelHereAlreadyStillTakesTheCopysPlace() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Shoes", UUID.randomUUID()).copy(frozen = "2026-11-01T08:00:00Z"),
            parcel("22222222222B", "Coat", id),
        )
        val device = device(client(Calls()), storage, this)

        // The copy of the same number goes, on file at once, though the parcel was followed here already.
        val shared = PublicParcelResponse(link = reader("22222222222D"), parcel = pkg(id))
        assertEquals(DeviceParcels.Followed(id, false), device.follow(shared, null, null))
        assertEquals(listOf("22222222222B"), storage.parcels.map { it.linkId })
        assertEquals(listOf("Coat"), device.list().map { it.label })
    }

    @Test
    fun aLostEntryReadAgainTakesTheCopysPlaceAndNameAndKeepsItsKey() = runTest {
        val id = UUID.randomUUID()
        var now = Instant.ofEpochSecond(1_800_000_000)
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id, date(now)).copy(frozen = "2026-11-01T08:00:00Z"))
        val calls = Calls()
        val device = device(
            client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(id)).takeIf { linkId == "22222222222B" } }),
            storage, this, now = { now },
        )

        now = now.plusSeconds(DeviceParcels.fresh.toLong() + 1)
        device.refresh()

        assertEquals(listOf("read 22222222222B k"), calls.all)
        assertEquals(listOf("22222222222B"), storage.parcels.map { it.linkId })
        assertEquals(listOf("Shoes"), device.list().map { it.label })
        assertFalse(device.isFrozen(id))
        assertEquals(mapOf("22222222222B" to key), storage.keys)
    }

    /** iPhone's testAMovedLinkTakesTheForgottenCopysPlaceAndName. */
    @Test
    fun aMovedLinkTakesTheForgottenCopysPlaceAndNameAndItsParcelIsNoNews() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", previousId).copy(seenNewsAt = "2026-09-01T08:00:00Z"),
            // The copy of the same number and the new carrier is no duplicate.
            parcel("22222222222C", "Moon lamp", UUID.randomUUID(), pkg = pkg(UUID.randomUUID(), carrier = CarrierId.Dpd))
                .copy(frozen = "2026-11-01T08:00:00Z"),
        )
        val calls = Calls()
        val device = device(
            client(calls, move = { linkId, _ -> PublicParcelResponse(link = link(linkId), parcel = pkg(nextId, carrier = CarrierId.Dpd)) }),
            storage, this,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)

        assertEquals(nextId, parcel.id)
        assertEquals("Moon lamp", parcel.label)
        // What the other lookup of the number tells so far is no news, as a new lookup's.
        assertFalse(parcel.hasNewUpdate)
        assertEquals(previousId, parcel.carrierData?.originalPackageId)
        assertEquals(listOf("22222222222A"), storage.parcels.map { it.linkId })
        assertEquals(listOf("carrier 22222222222A k dpd"), calls.all)
    }

    @Test
    fun aLinkMovedToAParcelFollowedHereUnderAnotherLinkTakesItsPlace() = runTest {
        val previousId = UUID.randomUUID()
        val nextId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Moon lamp", previousId),
            parcel("22222222222B", "Other", nextId, pkg = pkg(nextId, number = "TESTPARCEL555000")).copy(alertToken = "token2"),
        )
        val calls = Calls()
        val device = device(
            client(calls, move = { linkId, _ -> PublicParcelResponse(link = link(linkId), parcel = pkg(nextId, carrier = CarrierId.Dpd)) }),
            storage, this,
        )

        val parcel = device.changeCarrier(id = previousId, carrier = CarrierId.Dpd, trackingUrl = null, dpdPostcode = null)
        advanceUntilIdle()

        // One parcel is followed here once: the other link is forgotten, with its notifications.
        assertEquals("Moon lamp", parcel.label)
        assertEquals(listOf("22222222222A"), storage.parcels.map { it.linkId })
        assertEquals(mapOf("22222222222A" to key), storage.keys)
        assertEquals(
            listOf("carrier 22222222222A k dpd", "unalert 22222222222B token2 ${NativePushTransport.Fcm.rawValue}", "forget 22222222222B"),
            calls.all,
        )
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
                "GET" to "/api/public/native/verification" -> jsonResponse(200, """{"androidAttestAppId":null,"turnstile":true}""")
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
    private class SigningIn(
        val transport: RecordingTransport,
        val device: DeviceParcels,
        val defaults: MemoryDefaults,
        val persistence: MemorySessionPersistence,
        val storage: Memory,
        val calls: Calls,
    )

    private fun signingIn(
        links: List<String>,
        account: AccountRoom,
        scope: CoroutineScope,
        pendingForgets: Map<String, String> = emptyMap(),
    ): SigningIn {
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
        storage.pendingForgets = pendingForgets
        val calls = Calls()
        return SigningIn(transport, device(client(calls), storage, scope), MemoryDefaults(), MemorySessionPersistence(), storage, calls)
    }

    private fun SigningIn.session(scope: CoroutineScope) =
        testSessionStore(postcodeConfiguration, scope, persistence = persistence, transport = transport, defaults = defaults)

    private fun SigningIn.store(session: SessionStore, scope: CoroutineScope) = testParcelStore(
        postcodeConfiguration, session, transport, scope, defaults, device = device, deviceNotifications = quietNotifications(),
    ).apply {
        setDeliveryLiveActivitiesEnabled(false)
    }

    /**
     * A link of this phone's own forgotten without an account while the service couldn't be told is told at the
     * account's start, not left until a sign-out's first refresh.
     */
    @Test
    fun signedInALinkForgottenOfflineBeforeSigningInIsToldWithoutASignOut() = runTest {
        val phone = signingIn(emptyList(), AccountRoom(0), backgroundScope, pendingForgets = mapOf("22222222222D" to key))
        val session = phone.session(backgroundScope)
        session.showWelcome()
        val store = phone.store(session, backgroundScope)
        runCurrent()
        // Control: signed out, nothing has told it yet.
        assertEquals(emptyList<String>(), phone.calls.all)

        session.verifyCode(email = "test@example.com", code = "123456")
        store.start()

        assertEquals(
            "Signed in, the link forgotten here before signing in waits for a sign-out to reach the service",
            listOf("forget 22222222222D"),
            phone.calls.all,
        )
        assertEquals(emptyMap<String, String>(), phone.storage.pendingForgets)
        session.forceSignOut()
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
    fun recoveryUpgradesViewerWithOwnerLink() = runTest {
        val id = UUID.randomUUID()
        val owner = "22222222222A"
        val viewer = "22222222222B"
        val storage = Memory()
        storage.keys = mapOf(owner to key)
        val owned = PublicParcelResponse(link(owner), pkg(id))
        val parcels = device(client(Calls(), read = { owned }), storage, this)
        parcels.follow(PublicParcelResponse(reader(viewer), pkg(id)), key = null, label = "Lamp")

        parcels.refresh()

        val reopened = device(null, storage, this)
        assertEquals(listOf("Lamp"), reopened.list().map { it.label })
        assertNull(reopened.viewerLink(id))
        assertEquals(listOf(owner), storage.parcels.map { it.linkId })
        assertEquals(mapOf(owner to key), storage.keys)
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

    /**
     * While the server restarts, its proxy answers "404 page not found" as text: the server failing, and nothing on this
     * phone goes. Only the service's own 404, with its JSON error, forgets a parcel and its key.
     */
    @Test
    fun aProxysNotFoundWhileTheServerRestartsForgetsNothing() = runTest {
        var answer = HttpResponse(404, mapOf("Content-Type" to "text/plain; charset=utf-8"), "404 page not found\n".toByteArray())
        val transport = RecordingTransport { answer }
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "Running shoes", UUID.randomUUID(), at = 0.0))
        storage.pendingForgets = mapOf("22222222222C" to key)
        val device = device(DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK }), storage, this)

        try {
            device.refresh()
            fail("Expected the server down")
        } catch (error: ServerDown) {
            assertEquals(ServerDown(), error)
        }
        assertEquals(listOf("Running shoes"), device.list().map { it.label })
        assertEquals("A key went for a proxy's 404", mapOf("22222222222A" to key, "22222222222B" to key), storage.keys)
        assertEquals("A forget counted as told for a proxy's 404", mapOf("22222222222C" to key), storage.pendingForgets)
        try {
            DeliveryApiClient.publicParcel("22222222222A", configuration = postcodeConfiguration, transport = transport)
            fail("A link sheet would say the parcel was forgotten")
        } catch (error: ServerDown) {
            assertEquals(ServerDown(), error)
        }

        answer = jsonResponse(404, """{"error":"route unavailable"}""")
        runCatching { device.refresh() }
        assertEquals(2, storage.keys.size)
        assertEquals(1, storage.pendingForgets.size)

        answer = jsonResponse(404, """{"error":"Parcel unavailable"}""")
        device.refresh()
        // The service's own 404: the phone's lookup stays, no longer updated, without its key.
        assertEquals(listOf("Running shoes"), device.list().map { it.label })
        assertTrue(device.isFrozen(device.list().single().id))
        assertEquals(emptyMap<String, String>(), storage.keys)
        assertEquals(emptyMap<String, String>(), storage.pendingForgets)
        assertNull(DeliveryApiClient.publicParcel("22222222222A", configuration = postcodeConfiguration, transport = transport))
    }

    /**
     * A database being upgraded, a host restarting or the edge answering for them: the server down, which keeps every
     * parcel. The app's own 503 about one part it was never set up for is that part failing.
     */
    @Test
    fun theServerDownForMaintenanceIsToldFromTheAppsOwnTrouble() = runTest {
        var answer = jsonResponse(200)
        val transport = RecordingTransport { answer }
        val verification = NativeVerification(
            NativeAttestProvider(supported = { false }, generateKey = { "" }, attest = { _, _ -> ByteArray(0) }, assert = { _, _ -> ByteArray(0) }),
            TurnstilePresenter { throw NativeVerificationFailed() }, emptyMap(), {}, language = { AppLanguage.En },
        )
        val client = DeviceParcelClient.api(postcodeConfiguration, transport, verification, region = { Locale.UK })
        val answers = listOf(
            Triple(502, """{"error":"The delivery database is temporarily unavailable"}""", ServerDown()),
            Triple(530, "error code: 1033", ServerDown()),
            Triple(
                503, """{"error":"Peek is down for maintenance","maintenance":{"planned":true,"mode":"soft","since":null,"backAround":"2026-11-17T15:00:00Z"}}""",
                ServerDown(notice = MaintenanceNotice(planned = true, backAround = DateParser.date("2026-11-17T15:00:00Z"))),
            ),
            Triple(503, """{"error":"Push notifications are not configured"}""", null),
        )
        for ((status, body, expected) in answers) {
            answer = jsonResponse(status, body)
            try {
                client.read("22222222222A", key)
                fail("Expected a failure for $status")
            } catch (down: ServerDown) {
                assertEquals("$status $body", expected, down)
            } catch (failed: DeliveryApiError.ServiceFailed) {
                assertNull("$status $body", expected)
                assertEquals(status, failed.status)
            }
        }
    }

    /**
     * Without an account, the list keeps the device's parcels through the server down and says how old they are; the
     * next round that reads one takes the notice away.
     */
    @Test
    fun aGuestsListKeepsItsParcelsThroughTheServerDownAndSaysSo() = runTest {
        // /maintenance.json is the app's own 404 page before the edge is in front.
        val transport = RecordingTransport { jsonResponse(404, "<!doctype html><title>Not found</title>") }
        val id = UUID.randomUUID()
        val checkedAt = Instant.now().minusSeconds(5).truncatedTo(ChronoUnit.SECONDS)
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Running shoes", id, at = date(checkedAt)))
        var down = true
        val answered = PublicParcelResponse(link = link("22222222222A"), parcel = pkg(id))
        val device = device(client(Calls(), read = { if (down) throw ServerDown() else answered }), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)

        store.refreshAll()
        store.load()
        assertEquals(listOf("Running shoes"), store.parcels.map { it.label })
        assertEquals(MaintenanceNotice.Unannounced, store.serverDown)
        assertNull(store.errorMessage)
        assertEquals(checkedAt, store.listReadAt)
        assertEquals(mapOf("22222222222A" to key), storage.keys)
        runCurrent()
        assertEquals("/maintenance.json read once", 1, transport.requests.count { it.path == "/maintenance.json" })

        down = false
        store.load()
        assertNull(store.serverDown)
        assertEquals(listOf("Running shoes"), store.parcels.map { it.label })
        assertTrue(store.listReadAt!! > checkedAt)
    }

    /**
     * A guest who removes their last parcel while the server is down has nothing left to read: the service forgetting
     * it once it is back takes the notice away, and the list invites them to add one. Forgetting every parcel does too.
     */
    @Test
    fun aGuestWhoRemovedTheirLastParcelLosesTheNoticeOnceTheServiceForgetsIt() = runTest {
        val transport = RecordingTransport { jsonResponse(404, "<!doctype html><title>Not found</title>") }
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Running shoes", id, at = date(Instant.now().minusSeconds(5))))
        var down = true
        val calls = Calls()
        val device = device(
            client(calls, read = { throw ServerDown() }, forget = { if (down) throw ServerDown() }), storage, backgroundScope,
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)

        store.refreshAll()
        store.load()
        assertEquals(MaintenanceNotice.Unannounced, store.serverDown)
        store.permanentlyDelete(store.parcels.single())
        assertTrue(store.parcels.isEmpty())
        assertEquals(MaintenanceNotice.Unannounced, store.serverDown)

        down = false
        store.load()
        assertNull(store.serverDown)
        assertEquals(listOf("forget 22222222222A", "forget 22222222222A"), calls.all.filter { it.startsWith("forget") })

        // Forgetting every parcel the service answers for takes the notice away too.
        storage.parcels = listOf(parcel("22222222222B", "Coat", UUID.randomUUID()))
        storage.keys = mapOf("22222222222B" to key)
        val again = device(client(calls, read = { throw ServerDown() }), storage, backgroundScope)
        val other = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = again, deviceNotifications = quietNotifications())
        other.setDeliveryLiveActivitiesEnabled(false)
        other.refreshAll()
        assertEquals(MaintenanceNotice.Unannounced, other.serverDown)
        other.forgetDeviceParcels()
        assertNull(other.serverDown)
        assertTrue(other.parcels.isEmpty())
    }

    /** A copy the service forgot no longer moves: the widget and its shortcut pass over it for the parcels still followed. */
    @Test
    fun theWidgetPassesOverAGuestsCopyTheServiceForgot() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val gone = UUID.randomUUID()
        val moving = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "Shoes", gone).copy(frozen = "2026-11-01T08:00:00Z"),
            parcel("22222222222B", "Coat", moving, pkg = pkg(moving, number = "TESTPARCEL555000")),
        )
        val device = device(client(Calls()), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val defaults = MemoryDefaults()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device, deviceNotifications = quietNotifications(),
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        store.load()

        assertEquals("The list keeps the copy", listOf(gone, moving).toSet(), store.parcels.map { it.id }.toSet())
        assertEquals(listOf(moving), store.nextDeliveries.map { it.id })
        assertEquals(listOf(moving), requireNotNull(DeliveryWidgetSharedStore(defaults).snapshot).parcels.map { it.id })
    }

    /**
     * The sign-in opened over a guest's list leaves that list the widget's until someone signs in, and a relaunch
     * meanwhile comes back to the list; from an empty list it is remembered, as before.
     */
    @Test
    fun theSignInOverAGuestsListLeavesItTheWidgetsAndARelaunchComesBackToIt() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id))
        val device = device(client(Calls()), storage, backgroundScope)
        val defaults = MemoryDefaults()
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport, defaults = defaults)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device, deviceNotifications = quietNotifications(),
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        store.load()
        fun widget() = DeliveryWidgetSharedStore(defaults).snapshot?.parcels?.map { it.id }
        assertEquals(listOf(id), widget())

        store.showSignIn()
        assertEquals(AuthenticationState.SignedOut, session.state)
        assertTrue(session.signInOverDeviceList)
        assertEquals(listOf(id), store.parcels.map { it.id })
        assertEquals("The widget keeps the guest's list", listOf(id), widget())
        assertEquals(listOf(id), store.nextDeliveries.map { it.id })
        val relaunched = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport, defaults = defaults)
        relaunched.bootstrap()
        assertEquals("A relaunch comes back to the list", AuthenticationState.Welcome, relaunched.state)

        // Back: the guest's list, as it was.
        session.showWelcome()
        assertFalse(session.signInOverDeviceList)
        assertEquals(listOf(id), widget())

        // A phone that follows nothing on its own opens the sign-in as before, and comes back to it.
        storage.parcels = emptyList()
        storage.keys = emptyMap()
        val empty = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport, defaults = MemoryDefaults())
        empty.showWelcome()
        val emptyStore = testParcelStore(
            postcodeConfiguration, empty, transport, backgroundScope, device = device(client(Calls()), Memory(), backgroundScope),
            deviceNotifications = quietNotifications(),
        )
        emptyStore.showSignIn()
        assertFalse(empty.signInOverDeviceList)
    }

    /** The list's own read freezes a copy as a refresh does: the list stays equal, and the store says so all the same. */
    @Test
    fun aCopyTheServiceForgetsInTheListsOwnReadIsMarkedToo() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        var now = Instant.now()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id, at = date(now)))
        val calls = Calls()
        val device = device(client(calls), storage, backgroundScope, now = { now })
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)
        store.load()
        val before = store.parcels
        assertEquals(listOf(id), before.map { it.id })
        assertFalse(calls.all.toString(), "read 22222222222A k" in calls.all)

        now = now.plusSeconds(3600)
        store.load()
        assertTrue(calls.all.toString(), "read 22222222222A k" in calls.all)
        assertEquals(before, store.parcels)
        assertEquals(setOf(id), store.frozenFlow.value)
        // Nothing to be told about is on its way.
        assertFalse(device.hasParcelsOnTheirWay)
    }

    /** A copy freezes without changing its parcel: the list stays equal, and the store says which copies froze. */
    @Test
    fun theScreenIsToldWhenTheServiceForgetsAGuestsCopyThoughItsParcelStaysTheSame() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id))
        val calls = Calls()
        val device = device(client(calls), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)
        store.load()
        val before = store.parcels
        assertEquals(listOf(id), before.map { it.id })
        assertEquals(emptySet<UUID>(), store.frozenFlow.value)

        store.refreshAll()
        assertTrue(calls.all.toString(), "read 22222222222A k" in calls.all)
        assertEquals("The copy's parcel is the same", before, store.parcels)
        assertEquals(setOf(id), store.frozenFlow.value)
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
    fun viewerFollowKeepsRevocationUntilOwnershipIsRestored() = runTest {
        val id = UUID.randomUUID()
        val linkId = "22222222222A"
        for (restoreOwner in listOf(false, true)) {
            val storage = Memory()
            storage.pendingForgets = mapOf(linkId to key)
            val calls = Calls()
            val parcels = device(client(calls), storage, this)
            parcels.follow(PublicParcelResponse(reader(linkId), pkg(id)), key = null, label = "Lamp")
            assertEquals(mapOf(linkId to key), storage.pendingForgets)
            val reopened = device(client(calls), storage, this)
            assertEquals(linkId, reopened.viewerLink(id))
            if (restoreOwner) {
                assertFalse(reopened.follow(PublicParcelResponse(link(linkId), pkg(id)), key, null).added)
                assertEquals(mapOf(linkId to key), storage.keys)
            }
            reopened.tellForgotten()
            assertEquals(if (restoreOwner) emptyList() else listOf("forget $linkId"), calls.all)
            assertTrue(storage.pendingForgets.isEmpty())
        }
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
    fun aLinkFollowedAgainWhileTheServiceIsToldOfAnotherStays() = runTest {
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.pendingForgets = mapOf("22222222222A" to key, "22222222222B" to key)
        val calls = Calls()
        val gate = AlertGate()
        val gated = client(calls, read = { linkId -> PublicParcelResponse(link = link(linkId), parcel = pkg(id)) }).replacing(forget = { linkId, _ ->
            calls.add("forget $linkId")
            gate.pass("forget")
        })
        val device = device(gated, storage, this)

        val refreshing = launch { device.refresh(all = true) }
        gate.arrived("forget")
        val told = calls.all.first().substringAfterLast(" ")
        val other = if (told == "22222222222A") "22222222222B" else "22222222222A"
        device.follow(PublicParcelResponse(link = link(other), parcel = pkg(id)), key = key, label = "Lamp")
        gate.release("forget")
        refreshing.join()

        assertEquals("The link followed again is not forgotten at the service", listOf("forget $told"), calls.all.filter { it.startsWith("forget") })
        assertTrue(storage.pendingForgets.isEmpty())
        assertEquals(1, device.list().size)
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
    fun anEarlierAlertTimeoutDoesNotRetryTheOldToken() = runTest {
        val calls = Calls()
        val gate = AlertGate()
        val storage = Memory()
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()))
        var attempts = 0
        val held = client(calls).replacing(setAlert = { _, _, request ->
            if (request.token == "old") {
                attempts++
                if (attempts == 1) gate.pass("old")
                throw java.io.IOException()
            }
            DeviceAlertAnswer.On
        })
        val device = device(held, storage, this, now = { Instant.EPOCH.plusMillis(testScheduler.currentTime) })
        val first = launch { device.registerAlerts("old", NativePushLocale.En) }
        gate.arrived("old")
        device.registerAlerts("new", NativePushLocale.En)
        gate.release("old")
        first.join()
        advanceTimeBy(61_000)
        device.retryAlerts()
        assertEquals(1, attempts)
        assertEquals("new", storage.parcels.single().alertToken)
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

    /**
     * A held return is still on its way back: the carrier scans it and the server keeps its alerts on. A new token or
     * language signs it up again, as it does a parcel on its way; a plain return needs nothing.
     */
    @Test
    fun aHeldReturnIsSignedUpForNotificationsAsAParcelOnItsWay() = runTest {
        val held = UUID.randomUUID()
        val returned = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        val heldReturn = pkg(held, stage = TrackingStage.Returned).copy(carrierData = PublicPackageCarrierData(returnHeld = true))
        storage.parcels = listOf(
            parcel("22222222222A", "", held, pkg = heldReturn),
            parcel("22222222222B", "", returned, pkg = pkg(returned, stage = TrackingStage.Returned)),
        )
        val calls = Calls()
        val device = device(client(calls), storage, this)
        assertTrue(device.hasParcelsOnTheirWay)

        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(listOf("token1", null), storage.parcels.map { it.alertToken })
        assertEquals(1, calls.all.size)
        assertTrue(calls.all.single(), calls.all.single().startsWith("alert 22222222222A k "))
    }

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
    fun aParcelMutedOnItsPageHearsNothingUntilItIsTurnedOnAgain() = runTest {
        val mine = UUID.randomUUID()
        val other = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "", mine), parcel("22222222222B", "", other))
        val calls = Calls()
        var offline = true
        val device = device(client(calls, unalert = { if (offline) throw DeliveryApiError.ServiceFailed(503) }), storage, this)
        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(2, calls.all.size)

        // The service can't be told: the notifications stay on, and the page says why.
        assertThrows { device.setMuted(mine, true) }
        assertFalse(device.list().first { it.id == mine }.notificationsMuted)
        assertEquals("token1", storage.parcels.first { it.parcel.id == mine }.alertToken)

        offline = false
        assertTrue(device.setMuted(mine, true).notificationsMuted)
        assertEquals("unalert 22222222222A token1 fcm", calls.all.last())
        assertEquals(listOf(true, null), storage.parcels.map { it.muted })
        assertEquals(null, storage.parcels.first { it.parcel.id == mine }.alertToken)

        // A new token or a launch passes it over, and it counts as settled.
        device.registerAlerts(token = "token2", locale = NativePushLocale.En)
        assertEquals("alert 22222222222B k important token2 en production fcm", calls.all.dropLast(1).last())
        assertTrue(device.alertsSettled("token2", NativePushLocale.En))
        val relaunched = device(client(calls), storage, this)
        assertTrue(relaunched.list().first { it.id == mine }.notificationsMuted)
        val before = calls.all.size
        relaunched.registerAlerts(token = "token2", locale = NativePushLocale.En)
        assertFalse(calls.all.drop(before).any { it.startsWith("alert 22222222222A") })

        // Turned on again, it is asked for on the last round's token, with the preset it had.
        assertFalse(relaunched.setMuted(mine, false).notificationsMuted)
        relaunched.alertsAgain()
        assertEquals("alert 22222222222A k important token2 en production fcm", calls.all.last())
        assertEquals(listOf("token2", "token2"), storage.parcels.map { it.alertToken })
        assertEquals(listOf(null, null), storage.parcels.map { it.muted })
    }

    /**
     * A round under way while a parcel is turned back on leaves it to the round after; one muted while a round signs
     * it up with a new token has that token taken off too.
     */
    @Test
    fun aParcelTurnedOnOrOffDuringARoundEndsAsItWasLeft() = runTest {
        val mine = UUID.randomUUID()
        val other = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "", mine).copy(muted = true), parcel("22222222222B", "", other))
        val calls = Calls()
        val base = client(calls)
        val asking = CompletableDeferred<Unit>()
        val device = device(
            base.replacing(setAlert = { linkId, k, request -> if (linkId == "22222222222B") asking.await(); base.setAlert(linkId, k, request) }),
            storage, this,
        )
        val round = async { device.registerAlerts(token = "token1", locale = NativePushLocale.En) }
        runCurrent()
        device.setMuted(mine, false)
        device.alertsAgain()
        asking.complete(Unit)
        round.await()
        assertFalse(calls.all.toString(), calls.all.any { it.startsWith("alert 22222222222A") })
        assertFalse(device.alertsSettled("token1", NativePushLocale.En))
        device.retryAlerts()
        assertEquals("alert 22222222222A k important token1 en production fcm", calls.all.last())
        assertTrue(device.alertsSettled("token1", NativePushLocale.En))

        // Muted while its first token is taken off, and a round with a new token signs it up meanwhile.
        val removing = CompletableDeferred<Unit>()
        var held = true
        val muting = device(
            base.replacing(
                removeAlert = { linkId, request ->
                    if (held && linkId == "22222222222A" && request.token == "token1") {
                        held = false
                        removing.await()
                    }
                    base.removeAlert(linkId, request)
                },
            ),
            storage, this,
        )
        val mute = async { muting.setMuted(mine, true) }
        runCurrent()
        muting.registerAlerts(token = "token2", locale = NativePushLocale.En)
        removing.complete(Unit)
        assertTrue(mute.await().notificationsMuted)
        assertTrue(calls.all.toString(), "unalert 22222222222A token2 fcm" in calls.all)
        assertNull(storage.parcels.first { it.parcel.id == mine }.alertToken)
    }

    /**
     * A preset chosen for every parcel on the Delivery updates page asks again for those already on, on the same token,
     * and the service changes what they tell in place; each later round asks with it, a parcel turned back on and a
     * relaunch included. Muted parcels and copies the service forgot are passed over.
     */
    @Test
    fun aPresetChosenForEveryParcelSignsTheParcelsUpAgainWithIt() = runTest {
        val news = UUID.randomUUID()
        val waiting = UUID.randomUUID()
        val muted = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", news),
            parcel("22222222222B", "", waiting, pkg = pkg(waiting, stage = TrackingStage.Registered)),
            parcel("22222222222C", "", muted).copy(muted = true),
            parcel("22222222222D", "", UUID.randomUUID()).copy(frozen = "2026-11-01T08:00:00Z"),
        )
        val calls = Calls()
        val device = device(client(calls), storage, this)
        // Until a preset is chosen, each parcel is given its own.
        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(
            listOf("alert 22222222222A k important token1 en production fcm", "alert 22222222222B k all token1 en production fcm"),
            calls.all,
        )

        device.registerAlerts(token = "token1", locale = NativePushLocale.En, preset = ParcelAlertPreset.Delivery)
        assertEquals(
            listOf("alert 22222222222A k delivery token1 en production fcm", "alert 22222222222B k delivery token1 en production fcm"),
            calls.all.drop(2),
        )
        assertFalse("The same phone: nothing is taken off", calls.all.any { it.startsWith("unalert") })
        assertEquals(listOf(ParcelAlertPreset.Delivery, ParcelAlertPreset.Delivery, null, null), storage.parcels.map { it.alertPreset })
        assertTrue(device.alertsSettled("token1", NativePushLocale.En, ParcelAlertPreset.Delivery))
        device.registerAlerts(token = "token1", locale = NativePushLocale.En, preset = ParcelAlertPreset.Delivery)
        assertEquals("Asked with it already, nothing goes out again", 4, calls.all.size)

        // A parcel turned back on is asked for with it.
        device.setMuted(muted, false)
        device.alertsAgain(ParcelAlertPreset.Delivery)
        assertEquals("alert 22222222222C k delivery token1 en production fcm", calls.all.last())

        // A relaunch's round on a new token asks with the preset chosen since.
        val relaunched = device(client(calls), storage, this)
        val launched = calls.all.size
        relaunched.registerAlerts(token = "token2", locale = NativePushLocale.En, preset = ParcelAlertPreset.All)
        assertEquals(
            listOf("A", "B", "C").map { "alert 22222222222$it k all token2 en production fcm" },
            calls.all.drop(launched).filter { it.startsWith("alert") },
        )
        assertFalse(calls.all.toString(), calls.all.any { it.startsWith("alert 22222222222D") })
    }

    /**
     * Turned off for every parcel from the Delivery updates page: the service is told about each link signed up here,
     * and one it can't be told about yet keeps its token for the next call. No retry, and no parcel turned back on, asks
     * for notifications meanwhile; a round under way has what it signed up taken off. Asked again, every parcel on its
     * way is signed up.
     */
    @Test
    fun turnedOffForEveryParcelTheLinksHearNothingUntilAskedAgain() = runTest {
        val muted = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key, "22222222222C" to key, "22222222222D" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", UUID.randomUUID()),
            parcel("22222222222B", "", UUID.randomUUID()),
            parcel("22222222222C", "", muted).copy(muted = true),
            parcel("22222222222D", "", UUID.randomUUID()),
        )
        val calls = Calls()
        var clock = Instant.parse("2026-11-20T08:00:00Z")
        var failing: String? = "22222222222D"
        var offline: String? = null
        val base = client(
            calls,
            alert = { if (it == failing) throw IOException("offline") else DeviceAlertAnswer.On },
            unalert = { if (it == offline) throw DeliveryApiError.ServiceFailed(503) },
        )
        val device = device(base, storage, this, now = { clock })
        // A round cut short: the last parcel waits for a retry.
        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(listOf("token1", "token1", null, null), storage.parcels.map { it.alertToken })
        val signedUp = calls.all.size

        // The service can't be told: the first failure ends the call, and the tokens stay for the next one.
        offline = "22222222222A"
        device.alertsOff()
        assertEquals(listOf("unalert 22222222222A token1 fcm"), calls.all.drop(signedUp))
        assertEquals(listOf("token1", "token1", null, null), storage.parcels.map { it.alertToken })

        // Nothing asks for notifications meanwhile: not the retry once its time has come, nor a parcel turned back on.
        failing = null
        clock = clock.plus(2, ChronoUnit.HOURS)
        device.retryAlerts()
        device.setMuted(muted, false)
        device.alertsAgain()
        assertFalse(calls.all.toString(), calls.all.drop(signedUp).any { it.startsWith("alert") })

        offline = null
        device.alertsOff()
        assertEquals(listOf("unalert 22222222222A token1 fcm", "unalert 22222222222B token1 fcm"), calls.all.drop(signedUp + 1))
        assertEquals(listOf<String?>(null, null, null, null), storage.parcels.map { it.alertToken })

        // Turned off while a round asks the service: the link it signed up meanwhile is taken off.
        val asking = CompletableDeferred<Unit>()
        val held = device(
            base.replacing(setAlert = { linkId, k, request -> if (linkId == "22222222222A") asking.await(); base.setAlert(linkId, k, request) }),
            storage, this, now = { clock },
        )
        val round = async { held.registerAlerts(token = "token2", locale = NativePushLocale.En) }
        runCurrent()
        held.alertsOff()
        asking.complete(Unit)
        round.await()
        assertEquals(listOf("alert 22222222222A k important token2 en production fcm", "unalert 22222222222A token2 fcm"), calls.all.takeLast(2))
        assertEquals(listOf<String?>(null, null, null, null), storage.parcels.map { it.alertToken })

        // Asked again, every parcel on its way is signed up, the one turned back on included.
        held.registerAlerts(token = "token2", locale = NativePushLocale.En)
        assertEquals(listOf("token2", "token2", "token2", "token2"), storage.parcels.map { it.alertToken })
    }

    /**
     * The build without Google's services names the phone by its installation token, reached through its inbox or
     * through UnifiedPush: a change of way asks again with the same token, whose alert the service changes in place, so
     * nothing is turned off; a new installation token takes the old one's place as a new FCM token does.
     */
    @Test
    fun aPhoneWithoutGooglesServicesKeepsItsAlertsAsItMovesBetweenItsInboxAndUnifiedPush() = runTest {
        val mine = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", mine))
        val calls = Calls()
        val sent = mutableListOf<ParcelAppAlertRequest>()
        val base = client(calls)
        val device = device(base.replacing(setAlert = { linkId, key, request -> sent += request; base.setAlert(linkId, key, request) }), storage, this)
        val installation = "A".repeat(43)
        val inbox = PushAddress(installation, NativePushTransport.Inbox).text
        val keys = PushSubscriptionKeys(p256dh = "B".repeat(87), auth = "C".repeat(22))
        val unifiedPush = PushAddress(installation, NativePushTransport.Unifiedpush, "https://push.example.com/up/1", keys).text

        device.registerAlerts(token = inbox, locale = NativePushLocale.En)
        device.registerAlerts(token = unifiedPush, locale = NativePushLocale.En)
        assertEquals(
            listOf("alert 22222222222A k important $installation en production inbox", "alert 22222222222A k important $installation en production unifiedpush"),
            calls.all,
        )
        assertEquals(listOf(null to null, "https://push.example.com/up/1" to keys), sent.map { it.endpoint to it.keys })
        assertEquals(listOf(unifiedPush), storage.parcels.map { it.alertToken })

        // Signed out, the phone takes a new installation token, and the old one hears nothing more.
        device.registerAlerts(token = PushAddress("D".repeat(43), NativePushTransport.Inbox).text, locale = NativePushLocale.En)
        assertEquals("unalert 22222222222A $installation unifiedpush", calls.all.last())
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

    /**
     * A round cut short by an outage or a pause is tried again: a minute later, then twice as long each time it fails
     * again, never before a pause the service asked for runs out, and the parcels it signed up aren't asked again.
     */
    @Test
    fun aRoundCutShortIsTriedAgainLater() = runTest {
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(
            parcel("22222222222A", "", UUID.randomUUID()),
            UUID.randomUUID().let { parcel("22222222222B", "", it, pkg = pkg(it, number = "TESTPARCEL999")) },
        )
        val calls = Calls()
        val start = Instant.ofEpochSecond(1_800_000_000)
        var now = start
        val device = device(
            client(calls, alert = {
                when (calls.all.size) {
                    2 -> DeviceAlertAnswer.Unavailable
                    3 -> throw DeliveryApiError.ServiceFailed(502)
                    4 -> throw DeliveryApiError.RateLimited(600.0)
                    else -> DeviceAlertAnswer.On
                }
            }),
            storage, this, now = { now },
        )
        fun asked() = calls.all.map { it.split(" ")[1] }

        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(listOf("22222222222A", "22222222222B"), asked())
        for ((seconds, expected) in listOf(59L to 2, 60L to 3, 179L to 3, 180L to 4, 779L to 4, 780L to 5, 10_000L to 5)) {
            now = start.plusSeconds(seconds)
            device.retryAlerts()
            assertEquals("$seconds s after the first round", expected, calls.all.size)
        }
        assertEquals(listOf("22222222222A", "22222222222B", "22222222222B", "22222222222B", "22222222222B"), asked())
        assertEquals(listOf("token1", "token1"), storage.parcels.mapNotNull { it.alertToken })
    }

    /** Past a round's 25 parcels, the next round asks for the rest at once, without waiting for another token. */
    @Test
    fun theParcelsPastARoundsGoInTheNextRound() = runTest {
        val storage = Memory()
        val links = (0 until DeviceParcels.alertsPerRound + 2).map { "2222222222%02d".format(it) }
        storage.keys = links.associateWith { key }
        storage.parcels = links.map { parcel(it, "", UUID.randomUUID()) }
        val calls = Calls()
        val device = device(client(calls), storage, this, now = { Instant.ofEpochSecond(1_800_000_000) })
        fun alerts() = calls.all.count { it.startsWith("alert") }

        device.registerAlerts(token = "token1", locale = NativePushLocale.En)
        assertEquals(DeviceParcels.alertsPerRound, alerts())
        assertFalse(device.alertsSettled("token1", NativePushLocale.En))
        device.retryAlerts()
        assertEquals(DeviceParcels.alertsPerRound + 2, alerts())
        assertTrue(device.alertsSettled("token1", NativePushLocale.En))
        device.retryAlerts()
        assertEquals("Once every parcel is signed up, nothing is asked again", DeviceParcels.alertsPerRound + 2, alerts())
        assertEquals(links, storage.parcels.filter { it.alertToken == "token1" }.map { it.linkId })
    }

    /** The guest list's refresh is what tries a round cut short again. */
    @Test
    fun theGuestListsRefreshTriesNotificationsAgainAfterAnOutage() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(UUID.randomUUID(), stage = TrackingStage.Registered))
        val calls = Calls()
        var now = Instant.now()
        fun alerts() = calls.all.count { it.startsWith("alert") }
        val device = device(
            client(calls, read = { shown }, alert = { if (alerts() == 1) DeviceAlertAnswer.Unavailable else DeviceAlertAnswer.On }),
            Memory(), backgroundScope, now = { now },
        )
        val notifications = DeviceNotificationAccess(
            status = { NotificationAuthorizationStatus.Authorized }, request = {}, register = {}, token = { "token1" },
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = Localizer(AppLanguage.En), device = device,
            deviceNotifications = notifications,
        )
        store.setDeliveryLiveActivitiesEnabled(false)

        store.follow(ParcelLinkRoute(id = "33333333333A", name = "Books"), shown)
        runCurrent()
        assertEquals(1, alerts())
        store.load()
        assertEquals("Not before its time", 1, alerts())
        now = now.plusSeconds(DeviceParcels.alertRetryFirst.toLong())
        store.load()
        assertEquals(2, alerts())
        store.load()
        assertEquals("Once it went through, nothing is asked again", 2, alerts())
    }

    /** A carrier change looked up afresh signs the new lookup's link up for notifications at once, as an added parcel's. */
    @Test
    fun aNewLookupForAnotherCarrierIsSignedUpForNotificationsAtOnce() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val previousId = UUID.randomUUID()
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "Moon lamp", previousId).copy(alertToken = "token1"))
        val calls = Calls()
        val device = device(
            client(calls, lookup = PublicLookupResponse(link = link("22222222222B"), key = key, parcel = pkg(UUID.randomUUID(), carrier = CarrierId.Dpd))),
            storage, backgroundScope,
        )
        val notifications = DeviceNotificationAccess(
            status = { NotificationAuthorizationStatus.Authorized }, request = {}, register = {}, token = { "token1" },
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = Localizer(AppLanguage.En), device = device,
            deviceNotifications = notifications,
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        assertTrue(store.isGuest)

        store.changeCarrier(device.list().single(), CarrierId.Dpd, trackingUrl = null, dpdPostcode = null, dpdPostcodeCountry = null)
        runCurrent()

        assertTrue(calls.all.toString(), calls.all.any { it.startsWith("alert 22222222222B") })
        assertEquals(listOf("token1"), storage.parcels.map { it.alertToken })
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

    /**
     * Signed out, a pull made while a check of every parcel runs gets `AlreadyRunning`, and the list waits for the
     * check's outcome to show it, as a signed-in pull waits for the service's.
     */
    @Test
    fun signedOutAPullDuringACheckIsToldHowThatCheckEnded() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        val id = UUID.randomUUID()
        storage.keys = mapOf("22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222B", "", id))
        val gate = CompletableDeferred<Unit>()
        val delivered = PublicParcelResponse(link = link("22222222222B"), parcel = pkg(id, stage = TrackingStage.Delivered))
        val client = client(Calls()).replacing(read = { _, _ -> gate.await(); delivered })
        val device = device(client, storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, deviceNotifications = quietNotifications())
        store.setDeliveryLiveActivitiesEnabled(false)
        assertTrue(store.isGuest)

        val first = async { store.refreshAll() }
        runCurrent()
        assertTrue(store.refreshing)
        val before = store.refreshOutcomeFlow.value?.id
        assertEquals(ParcelStore.RefreshStart.AlreadyRunning, store.refreshAll())
        gate.complete(Unit)

        assertEquals(ParcelStore.RefreshStart.Completed(ParcelStore.RefreshResult.Updated), first.await())
        assertEquals(
            "Signed out, a pull made during a check is left without that check's outcome, so it shows nothing",
            ParcelStore.RefreshResult.Updated,
            store.refreshOutcomeFlow.value?.takeIf { it.id != before }?.result,
        )
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
    fun aLinkToTheParcelOfACopyTheServiceForgotIsFollowedInItsPlace() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val storage = Memory()
        storage.parcels = listOf(parcel("22222222222A", "Shoes", id).copy(frozen = "2026-11-01T08:00:00Z"))
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id))
        val device = device(client(Calls(), read = { shown }), storage, backgroundScope)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = Localizer(AppLanguage.En), device = device,
            notifications = StubPushNotifications(),
        )
        store.setDeliveryLiveActivitiesEnabled(false)

        // The link's sheet offers to follow it, rather than opening the copy no longer updated.
        assertNull(store.followedHere(shown))
        assertEquals(ParcelLinkClaim.Added(id), store.follow(ParcelLinkRoute(id = "33333333333A"), shown))
        assertEquals(listOf("Shoes"), store.parcels.map { it.label })
        assertEquals(id, store.followedHere(shown))
        assertEquals(listOf("33333333333A"), storage.parcels.map { it.linkId })
    }

    @Test
    fun followingALinkWithoutAnAccountInvitesTheListToNotificationsAndTurnsThemOnFromIt() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id, stage = TrackingStage.Registered))
        val calls = Calls()
        val device = device(client(calls, read = { shown }), Memory(), backgroundScope)
        val push = StubPushNotifications(granted = true, currentDeviceToken = "token1")
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        val localizer = Localizer(AppLanguage.En)
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, localizer = localizer, device = device, notifications = push,
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        assertNull(store.followedHere(shown))

        val claim = store.follow(ParcelLinkRoute(id = "33333333333A", name = "Books"), shown)

        assertEquals(ParcelLinkClaim.Added(id), claim)
        assertTrue("The sign-in screen gives way to the parcels on this iPhone", session.isGuest)
        assertEquals(listOf("Books"), store.parcels.map { it.label })
        assertEquals(id, store.followedHere(shown))
        assertEquals(id, store.followedHere(linkId = "33333333333A"))
        assertEquals("33333333333A", store.viewerLink(store.parcels.first()))
        runCurrent()
        // The phone isn't asked at once: the list invites it.
        assertFalse(push.calls.contains("request"))
        assertEquals(emptyList<String>(), calls.all.filter { it.startsWith("alert") })
        store.refreshNotificationState()
        assertTrue(store.shouldInviteNotifications)

        assertFalse("No account, no welcome notification", store.enableNotifications(localizer.language))
        runCurrent()
        val alert = "alert 33333333333A - all token1 ${NativePushLocale(localizer.language).rawValue} production fcm"
        assertEquals(listOf(alert), calls.all.filter { it.startsWith("alert") })
        assertEquals(1, push.calls.count { it == "request" })
        assertFalse(store.shouldInviteNotifications)
        val again = store.follow(ParcelLinkRoute(id = "33333333333A"), shown)
        assertEquals(ParcelLinkClaim.Already(id), again)
    }

    @Test
    fun aGuestsNotNowStaysAndAPhoneThatAllowsNotificationsIsNotInvited() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val id = UUID.randomUUID()
        val shown = PublicParcelResponse(link = reader("33333333333A"), parcel = pkg(id, stage = TrackingStage.Registered))
        val device = device(client(Calls(), read = { shown }), Memory(), backgroundScope)
        val push = StubPushNotifications()
        val defaults = MemoryDefaults()
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        fun store() = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device, notifications = push)
        val first = store()
        first.setDeliveryLiveActivitiesEnabled(false)
        first.follow(ParcelLinkRoute(id = "33333333333A"), shown)
        first.refreshNotificationState()
        assertTrue(first.shouldInviteNotifications)

        first.dismissNotificationInvitation()
        assertFalse(first.shouldInviteNotifications)
        val relaunched = store()
        relaunched.refreshNotificationState()
        assertFalse("Not now is kept on the phone", relaunched.shouldInviteNotifications)

        push.status = NotificationAuthorizationStatus.Authorized
        val allowed = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, device = device, notifications = push)
        allowed.refreshNotificationState()
        assertFalse(allowed.shouldInviteNotifications)
        push.status = NotificationAuthorizationStatus.Denied
        allowed.refreshNotificationState()
        assertFalse(allowed.shouldInviteNotifications)
    }

    /**
     * Without an account, the preset saved on the Delivery updates page is kept on the phone: the parcel already on is
     * asked again with it at once, the one muted on its page stays off, and a relaunch's rounds ask with it. The page
     * shows Important until one is saved.
     */
    @Test
    fun aGuestsPresetSavedInSettingsIsKeptOnThePhoneAndSignsTheParcelsUpAgain() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()), parcel("22222222222B", "", UUID.randomUUID()).copy(muted = true))
        val calls = Calls()
        val push = StubPushNotifications(status = NotificationAuthorizationStatus.Authorized, currentDeviceToken = "token1")
        val defaults = MemoryDefaults()
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults = defaults,
            device = device(client(calls), storage, backgroundScope), notifications = push,
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        assertNull("Until one is saved, each parcel keeps its own", store.deviceAlertPresetFlow.value)
        assertEquals(NotificationPreset.Important, NotificationPreset.withoutAccount(store.deviceAlertPresetFlow.value))
        store.registerDeviceAlerts("token1", AppLanguage.En)
        assertEquals(listOf("alert 22222222222A k important token1 en production fcm"), calls.all)

        store.saveDeviceAlertPreset(NotificationPreset.DeliveryDay.alertPreset)

        assertEquals(ParcelAlertPreset.Delivery, store.deviceAlertPresetFlow.value)
        assertEquals("delivery", defaults.string(forKey = NotificationDeviceKeys.ALERT_PRESET))
        assertEquals(listOf("alert 22222222222A k delivery token1 en production fcm"), calls.all.drop(1))

        // Kept on the phone: a relaunch shows it, and its rounds ask with it, on a new token too.
        val relaunched = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults = defaults,
            device = device(client(calls), storage, backgroundScope), notifications = push,
        )
        assertEquals(NotificationPreset.DeliveryDay, NotificationPreset.withoutAccount(relaunched.deviceAlertPresetFlow.value))
        assertTrue(relaunched.registerDeviceAlerts("token2", AppLanguage.En))
        assertEquals(listOf("alert 22222222222A k delivery token2 en production fcm"), calls.all.filter { it.contains("token2") && it.startsWith("alert") })
        assertFalse(calls.all.toString(), calls.all.any { it.startsWith("alert 22222222222B") })

        // The page's presets are the service's, one for one.
        assertEquals(listOf(ParcelAlertPreset.All, ParcelAlertPreset.Important, ParcelAlertPreset.Delivery), NotificationPreset.entries.map { it.alertPreset })
        assertEquals(NotificationPreset.entries.toList(), NotificationPreset.entries.map { NotificationPreset.of(it.alertPreset) })
    }

    /**
     * Without an account, the Delivery updates page's switch turned off takes every parcel's notifications off this
     * phone, the next refresh telling the service about a link it couldn't be told about then, and the choice stays: no
     * token, launch or invitation signs a parcel up until the switch is on again, which leaves a parcel muted on its page
     * as it was.
     */
    @Test
    fun aGuestsSwitchTurnedOffTakesTheParcelsOffAndNothingSignsThemUpUntilItIsOn() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key, "22222222222B" to key)
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()), parcel("22222222222B", "", UUID.randomUUID()).copy(muted = true))
        val calls = Calls()
        var offline = true
        val device = device(client(calls, unalert = { if (offline) throw DeliveryApiError.ServiceFailed(503) }), storage, backgroundScope)
        val push = StubPushNotifications(status = NotificationAuthorizationStatus.Authorized, granted = true, currentDeviceToken = "token1")
        val defaults = MemoryDefaults()
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device, notifications = push)
        store.setDeliveryLiveActivitiesEnabled(false)
        store.refreshNotificationState()
        assertTrue("Allowed and never turned off, a guest's notifications are on", store.notificationsEnabledOnDevice)
        store.registerDeviceAlerts("token1", AppLanguage.En)
        assertEquals(listOf("alert 22222222222A k important token1 en production fcm"), calls.all)

        // The service can't be told yet: the switch is off all the same, and the next refresh tells it.
        store.disableNotifications()
        assertEquals("unalert 22222222222A token1 fcm", calls.all.last())
        assertFalse(store.notificationsEnabledOnDevice)
        assertTrue(defaults.bool(forKey = NotificationDeviceKeys.DEVICE_ALERTS_OFF))
        assertFalse("An account keeps its own choice", defaults.bool(forKey = NotificationDeviceKeys.OPT_OUT))
        assertTrue("The phone lets its token go", push.calls.containsAll(listOf("unregister", "clear")))
        assertEquals("token1", storage.parcels.first().alertToken)
        offline = false
        store.load()
        assertEquals(List(2) { "unalert 22222222222A token1 fcm" }, calls.all.filter { it.startsWith("unalert") })
        assertNull(storage.parcels.first().alertToken)

        // Nothing signs a parcel up while it is off: a new token, a parcel's question, a launch, the invitation.
        push.calls.clear()
        push.currentDeviceToken = "token2"
        assertTrue(store.registerDeviceAlerts("token2", AppLanguage.En))
        store.askForDeviceAlerts(AppLanguage.En)
        push.status = NotificationAuthorizationStatus.NotDetermined
        val relaunched = testParcelStore(postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device, notifications = push)
        relaunched.setDeliveryLiveActivitiesEnabled(false)
        relaunched.refreshNotificationState()
        assertFalse(relaunched.shouldInviteNotifications)
        push.status = NotificationAuthorizationStatus.Authorized
        relaunched.refreshNotificationState()
        assertFalse(relaunched.notificationsEnabledOnDevice)
        assertFalse("Nothing registers the phone", push.calls.contains("register"))
        assertFalse(calls.all.toString(), calls.all.any { it.contains("token2") })

        // On again, the parcel on its way is signed up; the one muted on its page stays off.
        assertFalse("No account, no welcome notification", relaunched.enableNotifications(AppLanguage.En))
        assertTrue(relaunched.notificationsEnabledOnDevice)
        assertFalse(defaults.bool(forKey = NotificationDeviceKeys.DEVICE_ALERTS_OFF))
        assertEquals(listOf("alert 22222222222A k important token2 en production fcm"), calls.all.filter { it.contains("token2") })
        assertEquals(listOf(null, true), storage.parcels.map { it.muted })
    }

    /** An account's "off" on this phone is its own: without an account, the parcels followed here still announce. */
    @Test
    fun anAccountsOffLeavesTheParcelsFollowedWithoutOneAlone() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()))
        val calls = Calls()
        val push = StubPushNotifications(status = NotificationAuthorizationStatus.Authorized, granted = true, currentDeviceToken = "token1")
        val defaults = MemoryDefaults(
            mutableMapOf(
                NotificationDeviceKeys.OPT_OUT to true, NotificationDeviceKeys.OWNER to UUID.randomUUID().toString(),
                NotificationDeviceKeys.DEVICE_ALERTS_OFF_CARRIED to true,
            ),
        )
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, defaults = defaults, device = device(client(calls), storage, backgroundScope),
            notifications = push,
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        store.refreshNotificationState()
        assertTrue(store.notificationsEnabledOnDevice)
        store.registerDeviceAlerts("token1", AppLanguage.En)
        assertEquals(listOf("alert 22222222222A k important token1 en production fcm"), calls.all)
        assertTrue(defaults.bool(forKey = NotificationDeviceKeys.OPT_OUT))
    }

    /**
     * Refused for good, a guest's notifications are allowed in Android's settings: back in the app, the refresh of what
     * the phone allows registers it, and the token it hands over signs the parcels up with nothing to tap. Not once the
     * switch on the Delivery updates page was turned off.
     */
    @Test
    fun notificationsAllowedInAndroidsSettingsSignTheParcelsUpOnTheWayBackUnlessTurnedOff() = runTest {
        val transport = RecordingTransport { jsonResponse(404) }
        val storage = Memory()
        storage.keys = mapOf("22222222222A" to key)
        storage.parcels = listOf(parcel("22222222222A", "", UUID.randomUUID()))
        val calls = Calls()
        val push = StubPushNotifications(status = NotificationAuthorizationStatus.Denied, answeredBefore = true)
        val session = testSessionStore(postcodeConfiguration, backgroundScope, transport = transport)
        session.showWelcome()
        val store = testParcelStore(
            postcodeConfiguration, session, transport, backgroundScope, device = device(client(calls), storage, backgroundScope), notifications = push,
        )
        store.setDeliveryLiveActivitiesEnabled(false)
        // The app hands over each token it is given, as AppContainer does.
        push.registered = {
            push.currentDeviceToken = "token1"
            backgroundScope.launch { store.registerDeviceAlerts("token1", AppLanguage.En) }
        }
        store.refreshNotificationState()
        assertEquals(NotificationAuthorizationStatus.Denied, store.notificationStatus)
        assertFalse(store.notificationsEnabledOnDevice)
        assertFalse(push.calls.contains("register"))

        // Allowed in Android's settings, and back on the page.
        push.status = NotificationAuthorizationStatus.Authorized
        store.refreshNotificationState()
        runCurrent()
        assertTrue(store.notificationsEnabledOnDevice)
        assertEquals(listOf("alert 22222222222A k important token1 en production fcm"), calls.all)
        assertFalse("Nothing is asked", push.calls.contains("request"))

        // Turned off on the page, then refused and allowed again: the way back leaves it off.
        store.disableNotifications()
        push.status = NotificationAuthorizationStatus.Denied
        store.refreshNotificationState()
        push.status = NotificationAuthorizationStatus.Authorized
        push.calls.clear()
        val before = calls.all.size
        store.refreshNotificationState()
        runCurrent()
        assertFalse(store.notificationsEnabledOnDevice)
        assertFalse(push.calls.contains("register"))
        assertEquals(before, calls.all.size)
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

    /**
     * Forgetting is cancelled while the service is asked to turn someone else's parcel's notifications off. The
     * cancellation goes on to the caller, the parcel has left the list already, and it stays on file as forgotten, so
     * that the next refresh asks the service again.
     */
    @Test
    fun aParcelWhoseForgettingIsCancelledStaysForgotten() = runTest {
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
            fail("The forgetting went on as though nothing happened")
        } catch (_: CancellationException) {
        }
        assertTrue(device.list().isEmpty())
        assertEquals(listOf(true), storage.parcels.map { it.forgotten })
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
