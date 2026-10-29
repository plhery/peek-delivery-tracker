package com.plhery.peekdeliverytracker.ui.draw

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.MapFont
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

/** The map's names measured for :core's layout as the renderer writes them, tracking left to the layout. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class MapTextWidthsTest {
    private val widths = MapTextWidths.of(ApplicationProvider.getApplicationContext<Context>())

    @Test
    fun aNameIsAsWideAsTheRendererWritesItWithoutTracking() {
        val font = MapFont(11.0, MapFont.Weight.Semibold)
        val width = widths.width("Zürich", font)
        assertTrue("$width", width > 20 && width < 60)

        val measurer = androidx.compose.ui.text.TextMeasurer(
            androidx.compose.ui.text.font.createFontFamilyResolver(ApplicationProvider.getApplicationContext()),
            androidx.compose.ui.unit.Density(1f),
            androidx.compose.ui.unit.LayoutDirection.Ltr,
        )
        val written = measureMapText(measurer, "Zürich", font, Color.Black, tracking = 0.0)
        assertEquals((written.getLineRight(0) - written.getLineLeft(0)).toDouble(), width, 0.001)
        // Tracking widens what is written, never what is measured.
        val tracked = measureMapText(measurer, "Zürich", font, Color.Black, tracking = 2.0)
        assertTrue(tracked.getLineRight(0) - tracked.getLineLeft(0) > width)
        // The same width each time, in dp whatever the screen's density.
        assertEquals(width, widths.width("Zürich", font), 0.0)
    }

    @Test
    fun widthsGrowWithTheTextSizeAndWeight() {
        val regular = MapFont(10.0, MapFont.Weight.Regular)
        assertEquals(0.0, widths.width("", regular), 0.0)
        assertTrue(widths.width("Genève", regular) < widths.width("Genève – Lausanne", regular))
        assertTrue(widths.width("Genève", regular) < widths.width("Genève", MapFont(14.0, MapFont.Weight.Regular)))
        assertTrue(widths.width("GENÈVE", regular) < widths.width("GENÈVE", MapFont(10.0, MapFont.Weight.Heavy)))
    }
}
