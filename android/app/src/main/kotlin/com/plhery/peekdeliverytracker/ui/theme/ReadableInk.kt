package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import com.plhery.peekdeliverytracker.core.CarrierVisualIdentity

/** WCAG's ratio for small text, the one Apple asks of it for Sufficient Contrast. */
const val TEXT_CONTRAST = 4.5

/**
 * [ink] at [strength] of its own alpha, or as little more of it as reads at [TEXT_CONTRAST] on each of [grounds], in
 * steps of 5 %. Where the ink itself doesn't, it is mixed toward [label] (the words' own colour, which reads on every
 * ground) in steps of 10 %, channel by channel, until it does. Words a card writes in a lighter shade of its ink keep
 * it on the cards where it reads, and darken only where it doesn't.
 */
fun readableInk(ink: Color, strength: Float, grounds: List<Color>, label: Color): Color {
    val reads = { shade: Color -> grounds.all { contrast(shade.compositeOver(it), it) >= TEXT_CONTRAST } }
    var step = strength
    while (step < 1f) {
        val shade = ink.copy(alpha = ink.alpha * step)
        if (reads(shade)) return shade
        step += 0.05f
    }
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
 * The grounds a card's top corners stand on: its surface, and over it the map ([MapPalette.tint][com.plhery.peekdeliverytracker.core.MapPalette.tint])
 * where the card draws one: land, the card's ink at 10 %, and a country the parcel passed through, its ink at 17 % on
 * that land.
 */
fun cardGrounds(ink: Color, surface: Color, map: Boolean): List<Color> {
    if (!map) return listOf(surface)
    val land = ink.copy(alpha = ink.alpha * 0.10f).compositeOver(surface)
    return listOf(surface, land, ink.copy(alpha = ink.alpha * 0.17f).compositeOver(land))
}

/** WCAG's ratio ([CarrierVisualIdentity.contrast]) of two opaque colours as the screen draws them, in 8 bits. */
internal fun contrast(first: Color, second: Color): Double = CarrierVisualIdentity.contrast(hex(first), hex(second))

private fun hex(color: Color): String = "#%06X".format(color.toArgb() and 0xFFFFFF)
