package com.plhery.peekdeliverytracker.ui.theme

import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import java.util.function.Consumer

/**
 * Whether materials are drawn solid, as iOS draws them under Reduce Transparency. Android has no such setting; the
 * nearest is the system turning its own window blurs off (`WindowManager.isCrossWindowBlurEnabled`): in battery saver,
 * with the developer option "Disable window blurs", or on a device that can't afford them.
 */
val LocalReduceTransparency = staticCompositionLocalOf { false }

/** Whether the system has window blurs off, read again whenever that changes. */
@Composable
fun reduceTransparencySetting(): Boolean {
    val context = LocalContext.current
    val windows = remember(context) { context.getSystemService(WindowManager::class.java) }
    val off = remember(windows) { mutableStateOf(!windows.isCrossWindowBlurEnabled) }
    DisposableEffect(windows) {
        // The listener hears the current value at once, then each change.
        val listener = Consumer<Boolean> { enabled -> off.value = !enabled }
        windows.addCrossWindowBlurEnabledListener(context.mainExecutor, listener)
        onDispose { windows.removeCrossWindowBlurEnabledListener(listener) }
    }
    return off.value
}
