package com.plhery.peekdeliverytracker.ui

import android.app.Activity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.compose.LocalActivity
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
 * runs by itself, and then runs it past that frame. While the content goes, the activity is changing configurations,
 * as the system has it then.
 */
class Recreation(private val compose: ComposeContentTestRule) {
    private var canBeSaved: (Any) -> Boolean = { true }
    private var registry = SaveableStateRegistry(null) { canBeSaved(it) }
    private var shown by mutableStateOf(true)
    private var activity: Activity? = null

    fun setContent(content: @Composable () -> Unit) {
        compose.setContent {
            activity = LocalActivity.current
            val window = LocalSaveableStateRegistry.current
            if (window != null) canBeSaved = window::canBeSaved
            if (shown) CompositionLocalProvider(LocalSaveableStateRegistry provides registry, content = content)
        }
    }

    /**
     * Saves the content and takes it away, then composes it again from what it saved, as far as its first frame. What
     * changed with the window, such as its size as it turns, changes in `meanwhile`, while the content is away. Where
     * [parceled], what it saved is written to a Parcel and read back, as the system hands it back once it has kept it
     * outside the process, so nothing comes back as the very object that was saved.
     */
    fun recreate(parceled: Boolean = false, meanwhile: () -> Unit = {}) {
        compose.mainClock.autoAdvance = false
        compose.runOnIdle {
            changingConfigurations(true)
            val saved = registry.performSave()
            registry = SaveableStateRegistry(if (parceled) saved.throughParcel() else saved) { canBeSaved(it) }
            shown = false
        }
        frame()
        compose.runOnIdle {
            meanwhile()
            changingConfigurations(false)
            shown = true
        }
        frame()
    }

    /** What was saved, through a Parcel in a Bundle as the activity's saved state holds it. */
    @Suppress("DEPRECATION", "UNCHECKED_CAST")
    private fun Map<String, List<Any?>>.throughParcel(): Map<String, List<Any?>> {
        val bundle = Bundle()
        forEach { (key, values) -> bundle.putParcelableArrayList(key, ArrayList(values) as ArrayList<Parcelable?>) }
        val parcel = Parcel.obtain()
        try {
            parcel.writeBundle(bundle)
            parcel.setDataPosition(0)
            val read = parcel.readBundle(Recreation::class.java.classLoader)!!
            // Untyped, as the registry's own reading is: the values are of every kind a Bundle holds.
            return read.keySet().associateWith { read.get(it) as List<Any?> }
        } finally {
            parcel.recycle()
        }
    }

    private fun changingConfigurations(changing: Boolean) {
        val shown = activity ?: return
        Activity::class.java.getDeclaredField("mChangingConfigurations").apply { isAccessible = true }.setBoolean(shown, changing)
    }

    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }
}
