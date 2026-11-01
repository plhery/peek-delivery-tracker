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
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
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
 * The label scanner (B-S32) on a device that scans, signed in, with the iPhone's bars and a grey placeholder where the
 * camera shows ([FakeLabelScanner]): the scanner over Add on a phone, light and dark, and over the whole window from
 * Add's form in a regular window; Add with the scan button beside Paste; the empty list with Scan barcode beside Add a
 * parcel, and one under the other at the largest Android text size.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class TrackingScannerScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    /** `parcels` listed as the captures show them, with the iPhone's bars, the device able to scan. */
    private fun show(dark: Boolean = false, parcels: List<Parcel> = DemoCards.parcels(), fontScale: Float = 1f) {
        val source = FakeDeliveries(parcels, reader)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = reader, dark = dark, source = source, identities = pinnedIdentities, scanner = FakeLabelScanner())
                }
            }
        }
        compose.waitForIdle()
        bars()
    }

    /** The iPhone's bars. */
    private fun bars() {
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
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun click(tag: String) {
        compose.onNodeWithTag(tag).performClick()
        settle()
    }

    /** Add from the +, then its scan button. */
    private fun scan() {
        click(ADD_BUTTON)
        click(ADD_SCAN)
    }

    @Test
    fun theScannerOverAdd() {
        show()
        scan()
        compose.screenshot("scanner_phone_light")
    }

    @Test
    fun theScannerOverAddInDark() {
        show(dark = true)
        scan()
        compose.screenshot("scanner_phone_dark")
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theScannerOverAddsFormInARegularWindow() {
        show()
        scan()
        compose.screenshot("scanner_tablet_light")
    }

    @Test
    fun addsCaptureActionsWithTheScanButton() {
        show()
        click(ADD_BUTTON)
        compose.screenshot("scanner_add_actions_light")
    }

    @Test
    fun theEmptyListsScanBesideAdd() {
        show(parcels = emptyList())
        compose.screenshot("scanner_empty_list_light")
    }

    @Test
    fun theEmptyListsScanAboveAddAtTheLargestTextSize() {
        show(parcels = emptyList(), fontScale = 2f)
        compose.screenshot("scanner_empty_list_accessibility_light")
    }
}
