package com.plhery.peekdeliverytracker.ui.theme

import android.provider.Settings
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

/** Reduce Motion from the phone's animation setting, and feedback on a trigger's change. */
@RunWith(AndroidJUnit4::class)
class EnvironmentTest {
    @get:Rule
    val compose = createComposeRule()

    private val resolver = ApplicationProvider.getApplicationContext<android.content.Context>().contentResolver
    private val durationScale = Settings.Global.getUriFor(Settings.Global.ANIMATOR_DURATION_SCALE)

    @After
    fun animationsBackOn() {
        Settings.Global.putFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
    }

    @Test
    fun removingAnimationsReducesMotionWhileTheScreenIsShown() {
        Settings.Global.putFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
        var reduced: Boolean? = null
        compose.setContent { reduced = reduceMotionSetting() }
        compose.waitForIdle()
        assertEquals(false, reduced)

        Settings.Global.putFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        resolver.notifyChange(durationScale, null)
        compose.waitForIdle()
        assertEquals(true, reduced)

        // Any other speed is motion as usual.
        Settings.Global.putFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0.5f)
        resolver.notifyChange(durationScale, null)
        compose.waitForIdle()
        assertEquals(false, reduced)
    }

    @Test
    fun aScreenOpenedWithAnimationsOffStartsReduced() {
        Settings.Global.putFloat(resolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0f)
        var reduced: Boolean? = null
        compose.setContent { reduced = reduceMotionSetting() }
        compose.waitForIdle()
        assertEquals(true, reduced)
    }

    @Test
    fun feedbackPlaysWhenTheTriggerChangesAndTheConditionHolds() {
        var trigger by mutableIntStateOf(0)
        lateinit var view: View
        compose.setContent {
            view = LocalView.current
            SensoryFeedbackEffect(SensoryFeedback.Success, trigger, condition = { _, new -> new != 2 })
        }
        compose.waitForIdle()
        // Not when the screen first appears.
        assertFalse(shadowOf(view).lastHapticFeedbackPerformed() == HapticFeedbackConstants.CONFIRM)

        trigger = 1
        compose.waitForIdle()
        assertEquals(HapticFeedbackConstants.CONFIRM, shadowOf(view).lastHapticFeedbackPerformed())
    }

    @Test
    fun feedbackStaysQuietWhenTheConditionFails() {
        var trigger by mutableIntStateOf(0)
        lateinit var view: View
        compose.setContent {
            view = LocalView.current
            SensoryFeedbackEffect(SensoryFeedback.Selection, trigger, condition = { _, new -> new != 2 })
        }
        compose.waitForIdle()
        trigger = 2
        compose.waitForIdle()
        assertFalse(shadowOf(view).lastHapticFeedbackPerformed() == HapticFeedbackConstants.SEGMENT_TICK)
        trigger = 3
        compose.waitForIdle()
        assertEquals(HapticFeedbackConstants.SEGMENT_TICK, shadowOf(view).lastHapticFeedbackPerformed())
    }
}
