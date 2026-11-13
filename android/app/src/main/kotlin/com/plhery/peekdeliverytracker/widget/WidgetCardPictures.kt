package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.DistanceUnit
import com.plhery.peekdeliverytracker.core.CarrierTruckGeometry
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.DeliveryWidgetArtist
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.MapDrawingContext
import com.plhery.peekdeliverytracker.core.MapLabels
import com.plhery.peekdeliverytracker.core.MapPalette
import com.plhery.peekdeliverytracker.core.ParcelRoute
import com.plhery.peekdeliverytracker.core.PipArtwork
import com.plhery.peekdeliverytracker.core.PipGeometry
import com.plhery.peekdeliverytracker.core.PipMood
import com.plhery.peekdeliverytracker.core.PipPalette
import com.plhery.peekdeliverytracker.core.PipRequest
import com.plhery.peekdeliverytracker.core.WorldAtlas
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.hasMappedPlace
import com.plhery.peekdeliverytracker.ui.draw.drawMapDrawings
import com.plhery.peekdeliverytracker.ui.map.MapSceneRequest
import com.plhery.peekdeliverytracker.ui.map.MapScenes
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import com.plhery.peekdeliverytracker.ui.theme.italic
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.toColor
import java.io.File
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

/**
 * `DeliveryWidgetPictures.draw`: a card's pictures, drawn by the app for its widget, which has the memory to show them
 * but not to draw the map. For each shape of card (a small widget, half a medium one, a whole medium one), light and
 * dark: the route in the card's ink as the parcel's card draws it (`RouteEngraving`, without its pulse, which a picture
 * can't keep), Pip beside the parcel's place, faded in from under the card's top row; Pip alone where the parcel has
 * no places. Then the carrier's mark (`CarrierFleetMark`), last, so that a card whose mark is there has all of them.
 * At the scale the card was named for (its `scale`: the screen's pixels per dp as [scale] read it, as the iPhone draws
 * them at its screen's scale), the text at the default size (Dynamic Type's Large, which the iPhone draws them at),
 * into the app's caches, which the widget, part of the app, reads.
 */
class WidgetCardPictures(
    private val context: Context,
    override val folder: File = folder(context),
    /** A scale of its own (a test's), in place of the screen's. */
    scale: Double? = null,
    /** The world the routes are drawn on: the bundled atlas, read the first time a route is drawn. */
    private val atlas: suspend () -> WorldAtlas? = { WorldAtlas.bundled() },
) : DeliveryWidgetArtist {
    private val fixedScale = scale

    /**
     * The screen's pixels per dp, read each time: the display size setting, or a foldable's other screen, changes it
     * while the app runs, and the cards are then named and drawn again for the new one.
     */
    override val scale: Double get() = fixedScale ?: context.resources.displayMetrics.density.toDouble()

    override suspend fun draw(parcel: Parcel, card: DeliveryWidgetCard, language: AppLanguage, distanceUnit: DistanceUnit): Boolean = withContext(Dispatchers.Default) {
        val scale = card.scale
        try {
            if (!folder.isDirectory && !folder.mkdirs()) return@withContext false
            // The list's card: the carrier the parcel came with, after a handover too.
            val identity = CarrierVisualIdentity.of(parcel.displayedCarrier, language = language)
            val world = if (parcel.hasMappedPlace) atlas() else null
            for (dark in listOf(false, true)) {
                for (picture in DeliveryWidgetPicture.routes) {
                    val frame = DeliveryWidgetPictures.frame(picture)
                    val drawn = if (world != null) {
                        route(parcel, identity, world, frame, dark, language, scale, distanceUnit)
                    } else {
                        // With no route to draw, Pip still stands where he would be.
                        lonePip(PipMood(parcel.currentStage) ?: PipMood.Look, identity, frame, dark, scale)
                    }
                    if (!write(drawn, picture.file(card, dark, folder))) return@withContext false
                    yield()
                }
            }
            for (dark in listOf(false, true)) {
                if (!write(mark(identity, dark, language, scale), DeliveryWidgetPicture.Mark.file(card, dark, folder))) return@withContext false
            }
            true
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (_: Exception) {
            false
        }
    }

    /** `RouteEngraving` at the frame's size, its marks faded toward the bottom, the whole faded in from under the top row. */
    private suspend fun route(
        parcel: Parcel,
        identity: CarrierVisualIdentity,
        atlas: WorldAtlas,
        frame: DeliveryWidgetPictures.Frame,
        dark: Boolean,
        language: AppLanguage,
        scale: Double,
        distanceUnit: DistanceUnit,
    ): Bitmap {
        val route = ParcelRoute(parcel, atlas, language)
        val stage = parcel.currentStage
        val request = MapSceneRequest(
            atlas, route, route.defaultMode(stage), MapPalette.tint(identity.ink, identity.surface), MapLabels.Ends, sites = false,
            showsContext = false, night = null, pip = PipMood(stage)?.let { PipRequest(it, ceiling = frame.ceiling, floor = frame.floor) },
            quiet = false, insets = frame.insets, covered = null, buttons = null, language = language, width = frame.size.width,
            height = frame.size.height, dark = dark, highContrast = false, detail = null, distanceUnit = distanceUnit,
        )
        val scene = MapScenes.made(request, context).await()
        val width = frame.size.width.toFloat()
        val height = frame.size.height.toFloat()
        return picture(frame.size.width, frame.size.height, scale) {
            layer(width, height) {
                layer(width, height) {
                    drawContext.canvas.nativeCanvas.drawPicture(scene.base)
                    drawContext.canvas.nativeCanvas.drawPicture(scene.marks)
                    drawRect(Brush.verticalGradient(0.78f to Color.Black, 1f to Color.Transparent, endY = height), blendMode = BlendMode.DstIn)
                }
                scene.pip?.let { place -> drawPip(place.mood, place.side, place.below, place.origin.x, place.origin.y, place.width, identity, dark) }
                val fade = Brush.verticalGradient(
                    (frame.fade.start / frame.size.height).toFloat() to Color.Transparent,
                    (frame.fade.endInclusive / frame.size.height).toFloat() to Color.Black,
                    endY = height,
                )
                drawRect(fade, blendMode = BlendMode.DstIn)
            }
        }
    }

    /**
     * `LonePip`: Pip across the top of a card whose parcel has no places, as big as he fits between the card's top row
     * and its words, at its trailing end, looking back at them.
     */
    private fun lonePip(mood: PipMood, identity: CarrierVisualIdentity, frame: DeliveryWidgetPictures.Frame, dark: Boolean, scale: Double): Bitmap {
        val side = -1
        val extents = PipGeometry.extents(mood, side)
        val room = frame.floor - frame.ceiling
        val width = mood.widths.firstOrNull { extents.height * it / PipGeometry.frame.width <= room } ?: mood.widths.last()
        val unit = width / PipGeometry.frame.width
        val x = frame.size.width - frame.insets.trailing - extents.maxX * unit
        val y = frame.ceiling + (room - extents.height * unit) / 2 - extents.minY * unit
        return picture(frame.size.width, frame.size.height, scale) { drawPip(mood, side, false, x, y, width, identity, dark) }
    }

    /** `InkPip`, still: his frame's top left at (`x`, `y`), `width` wide, in the card's ink. */
    private fun DrawScope.drawPip(mood: PipMood, side: Int, below: Boolean, x: Double, y: Double, width: Double, identity: CarrierVisualIdentity, dark: Boolean) {
        val palette = PipPalette.ink(AdaptiveColor(identity.ink.resolve(dark)), AdaptiveColor(identity.surface.resolve(dark)), dark)
        val drawing = MapDrawingContext().also { PipArtwork.ink(it, palette, mood, side, below, time = null) }.drawing
        val unit = (width / PipGeometry.frame.width).toFloat()
        translate(x.toFloat(), y.toFloat()) {
            scale(unit, unit, pivot = Offset.Zero) { drawMapDrawings(drawing, dark) }
        }
    }

    /**
     * `CarrierFleetMark`, at its own size: the truck, 27 × 18, then 7 points on the carrier's name in caption bold, in
     * its colour, DHL's in italics, GLS's with its full stop.
     */
    private fun mark(identity: CarrierVisualIdentity, dark: Boolean, language: AppLanguage, scale: Double): Bitmap {
        val density = Density(scale.toFloat(), fontScale = 1f)
        val type = PeekTypography(DynamicTypeSize.Large, density, language)
        val style = type.style(IosTextStyle.Caption, FontWeight.Bold).sfLines().let { if (identity.family == "dhl") it.italic() else it }
            .copy(color = identity.brand.resolve(dark).toColor())
        val measurer = TextMeasurer(createFontFamilyResolver(context), density, LayoutDirection.Ltr)
        val text = measurer.measure(identity.name + if (identity.family == "gls") "." else "", style, softWrap = false)
        val truck = CarrierTruckGeometry.draw(identity, dark)
        val textWidth = text.size.width / scale
        val textHeight = text.size.height / scale
        val height = maxOf(TRUCK_HEIGHT, textHeight)
        return picture(ceil((TRUCK_WIDTH + GAP + textWidth) * scale) / scale, height, scale) {
            val box = CarrierTruckGeometry.viewBox
            translate(0f, ((height - TRUCK_HEIGHT) / 2).toFloat()) {
                scale((TRUCK_WIDTH / box.width).toFloat(), (TRUCK_HEIGHT / box.height).toFloat(), pivot = Offset.Zero) { drawMapDrawings(truck, dark) }
            }
            // The text in pixels, as it was measured.
            scale(1 / scale.toFloat(), 1 / scale.toFloat(), pivot = Offset.Zero) {
                drawText(text, topLeft = Offset(((TRUCK_WIDTH + GAP) * scale).toFloat(), ((height - textHeight) / 2 * scale).toFloat()))
            }
        }
    }

    /** A transparent picture `width` × `height` points at `scale` pixels to the point, drawn one unit to the point. */
    private fun picture(width: Double, height: Double, scale: Double, draw: DrawScope.() -> Unit): Bitmap {
        val pixels = androidx.compose.ui.geometry.Size((width * scale).roundToInt().toFloat(), (height * scale).roundToInt().toFloat())
        val image = ImageBitmap(pixels.width.toInt(), pixels.height.toInt(), ImageBitmapConfig.Argb8888)
        CanvasDrawScope().draw(Density(scale.toFloat()), LayoutDirection.Ltr, Canvas(image), pixels) {
            scale(scale.toFloat(), scale.toFloat(), pivot = Offset.Zero) { draw() }
        }
        return image.asAndroidBitmap()
    }

    /** What is drawn in `draw`, composited onto what is under it as one layer, so that a fade masks it alone. */
    private fun DrawScope.layer(width: Float, height: Float, draw: DrawScope.() -> Unit) {
        drawContext.canvas.saveLayer(Rect(0f, 0f, width, height), Paint())
        draw()
        drawContext.canvas.restore()
    }

    private fun write(bitmap: Bitmap, file: File): Boolean {
        val written = File(file.parentFile, "${file.name}.part")
        val saved = written.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        return saved && written.renameTo(file)
    }

    companion object {
        private const val TRUCK_WIDTH = 27.0
        private const val TRUCK_HEIGHT = 18.0
        private const val GAP = 7.0

        /**
         * `DeliveryWidgetPicture.folder`: where the cards' pictures are kept, which the widget reads them from. In the
         * app's caches, as the iPhone keeps them in its App Group's Library/Caches: out of backups, and drawn again
         * at the next publishing if the system clears them, the widget showing words until then.
         */
        fun folder(context: Context): File = File(context.cacheDir, "WidgetCards")
    }
}
