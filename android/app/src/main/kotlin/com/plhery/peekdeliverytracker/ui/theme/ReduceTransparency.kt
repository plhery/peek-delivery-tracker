package com.plhery.peekdeliverytracker.ui.theme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
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

    /** What `enabled` blurs read as with the saver as it is, without taking them as the blurs without it. */
    fun reduceTransparency(enabled: Boolean, saver: Boolean): Boolean = !enabled && !(saver && withoutSaver)

    /** The blur listener's news, or a read once the window manager has caught up with the saver. */
    fun settled(enabled: Boolean, saver: Boolean): Boolean {
        if (!saver) withoutSaver = enabled
        return reduceTransparency(enabled, saver)
    }

    /**
     * The saver turned on or off, with blurs read at once: what that draws, or null to read again in [SETTLE_MILLIS].
     * The window manager may not have let blurs back yet when the saver turns off, so that read would take them as off
     * without it; the blur listener tells when they come back, and the read later tells the blurs that stay off.
     */
    fun saverChanged(enabled: Boolean, saver: Boolean): Boolean? = if (saver) reduceTransparency(enabled, saver) else null

    companion object {
        /** How long after the saver turns off the window manager has surely let blurs back. */
        const val SETTLE_MILLIS = 1_000L
    }
}

private val windowBlurs = WindowBlurs()

/** Whether the system has window blurs off for any reason but battery saver, read again whenever that changes. */
@Composable
fun reduceTransparencySetting(): Boolean {
    val context = LocalContext.current
    val windows = remember(context) { context.getSystemService(WindowManager::class.java) }
    val power = remember(context) { context.getSystemService(PowerManager::class.java) }
    // Read, not taken in: the listener takes the same value as soon as it is added.
    val off = remember(windows, power) { mutableStateOf(windowBlurs.reduceTransparency(windows.isCrossWindowBlurEnabled, power.isPowerSaveMode)) }
    DisposableEffect(windows, power) {
        // The listener hears the current value at once, then each change. The saver is on by the time it turns blurs
        // off, and off by the time it lets them back. With blurs off for another reason as well, it hears nothing as
        // the saver comes or goes: the saver's own broadcast tells.
        val listener = Consumer<Boolean> { enabled -> off.value = windowBlurs.settled(enabled, power.isPowerSaveMode) }
        val handler = Handler(context.mainLooper)
        val reread = Runnable { off.value = windowBlurs.settled(windows.isCrossWindowBlurEnabled, power.isPowerSaveMode) }
        val saver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                handler.removeCallbacks(reread)
                val now = windowBlurs.saverChanged(windows.isCrossWindowBlurEnabled, power.isPowerSaveMode)
                if (now != null) off.value = now else handler.postDelayed(reread, WindowBlurs.SETTLE_MILLIS)
            }
        }
        windows.addCrossWindowBlurEnabledListener(context.mainExecutor, listener)
        context.registerReceiver(saver, IntentFilter(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED))
        onDispose {
            windows.removeCrossWindowBlurEnabledListener(listener)
            context.unregisterReceiver(saver)
            handler.removeCallbacks(reread)
        }
    }
    return off.value
}
