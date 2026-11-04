package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.width
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What the opened map's summary says in each state of the journey (A-ST30), as ParcelMapView's does: the end titled
 * "Delivered" once delivered where it is known, "To" where the parcel is headed, "Now" where it was last scanned, "Last
 * seen" where its newest scan has no place; one place and nowhere to go, only that end and no figures; a journey over
 * (delivered or returned), its length rather than how far "so far", and nothing "to go"; the views only where the
 * journey has a close-up of its own, side by side where they fit and one under the other where they don't. The
 * parcel's place pulsing until the journey is over is seen on a phone: a test's composition cancels animations that
 * never end.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelMapSummaryTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    /** The demo, `changed` in place of the demo's parcel of the same id, its map opened from `name`'s page and at rest. */
    private fun openMap(name: String, changed: Parcel? = null, fontScale: Float = 1f) {
        val parcels = DemoCards.parcels().map { if (it.id == changed?.id) changed else it }
        val source = FakeDeliveries(parcels, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = AuthenticationState.Demo, source = source)
            }
        }
        compose.waitForIdle()
        // Its card opened by its action: a card scrolled to the list's foot is under the tab bar, which takes a finger.
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
        compose.card(name).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
    }

    /** The ends of the journey the summary shows, as TalkBack hears each: its title, the place, and the country. */
    private fun ends(): List<String> = compose.onAllNodes(hasAnyAncestor(hasTestTag(MAP_BAR)), useUnmergedTree = true)
        .fetchSemanticsNodes()
        .mapNotNull { it.config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull() }
        .filter { ENDS.any { title -> it.startsWith("$title, ") } }

    /** The summary's figures: how far, how far to go and through how many countries. */
    private fun figures(): List<String> = compose.onAllNodes(hasAnyAncestor(hasTestTag(MAP_BAR)), useUnmergedTree = true)
        .fetchSemanticsNodes()
        .flatMap { node -> node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text } }
        .filter { it.contains(" km") || it.contains("countr") }
        .distinct()

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun deliveredWhereItIsKnownTheEndIsDeliveredAndTheJourneyHasItsLength() {
        openMap("Coffee beans")
        assertEquals(listOf("From, Zürich, Switzerland", "Delivered, Zürich, Switzerland"), ends())
        val figures = figures()
        assertEquals(1, figures.size)
        assertTrue(figures.single(), Regex("\\d+ km").matches(figures.single()))
        // Every stop within the close-up: one view, no switch.
        assertFalse(exists(MAP_VIEWS))
    }

    @Test
    fun aJourneyThroughSeveralCountriesCountsThem() {
        openMap("Matcha ritual")
        assertEquals(listOf("From, Kyoto, Japan", "Delivered, Zürich, Switzerland"), ends())
        val figures = figures()
        assertTrue(figures.toString(), Regex("[\\d,]+ km").matches(figures.first()))
        assertEquals("5 countries", figures.last())
        assertEquals(2, figures.size)
    }

    @Test
    fun headedSomewhereTheEndIsWhereItGoesWithHowFarItCameAndHasToGo() {
        openMap("Moon lamp", MapStates.headed())
        assertEquals(listOf("From, Berlin, Germany", "To, Switzerland"), ends())
        val figures = figures()
        assertEquals(2, figures.size)
        assertTrue(figures.toString(), Regex("\\d+ km so far").matches(figures[0]))
        assertTrue(figures.toString(), Regex("\\d+ km to go").matches(figures[1]))
        assertTrue(exists(MAP_VIEWS))
    }

    @Test
    fun scannedWhereItIsTheEndIsNow() {
        openMap("Moon lamp")
        assertEquals(listOf("From, Berlin, Germany", "Now, Neuenstein, Germany"), ends())
        val figures = figures()
        assertEquals(1, figures.size)
        assertTrue(figures.toString(), Regex("\\d+ km so far").matches(figures.single()))
    }

    @Test
    fun aNewestScanWithNoPlaceIsLastSeenWhereItLastWas() {
        openMap("Kind of Blue", MapStates.unplaced())
        assertEquals(listOf("From, Amsterdam, Netherlands", "Last seen, Eindhoven, Netherlands"), ends())
        assertTrue(figures().toString(), Regex("\\d+ km so far").matches(figures().single()))
    }

    @Test
    fun onePlaceAndNowhereToGoShowsOnlyThatEndAndNoFigures() {
        openMap("Trail weekend kit")
        assertEquals(listOf("Now, Buchs, Switzerland"), ends())
        assertEquals(emptyList<String>(), figures())
        assertFalse(exists(MAP_VIEWS))
    }

    @Test
    fun returnedTheJourneyHasItsLengthAndNothingToGo() {
        openMap("Moon lamp", MapStates.returned())
        assertEquals(listOf("From, Berlin, Germany", "Now, Berlin, Germany"), ends())
        val figures = figures()
        assertEquals(1, figures.size)
        assertTrue(figures.toString(), Regex("\\d+ km").matches(figures.single()))
    }

    private fun view(name: String): DpRect = compose.onNode(hasText(name) and hasClickAction()).getBoundsInRoot()

    @Test
    fun theViewsStandSideBySideWhereTheyFit() {
        openMap("Moon lamp")
        val journey = view("Journey")
        val nearby = view("Nearby")
        assertEquals(journey.top, nearby.top)
        assertTrue(nearby.left > journey.right)
    }

    /** As ParcelMapView's since its largest text on an iPad broke "Journey" in two: each as wide as the switch. */
    @Test
    @Config(qualifiers = "w320dp-h700dp-xhdpi")
    fun atTheLargestTextOnANarrowPhoneTheViewsStandOneUnderTheOther() {
        openMap("Moon lamp", fontScale = 2f)
        val journey = view("Journey")
        val nearby = view("Nearby")
        assertTrue("$journey over $nearby", nearby.top >= journey.bottom)
        assertEquals(journey.left, nearby.left)
        assertEquals(journey.width, nearby.width)
        val switch = compose.onNodeWithTag(MAP_VIEWS).getBoundsInRoot()
        assertEquals(switch.width, journey.width)
    }

    private companion object {
        /** The titles an end of the journey goes by, in English. */
        val ENDS = listOf("From", "To", "Now", "Last seen", "Delivered")
    }
}
