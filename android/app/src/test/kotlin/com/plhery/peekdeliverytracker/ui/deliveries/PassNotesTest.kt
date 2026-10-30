package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Application
import android.content.ClipboardManager
import android.content.Intent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.CarrierPostcodeRefused
import com.plhery.peekdeliverytracker.core.api.ImportCharges
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeeded
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeededField
import com.plhery.peekdeliverytracker.core.api.RecipientAction
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.showsMoreWithPostcode
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.LocalCarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What the parcel's page says in and under its pass (`liveParcelPass`): the postcode notes (B-ST09), the made-up scans
 * (B-ST11), the pickup card and its buttons (B-S05, B-G16, B-H02, B-L06), what the carrier says (B-S04), the shipment's
 * facts, the hint where no carrier is asked (B-ST06), and why the last check failed with its buttons and its switch
 * (B-ST07, B-ST14), each shown and hidden by its conditions, and the note and the card as TalkBack reads them.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PassNotesTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** The text's size, times the normal. */
    private var fontScale by mutableFloatStateOf(1f)

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val identities get() = DemoCards.identities

    /** When a parcel was archived, two days before the captures. */
    private val archivedTime = DateParser.isoString(CAPTURED.minus(Duration.ofDays(2)))

    private fun id(name: String): UUID = DemoCards.parcel(name).id

    /** The demo, the parcel whose name starts with `name` changed by `change`. */
    private fun demo(name: String, change: (Parcel) -> Parcel): List<Parcel> {
        val id = id(name)
        return DemoCards.parcels().map { if (it.id == id) change(it) else it }
    }

    /** The demo's `parcels` in the app, as `prepare` sets the stores up. */
    private fun show(parcels: List<Parcel> = DemoCards.parcels(), prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(parcels, AuthenticationState.Demo).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale),
                LocalDeliveriesState provides { deliveries = it },
            ) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = true)
            }
        }
        compose.waitForIdle()
    }

    /** Opens the page of the parcel with `id`, as a notification does. */
    private fun open(id: UUID) {
        compose.runOnUiThread { deliveries.navigator.open(id) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun textExists(text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** How many buttons say exactly `text`. */
    private fun buttons(text: String): Int = compose.onAllNodes(hasText(text) and hasClickAction()).fetchSemanticsNodes().size

    private fun button(text: String) = compose.onNode(hasText(text) and hasClickAction())

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getBoundsInRoot()

    private fun node(tag: String): SemanticsNode = compose.onNodeWithTag(tag).fetchSemanticsNode()

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    private fun rootView(): View = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view

    private fun clipboard(): String? =
        compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()

    private fun opened(): Intent? = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity

    private fun editor(): Pair<UUID, CarrierId?>? = deliveries.carrierEditor?.let { it.pageId to it.initialCarrier }

    // The pass on its own.

    private var passParcel by mutableStateOf<Parcel?>(null)
    private var sample by mutableStateOf(false)
    private var readerLink by mutableStateOf(false)

    /** The carrier editor's requests the pass made, each with the carrier it starts on. */
    private val edits = mutableListOf<CarrierId?>()

    /** `parcel`'s pass on its own in the list's card width, as the page draws it, later changed through the fields above. */
    private fun pass(parcel: Parcel) {
        passParcel = parcel
        compose.waitsForMaps()
        compose.setContent {
            CardHost(reduceMotion = false) {
                LivePassCard(
                    passParcel!!, WorldAtlases.loadedOrNull(), sample = sample, readerLink = readerLink, bell = null,
                    working = false, onMap = {}, onEditCarrier = { edits += it },
                )
            }
        }
        compose.waitForIdle()
    }

    private val dpd get() = identities.displayName(CarrierId.Dpd, AppLanguage.En)

    /** Trail weekend kit, with DPD, whose delivery postcode it refused. */
    private fun refusedPostcode(): Parcel = DemoCards.parcel("Trail weekend kit").let {
        it.copy(
            dpdPostcode = "0000",
            carrierData = (it.carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(postcodeRefused = CarrierPostcodeRefused(CarrierId.Dpd))),
        )
    }

    @Test
    fun aRefusedPostcodeIsSaidWithItsCarrierAndItsEditOpensTheEditor() {
        pass(refusedPostcode())
        compose.onNodeWithTag(PAGE_POSTCODE).assertExists()
        assertTrue(textExists("$dpd didn’t find this parcel with postcode 0000."))
        button("Edit postcode").performClick()
        compose.waitForIdle()
        assertEquals(listOf<CarrierId?>(null), edits)
    }

    @Test
    fun aDpdPostcodeTheCarrierCouldNotCheckIsSaidOnlyWhileItIsGivenAndUnchecked() {
        val kit = DemoCards.parcel("Trail weekend kit")
        val unchecked = kit.copy(dpdPostcode = "0000", carrierData = (kit.carrierData ?: CarrierData()).copy(dpdPostcodeVerified = false))
        pass(unchecked)
        assertTrue(textExists("$dpd didn’t accept postcode 0000, so it shows fewer details."))
        assertEquals(1, buttons("Edit postcode"))

        passParcel = unchecked.copy(carrierData = unchecked.carrierData!!.copy(dpdPostcodeVerified = true))
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE))
        passParcel = unchecked.copy(carrierData = unchecked.carrierData!!.copy(dpdPostcodeVerified = null))
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE))
        passParcel = unchecked.copy(dpdPostcode = "")
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE))
    }

    @Test
    fun archivedSomeoneElsesOrTheSampleTheRefusedPostcodeIsSaidWithoutItsEdit() {
        val refused = refusedPostcode()
        pass(refused.copy(archivedAt = archivedTime))
        assertTrue(exists(PAGE_POSTCODE))
        assertEquals(0, buttons("Edit postcode"))

        passParcel = refused
        readerLink = true
        compose.waitForIdle()
        assertTrue(exists(PAGE_POSTCODE))
        assertEquals(0, buttons("Edit postcode"))

        readerLink = false
        sample = true
        compose.waitForIdle()
        assertTrue(exists(PAGE_POSTCODE))
        assertEquals(0, buttons("Edit postcode"))
    }

    @Test
    fun theReaderIsToldTheCarrierShowsMoreWithThePostcodeAndAskedForIt() {
        // The demo gives it a postcode.
        val kit = DemoCards.parcel("Trail weekend kit").copy(dpdPostcode = null)
        assertTrue(kit.showsMoreWithPostcode(CarrierCatalog.shared))
        pass(kit)
        assertTrue(textExists("$dpd shows more with the delivery postcode."))
        compose.onNode(hasText("Add postcode") and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_POSTCODE_MORE))).performClick()
        compose.waitForIdle()
        assertEquals(listOf<CarrierId?>(null), edits)

        readerLink = true
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE_MORE))
        readerLink = false
        sample = true
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE_MORE))
        sample = false
        passParcel = kit.copy(archivedAt = archivedTime)
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE_MORE))
        // Once given, the postcode's note is the one above, if any.
        passParcel = kit.copy(dpdPostcode = "0000")
        compose.waitForIdle()
        assertFalse(exists(PAGE_POSTCODE_MORE))
    }

    /** Moon lamp, its scans made up: each a day and 11 s after the last, each in a new place. */
    private fun generated(): Parcel {
        val lamp = DemoCards.parcel("Moon lamp")
        val start = CAPTURED.minus(Duration.ofDays(4))
        val moves = (0 until 4).map { step ->
            TrackingEvent(
                id = UUID.nameUUIDFromBytes("move-$step".toByteArray()), packageId = lamp.id, stage = TrackingStage.InTransit,
                description = "In transit", location = "Place $step", occurredAt = DateParser.isoString(start.plusSeconds(step * 86_111L)),
            )
        }
        return lamp.copy(trackingEvents = moves)
    }

    @Test
    fun madeUpScansAreFlaggedAndSaidToSayNothingOfWhereTheParcelIs() {
        val parcel = generated()
        assertEquals(ParcelAttention.Generated, parcel.attention(CAPTURED))
        pass(parcel)
        assertTrue(textExists("These updates look generated"))
        assertTrue(textExists("these updates were most likely generated"))
        assertTrue(textExists("Peek keeps asking ${identities.displayName(parcel.carrier, AppLanguage.En)} and shows the next real scan here."))

        passParcel = DemoCards.parcel("Moon lamp")
        compose.waitForIdle()
        assertFalse(textExists("These updates look generated"))
    }

    @Test
    fun theCarriersPromptsAndMarksOpenTheEditorOnTheCarrierTheyName() {
        val gift = DemoCards.parcel("Birthday gift")
        show(
            demo("Birthday gift") {
                it.copy(carrierData = CarrierData(routing = CarrierDataRouting(inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode))))
            },
        )
        open(gift.id)
        compose.onNode(hasText("Add postcode") and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_PROMPT))).performClick()
        settle()
        assertEquals(gift.id to CarrierId.Dpd, editor())
        val first = deliveries.carrierEditor

        compose.onNodeWithTag(PAGE_MARKS).performClick()
        settle()
        assertEquals(gift.id to null, editor())
        // Each a request of its own, as the iPhone's has its own id.
        assertNotSame(first, deliveries.carrierEditor)
    }

    @Test
    fun theProvidersPromptOpensTheEditor() {
        val gift = DemoCards.parcel("Birthday gift")
        show(
            demo("Birthday gift") {
                it.copy(carrierData = CarrierData(routing = CarrierDataRouting(providerInputNeeded = ProviderInputNeeded("Example Post", ProviderInputNeededField.DpdPostcode))))
            },
        )
        open(gift.id)
        button("Add postcode").performClick()
        settle()
        assertEquals(gift.id to null, editor())
    }

    // The pickup card.

    private val trainers get() = id("New trainers")

    @Test
    fun whileTheParcelWaitsItsPickupPointHasACardWhoseDirectionsOpenGoogleMaps() {
        show()
        open(trainers)
        compose.onNodeWithTag(PAGE_PICKUP).performScrollTo()
        assertEquals(listOf("Pickup point", "Kiosk im Hauptbahnhof", "Bahnhofplatz 15, 8001 Zürich"), texts(node(PAGE_PICKUP_POINT)))
        // A card says where, so the facts don't.
        assertTrue(exists(PAGE_FACTS))
        assertEquals(0, compose.onAllNodes(hasText("Pickup point") and hasAnyAncestor(hasTestTag(PAGE_FACTS)), useUnmergedTree = true).fetchSemanticsNodes().size)
        assertTrue(textExists("0.9 kg"))

        compose.onNodeWithTag(PAGE_PICKUP_DIRECTIONS).performClick()
        compose.waitForIdle()
        assertEquals(listOf("parcel-pickup-directions"), source.actions.filter { it.startsWith("parcel-pickup") })
        val started = opened()
        assertEquals(Intent.ACTION_VIEW, started?.action)
        assertEquals("https://www.google.com/maps/dir/?api=1&destination=Bahnhofplatz%2015%2C%208001%20Z%C3%BCrich", started?.dataString)
    }

    @Test
    fun aPointWithoutAnAddressIsSearchedForAndHasNoCopy() {
        show(demo("New trainers") { it.copy(carrierData = it.carrierData!!.copy(pickupPoint = "  Example Kiosk & Co\n")) })
        open(trainers)
        compose.onNodeWithTag(PAGE_PICKUP).performScrollTo()
        assertEquals(listOf("Pickup point", "Example Kiosk & Co"), texts(node(PAGE_PICKUP_POINT)))
        assertFalse(exists(PAGE_PICKUP_COPY))
        assertEquals(listOf("Show on map"), texts(node(PAGE_PICKUP_DIRECTIONS)))
        compose.onNodeWithTag(PAGE_PICKUP_DIRECTIONS).performClick()
        compose.waitForIdle()
        assertEquals("https://www.google.com/maps/search/?api=1&query=Example%20Kiosk%20%26%20Co", opened()?.dataString)
    }

    @Test
    fun withoutAPointOrOnceCollectedThereIsNoCard() {
        show(demo("New trainers") { it.copy(carrierData = it.carrierData!!.copy(pickupPoint = " \n ")) })
        open(trainers)
        assertFalse(exists(PAGE_PICKUP))

        // Collected, the point is a fact.
        open(id("Art print"))
        assertFalse(exists(PAGE_PICKUP))
        assertTrue(textExists("Picked up at"))
        assertTrue(textExists("Paketshop Stadelhofen\nStadelhoferstrasse 8, 8001 Zürich"))
    }

    @Test
    fun theCopyPutsThePointOnTheClipboardWithTheSuccessTapAndSaysCopiedForTwoSeconds() {
        show()
        open(trainers)
        val copy = compose.onNodeWithTag(PAGE_PICKUP_COPY)
        copy.performScrollTo()
        copy.assertContentDescriptionEquals("Copy address")
        compose.mainClock.autoAdvance = false
        copy.performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals("Kiosk im Hauptbahnhof, Bahnhofplatz 15, 8001 Zürich", clipboard())
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), shadowOf(rootView()).lastHapticFeedbackPerformed())
        val counted = source.actions.indexOf("parcel-pickup-copy")
        assertTrue(counted >= 0)
        assertEquals(DeliveryAnalytics.Outcome.Success, source.outcomes[counted])
        copy.assertContentDescriptionEquals("Copied")
        assertTrue(textExists("Copied"))

        compose.mainClock.advanceTimeBy(1_800)
        copy.assertContentDescriptionEquals("Copied")
        compose.mainClock.advanceTimeBy(200)
        copy.assertContentDescriptionEquals("Copy address")
        assertFalse(textExists("Copied"))

        // As on the iPhone, a second copy's word goes when the first one's time is up.
        copy.performClick()
        compose.mainClock.advanceTimeBy(1_500)
        copy.performClick()
        compose.mainClock.advanceTimeBy(600)
        copy.assertContentDescriptionEquals("Copy address")
        compose.mainClock.autoAdvance = true
        assertEquals(3, source.actions.count { it == "parcel-pickup-copy" })
    }

    @Test
    fun theButtonsShareTheCardsWidthAndAtTheLargestTextStandOneUnderTheOtherWithoutTheStorefront() {
        show()
        open(trainers)
        var card = bounds(PAGE_PICKUP)
        var directions = bounds(PAGE_PICKUP_DIRECTIONS)
        var copy = bounds(PAGE_PICKUP_COPY)
        // The storefront's 40 and 12 beside it.
        assertEquals(16f + 40f + 12f, (bounds(PAGE_PICKUP_POINT).left - card.left).value, 0.5f)
        assertEquals(directions.top.value, copy.top.value, 0.5f)
        assertEquals((directions.right - directions.left).value, (copy.right - copy.left).value, 1f)
        assertEquals(8f, (copy.left - directions.right).value, 1f)
        assertEquals(16f, (directions.left - card.left).value, 0.5f)
        assertEquals(16f, (card.right - copy.right).value, 0.5f)
        assertEquals(44f, (directions.bottom - directions.top).value, 0.5f)

        fontScale = 2f
        compose.waitForIdle()
        card = bounds(PAGE_PICKUP)
        directions = bounds(PAGE_PICKUP_DIRECTIONS)
        copy = bounds(PAGE_PICKUP_COPY)
        assertEquals(16f, (bounds(PAGE_PICKUP_POINT).left - card.left).value, 0.5f)
        assertEquals(8f, (copy.top - directions.bottom).value, 1f)
        for (button in listOf(directions, copy)) {
            assertEquals(16f, (button.left - card.left).value, 0.5f)
            assertEquals(16f, (card.right - button.right).value, 0.5f)
        }
    }

    @Test
    fun talkBackReadsThePointAsOneAndEachButtonByWhatItDoes() {
        show()
        open(trainers)
        val parts = compose.onAllNodes(hasAnyAncestor(hasTestTag(PAGE_PICKUP))).fetchSemanticsNodes()
            .filter { SemanticsProperties.Text in it.config || SemanticsProperties.ContentDescription in it.config }
        assertEquals(
            listOf(
                listOf("Pickup point", "Kiosk im Hauptbahnhof", "Bahnhofplatz 15, 8001 Zürich"),
                listOf("Directions"),
                listOf("Copy address"),
            ),
            parts.map { texts(it).ifEmpty { it.config.getOrNull(SemanticsProperties.ContentDescription).orEmpty() } },
        )
        for (button in parts.drop(1)) {
            assertEquals(Role.Button, button.config.getOrNull(SemanticsProperties.Role))
            assertTrue(SemanticsActions.OnClick in button.config)
        }
        assertFalse(SemanticsActions.OnClick in parts.first().config)
        // The copy's symbol and its hidden words say nothing more.
        assertEquals(listOf("Copy address"), node(PAGE_PICKUP_COPY).config[SemanticsProperties.ContentDescription])
        assertNull(node(PAGE_PICKUP_COPY).config.getOrNull(SemanticsProperties.Text))
    }

    // What the carrier says.

    /** `parcel` with a newer scan of `stage` saying `words`, an hour before the captures. */
    private fun Parcel.scanned(stage: TrackingStage, words: String, action: RecipientAction? = null): Parcel = copy(
        trackingEvents = trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("$id-latest".toByteArray()), packageId = id, stage = stage, description = words,
            occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))), recipientAction = action,
        ),
    )

    private val gift get() = id("Birthday gift")

    @Test
    fun whatTheCarrierSaysItWaitsOnIsQuotedUnderItsNameAndReadAsOne() {
        show(demo("Birthday gift") { it.scanned(TrackingStage.FailedAttempt, "  Nobody was home. A notice was left.\n") })
        open(gift)
        assertTrue(textExists("Check the carrier’s next steps"))
        val note = node(PAGE_CARRIER_SAYS)
        assertEquals(listOf("What Chronopost says", "“Nobody was home. A notice was left.”"), texts(note))
        assertNull(note.config.getOrNull(SemanticsProperties.ContentDescription))
        assertFalse(SemanticsActions.OnClick in note.config)
    }

    @Test
    fun theCarrierSaysNothingWhereItsWordsAreBlankItWaitsOnNobodyOrTheParcelIsArchived() {
        var parcels = demo("Birthday gift") { it.scanned(TrackingStage.Exception, " \n ") }
        show(parcels)
        open(gift)
        // The flag still says it waits.
        assertTrue(exists(PAGE_PASS))
        assertFalse(exists(PAGE_CARRIER_SAYS))

        parcels = demo("Birthday gift") { it.scanned(TrackingStage.InTransit, "On its way.") }
        compose.runOnUiThread { source.parcels.value = parcels }
        settle()
        assertFalse(exists(PAGE_CARRIER_SAYS))

        // A scan asking for duties, whatever its stage.
        parcels = demo("Birthday gift") { it.scanned(TrackingStage.InTransit, "Duties are due.", RecipientAction.Duties) }
        compose.runOnUiThread { source.parcels.value = parcels }
        settle()
        assertEquals(listOf("What Chronopost says", "“Duties are due.”"), texts(node(PAGE_CARRIER_SAYS)))

        parcels = demo("Birthday gift") { it.scanned(TrackingStage.FailedAttempt, "Nobody was home.").copy(archivedAt = archivedTime) }
        compose.runOnUiThread { source.parcels.value = parcels }
        settle()
        assertFalse(exists(PAGE_CARRIER_SAYS))
    }

    // The facts.

    @Test
    fun theFactsAreTheCarriersInThePagesOrder() {
        val charges = ImportCharges(currency = "EUR", customs = 23.41)
        val parcels = demo("Moon lamp") {
            it.copy(
                carrierData = (it.carrierData ?: CarrierData()).copy(
                    importCharges = charges, pickupPoint = "Example Kiosk\nSample Street 1", receiverName = " A. Reader ",
                    weightKg = 1.25, dimensionsText = "30 × 20 × 10 cm",
                ),
            )
        }
        show(parcels)
        val lamp = parcels.first { it.id == id("Moon lamp") }
        open(lamp.id)
        compose.onNodeWithTag(PAGE_FACTS).performScrollTo()
        val values = listOf(localizer.importCharges(lamp)!!.text, "Example Kiosk\nSample Street 1", "A. Reader", "1.25 kg", "30 × 20 × 10 cm")
        val labels = listOf("Import charges", "Pickup point", "Recipient", "Weight", "Dimensions")
        val tops = (labels + values).map { text ->
            compose.onNode(hasText(text) and hasAnyAncestor(hasTestTag(PAGE_FACTS)), useUnmergedTree = true).getBoundsInRoot().top.value
        }
        // Each label above its value, and each fact under the one before.
        for (index in labels.indices) assertTrue("${labels[index]} above its value", tops[index] < tops[labels.size + index])
        assertEquals(tops.take(labels.size), tops.take(labels.size).sorted())
    }

    @Test
    fun withoutCarrierDataThereAreNoFactsAndTheSampleHasItsOwn() {
        show(demo("Moon lamp") { it.copy(carrierData = null) })
        open(id("Moon lamp"))
        assertTrue(exists(PAGE_PASS))
        assertFalse(exists(PAGE_FACTS))

        compose.runOnUiThread { deliveries.openSample() }
        settle()
        assertTrue(exists(PAGE_FACTS))
        assertTrue(textExists("Picked up at"))
        assertTrue(textExists("0.9 kg"))
        assertFalse(exists(PAGE_CARRIER_HINT))
        assertFalse(exists(PAGE_SYNC_ERROR))
    }

    // Where no carrier is asked.

    @Test
    fun anAmazonLogisticsParcelSaysWhereItIsTrackedAndOffersNoOtherCarrier() {
        show(demo("Moon lamp") { it.copy(carrier = CarrierId.AmazonLogistics, syncStatus = SyncStatus.Error, syncError = "carrier:not_found") })
        open(id("Moon lamp"))
        compose.onNodeWithTag(PAGE_CARRIER_HINT).performScrollTo()
        assertTrue(textExists("Amazon Logistics deliveries are usually tracked in Your Orders on Amazon."))
        assertEquals(0, buttons("Change carrier"))
        // The hint, rather than why the last check failed.
        assertFalse(exists(PAGE_SYNC_ERROR))
    }

    @Test
    fun anAmazonShippingParcelWhoseHistoryIsGoneSaysSoAndOffersNoOtherCarrier() {
        show(demo("Moon lamp") { it.copy(carrier = CarrierId.AmazonShipping, syncStatus = SyncStatus.Error, syncError = "amazon_shipping_history_expired") })
        open(id("Moon lamp"))
        compose.onNodeWithTag(PAGE_CARRIER_HINT).performScrollTo()
        assertTrue(textExists("This is an Amazon Shipping parcel, but Amazon no longer provides its tracking history."))
        assertEquals(0, buttons("Change carrier"))
        assertFalse(exists(PAGE_SYNC_ERROR))
    }

    /** The app's catalog with UPS followed through its site only, which none is but Amazon Logistics. */
    private val linkOnlyUps: CarrierIdentities by lazy {
        val bundled = SharedResources.text("CarrierCatalog.json")
        val ups = Regex("\"ups\": \\{[^}]*?\"tracking\": \\{\\s*\"mode\": \"automatic\"").find(bundled)!!
        val changed = bundled.replaceRange(ups.range, ups.value.replace("\"automatic\"", "\"link-only\""))
        runBlocking { CarrierIdentities.load(CarrierCatalog(changed.encodeToByteArray())) }
    }

    @Test
    fun aCarrierPeekDoesNotAskSaysSoAndOffersTheReaderAnother() {
        assertFalse(linkOnlyUps.catalog.tracksAutomatically(CarrierId.Ups))
        val vinyl = DemoCards.parcel("Kind of Blue")
        var editable by mutableStateOf(true)
        var changes = 0
        compose.setContent {
            CardHost {
                CompositionLocalProvider(LocalCarrierIdentities provides linkOnlyUps) {
                    Column {
                        TrackingNotes(vinyl, editable, working = false, ink = Color.Blue, onEditCarrier = { changes++ }, onSwitch = {})
                    }
                }
            }
        }
        compose.waitForIdle()
        assertTrue(textExists("Automatic updates aren’t available for UPS yet."))
        button("Change carrier").performClick()
        compose.waitForIdle()
        assertEquals(1, changes)

        editable = false
        compose.waitForIdle()
        assertTrue(textExists("Automatic updates aren’t available for UPS yet."))
        assertEquals(0, buttons("Change carrier"))
    }

    // Why the last check failed.

    private fun Parcel.failing(code: String): Parcel = copy(syncStatus = SyncStatus.Error, syncError = code)

    @Test
    fun aFailedCheckSaysWhyInTheAppsWordsAndOffersNothingWhereNothingHelps() {
        val vinyl = id("Kind of Blue")
        show()
        open(vinyl)
        assertFalse(exists(PAGE_SYNC_ERROR))
        val codes = listOf(
            "carrier:input_required", "carrier:invalid_input", "carrier:several_parcels", "carrier:placeholder_number",
            "carrier:maintenance", "carrier:rate_limited", "carrier:challenge", "carrier:transport", "carrier:budget",
            "carrier:not_found", "carrier:schema", "carrier:indeterminate", "carrier:something_new", "network",
        )
        for (code in codes) {
            compose.runOnUiThread { source.parcels.value = demo("Kind of Blue") { it.failing(code) } }
            settle()
            assertEquals(code, listOf(localizer.trackingFailureMessage(code)), texts(node(PAGE_SYNC_ERROR)))
            // UPS asks for nothing more, and the number is its own.
            for (text in listOf("Update tracking details", "Change carrier")) assertEquals(code, 0, buttons(text))
        }
        assertEquals(
            "The carrier hasn’t found this shipment yet. Check the tracking details or try again later.",
            localizer.trackingFailureMessage("carrier:not_found"),
        )
    }

    @Test
    fun detailsTheCarrierAskedForAreUpdatedInTheEditorWhereItIsTheCarrierAsked() {
        val kit = id("Trail weekend kit")
        val number = DemoCards.parcel("Trail weekend kit").trackingNumber
        assertTrue(CarrierCatalog.shared.requirements(CarrierId.Dpd, number).isNotEmpty())
        show(demo("Trail weekend kit") { it.failing("carrier:input_required") })
        open(kit)
        compose.onNodeWithTag(PAGE_SYNC_ERROR).performScrollTo()
        assertEquals(
            listOf("More information is needed to check this shipment. Update its tracking details or check the carrier’s website."),
            texts(node(PAGE_SYNC_ERROR)),
        )
        button("Update tracking details").performScrollTo().performClick()
        settle()
        assertEquals(kit to null, editor())

        compose.runOnUiThread { source.parcels.value = demo("Trail weekend kit") { it.failing("carrier:invalid_input") } }
        settle()
        assertEquals(1, buttons("Update tracking details"))

        // Another carrier follows it now: the details are not this one's to ask for.
        compose.runOnUiThread {
            source.parcels.value = demo("Trail weekend kit") {
                it.failing("carrier:invalid_input").copy(carrierData = (it.carrierData ?: CarrierData()).copy(activeTrackingCarrier = CarrierId.SwissPost))
            }
        }
        settle()
        assertTrue(exists(PAGE_SYNC_ERROR))
        assertEquals(0, buttons("Update tracking details"))
    }

    /** UPS's example number, whose shape and check digit are UPS's alone. */
    private val upsNumber = "1Z999AA10123456784"

    private fun refusedNumber(): List<Parcel> = demo("Birthday gift") { it.copy(trackingNumber = upsNumber).failing("carrier:refused_number") }

    @Test
    fun aRefusedNumberThatLooksLikeAnotherCarriersOffersTheSwitchAndTheChange() {
        assertEquals(CarrierId.Ups, CarrierCatalog.shared.claimingCarrier(upsNumber, besides = CarrierId.Chronopost))
        show(refusedNumber())
        open(gift)
        compose.onNodeWithTag(PAGE_SYNC_ERROR).performScrollTo()
        assertEquals(
            listOf("This carrier doesn’t issue tracking numbers like this one. It looks like one from UPS."),
            texts(node(PAGE_SYNC_ERROR)),
        )
        assertEquals(0, buttons("Update tracking details"))
        button("Change carrier").performScrollTo().performClick()
        settle()
        assertEquals(gift to null, editor())

        button("Switch to UPS").performScrollTo().performClick()
        settle()
        assertEquals(listOf(gift to CarrierId.Ups), source.carrierChanges)
        assertFalse(exists(PAGE_SYNC_ERROR))
        assertFalse(deliveries.pageWorking)
    }

    @Test
    fun aRefusedNumberNoOtherCarrierClaimsOffersOnlyTheChange() {
        val number = DemoCards.parcel("Birthday gift").trackingNumber
        assertNull(CarrierCatalog.shared.claimingCarrier(number, besides = CarrierId.Chronopost))
        show(demo("Birthday gift") { it.failing("carrier:refused_number") })
        open(gift)
        assertEquals(listOf(localizer.trackingFailureMessage("carrier:refused_number")), texts(node(PAGE_SYNC_ERROR)))
        assertEquals(0, compose.onAllNodes(hasText("Switch to", substring = true)).fetchSemanticsNodes().size)
        assertEquals(1, buttons("Change carrier"))
    }

    @Test
    fun theSwitchSavesOneChangeAtATimeWhileThePageWaits() {
        show(refusedNumber()) { changing = CompletableDeferred() }
        open(gift)
        val switch = compose.onNode(hasText("Switch to UPS"))
        switch.performScrollTo().performClick()
        settle()
        assertTrue(deliveries.pageWorking)
        switch.assertIsNotEnabled()
        compose.onNodeWithTag(PAGE_BELL).assertIsNotEnabled()
        // A second one, however it comes, is not asked for.
        compose.runOnUiThread { deliveries.switchCarrierOnPage(gift, source.parcels.value.first { it.id == gift }, CarrierId.Ups) }
        assertEquals(emptyList<Any>(), source.carrierChanges)

        compose.runOnUiThread { source.changing?.complete(Unit) }
        settle()
        assertEquals(listOf(gift to CarrierId.Ups), source.carrierChanges)
        assertFalse(deliveries.pageWorking)
    }

    @Test
    fun aSwitchThatFailedSaysWhyAndCanBeTriedAgain() {
        show(refusedNumber()) { failure = IllegalStateException("No connection") }
        open(gift)
        button("Switch to UPS").performScrollTo().performClick()
        settle()
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        assertTrue(textExists("No connection"))
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        settle()
        assertEquals(emptyList<Any>(), source.carrierChanges)
        compose.onNode(hasText("Switch to UPS")).assertIsEnabled()
    }

    @Test
    fun aSwitchToACarrierThatNeedsMoreOpensTheEditorOnIt() {
        assertTrue(CarrierCatalog.shared.requirements(CarrierId.Dpd, upsNumber).isNotEmpty())
        show(refusedNumber())
        open(gift)
        compose.runOnUiThread { deliveries.switchCarrierOnPage(gift, source.parcels.value.first { it.id == gift }, CarrierId.Dpd) }
        settle()
        assertEquals(gift to CarrierId.Dpd, editor())
        assertEquals(emptyList<Any>(), source.carrierChanges)
        assertFalse(deliveries.pageWorking)
    }

    @Test
    fun someoneElsesParcelSaysWhyItsCheckFailedButOffersNothingToChange() {
        show(refusedNumber()) { viewerLinks = mapOf(gift to "link-0001") }
        open(gift)
        assertTrue(exists(PAGE_SYNC_ERROR))
        assertEquals(0, compose.onAllNodes(hasText("Switch to", substring = true)).fetchSemanticsNodes().size)
        assertEquals(0, buttons("Change carrier"))
        assertNotNull(source.viewerLink(source.parcels.value.first { it.id == gift }))
    }
}
