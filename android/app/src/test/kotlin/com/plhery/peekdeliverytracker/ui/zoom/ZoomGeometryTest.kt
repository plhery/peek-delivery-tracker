package com.plhery.peekdeliverytracker.ui.zoom

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The zoom's geometry against the iPhone's captures: a 440 × 956 point window, the Next up card 408 wide at 16 from
 * each edge. All in points here, a density of 1.
 */
class ZoomGeometryTest {
    private val window = Rect(0f, 0f, 440f, 956f)
    private val card = Rect(16f, 180f, 424f, 420f)

    private fun assertRect(expected: Rect, actual: Rect, tolerance: Float = 0.01f) {
        assertEquals("left", expected.left, actual.left, tolerance)
        assertEquals("top", expected.top, actual.top, tolerance)
        assertEquals("right", expected.right, actual.right, tolerance)
        assertEquals("bottom", expected.bottom, actual.bottom, tolerance)
    }

    @Test
    fun theContainerMovesEachEdgeOnAStraightLine() {
        assertRect(card, ZoomGeometry.frame(card, window, 0f))
        assertRect(window, ZoomGeometry.frame(card, window, 1f))
        assertRect(Rect(8f, 90f, 432f, 688f), ZoomGeometry.frame(card, window, 0.5f))
        // A spring that overshoots takes the container past the window.
        assertTrue(ZoomGeometry.frame(card, window, 1.02f).width > window.width)
    }

    @Test
    fun theCornersGoFromTheCardsToTheDisplays() {
        assertEquals(24f, ZoomGeometry.cornerRadius(24f, 62f, 0f), 0.001f)
        assertEquals(43f, ZoomGeometry.cornerRadius(24f, 62f, 0.5f), 0.001f)
        assertEquals(62f, ZoomGeometry.cornerRadius(24f, 62f, 1f), 0.001f)
    }

    @Test
    fun theCardsPictureGivesWayToThePageOverTheFirstHalf() {
        assertEquals(1f, ZoomGeometry.faceOpacity(0f), 0f)
        assertEquals(0.5f, ZoomGeometry.faceOpacity(0.25f), 0.0001f)
        assertEquals(0f, ZoomGeometry.faceOpacity(0.5f), 0f)
        assertEquals(0f, ZoomGeometry.faceOpacity(1f), 0f)
        assertEquals(1f, ZoomGeometry.pageOpacity(0.7f), 0f)
        // Below zero, as a spring may take it on its way back, the page is gone.
        assertEquals(0f, ZoomGeometry.pageOpacity(-0.01f), 0f)
    }

    @Test
    fun theListRecedesTo92PercentAndDimsTo15UnderThePage() {
        assertEquals(1f, ZoomGeometry.receded(0f), 0f)
        assertEquals(0.96f, ZoomGeometry.receded(0.5f), 0.0001f)
        assertEquals(0.92f, ZoomGeometry.receded(1f), 0.0001f)
        assertEquals(0.92f, ZoomGeometry.receded(1.1f), 0.0001f)
        assertEquals(0f, ZoomGeometry.dim(0f), 0f)
        assertEquals(0.15f, ZoomGeometry.dim(1f), 0.0001f)
    }

    @Test
    fun thePagesBarComesInOverTheLast40PercentOfAnOpeningAndGoesOverTheLastQuarterOfAClosing() {
        assertEquals(0f, ZoomGeometry.pageBarOpacity(0f, closing = false), 0f)
        assertEquals(0f, ZoomGeometry.pageBarOpacity(0.6f, closing = false), 0f)
        assertEquals(0.5f, ZoomGeometry.pageBarOpacity(0.8f, closing = false), 0.0001f)
        assertEquals(1f, ZoomGeometry.pageBarOpacity(1f, closing = false), 0f)
        // A spring past the window keeps it whole.
        assertEquals(1f, ZoomGeometry.pageBarOpacity(1.02f, closing = false), 0f)

        assertEquals(1f, ZoomGeometry.pageBarOpacity(1f, closing = true), 0f)
        assertEquals(1f, ZoomGeometry.pageBarOpacity(0.25f, closing = true), 0f)
        assertEquals(0.4f, ZoomGeometry.pageBarOpacity(0.1f, closing = true), 0.0001f)
        assertEquals(0f, ZoomGeometry.pageBarOpacity(0f, closing = true), 0f)
        assertEquals(0f, ZoomGeometry.pageBarOpacity(-0.01f, closing = true), 0f)
    }

    @Test
    fun aPageDraggedDownShrinksAboutTheFinger() {
        // The captures: 100 points down, 1 - 100 / (1.55 × 956) of its size.
        val finger = Offset(220f, 500f)
        val dragged = ZoomGeometry.dragFrame(window, finger, Offset(0f, 100f))
        val scale = 1f - 100f / (1.55f * 956f)
        assertEquals(440f * scale, dragged.width, 0.01f)
        assertEquals(956f * scale, dragged.height, 0.01f)
        // The point under the finger stays under it.
        assertEquals(220f, dragged.left + (finger.x - window.left) * scale, 0.01f)
        assertEquals(600f, dragged.top + (finger.y - window.top) * scale, 0.01f)
        // Sideways, it follows the finger.
        val aside = ZoomGeometry.dragFrame(window, finger, Offset(-30f, 100f))
        assertEquals(dragged.left - 30f, aside.left, 0.01f)
        // It never gets smaller than 40 %.
        assertEquals(0.4f, ZoomGeometry.dragScale(10_000f, 956f), 0f)
    }

    @Test
    fun aPageDraggedUpGivesOnlyALittle() {
        val dragged = ZoomGeometry.dragFrame(window, Offset(220f, 500f), Offset(0f, -300f))
        assertEquals(window.width, dragged.width, 0.01f)
        assertTrue("Up by ${-dragged.top}", -dragged.top > 0f && -dragged.top < 150f)
    }

    @Test
    fun aDragClosesThePagePastAQuarterOfTheWindowOrFlungThere() {
        assertFalse(ZoomGeometry.dismisses(90f, 0f, 956f))
        assertTrue(ZoomGeometry.dismisses(240f, 0f, 956f))
        // 50 points down at 800 points a second comes to rest 0.499 s on, past the quarter.
        assertTrue(ZoomGeometry.dismisses(50f, 800f, 956f))
        assertFalse(ZoomGeometry.dismisses(50f, 300f, 956f))
        // Flung back up, it stays.
        assertFalse(ZoomGeometry.dismisses(300f, -1000f, 956f))
        assertFalse(ZoomGeometry.dismisses(-10f, 5000f, 956f))
        assertEquals(0.499f, ZoomGeometry.PROJECTION, 0.0001f)
    }

    @Test
    fun thePredictiveBackShrinksThePageAwayFromTheEdge() {
        val gap = 8f
        val lift = 24f
        assertRect(window, ZoomGeometry.backFrame(window, 0f, fromLeft = true, touchDeltaY = 0f, edgeGap = gap, lift = lift))
        val left = ZoomGeometry.backFrame(window, 1f, fromLeft = true, touchDeltaY = 0f, edgeGap = gap, lift = lift)
        assertEquals(396f, left.width, 0.01f)
        assertEquals(860.4f, left.height, 0.01f)
        // Swiped from the left, it goes right as far as leaves 8 from the edge.
        assertEquals(440f - gap, left.right, 0.01f)
        val right = ZoomGeometry.backFrame(window, 1f, fromLeft = false, touchDeltaY = 0f, edgeGap = gap, lift = lift)
        assertEquals(gap, right.left, 0.01f)
        assertEquals(left.top, right.top, 0.01f)
        // The finger up the whole window lifts it 24, never more.
        val lifted = ZoomGeometry.backFrame(window, 1f, fromLeft = true, touchDeltaY = -956f, edgeGap = gap, lift = lift)
        assertEquals(left.top - lift, lifted.top, 0.01f)
        val halfway = ZoomGeometry.backFrame(window, 1f, fromLeft = true, touchDeltaY = 478f, edgeGap = gap, lift = lift)
        assertEquals(left.top + lift / 2, halfway.top, 0.01f)
    }

    @Test
    fun aPageLetGoKeepsTheFingersSpeedOnItsWayBack() {
        val held = Rect(40f, 300f, 400f, 1082f)
        // The card above the page: the top goes up 120 points over the flight, at 600 a second, 5 of progress a second.
        assertEquals(-5f, ZoomGeometry.closingVelocity(card, held, -600f), 0.001f)
        // Its card below the page and the finger going down: the top goes down 400 points, at 600 a second.
        val low = Rect(16f, 700f, 424f, 800f)
        assertEquals(-1.5f, ZoomGeometry.closingVelocity(low, held, 600f), 0.001f)
        // Never away from the card, and never faster than 12 a second.
        assertEquals(0f, ZoomGeometry.closingVelocity(card, held, 600f), 0f)
        assertEquals(-12f, ZoomGeometry.closingVelocity(card, held, -100_000f), 0f)
        assertEquals(0f, ZoomGeometry.closingVelocity(held, held, 600f), 0f)
    }

    @Test
    fun aPageWithoutACardComesFromTheMiddleOfTheWindow() {
        val safe = Rect(0f, 62f, 440f, 922f)
        val frame = ZoomGeometry.fallback(safe, density = 1f)
        assertEquals(408f, frame.width, 0.01f)
        assertEquals(100f, frame.height, 0.01f)
        assertEquals(safe.center.x, frame.center.x, 0.01f)
        assertEquals(safe.center.y, frame.center.y, 0.01f)
        // On a tablet it is as wide as the list's column at most.
        val tablet = ZoomGeometry.fallback(Rect(0f, 0f, 2000f, 800f), density = 2f)
        assertEquals(1256f, tablet.width, 0.01f)
        assertEquals(200f, tablet.height, 0.01f)
    }

    @Test
    fun aHingeSendsTheFallbackToItsWiderSide() {
        val safe = Rect(0f, 0f, 1000f, 800f)
        val frame = ZoomGeometry.fallback(safe, density = 1f, hinge = 380f..420f)
        assertEquals(710f, frame.center.x, 0.01f)
        assertEquals(548f, frame.width, 0.01f)
        val other = ZoomGeometry.fallback(safe, density = 1f, hinge = 600f..640f)
        assertEquals(300f, other.center.x, 0.01f)
    }
}
