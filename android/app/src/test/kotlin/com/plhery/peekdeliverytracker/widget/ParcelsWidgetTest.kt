package com.plhery.peekdeliverytracker.widget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.SizeF
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
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
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
import kotlin.math.ceil
import kotlin.math.pow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotSame
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
    private fun sections(
        list: DeliveryWidgetList,
        size: DpSize,
        type: WidgetText = WidgetText(app),
        design: DeliveryWidgetList.Design = DeliveryWidgetList.Design.Sections,
    ) = SectionsLayout(list, size, type, design)

    /** The line a row of the two-by-two widget shows. */
    private fun narrowLine(parcel: DeliveryWidgetParcel, list: DeliveryWidgetList) =
        sections(list, LIST_SMALL).layout.let { fittingLine(parcel, it, it.words) }

    private fun list(parcels: List<DeliveryWidgetParcel>, enabled: Boolean = true) =
        DeliveryWidgetList.make(CAPTURED, enabled, "en", DeliveryWidgetSnapshot(CAPTURED, "en", parcels))

    /**
     * The rows each size has room for, as many as fit, in the sections' order: four two cells by two, seven four by two,
     * thirteen four by four; the line counting the rest where there are more.
     */
    @Test
    fun eachSizeHasRoomForItsRows() {
        val order = sample.sections.flatMap { it.parcels }
        for (size in listOf(LIST_SMALL, LIST_THREE_BY_TWO, LIST_MEDIUM, LIST_TWO_BY_THREE, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE)) {
            val laid = sections(sample, size)
            assertEquals("$size", order.take(laid.rows.size), laid.rows)
            assertEquals("$size", 15 - laid.rows.size, laid.more)
        }
        assertEquals(listOf(4, 7, 13), listOf(LIST_SMALL, LIST_MEDIUM, LIST_LARGE).map { sections(sample, it).rows.size })
        // The iPhone's widget each size stands for: under four cells across the small one, from there the medium one,
        // from three cells down the large one, its rows on one line where it is four cells across.
        fun layout(size: DpSize) = sections(sample, size).layout
        assertEquals(DeliveryWidgetList.Size.Small, layout(LIST_SMALL).size)
        for (size in listOf(LIST_THREE_BY_TWO, LIST_MEDIUM)) assertEquals("$size", DeliveryWidgetList.Size.Medium, layout(size).size)
        for (size in listOf(LIST_TWO_BY_THREE, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE)) {
            assertEquals("$size", DeliveryWidgetList.Size.Large, layout(size).size)
        }
        assertEquals(listOf(LIST_FOUR_BY_THREE, LIST_LARGE), listOf(LIST_SMALL, LIST_MEDIUM, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE).filter { layout(it).lineRows })

        // Two cells across leave the first row's line too little room for its stage: its day stays, opening the line.
        assertEquals(sample.parcels[0].listDay?.openingLine(Locale.ENGLISH), narrowLine(sample.parcels[0], sample))
    }

    /**
     * Fifteen parcels are rows under the app list's sections, in its order: in a small widget four rows without the
     * headings, in a medium one the headings and rows down two columns, in a large one down one; a heading never ends a
     * column; then the count of the rest, which opens the list.
     */
    @Test
    fun manyParcelsAreRowsUnderTheAppListsSections() {
        val order = sample.sections.flatMap { it.parcels }
        assertEquals(listOf("Phone case", "Camera lens from Japan", "Kids' science kit", "Garden hose reel", "Ceramic pour-over set"), order.take(5).map { it.label })
        val small = sections(sample, LIST_SMALL)
        assertEquals(order.take(4), small.rows)
        assertEquals(11, small.more)
        assertTrue(small.columns.flatten().none { it is DeliveryWidgetList.Line.Heading })
        show(sample, LIST_SMALL) {
            for (parcel in order.take(4)) onNode(hasTextEqualTo(parcel.listName)).assertExists()
            for (parcel in order.take(4)) onNode(hasTextEqualTo(narrowLine(parcel, sample))).assertExists()
            onNode(hasTextEqualTo(order[4].label)).assertDoesNotExist()
            onNode(hasTextEqualTo("+11 more") and opensList).assertExists()
            // The heading's words only as the stage of a parcel held up.
            val attention = english.text("app.needsAttention")
            onAllNodes(hasTextEqualTo(attention)).assertCountEquals(order.take(4).count { narrowLine(it, sample) == attention })
        }
        for (size in listOf(LIST_MEDIUM, LIST_FOUR_BY_THREE, LIST_LARGE)) {
            val laid = sections(sample, size)
            assertEquals("$size", if (size == LIST_MEDIUM) 2 else 1, laid.columns.size)
            assertEquals("$size", order.take(laid.rows.size), laid.rows)
            assertEquals("$size", 15 - laid.rows.size, laid.more)
            assertTrue("$size", laid.more > 0)
            for (column in laid.columns.dropLast(1)) assertTrue("$size", column.last() is DeliveryWidgetList.Line.Row)
            assertEquals("$size", DeliveryWidgetList.Line.More(laid.more), laid.columns.last().last())
            assertTrue("$size", laid.columns.first().first() is DeliveryWidgetList.Line.Heading)
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
        val olderMedium = sections(older, LIST_MEDIUM)
        assertEquals(older.parcels.take(olderMedium.rows.size), olderMedium.rows)
        assertTrue(olderMedium.columns.flatten().none { it is DeliveryWidgetList.Line.Heading })
        assertEquals(2, olderMedium.columns.size)
    }

    /**
     * The line counting the parcels left out ends the last column, after the last row shown, as they would follow it; the
     * columns, the line with them, fit the widget at any height and text size that leaves room for more than one row.
     */
    @Test
    fun theLineForTheParcelsLeftOutEndsTheColumnTheyWouldFollow() {
        val medium = sections(sample, LIST_MEDIUM)
        val (first, second) = medium.columns
        assertEquals(DeliveryWidgetList.Line.More(medium.more), second.last())
        show(sample, LIST_MEDIUM) {
            val names = { column: List<DeliveryWidgetList.Line> -> column.filterIsInstance<DeliveryWidgetList.Line.Row>().map { hasTextEqualTo(it.parcel.listName) } }
            onNode(hasAnyDescendant(hasTextEqualTo("+${medium.more} more")) and hasAnyDescendant(names(second).last()) and hasAnyDescendant(names(first).first()).not())
                .assertExists()
        }
        for (scale in listOf(1f, 1.3f, 2f)) {
            val type = WidgetText(fontScaled(scale))
            for (height in 110..429 step 10) for (width in listOf(LIST_SMALL.width, LIST_THREE_BY_TWO.width, LIST_MEDIUM.width)) {
                val laid = sections(sample, DpSize(width, height.dp), type)
                val room = laid.layout.room.height
                assertTrue("$width x $height at $scale", laid.columns.all { laid.height(it) <= room } || laid.rows.size == 1)
                if (laid.more > 0) assertEquals("$width x $height at $scale", DeliveryWidgetList.Line.More(laid.more), laid.columns.last().last())
            }
        }
    }

    /**
     * The rows a widget tries ([DeliveryWidgetList.layouts]) start above what the tallest widget of its size holds in
     * the smallest type, without headings or the last line: a small or medium one is under 280 dp tall whatever the
     * launcher; a large one lists fewer parcels than its bound.
     */
    @Test
    fun theRowsTriedStartAboveWhatTheTallestWidgetHolds() {
        val smallest = WidgetText(fontScaled(0.5f))
        for ((size, across) in listOf(DpSize(249.dp, 279.dp) to 1, DpSize(900.dp, 279.dp) to 2)) {
            val kind = listSize(size)
            val least = ListLayout(sample, size, smallest, compact = true, across)
            val held = ((least.room.height + least.metrics.rowSpacing) / (least.rowHeight + least.metrics.rowSpacing)).toInt() * across
            assertTrue("$size holds $held rows, over ${kind.mostRows}", held <= kind.mostRows)
        }
        assertTrue(DeliveryWidgetSelection.listLimit <= DeliveryWidgetList.Size.Large.mostRows)
    }

    /**
     * However many parcels there are, at any size and text size, the rows show them all or leave no room for another
     * under the last: a two-by-two widget showed three of four parcels over a blank a row tall, as the iPhone's
     * smaller one holds three.
     */
    @Test
    fun theRowsLeaveNoRoomForAnotherBlank() {
        val order = sample.sections.flatMap { it.parcels }
        for (scale in listOf(0.85f, 1f, 1.3f, 2f)) {
            val type = WidgetText(fontScaled(scale))
            for (size in listOf(LIST_SMALL, LIST_THREE_BY_TWO, LIST_MEDIUM, LIST_TWO_BY_THREE, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE, DpSize(194.dp, 223.dp), DpSize(249.dp, 279.dp), DpSize(900.dp, 279.dp))) {
                for (count in 3..order.size) {
                    val laid = sections(list(order.take(count)), size, type)
                    val case = "$count parcels in $size at $scale"
                    assertEquals(case, count, laid.rows.size + laid.more)
                    if (laid.more == 0) continue
                    // One row more, with its heading if it opens a section, and the last line, doesn't fit, even in the
                    // type taken before a row is given up.
                    val kind = laid.layout.size
                    val next = DeliveryWidgetList.lines(laid.sections, laid.rows.size + 1, headings = kind != DeliveryWidgetList.Size.Small)
                    val columns = if (kind == DeliveryWidgetList.Size.Medium) DeliveryWidgetList.columns(next) else listOf(next)
                    val least = ListLayout(sample, size, type, compact = kind != DeliveryWidgetList.Size.Large, columns.size)
                    val blank = laid.layout.room.height - laid.columns.maxOf { laid.height(it) }
                    assertTrue("$case: ${laid.rows.size} rows over $blank blank", columns.any { laid.height(it, least) > least.room.height })
                }
            }
        }
        assertEquals(listOf(4, 4), listOf(LIST_SMALL, DpSize(194.dp, 223.dp)).map { sections(list(order.take(4)), it).rows.size })
    }

    /**
     * Each list is drawn a little inside the sizes its launcher gives ([ParcelsWidget.fitting]), so that RemoteViews,
     * which shows the closest layout that fits the widget's frame (`ceil(frame) + 1` over it each way), shows the
     * frame's own. A Pixel launcher on a phone with a second screen gives a two-by-two widget four sizes, each twice,
     * and frames it at 471 x 548 px at 420 dpi, 1.5 dp under the 210.3 dp it reports: as reported, the frame would show
     * the other screen's layout, three rows and a row's room blank.
     */
    @Test
    fun eachListIsDrawnInsideTheSizesItsLauncherGives() {
        val reported = listOf(SizeF(179.42857f, 175.61905f), SizeF(179.42857f, 210.28572f), SizeF(360.38095f, 108.95238f), SizeF(419.0476f, 108.95238f))
        val frame = SizeF(471 / 2.625f, 548 / 2.625f)
        fun shown(sizes: List<SizeF>): SizeF = sizes.filter { ceil(frame.width) + 1 > it.width && ceil(frame.height) + 1 > it.height }
            .minByOrNull { (it.width - frame.width).pow(2) + (it.height - frame.height).pow(2) } ?: sizes.minBy { it.width * it.height }
        val options = Bundle().apply {
            putParcelableArrayList(AppWidgetManager.OPTION_APPWIDGET_SIZES, ArrayList(reported.flatMap { listOf(it, it) }))
            putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 179)
            putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 210)
        }
        @Suppress("DEPRECATION")
        val fitted = ParcelsWidget.fitting(options).getParcelableArrayList<SizeF>(AppWidgetManager.OPTION_APPWIDGET_SIZES)!!
        assertEquals(listOf(SizeF(177f, 173f), SizeF(177f, 208f), SizeF(358f, 106f), SizeF(417f, 106f)), fitted)
        assertEquals(177, ParcelsWidget.fitting(options).getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH))
        assertEquals(208, ParcelsWidget.fitting(options).getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT))
        assertEquals(reported[0], shown(reported))
        assertEquals(SizeF(177f, 208f), shown(fitted))

        val order = sample.sections.flatMap { it.parcels }
        fun rows(size: SizeF, count: Int) = sections(list(order.take(count)), DpSize(size.width.dp, size.height.dp)).rows.size
        assertEquals(listOf(4, 4, 4), listOf(4, 6, 7).map { rows(shown(fitted), it) })
        assertEquals(3, rows(shown(reported), 7))
    }

    /** Drawn by the app itself, a placed list is the launcher's to show, from its sizes. */
    @Test
    fun aPlacedListIsDrawnByTheApp() {
        val manager = AppWidgetManager.getInstance(app)
        val widgets = shadowOf(manager)
        val component = ComponentName(app, ParcelsWidgetReceiver::class.java)
        widgets.addInstalledProvider(AppWidgetProviderInfo().apply { provider = component })
        widgets.bindAppWidgetId(3, component)
        manager.updateAppWidgetOptions(3, Bundle().apply { putParcelableArrayList(AppWidgetManager.OPTION_APPWIDGET_SIZES, arrayListOf(SizeF(LIST_SMALL.width.value, LIST_SMALL.height.value))) })
        val placed = widgets.getViewFor(3)
        runBlocking { ParcelsWidget.draw(app) }
        assertNotEquals(null, widgets.getViewFor(3))
        assertNotSame(placed, widgets.getViewFor(3))
        // Removed meanwhile: nothing to draw.
        runBlocking { ParcelsWidget.draw(app, 4, Bundle()) }
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
        val headings = five.columns.flatten().filterIsInstance<DeliveryWidgetList.Line.Heading>()
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
     * Passes show every parcel or none ([DeliveryWidgetList.fits]): where two can't stand one above the other, in a
     * shorter widget or at larger text, the parcels are rows, every one, rather than fewer passes and a line for the
     * others; a medium widget's rows try one column, without headings, before one is left out. A phone's usual sizes
     * keep their passes.
     */
    @Test
    fun passesThatDontAllFitAreRowsForEveryParcel() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val folder = files.newFolder("WidgetCards")
        runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble(), drawn = DeliveryWidgetSelection.limit) }
        val source = widgetSource(app, folder, store)
        val demo = source.list(CAPTURED)
        fun some(count: Int) = demo.copy(parcels = demo.parcels.take(count))
        val type = WidgetText(app)
        val largest = WidgetText(fontScaled(2f))

        // Two cells by two and four by two hold every pass at every text size a widget takes; four by four holds six at
        // the default text, and at the largest, as on the iPhone, they are rows, every one.
        for (text in listOf(type, largest)) {
            assertTrue(passesFit(2, LIST_SMALL, text))
            assertTrue(passesFit(4, LIST_MEDIUM, text))
        }
        assertTrue(passesFit(6, LIST_LARGE, type))
        assertFalse(passesFit(6, LIST_LARGE, largest))
        assertEquals(6, sections(some(6), LIST_LARGE, largest, DeliveryWidgetList.Design.Passes).rows.size)
        // Four by three holds four passes, not five or six: those are rows, each on one line.
        assertTrue(passesFit(4, LIST_FOUR_BY_THREE, type))
        assertFalse(passesFit(5, LIST_FOUR_BY_THREE, type))
        val six = sections(some(6), LIST_FOUR_BY_THREE, type, DeliveryWidgetList.Design.Passes)
        assertEquals(some(6).sections.flatMap { it.parcels }, six.rows)
        assertEquals(0, six.more)
        assertTrue(six.layout.lineRows)
        show(some(6), LIST_FOUR_BY_THREE, source::read) {
            for (parcel in some(6).parcels) {
                onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
                // A large pass has the day at its top and the stage beside the name; a row, both at its end.
                onAllNodes(hasTextEqualTo(lineRowEnd(parcel, six.layout).line))[0].assertExists()
            }
            onNode(hasTextEqualTo(moreParcels(english, 2))).assertDoesNotExist()
            onNode(hasTextEqualTo(moreParcels(english, 1))).assertDoesNotExist()
        }

        // A medium widget on a phone on its side: one pass a column, so four parcels are rows. Two columns under their
        // headings hold fewer than four; one column of one-line rows in the smaller type holds every one.
        val side = LAUNCHER_ON_ITS_SIDE
        assertFalse(passesFit(4, side, type))
        assertTrue(sections(some(4), side, type).rows.size < 4)
        val down = sections(some(4), side, type, DeliveryWidgetList.Design.Passes)
        assertEquals(some(4).sections.flatMap { it.parcels }, down.rows)
        assertEquals(1, down.columns.size)
        assertTrue(down.columns.single().none { it is DeliveryWidgetList.Line.Heading })
        assertTrue(down.layout.lineRows)
        assertTrue(down.layout.metrics.compact)
        show(some(4), side, source::read) {
            for (parcel in some(4).parcels) onNode(opens(parcel) and hasContentDescriptionEqualTo(rowDescription(parcel))).assertExists()
            for (kind in ParcelSectionKind.entries) onNode(hasTextEqualTo(english.text(sectionTitleKey(kind)))).assertDoesNotExist()
            onNode(hasTextEqualTo(moreParcels(english, 1))).assertDoesNotExist()
        }

        // Two cells by two made short: rows, both parcels. Without the pictures, the sections' design: no single column.
        val short = DpSize(LIST_SMALL.width, 120.dp)
        assertFalse(passesFit(2, short, type))
        assertEquals(2, sections(some(2), short, type, DeliveryWidgetList.Design.Passes).rows.size)
        assertTrue(DeliveryWidgetList.fits(DeliveryWidgetList.Design.Sections, 4, DeliveryWidgetList.Size.Medium).none {
            it is DeliveryWidgetList.Fit.Rows && it.layout.oneColumn
        })
    }

    /**
     * However many parcels there are, the rows keep the first of them and the line counts the rest; where every parcel
     * has its row, there is no line, which would take a row's place.
     */
    @Test
    fun noCountWhereEveryParcelHasItsRow() {
        for (size in listOf(LIST_SMALL, LIST_THREE_BY_TWO, LIST_MEDIUM, LIST_TWO_BY_FOUR, LIST_LARGE)) {
            val beside = sections(sample, size).rows.size
            for (count in 1..sample.parcels.size) {
                val some = list(sample.parcels.take(count))
                val kept = sections(some, size)
                assertEquals("$count at $size", some.sections.flatMap { it.parcels }.take(kept.rows.size), kept.rows)
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
     * A medium widget splits its lines into two columns about as tall as each other ([DeliveryWidgetList.columns]), read
     * down the first then the second; a single row and its heading take one, across the widget.
     */
    @Test
    fun mediumListReadsDownTwoColumns() {
        fun columns(count: Int) = sections(list(sample.parcels.take(count)), LIST_MEDIUM).columns.map { column -> column.count { it is DeliveryWidgetList.Line.Row } }
        assertEquals(listOf(listOf(1), listOf(1, 1), listOf(1, 2), listOf(1, 3), listOf(2, 3), listOf(2, 4), listOf(3, 4), listOf(3, 5)), (1..8).map { columns(it) })
    }

    /**
     * At the iPhone's own small size, the three rows and the last line fit only in smaller type, which the widget takes
     * before it gives a row up; three parcels alone keep the larger.
     */
    @Test
    fun aShortListTakesSmallerTypeBeforeItGivesARowUp() {
        val iPhoneSmall = DpSize(158.dp, 158.dp)
        val crowded = sections(sample, iPhoneSmall)
        assertEquals(sample.sections.flatMap { it.parcels }.take(3), crowded.rows)
        assertTrue(crowded.layout.metrics.compact)
        val three = sections(list(sample.parcels.take(3)), iPhoneSmall)
        assertEquals(3, three.rows.size)
        assertFalse(three.layout.metrics.compact)
        // A large widget keeps its type and gives rows up.
        assertFalse(sections(sample, LIST_LARGE, WidgetText(fontScaled(2f))).layout.metrics.compact)
    }

    /**
     * A large widget's row ends in the whole stage and day where they fit beside the name; beside a long name, in what
     * fits once the name keeps two fifths of the widget.
     */
    @Test
    fun aLargeRowEndsInWhatFitsBesideItsName() {
        val layout = sections(sample, LIST_LARGE).layout
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
        val larger = sections(sample, LIST_LARGE, WidgetText(fontScaled(2f)))
        assertTrue(larger.rows.size < sections(sample, LIST_LARGE).rows.size)
        assertTrue(larger.rows.size >= 10)
        assertEquals(15 - larger.rows.size, larger.more)
    }

    /**
     * A line too long for its room keeps the day, and the stage where even the day is too long; TalkBack reads both. The
     * day takes a capital where it opens the line.
     */
    @Test
    fun aLineTooLongKeepsTheDay() {
        val layout = sections(sample, LIST_SMALL).layout
        val parcel = sample.parcels.first { it.listDay != null }.copy(detail = "tomorrow")
        val status = parcel.listStatus
        fun width(text: String) = layout.type.width(text, layout.statusPoints, FontWeight.Normal)
        assertEquals("$status · tomorrow", fittingLine(parcel, layout, width(parcel.listLine)))
        assertEquals("Tomorrow", fittingLine(parcel, layout, width(parcel.listLine) - 1.dp))
        assertEquals(status, fittingLine(parcel, layout, width("Tomorrow") - 1.dp))
        assertTrue(rowDescription(parcel).endsWith("$status, tomorrow"))
    }

    /**
     * The new-update dot (#72) where the app's list shows it: before each marked row's line, which keeps to the room
     * the dot leaves, taking a shorter wording, then a smaller size, rather than being cut short, so every size keeps its
     * rows at every text size a widget takes. TalkBack says "New update." first, in the widget's language, and nothing
     * of it for the others.
     */
    @Test
    fun aParcelWithNewsHasTheDotAndEverySizeKeepsItsRows() {
        val news = sample.copy(parcels = sample.parcels.map { it.copy(newUpdate = true) })
        val sizes = listOf(LIST_SMALL, LIST_THREE_BY_TWO, LIST_MEDIUM, LIST_TWO_BY_THREE, LIST_TWO_BY_FOUR, LIST_FOUR_BY_THREE, LIST_LARGE, LAUNCHER_ON_ITS_SIDE)
        var smaller = 0
        for (size in sizes) {
            for (type in listOf(WidgetText(app), WidgetText(fontScaled(1.3f)), WidgetText(fontScaled(2f)))) {
                val plain = sections(sample, size, type)
                val marked = sections(news, size, type)
                assertEquals("$size", plain.rows.map { it.id }, marked.rows.map { it.id })
                assertEquals("$size", plain.more, marked.more)
                val layout = marked.layout
                for (parcel in marked.rows) {
                    if (layout.lineRows) {
                        val end = lineRowEnd(parcel, layout)
                        val name = minOf(layout.type.width(parcel.listName, layout.metrics.namePoints, FontWeight.Medium), layout.room.width * 0.4f)
                        assertTrue("${parcel.listName} at $size", name + end.gap + parcel.dotRoom + end.width <= layout.words)
                        assertTrue("${parcel.listName} at $size: ${end.line}", layout.type.lines(end.line, end.points, FontWeight.Normal, end.width + 0.5.dp) <= 1)
                    } else {
                        val room = layout.words - parcel.dotRoom
                        val line = fittingLine(parcel, layout, room)
                        val points = linePoints(layout, line, room)
                        // What fitted without the dot fits beside it.
                        val alone = fittingLine(parcel.copy(newUpdate = null), layout, layout.words)
                        if (layout.type.lines(alone, layout.statusPoints, FontWeight.Normal, layout.words) <= 1) {
                            assertTrue("${parcel.listName} at $size: $line", layout.type.lines(line, points, FontWeight.Normal, room) <= 1)
                            if (points < layout.statusPoints) smaller += 1
                        }
                        assertTrue(points >= layout.statusPoints * 0.8f - 0.01f)
                    }
                }
            }
        }

        assertTrue("Some lines take a smaller size beside the dot", smaller > 0)

        val some = sample.copy(parcels = sample.parcels.mapIndexed { index, parcel -> if (index % 2 == 0) parcel.copy(newUpdate = true) else parcel })
        show(some, LIST_MEDIUM) {
            for (parcel in sections(some, LIST_MEDIUM).rows) {
                val said = if (parcel.newUpdate == true) "New update. ${rowDescription(parcel)}" else rowDescription(parcel)
                onNode(hasContentDescriptionEqualTo(said) and opens(parcel)).assertExists()
            }
        }
        val first = some.parcels.first()
        assertEquals("Nouvelle mise à jour. ${rowDescription(first)}", spokenRow(first, "fr"))
        for (language in AppLanguage.entries) {
            val said = requireNotNull(newUpdateSpoken(first, language.rawValue))
            assertNotEquals(language.rawValue, "parcel.newUpdate.", said)
            assertEquals(language.rawValue, Localizer(language).text("parcel.newUpdate") + ".", said)
        }
        assertEquals(null, newUpdateSpoken(first.copy(newUpdate = null), "en"))
    }

    /**
     * A row's line too long for its column takes a smaller size, down to 0.8 of it, before it is cut short, dot or none:
     * three cells by two, two narrow columns, at the largest text a widget takes, in every language. A line that fits
     * at a smaller size takes it ("Out for delivery", cut short at its own size); one that still doesn't stands at 0.8.
     */
    @Test
    fun aLineTooLongForItsColumnTakesASmallerSize() {
        val type = WidgetText(fontScaled(1.3f))
        var smaller = 0
        for (language in AppLanguage.entries) {
            val sections = sections(sampleList(language), LIST_THREE_BY_TWO, type)
            val layout = sections.layout
            assertFalse(layout.lineRows)
            for (parcel in sections.rows) {
                val line = fittingLine(parcel, layout, layout.words)
                val points = linePoints(layout, line, layout.words)
                val fits = type.lines(line, points, FontWeight.Normal, layout.words) <= 1
                assertTrue("${language.rawValue}: $line at $points", fits || points <= layout.statusPoints * 0.8f + 0.01f)
                assertTrue("${language.rawValue}: $line at $points", points >= layout.statusPoints * 0.8f - 0.01f)
                if (fits && type.lines(line, layout.statusPoints, FontWeight.Normal, layout.words) > 1) smaller += 1
                if (language == AppLanguage.En && line == "Out for delivery") {
                    assertTrue(type.lines(line, layout.statusPoints, FontWeight.Normal, layout.words) > 1)
                    assertTrue(fits && points < layout.statusPoints)
                }
            }
        }
        assertTrue(smaller > 0)
        // A line that fits keeps the layout's size.
        val roomy = sections(sample, LIST_MEDIUM)
        for (parcel in roomy.rows) {
            val line = fittingLine(parcel, roomy.layout, roomy.layout.words)
            if (roomy.layout.type.lines(line, roomy.layout.statusPoints, FontWeight.Normal, roomy.layout.words) <= 1) {
                assertEquals(roomy.layout.statusPoints, linePoints(roomy.layout, line, roomy.layout.words))
            }
        }
    }

    /**
     * Passes and cards show the dot before their stage, the passes' line taking the shorter wording the dot leaves room
     * for; each says "New update." first, as the rows do, the cards in their carrier's words.
     */
    @Test
    fun passesAndCardsWithNewsHaveTheDot() {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val folder = files.newFolder("WidgetCards")
        runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble(), drawn = DeliveryWidgetSelection.limit) }
        val source = widgetSource(app, folder, store)
        val demo = source.list(CAPTURED)
        fun some(count: Int) = demo.copy(parcels = demo.parcels.take(count).mapIndexed { index, parcel -> if (index != 1) parcel.copy(newUpdate = true) else parcel })
        // Passes two cells by two, four by two and four by four, and three cards one above another.
        for ((count, size) in listOf(2 to LIST_SMALL, 4 to LIST_MEDIUM, 6 to LIST_LARGE, 3 to LIST_LARGE)) {
            val list = some(count)
            show(list, size, source::read) {
                for (parcel in list.parcels) {
                    val said = if (parcel.newUpdate == true) "New update. ${rowDescription(parcel)}" else rowDescription(parcel)
                    onNode(opens(parcel) and hasContentDescriptionEqualTo(said)).assertExists()
                }
            }
        }
        // A card, two cards side by side, a card over its steps: the card's first words, its carrier's mark.
        for ((count, size) in listOf(1 to LIST_SMALL, 2 to LIST_MEDIUM, 2 to LIST_LARGE, 1 to LIST_LARGE)) {
            val list = some(count)
            val said = appliedViews(app, runBlocking { listViews(app, size, list, source::read) }).filter { it.isClickable }.map { spoken(it) }
            for (parcel in list.parcels) {
                val card = said.filter { parcel.label in it }
                assertEquals("$count at $size: $said", 1, card.size)
                val first = if (parcel.newUpdate == true) "New update. ${parcel.carrier} " else "${parcel.carrier} "
                assertTrue("$count at $size: $said", card.single().startsWith(first))
            }
            assertEquals("$count at $size: $said", list.parcels.count { it.newUpdate == true }, said.count { "New update." in it })
        }
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
            onNode(hasTextEqualTo("+11 autres")).assertExists()
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
