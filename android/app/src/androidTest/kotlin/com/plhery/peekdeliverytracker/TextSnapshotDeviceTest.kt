package com.plhery.peekdeliverytracker

import android.content.Context
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlin.concurrent.thread
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Text measured while the emoji font loads, outside a snapshot taken before, then in that snapshot: a map's names or
 * the widget's measured off the main thread while a composition is open, then the tab bar's labels, measured as it is
 * composed. Compose keeps whether the emoji font has loaded in a state it makes the first time any text is measured;
 * made outside the open snapshot, the composition couldn't read it and the app stopped as it launched.
 * [PeekApplication] makes it before anything else measures text. Meant for a process of its own, where no text has been
 * measured yet: run it alone.
 */
@RunWith(AndroidJUnit4::class)
class TextSnapshotDeviceTest {
    @Test
    fun textMeasuredOutsideAnOpenSnapshotAndThenInItIsMeasured() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val measurer = TextMeasurer(createFontFamilyResolver(context), Density(1f), LayoutDirection.Ltr)
        val composing = Snapshot.takeMutableSnapshot()
        try {
            thread { measurer.measure("Härkingen") }.join()
            composing.enter { measurer.measure("Deliveries") }
        } finally {
            composing.dispose()
        }
    }
}
