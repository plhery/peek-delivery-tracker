package com.plhery.peekdeliverytracker.ui.draw

import android.graphics.PathIterator
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.MapPath
import com.plhery.peekdeliverytracker.core.Point
import com.plhery.peekdeliverytracker.core.Rect
import com.plhery.peekdeliverytracker.core.RectangleCornerRadii
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode
import kotlin.math.PI

/** The outlines and paths, element for element, against :core's (SwiftUI's) own. */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ShapesTest {
    /** One element: its kind and the points it ends at and is pulled toward, as SwiftUI's `Path.forEach` gives them. */
    private data class Element(val kind: Char, val points: List<Float>)

    private fun elements(path: MapPath): List<Element> {
        val out = mutableListOf<Element>()
        fun add(kind: Char, vararg numbers: Double) = out.add(Element(kind, numbers.map(Double::toFloat)))
        path.replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) {
                add('M', x, y)
            }

            override fun line(x: Double, y: Double) {
                add('L', x, y)
            }

            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) {
                add('Q', controlX, controlY, x, y)
            }

            override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                add('C', control1X, control1Y, control2X, control2Y, x, y)
            }

            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) =
                error("not expected")

            override fun ellipse(x: Double, y: Double, width: Double, height: Double) = error("not expected")

            override fun close() {
                add('Z')
            }
        })
        return out
    }

    /** Compose's path read back through Android's own iterator, each element without the point it starts from. */
    private fun elements(path: Path): List<Element> {
        val out = mutableListOf<Element>()
        val iterator = path.asAndroidPath().pathIterator
        val p = FloatArray(8)
        while (true) {
            when (iterator.next(p, 0)) {
                PathIterator.VERB_MOVE -> out += Element('M', p.take(2))
                PathIterator.VERB_LINE -> out += Element('L', p.drop(2).take(2))
                PathIterator.VERB_QUAD -> out += Element('Q', p.drop(2).take(4))
                PathIterator.VERB_CUBIC -> out += Element('C', p.drop(2).take(6))
                PathIterator.VERB_CLOSE -> out += Element('Z', emptyList())
                PathIterator.VERB_DONE -> return out
                else -> error("unexpected verb")
            }
        }
    }

    private fun assertSameElements(expected: List<Element>, actual: List<Element>, tolerance: Float = 0.0001f) {
        assertEquals(expected.map { it.kind }.joinToString(""), actual.map { it.kind }.joinToString(""))
        for ((index, pair) in expected.zip(actual).withIndex()) {
            assertEquals("element $index", pair.first.points.size, pair.second.points.size)
            for (n in pair.first.points.indices) {
                assertEquals("element $index number $n", pair.first.points[n], pair.second.points[n], tolerance)
            }
        }
    }

    private fun outline(
        shape: androidx.compose.ui.graphics.Shape,
        width: Float,
        height: Float,
        density: Float,
        direction: LayoutDirection = LayoutDirection.Ltr,
    ): Path = (shape.createOutline(Size(width, height), direction, Density(density)) as Outline.Generic).path

    @Test
    fun aContinuousRoundedShapeIsSwiftUIsRoundedRectangle() {
        // The radii X-AD37 compares with the iPhone, on a card and on a hero, at three pixels a point.
        for ((radius, size) in listOf(24f to Size(358f, 120f), 38f to Size(390f, 300f), 16f to Size(40f, 20f))) {
            val path = outline(ContinuousRoundedShape(radius.dp), size.width * 3, size.height * 3, density = 3f)
            val expected = MapPath.roundedRect(Rect(0.0, 0.0, size.width * 3.0, size.height * 3.0), radius * 3.0)
            assertSameElements(elements(expected), elements(path), tolerance = 0.001f)
        }
    }

    @Test
    fun anUnevenShapeIsSwiftUIsUnevenRoundedRectangle() {
        // The first-open hero's corners (38 at the bottom) and the feedback card's (5 at the bottom leading), at three
        // pixels a point.
        val shapes = listOf(
            UnevenContinuousRoundedShape(bottomLeading = 38.dp, bottomTrailing = 38.dp) to RectangleCornerRadii(0.0, 114.0, 114.0, 0.0),
            UnevenContinuousRoundedShape(18.dp, 5.dp, 18.dp, 18.dp) to RectangleCornerRadii(54.0, 15.0, 54.0, 54.0),
        )
        for ((shape, radii) in shapes) {
            val expected = MapPath.unevenRoundedRect(Rect(0.0, 0.0, 1170.0, 180.0), radii)
            assertSameElements(elements(expected), elements(outline(shape, 1170f, 180f, density = 3f)), tolerance = 0.001f)
        }
    }

    @Test
    fun rightToLeftSwapsLeadingAndTrailing() {
        val shape = UnevenContinuousRoundedShape(topLeading = 24.dp, bottomLeading = 5.dp)
        val path = outline(shape, 300f, 100f, density = 1f, direction = LayoutDirection.Rtl)
        val expected = MapPath.unevenRoundedRect(Rect(0.0, 0.0, 300.0, 100.0), RectangleCornerRadii(topTrailing = 24.0, bottomTrailing = 5.0))
        assertSameElements(elements(expected), elements(path))
    }

    @Test
    fun anArcIsCoreGraphicsQuarterTurnsThenTheRest() {
        // Three eighths of a turn: a quarter, then the eighth left, where an even split would be two curves of 3/16 each.
        val path = MapPath().also { it.addRelativeArc(Point(0.0, 0.0), 10.0, 0.0, 3 * PI / 4) }
        val actual = elements(path.toComposePath())
        assertEquals("MCC", actual.joinToString("") { it.kind.toString() })
        assertEquals(0f, actual[1].points[4], 0.0001f)
        assertEquals(10f, actual[1].points[5], 0.0001f)
        assertEquals(-7.0710678f, actual[2].points[4], 0.0001f)
        assertEquals(7.0710678f, actual[2].points[5], 0.0001f)
    }

    @Test
    fun aCapsuleIsSwiftUIsCapsule() {
        // SwiftUI's own `Capsule().path(in: CGRect(0, 0, 100, 30))`, printed on a Mac.
        val path = outline(CapsuleShape, 100f, 30f, density = 1f)
        val actual = elements(path)
        assertEquals(Element('M', listOf(100f, 15f)), actual[0])
        assertEquals(Element('L', listOf(100f, 15f)), actual[1])
        assertSameElements(
            listOf(Element('C', listOf(100f, 15.6f, 100f, 17.7f, 98.8763f, 20.5276f))),
            listOf(actual[2]),
            tolerance = 0.0001f,
        )
        assertSameElements(elements(MapPath.roundedRect(Rect(0.0, 0.0, 100.0, 30.0), 15.0)), actual)
        // Upright, the radius is half the width.
        val upright = outline(CapsuleShape, 30f, 100f, density = 1f)
        assertSameElements(elements(MapPath.roundedRect(Rect(0.0, 0.0, 30.0, 100.0), 15.0)), elements(upright))
    }

    @Test
    fun aRadiusPastHalfTheShortSideStopsThere() {
        val path = outline(ContinuousRoundedShape(100.dp), 80f, 40f, density = 1f)
        assertSameElements(elements(MapPath.roundedRect(Rect(0.0, 0.0, 80.0, 40.0), 20.0)), elements(path))
    }

    @Test
    fun anEllipseIsCoreGraphicsFourCurvesFromTheRightMiddle() {
        // `Circle().path(in: CGRect(0, 0, 30, 30))` on a Mac: M 30 15, then C 30 23.2843 23.2843 30 15 30 and round.
        val circle = MapPath.ellipse(Rect(0.0, 0.0, 30.0, 30.0)).toComposePath()
        val expected = listOf(
            Element('M', listOf(30f, 15f)),
            Element('C', listOf(30f, 23.28427f, 23.28427f, 30f, 15f, 30f)),
            Element('C', listOf(6.71573f, 30f, 0f, 23.28427f, 0f, 15f)),
            Element('C', listOf(0f, 6.71573f, 6.71573f, 0f, 15f, 0f)),
            Element('C', listOf(23.28427f, 0f, 30f, 6.71573f, 30f, 15f)),
            Element('Z', emptyList()),
        )
        assertSameElements(expected, elements(circle), tolerance = 0.0001f)
    }

    @Test
    fun aFullTurnArcDrawsWholeAndStartsWithALineFromTheCurrentPoint() {
        val path = MapPath().also {
            it.move(0.0, 0.0)
            it.addRelativeArc(Point(50.0, 50.0), 10.0, 0.0, 2 * PI)
        }
        val actual = elements(path.toComposePath())
        assertEquals("MLCCCC", actual.joinToString("") { it.kind.toString() })
        assertEquals(listOf(60f, 50f), actual[1].points)
        // A quarter turn each, ending back at the start.
        assertSameElements(listOf(Element('C', listOf(60f, 55.52285f, 55.52285f, 60f, 50f, 60f))), listOf(actual[2]))
        assertEquals(60f, actual[5].points[4], 0.0001f)
        assertEquals(50f, actual[5].points[5], 0.0001f)
    }

    @Test
    fun anArcAgainstTheClockTurnsTowardNegativeY() {
        val path = MapPath().also { it.addRelativeArc(Point(0.0, 0.0), 10.0, 0.0, -PI / 2) }
        val actual = elements(path.toComposePath())
        assertEquals("MC", actual.joinToString("") { it.kind.toString() })
        assertEquals(listOf(10f, 0f), actual[0].points)
        assertEquals(0f, actual[1].points[4], 0.0001f)
        assertEquals(-10f, actual[1].points[5], 0.0001f)
    }

    @Test
    fun aLineWithNoCurrentPointStartsTheSubpathAndAnUnstartedCurveIsSkipped() {
        val path = MapPath().also {
            it.addCurve(Point(5.0, 5.0), Point(1.0, 1.0), Point(2.0, 2.0))
            it.addLine(1.0, 2.0)
            it.addLine(3.0, 4.0)
            it.addQuadCurve(Point(6.0, 4.0), Point(5.0, 8.0))
        }
        assertSameElements(
            listOf(
                Element('M', listOf(1f, 2f)),
                Element('L', listOf(3f, 4f)),
                Element('Q', listOf(5f, 8f, 6f, 4f)),
            ),
            elements(path.toComposePath()),
        )
    }
}
