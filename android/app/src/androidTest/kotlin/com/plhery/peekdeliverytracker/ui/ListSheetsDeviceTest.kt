package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.width
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FOOT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_CLOSE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.sheet.COVER_PANEL
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import java.io.IOException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the deliveries' list presents (A-S43), on this device's own window, where the list stands alone or beside a
 * parcel: Add over the whole window or a form in its middle (`.fullScreenCover` or `.sheet` with
 * `.presentationSizing(.form)`), the filters a sheet from the bottom or a popover off their button, the account a sheet
 * or a form, a failed action's alert, and a page of the site, which the iPhone shows in SafariPage's sheet and the app
 * opens in a Custom Tab of the default browser over the window. Each shape is measured against the window's size, its
 * bars, cutout and keyboard, with the sizes the app's sheet, popover and alert take: the sheet's 0.4714 of the window
 * at the medium detent, floating 8 in, attached under the status bar at the large one, 580 × 660 at most as a form
 * and the window's shorter side less 52 each side as a page; the filters' popover 380 × 620, down to 340 × 440, 16 in
 * from the safe area and its arrow 13 tall; the alert 320 wide. Signed in as the synthetic reader@example.com on fake
 * deliveries ([ParcelWalk]) whose stores answer every read in memory: nothing goes to the network, and the browser's
 * page is asked of the system without opening. Each moment saved as listsheets/A-S43-<moment>.png in the test package's
 * external files, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class ListSheetsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()

    /**
     * A-S43: Add, from the list's +, covers the whole window where the list stands alone, edge to edge; beside a parcel
     * it is a form in the window's middle, 580 wide and 660 tall where the window has room, half the keyboard higher
     * while one is up. Its ✕ closes it, and nothing of it is left over the list.
     */
    @Test
    fun addCoversTheWindowOverTheListAndIsAFormBesideAParcel() {
        walk.show(demo)
        val stacked = walk.stacked()
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.settle()
        val room = room()
        if (stacked) {
            assertFalse(walk.exists(SHEET_PANEL))
            assertFrame("Add's cover", Frame(0f, 0f, room.width, room.height), bounds(COVER_PANEL))
            walk.shot("A-S43-add-cover", alsoDark = false)
        } else {
            assertFalse(walk.exists(COVER_PANEL))
            assertFrame("Add's form", form(room, page = false), bounds(SHEET_PANEL))
            walk.shot("A-S43-add-form", alsoDark = false)
        }

        walk.tap(ADD_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        walk.awaitStill()
        walk.settle()
        assertFalse(walk.exists(COVER_PANEL))
        assertFalse(walk.exists(SHEET_PANEL))
    }

    /**
     * A-S43: the filters, from their button, are a sheet over the list where it stands alone, at the medium detent
     * floating clear of the window's sides and bottom in a compact window, a form in a regular one; beside a parcel,
     * a popover off their button, 380 wide and as tall as the room under it allows between 440 and 620, its arrow at the
     * button. Done closes them.
     */
    @Test
    fun theFiltersAreASheetOverTheListAndAPopoverOffTheirButtonBesideAParcel() {
        walk.show(demo)
        val stacked = walk.stacked()
        walk.tap(FILTERS_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(if (stacked) FILTERS_SHEET else FILTERS_POPOVER) }
        walk.settle()
        val room = room()
        if (stacked) {
            assertFalse(walk.exists(FILTERS_POPOVER))
            val expected = if (room.regular) form(room, page = false) else compactSheet(room, medium = true)
            assertFrame("The filters' sheet", expected, bounds(SHEET_PANEL))
            walk.shot("A-S43-filters-sheet", alsoDark = false)
        } else {
            assertFalse(walk.exists(FILTERS_SHEET))
            assertFalse(walk.exists(SHEET_PANEL))
            val scale = compose.density.density
            val button = walk.onUi { walk.deliveries.filtersAnchor }
            val anchor = Frame(button.left / scale, button.top / scale, button.right / scale, button.bottom / scale)
            assertFrame("The filters' popover", popover(room, anchor), bounds(FILTERS_POPOVER))
            walk.shot("A-S43-filters-popover", alsoDark = false)
        }

        walk.tap(FILTERS_DONE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_SHEET) && !walk.exists(FILTERS_POPOVER) }
        assertFalse(walk.onUi { walk.deliveries.showingFilters })
    }

    /**
     * A-S43: the account, from its button, is a sheet where the list stands alone, at the large detent and attached
     * under the status bar in a compact window, a page sheet in a regular one; beside a parcel, a form sized as Add's
     * is. Close closes it.
     */
    @Test
    fun theAccountIsASheetOverTheListAndAFormBesideAParcel() {
        walk.show(demo)
        val stacked = walk.stacked()
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.settle()
        assertEquals("Sheets as forms", !stacked, walk.onUi { walk.deliveries.sheetsAsForms })
        val room = room()
        val expected = when {
            !stacked -> form(room, page = false)
            room.regular -> form(room, page = true)
            else -> compactSheet(room, medium = false)
        }
        assertFrame("The account's sheet", expected, bounds(SHEET_PANEL))
        walk.shot(if (stacked) "A-S43-account-sheet" else "A-S43-account-form", alsoDark = false)

        walk.tap(SETTINGS_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        assertFalse(walk.onUi { walk.deliveries.showingAccount })
    }

    /**
     * A-S43: an action that fails, an archive offline, says so in an alert titled "native.errorTitle" with the error's
     * words and one "common.close", 320 wide in the middle of the window, which it dims; the list standing alone or
     * beside a parcel alike. Close dismisses it, and the error with it.
     */
    @Test
    fun aFailedActionsAlertSaysWhyAndCloseDismissesIt() {
        walk.show(demo)
        walk.onUi { walk.source.failure = IOException("offline") }
        walk.onUi { walk.deliveries.archive(demo.first()) }
        compose.waitUntil(LONG_MILLIS) { walk.exists(ACTION_ERROR) }
        walk.settle()
        val title = walk.text("native.errorTitle")
        assertTrue("Titled $title", walk.shows(ACTION_ERROR, title))
        assertTrue(walk.shows(ACTION_ERROR, walk.text("error.connection")))
        assertTrue(walk.shows(ACTION_ERROR_CLOSE, walk.text("common.close")))
        val room = room()
        val dim = bounds(ACTION_ERROR)
        assertFrame("The dim over the window", Frame(0f, 0f, room.width, room.height), dim)
        val card = compose.onNode(
            SemanticsMatcher.expectValue(SemanticsProperties.PaneTitle, title) and hasAnyAncestor(hasTestTag(ACTION_ERROR)),
            useUnmergedTree = true,
        ).getUnclippedBoundsInRoot()
        assertEquals("The alert's width: $card", ALERT_WIDTH, card.width.value, 1f)
        assertEquals("Across the window's middle: $card", room.width / 2, (card.left + card.right).value / 2, 1f)
        assertEquals("Down the window's middle: $card", room.height / 2, (card.top + card.bottom).value / 2, 1f)
        walk.shot("A-S43-error-alert", alsoDark = false)

        walk.tap(ACTION_ERROR_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ACTION_ERROR) }
        assertNull(walk.onUi { walk.deliveries.actionError })
    }

    /**
     * A-S43: a page of the site from the list, the home page's link at its foot, opens without leaving the app as the
     * iPhone's SafariPage sheet does: here a Custom Tab of the default browser over the window, asked of the system as a
     * view of the page in that browser, with the Custom Tabs session's extra, where the phone has one; a plain view of
     * the page where it has none. Nothing of the app's own opens over the list.
     */
    @Test
    fun aPageOfTheSiteOpensInACustomTabOverTheWindow() {
        walk.show(demo)
        walk.node(PARCEL_LIST).performScrollToKey("foot")
        walk.settle()
        walk.tap("$FOOT.home")
        compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        val asked = walk.asked().single()
        assertEquals(Intent.ACTION_VIEW, asked.action)
        assertEquals(walk.source.homePageUrl, asked.data.toString())
        val browser = walk.onUi { CustomTabsClient.getPackageName(compose.activity, null) }
        if (browser != null) {
            assertEquals("The default browser's own tab", browser, asked.`package`)
            assertTrue("A Custom Tab", asked.hasExtra(CustomTabsIntent.EXTRA_SESSION))
        }
        walk.settle()
        assertFalse(walk.exists(SHEET_PANEL))
        assertFalse(walk.exists(COVER_PANEL))
        assertTrue(walk.exists(PARCEL_LIST))
    }

    /** The window in dp, the room its bars and cutout take on each side, and the keyboard's: what a sheet lays out in. */
    private data class Room(
        val width: Float,
        val height: Float,
        val left: Float,
        val top: Float,
        val right: Float,
        val bottom: Float,
        val keyboard: Float,
    ) {
        /** Regular as the sheets have it: 600 wide and 480 tall at least. */
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT
    }

    /** Where something stands in the window, in dp. */
    private data class Frame(val left: Float, val top: Float, val right: Float, val bottom: Float)

    private fun room(): Room = walk.onUi {
        val view = compose.activity.window.decorView
        val scale = compose.activity.resources.displayMetrics.density
        val insets = ViewCompat.getRootWindowInsets(view)
        val bars = insets?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        val keyboard = if (insets?.isVisible(WindowInsetsCompat.Type.ime()) == true) insets.getInsets(WindowInsetsCompat.Type.ime()).bottom else 0
        Room(
            view.width / scale, view.height / scale, (bars?.left ?: 0) / scale, (bars?.top ?: 0) / scale,
            (bars?.right ?: 0) / scale, (bars?.bottom ?: 0) / scale, keyboard / scale,
        )
    }

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getUnclippedBoundsInRoot()

    /**
     * A regular window's sheet: a form in the window's middle, 580 × 660 at most and 20 in from the safe area, or a page
     * sheet, the window's shorter side less 52 each side, 32 from the window's top and bottom at least and 44 lower in a
     * window wider than tall; half the keyboard higher while one is up, no higher than its highest.
     */
    private fun form(room: Room, page: Boolean): Frame {
        val pageHighest = max(room.top, PAGE_GAP)
        val pageTop = if (room.width > room.height) pageHighest + PAGE_WIDE_DROP else pageHighest
        val width = if (page) min(room.width, room.height) - 2 * PAGE_SIDE else min(FORM_WIDTH, room.width - 2 * FORM_MARGIN)
        val height = if (page) {
            room.height - pageTop - max(room.bottom, PAGE_GAP)
        } else {
            min(FORM_HEIGHT, room.height - room.top - room.bottom - 2 * FORM_MARGIN)
        }
        val rest = if (page) pageTop else max(room.top, (room.height - height) / 2)
        val highest = if (page) pageHighest else room.top
        val top = rest - min(room.keyboard / 2, rest - highest).coerceAtLeast(0f)
        val left = (room.width - width) / 2
        return Frame(left, top, left + width, top + height)
    }

    /**
     * A compact window's sheet: at the medium detent, its top 0.4714 of the way down and floating 8 in from the window's
     * sides and bottom; at the large one, or in a window under 480 tall, attached to them, its top under the status bar.
     */
    private fun compactSheet(room: Room, medium: Boolean): Frame {
        val largeTop = max(room.top, LARGE_MIN_TOP)
        if (!medium || room.height < REGULAR_HEIGHT) return Frame(0f, largeTop, room.width, room.height)
        return Frame(FLOAT_INSET, max(room.height * MEDIUM_TOP, largeTop), room.width - FLOAT_INSET, room.height - FLOAT_INSET)
    }

    /**
     * The filters' popover off their button at `anchor`: under it while there is room for its ideal height there or
     * more room than over it, centred on it as far as the safe area less 16 allows, 380 wide down to 340, as tall as the
     * room allows between 440 and 620; what it holds under its arrow, or over it.
     */
    private fun popover(room: Room, anchor: Frame): Frame {
        val start = room.left + POPOVER_MARGIN
        val end = room.width - room.right - POPOVER_MARGIN
        val top = room.top + POPOVER_MARGIN
        val bottom = room.height - room.bottom - POPOVER_MARGIN
        val roomBelow = max(0f, bottom - anchor.bottom - ARROW)
        val roomAbove = max(0f, anchor.top - top - ARROW)
        val below = roomBelow >= POPOVER_HEIGHT || roomBelow >= roomAbove
        val roomThere = if (below) roomBelow else roomAbove
        val across = max(0f, end - start)
        val width = min(POPOVER_WIDTH, across).coerceAtLeast(min(POPOVER_LEAST_WIDTH, across))
        val height = min(POPOVER_HEIGHT, roomThere).coerceAtLeast(min(POPOVER_LEAST_HEIGHT, roomThere))
        val left = ((anchor.left + anchor.right) / 2 - width / 2).coerceIn(start, max(start, end - width))
        val bodyTop = if (below) {
            anchor.bottom.coerceIn(top - ARROW, max(top - ARROW, bottom - height - ARROW)) + ARROW
        } else {
            (anchor.top - height - ARROW).coerceIn(top, max(top, bottom - height))
        }
        return Frame(left, bodyTop, left + width, bodyTop + height)
    }

    private fun assertFrame(what: String, expected: Frame, actual: DpRect) {
        val shown = Frame(actual.left.value, actual.top.value, actual.right.value, actual.bottom.value)
        val off = listOf(expected.left - shown.left, expected.top - shown.top, expected.right - shown.right, expected.bottom - shown.bottom)
        assertTrue("$what at $shown, not $expected", off.all { abs(it) <= TOLERANCE })
    }

    private companion object {
        const val FOLDER = "listsheets"

        /** How far a frame may stand from where it is worked out, in dp: a pixel's rounding at most. */
        const val TOLERANCE = 1.5f

        const val REGULAR_WIDTH = 600f
        const val REGULAR_HEIGHT = 480f

        // The sheet's sizes (Sheet.kt).
        const val MEDIUM_TOP = 0.4714f
        const val FLOAT_INSET = 8f
        const val LARGE_MIN_TOP = 10f
        const val FORM_WIDTH = 580f
        const val FORM_HEIGHT = 660f
        const val FORM_MARGIN = 20f
        const val PAGE_SIDE = 52f
        const val PAGE_GAP = 32f
        const val PAGE_WIDE_DROP = 44f

        // The filters' popover (ParcelFilters.kt) and the popover's margin and arrow (Popover.kt).
        const val POPOVER_WIDTH = 380f
        const val POPOVER_LEAST_WIDTH = 340f
        const val POPOVER_HEIGHT = 620f
        const val POPOVER_LEAST_HEIGHT = 440f
        const val POPOVER_MARGIN = 16f
        const val ARROW = 13f

        /** An alert's width, on a tablet too (CardMenu.kt). */
        const val ALERT_WIDTH = 320f
    }
}
