package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_ADD
import com.plhery.peekdeliverytracker.ui.deliveries.EMPTY_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN_POSTER
import com.plhery.peekdeliverytracker.ui.deliveries.LocalArchiveWord
import com.plhery.peekdeliverytracker.ui.deliveries.LocalFirstOpenPing
import com.plhery.peekdeliverytracker.ui.deliveries.LocalNextUpWords
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PULL_INDICATOR
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The words that shrink rather than wrap or be cut off (A-AX04, `minimumScaleFactor`), on this device's own fonts and
 * window, as the iPhone draws them: each on its lines at its size where it fits whole, else at the largest size down to
 * its factor that does, never cut. The swipe action's word to 70 %, "Next up" over the next delivery's pass to 70 %,
 * the first open's ping to 80 %, the pull's words to 80 % in their 48-point band
 * (until xxxLarge, where they take the lines they need), the empty list's buttons to 80 % on one line (or one under the
 * other, wrapping, where they don't fit side by side), the first open's question to 70 % on two lines and the poster's
 * to 60 %. A word drawn at its size is measured at that size on one line; how much smaller it is drawn is its width on
 * one line against that. The demo's parcels on fake deliveries ([ParcelWalk]), the text size the composition's own
 * Density. Shots in minimumscale/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class MinimumScaleDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** The swipe action's word as last laid out, told by the action ([LocalArchiveWord]). */
    @Volatile
    private var word: TextLayoutResult? = null

    /** "Next up" as last laid out, told by the pass ([LocalNextUpWords]). */
    @Volatile
    private var nextUp: TextLayoutResult? = null

    /** The first open's ping as last laid out, told by the ping ([LocalFirstOpenPing]). */
    @Volatile
    private var ping: TextLayoutResult? = null

    private fun at(scale: Float) {
        walk.fontScale = scale
        walk.recompose()
        walk.settle()
    }

    /** The fonts the app sets its words in at [scale], in the walk's language. */
    private fun type(scale: Float, size: DynamicTypeSize = DynamicTypeSize.of(scale)): PeekTypography =
        PeekTypography(size, Density(compose.density.density, scale), walk.language)

    private fun bounds(tag: String): DpRect = compose.onNode(hasTestTag(tag), useUnmergedTree = true).getUnclippedBoundsInRoot()

    /**
     * The layout of the one placed text `matcher` finds, as TalkBack reads it: its style as given, its lines drawn. In
     * the [merged] tree, which leaves out what a layout measures to choose itself and clears (`ViewThatFits`).
     */
    private fun layoutOf(matcher: SemanticsMatcher, merged: Boolean = false): TextLayoutResult {
        val nodes = compose.onAllNodes(matcher, useUnmergedTree = !merged).fetchSemanticsNodes().filter { it.layoutInfo.isPlaced }
        val node = nodes.singleOrNull() ?: throw AssertionError("One placed text, not ${nodes.map { it.boundsInRoot }}")
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    /** How much smaller than [full] a text is drawn, and whether it would fit whole at [full] on its lines and width. */
    private class Shrink(val scale: Float, val fitsWhole: Boolean)

    private fun shrinkOf(layout: TextLayoutResult, full: TextUnit): Shrink {
        val input = layout.layoutInput
        val style = input.style.copy(fontSize = full)
        val measurer = TextMeasurer(input.fontFamilyResolver, input.density, input.layoutDirection)
        val oneLine = measurer.measure(input.text, style = style, softWrap = false, maxLines = 1)
        val atFull = measurer.measure(
            input.text, style = style, overflow = input.overflow, softWrap = input.softWrap, maxLines = input.maxLines,
            constraints = Constraints(maxWidth = input.constraints.maxWidth),
        )
        val fits = !atFull.hasVisualOverflow && !atFull.isLineEllipsized(atFull.lineCount - 1)
        return Shrink(layout.multiParagraph.intrinsics.maxIntrinsicWidth / oneLine.multiParagraph.intrinsics.maxIntrinsicWidth, fits)
    }

    /** [what] drawn whole: no line cut short, nothing ellipsized, nothing clipped. */
    private fun assertWhole(what: String, layout: TextLayoutResult) {
        val last = layout.lineCount - 1
        assertFalse("$what clipped: ${layout.multiParagraph.width} × ${layout.multiParagraph.height} in ${layout.size}", layout.hasVisualOverflow)
        assertFalse("$what ellipsized", layout.isLineEllipsized(last))
        assertEquals("$what whole", layout.layoutInput.text.length, layout.getLineEnd(last))
    }

    /** `.minimumScaleFactor(minimum)` of [what] at [full]: whole, at its size where it fits, else down to [minimum]. */
    private fun assertShrinksTo(what: String, layout: TextLayoutResult, full: TextUnit, minimum: Float) {
        assertWhole(what, layout)
        val shrink = shrinkOf(layout, full)
        if (shrink.fitsWhole) {
            assertTrue("$what fits whole at its size, drawn at ${shrink.scale} of it", shrink.scale >= AT_ITS_SIZE)
        } else {
            assertTrue("$what too long for its size, drawn at ${shrink.scale} of it, $minimum at least", shrink.scale >= minimum - SLACK)
            assertTrue("$what no larger than its size: ${shrink.scale}", shrink.scale <= 1f + SLACK)
        }
    }

    /**
     * A-AX04: the swipe action's word (Polish's "Archiwizuj") keeps to one line in the action's 88 points, at its size
     * where it fits, as at the default text, and else shrinking to 70 % rather than be cut off, as at the largest text,
     * where it has grown only to accessibility1's caption 2 (`.dynamicTypeSize(...accessibility1)`,
     * `.minimumScaleFactor(0.7)`).
     */
    @Test
    fun theSwipeActionsWordKeepsToOneLineShrinkingToSeventyPercent() {
        walk.language = AppLanguage.Pl
        walk.show(DemoCards.parcels(AppLanguage.Pl), around = { content ->
            CompositionLocalProvider(LocalArchiveWord provides { word = it }) { content() }
        })
        for (scale in listOf(1f, 2f)) {
            if (scale != 1f) at(scale)
            compose.waitUntil(LONG_MILLIS) { word?.layoutInput?.density?.fontScale == scale }
            val layout = checkNotNull(word)
            assertEquals(walk.text("parcel.archive"), layout.layoutInput.text.text)
            assertEquals("On one line at $scale", 1, layout.lineCount)
            val full = type(scale, DynamicTypeSize.of(scale).capped(DynamicTypeSize.Accessibility1))
                .style(IosTextStyle.Caption2, FontWeight.SemiBold).fontSize
            assertShrinksTo("The action's word at $scale", layout, full, minimum = 0.7f)
            // Whole at its size at the default text; too long for it at the largest, where it shrinks.
            assertEquals("Fits at its size at $scale", scale == 1f, shrinkOf(layout, full).fitsWhole)
        }
    }

    /**
     * `.lineLimit(1).minimumScaleFactor(minimum)` of [what] by its font size: [full] where it fits whole at it, else
     * smaller, down to [minimum] of it, in steps of [step], and cut only once at that least size.
     */
    private fun assertFitted(what: String, layout: TextLayoutResult, full: TextUnit, minimum: Float, step: Float) {
        val size = layout.layoutInput.style.fontSize.value
        assertEquals("$what on one line", 1, layout.lineCount)
        assertTrue("$what at $size, no larger than its ${full.value}", size <= full.value + 0.01f)
        assertTrue("$what at $size, $minimum of its ${full.value} at least", size >= full.value * minimum - 0.01f)
        val cut = layout.hasVisualOverflow || layout.isLineEllipsized(0)
        when {
            cut -> assertTrue("$what cut at $size before it shrank to its least", size < full.value * minimum + step)
            shrinkOf(layout, full).fitsWhole -> assertEquals("$what fits whole at its size", full.value, size, 0.01f)
            else -> assertWhole(what, layout)
        }
    }

    /**
     * A-AX04: "Next up" over the next delivery's pass, in Polish's longer words ("Najbliższa dostawa"), on one line at
     * its size where it fits and else down to 70 % in tenths of a point rather than be cut, its size growing no further
     * than accessibility1's caption 2 (`.dynamicTypeSize(...accessibility1)`, `.lineLimit(1).minimumScaleFactor(0.7)`).
     */
    @Test
    fun theNextUpWordsKeepToOneLineShrinkingToSeventyPercent() {
        walk.language = AppLanguage.Pl
        walk.show(DemoCards.parcels(AppLanguage.Pl), around = { content ->
            CompositionLocalProvider(LocalNextUpWords provides { nextUp = it }) { content() }
        })
        for (scale in listOf(1f, 1.3f, 2f)) {
            if (scale != 1f) at(scale)
            compose.waitUntil(LONG_MILLIS) { nextUp?.layoutInput?.density?.fontScale == scale }
            val layout = checkNotNull(nextUp)
            assertEquals(walk.text("app.nextUp").uppercase(AppLanguage.Pl.locale), layout.layoutInput.text.text)
            val full = type(scale, DynamicTypeSize.of(scale).capped(DynamicTypeSize.Accessibility1)).caption2.fontSize
            assertFitted("Next up at $scale", layout, full, minimum = 0.7f, step = 0.1f)
            if (scale == 2f) walk.shot("A-AX04-next-up", alsoDark = false)
        }
    }

    /**
     * A-AX04: the first open's ping, what an alert would say as the journey reaches the parcel out for delivery, in
     * Russian's longer words, on one line in its capsule at its size where it fits and else down to 80 % rather than be
     * cut (`.lineLimit(1).minimumScaleFactor(0.8)`). The journey moves only with Reduce Motion off: it is let run on the
     * test's own clock until the ping shows, the window never idle while it rolls, and stilled again after.
     */
    @Test
    fun theFirstOpensPingKeepsToOneLineShrinkingToEightyPercent() {
        walk.language = AppLanguage.Ru
        walk.show(
            emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) },
            around = { content -> CompositionLocalProvider(LocalFirstOpenPing provides { ping = it }) { content() } },
        ) { isGuest = true }
        for (scale in listOf(1f, 2f)) {
            at(scale)
            ping = null
            compose.mainClock.autoAdvance = false
            walk.onUi { walk.reduceMotion = false }
            var waited = 0L
            while (ping?.layoutInput?.density?.fontScale != scale && waited < JOURNEY_MILLIS) {
                compose.mainClock.advanceTimeBy(FRAME_MILLIS)
                waited += FRAME_MILLIS
            }
            val layout = checkNotNull(ping) { "No ping at $scale after $waited ms of the journey" }
            val full = type(scale).style(IosTextStyle.Caption, FontWeight.Medium).fontSize
            assertFitted("The ping at $scale", layout, full, minimum = 0.8f, step = 0.25f)
            walk.onUi { walk.reduceMotion = true }
            compose.mainClock.autoAdvance = true
            walk.settle()
        }
    }

    /**
     * A finger down a quarter of the way down the list drawn 0.6 of its height further down and held a moment before
     * it lifts: past the threshold, whatever the window's height.
     */
    private fun pull() {
        walk.node(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, height * 0.25f))
            val steps = 30
            repeat(steps) { moveBy(Offset(0f, height * 0.6f / steps)) }
            advanceEventTime(200)
            up()
        }
    }

    /**
     * A-AX04: the pull's band stays 48 tall up to xxLarge, its words on one line under the seal, shrinking to 80 %
     * rather than wrap (`.lineLimit(1).minimumScaleFactor(0.8)`); from xxxLarge they take the lines they need at their
     * size and the band grows round them, at least as tall at the largest text.
     */
    @Test
    fun thePullsWordsKeepTheBandFortyEightTallUpToXxLargeThenGrowIt() {
        assertEquals(DynamicTypeSize.XXLarge, DynamicTypeSize.of(1.2f))
        assertEquals(DynamicTypeSize.XXXLarge, DynamicTypeSize.of(1.3f))
        walk.show(DemoCards.parcels())
        val heights = mutableMapOf<Float, Dp>()
        for (scale in listOf(1f, 1.2f, 1.3f, 2f)) {
            at(scale)
            val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
            walk.onUi { walk.source.checking = checked }
            compose.mainClock.autoAdvance = false
            pull()
            compose.mainClock.advanceTimeBy(2_000)
            heights[scale] = bounds(PULL_INDICATOR).height
            if (scale == 1f || scale == 2f) walk.shot("A-AX04-pull", alsoDark = false)
            checked.complete(ParcelStore.RefreshResult.Unchanged)
            compose.mainClock.advanceTimeBy(4_000)
            compose.mainClock.autoAdvance = true
            compose.waitForIdle()
        }
        for (scale in listOf(1f, 1.2f)) assertEquals("The band at $scale: $heights", BAND.value, heights.getValue(scale).value, TOLERANCE.value)
        val grown = heights.getValue(1.3f)
        assertTrue("The band grown round its words at xxxLarge: $heights", grown > BAND + TOLERANCE)
        assertTrue("At least as tall at the largest text: $heights", heights.getValue(2f) >= grown - TOLERANCE)
    }

    /**
     * A-AX04: the empty list's quiet buttons (German's "Barcode scannen", where the device scans, and "Paket
     * hinzufügen") stand side by side while both fit, each word on one line, at its size where it fits and down to
     * 80 % (`.lineLimit(1).minimumScaleFactor(0.8)`); else one under the other, 10 apart, each word wrapping at its
     * size (`ViewThatFits`).
     */
    @Test
    fun theEmptyListsButtonsKeepTheirWordsOnOneLineShrinkingToEightyPercentOrStackAndWrap() {
        walk.language = AppLanguage.De
        walk.show(emptyList())
        for (scale in listOf(1f, 1.3f, 2f)) {
            at(scale)
            val buttons = listOf(EMPTY_SCAN to "add.scan", EMPTY_ADD to "app.addParcel").filter { walk.exists(it.first) }
            assertTrue("Add a parcel at $scale", buttons.any { it.first == EMPTY_ADD })
            val full = type(scale).style(IosTextStyle.Subheadline, FontWeight.Medium).fontSize
            val layouts = buttons.map { (tag, key) -> layoutOf(hasText(walk.text(key)) and hasAnyAncestor(hasTestTag(tag))) }
            val oneLine = layouts.first().layoutInput.maxLines == 1
            for ((layout, button) in layouts.zip(buttons)) {
                val what = "${button.first} at $scale"
                assertEquals("$what like the other", oneLine, layout.layoutInput.maxLines == 1)
                if (oneLine) {
                    assertEquals("$what on one line", 1, layout.lineCount)
                    assertShrinksTo(what, layout, full, minimum = 0.8f)
                } else {
                    assertWhole(what, layout)
                    assertTrue("$what wrapping at its size: ${shrinkOf(layout, full).scale}", shrinkOf(layout, full).scale >= AT_ITS_SIZE)
                }
            }
            if (buttons.size == 2) {
                val scan = bounds(EMPTY_SCAN)
                val add = bounds(EMPTY_ADD)
                if (oneLine) {
                    assertEquals("Side by side at $scale", scan.top.value, add.top.value, LAYOUT_TOLERANCE.value)
                    assertTrue("10 apart at $scale: $scan, $add", add.left - scan.right >= 10.dp - LAYOUT_TOLERANCE)
                } else {
                    assertTrue("One under the other at $scale: $scan, $add", add.top - scan.bottom >= 10.dp - LAYOUT_TOLERANCE)
                }
            }
            if (scale == 1f || scale == 2f) {
                compose.reveal(EMPTY_ADD)
                walk.settle()
                walk.shot("A-AX04-empty-buttons", alsoDark = false)
            }
        }
    }

    /**
     * A-AX04: the first open's question ("peek.title") on two lines at most, at its size where it fits and down to
     * 70 % (`.lineLimit(2).minimumScaleFactor(0.7)`); at the accessibility sizes on the lines it needs at its size,
     * the screen scrolling. On the poster, where the window is 600 points or more both ways, at its fixed size or down
     * to 60 % on two lines (`.minimumScaleFactor(0.6)`), whatever the text size.
     */
    @Test
    fun theFirstOpensQuestionShrinksToSeventyPercentOnTwoLinesOrSixtyOnThePoster() {
        walk.show(emptyList(), session = AuthenticationState.Welcome, until = { walk.exists(FIRST_OPEN) }) { isGuest = true }
        val question = hasText(walk.text("peek.title")) and SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading)
        for (scale in listOf(1f, 1.3f, 1.5f, 2f)) {
            at(scale)
            val size = DynamicTypeSize.of(scale)
            val layout = layoutOf(question, merged = true)
            if (walk.exists(FIRST_OPEN_POSTER)) {
                val what = "The poster's question at $scale"
                assertTrue("$what on two lines at most: ${layout.lineCount}", layout.lineCount <= 2)
                assertShrinksTo(what, layout, layout.layoutInput.style.fontSize, minimum = 0.6f)
            } else if (size.isAccessibilitySize) {
                val what = "The question at $scale ($size)"
                assertWhole(what, layout)
                assertEquals("$what on the lines it needs", Int.MAX_VALUE, layout.layoutInput.maxLines)
                val full = type(scale).style(IosTextStyle.LargeTitle).fontSize
                assertTrue("$what at its size: ${shrinkOf(layout, full).scale}", shrinkOf(layout, full).scale >= AT_ITS_SIZE)
            } else {
                val what = "The question at $scale ($size)"
                assertTrue("$what on two lines at most: ${layout.lineCount}", layout.lineCount <= 2)
                assertShrinksTo(what, layout, type(scale).style(IosTextStyle.LargeTitle).fontSize, minimum = 0.7f)
            }
            if (scale == 1f || scale == 2f) walk.shot("A-AX04-first-open-question", alsoDark = false)
        }
    }

    private companion object {
        const val FOLDER = "minimumscale"

        /** Drawn at its size: a text that fits whole stands at its size (`MinimumScale`), within the measure's rounding. */
        const val AT_ITS_SIZE = 0.995f
        const val SLACK = 0.02f

        /** How long the first open's journey may take to reach a step with a ping, and the frames it is moved by. */
        const val JOURNEY_MILLIS = 30_000L
        const val FRAME_MILLIS = 16L

        /** `PullToRefreshIndicator.height`: the band's height while its words keep to one line. */
        val BAND = 48.dp
        val TOLERANCE: Dp = 0.5.dp

        /** Places and gaps, in whole pixels. */
        val LAYOUT_TOLERANCE: Dp = 1.dp
    }
}
