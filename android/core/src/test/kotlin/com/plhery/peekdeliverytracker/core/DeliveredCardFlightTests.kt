package com.plhery.peekdeliverytracker.core

import kotlin.math.abs
import kotlin.math.hypot
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** ParcelLogicTests.swift's delivered paper and flight: the burst out of the box, and a card on its way to its new place. */
class DeliveredCardFlightTests {
    @Test
    fun seededNumbersAreSplitMix64AndSwiftsFractions() {
        // SplitMix64's own first numbers from a seed of 0.
        val numbers = SeededNumbers(0uL)
        assertEquals(0xE220_A839_7B1D_CDAFuL, numbers.next())
        assertEquals(0x6E78_9E6A_A1B9_65F4uL, numbers.next())
        // `Double.random(in: 0..<1, using:)` takes the low 53 bits.
        val unit = SeededNumbers(0uL).unit()
        assertEquals((0xE220_A839_7B1D_CDAFuL and ((1uL shl 53) - 1uL)).toDouble() / (1L shl 53), unit, 0.0)
        assertTrue(unit >= 0 && unit < 1)
    }

    @Test
    fun deliveredConfettiPopsOutOfItsOriginAndFallsAway() {
        val origin = Point(200.0, 300.0)
        val burst = DeliveredConfetti(origin, DeliveredConfetti.next, 7uL)
        // A burst follows from its seed.
        assertEquals(DeliveredConfetti(origin, DeliveredConfetti.next, 7uL), burst)
        assertNotEquals(DeliveredConfetti(origin, DeliveredConfetti.next, 8uL).pieces, burst.pieces)
        assertEquals(58, burst.pieces.size)
        assertEquals(38, DeliveredConfetti(origin, DeliveredConfetti.other, 7uL).pieces.size)
        assertEquals(6, burst.glints.size)
        assertTrue(burst.duration > 1.5)
        assertTrue(burst.duration <= 2.4)
        assertTrue(burst.pieces.map { it.tone }.toSet().size > 4)

        for (piece in burst.pieces) {
            assertTrue(piece.tone in 0 until DeliveredConfetti.tones)
            // Not there before it is thrown, nor once it has gone.
            assertNull(burst.pose(piece, piece.delay - 0.001))
            assertNull(burst.pose(piece, piece.delay + piece.life + 0.001))
            val start = burst.pose(piece, piece.delay)!!
            assertEquals(origin.x, start.centerX, 0.001)
            assertEquals(origin.y, start.centerY, 0.001)
            assertEquals(0.0, start.opacity, 0.001)
            // Up out of the box, then down, a long way below its highest point, and faint by the end.
            val heights = generateSequence(0.0) { it + 0.02 }.takeWhile { it < piece.life }
                .mapNotNull { burst.pose(piece, piece.delay + it)?.centerY }.toList()
            val top = heights.min()
            assertTrue(top < origin.y - 5)
            assertTrue(heights.last() > top + 100)
            assertEquals(1.0, burst.pose(piece, piece.delay + 0.3)!!.opacity, 0.001)
            assertTrue(burst.pose(piece, piece.delay + piece.life - 0.01)!!.opacity < 0.05)
        }
        for (glint in burst.glints) {
            assertNull(burst.pose(glint, glint.delay + glint.life + 0.001))
            val start = burst.pose(glint, glint.delay)!!
            assertEquals(origin.x, start.centerX, 0.001)
            assertEquals(0.0, start.scaleX, 0.001)
            // It flies out as far as it reaches, shrinking to nothing.
            val end = burst.pose(glint, glint.delay + glint.life - 0.0001)!!
            assertEquals(glint.reach, hypot(end.centerX - origin.x, end.centerY - origin.y), 0.5)
            assertTrue(end.scaleX < 0.01)
        }
        // The paper's colours: the card's ink, its carrier's and the app's yellow, one for each tone.
        val black = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0))
        val white = AdaptiveColor(RgbaColor(1.0, 1.0, 1.0))
        val red = AdaptiveColor(RgbaColor(1.0, 0.0, 0.0))
        assertEquals(DeliveredConfetti.tones, DeliveredConfetti.colors(black, white, red).size)
    }

    @Test
    fun aPoseSetAgainIsTheSameAsANewOne() {
        val burst = DeliveredConfetti(Point(10.0, 20.0), DeliveredConfetti.other, 3uL)
        val kept = DeliveredConfetti.Pose()
        val piece = burst.pieces[5]
        assertTrue(burst.pose(piece, piece.delay + 0.4, kept))
        val made = burst.pose(piece, piece.delay + 0.4)!!
        assertEquals(made.centerX, kept.centerX, 0.0)
        assertEquals(made.centerY, kept.centerY, 0.0)
        assertEquals(made.turn, kept.turn, 0.0)
        assertEquals(made.scaleY, kept.scaleY, 0.0)
        assertEquals(made.opacity, kept.opacity, 0.0)
        // Gone, it leaves the pose as it was.
        assertFalse(burst.pose(piece, piece.delay + piece.life + 1, kept))
        assertEquals(made.centerY, kept.centerY, 0.0)
    }

    @Test
    fun deliveredFlightFoldsEarlyGrowsLateAndKeepsTheNamesOnOneLine() {
        val high = DeliveryCardPlace(Rect(16.0, 62.0, 370.0, 242.0), title = 166.0)
        val low = DeliveryCardPlace(Rect(16.0, 474.0, 370.0, 102.0), title = 54.0)
        fun falling(progress: Double) =
            DeliveredFlightPose(high, low, DeliveryCardKind.Next, DeliveryCardKind.Past, lifted = true, progress = progress)
        assertEquals(high.frame, falling(0.0).frame)
        assertEquals(24.0, falling(0.0).cornerRadius, 0.0)
        assertEquals(0.0, abs(falling(0.0).leaving), 0.0)
        assertEquals(112.0, falling(0.0).arriving, 0.0)
        assertEquals(0.0, falling(0.0).arrival, 0.0)
        assertEquals(0.0, falling(0.0).shadow, 0.0)
        // A card that shrinks is small well before it lands, and casts its shadow on the way.
        assertEquals(102.0, falling(0.72).height, 0.001)
        assertEquals(62 + 412 * 0.72, falling(0.72).minY, 0.001)
        assertEquals(1.0, falling(0.5).shadow, 0.0)
        assertEquals(low.frame, falling(1.0).frame)
        assertEquals(16.0, falling(1.0).cornerRadius, 0.0)
        assertEquals(-112.0, falling(1.0).leaving, 0.0)
        assertEquals(0.0, falling(1.0).arriving, 0.0)
        assertEquals(1.0, falling(1.0).arrival, 0.0)
        assertEquals(0.0, falling(1.0).shadow, 0.0)
        // Past its place, as a spring goes, the card moves on but keeps its shape.
        assertEquals(102.0, falling(1.02).height, 0.001)
        assertTrue(falling(1.02).minY > 474)

        // The card that takes the place grows late, and is not lifted.
        val rising = DeliveredFlightPose(low, high, DeliveryCardKind.OnTheWay, DeliveryCardKind.Next, lifted = false, progress = 0.28)
        assertEquals(102.0, rising.height, 0.001)
        assertEquals(0.0, rising.arrival, 0.0)
        assertEquals(0.0, rising.shadow, 0.0)
        val landed = DeliveredFlightPose(low, high, DeliveryCardKind.OnTheWay, DeliveryCardKind.Next, lifted = false, progress = 1.0)
        assertEquals(high.frame, landed.frame)
        // One pose set again for another flight is that flight's.
        assertEquals(falling(0.5).frame, landed.set(high, low, DeliveryCardKind.Next, DeliveryCardKind.Past, lifted = true, progress = 0.5).frame)
    }

    @Test
    fun aRoundedRectangleAddedToAKeptPathIsTheSameAsANewOne() {
        val kept = MapPath()
        for ((rect, radius) in listOf(Rect(16.0, 62.0, 370.0, 242.0) to 24.0, Rect(3.0, 4.0, 40.0, 20.0) to 16.0, Rect(0.0, 0.0, 10.0, 10.0) to 0.0)) {
            kept.rewind()
            kept.addRoundedRect(rect.minX, rect.minY, rect.maxX, rect.maxY, radius)
            assertEquals(elements(MapPath.roundedRect(rect, radius)), elements(kept))
        }
        assertEquals(elements(MapPath.rect(Rect(0.0, 0.0, 10.0, 10.0))), elements(MapPath.roundedRect(Rect(0.0, 0.0, 10.0, 10.0), 0.0)))
    }

    /** A path's elements as words and numbers, to compare two paths by. */
    private fun elements(path: MapPath): List<String> = mutableListOf<String>().also { out ->
        path.replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) { out += "move $x $y" }
            override fun line(x: Double, y: Double) { out += "line $x $y" }
            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) { out += "quad $x $y $controlX $controlY" }
            override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) {
                out += "curve $x $y $control1X $control1Y $control2X $control2Y"
            }
            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) {
                out += "arc $centerX $centerY $radius $startAngle $delta"
            }
            override fun ellipse(x: Double, y: Double, width: Double, height: Double) { out += "ellipse $x $y $width $height" }
            override fun close() { out += "close" }
        })
    }
}
