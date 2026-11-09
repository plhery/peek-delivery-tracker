package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.GivenPostcode
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.READER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CARRIER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_COUNTRY
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_FORGET_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PICKER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_USE_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.CARRIER_PICKER_SEARCH
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_COUNTRY
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.carrierPickerRowTag
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import com.plhery.peekdeliverytracker.ui.deliveries.postcodeCountryTag
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * Add's postcodes on this device's own window (B-SE08, B-SE09), as AddParcelView's postcode field, its country menu
 * and the postcodes given before, and Change carrier's country (ParcelDetailView's ChangeCarrierView). In Zurich's time
 * zone, which names the phone's country; signed in as the synthetic reader@example.com, or as a guest, on fake
 * deliveries ([ParcelWalk]) whose stores answer every read in memory; the numbers and postcodes are synthetic or public
 * samples, and nothing goes to the network. Each saved as addpostcodes/<row>-<moment>.png in the test package's
 * external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class AddPostcodesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    /** Add opened from the + over `lamp` in the deliveries of `session` set up by `prepare`, its tracking field focused. */
    private fun openAdd(session: AuthenticationState = READER, prepare: FakeDeliveries.() -> Unit = {}) {
        walk.show(listOf(lamp), session = session, prepare = prepare)
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        awaitFocus(ADD_TRACKING)
    }

    private fun awaitFocus(tag: String) {
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(hasTestTag(tag) and isFocused()).fetchSemanticsNodes().isNotEmpty() }
        compose.waitForIdle()
    }

    /** `number` typed, then the field left for the name, as a finger leaves it, until the postcode field shows. */
    private fun typeAndLeave(number: String) {
        walk.node(ADD_TRACKING).performTextInput(number)
        walk.settle()
        walk.tap(ADD_NAME)
        awaitFocus(ADD_NAME)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_POSTCODE) }
        walk.settle()
    }

    /** The country the menu tagged `tag` reads as its value. */
    private fun country(tag: String): String? = walk.node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.StateDescription)

    /** Mondial Relay chosen from the carrier line's picker for [MONDIAL_NUMBER], which nothing names. */
    private fun chooseMondialRelay() {
        walk.node(ADD_TRACKING).performTextInput(MONDIAL_NUMBER)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_CARRIER) }
        walk.tap(ADD_CARRIER)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CARRIER_PICKER_SEARCH) }
        walk.awaitStill()
        walk.node(CARRIER_PICKER_SEARCH).performTextInput("Mondial")
        walk.settle()
        val row = carrierPickerRowTag(CarrierId.MondialRelay)
        compose.waitUntil(LONG_MILLIS) { walk.exists(row) }
        walk.tap(row)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PICKER) && walk.exists(ADD_COUNTRY) }
        walk.awaitStill()
    }

    /** `code` chosen from the country menu tagged `menu`. */
    private fun chooseCountry(menu: String, code: String) {
        walk.tap(menu)
        walk.settle()
        val option = postcodeCountryTag(code)
        compose.waitUntil(LONG_MILLIS) { walk.exists(option) }
        walk.tap(option)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(option) }
    }

    /** Add saved: the one parcel it asked for. */
    private fun save(): List<FakeDeliveries.AdditionRequest> {
        walk.tap(ADD_SAVE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.additions.isNotEmpty() } }
        return walk.onUi { walk.source.additions.toList() }
    }

    // B-SE08: the postcode's country.

    /**
     * B-SE08: Mondial Relay's postcode field, which takes any country's, starts on the phone's (Switzerland, from
     * Zurich's time zone); Belgium chosen from the menu beside it is sent with the postcode (`postcodeCountry`,
     * `sentPostcodeCountry`).
     */
    @Test
    fun thePostcodeStartsOnThePhonesCountryAndAnotherIsSentWithIt() {
        openAdd()
        chooseMondialRelay()
        assertEquals("The phone's country", "Switzerland", country(ADD_COUNTRY))
        walk.shot("B-SE08-phone-country", alsoDark = false)
        chooseCountry(ADD_COUNTRY, "BE")
        assertEquals("Belgium", country(ADD_COUNTRY))
        walk.node(ADD_POSTCODE).performTextInput("1000")
        walk.settle()
        walk.shot("B-SE08-belgium", alsoDark = false)
        assertEquals(listOf(FakeDeliveries.AdditionRequest(MONDIAL_NUMBER, "", CarrierId.MondialRelay, null, "1000", "BE")), save())
    }

    /** B-SE08: a postcode from the carrier's own home (France for Mondial Relay) goes without a country, as it always has. */
    @Test
    fun aPostcodeFromTheCarriersHomeGoesWithoutItsCountry() {
        openAdd()
        chooseMondialRelay()
        chooseCountry(ADD_COUNTRY, "FR")
        assertEquals("France", country(ADD_COUNTRY))
        walk.node(ADD_POSTCODE).performTextInput("75001")
        walk.settle()
        assertEquals(listOf(FakeDeliveries.AdditionRequest(MONDIAL_NUMBER, "", CarrierId.MondialRelay, null, "75001", null)), save())
    }

    /**
     * B-SE08: Change carrier on a Mondial Relay parcel with no postcode, bound for Belgium, starts its country on the
     * parcel's destination rather than the phone's (`initialPostcodeCountry`).
     */
    @Test
    fun changeCarrierStartsOnTheParcelsDestination() {
        val parcel = lamp.copy(
            carrier = CarrierId.MondialRelay, trackingNumber = MONDIAL_NUMBER, dpdPostcode = null,
            carrierData = CarrierData(destinationCountry = "BE"),
        )
        walk.show(listOf(parcel))
        walk.open(parcel)
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
        walk.tap(menuItemTag(ParcelMenuItem.ChangeCarrier))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) && walk.exists(CHANGE_CARRIER_COUNTRY) }
        walk.awaitStill()
        assertEquals("The parcel's destination", "Belgium", country(CHANGE_CARRIER_COUNTRY))
        assertEquals("", walk.editable(CHANGE_CARRIER_POSTCODE))
        walk.shot("B-SE08-change-carrier", alsoDark = false)
    }

    // B-SE09: the postcodes given before.

    /**
     * B-SE09: for a guest, a required postcode (GLS Switzerland's) starts from the last one given, which this phone
     * remembers and "add.forgetPostcode" forgets (`store.forgetPostcode`), emptying the field.
     */
    @Test
    fun aRequiredPostcodeStartsFromTheLastGivenWhichAGuestCanForget() {
        openAdd(session = AuthenticationState.Welcome) {
            isGuest = true
            detections[GLS_NUMBER] = CarrierDetectionResponse(GLS_NUMBER, carrier = CarrierId.GlsCh)
            givenPostcodes = listOf(GivenPostcode(carrier = CarrierId.GlsCh, postcode = "3011"))
        }
        typeAndLeave(GLS_NUMBER)
        assertEquals("3011", walk.editable(ADD_POSTCODE))
        assertEquals(listOf(walk.text("add.forgetPostcode")), walk.texts(ADD_FORGET_POSTCODE))
        walk.shot("B-SE09-required", alsoDark = false)
        walk.tap(ADD_FORGET_POSTCODE)
        walk.settle()
        assertEquals("", walk.editable(ADD_POSTCODE))
        assertEquals(listOf("3011"), walk.onUi { walk.source.forgottenPostcodes.toList() })
        assertFalse(walk.exists(ADD_FORGET_POSTCODE))
    }

    /**
     * B-SE09: for a guest, an optional postcode (GLS Austria's) is offered as "add.usePostcode", never filled in; a tap
     * fills it, and it can then be forgotten; it is sent with the parcel.
     */
    @Test
    fun anOptionalPostcodeIsOfferedFilledAtATapAndSent() {
        openAdd(session = AuthenticationState.Welcome) {
            isGuest = true
            detections[GLS_NUMBER] = CarrierDetectionResponse(GLS_NUMBER, carrier = GLS_AT, asked = listOf(GLS_AT))
            givenPostcodes = listOf(GivenPostcode(carrier = GLS_AT, postcode = "1010"))
        }
        typeAndLeave(GLS_NUMBER)
        assertEquals("Offered, not filled in", "", walk.editable(ADD_POSTCODE))
        assertEquals(listOf(walk.text("add.usePostcode", "postcode" to "1010")), walk.texts(ADD_USE_POSTCODE))
        assertFalse(walk.exists(ADD_FORGET_POSTCODE))
        walk.shot("B-SE09-offered", alsoDark = false)
        walk.tap(ADD_USE_POSTCODE)
        walk.settle()
        assertEquals("1010", walk.editable(ADD_POSTCODE))
        assertFalse(walk.exists(ADD_USE_POSTCODE))
        assertTrue("This phone's own, which a guest may forget", walk.exists(ADD_FORGET_POSTCODE))
        walk.node(ADD_SAVE).assertIsEnabled()
        assertEquals(listOf(FakeDeliveries.AdditionRequest(GLS_NUMBER, "", GLS_AT, null, "1010", null)), save())
    }

    /** B-SE09: signed in, a postcode from the account's parcels is offered and filled alike, with nothing to forget. */
    @Test
    fun signedInTheAccountsPostcodeIsOfferedWithNothingToForget() {
        openAdd {
            detections[GLS_NUMBER] = CarrierDetectionResponse(GLS_NUMBER, carrier = GLS_AT, asked = listOf(GLS_AT))
            givenPostcodes = listOf(GivenPostcode(carrier = GLS_AT, postcode = "1010"))
        }
        typeAndLeave(GLS_NUMBER)
        walk.tap(ADD_USE_POSTCODE)
        walk.settle()
        assertEquals("1010", walk.editable(ADD_POSTCODE))
        assertFalse("Nothing of this phone's to forget", walk.exists(ADD_FORGET_POSTCODE))
    }

    private companion object {
        const val FOLDER = "addpostcodes"

        /** A synthetic number no carrier's shape names, so the carrier is chosen. */
        const val MONDIAL_NUMBER = "76434219"

        /** A synthetic number the fake detection names GLS by. */
        const val GLS_NUMBER = "12345478901"

        val GLS_AT = CarrierId("gls-at")
    }
}
