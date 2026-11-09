package com.plhery.peekdeliverytracker.ui

import android.view.ViewConfiguration
import android.view.accessibility.AccessibilityNodeInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.DpRect
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_GRABBER
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
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
 * The drag indicator and the detents of the sheets that show one on the iPhone (B-G13), on this device's own window:
 * the share sheet (ParcelShareSheet.swift:60, `fittedSheet` with `.presentationDragIndicator(.visible)`), the feedback's
 * sheet (ParcelFeedbackViews.swift:660), the alerts (ParcelAlerts.swift:41), Change carrier (ParcelDetailView.swift:1364,
 * `.formOnPad(phoneDetents: [.medium, .large])`) and the filters (AddParcelView.swift:1600,
 * `.presentationDetents([.medium, .large])`). In a compact window each is a sheet from the window's bottom: it opens at
 * its first detent with its indicator, medium (0.4714 of the window) or fitted to what it holds, floating 8 in from the
 * window's sides and bottom; a finger on the indicator dragging it up settles it at large, attached under the status
 * bar; one dragging it down from there settles it back at its first detent, and one dragging it down from there sends
 * it away. In a regular window it is a form in the window's middle, as an iPad's (`.presentationSizing(.form)`, fitted
 * to what it holds where the iPhone's is), its indicator still drawn: one size, so pulled up it gives and comes back
 * where it stood, and a drag down sends it away. Beside a parcel the filters are a popover off their button
 * (ParcelListView.swift:1018), with no indicator and no detents. Each drag is a real finger's ([ScreenTouches]) on the
 * indicator, held still before it lifts, so the sheet settles where the finger left it rather than where a throw would
 * carry it. TalkBack reaches the indicator, in Material's words for a drag handle, as VoiceOver reaches the iPhone's
 * ("Sheet grabber"); where the sheet has detents to go to, it is a button whose double tap takes the sheet to the next,
 * as UIKit's grabber cycles them, read from the tree an accessibility service is given ([AccessTree]). No field takes
 * the focus here: writing that opens a fitted sheet fully, and the keyboard, are
 * [KeyboardDismissDeviceTest]'s; Change carrier opening fully at once where the carrier asks for more is
 * ChangeCarrierTest's. The demo's parcels on fake deliveries ([ParcelWalk]) whose stores answer every read in memory,
 * for the synthetic reader@example.com: nothing goes to the network. Each moment saved as
 * sheetdetents/B-G13-<sheet>-<moment>.png in the test package's external files, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class SheetDetentsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touches = ScreenTouches(compose)
    private val tree = AccessTree(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel filed under no carrier: Change carrier opens on automatic detection, which asks for nothing more. */
    private val lamp: Parcel get() = DemoCards.probable()

    /**
     * B-G13: the share sheet, from the gift's page, opens fitted to what it holds with its indicator, and the drags on
     * the indicator walk it to large, back, and away where it is a sheet; a form in a regular window.
     */
    @Test
    fun theShareSheetOpensFittedGoesLargeAndBackAndADragDownSendsItAway() {
        walk.show(DemoCards.parcels())
        walk.open(gift)
        walk.tap(PAGE_SHARE)
        walk.settle()
        walksTheDetents("share", PARCEL_SHARE, Detent.Fitted) {
            walk.deliveries.sharing?.let { walk.source.shares.sheet(it.id) }?.large == true
        }
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.sharing == null } }
    }

    /**
     * B-G13: the feedback's sheet asking who carries the lamp, from Yes on its page's row, opens fitted with its
     * indicator, and the drags walk it to large, back, and away where it is a sheet; a form in a regular window.
     */
    @Test
    fun theFeedbackSheetOpensFittedGoesLargeAndBackAndADragDownSendsItAway() {
        walk.show(listOf(gift, lamp))
        walk.open(lamp)
        walk.node(if (walk.exists(FEEDBACK_QUESTION)) FEEDBACK_QUESTION else FEEDBACK_ROW).performScrollTo()
        walk.settle()
        walk.tap(FEEDBACK_YES)
        walk.settle()
        walksTheDetents("feedback", FEEDBACK_CARRIER_SHEET, Detent.Fitted) { walk.source.feedbacks.of(lamp.id)?.large == true }
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.modal } }
    }

    /**
     * B-G13: the alerts, from the gift's bell for a reader with an email address, open fitted with their indicator, and
     * the drags walk them to large, back, and away where they are a sheet; a form in a regular window.
     */
    @Test
    fun theAlertsOpenFittedGoLargeAndBackAndADragDownSendsThemAway() {
        walk.show(DemoCards.parcels()) { signedInWithEmail(true) }
        walk.open(gift)
        walk.tap(PAGE_BELL)
        walk.settle()
        walksTheDetents("alerts", PARCEL_ALERTS, Detent.Fitted)
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.modal } }
    }

    /**
     * B-G13: Change carrier, from the ••• menu of the lamp's page, opens at the medium detent with its indicator on
     * automatic detection, which asks for nothing more, and the drags walk it to large, back, and away where it is a
     * sheet; a form in a regular window, as `.formOnPad` makes it on an iPad.
     */
    @Test
    fun changeCarrierOpensAtMediumGoesLargeAndBackAndADragDownSendsItAway() {
        walk.show(listOf(gift, lamp))
        walk.open(lamp)
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        walk.tap(menuItemTag(ParcelMenuItem.ChangeCarrier))
        walk.settle()
        walksTheDetents("change-carrier", CHANGE_CARRIER_SHEET, Detent.Medium)
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.carrierEditor == null } }
    }

    /**
     * B-G13: the filters, from the list's button, are a sheet where the list stands alone, at the medium detent with
     * their indicator, which the drags walk to large, back, and away (a form in a regular window); beside a parcel they
     * are a popover off their button, as the iPhone's sheet becomes on an iPad (`filtersShown(asPopover:)`), with no
     * indicator and no detents, which Done closes.
     */
    @Test
    fun theFiltersOpenAtMediumGoLargeAndBackAndADragDownSendsThemAwayOrAreAPopover() {
        walk.show(DemoCards.parcels())
        walk.tap(FILTERS_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FILTERS_SHEET) || walk.exists(FILTERS_POPOVER) }
        walk.settle()
        val popover = walk.onUi { walk.deliveries.sheetsAsForms }
        assertEquals("A popover beside a parcel, a sheet over the list alone", popover, walk.exists(FILTERS_POPOVER))
        if (popover) {
            assertFalse("No drag indicator on the popover", walk.exists(SHEET_GRABBER))
            assertFalse("No sheet under the popover", walk.exists(SHEET_PANEL))
            walk.shot("B-G13-filters-popover", alsoDark = false)
            walk.tap(FILTERS_DONE)
            compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_POPOVER) }
        } else {
            walksTheDetents("filters", FILTERS_SHEET, Detent.Medium)
        }
        compose.waitUntil(LONG_MILLIS) { !walk.onUi { walk.deliveries.showingFilters } }
    }

    /**
     * B-G13: TalkBack reaches Change carrier's drag indicator, which says it is a drag handle in Material's words. A
     * sheet from the window's bottom makes it a button: at medium, a double tap expands the sheet to large ("Expand
     * bottom sheet"), and there it collapses it back to medium ("Collapse bottom sheet"), as UIKit's grabber cycles its
     * detents. A form in a regular window has no detent to go to: the indicator only says what it is.
     */
    @Test
    fun talkBackReachesTheDragIndicatorWhoseDoubleTapExpandsAndCollapsesTheSheet() {
        walk.show(listOf(gift, lamp))
        walk.open(lamp)
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        walk.tap(menuItemTag(ParcelMenuItem.ChangeCarrier))
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) && walk.exists(SHEET_PANEL) }
        walk.awaitStill()
        walk.settle()
        val grabber = tree.awaitId(SHEET_GRABBER)
        assertTrue("TalkBack stops on the indicator", grabber.isScreenReaderFocusable)
        assertEquals(material("m3c_bottom_sheet_drag_handle_description"), grabber.spoken)
        val room = room()
        if (room.regular) {
            assertNull("A form has no detent to go to", grabber.action(AccessibilityNodeInfo.ACTION_CLICK))
            assertFalse(grabber.isButton)
            walk.shot("B-G13-grabber-form", alsoDark = false)
            return
        }
        assertTrue(grabber.isButton)
        assertEquals(material("m3c_bottom_sheet_expand_description"), grabber.clickLabel)
        val largeTop = max(room.top, LARGE_MIN_TOP)
        val opened = frame()
        assertFrame("Change carrier at its medium detent", floating(room, max(room.height * MEDIUM_TOP, largeTop)), opened)

        assertTrue(grabber.click())
        walk.settle()
        walk.awaitStill()
        assertFrame("Expanded to large", Frame(0f, largeTop, room.width, room.height), frame())
        val collapse = material("m3c_bottom_sheet_collapse_description")
        val atLarge = tree.await("The indicator collapsing the sheet") { nodes ->
            nodes.firstOrNull { it.viewIdResourceName == SHEET_GRABBER && it.clickLabel == collapse }
        }
        walk.shot("B-G13-grabber-large", alsoDark = false)

        assertTrue(atLarge.click())
        walk.settle()
        walk.awaitStill()
        assertFrame("Collapsed back to medium", opened, frame())
        val expand = material("m3c_bottom_sheet_expand_description")
        tree.await("The indicator expanding the sheet again") { nodes ->
            nodes.firstOrNull { it.viewIdResourceName == SHEET_GRABBER && it.clickLabel == expand }
        }
    }

    /** Material's string `name`, looked up among the app's resources, where Material's are merged, in the app's words. */
    private fun material(name: String): String = walk.onUi {
        val app = compose.activity
        @Suppress("DiscouragedApi")
        val id = app.resources.getIdentifier(name, "string", app.packageName)
        check(id != 0) { "The app has no string $name of Material's" }
        app.getString(id)
    }

    /** The detent a sheet opens at on the iPhone. */
    private enum class Detent { Medium, Fitted }

    /**
     * The sheet `name`, what it holds tagged `sheet`, up with its one drag indicator at its `first` detent, then walked
     * through its detents by drags on the indicator as this window has it ([aBottomSheet] or [aForm]) and sent away by
     * one: nothing of it left after. `large` reads whether what it shows holds it at its large detent, where it says.
     */
    private fun walksTheDetents(name: String, sheet: String, first: Detent, large: (() -> Boolean)? = null) {
        compose.waitUntil(LONG_MILLIS) { walk.exists(sheet) && walk.exists(SHEET_PANEL) }
        walk.awaitStill()
        walk.settle()
        assertEquals("$name: its drag indicator", 1, walk.count(SHEET_GRABBER))
        val holdsLarge: (() -> Boolean)? = large?.let { read -> { walk.onUi(read) } }
        holdsLarge?.let { assertFalse("$name opens below its large detent", it()) }
        if (room().regular) aForm(name, first, holdsLarge) else aBottomSheet(name, first, holdsLarge)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(sheet) && !walk.exists(SHEET_PANEL) }
        assertFalse("$name: no drag indicator left", walk.exists(SHEET_GRABBER))
    }

    /**
     * A compact window's sheet from the bottom: at its `first` detent, floating 8 in, medium at 0.4714 of the window,
     * fitted where what it holds leaves it lower than large, else attached at large's height (what it holds is as tall
     * as the window); dragged up by the way to large, attached under the status bar; dragged down as far, back where it
     * opened; dragged down 0.8 of its height from there, sent away. A fitted detent too close to large for a drag to
     * tell them apart is only sent away.
     */
    private fun aBottomSheet(name: String, first: Detent, large: (() -> Boolean)?) {
        val room = room()
        val largeTop = max(room.top, LARGE_MIN_TOP)
        val attached = Frame(0f, largeTop, room.width, room.height)
        val opened = frame()
        when {
            first == Detent.Medium -> assertFrame("$name at its medium detent", floating(room, max(room.height * MEDIUM_TOP, largeTop)), opened)
            opened.top > largeTop + TOLERANCE -> assertFrame("$name fitted, floating", floating(room, opened.top), opened)
            else -> assertFrame("$name fitted as high as large", attached, opened)
        }
        walk.shot("B-G13-$name-opened", alsoDark = false)
        val travel = opened.top - largeTop
        if (travel >= MIN_TRAVEL) {
            dragTheIndicator(-(travel + slop() + PAST))
            assertFrame("$name dragged up to its large detent", attached, frame())
            large?.let { assertTrue("$name held at large", it()) }
            walk.shot("B-G13-$name-large", alsoDark = false)
            dragTheIndicator(travel + slop() + PAST)
            assertFrame("$name dragged back down to its first detent", opened, frame())
            large?.let { assertFalse("$name back below large", it()) }
        }
        dragTheIndicator((room.height - frame().top) * AWAY + slop())
    }

    /**
     * A regular window's form, in the window's middle: 580 wide at most and 20 in from the window's sides, as tall as
     * what it holds where it is fitted, else 660 at most, either within the safe area less 20; pulled up, it comes back
     * where it stood, with no larger size to go to; dragged down 0.8 of the window under its top, sent away.
     */
    private fun aForm(name: String, first: Detent, large: (() -> Boolean)?) {
        val room = room()
        val opened = frame()
        val width = min(FORM_WIDTH, room.width - 2 * FORM_MARGIN)
        val formRoom = room.height - room.top - room.bottom - 2 * FORM_MARGIN
        val height = if (first == Detent.Medium) min(FORM_HEIGHT, formRoom) else opened.bottom - opened.top
        assertTrue("$name within the window's room for a form: $height in $formRoom", height <= formRoom + TOLERANCE)
        val left = (room.width - width) / 2
        val top = max(room.top, (room.height - height) / 2)
        assertFrame("$name a form in the window's middle", Frame(left, top, left + width, top + height), opened)
        walk.shot("B-G13-$name-form", alsoDark = false)
        dragTheIndicator(-(FORM_PULL + slop()))
        assertFrame("$name back where it stood", opened, frame())
        large?.let { assertFalse("$name has no large detent of its own", it()) }
        dragTheIndicator((room.height - opened.top) * AWAY + slop())
    }

    /** A finger on the drag indicator moved `by` dp down (up where negative), held still before it lifts, and the sheet settled. */
    private fun dragTheIndicator(by: Float) {
        touches.drag(touches.point(SHEET_GRABBER), Offset(0f, by * compose.density.density))
        walk.settle()
        walk.awaitStill()
    }

    /** The system's touch slop in dp: what a drag moves before the sheet starts to follow it. */
    private fun slop(): Float = walk.onUi { ViewConfiguration.get(compose.activity).scaledTouchSlop } / compose.density.density

    /** A sheet floating at `top`, 8 in from the window's sides and bottom. */
    private fun floating(room: Room, top: Float) = Frame(FLOAT_INSET, top, room.width - FLOAT_INSET, room.height - FLOAT_INSET)

    /** The window in dp, and the room its bars and cutout take at its top and bottom. */
    private data class Room(val width: Float, val height: Float, val top: Float, val bottom: Float) {
        /** Regular as the sheets have it: a form in the window's middle rather than a sheet from its bottom. */
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT
    }

    /** Where something stands in the window, in dp. */
    private data class Frame(val left: Float, val top: Float, val right: Float, val bottom: Float)

    private fun room(): Room = walk.onUi {
        val view = compose.activity.window.decorView
        val scale = compose.activity.resources.displayMetrics.density
        val bars = ViewCompat.getRootWindowInsets(view)?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        Room(view.width / scale, view.height / scale, (bars?.top ?: 0) / scale, (bars?.bottom ?: 0) / scale)
    }

    /** Where the sheet's panel stands now. */
    private fun frame(): Frame {
        val bounds: DpRect = compose.onNodeWithTag(SHEET_PANEL, useUnmergedTree = true).getUnclippedBoundsInRoot()
        return Frame(bounds.left.value, bounds.top.value, bounds.right.value, bounds.bottom.value)
    }

    private fun assertFrame(what: String, expected: Frame, actual: Frame) {
        val off = listOf(expected.left - actual.left, expected.top - actual.top, expected.right - actual.right, expected.bottom - actual.bottom)
        assertTrue("$what at $actual, not $expected", off.all { abs(it) <= TOLERANCE })
    }

    private companion object {
        const val FOLDER = "sheetdetents"

        /** How far a sheet's edge may stand from where it is worked out, in dp: a pixel's rounding at most. */
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

        /** The least way between a sheet's first detent and large that a drag is walked through, in dp. */
        const val MIN_TRAVEL = 24f

        /** How much further than the way between two detents a drag goes, in dp: it settles at the nearer, the far one. */
        const val PAST = 4f

        /** How far a form is pulled up, in dp. */
        const val FORM_PULL = 60f

        /** The part of the room under a sheet's top a drag down takes to send it away: past half, nearer gone than up. */
        const val AWAY = 0.8f
    }
}
