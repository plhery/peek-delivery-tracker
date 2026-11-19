package com.plhery.peekdeliverytracker.widget

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color as AndroidColor
import android.graphics.LinearGradient
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.AndroidRemoteViews
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
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.listShortStatus
import com.plhery.peekdeliverytracker.core.listStatus
import com.plhery.peekdeliverytracker.core.openingLine
import com.plhery.peekdeliverytracker.ui.symbols.drawSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.math.sqrt

// DeliveryWidgetCards.swift: the widget's cards, in Glance's RemoteViews, at the size the launcher gives it. Text is
// iOS's text styles at the phone's text size within WidgetKit's range, shrunk to fit where the iPhone's shrinks
// ([WidgetText]); Glance has three weights (semibold is Medium, heavy Bold) and no tracking. Glance's layouts don't give
// up a line where room runs out, as SwiftUI's do, so each layout works out from the measured text which lines it keeps.

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
    val page: ColorProvider = pair(listOf("#F4F5F1", "#13110E"))

    /** `Color.primary`: the label's colour on a card. */
    val primary: ColorProvider = ColorProvider(day = Color.Black, night = Color.White)
}

/** The narrowest widget that shows two cards side by side, two cards on their side on a phone. */
private val MEDIUM_WIDTH = 250.dp

/**
 * How much wider than tall a widget is from which it is medium: midway, in proportion, between the iPhone's small widget
 * (170 × 170) and its medium one (364 × 170).
 */
private val MEDIUM_SHAPE = sqrt(364f / 170f)

/**
 * Whether a widget `size` big shows its cards as the iPhone's medium widget does, wide enough and wide for its height:
 * two side by side, or one across. A two-by-two widget on a phone is small upright and medium on its side.
 */
fun isMedium(size: DpSize): Boolean = size.width >= MEDIUM_WIDTH && size.width.value >= size.height.value * MEDIUM_SHAPE

/** A card's pictures as the widget reads them: its mark, and its route as the widget's shape shows it, light and dark. */
class WidgetCardArt(val mark: Pair<Bitmap, Bitmap>, val route: Pair<Bitmap, Bitmap>?)

/**
 * `ParcelCards`' one or two cards in a small or medium widget, each a parcel with its card and its pictures: one fills
 * the widget, in the iPhone's systemMedium shape where the widget is wide for its height ([isMedium]); two stand side by
 * side. A card opens its parcel, and the page between two cards opens `page`.
 */
@Composable
internal fun OneOrTwoCards(
    languageCode: String,
    cards: List<Triple<DeliveryWidgetParcel, DeliveryWidgetCard, WidgetCardArt>>,
    size: DpSize,
    type: WidgetText,
    page: Intent,
) {
    val context = LocalContext.current
    val only = cards.singleOrNull()
    if (only != null) {
        val shape = if (isMedium(size)) DeliveryWidgetPicture.Wide else DeliveryWidgetPicture.Small
        val (parcel, card, art) = only
        val words = CardWords(parcel, card, shape, room = size.inside(MARGIN), type, languageCode)
        Box(root().background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))) {
            CardBackground(art, shape, size)
            Box(GlanceModifier.fillMaxSize().padding(MARGIN)) { CardView(parcel, card, art, words) }
        }
    } else {
        val half = DpSize((size.width - MARGIN * 2 - CARD_SPACING) / 2, size.height - MARGIN * 2)
        // The page between the cards opens the list, as WidgetKit's does, but isn't an item of its own for TalkBack, as
        // the iPhone's page isn't one for VoiceOver: its cards are.
        Box(root().background(WidgetCardColors.page)) {
            PageTap(page)
            Row(GlanceModifier.fillMaxSize().padding(MARGIN)) {
                cards.forEachIndexed { index, (parcel, card, art) ->
                    if (index > 0) Spacer(GlanceModifier.width(CARD_SPACING))
                    val words = CardWords(parcel, card, DeliveryWidgetPicture.Half, room = half.inside(CARD_PADDING), type, languageCode)
                    Box(
                        GlanceModifier.defaultWeight().fillMaxHeight().cornerRadius(14.dp).background(WidgetCardColors.pair(card.surface))
                            .clickable(openParcel(context, parcel)),
                    ) {
                        CardBackground(art, DeliveryWidgetPicture.Half, half)
                        Box(GlanceModifier.fillMaxSize().padding(CARD_PADDING)) { CardView(parcel, card, art, words) }
                    }
                }
            }
        }
    }
}

/**
 * A card's words as they fit `room`, the card inside its padding: the label on two lines where the card is tall enough
 * for them, else one, shrunk to 0.82 of its style to fit them, and the status line shrunk to 0.8 to fit its one, past
 * the new-update dot where the parcel has one ([newUpdate]).
 */
internal class CardWords(
    parcel: DeliveryWidgetParcel,
    card: DeliveryWidgetCard,
    val shape: DeliveryWidgetPicture,
    room: DpSize,
    val type: WidgetText,
    languageCode: String,
) {
    val half = shape == DeliveryWidgetPicture.Half
    val markHeight = markHeight(shape)
    val status = if (card.status == parcel.detail) card.status else "${card.status} · ${parcel.detail}"

    /** Whether the status line starts with the new-update dot (#72). */
    val newUpdate = parcel.newUpdate == true
    private val statusWidth = room.width - if (newUpdate) parcel.dotRoom else 0.dp
    val statusPoints = type.fit(status, type.points(if (half) IosTextStyle.Caption2 else IosTextStyle.Caption), FontWeight.Normal, statusWidth, 1, 0.8f)
    val spacing = if (half) 2.dp else 3.dp
    private val labelStyle = if (half) IosTextStyle.Subheadline else if (shape == DeliveryWidgetPicture.Wide) IosTextStyle.Title3 else IosTextStyle.Headline
    private val statusHeight = type.height(status, statusPoints, FontWeight.Normal, statusWidth, 1)

    /** What TalkBack reads for the mark, the card's first words: "New update." before the carrier while the dot shows. */
    val markDescription: String = newUpdateSpoken(parcel, languageCode)?.takeIf { newUpdate }?.let { "$it ${parcel.carrier}" } ?: parcel.carrier
    val label: FittedLine = listOf(2, 1).map { lines -> FittedLine.of(parcel.label, labelStyle, FontWeight.Medium, room.width, lines, 0.82f, type) }
        .firstOrNull { markHeight + 4.dp + it.height + spacing + statusHeight <= room.height } ?: FittedLine.of(parcel.label, labelStyle, FontWeight.Medium, room.width, 1, 0.82f, type)

    /** The width the top row leaves the mark, which shrinks to it as `scaledToFit` shrinks the iPhone's. */
    val markRoom = room.width
}

/** A line of text at the size it fits its room, and how tall it stands. */
internal class FittedLine(val text: String, val points: Float, val lines: Int, val height: Dp) {
    companion object {
        fun of(text: String, style: IosTextStyle, weight: FontWeight, width: Dp, lines: Int, minimumScale: Float, type: WidgetText): FittedLine {
            val points = type.fit(text, type.points(style), weight, width, lines, minimumScale)
            return FittedLine(text, points, lines, type.height(text, points, weight, width, lines))
        }
    }
}

/**
 * `DeliveryWidgetCardBackground`: the card's route across its top, centred, fitting the card's width and height
 * (`scaledToFit`), never larger than it was drawn, which would blur it. On a card wider than that, as Android's widgets
 * can be, its sides fade into the card rather than end in edges.
 */
@Composable
internal fun CardBackground(art: WidgetCardArt, shape: DeliveryWidgetPicture, card: DpSize) {
    val drawn = art.route ?: return
    val frame = DeliveryWidgetPictures.frame(shape)
    val scale = minOf(1.0, card.width.value / frame.size.width, card.height.value / frame.size.height)
    val width = (frame.size.width * scale).dp
    val narrower = width < card.width - 1.dp
    val route = if (narrower) remember(drawn) { fadedSides(drawn.first, frame.size.width) to fadedSides(drawn.second, frame.size.width) } else drawn
    Box(GlanceModifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        DayNightPicture(route, R.layout.widget_route, GlanceModifier.width(width).height((frame.size.height * scale).dp), description = null)
    }
}

/** A copy of `picture`, drawn `points` wide, fading out over its outer [SIDE_FADE] on each side. */
internal fun fadedSides(picture: Bitmap, points: Double): Bitmap {
    val faded = picture.copy(Bitmap.Config.ARGB_8888, true)
    val width = faded.width.toFloat()
    val fade = (SIDE_FADE.value / points).toFloat().coerceAtMost(0.5f)
    val paint = android.graphics.Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        shader = LinearGradient(
            0f, 0f, width, 0f, intArrayOf(AndroidColor.TRANSPARENT, AndroidColor.BLACK, AndroidColor.BLACK, AndroidColor.TRANSPARENT),
            floatArrayOf(0f, fade, 1 - fade, 1f), Shader.TileMode.CLAMP,
        )
    }
    android.graphics.Canvas(faded).drawRect(0f, 0f, width, faded.height.toFloat(), paint)
    return faded
}

/**
 * `DeliveryWidgetCardView`: a parcel's card as the app's list shows it, in its carrier's colours: the mark at the top,
 * over the route, and the parcel's name and status at the bottom.
 */
@Composable
internal fun CardView(parcel: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: WidgetCardArt, words: CardWords) {
    val type = words.type
    Column(GlanceModifier.fillMaxSize()) {
        Row(GlanceModifier.fillMaxWidth()) {
            val natural = words.markHeight * (art.mark.first.width.toFloat() / art.mark.first.height)
            val mark = GlanceModifier.height(words.markHeight).let { if (natural > words.markRoom) it.width(words.markRoom) else it }
            DayNightPicture(art.mark, R.layout.widget_mark, mark, description = words.markDescription)
            Spacer(GlanceModifier.defaultWeight())
        }
        Spacer(GlanceModifier.height(4.dp))
        Spacer(GlanceModifier.defaultWeight())
        Text(
            words.label.text, maxLines = words.label.lines,
            style = TextStyle(color = WidgetCardColors.primary, fontWeight = FontWeight.Medium, fontSize = type.sp(words.label.points)),
        )
        Spacer(GlanceModifier.height(words.spacing))
        Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            if (words.newUpdate) NewUpdateDot(WidgetCardColors.pair(card.ink, NEW_UPDATE_OPACITY))
            Text(words.status, GlanceModifier.defaultWeight(), maxLines = 1, style = TextStyle(color = WidgetCardColors.pair(card.ink), fontSize = type.sp(words.statusPoints)))
        }
    }
}

/**
 * The mark of a scan the reader hasn't seen (#72), first on a parcel's status line, as the app's cards draw it
 * (`NewUpdateDot`): a dot [NEW_UPDATE_DOT] wide in the line's colour at 80 % ([NEW_UPDATE_OPACITY]), [NEW_UPDATE_GAP]
 * before the words. TalkBack hears "New update." first in the parcel's words ([newUpdateSpoken]), not here.
 */
@Composable
internal fun NewUpdateDot(color: ColorProvider) {
    Box(GlanceModifier.size(NEW_UPDATE_DOT).cornerRadius(NEW_UPDATE_DOT / 2).background(color)) {}
    Spacer(GlanceModifier.width(NEW_UPDATE_GAP))
}

/** The room the new-update dot takes before the parcel's status line: none without one. */
internal val DeliveryWidgetParcel.dotRoom: Dp
    get() = if (newUpdate == true) NEW_UPDATE_DOT + NEW_UPDATE_GAP else 0.dp

/**
 * The size of a parcel's stage or day `text` in a line `room` wide, past the new-update dot where it has one: `points`
 * where it fits, else as much smaller as it takes, down to [LINE_FLOOR] of it as a card's status, past which it is cut
 * short.
 */
internal fun WidgetText.lineFit(text: String, points: Float, room: Dp): Float = fit(text, points, FontWeight.Normal, room, 1, LINE_FLOOR)

/**
 * A parcel's stage as a line `room` wide shows it from `points` ([lineFit]): in the app's words where they fit at
 * [LINE_FLOOR] of that size, else in the widget's shorter ones where the app gave some ([listShortStatus]), so that a
 * stage is cut short only where neither fits.
 */
internal fun WidgetText.fittingStatus(parcel: DeliveryWidgetParcel, locale: Locale, points: Float, room: Dp): String {
    val status = parcel.listStatus.openingLine(locale)
    val short = parcel.listShortStatus?.openingLine(locale) ?: return status
    return if (lines(status, points * LINE_FLOOR, FontWeight.Normal, room) <= 1) status else short
}

/** "New update." as TalkBack reads it first on a parcel with the dot (#72), as the app's cards say it; none without. */
internal fun newUpdateSpoken(parcel: DeliveryWidgetParcel, languageCode: String): String? =
    if (parcel.newUpdate == true) "${ExtensionLocalizer(languageCode).text("parcel.newUpdate")}." else null

/** The least of its size a parcel's line takes before it is cut short ([lineFit]). */
internal const val LINE_FLOOR = 0.8f

internal val NEW_UPDATE_DOT = 5.dp
internal val NEW_UPDATE_GAP = 5.dp
internal const val NEW_UPDATE_OPACITY = 0.8f

/**
 * A picture in its light and dark forms, which the launcher swaps as the phone's mode changes, with no word from the
 * app: an image view of `layout` (widget_route, widget_mark) given both.
 */
@Composable
internal fun DayNightPicture(picture: Pair<Bitmap, Bitmap>, @LayoutRes layout: Int, modifier: GlanceModifier, description: String?) {
    val context = LocalContext.current
    val views = RemoteViews(context.packageName, layout).apply {
        setIcon(R.id.widget_picture, "setImageIcon", Icon.createWithBitmap(picture.first), Icon.createWithBitmap(picture.second))
        setContentDescription(R.id.widget_picture, description)
    }
    AndroidRemoteViews(views, modifier)
}

/**
 * A tap on the widget's page, behind what it shows, opening `intent` (widget_tap): a view TalkBack passes over, as
 * VoiceOver passes over a widget's background.
 */
@Composable
internal fun PageTap(intent: Intent) {
    val context = LocalContext.current
    val views = RemoteViews(context.packageName, R.layout.widget_tap).apply {
        setOnClickPendingIntent(R.id.widget_tap, PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))
    }
    AndroidRemoteViews(views, GlanceModifier.fillMaxSize())
}

/** `widgetURL` and `Link`: the parcel on the deliveries, through the app's routing (NativeRoute), in the app's window. */
internal fun openParcel(context: Context, parcel: DeliveryWidgetParcel): Action =
    actionStartActivity(Intent(Intent.ACTION_VIEW, parcel.deepLink.toUri(), context, MainActivity::class.java))

/** The widget's whole surface, its corners the launcher's, as a widget's background is. */
internal fun root(): GlanceModifier =
    GlanceModifier.fillMaxSize().appWidgetBackground().cornerRadius(android.R.dimen.system_app_widget_background_radius)

internal fun markHeight(shape: DeliveryWidgetPicture): Dp = if (shape == DeliveryWidgetPicture.Half) 13.dp else 15.dp

private fun DpSize.inside(padding: Dp) = DpSize(width - padding * 2, height - padding * 2)

/** A transparent picture `width` × `height` dp at the screen's density, drawn one unit to the dp but for [drawSymbol]'s own density. */
internal fun bitmap(context: Context, width: Float, height: Float, draw: DrawScope.() -> Unit): Bitmap {
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
private val CARD_SPACING = 8.dp
private val CARD_PADDING = 10.dp
private val SIDE_FADE = 16.dp
