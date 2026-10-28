package com.plhery.peekdeliverytracker.ui.theme

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.OverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.core.view.children
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** What PeekTheme gives Material and Compose's own components: the app's colours, no ripple, the iPhone's bounce. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekThemeTest {
    @get:Rule
    val compose = createComposeRule()

    /** Every colour role of a scheme by name, read through its getters (`getPrimary-0d7_KjU`, a packed colour). */
    private fun roles(scheme: ColorScheme): Map<String, Color> = ColorScheme::class.java.methods
        .filter { it.parameterCount == 0 && it.returnType == Long::class.javaPrimitiveType && it.name.startsWith("get") }
        .associate { it.name.removePrefix("get").substringBefore('-') to Color((it.invoke(scheme) as Long).toULong()) }

    @Test
    fun everyMaterialRoleIsOneOfTheAppsColours() {
        for (dark in listOf(false, true)) {
            for (highContrast in listOf(false, true)) {
                val colors = PeekColors(dark, highContrast)
                val ours = with(colors) {
                    setOf(accent, onAccent, ink, inkSoft, cream, paper, warning, warningSoft, background, separator, SCRIM)
                }
                val roles = roles(materialColors(colors))
                val baseline = roles(if (dark) darkColorScheme() else lightColorScheme())
                assertEquals("Material 3 1.4's 48 colour roles", 48, roles.size)
                assertEquals(baseline.keys, roles.keys)
                for ((role, color) in roles) {
                    val label = "$role, dark $dark, high contrast $highContrast"
                    assertTrue("$label is $color", color in ours)
                    // Only the baseline's white roles may happen to match: paper is white in light.
                    assertTrue("$label is Material's own", color != baseline[role] || color == Color.White)
                }
            }
        }
        val light = roles(materialColors(PeekColors(dark = false)))
        assertEquals(PeekColors(dark = false).accent, light["Primary"])
        assertEquals(PeekColors(dark = false).separator, light["OutlineVariant"])
        assertEquals(Color.Black.copy(alpha = 0.4f), light["Scrim"])
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun insideTheThemeTextIsTheLabelAndSelectionTheTint() {
        var content: Color? = null
        var selection: TextSelectionColors? = null
        var indication: Indication? = null
        var ripple: RippleConfiguration? = RippleConfiguration()
        var overscroll: OverscrollFactory? = null
        var scheme: ColorScheme? = null
        compose.setContent {
            PeekTheme(appearance = AppAppearance.Dark, highContrast = false, carrierIdentities = null) {
                content = LocalContentColor.current
                selection = LocalTextSelectionColors.current
                indication = LocalIndication.current
                ripple = LocalRippleConfiguration.current
                overscroll = LocalOverscrollFactory.current
                scheme = MaterialTheme.colorScheme
            }
        }
        compose.waitForIdle()
        val colors = PeekColors(dark = true)
        assertEquals(colors.label, content)
        assertEquals(TextSelectionColors(handleColor = colors.ink, backgroundColor = colors.ink.copy(alpha = 0.2f)), selection)
        assertSame(NoIndication, indication)
        assertNull(ripple)
        assertSame(RubberBandOverscrollFactory, overscroll)
        assertEquals(colors.paper, scheme!!.surface)
    }

    /** The centre pixel of the node tagged `tag`, as 0…255 channels. */
    private fun centre(tag: String): List<Int> {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread { view.rootView.invalidate() }
        compose.waitForIdle()
        val bitmap = compose.onNodeWithTag(tag).captureToImage().asAndroidBitmap()
        val pixel = bitmap.getPixel(bitmap.width / 2, bitmap.height / 2)
        return listOf(pixel shr 16 and 0xFF, pixel shr 8 and 0xFF, pixel and 0xFF)
    }

    /**
     * The views Material's ripples draw in. Compose draws a ripple with the platform's RippleDrawable in a view it adds
     * to the window at the first press (material-ripple's RippleContainer); Robolectric doesn't paint those, so a
     * ripple shows here as that view.
     */
    private fun rippleViews(): Int {
        val root = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view.rootView
        fun count(view: View): Int = (if (view.javaClass.simpleName == "RippleContainer") 1 else 0) +
            ((view as? ViewGroup)?.children?.sumOf { count(it) } ?: 0)
        return count(root)
    }

    /** Holds the node tagged `tag` down for 400 ms, then lets go. */
    private fun press(tag: String, whileDown: () -> Unit = {}) {
        compose.onNodeWithTag(tag).performTouchInput { down(center) }
        compose.mainClock.advanceTimeBy(400)
        whileDown()
        compose.onNodeWithTag(tag).performTouchInput { up() }
        compose.mainClock.advanceTimeBy(1000)
    }

    @Test
    fun aPressShowsNoRippleOnMaterialsOrComposesOwn() {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = false, carrierIdentities = null) {
                Box(Modifier.background(Color.White).padding(20.dp)) {
                    Box(Modifier.testTag("plain").size(80.dp).clickable {})
                }
                Box(Modifier.padding(top = 140.dp).background(Color.White).padding(20.dp)) {
                    Surface(onClick = {}, modifier = Modifier.testTag("material").size(80.dp), color = Color.White) {}
                }
            }
        }
        compose.mainClock.advanceTimeBy(100)
        val white = listOf(255, 255, 255)
        for (tag in listOf("plain", "material")) {
            assertEquals(tag, white, centre(tag))
            press(tag) { assertEquals(tag, white, centre(tag)) }
        }
        assertEquals(0, rippleViews())
    }

    /** What the test above would see of a ripple: Material's own theme ripples. */
    @Test
    fun materialsOwnThemeRipples() {
        compose.mainClock.autoAdvance = false
        compose.setContent {
            MaterialTheme {
                Surface(onClick = {}, modifier = Modifier.testTag("ripple").size(80.dp), color = Color.White) {}
            }
        }
        compose.mainClock.advanceTimeBy(100)
        press("ripple")
        assertEquals(1, rippleViews())
    }
}
