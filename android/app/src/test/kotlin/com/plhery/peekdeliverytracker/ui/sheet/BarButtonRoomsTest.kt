package com.plhery.peekdeliverytracker.ui.sheet

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** How a form's bar shares itself between its confirmation and its cancellation. */
class BarButtonRoomsTest {
    @Test
    fun bothKeepTheirWidthWhereTheyFit() = assertEquals(120 to 80, barButtonRooms(300, 120, 80))

    @Test
    fun aCancellationNarrowerThanItsHalfKeepsItsWidthAndTheConfirmationTakesTheRest() =
        assertEquals(220 to 80, barButtonRooms(300, 260, 80))

    @Test
    fun aConfirmationNarrowerThanItsHalfKeepsItsWidthAndTheCancellationTakesTheRest() =
        assertEquals(100 to 200, barButtonRooms(300, 100, 240))

    @Test
    fun twoWiderThanTheirHalvesTakeHalfEach() = assertEquals(150 to 150, barButtonRooms(300, 200, 180))

    @Test
    fun tooWideTogetherTheyFillTheBarEachWithAtLeastItsHalfOrItsWidth() {
        for (confirmation in 0..400 step 7) for (cancellation in 0..400 step 11) {
            val (a, b) = barButtonRooms(301, confirmation, cancellation)
            val case = "$confirmation, $cancellation -> $a, $b"
            if (confirmation + cancellation <= 301) {
                assertEquals(case, confirmation to cancellation, a to b)
            } else {
                assertEquals(case, 301, a + b)
                assertTrue(case, a >= minOf(confirmation, 150) && b >= minOf(cancellation, 150))
            }
        }
    }
}
