package com.plhery.peekdeliverytracker.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.clearAndSetSemantics
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.theme.KeepMotion
import kotlinx.coroutines.withContext

/**
 * SwiftUI's transaction, for RootView's switch between its screens. On the iPhone a change of the session's state
 * switches RootView's screens at once, unless it was made in `withAnimation`, whose animation the change carries to
 * every view it touches: the screen that goes fades out and the one that comes fades in with it (`.opacity`, the
 * default transition), and the views of the one that goes take their new values on it. The arrival's back
 * (`goBack`) is the one change made so; the way in (`showSignIn` from the first open, Add, a link's sheet,
 * settings) and the demo (`enterDemo`) are made without, and switch at once.
 *
 * [withAnimation] records the animation with the state the change led to; [PeekRoot] takes it as it switches to that
 * state, and switches at once for any other. A record it doesn't take, a change the switch never saw, goes with the
 * next switch, so an old animation never reaches a later change.
 */
@Stable
internal class RootTransaction {
    private var pending: Pending? = null

    private class Pending(val target: AuthenticationState, val animation: FiniteAnimationSpec<Float>)

    /**
     * `withAnimation(animation) { change() }`: [change] changes the session's state and gives the state it left it in,
     * which the root switches to with [animation].
     */
    fun withAnimation(animation: FiniteAnimationSpec<Float>, change: () -> AuthenticationState) {
        pending = Pending(change(), animation)
    }

    /** The animation the change to [state] was made with, or null where it was made without one. Taken once. */
    fun take(state: AuthenticationState): FiniteAnimationSpec<Float>? {
        val taken = pending ?: return null
        pending = null
        return taken.animation.takeIf { taken.target == state }
    }
}

/** RootView's three screens, which a state shows. */
internal enum class RootScreen { Launch, Arrival, Tabs }

internal val AuthenticationState.rootScreen: RootScreen
    get() = when (this) {
        AuthenticationState.Loading -> RootScreen.Launch
        AuthenticationState.Unconfigured, AuthenticationState.SignedOut -> RootScreen.Arrival
        AuthenticationState.Welcome, AuthenticationState.Demo, is AuthenticationState.SignedIn -> RootScreen.Tabs
    }

/** A screen fading out under the one that took its place, over [animation]. */
@Stable
private class LeavingScreen(val screen: RootScreen, val animation: FiniteAnimationSpec<Float>) {
    val progress = Animatable(0f)
    var gone by mutableStateOf(false)
}

/**
 * RootView's `Group { switch session.state }`: the screen [state] shows ([content]), which replaces the one before it
 * in the same frame, unless the change came with an animation ([transaction]). Then the screen before stays under the
 * new one for the animation, fading out as the new one fades in over it, untouchable and out of TalkBack's reach, and
 * goes once it is done; it is drawn with the new state meanwhile, as SwiftUI's removed view takes the new values. The
 * fade runs under Reduce Motion too, as the opacity transition does on the iPhone ([KeepMotion]). Each screen keeps
 * its state while it shows.
 */
@Composable
internal fun RootSwitch(state: AuthenticationState, transaction: RootTransaction, content: @Composable (RootScreen) -> Unit) {
    val screen = state.rootScreen
    val last = remember { arrayOf(screen) }
    val leaving = remember(screen) {
        val from = last[0]
        last[0] = screen
        val animation = transaction.take(state)
        if (animation != null && from != screen) LeavingScreen(from, animation) else null
    }
    LaunchedEffect(leaving) {
        val fading = leaving ?: return@LaunchedEffect
        withContext(KeepMotion) { fading.progress.animateTo(1f, fading.animation) }
        fading.gone = true
    }
    val under = leaving?.takeUnless { it.gone }
    Box(propagateMinConstraints = true) {
        for (shown in listOfNotNull(under?.screen, screen)) {
            key(shown) {
                val modifier = when {
                    under == null -> Modifier
                    shown == under.screen -> Modifier
                        .graphicsLayer { alpha = 1f - under.progress.value }
                        .clearAndSetSemantics {}
                        .pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() } } }
                    else -> Modifier.graphicsLayer { alpha = under.progress.value }
                }
                Box(modifier, propagateMinConstraints = true) { content(shown) }
            }
        }
    }
}
