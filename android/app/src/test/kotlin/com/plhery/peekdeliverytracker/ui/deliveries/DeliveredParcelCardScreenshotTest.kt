package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The past deliveries' cards as the iPhone's list draws them on its captures (A-ST19): paler than the cards of the
 * parcels on their way, a tick before "Delivered" and the day it came.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class DeliveredParcelCardScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    @Test
    fun swissPostLight() = shot("delivered_swisspost_light") { DemoCards.parcel("Coffee beans") }

    @Test
    fun swissPostDark() = shot("delivered_swisspost_dark", dark = true) { DemoCards.parcel("Coffee beans") }

    /** A journey across the globe: the route is drawn on a globe. */
    @Test
    fun fedexLight() = shot("delivered_fedex_light") { DemoCards.parcel("Matcha ritual") }

    @Test
    fun fedexDark() = shot("delivered_fedex_dark", dark = true) { DemoCards.parcel("Matcha ritual") }

    @Test
    fun chronopostLight() = shot("delivered_chronopost_light") { DemoCards.parcel("Sunday in Kyoto") }

    @Test
    fun chronopostDark() = shot("delivered_chronopost_dark", dark = true) { DemoCards.parcel("Sunday in Kyoto") }

    @Test
    fun dhlLight() = shot("delivered_dhl_light") { DemoCards.parcel("Mechanical keyboard") }

    @Test
    fun dhlDark() = shot("delivered_dhl_dark", dark = true) { DemoCards.parcel("Mechanical keyboard") }

    @Test
    fun postnlLight() = shot("delivered_postnl_light") { DemoCards.parcel("Cat tree") }

    @Test
    fun postnlDark() = shot("delivered_postnl_dark", dark = true) { DemoCards.parcel("Cat tree") }

    @Test
    fun german() = shot("delivered_swisspost_de_light", AppLanguage.De) { DemoCards.parcel("Coffee beans", AppLanguage.De) }

    @Test
    fun largeText() = shot("delivered_swisspost_light_text2", fontScale = 2f) { DemoCards.parcel("Coffee beans") }

    @Test
    fun largeTextDark() = shot("delivered_swisspost_dark_text2", dark = true, fontScale = 2f) { DemoCards.parcel("Coffee beans") }

    private fun shot(
        name: String,
        language: AppLanguage = AppLanguage.En,
        dark: Boolean = false,
        fontScale: Float = 1f,
        parcel: () -> Parcel,
    ) {
        compose.waitsForMaps()
        val shown = parcel()
        compose.setContent {
            CardHost(language, dark, fontScale) { DeliveredParcelCard(shown, onOpen = {}) }
        }
        compose.screenshot(name)
    }
}
