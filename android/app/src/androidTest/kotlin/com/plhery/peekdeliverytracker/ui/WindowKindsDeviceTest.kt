package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.DpRect
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_DONE
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_POPOVER
import com.plhery.peekdeliverytracker.ui.deliveries.FILTERS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_POSTER
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_SCROLL
import com.plhery.peekdeliverytracker.ui.deliveries.LIST_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_AVERAGE
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.sheet.COVER_PANEL
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What this window gets as an iPhone's or an iPad's (A-ST34), in one place, on the device's own window as it is, never
 * narrowed: its size and safe sides in dp against the lines the app draws, never the device's name. A parcel beside the
 * list or pushed over it (a regular window, 600 × 480 at least, 730 wide); the list's sheets an iPhone's or an iPad's
 * with it (`sheetsAsForms`); the first open's poster or the phone's page (600 both ways between the safe sides); the
 * passport's spread or one page (a regular window with 760 between the safe sides). Each is measured in detail
 * elsewhere, the window narrowed across its line where it can be: [ArrangementDeviceTest] (A-S96),
 * [ListSheetsDeviceTest] (A-S43), FirstOpenTest's layouts, and [LargeTextDeviceTest] and [PassportLargeTextDeviceTest]
 * (A-AX05) at the accessibility sizes. A foldable standing half open, which parts the deliveries, the first open and
 * the passport at its fold, is left out: a flat one has no fold in its window's way. The demo's parcels on fake
 * deliveries ([ParcelWalk]) whose stores answer every read in memory, for the synthetic reader@example.com, the demo
 * or a guest: nothing goes to the network. Each saved as windowkinds/A-ST34-<what>.png in the test package's external
 * files, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class WindowKindsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()

    /**
     * A-ST34: in an iPad's window, regular and 730 wide at least, a parcel opened from its card stands beside the list,
     * in the page's column from the list's edge, the list still shown; elsewhere it is pushed over the list across the
     * whole window, as on an iPhone, the list under it.
     */
    @Test
    fun aParcelStandsBesideTheListInAnIPadsWindowAndIsPushedOverItInAnIPhones() {
        walk.show(demo)
        val room = room()
        assertEquals("A window of $room: side by side", room.sideBySide, !walk.stacked())
        walk.open(DemoCards.parcel("Birthday gift"))
        if (room.sideBySide) {
            val list = bounds(LIST_COLUMN)
            assertEquals("The page's column from the list's edge", list.right.value, bounds(PAGE_COLUMN).left.value, TOLERANCE)
            assertTrue("The parcel in the page's column", bounds(PARCEL_PAGE).left.value >= list.right.value - TOLERANCE)
            assertTrue("The list still shown", walk.onUi { walk.deliveries.listShown })
        } else {
            assertFalse("No column of the list", walk.exists(LIST_COLUMN))
            assertFalse("The list under the page", walk.onUi { walk.deliveries.listShown })
            val page = bounds(PARCEL_PAGE)
            assertEquals("The page from the window's leading edge", 0f, page.left.value, TOLERANCE)
            assertEquals("The page to the window's trailing edge", room.width, page.right.value, TOLERANCE)
        }
        walk.shot("A-ST34-arrangement", alsoDark = false)
    }

    /**
     * A-ST34: the list's sheets go with the arrangement (`sheetsAsForms`): over the list alone they are an iPhone's,
     * Add over the whole window and the filters a sheet; beside a parcel they are an iPad's, Add a form in the window's
     * middle and the filters a popover off their button.
     */
    @Test
    fun theListsSheetsAreAnIPhonesOverTheListAloneAndAnIPadsBesideAParcel() {
        walk.show(demo)
        val forms = room().sideBySide
        assertEquals("Sheets as forms", forms, walk.onUi { walk.deliveries.sheetsAsForms })

        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.settle()
        assertEquals("Add a form", forms, walk.exists(SHEET_PANEL))
        assertEquals("Add over the whole window", !forms, walk.exists(COVER_PANEL))
        walk.shot("A-ST34-add", alsoDark = false)
        walk.tap(ADD_CLOSE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        walk.awaitStill()
        walk.settle()

        walk.tap(FILTERS_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FILTERS_POPOVER) || walk.exists(FILTERS_SHEET) }
        walk.settle()
        assertEquals("The filters a popover", forms, walk.exists(FILTERS_POPOVER))
        assertEquals("The filters a sheet", !forms, walk.exists(FILTERS_SHEET))
        walk.shot("A-ST34-filters", alsoDark = false)
        walk.tap(FILTERS_DONE)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FILTERS_POPOVER) && !walk.exists(FILTERS_SHEET) }
    }

    /**
     * A-ST34: a guest's first open is the poster, an iPad's, in a window 600 or more both ways between its safe sides;
     * elsewhere the phone's page, the picture over the question, held still or scrolling.
     */
    @Test
    fun theFirstOpenIsThePosterInAWindowSixHundredBothWaysAndThePhonesPageElsewhere() {
        walk.show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }) { isGuest = true }
        val room = room()
        assertEquals("A window of $room: the poster", room.poster, walk.exists(FIRST_OPEN_POSTER))
        if (!room.poster) {
            assertTrue("A window of $room: the phone's page", walk.exists(FIRST_OPEN_PAGE) || walk.exists(FIRST_OPEN_SCROLL))
        }
        walk.shot("A-ST34-first-open", alsoDark = false)
    }

    /**
     * A-ST34: the demo's passport is a spread, the times on the page beside the cover, in a regular window with 760 of
     * page between its safe sides, two of the parcel page's 340-point columns with their margins; elsewhere it is one
     * page, the times under the cover.
     */
    @Test
    fun thePassportIsASpreadWithSevenHundredSixtyOfPageAndOnePageElsewhere() {
        walk.show(demo, session = AuthenticationState.Demo)
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PASSPORT_COVER) }
        walk.settle()
        val room = room()
        val cover = bounds(PASSPORT_COVER)
        val average = bounds(PASSPORT_AVERAGE)
        if (room.spread) {
            assertTrue("A window of $room: the times beside the cover, $average by $cover", average.left.value >= cover.right.value - TOLERANCE)
        } else {
            assertTrue("A window of $room: the times under the cover, $average under $cover", average.top.value >= cover.bottom.value - TOLERANCE)
        }
        walk.shot("A-ST34-passport", alsoDark = false)
    }

    /** The window in dp: its size, and its safe sides (the bars and cutout at its left and right) together. */
    private data class Room(val width: Float, val height: Float, val sides: Float) {
        /** Regular both ways, as the deliveries, their sheets and the passport take the size classes. */
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT

        /** An iPad's: the list and a parcel side by side, in a regular window wide enough for both. */
        val sideBySide: Boolean get() = regular && width >= SIDE_BY_SIDE

        /** The first open's poster: 600 or more of window between the safe sides, and 600 tall. */
        val poster: Boolean get() = minOf(width - sides, height) >= POSTER

        /** The passport's spread: a regular window with room for its two pages between the safe sides. */
        val spread: Boolean get() = regular && width - sides >= SPREAD
    }

    private fun room(): Room = walk.onUi {
        val view = compose.activity.window.decorView
        val scale = compose.activity.resources.displayMetrics.density
        val insets = ViewCompat.getRootWindowInsets(view)
        val safe = insets?.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        Room(view.width / scale, view.height / scale, ((safe?.left ?: 0) + (safe?.right ?: 0)) / scale)
    }

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getUnclippedBoundsInRoot()

    private companion object {
        const val FOLDER = "windowkinds"

        /** The regular size classes, as the deliveries and the passport take them. */
        const val REGULAR_WIDTH = 600f
        const val REGULAR_HEIGHT = 480f

        /**
         * `DeliveriesArrangement`: the list's 320 and a parcel's 390, and the 20 of slack a window shown stacked
         * goes past them before it lays them out side by side.
         */
        const val SIDE_BY_SIDE = 730f

        /** The first open's poster: the window's narrower way, its safe sides left out of its width. */
        const val POSTER = 600f

        /** `PassportSpread.fits`: two columns of 340, each with its 2 × 20 margins. */
        const val SPREAD = 760f

        /** How far apart two edges that meet may be measured, in dp. */
        const val TOLERANCE = 1f
    }
}
