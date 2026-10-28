package com.plhery.peekdeliverytracker.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

/**
 * The feedback the iPhone app asks for (`.sensoryFeedback`, `Haptics.success()`), plus the Android gestures that have
 * a constant of their own. An impact's intensity is kept for the caller's record, but Android's constants have none.
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
 * The constant `View.performHapticFeedback` plays for a feedback on an Android release (X-AD17): the newer
 * constants from Android 14, the nearest older one on Android 12 and 13. Lint can't tell `sdk` is the release.
 */
@SuppressLint("InlinedApi")
fun hapticFeedbackConstant(feedback: SensoryFeedback, sdk: Int = Build.VERSION.SDK_INT): Int {
    val android14 = sdk >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
    return when (feedback) {
        SensoryFeedback.Selection -> if (android14) HapticFeedbackConstants.SEGMENT_TICK else HapticFeedbackConstants.CLOCK_TICK
        SensoryFeedback.Success -> HapticFeedbackConstants.CONFIRM
        SensoryFeedback.Warning, SensoryFeedback.Error -> HapticFeedbackConstants.REJECT
        is SensoryFeedback.Impact -> when (feedback.weight) {
            SensoryFeedback.Impact.Weight.Light -> HapticFeedbackConstants.CLOCK_TICK
            SensoryFeedback.Impact.Weight.Medium -> HapticFeedbackConstants.CONTEXT_CLICK
            SensoryFeedback.Impact.Weight.Heavy -> HapticFeedbackConstants.LONG_PRESS
        }
        is SensoryFeedback.FlexibleImpact -> when (feedback.flexibility) {
            SensoryFeedback.FlexibleImpact.Flexibility.Soft -> HapticFeedbackConstants.CLOCK_TICK
            SensoryFeedback.FlexibleImpact.Flexibility.Solid -> HapticFeedbackConstants.CONTEXT_CLICK
            SensoryFeedback.FlexibleImpact.Flexibility.Rigid -> HapticFeedbackConstants.LONG_PRESS
        }
        SensoryFeedback.GestureThreshold ->
            if (android14) HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE else HapticFeedbackConstants.CONTEXT_CLICK
        SensoryFeedback.FrequentTick ->
            if (android14) HapticFeedbackConstants.SEGMENT_FREQUENT_TICK else HapticFeedbackConstants.CLOCK_TICK
        SensoryFeedback.ToggleOn -> if (android14) HapticFeedbackConstants.TOGGLE_ON else HapticFeedbackConstants.CLOCK_TICK
        SensoryFeedback.ToggleOff -> if (android14) HapticFeedbackConstants.TOGGLE_OFF else HapticFeedbackConstants.CLOCK_TICK
    }
}

/** Plays feedback through a view, so the phone's touch-feedback setting applies as it does to its own controls. */
@Stable
class Haptics(private val view: View) {
    fun perform(feedback: SensoryFeedback) {
        view.performHapticFeedback(hapticFeedbackConstant(feedback))
    }

    /** `Haptics.success()`. */
    fun success() = perform(SensoryFeedback.Success)

    /** `Haptics.selection()`. */
    fun selection() = perform(SensoryFeedback.Selection)
}

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
