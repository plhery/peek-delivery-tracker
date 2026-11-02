package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelSort
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's motion (A-AN09): its cards move on the snappy 0.32 s only as the store's parcels on their way change
 * (`.animation(.snappy(duration: 0.32), value: store.parcels.filter { !$0.isArchived }.map(\.id))`); a query or a sort
 * moves them at once, in place as the list next takes its new places.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ListMotionTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var state: DeliveriesState

    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        var deliveries: DeliveriesState? = null
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
        state = checkNotNull(deliveries)
    }

    /** The clock moved on `milliseconds` a frame at a time, what each frame sets off run before the next. */
    private fun advance(milliseconds: Long) {
        repeat((milliseconds / FRAME).toInt()) {
            compose.runOnUiThread { Snapshot.sendApplyNotifications() }
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
    }

    /** The top of each card on screen in the list, by its label; not a card leaving as another takes its label. */
    private fun tops(): Map<String, Float> =
        compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))).fetchSemanticsNodes()
            .mapNotNull { node ->
                val label = node.config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString() ?: return@mapNotNull null
                label to node.boundsInRoot.top / compose.density.density
            }
            .groupBy({ it.first }, { it.second })
            .filterValues { it.size == 1 }
            .mapValues { it.value.single() }

    /**
     * The cards on screen before the change, the frame after the list took it and at rest: those that stay moved, and
     * are already in their new places.
     */
    private fun assertMovedAtOnce(before: Map<String, Float>, next: Map<String, Float>, after: Map<String, Float>) {
        val kept = after.keys.filter { it in before && it in next }
        assertTrue("A card moves: $before to $after", kept.any { abs(after.getValue(it) - before.getValue(it)) > 20 })
        for (label in kept) assertEquals(label, after.getValue(label), next.getValue(label), 0.5f)
    }

    @Test
    fun aQueryMovesTheCardsAtOnce() {
        show()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        val before = tops()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("Belgian")
        advance(FRAME * 2)
        val next = tops()
        advance(1_000)
        assertMovedAtOnce(before, next, tops())
    }

    @Test
    fun aSortMovesTheCardsAtOnce() {
        show()
        val before = tops()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { state.sort = ParcelSort.Newest }
        advance(FRAME * 2)
        val next = tops()
        advance(1_000)
        assertMovedAtOnce(before, next, tops())
    }

    @Test
    fun anArchiveStillClosesUpOnTheSnappyMotion() {
        show()
        val gift = DemoCards.parcel("Birthday gift")
        val before = tops()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            source.parcels.value = source.parcels.value.map { if (it.id == gift.id) it.copy(archivedAt = DateParser.isoString(CAPTURED)) else it }
            Snapshot.sendApplyNotifications()
        }
        advance(FRAME * 6)
        val mid = tops()
        advance(1_000)
        val after = tops()
        val moving = after.keys.filter { it in before && it in mid && abs(after.getValue(it) - before.getValue(it)) > 20 }
        assertTrue("The cards under it close up: $before to $after", moving.isNotEmpty())
        for (label in moving) {
            val (from, to) = before.getValue(label) to after.getValue(label)
            val now = mid.getValue(label)
            assertTrue("$label is on its way at $now, from $from to $to", abs(now - to) > 2 && abs(now - from) > 2)
        }
    }

    private companion object {
        /** The test clock's frame, in milliseconds. */
        const val FRAME = 16L
    }
}
