package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's gestures on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), in the
 * demo, light and dark: a card swiped open on its action, a card's menu, its delete question (on a tablet too), the Undo
 * of an archive, the pull halfway to its threshold, and the seal's tick once every parcel has been checked.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ListGesturesScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var source: FakeDeliveries

    /** The demo's list with the iPhone's bars: the status bar 62 points tall unless `top` says otherwise, the gesture bar 24. */
    private fun show(dark: Boolean, top: Int = 62) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            Box(Modifier.fillMaxSize().testTag(SHOT)) {
                RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = false, source = source)
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (top * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (24 * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun TouchInjectionScope.points(value: Float): Float = value * density

    /** The finger across the card from near its trailing edge, `distance` points, still for a moment before it lifts. */
    private fun swipe(name: String, distance: Float) {
        compose.card(name).performTouchInput {
            down(Offset(width - points(30f), height / 2f))
            repeat(12) { moveBy(Offset(points(distance) / 12, 0f)) }
            advanceEventTime(200)
            up()
        }
        compose.waitForIdle()
    }

    /** The list pulled down `distance` points from 200 points down the window, the finger still on it unless it `lifts`. */
    private fun pull(distance: Float, lifts: Boolean) {
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, points(200f)))
            repeat(30) { moveBy(Offset(0f, points(distance) / 30)) }
            advanceEventTime(200)
            if (lifts) up() else moveBy(Offset.Zero)
        }
    }

    private fun archive(name: String) {
        val action = compose.card(name).fetchSemanticsNode().config[SemanticsActions.CustomActions].single { it.label == "Archive" }
        compose.runOnUiThread { action.action() }
        compose.waitForIdle()
    }

    private fun swipeOpen(dark: Boolean) {
        show(dark)
        swipe("Birthday gift", -60f)
        compose.screenshot("gestures_swipe_open_${theme(dark)}")
    }

    private fun menu(dark: Boolean) {
        show(dark)
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
        compose.screenshot("gestures_menu_${theme(dark)}")
    }

    /** A card's menu's Delete: its question in a popover pointing at the card. */
    private fun askDelete(name: String) {
        compose.card(name).performTouchInput { longClick() }
        compose.waitForIdle()
        compose.onNodeWithTag(menuItemTag(ParcelMenuItem.Delete)).performClick()
        compose.mainClock.advanceTimeBy(1_000)
    }

    private fun undo(dark: Boolean) {
        show(dark)
        archive("Belgian chocolate")
        compose.screenshot("gestures_undo_${theme(dark)}")
    }

    private fun pullHalfway(dark: Boolean) {
        show(dark)
        // 250 points of finger show the content 120 down on the rubber band: below the 191 that start a refresh.
        pull(250f, lifts = false)
        compose.waitForIdle()
        compose.screenshot("gestures_pull_${theme(dark)}")
    }

    private fun seal(dark: Boolean) {
        show(dark)
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checked
        compose.mainClock.autoAdvance = false
        pull(520f, lifts = true)
        checked.complete(ParcelStore.RefreshResult.Updated)
        // The tick, drawn in, once the arrow has turned 0.9 s and the content rests under the gap.
        for (frame in 0 until 120) {
            val said = compose.onNodeWithTag(ANNOUNCEMENT, useUnmergedTree = true).fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.ContentDescription)?.firstOrNull()
            if (said == "Tracking updated") break
            compose.mainClock.advanceTimeByFrame()
        }
        compose.mainClock.advanceTimeBy(400)
        compose.screenshot("gestures_seal_${theme(dark)}")
    }

    private fun theme(dark: Boolean) = if (dark) "dark" else "light"

    @Test
    fun aCardSwipedOpen() = swipeOpen(dark = false)

    @Test
    fun aCardSwipedOpenInDark() = swipeOpen(dark = true)

    @Test
    fun aCardsMenu() = menu(dark = false)

    @Test
    fun aCardsMenuInDark() = menu(dark = true)

    /** Next up's, under its card, as the first card leaves no room over it. */
    @Test
    fun aCardsDeleteQuestion() {
        show(dark = false)
        askDelete("New trainers")
        compose.screenshot("delete_popover_card_light")
    }

    /** An iPad, 1032 × 1376, the list beside a page: the question over its card, though there is more room under it. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aCardsDeleteQuestionOnATablet() {
        show(dark = false, top = 24)
        askDelete("Birthday gift")
        compose.screenshot("delete_popover_card_tablet_light")
    }

    @Test
    fun theUndoOfAnArchive() = undo(dark = false)

    @Test
    fun theUndoOfAnArchiveInDark() = undo(dark = true)

    @Test
    fun thePullHalfway() = pullHalfway(dark = false)

    @Test
    fun thePullHalfwayInDark() = pullHalfway(dark = true)

    @Test
    fun theSealsTick() = seal(dark = false)

    @Test
    fun theSealsTickInDark() = seal(dark = true)
}
