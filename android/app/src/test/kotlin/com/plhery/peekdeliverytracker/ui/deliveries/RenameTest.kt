package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Naming a parcel (B-S10, `parcelRenameAlert`): from its page's ••• menu or a tap on its name, and from its card's menu
 * over the list. The alert opens on the parcel's name with the field focused, saves what was typed trimmed and cut to
 * 80 characters once, from Save or the keyboard's Done, and nothing from Cancel or back; a failure says why in the
 * page's alert or the list's, as does a card's parcel gone meanwhile; the name typed stays through the window being
 * made again. Its buttons stand one over the other at an accessibility text size, and it scrolls in a short window.
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

    private lateinit var view: View

    private fun show(recreation: Recreation? = null, fontScale: Float = 1f) {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val content: @Composable () -> Unit = {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalDensity provides Density(density.density, fontScale)) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun px(dp: Float): Float = dp * compose.activity.resources.displayMetrics.density

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

    private fun bounds(tag: String): Rect = compose.onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot

    /** The keyboard `height` points tall, as the window's insets tell it. */
    private fun keyboard(height: Int) {
        compose.runOnUiThread {
            val px = (height * view.resources.displayMetrics.density).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, px))
                .setVisible(WindowInsetsCompat.Type.ime(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

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
        // Side by side, Cancel first, as their titles fit their halves.
        val cancel = bounds(RENAME_CANCEL)
        val save = bounds(RENAME_SAVE)
        assertEquals(cancel.top, save.top, 0.5f)
        assertTrue(cancel.right <= save.left)

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
    fun aCardsParcelGoneWhileItWasNamedIsTheStoresToRefuseAndTheListSaysWhy() {
        show()
        renameFromCard("Birthday gift")
        field().performTextReplacement("Desk lamp")
        // Deleted elsewhere meanwhile: the name still goes to the parcel the menu asked about, which the store refuses.
        compose.runOnUiThread { source.parcels.value = source.parcels.value.filter { it.id != gift.id } }
        compose.waitForIdle()
        compose.onNodeWithTag(RENAME_SAVE).performClick()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()
        compose.onNodeWithTag(ACTION_ERROR).assertExists()
        assertEquals(DeliveryApiError.ParcelMissing().message, deliveries.actionError)
        assertEquals(emptyList<Any>(), source.renames)
    }

    @Test
    fun atAnAccessibilityTextSizeSaveStandsOverCancelEachTheAlertsWidth() {
        show(fontScale = 2f)
        open("Birthday gift")
        renameFromPageMenu()
        val typing = bounds(RENAME_FIELD)
        val save = bounds(RENAME_SAVE)
        val cancel = bounds(RENAME_CANCEL)
        assertTrue("$save over $cancel", save.bottom <= cancel.top)
        assertEquals(save.left, cancel.left, 0.5f)
        assertEquals(save.width, cancel.width, 0.5f)
        // Each as wide as the alert, wider than its field inset 16 on either side.
        assertEquals(typing.width + px(32f), save.width, 1f)
        compose.onNodeWithTag(RENAME_CANCEL).performClick()
        settle()
        compose.onNodeWithTag(RENAME_ALERT).assertDoesNotExist()
        assertEquals(emptyList<Any>(), source.renames)
    }

    @Test
    @Config(qualifiers = "w844dp-h390dp-xhdpi")
    fun onAPhoneOnItsSideWithTheKeyboardUpTheAlertScrollsInTheRoomLeft() {
        show(fontScale = 1.3f)
        // A card's Rename, asked of the state as the menu would.
        compose.runOnUiThread { deliveries.chose(ParcelMenuItem.Rename, source.parcels.value.first { it.id == gift.id }) }
        compose.waitForIdle()
        keyboard(250)
        val room = compose.onRoot().getUnclippedBoundsInRoot().bottom - 250.dp
        // The alert keeps to what the keyboard leaves, its field in view and its buttons a scroll away.
        val alert = compose.onNode(hasScrollAction() and hasAnyAncestor(hasTestTag(RENAME_ALERT))).getUnclippedBoundsInRoot()
        assertTrue("$alert within $room", alert.top >= 0.dp && alert.bottom <= room + 0.5.dp)
        val save = compose.onNodeWithTag(RENAME_SAVE)
        assertTrue(save.getUnclippedBoundsInRoot().bottom > room)
        save.performScrollTo()
        assertTrue(save.getUnclippedBoundsInRoot().bottom <= room + 0.5.dp)
        save.performClick()
        settle()
        assertEquals(listOf(gift.id to gift.label), source.renames)
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
