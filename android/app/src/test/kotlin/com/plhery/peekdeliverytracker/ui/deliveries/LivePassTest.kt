package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Application
import android.content.Intent
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.click
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
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature.Orientation
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.LikelyCarrier
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeeded
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeededField
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.time.Duration
import java.util.UUID
import kotlin.math.abs
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The pass at the top of a parcel's page (B-S02): its bell (B-S03, B-W01, B-H01), its buttons and what a tap on them,
 * on the name, the marks or the route does (B-G01–B-G03), what it asks of the parcel's carrier (B-S73, B-S74), its top
 * row at the largest text, the quiet note under it (B-S75), Reduce Motion, and the map it opens (B-S19, A-S84).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class LivePassTest {
    val compose = createAndroidComposeRule<ComponentActivity>()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(fold).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** The text's size, times the normal. */
    private var fontScale by mutableFloatStateOf(1f)

    private val gift get() = DemoCards.parcel("Birthday gift").id

    /** `parcels` in the demo, or before signing in, as `prepare` sets the stores up, made again by `recreation`. */
    private fun show(
        session: AuthenticationState = AuthenticationState.Demo,
        parcels: List<Parcel> = DemoCards.parcels(),
        reduceMotion: Boolean = false,
        recreation: Recreation? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(parcels, session).apply(prepare)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale),
                LocalDeliveriesState provides { deliveries = it },
            ) {
                RootHost(state = session, source = source, reduceMotion = reduceMotion)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    /** `parcel` on its own in the list's card width, as the page draws its pass, through [LivePassCard]. */
    private fun card(parcel: () -> Parcel, atlas: () -> WorldAtlas? = { WorldAtlases.loadedOrNull() }, readerLink: Boolean = false, onMap: () -> Unit = {}) {
        compose.waitsForMaps()
        compose.setContent {
            CardHost(reduceMotion = false) {
                LivePassCard(
                    parcel(), atlas(), sample = false, readerLink = readerLink, bell = null, working = false, onMap = { onMap() },
                    onEditCarrier = { _, _ -> }, onRename = {},
                )
            }
        }
        compose.waitForIdle()
    }

    private fun open(name: String) {
        compose.openCard(name)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String) = compose.onNodeWithTag(tag)

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getBoundsInRoot()

    private fun textExists(text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun rootView(): View = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view

    private fun clickLabel(node: SemanticsNodeInteraction): String? =
        node.fetchSemanticsNode().config.getOrNull(SemanticsActions.OnClick)?.label

    private fun hasClick(tag: String): Boolean = SemanticsActions.OnClick in node(tag).fetchSemanticsNode().config

    // The bell.

    @Test
    fun theBellTurnsTheParcelsNotificationsOffThenRingsAndTicksAndBackOn() {
        show()
        open("Birthday gift")
        val bell = node(PAGE_BELL)
        bell.assertContentDescriptionEquals("Turn off parcel notifications")
        bell.performClick()
        settle()
        assertEquals(listOf(gift to true), source.mutes)
        bell.assertContentDescriptionEquals("Turn on parcel notifications")
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Selection), shadowOf(rootView()).lastHapticFeedbackPerformed())

        bell.performClick()
        settle()
        assertEquals(listOf(gift to true, gift to false), source.mutes)
        bell.assertContentDescriptionEquals("Turn off parcel notifications")
    }

    @Test
    fun whileAMuteIsSavedTheBellWaitsAndAFailedOneSaysWhy() {
        show { muting = CompletableDeferred() }
        open("Birthday gift")
        val bell = node(PAGE_BELL)
        bell.performClick()
        settle()
        bell.assertIsNotEnabled()
        assertEquals(emptyList<Any>(), source.mutes)
        compose.runOnUiThread { source.muting?.complete(Unit) }
        settle()
        bell.assertIsEnabled()
        assertEquals(listOf(gift to true), source.mutes)

        source.muting = null
        source.failure = IllegalStateException("No connection")
        bell.performClick()
        settle()
        node(ACTION_ERROR).assertExists()
        compose.onNode(hasText("No connection")).assertExists()
        node(ACTION_ERROR_CLOSE).performClick()
        settle()
        bell.assertIsEnabled()
        bell.assertContentDescriptionEquals("Turn on parcel notifications")
    }

    @Test
    fun whileTheEmailIsOnTheBellOpensTheParcelsAlertsAndMutesNothingUntilTheParcelArrives() {
        val delivered = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }
        show {
            deliveryEmail.value = DeliveryEmail(
                isDemo = false, address = "reader@example.com",
                preferences = NotificationPreferences(enabledStages = emptyList(), timezone = "Europe/Zurich", emailOnDelivery = true, emailAvailable = true),
            )
        }
        open("Birthday gift")
        val bell = node(PAGE_BELL)
        bell.assertContentDescriptionEquals("Notifications for this parcel")
        bell.performClick()
        settle()
        // The alerts sheet comes with its step: nothing yet.
        assertEquals(emptyList<Any>(), source.mutes)
        assertTrue(exists(PAGE_CONTENT))

        compose.runOnUiThread { deliveries.navigator.open(delivered.id) }
        settle()
        bell.assertContentDescriptionEquals("Turn off parcel notifications")
        bell.performClick()
        settle()
        assertEquals(listOf(delivered.id to true), source.mutes)
    }

    @Test
    fun beforeSigningInThereIsNoBell() {
        show(AuthenticationState.Welcome)
        open("Birthday gift")
        assertFalse(exists(PAGE_BELL))
        assertTrue(exists(PAGE_SHARE))
        assertTrue(exists(PAGE_GLOBE))
        assertTrue(hasClick(PAGE_MARKS))
    }

    @Test
    fun theSampleHasNoBellNoShareAndNothingToChange() {
        show()
        compose.runOnUiThread { deliveries.openSample() }
        settle()
        assertTrue(exists(PAGE_PASS))
        assertFalse(exists(PAGE_BELL))
        assertFalse(exists(PAGE_SHARE))
        assertFalse(hasClick(PAGE_MARKS))
        assertFalse(hasClick(PAGE_NAME))
    }

    @Test
    fun aParcelFollowedThroughItsLinkIsSharedButItsCarrierIsTheirs() {
        show { viewerLinks = mapOf(DemoCards.parcel("Birthday gift").id to "link-0001") }
        open("Birthday gift")
        assertTrue(exists(PAGE_SHARE))
        assertTrue(exists(PAGE_BELL))
        assertFalse(hasClick(PAGE_MARKS))
        // Still the reader's to name.
        assertTrue(hasClick(PAGE_NAME))
    }

    // The buttons, the name and the marks.

    @Test
    fun theMarksTheNameAndTheButtonsSayWhatTheyDoAndTheMarksOpenTheCarrierEditor() {
        show()
        open("Birthday gift")
        node(PAGE_MARKS).assertContentDescriptionEquals("Change carrier from Chronopost")
        assertEquals("Edit parcel name", clickLabel(node(PAGE_NAME)))
        node(PAGE_GLOBE).assertContentDescriptionEquals("Open the map")
        node(PAGE_SHARE).assertContentDescriptionEquals("Share this parcel")
        val actions = source.actions.toList()
        // The marks open the carrier editor on the parcel (ChangeCarrierTest's); the share sheet comes with its step,
        // the name's rename is RenameTest's.
        node(PAGE_MARKS).performClick()
        settle()
        assertEquals(gift, deliveries.carrierEditor?.parcelId)
        node(CHANGE_CARRIER_CANCEL).performClick()
        settle()
        assertNull(deliveries.carrierEditor)
        node(PAGE_SHARE).performClick()
        settle()
        assertEquals(actions, source.actions)
        assertNull(deliveries.mapCover)
        assertTrue(exists(PAGE_CONTENT))
    }

    /** The demo with Birthday gift handed over to Swiss Post, which delivers it: two marks on its pass. */
    private fun handedOver(): List<Parcel> = DemoCards.parcels().map { if (it.id == gift) DemoCards.handover() else it }

    /** The middle of the second mark, which hangs 7 under the first, outside the marks' bounds. */
    private fun secondMark(): DpOffset {
        val first = bounds(PAGE_MARKS)
        return DpOffset(first.left + 12.dp, first.bottom + 7.dp + (first.bottom - first.top) / 2)
    }

    @Test
    fun aPressOnTheSecondMarkGivesWithTheFirstAndChangesTheCarrierRatherThanOpeningTheMap() {
        show(parcels = handedOver())
        open("Birthday gift")
        val marks = compose.onNodeWithTag(PAGE_MARKS, useUnmergedTree = true)
        val at = secondMark()
        val rest = compose.drawn(marks)
        compose.mainClock.autoAdvance = false
        compose.onRoot().performTouchInput { down(Offset(at.x.toPx(), at.y.toPx())) }
        compose.mainClock.advanceTimeBy(300)
        val pressed = compose.drawn(marks)
        compose.onRoot().performTouchInput { up() }
        compose.mainClock.autoAdvance = true
        settle()
        // One button, as the iPhone's holds both marks: the carrier editor.
        assertFalse("The first mark didn't give", rest.contentEquals(pressed))
        assertNull(deliveries.mapCover)
        assertEquals(gift, deliveries.carrierEditor?.parcelId)
        assertEquals(0, source.actions.count { it == "parcel-map-open" })
    }

    @Test
    fun theMarksOfAParcelFollowedThroughItsLinkTakeATapWithoutOpeningTheMap() {
        show(parcels = handedOver()) { viewerLinks = mapOf(gift to "link-0001") }
        open("Birthday gift")
        val at = secondMark()
        compose.onRoot().performTouchInput { click(Offset(at.x.toPx(), at.y.toPx())) }
        node(PAGE_MARKS).performClick()
        settle()
        assertNull(deliveries.mapCover)
        // Beside them the route opens it as ever.
        node(PAGE_ROUTE).performClick()
        settle()
        assertNotNull(deliveries.mapCover)
    }

    @Test
    fun aParcelWithNoPlaceHasNoGlobeNorRoute() {
        show()
        // As a notification opens it: scrolled to, its card would sit under the list's bar.
        compose.runOnUiThread { deliveries.navigator.open(DemoCards.parcel("35mm film rolls").id) }
        settle()
        assertTrue(exists(PAGE_PASS))
        assertFalse(exists(PAGE_GLOBE))
        assertFalse(exists(PAGE_ROUTE))
    }

    @Test
    fun theGlobeWaitsForTheRouteThenOpensTheMap() {
        var atlas by mutableStateOf<WorldAtlas?>(null)
        var opened = 0
        card({ DemoCards.parcel("Birthday gift") }, atlas = { atlas }, onMap = { opened++ })
        node(PAGE_GLOBE).assertIsNotEnabled()
        assertFalse(exists(PAGE_ROUTE))

        atlas = runBlocking { WorldAtlases.load().await() }
        compose.waitForIdle()
        node(PAGE_GLOBE).assertIsEnabled().performClick()
        compose.waitForIdle()
        assertEquals(1, opened)
        assertTrue(exists(PAGE_ROUTE))
    }

    // At the largest text.

    @Test
    fun atTheLargestTextTheButtonsGoUnderTheMarksAndTheMapAsMuchLower() {
        show()
        open("Birthday gift")
        var marks = bounds(PAGE_MARKS)
        var bell = bounds(PAGE_BELL)
        assertTrue("Bell $bell beside the marks $marks", bell.top < marks.bottom && bell.left > marks.right)
        assertEquals(176f, (bounds(PAGE_ROUTE).bottom - bounds(PAGE_ROUTE).top).value, 0.5f)

        fontScale = 2f
        compose.waitForIdle()
        marks = bounds(PAGE_MARKS)
        bell = bounds(PAGE_BELL)
        assertTrue("Bell $bell under the marks $marks", bell.top >= marks.bottom)
        // The marks, then 4, then the buttons' 44: all of it more than the top row's 44 lowers the map.
        val route = bounds(PAGE_ROUTE)
        val markHeight = (marks.bottom - marks.top).value
        assertEquals(176f + markHeight + 4f, (route.bottom - route.top).value, 1f)
    }

    // What the pass asks of the carrier.

    @Test
    fun theLikelyCarrierIsSaidAndAfterAWeekTheReaderMayChooseAnother() {
        var parcel by mutableStateOf(DemoCards.probable())
        card({ parcel })
        assertTrue(textExists("doesn’t list it yet"))
        assertFalse(textExists("Choose another carrier"))

        val since = DateParser.isoString(CAPTURED.minus(Duration.ofDays(8)))
        parcel = parcel.copy(carrierData = CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.GlsDe, since))))
        compose.waitForIdle()
        assertTrue(textExists("still doesn’t list it after a week"))
        compose.onNode(hasText("Choose another carrier") and hasClickAction()).assertExists()
    }

    @Test
    fun aNumberShapedLikeAnOrderOrTooShortSaysWhereTheTrackingNumberIs() {
        var parcel by mutableStateOf(DemoCards.probable().copy(trackingNumber = "111-0000000-0000001", carrierData = null))
        card({ parcel })
        assertTrue(textExists("order number from Amazon"))
        compose.onNode(hasText("Choose the carrier") and hasClickAction()).assertExists()

        parcel = parcel.copy(trackingNumber = "AB123")
        compose.waitForIdle()
        assertTrue(textExists("shorter than most tracking numbers"))
    }

    @Test
    fun aCarrierThatNeedsThePostcodeAsksForIt() {
        val birthday = DemoCards.parcel("Birthday gift")
        var parcel by mutableStateOf(
            birthday.copy(carrierData = CarrierData(routing = CarrierDataRouting(inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode)))),
        )
        card({ parcel })
        node(PAGE_PROMPT).assertExists()
        assertTrue(textExists("has this parcel. Add the delivery postcode to track it."))
        compose.onNode(hasText("Add postcode") and hasClickAction()).assertExists()

        parcel = birthday.copy(
            carrierData = CarrierData(routing = CarrierDataRouting(providerInputNeeded = ProviderInputNeeded("Example Post", ProviderInputNeededField.DpdPostcode))),
        )
        compose.waitForIdle()
        assertFalse(exists(PAGE_PROMPT))
        assertTrue(textExists("Example Post needs the delivery postcode to look up this parcel."))
        compose.onNode(hasText("Add postcode") and hasClickAction()).assertExists()
    }

    @Test
    fun someoneElsesParcelAsksNothingOfItsCarrier() {
        card(
            {
                DemoCards.parcel("Birthday gift").copy(
                    carrierData = CarrierData(
                        routing = CarrierDataRouting(
                            inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode),
                            providerInputNeeded = ProviderInputNeeded("Example Post", ProviderInputNeededField.DpdPostcode),
                        ),
                    ),
                )
            },
            readerLink = true,
        )
        assertFalse(exists(PAGE_PROMPT))
        assertFalse(textExists("Add postcode"))
        assertFalse(hasClick(PAGE_MARKS))
    }

    // The quiet note.

    /** Cat tree as it was when its scans stopped, a week before the captures: still in transit, and quiet since. */
    private fun quietParcels(): List<Parcel> {
        val cutoff = CAPTURED.minus(Duration.ofHours(170))
        return DemoCards.parcels().map { parcel ->
            if (!parcel.label.startsWith("Cat tree")) return@map parcel
            parcel.copy(trackingEvents = parcel.trackingEvents.filter { DateParser.date(it.occurredAt)!! <= cutoff })
        }
    }

    @Test
    fun aQuietParcelSaysWhyAndItsHelpLinkOpensTheCarriersPageAndIsCounted() {
        val parcels = quietParcels()
        assertEquals(ParcelAttention.Stalled, parcels.first { it.label.startsWith("Cat tree") }.attention(CAPTURED))
        show(parcels = parcels)
        open("Cat tree")
        node(PAGE_QUIET).performScrollTo()
        assertTrue(textExists("Parcels often go quiet"))
        val help = compose.onNode(hasText("Get help from", substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_QUIET)))
        help.performClick()
        compose.waitForIdle()
        assertEquals(1, source.actions.count { it == "parcel-carrier-help" })
        val started = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity
        assertEquals(Intent.ACTION_VIEW, started.action)
        assertTrue(started.dataString!!.startsWith("https://"))
    }

    @Test
    fun aMovingParcelHasNoQuietNote() {
        show()
        open("Birthday gift")
        assertFalse(exists(PAGE_QUIET))
    }

    /** `parcel`'s quiet note on its own, as the page draws it, `onHelp` called by its link. */
    private fun quietNote(parcel: Parcel, onHelp: () -> Unit = {}) {
        compose.setContent {
            CardHost { ParcelQuietNote(parcel, Color.Blue, onHelp, Modifier.testTag(PAGE_QUIET)) }
        }
        compose.waitForIdle()
    }

    private val helpLink get() = compose.onNode(hasText("Get help from", substring = true) and hasClickAction())

    @Test
    fun aCarrierThatLeavesOutTheJourneyAbroadIsSaidToAndItsHelpIsOffered() {
        val parcel = quietParcels().first { it.label.startsWith("Cat tree") }
            .copy(carrier = CarrierId.SpringGDS, trackingNumber = "100000000000000001", carrierData = null)
        val catalog = DemoCards.identities.catalog
        assertTrue(catalog.hidesDestinationLeg(parcel))
        quietNote(parcel)
        assertTrue(textExists("Parcels often go quiet"))
        assertTrue(textExists("doesn’t show this parcel’s journey in the destination country"))
        assertTrue(textExists("Get help from ${catalog.helpLink(parcel, AppLanguage.En)!!.name}"))
    }

    @Test
    fun aCarrierWithoutAHelpPageHasNoLink() {
        quietNote(quietParcels().first { it.label.startsWith("Cat tree") }.copy(carrier = CarrierId.Yunexpress, carrierData = null))
        assertTrue(textExists("Parcels often go quiet"))
        assertFalse(textExists("Get help from"))
        compose.onAllNodes(hasClickAction()).assertCountEquals(0)
    }

    @Test
    fun withoutABrowserTheHelpLinkIsCountedAndOpensNothing() {
        shadowOf(ApplicationProvider.getApplicationContext<Application>()).checkActivities(true)
        var helps = 0
        quietNote(quietParcels().first { it.label.startsWith("Cat tree") }) { helps++ }
        helpLink.performClick()
        compose.waitForIdle()
        assertEquals(1, helps)
    }

    // Reduce Motion.

    /** The bell's pixels a few frames after a tap, and once it has settled. */
    private fun bellFrames(): Pair<IntArray, IntArray> {
        open("Birthday gift")
        val bell = compose.onNodeWithTag(PAGE_BELL, useUnmergedTree = true)
        compose.mainClock.autoAdvance = false
        bell.performClick()
        repeat(4) { compose.mainClock.advanceTimeByFrame() }
        val early = compose.drawn(bell)
        compose.mainClock.advanceTimeBy(1_000)
        val late = compose.drawn(bell)
        compose.mainClock.autoAdvance = true
        return early to late
    }

    @Test
    fun theBellRingsAndDrawsItsLineIn() {
        show()
        val (early, late) = bellFrames()
        assertFalse(early.contentEquals(late))
    }

    @Test
    fun underReduceMotionTheBellStaysStillAndItsLineIsThereAtOnce() {
        show(reduceMotion = true)
        val (early, late) = bellFrames()
        assertArrayEquals(late, early)
        assertEquals(listOf(gift to true), source.mutes)
    }

    /**
     * How far, across, the middle of the bell's lip has moved since `rest`, in pixels, in its left half: the part
     * neither the line that strikes it out nor its sound lines cross. The button is 44 square, the bell 18 in its middle.
     */
    private fun lipMoved(rest: IntArray, now: IntArray, width: Int): Double {
        val unit = compose.density.density
        val inset = (44 - 18) / 2f
        fun middle(pixels: IntArray): Double {
            val paper = pixels[0]
            var sum = 0.0
            var count = 0
            for (y in ((inset + 11.5f) * unit).toInt() until ((inset + 17f) * unit).toInt()) {
                for (x in (inset * unit).toInt() until ((inset + 8.5f) * unit).toInt()) {
                    val pixel = pixels[y * width + x]
                    val far = (0..2).sumOf { abs((pixel shr (it * 8) and 0xFF) - (paper shr (it * 8) and 0xFF)) }
                    if (far > 60) {
                        sum += x
                        count++
                    }
                }
            }
            check(count > 0) { "No lip drawn" }
            return sum / count
        }
        return middle(now) - middle(rest)
    }

    @Test
    fun onceSavedTheBellSwingsTheWayItsNewStateDoes() {
        show()
        open("Birthday gift")
        val bell = compose.onNodeWithTag(PAGE_BELL, useUnmergedTree = true)
        val width = bell.fetchSemanticsNode().boundsInRoot.width.toInt()
        fun swing(): Double {
            val rest = compose.drawn(bell)
            compose.mainClock.autoAdvance = false
            bell.performClick()
            repeat(7) { compose.mainClock.advanceTimeByFrame() }
            val ringing = compose.drawn(bell)
            compose.mainClock.advanceTimeBy(1_000)
            compose.mainClock.autoAdvance = true
            compose.waitForIdle()
            return lipMoved(rest, ringing, width)
        }
        // Muted, it first nods back by 7 degrees, its lip going right; on again, it swings forward by 17, its lip going
        // left.
        val muting = swing()
        assertEquals(listOf(gift to true), source.mutes)
        assertTrue("The lip moved $muting", muting > 0.5)
        val unmuting = swing()
        assertEquals(listOf(gift to true, gift to false), source.mutes)
        assertTrue("The lip moved $unmuting", unmuting < -0.5)
    }

    /**
     * The route's pixels before its picture comes, a few frames after, and once it has settled, on a card whose
     * scans no map has drawn yet, so its picture is made then, as a page's is the first time.
     */
    private fun routeFrames(reduceMotion: Boolean): Triple<IntArray, IntArray, IntArray> {
        val birthday = DemoCards.parcel("Birthday gift")
        val parcel = birthday.copy(
            trackingEvents = birthday.trackingEvents.map {
                it.copy(id = UUID.nameUUIDFromBytes("entrance $reduceMotion ${it.id}".toByteArray()))
            },
        )
        compose.waitsForMaps()
        compose.mainClock.autoAdvance = false
        compose.setContent {
            CardHost(reduceMotion = reduceMotion) {
                LivePassCard(
                    parcel, WorldAtlases.loadedOrNull(), sample = false, readerLink = false, bell = null, working = false, onMap = {},
                    onEditCarrier = { _, _ -> }, onRename = {},
                )
            }
        }
        var frames = 0
        while (!exists(PAGE_ROUTE)) {
            check(frames++ < 10) { "No route" }
            compose.mainClock.advanceTimeByFrame()
        }
        val route = compose.onNodeWithTag(PAGE_ROUTE)
        val blank = compose.drawn(route)
        // Its picture is made off the main thread, and shown from the next frame.
        compose.waitForIdle()
        repeat(3) { compose.mainClock.advanceTimeByFrame() }
        val early = compose.drawn(route)
        compose.mainClock.advanceTimeBy(1_000)
        val late = compose.drawn(route)
        compose.mainClock.autoAdvance = true
        return Triple(blank, early, late)
    }

    /** How unlike `a` and `b` are: the sum of their channels' differences. */
    private fun unlike(a: IntArray, b: IntArray): Long =
        a.indices.sumOf { i -> (0..2).sumOf { abs((a[i] shr (it * 8) and 0xFF) - (b[i] shr (it * 8) and 0xFF)) }.toLong() }

    @Test
    fun theRouteFadesInOnceDrawn() {
        val (blank, early, late) = routeFrames(reduceMotion = false)
        val whole = unlike(blank, late)
        assertTrue("The route came before the test saw the card without it", whole > 0)
        assertTrue("${unlike(blank, early)} of $whole", unlike(blank, early) < whole / 2)
    }

    @Test
    fun underReduceMotionTheRouteIsThereAtOnce() {
        val (blank, early, late) = routeFrames(reduceMotion = true)
        val whole = unlike(blank, late)
        assertTrue("The route came before the test saw the card without it", whole > 0)
        assertTrue("${unlike(blank, early)} of $whole", unlike(blank, early) > whole * 0.95)
    }

    // The map.

    @Test
    fun theGlobeOpensTheMapCountedOnceAndItsButtonClosesIt() {
        show()
        open("Birthday gift")
        node(PAGE_GLOBE).assertIsEnabled().performClick()
        settle()
        assertEquals(1, source.actions.count { it == "parcel-map-open" })
        node(MAP_COVER).assertExists()
        val label = node(MAP_COVER_MAP).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.single()
        assertTrue("Label $label", label!!.startsWith("Map of the journey from "))
        // Under nothing on the robot's window: the button 14 below its top and 14 in from its side, the summary 12
        // above its foot and in from each side.
        val close = bounds(MAP_CLOSE)
        assertEquals(14f, close.top.value, 0.5f)
        assertEquals(390f - 14f, close.right.value, 0.5f)
        assertEquals(44f, (close.right - close.left).value, 0.5f)
        val bar = bounds(MAP_BAR)
        assertEquals(844f - 12f, bar.bottom.value, 0.5f)
        assertEquals(12f, bar.left.value, 0.5f)
        assertEquals(390f - 12f, bar.right.value, 0.5f)

        node(MAP_CLOSE).performClick()
        settle()
        assertFalse(exists(MAP_COVER))
        assertNull(deliveries.mapCover)
        assertTrue(exists(PAGE_CONTENT))
        assertEquals(1, source.actions.count { it == "parcel-map-open" })
    }

    @Test
    fun aTapOnTheRouteOpensTheMapAndBackClosesIt() {
        show()
        open("Birthday gift")
        node(PAGE_ROUTE).performClick()
        settle()
        assertEquals(1, source.actions.count { it == "parcel-map-open" })
        node(MAP_COVER).assertExists()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        assertFalse(exists(MAP_COVER))
        // Back closed the map, not the page.
        assertTrue(exists(PAGE_CONTENT))
    }

    @Test
    fun theMapSwitchesBetweenTheJourneyAndCloseBy() {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        val parcel = DemoCards.parcels().first { it.archivedAt == null && ParcelRoute(it, atlas, AppLanguage.En).hasNearView }
        show()
        open(parcel.label)
        node(PAGE_GLOBE).performClick()
        settle()
        node(MAP_VIEWS).assertContentDescriptionEquals("Map view")
        val journey = compose.onNode(hasText("Journey") and hasClickAction())
        val nearby = compose.onNode(hasText("Nearby") and hasClickAction())
        nearby.performClick()
        compose.waitForIdle()
        nearby.assertIsSelected()
        journey.assertIsNotSelected()
        journey.performClick()
        compose.waitForIdle()
        journey.assertIsSelected()
        nearby.assertIsNotSelected()
    }

    @Test
    fun theMapGoesWithItsParcel() {
        show()
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        source.parcels.value = source.parcels.value.filterNot { it.id == gift }
        settle()
        assertFalse(exists(MAP_COVER))
        assertNull(deliveries.mapCover)
    }

    @Test
    fun theMapGoesWithItsPage() {
        show()
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        // As a notification opens another parcel.
        compose.runOnUiThread { deliveries.navigator.open(DemoCards.parcel("Moon lamp").id) }
        settle()
        assertFalse(exists(MAP_COVER))
        assertNull(deliveries.mapCover)
        assertTrue(textExists("Moon lamp"))
    }

    @Test
    fun theMapStaysUpAsTheWindowIsMadeAgainWithoutComingUpAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        recreation.recreate()
        // Back with the shell's layers, at rest from the frame it shows in.
        var frames = 0
        while (!exists(MAP_COVER)) {
            check(frames++ < 5) { "The map didn't come back" }
            compose.mainClock.advanceTimeByFrame()
        }
        assertEquals(MapCoverRequest(gift), deliveries.mapCover)
        assertEquals(14f, bounds(MAP_CLOSE).top.value, 0.5f)
        compose.mainClock.autoAdvance = true
        node(MAP_CLOSE).performClick()
        settle()
        assertFalse(exists(MAP_COVER))
        assertNull(deliveries.mapCover)
        assertTrue(exists(PAGE_CONTENT))
    }

    /** The open map's pixels, and again 0.7 s on. */
    private fun mapFrames(reduceMotion: Boolean): Pair<IntArray, IntArray> {
        show(reduceMotion = reduceMotion)
        open("Birthday gift")
        // A pulse runs only on a clock the test moves itself: on one that runs by itself, Compose's tests stop it.
        compose.mainClock.autoAdvance = false
        node(PAGE_GLOBE).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        val map = node(MAP_COVER_MAP)
        val first = compose.drawn(map)
        compose.mainClock.advanceTimeBy(700)
        val later = compose.drawn(map)
        compose.mainClock.autoAdvance = true
        return first to later
    }

    @Test
    fun theMapPulsesAtTheParcelsPlace() {
        val (first, later) = mapFrames(reduceMotion = false)
        assertFalse(first.contentEquals(later))
    }

    @Test
    fun underReduceMotionTheMapComesUpAndStaysStill() {
        val (first, later) = mapFrames(reduceMotion = true)
        assertArrayEquals(first, later)
    }

    /** A foldable in book posture, its hinge 350 in from the window's left. */
    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun besideAnUprightHingeTheSummaryKeepsToTheSecondHalf() {
        show()
        val hinge = FoldingFeature(
            compose.activity, center = 700, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED,
            orientation = Orientation.VERTICAL,
        )
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        val bar = bounds(MAP_BAR)
        assertEquals(350f + 12f, bar.left.value, 0.5f)
        assertEquals(700f - 12f, bar.right.value, 0.5f)
        assertEquals(840f - 12f, bar.bottom.value, 0.5f)
    }

    /** A foldable half open on a table, its hinge 700 down the window's 840. */
    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun withALevelHingeTheSummaryKeepsUnderIt() {
        fontScale = 2f
        show()
        val hinge = FoldingFeature(
            compose.activity, center = 1400, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED,
            orientation = Orientation.HORIZONTAL,
        )
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        // At the largest text it scrolls in the 140 under the hinge less 24, 12 under it and 12 above the foot, and is
        // centred, as without a hinge.
        val bar = bounds(MAP_BAR)
        assertEquals(840f - 12f, bar.bottom.value, 0.5f)
        assertEquals(140f - 24f, (bar.bottom - bar.top).value, 0.5f)
        assertEquals(350f, (bar.left.value + bar.right.value) / 2, 0.5f)
    }

    // TalkBack.

    private fun talkBack(): View {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view
    }

    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    @Test
    fun talkBackGoesToTheMapAsItOpensAndBackToTheGlobeAsItCloses() {
        show(AuthenticationState.Welcome)
        val view = talkBack()
        open("Birthday gift")
        node(PAGE_GLOBE).performClick()
        settle()
        assertTrue(node(MAP_COVER_MAP).hasTalkBackFocus(view))
        // Nothing under the map is reached meanwhile.
        assertFalse(exists(PAGE_CONTENT))
        node(MAP_CLOSE).performClick()
        settle()
        assertTrue(node(PAGE_GLOBE).hasTalkBackFocus(view))
    }
}
