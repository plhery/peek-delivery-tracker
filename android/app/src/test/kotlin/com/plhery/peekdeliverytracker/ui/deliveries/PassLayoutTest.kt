package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.EdgeInsets
import com.plhery.peekdeliverytracker.ui.theme.PeekKeyframes
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * The page's pure layout against ParcelDetailView's and ParcelMapScreen's sums: the route behind the pass, the bell's
 * pose as it is drawn, and where the map screen's parts stand.
 */
class PassLayoutTest {
    @Test
    fun theRouteIs176TallWith68ForItUnderTheTopRow() {
        val metrics = PassRouteMetrics.of(handover = false, largeText = false, headerHeight = 44.0)
        assertEquals(176.0, metrics.height, 0.0)
        assertEquals(160.0, metrics.floor, 0.0)
        assertEquals(68.0, metrics.spacer, 0.0)
        assertEquals(0.0, metrics.routeRoom, 0.0)
    }

    @Test
    fun aSecondCarrierAdds40AndTheRouteStartsThatMuchLower() {
        val metrics = PassRouteMetrics.of(handover = true, largeText = false, headerHeight = 44.0)
        assertEquals(216.0, metrics.height, 0.0)
        assertEquals(200.0, metrics.floor, 0.0)
        assertEquals(108.0, metrics.spacer, 0.0)
        assertEquals(40.0, metrics.routeRoom, 0.0)
    }

    @Test
    fun atTheLargestTextTheButtonsLineMovesTheMapDownByWhatItAdds() {
        // The marks 60 tall and the buttons' 44 under them, 4 apart: 64 more than the top row's 44.
        val metrics = PassRouteMetrics.of(handover = true, largeText = true, headerHeight = 108.0)
        assertEquals(64.0, metrics.lower, 0.0)
        assertEquals(280.0, metrics.height, 0.0)
        assertEquals(264.0, metrics.floor, 0.0)
        // The room under the top row grows only with the second mark; the route starts lower by both.
        assertEquals(108.0, metrics.spacer, 0.0)
        assertEquals(104.0, metrics.routeRoom, 0.0)
        // Not before the header is measured, nor at the other sizes.
        assertEquals(0.0, PassRouteMetrics.of(handover = false, largeText = true, headerHeight = 0.0).lower, 0.0)
        assertEquals(0.0, PassRouteMetrics.of(handover = false, largeText = false, headerHeight = 108.0).lower, 0.0)
    }

    @Test
    fun theBellIsStillUnderReduceMotionAndHasNoSoundLinesWhileMuted() {
        val pose = PeekKeyframes.BellPose(angle = 12.0, scale = 0.96, waves = 0.5)
        assertEquals(BellDrawing(12f, 0.96f, 0.5f), BellDrawing.of(pose, muted = false, reduceMotion = false))
        assertEquals(BellDrawing(-7f, 0.94f, 0f), BellDrawing.of(PeekKeyframes.BellPose(-7.0, 0.94, 0.3), muted = true, reduceMotion = false))
        assertEquals(BellDrawing(0f, 1f, 0f), BellDrawing.of(pose, muted = false, reduceMotion = true))
        assertEquals(BellDrawing(0f, 1f, 0f), BellDrawing.of(pose, muted = true, reduceMotion = true))
    }

    @Test
    fun onAPhoneTheMapKeepsClearOfTheCloseButtonsRowAndTheSummary() {
        val safe = EdgeInsets(top = 62.0, leading = 0.0, bottom = 34.0, trailing = 0.0)
        val layout = MapCoverLayout.of(440.0, 956.0, safe, barHeight = 180.0, hinge = null)
        // Under the status bar the button keeps 4 below the safe area's top.
        assertEquals(4.0, layout.closeTop, 0.0)
        assertEquals(EdgeInsets(top = 114.0, leading = 0.0, bottom = 238.0, trailing = 0.0), layout.insets)
        assertEquals(430.0, layout.barMaxHeight, 0.0)
        assertNull(layout.barWidth)
    }

    @Test
    fun onItsSideThePhoneKeepsTheButtonOffTheEdgeAndClearOfTheCutout() {
        val safe = EdgeInsets(top = 0.0, leading = 62.0, bottom = 21.0, trailing = 62.0)
        val layout = MapCoverLayout.of(956.0, 440.0, safe, barHeight = 150.0, hinge = null)
        assertEquals(14.0, layout.closeTop, 0.0)
        assertEquals(EdgeInsets(top = 62.0, leading = 62.0, bottom = 195.0, trailing = 62.0), layout.insets)
        assertEquals(209.5, layout.barMaxHeight, 0.0)
    }

    @Test
    fun besideAnUprightHingeTheRouteKeepsToTheFirstPartAndTheSummaryToTheSecond() {
        val safe = EdgeInsets(top = 24.0, leading = 0.0, bottom = 24.0, trailing = 0.0)
        val layout = MapCoverLayout.of(1200.0, 900.0, safe, barHeight = 180.0, hinge = 590.0..610.0)
        assertEquals(EdgeInsets(top = 24.0 + 4 + 48, leading = 0.0, bottom = 48.0, trailing = 634.0), layout.insets)
        assertEquals(590.0, layout.barWidth!!, 0.0)
        assertEquals(426.0, layout.barMaxHeight, 0.0)
    }

    @Test
    fun withALevelHingeTheRouteKeepsAboveItAndTheSummaryUnderIt() {
        val safe = EdgeInsets(top = 24.0, leading = 0.0, bottom = 24.0, trailing = 0.0)
        val layout = MapCoverLayout.of(840.0, 1200.0, safe, barHeight = 180.0, hinge = null, level = 590.0..610.0)
        // The route ends 24 above the hinge, whatever the summary's height; the summary stays centred and, at the
        // accessibility sizes, keeps 24 under the hinge above its 12 off the foot.
        assertEquals(EdgeInsets(top = 24.0 + 4 + 48, leading = 0.0, bottom = 634.0, trailing = 0.0), layout.insets)
        assertEquals(layout.insets, MapCoverLayout.of(840.0, 1200.0, safe, barHeight = 400.0, hinge = null, level = 590.0..610.0).insets)
        assertEquals(542.0, layout.barMaxHeight, 0.0)
        assertNull(layout.barWidth)
    }

    @Test
    fun aWindowSplitBothWaysKeepsToItsUprightHinge() {
        val safe = EdgeInsets(top = 24.0, leading = 0.0, bottom = 24.0, trailing = 0.0)
        assertEquals(
            MapCoverLayout.of(1200.0, 900.0, safe, barHeight = 180.0, hinge = 590.0..610.0),
            MapCoverLayout.of(1200.0, 900.0, safe, barHeight = 180.0, hinge = 590.0..610.0, level = 440.0..460.0),
        )
    }
}
