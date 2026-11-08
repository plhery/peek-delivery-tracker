package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.width
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DEVICE_ACCOUNT_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_NO
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_BACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORD
import com.plhery.peekdeliverytracker.ui.deliveries.LIST_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_LINK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import kotlin.math.max
import kotlin.math.min
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the deliveries lay out anew at the larger text sizes (A-AX05), on this device's own window, as the iPhone and
 * the iPad do: the list and a parcel's page side by side from 730 points of window (920 ± 20 at the accessibility
 * sizes), back one over the other under 690 (900), in a window at least 600 wide and 480 tall, the list as wide as
 * NavigationSplitView makes it; the invitation to turn notifications on among the cards rather than over them; an
 * asking toast's answers under its words; a guest's offer of an account with its button under its words. Each test
 * branches on the window it finds, never on the device, and asserts what that window shows; where a window is wide
 * enough, the root is narrowed, by a box of its own width at the window's leading edge, half a point either side of
 * each line. The demo's parcels on fake deliveries ([ParcelWalk]), the text size the composition's own Density. Shots
 * in largetext/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class LargeTextDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val demo: List<Parcel> = DemoCards.parcels()

    /** The width the root is given, the window's where null. */
    private var width by mutableStateOf<Dp?>(null)

    /** The window as the root last measured it. */
    @Volatile
    private var room: Room? = null

    private data class Room(val width: Dp, val height: Dp) {
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT
    }

    /** The root in a box [width] wide at the window's leading edge, as tall as the window; the window kept in [room]. */
    @Composable
    private fun Narrowed(content: @Composable () -> Unit) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val measured = Room(maxWidth, maxHeight)
            SideEffect { room = measured }
            val given = width?.let { minOf(it, maxWidth) } ?: maxWidth
            Box(Modifier.align(Alignment.TopStart).width(given).fillMaxHeight()) { content() }
        }
    }

    private fun narrowTo(given: Dp?) {
        width = given
        walk.recompose()
        walk.settle()
    }

    private fun at(scale: Float) {
        walk.fontScale = scale
        walk.recompose()
        walk.settle()
    }

    private fun bounds(tag: String): DpRect = compose.onNode(hasTestTag(tag), useUnmergedTree = true).getUnclippedBoundsInRoot()

    private fun keys(): List<Any> = walk.onUi { walk.deliveries.itemKeys }

    // A-AX05: the list and the page side by side.

    /**
     * A-AX05: at the default text the list and a parcel's page stand side by side from 730 points of window, a window
     * itself at least 600 wide and 480 tall, and stay so down to 690, under which the list stands alone; it turns back
     * only at 730 (`DeliveriesArrangement`, 710 ± 20). Side by side, the list is 380 wide, or what leaves the page 390,
     * and 320 at least, the page from the list's edge. A window too narrow or too short for both keeps the list alone.
     */
    @Test
    fun atTheDefaultTextTheListAndThePageStandSideBySideFromSevenHundredThirtyToSixHundredNinety() =
        turnsAt(scale = 1f, enter = 730f, leave = 690f, smallest = 320f, ideal = 380f, parcel = 390f)

    /**
     * A-AX05: at the accessibility sizes each column needs more room: side by side from 940 points of window and down
     * to 900 (920 ± 20), the list 480 wide, or what leaves the page 480, and 440 at least.
     */
    @Test
    fun atTheLargestTextTheListAndThePageStandSideBySideFromNineHundredFortyToNineHundred() =
        turnsAt(scale = 2f, enter = 940f, leave = 900f, smallest = 440f, ideal = 480f, parcel = 480f)

    /**
     * The arrangement at [scale]: where the window is regular and wider than [enter], from the window's width down to
     * half a point over [leave] side by side, half a point under it stacked, back up to half a point under [enter]
     * still stacked, half a point over it side by side again, and so down to half a point under [enter]; at the
     * window's width last. Elsewhere the window's own arrangement, from the list alone. Side by side, the list's
     * column as wide as `listWidth` makes it at that width.
     */
    private fun turnsAt(scale: Float, enter: Float, leave: Float, smallest: Float, ideal: Float, parcel: Float) {
        walk.fontScale = scale
        walk.show(demo, around = { content -> Narrowed(content) })
        val room = checkNotNull(room)
        val name = if (scale >= 2f) "large" else "default"
        fun listAt(given: Float) = max(smallest, min(ideal, given - parcel))
        if (room.regular && room.width >= (enter + 0.5f).dp) {
            assertSideBySide("At the window's ${room.width}", listAt(room.width.value))
            narrowTo((leave + 0.5f).dp)
            assertSideBySide("Down to half a point over $leave", listAt(leave + 0.5f))
            walk.shot("A-AX05-$name-narrowest-side-by-side", alsoDark = false)
            narrowTo((leave - 0.5f).dp)
            assertStacked("Half a point under $leave")
            narrowTo((enter - 0.5f).dp)
            assertStacked("Back up to half a point under $enter")
            narrowTo((enter + 0.5f).dp)
            assertSideBySide("Half a point over $enter", listAt(enter + 0.5f))
            narrowTo((enter - 0.5f).dp)
            assertSideBySide("Back down to half a point under $enter", listAt(enter - 0.5f))
            narrowTo(null)
            assertSideBySide("Back at the window's ${room.width}", listAt(room.width.value))
            walk.shot("A-AX05-$name-side-by-side")
        } else {
            val sideBySide = room.regular && room.width >= enter.dp
            if (sideBySide) assertSideBySide("A window of $room", listAt(room.width.value)) else assertStacked("A window of $room")
            walk.shot("A-AX05-$name-window")
        }
    }

    private fun assertStacked(moment: String) {
        assertTrue("$moment: the list alone", walk.stacked())
        assertFalse("$moment: no column of the list", walk.exists(LIST_COLUMN))
    }

    private fun assertSideBySide(moment: String, listWidth: Float) {
        assertFalse("$moment: side by side", walk.stacked())
        val list = bounds(LIST_COLUMN)
        val page = bounds(PAGE_COLUMN)
        assertEquals("$moment: the list's width", listWidth, list.width.value, WIDTH_TOLERANCE)
        assertEquals("$moment: the page from the list's edge", list.right.value, page.left.value, WIDTH_TOLERANCE)
    }

    // A-AX05: what moves at the accessibility sizes.

    /**
     * A-AX05: the invitation to turn notifications on stands over the list's foot, 380 wide at most, up to xxxLarge;
     * at the accessibility sizes it would cover the cards, and it scrolls with them instead, an item of the list
     * (`shouldShowNotificationInvitation` in the list's rows). Beside a parcel's page it keeps to the list's column.
     */
    @Test
    fun atTheAccessibilitySizesTheNotificationInvitationScrollsWithTheCards() {
        walk.fontScale = 1.3f
        walk.show(demo) { shouldInviteNotifications.value = true }
        compose.waitUntil(LONG_MILLIS) { walk.exists(NOTIFICATIONS_PROMPT) }
        walk.settle()
        assertFalse("Not among the cards: ${keys()}", INVITATION in keys())
        assertFalse("Over the list rather than in it", inTheList(NOTIFICATIONS_PROMPT))
        val over = bounds(NOTIFICATIONS_PROMPT)
        assertTrue("380 wide at most: $over", over.width <= 380.dp + TOLERANCE)
        assertInTheListsColumn(over)
        walk.shot("A-AX05-invitation-over-the-list")

        at(1.5f)
        compose.waitUntil(LONG_MILLIS) { INVITATION in keys() }
        walk.node(PARCEL_LIST).performScrollToKey(INVITATION)
        walk.settle()
        assertEquals("One invitation", 1, walk.count(NOTIFICATIONS_PROMPT))
        assertTrue("In the list", inTheList(NOTIFICATIONS_PROMPT))
        assertInTheListsColumn(bounds(NOTIFICATIONS_PROMPT))
        walk.shot("A-AX05-invitation-in-the-list")
    }

    private fun inTheList(tag: String): Boolean = compose.onAllNodes(
        hasTestTag(tag) and hasAnyAncestor(hasTestTag(PARCEL_LIST)),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** Beside a parcel's page, [rect] within the list's column. */
    private fun assertInTheListsColumn(rect: DpRect) {
        if (walk.stacked()) return
        val column = bounds(LIST_COLUMN)
        assertTrue("$rect within the list's column $column", rect.left >= column.left - TOLERANCE && rect.right <= column.right + TOLERANCE)
    }

    /**
     * A-AX05: an asking toast (Pip's "Does Chronopost say the same?" back from the carrier's site) puts its answers
     * after its words, level with them, up to xxxLarge; at the accessibility sizes its words take the lines they need
     * and the answers go 10 under them, still side by side (`InlineToast`'s `isAccessibilitySize` stack).
     */
    @Test
    fun atTheAccessibilitySizesAnAskingToastsAnswersGoUnderItsWords() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(listOf(gift, DemoCards.probable()))
        walk.open(gift)
        walk.tap(PAGE_CARRIER_LINK)
        compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        // Back on the clock the test moves itself: Pip asks for twelve seconds.
        walk.awayAndBack()
        walk.advanceTo(walk.now() + TOAST_SETTLE_MILLIS)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_BACK_YES) }
        val words = toastWords()
        val yes = bounds(FEEDBACK_BACK_YES)
        val no = bounds(FEEDBACK_BACK_NO)
        assertTrue("Yes after the words: $yes, $words", yes.left >= words.right - TOLERANCE)
        assertTrue("Yes level with the words: $yes, $words", yes.top < words.bottom && yes.bottom > words.top)
        assertTrue("No after Yes: $no, $yes", no.left >= yes.right - TOLERANCE)
        walk.shot("A-AX05-toast-beside", alsoDark = false)

        walk.fontScale = 1.5f
        walk.recompose()
        walk.advanceTo(walk.now() + TOAST_SETTLE_MILLIS)
        compose.waitForIdle()
        val large = toastWords()
        val under = bounds(FEEDBACK_BACK_YES)
        val beside = bounds(FEEDBACK_BACK_NO)
        assertTrue("Yes 10 under the words: $under, $large", under.top - large.bottom >= 10.dp - TOLERANCE)
        assertEquals("No level with Yes", under.top.value, beside.top.value, TOLERANCE.value)
        assertTrue("No after Yes: $beside, $under", beside.left >= under.right - TOLERANCE)
        walk.shot("A-AX05-toast-under", alsoDark = false)
    }

    private fun toastWords(): DpRect = compose.onAllNodes(
        hasText(CARRIER, substring = true) and hasAnyAncestor(hasTestTag(FEEDBACK_WORD)),
        useUnmergedTree = true,
    ).onFirst().getUnclippedBoundsInRoot()

    /**
     * A-AX05: a guest's offer of an account under the cards (FirstOpenView's `DeviceAccountRow`) has its words and its
     * "Sign in" capsule side by side, 32 apart at least, level, up to xxxLarge, and the capsule 10 under the words at
     * the accessibility sizes, at their start.
     */
    @Test
    fun atTheAccessibilitySizesAGuestsOfferPutsItsButtonUnderItsWords() {
        walk.fontScale = 1.3f
        walk.show(demo, session = AuthenticationState.Welcome) { isGuest = true }
        val title = walk.text("link.account.title")
        val kept = walk.text("native.guest.kept")
        val signIn = walk.text("arrival.signInTitle")
        revealOffer()
        val heading = inOffer(title)
        val words = inOffer(kept)
        val capsule = inOffer(signIn)
        assertTrue("The capsule 32 after the words: $capsule, $words", capsule.left - maxOf(heading.right, words.right) >= 32.dp - TOLERANCE)
        assertTrue("Level with the words: $capsule, $heading to $words", capsule.top < words.bottom && capsule.bottom > heading.top)
        walk.shot("A-AX05-guest-offer-beside", alsoDark = false)

        at(1.5f)
        revealOffer()
        val stackedHeading = inOffer(title)
        val stackedWords = inOffer(kept)
        val stackedCapsule = inOffer(signIn)
        assertTrue("The capsule 10 under the words: $stackedCapsule, $stackedWords", stackedCapsule.top - stackedWords.bottom >= 10.dp - TOLERANCE)
        // The capsule's words are 12 in from its edge, which starts where the words do.
        assertEquals("At the words' start", stackedHeading.left.value + CAPSULE_INSET, stackedCapsule.left.value, TOLERANCE.value)
        walk.shot("A-AX05-guest-offer-under", alsoDark = false)
    }

    private fun revealOffer() {
        compose.waitUntil(LONG_MILLIS) { OFFER in keys() }
        walk.node(PARCEL_LIST).performScrollToKey(OFFER)
        walk.settle()
    }

    private fun inOffer(text: String): DpRect = compose.onAllNodes(
        hasText(text) and hasAnyAncestor(hasTestTag(DEVICE_ACCOUNT_ROW)),
        useUnmergedTree = true,
    ).onFirst().getUnclippedBoundsInRoot()

    private companion object {
        const val FOLDER = "largetext"

        /** The regular size classes, as the deliveries take them. */
        val REGULAR_WIDTH = 600.dp
        val REGULAR_HEIGHT = 480.dp

        /** The list's keys of the invitation and of a guest's offer among the cards. */
        const val INVITATION = "invitation"
        const val OFFER = "deviceAccount"

        /** The demo's carrier, which Pip names as he asks. */
        const val CARRIER = "Chronopost"

        /** Long enough for the toast to come in and settle, well inside its twelve seconds. */
        const val TOAST_SETTLE_MILLIS = 600L

        /** The capsule's words in from its edge (`.padding(.horizontal, 12)`). */
        const val CAPSULE_INSET = 12f

        /** A column's width and edge, whole pixels from a width in points. */
        const val WIDTH_TOLERANCE = 1f
        val TOLERANCE: Dp = 1.dp
    }
}
