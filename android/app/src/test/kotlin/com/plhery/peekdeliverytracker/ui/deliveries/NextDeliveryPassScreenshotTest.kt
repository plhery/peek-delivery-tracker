package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The Next up card as the iPhone's list draws it on its captures (A-S34): the demo's trainers waiting at a kiosk, their
 * route across the top of the card; a parcel whose scans have no places; one just delivered; one whose update failed;
 * and the parcel shown beside the list.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class NextDeliveryPassScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    @Test
    fun routeLight() = shot("nextup_route_light") { DemoCards.parcel("New trainers") }

    @Test
    fun routeDark() = shot("nextup_route_dark", dark = true) { DemoCards.parcel("New trainers") }

    @Test
    fun routeGerman() = shot("nextup_route_de_light", AppLanguage.De) { DemoCards.parcel("New trainers", AppLanguage.De) }

    @Test
    fun routeLargeText() = shot("nextup_route_light_text2", fontScale = 2f) { DemoCards.parcel("New trainers") }

    @Test
    fun routeLargeTextDark() = shot("nextup_route_dark_text2", dark = true, fontScale = 2f) { DemoCards.parcel("New trainers") }

    /** With Increase Contrast: the carrier's darker ink and the map's firmer lines. */
    @Test
    fun increasedContrastLight() = shot("nextup_route_light_contrast", highContrast = true) { DemoCards.parcel("New trainers") }

    @Test
    fun increasedContrastDark() = shot("nextup_route_dark_contrast", dark = true, highContrast = true) { DemoCards.parcel("New trainers") }

    @Test
    fun withoutAPlaceLight() = shot("nextup_unplaced_light") { DemoCards.parcel("35mm film rolls") }

    @Test
    fun withoutAPlaceDark() = shot("nextup_unplaced_dark", dark = true) { DemoCards.parcel("35mm film rolls") }

    @Test
    fun arrivedLight() = shot("nextup_arrived_light", arrived = true) { DemoCards.parcel("Coffee beans") }

    @Test
    fun arrivedDark() = shot("nextup_arrived_dark", dark = true, arrived = true) { DemoCards.parcel("Coffee beans") }

    @Test
    fun updateFailedLight() = shot("nextup_failed_light") { failed() }

    @Test
    fun updateFailedDark() = shot("nextup_failed_dark", dark = true) { failed() }

    @Test
    fun selectedLight() = shot("nextup_selected_light", selected = true) { DemoCards.parcel("New trainers") }

    @Test
    fun selectedDark() = shot("nextup_selected_dark", dark = true, selected = true) { DemoCards.parcel("New trainers") }

    private fun failed(): Parcel = DemoCards.parcel("Moon lamp").copy(syncStatus = SyncStatus.Error)

    private fun shot(
        name: String,
        language: AppLanguage = AppLanguage.En,
        dark: Boolean = false,
        fontScale: Float = 1f,
        highContrast: Boolean = false,
        arrived: Boolean = false,
        selected: Boolean = false,
        parcel: () -> Parcel,
    ) {
        compose.waitsForMaps()
        val shown = parcel()
        compose.setContent {
            CardHost(language, dark, fontScale, highContrast) {
                NextDeliveryPass(shown, onOpen = {}, arrived = arrived, selected = selected)
            }
        }
        compose.screenshot(name)
    }
}
