package com.plhery.peekdeliverytracker.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetParcel
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer

// Peek AI's words on the widgets, as the app's cards write them (ui/deliveries/PeekAi.kt): "✦ Peek AI" in a pill of the
// line's colour at 14 %, then the words. The parcel carries them while Peek AI has them and the reader shows it
// (DeliveryWidgetParcel.peekAi); the widgets add them wherever they show a date line and the phrase fits beside it.

/** The opacity of the line's colour in Peek AI's pill. */
internal const val PEEK_AI_PILL = 0.14f

/** The gap before Peek AI's phrase where it follows a line. */
internal val PEEK_AI_GAP = 6.dp

/** Peek AI's phrase for `parcel` at `points`, as wide as it stands, and as tall as a line of its words: its pill too. */
internal class PeekAiFit(val name: String, val words: String, val points: Float, val width: Dp, val height: Dp)

/**
 * Peek AI's phrase for `parcel` in `room`, at `points` where it fits, else as much smaller as it takes down to
 * [LINE_FLOOR] of it; none where the parcel has no Peek AI words or they don't fit then.
 */
internal fun WidgetText.peekAiFit(parcel: DeliveryWidgetParcel, languageCode: String, points: Float, room: Dp): PeekAiFit? {
    val words = parcel.peekAi ?: return null
    val name = ExtensionLocalizer(languageCode).text("peekAi.name")
    fun width(size: Float): Dp =
        (size * (PILL_START + SPARKLE + SPARKLE_GAP + PILL_END + WORDS_GAP)).dp + width(name, size * NAME_SCALE, FontWeight.Bold) + width(words, size, FontWeight.Normal)
    val size = listOf(points, points * LINE_FLOOR).firstOrNull { width(it) <= room } ?: return null
    return PeekAiFit(name, words, size, width(size), lineHeight(size, FontWeight.Normal))
}

/** What TalkBack hears of the parcel's Peek AI words, "Peek AI: by 16:00"; nothing without them. */
internal fun peekAiSpoken(parcel: DeliveryWidgetParcel, languageCode: String): String? =
    parcel.peekAi?.let { ExtensionLocalizer(languageCode).text("peekAi.says").replace("{{words}}", it) }

/** The phrase `fit` measured, in `ink`, its pill in `ink` at 14 % (`pill`). */
@Composable
internal fun PeekAiLine(fit: PeekAiFit, ink: ColorProvider, pill: ColorProvider, type: WidgetText) {
    val points = fit.points
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(
            GlanceModifier.height(fit.height).cornerRadius(fit.height / 2).background(pill)
                .padding(start = (points * PILL_START).dp, end = (points * PILL_END).dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                ImageProvider(R.drawable.widget_peek_ai_sparkle), contentDescription = null, modifier = GlanceModifier.size((points * SPARKLE).dp),
                colorFilter = ColorFilter.tint(ink),
            )
            Spacer(GlanceModifier.width((points * SPARKLE_GAP).dp))
            Text(fit.name, maxLines = 1, style = TextStyle(color = ink, fontSize = type.sp(points * NAME_SCALE), fontWeight = FontWeight.Bold))
        }
        Spacer(GlanceModifier.width((points * WORDS_GAP).dp))
        Text(fit.words, maxLines = 1, style = TextStyle(color = ink, fontSize = type.sp(points)))
    }
}

// The web's pill around 11-point words (src/components/PeekAi.css), as tall as their line: 4 before the sparkle and 6
// after the name, the sparkle 11 across and 3 before the name, which is 0.92 of the words' size; the words 4 after it.
private const val PILL_START = 4f / 11
private const val PILL_END = 6f / 11
private const val SPARKLE = 1f
private const val SPARKLE_GAP = 3f / 11
private const val NAME_SCALE = 0.92f
private const val WORDS_GAP = 4f / 11
