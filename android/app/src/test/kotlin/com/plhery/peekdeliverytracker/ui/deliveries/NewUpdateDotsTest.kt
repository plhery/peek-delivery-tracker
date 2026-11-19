package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The list's new-update dots (#72), with every parcel given a scan later than it was last opened: Next up, the cards on
 * their way and the past deliveries read "New update." first; an archived parcel's row doesn't, in the shelf or among a
 * search's results. "Dot on updated parcels" turned off takes every dot away, and turned on brings them back.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class NewUpdateDotsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private val newUpdate = "${Localizer(AppLanguage.En, variant = "android").text("parcel.newUpdate")}. "

    private fun show() {
        val parcels = DemoCards.parcels().map { it.copy(newsAt = "2026-10-29T05:00:00Z", seenAt = "2026-10-28T05:00:00Z") }
        source = FakeDeliveries(parcels, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source) }
        compose.waitForIdle()
    }

    /** Whether the list's card or row of the parcel named `name`, scrolled to, reads "New update." first. */
    private fun marked(name: String): Boolean {
        val card = cardNamed(name) and hasAnyAncestor(hasTestTag(PARCEL_LIST))
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(card)
        val said = compose.onNode(card).fetchSemanticsNode().config[SemanticsProperties.ContentDescription].joinToString()
        return said.startsWith(newUpdate)
    }

    private fun mark(enabled: Boolean) {
        compose.runOnUiThread { source.setMarkNewUpdates(enabled) }
        compose.waitForIdle()
    }

    @Test
    fun everyCardWithNewsHasTheDotButAnArchivedParcelsAndTheSettingTakesThemAway() {
        show()
        // Next up, on its way, delivered.
        val cards = listOf("New trainers", "Birthday gift", "Coffee beans")
        for (name in cards) assertTrue(name, marked(name))
        val shelf = hasContentDescription("Archived", substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))
        // From TalkBack: scrolled to, the shelf can lie under the floating tab bar.
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(shelf)
        compose.onNode(shelf).performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        assertFalse(marked("Linen weekend bag"))

        mark(false)
        for (name in cards) assertFalse(name, marked(name))
        mark(true)
        for (name in cards) assertTrue(name, marked(name))
    }

    /** A search that finds a parcel on its way and an archived one: the first has its dot, the archived one's row none. */
    @Test
    fun anArchivedParcelFoundBySearchHasNoDot() {
        show()
        compose.onNodeWithTag(SEARCH_BUTTON).performSemanticsAction(SemanticsActions.OnClick)
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_FIELD).performTextInput("weekend")
        compose.waitForIdle()
        assertTrue(marked("Trail weekend kit"))
        assertFalse(marked("Linen weekend bag"))
    }
}
