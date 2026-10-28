package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import kotlin.math.roundToInt

/**
 * PeekMarkTests.testDrawingPaintsTheLayersBackToFrontWithYPointingDown (A-T37): the mark drawn 512 pixels wide gives
 * the iPhone's bytes, premultiplied RGBA with y pointing down, within 1 a channel.
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class PeekMarkPixelTest {
    @Test
    fun drawingPaintsTheLayersBackToFrontWithYPointingDown() {
        val side = 512
        val image = ImageBitmap(side, side)
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, Canvas(image), Size(side.toFloat(), side.toFloat())) {
            drawPeekMark(side.toDouble())
        }
        val bitmap = image.asAndroidBitmap()

        val clear = intArrayOf(0, 0, 0, 0)
        val yellow = intArrayOf(0xF3, 0xCF, 0x48, 255)
        val paper = intArrayOf(0xFF, 0xFA, 0xF0, 255)
        val ink = intArrayOf(0x17, 0x17, 0x14, 255)
        val white = intArrayOf(255, 255, 255, 255)
        // Outside the rounded corner, the tile, then the white, the pupil and the catchlight of each eye.
        val expected = mutableListOf(Triple(2, 2, clear), Triple(256, 60, yellow))
        for (eye in listOf(0, 132)) {
            expected += listOf(Triple(150 + eye, 262, paper), Triple(225 + eye, 285, ink), Triple(207 + eye, 255, white))
        }
        // The left pupil stays whole where the right eye's white reaches over it.
        expected += Triple(253, 269, ink)
        assertEquals(9, expected.size)
        for ((x, y, color) in expected) {
            val bytes = premultipliedRgba(bitmap.getPixel(x, y))
            for (channel in 0 until 4) {
                assertEquals("($x, $y) channel $channel", color[channel].toDouble(), bytes[channel].toDouble(), 1.0)
            }
        }
    }

    /** A pixel as the iPhone's bitmap holds it, whatever order the bitmap keeps its bytes in on this machine. */
    private fun premultipliedRgba(argb: Int): IntArray {
        val alpha = argb ushr 24
        fun channel(shift: Int) = ((argb shr shift and 0xFF) * alpha / 255.0).roundToInt()
        return intArrayOf(channel(16), channel(8), channel(0), alpha)
    }
}
