package com.plhery.peekdeliverytracker.ui.passport

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.Continent
import com.plhery.peekdeliverytracker.core.CountryStamp
import com.plhery.peekdeliverytracker.core.PassportData
import com.plhery.peekdeliverytracker.core.TrackingLocation
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedbackEffect
import kotlinx.coroutines.delay

// The web's `CountryInk`: a country's ink stamp, its continent's outline with the inner line, its running number, its
// code and the day a parcel first reached it, in the country's tone and tilt. The Countries page and the year's card
// draw it alike; a new one drops in on the page.

/** A path of passport.json's, as the canvas draws it, parsed once. */
private val canvasPaths = HashMap<String, android.graphics.Path>()

internal fun androidPath(data: String): android.graphics.Path = synchronized(canvasPaths) {
    canvasPaths.getOrPut(data) { PathParser().parsePathString(data).toPath().asAndroidPath() }
}

/**
 * The words in the system face at [weight], their baseline at [y] (or their middle, [middle]), [spacing] apart, squeezed
 * to [maxWidth] as a canvas's `fillText` does.
 */
internal fun Canvas.drawWords(
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

/**
 * A country's ink stamp in [box], turned by [tilt] degrees about its middle: its continent's outline ([outline] wide in
 * the 124×92 box, 1.7 at rest), the inner line (0.8 at .55, scaled by the layout's `inner` about the middle), "Nº 01",
 * its code and its first [day], all in [color].
 */
internal fun Canvas.inkStamp(
    box: CardBox,
    code: String,
    number: Int,
    day: String,
    continent: Continent,
    tilt: Float,
    color: Int,
    outline: Float = INK_LINE,
) {
    val scale = box.w / INK_WIDTH
    val layout = PassportData.layout(continent)
    val path = androidPath(PassportData.outline(continent))
    val line = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeWidth = outline
        this.color = color
    }
    save()
    translate(box.x + box.w / 2, box.y + box.h / 2)
    rotate(tilt)
    scale(scale, scale)
    translate(-62f, -46f)
    drawPath(path, line)
    save()
    val inner = layout.inner.toFloat()
    translate(62f, 46f)
    scale(inner, inner)
    translate(-62f, -46f)
    line.strokeWidth = 0.8f / inner
    line.alpha = (0.55f * android.graphics.Color.alpha(color)).toInt()
    drawPath(path, line)
    restore()
    val small = layout.small.toFloat()
    drawWords("Nº ${number.toString().padStart(2, '0')}", 62f, layout.number.toFloat(), small, 600, color, 1.3f, Paint.Align.CENTER)
    drawWords(code, 63f, layout.code.toFloat(), layout.codeSize.toFloat(), 700, color, 2f, Paint.Align.CENTER)
    drawWords(day, 62f, layout.date.toFloat(), small, 600, color, layout.dateSpacing.toFloat(), Paint.Align.CENTER)
    restore()
}

/**
 * A new country's ink dropping in among those landing together ([drop] its place): 180 ms after its page shows, then
 * 120 ms more for each before it. The first two drop (scale 1.6, tilt −10° and 14 above, in place by [IMPACT], then
 * resting by 520 ms, the outline bleeding from 2.4 back to 1.7); further ones fade in over 200 ms. Its caption rises 80
 * ms after the impact and its New pill pops 200 ms after it. [time] is the ms since its own start; past [END] it rests.
 * Under Reduce Motion it is there.
 */
@Stable
internal class InkDrop(val drop: Int?, private val clock: Animatable<Float, *>?) {
    val delay: Float get() = if (drop == null) 0f else 180f + drop * 120f
    val time: Float get() = clock?.value?.minus(delay) ?: SealPress.REST
    val falls: Boolean get() = drop != null && drop < 2

    companion object {
        const val IMPACT = 234f

        /** The drop (520), the caption (impact + 80 + 300) and the pill (impact + 200 + 200), all done. */
        const val END = 634f
    }
}

/**
 * The clock a new country's ink drops in by: none for a country that isn't new, nor under Reduce Motion. It waits [lead]
 * ms first, the page's turn into view. The first one plays one light impact as it lands.
 */
@Composable
internal fun rememberInkDrop(drop: Int?, lead: Int = 0): InkDrop {
    val reduceMotion = PeekTheme.reduceMotion
    val clock = remember(drop, reduceMotion) { if (drop == null || reduceMotion) null else Animatable(0f) }
    val ink = remember(drop, clock) { InkDrop(drop, clock) }
    var landed by remember { mutableIntStateOf(0) }
    SensoryFeedbackEffect(SensoryFeedback.Impact(SensoryFeedback.Impact.Weight.Light, 0.65), landed)
    if (clock != null) {
        LaunchedEffect(clock) {
            if (lead > 0) delay(lead.toLong())
            val end = ink.delay + InkDrop.END
            if (drop == 0) {
                val impact = ink.delay + InkDrop.IMPACT
                clock.animateTo(impact, tween(impact.toInt(), easing = LinearEasing))
                landed++
                clock.animateTo(end, tween((end - impact).toInt(), easing = LinearEasing))
            } else {
                clock.animateTo(end, tween(end.toInt(), easing = LinearEasing))
            }
        }
    }
    return ink
}

/**
 * `CountryInk`: [code]'s ink stamp, at most 124 wide and 92 high for 124, in its tone's text colour and its tilt, with
 * its running [number] and first [day]; [drop] drops it in. Hidden from TalkBack: its button says it.
 */
@Composable
internal fun CountryInk(code: String, number: Int, day: String, drop: InkDrop?, modifier: Modifier = Modifier) {
    val colors = PeekTheme.colors
    val ink = PassportData.countryInk(code)
    val color = colors.resolve(ink.tone.ink).toArgb()
    val continent = ink.continent
    Box(
        modifier
            .widthIn(max = INK_WIDTH.dp)
            .aspectRatio(INK_WIDTH / INK_HEIGHT)
            .clearAndSetSemantics {}
            .graphicsLayer {
                val time = drop?.time ?: SealPress.REST
                if (time >= SealPress.REST || drop == null) return@graphicsLayer
                if (drop.falls) {
                    val fall = phase(time, 0f, InkDrop.IMPACT, PassportDrop)
                    val settle = phase(time, InkDrop.IMPACT, 520f - InkDrop.IMPACT, PassportSettle)
                    val scale = if (time < InkDrop.IMPACT) 1.6f + (0.95f - 1.6f) * fall else 0.95f + 0.05f * settle
                    scaleX = scale
                    scaleY = scale
                    rotationZ = -10f * (1 - fall)
                    translationY = -14.dp.toPx() * (1 - fall)
                    alpha = phase(time, 0f, 100f)
                } else {
                    alpha = phase(time, 0f, 200f)
                }
            }
            .drawBehind {
                // The outline bleeds at the impact and dries back to its width.
                val time = drop?.time ?: SealPress.REST
                val outline = if (drop?.falls == true && time < SealPress.REST) {
                    INK_BLEED + (INK_LINE - INK_BLEED) * phase(time, InkDrop.IMPACT, 520f - InkDrop.IMPACT, PassportSettle)
                } else {
                    INK_LINE
                }
                drawIntoCanvas {
                    it.nativeCanvas.inkStamp(CardBox(0f, 0f, size.width, size.height), code, number, day, continent, ink.tilt.toFloat(), color, outline)
                }
            },
    )
}

/**
 * The Countries page: one ink stamp per country in the order they were reached, two to a row, three where the page is
 * [wide] (560 or more, or the book lying open), rows 22 apart and columns 12. Each is a button whose popover says when a
 * parcel first reached it. The ones new since the passport last opened ([dropping], in order) drop in once the page
 * shows, [lead] ms after it is turned to. With no country yet, a dashed ghost of a stamp over what will fill the page.
 */
@Composable
internal fun PassportCountries(state: PassportState, countries: List<CountryStamp>, dropping: List<String>, wide: Boolean, lead: Int) {
    if (countries.isEmpty()) {
        CountriesEmpty()
        return
    }
    StampGrid(if (wide) 3 else 2, gap = 12.dp, rowGap = 22.dp) {
        for (country in countries) {
            androidx.compose.runtime.key(country.code) {
                CountryCell(state, country, dropping.indexOf(country.code).takeIf { it >= 0 }, lead)
            }
        }
    }
}

/**
 * One country: its ink, then its flag (hidden from TalkBack) and name, then its parcels; read as its name, the day it was
 * first reached and its parcels, and "New" while it is new. [drop] is its place among the inks landing together; its
 * caption rises 80 ms after its ink lands and its New pill pops 200 ms after.
 */
@Composable
private fun CountryCell(state: PassportState, country: CountryStamp, drop: Int?, lead: Int) {
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val id = PassportSelection.country(country.code)
    val expanded = state.expanded == id
    var anchor by remember { mutableStateOf(Rect.Zero) }
    val name = TrackingLocation.countryName(country.code, language)
    val reached = localizer.text("passport.firstReached", mapOf("date" to stampFullDay(country.firstAt, language)))
    val parcels = localizer.text("passport.parcels.many", mapOf("count" to country.count))
    val label = listOfNotNull(name, reached, parcels, if (drop != null) localizer.text("passport.new") else null).joinToString(", ")
    val details = "$reached · ${country.label.ifEmpty { localizer.text("common.parcel") }}\n$parcels"
    val ink = rememberInkDrop(drop, lead)
    Box(
        Modifier
            .fillMaxWidth()
            .popoverAnchor { anchor = it }
            .testTag("$PASSPORT_COUNTRY.${country.code}")
            .passportButton(label, expansionValue(expanded, details, localizer), expansionHint(expanded, localizer)) { state.toggle(id) },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(3.dp)) {
            CountryInk(country.code, country.number, inkDay(country.firstAt, language), ink, Modifier.fillMaxWidth().padding(bottom = 6.dp))
            Column(
                Modifier.graphicsLayer {
                    if (drop != null && ink.time < SealPress.REST) {
                        val rise = phase(ink.time, InkDrop.IMPACT + 80f, 300f, PassportSettle)
                        translationY = 8.dp.toPx() * (1 - rise)
                        alpha = rise
                    }
                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                Text(
                    "${TrackingLocation.flag(country.code)} $name", color = colors.ink,
                    style = type.fixed(type.scaled(12.5f, IosTextStyle.Caption)).sfLines(), textAlign = TextAlign.Center,
                )
                Text(parcels, color = colors.secondaryText, style = type.fixed(type.scaled(11f, IosTextStyle.Caption2)).sfLines(), textAlign = TextAlign.Center)
            }
        }
        if (drop != null) {
            NewPill(ink.time, InkDrop.IMPACT + 200f, Modifier.align(Alignment.TopEnd).offset(x = (-6).dp, y = (-6).dp))
        }
    }
    ExplanationPopover(state, id, anchor, name, details)
}

/** No country yet: a dashed ghost of an ink stamp, 124×92 turned −4°, over `passport.countriesEmpty`. */
@Composable
private fun CountriesEmpty() {
    val localizer = LocalLocalizer.current
    LocalLanguage.current
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val line = colors.separator
    Column(
        Modifier.fillMaxWidth().padding(start = 10.dp, top = 22.dp, end = 10.dp, bottom = 8.dp).testTag(PASSPORT_COUNTRIES_EMPTY),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Box(
            Modifier
                .size(INK_WIDTH.dp, INK_HEIGHT.dp)
                .rotate(-4f)
                .drawBehind {
                    val width = 1.5.dp.toPx()
                    drawRoundRect(
                        line, Offset(width / 2, width / 2), Size(size.width - width, size.height - width), CornerRadius(12.dp.toPx() - width / 2),
                        style = Stroke(width, pathEffect = PathEffect.dashPathEffect(floatArrayOf(3 * width, 2 * width))),
                    )
                },
        )
        Text(localizer.text("passport.countriesEmpty"), color = colors.secondaryText, style = type.subheadline.sfLines(), textAlign = TextAlign.Center)
    }
}

/** The ink's box, and its outline's width at rest and as it bleeds. */
internal const val INK_WIDTH = 124f
internal const val INK_HEIGHT = 92f
private const val INK_LINE = 1.7f
private const val INK_BLEED = 2.4f

/** The test tags of a country on the Countries page (then its code) and of the page without one. */
const val PASSPORT_COUNTRY = "$PASSPORT.country"
const val PASSPORT_COUNTRIES_EMPTY = "$PASSPORT.countriesEmpty"
