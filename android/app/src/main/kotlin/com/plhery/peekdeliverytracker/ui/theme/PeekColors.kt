package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.Brand
import com.plhery.peekdeliverytracker.core.RgbaColor

/** A colour of :core's as Compose's, its channels held to 0…1 as CoreGraphics holds them when it paints. */
fun RgbaColor.toColor(): Color = Color(
    red = red.coerceIn(0.0, 1.0).toFloat(),
    green = green.coerceIn(0.0, 1.0).toFloat(),
    blue = blue.coerceIn(0.0, 1.0).toFloat(),
    alpha = opacity.coerceIn(0.0, 1.0).toFloat(),
)

/**
 * UIKit's label and separator colours, which the iPhone app takes from the system (`.primary`, `.secondary`,
 * `.tertiary`, `Color(uiColor: .separator)`). Their increased-contrast variants are not modelled.
 */
object SystemColors {
    val label = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0), RgbaColor(1.0, 1.0, 1.0))
    val secondaryLabel = AdaptiveColor(rgb(60, 60, 67, 0.6), rgb(235, 235, 245, 0.6))
    val tertiaryLabel = AdaptiveColor(rgb(60, 60, 67, 0.3), rgb(235, 235, 245, 0.3))
    val separator = AdaptiveColor(rgb(60, 60, 67, 0.29), rgb(84, 84, 88, 0.6))

    private fun rgb(red: Int, green: Int, blue: Int, opacity: Double) =
        RgbaColor(red / 255.0, green / 255.0, blue / 255.0, opacity)
}

/** The iPhone app's colours (DesignSystem.swift `Brand`) for one appearance, from :core's adaptive ones. */
@Immutable
class PeekColors(val dark: Boolean, val highContrast: Boolean = false) {
    /** Any adaptive colour of :core's (a carrier's, the map's) in this appearance. */
    fun resolve(color: AdaptiveColor): Color = color.resolve(dark, highContrast).toColor()

    val accent = resolve(Brand.accent)
    val accentBright = resolve(Brand.accentBright)
    val onAccent = resolve(Brand.onAccent)
    val ink = resolve(Brand.ink)
    val inkSoft = resolve(Brand.inkSoft)
    val cream = resolve(Brand.cream)
    val paper = resolve(Brand.paper)
    val warning = resolve(Brand.warning)
    val warningSoft = resolve(Brand.warningSoft)
    val background = resolve(Brand.background)
    val separator = resolve(SystemColors.separator)
    val label = resolve(SystemColors.label)
    val secondaryLabel = resolve(SystemColors.secondaryLabel)
    val tertiaryLabel = resolve(SystemColors.tertiaryLabel)
}

val LocalPeekColors = staticCompositionLocalOf { PeekColors(dark = false) }
