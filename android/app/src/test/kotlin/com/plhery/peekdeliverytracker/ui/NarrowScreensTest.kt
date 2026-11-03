package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryEmail
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveryEmailOffer
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.EmailOfferPhase
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelAlertSwitches
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.settings.DeliveryEmailSettings
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * iOS's fit tests (C-T178, C-T188, C-T189, C-T190): the notification card, the email settings, a parcel's alerts and
 * the email offer at a phone's narrow content widths, in every language the app ships, at Large in light and at the
 * largest text Android has in dark, each under the iPhone's height limits. Android's largest text is font scale 2.0,
 * which reads as accessibility2 ([com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize]): iOS's accessibility3, the
 * size its large-type cases use, has no Android match, so they run at accessibility2 under accessibility3's limits.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class NarrowScreensTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var language by mutableStateOf(AppLanguage.En)

    /** The largest text, in dark, or Large in light. */
    private var large by mutableStateOf(false)

    private fun label(name: String) = "$name-${language.rawValue}-${if (large) "large-dark" else "regular-light"}"

    /** Each language at each size, the content laid out again for it. */
    private fun everyLanguage(check: () -> Unit) {
        for (each in AppLanguage.entries) {
            for (largeType in listOf(false, true)) {
                compose.runOnIdle {
                    language = each
                    large = largeType
                }
                compose.waitForIdle()
                check()
            }
        }
    }

    /**
     * `renderInEveryLanguage`: [content] [width] wide with [padding] round it on the page's ground, as tall as it asks,
     * at both sizes in every language, each case of it that [cases] goes through (once by default): what [measured]
     * tags (the whole, by default) stays [width] + 2 × [padding] wide and under [regular] tall at Large, under
     * [largest] at the largest text.
     */
    private fun fitsInEveryLanguage(
        name: String,
        width: Dp,
        padding: Dp = 16.dp,
        regular: Float,
        largest: Float,
        measured: String = FIT,
        cases: List<() -> String> = listOf { name },
        content: @Composable (large: Boolean) -> Unit,
    ) {
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, if (large) 2f else 1f)) {
                ProvideLocalizer(remember(language) { Localizer(language, variant = "android") }) {
                    PeekTheme(dark = large, highContrast = false, reduceMotion = true, carrierIdentities = DemoCards.identities) {
                        Box(
                            Modifier
                                .wrapContentSize(Alignment.TopStart, unbounded = true)
                                .testTag(FIT)
                                .background(PeekTheme.colors.background)
                                .padding(padding)
                                .width(width),
                        ) { content(large) }
                    }
                }
            }
        }
        for (case in cases) {
            val named = compose.runOnIdle(case)
            everyLanguage {
                val bounds = compose.onNodeWithTag(measured, useUnmergedTree = true).getUnclippedBoundsInRoot()
                val tall = (bounds.bottom - bounds.top).value
                assertEquals(label(named), (width + padding * 2).value, (bounds.right - bounds.left).value, 0.5f)
                assertTrue("${label(named)}: $tall tall", tall < if (large) largest else regular)
            }
        }
    }

    /**
     * C-T178, `testPopupFitsNarrowScreensInEveryLanguageAndLargeType`: in a window 375 wide the card stands 343 across,
     * 16 in from each side, and with that 16 above and below it stays under 650 tall.
     */
    @Test
    @Config(qualifiers = "w375dp-h812dp-xhdpi")
    fun theNotificationCardFitsANarrowPhoneInEveryLanguageAndAtTheLargestText() {
        val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 9), email = "reader@example.com"))
        val source = FakeDeliveries(DemoCards.parcels(), reader).apply { shouldInviteNotifications.value = true }
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, if (large) 2f else 1f)) {
                RootHost(state = reader, language = language, dark = large, source = source)
            }
        }
        compose.mainClock.advanceTimeBy(1_000)
        everyLanguage {
            compose.mainClock.advanceTimeBy(1_000)
            val card = compose.onNodeWithTag(NOTIFICATIONS_PROMPT, useUnmergedTree = true).getUnclippedBoundsInRoot()
            val tall = (card.bottom - card.top).value + 2 * 16
            assertEquals(label("notification-popup"), 343f, (card.right - card.left).value, 0.5f)
            assertTrue("${label("notification-popup")}: $tall tall", tall < 650)
        }
    }

    /**
     * C-T188, `testEmailSettingsFitNarrowScreensInEveryLanguageAndLargeType`: 327 wide, under 270 tall, or under 1 060
     * at the largest text, where the group also says a save failed, the tallest it gets.
     */
    @Test
    fun theEmailSettingsFitANarrowPhoneInEveryLanguageAndAtTheLargestText() {
        fitsInEveryLanguage("email-settings", width = 327.dp, regular = 270f, largest = 1_060f) { largeType ->
            DeliveryEmailSettings(
                address = "alex@example.com", isOn = true, onChange = {}, saving = false, failed = largeType, onPrivacy = {}, onExample = {},
            )
        }
    }

    /** C-T189, `testParcelAlertsFitNarrowScreensInEveryLanguageAndLargeType`: 375 wide, no padding, under 440, or 1 200. */
    @Test
    fun aParcelsAlertsFitANarrowPhoneInEveryLanguageAndAtTheLargestText() {
        val parcel = DemoCards.parcels().first { it.currentStage == TrackingStage.OutForDelivery }.copy(label = "New sneakers")
        val email = DeliveryEmail(
            isDemo = false, address = "alex@example.com",
            preferences = NotificationPreferences(enabledStages = emptyList(), timezone = "Europe/Zurich", emailOnDelivery = true, emailAvailable = true),
        )!!
        // The sheet scrolls, so it is given more room than it takes: its column is as tall as it asks.
        fitsInEveryLanguage("parcel-alerts", width = 375.dp, padding = 0.dp, regular = 440f, largest = 1_200f, measured = PARCEL_ALERTS) {
            Box(Modifier.height(4_000.dp)) {
                ParcelAlertSwitches(parcel, email, NotificationPreset.Important, saving = null, closing = false, flip = { _, _ -> }, close = {})
            }
        }
    }

    /**
     * C-T190, `testEmailOfferFitsNarrowScreensInEveryLanguageAndLargeType`: 307 wide, in each of its four phases, under
     * 330, or 820.
     */
    @Test
    fun theEmailOfferFitsANarrowPhoneInEachPhaseEveryLanguageAndAtTheLargestText() {
        var phase by mutableStateOf<EmailOfferPhase>(EmailOfferPhase.Open)
        val phases = listOf(EmailOfferPhase.Open to "open", EmailOfferPhase.Saving(true) to "saving", EmailOfferPhase.Failed to "failed", EmailOfferPhase.Accepted to "accepted")
        val cases = phases.map { (each, name) -> { phase = each; "email-offer-$name" } }
        fitsInEveryLanguage("email-offer", width = 307.dp, regular = 330f, largest = 820f, cases = cases) {
            DeliveryEmailOffer(address = "alex@example.com", phase = phase, turnOn = {}, notNow = {})
        }
    }

    private companion object {
        const val FIT = "fit"
    }
}
