package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_JOURNAL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * B-ST15: a parcel's page in each of the app's languages, in light and dark (ParcelDetailView's copy, its `\.locale`
 * the Localizer's): no copy key on it is left unread, and its ••• reads "detail.parcelActions" in that language. The
 * demo's Birthday gift in that language, signed in as the synthetic reader@example.com on fake deliveries
 * ([ParcelWalk]); over the list on a phone, beside it on a tablet or an unfolded window. Each saved as
 * pagelanguages/B-ST15-<language>.png at the page's top and -journal at its journey, each with -dark, in the test
 * package's external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class PageLanguagesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    @Test
    fun inEnglish() = walkThePage(AppLanguage.En)

    @Test
    fun inGerman() = walkThePage(AppLanguage.De)

    @Test
    fun inFrench() = walkThePage(AppLanguage.Fr)

    @Test
    fun inItalian() = walkThePage(AppLanguage.It)

    @Test
    fun inSpanish() = walkThePage(AppLanguage.Es)

    @Test
    fun inPortuguese() = walkThePage(AppLanguage.Pt)

    @Test
    fun inBrazilianPortuguese() = walkThePage(AppLanguage.PtBr)

    @Test
    fun inPolish() = walkThePage(AppLanguage.Pl)

    @Test
    fun inRussian() = walkThePage(AppLanguage.Ru)

    @Test
    fun inChinese() = walkThePage(AppLanguage.Zh)

    private fun walkThePage(language: AppLanguage) {
        walk.language = language
        walk.show(DemoCards.parcels(language))
        walk.open(DemoCards.parcel("Birthday gift", language))
        assertEquals("Copy keys shown unread in ${language.rawValue}", emptyList<String>(), unreadKeys())
        assertEquals(listOf(walk.text("detail.parcelActions")), description(PAGE_MENU_BUTTON))
        walk.shot("B-ST15-${language.rawValue}")
        compose.reveal(PAGE_JOURNAL)
        assertEquals("Copy keys shown unread at the journey in ${language.rawValue}", emptyList<String>(), unreadKeys())
        walk.shot("B-ST15-${language.rawValue}-journal")
    }

    /** The page's words that are copy keys the language has words for: a key drawn as it is instead of its words. */
    private fun unreadKeys(): List<String> = pageWords().filter { KEY.matches(it) && walk.text(it) != it }

    /** Every word the page draws or reads out: its texts and its descriptions. */
    private fun pageWords(): List<String> = compose.onAllNodes(hasAnyAncestor(hasTestTag(PARCEL_PAGE)), useUnmergedTree = true)
        .fetchSemanticsNodes()
        .flatMap { node ->
            node.config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text } +
                node.config.getOrNull(SemanticsProperties.ContentDescription).orEmpty()
        }

    private fun description(tag: String): List<String> =
        compose.onNode(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription).orEmpty()

    private companion object {
        const val FOLDER = "pagelanguages"

        /** A copy key as the app's copy names them: dotted words, such as "detail.parcelActions". */
        val KEY = Regex("^[a-z][a-zA-Z0-9]*(\\.[a-zA-Z0-9-]+)+$")
    }
}
