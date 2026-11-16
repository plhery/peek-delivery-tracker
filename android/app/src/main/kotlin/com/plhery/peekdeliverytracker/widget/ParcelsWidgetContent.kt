package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.background
import androidx.glance.color.ColorProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.semantics.contentDescription
import androidx.glance.semantics.semantics
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.listDay
import com.plhery.peekdeliverytracker.core.listLine
import com.plhery.peekdeliverytracker.core.listName
import com.plhery.peekdeliverytracker.core.listStatus
import com.plhery.peekdeliverytracker.core.openingLine
import com.plhery.peekdeliverytracker.core.metadata
import com.plhery.peekdeliverytracker.ui.symbols.SymbolRenderingMode
import com.plhery.peekdeliverytracker.ui.symbols.drawSymbol
import com.plhery.peekdeliverytracker.ui.symbols.materialSymbols
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.SystemColors
import com.plhery.peekdeliverytracker.ui.theme.toColor
import java.util.Locale
import kotlin.math.floor
import kotlin.math.roundToInt

// The "Parcels" widget's look, the iPhone's list widget (ParcelListWidget.swift): the parcels on their way, in the
// design their number takes at the widget's size ([DeliveryWidgetList.design]): cards for a few, passes for a few more
// where every one fits (ParcelsWidgetDesigns.kt), else rows under the app list's sections on the app's page, then a
// line counting the rest at the widget's foot. Each row is the carrier's truck, the parcel's name, and its stage and day, as the app published
// them (DeliveryWidgetStage). The widget's size stands for one of the iPhone's three ([listSize]): small, a column of
// rows of two lines, the name over the stage and day; medium, two such columns; large, rows of one line, the stage and
// day at their end. Text is the existing widget's (WidgetText), and as there, the layout works out from the measured
// text how many rows it keeps. Names end in an ellipsis where they are too long; a line too long for its room keeps
// the day.

/**
 * The widget's colours, light and dark as the launcher's mode picks: the app's page, and on it the system's label
 * colours the iPhone's list widget writes in (`.primary`, `.secondary`).
 */
internal object ListColors {
    fun of(color: AdaptiveColor, alpha: Float = 1f): ColorProvider =
        ColorProvider(day = color.light.toColor().copy(alpha = alpha), night = color.dark.toColor().copy(alpha = alpha))

    val page = of(Brand.background)
    val primary = of(SystemColors.label)
    val secondary = of(SystemColors.secondaryLabel)
}

/** `ParcelListMetrics`: a row's name and the box of its stage's symbol, each smaller where [compact]. */
internal class ListMetrics(val compact: Boolean, type: WidgetText) {
    /** The width of the stage's symbol, which grows with the text (`@ScaledMetric`). */
    val markWidth: Dp = if (compact) {
        18.dp * (type.points(IosTextStyle.Caption) / IosTextStyle.Caption.pointSize(DynamicTypeSize.Large))
    } else {
        20.dp * (type.points(IosTextStyle.Footnote) / IosTextStyle.Footnote.pointSize(DynamicTypeSize.Large))
    }

    /** The symbol's box is three quarters as tall: wide symbols, the bicycle and the truck, take its width, the others its height. */
    val markHeight: Dp = (markWidth.value * 0.75f).roundToInt().dp
    val markSpacing: Dp = if (compact) 5.dp else 6.dp
    val rowSpacing: Dp = if (compact) 3.dp else 5.dp

    /** The name's size, semibold: caption where compact, else footnote. */
    val namePoints: Float = type.points(if (compact) IosTextStyle.Caption else IosTextStyle.Footnote)
}

/**
 * How the list's rows look at `size` (`ParcelListMetrics`): the iPhone's widget the size stands for ([size]), the larger
 * type or, `compact`, the smaller ([ListMetrics.compact]), and in `columns` columns, which give the words their width;
 * `oneColumn`, a medium widget's rows down one column, each on one line ([DeliveryWidgetList.Layout.oneColumn]).
 * [SectionsLayout] picks the type and the rows that fit.
 */
internal class ListLayout(list: DeliveryWidgetList, size: DpSize, val type: WidgetText, compact: Boolean, columns: Int, oneColumn: Boolean = false) {
    val languageCode: String = list.languageCode
    val locale: Locale = ExtensionLocalizer.locale(list.languageCode)
    val room = DpSize(size.width - MARGIN * 2, size.height - MARGIN * 2)

    val size: DeliveryWidgetList.Size = listSize(size)

    /**
     * Whether rows take one line, the stage and day at their end, as a large widget's where it is wide enough, and a
     * medium one's down one column.
     */
    val lineRows = oneColumn || (this.size == DeliveryWidgetList.Size.Large && size.width >= WIDE_WIDTH)

    /** The stage and day's size: caption beside the name, caption2 under it and beside the name in the smaller type. */
    val statusPoints: Float = type.points(if (lineRows && !compact) IosTextStyle.Caption else IosTextStyle.Caption2)
    val morePoints: Float = type.points(IosTextStyle.Caption)
    val moreHeight: Dp = type.lineHeight(morePoints, FontWeight.Medium)
    val metrics = ListMetrics(compact, type)
    val rowHeight: Dp = rowHeight(metrics)

    /** The width a row leaves its words, past the stage's symbol. */
    val words: Dp = (if (columns == 2) (room.width - COLUMN_GAP) / 2 else room.width) - metrics.markWidth - metrics.markSpacing

    /** A row's height: its name over its line, or both on one line, and the symbol's box. */
    private fun rowHeight(metrics: ListMetrics): Dp {
        val name = type.lineHeight(metrics.namePoints, FontWeight.Medium)
        val line = type.lineHeight(statusPoints, FontWeight.Normal)
        return maxOf(if (lineRows) maxOf(name, line) else name + line, metrics.markHeight)
    }
}

/** The iPhone's list widget a size stands for: from [TALL_HEIGHT] the large one, else from [WIDE_WIDTH] the medium one, else the small. */
internal fun listSize(size: DpSize): DeliveryWidgetList.Size = when {
    size.height >= TALL_HEIGHT -> DeliveryWidgetList.Size.Large
    size.width >= WIDE_WIDTH -> DeliveryWidgetList.Size.Medium
    else -> DeliveryWidgetList.Size.Small
}

/**
 * The list at the widget's `size`, in the design its number of parcels takes there ([DeliveryWidgetList.design]):
 * cards, passes, or rows under the app list's sections, which it also shows until the cards' pictures `art` reads are
 * drawn, and where the passes don't all fit ([passesFit], [DeliveryWidgetList.fits]). Each parcel opens itself, and the rest of the widget the app's list ([DeliveryWidgetList.link]). Or why there
 * are none, in the existing widget's words.
 */
@Composable
fun ParcelsWidgetContent(
    list: DeliveryWidgetList,
    size: DpSize,
    art: (DeliveryWidgetCard, DeliveryWidgetPicture) -> WidgetCardArt? = { _, _ -> null },
) {
    val context = LocalContext.current
    val copy = remember(list.languageCode) { Localizer(AppLanguage.fromRawValue(list.languageCode) ?: AppLanguage.En, variant = "android") }
    val type = WidgetText(context)
    val design = if (list.enabled && list.parcels.isNotEmpty()) list.design(listSize(size)) else DeliveryWidgetList.Design.Sections
    val shown = if (design == DeliveryWidgetList.Design.Cards) list.cardOrder else list.parcels
    val shape = if (design == DeliveryWidgetList.Design.Cards) cardShape(listSize(size), shown.size, size) else DeliveryWidgetPicture.Half
    val drawn = if (design == DeliveryWidgetList.Design.Sections) emptyList() else shown.map { parcel -> parcel.card?.let { art(it, shape) } }
    val drawnAll = drawn.isNotEmpty() && drawn.all { it != null }
    val passes = drawnAll && design == DeliveryWidgetList.Design.Passes
    when {
        drawnAll && design == DeliveryWidgetList.Design.Cards -> ListCards(list, shown, drawn.filterNotNull(), size, type)
        passes && passesFit(list.parcels.size, size, type) -> Passes(list, drawn.filterNotNull(), size, type)
        else -> Box(root().background(ListColors.page)) {
            PageTap(listIntent(context))
            Box(GlanceModifier.fillMaxSize().padding(MARGIN)) {
                when {
                    !list.enabled -> ListState("rectangle.slash", copy.text("widget.disabledTitle"), copy.text("widget.disabledDescription"), size, type)
                    list.parcels.isEmpty() -> ListState("shippingbox", copy.text("app.noneOnWay"), copy.text("app.emptyDescription"), size, type)
                    else -> Sections(list, size, type, copy, if (passes) DeliveryWidgetList.Design.Passes else DeliveryWidgetList.Design.Sections)
                }
            }
        }
    }
}

/** The count of the parcels past the rows: "+12 more", in the language's plural. */
internal fun moreParcels(copy: Localizer, count: Int): String = copy.text("widget.listMore.many", mapOf("count" to count))

/** `DeliveryWidgetList.link`: the deliveries, through the app's routing, in the app's window. */
internal fun listIntent(context: Context): Intent = Intent(Intent.ACTION_VIEW, DeliveryWidgetList.link.toUri(), context, MainActivity::class.java)

internal fun openList(context: Context): Action = actionStartActivity(listIntent(context))

/**
 * `ParcelListStatus`'s `ViewThatFits`: a row's line where it fits `width`, the stage and the day; else the day alone;
 * else the stage, cut short. What opens the line takes a capital, "Tomorrow" ([openingLine]).
 */
internal fun fittingLine(parcel: DeliveryWidgetParcel, layout: ListLayout, width: Dp): String {
    val whole = parcel.listLine.openingLine(layout.locale)
    val day = parcel.listDay?.openingLine(layout.locale) ?: return whole
    fun fits(text: String) = layout.type.width(text, layout.statusPoints, FontWeight.Normal) <= width
    return when {
        fits(whole) -> whole
        fits(day) -> day
        else -> parcel.listStatus.openingLine(layout.locale)
    }
}

/** `listAccessibilityLabel`: what TalkBack reads for a row, the parcel's name, its carrier, its stage and its day. */
internal fun rowDescription(parcel: DeliveryWidgetParcel): String =
    listOfNotNull(parcel.listName, parcel.carrier.takeIf { parcel.named != false }, parcel.listStatus, parcel.listDay).joinToString(", ")

/** The size of a row's `line`, `room` wide: the layout's, smaller where it takes that to fit ([lineFit]). */
internal fun linePoints(layout: ListLayout, line: String, room: Dp): Float = layout.type.lineFit(line, layout.statusPoints, room)

/** What TalkBack reads for a row, a pass or a card of the list: "New update." first while it shows the dot (#72). */
internal fun spokenRow(parcel: DeliveryWidgetParcel, languageCode: String): String =
    newUpdateSpoken(parcel, languageCode)?.let { "$it ${rowDescription(parcel)}" } ?: rowDescription(parcel)

/**
 * The stage and day a large widget's row ends in, `ParcelListLineRow`'s: the whole line where it fits beside the name,
 * else what fits beside the name kept to [NAME_SHARE] of the widget ([fittingLine]); its width, its size ([linePoints]),
 * and the gap before it, and before the line the new-update dot where the parcel has one ([dotRoom]).
 */
internal class LineRowEnd(val line: String, val width: Dp, val gap: Dp, val points: Float)

internal fun lineRowEnd(parcel: DeliveryWidgetParcel, layout: ListLayout): LineRowEnd {
    val type = layout.type
    val spacing = layout.metrics.markSpacing
    val name = type.width(parcel.listName, layout.metrics.namePoints, FontWeight.Medium)
    val whole = parcel.listLine.openingLine(layout.locale)
    val wholeWidth = type.width(whole, layout.statusPoints, FontWeight.Normal)
    val dot = parcel.dotRoom
    // The name, a spacer at least END_GAP wide and the line, spaced as the symbol and the name.
    val apart = spacing * 2 + END_GAP
    if (name + apart + dot + wholeWidth <= layout.words) return LineRowEnd(whole, wholeWidth, apart, layout.statusPoints)
    val room = (layout.words - spacing - END_GAP - layout.room.width * NAME_SHARE - dot).coerceAtLeast(0.dp)
    val line = fittingLine(parcel, layout, room)
    val points = linePoints(layout, line, room)
    return LineRowEnd(line, minOf(type.width(line, points, FontWeight.Normal), room), spacing + END_GAP, points)
}

/**
 * A parcel's row, `top` below the one before it (Glance's padding is inside a view's height): the stage's symbol in its
 * colour, then its name over its stage and day (`ParcelListStackedRow`), or, in a large widget, its name and then its
 * stage and day at the row's end ([lineRowEnd]). The line, in `lineColor`, starts with the new-update dot where the
 * parcel has one (#72), and keeps to the room left to it, at a smaller size where it must ([linePoints]).
 */
@Composable
internal fun ListRow(
    parcel: DeliveryWidgetParcel,
    layout: ListLayout,
    top: Dp,
    lineColor: AdaptiveColor = SystemColors.secondaryLabel,
    mark: @Composable () -> Unit = { StageSymbol(parcel, layout.metrics) },
) {
    val context = LocalContext.current
    val name = parcel.listName
    val type = layout.type
    val metrics = layout.metrics
    val nameStyle = TextStyle(color = ListColors.primary, fontSize = type.sp(metrics.namePoints), fontWeight = FontWeight.Medium)
    val lineStyle = TextStyle(color = ListColors.of(lineColor), fontSize = type.sp(layout.statusPoints))
    val dot = if (parcel.newUpdate == true) ListColors.of(lineColor, NEW_UPDATE_OPACITY) else null
    Row(
        GlanceModifier.fillMaxWidth().height(layout.rowHeight + top).padding(top = top).semantics { contentDescription = spokenRow(parcel, layout.languageCode) }
            .clickable(openParcel(context, parcel)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        mark()
        Spacer(GlanceModifier.width(metrics.markSpacing))
        if (layout.lineRows) {
            val end = lineRowEnd(parcel, layout)
            Text(name, GlanceModifier.defaultWeight(), maxLines = 1, style = nameStyle)
            Spacer(GlanceModifier.width(end.gap))
            dot?.let { NewUpdateDot(it) }
            Text(end.line, GlanceModifier.width(end.width), maxLines = 1, style = lineStyle.copy(fontSize = type.sp(end.points), textAlign = TextAlign.End))
        } else {
            Column(GlanceModifier.defaultWeight()) {
                Text(name, maxLines = 1, style = nameStyle)
                Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    val room = layout.words - parcel.dotRoom
                    val line = fittingLine(parcel, layout, room)
                    dot?.let { NewUpdateDot(it) }
                    Text(line, GlanceModifier.defaultWeight(), maxLines = 1, style = lineStyle.copy(fontSize = type.sp(linePoints(layout, line, room))))
                }
            }
        }
    }
}

/**
 * `ParcelListMark`: the stage's symbol in its colour, semibold, fitted to the metrics' box, drawn once in white and tinted
 * light and dark; from an app that didn't publish the stage, the parcel's state in grey.
 */
@Composable
internal fun StageSymbol(parcel: DeliveryWidgetParcel, metrics: ListMetrics) {
    val stage = parcel.stage
    val symbol = stage?.symbol?.takeIf { it in materialSymbols }
        ?: if (parcel.isOutForDelivery) "bicycle" else if (parcel.deliveredAt != null) "checkmark.circle.fill" else "shippingbox.fill"
    // `.resizable().scaledToFit()`: the symbol's image as large as the box holds.
    val unit = requireNotNull(materialSymbols[symbol]).box(1f)
    val points = minOf(metrics.markWidth.value / unit[0], metrics.markHeight.value / unit[1])
    Box(GlanceModifier.width(metrics.markWidth).height(metrics.markHeight), contentAlignment = Alignment.Center) {
        TintedSymbol(symbol, points, ComposeFontWeight.SemiBold, stage?.let { WidgetCardColors.pair(it.tint) } ?: ListColors.secondary)
    }
}

@Composable
internal fun TintedSymbol(name: String, points: Float, weight: ComposeFontWeight, tint: ColorProvider) {
    val context = LocalContext.current
    val art = requireNotNull(materialSymbols[name])
    val box = art.box(points)
    val bitmap = remember(name, points, weight) {
        bitmap(context, box[0], box[1]) { drawSymbol(art, points, weight.weight, Color.White, SymbolRenderingMode.Monochrome) }
    }
    Image(ImageProvider(bitmap), contentDescription = null, modifier = GlanceModifier.width(box[0].dp).height(box[1].dp), colorFilter = ColorFilter.tint(tint))
}

/**
 * Nothing to list, or the widget switched off, `ParcelListState`: the existing widget's symbol, title and words, at the
 * leading edge and centred down; the words on three lines at most in a narrow widget, else two. It opens the app.
 */
@Composable
private fun ListState(symbol: String, title: String, description: String, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    val room = DpSize(size.width - MARGIN * 2, size.height - MARGIN * 2)
    val symbolPoints = type.points(IosTextStyle.Title3)
    val symbolHeight = requireNotNull(materialSymbols[symbol]).box(symbolPoints)[1].dp
    val caption = type.points(IosTextStyle.Caption)
    val titlePoints = type.fit(title, type.points(IosTextStyle.Headline), FontWeight.Bold, room.width, 2, 0.82f)
    val titleHeight = type.height(title, titlePoints, FontWeight.Bold, room.width, 2)
    val left = room.height - symbolHeight - titleHeight - STATE_SPACING * 2
    val first = type.lineHeight(caption, FontWeight.Normal)
    val next = type.height("1\n2", caption, FontWeight.Normal, room.width, 2) - first
    val most = if (size.width < WIDE_WIDTH) 3 else 2
    val lines = if (left < first) 0 else minOf(most, 1 + floor((left - first) / next).toInt())
    Column(GlanceModifier.fillMaxSize().clickable(openList(context)), verticalAlignment = Alignment.CenterVertically) {
        TintedSymbol(symbol, symbolPoints, ComposeFontWeight.Bold, ListColors.primary)
        Spacer(GlanceModifier.height(STATE_SPACING))
        Text(title, maxLines = 2, style = TextStyle(color = ListColors.primary, fontSize = type.sp(titlePoints), fontWeight = FontWeight.Bold))
        if (lines > 0) {
            Spacer(GlanceModifier.height(STATE_SPACING))
            Text(description, maxLines = lines, style = TextStyle(color = ListColors.secondary, fontSize = type.sp(caption)))
        }
    }
}

/** The narrowest list that stands for the iPhone's medium or large widget: four cells across on a phone. */
private val WIDE_WIDTH = 250.dp

/** The shortest list that stands for the iPhone's large widget: three cells down on a phone. */
private val TALL_HEIGHT = 280.dp

/** WidgetKit's margins around a widget's content. */
private val MARGIN = 16.dp
private val COLUMN_GAP = 12.dp
private val STATE_SPACING = 6.dp

/** The least room between a large widget's name and the stage and day at its row's end. */
private val END_GAP = 4.dp

/** The share of a large widget's width its names keep where a line can't hold the whole stage and day beside them. */
private const val NAME_SHARE = 0.4f
