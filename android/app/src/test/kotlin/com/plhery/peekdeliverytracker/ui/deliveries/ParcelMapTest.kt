package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.compose.ui.test.swipe
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.GeoPoint
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import kotlin.math.PI
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The globe under the fingers: on the opened map a drag turns it and a pinch zooms it, freeing it from both views
 * (A-G12, A-G14), a view flies it there (A-AN46), and the map going down takes no touch; on the page's pass two fingers
 * zoom its map, which flies back as they lift, and one is left to the page (A-G13).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelMapTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    /** Each camera a map that moves was composed with: the opened map's, then the page pass's. */
    private val opened = mutableListOf<GlobeCamera>()
    private val peeked = mutableListOf<GlobeCamera>()

    /** A parcel whose map has both views. */
    private val twoViews by lazy {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        DemoCards.parcels().first { it.archivedAt == null && ParcelRoute(it, atlas, AppLanguage.En).hasNearView }
    }

    private fun show(reduceMotion: Boolean = false) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(
                LocalDeliveriesState provides { deliveries = it },
                LocalMapCameraWatch provides { interactive, camera -> (if (interactive) opened else peeked) += camera },
            ) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
    }

    /** The parcel's map, opened from its page's globe and at rest. */
    private fun openMap(name: String = twoViews.label, reduceMotion: Boolean = false) {
        show(reduceMotion)
        compose.openCard(name)
        settle()
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun view(name: String): SemanticsNodeInteraction = compose.onNode(hasText(name) and hasClickAction())

    /** The view the map opened at, and the other. */
    private fun views(): Pair<String, String> {
        val journeyShown = view("Journey").fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected) == true
        return if (journeyShown) "Journey" to "Nearby" else "Nearby" to "Journey"
    }

    private val map get() = compose.onNodeWithTag(MAP_COVER_MAP)

    /** One finger across the opened map by `by` pixels, from high on it, clear of the summary. */
    private fun drag(by: Offset) {
        map.performTouchInput { swipe(Offset(centerX, height * 0.3f), Offset(centerX, height * 0.3f) + by, durationMillis = 300) }
    }

    /** Two fingers on the opened map, `from` apart and then `to` apart across it, about its middle high on it. */
    private fun pinch(from: Float, to: Float) {
        map.performTouchInput {
            val middle = Offset(centerX, height * 0.3f)
            pinch(
                middle - Offset(from / 2, 0f), middle - Offset(to / 2, 0f),
                middle + Offset(from / 2, 0f), middle + Offset(to / 2, 0f),
                durationMillis = 300,
            )
        }
        settle()
    }

    @Test
    fun aDragTurnsTheGlobeUnderTheFingerAndNeitherViewStaysSelected() {
        openMap()
        val (shown, other) = views()
        val start = opened.last()
        val before = compose.drawn(map)
        drag(Offset(-120f, 60f))
        settle()
        // 120 pixels are 60 points on this phone: the globe turns as far as a point under the finger goes.
        val degrees = 180 / PI / start.scale
        val turned = opened.last()
        assertEquals(start.center.longitude + 60 * degrees, turned.center.longitude, 1e-6)
        assertEquals(start.center.latitude + 30 * degrees, turned.center.latitude, 1e-6)
        assertEquals(start.scale, turned.scale, 0.0)
        assertFalse(before.contentEquals(compose.drawn(map)))
        view(shown).assertIsNotSelected()
        view(other).assertIsNotSelected()
        // Still one image to TalkBack, with nothing to adjust or scroll.
        assertFalse(SemanticsActions.ScrollBy in map.fetchSemanticsNode().config)
        assertFalse(SemanticsActions.SetProgress in map.fetchSemanticsNode().config)
        assertTrue(exists(MAP_COVER))
    }

    @Test
    fun aDragOrAPinchFromTheSummaryLeavesTheMapAsItWas() {
        openMap()
        val (shown, other) = views()
        val start = opened.last()
        val from = opened.size
        val bar = compose.onNodeWithTag(MAP_BAR)
        // From the summary's first words, up across the map.
        bar.performTouchInput { swipe(Offset(width * 0.2f, height * 0.12f), Offset(width * 0.2f, -400f), durationMillis = 300) }
        settle()
        bar.performTouchInput {
            pinch(
                Offset(width * 0.4f, height * 0.12f), Offset(width * 0.1f, -300f),
                Offset(width * 0.6f, height * 0.12f), Offset(width * 0.9f, -300f),
                durationMillis = 300,
            )
        }
        settle()
        assertTrue(opened.drop(from).all { it == start })
        view(shown).assertIsSelected()
        view(other).assertIsNotSelected()
    }

    /** The window's accessibility nodes, which TalkBack reads, with TalkBack on. */
    private fun talkBack(): AccessibilityNodeProvider {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view.accessibilityNodeProvider
    }

    private fun AccessibilityNodeProvider.info(id: Int): AccessibilityNodeInfo {
        var info: AccessibilityNodeInfo? = null
        compose.runOnUiThread { info = createAccessibilityNodeInfo(id) }
        return checkNotNull(info)
    }

    private fun AccessibilityNodeProvider.info(node: SemanticsNodeInteraction): AccessibilityNodeInfo = info(node.fetchSemanticsNode().id)

    /**
     * What TalkBack says of the node with `id` as it stops there: its own description or words, else those of each of
     * its children it doesn't stop on, in their order.
     */
    private fun AccessibilityNodeProvider.spoken(id: Int): List<String> {
        val info = info(id)
        (info.contentDescription ?: info.text)?.let { return listOf(it.toString()) }
        val childId = AccessibilityNodeInfo::class.java.getMethod("getChildId", Int::class.javaPrimitiveType)
        return (0 until info.childCount).flatMap { index ->
            // The child's virtual id, in the high half of the node id.
            val child = ((childId.invoke(info, index) as Long) ushr 32).toInt()
            if (info(child).isScreenReaderFocusable) emptyList() else spoken(child)
        }
    }

    @Test
    fun talkBackHearsTheViewsNameAsItComesToThemAndTheOneShownSelected() {
        openMap()
        val (shown, other) = views()
        val talkBack = talkBack()
        // The group is no stop of its own and says nothing: TalkBack goes from the summary to the first view, which
        // says the group's name first, as VoiceOver says it coming into the group.
        val group = talkBack.info(compose.onNodeWithTag(MAP_VIEWS, useUnmergedTree = true))
        assertFalse(group.isScreenReaderFocusable)
        assertEquals(null, group.contentDescription ?: group.text)
        assertEquals(2, group.collectionInfo.rowCount * group.collectionInfo.columnCount)
        val journey = view("Journey").fetchSemanticsNode().id
        val nearby = view("Nearby").fetchSemanticsNode().id
        assertTrue(talkBack.info(journey).isScreenReaderFocusable)
        assertTrue(talkBack.info(nearby).isScreenReaderFocusable)
        assertEquals(listOf("Map view", "Journey"), talkBack.spoken(journey))
        assertEquals(listOf("Nearby"), talkBack.spoken(nearby))
        assertEquals("Selected", talkBack.info(view(shown)).stateDescription)
        assertEquals("Not selected", talkBack.info(view(other)).stateDescription)
        // Moved by hand, neither is.
        drag(Offset(120f, 0f))
        settle()
        assertEquals("Not selected", talkBack.info(view(shown)).stateDescription)
    }

    @Test
    fun theViewShownBringsAMovedMapBack() {
        openMap()
        val (shown, _) = views()
        val start = opened.last()
        drag(Offset(200f, 0f))
        settle()
        assertNotEquals(start, opened.last())
        view(shown).performClick()
        settle()
        assertEquals(start, opened.last())
        view(shown).assertIsSelected()
    }

    @Test
    fun aPinchZoomsAboutWhereTheFingersStoodFromTheWholeGlobeDownToATown() {
        openMap()
        val (shown, other) = views()
        val start = opened.last()
        pinch(from = 200f, to = 400f)
        val zoomed = opened.last()
        assertEquals(2.0, zoomed.scale / start.scale, 1e-6)
        assertEquals(start.center, zoomed.center)
        view(shown).assertIsNotSelected()
        view(other).assertIsNotSelected()
        // Close in, the globe no longer drifts back: the point between the fingers stays under them.
        val bounds = map.getBoundsInRoot()
        val middle = (bounds.bottom - bounds.top).value * 0.3
        val x = (bounds.right - bounds.left).value / 2.0
        if (zoomed.scale > 2.5 * 220) {
            assertEquals(x + (start.offset.x - x) * 2, zoomed.offset.x, 1e-3)
            assertEquals(middle + (start.offset.y - middle) * 2, zoomed.offset.y, 1e-3)
        }

        // Each pinch 35 times: twice reaches a town 20 km across, and a third goes no closer.
        pinch(from = 20f, to = 700f)
        pinch(from = 20f, to = 700f)
        val closest = opened.last().scale
        pinch(from = 20f, to = 700f)
        assertEquals(closest, opened.last().scale, 0.0)
        // And out again, to nine tenths of the whole globe and no further.
        pinch(from = 700f, to = 20f)
        pinch(from = 700f, to = 20f)
        val farthest = opened.last().scale
        pinch(from = 700f, to = 20f)
        assertEquals(farthest, opened.last().scale, 0.0)
        assertEquals(GeoPoint.EARTH_KILOMETRES / 20 / 0.45, closest / farthest, 1e-6)
    }

    /** The cameras the opened map was composed with, frame by frame, as `other` view is chosen, and where it began. */
    private fun flight(reduceMotion: Boolean): Triple<GlobeCamera, List<GlobeCamera>, GlobeCamera> {
        openMap(reduceMotion = reduceMotion)
        val (_, other) = views()
        val start = opened.last()
        compose.mainClock.autoAdvance = false
        val from = opened.size
        view(other).performClick()
        repeat(120) { compose.mainClock.advanceTimeByFrame() }
        compose.mainClock.autoAdvance = true
        settle()
        view(other).assertIsSelected()
        return Triple(start, opened.drop(from), opened.last())
    }

    @Test
    fun aViewFliesTheMapThereAlongTheWay() {
        val (start, frames, end) = flight(reduceMotion = false)
        assertNotEquals(start, end)
        val between = frames.filter { it != start && it != end }.distinct()
        assertTrue("Cameras on the way: ${between.size}", between.size >= 20)
    }

    @Test
    fun underReduceMotionAViewPutsTheMapThereAtOnce() {
        val (start, frames, end) = flight(reduceMotion = true)
        assertNotEquals(start, end)
        assertTrue(frames.all { it == start || it == end })
    }

    @Test
    fun aViewTappedAsTheMapGoesDownFliesNothing() {
        openMap()
        val (_, other) = views()
        val start = opened.last()
        val button = view(other).getBoundsInRoot()
        val at = Offset(((button.left + button.right) / 2).value, ((button.top + button.bottom) / 2).value) * compose.density.density
        val from = opened.size
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(MAP_CLOSE).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.onRoot().performTouchInput {
            down(at)
            up()
        }
        compose.mainClock.autoAdvance = true
        settle()
        assertFalse(exists(MAP_COVER))
        assertTrue(opened.drop(from).all { it == start })
    }

    @Test
    fun twoFingersOnThePassZoomItsMapAndItFliesBackAsTheyLift() {
        show()
        compose.openCard("Birthday gift")
        settle()
        val route = compose.onNodeWithTag(PAGE_ROUTE)
        val top = route.getBoundsInRoot().top
        val start = peeked.last()
        route.performTouchInput {
            down(0, center - Offset(40f, 0f))
            down(1, center + Offset(40f, 0f))
        }
        route.performTouchInput {
            moveTo(0, center - Offset(160f, 0f))
            moveTo(1, center + Offset(160f, 0f))
        }
        compose.waitForIdle()
        assertEquals(4.0, peeked.last().scale / start.scale, 1e-6)
        route.performTouchInput {
            up(0)
            up(1)
        }
        settle()
        assertEquals(start, peeked.last())
        // The page stayed where it was, and the map stayed shut.
        assertEquals(top, route.getBoundsInRoot().top)
        assertFalse(exists(MAP_COVER))
    }

    @Test
    fun oneFingerOnThePassScrollsThePage() {
        show()
        compose.openCard("Birthday gift")
        settle()
        val route = compose.onNodeWithTag(PAGE_ROUTE)
        val top = route.getBoundsInRoot().top
        val start = peeked.last()
        route.performTouchInput { swipe(center, center - Offset(0f, 300f), durationMillis = 300) }
        settle()
        assertTrue(route.getBoundsInRoot().top < top)
        assertTrue(peeked.all { it == start })
        assertFalse(exists(MAP_COVER))
    }
}
