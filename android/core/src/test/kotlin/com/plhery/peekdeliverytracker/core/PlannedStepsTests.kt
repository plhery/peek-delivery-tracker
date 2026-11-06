package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.EventPlace
import com.plhery.peekdeliverytracker.core.api.EventPlacePrecision
import com.plhery.peekdeliverytracker.core.api.PlannedTrackingEvent
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The steps a carrier wrote ahead of the parcel (`plannedEvents`): the demo's, which a refresh drops once its scans
 * overtake them, a link's, and how the journal dates and reads them.
 */
class PlannedStepsTests {
    private val vinyl = "Kind of Blue · vinyl 🎷"

    @Test
    fun theDemosPlannedStepsStayApartFromItsScans() {
        val now = DateParser.date("2026-09-09T12:00:00Z")!!
        val seeded = DemoRepository.seed(now)
        val parcel = seeded.first { it.label == vinyl }
        assertEquals(CarrierId.Ups, parcel.carrier)
        assertEquals(listOf(TrackingStage.Registered, TrackingStage.Accepted, TrackingStage.InTransit), parcel.trackingEvents.map { it.stage })
        val planned = parcel.plannedEvents!!
        assertEquals(
            listOf("Customs cleared", "Arrived at the local delivery centre", "Out for delivery", "Delivered"),
            planned.map { it.description },
        )
        assertEquals(
            listOf(TrackingStage.Customs, TrackingStage.InTransit, TrackingStage.OutForDelivery, TrackingStage.Delivered),
            planned.map { it.stage },
        )
        assertEquals(listOf("Basel, Switzerland", "Zürich, Switzerland", null, null), planned.map { it.location })
        // A negative `hoursAgo` is still to come; the step an hour ago has passed with no scan to confirm it.
        assertEquals(
            listOf(-1L, 10L, 20L, 24L).map { now.plus(Duration.ofHours(it)) },
            planned.map { DateParser.date(it.occurredAt) },
        )
        assertEquals(TrackingStage.InTransit, parcel.currentStage)
        assertFalse(parcel.isDelivered)
        assertNull(parcel.expectedDelivery)
        assertEquals(3, parcel.journalEntries.size)
        assertTrue(seeded.filter { it.label != vinyl }.all { it.plannedEvents == null })
    }

    @Test
    fun theDemoTranslatesItsPlannedStepsAndARefreshDropsTheOnesItsScansOvertake() {
        val defaults = MemoryDefaults()
        var language = AppLanguage.En
        val start = DateParser.date("2026-09-09T12:00:00Z")!!
        var now = start
        val repo = DemoRepository(defaults, language = { language }, now = { now })
        val parcel = repo.list().first { it.label == vinyl }

        language = AppLanguage.Fr
        val french = repo.list().first { it.id == parcel.id }
        assertEquals(
            listOf("Dédouané", "Arrivé au centre de distribution local", "En cours de livraison", "Livré"),
            french.plannedEvents?.map { it.description },
        )
        assertEquals(parcel.plannedEvents?.map { it.occurredAt }, french.plannedEvents?.map { it.occurredAt })
        language = AppLanguage.En
        assertEquals(parcel.plannedEvents, repo.list().first { it.id == parcel.id }.plannedEvents)

        // Out for delivery for real: the steps dated before it go, and so does a later one of an earlier stage.
        now = start.plusSeconds(60)
        val out = repo.refresh(parcel.id)
        assertEquals(TrackingStage.OutForDelivery, out.currentStage)
        assertEquals(listOf("Out for delivery", "Delivered"), out.plannedEvents?.map { it.description })
        // Delivered, nothing is planned any more.
        now = start.plusSeconds(120)
        val delivered = repo.refresh(parcel.id)
        assertEquals(TrackingStage.Delivered, delivered.currentStage)
        assertNull(delivered.plannedEvents)
        assertNull(repo.list().first { it.id == parcel.id }.plannedEvents)
    }

    @Test
    fun aPlannedStepStaysUntilAScanIsAsLateOrHasComeFurther() {
        val id = UUID.randomUUID()
        fun step(stage: TrackingStage, occurredAt: String) =
            PlannedTrackingEvent(description = "${stage.rawValue} planned", stage = stage, occurredAt = occurredAt)
        val scans = listOf(event(id, TrackingStage.Accepted, "2026-09-01T10:00:00Z"), event(id, TrackingStage.OutForDelivery, "2026-09-03T08:00:00Z"))
        val parcel = makeParcel(id, events = scans).copy(
            plannedEvents = listOf(
                step(TrackingStage.InTransit, "2026-09-02T10:00:00Z"),
                step(TrackingStage.InTransit, "2026-09-03T09:00:00Z"),
                step(TrackingStage.Customs, "2026-09-03T08:00:00Z"),
                step(TrackingStage.Customs, "2026-09-03T10:00:00Z"),
                step(TrackingStage.Exception, "2026-09-03T11:00:00Z"),
                step(TrackingStage.OutForDelivery, "2026-09-03T12:00:00Z"),
                step(TrackingStage.Delivered, "2026-09-03T15:00:00Z"),
            ),
        )
        // Dated before the newest scan, or as late, it is overtaken; so is a step behind the furthest stage scanned. A
        // step outside the stages' order is never behind.
        assertEquals(
            listOf("2026-09-03T10:00:00Z", "2026-09-03T11:00:00Z", "2026-09-03T12:00:00Z", "2026-09-03T15:00:00Z"),
            DemoRepository.stillPlanned(parcel).plannedEvents?.map { it.occurredAt },
        )
        val unplanned = makeParcel(id, events = scans)
        assertEquals(unplanned, DemoRepository.stillPlanned(unplanned))
        for (ending in listOf(TrackingStage.Delivered, TrackingStage.Returned)) {
            val ended = parcel.copy(trackingEvents = scans.take(1) + event(id, ending, "2026-09-02T09:00:00Z") + scans.drop(1))
            assertNull(ending.rawValue, DemoRepository.stillPlanned(ended).plannedEvents)
        }
        // None left: none planned.
        val overtaken = parcel.copy(trackingEvents = scans + event(id, TrackingStage.OutForDelivery, "2026-09-04T08:00:00Z"))
        assertNull(DemoRepository.stillPlanned(overtaken).plannedEvents)
    }

    @Test
    fun aPlannedStepReadsItsDayAsTheJournalDoesAndItsTimeWhenItHasOne() {
        val zone = ZoneId.systemDefault()
        val now = ZonedDateTime.of(2026, 11, 4, 12, 0, 0, 0, zone).toInstant()
        fun at(month: Int, day: Int, year: Int = 2026) = DateParser.isoString(ZonedDateTime.of(year, month, day, 7, 54, 0, 0, zone).toInstant())
        fun step(occurredAt: String, day: String? = null) =
            PlannedTrackingEvent(description = "Planned", stage = TrackingStage.InTransit, occurredAt = occurredAt, day = day)
        val localizer = Localizer(AppLanguage.En)
        assertEquals("today, 07:54", localizer.plannedDate(step(at(11, 4)), now))
        assertEquals("tomorrow, 07:54", localizer.plannedDate(step(at(11, 5)), now))
        assertEquals("yesterday, 07:54", localizer.plannedDate(step(at(11, 3)), now))
        assertEquals("Fri 6 Nov, 07:54", localizer.plannedDate(step(at(11, 6)), now))
        assertEquals("Fri 8 Jan 2027, 07:54", localizer.plannedDate(step(at(1, 8, year = 2027)), now))
        // A step the carrier dated by its day alone has no time.
        assertEquals("Fri 6 Nov", localizer.plannedDate(step(at(11, 6), day = "2026-11-06"), now))
        assertEquals("tomorrow", localizer.plannedDate(step(at(11, 5), day = "2026-11-05"), now))
        assertEquals("", localizer.plannedDate(step("not a date"), now))
        assertEquals("明天 07:54", Localizer(AppLanguage.Zh).plannedDate(step(at(11, 5)), now))
    }

    @Test
    fun aPlannedStepShowsOnlyWhileItsTimeIsAhead() {
        fun step(occurredAt: String, day: String? = null) =
            PlannedTrackingEvent(description = "Delivered", stage = TrackingStage.Delivered, occurredAt = occurredAt, day = day)
        val now = Instant.parse("2026-11-04T10:00:00Z")
        assertTrue(step("2026-11-04T10:01:00Z").isAhead(now))
        // The carrier's pre-written "Delivered" whose time has come is no longer planned.
        assertFalse(step("2026-11-04T10:00:00Z").isAhead(now))
        assertFalse(step("2026-11-04T07:00:00Z").isAhead(now))
        // A step dated by its day alone stands through that day, from its start in the carrier's zone.
        assertTrue(step("2026-11-03T16:00:00Z", day = "2026-11-04").isAhead(now))
        assertFalse(step("2026-11-02T16:00:00Z", day = "2026-11-03").isAhead(now))
        assertFalse(step("not a date").isAhead(now))
    }

    @Test
    fun aPlannedStepIsListedNewestFirstAndReadAsOne() {
        val zone = ZoneId.systemDefault()
        val now = ZonedDateTime.of(2026, 11, 4, 12, 0, 0, 0, zone).toInstant()
        val tomorrow = DateParser.isoString(ZonedDateTime.of(2026, 11, 5, 7, 54, 0, 0, zone).toInstant())
        val steps = listOf(
            PlannedTrackingEvent(description = "Departed the origin facility", stage = TrackingStage.InTransit, occurredAt = "2026-10-30T20:54:00Z"),
            PlannedTrackingEvent(description = "", stage = TrackingStage.OutForDelivery, location = "Zürich, CH", occurredAt = tomorrow),
            PlannedTrackingEvent(description = "Delivered", stage = TrackingStage.Delivered, occurredAt = "unreadable"),
        )
        assertEquals(listOf(steps[1], steps[0], steps[2]), plannedNewestFirst(steps))
        val localizer = Localizer(AppLanguage.En)
        // The stage speaks for a step without words, and a carrier's wording reads as a scan's.
        assertEquals(localizer.text("stage.out_for_delivery"), localizer.plannedWording(steps[1]))
        assertEquals(localizer.eventDescription("Delivered"), localizer.plannedWording(steps[2]))
        assertEquals(
            "Planned, not confirmed: ${localizer.text("stage.out_for_delivery")}, tomorrow, 07:54, Zürich, Switzerland",
            localizer.plannedStepLabel(steps[1], now),
        )
        assertEquals("Planned, not confirmed: ${localizer.eventDescription("Delivered")}", localizer.plannedStepLabel(steps[2], now))
        // A town written in another script is named by the place the server found for it, as a scan's.
        val guangzhou = EventPlace(latitude = 23.13, longitude = 113.26, precision = EventPlacePrecision.City, country = "CN", name = "Guangzhou")
        val placed = steps[1].copy(location = "广州市", place = guangzhou)
        assertEquals(
            "Planned, not confirmed: ${localizer.text("stage.out_for_delivery")}, tomorrow, 07:54, Guangzhou, China",
            localizer.plannedStepLabel(placed, now),
        )
    }

    @Test
    fun aLinkCarriesThePlannedStepsItShows() {
        val parcel = DemoRepository.seed(DateParser.date("2026-09-09T12:00:00Z")!!).first { it.label == vinyl }
        val shared = PublicPackage(
            id = parcel.id, trackingNumber = parcel.trackingNumber, label = "", carrier = parcel.carrier, createdAt = parcel.createdAt,
            syncStatus = SyncStatus.Ok, carrierData = PublicPackageCarrierData(), notificationsMuted = false,
            trackingEvents = parcel.trackingEvents, plannedEvents = parcel.plannedEvents,
        )
        assertEquals(parcel.plannedEvents, Parcel(shared = shared).plannedEvents)
        assertEquals(parcel.trackingEvents, Parcel(shared = shared).trackingEvents)
    }
}
