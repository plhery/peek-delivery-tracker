package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.pinch
import androidx.compose.ui.test.swipe
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.Size
import com.plhery.peekdeliverytracker.core.WorldDetailPack
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.map.LocalMapSceneWatch
import com.plhery.peekdeliverytracker.ui.map.MapScene
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.screenshot
import kotlin.math.abs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The opened map of a parcel with both views, in the demo at the captures' moment: the journey and close by, light and
 * dark, on the iPhone the captures were taken on and on an iPad; a parcel from across the world partway through its
 * flight from one to the other; turned by hand, neither view selected; and the demo's nearest journey up close, with
 * the rivers, lakes, built-up areas, roads and towns of the detail tiles. The map at its first view is
 * LivePassScreenshotTest's.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelMapScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View

    /** The opened map's last picture. */
    private var drawn: MapScene? = null

    /**
     * The demo's parcel named `name`, by default its first with both views, its map opened, with the system's bars
     * `top` and `bottom` tall.
     */
    private fun openMap(dark: Boolean = false, top: Int = 62, bottom: Int = 24, name: String? = null) {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        val parcel = name?.let { DemoCards.parcel(it) }
            ?: DemoCards.parcels().first { it.archivedAt == null && ParcelRoute(it, atlas, AppLanguage.En).hasNearView }
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides {}, LocalMapSceneWatch provides { interactive, scene -> if (interactive) drawn = scene }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = false, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (top * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (bottom * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
        compose.openCard(parcel.label)
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        compose.mainClock.advanceTimeBy(2_000)
    }

    /** Chooses the view named `name`, and lets the map land there. */
    private fun choose(name: String) {
        compose.onNode(hasText(name) and hasClickAction()).performClick()
        compose.mainClock.advanceTimeBy(2_000)
    }

    @Test
    fun theJourney() {
        openMap()
        choose("Journey")
        compose.everyTileDrawn { drawn }
        compose.screenshot("map_journey_light")
    }

    @Test
    fun closeByInDark() {
        openMap(dark = true)
        choose("Nearby")
        compose.everyTileDrawn { drawn }
        compose.screenshot("map_nearby_dark")
    }

    /**
     * About halfway through the flight of a parcel that came from Japan, from its journey to close by: the globe
     * turns toward Zürich as it closes in, Pip and the faint names waiting for it to land. It isn't lifted out: the
     * way between the two views' middles is shorter than the window, and `GlobeCamera.flight` lifts only a longer one,
     * as from a map moved far away by hand.
     */
    @Test
    fun onItsWayAcrossTheWorld() {
        openMap(name = "Matcha ritual")
        choose("Journey")
        compose.mainClock.autoAdvance = false
        compose.onNode(hasText("Nearby") and hasClickAction()).performClick()
        repeat(48) { compose.mainClock.advanceTimeByFrame() }
        compose.screenshot("map_flight_world_light")
        compose.mainClock.autoAdvance = true
    }

    /** Turned by a finger: neither view selected, and Pip where he stood. */
    @Test
    fun turnedByHandInDark() {
        openMap(dark = true)
        choose("Journey")
        compose.onNodeWithTag(MAP_COVER_MAP).performTouchInput {
            swipe(Offset(centerX, height * 0.3f), Offset(centerX - 160f, height * 0.3f + 80f), durationMillis = 300)
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.everyTileDrawn { drawn }
        compose.screenshot("map_dragged_dark")
    }

    /**
     * Coffee beans' map, the demo's nearest journey, from Zürich to Härkingen and back, zoomed by two fingers about the
     * parcel's place until it is `kilometres` across its width, within 3 %, once each tile it shows has come.
     */
    private fun closeUp(kilometres: Double, name: String, dark: Boolean = false, top: Int = 62, bottom: Int = 24) {
        openMap(dark = dark, top = top, bottom = bottom, name = "Coffee beans")
        compose.waitForIdle()
        val scene = checkNotNull(drawn)
        val map = compose.onNodeWithTag(MAP_COVER_MAP)
        val density = compose.density.density
        val shown = map.fetchSemanticsNode().size
        val by = WorldDetailPack.spanKilometres(scene.camera, Size(shown.width / density.toDouble(), shown.height / density.toDouble())) / kilometres
        if (abs(by - 1) > 0.02) {
            val dot = checkNotNull(scene.current)
            map.performTouchInput {
                val middle = Offset(dot.x.toFloat() * density, dot.y.toFloat() * density)
                val from = if (by > 1) 120f else 480f
                val to = from * by.toFloat()
                pinch(middle - Offset(from / 2, 0f), middle - Offset(to / 2, 0f), middle + Offset(from / 2, 0f), middle + Offset(to / 2, 0f), 300)
            }
            compose.mainClock.advanceTimeBy(1_000)
        }
        val span = compose.everyTileDrawn { drawn }
        assertEquals("$span km", kilometres, span, kilometres * 0.03)
        compose.screenshot(name)
    }

    @Test
    fun upCloseAt300Kilometres() = closeUp(300.0, "map_close_300_light")

    @Test
    fun upCloseAt300KilometresInDark() = closeUp(300.0, "map_close_300_dark", dark = true)

    @Test
    fun upCloseAt150Kilometres() = closeUp(150.0, "map_close_150_light")

    @Test
    fun upCloseAt150KilometresInDark() = closeUp(150.0, "map_close_150_dark", dark = true)

    @Test
    fun upCloseAt40Kilometres() = closeUp(40.0, "map_close_40_light")

    @Test
    fun upCloseAt40KilometresInDark() = closeUp(40.0, "map_close_40_dark", dark = true)

    @Test
    @Config(qualifiers = TABLET)
    fun upCloseAt150KilometresOnATablet() = closeUp(150.0, "map_close_150_tablet_light", top = 24, bottom = 20)

    @Test
    @Config(qualifiers = TABLET)
    fun upCloseAt40KilometresOnATabletInDark() = closeUp(40.0, "map_close_40_tablet_dark", dark = true, top = 24, bottom = 20)

    @Test
    @Config(qualifiers = TABLET)
    fun theJourneyOnATablet() {
        openMap(top = 24, bottom = 20)
        choose("Journey")
        compose.everyTileDrawn { drawn }
        compose.screenshot("map_journey_tablet_light")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun closeByOnATabletInDark() {
        openMap(dark = true, top = 24, bottom = 20)
        choose("Nearby")
        compose.everyTileDrawn { drawn }
        compose.screenshot("map_nearby_tablet_dark")
    }
}

/** An iPad Pro 13-inch's 1032 × 1376 points, upright. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
