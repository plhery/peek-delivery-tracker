package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Changing a parcel's carrier over the demo's list, with the iPhone's bars: automatic detection at the medium detent,
 * light and dark; Mondial Relay's postcode at the large detent with its country beside it, light and dark, its
 * countries' menu, and at the largest Android text size; DPD's optional postcode while the change is saved, and once
 * it failed; and the picker pushed in the sheet. On synthetic parcels.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ChangeCarrierScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    private fun parcel(index: Int, label: String, carrier: CarrierId, number: String) = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-0000000006%02d".format(index)), trackingNumber = number, label = label,
        carrier = carrier, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(index.toLong()))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    /** Filed under none: automatic detection. */
    private val lamp = parcel(1, "Desk lamp", CarrierId.Unknown, "TESTLAMP0001")

    /** Mondial Relay asks for the delivery postcode, from one of several countries. */
    private val chair = parcel(2, "Garden chair", CarrierId.MondialRelay, "76434219")

    /** The demo's list as the captures show it, with the iPhone's bars, then the carrier editor on `parcel`. */
    private fun show(parcel: Parcel, dark: Boolean = false, fontScale: Float = 1f, prepare: FakeDeliveries.() -> Unit = {}) {
        source = FakeDeliveries(DemoCards.parcels() + lamp + chair, AuthenticationState.Demo).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, dark = dark, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(62), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(24)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
        compose.runOnUiThread { deliveries.chose(ParcelMenuItem.ChangeCarrier, parcel) }
        compose.waitForIdle()
    }

    private fun click(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
    }

    /** The picker pushed from the carrier's row, and DPD found and chosen in it. */
    private fun chooseDpd() {
        click(CHANGE_CARRIER_ROW)
        compose.onNodeWithTag(CARRIER_PICKER_SEARCH).performTextInput("DPD")
        compose.waitForIdle()
        compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.Dpd)).onFirst().performClick()
        compose.waitForIdle()
    }

    @Test
    fun automaticDetectionAtTheMediumDetent() {
        show(lamp)
        compose.screenshot("change_carrier_medium_light")
    }

    @Test
    fun automaticDetectionAtTheMediumDetentInDark() {
        show(lamp, dark = true)
        compose.screenshot("change_carrier_medium_dark")
    }

    @Test
    fun aPostcodeAndItsCountryAtTheLargeDetent() {
        show(chair)
        compose.screenshot("change_carrier_postcode_light")
    }

    @Test
    fun aPostcodeAndItsCountryAtTheLargeDetentInDark() {
        show(chair, dark = true)
        compose.screenshot("change_carrier_postcode_dark")
    }

    @Test
    fun theCountriesMenu() {
        show(chair)
        click(CHANGE_CARRIER_COUNTRY)
        compose.screenshot("change_carrier_country_menu_light")
    }

    @Test
    fun atTheLargestTextSize() {
        show(chair, fontScale = 2f)
        compose.screenshot("change_carrier_accessibility_light")
    }

    @Test
    fun whileTheChangeIsSaved() {
        show(lamp) { changing = CompletableDeferred() }
        chooseDpd()
        click(CHANGE_CARRIER_SAVE)
        compose.screenshot("change_carrier_saving_light")
    }

    @Test
    fun onceTheChangeFailed() {
        val failed = Localizer(AppLanguage.En, variant = "android").text("detail.changeCarrierFailed")
        show(lamp) { failure = IllegalStateException(failed) }
        chooseDpd()
        click(CHANGE_CARRIER_SAVE)
        compose.screenshot("change_carrier_error_light")
    }

    @Test
    fun thePickerPushedInTheSheet() {
        show(lamp)
        click(CHANGE_CARRIER_ROW)
        compose.screenshot("change_carrier_picker_light")
    }
}
