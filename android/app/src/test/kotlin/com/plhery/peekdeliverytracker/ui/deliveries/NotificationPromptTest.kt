package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.Localizer
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
 * The invitation to turn notifications on (C-S34, A-W07, C-ST19, C-ST20, C-SE09, C-AX07): under the list while the
 * store invites and nothing stands over the list or is under way on it, after the toasts, among the cards at the
 * accessibility sizes; the title a heading; Turn on once at a time with its spinner and both buttons still, its failure
 * said in the card; Not now counted and the invitation sent away.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class NotificationPromptTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 8), email = "reader@example.com"))

    /** The window's lifecycle as the tab sees it, which a test sends to the background and back. */
    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    /** The demo's parcels in an account the store invites, unless `invited` is false. */
    private fun show(invited: Boolean = true, fontScale: Float = 1f) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply { shouldInviteNotifications.value = invited }
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale),
                LocalDeliveriesState provides { deliveries = it },
                LocalLifecycleOwner provides owner,
            ) {
                RootHost(state = reader, source = source)
            }
        }
        settle()
    }

    private fun settle(millis: Long = 1_000) {
        compose.mainClock.advanceTimeBy(millis)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String): Boolean = compose.onAllNodes(hasText(text), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag).getUnclippedBoundsInRoot()

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    private fun spins(): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and hasAnyAncestor(hasTestTag(NOTIFICATIONS_PROMPT_ENABLE)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun change(what: DeliveriesState.() -> Unit) {
        compose.runOnUiThread { deliveries.what() }
        settle()
    }

    private fun lifecycle(state: Lifecycle.State) {
        compose.runOnUiThread { owner.registry.currentState = state }
        settle()
    }

    @Test
    fun itShowsUnderTheListWhileTheStoreInvitesWithItsTitleAHeading() {
        show()
        assertTrue(exists(NOTIFICATIONS_PROMPT))
        assertTrue(shows(localizer.text("notifications.prompt.title")))
        assertTrue(shows(localizer.text("notifications.prompt.description")))
        val title = compose.onNode(hasText(localizer.text("notifications.prompt.title")), useUnmergedTree = true).fetchSemanticsNode()
        assertTrue(SemanticsProperties.Heading in title.config)
        assertFalse(exists(NOTIFICATIONS_PROMPT_ERROR))
        // Under the list, 16 in from each side of the phone.
        val root = compose.onRoot().getUnclippedBoundsInRoot()
        val card = bounds(NOTIFICATIONS_PROMPT)
        assertTrue("$card in $root", card.bottom > root.bottom * 2 / 3)
        assertEquals(16f, card.left.value, 0.5f)
        assertEquals(root.right.value - 16f, card.right.value, 0.5f)
    }

    @Test
    fun notInvitedThereIsNone() {
        show(invited = false)
        assertFalse(exists(NOTIFICATIONS_PROMPT))
        compose.runOnUiThread { source.shouldInviteNotifications.value = true }
        settle()
        assertTrue(exists(NOTIFICATIONS_PROMPT))
    }

    @Test
    fun itStepsAsideForWhatStandsOverTheListOrIsUnderWayOnIt() {
        show()
        change { showingSearch = true }
        assertFalse("Searching", exists(NOTIFICATIONS_PROMPT))
        change { showingSearch = false }
        assertTrue(exists(NOTIFICATIONS_PROMPT))

        change { actionError = "Something went wrong" }
        assertFalse("An alert", exists(NOTIFICATIONS_PROMPT))
        change { actionError = null }
        assertTrue(exists(NOTIFICATIONS_PROMPT))

        compose.runOnUiThread { source.refreshing.value = true }
        settle(100)
        assertFalse("Refreshing", exists(NOTIFICATIONS_PROMPT))
        compose.runOnUiThread { source.refreshing.value = false }
        settle()
        assertTrue(exists(NOTIFICATIONS_PROMPT))

        lifecycle(Lifecycle.State.CREATED)
        assertFalse("In the background", exists(NOTIFICATIONS_PROMPT))
        lifecycle(Lifecycle.State.RESUMED)
        assertTrue(exists(NOTIFICATIONS_PROMPT))
    }

    /** `bottomControls`: the undo toast, else the latest message, else the invitation. */
    @Test
    fun aToastTakesItsPlaceForItsFourSeconds() {
        show()
        change { this.show("Saved") }
        assertTrue(exists(INLINE_TOAST))
        assertFalse(exists(NOTIFICATIONS_PROMPT))
        settle(4_000)
        assertFalse(exists(INLINE_TOAST))
        assertTrue(exists(NOTIFICATIONS_PROMPT))

        compose.runOnUiThread { source.undoParcel.value = DemoCards.parcels().last() }
        settle()
        assertTrue(exists(INLINE_TOAST))
        assertFalse(exists(NOTIFICATIONS_PROMPT))
    }

    @Test
    fun notNowCountsItAndSendsTheInvitationAway() {
        show()
        tap(NOTIFICATIONS_PROMPT_DISMISS)
        assertEquals(listOf("notifications-defer"), source.actions.filter { it.startsWith("notifications") })
        assertEquals(listOf("dismissNotificationInvitation"), source.accountCalls.filter { it.contains("otification") })
        assertFalse(exists(NOTIFICATIONS_PROMPT))
    }

    @Test
    fun turnOnAsksOnceAtATimeWithItsSpinnerAndBothButtonsStill() {
        show()
        source.accountWork = CompletableDeferred()
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_ENABLE).assertIsEnabled()
        tap(NOTIFICATIONS_PROMPT_ENABLE)
        assertTrue(shows(localizer.text("notifications.enabling")))
        assertTrue(spins())
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_ENABLE).assertIsNotEnabled()
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_DISMISS).assertIsNotEnabled()
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_ENABLE).performClick()
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_DISMISS).performClick()
        settle()
        assertEquals(listOf("enableNotifications"), source.accountCalls.filter { it.contains("otification") })
        assertFalse(source.actions.contains("notifications-defer"))

        // The phone's question over the window while it works: the card stays.
        lifecycle(Lifecycle.State.STARTED)
        assertTrue(exists(NOTIFICATIONS_PROMPT))
        lifecycle(Lifecycle.State.RESUMED)

        source.accountWork?.complete(Unit)
        settle()
        assertTrue(source.notificationsEnabledOnDevice.value)
        assertFalse(exists(NOTIFICATIONS_PROMPT))
    }

    @Test
    fun turnOnFailedSaysSoInTheCardUntilItIsTriedAgain() {
        show()
        source.enableFailure = DeliveryApiError.PushTokenUnavailable()
        tap(NOTIFICATIONS_PROMPT_ENABLE)
        assertTrue(exists(NOTIFICATIONS_PROMPT_ERROR))
        assertTrue(shows(localizer.text("notifications.error.enable")))
        assertTrue(shows(localizer.text("notifications.enable")))

        source.enableFailure = null
        source.accountWork = CompletableDeferred()
        tap(NOTIFICATIONS_PROMPT_ENABLE)
        assertFalse("Cleared as it is tried again", exists(NOTIFICATIONS_PROMPT_ERROR))
        source.accountWork?.complete(Unit)
        settle()
        assertFalse(exists(NOTIFICATIONS_PROMPT))
    }

    /** At the accessibility sizes the card comes first among the cards, without the bell, its buttons stacked. */
    @Test
    fun atTheLargestTextItSitsAmongTheCards() {
        show(fontScale = 2f)
        assertTrue(exists(NOTIFICATIONS_PROMPT))
        val root = compose.onRoot().getUnclippedBoundsInRoot()
        val card = bounds(NOTIFICATIONS_PROMPT)
        assertTrue("$card in $root", card.top < root.bottom / 2)
        val enable = bounds(NOTIFICATIONS_PROMPT_ENABLE)
        val dismiss = bounds(NOTIFICATIONS_PROMPT_DISMISS)
        assertTrue("Stacked: $enable over $dismiss", dismiss.top >= enable.bottom)
        assertEquals("Turn on across the card", card.right - card.left - 32.dp, enable.right - enable.left)
    }
}
