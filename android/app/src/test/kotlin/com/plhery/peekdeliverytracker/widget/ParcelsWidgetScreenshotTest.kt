package com.plhery.peekdeliverytracker.widget

import android.content.Context
import android.widget.RemoteViews
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.DeliveryWidgetList
import com.plhery.peekdeliverytracker.core.DeliveryWidgetSnapshot
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.screenshot
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The "Parcels" widget as a launcher shows it, its RemoteViews applied on a wallpaper: its three sizes with the sample
 * (two cells by two and four by two side by side, four by four under them), beside nothing on its way and the widget
 * switched off; light and dark; the largest text; and the sizes between.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w600dp-h740dp-xhdpi")
class ParcelsWidgetScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    @get:Rule
    val zurich = ZurichTime()

    private val app = ApplicationProvider.getApplicationContext<Context>()

    private class Shown(val views: RemoteViews, val size: DpSize)

    private fun widget(size: DpSize, list: DeliveryWidgetList = sampleList()) = Shown(runBlocking { listViews(app, size, list) }, size)

    private fun none(enabled: Boolean = true) = DeliveryWidgetList.make(CAPTURED, enabled, "en", DeliveryWidgetSnapshot(CAPTURED, "en", emptyList()))

    /** Columns of widgets in rows, on a wallpaper of the phone's mode. */
    private fun shoot(name: String, dark: Boolean, rows: List<List<List<Shown>>>) {
        compose.setContent {
            Column(
                Modifier.testTag(SHOT).background(if (dark) Color(0xFF30343A) else Color(0xFFC9D3DD)).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                for (row in rows) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        for (column in row) {
                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) { for (shown in column) WidgetOnHome(shown.views, shown.size) }
                        }
                    }
                }
            }
        }
        compose.screenshot("widget_list_$name")
    }

    /** Small and medium, then large beside nothing on its way and the widget switched off. */
    private fun sizes(name: String, dark: Boolean) = shoot(
        name, dark,
        listOf(
            listOf(listOf(widget(LIST_SMALL)), listOf(widget(LIST_MEDIUM))),
            listOf(listOf(widget(LIST_LARGE)), listOf(widget(LIST_SMALL, none()), widget(LIST_SMALL, none(enabled = false)))),
        ),
    )

    @Test
    fun light() = sizes("light", dark = false)

    @Test
    @Config(qualifiers = "+night")
    fun dark() = sizes("dark", dark = true)

    /** At the phone's largest font scale (WidgetKit's largest for a widget): fewer rows, names shrunk to fit. */
    @Test
    @Config(fontScale = 2.0f)
    fun largestText() = sizes("largest_text", dark = false)

    /**
     * Between the three: three cells by two (one-line rows), two by three and two by four (two-line rows), four by three;
     * and the widget's first look until it has drawn (parcels_widget_preview).
     */
    @Test
    @Config(qualifiers = "w600dp-h1100dp-xhdpi")
    fun between() = shoot(
        "between", dark = false,
        listOf(
            listOf(listOf(widget(LIST_THREE_BY_TWO)), listOf(widget(LIST_TWO_BY_THREE))),
            listOf(listOf(widget(LIST_FOUR_BY_THREE)), listOf(widget(LIST_TWO_BY_FOUR))),
            listOf(listOf(Shown(RemoteViews(app.packageName, R.layout.parcels_widget_preview), LIST_MEDIUM))),
        ),
    )
}
