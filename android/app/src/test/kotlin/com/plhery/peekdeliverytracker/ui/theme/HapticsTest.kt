package com.plhery.peekdeliverytracker.ui.theme

import android.view.HapticFeedbackConstants
import org.junit.Assert.assertEquals
import org.junit.Test

/** X-AD17's table: the iPhone's feedback to Android's constants, Android 14's where it has them. */
class HapticsTest {
    private fun constants(sdk: Int) = mapOf(
        SensoryFeedback.Selection to hapticFeedbackConstant(SensoryFeedback.Selection, sdk),
        SensoryFeedback.Success to hapticFeedbackConstant(SensoryFeedback.Success, sdk),
        SensoryFeedback.Warning to hapticFeedbackConstant(SensoryFeedback.Warning, sdk),
        SensoryFeedback.Error to hapticFeedbackConstant(SensoryFeedback.Error, sdk),
        SensoryFeedback.GestureThreshold to hapticFeedbackConstant(SensoryFeedback.GestureThreshold, sdk),
        SensoryFeedback.FrequentTick to hapticFeedbackConstant(SensoryFeedback.FrequentTick, sdk),
        SensoryFeedback.ToggleOn to hapticFeedbackConstant(SensoryFeedback.ToggleOn, sdk),
        SensoryFeedback.ToggleOff to hapticFeedbackConstant(SensoryFeedback.ToggleOff, sdk),
    )

    @Test
    fun android14PlaysItsOwnConstants() {
        assertEquals(
            mapOf(
                SensoryFeedback.Selection to HapticFeedbackConstants.SEGMENT_TICK,
                SensoryFeedback.Success to HapticFeedbackConstants.CONFIRM,
                SensoryFeedback.Warning to HapticFeedbackConstants.REJECT,
                SensoryFeedback.Error to HapticFeedbackConstants.REJECT,
                SensoryFeedback.GestureThreshold to HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE,
                SensoryFeedback.FrequentTick to HapticFeedbackConstants.SEGMENT_FREQUENT_TICK,
                SensoryFeedback.ToggleOn to HapticFeedbackConstants.TOGGLE_ON,
                SensoryFeedback.ToggleOff to HapticFeedbackConstants.TOGGLE_OFF,
            ),
            constants(34),
        )
    }

    @Test
    fun android12And13FallBackToTheNearestOlderConstant() {
        val expected = mapOf(
            SensoryFeedback.Selection to HapticFeedbackConstants.CLOCK_TICK,
            SensoryFeedback.Success to HapticFeedbackConstants.CONFIRM,
            SensoryFeedback.Warning to HapticFeedbackConstants.REJECT,
            SensoryFeedback.Error to HapticFeedbackConstants.REJECT,
            SensoryFeedback.GestureThreshold to HapticFeedbackConstants.CONTEXT_CLICK,
            SensoryFeedback.FrequentTick to HapticFeedbackConstants.CLOCK_TICK,
            SensoryFeedback.ToggleOn to HapticFeedbackConstants.CLOCK_TICK,
            SensoryFeedback.ToggleOff to HapticFeedbackConstants.CLOCK_TICK,
        )
        assertEquals(expected, constants(31))
        assertEquals(expected, constants(33))
    }

    @Test
    fun impactsGoByWeightOrFlexibilityWhateverTheirIntensity() {
        for (sdk in listOf(31, 37)) {
            assertEquals(HapticFeedbackConstants.CLOCK_TICK, hapticFeedbackConstant(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65), sdk))
            assertEquals(HapticFeedbackConstants.CONTEXT_CLICK, hapticFeedbackConstant(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Medium, 0.9), sdk))
            assertEquals(HapticFeedbackConstants.LONG_PRESS, hapticFeedbackConstant(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Heavy), sdk))
            val soft = SensoryFeedback.FlexibleImpact(SensoryFeedback.FlexibleImpact.Flexibility.Soft, 0.45)
            assertEquals(HapticFeedbackConstants.CLOCK_TICK, hapticFeedbackConstant(soft, sdk))
            val solid = SensoryFeedback.FlexibleImpact(SensoryFeedback.FlexibleImpact.Flexibility.Solid)
            assertEquals(HapticFeedbackConstants.CONTEXT_CLICK, hapticFeedbackConstant(solid, sdk))
            val rigid = SensoryFeedback.FlexibleImpact(SensoryFeedback.FlexibleImpact.Flexibility.Rigid)
            assertEquals(HapticFeedbackConstants.LONG_PRESS, hapticFeedbackConstant(rigid, sdk))
        }
    }
}
