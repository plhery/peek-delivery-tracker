package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Parcel
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SIGN_IN_PILL
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SIGN_IN_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_ARROW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_REFRESH
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Where a finger acts on a button whose iPhone hit area is narrower than the box Compose lays it out in, on this
 * device's own window. The account button takes a finger in its circle 44 across (AccountToolbarButton's
 * `.frame(width: 44, height: 44).contentShape(Circle())`) and the sync line's refresh in its circle 26 across
 * (ParcelDetailView's `.frame(width: 26, height: 26).contentShape(Circle())`): a finger in a corner of either's square,
 * outside the circle, does nothing, though Compose still widens a small button's touch to 48 outside its square. A
 * SwiftUI `Button` takes a finger on its label alone where its frame, padding and background are laid outside it: in a
 * link's sheet, "link.signInToAdd" under following (`.frame(maxWidth: .infinity, minHeight: 44)`) and, on the first
 * open, the masthead's Sign in on its pill of paper (`.padding(.horizontal, 14).frame(minHeight: 44).background(…)`); a
 * finger beside the words, in the row or on the pill, does nothing, one on the words acts once. A sheet's carrier link
 * reads as its words alone, its arrow hidden from TalkBack (`.accessibilityHidden(true)`). The touches are the screen's
 * own, sent through UiAutomation ([ScreenTouches]) at points worked out from the bounds of what each test finds by its
 * tag, so that a small phone, a foldable folded or open and a tablet each place them. Signed in as the synthetic
 * reader@example.com, or signed out, on fake deliveries and links whose stores answer every read in memory. Shots in
 * touchareas/<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class TouchAreasDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touches = ScreenTouches(compose)
    private val tree = AccessTree(compose)

    @get:Rule
    val rules: RuleChain =
        RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk).around(tree.logsTheWindowOnFailure)

    /** A parcel its carrier answers for, whose page has a sync line with its refresh. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    private fun px(dp: Dp): Float = with(compose.density) { dp.toPx() }

    /** Only what is drawn: the first open measures copies of its content that it never places. */
    private val drawnOnly = SemanticsMatcher("is drawn") { it.layoutInfo.isPlaced && it.size.width > 0 && it.size.height > 0 }

    private fun tagged(tag: String): SemanticsMatcher = hasTestTag(tag) and drawnOnly

    /**
     * Where what has `tag` is drawn on the screen, in pixels: the box of its modifiers from the tag in, as the scales
     * round it leave it (a floating sheet draws what it holds smaller than it lays it out).
     */
    private fun drawn(tag: String): Rect {
        val node = touches.node(tagged(tag))
        return walk.onUi { node.boundsInWindow }.translate(touches.origin())
    }

    /** The window on the screen less the system's bars, the cutout and the keyboard: where a finger reaches the app. */
    private fun clear(): Rect {
        val inWindow = walk.onUi {
            val decor = compose.activity.window.decorView
            val insets = ViewCompat.getRootWindowInsets(decor)
                ?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime())
                ?: Insets.NONE
            Rect(insets.left.toFloat(), insets.top.toFloat(), (decor.width - insets.right).toFloat(), (decor.height - insets.bottom).toFloat())
        }
        return inWindow.translate(touches.origin())
    }

    private fun within(box: Rect, area: Rect): Boolean =
        box.left >= area.left - 1f && box.top >= area.top - 1f && box.right <= area.right + 1f && box.bottom <= area.bottom + 1f

    private fun within(point: Offset, area: Rect): Boolean =
        point.x >= area.left && point.y >= area.top && point.x <= area.right && point.y <= area.bottom

    /**
     * What has `tag`, scrolled into the window clear of the tab bar ([reveal]), then on, by the innermost scroll that
     * holds it, until it stands inside that scroll's viewport and clear of the system's bars; then asserted drawn whole
     * there, nothing clipping it. Its box on the screen.
     */
    private fun revealClear(tag: String): Rect {
        compose.reveal(tag)
        walk.settle()
        val scrolls = compose.onAllNodes(hasScrollAction() and hasAnyDescendant(tagged(tag)), useUnmergedTree = true)
        if (scrolls.fetchSemanticsNodes().isNotEmpty()) {
            val viewport = scrolls.onLast().fetchSemanticsNode().boundsInWindow.translate(touches.origin())
            val room = clear().intersect(viewport).deflate(px(CLEAR_MARGIN))
            val box = drawn(tag)
            val shift = when {
                box.bottom > room.bottom -> box.bottom - room.bottom
                box.top < room.top -> box.top - room.top
                else -> 0f
            }
            if (shift != 0f) {
                scrolls.onLast().performSemanticsAction(SemanticsActions.ScrollBy) { scroll -> scroll(0f, shift) }
                walk.settle()
            }
        }
        walk.awaitStill()
        val node = touches.node(tagged(tag))
        val (clipped, whole) = walk.onUi {
            val coordinates = node.layoutInfo.coordinates
            val root = coordinates.findRootCoordinates()
            root.localBoundingBoxOf(coordinates, clipBounds = true) to root.localBoundingBoxOf(coordinates, clipBounds = false)
        }
        assertTrue("$tag drawn whole: $clipped of $whole", abs(clipped.width - whole.width) <= 1f && abs(clipped.height - whole.height) <= 1f)
        val box = drawn(tag)
        val clear = clear()
        assertTrue("$tag at $box within the window's clear area $clear", within(box, clear))
        return box
    }

    /**
     * The four points `inset` in from each corner of `box`, a square, each asserted outside the circle the square holds
     * by a dp at least and where a finger reaches the app.
     */
    private fun cornersOutsideTheCircle(box: Rect, inset: Dp): List<Offset> {
        val radius = min(box.width, box.height) / 2
        val clear = clear()
        val corners = listOf(
            Offset(box.left + px(inset), box.top + px(inset)),
            Offset(box.right - px(inset), box.top + px(inset)),
            Offset(box.left + px(inset), box.bottom - px(inset)),
            Offset(box.right - px(inset), box.bottom - px(inset)),
        )
        for (corner in corners) {
            assertTrue("$corner outside the circle of $box", (corner - box.center).getDistance() >= radius + px(1.dp))
            assertTrue("$corner within the window's clear area $clear", within(corner, clear))
        }
        return corners
    }

    /**
     * A point in `row` beside `words` (both on the screen), level with the words' middle, halfway between the row's
     * edge and the reach of the words' touch on the side with more room: Compose widens a node narrower than 48 to
     * 48, which a finger there stays clear of. Asserts there is room for a finger to miss the words.
     */
    private fun besideTheWords(row: Rect, words: Rect): Offset {
        val widening = max(0f, (px(TOUCH_SIZE) - words.width) / 2)
        val before = words.left - widening - row.left
        val after = row.right - (words.right + widening)
        val room = max(before, after)
        assertTrue("Room beside the words $words in $row: ${room / compose.density.density} dp", room >= px(ROOM_BESIDE))
        val x = if (before >= after) row.left + before / 2 else row.right - after / 2
        return Offset(x, words.center.y)
    }

    /**
     * The account button takes a finger in its circle 44 across and none in the corners of its square (`AccountToolbar
     * Button`, `.frame(width: 44, height: 44).contentShape(Circle())`): a tap 3 in from each corner of the square, 5
     * outside the circle, opens nothing (Settings stays closed, no parcel or Add opens, nothing is asked of the system);
     * a tap at its middle opens Settings. On a phone it stands in the list's bar, beside a parcel on the page's glass.
     */
    @Test
    fun theAccountButtonTakesAFingerInItsCircleAndNoneInTheCornersOfItsSquare() {
        walk.show(listOf(gift, lamp))
        walk.awaitStill()
        val page = walk.shown()
        val box = drawn(ACCOUNT_BUTTON)
        assertEquals("The account's square", px(ACCOUNT_SIDE), box.width, PIXEL)
        assertEquals("The account's square", px(ACCOUNT_SIDE), box.height, PIXEL)
        assertTrue("The account at $box within the window's clear area ${clear()}", within(box, clear()))

        for (corner in cornersOutsideTheCircle(box, ACCOUNT_CORNER_IN)) {
            touches.tap(corner)
            walk.settle()
            assertFalse("A tap at $corner, in a corner of the account's square at $box, opens nothing", walk.onUi { walk.deliveries.showingAccount })
        }
        assertEquals("No parcel opened", page, walk.shown())
        assertNull("No Add opened", walk.onUi { walk.deliveries.addition })
        assertTrue("Nothing asked of the system: ${walk.asked()}", walk.asked().isEmpty())
        walk.shot("account-corners", alsoDark = false)

        touches.tap(box.center)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.showingAccount } }
        walk.settle()
    }

    /**
     * The sync line's refresh takes a finger in its circle 26 across and none in the corners of its square
     * (ParcelDetailView's `.frame(width: 26, height: 26).contentShape(Circle())`): a tap 1.5 in from each corner of the
     * square, 3 outside the circle, checks nothing; a tap at its middle checks the parcel once.
     */
    @Test
    fun theSyncLinesRefreshTakesNoFingerInTheCornersOfItsSquare() {
        val gift = gift
        walk.show(listOf(gift, lamp))
        walk.open(gift)
        val box = revealClear(PAGE_REFRESH)
        assertEquals("The refresh's square", px(REFRESH_SIDE), box.width, PIXEL)
        assertEquals("The refresh's square", px(REFRESH_SIDE), box.height, PIXEL)

        for (corner in cornersOutsideTheCircle(box, REFRESH_CORNER_IN)) {
            touches.tap(corner)
            walk.settle()
            assertTrue("A tap at $corner, in a corner of the refresh's square at $box, checks nothing", walk.onUi { walk.source.checked.isEmpty() })
        }
        assertEquals("The page still shows", gift.id, walk.shown())
        walk.shot("refresh-corners", alsoDark = false)

        touches.tap(box.center)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.checked.isNotEmpty() } }
        walk.settle()
        assertEquals("Checked once", listOf(gift.id), walk.onUi { walk.source.checked.toList() })
    }

    /**
     * In a link's sheet seen signed out, "link.signInToAdd" under following takes a finger on its words alone, as a
     * SwiftUI `Button` with its `.frame(maxWidth: .infinity, minHeight: 44)` outside it: its row runs across the sheet,
     * and a tap in the row beside the words signs nothing in and leaves the sheet up; a tap on the words asks to sign
     * in once.
     */
    @Test
    fun inALinksSheetSigningInTakesAFingerOnItsWordsAlone() {
        val links = openSheet(LinkFixtures.keepable(), AuthenticationState.Welcome, hasTestTag(LINK_SIGN_IN_ROW))
        val row = revealClear(LINK_SIGN_IN_ROW)
        val words = drawn(LINK_SIGN_IN)
        assertTrue("The words $words in their row $row", within(words, row))
        assertTrue("The row $row wider than its words $words", row.width > words.width + px(ROOM_BESIDE))

        val beside = besideTheWords(row, words)
        touches.tap(beside)
        walk.settle()
        assertEquals("A tap at $beside, in the row at $row beside the words at $words, signs nothing in", 0, walk.onUi { links.signIns })
        assertNotNull("The sheet still up", walk.onUi { links.links.route })
        walk.shot("link-sign-in-row", alsoDark = false)

        touches.tap(words.center)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { links.signIns } > 0 }
        walk.settle()
        assertEquals("Asked to sign in once", 1, walk.onUi { links.signIns })
    }

    /**
     * On the first open, the masthead's Sign in takes a finger on its words alone, as a SwiftUI `Button` with its
     * padding, frame and paper laid outside it: a tap on the pill beside the words signs nothing in and opens nothing
     * under it (the pill's paper takes the touch, as the iPhone's background does over the picture), the first open
     * still up; a tap on the words asks to sign in once.
     */
    @Test
    fun onTheFirstOpenSignInTakesAFingerOnItsWordsAlone() {
        walk.show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }) { isGuest = true }
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(tagged(FIRST_OPEN_SIGN_IN_PILL), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() }
        val pill = revealClear(FIRST_OPEN_SIGN_IN_PILL)
        val words = drawn(FIRST_OPEN_SIGN_IN)
        val pages = walk.count(PARCEL_PAGE)
        assertTrue("The words $words on their pill $pill", within(words, pill))

        val beside = besideTheWords(pill, words)
        touches.tap(beside)
        walk.settle()
        assertEquals("A tap at $beside, on the pill at $pill beside the words at $words, signs nothing in", 0, walk.onUi { walk.source.signIns })
        assertTrue("The first open still up", walk.exists(FIRST_OPEN))
        assertEquals("No sample opened under the pill", pages, walk.count(PARCEL_PAGE))
        walk.shot("first-open-sign-in", alsoDark = false)

        touches.tap(words.center)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.signIns } > 0 }
        walk.settle()
        assertEquals("Asked to sign in once", 1, walk.onUi { walk.source.signIns })
    }

    /**
     * In a link's sheet, a carrier's link is read as its words alone, its arrow hidden from TalkBack
     * (ParcelLinkSheet's `carrierLinks`, `.accessibilityHidden(true)`): in the semantics tree the link TalkBack meets
     * holds the words and no description, and the arrow drawn in it carries nothing but its tag nor stands as a node
     * of its own; in the window's accessibility tree the link is one button read as the words, and the arrow, where
     * it is listed, is no stop and says nothing.
     */
    @Test
    fun inALinksSheetTheCarriersLinkArrowIsNotReadApart() {
        val answer = LinkFixtures.keepable()
        val link = walk.identities.catalog.trackingLinks(Parcel(shared = answer.parcel), AppLanguage.En).first()
        val words = walk.text("detail.carrierWebsite", "carrier" to link.name)
        val inSheet = hasAnyAncestor(hasTestTag(LINK_SHEET))
        val button = hasTestTag(PAGE_CARRIER_LINK) and hasText(words) and inSheet
        openSheet(answer, FakeParcelLinks.READER, button)
        reveal(button)

        val merged = compose.onAllNodes(button).onFirst().fetchSemanticsNode().config
        assertEquals("The link's words", listOf(words), merged.getOrNull(SemanticsProperties.Text)?.map { it.text })
        assertNull("No description on the link", merged.getOrNull(SemanticsProperties.ContentDescription))

        val arrows = compose.onAllNodes(
            hasTestTag(PAGE_CARRIER_ARROW) and hasAnyAncestor(hasTestTag(PAGE_CARRIER_LINK) and inSheet),
            useUnmergedTree = true,
        ).fetchSemanticsNodes()
        assertTrue("An arrow in the sheet's link", arrows.isNotEmpty())
        for (arrow in arrows) {
            val own = arrow.config.map { it.key }.filter { it != SemanticsProperties.TestTag }
            assertTrue("The arrow carries nothing but its tag: $own", own.isEmpty())
        }
        assertTrue(
            "No arrow stands apart from its link",
            compose.onAllNodes(hasTestTag(PAGE_CARRIER_ARROW) and inSheet).fetchSemanticsNodes().isEmpty(),
        )

        val node = tree.await("The sheet's link read as \"$words\"") { nodes -> nodes.firstOrNull { it.viewIdResourceName == PAGE_CARRIER_LINK && it.spoken == words } }
        assertTrue("The link is a button", node.isButton)
        assertEquals("The link says its words alone", listOf(words), node.spokenParts)
        assertTrue("The link is one element", tree.oneElement(node))
        for (arrow in tree.under(node).drop(1).filter { it.viewIdResourceName == PAGE_CARRIER_ARROW }) {
            assertFalse("The arrow is no stop", arrow.isStop || arrow.isFocusable)
            assertTrue("The arrow says nothing: \"${arrow.words}\"", arrow.words.isEmpty())
        }
        walk.shot("link-arrow", alsoDark = false)
    }

    /** What `matcher` finds first, scrolled into the window clear of the tab bar where it scrolls. */
    private fun reveal(matcher: SemanticsMatcher) {
        if (compose.onAllNodes(matcher and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            compose.reveal(compose.onAllNodes(matcher).onFirst(), hasScrollAction() and hasAnyDescendant(matcher))
        }
        walk.settle()
    }

    /**
     * `answer`'s sheet, opened over the root `session` sees, edge to edge as the app's window, once `until` finds
     * something in it. The sheet's links.
     */
    private fun openSheet(answer: PublicParcelResponse, session: AuthenticationState, until: SemanticsMatcher): FakeParcelLinks {
        val links = FakeParcelLinks(answer = answer, session = session)
        compose.waitsForMaps()
        compose.runOnUiThread { compose.activity.edgeToEdge(dark = false) }
        compose.setContent { RootHost(state = session, identities = walk.identities, links = links) }
        compose.waitForIdle()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = answer.link.id)) }
        // In either tree: a button's words are its own only once merged.
        compose.waitUntil(LONG_MILLIS) {
            listOf(false, true).any { unmerged -> compose.onAllNodes(until, useUnmergedTree = unmerged).fetchSemanticsNodes().isNotEmpty() }
        }
        walk.settle()
        return links
    }

    private companion object {
        const val FOLDER = "touchareas"

        /** How far a measured size may be off, in pixels. */
        const val PIXEL = 2f

        val ACCOUNT_SIDE = 44.dp
        val REFRESH_SIDE = 26.dp

        /** How far in from a corner of the account's square a tap lands: 5 outside its circle. */
        val ACCOUNT_CORNER_IN = 3.dp

        /** How far in from a corner of the refresh's square a tap lands: 3 outside its circle. */
        val REFRESH_CORNER_IN = 1.5.dp

        /** The touch Android widens a small button's to. */
        val TOUCH_SIZE = 48.dp

        /** The least room beside a button's words for a finger to miss them. */
        val ROOM_BESIDE = 6.dp

        /** How far inside the scroll's clear room a revealed box stands. */
        val CLEAR_MARGIN = 8.dp
    }
}
