package com.plhery.peekdeliverytracker.ui.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import com.plhery.peekdeliverytracker.ui.deliveries.OverlayLayer
import com.plhery.peekdeliverytracker.ui.tabs.LocalTabOverlay
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

/**
 * `.fullScreenCover(isPresented:)`: the whole window, its tab bar and the system's bars too ([LocalTabOverlay]), on the
 * app's background. It comes up from the window's foot and goes back down on UIKit's cover spring ([CoverSpring]),
 * which no drag moves and nothing dims around: only what it holds closes it ([onDismiss] from its own button), and the
 * system's back, as long as it is [dismissible]; otherwise the back is kept from what is under it.
 * TalkBack reaches only the cover, named [paneTitle], and only a menu opened in it while one is open ([LocalSheetMenus]);
 * on its way down it takes no touch and TalkBack no longer reaches it, as UIKit's cover going down. Once it has gone,
 * [onClosed] runs. The window made again (a rotation) finds it up, without coming up again. What it holds lays out the
 * system's bars and the keyboard itself.
 */
@Composable
fun FullScreenCover(
    visible: Boolean,
    onDismiss: () -> Unit,
    paneTitle: String,
    modifier: Modifier = Modifier,
    onClosed: () -> Unit = {},
    dismissible: Boolean = true,
    content: @Composable () -> Unit,
) {
    var present by remember { mutableStateOf(visible) }
    if (visible) present = true
    val memory = rememberSaveable(saver = SheetMemory.Saver) { SheetMemory() }
    if (!present) memory.forget()
    val host = LocalTabOverlay.current
    val dismiss by rememberUpdatedState(onDismiss)
    val closed by rememberUpdatedState(onClosed)
    val layer: (@Composable () -> Unit)? = if (!present) null else {
        {
            CoverLayer(
                visible, { dismiss() }, paneTitle, modifier, memory,
                onGone = {
                    present = false
                    closed()
                },
                dismissible, content,
            )
        }
    }
    if (host == null) layer?.invoke() else OverlayLayer(host, CoverKey(paneTitle), layer)
}

private data class CoverKey(val title: String)

@Composable
private fun CoverLayer(
    visible: Boolean,
    onDismiss: () -> Unit,
    paneTitle: String,
    modifier: Modifier,
    memory: SheetMemory,
    onGone: () -> Unit,
    dismissible: Boolean,
    content: @Composable () -> Unit,
) {
    val colors = PeekTheme.colors
    val menus = remember { SheetMenus() }
    val shown = remember { Animatable(if (memory.up) 1f else 0f) }
    LaunchedEffect(visible) {
        if (visible) {
            if (shown.value < 1f) {
                // Once laid out, from the window's foot.
                withFrameNanos {}
                shown.animateTo(1f, CoverSpring)
            }
            memory.up = true
        } else {
            memory.forget()
            shown.animateTo(0f, CoverSpring)
            onGone()
        }
    }
    // The back closes it, or, while it can't be closed, goes nowhere rather than to what is under it.
    BackHandler(enabled = visible) { if (dismissible) onDismiss() }
    val leaving = !visible
    // While a menu is open over it, TalkBack reaches only the menu.
    val hidden = if (leaving || menus.content != null) Modifier.clearAndSetSemantics {} else Modifier
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val width = maxWidth
        Box(
            Modifier
                .fillMaxSize()
                .graphicsLayer { translationY = (1 - shown.value) * size.height }
                .testTag(COVER_PANEL)
                .then(modifier)
                .background(colors.background)
                .semantics { this.paneTitle = paneTitle }
                // What lies under the cover takes no touches while it is up, nor anything in it on its way down.
                .pointerInput(Unit) { detectTapGestures { } }
                .then(if (leaving) Untouchable else Modifier)
                .then(hidden),
        ) {
            CompositionLocalProvider(LocalSheetMenus provides menus, LocalSheetInsets provides SheetInsets.sheet(width)) { content() }
        }
        menus.content?.invoke()
    }
}

/** Takes every touch on its way to what it holds, which then sees none. */
private val Untouchable = Modifier.pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() }
    }
}

/** UIKit's cover, both ways: critically damped, its response 0.34 s, a little quicker than the sheets' spring. */
private val CoverSpring = Motion.springWithResponse<Float>(response = 0.34, dampingFraction = 1.0)

internal const val COVER_PANEL = "cover.panel"
