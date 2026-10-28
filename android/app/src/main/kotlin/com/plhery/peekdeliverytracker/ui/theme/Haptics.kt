package com.plhery.peekdeliverytracker.ui.theme

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.os.Build
import android.os.VibrationAttributes
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

/**
 * The feedback the iPhone app asks for (`.sensoryFeedback`, `Haptics.success()`), plus the Android gestures that have
 * a constant of their own. An impact keeps its intensity, played where the phone can scale a vibration.
 */
sealed interface SensoryFeedback {
    data object Selection : SensoryFeedback
    data object Success : SensoryFeedback
    data object Warning : SensoryFeedback
    data object Error : SensoryFeedback

    /** `.impact(weight:intensity:)`. */
    data class Impact(val weight: Weight = Weight.Medium, val intensity: Double = 1.0) : SensoryFeedback {
        enum class Weight { Light, Medium, Heavy }
    }

    /** `.impact(flexibility:intensity:)`. */
    data class FlexibleImpact(val flexibility: Flexibility, val intensity: Double = 1.0) : SensoryFeedback {
        enum class Flexibility { Soft, Solid, Rigid }
    }

    /** A drag crossing the point where letting go acts (a swipe arming archive). */
    data object GestureThreshold : SensoryFeedback

    /** A rail scrubbed through many values (the carrier picker's letters). */
    data object FrequentTick : SensoryFeedback

    data object ToggleOn : SensoryFeedback
    data object ToggleOff : SensoryFeedback
}

/**
 * The constant `View.performHapticFeedback` plays for a feedback on an Android release (X-AD17), chosen by the
 * vibration AOSP gives each constant (HapticFeedbackVibrationProvider from Android 14, PhoneWindowManager before; the
 * two agree on the older constants), so each is as strong as the iPhone's:
 * - a tick (EFFECT_TICK): selection (SEGMENT_TICK from Android 14), a light or soft impact (CONTEXT_CLICK), a gesture
 *   threshold (GESTURE_THRESHOLD_ACTIVATE from Android 14), a toggle turned on (TOGGLE_ON from Android 14, a tick
 *   at half strength);
 * - a click (EFFECT_CLICK): a medium, solid or rigid impact (VIRTUAL_KEY), success (CONFIRM);
 * - a heavy click (EFFECT_HEAVY_CLICK): a heavy impact (LONG_PRESS); a double click: warning and error (REJECT);
 * - a texture tick (EFFECT_TEXTURE_TICK), the faintest: a frequent tick (SEGMENT_FREQUENT_TICK from Android 14) and a
 *   toggle turned off (TOGGLE_OFF from Android 14, a low tick at a fifth).
 * On Android 12 and 13 a constant from 14 falls back to the older one of its strength: CONTEXT_CLICK for a tick,
 * CLOCK_TICK for a texture tick. Lint can't tell `sdk` is the release.
 */
@SuppressLint("InlinedApi")
fun hapticFeedbackConstant(feedback: SensoryFeedback, sdk: Int = Build.VERSION.SDK_INT): Int {
    val android14 = sdk >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
    return when (feedback) {
        SensoryFeedback.Selection -> if (android14) HapticFeedbackConstants.SEGMENT_TICK else HapticFeedbackConstants.CONTEXT_CLICK
        SensoryFeedback.Success -> HapticFeedbackConstants.CONFIRM
        SensoryFeedback.Warning, SensoryFeedback.Error -> HapticFeedbackConstants.REJECT
        is SensoryFeedback.Impact -> when (feedback.weight) {
            SensoryFeedback.Impact.Weight.Light -> HapticFeedbackConstants.CONTEXT_CLICK
            SensoryFeedback.Impact.Weight.Medium -> HapticFeedbackConstants.VIRTUAL_KEY
            SensoryFeedback.Impact.Weight.Heavy -> HapticFeedbackConstants.LONG_PRESS
        }
        is SensoryFeedback.FlexibleImpact -> when (feedback.flexibility) {
            SensoryFeedback.FlexibleImpact.Flexibility.Soft -> HapticFeedbackConstants.CONTEXT_CLICK
            SensoryFeedback.FlexibleImpact.Flexibility.Solid,
            SensoryFeedback.FlexibleImpact.Flexibility.Rigid,
            -> HapticFeedbackConstants.VIRTUAL_KEY
        }
        SensoryFeedback.GestureThreshold ->
            if (android14) HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE else HapticFeedbackConstants.CONTEXT_CLICK
        SensoryFeedback.FrequentTick ->
            if (android14) HapticFeedbackConstants.SEGMENT_FREQUENT_TICK else HapticFeedbackConstants.CLOCK_TICK
        SensoryFeedback.ToggleOn -> if (android14) HapticFeedbackConstants.TOGGLE_ON else HapticFeedbackConstants.CONTEXT_CLICK
        SensoryFeedback.ToggleOff -> if (android14) HapticFeedbackConstants.TOGGLE_OFF else HapticFeedbackConstants.CLOCK_TICK
    }
}

/** A vibration primitive at a scale, 0 to 1. */
data class HapticPrimitive(val id: Int, val scale: Float)

/**
 * The primitive for an impact the iPhone app plays below full intensity, at that intensity; null for anything else,
 * which plays its constant. A light impact is a tick, a soft one a low tick, a medium, solid or rigid one a click, a
 * heavy one a thud.
 */
fun hapticPrimitive(feedback: SensoryFeedback): HapticPrimitive? {
    val (id, intensity) = when (feedback) {
        is SensoryFeedback.Impact -> when (feedback.weight) {
            SensoryFeedback.Impact.Weight.Light -> VibrationEffect.Composition.PRIMITIVE_TICK
            SensoryFeedback.Impact.Weight.Medium -> VibrationEffect.Composition.PRIMITIVE_CLICK
            SensoryFeedback.Impact.Weight.Heavy -> VibrationEffect.Composition.PRIMITIVE_THUD
        } to feedback.intensity
        is SensoryFeedback.FlexibleImpact -> when (feedback.flexibility) {
            SensoryFeedback.FlexibleImpact.Flexibility.Soft -> VibrationEffect.Composition.PRIMITIVE_LOW_TICK
            SensoryFeedback.FlexibleImpact.Flexibility.Solid,
            SensoryFeedback.FlexibleImpact.Flexibility.Rigid,
            -> VibrationEffect.Composition.PRIMITIVE_CLICK
        } to feedback.intensity
        else -> return null
    }
    if (intensity >= 1) return null
    return HapticPrimitive(id, intensity.coerceAtLeast(0.0).toFloat())
}

/**
 * Plays feedback as the phone's own controls do: a constant through the view, or, for an impact below full intensity,
 * its primitive at that scale where the vibrator has it, as touch feedback so the phone's touch-feedback strength
 * applies to both. With touch feedback switched off nothing plays: the view drops the constant, as the window manager
 * does for the phone's own controls, and the primitive isn't played, or from Android 13 the vibrator drops it.
 */
@Stable
class Haptics(private val view: View, private val vibrator: Vibrator? = defaultVibrator(view)) {
    fun perform(feedback: SensoryFeedback) {
        val primitive = hapticPrimitive(feedback)
        if (primitive != null && touchFeedbackOn() && vibrator?.areAllPrimitivesSupported(primitive.id) == true) {
            vibrator.vibrateForTouch(VibrationEffect.startComposition().addPrimitive(primitive.id, primitive.scale).compose())
        } else {
            view.performHapticFeedback(hapticFeedbackConstant(feedback))
        }
    }

    private fun Vibrator.vibrateForTouch(effect: VibrationEffect) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            vibrate(effect, VibrationAttributes.createForUsage(VibrationAttributes.USAGE_TOUCH))
        } else {
            // Android 12 files this usage under touch feedback.
            @Suppress("DEPRECATION")
            vibrate(effect, AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).build())
        }
    }

    /**
     * The view's and the phone's touch-feedback switches. From Android 13 the vibrator silences touch vibrations itself
     * when the phone's is off; Android 12 leaves that to the window manager, which only sees the constants, so it is
     * read here.
     */
    private fun touchFeedbackOn(): Boolean = view.isHapticFeedbackEnabled &&
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU || androidTwelveTouchFeedbackOn())

    /** The phone's switch, deprecated from Android 13, where touch vibrations follow it of their own. */
    @Suppress("DEPRECATION")
    private fun androidTwelveTouchFeedbackOn(): Boolean =
        Settings.System.getInt(view.context.contentResolver, Settings.System.HAPTIC_FEEDBACK_ENABLED, 1) != 0

    /** `Haptics.success()`. */
    fun success() = perform(SensoryFeedback.Success)

    /** `Haptics.selection()`. */
    fun selection() = perform(SensoryFeedback.Selection)
}

private fun defaultVibrator(view: View): Vibrator? =
    view.context.getSystemService(VibratorManager::class.java)?.defaultVibrator

@Composable
fun rememberHaptics(): Haptics {
    val view = LocalView.current
    return remember(view) { Haptics(view) }
}

/**
 * `.sensoryFeedback(_:trigger:condition:)`: plays `feedback` each time `trigger` changes and `condition` holds for its
 * old and new values; not when the view first appears.
 */
@Composable
fun <T> SensoryFeedbackEffect(
    feedback: SensoryFeedback,
    trigger: T,
    condition: (old: T, new: T) -> Boolean = { _, _ -> true },
) {
    val haptics = rememberHaptics()
    val last = remember { LastValue(trigger) }
    SideEffect {
        val old = last.value
        if (old != trigger) {
            last.value = trigger
            if (condition(old, trigger)) haptics.perform(feedback)
        }
    }
}

private class LastValue<T>(var value: T)
