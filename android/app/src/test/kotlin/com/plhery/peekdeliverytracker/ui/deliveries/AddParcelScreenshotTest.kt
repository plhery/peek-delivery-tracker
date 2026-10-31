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
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.util.UUID
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Adding a parcel over the demo's list, signed in, with the iPhone's bars: Add empty, light and dark; a number UPS
 * claims with its name, light and dark; several numbers; Mondial Relay's postcode with its country, in English and
 * German; a save that failed, and a number in the box already; at the largest Android text size; and side by side on a
 * tablet, light and dark. On synthetic numbers, with the catalog pinned ([pinnedIdentities]).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class AddParcelScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    /** The demo's list as the captures show it, with the iPhone's bars, then Add from the +, settled. */
    private fun show(dark: Boolean = false, fontScale: Float = 1f, language: AppLanguage = AppLanguage.En, prepare: FakeDeliveries.() -> Unit = {}) {
        val source = FakeDeliveries(DemoCards.parcels(language), reader).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = reader, language = language, dark = dark, source = source, identities = pinnedIdentities)
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
        click(ADD_BUTTON)
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun click(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        compose.waitForIdle()
    }

    /** UPS's number and a name, the name's field left with the keyboard on it. */
    private fun typeShoes() {
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.onNodeWithTag(ADD_NAME).performClick()
        compose.onNodeWithTag(ADD_NAME).performTextInput("Running shoes")
        settle()
    }

    /** Mondial Relay's number, its carrier chosen in the picker, the reader's country beside its postcode. */
    private fun chooseMondialRelay() {
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("76434219")
        click(ADD_CARRIER)
        settle()
        compose.onNodeWithTag(CARRIER_PICKER_SEARCH).performTextInput("Mondial")
        compose.waitForIdle()
        compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.MondialRelay)).onFirst().performClick()
        settle()
    }

    @Test
    fun empty() {
        show()
        compose.screenshot("add_parcel_empty_light")
    }

    @Test
    fun emptyInDark() {
        show(dark = true)
        compose.screenshot("add_parcel_empty_dark")
    }

    @Test
    fun aCarrierFound() {
        show()
        typeShoes()
        compose.screenshot("add_parcel_found_light")
    }

    @Test
    fun aCarrierFoundInDark() {
        show(dark = true)
        typeShoes()
        compose.screenshot("add_parcel_found_dark")
    }

    @Test
    fun severalNumbers() {
        show()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("Your order ships in two parcels:\nUPS 1Z999AA10123456784\nUPS 1Z999AA10123456793")
        settle()
        compose.screenshot("add_parcel_several_light")
    }

    @Test
    fun aPostcodeAndItsCountry() {
        show()
        chooseMondialRelay()
        compose.screenshot("add_parcel_requirements_light")
    }

    @Test
    fun aPostcodeAndItsCountryInGerman() {
        show(language = AppLanguage.De)
        chooseMondialRelay()
        compose.screenshot("add_parcel_requirements_de_light")
    }

    @Test
    fun aSaveThatFailed() {
        show { failure = IllegalStateException("No connection. Try again in a moment.") }
        typeShoes()
        click(ADD_SAVE)
        settle()
        compose.screenshot("add_parcel_error_light")
    }

    @Test
    fun aNumberInTheBoxAlready() {
        show { failure = DeliveryApiError.DuplicateTracking(UUID.fromString("00000000-0000-4000-8000-000000000701")) }
        typeShoes()
        click(ADD_SAVE)
        settle()
        compose.screenshot("add_parcel_duplicate_light")
    }

    @Test
    fun atTheLargestTextSize() {
        show(fontScale = 2f)
        typeShoes()
        compose.screenshot("add_parcel_accessibility_light")
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun sideBySideOnATablet() {
        show()
        typeShoes()
        compose.screenshot("add_parcel_tablet_light")
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun sideBySideOnATabletInDark() {
        show(dark = true)
        compose.screenshot("add_parcel_tablet_dark")
    }
}
