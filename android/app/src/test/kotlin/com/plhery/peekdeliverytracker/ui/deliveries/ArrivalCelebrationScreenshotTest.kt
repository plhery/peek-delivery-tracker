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
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The arrival of a card just added, 0.3 s in, on the demo's list as the iPhone the captures were taken on shows it
 * (440 × 956 points at three pixels to the point), once the list has brought the card to its middle: the card wobbling
 * as the little parcels fly up out of its stamp, on a card on its way and on Next up, in light and dark; then on a
 * tablet, the list beside the first card's parcel; and under Reduce Motion, Next up brightened, its shadow with it.
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
    private val bursts = mutableMapOf<UUID, ParcelBurstFrame>()

    /** The demo's list with the window's bars, `top` and `bottom` points tall. */
    private fun show(dark: Boolean = false, top: Int = 62, bottom: Int = 24, reduceMotion: Boolean = false) {
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(
                LocalDeliveriesState provides { deliveries = it },
                LocalParcelBursts provides { id, burst -> bursts[id] = burst },
            ) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, reduceMotion = reduceMotion)
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

    /**
     * The card of the demo's parcel named `name` revealed, as Add's save ends: brought to the list's middle, then its
     * arrival `seconds` after its first frame, to the frame.
     */
    private fun arrive(name: String, shot: String, seconds: Double = 0.3) {
        val parcel = DemoCards.parcels(AppLanguage.En).first { it.label.startsWith(name) }
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread {
            deliveries.revealParcel = parcel.id
            Snapshot.sendApplyNotifications()
        }
        var frames = 0
        while (bursts[parcel.id].let { it == null || it.seconds < seconds - 0.008 }) {
            check(frames++ < 180) { "No arrival on $name" }
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
        assertNull(deliveries.revealParcel)
        assertEquals(parcel.id, deliveries.parcelBurst)
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

    /** Under Reduce Motion, Next up at the top of its pulse: brightened, its shadow under it brightened too. */
    @Test
    fun nextUpUnderReduceMotion() {
        show(reduceMotion = true)
        arrive("New trainers", "add_arrival_next_reduced", seconds = 0.375)
    }
}
