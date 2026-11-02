package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.UUID
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The counts' roll (A-S66, `.contentTransition(.numericText())`): a count that changes with a change of the list SwiftUI
 * animates rolls its changed figures up, the new one from under the old, whichever way the number goes, and the others
 * stay; under Reduce Motion it just changes, but the release cross-fades; a count that changes otherwise just changes.
 * The list makes a new change as its parcels change (added here), none for a search, and the release's as the held
 * parcels are let go (DeliveredCelebrationScreenshotTest). Paused mid-roll, and a shot of it.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class CountRollTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var count by mutableIntStateOf(17)
    private var change: ListChange? by mutableStateOf(null)

    /** The pill alone, on the background, with the clock in the test's hands. */
    private fun pill(reduceMotion: Boolean = false) {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, carrierIdentities = null) {
                Box(Modifier.testTag(SHOT).background(PeekTheme.colors.background).padding(12.dp)) {
                    CountPill(count, horizontalPadding = 6.dp, change)
                }
            }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    private fun set(count: Int, change: ListChange? = this.change) {
        compose.runOnUiThread {
            this.count = count
            this.change = change
            Snapshot.sendApplyNotifications()
        }
    }

    /** The clock moved on `milliseconds` a frame at a time, what each frame sets off run before the next. */
    private fun advance(milliseconds: Long) {
        repeat((milliseconds / FRAME).toInt()) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
    }

    /** Each figure shown, as its text and its top, in the order drawn; [within] the node tagged so, if given. */
    private fun figures(within: String? = null): List<Pair<String, Float>> {
        val matcher = hasTestTag(COUNT_FIGURE).let { if (within == null) it else it and hasAnyAncestor(hasTestTag(within)) }
        val nodes = compose.onAllNodes(matcher, useUnmergedTree = true)
        return nodes.fetchSemanticsNodes().indices.map { index ->
            val node = nodes[index]
            val text = node.fetchSemanticsNode().config[CountFigure]
            text to node.getUnclippedBoundsInRoot().top.value
        }
    }

    private fun top(figures: List<Pair<String, Float>>, text: String): Float = figures.single { it.first == text }.second

    @Test
    fun aChangeOfTheListRollsTheFiguresThatChangeUpAndTheOthersStay() {
        pill()
        val rest = top(figures(), "7")
        set(18, ListChange(releasing = false))
        advance(96)
        val mid = figures()
        assertEquals(listOf("1", "7", "8"), mid.map { it.first }.sorted())
        assertEquals(rest, top(mid, "1"), 0.01f)
        assertTrue("the old figure goes up", top(mid, "7") < rest - 1)
        assertTrue("the new one comes up from under it", top(mid, "8") > rest + 1)
        advance(1000)
        assertEquals(listOf("1" to rest, "8" to rest), figures())
    }

    @Test
    fun aCountGoingDownRollsUpToo() {
        pill()
        val rest = top(figures(), "7")
        set(16, ListChange(releasing = false))
        advance(96)
        val mid = figures()
        assertTrue(top(mid, "7") < rest - 1)
        assertTrue(top(mid, "6") > rest + 1)
    }

    @Test
    fun theReleaseRollsTheFiguresUpWithItsSpring() {
        pill()
        val rest = top(figures(), "7")
        set(15, ListChange(releasing = true))
        advance(96)
        val mid = figures()
        assertTrue(top(mid, "7") < rest - 1)
        assertTrue(top(mid, "5") > rest + 1)
        advance(1500)
        assertEquals(listOf("1", "5"), figures().map { it.first })
    }

    @Test
    fun aCountChangedWithoutANewChangeOfTheListJustChanges() {
        change = ListChange(releasing = false)
        pill()
        val rest = top(figures(), "7")
        // A search or a filter: the count changes, the list's last change stays.
        set(12)
        advance(FRAME)
        assertEquals(listOf("1" to rest, "2" to rest), figures())
        set(3, change = null)
        advance(FRAME)
        assertEquals(listOf("3" to rest), figures())
    }

    @Test
    fun underReduceMotionTheListsChangesJustChangeTheCount() {
        pill(reduceMotion = true)
        val rest = top(figures(), "7")
        set(18, ListChange(releasing = false))
        advance(FRAME)
        assertEquals(listOf("1" to rest, "8" to rest), figures())
    }

    @Test
    fun underReduceMotionTheReleaseCrossFades() {
        pill(reduceMotion = true)
        val rest = top(figures(), "7")
        set(15, ListChange(releasing = true))
        advance(96)
        val mid = figures()
        assertEquals(listOf("1", "5", "7"), mid.map { it.first }.sorted())
        assertTrue("nothing moves", mid.all { (_, top) -> abs(top - rest) < 0.01f })
        advance(1500)
        assertEquals(listOf("1" to rest, "5" to rest), figures())
    }

    @Test
    fun midRoll() {
        pill()
        set(18, ListChange(releasing = false))
        advance(96)
        compose.screenshot("count_roll_light")
    }

    @Test
    fun aParcelAddedRollsTheOverviewsCountAndASearchJustChangesIt() {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        var deliveries: DeliveriesState? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
        val state = checkNotNull(deliveries)
        val before = figures(ACTIVE_COUNT)
        val active = before.joinToString("") { it.first }.toInt()
        val rest = before.last().second
        val first = state.listChange
        compose.mainClock.autoAdvance = false
        // Another parcel on its way, as a copy of one with an id of its own.
        val added = source.parcels.value.first { it.id in state.layout.active.map { parcel -> parcel.id } }.copy(id = UUID(0, 0xADD))
        compose.runOnUiThread {
            source.parcels.value = listOf(added) + source.parcels.value
            Snapshot.sendApplyNotifications()
        }
        advance(96)
        val change = checkNotNull(state.listChange)
        assertNotSame(first, change)
        assertFalse(change.releasing)
        val units = figures(ACTIVE_COUNT).filter { (_, top) -> abs(top - rest) > 1 }
        assertEquals(
            "the units roll from ${active % 10} to ${(active + 1) % 10}",
            setOf("${active % 10}", "${(active + 1) % 10}"),
            units.map { it.first }.toSet(),
        )
        advance(1000)
        assertEquals("${active + 1}", figures(ACTIVE_COUNT).joinToString("") { it.first })
        // A parcel renamed is no change of the list.
        compose.runOnUiThread {
            source.parcels.value = source.parcels.value.map { if (it.id == added.id) it.copy(label = "Renamed") else it }
            Snapshot.sendApplyNotifications()
        }
        advance(FRAME)
        assertSame(change, state.listChange)
        // A search narrows the list: the count just changes.
        compose.mainClock.autoAdvance = true
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("Renamed")
        advance(FRAME * 2)
        assertSame(change, state.listChange)
        assertEquals(listOf("1"), figures(ACTIVE_COUNT).map { it.first })
    }

    private companion object {
        /** The test clock's frame, in milliseconds. */
        const val FRAME = 16L
    }
}
