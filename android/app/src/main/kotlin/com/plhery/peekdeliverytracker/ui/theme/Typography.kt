package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * SwiftUI's font designs. SF Pro Rounded, New York and SF Mono can't ship outside Apple's platforms (X-AD38): the
 * rounded design is the system sans until `roundedFamily` names a bundled rounded face, the one place to change.
 */
enum class FontDesign(val family: FontFamily) {
    Default(FontFamily.Default),
    Rounded(roundedFamily),
    Serif(FontFamily.Serif),
    Monospaced(FontFamily.Monospace),
}

private val roundedFamily: FontFamily get() = FontFamily.Default

/**
 * The iPhone app's fonts at one content size category. Sizes are points, drawn as that many dp whatever Android's
 * own font scaling would make of them: the category already carries the reader's text size, as Dynamic Type does.
 * Line heights and SF's per-size tracking are the system font's own.
 */
@Immutable
class PeekTypography(val size: DynamicTypeSize, private val density: Density) {
    /** `.font(.body)`, `.font(.title2.weight(.bold))`, `.font(.system(.title, design: .rounded))`. */
    fun style(style: IosTextStyle, weight: FontWeight = style.weight, design: FontDesign = FontDesign.Default): TextStyle =
        TextStyle(fontSize = points(style.pointSize(size)), fontWeight = weight, fontFamily = design.family)

    /** `.system(size:weight:design:)`: the same size in every category. */
    fun fixed(size: Float, weight: FontWeight = FontWeight.Normal, design: FontDesign = FontDesign.Default): TextStyle =
        TextStyle(fontSize = points(size), fontWeight = weight, fontFamily = design.family)

    /** `@ScaledMetric(relativeTo:)`: a length at Large, grown as the style's size grows from Large to this category. */
    fun scaled(value: Float, relativeTo: IosTextStyle = IosTextStyle.Body): Float =
        value * relativeTo.pointSize(size) / relativeTo.pointSize(DynamicTypeSize.Large)

    /** `.tracking(_:)` and `.kerning(_:)` in points, for `TextStyle.letterSpacing`. */
    fun tracking(points: Float): TextUnit = points(points)

    /** A length in points as the sp that Compose draws at that many dp. */
    private fun points(value: Float): TextUnit = with(density) { value.dp.toSp() }

    val largeTitle: TextStyle get() = style(IosTextStyle.LargeTitle)
    val title: TextStyle get() = style(IosTextStyle.Title)
    val title2: TextStyle get() = style(IosTextStyle.Title2)
    val title3: TextStyle get() = style(IosTextStyle.Title3)
    val headline: TextStyle get() = style(IosTextStyle.Headline)
    val body: TextStyle get() = style(IosTextStyle.Body)
    val callout: TextStyle get() = style(IosTextStyle.Callout)
    val subheadline: TextStyle get() = style(IosTextStyle.Subheadline)
    val footnote: TextStyle get() = style(IosTextStyle.Footnote)
    val caption: TextStyle get() = style(IosTextStyle.Caption)
    val caption2: TextStyle get() = style(IosTextStyle.Caption2)
}

/** `.monospacedDigit()`: figures of one width, so counts and times don't shift as they change. */
fun TextStyle.monospacedDigit(): TextStyle =
    copy(fontFeatureSettings = listOfNotNull(fontFeatureSettings, "tnum").joinToString(", "))

val LocalDynamicTypeSize = staticCompositionLocalOf { DynamicTypeSize.Large }

val LocalPeekTypography = staticCompositionLocalOf { PeekTypography(DynamicTypeSize.Large, Density(1f)) }

/** Sets the content size category, and the fonts and Material's text style that follow it, for `content`. */
@Composable
fun ProvideDynamicTypeSize(size: DynamicTypeSize, content: @Composable () -> Unit) {
    val density = LocalDensity.current
    val typography = remember(size, density) { PeekTypography(size, density) }
    CompositionLocalProvider(
        LocalDynamicTypeSize provides size,
        LocalPeekTypography provides typography,
        LocalTextStyle provides typography.body,
        content = content,
    )
}

/** `.dynamicTypeSize(...cap)` (A-AX03): `content` grows no further than `cap`, as with `...accessibility1`. */
@Composable
fun CappedDynamicTypeSize(cap: DynamicTypeSize, content: @Composable () -> Unit) {
    ProvideDynamicTypeSize(LocalDynamicTypeSize.current.capped(at = cap), content)
}
