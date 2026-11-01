package com.plhery.peekdeliverytracker.ui

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Density
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelLinkStopped
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.pinnedIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.io.IOException
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's link in its sheet over the demo's list, with the iPhone's bars: a parcel with a route (on a phone, light
 * and dark, in the iPad's window and at the largest text size), one that arrived and one with neither, as someone
 * signed out sees them; the keeping block for someone signed in; a gift wrapped and opened; a link gone, stopped, and
 * one whose read failed, offline and otherwise. On synthetic links.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ParcelLinkScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View

    /** The link `answer` reads as, its sheet up over the list, as `state` sees it. */
    private fun show(
        answer: suspend () -> PublicParcelResponse?,
        state: AuthenticationState = AuthenticationState.Welcome,
        dark: Boolean = false,
        iPad: Boolean = false,
        fontScale: Float = 1f,
    ) {
        val links = FakeParcelLinks(session = state).apply { read = { _, _ -> answer() } }
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = state, dark = dark, identities = pinnedIdentities, links = links)
                }
            }
        }
        compose.waitForIdle()
        if (iPad) bars(status = 32, bottom = 20) else bars(status = 62, bottom = 24)
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The status bar `status` points tall and the bottom bar `bottom`: the iPhone's 62 and 24, the iPad's 32 and 20. */
    private fun bars(status: Int, bottom: Int) {
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(status), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    @Test
    fun withARoute() {
        show({ LinkFixtures.routed() })
        compose.screenshot("link_route_phone_light")
    }

    @Test
    fun withARouteInDark() {
        show({ LinkFixtures.routed() }, dark = true)
        compose.screenshot("link_route_phone_dark")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun withARouteInARegularWindow() {
        show({ LinkFixtures.routed() }, iPad = true)
        compose.screenshot("link_route_tablet_light")
    }

    @Test
    fun withARouteAtTheLargestTextSize() {
        show({ LinkFixtures.routed() }, fontScale = 2f)
        compose.screenshot("link_route_phone_text_largest")
    }

    @Test
    fun arrived() {
        show({ LinkFixtures.arrived() })
        compose.screenshot("link_arrived_phone_light")
    }

    @Test
    fun withNeitherRouteNorArrival() {
        show({ LinkFixtures.keepable() })
        compose.screenshot("link_plain_signed_out_phone_light")
    }

    @Test
    fun keptBySomeoneSignedIn() {
        show({ LinkFixtures.keepable() }, state = FakeParcelLinks.READER)
        compose.screenshot("link_plain_signed_in_phone_light")
    }

    @Test
    fun aGiftOnItsWay() {
        show({ LinkFixtures.wrappedGift() })
        compose.screenshot("link_gift_wrapped_phone_light")
    }

    @Test
    fun aGiftDelivered() {
        show({ LinkFixtures.openedGift() })
        compose.screenshot("link_gift_opened_phone_light")
    }

    @Test
    fun aGiftDeliveredInDark() {
        show({ LinkFixtures.openedGift() }, dark = true)
        compose.screenshot("link_gift_opened_phone_dark")
    }

    @Test
    fun gone() {
        show({ null })
        compose.screenshot("link_nothing_phone_light")
    }

    @Test
    fun stopped() {
        show({ throw ParcelLinkStopped() })
        compose.screenshot("link_stopped_phone_light")
    }

    @Test
    fun failedOffline() {
        show({ throw IOException("offline") })
        compose.screenshot("link_failed_offline_phone_light")
    }

    @Test
    fun failedOtherwise() {
        show({ throw IllegalStateException("broken") })
        compose.screenshot("link_failed_service_phone_light")
    }
}

/** The iPad's window, 1032 × 1376 points upright, at twice the pixels. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
