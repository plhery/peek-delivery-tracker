package com.plhery.peekdeliverytracker.ui

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WindowStatusBarTest {
    /** As an iPhone hides it in landscape, where its height is compact, and an iPad never does. */
    @Test
    fun aPhoneTurnedSidewaysHidesTheStatusBarAndATabletKeepsIt() {
        // A small phone, a large one and a folded foldable on their sides.
        assertTrue(hidesStatusBar(640.dp, 360.dp, multiWindow = false))
        assertTrue(hidesStatusBar(956.dp, 440.dp, multiWindow = false))
        assertTrue(hidesStatusBar(970.dp, 443.dp, multiWindow = false))
        // Upright, a tablet either way, an unfolded foldable, and a phone's window sharing the screen.
        assertFalse(hidesStatusBar(360.dp, 640.dp, multiWindow = false))
        assertFalse(hidesStatusBar(1280.dp, 800.dp, multiWindow = false))
        assertFalse(hidesStatusBar(800.dp, 1280.dp, multiWindow = false))
        assertFalse(hidesStatusBar(883.dp, 852.dp, multiWindow = false))
        assertFalse(hidesStatusBar(640.dp, 360.dp, multiWindow = true))
    }
}
