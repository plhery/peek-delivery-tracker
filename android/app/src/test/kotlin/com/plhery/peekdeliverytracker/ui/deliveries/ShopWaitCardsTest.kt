package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DEMO_SHOP_WAIT_ID
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ShopWait
import com.plhery.peekdeliverytracker.core.demoShopWait
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's waits for shops that haven't shipped yet (the web's `ShopWaitCards`): a card each after the cards on
 * their way, read as the list opens, comes back to the foreground, checks every parcel and every 10 minutes; Stop
 * waiting takes one away at once, and one that goes by itself reads the parcels again.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShopWaitCardsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val client get() = source.shopWaitsClient
    private val coat = ShopWaitFixtures.coat
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11)))

    /** Two parcels on their way and one delivered, from the demo's. */
    private fun parcels(): List<Parcel> {
        val all = DemoCards.parcels().filter { it.archivedAt == null }
        return all.filter { !it.isDelivered }.take(2) + all.first { it.isDelivered }
    }

    private fun show(
        parcels: List<Parcel> = parcels(),
        session: AuthenticationState = reader,
        waits: List<ShopWait> = listOf(coat),
        demo: Boolean = false,
    ) {
        source = FakeDeliveries(parcels, session)
        client.waits = waits
        if (demo) source.demoShopWait = { demoShopWait(CAPTURED) }
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, source = source)
            }
        }
        compose.waitForIdle()
    }

    private fun key(wait: ShopWait) = "shopWait-${wait.id}"

    private fun keys(): List<String> = deliveries.itemKeys.map { it.toString() }

    private fun reads() = client.requests.count { it == "list" }

    private fun scrollTo(wait: ShopWait) {
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(key(wait))
        compose.waitForIdle()
    }

    private fun words(): List<String> =
        compose.onNodeWithTag(SHOP_WAIT_CARD).fetchSemanticsNode().config[SemanticsProperties.Text].map { it.text }

    @Test
    fun theWaitStandsAfterTheCardsOnTheirWay() {
        show()
        val keys = keys()
        val wait = keys.indexOf(key(coat))
        assertTrue(keys.toString(), wait > 0)
        val onTheWay = keys.withIndex().filter { (_, key) -> listOf("next/", "attention/", "remaining/").any(key::startsWith) }.map { it.index }
        assertEquals(keys.toString(), 2, onTheWay.size)
        assertTrue(keys.toString(), onTheWay.all { it < wait })
        assertTrue(keys.toString(), keys.indexOf("section-Delivered") > wait)
        assertEquals(1, reads())
    }

    @Test
    fun theCardReadsAsOneWithItsButtonOnItsOwn() {
        show()
        scrollTo(coat)
        assertEquals(
            listOf("Example Shop via parcelLab", "Wool coat", "Waiting for Example Shop to ship", "Peek checks every few hours until Wed 11 Nov"),
            words(),
        )
        compose.onNodeWithTag(SHOP_WAIT_CARD).assert(SemanticsMatcher.keyNotDefined(SemanticsProperties.Role))
        compose.onNodeWithTag(SHOP_WAIT_BUTTON)
            .assertHasClickAction()
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
        assertEquals(listOf("Stop waiting"), compose.onNodeWithTag(SHOP_WAIT_BUTTON).fetchSemanticsNode().config[SemanticsProperties.Text].map { it.text })
    }

    @Test
    fun anExpiredWaitSaysSoAndOffersRemove() {
        show(waits = listOf(ShopWaitFixtures.expired))
        scrollTo(ShopWaitFixtures.expired)
        assertEquals(
            listOf("Example Shop via parcelLab", "Boots", "Example Shop named no parcel in 14 days", "Peek stopped checking. Add the tracking number when the shop sends it."),
            words(),
        )
        assertEquals(listOf("Remove"), compose.onNodeWithTag(SHOP_WAIT_BUTTON).fetchSemanticsNode().config[SemanticsProperties.Text].map { it.text })
    }

    @Test
    fun theEmptyListGivesWayToAWait() {
        show(parcels = emptyList())
        assertFalse(keys().toString(), "empty" in keys())
        assertTrue(keys().toString(), key(coat) in keys())
        assertTrue(compose.onAllNodesWithTag(EMPTY_SAMPLE).fetchSemanticsNodes().isEmpty())

        // Once the reader stops waiting, the empty list is back.
        compose.onNodeWithTag(SHOP_WAIT_BUTTON).performClick()
        compose.waitForIdle()
        assertTrue(keys().toString(), "empty" in keys())
        assertFalse(keys().toString(), key(coat) in keys())
    }

    @Test
    fun everythingArrivedWaitsForTheShop() {
        val delivered = DemoCards.parcels().filter { it.archivedAt == null && it.isDelivered }
        show(parcels = delivered)
        assertFalse(keys().toString(), "allArrived" in keys())
        assertTrue(keys().toString(), key(coat) in keys())
    }

    @Test
    fun theDemoShowsItsCoatAndAGuestNone() {
        show(parcels = DemoCards.parcels(), session = AuthenticationState.Demo, waits = emptyList(), demo = true)
        val demo = demoShopWait(CAPTURED)
        assertEquals(DEMO_SHOP_WAIT_ID, demo.id)
        scrollTo(demo)
        assertEquals(listOf("H&M via parcelLab", "Wool coat", "Waiting for H&M to ship", "Peek checks every few hours until Wed 11 Nov"), words())

        // Stopping in the demo asks no server.
        compose.onNodeWithTag(SHOP_WAIT_BUTTON).performClick()
        compose.waitForIdle()
        assertFalse(key(demo) in keys())
        assertEquals(emptyList<String>(), client.requests)
    }

    @Test
    fun aGuestHasNoWaits() {
        show(parcels = parcels(), session = AuthenticationState.Welcome, demo = true)
        assertFalse(keys().any { it.startsWith("shopWait-") })
        assertEquals(emptyList<String>(), client.requests)
    }

    @Test
    fun stopWaitingTakesTheCardAwayAtOnce() {
        show()
        scrollTo(coat)
        val reloads = source.reloads
        client.removing = CompletableDeferred()
        compose.onNodeWithTag(SHOP_WAIT_BUTTON).performClick()
        compose.waitForIdle()
        assertFalse(key(coat) in keys())
        assertEquals(listOf("list", "remove ${coat.id}"), client.requests)

        compose.runOnUiThread { client.removing?.complete(Unit) }
        compose.waitForIdle()
        assertFalse(key(coat) in keys())
        // The reader removed it: no news of parcels.
        assertEquals(reloads, source.reloads)
    }

    @Test
    fun aFailedRemovalBringsTheWaitBackAndReadsAgain() {
        show()
        scrollTo(coat)
        client.removing = CompletableDeferred()
        client.removeFailure = DeliveryApiError.ServiceFailed(502)
        compose.onNodeWithTag(SHOP_WAIT_BUTTON).performClick()
        compose.waitForIdle()
        assertFalse(key(coat) in keys())

        compose.runOnUiThread { client.removing?.complete(Unit) }
        compose.waitForIdle()
        assertTrue(key(coat) in keys())
        assertEquals(listOf("list", "remove ${coat.id}", "list"), client.requests)
    }

    @Test
    fun theWaitsAreReadAgainWhileTheListShows() {
        show()
        assertEquals(1, reads())

        // Every 10 minutes.
        compose.mainClock.advanceTimeBy(10 * 60_000L)
        compose.waitForIdle()
        assertEquals(2, reads())

        // As a check of every parcel starts.
        compose.runOnUiThread { source.refreshing.value = true }
        compose.waitForIdle()
        compose.runOnUiThread { source.refreshing.value = false }
        compose.waitForIdle()
        assertEquals(3, reads())

        // Back in the foreground.
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitForIdle()
        assertEquals(4, reads())
    }

    @Test
    fun aWaitThatGoesByItselfReadsTheParcelsAgain() {
        show()
        val reloads = source.reloads
        assertTrue(key(coat) in keys())

        // The shop shipped: the server added the parcels and forgot the wait.
        client.waits = emptyList()
        compose.mainClock.advanceTimeBy(10 * 60_000L)
        compose.waitForIdle()
        assertFalse(key(coat) in keys())
        assertEquals(reloads + 1, source.reloads)
    }
}
