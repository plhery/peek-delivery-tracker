package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.event
import com.plhery.peekdeliverytracker.core.ParcelLogicTestFixtures.makeParcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.time.Instant
import java.util.TimeZone
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * The widgets' days follow the phone's clock while the app stays closed: the app words each parcel's day ahead for the
 * next midnights where it changes ([DeliveryWidgetDays]), and the widgets read the words for the moment they draw. A
 * parcel published as "tomorrow" late in the evening reads "Today" on its delivery day, and last night's delivery
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

    /** Control: published at 08:00 on the day, the app's words say "today". */
    @Test
    fun controlTheAppsOwnWordsOnTheDayAreToday() {
        val fresh = ParcelStore.widgetOrder(listOf(parcel), localizer, morning).single()
        assertEquals("today", fresh.listDay)
        assertEquals("tomorrow", ParcelStore.widgetOrder(listOf(parcel), localizer, published).single().listDay)
    }

    @Test
    fun theParcelsWidgetStillSaysTomorrowOnTheDeliveryDay() {
        val list = DeliveryWidgetList.make(morning, enabled = true, languageCode = "en", snapshot = storedAt23h30())
        val row = list.parcels.single()
        assertEquals(
            "At 08:00 on Fri 13 Nov (Zurich) the Parcels widget's row for a parcel expected that day should read " +
                "\"In transit · today\"; it reads the words the app wrote at 23:30 the evening before",
            "In transit · today",
            row.listLine.openingLine(ExtensionLocalizer.locale("en")),
        )
    }

    @Test
    fun theNextDeliveryWidgetStillSaysTomorrowOnTheDeliveryDay() {
        val entry = DeliveryWidgetEntry.make<Unit>(morning, enabled = true, languageCode = "en", snapshot = storedAt23h30(), medium = false) { _, _ -> null }
        assertEquals(
            "At 08:00 on Fri 13 Nov (Zurich) the Next delivery widget should say \"Today\" for a parcel expected that " +
                "day; it says what the app wrote at 23:30 the evening before",
            "Today",
            entry.shown.single().detail.openingLine(ExtensionLocalizer.locale("en")),
        )
    }

    /** The widgets are drawn again at the midnights where the days change, sooner than their refresh. */
    @Test
    fun theWidgetsAreDrawnAgainAtTheMidnightsWhereTheDaysChange() {
        val parcels = storedAt23h30().parcels
        val friday = Instant.parse("2026-11-12T23:00:00Z") // midnight in Zurich
        val saturday = Instant.parse("2026-11-13T23:00:00Z")
        assertEquals(listOf(friday, saturday), DeliveryWidgetDays.changes(parcels, after = published))
        // At 23:50 midnight comes before the refresh.
        val late = Instant.parse("2026-11-12T22:50:00Z")
        assertEquals(friday, DeliveryWidgetTimeline.next(late, turns = emptyList(), parcels = parcels))
        // On Saturday the passed estimate gives way to the status.
        val list = DeliveryWidgetList.make(saturday.plusSeconds(8 * 3600), enabled = true, languageCode = "en", snapshot = storedAt23h30())
        assertEquals("In transit", list.parcels.single().listLine)
        // Mid-afternoon on Friday, the refresh comes first.
        val afternoon = Instant.parse("2026-11-13T14:00:00Z")
        assertEquals(afternoon.plusSeconds(20 * 60), DeliveryWidgetTimeline.next(afternoon, turns = emptyList(), parcels = parcels))
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
        val row = DeliveryWidgetList.make(morning, enabled = true, languageCode = "en", snapshot = store.snapshot).parcels.single()
        // Control: the app at 08:00 would write "yesterday".
        assertEquals("yesterday", ParcelStore.widgetOrder(listOf(delivered), localizer, morning).single().listDay)
        assertEquals(
            "At 08:00 on Fri 13 Nov the Parcels widget should date a parcel delivered at 19:55 on Thursday \"yesterday\"; " +
                "it still reads the \"today\" the app wrote at 20:00 on Thursday",
            "yesterday",
            row.listDay,
        )
    }
}
