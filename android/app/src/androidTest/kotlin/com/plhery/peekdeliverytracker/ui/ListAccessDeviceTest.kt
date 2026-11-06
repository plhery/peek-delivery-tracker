package com.plhery.peekdeliverytracker.ui

import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.os.PowerManager
import android.provider.Settings
import android.view.WindowManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.ARCHIVE_KEY
import com.plhery.peekdeliverytracker.ui.deliveries.DEMO_EXIT
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_SAMPLE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_DEMO
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_PICTURE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_RIBBON
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.FOOT
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_BAR
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_COVER_MAP
import com.plhery.peekdeliverytracker.ui.deliveries.MAP_VIEWS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_GLOBE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.REFRESH_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_CLEAR
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.SEARCH_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COUNTRIES
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_POPOVER
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What TalkBack receives from the deliveries list and what stands over it (A-AX01 to A-AX24), read on this device's own
 * window from the accessibility tree a service is given ([AccessTree]), not from Compose's semantics: each card one
 * button read with its carrier's full name, the Next up card's hint, the archive in its actions; the undo toast one
 * button and the archive's shelf saying its count; a card selected only while its parcel is shown beside the list, which
 * depends on the window the app lays out side by side or one over the other ([ParcelWalk.stacked]); the top bar's
 * buttons by their names and ids, the search field taking the focus and losing it; the refresh button touch exploration
 * brings and what its check says through the live region; the demo bar, the foot, the empty list, the first open, the
 * passport and the map. Signed in as the synthetic reader@example.com, or in the demo, on fake deliveries whose stores
 * answer every read in memory. The app's following the phone's animations, contrast and blur settings is read from the
 * theme as MainActivity sets it up, the animations and contrast settings changed through the shell for a moment and put
 * back after. Each test keeps Zurich's time and the captures' moment; the preferences files the fakes write are put back.
 */
@RunWith(AndroidJUnit4::class)
class ListAccessDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)
    private val tree = AccessTree(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk).around(tree.logsTheWindowOnFailure)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val demo: List<Parcel> = DemoCards.parcels()

    /** The card of `parcel` among the list's nodes: a button read with its name, other than a toast saying it. */
    private fun card(nodes: List<AccessibilityNodeInfo>, parcel: Parcel): AccessibilityNodeInfo? =
        nodes.firstOrNull { it.isStop && it.viewIdResourceName != INLINE_TOAST && it.isButton && it.spoken.contains(parcel.label) }

    /** The list's nodes, read again. */
    private fun list(): List<AccessibilityNodeInfo> = tree.within(PARCEL_LIST)

    private fun fullName(parcel: Parcel): String = walk.identities.shown(parcel, walk.localizer).fullName

    /** The parcel of the list's Next up card. */
    private fun nextUp(): Parcel {
        val id = walk.onUi { walk.deliveries.itemKeys.firstOrNull { (it as? String)?.startsWith("next/") == true } }?.let(::cardId)
        return demo.first { it.id == checkNotNull(id) { "No Next up card" } }
    }

    /** The parcels with a compact card of their own, in the list's order. */
    private fun compactParcels(): List<Parcel> = walk.onUi { walk.deliveries.itemKeys.toList() }
        .filter { (it as? String)?.startsWith("next/") == false }
        .mapNotNull { key -> cardId(key)?.let { id -> demo.firstOrNull { it.id == id } } }

    /** The list scrolled to `parcel`'s card, once it has one, as the walk scrolls to it before a tap. */
    private fun scrollToCard(parcel: Parcel) {
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.itemKeys.any { cardId(it) == parcel.id } } }
        val key = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == parcel.id } }
        walk.node(PARCEL_LIST).performScrollToKey(key)
        walk.settle()
    }

    // A-AX06, A-AX07, A-AX08, A-AX16: the cards.

    /**
     * A-AX06, A-AX07, A-AX08, A-AX16: the Next up card is one button, nothing in it a stop of its own (its header, its
     * tick, its flights), read from its carrier's full name with "Next up" and its name, which a double tap opens with
     * "detail.label" said as what it does; a long press opens its menu and the actions menu offers "parcel.archive".
     * Each compact card is one button read the same way from its carrier's full name, its double tap said as a plain
     * activation, with the same archive action. Nothing in the list stops without saying something.
     */
    @Test
    fun eachCardIsOneButtonReadFromItsCarrierWithTheArchiveInItsActions() {
        walk.show(demo)
        val archive = walk.text("parcel.archive")
        val next = nextUp()
        val card = tree.await("The Next up card", read = ::list) { card(it, next) }
        assertEquals("Nothing in the Next up card is a stop of its own", 0, card.childCount)
        val label = card.spoken
        assertTrue(label, label.startsWith(fullName(next)))
        if (next.currentStage != TrackingStage.Delivered) assertTrue(label, label.contains(walk.text("app.nextUp")))
        assertEquals(walk.text("detail.label"), card.clickLabel)
        assertNotNull(card.action(AccessibilityNodeInfo.ACTION_LONG_CLICK))
        assertNotNull(card.actionNamed(archive))

        val shown = compactParcels().mapNotNull { parcel -> card(list(), parcel)?.let { parcel to it } }
        assertTrue("No compact card shown", shown.isNotEmpty())
        for ((parcel, compact) in shown) {
            assertEquals("Nothing in ${parcel.label}'s card is a stop of its own", 0, compact.childCount)
            assertTrue(compact.spoken, compact.spoken.startsWith(fullName(parcel)))
            assertNotNull(compact.action(AccessibilityNodeInfo.ACTION_CLICK))
            assertNull(compact.clickLabel)
            assertNotNull(compact.actionNamed(archive))
        }
        tree.assertNoBlankStops()
    }

    /**
     * A-AX08, A-AX21, A-AX14, A-AX07: a card archived from TalkBack's actions menu leaves the list, and the toast saying
     * so is one button read with its words and "app.undo", whose double tap undoes it: the card comes back. The archive's
     * shelf is one button read "Archived, <count>", its double tap said as showing the archive, then as hiding it once
     * shown; each archived row is one button read with its name.
     */
    @Test
    fun aCardArchivedFromItsActionsLeavesAnUndoButtonAndTheShelfSaysItsCount() {
        walk.show(demo)
        val parcel = compactParcels().first()
        scrollToCard(parcel)
        val card = tree.await("${parcel.label}'s card", read = ::list) { card(it, parcel) }
        val archive = checkNotNull(card.actionNamed(walk.text("parcel.archive"))) { "No archive action" }
        assertTrue(card.performAction(archive.id))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.onUi { parcel.id in walk.source.archived } }
        tree.awaitGone("${parcel.label}'s card", read = ::list) { card(it, parcel) == null }

        val undo = walk.text("app.undo")
        val toast = tree.awaitId(INLINE_TOAST)
        assertTrue(toast.isButton)
        assertTrue(toast.spoken, toast.spoken.endsWith(", $undo"))
        assertEquals(undo, toast.clickLabel)
        assertTrue("The toast is read as one", tree.oneElement(toast))
        assertTrue(toast.click())
        walk.settle()
        scrollToCard(parcel)
        tree.await("${parcel.label}'s card back", read = ::list) { card(it, parcel) }

        walk.node(PARCEL_LIST).performScrollToKey(ARCHIVE_KEY)
        walk.settle()
        val archived = DemoCards.archived()
        val shelfLabel = "${walk.text("app.archived")}, ${archived.size}"
        val shelf = tree.await("The archive's shelf", read = ::list) { nodes -> nodes.firstOrNull { it.isStop && it.spoken == shelfLabel } }
        assertTrue(shelf.isButton)
        assertEquals(0, shelf.childCount)
        assertEquals(walk.text("design.showArchive"), shelf.clickLabel)
        assertTrue(shelf.click())
        walk.settle()
        val open = tree.await("The open shelf", read = ::list) { nodes ->
            nodes.firstOrNull { it.isStop && it.clickLabel == walk.text("design.hideArchive") && it.spoken == shelfLabel }
        }
        assertTrue(open.isButton)
        val row = tree.await("${archived.first().label}'s row", read = ::list) { card(it, archived.first()) }
        assertEquals(0, row.childCount)
        assertNotNull(row.action(AccessibilityNodeInfo.ACTION_CLICK))
    }

    /**
     * A-AX06, A-AX07: a card is selected while its parcel is shown beside the list, and the archived row of a parcel
     * shown there too, the card before it no longer; where the window lays the page over the list, back on the list no
     * card is, as nothing is shown beside it.
     */
    @Test
    fun aCardIsSelectedOnlyWhileItsParcelIsShownBesideTheList() {
        walk.show(demo)
        val (first, second) = compactParcels()
        scrollToCard(first)
        assertFalse(checkNotNull(card(list(), first)).chosen)
        walk.open(first)
        if (walk.stacked()) {
            walk.home()
            scrollToCard(first)
            val card = tree.await("${first.label}'s card", read = ::list) { card(it, first) }
            assertFalse("Nothing is shown beside the list, yet ${first.label}'s card is selected", card.chosen)
            return
        }
        tree.await("${first.label}'s card selected", read = ::list) { nodes -> card(nodes, first)?.takeIf { it.chosen } }
        scrollToCard(second)
        assertFalse(checkNotNull(card(list(), second)).chosen)

        val archived = DemoCards.archived().first()
        walk.openArchive()
        walk.open(archived)
        tree.await("${archived.label}'s row selected", read = ::list) { nodes -> card(nodes, archived)?.takeIf { it.chosen } }
        scrollToCard(first)
        tree.await("${first.label}'s card no longer selected", read = ::list) { nodes -> card(nodes, first)?.takeUnless { it.chosen } }
    }

    // A-AX11, A-AX20, A-AX24: the top bar and the search.

    /**
     * A-AX11, A-AX20, A-AX24, A-AX16: the top bar's buttons are read by their names (Add as "app.addParcelAria", the
     * account, sort and filter, search), each a button found by its id. Search opened from TalkBack shows its field with
     * the focus, read "view.search" with its magnifier and placeholder silent; with words in it a clear button "view.clear"
     * shows, and the search button reads "view.hideSearch". Sort and filter takes the focus from the field before its
     * sheet or popover opens; search closed leaves no field holding the focus.
     */
    @Test
    fun theTopBarIsReadByNameAndTheSearchFieldTakesTheFocusAndGivesItBack() {
        walk.show(demo)
        val add = tree.awaitId(ADD_BUTTON)
        assertEquals(walk.text("app.addParcelAria"), add.spoken)
        assertTrue(add.isButton)
        val account = checkNotNull(tree.byId(ACCOUNT_BUTTON))
        assertEquals(walk.text("native.account"), account.spoken)
        assertTrue(account.isButton)
        val filters = checkNotNull(tree.byId(FILTERS_BUTTON))
        assertEquals(walk.text("view.sortFilter"), filters.spoken)
        assertNull(filters.state)
        val search = checkNotNull(tree.byId(SEARCH_BUTTON))
        assertEquals(walk.text("view.search"), search.spoken)
        assertTrue(search.isButton)
        tree.assertNoBlankStops()

        assertTrue(search.click())
        walk.settle()
        val field = tree.await("The focused search field") { nodes -> nodes.firstOrNull { it.viewIdResourceName == SEARCH_FIELD && it.isFocused } }
        assertEquals(walk.text("view.search"), field.spoken)
        assertTrue(field.isEditable)
        tree.assertNoBlankStops(tree.within(SEARCH_ROW))
        assertTrue(
            field.performAction(
                AccessibilityNodeInfo.ACTION_SET_TEXT,
                Bundle().apply { putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "lamp") },
            ),
        )
        walk.settle()
        val clear = tree.awaitId(SEARCH_CLEAR)
        assertEquals(walk.text("view.clear"), clear.spoken)
        assertTrue(clear.isButton)
        assertEquals(walk.text("view.hideSearch"), checkNotNull(tree.byId(SEARCH_BUTTON)).spoken)

        assertTrue(checkNotNull(tree.byId(FILTERS_BUTTON)).click())
        walk.settle()
        tree.await("The filters") { nodes -> nodes.firstOrNull { it.viewIdResourceName == FILTERS_SHEET || it.viewIdResourceName == FILTERS_POPOVER } }
        assertFalse("The field kept the focus as the filters opened", tree.byId(SEARCH_FIELD)?.isFocused == true)
        assertFalse("The field kept the focus as the filters opened", walk.onUi { walk.deliveries.searchFocused })
        walk.back()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_SHEET) && !walk.exists(FILTERS_POPOVER) }

        assertTrue(tree.awaitId(SEARCH_BUTTON).click())
        walk.settle()
        tree.awaitGone("The search field") { nodes -> nodes.none { it.viewIdResourceName == SEARCH_FIELD } }
        assertTrue("A field keeps the focus with search closed", tree.nodes().none { it.isEditable && it.isFocused })
    }

    // A-AX09, A-AX10, A-AX21: the refresh button.

    /**
     * A-AX10, A-AX09, A-AX21, A-AX20: a list that stands alone has no refresh button until touch exploration comes on,
     * which brings it at once; beside a page it is there either way. It is the button "app.refresh", found by its id,
     * then "app.refreshing" while the parcels are checked; the check's result is said through the window's polite live
     * region and left under the list in a toast read as one, without a button.
     */
    @Test
    fun touchExplorationBringsTheRefreshButtonAndItsCheckIsSaid() {
        walk.show(demo)
        if (walk.stacked() && !touchExploration()) assertNull("A phone's list has a refresh button without touch exploration", tree.byId(REFRESH_BUTTON))
        compose.exploringByTouch {
            assumeTrue("Touch exploration stayed off and the list stands alone: no refresh button", !walk.stacked() || touchExploration())
            val refresh = tree.awaitId(REFRESH_BUTTON)
            assertEquals(walk.text("app.refresh"), refresh.spoken)
            assertTrue(refresh.isButton)
            val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
            walk.onUi { walk.source.checking = checked }
            assertTrue(refresh.click())
            tree.holdingTheClock {
                tree.await("The refresh button checking", read = tree::framed) { nodes ->
                    nodes.firstOrNull { it.viewIdResourceName == REFRESH_BUTTON && it.spoken == walk.text("app.refreshing") }
                }
                checked.complete(ParcelStore.RefreshResult.Unchanged)
                val unchanged = walk.text("app.refreshUnchanged")
                tree.awaitSaid(ANNOUNCEMENT, unchanged)
                val toast = checkNotNull(tree.byId(INLINE_TOAST))
                assertEquals(unchanged, toast.spoken)
                assertFalse(toast.isButton)
                assertEquals(0, toast.childCount)
                assertEquals(walk.text("app.refresh"), checkNotNull(tree.byId(REFRESH_BUTTON)).spoken)
            }
        }
    }

    // A-AX03, A-AX12, A-AX13, A-AX20: the demo bar and the foot.

    /**
     * A-AX12, A-AX20, A-AX03: in the demo the bar over the list is one button read "app.demo, native.exitDemo" and found
     * as demo.exit, at the default text size and at the largest; its words drawn inside add nothing to that and are no
     * stops of their own.
     */
    @Test
    fun theDemoBarIsOneButtonNamedDemoExitAtEveryTextSize() {
        walk.show(demo, session = AuthenticationState.Demo)
        val label = "${walk.text("app.demo")}, ${walk.text("native.exitDemo")}"
        for (scale in listOf(1f, 2f)) {
            walk.fontScale = scale
            walk.recompose()
            walk.settle()
            val bar = tree.await("The demo bar at $scale") { nodes -> nodes.firstOrNull { it.viewIdResourceName == DEMO_EXIT && it.spoken == label } }
            assertTrue(bar.isButton)
            assertTrue("The demo bar is read as one at $scale", tree.oneElement(bar))
            assertTrue("Something under the demo bar is a stop of its own at $scale", tree.under(bar).drop(1).none { it.isScreenReaderFocusable })
        }
    }

    /**
     * A-AX13, A-AX20, A-AX16: the list's foot, found as deliveries.foot, reads the app's name and tagline as one stop,
     * its mark adding nothing, then its three links, each one button found by its id and read by its words alone.
     */
    @Test
    fun theFootReadsItsNameOnceThenThreeLinksByTheirIds() {
        walk.show(demo)
        walk.node(PARCEL_LIST).performScrollToKey("foot")
        walk.settle()
        val foot = tree.await("The foot", read = { tree.within(FOOT) }) { it.takeIf(List<AccessibilityNodeInfo>::isNotEmpty) }
        val name = "${walk.text("app.title")} · ${walk.text("native.tagline")}"
        val row = checkNotNull(foot.firstOrNull { it.isScreenReaderFocusable && !it.isClickable && it.spoken == name }) {
            "No stop of the foot reads \"$name\" alone\n${tree.dump(foot)}"
        }
        assertTrue("The foot's name is read as one", tree.oneElement(row))
        for ((id, words) in listOf("home" to walk.text("app.homePage"), "privacy" to walk.text("auth.privacyLink"), "source" to "GitHub")) {
            val link = foot.first { it.viewIdResourceName == "$FOOT.$id" }
            assertTrue(link.isButton)
            assertTrue("The $id link is read as one", tree.oneElement(link))
            assertEquals(words, link.spoken)
        }
    }

    // A-AX17, A-AX20: the empty list and the first open.

    /**
     * A-AX20, A-AX17, A-AX16: signed in with no parcels, Pip's parcel is the button deliveries.empty.sample read
     * "landing.pip.open" alone, the drawing in it adding nothing, under it the app's name a heading, and paste and add
     * are buttons found by their ids and read by their words. Nothing stops without saying something.
     */
    @Test
    fun anEmptyListOffersTheSampleAndItsWaysInByTheirIds() {
        walk.show(emptyList())
        val sample = tree.awaitId(EMPTY_SAMPLE)
        assertEquals(walk.text("landing.pip.open"), sample.spoken)
        assertTrue(sample.isButton)
        assertTrue(tree.nodes().any { it.isHeading && it.spoken == walk.text("peek.title") })
        for (tag in listOf(EMPTY_PASTE, EMPTY_ADD)) {
            compose.reveal(tag)
            val button = tree.awaitId(tag)
            assertTrue(button.isButton)
            assertTrue(tree.oneElement(button))
            assertTrue(button.spoken.isNotEmpty())
        }
        tree.assertNoBlankStops()
    }

    /**
     * A-AX17, A-AX20, A-AX16: while the first open covers the list neither the list nor the tab bar is in the tree. Its
     * question is a heading; the field, found as welcome.field, is read with its placeholder then "add.tracking"; scan is
     * "add.scan"; Pip's picture opens the sample as "landing.pip.open"; the carriers' ribbon is one element naming the
     * first five; paste, the demo and sign in are found by their ids. Nothing stops without saying something.
     */
    @Test
    fun theFirstOpenHidesTheListAndNamesItsQuestionFieldAndRibbon() {
        walk.show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }) { isGuest = true }
        val nodes = tree.await("The first open's field") { nodes -> nodes.takeIf { all -> all.any { it.viewIdResourceName == FIRST_OPEN_FIELD } } }
        assertTrue("The list is in the tree under the first open", nodes.none { it.viewIdResourceName == PARCEL_LIST })
        assertTrue("The tab bar is in the tree under the first open", nodes.none { it.viewIdResourceName == TAB_BAR })
        assertTrue(nodes.any { it.isHeading && it.spoken == walk.text("peek.title") })
        val field = nodes.first { it.viewIdResourceName == FIRST_OPEN_FIELD }
        assertTrue(field.isButton)
        assertEquals("${walk.text("add.trackingPlaceholder")}, ${walk.text("add.tracking")}", field.spoken)
        nodes.firstOrNull { it.viewIdResourceName == FIRST_OPEN_SCAN }?.let { scan ->
            assertEquals(walk.text("add.scan"), scan.spoken)
            assertTrue(scan.isButton)
        }
        val picture = nodes.first { it.viewIdResourceName == FIRST_OPEN_PICTURE }
        assertEquals(walk.text("landing.pip.open"), picture.spoken)
        assertTrue(picture.isButton)
        compose.reveal(FIRST_OPEN_RIBBON)
        val ribbon = tree.awaitId(FIRST_OPEN_RIBBON)
        assertEquals(0, ribbon.childCount)
        val label = ribbon.spoken
        val (before, after) = walk.text("landing.ribbon.label", "carriers" to MARK).split(MARK)
        assertTrue(label, label.startsWith(before) && label.endsWith(after))
        assertEquals(label, 5, label.removePrefix(before).removeSuffix(after).split(", ").size)
        for (tag in listOf(FIRST_OPEN_PASTE, FIRST_OPEN_DEMO, FIRST_OPEN_SIGN_IN).filter(walk::exists)) {
            compose.reveal(tag)
            assertTrue(tree.awaitId(tag).isClickable)
        }
        tree.assertNoBlankStops()
    }

    // A-AX18: the passport.

    /**
     * A-AX18: the passport's cover is one button read with its count, its state collapsed and its double tap said as
     * showing the details. Shown, the popover is all TalkBack reaches, the passport under it out of the tree, its title
     * a heading and the pane named after it; once it closes the cover is collapsed again. The countries' title reads its
     * state and hint the same way, and each country is one element read with its parcels.
     */
    @Test
    fun thePassportsCoverAndCountriesReadTheirStateAndWhatATapDoes() {
        walk.show(demo, session = AuthenticationState.Demo)
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PASSPORT_COVER) }
        walk.settle()
        val collapsed = walk.text("passport.expansionState.collapsed")
        val details = walk.text("passport.detailsHint")
        val cover = tree.awaitId(PASSPORT_COVER)
        assertTrue(cover.isButton)
        assertEquals(0, cover.childCount)
        assertTrue(cover.spoken.isNotEmpty())
        assertEquals(collapsed, cover.state)
        assertEquals(details, cover.clickLabel)
        assertTrue(cover.click())
        walk.settle()
        val bubble = tree.await("The popover", read = { tree.within(PASSPORT_POPOVER) }) { it.takeIf(List<AccessibilityNodeInfo>::isNotEmpty) }
        val title = checkNotNull(bubble.firstOrNull { it.isHeading }) { "The popover's title is no heading\n${tree.dump(bubble)}" }.spoken
        val nodes = tree.nodes()
        assertTrue("The passport stays in the tree under its popover", nodes.none { it.viewIdResourceName == PASSPORT_COVER })
        assertTrue("No pane is named ${title}", nodes.any { it.paneTitle?.toString() == title })
        walk.back()
        tree.await("The cover collapsed again") { all -> all.firstOrNull { it.viewIdResourceName == PASSPORT_COVER && it.state == collapsed } }

        compose.reveal(PASSPORT_COUNTRIES)
        val countries = tree.awaitId(PASSPORT_COUNTRIES)
        assertTrue(countries.isButton)
        assertEquals(0, countries.childCount)
        assertEquals(walk.text("passport.firstSeenIn"), countries.spoken)
        assertEquals(collapsed, countries.state)
        assertEquals(details, countries.clickLabel)
        val parcels = Regex(".+, (${walk.pattern("passport.parcels.one", "count").pattern}|${walk.pattern("passport.parcels.many", "count").pattern})")
        val country = SemanticsMatcher("A country read with its parcels") { node ->
            node.config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()?.let(parcels::matches) == true
        }
        compose.reveal(compose.onAllNodes(country).onFirst())
        val row = tree.await("A country read with its parcels") { all -> all.firstOrNull { parcels.matches(it.words) } }
        assertEquals(0, row.childCount)
        assertTrue(row.isScreenReaderFocusable)
    }

    // A-AX19: the map.

    /** `parcel`'s map, opened from the globe on its page, its summary in. */
    private fun openMap(parcel: Parcel) {
        walk.open(parcel)
        walk.tap(PAGE_GLOBE)
        compose.waitUntil(LONG_MILLIS) { walk.exists(MAP_COVER_MAP) && walk.exists(MAP_BAR) }
        walk.settle()
    }

    /**
     * A-AX19: a parcel's map is one image read with its journey, "map.label" from one place to another, and each end of
     * the journey one element read with its title, place and country; its close button is "map.close". Its views are
     * buttons read by their names, the first after "map.view", the group's name, and the view shown selected. Where the
     * journey keeps to one place the map is read "map.labelOne", with no views.
     */
    @Test
    fun theMapIsOneImageReadWithItsJourneyAndItsViewsSaySelected() {
        walk.show(demo)
        openMap(DemoCards.parcel("Moon lamp"))
        val map = tree.awaitId(MAP_COVER_MAP)
        assertEquals("android.widget.ImageView", map.className)
        assertEquals(0, map.childCount)
        assertTrue(map.spoken, walk.pattern("map.label", "from", "to").matches(map.spoken))
        val bar = tree.within(MAP_BAR)
        for (end in listOf("From, Berlin, Germany", "Now, Neuenstein, Germany")) {
            val node = checkNotNull(bar.firstOrNull { it.isScreenReaderFocusable && it.spoken == end }) { "No end read \"$end\"\n${tree.dump(bar)}" }
            assertEquals(0, node.childCount)
        }
        val views = tree.within(MAP_VIEWS).filter { it.isClickable }
        assertEquals(listOf("${walk.text("map.view")}, ${walk.text("map.journey")}", walk.text("map.nearby")), views.map { it.spoken })
        assertTrue(views.all { it.isButton })
        assertEquals(1, views.count { it.chosen })
        val close = checkNotNull(tree.byId(MAP_CLOSE))
        assertEquals(walk.text("map.close"), close.spoken)
        assertTrue(close.click())
        compose.waitUntil(LONG_MILLIS) { !walk.exists(MAP_COVER_MAP) }
        walk.settle()

        openMap(DemoCards.parcel("Trail weekend kit"))
        val one = tree.awaitId(MAP_COVER_MAP)
        assertTrue(one.spoken, walk.pattern("map.labelOne", "place").matches(one.spoken))
        assertNull(tree.byId(MAP_VIEWS))
    }

    // A-AX01, A-AX02, A-AX23 (and B-AX18, C-AX15): the phone's settings.

    @Volatile private var reducesMotion: Boolean? = null

    @Volatile private var highContrast: Boolean? = null

    @Volatile private var reducesTransparency: Boolean? = null

    /**
     * A-AX01, A-AX02, A-AX23, B-AX18, C-AX15: the theme as MainActivity sets it up follows the phone. Reduce Transparency
     * is the window's cross-window blur being off, away from the battery saver; Reduce Motion is the animator duration
     * scale at 0 (which Remove animations sets), read again as the shell changes it and changes it back; Increase
     * Contrast is the system's contrast above standard from Android 14, read again as the shell changes it, and never
     * below Android 14.
     */
    @Test
    fun theThemeFollowsThePhonesAnimationsContrastAndBlur() {
        val context = instrumentation.targetContext
        compose.setContent {
            PeekTheme(carrierIdentities = null) {
                reducesMotion = PeekTheme.reduceMotion
                highContrast = PeekTheme.colors.highContrast
                reducesTransparency = PeekTheme.reduceTransparency
            }
        }
        compose.waitUntil(LONG_MILLIS) { reducesMotion != null }

        val power = context.getSystemService(PowerManager::class.java)
        if (!power.isPowerSaveMode) {
            val blurs = context.getSystemService(WindowManager::class.java).isCrossWindowBlurEnabled
            compose.waitUntil(LONG_MILLIS) { reducesTransparency == !blurs }
        }

        val resolver = context.contentResolver
        val animationsOff = { Settings.Global.getFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f }
        val wereOff = animationsOff()
        assertEquals(wereOff, reducesMotion)
        val scale = shell("settings get global ${Settings.Global.ANIMATOR_DURATION_SCALE}")
        try {
            shell("settings put global ${Settings.Global.ANIMATOR_DURATION_SCALE} ${if (wereOff) "1" else "0"}")
            compose.waitUntil(LONG_MILLIS) { animationsOff() != wereOff }
            compose.waitUntil(LONG_MILLIS) { reducesMotion == !wereOff }
        } finally {
            restore("global", Settings.Global.ANIMATOR_DURATION_SCALE, scale)
        }
        compose.waitUntil(LONG_MILLIS) { reducesMotion == wereOff }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            assertEquals(false, highContrast)
            return
        }
        val modes = context.getSystemService(UiModeManager::class.java)
        val wasHigh = modes.contrast > 0f
        assertEquals(wasHigh, highContrast)
        val level = shell("settings get secure $CONTRAST_LEVEL")
        try {
            shell("settings put secure $CONTRAST_LEVEL ${if (wasHigh) "0.0" else "0.5"}")
            val taken = runCatching { compose.waitUntil(LONG_MILLIS) { (modes.contrast > 0f) != wasHigh } }.isSuccess
            assumeTrue("The system didn't take the contrast level the shell set", taken)
            compose.waitUntil(LONG_MILLIS) { highContrast == !wasHigh }
        } finally {
            restore("secure", CONTRAST_LEVEL, level)
        }
        compose.waitUntil(LONG_MILLIS) { highContrast == wasHigh }
    }

    /** What the shell prints for `command`, run as the test's instrumentation runs it. */
    private fun shell(command: String): String =
        ParcelFileDescriptor.AutoCloseInputStream(instrumentation.uiAutomation.executeShellCommand(command)).use { it.readBytes().decodeToString().trim() }

    /** The setting `name` in `table` put back to `value`, as `settings get` printed it ("null" where it was unset). */
    private fun restore(table: String, name: String, value: String) {
        shell(if (value == "null" || value.isEmpty()) "settings delete $table $name" else "settings put $table $name $value")
    }

    private companion object {
        /** A placeholder no copy holds, standing for the carriers while the ribbon's words are split about them. */
        const val MARK = "\u0001"

        /** `Settings.Secure.CONTRAST_LEVEL`, which the SDK keeps hidden: what UiModeManager's contrast reads. */
        const val CONTRAST_LEVEL = "contrast_level"
    }
}
