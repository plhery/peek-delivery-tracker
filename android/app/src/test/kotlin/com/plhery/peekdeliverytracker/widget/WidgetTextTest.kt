package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.graphics.Typeface
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.ui.unit.dp
import androidx.glance.text.FontWeight
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The widget's text (C-W10) as WidgetKit sets it: iOS's text styles at the phone's text size, within the categories a
 * widget is drawn at, drawn as that many dp whatever Android's font scaling makes of sp; and `minimumScaleFactor`.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w390dp-h844dp-xhdpi")
class WidgetTextTest {
    private val app = ApplicationProvider.getApplicationContext<Context>()

    /** The pixels a launcher's text view draws `sp` at. */
    private fun pixels(type: WidgetText, points: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, type.sp(points).value, app.resources.displayMetrics)

    @Test
    fun defaultTextIsTheIPhonesDefault() {
        val type = WidgetText(app)
        assertEquals(DynamicTypeSize.Large, type.category)
        assertEquals(17f, type.points(IosTextStyle.Headline))
        assertEquals(11f, type.points(IosTextStyle.Caption2))
        assertEquals(17f * 2, pixels(type, 17f), 0.01f)
    }

    /** Android's largest text is WidgetKit's largest, xxxLarge, in dp, not Android's own scaling of sp. */
    @Test
    @Config(fontScale = 2.0f)
    fun largestTextStopsAtWidgetKitsLargest() {
        val type = WidgetText(app)
        assertEquals(DynamicTypeSize.XXXLarge, type.category)
        assertEquals(23f, type.points(IosTextStyle.Headline))
        assertEquals(26f, type.points(IosTextStyle.Title3))
        assertEquals(23f * 2, pixels(type, 23f), 0.01f)
    }

    @Test
    @Config(fontScale = 1.15f)
    fun largerTextIsTheNearestCategory() {
        val type = WidgetText(app)
        assertEquals(DynamicTypeSize.XLarge, type.category)
        assertEquals(19f * 2, pixels(type, type.points(IosTextStyle.Headline)), 0.01f)
    }

    /** Smaller text than the default is the default, as a widget's is. */
    @Test
    @Config(fontScale = 0.85f)
    fun smallerTextIsTheDefault() = assertEquals(DynamicTypeSize.Large, WidgetText(app).category)

    /**
     * `minimumScaleFactor`: a line that fits is left as it is; one that doesn't shrinks in steps until it fits, as
     * "Received by carrier · tomorrow" does on half a medium widget's card; past the factor, it ends in an ellipsis.
     */
    @Test
    fun lineTooLongShrinksBeforeItEnds() {
        val type = WidgetText(app)
        val status = "Received by carrier · tomorrow"
        val density = app.resources.displayMetrics.density
        val paint = TextPaint().apply { typeface = Typeface.create(Typeface.DEFAULT, 400, false) }
        fun width(points: Float) = paint.apply { textSize = points * density }.measureText(status) / density

        assertEquals(11f, type.fit(status, 11f, FontWeight.Normal, (width(11f) + 2).dp, 1, 0.8f))
        val room = (width(11f * 0.9f) + 1).dp
        val fitted = type.fit(status, 11f, FontWeight.Normal, room, 1, 0.8f)
        assertTrue("$fitted", fitted < 11f && fitted >= 11f * 0.88f)
        assertEquals(1, type.lines(status, fitted, FontWeight.Normal, room))
        assertEquals(2, type.lines(status, 11f, FontWeight.Normal, room))
        assertEquals(11f * 0.8f, type.fit(status, 11f, FontWeight.Normal, (width(11f * 0.7f)).dp, 1, 0.8f), 0.001f)

        // On half a medium widget's card, the status line fits once shrunk.
        val half = ((IPHONE_MEDIUM.width.value - 32 - 8) / 2 - 20).dp
        assertEquals(1, type.lines(status, type.fit(status, 11f, FontWeight.Normal, half, 1, 0.8f), FontWeight.Normal, half))
    }
}
