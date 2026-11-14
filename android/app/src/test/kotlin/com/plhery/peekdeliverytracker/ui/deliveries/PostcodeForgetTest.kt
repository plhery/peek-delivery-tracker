package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.Density
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.GivenPostcode
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Instant
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The Add sheet's Forget button for a guest. As on the iPhone (`CarrierPostcodes.fills`), each carrier's field keeps
 * what it was filled with, so Forget shows only beside a postcode that field was filled with from memory, never beside
 * one typed by hand because another carrier's field was filled with it. Synthetic numbers and postcodes only.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PostcodeForgetTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000001114"), trackingNumber = "TESTLAMP1114", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(Instant.parse("2026-11-12T08:00:00Z")),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun show(prepare: FakeDeliveries.() -> Unit) {
        source = FakeDeliveries(listOf(lamp), reader).apply(prepare)
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, 1f)) {
                RootHost(state = reader, source = source, reduceMotion = true, inbox = null, scanner = NoLabelScanner)
            }
        }
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun fieldText(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    /** Add for a guest on the synthetic PostNL number, whose postcode is optional, with `given` remembered. */
    private fun showPostNL(given: List<GivenPostcode>) {
        show {
            isGuest = true
            givenPostcodes = given
        }
        node(ADD_BUTTON).performClick()
        settle()
        node(ADD_TRACKING).performTextReplacement(POSTNL_1114)
        compose.waitForIdle()
        node(ADD_NAME).performClick()
        settle()
    }

    private fun pick(carrier: CarrierId, search: String) {
        node(ADD_CARRIER).performClick()
        settle()
        node(CARRIER_PICKER_SEARCH).performTextInput(search)
        compose.waitForIdle()
        compose.onAllNodesWithTag(carrierPickerRowTag(carrier)).onFirst().performClick()
        settle()
    }

    private fun backToPostNL() {
        node(ADD_CARRIER).performClick()
        settle()
        node(CARRIER_PICKER_AUTOMATIC).performClick()
        settle()
    }

    /**
     * Packs' required field starts from the remembered postcode; back on PostNL, whose field is optional and empty, the
     * reader types that postcode by hand instead of tapping Use. Nothing filled PostNL's field, so, as on the iPhone,
     * there is nothing to forget there.
     */
    @Test
    fun aPostcodeTypedByHandAfterAnotherCarriersFieldWasFilledWithItOffersNoForget() {
        showPostNL(listOf(GivenPostcode(CarrierId.Dpd, "1234 AB")))
        pick(PACKS_1114, "Packs")
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertTrue("Packs' field was filled from memory, so Forget shows there", exists(ADD_FORGET_POSTCODE))

        backToPostNL()
        assertEquals("", fieldText(ADD_POSTCODE))
        assertTrue(exists(ADD_USE_POSTCODE))
        node(ADD_POSTCODE).performTextInput("1234 AB")
        compose.waitForIdle()
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertFalse("A postcode typed by hand in PostNL's field offers no Forget, whatever filled Packs'", exists(ADD_FORGET_POSTCODE))
    }

    /** The same postcode typed by hand in PostNL's field, with no other field filled before, offers no Forget. */
    @Test
    fun aPostcodeTypedByHandWithNothingFilledBeforeOffersNoForget() {
        showPostNL(listOf(GivenPostcode(CarrierId.Dpd, "1234 AB")))
        assertTrue(exists(ADD_USE_POSTCODE))
        node(ADD_POSTCODE).performTextInput("1234 AB")
        compose.waitForIdle()
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertFalse(exists(ADD_FORGET_POSTCODE))
    }

    /** PostNL's field filled by tapping Use offers Forget, as on the iPhone. */
    @Test
    fun aPostcodeFilledWithUseOffersForget() {
        showPostNL(listOf(GivenPostcode(CarrierId.Dpd, "1234 AB")))
        node(ADD_USE_POSTCODE).performClick()
        settle()
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertTrue(exists(ADD_FORGET_POSTCODE))
    }
}

/** A synthetic 3S number: PostNL's, its postcode optional. */
private const val POSTNL_1114 = "3SABCD826405193"

/** Packs, whose lookup needs the postcode. */
private val PACKS_1114 = CarrierId("packs")
