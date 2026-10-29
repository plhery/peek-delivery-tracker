package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.screenshot
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The archive under the deliveries as the iPhone's list draws it on its captures (A-S39, A-S40): closed, its header
 * alone; and open, the archived parcels' rows on one surface.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ArchiveShelfScreenshotTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    @Test
    fun closedLight() = shot("shelf_closed_light", expanded = false)

    @Test
    fun closedDark() = shot("shelf_closed_dark", expanded = false, dark = true)

    @Test
    fun openLight() = shot("shelf_open_light", expanded = true)

    @Test
    fun openDark() = shot("shelf_open_dark", expanded = true, dark = true)

    @Test
    fun openGerman() = shot("shelf_open_de_light", expanded = true, language = AppLanguage.De)

    /** The parcel shown beside the list, tinted with the ink. */
    @Test
    fun selectedLight() = shot("shelf_selected_light", expanded = true, selected = 1)

    @Test
    fun selectedDark() = shot("shelf_selected_dark", expanded = true, dark = true, selected = 1)

    /** At twice the text size, one of the accessibility sizes: no symbols, the words wrap and the day goes under them. */
    @Test
    fun largeText() = shot("shelf_open_light_text2", expanded = true, fontScale = 2f)

    @Test
    fun largeTextDark() = shot("shelf_open_dark_text2", expanded = true, dark = true, fontScale = 2f)

    private fun shot(
        name: String,
        expanded: Boolean,
        dark: Boolean = false,
        language: AppLanguage = AppLanguage.En,
        fontScale: Float = 1f,
        selected: Int? = null,
    ) {
        val parcels = DemoCards.archived(language)
        compose.setContent {
            CardHost(language, dark, fontScale) {
                ArchiveShelf(
                    parcels, expanded = expanded, onExpandedChange = {}, onOpen = {},
                    selected = selected?.let { parcels[it].id },
                )
            }
        }
        compose.screenshot(name)
    }
}
