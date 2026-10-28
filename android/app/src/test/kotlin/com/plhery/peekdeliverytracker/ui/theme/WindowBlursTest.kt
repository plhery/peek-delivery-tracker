package com.plhery.peekdeliverytracker.ui.theme

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Window blurs off read as Reduce Transparency, but not when only battery saver turned them off. */
class WindowBlursTest {
    @Test
    fun batterySaverKeepsTheMaterials() {
        val blurs = WindowBlurs()
        assertFalse(blurs.reduceTransparency(enabled = true, saver = false))
        assertFalse(blurs.reduceTransparency(enabled = false, saver = true))
        assertFalse(blurs.reduceTransparency(enabled = true, saver = false))
    }

    @Test
    fun blursOffOtherwiseDrawThemSolidSaverOrNot() {
        val blurs = WindowBlurs()
        assertTrue(blurs.reduceTransparency(enabled = false, saver = false))
        assertTrue(blurs.reduceTransparency(enabled = false, saver = true))
        assertTrue(blurs.reduceTransparency(enabled = false, saver = false))
    }

    @Test
    fun startedUnderTheSaverTheyAreTakenToBeOn() {
        assertFalse(WindowBlurs().reduceTransparency(enabled = false, saver = true))
    }
}
