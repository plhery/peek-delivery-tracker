package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
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
 * A parcel's alerts over its page, with the iPhone's bars, in an account with its email on: at the height of what they
 * show, light and dark, at the largest Android text size, and while the notifications' switch saves with Done waiting
 * for it.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelAlertsScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    /** The account shown: the demo has none, and so no delivery email. */
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 8), email = "reader@example.com"))

    /** Birthday gift's page with the window's bars, then its alerts. */
    private fun show(dark: Boolean = false, fontScale: Float = 1f) {
        source = FakeDeliveries(DemoCards.parcels(), reader).apply { signedInWithEmail(true, address = "reader@example.com") }
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = reader, dark = dark, reduceMotion = false, source = source, identities = pinnedIdentities)
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
        compose.openCard(DemoCards.parcel("Birthday gift").label)
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(PAGE_BELL).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    @Test
    fun theAlerts() {
        show()
        compose.screenshot("parcel_alerts_light")
    }

    @Test
    fun theAlertsInDark() {
        show(dark = true)
        compose.screenshot("parcel_alerts_dark")
    }

    @Test
    fun theAlertsAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("parcel_alerts_text2")
    }

    @Test
    fun aSwitchSavingWithDoneWaitingForIt() {
        show()
        source.muting = CompletableDeferred()
        compose.onNodeWithTag(PARCEL_ALERTS_NOTIFICATIONS).performSemanticsAction(SemanticsActions.OnClick)
        compose.onNodeWithTag(PARCEL_ALERTS_DONE).performClick()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.screenshot("parcel_alerts_saving")
    }
}
