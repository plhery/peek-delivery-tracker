package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.LikelyCarrier
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.screenshot
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Duration
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The pass at the top of a parcel's page on the iPhone the captures were taken on, in the demo at the captures' moment:
 * on its paper, its bell on and off, at twice the text size, and with a probable carrier, new and a week on; and on the
 * page, a parcel gone quiet with its note, and the map its globe opens, light and dark. The page at rest, dark and on a
 * tablet is ParcelPageScreenshotTest's.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class LivePassScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View

    /** `parcel`'s pass on the page's paper, 18 in from its sides, with a bell that does nothing. */
    private fun pass(name: String, dark: Boolean = false, fontScale: Float = 1f, parcel: () -> Parcel) {
        compose.waitsForMaps()
        compose.setContent {
            CardHost(dark = dark, fontScale = fontScale) {
                Box(Modifier.background(PeekTheme.colors.paper).padding(18.dp)) {
                    LivePassCard(
                        parcel(), rememberWorldAtlas(), sample = false, readerLink = false,
                        bell = PassBell(opensAlerts = false) { _, _ -> }, working = false, onMap = {},
                    )
                }
            }
        }
        compose.screenshot(name)
    }

    /** The demo's list as the page shots show it, with the iPhone's status bar 62 tall and gesture bar 24. */
    private fun show(parcels: List<Parcel> = DemoCards.parcels(), dark: Boolean = false) {
        val source = FakeDeliveries(parcels, AuthenticationState.Demo)
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
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (62 * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (24 * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun open(name: String) {
        compose.openCard(name)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    @Test
    fun theBellOn() = pass("pass_bell_on_light") { DemoCards.parcel("Birthday gift") }

    @Test
    fun theBellOff() = pass("pass_bell_off_light") { DemoCards.parcel("Birthday gift").copy(notificationsMuted = true) }

    @Test
    fun theBellOffInDark() = pass("pass_bell_off_dark", dark = true) { DemoCards.parcel("Birthday gift").copy(notificationsMuted = true) }

    /** At twice the text size, an accessibility size: the buttons under the marks, and the map that much lower. */
    @Test
    fun largeText() = pass("pass_light_text2", fontScale = 2f) { DemoCards.parcel("Birthday gift") }

    /** Nobody has answered for the number yet: the carrier it points to, named as probable, and what it means. */
    @Test
    fun aProbableCarrier() = pass("pass_probable_light") { DemoCards.probable() }

    /** A week on, the reader is asked to check the number or choose another carrier. */
    @Test
    fun aProbableCarrierAWeekOn() = pass("pass_probable_week_light") {
        val since = DateParser.isoString(CAPTURED.minus(Duration.ofDays(8)))
        DemoCards.probable().copy(carrierData = CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.GlsDe, since))))
    }

    /** Cat tree, as its scans stopped a week before the captures: the page's note on why parcels go quiet. */
    @Test
    fun aQuietParcel() {
        val cutoff = CAPTURED.minus(Duration.ofHours(170))
        show(
            DemoCards.parcels().map { parcel ->
                if (!parcel.label.startsWith("Cat tree")) return@map parcel
                parcel.copy(trackingEvents = parcel.trackingEvents.filter { DateParser.date(it.occurredAt)!! <= cutoff })
            },
        )
        open("Cat tree")
        compose.onNodeWithTag(PAGE_QUIET).performScrollTo()
        compose.screenshot("page_quiet_light")
    }

    @Test
    fun theMap() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.screenshot("map_cover_light")
    }

    @Test
    fun theMapInDark() {
        show(dark = true)
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_GLOBE).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.screenshot("map_cover_dark")
    }
}
