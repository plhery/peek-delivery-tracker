package com.plhery.peekdeliverytracker.core

import kotlin.math.roundToInt

// From DesignSystem.swift, the colours the map is painted with, as plain data: :app turns them into its own colours.

/** A colour as SwiftUI resolves one: sRGB components and an opacity, each from 0 to 1. */
data class RgbaColor(val red: Double, val green: Double, val blue: Double, val opacity: Double = 1.0) {
    /** `Color.opacity(_:)`. */
    fun opacity(value: Double): RgbaColor = copy(opacity = opacity * value)

    /**
     * `Color.mix(with:by:in:)`: `by` of the way to `with`, each colour weighted by its opacity. Swift's default space,
     * and this one's, is [GradientColorSpace.Perceptual]; pass [GradientColorSpace.Device] where Swift passes `.device`.
     * A mix whose opacity comes to 0 or less is black here, where SwiftUI's colour is no colour in particular.
     */
    fun mix(with: RgbaColor, by: Double, colorSpace: GradientColorSpace = GradientColorSpace.Perceptual): RgbaColor {
        val opacity = opacity + (with.opacity - opacity) * by
        val perceptual = colorSpace == GradientColorSpace.Perceptual
        val own = if (perceptual) oklabCone(this) else doubleArrayOf(red, green, blue)
        val theirs = if (perceptual) oklabCone(with) else doubleArrayOf(with.red, with.green, with.blue)
        val mixed = DoubleArray(3) { index ->
            val premultiplied = own[index] * this.opacity + (theirs[index] * with.opacity - own[index] * this.opacity) * by
            if (opacity > 0) premultiplied / opacity else 0.0
        }
        return if (perceptual) fromOklabCone(mixed, opacity) else RgbaColor(mixed[0], mixed[1], mixed[2], opacity)
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

        /**
         * `Color(hex:)`: "#RRGGBB", opaque. What is left once everything but letters, marks and numbers is trimmed
         * from both ends must be ASCII hexadecimal digits worth no more than 64 bits; anything else is black.
         */
        fun hex(hex: String): RgbaColor {
            val cleaned = hex.trimmingScalars { !isAlphanumeric(it) }
            val value = swiftUnsignedHex(cleaned) ?: 0UL
            return RgbaColor(
                ((value shr 16) and 0xFFUL).toDouble() / 255,
                ((value shr 8) and 0xFFUL).toDouble() / 255,
                (value and 0xFFUL).toDouble() / 255,
            )
        }

        // OKLab (Björn Ottosson's matrices) from extended linear sRGB: the cube roots of the cone responses, in which
        // a straight line between two colours is the mix SwiftUI's `.perceptual` space gives.
        private fun oklabCone(color: RgbaColor): DoubleArray {
            val red = linear(color.red)
            val green = linear(color.green)
            val blue = linear(color.blue)
            return doubleArrayOf(
                Math.cbrt(0.4122214708 * red + 0.5363325363 * green + 0.0514459929 * blue),
                Math.cbrt(0.2119034982 * red + 0.6806995451 * green + 0.1073969566 * blue),
                Math.cbrt(0.0883024619 * red + 0.2817188376 * green + 0.6299787005 * blue),
            )
        }

        private fun fromOklabCone(cone: DoubleArray, opacity: Double): RgbaColor {
            val long = cone[0] * cone[0] * cone[0]
            val medium = cone[1] * cone[1] * cone[1]
            val short = cone[2] * cone[2] * cone[2]
            return RgbaColor(
                encoded(4.0767416621 * long - 3.3077115913 * medium + 0.2309699292 * short),
                encoded(-1.2684380046 * long + 2.6097574011 * medium - 0.3413193965 * short),
                encoded(-0.0041960863 * long - 0.7034186147 * medium + 1.7076147010 * short),
                opacity,
            )
        }

        /** The sRGB transfer function, mirrored below 0 as extended sRGB has it. */
        private fun linear(value: Double): Double {
            val magnitude = Math.abs(value)
            val result = if (magnitude <= 0.04045) magnitude / 12.92 else Math.pow((magnitude + 0.055) / 1.055, 2.4)
            return if (value < 0) -result else result
        }

        private fun encoded(value: Double): Double {
            val magnitude = Math.abs(value)
            val result = if (magnitude <= 0.0031308) magnitude * 12.92 else 1.055 * Math.pow(magnitude, 1 / 2.4) - 0.055
            return if (value < 0) -result else result
        }
    }
}

/** `Gradient.ColorSpace`: the space [RgbaColor.mix] interpolates in. */
enum class GradientColorSpace {
    /** `.device`: the sRGB components themselves. */
    Device,

    /** `.perceptual`, SwiftUI's default: OKLab, so the steps look even to the eye. */
    Perceptual,
}

/**
 * `UInt64(text, radix: 16)`, or a narrower unsigned integer below `max`: an optional sign, then ASCII hexadecimal
 * digits only. Null when there are no digits, when anything else is there, when negative, or when larger than `max`.
 */
internal fun swiftUnsignedHex(text: String, max: ULong = ULong.MAX_VALUE): ULong? {
    var index = 0
    var negative = false
    if (text.startsWith("+") || text.startsWith("-")) {
        negative = text[0] == '-'
        index = 1
    }
    if (index == text.length) return null
    var value = 0uL
    while (index < text.length) {
        val digit = when (val char = text[index]) {
            in '0'..'9' -> char - '0'
            in 'a'..'f' -> char - 'a' + 10
            in 'A'..'F' -> char - 'A' + 10
            else -> return null
        }.toULong()
        if (value > (max - digit) / 16u) return null
        value = value * 16u + digit
        index++
    }
    if (negative && value != 0uL) return null
    return value
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

    /** [RgbaColor.mix] in each appearance. */
    fun mix(with: AdaptiveColor, by: Double, colorSpace: GradientColorSpace = GradientColorSpace.Perceptual): AdaptiveColor =
        AdaptiveColor(
            light.mix(with.light, by, colorSpace), dark.mix(with.dark, by, colorSpace),
            lightHighContrast.mix(with.lightHighContrast, by, colorSpace),
            darkHighContrast.mix(with.darkHighContrast, by, colorSpace),
        )

    private fun map(transform: (RgbaColor) -> RgbaColor): AdaptiveColor =
        AdaptiveColor(transform(light), transform(dark), transform(lightHighContrast), transform(darkHighContrast))
}

object Brand {
    /** Postal yellow, the same in every appearance. */
    val accent = AdaptiveColor(RgbaColor.hex("#F3CF48"))
    val accentBright = accent
    val onAccent = AdaptiveColor(RgbaColor.hex("#20251E"))
    val ink = color(light = "#20251E", dark = "#F5F6F2")
    val inkSoft = color(light = "#657060", dark = "#AFB9A9")
    val cream = color(light = "#F0F0E9", dark = "#292C28")
    val paper = color(light = "#FFFFFF", dark = "#242824")
    val warning = color(light = "#963E19", dark = "#FFB184")

    /** The tray behind a swiped card. */
    val warningSoft = color(light = "#FFDDC7", dark = "#4C3123")
    val background = color(light = "#F4F5F1", dark = "#151915")

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
