package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.widget.RemoteViews
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSelection
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.screenshot
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The "Parcels" widget as a launcher shows it, its RemoteViews applied on a wallpaper: its three sizes with the sample
 * (two cells by two and four by two side by side, four by four under them), beside nothing on its way and the widget
 * switched off; light and dark; the largest text; the sizes between; and the cards and passes a few of the demo's
 * parcels take at each size.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w600dp-h740dp-xhdpi")
class ParcelsWidgetScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    @get:Rule
    val zurich = ZurichTime()

    private val app = ApplicationProvider.getApplicationContext<Context>()

    private class Shown(val views: RemoteViews, val size: DpSize)

    @get:Rule
    val files = TemporaryFolder()

    private fun widget(
        size: DpSize,
        list: DeliveryWidgetList = sampleList(),
        art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> WidgetCardArt? = { _, _ -> null },
    ) = Shown(runBlocking { listViews(app, size, list, art) }, size)

    /** The demo's next deliveries with their cards' pictures, as the store publishes them. */
    private val demoSource by lazy {
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val folder = files.newFolder("WidgetCards")
        runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble(), drawn = DeliveryWidgetSelection.limit) }
        widgetSource(app, folder, store)
    }

    /** The demo's first `count` parcels at `size`. */
    private fun demo(size: DpSize, count: Int): Shown {
        val list = demoSource.list(CAPTURED)
        return widget(size, list.copy(parcels = list.parcels.take(count)), demoSource::read)
    }

    private fun none(enabled: Boolean = true) = DeliveryWidgetList.make(CAPTURED, enabled, "en", DeliveryWidgetSnapshot(CAPTURED, "en", emptyList()))

    /** Columns of widgets in rows, on a wallpaper of the phone's mode. */
    private fun shoot(name: String, dark: Boolean, rows: List<List<List<Shown>>>) {
        compose.setContent {
            Column(
                Modifier.testTag(SHOT).background(if (dark) Color(0xFF30343A) else Color(0xFFC9D3DD)).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                for (row in rows) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        for (column in row) {
                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) { for (shown in column) WidgetOnHome(shown.views, shown.size) }
                        }
                    }
                }
            }
        }
        compose.screenshot("widget_list_$name")
    }

    /** Small and medium, then large beside nothing on its way and the widget switched off. */
    private fun sizes(name: String, dark: Boolean) = shoot(
        name, dark,
        listOf(
            listOf(listOf(widget(LIST_SMALL)), listOf(widget(LIST_MEDIUM))),
            listOf(listOf(widget(LIST_LARGE)), listOf(widget(LIST_SMALL, none()), widget(LIST_SMALL, none(enabled = false)))),
        ),
    )

    @Test
    fun light() = sizes("light", dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun dark() = sizes("dark", dark = true)

    /** At the phone's largest font scale (WidgetKit's largest for a widget): fewer rows. */
    @Test
    @Config(fontScale = 2.0f)
    fun largestText() = sizes("largest_text", dark = false)

    /**
     * Small and medium, a row each: one, two and three parcels two cells by two (a card, passes, rows); one to four
     * four by two (cards, then passes in two columns).
     */
    private fun designs(name: String, dark: Boolean) = shoot(
        name, dark,
        listOf(
            listOf(listOf(demo(LIST_SMALL, 1)), listOf(demo(LIST_SMALL, 2)), listOf(demo(LIST_SMALL, 3))),
            listOf(listOf(demo(LIST_MEDIUM, 1)), listOf(demo(LIST_MEDIUM, 2))),
            listOf(listOf(demo(LIST_MEDIUM, 3)), listOf(demo(LIST_MEDIUM, 4))),
        ),
    )

    @Test
    @Config(qualifiers = "w800dp-h740dp-xhdpi")
    fun designsLight() = designs("designs_light", dark = false)

    @Test
    @Config(qualifiers = "w800dp-h740dp-night-xhdpi")
    fun designsDark() = designs("designs_dark", dark = true)

    /** Large: one parcel's card over its last steps, two and three cards; four and six passes. */
    private fun largeDesigns(name: String, dark: Boolean) = shoot(
        name, dark,
        listOf(
            listOf(listOf(demo(LIST_LARGE, 1)), listOf(demo(LIST_LARGE, 2)), listOf(demo(LIST_LARGE, 3))),
            listOf(listOf(demo(LIST_LARGE, 4)), listOf(demo(LIST_LARGE, 6))),
        ),
    )

    @Test
    @Config(qualifiers = "w1220dp-h960dp-xhdpi")
    fun largeDesignsLight() = largeDesigns("large_designs_light", dark = false)

    @Test
    @Config(qualifiers = "w1220dp-h960dp-night-xhdpi")
    fun largeDesignsDark() = largeDesigns("large_designs_dark", dark = true)

    /**
     * Between the three: three cells by two (two columns), two by three and two by four (one column of two-line rows),
     * four by three (one-line rows); and the widget's first look until it has drawn (parcels_widget_preview).
     */
    @Test
    @Config(qualifiers = "w600dp-h1100dp-xhdpi")
    fun between() = shoot(
        "between", dark = false,
        listOf(
            listOf(listOf(widget(LIST_THREE_BY_TWO)), listOf(widget(LIST_TWO_BY_THREE))),
            listOf(listOf(widget(LIST_FOUR_BY_THREE)), listOf(widget(LIST_TWO_BY_FOUR))),
            listOf(listOf(Shown(RemoteViews(app.packageName, R.layout.parcels_widget_preview), LIST_MEDIUM))),
        ),
    )
}
