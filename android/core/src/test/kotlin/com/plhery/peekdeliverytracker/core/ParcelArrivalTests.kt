package com.plhery.peekdeliverytracker.core

import org.junit.Assert.assertEquals
import org.junit.Test

/** AddParcelView's arrival of a parcel just added: its card's wobble, and the six little parcels out of its stamp. */
class ParcelArrivalTests {
    @Test
    fun theCardSwingsThreeTimesEachWayDyingAwayAndSwellsOnce() {
        // A twelfth of the way: the first swing's height, 3 × 11/12 points and 1.1 × 11/12 degrees.
        assertEquals(2.75, ParcelArrival.offsetX(1.0 / 12, reduced = false), 1e-9)
        assertEquals(1.1 * 11 / 12, ParcelArrival.rotation(1.0 / 12, reduced = false), 1e-9)
        assertEquals(1.0031058285412302, ParcelArrival.scale(1.0 / 12, reduced = false), 1e-12)
        // A quarter: back the other way, three quarters as far.
        assertEquals(-2.25, ParcelArrival.offsetX(0.25, reduced = false), 1e-9)
        assertEquals(-0.825, ParcelArrival.rotation(0.25, reduced = false), 1e-9)
        assertEquals(1.0084852813742387, ParcelArrival.scale(0.25, reduced = false), 1e-12)
        // Half way, it stands straight at its largest; it ends as it started.
        assertEquals(0.0, ParcelArrival.offsetX(0.5, reduced = false), 1e-9)
        assertEquals(1.012, ParcelArrival.scale(0.5, reduced = false), 1e-12)
        for (progress in listOf(0.0, 1.0)) {
            assertEquals(0.0, ParcelArrival.offsetX(progress, reduced = false), 1e-9)
            assertEquals(0.0, ParcelArrival.rotation(progress, reduced = false), 1e-9)
            assertEquals(1.0, ParcelArrival.scale(progress, reduced = false), 1e-9)
        }
        // Nothing brightens it while it moves.
        assertEquals(0.0, ParcelArrival.brightness(0.5, reduced = false), 0.0)
    }

    @Test
    fun underReduceMotionTheCardOnlyBrightensAndBack() {
        for (progress in listOf(0.1, 0.25, 0.5)) {
            assertEquals(0.0, ParcelArrival.offsetX(progress, reduced = true), 0.0)
            assertEquals(0.0, ParcelArrival.rotation(progress, reduced = true), 0.0)
            assertEquals(1.0, ParcelArrival.scale(progress, reduced = true), 0.0)
        }
        assertEquals(0.024721359549995794, ParcelArrival.brightness(0.1, reduced = true), 1e-12)
        assertEquals(0.08, ParcelArrival.brightness(0.5, reduced = true), 1e-12)
        assertEquals(0.0, ParcelArrival.brightness(1.0, reduced = true), 1e-9)
        assertEquals(0.75, ParcelArrival.REDUCED_WOBBLE_DURATION, 0.0)
        assertEquals(0.48, ParcelArrival.WOBBLE_DURATION, 0.0)
    }

    @Test
    fun theLittleParcelsFlyOutOfTheStampUpAndBackDown() {
        val pose = ParcelArrival.PipPose()
        // 0.3 s in on a card 358 × 120 points, from 30 in from its end: (x, y, turn, scale), all fully shown.
        val expected = listOf(
            doubleArrayOf(281.8, 43.2, -21.527864045000413, 0.9125),
            doubleArrayOf(293.875, 22.5, 30.03185424949238, 0.90625),
            doubleArrayOf(313.234375, 35.78125, -23.858963739909704, 0.921875),
            doubleArrayOf(328.0, 21.356250000000003, 35.508621314832745, 0.915625),
            doubleArrayOf(342.28, 34.8, -8.89154786963877, 0.925),
            doubleArrayOf(356.4925, 41.331250000000004, 42.333247724800245, 0.909375),
        )
        assertEquals(6, ParcelArrival.pips.size)
        for ((index, pip) in ParcelArrival.pips.withIndex()) {
            ParcelArrival.pose(pip, 0.3, 358.0, 120.0, ParcelArrival.STUB_INSET, pose)
            assertEquals(expected[index][0], pose.x, 1e-9)
            assertEquals(expected[index][1], pose.y, 1e-9)
            assertEquals(expected[index][2], pose.rotation, 1e-9)
            assertEquals(expected[index][3], pose.scale, 1e-9)
            assertEquals(1.0, pose.opacity, 1e-9)
        }
        // On a narrow card the spread is a quarter of its width; Next up's stamp is 43 in.
        ParcelArrival.pose(ParcelArrival.pips[1], 0.4, 200.0, 100.0, ParcelArrival.NEXT_STUB_INSET, pose)
        assertEquals(130.75, pose.x, 1e-9)
        assertEquals(7.5, pose.y, 1e-9)
        assertEquals(32.5, pose.rotation, 1e-9)
        assertEquals(0.875, pose.scale, 1e-9)
        assertEquals(30.0, ParcelArrival.pips[1].width, 1e-9)
    }

    @Test
    fun eachLittleParcelWaitsForItsDelayAndFadesAtTheEnd() {
        val pose = ParcelArrival.PipPose()
        // Before its delay it sits unseen in the stamp, at mid-height.
        ParcelArrival.pose(ParcelArrival.pips[0], 0.01, 358.0, 120.0, ParcelArrival.STUB_INSET, pose)
        assertEquals(328.0, pose.x, 1e-9)
        assertEquals(60.0, pose.y, 1e-9)
        assertEquals(0.0, pose.scale, 0.0)
        assertEquals(0.0, pose.opacity, 0.0)
        // Fading in over its first 0.04 s, and out over its last 0.24 s.
        ParcelArrival.pose(ParcelArrival.pips[1], 0.02, 358.0, 120.0, ParcelArrival.STUB_INSET, pose)
        assertEquals(0.5, pose.opacity, 1e-9)
        ParcelArrival.pose(ParcelArrival.pips[1], 0.68, 358.0, 120.0, ParcelArrival.STUB_INSET, pose)
        assertEquals(0.5, pose.opacity, 1e-9)
        // At its end it is gone, a quarter smaller, its own turn done: on a section's Next up, from 49 in.
        ParcelArrival.pose(ParcelArrival.pips[3], 0.83, 358.0, 120.0, ParcelArrival.SECTION_NEXT_STUB_INSET, pose)
        assertEquals(309.0, pose.x, 1e-9)
        assertEquals(25.0, pose.y, 1e-9)
        assertEquals(85.0, pose.rotation, 1e-9)
        assertEquals(0.75, pose.scale, 1e-9)
        assertEquals(0.0, pose.opacity, 1e-9)
        assertEquals(0.95, ParcelArrival.BURST_DURATION, 0.0)
        assertEquals(0.75, ParcelArrival.REDUCED_BURST_DURATION, 0.0)
    }
}
