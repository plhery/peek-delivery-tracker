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
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSelection
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.listDay
import com.plhery.peekdeliverytracker.core.listLine
import com.plhery.peekdeliverytracker.core.listName
import com.plhery.peekdeliverytracker.core.listStatus
import com.plhery.peekdeliverytracker.core.openingLine
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import java.time.Duration
import java.util.Locale
import kotlinx.coroutines.runBlocking
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
 * The "Parcels" widget (ParcelsWidget) as Glance composes it, without a launcher: the design its number of parcels
 * takes at each size, the rows under the app list's sections and the line counting the rest, the empty and
 * switched-off states, where taps go, what TalkBack reads, and what it reads from the store the app publishes to.
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

    private fun show(
        list: DeliveryWidgetList,
        size: DpSize,
        art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> WidgetCardArt? = { _, _ -> null },
        test: GlanceAppWidgetUnitTest.() -> Unit,
    ) = runGlanceAppWidgetUnitTest {
        setContext(app)
        setAppWidgetSize(size)
        provideComposable { ParcelsWidgetContent(list, size, art) }
        test()
    }

    /** The rows under the sections at `size`, in the sections' order, and the count of the rest. */
    private fun sections(list: DeliveryWidgetList, size: DpSize) = SectionsLayout(list, size, WidgetText(app))

    private fun shown(list: DeliveryWidgetList, size: DpSize) = ListLayout(list, size, WidgetText(app)).shown

    /** The line a row of the two-by-two widget shows. */
    private fun narrowLine(parcel: DeliveryWidgetParcel, list: DeliveryWidgetList) =
        ListLayout(list, LIST_SMALL, WidgetText(app)).let { fittingLine(parcel, it, it.words) }

    private fun list(parcels: List<DeliveryWidgetParcel>, enabled: Boolean = true) =
        DeliveryWidgetList.make(CAPTURED, enabled, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels))

    /**
     * The rows each size has room for, which its sections keep: three two cells by two, six four by two, and all
     * fifteen four by four, in the order they are given; the line counting the rest where there are more.
     */
    @Test
    fun eachSizeHasRoomForItsRows() {
        val small = shown(sample, LIST_SMALL)
        assertEquals(sample.parcels.take(3), small.rows)
        assertEquals(12, small.more)
        val medium = shown(sample, LIST_MEDIUM)
        assertEquals(sample.parcels.take(6), medium.rows)
        assertEquals(9, medium.more)
        val large = shown(sample, LIST_LARGE)
        assertEquals(sample.parcels, large.rows)
        assertEquals(0, large.more)
        // The iPhone's widget each size stands for: under four cells across the small one, from there the medium one,
        // from three cells down the large one, its rows on one line where it is four cells across.
        fun layout(size: DpSize) = ListLayout(sample, size, WidgetText(app))
        assertEquals(DeliveryWidgetList.Size.Small, layout(LIST_SMALL).size)
        for (size in listOf(LIST_THREE_BY_TWO, LIST_MEDIUM)) assertEquals("$size", DeliveryWidgetList.Size.Medium, layout(size).size)
        for (size in listOf(LIST_TWO_BY_THREE, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE)) {
            assertEquals("$size", DeliveryWidgetList.Size.Large, layout(size).size)
        }
        assertEquals(listOf(LIST_FOUR_BY_THREE, LIST_LARGE), listOf(LIST_SMALL, LIST_MEDIUM, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE).filter { layout(it).lineRows })
        assertEquals(listOf(sample.parcels.take(3)), layout(LIST_SMALL).columns)
        assertEquals(listOf(sample.parcels.take(3), sample.parcels.subList(3, 6)), layout(LIST_MEDIUM).columns)
        assertEquals(listOf(sample.parcels), layout(LIST_LARGE).columns)

        // Two cells across leave the first row's line too little room for its stage: its day stays, opening the line.
        assertEquals(sample.parcels[0].listDay?.openingLine(Locale.ENGLISH), narrowLine(sample.parcels[0], sample))
    }

    /**
     * Fifteen parcels are rows under the app list's sections, in its order: in a small widget three rows without the
     * headings, in a medium one the headings and rows down two columns, in a large one down one; a heading never ends a
     * column; then the count of the rest, which opens the list.
     */
    @Test
    fun manyParcelsAreRowsUnderTheAppListsSections() {
        val order = sample.sections.flatMap { it.parcels }
        assertEquals(listOf("Phone case", "Camera lens from Japan", "Kids' science kit", "Garden hose reel", "Ceramic pour-over set"), order.take(5).map { it.label })
        val small = sections(sample, LIST_SMALL)
        assertEquals(order.take(3), small.rows)
        assertEquals(12, small.more)
        assertTrue(small.columns.flatten().all { it is SectionEntry.Row })
        show(sample, LIST_SMALL) {
            for (parcel in order.take(3)) onNode(hasTextEqualTo(parcel.listName)).assertExists()
            for (parcel in order.take(3)) onNode(hasTextEqualTo(narrowLine(parcel, sample))).assertExists()
            onNode(hasTextEqualTo(order[3].label)).assertDoesNotExist()
            onNode(hasTextEqualTo("+12 more") and opensList).assertExists()
            onNode(hasTextEqualTo(english.text("app.needsAttention"))).assertDoesNotExist()
        }
        for (size in listOf(LIST_MEDIUM, LIST_FOUR_BY_THREE, LIST_LARGE)) {
            val laid = sections(sample, size)
            assertEquals("$size", if (size == LIST_MEDIUM) 2 else 1, laid.columns.size)
            assertEquals("$size", order.take(laid.rows.size), laid.rows)
            assertEquals("$size", 15 - laid.rows.size, laid.more)
            assertTrue("$size", laid.more > 0)
            for (column in laid.columns) {
                assertTrue("$size", column.first() is SectionEntry.Heading || column.first() is SectionEntry.Row)
                assertTrue("$size", column.last() is SectionEntry.Row)
            }
            assertTrue("$size", laid.columns.first().first() is SectionEntry.Heading)
        }
        val medium = sections(sample, LIST_MEDIUM)
        show(sample, LIST_MEDIUM) {
            // The heading, besides the stage of the parcel held up, in the same words.
            onAllNodes(hasTextEqualTo(english.text("app.needsAttention"))).assertCountEquals(2)
            onNode(hasTextEqualTo("4")).assertExists()
            for (parcel in medium.rows) onNode(hasTextEqualTo(parcel.listName)).assertExists()
            onNode(hasTextEqualTo("+${medium.more} more") and opensList).assertExists()
            // As on the iPhone, no header above the rows.
            onNode(hasTextEqualTo(english.text("app.title").uppercase(Locale.ENGLISH))).assertDoesNotExist()
        }
        val large = sections(sample, LIST_LARGE)
        assertTrue(large.rows.size > medium.rows.size)
        show(sample, LIST_LARGE) {
            for (kind in listOf("app.arrivingToday", "app.onTheWaySection")) onNode(hasTextEqualTo(english.text(kind))).assertExists()
            onNode(hasTextEqualTo(large.rows.last().listName)).assertExists()
            onNode(hasTextEqualTo("+${large.more} more")).assertExists()
        }
        // From an app that published no sections, the rows in the widgets' order, without headings.
        val older = list(sample.parcels.map { it.copy(section = null) })
        assertEquals(listOf(older.parcels.take(3), older.parcels.subList(3, 6)), sections(older, LIST_MEDIUM).columns.map { column -> column.map { (it as SectionEntry.Row).parcel } })
    }

    /**
     * Its design by how many parcels it shows, from the demo's cards: one a small widget shows as Next delivery's card,
     * two as passes, three as rows; a medium one two cards, four passes; a large one three cards, six passes. Until a
     * parcel's pictures are drawn, its rows.
     */
    @Test
    fun theDesignTakesTheNumberOfParcels() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val folder = files.newFolder("WidgetCards")
        runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble(), drawn = DeliveryWidgetSelection.limit) }
        val source = widgetSource(app, folder, store)
        val demo = source.list(CAPTURED)
        assertTrue(demo.parcels.size >= 7)
        assertTrue(demo.parcels.all { it.section != null && it.carrierId != null })
        fun some(count: Int) = demo.copy(parcels = demo.parcels.take(count))

        // One: the card, Next delivery's, which opens the parcel; the rest of the widget opens the list.
        val one = some(1).parcels.single()
        show(some(1), LIST_SMALL, source::read) {
            onNode(hasTextEqualTo(one.label)).assertExists()
            onAllNodes(opens(one))[0].assertExists()
        }
        // Two in a small widget: passes, each opening its parcel and read as one.
        show(some(2), LIST_SMALL, source::read) {
            for (parcel in some(2).parcels) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
        }
        // Three: rows, under no headings.
        show(some(3), LIST_SMALL, source::read) {
            for (parcel in sections(some(3), LIST_SMALL).rows) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
        }
        // Two in a medium widget: the cards, the Next up first; four: passes in two columns; five: the sections.
        show(some(2), LIST_MEDIUM, source::read) {
            for (parcel in some(2).parcels) onNode(hasTextEqualTo(parcel.label)).assertExists()
        }
        show(some(4), LIST_MEDIUM, source::read) {
            for (parcel in some(4).parcels) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
        }
        val five = sections(some(5), LIST_MEDIUM)
        val headings = five.columns.flatten().filterIsInstance<SectionEntry.Heading>()
        assertTrue(headings.isNotEmpty())
        show(some(5), LIST_MEDIUM, source::read) {
            for (heading in headings) onAllNodes(hasTextEqualTo(english.text(sectionTitleKey(heading.kind))))[0].assertExists()
            for (parcel in five.rows) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
        }
        // A large widget: one card over its last steps, then two and three cards, six passes, seven rows.
        val steps = requireNotNull(one.steps)
        assertTrue(steps.isNotEmpty())
        show(some(1), LIST_LARGE, source::read) {
            onNode(hasTextEqualTo(steps.first().text)).assertExists()
        }
        for (count in 2..3) show(some(count), LIST_LARGE, source::read) {
            for (parcel in some(count).parcels) onAllNodes(opens(parcel))[0].assertExists()
        }
        show(some(6), LIST_LARGE, source::read) {
            for (parcel in some(6).parcels) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
        }
        // Without the pictures, the rows, whatever their number.
        show(some(2), LIST_SMALL) {
            for (parcel in some(2).parcels) onNode(opens(parcel) and hasAnyDescendant(hasTextEqualTo(parcel.listName))).assertExists()
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

    /**
     * A medium widget reads down two columns once one can't hold its parcels, the first never the shorter; three, it
     * lists down one, across the widget.
     */
    @Test
    fun mediumListReadsDownTwoColumns() {
        fun columns(count: Int) = ListLayout(list(sample.parcels.take(count)), LIST_MEDIUM, WidgetText(app)).columns.map { it.size }
        assertEquals(listOf(3, 3), columns(7))
        assertEquals(listOf(3, 3), columns(6))
        assertEquals(listOf(3, 2), columns(5))
        assertEquals(listOf(2, 2), columns(4))
        assertEquals(listOf(3), columns(3))
        assertEquals(listOf(1), columns(1))
    }

    /**
     * At the iPhone's own small size, the three rows and the last line fit only in smaller type, which the widget takes
     * before it gives a row up; three parcels alone keep the larger.
     */
    @Test
    fun aShortListTakesSmallerTypeBeforeItGivesARowUp() {
        val iPhoneSmall = DpSize(158.dp, 158.dp)
        val crowded = ListLayout(sample, iPhoneSmall, WidgetText(app))
        assertEquals(sample.parcels.take(3), crowded.shown.rows)
        assertTrue(crowded.metrics.compact)
        val three = ListLayout(list(sample.parcels.take(3)), iPhoneSmall, WidgetText(app))
        assertEquals(3, three.shown.rows.size)
        assertFalse(three.metrics.compact)
        // A large widget keeps its type and gives rows up.
        assertFalse(ListLayout(sample, LIST_LARGE, WidgetText(fontScaled(2f))).metrics.compact)
    }

    /**
     * A large widget's row ends in the whole stage and day where they fit beside the name; beside a long name, in what
     * fits once the name keeps two fifths of the widget.
     */
    @Test
    fun aLargeRowEndsInWhatFitsBesideItsName() {
        val layout = ListLayout(sample, LIST_LARGE, WidgetText(app))
        val short = sample.parcels.first { it.listName.length < 12 }
        val end = lineRowEnd(short, layout)
        assertEquals(short.listLine.openingLine(Locale.ENGLISH), end.line)
        assertEquals(16.dp, end.gap)
        val long = sample.parcels.first { it.listName.startsWith("Handmade oak bookshelf") }
        val cut = lineRowEnd(long, layout)
        assertEquals(10.dp, cut.gap)
        assertTrue(cut.width <= layout.words - 10.dp - layout.room.width * 0.4f)
        assertEquals(fittingLine(long, layout, layout.words - 10.dp - layout.room.width * 0.4f), cut.line)
    }

    /** The unnamed parcel is named by its carrier; a long name keeps its one line, which ends in an ellipsis. */
    @Test
    fun rowsNameTheCarrierOfAnUnnamedParcelAndKeepLongNamesToALine() {
        show(list(sample.parcels.filter { it.section == "active" }), LIST_LARGE) {
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

    /**
     * A line too long for its room keeps the day, and the stage where even the day is too long; TalkBack reads both. The
     * day takes a capital where it opens the line.
     */
    @Test
    fun aLineTooLongKeepsTheDay() {
        val layout = ListLayout(sample, LIST_SMALL, WidgetText(app))
        val parcel = sample.parcels.first { it.listDay != null }.copy(detail = "tomorrow")
        val status = parcel.listStatus
        fun width(text: String) = layout.type.width(text, layout.statusPoints, FontWeight.Normal)
        assertEquals("$status · tomorrow", fittingLine(parcel, layout, width(parcel.listLine)))
        assertEquals("Tomorrow", fittingLine(parcel, layout, width(parcel.listLine) - 1.dp))
        assertEquals(status, fittingLine(parcel, layout, width("Tomorrow") - 1.dp))
        assertTrue(rowDescription(parcel).endsWith("$status, tomorrow"))
    }

    /** A row opens its parcel, and TalkBack reads its name, stage and day as one item. */
    @Test
    fun eachRowOpensItsParcelAndReadsAsOne() = show(sample, LIST_MEDIUM) {
        for (parcel in sections(sample, LIST_MEDIUM).rows) {
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
            val rows = sections(french, LIST_SMALL).rows
            val first = narrowLine(rows[0], french)
            onAllNodes(hasTextEqualTo(first)).assertCountEquals(rows.count { narrowLine(it, french) == first })
            onNode(hasTextEqualTo(Localizer(AppLanguage.Fr).text("app.needsAttention"))).assertDoesNotExist()
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
        store.save(DeliveryWidgetSnapshot(CAPTURED, "en", published))
        assertEquals(published, source.list(CAPTURED).parcels)
        assertEquals(published.take(3), source.list(CAPTURED.plusSeconds(DeliveryWidgetSelection.deliveredFor.toLong())).parcels)

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
