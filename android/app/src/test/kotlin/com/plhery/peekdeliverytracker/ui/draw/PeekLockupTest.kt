package com.plhery.peekdeliverytracker.ui.draw

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.ProvideLocalizer
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The lockup's lines (C-S03): whole with room enough, shrunk towards 70 % where that fits them, and ending in "…" where
 * even that doesn't, as `.lineLimit(1).minimumScaleFactor(0.7)` truncates.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekLockupTest {
    @get:Rule
    val compose = createComposeRule()

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private var width by mutableStateOf(300.dp)

    private fun show() {
        compose.setContent {
            ProvideLocalizer(localizer) {
                PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                    Box(Modifier.width(width)) { PeekLockup() }
                }
            }
        }
    }

    /** The lockup [lockup] wide: its lines take what the mark (28) and the gap (9) leave. */
    private fun resize(lockup: Dp) {
        compose.runOnIdle { width = lockup }
        compose.waitForIdle()
    }

    private fun layout(key: String): TextLayoutResult {
        val layouts = mutableListOf<TextLayoutResult>()
        compose.onNode(hasText(localizer.text(key)), useUnmergedTree = true).fetchSemanticsNode()
            .config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        return layouts.single()
    }

    @Test
    fun theNameShrinksBeforeItIsCutAndEndsInAnEllipsisPastSeventyPercent() {
        show()
        assertFalse(layout("app.title").isLineEllipsized(0))
        assertFalse(layout("native.tagline").isLineEllipsized(0))
        val name = layout("app.title").size.width / compose.density.density
        // 85 % of its width: shrunk, whole.
        resize(37.dp + (name * 0.85f).dp)
        assertFalse(layout("app.title").isLineEllipsized(0))
        // Half of it: past 70 %, cut with "…" rather than clipped.
        resize(37.dp + (name * 0.5f).dp)
        assertTrue(layout("app.title").isLineEllipsized(0))
        assertTrue(layout("native.tagline").isLineEllipsized(0))
    }
}
