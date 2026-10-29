package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Application
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The deliveries' list on a tablet whose window a test narrows and widens: the arrangements and the turns between
 * them, the parcel chosen beside the list, the keys, a link's parcel, the demo's bar, the foot and what is counted.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w1032dp-h1376dp-xhdpi")
class DeliveryListTest {
    @get:Rule
    val compose = createComposeRule()

    private var width by mutableStateOf(1032.dp)
    private lateinit var view: View
    private lateinit var source: FakeDeliveries
    private val views = mutableListOf<String>()

    /** The deliveries in a window `width` wide, the demo's parcels in `session` unless a test gives its own. */
    private fun show(
        width: Dp = 1032.dp,
        session: AuthenticationState = AuthenticationState.Demo,
        inbox: DeliveriesInbox? = null,
        recreation: Recreation? = null,
    ) {
        this.width = width
        source = FakeDeliveries(DemoCards.parcels(), session)
        val content = @androidx.compose.runtime.Composable {
            view = LocalView.current
            Box(Modifier.width(this.width).fillMaxHeight()) {
                RootHost(state = session, onView = { views += it }, source = source, inbox = inbox)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun resize(to: Dp) {
        width = to
        compose.waitForIdle()
    }

    private fun sideBySide(): Boolean = compose.onAllNodes(hasTestTag(PAGE_COLUMN)).fetchSemanticsNodes().isNotEmpty()

    /** The parcel's page shows the parcel named `name`. */
    private fun assertPageShows(name: String) {
        compose.onNode(hasText(name, substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).assertExists()
    }

    private fun assertNoPage() {
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
    }

    /** A key pressed and let go on a hardware keyboard, through the window as the system sends it. */
    private fun press(key: Int) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, 0))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, 0))
        }
        compose.waitForIdle()
    }

    @Test
    fun theWindowTurnsSideBySidePastTheLineAndBackWithSomeSlack() {
        // The list and the parcel need 320 + 390; the window goes 20 past that before it turns either way.
        show(width = 700.dp)
        assertEquals(false, sideBySide())
        assertNoPage()
        resize(729.dp)
        assertEquals(false, sideBySide())

        resize(730.dp)
        assertEquals(true, sideBySide())
        // Nobody chose a parcel: the first card's shows beside the list, ringed.
        assertPageShows("New trainers")
        compose.card("New trainers").assertIsSelected()
        compose.card("Birthday gift").assertIsNotSelected()
        resize(700.dp)
        assertEquals(true, sideBySide())

        resize(689.dp)
        assertEquals(false, sideBySide())
        // The parcel nobody chose goes back into the list.
        assertNoPage()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        // Showing a parcel nobody chose is no screen.
        assertEquals(emptyList<String>(), views)
    }

    /** How far the list is scrolled, in pixels. */
    private fun scrolled(): Float =
        compose.onNodeWithTag(PARCEL_LIST).fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()

    @Test
    fun turningKeepsTheListAtItsTopOrTheCardAtItsTopThere() {
        show(width = 700.dp)
        resize(730.dp)
        // At its top, the list stays there rather than hide the overview under the bar for the first card.
        assertEquals(0f, scrolled(), 0f)

        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(cardKey("remaining", DemoCards.parcel("Trail weekend kit").id))
        compose.waitForIdle()
        val top = compose.card("Trail weekend kit").getBoundsInRoot().top.value
        resize(689.dp)
        // The card at the list's top comes back there in the list laid out anew.
        assertEquals(top, compose.card("Trail weekend kit").getBoundsInRoot().top.value, 1f)
        assertEquals(true, scrolled() > 0f)
    }

    @Test
    fun aParcelChosenBesideTheListComesOverItAsTheWindowNarrows() {
        show()
        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        compose.card("New trainers").assertIsNotSelected()
        assertEquals(listOf("parcel"), views)

        resize(600.dp)
        assertEquals(false, sideBySide())
        assertPageShows("Birthday gift")
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()

        resize(1032.dp)
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        // The same parcel all along: counted once.
        assertEquals(listOf("parcel"), views)
    }

    @Test
    fun theParcelShownBesideTheListGoesToTheNextCardWhenItGoes() {
        show()
        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        source.parcels.value = source.parcels.value.filterNot { it.label.startsWith("Birthday gift") }
        compose.waitForIdle()
        assertPageShows("Belgian chocolate")
        compose.card("Belgian chocolate").assertIsSelected()
    }

    @Test
    fun upAndDownStepThroughTheCardsBesideTheListAndReturnChoosesTheOneShown() {
        show()
        assertPageShows("New trainers")
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Belgian chocolate")
        press(KeyEvent.KEYCODE_DPAD_UP)
        assertPageShows("Birthday gift")
        assertEquals(listOf("parcel", "parcel", "parcel"), views)
        // Past the first card ↑ does nothing.
        press(KeyEvent.KEYCODE_DPAD_UP)
        press(KeyEvent.KEYCODE_DPAD_UP)
        assertPageShows("New trainers")
        assertEquals(4, views.size)
    }

    @Test
    fun theOpenParcelAndTheArchiveComeBackAfterTheWindowIsMadeAgainCountedOnce() {
        val recreation = Recreation(compose)
        show(width = 600.dp, recreation = recreation)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNodeWithContentDescription("Archived", substring = true).performClick()
        compose.waitForIdle()
        compose.openCard("Moon lamp")
        assertPageShows("Moon lamp")
        assertEquals(listOf("parcel"), views)
        assertEquals(listOf("archive-open"), source.actions)

        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertPageShows("Moon lamp")
        assertEquals(listOf("parcel"), views)

        // Back on the list, the archive is still open.
        compose.onNodeWithContentDescription("Deliveries").performClick()
        compose.waitForIdle()
        assertNoPage()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Linen weekend bag"))
        assertEquals(listOf("parcel", "deliveries"), views)
        assertEquals(listOf("archive-open"), source.actions)
    }

    @Test
    fun aParcelsLinkOpensItOnTheDeliveries() {
        val inbox = DeliveriesInbox()
        show(width = 600.dp, inbox = inbox)
        compose.onNodeWithContentDescription("Friends").performClick()
        compose.waitForIdle()
        views.clear()

        compose.runOnUiThread { inbox.open(DemoCards.parcel("Kind of Blue").id) }
        compose.waitForIdle()
        compose.onNodeWithContentDescription("Deliveries").assertIsSelected()
        assertPageShows("Kind of Blue")
        assertEquals(null, inbox.parcel.value)
        assertEquals(listOf("deliveries", "parcel"), views)
    }

    @Test
    fun exitingTheDemoShowsTheWelcome() {
        show(width = 600.dp)
        compose.onNodeWithTag(DEMO_EXIT).performClick()
        compose.waitForIdle()
        assertEquals(1, source.welcomes)
    }

    @Test
    fun besideAParcelTheRefreshButtonRefreshes() {
        show()
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        compose.waitForIdle()
        assertEquals(1, source.refreshes)
        resize(600.dp)
        compose.onNodeWithTag(REFRESH_BUTTON).assertDoesNotExist()
    }

    @Test
    fun theSearchButtonShowsAndHidesTheSearch() {
        show(width = 600.dp)
        compose.onNodeWithTag(SEARCH_ROW).assertDoesNotExist()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_ROW).assertExists()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_ROW).assertDoesNotExist()
    }

    @Test
    fun theFootsLinksOpenInTheBrowser() {
        show(width = 600.dp)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNodeWithTag("$FOOT.privacy", useUnmergedTree = true).performClick()
        compose.waitForIdle()
        val started = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity
        assertEquals(Intent.ACTION_VIEW, started.action)
        assertEquals("https://example.com/privacy", started.dataString)
    }
}
