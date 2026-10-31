package com.plhery.peekdeliverytracker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.pinnedIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The browser check's sheet over Add as a save asks for it: on a phone with the iPhone's bars, light and dark, and the
 * page sheet in the iPad's window with its bars, upright and on its side, light and dark; the page a local placeholder
 * of the check's (the service's page centres the widget in the top 70% of a canvas that follows the appearance). On a
 * synthetic number.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class TurnstileSheetScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val pages = FakePages(draw = ::PlaceholderPage)
    private val sheet = TurnstileSheet(pages)
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private lateinit var view: View

    /**
     * The demo's list with the iPhone's bars, or the iPad's, Add from the +, a number saved, and the check's sheet up
     * over it.
     */
    private fun show(dark: Boolean = false, iPad: Boolean = false) {
        val source = FakeDeliveries(DemoCards.parcels(AppLanguage.En), reader).apply {
            verification = sheet
            localizer = Localizer(AppLanguage.En, variant = "android")
        }
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            Box(Modifier.fillMaxSize().testTag(SHOT)) {
                RootHost(state = reader, dark = dark, source = source, identities = pinnedIdentities, browserCheck = sheet)
            }
        }
        compose.waitForIdle()
        if (iPad) bars(status = 32, bottom = 20) else bars(status = 62, bottom = 24)
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.waitForIdle()
        compose.onNodeWithTag(ADD_SAVE).performClick()
        settle()
        assertEquals(1, pages.made.single().loads)
    }

    /** The status bar `status` points tall and the bottom bar `bottom`: the iPhone's 62 and 24, the iPad's 32 and 20. */
    private fun bars(status: Int, bottom: Int) {
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(status), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    @Test
    fun overAddOnAPhone() {
        show()
        compose.screenshot("verification_phone_light")
    }

    @Test
    fun overAddOnAPhoneInDark() {
        show(dark = true)
        compose.screenshot("verification_phone_dark")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun overAddInARegularWindow() {
        show(iPad = true)
        compose.screenshot("verification_tablet_light")
    }

    @Test
    @Config(qualifiers = TABLET)
    fun overAddInARegularWindowInDark() {
        show(dark = true, iPad = true)
        compose.screenshot("verification_tablet_dark")
    }

    @Test
    @Config(qualifiers = TABLET_LANDSCAPE)
    fun overAddInARegularWindowOnItsSide() {
        show(iPad = true)
        compose.screenshot("verification_tablet_landscape_light")
    }

    @Test
    @Config(qualifiers = TABLET_LANDSCAPE)
    fun overAddInARegularWindowOnItsSideInDark() {
        show(dark = true, iPad = true)
        compose.screenshot("verification_tablet_landscape_dark")
    }
}

/** The iPad's window, 1032 × 1376 points upright, at twice the pixels. */
private const val TABLET = "w1032dp-h1376dp-xhdpi"
private const val TABLET_LANDSCAPE = "w1376dp-h1032dp-land-xhdpi"

/**
 * The check's page as it stands while its widget asks for a tap, drawn locally: the canvas, white or the dark one, and
 * a 300 × 65 widget centred in its top 70%, the page's 70vh in a WebView laid out under the bar.
 */
private class PlaceholderPage(context: Context, private val dark: Boolean) : View(context) {
    private val scale = context.resources.displayMetrics.density
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG)
    private val stroke = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = scale
    }
    private val label = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 14 * scale
        typeface = Typeface.DEFAULT
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(if (dark) 0xFF121212.toInt() else 0xFFFFFFFF.toInt())
        val widget = RectF(0f, 0f, 300 * scale, 65 * scale).apply {
            offset((width - width()) / 2, (height * 0.7f - height()) / 2)
        }
        fill.color = if (dark) 0xFF232323.toInt() else 0xFFFAFAFA.toInt()
        stroke.color = if (dark) 0xFF4A4A4A.toInt() else 0xFFE0E0E0.toInt()
        canvas.drawRect(widget, fill)
        canvas.drawRect(widget, stroke)
        val box = RectF(0f, 0f, 24 * scale, 24 * scale).apply { offset(widget.left + 16 * scale, widget.centerY() - height() / 2) }
        stroke.color = if (dark) 0xFF9E9E9E.toInt() else 0xFF6B6B6B.toInt()
        stroke.strokeWidth = 2 * scale
        fill.color = if (dark) 0xFF121212.toInt() else 0xFFFFFFFF.toInt()
        canvas.drawRoundRect(box, 2 * scale, 2 * scale, fill)
        canvas.drawRoundRect(box, 2 * scale, 2 * scale, stroke)
        stroke.strokeWidth = scale
        label.color = if (dark) 0xFFE8E8E8.toInt() else 0xFF232323.toInt()
        canvas.drawText("Verify you are human", box.right + 12 * scale, widget.centerY() - (label.ascent() + label.descent()) / 2, label)
    }
}
