package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.CarrierRequirement
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponseAmazonShippingStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.READER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_AMAZON_ORDERS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_AMAZON_RETRY
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_AMAZON_UNAVAILABLE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_AS_TYPED
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CARRIER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_FOUND
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_HINT
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_LINE_DETAIL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_LINE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NOT_FOUND
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_OPEN_EXISTING
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ORDER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PASTE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PICKUP_CODE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SEVERAL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SIGN_IN
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SUGGESTION
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING_URL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TYPO
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.numberRowTag
import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's states on this device's own window (B-ST17, B-ST20, B-ST21, B-ST22, B-ST23, B-ST25), as AddParcelView's
 * `trackingField` notes, its `carrierFeedback` and Amazon's check, its `numberChoice`, the link `requiredDetails` asks
 * for, `save()` with its `errorBanner`, and its layout at the largest text. Signed in as the synthetic
 * reader@example.com, as a guest, or in the demo, on fake deliveries ([ParcelWalk]) whose stores answer every read in
 * memory, Amazon's check among them; the numbers are synthetic or public samples, and nothing goes to the network. The
 * fields themselves and the postcodes are [AddFieldsDeviceTest]'s and [AddPostcodesDeviceTest]'s. Each saved as
 * addstates/<row>-<moment>.png in the test package's external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class AddStatesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /**
     * Add opened from the + over `parcels` in the deliveries of `session` set up by `prepare`; once its tracking field
     * has taken the focus where `focused`.
     */
    private fun openAdd(
        parcels: List<Parcel> = listOf(lamp),
        session: AuthenticationState = READER,
        focused: Boolean = true,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        walk.show(parcels, session = session, prepare = prepare)
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        if (focused) awaitFocus(ADD_TRACKING)
        walk.awaitKeyboard(required = false)
    }

    private fun focused(tag: String): Boolean = compose.onAllNodes(hasTestTag(tag) and isFocused()).fetchSemanticsNodes().isNotEmpty()

    private fun awaitFocus(tag: String) {
        compose.waitUntil(LONG_MILLIS) { focused(tag) }
        compose.waitForIdle()
    }

    /** `text` in the tracking field, as typed, then a second of the app's time. */
    private fun type(text: String) {
        walk.node(ADD_TRACKING).performTextReplacement(text)
        walk.settle()
    }

    /** The tracking field left for the name, as a finger leaves it, which settles its number. */
    private fun leave() {
        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        walk.settle()
    }

    /** `text` typed in the tracking field, tapped first where the focus is elsewhere, then the field left. */
    private fun typeAndLeave(text: String) {
        if (!focused(ADD_TRACKING)) {
            walk.tap(ADD_TRACKING)
            awaitFocus(ADD_TRACKING)
        }
        type(text)
        leave()
    }

    /** Add tapped, then a second of the app's time. */
    private fun tapAdd() {
        walk.tap(ADD_SAVE)
        walk.settle()
    }

    /** Add saved: the parcels it asked for. */
    private fun save(): List<FakeDeliveries.AdditionRequest> {
        tapAdd()
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.additions.isNotEmpty() } }
        return walk.onUi { walk.source.additions.toList() }
    }

    /** What TalkBack reads the part tagged `tag` by, from its own node. */
    private fun description(tag: String): List<String>? =
        compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)

    /** What the carrier line reads as, its parts one after the other. */
    private fun line(): String? = description(ADD_CARRIER)?.joinToString()

    /** The words under the carrier line on how the carrier tracks, or what Amazon said; null where there are none. */
    private fun hint(): String? = if (walk.exists(ADD_HINT)) walk.texts(ADD_HINT).singleOrNull() else null

    /** The numbers Amazon's check (or the carriers') was asked about, in their order. */
    private fun asked(): List<String> = walk.onUi { walk.source.detected.toList() }

    private fun selected(tag: String): Boolean = walk.node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Selected) == true

    /** The carrier's name as the carrier line and its hint give it. */
    private fun carrierName(carrier: CarrierId): String = CarrierCatalog.shared.info(carrier, walk.language).displayName

    /**
     * Where what is tagged `tag` stands in the window, whole where the keyboard or the window's foot clips it, inside a
     * part that reads as one or not.
     */
    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag, useUnmergedTree = true).getUnclippedBoundsInRoot()

    // B-ST17: what the field holds.

    /**
     * B-ST17: empty, the field has no note and no carrier line, and Add is off; words with no number say
     * "add.notFound" at once; a pickup code's 6 digits say "add.pickupCode.title" and its words only once the field is
     * left, Add off; a check digit that doesn't add up says "door.typo.check" and "door.typo.letter" once left, with
     * "door.typo.suggest" naming the number it may be, which a tap puts in the field (AddParcelView's `trackingField`,
     * `heldBackNote`, `use(_:)`).
     */
    @Test
    fun theFieldSaysItHoldsNoNumberAPickupCodeOrACheckDigitThatDoesNotAddUp() {
        openAdd()
        assertEquals("", walk.editable(ADD_TRACKING))
        assertFalse("No carrier line while empty", walk.exists(ADD_CARRIER))
        assertFalse(walk.exists(ADD_NOT_FOUND))
        walk.node(ADD_SAVE).assertIsNotEnabled()

        type(NO_NUMBER)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_NOT_FOUND) }
        assertEquals(listOf(walk.text("add.notFound")), walk.texts(ADD_NOT_FOUND))
        assertFalse("No carrier line without a number", walk.exists(ADD_CARRIER))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        walk.shot("B-ST17-not-found", alsoDark = false)

        type(PICKUP)
        assertFalse("Not said while it is typed", walk.exists(ADD_PICKUP_CODE))
        leave()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PICKUP_CODE) }
        assertEquals(listOf(walk.text("add.pickupCode.title"), walk.text("add.pickupCode.body")), walk.texts(ADD_PICKUP_CODE))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        compose.reveal(ADD_PICKUP_CODE)
        walk.shot("B-ST17-pickup-code", alsoDark = false)

        typeAndLeave(TYPO)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_TYPO) }
        assertEquals(listOf(walk.text("door.typo.check"), walk.text("door.typo.letter")), walk.texts(ADD_TYPO))
        assertEquals(listOf(walk.text("door.typo.suggest", "number" to SUGGESTED)), description(ADD_SUGGESTION))
        assertTrue("Track it as typed offered beside it", walk.exists(ADD_AS_TYPED))
        compose.reveal(ADD_AS_TYPED)
        walk.shot("B-ST17-typo", alsoDark = false)
        walk.tap(ADD_SUGGESTION)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.editable(ADD_TRACKING) == SUGGESTED }
        assertFalse(walk.exists(ADD_TYPO))
    }

    /**
     * B-ST17: each shop's order number (Amazon's, Shein's, Shopee's and Temu's samples in Reading.json) says
     * "door.order.<shop>.title" and its words once the field is left, not "add.notFound"; only Amazon's offers
     * "add.openAmazonOrders"; Add stays off (`trackingField`'s order note).
     */
    @Test
    fun eachShopsOrderNumberSaysWhichShopItIsFromOnceLeftAndAddStaysOff() {
        openAdd()
        for ((shop, number) in ORDERS) {
            val title = walk.text("door.order.$shop.title")
            typeAndLeave(number)
            compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_ORDER) && walk.texts(ADD_ORDER).firstOrNull() == title }
            assertEquals(shop, listOf(title, walk.text("door.order.$shop.body")), walk.texts(ADD_ORDER))
            assertFalse("$shop: a number, though not a tracking one", walk.exists(ADD_NOT_FOUND))
            assertEquals("$shop: Amazon's orders offered for Amazon's alone", shop == "amazon", walk.exists(ADD_AMAZON_ORDERS))
            walk.node(ADD_SAVE).assertIsNotEnabled()
            if (shop == "amazon" || shop == "temu") {
                compose.reveal(ADD_ORDER)
                walk.shot("B-ST17-order-$shop", alsoDark = false)
            }
        }
    }

    /**
     * B-ST17: a number found in a link says "add.foundLink" with the number as printed, drawn with the barcode under
     * the carrier line, and one found in a text says "add.foundText"; either can be added (`carrierFeedback`'s
     * barcode label).
     */
    @Test
    fun aNumberFoundInALinkOrATextShowsWithTheBarcodeUnderTheCarrierLine() {
        openAdd()
        val printed = CarrierCatalog.format(UPS, CarrierId.Ups)
        type(UPS_LINK)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_FOUND) }
        assertEquals(listOf(walk.text("add.foundLink", "number" to printed)), description(ADD_FOUND))
        assertTrue("The number drawn as printed", walk.shows(ADD_FOUND, printed))
        walk.node(ADD_SAVE).assertIsEnabled()
        compose.reveal(ADD_FOUND)
        walk.shot("B-ST17-found-link", alsoDark = false)

        type(UPS_TEXT)
        compose.waitUntil(LONG_MILLIS) { description(ADD_FOUND) == listOf(walk.text("add.foundText", "number" to printed)) }
        walk.node(ADD_SAVE).assertIsEnabled()
    }

    // B-ST20: Amazon's numbers.

    /**
     * B-ST20: an Amazon number Amazon Shipping confirms is asked about once, says "add.amazonShippingConfirmed",
     * offers no orders and is added as Amazon Shipping; one whose history has expired says "add.amazonHistoryExpired"
     * and can be added too (`lookupTrackingNumber`, `shippingConfirmed`, `resolvedCarrier`).
     */
    @Test
    fun anAmazonNumberAmazonShippingConfirmsIsAddedAsAmazonShippingAndAnExpiredOneSaysSo() {
        openAdd {
            detections[AMAZON] = CarrierDetectionResponse(AMAZON, CarrierDetectionResponseAmazonShippingStatus.Available, carrier = CarrierId.AmazonShipping)
            detections[EXPIRED] = CarrierDetectionResponse(EXPIRED, CarrierDetectionResponseAmazonShippingStatus.Expired, carrier = CarrierId.AmazonShipping)
        }
        type(EXPIRED)
        compose.waitUntil(LONG_MILLIS) { hint() == walk.text("add.amazonHistoryExpired") }
        walk.node(ADD_SAVE).assertIsEnabled()
        compose.reveal(ADD_HINT)
        walk.shot("B-ST20-expired", alsoDark = false)

        type(AMAZON)
        compose.waitUntil(LONG_MILLIS) { hint() == walk.text("add.amazonShippingConfirmed") }
        assertEquals("Asked once each", listOf(EXPIRED, AMAZON), asked())
        assertEquals("${carrierName(CarrierId.AmazonShipping)}, ${walk.text("add.detectedCarrier")}", line())
        assertFalse("No orders to open once Amazon Shipping follows it", walk.exists(ADD_AMAZON_ORDERS))
        walk.node(ADD_SAVE).assertIsEnabled()
        compose.reveal(ADD_HINT)
        walk.shot("B-ST20-shipping", alsoDark = false)
        val added = save().single()
        assertEquals(AMAZON, added.trackingNumber)
        assertEquals(CarrierId.AmazonShipping, added.carrier)
    }

    /**
     * B-ST20: an Amazon number only an account follows stays Amazon Logistics, says "add.amazonAccount" and offers
     * "add.openAmazonOrders", Add off; where Amazon's check can't answer, "add.amazonCheckUnavailable" with
     * "add.amazonRetry", Add off, and the retry asks again (`carrierFeedback`, `accountRequired`, `lookupAttempt`).
     */
    @Test
    fun anAmazonNumberOnlyAnAccountFollowsOffersItsOrdersAndAnUnansweredCheckIsAskedAgain() {
        openAdd { detections[LOGISTICS] = CarrierDetectionResponse(LOGISTICS, carrier = CarrierId.AmazonLogistics) }
        type(LOGISTICS)
        compose.waitUntil(LONG_MILLIS) { LOGISTICS in asked() }
        walk.settle()
        assertEquals(listOf(walk.text("add.openAmazonOrders")), walk.texts(ADD_AMAZON_ORDERS))
        assertEquals(walk.text("add.amazonAccount"), hint())
        assertEquals("${carrierName(CarrierId.AmazonLogistics)}, ${walk.text("add.detectedCarrier")}", line())
        assertFalse(walk.exists(ADD_AMAZON_UNAVAILABLE))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        compose.reveal(ADD_AMAZON_ORDERS)
        walk.shot("B-ST20-account", alsoDark = false)

        // No answer for this one: the fake's check fails, as a lookup without one does.
        type(AMAZON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_AMAZON_UNAVAILABLE) }
        assertEquals(listOf(walk.text("add.amazonCheckUnavailable")), walk.texts(ADD_AMAZON_UNAVAILABLE))
        assertEquals(listOf(walk.text("add.amazonRetry")), walk.texts(ADD_AMAZON_RETRY))
        assertTrue("Its orders still offered", walk.exists(ADD_AMAZON_ORDERS))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        compose.reveal(ADD_AMAZON_RETRY)
        walk.shot("B-ST20-unavailable", alsoDark = false)

        walk.onUi {
            walk.source.detections[AMAZON] = CarrierDetectionResponse(AMAZON, CarrierDetectionResponseAmazonShippingStatus.Available, carrier = CarrierId.AmazonShipping)
        }
        walk.tap(ADD_AMAZON_RETRY)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { hint() == walk.text("add.amazonShippingConfirmed") }
        assertFalse(walk.exists(ADD_AMAZON_UNAVAILABLE))
        assertEquals("Asked again", 2, asked().count { it == AMAZON })
        walk.node(ADD_SAVE).assertIsEnabled()
    }

    /**
     * B-ST20: in the demo nobody is asked about an Amazon number: it stays Amazon Logistics, offers its orders, and
     * Add stays off (`lookupTrackingNumber`'s `store.isDemo`).
     */
    @Test
    fun inTheDemoAnAmazonNumberIsNeverLookedUp() {
        openAdd(session = AuthenticationState.Demo, focused = false)
        type(AMAZON)
        walk.settle()
        assertEquals("Nothing asked", emptyList<String>(), asked())
        assertFalse(walk.exists(ADD_AMAZON_UNAVAILABLE))
        assertTrue(walk.exists(ADD_AMAZON_ORDERS))
        assertEquals(walk.text("add.amazonAccount"), hint())
        walk.node(ADD_SAVE).assertIsNotEnabled()
        walk.shot("B-ST20-demo", alsoDark = false)
    }

    // B-ST21: several numbers.

    /**
     * B-ST21: signed in, two numbers in a text are listed under "door.several.title.many", the first chosen, with no
     * sign-in offered; Add reads "door.several.track", and the one chosen instead is the one added (`numberChoice`,
     * `choose(_:)`).
     */
    @Test
    fun signedInSeveralNumbersAreChoicesAndTheOneChosenIsTracked() {
        openAdd()
        type(SEVERAL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_SEVERAL) }
        assertEquals(listOf(walk.text("door.several.title.many", "count" to 2)), walk.texts(ADD_SEVERAL))
        assertTrue("The first chosen", selected(numberRowTag(UPS)))
        assertEquals(listOf(walk.text("door.several.track")), description(ADD_SAVE))
        assertFalse("Signed in, they are kept together already", walk.exists(ADD_SIGN_IN))
        walk.node(ADD_SAVE).assertIsEnabled()
        walk.shot("B-ST21-account", alsoDark = false)

        walk.tap(numberRowTag(SECOND))
        walk.settle()
        assertTrue(selected(numberRowTag(SECOND)))
        assertFalse(selected(numberRowTag(UPS)))
        val added = save().single()
        assertEquals(SECOND, added.trackingNumber)
        assertEquals(CarrierId.Ups, added.carrier)
    }

    /**
     * B-ST21: a guest with several numbers is asked "door.several.question" and offered "door.several.signIn", which
     * closes Add and opens the sign-in (`numberChoice`'s `store.isGuest`).
     */
    @Test
    fun aGuestWithSeveralNumbersIsAskedAboutThemAllAndOfferedToSignIn() {
        openAdd(session = AuthenticationState.Welcome, focused = false) { isGuest = true }
        type(SEVERAL)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_SIGN_IN) }
        assertTrue(walk.shows(ADD_PARCEL, walk.text("door.several.question")))
        assertEquals(listOf(walk.text("door.several.signIn")), walk.texts(ADD_SIGN_IN))
        compose.reveal(ADD_SIGN_IN)
        walk.shot("B-ST21-guest", alsoDark = false)

        walk.tap(ADD_SIGN_IN)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        assertEquals(1, walk.onUi { walk.source.signIns })
    }

    // B-ST22: the link a carrier asks for.

    /**
     * B-ST22: Planzer's number asks for "add.requirement.trackingUrl", its placeholder the catalog's, read after its
     * name while empty; a link that isn't https, or has no host, keeps Add off; a secure link turns it on and is sent
     * with the parcel (`requiredDetails`, `canSave`).
     */
    @Test
    fun aCarrierThatFollowsALinkAsksForItsWholeSecureLinkAndSendsIt() {
        openAdd()
        type(PLANZER)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_TRACKING_URL) }
        val placeholder = checkNotNull(
            CarrierCatalog.shared.requirements(CarrierId.Planzer, PLANZER).first { it.field == CarrierRequirement.Field.TrackingUrl }.placeholder,
        ) { "Planzer's link has a placeholder in the catalog" }
        val label = walk.text("add.requirement.trackingUrl")
        assertEquals(listOf("$label, $placeholder"), description(ADD_TRACKING_URL))
        assertTrue(walk.shows(ADD_PARCEL, walk.text("add.requirement.trackingUrlHelp")))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        compose.reveal(ADD_TRACKING_URL)
        walk.shot("B-ST22-link-asked", alsoDark = false)

        for (refused in listOf(PLAIN_LINK, HOSTLESS_LINK)) {
            walk.node(ADD_TRACKING_URL).performTextReplacement(refused)
            walk.settle()
            assertEquals(refused, walk.editable(ADD_TRACKING_URL))
            walk.node(ADD_SAVE).assertIsNotEnabled()
        }
        walk.node(ADD_TRACKING_URL).performTextReplacement(PLANZER_LINK)
        walk.settle()
        assertEquals("Its name alone once it holds a link", listOf(label), description(ADD_TRACKING_URL))
        walk.node(ADD_SAVE).assertIsEnabled()
        compose.reveal(ADD_TRACKING_URL)
        walk.shot("B-ST22-link-given", alsoDark = false)
        assertEquals(listOf(FakeDeliveries.AdditionRequest(PLANZER, "", CarrierId.Planzer, PLANZER_LINK, null, null)), save())
    }

    // B-ST23: saving.

    /**
     * B-ST23: while it saves, the form's controls and its close are off, Add spins beside "add.adding" and is read so,
     * off, and the back keeps Add up; once saved, Add goes (`save()`, `.disabled(saving)`,
     * `.interactiveDismissDisabled(saving)`).
     */
    @Test
    fun whileItSavesTheFormIsOffAddSpinsSayingAddingAndTheBackKeepsItUp() {
        val adding = CompletableDeferred<Unit>()
        openAdd { this.adding = adding }
        type(UPS)
        tapAdd()
        val words = walk.text("add.adding")
        compose.waitUntil(LONG_MILLIS) { description(ADD_SAVE) == listOf(words) }
        assertTrue("A spinner in the button", walk.spins(ADD_SAVE))
        assertTrue(walk.shows(ADD_SAVE, words))
        walk.node(ADD_SAVE).assertIsNotEnabled()
        walk.node(ADD_CLOSE).assertIsNotEnabled()
        for (tag in listOf(ADD_TRACKING, ADD_NAME, ADD_PASTE, ADD_CARRIER)) walk.node(tag).assertIsNotEnabled()
        assertEquals("The number as it stands", UPS, walk.editable(ADD_TRACKING))
        walk.shot("B-ST23-saving", alsoDark = false)

        walk.back()
        assertTrue("The back keeps Add up while it saves", walk.exists(ADD_PARCEL))
        assertNotNull(walk.onUi { walk.deliveries.addition })

        adding.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) }
        assertEquals(UPS, walk.onUi { walk.source.additions.single().trackingNumber })
    }

    /**
     * B-ST23: a failed save says why in the error banner ("error.connection" offline), Add on again, until the number
     * changes; a cancelled one says nothing and leaves Add as it was; a number in the box already says so with
     * "add.openExisting", which closes Add and opens that parcel (`save()`'s `CancellationError`, `errorBanner`,
     * `duplicateParcelID`).
     */
    @Test
    fun aFailedSaveSaysWhyACancelledOneNothingAndADuplicateOpensTheParcelWithTheNumber() {
        openAdd(listOf(lamp, gift))
        // Side by side the first card is open already: the parcel with the number is the other one.
        val existing = if (walk.shown() == lamp.id) gift else lamp
        walk.onUi { walk.source.failure = IOException("offline") }
        type(UPS)
        tapAdd()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_ERROR) }
        assertTrue(walk.shows(ADD_ERROR, walk.text("error.connection")))
        assertFalse(walk.exists(ADD_OPEN_EXISTING))
        walk.node(ADD_SAVE).assertIsEnabled()
        compose.reveal(ADD_ERROR)
        walk.shot("B-ST23-error", alsoDark = false)
        type(UPS.dropLast(1))
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_ERROR) }

        type(UPS)
        val held = CompletableDeferred<Unit>()
        walk.onUi {
            walk.source.adding = held
            walk.source.failure = CancellationException("Called off")
        }
        tapAdd()
        compose.waitUntil(LONG_MILLIS) { description(ADD_SAVE) == listOf(walk.text("add.adding")) }
        held.complete(Unit)
        compose.waitUntil(LONG_MILLIS) { description(ADD_SAVE) == listOf(walk.text("app.addParcel")) }
        walk.settle()
        assertFalse("Nothing said of a cancelled save", walk.exists(ADD_ERROR))
        assertTrue(walk.exists(ADD_PARCEL))
        assertEquals(UPS, walk.editable(ADD_TRACKING))
        walk.node(ADD_SAVE).assertIsEnabled()

        walk.onUi {
            walk.source.adding = null
            walk.source.failure = DeliveryApiError.DuplicateTracking(existing.id)
        }
        tapAdd()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_OPEN_EXISTING) }
        assertTrue(walk.shows(ADD_ERROR, walk.text("native.error.duplicateTracking")))
        assertEquals(listOf(walk.text("add.openExisting")), walk.texts(ADD_OPEN_EXISTING))
        compose.reveal(ADD_OPEN_EXISTING)
        walk.shot("B-ST23-duplicate", alsoDark = false)
        walk.tap(ADD_OPEN_EXISTING)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PARCEL) && walk.shown() == existing.id }
        assertTrue("Nothing added", walk.onUi { walk.source.additions.isEmpty() })
    }

    // B-ST25: the largest text.

    /**
     * B-ST25: at the largest text the title scrolls with the form, the carrier line stands its name over what it says,
     * and Add's words, "door.several.track" among several numbers, keep to two lines in a button 50 tall at least that
     * holds them (AddParcelView's `body`, `carrierLine`, `addButton`'s `.lineLimit(2)`; the button's sizes are
     * [AddButtonLargeTextDeviceTest]'s).
     */
    @Test
    fun atTheLargestTextTheTitleScrollsTheCarrierLineStacksAndAddsWordsKeepToTwoLines() {
        walk.fontScale = 2f
        openAdd()
        compose.onNode(hasTestTag(ADD_TITLE) and hasAnyAncestor(hasScrollAction())).assertExists()

        type(UPS)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_LINE_DETAIL) }
        assertFalse("The name in a column of its own, not beside what it says", walk.exists(ADD_LINE_NAME))
        val line = bounds(ADD_CARRIER)
        val detail = bounds(ADD_LINE_DETAIL)
        assertTrue("The line grows round its lines: ${line.height}", line.height > 60.dp)
        assertTrue("What it says under the name: ${detail.top - line.top} below the line's top", detail.top - line.top >= 20.dp)
        compose.reveal(ADD_CARRIER)
        walk.shot("B-ST25-carrier-line", alsoDark = false)

        type(SEVERAL)
        val words = walk.text("door.several.track")
        compose.waitUntil(LONG_MILLIS) { description(ADD_SAVE) == listOf(words) }
        val drawn = compose.onAllNodes(hasText(words) and hasAnyAncestor(hasTestTag(ADD_SAVE)), useUnmergedTree = true)
            .fetchSemanticsNodes().filter { it.layoutInfo.isPlaced }
        val node = drawn.singleOrNull() ?: throw AssertionError("Add's words drawn once, not ${drawn.size} times")
        val layout = walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
        assertEquals("Two lines at most", 2, layout.layoutInput.maxLines)
        assertTrue("On ${layout.lineCount} lines", layout.lineCount <= 2)
        walk.node(ADD_SAVE).assertHeightIsAtLeast(50.dp)
        assertTrue("The button holds its words", walk.node(ADD_SAVE).fetchSemanticsNode().size.height >= layout.size.height)
        walk.shot("B-ST25-button", alsoDark = false)
    }

    private companion object {
        const val FOLDER = "addstates"

        /** UPS's public sample number, and the one after it. */
        const val UPS = "1Z999AA10123456784"
        const val SECOND = "1Z999AA10123456793"

        const val SEVERAL = "UPS $UPS\nUPS $SECOND"
        const val UPS_LINK = "https://www.ups.com/track?tracknum=$UPS"
        const val UPS_TEXT = "Your parcel $UPS is on its way"

        const val NO_NUMBER = "hello there"

        /** Six digits a pickup point gives, not a carrier's number. */
        const val PICKUP = "204 617"

        /** An S10 number whose check digit doesn't add up, its 8 read as a B, and the number it may be. */
        const val TYPO = "LX9999999B7DE"
        const val SUGGESTED = "LX999999987DE"

        /** Each shop's sample order number in Reading.json. */
        val ORDERS = listOf(
            "amazon" to "302-4571983-2294617",
            "shein" to "GSH0A1B2C3D4E5F",
            "shopee" to "261025ABCD1234",
            "temu" to "PO-000-00000000000000000",
        )

        /**
         * Synthetic numbers in Amazon's shapes, none a run of digits (which Add holds back as a placeholder): confirmed,
         * expired, and followed only through an account.
         */
        const val AMAZON = "FR4829173650"
        const val EXPIRED = "TBA304918276451"
        const val LOGISTICS = "DE4718293056"

        /** A synthetic Planzer number, which asks for its shared link, and links to give it. */
        const val PLANZER = "9999012345678"
        const val PLANZER_LINK = "https://trackandtrace.planzergroup.com/shared/abc?accessKey=xyz"
        const val PLAIN_LINK = "http://trackandtrace.planzergroup.com/shared/abc"
        const val HOSTLESS_LINK = "https:///shared/abc?accessKey=xyz"
    }
}
