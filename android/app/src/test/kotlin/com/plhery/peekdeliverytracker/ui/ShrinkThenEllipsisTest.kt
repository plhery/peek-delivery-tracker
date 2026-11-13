package com.plhery.peekdeliverytracker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.text.MultiParagraphIntrinsics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.ui.arrival.LocalSignInPlaceholder
import com.plhery.peekdeliverytracker.ui.arrival.SignInTextField
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FirstOpenPing
import com.plhery.peekdeliverytracker.ui.deliveries.LocalFirstOpenPing
import com.plhery.peekdeliverytracker.ui.deliveries.QuietButton
import com.plhery.peekdeliverytracker.ui.deliveries.ShrinkingText
import com.plhery.peekdeliverytracker.ui.settings.SettingsRow
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Words kept to their lines by `.lineLimit(n).minimumScaleFactor(x)` on the iPhone: whole where they fit, shrunk to x
 * where they don't, and ending in "…" past that, as SwiftUI truncates them, rather than cut at the edge.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ShrinkThenEllipsisTest {
    @get:Rule
    val compose = createComposeRule()

    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private var width by mutableStateOf(360.dp)
    private var probed: TextLayoutResult? = null

    /** [content] [width] wide, its type at [fontScale], telling the probes of the ping and the placeholder. */
    private fun show(fontScale: Float = 1f, content: @Composable () -> Unit) {
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale),
                LocalFirstOpenPing provides { probed = it },
                LocalSignInPlaceholder provides { probed = it },
            ) {
                ProvideLocalizer(localizer) {
                    PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = DemoCards.identities) {
                        Box(Modifier.width(width)) { content() }
                    }
                }
            }
        }
        compose.waitForIdle()
    }

    private fun resize(to: Dp) {
        compose.runOnIdle { width = to }
        compose.waitForIdle()
    }

    /** The layout of the words [text] as their semantics tell it. */
    private fun layout(text: String): TextLayoutResult {
        val layouts = mutableListOf<TextLayoutResult>()
        compose.onNode(hasText(text), useUnmergedTree = true).fetchSemanticsNode()
            .config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        return layouts.single()
    }

    /** Whole and at its size with room, then at [scale] of that size and its [lines] ending in "…" when [narrow] wide. */
    private fun assertShrinksThenEllipsizes(scale: Float, narrow: Dp, lines: Int = 1, layout: () -> TextLayoutResult) {
        val wide = layout()
        assertFalse("Whole with room: ${wide.layoutInput.text}", wide.isLineEllipsized(0))
        assertFalse(wide.hasVisualOverflow)
        assertLaidOutAt(wide.layoutInput.style.fontSize, wide)
        resize(narrow)
        val cut = layout()
        assertEquals(wide.layoutInput.text.text, cut.layoutInput.text.text)
        assertEquals(lines, cut.lineCount)
        assertLaidOutAt(wide.layoutInput.style.fontSize * scale, cut)
        assertTrue("Ends in an ellipsis: ${cut.layoutInput.text}", cut.isLineEllipsized(lines - 1))
    }

    /** The list's quiet buttons side by side: their words shrink to 80 %, then end in "…" (FirstOpenView.quiet). */
    @Test
    fun theQuietButtonsWordsShrinkThenEndInAnEllipsis() {
        val title = localizer.text("app.addParcel")
        show { QuietButton(title, "keyboard", wraps = false, tag = "quiet", focus = AccessibilityFocus()) {} }
        assertShrinksThenEllipsizes(0.8f, 60.dp) { layout(title) }
    }

    /** At the accessibility sizes a value of one word under its title shrinks to half, then ends in "…" (SettingsRow). */
    @Test
    fun aSettingsValueOfOneWordShrinksThenEndsInAnEllipsis() {
        show(fontScale = 2f) { SettingsRow("Email", value = "reader@example.com") }
        assertShrinksThenEllipsizes(0.5f, 80.dp) { layout("reader@example.com") }
    }

    /** The address's placeholder shrinks to a quarter where the field is too narrow, then ends in "…" (UITextField). */
    @Test
    fun thePlaceholderShrinksThenEndsInAnEllipsis() {
        show {
            SignInTextField(
                value = "", onValueChange = {}, label = "Email", tag = "field", modifier = Modifier,
                placeholder = "you@example.com", keyboard = KeyboardOptions.Default, autofill = ContentType.EmailAddress,
                style = PeekTheme.type.body.sfLines(),
            )
        }
        assertShrinksThenEllipsizes(0.25f, 24.dp) { checkNotNull(probed) }
    }

    /** At the accessibility sizes Add's button keeps to two lines, shrinking to 70 %, then ends in "…" (AddParcelView). */
    @Test
    fun addsButtonShrinksOnItsTwoLinesThenEndsInAnEllipsis() {
        val title = localizer.text("app.addParcel")
        show(fontScale = 2f) { ShrinkingText(title, PeekTheme.type.headline.sfLines(), PeekTheme.colors.onAccent, twoLines = true) }
        assertShrinksThenEllipsizes(0.7f, 60.dp, lines = 2) { layout(title) }
    }

    /** The welcome's alert over the picture shrinks to 80 %, then ends in "…" (FirstOpenPing). */
    @Test
    fun theWelcomeAlertShrinksThenEndsInAnEllipsis() {
        val identity = DemoCards.identities.of(CarrierId.Dhl, AppLanguage.En)
        show { FirstOpenPing("Arriving today, 10:00 to 12:00", identity) }
        assertShrinksThenEllipsizes(0.8f, 120.dp) { checkNotNull(probed) }
    }
}

/**
 * That [layout] was laid out at [fontSize]: its words whole are as wide as they are in its style at that size. A text's
 * semantics give its layout with the style it was given, not the size
 * [MinimumScale][com.plhery.peekdeliverytracker.ui.theme.MinimumScale] chose, so the size shows in the width.
 */
internal fun assertLaidOutAt(fontSize: TextUnit, layout: TextLayoutResult) {
    val input = layout.layoutInput
    val sized = MultiParagraphIntrinsics(
        input.text, input.style.copy(fontSize = fontSize), input.placeholders, input.density, input.fontFamilyResolver, softWrap = false,
    )
    assertEquals("Laid out at $fontSize", sized.maxIntrinsicWidth, layout.multiParagraph.intrinsics.maxIntrinsicWidth, 0.5f)
}
