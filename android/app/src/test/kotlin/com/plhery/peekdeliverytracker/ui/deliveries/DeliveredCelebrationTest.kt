package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isActive
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
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
 * A parcel delivered as the list is looked at, on a phone: its card held with the postmark, the tap and one burst of
 * paper, then the cards flown to their new places; nothing held when nobody looks or under Reduce Motion; the flights
 * ended without a trace as the list goes, turns or takes another size, or a parcel opens; and the rating the arrival may
 * ask for once its card has landed, through a fake of Play's prompt.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DeliveredCelebrationTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var view: View
    private var windowWidth by mutableStateOf(390.dp)

    /** The bursts of paper thrown, each asking for its seed once. */
    private var bursts = 0

    /** The demo's parcels as they are, then as each check of every parcel finds them, each a stage further. */
    private val demo: List<List<Parcel>> by lazy {
        val repository = demoRepository()
        val first = repository.list()
        listOf(first) + List(6) { repository.refreshAll().let { checked -> first.map { parcel -> checked.first { it.id == parcel.id } } } }
    }

    private val before: List<Parcel> get() = demo[0]

    /** The parcels after the check: New trainers and Belgian chocolate delivered, Moon lamp out for delivery. */
    private val after: List<Parcel> get() = demo[1]

    private fun demoRepository(): DemoRepository {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val preferences = context.getSharedPreferences("celebration-${UUID.randomUUID()}", Context.MODE_PRIVATE)
        return DemoRepository(PreferencesDefaults(preferences), { AppLanguage.En }, { CAPTURED })
    }

    /** `parcels` with the parcel whose name starts with `name` as `found` has it. */
    private fun List<Parcel>.with(name: String, found: List<Parcel>): List<Parcel> =
        map { parcel -> if (parcel.label.startsWith(name)) found.first { it.id == parcel.id } else parcel }

    private fun show(parcels: List<Parcel> = before, reduceMotion: Boolean = false, recreation: Recreation? = null) {
        source = FakeDeliveries(parcels, AuthenticationState.Demo)
        val content = @Composable {
            view = LocalView.current
            CompositionLocalProvider(LocalPaperSeeds provides { bursts++; SEED }) {
                Box(Modifier.width(windowWidth).fillMaxHeight()) {
                    RootHost(state = AuthenticationState.Demo, reduceMotion = reduceMotion, source = source)
                }
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    /** The check's parcels come from the store. */
    private fun deliver(parcels: List<Parcel> = after) {
        compose.runOnUiThread { source.parcels.value = parcels }
        frame()
    }

    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    /** The clock moved on `milliseconds`, after what a tap or the back gesture changed is seen. */
    private fun advance(milliseconds: Long) {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeBy(milliseconds)
    }

    /** The clock moved a frame at a time until `done`, within `seconds`. */
    private fun advanceUntil(seconds: Int = 3, done: () -> Boolean) {
        repeat(seconds * 60) {
            if (done()) return
            compose.mainClock.advanceTimeByFrame()
        }
        throw AssertionError("Not done in $seconds s")
    }

    private fun shows(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun cards(name: String): Int = compose.onAllNodes(cardNamed(name)).fetchSemanticsNodes().size

    private fun top(name: String): Float = compose.card(name).getUnclippedBoundsInRoot().top.value

    private fun tapped(): Boolean = shadowOf(view).lastHapticFeedbackPerformed() == HapticFeedbackConstants.CONFIRM

    /** The cards in flight have landed, and each parcel shows one card, Moon lamp's as Next up's at the list's top. */
    private fun assertLanded(nextTop: Float) {
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(nextTop, top("Moon lamp"), 1f)
        assertTrue(compose.card("Moon lamp").fetchSemanticsNode().config[SemanticsProperties.ContentDescription].joinToString().contains("Next up"))
        assertTrue(cards("New trainers") <= 1)
        assertTrue(cards("Belgian chocolate") <= 1)
    }

    @Test
    fun theDemosCheckFindsTwoParcelsDeliveredAndAnotherNextUp() {
        val trainers = after.first { it.label.startsWith("New trainers") }
        val chocolate = after.first { it.label.startsWith("Belgian chocolate") }
        assertTrue(trainers.isDelivered && chocolate.isDelivered)
        assertEquals(2, after.count { it.isDelivered } - before.count { it.isDelivered })
    }

    @Test
    fun aParcelDeliveredInSightIsPostmarkedThenFliesToItsPlace() {
        show()
        val nextTop = top("New trainers")
        assertFalse(tapped())
        deliver()

        // The cards keep their places while the postmark lands.
        advance(250)
        assertFalse(tapped())
        assertFalse(shows(DELIVERED_PAPER))
        assertEquals(nextTop, top("New trainers"), 0.5f)
        // At 300 ms: one tap of success and one burst of paper, for both parcels.
        advance(100)
        assertTrue(tapped())
        assertEquals(1, bursts)
        assertTrue(shows(DELIVERED_PAPER))
        // The card is read for 650 ms more.
        advance(500)
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(nextTop, top("New trainers"), 0.5f)
        advance(150)
        assertTrue(shows(DELIVERED_FLIGHTS))
        // The list has its new places at once; the cards fly there over it, unseen by TalkBack.
        assertEquals(nextTop, top("Moon lamp"), 1f)
        assertEquals(1, cards("Moon lamp"))
        val flights = compose.onNodeWithTag(DELIVERED_FLIGHTS).fetchSemanticsNode()
        assertTrue(flights.children.isEmpty())
        assertTrue(flights.config.none { it.key == SemanticsProperties.ContentDescription || it.key == SemanticsProperties.Text })

        advanceUntil(2) { !shows(DELIVERED_FLIGHTS) }
        assertLanded(nextTop)
        // The paper falls for its own time, then goes; nothing else was thrown.
        advanceUntil(3) { !shows(DELIVERED_PAPER) }
        assertEquals(1, bursts)
    }

    @Test
    fun underReduceMotionOrWithNobodyLookingTheCardsTakeTheirPlacesAtOnce() {
        show(reduceMotion = true)
        val nextTop = top("New trainers")
        deliver()
        frame()
        assertLanded(nextTop)
        advance(1500)
        assertFalse(tapped())
        assertEquals(0, bursts)
        assertFalse(shows(DELIVERED_FLIGHTS))
    }

    @Test
    fun aParcelDeliveredBehindAnOpenParcelIsntHeld() {
        show()
        val nextTop = top("New trainers")
        compose.card("Birthday gift").performClick()
        advance(1000)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        deliver()
        advance(1500)
        assertFalse(tapped())
        assertEquals(0, bursts)

        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        advance(1000)
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertLanded(nextTop)
    }

    @Test
    fun anotherParcelDeliveredDuringTheHoldHoldsTheCardsLongerAndOneDuringTheFlightsFliesNext() {
        val trainers = before.with("New trainers", after)
        show()
        val nextTop = top("New trainers")
        deliver(trainers)
        advance(500)
        assertEquals(1, bursts)
        // The chocolate arrives as the trainers' card is read: both are held from now, and each postmark taps.
        deliver(trainers.with("Belgian chocolate", after))
        advance(500)
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(nextTop, top("New trainers"), 0.5f)
        assertEquals(2, bursts)
        advance(500)
        assertTrue(shows(DELIVERED_FLIGHTS))

        // A check during the flights that finds nothing more lets them land.
        advance(200)
        deliver(after)
        advanceUntil(2) { !shows(DELIVERED_FLIGHTS) }
        assertLanded(nextTop)
        assertEquals(2, bursts)
    }

    @Test
    fun theFlightsEndAsTheListGoesOrTakesAnotherSize() {
        show()
        val nextTop = top("New trainers")
        deliver()
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        // A narrower window: the cards are at their new places at once.
        windowWidth = 380.dp
        frame()
        frame()
        assertLanded(nextTop)

        // The next check delivers Moon lamp, and the tab is left mid-flight: back on it, each card shows once, at rest.
        deliver(demo[2])
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        compose.onNodeWithContentDescription("Passport").performClick()
        advance(500)
        assertFalse(shows(PARCEL_LIST))
        compose.onNodeWithContentDescription("Deliveries").performClick()
        advance(500)
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(nextTop, top("Trail weekend kit"), 1f)
        assertTrue(cards("Moon lamp") <= 1)
    }

    @Test
    fun aParcelOpenedMidFlightLeavesNoFlightBehind() {
        show()
        val nextTop = top("New trainers")
        deliver()
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        compose.card("Birthday gift").performClick()
        advance(1000)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        assertFalse(shows(DELIVERED_FLIGHTS))
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        advance(1000)
        assertLanded(nextTop)
    }

    @Test
    fun aWindowMadeAgainDuringTheHoldOrTheFlightsShowsTheCardsAtTheirPlaces() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        val nextTop = top("New trainers")
        deliver()
        advance(600)
        // As after a turn of the phone, or the system closing the app: nothing of the hold is kept.
        recreation.recreate()
        frame()
        assertLanded(nextTop)
        advance(1500)
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(1, bursts)
    }

    @Test
    fun aWindowMadeAgainOrLeftDuringTheFlightsShowsTheCardsAtTheirPlaces() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        val nextTop = top("New trainers")
        deliver()
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        recreation.recreate()
        frame()
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertLanded(nextTop)

        // The next check delivers Moon lamp, and the app goes to the background mid-flight: back, the cards are at rest.
        deliver(demo[2])
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        advance(100)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        frame()
        assertFalse(shows(DELIVERED_FLIGHTS))
        assertEquals(nextTop, top("Trail weekend kit"), 1f)
        assertTrue(cards("Moon lamp") <= 1)
    }

    @Test
    fun onlyACardThatShowsWholeIsFlown() {
        // Every parcel delivered but New trainers, Next up: its card, cut by the top of the list, takes its place unflown.
        val all = demo.last()
        assertTrue(all.none { it.isActive })
        show(all.with("New trainers", before))
        val scroll = top("New trainers") + 30f
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, height * 0.7f))
            repeat(30) { moveBy(Offset(0f, -(scroll + 8f) * density / 30)) }
            advanceEventTime(300)
            up()
        }
        advance(1000)
        assertTrue(top("New trainers") < 0f)

        deliver(all)
        repeat(90) {
            frame()
            assertFalse(shows(DELIVERED_FLIGHTS))
        }
        assertTrue(cards("New trainers") <= 1)
    }

    @Test
    fun aParcelSeenArrivingAsksForARatingOnceItsCardHasLanded() {
        show()
        usedTwoEarlierDays()
        deliver()
        advance(1050)
        assertTrue(shows(DELIVERED_FLIGHTS))
        assertEquals(emptyList<String>(), source.reviewCalls)
        advanceUntil(2) { !shows(DELIVERED_FLIGHTS) }
        frame()
        // Play's review info is fetched as the moment settles, two seconds, then the prompt shows and the ask counts.
        assertEquals(listOf("prepare"), source.reviewCalls)
        advance(1900)
        assertEquals(listOf("prepare"), source.reviewCalls)
        advance(200)
        assertEquals(listOf("prepare", "launch"), source.reviewCalls)
        assertEquals(1, source.actions.count { it == "review-ask" })
        assertFalse(source.reviewPrompt.isDue(CAPTURED, calm = true))
    }

    @Test
    fun anAskWaitsWhileTheListIsBusyAndNoneComesWithoutPlay() {
        show()
        usedTwoEarlierDays()
        deliver()
        advance(1050)
        advanceUntil(2) { !shows(DELIVERED_FLIGHTS) }
        frame()
        assertEquals(listOf("prepare"), source.reviewCalls)
        // A check starts as the moment settles: nothing is asked, and the ask waits for the list to be calm again.
        advance(1000)
        compose.runOnUiThread { source.refreshing.value = true }
        advance(3000)
        assertEquals(listOf("prepare"), source.reviewCalls)
        // Back calm, but Play has no review info: nothing shows, and the version keeps its ask.
        source.reviewInfo = false
        compose.runOnUiThread { source.refreshing.value = false }
        advance(2500)
        assertEquals(listOf("prepare", "prepare"), source.reviewCalls)
        assertEquals(0, source.actions.count { it == "review-ask" })
        assertTrue(source.reviewPrompt.isDue(CAPTURED, calm = true))
    }

    @Test
    fun aDayThatWentWrongAsksNothing() {
        show()
        usedTwoEarlierDays()
        // The list's notice of a failure, shown a moment: no ask for the rest of the day.
        compose.runOnUiThread { source.errorMessage.value = "The service is down" }
        frame()
        compose.runOnUiThread { source.errorMessage.value = null }
        deliver()
        advance(5000)
        assertEquals(emptyList<String>(), source.reviewCalls)
        assertFalse(source.reviewPrompt.isDue(CAPTURED, calm = true))
    }

    @Test
    fun theDemosParcelsAskNothing() {
        show()
        usedTwoEarlierDays()
        // Made up, they are never the moment (`ReviewPrompt.settled`), as the app's container says of them.
        source.reviewSettled.value = false
        deliver()
        advance(5000)
        assertEquals(emptyList<String>(), source.reviewCalls)
        assertTrue(source.reviewPrompt.isDue(CAPTURED, calm = true))
    }


    /** The list was in use on the two days before the captures' one, which makes it the third. */
    private fun usedTwoEarlierDays() {
        source.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(2)))
        source.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(1)))
    }

    private companion object {
        /** One burst of paper, the same in every run. */
        const val SEED = 0x5EED_CAFEuL
    }
}
