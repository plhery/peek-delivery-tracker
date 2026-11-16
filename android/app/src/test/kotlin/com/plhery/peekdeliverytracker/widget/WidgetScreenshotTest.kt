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
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSample
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSharedStore
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.screenshot
import java.io.File
import java.time.Duration
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The home screen widget as a launcher shows it, its RemoteViews applied at the iPhone's widget sizes, on a wallpaper
 * (a screen wide enough for a small and a medium one side by side, at twice the pixels): the gallery's sample with the
 * iPhone's own pictures over the same cards with the pictures this app draws, and the pictures themselves (the
 * iPhone's, then this app's); words, empty and switched off; light and dark; the largest text; and at the sizes a
 * launcher gives it.
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

    private fun widget(size: DpSize, entry: DeliveryWidgetEntry<WidgetCardArt>) = Shown(runBlocking { widgetViews(app, size, entry) }, size)

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

    /** The iPhone's picture of the sample's card `index`, as its tests draw it. */
    private fun iPhone(index: Int, picture: DeliveryWidgetPicture, dark: Boolean): Bitmap =
        app.assets.open("${DeliveryWidgetSample.folderName}/sample-$index-${picture.rawValue}-${if (dark) "dark" else "light"}.png").use {
            BitmapFactory.decodeStream(it).apply { density = Bitmap.DENSITY_NONE }
        }

    private fun android(card: DeliveryWidgetCard, picture: DeliveryWidgetPicture, dark: Boolean, folder: File): Bitmap =
        BitmapFactory.decodeFile(picture.file(card, dark, folder).path).apply { density = (app.resources.displayMetrics.density * 160).toInt() }

    /**
     * The gallery's sample with the iPhone's pictures, over the demo's same next deliveries with the pictures this app
     * draws; a card whose parcel has no places, with Pip alone where its route would be, alone across a medium widget;
     * then the iPhone's pictures over this app's.
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
                listOf(widget(IPHONE_SMALL, source.placeholder(false, CAPTURED)), widget(IPHONE_MEDIUM, source.placeholder(true, CAPTURED))),
                listOf(widget(IPHONE_SMALL, source.current(false, CAPTURED)), widget(IPHONE_MEDIUM, source.current(true, CAPTURED))),
                listOf(widget(IPHONE_SMALL, unplaced.current(false, CAPTURED)), widget(IPHONE_MEDIUM, unplaced.current(true, CAPTURED))),
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

    private val parcels = listOf(
        widgetParcel(1, "Belgian chocolate 🍫", carrier = "FedEx", outForDelivery = true),
        widgetParcel(2, "Trail weekend kit 🏕️", carrier = "DPD Switzerland", detail = "tomorrow"),
        widgetParcel(3, "Desk lamp", detail = "Friday"),
    )

    /**
     * Words, before the app has drawn the cards: three parcels, so with the next button; a parcel delivered today; one
     * parcel alone across a medium widget.
     */
    private fun words(dark: Boolean) {
        val delivered = widgetParcel(4, "Moon lamp", carrier = "Swiss Post", detail = "Delivered today", deliveredAt = CAPTURED.minus(Duration.ofHours(2)))
        shoot(
            if (dark) "words_dark" else "words_light", dark,
            listOf(
                listOf(widget(IPHONE_SMALL, wordsEntry(parcels, medium = false)), widget(IPHONE_MEDIUM, wordsEntry(parcels, medium = true))),
                listOf(widget(IPHONE_SMALL, wordsEntry(listOf(delivered), medium = false)), widget(IPHONE_MEDIUM, wordsEntry(parcels.drop(1).take(1), medium = true))),
            ),
        )
    }

    @Test
    fun wordsLight() = words(dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun wordsDark() = words(dark = true)

    /** Nothing on its way, and the widget switched off in the app. */
    private fun states(dark: Boolean) = shoot(
        if (dark) "states_dark" else "states_light", dark,
        listOf(
            listOf(widget(IPHONE_SMALL, wordsEntry(emptyList(), medium = false)), widget(IPHONE_MEDIUM, wordsEntry(emptyList(), medium = true))),
            listOf(widget(IPHONE_SMALL, wordsEntry(parcels, medium = false, enabled = false)), widget(IPHONE_MEDIUM, wordsEntry(parcels, medium = true, enabled = false))),
        ),
    )

    /**
     * The gallery's preview before Android 15, and a widget's first look until it has drawn (widget_preview), applied as
     * a launcher applies it, which inflates only the views RemoteViews allows: at the iPhone's small size, a phone's
     * two-by-two and a tablet's.
     */
    private fun staticPreview(dark: Boolean) = shoot(
        if (dark) "static_preview_dark" else "static_preview_light", dark,
        listOf(listOf(IPHONE_SMALL, DpSize(179.dp, 208.dp)), listOf(DpSize(304.dp, 233.dp))).map { row ->
            row.map { Shown(RemoteViews(app.packageName, R.layout.widget_preview), it) }
        },
    )

    @Test
    fun staticPreviewLight() = staticPreview(dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun staticPreviewDark() = staticPreview(dark = true)

    @Test
    fun statesLight() = states(dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun statesDark() = states(dark = true)

    /**
     * At the phone's largest font scale, which is WidgetKit's largest text size for a widget (xxLarge): the names and
     * status lines shrink to fit, as `minimumScaleFactor` shrinks them, and the last row's long names are shrunk as far
     * as they go and end in an ellipsis.
     */
    @Test
    @Config(fontScale = 2.0f)
    fun largestText() {
        val folder = files.newFolder("WidgetCards")
        val source = widgetSource(app, folder)
        val long = listOf(
            widgetParcel(5, "Wanderschuh-Imprägnierspray", carrier = "Swiss Post", detail = "tomorrow"),
            widgetParcel(6, "Mechanical keyboard switches", carrier = "DPD Switzerland", detail = "Friday"),
        )
        shoot(
            "largest_text", dark = false,
            listOf(
                listOf(widget(IPHONE_SMALL, wordsEntry(parcels, medium = false)), widget(IPHONE_MEDIUM, wordsEntry(parcels, medium = true))),
                listOf(widget(IPHONE_SMALL, source.placeholder(false, CAPTURED)), widget(IPHONE_MEDIUM, source.placeholder(true, CAPTURED))),
                listOf(widget(IPHONE_SMALL, wordsEntry(emptyList(), medium = false)), widget(IPHONE_MEDIUM, wordsEntry(parcels, medium = true, enabled = false))),
                listOf(widget(IPHONE_SMALL, wordsEntry(long, medium = false)), widget(IPHONE_MEDIUM, wordsEntry(long, medium = true))),
            ),
        )
    }

    /**
     * At the sizes a launcher gives a two-by-two widget on a phone, upright (130 × 220) and on its side (269 × 117), a
     * three-by-two upright (203 × 220), and a two-by-two on a ten-inch tablet on its side (396 × 244): the cards, then
     * words and nothing on its way.
     */
    private fun sizes(name: String) {
        val folder = files.newFolder("WidgetCards")
        val store = DeliveryWidgetSharedStore(MemoryDefaults())
        val source = widgetSource(app, folder, store)
        runBlocking { publishDemo(app, store, folder, scale = app.resources.displayMetrics.density.toDouble()) }
        fun cards(size: DpSize) = widget(size, source.current(NextDeliveryWidget.isMedium(size), CAPTURED))
        fun words(size: DpSize) = widget(size, wordsEntry(parcels, NextDeliveryWidget.isMedium(size)))
        shoot(
            name, dark = false,
            listOf(
                listOf(cards(LAUNCHER_UPRIGHT), cards(LAUNCHER_UPRIGHT_WIDE), cards(LAUNCHER_ON_ITS_SIDE)),
                listOf(cards(LAUNCHER_TABLET), words(LAUNCHER_ON_ITS_SIDE)),
                listOf(words(LAUNCHER_UPRIGHT), words(LAUNCHER_UPRIGHT_WIDE), widget(LAUNCHER_UPRIGHT, wordsEntry(emptyList(), medium = false))),
                listOf(words(LAUNCHER_TABLET), widget(LAUNCHER_ON_ITS_SIDE, wordsEntry(emptyList(), medium = true))),
            ),
        )
    }

    @Test
    @Config(qualifiers = "w720dp-h1700dp-xhdpi")
    fun sizesLight() = sizes("sizes_light")

    /** The same sizes at the largest text. */
    @Test
    @Config(qualifiers = "w720dp-h1700dp-xhdpi", fontScale = 2.0f)
    fun sizesLargestText() = sizes("sizes_largest_text")
}
