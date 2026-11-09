package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's button at the larger text sizes (B-AX09), on this device's own window, as AddParcelView's `addButton` draws
 * it: at the accessibility sizes its words keep to two lines at most, and only a title longer than those hold is made
 * smaller, down to 70 % and never under (`.lineLimit(2)`, `.minimumScaleFactor(0.7)`); the button is 50 tall at least
 * and grows round its lines (`.frame(maxWidth: .infinity, minHeight: 50)`). At the default text its words take the
 * lines they need at their size (`.lineLimit(nil)`, `.minimumScaleFactor(1)`). In Portuguese, whose "Adicionar
 * encomenda" is the longest of the app's words for it, yet fits on two lines in a phone's window: to make it too long,
 * the root is narrowed, by a box of its own width at the window's leading edge, until the words have a share of the
 * width they take on one line at their size. A word drawn at its size is measured at that size on
 * one line; how much smaller it is drawn is its width on one line against that. Add opened over the demo's parcels on
 * fake deliveries ([ParcelWalk]) with a synthetic number typed, the text size the composition's own Density. Shots in
 * addbutton/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class AddButtonLargeTextDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** The width the root is given, the window's where null. */
    private var width by mutableStateOf<Dp?>(null)

    /** The root in a box [width] wide at the window's leading edge, as tall as the window. */
    @Composable
    private fun Narrowed(content: @Composable () -> Unit) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val given = width?.let { minOf(it, maxWidth) } ?: maxWidth
            Box(Modifier.align(Alignment.TopStart).width(given).fillMaxHeight()) { content() }
        }
    }

    private fun narrowTo(given: Dp?) {
        width = given
        walk.recompose()
        walk.settle()
    }

    /** The fonts the app sets its words in at [scale], in the walk's language. */
    private fun type(scale: Float): PeekTypography = PeekTypography(DynamicTypeSize.of(scale), Density(compose.density.density, scale), walk.language)

    /** Add's words for a single number, as the walk's language has them. */
    private val words: String get() = walk.text("app.addParcel")

    /** How wide Add's words are on one line in headline at [scale], set in the app's fonts. */
    private fun oneLine(scale: Float): Dp {
        val measurer = TextMeasurer(createFontFamilyResolver(compose.activity), Density(compose.density.density, scale), LayoutDirection.Ltr)
        val laid = measurer.measure(words, style = type(scale).headline, softWrap = false, maxLines = 1)
        return with(compose.density) { laid.multiParagraph.intrinsics.maxIntrinsicWidth.toDp() }
    }

    /**
     * Add over the demo's parcels at [scale], its words given [area] where said, a synthetic number typed. The root is
     * narrowed before the window shows, so Add opens in the arrangement it keeps. Whatever stands either side of the
     * words besides the button's own insets, if anything, is given back once Add shows.
     */
    private fun showAdd(scale: Float, area: Dp? = null) {
        walk.fontScale = scale
        width = area?.let { it + BUTTON_INSETS }
        walk.show(DemoCards.parcels(walk.language), around = { content -> Narrowed(content) })
        walk.onUi { walk.deliveries.add() }
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.node(ADD_TRACKING).performTextReplacement(NUMBER)
        walk.settle()
        walk.awaitKeyboard(required = false)
        if (area != null) {
            val off = area - areaOf(layoutOf(drawnWords()))
            if (abs(off.value) > TOLERANCE.value) narrowTo(checkNotNull(width) + off)
        }
    }

    private val label: SemanticsMatcher get() = hasText(words) and hasAnyAncestor(hasTestTag(ADD_SAVE))

    /** Add's words as drawn: the one placed text in its button. */
    private fun drawnWords(): SemanticsNode {
        val nodes = compose.onAllNodes(label, useUnmergedTree = true).fetchSemanticsNodes().filter { it.layoutInfo.isPlaced }
        return nodes.singleOrNull() ?: throw AssertionError("Add's words drawn once, not ${nodes.map { it.boundsInRoot }}")
    }

    /** The layout of `node`'s text: its style as given, its lines as drawn. */
    private fun layoutOf(node: SemanticsNode): TextLayoutResult = walk.onUi {
        val laid = mutableListOf<TextLayoutResult>()
        node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
        laid.single()
    }

    /** The width a text was given to lay its lines out in. */
    private fun areaOf(layout: TextLayoutResult): Dp = with(compose.density) { layout.layoutInput.constraints.maxWidth.toDp() }

    /** Whether a text fits whole at `size`, on its lines and width. */
    private fun fitsAt(layout: TextLayoutResult, size: TextUnit): Boolean {
        val input = layout.layoutInput
        val measurer = TextMeasurer(input.fontFamilyResolver, input.density, input.layoutDirection)
        val laid = measurer.measure(
            input.text, style = input.style.copy(fontSize = size), overflow = input.overflow, softWrap = input.softWrap, maxLines = input.maxLines,
            constraints = Constraints(maxWidth = input.constraints.maxWidth),
        )
        return !laid.hasVisualOverflow && !laid.isLineEllipsized(laid.lineCount - 1)
    }

    /** How much smaller than [full] a text is drawn: its width on one line against its width on one line at [full]. */
    private fun scaleOf(layout: TextLayoutResult, full: TextUnit): Float {
        val input = layout.layoutInput
        val measurer = TextMeasurer(input.fontFamilyResolver, input.density, input.layoutDirection)
        val oneLine = measurer.measure(input.text, style = input.style.copy(fontSize = full), softWrap = false, maxLines = 1)
        return layout.multiParagraph.intrinsics.maxIntrinsicWidth / oneLine.multiParagraph.intrinsics.maxIntrinsicWidth
    }

    /**
     * [what] drawn whole: within its lines, each line within its width, as tall as its box at most, nothing
     * ellipsized, every character on a line. Read through semantics, a text is laid out across all its width while
     * its box is as wide as its words, so the layout's own overflow, which compares the two, says nothing here.
     */
    private fun assertWhole(what: String, layout: TextLayoutResult) {
        val last = layout.lineCount - 1
        val room = layout.layoutInput.constraints.maxWidth
        assertFalse("$what past its lines", layout.multiParagraph.didExceedMaxLines)
        (0..last).forEach { line ->
            val wide = layout.getLineRight(line) - layout.getLineLeft(line)
            assertTrue("$what: line $line $wide wide in $room", wide <= room + PIXEL)
        }
        assertTrue("$what clipped: ${layout.multiParagraph.height} tall in ${layout.size}", layout.multiParagraph.height <= layout.size.height + PIXEL)
        assertFalse("$what ellipsized", layout.isLineEllipsized(last))
        assertEquals("$what whole", layout.layoutInput.text.length, layout.getLineEnd(last))
    }

    /** How wide the widest of a text's words is on one line at `size`. */
    private fun widestWord(layout: TextLayoutResult, size: TextUnit): Float {
        val input = layout.layoutInput
        val measurer = TextMeasurer(input.fontFamilyResolver, input.density, input.layoutDirection)
        return input.text.text.split(' ').filter { it.isNotEmpty() }.maxOf { word ->
            measurer.measure(word, style = input.style.copy(fontSize = size), softWrap = false, maxLines = 1).multiParagraph.intrinsics.maxIntrinsicWidth
        }
    }

    /** At the accessibility sizes, [what] on two lines at most (`.lineLimit(2)`). */
    private fun assertTwoLinesAtMost(what: String, layout: TextLayoutResult) {
        assertEquals("$what kept to two lines", 2, layout.layoutInput.maxLines)
        assertTrue("$what on ${layout.lineCount} lines, two at most", layout.lineCount <= 2)
    }

    /** The button 50 tall at least, and round its words: they stand within it, top to bottom. */
    private fun assertButtonHolds(what: String, words: SemanticsNode) {
        val button = compose.onNode(hasTestTag(ADD_SAVE), useUnmergedTree = true)
        val height = button.getUnclippedBoundsInRoot().height
        assertTrue("$what: the button $height tall, 50 at least", height >= MIN_HEIGHT - TOLERANCE)
        val outer = button.fetchSemanticsNode().boundsInRoot
        val inner = words.boundsInRoot
        assertTrue("$what: its words $inner within the button $outer", inner.top >= outer.top - PIXEL && inner.bottom <= outer.bottom + PIXEL)
    }

    // At the largest text.

    /**
     * B-AX09: at the largest text, an accessibility size, Add's words keep to two lines at most in the window, whole,
     * at their size where they fit at it, else smaller down to 70 %; the button is 50 tall at least, round them.
     */
    @Test
    fun atTheLargestTextAddsWordsKeepToTwoLinesAndTheButtonToFiftyAtLeast() {
        assertTrue(DynamicTypeSize.of(LARGEST).isAccessibilitySize)
        walk.language = AppLanguage.Pt
        showAdd(LARGEST)
        val node = drawnWords()
        val layout = layoutOf(node)
        val what = "Add's words in ${areaOf(layout)}"
        assertTwoLinesAtMost(what, layout)
        assertWhole(what, layout)
        val full = type(LARGEST).headline.fontSize
        val scale = scaleOf(layout, full)
        if (fitsAt(layout, full)) {
            assertTrue("$what fit whole at their size, drawn at $scale of it", scale >= AT_ITS_SIZE)
        } else {
            assertTrue("$what drawn at $scale of their size, 70 % at least", scale >= MINIMUM - SLACK)
        }
        assertButtonHolds(what, node)
        walk.shot("B-AX09-window")
    }

    /**
     * B-AX09: at the largest text, Add's words too long for two lines at their size (given 45 % of their width on one
     * line) are made smaller, to the size at which they fit whole on two lines, which is no smaller than 70 %.
     */
    @Test
    fun atTheLargestTextATitleTooLongForTwoLinesIsMadeSmallerButWhole() {
        walk.language = AppLanguage.Pt
        showAdd(LARGEST, area = oneLine(LARGEST) * SMALLER_SHARE)
        val node = drawnWords()
        val layout = layoutOf(node)
        val full = type(LARGEST).headline.fontSize
        val what = "Add's words in ${areaOf(layout)}"
        assertFalse("$what too long for two lines at their size", fitsAt(layout, full))
        assertTwoLinesAtMost(what, layout)
        assertWhole(what, layout)
        val scale = scaleOf(layout, full)
        assertTrue("$what made smaller: drawn at $scale of their size", scale < AT_ITS_SIZE)
        assertTrue("$what drawn at $scale of their size, 70 % at least", scale >= MINIMUM - SLACK)
        assertButtonHolds(what, node)
        walk.shot("B-AX09-smaller", alsoDark = false)
    }

    /**
     * B-AX09: at the largest text, Add's words given less room than their widest word takes even at 70 % (30 % of
     * their width on one line) are still never drawn under 70 %: on two lines at most, the button round them, and what
     * those can't hold is cut. Compose's search lands on a size from 70 % up (77 % here, "Adicio-" and "nar" on the
     * two lines). No window is this narrow: Android's least, 220 wide, leaves Add's words 148, where the widest word
     * of each language fits at 70 %.
     */
    @Test
    fun atTheLargestTextAWordWiderThanTheButtonIsBrokenButNeverDrawnUnderSeventyPercent() {
        walk.language = AppLanguage.Pt
        showAdd(LARGEST, area = oneLine(LARGEST) * LEAST_SHARE)
        val node = drawnWords()
        val layout = layoutOf(node)
        val full = type(LARGEST).headline.fontSize
        val what = "Add's words in ${areaOf(layout)}"
        assertTrue("$what: a word wider than the room even at 70 %", widestWord(layout, full * MINIMUM) > layout.layoutInput.constraints.maxWidth)
        assertTwoLinesAtMost(what, layout)
        val scale = scaleOf(layout, full)
        assertTrue("$what made smaller: drawn at $scale of their size", scale < AT_ITS_SIZE)
        assertTrue("$what drawn at $scale of their size, 70 % at least", scale >= MINIMUM - SLACK)
        assertButtonHolds(what, node)
        walk.shot("B-AX09-least", alsoDark = false)
    }

    // At the default text.

    /** B-AX09: at the default text, Add's words stand at their size in the window, on the lines they need, whole. */
    @Test
    fun atTheDefaultTextAddsWordsStandAtTheirSize() {
        assertFalse(DynamicTypeSize.of(1f).isAccessibilitySize)
        walk.language = AppLanguage.Pt
        showAdd(1f)
        val node = drawnWords()
        val layout = layoutOf(node)
        val what = "Add's words in ${areaOf(layout)}"
        assertEquals("$what on the lines they need", Int.MAX_VALUE, layout.layoutInput.maxLines)
        assertWhole(what, layout)
        val scale = scaleOf(layout, type(1f).headline.fontSize)
        assertTrue("$what at their size, drawn at $scale of it", scale >= AT_ITS_SIZE)
        assertButtonHolds(what, node)
        walk.shot("B-AX09-default")
    }

    /**
     * B-AX09: at the default text, Add's words too long for one line (given 70 % of their width on it) wrap onto the
     * lines they need at their size rather than shrink, and the button grows round them.
     */
    @Test
    fun atTheDefaultTextAddsWordsWrapAtTheirSizeRatherThanShrink() {
        walk.language = AppLanguage.Pt
        showAdd(1f, area = oneLine(1f) * WRAPPED_SHARE)
        val node = drawnWords()
        val layout = layoutOf(node)
        val what = "Add's words in ${areaOf(layout)}"
        assertEquals("$what on the lines they need", Int.MAX_VALUE, layout.layoutInput.maxLines)
        assertTrue("$what wrapped: ${layout.lineCount} lines", layout.lineCount >= 2)
        assertWhole(what, layout)
        val scale = scaleOf(layout, type(1f).headline.fontSize)
        assertTrue("$what at their size, drawn at $scale of it", scale >= AT_ITS_SIZE)
        assertButtonHolds(what, node)
        walk.shot("B-AX09-default-wrapped", alsoDark = false)
    }

    private companion object {
        const val FOLDER = "addbutton"

        /** Android's largest text step, which the app reads as accessibility2. */
        const val LARGEST = 2f

        /** `.minimumScaleFactor(0.7)` at the accessibility sizes. */
        const val MINIMUM = 0.7f

        /** Drawn at its size: a text that fits whole stands at its size (`MinimumScale`), within the measure's rounding. */
        const val AT_ITS_SIZE = 0.995f
        const val SLACK = 0.02f

        /**
         * The words' share of their width on one line at their size: too little for two lines at it but room for two
         * at 70 %; less than the widest word takes at 70 %; too little for one at the default text.
         */
        const val SMALLER_SHARE = 0.45f
        const val LEAST_SHARE = 0.3f
        const val WRAPPED_SHARE = 0.7f

        /** A synthetic UPS number, so Add can add. */
        const val NUMBER = "1Z999AA10123456784"

        /** The button's insets either side of its words: 20 round the button, 16 inside it. */
        val BUTTON_INSETS = (20 + 16).dp * 2

        /** `minHeight: 50`. */
        val MIN_HEIGHT = 50.dp

        val TOLERANCE: Dp = 0.5.dp

        /** A pixel, for bounds in the root's pixels. */
        const val PIXEL = 1f
    }
}
