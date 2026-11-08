package com.plhery.peekdeliverytracker.ui

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.width
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.PassportStatistics
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_AVERAGE
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_PERSONAL_BEST
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_SHOW_ALL
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_STAMP
import com.plhery.peekdeliverytracker.ui.passport.passportDuration
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import com.plhery.peekdeliverytracker.ui.theme.monospacedDigit
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The passport at the larger text sizes (A-AX04, A-AX05) on this device's own window, as PassportView.swift lays it out:
 * the spread from 920 points of page at the accessibility sizes (760 below them) in a window wide and tall in its own
 * right; the stamps four to a row, three from xxLarge and two at the accessibility sizes; the average and the fastest
 * delivery side by side, each time on one line shrinking to three quarters, and one over the other at the accessibility
 * sizes, where the times wrap; the cover's seal dropped at the accessibility sizes. Each test branches on the window it
 * finds (its size and safe sides), never on the device, and asserts what that window shows. The demo's parcels on fake
 * deliveries ([ParcelWalk]), the text size the composition's own Density; the window narrowed, where a test needs it,
 * by a box of its own width at the window's leading edge, which leaves the window's height and safe sides as they are.
 * Shots in passportlarge/<row>-<moment>.png.
 */
@RunWith(AndroidJUnit4::class)
class PassportLargeTextDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val parcels = DemoCards.parcels()

    /** The width the root is given, the window's where null. */
    private var width by mutableStateOf<Dp?>(null)

    /** The window as the root last measured it: its size and its safe sides, which the passport keeps clear of. */
    @Volatile
    private var room: Room? = null

    private data class Room(val width: Dp, val height: Dp, val start: Dp, val end: Dp) {
        val regular: Boolean get() = width >= REGULAR_WIDTH && height >= REGULAR_HEIGHT
        val sides: Dp get() = start + end
    }

    /** The root in a box [width] wide at the window's leading edge, as tall as the window; the window kept in [room]. */
    @Composable
    private fun Narrowed(content: @Composable () -> Unit) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val direction = LocalLayoutDirection.current
            val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout).asPaddingValues()
            val measured = Room(maxWidth, maxHeight, safe.calculateStartPadding(direction), safe.calculateEndPadding(direction))
            SideEffect { room = measured }
            val given = width?.let { minOf(it, maxWidth) } ?: maxWidth
            Box(Modifier.align(Alignment.TopStart).width(given).fillMaxHeight()) { content() }
        }
    }

    /** The demo's passport, chosen from the tab bar, at [scale]. */
    private fun showPassport(scale: Float = 1f) {
        walk.fontScale = scale
        walk.show(parcels, session = AuthenticationState.Demo, around = { content -> Narrowed(content) })
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PASSPORT_COVER) }
        walk.settle()
    }

    private fun at(scale: Float) {
        walk.fontScale = scale
        walk.recompose()
        walk.settle()
    }

    private fun narrowTo(given: Dp?) {
        width = given
        walk.recompose()
        walk.settle()
    }

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getUnclippedBoundsInRoot()

    /**
     * Whether the passport stands as a spread: the times on the second page, beside the cover's, rather than under the
     * cover and the stamps on the one page.
     */
    private fun spread(): Boolean {
        val cover = bounds(PASSPORT_COVER)
        val average = bounds(PASSPORT_AVERAGE)
        val beside = average.left >= cover.right - TOLERANCE
        if (!beside) assertTrue("One page: the times under the cover, $average under $cover", average.top >= cover.bottom)
        return beside
    }

    /**
     * A-AX05: at the accessibility sizes the passport spreads over two pages from 920 points of page between the
     * window's safe sides (`PassportSpread.fits`, two 420-point columns and their 20-point margins), in a window at
     * least 600 wide and 480 tall; a window that gives it less shows one page, the times under the cover.
     */
    @Test
    fun atTheLargestTextTheSpreadTakesNineHundredTwentyPointsOfPage() = spreadsFrom(scale = 2f, line = LARGE_SPREAD)

    /** A-AX05: below the accessibility sizes the spread takes 760 points of page, two 340-point columns and margins. */
    @Test
    fun atTheDefaultTextTheSpreadTakesSevenHundredSixtyPointsOfPage() = spreadsFrom(scale = 1f, line = SPREAD)

    /**
     * The spread at [scale], half a point either side of [line] where the window is wide enough to narrow to them, else
     * at the window's own width: two pages exactly where the page is [line] wide or more in a regular window.
     */
    private fun spreadsFrom(scale: Float, line: Float) {
        showPassport(scale)
        val room = checkNotNull(room)
        val name = if (scale >= 2f) "large" else "default"
        if (room.regular && room.width >= room.sides + (line + 0.5f).dp) {
            narrowTo(room.sides + (line - 0.5f).dp)
            assertTrue("Half a point short of $line, ${room.sides} of safe sides: one page", !spread())
            walk.shot("A-AX05-passport-$name-one-page", alsoDark = false)
            narrowTo(room.sides + (line + 0.5f).dp)
            assertTrue("Half a point past $line, ${room.sides} of safe sides: the spread", spread())
            walk.shot("A-AX05-passport-$name-spread")
        } else {
            val spreads = room.regular && room.width - room.sides >= line.dp
            assertEquals("A window of $room, a spread from $line of page", spreads, spread())
            walk.shot("A-AX05-passport-$name-window")
        }
    }

    /** The stamps' tags and where each stands, in pixels from the root, in the tree's order. */
    private fun stamps(): List<Pair<String, Offset>> = compose.onAllNodes(
        SemanticsMatcher("a stamp") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("$PASSPORT_STAMP.") == true },
        useUnmergedTree = true,
    ).fetchSemanticsNodes().map { it.config[SemanticsProperties.TestTag] to it.positionInRoot }

    /**
     * A-AX05: the stamps stand four to a row, three from the xxLarge text size and two at the accessibility sizes
     * (`LazyVGrid` of 4, 3 or 2 flexible columns), all twelve shown: the first row holds that many, each in a column of
     * its own, and the grid that many rows, on a spread's page and on one page alike.
     */
    @Test
    fun theStampsStandFourThenThreeFromXxLargeThenTwoAtTheAccessibilitySizes() {
        showPassport()
        // The demo has earned nine and shows the three to come: all twelve, unless some were left out.
        if (walk.exists(PASSPORT_SHOW_ALL)) compose.reveal(compose.onNodeWithTag(PASSPORT_SHOW_ALL)).performClick()
        compose.waitUntil(LONG_MILLIS) { stamps().size == ALL_STAMPS }
        for ((scale, columns) in listOf(1f to 4, 1.2f to 3, 1.3f to 3, 1.5f to 2, 2f to 2)) {
            at(scale)
            val size = DynamicTypeSize.of(scale)
            assertEquals("$size at $scale", columns, if (size.isAccessibilitySize) 2 else if (size >= DynamicTypeSize.XXLarge) 3 else 4)
            val shown = stamps()
            assertEquals("All the stamps at $scale", ALL_STAMPS, shown.size)
            val tops = shown.map { it.second.y.roundToInt() }
            val firstRow = shown.filter { abs(it.second.y - tops.min()) < 2f }
            assertEquals("The first row at $scale ($size): $shown", columns, firstRow.size)
            assertEquals("Rows at $scale ($size): $shown", (ALL_STAMPS + columns - 1) / columns, tops.distinct().size)
            val lefts = firstRow.map { it.second.x.roundToInt() }
            assertEquals("Each stamp of the first row in a column of its own: $lefts", columns, lefts.distinct().size)
        }
        compose.reveal(compose.onNodeWithTag(stampTag(FIRST_STAMP)))
        walk.settle()
        walk.shot("A-AX05-passport-stamps")
    }

    private fun stampTag(id: String): String = "$PASSPORT_STAMP.$id"

    /**
     * A-AX04, A-AX05: the average delivery time and the fastest stand side by side, 18 apart and level, each time on one
     * line that shrinks to three quarters rather than wrap (`.lineLimit(1).minimumScaleFactor(0.75)`); at the
     * accessibility sizes one over the other, 18 apart, each across the page, its time at its full size on the lines it
     * needs (`.lineLimit(nil)`). A card is as tall as its time over its title, 6 apart, and 48 at least: its height
     * tells how many lines its time takes and at what size.
     */
    @Test
    fun theTimesStandSideBySideEachOnOneLineThenOneOverTheOtherAtTheAccessibilitySizes() {
        showPassport()
        val stats = PassportStatistics(parcels)
        val average = passportDuration(checkNotNull(stats.averageDeliveryDuration) { "The demo has no average" }, walk.localizer)
        val fastest = passportDuration(checkNotNull(stats.fastestDelivery) { "The demo has no fastest delivery" }.duration, walk.localizer)
        for (scale in listOf(1f, 1.3f, 1.5f, 2f)) {
            at(scale)
            val large = DynamicTypeSize.of(scale).isAccessibilitySize
            val left = bounds(PASSPORT_AVERAGE)
            val right = bounds(PASSPORT_PERSONAL_BEST)
            if (large) {
                assertTrue("At $scale the fastest 18 under the average: $right, $left", right.top - left.bottom >= 18.dp - TOLERANCE)
                assertEquals("At $scale one over the other, level at their start", left.left.value, right.left.value, TOLERANCE.value)
                assertEquals("At $scale as wide as each other", left.width.value, right.width.value, TOLERANCE.value)
            } else {
                assertEquals("At $scale level", left.top.value, right.top.value, TOLERANCE.value)
                assertTrue("At $scale the fastest 18 after the average: $right, $left", right.left - left.right >= 18.dp - TOLERANCE)
            }
            assertTimeCard(PASSPORT_AVERAGE, walk.text("passport.average"), average, scale)
            assertTimeCard(PASSPORT_PERSONAL_BEST, walk.text("passport.personalBest"), fastest, scale)
            if (scale == 1f || scale == 2f) {
                compose.reveal(compose.onNodeWithTag(PASSPORT_PERSONAL_BEST))
                walk.settle()
                walk.shot("A-AX05-passport-times", alsoDark = false)
            }
        }
    }

    /**
     * The card of [tag] as tall as [duration] over [title] at [scale]: its time on one line at three quarters of its
     * size or more below the accessibility sizes, at its full size on one line or more at them. The heights are the
     * time's line and the title's lines, at the card's width, as the card's own fonts lay them out.
     */
    private fun assertTimeCard(tag: String, title: String, duration: String, scale: Float) {
        val card = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().size
        val density = Density(compose.density.density, scale)
        val size = DynamicTypeSize.of(scale)
        val type = PeekTypography(size, density, walk.language)
        val measurer = TextMeasurer(createFontFamilyResolver(compose.activity), density, LayoutDirection.Ltr)
        val time = type.style(IosTextStyle.Title2, FontWeight.Medium).monospacedDigit().sfLines()
        val line = measurer.measure(duration, style = time, softWrap = false, maxLines = 1).size.height
        val words = measurer.measure(title, style = type.caption.sfLines(), constraints = Constraints(maxWidth = card.width)).size.height
        val gap = with(density) { 6.dp.roundToPx() }
        val least = with(density) { 48.dp.roundToPx() }
        val slack = with(density) { 2.dp.roundToPx() }
        if (size.isAccessibilitySize) {
            assertTrue(
                "$tag at $scale ($size): ${card.height} px, at least \"$duration\" at its full size ($line) over its title ($words)",
                card.height >= max(least, line + gap + words) - slack,
            )
        } else {
            val most = max(least, line + gap + words)
            val fewest = max(least, (line * TIME_MINIMUM_SCALE).toInt() + gap + words)
            assertTrue(
                "$tag at $scale ($size): ${card.height} px, \"$duration\" on one line ($line at full size) over its title ($words)",
                card.height in (fewest - slack)..(most + slack),
            )
        }
    }

    /** The near-white pixels in what has [tag], light appearance: the cover's seal is the only white on its yellow. */
    private fun whitePixels(tag: String): Int {
        val image = compose.onNodeWithTag(tag, useUnmergedTree = true).captureToImage().asAndroidBitmap()
        val pixels = IntArray(image.width * image.height)
        image.getPixels(pixels, 0, image.width, 0, 0, image.width, image.height)
        return pixels.count { Color.red(it) >= WHITE && Color.green(it) >= WHITE && Color.blue(it) >= WHITE }
    }

    /**
     * A-AX05: the cover's seal, the parcel stamp tilted at its side on its white paper, stands there up to xxxLarge
     * and is dropped at the accessibility sizes (`if !dynamicTypeSize.isAccessibilitySize`): the cover's yellow then
     * holds no white at all.
     */
    @Test
    fun theCoverDropsItsSealAtTheAccessibilitySizes() {
        showPassport()
        val square = compose.density.density * compose.density.density
        for (scale in listOf(1f, 1.3f, 1.5f, 2f)) {
            at(scale)
            val size = DynamicTypeSize.of(scale)
            val white = whitePixels(PASSPORT_COVER)
            if (size.isAccessibilitySize) {
                assertTrue("At $scale ($size) no seal: $white white pixels", white <= SPECKS * square)
            } else {
                assertTrue("At $scale ($size) the seal: $white white pixels", white >= SEAL_PAPER * square)
            }
            if (scale == 1f || scale == 2f) walk.shot("A-AX05-passport-cover", alsoDark = false)
        }
    }

    private companion object {
        const val FOLDER = "passportlarge"

        /** `PassportSpread.fits`: two columns of 340 (420 at the accessibility sizes), each with its 2 × 20 margins. */
        const val SPREAD = 760f
        const val LARGE_SPREAD = 920f

        /** The regular size classes, as the passport and the deliveries take them. */
        val REGULAR_WIDTH = 600.dp
        val REGULAR_HEIGHT = 480.dp

        /** `minimumScaleFactor(0.75)` of a time on one line. */
        const val TIME_MINIMUM_SCALE = 0.75f

        /** The demo's stamps: the nine it has earned and the three to come. */
        const val ALL_STAMPS = 12
        const val FIRST_STAMP = "first"

        /** A channel this bright or more, on all three, is the seal's white paper. */
        const val WHITE = 235

        /** Square points of white: the seal's paper (66 × 80, less its perforations, frame and symbol), and none. */
        const val SEAL_PAPER = 1_500f
        const val SPECKS = 20f

        val TOLERANCE: Dp = 1.dp
    }
}
