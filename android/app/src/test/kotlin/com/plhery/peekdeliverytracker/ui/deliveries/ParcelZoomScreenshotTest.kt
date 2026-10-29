package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's page zooming out of its card on the iPhone the captures were taken on (440 × 956 points at three pixels
 * to the point), in the demo, light and dark: three frames after New trainers' Next up card is tapped, the page a quarter of
 * the way out of it over the receding list with the card's picture half gone, and the page dragged 200 points down,
 * shrinking about the finger.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelZoomScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState

    /** The demo's list with the iPhone's bars, the status bar 62 points tall, the gesture bar 24, its clock paused. */
    private fun show(dark: Boolean) {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = false, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (62 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (24 * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    /** The clock moved on `milliseconds` a frame at a time. */
    private fun advance(milliseconds: Long) {
        repeat(((milliseconds + FRAME - 1) / FRAME).toInt()) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
    }

    /** Taps New trainers' card and moves the clock on to the frame its page sets off in. */
    private fun open() {
        compose.card("New trainers").performClick()
        var frames = 0
        while (deliveries.zoom.covered == null) {
            check(frames++ < 10) { "The page didn't set off" }
            advance(FRAME)
        }
    }

    private fun opening(dark: Boolean) {
        show(dark)
        open()
        advance(3 * FRAME)
        compose.screenshot("zoom_open_${theme(dark)}")
    }

    private fun dragged(dark: Boolean) {
        show(dark)
        open()
        advance(1_000)
        // From 300 points down the window, the finger still on it.
        compose.onRoot().performTouchInput {
            down(Offset(width / 2f, 300f * density))
            repeat(20) { moveBy(Offset(0f, 200f * density / 20), delayMillis = 16) }
            moveBy(Offset.Zero)
        }
        advance(2 * FRAME)
        compose.screenshot("zoom_drag_${theme(dark)}")
    }

    private fun theme(dark: Boolean) = if (dark) "dark" else "light"

    @Test
    fun thePageOnItsWayOutOfItsCard() = opening(dark = false)

    @Test
    fun thePageOnItsWayOutOfItsCardInDark() = opening(dark = true)

    @Test
    fun thePageDraggedDown() = dragged(dark = false)

    @Test
    fun thePageDraggedDownInDark() = dragged(dark = true)

    private companion object {
        const val FRAME = 16L
    }
}
