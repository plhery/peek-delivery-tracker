package com.plhery.peekdeliverytracker.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
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
 * [withAnimation] records the animation with the state the change led to. [RootSwitch] reads it as it composes the
 * switch to that state ([animation]), and is done with it once that switch is on screen ([settle], from a SideEffect):
 * a composition thrown away takes nothing, and a record the switch didn't use, a change it never saw, goes with the
 * next change it shows, so an old animation never reaches a later change. Nothing is saved: a window made again, or a
 * state that :core changes on its own (a sign-out elsewhere, a refresh that fails), switches at once.
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

    /** The animation the change to [state] was made with, or null where it was made without one. */
    fun animation(state: AuthenticationState): FiniteAnimationSpec<Float>? = pending?.takeIf { it.target == state }?.animation

    /** The switch has shown a change: the record, used or not, goes with it. */
    fun settle() {
        pending = null
    }
}

/** Told each screen's opacity as the root draws it, while one fades under another: the tests follow the fade. */
internal val LocalRootFade = staticCompositionLocalOf<((RootScreen, Float) -> Unit)?> { null }

/** RootView's three screens, which a state shows. */
internal enum class RootScreen { Launch, Arrival, Tabs }

internal val AuthenticationState.rootScreen: RootScreen
    get() = when (this) {
        AuthenticationState.Loading -> RootScreen.Launch
        AuthenticationState.Unconfigured, AuthenticationState.SignedOut -> RootScreen.Arrival
        AuthenticationState.Welcome, AuthenticationState.Demo, is AuthenticationState.SignedIn -> RootScreen.Tabs
    }

/** A screen fading out under the one that took its place, over [animation], drawn with the [state] it left on. */
@Stable
private class LeavingScreen(val screen: RootScreen, val state: AuthenticationState, val animation: FiniteAnimationSpec<Float>) {
    val progress = Animatable(0f)
    var gone by mutableStateOf(false)
}

/** The screen and the state the switch last put on screen. */
private class ShownScreen(var screen: RootScreen, var state: AuthenticationState)

/**
 * RootView's `Group { switch session.state }`: the screen [state] shows ([content], given the screen and the state to
 * draw it with), which replaces the one before it in the same frame, unless the change came with an animation
 * ([transaction]). Then the screen before stays under the new one for the animation, fading out as the new one fades
 * in over it, untouchable and out of TalkBack's reach, and goes once it is done. The one going is drawn with the state
 * the change led to, as SwiftUI's removed view takes the change's values, and keeps it: a later change while it fades reaches only the screen shown, as a view SwiftUI has
 * removed sees no more. The fade runs under Reduce Motion too, as the opacity transition does on the iPhone
 * ([KeepMotion]). Each screen keeps its state while it shows.
 */
@Composable
internal fun RootSwitch(state: AuthenticationState, transaction: RootTransaction, content: @Composable (RootScreen, AuthenticationState) -> Unit) {
    val screen = state.rootScreen
    val shown = remember { ShownScreen(screen, state) }
    val leaving = remember(screen) {
        val animation = transaction.animation(state)
        if (animation != null && shown.screen != screen) LeavingScreen(shown.screen, state, animation) else null
    }
    SideEffect {
        if (shown.state != state) transaction.settle()
        shown.screen = screen
        shown.state = state
    }
    LaunchedEffect(leaving) {
        val fading = leaving ?: return@LaunchedEffect
        withContext(KeepMotion) { fading.progress.animateTo(1f, fading.animation) }
        fading.gone = true
    }
    val under = leaving?.takeUnless { it.gone }
    val fade = LocalRootFade.current
    Box(propagateMinConstraints = true) {
        for (drawn in listOfNotNull(under?.screen, screen)) {
            key(drawn) {
                val modifier = when {
                    under == null -> Modifier
                    drawn == under.screen -> Modifier
                        .graphicsLayer {
                            alpha = 1f - under.progress.value
                            fade?.invoke(drawn, alpha)
                        }
                        .clearAndSetSemantics {}
                        .pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() } } }
                    else -> Modifier.graphicsLayer {
                        alpha = under.progress.value
                        fade?.invoke(drawn, alpha)
                    }
                }
                Box(modifier, propagateMinConstraints = true) { content(drawn, if (drawn == under?.screen) under.state else state) }
            }
        }
    }
}
