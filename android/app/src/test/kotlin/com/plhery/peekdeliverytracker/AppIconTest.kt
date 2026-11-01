package com.plhery.peekdeliverytracker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AdaptiveColor
import com.plhery.peekdeliverytracker.core.PeekMarkArtwork
import com.plhery.peekdeliverytracker.core.RgbaColor
import com.plhery.peekdeliverytracker.ui.draw.drawMapDrawings
import com.plhery.peekdeliverytracker.ui.theme.toColor
import java.io.File
import javax.imageio.ImageIO
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.w3c.dom.Element
import androidx.compose.ui.graphics.Canvas as ComposeCanvas

/**
 * The app's icon (X-AD14), the iPhone's AppIcon.png: an adaptive icon, the mark's eyes on its yellow, the eyes as large
 * and in the same place under the launcher's mask as under iOS's, and the eyes alone for themed icons. The share sheet
 * shows it beside "Add to Peek" (ShareActivityTest).
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class AppIconTest {
    private val app = ApplicationProvider.getApplicationContext<Context>()

    private fun icon() = app.getDrawable(app.applicationInfo.icon) as AdaptiveIconDrawable

    @Test
    @Config(sdk = [31, 33])
    fun itIsAdaptiveTheEyesOnTheMarksYellowAndTheEyesAloneForThemedIcons() {
        assertEquals(R.mipmap.ic_launcher, app.applicationInfo.icon)
        val icon = icon()
        assertEquals(app.getColor(R.color.mark_yellow), (icon.background as ColorDrawable).color)
        assertTrue("${icon.foreground}", icon.foreground is VectorDrawable)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            assertTrue("${icon.monochrome}", icon.monochrome is VectorDrawable)
        }
    }

    /** ApplicationInfo keeps the round icon out of its public fields, so the manifest is read as written. */
    @Test
    fun roundLaunchersShowTheSameIcon() {
        val manifest = File(System.getProperty("user.dir")).resolve("src/main/AndroidManifest.xml")
        val application = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }.newDocumentBuilder()
            .parse(manifest).getElementsByTagName("application").item(0) as Element
        fun attribute(name: String) = application.getAttributeNS("http://schemas.android.com/apk/res/android", name)
        assertEquals("@mipmap/ic_launcher", attribute("icon"))
        assertEquals("@mipmap/ic_launcher", attribute("roundIcon"))
    }

    /** The resource colours the icon is drawn in are the mark's: the tile's, a white's, a pupil's and a catchlight's. */
    @Test
    fun itsColoursAreTheMarks() {
        val layers = PeekMarkArtwork.layers
        val colors = listOf(R.color.mark_yellow, R.color.mark_paper, R.color.mark_ink, android.R.color.white)
        for ((color, layer) in colors.zip(listOf(layers[0], layers[1], layers[3], layers[4]))) {
            assertEquals(app.resources.getResourceEntryName(color), layer.color.toColor().toArgb(), app.getColor(color))
        }
    }

    /**
     * What the launcher's mask shows of the icon, the middle 72 of its 108 dp, drawn 1024 pixels wide is AppIcon.png:
     * the same yellow, the same eyes in the same place, to a fraction of a pixel ([assertAlike]).
     */
    @Test
    fun whatTheMaskShowsIsTheIPhonesIcon() {
        val side = 1024
        val icon = icon()
        val drawn = draw(listOf(icon.background, icon.foreground), side * 108 / 72, inset = side / 4, side = side)
        val iPhone = ImageIO.read(
            File(System.getProperty("user.dir"))
                .resolve("../../ios/PeekDeliveryTracker/Resources/Assets.xcassets/AppIcon.appiconset/AppIcon.png"),
        )
        assertEquals(side, iPhone.width)
        val expected = iPhone.getRGB(0, 0, side, side, null, 0, side)
        // The smallest step at an edge of the icon's: from the paper to the yellow, 168 in blue.
        assertAlike(drawn.indices.map { difference(drawn[it], expected[it]) }, step = 168)
    }

    /**
     * Themed icons tint `PeekMarkArtwork.drawEyes`, the Mac menu bar's template image: the whites, the pupils cut out
     * around their catchlights, in the foreground's place ([assertAlike], in opacity). The canvas drawn 768 pixels wide
     * puts the frame's units on pixels, 128 in.
     */
    @Test
    @Config(sdk = [33])
    fun themedIconsTintTheEyesThePupilsCutOutAroundTheirCatchlights() {
        val side = 768
        val monochrome = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) icon().monochrome else null
        val drawn = draw(listOf(monochrome!!), side, inset = 0, side = side)
        val image = ImageBitmap(side, side)
        CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, ComposeCanvas(image), Size(side.toFloat(), side.toFloat())) {
            translate(128f, 128f) { drawMapDrawings(PeekMarkArtwork.drawEyes(AdaptiveColor(RgbaColor(1.0, 1.0, 1.0))), dark = false) }
        }
        val eyes = IntArray(side * side).also { image.asAndroidBitmap().getPixels(it, 0, side, 0, 0, side, side) }

        fun alpha(x: Int, y: Int) = drawn[(y + 128) * side + x + 128] ushr 24
        // Outside, then the white, the pupil and the catchlight of each eye, in the frame's units.
        assertEquals(0, alpha(256, 100))
        for (eye in listOf(0, 132)) {
            assertEquals(255, alpha(150 + eye, 262))
            assertEquals(0, alpha(225 + eye, 285))
            assertEquals(255, alpha(207 + eye, 255))
        }
        // The left pupil is cut out where the right eye's white reaches over it.
        assertEquals(0, alpha(253, 269))
        assertAlike(drawn.indices.map { abs((drawn[it] ushr 24) - (eyes[it] ushr 24)) }, step = 255)
    }

    /** [layers] drawn on a canvas `canvasSide` pixels wide, of which the `side` pixels `inset` in from its corner. */
    private fun draw(layers: List<Drawable>, canvasSide: Int, inset: Int, side: Int): IntArray {
        val bitmap = Bitmap.createBitmap(side, side, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.translate(-inset.toFloat(), -inset.toFloat())
        for (layer in layers) {
            layer.setBounds(0, 0, canvasSide, canvasSide)
            layer.draw(canvas)
        }
        return IntArray(side * side).also { bitmap.getPixels(it, 0, side, 0, 0, side, side) }
    }

    /**
     * Pixel by pixel [differences] as small as edges drawn in the same place give, where `step` is the least that two
     * sides of an edge differ by. Edges are anti-aliased, by Skia here and by Core Graphics in AppIcon.png, and each
     * renderer cuts curves into pieces its own way, so pixels on an edge differ a little: none by half the step, and
     * fewer than [EDGE_PIXELS] by more than [EDGE]. An edge half a pixel out moves its pixels by half the step, and an
     * edge a pixel out moves some 3,200 of AppIcon.png's pixels by more than [EDGE].
     */
    private fun assertAlike(differences: List<Int>, step: Int) {
        val apart = differences.count { it > EDGE }
        val most = differences.max()
        assertTrue("$apart pixels differ by more than $EDGE, at most by $most", apart < EDGE_PIXELS && most < step / 2)
    }

    /** The most two opaque colours differ by in a channel. */
    private fun difference(a: Int, b: Int) = (0 until 24 step 8).maxOf { abs((a shr it and 0xFF) - (b shr it and 0xFF)) }

    private companion object {
        /** An eighth of the way from black to white in a channel. */
        const val EDGE = 32

        /** A fifth of the pixels an edge a pixel out moves. */
        const val EDGE_PIXELS = 640
    }
}
