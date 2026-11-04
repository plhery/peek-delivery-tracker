package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.isActive
import com.plhery.peekdeliverytracker.core.isDelivered
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.READER
import com.plhery.peekdeliverytracker.ui.arrival.SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ACTIVE_COUNT
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ALL_ARRIVED
import com.plhery.peekdeliverytracker.ui.deliveries.ARCHIVE_KEY
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DEVICE_ACCOUNT_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.FOOT
import com.plhery.peekdeliverytracker.ui.deliveries.LIST_NOTICE
import com.plhery.peekdeliverytracker.ui.deliveries.LocalOnTheWayHeading
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.deliveries.cardKey
import com.plhery.peekdeliverytracker.ui.deliveries.sectionCount
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SIGN_IN
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ACCOUNT_DATA
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SIGN_OUT
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import java.time.Duration
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The deliveries list in each state the m7c list walk names, on this device's own window (A-ST01-A-ST04, A-ST07,
 * A-ST08, A-ST10, A-ST11, A-ST14-A-ST16, A-ST18, A-ST19, A-ST21, A-ST24, A-ST26): the launch screen blank, then saying
 * the app is opening, then the first screen; signed out, and the first open after signing out; the demo, which needs
 * no connection; a guest with nothing followed opening Add and cancelling it under the first open; a first load the
 * fakes hold; signed in with no parcels, and with only delivered ones; offline with the parcels the phone kept, and
 * the sign-in expired; the demo, a guest with parcels and that guest's Friends tab at the largest text, and German
 * there too; the archive's shelf opened, the past deliveries, the parcel shown beside the list where the window lays
 * them out side by side, and a long list scrolled to its foot and back. Each moment saved as
 * liststates/<row>-<moment>.png, with -dark, and -largest at the largest text, in the test package's external files,
 * which adb pulls. Signed in as the synthetic reader@example.com, or as a guest or the demo, on fake deliveries
 * ([com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries], [ParcelWalk]) whose stores answer every read in
 * memory: nothing goes to the network. Each test keeps Zurich's time and the captures' moment as the JVM's do, under
 * Reduce Motion; the largest text is the composition's own Density at font scale 2, Android's largest step, which the
 * app reads as iOS's accessibility2, not the phone's setting. The zone is put back after each test, and the
 * preferences files the fakes write go once its window has; the language is the composition's alone.
 */
@RunWith(AndroidJUnit4::class)
class ListStatesDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()

    /** "On the way" over the list, as last laid out. */
    @Volatile
    private var heading: TextLayoutResult? = null

    /** The keys of the list's items, in its order. */
    private fun keys(): List<Any> = walk.onUi { walk.deliveries.itemKeys }

    /** Whether something in the list, or the window where the list has no part of it, says `text`. */
    private fun says(text: String, within: String? = null): Boolean = compose.onAllNodes(
        if (within == null) hasText(text, substring = true) else hasText(text, substring = true) and hasAnyAncestor(hasTestTag(within)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun spinning(): Boolean =
        compose.onAllNodes(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The figure a count's pill shows. */
    private fun figure(tag: String): String = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).onFirst().fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.Text).orEmpty().joinToString("") { it.text }

    private fun noticeSays(text: String): Boolean = says(text, within = LIST_NOTICE)

    private fun inNotice(text: String): SemanticsMatcher = hasText(text) and hasAnyAncestor(hasTestTag(LIST_NOTICE))

    private val button = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)

    // A-ST01, A-ST02: the session.

    /**
     * A-ST01: the session restored as the app starts. The launch screen stays blank for its first 600 ms, then shows
     * Peek's mark, a spinner and "auth.loading"; signed in, the first screen is the list.
     */
    @Test
    fun theLaunchScreenStaysBlankThenSaysTheAppIsOpeningThenTheListComes() {
        compose.mainClock.autoAdvance = false
        walk.show(demo, session = AuthenticationState.Loading, until = { walk.exists(LAUNCH) }, settles = false)
        val start = walk.now()
        val loading = walk.text("auth.loading")
        walk.recompose()
        assertFalse(walk.shows(LAUNCH, loading))
        assertFalse(walk.spins(LAUNCH))
        assertFalse(walk.exists(PARCEL_LIST))
        walk.shot("A-ST01-launch-blank")
        walk.advanceTo(start + 500)
        walk.recompose()
        assertFalse(walk.shows(LAUNCH, loading))

        walk.advanceTo(start + 850)
        walk.recompose()
        assertTrue(walk.shows(LAUNCH, loading))
        assertTrue(walk.spins(LAUNCH))
        walk.shot("A-ST01-launch-loading")

        compose.mainClock.autoAdvance = true
        walk.session = READER
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_LIST) && !walk.exists(LAUNCH) }
        walk.settle()
        assertEquals("${demo.count { it.isActive }}", figure(ACTIVE_COUNT))
        walk.shot("A-ST01-first-screen")
    }

    /** A-ST02: signed out, the arrival's sign-in, which this debug build has unavailable, and no list. */
    @Test
    fun signedOutTheArrivalAsksToSignIn() {
        walk.show(emptyList(), session = AuthenticationState.SignedOut, until = { walk.exists(ARRIVAL) })
        assertTrue(walk.exists(SIGN_IN))
        assertTrue(says(walk.text("auth.configTitle"), within = ARRIVAL))
        assertFalse(walk.exists(PARCEL_LIST))
        assertFalse(walk.exists(FIRST_OPEN))
        walk.shot("A-ST02-signed-out")
    }

    /**
     * A-ST02: signing out from Settings' account page asks the store to sign out and closes Settings; the store then
     * welcomes a guest with nothing followed, as `signOut()` does, and the first open stands over the tabs.
     */
    @Test
    fun signingOutLeavesTheFirstOpen() {
        walk.show(demo)
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.awaitStill()
        walk.tap(SETTINGS_ACCOUNT_DATA)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SIGN_OUT) }
        walk.settle()
        walk.tap(SETTINGS_SIGN_OUT)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { "signOut" in walk.source.accountCalls && !walk.deliveries.showingAccount } }
        walk.settle()
        assertFalse(walk.exists(SETTINGS_SHEET))

        walk.onUi {
            walk.source.isGuest = true
            walk.source.parcels.value = emptyList()
        }
        walk.session = AuthenticationState.Welcome
        compose.waitUntil(LONG_MILLIS) { walk.exists(FIRST_OPEN) }
        walk.settle()
        assertTrue(walk.reaches(FIRST_OPEN_FIELD))
        walk.shot("A-ST02-welcome-after-sign-out")
    }

    // A-ST03, A-ST04, A-ST07, A-ST08, A-ST10, A-ST11: what the list has.

    /**
     * A-ST03: the demo's parcels are its own, read with no connection: no notice over them, its bar read as one
     * button ("demo, exit"), which welcomes the reader back.
     */
    @Test
    fun theDemoNeedsNoConnection() {
        walk.show(demo, session = AuthenticationState.Demo)
        walk.node(DEMO_EXIT).assertContentDescriptionEquals("${walk.text("app.demo")}, ${walk.text("native.exitDemo")}").assert(button)
        assertFalse(walk.exists(LIST_NOTICE))
        assertEquals("${demo.count { it.isActive }}", figure(ACTIVE_COUNT))
        walk.shot("A-ST03-demo")
        walk.tap(DEMO_EXIT)
        assertEquals(1, walk.onUi { walk.source.welcomes })
    }

    /** A-ST04: a guest with nothing followed opens Add from the first open's field and cancels; the first open stays. */
    @Test
    fun aGuestWithNoParcelsCancelsAddAndTheFirstOpenStays() {
        walk.show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }) { isGuest = true }
        assertTrue(walk.reaches(FIRST_OPEN_FIELD))
        walk.shot("A-ST04-first-open")

        walk.tap(FIRST_OPEN_FIELD)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.awaitKeyboard(required = false)
        walk.shot("A-ST04-add-open")

        walk.tap(ADD_CLOSE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        walk.awaitStill()
        assertTrue(walk.exists(FIRST_OPEN))
        assertTrue(walk.reaches(FIRST_OPEN_FIELD))
        assertTrue(walk.onUi { walk.source.parcels.value.isEmpty() })
        walk.shot("A-ST04-add-cancelled")
    }

    /**
     * A-ST07: the first load, the fakes holding the parcels back, shows the spinner and "app.opening" in place of the
     * list, without its empty state or its foot; the parcels come and take their place.
     */
    @Test
    fun aSlowFirstLoadSaysSoUntilTheParcelsCome() {
        walk.show(emptyList()) { loading.value = true }
        val opening = walk.text("app.opening")
        assertTrue(says(opening))
        assertTrue(spinning())
        assertFalse(walk.exists(EMPTY_PASTE))
        assertFalse(walk.exists(FOOT))
        assertEquals(emptyList<Any>(), keys())
        walk.shot("A-ST07-first-load")

        walk.onUi {
            walk.source.parcels.value = demo
            walk.source.loading.value = false
        }
        compose.waitUntil(LONG_MILLIS) { walk.exists(ACTIVE_COUNT) }
        walk.settle()
        assertFalse(says(opening))
        assertEquals("overview", keys().first())
        walk.shot("A-ST07-loaded")
    }

    /** A-ST08: signed in with no parcels, the empty state and the foot alone, and no first open. */
    @Test
    fun signedInWithNoParcels() {
        walk.show(emptyList())
        assertEquals(listOf<Any>("empty", "foot"), keys())
        for (key in listOf("peek.title", "app.emptyDescription", "landing.pasteAndTrack")) assertTrue(key, says(walk.text(key), within = PARCEL_LIST))
        assertTrue(walk.exists(EMPTY_PASTE))
        assertTrue(walk.exists(EMPTY_ADD))
        assertTrue(compose.onAllNodes(hasContentDescription(walk.text("landing.pip.open"))).fetchSemanticsNodes().isNotEmpty())
        assertFalse(walk.exists(FIRST_OPEN))
        assertFalse(walk.exists(ACTIVE_COUNT))
        walk.shot("A-ST08-no-parcels")
    }

    /** A-ST10: signed in with only delivered parcels, "everything has arrived" over the past deliveries. */
    @Test
    fun onlyDeliveredParcels() {
        walk.show(demo.filter { it.isDelivered })
        for (key in listOf("app.allArrived", "app.allArrivedDescription", "app.trackAnother")) {
            assertTrue(key, walk.shows(ALL_ARRIVED, walk.text(key)))
        }
        val keys = keys()
        assertTrue(keys.toString(), keys.indexOf("allArrived") in 0 until keys.indexOf("section-${ParcelSectionKind.Delivered.name}"))
        assertFalse(walk.exists(EMPTY_PASTE))
        walk.shot("A-ST10-all-arrived")
    }

    /**
     * A-ST11: offline, the notice under the overview says the service is out of reach, why, and that the parcels the
     * phone kept still show, and they do; Try again loads again. The sign-in expired asks to sign in again, which
     * loads again too.
     */
    @Test
    fun offlineTheListKeepsWhatThePhoneSavedAndSaysSo() {
        val offline = walk.text("error.connection")
        walk.show(demo) {
            errorMessage.value = offline
            usingCachedData.value = true
        }
        assertTrue(noticeSays(walk.text("app.trackingBreak")))
        assertTrue(noticeSays("$offline ${walk.text("app.cachedData")}"))
        assertEquals(listOf<Any>("overview", "notice"), keys().take(2))
        assertEquals("${demo.count { it.isActive }}", figure(ACTIVE_COUNT))
        walk.shot("A-ST11-offline-cached")
        walk.tap(inNotice(walk.text("app.tryAgain")) and button)
        assertEquals(1, walk.onUi { walk.source.reloads })

        val expired = walk.text("native.error.authenticationExpired")
        walk.onUi {
            walk.source.errorMessage.value = expired
            walk.source.authenticationRequired.value = true
            walk.source.usingCachedData.value = false
        }
        walk.settle()
        assertTrue(noticeSays(walk.text("app.signInNeeded")))
        assertTrue(noticeSays(expired))
        assertFalse(noticeSays(walk.text("app.trackingBreak")))
        walk.shot("A-ST11-sign-in-needed")
        walk.tap(inNotice(walk.text("app.signInAgain")) and button)
        assertEquals(2, walk.onUi { walk.source.reloads })
    }

    // A-ST14, A-ST15, A-ST16, A-ST24: the largest text.

    /** A-ST14: the demo's bar at the largest text, over the list, still one button that welcomes the reader back. */
    @Test
    fun theDemoAtTheLargestText() {
        walk.fontScale = 2f
        walk.show(demo, session = AuthenticationState.Demo)
        val exit = walk.node(DEMO_EXIT)
        exit.assertContentDescriptionEquals("${walk.text("app.demo")}, ${walk.text("native.exitDemo")}").assert(button)
        val bar = exit.getUnclippedBoundsInRoot()
        val count = compose.onNodeWithTag(ACTIVE_COUNT, useUnmergedTree = true).getUnclippedBoundsInRoot()
        assertTrue("The bar ends at ${bar.bottom}, the list's first row starts at ${count.top}", count.top >= bar.bottom)
        walk.shot("A-ST14-demo")
        walk.tap(DEMO_EXIT)
        assertEquals(1, walk.onUi { walk.source.welcomes })
    }

    /**
     * A-ST15: a guest's offer of an account at the largest text, after the cards on their way: its words, then its
     * button 10 under them, stacked as on iOS, read as one button that asks to sign in.
     */
    @Test
    fun aGuestWithParcelsAtTheLargestText() {
        walk.fontScale = 2f
        walk.show(demo, session = AuthenticationState.Welcome) { isGuest = true }
        val keys = keys()
        val offer = keys.indexOf("deviceAccount")
        assertTrue(keys.toString(), offer > 0)
        val before = keys[offer - 1].toString()
        assertTrue(before, cardId(before) != null && !before.startsWith("section-"))
        walk.node(PARCEL_LIST).performScrollToKey("deviceAccount")
        walk.settle()
        val title = walk.text("link.account.title")
        val kept = walk.text("native.guest.kept")
        val signIn = walk.text("arrival.signInTitle")
        assertEquals(listOf(title, kept, signIn), walk.texts(DEVICE_ACCOUNT_ROW))
        walk.node(DEVICE_ACCOUNT_ROW).assert(button)
        val words = inOffer(kept)
        val capsule = inOffer(signIn)
        assertTrue("The button's words at ${capsule.top}, the row's at ${words.bottom}", capsule.top - words.bottom >= 10.dp - TOLERANCE)
        walk.shot("A-ST15-guest-offer")
        walk.tap(DEVICE_ACCOUNT_ROW)
        assertEquals(1, walk.onUi { walk.source.signIns })
    }

    private fun inOffer(text: String) = compose.onAllNodes(hasText(text) and hasAnyAncestor(hasTestTag(DEVICE_ACCOUNT_ROW)), useUnmergedTree = true)
        .onFirst().getUnclippedBoundsInRoot()

    /** A-ST16: the guest's Friends tab at the largest text, what friends share and the button that asks to sign in. */
    @Test
    fun aGuestOnTheFriendsTabAtTheLargestText() {
        walk.fontScale = 2f
        walk.show(demo, session = AuthenticationState.Welcome) { isGuest = true }
        compose.onNode(hasContentDescription("Friends") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_SIGN_IN) }
        walk.settle()
        assertTrue(says(walk.text("friends.title")))
        assertTrue(says(walk.text("friends.introSummary")))
        assertTrue(walk.shows(FRIENDS_SIGN_IN, walk.text("arrival.signInTitle")))
        walk.shot("A-ST16-friends-signed-out")
        walk.tap(FRIENDS_SIGN_IN)
        assertEquals(1, walk.onUi { walk.source.signIns })
    }

    /**
     * A-ST24: German at the largest text, "On the way" over the list on its one line, whole, at its size or a little
     * smaller (`.lineLimit(1).minimumScaleFactor(0.75)`), never cut.
     */
    @Test
    fun germanAtTheLargestText() {
        walk.language = AppLanguage.De
        walk.fontScale = 2f
        walk.show(DemoCards.parcels(AppLanguage.De), around = { content ->
            CompositionLocalProvider(LocalOnTheWayHeading provides { heading = it }) { content() }
        })
        val layout = checkNotNull(heading)
        val words = walk.text("app.onTheWaySection")
        assertEquals(words, layout.layoutInput.text.text)
        assertEquals(1, layout.lineCount)
        assertEquals("Whole", words.length, layout.getLineEnd(0))
        assertFalse(layout.isLineEllipsized(0))
        assertFalse("Clipped: ${layout.multiParagraph.width} in ${layout.size.width}", layout.hasVisualOverflow)
        val full = PeekTypography(DynamicTypeSize.of(2f), Density(compose.density.density, 2f), AppLanguage.De).headline.fontSize.value
        val size = layout.layoutInput.style.fontSize.value
        assertTrue("$size of $full", size <= full + 0.01f && size >= full * 0.75f - 0.01f)
        walk.shot("A-ST24-german")
    }

    // A-ST18, A-ST19, A-ST21, A-ST26: the list's parts.

    /** A-ST18: the archive's shelf at the list's foot, closed, then opened from its header, its parcels listed. */
    @Test
    fun theArchiveShelfOpens() {
        walk.show(demo)
        val archived = DemoCards.archived().map { it.id }
        assertTrue(archived.isNotEmpty())
        walk.node(PARCEL_LIST).performScrollToKey(ARCHIVE_KEY)
        walk.settle()
        assertFalse(walk.onUi { walk.deliveries.archiveOpen })
        assertTrue(walk.onUi { walk.deliveries.rows }.none { it in archived })
        walk.shot("A-ST18-archive-closed")

        walk.openArchive()
        assertTrue(walk.onUi { walk.deliveries.archiveOpen })
        assertTrue("archive-open" in walk.onUi { walk.source.actions.toList() })
        assertTrue(walk.onUi { walk.deliveries.rows }.containsAll(archived))
        walk.node(PARCEL_LIST).performScrollToKey(ARCHIVE_KEY)
        walk.settle()
        walk.shot("A-ST18-archive-open")
    }

    /** A-ST19: the past deliveries under their header and count, after the cards on their way and before the shelf. */
    @Test
    fun pastDeliveries() {
        walk.show(demo)
        val past = walk.onUi { walk.deliveries.layout.sections }.first { it.kind == ParcelSectionKind.Delivered }.parcels
        assertTrue(past.isNotEmpty() && past.all { it.isDelivered })
        val header = "section-${ParcelSectionKind.Delivered.name}"
        walk.node(PARCEL_LIST).performScrollToKey(header)
        walk.settle()
        assertTrue(says(walk.text("app.pastDeliveries"), within = PARCEL_LIST))
        assertEquals("${past.size}", figure(sectionCount(ParcelSectionKind.Delivered)))
        val keys = keys()
        val at = keys.indexOf(header)
        assertEquals(past.map { cardKey(header, it.id) }, keys.subList(at + 1, at + 1 + past.size))
        assertTrue(keys.toString(), keys.indexOf(ARCHIVE_KEY) > at + past.size)
        assertEquals("foot", keys.last())
        walk.shot("A-ST19-past-deliveries")
    }

    /**
     * A-ST21: the parcel shown beside the list is its one selected card, where this window lays the list and the page
     * out side by side; where it stacks them, the page stands over the list and no card is selected. The shot says which.
     */
    @Test
    fun theParcelShownIsSelectedWhereTheListStandsBesideIt() {
        walk.show(demo)
        val lamp = DemoCards.parcel("Moon lamp")
        walk.open(lamp)
        if (!walk.stacked()) {
            assertEquals(lamp.id, walk.onUi { walk.deliveries.selectedCard })
            val selected = compose.onAllNodes(
                hasAnyAncestor(hasTestTag(PARCEL_LIST)) and hasClickAction() and SemanticsMatcher.expectValue(SemanticsProperties.Selected, true),
            ).fetchSemanticsNodes()
            assertEquals(1, selected.size)
            val label = selected.single().config.getOrNull(SemanticsProperties.ContentDescription).orEmpty().joinToString()
            assertTrue(label, lamp.label in label)
            walk.shot("A-ST21-side-by-side")
        } else {
            assertNull(walk.onUi { walk.deliveries.selectedCard })
            assertEquals(lamp.id, walk.shown())
            assertTrue(walk.exists(PARCEL_PAGE))
            walk.shot("A-ST21-stacked")
        }
    }

    /**
     * A-ST26: sixty parcels on their way, each its own card, the list stroked up to its foot and brought back to its
     * top, where the overview counts them.
     */
    @Test
    fun aLongListScrolledToItsFootAndBack() {
        val lamps = (1..20).map { syntheticLamp(it, due = it) } + (21..60).map { syntheticLamp(it) }
        walk.show(lamps)
        assertEquals(lamps.map { it.id }.toSet(), keys().mapNotNull(::cardId).toSet())
        assertEquals("foot", keys().last())
        val list = walk.node(PARCEL_LIST)
        var strokes = 0
        while (walk.onUi { walk.deliveries.listState.canScrollForward }) {
            assertTrue("Not at the foot after $strokes strokes", ++strokes <= MAX_STROKES)
            list.performTouchInput { swipeUp(startY = height * 0.65f, endY = height * 0.3f, durationMillis = 200) }
            compose.waitForIdle()
        }
        walk.settle()
        compose.onNodeWithTag(FOOT).assertIsDisplayed()
        walk.shot("A-ST26-foot")

        list.performScrollToIndex(0)
        walk.settle()
        assertEquals(0 to 0, walk.onUi { walk.deliveries.listState.let { it.firstVisibleItemIndex to it.firstVisibleItemScrollOffset } })
        assertEquals("${lamps.size}", figure(ACTIVE_COUNT))
        walk.shot("A-ST26-top")
    }

    private companion object {
        const val FOLDER = "liststates"

        /** The most strokes a long list takes to its foot on the smallest phone. */
        const val MAX_STROKES = 80

        val TOLERANCE: Dp = 0.5.dp
    }
}

/**
 * A synthetic parcel on its way, "Lamp `index`", added `index` minutes before the captures, due on the `due`th of
 * November where it has a day: the newest of those without a day comes after those with one.
 */
internal fun syntheticLamp(index: Int, due: Int? = null) = Parcel(
    id = UUID(0, 5000L + index), trackingNumber = "TESTLIST%04d".format(index), label = "Lamp $index", carrier = CarrierId.Unknown,
    createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofMinutes(index.toLong()))), syncStatus = SyncStatus.Ok,
    notificationsMuted = false, expectedDelivery = due?.let { "2026-11-%02d".format(it) },
)
