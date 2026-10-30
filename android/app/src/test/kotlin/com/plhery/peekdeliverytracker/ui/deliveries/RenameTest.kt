package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Naming a parcel (B-S10, `parcelRenameAlert`): from its page's ••• menu or a tap on its name, and from its card's menu
 * over the list. The alert opens on the parcel's name with the field focused, saves what was typed trimmed and cut to
 * 80 characters once, from Save or the keyboard's Done, and nothing from Cancel or back; a failure says why in the
 * page's alert or the list's; the name typed stays through the window being made again.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class RenameTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val gift get() = DemoCards.parcel("Birthday gift")

    private fun show(recreation: Recreation? = null) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val content: @Composable () -> Unit = {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun open(name: String) {
        compose.openCard(name)
        settle()
    }

    private fun choose(item: ParcelMenuItem) {
        compose.onNodeWithTag(menuItemTag(item)).performClick()
        settle()
    }

    private fun renameFromPageMenu() {
        compose.onNodeWithTag(PAGE_MENU_BUTTON).performClick()
        settle()
        choose(ParcelMenuItem.Rename)
    }

    private fun renameFromCard(name: String) {
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed(name))
        compose.card(name).performTouchInput { longClick() }
        compose.waitForIdle()
        choose(ParcelMenuItem.Rename)
    }

    private fun field() = compose.onNodeWithTag(RENAME_FIELD)

    private fun fieldText(): String? =
        field().fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    @Test
    fun thePagesMenuAsksOnTheNameWithTheFieldFocusedAndSaveSendsItTrimmedAndCutOnce() {
        show()
        open("Birthday gift")
        renameFromPageMenu()
        compose.onNodeWithTag(RENAME_ALERT).assertExists()
        compose.onNode(hasText("Edit parcel name") and hasAnyAncestor(hasTestTag(RENAME_ALERT))).assertExists()
        compose.onNodeWithTag(RENAME_CANCEL).assertTextEquals("Cancel")
        compose.onNodeWithTag(RENAME_SAVE).assertTextEquals("Save name")
        assertEquals(gift.label, fieldText())
        field().assertIsFocused()

        val typed = "  " + "Lamp ".repeat(20) + "\n"
        field().performTextReplacement(typed)
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()
        assertEquals(listOf(gift.id to typed.trim().take(80)), source.renames)
        // The page stays, named anew.
        compose.onNodeWithTag(PAGE_NAME).assertTextEquals(typed.trim().take(80))
        assertEquals(listOf(gift.id), deliveries.navigator.navigation.path)
    }

    @Test
    fun aTapOnTheNameAsksTooAndCancelOrBackChangeNothing() {
        show()
        open("Birthday gift")
        compose.onNodeWithTag(PAGE_NAME).performClick()
        settle()
        assertEquals(gift.label, fieldText())
        field().performTextReplacement("Something else")
        compose.onNodeWithTag(RENAME_CANCEL).performClick()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()

        // Asked again, it starts from the parcel's name, not what was typed before.
        compose.onNodeWithTag(PAGE_NAME).performClick()
        settle()
        assertEquals(gift.label, fieldText())
        back()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.renames)
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    @Test
    fun theKeyboardsDoneSavesAsReturnDoes() {
        show()
        open("Birthday gift")
        renameFromPageMenu()
        field().performTextReplacement("Desk lamp")
        field().performImeAction()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()
        assertEquals(listOf(gift.id to "Desk lamp"), source.renames)
    }

    @Test
    fun aFailureOnThePageSaysWhyInThePagesAlertAndThePageStays() {
        show()
        source.failure = IllegalStateException("No connection")
        open("Birthday gift")
        renameFromPageMenu()
        field().performTextReplacement("Desk lamp")
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        compose.onNode(hasText("No connection")).assertExists()
        assertEquals("No connection", deliveries.pageError)
        assertNull(deliveries.actionError)
        compose.onNodeWithTag(ACTION_ERROR_CLOSE).performClick()
        settle()
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
        assertEquals(listOf(gift.id), deliveries.navigator.navigation.path)
    }

    @Test
    fun aCardsMenuAsksOverTheListAndAFailureSaysWhyInTheListsAlert() {
        show()
        renameFromCard("Birthday gift")
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.onNodeWithTag(RENAME_ALERT).assertExists()
        assertEquals(gift.label, fieldText())
        field().assertIsFocused()
        field().performTextReplacement(" Desk lamp ")
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        assertEquals(listOf(gift.id to "Desk lamp"), source.renames)
        compose.onNode(cardNamed("Desk lamp")).assertExists()

        source.failure = IllegalStateException("No connection")
        renameFromCard("Desk lamp")
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        assertEquals("No connection", deliveries.actionError)
        back()
        compose.onNodeWithTag(ACTION_ERROR).assertDoesNotExist()
        assertEquals(1, source.renames.size)
    }

    @Test
    fun theNameTypedStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation)
        open("Birthday gift")
        renameFromPageMenu()
        field().performTextReplacement("Desk la")
        recreation.recreate()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertExists()
        assertEquals("Desk la", fieldText())
        field().assertIsFocused()
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        assertEquals(listOf(gift.id to "Desk la"), source.renames)
    }

    @Test
    fun theDeleteQuestionStaysThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Birthday gift"))
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
        choose(ParcelMenuItem.Delete)
        compose.onNodeWithTag(DELETE_CONFIRMATION).assertExists()
        recreation.recreate()
        settle()
        compose.onNodeWithTag(DELETE_CONFIRM).performClick()
        settle()
        assertEquals(listOf(gift.id), source.deleted)
    }
}
