package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierPickerSearch
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The carrier picker on a phone, Swiss Post chosen, the two carriers that fit the number and one used before over the
 * letters: light and dark, a search marking what it matched, the rail held on M with its bubble, and at the largest
 * Android text size.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class CarrierPickerScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private val tags = PICKER_TAGS + (CarrierId.Planzer to CarrierPickerTag("Knows this number", found = false))

    private fun show(dark: Boolean = false, fontScale: Float = 1f, automatic: CarrierPickerAutomatic = PICKER_AUTOMATIC.copy(recommended = false)) {
        compose.setContent {
            PickerHost(dark = dark, fontScale = fontScale) {
                CarrierPicker(CarrierId.SwissPost, PICKER_SECTIONS, onSelect = {}, onLeave = {}, automatic = automatic, tags = tags)
            }
        }
        compose.waitForIdle()
    }

    @Test
    fun thePicker() {
        show()
        compose.screenshot("carrier_picker_light")
    }

    @Test
    fun thePickerInDark() {
        show(dark = true)
        compose.screenshot("carrier_picker_dark")
    }

    @Test
    fun aSearch() {
        show()
        compose.onNodeWithTag(CARRIER_PICKER_SEARCH).performTextInput("colis")
        compose.screenshot("carrier_picker_search_light")
    }

    @Test
    fun theRailHeldOnALetter() {
        show()
        val letters = CarrierPickerSearch.letterSections(DemoCards.identities.catalog, AppLanguage.En).map { it.letter }
        val rail = compose.onNodeWithTag(LETTER_RAIL)
        val bounds = rail.fetchSemanticsNode().boundsInRoot
        val padding = 12 * compose.density.density
        val row = (bounds.height - 2 * padding) / letters.size
        compose.mainClock.autoAdvance = false
        rail.performTouchInput { down(Offset(bounds.width / 2, padding + row * letters.indexOf("M") + row / 2)) }
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
        compose.screenshot("carrier_picker_rail_light")
        rail.performTouchInput { up() }
    }

    @Test
    fun atTheLargestTextSize() {
        show(fontScale = 2f, automatic = PICKER_AUTOMATIC)
        compose.screenshot("carrier_picker_accessibility_light")
    }
}
