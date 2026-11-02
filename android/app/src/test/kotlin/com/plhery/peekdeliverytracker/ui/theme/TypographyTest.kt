package com.plhery.peekdeliverytracker.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.GraphicsMode
import kotlin.math.ceil
import kotlin.math.max

// Text measured as Android lays it out, for where lines break.
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(AndroidJUnit4::class)
class TypographyTest {
    @get:Rule
    val compose = createComposeRule()

    @After
    fun resetFontScale() {
        RuntimeEnvironment.setFontScale(1f)
    }

    /** What the theme gives inside, at the phone's font scale. */
    private class Seen(var size: DynamicTypeSize? = null, var body: Float = 0f, var footnote: Float = 0f, var fixed: Float = 0f)

    @Composable
    private fun Record(seen: Seen) {
        val density = LocalDensity.current
        seen.size = PeekTheme.dynamicTypeSize
        seen.body = dp(PeekTheme.type.body, density)
        seen.footnote = dp(PeekTheme.type.footnote, density)
        seen.fixed = dp(PeekTheme.type.fixed(13f), density)
    }

    private fun dp(style: TextStyle, density: Density): Float = with(density) { style.fontSize.toDp().value }

    private fun themed(fontScale: Float, content: @Composable () -> Unit) {
        RuntimeEnvironment.setFontScale(fontScale)
        compose.setContent { PeekTheme(highContrast = false, reduceMotion = false, carrierIdentities = null, content = content) }
        compose.waitForIdle()
    }

    @Test
    fun atTheDefaultScaleTextIsTheIPhonesLargeSizesInDp() {
        val seen = Seen()
        themed(1f) { Record(seen) }
        assertEquals(DynamicTypeSize.Large, seen.size)
        assertEquals(17f, seen.body, 0.01f)
        assertEquals(13f, seen.footnote, 0.01f)
        assertEquals(13f, seen.fixed, 0.01f)
    }

    @Test
    fun atTwiceTheScaleTextIsAccessibility2NotTwiceLarge() {
        val seen = Seen()
        themed(2f) { Record(seen) }
        assertEquals(DynamicTypeSize.Accessibility2, seen.size)
        // Drawn at the iPhone's sizes in dp whatever Android 14's nonlinear scaling would make of the sp.
        assertEquals(33f, seen.body, 0.01f)
        assertEquals(27f, seen.footnote, 0.01f)
        // `.system(size:)` stays put.
        assertEquals(13f, seen.fixed, 0.01f)
    }

    @Test
    fun aCapHoldsItsContentAtTheCap() {
        val outside = Seen()
        val inside = Seen()
        themed(1.8f) {
            Record(outside)
            CappedDynamicTypeSize(DynamicTypeSize.Accessibility1) { Record(inside) }
        }
        assertEquals(DynamicTypeSize.Accessibility2, outside.size)
        assertEquals(DynamicTypeSize.Accessibility1, inside.size)
        assertEquals(28f, inside.body, 0.01f)
    }

    @Test
    fun aCapAboveTheCategoryChangesNothing() {
        val inside = Seen()
        themed(1.15f) { CappedDynamicTypeSize(DynamicTypeSize.XXLarge) { Record(inside) } }
        assertEquals(DynamicTypeSize.XLarge, inside.size)
        assertEquals(19f, inside.body, 0.01f)
    }

    @Test
    fun scaledMetricsGrowWithTheirStyle() {
        val type = PeekTypography(DynamicTypeSize.Accessibility2, Density(2f))
        assertEquals(26f * 43 / 28, type.scaled(26f, relativeTo = IosTextStyle.Title), 0.001f)
        assertEquals(16f * 30 / 15, type.scaled(16f, relativeTo = IosTextStyle.Subheadline), 0.001f)
        assertEquals(10f, PeekTypography(DynamicTypeSize.Large, Density(2f)).scaled(10f, IosTextStyle.Caption2), 0.001f)
    }

    @Test
    fun stylesCarryTheirWeightAndDesign() {
        val type = PeekTypography(DynamicTypeSize.Large, Density(1f))
        assertEquals(FontWeight.SemiBold, type.headline.fontWeight)
        assertEquals(FontWeight.Normal, type.body.fontWeight)
        val title = type.style(IosTextStyle.Title, weight = FontWeight.Bold, design = FontDesign.Rounded)
        assertEquals(FontWeight.Bold, title.fontWeight)
        assertEquals(FontFamily.Default, title.fontFamily)
        assertEquals(FontFamily.Monospace, type.style(IosTextStyle.Body, design = FontDesign.Monospaced).fontFamily)
    }

    @Test
    fun monospacedDigitsAskForTabularFigures() {
        assertEquals("tnum", TextStyle().monospacedDigit().fontFeatureSettings)
        assertEquals("smcp, tnum", TextStyle(fontFeatureSettings = "smcp").monospacedDigit().fontFeatureSettings)
    }

    @Test
    fun letterSpacingGivesRobotoSFsWidths() {
        // Body and headline at Large: 17 points, regular and semibold.
        assertEquals(0.0035f, SfWidths.em(17f, FontWeight.Normal), 1e-6f)
        assertEquals(0.0179f, SfWidths.em(17f, FontWeight.SemiBold), 1e-6f)
        assertEquals(-0.0091f, SfWidths.em(17f, FontWeight.Normal, SfWidths.Design.Rounded), 1e-6f)
        // Between sizes and between weights, a straight line.
        assertEquals((0.0035f - 0.0001f) / 2, SfWidths.em(18f, FontWeight.Normal), 1e-6f)
        assertEquals((0.0035f + 0.0112f) / 2, SfWidths.em(17f, FontWeight(450)), 1e-6f)
        // Past the ends, the end rows and columns.
        assertEquals(SfWidths.em(6f, FontWeight.Normal), SfWidths.em(4f, FontWeight.Normal))
        assertEquals(SfWidths.em(80f, FontWeight.Bold), SfWidths.em(120f, FontWeight.Bold))
        assertEquals(SfWidths.em(17f, FontWeight.Black), SfWidths.em(17f, FontWeight(1000)))
        assertEquals(SfWidths.em(17f, FontWeight.Thin), SfWidths.em(17f, FontWeight(1)))

        val type = PeekTypography(DynamicTypeSize.Large, Density(2f, fontScale = 1.3f))
        assertEquals(0.0035f, type.body.letterSpacing.value, 1e-6f)
        assertTrue(type.body.letterSpacing.isEm)
        assertEquals(SfWidths.em(13f, FontWeight.Medium, SfWidths.Design.Rounded), type.fixed(13f, FontWeight.Medium, FontDesign.Rounded).letterSpacing.value)
        // Serif and monospaced text keeps its own font's widths.
        assertTrue(type.style(IosTextStyle.Body, design = FontDesign.Serif).letterSpacing.isUnspecified)
        assertTrue(type.style(IosTextStyle.Body, design = FontDesign.Monospaced).letterSpacing.isUnspecified)
    }

    @Test
    fun chineseKeepsHanCharactersOwnWidths() {
        val chinese = PeekTypography(DynamicTypeSize.Large, Density(2f), AppLanguage.Zh)
        assertTrue(chinese.body.letterSpacing.isUnspecified)
        assertTrue(chinese.style(IosTextStyle.LargeTitle, design = FontDesign.Rounded).letterSpacing.isUnspecified)
        assertTrue(chinese.fixed(11f, FontWeight.Bold).letterSpacing.isUnspecified)
        // `.tracking(_:)` still adds its points.
        assertEquals((1.7f / 17).toDouble(), chinese.tracking(chinese.body, 1.7f).letterSpacing.value.toDouble(), 1e-6)
        // Cyrillic takes the Latin languages' values.
        val russian = PeekTypography(DynamicTypeSize.Large, Density(2f), AppLanguage.Ru)
        assertEquals(0.0035f, russian.body.letterSpacing.value, 1e-6f)
        assertEquals(SfWidths.em(28f, FontWeight.Normal), russian.title.letterSpacing.value, 1e-6f)
    }

    @Test
    fun theLocalizersLanguageSpacesTheFontsInside() {
        val localizer = Localizer(AppLanguage.Zh, variant = "android")
        var spacing = TextUnit.Unspecified
        var capped: PeekTypography? = null
        themed(1f) {
            ProvideLocalizer(localizer) {
                spacing = LocalTextStyle.current.letterSpacing
                CappedDynamicTypeSize(DynamicTypeSize.Small) { capped = PeekTheme.type }
            }
        }
        assertTrue(spacing.isUnspecified)
        assertEquals(AppLanguage.Zh, capped?.language)
        assertTrue(capped!!.body.letterSpacing.isUnspecified)

        localizer.language = AppLanguage.Ru
        compose.waitForIdle()
        assertEquals(0.0035f, spacing.value, 1e-6f)
        assertEquals(AppLanguage.Ru, capped?.language)
    }

    @Test
    fun trackingAddsPointsOnTopOfTheFontsOwnSpacing() {
        // At a font scale whose sp aren't dp, as Android 14's nonlinear scaling gives.
        val type = PeekTypography(DynamicTypeSize.Large, Density(2f, fontScale = 1.3f))
        assertEquals((0.0035f + 1.7f / 17).toDouble(), type.tracking(type.body, 1.7f).letterSpacing.value.toDouble(), 1e-6)
        val serif = type.style(IosTextStyle.Body, design = FontDesign.Serif)
        assertEquals((-0.5f / 17).toDouble(), type.tracking(serif, -0.5f).letterSpacing.value.toDouble(), 1e-6)
        assertThrows(IllegalArgumentException::class.java) { type.tracking(TextStyle(fontSize = 17.sp, letterSpacing = 1.sp), 1f) }
    }

    @Test
    fun italicKeepsTheUprightsSpacing() {
        val body = PeekTypography(DynamicTypeSize.Large, Density(2f)).body
        val italic = body.italic()
        assertEquals(FontStyle.Italic, italic.fontStyle)
        assertEquals(body.letterSpacing, italic.letterSpacing)
        assertEquals(body.fontWeight, italic.fontWeight)
    }

    @Test
    fun eachStyleCarriesTheTablesLeading() {
        // Apple's table at Large, and at accessibility5 where only footnote, caption and caption 2 stay over SF's line.
        val large = listOf(41f, 34f, 28f, 25f, 22f, 22f, 21f, 20f, 18f, 16f, 13f)
        assertEquals(large, IosTextStyle.entries.map { it.leading(DynamicTypeSize.Large) })
        val ax5 = listOf(70f, 68f, 66f, 65f, 62f, 62f, 60f, 58f, 52f, 51f, 48f)
        assertEquals(ax5, IosTextStyle.entries.map { it.leading(DynamicTypeSize.Accessibility5) })
        assertEquals(29f, IosTextStyle.Footnote.leading(DynamicTypeSize.Accessibility1))
        assertEquals(51f, IosTextStyle.Headline.leading(DynamicTypeSize.Accessibility4))
        assertEquals(56f, IosTextStyle.Body.leading(DynamicTypeSize.Accessibility4))
        // A style whose leading is SF's line or less is `.system(size:)`'s: caption 2 at Large (13 against 13.1).
        for (size in DynamicTypeSize.entries) {
            val type = PeekTypography(size, Density(3f))
            for (style in IosTextStyle.entries) {
                val plain = type.fixed(style.pointSize(size), style.weight)
                val sfLine = style.leading(size) <= style.pointSize(size) * SF_LINE_HEIGHT
                assertEquals("$style at $size", sfLine, type.style(style) == plain)
            }
        }
    }

    @Test
    fun aStylesLinesStandItsLeadingApartAndASingleLineIsAsTallAsBefore() {
        lateinit var measurer: TextMeasurer
        themed(1f) { measurer = rememberTextMeasurer() }
        for (density in listOf(2f, 2.625f, 3f)) {
            val at = Density(density)
            fun lines(text: String, style: TextStyle) = measurer.measure(text, style, density = at)
            for (size in DynamicTypeSize.entries) {
                val type = PeekTypography(size, at)
                for (style in IosTextStyle.entries) {
                    val leading = style.leading(size)
                    val pointSize = style.pointSize(size)
                    if (leading <= pointSize * SF_LINE_HEIGHT) continue
                    val where = "$style at $size, ${density}x"
                    for (sf in listOf(false, true)) {
                        val styled = if (sf) type.style(style).sfLines() else type.style(style)
                        val plain = if (sf) type.fixed(pointSize, style.weight).sfLines() else type.fixed(pointSize, style.weight)
                        val one = lines("Peek", styled)
                        assertEquals("$where, one line's height, sfLines $sf", lines("Peek", plain).size.height, one.size.height)
                        assertEquals("$where, its baseline, sfLines $sf", lines("Peek", plain).firstBaseline, one.firstBaseline)
                        val two = lines("Peek\nPeek", styled)
                        val apart = two.getLineBaseline(1) - two.getLineBaseline(0)
                        assertEquals("$where, between lines, sfLines $sf", ceil(leading * density), apart, 0.01f)
                        assertEquals("$where, its first baseline, sfLines $sf", one.firstBaseline, two.firstBaseline)
                        assertEquals("$where, two lines' height, sfLines $sf", one.size.height + apart, two.size.height.toFloat(), 0.01f)
                    }
                }
            }
        }
    }

    @Test
    fun lineSpacingAddsToTheLeading() {
        // GiftNote's `.lineSpacing(4)`: 4 more between lines and, with SF's lines, over a single one as before.
        lateinit var measurer: TextMeasurer
        themed(1f) { measurer = rememberTextMeasurer() }
        for (density in listOf(2f, 2.625f, 3f)) {
            val at = Density(density)
            fun lines(text: String, style: TextStyle) = measurer.measure(text, style, density = at)
            for (size in DynamicTypeSize.entries) {
                val type = PeekTypography(size, at)
                val pointSize = IosTextStyle.Title3.pointSize(size)
                val where = "title 3 at $size, ${density}x"
                val styled = type.style(IosTextStyle.Title3, FontWeight.SemiBold, lineSpacing = 4f).sfLines()
                val before = type.fixed(pointSize, FontWeight.SemiBold).sfLines().copy(lineHeight = (pointSize * SF_LINE_HEIGHT + 4f).sp)
                val one = lines("Peek", styled)
                assertEquals("$where, one line's height", lines("Peek", before).size.height, one.size.height)
                assertEquals("$where, its baseline", lines("Peek", before).firstBaseline, one.firstBaseline)
                val two = lines("Peek\nPeek", styled)
                val leading = max(IosTextStyle.Title3.leading(size), pointSize * SF_LINE_HEIGHT)
                assertEquals(where, ceil(leading * density + 4 * density), two.getLineBaseline(1) - two.getLineBaseline(0), 0.01f)
            }
        }
    }

    // At 1.3 and 2.0 Android 14 scales sp by its own curve; the leading, in em, is the same points whatever.
    @Test
    fun theLeadingHoldsUnderAndroidsNonlinearFontScalingAt1_3() = assertFootnoteLeading(1.3f)

    @Test
    fun theLeadingHoldsUnderAndroidsNonlinearFontScalingAt2() = assertFootnoteLeading(2f)

    private fun assertFootnoteLeading(scale: Float) {
        var apart = 0f
        var expected = 0f
        themed(scale) {
            val density = LocalDensity.current
            val type = PeekTheme.type
            val two = rememberTextMeasurer().measure("Peek\nPeek", type.footnote.sfLines())
            apart = two.getLineBaseline(1) - two.getLineBaseline(0)
            expected = ceil(IosTextStyle.Footnote.leading(type.size) * density.density)
        }
        assertEquals(expected, apart, 0.01f)
    }

    @Test
    fun aWordTooLongForItsLineBreaksAtItsHyphenAndOneThatFitsALineGoesThereWhole() {
        lateinit var measurer: TextMeasurer
        lateinit var style: TextStyle
        themed(1f) {
            ProvideDynamicTypeSize(DynamicTypeSize.Large, AppLanguage.De) {
                measurer = rememberTextMeasurer()
                style = PeekTheme.type.title
            }
        }
        assertEquals(LocaleList("de"), style.localeList)
        fun firstLine(text: String, asWideAs: String): String {
            val width = measurer.measure(asWideAs, style).size.width
            return text.substring(0, measurer.measure(text, style, constraints = Constraints(maxWidth = width)).getLineEnd(0))
        }
        // Too long by itself: cut at its last hyphen that fits, where Android would cut it after the S.
        assertEquals("Trail-Wochenend-", firstLine("Trail-Wochenend-Set", asWideAs = "Trail-Wochenend-S"))
        // A word that fits a line by itself isn't hyphenated: it goes to the next line whole.
        assertEquals("Kiosk im ", firstLine("Kiosk im Hauptbahnhof", asWideAs = "Kiosk im Hauptbahn"))
    }
}
