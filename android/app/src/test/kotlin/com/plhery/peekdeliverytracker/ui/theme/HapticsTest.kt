package com.plhery.peekdeliverytracker.ui.theme

import android.content.Context
import android.os.Build
import android.os.VibrationAttributes
import android.os.VibrationEffect.Composition
import android.os.VibratorManager
import android.provider.Settings
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback.FlexibleImpact
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback.FlexibleImpact.Flexibility
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback.Impact
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback.Impact.Weight
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowVibrator.PrimitiveEffect

/**
 * X-AD17's table, on Android 12, 13 and a release from 14 on: each feedback plays the constant whose AOSP vibration is
 * as strong as the iPhone's, and an impact below full intensity its primitive at that scale where the phone has it.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [31, 33, 37])
class HapticsTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val vibrator = context.getSystemService(VibratorManager::class.java).defaultVibrator
    private val view = View(context)
    private val android14 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    @Test
    fun eachFeedbackPlaysTheConstantOfItsStrength() {
        val expected = mapOf(
            SensoryFeedback.Selection to if (android14) HapticFeedbackConstants.SEGMENT_TICK else HapticFeedbackConstants.CONTEXT_CLICK,
            SensoryFeedback.Success to HapticFeedbackConstants.CONFIRM,
            SensoryFeedback.Warning to HapticFeedbackConstants.REJECT,
            SensoryFeedback.Error to HapticFeedbackConstants.REJECT,
            Impact(Weight.Light) to HapticFeedbackConstants.CONTEXT_CLICK,
            Impact(Weight.Medium) to HapticFeedbackConstants.VIRTUAL_KEY,
            Impact(Weight.Heavy) to HapticFeedbackConstants.LONG_PRESS,
            FlexibleImpact(Flexibility.Soft) to HapticFeedbackConstants.CONTEXT_CLICK,
            FlexibleImpact(Flexibility.Solid) to HapticFeedbackConstants.VIRTUAL_KEY,
            FlexibleImpact(Flexibility.Rigid) to HapticFeedbackConstants.VIRTUAL_KEY,
            SensoryFeedback.GestureThreshold to
                if (android14) HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE else HapticFeedbackConstants.CONTEXT_CLICK,
            SensoryFeedback.FrequentTick to
                if (android14) HapticFeedbackConstants.SEGMENT_FREQUENT_TICK else HapticFeedbackConstants.CLOCK_TICK,
            SensoryFeedback.ToggleOn to if (android14) HapticFeedbackConstants.TOGGLE_ON else HapticFeedbackConstants.CONTEXT_CLICK,
            SensoryFeedback.ToggleOff to if (android14) HapticFeedbackConstants.TOGGLE_OFF else HapticFeedbackConstants.CLOCK_TICK,
        )
        assertEquals(expected, expected.keys.associateWith { hapticFeedbackConstant(it) })

        val haptics = Haptics(view, vibrator)
        for ((feedback, constant) in expected) {
            haptics.perform(feedback)
            assertEquals("$feedback", constant, shadowOf(view).lastHapticFeedbackPerformed())
        }
    }

    @Test
    fun anImpactBelowFullIntensityPlaysItsPrimitiveAtThatScaleAsTouchFeedback() {
        shadowOf(vibrator).setSupportedPrimitives(
            listOf(Composition.PRIMITIVE_TICK, Composition.PRIMITIVE_LOW_TICK, Composition.PRIMITIVE_CLICK, Composition.PRIMITIVE_THUD),
        )
        val haptics = Haptics(view, vibrator)
        // A-H04, A-H08 and C-H01.
        val played = mapOf(
            Impact(Weight.Medium, 0.9) to PrimitiveEffect(Composition.PRIMITIVE_CLICK, 0.9f, 0),
            Impact(Weight.Light, 0.65) to PrimitiveEffect(Composition.PRIMITIVE_TICK, 0.65f, 0),
            FlexibleImpact(Flexibility.Soft, 0.45) to PrimitiveEffect(Composition.PRIMITIVE_LOW_TICK, 0.45f, 0),
        )
        for ((feedback, primitive) in played) {
            haptics.perform(feedback)
            assertEquals("$feedback", listOf(primitive), shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects)
            // Android 12 takes the audio usage for touch feedback too.
            val attributes = shadowOf(vibrator).vibrationAttributesFromLastVibration as VibrationAttributes
            assertEquals(VibrationAttributes.USAGE_TOUCH, attributes.usage)
        }
    }

    @Test
    fun withoutThePrimitiveOrAtFullIntensityTheConstantPlays() {
        shadowOf(vibrator).setSupportedPrimitives(emptyList())
        val haptics = Haptics(view, vibrator)
        haptics.perform(Impact(Weight.Medium, 0.8))
        assertEquals(HapticFeedbackConstants.VIRTUAL_KEY, shadowOf(view).lastHapticFeedbackPerformed())

        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_TICK))
        // Pull to refresh's light impact (A-H07) has no intensity of its own.
        haptics.perform(Impact(Weight.Light))
        assertEquals(HapticFeedbackConstants.CONTEXT_CLICK, shadowOf(view).lastHapticFeedbackPerformed())
        assertTrue(shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects.isEmpty())
    }

    @Test
    fun withTouchFeedbackOffNothingVibratesOfItsOwn() {
        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_TICK))
        view.isHapticFeedbackEnabled = false
        Haptics(view, vibrator).perform(Impact(Weight.Light, 0.5))
        assertTrue(shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects.isEmpty())

        // The phone's switch, which Android 12's vibrator doesn't apply to touch vibrations itself; from Android 13
        // the vibrator drops them (the shadow doesn't).
        val tick = listOf(PrimitiveEffect(Composition.PRIMITIVE_TICK, 0.5f, 0))
        view.isHapticFeedbackEnabled = true
        touchFeedback(on = false)
        Haptics(view, vibrator).perform(Impact(Weight.Light, 0.5))
        val android13 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        assertEquals(if (android13) tick else emptyList(), shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects)
        touchFeedback(on = true)
        Haptics(view, vibrator).perform(Impact(Weight.Light, 0.5))
        assertEquals(tick, shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects)
    }

    @Suppress("DEPRECATION")
    private fun touchFeedback(on: Boolean) =
        Settings.System.putInt(context.contentResolver, Settings.System.HAPTIC_FEEDBACK_ENABLED, if (on) 1 else 0)

    @Test
    fun onlyImpactsHavePrimitives() {
        assertEquals(HapticPrimitive(Composition.PRIMITIVE_THUD, 0.5f), hapticPrimitive(Impact(Weight.Heavy, 0.5)))
        assertEquals(HapticPrimitive(Composition.PRIMITIVE_CLICK, 0.3f), hapticPrimitive(FlexibleImpact(Flexibility.Rigid, 0.3)))
        assertEquals(null, hapticPrimitive(Impact(Weight.Medium, 1.0)))
        assertEquals(null, hapticPrimitive(SensoryFeedback.Selection))
        assertEquals(null, hapticPrimitive(SensoryFeedback.Success))
    }
}
