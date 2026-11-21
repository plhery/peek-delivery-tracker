package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.PeekAi
import com.plhery.peekdeliverytracker.core.parcelPeekAi
import com.plhery.peekdeliverytracker.core.peekAiClaim
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Peek AI on a parcel's page, in the demo at the captures' moment: Birthday gift's card says what Peek AI expects, and
 * its (i) opens the sheet, counted, with the days; switching Peek AI off there keeps the sheet up, counted, and the card
 * is gone once it closes. Belgian chocolate's sheet has the hours.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekAiPageTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var source: FakeDeliveries
    private val copy = Localizer(AppLanguage.En, variant = "android")

    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
    }

    private fun open(name: String) {
        compose.openCard(DemoCards.parcel(name).label)
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun ai(name: String): PeekAi = requireNotNull(parcelPeekAi(DemoCards.parcel(name), CAPTURED))

    private fun inside(tag: String, text: String): Boolean =
        compose.onAllNodes(hasText(text, substring = true) and hasAnyAncestor(hasTestTag(tag))).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun theGiftsCardOpensPeekAisSheetAndItsSwitchTakesTheCardAway() {
        show()
        open("Birthday gift")
        assertTrue(inside(PEEK_AI_CARD, copy.peekAiClaim(ai("Birthday gift"), CAPTURED)))
        assertFalse(exists(PEEK_AI_SHEET))

        compose.onNodeWithTag(PEEK_AI_INFO).performClick()
        settle()
        assertEquals("peek-ai-open", source.actions.last())
        assertTrue(exists(PEEK_AI_SHEET))
        assertTrue(inside(PEEK_AI_STRIP, copy.text("peekAi.sheet.days")))
        val toggled = { compose.onNodeWithTag(PEEK_AI_SHOW).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState) }
        assertEquals(ToggleableState.On, toggled())

        // The row flips as TalkBack flips it; a touch flips its switch.
        compose.onNodeWithTag(PEEK_AI_SHOW).performSemanticsAction(SemanticsActions.OnClick)
        settle()
        assertFalse(source.showPeekAi.value)
        assertEquals("peek-ai-off", source.actions.last())
        assertEquals(ToggleableState.Off, toggled())
        assertTrue(exists(PEEK_AI_SHEET))

        compose.onNodeWithTag(PEEK_AI_CLOSE).performClick()
        settle()
        assertFalse(exists(PEEK_AI_SHEET))
        assertFalse(exists(PEEK_AI_CARD))
    }

    @Test
    fun theChocolatesSheetHasTheHours() {
        show()
        open("Belgian chocolate")
        assertTrue(exists(PEEK_AI_CARD))
        compose.onNodeWithTag(PEEK_AI_INFO).performClick()
        settle()
        assertTrue(inside(PEEK_AI_STRIP, copy.text("peekAi.sheet.hours")))
    }
}
