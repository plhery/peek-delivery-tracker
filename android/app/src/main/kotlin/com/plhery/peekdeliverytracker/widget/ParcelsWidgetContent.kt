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
import androidx.glance.layout.size
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
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSelection
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
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.SystemColors
import com.plhery.peekdeliverytracker.ui.theme.toColor
import java.util.Locale
import kotlin.math.floor

// The "Parcels" widget's look: the parcels on their way as the app's list shows them, one row each, on the app's page,
// as many as the widget's height holds (15 at most), then a line counting the rest. Each row is the stage's symbol in
// its colour, the parcel's name, and its stage and day, as the app published them (DeliveryWidgetStage). Narrow, a row
// takes two lines, the name over the stage and day; wide, one, the stage and day at its end. Text is the existing
// widget's (WidgetText), and as there, the layout works out from the measured text how many rows it keeps. Names keep
// one size down the list and end in an ellipsis where they are too long; a line too long for its room keeps the day.

/**
 * The widget's colours, light and dark as the launcher's mode picks: the app's page, and on it the system's label
 * colours the iPhone's list widget writes in (`.primary`, `.secondary`).
 */
private object ListColors {
    /** `color` light and dark, its own opacity times `alpha`. */
    fun of(color: AdaptiveColor, alpha: Float = 1f): ColorProvider =
        ColorProvider(day = color.light.toColor().faded(alpha), night = color.dark.toColor().faded(alpha))

    private fun Color.faded(alpha: Float): Color = copy(alpha = this.alpha * alpha)

    val page = of(Brand.background)
    val primary = of(SystemColors.label)
    val secondary = of(SystemColors.secondaryLabel)
}

/**
 * What the list keeps at `size`: whether rows take one line ([wide]) or two, how many it shows and how many the last
 * line counts, and whether the app's header stands above them, which goes first where it would cost a row.
 */
internal class ListLayout(list: DeliveryWidgetList, size: DpSize, val type: WidgetText) {
    val locale: Locale = ExtensionLocalizer.locale(list.languageCode)
    val wide = size.width >= WIDE_WIDTH
    val room = DpSize(size.width - MARGIN * 2, size.height - MARGIN_VERTICAL * 2)
    val namePoints = type.points(IosTextStyle.Subheadline)
    val linePoints = type.points(IosTextStyle.Caption)
    val rowHeight: Dp = type.lineHeight(namePoints, FontWeight.Medium).let { name ->
        if (wide) maxOf(name, ICON) else name + type.lineHeight(linePoints, FontWeight.Normal)
    }
    val gap = if (wide) WIDE_GAP else NARROW_GAP
    val moreHeight = type.lineHeight(linePoints, FontWeight.Medium)
    val header: Boolean
    val shown: DeliveryWidgetList.Shown

    /** The width a row leaves its words, past the stage's symbol. */
    val words = room.width - ICON - ICON_GAP

    init {
        fun shown(height: Dp): DeliveryWidgetList.Shown =
            list.shown(rows(height).coerceAtMost(DeliveryWidgetSelection.listLimit), rows(height - moreHeight - gap))
        val withHeader = shown(room.height - headerHeight(type) - HEADER_GAP)
        val without = shown(room.height)
        header = withHeader.rows.size >= without.rows.size
        shown = if (header) withHeader else without
    }

    /** How many rows stand in `height`. */
    private fun rows(height: Dp): Int = if (height < rowHeight) 0 else 1 + floor((height - rowHeight) / (rowHeight + gap)).toInt()
}

/**
 * The list at the widget's `size`: its rows, each opening its parcel, and the count of the rest, which opens the app's
 * list ([DeliveryWidgetList.link]), as the page behind them does; or why there are none, in the existing widget's words.
 */
@Composable
fun ParcelsWidgetContent(list: DeliveryWidgetList, size: DpSize) {
    val context = LocalContext.current
    val copy = remember(list.languageCode) { Localizer(AppLanguage.fromRawValue(list.languageCode) ?: AppLanguage.En, variant = "android") }
    val type = WidgetText(context)
    Box(root().background(ListColors.page)) {
        PageTap(listIntent(context))
        Box(GlanceModifier.fillMaxSize().padding(horizontal = MARGIN, vertical = MARGIN_VERTICAL)) {
            when {
                !list.enabled -> ListState("rectangle.slash", copy.text("widget.disabledTitle"), copy.text("widget.disabledDescription"), size, type)
                list.parcels.isEmpty() -> ListState("shippingbox", copy.text("app.noneOnWay"), copy.text("app.emptyDescription"), size, type)
                else -> Rows(ListLayout(list, size, type), copy)
            }
        }
    }
}

/** The rows, in groups of five, as a layout of Glance's shows ten children at most. */
@Composable
private fun Rows(layout: ListLayout, copy: Localizer) {
    val context = LocalContext.current
    Column(GlanceModifier.fillMaxSize()) {
        if (layout.header) ListHeader(layout.type, copy)
        layout.shown.rows.chunked(5).forEachIndexed { group, rows ->
            Column(GlanceModifier.fillMaxWidth()) {
                rows.forEachIndexed { index, parcel ->
                    ListRow(parcel, layout, top = if (group == 0 && index == 0) 0.dp else layout.gap)
                }
            }
        }
        if (layout.shown.more > 0) {
            val more = moreParcels(copy, layout.shown.more)
            Text(
                more,
                GlanceModifier.fillMaxWidth().padding(start = ICON + ICON_GAP, top = layout.gap).clickable(openList(context)),
                maxLines = 1,
                style = TextStyle(color = ListColors.secondary, fontSize = layout.type.sp(layout.linePoints), fontWeight = FontWeight.Medium),
            )
        }
    }
}

/** The count of the parcels past the rows: "+12 more", in the language's plural. */
internal fun moreParcels(copy: Localizer, count: Int): String = copy.text("widget.listMore.many", mapOf("count" to count))

/** `DeliveryWidgetList.link`: the deliveries, through the app's routing, in the app's window. */
internal fun listIntent(context: Context): Intent = Intent(Intent.ACTION_VIEW, DeliveryWidgetList.link.toUri(), context, MainActivity::class.java)

private fun openList(context: Context): Action = actionStartActivity(listIntent(context))

/**
 * `ParcelListStatus`'s `ViewThatFits`: a row's line where it fits `width`, the stage and the day; else the day alone;
 * else the stage, cut short. What opens the line takes a capital, "Tomorrow" ([openingLine]).
 */
internal fun fittingLine(parcel: DeliveryWidgetParcel, layout: ListLayout, width: Dp): String {
    val whole = parcel.listLine.openingLine(layout.locale)
    val day = parcel.listDay?.openingLine(layout.locale) ?: return whole
    fun fits(text: String) = layout.type.width(text, layout.linePoints, FontWeight.Normal) <= width
    return when {
        fits(whole) -> whole
        fits(day) -> day
        else -> parcel.listStatus.openingLine(layout.locale)
    }
}

/** `listAccessibilityLabel`: what TalkBack reads for a row, the parcel's name, its carrier, its stage and its day. */
internal fun rowDescription(parcel: DeliveryWidgetParcel): String =
    listOfNotNull(parcel.listName, parcel.carrier.takeIf { parcel.named != false }, parcel.listStatus, parcel.listDay).joinToString(", ")

/**
 * A parcel's row, `top` below the one before it (Glance's padding is inside a view's height): the stage's symbol in its
 * colour, then its name over its stage and day, or, wide, its name and then its stage and day at the row's end, given
 * what the name leaves them and at least [LINE_SHARE] of the row ([fittingLine]).
 */
@Composable
private fun ListRow(parcel: DeliveryWidgetParcel, layout: ListLayout, top: Dp) {
    val context = LocalContext.current
    val name = parcel.listName
    val type = layout.type
    val nameStyle = TextStyle(color = ListColors.primary, fontSize = type.sp(layout.namePoints), fontWeight = FontWeight.Medium)
    Row(
        GlanceModifier.fillMaxWidth().height(layout.rowHeight + top).padding(top = top).semantics { contentDescription = rowDescription(parcel) }.clickable(openParcel(context, parcel)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StageSymbol(parcel)
        Spacer(GlanceModifier.width(ICON_GAP))
        if (layout.wide) {
            val words = layout.words - ROW_GAP
            val room = maxOf(words - type.width(name, layout.namePoints, FontWeight.Medium), words * LINE_SHARE)
            val line = fittingLine(parcel, layout, room)
            val lineWidth = minOf(type.width(line, layout.linePoints, FontWeight.Normal), room)
            Text(name, GlanceModifier.defaultWeight(), maxLines = 1, style = nameStyle)
            Spacer(GlanceModifier.width(ROW_GAP))
            Text(
                line, GlanceModifier.width(lineWidth), maxLines = 1,
                style = TextStyle(color = ListColors.secondary, fontSize = type.sp(layout.linePoints), textAlign = TextAlign.End),
            )
        } else {
            Column(GlanceModifier.defaultWeight()) {
                Text(name, maxLines = 1, style = nameStyle)
                Text(fittingLine(parcel, layout, layout.words), maxLines = 1, style = TextStyle(color = ListColors.secondary, fontSize = type.sp(layout.linePoints)))
            }
        }
    }
}

/**
 * `ParcelListMark`: the stage's symbol in its colour, drawn once in white and tinted light and dark; from an app that
 * didn't publish the stage, the parcel's state in grey.
 */
@Composable
private fun StageSymbol(parcel: DeliveryWidgetParcel) {
    val stage = parcel.stage
    val symbol = stage?.symbol?.takeIf { it in materialSymbols }
        ?: if (parcel.isOutForDelivery) "bicycle" else if (parcel.deliveredAt != null) "checkmark.circle.fill" else "shippingbox.fill"
    Box(GlanceModifier.width(ICON), contentAlignment = Alignment.Center) {
        TintedSymbol(symbol, SYMBOL_POINTS, stage?.let { WidgetCardColors.pair(it.tint) } ?: ListColors.secondary)
    }
}

@Composable
private fun TintedSymbol(name: String, points: Float, tint: ColorProvider) {
    val context = LocalContext.current
    val art = requireNotNull(materialSymbols[name])
    val box = art.box(points)
    val bitmap = remember(name, points) {
        bitmap(context, box[0], box[1]) { drawSymbol(art, points, ComposeFontWeight.Medium.weight, Color.White, SymbolRenderingMode.Monochrome) }
    }
    Image(ImageProvider(bitmap), contentDescription = null, modifier = GlanceModifier.width(box[0].dp).height(box[1].dp), colorFilter = ColorFilter.tint(tint))
}

/** The app's mark and name over the rows, as the existing widget's header. */
@Composable
private fun ListHeader(type: WidgetText, copy: Localizer) {
    val context = LocalContext.current
    val mark = remember { peekMark(context) }
    Column(GlanceModifier.fillMaxWidth()) {
        Row(GlanceModifier.fillMaxWidth().height(headerHeight(type)), verticalAlignment = Alignment.CenterVertically) {
            Image(ImageProvider(mark), contentDescription = null, modifier = GlanceModifier.size(HEADER_MARK))
            Spacer(GlanceModifier.width(6.dp))
            val name = copy.text("app.title").uppercase(copy.language.locale)
            Text(
                name, maxLines = 1,
                style = TextStyle(color = ListColors.of(Brand.ink, 0.72f), fontSize = type.sp(type.points(IosTextStyle.Caption2)), fontWeight = FontWeight.Bold),
            )
        }
        Spacer(GlanceModifier.height(HEADER_GAP))
    }
}

/** Nothing to list, or the widget switched off: the existing widget's symbol, title and words, centred; it opens the app. */
@Composable
private fun ListState(symbol: String, title: String, description: String, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    val room = DpSize(size.width - MARGIN * 2, size.height - MARGIN_VERTICAL * 2)
    val symbolPoints = type.points(IosTextStyle.Title3)
    val symbolHeight = requireNotNull(materialSymbols[symbol]).box(symbolPoints)[1].dp
    val caption = type.points(IosTextStyle.Caption)
    val titlePoints = type.fit(title, type.points(IosTextStyle.Headline), FontWeight.Bold, room.width, 2, 0.82f)
    val titleHeight = type.height(title, titlePoints, FontWeight.Bold, room.width, 2)
    val left = room.height - symbolHeight - titleHeight - 12.dp
    val first = type.lineHeight(caption, FontWeight.Normal)
    val next = type.height("1\n2", caption, FontWeight.Normal, room.width, 2) - first
    val lines = if (left < first) 0 else minOf(3, 1 + floor((left - first) / next).toInt())
    Column(GlanceModifier.fillMaxSize().clickable(openList(context)), verticalAlignment = Alignment.CenterVertically) {
        TintedSymbol(symbol, symbolPoints, ListColors.primary)
        Spacer(GlanceModifier.height(6.dp))
        Text(title, maxLines = 2, style = TextStyle(color = ListColors.primary, fontSize = type.sp(titlePoints), fontWeight = FontWeight.Bold))
        if (lines > 0) {
            Spacer(GlanceModifier.height(6.dp))
            Text(description, maxLines = lines, style = TextStyle(color = ListColors.secondary, fontSize = type.sp(caption)))
        }
    }
}

private fun headerHeight(type: WidgetText): Dp = maxOf(HEADER_MARK, type.lineHeight(type.points(IosTextStyle.Caption2), FontWeight.Bold))

/** The narrowest list whose rows take one line, the stage and day at their end: four cells across on a phone. */
private val WIDE_WIDTH = 250.dp
private val MARGIN = 14.dp
private val MARGIN_VERTICAL = 12.dp
private val HEADER_GAP = 6.dp
private val NARROW_GAP = 8.dp
private val WIDE_GAP = 4.dp
private val ICON = 20.dp
private val ICON_GAP = 8.dp
private val ROW_GAP = 8.dp
private const val SYMBOL_POINTS = 14f
private const val LINE_SHARE = 0.45f
