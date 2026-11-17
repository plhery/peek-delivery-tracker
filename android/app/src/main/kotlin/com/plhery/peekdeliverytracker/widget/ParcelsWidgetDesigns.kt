package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
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
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.CarrierTruckGeometry
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity
import com.plhery.peekdeliverytracker.core.ClockPreference
import com.plhery.peekdeliverytracker.core.DeliveryWidgetCard
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPicture
import com.plhery.peekdeliverytracker.core.DeliveryWidgetPictures
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSelection
import com.plhery.peekdeliverytracker.core.DeliveryWidgetStep
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.listDay
import com.plhery.peekdeliverytracker.core.listLine
import com.plhery.peekdeliverytracker.core.listName
import com.plhery.peekdeliverytracker.core.openingLine
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.draw.drawMapDrawings
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import kotlin.math.floor

// The "Parcels" widget's designs ([DeliveryWidgetList.design]), drawn from the app's design system as the iPhone's
// list widget draws them: the iPhone's cards for a few parcels, the app list's cards made small (passes)
// for a few more where every one fits, and rows under the app list's sections for the rest. Cards and passes are in
// each parcel's carrier colours, from the pictures the app draws for the widget (WidgetCardPictures).

/**
 * The pictures `count` cards show at the widget's size: one alone fills a small or medium widget as the iPhone's does,
 * two stand side by side; at the large size one or two are wide cards, three the app list's cards with half routes.
 */
internal fun cardShape(listSize: DeliveryWidgetList.Size, count: Int, size: DpSize): DeliveryWidgetPicture = when {
    listSize == DeliveryWidgetList.Size.Large -> if (count >= 3) DeliveryWidgetPicture.Half else DeliveryWidgetPicture.Wide
    count >= 2 -> DeliveryWidgetPicture.Half
    isMedium(size) -> DeliveryWidgetPicture.Wide
    else -> DeliveryWidgetPicture.Small
}

// Cards.

/**
 * The parcels as cards, the app list's Next up first ([DeliveryWidgetList.cardOrder]), each with its pictures `art`:
 * in a small or medium widget one, or two side by side ([OneOrTwoCards]); in a large one a card over the parcel's last
 * steps, two wide cards one above the other, or three of the app list's cards.
 */
@Composable
internal fun ListCards(list: DeliveryWidgetList, parcels: List<DeliveryWidgetParcel>, art: List<WidgetCardArt>, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    when {
        listSize(size) != DeliveryWidgetList.Size.Large -> {
            val cards = parcels.zip(art).mapNotNull { (parcel, pictures) -> parcel.card?.let { Triple(parcel, it, pictures) } }
            OneOrTwoCards(list.languageCode, cards, size, type, listIntent(context))
        }
        parcels.size == 1 -> CardAndSteps(list, parcels.single(), art.single(), size, type)
        parcels.size == 2 -> WideCards(list, parcels, art, size, type)
        else -> ThreeCards(list, parcels, art, size, type)
    }
}

/**
 * One parcel in a large widget: its wide card across the top third, and under it, on the card's colour, its last steps,
 * latest first, joined by a thread; its card alone where it has no steps.
 */
@Composable
private fun CardAndSteps(list: DeliveryWidgetList, parcel: DeliveryWidgetParcel, art: WidgetCardArt, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    val card = parcel.card ?: return
    val steps = parcel.steps.orEmpty()
    val top = DpSize(size.width, if (steps.isEmpty()) size.height else size.height / 3)
    val words = CardWords(parcel, card, DeliveryWidgetPicture.Wide, room = top.inside(PAGE_MARGIN), type, list.languageCode)
    Box(root().background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))) {
        Column(GlanceModifier.fillMaxSize()) {
            Box(GlanceModifier.fillMaxWidth().height(top.height)) {
                CardBackground(art, DeliveryWidgetPicture.Wide, top)
                Box(GlanceModifier.fillMaxSize().padding(PAGE_MARGIN)) { CardView(parcel, card, art, words) }
            }
            if (steps.isNotEmpty()) Steps(list, steps, card, size.height - top.height - PAGE_MARGIN, type)
        }
    }
}

/**
 * The steps that fit `height`, four at most: a dot on the thread, the brightest for the latest; the words the parcel's
 * page gives them, semibold for the latest, and the time at the end in the card's ink ([DeliveryWidgetStep.time]: the
 * time alone today, else after the weekday, and the weekday alone for a step dated by its day alone), on the app's
 * clock ([Device.appUses24HourTime]); the place under them in the app's quieter grey, as on the iPhone.
 */
@Composable
private fun Steps(list: DeliveryWidgetList, steps: List<DeliveryWidgetStep>, card: DeliveryWidgetCard, height: Dp, type: WidgetText) {
    val use24HourTime = Device.appUses24HourTime(LocalContext.current)
    val copy = remember(list.languageCode, use24HourTime) {
        val clock = if (use24HourTime) ClockPreference.TwentyFourHour else ClockPreference.TwelveHour
        Localizer(AppLanguage.fromRawValue(list.languageCode) ?: AppLanguage.En, clockPreference = clock)
    }
    val footnote = type.points(IosTextStyle.Footnote)
    val caption = type.points(IosTextStyle.Caption)
    val caption2 = type.points(IosTextStyle.Caption2)
    val textHeight = type.lineHeight(footnote, FontWeight.Medium)
    val stepHeight = textHeight + type.lineHeight(caption2, FontWeight.Normal)
    val kept = steps.take(fitting(height - STEP_TOP, stepHeight, STEP_GAP).coerceAtMost(DeliveryWidgetSelection.steps))
    val ink = WidgetCardColors.pair(card.ink)
    val thread = WidgetCardColors.pair(card.ink, 0.28f)
    val dotTop = (textHeight - STEP_DOT) / 2
    Column(GlanceModifier.fillMaxWidth().padding(start = PAGE_MARGIN, end = PAGE_MARGIN, top = STEP_TOP)) {
        kept.forEachIndexed { index, step ->
            val last = index == kept.lastIndex
            Row(GlanceModifier.fillMaxWidth().height(stepHeight + if (last) 0.dp else STEP_GAP)) {
                Column(GlanceModifier.width(STEP_THREAD_BOX).fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                    if (index == 0) Spacer(GlanceModifier.height(dotTop)) else Box(GlanceModifier.width(STEP_THREAD).height(dotTop).background(thread)) {}
                    Box(GlanceModifier.size(STEP_DOT).cornerRadius(STEP_DOT / 2).background(if (index == 0) ink else WidgetCardColors.pair(card.ink, 0.5f))) {}
                    if (!last) Box(GlanceModifier.width(STEP_THREAD).defaultWeight().background(thread)) {}
                }
                Spacer(GlanceModifier.width(STEP_THREAD_GAP))
                Column(GlanceModifier.defaultWeight()) {
                    Row(GlanceModifier.fillMaxWidth().height(textHeight), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            step.text, GlanceModifier.defaultWeight(), maxLines = 1,
                            style = TextStyle(color = WidgetCardColors.primary, fontSize = type.sp(footnote), fontWeight = if (index == 0) FontWeight.Medium else FontWeight.Normal),
                        )
                        Spacer(GlanceModifier.width(STEP_TIME_GAP))
                        Text(step.time(list.date, copy), maxLines = 1, style = TextStyle(color = ink, fontSize = type.sp(caption), fontWeight = FontWeight.Medium))
                    }
                    step.place?.let { Text(it, maxLines = 1, style = TextStyle(color = ListColors.secondary, fontSize = type.sp(caption2))) }
                }
            }
        }
    }
}

/** Two parcels in a large widget: two wide cards, one above the other, half the height each, on the app's page. */
@Composable
private fun WideCards(list: DeliveryWidgetList, parcels: List<DeliveryWidgetParcel>, art: List<WidgetCardArt>, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    val each = DpSize(size.width - PAGE_MARGIN * 2, (size.height - PAGE_MARGIN * 2 - CARD_GAP) / 2)
    Box(root().background(WidgetCardColors.page)) {
        PageTap(listIntent(context))
        Column(GlanceModifier.fillMaxSize().padding(PAGE_MARGIN)) {
            parcels.zip(art).forEachIndexed { index, (parcel, pictures) ->
                if (index > 0) Spacer(GlanceModifier.height(CARD_GAP))
                val card = parcel.card ?: return@forEachIndexed
                val words = CardWords(parcel, card, DeliveryWidgetPicture.Wide, room = each.inside(WIDE_CARD_PADDING), type, list.languageCode)
                Box(
                    GlanceModifier.fillMaxWidth().defaultWeight().cornerRadius(16.dp).background(WidgetCardColors.pair(card.surface))
                        .clickable(openParcel(context, parcel)),
                ) {
                    CardBackground(pictures, DeliveryWidgetPicture.Wide, each)
                    Box(GlanceModifier.fillMaxSize().padding(WIDE_CARD_PADDING)) { CardView(parcel, card, pictures, words) }
                }
            }
        }
    }
}

/** Three parcels in a large widget: three of the app list's cards, one above another, a third of the height each. */
@Composable
private fun ThreeCards(list: DeliveryWidgetList, parcels: List<DeliveryWidgetParcel>, art: List<WidgetCardArt>, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    Box(root().background(WidgetCardColors.page)) {
        PageTap(listIntent(context))
        Column(GlanceModifier.fillMaxSize().padding(PAGE_MARGIN)) {
            parcels.zip(art).forEachIndexed { index, (parcel, pictures) ->
                if (index > 0) Spacer(GlanceModifier.height(CARD_GAP))
                Box(GlanceModifier.fillMaxWidth().defaultWeight()) { ListCard(parcel, pictures, size.width - PAGE_MARGIN * 2, type, list.languageCode) }
            }
        }
    }
}

/**
 * `ParcelPassCard` made small, `width` wide: the carrier's mark and the day at the top, the name and the status at the
 * bottom, the status after the new-update dot where the parcel has one (#72), the half route at the end.
 */
@Composable
private fun ListCard(parcel: DeliveryWidgetParcel, art: WidgetCardArt, width: Dp, type: WidgetText, languageCode: String) {
    val context = LocalContext.current
    val card = parcel.card ?: return
    val locale = ExtensionLocalizer.locale(languageCode)
    val ink = WidgetCardColors.pair(card.ink)
    val caption = type.points(IosTextStyle.Caption)
    val inner = width - LIST_CARD_PADDING * 2
    val day = parcel.listDay?.openingLine(locale)
    val dayWidth = day?.let { type.width(it, caption, FontWeight.Medium) + 8.dp } ?: 0.dp
    Box(
        GlanceModifier.fillMaxSize().cornerRadius(16.dp).background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))
            .semantics { contentDescription = spokenRow(parcel, languageCode) },
    ) {
        art.route?.let { route ->
            val frame = DeliveryWidgetPictures.frame(DeliveryWidgetPicture.Half)
            val faded = remember(route) { fadedSides(route.first, frame.size.width) to fadedSides(route.second, frame.size.width) }
            Box(GlanceModifier.fillMaxSize().padding(top = 8.dp, end = 4.dp), contentAlignment = Alignment.CenterEnd) {
                DayNightPicture(faded, R.layout.widget_route, GlanceModifier.width(frame.size.width.dp).height(frame.size.height.dp), description = null)
            }
        }
        Column(GlanceModifier.fillMaxSize().padding(horizontal = LIST_CARD_PADDING, vertical = 12.dp)) {
            Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                DayNightPicture(art.mark, R.layout.widget_mark, markSize(art, LIST_CARD_MARK, inner - dayWidth), description = parcel.carrier)
                Spacer(GlanceModifier.defaultWeight())
                if (day != null) Text(day, maxLines = 1, style = TextStyle(color = ink, fontSize = type.sp(caption), fontWeight = FontWeight.Medium))
            }
            Spacer(GlanceModifier.defaultWeight())
            Column(GlanceModifier.width(minOf(inner, LIST_CARD_WORDS))) {
                Text(parcel.listName, maxLines = 1, style = TextStyle(color = WidgetCardColors.primary, fontSize = type.sp(type.points(IosTextStyle.Headline)), fontWeight = FontWeight.Medium))
                Spacer(GlanceModifier.height(2.dp))
                Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    if (parcel.newUpdate == true) NewUpdateDot(WidgetCardColors.pair(card.ink, NEW_UPDATE_OPACITY))
                    val room = minOf(inner, LIST_CARD_WORDS) - parcel.dotRoom
                    val status = type.fittingStatus(parcel, locale, caption, room)
                    Text(status, GlanceModifier.defaultWeight(), maxLines = 1, style = TextStyle(color = ink, fontSize = type.sp(type.lineFit(status, caption, room))))
                }
            }
        }
    }
}

// Passes.

/** A pass's type and its least height: its padding, the mark's row, then the name over the line, or both on one line. */
private class PassMetrics(type: WidgetText, val oneLine: Boolean) {
    val caption2 = type.points(IosTextStyle.Caption2)
    val name = type.points(IosTextStyle.Subheadline)
    val nameHeight = type.lineHeight(name, FontWeight.Medium)
    val lineHeight = type.lineHeight(caption2, FontWeight.Normal)
    val top: Dp = if (oneLine) maxOf(PASS_MARK, lineHeight) else PASS_MARK
    val height: Dp = PASS_PADDING_VERTICAL * 2 + top + PASS_SPACING + if (oneLine) maxOf(nameHeight, lineHeight) else nameHeight + lineHeight
}

/** One column of passes in a small or large widget, two in a medium one. */
private fun passColumns(listSize: DeliveryWidgetList.Size): Int = if (listSize == DeliveryWidgetList.Size.Medium) 2 else 1

private fun passMetrics(listSize: DeliveryWidgetList.Size, type: WidgetText) = PassMetrics(type, oneLine = listSize == DeliveryWidgetList.Size.Large)

/**
 * Whether `count` passes all stand in the widget at `size`, one above another down their columns: passes show every
 * parcel or none, and where they don't all fit, at larger text or in a shorter widget, the parcels are rows
 * ([DeliveryWidgetList.fits], [SectionsLayout]).
 */
internal fun passesFit(count: Int, size: DpSize, type: WidgetText): Boolean {
    val listSize = listSize(size)
    val columns = passColumns(listSize)
    return fitting(size.height - PAGE_MARGIN * 2, passMetrics(listSize, type).height, PASS_GAP) >= (count + columns - 1) / columns
}

/**
 * Every parcel's pass, in the widget's order, each with its mark `art`: one column in a small or large widget, two in a
 * medium one, the first column holding the fewer, so that three put the first parcel alone. They share the height,
 * which holds them all ([passesFit]).
 */
@Composable
internal fun Passes(list: DeliveryWidgetList, art: List<WidgetCardArt>, size: DpSize, type: WidgetText) {
    val context = LocalContext.current
    val listSize = listSize(size)
    val columns = passColumns(listSize)
    val metrics = passMetrics(listSize, type)
    val parcels = list.parcels.zip(art)
    val filled = if (columns == 2) listOf(parcels.take(parcels.size / 2), parcels.drop(parcels.size / 2)) else listOf(parcels)
    val width = (size.width - PAGE_MARGIN * 2 - PASS_GAP * (columns - 1)) / columns
    Box(root().background(ListColors.page)) {
        PageTap(listIntent(context))
        // Each pass stands in half the gap above and below it, the page's margins less as much.
        Row(GlanceModifier.fillMaxSize().padding(horizontal = PAGE_MARGIN, vertical = PAGE_MARGIN - PASS_GAP / 2)) {
            filled.forEachIndexed { index, column ->
                if (index > 0) Spacer(GlanceModifier.width(PASS_GAP))
                Column(GlanceModifier.defaultWeight().fillMaxHeight()) {
                    column.forEach { (parcel, pictures) ->
                        Box(GlanceModifier.fillMaxWidth().defaultWeight().padding(vertical = PASS_GAP / 2)) { Pass(parcel, pictures, metrics, width, type, list.languageCode) }
                    }
                }
            }
        }
    }
}

/**
 * A parcel's pass, `width` wide, in its carrier's colours: the mark at the top, then the name, over the day, or the
 * stage and day where both fit; in a large widget the day at the top's end, then the name and the stage on one line.
 * The stage or day starts with the new-update dot where the parcel has one (#72), and keeps to the room left to it,
 * at a smaller size where it must ([lineFit]).
 */
@Composable
private fun Pass(parcel: DeliveryWidgetParcel, art: WidgetCardArt, metrics: PassMetrics, width: Dp, type: WidgetText, languageCode: String) {
    val context = LocalContext.current
    val card = parcel.card ?: return
    val locale = ExtensionLocalizer.locale(languageCode)
    val words = width - PASS_PADDING * 2
    val dot = if (parcel.newUpdate == true) WidgetCardColors.pair(card.ink, NEW_UPDATE_OPACITY) else null
    val nameStyle = TextStyle(color = WidgetCardColors.primary, fontSize = type.sp(metrics.name), fontWeight = FontWeight.Medium)
    val lineStyle = TextStyle(color = WidgetCardColors.pair(card.ink), fontSize = type.sp(metrics.caption2))
    fun width(text: String) = type.width(text, metrics.caption2, FontWeight.Normal)
    Column(
        GlanceModifier.fillMaxSize().cornerRadius(12.dp).background(WidgetCardColors.pair(card.surface)).clickable(openParcel(context, parcel))
            .semantics { contentDescription = spokenRow(parcel, languageCode) }.padding(horizontal = PASS_PADDING, vertical = PASS_PADDING_VERTICAL),
    ) {
        val day = if (metrics.oneLine) parcel.listDay?.openingLine(locale) else null
        Row(GlanceModifier.fillMaxWidth().height(metrics.top), verticalAlignment = Alignment.CenterVertically) {
            DayNightPicture(art.mark, R.layout.widget_mark, markSize(art, PASS_MARK, words - (day?.let { width(it) + 6.dp } ?: 0.dp)), description = parcel.carrier)
            if (day != null) {
                Spacer(GlanceModifier.defaultWeight())
                Text(day, maxLines = 1, style = lineStyle)
            }
        }
        Spacer(GlanceModifier.height(PASS_SPACING))
        Spacer(GlanceModifier.defaultWeight())
        if (metrics.oneLine) {
            val room = words * 0.5f - parcel.dotRoom
            val status = type.fittingStatus(parcel, locale, metrics.caption2, room)
            Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(parcel.listName, GlanceModifier.defaultWeight(), maxLines = 1, style = nameStyle)
                Spacer(GlanceModifier.width(6.dp))
                val points = type.lineFit(status, metrics.caption2, room)
                dot?.let { NewUpdateDot(it) }
                Text(
                    status, GlanceModifier.width(minOf(type.width(status, points, FontWeight.Normal), room)), maxLines = 1,
                    style = lineStyle.copy(fontSize = type.sp(points), textAlign = TextAlign.End),
                )
            }
        } else {
            val whole = parcel.listLine.openingLine(locale)
            val room = words - parcel.dotRoom
            val line = if (width(whole) <= room) whole else parcel.listDay?.openingLine(locale) ?: type.fittingStatus(parcel, locale, metrics.caption2, room)
            Text(parcel.listName, maxLines = 1, style = nameStyle)
            Row(GlanceModifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                dot?.let { NewUpdateDot(it) }
                Text(line, GlanceModifier.defaultWeight(), maxLines = 1, style = lineStyle.copy(fontSize = type.sp(type.lineFit(line, metrics.caption2, room))))
            }
        }
    }
}

// Sections.

/**
 * The parcels as rows under the app list's sections ([DeliveryWidgetList.sections]), as `ParcelSections` picks them: as
 * many as the widget holds at the reader's text size, whatever its size, so that no room for another is left blank; a
 * small or medium widget taking smaller type ([ListMetrics.compact]) before it gives a row up
 * ([DeliveryWidgetList.layouts]). A small widget has no room for the headings, nor has one fed by an app that didn't
 * publish the sections ([DeliveryWidgetList.lines]); a medium one splits the lines into two columns about as tall as
 * each other, a heading never ending the first ([DeliveryWidgetList.columns]). The line counting the parcels left out
 * ends the last column, where they would follow its last row. The passes' `design`, whose passes don't all fit, tries
 * the same rows, in a medium widget every parcel down one column without headings before one is left out
 * ([DeliveryWidgetList.fits]).
 */
internal class SectionsLayout(
    list: DeliveryWidgetList,
    size: DpSize,
    type: WidgetText,
    design: DeliveryWidgetList.Design = DeliveryWidgetList.Design.Sections,
) {
    val sections = list.sections
    val headingHeight = maxOf(
        type.lineHeight(type.points(IosTextStyle.Caption), FontWeight.Medium),
        type.lineHeight(type.points(IosTextStyle.Caption2), FontWeight.Medium) + HEADING_CAPSULE_PADDING * 2,
    )
    val layout: ListLayout
    val columns: List<List<DeliveryWidgetList.Line>>

    init {
        val kind = listSize(size)
        val tries = DeliveryWidgetList.fits(design, list.parcels.size, kind).mapNotNull { (it as? DeliveryWidgetList.Fit.Rows)?.layout }
            .ifEmpty { listOf(DeliveryWidgetList.Layout(1, compact = false)) }

        fun attempt(tried: DeliveryWidgetList.Layout): Pair<ListLayout, List<List<DeliveryWidgetList.Line>>> {
            val lines = DeliveryWidgetList.lines(sections, tried.rows, headings = kind != DeliveryWidgetList.Size.Small && !tried.oneColumn)
            val columns = if (kind == DeliveryWidgetList.Size.Medium && !tried.oneColumn) DeliveryWidgetList.columns(lines) else listOf(lines)
            return ListLayout(list, size, type, tried.compact, columns.size, tried.oneColumn) to columns
        }
        val kept = tries.asSequence().map(::attempt)
            .firstOrNull { (layout, columns) -> columns.all { height(it, layout) <= layout.room.height } }
            ?: attempt(tries.last())
        layout = kept.first
        columns = kept.second
    }

    /** The rows shown, down the columns in turn. */
    val rows: List<DeliveryWidgetParcel>
        get() = columns.flatten().filterIsInstance<DeliveryWidgetList.Line.Row>().map { it.parcel }

    /** How many parcels the last line counts. */
    val more: Int
        get() = columns.flatten().filterIsInstance<DeliveryWidgetList.Line.More>().sumOf { it.count }

    /** How tall `column` stands, each line with the gap above it, in this layout's rows or `layout`'s. */
    fun height(column: List<DeliveryWidgetList.Line>, layout: ListLayout = this.layout): Dp = column.foldIndexed(0.dp) { at, sum, line ->
        val before = column.getOrNull(at - 1)
        sum + when (line) {
            is DeliveryWidgetList.Line.Heading -> headingHeight + HEADING_BOTTOM + if (before == null) 0.dp else HEADING_TOP
            is DeliveryWidgetList.Line.Row -> layout.rowHeight + if (before == null || before is DeliveryWidgetList.Line.Heading) 0.dp else layout.metrics.rowSpacing
            is DeliveryWidgetList.Line.More -> layout.moreHeight + layout.metrics.rowSpacing * 2
        }
    }
}

/**
 * The sections as [SectionsLayout] lays them out for `design`, each row marked by its carrier's truck, the stage of one
 * that needs the reader in the warning colour; then the count of the rest at the foot of the last column.
 */
@Composable
internal fun Sections(
    list: DeliveryWidgetList,
    size: DpSize,
    type: WidgetText,
    copy: Localizer,
    design: DeliveryWidgetList.Design = DeliveryWidgetList.Design.Sections,
) {
    val laid = SectionsLayout(list, size, type, design)
    if (laid.columns.size == 2) {
        Row(GlanceModifier.fillMaxSize()) {
            SectionColumn(laid.columns.first(), laid, copy, GlanceModifier.defaultWeight().fillMaxHeight())
            Spacer(GlanceModifier.width(SECTIONS_COLUMN_GAP))
            SectionColumn(laid.columns.last(), laid, copy, GlanceModifier.defaultWeight().fillMaxHeight())
        }
    } else {
        SectionColumn(laid.columns.firstOrNull().orEmpty(), laid, copy, GlanceModifier.fillMaxSize())
    }
}

/**
 * A column's headings and rows, in groups of five, as a layout of Glance's shows ten children at most; then, at its foot,
 * the line for the parcels left out where it ends the column.
 */
@Composable
private fun SectionColumn(lines: List<DeliveryWidgetList.Line>, laid: SectionsLayout, copy: Localizer, modifier: GlanceModifier) {
    val layout = laid.layout
    val metrics = layout.metrics
    val more = lines.lastOrNull() as? DeliveryWidgetList.Line.More
    val entries = if (more == null) lines else lines.dropLast(1)
    Column(modifier) {
        entries.chunked(5).forEachIndexed { group, chunk ->
            Column(GlanceModifier.fillMaxWidth()) {
                chunk.forEachIndexed { at, line ->
                    val before = entries.getOrNull(group * 5 + at - 1)
                    when (line) {
                        is DeliveryWidgetList.Line.Heading ->
                            SectionHeading(copy.text(sectionTitleKey(line.kind)), line.count, layout.type, top = if (before == null) 0.dp else HEADING_TOP, height = laid.headingHeight)
                        is DeliveryWidgetList.Line.Row -> ListRow(
                            line.parcel, layout, top = if (before == null || before is DeliveryWidgetList.Line.Heading) 0.dp else metrics.rowSpacing,
                            lineColor = if (line.parcel.section == ParcelSectionKind.Attention.rawValue) Brand.warning else Brand.secondaryText,
                            mark = { CarrierMark(line.parcel, metrics) },
                        )
                        is DeliveryWidgetList.Line.More -> Unit
                    }
                }
            }
        }
        if (more != null) {
            Spacer(GlanceModifier.defaultWeight())
            MoreLine(copy, more.count, layout.type, GlanceModifier.padding(start = metrics.markWidth + metrics.markSpacing, top = metrics.rowSpacing * 2))
        }
    }
}

/** The app list's heading made small: its name in caption semibold, and its count in a capsule. */
@Composable
private fun SectionHeading(title: String, count: Int, type: WidgetText, top: Dp, height: Dp) {
    Column(GlanceModifier.fillMaxWidth().padding(top = top, bottom = HEADING_BOTTOM)) {
        Row(GlanceModifier.fillMaxWidth().height(height), verticalAlignment = Alignment.CenterVertically) {
            Text(title, maxLines = 1, style = TextStyle(color = ListColors.primary, fontSize = type.sp(type.points(IosTextStyle.Caption)), fontWeight = FontWeight.Medium))
            Spacer(GlanceModifier.width(5.dp))
            Box(GlanceModifier.cornerRadius(8.dp).background(ListColors.of(Brand.ink, 0.08f)).padding(horizontal = 6.dp, vertical = HEADING_CAPSULE_PADDING)) {
                Text("$count", maxLines = 1, style = TextStyle(color = ListColors.secondary, fontSize = type.sp(type.points(IosTextStyle.Caption2)), fontWeight = FontWeight.Medium))
            }
        }
    }
}

internal fun sectionTitleKey(kind: ParcelSectionKind): String = when (kind) {
    ParcelSectionKind.Attention -> "app.needsAttention"
    ParcelSectionKind.Today -> "app.arrivingToday"
    ParcelSectionKind.Active -> "app.onTheWaySection"
    ParcelSectionKind.Delivered -> "app.pastDeliveries"
    ParcelSectionKind.Returned -> "app.returned"
    ParcelSectionKind.Archived -> "app.archived"
}

/** The carrier's truck, as wide as the row's mark, light and dark; the stage's symbol from an app that didn't publish the carrier. */
@Composable
private fun CarrierMark(parcel: DeliveryWidgetParcel, metrics: ListMetrics) {
    val carrier = parcel.carrierId ?: return StageSymbol(parcel, metrics)
    val context = LocalContext.current
    val box = CarrierTruckGeometry.viewBox
    val width = metrics.markWidth
    val height = width * (box.height / box.width).toFloat()
    val truck = remember(carrier, width) { truckPicture(context, CarrierVisualIdentity.of(CarrierId(carrier), language = AppLanguage.En), width, height) }
    Box(GlanceModifier.width(metrics.markWidth).height(metrics.markHeight), contentAlignment = Alignment.Center) {
        DayNightPicture(truck, R.layout.widget_mark, GlanceModifier.width(width).height(height), description = null)
    }
}

private fun truckPicture(context: Context, identity: CarrierVisualIdentity, width: Dp, height: Dp): Pair<Bitmap, Bitmap> {
    fun draw(dark: Boolean) = bitmap(context, width.value, height.value) {
        val box = CarrierTruckGeometry.viewBox
        scale((width.value / box.width).toFloat(), (height.value / box.height).toFloat(), pivot = Offset.Zero) {
            drawMapDrawings(CarrierTruckGeometry.draw(identity, dark), dark)
        }
    }
    return draw(false) to draw(true)
}

// Shared.

/** The count of the parcels left out, which opens the app's list. */
@Composable
private fun MoreLine(copy: Localizer, count: Int, type: WidgetText, modifier: GlanceModifier) {
    val context = LocalContext.current
    Text(
        moreParcels(copy, count), modifier.fillMaxWidth().clickable(openList(context)), maxLines = 1,
        style = TextStyle(color = ListColors.secondary, fontSize = type.sp(type.points(IosTextStyle.Caption)), fontWeight = FontWeight.Medium),
    )
}

/**
 * A carrier's mark `height` tall, as wide as its picture then is but no wider than `room`. The width is given outright:
 * an image view sized by its height alone can keep the picture's own width and push what follows out.
 */
private fun markSize(art: WidgetCardArt, height: Dp, room: Dp): GlanceModifier =
    GlanceModifier.height(height).width(minOf(height * (art.mark.first.width.toFloat() / art.mark.first.height), room.coerceAtLeast(0.dp)))

/** How many items `item` tall, `gap` apart, stand in `height`. */
private fun fitting(height: Dp, item: Dp, gap: Dp): Int = if (height < item) 0 else 1 + floor((height - item) / (item + gap)).toInt()

private fun DpSize.inside(padding: Dp) = DpSize(width - padding * 2, height - padding * 2)

private val PAGE_MARGIN = 16.dp
private val CARD_GAP = 8.dp
private val WIDE_CARD_PADDING = 14.dp
private val LIST_CARD_PADDING = 14.dp
private val LIST_CARD_MARK = 14.dp
private val LIST_CARD_WORDS = 200.dp
private val STEP_TOP = 4.dp
private val STEP_GAP = 10.dp
private val STEP_DOT = 7.dp
private val STEP_THREAD = 2.dp
private val STEP_THREAD_BOX = 12.dp
private val STEP_THREAD_GAP = 8.dp
private val STEP_TIME_GAP = 6.dp
private val PASS_GAP = 6.dp
private val PASS_PADDING = 10.dp
private val PASS_PADDING_VERTICAL = 8.dp
private val PASS_MARK = 13.dp
private val PASS_SPACING = 3.dp
private val SECTIONS_COLUMN_GAP = 12.dp
private val HEADING_TOP = 8.dp
private val HEADING_BOTTOM = 3.dp
private val HEADING_CAPSULE_PADDING = 1.dp
