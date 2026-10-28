package com.plhery.peekdeliverytracker.ui.theme

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Window blurs off read as Reduce Transparency, but not when only battery saver turned them off. */
class WindowBlursTest {
    @Test
    fun batterySaverKeepsTheMaterials() {
        val blurs = WindowBlurs()
        assertFalse(blurs.settled(enabled = true, saver = false))
        assertFalse(blurs.settled(enabled = false, saver = true))
        assertFalse(blurs.settled(enabled = true, saver = false))
    }

    @Test
    fun blursOffOtherwiseDrawThemSolidSaverOrNot() {
        val blurs = WindowBlurs()
        assertTrue(blurs.settled(enabled = false, saver = false))
        assertTrue(blurs.settled(enabled = false, saver = true))
        assertTrue(blurs.settled(enabled = false, saver = false))
    }

    @Test
    fun startedUnderTheSaverTheyAreTakenToBeOn() {
        assertFalse(WindowBlurs().settled(enabled = false, saver = true))
    }

    @Test
    fun aReadIsNotTakenIn() {
        val blurs = WindowBlurs()
        assertTrue(blurs.reduceTransparency(enabled = false, saver = false))
        assertFalse(blurs.reduceTransparency(enabled = false, saver = true))
    }

    /**
     * The system as reduceTransparencySetting hears it: the blur listener's news, the saver's broadcast with blurs read
     * as the window manager has them then, and the read again a while after. What it draws, from blurs on and no saver.
     */
    private class Phone(var blurs: Boolean = true, var saver: Boolean = false) {
        private val windowBlurs = WindowBlurs()
        var drawn = windowBlurs.reduceTransparency(blurs, saver)
            private set
        val frames = mutableListOf(drawn)
        private var readAgain = false

        init {
            listener()
        }

        fun listener() = draw(windowBlurs.settled(blurs, saver))

        fun broadcast() {
            readAgain = false
            windowBlurs.saverChanged(blurs, saver)?.let(::draw) ?: run { readAgain = true }
        }

        fun later() {
            if (readAgain) draw(windowBlurs.settled(blurs, saver))
            readAgain = false
        }

        private fun draw(value: Boolean) {
            drawn = value
            frames += value
        }
    }

    @Test
    fun theSaverComingOrGoingKeepsTheMaterialsWhicheverIsHeardFirst() {
        for (listenerFirst in listOf(true, false)) {
            val phone = Phone()
            // The saver comes and turns blurs off.
            phone.saver = true
            if (listenerFirst) {
                phone.blurs = false
                phone.listener()
                phone.broadcast()
            } else {
                // The broadcast reads blurs before the window manager turns them off.
                phone.broadcast()
                phone.blurs = false
                phone.listener()
            }
            phone.later()
            // It goes and lets them back.
            phone.saver = false
            if (listenerFirst) {
                phone.blurs = true
                phone.listener()
                phone.broadcast()
            } else {
                // The broadcast reads blurs still off, with the saver gone.
                phone.broadcast()
                phone.blurs = true
                phone.listener()
            }
            phone.later()
            assertEquals("listener first: $listenerFirst", listOf(false), phone.frames.distinct())
        }
    }

    @Test
    fun blursOffForAnotherReasonAreTakenInOnceTheSaverHasGone() {
        // "Disable window blurs" while the saver has them off already: no news, until the saver goes and they stay off.
        val phone = Phone()
        phone.saver = true
        phone.blurs = false
        phone.listener()
        phone.broadcast()
        assertFalse(phone.drawn)
        phone.saver = false
        phone.broadcast()
        assertFalse(phone.drawn)
        phone.later()
        assertTrue(phone.drawn)
        // The saver again: they stay solid, as they were without it.
        phone.saver = true
        phone.broadcast()
        assertTrue(phone.drawn)
    }
}
