package com.plhery.peekdeliverytracker.ui.deliveries

import android.text.InputType
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierChange
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeeded
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeededField
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import java.time.Duration
import java.util.UUID
import kotlin.math.roundToInt
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Changing a parcel's carrier (B-S13, B-ST27, ChangeCarrierView): the sheet from a page's ••• menu, from its prompts on
 * the carrier they name and from a card's menu; automatic detection at the medium detent, a carrier that asks for a
 * postcode at the large one with its country beside it; the picker pushed from the carrier's row and taken away by a
 * choice, the ‹ or the back; the fields as they are typed; the save, which waits with Cancel and dismissal off, closes
 * once saved and says why in red once failed; a refused number under the carrier; the sheet through a window made
 * again; and its carrier row as TalkBack reads it. On synthetic parcels.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class ChangeCarrierTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    // In Zurich, where the phone's country is Switzerland.
    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private fun parcel(index: Int, label: String, carrier: CarrierId, number: String, postcode: String? = null, data: CarrierData? = null) = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-0000000005%02d".format(index)), trackingNumber = number, label = label,
        carrier = carrier, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(index.toLong()))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false, dpdPostcode = postcode, carrierData = data,
    )

    /** Filed under none: automatic detection. */
    private val lamp = parcel(1, "Desk lamp", CarrierId.Unknown, "TESTLAMP0001")

    /** Mondial Relay asks for the delivery postcode, from one of several countries. */
    private val chair = parcel(2, "Garden chair", CarrierId.MondialRelay, "76434219")

    /** DPD and GLS used before, so the picker offers them first. */
    private val kettle = parcel(3, "Kettle", CarrierId.Dpd, "TESTKETTLE01", postcode = "8000")
    private val boots = parcel(4, "Hiking boots", CarrierId.GlsCh, "TESTBOOTS001")

    private val parcels get() = listOf(lamp, chair, kettle, boots)

    private fun show(
        list: List<Parcel> = parcels,
        session: AuthenticationState = AuthenticationState.Demo,
        recreation: Recreation? = null,
        fontScale: Float = 1f,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(list, session).apply(prepare)
        val content: @Composable () -> Unit = {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, source = source, reduceMotion = false)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun fieldText(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun sheetTop(): Float = node(SHEET_PANEL).getUnclippedBoundsInRoot().top.value

    private fun windowHeight(): Float = compose.onRoot().getUnclippedBoundsInRoot().bottom.value

    private fun assertMedium() = assertEquals(windowHeight() * 0.4714f, sheetTop(), 2f)

    private fun assertLarge() = assertTrue("At ${sheetTop()}", sheetTop() < windowHeight() * 0.1f)

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    /** `parcel`'s page, then its ••• menu's Change carrier. */
    private fun fromPage(parcel: Parcel) {
        compose.openCard(parcel.label)
        settle()
        node(PAGE_MENU_BUTTON).performClick()
        settle()
        node(menuItemTag(ParcelMenuItem.ChangeCarrier)).performClick()
        settle()
    }

    /** `parcel`'s card's menu's Change carrier, over the list. */
    private fun fromCard(parcel: Parcel) {
        node(PARCEL_LIST).performScrollToNode(cardNamed(parcel.label))
        compose.card(parcel.label).performTouchInput { longClick() }
        compose.waitForIdle()
        node(menuItemTag(ParcelMenuItem.ChangeCarrier)).performClick()
        settle()
    }

    private fun pushPicker() {
        node(CHANGE_CARRIER_ROW).performClick()
        settle()
    }

    private fun pick(carrier: CarrierId) {
        compose.onAllNodesWithTag(carrierPickerRowTag(carrier)).onFirst().performClick()
        settle()
    }

    private fun carrierRowValue(): String? = node(CHANGE_CARRIER_ROW).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription)

    /**
     * Steps the frames from `saved`, which lets a save end, until the editor has closed, then a little more, and runs
     * `check` on the sheet halfway down, before it has gone.
     */
    private fun goingDown(saved: () -> Unit, check: () -> Unit) {
        settle()
        compose.mainClock.autoAdvance = false
        saved()
        var frames = 0
        while (deliveries.carrierEditor != null && frames++ < 60) compose.mainClock.advanceTimeBy(16)
        assertNull(deliveries.carrierEditor)
        compose.mainClock.advanceTimeBy(150)
        node(CHANGE_CARRIER_SHEET).assertExists()
        check()
        compose.mainClock.autoAdvance = true
        settle()
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
    }

    /** Still saying it saves: "Changing…", and Cancel off. */
    private fun assertSaving() {
        node(CHANGE_CARRIER_SAVE).assertTextEquals("Changing…").assertIsNotEnabled()
        node(CHANGE_CARRIER_CANCEL).assertIsNotEnabled()
    }

    /** A keyboard `height` tall over the window's foot, as the system's comes up. */
    private fun keyboard(height: Int) {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        compose.runOnUiThread {
            val px = (height * view.resources.displayMetrics.density).roundToInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.ime(), Insets.of(0, 0, 0, px))
                .setVisible(WindowInsetsCompat.Type.ime(), px > 0)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    private fun talkBack(): View {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view
    }

    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    @Test
    fun anUnfiledParcelOpensOnAutomaticDetectionAtTheMediumDetentFromItsPage() {
        show()
        fromPage(lamp)
        node(CHANGE_CARRIER_SHEET).assertExists()
        assertEquals(lamp.id, deliveries.carrierEditor?.pageId)
        assertMedium()
        node(CHANGE_CARRIER_NUMBER).assertTextEquals("TESTLAMP0001")
        compose.onNode(hasText("Choose the carrier for TESTLAMP0001", substring = true)).assertExists()
        assertEquals("Detect automatically", carrierRowValue())
        node(CHANGE_CARRIER_HINT).assertTextEquals("Peek finds the carrier for you.")
        // Nothing changed: Save is off, Cancel closes.
        node(CHANGE_CARRIER_SAVE).assertTextEquals("Save carrier").assertIsNotEnabled()
        assertTrue(!exists(CHANGE_CARRIER_POSTCODE) && !exists(CHANGE_CARRIER_URL) && !exists(CHANGE_CARRIER_ERROR))
        node(CHANGE_CARRIER_CANCEL).assertTextEquals("Cancel").performClick()
        settle()
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
        assertNull(deliveries.carrierEditor)
        node(PARCEL_PAGE).assertExists()
    }

    @Test
    fun theCarrierRowReadsAsCarrierItsNameAndAButton() {
        show()
        fromPage(lamp)
        val config = node(CHANGE_CARRIER_ROW).assertHasClickAction().fetchSemanticsNode().config
        assertEquals(listOf("Carrier"), config.getOrNull(SemanticsProperties.ContentDescription))
        assertEquals("Detect automatically", config.getOrNull(SemanticsProperties.StateDescription))
        assertEquals(Role.Button, config.getOrNull(SemanticsProperties.Role))
    }

    @Test
    fun aCarrierThatAsksForAPostcodeOpensLargeWithItsCountryAndSavesItFromTheCardsMenu() {
        show()
        fromCard(chair)
        assertNull(deliveries.carrierEditor?.pageId)
        assertEquals(chair.id, deliveries.carrierEditor?.parcelId)
        assertLarge()
        assertEquals("Mondial Relay", carrierRowValue())
        val country = node(CHANGE_CARRIER_COUNTRY).fetchSemanticsNode().config
        assertEquals(listOf("Delivery country"), country.getOrNull(SemanticsProperties.ContentDescription))
        // The phone's country, where the carrier delivers.
        assertEquals("Switzerland", country.getOrNull(SemanticsProperties.StateDescription))
        assertEquals(ContentType.PostalCode, node(CHANGE_CARRIER_POSTCODE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentType))
        node(CHANGE_CARRIER_SAVE).assertIsNotEnabled()

        // Belgium, from the menu beside the field.
        node(CHANGE_CARRIER_COUNTRY).performClick()
        settle()
        node(postcodeCountryTag("BE")).performClick()
        settle()
        assertEquals("Belgium", node(CHANGE_CARRIER_COUNTRY).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription))
        node(CHANGE_CARRIER_POSTCODE).performTextInput("1000")
        compose.waitForIdle()
        node(CHANGE_CARRIER_SAVE).assertIsEnabled().performClick()
        settle()
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
        assertEquals(
            listOf(FakeDeliveries.CarrierChangeRequest(chair.id, CarrierId.MondialRelay, null, "1000", "BE", null)),
            source.carrierRequests,
        )
    }

    @Test
    fun thePickerPushesFromTheRowAndAChoiceSetsTheCarrierAndPops() {
        show()
        fromPage(lamp)
        assertMedium()
        pushPicker()
        node(CARRIER_PICKER).assertIsDisplayed()
        node(CHANGE_CARRIER_ROW).assertIsNotDisplayed()
        // The full list, and room for its letter rail.
        assertLarge()
        // Automatic detection is offered and chosen.
        compose.onNodeWithTag(CARRIER_PICKER_AUTOMATIC).assertExists()
        pick(CarrierId.Dpd)
        assertTrue(!exists(CARRIER_PICKER))
        node(CHANGE_CARRIER_ROW).assertIsDisplayed()
        assertEquals("DPD Switzerland", carrierRowValue())
        // DPD's hint, its optional postcode and what the postcode adds.
        compose.onNode(hasText("Optional") and hasAnyAncestor(hasTestTag(CHANGE_CARRIER_SHEET))).assertExists()
        compose.onNode(hasText("With it, DPD Switzerland also shows verified scans and the delivery window.")).assertExists()
        node(CHANGE_CARRIER_SAVE).assertIsEnabled()

        // The ‹ takes it away, choosing nothing. TalkBack reads it by the form's title, as VoiceOver reads UIKit's.
        pushPicker()
        node(CHANGE_CARRIER_BACK).assertContentDescriptionEquals("Change carrier")
        node(CHANGE_CARRIER_BACK).performClick()
        settle()
        assertTrue(!exists(CARRIER_PICKER))
        assertEquals("DPD Switzerland", carrierRowValue())
        // As the back does, which leaves the sheet.
        pushPicker()
        back()
        settle()
        assertTrue(!exists(CARRIER_PICKER))
        node(CHANGE_CARRIER_SHEET).assertExists()
        // Automatic detection again: the fields go.
        pushPicker()
        node(CARRIER_PICKER_AUTOMATIC).performClick()
        settle()
        assertEquals("Detect automatically", carrierRowValue())
        assertTrue(!exists(CHANGE_CARRIER_POSTCODE))
        node(CHANGE_CARRIER_SAVE).assertIsNotEnabled()
    }

    @Test
    fun inTheSheetADragDownThePickersRailJumpsDownTheListAndLeavesTheSheetWhereItIs() {
        show()
        fromPage(lamp)
        pushPicker()
        assertLarge()
        val top = sheetTop()
        node(LETTER_RAIL).performTouchInput {
            down(Offset(width / 2f, height * 0.2f))
            moveTo(Offset(width / 2f, height * 0.6f))
            moveTo(Offset(width / 2f, height - 1f))
            up()
        }
        settle()
        assertEquals(top, sheetTop(), 1f)
        node(CARRIER_PICKER).assertIsDisplayed()
        // The list stands at its last letters, automatic detection gone by.
        assertTrue(!exists(CARRIER_PICKER_AUTOMATIC))
    }

    @Test
    fun anotherCarrierStartsItsFieldsAfreshAndTypingFollowsTheFieldsRules() {
        show()
        fromPage(chair)
        node(CHANGE_CARRIER_COUNTRY).performClick()
        settle()
        node(postcodeCountryTag("GB")).performClick()
        settle()
        // A postcode of any country, in capitals with one space between its groups.
        node(CHANGE_CARRIER_POSTCODE).performTextInput("sw1a  1aa")
        compose.waitForIdle()
        val typed = CarrierChange.start(chair).copy(chosenPostcodeCountry = "GB").postcodeRequirement!!.typedValue("sw1a  1aa")
        assertEquals("SW1A 1AA", typed)
        assertEquals(typed, fieldText(CHANGE_CARRIER_POSTCODE))

        // GLS: its own empty postcode field, the country menu gone, and numbers only.
        pushPicker()
        pick(CarrierId.GlsCh)
        assertEquals("", fieldText(CHANGE_CARRIER_POSTCODE))
        assertTrue(!exists(CHANGE_CARRIER_COUNTRY))
        node(CHANGE_CARRIER_POSTCODE).performTextInput("80a01")
        compose.waitForIdle()
        assertEquals("8001", fieldText(CHANGE_CARRIER_POSTCODE))
        // Back to Mondial Relay, its fields start from the parcel's own again.
        pushPicker()
        pick(CarrierId.MondialRelay)
        assertEquals("", fieldText(CHANGE_CARRIER_POSTCODE))
        assertEquals("Switzerland", node(CHANGE_CARRIER_COUNTRY).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription))
    }

    @Test
    fun theKeyboardsAreTheNumberPadOrTheLetters() {
        assertEquals(KeyboardType.Number, postcodeKeyboard(CarrierChange.PostcodeKeys.NumberPad))
        assertEquals(KeyboardType.Ascii, postcodeKeyboard(CarrierChange.PostcodeKeys.NumbersAndPunctuation))
        assertEquals(KeyboardType.Ascii, postcodeKeyboard(CarrierChange.PostcodeKeys.AsciiCapable))
    }

    @Test
    fun aProvidersPromptOpensTheEditorOnItsPostcodeWithItsFooter() {
        val asked = kettle.copy(
            carrierData = CarrierData(routing = CarrierDataRouting(providerInputNeeded = ProviderInputNeeded("Example Provider", ProviderInputNeededField.DpdPostcode))),
        )
        show(listOf(lamp, chair, asked, boots))
        compose.openCard(asked.label)
        settle()
        compose.onNode(hasText("Add postcode") and hasClickAction()).performClick()
        settle()
        node(CHANGE_CARRIER_PROVIDER_POSTCODE).assertExists()
        compose.onNode(hasText("Example Provider needs the delivery postcode to look up this parcel.")).assertExists()
        assertEquals(ContentType.PostalCode, node(CHANGE_CARRIER_PROVIDER_POSTCODE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentType))
        node(CHANGE_CARRIER_SAVE).assertIsNotEnabled()
        node(CHANGE_CARRIER_PROVIDER_POSTCODE).performTextInput("ab")
        node(CHANGE_CARRIER_SAVE).assertIsNotEnabled()
        node(CHANGE_CARRIER_PROVIDER_POSTCODE).performTextReplacement(" sw1a 1aa ")
        node(CHANGE_CARRIER_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(
            listOf(FakeDeliveries.CarrierChangeRequest(asked.id, CarrierId.Dpd, null, "8000", null, "SW1A 1AA")),
            source.carrierRequests,
        )
    }

    @Test
    fun aCarriersPromptOpensTheEditorOnTheCarrierItNames() {
        val asked = lamp.copy(
            carrierData = CarrierData(routing = CarrierDataRouting(inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode))),
        )
        show(listOf(asked, chair, kettle, boots))
        compose.openCard(asked.label)
        settle()
        compose.onNode(hasText("Add postcode") and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_PROMPT))).performClick()
        settle()
        assertEquals(CarrierId.Dpd, deliveries.carrierEditor?.initialCarrier)
        assertEquals("DPD Switzerland", carrierRowValue())
        node(CHANGE_CARRIER_POSTCODE).assertExists()
        // The carrier asks for something: the large detent.
        assertLarge()
    }

    @Test
    fun whileItSavesSaveSaysSoAndNeitherCancelNorTheBackCloseItThenItCloses() {
        show { changing = CompletableDeferred() }
        compose.openCard(lamp.label)
        settle()
        compose.runOnUiThread { deliveries.editCarrier(lamp.id, CarrierId.Ups) }
        settle()
        assertMedium()
        node(CHANGE_CARRIER_SAVE).assertIsEnabled().performClick()
        settle()
        node(CHANGE_CARRIER_SAVE).assertTextEquals("Changing…").assertIsNotEnabled()
        node(CHANGE_CARRIER_CANCEL).assertIsNotEnabled()
        back()
        settle()
        node(CHANGE_CARRIER_SHEET).assertExists()
        // Nor a tap outside it.
        compose.onRoot().performTouchInput { click(Offset(width / 2f, 60f)) }
        settle()
        node(CHANGE_CARRIER_SHEET).assertExists()

        // Saved, it goes down still saying so.
        goingDown({ compose.runOnUiThread { source.changing!!.complete(Unit) } }) { assertSaving() }
        assertEquals(listOf(FakeDeliveries.CarrierChangeRequest(lamp.id, CarrierId.Ups, null, null, null, null)), source.carrierRequests)
        // The page stays, on its parcel.
        assertEquals(listOf(lamp.id), deliveries.navigator.navigation.path)
    }

    @Test
    fun aSaveGoesOnThroughTheWindowMadeAgainWithCancelAndDismissalOff() {
        val recreation = Recreation(compose)
        show(recreation = recreation) { changing = CompletableDeferred() }
        compose.openCard(lamp.label)
        settle()
        compose.runOnUiThread { deliveries.editCarrier(lamp.id, CarrierId.Ups) }
        settle()
        node(CHANGE_CARRIER_SAVE).performClick()
        settle()
        recreation.recreate()
        settle()
        assertSaving()
        back()
        settle()
        compose.onRoot().performTouchInput { click(Offset(width / 2f, 60f)) }
        settle()
        node(CHANGE_CARRIER_SHEET).assertExists()
        goingDown({ compose.runOnUiThread { source.changing!!.complete(Unit) } }) { assertSaving() }
        assertEquals(listOf(FakeDeliveries.CarrierChangeRequest(lamp.id, CarrierId.Ups, null, null, null, null)), source.carrierRequests)
    }

    @Test
    fun aFailureSaysWhyInRedAndAnotherTryOrCarrierClearsIt() {
        show { failure = IllegalStateException("No connection") }
        fromPage(lamp)
        pushPicker()
        pick(CarrierId.Dpd)
        node(CHANGE_CARRIER_SAVE).performClick()
        settle()
        node(CHANGE_CARRIER_ERROR).assertExists()
        compose.onNode(hasText("No connection") and hasAnyAncestor(hasTestTag(CHANGE_CARRIER_ERROR))).assertExists()
        node(CHANGE_CARRIER_SAVE).assertTextEquals("Save carrier").assertIsEnabled()
        node(CHANGE_CARRIER_CANCEL).assertIsEnabled()
        // Another carrier clears it.
        pushPicker()
        pick(CarrierId.GlsCh)
        assertTrue(!exists(CHANGE_CARRIER_ERROR))
        node(CHANGE_CARRIER_POSTCODE).performTextInput("8001")
        node(CHANGE_CARRIER_SAVE).performClick()
        settle()
        node(CHANGE_CARRIER_ERROR).assertExists()
        // Another try, once it can.
        source.failure = null
        node(CHANGE_CARRIER_SAVE).performClick()
        settle()
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
        assertEquals(listOf(FakeDeliveries.CarrierChangeRequest(lamp.id, CarrierId.GlsCh, null, "8001", null, null)), source.carrierRequests)
    }

    @Test
    fun aRefusedNumberSaysSoUnderTheCarrierUntilAnotherIsChosen() {
        // A number of its own: the app asks about each pair once while it runs.
        val refused = parcel(5, "Wool socks", CarrierId.Dpd, "TESTREFUSED05", postcode = "8000")
        val user = AuthenticationState.SignedIn(AuthUser(UUID(0, 5), email = "reader@example.com"))
        show(listOf(lamp, chair, refused, boots), session = user) {
            refusals = setOf(NumberRefusalRequest("TESTREFUSED05", CarrierId.Dpd))
        }
        fromCard(refused)
        compose.onNodeWithTag(CHANGE_CARRIER_REFUSED, useUnmergedTree = true).assertTextEquals("doesn’t issue numbers like this one")
        assertEquals("DPD Switzerland, doesn’t issue numbers like this one", carrierRowValue())
        assertEquals(listOf(NumberRefusalRequest("TESTREFUSED05", CarrierId.Dpd)), source.refusalQuestions)
        pushPicker()
        pick(CarrierId.GlsCh)
        assertTrue(!exists(CHANGE_CARRIER_REFUSED))
        assertEquals("GLS Switzerland", carrierRowValue())
    }

    @Test
    fun inTheDemoNoNumberIsAskedAbout() {
        show { refusals = setOf(NumberRefusalRequest("TESTKETTLE01", CarrierId.Dpd)) }
        fromCard(kettle)
        assertTrue(!exists(CHANGE_CARRIER_REFUSED))
        assertEquals(emptyList<NumberRefusalRequest>(), source.refusalQuestions)
    }

    @Test
    fun theSheetItsFieldsAndThePickerStayThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        fromPage(chair)
        node(CHANGE_CARRIER_POSTCODE).performTextInput("8001")
        pushPicker()
        recreation.recreate()
        settle()
        node(CARRIER_PICKER).assertIsDisplayed()
        assertLarge()
        node(CHANGE_CARRIER_BACK).performClick()
        settle()
        assertEquals("8001", fieldText(CHANGE_CARRIER_POSTCODE))
        node(CHANGE_CARRIER_SAVE).assertIsEnabled().performClick()
        settle()
        // Switzerland isn't Mondial Relay's home: its country goes with the postcode.
        assertEquals(listOf(FakeDeliveries.CarrierChangeRequest(chair.id, CarrierId.MondialRelay, null, "8001", "CH", null)), source.carrierRequests)
    }

    @Test
    fun anotherCarrierAndItsFieldsStayThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        fromPage(chair)
        pushPicker()
        pick(CarrierId.GlsCh)
        node(CHANGE_CARRIER_POSTCODE).performTextInput("8001")
        recreation.recreate()
        settle()
        assertEquals("GLS Switzerland", carrierRowValue())
        assertEquals("8001", fieldText(CHANGE_CARRIER_POSTCODE))
        node(CHANGE_CARRIER_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(listOf(FakeDeliveries.CarrierChangeRequest(chair.id, CarrierId.GlsCh, null, "8001", null, null)), source.carrierRequests)
    }

    @Test
    fun draggedToTheMediumDetentItStaysThereThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        fromPage(chair)
        assertLarge()
        node(SHEET_PANEL).performTouchInput { swipeDown(top + 30f, top + 420f, durationMillis = 400) }
        settle()
        assertMedium()
        recreation.recreate()
        settle()
        node(CHANGE_CARRIER_SHEET).assertExists()
        assertMedium()
    }

    @Test
    fun theFieldsOpenTheirKeyboards() {
        // Planzer follows a parcel through the link its message shared.
        val sofa = parcel(6, "Sofa", CarrierId.Planzer, "9999012345678")
        show(parcels + sofa)
        fromCard(chair)
        // Mondial Relay's postcode, from Switzerland: digits first, and the letters Android has for them.
        val postcode = keyboard(CHANGE_CARRIER_POSTCODE)
        assertEquals(InputType.TYPE_CLASS_TEXT, postcode.inputType and InputType.TYPE_MASK_CLASS)
        assertTrue(postcode.inputType and InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS != 0)
        assertTrue(postcode.inputType and InputType.TYPE_TEXT_FLAG_AUTO_CORRECT == 0)
        // GLS takes digits alone: the number pad.
        pushPicker()
        pick(CarrierId.GlsCh)
        assertEquals(InputType.TYPE_CLASS_NUMBER, keyboard(CHANGE_CARRIER_POSTCODE).inputType and InputType.TYPE_MASK_CLASS)
        node(CHANGE_CARRIER_CANCEL).performClick()
        settle()

        // The link: the web address keys, no capitals nor corrections.
        fromCard(sofa)
        val link = keyboard(CHANGE_CARRIER_URL)
        assertEquals(InputType.TYPE_TEXT_VARIATION_URI, link.inputType and InputType.TYPE_MASK_VARIATION)
        assertTrue(link.inputType and (InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS or InputType.TYPE_TEXT_FLAG_AUTO_CORRECT) == 0)
    }

    /** The keyboard the field tagged `tag` opens with, once it has the focus. */
    private fun keyboard(tag: String): EditorInfo {
        node(tag).performClick()
        compose.waitForIdle()
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        return EditorInfo().also { info -> compose.runOnUiThread { view.onCreateInputConnection(info) } }
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun inARegularWindowItIsAFormSheetInTheMiddleWhateverItAsksFor() {
        show()
        fromCard(chair)
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        assertEquals(540f, (panel.right - panel.left).value, 1f)
        assertEquals((compose.onRoot().getUnclippedBoundsInRoot().right.value - 540f) / 2, panel.left.value, 1f)
        val top = panel.top.value
        assertTrue("At $top", top > windowHeight() * 0.2f)
        // The picker comes in where the form was.
        pushPicker()
        node(CARRIER_PICKER).assertIsDisplayed()
        assertEquals(top, sheetTop(), 1f)
    }

    @Test
    fun anEditorWhosePageIsntShownClosesWithoutHoldingTheDeliveries() {
        show()
        compose.runOnUiThread { deliveries.editCarrier(chair.id) }
        settle()
        assertNull(deliveries.carrierEditor)
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
        assertTrue(!deliveries.modal)
    }

    @Test
    fun aCardsParcelGoneMeanwhileIsStillTheEditorsToTry() {
        show()
        fromCard(chair)
        compose.runOnUiThread { source.parcels.value = source.parcels.value.filter { it.id != chair.id } }
        settle()
        node(CHANGE_CARRIER_SHEET).assertExists()
        assertNotNull(deliveries.carrierEditor)
    }

    @Test
    fun aCardsSheetGoesDownOnItsParcelWhenTheChangeFilesItAnew() {
        // A guest's change makes the parcel anew, under another id.
        show { changedId = UUID.fromString("00000000-0000-4000-8000-000000000599") }
        fromCard(chair)
        node(CHANGE_CARRIER_POSTCODE).performTextInput("8001")
        goingDown({ node(CHANGE_CARRIER_SAVE).performSemanticsAction(SemanticsActions.OnClick) }) {
            node(CHANGE_CARRIER_NUMBER).assertTextEquals("76434219")
            assertEquals("8001", fieldText(CHANGE_CARRIER_POSTCODE))
        }
    }

    @Test
    fun aCardsSheetMadeAgainGoesDownOnItsParcelWhenTheChangeFilesItAnew() {
        val recreation = Recreation(compose)
        show(recreation = recreation) { changedId = UUID.fromString("00000000-0000-4000-8000-000000000599") }
        fromCard(chair)
        recreation.recreate()
        settle()
        node(CHANGE_CARRIER_POSTCODE).performTextInput("8001")
        goingDown({ node(CHANGE_CARRIER_SAVE).performSemanticsAction(SemanticsActions.OnClick) }) {
            node(CHANGE_CARRIER_NUMBER).assertTextEquals("76434219")
            assertEquals("Mondial Relay", carrierRowValue())
        }
    }

    @Test
    fun talkBackGoesBackToThePromptThatOpenedTheEditor() {
        val asked = lamp.copy(
            carrierData = CarrierData(routing = CarrierDataRouting(inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode))),
        )
        show(listOf(asked, chair, kettle, boots), session = AuthenticationState.Welcome)
        val view = talkBack()
        compose.openCard(asked.label)
        settle()
        val prompt = compose.onNode(hasText("Add postcode") and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_PROMPT)))
        prompt.performClick()
        settle()
        node(CHANGE_CARRIER_CANCEL).performClick()
        settle()
        assertTrue(!exists(CHANGE_CARRIER_SHEET))
        assertTrue(prompt.hasTalkBackFocus(view))
    }

    @Test
    fun atTheLargestTextTheBarsTitleIsWholeAtTheDefaultSize() {
        show(fontScale = 2f)
        fromCard(chair)
        val title = compose.onNode(isHeading() and hasText("Change carrier"))
        val layouts = mutableListOf<TextLayoutResult>()
        title.fetchSemanticsNode().config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        val layout = layouts.single()
        assertEquals(1, layout.lineCount)
        assertTrue(!layout.isLineEllipsized(0))
        assertEquals("Change carrier".length, layout.getLineEnd(0))
        // Headline's 17 points, whatever the reader's size.
        assertEquals(17f, with(layout.layoutInput.density) { layout.layoutInput.style.fontSize.toDp().value }, 0.01f)
    }

    @Test
    fun theFocusedFieldComesAboveTheKeyboardAtTheLargestText() {
        show(fontScale = 2f)
        fromCard(chair)
        val window = windowHeight()
        node(CHANGE_CARRIER_POSTCODE).performClick()
        settle()
        keyboard(KEYBOARD)
        settle()
        assertLarge()
        val field = node(CHANGE_CARRIER_POSTCODE).getUnclippedBoundsInRoot()
        assertTrue("Field at ${field.top}..${field.bottom}, keyboard from ${window - KEYBOARD}", field.bottom.value <= window - KEYBOARD + 0.5f)
        // Clear of the bar, 63 tall.
        assertTrue("Field at ${field.top}, under the bar", field.top.value >= sheetTop() + 62.5f)
        node(CHANGE_CARRIER_POSTCODE).assertIsDisplayed()
    }

    @Test
    fun aChangeSavedInOneOfTwoWindowsClosesThatWindowsSheet() {
        // Two windows of the app, side by side, over the process's one store.
        val windows = mutableListOf<DeliveriesState>()
        source = FakeDeliveries(parcels, AuthenticationState.Demo)
        compose.setContent {
            CompositionLocalProvider(LocalDeliveriesState provides { if (windows.none { window -> window === it }) windows += it }) {
                RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) {
                    Row(Modifier.fillMaxSize()) {
                        DeliveriesTab(source, Modifier.weight(1f))
                        DeliveriesTab(source, Modifier.weight(1f))
                    }
                }
            }
        }
        compose.waitForIdle()
        assertEquals(2, windows.size)
        // The second window's change, then the first's.
        for ((window, parcel) in listOf(windows[1] to lamp, windows[0] to boots)) {
            compose.runOnUiThread { window.navigator.open(parcel.id) }
            settle()
            compose.runOnUiThread { window.editCarrier(parcel.id, CarrierId.Ups) }
            settle()
            node(CHANGE_CARRIER_SAVE).performClick()
            settle()
            assertTrue(!exists(CHANGE_CARRIER_SHEET))
            windows.forEach { assertNull(it.carrierEditor) }
            assertTrue(source.pageWork.leaving.isEmpty())
        }
        assertEquals(listOf(lamp.id to CarrierId.Ups, boots.id to CarrierId.Ups), source.carrierChanges)
    }
}

private const val KEYBOARD = 336
