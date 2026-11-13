package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.RectF
import android.util.Xml
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.XmlRes
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import kotlin.math.abs
import kotlin.math.ceil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.xmlpull.v1.XmlPullParser

/**
 * The widgets' sizes: how many rows of cells a launcher lets each one take on the home screens it was tried on, and the
 * Next delivery card's carrier mark at every size the widget can be given, which keeps its shape and its place.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w720dp-h1700dp-xhdpi")
class WidgetSizingTest {
    @get:Rule
    val zurich = ZurichTime()

    @get:Rule
    val files = TemporaryFolder()

    private val app = ApplicationProvider.getApplicationContext<Context>()
    private val density get() = app.resources.displayMetrics.density

    /** One orientation of a home screen as Launcher3 measures it (`DeviceProfile`): a cell's height and the space between two, in dp. */
    private class Rows(val cell: Float, val spacing: Float)

    /**
     * The home screens the widgets were tried on, each with the orientations a launcher counts: the smallest phone
     * upright (Android 12, 720 × 1280 at 2x), the foldable opened (2076 × 2152 at 2.4375x), the ten-inch tablet (2560 ×
     * 1600 at 2x). A launcher allows the fewest rows any of them does.
     */
    private val homes = mapOf(
        "smallest phone" to listOf(Rows(cell = 78.5f, spacing = 0f)),
        "foldable" to listOf(Rows(cell = 223 / 2.4375f, spacing = 33 / 2.4375f), Rows(cell = 213 / 2.4375f, spacing = 31 / 2.4375f)),
        "tablet" to listOf(Rows(cell = 120f, spacing = 70f), Rows(cell = 109f, spacing = 16f)),
    )

    /** `LauncherAppWidgetProviderInfo.getSpan`: the cells `size` dp needs, one at least (no widget padding on these launchers). */
    private fun span(size: Float, rows: Rows): Int = maxOf(1, ceil((size + rows.spacing) / (rows.cell + rows.spacing)).toInt())

    private class Provider(val minResizeHeight: Float, val maxResizeHeight: Float, val targetCellHeight: Int)

    private fun provider(@XmlRes xml: Int): Provider {
        val parser = app.resources.getXml(xml)
        while (parser.next() != XmlPullParser.START_TAG) continue
        val attributes = Xml.asAttributeSet(parser)
        fun dimension(attribute: Int): Float = app.obtainStyledAttributes(attributes, intArrayOf(attribute)).run {
            getDimension(0, 0f).also { recycle() } / density
        }
        val target = app.obtainStyledAttributes(attributes, intArrayOf(android.R.attr.targetCellHeight)).run { getInt(0, 0).also { recycle() } }
        return Provider(dimension(android.R.attr.minResizeHeight), dimension(android.R.attr.maxResizeHeight), target)
    }

    /**
     * Next delivery grows no taller than two rows anywhere it was tried, as the iPhone's has no large size, and still
     * starts at two; the list grows as tall as the home screen, its large size the iPhone's large one.
     */
    @Test
    fun nextDeliveryTakesTwoRowsAtMostAndTheListAsManyAsItLikes() {
        val next = provider(R.xml.next_delivery_widget)
        assertEquals(2, next.targetCellHeight)
        for ((home, orientations) in homes) {
            // A launcher leaves a widget with no limit free to take every row.
            val most = if (next.maxResizeHeight <= 0f) Int.MAX_VALUE else orientations.minOf { span(next.maxResizeHeight, it) }
            val least = orientations.maxOf { span(next.minResizeHeight, it) }
            assertEquals("Next delivery's rows at most on the $home", 2, most)
            assertTrue("Next delivery starts at two rows on the $home", next.targetCellHeight in least..most)
        }
        assertEquals("the list has no height limit", 0f, provider(R.xml.parcels_widget).maxResizeHeight)
    }

    /** `DeliveryWidgetCard`'s colours, as the gallery's first card has them. */
    private fun card(name: String) = DeliveryWidgetCard(listOf("#e6deef", "#292235"), listOf("#23093f", "#c1add7"), "Out for delivery", name, 2.0)

    private fun picture(width: Int, height: Int): Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply { eraseColor(Color.DKGRAY) }

    private val three = listOf(
        widgetParcel(1, "Belgian chocolate", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", carrier = "Swiss Post", detail = "Friday"),
    ).map { it.copy(card = card(it.id.toString())) }

    /** Cards whose every mark is `mark`, and no route. */
    private fun drawnWith(mark: Bitmap, medium: Boolean): DeliveryWidgetEntry<WidgetCardArt> =
        DeliveryWidgetEntry.make(CAPTURED, true, "en", DeliveryWidgetSnapshot(CAPTURED, "en", three), medium) { _, _ -> WidgetCardArt(mark to mark, null) }

    /**
     * Every size the widget can be given: the smallest, small and medium, a phone's cells upright and on its side, a
     * tablet's two by two and three by two, the iPhone's, and a tablet's three by three, where a widget placed before
     * its height had a limit may still stand.
     */
    private val sizes = listOf(
        DpSize(110.dp, 110.dp), DpSize(250.dp, 110.dp), LAUNCHER_UPRIGHT, LAUNCHER_ON_ITS_SIDE, LAUNCHER_UPRIGHT_WIDE,
        LAUNCHER_TABLET, DpSize(488.dp, 232.dp), IPHONE_SMALL, IPHONE_MEDIUM, DpSize(488.dp, 357.dp),
    )

    /**
     * `DeliveryWidgetCardView`'s mark (`resizable().scaledToFit().frame(height:)`): at every size, the demo's marks as
     * this app draws them, one far wider than any carrier's and one narrower, keep their shape, stay inside their card
     * clear of the next button, and stand above the card's words; at the iPhone's sizes the demo's stand as tall as the
     * iPhone's (15 points, 13 on half a medium widget).
     */
    private fun marksKeepTheirShapeAndPlace() = runBlocking {
        val folder = files.newFolder("WidgetCards")
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(app, folder, store)
        publishDemo(app, store, folder, scale = density.toDouble())
        val next = ExtensionLocalizer("en").text("app.nextParcel")
        for (size in sizes) {
            val medium = NextDeliveryWidget.isMedium(size)
            val entries = mapOf(
                "the demo's" to source.current(medium, CAPTURED),
                "a wide mark" to drawnWith(picture(1200, 36), medium),
                "a narrow mark" to drawnWith(picture(24, 36), medium),
            )
            for ((what, entry) in entries) {
                val where = "$what at ${size.width.value} × ${size.height.value}"
                assertTrue("$where shows cards", entry.cards.isNotEmpty())
                val host = laidOut(widgetViews(app, size, entry), size)
                val carriers = entry.cards.map { it.first.carrier }
                val marks = views(host).filterIsInstance<ImageView>().filter { it.contentDescription?.toString() in carriers }
                assertEquals("$where: a mark on each card", carriers.size, marks.size)
                val words = views(host).filterIsInstance<TextView>().filter { it.text.isNotBlank() }.map { text ->
                    bounds(text, host).apply { right = left + (0 until text.layout.lineCount).maxOf { text.layout.getLineWidth(it) } }
                }
                val button = views(host).singleOrNull { it.contentDescription?.toString() == next }?.let { bounds(it, host) }
                for (mark in marks) {
                    val picture = mark.drawable
                    val shown = RectF(picture.bounds).also { mark.imageMatrix.mapRect(it) }
                    val view = bounds(mark, host)
                    shown.offset(view.left + mark.paddingLeft, view.top + mark.paddingTop)
                    val label = "$where: ${mark.contentDescription}'s mark $shown"
                    val drawnRatio = picture.intrinsicWidth.toFloat() / picture.intrinsicHeight
                    assertEquals("$label keeps its shape", drawnRatio, shown.width() / shown.height(), drawnRatio * 0.02f)
                    assertTrue("$label stays inside its picture's view", view.contains(RectF(shown).apply { inset(0.5f, 0.5f) }))
                    val card = bounds(clickableAncestor(mark, host), host).apply { inset(10 * density - 0.5f, 10 * density - 0.5f) }
                    assertTrue("$label stays inside its card $card", card.contains(shown))
                    // The button's chevron is a circle 18 dp across at the centre of its reach.
                    if (button != null) assertTrue("$label stays clear of the next button", shown.right <= button.centerX() - 9 * density)
                    for (text in words) assertTrue("$label stays above the words $text", shown.bottom <= text.top + 0.5f || !RectF.intersects(shown, text))
                    if (what == "the demo's" && size in listOf(IPHONE_SMALL, IPHONE_MEDIUM)) {
                        val points = if (entry.cards.size == 2) 13 else 15
                        assertTrue("$label stands $points dp tall", abs(shown.height() - points * density) <= 1f)
                    }
                }
            }
        }
    }

    @Test
    fun cardMarksKeepTheirShapeAndPlace() = marksKeepTheirShapeAndPlace()

    @Test
    @Config(fontScale = 2.0f)
    fun cardMarksKeepTheirShapeAndPlaceAtTheLargestText() = marksKeepTheirShapeAndPlace()

    /** `views` applied on a host `size` big and laid out, as a launcher's cell holds them. */
    private fun laidOut(views: android.widget.RemoteViews, size: DpSize): FrameLayout {
        val host = FrameLayout(app)
        host.addView(views.apply(app, host))
        val width = (size.width.value * density).toInt()
        val height = (size.height.value * density).toInt()
        host.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
        host.layout(0, 0, width, height)
        return host
    }

    private fun views(view: View): List<View> = listOf(view) + ((view as? ViewGroup)?.let { group -> (0 until group.childCount).flatMap { views(group.getChildAt(it)) } } ?: emptyList())

    /** `view`'s frame in `root`'s pixels. */
    private fun bounds(view: View, root: View): RectF {
        var x = 0f
        var y = 0f
        var at: View = view
        while (at !== root) {
            x += at.left - at.scrollX
            y += at.top - at.scrollY
            at = at.parent as View
        }
        return RectF(x, y, x + view.width, y + view.height)
    }

    /** The card a mark is on: the nearest view that opens its parcel. */
    private fun clickableAncestor(view: View, root: View): View {
        var at = view.parent as View
        while (!at.isClickable && at !== root) at = at.parent as View
        return at
    }
}
