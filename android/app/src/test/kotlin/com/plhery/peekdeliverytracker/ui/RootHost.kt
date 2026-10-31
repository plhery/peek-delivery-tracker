package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.coroutines.ContinuationInterceptor

/**
 * The root as the app shows it, in the app's copy for `language`, at the captures' moment ([CAPTURED]), the deliveries
 * showing [source]'s parcels (the demo's unless a test gives its own), under Reduce Motion unless asked otherwise. The
 * session is told to the deliveries too, and their screens counted to [onView] with the tabs'; their app's scope runs
 * on the test's clock. The carriers are the bundled catalog's unless [identities] pins others. Transparency is full
 * unless [reduceTransparency].
 */
@Composable
fun RootHost(
    state: AuthenticationState = AuthenticationState.Welcome,
    language: AppLanguage = AppLanguage.En,
    dark: Boolean = false,
    reduceMotion: Boolean = true,
    onView: (String) -> Unit = {},
    source: FakeDeliveries? = null,
    inbox: DeliveriesInbox? = null,
    identities: CarrierIdentities = DemoCards.identities,
    reduceTransparency: Boolean = false,
    deliveries: (@Composable () -> Unit)? = null,
) {
    val localizer = remember(language) { Localizer(language, variant = "android") }
    val shown = source ?: remember(language) { FakeDeliveries(DemoCards.parcels(language), state) }
    shown.onView = onView
    // The app's scope keeps the test's time, as the composition's effects do.
    val effects = rememberCoroutineScope()
    effects.coroutineContext[ContinuationInterceptor]?.let { shown.dispatcher = it }
    shown.frameClock = effects.coroutineContext[MonotonicFrameClock]
    SideEffect { shown.session.value = state }
    CompositionLocalProvider(LocalNow provides { CAPTURED }) {
        PeekTheme(
            dark = dark, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = reduceTransparency,
            carrierIdentities = identities,
        ) {
            PeekRoot(state, localizer, onView, inbox) {
                if (deliveries != null) {
                    deliveries()
                } else {
                    DeliveriesTab(shown, inbox = inbox)
                }
            }
        }
    }
}
