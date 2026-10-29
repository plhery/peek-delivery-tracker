package com.plhery.peekdeliverytracker.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesTab
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelListPlaceholder
import com.plhery.peekdeliverytracker.ui.deliveries.ParcelPagePlaceholder
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.PeekMark
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TabShell
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/** RootView (A-D20): what the session's state shows, from the container's stores. */
@Composable
fun PeekRoot(container: AppContainer) {
    val state by container.session.stateFlow.collectAsState()
    val parcels by container.parcels.parcelsFlow.collectAsState()
    PeekRoot(state, container.localizer, onView = { container.analytics.view(it) }) {
        val rows = remember(parcels) { parcels.map { it.id.toString() to it.label } }
        DeliveriesTab(
            list = { open -> ParcelListPlaceholder(rows, open) },
            page = { id -> ParcelPagePlaceholder(rows.firstOrNull { it.first == id }?.second) },
        )
    }
}

/**
 * RootView's routing, in its order: the launch screen while the session is read, the arrival while nobody is signed in
 * and this phone follows nothing on its own, the tabs otherwise. A state replaces the one before it at once. The tab
 * chosen is kept with the window and through process death, as `@SceneStorage("tab")` keeps it, whatever the state
 * shows in between.
 */
@Composable
internal fun PeekRoot(
    state: AuthenticationState,
    localizer: Localizer,
    onView: (String) -> Unit,
    deliveries: @Composable () -> Unit,
) {
    val language by localizer.languageFlow.collectAsState()
    var tab by rememberSaveable { mutableIntStateOf(PeekTab.Deliveries.ordinal) }
    when (state) {
        AuthenticationState.Loading -> LaunchView(remember(language) { localizer.text("auth.loading") })
        AuthenticationState.Unconfigured, AuthenticationState.SignedOut -> ArrivalPlaceholder()
        AuthenticationState.Welcome, AuthenticationState.Demo, is AuthenticationState.SignedIn -> {
            val labels = remember(language) { PeekTab.entries.map { localizer.text(it.copyKey) } }
            TabShell(selection = tab, onSelect = { tab = it }, labels = labels, onView = onView) { shown ->
                when (shown) {
                    PeekTab.Deliveries -> deliveries()
                    PeekTab.Passport, PeekTab.Friends -> TabPlaceholder(shown)
                }
            }
        }
    }
}

/**
 * LaunchView (A-ST01): restoring the session takes a frame or two, so the screen stays as the launch screen left it
 * and shows progress only after 600 ms, fading in over 0.2 s, rather than flashing a spinner. The fade is kept under
 * Reduce Motion, as SwiftUI keeps an opacity transition.
 */
@Composable
internal fun LaunchView(loading: String) {
    var slow by remember { mutableStateOf(false) }
    val opacity = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(SLOW_MILLIS)
        slow = true
        withContext(KeepMotion) { opacity.animateTo(1f, Motion.easeOut(0.2)) }
    }
    val colors = PeekTheme.colors
    Box(Modifier.fillMaxSize().background(colors.background).testTag(LAUNCH), contentAlignment = Alignment.Center) {
        if (slow) {
            Column(
                Modifier.safeDrawingPadding().graphicsLayer { alpha = opacity.value },
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PeekMark(78.dp)
                ActivityIndicator()
                BasicText(loading, style = PeekTheme.type.subheadline.copy(color = colors.secondaryLabel))
            }
        }
    }
}

/** The arrival and sign-in until they are ported: the page's background. */
@Composable
private fun ArrivalPlaceholder() {
    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background).testTag(ARRIVAL))
}

/** The passport and friends until they are ported: the page's background. */
@Composable
private fun TabPlaceholder(tab: PeekTab) {
    Box(Modifier.fillMaxSize().background(PeekTheme.colors.background).testTag(tab.screen))
}

private const val SLOW_MILLIS = 600L

/** The test tags of the launch screen and the arrival. */
const val LAUNCH = "launch"
const val ARRIVAL = "arrival"
