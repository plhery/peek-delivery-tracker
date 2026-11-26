package com.plhery.peekdeliverytracker.ui.passport

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Continent
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.PassportBook
import com.plhery.peekdeliverytracker.core.PassportData
import com.plhery.peekdeliverytracker.core.StampTone
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.core.YearInParcels
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.LocalNow
import com.plhery.peekdeliverytracker.ui.friends.ProminentButton
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.io.File
import java.time.Instant
import java.time.ZoneId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// The web's `YearCard` and `cardDrawing.ts`: "Your <year> in parcels" as a picture to share, one page of paper on the
// passport's yellow, light only, drawn as the web and the iPhone draw it. It carries aggregates alone: counts, country
// stamps and earned seals; never a label, shop, carrier, number, place below the country or nickname.

internal const val CARD_WIDTH = 1080
internal const val CARD_HEIGHT = 1350
private const val ADDRESS = "peektracker.com"

/** The year a card is offered for: this one, in December only, by the device's calendar. */
internal fun cardYear(now: Instant, zone: ZoneId = ZoneId.systemDefault()): Int? = now.atZone(zone).takeIf { it.monthValue == 12 }?.year

/** A country stamp's day, as its ink carries it: "12 AUG 2026". */
internal fun inkDay(at: Instant, language: AppLanguage, zone: ZoneId = ZoneId.systemDefault()): String =
    formatted(at, "yMMMd", language, zone).replace(".", "").uppercase(dateLocale(language))

/** The card's words, and what TalkBack reads for its picture. */
internal class CardWords(
    val inParcels: String,
    /** "13 delivered · 5 countries", or "13 delivered" alone with no country. */
    val lineOne: String,
    /** "Fastest 18h · peektracker.com", or the address alone. */
    val lineTwo: String,
    /** The one country's name, under its stamp when it is the only one. */
    val onlyCountry: String?,
    /** A country stamp's day: "12 AUG 2026". */
    val inkDay: (Instant) -> String,
    /** "2026 in parcels. 13 delivered, 5 countries: Switzerland, …. Fastest 18h." */
    val description: String,
)

internal fun cardWords(year: YearInParcels, localizer: Localizer, language: AppLanguage, zone: ZoneId = ZoneId.systemDefault()): CardWords {
    val delivered = localizer.text("passport.card.delivered", mapOf("count" to year.deliveredCount))
    val countries = if (year.countryCount > 0) localizer.text("passport.card.countries", mapOf("count" to year.countryCount)) else null
    val fastest = year.fastest?.let { localizer.text("passport.card.fastest", mapOf("time" to passportDuration(it, localizer))) }
    val names = year.countries.map { TrackingLocation.countryName(it.stamp.code, language) }
    return CardWords(
        inParcels = localizer.text("passport.card.inParcels"),
        lineOne = if (countries != null) "$delivered · $countries" else delivered,
        lineTwo = if (fastest != null) "$fastest · $ADDRESS" else ADDRESS,
        onlyCountry = names.singleOrNull(),
        inkDay = { inkDay(it, language, zone) },
        description = listOfNotNull(
            localizer.text("passport.card.alt", mapOf("year" to year.year)),
            if (countries != null) "$delivered, $countries: ${names.joinToString(", ")}" else delivered,
            fastest,
        ).joinToString(". ") + ".",
    )
}

/** Where a box stands on the card, in its pixels. */
internal data class CardBox(val x: Float, val y: Float, val w: Float, val h: Float)

/** Where each country stamp goes, by how many there are (at most six), centred between [top] and [bottom]. */
internal fun countryLayout(count: Int, top: Float, bottom: Float): List<CardBox> {
    val rows = when (count) {
        1 -> listOf(1)
        2 -> listOf(2)
        3 -> listOf(3)
        4 -> listOf(2, 2)
        5 -> listOf(3, 2)
        6 -> listOf(3, 3)
        else -> return emptyList()
    }
    val (w, h, gap, rowGap) = when (count) {
        1 -> listOf(420f, 312f, 0f, 0f)
        2 -> listOf(340f, 252f, 80f, 0f)
        4 -> listOf(300f, 223f, 60f, 60f)
        else -> listOf(248f, 184f, 48f, 40f)
    }
    val blockHeight = rows.size * h + (rows.size - 1) * rowGap + if (count == 1) 72f else 0f
    var y = top + (bottom - top - blockHeight) / 2
    return buildList {
        for (inRow in rows) {
            var x = CARD_WIDTH / 2f - (inRow * w + (inRow - 1) * gap) / 2
            repeat(inRow) {
                add(CardBox(x, y, w, h))
                x += w + gap
            }
            y += h + rowGap
        }
    }
}

/** Each tone's ink and paper in the light theme, as ARGB. */
private class CardTone(val ink: Int, val paper: Int)

private val TONES = mapOf(
    StampTone.Green to CardTone(0xFF24613E.toInt(), 0xFFDCF0D7.toInt()),
    StampTone.Blue to CardTone(0xFF22588A.toInt(), 0xFFDAEAFE.toInt()),
    StampTone.Peach to CardTone(0xFF963E19.toInt(), 0xFFFFDDC7.toInt()),
    StampTone.Lilac to CardTone(0xFF654299.toInt(), 0xFFE9DEFF.toInt()),
    StampTone.Ochre to CardTone(0xFF745300.toInt(), 0xFFFFEBAB.toInt()),
    StampTone.Rose to CardTone(0xFF9A3D5C.toInt(), 0xFFFADDE7.toInt()),
)
private const val INK = 0xFF20251E.toInt()
private const val SOFT = 0xFF657060.toInt()
private const val PAPER = 0xFFFFF9E8.toInt()
private const val YELLOW = 0xFFF3CF48.toInt()
private const val RULE = 0xFFE6DCC0.toInt()
private const val COVER_INK = 0xFF745300.toInt()
private const val WHITE = 0xFFFFFFFF.toInt()

/** A path of passport.json's, as the canvas draws it. */
private fun androidPath(data: String): android.graphics.Path = PathParser().parsePathString(data).toPath().asAndroidPath()

/**
 * The words in the system face at [weight], their baseline at [y] (or their middle, [middle]), [spacing] apart, squeezed
 * to [maxWidth] as a canvas's `fillText` does.
 */
private fun Canvas.drawWords(
    text: String,
    x: Float,
    y: Float,
    size: Float,
    weight: Int,
    color: Int,
    spacing: Float = 0f,
    align: Paint.Align = Paint.Align.LEFT,
    maxWidth: Float? = null,
    middle: Boolean = false,
) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        typeface = Typeface.create(Typeface.DEFAULT, weight, false)
        textSize = size
        this.color = color
        textAlign = align
        letterSpacing = spacing / size
    }
    val baseline = if (middle) y - (paint.fontMetrics.ascent + paint.fontMetrics.descent) / 2 else y
    val width = paint.measureText(text)
    save()
    if (maxWidth != null && width > maxWidth) scale(maxWidth / width, 1f, x, baseline)
    drawText(text, x, baseline, paint)
    restore()
}

/** A country's ink stamp: its continent's outline, an inner line, its number, its code and its first day. */
private fun Canvas.inkStamp(box: CardBox, code: String, number: Int, day: String, continent: Continent, tilt: Int, color: Int) {
    val scale = box.w / 124f
    val layout = PassportData.layout(continent)
    val outline = androidPath(PassportData.outline(continent))
    val line = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeWidth = 1.7f
        this.color = color
    }
    save()
    translate(box.x + box.w / 2, box.y + box.h / 2)
    rotate(tilt.toFloat())
    scale(scale, scale)
    translate(-62f, -46f)
    drawPath(outline, line)
    save()
    val inner = layout.inner.toFloat()
    translate(62f, 46f)
    scale(inner, inner)
    translate(-62f, -46f)
    line.strokeWidth = 0.8f / inner
    line.alpha = (0.55f * 255).toInt()
    drawPath(outline, line)
    restore()
    val small = layout.small.toFloat()
    drawWords("Nº ${number.toString().padStart(2, '0')}", 62f, layout.number.toFloat(), small, 600, color, 1.3f, Paint.Align.CENTER)
    drawWords(code, 63f, layout.code.toFloat(), layout.codeSize.toFloat(), 700, color, 2f, Paint.Align.CENTER)
    drawWords(day, 62f, layout.date.toFloat(), small, 600, color, layout.dateSpacing.toFloat(), Paint.Align.CENTER)
    restore()
}

/** A paper seal with its perforated edge, frame, glyph or numeral, and postmark, on [ground]. */
private fun Canvas.seal(box: CardBox, glyph: String?, numeral: String?, ink: Int, paper: Int, ground: Int, tilt: Float = 0f) {
    val unit = box.w / 54f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    save()
    translate(box.x + box.w / 2, box.y + box.h / 2)
    rotate(tilt)
    translate(-box.w / 2, -box.h / 2)
    paint.color = paper
    drawRect(0f, 0f, box.w, box.h, paint)
    // The perforations: small bites of the ground along every edge.
    paint.color = ground
    val bite = 1.05f * unit
    val step = 3.6f * unit
    var along = step / 2
    while (along < box.w) {
        drawCircle(along, 0f, bite, paint)
        drawCircle(along, box.h, bite, paint)
        along += step
    }
    along = step / 2
    while (along < box.h) {
        drawCircle(0f, along, bite, paint)
        drawCircle(box.w, along, bite, paint)
        along += step
    }
    val inset = 6 * unit
    paint.apply {
        style = Paint.Style.STROKE
        color = ink
        alpha = (0.4f * 255).toInt()
        strokeWidth = unit
    }
    drawRect(inset + unit / 2, inset + unit / 2, box.w - inset - unit / 2, box.h - inset - unit / 2, paint)
    paint.alpha = 255
    if (numeral != null) {
        drawWords(numeral, box.w / 2, box.h / 2, 20 * unit, 300, ink, align = Paint.Align.CENTER, middle = true)
    } else {
        val size = 24 * unit
        save()
        translate(box.w / 2 - size / 2, box.h / 2 - size / 2)
        scale(unit, unit)
        paint.strokeWidth = 1.1f
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
        drawPath(androidPath(PassportData.glyph(glyph)), paint)
        restore()
    }
    // The postmark, over the seal's lower corner.
    val ring = 27 * unit
    save()
    translate(box.w + 11 * unit - ring, box.h + 3 * unit - ring)
    scale(ring / 32, ring / 32)
    paint.alpha = (0.34f * 255).toInt()
    paint.strokeWidth = 1.05f
    drawCircle(16f, 16f, 15f, paint)
    drawCircle(16f, 16f, 10.5f, paint)
    restore()
    restore()
}

/** Peek's mark (src/brand/mark.json): the yellow tile and the two eyes, [size] across at ([x], [y]). */
private fun Canvas.mark(x: Float, y: Float, size: Float) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    save()
    translate(x, y)
    scale(size / 512f, size / 512f)
    paint.color = YELLOW
    drawRoundRect(0f, 0f, 512f, 512f, 116f, 116f, paint)
    paint.color = 0xFFFFFAF0.toInt()
    drawOval(RectF(190f - 70, 262f - 77, 190f + 70, 262f + 77), paint)
    drawOval(RectF(322f - 70, 262f - 77, 322f + 70, 262f + 77), paint)
    for ((cx, pupil) in listOf(219.4f to 207.5f, 351.4f to 339.5f)) {
        paint.color = 0xFF171714.toInt()
        drawCircle(cx, 269f, 35f, paint)
        paint.color = WHITE
        drawCircle(pupil, 255f, 10.5f, paint)
    }
    restore()
}

/** Draws the whole card on a 1080×1350 canvas. */
internal fun drawYearCard(canvas: Canvas, year: YearInParcels, words: CardWords) = with(canvas) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = YELLOW
    drawRect(0f, 0f, CARD_WIDTH.toFloat(), CARD_HEIGHT.toFloat(), paint)
    paint.color = PAPER
    drawRoundRect(48f, 48f, 48f + 984f, 48f + 1254f, 56f, 56f, paint)

    drawWords(year.year.toString(), 116f, 300f, 200f, 650, INK, spacing = -7f)
    drawWords(words.inParcels, 120f, 368f, 44f, 400, SOFT)

    val seals = if (year.stamps.size > 7) year.stamps.take(6) else year.stamps
    val zoneBottom = if (seals.isNotEmpty()) 900f else 1010f
    if (year.countries.isNotEmpty()) {
        val boxes = countryLayout(year.countries.size, 430f, zoneBottom)
        year.countries.forEachIndexed { index, country ->
            val ink = PassportData.countryInk(country.stamp.code)
            inkStamp(boxes[index], country.stamp.code, country.stamp.number, words.inkDay(country.firstAt), country.stamp.continent, ink.tilt, TONES.getValue(ink.tone).ink)
        }
        val only = words.onlyCountry
        if (year.countries.size == 1 && only != null) {
            val box = boxes[0]
            drawWords(only, CARD_WIDTH / 2f, box.y + box.h + 64, 40f, 600, INK, align = Paint.Align.CENTER)
        }
    } else {
        val w = 220f
        val h = 270f
        seal(CardBox(CARD_WIDTH / 2f - w / 2, (430f + zoneBottom) / 2 - h / 2, w, h), "parcel", null, COVER_INK, WHITE, PAPER, tilt = -7f)
    }

    seals.forEachIndexed { index, stamp ->
        val tone = TONES.getValue(stamp.definition.tone)
        seal(CardBox(120f + index * 96, 940f, 72f, 90f), stamp.definition.glyph, stamp.definition.numeral, tone.ink, tone.paper, PAPER)
    }
    if (year.stamps.size > 7) drawWords("+${year.stamps.size - 6}", 120f + 6 * 96, 997f, 34f, 500, SOFT)

    paint.color = RULE
    drawRect(120f, 1069f, 960f, 1071f, paint)
    mark(120f, 1121f, 88f)
    drawWords(words.lineOne, 232f, 1158f, 44f, 650, INK, maxWidth = 728f)
    drawWords(words.lineTwo, 232f, 1208f, 34f, 400, SOFT, maxWidth = 728f)
}

/** The card as a picture. */
internal fun renderYearCard(year: YearInParcels, words: CardWords): Bitmap =
    Bitmap.createBitmap(CARD_WIDTH, CARD_HEIGHT, Bitmap.Config.ARGB_8888).also { drawYearCard(Canvas(it), year, words) }

/**
 * The picture through the system's share sheet as a PNG, read through the app's FileProvider (cacheDir/cards), which
 * the chosen app may read; the next card of the year takes its place.
 */
private suspend fun shareYearCard(context: Context, picture: Bitmap, year: Int, title: String) {
    val file = withContext(Dispatchers.IO) {
        val folder = File(context.cacheDir, "cards").apply { mkdirs() }
        File(folder, "peek-$year.png").also { file -> file.outputStream().use { picture.compress(Bitmap.CompressFormat.PNG, 100, it) } }
    }
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.exports", file)
    val send = Intent(Intent.ACTION_SEND)
        .setType("image/png")
        .putExtra(Intent.EXTRA_STREAM, uri)
        .putExtra(Intent.EXTRA_TITLE, title)
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    send.clipData = ClipData.newRawUri(file.name, uri)
    try {
        context.startActivity(Intent.createChooser(send, title).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
    } catch (_: ActivityNotFoundException) {
        // No app to take it: nothing opens, as an empty share sheet would offer nothing.
    }
}

/**
 * `YearCard`: in December, once a parcel was delivered this year, a quiet row under the times (the share symbol and
 * "Your 2026 in parcels") that opens the card in a sheet: the picture, read as its description, then Share and Close.
 * The day the passport opened decides: one left open over midnight keeps its row until it opens again.
 */
@Composable
internal fun PassportYearCard(book: PassportBook) {
    val now = LocalNow.current
    val opened = remember { now() }
    val shownYear = cardYear(opened) ?: return
    val year = remember(book, shownYear) { YearInParcels(book, shownYear) }
    if (year.deliveredCount == 0) return
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val words = remember(year, localizer, language) { cardWords(year, localizer, language) }
    val title = remember(shownYear, localizer, language) { localizer.text("passport.card.button", mapOf("year" to shownYear)) }
    var open by remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(PASSPORT_YEAR_CARD)
            .button(interactionSource = null, indication = PressStyle.Passport, role = Role.Button) { open = true }
            .background(colors.raised, RoundedCornerShape(14.dp))
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val style = type.style(IosTextStyle.Footnote, FontWeight.SemiBold)
        PeekSymbol("square.and.arrow.up", Modifier.clearAndSetSemantics {}, font = style, tint = colors.ink)
        Text(title, color = colors.ink, style = style.sfLines(), textAlign = TextAlign.Center)
    }
    Sheet(visible = open, onDismiss = { open = false }, paneTitle = title, id = PASSPORT_YEAR_CARD, detents = listOf(SheetDetent.Large), lit = true) {
        YearCardSheet(year, words, onClose = { open = false })
    }
}

/** The sheet's picture, drawn once it opens, then Share and Close. */
@Composable
private fun YearCardSheet(year: YearInParcels, words: CardWords, onClose: () -> Unit) {
    val localizer = LocalLocalizer.current
    val colors = PeekTheme.colors
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var picture by remember(year, words) { mutableStateOf<Bitmap?>(null) }
    LaunchedEffect(year, words) { picture = withContext(Dispatchers.Default) { renderYearCard(year, words) } }
    Column(
        Modifier.verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(CARD_WIDTH / CARD_HEIGHT.toFloat())
                .clip(RoundedCornerShape(14.dp))
                .background(colors.raised)
                .testTag(PASSPORT_YEAR_CARD_PICTURE)
                .semantics { contentDescription = words.description },
        ) {
            picture?.let { Image(it.asImageBitmap(), contentDescription = null, Modifier.fillMaxWidth().aspectRatio(CARD_WIDTH / CARD_HEIGHT.toFloat())) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ProminentButton(localizer.text("passport.card.share"), Modifier.weight(1f), enabled = picture != null) {
                val shown = picture ?: return@ProminentButton
                scope.launch { shareYearCard(context, shown, year.year, localizer.text("passport.card.alt", mapOf("year" to year.year))) }
            }
            ProminentButton(
                localizer.text("common.close"), Modifier.weight(1f), fill = colors.ink.copy(alpha = 0.12f), content = colors.ink, action = onClose,
            )
        }
    }
}

/** The card's row, and its sheet's picture. */
const val PASSPORT_YEAR_CARD = "$PASSPORT.yearCard"
const val PASSPORT_YEAR_CARD_PICTURE = "$PASSPORT.yearCard.picture"
