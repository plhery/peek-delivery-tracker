package com.plhery.peekdeliverytracker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.LocalSaveableStateRegistry
import androidx.compose.runtime.saveable.SaveableStateRegistry
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.junit4.ComposeContentTestRule

/**
 * The content made again from what it saved, as when the window is made again (a rotation, a change of theme), with
 * the first frame after it in the test's hands. StateRestorationTester takes the content away only while the clock
 * runs by itself, and then runs it past that frame.
 */
class Recreation(private val compose: ComposeContentTestRule) {
    private var canBeSaved: (Any) -> Boolean = { true }
    private var registry = SaveableStateRegistry(null) { canBeSaved(it) }
    private var shown by mutableStateOf(true)

    fun setContent(content: @Composable () -> Unit) {
        compose.setContent {
            val window = LocalSaveableStateRegistry.current
            if (window != null) canBeSaved = window::canBeSaved
            if (shown) CompositionLocalProvider(LocalSaveableStateRegistry provides registry, content = content)
        }
    }

    /** Saves the content and takes it away, then composes it again from what it saved, as far as its first frame. */
    fun recreate() {
        compose.mainClock.autoAdvance = false
        compose.runOnIdle {
            registry = SaveableStateRegistry(registry.performSave()) { canBeSaved(it) }
            shown = false
        }
        frame()
        compose.runOnIdle { shown = true }
        frame()
    }

    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }
}
