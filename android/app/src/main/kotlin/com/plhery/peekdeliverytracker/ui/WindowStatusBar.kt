package com.plhery.peekdeliverytracker.ui

import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/** Whether the window hides its status bar ([hidesStatusBar]): the bars' rows then stand clear of the window's edge. */
val LocalStatusBarHidden = staticCompositionLocalOf { false }

/**
 * Whether a window hides the status bar, as an iPhone does in landscape, where its height is compact: a window less
 * than 480 tall, wider than it is tall, with the screen to itself. A taller one, a tablet's or an unfolded foldable's,
 * keeps it, as an iPad does, and so does a window sharing the screen.
 */
internal fun hidesStatusBar(width: Dp, height: Dp, multiWindow: Boolean): Boolean =
    !multiWindow && width > height && height < COMPACT_HEIGHT

/**
 * Hides `window`'s status bar while [hidesStatusBar] says so, a swipe from the edge showing it a moment as the system
 * shows a hidden bar, and tells `content` so ([LocalStatusBarHidden]).
 */
@Composable
fun StatusBarAsOnIPhone(window: Window, multiWindow: Boolean, content: @Composable () -> Unit) {
    val size = LocalWindowInfo.current.containerSize
    val hides = with(LocalDensity.current) { hidesStatusBar(size.width.toDp(), size.height.toDp(), multiWindow) }
    DisposableEffect(window, hides) {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        if (hides) {
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(WindowInsetsCompat.Type.statusBars())
        } else {
            controller.show(WindowInsetsCompat.Type.statusBars())
        }
        onDispose {}
    }
    CompositionLocalProvider(LocalStatusBarHidden provides hides, content = content)
}

private val COMPACT_HEIGHT = 480.dp
