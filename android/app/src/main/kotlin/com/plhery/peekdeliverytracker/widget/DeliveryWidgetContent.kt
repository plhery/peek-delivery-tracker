package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.semantics.contentDescription
import androidx.glance.semantics.semantics
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetEntry
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.ui.draw.drawPeekMark
import com.plhery.peekdeliverytracker.ui.symbols.SymbolRenderingMode
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.symbols.drawSymbol
import com.plhery.peekdeliverytracker.ui.symbols.materialSymbols
import kotlin.math.roundToInt

// DeliveryWidget.swift's DeliveryWidgetView and DeliveryWidgetCards.swift: what the widget shows, in Glance's
// RemoteViews. Text is in sp at the iPhone's sizes for its default text size (Dynamic Type's Large), so the phone's
// font scale grows it as Dynamic Type grows the iPhone's; Glance has three weights (semibold is Medium, heavy Bold),
// no tracking and no shrinking to fit, so a line too long ends in an ellipsis.

/** `WidgetPalette`: the widget's own colours when it writes words, the same light and dark. */
internal object WidgetPalette {
    val accent = Color(0.96f, 0.73f, 0.13f)
    val accentBright = Color(1f, 0.84f, 0.34f)
    val ink = Color(0.10f, 0.10f, 0.09f)
}

/** `DeliveryWidgetCardColors`: the light and the dark one of a card's pair of hex colours, as the launcher's mode picks. */
internal object WidgetCardColors {
    fun color(hex: String): Color {
        val value = hex.removePrefix("#").toLongOrNull(16) ?: 0
        return Color((value shr 16 and 255).toInt(), (value shr 8 and 255).toInt(), (value and 255).toInt())
    }

    fun pair(pair: List<String>, alpha: Float = 1f): ColorProvider {
        val light = color(pair.firstOrNull() ?: "#000000").copy(alpha = alpha)
        return ColorProvider(day = light, night = pair.getOrNull(1)?.let { color(it).copy(alpha = alpha) } ?: light)
    }

    /** The page the app's list of cards stands on. */
    val page: ColorProvider = pair(listOf("#F4F5F1", "#151915"))

    /** `Color.primary`: the label's colour on a card. */
    val primary: ColorProvider = ColorProvider(day = Color.Black, night = Color.White)
}

/** A card's pictures as the widget reads them: its mark, and its route as the widget's shape shows it, light and dark. */
class WidgetCardArt(val mark: Pair<Bitmap, Bitmap>, val route: Pair<Bitmap, Bitmap>?)

/** The widget's next button reaches past what it shows, so that a finger finds it (`DeliveryWidgetNextMark`). */
private object NextMark {
    val size = 18.dp
    val reach = 9.dp
}

/**
 * `DeliveryWidgetView`: the cards once the app has drawn every one the widget shows, else words. `medium` is the
 * widget's size (the iPhone's systemMedium family). A tap opens the parcel shown, or the app where none is.
 */
@Composable
fun DeliveryWidgetContent(entry: DeliveryWidgetEntry<WidgetCardArt>, medium: Boolean) {
    val copy = remember(entry.languageCode) { ExtensionLocalizer(entry.languageCode) }
    val cards = entry.cards
    if (cards.isEmpty()) Words(entry, medium, copy) else Cards(entry, cards, medium, copy)
}

@Composable
private fun Cards(
    entry: DeliveryWidgetEntry<WidgetCardArt>,
    cards: List<Triple<DeliveryWidgetParcel, DeliveryWidgetCard, WidgetCardArt>>,
    medium: Boolean,
    copy: ExtensionLocalizer,
) {
    val context = LocalContext.current
    val only = cards.singleOrNull()
    if (only != null) {
        val shape = if (medium) DeliveryWidgetPicture.Wide else DeliveryWidgetPicture.Small
        val (parcel, card, art) = only
        Box(root().background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))) {
            CardBackground(art)
            Box(GlanceModifier.fillMaxSize().padding(MARGIN)) {
                CardView(parcel, card, art, shape, leavesNext = entry.next != null, copy)
            }
            NextButton(entry, medium, card.ink, copy, top = MARGIN + markHeight(shape) / 2, end = MARGIN + NextMark.reach)
        }
    } else {
        Box(root().background(WidgetCardColors.page).clickable(openApp(context))) {
            Row(GlanceModifier.fillMaxSize().padding(MARGIN)) {
                cards.forEachIndexed { index, (parcel, card, art) ->
                    if (index > 0) Spacer(GlanceModifier.width(8.dp))
                    Box(
                        GlanceModifier.defaultWeight().fillMaxHeight().cornerRadius(14.dp).background(WidgetCardColors.pair(card.surface))
                            .clickable(openParcel(context, parcel)),
                    ) {
                        CardBackground(art)
                        Box(GlanceModifier.fillMaxSize().padding(10.dp)) {
                            CardView(
                                parcel, card, art, DeliveryWidgetPicture.Half,
                                leavesNext = entry.next != null && index == cards.lastIndex, copy,
                            )
                        }
                    }
                }
            }
            // Over the last card, outside its link.
            NextButton(
                entry, medium, cards.last().second.ink, copy, top = MARGIN + 10.dp + markHeight(DeliveryWidgetPicture.Half) / 2,
                end = MARGIN + 10.dp + NextMark.reach,
            )
        }
    }
}

/** `DeliveryWidgetCardBackground`: the card's route across its top, as wide as the card. */
@Composable
private fun CardBackground(art: WidgetCardArt) {
    val route = art.route ?: return
    DayNightPicture(route, R.layout.widget_route, GlanceModifier.fillMaxSize(), description = null)
}

/**
 * `DeliveryWidgetCardView`: a parcel's card as the app's list shows it, in its carrier's colours: the mark at the top,
 * over the route, and the parcel's name and status at the bottom.
 */
@Composable
private fun CardView(
    parcel: DeliveryWidgetParcel,
    card: DeliveryWidgetCard,
    art: WidgetCardArt,
    shape: DeliveryWidgetPicture,
    leavesNext: Boolean,
    copy: ExtensionLocalizer,
) {
    val status = if (card.status == parcel.detail) card.status else "${card.status} · ${parcel.detail}"
    val half = shape == DeliveryWidgetPicture.Half
    Column(GlanceModifier.fillMaxSize()) {
        Row(GlanceModifier.fillMaxWidth()) {
            DayNightPicture(art.mark, R.layout.widget_mark, GlanceModifier.height(markHeight(shape)), description = parcel.carrier)
            Spacer(GlanceModifier.defaultWeight())
            // The end of the top row is left to the widget's next button.
            if (leavesNext) Spacer(GlanceModifier.width(NextMark.size + 8.dp))
        }
        Spacer(GlanceModifier.height(4.dp))
        Spacer(GlanceModifier.defaultWeight())
        Text(
            parcel.label, maxLines = 2,
            style = TextStyle(
                color = WidgetCardColors.primary, fontWeight = FontWeight.Medium,
                fontSize = (if (half) SUBHEADLINE else if (shape == DeliveryWidgetPicture.Wide) TITLE3 else HEADLINE).sp,
            ),
        )
        Spacer(GlanceModifier.height(if (half) 2.dp else 3.dp))
        Text(status, maxLines = 1, style = TextStyle(color = WidgetCardColors.pair(card.ink), fontSize = (if (half) CAPTION2 else CAPTION).sp))
    }
}

/**
 * `words`: the widget's own yellow, with the next parcels, or why there are none: the widget switched off in the app,
 * or nothing on its way. A small widget opens its parcel; a medium one each of its two.
 */
@Composable
private fun Words(entry: DeliveryWidgetEntry<WidgetCardArt>, medium: Boolean, copy: ExtensionLocalizer) {
    val context = LocalContext.current
    val parcels = entry.shown
    val tap = if (!medium && entry.enabled) parcels.firstOrNull()?.let { openParcel(context, it) } else null
    Box(root().background(ImageProvider(R.drawable.widget_words_background)).clickable(tap ?: openApp(context))) {
        Box(GlanceModifier.fillMaxSize().padding(MARGIN)) {
            when {
                !entry.enabled -> StateView("rectangle.slash", copy.text("widget.disabledTitle"), copy.onAndroid("widget.disabledDescription"), medium)
                parcels.isEmpty() -> StateView("shippingbox", copy.text("app.noneOnWay"), copy.text("app.emptyDescription"), medium)
                medium -> MediumContent(parcels, copy)
                else -> SmallContent(parcels.first(), copy)
            }
        }
        if (entry.enabled && parcels.isNotEmpty()) {
            NextButton(entry, medium, null, copy, top = MARGIN + HEADER_MARK / 2, end = MARGIN + NextMark.reach)
        }
    }
}

/**
 * Inside the margins, the smallest widgets still fit a two-line name. Grouped, as a layout of Glance's shows ten
 * children at most.
 */
@Composable
private fun SmallContent(parcel: DeliveryWidgetParcel, copy: ExtensionLocalizer) {
    Column(GlanceModifier.fillMaxSize()) {
        Header(copy)
        Spacer(GlanceModifier.height(6.dp))
        Spacer(GlanceModifier.defaultWeight())
        Column {
            Badge(parcel, copy)
            Spacer(GlanceModifier.height(3.dp))
            Text(parcel.label, maxLines = 2, style = words(HEADLINE, FontWeight.Bold))
        }
        Spacer(GlanceModifier.height(6.dp))
        Spacer(GlanceModifier.defaultWeight())
        Column {
            Text(parcel.carrier, maxLines = 1, style = words(CAPTION, FontWeight.Medium, 0.68f))
            Spacer(GlanceModifier.height(1.dp))
            Text(parcel.detail, maxLines = 1, style = words(CAPTION, FontWeight.Bold))
        }
    }
}

@Composable
private fun MediumContent(parcels: List<DeliveryWidgetParcel>, copy: ExtensionLocalizer) {
    val context = LocalContext.current
    Column(GlanceModifier.fillMaxSize()) {
        Header(copy)
        Spacer(GlanceModifier.height(8.dp))
        Row(GlanceModifier.fillMaxWidth().defaultWeight()) {
            parcels.take(2).forEachIndexed { index, parcel ->
                if (index > 0) Spacer(GlanceModifier.width(10.dp))
                Column(
                    GlanceModifier.defaultWeight().fillMaxHeight().cornerRadius(14.dp).background(ColorProvider(Color.White.copy(alpha = 0.36f)))
                        .clickable(openParcel(context, parcel)).padding(horizontal = 10.dp, vertical = 8.dp),
                ) {
                    Badge(parcel, copy)
                    Spacer(GlanceModifier.height(3.dp))
                    Text(parcel.label, maxLines = 1, style = words(SUBHEADLINE, FontWeight.Bold))
                    Spacer(GlanceModifier.height(3.dp))
                    Text(parcel.carrier, maxLines = 1, style = words(CAPTION2, FontWeight.Medium, 0.64f))
                    Spacer(GlanceModifier.height(3.dp))
                    Text(parcel.detail, maxLines = 1, style = words(CAPTION, FontWeight.Bold))
                }
            }
            if (parcels.size == 1) {
                Spacer(GlanceModifier.width(10.dp))
                Spacer(GlanceModifier.defaultWeight())
            }
        }
    }
}

/**
 * `widgetHeader`: the app's mark and name, as tall as the name where the phone's font scale makes it taller than the
 * mark. The next button is level with the mark at the default size, as the iPhone's is (`deliveryWidgetNext`).
 */
@Composable
private fun Header(copy: ExtensionLocalizer) {
    val context = LocalContext.current
    val mark = remember { peekMark(context) }
    Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(ImageProvider(mark), contentDescription = null, modifier = GlanceModifier.size(HEADER_MARK))
        Spacer(GlanceModifier.width(6.dp))
        Text(copy.text("app.title").uppercase(ExtensionLocalizer.locale(copy.languageCode)), maxLines = 1, style = words(CAPTION2, FontWeight.Bold, 0.72f))
    }
}

/** `parcelBadge`: out for delivery, delivered or on its way. Tighter than a Label, so "Out for delivery" fits a small widget. */
@Composable
private fun Badge(parcel: DeliveryWidgetParcel, copy: ExtensionLocalizer) {
    val delivered = parcel.deliveredAt != null
    val symbol = if (parcel.isOutForDelivery) "bicycle" else if (delivered) "checkmark.circle.fill" else "arrow.right.circle.fill"
    val key = if (parcel.isOutForDelivery) "stage.out_for_delivery" else if (delivered) "stage.delivered" else "app.onTheWaySection"
    Row(verticalAlignment = Alignment.CenterVertically) {
        Symbol(symbol, CAPTION2, ComposeFontWeight.Black, WidgetPalette.ink, SymbolScale.Small)
        Spacer(GlanceModifier.width(4.dp))
        Text(copy.text(key).uppercase(ExtensionLocalizer.locale(copy.languageCode)), maxLines = 1, style = words(CAPTION2, FontWeight.Bold))
    }
}

/** `stateView`: where space runs out, the description gives up lines before the title does. */
@Composable
private fun StateView(symbol: String, title: String, description: String, medium: Boolean) {
    Column(GlanceModifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
        Symbol(symbol, TITLE3, ComposeFontWeight.Bold, WidgetPalette.ink)
        Spacer(GlanceModifier.height(6.dp))
        Text(title, maxLines = 2, style = words(HEADLINE, FontWeight.Bold))
        Spacer(GlanceModifier.height(6.dp))
        Text(description, maxLines = if (medium) 2 else 3, style = words(CAPTION, FontWeight.Normal, 0.67f))
    }
}

/**
 * The next button, shown only when there are more parcels than the widget shows at once: a small chevron in the
 * card's ink (the widget's ink over words), level with the card's mark, `top` and `end` its centre from the widget's
 * top and end. It turns the widgets of its size to the next parcels (`ShowNextParcelIntent`).
 */
@Composable
private fun NextButton(entry: DeliveryWidgetEntry<WidgetCardArt>, medium: Boolean, ink: List<String>?, copy: ExtensionLocalizer, top: Dp, end: Dp) {
    val next = entry.next ?: return
    val context = LocalContext.current
    val box = NextMark.size + NextMark.reach * 2
    val art = remember(ink) { nextMark(context, ink) }
    val label = copy.text("app.nextParcel")
    Box(GlanceModifier.fillMaxSize().padding(top = top - box / 2, end = end - box / 2), contentAlignment = Alignment.TopEnd) {
        Box(
            GlanceModifier.size(box).semantics { contentDescription = label }
                .clickable(actionRunCallback<ShowNextParcelAction>(ShowNextParcelAction.parameters(next, DeliveryWidgetTimeline.turnSize(medium)))),
        ) {
            DayNightPicture(art, R.layout.widget_symbol, GlanceModifier.size(box), description = null)
        }
    }
}

/** An SF Symbol as the app draws it, at `points` grown with the phone's font scale, in `tint`. */
@Composable
private fun Symbol(name: String, points: Float, weight: ComposeFontWeight, tint: Color, scale: SymbolScale = SymbolScale.Medium) {
    val context = LocalContext.current
    val size = points * context.resources.configuration.fontScale
    val art = requireNotNull(materialSymbols[name])
    val box = art.box(size, scale.factor)
    val bitmap = remember(name, size, tint) {
        bitmap(context, box[0], box[1]) { drawSymbol(art, size * scale.factor, weight.weight, tint, SymbolRenderingMode.Monochrome) }
    }
    Image(ImageProvider(bitmap), contentDescription = null, modifier = GlanceModifier.width(box[0].dp).height(box[1].dp))
}

/**
 * A picture in its light and dark forms, which the launcher swaps as the phone's mode changes, with no word from the
 * app: an image view of `layout` (widget_route, widget_mark, widget_symbol) given both.
 */
@Composable
private fun DayNightPicture(picture: Pair<Bitmap, Bitmap>, @LayoutRes layout: Int, modifier: GlanceModifier, description: String?) {
    val context = LocalContext.current
    val views = RemoteViews(context.packageName, layout).apply {
        setIcon(R.id.widget_picture, "setImageIcon", Icon.createWithBitmap(picture.first), Icon.createWithBitmap(picture.second))
        setContentDescription(R.id.widget_picture, description)
    }
    AndroidRemoteViews(views, modifier)
}

/** `widgetURL` and `Link`: the parcel on the deliveries, through the app's routing (NativeRoute), in the app's window. */
internal fun openParcel(context: Context, parcel: DeliveryWidgetParcel): Action =
    actionStartActivity(Intent(Intent.ACTION_VIEW, parcel.deepLink.toUri(), context, MainActivity::class.java))

/** A widget with no link opens the app, as WidgetKit opens it. */
internal fun openApp(context: Context): Action =
    actionStartActivity(Intent(context, MainActivity::class.java).setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER))

/** The widget's whole surface, its corners the launcher's, as a widget's background is. */
private fun root(): GlanceModifier =
    GlanceModifier.fillMaxSize().appWidgetBackground().cornerRadius(android.R.dimen.system_app_widget_background_radius)

/** The copy as Android words it where it names the iPhone's things (its `android` variant: "home screen"), else as it is. */
internal fun ExtensionLocalizer.onAndroid(key: String): String = text("$key.android").takeIf { it != "$key.android" } ?: text(key)

private fun words(points: Float, weight: FontWeight, opacity: Float = 1f) =
    TextStyle(color = ColorProvider(WidgetPalette.ink.copy(alpha = opacity)), fontSize = points.sp, fontWeight = weight)

private fun markHeight(shape: DeliveryWidgetPicture): Dp = if (shape == DeliveryWidgetPicture.Half) 13.dp else 15.dp

/** The app's mark at the header's size. */
private fun peekMark(context: Context): Bitmap = bitmap(context, HEADER_MARK.value, HEADER_MARK.value) { drawPeekMark(HEADER_MARK.value.toDouble()) }

/**
 * `DeliveryWidgetNextMark`: a chevron at 8 points bold in the ink at 60%, in a circle of the ink at 7%, with its reach
 * round it; light and dark from the card's pair, or the widget's own ink.
 */
private fun nextMark(context: Context, ink: List<String>?): Pair<Bitmap, Bitmap> {
    val art = requireNotNull(materialSymbols["chevron.forward"])
    val box = art.box(8f)
    val side = (NextMark.size + NextMark.reach * 2).value
    fun draw(color: Color) = bitmap(context, side, side) {
        drawCircle(color.copy(alpha = 0.07f), radius = NextMark.size.value / 2, center = Offset(side / 2, side / 2))
        drawContext.transform.translate((side - box[0]) / 2, (side - box[1]) / 2)
        drawSymbol(art, 8f, ComposeFontWeight.Bold.weight, color.copy(alpha = 0.6f), SymbolRenderingMode.Monochrome)
    }
    val light = ink?.firstOrNull()?.let(WidgetCardColors::color) ?: WidgetPalette.ink
    val dark = ink?.getOrNull(1)?.let(WidgetCardColors::color) ?: light
    return draw(light) to draw(dark)
}

/** A transparent picture `width` × `height` dp at the screen's density, drawn one unit to the dp but for [drawSymbol]'s own density. */
private fun bitmap(context: Context, width: Float, height: Float, draw: DrawScope.() -> Unit): Bitmap {
    val density = context.resources.displayMetrics.density
    val pixelsWide = (width * density).roundToInt().coerceAtLeast(1)
    val pixelsHigh = (height * density).roundToInt().coerceAtLeast(1)
    val image = ImageBitmap(pixelsWide, pixelsHigh, ImageBitmapConfig.Argb8888)
    CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, Canvas(image), androidx.compose.ui.geometry.Size(pixelsWide.toFloat(), pixelsHigh.toFloat())) {
        scale(density, density, pivot = Offset.Zero) { draw() }
    }
    return image.asAndroidBitmap()
}

private val MARGIN = 16.dp
private val HEADER_MARK = 16.dp
private const val CAPTION2 = 11f
private const val CAPTION = 12f
private const val SUBHEADLINE = 15f
private const val HEADLINE = 17f
private const val TITLE3 = 20f
