package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.SystemClock
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.text.InputType
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponseInvitationState
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ACCEPT
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ACCEPTANCE
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_ERROR
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_HEADING
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_LOADING
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_OUTCOME
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_OUTCOME_CLOSE
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_RETRY
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_SHEET
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_STAMP
import com.plhery.peekdeliverytracker.ui.arrival.INVITATION_WELCOME
import com.plhery.peekdeliverytracker.ui.arrival.WELCOME_OPEN_PARCEL
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NICKNAME
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SAVE
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_GRABBER
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * A friend's invitation received (C-S02, C-S12, C-S13, C-S18) on this device's own window, its bars and density the
 * device's, on fake deliveries and made-up friends and codes, which reach no server: a link to the window standing the
 * welcome over the tabs, the parcel opened into the acceptance, accepted with its receipt's stamp, then the friends
 * with the new friend; signed out, the parcel opening into the sign-in and the system's back shutting it, then closing
 * the invitation; without a profile, the sheet joining and accepting. Each saved as invite/<test>.png in the test
 * package's external files, which adb pulls. Under Reduce Motion, as the other device tests are. The invitation waits
 * in the fakes' memory; each test still puts back the appearance it found and whatever the app's own preferences kept
 * of a pending invitation.
 *
 * The rest of the invitation's states and gestures, each saved as invite/<row>-<state>.png: the welcome loading, then
 * with the sender's name and its parcel (C-ST08); a link already used, its name asked again for the account signed in
 * and unavailable without retry signed out; another failure with its retry; a link with no code (C-ST08); what the
 * acceptance says where the invitation can't be accepted, with Close, and where accepting fails (C-ST10); the parcel
 * off while it opens, tapped through the screen (C-G01); the profile's sheet thrown down by its grabber, its keyboard
 * taken down by a finger dragging the sheet, the nickname's Done, and the sheet held while it joins, against a throw
 * and the system's back (C-G07, C-G06, C-G11); and the lockup on one line at the largest text (C-AX12).
 *
 * What the arrival asks the phone's vibrator for (C-H01, C-H02, C-H03), read from the vibrator service's history as
 * HapticsDeviceTest reads it ([Vibrations]), each step saved as hapticswalk/<row>-<step>.txt: the parcel opening, the
 * sign-in taking the welcome's place, the system's back shutting it, and the receipt's stamp landing. The phone's touch
 * feedback is on for each test and put back after it; these steps are skipped on a device without a vibrator.
 */
@RunWith(AndroidJUnit4::class)
class InvitationDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain =
        RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose).around(TouchFeedback(compose))

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, "invite")
    private val vibrator: Vibrator = instrumentation.targetContext.getSystemService(VibratorManager::class.java).defaultVibrator
    private val vibrations = Vibrations(compose, DeviceShots(compose, "hapticswalk").folder, vibrator)
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The app's own preferences, and the invitation they had waiting before the test, if any. */
    private val preferences = PreferencesDefaults.standard(instrumentation.targetContext)
    private val pendingBefore = preferences.data(FriendInvitationStore.STORAGE_KEY)

    @After
    fun putBack() {
        store.set(appearanceBefore)
        val pending = pendingBefore
        if (pending != null) {
            preferences.set(pending, forKey = FriendInvitationStore.STORAGE_KEY)
        } else {
            preferences.removeObject(forKey = FriendInvitationStore.STORAGE_KEY)
        }
    }

    private lateinit var source: FakeDeliveries
    private lateinit var inbox: DeliveriesInbox

    /**
     * The tabs of [session] edge to edge as the app's window, the friends as [friends] sets them, the window's links
     * through an inbox that knows the fakes' invitations; with [signIn] the real session store on a fake auth service.
     */
    private fun show(
        session: AuthenticationState = reader,
        signIn: Boolean = false,
        fontScale: Float = 1f,
        friends: FakeFriendsClient.() -> Unit = { accepting() },
    ) {
        store.set(AppAppearance.Light)
        source = FakeDeliveries(DemoCards.parcels(), session)
        source.friendsClient.friends()
        inbox = DeliveriesInbox(invitationLink = source.invitation::open)
        compose.waitsForMaps()
        compose.setContent {
            val dark = appearanceSetting().isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            val scope = rememberCoroutineScope()
            val sessionStore = if (signIn) remember { signInSession(scope).also { it.showWelcome() } } else null
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = session, dark = dark, reduceMotion = true, source = source, inbox = inbox, session = sessionStore)
            }
        }
        compose.waitUntil(5_000) { exists(TAB_BAR) }
        compose.waitForIdle()
    }

    /** The service answering: the invitation can be accepted, and accepting it makes Sasha a friend. */
    private fun FakeFriendsClient.accepting() {
        answer = { request ->
            when (request.action) {
                FriendsAction.AcceptInvite -> {
                    snapshot = FriendFixtures.circle(listOf(sasha, FriendFixtures.ada), snapshot.profile)
                    FriendsActionResponse(snapshot = snapshot, acceptedFriend = sasha)
                }
                FriendsAction.SaveProfile -> {
                    snapshot = snapshot.copy(profile = FriendFixtures.profile.copy(nickname = request.nickname ?: ""))
                    FriendsActionResponse(snapshot = snapshot)
                }
                else -> FriendsActionResponse(snapshot = snapshot)
            }
        }
    }

    /**
     * The invitation's [link] to the window, as the trampoline or a browser hands one over; then its welcome, once
     * [until] holds (its parcel, unless said).
     */
    private fun follow(link: String = LINK, until: () -> Boolean = { exists(WELCOME_OPEN_PARCEL) }) {
        instrumentation.runOnMainSync { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(link))) }
        compose.waitUntil(5_000) { until() }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** A finger on what has [tag] where it stands, then what it brings. */
    private fun press(tag: String, until: String) {
        compose.onNodeWithTag(tag).performClick()
        compose.waitUntil(5_000) { exists(until) }
        compose.waitForIdle()
    }

    private fun back() {
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        compose.waitForIdle()
    }

    /**
     * Accepted: the receipt's stamp landed, taken before the arrival goes on to the friends; then the friends, once the
     * friend revealed has landed.
     */
    private fun acceptedThenTheFriends(button: String) {
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(button).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(250)
        compose.waitForIdle()
        assertTrue(exists(INVITATION_STAMP))
        shots.take("${name.methodName}-receipt")
        compose.mainClock.autoAdvance = true
        compose.waitUntil(5_000) { exists(PeekTab.Friends.screen) && exists(friendCardTag(sasha.id)) }
        // Revealed, Sasha lands once the circle has scrolled to them, and the focus is let go as the landing goes on.
        compose.waitUntil(5_000) { source.friendsActivity.focusId == null }
        compose.waitForIdle()
        assertFalse(source.invitation.isPresenting)
        assertTrue("Sasha's card drawn", drawn(sasha.id))
    }

    /**
     * Whether the card of [friend] is drawn: just inside its top edge the window shows its fill, not the page beside
     * it, which shows through the card while it waits to land at alpha 0.
     */
    private fun drawn(friend: UUID): Boolean {
        val card = compose.onNodeWithTag(friendCardTag(friend), useUnmergedTree = true).getUnclippedBoundsInRoot()
        val window = compose.onRoot().captureToImage().asAndroidBitmap()
        val scale = compose.density.density
        val y = ((card.top + 4.dp).value * scale).toInt()
        val inside = window.getPixel(((card.left + card.right).value / 2 * scale).toInt(), y)
        val beside = window.getPixel(((card.left - 6.dp).value * scale).toInt(), y)
        return inside != beside
    }

    @Test
    fun aLinkToTheWindowTheWelcomeTheAcceptanceAndFinish() {
        show()
        follow()
        assertFalse(exists(TAB_BAR))
        shots.take("${name.methodName}-welcome")
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPT)
        shots.take("${name.methodName}-acceptance")
        acceptedThenTheFriends(INVITATION_ACCEPT)
        shots.take(name.methodName)
    }

    @Test
    fun signedOutTheParcelOpensIntoTheSignInAndBackShutsIt() {
        show(AuthenticationState.Welcome, signIn = true)
        follow()
        shots.take("${name.methodName}-welcome")
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.waitUntil(5_000) { source.invitation.opened && !exists(INVITATION_WELCOME) }
        compose.waitForIdle()
        shots.take("${name.methodName}-signIn")
        back()
        compose.waitUntil(5_000) { exists(WELCOME_OPEN_PARCEL) }
        assertFalse(source.invitation.opened)
        back()
        compose.waitUntil(5_000) { !source.invitation.isPresenting && exists(TAB_BAR) }
        compose.waitForIdle()
        assertFalse(compose.activity.isFinishing)
        shots.take(name.methodName)
    }

    @Test
    fun withoutAProfileTheSheetJoinsAndAccepts() {
        show { snapshot = FriendFixtures.circle(profile = null); accepting() }
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPTANCE)
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo()
        press(INVITATION_ACCEPT, until = INVITATION_SHEET)
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin")
        compose.waitForIdle()
        shots.take("${name.methodName}-sheet")
        acceptedThenTheFriends(FRIENDS_SAVE)
        shots.take(name.methodName)
    }

    // C-H01 to C-H03: what the arrival asks the vibrator for.

    /**
     * C-H02, C-H03: signed out, the parcel tapped asks for a soft impact at 0.65 as it starts to open, then a light one
     * at 0.35 as the sign-in takes the welcome's place; the system's back shutting the parcel asks for nothing.
     */
    @Test
    fun signedOutTheParcelAsksForASoftImpactThenTheSignInALightOne() {
        assumeTrue("No vibrator to ask", vibrator.hasVibrator())
        show(AuthenticationState.Welcome, signIn = true)
        follow()
        vibrations.mark()
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.waitUntil(5_000) { source.invitation.opened && !exists(INVITATION_WELCOME) }
        compose.waitForIdle()
        vibrations.assertInOrder("C-H02-C-H03-open-signed-out", vibrations.soft(0.65f), vibrations.light(0.35f))
        back()
        compose.waitUntil(5_000) { exists(WELCOME_OPEN_PARCEL) }
        compose.waitForIdle()
        assertFalse(source.invitation.opened)
        vibrations.assertNone("C-H03-back")
    }

    /**
     * C-H02, C-H03, C-H01: signed in, the parcel tapped asks for its soft impact at 0.65, then the light one at 0.35 as
     * the acceptance takes the welcome's place: once opened the screen is the sign-in's, whoever reads it, as on the
     * iPhone. Accepted, the receipt's stamp lands with a soft impact at 0.45, and the friends come after it with nothing
     * more.
     */
    @Test
    fun signedInTheParcelAsksForASoftImpactAndTheReceiptsStampAnother() {
        assumeTrue("No vibrator to ask", vibrator.hasVibrator())
        show()
        follow()
        vibrations.mark()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPT)
        vibrations.assertInOrder("C-H02-C-H03-open-signed-in", vibrations.soft(0.65f), vibrations.light(0.35f))
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.waitUntil(5_000) { !source.invitation.isPresenting && exists(PeekTab.Friends.screen) }
        compose.waitForIdle()
        vibrations.assertOne("C-H01-stamp", vibrations.soft(0.45f))
    }

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val touches = ScreenTouches(compose)

    private fun text(key: String) = localizer.text(key, emptyMap())

    /** Whether the part tagged [tag], or something in it, shows [text]. */
    private fun shows(tag: String, text: String) = compose.onAllNodes(
        hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** How the text [matcher] finds first is laid out. */
    private fun layoutOf(matcher: SemanticsMatcher): TextLayoutResult {
        val node = compose.onAllNodes(matcher, useUnmergedTree = true).onFirst().fetchSemanticsNode()
        return compose.runOnUiThread {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    /** The window's height, in pixels. */
    private fun windowHeight(): Float = compose.runOnUiThread { compose.activity.window.decorView.height.toFloat() }

    /** What the focused field asks of the keyboard, as the window hands it to the keyboard. */
    private fun editorInfo(): EditorInfo {
        compose.waitForIdle()
        val view = (compose.onNode(isFocused()).fetchSemanticsNode().root as ViewRootForTest).view
        return EditorInfo().also { info -> compose.runOnUiThread { view.onCreateInputConnection(info) } }
    }

    /** The window's room shrinks for the keyboard, as MainActivity's manifest asks (`adjustResize`). */
    private fun resizesForTheKeyboard() {
        compose.runOnUiThread {
            @Suppress("DEPRECATION")
            compose.activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    /** The keyboard's height where the window has it, in pixels: none where none shows. */
    private fun keyboardHeight(): Int = compose.runOnUiThread {
        val insets = ViewCompat.getRootWindowInsets(compose.activity.window.decorView)
        if (insets?.isVisible(WindowInsetsCompat.Type.ime()) == true) insets.getInsets(WindowInsetsCompat.Type.ime()).bottom else 0
    }

    /** Whether the device shows a keyboard on the screen for a field, as the sign-in's device test asks. */
    private fun showsAKeyboard(): Boolean {
        val context = compose.activity
        val hardware = context.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
        return !hardware || Settings.Secure.getInt(context.contentResolver, "show_ime_with_hard_keyboard", 0) != 0
    }

    /** The keyboard's height unchanged for half a second, and the UI idle. */
    private fun awaitKeyboardStill() {
        var last = -1
        var since = SystemClock.uptimeMillis()
        compose.waitUntil(KEYBOARD_MILLIS) {
            val height = keyboardHeight()
            if (height != last) {
                last = height
                since = SystemClock.uptimeMillis()
            }
            SystemClock.uptimeMillis() - since >= 500
        }
        compose.waitForIdle()
    }

    /** The acceptance's Accept, or "friends.enableToAccept" without a profile, scrolled to and tapped. */
    private fun accept() {
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.waitForIdle()
    }

    /** The profile's sheet, opened from the acceptance's "friends.enableToAccept". */
    private fun openProfileSheet() {
        accept()
        compose.waitUntil(5_000) { exists(INVITATION_SHEET) }
        compose.waitForIdle()
    }

    /** C-ST08: the welcome waits for the sender's name with the generic heading and no parcel, then offers it. */
    @Test
    fun theWelcomeWaitsForTheNameThenOffersTheParcel() {
        show()
        val held = CompletableDeferred<Unit>()
        source.previewing = held
        follow(until = { exists(INVITATION_LOADING) })
        assertTrue(shows(INVITATION_HEADING, text("friends.invitationGeneric")))
        // C-G01: the parcel isn't offered without the sender's name.
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        shots.take("C-ST08-loading")
        held.complete(Unit)
        compose.waitUntil(5_000) { exists(WELCOME_OPEN_PARCEL) }
        compose.waitForIdle()
        assertTrue(shows(INVITATION_HEADING, "Sasha"))
        assertFalse(exists(INVITATION_LOADING))
        assertFalse(exists(INVITATION_ERROR))
        shots.take("C-ST08-name")
    }

    /**
     * C-ST08: a link already used has no public preview; the account signed in asks the service who sent it, and the
     * welcome has the name.
     */
    @Test
    fun aUsedLinkRecoversTheNameForTheAccountSignedIn() {
        show {
            accepting()
            val others = answer
            answer = { request ->
                if (request.action == FriendsAction.PreviewInvite) FriendsActionResponse(snapshot = snapshot, previewNickname = "Sasha") else others(request)
            }
        }
        source.inviterFailure = DeliveryApiError.Service(INVITATION_UNAVAILABLE)
        follow()
        assertTrue(shows(INVITATION_HEADING, "Sasha"))
        assertTrue(source.friendsClient.requests.any { it.action == FriendsAction.PreviewInvite })
        assertFalse(exists(INVITATION_ERROR))
        shots.take("C-ST08-used-link-signed-in")
    }

    /** C-ST08: signed out, a link already used is unavailable, with no retry and no parcel. */
    @Test
    fun aUsedLinkIsUnavailableSignedOut() {
        show(AuthenticationState.Welcome, signIn = true)
        source.inviterFailure = DeliveryApiError.Service(INVITATION_UNAVAILABLE)
        follow(until = { exists(INVITATION_ERROR) })
        assertTrue(shows(INVITATION_ERROR, text("friends.inviteUnavailable")))
        assertFalse(exists(INVITATION_RETRY))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        assertTrue(source.friendsClient.requests.none { it.action == FriendsAction.PreviewInvite })
        shots.take("C-ST08-used-link-signed-out")
    }

    /** C-ST08: another failure says the friends are unavailable, with a retry that asks again and offers the parcel. */
    @Test
    fun anotherFailureOffersARetry() {
        show()
        source.inviterFailure = FriendFixtures.unavailable()
        follow(until = { exists(INVITATION_ERROR) })
        assertTrue(shows(INVITATION_ERROR, text("friends.unavailable")))
        assertTrue(exists(INVITATION_RETRY))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        shots.take("C-ST08-failed")
        source.inviterFailure = null
        press(INVITATION_RETRY, until = WELCOME_OPEN_PARCEL)
        assertFalse(exists(INVITATION_ERROR))
        assertEquals(2, source.previews.size)
    }

    /** C-ST08: a link with no code is unavailable at once, nothing asked and no retry. */
    @Test
    fun aLinkWithoutACodeIsUnavailable() {
        show()
        follow(link = "$INVITATION_BASE/invite", until = { exists(INVITATION_ERROR) })
        assertTrue(shows(INVITATION_ERROR, text("friends.inviteUnavailable")))
        assertFalse(exists(INVITATION_RETRY))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        assertTrue(source.previews.isEmpty())
        shots.take("C-ST08-no-code")
    }

    /**
     * C-G01: a finger on the parcel through the screen unwraps it; while it opens, the parcel is off; then the
     * acceptance comes.
     */
    @Test
    fun theParcelUnwrapsOnATapAndIsOffWhileItOpens() {
        show()
        follow()
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).assertIsEnabled()
        val parcel = touches.point(WELCOME_OPEN_PARCEL)
        compose.mainClock.autoAdvance = false
        try {
            touches.tap(parcel)
            compose.mainClock.advanceTimeByFrame()
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            compose.onNodeWithTag(WELCOME_OPEN_PARCEL).assertIsNotEnabled()
            assertFalse(source.invitation.opened)
            shots.take("C-G01-opening")
            compose.mainClock.advanceTimeBy(300)
        } finally {
            compose.mainClock.autoAdvance = true
        }
        compose.waitUntil(5_000) { source.invitation.opened && exists(INVITATION_ACCEPT) }
        compose.waitForIdle()
    }

    /**
     * The invitation followed and opened into an acceptance that can't accept it: [key] as its heading with Close and
     * no Accept, saved as C-ST10-[state]; then Close, back to the tabs.
     */
    private fun showsTheOutcome(state: String, key: String) {
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_OUTCOME)
        assertTrue(shows(INVITATION_OUTCOME, text(key)))
        assertTrue(exists(INVITATION_OUTCOME_CLOSE))
        assertFalse(exists(INVITATION_ACCEPT))
        assertFalse(exists(INVITATION_ERROR))
        shots.take("C-ST10-$state")
        compose.onNodeWithTag(INVITATION_OUTCOME_CLOSE).performScrollTo().performClick()
        compose.waitUntil(5_000) { !source.invitation.isPresenting && exists(TAB_BAR) }
        compose.waitForIdle()
    }

    /** C-ST10: already accepted, already friends and the reader's own invitation each say so, with Close. */
    @Test
    fun anInvitationThatCantBeAcceptedSaysWhyWithClose() {
        var preview: () -> FriendsActionResponse = { FriendsActionResponse() }
        show {
            accepting()
            val others = answer
            answer = { request -> if (request.action == FriendsAction.PreviewInvite) preview() else others(request) }
        }
        preview = { FriendsActionResponse(snapshot = source.friendsClient.snapshot, invitationState = FriendsActionResponseInvitationState.AlreadyAccepted) }
        showsTheOutcome("already-accepted", "friends.inviteAlreadyAccepted")
        preview = { FriendsActionResponse(snapshot = source.friendsClient.snapshot, invitationState = FriendsActionResponseInvitationState.AlreadyFriends) }
        showsTheOutcome("already-friends", "friends.alreadyFriends")
        preview = { throw DeliveryApiError.Service("Cannot accept your own invitation") }
        showsTheOutcome("own-invitation", "friends.selfInvitation")
    }

    /**
     * C-ST10: accepting refused: a full circle and any other failure say so with a retry, Accept still on; the reader's
     * own invitation becomes its outcome with Close; a link no longer available leaves Accept off, with no retry.
     */
    @Test
    fun acceptingFailsAndSaysWhy() {
        var refusal: Exception = DeliveryApiError.Service("Your circle is full")
        show {
            accepting()
            val others = answer
            answer = { request -> if (request.action == FriendsAction.AcceptInvite) throw refusal else others(request) }
        }
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPT)
        accept()
        compose.waitUntil(5_000) { shows(INVITATION_ERROR, text("friends.circleFull")) }
        compose.waitForIdle()
        assertTrue(exists(INVITATION_RETRY))
        compose.onNodeWithTag(INVITATION_ACCEPT).assertIsEnabled()
        shots.take("C-ST10-circle-full")

        refusal = DeliveryApiError.Service("Something went wrong")
        accept()
        compose.waitUntil(5_000) { shows(INVITATION_ERROR, text("friends.actionFailed")) }
        compose.waitForIdle()
        assertTrue(exists(INVITATION_RETRY))
        compose.onNodeWithTag(INVITATION_ACCEPT).assertIsEnabled()
        shots.take("C-ST10-action-failed")

        refusal = DeliveryApiError.Service("Cannot accept your own invitation")
        accept()
        compose.waitUntil(5_000) { exists(INVITATION_OUTCOME) }
        compose.waitForIdle()
        assertTrue(shows(INVITATION_OUTCOME, text("friends.selfInvitation")))
        assertFalse(exists(INVITATION_ACCEPT))
        shots.take("C-ST10-own-on-accepting")
        compose.onNodeWithTag(INVITATION_OUTCOME_CLOSE).performScrollTo().performClick()
        compose.waitUntil(5_000) { !source.invitation.isPresenting && exists(TAB_BAR) }

        refusal = DeliveryApiError.Service(INVITATION_UNAVAILABLE)
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPT)
        accept()
        compose.waitUntil(5_000) { shows(INVITATION_ERROR, text("friends.inviteUnavailable")) }
        compose.waitForIdle()
        assertFalse(exists(INVITATION_RETRY))
        compose.onNodeWithTag(INVITATION_ACCEPT).assertIsNotEnabled()
        shots.take("C-ST10-unavailable")
    }

    /**
     * C-G07: the profile's sheet shows its grabber and a throw down on it closes the sheet. C-G06: a finger dragging the
     * sheet down takes the nickname's keyboard down with it, and its focus, the sheet staying, where the device shows a
     * keyboard.
     * C-G11: the nickname asks for Done and sentence capitals, and Done lets the field go. C-G07: while it joins, the
     * sheet stays against a throw and the system's back; joined, it closes.
     */
    @Test
    fun theProfileSheetSwipesDownItsKeyboardFollowsAndItHoldsWhileJoining() {
        show { snapshot = FriendFixtures.circle(profile = null); accepting() }
        resizesForTheKeyboard()
        follow()
        press(WELCOME_OPEN_PARCEL, until = INVITATION_ACCEPTANCE)
        openProfileSheet()
        assertTrue(exists(SHEET_GRABBER))
        touches.fling(touches.point(SHEET_GRABBER), Offset(0f, windowHeight() / 2))
        compose.waitUntil(5_000) { !exists(INVITATION_SHEET) }
        compose.waitForIdle()
        assertTrue(exists(INVITATION_ACCEPT))

        openProfileSheet()
        compose.onNodeWithTag(FRIENDS_NICKNAME).performScrollTo().performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(FRIENDS_NICKNAME).assertIsFocused()
        val info = editorInfo()
        assertEquals(EditorInfo.IME_ACTION_DONE, info.imeOptions and EditorInfo.IME_MASK_ACTION)
        assertTrue("Sentence capitals", info.inputType and InputType.TYPE_TEXT_FLAG_CAP_SENTENCES != 0)
        if (showsAKeyboard()) runCatching { compose.waitUntil(KEYBOARD_COMES_MILLIS) { keyboardHeight() > 0 } }
        if (keyboardHeight() > 0) {
            awaitKeyboardStill()
            val keyboard = keyboardHeight()
            // From just above the field, in the sheet's scroll: 0.8 of the keyboard's height, which leaves it below
            // half its height as the finger lifts, so it goes on down.
            val from = touches.point(FRIENDS_NICKNAME, y = 0f) - Offset(0f, 4 * compose.density.density)
            touches.drag(from, Offset(0f, keyboard * 0.8f), steps = 30)
            compose.waitUntil(KEYBOARD_MILLIS) { keyboardHeight() == 0 }
            awaitKeyboardStill()
            assertTrue(exists(INVITATION_SHEET))
            compose.onNodeWithTag(FRIENDS_NICKNAME).assertIsNotFocused()
            shots.take("C-G06-sheet-keyboard-down")
        }
        if (!compose.onAllNodes(hasTestTag(FRIENDS_NICKNAME) and isFocused()).fetchSemanticsNodes().any()) {
            compose.onNodeWithTag(FRIENDS_NICKNAME).performClick()
            compose.waitForIdle()
        }
        compose.onNodeWithTag(FRIENDS_NICKNAME).performImeAction()
        compose.waitForIdle()
        compose.onNodeWithTag(FRIENDS_NICKNAME).assertIsNotFocused()

        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin")
        val held = CompletableDeferred<Unit>()
        source.friendsClient.acting = held
        compose.onNodeWithTag(FRIENDS_SAVE).performScrollTo().performClick()
        compose.waitUntil(5_000) { source.friendsClient.requests.any { it.action == FriendsAction.SaveProfile } }
        compose.waitForIdle()
        touches.fling(touches.point(SHEET_GRABBER), Offset(0f, windowHeight() / 2))
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue("The sheet stays while it joins", exists(INVITATION_SHEET))
        back()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue("The back leaves the sheet while it joins", exists(INVITATION_SHEET))
        assertTrue(source.invitation.isPresenting)
        shots.take("C-G07-sheet-joining")
        held.complete(Unit)
        compose.waitUntil(5_000) { !exists(INVITATION_SHEET) }
        compose.waitUntil(5_000) { !source.invitation.isPresenting }
        compose.waitForIdle()
    }

    /**
     * C-AX12: at the largest text the welcome's lockup keeps each line on one line, at the size it caps at
     * (accessibility1) or shrunk to 70 % of it at the least.
     */
    @Test
    fun theLockupKeepsOneLineAtTheLargestText() {
        show(fontScale = 2f)
        follow()
        val type = PeekTypography(DynamicTypeSize.Accessibility1, Density(compose.density.density, 2f))
        val lines = listOf(
            "app.title" to type.fixed(type.scaled(16f, IosTextStyle.Subheadline)).fontSize.value,
            "native.tagline" to type.fixed(type.scaled(10f, IosTextStyle.Caption2)).fontSize.value,
        )
        for ((key, full) in lines) {
            val laid = layoutOf(hasText(text(key)) and hasAnyAncestor(hasTestTag(INVITATION_WELCOME)))
            assertEquals("$key on one line", 1, laid.lineCount)
            val size = laid.layoutInput.style.fontSize.value
            assertTrue("$key at $size sp, between 70 % of $full and $full", size <= full + 0.01f && size >= full * 0.7f - 0.01f)
        }
        shots.take("C-AX12-lockup-largest")
    }

    private companion object {
        /** A made-up invitation code, in the 16-character form the service gives. */
        const val LINK = "$INVITATION_BASE/invite#Qa3vT8nL5wZ1pR7k"

        /** What the service says of a link that leads nowhere, used or revoked. */
        const val INVITATION_UNAVAILABLE = "Invitation unavailable"

        /** How long a keyboard is given to come or go, and to come the first time a layout of it is drawn. */
        const val KEYBOARD_MILLIS = 3_000L
        const val KEYBOARD_COMES_MILLIS = 15_000L

        val sasha = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Sasha", FriendStats(5, 3, emptyList()), arrivedThisWeek = false)
    }
}
