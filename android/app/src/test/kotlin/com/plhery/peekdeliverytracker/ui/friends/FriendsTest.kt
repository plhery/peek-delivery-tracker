package com.plhery.peekdeliverytracker.ui.friends

import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.VibrationEffect.Composition
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.core.content.IntentCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.ExperimentalPalette
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeRoute
import com.plhery.peekdeliverytracker.core.api.FriendUpdate
import com.plhery.peekdeliverytracker.core.api.FriendsAction
import com.plhery.peekdeliverytracker.core.api.FriendsActivity
import com.plhery.peekdeliverytracker.core.api.FriendProfile
import com.plhery.peekdeliverytracker.core.api.FriendsActionRequest
import com.plhery.peekdeliverytracker.core.api.FriendsActionResponse
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.platform.notificationTap
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowVibrator
import org.robolectric.shadows.ShadowVibrator.PrimitiveEffect

/**
 * The friends tab (A-S20, C-S19 to C-S21, C-S23 to C-S28, C-S32) on made-up friends: signed out; loading, failing and
 * retried, without a profile, with no friend, one and four, a revealed friend first or gone; a friend's link and a
 * friend's notification opened; the pull,
 * the reload each 60 s and the clear as the tab or the app goes, but for the app the invitation is shared to; the
 * colour from the id; the demo's changes, kept across tabs and forgotten once it is left; the profile's form, the
 * panels' titles, Close, detents and no dismissal while working; the account's invitation (made once, retried, shared,
 * copied, cancelled, the previous ones cancelled) and the demo's; the layouts by text size and window; what TalkBack
 * reads; the analytics.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FriendsTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val client get() = source.friendsClient
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
    private val views = mutableListOf<String>()

    /** The window's lifecycle as the tab sees it, which a test sends to the background and back. */
    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    /** The session shown, which a test changes as the app's would ([switch]). */
    private var shownSession by mutableStateOf<AuthenticationState>(reader)

    /**
     * The friends tab of [session], its friends as [friends] sets them, chosen from the tab bar unless [choose] is false;
     * the window's links come through [inbox].
     */
    private fun show(
        session: AuthenticationState = reader,
        parcels: List<Parcel> = DemoCards.parcels(),
        fontScale: Float = 1f,
        choose: Boolean = true,
        inbox: DeliveriesInbox? = null,
        recreation: Recreation? = null,
        friends: FakeFriendsClient.() -> Unit = {},
    ) {
        shownSession = session
        source = FakeDeliveries(parcels, session)
        client.friends()
        compose.waitsForMaps()
        val content = @Composable {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalLifecycleOwner provides owner) {
                RootHost(state = shownSession, dark = false, onView = { views += it }, source = source, inbox = inbox)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        settle()
        if (choose) choose("Friends")
    }

    /** The session changed to [state], as the app's store changes it. */
    private fun switch(state: AuthenticationState) {
        compose.runOnUiThread { shownSession = state }
        settle()
    }

    /** The window's lifecycle moved to [state]: stopped (CREATED), paused (STARTED) or in front again (RESUMED). */
    private fun lifecycle(state: Lifecycle.State) {
        compose.runOnUiThread { owner.registry.currentState = state }
        settle()
    }

    /** A link to the app's window, `peekdeliverytracker://friend/<id>`. */
    private fun friendLink(id: UUID) = Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://friend/$id"))

    /** What opening the notification of friend `id` who accepted the invitation starts, as the app posts it. */
    private fun friendNotification(id: UUID) =
        notificationTap(mapOf("title" to "Peek", "body" to "A friend accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$id"))

    private fun choose(label: String) {
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private val vibrator: Vibrator get() = compose.activity.getSystemService(VibratorManager::class.java).defaultVibrator

    /** What the phone played since it was last asked, the haptics the test has just set off; the vibrator ready again. */
    private fun felt(): List<PrimitiveEffect> {
        val played = shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects.toList()
        ShadowVibrator.reset()
        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_TICK, Composition.PRIMITIVE_LOW_TICK))
        return played
    }

    /** A light impact at [intensity], as the phone plays it. */
    private fun light(intensity: Float) = listOf(PrimitiveEffect(Composition.PRIMITIVE_TICK, intensity, 0))

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether [text] shows to TalkBack: what a part clears for itself isn't. */
    private fun shows(text: String) = compose.onAllNodes(hasText(text)).fetchSemanticsNodes().isNotEmpty()

    private fun semantics(tag: String): SemanticsConfiguration = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config

    private fun label(tag: String): String = semantics(tag)[SemanticsProperties.ContentDescription].single()

    private fun hint(tag: String): String? = semantics(tag).getOrNull(SemanticsActions.OnClick)?.label

    /** What the part tagged [tag] reads to TalkBack, its words and those of what it holds, in their order. */
    private fun words(tag: String): List<String> {
        val words = mutableListOf<String>()
        fun walk(node: SemanticsNode) {
            node.config.getOrNull(SemanticsProperties.Text)?.let { texts -> words += texts.map { it.text } }
            node.children.forEach(::walk)
        }
        walk(compose.onNodeWithTag(tag).fetchSemanticsNode())
        return words
    }

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

    /** The friends' cards, from the top. */
    private fun cards(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a friend's card") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("friends.card.") == true },
    ).fetchSemanticsNodes().sortedBy { it.boundsInRoot.top }.map { it.config[SemanticsProperties.Text].first().text }

    /** The panel's title, as TalkBack names the sheet it stands in; null with none open. */
    private fun panel(): String? {
        var node: SemanticsNode? = compose.onAllNodes(hasTestTag(FRIENDS_PANEL), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull() ?: return null
        while (node != null) {
            node.config.getOrNull(SemanticsProperties.PaneTitle)?.let { return it }
            node = node.parent
        }
        return null
    }

    private fun toggle(tag: String): ToggleableState? = semantics(tag).getOrNull(SemanticsProperties.ToggleableState)

    private fun flip(tag: String) {
        compose.runOnUiThread { semantics(tag)[SemanticsActions.OnClick].action?.invoke() }
        settle()
    }

    private fun name(): String = semantics(FRIENDS_NICKNAME)[SemanticsProperties.EditableText].text

    private fun type(value: String) {
        compose.onNodeWithTag(FRIENDS_NICKNAME).performTextReplacement(value)
        settle()
    }

    private fun clipboard(): String? = compose.activity.getSystemService(ClipboardManager::class.java).primaryClip?.getItemAt(0)?.text?.toString()

    private fun actions(name: String): List<DeliveryAnalytics.Outcome?> = source.actions.indices.filter { source.actions[it] == name }.map { source.outcomes[it] }

    private fun requests(action: FriendsAction): List<FriendsActionRequest> = client.requests.filter { it.action == action }

    /** Someone just accepted an invitation: in no circle the service has answered yet. */
    private val newcomer = UUID.fromString("99000000-0000-4000-8000-000000000099")

    private fun panelTop(): Float = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot().top.value

    // Signed out (A-S20).

    @Test
    fun signedOutTheTabSaysWhatItIsForAndSignInOpensTheSignIn() {
        show(AuthenticationState.Welcome)
        assertTrue(shows(text("friends.title")))
        assertTrue(shows(text("friends.introSummary")))
        assertEquals(listOf(text("arrival.signInTitle")), words(FRIENDS_SIGN_IN))
        // The postage pair is drawn, not read.
        assertFalse(shows("A"))
        assertFalse(shows("M"))
        tap(FRIENDS_SIGN_IN)
        assertEquals(1, source.signIns)
        assertEquals(0, client.loads)
        assertFalse(exists(FRIENDS_INVITE))
    }

    // The tab's states (C-S19).

    @Test
    fun whileTheCircleLoadsASpinnerStandsForIt() {
        val loading = CompletableDeferred<Unit>()
        show { this.loading = loading }
        assertTrue(exists(FRIENDS_LOADING))
        assertFalse(exists(FRIENDS_SHARING))
        loading.complete(Unit)
        settle()
        assertFalse(exists(FRIENDS_LOADING))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
        assertTrue(views.contains("friends"))
    }

    @Test
    fun aCircleThatFailsToLoadSaysSoAndRetryLoadsItAgain() {
        show { failure = FriendFixtures.unavailable() }
        assertEquals(text("friends.unavailable"), words(FRIENDS_ERROR).single())
        assertEquals(listOf(text("common.retry")), words(FRIENDS_RETRY))
        assertFalse(exists(FRIENDS_LOADING))
        client.failure = null
        tap(FRIENDS_RETRY)
        assertFalse(exists(FRIENDS_ERROR))
        assertFalse(exists(FRIENDS_RETRY))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
        assertEquals(2, client.loads)
    }

    @Test
    fun withoutAProfileTheIntroCreatesOneTrimmedWithItsDefaults() {
        show { snapshot = FriendFixtures.circle(emptyList(), profile = null) }
        assertFalse(exists(FRIENDS_SHARING))
        assertEquals(listOf(text("friends.join")), words(FRIENDS_JOIN))
        tap(FRIENDS_JOIN)
        assertEquals(text("friends.join"), panel())
        // A new profile shares its stats and not its arrivals, under no name yet.
        assertEquals("", name())
        assertEquals(ToggleableState.On, toggle(FRIENDS_SHARE_STATS))
        assertEquals(ToggleableState.Off, toggle(FRIENDS_SHARE_ARRIVAL))
        assertEquals(text("friends.you"), words(FRIENDS_PREVIEW).first())
        assertEquals(listOf(text("friends.join")), words(FRIENDS_SAVE))
        compose.onNodeWithTag(FRIENDS_SAVE).assertIsNotEnabled()
        type("   ")
        compose.onNodeWithTag(FRIENDS_SAVE).assertIsNotEnabled()
        type("  Robin \n")
        assertEquals("Robin", words(FRIENDS_PREVIEW).first())
        compose.onNodeWithTag(FRIENDS_SAVE).assertIsEnabled()
        client.answer = { FriendsActionResponse(snapshot = FriendFixtures.circle(emptyList())) }
        tap(FRIENDS_SAVE)
        val saved = requests(FriendsAction.SaveProfile).single()
        assertEquals("Robin", saved.nickname)
        assertEquals(true, saved.shareStats)
        assertEquals(false, saved.shareArrival)
        assertNull(panel())
        assertTrue(shows(text("friends.emptyTitle")))
    }

    @Test
    fun theCircleShowsTheSharingRowTheCountAndEachFriendsCard() {
        show()
        assertEquals("${text("friends.yourSharing")}, ${text("friends.settings")}", label(FRIENDS_SHARING))
        assertEquals(Role.Button, semantics(FRIENDS_SHARING)[SemanticsProperties.Role])
        assertEquals(listOf(text("friends.circle"), "4"), words(FRIENDS_HEADER).take(2))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
        // Each card reads its name, what it shares and this week's arrival, its stamp drawn, not read.
        assertEquals(listOf("Ada", "2 stamps", text("friends.arrived")), words(friendCardTag(FriendFixtures.ada.id)))
        assertEquals(listOf("Bo", "1 stamp"), words(friendCardTag(FriendFixtures.bo.id)))
        assertEquals(listOf("Cy", text("friends.privateStats")), words(friendCardTag(FriendFixtures.cy.id)))
        assertEquals(listOf("Di", "0 stamps"), words(friendCardTag(FriendFixtures.di.id)))
        assertEquals(Role.Button, semantics(friendCardTag(FriendFixtures.ada.id))[SemanticsProperties.Role])
    }

    @Test
    fun oneFriendIsCountedAsOne() {
        show { snapshot = FriendFixtures.circle(listOf(FriendFixtures.bo)) }
        assertEquals(listOf(text("friends.circle"), "1"), words(FRIENDS_HEADER).take(2))
        assertEquals(listOf("Bo"), cards())
    }

    @Test
    fun anEmptyCircleOffersToInviteAFriend() {
        show { snapshot = FriendFixtures.circle(emptyList()) }
        assertTrue(exists(FRIENDS_SHARING))
        assertTrue(shows(text("friends.emptyTitle")))
        assertFalse(exists(FRIENDS_HEADER))
        assertEquals(emptyList<String>(), cards())
        assertEquals(listOf(text("friends.invite")), words(FRIENDS_INVITE))
        tap(FRIENDS_INVITE)
        assertEquals(text("friends.inviteTitle"), panel())
    }

    @Test
    fun aFriendRevealedChoosesTheTabAndComesFirst() {
        show(choose = false)
        assertFalse(exists(PeekTab.Friends.screen))
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.cy.id) }
        settle()
        assertTrue(exists(PeekTab.Friends.screen))
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        assertFalse(exists(FRIENDS_UNAVAILABLE_FRIEND))
    }

    @Test
    fun aFriendRevealedWhoIsNoLongerInTheCircleIsSaidToBeGone() {
        show()
        compose.runOnUiThread { source.friendsActivity.reveal(UUID.fromString("99000000-0000-4000-8000-000000000099")) }
        settle()
        assertEquals(listOf(text("friends.friendUnavailable")), words(FRIENDS_UNAVAILABLE_FRIEND))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun aLoadCalledOffAsItAnswersChecksNoNewerPresentation() {
        show()
        val late = CompletableDeferred<Unit>()
        client.answersWhenCalledOff = true
        client.loading = late
        compose.mainClock.advanceTimeBy(60_000)
        compose.waitForIdle()
        assertEquals(2, client.loads)
        // A newcomer revealed while the minute's load waits: it is called off, and the new presentation's load waits.
        client.loading = CompletableDeferred()
        compose.runOnUiThread { source.friendsActivity.reveal(newcomer) }
        settle()
        assertEquals(3, client.loads)
        assertFalse(exists(FRIENDS_UNAVAILABLE_FRIEND))
        // The answer for the presentation before says nothing of the newcomer, who isn't in that circle yet.
        late.complete(Unit)
        settle()
        assertFalse(exists(FRIENDS_UNAVAILABLE_FRIEND))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    // A friend's link (`open(_:)`'s `.friend` route).

    @Test
    fun aFriendsLinkSignedInRevealsThemOnTheFriendsTab() {
        val inbox = DeliveriesInbox()
        show(choose = false, inbox = inbox)
        assertFalse(exists(PeekTab.Friends.screen))
        compose.runOnUiThread { inbox.follow(friendLink(FriendFixtures.cy.id)) }
        settle()
        assertTrue(exists(PeekTab.Friends.screen))
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        // Landed, Cy is acknowledged and the focus let go.
        assertEquals(listOf(FriendFixtures.cy.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
        assertNull(source.friendsActivity.focusId)
        assertNull(inbox.friend.value)
    }

    @Test
    fun aFriendsLinkThatStartsTheWindowRevealsThemToo() {
        val inbox = DeliveriesInbox()
        inbox.follow(friendLink(FriendFixtures.bo.id))
        show(choose = false, inbox = inbox)
        assertTrue(exists(PeekTab.Friends.screen))
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        assertEquals(listOf(FriendFixtures.bo.id), requests(FriendsAction.AcknowledgeFriend).map { it.friendId })
    }

    /** A friend's notification opened (C-W15): revealed while someone is signed in and the window is in front, counted. */
    @Test
    fun aFriendsNotificationRevealsThemCounted() {
        val opened = mutableListOf<NativeRoute>()
        val inbox = DeliveriesInbox(notificationOpened = { opened += it })
        show(choose = false, inbox = inbox)
        compose.runOnUiThread { inbox.follow(friendNotification(FriendFixtures.cy.id)) }
        settle()
        assertTrue(exists(PeekTab.Friends.screen))
        assertEquals(listOf("Cy", "Ada", "Bo", "Di"), cards())
        assertEquals(listOf<NativeRoute>(NativeRoute.Friend(FriendFixtures.cy.id)), opened)
        assertNull(inbox.notifiedFriend.value)
    }

    /** `pendingFriendID`: held while nobody is signed in or the window is away, and revealed once both are so. */
    @Test
    fun aFriendsNotificationWaitsForSomeoneSignedInAndTheWindowInFront() {
        val inbox = DeliveriesInbox()
        show(AuthenticationState.Welcome, choose = false, inbox = inbox)
        compose.runOnUiThread { inbox.follow(friendNotification(FriendFixtures.bo.id)) }
        settle()
        assertEquals(FriendFixtures.bo.id, inbox.notifiedFriend.value)
        assertFalse(exists(PeekTab.Friends.screen))

        lifecycle(Lifecycle.State.CREATED)
        switch(reader)
        assertEquals(FriendFixtures.bo.id, inbox.notifiedFriend.value)
        lifecycle(Lifecycle.State.RESUMED)
        assertTrue(exists(PeekTab.Friends.screen))
        assertEquals(listOf("Bo", "Ada", "Cy", "Di"), cards())
        assertNull(inbox.notifiedFriend.value)
    }

    @Test
    fun aFriendsLinkDoesNothingForAGuest() {
        followsNothing(AuthenticationState.Welcome)
    }

    @Test
    fun aFriendsLinkDoesNothingInTheDemo() {
        followsNothing(AuthenticationState.Demo)
    }

    /** A friend's link to the window of [state] is taken and does nothing: no friend revealed, no tab chosen. */
    private fun followsNothing(state: AuthenticationState) {
        val inbox = DeliveriesInbox()
        show(state, choose = false, inbox = inbox)
        val presentation = source.friendsActivity.presentationId
        compose.runOnUiThread { inbox.follow(friendLink(FriendFixtures.cy.id)) }
        settle()
        assertNull(inbox.friend.value)
        assertFalse(exists(PeekTab.Friends.screen))
        assertNull(source.friendsActivity.focusId)
        assertEquals(presentation, source.friendsActivity.presentationId)
    }

    @Test
    fun aCardOpensTheFriendsPageCountedAndTheBackClosesIt() {
        show()
        tap(friendCardTag(FriendFixtures.bo.id))
        assertTrue(exists(FRIENDS_PAGE))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(null), actions("friend-open"))
        back()
        assertFalse(exists(FRIENDS_PAGE))
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun aFriendGoneFromTheCircleClosesTheirPage() {
        show()
        tap(friendCardTag(FriendFixtures.bo.id))
        client.snapshot = FriendFixtures.circle(listOf(FriendFixtures.ada))
        compose.runOnUiThread { source.scope.launch { source.friends.load(source.parcels.value) } }
        settle()
        assertFalse(exists(FRIENDS_PAGE))
    }

    @Test
    fun aPullFromTheTopLoadsTheCircleAgain() {
        show()
        assertEquals(1, client.loads)
        compose.onNodeWithTag(FRIENDS_SCROLL).performTouchInput {
            down(Offset(width / 2f, 200.dp.toPx()))
            repeat(30) { moveBy(Offset(0f, 700.dp.toPx() / 30)) }
            advanceEventTime(200)
            up()
        }
        settle()
        assertEquals(2, client.loads)
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun whileItShowsTheCircleLoadsAgainEachMinute() {
        show()
        assertEquals(1, client.loads)
        compose.mainClock.advanceTimeBy(58_000)
        compose.waitForIdle()
        assertEquals(1, client.loads)
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
        assertEquals(2, client.loads)
    }

    @Test
    fun anotherTabClearsTheCircleAndComingBackLoadsItAfresh() {
        show()
        choose("Passport")
        assertNull(source.friends.snapshotFlow.value)
        choose("Friends")
        assertEquals(2, client.loads)
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    /**
     * C-ST13, `onDisappear`: another tab chosen (by a parcel's link, as the panel keeps the tab bar from the finger)
     * closes the panel, which the tab's holder would otherwise have saved open before the tab's own disposal closed it.
     */
    @Test
    fun anotherTabClosesThePanel() {
        val inbox = DeliveriesInbox()
        show(inbox = inbox)
        tap(FRIENDS_SHARING)
        assertEquals(text("friends.settings"), panel())
        val parcel = DemoCards.parcels().first().id
        compose.runOnUiThread { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://parcel/$parcel"))) }
        settle()
        assertFalse(exists(PeekTab.Friends.screen))
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        choose("Friends")
        assertNull(panel())
    }

    @Test
    fun aParcelsLinkOverAFriendsPageTakesTheDeliveriesForwardAndClosesThePage() {
        val inbox = DeliveriesInbox()
        show(inbox = inbox)
        tap(friendCardTag(FriendFixtures.bo.id))
        assertTrue(exists(FRIENDS_PAGE))
        val parcel = DemoCards.parcels().first().id
        compose.runOnUiThread { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://parcel/$parcel"))) }
        settle()
        assertFalse(exists(PeekTab.Friends.screen))
        assertFalse(exists(FRIENDS_PAGE))
        // Back from the parcel's page to the list, and on the friends: the circle, loaded afresh, and no page.
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        choose("Friends")
        assertFalse(exists(FRIENDS_PAGE))
        assertEquals(2, client.loads)
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun inTheBackgroundTheCircleIsClearedAndItsPanelClosedAndLoadedAgainInFront() {
        show()
        tap(FRIENDS_SHARING)
        assertEquals(text("friends.settings"), panel())
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.CREATED }
        settle()
        assertNull(source.friends.snapshotFlow.value)
        assertNull(panel())
        compose.runOnUiThread { owner.registry.currentState = Lifecycle.State.RESUMED }
        settle()
        assertEquals(2, client.loads)
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun pausedTheCircleAndTheNoticeAreHiddenAndComeBackInFront() {
        show { activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo"))) }
        assertTrue(exists(FRIENDS_NOTICE))
        tap(FRIENDS_SHARING)
        // Paused, as under Recents or a system prompt (iOS's `.inactive`): nothing private is left on show.
        lifecycle(Lifecycle.State.STARTED)
        assertNull(source.friends.snapshotFlow.value)
        assertNull(panel())
        assertFalse(exists(FRIENDS_NOTICE))
        lifecycle(Lifecycle.State.RESUMED)
        assertEquals(2, client.loads)
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
        assertTrue(exists(FRIENDS_NOTICE))
    }

    // The cards' colours (C-S21).

    @Test
    fun aFriendsColourComesFromTheFirstByteOfTheirId() {
        fun id(first: Int) = UUID.fromString("%02x000000-0000-4000-8000-000000000000".format(first))
        val transit = ExperimentalPalette.transit to ExperimentalPalette.transitSurface
        val lilac = ExperimentalPalette.lilac to ExperimentalPalette.lilacSurface
        val pickup = ExperimentalPalette.pickup to ExperimentalPalette.pickupSurface
        val delivered = ExperimentalPalette.delivered to ExperimentalPalette.deliveredSurface
        assertEquals(listOf(transit, lilac, pickup, delivered, transit), listOf(0x00, 0x01, 0x02, 0x03, 0x04).map { friendTone(id(it)) })
        // Only the first byte counts, whatever follows it, the highest included.
        assertEquals(delivered, friendTone(UUID.fromString("ffffffff-ffff-4fff-bfff-ffffffffffff")))
        assertEquals(lilac, friendTone(UUID.fromString("11000000-0000-4000-8000-000000000001")))
        assertEquals(pickup, friendTone(UUID.fromString("22000000-0000-4000-8000-000000000002")))
        assertEquals(delivered, friendTone(UUID.fromString("33000000-0000-4000-8000-000000000003")))
    }

    // The demo.

    @Test
    fun theDemosCircleIsItsOwnAndAsksNothing() {
        show(AuthenticationState.Demo)
        assertEquals(listOf("Mila", "Noé", "Sam"), cards())
        assertEquals(listOf("Sam", text("friends.privateStats")), words(friendCardTag(UUID.fromString("33000000-0000-4000-8000-000000000003"))))
        assertEquals(0, client.loads)
    }

    @Test
    fun theDemosInvitationOffersToSignInInsteadWhichShowsTheWelcome() {
        show(AuthenticationState.Demo)
        tap(FRIENDS_INVITE)
        assertEquals(text("friends.inviteTitle"), panel())
        assertTrue(shows(text("friends.demoInvites")))
        assertTrue(exists(FRIENDS_REPLAY))
        assertFalse(exists(FRIENDS_INVITATION_URL))
        assertEquals(listOf(text("welcome.signInInstead")), words(FRIENDS_DEMO_SIGN_IN))
        tap(FRIENDS_DEMO_SIGN_IN)
        assertEquals(1, source.welcomes)
        assertNull(panel())
        assertTrue(client.requests.isEmpty())
    }

    @Test
    fun theDemosProfileIsSavedInMemory() {
        show(AuthenticationState.Demo)
        tap(FRIENDS_SHARING)
        assertEquals("Alex", name())
        type("Alexa ")
        flip(FRIENDS_SHARE_ARRIVAL)
        tap(FRIENDS_SAVE)
        assertNull(panel())
        assertEquals(FriendProfile("Alexa", shareStats = true, shareArrival = true), source.friends.snapshotFlow.value?.profile)
        assertTrue(client.requests.isEmpty())
    }

    @Test
    fun theDemosChangesOutlastATripToThePassport() {
        show(AuthenticationState.Demo)
        changeTheDemo()
        choose("Passport")
        choose("Friends")
        assertEquals(listOf("Mila", "Noé"), cards())
        assertEquals(FriendProfile("Alex", shareStats = false, shareArrival = false), source.friends.snapshotFlow.value?.profile)
    }

    @Test
    fun leavingTheDemoForgetsItsChangesAndItsCircleComesBackAfresh() {
        show(AuthenticationState.Demo)
        changeTheDemo()
        press(DEMO_EXIT)
        assertEquals(1, source.welcomes)
        switch(AuthenticationState.Welcome)
        assertFalse(exists(FRIENDS_SHARING))
        switch(AuthenticationState.Demo)
        assertEquals(listOf("Mila", "Noé", "Sam"), cards())
        assertEquals(FriendProfile("Alex", shareStats = true, shareArrival = false), source.friends.snapshotFlow.value?.profile)
        assertEquals(0, client.loads)
        assertTrue(client.requests.isEmpty())
    }

    /** In the demo: the stats no longer shared, from the profile's panel, and Sam removed from their page. */
    private fun changeTheDemo() {
        tap(FRIENDS_SHARING)
        flip(FRIENDS_SHARE_STATS)
        tap(FRIENDS_SAVE)
        assertNull(panel())
        tap(friendCardTag(UUID.fromString("33000000-0000-4000-8000-000000000003")))
        tap(FRIENDS_MANAGE_FRIENDSHIP)
        tap(FRIENDS_REMOVE)
        tap(FRIENDS_CONFIRM_REMOVE)
        assertFalse(exists(FRIENDS_PAGE))
        assertEquals(listOf("Mila", "Noé"), cards())
        assertEquals(FriendProfile("Alex", shareStats = false, shareArrival = false), source.friends.snapshotFlow.value?.profile)
    }

    // The profile's form (C-S23, C-S24).

    @Test
    fun theProfilesPanelKeepsTheNameTo24ScalarsAnEmojiWhole() {
        show()
        tap(FRIENDS_SHARING)
        assertEquals(text("friends.settings"), panel())
        assertEquals(FriendFixtures.profile.nickname, name())
        assertEquals(listOf(text("friends.save")), words(FRIENDS_SAVE))
        type("a".repeat(30))
        assertEquals("a".repeat(24), name())
        // 23 letters and an emoji of two halves: 24 scalars, 25 characters, kept whole.
        type("a".repeat(23) + "😀")
        assertEquals("a".repeat(23) + "😀", name())
        type("a".repeat(24) + "😀")
        assertEquals("a".repeat(24), name())
        // A flag is two scalars: only its first fits after 23 letters, as the iPhone's `unicodeScalars.prefix(24)`.
        type("a".repeat(23) + "🇨🇭")
        assertEquals("a".repeat(23) + "🇨", name())
        assertEquals("a".repeat(23).cutToScalars(), "a".repeat(23))
    }

    @Test
    fun theSharesFlipThePreviewAndAreSaved() {
        show()
        tap(FRIENDS_SHARING)
        // Robin shares their stats and not their arrivals: the preview says what friends would see.
        assertEquals(ToggleableState.On, toggle(FRIENDS_SHARE_STATS))
        assertEquals(ToggleableState.Off, toggle(FRIENDS_SHARE_ARRIVAL))
        assertTrue(words(FRIENDS_PREVIEW)[1].endsWith(" stamps") || words(FRIENDS_PREVIEW)[1].endsWith(" stamp"))
        assertEquals(2, words(FRIENDS_PREVIEW).size)
        flip(FRIENDS_SHARE_STATS)
        flip(FRIENDS_SHARE_ARRIVAL)
        assertEquals(text("friends.privateStats"), words(FRIENDS_PREVIEW)[1])
        assertEquals(3, words(FRIENDS_PREVIEW).size)
        tap(FRIENDS_SAVE)
        val saved = requests(FriendsAction.SaveProfile).single()
        assertEquals(FriendsActionRequest(action = FriendsAction.SaveProfile, nickname = "Robin", shareStats = false, shareArrival = true), saved)
        assertNull(panel())
    }

    @Test
    fun whileTheProfileSavesTheFormWaitsAndNothingClosesThePanel() {
        val acting = CompletableDeferred<Unit>()
        show { this.acting = acting }
        tap(FRIENDS_SHARING)
        tap(FRIENDS_SAVE)
        compose.onNodeWithTag(FRIENDS_SAVE).assertIsNotEnabled()
        compose.onNodeWithTag(FRIENDS_NICKNAME).assertIsNotEnabled()
        assertTrue(semantics(FRIENDS_SHARE_STATS).contains(SemanticsProperties.Disabled))
        compose.onNodeWithTag(FRIENDS_CLOSE).assertIsNotEnabled()
        back()
        assertEquals(text("friends.settings"), panel())
        acting.complete(Unit)
        settle()
        assertNull(panel())
    }

    @Test
    fun aProfileTheServiceRefusesSaysWhyInThePanelAndCloseForgetsIt() {
        show { actionFailure = DeliveryApiError.InvalidResponse() }
        tap(FRIENDS_SHARING)
        tap(FRIENDS_SAVE)
        assertEquals(text("friends.settings"), panel())
        assertEquals(text("friends.actionFailed"), words(FRIENDS_ERROR).single())
        press(FRIENDS_CLOSE)
        assertNull(panel())
        assertFalse(exists(FRIENDS_ERROR))
    }

    @Test
    fun theSharesReadAsSwitchesWithTheirDetailAsTheHint() {
        show()
        tap(FRIENDS_SHARING)
        assertEquals(text("friends.shareStats") + ", " + text("friends.sharedStatsSummary"), label(FRIENDS_SHARE_STATS))
        assertEquals(text("friends.sharedStatsSummary"), hint(FRIENDS_SHARE_STATS))
        assertEquals(Role.Switch, semantics(FRIENDS_SHARE_STATS)[SemanticsProperties.Role])
        assertEquals(text("friends.shareArrival"), label(FRIENDS_SHARE_ARRIVAL))
        assertEquals(text("friends.shareArrivalDetail"), hint(FRIENDS_SHARE_ARRIVAL))
        assertEquals(text("friends.nickname"), label(FRIENDS_NICKNAME))
        // Where arrivals aren't shared the preview keeps the line's place, unseen and unread.
        assertFalse(words(FRIENDS_PREVIEW).contains(text("friends.noArrival")))
    }

    // The panels (C-S25).

    @Test
    fun onAPhoneTheProfileStandsAtTheLargeDetentAndTheInvitationAt590() {
        show()
        tap(FRIENDS_SHARING)
        assertEquals(listOf(text("common.close")), words(FRIENDS_CLOSE))
        assertTrue("${panelTop()}", panelTop() < 20f)
        press(FRIENDS_CLOSE)
        assertNull(panel())
        tap(FRIENDS_INVITE)
        assertEquals(text("friends.inviteTitle"), panel())
        val height = compose.onRoot().getUnclippedBoundsInRoot().height.value
        assertEquals(height - 8 - 590, panelTop(), 1f)
        back()
        assertNull(panel())
    }

    @Test
    fun atTheAccessibilitySizesTheInvitationStandsAtTheLargeDetentAndTheHeaderStacks() {
        show(fontScale = 2f)
        val header = compose.onNodeWithTag(FRIENDS_HEADER).getUnclippedBoundsInRoot()
        val pill = compose.onNodeWithTag(FRIENDS_INVITE).getUnclippedBoundsInRoot()
        assertEquals(header.width.value, pill.width.value, 1f)
        assertEquals(header.bottom.value, pill.bottom.value, 1f)
        assertTrue(pill.top > header.top + 10.dp)
        tap(FRIENDS_INVITE)
        assertTrue("${panelTop()}", panelTop() < 20f)
    }

    @Test
    fun aDotKeepsToTheWordBeforeItAndAFriendsArrivalHasItsDot() {
        show { snapshot = FriendFixtures.circle(profile = FriendFixtures.profile.copy(shareArrival = true)) }
        // A line never starts with "·": a no-break space ties it to the part before it.
        assertTrue(line("${text("friends.shareStats")}\u00A0· ${text("friends.shareArrival")}") != null)
        assertTrue(exists(FRIENDS_ARRIVAL_DOT))
        tap(FRIENDS_SHARING)
        val summary = words(FRIENDS_PREVIEW)[1]
        assertEquals(summary, 2, summary.split("\u00A0· ").size - 1)
        assertFalse(summary, summary.contains(" · "))
        back()
        tap(FRIENDS_INVITE)
        assertTrue(shows(text("friends.inviteExpiry").replace(" · ", "\u00A0· ")))
    }

    @Test
    fun atTheAccessibilitySizesEachPartOfADottedLineTakesALineOfItsOwnAndAnArrivalLosesItsDot() {
        show(fontScale = 2f) { snapshot = FriendFixtures.circle(profile = FriendFixtures.profile.copy(shareArrival = true)) }
        assertLines(listOf(text("friends.shareStats"), text("friends.shareArrival")), within = FRIENDS_SHARING)
        // The row still reads as one button.
        assertEquals("${text("friends.yourSharing")}, ${text("friends.settings")}", label(FRIENDS_SHARING))
        // The words alone: the small dot would read as a stray "·".
        assertFalse(exists(FRIENDS_ARRIVAL_DOT))
        assertEquals(listOf("Ada", "2 stamps", text("friends.arrived")), words(friendCardTag(FriendFixtures.ada.id)))
        tap(FRIENDS_SHARING)
        // The name, then the three parts of what friends see, read in order.
        val preview = words(FRIENDS_PREVIEW)
        assertEquals(preview.toString(), 5, preview.size)
        assertLines(preview.subList(1, 4), within = FRIENDS_PREVIEW)
        back()
        tap(FRIENDS_INVITE)
        assertLines(text("friends.inviteExpiry").split(" · "), within = SHEET_PANEL)
    }

    /** The text node that shows [text] exactly, if one does, in the tree as drawn. */
    private fun line(text: String): DpRect? =
        compose.onAllNodes(hasText(text), useUnmergedTree = true).fetchSemanticsNodes().singleOrNull()?.let {
            compose.onNode(hasText(text), useUnmergedTree = true).getBoundsInRoot()
        }

    /** Each of [parts] stands on a line of its own under the one before, and no words in the part tagged [within] have a "·". */
    private fun assertLines(parts: List<String>, within: String) {
        val lines = parts.map { requireNotNull(line(it)) { "No line of its own for \"$it\"" } }
        lines.zipWithNext().forEachIndexed { i, (above, below) -> assertTrue("${parts[i + 1]} under ${parts[i]}", below.top >= above.bottom) }
        val dotted = hasText("·", substring = true) and hasAnyAncestor(hasTestTag(within))
        assertTrue(compose.onAllNodes(dotted, useUnmergedTree = true).fetchSemanticsNodes().isEmpty())
    }

    @Test
    fun atTheUsualSizeTheInvitePillStandsBesideTheTitle() {
        show()
        val header = compose.onNodeWithTag(FRIENDS_HEADER).getUnclippedBoundsInRoot()
        val pill = compose.onNodeWithTag(FRIENDS_INVITE).getUnclippedBoundsInRoot()
        assertTrue(pill.width < header.width / 2)
        assertEquals(header.right.value, pill.right.value, 1f)
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun onATabletThePanelIsAFormAndTheColumnAt680() {
        show()
        assertEquals(680f - 40f, compose.onNodeWithTag(FRIENDS_HEADER).getUnclippedBoundsInRoot().width.value, 1f)
        assertTrue(exists(FRIENDS_ACCOUNT_BUTTON))
        tap(FRIENDS_SHARING)
        assertEquals(580f, compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot().width.value, 1f)
    }

    // The invitation (C-S26 to C-S28).

    private val link = "https://example.com/i/preview000000001"

    @Test
    fun theInvitationIsMadeOnceAndItsLinkSharedAndCopied() {
        show()
        tap(FRIENDS_INVITE)
        assertEquals(1, requests(FriendsAction.CreateInvite).size)
        assertTrue(shows(text("friends.inviteExpiry").replace(" · ", "\u00A0· ")))
        assertEquals(link, words(FRIENDS_INVITATION_URL).single())
        assertEquals(text("friends.link") + ": " + link, label(FRIENDS_INVITATION_URL))
        repeat(3) { settle() }
        assertEquals(1, requests(FriendsAction.CreateInvite).size)

        tap(FRIENDS_SHARE)
        val chooser = requireNotNull(shadowOf(compose.activity).nextStartedActivity)
        assertEquals(Intent.ACTION_CHOOSER, chooser.action)
        val send = requireNotNull(IntentCompat.getParcelableExtra(chooser, Intent.EXTRA_INTENT, Intent::class.java))
        assertEquals(Intent.ACTION_SEND, send.action)
        assertEquals("text/plain", send.type)
        assertEquals(link, send.getStringExtra(Intent.EXTRA_TEXT))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Started), actions("friend-invite-share"))

        assertEquals(listOf(text("friends.copyLink")), words(FRIENDS_COPY))
        tap(FRIENDS_COPY)
        assertEquals(link, clipboard())
        assertEquals(listOf(text("friends.copied")), words(FRIENDS_COPY))
        assertEquals(listOf<DeliveryAnalytics.Outcome?>(DeliveryAnalytics.Outcome.Success), actions("friend-invite-copy"))
        // Copied it stays, as the iPhone's does.
        compose.mainClock.advanceTimeBy(5_000)
        compose.waitForIdle()
        assertEquals(listOf(text("friends.copied")), words(FRIENDS_COPY))
    }

    @Test
    fun theAppTheLinkIsSharedToKeepsThePanelItsLinkAndTheCircle() {
        show { activity = FriendsActivity(updates = listOf(FriendUpdate(FriendFixtures.bo.id, "Bo"))) }
        tap(FRIENDS_INVITE)
        tap(FRIENDS_SHARE)
        assertEquals(Intent.ACTION_CHOOSER, shadowOf(compose.activity).nextStartedActivity?.action)
        // The share sheet pauses the window; the app picked there stops it.
        lifecycle(Lifecycle.State.STARTED)
        assertEquals(text("friends.inviteTitle"), panel())
        assertEquals(4, source.friends.snapshotFlow.value?.friends?.size)
        assertEquals(1, source.friendsActivity.updatesFlow.value.size)
        lifecycle(Lifecycle.State.CREATED)
        assertEquals(text("friends.inviteTitle"), panel())
        assertEquals(link, words(FRIENDS_INVITATION_URL).single())
        assertEquals(4, source.friends.snapshotFlow.value?.friends?.size)
        assertEquals(1, source.friendsActivity.updatesFlow.value.size)
        // Back from it: the same link, and the circle loaded again as the window starts.
        lifecycle(Lifecycle.State.RESUMED)
        assertEquals(text("friends.inviteTitle"), panel())
        assertEquals(link, words(FRIENDS_INVITATION_URL).single())
        assertEquals(1, requests(FriendsAction.CreateInvite).size)
        assertEquals(2, client.loads)
        // Any other stop, with no Share before it, closes the panel and clears the circle and the notice.
        lifecycle(Lifecycle.State.CREATED)
        assertNull(panel())
        assertNull(source.friends.snapshotFlow.value)
        assertTrue(source.friendsActivity.updatesFlow.value.isEmpty())
        lifecycle(Lifecycle.State.RESUMED)
        assertNull(panel())
        assertEquals(listOf("Ada", "Bo", "Cy", "Di"), cards())
    }

    @Test
    fun aWindowEndedBehindTheAppTheLinkIsSharedToKeepsThePanelAndItsLink() {
        val recreation = Recreation(compose, owner.registry)
        show(recreation = recreation)
        tap(FRIENDS_INVITE)
        tap(FRIENDS_SHARE)
        lifecycle(Lifecycle.State.STARTED)
        // The app picked stops the window, and the system ends it while the app lives on ("Don't keep activities").
        recreation.recreate(inTheBackground = true)
        compose.mainClock.autoAdvance = true
        settle()
        assertEquals(text("friends.inviteTitle"), panel())
        assertEquals(link, words(FRIENDS_INVITATION_URL).single())
        assertEquals(1, requests(FriendsAction.CreateInvite).size)
    }

    @Test
    fun aShareSheetLeftWithoutSharingLeavesTheNextStopAsAnyOther() {
        show()
        tap(FRIENDS_INVITE)
        tap(FRIENDS_SHARE)
        // The sheet closed with nothing picked: the window comes back without having stopped.
        lifecycle(Lifecycle.State.STARTED)
        lifecycle(Lifecycle.State.RESUMED)
        assertEquals(text("friends.inviteTitle"), panel())
        lifecycle(Lifecycle.State.CREATED)
        assertNull(panel())
        assertNull(source.friends.snapshotFlow.value)
    }

    @Test
    fun eachOpeningMakesANewInvitation() {
        show()
        tap(FRIENDS_INVITE)
        press(FRIENDS_CLOSE)
        tap(FRIENDS_INVITE)
        assertEquals(2, requests(FriendsAction.CreateInvite).size)
        assertEquals("https://example.com/i/preview000000002", words(FRIENDS_INVITATION_URL).single())
    }

    @Test
    fun whileTheLinkIsMadeASpinnerStandsForItAndAFailureOffersRetry() {
        val acting = CompletableDeferred<Unit>()
        show {
            this.acting = acting
            actionFailure = DeliveryApiError.InvalidResponse()
        }
        tap(FRIENDS_INVITE)
        assertEquals(text("friends.link"), label(FRIENDS_LINK_LOADING))
        compose.onNodeWithTag(FRIENDS_CLOSE).assertIsNotEnabled()
        acting.complete(Unit)
        settle()
        assertFalse(exists(FRIENDS_LINK_LOADING))
        assertEquals(text("friends.actionFailed"), words(FRIENDS_ERROR).single())
        assertEquals(listOf(text("common.retry")), words(FRIENDS_INVITE_RETRY))
        client.actionFailure = null
        tap(FRIENDS_INVITE_RETRY)
        assertEquals(2, requests(FriendsAction.CreateInvite).size)
        assertFalse(exists(FRIENDS_ERROR))
        assertEquals(link, words(FRIENDS_INVITATION_URL).single())
    }

    @Test
    fun cancellingThisInvitationClosesThePanel() {
        show()
        tap(FRIENDS_INVITE)
        assertFalse(exists(FRIENDS_REVOKE))
        assertTrue(semantics(FRIENDS_MANAGE_LINKS).contains(SemanticsActions.Expand))
        tap(FRIENDS_MANAGE_LINKS)
        assertTrue(semantics(FRIENDS_MANAGE_LINKS).contains(SemanticsActions.Collapse))
        assertEquals(listOf(text("friends.revoke")), words(FRIENDS_REVOKE))
        // Without links out before this one, there are none to cancel.
        assertFalse(exists(FRIENDS_CANCEL_PREVIOUS))
        tap(FRIENDS_REVOKE)
        assertEquals("code-1", requests(FriendsAction.RevokeInvite).single().code)
        assertNull(panel())
    }

    @Test
    fun cancellingThePreviousLinksSaysSoAndOffersItNoMore() {
        show { previous = 2 }
        tap(FRIENDS_INVITE)
        tap(FRIENDS_MANAGE_LINKS)
        assertEquals(listOf(text("friends.cancelPrevious", mapOf("count" to 2))), words(FRIENDS_CANCEL_PREVIOUS))
        assertFalse(shows(text("friends.previousRevoked")))
        tap(FRIENDS_CANCEL_PREVIOUS)
        assertEquals("code-1", requests(FriendsAction.RevokePreviousInvites).single().code)
        assertTrue(shows(text("friends.previousRevoked")))
        assertFalse(exists(FRIENDS_CANCEL_PREVIOUS))
        assertEquals(text("friends.inviteTitle"), panel())
    }

    @Test
    fun theParcelReadsAsItsReplayAndPlaysAgainOnATap() {
        show()
        tap(FRIENDS_INVITE)
        assertEquals(text("friends.replayParcel"), label(FRIENDS_REPLAY))
        assertEquals(Role.Button, semantics(FRIENDS_REPLAY)[SemanticsProperties.Role])
        assertTrue(shows(text("friends.from")))
        assertTrue(shows(FriendFixtures.profile.nickname))
        tap(FRIENDS_REPLAY)
        assertEquals(text("friends.replayParcel"), label(FRIENDS_REPLAY))
    }

    /** Each flip of a sharing switch plays a light impact at 0.5 (C-H05). */
    @Test
    fun eachSharingSwitchFlippedPlaysALightImpact() {
        show()
        tap(FRIENDS_SHARING)
        felt()
        flip(FRIENDS_SHARE_STATS)
        assertEquals(light(0.5f), felt())
        flip(FRIENDS_SHARE_ARRIVAL)
        assertEquals(light(0.5f), felt())
        flip(FRIENDS_SHARE_STATS)
        assertEquals(light(0.5f), felt())
    }
}
