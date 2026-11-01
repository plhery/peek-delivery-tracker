package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.arrival.signInConfiguration
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.NoLabelScanner
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelLinkSource
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.time.Instant
import kotlin.coroutines.ContinuationInterceptor

/**
 * The root as the app shows it, in the app's copy for `language`, at the captures' moment ([CAPTURED]) unless [now]
 * says otherwise, the deliveries showing [source]'s parcels (the demo's unless a test gives its own), under Reduce
 * Motion unless asked otherwise. The session is told to the deliveries too, and their screens counted to [onView] with
 * the tabs'; their app's scope runs on the test's clock. The carriers are the bundled catalog's unless [identities] pins others. Transparency is full
 * unless [reduceTransparency]. The tabs show [browserCheck]'s sheet, where a test gives one, and scan labels with
 * [scanner] (none unless a test gives one). A parcel's link opens over it from [links], where a test gives them. The
 * first open stands over the tabs for a guest with nothing followed, unless a test draws its own [deliveries]. The
 * arrival signs in with [session], whose state then is the root's in place of [state], its runs going on in the
 * host's scope as the app's do in its own; without one it has the debug build's, where sign-in is unavailable, which
 * never changes the state.
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
    browserCheck: TurnstileSheet? = null,
    scanner: LabelScanner = NoLabelScanner,
    links: ParcelLinkSource? = null,
    now: () -> Instant = { CAPTURED },
    session: SessionStore? = null,
    deliveries: (@Composable () -> Unit)? = null,
) {
    val effects = rememberCoroutineScope()
    val signIn = session ?: remember { signInSession(effects, signInConfiguration(configured = false)) }
    val signedIn = session?.stateFlow?.collectAsState()
    val shownState = signedIn?.value ?: state
    val localizer = remember(language) { Localizer(language, variant = "android") }
    val shown = source ?: remember(language) { FakeDeliveries(DemoCards.parcels(language), shownState) }
    shown.onView = onView
    // The app's scope keeps the test's time, as the composition's effects do.
    effects.coroutineContext[ContinuationInterceptor]?.let { shown.dispatcher = it }
    shown.frameClock = effects.coroutineContext[MonotonicFrameClock]
    SideEffect { shown.session.value = shownState }
    CompositionLocalProvider(LocalNow provides now) {
        PeekTheme(
            dark = dark, highContrast = false, reduceMotion = reduceMotion, reduceTransparency = reduceTransparency,
            carrierIdentities = identities,
        ) {
            // The first open over the tabs is the deliveries' own: none over a page a test draws in their place.
            PeekRoot(shownState, localizer, signIn, onView, inbox, browserCheck, scanner, links, source = shown.takeIf { deliveries == null }, work = effects) {
                if (deliveries != null) {
                    deliveries()
                } else {
                    DeliveriesTab(shown, inbox = inbox)
                }
            }
        }
    }
}
