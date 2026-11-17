package com.plhery.peekdeliverytracker.core

import java.time.Instant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * AccountInvitationTests.swift: the guest's invitation to an account under their parcels, shown on every visit, closable
 * from the sixth, away 30 days once closed, then back for one visit.
 */
class AccountInvitationTests {
    private class Clock(var now: Instant = Instant.parse("2026-09-21T14:13:20Z")) {
        fun move(seconds: Double) {
            now = now.plusMillis(Math.round(seconds * 1_000))
        }
    }

    private val visit = AccountInvitationMemory.visit
    private val away = AccountInvitationMemory.away

    /** The row seen on `count` visits, each starting as the one before ends. */
    private fun visits(count: Int, memory: AccountInvitationMemory, clock: Clock) {
        repeat(count) {
            memory.seen()
            clock.move(visit)
        }
    }

    @Test
    fun theRowCanBeClosedFromItsSixthVisit() {
        val clock = Clock()
        val memory = AccountInvitationMemory(MemoryDefaults()) { clock.now }
        assertTrue(memory.shows)
        assertFalse(memory.closable)

        visits(5, memory, clock)
        assertTrue(memory.shows)
        assertFalse("Five visits show it without its ×", memory.closable)

        memory.seen()
        assertTrue("The sixth has it", memory.closable)
        clock.move(3 * visit)
        memory.seen()
        assertTrue("And every one after", memory.closable)
        assertTrue(memory.shows)
    }

    @Test
    fun aVisitIsCountedOnceEvery30Minutes() {
        val clock = Clock()
        val memory = AccountInvitationMemory(MemoryDefaults()) { clock.now }
        visits(4, memory, clock)

        // The fifth visit: the row comes on screen again and again within it.
        memory.seen()
        clock.move(10 * 60.0)
        memory.seen()
        clock.move(visit - 10 * 60 - 1)
        memory.seen()
        assertFalse("Still five visits", memory.closable)

        clock.move(1.0)
        memory.seen()
        assertTrue(memory.closable)
    }

    @Test
    fun aClockSetBackStartsAVisit() {
        val clock = Clock()
        val memory = AccountInvitationMemory(MemoryDefaults()) { clock.now }
        visits(5, memory, clock)
        clock.move(-3 * 3_600.0)

        memory.seen()

        assertTrue(memory.closable)
        // The visit is counted from the time the clock gives now.
        val fresh = AccountInvitationMemory(MemoryDefaults()) { clock.now }
        visits(4, fresh, clock)
        clock.move(-3 * 3_600.0)
        fresh.seen()
        clock.move(10 * 60.0)
        fresh.seen()
        assertFalse("Ten minutes later is the same visit", fresh.closable)
    }

    @Test
    fun closedItStaysAway30DaysThenComesBackForOneVisit() {
        val clock = Clock()
        val memory = AccountInvitationMemory(MemoryDefaults()) { clock.now }
        visits(6, memory, clock)

        memory.close()

        assertFalse(memory.shows)
        clock.move(away - 1)
        assertFalse(memory.shows)
        clock.move(1.0)
        assertTrue("Back after 30 days", memory.shows)
        assertTrue("With its ×", memory.closable)

        // It stays for the visit it comes back for, closed or not, and then is away again.
        memory.seen()
        clock.move(visit - 1)
        assertTrue(memory.shows)
        memory.seen()
        clock.move(1.0)
        assertFalse(memory.shows)
        clock.move(away - visit - 1)
        assertFalse(memory.shows)
        clock.move(1.0)
        assertTrue("Back again 30 days after it came back", memory.shows)

        // Closed on the visit it came back for, it is away at once.
        memory.seen()
        memory.close()
        assertFalse(memory.shows)
        clock.move(away)
        assertTrue(memory.shows)
    }

    @Test
    fun theRecordIsTheDevicesOwnAndOutlastsTheApp() {
        val clock = Clock()
        val defaults = MemoryDefaults()
        val memory = AccountInvitationMemory(defaults) { clock.now }
        visits(6, memory, clock)
        memory.close()

        val again = AccountInvitationMemory(defaults) { clock.now }
        assertFalse("Another launch reads the same record", again.shows)
        assertTrue(again.closable)
        assertEquals(setOf("sdt.accountInvitation.v1"), defaults.values.keys)
    }

    @Test
    fun aRecordThatCannotBeReadIsNone() {
        val clock = Clock()
        val defaults = MemoryDefaults(mutableMapOf("sdt.accountInvitation.v1" to "{\"hiddenUntil\":".toByteArray()))
        val memory = AccountInvitationMemory(defaults) { clock.now }
        assertTrue(memory.shows)
        assertFalse(memory.closable)

        memory.seen()
        assertTrue(memory.shows)
    }
}
