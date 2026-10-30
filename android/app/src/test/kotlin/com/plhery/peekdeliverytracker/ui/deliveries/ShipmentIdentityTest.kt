package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Application
import android.content.ClipboardManager
import android.content.Intent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isPopup
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelTrackingLink
import com.plhery.peekdeliverytracker.core.TrackingNumberCopy
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.trackingNumbers
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's numbers and its carriers' pages (`shipmentIdentity`, `carrierLinks`): each number under what it
 * is, as its carrier writes it, selectable (B-S06, B-G04); its copy, with the clipboard, the success tap, the word for
 * 2 s and what TalkBack hears (B-H03), and the ••• menu's copy said on the same row; the copy under the number at the
 * accessibility sizes; and the carriers' pages under their numbers or after them with what they are to the parcel,
 * opened in the browser and counted, none for the sample (B-S07, B-G05, B-L05).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShipmentIdentityTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** The text's size, times the normal. */
    private var fontScale by mutableFloatStateOf(1f)

    private val gift: UUID get() = DemoCards.parcel("Birthday gift").id

    /** The demo, Birthday gift changed by `change`. */
    private fun demo(change: (Parcel) -> Parcel): List<Parcel> = DemoCards.parcels().map { if (it.id == gift) change(it) else it }

    private fun show(parcels: List<Parcel> = DemoCards.parcels()) {
        source = FakeDeliveries(parcels, AuthenticationState.Demo)
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

    /** Opens the page of the parcel with `id`, as a notification does, and scrolls to its numbers. */
    private fun open(id: UUID = gift) {
        compose.runOnUiThread { deliveries.navigator.open(id) }
        settle()
        compose.onNodeWithTag(PAGE_IDENTITY).performScrollTo()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun nodes(tag: String): List<SemanticsNode> =
        compose.onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasTestTag(PAGE_IDENTITY)), useUnmergedTree = true).fetchSemanticsNodes()

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    /** Every text of the numbers and the pages, one by one. */
    private fun words(): List<String> =
        compose.onAllNodes(hasAnyAncestor(hasTestTag(PAGE_IDENTITY)), useUnmergedTree = true).fetchSemanticsNodes().flatMap(::texts)

    /** What each number of the page says, as it is written. */
    private fun numbers(): List<String> = nodes(PAGE_NUMBER).flatMap(::texts)

    /** What TalkBack hears of each copy. */
    private fun copies(): List<String> = nodes(PAGE_COPY_NUMBER).map { it.config[SemanticsProperties.ContentDescription].single() }

    /** What each of the carriers' pages says, its words joined. */
    private fun links(): List<String> =
        compose.onAllNodesWithTag(PAGE_CARRIER_LINK).fetchSemanticsNodes().map { texts(it).joinToString(" · ") }

    private fun bounds(node: SemanticsNode): DpRect = with(compose.density) {
        node.boundsInRoot.let { DpRect(it.left.toDp(), it.top.toDp(), it.right.toDp(), it.bottom.toDp()) }
    }

    private fun rootView(): View = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view

    private fun clipboard(): String? =
        compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()

    private fun opened(): Intent? = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity

    /** Swiss Post's 18-digit shape, made up. */
    private val swissPostNumber = "990012345612345678"

    /**
     * Birthday gift handed by Chronopost to Swiss Post under a number of its own, `original` its first number: Swiss
     * Post's number first, then Chronopost's, each with its carrier's page under it.
     */
    private fun handedOver(original: String = DemoCards.parcel("Birthday gift").trackingNumber): List<Parcel> = demo {
        it.copy(
            carrierData = (it.carrierData ?: CarrierData()).copy(
                activeTrackingCarrier = CarrierId.SwissPost, activeTrackingNumber = swissPostNumber, originalCarrier = it.carrier,
                originalTrackingNumber = original,
            ),
        )
    }

    /** An AliExpress number Swiss Post takes over once the parcel reaches Switzerland, made up with its check digit. */
    private val handoffNumber = "LX123456785CH"

    @Test
    fun eachNumberIsWrittenAsItsCarrierWritesItUnderWhatItIs() {
        show(handedOver())
        open()
        assertEquals(listOf("99.00.123456.12345678", "LX123456789DE"), numbers())
        assertTrue(words().toString(), words().containsAll(listOf("Swiss Post", "Chronopost")))
        assertFalse(words().contains("Tracking number"))
        // With several numbers, TalkBack hears which one each copy copies.
        assertEquals(listOf("Copy tracking number — Swiss Post", "Copy tracking number — Chronopost"), copies())
    }

    @Test
    fun aSingleNumberIsTheTrackingNumberAndSwissPostLogisticsWritesItsWithADash() {
        show(demo { it.copy(carrier = CarrierId.Postlogistics, trackingNumber = "12345678001") })
        open()
        assertEquals(listOf("12345678-001"), numbers())
        assertEquals(listOf("Copy tracking number"), copies())
        assertTrue(words().contains("Tracking number"))
        // Its site takes it as it is written.
        compose.onNodeWithTag(PAGE_COPY_NUMBER).performClick()
        compose.waitForIdle()
        assertEquals("12345678-001", clipboard())
    }

    @Test
    fun theNumberTheCarrierTracksTheParcelAsIsSaidSo() {
        show(demo { it.copy(carrierData = (it.carrierData ?: CarrierData()).copy(canonicalTrackingNumber = "DEMOCANON0001")) })
        open()
        assertEquals(listOf("LX123456789DE", "DEMOCANON0001"), numbers())
        assertEquals(listOf("Copy tracking number — Tracking number", "Copy tracking number — Chronopost tracks this as"), copies())
    }

    @Test
    fun aLongPressSelectsTheNumber() {
        show()
        open()
        assertEquals(0, compose.onAllNodes(isPopup()).fetchSemanticsNodes().size)
        compose.onNodeWithTag(PAGE_NUMBER, useUnmergedTree = true).performTouchInput { longClick() }
        compose.waitForIdle()
        // The selection's two handles.
        assertEquals(2, compose.onAllNodes(isPopup()).fetchSemanticsNodes().size)
    }

    @Test
    fun theCopyPutsTheNumberOnTheClipboardWithTheSuccessTapAndSaysCopiedForTwoSeconds() {
        show()
        open()
        val copy = compose.onNodeWithTag(PAGE_COPY_NUMBER)
        copy.assertContentDescriptionEquals("Copy tracking number")
        assertEquals(Role.Button, copy.fetchSemanticsNode().config[SemanticsProperties.Role])
        compose.mainClock.autoAdvance = false
        copy.performClick()
        compose.mainClock.advanceTimeBy(100)
        val entry = DemoCards.parcel("Birthday gift").trackingNumbers.single()
        assertEquals(TrackingNumberCopy.text(entry.number, entry.carrier), clipboard())
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Success), shadowOf(rootView()).lastHapticFeedbackPerformed())
        assertEquals(listOf("parcel-copy-tracking"), source.actions.filter { it == "parcel-copy-tracking" })
        copy.assertContentDescriptionEquals("Copied")

        compose.mainClock.advanceTimeBy(1_800)
        copy.assertContentDescriptionEquals("Copied")
        compose.mainClock.advanceTimeBy(200)
        copy.assertContentDescriptionEquals("Copy tracking number")

        // As on the iPhone, a second copy's word goes when the first one's time is up.
        copy.performClick()
        compose.mainClock.advanceTimeBy(1_500)
        copy.performClick()
        compose.mainClock.advanceTimeBy(600)
        copy.assertContentDescriptionEquals("Copy tracking number")
        compose.mainClock.autoAdvance = true
        assertEquals(3, source.actions.count { it == "parcel-copy-tracking" })
    }

    @Test
    fun aCopyOfAnotherNumberSaysCopiedOnItsOwnRowForItsWholeTime() {
        show(handedOver())
        open()
        compose.mainClock.autoAdvance = false
        val copies = compose.onAllNodesWithTag(PAGE_COPY_NUMBER)
        copies[0].performClick()
        compose.mainClock.advanceTimeBy(1_500)
        copies[1].performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(listOf("Copy tracking number — Swiss Post", "Copied — Chronopost"), copies())
        assertEquals("LX123456789DE", clipboard())
        compose.mainClock.advanceTimeBy(1_800)
        assertEquals(listOf("Copy tracking number — Swiss Post", "Copied — Chronopost"), copies())
        compose.mainClock.advanceTimeBy(200)
        assertEquals(listOf("Copy tracking number — Swiss Post", "Copy tracking number — Chronopost"), copies())
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun theMenusCopySaysCopiedOnTheFirstNumbersRow() {
        show(handedOver())
        open()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { deliveries.choseOnPage(gift, ParcelMenuItem.CopyTrackingNumber, source.parcels.value.first { it.id == gift }) }
        compose.mainClock.advanceTimeBy(100)
        assertEquals(swissPostNumber, clipboard())
        assertEquals(listOf("Copied — Swiss Post", "Copy tracking number — Chronopost"), copies())
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(listOf("Copy tracking number — Swiss Post", "Copy tracking number — Chronopost"), copies())
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun theCopyEndsTheNumbersLineInASquareOf44() {
        show()
        open()
        val number = bounds(nodes(PAGE_NUMBER).single())
        val copy = bounds(nodes(PAGE_COPY_NUMBER).single())
        val identity = bounds(compose.onNodeWithTag(PAGE_IDENTITY, useUnmergedTree = true).fetchSemanticsNode())
        assertEquals(44f, copy.width.value, 0.5f)
        assertEquals(44f, copy.height.value, 0.5f)
        assertEquals(identity.right.value - 2, copy.right.value, 0.5f)
        assertTrue("Copy $copy beside $number", copy.left >= number.right)
    }

    @Test
    fun atTheLargestTextTheCopyGoesUnderTheNumberAndSaysWhatItDoes() {
        fontScale = 2f
        show()
        open()
        val number = bounds(nodes(PAGE_NUMBER).single())
        val copy = bounds(nodes(PAGE_COPY_NUMBER).single())
        assertTrue("Copy $copy under $number", copy.top >= number.bottom)
        assertEquals(number.left.value, copy.left.value, 0.5f)
        assertTrue("Copy $copy is 44 tall at least", copy.height.value >= 44f - 0.5f)
        compose.onNodeWithTag(PAGE_COPY_NUMBER).assertContentDescriptionEquals("Copy tracking number")
    }

    @Test
    fun theCarriersPageOpensInTheBrowserAndIsCounted() {
        show()
        open()
        val link = CarrierCatalog.shared.trackingLinks(DemoCards.parcel("Birthday gift"), AppLanguage.En).single()
        assertEquals(listOf("Open the Chronopost website"), links())
        assertEquals(Role.Button, compose.onNodeWithTag(PAGE_CARRIER_LINK).fetchSemanticsNode().config[SemanticsProperties.Role])
        val row = bounds(compose.onNodeWithTag(PAGE_CARRIER_LINK, useUnmergedTree = true).fetchSemanticsNode())
        assertTrue("Link $row is 44 tall at least", row.height.value >= 44f - 0.5f)
        compose.onNodeWithTag(PAGE_CARRIER_LINK).performClick()
        compose.waitForIdle()
        val intent = opened()
        assertEquals(Intent.ACTION_VIEW, intent?.action)
        assertEquals(link.url, intent?.data.toString())
        assertEquals(listOf("parcel-carrier-link"), source.actions.filter { it == "parcel-carrier-link" })
    }

    @Test
    fun eachNumbersOwnCarrierPageIsUnderItWithoutMoreWords() {
        show(handedOver())
        open()
        assertEquals(listOf("Open the Swiss Post website", "Open the Chronopost website"), links())
        val numbers = nodes(PAGE_NUMBER).map(::bounds)
        val pages = compose.onAllNodesWithTag(PAGE_CARRIER_LINK, useUnmergedTree = true).fetchSemanticsNodes().map(::bounds)
        assertTrue("$pages between $numbers", numbers[0].bottom <= pages[0].top && pages[0].bottom <= numbers[1].top && numbers[1].bottom <= pages[1].top)
    }

    @Test
    fun aPageOfNoNumbersOwnSaysWhatItIsToTheParcel() {
        // The journey before the handover, under the same number.
        show(handedOver(original = DemoCards.parcel("Birthday gift").trackingNumber).map { if (it.id == gift) it.copy(carrierData = it.carrierData!!.copy(activeTrackingNumber = null)) else it })
        open()
        assertEquals(listOf("LX123456789DE"), numbers())
        assertEquals(listOf("Open the Swiss Post website", "Open the Chronopost website · Earlier journey"), links())
        // The row takes the page's width, its words at the start and what it is at the end.
        val row = bounds(compose.onAllNodesWithTag(PAGE_CARRIER_LINK, useUnmergedTree = true).fetchSemanticsNodes()[1])
        val identity = bounds(compose.onNodeWithTag(PAGE_IDENTITY, useUnmergedTree = true).fetchSemanticsNode())
        assertEquals(identity.width.value - 4, row.width.value, 0.5f)
    }

    @Test
    fun swissPostsPageSaysItIsNotReadyYetUntilTheParcelReachesIt() {
        val parcels = demo { it.copy(carrier = CarrierId.Aliexpress, trackingNumber = handoffNumber, carrierData = null, trackingUrl = null) }
        val links = CarrierCatalog.shared.trackingLinks(parcels.first { it.id == gift }, AppLanguage.En)
        assertEquals(listOf(ParcelTrackingLink.Role.Active, ParcelTrackingLink.Role.Waiting), links.map { it.role })
        show(parcels)
        open()
        assertEquals(listOf("Open the AliExpress / Cainiao website", "Open the Swiss Post website · Not ready yet"), links())
    }

    @Test
    fun theSampleHasNoCarriersPage() {
        show()
        compose.runOnUiThread { deliveries.openSample() }
        settle()
        compose.onNodeWithTag(PAGE_IDENTITY).performScrollTo()
        assertTrue(numbers().isNotEmpty())
        assertEquals(emptyList<String>(), links())
    }
}
