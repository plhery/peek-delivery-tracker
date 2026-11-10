package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.testing.unit.GlanceAppWidgetUnitTest
import androidx.glance.appwidget.testing.unit.hasStartActivityClickAction
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasAnyDescendant
import androidx.glance.testing.unit.hasContentDescriptionEqualTo
import androidx.glance.testing.unit.hasTextEqualTo
import androidx.glance.text.FontWeight
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSelection
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.listDay
import com.plhery.peekdeliverytracker.core.listLine
import com.plhery.peekdeliverytracker.core.listName
import com.plhery.peekdeliverytracker.core.listStatus
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.time.Duration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The "Parcels" widget (ParcelsWidget) as Glance composes it, without a launcher: the rows each size keeps, in the
 * widgets' order, the line counting the rest, the empty and switched-off states, where taps go, what TalkBack reads,
 * and what it reads from the store the app publishes to.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w411dp-h914dp-xxhdpi")
class ParcelsWidgetTest {
    @get:Rule
    val zurich = ZurichTime()

    @get:Rule
    val files = TemporaryFolder()

    private val app = ApplicationProvider.getApplicationContext<Context>()
    private val english = Localizer(AppLanguage.En, variant = "android")
    private val sample = sampleList()

    private fun opens(parcel: DeliveryWidgetParcel) =
        hasStartActivityClickAction(Intent(Intent.ACTION_VIEW, Uri.parse(parcel.deepLink), app, MainActivity::class.java))

    private val opensList = hasStartActivityClickAction(Intent(Intent.ACTION_VIEW, Uri.parse(DeliveryWidgetList.link), app, MainActivity::class.java))

    private fun show(list: DeliveryWidgetList, size: DpSize, test: GlanceAppWidgetUnitTest.() -> Unit) = runGlanceAppWidgetUnitTest {
        setContext(app)
        setAppWidgetSize(size)
        provideComposable { ParcelsWidgetContent(list, size) }
        test()
    }

    private fun shown(list: DeliveryWidgetList, size: DpSize) = ListLayout(list, size, WidgetText(app)).shown

    /** The line a row of the two-by-two widget shows. */
    private fun narrowLine(parcel: DeliveryWidgetParcel, list: DeliveryWidgetList) =
        ListLayout(list, LIST_SMALL, WidgetText(app)).let { fittingLine(parcel, it, it.words) }

    private fun list(parcels: List<DeliveryWidgetParcel>, enabled: Boolean = true) =
        DeliveryWidgetList.make(CAPTURED, enabled, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels))

    /**
     * Three rows two cells by two, six four by two, and all fifteen four by four, in the widgets' order; the line counting
     * the rest where there are more.
     */
    @Test
    fun eachSizeShowsItsRowsInTheWidgetsOrder() {
        val small = shown(sample, LIST_SMALL)
        assertEquals(sample.parcels.take(3), small.rows)
        assertEquals(12, small.more)
        val medium = shown(sample, LIST_MEDIUM)
        assertEquals(sample.parcels.take(6), medium.rows)
        assertEquals(9, medium.more)
        val large = shown(sample, LIST_LARGE)
        assertEquals(sample.parcels, large.rows)
        assertEquals(0, large.more)
        // Narrow, a row takes two lines; four cells across, one.
        assertFalse(ListLayout(sample, LIST_SMALL, WidgetText(app)).wide)
        assertTrue(ListLayout(sample, LIST_MEDIUM, WidgetText(app)).wide)

        show(sample, LIST_SMALL) {
            for (parcel in sample.parcels.take(3)) onNode(hasTextEqualTo(parcel.label)).assertExists()
            onNode(hasTextEqualTo(sample.parcels[3].label)).assertDoesNotExist()
            onNode(hasTextEqualTo("+12 more") and opensList).assertExists()
            // Two cells across leave the first row's line too little room for its stage: its day stays.
            onNode(hasTextEqualTo(narrowLine(sample.parcels[0], sample))).assertExists()
            assertEquals(sample.parcels[0].listDay, narrowLine(sample.parcels[0], sample))
        }
        show(sample, LIST_MEDIUM) {
            onNode(hasTextEqualTo(sample.parcels[5].label)).assertExists()
            onNode(hasTextEqualTo(sample.parcels[6].label)).assertDoesNotExist()
            onNode(hasTextEqualTo("+9 more")).assertExists()
        }
        show(sample, LIST_LARGE) {
            onNode(hasTextEqualTo(sample.parcels.last().label)).assertExists()
            onAllNodes(hasTextEqualTo("+1 more")).assertCountEquals(0)
        }
    }

    /**
     * However many parcels there are, the rows keep the first of them and the line counts the rest; where every parcel
     * has its row, there is no line, which would take a row's place.
     */
    @Test
    fun noCountWhereEveryParcelHasItsRow() {
        for (size in listOf(LIST_SMALL, LIST_THREE_BY_TWO, LIST_MEDIUM, LIST_TWO_BY_FOUR, LIST_LARGE)) {
            val beside = shown(sample, size).rows.size
            for (count in 1..sample.parcels.size) {
                val some = list(sample.parcels.take(count))
                val kept = shown(some, size)
                assertEquals("$count at $size", some.parcels.take(kept.rows.size), kept.rows)
                assertEquals("$count at $size", count - kept.rows.size, kept.more)
                if (count <= beside) assertEquals("$count at $size", 0, kept.more)
            }
        }
        show(list(sample.parcels.take(3)), LIST_SMALL) {
            for (parcel in sample.parcels.take(3)) onNode(hasTextEqualTo(parcel.listName)).assertExists()
            onNode(opensList and hasTextEqualTo("+1 more")).assertDoesNotExist()
        }
    }

    /** The unnamed parcel is named by its carrier; a long name keeps its one line, which ends in an ellipsis. */
    @Test
    fun rowsNameTheCarrierOfAnUnnamedParcelAndKeepLongNamesToALine() {
        show(sample, LIST_LARGE) {
            onNode(hasTextEqualTo("Royal Mail")).assertExists()
            onNode(hasTextEqualTo(english.text("common.parcel"))).assertDoesNotExist()
            onNode(hasTextEqualTo("Handmade oak bookshelf with matching side table and drawers")).assertExists()
        }
        // At the largest text, fewer rows.
        val larger = ListLayout(sample, LIST_LARGE, WidgetText(fontScaled(2f)))
        assertTrue(larger.shown.rows.size < 15)
        assertTrue(larger.shown.rows.size >= 10)
        assertEquals(15 - larger.shown.rows.size, larger.shown.more)
    }

    /** A line too long for its room keeps the day, and the stage where even the day is too long; TalkBack reads both. */
    @Test
    fun aLineTooLongKeepsTheDay() {
        val layout = ListLayout(sample, LIST_SMALL, WidgetText(app))
        val parcel = sample.parcels.first { it.listDay != null }
        val status = parcel.listStatus
        fun width(text: String) = layout.type.width(text, layout.linePoints, FontWeight.Normal)
        assertEquals("$status · ${parcel.detail}", fittingLine(parcel, layout, width(parcel.listLine)))
        assertEquals(parcel.detail, fittingLine(parcel, layout, width(parcel.listLine) - 1.dp))
        assertEquals(status, fittingLine(parcel, layout, width(parcel.detail) - 1.dp))
        assertTrue(rowDescription(parcel).endsWith("$status, ${parcel.detail}"))
    }

    /** A row opens its parcel, and TalkBack reads its name, stage and day as one item. */
    @Test
    fun eachRowOpensItsParcelAndReadsAsOne() = show(sample, LIST_MEDIUM) {
        for (parcel in sample.parcels.take(6)) {
            onNode(opens(parcel) and hasAnyDescendant(hasTextEqualTo(parcel.listName))).assertExists()
            onNode(hasContentDescriptionEqualTo(rowDescription(parcel)) and opens(parcel)).assertExists()
        }
        val vinyl = sample.parcels[2]
        assertEquals("Vintage vinyl records, ${vinyl.carrier}, ${vinyl.listStatus}, ${vinyl.detail}", rowDescription(vinyl))
        // Unnamed, the carrier is its name, said once.
        val unnamed = sample.parcels[8]
        assertEquals(listOf(unnamed.carrier, unnamed.listStatus, unnamed.listDay).filterNotNull().joinToString(", "), rowDescription(unnamed))
        onNode(opensList).assertExists()
    }

    /** Nothing on its way, and the widget switched off in the app, in the existing widget's words; the list opens. */
    @Test
    fun emptyAndSwitchedOffListsSayWhy() {
        show(list(emptyList()), LIST_SMALL) {
            onNode(hasTextEqualTo(english.text("app.noneOnWay"))).assertExists()
            onNode(hasTextEqualTo(english.text("app.emptyDescription"))).assertExists()
            onNode(opensList).assertExists()
        }
        show(list(sample.parcels, enabled = false), LIST_MEDIUM) {
            onNode(opensList).assertExists()
            onNode(hasTextEqualTo(english.text("widget.disabledTitle"))).assertExists()
            onNode(hasTextEqualTo(english.text("widget.disabledDescription.android"))).assertExists()
            onNode(hasTextEqualTo(sample.parcels[0].label)).assertDoesNotExist()
        }
    }

    /** The list speaks the app's language, its count in the language's plural. */
    @Test
    fun listSpeaksTheAppsLanguage() {
        val german = Localizer(AppLanguage.De)
        assertEquals("+1 weiteres", moreParcels(german, 1))
        assertEquals("+12 weitere", moreParcels(german, 12))
        assertEquals("+3 więcej", moreParcels(Localizer(AppLanguage.Pl), 3))
        assertEquals("Ещё 21", moreParcels(Localizer(AppLanguage.Ru), 21))
        assertEquals("+1 more", moreParcels(english, 1))
        val french = sampleList(AppLanguage.Fr)
        show(french, LIST_SMALL) {
            onNode(hasTextEqualTo("+12 autres")).assertExists()
            val first = narrowLine(french.parcels[0], french)
            onAllNodes(hasTextEqualTo(first)).assertCountEquals(french.parcels.take(3).count { narrowLine(it, french) == first })
        }
        assertNotEquals(sample.parcels[0].listLine, french.parcels[0].listLine)
    }

    /**
     * The list reads what the app published, those delivered over a day ago gone, and whether it is switched on; its
     * gallery picture is the sample in the reader's language, whatever the reader tracks.
     */
    @Test
    fun listReadsWhatTheAppPublished() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(app, files.newFolder("WidgetCards"), store, languages = listOf("de-CH"))
        assertTrue(source.list(CAPTURED).parcels.isEmpty())
        assertEquals("de", source.list(CAPTURED).languageCode)

        val arrived = widgetParcel(9, "Moon lamp", deliveredAt = CAPTURED.minus(Duration.ofHours(2)))
        val published = (1..3).map { widgetParcel(it) } + arrived
        store.save(DeliveryWidgetSnapshot(CAPTURED, "en", published, total = 20))
        val read = source.list(CAPTURED)
        assertEquals(published, read.parcels)
        assertEquals(20, read.total)
        assertEquals(published.take(3), source.list(CAPTURED.plusSeconds(DeliveryWidgetSelection.deliveredFor.toLong())).parcels)
        assertEquals(19, source.list(CAPTURED.plusSeconds(DeliveryWidgetSelection.deliveredFor.toLong())).total)

        store.setEnabled(false)
        assertFalse(source.list(CAPTURED).enabled)

        val gallery = source.listSample(CAPTURED)
        assertTrue(gallery.isSample)
        assertEquals("de", gallery.languageCode)
        assertEquals(15, gallery.parcels.size)
        val shows = DeliveryWidgetUpdates.listPreviewsShow(source, CAPTURED)
        assertTrue(shows.sample)
        store.setLanguageCode("fr")
        assertNotEquals(shows, DeliveryWidgetUpdates.listPreviewsShow(source, CAPTURED))
    }

    /**
     * Placed alone, the list keeps the refresh that drops a parcel a day after it arrived; it goes with the last widget
     * of either kind.
     */
    @Test
    fun theRefreshLastsWhileEitherWidgetIsPlaced() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val alarms = shadowOf(app.getSystemService(AlarmManager::class.java))
        val widgets = shadowOf(AppWidgetManager.getInstance(app))
        assertFalse(DeliveryWidgetUpdates.isPlaced(app))
        widgets.bindAppWidgetId(2, ComponentName(app, ParcelsWidgetReceiver::class.java))
        assertTrue(DeliveryWidgetUpdates.isPlaced(app))
        DeliveryWidgetUpdates.schedule(app, store, AppClock.now())
        assertEquals(1, alarms.scheduledAlarms.size)
        // The last next delivery widget gone: the list keeps the refresh.
        NextDeliveryWidgetReceiver().onDisabled(app)
        assertEquals(1, alarms.scheduledAlarms.size)
        // The list's own last one gone (no next delivery widget placed): the refresh goes.
        ParcelsWidgetReceiver().onDisabled(app)
        assertTrue(alarms.scheduledAlarms.isEmpty())
    }

    private fun fontScaled(scale: Float): Context = app.createConfigurationContext(
        android.content.res.Configuration(app.resources.configuration).apply { fontScale = scale },
    )
}
