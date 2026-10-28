package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.GiftWords
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.ParcelShareResponse
import com.plhery.peekdeliverytracker.core.api.PublicLookupResponse
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.SecureRandom
import java.time.Instant
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.asKotlinRandom
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.yield
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

/** ParcelShareTests.swift. A sheet's `Client` is a stand-in service here, as on iOS. */
class ParcelShareTests {
    private val linkId = "g8Rn3yJe2SuX"
    private val base = "https://peektracker.com"

    @Serializable
    private class Fixture(
        val publicParcel: PublicParcelResponse,
        val publicLookup: PublicLookupResponse,
        val publicGiftParcel: PublicParcelResponse,
        val parcelShare: ParcelShareResponse,
    )

    private fun fixture(): Fixture = ApiJson.decodeFromString(Fixture.serializer(), SharedResources.text("ContractFixtures.json"))

    private fun event(stage: TrackingStage, description: String, location: String?, at: String): TrackingEvent =
        TrackingEvent(id = UUID.randomUUID(), packageId = UUID.randomUUID(), stage = stage, description = description, location = location, occurredAt = at)

    /** Swift's `URL.fragment`: nil without a `#`. */
    private fun fragment(address: String?): String? = address?.let { Url.parse(it)?.fragment }

    // Contract

    @Test
    fun decodesTheGiftAndShareFixtures() {
        val fixture = fixture()

        val gift = fixture.publicGiftParcel
        assertEquals(linkId, gift.link.id)
        assertEquals(ParcelLinkRole.Viewer, gift.link.role)
        assertEquals(ParcelLinkKind.Shared, gift.link.kind)
        assertEquals(true, gift.link.gift)
        assertEquals(true, gift.link.shared)
        assertEquals(true, gift.link.alerts?.available)
        assertNull(gift.link.forgetAt)
        assertFalse(gift.link.numberShown)
        assertFalse(gift.link.canKeep)
        assertNull(gift.parcel.trackingNumber)
        assertEquals("••• 6789", gift.parcel.numberHint?.masked)
        assertNull(gift.parcel.lastStatusText)
        assertNull(gift.parcel.carrierData.senderName)
        assertEquals(listOf("With the courier for delivery", "Left the sender", "Left the sender"), gift.parcel.trackingEvents.map { it.description })
        assertEquals("DE", gift.parcel.trackingEvents.lastOrNull()?.location)
        assertEquals(EventPlacePrecision.Country, gift.parcel.trackingEvents.lastOrNull()?.place?.precision)

        assertEquals(ParcelShare(id = linkId, showNumber = false, gift = true, createdAt = "2026-10-01T08:00:00.000Z"), fixture.parcelShare.link)
        assertNull(ApiJson.decodeFromString(ParcelShareResponse.serializer(), """{"link":null}""").link)

        // A lookup's owner reads whether viewers see the whole number; older answers say nothing.
        assertEquals(false, fixture.publicLookup.link.showNumber)
        assertNull(fixture.publicParcel.link.showNumber)
        assertNull(fixture.publicParcel.link.gift)
    }

    @Test
    fun shareRequestsSendOnlyTheTwoSwitches() {
        val both = JsonEncoder.deliveryTracker.encode(ShareParcelRequest.serializer(), ShareParcelRequest(showNumber = true, gift = false))
        assertEquals("""{"gift":false,"showNumber":true}""", both.decodeToString())
        assertEquals("{}", JsonEncoder.deliveryTracker.encode(ShareParcelRequest.serializer(), ShareParcelRequest()).decodeToString())
    }

    @Test
    fun theBlurredDescriptionIsTranslated() {
        val localizer = Localizer(AppLanguage.En)
        assertEquals("Left the sender", localizer.eventDescription(TrackingEvent.giftOriginDescription))
        assertEquals(localizer.text("event.gift.leftSender"), localizer.eventDescription(TrackingEvent.giftOriginDescription))
        for (language in AppLanguage.entries.filter { it != AppLanguage.En }) {
            localizer.language = language
            val said = localizer.eventDescription(TrackingEvent.giftOriginDescription)
            assertEquals(language.rawValue, localizer.text("event.gift.leftSender"), said)
            assertNotEquals(language.rawValue, "Left the sender", said)
            assertNotEquals(language.rawValue, "event.gift.leftSender", said)
        }
    }

    // The link's words

    @Test
    fun aLinkCarriesItsNameNoteAndSignatureAfterTheHash() {
        fun route(fragment: String): ParcelLinkRoute? =
            ParcelLinkRoute(url = "https://peektracker.com/p/$linkId$fragment", baseUrl = base, linkHosts = emptyList())
        assertEquals(
            ParcelLinkRoute(linkId, name = "New sneakers", note = "Happy birthday, Alex!", from = "Sam"),
            route("#n=New%20sneakers&g=Happy%20birthday%2C%20Alex!&f=Sam"),
        )
        assertEquals(ParcelLinkRoute(linkId, note = "Enjoy", from = "Sam"), route("#f=Sam&g=Enjoy"))
        assertEquals(ParcelLinkRoute(linkId, note = "Café ☕"), route("#g=Caf%C3%A9%20%E2%98%95"))
        assertEquals(ParcelLinkRoute(linkId, name = "Lamp"), route("#n=Lamp&g=&f=%20"))
        // A part that cannot be decoded is left out; the others stay.
        assertEquals(ParcelLinkWords(from = "Sam"), ParcelLinkWords(fragment = "g=%ZZ&f=Sam"))
        assertEquals(ParcelLinkRoute(linkId), route("?g=Query&f=Query"))
        assertEquals(
            ParcelLinkRoute(linkId, note = "Hi", from = "Sam"),
            ParcelLinkRoute(url = "peekdeliverytracker://p/$linkId#g=Hi&f=Sam", baseUrl = base, linkHosts = emptyList()),
        )
        // Longer keys are someone else's.
        assertEquals(ParcelLinkRoute(linkId), route("#gift=1&from=Sam&note=x"))
    }

    @Test
    fun theWordsAreCleanedToWhatALinkMayCarry() {
        // One line, without control characters, runs of blanks told once.
        assertEquals("Line one two three", ParcelLinkWords(fragment = "g=%20Line%0Aone%09%09two%20%20three%20").note)
        assertEquals("Sam Lee", ParcelLinkWords(fragment = "f=Sam%E2%80%8B%00Lee").from)
        // A note holds 280 characters and a signature 60, as the site counts them.
        assertEquals("a".repeat(280), ParcelLinkWords(fragment = "g=" + "a".repeat(400)).note)
        assertEquals("b".repeat(60), ParcelLinkWords(fragment = "f=" + "b".repeat(100)).from)
        assertEquals("🎁".repeat(60), ParcelLinkWords(fragment = "f=" + "%F0%9F%8E%81".repeat(80)).from)
        assertEquals(280, ParcelLinkWords(fragment = "g=" + "%C3%A9".repeat(300)).note?.characterCount)
        // A cut never leaves a blank at the end.
        assertEquals("c".repeat(59), ParcelLinkWords(fragment = "f=" + "c".repeat(59) + "%20dd").from)
        // The name keeps its own measure: what an account's parcel name holds.
        assertEquals(80, ParcelLinkWords(fragment = "n=" + "%F0%9F%93%A6".repeat(50)).name?.length)
        assertEquals("Moon lamp", ParcelLinkWords.cleanName("  Moon \n lamp "))
        assertNull(ParcelLinkWords.clean(" \n\t ", limit = 10))
        assertEquals(ParcelLinkWords(), ParcelLinkWords(fragment = null))
        assertEquals(ParcelLinkWords(), ParcelLinkWords(fragment = ""))
    }

    @Test
    fun theAddressIsBuiltOnTheSiteAsTheSiteBuildsIt() {
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX", ParcelLinkRoute.address(id = linkId, baseUrl = base))
        val words = ParcelLinkWords(name = "New sneakers 👟", note = "Happy birthday, Alex! (It's me) & co #1 100%", from = "Sam & Léa")
        val address = ParcelLinkRoute.address(id = linkId, words = words, baseUrl = base)
        // The same escapes as JavaScript's encodeURIComponent, in the order n, g, f.
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX#n=New%20sneakers%20%F0%9F%91%9F", address)
        // What is written is what is read back.
        assertEquals(ParcelLinkRoute(linkId, name = words.name), ParcelLinkRoute(url = address, baseUrl = base, linkHosts = emptyList()))
        // Words are cleaned on their way out, and empty ones left out.
        assertEquals(
            "https://peektracker.com/p/g8Rn3yJe2SuX",
            ParcelLinkRoute.address(id = linkId, words = ParcelLinkWords(name = " ", note = "a\nb", from = ""), baseUrl = base),
        )
        // A development site keeps its port; a query never travels.
        assertEquals(
            "http://localhost:3000/p/g8Rn3yJe2SuX#n=Lamp",
            ParcelLinkRoute.address(id = linkId, words = ParcelLinkWords(name = "Lamp"), baseUrl = "http://localhost:3000"),
        )
    }

    @Test
    fun aMadeUpIdHasTheFormatOfARealOne() {
        val generator = SecureRandom().asKotlinRandom()
        val ids = (0 until 50).map { ParcelLinkRoute.madeUpId(generator) }
        assertTrue(ids.all(ParcelLinkRoute::validId))
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun aHiddenNumberShowsOnlyItsEndByTheServicesRule() {
        assertEquals("••• 899", ParcelNumberHint(hiding = "1234567890899").masked)
        assertEquals("••• 3456", ParcelNumberHint(hiding = "TESTPARCEL983456").masked)
        assertEquals("••• 7890", ParcelNumberHint(hiding = "TESTPARCEL9834567890").masked)
        assertEquals("••• 99", ParcelNumberHint(hiding = "1234567899").masked)
        assertEquals(ParcelNumberHint(head = "", tail = "E"), ParcelNumberHint(hiding = "ABCDE"))
        assertEquals(ParcelNumberHint(head = "", tail = ""), ParcelNumberHint(hiding = "AB"))
        // An answer from a service that still shows the start reads as it did.
        assertEquals("123 ••• 99", ParcelNumberHint(head = "123", tail = "99").masked)
    }

    // A gift

    @Test
    fun aGiftIsWrappedUntilItIsDeliveredExceptForItsSender() {
        var link = fixture().publicGiftParcel.link
        for (stage in TrackingStage.entries.filter { it != TrackingStage.Delivered }) {
            assertEquals(stage.rawValue, ParcelLinkGift.Wrapped, ParcelLinkGift(link = link, stage = stage))
        }
        assertEquals(ParcelLinkGift.Wrapped, ParcelLinkGift(link = link, stage = null))
        assertEquals(ParcelLinkGift.Opened, ParcelLinkGift(link = link, stage = TrackingStage.Delivered))

        link = link.copy(role = ParcelLinkRole.Owner)
        assertEquals(ParcelLinkGift.Own, ParcelLinkGift(link = link, stage = TrackingStage.InTransit))
        assertEquals(ParcelLinkGift.Own, ParcelLinkGift(link = link, stage = TrackingStage.Delivered))

        // A link that is no gift, or says nothing about it, shows none of this.
        link = link.copy(role = ParcelLinkRole.Viewer, gift = false)
        assertNull(ParcelLinkGift(link = link, stage = TrackingStage.InTransit))
        link = link.copy(gift = null)
        assertNull(ParcelLinkGift(link = link, stage = TrackingStage.Delivered))
    }

    @Test
    fun theAnswerSaysHowItsGiftIsShown() {
        var answer = fixture().publicGiftParcel
        assertEquals(ParcelLinkGift.Wrapped, answer.gift)

        val packageId = answer.parcel.id
        val delivered = TrackingEvent(
            id = UUID.randomUUID(), packageId = packageId, stage = TrackingStage.Delivered, description = "Delivered",
            location = "Zürich, CH", occurredAt = "2026-10-02T12:12:00+00:00",
        )
        answer = answer.copy(parcel = answer.parcel.copy(trackingEvents = listOf(delivered) + answer.parcel.trackingEvents))
        assertEquals(ParcelLinkGift.Opened, answer.gift)

        answer = answer.copy(link = answer.link.copy(role = ParcelLinkRole.Owner))
        assertEquals(ParcelLinkGift.Own, answer.gift)
        assertNull(fixture().publicParcel.gift)
    }

    @Test
    fun blurredRowsThatFollowOneAnotherAreToldOnce() {
        val fixture = fixture().publicGiftParcel.parcel.trackingEvents
        val collapsed = fixture.collapsingGiftRows()
        assertEquals("The newest of the run stays, in the order given", listOf(fixture[0].id, fixture[1].id), collapsed.map { it.id })
        assertEquals(listOf(TrackingStage.OutForDelivery, TrackingStage.InTransit), collapsed.map { it.stage })

        val blurred = TrackingEvent.giftOriginDescription
        val journey = listOf(
            event(TrackingStage.Accepted, blurred, "DE", at = "2026-10-01T08:00:00Z"),
            event(TrackingStage.InTransit, blurred, "DE", at = "2026-10-01T10:00:00Z"),
            event(TrackingStage.InTransit, blurred, null, at = "2026-10-01T12:00:00Z"),
            event(TrackingStage.InTransit, blurred, null, at = "2026-10-01T13:00:00Z"),
            event(TrackingStage.Customs, "Cleared customs", "Basel, CH", at = "2026-10-01T20:00:00Z"),
            event(TrackingStage.InTransit, blurred, "DE", at = "2026-10-01T21:00:00Z"),
            event(TrackingStage.OutForDelivery, "Out for delivery", "Zürich, CH", at = "2026-10-02T06:00:00Z"),
            event(TrackingStage.OutForDelivery, "Out for delivery", "Zürich, CH", at = "2026-10-02T07:00:00Z"),
        )
        // Runs are found in the order of the journey, whatever order the answer came in.
        for (order in listOf(
            journey, journey.reversed(),
            listOf(journey[3], journey[0], journey[6], journey[1], journey[7], journey[5], journey[2], journey[4]),
        )) {
            val kept = order.collapsingGiftRows().map { it.id }.toSet()
            assertEquals(listOf(journey[1], journey[3], journey[4], journey[5], journey[6], journey[7]).map { it.id }.toSet(), kept)
            assertEquals(order.map { it.id }.filter { it in kept }, order.collapsingGiftRows().map { it.id })
        }
        // Nothing blurred, nothing dropped.
        assertEquals(journey.drop(6), journey.drop(6).collapsingGiftRows())
        assertEquals(emptyList<TrackingEvent>(), emptyList<TrackingEvent>().collapsingGiftRows())
    }

    // A stopped link

    @Test
    fun aLinkWhoseSharingStoppedSaysSo() = runTest {
        val shown = fixture().publicGiftParcel
        var answer: Result<PublicParcelResponse?> = Result.failure(ParcelLinkStopped())
        val store = ParcelLinkStore(defaults = MemoryDefaults(), read = { _, _ -> answer.getOrThrow() })
        val link = ParcelLinkRoute(linkId, name = "Lamp", note = "Enjoy", from = "Sam")

        store.open(link)
        store.load()
        assertEquals(ParcelLinkStore.Phase.Stopped, store.phase)
        assertNotEquals("A stopped link is not a forgotten one", ParcelLinkStore.Phase.Unavailable, store.phase)
        assertNull(store.refreshFailure)
        assertNotNull("The sheet stays to say so", store.route)

        // A parcel on screen makes way when its sharing stops.
        answer = Result.success(shown)
        store.open(link)
        store.load()
        assertEquals(ParcelLinkStore.Phase.Shown(shown), store.phase)
        answer = Result.failure(ParcelLinkStopped())
        store.load()
        assertEquals(ParcelLinkStore.Phase.Stopped, store.phase)

        // A link kept for after sign-in is settled by it, with the words it carried.
        store.open(link)
        store.rememberForSignIn()
        assertEquals(link, store.remembered)
        store.resume()
        assertEquals(link, store.route)
        store.load()
        assertEquals(ParcelLinkStore.Phase.Stopped, store.phase)
        assertNull(store.remembered)
    }

    @Test
    fun theServiceAnswersGoneForAStoppedLink() = runTest {
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://share.test", supabaseUrl = "https://share.test", supabasePublishableKey = "public",
            googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true, appGroupIdentifier = "share.test",
        )
        var response = jsonResponse(410, """{"error":"Parcel not shared"}""")
        val transport = RecordingTransport { response }
        try {
            DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
            fail("Expected a stopped link")
        } catch (error: Exception) {
            assertTrue(error is ParcelLinkStopped)
        }
        response = jsonResponse(404, """{"error":"Parcel unavailable"}""")
        val gone = DeliveryApiClient.publicParcel(linkId, configuration = configuration, transport = transport)
        assertNull(gone)
    }

    // The share sheet

    /** A parcel's link as a service would keep it, with every call noted. */
    private class Service {
        var link: ParcelShare? = null
        val calls = mutableListOf<String>()
        var failure: Exception? = null

        /** Holds the next change until the test lets it through. */
        var gate: CompletableDeferred<Unit>? = null
        var gated = false
        private var made = 0

        val client: ParcelShareModel.Client
            get() = ParcelShareModel.Client(
                current = {
                    calls.add("GET")
                    failure?.let { throw it }
                    link
                },
                share = { settings ->
                    calls.add("PUT ${settings.showNumber} ${settings.gift}")
                    if (gated) {
                        val gate = CompletableDeferred<Unit>()
                        this.gate = gate
                        gate.await()
                    }
                    failure?.let { throw it }
                    if (link == null) {
                        made += 1
                        link = ParcelShare(
                            id = "2222222222${made}A".replace("1", "B"), showNumber = false, gift = false, createdAt = "2026-10-02T08:00:00.000Z",
                        )
                    }
                    link = link!!.copy(showNumber = settings.showNumber, gift = settings.gift)
                    settings.giftWords?.let { link = link!!.copy(giftWords = it) }
                    link!!
                },
                stop = {
                    calls.add("DELETE")
                    failure?.let { throw it }
                    link = null
                },
            )
    }

    private fun model(service: Service, parcelId: UUID = UUID.randomUUID(), name: String? = "New sneakers", defaults: UserDefaults): ParcelShareModel =
        ParcelShareModel(parcelId = parcelId, name = name, client = service.client, notes = ParcelShareNotes(defaults), baseUrl = base)

    @Test
    fun theLinkIsMadeOnTheFirstShareOrCopy() = runTest {
        val defaults = MemoryDefaults()
        val service = Service()
        val model = model(service, defaults = defaults)
        assertTrue(model.loading)

        model.load()
        assertFalse(model.loading)
        assertNull(model.link)
        assertNull(model.address)
        assertEquals("Opening the sheet makes nothing", listOf("GET"), service.calls)

        // Before there is a link, the switches only say what the next one will show.
        model.set(ParcelShareModel.Field.ShowNumber, to = true)
        model.set(ParcelShareModel.Field.Gift, to = true)
        model.words = model.words.copy(name = true)
        model.words = model.words.copy(note = "Enjoy")
        assertEquals(ParcelShareModel.Settings(showNumber = true, gift = true), model.shown)
        assertEquals(listOf("GET"), service.calls)
        assertNull("Words are kept once a link carries them", ParcelShareNotes(defaults).words(model.parcelId))

        val address = model.addressToHandOut()
        assertEquals(listOf("GET", "PUT true true"), service.calls)
        assertEquals("https://peektracker.com/p/2222222222BA", address)
        assertEquals(address, model.address)
        assertEquals(ParcelShareWords(name = true, note = "Enjoy", from = ""), ParcelShareNotes(defaults).words(model.parcelId))

        // The same link is handed out from then on.
        val again = model.addressToHandOut()
        assertEquals(address, again)
        assertEquals(3, service.calls.size)
        assertEquals(GiftWords(name = "New sneakers", note = "Enjoy", from = null), service.link?.giftWords)
    }

    @Test
    fun giftCopyWaitsForTheMessageAndFailsWhenItCannotBeSaved() = runTest {
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = true, createdAt = "2026-10-01T08:00:00.000Z")
        val model = model(service, defaults = MemoryDefaults())
        model.load()
        model.words = ParcelShareWords(name = true, note = "Surprise!", from = "Sam")
        service.gated = true
        val copy = async { model.addressToHandOut() }
        while (service.gate == null) yield()
        assertTrue(model.working)
        assertNull(fragment(model.address))
        service.gate?.complete(Unit)
        service.gate = null
        val address = copy.await()
        assertNull(fragment(address))
        assertEquals(GiftWords(name = "New sneakers", note = "Surprise!", from = "Sam"), service.link?.giftWords)
        service.gated = false
        service.failure = DeliveryApiError.ServiceFailed(502)
        val failed = model.addressToHandOut()
        assertNull(failed)
        assertEquals("share.failed", model.failureKey)
    }

    @Test
    fun emptyGiftFieldsAreEncodedAsNull() {
        val body = Json.parseToJsonElement(JsonEncoder().encode(GiftWords.serializer(), GiftWords(note = "Enjoy")).decodeToString()).jsonObject
        assertSame(JsonNull, body["name"])
        assertSame(JsonNull, body["from"])
        assertEquals("Enjoy", body["note"]?.jsonPrimitive?.content)
    }

    @Test
    fun aSwitchIsShownAtOnceAndPutBackWhenTheServiceRefuses() = runTest {
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = false, createdAt = "2026-10-01T08:00:00.000Z")
        val model = model(service, defaults = MemoryDefaults())
        model.load()
        assertEquals(linkId, model.link?.id)
        assertEquals(ParcelShareModel.Settings(), model.shown)

        // On its way: shown, and marked as saving.
        service.gated = true
        val saving = launch { model.set(ParcelShareModel.Field.ShowNumber, to = true) }
        while (service.gate == null) yield()
        assertTrue(model.shown.showNumber)
        assertTrue(model.isSaving(ParcelShareModel.Field.ShowNumber))
        assertFalse(model.isSaving(ParcelShareModel.Field.Gift))
        service.gated = false
        service.gate?.complete(Unit)
        service.gate = null
        saving.join()
        assertTrue(model.shown.showNumber)
        assertFalse(model.isSaving(ParcelShareModel.Field.ShowNumber))
        assertNull(model.failureKey)
        assertEquals("Both switches are sent, as the sheet shows them", "PUT true false", service.calls.lastOrNull())

        // Refused: put back, with a message.
        for ((error, key) in listOf(
            UnknownHostException() to "error.connection",
            DeliveryApiError.RateLimited(10.0) to "error.rateLimited",
            DeliveryApiError.ServiceFailed(502) to "share.failed",
            DeliveryApiError.Service("Package not found") to "share.failed",
        )) {
            service.failure = error
            model.set(ParcelShareModel.Field.Gift, to = true)
            assertFalse(model.shown.gift)
            assertTrue("The other switch keeps what was saved", model.shown.showNumber)
            assertFalse(model.isSaving(ParcelShareModel.Field.Gift))
            assertEquals(key, model.failureKey)
        }
        assertEquals(false, service.link?.gift)

        // The next change clears the message.
        service.failure = null
        model.set(ParcelShareModel.Field.Gift, to = true)
        assertNull(model.failureKey)
        assertEquals(ParcelShareModel.Settings(showNumber = true, gift = true), model.shown)
        assertEquals(true, service.link?.gift)

        // A read that was cancelled says nothing.
        service.failure = CancellationException()
        model.set(ParcelShareModel.Field.Gift, to = false)
        assertNull(model.failureKey)
        assertTrue(model.shown.gift)
    }

    /**
     * A `withTimeout` around a call that runs out is a call that timed out, as `URLError.timedOut` is. A call the app
     * cancelled says nothing.
     */
    @Test
    fun aCallThatTimesOutSaysTheConnectionFailedAndOneTheAppCancelledSaysNothing() = runTest {
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = false, createdAt = "2026-10-01T08:00:00.000Z")
        var slow = false
        var hanging = false
        var entered = false
        val client = service.client
        val model = ParcelShareModel(
            parcelId = UUID.randomUUID(), name = "New sneakers", notes = ParcelShareNotes(MemoryDefaults()), baseUrl = base,
            client = ParcelShareModel.Client(
                current = client.current,
                share = { settings ->
                    entered = true
                    if (slow) withTimeout(15_000) { awaitCancellation() }
                    if (hanging) awaitCancellation()
                    client.share(settings)
                },
                stop = {
                    if (slow) withTimeout(15_000) { awaitCancellation() }
                    client.stop()
                },
            ),
        )
        model.load()
        slow = true
        model.set(ParcelShareModel.Field.Gift, to = true)
        assertEquals("error.connection", model.failureKey)
        assertFalse(model.shown.gift)
        model.stop()
        assertEquals("error.connection", model.failureKey)
        assertEquals(linkId, model.link?.id)

        slow = false
        hanging = true
        entered = false
        val saving = launch { model.set(ParcelShareModel.Field.Gift, to = true) }
        while (!entered) yield()
        saving.cancel()
        saving.join()
        assertNull(model.failureKey)
        assertFalse(model.isSaving(ParcelShareModel.Field.Gift))
    }

    /** Swift compares the words canonically: while only how they are written changes, the device keeps them as they were. */
    @Test
    fun wordsWrittenAnotherWayAreNotKeptAgain() = runTest {
        val defaults = MemoryDefaults()
        val notes = ParcelShareNotes(defaults)
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = true, createdAt = "2026-10-01T08:00:00.000Z")
        val parcelId = UUID.randomUUID()
        val model = model(service, parcelId = parcelId, defaults = defaults)
        model.load()
        model.words = model.words.copy(note = "Bonne ann\u00E9e")
        assertEquals("Bonne ann\u00E9e", notes.words(parcelId)?.note)
        model.words = model.words.copy(note = "Bonne anne\u0301e")
        assertEquals("What is typed shows as typed", "Bonne anne\u0301e", model.words.note)
        assertEquals("Bonne ann\u00E9e", notes.words(parcelId)?.note)
        model.words = model.words.copy(note = "Bonne anne\u0301e!")
        assertEquals("Bonne anne\u0301e!", notes.words(parcelId)?.note)
    }

    @Serializable
    private class Note(val note: String)

    /** A Swift String cannot hold a lone surrogate: what is typed and what is sent hold U+FFFD in its place. */
    @Test
    fun aLoneSurrogateIsTheReplacementCharacter() = runTest {
        assertEquals(ParcelShareWords(note = "a\uFFFDb", from = "\uFFFD"), ParcelShareWords(note = "a\uD800b", from = "\uDC00").limited)
        assertEquals("\uFFFD".repeat(60), ParcelShareWords(from = "\uDC00".repeat(70)).limited.from)

        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = true, createdAt = "2026-10-01T08:00:00.000Z")
        val parcelId = UUID.randomUUID()
        val defaults = MemoryDefaults()
        val model = model(service, parcelId = parcelId, defaults = defaults)
        model.load()
        model.words = model.words.copy(note = "a\uD800b")
        assertEquals("a\uFFFDb", model.words.note)
        assertEquals("a\uFFFDb", ParcelShareNotes(defaults).words(parcelId)?.note)

        // Swift's JSONEncoder writes it as UTF-8: EF BF BD.
        val bytes = JsonEncoder.deliveryTracker.encode(Note.serializer(), Note("a\uD800b"))
        assertEquals(listOf(0x61, 0xEF, 0xBF, 0xBD, 0x62), bytes.drop(9).take(5).map { it.toInt() and 0xFF })
        assertEquals("{\"note\":\"a\uFFFDb\"}", bytes.decodeToString())
        assertEquals("{\"note\":\"a\uFFFDb\"}", JsonEncoder.deliveryTracker.encode(Note.serializer(), Note("a\uDC00b")).decodeToString())
    }

    @Test
    fun theLinkCarriesTheNameOnlyWhenAskedAndAGiftsWordsOnlyForAGift() = runTest {
        val defaults = MemoryDefaults()
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = true, gift = false, createdAt = "2026-10-01T08:00:00.000Z")
        val model = model(service, defaults = defaults)
        model.load()
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX", model.address)

        model.words = model.words.copy(name = true)
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX#n=New%20sneakers", model.address)

        // A note typed for a gift stays off the link until the link is a gift.
        model.words = model.words.copy(note = "Happy birthday! ")
        model.words = model.words.copy(from = " Sam")
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX#n=New%20sneakers", model.address)
        model.set(ParcelShareModel.Field.Gift, to = true)
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX", model.address)
        assertEquals("What is being typed keeps its spaces", "Happy birthday! ", model.words.note)

        model.words = model.words.copy(name = false)
        assertEquals("https://peektracker.com/p/g8Rn3yJe2SuX", model.address)

        // What is typed stays within what a link may carry.
        model.words = model.words.copy(note = "a".repeat(300))
        assertEquals(280, model.words.note.characterCount)
        model.words = model.words.copy(from = "🎁".repeat(70))
        assertEquals(60, model.words.from.characterCount)
        assertEquals(280, ParcelShareNotes(defaults).words(model.parcelId)?.note?.characterCount)

        // A parcel without a name has none to carry.
        val unnamed = model(service, name = "  ", defaults = defaults)
        unnamed.load()
        unnamed.words = unnamed.words.copy(name = true)
        assertNull(unnamed.name)
        assertNull(unnamed.carried.name)
    }

    @Test
    fun theWordsOfALinkAreKeptOnTheDeviceAndShownAgain() = runTest {
        val defaults = MemoryDefaults()
        val notes = ParcelShareNotes(defaults)
        val service = Service()
        service.link = ParcelShare(id = linkId, showNumber = false, gift = true, createdAt = "2026-10-01T08:00:00.000Z")
        val parcelId = UUID.randomUUID()

        val first = model(service, parcelId = parcelId, defaults = defaults)
        first.load()
        assertEquals(ParcelShareWords(), first.words)
        first.words = first.words.copy(name = true)
        first.words = first.words.copy(note = "Enjoy  the trails\n")
        first.words = first.words.copy(from = "Sam")
        assertEquals("Kept clean", ParcelShareWords(name = true, note = "Enjoy the trails", from = "Sam"), notes.words(parcelId))

        // Opened again: the same words, and the same address.
        val second = model(service, parcelId = parcelId, defaults = defaults)
        second.load()
        assertEquals(ParcelShareWords(name = true, note = "Enjoy the trails", from = "Sam"), second.words)
        assertEquals(first.address, second.address)
        assertEquals(true, second.shown.gift)

        // Another parcel has its own.
        val other = model(service, parcelId = UUID.randomUUID(), defaults = defaults)
        other.load()
        assertEquals(ParcelShareWords(), other.words)

        // Without an answer the sheet shows what the device kept.
        service.failure = SocketTimeoutException()
        val offline = model(service, parcelId = parcelId, defaults = defaults)
        offline.load()
        assertFalse(offline.loading)
        assertNull(offline.link)
        assertEquals("Enjoy the trails", offline.words.note)
        service.failure = null

        // Words emptied are no longer kept.
        second.words = ParcelShareWords()
        assertNull(notes.words(parcelId))

        // A link stopped elsewhere takes its words with it.
        notes.remember(ParcelShareWords(name = true, note = "Old", from = ""), parcelId)
        service.link = null
        val later = model(service, parcelId = parcelId, defaults = defaults)
        later.load()
        assertEquals(ParcelShareWords(), later.words)
        assertNull(notes.words(parcelId))
    }

    @Test
    fun stoppingForgetsTheWordsAndSharingAgainMakesANewLink() = runTest {
        val defaults = MemoryDefaults()
        val notes = ParcelShareNotes(defaults)
        val service = Service()
        val model = model(service, defaults = defaults)
        model.load()
        model.set(ParcelShareModel.Field.Gift, to = true)
        model.words = ParcelShareWords(name = true, note = "Enjoy", from = "Sam")
        val first = model.addressToHandOut()
        assertNotNull(notes.words(model.parcelId))

        // A refusal leaves everything as it was.
        service.failure = DeliveryApiError.ServiceFailed(502)
        model.stop()
        assertFalse(model.stopped)
        assertEquals("share.failed", model.failureKey)
        assertEquals(first, model.address)
        assertNotNull(notes.words(model.parcelId))
        service.failure = null

        model.stop()
        assertTrue(model.stopped)
        assertNull(model.failureKey)
        assertNull(model.link)
        assertNull(model.address)
        assertEquals(ParcelShareWords(), model.words)
        assertNull(notes.words(model.parcelId))
        assertNull(service.link)
        assertFalse(model.working)

        service.failure = SocketTimeoutException()
        model.shareAgain()
        assertTrue(model.stopped)
        assertEquals("error.connection", model.failureKey)
        service.failure = null

        model.shareAgain()
        assertFalse(model.stopped)
        assertNull(model.failureKey)
        assertNotNull(model.link)
        assertNotEquals("A new link, with a new id", first, model.address)
        assertNull("It carries nothing the old one did", fragment(model.address))
        assertEquals("The switches stay as they were", true, model.shown.gift)
    }

    @Test
    fun theDevicesWordsAreKeptPerParcelAndForgotten() {
        val defaults = MemoryDefaults()
        val notes = ParcelShareNotes(defaults)
        val one = UUID.randomUUID()
        val two = UUID.randomUUID()
        val start = Instant.ofEpochSecond(1_790_000_000)

        assertNull(notes.words(one))
        notes.remember(ParcelShareWords(name = true, note = " Hello\nthere ", from = "Sam"), one, now = start)
        notes.remember(ParcelShareWords(name = false, note = "", from = "Léa"), two, now = start)
        assertEquals(ParcelShareWords(name = true, note = "Hello there", from = "Sam"), notes.words(one))
        assertEquals(ParcelShareWords(name = false, note = "", from = "Léa"), notes.words(two))

        notes.forget(one)
        assertNull(notes.words(one))
        assertNotNull(notes.words(two))
        notes.forget(one)

        // Words that say nothing are not kept.
        notes.remember(ParcelShareWords(), two)
        assertNull(notes.words(two))
        assertNull(defaults.values["sdt.parcelShareWords.v1"])

        // Only the latest links stay.
        val ids = (0 until 70).map { UUID.randomUUID() }
        for ((index, id) in ids.withIndex()) {
            notes.remember(ParcelShareWords(name = true), id, now = start.plusSeconds(index.toLong()))
        }
        assertEquals(ids.takeLast(60), ids.filter { notes.words(it) != null })

        notes.forgetAll()
        assertTrue(ids.all { notes.words(it) == null })
        // Damaged notes are no notes.
        defaults.set("nonsense".toByteArray(), forKey = "sdt.parcelShareWords.v1")
        assertNull(notes.words(one))
    }

    @Test
    fun theDemoMakesItsOwnLinksOnThisDevice() {
        val defaults = MemoryDefaults()
        val shares = DemoParcelShares(defaults)
        val parcel = UUID.randomUUID()

        assertNull(shares.link(parcel))
        val made = shares.share(parcel, showNumber = true, gift = false)
        assertTrue(ParcelLinkRoute.validId(made.id))
        assertEquals(made, shares.link(parcel))

        val changed = shares.share(parcel, showNumber = false, gift = true)
        assertEquals("A change keeps the link", made.id, changed.id)
        assertEquals(made.createdAt, changed.createdAt)
        assertEquals(ParcelShare(id = made.id, showNumber = false, gift = true, createdAt = made.createdAt), DemoParcelShares(defaults).link(parcel))

        shares.stop(parcel)
        assertNull(shares.link(parcel))
        assertNotEquals("Sharing again makes a new link", made.id, shares.share(parcel, showNumber = false, gift = false).id)

        shares.share(UUID.randomUUID(), showNumber = true, gift = true)
        shares.reset()
        assertNull(shares.link(parcel))
    }

    // The service

    private class Sent(val method: String, val path: String, val authorization: String?, val body: String)

    @Test
    fun sharingGoesThroughTheAccountAndForgetsTheWordsOfADeletedParcel() = runTest {
        val configuration = AppConfiguration(
            mode = AppConfiguration.Mode.Api, apiBaseUrl = "https://share.test", supabaseUrl = "https://share.test",
            supabasePublishableKey = "public", googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = true,
            appGroupIdentifier = "share.test",
        )
        val user = AuthUser(id = UUID.randomUUID(), email = "test@example.com", isAnonymous = false)
        val signedIn = JsonEncoder.deliveryTracker.encode(
            AuthSession.serializer(),
            AuthSession(
                accessToken = "access", tokenType = "bearer", expiresIn = 3600, expiresAt = Instant.now().epochSecond + 3600,
                refreshToken = "refresh", user = user,
            ),
        ).decodeToString()
        val parcel = Parcel(
            id = UUID.randomUUID(), trackingNumber = "TESTPARCEL983456", label = "Moon lamp", carrier = CarrierId.Dpd,
            createdAt = "2026-10-02T08:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false,
        )
        val path = "/api/packages/${parcel.id.uuidString}/share"
        val shared = JsonEncoder.deliveryTracker.encode(ParcelShareResponse.serializer(), fixture().parcelShare).decodeToString()
        val requests = mutableListOf<Sent>()
        var link: String? = null
        val transport = RecordingTransport { request ->
            val asked = request.path ?: ""
            if (asked == "/auth/v1/verify") return@RecordingTransport jsonResponse(200, signedIn)
            requests += Sent(request.method, asked, request.header("Authorization"), request.body?.decodeToString() ?: "")
            if (asked == path) {
                when (request.method) {
                    "GET" -> return@RecordingTransport jsonResponse(200, link ?: """{"link":null}""")
                    "PUT" -> {
                        link = shared
                        return@RecordingTransport jsonResponse(200, shared)
                    }
                    "DELETE" -> {
                        link = null
                        return@RecordingTransport jsonResponse(204)
                    }
                }
            }
            if (request.method == "DELETE" && asked == "/api/packages/${parcel.id.uuidString}/permanent") {
                return@RecordingTransport jsonResponse(200, """{"ok":true}""")
            }
            jsonResponse(404, """{"error":"Package not found"}""")
        }
        val defaults = MemoryDefaults()
        val session = testSessionStore(configuration, backgroundScope, transport = transport)
        val store = testParcelStore(configuration, session, transport, backgroundScope, defaults)
        store.setDeliveryWidgetEnabled(false)
        store.setDeliveryLiveActivitiesEnabled(false)
        val client = store.shareClient(parcel)

        try {
            client.current()
            fail("Sharing needs an account")
        } catch (_: DeliveryApiError) {
            assertTrue(requests.isEmpty())
        }

        session.verifyCode(email = "test@example.com", code = "123456")
        assertNull(client.current())
        assertEquals("GET", requests.last().method)
        assertEquals(path, requests.last().path)
        assertEquals("Bearer access", requests.last().authorization)

        val made = client.share(ParcelShareModel.Settings(showNumber = false, gift = true))
        assertEquals(linkId, made.id)
        assertEquals("PUT", requests.last().method)
        assertEquals("Never a name, a note or a signature", """{"gift":true,"showNumber":false}""", requests.last().body)
        assertEquals(made, client.current())

        client.stop()
        assertEquals("DELETE", requests.last().method)
        assertEquals(path, requests.last().path)
        assertNull(client.current())

        // Another account's parcel reads as missing.
        val other = store.shareClient(
            Parcel(
                id = UUID.randomUUID(), trackingNumber = "TESTPARCEL654321", label = "", carrier = CarrierId.Dpd,
                createdAt = "2026-10-02T08:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false,
            ),
        )
        try {
            other.share(ParcelShareModel.Settings())
            fail("Expected a refusal")
        } catch (error: DeliveryApiError.Service) {
            assertEquals("Package not found", error.serviceMessage)
        }

        // Deleting the parcel drops what its link carried.
        val notes = ParcelShareNotes(defaults)
        notes.remember(ParcelShareWords(name = true, note = "Enjoy", from = "Sam"), parcel.id)
        store.permanentlyDelete(parcel)
        assertNull(notes.words(parcel.id))
    }

    @Test
    fun theRequestsBodiesCarryOnlyTheSwitchesAndAnyGiftWords() {
        val shared = fixture().parcelShare
        val served = JsonEncoder.deliveryTracker.encode(ParcelShareResponse.serializer(), shared)
        assertEquals(shared, ApiJson.decodeFromString(ParcelShareResponse.serializer(), served.decodeToString()))
        assertEquals(linkId, shared.link?.id)
        assertNull(ApiJson.decodeFromString(ParcelShareResponse.serializer(), """{"link":null}""").link)
        val words = GiftWords(name = "Moon lamp", note = "Enjoy", from = null)
        assertEquals(
            """{"gift":true,"giftWords":{"from":null,"name":"Moon lamp","note":"Enjoy"},"showNumber":true}""",
            JsonEncoder.deliveryTracker.encode(
                ShareParcelRequest.serializer(),
                ParcelShareModel.Settings(showNumber = true, gift = true, giftWords = words).request,
            ).decodeToString(),
        )
    }

    @Test
    fun sharingIsCountedOnceUnderTheCatalogsNames() {
        val catalog = AnalyticsCatalog.bundled!!
        val path = "/api/packages/43000000-0000-0000-0000-000000000006/share"
        assertEquals("parcel-link-share-change", catalog.event(path = path, method = "PUT", body = null))
        assertEquals("parcel-link-share-stop", catalog.event(path = path, method = "DELETE", body = null))
        assertNull("Opening the sheet is not an action", catalog.event(path = path, method = "GET", body = null))
        assertEquals("parcel-link-keep", catalog.event(path = "/api/packages/claim", method = "POST", body = null))
        assertTrue(catalog.screens.contains("parcel-link"))
        for (action in listOf("parcel-link-share", "parcel-link-sign-in", "parcel-link-open-existing")) {
            assertTrue(action, catalog.actions.contains(action))
            assertFalse("$action is counted where it happens", catalog.operations.any { it.event == action })
        }
    }
}
