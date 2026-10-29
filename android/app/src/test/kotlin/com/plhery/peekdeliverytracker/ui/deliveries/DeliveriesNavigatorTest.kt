package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Test

/** The navigation as the window turns: the card at the list's top is kept, and back goes back at once. */
class DeliveriesNavigatorTest {
    private val navigator = DeliveriesNavigator()
    private val parcel = UUID.fromString("00000000-0000-4000-8000-000000000001")
    private val views = mutableListOf<String>()

    init {
        navigator.view = { views += it }
    }

    @Test
    fun aPopRightAfterTurningStackedPops() {
        navigator.turn(DeliveriesArrangement.SideBySide) { null }
        navigator.open(parcel)
        navigator.turn(DeliveriesArrangement.Stacked) { null }

        navigator.pop(emptyList())
        assertEquals(emptyList<UUID>(), navigator.navigation.path)
        assertEquals(listOf("parcel", "deliveries"), views)
    }

    @Test
    fun theFirstArrangementIsNoTurn() {
        navigator.turn(DeliveriesArrangement.Stacked) { parcel }
        assertEquals(null, navigator.keptCard)
        navigator.open(parcel)
        navigator.pop(emptyList())
        assertEquals(emptyList<UUID>(), navigator.navigation.path)
    }

    @Test
    fun turningKeepsTheCardAtTheTopOfTheList() {
        navigator.turn(DeliveriesArrangement.Stacked) { null }
        navigator.turn(DeliveriesArrangement.SideBySide) { parcel }
        assertEquals(parcel, navigator.keptCard)
    }

    @Test
    fun theListsTopIsReadOnlyAsTheWindowTurns() {
        var reads = 0
        val top = { reads++; parcel }
        navigator.turn(DeliveriesArrangement.Stacked, top)
        navigator.turn(DeliveriesArrangement.Stacked, top)
        assertEquals(0, reads)
        navigator.turn(DeliveriesArrangement.SideBySide, top)
        assertEquals(1, reads)
    }
}
