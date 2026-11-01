package com.plhery.peekdeliverytracker.ui.settings

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.screenshot
import androidx.test.core.app.ApplicationProvider
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import java.util.UUID
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Settings on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), with its safe area
 * as a phone's bars, in light and dark: an account's, a guest's with parcels, the demo's, the data page an account's
 * and the demo's, also on a tablet, and Delivery updates an account's and the demo's; an account's at the largest text
 * and the demo's scrolled to its foot there; the iPad's form on a tablet both ways and the page sheet on an unfolded
 * foldable; the delete alert with the address typed and the demo's reset alert.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class SettingsScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom. */
    private class Bars(val top: Int = 62, val bottom: Int = 24)

    private fun show(
        session: AuthenticationState = reader,
        dark: Boolean = false,
        fontScale: Float = 1f,
        bars: Bars = Bars(),
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        // The picker shows the setting the window's theme comes from.
        AppearanceStore.of(ApplicationProvider.getApplicationContext()).set(if (dark) AppAppearance.Dark else AppAppearance.Light)
        val source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, dark = dark, source = source)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(bars.top), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bars.bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
            deliveries.account()
        }
        settle()
    }

    @After
    fun forgetAppearance() {
        AppearanceStore.of(ApplicationProvider.getApplicationContext()).set(AppAppearance.DEFAULT)
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun tap(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        settle()
    }

    /** As AccountView for an account: the address, Delivery updates off, the preferences, the data and the site. */
    @Test
    fun anAccount() {
        show()
        compose.screenshot("settings_phone_light")
    }

    @Test
    fun anAccountInDark() {
        show(dark = true)
        compose.screenshot("settings_phone_dark")
    }

    /** A guest with parcels: the offer of an account in place of the address, and no Delivery updates. */
    @Test
    fun aGuest() {
        show(AuthenticationState.Welcome) { isGuest = true }
        compose.screenshot("settings_guest_light")
    }

    @Test
    fun aGuestInDark() {
        show(AuthenticationState.Welcome, dark = true) { isGuest = true }
        compose.screenshot("settings_guest_dark")
    }

    @Test
    fun theDemo() {
        show(AuthenticationState.Demo)
        compose.screenshot("settings_demo_light")
    }

    @Test
    fun theDemoInDark() {
        show(AuthenticationState.Demo, dark = true)
        compose.screenshot("settings_demo_dark")
    }

    /** AccountDataView for an account: export and privacy, sign out and the deletion under it. */
    @Test
    fun theDataPage() {
        show()
        tap(SETTINGS_ACCOUNT_DATA)
        compose.screenshot("settings_data_light")
    }

    @Test
    fun theDataPageInDark() {
        show(dark = true)
        tap(SETTINGS_ACCOUNT_DATA)
        compose.screenshot("settings_data_dark")
    }

    /** The demo's data page (iphone-02, iphone-06): export and privacy, then the demo's reset and its exit. */
    @Test
    fun theDemosDataPage() {
        show(AuthenticationState.Demo)
        tap(SETTINGS_ACCOUNT_DATA)
        compose.screenshot("settings_data_demo_light")
    }

    @Test
    fun theDemosDataPageInDark() {
        show(AuthenticationState.Demo, dark = true)
        tap(SETTINGS_ACCOUNT_DATA)
        compose.screenshot("settings_data_demo_dark")
    }

    /** The demo's data page in the iPad's form (ipad-02). */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theDemosDataPageOnATablet() {
        show(AuthenticationState.Demo, bars = Bars(top = 24, bottom = 24))
        tap(SETTINGS_ACCOUNT_DATA)
        compose.screenshot("settings_data_demo_tablet")
    }

    /** The demo's reset alert (iphone-03): its question and message leading, Reset demo data over Cancel. */
    @Test
    fun theResetAlert() {
        show(AuthenticationState.Demo)
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_RESET_DEMO)
        compose.screenshot("settings_reset_alert")
    }

    /** NotificationSettingsView in the demo (iphone-04): notifications to turn on and the presets, without the email. */
    @Test
    fun deliveryUpdatesInTheDemo() {
        show(AuthenticationState.Demo) {
            // The demo's own preferences, which the store loads for the page (`demo.notificationPreferences`): every stage.
            notificationPreferences.value = NotificationPreferences(enabledStages = NotificationPreset.All.stages, timezone = "Europe/Zurich")
        }
        tap(SETTINGS_DELIVERY_UPDATES)
        compose.screenshot("settings_updates_demo_light")
    }

    /** NotificationSettingsView with the delivery email: notifications to turn on, the presets, Save and the email. */
    @Test
    fun deliveryUpdates() {
        show { signedInWithEmail(choice = false) }
        tap(SETTINGS_DELIVERY_UPDATES)
        compose.screenshot("settings_updates_light")
    }

    @Test
    fun deliveryUpdatesInDark() {
        show(dark = true) { signedInWithEmail(choice = false) }
        tap(SETTINGS_DELIVERY_UPDATES)
        compose.screenshot("settings_updates_dark")
    }

    /** At the largest text the rows stack their values under their titles and the appearances lose their miniatures. */
    @Test
    fun anAccountAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("settings_phone_largest_text")
    }

    /** The demo's at the largest text scrolled to its foot (iphone-08): the data, the site and the version. */
    @Test
    fun theDemoAtTheLargestTextScrolledToItsFoot() {
        show(AuthenticationState.Demo, fontScale = 2f)
        compose.onAllNodes(hasScrollAction() and hasAnyAncestor(hasTestTag(SETTINGS_SHEET))).onFirst()
            .performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 100_000f) }
        settle()
        compose.screenshot("settings_demo_largest_text_bottom")
    }

    /** The delete alert, the address typed, its action on. */
    @Test
    fun theDeleteAlert() {
        show()
        tap(SETTINGS_ACCOUNT_DATA)
        tap(SETTINGS_DELETE)
        compose.onNodeWithTag(SETTINGS_ALERT_FIELD).performTextInput("reader@example.com")
        settle()
        compose.screenshot("settings_delete_alert")
    }

    /** `FormSizing(applies: sheetsAsForms)`: the iPad's form in the middle of the window, beside the list. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletUpright() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("settings_tablet_portrait")
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletOnItsSide() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("settings_tablet_landscape")
    }

    /** Not side by side there, so not a form: the iPad's page sheet. */
    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("settings_foldable")
    }
}
