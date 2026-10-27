package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsResponse
import com.plhery.peekdeliverytracker.core.api.DeleteParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.NativePushEnvironment
import com.plhery.peekdeliverytracker.core.api.NativePushLocale
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.ParcelAlertPreset
import com.plhery.peekdeliverytracker.core.api.ParcelAppAlertRequest
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ParcelLinkTests.swift. The tests that go through `DeliveryAPIClient`, `ParcelStore` or `DeviceParcelClient` over
 * HTTP keep here what does not need the app: the bodies those calls send and how their answers read.
 */
class ParcelLinkTests {
    private val linkId = "k7Qm2xHd9RtW"

    @Serializable
    private class Fixture(val publicParcel: PublicParcelResponse, val publicLookup: PublicLookupResponse)

    private fun fixture(): Fixture = ApiJson.decodeFromString(Fixture.serializer(), SharedResources.text("ContractFixtures.json"))

    private fun body(bytes: ByteArray): String = bytes.decodeToString()

    // Contract

    @Test
    fun decodesThePublicParcelFixtures() {
        val fixture = fixture()

        val viewed = fixture.publicParcel
        assertEquals(linkId, viewed.link.id)
        assertEquals(ParcelLinkRole.Viewer, viewed.link.role)
        assertEquals(ParcelLinkKind.Lookup, viewed.link.kind)
        assertFalse(viewed.link.numberShown)
        assertFalse(viewed.link.canKeep)
        assertNotNull(viewed.link.forgetAt)
        assertNull(viewed.parcel.trackingNumber)
        assertEquals(ParcelNumberHint(head = "", tail = "3456"), viewed.parcel.numberHint)
        assertEquals(CarrierId.Dpd, viewed.parcel.carrier)
        assertEquals("Example Shop", viewed.parcel.carrierData.senderName)
        assertEquals(1.2, viewed.parcel.carrierData.weightKg)
        assertEquals(TrackingStage.InTransit, viewed.parcel.trackingEvents.firstOrNull()?.stage)

        val looked = fixture.publicLookup
        assertEquals(ParcelLinkRole.Owner, looked.link.role)
        assertTrue(looked.link.numberShown)
        assertTrue(looked.link.canKeep)
        assertEquals(43, looked.key.characterCount)
        assertEquals("TESTPARCEL983456", looked.parcel.trackingNumber)
        assertNull(looked.parcel.numberHint)
        assertEquals(PublicPackageCarrierData(), looked.parcel.carrierData)
    }

    @Test
    fun claimRequestSendsOnlyTheLinkAndItsSuggestedName() {
        val named = JsonEncoder.deliveryTracker.encode(
            ClaimParcelsRequest.serializer(), ClaimParcelsRequest(links = listOf(ClaimParcelLink(id = linkId, label = "Moon lamp"))),
        )
        assertEquals("""{"links":[{"id":"k7Qm2xHd9RtW","label":"Moon lamp"}]}""", body(named))
        val plain = JsonEncoder.deliveryTracker.encode(ClaimParcelsRequest.serializer(), ClaimParcelsRequest(links = listOf(ClaimParcelLink(id = linkId))))
        assertEquals("""{"links":[{"id":"k7Qm2xHd9RtW"}]}""", body(plain))
    }

    // The app's parcel

    @Test
    fun aHiddenNumberStaysHiddenOnTheAppsParcel() {
        val shared = fixture().publicParcel.parcel
        val parcel = Parcel(shared = shared)

        assertEquals(shared.id, parcel.id)
        assertEquals("", parcel.trackingNumber)
        assertEquals("••• 3456", shared.numberHint?.masked)
        assertEquals("", parcel.label)
        assertEquals(CarrierId.Dpd, parcel.carrier)
        assertEquals("2026-10-03", parcel.expectedDelivery)
        assertEquals("2026-10-02T08:05:00+00:00", parcel.lastSyncedAt)
        assertEquals(SyncStatus.Ok, parcel.syncStatus)
        assertNull(parcel.trackingUrl)
        assertNull(parcel.dpdPostcode)
        assertFalse(parcel.isArchived)
        assertFalse(parcel.notificationsMuted)
        assertEquals("Example Shop", parcel.carrierData?.senderName)
        assertEquals(1.2, parcel.carrierData?.weightKg)
        assertEquals("CH", parcel.carrierData?.destinationCountry)
        assertEquals(shared.trackingEvents, parcel.trackingEvents)
        assertEquals(TrackingStage.InTransit, parcel.currentStage)
        assertEquals("stage.in_transit", parcel.displayStatus.key)
    }

    @Test
    fun aShownNumberIsTheAppsParcelNumber() {
        val parcel = Parcel(shared = fixture().publicLookup.parcel)

        assertEquals("TESTPARCEL983456", parcel.trackingNumber)
        assertEquals(listOf("TESTPARCEL983456"), parcel.trackingNumbers.map { it.number })
        assertEquals(TrackingStage.Pending, parcel.currentStage)
        assertTrue(parcel.displayStatus.syncing)
    }

    @Test
    fun placesAndTheSharedCarrierDataReachTheAppsParcel() {
        val json = """
            {
              "id": "43000000-0000-0000-0000-000000000005",
              "tracking_number": "TESTPARCEL654321",
              "number_hint": null,
              "label": "",
              "carrier": "asendia",
              "created_at": "2026-10-01T08:00:00+00:00",
              "expected_delivery": "2026-10-04",
              "last_status_text": "Out for delivery",
              "last_synced_at": "2026-10-02T08:05:00+00:00",
              "sync_status": "ok",
              "sync_error": null,
              "tracking_url": null,
              "dpd_postcode": null,
              "carrier_data": {
                "active_tracking_carrier": "swiss-post",
                "active_tracking_number": "990000000000000001",
                "original_carrier": "asendia",
                "original_tracking_number": "TESTPARCEL654321",
                "tracking_provider": "ParcelsApp",
                "carrier_answered": true,
                "auto_changed_from": "unknown",
                "auto_changed_to": "asendia",
                "auto_changed_at": "2026-10-01T09:00:00+00:00",
                "sender_name": "Example Shop",
                "swiss_post_ready": true,
                "expected_delivery_from": "2026-10-03",
                "pickup_point": "Example Kiosk\nBahnhofstrasse 1, 8000 Zürich",
                "dimensions_text": "30 × 20 × 10 cm",
                "weight_kg": 0.8,
                "import_charges": { "currency": "EUR", "customs": 23.41, "carrier_fee": 11, "carrier_fee_online": 8.09 },
                "destination_country": "CH",
                "receiver_name": "Never shared",
                "routing": { "input_needed": { "carrier": "dpd", "field": "dpdPostcode" } }
              },
              "archived_at": null,
              "notifications_muted": false,
              "tracking_events": [{
                "id": "44000000-0000-0000-0000-000000000005",
                "package_id": "43000000-0000-0000-0000-000000000005",
                "stage": "out_for_delivery",
                "description": "Out for delivery",
                "location": "Zürich, CH",
                "occurred_at": "2026-10-02T08:05:00+00:00",
                "place": { "latitude": 47.37, "longitude": 8.54, "precision": "city", "country": "CH", "name": "Zürich" }
              }]
            }
        """.trimIndent()
        val shared = ApiJson.decodeFromString(PublicPackage.serializer(), json)
        val parcel = Parcel(shared = shared)
        val data = parcel.carrierData!!

        assertEquals(EventPlace(latitude = 47.37, longitude = 8.54, precision = EventPlacePrecision.City, country = "CH", name = "Zürich"), parcel.trackingEvents.firstOrNull()?.place)
        assertEquals(TrackingStage.OutForDelivery, parcel.currentStage)
        assertEquals(CarrierId.SwissPost, parcel.activeTrackingCarrier)
        assertEquals(CarrierId("asendia"), parcel.displayedCarrier)
        assertEquals(listOf("990000000000000001", "TESTPARCEL654321"), parcel.trackingNumbers.map { it.number })
        assertEquals("ParcelsApp", data.trackingProvider)
        assertEquals(true, data.carrierAnswered)
        assertEquals(CarrierId.Unknown, data.autoChangedFrom)
        assertEquals(CarrierId("asendia"), data.autoChangedTo)
        assertEquals("2026-10-01T09:00:00+00:00", data.autoChangedAt)
        assertEquals("Example Shop", data.senderName)
        assertEquals(true, data.swissPostReady)
        assertEquals("2026-10-03", data.expectedDeliveryFrom)
        assertEquals("Example Kiosk", PickupPoint(data.pickupPoint)?.name)
        assertEquals("30 × 20 × 10 cm", data.dimensionsText)
        assertEquals(0.8, data.weightKg)
        assertEquals(ImportCharges(currency = "EUR", customs = 23.41, carrierFee = 11.0, carrierFeeOnline = 8.09), data.importCharges)
        assertEquals("CH", data.destinationCountry)
        // Whatever else a response carried, the parcel shows only what a link shares.
        assertNull(data.receiverName)
        assertNull(data.routing)
        assertNull(data.originalPackageId)
        assertNull(data.originalTrackingUrl)
        assertNull(data.dpdPostcodeVerified)
        assertNull(parcel.inputNeededPrompt)
    }

    // Keeping

    @Test
    fun claimOutcomesNameWhatTheSheetDoesNext() {
        val kept = UUID.randomUUID()
        val json = """
            {"results":[
              {"id":"$linkId","outcome":"kept","packageId":"${kept.uuidString}"},
              {"id":"22222222222A","outcome":"already","packageId":"${kept.uuidString}"},
              {"id":"22222222222B","outcome":"quota"},
              {"id":"22222222222C","outcome":"unavailable"}
            ]}
        """.trimIndent()
        val response = ApiJson.decodeFromString(ClaimParcelsResponse.serializer(), json)

        assertEquals(ParcelLinkClaim.Added(kept), ParcelLinkClaim(response = response, linkId = linkId))
        assertEquals(ParcelLinkClaim.Already(kept), ParcelLinkClaim(response = response, linkId = "22222222222A"))
        assertEquals(ParcelLinkClaim.Full, ParcelLinkClaim(response = response, linkId = "22222222222B"))
        assertEquals(ParcelLinkClaim.Unavailable, ParcelLinkClaim(response = response, linkId = "22222222222C"))
        assertNull(ParcelLinkClaim(response = response, linkId = "22222222222D"))
        assertNull(ParcelLinkClaim(response = ClaimParcelsResponse(results = emptyList()), linkId = linkId))
    }

    /** Swift compares link ids canonically: one written with the Kelvin sign, U+212A, is the same link. */
    @Test
    fun aLinkIdWrittenWithTheKelvinSignIsTheSameLink() {
        val plain = "k7Qm2xHd9RtK"
        val kelvin = "k7Qm2xHd9Rt\u212A"
        val response = ApiJson.decodeFromString(ClaimParcelsResponse.serializer(), """{"results":[{"id":"$plain","outcome":"quota"}]}""")
        assertEquals(ParcelLinkClaim.Full, ParcelLinkClaim(response = response, linkId = kelvin))

        // The link kept for after sign-in is settled by the same link written the other way.
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> null })
        store.open(ParcelLinkRoute(plain))
        store.rememberForSignIn()
        assertEquals(plain, store.remembered?.id)
        store.open(ParcelLinkRoute(kelvin))
        store.close()
        assertNull(store.remembered)
    }

    @Test
    fun anAddedParcelClosesTheSheetAndReachesTheListOnceItHasGone() {
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> null })
        val kept = UUID.randomUUID()
        store.open(ParcelLinkRoute(linkId, name = "Moon lamp"))

        store.resolve(ParcelLinkClaim.Added(kept))
        assertNull(store.route)
        assertNull("The list waits for the sheet to leave the screen", store.arrival)
        store.dismissed()
        assertEquals(ParcelLinkStore.Arrival.Added(kept), store.arrival)
        store.consumeArrival()
        assertNull(store.arrival)
        store.dismissed()
        assertNull("An arrival is announced once", store.arrival)
    }

    @Test
    fun giftWordsArriveFromTheServiceAfterDelivery() = runTest {
        var answer = fixture().publicParcel
        answer = answer.copy(link = answer.link.copy(gift = true))
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> answer })
        store.open(ParcelLinkRoute(linkId))
        store.load()
        assertNull(store.route?.name)
        assertNull(store.route?.note)
        assertNull(store.route?.from)
        val events = answer.parcel.trackingEvents
        answer = answer.copy(
            parcel = answer.parcel.copy(trackingEvents = listOf(events[0].copy(stage = TrackingStage.Delivered)) + events.drop(1)),
            link = answer.link.copy(giftWords = GiftWords(name = "Trail shoes", note = "Happy birthday!", from = "Sam")),
        )
        store.load()
        assertEquals("Trail shoes", store.route?.name)
        assertEquals("Happy birthday!", store.route?.note)
        assertEquals("Sam", store.route?.from)
    }

    @Test
    fun otherOutcomesStayOnTheSheet() = runTest {
        val shown = fixture().publicParcel
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> shown })
        val existing = UUID.randomUUID()
        store.open(ParcelLinkRoute(linkId))
        store.load()
        assertEquals(ParcelLinkStore.Phase.Shown(shown), store.phase)

        store.resolve(ParcelLinkClaim.Full)
        assertEquals(ParcelLinkClaim.Full, store.claim)
        assertNotNull(store.route)

        store.resolve(ParcelLinkClaim.Already(existing))
        assertEquals(ParcelLinkClaim.Already(existing), store.claim)
        assertNotNull(store.route)
        store.openExisting(existing)
        assertNull(store.route)
        store.dismissed()
        assertEquals(ParcelLinkStore.Arrival.Open(existing), store.arrival)
        store.consumeArrival()

        store.open(ParcelLinkRoute(linkId))
        assertNull("A link opened again starts afresh", store.claim)
        store.resolve(ParcelLinkClaim.Unavailable)
        assertEquals(ParcelLinkStore.Phase.Unavailable, store.phase)
        assertNotNull(store.route)
        store.close()
        store.dismissed()
        assertNull(store.arrival)
    }

    // Reading

    /** Swift's `URLError`s are the `IOException`s OkHttp throws; a call the app cancelled ends in a `CancellationException`. */
    @Test
    fun readingALinkShowsItOrSaysWhyNot() = runTest {
        val shown = fixture().publicParcel
        var answer: Result<PublicParcelResponse?> = Result.success(shown)
        val asked = mutableListOf<String>()
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { id, _ ->
            asked.add(id)
            answer.getOrThrow()
        })

        store.load()
        assertTrue("Nothing is read until a link opens", asked.isEmpty())

        store.open(ParcelLinkRoute(linkId, name = "Moon lamp"))
        assertEquals(ParcelLinkStore.Phase.Loading, store.phase)
        store.load()
        assertEquals(ParcelLinkStore.Phase.Shown(shown), store.phase)
        assertEquals("Only the id is sent, never the name", listOf(linkId), asked)

        // A failed refresh keeps the parcel on screen.
        answer = Result.failure(UnknownHostException())
        store.load()
        assertEquals(ParcelLinkStore.Phase.Shown(shown), store.phase)
        assertEquals(ParcelLinkFailure.Offline, store.refreshFailure)
        answer = Result.success(shown)
        store.load()
        assertNull(store.refreshFailure)

        // A link that stops leading anywhere replaces it.
        answer = Result.success(null)
        store.load()
        assertEquals(ParcelLinkStore.Phase.Unavailable, store.phase)

        for ((error, failure) in listOf(
            SocketTimeoutException() to ParcelLinkFailure.Offline,
            DeliveryApiError.RateLimited(30.0) to ParcelLinkFailure.RateLimited,
            DeliveryApiError.ServiceFailed(502) to ParcelLinkFailure.Service,
            DeliveryApiError.InvalidResponse() to ParcelLinkFailure.Service,
        )) {
            store.open(ParcelLinkRoute(linkId))
            answer = Result.failure(error)
            store.load()
            assertEquals(ParcelLinkStore.Phase.Failed(failure), store.phase)
        }
        assertEquals("error.connection", ParcelLinkFailure.Offline.messageKey)
        assertEquals("error.rateLimited", ParcelLinkFailure.RateLimited.messageKey)
        assertNull(ParcelLinkFailure(CancellationException()))
        assertNull(ParcelLinkFailure(CancellationException("Canceled")))
    }

    /** Swift compares routes canonically: words the service writes another way leave the route as it is. */
    @Test
    fun wordsWrittenAnotherWayLeaveTheRouteAsItIs() = runTest {
        var answer = fixture().publicParcel
        val events = answer.parcel.trackingEvents
        answer = answer.copy(
            parcel = answer.parcel.copy(trackingEvents = listOf(events[0].copy(stage = TrackingStage.Delivered)) + events.drop(1)),
            link = answer.link.copy(gift = true, giftWords = GiftWords(name = "Cafe\u0301 lamp", note = "Bonne anne\u0301e", from = "Zoe\u0308")),
        )
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> answer })
        val opened = ParcelLinkRoute(linkId, name = "Caf\u00E9 lamp", note = "Bonne ann\u00E9e", from = "Zo\u00EB")
        store.open(opened)
        store.load()
        assertSame(opened, store.route)
        assertSame(opened, store.routeFlow.value)

        answer = answer.copy(link = answer.link.copy(giftWords = GiftWords(name = "Cafe lamp", note = "Bonne anne\u0301e", from = "Zoe\u0308")))
        store.load()
        assertEquals("Cafe lamp", store.route?.name)
        assertEquals("Bonne anne\u0301e", store.route?.note)
    }

    /** The app's API client stands in for `DeliveryAPIClient.publicParcel`, failing as a request to links.test would. */
    @Test
    fun aBuildWithoutAServerNeverReadsALink() = runTest {
        val demo = AppConfiguration(
            mode = AppConfiguration.Mode.Demo, apiBaseUrl = "https://links.test", supabaseUrl = null, supabasePublishableKey = "",
            googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "links.test",
        )
        val store = ParcelLinkStore(defaults = MemoryDefaults(), configuration = demo, api = { _, _ -> throw IOException("links.test") })
        store.open(ParcelLinkRoute(linkId))
        store.load()
        // A request to this host would fail, and read as trouble rather than as a link that leads nowhere.
        assertEquals(ParcelLinkStore.Phase.Unavailable, store.phase)
    }

    /** The request itself is the app's (OkHttp); this keeps how its answers read. */
    @Test
    fun theLinkIsReadWithoutSignInCookiesOrAnOwnerKey() {
        val served = JsonEncoder.deliveryTracker.encode(PublicParcelResponse.serializer(), fixture().publicParcel)
        val shown = ApiJson.decodeFromString(PublicParcelResponse.serializer(), served.decodeToString())
        assertEquals(fixture().publicParcel, shown)
        assertEquals(linkId, shown.link.id)
        assertEquals("••• 3456", shown.parcel.numberHint?.masked)

        assertEquals(12.0, DeliveryApiClient.retryAfterSeconds("12"), 0.0)
        for (error in listOf(DeliveryApiError.ServiceFailed(503), DeliveryApiError.InvalidResponse())) {
            assertEquals(ParcelLinkFailure.Service, ParcelLinkFailure(error))
        }
    }

    /** The request itself is the app's (OkHttp); this keeps the body it sends. */
    @Test
    fun aRefusalIsAskedWithoutSignInOrCookies() {
        val question = NumberRefusalRequest(trackingNumber = "SYNTHREFUSAL01", carrier = CarrierId.NzCouriers)
        assertEquals(
            """{"carrier":"nz-couriers","trackingNumber":"SYNTHREFUSAL01"}""",
            body(JsonEncoder.deliveryTracker.encode(NumberRefusalRequest.serializer(), question)),
        )
    }

    /** Keeping is `ParcelStore.keep`, which comes with the store's port; this keeps its request and how it reads the answer. */
    @Test
    fun keepingSendsTheSuggestedNameUnderTheAccountAndReloadsTheList() {
        val kept = UUID.randomUUID()
        // `ParcelStore.keep` asks for the link with its key and its name as the parcel's.
        fun claimBody(link: ParcelLinkRoute): String = body(
            JsonEncoder.deliveryTracker.encode(
                ClaimParcelsRequest.serializer(),
                ClaimParcelsRequest(links = listOf(ClaimParcelLink(id = link.id, key = link.key, label = link.name))),
            ),
        )
        assertEquals("""{"links":[{"id":"k7Qm2xHd9RtW","label":"Moon lamp"}]}""", claimBody(ParcelLinkRoute(linkId, name = "Moon lamp")))
        assertEquals("""{"links":[{"id":"k7Qm2xHd9RtW"}]}""", claimBody(ParcelLinkRoute(linkId)))

        for ((outcome, claim) in listOf("kept" to ParcelLinkClaim.Added(kept), "quota" to ParcelLinkClaim.Full)) {
            val id = if (outcome == "quota") "" else ""","packageId":"${kept.uuidString}""""
            val answer = ApiJson.decodeFromString(ClaimParcelsResponse.serializer(), """{"results":[{"id":"k7Qm2xHd9RtW","outcome":"$outcome"$id}]}""")
            assertEquals(claim, ParcelLinkClaim(response = answer, linkId = linkId))
        }
    }

    // Across sign-in

    @Test
    fun aLinkOpenedBeforeSigningInComesBackOnceForSevenDays() {
        val defaults = MemoryDefaults()
        var now = Instant.ofEpochSecond(1_790_000_000)
        val link = ParcelLinkRoute(linkId, name = "Moon lamp")
        val store = ParcelLinkStore(defaults = defaults, now = { now }, read = { _, _ -> null })

        store.open(link)
        store.resume()
        assertNull("Looking at a link does not remember it", store.remembered)

        store.rememberForSignIn()
        assertNull("The sheet makes way for sign-in", store.route)

        // The app may be relaunched by the sign-in round trip.
        now = now.plusSeconds(6 * 86_400)
        val relaunched = ParcelLinkStore(defaults = defaults, now = { now }, read = { _, _ -> null })
        assertNull("Nothing reopens until someone is signed in", relaunched.route)
        relaunched.resume()
        assertEquals(link, relaunched.route)
        assertEquals(ParcelLinkStore.Phase.Loading, relaunched.phase)

        // Added: the link is settled.
        relaunched.resolve(ParcelLinkClaim.Added(UUID.randomUUID()))
        assertNull(relaunched.remembered)
        relaunched.resume()
        assertNull(relaunched.route)
    }

    @Test
    fun aRememberedLinkExpiresAndIsDroppedOnceSettled() {
        val defaults = MemoryDefaults()
        var now = Instant.ofEpochSecond(1_790_000_000)
        val link = ParcelLinkRoute(linkId)
        val other = ParcelLinkRoute("22222222222A")
        val store = ParcelLinkStore(defaults = defaults, now = { now }, read = { _, _ -> null })
        fun remember() {
            store.open(link)
            store.rememberForSignIn()
            assertEquals(link, store.remembered)
        }

        remember()
        now = now.plusSeconds(7 * 86_400)
        assertNull("Seven days on, the link is forgotten", store.remembered)
        store.resume()
        assertNull(store.route)
        assertNull(defaults.values["sdt.pendingParcelLink.v1"])

        // A clock set back does not keep a link alive.
        remember()
        now = now.minusSeconds(60)
        assertNull(store.remembered)
        now = now.plusSeconds(60)

        // Closing another link leaves the remembered one alone; closing it forgets it.
        remember()
        store.open(other)
        store.close()
        assertEquals(link, store.remembered)
        store.resume()
        assertEquals(link, store.route)
        store.close()
        assertNull(store.remembered)

        // So does an answer that settles it.
        for (claim in listOf(ParcelLinkClaim.Already(UUID.randomUUID()), ParcelLinkClaim.Unavailable)) {
            remember()
            store.resume()
            store.resolve(claim)
            assertNull(store.remembered)
            store.close()
        }
        // No room yet: the link stays for another try.
        remember()
        store.resume()
        store.resolve(ParcelLinkClaim.Full)
        assertEquals(link, store.remembered)

        // Only a well-formed link is ever restored.
        defaults.set("""{"id":"not-a-link","receivedAt":0}""".toByteArray(), forKey = "sdt.pendingParcelLink.v1")
        assertNull(store.remembered)
        assertNull(defaults.values["sdt.pendingParcelLink.v1"])
    }

    // Following on this phone

    @Test
    fun aHandedOverLinkIsReadWithItsKeyWhichIsNeverRemembered() = runTest {
        val defaults = MemoryDefaults()
        val key = "K".repeat(43)
        var answer = fixture().publicParcel
        answer = answer.copy(link = answer.link.copy(role = ParcelLinkRole.Owner))
        val sent = mutableListOf<String?>()
        val store = ParcelLinkStore(defaults = defaults, read = { _, key ->
            sent.add(key)
            answer
        })

        store.open(ParcelLinkRoute(linkId, name = "Moon lamp", key = key))
        store.load()
        assertEquals(listOf(key), sent)
        assertEquals("The owner's link keeps its key, for the parcel to come along", key, store.route?.key)

        // Kept for after sign-in, the link goes without its key.
        store.rememberForSignIn()
        assertEquals("Moon lamp", store.remembered?.name)
        assertNull(store.remembered?.key)
        val pending = defaults.data(forKey = "sdt.pendingParcelLink.v1")!!
        assertFalse(pending.decodeToString().contains(key))

        // A key the link does not answer to as its owner's is dropped, and is not sent again.
        answer = answer.copy(link = answer.link.copy(role = ParcelLinkRole.Viewer))
        store.open(ParcelLinkRoute(linkId, key = key))
        store.load()
        assertNull(store.route?.key)
        store.load()
        assertEquals(listOf(key, key, null), sent)
    }

    /** Alerts are `DeviceParcelClient`'s, over OkHttp in the app; this keeps the bodies it sends. */
    @Test
    fun appAlertsGoWithTheKeyOnlyForTheOwnerAndStopWithTheTokenAlone() {
        val alert = ParcelAppAlertRequest(
            token = "a1b2c3d4", environment = NativePushEnvironment.Production, preset = ParcelAlertPreset.All, locale = NativePushLocale.Fr,
        )
        assertEquals(
            """{"environment":"production","locale":"fr","preset":"all","token":"a1b2c3d4"}""",
            body(JsonEncoder.deliveryTracker.encode(ParcelAppAlertRequest.serializer(), alert)),
        )
        assertEquals(
            """{"token":"a1b2c3d4"}""",
            body(JsonEncoder.deliveryTracker.encode(DeleteParcelAppAlertRequest.serializer(), DeleteParcelAppAlertRequest(token = "a1b2c3d4"))),
        )
    }
}
