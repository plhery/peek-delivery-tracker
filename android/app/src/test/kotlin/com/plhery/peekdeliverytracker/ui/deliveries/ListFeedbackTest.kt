package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's feedback on a phone: the pull to refresh (A-G05) with its thresholds, the check it starts, and how it
 * ends; the message under the list and its four seconds; the Undo of an archive and its seven; and what TalkBack is
 * told of each.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ListFeedbackTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries

    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
    }

    private fun dp(value: Float): Float = value * compose.density.density

    /** The list pulled down `distance` points by a finger that stops before it lifts. */
    private fun pull(distance: Float) {
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, dp(200f)))
            val steps = 30
            repeat(steps) { moveBy(Offset(0f, dp(distance) / steps)) }
            advanceEventTime(200)
            up()
        }
    }

    private fun advance(milliseconds: Long) = compose.mainClock.advanceTimeBy(milliseconds)

    /** The clock moved a frame at a time until `done`, within three seconds. */
    private fun advanceUntil(done: () -> Boolean) {
        repeat(180) {
            if (done()) return
            compose.mainClock.advanceTimeByFrame()
        }
        throw AssertionError("Not done in 3 s")
    }

    /** What TalkBack was last told, while it is news. */
    private fun announced(): String? = compose.onNodeWithTag(ANNOUNCEMENT, useUnmergedTree = true).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.ifEmpty { null }

    private fun toast(text: String) = compose.onNode(hasTestTag(INLINE_TOAST) and hasContentDescription(text, substring = true))

    private fun top(name: String): Float = compose.card(name).getUnclippedBoundsInRoot().top.value

    /** Archives the parcel named `name` as TalkBack's action on its card does. */
    private fun archive(name: String) {
        val action = compose.card(name).fetchSemanticsNode().config[SemanticsActions.CustomActions].single { it.label == "Archive" }
        compose.runOnUiThread { action.action() }
    }

    @Test
    fun aPullPastTheThresholdChecksEveryParcelAndAShorterOneDoesNot() {
        show()
        val rest = top("New trainers")
        pull(140f)
        compose.waitForIdle()
        assertEquals(0, source.refreshes)
        assertEquals(rest, top("New trainers"), 0.5f)

        pull(520f)
        compose.waitForIdle()
        assertEquals(1, source.refreshes)
        // Once the check is over, the list is back where it was.
        assertEquals(rest, top("New trainers"), 0.5f)
    }

    @Test
    fun theListHoldsItsGapWhileCheckingThenSaysHowItWentAndCloses() {
        show()
        val rest = top("New trainers")
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checked
        compose.mainClock.autoAdvance = false
        pull(520f)
        advance(2000)
        assertEquals(1, source.refreshes)
        // The finger gone, the content rests under the 60-point gap held open over it.
        assertEquals(rest + 60f, top("New trainers"), 1f)
        assertEquals(null, announced())

        checked.complete(ParcelStore.RefreshResult.Updated)
        advance(100)
        assertEquals("Tracking updated", announced())
        // The seal shows its tick 0.7 s, fades 0.45 s, and the gap closes.
        advance(600)
        assertEquals(rest + 60f, top("New trainers"), 1f)
        advance(1200)
        assertEquals(rest, top("New trainers"), 0.5f)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
    }

    @Test
    fun aFailedCheckShowsItsCrossThenItsMessageForFourSeconds() {
        show()
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checked
        compose.mainClock.autoAdvance = false
        // The clock follows the finger: the check starts as the pull passes the threshold, before the finger lifts.
        pull(520f)
        // A check that answers at once still shows the arrow turning for 0.9 s.
        checked.complete(ParcelStore.RefreshResult.Failed("The service is down"))
        advance(200)
        assertEquals(null, announced())
        // Then the cross shows, said.
        advanceUntil { announced() == "Couldn’t check. Try again." }
        // 1.1 s later, the message comes under the list, and TalkBack hears it.
        advance(1050)
        toast("The service is down").assertDoesNotExist()
        advance(100)
        toast("The service is down").assertExists()
        assertEquals("The service is down", announced())
        advance(3850)
        toast("The service is down").assertExists()
        advance(150)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
    }

    @Test
    fun aCheckStillRunningSaysSoAndLeavesNoMessage() {
        show()
        source.checkResult = ParcelStore.RefreshResult.StillChecking
        compose.mainClock.autoAdvance = false
        pull(520f)
        advance(1000)
        assertEquals("Still checking. Updates will appear here.", announced())
        advance(5000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
    }

    @Test
    fun theUndoLastsSevenSecondsFromTheLastArchiveAndBringsTheParcelBack() {
        show()
        compose.mainClock.autoAdvance = false
        archive("Birthday gift")
        advance(1000)
        toast("Birthday gift").assertExists()
        assertEquals("Birthday gift 🎁 archived", announced())
        advance(5000)
        // Another archive takes the toast, for seven seconds of its own.
        archive("Belgian chocolate")
        advance(1000)
        toast("Belgian chocolate").assertExists()
        advance(5500)
        toast("Belgian chocolate").assertExists()
        advance(1000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        assertEquals(null, source.undoParcel.value)

        // Undo, at the toast's end.
        archive("New trainers")
        advance(1000)
        compose.onNodeWithTag(INLINE_TOAST).performTouchInput { click(Offset(width - dp(34f), height / 2f)) }
        advance(500)
        assertEquals(listOf(DemoCards.parcel("New trainers").id), source.restored)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        compose.card("New trainers").assertExists()
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun besideAParcelTheButtonChecksTooAndEachNewMessageHasFourSeconds() {
        show()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        advance(100)
        assertEquals(1, source.refreshes)
        toast("No new updates").assertExists()
        assertEquals("No new updates", announced())
        advance(3000)
        source.checkResult = ParcelStore.RefreshResult.Updated
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        advance(100)
        toast("Tracking updated").assertExists()
        advance(2000)
        toast("Tracking updated").assertExists()
        advance(2000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()

        // The pull works beside a parcel as well.
        compose.mainClock.autoAdvance = true
        pull(700f)
        compose.waitForIdle()
        assertEquals(3, source.refreshes)
    }
}
