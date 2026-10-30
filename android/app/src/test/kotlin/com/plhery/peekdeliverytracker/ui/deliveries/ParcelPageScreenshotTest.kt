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
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
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
 * A parcel's page's frame on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), in
 * the demo at the captures' moment: Birthday gift's page at rest over the list, light and dark and in Russian and
 * Chinese, its ••• menu open, its rename alert light and dark and at an accessibility text size, its delete question
 * light and dark, the list's error alert light and dark, the page of a parcel gone, and the page beside the list on a
 * tablet on its side, the history beside the pass. The page dragged down, under its bar, is
 * ParcelZoomScreenshotTest's.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelPageScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState

    /**
     * The demo's list in `language` with the window's bars in points: the iPhone's status bar 62 tall and gesture bar 24
     * by default.
     */
    private fun show(
        dark: Boolean = false,
        top: Int = 62,
        bottom: Int = 24,
        fontScale: Float = 1f,
        language: AppLanguage = AppLanguage.En,
    ) {
        val source = FakeDeliveries(DemoCards.parcels(language), AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = AuthenticationState.Demo, language = language, dark = dark, reduceMotion = false, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, (top * scale).toInt(), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, (bottom * scale).toInt()))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    /** Birthday gift's page in `language`, landed. */
    private fun open(language: AppLanguage = AppLanguage.En) {
        compose.openCard(DemoCards.parcel("Birthday gift", language).label)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    @Test
    fun thePageAtRest() {
        show()
        open()
        compose.screenshot("page_light")
    }

    @Test
    fun thePageAtRestInDark() {
        show(dark = true)
        open()
        compose.screenshot("page_dark")
    }

    @Test
    fun thePageAtRestInRussian() {
        show(language = AppLanguage.Ru)
        open(AppLanguage.Ru)
        compose.screenshot("page_ru")
    }

    @Test
    fun thePageAtRestInChinese() {
        show(language = AppLanguage.Zh)
        open(AppLanguage.Zh)
        compose.screenshot("page_zh")
    }

    @Test
    fun theMenuOpen() {
        show()
        open()
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.screenshot("page_menu_light")
    }

    /** The rename alert over the page, on the parcel's name, as its ••• menu's Rename opens it. */
    private fun rename() {
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(menuItemTag(ParcelMenuItem.Rename)).performClick()
        compose.mainClock.advanceTimeBy(1_000)
    }

    @Test
    fun theRenameAlert() {
        show()
        open()
        rename()
        compose.screenshot("page_rename_light")
    }

    @Test
    fun theRenameAlertInDark() {
        show(dark = true)
        open()
        rename()
        compose.screenshot("page_rename_dark")
    }

    /** At an accessibility text size, its buttons one over the other, Cancel last. */
    @Test
    fun theRenameAlertAtAnAccessibilityTextSize() {
        show(fontScale = 2f)
        open()
        rename()
        compose.screenshot("page_rename_light_text2")
    }

    /** The delete question off the page's •••, as its menu's Delete asks it. */
    private fun askDelete() {
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(menuItemTag(ParcelMenuItem.Delete)).performClick()
        compose.mainClock.advanceTimeBy(1_000)
    }

    @Test
    fun theDeleteQuestion() {
        show()
        open()
        askDelete()
        compose.screenshot("delete_popover_page_light")
    }

    @Test
    fun theDeleteQuestionInDark() {
        show(dark = true)
        open()
        askDelete()
        compose.screenshot("delete_popover_page_dark")
    }

    /** The list's alert when an action failed, its words under its title and one Close. */
    @Test
    fun theErrorAlert() {
        show()
        failed()
        compose.screenshot("error_alert_light")
    }

    @Test
    fun theErrorAlertInDark() {
        show(dark = true)
        failed()
        compose.screenshot("error_alert_dark")
    }

    private fun failed() {
        compose.runOnUiThread { deliveries.actionError = DeliveryApiError.ParcelMissing().message }
        compose.mainClock.advanceTimeBy(1_000)
    }

    @Test
    fun aParcelGone() {
        show()
        compose.runOnUiThread { deliveries.navigator.open(UUID(0, 1)) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.screenshot("page_missing_light")
    }

    /** An iPad on its side, 1376 × 1032: the list beside Birthday gift's page, which has room for the two columns. */
    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun besideTheListOnATablet() {
        show(top = 24, bottom = 24)
        open()
        compose.screenshot("page_tablet_light")
    }
}
