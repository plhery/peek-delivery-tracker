package com.plhery.peekdeliverytracker.ui.theme

import android.content.ContentResolver
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.withContext

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

/**
 * Runs Compose animations at their own speed whatever the animator duration scale. Android scales every animation's
 * time by it (MotionDurationScale), and "Remove animations" sets it to 0, so each jumps to its end. Under Reduce Motion
 * iOS drops some animations but keeps others: the arrival's `.linear(duration: 0.75)` (AddParcelView), the 0.15 s
 * crossfades (SignInView), the `.easeOut(duration: 0.15)` and `.linear(duration: 0.1)` of FriendsView, spinners.
 *
 * Which to use:
 * - An animation iOS drops under Reduce Motion (`reduceMotion ? nil : …`): Compose's own, as usual.
 * - One iOS keeps: an `Animatable` run in this context (`withContext(KeepMotion) { animateTo(…) }`,
 *   `scope.launch(KeepMotion)`), [animateKeptFloatAsState] (a crossfade's opacity), or an [AnimatedValue] on frame
 *   time. A spinner is an `Animatable` turning with `infiniteRepeatable` in this context.
 * - `animate*AsState`, `Transition`, `AnimatedVisibility`, `Crossfade` and `rememberInfiniteTransition` take the scale
 *   from the composition and can't keep motion; use one of the above for an animation iOS keeps.
 */
object KeepMotion : MotionDurationScale {
    override val scaleFactor: Float get() = 1f
}

/** `animateFloatAsState` for an animation iOS keeps under Reduce Motion ([KeepMotion]). */
@Composable
fun animateKeptFloatAsState(targetValue: Float, animationSpec: AnimationSpec<Float>): State<Float> {
    val animatable = remember { Animatable(targetValue) }
    LaunchedEffect(targetValue, animationSpec) {
        withContext(KeepMotion) { animatable.animateTo(targetValue, animationSpec) }
    }
    return animatable.asState()
}
