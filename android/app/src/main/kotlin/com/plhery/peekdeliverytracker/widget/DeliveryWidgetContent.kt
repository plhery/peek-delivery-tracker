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
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
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
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.DeliveryWidgetTimeline
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.ui.draw.drawPeekMark
import com.plhery.peekdeliverytracker.ui.symbols.SymbolRenderingMode
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.symbols.drawSymbol
import com.plhery.peekdeliverytracker.ui.symbols.materialSymbols
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import kotlin.math.floor
import kotlin.math.roundToInt

// DeliveryWidget.swift's DeliveryWidgetView and DeliveryWidgetCards.swift: what the widget shows, in Glance's
// RemoteViews, at the size the launcher gives it. Text is iOS's text styles at the phone's text size within WidgetKit's
// range, shrunk to fit where the iPhone's shrinks ([WidgetText]); Glance has three weights (semibold is Medium, heavy
// Bold) and no tracking. Glance's layouts don't give up a line where room runs out, as SwiftUI's do, so each layout
// works out from the measured text which lines it keeps.

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
    val page: ColorProvider = pair(listOf("#F4F5F1", "#13110E"))

    /** `Color.primary`: the label's colour on a card. */
    val primary: ColorProvider = ColorProvider(day = Color.Black, night = Color.White)
}

/** A card's pictures as the widget reads them: its mark, and its route as the widget's shape shows it, light and dark. */
class WidgetCardArt(val mark: Pair<Bitmap, Bitmap>, val route: Pair<Bitmap, Bitmap>?)

/** The widget's next button reaches past what it shows, so that a finger finds it (`DeliveryWidgetNextMark`). */
private object NextMark {
    val size = 18.dp
    val reach = 9.dp

    /** What a row the button stands at the end of leaves it: the button, and the row's spacing. */
    val room = size + 8.dp
}

/**
 * `DeliveryWidgetView`: the cards once the app has drawn every one the widget shows, else words, at the widget's
 * `size`: the iPhone's systemMedium family where it is wide for its height ([NextDeliveryWidget.isMedium]). A tap opens
 * the parcel shown, or the app where none is.
 */
@Composable
fun DeliveryWidgetContent(entry: DeliveryWidgetEntry<WidgetCardArt>, size: DpSize) {
    val context = LocalContext.current
    val copy = remember(entry.languageCode) { ExtensionLocalizer(entry.languageCode) }
    val type = WidgetText(context)
    val cards = entry.cards
    if (cards.isEmpty()) Words(entry, size, type, copy) else Cards(entry, cards, size, type, copy)
}

@Composable
private fun Cards(
    entry: DeliveryWidgetEntry<WidgetCardArt>,
    cards: List<Triple<DeliveryWidgetParcel, DeliveryWidgetCard, WidgetCardArt>>,
    size: DpSize,
    type: WidgetText,
    copy: ExtensionLocalizer,
) {
    val context = LocalContext.current
    val only = cards.singleOrNull()
    if (only != null) {
        val shape = if (NextDeliveryWidget.isMedium(size)) DeliveryWidgetPicture.Wide else DeliveryWidgetPicture.Small
        val (parcel, card, art) = only
        val words = CardWords(parcel, card, shape, leavesNext = entry.next != null, room = size.inside(MARGIN), type)
        Box(root().background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))) {
            CardBackground(art, shape, size)
            Box(GlanceModifier.fillMaxSize().padding(MARGIN)) { CardView(parcel, card, art, words) }
            NextButton(entry, NextDeliveryWidget.isMedium(size), card.ink, copy, top = MARGIN + words.markHeight / 2, end = MARGIN + NextMark.reach)
        }
    } else {
        val half = DpSize((size.width - MARGIN * 2 - CARD_SPACING) / 2, size.height - MARGIN * 2)
        // The page between the cards opens the app, as WidgetKit's does, but isn't an item of its own for TalkBack, as
        // the iPhone's page isn't one for VoiceOver: its cards are.
        Box(root().background(WidgetCardColors.page)) {
            PageTap()
            Row(GlanceModifier.fillMaxSize().padding(MARGIN)) {
                cards.forEachIndexed { index, (parcel, card, art) ->
                    if (index > 0) Spacer(GlanceModifier.width(CARD_SPACING))
                    val leavesNext = entry.next != null && index == cards.lastIndex
                    val words = CardWords(parcel, card, DeliveryWidgetPicture.Half, leavesNext, room = half.inside(CARD_PADDING), type)
                    Box(
                        GlanceModifier.defaultWeight().fillMaxHeight().cornerRadius(14.dp).background(WidgetCardColors.pair(card.surface))
                            .clickable(openParcel(context, parcel)),
                    ) {
                        CardBackground(art, DeliveryWidgetPicture.Half, half)
                        Box(GlanceModifier.fillMaxSize().padding(CARD_PADDING)) { CardView(parcel, card, art, words) }
                    }
                }
            }
            // Over the last card, outside its link.
            NextButton(
                entry, NextDeliveryWidget.isMedium(size), cards.last().second.ink, copy, top = MARGIN + CARD_PADDING + markHeight(DeliveryWidgetPicture.Half) / 2,
                end = MARGIN + CARD_PADDING + NextMark.reach,
            )
        }
    }
}

/**
 * A card's words as they fit `room`, the card inside its padding: the label on two lines where the card is tall enough
 * for them, else one, shrunk to 0.82 of its style to fit them, and the status line shrunk to 0.8 to fit its one.
 */
private class CardWords(
    parcel: DeliveryWidgetParcel,
    card: DeliveryWidgetCard,
    val shape: DeliveryWidgetPicture,
    val leavesNext: Boolean,
    room: DpSize,
    val type: WidgetText,
) {
    val half = shape == DeliveryWidgetPicture.Half
    val markHeight = markHeight(shape)
    val status = if (card.status == parcel.detail) card.status else "${card.status} · ${parcel.detail}"
    val statusPoints = type.fit(status, type.points(if (half) IosTextStyle.Caption2 else IosTextStyle.Caption), FontWeight.Normal, room.width, 1, 0.8f)
    val spacing = if (half) 2.dp else 3.dp
    private val labelStyle = if (half) IosTextStyle.Subheadline else if (shape == DeliveryWidgetPicture.Wide) IosTextStyle.Title3 else IosTextStyle.Headline
    private val statusHeight = type.height(status, statusPoints, FontWeight.Normal, room.width, 1)
    val label: FittedLine = listOf(2, 1).map { lines -> FittedLine.of(parcel.label, labelStyle, FontWeight.Medium, room.width, lines, 0.82f, type) }
        .firstOrNull { markHeight + 4.dp + it.height + spacing + statusHeight <= room.height } ?: FittedLine.of(parcel.label, labelStyle, FontWeight.Medium, room.width, 1, 0.82f, type)

    /** The width the top row leaves the mark, which shrinks to it as `scaledToFit` shrinks the iPhone's. */
    val markRoom = room.width - if (leavesNext) NextMark.room else 0.dp
}

/** A line of text at the size it fits its room, and how tall it stands. */
private class FittedLine(val text: String, val points: Float, val lines: Int, val height: Dp) {
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
private fun CardBackground(art: WidgetCardArt, shape: DeliveryWidgetPicture, card: DpSize) {
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
private fun fadedSides(picture: Bitmap, points: Double): Bitmap {
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
private fun CardView(parcel: DeliveryWidgetParcel, card: DeliveryWidgetCard, art: WidgetCardArt, words: CardWords) {
    val type = words.type
    Column(GlanceModifier.fillMaxSize()) {
        Row(GlanceModifier.fillMaxWidth()) {
            val natural = words.markHeight * (art.mark.first.width.toFloat() / art.mark.first.height)
            val mark = GlanceModifier.height(words.markHeight).let { if (natural > words.markRoom) it.width(words.markRoom) else it }
            DayNightPicture(art.mark, R.layout.widget_mark, mark, description = parcel.carrier)
            Spacer(GlanceModifier.defaultWeight())
            // The end of the top row is left to the widget's next button.
            if (words.leavesNext) Spacer(GlanceModifier.width(NextMark.room))
        }
        Spacer(GlanceModifier.height(4.dp))
        Spacer(GlanceModifier.defaultWeight())
        Text(
            words.label.text, maxLines = words.label.lines,
            style = TextStyle(color = WidgetCardColors.primary, fontWeight = FontWeight.Medium, fontSize = type.sp(words.label.points)),
        )
        Spacer(GlanceModifier.height(words.spacing))
        Text(words.status, maxLines = 1, style = TextStyle(color = WidgetCardColors.pair(card.ink), fontSize = type.sp(words.statusPoints)))
    }
}

/**
 * `words`: the widget's own yellow, with the next parcels, or why there are none: the widget switched off in the app,
 * or nothing on its way. A small widget opens its parcel; a medium one each of its two.
 */
@Composable
private fun Words(entry: DeliveryWidgetEntry<WidgetCardArt>, size: DpSize, type: WidgetText, copy: ExtensionLocalizer) {
    val context = LocalContext.current
    val parcels = entry.shown
    val medium = NextDeliveryWidget.isMedium(size)
    val tap = if (!medium && entry.enabled) parcels.firstOrNull()?.let { openParcel(context, it) } else null
    val room = size.inside(MARGIN)
    Box(root().background(ImageProvider(R.drawable.widget_words_background)).clickable(tap ?: openApp(context))) {
        when {
            !entry.enabled -> Margins {
                StateView("rectangle.slash", copy.text("widget.disabledTitle"), copy.onAndroid("widget.disabledDescription"), medium, room, type)
            }
            parcels.isEmpty() -> Margins { StateView("shippingbox", copy.text("app.noneOnWay"), copy.text("app.emptyDescription"), medium, room, type) }
            medium -> {
                val words = MediumWords(entry, parcels.take(2), room, type, copy)
                Margins { MediumContent(words, type) }
                NextButton(entry, medium, null, copy, top = MARGIN + words.buttonTop, end = MARGIN + words.buttonEnd)
            }
            else -> {
                val words = SmallWords(entry, parcels.first(), room, type, copy)
                Margins { SmallContent(words, type) }
                NextButton(entry, medium, null, copy, top = MARGIN + words.buttonTop, end = MARGIN + NextMark.reach)
            }
        }
    }
}

@Composable
private fun Margins(content: @Composable () -> Unit) {
    Box(GlanceModifier.fillMaxSize().padding(MARGIN)) { content() }
}

/**
 * What a words widget keeps where its height runs short, the date last: the name's second line goes first (as
 * SwiftUI's would), then the header (Android's widgets can be shorter than any iPhone's or iPad's), then the carrier.
 * Without the header, the next button stands level with the badge of the parcel it stands over.
 */
private class WordsLines(val header: Boolean, val labelLines: Int, val carrier: Boolean)

/** `widgetHeader`'s height: the app's mark, or its name where the text size makes that taller. */
private fun headerHeight(type: WidgetText): Dp = maxOf(HEADER_MARK, type.lineHeight(type.points(IosTextStyle.Caption2), FontWeight.Bold))

/** `smallContent`'s lines as they fit `room`, the widget inside its margins. */
private class SmallWords(entry: DeliveryWidgetEntry<WidgetCardArt>, val parcel: DeliveryWidgetParcel, room: DpSize, type: WidgetText, val copy: ExtensionLocalizer) {
    val caption = type.points(IosTextStyle.Caption)
    val lines: WordsLines
    val badge: BadgeLine
    val label: FittedLine

    /** The next button's centre from the top inside the margins: level with the header's mark, else with the badge. */
    val buttonTop: Dp

    init {
        val carrierHeight = type.lineHeight(caption, FontWeight.Medium)
        val detailHeight = type.lineHeight(caption, FontWeight.Bold)
        fun badge(lines: WordsLines) = BadgeLine(parcel, room.width, leaves = if (!lines.header && entry.next != null) NextMark.room else 0.dp, type, copy)
        fun label(lines: WordsLines) = FittedLine.of(parcel.label, IosTextStyle.Headline, FontWeight.Bold, room.width, lines.labelLines, 0.82f, type)
        val choices = listOf(WordsLines(true, 2, true), WordsLines(true, 1, true), WordsLines(false, 1, true), WordsLines(false, 1, false))
        lines = choices.firstOrNull { lines ->
            (if (lines.header) headerHeight(type) + 6.dp else 0.dp) + badge(lines).height + 3.dp + label(lines).height + 6.dp +
                (if (lines.carrier) carrierHeight + 1.dp else 0.dp) + detailHeight <= room.height
        } ?: choices.last()
        badge = badge(lines)
        label = label(lines)
        buttonTop = if (lines.header) headerHeight(type) / 2 else badge.height / 2
    }
}

/**
 * Inside the margins, the smallest widgets still fit a two-line name. Grouped, as a layout of Glance's shows ten
 * children at most.
 */
@Composable
private fun SmallContent(layout: SmallWords, type: WidgetText) {
    val parcel = layout.parcel
    Column(GlanceModifier.fillMaxSize()) {
        if (layout.lines.header) {
            Header(type, layout.copy)
            Spacer(GlanceModifier.height(6.dp))
        }
        Spacer(GlanceModifier.defaultWeight())
        Column {
            Badge(layout.badge, type)
            Spacer(GlanceModifier.height(3.dp))
            Text(layout.label.text, maxLines = layout.label.lines, style = words(type, layout.label.points, FontWeight.Bold))
        }
        Spacer(GlanceModifier.height(6.dp))
        Spacer(GlanceModifier.defaultWeight())
        Column {
            if (layout.lines.carrier) {
                Text(parcel.carrier, maxLines = 1, style = words(type, layout.caption, FontWeight.Medium, 0.68f))
                Spacer(GlanceModifier.height(1.dp))
            }
            Text(parcel.detail, maxLines = 1, style = words(type, layout.caption, FontWeight.Bold))
        }
    }
}

/** `mediumContent`'s lines as they fit `room`: one or two parcels side by side, each in its column. */
private class MediumWords(entry: DeliveryWidgetEntry<WidgetCardArt>, val shown: List<DeliveryWidgetParcel>, room: DpSize, type: WidgetText, val copy: ExtensionLocalizer) {
    val caption = type.points(IosTextStyle.Caption)
    val caption2 = type.points(IosTextStyle.Caption2)
    val lines: WordsLines
    val badges: List<BadgeLine>
    val labels: List<FittedLine>

    /** The next button's centre from the top and the end inside the margins: level with the header's mark, else with the last column's badge. */
    val buttonTop: Dp
    val buttonEnd: Dp

    init {
        // Two columns whatever is shown, as the iPhone's single parcel takes half the width.
        val column = (room.width - COLUMN_SPACING) / 2 - COLUMN_PADDING * 2
        val carrierHeight = type.lineHeight(caption2, FontWeight.Medium)
        val detailHeight = type.lineHeight(caption, FontWeight.Bold)
        fun badges(lines: WordsLines) = shown.mapIndexed { index, parcel ->
            val leavesNext = !lines.header && entry.next != null && shown.size == 2 && index == 1
            BadgeLine(parcel, column, leaves = if (leavesNext) NextMark.room else 0.dp, type, copy)
        }
        labels = shown.map { FittedLine.of(it.label, IosTextStyle.Subheadline, FontWeight.Bold, column, 1, 0.85f, type) }
        val choices = listOf(WordsLines(true, 1, true), WordsLines(false, 1, true), WordsLines(false, 1, false))
        lines = choices.firstOrNull { lines ->
            (if (lines.header) headerHeight(type) + 8.dp else 0.dp) + COLUMN_TOP * 2 + badges(lines).maxOf { it.height } + 3.dp +
                labels.maxOf { it.height } + 3.dp + (if (lines.carrier) carrierHeight + 3.dp else 0.dp) + detailHeight <= room.height
        } ?: choices.last()
        badges = badges(lines)
        buttonTop = if (lines.header) headerHeight(type) / 2 else COLUMN_TOP + badges.last().height / 2
        buttonEnd = if (lines.header || shown.size == 1) NextMark.reach else COLUMN_PADDING + NextMark.reach
    }
}

@Composable
private fun MediumContent(layout: MediumWords, type: WidgetText) {
    val context = LocalContext.current
    Column(GlanceModifier.fillMaxSize()) {
        if (layout.lines.header) {
            Header(type, layout.copy)
            Spacer(GlanceModifier.height(8.dp))
        }
        Row(GlanceModifier.fillMaxWidth().defaultWeight()) {
            layout.shown.forEachIndexed { index, parcel ->
                if (index > 0) Spacer(GlanceModifier.width(COLUMN_SPACING))
                Column(
                    GlanceModifier.defaultWeight().fillMaxHeight().cornerRadius(14.dp).background(ColorProvider(Color.White.copy(alpha = 0.36f)))
                        .clickable(openParcel(context, parcel)).padding(horizontal = COLUMN_PADDING, vertical = COLUMN_TOP),
                ) {
                    Badge(layout.badges[index], type)
                    Spacer(GlanceModifier.height(3.dp))
                    Text(layout.labels[index].text, maxLines = 1, style = words(type, layout.labels[index].points, FontWeight.Bold))
                    Spacer(GlanceModifier.height(3.dp))
                    if (layout.lines.carrier) {
                        Text(parcel.carrier, maxLines = 1, style = words(type, layout.caption2, FontWeight.Medium, 0.64f))
                        Spacer(GlanceModifier.height(3.dp))
                    }
                    Text(parcel.detail, maxLines = 1, style = words(type, layout.caption, FontWeight.Bold))
                }
            }
            if (layout.shown.size == 1) {
                Spacer(GlanceModifier.width(COLUMN_SPACING))
                Spacer(GlanceModifier.defaultWeight())
            }
        }
    }
}

/**
 * `widgetHeader`: the app's mark and name, as tall as the name where the text size makes it taller than the mark. The
 * next button is level with the mark, as the iPhone's is (`deliveryWidgetNext(markHeight: 16)`).
 */
@Composable
private fun Header(type: WidgetText, copy: ExtensionLocalizer) {
    val context = LocalContext.current
    val mark = remember { peekMark(context) }
    Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(ImageProvider(mark), contentDescription = null, modifier = GlanceModifier.size(HEADER_MARK))
        Spacer(GlanceModifier.width(6.dp))
        val name = copy.text("app.title").uppercase(ExtensionLocalizer.locale(copy.languageCode))
        Text(name, maxLines = 1, style = words(type, type.points(IosTextStyle.Caption2), FontWeight.Bold, 0.72f))
    }
}

/**
 * `parcelBadge`'s words: out for delivery, delivered or on its way, in `width`, its symbol and its spacing included,
 * less what it `leaves` at its end to the widget's next button; the words shrink to 0.7 of caption 2 before they end in
 * an ellipsis.
 */
private class BadgeLine(parcel: DeliveryWidgetParcel, width: Dp, leaves: Dp, type: WidgetText, copy: ExtensionLocalizer) {
    val symbol = if (parcel.isOutForDelivery) "bicycle" else if (parcel.deliveredAt != null) "checkmark.circle.fill" else "arrow.right.circle.fill"
    val symbolPoints = type.points(IosTextStyle.Caption2)
    private val box = requireNotNull(materialSymbols[symbol]).box(symbolPoints, SymbolScale.Small.factor)
    val text = copy.text(if (parcel.isOutForDelivery) "stage.out_for_delivery" else if (parcel.deliveredAt != null) "stage.delivered" else "app.onTheWaySection")
        .uppercase(ExtensionLocalizer.locale(copy.languageCode))
    /** The words' width where the badge leaves room for the button, which the row's own width wouldn't hold them to. */
    val textWidth: Dp? = (width - leaves - box[0].dp - 4.dp).takeIf { leaves > 0.dp }
    val points = type.fit(text, symbolPoints, FontWeight.Bold, width - leaves - box[0].dp - 4.dp, 1, 0.7f)
    val height = maxOf(box[1].dp, type.lineHeight(points, FontWeight.Bold))
}

/** `parcelBadge`: tighter than a Label, so "Out for delivery" fits a small widget. */
@Composable
private fun Badge(badge: BadgeLine, type: WidgetText) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Symbol(badge.symbol, badge.symbolPoints, ComposeFontWeight.Black, WidgetPalette.ink, SymbolScale.Small)
        Spacer(GlanceModifier.width(4.dp))
        val width = badge.textWidth?.let { GlanceModifier.width(it) } ?: GlanceModifier
        Text(badge.text, width, maxLines = 1, style = words(type, badge.points, FontWeight.Bold))
    }
}

/**
 * `stateView`: where space runs out, the description gives up lines before the title does (its `layoutPriority`),
 * the title shrinking to 0.82 of its style to fit its two.
 */
@Composable
private fun StateView(symbol: String, title: String, description: String, medium: Boolean, room: DpSize, type: WidgetText) {
    val symbolPoints = type.points(IosTextStyle.Title3)
    val symbolHeight = requireNotNull(materialSymbols[symbol]).box(symbolPoints)[1].dp
    val caption = type.points(IosTextStyle.Caption)
    val titles = listOf(2, 1).map { FittedLine.of(title, IosTextStyle.Headline, FontWeight.Bold, room.width, it, 0.82f, type) }
    val fitted = titles.firstOrNull { symbolHeight + 6.dp + it.height <= room.height } ?: titles.last()
    val left = room.height - symbolHeight - 6.dp - fitted.height - 6.dp
    val first = type.lineHeight(caption, FontWeight.Normal)
    val next = type.height("1\n2", caption, FontWeight.Normal, room.width, 2) - first
    val lines = if (left < first) 0 else minOf(if (medium) 2 else 3, 1 + floor((left - first) / next).toInt())
    Column(GlanceModifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
        Symbol(symbol, symbolPoints, ComposeFontWeight.Bold, WidgetPalette.ink)
        Spacer(GlanceModifier.height(6.dp))
        Text(fitted.text, maxLines = fitted.lines, style = words(type, fitted.points, FontWeight.Bold))
        if (lines > 0) {
            Spacer(GlanceModifier.height(6.dp))
            Text(description, maxLines = lines, style = words(type, caption, FontWeight.Normal, 0.67f))
        }
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

/** An SF Symbol as the app draws it, at `points` (its text style's size at the widget's category), in `tint`. */
@Composable
private fun Symbol(name: String, points: Float, weight: ComposeFontWeight, tint: Color, scale: SymbolScale = SymbolScale.Medium) {
    val context = LocalContext.current
    val art = requireNotNull(materialSymbols[name])
    val box = art.box(points, scale.factor)
    val bitmap = remember(name, points, tint) {
        bitmap(context, box[0], box[1]) { drawSymbol(art, points * scale.factor, weight.weight, tint, SymbolRenderingMode.Monochrome) }
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

/**
 * A tap on the widget's page, behind what it shows, opening the app (widget_tap): a view TalkBack passes over, as
 * VoiceOver passes over a widget's background.
 */
@Composable
private fun PageTap() {
    val context = LocalContext.current
    val views = RemoteViews(context.packageName, R.layout.widget_tap).apply {
        setOnClickPendingIntent(R.id.widget_tap, PendingIntent.getActivity(context, 0, appIntent(context), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))
    }
    AndroidRemoteViews(views, GlanceModifier.fillMaxSize())
}

/** `widgetURL` and `Link`: the parcel on the deliveries, through the app's routing (NativeRoute), in the app's window. */
internal fun openParcel(context: Context, parcel: DeliveryWidgetParcel): Action =
    actionStartActivity(Intent(Intent.ACTION_VIEW, parcel.deepLink.toUri(), context, MainActivity::class.java))

/** A widget with no link opens the app, as WidgetKit opens it. */
internal fun openApp(context: Context): Action = actionStartActivity(appIntent(context))

internal fun appIntent(context: Context): Intent =
    Intent(context, MainActivity::class.java).setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)

/** The widget's whole surface, its corners the launcher's, as a widget's background is. */
private fun root(): GlanceModifier =
    GlanceModifier.fillMaxSize().appWidgetBackground().cornerRadius(android.R.dimen.system_app_widget_background_radius)

/** The copy as Android words it where it names the iPhone's things (its `android` variant: "home screen"), else as it is. */
internal fun ExtensionLocalizer.onAndroid(key: String): String = text("$key.android").takeIf { it != "$key.android" } ?: text(key)

private fun words(type: WidgetText, points: Float, weight: FontWeight, opacity: Float = 1f) =
    TextStyle(color = ColorProvider(WidgetPalette.ink.copy(alpha = opacity)), fontSize = type.sp(points), fontWeight = weight)

private fun markHeight(shape: DeliveryWidgetPicture): Dp = if (shape == DeliveryWidgetPicture.Half) 13.dp else 15.dp

private fun DpSize.inside(padding: Dp) = DpSize(width - padding * 2, height - padding * 2)

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
private val CARD_SPACING = 8.dp
private val CARD_PADDING = 10.dp
private val COLUMN_SPACING = 10.dp
private val COLUMN_PADDING = 10.dp
private val COLUMN_TOP = 8.dp
private val SIDE_FADE = 16.dp
