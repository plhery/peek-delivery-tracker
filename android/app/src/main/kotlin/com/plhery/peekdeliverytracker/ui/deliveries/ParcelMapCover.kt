package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.map.rememberParcelRoute
import com.plhery.peekdeliverytracker.ui.map.rememberWorldAtlas
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.tabs.LocalWindowHinge
import com.plhery.peekdeliverytracker.ui.tabs.WindowHinge
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.shownCarrierIdentity
import java.util.UUID
import kotlinx.coroutines.launch

/** The map of the page with `pageId`, open over the window (`showingMap`): it shows that page's parcel as it is now. */
@Stable
internal class MapCoverRequest(val pageId: UUID)

/**
 * The page's map (B-S19), over the whole window, its tab bar and the system's bars too, where the tab shell is there to
 * draw it ([LocalTabOverlay]), over the page otherwise. Nothing while it is closed.
 */
@Composable
internal fun ParcelMapHost(state: DeliveriesState) {
    val request = state.mapCover
    val host = LocalTabOverlay.current
    // The shell draws its layers outside the tab, where the hinge isn't known: it is read here.
    val hinge = LocalWindowHinge.current
    val content: (@Composable () -> Unit)? = request?.let { { ParcelMapCover(state, it, hinge) } }
    if (host == null) {
        content?.invoke()
        return
    }
    OverlayLayer(host, MAP_COVER, content)
}

/**
 * `.fullScreenCover { ParcelMapScreen(…) }`: the map comes up from the window's foot over 0.5 s and goes back down as
 * its button or the back gesture closes it, and goes at once with its page or its parcel. TalkBack reaches only the
 * map, from the map itself, and goes back to the globe as it closes.
 */
@Composable
private fun ParcelMapCover(state: DeliveriesState, request: MapCoverRequest, hinge: WindowHinge?) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val live = state.liveMenuParcel(request.pageId)
    val parcel = state.sample(request.pageId) ?: live
    val gone = parcel == null || state.navigator.navigation.shown != request.pageId
    LaunchedEffect(gone) { if (gone) state.mapCover = null }
    val atlas = rememberWorldAtlas()
    val identity = parcel?.let { shownCarrierIdentity(it, localizer, language) }
    val shown = remember { Animatable(0f) }
    var closing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val mapFocus = remember { AccessibilityFocus() }
    LaunchedEffect(Unit) {
        mapFocus.request()
        shown.animateTo(1f, Motion.smooth(PRESENT_DURATION))
    }
    val close = {
        if (!closing) {
            closing = true
            scope.launch {
                shown.animateTo(0f, Motion.smooth(PRESENT_DURATION))
                state.mapCover = null
            }
        }
    }
    BackHandler(enabled = !closing) { close() }
    if (parcel == null || gone || atlas == null || identity == null) return

    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { translationY = (1 - shown.value) * size.height }
            // What lies under the map takes no touches while it is open.
            .pointerInput(Unit) { detectTapGestures { } },
    ) {
        ParcelMapScreen(
            atlas = atlas, route = rememberParcelRoute(parcel, atlas, language), stage = parcel.currentStage,
            accent = identity.ink, hinge = hinge, onClose = { close() }, mapFocus = mapFocus,
        )
    }
}

private const val PRESENT_DURATION = 0.5
