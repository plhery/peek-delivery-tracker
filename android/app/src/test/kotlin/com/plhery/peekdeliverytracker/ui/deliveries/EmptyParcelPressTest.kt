package com.plhery.peekdeliverytracker.ui.deliveries

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.util.UUID
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The empty list's parcel under the finger (A-AN07, `UnwrappingParcel(open: 0, pressed:)`): it sinks to 0.956 of its
 * height toward the ground and 5 points lower (in Pip's frame) on a spring of response 0.3 s and damping 0.64, and at
 * once under Reduce Motion. Read from where its lid's top stands.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class EmptyParcelPressTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private fun show(reduceMotion: Boolean) {
        val session = AuthenticationState.SignedIn(AuthUser(UUID(0, 11)))
        compose.setContent { RootHost(state = session, source = FakeDeliveries(emptyList(), session), reduceMotion = reduceMotion) }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
    }

    /** The parcel's top, in pixels from the top of its button. */
    private fun top(): Float {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.waitForIdle()
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        val button = compose.onNodeWithTag(EMPTY_SAMPLE).fetchSemanticsNode().boundsInRoot
        val background = whole.getPixel(button.left.toInt() + 1, button.top.toInt() + 1)
        val x = button.center.x.toInt()
        val row = (button.top.toInt() until button.bottom.toInt()).first { y -> differs(whole.getPixel(x, y), background) }
        return row - button.top
    }

    private fun differs(pixel: Int, background: Int): Boolean =
        (0..2).any { channel -> abs((pixel shr channel * 8 and 0xFF) - (background shr channel * 8 and 0xFF)) > 24 }

    /** Where the top stands pressed: 0.956 of its height above the frame's bottom, then 5 of Pip's points lower. */
    private fun pressed(rest: Float): Float {
        val button = compose.onNodeWithTag(EMPTY_SAMPLE).fetchSemanticsNode().boundsInRoot
        // Pip's 300-point frame drawn across the button.
        val unit = button.width / 300f
        return button.height - (button.height - rest) * 0.956f + 5 * unit
    }

    /** Where the top stands at each frame for `milliseconds`. */
    private fun tops(milliseconds: Long): List<Float> = List((milliseconds / 16).toInt()) {
        compose.mainClock.advanceTimeByFrame()
        top()
    }

    @Test
    fun theParcelSinksOnItsSpringAndComesBack() {
        show(reduceMotion = false)
        val rest = top()
        val target = pressed(rest)
        assertTrue("$rest to $target", target - rest > 10)
        compose.onNodeWithTag(EMPTY_SAMPLE).performTouchInput { down(center) }
        val tops = tops(1_000)
        assertTrue("on its way: $tops", tops.count { it > rest + 1 && it < target - 1 } >= 2)
        assertTrue("a little past it and back: $tops", tops.max() > target + 0.5f)
        assertEquals(target, tops.last(), 1.5f)
        compose.onNodeWithTag(EMPTY_SAMPLE).performTouchInput { cancel() }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(rest, top(), 1f)
    }

    @Test
    fun underReduceMotionItSinksAtOnce() {
        show(reduceMotion = true)
        val rest = top()
        val target = pressed(rest)
        compose.onNodeWithTag(EMPTY_SAMPLE).performTouchInput { down(center) }
        val tops = tops(500)
        assertTrue("$tops", tops.all { abs(it - rest) <= 1 || abs(it - target) <= 1.5f })
        assertEquals(target, tops.last(), 1.5f)
        compose.onNodeWithTag(EMPTY_SAMPLE).performTouchInput { cancel() }
    }
}
