package com.plhery.peekdeliverytracker.ui

import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.captureRoboImage

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
 * The node tagged [SHOT], saved as src/test/snapshots/[name].png: recorded with recordRoborazziDebug, checked with
 * verifyRoborazziDebug. The window is drawn again first, as Robolectric may keep a drawing from before the content.
 */
fun ComposeContentTestRule.screenshot(name: String) {
    val view = (onRoot().fetchSemanticsNode().root as ViewRootForTest).view
    runOnUiThread {
        view.rootView.invalidate()
        view.invalidate()
    }
    waitForIdle()
    onNodeWithTag(SHOT).captureRoboImage("src/test/snapshots/$name.png", roborazziOptions = screenshotOptions)
}
