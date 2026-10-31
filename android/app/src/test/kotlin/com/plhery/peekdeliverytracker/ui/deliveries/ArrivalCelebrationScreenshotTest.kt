package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
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
 * The arrival of a card just added, 0.3 s in, on the demo's list as the iPhone the captures were taken on shows it
 * (440 × 956 points at three pixels to the point): the card wobbling as the little parcels fly up out of its stamp, on
 * a card on its way and on Next up, in light and dark; then on a tablet, the list beside the first card's parcel.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ArrivalCelebrationScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState

    /** The demo's list with the window's bars, `top` and `bottom` points tall. */
    private fun show(dark: Boolean = false, top: Int = 62, bottom: Int = 24) {
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = false)
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
    }

    /** The arrival on the card of the demo's parcel named `name`, 0.3 s after its first frame. */
    private fun arrive(name: String, shot: String) {
        val parcel = DemoCards.parcels(AppLanguage.En).first { it.label.startsWith(name) }
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            deliveries.parcelBurst = parcel.id
            Snapshot.sendApplyNotifications()
        }
        // Composed, then its first frame, then 0.3 s on.
        repeat(2 + 19) {
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
        compose.screenshot(shot)
    }

    private fun theme(dark: Boolean) = if (dark) "dark" else "light"

    private fun onTheWay(dark: Boolean) {
        show(dark)
        arrive("Belgian chocolate", "add_arrival_onTheWay_${theme(dark)}")
    }

    private fun next(dark: Boolean) {
        show(dark)
        arrive("New trainers", "add_arrival_next_${theme(dark)}")
    }

    @Test
    fun aCardOnItsWay() = onTheWay(dark = false)

    @Test
    fun aCardOnItsWayInDark() = onTheWay(dark = true)

    @Test
    fun nextUp() = next(dark = false)

    @Test
    fun nextUpInDark() = next(dark = true)

    /** An iPad's 1032 × 1376: the list beside the first card's parcel, a card on its way arriving. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletBesideTheList() {
        show(top = 24, bottom = 24)
        arrive("Belgian chocolate", "add_arrival_tablet")
    }
}
