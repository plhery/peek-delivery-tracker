package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isActive
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
import java.time.Instant
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
 * A delivery the list didn't see arrives on it once it is back in view, as on the iPhone (`see(_:)`, OnTheWayRecord):
 * back from the parcel's page, at the app's next start, on the tab chosen again; several parcels in one burst of
 * paper, the postmark 700 ms after the list comes into view where a live one's lands after 300; once only, and never
 * for a delivery a week old, an archived parcel or the demo's, nor beside its page on a tablet, where the list saw it
 * arrive. Under Remove animations nothing moves, but the moment counts for a rating. The record is written only while
 * the list is in view with parcels on it. The demo's made-up parcels, followed by a synthetic signed-in reader.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DeliveredCatchUpTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    /** The deliveries' stores of the process shown: a start of the app again makes new ones on the same defaults. */
    private var source by mutableStateOf<FakeDeliveries?>(null)
    private val stores: FakeDeliveries get() = source!!
    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState

    /** The app's clock, which a test moves on. */
    private var clock: Instant = CAPTURED

    /** The bursts of paper thrown, each asking for its seed once. */
    private var bursts = 0

    /** The demo's parcels as they are, then as the next check of every parcel finds them. */
    private val demo: List<List<Parcel>> by lazy {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val preferences = context.getSharedPreferences("catch-up-${UUID.randomUUID()}", Context.MODE_PRIVATE)
        val repository = DemoRepository(PreferencesDefaults(preferences), { AppLanguage.En }, { CAPTURED })
        val first = repository.list()
        listOf(first, repository.refreshAll().let { checked -> first.map { parcel -> checked.first { it.id == parcel.id } } })
    }

    private val before: List<Parcel> get() = demo[0]

    /** The parcels after the check: New trainers and Belgian chocolate delivered, at the captures' moment. */
    private val after: List<Parcel> get() = demo[1]

    /** New trainers and Belgian chocolate. */
    private val arrived: Set<UUID> get() = after.filter { it.isDelivered }.map { it.id }.toSet() - before.filter { it.isDelivered }.map { it.id }.toSet()

    private fun show(parcels: List<Parcel> = before, session: AuthenticationState = READER, reduceMotion: Boolean = false) {
        source = FakeDeliveries(parcels, session)
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalPaperSeeds provides { bursts++; SEED }, LocalDeliveriesState provides { deliveries = it }) {
                val shown = stores
                // A start of the app again: nothing of the window before is kept.
                key(shown) { RootHost(state = session, reduceMotion = reduceMotion, source = shown, now = { clock }) }
            }
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    /** The app started again, its stores' parcels being `parcels` by then. */
    private fun startAgain(parcels: List<Parcel>) {
        val next = stores.newProcess()
        next.parcels.value = parcels
        compose.runOnUiThread { source = next }
        frame()
        frame()
    }

    /** The check's parcels come from the store. */
    private fun deliver(parcels: List<Parcel> = after) {
        compose.runOnUiThread { stores.parcels.value = parcels }
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

    /** The page of the parcel named `name`, opened from its card over the list. */
    private fun open(name: String) {
        compose.card(name).performClick()
        advance(1000)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    /** The system's back, from a page to the list. */
    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        frame()
    }

    /** The app sent to the background a moment, and brought back. */
    private fun awayAndBack() {
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        advance(100)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        frame()
    }

    private fun shows(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether the list has the parcel named `name` on Next up's card (which doesn't say so of one held delivered). */
    private fun nextUp(name: String): Boolean = compose.runOnUiThread {
        deliveries.layout.next?.label?.startsWith(name) == true
    }

    private fun tapped(): Boolean = shadowOf(view).lastHapticFeedbackPerformed() == HapticFeedbackConstants.CONFIRM

    /** The record's parcels: those the list last showed on their way. */
    private val recorded: Set<UUID> get() = compose.runOnUiThread { stores.onTheWay.ids }

    /** Nothing arrives on the list: no hold, no postmark, no paper but the `bursts` thrown before. */
    private fun assertNothingArrives(bursts: Int = 0) {
        advance(1500)
        assertTrue(deliveries.hold.isEmpty)
        if (bursts == 0) assertFalse(tapped())
        assertEquals(bursts, this.bursts)
        assertFalse(shows(DELIVERED_FLIGHTS))
    }

    /**
     * The held parcels' postmark lands 700 ms after the list came into view, the cards having come to rest, with one tap
     * and one burst of paper for them all; they are then forgotten, and fly to the past deliveries 650 ms later.
     */
    private fun assertArrivesLate(bursts: Int = 1) {
        advance(650)
        assertEquals(arrived, deliveries.hold.ids)
        assertTrue(deliveries.hold.late)
        assertEquals(bursts - 1, this.bursts)
        advance(120)
        assertTrue(tapped())
        assertEquals(bursts, this.bursts)
        assertEquals(arrived, deliveries.hold.stamped)
        assertTrue(recorded.none { it in arrived })
        assertFalse(shows(DELIVERED_FLIGHTS))
        advance(650)
        assertTrue(shows(DELIVERED_FLIGHTS))
        advanceUntil(2) { !shows(DELIVERED_FLIGHTS) }
        assertTrue(deliveries.hold.isEmpty)
    }

    @Test
    fun aDeliveryWhileItsPageCoversTheListArrivesOnceThePageCloses() {
        show()
        assertEquals(before.filter { it.isActive }.map { it.id }.toSet(), recorded)
        assertTrue(arrived.size == 2 && arrived.all { it in recorded })
        open("New trainers")
        deliver()
        advance(1500)
        assertFalse(tapped())
        assertEquals(0, bursts)

        // Back on the list, both parcels arrive on it from its first frame, held where they stood on their way: New
        // trainers on Next up's card, saying it is delivered. One burst of paper for both.
        back()
        assertEquals(arrived, deliveries.hold.ids)
        assertTrue(nextUp("New trainers"))
        assertArrivesLate()
        assertTrue(nextUp("Moon lamp"))

        // Once: neither the page opened and closed again nor the app brought back plays it again.
        open("Moon lamp")
        back()
        awayAndBack()
        advance(1500)
        assertEquals(1, bursts)
        assertTrue(deliveries.hold.isEmpty)
    }

    @Test
    fun aDeliveryWhileTheAppWasClosedArrivesOnceAtTheNextStartAndNotTheOneAfter() {
        show()
        startAgain(after)
        assertArrivesLate()

        startAgain(after)
        assertNothingArrives(bursts = 1)
    }

    @Test
    fun aListThatGoesOutOfViewBeforeThePostmarkCatchesUpAgain() {
        show()
        startAgain(after)
        advance(300)
        assertEquals(arrived, deliveries.hold.ids)
        // A page opened before the postmark lands, as a parcel's page opening at launch does: the hold is let go
        // unstamped, and plays once the list is back.
        open("Birthday gift")
        assertTrue(deliveries.hold.isEmpty)
        assertEquals(0, bursts)
        assertTrue(arrived.all { it in recorded })
        back()
        assertArrivesLate()
    }

    @Test
    fun aDeliveryWhileAnotherTabShowsArrivesAsTheTabIsChosenAgain() {
        show()
        compose.onNodeWithContentDescription("Passport").performClick()
        advance(500)
        assertFalse(shows(PARCEL_LIST))
        deliver()
        advance(500)
        compose.onNodeWithContentDescription("Deliveries").performClick()
        frame()
        assertEquals(arrived, deliveries.hold.ids)
        assertArrivesLate()
    }

    @Test
    fun aDeliveryAWeekOldIsNoNews() {
        show()
        open("Birthday gift")
        deliver()
        clock = CAPTURED.plus(Duration.ofDays(7))
        back()
        assertNothingArrives()
        // The list showed them delivered: forgotten too.
        assertTrue(recorded.none { it in arrived })
    }

    @Test
    fun anArchivedParcelArrivesOnNothing() {
        show()
        open("Birthday gift")
        val archivedAt = DateParser.isoString(CAPTURED)
        deliver(after.map { if (it.id in arrived) it.copy(archivedAt = archivedAt) else it })
        back()
        assertNothingArrives()
    }

    @Test
    fun theDemosParcelsArriveOnNothingAndAreNotRecorded() {
        show(session = AuthenticationState.Demo)
        assertTrue(recorded.isEmpty())
        open("Birthday gift")
        deliver()
        back()
        assertNothingArrives()
        assertTrue(recorded.isEmpty())
    }

    @Test
    @Config(qualifiers = TABLET)
    fun besideItsPageOnATabletADeliverySeenArrivingDoesntArriveAgain() {
        show()
        assertTrue(shows(PAGE_COLUMN))
        // Seen arriving, it is held as a live one is: its postmark at 300 ms.
        deliver()
        advance(250)
        assertEquals(arrived, deliveries.hold.ids)
        assertFalse(deliveries.hold.late)
        assertEquals(0, bursts)
        advance(100)
        assertEquals(1, bursts)
        assertTrue(recorded.none { it in arrived })
        advanceUntil(3) { deliveries.hold.isEmpty && !shows(DELIVERED_FLIGHTS) }

        // Back in front, the list has nothing to catch up with.
        awayAndBack()
        advance(1500)
        assertTrue(deliveries.hold.isEmpty)
        assertEquals(1, bursts)
    }

    @Test
    fun underRemoveAnimationsNothingMovesButTheArrivalCountsForARating() {
        show(reduceMotion = true)
        stores.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(2)))
        stores.reviewPrompt.used(CAPTURED.minus(Duration.ofDays(1)))
        open("Birthday gift")
        deliver()
        advance(500)
        assertFalse(deliveries.reviewDue)
        back()
        frame()
        // As the cards would have landed (`sawArrival()`): the rating's ask is due, and comes once the moment settles.
        assertTrue(deliveries.reviewDue)
        assertTrue(recorded.none { it in arrived })
        assertNothingArrives()
        advance(1000)
        assertEquals(listOf("prepare", "launch"), stores.reviewCalls)
        assertEquals(1, stores.actions.count { it == "review-ask" })
    }

    @Test
    fun theRecordIsWrittenOnlyWhileTheListIsInViewWithParcelsOnIt() {
        show()
        val onTheWay = before.filter { it.isActive }.map { it.id }.toSet()
        assertEquals(onTheWay, recorded)
        // A parcel added while a page covers the list isn't written down until the list is back.
        val lamp = before.first { it.label.startsWith("Moon lamp") }
        val spare = lamp.copy(id = UUID(0x5EED, 1), trackingNumber = "JJD0099990001", label = "Spare lamp")
        open("Birthday gift")
        deliver(before + spare)
        advance(500)
        assertEquals(onTheWay, recorded)
        back()
        advance(500)
        assertEquals(onTheWay + spare.id, recorded)
        // A list with no parcel, as one still loading is, leaves it as it was.
        deliver(emptyList())
        advance(500)
        assertEquals(onTheWay + spare.id, recorded)
        deliver(before)
        advance(500)
        assertEquals(onTheWay, recorded)
    }

    private companion object {
        /** One burst of paper, the same in every run. */
        const val SEED = 0x5EED_CAFEuL

        const val TABLET = "w1032dp-h1376dp-xhdpi"

        val READER = AuthenticationState.SignedIn(AuthUser(UUID(0, 21), email = "reader@example.com"))
    }
}
