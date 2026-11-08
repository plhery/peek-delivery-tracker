package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.height
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
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
 * The list's feedback on a phone: the pull to refresh (A-G06) with its thresholds, the check it starts, and how it
 * ends, the list back at its top however it is thrown into it; the message under the list and its four seconds, or eight
 * for the parcels a full account left on the phone; the Undo of an archive and its seven, or for as long as TalkBack or
 * Switch Access is on; and what TalkBack is told of each.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ListFeedbackTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private fun show(parcels: List<Parcel> = DemoCards.parcels(), fontScale: Float = 1f) {
        source = FakeDeliveries(parcels, AuthenticationState.Demo)
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        compose.waitForIdle()
    }

    /** The demo's own parcels, which each check moves a stage further, as the demo's check does. */
    private fun demo(): DemoRepository {
        val preferences = ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("feedback-${UUID.randomUUID()}", Context.MODE_PRIVATE)
        return DemoRepository(PreferencesDefaults(preferences), { AppLanguage.En }, { CAPTURED })
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

    /** A quick stroke across the window, from `from` to `to` of its height in 0.3 s, as a thumb flicks the list. */
    private fun stroke(from: Float, to: Float) {
        compose.onRoot().performTouchInput { swipe(Offset(width / 2f, height * from), Offset(width / 2f, height * to), 300) }
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
    private fun announced(): String? = compose.onNodeWithTag(ANNOUNCEMENT).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.ifEmpty { null }

    private fun toast(text: String) = compose.onNode(hasTestTag(INLINE_TOAST) and hasContentDescription(text, substring = true))

    private fun top(name: String): Float = compose.card(name).getUnclippedBoundsInRoot().top.value

    /** The top of the list's first row, "On the way" and its search button. */
    private fun firstRowTop(): Float = compose.onNodeWithTag(SEARCH_BUTTON).getUnclippedBoundsInRoot().top.value

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
    fun atTheLargestTextTheListMakesRoomForThePullsWordsWhileCheckingThenGoesBackUp() {
        show(fontScale = 2f)
        val rest = firstRowTop()
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checked
        compose.mainClock.autoAdvance = false
        pull(520f)
        advance(2000)
        assertEquals(1, source.refreshes)
        // The words take the lines they need, and the list moves further down than the 60-point gap to keep them clear
        // of the bar above and of the list's first row.
        val words = compose.onNodeWithTag(PULL_INDICATOR, useUnmergedTree = true).getUnclippedBoundsInRoot()
        val bar = compose.onNodeWithTag(DEMO_EXIT).getUnclippedBoundsInRoot()
        assertTrue("Words $words taller than the usual 48", words.height.value > 48f)
        assertTrue("Words $words under the bar $bar", words.top.value >= bar.bottom.value)
        assertTrue("Words $words over the first row at ${firstRowTop()}", words.bottom.value <= firstRowTop())
        assertTrue("First row at ${firstRowTop()}, more than 60 under $rest", firstRowTop() > rest + 61f)

        // Once the seal has said how it went, the list goes back up with the gap.
        checked.complete(ParcelStore.RefreshResult.Updated)
        advance(100)
        assertEquals("Tracking updated", announced())
        advance(1800)
        assertEquals(rest, firstRowTop(), 0.5f)
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
        // 1.1 s later, the message comes under the list; TalkBack isn't told it, as VoiceOver isn't: it heard the result.
        advance(1050)
        toast("The service is down").assertDoesNotExist()
        advance(100)
        toast("The service is down").assertExists()
        assertEquals(null, announced())
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
    fun theParcelsAFullAccountLeftOnThePhoneAreSaidOnceForEightSeconds() {
        show()
        compose.mainClock.autoAdvance = false
        val words = "Your deliveries are full, so 2 parcels stayed on this device. Archive or delete a few, and Peek will offer them again."
        compose.runOnUiThread { source.leftOnDevice.value = ParcelStore.LeftOnDevice(2) }
        advance(100)
        toast(words).assertExists()
        assertEquals(words, announced())
        // Said, the news is let go: nothing brings it back.
        assertEquals(null, source.leftOnDevice.value)
        advance(7800)
        toast(words).assertExists()
        advance(200)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        advance(8000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()

        // One parcel, in the singular.
        compose.runOnUiThread { source.leftOnDevice.value = ParcelStore.LeftOnDevice(1) }
        advance(100)
        toast("Your deliveries are full, so 1 parcel stayed on this device. Archive or delete a few, and Peek will offer it again.").assertExists()
    }

    @Test
    fun theUndoLastsSevenSecondsFromTheLastArchiveAndBringsTheParcelBack() {
        show()
        compose.mainClock.autoAdvance = false
        // Read out with its Undo, as on the iPhone.
        compose.assertAnnounced("${DemoCards.parcel("Birthday gift").label} archived. You can undo it.") { archive("Birthday gift") }
        toast("Birthday gift").assertExists()
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
        compose.assertAnnounced("${DemoCards.parcel("New trainers").label} restored") {
            compose.onNodeWithTag(INLINE_TOAST).performTouchInput { click(Offset(width - dp(34f), height / 2f)) }
        }
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
        // The same words again are another message, with four seconds of its own, and news to TalkBack again.
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        advance(100)
        assertEquals(2, source.refreshes)
        assertEquals("No new updates", announced())
        advance(2000)
        toast("No new updates").assertExists()
        advance(2000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()

        // The pull works beside a parcel as well.
        compose.mainClock.autoAdvance = true
        pull(700f)
        compose.waitForIdle()
        assertEquals(3, source.refreshes)
    }

    @Test
    fun theUndoKeepsItsSevenSecondsFromTheArchiveThoughTheWindowIsMadeAgain() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val recreation = Recreation(compose)
        recreation.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        archive("Birthday gift")
        // Archived once the card is out of sight: its seven seconds start.
        advanceUntil { compose.onAllNodes(hasTestTag(INLINE_TOAST)).fetchSemanticsNodes().isNotEmpty() }
        toast("Birthday gift").assertExists()
        advance(3000)
        recreation.recreate()
        advance(500)
        toast("Birthday gift").assertExists()
        advance(3300)
        toast("Birthday gift").assertExists()
        advance(300)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        assertEquals(null, source.undoParcel.value)
    }

    private fun accessibility() = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))

    /** TalkBack, which explores by touch, turned on or off, and the window told, the clock kept where it is. */
    private fun talkBack(on: Boolean) {
        val manager = accessibility()
        compose.runOnUiThread {
            manager.setEnabled(on)
            manager.setTouchExplorationEnabled(on)
        }
        compose.waitForIdle()
    }

    private fun toastShown(): Boolean = compose.onAllNodes(hasTestTag(INLINE_TOAST)).fetchSemanticsNodes().isNotEmpty()

    /** As with VoiceOver, which takes longer than seven seconds to reach the Undo. */
    @Test
    fun withTalkBackTheUndoStaysUntilAnotherMessageTakesItsPlaceAndHasItsSevenOnceTalkBackIsOff() {
        show()
        talkBack(true)
        assertTrue(source.undoClock.held.value)
        compose.mainClock.autoAdvance = false
        archive("Birthday gift")
        advanceUntil(::toastShown)
        advance(30_000)
        toast("Birthday gift").assertExists()
        // TalkBack off: seven seconds from then.
        talkBack(false)
        advance(100)
        assertFalse(source.undoClock.held.value)
        advance(6_400)
        toast("Birthday gift").assertExists()
        advance(1_000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
        assertEquals(null, source.undoParcel.value)

        // On again, the message of a check takes the place of the Undo, which goes.
        talkBack(true)
        advance(100)
        archive("Belgian chocolate")
        advanceUntil(::toastShown)
        advance(10_000)
        toast("Belgian chocolate").assertExists()
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        advance(100)
        assertEquals(null, source.undoParcel.value)
        toast("No new updates").assertExists()
    }

    /**
     * The services on, as Settings lists them, and the window told as the system tells it of their change, with
     * accessibility on, the clock kept where it is.
     */
    private fun servicesOn(services: String) {
        val resolver = compose.activity.contentResolver
        val manager = accessibility()
        compose.runOnUiThread {
            Settings.Secure.putString(resolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, services)
            manager.setEnabled(true)
            resolver.notifyChange(Settings.Secure.getUriFor(Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES), null)
        }
        compose.waitForIdle()
    }

    /** As with Switch Control: turned on or off beside another service too, which Android 12 tells nothing else of. */
    @Test
    @Config(sdk = [31, 37])
    fun withSwitchAccessTheUndoStaysUntilItIsOff() {
        show()
        servicesOn("$OTHER_SERVICE:$SWITCH_ACCESS")
        assertTrue(source.undoClock.held.value)
        compose.mainClock.autoAdvance = false
        archive("Birthday gift")
        advanceUntil(::toastShown)
        advance(30_000)
        toast("Birthday gift").assertExists()
        // Off, the other service still on: seven seconds from then.
        servicesOn(OTHER_SERVICE)
        advance(100)
        assertFalse(source.undoClock.held.value)
        advance(6_400)
        toast("Birthday gift").assertExists()
        advance(1_000)
        compose.onNodeWithTag(INLINE_TOAST).assertDoesNotExist()
    }

    @Test
    fun samsungsUniversalSwitchHoldsTheUndoToo() {
        show()
        servicesOn("com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService")
        assertTrue(source.undoClock.held.value)
    }

    @Test
    fun aPullThrownBackUpGivesItselfBackAndTheListScrollsOnWithTheThrow() {
        show()
        val rest = top("New trainers")
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, dp(200f)))
            // A hundred points down, short of the threshold, held a moment, then thrown up sixty: still pulled as the
            // finger lifts.
            repeat(20) { moveBy(Offset(0f, dp(5f))) }
            advanceEventTime(200)
            repeat(4) { moveBy(Offset(0f, -dp(15f))) }
            up()
        }
        compose.waitForIdle()
        assertEquals(0, source.refreshes)
        assertTrue("The list scrolled on: ${top("New trainers")} from $rest", top("New trainers") < rest - 50f)
    }

    @Test
    @Config(qualifiers = "w360dp-h640dp-xhdpi")
    fun strokesThatFlingTheListBackIntoItsTopLeaveItAtItsTop() {
        val demo = demo()
        show(demo.list())
        source.checkResult = ParcelStore.RefreshResult.Updated
        repeat(3) { source.found += demo.refreshAll() }
        val rest = firstRowTop()
        compose.mainClock.autoAdvance = false
        // Four strokes up into the list, then six down: the list flies back into its top, and the last strokes land
        // on it and pull, which checks the parcels and delivers New trainers.
        repeat(4) {
            stroke(0.75f, 0.25f)
            advance(600)
        }
        repeat(6) {
            stroke(0.25f, 0.75f)
            advance(400)
        }
        advance(5000)
        assertTrue(source.refreshes > 0)
        assertTrue(source.parcels.value.first { it.label.startsWith("New trainers") }.isDelivered)
        assertEquals(0f, deliveries.listPull.past, 0f)
        assertEquals(0f, deliveries.listPull.held.value, 0f)
        assertEquals(0f, deliveries.contentShift, 1f)
        assertEquals(rest, firstRowTop(), 0.5f)
    }

    @Test
    fun aPullThatCatchesTheListsBounceAndLosesItsTouchSpringsHome() {
        show()
        val rest = top("New trainers")
        compose.mainClock.autoAdvance = false
        // Scrolled 250 points into the list, then thrown back: it flies into its top and bounces past it.
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, dp(400f)))
            repeat(25) { moveBy(Offset(0f, -dp(10f))) }
            advanceEventTime(200)
            up()
        }
        advance(500)
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput { swipe(Offset(width / 2f, dp(200f)), Offset(width / 2f, dp(300f)), 50) }
        advanceUntil { deliveries.listState.firstVisibleItemIndex == 0 && deliveries.listState.firstVisibleItemScrollOffset == 0 }
        advance(50)
        assertTrue("Bouncing: ${top("New trainers")} from $rest", top("New trainers") > rest + 5f)
        // A finger catches it there and pulls it on a hundred points, and the system takes the touch away.
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, dp(200f)))
            repeat(20) { moveBy(Offset(0f, dp(5f))) }
            cancel()
        }
        advance(3000)
        assertEquals(0, source.refreshes)
        assertEquals(0f, deliveries.listPull.past, 0f)
        assertEquals(rest, top("New trainers"), 0.5f)
    }
}

/** Switch Access's service, as Settings lists it. */
private const val SWITCH_ACCESS = "com.google.android.accessibility.switchaccess/com.android.switchaccess.SwitchAccessService"

/** A service of another kind, which stays on. */
private const val OTHER_SERVICE = "com.example.passwords/com.example.passwords.FillService"
