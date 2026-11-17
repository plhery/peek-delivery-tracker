package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.screenshot
import java.io.File
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The cards the Parcels widget shows for its fewest parcels ([OneOrTwoCards]), as a launcher shows them, their
 * RemoteViews applied at the iPhone's widget sizes, on a wallpaper (a screen wide enough for a small and a medium one
 * side by side, at twice the pixels): the demo's cards with the pictures this app draws, and the pictures themselves
 * (the iPhone's for its sample, then this app's); light and dark.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w600dp-h1000dp-xhdpi")
class WidgetScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    @get:Rule
    val zurich = ZurichTime()

    @get:Rule
    val files = TemporaryFolder()

    private val app = ApplicationProvider.getApplicationContext<Context>()

    private class Shown(val views: RemoteViews, val size: DpSize)

    private fun widget(size: DpSize, list: DeliveryWidgetList, art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> WidgetCardArt?) =
        Shown(runBlocking { listViews(app, size, list, art) }, size)

    /** `source`'s list of its first `count` parcels. */
    private fun first(source: DeliveryWidgetSource, count: Int): DeliveryWidgetList = source.list(CAPTURED).let { it.copy(parcels = it.parcels.take(count)) }

    /** Rows of widgets, then rows of pictures, on a wallpaper of the phone's mode. */
    private fun shoot(name: String, dark: Boolean, widgets: List<List<Shown>>, pictures: List<List<Bitmap>> = emptyList()) {
        compose.setContent {
            Column(
                Modifier.testTag(SHOT).background(if (dark) Color(0xFF30343A) else Color(0xFFC9D3DD)).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                for (row in widgets) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) { for (shown in row) WidgetOnHome(shown.views, shown.size) }
                }
                for (row in pictures) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Top) {
                        for (picture in row) {
                            // At its size in points: the iPhone's at 3 pixels to the point, this app's at the screen's.
                            val scale = if (picture.density == Bitmap.DENSITY_NONE) 3f else picture.density / 160f
                            Image(picture.asImageBitmap(), null, Modifier.size((picture.width / scale).dp, (picture.height / scale).dp))
                        }
                    }
                }
            }
        }
        compose.screenshot("widget_$name")
    }

    /** The iPhone's picture of its sample's card `index` (the demo's), as its tests draw it, from the tests' resources. */
    private fun iPhone(index: Int, picture: DeliveryWidgetPicture, dark: Boolean): Bitmap =
        checkNotNull(javaClass.getResourceAsStream("/${DeliveryWidgetSample.folderName}/sample-$index-${picture.rawValue}-${if (dark) "dark" else "light"}.png")).use {
            BitmapFactory.decodeStream(it).apply { density = Bitmap.DENSITY_NONE }
        }

    private fun android(card: DeliveryWidgetCard, picture: DeliveryWidgetPicture, dark: Boolean, folder: File): Bitmap =
        BitmapFactory.decodeFile(picture.file(card, dark, folder).path).apply { density = (app.resources.displayMetrics.density * 160).toInt() }

    /**
     * The demo's next deliveries with the pictures this app draws, one in a small widget and two in a medium one; a card
     * whose parcel has no places, with Pip alone where its route would be, alone in a small widget and across a medium
     * one; then the iPhone's pictures of the same cards over this app's.
     */
    private fun cards(dark: Boolean) {
        val folder = files.newFolder("WidgetCards")
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(app, folder, store)
        val published = runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble()) }
        val unplacedFolder = files.newFolder("Unplaced")
        val unplacedStore = DeliveryWidgetSharedStore(MemoryDefaults())
        runBlocking {
            publishDemo(app, unplacedStore, unplacedFolder, app.resources.displayMetrics.density.toDouble(), drawn = 1) { parcels ->
                parcels.filter { parcel -> parcel.trackingEvents.all { it.place == null } }
            }
        }
        val unplaced = widgetSource(app, unplacedFolder, unplacedStore)
        val first = published.parcels[0].card!!
        val second = published.parcels[1].card!!
        shoot(
            if (dark) "cards_dark" else "cards_light", dark,
            listOf(
                listOf(widget(IPHONE_SMALL, first(source, 1), source::read), widget(IPHONE_MEDIUM, first(source, 2), source::read)),
                listOf(widget(IPHONE_SMALL, first(unplaced, 1), unplaced::read), widget(IPHONE_MEDIUM, first(unplaced, 1), unplaced::read)),
            ),
            listOf(
                listOf(iPhone(0, DeliveryWidgetPicture.Small, dark), iPhone(0, DeliveryWidgetPicture.Half, dark), iPhone(1, DeliveryWidgetPicture.Half, dark)),
                listOf(
                    android(first, DeliveryWidgetPicture.Small, dark, folder), android(first, DeliveryWidgetPicture.Half, dark, folder),
                    android(second, DeliveryWidgetPicture.Half, dark, folder),
                ),
                listOf(
                    iPhone(0, DeliveryWidgetPicture.Mark, dark), iPhone(1, DeliveryWidgetPicture.Mark, dark),
                    android(first, DeliveryWidgetPicture.Mark, dark, folder), android(second, DeliveryWidgetPicture.Mark, dark, folder),
                ),
            ),
        )
    }

    @Test
    fun cardsLight() = cards(dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun cardsDark() = cards(dark = true)
}
