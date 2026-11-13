package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

/** Where the welcome picture's route and Pip stand, and how tall it grows, from what its words take. */
class FirstOpenHeroLayoutTest {
    /** At the usual sizes (a masthead 44 tall, a foot of 106) nothing moves from the fixed insets, on any screen. */
    @Test
    fun atTheUsualSizesNothingMoves() {
        val usual = FirstOpenHeroLayout(top = 62.dp, masthead = 44.dp, foot = 106.dp)
        assertEquals(174.dp, usual.routeTop)
        assertEquals(128.dp, usual.routeBottom)
        assertEquals(118.dp, usual.pipRoom)
        assertEquals(446.dp, usual.height(446.dp))
        // A small phone's 300, where the route has less than its usual room.
        assertEquals(300.dp, usual.height(300.dp))
        assertEquals(300.dp, usual.copy(top = 24.dp).height(300.dp))
    }

    /** The words at their tallest push the route down and up; on a short screen it keeps 144. */
    @Test
    fun largeWordsGrowThePictureAndKeepTheRouteClearOfThem() {
        val large = FirstOpenHeroLayout(top = 24.dp, masthead = 50.dp, foot = 190.dp)
        // 16 under the masthead and the alert's room, 10 over the foot, Pip on it.
        assertEquals(24.dp + 10.dp + 50.dp + 12.dp + 26.dp + 16.dp, large.routeTop)
        assertEquals(200.dp, large.routeBottom)
        assertEquals(190.dp, large.pipRoom)
        val height = large.height(300.dp)
        assertEquals(144.dp, height - large.routeTop - large.routeBottom)
        assertEquals(482.dp, height)
    }

    /** On a taller screen the route keeps the room it has there at the usual size. */
    @Test
    fun aTallerScreenKeepsTheRoutesUsualRoom() {
        val large = FirstOpenHeroLayout(top = 24.dp, masthead = 50.dp, foot = 190.dp)
        val height = large.height(500.dp)
        assertEquals(500.dp - 24.dp - 112.dp - 128.dp, height - large.routeTop - large.routeBottom)
    }

    /** The foot alone outgrowing its inset is enough for the picture to grow. */
    @Test
    fun aTallFootAloneGrowsThePicture() {
        val tall = FirstOpenHeroLayout(top = 62.dp, masthead = 44.dp, foot = 150.dp)
        assertEquals(174.dp + 144.dp + 160.dp, tall.height(300.dp))
    }
}
