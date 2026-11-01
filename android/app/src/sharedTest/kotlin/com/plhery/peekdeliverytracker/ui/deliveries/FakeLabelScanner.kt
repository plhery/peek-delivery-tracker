package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A label scanner without a camera: available while [usable], its camera a grey placeholder across its room, and the
 * barcodes a frame holds given by the test ([read]) to the camera drawn, if any.
 */
class FakeLabelScanner(usable: Boolean = true) : LabelScanner {
    var usable by mutableStateOf(usable)

    /** How many cameras are drawn, which run while they are. */
    var running = 0
        private set

    private var reader: ((List<String?>) -> Unit)? = null

    @Composable
    override fun available(): Boolean = usable

    @Composable
    override fun Camera(onRead: (List<String?>) -> Unit, modifier: Modifier) {
        val read by rememberUpdatedState(onRead)
        DisposableEffect(Unit) {
            val each: (List<String?>) -> Unit = { read(it) }
            reader = each
            running++
            onDispose {
                running--
                if (reader === each) reader = null
            }
        }
        Box(modifier.background(PLACEHOLDER))
    }

    /** A frame holding barcodes with these payloads, the one followed first, told to the camera drawn. */
    fun read(vararg payloads: String?) {
        val reader = checkNotNull(reader) { "No camera drawn" }
        reader(payloads.toList())
    }

    private companion object {
        val PLACEHOLDER = Color(0xFF8E8E93)
    }
}
