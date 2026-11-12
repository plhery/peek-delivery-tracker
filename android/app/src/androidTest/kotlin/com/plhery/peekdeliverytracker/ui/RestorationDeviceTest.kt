package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COLUMN
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * X-AD25 and X-AD33 on the device, the X-M rows' process death and change of language: the window made again from
 * what it saved, as [PeekRootTest] has it on the JVM, on this device's own window as it is. In a new process the
 * deliveries' stores start again ([FakeDeliveries.newProcess]) and the session is read again behind the launch
 * screen; the parcel chosen comes back once the parcels do, over the list where the window stacks them (the tab bar
 * hidden) and chosen beside it where it lays them side by side, counted as it opens again. The friends come back
 * chosen, counted as nothing. A window made again in another language, in the same process or a new one, keeps the
 * parcel open in that language's words. The demo's parcels on fake deliveries, whose stores answer every read in
 * memory: nothing goes to the network.
 */
@RunWith(AndroidJUnit4::class)
class RestorationDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose)

    // What the content is made with, read only as it is made: a window made again reads them anew, the one shown never.
    private var session: MutableState<AuthenticationState> = mutableStateOf(AuthenticationState.Loading)
    private var language = AppLanguage.En
    private var process = FakeDeliveries(DemoCards.parcels())
    private val views = mutableListOf<String>()
    private val restoration = StateRestorationTester(compose)

    private fun show() {
        restoration.setContent {
            RootHost(state = session.value, language = language, onView = { views += it }, source = remember { process })
        }
        session.value = AuthenticationState.Welcome
        compose.waitForIdle()
    }

    /** Whether this window lays the list and a parcel side by side. */
    private fun besideTheList(): Boolean = compose.onAllNodesWithTag(PAGE_COLUMN).fetchSemanticsNodes().isNotEmpty()

    /** The parcel named `name` chosen from its card in the list: opened over it, or shown beside it. */
    private fun choose(name: String) {
        val card = hasContentDescription(name, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(card)
        compose.onNode(card).performClick()
        compose.waitForIdle()
    }

    /** A new process: its session read again behind the launch screen before the tabs come back, counted to [views]. */
    private fun newProcess() {
        session = mutableStateOf(AuthenticationState.Loading)
        process = process.newProcess()
        restoration.emulateSavedInstanceStateRestore()
        compose.onNodeWithTag(LAUNCH).assertExists()
        views.clear()
        session.value = AuthenticationState.Welcome
        compose.waitForIdle()
    }

    private fun assertPageShows(name: String) {
        val named = compose.onAllNodes(hasText(name, substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)))
        assertTrue("The page shows $name", named.fetchSemanticsNodes().isNotEmpty())
    }

    /** The page as it comes back: over the list without the tab bar, or beside it with its card chosen. */
    private fun assertBack(name: String, beside: Boolean) {
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        assertPageShows(name)
        if (beside) {
            compose.onNode(hasContentDescription(name, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST)))
                .assertIsSelected()
        } else {
            compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
        }
    }

    private fun tab(label: String) = compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab))

    @Test
    fun theParcelChosenAndTheTabComeBackInANewProcess() {
        show()
        val beside = besideTheList()
        choose("Birthday gift")
        assertPageShows("Birthday gift")

        newProcess()
        assertEquals(listOf("parcel"), views)
        assertBack("Birthday gift", beside)

        // Over the list the page hides the tabs: back to the list first.
        if (!beside) {
            compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
            compose.waitForIdle()
        }
        views.clear()
        tab("Friends").performClick()
        compose.waitForIdle()
        assertEquals(listOf("friends"), views)

        newProcess()
        assertEquals(emptyList<String>(), views)
        tab("Friends").assertIsSelected()
        compose.onNodeWithTag("friends").assertExists()

        // The deliveries come back as they were left: the parcel still chosen beside the list, or the list itself.
        tab("Deliveries").performClick()
        compose.waitForIdle()
        if (beside) assertBack("Birthday gift", beside = true) else compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
    }

    @Test
    fun aWindowMadeAgainInAnotherLanguageKeepsTheParcelOpen() {
        show()
        val beside = besideTheList()
        choose("Birthday gift")
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assert(hasContentDescription(words(AppLanguage.En, "detail.parcelActions")))

        // The phone's language changed while the app runs: the same process, the window made again.
        language = AppLanguage.Fr
        restoration.emulateSavedInstanceStateRestore()
        compose.waitForIdle()
        assertBack("Birthday gift", beside)
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assert(hasContentDescription(words(AppLanguage.Fr, "detail.parcelActions")))
        compose.onAllNodes(hasContentDescription(words(AppLanguage.En, "detail.parcelActions"))).assertCountEquals(0)

        // Changed again while the app was away and closed: a new process in German.
        language = AppLanguage.De
        newProcess()
        assertEquals(listOf("parcel"), views)
        assertBack("Birthday gift", beside)
        compose.onNodeWithTag(PAGE_MENU_BUTTON).assert(hasContentDescription(words(AppLanguage.De, "detail.parcelActions")))
    }

    private fun words(language: AppLanguage, key: String): String = Localizer(language, variant = "android").text(key)
}
