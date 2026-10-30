package com.plhery.peekdeliverytracker.ui

import android.os.Looper
import android.view.View
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.captureRoboImage
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowLooper

/** The iPhone's 390 × 844 points, at twice the pixels. */
const val PHONE = "w390dp-h844dp-xhdpi"

/** The tag of the part of the screen a screenshot keeps, so each PNG holds only what it shows. */
const val SHOT = "shot"

/**
 * At most a thousandth of a shot's pixels may differ from the baseline beyond Roborazzi's colour tolerance. The
 * baselines are recorded on a Mac with Apple silicon and checked on one in CI (macos-26), as Robolectric's native
 * graphics round anti-aliased edges and blurs differently on Linux x86-64. Even on a Mac a few edge pixels of a shot
 * can come out unlike the recording, so the margin stays. It hides nothing: a thousandth is less than any change the
 * shots check, as a one-pixel line across the tallest shot (844 px) is 1/844 of it.
 *
 * Robolectric's Roboto has no 600 or 800 weight: semibold text shows as Medium (500), a little lighter and narrower
 * than on a phone, and heavy as Black (900).
 */
val screenshotOptions = RoborazziOptions(compareOptions = RoborazziOptions.CompareOptions(changeThreshold = 0.001f))

/**
 * Waits until a frame of the window is on its surface. Robolectric's renderer may take a frame of the window and put
 * off drawing it until the next frame it draws, and its screenshots draw with the window's own renderer: a screenshot
 * taken in between draws in place of that frame and loses what the frame was to paint first, such as a map that has
 * just come into an offscreen layer, which stays blank until it is drawn again. On a device Compose's `captureToImage`
 * waits for a frame of its own; under Robolectric it doesn't.
 *
 * Two frames, one after the other: on a slow machine the first to be committed can be the one put off, drawn as the
 * window stood a frame before, so a screenshot after it shows the paper and Pip a frame early (CI's macOS runner did).
 * The second is drawn from the window as it stands.
 */
fun ComposeContentTestRule.awaitFrame() {
    val view = (onRoot().fetchSemanticsNode().root as ViewRootForTest).view
    val looper = Shadows.shadowOf(Looper.getMainLooper())
    repeat(2) { awaitCommit(view, looper) }
}

/** Waits until the window commits a frame asked for after this call. */
private fun ComposeContentTestRule.awaitCommit(view: View, looper: ShadowLooper) {
    val drawn = AtomicBoolean(false)
    repeat(10) {
        runOnUiThread {
            view.viewTreeObserver.registerFrameCommitCallback { drawn.set(true) }
            view.rootView.invalidate()
            view.invalidate()
        }
        // The next frame of the window, then a moment for the render thread to draw it or put it off.
        looper.idleFor(Duration.ofMillis(16))
        val until = System.nanoTime() + 100_000_000
        while (System.nanoTime() < until) {
            looper.idle()
            if (drawn.get()) return
            Thread.sleep(1)
        }
    }
    error("The window drew none of ten frames")
}

/**
 * The node tagged [SHOT], saved as src/test/snapshots/[name].png: recorded with recordRoborazziDebug, checked with
 * verifyRoborazziDebug. The window draws a frame first ([awaitFrame]), as Robolectric may keep a drawing from before
 * the content.
 */
fun ComposeContentTestRule.screenshot(name: String) {
    waitForIdle()
    awaitFrame()
    onNodeWithTag(SHOT).captureRoboImage("src/test/snapshots/$name.png", roborazziOptions = screenshotOptions)
}
