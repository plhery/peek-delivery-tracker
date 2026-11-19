package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AccountInvitationMemory
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The guest's invitation to an account under their parcels (`DeviceAccountRow`): no × on its first five visits, one from
 * the sixth, which hides it for 30 days without signing in; back for one visit after that. A visit is the app in use,
 * counted once in 30 minutes, which the tests mark by stopping the window and starting it again.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class AccountInvitationTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    /** The demo's parcels for a guest, or `parcels`, their invitation's record as `record` leaves it, at `fontScale`. */
    private fun show(
        fontScale: Float = 1f,
        parcels: List<Parcel> = DemoCards.parcels(),
        record: (AccountInvitationMemory, Instant) -> Unit = { _, _ -> },
    ) {
        source = FakeDeliveries(parcels, AuthenticationState.Welcome)
        record(AccountInvitationMemory(source.accountInvitationDefaults) { source.now }, source.now)
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it },
            ) {
                RootHost(state = AuthenticationState.Welcome, source = source)
            }
        }
        compose.waitForIdle()
    }

    /** The row seen on `count` visits, two hours apart, the last two hours before `now`. */
    private fun visits(count: Int, now: Instant) {
        repeat(count) { index ->
            val at = now - Duration.ofHours(2L * (count - index))
            AccountInvitationMemory(source.accountInvitationDefaults) { at }.seen()
        }
    }

    /** The app away for `away`, then in use again. */
    private fun away(away: Duration) {
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        source.now += away
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitForIdle()
    }

    private fun offered(): Boolean = OFFER in deliveries.itemKeys

    private fun scrollToTheOffer() {
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(OFFER)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag).getBoundsInRoot()

    private fun inOffer(key: String): DpRect = compose.onNode(
        hasText(localizer.text(key)) and hasAnyAncestor(hasTestTag(DEVICE_ACCOUNT_ROW)),
        useUnmergedTree = true,
    ).getBoundsInRoot()

    @Test
    fun theRowHasNoCrossOnItsFirstFiveVisitsAndOneFromTheSixth() {
        show { _, now -> visits(4, now) }
        scrollToTheOffer()
        assertTrue(exists(DEVICE_ACCOUNT_ROW))
        assertFalse("The fifth visit", exists(DEVICE_ACCOUNT_HIDE))

        // Back within 30 minutes of the visit's start: the same visit.
        away(Duration.ofMinutes(10))
        assertFalse(exists(DEVICE_ACCOUNT_HIDE))

        // The row still on screen counts the next visit as it begins.
        away(Duration.ofMinutes(20))
        assertTrue("The sixth visit", exists(DEVICE_ACCOUNT_HIDE))
    }

    @Test
    fun theCrossIsItsOwnButtonBesideTheSignInAndHidesTheRowWithoutSigningIn() {
        show { _, now -> visits(5, now) }
        scrollToTheOffer()
        val hide = compose.onNodeWithTag(DEVICE_ACCOUNT_HIDE)
        hide.assertContentDescriptionEquals(localizer.text("link.account.hide"))
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
        // Not read as part of the row, which still asks to sign in.
        compose.onNodeWithTag(DEVICE_ACCOUNT_ROW).assert(!hasContentDescription(localizer.text("link.account.hide")))

        val row = bounds(DEVICE_ACCOUNT_ROW)
        val cross = bounds(DEVICE_ACCOUNT_HIDE)
        assertEquals(48f, cross.right.value - cross.left.value, 0.5f)
        assertEquals(48f, cross.bottom.value - cross.top.value, 0.5f)
        assertEquals("At the row's end", row.right.value, cross.right.value, 0.5f)
        assertEquals("Level with the row", (row.top.value + row.bottom.value) / 2, (cross.top.value + cross.bottom.value) / 2, 0.5f)
        assertTrue("Beside the button", inOffer("arrival.signInTitle").right < cross.left)

        compose.onNodeWithTag(DEVICE_ACCOUNT_ROW).performClick()
        compose.waitForIdle()
        assertEquals(1, source.signIns)

        hide.performClick()
        compose.waitForIdle()
        assertEquals("Hiding it never signs in", 1, source.signIns)
        assertFalse(offered())
        assertFalse(exists(DEVICE_ACCOUNT_ROW))
        assertFalse("Remembered on the phone", AccountInvitationMemory(source.accountInvitationDefaults) { source.now }.shows)

        away(Duration.ofDays(29))
        assertFalse("Away for 30 days", offered())
    }

    /** Every parcel arrived: the row under them speaks to the guest, not "All arrived". */
    @Test
    fun everyParcelArrivedTheRowSpeaksToTheGuest() {
        show(parcels = DemoCards.parcels().filter { it.isDelivered })
        assertTrue(offered())
        assertFalse(exists(ALL_ARRIVED))
    }

    /** Once they closed the row, "All arrived" tells them. */
    @Test
    fun aGuestWhoClosedTheRowIsToldEverythingArrived() {
        show(parcels = DemoCards.parcels().filter { it.isDelivered }) { memory, now ->
            visits(6, now)
            AccountInvitationMemory(source.accountInvitationDefaults) { now }.close()
            check(!memory.shows)
        }
        assertFalse(offered())
        assertTrue(exists(ALL_ARRIVED))
    }

    @Test
    fun closedTheRowComesBackAfter30DaysForOneVisit() {
        show { memory, now ->
            visits(6, now)
            AccountInvitationMemory(source.accountInvitationDefaults) { now - Duration.ofDays(29) }.close()
            check(!memory.shows)
        }
        assertFalse(offered())

        away(Duration.ofDays(1))
        assertTrue("Back after 30 days", offered())
        scrollToTheOffer()
        assertTrue("With its ×", exists(DEVICE_ACCOUNT_HIDE))

        // It stands for that visit, whatever the app does within it, and is away once it ends.
        away(Duration.ofMinutes(29))
        assertTrue(offered())
        away(Duration.ofMinutes(1))
        assertFalse(offered())
        away(Duration.ofDays(29))
        assertFalse(offered())
    }

    /** At the accessibility sizes the button goes under the words, and the × stands in the row's top corner. */
    @Test
    fun atTheLargestTextTheCrossStandsInTheRowsTopCorner() {
        show(fontScale = 2f) { _, now -> visits(5, now) }
        scrollToTheOffer()
        val row = bounds(DEVICE_ACCOUNT_ROW)
        val cross = bounds(DEVICE_ACCOUNT_HIDE)
        assertEquals(row.top.value, cross.top.value, 0.5f)
        assertEquals(row.right.value, cross.right.value, 0.5f)
        assertTrue("Clear of the words", inOffer("link.account.title").right <= cross.left)
        assertTrue("The button under them", inOffer("arrival.signInTitle").top > inOffer("native.guest.kept").bottom)
    }

    private companion object {
        const val OFFER = "deviceAccount"
    }
}
