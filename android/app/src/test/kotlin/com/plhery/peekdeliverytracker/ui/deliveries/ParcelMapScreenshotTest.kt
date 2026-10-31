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
import androidx.compose.ui.test.swipe
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.map.WorldAtlases
import com.plhery.peekdeliverytracker.ui.screenshot
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The opened map of a parcel with both views, in the demo at the captures' moment: the journey and close by, light and
 * dark, on the iPhone the captures were taken on and on an iPad; partway through its flight from one to the other; and
 * turned by hand, neither view selected. The map at its first view is LivePassScreenshotTest's.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelMapScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View

    /** The demo's parcel with both views, its map opened, with the system's bars `top` and `bottom` tall. */
    private fun openMap(dark: Boolean = false, top: Int = 62, bottom: Int = 24) {
        val atlas = runBlocking { WorldAtlases.load().await() }!!
        val parcel = DemoCards.parcels().first { it.archivedAt == null && ParcelRoute(it, atlas, AppLanguage.En).hasNearView }
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides {}) {
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
        compose.screenshot("map_journey_light")
    }

    @Test
    fun closeByInDark() {
        openMap(dark = true)
        choose("Nearby")
        compose.screenshot("map_nearby_dark")
    }

    /** A third of a second into the flight from the journey to close by, lifted out over the way. */
    @Test
    fun onItsWay() {
        openMap()
        choose("Journey")
        compose.mainClock.autoAdvance = false
        compose.onNode(hasText("Nearby") and hasClickAction()).performClick()
        repeat(20) { compose.mainClock.advanceTimeByFrame() }
        compose.screenshot("map_flight_light")
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
        compose.screenshot("map_dragged_dark")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun theJourneyOnATablet() {
        openMap(top = 24, bottom = 20)
        choose("Journey")
        compose.screenshot("map_journey_tablet_light")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun closeByOnATabletInDark() {
        openMap(dark = true, top = 24, bottom = 20)
        choose("Nearby")
        compose.screenshot("map_nearby_tablet_dark")
    }
}

/** An iPad Pro 13-inch's 1032 × 1376 points, upright. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
