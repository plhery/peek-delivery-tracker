package com.plhery.peekdeliverytracker.ui.arrival

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The sign-in on the iPhone the captures were taken on (440 × 956 points at three pixels to the point), with the
 * iPhone's safe area as a phone's bars (the status bar 62 tall, the gesture bar at the foot), as the iPhone's pictures
 * show it: email sign-in alone, then the code step, its errors, the language menu, and the build without a server;
 * then the iPad's column on a tablet both ways, an unfolded foldable, and a small phone at the largest text, which
 * scrolls. Nothing reaches a server: the auth service is a fake.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class SignInScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val service = FakeAuthService()
    private lateinit var view: View

    /** The window's bars in points: the status bar at the top, the gesture bar at the bottom. */
    private class Bars(val top: Int = 62, val bottom: Int = 24)

    private fun show(
        configuration: AppConfiguration = signInConfiguration(),
        dark: Boolean = false,
        fontScale: Float = 1f,
        bars: Bars = Bars(),
    ) {
        compose.setContent {
            view = LocalView.current
            val scope = rememberCoroutineScope()
            val session = remember { signInSession(scope, configuration, service).also { it.showSignIn() } }
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(dark = dark, session = session)
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
        }
        compose.waitForIdle()
    }

    /** The address typed and sent, which the service takes: the code step. */
    private fun sendCode() {
        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(READER_EMAIL)
        compose.onNodeWithTag(EMAIL_SEND).performClick()
        compose.waitForIdle()
    }

    /** As iphone-01: email sign-in alone, its form open. */
    @Test
    fun aPhone() {
        show()
        compose.screenshot("sign_in_phone_light")
    }

    @Test
    fun aPhoneInDark() {
        show(dark = true)
        compose.screenshot("sign_in_phone_dark")
    }

    /** As iphone-03: at the largest text the page grows and scrolls; the parcel doesn't grow. */
    @Test
    fun aPhoneAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("sign_in_phone_largest_text")
    }

    /** As iphone-06: the code sent and typed. */
    @Test
    fun theCodeStep() {
        show()
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.screenshot("sign_in_phone_code")
    }

    /** As iphone-07: the service refuses the code as expired or wrong. */
    @Test
    fun aCodeRefused() {
        service.verify = FakeAuthService.Answer.Refused()
        show()
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.onNodeWithTag(CODE_VERIFY).performClick()
        compose.waitForIdle()
        compose.screenshot("sign_in_phone_code_refused")
    }

    /** As iphone-09: no connection, said under the field. */
    @Test
    fun theEmailOffline() {
        service.otp = FakeAuthService.Answer.Offline
        show()
        sendCode()
        compose.screenshot("sign_in_phone_email_offline")
    }

    /** As iphone-08: the language menu open, English ticked. */
    @Test
    fun theLanguageMenu() {
        show()
        compose.onNodeWithTag(LANGUAGE_MENU).performClick()
        compose.waitForIdle()
        compose.screenshot("sign_in_phone_menu")
    }

    /** As iphone-10: the build without a server, its notice in place of the form. */
    @Test
    fun aBuildWithoutAServer() {
        show(signInConfiguration(configured = false))
        compose.screenshot("sign_in_phone_unconfigured")
    }

    /** As iphone-11. */
    @Test
    fun aBuildWithoutAServerAtTheLargestText() {
        show(signInConfiguration(configured = false), fontScale = 2f)
        compose.screenshot("sign_in_phone_unconfigured_largest_text")
    }

    /** Google's and an OIDC provider's buttons over "Sign in with email", as a build with them shows them. */
    @Test
    fun otherWaysIn() {
        show(signInConfiguration(google = true, oidc = true))
        compose.screenshot("sign_in_phone_methods")
    }

    /** As ipad-01: the column 520 wide and the card 365, at the top, centred. */
    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun aTabletUpright() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("sign_in_tablet_portrait")
    }

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun aTabletOnItsSide() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("sign_in_tablet_landscape")
    }

    @Test
    @Config(qualifiers = "w673dp-h841dp-xxhdpi")
    fun anUnfoldedFoldable() {
        show(bars = Bars(top = 24, bottom = 24))
        compose.screenshot("sign_in_foldable")
    }

    /** A small phone at the largest text: too short for the page, it scrolls. */
    @Test
    @Config(qualifiers = "w360dp-h640dp-xxhdpi")
    fun aShortPhoneAtTheLargestText() {
        show(fontScale = 2f, bars = Bars(top = 24, bottom = 24))
        compose.screenshot("sign_in_short_phone")
    }
}
