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
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
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
 * The delivery email offered on a delivered parcel's page, with the iPhone's bars, in an account that has never chosen:
 * open, Turn on saving, failed and accepted, light and dark, and open at the largest Android text size. Under Reduce
 * Motion, so the delivered box is at rest: with motion, where it is depends on when its map was made.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class EmailOfferScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    /** The account shown: the demo has none, and so no delivery email. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 9), email = "reader@example.com"))

    private val delivered = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    /** The delivered parcel's page with the window's bars. */
    private fun show(dark: Boolean = false, fontScale: Float = 1f) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply { signedInWithEmail(null, address = "reader@example.com") }
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
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
        compose.waitForIdle()
        compose.runOnUiThread { deliveries.navigator.open(delivered.id) }
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** Turn on, held by the account until the test lets it go. */
    private fun saving() {
        source.choosing = CompletableDeferred()
        compose.onNodeWithTag(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        settle()
    }

    /** Turn on, failed. */
    private fun failing() {
        source.failure = IllegalStateException("No connection")
        compose.onNodeWithTag(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        settle()
    }

    /** Turn on, saved. */
    private fun accepting() {
        compose.onNodeWithTag(PAGE_EMAIL_OFFER_TURN_ON).performClick()
        settle()
    }

    @Test
    fun theOffer() {
        show()
        compose.screenshot("email_offer_open_light")
    }

    @Test
    fun theOfferInDark() {
        show(dark = true)
        compose.screenshot("email_offer_open_dark")
    }

    @Test
    fun theOfferAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("email_offer_text2")
    }

    @Test
    fun turnOnSaving() {
        show()
        saving()
        compose.screenshot("email_offer_saving_light")
    }

    @Test
    fun turnOnSavingInDark() {
        show(dark = true)
        saving()
        compose.screenshot("email_offer_saving_dark")
    }

    @Test
    fun turnOnFailed() {
        show()
        failing()
        compose.screenshot("email_offer_failed_light")
    }

    @Test
    fun turnOnFailedInDark() {
        show(dark = true)
        failing()
        compose.screenshot("email_offer_failed_dark")
    }

    @Test
    fun accepted() {
        show()
        accepting()
        compose.screenshot("email_offer_accepted_light")
    }

    @Test
    fun acceptedInDark() {
        show(dark = true)
        accepting()
        compose.screenshot("email_offer_accepted_dark")
    }
}
