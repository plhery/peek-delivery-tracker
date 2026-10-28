package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.PeekMarkArtwork
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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

    /** A row of pixels from `from` to `to` (exclusive) crossing one edge, from colour `outside` to colour `inside`. */
    private class Crossing(val name: String, val y: Int, val from: Int, val to: Int, val outside: IntArray, val inside: IntArray, val edge: Double)

    /**
     * Edges are anti-aliased and lie where the iPhone's do. Where a row crosses an edge, each pixel blends the two
     * sides by how much of each it covers, so the edge's place along the row is the row's length less what it holds
     * of the inside, to a fraction of a pixel. The places are CoreGraphics' for PeekMarkArtwork.swift drawn 512 wide
     * on a Mac (swiftc), as PeekMarkTests draws it, without PeekMark's continuous clip; each is within 0.07 pixels of
     * the true curve: the tile's quarter circle where it is steep, at 45° and nearly flat, the edges of a white and of
     * each pupil, and a catchlight, each over the layer below it.
     *
     * Within [EDGE_TOLERANCE]: Skia covers a curve as a chain of straight pieces, which cut inside a convex curve by a few
     * hundredths of a pixel, while CoreGraphics covers the curve itself; where a row crosses the curve nearly flat, as
     * at the top of the tile's corner, that gap stretches along the row to about a fifth of a pixel. An edge drawn half
     * a pixel off, or without anti-aliasing, misses by more.
     */
    @Test
    fun edgesBlendAndLieWhereTheIPhonesDo() {
        val bitmap = artwork(512)
        val clear = intArrayOf(0, 0, 0, 0)
        val crossings = listOf(
            Crossing("tile corner, steep", 60, 0, 30, clear, YELLOW, 14.1216),
            Crossing("tile corner, 45°", 33, 20, 50, clear, YELLOW, 34.4745),
            Crossing("tile corner, nearly flat", 4, 60, 120, clear, YELLOW, 84.0275),
            Crossing("white", 230, 115, 140, YELLOW, PAPER, 126.1369),
            Crossing("left pupil", 250, 180, 196, PAPER, INK, 189.7284),
            Crossing("right pupil", 250, 312, 328, PAPER, INK, 321.7284),
            Crossing("catchlight", 248, 192, 206, INK, WHITE, 199.3191),
        )
        for (crossing in crossings) {
            // The channel the two sides differ most in.
            val channel = (0 until 4).maxBy { kotlin.math.abs(crossing.inside[it] - crossing.outside[it]) }
            val values = (crossing.from until crossing.to).map { premultipliedRgba(bitmap.getPixel(it, crossing.y))[channel] }
            val span = (crossing.inside[channel] - crossing.outside[channel]).toDouble()
            val inside = values.sumOf { (it - crossing.outside[channel]) / span }
            val edge = crossing.to - inside
            assertEquals(crossing.name, crossing.edge, edge, EDGE_TOLERANCE)
            val blended = values.filter { it != crossing.outside[channel] && it != crossing.inside[channel] }
            assertTrue("${crossing.name} is anti-aliased: $values", blended.isNotEmpty())
        }
    }

    /** `PeekMarkArtwork.draw(in:size:)` into a bitmap `side` pixels wide. */
    private fun artwork(side: Int): android.graphics.Bitmap {
        val image = ImageBitmap(side, side)
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, Canvas(image), Size(side.toFloat(), side.toFloat())) {
            val scale = (side / PeekMarkArtwork.frame).toFloat()
            scale(scale, scale, pivot = Offset.Zero) { drawMapDrawings(PeekMarkArtwork.draw(), dark = false) }
        }
        return image.asAndroidBitmap()
    }

    private companion object {
        val YELLOW = intArrayOf(0xF3, 0xCF, 0x48, 255)
        val PAPER = intArrayOf(0xFF, 0xFA, 0xF0, 255)
        val INK = intArrayOf(0x17, 0x17, 0x14, 255)
        val WHITE = intArrayOf(255, 255, 255, 255)

        /** A quarter of a pixel. */
        const val EDGE_TOLERANCE = 0.25
    }

    /** A pixel as the iPhone's bitmap holds it, whatever order the bitmap keeps its bytes in on this machine. */
    private fun premultipliedRgba(argb: Int): IntArray {
        val alpha = argb ushr 24
        fun channel(shift: Int) = ((argb shr shift and 0xFF) * alpha / 255.0).roundToInt()
        return intArrayOf(channel(16), channel(8), channel(0), alpha)
    }
}
