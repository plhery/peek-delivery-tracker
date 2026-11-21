package com.plhery.peekdeliverytracker.ui.settings

import android.content.ClipboardManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.state.ToggleableState
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures.ADDRESS
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures.chatGpt
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures.claude
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures.offer
import com.plhery.peekdeliverytracker.ui.settings.AssistantsFixtures.token
import java.io.IOException
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
 * Settings' "Assistants": its row only for an account the server lets assistants connect to (its 404 otherwise), the
 * address copied, each assistant's steps folded under its name, and the ones connected with OAuth, each with
 * Disconnect, one at a time with the back off meanwhile and why it failed.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class AssistantsSettingsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    private fun show(session: AuthenticationState = reader, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, dark = false, source = source)
            }
        }
        settle()
        compose.runOnUiThread { deliveries.account() }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String) = localizer.text(key)

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun words(tag: String) = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.joinToString()

    private fun tap(tag: String) {
        node(tag).performScrollTo().performClick()
        settle()
    }

    private fun enabled(tag: String): Boolean = SemanticsProperties.Disabled !in node(tag).fetchSemanticsNode().config

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun announced(): String? = node(ANNOUNCEMENT).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.ifEmpty { null }

    private fun disconnect(id: UUID) = "$ASSISTANTS_DISCONNECT.$id"

    private fun toggled(tag: String): ToggleableState? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState)

    @Test
    fun anAccountAssistantsMayConnectToHasTheRowWithItsBadge() {
        show { assistantsClient.offer = offer() }
        assertTrue(exists(SETTINGS_ASSISTANTS))
        assertTrue(shows(text("assistants.experimental").uppercase()))
        assertEquals(listOf("read"), source.assistantsClient.requests)
        // Read alongside the forwarding address, under Delivery updates.
        assertEquals(listOf("read"), source.addByEmailClient.requests)
        val updates = node(SETTINGS_DELIVERY_UPDATES).fetchSemanticsNode().boundsInRoot
        val row = node(SETTINGS_ASSISTANTS).fetchSemanticsNode().boundsInRoot
        assertTrue(row.top >= updates.bottom)
    }

    /** The server answers 404 until the feature is set up and open to the account. */
    @Test
    fun withoutAnOfferThereIsNoRow() {
        show()
        assertEquals(listOf("read"), source.assistantsClient.requests)
        assertFalse(exists(SETTINGS_ASSISTANTS))
        assertTrue(exists(SETTINGS_DELIVERY_UPDATES))
    }

    @Test
    fun aFailedReadShowsNoRow() {
        show { assistantsClient.failure = IOException("Offline") }
        assertFalse(exists(SETTINGS_ASSISTANTS))
    }

    @Test
    fun theDemoIsNeverOfferedOne() {
        show(AuthenticationState.Demo) { assistantsClient.offer = offer() }
        assertFalse(exists(SETTINGS_ASSISTANTS))
        assertEquals(emptyList<String>(), source.assistantsClient.requests)
    }

    @Test
    fun thePageShowsTheAddressAndOpensOneAssistantsStepsAtATime() {
        show { assistantsClient.offer = offer() }
        tap(SETTINGS_ASSISTANTS)
        assertTrue(shows(text("assistants.intro")))
        assertEquals(ADDRESS, words(ASSISTANTS_ADDRESS))
        assertFalse(exists("$ASSISTANTS_STEPS.Claude"))

        tap("$ASSISTANTS_SETUP.Claude")
        assertTrue(exists("$ASSISTANTS_STEPS.Claude"))
        assertTrue(shows("Sign in to Peek when it asks, then select Allow."))
        assertTrue(shows("Select Connect."))
        tap("$ASSISTANTS_SETUP.OpenClaw")
        assertFalse(exists("$ASSISTANTS_STEPS.Claude"))
        assertTrue(exists("$ASSISTANTS_STEPS.OpenClaw"))
        tap("$ASSISTANTS_SETUP.OpenClaw")
        assertFalse(exists("$ASSISTANTS_STEPS.OpenClaw"))
    }

    @Test
    fun theAssistantsConnectedWithOAuthShowEachWithDisconnect() {
        show { assistantsClient.offer = offer() }
        tap(SETTINGS_ASSISTANTS)
        assertTrue(exists(disconnect(claude.id)))
        assertTrue(exists(disconnect(chatGpt.id)))
        // A personal token has no screen.
        assertFalse(exists(disconnect(token.id)))
        assertFalse(shows(token.name))
        assertFalse(exists(ASSISTANTS_NONE))
        // TalkBack names the assistant Disconnect removes.
        val label = node(disconnect(claude.id)).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)
        assertEquals(listOf(localizer.text("assistants.revokeNamed", mapOf("name" to "Claude"))), label)
    }

    @Test
    fun withoutAnAssistantConnectedThePageSaysSo() {
        show { assistantsClient.offer = offer(connections = listOf(token)) }
        tap(SETTINGS_ASSISTANTS)
        assertTrue(exists(ASSISTANTS_NONE))
        assertEquals(text("assistants.none"), words(ASSISTANTS_NONE))
        // With no assistant, there is nothing to be notified of.
        assertFalse(exists(ASSISTANTS_NOTIFY))
    }

    @Test
    fun theParcelsTheyAddAreNotifiedUntilSwitchedOff() {
        show { assistantsClient.offer = offer() }
        tap(SETTINGS_ASSISTANTS)
        node(ASSISTANTS_NOTIFY).performScrollTo()
        settle()
        assertEquals(ToggleableState.On, toggled(ASSISTANTS_NOTIFY))
        assertEquals(listOf(text("assistants.notify")), node(ASSISTANTS_NOTIFY).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text)?.map { it.text })
        node(ASSISTANTS_NOTIFY).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(ToggleableState.Off, toggled(ASSISTANTS_NOTIFY))
        assertEquals(listOf("read", "notify false"), source.assistantsClient.requests)

        // A save that fails leaves the switch as the server last answered, and says why.
        source.assistantsClient.notifyFailure = IOException("Offline")
        node(ASSISTANTS_NOTIFY).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertEquals(ToggleableState.Off, toggled(ASSISTANTS_NOTIFY))
        assertEquals(localizer.errorMessage(IOException("Offline"), fallback = "assistants.failed"), words(ASSISTANTS_ERROR))
    }

    @Test
    fun copyingPutsTheAddressOnTheClipboardAndSaysSo() {
        show { assistantsClient.offer = offer() }
        tap(SETTINGS_ASSISTANTS)
        tap(ASSISTANTS_COPY)
        val clip = compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()
        assertEquals(ADDRESS, clip)
        assertTrue(shows(text("inbox.copied")))
        assertEquals(text("inbox.copied"), announced())
    }

    @Test
    fun disconnectTakesTheAssistantAway() {
        show { assistantsClient.offer = offer() }
        tap(SETTINGS_ASSISTANTS)
        tap(disconnect(claude.id))
        assertFalse(exists(disconnect(claude.id)))
        assertTrue(exists(disconnect(chatGpt.id)))
        assertFalse(exists(ASSISTANTS_ERROR))
        assertEquals(listOf("read", "revoke ${claude.id}"), source.assistantsClient.requests)
        // The last one gone, the page says none is connected.
        tap(disconnect(chatGpt.id))
        assertTrue(exists(ASSISTANTS_NONE))
    }

    @Test
    fun whileADisconnectWaitsTheBackIsOffThenWhyItFailedShows() {
        show {
            assistantsClient.offer = offer()
            assistantsClient.revoking = CompletableDeferred()
        }
        tap(SETTINGS_ASSISTANTS)
        tap(disconnect(claude.id))
        assertTrue(exists(ASSISTANTS_WORKING))
        assertFalse(enabled(disconnect(chatGpt.id)))
        back()
        assertTrue(exists(ASSISTANTS_ADDRESS))
        assertTrue(deliveries.showingAccount)

        source.assistantsClient.revokeFailure = IOException("Offline")
        compose.runOnUiThread { source.assistantsClient.revoking!!.complete(Unit) }
        settle()
        assertFalse(exists(ASSISTANTS_WORKING))
        assertEquals(localizer.errorMessage(IOException("Offline"), fallback = "assistants.failed"), words(ASSISTANTS_ERROR))
        assertTrue(exists(disconnect(claude.id)))
        assertTrue(enabled(disconnect(chatGpt.id)))
        back()
        assertFalse(exists(ASSISTANTS_ADDRESS))
        assertTrue(exists(SETTINGS_ASSISTANTS))
    }
}
