package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_WELCOME
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** A window of the app as far as an add link goes: its own inbox, the links it counted, and the deliveries' screens. */
class AddLinkWindow : ComponentActivity() {
    val views = mutableListOf<String>()
    var opened = 0
    lateinit var inbox: DeliveriesInbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inbox = DeliveriesInbox.of(this, opened = { opened++ })
        setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = state.value, source = source, onView = { views += it }, inbox = inbox)
            }
        }
    }

    companion object {
        val state = mutableStateOf<AuthenticationState>(AuthenticationState.Demo)
        lateinit var source: FakeDeliveries
        internal var deliveries: DeliveriesState? = null
    }
}

/**
 * `peekdeliverytracker://add?tracking=<text>` (A-L06, A-L14), `handleURL`'s `.add`: Add opens on the deliveries with the
 * link's text, whether the link starts the window or comes to it from another tab, which brings the deliveries forward;
 * Add already up takes the text in place (`fill(_:)`). It waits through a turn and after Android ended the process
 * while the session is read, and isn't opened again by the window made again or by Recents. Where the window shows no
 * list (nobody signed in, an invitation over everything) it goes, as the iPhone's list isn't there to hear it. Each is
 * counted as a link opened, and not as a share. On synthetic numbers.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class AddLinkTest {
    @get:Rule
    val compose = createEmptyComposeRule()

    private val deliveries: DeliveriesState get() = checkNotNull(AddLinkWindow.deliveries)

    @Before
    fun start() {
        AddLinkWindow.state.value = AuthenticationState.Demo
        AddLinkWindow.source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        AddLinkWindow.deliveries = null
    }

    private fun addLink(tracking: String? = "TEST%200001") =
        Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://add" + (tracking?.let { "?tracking=$it" } ?: "")))

    private fun window(intent: Intent? = null): ActivityController<AddLinkWindow> =
        (if (intent == null) Robolectric.buildActivity(AddLinkWindow::class.java) else Robolectric.buildActivity(AddLinkWindow::class.java, intent))
            .setup()
            .also { settle() }

    /**
     * Android ending the process while the window is in the background, and starting it again as it comes back: its
     * first intent, the link included, and what it saved.
     */
    private fun ActivityController<AddLinkWindow>.processEnded(intent: Intent): ActivityController<AddLinkWindow> {
        val saved = Bundle()
        pause().stop().saveInstanceState(saved)
        destroy()
        return Robolectric.buildActivity(AddLinkWindow::class.java, intent).setup(saved).also { settle() }
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun session(state: AuthenticationState) {
        compose.runOnIdle { AddLinkWindow.state.value = state }
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun fieldText(tag: String): String? =
        compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun choose(tab: String) {
        compose.onNodeWithContentDescription(tab).performClick()
        settle()
    }

    @Test
    fun aLinkThatStartsTheWindowOpensAddWithItsTextCountedAsALinkAlone() {
        val window = window(addLink())
        assertEquals("TEST 0001", fieldText(ADD_TRACKING))
        assertEquals("", fieldText(ADD_NAME))
        assertEquals("add-parcel", window.get().views.last())
        assertNull(window.get().inbox.add.value)
        assertNull("The link forgotten", window.get().intent.data)
        assertEquals(1, window.get().opened)
        assertFalse(AddLinkWindow.source.actions.contains("parcel-share-received"))
    }

    @Test
    fun aLinkWithoutATrackingNumberOpensAddEmpty() {
        window(addLink(tracking = null))
        assertTrue(exists(ADD_PARCEL))
        assertEquals("", fieldText(ADD_TRACKING))
    }

    @Test
    fun aLinkThatComesFromAnotherTabBringsTheDeliveriesForwardWithAdd() {
        val window = window()
        choose("Passport")
        assertTrue(exists(PeekTab.Passport.screen))

        window.newIntent(addLink())
        settle()
        assertFalse(exists(PeekTab.Passport.screen))
        assertEquals("TEST 0001", fieldText(ADD_TRACKING))
        assertEquals(1, window.get().opened)
    }

    /** `.onChange(of: draft) { fill(draft) }`: the link's text in place of what Add holds, even none; its name stays. */
    @Test
    fun aLinkWhileAddIsUpFillsItInPlaceAndLeavesItsName() {
        val window = window()
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        val opening = deliveries.addition?.id
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("TESTTYPED0001")
        compose.onNodeWithTag(ADD_NAME).performTextReplacement("Lamp")
        settle()

        window.newIntent(addLink())
        settle()
        assertEquals("The same Add", opening, deliveries.addition?.id)
        assertEquals("TEST 0001", fieldText(ADD_TRACKING))
        assertEquals("Lamp", fieldText(ADD_NAME))

        window.newIntent(addLink(tracking = ""))
        settle()
        assertEquals(opening, deliveries.addition?.id)
        assertEquals("", fieldText(ADD_TRACKING))
        assertEquals("Lamp", fieldText(ADD_NAME))
    }

    @Test
    fun aLinkWaitingWhileTheSessionIsReadIsKeptThroughATurnAndAfterTheProcessEnded() {
        AddLinkWindow.state.value = AuthenticationState.Loading
        val intent = addLink()
        val window = window(intent)
        assertEquals("TEST 0001", window.get().inbox.add.value?.trackingInput)
        assertFalse(exists(ADD_PARCEL))

        window.recreate()
        settle()
        assertEquals("Through a turn", "TEST 0001", window.get().inbox.add.value?.trackingInput)
        assertEquals(0, window.get().opened)

        val restored = window.processEnded(intent)
        assertEquals("After the process ended", "TEST 0001", restored.get().inbox.add.value?.trackingInput)
        assertEquals(0, restored.get().opened)

        session(AuthenticationState.Demo)
        assertEquals("TEST 0001", fieldText(ADD_TRACKING))
        assertNull(restored.get().inbox.add.value)
    }

    @Test
    fun theWindowMadeAgainDoesNotOpenAddAgain() {
        val window = window(addLink())
        compose.onNodeWithTag(ADD_CLOSE).performClick()
        settle()
        assertFalse(exists(ADD_PARCEL))

        window.recreate()
        settle()
        assertFalse(exists(ADD_PARCEL))
        assertEquals(0, window.get().opened)
    }

    /** Recents starting the window again after the phone restarted, with the link that began its task. */
    @Test
    fun recentsAfterARestartDoesNotOpenAddAgain() {
        val window = window(addLink().addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY))
        assertFalse(exists(ADD_PARCEL))
        assertNull(window.get().inbox.add.value)
        assertEquals(0, window.get().opened)
    }

    /** The arrival has no list to hear the link: it goes, counted, and doesn't open once someone is in. */
    @Test
    fun whileNobodyIsSignedInALinkOpensNothingAndGoes() {
        AddLinkWindow.state.value = AuthenticationState.Loading
        val window = window(addLink())
        session(AuthenticationState.SignedOut)
        assertNull(window.get().inbox.add.value)
        assertEquals(1, window.get().opened)

        session(AuthenticationState.Demo)
        assertFalse(exists(ADD_PARCEL))
    }

    @Test
    fun overAnInvitationALinkOpensNothingAndGoes() {
        val window = window()
        compose.runOnIdle { AddLinkWindow.source.invitation.open("$INVITATION_BASE/i/Qa3vT8nL5wZ1pR7k") }
        settle()
        assertTrue(exists(INVITATION_WELCOME))

        window.newIntent(addLink())
        settle()
        assertNull(window.get().inbox.add.value)
        assertFalse(exists(ADD_PARCEL))
    }

    /** The first open stands over the tabs, the deliveries under it: Add opens over it, as the iPhone's sheet does. */
    @Test
    fun forAGuestWithNothingFollowedALinkOpensAddOverTheFirstOpen() {
        AddLinkWindow.state.value = AuthenticationState.Welcome
        AddLinkWindow.source = FakeDeliveries(emptyList(), AuthenticationState.Welcome)
        window(addLink())
        assertEquals("TEST 0001", fieldText(ADD_TRACKING))
    }
}
