package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * What TalkBack finds on the deliveries' cards (A-AX06, A-AX07): each card one element, read as VoiceOver reads the
 * iPhone's once its texts are combined, a button that opens the parcel, selected while the parcel is shown beside the
 * list; nothing on a card drawn for its look alone.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class CardSemanticsTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val localizer = Localizer(AppLanguage.En, variant = "android")

    private fun fullName(parcel: Parcel): String = DemoCards.identities.shown(parcel, localizer).fullName

    private val buttons = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)

    /** The only button on screen. */
    private fun button(): SemanticsNodeInteraction {
        compose.onAllNodes(buttons).assertCountEquals(1)
        return compose.onAllNodes(buttons)[0]
    }

    private fun SemanticsNodeInteraction.label(): String =
        fetchSemanticsNode().config[SemanticsProperties.ContentDescription].joinToString()

    private fun SemanticsNodeInteraction.hint(): String? = fetchSemanticsNode().config[SemanticsActions.OnClick].label

    private fun SemanticsNodeInteraction.selected(): Boolean? = fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected)

    @Test
    fun nextUpIsOneButtonReadInTheIPhonesOrderWithItsHint() {
        compose.waitsForMaps()
        val parcel = DemoCards.parcel("New trainers")
        var opened = 0
        compose.setContent { CardHost { NextDeliveryPass(parcel, onOpen = { opened++ }) } }
        val card = button()
        assertEquals("${fullName(parcel)}, Next up, New trainers 👟, Ready for pickup · Kiosk im Hauptbahnhof", card.label())
        assertEquals("Parcel details", card.hint())
        assertEquals(false, card.selected())
        // Nothing inside it is read on its own: not the carrier's mark, the words, the stamp nor the map.
        compose.onAllNodesWithText("New trainers 👟").assertCountEquals(0)
        compose.onAllNodesWithContentDescription(fullName(parcel)).assertCountEquals(0)
        card.performClick()
        card.performSemanticsAction(SemanticsActions.OnClick)
        assertEquals(2, opened)
    }

    @Test
    fun anArrivedParcelIsNobodysNextAndSaysWhenItCame() {
        compose.waitsForMaps()
        val parcel = DemoCards.parcel("Coffee beans")
        compose.setContent { CardHost { NextDeliveryPass(parcel, onOpen = {}, arrived = true) } }
        assertEquals("${fullName(parcel)}, Coffee beans ☕, Delivered · yesterday", button().label())
    }

    @Test
    fun theParcelShownBesideTheListIsSelected() {
        compose.waitsForMaps()
        compose.setContent {
            CardHost {
                Column {
                    NextDeliveryPass(DemoCards.parcel("New trainers"), onOpen = {}, selected = true)
                    ParcelPassCard(DemoCards.parcel("Moon lamp"), notice = null, onOpen = {}, selected = true)
                }
            }
        }
        val cards = compose.onAllNodes(buttons)
        cards.assertCountEquals(2)
        assertEquals(true, cards[0].selected())
        assertEquals(true, cards[1].selected())
    }

    @Test
    fun aDecorativeCardSaysNothingAndTakesNoTouches() {
        compose.waitsForMaps()
        compose.setContent {
            CardHost {
                Column {
                    NextDeliveryPass(DemoCards.parcel("New trainers"), onOpen = {}, decorative = true)
                    ParcelPassCard(DemoCards.parcel("Moon lamp"), notice = null, onOpen = {}, decorative = true)
                }
            }
        }
        compose.onAllNodes(buttons).assertCountEquals(0)
        compose.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsActions.OnClick)).assertCountEquals(0)
        compose.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.ContentDescription)).assertCountEquals(0)
        compose.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.Text)).assertCountEquals(0)
    }

    @Test
    fun aListCardIsOneButtonWithoutAHintAsOnTheIPhone() {
        compose.waitsForMaps()
        val parcel = DemoCards.parcel("Moon lamp")
        var opened = false
        compose.setContent { CardHost { ParcelPassCard(parcel, notice = null, onOpen = { opened = true }) } }
        val card = button()
        assertEquals("${fullName(parcel)}, Sat 31 Oct, Moon lamp 🌙, In transit", card.label())
        assertNull(card.hint())
        assertEquals(false, card.selected())
        card.performClick()
        assertTrue(opened)
    }

    @Test
    fun aCardsProblemIsReadLast() {
        compose.waitsForMaps()
        val trail = DemoCards.parcel("Trail weekend kit")
        val latest = trail.trackingEvents.maxBy { it.occurredAt }
        val missed = trail.copy(
            trackingEvents = trail.trackingEvents.map { if (it.id == latest.id) it.copy(stage = TrackingStage.FailedAttempt) else it },
        )
        compose.setContent { CardHost { ParcelPassCard(missed, notice = DemoCards.notice(missed), onOpen = {}) } }
        assertEquals(
            "${fullName(missed)}, Trail weekend kit 🏕️, Delivery attempted, Check the carrier’s next steps",
            button().label(),
        )
    }

    @Test
    fun aProbableCarrierIsNamedAsProbable() {
        val parcel = DemoCards.probable()
        compose.setContent { CardHost { ParcelPassCard(parcel, notice = null, onOpen = {}) } }
        assertEquals("Probably GLS Germany, Desk lamp 💡, Waiting for the carrier", button().label())
    }

    @Test
    fun aCardSaysWhereItsNameIsWritten() {
        compose.waitsForMaps()
        var name: Float? = null
        compose.setContent { CardHost { ParcelPassCard(DemoCards.parcel("Moon lamp"), notice = null, onOpen = {}, onName = { name = it }) } }
        compose.waitForIdle()
        val card = button().getBoundsInRoot()
        val middle = requireNotNull(name)
        // In the window, as there is no list: the card's 102 points hold 13 above, the carrier's 18-point row, 3 and 5
        // more, then the name's 20-point line, the rest centred, 5 points down.
        assertEquals(card.top.value + 5 + 13 + 18 + 3 + 5 + 10, middle, 1f)
    }

    @Test
    fun theShelfIsOneButtonThatSaysWhatItDoes() {
        val parcels = DemoCards.archived()
        var expanded by mutableStateOf(false)
        compose.setContent {
            CardHost { ArchiveShelf(parcels, expanded = expanded, onExpandedChange = { expanded = it }, onOpen = {}) }
        }
        val header = button()
        assertEquals("Archived, 5", header.label())
        assertEquals("Show archived parcels", header.hint())
        header.performClick()
        compose.waitForIdle()
        assertTrue(expanded)
        val all = compose.onAllNodes(buttons)
        all.assertCountEquals(6)
        assertEquals("Hide archived parcels", all[0].hint())
        assertEquals("Linen weekend bag 🧳, La Poste / Colissimo · Delivered, Tue 20 Oct", all[1].label())
        assertNull(all[1].hint())
        all[0].performClick()
        compose.waitForIdle()
        assertFalse(expanded)
        compose.onAllNodes(buttons).assertCountEquals(1)
    }

    @Test
    fun anArchivedRowOpensItsParcelAndIsSelectedBesideTheList() {
        val parcels = DemoCards.archived()
        var opened: Any? = null
        compose.setContent {
            CardHost { ArchivedParcelGroup(parcels, onOpen = { opened = it }, selected = parcels[2].id) }
        }
        val rows = compose.onAllNodes(buttons)
        rows.assertCountEquals(5)
        assertEquals(true, rows[2].selected())
        assertEquals(false, rows[0].selected())
        rows[3].performClick()
        assertEquals(parcels[3].id, opened)
    }

    @Test
    fun theShelfIsWrittenAgainInANewLanguage() {
        val parcels = DemoCards.archived()
        val localizer = Localizer(AppLanguage.En, variant = "android")
        compose.setContent {
            Column {
                CardHost(localizer = localizer) { ArchiveShelf(parcels, expanded = false, onExpandedChange = {}, onOpen = {}) }
                CardHost(AppLanguage.De) { ArchiveShelf(parcels, expanded = false, onExpandedChange = {}, onOpen = {}) }
            }
        }
        val headers = compose.onAllNodes(buttons)
        headers.assertCountEquals(2)
        assertEquals("Archived, 5", headers[0].label())
        assertFalse("English and German headers look different", headers[0].pixels().contentEquals(headers[1].pixels()))

        localizer.language = AppLanguage.De
        compose.waitForIdle()
        assertEquals("Archiviert, 5", headers[0].label())
        assertEquals("Archivierte Pakete anzeigen", headers[0].hint())
        // Its words on screen too: drawn as the German header drawn in German from the start.
        assertArrayEquals(headers[1].pixels(), headers[0].pixels())
    }

    private fun SemanticsNodeInteraction.pixels(): IntArray = compose.drawn(this)

    /** What a long press plays, as the phone's own long press does. */
    private class FeltHaptics : HapticFeedback {
        val played = mutableListOf<HapticFeedbackType>()

        override fun performHapticFeedback(hapticFeedbackType: HapticFeedbackType) {
            played += hapticFeedbackType
        }
    }

    @Test
    fun aLongPressCallsForTheCardsMenuWithTheLongPressFeelAndOpensNothing() {
        compose.waitsForMaps()
        val next = DemoCards.parcel("New trainers")
        val card = DemoCards.parcel("Moon lamp")
        val delivered = DemoCards.parcel("Coffee beans")
        val archived = DemoCards.archived().first()
        val opened = mutableListOf<String>()
        val held = mutableListOf<String>()
        val haptics = FeltHaptics()
        compose.setContent {
            CompositionLocalProvider(LocalHapticFeedback provides haptics) {
                CardHost {
                    Column {
                        NextDeliveryPass(next, onOpen = { opened += "next" }, onLongClick = { held += "next" })
                        ParcelPassCard(card, notice = null, onOpen = { opened += "card" }, onLongClick = { held += "card" })
                        DeliveredParcelCard(delivered, onOpen = { opened += "delivered" }, onLongClick = { held += "delivered" })
                        ArchivedParcelRow(archived, onOpen = { opened += "archived" }, onLongClick = { held += "archived" })
                    }
                }
            }
        }
        val names = listOf("next", "card", "delivered", "archived")
        val cards = compose.onAllNodes(buttons)
        cards.assertCountEquals(4)
        names.forEachIndexed { index, name ->
            cards[index].performTouchInput { longClick() }
            compose.waitForIdle()
            assertEquals(name, held.last())
            assertEquals(index + 1, haptics.played.count { it == HapticFeedbackType.LongPress })
        }
        assertEquals("A long press opens no parcel", emptyList<String>(), opened)
        // TalkBack's long press is the same menu.
        names.forEachIndexed { index, name ->
            cards[index].performSemanticsAction(SemanticsActions.OnLongClick)
            assertEquals(name, held.last())
        }
        assertEquals(8, held.size)
        names.forEachIndexed { index, _ -> cards[index].performClick() }
        assertEquals(names, opened)
        assertEquals(8, held.size)
    }

    @Test
    fun aCardWithoutAMenuHasNoLongPress() {
        compose.waitsForMaps()
        compose.setContent { CardHost { ParcelPassCard(DemoCards.parcel("Moon lamp"), notice = null, onOpen = {}) } }
        assertNull(button().fetchSemanticsNode().config.getOrNull(SemanticsActions.OnLongClick))
    }

    @Test
    fun theListPutsItsMenuAndItsOwnModifierOnEachArchivedRow() {
        val parcels = DemoCards.archived()
        val held = mutableListOf<Parcel>()
        var opened: Any? = null
        compose.setContent {
            CardHost {
                ArchiveShelf(
                    parcels, expanded = true, onExpandedChange = {}, onOpen = { opened = it }, onLongClick = { held += it },
                    rowModifier = { Modifier.testTag("row ${it.trackingNumber}") },
                )
            }
        }
        for (parcel in parcels) {
            val row = compose.onNodeWithTag("row ${parcel.trackingNumber}")
            assertTrue(row.label().startsWith(parcel.label))
            row.performTouchInput { longClick() }
            compose.waitForIdle()
            assertEquals(parcel.id, held.last().id)
        }
        assertEquals(parcels.size, held.size)
        assertNull(opened)
        compose.onNodeWithTag("row ${parcels[2].trackingNumber}").performClick()
        assertEquals(parcels[2].id, opened)
    }
}
