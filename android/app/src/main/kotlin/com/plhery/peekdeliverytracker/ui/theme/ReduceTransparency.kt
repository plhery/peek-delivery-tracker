package com.plhery.peekdeliverytracker.ui.theme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.PowerManager
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
 * nearest is the system turning its own window blurs off (`WindowManager.isCrossWindowBlurEnabled`): with the developer
 * option "Disable window blurs", or on a device that can't afford them. Battery saver turns them off too, but the
 * iPhone keeps its materials in Low Power Mode, so it doesn't count.
 */
val LocalReduceTransparency = staticCompositionLocalOf { false }

/** Whether materials are drawn solid, from what the system says of its window blurs and of battery saver. */
internal class WindowBlurs {
    /**
     * Whether blurs were on when the saver was last off: what they would be without it. Until the saver is seen off,
     * they are taken to be on.
     */
    private var withoutSaver = true

    fun reduceTransparency(enabled: Boolean, saver: Boolean): Boolean {
        if (!saver) withoutSaver = enabled
        return !enabled && !(saver && withoutSaver)
    }
}

private val windowBlurs = WindowBlurs()

/** Whether the system has window blurs off for any reason but battery saver, read again whenever that changes. */
@Composable
fun reduceTransparencySetting(): Boolean {
    val context = LocalContext.current
    val windows = remember(context) { context.getSystemService(WindowManager::class.java) }
    val power = remember(context) { context.getSystemService(PowerManager::class.java) }
    val off = remember(windows, power) { mutableStateOf(windows.reduceTransparency(power)) }
    DisposableEffect(windows, power) {
        // The listener hears the current value at once, then each change. The saver is on by the time it turns blurs
        // off, and off by the time it lets them back.
        val listener = Consumer<Boolean> { enabled -> off.value = windowBlurs.reduceTransparency(enabled, power.isPowerSaveMode) }
        val saver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                off.value = windows.reduceTransparency(power)
            }
        }
        windows.addCrossWindowBlurEnabledListener(context.mainExecutor, listener)
        context.registerReceiver(saver, IntentFilter(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED))
        onDispose {
            windows.removeCrossWindowBlurEnabledListener(listener)
            context.unregisterReceiver(saver)
        }
    }
    return off.value
}

private fun WindowManager.reduceTransparency(power: PowerManager) =
    windowBlurs.reduceTransparency(isCrossWindowBlurEnabled, power.isPowerSaveMode)
