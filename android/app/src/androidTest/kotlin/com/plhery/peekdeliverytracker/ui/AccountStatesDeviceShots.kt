package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ACCOUNT
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ACCOUNT_DATA
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT_ACTION
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_ALERT_FIELD
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_APPEARANCE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_BACK
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELETE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_EMAIL_ERROR
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_DELIVERY_UPDATES
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_EXIT_DEMO
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_EXPORT
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_FORGET_ALL
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_HOME_PAGE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_LANGUAGE
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_RESET_DEMO
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_WORKING
import com.plhery.peekdeliverytracker.ui.settings.appearanceTag
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_GRABBER
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import java.io.IOException
import java.net.URI
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Settings' remaining states and gestures on this device's own window (C-ST23 to C-ST25, C-SE16, C-G07, C-G09, C-AX12,
 * C-AX13): the delivery email's switch showing the choice while it saves and going back to the saved one when the save
 * fails; the data page off while the demo's reset runs, the sheet staying through a throw and the back; the demo's exit,
 * the guest's forget all and the account's delete with its address typed; Settings at the largest text; the
 * notification prompt's bell dropped at the largest text; the sheet thrown down from its drag indicator; and the
 * account's address selected by a long press. Throws, taps and long presses that the rows are about go through the
 * screen as a finger's do ([ScreenTouches]). Signed in as the synthetic reader@example.com, or in the demo, or as a guest,
 * on fake deliveries ([ParcelWalk]), which reach no server; each moment saved as accountstates/<row>-<state>.png, with
 * -dark, and -largest at the largest text, in the test package's external files. Each test keeps Zurich's time and the
 * captures' moment, under Reduce Motion.
 */
@RunWith(AndroidJUnit4::class)
class AccountStatesDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val touches = ScreenTouches(compose)
    private val parcels: List<Parcel> = DemoCards.parcels()

    /** Settings, opened from the account button. */
    private fun openSettings() {
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.settle()
    }

    /** The page [row] pushes, once it shows what is tagged [until]. */
    private fun push(row: String, until: String) {
        walk.tap(row)
        compose.waitUntil(LONG_MILLIS) { walk.exists(until) }
        walk.settle()
    }

    private fun bounds(matcher: SemanticsMatcher): Rect = touches.node(matcher).boundsInWindow

    private fun bounds(tag: String): Rect = bounds(hasTestTag(tag))

    /** How the text [matcher] finds first is laid out. */
    private fun layoutOf(matcher: SemanticsMatcher): TextLayoutResult {
        val node = touches.node(matcher)
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    private fun enabled(tag: String): Boolean = SemanticsProperties.Disabled !in walk.node(tag).fetchSemanticsNode().config

    /** The sheet under the finger, thrown down from its drag indicator by half the window. */
    private fun throwDown() {
        val height = walk.onUi { compose.activity.window.decorView.height.toFloat() }
        touches.fling(touches.point(SHEET_GRABBER), Offset(0f, height * 0.5f))
        walk.settle()
    }

    private fun selectionShown(): Boolean = compose.onAllNodes(
        SemanticsMatcher("is a selection handle") { node -> node.config.any { it.key.name == "SelectionHandleInfo" } },
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /**
     * C-ST23: the delivery email's switch turned on shows On, off, while the save runs, and On once saved; turned off
     * with a save that fails, it shows Off while it runs, then "email.setting.failed" and On again, the saved choice.
     */
    @Test
    fun theEmailSwitchShowsItsSaveAndGoesBackWhenItFails() {
        val first = CompletableDeferred<Unit>()
        walk.show(parcels) {
            signedInWithEmail(choice = false)
            choosing = first
        }
        openSettings()
        push(SETTINGS_DELIVERY_UPDATES, until = SETTINGS_DELIVERY_EMAIL)
        assertEquals(ToggleableState.Off, walk.toggle(SETTINGS_DELIVERY_EMAIL))

        walk.flip(SETTINGS_DELIVERY_EMAIL)
        assertEquals(ToggleableState.On, walk.toggle(SETTINGS_DELIVERY_EMAIL))
        walk.node(SETTINGS_DELIVERY_EMAIL).assertIsNotEnabled()
        walk.shot("C-ST23-saving")
        first.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { enabled(SETTINGS_DELIVERY_EMAIL) }
        walk.settle()
        assertEquals(listOf(true), walk.onUi { walk.source.emailChoices.toList() })
        assertEquals(ToggleableState.On, walk.toggle(SETTINGS_DELIVERY_EMAIL))
        assertFalse(walk.exists(SETTINGS_DELIVERY_EMAIL_ERROR))

        val second = CompletableDeferred<Unit>()
        walk.onUi {
            walk.source.failure = IOException("The connection went")
            walk.source.choosing = second
        }
        walk.flip(SETTINGS_DELIVERY_EMAIL)
        assertEquals(ToggleableState.Off, walk.toggle(SETTINGS_DELIVERY_EMAIL))
        walk.node(SETTINGS_DELIVERY_EMAIL).assertIsNotEnabled()
        second.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_DELIVERY_EMAIL_ERROR) }
        walk.settle()
        assertTrue(walk.shows(SETTINGS_DELIVERY_EMAIL_ERROR, walk.text("email.setting.failed")))
        assertEquals("Back to the saved choice", ToggleableState.On, walk.toggle(SETTINGS_DELIVERY_EMAIL))
        assertTrue(enabled(SETTINGS_DELIVERY_EMAIL))
        assertEquals(listOf(true), walk.onUi { walk.source.emailChoices.toList() })
        walk.shot("C-ST23-failed")
    }

    /**
     * C-ST24, C-SE16, C-G07: the demo's reset, asked first; while it runs the data page spins, its ‹ is off, a tap on
     * Exit demo through the screen does nothing, and neither the system's back nor a throw down closes the sheet. Once
     * reset, the sheet closes; opened again, Exit demo shows the welcome and closes it.
     */
    @Test
    fun whileTheDemosResetRunsThePageIsOffAndTheSheetStays() {
        val held = CompletableDeferred<Unit>()
        walk.show(parcels, session = AuthenticationState.Demo) { accountWork = held }
        openSettings()
        push(SETTINGS_ACCOUNT_DATA, until = SETTINGS_RESET_DEMO)
        walk.shot("C-SE16-demo-data")
        push(SETTINGS_RESET_DEMO, until = SETTINGS_ALERT_ACTION)
        walk.shot("C-SE16-reset-asked")
        walk.tap(SETTINGS_ALERT_ACTION)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_WORKING) }
        compose.waitForIdle()
        assertTrue("resetDemoData" in walk.onUi { walk.source.accountCalls.toList() })
        assertTrue(walk.spins(SETTINGS_WORKING))
        walk.node(SETTINGS_BACK).assertIsNotEnabled()

        touches.tap(SETTINGS_EXIT_DEMO)
        assertEquals("A tap on the page while it works does nothing", 0, walk.onUi { walk.source.welcomes })
        walk.back()
        assertTrue("The back leaves the page", walk.exists(SETTINGS_EXPORT))
        throwDown()
        assertTrue("A throw leaves the sheet", walk.exists(SETTINGS_SHEET) && walk.exists(SETTINGS_EXPORT))
        assertFalse(compose.activity.isFinishing)
        walk.shot("C-ST24-working")

        held.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        walk.settle()

        openSettings()
        push(SETTINGS_ACCOUNT_DATA, until = SETTINGS_EXIT_DEMO)
        walk.tap(SETTINGS_EXIT_DEMO)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        assertEquals(1, walk.onUi { walk.source.welcomes })
        walk.settle()
        walk.shot("C-SE16-demo-left")
    }

    /** C-SE16: a guest's forget all, answered, forgets the phone's parcels; the page stays, without the row. */
    @Test
    fun aGuestForgetsAllTheirParcels() {
        walk.show(parcels, session = AuthenticationState.Welcome) { isGuest = true }
        openSettings()
        push(SETTINGS_ACCOUNT_DATA, until = SETTINGS_FORGET_ALL)
        push(SETTINGS_FORGET_ALL, until = SETTINGS_ALERT_ACTION)
        walk.tap(SETTINGS_ALERT_ACTION)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_FORGET_ALL) }
        walk.settle()
        assertTrue("forgetDeviceParcels" in walk.onUi { walk.source.accountCalls.toList() })
        assertTrue(walk.onUi { walk.source.parcels.value.isEmpty() })
        assertTrue(walk.exists(SETTINGS_SHEET))
        assertTrue(walk.exists(SETTINGS_EXPORT))
        walk.shot("C-SE16-guest-forgotten")
    }

    /**
     * C-SE16: the account's delete waits for its address: another one leaves Permanently delete off; the address typed
     * in other capitals turns it on, and it deletes the account with what was typed and closes the sheet.
     */
    @Test
    fun deletingTheAccountWaitsForItsAddress() {
        walk.show(parcels)
        openSettings()
        push(SETTINGS_ACCOUNT_DATA, until = SETTINGS_DELETE)
        push(SETTINGS_DELETE, until = SETTINGS_ALERT_FIELD)
        walk.node(SETTINGS_ALERT_FIELD).performTextReplacement("someone@example.com")
        compose.waitForIdle()
        walk.node(SETTINGS_ALERT_ACTION).assertIsNotEnabled()
        walk.awaitKeyboard(required = false)
        walk.shot("C-SE16-delete-another-address", alsoDark = false)
        walk.node(SETTINGS_ALERT_FIELD).performTextReplacement(TYPED)
        compose.waitForIdle()
        walk.node(SETTINGS_ALERT_ACTION).assertIsEnabled()
        walk.shot("C-SE16-delete-address-typed", alsoDark = false)
        walk.tap(SETTINGS_ALERT_ACTION)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        assertTrue("deleteAccount:$TYPED" in walk.onUi { walk.source.accountCalls.toList() })
    }

    /**
     * C-ST25, C-AX12, C-AX13: at the largest text Settings' rows drop their symbols and put their values under their
     * titles, a one-word value on one line made smaller rather than broken, down to half; the language row puts its
     * picker under its label, and the appearances stand one under the other without their pictures.
     */
    @Test
    fun settingsAtTheLargestText() {
        walk.fontScale = 2f
        walk.show(parcels)
        openSettings()
        val dense = Density(compose.density.density, 2f)
        val type = PeekTypography(DynamicTypeSize.Accessibility2, dense)
        fun px(sp: TextUnit) = with(dense) { sp.toPx() }
        val padding = with(dense) { 15.dp.toPx() }

        walk.node(SETTINGS_DELIVERY_UPDATES).performScrollTo()
        compose.waitForIdle()
        val updates = bounds(SETTINGS_DELIVERY_UPDATES)
        val title = bounds(hasText(walk.text("settings.deliveryUpdates")) and hasAnyAncestor(hasTestTag(SETTINGS_DELIVERY_UPDATES)))
        val off = bounds(hasText(walk.text("settings.off")) and hasAnyAncestor(hasTestTag(SETTINGS_DELIVERY_UPDATES)))
        assertEquals("No bell before the title", updates.left + padding, title.left, 1f)
        assertTrue("Off under the title: $off, $title", off.top >= title.bottom - 0.5f)
        walk.shot("C-ST25-rows")

        walk.node(SETTINGS_LANGUAGE).performScrollTo()
        compose.waitForIdle()
        val language = bounds(SETTINGS_LANGUAGE)
        val stackedLanguage = 2 * padding + px(type.subheadline.fontSize) + px(type.body.fontSize)
        assertTrue("The language under its label: ${language.height} < $stackedLanguage", language.height >= stackedLanguage)
        val tiles = AppAppearance.entries.map { bounds(appearanceTag(it)) }
        tiles.zipWithNext { above, below -> assertTrue("One under the other: $above, $below", below.top >= above.bottom - 0.5f) }
        val picker = bounds(SETTINGS_APPEARANCE)
        val withPicture = with(dense) { (10.dp + 43.dp + 8.dp + 10.dp).toPx() } + px(type.caption.fontSize)
        for (tile in tiles) {
            assertEquals("Each across the row", picker.width - 2 * padding, tile.width, 1f)
            assertTrue("No picture: ${tile.height} >= $withPicture", tile.height < withPicture)
        }
        walk.node(SETTINGS_APPEARANCE).performScrollTo()
        compose.waitForIdle()
        walk.shot("C-ST25-appearance")

        walk.node(SETTINGS_HOME_PAGE).performScrollTo()
        compose.waitForIdle()
        val host = URI(walk.source.homePageUrl).host
        val value = layoutOf(hasText(host) and hasAnyAncestor(hasTestTag(SETTINGS_HOME_PAGE)))
        val caption = type.caption.fontSize.value
        assertEquals("The address on one line", 1, value.lineCount)
        assertFalse(value.hasVisualOverflow)
        val size = value.layoutInput.style.fontSize.value
        assertTrue("Made smaller down to half at most: $size of $caption", size >= caption * 0.5f - 0.01f && size <= caption + 0.01f)
        walk.shot("C-ST25-home-page")
    }

    /** C-AX13: the notification prompt shows its bell, which it drops at the largest text, keeping its words. */
    @Test
    fun thePromptDropsItsBellAtTheLargestText() {
        walk.show(parcels) { shouldInviteNotifications.value = true }
        compose.waitUntil(LONG_MILLIS) { walk.exists(NOTIFICATIONS_PROMPT) }
        walk.settle()
        assertTrue(walk.exists(NOTIFICATIONS_PROMPT_BELL))
        walk.fontScale = 2f
        walk.recompose()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(NOTIFICATIONS_PROMPT_BELL) }
        walk.settle()
        assertTrue(walk.exists(NOTIFICATIONS_PROMPT))
        walk.shot("C-AX13-prompt-without-bell")
    }

    /** C-G07: Settings shows its drag indicator and goes when thrown down from it, through the screen. */
    @Test
    fun settingsGoesWhenThrownDown() {
        walk.show(parcels)
        openSettings()
        assertTrue(walk.exists(SHEET_GRABBER))
        throwDown()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(SETTINGS_SHEET) }
        assertFalse(compose.activity.isFinishing)
    }

    /** C-G09: a long press on the account's address, through the screen, selects it: its handles show. */
    @Test
    fun aLongPressSelectsTheAccountsAddress() {
        walk.show(parcels)
        openSettings()
        assertFalse(selectionShown())
        val address = hasText(ADDRESS) and hasAnyAncestor(hasTestTag(SETTINGS_ACCOUNT))
        touches.longPress(touches.point(address, x = 0.3f))
        compose.waitUntil(LONG_MILLIS) { selectionShown() }
        walk.shot("C-G09-address-selected", alsoDark = false)
    }

    private companion object {
        const val FOLDER = "accountstates"

        /** The synthetic reader's address ([ParcelWalk.READER]). */
        const val ADDRESS = "reader@example.com"

        /** The reader's address in other capitals, which the delete takes as theirs. */
        const val TYPED = "Reader@Example.com"
    }
}
