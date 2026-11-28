package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.TimeZone
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * The widgets' days follow the phone's clock while the app stays closed: the app words each parcel's day ahead for the
 * next midnights where it changes ([DeliveryWidgetDays]), and the widgets read the words for the moment they draw. A
 * parcel published as "expected tomorrow" late in the evening reads "Expected today" on its delivery day, and last night's delivery
 * "yesterday"; each widget is drawn again at those midnights.
 */
class WidgetDayRolloverTest {
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

    private val localizer = Localizer(AppLanguage.En)

    /** Thursday 12 Nov 2026, 23:30 in Zurich: the app last ran and published. */
    private val published = Instant.parse("2026-11-12T22:30:00Z")

    /** Friday 13 Nov 2026, 08:00 in Zurich: the reader looks at the home screen. */
    private val morning = Instant.parse("2026-11-13T07:00:00Z")

    private val id = UUID.fromString("00000000-0000-4000-8000-000000000001")

    /** A parcel in transit that the carrier expects on Friday 13 Nov. */
    private val parcel = makeParcel(id = id, trackingNumber = "TEST0000000001").let {
        it.copy(
            expectedDelivery = "2026-11-13",
            trackingEvents = listOf(event(id, TrackingStage.InTransit, "2026-11-12T08:00:00Z")),
        )
    }

    /** What the widgets read: the snapshot the app saved at 23:30, read back from the shared preferences. */
    private fun storedAt23h30(): DeliveryWidgetSnapshot {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        store.save(DeliveryWidgetSnapshot(published, "en", ParcelStore.widgetOrder(listOf(parcel), localizer, published)))
        return requireNotNull(store.snapshot)
    }

    /** Control: published at 08:00 on the day, the app's words say "expected today". */
    @Test
    fun controlTheAppsOwnWordsOnTheDayAreToday() {
        val fresh = ParcelStore.widgetOrder(listOf(parcel), localizer, morning).single()
        assertEquals("expected today", fresh.listDay)
        assertEquals("expected tomorrow", ParcelStore.widgetOrder(listOf(parcel), localizer, published).single().listDay)
    }

    @Test
    fun aWidgetRedrawUsesMidnightWhereTheReaderIsNow() {
        val snapshot = storedAt23h30()
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"))
        val evening = Instant.parse("2026-11-13T00:00:00Z")
        val row = DeliveryWidgetList.make(evening, "en", snapshot).parcels.single()
        assertEquals("expected tomorrow", row.listDay)
        assertEquals(Instant.parse("2026-11-13T05:00:00Z"), DeliveryWidgetDays.changes(snapshot.parcels, evening).first())
    }

    @Test
    fun anEstimateBeyondTheFirstWeekReachesTodayAndExpires() {
        val distant = parcel.copy(expectedDelivery = "2026-11-24")
        val row = ParcelStore.widgetOrder(listOf(distant), localizer, published).single()
        assertEquals("expected today", row.worded(Instant.parse("2026-11-24T12:00:00Z")).listDay)
        assertEquals("In transit", row.worded(Instant.parse("2026-11-25T12:00:00Z")).listLine)
    }

    @Test
    fun theParcelsWidgetStillSaysTomorrowOnTheDeliveryDay() {
        val list = DeliveryWidgetList.make(morning, languageCode = "en", snapshot = storedAt23h30())
        val row = list.parcels.single()
        assertEquals(
            "At 08:00 on Fri 13 Nov (Zurich) the Parcels widget's row for a parcel expected that day should read " +
                "\"In transit · expected today\"; it reads the words the app wrote at 23:30 the evening before",
            "In transit · expected today",
            row.listLine.openingLine(ExtensionLocalizer.locale("en")),
        )
    }

    @Test
    fun theParcelsWidgetsCardStillSaysTomorrowOnTheDeliveryDay() {
        val list = DeliveryWidgetList.make(morning, languageCode = "en", snapshot = storedAt23h30())
        assertEquals(
            "At 08:00 on Fri 13 Nov (Zurich) the Parcels widget's card should say \"Expected today\" for a parcel expected that " +
                "day; it says what the app wrote at 23:30 the evening before",
            "Expected today",
            list.parcels.single().detail.openingLine(ExtensionLocalizer.locale("en")),
        )
    }

    /** The widgets are drawn again at the midnights where the days change, sooner than their refresh. */
    @Test
    fun theWidgetsAreDrawnAgainAtTheMidnightsWhereTheDaysChange() {
        val parcels = storedAt23h30().parcels
        val friday = Instant.parse("2026-11-12T23:00:00Z") // midnight in Zurich
        val saturday = Instant.parse("2026-11-13T23:00:00Z")
        // And on Tuesday, four days without a scan, it needs attention.
        val tuesday = Instant.parse("2026-11-16T23:00:00Z")
        assertEquals(listOf(friday, saturday, tuesday), DeliveryWidgetDays.changes(parcels, after = published))
        // At 23:50 midnight comes before the refresh.
        val late = Instant.parse("2026-11-12T22:50:00Z")
        assertEquals(friday, DeliveryWidgetTimeline.next(late, parcels = parcels))
        // On Saturday the passed estimate gives way to the status.
        val list = DeliveryWidgetList.make(saturday.plusSeconds(8 * 3600), languageCode = "en", snapshot = storedAt23h30())
        assertEquals("In transit", list.parcels.single().listLine)
        // Mid-afternoon on Friday, the refresh comes first.
        val afternoon = Instant.parse("2026-11-13T14:00:00Z")
        assertEquals(afternoon.plusSeconds(20 * 60), DeliveryWidgetTimeline.next(afternoon, parcels = parcels))
    }

    /**
     * The Parcels widget's sections and Next up follow the day too: published at 23:30 on Thursday, a parcel due that
     * day is under Arriving today and Next up, one due Friday under On the way. On Friday morning the app's list has
     * them the other way round, and so does the widget, with no "Arriving today" over yesterday's parcel.
     */
    @Test
    fun theParcelsWidgetsSectionsAndNextUpFollowTheDay() {
        val thursdayId = UUID.fromString("00000000-0000-4000-8000-000000000002")
        val dueThursday = makeParcel(id = thursdayId, trackingNumber = "TEST0000000002").copy(
            expectedDelivery = "2026-11-12",
            trackingEvents = listOf(event(thursdayId, TrackingStage.InTransit, "2026-11-12T09:00:00Z")),
        )
        val parcels = listOf(parcel, dueThursday)
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        store.save(DeliveryWidgetSnapshot(published, "en", ParcelStore.widgetOrder(parcels, localizer, published)))
        fun sections(list: DeliveryWidgetList) = list.sections.map { section -> section.kind to section.parcels.map { it.id } }

        val evening = DeliveryWidgetList.make(published, languageCode = "en", snapshot = store.snapshot)
        assertEquals(listOf(ParcelSectionKind.Today to listOf(thursdayId), ParcelSectionKind.Active to listOf(id)), sections(evening))
        assertEquals(thursdayId, evening.cardOrder.first().id)

        // Control: the app's own list on Friday morning.
        val fresh = DeliveryWidgetList(morning, "en", ParcelStore.widgetOrder(parcels, localizer, morning))
        val friday = listOf(ParcelSectionKind.Today to listOf(id), ParcelSectionKind.Active to listOf(thursdayId))
        assertEquals(friday, sections(fresh))

        val list = DeliveryWidgetList.make(morning, languageCode = "en", snapshot = store.snapshot)
        assertEquals(
            "At 08:00 on Fri 13 Nov the Parcels widget should show Friday's parcel under Arriving today and Thursday's " +
                "under On the way, as the app's list does; it keeps the sections the app published at 23:30 on Thursday",
            friday,
            sections(list),
        )
        assertEquals("Friday's parcel leads the cards as Next up", id, list.cardOrder.first().id)
        assertEquals(listOf("In transit · expected today", "In transit"), list.sections.flatMap { it.parcels }.map { it.listLine })
    }

    /**
     * A step's time names its weekday from the day after ("Thu 21:00" on Friday), so the widget is drawn again at the
     * midnight after a step, even where the parcel's words stay the same.
     */
    @Test
    fun theWidgetsAreDrawnAgainAtTheMidnightAfterAStep() {
        val noEstimate = parcel.copy(
            expectedDelivery = null,
            trackingEvents = listOf(event(id, TrackingStage.InTransit, "2026-11-12T20:00:00Z")),
        )
        val shown = ParcelStore.widgetOrder(listOf(noEstimate), localizer, published).single()
        val midnight = Instant.parse("2026-11-12T23:00:00Z")
        assertEquals("the parcel's words stay the same at midnight", null, shown.later?.firstOrNull { it.from == midnight })
        assertEquals(midnight, DeliveryWidgetTimeline.next(Instant.parse("2026-11-12T22:50:00Z"), parcels = listOf(shown)))
        assertEquals("Thu 21:00", localizer.stepTime(shown.steps!!.single().at, morning))
    }

    /** The same for a delivery: "Delivered · today" at 20:00 is still "today" the next morning (the widget keeps it 24 h). */
    @Test
    fun aParcelDeliveredLastNightStillSaysTodayNextMorning() {
        val evening = Instant.parse("2026-11-12T19:00:00Z") // 20:00 Thursday in Zurich
        val delivered = parcel.copy(
            expectedDelivery = null,
            trackingEvents = listOf(event(id, TrackingStage.Delivered, "2026-11-12T18:55:00Z")),
        )
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        store.save(DeliveryWidgetSnapshot(evening, "en", ParcelStore.widgetOrder(listOf(delivered), localizer, evening)))
        val row = DeliveryWidgetList.make(morning, languageCode = "en", snapshot = store.snapshot).parcels.single()
        // Control: the app at 08:00 would write "yesterday".
        assertEquals("yesterday", ParcelStore.widgetOrder(listOf(delivered), localizer, morning).single().listDay)
        assertEquals(
            "At 08:00 on Fri 13 Nov the Parcels widget should date a parcel delivered at 19:55 on Thursday \"yesterday\"; " +
                "it still reads the \"today\" the app wrote at 20:00 on Thursday",
            "yesterday",
            row.listDay,
        )
    }

    /**
     * A parcel the carrier has only the label of: the widgets, the list and a shared parcel's line say its day is
     * expected, after its stage, and a row too narrow for both keeps the stage, never a bare day, which reads as the day
     * a parcel came. Once the day has passed with no scan, the stage alone.
     */
    @Test
    fun aLabelsDayReadsAsExpectedBesideItsStage() {
        val french = Localizer(AppLanguage.Fr)
        val labelOnly = parcel.copy(trackingEvents = listOf(event(id, TrackingStage.Registered, "2026-11-12T12:33:00Z")))
        val row = ParcelStore.widgetOrder(listOf(labelOnly), french, morning).single()
        assertEquals("prévu aujourd’hui", row.listDay)
        assertEquals(french.widgetStatus(labelOnly, morning), row.listStatus)
        assertNull(row.dayAlone)
        assertNull(row.listDayAlone)
        assertEquals("prévu aujourd’hui", french.parcelExpectedDay(labelOnly, morning))
        assertEquals("Prévu aujourd’hui", french.sharedParcelDetail(labelOnly, morning))

        val nextMorning = Instant.parse("2026-11-14T07:00:00Z")
        assertNull(ParcelStore.widgetOrder(listOf(labelOnly), french, nextMorning).single().listDay)
        assertNull(row.worded(nextMorning).listDay)

        val carried = ParcelStore.widgetOrder(listOf(parcel), french, morning).single()
        assertEquals("prévu aujourd’hui", carried.listDayAlone)
    }

    /**
     * A parcel at customs, under Needs attention: a row too narrow for its stage and its day keeps "At customs", which
     * its day alone would read as on its way.
     */
    @Test
    fun aParcelThatNeedsTheReaderKeepsItsStageWhereTheLineHoldsOne() {
        val atCustoms = parcel.copy(trackingEvents = parcel.trackingEvents + event(id, TrackingStage.Customs, "2026-11-12T20:00:00Z"))
        val row = ParcelStore.widgetOrder(listOf(atCustoms), localizer, morning).single()
        assertEquals("attention", row.section)
        assertEquals("expected today", row.listDay)
        assertNull(row.dayAlone)
        assertNull(row.listDayAlone)
    }
}
