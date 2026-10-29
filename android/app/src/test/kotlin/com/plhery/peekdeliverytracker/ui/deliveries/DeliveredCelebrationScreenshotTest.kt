package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
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
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DemoRepository
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.awaitFrame
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The demo's check delivering New trainers and Belgian chocolate on the iPhone the captures were taken on (440 × 956
 * points at three pixels to the point), as the list is looked at: New trainers' card held with its postmark, the paper
 * out of its Pip's box mid-burst in light and dark, the cards mid-flight, and the list once they have landed.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class DeliveredCelebrationScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var source: FakeDeliveries

    /** The demo's parcels as they are, and as its check finds them. */
    private val demo: Pair<List<Parcel>, List<Parcel>> by lazy {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val preferences = context.getSharedPreferences("celebration-shots", Context.MODE_PRIVATE)
        val repository = DemoRepository(PreferencesDefaults(preferences), { AppLanguage.En }, { CAPTURED })
        val first = repository.list()
        val checked = repository.refreshAll()
        first to first.map { parcel -> checked.first { it.id == parcel.id } }
    }

    /** The demo's list with the iPhone's bars, the status bar 62 points tall, the gesture bar 24, then the check. */
    private fun deliver(dark: Boolean) {
        source = FakeDeliveries(demo.first, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalPaperSeeds provides { SEED }) {
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
        compose.runOnUiThread {
            source.parcels.value = demo.second
            Snapshot.sendApplyNotifications()
        }
    }

    /** The shot `milliseconds` later, the clock moved a frame at a time to it. */
    private fun shot(milliseconds: Long, name: String) {
        compose.mainClock.advanceTimeBy(milliseconds)
        compose.screenshot(name)
    }

    private fun theme(dark: Boolean) = if (dark) "dark" else "light"

    /** The postmark has landed with its tap, and the card is read; the paper falls about it. */
    @Test
    fun theCardHeldWithItsPostmark() {
        deliver(dark = false)
        shot(900, "celebration_held_light")
    }

    private fun paper(dark: Boolean) {
        deliver(dark)
        shot(450, "celebration_paper_${theme(dark)}")
    }

    @Test
    fun thePaperMidBurst() = paper(dark = false)

    @Test
    fun thePaperMidBurstInDark() = paper(dark = true)

    /** 0.2 s into the flights: New trainers' card shrinks down to its place among the deliveries, Moon lamp's grows to Next up. */
    @Test
    fun theCardsMidFlight() {
        deliver(dark = false)
        compose.mainClock.advanceTimeBy(900)
        // The window draws the held cards, whose pictures fly: Robolectric draws it only when asked, as for a shot.
        compose.awaitFrame()
        shot(50 + 200, "celebration_flight_light")
    }

    /** The cards landed and the paper gone. */
    @Test
    fun theListOnceTheCardsHaveLanded() {
        deliver(dark = false)
        shot(5000, "celebration_landed_light")
    }

    private companion object {
        /** One burst of paper, the same in every run. */
        const val SEED = 0x5EED_CAFEuL
    }
}
