package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
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

/** A Compose colour as :core's, in sRGB. */
fun Color.toRgba(): RgbaColor =
    convert(ColorSpaces.Srgb).let { RgbaColor(it.red.toDouble(), it.green.toDouble(), it.blue.toDouble(), it.alpha.toDouble()) }

/**
 * The UIKit system colours the iPhone app takes from the system (`.primary`, `.secondary`, `.tertiary`,
 * `Color(uiColor: .separator)`, `.red`, `.green`, `.orange`), light, dark, and each with Increase Contrast.
 *
 * Sources: the label and system colours are iOS 27's own, read from the simulator runtime's CoreUI colour tables
 * (CoreUI.framework/DesignLibrary-iOS.bundle/iOSRepositories, LightStandard to DarkIncreasedContrast); red, green and
 * orange are the ones iOS 26 brought in. Xcode 27's iOS system colour catalog (AssetCatalogCocoaTouchFoundation
 * .framework, SystemColors-ios.xcassets) gives the same, and gives the separator, which those tables leave out:
 * (60, 60, 67) at 0.12 in light and (84, 84, 88) at 0.5 in dark, whatever the contrast. UIKit 27's opaqueSeparator,
 * (231, 231, 232) and (42, 42, 44), is that separator over white and over black, and so is this one once Compose keeps
 * its opacity in 8 bits (31/255 and 128/255). iOS 13 to 18 drew the separator at 0.29 (#3C3C4349) and 0.6.
 */
object SystemColors {
    val label = AdaptiveColor(RgbaColor(0.0, 0.0, 0.0), RgbaColor(1.0, 1.0, 1.0))
    val secondaryLabel = AdaptiveColor(
        light = rgb(60, 60, 67, 0.6),
        dark = rgb(235, 235, 245, 0.6),
        lightHighContrast = rgb(60, 60, 67, 0.8),
        darkHighContrast = rgb(235, 235, 245, 0.698),
    )
    val tertiaryLabel = AdaptiveColor(
        light = rgb(60, 60, 67, 0.298),
        dark = rgb(235, 235, 245, 0.298),
        lightHighContrast = rgb(60, 60, 67, 0.698),
        darkHighContrast = rgb(235, 235, 245, 0.549),
    )
    val separator = AdaptiveColor(rgb(60, 60, 67, 0.12), rgb(84, 84, 88, 0.5))
    val red = AdaptiveColor(
        light = rgb(255, 56, 60),
        dark = rgb(255, 66, 69),
        lightHighContrast = rgb(233, 21, 45),
        darkHighContrast = rgb(255, 97, 101),
    )
    val green = AdaptiveColor(
        light = rgb(52, 199, 89),
        dark = rgb(48, 209, 88),
        lightHighContrast = rgb(0, 137, 50),
        darkHighContrast = rgb(74, 217, 104),
    )
    val orange = AdaptiveColor(
        light = rgb(255, 141, 40),
        dark = rgb(255, 146, 48),
        lightHighContrast = rgb(197, 83, 0),
        darkHighContrast = rgb(255, 160, 86),
    )

    private fun rgb(red: Int, green: Int, blue: Int, opacity: Double = 1.0) =
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
    val red = resolve(SystemColors.red)
    val green = resolve(SystemColors.green)
    val orange = resolve(SystemColors.orange)
}

val LocalPeekColors = staticCompositionLocalOf { PeekColors(dark = false) }
