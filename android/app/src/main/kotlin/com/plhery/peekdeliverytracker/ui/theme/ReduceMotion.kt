package com.plhery.peekdeliverytracker.ui.theme

import android.content.ContentResolver
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

/**
 * Whether the phone asks for less motion, iOS's Reduce Motion (X-AD19): Android's "Remove animations", which sets the
 * animator duration scale to 0. Every reduced-motion rule of the iPhone app reads this.
 */
val LocalReduceMotion = staticCompositionLocalOf { false }

/** Whether animations are off on the phone, read again whenever the setting changes. */
@Composable
fun reduceMotionSetting(): Boolean {
    val resolver = LocalContext.current.contentResolver
    val off = remember(resolver) { mutableStateOf(animationsOff(resolver)) }
    DisposableEffect(resolver) {
        val observer = object : ContentObserver(Handler(Looper.getMainLooper())) {
            override fun onChange(selfChange: Boolean) {
                off.value = animationsOff(resolver)
            }
        }
        resolver.registerContentObserver(Settings.Global.getUriFor(Settings.Global.ANIMATOR_DURATION_SCALE), false, observer)
        // The setting may have changed between the first read and the observer starting.
        off.value = animationsOff(resolver)
        onDispose { resolver.unregisterContentObserver(observer) }
    }
    return off.value
}

/** The setting itself rather than ValueAnimator's copy of it, which may catch up only after the observer hears. */
private fun animationsOff(resolver: ContentResolver): Boolean =
    Settings.Global.getFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f
