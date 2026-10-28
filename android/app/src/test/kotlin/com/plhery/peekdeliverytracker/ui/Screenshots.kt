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

/** No pixel may differ beyond Roborazzi's colour tolerance from the baseline. */
val screenshotOptions = RoborazziOptions(compareOptions = RoborazziOptions.CompareOptions(changeThreshold = 0f))

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
