package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.ui.text.font.FontWeight

/**
 * SwiftUI's text styles (`Font.TextStyle`) with their point size in each content size category, xSmall to
 * accessibility5: the iOS Dynamic Type table of Apple's Human Interface Guidelines (Typography, "Dynamic Type
 * sizes"). The xSmall to xxxLarge columns agree with the iOS 11 edition of the same tables, and the body row with
 * ScootRadar's (UI-134). Each grows by its own steps, not by one ratio: footnote stays at 12 below Large.
 *
 * `leadings` are the same table's leading, the distance between a paragraph's lines, which a font from a text style
 * carries (`.font(.footnote)`, not `.font(.system(size:))`). They are CoreText's own for the iOS text styles, and the
 * iPhone's pictures set every style they show on several lines that far apart: footnote 18 at Large and 52 at
 * accessibility5, subheadline 20 and 58, caption 16, 32 and 51, caption 2 13 and 48, headline and body 62, title 2 66
 * and title 68 at accessibility5. Where the leading is SF's own line (1.193 em) or less, as from accessibility1 or 2
 * for the larger styles, the lines stay SF's line apart ([PeekTypography.style]).
 */
enum class IosTextStyle(private val sizes: IntArray, private val leadings: IntArray, val weight: FontWeight = FontWeight.Normal) {
    LargeTitle(intArrayOf(31, 32, 33, 34, 36, 38, 40, 44, 48, 52, 56, 60), intArrayOf(38, 39, 40, 41, 43, 46, 48, 52, 57, 61, 66, 70)),
    Title(intArrayOf(25, 26, 27, 28, 30, 32, 34, 38, 43, 48, 53, 58), intArrayOf(31, 32, 33, 34, 36, 38, 41, 46, 51, 57, 61, 68)),
    Title2(intArrayOf(19, 20, 21, 22, 24, 26, 28, 34, 39, 44, 50, 56), intArrayOf(24, 25, 26, 28, 30, 32, 34, 41, 47, 52, 59, 66)),
    Title3(intArrayOf(17, 18, 19, 20, 22, 24, 26, 31, 37, 43, 49, 55), intArrayOf(22, 23, 24, 25, 28, 30, 32, 38, 44, 51, 58, 65)),
    Headline(
        intArrayOf(14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53),
        intArrayOf(19, 20, 21, 22, 24, 26, 29, 34, 40, 48, 51, 62),
        FontWeight.SemiBold,
    ),
    Body(intArrayOf(14, 15, 16, 17, 19, 21, 23, 28, 33, 40, 47, 53), intArrayOf(19, 20, 21, 22, 24, 26, 29, 34, 40, 48, 56, 62)),
    Callout(intArrayOf(13, 14, 15, 16, 18, 20, 22, 26, 32, 38, 44, 51), intArrayOf(18, 19, 20, 21, 23, 25, 28, 32, 39, 46, 52, 60)),
    Subheadline(intArrayOf(12, 13, 14, 15, 17, 19, 21, 25, 30, 36, 42, 49), intArrayOf(16, 18, 19, 20, 22, 24, 26, 31, 36, 43, 50, 58)),
    Footnote(intArrayOf(12, 12, 12, 13, 15, 17, 19, 23, 27, 33, 38, 44), intArrayOf(16, 16, 16, 18, 20, 22, 24, 29, 33, 40, 46, 52)),
    Caption(intArrayOf(11, 11, 11, 12, 14, 16, 18, 22, 26, 32, 37, 43), intArrayOf(13, 13, 13, 16, 19, 21, 23, 27, 32, 39, 44, 51)),
    Caption2(intArrayOf(11, 11, 11, 11, 13, 15, 17, 20, 24, 29, 34, 40), intArrayOf(13, 13, 13, 13, 18, 20, 22, 25, 29, 35, 41, 48)),
    ;

    /** The style's size in points (dp) at a category. */
    fun pointSize(size: DynamicTypeSize): Float = sizes[size.ordinal].toFloat()

    /** The distance between the style's lines in points (dp) at a category, as the table gives it. */
    fun leading(size: DynamicTypeSize): Float = leadings[size.ordinal].toFloat()
}
