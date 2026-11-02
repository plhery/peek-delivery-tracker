package com.plhery.peekdeliverytracker.ui.arrival

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.ArrivalTilt
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.FriendInvitationStore
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.api.FriendCard
import com.plhery.peekdeliverytracker.core.api.FriendStats
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponseInvitationState
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_BASE
import com.plhery.peekdeliverytracker.ui.deliveries.INVITATION_HOST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CLOSE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NICKNAME
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NOTICE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PANEL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SAVE
import com.plhery.peekdeliverytracker.ui.friends.FakeFriendsClient
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.io.IOException
import java.util.UUID
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A friend's invitation received (C-S02, C-S12, C-S13, C-S18) on made-up codes and names: each way a link comes in,
 * over the tabs and in the demo; the welcome while its sender is asked, failing and retried, or leading nowhere; the
 * parcel opened into the sign-in, signing in keeping the invitation and landing on the acceptance; the acceptance's
 * check, its outcomes, the profile's sheet (join and accept, edit sharing, no closing while joining), the receipt and
 * its stamp, then the friends tab with the circle the acceptance answered and the friend revealed, in front or on
 * return; Accept and the sharing saved through a rotation; the acceptance's own scroll; ✕, back and Close; the notice
 * of an invitation accepted kept away meanwhile; the code waiting through a rotation and process death; the welcome's
 * parcel leaning with the device; what TalkBack reads. Nothing leaves the test.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class InvitationTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val client: FakeFriendsClient get() = source.friendsClient
    private val invitation: FriendInvitationStore get() = source.invitation
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = READER_EMAIL))
    private var session: SessionStore? = null
    private val recreation = Recreation(compose)

    /** Where the parcel was drawn each time, and how far open; and how it leant. */
    private val placed = mutableListOf<Rect>()
    private val opens = mutableListOf<Float>()
    private val tilts = mutableListOf<ArrivalTilt>()

    /** The window's lifecycle as the arrival sees it, which a test sends to the background and back. */
    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    /** The session shown, which a test changes as the app's would. */
    private var shownSession by mutableStateOf<AuthenticationState>(AuthenticationState.Welcome)

    /**
     * The root for [state] with the invitation [link] opened first (none where null), the friends as [friends] sets
     * them, the device moving as [motion] says; with [signIn] the real session store on a fake auth service signs in,
     * from the first open. A test made again ([Recreation]) reads [source] afresh, as a new process has new stores.
     */
    private fun show(
        state: AuthenticationState = AuthenticationState.Welcome,
        link: String? = LINK,
        reduceMotion: Boolean = true,
        signIn: Boolean = false,
        deliveries: FakeDeliveries = FakeDeliveries(DemoCards.parcels(), state),
        motion: ArrivalMotionSource = NoArrivalMotion,
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
        shownSession = state
        source = deliveries
        client.friends()
        if (link != null) assertTrue(invitation.open(link))
        compose.waitsForMaps()
        // The parcel's greeting comes back every 4.6 s for as long as the welcome shows: the clock never runs out of
        // frames by itself then, so a test in motion moves it on.
        if (!reduceMotion) compose.mainClock.autoAdvance = false
        recreation.setContent {
            val scope = rememberCoroutineScope()
            val store = if (signIn) remember { signInSession(scope).also { it.showWelcome() } } else null
            session = store
            CompositionLocalProvider(
                LocalLifecycleOwner provides owner,
                LocalArrivalParcel provides { rect, open ->
                    placed += rect
                    opens += open
                },
                LocalArrivalParcelTilt provides { tilts += it },
                LocalArrivalMotion provides motion,
            ) {
                RootHost(state = shownSession, source = remember { source }, reduceMotion = reduceMotion, session = store)
            }
        }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun shows(text: String) = compose.onAllNodes(hasText(text, substring = true)).fetchSemanticsNodes().isNotEmpty()

    private fun semantics(tag: String): SemanticsConfiguration = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config

    private fun label(tag: String): String = semantics(tag)[SemanticsProperties.ContentDescription].single()

    private fun heading(tag: String) = semantics(tag).contains(SemanticsProperties.Heading)

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
    }

    /** A tap on what has [tag] where it stands, out of any scroll. */
    private fun press(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    private fun lifecycle(state: Lifecycle.State) {
        compose.runOnUiThread { owner.registry.currentState = state }
        settle()
    }

    /** The window's lifecycle at [state], the clock moved on [millis] by hand. */
    private fun lifecycle(state: Lifecycle.State, millis: Long) {
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { owner.registry.currentState = state }
        compose.mainClock.advanceTimeBy(millis)
    }

    /** The clock moved on a frame at a time until [done], for a second at most, as by hand. */
    private fun advanceUntil(done: () -> Boolean) {
        compose.mainClock.autoAdvance = false
        repeat(60) {
            if (done()) return
            compose.mainClock.advanceTimeByFrame()
        }
        assertTrue(done())
    }

    /** How far down the page tagged [tag] is scrolled, and how far it could be. */
    private fun scroll(tag: String): Pair<Float, Float> {
        val node = compose.onAllNodes(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.VerticalScrollAxisRange) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
            useUnmergedTree = true,
        ).fetchSemanticsNodes().first()
        val range = node.config[SemanticsProperties.VerticalScrollAxisRange]
        return range.value() to range.maxValue()
    }

    /** The parcel tapped open: it opens, and the invitation's next page follows. */
    private fun openParcel() {
        press(WELCOME_OPEN_PARCEL)
        settle()
    }

    private fun requests(action: FriendsAction): List<FriendsActionRequest> = client.requests.filter { it.action == action }

    /** Whether the tabs show, [tab] chosen. */
    private fun tabShown(tab: PeekTab) = compose.onAllNodes(
        hasContentDescription(text(tab.copyKey)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab) and isSelected(),
    ).fetchSemanticsNodes().isNotEmpty()

    /** The title of the sheet the profile's form stands in; null with none open. */
    private fun paneTitle(): String? {
        var node = compose.onAllNodes(hasTestTag(FRIENDS_PANEL), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()
        while (node != null) {
            node.config.getOrNull(SemanticsProperties.PaneTitle)?.let { return it }
            node = node.parent
        }
        return null
    }

    /**
     * The service answering: the invitation can be accepted, accepting it makes Sasha a friend in [circle], and the
     * profile saved is the one sent.
     */
    private fun FakeFriendsClient.accepting(circle: List<FriendCard> = listOf(sasha, FriendFixtures.ada)) {
        answer = { request ->
            when (request.action) {
                FriendsAction.PreviewInvite -> FriendsActionResponse(previewNickname = "Sasha")
                FriendsAction.AcceptInvite -> {
                    snapshot = FriendFixtures.circle(circle, snapshot.profile)
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

    // Each way in.

    @Test
    fun aLinkToTheWindowShowsTheWelcomeOverTheTabsAndAsksWhoSentIt() {
        show(reader, link = null)
        assertTrue(tabShown(PeekTab.Deliveries))
        val inbox = DeliveriesInbox(invitationLink = invitation::open)
        compose.runOnUiThread { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse("https://elsewhere.example/invite#$CODE"))) }
        settle()
        assertFalse("Another site's link", invitation.isPresenting)
        assertTrue(tabShown(PeekTab.Deliveries))

        compose.runOnUiThread { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse(LINK))) }
        settle()
        assertTrue(exists(INVITATION_WELCOME))
        assertFalse(exists(TAB_BAR))
        assertEquals(listOf(CODE), source.previews)
        compose.onNodeWithTag(INVITATION_HEADING).assert(hasText(text("friends.invitationTitle", mapOf("name" to "Sasha"))))
        assertTrue(exists(WELCOME_OPEN_PARCEL))
    }

    @Test
    fun theSitesPreviewLinkItsOtherHostAndTheAppsSchemeOpenItToo() {
        show(reader, link = null)
        for (link in listOf("$INVITATION_BASE/i/$CODE", "https://$INVITATION_HOST/invite#$CODE", "peekdeliverytracker://invite#$CODE")) {
            compose.runOnUiThread { invitation.dismiss() }
            settle()
            compose.runOnUiThread { assertTrue(link, invitation.open(link)) }
            settle()
            assertEquals(link, CODE, invitation.code)
            assertTrue(link, exists(INVITATION_WELCOME))
        }
        compose.runOnUiThread { assertFalse(invitation.open("peekdeliverytracker://friend/${sasha.id}")) }
    }

    // The welcome.

    @Test
    fun whileTheSenderIsAskedTheWelcomeWaitsWithItsSpinnerAndNoParcelToOpen() {
        val held = CompletableDeferred<Unit>()
        show(deliveries = FakeDeliveries(DemoCards.parcels()).apply { previewing = held })
        assertTrue(exists(INVITATION_LOADING))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        compose.onNodeWithTag(INVITATION_HEADING).assert(hasText(text("friends.invitationGeneric")))
        held.complete(Unit)
        settle()
        assertFalse(exists(INVITATION_LOADING))
        assertTrue(exists(WELCOME_OPEN_PARCEL))
    }

    @Test
    fun aPreviewThatFailedOffersRetryWhichAsksAgain() {
        val deliveries = FakeDeliveries(DemoCards.parcels()).apply { inviterFailure = IOException("offline") }
        show(deliveries = deliveries)
        compose.onNodeWithTag(INVITATION_ERROR).assert(hasText(text("friends.unavailable")))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        deliveries.inviterFailure = null
        tap(INVITATION_RETRY)
        assertEquals(listOf(CODE, CODE), deliveries.previews)
        assertFalse(exists(INVITATION_ERROR))
        assertTrue(exists(WELCOME_OPEN_PARCEL))
    }

    @Test
    fun aLinkThatCantBeReadLeadsNowhereWithoutRetry() {
        show(link = "$INVITATION_BASE/invite#not-a-code")
        compose.onNodeWithTag(INVITATION_ERROR).assert(hasText(text("friends.inviteUnavailable")))
        assertFalse(exists(INVITATION_RETRY))
        assertFalse(exists(WELCOME_OPEN_PARCEL))
        assertTrue(source.previews.isEmpty())
    }

    @Test
    fun aLinkAlreadyUsedLeadsNowhereWithoutRetry() {
        show(deliveries = FakeDeliveries(DemoCards.parcels()).apply { inviterFailure = DeliveryApiError.Service("Invitation unavailable") })
        compose.onNodeWithTag(INVITATION_ERROR).assert(hasText(text("friends.inviteUnavailable")))
        assertFalse(exists(INVITATION_RETRY))
    }

    @Test
    fun signedInALinkAlreadyUsedAsksTheAccountForItsSender() {
        show(reader, deliveries = FakeDeliveries(DemoCards.parcels(), reader).apply { inviterFailure = DeliveryApiError.Service("Invitation unavailable") }) {
            accepting()
        }
        assertEquals(listOf(CODE), requests(FriendsAction.PreviewInvite).map { it.code })
        compose.onNodeWithTag(INVITATION_HEADING).assert(hasText(text("friends.invitationTitle", mapOf("name" to "Sasha"))))
    }

    // Signing in.

    @Test
    fun theParcelOpensIntoTheSignInWhichKeepsTheInvitationAndLandsOnTheAcceptance() {
        show(signIn = true) { accepting() }
        assertTrue(exists(INVITATION_WELCOME))
        openParcel()
        assertTrue(invitation.opened)
        assertFalse(exists(INVITATION_WELCOME))
        assertTrue(exists(SIGN_IN))
        assertTrue(shows(text("friends.signInToAccept")))
        compose.onNodeWithTag(INVITATION_HEADING).assert(hasText(text("friends.invitationTitle", mapOf("name" to "Sasha"))))

        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(READER_EMAIL)
        tap(EMAIL_SEND)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        tap(CODE_VERIFY)
        assertTrue(session?.state is AuthenticationState.SignedIn)
        assertTrue(invitation.isPresenting)
        assertFalse(exists(SIGN_IN))
        assertTrue(exists(INVITATION_ACCEPTANCE))
        assertEquals(listOf(CODE), requests(FriendsAction.PreviewInvite).map { it.code })
        assertTrue(exists(INVITATION_ACCEPT))
    }

    @Test
    @Config(qualifiers = LANDSCAPE_PHONE)
    fun theAcceptanceOpensAtItsTopHoweverFarTheSignInWasScrolled() {
        show(signIn = true) { accepting() }
        openParcel()
        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(READER_EMAIL)
        tap(EMAIL_SEND)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.onNodeWithTag(SIGN_IN).performTouchInput { swipeUp() }
        settle()
        val (signIn, _) = scroll(SIGN_IN)
        assertTrue("The sign-in scrolled: $signIn", signIn > 0f)
        compose.onNodeWithTag(CODE_VERIFY).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertTrue(exists(INVITATION_ACCEPTANCE))
        val (acceptance, room) = scroll(INVITATION_ACCEPTANCE)
        assertTrue("The acceptance scrolls: $room", room > 0f)
        assertEquals(0f, acceptance)
    }

    // The acceptance.

    @Test
    fun theAcceptanceChecksTheInvitationAcceptsItLandsTheStampAndRevealsTheFriend() {
        show(reader) { accepting() }
        val presented = source.friendsActivity.presentationId
        openParcel()
        assertTrue(exists(INVITATION_ACCEPTANCE))
        assertEquals(listOf(CODE), requests(FriendsAction.PreviewInvite).map { it.code })
        assertTrue(exists(INVITATION_SHARING))
        compose.onNodeWithTag(INVITATION_ACCEPT).assert(hasText(text("friends.accept"))).assertIsEnabled()
        // The friends tab's own load never answers: only the circle the acceptance answered can show Sasha.
        client.loading = CompletableDeferred()

        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(listOf(CODE), requests(FriendsAction.AcceptInvite).map { it.code })
        val answered = invitation.receipt?.snapshot
        assertNotNull(answered)
        assertTrue(exists(INVITATION_STAMP))
        assertTrue(shows(text("friends.friendshipDelivered")))
        assertFalse(exists(INVITATION_ACCEPT))
        advanceUntil { !invitation.isPresenting }
        assertEquals(sasha.id, source.friendsActivity.focusId)
        assertEquals(answered, source.friendsActivity.arrivalSnapshot)
        compose.mainClock.autoAdvance = true
        settle()

        assertEquals(1, invitation.completed)
        assertFalse(exists(INVITATION_ACCEPTANCE))
        assertTrue(tabShown(PeekTab.Friends))
        assertNotEquals(presented, source.friendsActivity.presentationId)
        assertTrue(exists(friendCardTag(sasha.id)))
    }

    @Test
    fun anAcceptanceUnderWayOutlastsARotationAndItsReceiptLands() {
        show(reader) { accepting() }
        openParcel()
        val held = CompletableDeferred<Unit>()
        client.acting = held
        tap(INVITATION_ACCEPT)
        assertEquals(listOf(CODE), requests(FriendsAction.AcceptInvite).map { it.code })
        val loads = client.loads
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(INVITATION_ACCEPTANCE))
        assertNull(invitation.receipt)
        // The acceptance made again neither checks again nor accepts again: the one under way answers.
        assertEquals(loads, client.loads)
        held.complete(Unit)
        settle()
        settle()
        assertEquals(1, requests(FriendsAction.AcceptInvite).size)
        assertFalse(invitation.isPresenting)
        assertEquals(1, invitation.completed)
        assertTrue(tabShown(PeekTab.Friends))
        assertTrue(exists(friendCardTag(sasha.id)))
    }

    @Test
    fun anInvitationAlreadyAcceptedSaysSoAndCloses() {
        outcome(FriendsActionResponseInvitationState.AlreadyAccepted, "friends.inviteAlreadyAccepted")
    }

    @Test
    fun anInvitationFromAFriendSaysSoAndCloses() {
        outcome(FriendsActionResponseInvitationState.AlreadyFriends, "friends.alreadyFriends")
    }

    /** The check answering [state]: the acceptance says [key] as its heading, offers Close alone, which dismisses. */
    private fun outcome(state: FriendsActionResponseInvitationState, key: String) {
        show(reader) { answer = { FriendsActionResponse(invitationState = state) } }
        openParcel()
        compose.onNodeWithTag(INVITATION_OUTCOME).assert(hasText(text(key)))
        assertTrue(heading(INVITATION_OUTCOME))
        assertFalse(exists(INVITATION_ACCEPT))
        assertFalse(exists(INVITATION_SHARING))
        tap(INVITATION_OUTCOME_CLOSE)
        assertFalse(invitation.isPresenting)
        assertTrue(tabShown(PeekTab.Deliveries))
    }

    @Test
    fun onesOwnInvitationSaysSo() {
        show(reader) { actionFailure = DeliveryApiError.Service("Cannot accept your own invitation") }
        openParcel()
        compose.onNodeWithTag(INVITATION_OUTCOME).assert(hasText(text("friends.selfInvitation")))
        assertFalse(exists(INVITATION_ACCEPT))
        assertTrue(exists(INVITATION_OUTCOME_CLOSE))
    }

    @Test
    fun anAcceptanceAnsweredAlreadyFriendsSaysSoWithoutAReceipt() {
        show(reader) {
            answer = { request ->
                if (request.action == FriendsAction.AcceptInvite) {
                    FriendsActionResponse(invitationState = FriendsActionResponseInvitationState.AlreadyFriends)
                } else {
                    FriendsActionResponse(snapshot = snapshot)
                }
            }
        }
        openParcel()
        tap(INVITATION_ACCEPT)
        compose.onNodeWithTag(INVITATION_OUTCOME).assert(hasText(text("friends.alreadyFriends")))
        assertNull(invitation.receipt)
        assertTrue(invitation.isPresenting)
    }

    @Test
    fun anAcceptanceThatFailedCanBeTriedAgain() {
        show(reader) { accepting() }
        openParcel()
        client.actionFailure = IOException("offline")
        tap(INVITATION_ACCEPT)
        assertNull(invitation.receipt)
        assertTrue(exists(INVITATION_ACCEPT))
        assertTrue(invitation.isPresenting)
        client.actionFailure = null
        tap(INVITATION_ACCEPT)
        settle()
        assertFalse(invitation.isPresenting)
    }

    // The profile's sheet.

    @Test
    fun withoutAProfileTheSheetJoinsAndAccepts() {
        show(reader) {
            snapshot = FriendFixtures.circle(profile = null)
            accepting()
        }
        openParcel()
        assertFalse(exists(INVITATION_SHARING))
        compose.onNodeWithTag(INVITATION_ACCEPT).assert(hasText(text("friends.enableToAccept")))
        tap(INVITATION_ACCEPT)
        assertTrue(exists(INVITATION_SHEET))
        assertEquals(text("friends.enable"), paneTitle())
        compose.onNodeWithTag(FRIENDS_SAVE).assert(hasText(text("friends.joinAndAccept")))
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin")
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(FRIENDS_SAVE).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(100)
        assertEquals(listOf("Robin"), requests(FriendsAction.SaveProfile).map { it.nickname })
        assertEquals(listOf(CODE), requests(FriendsAction.AcceptInvite).map { it.code })
        assertNotNull(invitation.receipt)
        compose.mainClock.advanceTimeBy(1_000)
        compose.mainClock.autoAdvance = true
        settle()
        assertFalse(exists(INVITATION_SHEET))
        assertTrue(tabShown(PeekTab.Friends))
    }

    @Test
    fun theSheetNeitherClosesNorGoesBackWhileJoining() {
        val held = CompletableDeferred<Unit>()
        show(reader) {
            snapshot = FriendFixtures.circle(profile = null)
            accepting()
        }
        openParcel()
        tap(INVITATION_ACCEPT)
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin")
        client.acting = held
        tap(FRIENDS_SAVE)
        compose.onNodeWithTag(FRIENDS_CLOSE).assertIsNotEnabled()
        back()
        assertTrue(exists(INVITATION_SHEET))
        assertTrue(invitation.opened)
        held.complete(Unit)
        settle()
        settle()
        assertFalse(invitation.isPresenting)
        assertTrue(tabShown(PeekTab.Friends))
    }

    @Test
    fun editSharingSavesTheProfileAndStaysOnTheAcceptance() {
        show(reader) { accepting() }
        openParcel()
        tap(INVITATION_EDIT_SHARING)
        assertEquals(text("friends.settings"), paneTitle())
        compose.onNodeWithTag(FRIENDS_SAVE).assert(hasText(text("friends.save")))
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin B")
        tap(FRIENDS_SAVE)
        assertEquals(listOf("Robin B"), requests(FriendsAction.SaveProfile).map { it.nickname })
        assertFalse(exists(INVITATION_SHEET))
        assertTrue(requests(FriendsAction.AcceptInvite).isEmpty())
        assertTrue(exists(INVITATION_ACCEPT))

        tap(INVITATION_EDIT_SHARING)
        press(FRIENDS_CLOSE)
        assertFalse(exists(INVITATION_SHEET))
        assertTrue(exists(INVITATION_ACCEPTANCE))
    }

    @Test
    fun savingTheSharingOutlastsARotationAndTheSheetCloses() {
        show(reader) { accepting() }
        openParcel()
        tap(INVITATION_EDIT_SHARING)
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement("Robin B")
        val held = CompletableDeferred<Unit>()
        client.acting = held
        tap(FRIENDS_SAVE)
        recreation.recreate()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(INVITATION_SHEET))
        assertEquals(text("friends.settings"), paneTitle())
        held.complete(Unit)
        settle()
        assertEquals(listOf("Robin B"), requests(FriendsAction.SaveProfile).map { it.nickname })
        assertFalse(exists(INVITATION_SHEET))
        assertTrue(requests(FriendsAction.AcceptInvite).isEmpty())
        assertTrue(exists(INVITATION_ACCEPT))
    }

    // Dismissal.

    @Test
    fun closeForgetsTheInvitationAndShowsTheTabsAgain() {
        show()
        press(INVITATION_CLOSE)
        assertFalse(invitation.isPresenting)
        assertNull(source.invitationDefaults.data(FriendInvitationStore.STORAGE_KEY))
        assertFalse(exists(INVITATION_WELCOME))
        assertTrue(exists(TAB_BAR))
    }

    @Test
    fun backOnTheWelcomeClosesItAsInTheDemo() {
        show(AuthenticationState.Demo)
        assertTrue(exists(INVITATION_WELCOME))
        back()
        assertFalse(invitation.isPresenting)
        assertTrue(exists(DEMO_EXIT))
    }

    @Test
    fun backFromTheSignInShutsTheParcelOnTheWelcome() {
        show()
        openParcel()
        assertTrue(exists(SIGN_IN))
        back()
        assertFalse(invitation.opened)
        assertTrue(invitation.isPresenting)
        assertTrue(exists(INVITATION_WELCOME))
        assertEquals(0f, opens.last())
    }

    @Test
    fun backFromTheAcceptanceShutsTheParcelOnTheWelcome() {
        show(reader) { accepting() }
        openParcel()
        press(INVITATION_BACK)
        assertFalse(invitation.opened)
        assertTrue(exists(INVITATION_WELCOME))
        assertFalse(exists(INVITATION_ACCEPTANCE))
    }

    @Test
    fun theNoticeOfAnInvitationAcceptedWaitsUntilTheInvitationHasGone() {
        show(reader) {
            accepting()
            activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo")))
        }
        assertTrue(source.friendsActivity.updates.isNotEmpty())
        assertFalse(exists(FRIENDS_NOTICE))
        press(INVITATION_CLOSE)
        assertTrue(exists(FRIENDS_NOTICE))
    }

    // The code kept.

    @Test
    fun theOpeningParcelAndThePageOutlastARotation() {
        show()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.mainClock.advanceTimeBy(100)
        recreation.recreate(parceled = true)
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(invitation.opened)
        assertTrue(exists(SIGN_IN))
        recreation.recreate(parceled = true)
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(SIGN_IN))
        assertFalse(exists(INVITATION_WELCOME))
    }

    @Test
    fun theCodeWaitsThroughProcessDeathAndOpensWhereItWasLeft() {
        show()
        openParcel()
        source = source.newProcess()
        assertTrue(invitation.isPresenting)
        assertTrue(invitation.opened)
        assertEquals(CODE, invitation.code)
        recreation.recreate(parceled = true)
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(exists(SIGN_IN))
        assertEquals(listOf(CODE), source.previews)

        back()
        press(INVITATION_CLOSE)
        assertFalse(source.newProcess().invitation.isPresenting)
    }

    @Test
    fun aReceiptThatLandsOutOfTheFrontWaitsThenPlaysItsStampAndRevealsTheFriendOnReturn() {
        show(reader) { accepting() }
        openParcel()
        val held = CompletableDeferred<Unit>()
        client.acting = held
        tap(INVITATION_ACCEPT)
        lifecycle(Lifecycle.State.CREATED)
        // The friends tab's own load never answers: only the circle the acceptance answered can show Sasha.
        client.loading = CompletableDeferred()
        held.complete(Unit)
        settle()
        val answered = invitation.receipt?.snapshot
        assertNotNull(answered)
        assertTrue(invitation.isPresenting)
        assertTrue(exists(INVITATION_STAMP))
        assertNull(source.friendsActivity.focusId)

        // Back in front, the stamp lands over 30 + 80 + 180 ms, then the friend is revealed with the circle.
        lifecycle(Lifecycle.State.RESUMED, millis = 150)
        assertTrue(invitation.isPresenting)
        advanceUntil { !invitation.isPresenting }
        assertEquals(sasha.id, source.friendsActivity.focusId)
        assertEquals(answered, source.friendsActivity.arrivalSnapshot)
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(tabShown(PeekTab.Friends))
        assertTrue(exists(friendCardTag(sasha.id)))
    }

    @Test
    fun aReceiptLeftDuringItsStampFinishesAtOnceWithoutItsCircle() {
        show(reader) { accepting() }
        openParcel()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(100)
        assertNotNull(invitation.receipt)
        assertTrue(invitation.isPresenting)
        lifecycle(Lifecycle.State.CREATED, millis = 50)
        assertFalse(invitation.isPresenting)
        assertEquals(sasha.id, source.friendsActivity.focusId)
        assertNull(source.friendsActivity.arrivalSnapshot)
        compose.mainClock.autoAdvance = true
        settle()
    }

    // TalkBack and motion.

    @Test
    fun theWelcomeReadsAsAHeadingAButtonWithItsHintAndClose() {
        show()
        assertTrue(heading(INVITATION_HEADING))
        assertEquals(text("common.close"), label(INVITATION_CLOSE))
        assertEquals(text("arrival.tapToOpen"), label(WELCOME_OPEN_PARCEL))
        assertEquals(Role.Button, semantics(WELCOME_OPEN_PARCEL)[SemanticsProperties.Role])
        assertEquals(text("arrival.openHint"), semantics(WELCOME_OPEN_PARCEL).getOrNull(SemanticsActions.OnClick)?.label)
    }

    @Test
    fun theAcceptanceReadsItsBackItsPostcardAndItsReceiptAsAHeading() {
        show(reader) { accepting() }
        openParcel()
        assertEquals(text("welcome.back"), label(INVITATION_BACK))
        assertTrue(shows("Sasha"))
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(INVITATION_ACCEPT).performScrollTo().performClick()
        compose.mainClock.advanceTimeBy(100)
        val delivered = compose.onAllNodes(hasText(text("friends.friendshipDelivered"))).fetchSemanticsNodes().single()
        assertTrue(delivered.config.contains(SemanticsProperties.Heading))
        compose.mainClock.autoAdvance = true
        settle()
    }

    @Test
    fun theWelcomesParcelLeansWithTheDeviceFromHowItWasHeldOnArrival() {
        val motion = FakeMotion()
        show(reduceMotion = false, motion = motion)
        assertTrue(motion.following)
        motion.reading = held(roll = 0.2, pitch = -0.1)
        compose.mainClock.advanceTimeBy(500)
        assertEquals(ArrivalTilt(), tilts.last())
        // Turned to its right side 0.36 rad further: all the way right, level up and down.
        motion.reading = held(roll = 0.56, pitch = -0.1)
        compose.mainClock.advanceTimeBy(1_000)
        val leaning = tilts.last()
        assertTrue("Leaning right: $leaning", leaning.x > 0.95 && abs(leaning.y) < 0.01)
        // And its top tipped 0.18 rad up: half up as well.
        motion.reading = held(roll = 0.56, pitch = 0.08)
        compose.mainClock.advanceTimeBy(1_000)
        val tipped = tilts.last()
        assertTrue("Leaning up too: $tipped", tipped.x > 0.95 && abs(tipped.y - 0.5) < 0.02)
    }

    @Test
    fun underReduceMotionTheParcelStaysLevelAndTheMotionIsntRead() {
        val motion = FakeMotion()
        motion.reading = held(roll = 0.4, pitch = 0.2)
        show(reduceMotion = true, motion = motion)
        assertEquals(0, motion.starts)
        assertTrue(tilts.isNotEmpty())
        assertTrue(tilts.all { it == ArrivalTilt() })
    }

    @Test
    fun theMotionIsReadOnlyWhileTheWelcomeGreetsInFront() {
        val motion = FakeMotion()
        show(reduceMotion = false, motion = motion)
        assertEquals(1, motion.starts)
        motion.reading = held(roll = 0.0, pitch = 0.0)
        compose.mainClock.advanceTimeBy(100)
        motion.reading = held(roll = 0.36, pitch = 0.0)
        compose.mainClock.advanceTimeBy(500)
        assertTrue(tilts.last().x > 0.5)

        // Paused, then stopped: the readings stop and the parcel comes level.
        lifecycle(Lifecycle.State.STARTED, millis = 200)
        assertFalse(motion.following)
        assertEquals(ArrivalTilt(), tilts.last())
        lifecycle(Lifecycle.State.CREATED, millis = 200)
        assertFalse(motion.following)
        // Back in front: read again, from how the device is held now.
        lifecycle(Lifecycle.State.RESUMED, millis = 500)
        assertTrue(motion.following)
        assertEquals(2, motion.starts)
        assertEquals(ArrivalTilt(), tilts.last())

        // Opening the parcel ends the greeting.
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertFalse(motion.following)
        compose.mainClock.advanceTimeBy(500)
        assertEquals(ArrivalTilt(), tilts.last())
        compose.mainClock.advanceTimeBy(1_000)
        assertTrue(invitation.opened)
        assertFalse(motion.following)
        // Back from the sign-in on the welcome, it greets again, and ✕ takes it away with its readings.
        back()
        compose.mainClock.advanceTimeBy(500)
        assertTrue(motion.following)
        compose.onNodeWithTag(INVITATION_CLOSE).performClick()
        compose.mainClock.advanceTimeBy(500)
        assertFalse(motion.following)
        assertEquals(motion.starts, motion.stops)
    }

    @Test
    fun theParcelOpensOverASecondThenMovesToTheSignInAsTheWelcomeFades() {
        show(reduceMotion = false)
        val welcome = placed.last()
        compose.onNodeWithTag(WELCOME_OPEN_PARCEL).performClick()
        compose.mainClock.advanceTimeBy(500)
        val half = opens.last()
        assertTrue("Opening over 0.95 s: $half", half > 0.2f && half < 0.95f)
        assertFalse(invitation.opened)
        compose.mainClock.advanceTimeBy(560)
        assertTrue(invitation.opened)
        compose.mainClock.advanceTimeBy(3_000)
        assertTrue("At the sign-in's anchor: ${placed.last()} from $welcome", placed.last().width < welcome.width)
        assertEquals(1f, opens.last(), 0.01f)
    }

    /** The device's motion as a test moves it: the [reading] it gives, how often it was started and stopped. */
    private class FakeMotion : ArrivalMotionSource {
        var reading: ArrivalMotionReading? = null
        var starts = 0
        var stops = 0
        val following get() = starts > stops

        override fun start(): ArrivalMotionReadings {
            starts++
            return object : ArrivalMotionReadings {
                override val latest get() = reading
                override fun stop() {
                    stops++
                }
            }
        }
    }

    private companion object {
        const val LANDSCAPE_PHONE = "w844dp-h390dp-land-xhdpi"

        /**
         * The device held turned [roll] about its long axis (its right side going down) after [pitch] about its short
         * one (its top coming up), as its attitude reads, unmoved by the hand.
         */
        fun held(roll: Double, pitch: Double): ArrivalMotionReading {
            val (cr, sr) = cos(roll) to sin(roll)
            val (cp, sp) = cos(pitch) to sin(pitch)
            // Rx(pitch) · Ry(roll), the device's axes to the world's, row by row.
            val rotation = doubleArrayOf(cr, 0.0, sr, sp * sr, cp, -sp * cr, -cp * sr, sp, cp * cr)
            return ArrivalMotionReading(FloatArray(9) { rotation[it].toFloat() }, 0.0, 0.0)
        }

        /** A made-up invitation code, in the 16-character form the service gives. */
        const val CODE = "Qa3vT8nL5wZ1pR7k"
        const val LINK = "$INVITATION_BASE/invite#$CODE"

        val sasha = FriendCard(UUID.fromString("14000000-0000-4000-8000-000000000014"), "Sasha", FriendStats(5, 3, emptyList()), arrivedThisWeek = false)
    }
}
