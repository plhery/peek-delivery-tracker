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

    private fun assertBar(expected: ZoomBar, actual: ZoomBar) {
        assertEquals("leading", expected.leading, actual.leading, 0.0001f)
        assertEquals("trailing", expected.trailing, actual.trailing, 0.0001f)
        assertEquals("root", expected.root, actual.root, 0.0001f)
        assertEquals("title", expected.title, actual.title, 0.0001f)
    }

    @Test
    fun anOpeningBringsThePagesBarInBetween82And97PercentAsTheListsItemsFadeWithIt() {
        val open = { progress: Float -> ZoomGeometry.openingBar(ZoomBar.Root, 0f, progress) }
        assertBar(ZoomBar.Root, open(0f))
        assertBar(ZoomBar(0f, 0f, 0.5f, 0.5f), open(0.5f))
        assertBar(ZoomBar(0f, 0f, 0.18f, 0.18f), open(0.82f))
        assertBar(ZoomBar(0.6f, 0.6f, 0.09f, 0.09f), open(0.91f))
        assertBar(ZoomBar(1f, 1f, 0.03f, 0.03f), open(0.97f))
        assertBar(ZoomBar(1f, 1f, 0f, 0f), open(1f))
        // A spring past the window keeps the page's bar whole and the list's items gone.
        assertBar(ZoomBar(1f, 1f, 0f, 0f), open(1.02f))
    }

    @Test
    fun aClosingHidesBackAtOnceThenTheMenuThenBringsTheListsItemsBackAsItsTitleComesWithThePage() {
        val page = ZoomBar(1f, 1f, 0f, 0f)
        assertBar(ZoomBar(0f, 1f, 0f, 0f), ZoomGeometry.closingBar(page, 1f, 1f, 0f))
        assertBar(ZoomBar(0f, 1f, 0f, 0.2f), ZoomGeometry.closingBar(page, 1f, 0.8f, 0.05f))
        assertBar(ZoomBar(0f, 0.5f, 0f, 0.3f), ZoomGeometry.closingBar(page, 1f, 0.7f, 0.06f))
        // The ••• gone 0.07 s in, the list's items come back over 0.17 s.
        assertBar(ZoomBar(0f, 0f, 0f, 0.4f), ZoomGeometry.closingBar(page, 1f, 0.6f, 0.07f))
        assertBar(ZoomBar(0f, 0f, 0.5f, 0.8f), ZoomGeometry.closingBar(page, 1f, 0.2f, 0.155f))
        assertBar(ZoomBar(0f, 0f, 1f, 1f), ZoomGeometry.closingBar(page, 1f, 0f, 0.24f))
        // Let go with its title part way back, the title comes the rest of the way with the page.
        assertBar(ZoomBar(0f, 1f, 0f, 0.4f), ZoomGeometry.closingBar(ZoomBar(1f, 1f, 0f, 0.4f), 1f, 1f, 0f))
        assertBar(ZoomBar(0f, 0f, 0.5f, 0.7f), ZoomGeometry.closingBar(ZoomBar(1f, 1f, 0f, 0.4f), 1f, 0.5f, 0.155f))
    }

    @Test
    fun aFlightCutShortGoesOnFromWhereTheItemsAreWithoutAJump() {
        // Back half way through an opening: the list's items, half there, come back at once, with no ••• to wait for.
        val halfOpen = ZoomGeometry.openingBar(ZoomBar.Root, 0f, 0.5f)
        assertBar(halfOpen, ZoomGeometry.closingBar(halfOpen, 0.5f, 0.5f, 0f))
        assertBar(ZoomBar(0f, 0f, 0.75f, 0.75f), ZoomGeometry.closingBar(halfOpen, 0.5f, 0.25f, 0.085f))
        assertBar(ZoomBar(0f, 0f, 1f, 1f), ZoomGeometry.closingBar(halfOpen, 0.5f, 0f, 0.17f))
        // Opened again on its way back: from where the closing left each item, to the page's.
        val closing = ZoomBar(0f, 0.4f, 0.3f, 0.6f)
        assertBar(closing, ZoomGeometry.openingBar(closing, 0.6f, 0.6f))
        assertBar(ZoomBar(0f, 0.4f, 0.15f, 0.3f), ZoomGeometry.openingBar(closing, 0.6f, 0.8f))
        assertBar(ZoomBar(1f, 1f, 0f, 0f), ZoomGeometry.openingBar(closing, 0.6f, 1f))
        // Turned round past 82 %, the page's items come in from there.
        assertBar(ZoomBar(0f, 0.4f, 0.3f, 0.6f), ZoomGeometry.openingBar(closing, 0.9f, 0.9f))
        assertEquals(0.5f, ZoomGeometry.openingBar(closing, 0.9f, 0.935f).leading, 0.0001f)
    }

    @Test
    fun theListsTitleComesBackAsAGestureShrinksThePageBelow83Percent() {
        assertEquals(0f, ZoomGeometry.titleUncovered(1f), 0f)
        assertEquals(0f, ZoomGeometry.titleUncovered(0.9f), 0f)
        assertEquals(0f, ZoomGeometry.titleUncovered(0.826f), 0.0001f)
        // The captures: 0.13 of it at 79 %, 0.37 at 72.7 %.
        assertEquals(0.13f, ZoomGeometry.titleUncovered(0.791f), 0.005f)
        assertEquals(0.367f, ZoomGeometry.titleUncovered(0.727f), 0.005f)
        assertEquals(1f, ZoomGeometry.titleUncovered(0.4f), 0f)
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
    fun aFingerTakesThePageSidewaysPastTheSlopGoingMoreThan125TimesAsFarAcross() {
        assertEquals(0, ZoomGeometry.swipeSide(Offset(8f, 0f), slop = 8f))
        assertEquals(1, ZoomGeometry.swipeSide(Offset(9f, 0f), slop = 8f))
        assertEquals(-1, ZoomGeometry.swipeSide(Offset(-9f, 2f), slop = 8f))
        // As far across as 1.25 times up or down is too steep; a little further across isn't.
        assertEquals(0, ZoomGeometry.swipeSide(Offset(10f, -8f), slop = 8f))
        assertEquals(1, ZoomGeometry.swipeSide(Offset(10.1f, -8f), slop = 8f))
        assertEquals(0, ZoomGeometry.swipeSide(Offset(0f, 20f), slop = 8f))
    }

    @Test
    fun aPageCarriedSidewaysFollowsTheFingerAndShrinksAboutIt() {
        val finger = Offset(220f, 500f)
        // 100 along, it has come 100 / (0.9 × 440) of its way, 1 - 0.24 of that its size.
        val scale = 1f - 0.24f * 100f / 396f
        assertEquals(scale, ZoomGeometry.swipeScale(100f, 440f), 0.0001f)
        val right = ZoomGeometry.swipeFrame(window, finger, Offset(100f, 0f), side = 1, density = 1f)
        assertEquals(440f * scale, right.width, 0.01f)
        assertEquals(956f * scale, right.height, 0.01f)
        // The point under the finger stays under it.
        assertEquals(320f, right.left + (finger.x - window.left) * scale, 0.01f)
        assertEquals(500f, right.top + (finger.y - window.top) * scale, 0.01f)
        // To the left, the same the other way.
        val left = ZoomGeometry.swipeFrame(window, finger, Offset(-100f, 0f), side = -1, density = 1f)
        assertEquals(440f - right.right, left.left, 0.01f)
        assertEquals(right.top, left.top, 0.01f)
        // Never back past where the finger took it.
        assertRect(window, ZoomGeometry.swipeFrame(window, finger, Offset(-50f, 30f), side = 1, density = 1f))
        // All its way, 0.9 of the width along, it is 76 % of its size, and no smaller further on.
        assertEquals(0.76f, ZoomGeometry.swipeScale(396f, 440f), 0.0001f)
        assertEquals(0.76f, ZoomGeometry.swipeScale(1_000f, 440f), 0.0001f)
    }

    @Test
    fun aPageCarriedSidewaysFollowsTheFingerUpAndDownOnceUnderWay() {
        val finger = Offset(220f, 500f)
        fun moved(along: Float, down: Float, density: Float = 1f): Float {
            val frame = ZoomGeometry.swipeFrame(window, finger, Offset(along, down), side = 1, density = density)
            return frame.top + (finger.y - window.top) * ZoomGeometry.swipeScale(along, 440f) - finger.y
        }
        // 0.7 of the finger's way once 90 dp along, as much less before.
        assertEquals(0f, moved(0f, 40f), 0.01f)
        assertEquals(14f, moved(45f, 40f), 0.01f)
        assertEquals(28f, moved(90f, 40f), 0.01f)
        assertEquals(-28f, moved(200f, -40f), 0.01f)
        // At three pixels to the dp, 90 dp is 270 pixels.
        assertEquals(14f, moved(135f, 40f, density = 3f), 0.01f)
    }

    @Test
    fun aPageCarriedSidewaysRoundsItsCornersAndLiftsTheDim() {
        assertEquals(0f, ZoomGeometry.swipeRadius(0f, 1f), 0f)
        assertEquals(20f, ZoomGeometry.swipeRadius(50f, 1f), 0.0001f)
        assertEquals(36f, ZoomGeometry.swipeRadius(90f, 1f), 0.0001f)
        assertEquals(36f, ZoomGeometry.swipeRadius(400f, 1f), 0.0001f)
        assertEquals(108f, ZoomGeometry.swipeRadius(400f, 3f), 0.0001f)
        assertEquals(1f, ZoomGeometry.swipeLight(0f), 0f)
        assertEquals(0.725f, ZoomGeometry.swipeLight(0.5f), 0.0001f)
        assertEquals(0.45f, ZoomGeometry.swipeLight(1f), 0.0001f)
        assertEquals(0.5f, ZoomGeometry.swipeShare(198f, 440f), 0.0001f)
    }

    @Test
    fun aPageCarriedSidewaysClosesFarEnoughOrFlicked() {
        // 120 dp in a window 440 wide, whose 0.3 is 132.
        assertFalse(ZoomGeometry.swipeDismisses(119f, 0f, 440f, 1f))
        assertTrue(ZoomGeometry.swipeDismisses(121f, 0f, 440f, 1f))
        // Unless it is coming back faster than 150 a second.
        assertTrue(ZoomGeometry.swipeDismisses(200f, -149f, 440f, 1f))
        assertFalse(ZoomGeometry.swipeDismisses(200f, -151f, 440f, 1f))
        // 0.3 of a window 390 wide, 117, is less than 120.
        assertTrue(ZoomGeometry.swipeDismisses(118f, 0f, 390f, 1f))
        // Flicked on faster than 550 a second, once 18 along.
        assertTrue(ZoomGeometry.swipeDismisses(19f, 551f, 440f, 1f))
        assertFalse(ZoomGeometry.swipeDismisses(19f, 549f, 440f, 1f))
        assertFalse(ZoomGeometry.swipeDismisses(18f, 2_000f, 440f, 1f))
        // In dp: at two pixels to the dp, 0.3 of the width (234 of 780) and 1100 pixels a second.
        assertFalse(ZoomGeometry.swipeDismisses(233f, 0f, 780f, 2f))
        assertTrue(ZoomGeometry.swipeDismisses(235f, 0f, 780f, 2f))
        assertFalse(ZoomGeometry.swipeDismisses(60f, 1_000f, 780f, 2f))
        assertTrue(ZoomGeometry.swipeDismisses(60f, 1_200f, 780f, 2f))
    }

    @Test
    fun aPageLetGoSidewaysKeepsTheFingersSpeedAcrossOnItsWayBack() {
        // Carried off to the right, its middle 110 right of its card's.
        val held = Rect(140f, 60f, 520f, 885f)
        // The finger going back toward the card: the flight takes its speed, and nothing is left to carry on.
        val back = ZoomGeometry.closingVelocity(card, held, -550f, across = true)
        assertEquals(-5f, back, 0.001f)
        assertEquals(0f, ZoomGeometry.closingCarry(card, held, -550f, back, 1f), 0.01f)
        // Going on away from it: the flight starts from rest, and the page carries on with all the finger's speed.
        val away = ZoomGeometry.closingVelocity(card, held, 600f, across = true)
        assertEquals(0f, away, 0f)
        assertEquals(600f, ZoomGeometry.closingCarry(card, held, 600f, away, 1f), 0.01f)
        // Faster toward it than 12 a second takes it: the rest is carried, up to 3000 dp a second.
        val fast = ZoomGeometry.closingVelocity(card, held, -2_000f, across = true)
        assertEquals(-12f, fast, 0f)
        assertEquals(-680f, ZoomGeometry.closingCarry(card, held, -2_000f, fast, 1f), 0.01f)
        assertEquals(9_000f, ZoomGeometry.closingCarry(card, held, 20_000f, 0f, 3f), 0.01f)
        // Down, the top keeps its way as before.
        assertEquals(-5f, ZoomGeometry.closingVelocity(card, Rect(40f, 300f, 400f, 1082f), -600f), 0.001f)
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
