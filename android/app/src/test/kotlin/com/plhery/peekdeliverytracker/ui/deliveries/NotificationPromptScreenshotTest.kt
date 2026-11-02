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
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The invitation to turn notifications on under the demo's parcels in an account, with the iPhone's bars: light and
 * dark, turning on, failed, and among the cards at the largest Android text size, its buttons stacked.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class NotificationPromptScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private lateinit var view: View
    private lateinit var source: FakeDeliveries

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 10), email = "reader@example.com"))

    /** The deliveries the store invites to turn notifications on, with the window's bars. */
    private fun show(dark: Boolean = false, fontScale: Float = 1f) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply { shouldInviteNotifications.value = true }
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale)) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = reader, dark = dark, reduceMotion = true, source = source, identities = pinnedIdentities)
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
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun turnOn() {
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_ENABLE).performClick()
        settle()
    }

    @Test
    fun theInvitation() {
        show()
        compose.screenshot("notification_prompt_light")
    }

    @Test
    fun theInvitationInDark() {
        show(dark = true)
        compose.screenshot("notification_prompt_dark")
    }

    @Test
    fun turningOn() {
        show()
        source.accountWork = CompletableDeferred()
        turnOn()
        compose.screenshot("notification_prompt_enabling")
    }

    @Test
    fun turningOnFailed() {
        show()
        source.enableFailure = DeliveryApiError.PushTokenUnavailable()
        turnOn()
        compose.screenshot("notification_prompt_error")
    }

    @Test
    fun amongTheCardsAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("notification_prompt_text2")
    }
}
