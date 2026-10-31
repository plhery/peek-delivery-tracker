package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.GivenPostcode
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponseAmazonShippingStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.sheet.COVER_PANEL
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Adding a parcel (B-S20-30, B-ST17-25, B-D18, AddParcelView): Add from the +, the empty list's Paste and Add, Ctrl+N
 * and a drop; a cover over the whole window on a phone and a form side by side; the field as it is typed, pasted and
 * left, and the carrier line, the check, Amazon's, several numbers, the numbers held back, the details a carrier needs
 * and the name; saving, its error and a duplicate; the card shown once Add has gone; Add through a window made again;
 * its keys, its motion and its tick. On synthetic numbers, signed in where the demo would look nothing up.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class AddParcelTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    // In Zurich, where the phone's country is Switzerland.
    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private lateinit var view: View
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))

    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000000701"), trackingNumber = "TESTLAMP0001", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private val added = UUID.fromString("00000000-0000-4000-8000-000000000799")

    private fun show(
        list: List<Parcel> = listOf(lamp),
        session: AuthenticationState = reader,
        recreation: Recreation? = null,
        fontScale: Float = 1f,
        reduceMotion: Boolean = true,
        ticking: View? = null,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        source = FakeDeliveries(list, session).apply {
            addedId = added
            prepare()
        }
        val content: @Composable () -> Unit = {
            view = LocalView.current
            val density = LocalDensity.current
            val root = @Composable {
                CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                    RootHost(state = session, source = source, reduceMotion = reduceMotion)
                }
            }
            if (ticking != null) CompositionLocalProvider(LocalView provides ticking, content = root) else root()
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = localizer.text(key, variables)

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun fieldText(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun description(tag: String, unmerged: Boolean = false): String? = compose.onNodeWithTag(tag, useUnmergedTree = unmerged).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString()

    private fun value(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription)

    /** Add from the list's +, up and with the keyboard on its field. */
    private fun open() {
        node(ADD_BUTTON).performClick()
        settle()
    }

    /** `text` typed in the tracking field. */
    private fun type(text: String) {
        node(ADD_TRACKING).performTextReplacement(text)
        compose.waitForIdle()
    }

    /** The tracking field left for the name, which settles its number. */
    private fun leave() {
        node(ADD_NAME).performClick()
        settle()
    }

    private fun clipboard(text: String?) {
        val manager = compose.activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        compose.runOnUiThread {
            if (text == null) manager.clearPrimaryClip() else manager.setPrimaryClip(ClipData.newPlainText("", text))
        }
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** A key pressed and let go on a hardware keyboard, with `meta` held, through the window as the system sends it. */
    private fun press(key: Int, meta: Int = 0) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        settle()
    }

    private fun assertClosed() {
        assertNull(deliveries.addition)
        assertFalse(exists(ADD_PARCEL))
        assertFalse(exists(COVER_PANEL))
    }

    @Test
    fun thePlusOpensACoverOverTheWholeWindowWithTheKeyboardOnItsFieldAndItsCloseButtonClosesIt() {
        show()
        open()
        assertNotNull(deliveries.addition)
        assertEquals("add-parcel", source.views.last())
        // The whole window, over the tab bar.
        val window = compose.onRoot().getUnclippedBoundsInRoot()
        assertEquals(window, node(COVER_PANEL).getUnclippedBoundsInRoot())
        compose.onNode(hasTestTag(ADD_TITLE) and isHeading()).assertTextEquals("Add a parcel")
        // Above the form, which scrolls under it.
        compose.onNode(hasTestTag(ADD_TITLE) and hasAnyAncestor(hasScrollAction())).assertDoesNotExist()
        node(ADD_TRACKING).assertIsFocused()
        node(ADD_SAVE).assertIsNotEnabled()
        assertEquals(text("app.addParcel"), description(ADD_SAVE))
        assertTrue(deliveries.modal)
        node(ADD_CLOSE).performClick()
        settle()
        assertClosed()
        assertEquals("deliveries", source.views.last())
        assertFalse(deliveries.modal)
    }

    @Test
    fun aNumberAClaimingCarrierKnowsIsAddedWithItsNameAndItsCardShownOnceAddHasGone() {
        show()
        open()
        type("1Z999AA10123456784")
        assertEquals("UPS, ${text("add.detectedCarrier")}", description(ADD_CARRIER))
        assertEquals(text("add.line.change"), value(ADD_CARRIER))
        // UPS needs no check: nothing is asked.
        leave()
        assertEquals(emptyList<String>(), source.detected)
        node(ADD_NAME).performTextInput("Running shoes")
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()
        assertClosed()
        assertEquals(listOf(FakeDeliveries.AdditionRequest("1Z999AA10123456784", "Running shoes", CarrierId.Ups, null, null, null)), source.additions)
        compose.onNode(hasText("Running shoes"), useUnmergedTree = true).assertExists()
        assertNull(deliveries.revealParcel)
    }

    @Test
    fun theCardAddedIsShownEvenWhereTheSearchWouldHideIt() {
        show()
        compose.runOnUiThread { deliveries.query = "lamp" }
        settle()
        open()
        type("1Z999AA10123456784")
        node(ADD_NAME).performTextInput("Running shoes")
        node(ADD_SAVE).performClick()
        settle()
        assertFalse(deliveries.hasCustomView)
        compose.onNode(hasText("Running shoes"), useUnmergedTree = true).assertExists()
    }

    @Test
    fun anAmbiguousNumberIsAskedAboutOnceTheFieldIsLeftAndTheLineSaysWhatTheCarriersFound() {
        val number = "12345478901"
        val asked = listOf(CarrierId.GlsCh, CarrierId.GlsDe)
        val ticking = FeelingView(compose.activity)
        show(ticking = ticking) {
            detections[number] = CarrierDetectionResponse(number, carrier = CarrierId.GlsCh, asked = asked)
            detecting = CompletableDeferred()
        }
        open()
        type(number)
        // Not while it is typed.
        assertEquals(emptyList<String>(), source.detected)
        assertEquals(text("add.detect"), description(ADD_CARRIER))
        leave()
        assertEquals(listOf(number), source.detected)
        assertTrue(exists(ADD_CHECKING))
        assertEquals("${text("add.detect")}, ${text("add.checkingServices")}", description(ADD_CARRIER))
        // The check never holds Add.
        node(ADD_SAVE).assertIsEnabled()
        assertEquals(emptyList<Int>(), ticking.played)

        source.detecting?.complete(Unit)
        settle()
        assertEquals("GLS Switzerland, ${text("add.line.found")}", description(ADD_CARRIER))
        assertEquals(listOf(hapticFeedbackConstant(SensoryFeedback.Selection)), ticking.played)
        // GLS needs the delivery postcode: Add waits for it.
        assertTrue(exists(ADD_POSTCODE))
        node(ADD_SAVE).assertIsNotEnabled()
        node(ADD_POSTCODE).performTextInput("8000")
        compose.waitForIdle()
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(listOf(FakeDeliveries.AdditionRequest(number, "", CarrierId.GlsCh, null, "8000", null)), source.additions)
    }

    @Test
    fun aCheckWithoutAnAnswerSaysItFailedAndIsNotAskedAgainForTheSameNumber() {
        val number = "12345478901"
        show()
        open()
        type(number)
        leave()
        assertEquals("${text("add.detect")}, ${text("add.line.failed")}", description(ADD_CARRIER))
        assertEquals(text("add.line.change"), value(ADD_CARRIER))
        node(ADD_SAVE).assertIsEnabled()
        type("$number ")
        node(ADD_TRACKING).performClick()
        leave()
        assertEquals(listOf(number), source.detected)
    }

    @Test
    fun severalCarriersFoundAreNamedOnTheLineAndOneIsChosenInThePicker() {
        val number = "12345478901"
        val asked = listOf(CarrierId.GlsCh, CarrierId.GlsDe)
        show { detections[number] = CarrierDetectionResponse(number, carrier = CarrierId.Unknown, recognized = asked, asked = asked) }
        open()
        type(number)
        leave()
        assertEquals(text("add.line.choose"), value(ADD_CARRIER))
        val detail = node(ADD_CARRIER).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)!!.single()
        assertTrue(detail, detail.contains("GLS Switzerland") && detail.contains(" and "))
        node(ADD_CARRIER).performClick()
        settle()
        node(ADD_PICKER).assertExists()
        // Under the picker, TalkBack reaches only the picker.
        compose.onNodeWithTag(ADD_TITLE).assertDoesNotExist()
        node(carrierPickerRowTag(CarrierId.GlsDe)).assertExists()
        compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.GlsDe)).onFirst().performClick()
        settle()
        assertFalse(exists(ADD_PICKER))
        assertEquals("GLS Germany, ${text("add.line.chosen")}", description(ADD_CARRIER))
    }

    @Test
    fun thePickersCancelCloseItAndEscapeLeavesAddUnderIt() {
        show()
        open()
        type("1Z999AA10123456784")
        node(ADD_CARRIER).performClick()
        settle()
        node(ADD_PICKER).assertExists()
        press(KeyEvent.KEYCODE_ESCAPE)
        assertNotNull(deliveries.addition)
        node(ADD_PICKER_CANCEL).assertTextEquals(text("common.cancel")).performClick()
        settle()
        assertFalse(exists(ADD_PICKER))
        assertTrue(exists(ADD_TITLE))
        assertEquals("UPS, ${text("add.detectedCarrier")}", description(ADD_CARRIER))
        // Escape closes Add once the picker has gone.
        press(KeyEvent.KEYCODE_ESCAPE)
        assertClosed()
    }

    @Test
    fun severalNumbersAreListedTheFirstChosenAndAnotherCanBe() {
        show()
        open()
        type("UPS 1Z999AA10123456784\nUPS 1Z999AA10123456793")
        node(ADD_SEVERAL).assertTextEquals(text("door.several.title.many", mapOf("count" to 2)))
        node(numberRowTag("1Z999AA10123456784")).assertIsSelected()
        node(numberRowTag("1Z999AA10123456793")).assertIsNotSelected()
        assertEquals(text("door.several.track"), description(ADD_SAVE))
        // Signed in, they are kept together already.
        assertFalse(exists(ADD_SIGN_IN))
        node(numberRowTag("1Z999AA10123456793")).performClick()
        settle()
        node(numberRowTag("1Z999AA10123456793")).assertIsSelected()
        node(ADD_SAVE).performClick()
        settle()
        assertEquals("1Z999AA10123456793", source.additions.single().trackingNumber)
    }

    @Test
    fun aGuestWithSeveralNumbersIsOfferedToSignIn() {
        show { isGuest = true }
        open()
        type("UPS 1Z999AA10123456784\nUPS 1Z999AA10123456793")
        node(ADD_SIGN_IN).performClick()
        settle()
        assertClosed()
        assertEquals(1, source.signIns)
    }

    @Test
    fun numbersHeldBackSayWhyOnceSettledAndAddWaits() {
        show()
        open()
        type("hello there")
        assertTrue(exists(ADD_NOT_FOUND))
        node(ADD_SAVE).assertIsNotEnabled()

        type("204 617")
        assertFalse(exists(ADD_PICKUP_CODE))
        leave()
        assertTrue(exists(ADD_PICKUP_CODE))
        node(ADD_SAVE).assertIsNotEnabled()

        type("RR123456785CH")
        leave()
        assertTrue(exists(ADD_PLACEHOLDER))
        node(ADD_SAVE).assertIsNotEnabled()

        type("402-9182736-4554637")
        leave()
        assertTrue(exists(ADD_ORDER))
        assertTrue(exists(ADD_AMAZON_ORDERS))
        node(ADD_SAVE).assertIsNotEnabled()
    }

    @Test
    fun aCheckDigitThatDoesNotAddUpOffersTheNumberItMayBeOrAddsItAsTyped() {
        show()
        open()
        type("LX1234567B5DE")
        leave()
        assertTrue(exists(ADD_TYPO))
        assertEquals(text("door.typo.suggest", mapOf("number" to "LX123456785DE")), description(ADD_SUGGESTION))
        node(ADD_SUGGESTION).performClick()
        settle()
        assertEquals("LX123456785DE", fieldText(ADD_TRACKING))
        assertFalse(exists(ADD_TYPO))

        type("LX1234567B5DE")
        leave()
        node(ADD_AS_TYPED).performClick()
        settle()
        assertClosed()
        assertEquals("LX1234567B5DE", source.additions.single().trackingNumber)
    }

    @Test
    fun anAmazonNumberWaitsForAmazonAndCanBeCheckedAgain() {
        val number = "FR4829173650"
        show()
        open()
        type(number)
        leave()
        // Amazon couldn't answer: ask again.
        assertTrue(exists(ADD_AMAZON_UNAVAILABLE))
        node(ADD_SAVE).assertIsNotEnabled()
        // An Amazon number keeps its carrier.
        assertTrue(node(ADD_CARRIER).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Disabled) != null)
        source.detections[number] = CarrierDetectionResponse(
            number, CarrierDetectionResponseAmazonShippingStatus.Available, carrier = CarrierId.AmazonShipping,
        )
        node(ADD_AMAZON_RETRY).performClick()
        settle()
        assertFalse(exists(ADD_AMAZON_UNAVAILABLE))
        node(ADD_HINT).assertTextEquals(text("add.amazonShippingConfirmed"))
        node(ADD_SAVE).assertIsEnabled()
    }

    @Test
    fun anAmazonNumberOnlyAnAccountFollowsOffersItsOrdersAndNoAdd() {
        val number = "FR4829173650"
        show { detections[number] = CarrierDetectionResponse(number, carrier = CarrierId.AmazonLogistics) }
        open()
        type(number)
        settle()
        assertTrue(exists(ADD_AMAZON_ORDERS))
        node(ADD_SAVE).assertIsNotEnabled()
    }

    @Test
    fun aCarrierThatFollowsALinkAsksForASecureOne() {
        show()
        open()
        type("9999012345678")
        assertTrue(exists(ADD_TRACKING_URL))
        node(ADD_SAVE).assertIsNotEnabled()
        node(ADD_TRACKING_URL).performTextInput("http://trackandtrace.planzergroup.com/shared/abc")
        compose.waitForIdle()
        // Only a secure link.
        node(ADD_SAVE).assertIsNotEnabled()
        node(ADD_TRACKING_URL).performTextReplacement("https://trackandtrace.planzergroup.com/shared/abc?accessKey=xyz")
        compose.waitForIdle()
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(
            listOf(FakeDeliveries.AdditionRequest("9999012345678", "", CarrierId.Planzer, "https://trackandtrace.planzergroup.com/shared/abc?accessKey=xyz", null, null)),
            source.additions,
        )
    }

    @Test
    fun aRequiredPostcodeStartsFromTheLastGivenWhichAGuestCanForget() {
        val number = "12345478901"
        show {
            isGuest = true
            detections[number] = CarrierDetectionResponse(number, carrier = CarrierId.GlsCh)
            givenPostcodes = listOf(GivenPostcode(carrier = CarrierId.GlsCh, postcode = "3011"))
        }
        open()
        type(number)
        leave()
        assertEquals("3011", fieldText(ADD_POSTCODE))
        node(ADD_FORGET_POSTCODE).performClick()
        settle()
        assertEquals("", fieldText(ADD_POSTCODE))
        assertEquals(listOf("3011"), source.forgottenPostcodes)
        assertFalse(exists(ADD_FORGET_POSTCODE))
    }

    @Test
    fun aPostcodeFromAnotherCountryIsSentWithIt() {
        show()
        open()
        type("76434219")
        node(ADD_CARRIER).performClick()
        settle()
        node(CARRIER_PICKER_SEARCH).performTextInput("Mondial")
        compose.waitForIdle()
        compose.onAllNodesWithTag(carrierPickerRowTag(CarrierId.MondialRelay)).onFirst().performClick()
        settle()
        assertEquals("Switzerland", value(ADD_COUNTRY))
        node(ADD_COUNTRY).performClick()
        settle()
        node(postcodeCountryTag("BE")).performClick()
        settle()
        assertEquals("Belgium", value(ADD_COUNTRY))
        node(ADD_POSTCODE).performTextInput("1000")
        compose.waitForIdle()
        node(ADD_SAVE).assertIsEnabled().performClick()
        settle()
        assertEquals(listOf(FakeDeliveries.AdditionRequest("76434219", "", CarrierId.MondialRelay, null, "1000", "BE")), source.additions)
    }

    @Test
    fun theNameTakesEightyCharacters() {
        show()
        open()
        node(ADD_NAME).performTextInput("a".repeat(90))
        compose.waitForIdle()
        assertEquals("a".repeat(80), fieldText(ADD_NAME))
    }

    @Test
    fun whileItSavesNothingClosesAddAndOnceSavedItGoesDownSayingSo() {
        val adding = CompletableDeferred<Unit>()
        show { this.adding = adding }
        open()
        type("1Z999AA10123456784")
        node(ADD_SAVE).performClick()
        settle()
        assertEquals(text("add.adding"), description(ADD_SAVE))
        node(ADD_SAVE).assertIsNotEnabled()
        node(ADD_CLOSE).assertIsNotEnabled()
        back()
        press(KeyEvent.KEYCODE_ESCAPE)
        assertNotNull(deliveries.addition)

        compose.mainClock.autoAdvance = false
        adding.complete(Unit)
        var frames = 0
        while (deliveries.addition != null && frames++ < 60) compose.mainClock.advanceTimeBy(16)
        assertNull(deliveries.addition)
        compose.mainClock.advanceTimeBy(100)
        // On its way down, it still says so.
        assertEquals(text("add.adding"), description(ADD_SAVE, unmerged = true))
        compose.mainClock.autoAdvance = true
        settle()
        assertClosed()
    }

    @Test
    fun aFailedSaveSaysWhyUntilTheNumberChanges() {
        show { failure = IllegalStateException("No connection") }
        open()
        type("1Z999AA10123456784")
        node(ADD_SAVE).performClick()
        settle()
        assertTrue(exists(ADD_ERROR))
        compose.onNode(hasText("No connection")).assertExists()
        assertFalse(exists(ADD_OPEN_EXISTING))
        node(ADD_SAVE).assertIsEnabled()
        type("1Z999AA1012345678")
        settle()
        assertFalse(exists(ADD_ERROR))
    }

    @Test
    fun aNumberInTheBoxAlreadyOpensTheExistingParcel() {
        show { failure = DeliveryApiError.DuplicateTracking(lamp.id) }
        open()
        type("1Z999AA10123456784")
        node(ADD_SAVE).performClick()
        settle()
        node(ADD_OPEN_EXISTING).performClick()
        settle()
        assertClosed()
        assertEquals(lamp.id, deliveries.navigator.navigation.shown)
    }

    @Test
    fun pasteFillsTheFieldFromTheClipboardOrSaysNothingCame() {
        show()
        open()
        clipboard(null)
        node(ADD_PASTE).performClick()
        settle()
        compose.onNode(hasText(text("add.pasteFailed"))).assertExists()
        clipboard("  Your parcel 1Z999AA10123456784 is on its way\n")
        node(ADD_PASTE).performClick()
        settle()
        assertFalse(exists(ADD_ERROR))
        assertEquals("Your parcel 1Z999AA10123456784 is on its way", fieldText(ADD_TRACKING))
        assertTrue(exists(ADD_FOUND))
        node(ADD_TRACKING).assertIsFocused()
        assertEquals(listOf("parcel-paste", "parcel-paste"), source.actions.filter { it == "parcel-paste" })
    }

    @Test
    fun theEmptyListsPasteOpensAddWithTheClipboardAndItsAddOpensItEmpty() {
        show(list = emptyList())
        clipboard("1Z999AA10123456784")
        node(EMPTY_PASTE).performClick()
        settle()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("UPS, ${text("add.detectedCarrier")}", description(ADD_CARRIER))
        assertEquals(listOf("parcel-paste"), source.actions.filter { it == "parcel-paste" })
        node(ADD_CLOSE).performClick()
        settle()
        node(EMPTY_ADD).performClick()
        settle()
        assertEquals("", fieldText(ADD_TRACKING))
    }

    @Test
    fun textDroppedOnTheListOpensAddWithItAndTextWithoutANumberIsTurnedAway() {
        show()
        var taken = true
        compose.runOnUiThread { taken = deliveries.addDropped(listOf("hello there")) }
        assertFalse(taken)
        assertNull(deliveries.addition)
        compose.runOnUiThread { taken = deliveries.addDropped(listOf("UPS 1Z999AA10123456784", "UPS 1Z999AA10123456784")) }
        settle()
        assertTrue(taken)
        assertEquals("UPS 1Z999AA10123456784", fieldText(ADD_TRACKING))
    }

    @Test
    fun ctrlNOpensAddAndEscapeClosesIt() {
        show()
        press(KeyEvent.KEYCODE_N, KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON)
        assertNotNull(deliveries.addition)
        press(KeyEvent.KEYCODE_ESCAPE)
        assertClosed()
        open()
        back()
        assertClosed()
    }

    @Test
    fun theFieldsOpenTheirKeyboardsAndNextAndDoneMoveOn() {
        show()
        open()
        assertTextWithoutSuggestions(compose.editorInfo(), EditorInfo.IME_ACTION_NEXT, InputType.TYPE_TEXT_VARIATION_NORMAL)
        type("1Z999AA10123456784")
        press(KeyEvent.KEYCODE_ENTER)
        node(ADD_NAME).assertIsFocused()
        assertEquals(EditorInfo.IME_ACTION_DONE, compose.editorInfo().imeOptions and EditorInfo.IME_MASK_ACTION)
        assertTrue(compose.editorInfo().inputType and InputType.TYPE_TEXT_FLAG_CAP_SENTENCES != 0)
        press(KeyEvent.KEYCODE_ENTER)
        assertClosed()
        assertEquals(1, source.additions.size)
    }

    @Test
    fun whatWasTypedTheFocusAndThePickerStayThroughTheWindowMadeAgain() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        open()
        type("1Z999AA1012345")
        settle()
        recreation.recreate()
        settle()
        node(ADD_TRACKING).assertIsFocused()
        assertEquals("1Z999AA1012345", fieldText(ADD_TRACKING))
        node(ADD_TRACKING).performTextInput("6784")
        node(ADD_CARRIER).performClick()
        settle()
        recreation.recreate()
        settle()
        node(ADD_PICKER).assertExists()
        node(ADD_PICKER_CANCEL).performClick()
        settle()
        assertEquals("1Z999AA10123456784", fieldText(ADD_TRACKING))
        assertEquals("UPS, ${text("add.detectedCarrier")}", description(ADD_CARRIER))
    }

    @Test
    fun atTheAccessibilitySizesTheTitleScrollsWithTheForm() {
        show(fontScale = 2f)
        open()
        compose.onNode(hasTestTag(ADD_TITLE) and hasAnyAncestor(hasScrollAction())).assertExists()
        // The carrier line's words have it to themselves, the way to change them under.
        type("1Z999AA10123456784")
        val line = node(ADD_CARRIER).getUnclippedBoundsInRoot()
        assertTrue("${line.height}", line.height.value > 60f)
    }

    @Test
    fun underReduceMotionRowsAndValuesComeAtOnce() {
        show(reduceMotion = true)
        open()
        val before = node(ADD_NAME).getUnclippedBoundsInRoot().top
        compose.mainClock.autoAdvance = false
        node(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.mainClock.advanceTimeBy(32)
        val now = node(ADD_NAME).getUnclippedBoundsInRoot().top
        compose.mainClock.advanceTimeBy(1_000)
        val after = node(ADD_NAME).getUnclippedBoundsInRoot().top
        assertTrue("$before $after", after > before)
        assertEquals(after.value, now.value, 0.5f)
        compose.mainClock.autoAdvance = true
    }

    @Test
    fun theFeedbackRisesOnTheSnappySpringAndTheButtonEasesIn() {
        show(reduceMotion = false)
        open()
        val before = node(ADD_NAME).getUnclippedBoundsInRoot().top
        val dim = buttonShade()
        compose.mainClock.autoAdvance = false
        node(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.mainClock.advanceTimeBy(80)
        val moving = node(ADD_NAME).getUnclippedBoundsInRoot().top
        val easing = buttonShade()
        compose.mainClock.advanceTimeBy(1_000)
        val after = node(ADD_NAME).getUnclippedBoundsInRoot().top
        val full = buttonShade()
        compose.mainClock.autoAdvance = true
        assertTrue("$before $moving $after", moving > before && moving < after)
        // The accent fills in: darker as Add can add.
        assertTrue("$dim $easing $full", easing < dim && easing > full)
    }

    /** How light Add's button is in its middle, 0 black to 3 white. */
    private fun buttonShade(): Float {
        compose.waitForIdle()
        val image = node(ADD_SAVE).captureToImage().toPixelMap()
        val pixel = image[image.width / 6, image.height / 2]
        return pixel.red + pixel.green + pixel.blue
    }

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun sideBySideAddIsAFormInTheWindowsMiddle() {
        show()
        assertTrue(deliveries.sheetsAsForms)
        open()
        assertFalse(exists(COVER_PANEL))
        val panel = node(SHEET_PANEL).getUnclippedBoundsInRoot()
        assertEquals(540f, panel.width.value, 1f)
        node(ADD_TRACKING).assertIsFocused()
        // Closed by its button, and the sheets keep their arrangement meanwhile.
        node(ADD_CLOSE).performClick()
        settle()
        assertClosed()
    }
}

/** A window's view that keeps each haptic feedback asked of it. */
private class FeelingView(context: Context) : View(context) {
    val played = mutableListOf<Int>()

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
        played += feedbackConstant
        return true
    }
}
