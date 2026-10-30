package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentEvent
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
import java.util.UUID
import kotlin.math.abs
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The parcel page's sync line (`syncStatus`, B-S08): when the next check comes, when the carrier was last asked and
 * when it last said something, each shown and hidden by its conditions; its refresh, only where there is a carrier to
 * ask again, which checks the parcel one change at a time with the page's other changes and says why it failed, and
 * keeps its 26 where the lines wrap; and its arrow, which turns a turn a second only while it checks, or pulses under
 * Reduce Motion (B-AN08).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SyncLineTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val localizer = Localizer(AppLanguage.En, variant = "android")

    private val gift: UUID get() = DemoCards.parcel("Birthday gift").id

    /** The demo, Birthday gift changed by `change`. */
    private fun demo(change: (Parcel) -> Parcel = { it }): List<Parcel> =
        DemoCards.parcels().map { if (it.id == gift) change(it) else it }

    /** Birthday gift's next check, at 15:05 in Zurich the day after the captures. */
    private fun Parcel.nextCheck(): Parcel =
        copy(carrierData = (carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(nextCheckAt = "2026-10-30T14:05:00Z")))

    private fun show(parcels: List<Parcel> = demo(), reduceMotion: Boolean = true, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(parcels, AuthenticationState.Demo).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
            }
        }
        compose.waitForIdle()
    }

    /** Opens the page of the parcel with `id`, as a notification does, and scrolls to its sync line. */
    private fun open(id: UUID = gift) {
        compose.runOnUiThread { deliveries.navigator.open(id) }
        settle()
        if (exists(PAGE_SYNC_LINE)) compose.onNodeWithTag(PAGE_SYNC_LINE).performScrollTo()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun texts(node: SemanticsNode): List<String> = node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    /** The sync line's lines, in their order. */
    private fun lines(): List<String> =
        compose.onAllNodes(hasAnyAncestor(hasTestTag(PAGE_SYNC_LINE)), useUnmergedTree = true).fetchSemanticsNodes().flatMap(::texts)

    private fun parcel(): Parcel = source.parcels.value.first { it.id == gift }

    @Test
    fun theLineSaysWhenTheNextCheckComesAndWhenTheCarrierWasLastAskedAndLastSaidSomething() {
        show(demo { it.nextCheck() })
        open()
        val parcel = parcel()
        assertEquals(
            listOf(
                "Next check after Oct 30, 2026 at 3:05 PM",
                "Last checked: ${localizer.relativeTime(parcel.lastSyncedAt!!, CAPTURED)}",
                "Last shipment update: ${localizer.relativeTime(parcel.currentEvent!!, CAPTURED)}",
            ),
            lines(),
        )
        // The demo checked it 12 minutes before the captures.
        assertEquals("Last checked: ${localizer.text("time.minutesAgo", mapOf("count" to 12))}", lines()[1])
        compose.onNodeWithTag(PAGE_REFRESH).assertContentDescriptionEquals("Check now").assertIsEnabled()
        assertEquals(Role.Button, compose.onNodeWithTag(PAGE_REFRESH).fetchSemanticsNode().config[SemanticsProperties.Role])
    }

    @Test
    fun anArchivedParcelSaysNoNextCheckAndHasNoRefresh() {
        val archived = DateParser.isoString(CAPTURED.minus(Duration.ofDays(2)))
        show(demo { it.nextCheck().copy(archivedAt = archived) })
        open()
        assertEquals(2, lines().size)
        assertFalse(lines().any { it.startsWith("Next check") })
        assertFalse(exists(PAGE_REFRESH))
    }

    @Test
    fun neverCheckedOrOnlyPendingTheLineSaysNeither() {
        show(
            demo {
                it.copy(
                    lastSyncedAt = null,
                    trackingEvents = it.trackingEvents.map { event -> event.copy(stage = TrackingStage.Pending) },
                )
            },
        )
        open()
        assertEquals(TrackingStage.Pending, parcel().currentEvent?.stage)
        assertEquals(emptyList<String>(), lines())
        // The refresh stays, alone on the line.
        assertTrue(exists(PAGE_REFRESH))
    }

    @Test
    fun withNothingToSayAndNoCarrierToAskThereIsNoLine() {
        val archived = DateParser.isoString(CAPTURED.minus(Duration.ofDays(2)))
        show(demo { it.copy(lastSyncedAt = null, trackingEvents = emptyList(), archivedAt = archived) })
        open()
        assertFalse(exists(PAGE_SYNC_LINE))
    }

    @Test
    fun aCarrierPeekDoesNotAskHasNoRefresh() {
        val manual = CarrierCatalog.shared.definitions.keys.first { it != CarrierId.Unknown && !CarrierCatalog.shared.tracksAutomatically(it) }
        show(demo { it.copy(carrier = manual, carrierData = null) })
        open()
        assertTrue(exists(PAGE_SYNC_LINE))
        assertFalse(exists(PAGE_REFRESH))
    }

    @Test
    fun theSampleHasNoRefresh() {
        show()
        compose.runOnUiThread { deliveries.openSample() }
        settle()
        assertFalse(exists(PAGE_REFRESH))
    }

    @Test
    fun theRefreshChecksTheParcelAgainOneChangeAtATimeWhileThePageWaits() {
        show { checkingParcel = CompletableDeferred() }
        open()
        compose.onNodeWithTag(PAGE_REFRESH).performClick()
        settle()
        assertTrue(deliveries.pageWorking(gift))
        compose.onNodeWithTag(PAGE_REFRESH).assertIsNotEnabled()
        compose.onNodeWithTag(PAGE_BELL).assertIsNotEnabled()
        // A second check, the bell or the switch, however they come, are not asked for.
        compose.runOnUiThread {
            deliveries.refreshOnPage(gift, parcel())
            deliveries.muteOnPage(gift, parcel(), muted = true) {}
            deliveries.switchCarrierOnPage(gift, parcel(), CarrierId.Ups)
        }
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        settle()
        assertEquals(listOf(gift), source.checked)
        assertEquals(emptyList<Any>(), source.mutes)
        assertEquals(emptyList<Any>(), source.carrierChanges)
        assertFalse(deliveries.pageWorking(gift))
        compose.onNodeWithTag(PAGE_REFRESH).assertIsEnabled()
    }

    @Test
    fun whileTheBellSavesTheRefreshWaits() {
        show { muting = CompletableDeferred() }
        open()
        compose.runOnUiThread { deliveries.muteOnPage(gift, parcel(), muted = true) {} }
        settle()
        compose.onNodeWithTag(PAGE_REFRESH).assertIsNotEnabled()
        compose.runOnUiThread { source.muting?.complete(Unit) }
        settle()
        compose.onNodeWithTag(PAGE_REFRESH).assertIsEnabled()
    }

    @Test
    fun aRefreshThatFailedSaysWhyAndCanBeTriedAgain() {
        show { failure = IllegalStateException("No connection") }
        open()
        compose.onNodeWithTag(PAGE_REFRESH).performClick()
        settle()
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        assertTrue(compose.onAllNodes(hasText("No connection", substring = true)).fetchSemanticsNodes().isNotEmpty())
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        settle()
        assertEquals(emptyList<Any>(), source.checked)
        compose.onNodeWithTag(PAGE_REFRESH).assertIsEnabled()
    }

    /**
     * Birthday gift's sync line in French, `width` wide, with the text `fontScale` times the normal: the refresh is
     * whole, 26 square, at the line's end and 7 after its widest line, and a line wraps in what is left.
     */
    private fun assertWrapsBesideTheRefresh(width: Dp, fontScale: Float) {
        val parcel = DemoCards.parcel("Birthday gift").nextCheck()
        compose.setContent {
            CardHost(language = AppLanguage.Fr, fontScale = fontScale) {
                Box(Modifier.width(width)) {
                    SyncLine(parcel, Color.Black, working = false, onRefresh = {}, Modifier.testTag(PAGE_SYNC_LINE))
                }
            }
        }
        compose.waitForIdle()
        val refresh = compose.onNodeWithTag(PAGE_REFRESH).getBoundsInRoot()
        val line = compose.onNodeWithTag(PAGE_SYNC_LINE).getBoundsInRoot()
        val texts = compose.onAllNodes(hasAnyAncestor(hasTestTag(PAGE_SYNC_LINE)) and hasText("", substring = true), useUnmergedTree = true)
            .fetchSemanticsNodes()
        assertEquals(3, texts.size)
        assertEquals(26f, (refresh.right - refresh.left).value, 0.5f)
        assertEquals(26f, (refresh.bottom - refresh.top).value, 0.5f)
        // CardHost stands its content 16 in.
        assertTrue("Refresh $refresh in $line", refresh.right <= line.right + 0.5.dp && line.right <= 16.dp + width + 0.5.dp)
        val widest = with(compose.density) { texts.maxOf { it.boundsInRoot.right }.toDp() }
        assertEquals(7f, (refresh.left - widest).value, 0.5f)
        val lines = texts.map { node ->
            val layouts = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(layouts)
            layouts.single().lineCount
        }
        assertTrue("Lines $lines", lines.any { it > 1 })
    }

    /** At the largest size short of the accessibility sizes, as narrow as the page's two columns stand then. */
    @Test
    fun inFrenchInTheNarrowColumnTheLinesWrapBesideTheWholeRefresh() = assertWrapsBesideTheRefresh(340.dp, fontScale = 1.3f)

    /** At the first accessibility size, in a column as narrow as the two stand then. */
    @Test
    fun atAnAccessibilitySizeTheLinesWrapBesideTheWholeRefresh() = assertWrapsBesideTheRefresh(420.dp, fontScale = 1.5f)

    @Test
    fun theRefreshIs26SquareAndTakesTouchesAroundIt() {
        show()
        open()
        val node = compose.onNodeWithTag(PAGE_REFRESH).fetchSemanticsNode()
        with(compose.density) {
            assertEquals(26f, node.size.width.toDp().value, 0.5f)
            assertEquals(26f, node.size.height.toDp().value, 0.5f)
        }
        // 9 to its left, outside its 26 but inside the 48 Android asks for.
        compose.onNodeWithTag(PAGE_REFRESH).performTouchInput { click(Offset(-9.dp.toPx(), centerY)) }
        settle()
        assertEquals(listOf(gift), source.checked)
    }

    // The arrow.

    private fun arrow(): IntArray = compose.drawn(compose.onNodeWithTag(PAGE_REFRESH))

    /** How much the arrow stands out from the paper: each pixel's distance from the first one's, summed. */
    private fun ink(pixels: IntArray): Long {
        val paper = pixels[0]
        fun channel(pixel: Int, shift: Int) = (pixel shr shift) and 0xFF
        return pixels.sumOf { pixel -> listOf(16, 8, 0).sumOf { abs(channel(pixel, it) - channel(paper, it)) }.toLong() }
    }

    /** The arrow at rest, while it checks at `times` in milliseconds after it started, and once it has checked. */
    private fun arrowWhileChecking(reduceMotion: Boolean, vararg times: Long): Triple<IntArray, List<IntArray>, IntArray> {
        show(reduceMotion = reduceMotion) { checkingParcel = CompletableDeferred() }
        open()
        // An effect runs only on a clock the test moves itself: on one that runs by itself, Compose's tests stop it.
        compose.mainClock.autoAdvance = false
        val rest = arrow()
        compose.mainClock.advanceTimeBy(800)
        assertArrayEquals("Still while nothing is checked", rest, arrow())
        compose.onNodeWithTag(PAGE_REFRESH).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        var elapsed = 0L
        val frames = times.map {
            compose.mainClock.advanceTimeBy(it - elapsed)
            elapsed = it
            arrow()
        }
        compose.runOnUiThread { source.checkingParcel?.complete(Unit) }
        compose.mainClock.advanceTimeBy(500)
        val after = arrow()
        compose.mainClock.autoAdvance = true
        return Triple(rest, frames, after)
    }

    @Test
    fun theArrowTurnsATurnASecondOnlyWhileItChecks() {
        // Frames come every 16 ms: 2 s is two whole turns, and 496 ms nearly half of one.
        val (rest, frames, after) = arrowWhileChecking(reduceMotion = false, 0, 496, 2_000)
        val (start, half, twoTurns) = frames
        assertFalse(start.contentEquals(half))
        assertArrayEquals(start, twoTurns)
        // Turned, the arrow keeps its ink.
        assertEquals(1.0, ink(half).toDouble() / ink(start), 0.15)
        assertArrayEquals("Upright once checked", rest, after)
    }

    @Test
    fun underReduceMotionTheArrowPulsesInsteadOfTurning() {
        // Faded to a third 0.75 s on, and back after 1.5 s: four pulses take 6 s, a whole number of frames.
        val (rest, frames, after) = arrowWhileChecking(reduceMotion = true, 0, 752, 6_000)
        val (start, faded, fourPulses) = frames
        val ratio = ink(faded).toDouble() / ink(start)
        assertTrue("Faded to $ratio", ratio in 0.25..0.5)
        assertArrayEquals(start, fourPulses)
        assertArrayEquals(rest, after)
    }
}
