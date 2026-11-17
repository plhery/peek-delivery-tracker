package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity

/** WCAG's ratio for small text, the one Apple asks of it for Sufficient Contrast. */
const val TEXT_CONTRAST = 4.5

/**
 * [ink] where it reads at [TEXT_CONTRAST] on each of [grounds], as the iPhone writes a card's quieter words. Where it
 * doesn't, which only a country the parcel passed through on some cards' maps does, it is moved toward [label] (the
 * words' own colour, which reads on every ground) in steps of 10 %, channel by channel, until it does.
 */
fun readableInk(ink: Color, grounds: List<Color>, label: Color): Color {
    val reads = { shade: Color -> grounds.all { contrast(shade.compositeOver(it), it) >= TEXT_CONTRAST } }
    for (tenth in 0..10) {
        val amount = tenth / 10f
        val mixed = Color(
            red = ink.red + (label.red - ink.red) * amount,
            green = ink.green + (label.green - ink.green) * amount,
            blue = ink.blue + (label.blue - ink.blue) * amount,
            alpha = ink.alpha,
        )
        if (reads(mixed)) return mixed
    }
    return label
}

/**
 * The grounds a card's words stand on: its surface, and over it the map ([MapPalette.tint][com.plhery.peekdeliverytracker.core.MapPalette.tint])
 * where the card draws one: land, the card's ink at 10 %, and a country the parcel passed through, its ink at 17 % on
 * that land. Each is under the ink at [backing] where the words have a backing of their own, as the first open's alert
 * on its capsule.
 */
fun cardGrounds(ink: Color, surface: Color, map: Boolean, backing: Float = 0f): List<Color> {
    val land = ink.copy(alpha = ink.alpha * 0.10f).compositeOver(surface)
    val grounds = if (map) listOf(surface, land, ink.copy(alpha = ink.alpha * 0.17f).compositeOver(land)) else listOf(surface)
    return grounds.map { ink.copy(alpha = ink.alpha * backing).compositeOver(it) }
}

/**
 * The ink for words over [identity]'s map (the iPhone's `mapInk`): its ink where that reads on every ground the map puts
 * under them ([cardGrounds]), else moved toward the label until it does ([readableInk]).
 */
@Composable
fun rememberMapInk(identity: CarrierVisualIdentity, backing: Float = 0f): Color {
    val colors = PeekTheme.colors
    val ink = colors.resolve(identity.ink)
    val surface = colors.resolve(identity.surface)
    return remember(ink, surface, backing, colors.label) { readableInk(ink, cardGrounds(ink, surface, map = true, backing), colors.label) }
}

/** WCAG's ratio ([CarrierVisualIdentity.contrast]) of two opaque colours as the screen draws them, in 8 bits. */
internal fun contrast(first: Color, second: Color): Double = CarrierVisualIdentity.contrast(hex(first), hex(second))

private fun hex(color: Color): String = "#%06X".format(color.toArgb() and 0xFFFFFF)
