package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.widgetParcel
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.File
import java.nio.file.Files
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** DeliveryWidget.swift's provider and DeliveryWidgetPictures.swift's naming, and the store drawing the cards (ParcelRouteTests). */
@OptIn(ExperimentalCoroutinesApi::class)
class DeliveryWidgetTests {
    private val now = Instant.parse("2026-10-28T12:00:00Z")

    private fun card(name: String) = DeliveryWidgetCard(
        surface = listOf("#e6deef", "#292235"), ink = listOf("#23093f", "#c1add7"), status = "In transit", pictures = name, scale = 2.0,
    )

    private fun snapshot(vararg parcels: DeliveryWidgetParcel) = DeliveryWidgetSnapshot(now, "en", parcels.toList())

    /** `makeEntry`: the page holding the turn, else the first, and the next button's parcel, the first again from the last. */
    @Test
    fun widgetEntryShowsThePageItWasTurnedTo() {
        val courier = widgetParcel(label = "Courier", outForDelivery = true)
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false)
        val later = widgetParcel(label = "Later", outForDelivery = false)
        val shared = snapshot(courier, nextUp, later)
        fun entry(medium: Boolean, turn: UUID? = null) =
            DeliveryWidgetEntry.make<String>(now, enabled = true, languageCode = "en", snapshot = shared, medium = medium, turn = turn) { _, _ -> null }

        val first = entry(medium = true)
        assertEquals(listOf(courier, nextUp), first.shown)
        assertTrue(first.isFirstPage)
        assertEquals(later.id, first.next)
        val turned = entry(medium = true, turn = later.id)
        assertEquals(listOf(later), turned.shown)
        assertFalse(turned.isFirstPage)
        assertEquals(courier.id, turned.next)
        // A turn to the second of a pair shows the pair.
        assertEquals(listOf(courier, nextUp), entry(medium = true, turn = nextUp.id).shown)
        // A turn to a parcel the widget no longer shows shows the first page.
        assertEquals(listOf(courier), entry(medium = false, turn = UUID.randomUUID()).shown)
        assertEquals(nextUp.id, entry(medium = false).next)
        assertEquals(courier.id, entry(medium = false, turn = later.id).next)
        // All at once: no next button.
        val alone = DeliveryWidgetEntry.make<String>(now, true, "en", snapshot(courier, nextUp), medium = true) { _, _ -> null }
        assertNull(alone.next)
        val none = DeliveryWidgetEntry.make<String>(now, true, "en", null, medium = true) { _, _ -> null }
        assertTrue(none.shown.isEmpty())
        assertNull(none.next)
    }

    /** The cards' pictures are read for the shape the widget shows them in, and the cards shown only when every one has them. */
    @Test
    fun widgetEntryShowsCardsOnlyWhenEveryOneIsDrawn() {
        val courier = widgetParcel(label = "Courier", outForDelivery = true).copy(card = card("a"))
        val nextUp = widgetParcel(label = "Next up", outForDelivery = false).copy(card = card("b"))
        val words = widgetParcel(label = "Words", outForDelivery = true)
        fun entry(vararg parcels: DeliveryWidgetParcel, medium: Boolean, enabled: Boolean = true, drawn: Set<String> = setOf("a", "b")) =
            DeliveryWidgetEntry.make(now, enabled, "en", snapshot(*parcels), medium = medium) { card, shape ->
                if (card.pictures in drawn) "${card.pictures}-${shape.rawValue}" else null
            }

        assertEquals(listOf("a-half", "b-half"), entry(courier, nextUp, medium = true).art)
        assertEquals(listOf(courier, nextUp), entry(courier, nextUp, medium = true).cards.map { it.first })
        assertEquals(listOf("b-wide"), entry(nextUp, medium = true).art)
        assertEquals(listOf("a-small"), entry(courier, nextUp, medium = false).art)
        // One card short, and the widget writes words for both.
        assertTrue(entry(courier, nextUp, medium = true, drawn = setOf("a")).cards.isEmpty())
        assertTrue(entry(courier, words, medium = true).art.isEmpty())
        assertTrue(entry(courier, nextUp, medium = true, enabled = false).cards.isEmpty())
    }

    /** The timeline reads the store again 20 minutes on, or at the end of a turn's spell, whichever comes first. */
    @Test
    fun widgetTimelineComesBackAtTheEndOfATurn() {
        assertEquals("medium", DeliveryWidgetTimeline.turnSize(medium = true))
        assertEquals("small", DeliveryWidgetTimeline.turnSize(medium = false))
        assertEquals(now.plusSeconds(1_200), DeliveryWidgetTimeline.next(now, listOf(null, null)))
        val turn = DeliveryWidgetTurn(parcel = UUID.randomUUID(), at = now.minusSeconds(60))
        assertEquals(turn.ends, DeliveryWidgetTimeline.next(now, listOf(null, turn)))
        val over = DeliveryWidgetTurn(parcel = UUID.randomUUID(), at = now.minusSeconds(600))
        assertEquals(now.plusSeconds(1_200), DeliveryWidgetTimeline.next(now, listOf(over)))
        assertEquals(DeliveryWidgetTurn(UUID.randomUUID(), now).ends, DeliveryWidgetTimeline.next(now, listOf(turn.copy(at = now), over)))
    }

    /** The parcel opens on the deliveries through the app's routing, as the iPhone widget's links do. */
    @Test
    fun widgetParcelOpensThroughTheAppsRouting() {
        val parcel = widgetParcel(label = "Parcel", outForDelivery = false)
        assertEquals("peekdeliverytracker://parcel/${parcel.id.toString().uppercase()}", parcel.deepLink)
        assertEquals(NativeRoute.Parcel(parcel.id), NativeRoute(parcel.deepLink))
    }

    /** `testTheWidgetsCardsAreDrawnOnceForTheWidgetToRead`, its naming half: the drawings wait for :app. */
    @Test
    fun widgetCardsAreNamedAfterWhatTheyShow() {
        val folder = Files.createTempDirectory("widget-cards").toFile()
        try {
            val localizer = Localizer(AppLanguage.En)
            val parcels = DemoRepository(MemoryDefaults(), language = { AppLanguage.En }).list()
            val placed = parcels.first { it.label.startsWith("Moon lamp") }
            val unplaced = parcels.first { parcel -> parcel.trackingEvents.all { it.place == null } }

            val card = DeliveryWidgetPictures.card(placed, localizer, scale = 2.0)
            assertEquals(card, DeliveryWidgetPictures.card(placed, localizer, scale = 2.0))
            assertEquals("In transit", card.status)
            assertTrue(card.pictures.startsWith(placed.id.toString().lowercase() + "-"))
            assertNotEquals(card.pictures, DeliveryWidgetPictures.card(placed, localizer, scale = 3.0).pictures)
            assertNotEquals(card.pictures, DeliveryWidgetPictures.card(placed, Localizer(AppLanguage.Fr), scale = 2.0).pictures)
            // Handed over, a parcel keeps the colours of the carrier it came with, as its card in the list does.
            val origin = if (placed.carrier == CarrierId.Dhl) CarrierId.GlsDe else CarrierId.Dhl
            val handedOver = placed.copy(carrierData = CarrierData(originalCarrier = origin, originalTrackingNumber = "ORIGIN1234"))
            val listed = CarrierVisualIdentity.of(handedOver.displayedCarrier, language = AppLanguage.En).colors
            assertEquals(listed.subList(0, 2), DeliveryWidgetPictures.card(handedOver, localizer, 2.0).surface)
            assertEquals(listed.subList(2, 4), DeliveryWidgetPictures.card(handedOver, localizer, 2.0).ink)
            assertNotEquals(listed, CarrierVisualIdentity.of(placed.carrier, language = AppLanguage.En).colors)

            // The mark is drawn last: a card is drawn once both of its marks are there.
            assertFalse(DeliveryWidgetPictures.isDrawn(card, folder))
            draw(card, folder, mark = false)
            assertFalse(DeliveryWidgetPictures.isDrawn(card, folder))
            draw(card, folder)
            assertTrue(DeliveryWidgetPictures.isDrawn(card, folder))
            val plain = DeliveryWidgetPictures.card(unplaced, localizer, 2.0)
            draw(plain, folder)

            // A new scan names new pictures; the old ones go with the next publication.
            val moved = placed.copy(
                trackingEvents = placed.trackingEvents + TrackingEvent(
                    id = UUID.randomUUID(), packageId = placed.id, stage = TrackingStage.InTransit, description = "Arrived",
                    location = null, occurredAt = "2026-10-27T08:00:00Z",
                    place = EventPlace(latitude = 49.2, longitude = 9.6, precision = EventPlacePrecision.City, country = "DE", name = "Öhringen"),
                ),
            )
            val next = DeliveryWidgetPictures.card(moved, localizer, 2.0)
            assertNotEquals(card.pictures, next.pictures)
            assertFalse(DeliveryWidgetPictures.isDrawn(next, folder))
            DeliveryWidgetPictures.sweep(keeping = listOf(plain), folder)
            assertFalse(DeliveryWidgetPictures.isDrawn(card, folder))
            assertTrue(DeliveryWidgetPictures.isDrawn(plain, folder))
            assertEquals(2 + DeliveryWidgetPicture.routes.size * 2, folder.listFiles().orEmpty().size)
            DeliveryWidgetPictures.sweep(keeping = emptyList(), folder)
            assertEquals(0, folder.listFiles().orEmpty().size)
        } finally {
            folder.deleteRecursively()
        }
    }

    /** The frames iOS draws a route in for each size of widget, no bigger than the smallest widget it goes in. */
    @Test
    fun widgetRoutesAreDrawnForEachSizeOfWidget() {
        assertEquals(Size(170.0, 104.0), DeliveryWidgetPictures.frame(DeliveryWidgetPicture.Small).size)
        assertEquals(Size(162.0, 76.0), DeliveryWidgetPictures.frame(DeliveryWidgetPicture.Half).size)
        assertEquals(Size(364.0, 104.0), DeliveryWidgetPictures.frame(DeliveryWidgetPicture.Wide).size)
        for (shape in DeliveryWidgetPicture.routes) {
            val frame = DeliveryWidgetPictures.frame(shape)
            assertTrue("$shape", frame.ceiling < frame.floor && frame.floor <= frame.size.height)
            assertTrue("$shape", frame.fade.start < frame.ceiling + 8 && frame.fade.endInclusive <= frame.insets.top + 4)
        }
        val card = card("named")
        assertEquals("named-half-dark.png", DeliveryWidgetPicture.Half.file(card, dark = true, File("x")).name)
        assertEquals("named-mark-light.png", DeliveryWidgetPicture.Mark.file(card, dark = false, File("x")).name)
    }

    /**
     * `testTheWidgetGalleryShowsTheDemosNextDeliveries`: the gallery's sample is the demo's next deliveries, with the words
     * the app publishes for them in each language. The iPhone's tests redraw it (PEEK_WIDGET_SAMPLE) when they change.
     */
    @Test
    fun theWidgetGalleryShowsTheDemosNextDeliveries() {
        val bytes = requireNotNull(javaClass.getResourceAsStream("/peek/${DeliveryWidgetSample.folderName}/${DeliveryWidgetSample.snapshotsFile}"))
            .use { it.readBytes() }
        fun words(parcels: List<DeliveryWidgetParcel>?) = parcels?.map {
            listOf(it.label, it.carrier, it.trackingNumber, it.detail, "${it.isOutForDelivery}", it.card?.status ?: "",
                it.card?.surface?.joinToString(" ") ?: "", it.card?.ink?.joinToString(" ") ?: "")
        }
        for (language in AppLanguage.entries) {
            val localizer = Localizer(language)
            val parcels = DemoRepository(MemoryDefaults(), language = { language }).list()
            val shown = DeliveryWidgetSelection.pages(ParcelStore.widgetDeliveries(parcels, localizer), medium = true).first()
            val published = shown.map { item ->
                item.copy(card = DeliveryWidgetPictures.card(parcels.first { it.id == item.id }, localizer, scale = 3.0))
            }
            val sample = DeliveryWidgetSample.snapshot(language.rawValue, bytes)
            assertEquals(language.rawValue, sample?.languageCode)
            assertEquals("The demo's next deliveries changed in $language", words(published), words(sample?.parcels))
            assertEquals(listOf("sample-0", "sample-1"), sample?.parcels?.map { it.card?.pictures })
        }
        assertEquals("en", DeliveryWidgetSample.snapshot("xx", bytes)?.languageCode)
        assertNull(DeliveryWidgetSample.snapshot("en", "{".toByteArray()))
        assertEquals(listOf(DeliveryWidgetPicture.Small, DeliveryWidgetPicture.Half), DeliveryWidgetSample.pictures(of = 0))
        assertEquals(listOf(DeliveryWidgetPicture.Half), DeliveryWidgetSample.pictures(of = 1))
    }

    /**
     * ParcelStore's `carded`: the widget shows words until the app has drawn every card, then one publication brings them
     * all; a card that could not be drawn is not tried again, and the pictures of cards no longer shown go.
     */
    @Test
    fun theStoreDrawsTheWidgetsCardsOnceAndPublishesThemTogether() = runTest {
        val folder = Files.createTempDirectory("widget-store").toFile()
        try {
            val artist = FakeArtist(folder)
            val reloads = mutableListOf<String>()
            val defaults = MemoryDefaults()
            val demo = AppConfiguration(
                mode = AppConfiguration.Mode.Demo, apiBaseUrl = "https://widget.test", supabaseUrl = null, supabasePublishableKey = "",
                googleAuthEnabled = false, appleAuthEnabled = false, emailOtpEnabled = false, appGroupIdentifier = "widget.test",
            )
            val transport = RecordingTransport()
            val session = testSessionStore(demo, backgroundScope, transport = transport, defaults = defaults)
            val store = testParcelStore(
                demo, session, transport, backgroundScope, defaults, widgetCenter = { reloads += it }, widgetPictures = artist,
            )
            val shared = DeliveryWidgetSharedStore(defaults)
            store.setDeliveryLiveActivitiesEnabled(false)
            session.enterDemo()
            assertTrue(store.isDemo)
            // Drawing: nothing is published until it ends.
            assertNull(shared.snapshot)
            runCurrent()
            val published = requireNotNull(shared.snapshot).parcels
            assertTrue(published.isNotEmpty())
            assertTrue(published.all { it.card != null })
            assertEquals(published.map { it.id }.toSet(), artist.drawn.map { it.first }.toSet())
            assertEquals(published.size, artist.drawn.size)
            assertEquals(listOf(DeliveryWidgetSharedStore.kind), reloads.distinct())

            // Drawn already: another publication draws nothing.
            store.refreshDeliverySurfaces()
            runCurrent()
            assertEquals(published.size, artist.drawn.size)

            // A card the artist could not draw stays words, and is not tried again.
            val failed = published.first()
            folder.listFiles().orEmpty().filter { it.name.startsWith(failed.card!!.pictures) }.forEach { it.delete() }
            artist.refusing = true
            store.refreshDeliverySurfaces()
            runCurrent()
            val again = requireNotNull(shared.snapshot).parcels
            assertNull(again.first { it.id == failed.id }.card)
            assertEquals(published.size - 1, again.count { it.card != null })
            val attempts = artist.attempts
            store.refreshDeliverySurfaces()
            runCurrent()
            assertEquals(attempts, artist.attempts)

            // Switched off, the widget keeps no pictures.
            store.setDeliveryWidgetEnabled(false)
            assertNull(shared.snapshot)
            assertEquals(0, folder.listFiles().orEmpty().size)
        } finally {
            folder.deleteRecursively()
        }
    }

    /** Writes a card's pictures as the app's artist does, the mark last. */
    private fun draw(card: DeliveryWidgetCard, folder: File, mark: Boolean = true) {
        folder.mkdirs()
        val pictures = DeliveryWidgetPicture.routes + if (mark) listOf(DeliveryWidgetPicture.Mark) else emptyList()
        for (picture in pictures) for (dark in listOf(false, true)) picture.file(card, dark, folder).writeBytes(byteArrayOf(1))
    }

    private inner class FakeArtist(override val folder: File) : DeliveryWidgetArtist {
        override val scale = 2.0
        val drawn = mutableListOf<Pair<UUID, AppLanguage>>()
        var attempts = 0
        var refusing = false

        override suspend fun draw(parcel: Parcel, card: DeliveryWidgetCard, language: AppLanguage): Boolean {
            attempts += 1
            if (refusing) return false
            draw(card, folder)
            drawn += parcel.id to language
            return true
        }
    }
}
