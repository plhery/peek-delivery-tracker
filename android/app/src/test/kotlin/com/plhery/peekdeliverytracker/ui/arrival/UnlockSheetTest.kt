package com.plhery.peekdeliverytracker.ui.arrival

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeviceLookupsSpent
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.talkBackStops
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ERROR_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL_SIGN_IN
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SIGN_IN
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The unlock sheet ([UnlockSheetHost]) over a guest's list, on the real session store, its auth service a fake that
 * answers as Supabase Auth does: what it says for each reason, the sign-in card, "Not now" and its ✕, the code checked
 * holding it up, a refused code keeping the guest, and a sign-in that leaves the screen under it where it was. Nothing
 * reaches a server.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class UnlockSheetTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val service = FakeAuthService()
    private val en = Localizer(AppLanguage.En, variant = "android")
    private lateinit var session: SessionStore
    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** A guest's list (the demo's parcels, followed on this phone), with email codes to sign in. */
    private fun show(prepare: FakeDeliveries.() -> Unit = {}) {
        compose.waitsForMaps()
        source = FakeDeliveries(DemoCards.parcels(AppLanguage.En), AuthenticationState.Welcome).apply {
            isGuest = true
            prepare()
        }
        compose.setContent {
            val scope = rememberCoroutineScope()
            session = remember { signInSession(scope, signInConfiguration(), service).also { it.showWelcome() } }
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) { RootHost(session = session, source = source) }
        }
        compose.waitForIdle()
    }

    private fun ask(reason: UnlockReason) {
        compose.runOnIdle { source.unlock.ask(reason) }
        compose.waitForIdle()
    }

    private fun text(key: String) = en.text(key)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    /** Drawn, though under the sheet nothing reaches TalkBack. */
    private fun drawn(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun inSheet(text: String) =
        compose.onAllNodes(hasText(text) and hasAnyAncestor(hasTestTag(UNLOCK_SHEET))).fetchSemanticsNodes().isNotEmpty()

    private fun signIn() {
        node(EMAIL_FIELD).performTextInput(READER_EMAIL)
        node(EMAIL_SEND).performClick()
        compose.waitForIdle()
        node(CODE_FIELD).performTextInput(READER_CODE)
        node(CODE_VERIFY).performClick()
        compose.waitForIdle()
    }

    @Test
    fun overTheListItSaysWhyWhatComesAlongAndHoldsTheWaysIn() {
        show()
        ask(UnlockReason.Several)
        assertTrue(inSheet(text("unlock.title")))
        node(UNLOCK_WHAT).assertTextEquals("${text("unlock.several")} ${text("unlock.comes")}")
        assertTrue(compose.onAllNodes(hasTestTag(EMAIL_FIELD) and hasAnyAncestor(hasTestTag(UNLOCK_CARD))).fetchSemanticsNodes().isNotEmpty())
        assertTrue(compose.onAllNodes(hasTestTag(PRIVACY_LINE) and hasAnyAncestor(hasTestTag(UNLOCK_CARD))).fetchSemanticsNodes().isNotEmpty())
        node(UNLOCK_LATER).assertTextEquals(text("unlock.later")).assertIsEnabled()
        assertEquals(text("common.close"), node(UNLOCK_CLOSE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.single())
        // The guest's list stays under it: no sign-in screen in its place.
        assertTrue(drawn(TAB_BAR))
        assertFalse(exists(SIGN_IN))
        assertEquals(AuthenticationState.Welcome, session.state)
    }

    /** TalkBack reads the sheet alone, from its grabber to Not now: nothing of the list under it. */
    @Test
    fun talkBackReadsTheSheetAlone() {
        show()
        ask(UnlockReason.Several)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertEquals(
            listOf(
                "Drag handle [button]",
                "${text("unlock.title")} [heading]",
                "${text("common.close")} [button]",
                "${text("unlock.several")} ${text("unlock.comes")}",
                "Peek will email you a sign-in code.",
                "Email address [edit box]",
                "Email me a code [button, unavailable]",
                "Your tracking details stay private. Privacy",
                "${text("unlock.later")} [button]",
            ),
            compose.talkBackStops(compose.activity.window.decorView, spoken = true),
        )
    }

    @Test
    fun eachReasonHasItsLineAndAnAccountJustWhatComesAlong() {
        show()
        ask(UnlockReason.Daily)
        node(UNLOCK_WHAT).assertTextEquals("${text("unlock.daily")} ${text("unlock.comes")}")
        ask(UnlockReason.Friends)
        node(UNLOCK_WHAT).assertTextEquals("${text("unlock.friends")} ${text("unlock.comes")}")
        ask(UnlockReason.Account)
        assertTrue(inSheet(text("arrival.signInTitle")))
        assertFalse(inSheet(text("unlock.title")))
        node(UNLOCK_WHAT).assertTextEquals(text("unlock.comes"))
    }

    @Test
    fun fromSettingsItAsksOverSettingsWhichStaysOnceSignedIn() {
        show()
        node(ACCOUNT_BUTTON).performClick()
        compose.waitForIdle()
        node(SETTINGS_SIGN_IN).performScrollTo().performClick()
        compose.waitForIdle()
        assertEquals(UnlockReason.Account, source.unlock.reason)
        assertTrue(source.views.contains("sign-in"))
        assertTrue(drawn(SETTINGS_SHEET))
        signIn()
        assertTrue(session.state is AuthenticationState.SignedIn)
        assertFalse(exists(UNLOCK_SHEET))
        assertTrue(exists(SETTINGS_SHEET))
    }

    @Test
    fun theNotificationsPagesEmailAsksOverThePage() {
        show()
        node(ACCOUNT_BUTTON).performClick()
        compose.waitForIdle()
        node(SETTINGS_DELIVERY_UPDATES).performScrollTo().performClick()
        compose.waitForIdle()
        node(SETTINGS_DELIVERY_EMAIL_SIGN_IN).performScrollTo().performClick()
        compose.waitForIdle()
        assertEquals(UnlockReason.Email, source.unlock.reason)
        assertTrue(inSheet(text("unlock.title")))
        node(UNLOCK_WHAT).assertTextEquals("${text("unlock.email")} ${text("unlock.comes")}")
        assertTrue(drawn(SETTINGS_DELIVERY_EMAIL_SIGN_IN))
    }

    @Test
    fun signedInOverAParcelsPageTheListShowsAlone() {
        show()
        val parcel = source.parcels.value.first().id
        compose.runOnIdle { deliveries.navigator.open(parcel) }
        compose.waitForIdle()
        assertEquals(parcel, deliveries.navigator.navigation.shown)
        ask(UnlockReason.Several)
        signIn()
        assertNull("This device's parcel may have gone with the account taking it along", deliveries.navigator.navigation.shown)
        assertTrue(exists(PARCEL_LIST))
    }

    @Test
    fun notNowAndTheCloseGoBackToTheGuestsList() {
        show()
        ask(UnlockReason.Several)
        node(UNLOCK_LATER).performClick()
        compose.waitForIdle()
        assertNull(source.unlock.reason)
        assertFalse(exists(UNLOCK_SHEET))
        ask(UnlockReason.Daily)
        node(UNLOCK_CLOSE).performClick()
        compose.waitForIdle()
        assertNull(source.unlock.reason)
        assertFalse(exists(UNLOCK_SHEET))
        assertEquals(AuthenticationState.Welcome, session.state)
        assertTrue(service.requests.isEmpty())
    }

    @Test
    fun aCodeTakenSignsInOverTheListAndTheSheetGoes() {
        show()
        ask(UnlockReason.Friends)
        signIn()
        assertEquals(READER_EMAIL, (session.state as AuthenticationState.SignedIn).user.email)
        assertEquals("""{"email":"reader@example.com","token":"123456","type":"email"}""", service.body("verify"))
        assertNull(source.unlock.reason)
        assertFalse(exists(UNLOCK_SHEET))
        assertTrue(exists(TAB_BAR))
        assertFalse(exists(SIGN_IN))
    }

    /** Closing it would leave the check to sign in behind the guest's back: it stays until the check ends. */
    @Test
    fun whileTheCodeIsCheckedItStays() {
        show()
        ask(UnlockReason.Several)
        node(EMAIL_FIELD).performTextInput(READER_EMAIL)
        node(EMAIL_SEND).performClick()
        compose.waitForIdle()
        val held = CompletableDeferred<Unit>()
        service.held = held
        node(CODE_FIELD).performTextInput(READER_CODE)
        node(CODE_VERIFY).performClick()
        compose.waitForIdle()
        node(UNLOCK_LATER).assertIsNotEnabled()
        node(UNLOCK_CLOSE).assertIsNotEnabled()
        compose.runOnIdle { source.unlock.close() }
        compose.waitForIdle()
        assertEquals(UnlockReason.Several, source.unlock.reason)
        assertTrue(exists(UNLOCK_SHEET))
        held.complete(Unit)
        compose.waitForIdle()
        assertTrue(session.state is AuthenticationState.SignedIn)
        assertFalse(exists(UNLOCK_SHEET))
    }

    @Test
    fun aRefusedCodeKeepsTheGuestAndTheSheet() {
        show()
        service.verify = FakeAuthService.Answer.Refused()
        ask(UnlockReason.Several)
        signIn()
        assertEquals(AuthenticationState.Welcome, session.state)
        assertTrue(exists(SIGN_IN_ERROR))
        assertEquals(UnlockReason.Several, source.unlock.reason)
        node(UNLOCK_LATER).assertIsEnabled()
    }

    /** "Resume the pending action": Add stays open under it with its number, and the day's limit goes once signed in. */
    @Test
    fun signedInFromTheDailyLimitAddKeepsItsNumberForAnotherTry() {
        show { failure = DeviceLookupsSpent() }
        node(ADD_BUTTON).performClick()
        compose.waitForIdle()
        node(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        node(ADD_SAVE).performClick()
        compose.waitForIdle()
        node(ADD_ERROR_SIGN_IN).performClick()
        compose.waitForIdle()
        assertEquals(UnlockReason.Daily, source.unlock.reason)
        signIn()
        assertTrue(session.state is AuthenticationState.SignedIn)
        assertFalse(exists(UNLOCK_SHEET))
        assertTrue(exists(ADD_PARCEL))
        assertFalse(exists(ADD_ERROR))
        assertEquals(
            "1Z999AA10123456784",
            node(ADD_TRACKING).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text,
        )
    }
}
