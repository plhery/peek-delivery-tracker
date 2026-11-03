package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.LocalPaperSeeds
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The "Delivered" moment catching up with a delivery the list didn't see, on this device's own window, with animations
 * on: the demo's made-up parcels followed by a synthetic signed-in reader on fake deliveries, which reach no server.
 * New trainers is delivered while its page is open: back on the list (or at once, where the list stands beside the
 * page and sees it arrive), its postmark lands with one burst of paper, and never again, the app sent away and brought
 * back. The app started again after a delivery while it was closed plays it once, and not at the start after.
 */
@RunWith(AndroidJUnit4::class)
class DeliveredCatchUpDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose)

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 23), email = "reader@example.com"))

    /** The deliveries' stores of the process shown: a start of the app again makes new ones on the same defaults. */
    private var source by mutableStateOf<FakeDeliveries?>(null)
    private val stores: FakeDeliveries get() = checkNotNull(source)

    @Volatile
    private var deliveries: DeliveriesState? = null

    /** The bursts of paper thrown, each asking for its seed once. */
    @Volatile
    private var bursts = 0

    private val before: List<Parcel> = DemoCards.parcels()
    private val trainers: Parcel = DemoCards.parcel("New trainers")

    /** The demo's parcels, New trainers delivered at the captures' moment. */
    private val after: List<Parcel> = before.map { parcel ->
        if (parcel.id != trainers.id) return@map parcel
        val delivered = TrackingEvent(
            id = UUID.nameUUIDFromBytes("${parcel.id}-delivered".toByteArray()), packageId = parcel.id, stage = TrackingStage.Delivered,
            description = "Delivered", location = null, occurredAt = DateParser.isoString(CAPTURED),
        )
        parcel.copy(trackingEvents = parcel.trackingEvents + delivered)
    }

    private fun show() {
        source = FakeDeliveries(before, reader)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalPaperSeeds provides { bursts++; SEED }, LocalDeliveriesState provides { deliveries = it }) {
                val shown = stores
                key(shown) { RootHost(state = reader, reduceMotion = false, source = shown) }
            }
        }
        compose.waitUntil(LONG_MILLIS) { exists(PARCEL_LIST) }
        compose.waitForIdle()
    }

    /** The app started again, its stores' parcels being `parcels` by then. */
    private fun startAgain(parcels: List<Parcel>) {
        val next = stores.newProcess()
        next.parcels.value = parcels
        compose.runOnUiThread { source = next }
        compose.waitUntil(LONG_MILLIS) { exists(PARCEL_LIST) }
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun card(label: String): SemanticsMatcher =
        hasContentDescription(label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))

    private fun stacked(): Boolean = compose.runOnUiThread { deliveries?.arrangement } == DeliveriesArrangement.Stacked

    private fun holding(): Boolean = compose.runOnUiThread { deliveries?.hold?.isEmpty == false }

    private fun recorded(): Set<UUID> = compose.runOnUiThread { stores.onTheWay.ids }

    /** Two seconds of the app's time, and the UI idle. */
    private fun settle() {
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
    }

    @Test
    fun aParcelDeliveredWhileItsPageIsOpenArrivesOnTheListOnce() {
        show()
        assertTrue(trainers.id in recorded())
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(card(trainers.label))
        compose.onAllNodes(card(trainers.label)).onFirst().performClick()
        compose.waitUntil(LONG_MILLIS) { exists(PARCEL_PAGE) }
        compose.waitForIdle()
        val stacked = stacked()

        compose.runOnUiThread { stores.parcels.value = after }
        if (stacked) {
            // Nothing while the page covers the list; back on it, the postmark and one burst of paper.
            settle()
            assertEquals(0, bursts)
            compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        }
        compose.waitUntil(LONG_MILLIS) { bursts == 1 }
        compose.waitUntil(LONG_MILLIS) { !holding() }
        assertTrue(trainers.id !in recorded())

        // Once: the app sent away and brought back plays nothing more.
        compose.activityRule.scenario.moveToState(Lifecycle.State.STARTED)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        settle()
        assertEquals(1, bursts)
    }

    @Test
    fun aParcelDeliveredWhileTheAppWasClosedArrivesAtTheNextStartOnly() {
        show()
        assertTrue(trainers.id in recorded())
        startAgain(after)
        compose.waitUntil(LONG_MILLIS) { bursts == 1 }
        compose.waitUntil(LONG_MILLIS) { !holding() }
        assertTrue(trainers.id !in recorded())

        startAgain(after)
        settle()
        assertEquals(1, bursts)
        assertTrue(!holding())
    }

    private companion object {
        const val SEED = 0x5EED_CAFEuL
        const val LONG_MILLIS = 10_000L
    }
}
