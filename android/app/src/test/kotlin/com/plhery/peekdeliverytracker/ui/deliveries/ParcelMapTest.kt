package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.core.GeoPoint
import com.plhery.peekdeliverytracker.core.GlobeCamera
import com.plhery.peekdeliverytracker.core.GlobeFlight
import com.plhery.peekdeliverytracker.core.GlobeZoomArea
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapOverlay
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipPlacement
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.draw.MapTextWidths
import com.plhery.peekdeliverytracker.ui.map.LocalMapCameraWatch
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
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

    /** Where Pip stood on the opened map each time it was composed. */
    private val pips = mutableListOf<PipPlacement?>()

    /** The window's size, where a test turns it; the activity's otherwise. */
    private var window by mutableStateOf<DpSize?>(null)
    private lateinit var root: View

    /** A parcel whose map has both views. */
    private val twoViews by lazy {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        DemoCards.parcels().first { it.archivedAt == null && ParcelRoute(it, atlas, AppLanguage.En).hasNearView }
    }

    private fun show(reduceMotion: Boolean = false, recreation: Recreation? = null) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        val content: @Composable () -> Unit = {
            root = LocalView.current
            CompositionLocalProvider(
                LocalDeliveriesState provides { deliveries = it },
                LocalMapCameraWatch provides { interactive, peek, camera, pip ->
                    if (interactive) {
                        opened += camera
                        pips += pip
                    } else if (peek) {
                        peeked += camera
                    }
                },
            ) {
                val sized = window
                if (sized == null) {
                    RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion)
                } else {
                    Box(Modifier.requiredSize(sized)) { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = reduceMotion) }
                }
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
    }

    /** The parcel's map, opened from its page's globe and at rest. */
    private fun openMap(name: String = twoViews.label, reduceMotion: Boolean = false, recreation: Recreation? = null) {
        show(reduceMotion, recreation)
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

    /** The window made again, what changed with it changing in `meanwhile`, and the map back up, as it was, and at rest. */
    private fun remake(recreation: Recreation, meanwhile: () -> Unit = {}) {
        recreation.recreate(meanwhile)
        var frames = 0
        while (!exists(MAP_COVER)) {
            check(frames++ < 5) { "The map didn't come back" }
            compose.mainClock.advanceTimeByFrame()
        }
        compose.mainClock.autoAdvance = true
        settle()
    }

    @Test
    fun theViewChosenStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        openMap(recreation = recreation)
        val (shown, other) = views()
        view(other).performClick()
        settle()
        val there = opened.last()
        remake(recreation)
        view(other).assertIsSelected()
        view(shown).assertIsNotSelected()
        assertEquals(there, opened.last())
    }

    @Test
    fun aMovedMapStaysWhereItWasPutThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        openMap(recreation = recreation)
        val (shown, other) = views()
        val start = opened.last()
        drag(Offset(-160f, 80f))
        settle()
        val moved = opened.last()
        assertNotEquals(start, moved)
        val from = opened.size
        remake(recreation)
        // There from its first frame back, not flown there nor back to its view.
        assertTrue(opened.size > from)
        assertTrue(opened.drop(from).all { it == moved })
        view(shown).assertIsNotSelected()
        view(other).assertIsNotSelected()
        // Turned on from there, and brought back by its view.
        drag(Offset(100f, 0f))
        settle()
        val degrees = 180 / PI / moved.scale
        assertEquals(moved.center.longitude - 50 * degrees, opened.last().center.longitude, 1e-6)
        view(shown).performClick()
        settle()
        assertEquals(start, opened.last())
        view(shown).assertIsSelected()
    }

    /** The system's bars and cutouts taking `safe` of the window's sides, in points. */
    private fun bars(safe: EdgeInsets) {
        val scale = root.resources.displayMetrics.density
        fun pixels(points: Double) = (points * scale).toInt()
        val insets = WindowInsetsCompat.Builder()
            .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, pixels(safe.top), 0, 0))
            .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(pixels(safe.leading), 0, pixels(safe.trailing), pixels(safe.bottom)))
            .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
            .build()
        ViewCompat.dispatchApplyWindowInsets(root, insets)
    }

    /** The opened map as it is drawn: the camera it looks from, Pip's spot, and the names, chips and Pip laid out there. */
    private class Drawn(val camera: GlobeCamera, val pip: PipPlacement?, val overlay: MapOverlay)

    /**
     * Matcha ritual's map close by, in a window `size` points whose system takes `safe`: what it draws, and the map laid
     * out again from what the screen shows (its size, the system's sides, the summary's height) with the app's names.
     */
    private fun drawn(size: DpSize, safe: EdgeInsets): Drawn {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        val parcel = DemoCards.parcel("Matcha ritual")
        val route = ParcelRoute(parcel, atlas, AppLanguage.En)
        val bar = compose.onNodeWithTag(MAP_BAR).getBoundsInRoot()
        val width = size.width.value.toDouble()
        val height = size.height.value.toDouble()
        val layout = MapCoverLayout.of(width, height, safe, (bar.bottom - bar.top).value.toDouble(), hinge = null)
        val camera = opened.last()
        val overlay = MapOverlay(
            route, camera, Size(width, height), layout.insets, MapLabels.All, sites = true, mode = ParcelRoute.Mode.Now, context = true,
            atlas = atlas, locale = AppLanguage.En.locale, countryName = { TrackingLocation.countryName(it, AppLanguage.En) },
            pip = PipRequest(PipMood.Joy, inset = true), measurer = MapTextWidths.of(compose.activity),
        )
        return Drawn(camera, pips.last(), overlay)
    }

    /**
     * Matcha ritual's map, Nearby chosen in an upright window `upright` points whose system takes `uprightSafe`, then
     * turned on its side to `side` and `sideSafe` through the window made again, the system's sides reaching it after
     * its first frame, as they do; then closed and opened again on its side, and Nearby chosen there. What the turned
     * map draws, and what the one opened on its side draws.
     */
    private fun turned(upright: DpSize, uprightSafe: EdgeInsets, side: DpSize, sideSafe: EdgeInsets): Pair<Drawn, Drawn> {
        val recreation = Recreation(compose)
        window = upright
        show(recreation = recreation)
        compose.runOnUiThread { bars(uprightSafe) }
        compose.waitForIdle()
        // Opened from the list's state: on the smallest phone the card would stand under the tab bar.
        compose.runOnUiThread { deliveries.navigator.open(DemoCards.parcel("Matcha ritual").id) }
        settle()
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
        view("Nearby").performClick()
        settle()
        remake(recreation) { window = side }
        compose.runOnUiThread { bars(sideSafe) }
        settle()
        view("Nearby").assertIsSelected()
        val turned = drawn(side, sideSafe)
        compose.onNodeWithTag(MAP_CLOSE).performClick()
        settle()
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        settle()
        if (views().first != "Nearby") view("Nearby").performClick()
        settle()
        return turned to drawn(side, sideSafe)
    }

    /** The box of the chip pointing to `name`, past the frame's edge. */
    private fun MapOverlay.chip(name: String): Rect = pointers.first { it.name == name }.let {
        Rect(it.center.x - it.width / 2, it.center.y - 13, it.width, 26.0)
    }

    private fun overlaps(a: Rect, b: Rect): Boolean = a.minX < b.maxX && a.maxX > b.minX && a.minY < b.maxY && a.maxY > b.minY

    /**
     * The iPhone's 956 × 440 points on its side (its Dynamic Island and home indicator taking 62 at each end and 21 at
     * the foot), Matcha ritual close by: Pip stands below the parcel's dot, between Zürich and the summary, Zürich's name
     * to the right of its dot, and the chip pointing to Kyoto above and to the right of it, clear of the names, as the
     * iPhone shows it; the same turned from upright as opened on its side.
     */
    @Test
    @Config(qualifiers = "w956dp-h956dp-xxhdpi")
    fun turnedOnItsSideTheMapStandsPipBelowTheParcelAsTheIphoneDoes() {
        val (turned, opened) = turned(
            DpSize(440.dp, 956.dp), EdgeInsets(top = 62.0, leading = 0.0, bottom = 34.0, trailing = 0.0),
            DpSize(956.dp, 440.dp), EdgeInsets(top = 0.0, leading = 62.0, bottom = 21.0, trailing = 62.0),
        )
        assertEquals(opened.camera, turned.camera)
        assertEquals(opened.pip, turned.pip)
        val pip = checkNotNull(turned.pip)
        assertEquals(pip, turned.overlay.pip)
        val dot = turned.overlay.dots.first { it.kind == MapOverlay.DotKind.Current }.point
        assertTrue(pip.below)
        val zurich = turned.overlay.labels.first { it.text == "Zürich" }.frame
        assertTrue(zurich.minX > dot.x)
        assertTrue(zurich.minY < dot.y && zurich.maxY > dot.y)
        val kyoto = turned.overlay.chip("Kyoto")
        assertTrue(kyoto.maxY < dot.y - 20 && kyoto.midX > dot.x)
        assertTrue(turned.overlay.labels.none { overlaps(it.frame, kyoto) })
        assertFalse(overlaps(pip.box, kyoto))
    }

    /**
     * The smallest phone's 640 × 360 points on its side, its status bar 24 tall and its buttons 48 wide at the trailing
     * end: under the close button's row and above the summary there is no room below the parcel's dot for Pip, and he
     * stands beside it, over the chip pointing to Kyoto, where the iPhone's own layout puts him in a frame this short;
     * the same turned from upright as opened on its side.
     */
    @Test
    @Config(qualifiers = "w640dp-h640dp-xhdpi")
    fun turnedOnASmallPhonesSideTheMapStandsPipBesideTheParcel() {
        val (turned, opened) = turned(
            DpSize(360.dp, 640.dp), EdgeInsets(top = 24.0, leading = 0.0, bottom = 48.0, trailing = 0.0),
            DpSize(640.dp, 360.dp), EdgeInsets(top = 24.0, leading = 0.0, bottom = 0.0, trailing = 48.0),
        )
        assertEquals(opened.camera, turned.camera)
        assertEquals(opened.pip, turned.pip)
        val pip = checkNotNull(turned.pip)
        assertEquals(pip, turned.overlay.pip)
        assertFalse(pip.below)
        assertEquals(-1, pip.side)
        assertTrue(overlaps(pip.box, turned.overlay.chip("Kyoto")))
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
        // Close in, past two and a half times the whole globe, it no longer drifts back: the point between the
        // fingers stays under them.
        val (size, area) = zoomArea()
        assertTrue("Zoomed to ${zoomed.scale}", zoomed.scale >= area.viewport / 2 * 2.5)
        val x = size.width / 2
        val y = size.height * 0.3
        assertEquals(x + (start.offset.x - x) * 2, zoomed.offset.x, 1e-3)
        assertEquals(y + (start.offset.y - y) * 2, zoomed.offset.y, 1e-3)

        // Each pinch 35 times: twice reaches a town 20 km across, and a third goes no closer.
        pinch(from = 20f, to = 700f)
        pinch(from = 20f, to = 700f)
        val closest = opened.last().scale
        pinch(from = 20f, to = 700f)
        assertEquals(closest, opened.last().scale, 0.0)
        // And out again, to nine tenths of the whole globe and no further, drifted back to the middle of what the
        // close button and the summary leave of the view.
        pinch(from = 700f, to = 20f)
        pinch(from = 700f, to = 20f)
        val farthest = opened.last()
        pinch(from = 700f, to = 20f)
        assertEquals(farthest.scale, opened.last().scale, 0.0)
        assertEquals(area.viewport / 2 * 0.9, farthest.scale, 1e-9)
        assertEquals(area.middle.x, farthest.offset.x, 1e-9)
        assertEquals(area.middle.y, farthest.offset.y, 1e-9)
        assertEquals(GeoPoint.EARTH_KILOMETRES / 20 / 0.45, closest / farthest.scale, 1e-6)
    }

    /**
     * The opened map's size, and where zooming settles its globe once it no longer fills the view and how much view
     * there is: what the system's bars, the close button and the summary leave, as the map screen works it out.
     */
    private fun zoomArea(): Pair<Size, GlobeZoomArea> {
        val density = compose.density.density.toDouble()
        val decor = compose.activity.window.decorView
        // Compose's `safeDrawing`.
        val types = WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime()
        val system = ViewCompat.getRootWindowInsets(decor)?.getInsets(types) ?: Insets.NONE
        val safe = EdgeInsets(system.top / density, system.left / density, system.bottom / density, system.right / density)
        val shown = map.fetchSemanticsNode().size
        val size = Size(shown.width / density, shown.height / density)
        val bar = compose.onNodeWithTag(MAP_BAR).fetchSemanticsNode().size.height / density
        val layout = MapCoverLayout.of(size.width, size.height, safe, bar, null)
        return size to GlobeZoomArea.of(size, layout.insets)
    }

    /**
     * Where the opened map looked from as `other` view is chosen: where it began, the camera it showed at each frame
     * after, and where it came to rest.
     */
    private fun flight(reduceMotion: Boolean): Triple<GlobeCamera, List<GlobeCamera>, GlobeCamera> {
        openMap(reduceMotion = reduceMotion)
        val (_, other) = views()
        val start = opened.last()
        compose.mainClock.autoAdvance = false
        view(other).performClick()
        val frames = List(120) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
            opened.last()
        }
        compose.mainClock.autoAdvance = true
        settle()
        view(other).assertIsSelected()
        return Triple(start, frames, opened.last())
    }

    /** Whether `camera` is `expected`, but for the last digits the arithmetic leaves. */
    private fun near(expected: GlobeCamera, camera: GlobeCamera): Boolean =
        abs(camera.center.latitude - expected.center.latitude) < 1e-9 &&
            abs(camera.center.longitude - expected.center.longitude) < 1e-9 &&
            abs(camera.scale / expected.scale - 1) < 1e-9 &&
            abs(camera.offset.x - expected.offset.x) < 1e-6 &&
            abs(camera.offset.y - expected.offset.y) < 1e-6

    private fun assertNear(message: String, expected: GlobeCamera, camera: GlobeCamera) =
        assertTrue("$message: $camera, not $expected", near(expected, camera))

    @Test
    fun aViewFliesTheMapThereAlongTheWay() {
        val (start, frames, end) = flight(reduceMotion = false)
        assertNotEquals(start, end)
        val (size, _) = zoomArea()
        val flight = GlobeFlight(start, end, max(size.width, size.height))
        // The flight's clock starts at the frame that first shows it, where the map began: then each frame shows the
        // camera that far through its time, along its path, eased in and out.
        val off = frames.indexOfFirst { !near(start, it) }
        assertTrue(off >= 0)
        val landing = ceil(flight.duration * 1e9 / FRAME_NANOS).toInt()
        for (k in 1..landing) {
            assertNear("Frame $k of $landing", flight.camera(k * FRAME_NANOS / 1e9 / flight.duration), frames[off + k - 1])
        }
        // There as its time is up and no sooner, then at rest.
        assertFalse(near(end, frames[off + landing - 2]))
        assertNear("Landed", end, frames[off + landing - 1])
        assertTrue(frames.drop(off + landing).all { it == end })
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

/** The test clock's frame, in nanoseconds. */
private const val FRAME_NANOS = 16_000_000L
