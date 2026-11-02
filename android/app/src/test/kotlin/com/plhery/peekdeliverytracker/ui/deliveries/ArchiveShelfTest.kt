package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Context
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** The archive shelf's tick (A-H06, `.sensoryFeedback(.selection, trigger: isExpanded)`): each toggle, not as it shows. */
@RunWith(AndroidJUnit4::class)
@Config(qualifiers = PHONE)
class ArchiveShelfTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun eachToggleTicksAndShowingDoesNot() {
        val feeling = FeelingView(ApplicationProvider.getApplicationContext<Context>())
        var expanded by mutableStateOf(false)
        compose.setContent {
            CompositionLocalProvider(LocalView provides feeling) {
                CardHost {
                    ArchiveShelf(DemoCards.archived(), expanded = expanded, onExpandedChange = { expanded = it }, onOpen = {})
                }
            }
        }
        compose.waitForIdle()
        assertEquals(emptyList<Int>(), feeling.played)
        val tick = hapticFeedbackConstant(SensoryFeedback.Selection)
        val header = compose.onNode(hasContentDescription("Archived", substring = true) and hasClickAction())
        header.performClick()
        compose.waitForIdle()
        assertEquals(listOf(tick), feeling.played)
        header.performClick()
        compose.waitForIdle()
        assertEquals(listOf(tick, tick), feeling.played)
    }

    /** A view that keeps the touch feedback asked of it. */
    private class FeelingView(context: Context) : View(context) {
        val played = mutableListOf<Int>()

        override fun performHapticFeedback(feedbackConstant: Int): Boolean {
            played += feedbackConstant
            return true
        }
    }
}
