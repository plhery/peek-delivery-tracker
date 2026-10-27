package com.plhery.peekdeliverytracker.core

/**
 * The map's names as wide as the iPhone's system font writes them, so the layout tests see the collisions the iPhone
 * tests do. `map-text-widths.tsv` holds `(text as NSString).size(withAttributes:).width` for every name the tests
 * measure, recorded on a Mac by android/tools/MapTextWidths.swift (AppKit's system font writes them as wide as UIKit's):
 * size, weight, text and width, with invisible characters written as `\uXXXX`. A name it does not hold fails the test
 * rather than being guessed.
 */
class RecordedTextMeasurer(lines: Sequence<String>) : TextMeasurer {
    private val widths = HashMap<String, Double>()

    init {
        for (line in lines) {
            if (line.isBlank()) continue
            val (size, weight, text, width) = line.split('\t')
            widths[key(size.toDouble(), weight, unescape(text))] = width.toDouble()
        }
    }

    override fun width(text: String, font: MapFont): Double =
        widths[key(font.size, font.weight.name, text)] ?: throw AssertionError("No width recorded for \"$text\" in $font")

    private fun key(size: Double, weight: String, text: String): String = "$size\t$weight\t$text"

    companion object {
        val recorded: RecordedTextMeasurer by lazy {
            val stream = checkNotNull(RecordedTextMeasurer::class.java.getResourceAsStream("/map-text-widths.tsv"))
            RecordedTextMeasurer(stream.bufferedReader().use { it.readLines() }.asSequence())
        }

        private val escape = Regex("""\\u([0-9A-Fa-f]{4})""")

        private fun unescape(text: String): String = escape.replace(text) { it.groupValues[1].toInt(16).toChar().toString() }
    }
}
