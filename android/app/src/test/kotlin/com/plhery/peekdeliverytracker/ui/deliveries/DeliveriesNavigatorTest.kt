package com.plhery.peekdeliverytracker.ui.deliveries

import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Test

/** The navigation as the window turns: for a second after turning stacked, the stack can't pop the parcel. */
class DeliveriesNavigatorTest {
    private var now = 10_000L
    private val navigator = DeliveriesNavigator(clock = { now })
    private val parcel = UUID.fromString("00000000-0000-4000-8000-000000000001")
    private val views = mutableListOf<String>()

    init {
        navigator.view = { views += it }
    }

    @Test
    fun aPopInTheSecondAfterTurningStackedKeepsTheParcel() {
        navigator.turn(DeliveriesArrangement.SideBySide, topCard = null)
        navigator.open(parcel)
        navigator.turn(DeliveriesArrangement.Stacked, topCard = null)

        now += 999
        navigator.pop(emptyList())
        assertEquals(listOf(parcel), navigator.navigation.path)

        now += 1
        navigator.pop(emptyList())
        assertEquals(emptyList<UUID>(), navigator.navigation.path)
        assertEquals(listOf("parcel", "deliveries"), views)
    }

    @Test
    fun theFirstArrangementIsNoTurn() {
        navigator.turn(DeliveriesArrangement.Stacked, topCard = parcel)
        assertEquals(null, navigator.keptCard)
        navigator.open(parcel)
        navigator.pop(emptyList())
        assertEquals(emptyList<UUID>(), navigator.navigation.path)
    }

    @Test
    fun turningKeepsTheCardAtTheTopOfTheList() {
        navigator.turn(DeliveriesArrangement.Stacked, topCard = null)
        navigator.turn(DeliveriesArrangement.SideBySide, topCard = parcel)
        assertEquals(parcel, navigator.keptCard)
    }
}
