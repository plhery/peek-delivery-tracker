package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The cards of the parcels on their way as the iPhone's list draws them on its captures (A-S35): each carrier's tint,
 * with a date and without, with a route and without a place to draw; a card's problem kept on it (A-ST20); a parcel's
 * probable carrier (A-S103); a parcel just delivered among them; and the parcel shown beside the list.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelPassCardScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    /** At customs: the status says all it needs, so the list's notice makes no flag. */
    @Test
    fun customsLight() = shot("card_customs_light", noticed = true) { DemoCards.parcel("Birthday gift") }

    @Test
    fun customsDark() = shot("card_customs_dark", dark = true, noticed = true) { DemoCards.parcel("Birthday gift") }

    @Test
    fun outForDeliveryLight() = shot("card_out_light") { DemoCards.parcel("Belgian chocolate") }

    @Test
    fun outForDeliveryDark() = shot("card_out_dark", dark = true) { DemoCards.parcel("Belgian chocolate") }

    @Test
    fun acceptedLight() = shot("card_accepted_light") { DemoCards.parcel("Trail weekend kit") }

    @Test
    fun acceptedDark() = shot("card_accepted_dark", dark = true) { DemoCards.parcel("Trail weekend kit") }

    @Test
    fun inTransitLight() = shot("card_transit_light") { DemoCards.parcel("Moon lamp") }

    @Test
    fun inTransitDark() = shot("card_transit_dark", dark = true) { DemoCards.parcel("Moon lamp") }

    /** Scans without places: no route. */
    @Test
    fun withoutAPlaceLight() = shot("card_unplaced_light") { DemoCards.parcel("35mm film rolls") }

    @Test
    fun withoutAPlaceDark() = shot("card_unplaced_dark", dark = true) { DemoCards.parcel("35mm film rolls") }

    /** No estimate: no date. */
    @Test
    fun withoutADateLight() = shot("card_undated_light") { DemoCards.parcel("Kind of Blue") }

    @Test
    fun withoutADateDark() = shot("card_undated_dark", dark = true) { DemoCards.parcel("Kind of Blue") }

    @Test
    fun germanLight() = shot("card_transit_de_light", AppLanguage.De) { DemoCards.parcel("Moon lamp", AppLanguage.De) }

    @Test
    fun germanCustomsDark() =
        shot("card_customs_de_dark", AppLanguage.De, dark = true, noticed = true) { DemoCards.parcel("Birthday gift", AppLanguage.De) }

    /** At twice the text size, one of the accessibility sizes: the words take the card and the route goes. */
    @Test
    fun largeTextLight() = shot("card_transit_light_text2", fontScale = 2f) { DemoCards.parcel("Moon lamp") }

    @Test
    fun largeTextDark() = shot("card_transit_dark_text2", dark = true, fontScale = 2f) { DemoCards.parcel("Moon lamp") }

    @Test
    fun largeTextGerman() =
        shot("card_failed_attempt_de_light_text2", AppLanguage.De, fontScale = 2f, noticed = true) { missed(AppLanguage.De) }

    /** With Increase Contrast: the carriers' darker inks and the map's firmer lines. */
    @Test
    fun increasedContrastLight() = shot("card_transit_light_contrast", highContrast = true) { DemoCards.parcel("Moon lamp") }

    @Test
    fun increasedContrastDark() = shot("card_transit_dark_contrast", dark = true, highContrast = true) { DemoCards.parcel("Moon lamp") }

    /** Its scans unchanged for days: the list's notice, with a clock, beside the status. */
    @Test
    fun stalledLight() = shot("card_stalled_light", notice = "attention.stalled") { DemoCards.parcel("Kind of Blue") }

    @Test
    fun stalledDark() = shot("card_stalled_dark", dark = true, notice = "attention.stalled") { DemoCards.parcel("Kind of Blue") }

    /** A missed delivery: the carrier waits on the reader, with a warning sign. */
    @Test
    fun failedAttemptLight() = shot("card_failed_attempt_light", noticed = true) { missed() }

    @Test
    fun failedAttemptDark() = shot("card_failed_attempt_dark", dark = true, noticed = true) { missed() }

    /** The latest update failed: the flag says so, with a turning arrow. */
    @Test
    fun updateFailedLight() = shot("card_update_failed_light", noticed = true) { failed() }

    @Test
    fun updateFailedDark() = shot("card_update_failed_dark", dark = true, noticed = true) { failed() }

    @Test
    fun probableCarrierLight() = shot("card_probable_light") { DemoCards.probable() }

    @Test
    fun probableCarrierDark() = shot("card_probable_dark", dark = true) { DemoCards.probable() }

    @Test
    fun probableCarrierGerman() = shot("card_probable_de_light", AppLanguage.De) { DemoCards.probable(AppLanguage.De) }

    /** Just delivered and still among the parcels on their way: a tick, and not yet pale. */
    @Test
    fun arrivedLight() = shot("card_arrived_light", arrived = true) { DemoCards.parcel("Coffee beans") }

    @Test
    fun selectedLight() = shot("card_selected_light", selected = true) { DemoCards.parcel("Belgian chocolate") }

    @Test
    fun selectedDark() = shot("card_selected_dark", dark = true, selected = true) { DemoCards.parcel("Belgian chocolate") }

    /** The trail kit, its latest scan a missed delivery. */
    private fun missed(language: AppLanguage = AppLanguage.En): Parcel {
        val parcel = DemoCards.parcel("Trail weekend kit", language)
        val latest = parcel.trackingEvents.maxBy { it.occurredAt }
        return parcel.copy(
            trackingEvents = parcel.trackingEvents.map { if (it.id == latest.id) it.copy(stage = TrackingStage.FailedAttempt) else it },
        )
    }

    private fun failed(): Parcel = DemoCards.parcel("Moon lamp").copy(syncStatus = SyncStatus.Error)

    /**
     * `noticed`: in the list's attention section, where the card says what the parcel needs; `notice`, a notice's key
     * the demo has no parcel for.
     */
    private fun shot(
        name: String,
        language: AppLanguage = AppLanguage.En,
        dark: Boolean = false,
        fontScale: Float = 1f,
        highContrast: Boolean = false,
        noticed: Boolean = false,
        notice: String? = null,
        arrived: Boolean = false,
        selected: Boolean = false,
        parcel: () -> Parcel,
    ) {
        compose.waitsForMaps()
        val shown = parcel()
        val said = notice?.let { Localizer(language, variant = "android").text(it) } ?: if (noticed) DemoCards.notice(shown, language) else null
        compose.setContent {
            CardHost(language, dark, fontScale, highContrast) {
                ParcelPassCard(shown, notice = said, onOpen = {}, arrived = arrived, selected = selected)
            }
        }
        compose.screenshot(name)
    }
}
