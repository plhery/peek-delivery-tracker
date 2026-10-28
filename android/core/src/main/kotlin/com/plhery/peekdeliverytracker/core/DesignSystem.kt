package com.plhery.peekdeliverytracker.core

import kotlin.math.roundToInt

// From DesignSystem.swift, the colours the map is painted with, as plain data: :app turns them into its own colours.

/** A colour as SwiftUI resolves one: sRGB components and an opacity, each from 0 to 1. */
data class RgbaColor(val red: Double, val green: Double, val blue: Double, val opacity: Double = 1.0) {
    /** `Color.opacity(_:)`. */
    fun opacity(value: Double): RgbaColor = copy(opacity = opacity * value)

    /** `Color.mix(with:by:in: .device)`: the components, weighted by their opacity, `fraction` of the way to `other`. */
    fun mix(with: RgbaColor, by: Double): RgbaColor {
        val opacity = opacity + (with.opacity - opacity) * by
        fun channel(own: Double, theirs: Double): Double {
            val premultiplied = own * this.opacity + (theirs * with.opacity - own * this.opacity) * by
            return if (opacity > 0) premultiplied / opacity else 0.0
        }
        return RgbaColor(channel(red, with.red), channel(green, with.green), channel(blue, with.blue), opacity)
    }

    /** As one Android colour int, 0xAARRGGBB. */
    val argb: Int
        get() {
            fun byte(value: Double): Int = (swiftMin(1.0, swiftMax(0.0, value)) * 255).roundToInt()
            return (byte(opacity) shl 24) or (byte(red) shl 16) or (byte(green) shl 8) or byte(blue)
        }

    companion object {
        /** CharacterSet.alphanumerics: letters, marks and numbers. */
        private fun isAlphanumeric(scalar: Int): Boolean = when (Character.getType(scalar).toByte()) {
            Character.UPPERCASE_LETTER, Character.LOWERCASE_LETTER, Character.TITLECASE_LETTER, Character.MODIFIER_LETTER,
            Character.OTHER_LETTER, Character.NON_SPACING_MARK, Character.ENCLOSING_MARK, Character.COMBINING_SPACING_MARK,
            Character.DECIMAL_DIGIT_NUMBER, Character.LETTER_NUMBER, Character.OTHER_NUMBER,
            -> true
            else -> false
        }

        /** `Color(hex:)`: "#RRGGBB", opaque; anything unreadable is black. */
        fun hex(hex: String): RgbaColor {
            val cleaned = hex.trimmingScalars { !isAlphanumeric(it) }
            val value = cleaned.toULongOrNull(16) ?: 0UL
            return RgbaColor(
                ((value shr 16) and 0xFFUL).toDouble() / 255,
                ((value shr 8) and 0xFFUL).toDouble() / 255,
                (value and 0xFFUL).toDouble() / 255,
            )
        }
    }
}

/**
 * A colour for each appearance, light and dark, each with its increased-contrast variant: what a dynamic UIColor gives
 * in each trait collection. Opacity and mixing apply to each.
 */
data class AdaptiveColor(
    val light: RgbaColor,
    val dark: RgbaColor,
    val lightHighContrast: RgbaColor = light,
    val darkHighContrast: RgbaColor = dark,
) {
    /** One colour, whatever the appearance. */
    constructor(color: RgbaColor) : this(color, color)

    fun resolve(dark: Boolean, highContrast: Boolean = false): RgbaColor = when {
        dark && highContrast -> darkHighContrast
        dark -> this.dark
        highContrast -> lightHighContrast
        else -> light
    }

    fun opacity(value: Double): AdaptiveColor = map { it.opacity(value) }

    fun mix(with: AdaptiveColor, by: Double): AdaptiveColor = AdaptiveColor(
        light.mix(with.light, by), dark.mix(with.dark, by), lightHighContrast.mix(with.lightHighContrast, by),
        darkHighContrast.mix(with.darkHighContrast, by),
    )

    private fun map(transform: (RgbaColor) -> RgbaColor): AdaptiveColor =
        AdaptiveColor(transform(light), transform(dark), transform(lightHighContrast), transform(darkHighContrast))
}

object Brand {
    val ink = color(light = "#20251E", dark = "#F5F6F2")
    val paper = color(light = "#FFFFFF", dark = "#242824")
    val warning = color(light = "#963E19", dark = "#FFB184")

    /** A colour for each appearance; with increased contrast, a little further from the middle grey. */
    fun color(light: String, dark: String): AdaptiveColor {
        val lightColor = RgbaColor.hex(light)
        val darkColor = RgbaColor.hex(dark)
        return AdaptiveColor(lightColor, darkColor, contrasted(lightColor), contrasted(darkColor))
    }

    /** A little further from the middle grey, for increased contrast. */
    private fun contrasted(color: RgbaColor): RgbaColor {
        val luminance = color.red * 0.2126 + color.green * 0.7152 + color.blue * 0.0722
        val adjustment = if (luminance > 0.5) 0.06 else -0.06
        return RgbaColor(
            red = swiftMin(1.0, swiftMax(0.0, color.red + adjustment)),
            green = swiftMin(1.0, swiftMax(0.0, color.green + adjustment)),
            blue = swiftMin(1.0, swiftMax(0.0, color.blue + adjustment)),
            opacity = color.opacity,
        )
    }
}
