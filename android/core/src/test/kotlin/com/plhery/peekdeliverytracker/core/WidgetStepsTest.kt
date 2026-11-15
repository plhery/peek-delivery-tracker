package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.TimeZone
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

/**
 * The Parcels widget's steps, as the iPhone's widget and the parcel's page write them: a scan dated by its day alone
 * names its day and no time, and each step reads in the app's language, its stage's name where the carrier gave no
 * words. Synthetic numbers.
 */
class WidgetStepsTest {
    private lateinit var saved: TimeZone

    @Before
    fun zurich() {
        saved = TimeZone.getDefault()
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Zurich"))
    }

    @After
    fun restore() {
        TimeZone.setDefault(saved)
    }

    private val localizer = Localizer(AppLanguage.En, clockPreference = ClockPreference.TwentyFourHour)
    private val id = UUID.fromString("00000000-0000-4000-8000-0000000000a1")

    /** Friday 13 Nov 2026, 08:00 in Zurich. */
    private val friday = Instant.parse("2026-11-13T07:00:00Z")

    private fun step(event: TrackingEvent, localizer: Localizer = this.localizer, now: Instant = friday): DeliveryWidgetStep {
        val parcel = makeParcel(id = id, trackingNumber = "TEST0000000001").copy(trackingEvents = listOf(event))
        return ParcelStore.widgetOrder(listOf(parcel), localizer, now).single().steps!!.single()
    }

    /**
     * A scan the carrier dated by its day alone (Thursday 12 Nov, in Shanghai): the API's `occurred_at` is the start of
     * that day in the carrier's zone, Wednesday 17:00 in Zurich. The step is on Thursday where the reader is, with no
     * time: nothing on Thursday, "Thu" from Friday.
     */
    @Test
    fun aStepDatedByItsDayAloneNamesItsDayWithoutATime() {
        val shanghaiDay = event(id, TrackingStage.InTransit, "2026-11-11T16:00:00Z").copy(day = "2026-11-12")
        val step = step(shanghaiDay)
        assertEquals(Instant.parse("2026-11-11T23:00:00Z"), step.at)
        assertEquals("", step.clock)
        assertEquals("Thu", step.time(friday, localizer))
        assertEquals("", step.time(Instant.parse("2026-11-12T19:00:00Z"), localizer))
    }

    /** A scan with its time reads its time on its day, then its weekday and time, in the app's language. */
    @Test
    fun aStepWithItsTimeReadsWeekdayAndTime() {
        val evening = step(event(id, TrackingStage.InTransit, "2026-11-12T20:40:00Z"))
        assertEquals("21:40", evening.clock)
        assertEquals("21:40", evening.time(Instant.parse("2026-11-12T22:00:00Z"), localizer))
        assertEquals("Thu 21:40", evening.time(friday, localizer))
        val other = listOf(AppLanguage.Fr, AppLanguage.De).map { Localizer(it, clockPreference = ClockPreference.TwentyFourHour) }
        assertEquals(listOf("jeu 21:40", "Do 21:40"), other.map { evening.time(friday, it) })
        // A step published without its clock reads the time on the widget's own clock.
        assertEquals("Thu 21:40", evening.copy(clock = null).time(friday, localizer))
    }

    /** The service's own words for a parcel just added read in the app's language, as on the parcel's page. */
    @Test
    fun aStepIsWordedAsTheParcelsPageWordsIt() {
        val french = Localizer(AppLanguage.Fr)
        val added = event(id, TrackingStage.Pending, "2026-11-12T20:00:00Z").copy(description = "Tracking added; the carrier has not announced it yet")
        val text = step(added, french).text
        assertEquals(french.journalWording(added), text)
        assertNotEquals(added.description, text)
    }

    /** A step the carrier gave no words names its stage, as the page does, rather than leaving a blank row. */
    @Test
    fun aStepWithoutWordsNamesItsStage() {
        val wordless = event(id, TrackingStage.InTransit, "2026-11-12T20:00:00Z").copy(description = "")
        assertEquals(localizer.text("stage.in_transit"), step(wordless).text)
    }

    /** A carrier's own words with no translation read as the carrier wrote them. */
    @Test
    fun aCarriersOwnWordsReadAsWritten() {
        val sorted = event(id, TrackingStage.InTransit, "2026-11-12T20:00:00Z").copy(description = "Arrived at sort facility")
        assertEquals("Arrived at sort facility", step(sorted).text)
    }

    /** The place reads as the page writes it: the country's name after the place's; none where the location is blank. */
    @Test
    fun aStepsPlaceReadsAsThePageWritesIt() {
        val scan = event(id, TrackingStage.InTransit, "2026-11-12T20:00:00Z")
        assertEquals(TrackingLocation.spoken("Zürich, CH", null, AppLanguage.En), step(scan.copy(location = " Zürich, CH ")).place)
        assertEquals("Zürich, Switzerland", step(scan.copy(location = "Zürich, CH")).place)
        assertEquals(null, step(scan.copy(location = "  ")).place)
    }

    /** What the widget reads back keeps the step's clock. */
    @Test
    fun theStoredSnapshotKeepsTheStepsClock() {
        val parcel = makeParcel(id = id, trackingNumber = "TEST0000000001")
            .copy(trackingEvents = listOf(event(id, TrackingStage.InTransit, "2026-11-11T16:00:00Z").copy(day = "2026-11-12")))
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        store.save(DeliveryWidgetSnapshot(friday, "en", ParcelStore.widgetOrder(listOf(parcel), localizer, friday)))
        val read = requireNotNull(store.snapshot).parcels.single().steps!!.single()
        assertEquals("", read.clock)
        assertEquals("Thu", read.time(friday, localizer))
    }
}
