package com.plhery.peekdeliverytracker.core

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.PI

/**
 * PullToRefreshTests.swift: the pull measured from the resting inset while the system holds its gap, the result shown
 * only at the end, the seal hidden until the content is back at rest, and a refresh started while settling. Then what
 * the indicator and the seal show, how they move (SwiftUI's own values, from a swiftc harness on the Mac), and the
 * trigger Android's list plays for UIRefreshControl.
 */
class PullToRefreshTests {
    @Test
    fun pullIsMeasuredFromTheRestingInsetWhileTheSystemHoldsItsGap() = runTest {
        val model = PullToRefreshModel(this)
        model.track(PullGeometry(overscroll = 0.0, inset = 164.0, height = 874.0))
        assertEquals(174.8, model.threshold, 0.01)
        model.track(PullGeometry(overscroll = 87.4, inset = 164.0, height = 874.0))
        assertEquals(0.5, model.progress, 0.001)

        // The system starts refreshing mid-pull and adds its gap to the inset,
        // sometimes before the refresh action has run.
        model.track(PullGeometry(overscroll = 118.7, inset = 224.0, height = 874.0))
        assertEquals(178.7, model.distance, 0.01)
        model.begin(label = "Checking for updates…")
        model.track(PullGeometry(overscroll = 110.0, inset = 224.0, height = 874.0))
        assertEquals(170.0, model.distance, 0.01)
        assertEquals(60.0, model.holdDistance, 0.0)
        assertEquals(1.0, model.progress, 0.0)

        // After the finger lifts, the gap stays open at its held height.
        model.track(PullGeometry(overscroll = 0.0, inset = 224.0, height = 874.0))
        assertEquals(60.0, model.distance, 0.0)
    }

    @Test
    fun resultAppearsOnlyAtTheEndAndClearsOnceSettled() = runTest {
        val model = PullToRefreshModel(this)
        model.begin(label = "Checking for updates…")
        assertEquals(PullToRefreshModel.Phase.Refreshing, model.phase)
        assertEquals(1, model.starts)
        assertNull(model.result)

        model.update(label = "Still checking. Updates will appear here.")
        assertEquals("Still checking. Updates will appear here.", model.label)
        assertNull(model.result)

        model.finish(succeeded = true, label = "Tracking updated")
        assertEquals(PullToRefreshModel.Result.Succeeded, model.result)
        model.update(label = "Still checking. Updates will appear here.")
        assertEquals("Tracking updated", model.label)

        model.settle()
        assertEquals(PullToRefreshModel.Phase.Settling, model.phase)
        assertEquals("the result fades out with the seal", PullToRefreshModel.Result.Succeeded, model.result)
        model.settleJob?.join()
        assertEquals(PullToRefreshModel.Phase.Idle, model.phase)
        assertNull(model.result)
        assertNull(model.label)
        assertEquals(0.0, model.distance, 0.0)
    }

    @Test
    fun sealStaysHiddenUntilTheContentReturnsToTheTopAfterARefresh() = runTest {
        val model = PullToRefreshModel(this)
        model.track(PullGeometry(overscroll = 0.0, inset = 164.0, height = 874.0))
        model.track(PullGeometry(overscroll = 180.0, inset = 164.0, height = 874.0))
        model.begin(label = "Checking for updates…")
        model.finish(succeeded = true, label = "Tracking updated")
        model.settle()
        // The finger still holds the content down when the refresh ends.
        model.track(PullGeometry(overscroll = 150.0, inset = 164.0, height = 874.0))
        model.settleJob?.join()
        assertEquals(PullToRefreshModel.Phase.Idle, model.phase)
        assertTrue(model.awaitingRest)
        model.track(PullGeometry(overscroll = 90.0, inset = 164.0, height = 874.0))
        assertTrue(model.awaitingRest)
        model.track(PullGeometry(overscroll = 0.0, inset = 164.0, height = 874.0))
        assertFalse(model.awaitingRest)
        model.track(PullGeometry(overscroll = 40.0, inset = 164.0, height = 874.0))
        assertEquals(40.0, model.distance, 0.0)
        assertFalse(model.awaitingRest)
    }

    @Test
    fun refreshStartedWhileSettlingIsNotResetByTheEarlierOne() = runTest {
        val model = PullToRefreshModel(this)
        model.begin(label = "Checking for updates…")
        model.finish(succeeded = false, label = "Couldn’t check. Try again.")
        assertEquals(PullToRefreshModel.Result.Failed, model.result)
        model.settle()
        val earlier = model.settleJob
        assertNotNull(earlier)
        model.begin(label = "Checking for updates…")
        assertNull(model.result)
        earlier!!.join()
        assertEquals(PullToRefreshModel.Phase.Refreshing, model.phase)
        assertEquals("Checking for updates…", model.label)
        assertEquals(2, model.starts)
    }

    @Test
    fun indicatorShowsThePullThenTheRefreshAndHidesWhileSettling() = runTest {
        val model = PullToRefreshModel(this)
        fun indicator(reduceMotion: Boolean = false) = PullToRefreshIndicator.of(model, "Pull to refresh", reduceMotion)
        model.track(PullGeometry(overscroll = 0.0, inset = 164.0, height = 874.0))
        indicator().let {
            assertEquals(0.0, it.opacity, 0.0)
            assertEquals(-21.0, it.offset, 0.0)
            assertEquals(0.82, it.sealScale, 0.0)
            assertEquals("Pull to refresh", it.label)
            assertEquals(Brand.paper, it.seal.disc)
            assertEquals(PullToRefreshPalette.inkSoft, it.seal.arrowColor)
            assertFalse(it.seal.spinning)
        }

        // Halfway to the threshold the seal is mostly shown, its arrow a quarter and a half turned.
        model.track(PullGeometry(overscroll = 87.4, inset = 164.0, height = 874.0))
        indicator().let {
            assertEquals(0.85, it.opacity, 1e-15)
            assertEquals(-0.55 * 87.4 - 21, it.offset, 0.0)
            assertEquals(0.91, it.sealScale, 1e-15)
            assertEquals(135.0, it.seal.arrowDegrees, 1e-12)
        }
        indicator(reduceMotion = true).let {
            assertEquals(1.0, it.sealScale, 0.0)
            assertEquals(0.0, it.seal.arrowDegrees, 0.0)
            assertFalse(it.seal.pops)
        }

        model.begin(label = "Checking for updates…")
        model.track(PullGeometry(overscroll = 0.0, inset = 224.0, height = 874.0))
        indicator().let {
            assertEquals(1.0, it.opacity, 0.0)
            assertEquals(60.0, it.gap, 0.0)
            assertEquals("Checking for updates…", it.label)
            assertEquals(Brand.ink, it.seal.arrowColor)
            assertTrue(it.seal.spinning)
            assertEquals(0.65, it.seal.glyphScale, 0.0)
            assertEquals(0.0, it.seal.glyphTrim, 0.0)
        }
        assertFalse("no spin with Reduce Motion", indicator(reduceMotion = true).seal.spinning)

        model.finish(succeeded = false, label = "Couldn’t check. Try again.")
        indicator().seal.let {
            assertEquals(ExperimentalPalette.pickupSurface, it.disc)
            assertTrue(it.cross)
            assertEquals(Brand.warning, it.glyphColor)
            assertEquals(1.0, it.glyphTrim, 0.0)
            assertEquals(1.0, it.glyphOpacity, 0.0)
            assertEquals(1.0, it.glyphScale, 0.0)
            assertEquals(0.0, it.arrowOpacity, 0.0)
        }

        model.settle()
        assertEquals(0.0, indicator().opacity, 0.0)
        model.settleJob?.join()
        assertEquals(PullToRefreshModel.Phase.Idle, model.phase)
        assertEquals(Brand.paper, indicator().seal.disc)
    }

    @Test
    fun sealMovesAsSwiftUIMovesIt() {
        // The pop's keyframes, from SwiftUI's KeyframeTimeline: its easing is solved in Float, its spring exactly.
        assertEquals(1.0, PullToRefreshSeal.pop(0.0), 0.0)
        assertEquals(1.0293009376525881, PullToRefreshSeal.pop(0.04), 1e-6)
        assertEquals(1.06, PullToRefreshSeal.pop(0.12), 1e-15)
        assertEquals(1.0505931074807369, PullToRefreshSeal.pop(0.16), 1e-15)
        assertEquals(1.0120328206835014, PullToRefreshSeal.pop(0.24), 1e-15)
        assertEquals(0.9903123502925298, PullToRefreshSeal.pop(0.36), 1e-15)
        assertEquals(1.000610672259116, PullToRefreshSeal.pop(0.52), 1e-15)
        assertEquals(1.000610672259116, PullToRefreshSeal.pop(0.68), 1e-15)

        // SwiftUI's Spring(duration: 0.3, bounce: 0.2) and Spring(duration: 0.4, bounce: 0.5).
        val glyph = Spring(duration = 0.3, bounce = 0.2)
        assertEquals(438.64908449286037, glyph.stiffness, 1e-12)
        assertEquals(33.51032163829113, glyph.damping, 1e-12)
        assertEquals(0.8, glyph.dampingRatio, 1e-12)
        assertEquals(246.74011002723392, Spring(duration = 0.4, bounce = 0.5).stiffness, 1e-12)
        assertEquals(15.707963267948966, Spring(duration = 0.4, bounce = 0.5).damping, 1e-12)
        val curve = PullToRefreshSeal.glyphAnimation
        for ((time, value) in listOf(
            0.0 to 0.0, 0.05 to 0.3108581140887894, 0.1 to 0.7047501156032632, 0.2 to 1.0008868349704654,
            0.3 to 1.0104505747325803, 0.5 to 0.9997700343043638,
        )) {
            assertEquals("spring at $time", value, curve.value(time), 1e-15)
        }
        assertEquals(0.4451866149902344, UnitCurve.easeOut.value(0.3), 1e-6)

        // The arrow eases out of its pulled angle for a whole turn, then turns once every 0.85 s.
        val start = PullToRefreshArrow.radians(270.0)
        assertEquals(3 * PI / 2, start, 1e-15)
        assertEquals(start, PullToRefreshArrow.spin(start, 0.0), 0.0)
        assertEquals(start + 2 * PI, PullToRefreshArrow.spin(start, 0.85), 1e-12)
        assertEquals(start + 3 * PI, PullToRefreshArrow.spin(start, 1.275), 1e-12)
        var last = start
        for (step in 1..85) {
            val angle = PullToRefreshArrow.spin(start, step / 100.0)
            assertTrue("turns forward at ${step / 100.0}", angle > last)
            last = angle
        }

        // The check, from the web app's 24-unit icon, in the seal's 15-point square.
        val points = mutableListOf<Point>()
        PullToRefreshGlyph.path(Rect(0.0, 0.0, 15.0, 15.0), cross = false).replay(object : MapPath.Sink {
            override fun move(x: Double, y: Double) { points.add(Point(x, y)) }
            override fun line(x: Double, y: Double) { points.add(Point(x, y)) }
            override fun quadCurve(x: Double, y: Double, controlX: Double, controlY: Double) = error("curve")
            override fun relativeArc(centerX: Double, centerY: Double, radius: Double, startAngle: Double, delta: Double) = error("arc")
            override fun ellipse(x: Double, y: Double, width: Double, height: Double) = error("ellipse")
            override fun curve(x: Double, y: Double, control1X: Double, control1Y: Double, control2X: Double, control2Y: Double) = error("curve")
            override fun close() = error("close")
        })
        assertEquals(listOf(Point(3.125, 7.5), Point(5.625, 10.0), Point(11.875, 3.75)), points)
    }

    // Not an iPhone test: the glyph's spring as SwiftUI applies it (a swiftc harness on the Mac). The opacity stops at 1
    // while the spring overshoots, as SwiftUI keeps an opacity within 0 and 1; the scale does not stop.
    @Test
    fun theGlyphFadesInAndGrowsOnItsSpring() {
        assertEquals(0.0, PullToRefreshSeal.glyphOpacity(0.0), 0.0)
        assertEquals(0.3108581140887894, PullToRefreshSeal.glyphOpacity(0.05), 1e-15)
        assertEquals(1.0, PullToRefreshSeal.glyphOpacity(0.2), 0.0)
        assertEquals(1.0, PullToRefreshSeal.glyphOpacity(0.3), 0.0)
        assertEquals(0.9997700343043638, PullToRefreshSeal.glyphOpacity(0.5), 1e-15)
        assertEquals(1.0, PullToRefreshSeal.glyphOpacity(Double.POSITIVE_INFINITY), 0.0)
        assertEquals(0.65, PullToRefreshSeal.glyphScale(0.0, reduceMotion = false), 0.0)
        assertEquals(0.65 + 0.35 * 0.7047501156032632, PullToRefreshSeal.glyphScale(0.1, reduceMotion = false), 1e-15)
        assertEquals(0.65 + 0.35 * 1.0104505747325803, PullToRefreshSeal.glyphScale(0.3, reduceMotion = false), 1e-15)
        assertTrue(PullToRefreshSeal.glyphScale(0.3, reduceMotion = false) > 1)
        assertEquals(1.0, PullToRefreshSeal.glyphScale(Double.POSITIVE_INFINITY, reduceMotion = false), 0.0)
        assertEquals(1.0, PullToRefreshSeal.glyphScale(0.0, reduceMotion = true), 0.0)
        // A spring that has run for ever has arrived, where SwiftUI's own formula gives NaN.
        assertEquals(1.0, PullToRefreshSeal.glyphAnimation.value(Double.POSITIVE_INFINITY), 0.0)
        assertEquals(-0.06, Spring(duration = 0.4, bounce = 0.5).value(-0.06, time = Double.POSITIVE_INFINITY), 0.0)
        assertEquals(0.0, Spring().value(1.0, time = Double.NaN), 0.0)
    }

    // Not an iPhone test: the label shrinks to fit the 12 points under the seal, as `minimumScaleFactor(0.8)` does
    // (measured with SwiftUI's own layout on the Mac), at caption 2's line height for 11, 13 and 15 points.
    @Test
    fun theLabelShrinksToFitUnderTheSealButNoFurtherThan80Percent() {
        assertEquals(12.0, PullToRefreshIndicator.LABEL_HEIGHT, 0.0)
        assertEquals(1.0, PullToRefreshIndicator.labelScale(lineHeight = 11.0), 0.0)
        assertEquals(1.0, PullToRefreshIndicator.labelScale(lineHeight = 12.0), 0.0)
        assertEquals(12 / 13.13, PullToRefreshIndicator.labelScale(lineHeight = 13.13), 1e-15)
        assertEquals(0.8, PullToRefreshIndicator.labelScale(lineHeight = 15.52), 0.0)
        assertEquals(0.8, PullToRefreshIndicator.labelScale(lineHeight = 17.91), 0.0)
        // A line too wide for the band shrinks too, as far as the same floor, then is cut.
        assertEquals(0.9, PullToRefreshIndicator.labelScale(lineHeight = 12.0, textWidth = 200.0, availableWidth = 180.0), 1e-15)
        assertEquals(0.8, PullToRefreshIndicator.labelScale(lineHeight = 12.0, textWidth = 400.0, availableWidth = 180.0), 0.0)
        assertEquals(12 / 13.13, PullToRefreshIndicator.labelScale(lineHeight = 13.13, textWidth = 100.0, availableWidth = 180.0), 1e-15)
        assertEquals(11.0, PullToRefreshIndicator.LABEL_FONT_SIZE, 0.0)
        assertEquals(15.0, PullToRefreshIndicator.LABEL_MAXIMUM_FONT_SIZE, 0.0)
    }

    // Not an iPhone test: UIScrollView comes back to exactly 0, Android's list to within a sliver of a pixel. Under half
    // a pixel is at rest, so the next pull can start a refresh.
    @Test
    fun anOverscrollUnderHalfAPixelIsAtRest() = runTest {
        assertEquals(PullGeometry(0.0, 164.0, 874.0), PullGeometry.of(-164.000001, 164.0, 874.0, displayScale = 3.0))
        assertEquals(PullGeometry(0.0, 164.0, 874.0), PullGeometry.of(-164.16, 164.0, 874.0, displayScale = 3.0))
        assertEquals(0.17, PullGeometry.of(-164.17, 164.0, 874.0, displayScale = 3.0).overscroll, 1e-9)
        assertEquals(0.25, PullGeometry.of(-164.25, 164.0, 874.0, displayScale = 2.0).overscroll, 1e-9)
        assertEquals(0.0, PullGeometry.of(-164.24, 164.0, 874.0, displayScale = 2.0).overscroll, 0.0)
        assertEquals(PullGeometry(0.0, 164.0, 874.0), PullGeometry.of(-100.0, 164.0, 874.0, displayScale = 3.0))

        val model = PullToRefreshModel(this)
        var trigger = PullToRefreshTrigger()
        fun pull(offset: Double, dragging: Boolean): Boolean {
            model.track(PullGeometry.of(offset, 164.0, 874.0, displayScale = 2.625))
            val next = trigger.next(model.distance, dragging, model)
            trigger = next.trigger
            if (next.starts) model.begin(label = "Checking for updates…")
            return next.starts
        }
        assertFalse(pull(-164.0, dragging = true))
        assertTrue(pull(-340.0, dragging = true))
        model.finish(succeeded = true, label = "Tracking updated")
        model.settle()
        model.settleJob?.join()
        assertTrue(model.awaitingRest)
        assertFalse(pull(-164.000001, dragging = false))
        assertFalse(model.awaitingRest)
        assertEquals(0.0, model.distance, 0.0)
        assertTrue("back at rest, a pull starts the next one", pull(-340.0, dragging = true))
        assertEquals(2, model.starts)
    }

    @Test
    fun aRefreshStartsOnceAHeldPullReachesTheThresholdAndAgainOnlyAfterRest() = runTest {
        val model = PullToRefreshModel(this)
        var trigger = PullToRefreshTrigger()
        fun pull(overscroll: Double, dragging: Boolean): Boolean {
            model.track(PullGeometry(overscroll = overscroll, inset = 164.0, height = 874.0))
            val next = trigger.next(model.distance, dragging, model)
            trigger = next.trigger
            if (next.starts) model.begin(label = "Checking for updates…")
            return next.starts
        }
        assertFalse(pull(0.0, dragging = true))
        assertFalse(pull(100.0, dragging = true))
        assertFalse("a pull the finger has let go of", pull(180.0, dragging = false))
        assertTrue(pull(175.0, dragging = true))
        assertFalse("already refreshing", pull(190.0, dragging = true))
        model.finish(succeeded = true, label = "Tracking updated")
        model.settle()
        assertFalse("the finger still holds the content", pull(180.0, dragging = true))
        model.settleJob?.join()
        assertFalse(pull(180.0, dragging = true))
        assertFalse(pull(0.0, dragging = false))
        assertTrue("back at rest, a pull starts the next one", pull(176.0, dragging = true))
        assertEquals(2, model.starts)
    }
}
