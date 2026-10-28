package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * PeekMarkTests.swift: the tile fills the frame, the two eyes, where they lie and their colours. The iPhone's last test
 * renders the drawing into a bitmap and reads its pixels; here it asks which fill covers each of those pixels' centres,
 * and the pixels themselves are left to :app's rendering.
 */
class PeekMarkTests {
    private val frame = Rect(0.0, 0.0, PeekMarkArtwork.frame, PeekMarkArtwork.frame)
    private val layers = PeekMarkArtwork.layers

    @Test
    fun theDrawingStartsWithTheTileFillingTheFrame() {
        val tile = layers.first().path.boundingBoxOfPath
        assertEquals(frame.minX, tile.minX, 0.01)
        assertEquals(frame.minY, tile.minY, 0.01)
        assertEquals(frame.maxX, tile.maxX, 0.01)
        assertEquals(frame.maxY, tile.maxY, 0.01)
    }

    @Test
    fun theTileCarriesTwoEyesAndNothingElse() {
        assertEquals(7, layers.size)
        // The whites of both eyes, then the pupil and the catchlight of the left eye and of the right one.
        val shapes = listOf(
            listOf(190.0, 262.0, 70.0, 77.0), listOf(322.0, 262.0, 70.0, 77.0),
            listOf(219.4, 269.0, 35.0, 35.0), listOf(207.5, 255.0, 10.5, 10.5),
            listOf(351.4, 269.0, 35.0, 35.0), listOf(339.5, 255.0, 10.5, 10.5),
        )
        for ((layer, shape) in layers.drop(1).zip(shapes)) {
            val box = layer.path.boundingBoxOfPath
            assertEquals(shape[0], box.midX, 0.01)
            assertEquals(shape[1], box.midY, 0.01)
            assertEquals(shape[2], box.width / 2, 0.01)
            assertEquals(shape[3], box.height / 2, 0.01)
        }
    }

    @Test
    fun theRightEyeIsTheLeftOne132UnitsFurtherRight() {
        val boxes = layers.map { it.path.boundingBoxOfPath }
        for ((left, right) in listOf(boxes[1] to boxes[2], boxes[3] to boxes[5], boxes[4] to boxes[6])) {
            assertEquals(132.0, right.midX - left.midX, 0.01)
            assertEquals(left.midY, right.midY, 0.01)
            assertEquals(left.width, right.width, 0.01)
            assertEquals(left.height, right.height, 0.01)
        }
    }

    @Test
    fun eachPupilAndCatchlightLieInsideTheirWhite() {
        // Each layer is one ellipse, so the even-odd rule covers what CoreGraphics' nonzero one does.
        for ((white, inner) in listOf(layers[1] to layers.subList(3, 5), layers[2] to layers.subList(5, 7))) {
            for (point in inner.flatMap(::edge)) {
                assertTrue("$point", white.path.containsEvenOdd(point))
            }
        }
    }

    @Test
    fun everyLayerStaysInsideTheFrame() {
        for ((index, layer) in layers.withIndex()) {
            assertTrue("layer $index", frame.insetBy(-0.01, -0.01).contains(layer.path.boundingBoxOfPath))
        }
    }

    @Test
    fun theTileIsYellowTheWhitesPaperAndEachPupilInkWithAWhiteCatchlight() {
        val pupil = listOf(0x171714, 0xFFFFFF)
        assertEquals(listOf(0xF3CF48, 0xFFFAF0, 0xFFFAF0) + pupil + pupil, layers.map { hex(it.color) })
    }

    /** Adapted: which fill is the last to cover each pixel's centre, rather than the pixel CoreGraphics renders. */
    @Test
    fun drawingPaintsTheLayersBackToFrontWithYPointingDown() {
        val drawing = PeekMarkArtwork.draw()
        assertEquals(layers.size, drawing.size)
        val clear: Int? = null
        // Outside the rounded corner, the tile, then the white, the pupil and the catchlight of each eye.
        val expected = mutableListOf(Triple(2, 2, clear), Triple(256, 60, 0xF3CF48))
        for (eye in listOf(0, 132)) {
            expected += listOf(Triple(150 + eye, 262, 0xFFFAF0), Triple(225 + eye, 285, 0x171714), Triple(207 + eye, 255, 0xFFFFFF))
        }
        // The left pupil stays whole where the right eye's white reaches over it.
        expected += Triple(253, 269, 0x171714)
        for ((x, y, color) in expected) {
            val centre = Point(x + 0.5, y + 0.5)
            val top = drawing.lastOrNull { it is MapDrawing.Fill && it.path.containsEvenOdd(centre) } as MapDrawing.Fill?
            assertEquals("($x, $y)", color, top?.let { hex(it.color.resolve(dark = false)) })
            top?.let { fill ->
                assertFalse(fill.cut)
                assertEquals(1.0, fill.opacity, 0.0)
                assertEquals("one colour in every appearance", fill.color.light, fill.color.resolve(dark = true, highContrast = true))
            }
        }
    }

    @Test
    fun theEyesAloneAreOneColourWithThePupilsCutAroundTheirCatchlights() {
        val ink = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0))
        val steps = PeekMarkArtwork.drawEyes(ink).map { it as MapDrawing.Fill }
        assertEquals(listOf(false, true, false), steps.map { it.cut })
        assertEquals(ink, steps[0].color)
        assertEquals(ink, steps[2].color)
        assertEquals(PeekMarkArtwork.eyesBounds, steps[0].path.boundingBoxOfPath)
        // Each step holds both eyes' parts, the right one 132 units further right: the whites, pupils and catchlights.
        for ((step, left) in steps.zip(listOf(1, 3, 4))) {
            val box = step.path.boundingBoxOfPath
            val part = layers[left].path.boundingBoxOfPath
            assertEquals(part.minX, box.minX, 1e-9)
            assertEquals(part.maxX + 132, box.maxX, 1e-9)
            assertEquals(part.minY, box.minY, 1e-9)
            assertEquals(part.maxY, box.maxY, 1e-9)
        }
    }

    @Test
    fun theMarkScalesTheFrameToItsSizeAndClipsItToAContinuousTile() {
        val mark = PeekMark(size = 64.0)
        assertEquals(0.125, mark.scale, 0.0)
        assertEquals(PeekMarkArtwork.draw().size, mark.drawing.size)
        var clip: List<Double>? = null
        mark.clip.replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) = error("move")
            override fun line(x: Double, y: Double) = error("line")
            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) = error("curve")
            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) = error("arc")
            override fun ellipse(x: Double, y: Double, width: Double, height: Double) = error("ellipse")
            override fun roundedRect(x: Double, y: Double, width: Double, height: Double, cornerRadius: Double) {
                clip = listOf(x, y, width, height, cornerRadius)
            }
            override fun close() = error("close")
        })
        assertEquals(listOf(0.0, 0.0, 64.0, 64.0, 14.5), clip)
    }

    /** Points around the edge of a round layer. */
    private fun edge(layer: PeekMarkArtwork.Layer): List<Point> {
        val box = layer.path.boundingBoxOfPath
        return (0 until 360 step 15).map { degrees ->
            val angle = degrees * PI / 180
            Point(box.midX + cos(angle) * box.width / 2, box.midY + sin(angle) * box.height / 2)
        }
    }

    /** An opaque colour as 0xRRGGBB. */
    private fun hex(color: RgbaColor): Int? {
        if (color.opacity != 1.0) return null
        return listOf(color.red, color.green, color.blue).fold(0) { hex, part -> hex shl 8 or (part * 255).roundToInt() }
    }
}
