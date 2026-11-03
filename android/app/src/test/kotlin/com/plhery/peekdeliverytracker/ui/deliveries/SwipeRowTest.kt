package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.VibrationEffect.Composition
import android.os.VibratorManager
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.MouseButton
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.Haptics
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import kotlin.math.roundToInt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.shadows.ShadowVibrator.PrimitiveEffect

/**
 * A swiping card's row on its own (ExperimentalSwipeToArchiveModifier): its press, told by the row's own drag as
 * ArchivePanGestureRecognizer tells it (A-AN32, A-G02), the open action dimming under the finger as a plain button
 * (A-G03), its word shrinking rather than being cut off (A-AX04), and the swipe's haptics (A-H04, A-H05).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class SwipeRowTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val vibrator = context.getSystemService(VibratorManager::class.java).defaultVibrator
    private val feeling = FeelingView(context)
    private val haptics = Haptics(feeling, vibrator)
    private val grey = Color(0.5f, 0.5f, 0.5f)
    private val interactions = mutableListOf<Interaction>()
    private val words = mutableListOf<TextLayoutResult>()
    private lateinit var swipe: ArchiveSwipe
    private var opened = 0

    /** The row with a grey card, `title` its action's word, on black in 24 points of margin. */
    private fun row(reduceMotion: Boolean = false, title: String = "Archive", typeSize: DynamicTypeSize = DynamicTypeSize.Large) {
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, 1f), LocalArchiveWord provides { words += it }) {
                PeekTheme(dark = false, highContrast = false, reduceMotion = reduceMotion, dynamicTypeSize = typeSize, carrierIdentities = null) {
                    val scope = rememberCoroutineScope()
                    val made = remember { ArchiveSwipe(scope, { haptics }, reduceMotion) }
                    swipe = made
                    val motion = made.motion
                    LaunchedEffect(motion) { if (motion != null) made.run(motion) }
                    LaunchedEffect(made) { made.presses.interactions.collect { interactions += it } }
                    Box(Modifier.testTag(FRAME).background(Color.Black).padding(24.dp)) {
                        SwipeToArchive(made, title, 16.dp, shadow = false) { moved ->
                            Box(
                                moved
                                    .fillMaxWidth()
                                    .height(96.dp)
                                    .testTag(CARD)
                                    .cardButton(
                                        decorative = false, label = "Card", hint = null, selected = false, onOpen = { opened++ },
                                        onLongClick = null, cornerRadius = 16.dp, shadow = false, actions = listOf(archiveAction(made, title)),
                                        onTap = { made.tap { opened++ } },
                                    )
                                    .background(grey),
                            )
                        }
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    private fun dp(value: Float): Float = value * compose.density.density

    /** The row is pressed now: a press told and not yet ended. */
    private fun pressed(): Boolean {
        compose.waitForIdle()
        val last = interactions.lastOrNull()
        return last is PressInteraction.Press
    }

    /** The window drawn now in software, as Robolectric's hardware capture may return a frame before. */
    private fun drawing(): Bitmap {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        return whole
    }

    /** The red channel `x` points across and halfway down the row, from the frame's left. */
    private fun red(x: Float): Int {
        val frame = compose.onNodeWithTag(FRAME).fetchSemanticsNode().boundsInRoot
        val pixel = drawing().getPixel((frame.left + dp(x)).roundToInt(), frame.center.y.roundToInt())
        return pixel shr 16 and 0xFF
    }

    /** The row's middle, and just inside its leading edge. */
    private fun centre(): Int {
        val frame = compose.onNodeWithTag(FRAME).fetchSemanticsNode().boundsInRoot
        return red(frame.width / compose.density.density / 2)
    }

    private fun edge(): Int = red(24f + 2f)

    private fun assertNear(expected: Int, actual: Int) = assertEquals(expected.toDouble(), actual.toDouble(), 1.0)

    private fun hold(at: Offset? = null) = compose.onNodeWithTag(FRAME).performTouchInput { down(at ?: center) }

    private fun lift() = compose.onNodeWithTag(FRAME).performTouchInput { up() }

    @Test
    fun aRestingFingerPressesTheWholeRowFiftyMillisecondsAfterItWentDown() {
        row()
        compose.mainClock.autoAdvance = false
        assertNear(128, centre())
        hold()
        compose.mainClock.advanceTimeBy(40)
        assertFalse(pressed())
        assertNear(128, centre())
        compose.mainClock.advanceTimeBy(20)
        assertTrue(pressed())
        compose.mainClock.advanceTimeBy(500)
        // A little darker (-0.025 of each channel), and the row, its clip with it, smaller about its middle: its edge
        // shows the black around it.
        assertNear(122, centre())
        assertNear(0, edge())
        lift()
        compose.mainClock.advanceTimeBy(500)
        assertFalse(pressed())
        assertNear(128, centre())
        assertNear(128, edge())
        assertEquals("the tap opens the card", 1, opened)
    }

    @Test
    fun aQuickTapShowsNoPress() {
        row()
        compose.mainClock.autoAdvance = false
        hold()
        compose.mainClock.advanceTimeBy(32)
        lift()
        repeat(10) {
            compose.mainClock.advanceTimeByFrame()
            assertNear(128, centre())
        }
        assertEquals(emptyList<Interaction>(), interactions)
        assertEquals(1, opened)
    }

    @Test
    fun aFingerMovedMoreThanSixPointsEndsThePress() {
        row()
        compose.mainClock.autoAdvance = false
        hold()
        compose.mainClock.advanceTimeBy(100)
        assertTrue(pressed())
        compose.onNodeWithTag(FRAME).performTouchInput { moveBy(Offset(0f, dp(5f))) }
        compose.mainClock.advanceTimeByFrame()
        assertTrue("five points still press", pressed())
        compose.onNodeWithTag(FRAME).performTouchInput { moveBy(Offset(0f, dp(2f))) }
        compose.mainClock.advanceTimeBy(500)
        assertFalse(pressed())
        assertNear(128, centre())
        lift()
    }

    @Test
    fun underReduceMotionThePressOnlyDarkens() {
        row(reduceMotion = true)
        compose.mainClock.autoAdvance = false
        hold()
        compose.mainClock.advanceTimeBy(100)
        assertTrue(pressed())
        assertNear(122, centre())
        assertNear(122, edge())
        lift()
    }

    /**
     * A right click opens the card's menu (`.contextMenu` with a pointer), and the row doesn't give under it: held past
     * [PRESS_DELAY], the secondary button tells no press, nor does its release.
     */
    @Test
    fun aRightClickPressesNothing() {
        row()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(FRAME).performMouseInput {
            moveTo(center)
            press(MouseButton.Secondary)
        }
        compose.mainClock.advanceTimeBy(PRESS_DELAY * 2)
        assertFalse(pressed())
        compose.onNodeWithTag(FRAME).performMouseInput { release(MouseButton.Secondary) }
        compose.mainClock.advanceTimeBy(500)
        assertEquals(emptyList<Interaction>(), interactions.filterIsInstance<PressInteraction>())
        assertNear(128, centre())
    }

    /** Opens the card on its action with a slow drag. */
    private fun open() {
        compose.onNodeWithTag(FRAME).performTouchInput {
            down(Offset(width - dp(40f), height / 2f))
            repeat(12) { moveBy(Offset(-dp(60f) / 12, 0f)) }
            advanceEventTime(200)
            up()
        }
        compose.waitForIdle()
        assertEquals(1, compose.onAllNodes(hasTestTag(ARCHIVE_ACTION) and hasClickAction()).fetchSemanticsNodes().size)
    }

    /** How white the action's symbol and word are drawn: the whitest pixel's weakest channel. */
    private fun whiteness(action: Rect): Int {
        val bitmap = drawing()
        var whitest = 0
        for (y in action.top.roundToInt() until action.bottom.roundToInt()) {
            for (x in action.left.roundToInt() until action.right.roundToInt()) {
                val pixel = bitmap.getPixel(x, y)
                whitest = maxOf(whitest, minOf(pixel shr 16 and 0xFF, pixel shr 8 and 0xFF, pixel and 0xFF))
            }
        }
        return whitest
    }

    @Test
    fun theOpenActionDimsWhilePressedAsAPlainButton() {
        row()
        open()
        compose.mainClock.autoAdvance = false
        val node = compose.onNode(hasTestTag(ARCHIVE_ACTION) and hasClickAction())
        val action = node.fetchSemanticsNode().boundsInRoot
        assertNear(255, whiteness(action))
        node.performTouchInput { down(center) }
        compose.mainClock.advanceTimeBy(400)
        // `.buttonStyle(.plain)`'s 0.7 while pressed, over the action's colour; the row's press alone darkens by 6.
        val pressed = whiteness(action)
        assertTrue("$pressed", pressed < 255 - 30)
        node.performTouchInput { cancel() }
    }

    @Test
    fun theActionsWordShrinksToSeventyPercentRatherThanBeCutOff() {
        // Polish's "Archiwizuj" at the largest size, the action's grown only to accessibility1's.
        row(title = "Archiwizuj", typeSize = DynamicTypeSize.Accessibility5)
        open()
        val word = words.last()
        assertEquals("Whole", word.layoutInput.text.length, word.getLineEnd(0))
        assertFalse(word.isLineEllipsized(0))
        assertFalse("Clipped: ${word.multiParagraph.width} in ${word.size.width}", word.hasVisualOverflow)
        val size = word.layoutInput.style.fontSize.value
        val full = PeekTypography(DynamicTypeSize.Accessibility1, compose.density).style(IosTextStyle.Caption2, FontWeight.SemiBold).fontSize.value
        assertTrue("$size of $full", size < full && size >= full * 0.7f - 0.01f)
    }

    /** Where it fits, the word stands at its size: English's "Archive", grown to accessibility1's. */
    @Test
    fun theActionsWordStandsAtItsSizeWhereItFits() {
        row(typeSize = DynamicTypeSize.Accessibility5)
        open()
        val word = words.last()
        assertFalse(word.isLineEllipsized(0))
        val full = PeekTypography(DynamicTypeSize.Accessibility1, compose.density).style(IosTextStyle.Caption2, FontWeight.SemiBold).fontSize.value
        assertEquals(full, word.layoutInput.style.fontSize.value, 0.001f)
    }

    @Test
    fun armingPlaysAMediumImpactOnceEachTimeTheDragPassesTheCommitPoint() {
        shadowOf(vibrator).setSupportedPrimitives(emptyList())
        row()
        val frame = compose.onNodeWithTag(FRAME)
        frame.performTouchInput { down(Offset(width - dp(40f), height / 2f)) }
        // Past half the card, back under it, past again, and back to rest before lifting.
        for (distance in listOf(-220f, 120f, -120f, 220f)) {
            frame.performTouchInput { repeat(10) { moveBy(Offset(dp(distance) / 10, 0f), delayMillis = 16) } }
            compose.waitForIdle()
        }
        frame.performTouchInput { advanceEventTime(300); up() }
        compose.waitForIdle()
        assertEquals(listOf(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.VIRTUAL_KEY), feeling.played)

        // At 0.9, where the phone plays primitives.
        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_CLICK))
        frame.performTouchInput {
            down(Offset(width - dp(40f), height / 2f))
            repeat(10) { moveBy(Offset(-dp(22f), 0f), delayMillis = 16) }
        }
        compose.waitForIdle()
        assertEquals(listOf(PrimitiveEffect(Composition.PRIMITIVE_CLICK, 0.9f, 0)), shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects)
        frame.performTouchInput { repeat(10) { moveBy(Offset(dp(22f), 0f), delayMillis = 16) }; advanceEventTime(300); up() }
        compose.waitForIdle()
    }

    @Test
    fun theOpenActionPlaysAMediumImpact() {
        shadowOf(vibrator).setSupportedPrimitives(emptyList())
        row()
        open()
        compose.onNode(hasTestTag(ARCHIVE_ACTION) and hasClickAction()).performTouchInput { click() }
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(listOf(HapticFeedbackConstants.VIRTUAL_KEY), feeling.played)
    }

    @Test
    fun talkBacksArchivePlaysAMediumImpactAtEightTenths() {
        shadowOf(vibrator).setSupportedPrimitives(listOf(Composition.PRIMITIVE_CLICK))
        row()
        val archive = compose.onNodeWithTag(CARD).fetchSemanticsNode().config.getOrNull(SemanticsActions.CustomActions).orEmpty().single()
        compose.runOnUiThread { archive.action() }
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(listOf(PrimitiveEffect(Composition.PRIMITIVE_CLICK, 0.8f, 0)), shadowOf(vibrator).primitiveSegmentsInPrimitiveEffects)
    }

    @Test
    fun aThrowPlaysOnlyTheArmingImpact() {
        shadowOf(vibrator).setSupportedPrimitives(emptyList())
        row()
        compose.onNodeWithTag(FRAME).performTouchInput {
            down(Offset(width - dp(40f), height / 2f))
            // The first move decides the drag; the rest carry the card past half the row.
            repeat(6) { moveBy(Offset(-dp(50f), 0f)) }
            up()
        }
        compose.mainClock.advanceTimeBy(2_000)
        assertEquals(listOf(HapticFeedbackConstants.VIRTUAL_KEY), feeling.played)
    }

    /** A view that keeps the touch feedback asked of it. */
    private class FeelingView(context: Context) : View(context) {
        val played = mutableListOf<Int>()

        override fun performHapticFeedback(feedbackConstant: Int): Boolean {
            played += feedbackConstant
            return true
        }
    }

    private companion object {
        const val FRAME = "frame"
        const val CARD = "card"
    }
}
