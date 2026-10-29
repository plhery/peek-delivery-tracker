package com.plhery.peekdeliverytracker.ui.zoom

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The zoom's drag down with a page whose content scrolls: on a 390 × 844 dp phone, two pixels to the dp. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ZoomStackTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var top: String? by mutableStateOf(null)

    private fun show() {
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                val transition = rememberZoomTransition()
                val drawn = transition.drawn(top)
                ZoomStack(
                    transition = transition,
                    top = top,
                    onBack = { top = null },
                    root = {
                        Box(Modifier.fillMaxSize()) {
                            Box(Modifier.padding(start = 16.dp, top = 120.dp).size(358.dp, 120.dp).zoomSource(SOURCE, transition, 16.dp))
                        }
                    },
                    page = drawn?.let {
                        {
                            LazyColumn(Modifier.fillMaxSize().testTag(PAGE)) {
                                items(40) { row -> BasicText("Row $row", Modifier.height(80.dp)) }
                            }
                        }
                    },
                )
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread { top = SOURCE }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun width(): Float = compose.onNodeWithTag(PAGE).getBoundsInRoot().let { it.right - it.left }.value

    @Test
    fun aPageThatScrollsIsDraggedDownOnlyFromItsTop() {
        show()
        assertEquals(390f, width(), 0.5f)
        compose.onNodeWithTag(PAGE).performScrollToIndex(5)
        compose.waitForIdle()
        val root = compose.onRoot()

        // Away from its top, a drag down scrolls the content.
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 250f), delayMillis = 400)
        }
        compose.waitForIdle()
        assertEquals(390f, width(), 0.5f)
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1_000)

        // Once at its top, the rest of the drag takes the page.
        root.performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 1_000f), delayMillis = 400)
            moveBy(Offset(0f, 100f), delayMillis = 400)
        }
        compose.waitForIdle()
        assertEquals(390f * (1f - 100f / (1.55f * 1688f)), width(), 0.5f)

        // Let go short of a quarter of the window, slowly, it springs back.
        root.performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(390f, width(), 0.5f)
        assertEquals(SOURCE, top)
    }

    @Test
    fun aPageAtItsTopDraggedFarGoesBackIntoItsSource() {
        show()
        compose.onRoot().performTouchInput {
            down(center)
            moveBy(Offset(0f, 50f), delayMillis = 100)
            moveBy(Offset(0f, 600f), delayMillis = 400)
            up()
        }
        compose.waitForIdle()
        assertEquals(null, top)
        compose.mainClock.advanceTimeBy(1_000)
        compose.onNodeWithTag(PAGE).assertDoesNotExist()
    }

    private companion object {
        const val SOURCE = "source"
        const val PAGE = "page"
    }
}
