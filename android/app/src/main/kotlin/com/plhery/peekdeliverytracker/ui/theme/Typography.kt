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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.isUnspecified
import com.plhery.peekdeliverytracker.core.AppLanguage

/**
 * SwiftUI's font designs. SF Pro Rounded, New York and SF Mono can't ship outside Apple's platforms (X-AD38): the
 * rounded design is the system sans until `roundedFamily` names a bundled rounded face, the one place to change (and
 * then [SfWidths] no longer applies to it). `widths` is the SF design whose widths Roboto is spaced to; serif and
 * monospaced text keeps its own font's widths.
 */
enum class FontDesign(val family: FontFamily, internal val widths: SfWidths.Design?) {
    Default(FontFamily.Default, SfWidths.Design.Pro),
    Rounded(roundedFamily, SfWidths.Design.Rounded),
    Serif(FontFamily.Serif, null),
    Monospaced(FontFamily.Monospace, null),
}

private val roundedFamily: FontFamily get() = FontFamily.Default

/**
 * The iPhone app's fonts at one content size category. Sizes are points, drawn as that many dp whatever Android's
 * own font scaling would make of them: the category already carries the reader's text size, as Dynamic Type does.
 * Line heights are the system font's own; letter spacing gives Roboto SF's widths at each size and weight
 * ([SfWidths]), so lines wrap and truncate where the iPhone's do. In Chinese there is none: the copy is mostly Han
 * characters, which Noto Sans CJK sets as far apart as the iPhone's PingFang does. Text is in the app's language, and
 * a word too long for a line by itself breaks at a hyphen or a syllable with a hyphen, as the iPhone's does at the
 * accessibility sizes ("Geburtstags-geschenk"), where Android would cut it between any two letters; a word that fits
 * a line by itself goes to the next one whole (the simple line break Compose takes by default).
 */
@Immutable
class PeekTypography(val size: DynamicTypeSize, private val density: Density, val language: AppLanguage = AppLanguage.En) {
    private val locales = LocaleList(language.locale.toLanguageTag())

    /** `.font(.body)`, `.font(.title2.weight(.bold))`, `.font(.system(.title, design: .rounded))`. */
    fun style(style: IosTextStyle, weight: FontWeight = style.weight, design: FontDesign = FontDesign.Default): TextStyle =
        fixed(style.pointSize(size), weight, design)

    /** `.system(size:weight:design:)`: the same size in every category. */
    fun fixed(size: Float, weight: FontWeight = FontWeight.Normal, design: FontDesign = FontDesign.Default): TextStyle =
        TextStyle(
            fontSize = points(size),
            fontWeight = weight,
            fontFamily = design.family,
            letterSpacing = design.widths?.takeIf { language != AppLanguage.Zh }?.let { SfWidths.em(size, weight, it).em }
                ?: TextUnit.Unspecified,
            localeList = locales,
            hyphens = Hyphens.Auto,
        )

    /** `@ScaledMetric(relativeTo:)`: a length at Large, grown as the style's size grows from Large to this category. */
    fun scaled(value: Float, relativeTo: IosTextStyle = IosTextStyle.Body): Float =
        value * relativeTo.pointSize(size) / relativeTo.pointSize(DynamicTypeSize.Large)

    /**
     * `.tracking(_:)` and `.kerning(_:)`: `points` more after every character, on top of the font's own spacing, as
     * CoreText adds them. The line is as wide as the iPhone's, but Android puts half of each character's extra space
     * before it, so the characters sit `points / 2` further along the line than the iPhone's; offset the text by
     * `-points / 2` where that shows.
     */
    fun tracking(style: TextStyle, points: Float): TextStyle {
        val pointSize = with(density) { style.fontSize.toDp().value }
        val own = if (style.letterSpacing.isUnspecified) 0f else style.letterSpacing.value
        require(style.letterSpacing.isUnspecified || style.letterSpacing.isEm) { "Letter spacing in sp can't take tracking" }
        return style.copy(letterSpacing = (own + points / pointSize).em)
    }

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

/**
 * A line as tall as SwiftUI's: SF's ascender and descender, 1.193 em, where Roboto's make 1.172, with the baseline
 * where SF's stands, so a stack of text is as tall as the iPhone's and each line sits where its line does there.
 */
fun TextStyle.sfLines(): TextStyle = copy(
    lineHeight = SF_LINE_HEIGHT.em,
    lineHeightStyle = LineHeightStyle(alignment = LineHeightStyle.Alignment.Bottom, trim = LineHeightStyle.Trim.None),
)

/** SF Pro's ascender and descender (1950 and 494 of its 2048 units), the height of a line of SwiftUI's text. */
private const val SF_LINE_HEIGHT = (1950f + 494f) / 2048f

/** `.italic()`: the font's italic, keeping the upright's letter spacing. */
fun TextStyle.italic(): TextStyle = copy(fontStyle = FontStyle.Italic)

/**
 * The letter spacing, in em, that makes Roboto as wide as SF Pro (or SF Pro Rounded) at a point size and weight, so
 * a line of the app's copy takes the room it takes on the iPhone.
 *
 * Measured with CoreText over every string in shared/locales (en, fr, de, es, it, pl, pt), each as one line: SF as
 * the system lays it out at that size (its optical size and size-specific tracking, which match the iPhone's SFUI.ttf
 * at `opsz` = size) against Android's variable Roboto 3 (AOSP external/roboto-fonts) at the same `wght`, with
 * spacing = (SF width − Roboto width) / (characters × size), averaged over the seven languages, which lie within
 * 0.005 em of each other. Robolectric's static Roboto 2.138 is within 0.0005 em of Roboto 3 at the weights it ships.
 * Cyrillic takes the same values: Russian's strings, measured the same way, come within 0.006 em of English's.
 * Rows are the sizes between which straight lines stay within 0.0005 em of every size measured (6 to 80 points);
 * the jump from 19 to 20 points is where SF turns from its text to its display spacing. Below 6 and above 80 points
 * the end rows hold, and between rows and weights the values are interpolated in a straight line.
 *
 * Han characters take none ([PeekTypography] in Chinese). Noto Sans CJK sets them a full em apart, as PingFang does;
 * the system font, which falls back to PingFang's UI faces, sets them 0.006 to 0.009 em closer up to 19 points, and
 * about 0.04 to 0.05 em closer from 20 points, where it turns to the display face, so Chinese titles are a little
 * wider than the iPhone's. Up to 19 points, the Chinese copy as a whole, Han and the Latin in it, would take −0.004 to
 * +0.014 em, the most in small bold text.
 *
 * Only Roboto is tuned: a phone whose maker replaces the system sans keeps these values, which then only come near
 * the iPhone's widths. Robolectric has no semibold or heavy Roboto and draws 600 as Medium (500) and 800 as Black
 * (900), so its snapshots of those weights differ a little from a phone's.
 */
internal object SfWidths {
    enum class Design { Pro, Rounded }

    private val SIZES = floatArrayOf(6f, 10f, 13f, 15f, 16f, 17f, 19f, 20f, 22f, 24f, 28f, 40f, 72f, 80f)

    /** Weights 100 to 900 across, a row per size in [SIZES]. */
    private val PRO = arrayOf(
        floatArrayOf(0.0668f, 0.0676f, 0.0708f, 0.0689f, 0.0766f, 0.0834f, 0.0933f, 0.1084f, 0.1214f),
        floatArrayOf(0.0385f, 0.0393f, 0.0425f, 0.0406f, 0.0483f, 0.0551f, 0.0650f, 0.0801f, 0.0931f),
        floatArrayOf(0.0209f, 0.0217f, 0.0249f, 0.0230f, 0.0307f, 0.0375f, 0.0474f, 0.0625f, 0.0755f),
        floatArrayOf(0.0111f, 0.0119f, 0.0151f, 0.0133f, 0.0209f, 0.0277f, 0.0376f, 0.0528f, 0.0658f),
        floatArrayOf(0.0072f, 0.0080f, 0.0112f, 0.0094f, 0.0170f, 0.0238f, 0.0337f, 0.0489f, 0.0619f),
        floatArrayOf(0.0014f, 0.0022f, 0.0054f, 0.0035f, 0.0112f, 0.0179f, 0.0279f, 0.0430f, 0.0560f),
        floatArrayOf(-0.0032f, -0.0022f, 0.0014f, -0.0001f, 0.0078f, 0.0147f, 0.0249f, 0.0403f, 0.0536f),
        floatArrayOf(-0.0126f, -0.0112f, -0.0069f, -0.0079f, 0.0003f, 0.0075f, 0.0180f, 0.0339f, 0.0476f),
        floatArrayOf(-0.0227f, -0.0206f, -0.0150f, -0.0149f, -0.0059f, 0.0017f, 0.0129f, 0.0298f, 0.0444f),
        floatArrayOf(-0.0289f, -0.0261f, -0.0191f, -0.0179f, -0.0083f, -0.0001f, 0.0117f, 0.0297f, 0.0451f),
        floatArrayOf(-0.0311f, -0.0280f, -0.0200f, -0.0182f, -0.0082f, 0.0003f, 0.0126f, 0.0312f, 0.0472f),
        floatArrayOf(-0.0355f, -0.0324f, -0.0244f, -0.0226f, -0.0126f, -0.0041f, 0.0082f, 0.0268f, 0.0428f),
        floatArrayOf(-0.0433f, -0.0402f, -0.0322f, -0.0304f, -0.0204f, -0.0119f, 0.0004f, 0.0189f, 0.0350f),
        floatArrayOf(-0.0448f, -0.0417f, -0.0337f, -0.0319f, -0.0218f, -0.0134f, -0.0011f, 0.0175f, 0.0335f),
    )

    private val ROUNDED = arrayOf(
        floatArrayOf(0.0408f, 0.0443f, 0.0523f, 0.0539f, 0.0638f, 0.0726f, 0.0848f, 0.1032f, 0.1195f),
        floatArrayOf(0.0125f, 0.0160f, 0.0239f, 0.0256f, 0.0354f, 0.0443f, 0.0565f, 0.0749f, 0.0912f),
        floatArrayOf(-0.0051f, -0.0016f, 0.0064f, 0.0080f, 0.0179f, 0.0267f, 0.0389f, 0.0573f, 0.0736f),
        floatArrayOf(-0.0149f, -0.0114f, -0.0034f, -0.0017f, 0.0081f, 0.0170f, 0.0292f, 0.0476f, 0.0638f),
        floatArrayOf(-0.0188f, -0.0153f, -0.0073f, -0.0056f, 0.0042f, 0.0130f, 0.0253f, 0.0437f, 0.0599f),
        floatArrayOf(-0.0222f, -0.0187f, -0.0107f, -0.0091f, 0.0008f, 0.0096f, 0.0218f, 0.0402f, 0.0565f),
        floatArrayOf(-0.0251f, -0.0216f, -0.0137f, -0.0120f, -0.0022f, 0.0067f, 0.0189f, 0.0373f, 0.0536f),
        floatArrayOf(-0.0261f, -0.0226f, -0.0146f, -0.0130f, -0.0031f, 0.0057f, 0.0179f, 0.0363f, 0.0526f),
        floatArrayOf(-0.0285f, -0.0251f, -0.0171f, -0.0154f, -0.0056f, 0.0033f, 0.0155f, 0.0339f, 0.0502f),
        floatArrayOf(-0.0295f, -0.0260f, -0.0181f, -0.0164f, -0.0066f, 0.0023f, 0.0145f, 0.0329f, 0.0492f),
        floatArrayOf(-0.0314f, -0.0280f, -0.0200f, -0.0183f, -0.0085f, 0.0004f, 0.0126f, 0.0310f, 0.0472f),
        floatArrayOf(-0.0349f, -0.0314f, -0.0234f, -0.0217f, -0.0119f, -0.0031f, 0.0092f, 0.0275f, 0.0438f),
        floatArrayOf(-0.0427f, -0.0392f, -0.0312f, -0.0296f, -0.0197f, -0.0109f, 0.0013f, 0.0197f, 0.0360f),
        floatArrayOf(-0.0441f, -0.0407f, -0.0327f, -0.0310f, -0.0212f, -0.0123f, -0.0001f, 0.0183f, 0.0345f),
    )

    fun em(pointSize: Float, weight: FontWeight, design: Design = Design.Pro): Float {
        val rows = if (design == Design.Pro) PRO else ROUNDED
        val size = pointSize.coerceIn(SIZES.first(), SIZES.last())
        val upper = SIZES.indexOfFirst { it >= size }.coerceAtLeast(1)
        val across = (size - SIZES[upper - 1]) / (SIZES[upper] - SIZES[upper - 1])
        val column = (weight.weight.coerceIn(100, 900) - 100) / 100f
        val left = column.toInt().coerceAtMost(7)
        val within = column - left
        fun at(row: FloatArray) = row[left] + (row[left + 1] - row[left]) * within
        return at(rows[upper - 1]) + (at(rows[upper]) - at(rows[upper - 1])) * across
    }
}

val LocalDynamicTypeSize = staticCompositionLocalOf { DynamicTypeSize.Large }

val LocalPeekTypography = staticCompositionLocalOf { PeekTypography(DynamicTypeSize.Large, Density(1f)) }

/**
 * Sets the content size category, and the fonts and Material's text style that follow it, for `content`. The fonts
 * are spaced for `language`, by default the language of the fonts outside.
 */
@Composable
fun ProvideDynamicTypeSize(
    size: DynamicTypeSize,
    language: AppLanguage = LocalPeekTypography.current.language,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val typography = remember(size, density, language) { PeekTypography(size, density, language) }
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
    ProvideDynamicTypeSize(LocalDynamicTypeSize.current.capped(at = cap), content = content)
}
