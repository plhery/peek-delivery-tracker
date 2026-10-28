package com.plhery.peekdeliverytracker.ui.theme

import android.app.UiModeManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * iOS's Increase Contrast (A-AX02, X-AD44): Android 14's contrast level above standard, followed live. Android 12 and
 * 13 have no such setting, so there it is always off.
 */
@Composable
fun increasedContrastSetting(): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) return false
    return contrastAboveStandard()
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
private fun contrastAboveStandard(): Boolean {
    val context = LocalContext.current
    val manager = remember(context) { context.getSystemService(UiModeManager::class.java) }
    val high = remember(manager) { mutableStateOf(manager.contrast > 0f) }
    DisposableEffect(manager) {
        val listener = UiModeManager.ContrastChangeListener { contrast -> high.value = contrast > 0f }
        manager.addContrastChangeListener(context.mainExecutor, listener)
        high.value = manager.contrast > 0f
        onDispose { manager.removeContrastChangeListener(listener) }
    }
    return high.value
}
