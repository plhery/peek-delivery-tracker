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
 * A postcode typed by hand stays the reader's own as its carrier's details come up again, even where it is the postcode
 * given before: as on the iPhone (`CarrierPostcodes.prepare`), only a field filled from memory offers Forget. Synthetic
 * numbers and postcodes only.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PostcodeForgetReturnTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000001115"), trackingNumber = "TESTLAMP1115", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(Instant.parse("2026-11-12T08:00:00Z")),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    private fun fieldText(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    private fun showPostNL() {
        source = FakeDeliveries(listOf(lamp), reader).apply {
            isGuest = true
            givenPostcodes = listOf(GivenPostcode(CarrierId.Dpd, "1234 AB"))
        }
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, 1f)) {
                RootHost(state = reader, source = source, reduceMotion = true, inbox = null, scanner = NoLabelScanner)
            }
        }
        compose.waitForIdle()
        node(ADD_BUTTON).performClick()
        settle()
        node(ADD_TRACKING).performTextReplacement(POSTNL_1115)
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
     * Typed by hand in PostNL's field, then away to UPS, which has no postcode field and fills nothing, and back: the
     * field is the reader's own typing, and nothing ever filled any field, so it offers no Forget.
     */
    @Test
    fun aPostcodeTypedByHandOffersNoForgetAsItsCarrierComesBack() {
        showPostNL()
        node(ADD_POSTCODE).performTextInput("1234 AB")
        compose.waitForIdle()
        assertFalse("typed by hand: no Forget at first", exists(ADD_FORGET_POSTCODE))

        pick(CarrierId.Ups, "UPS")
        assertFalse(exists(ADD_POSTCODE))
        backToPostNL()
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertFalse("A postcode typed by hand offers no Forget as its carrier comes back", exists(ADD_FORGET_POSTCODE))
    }

    /**
     * Packs' field keeps what it was filled with while PostNL's holds the same postcode typed by hand, so Forget shows
     * back on Packs; forgotten there, it empties PostNL's typed one too, which offers it no more.
     */
    @Test
    fun forgetInTheFilledFieldEmptiesTheOneTypedByHandToo() {
        showPostNL()
        pick(PACKS_1115, "Packs")
        backToPostNL()
        node(ADD_POSTCODE).performTextInput("1234 AB")
        compose.waitForIdle()
        assertFalse(exists(ADD_FORGET_POSTCODE))
        pick(PACKS_1115, "Packs")
        assertEquals("1234 AB", fieldText(ADD_POSTCODE))
        assertTrue(exists(ADD_FORGET_POSTCODE))
        node(ADD_FORGET_POSTCODE).performClick()
        settle()
        assertEquals(listOf("1234 AB"), source.forgottenPostcodes)
        assertEquals("", fieldText(ADD_POSTCODE))
        backToPostNL()
        assertEquals("", fieldText(ADD_POSTCODE))
        assertFalse(exists(ADD_USE_POSTCODE))
        assertFalse(exists(ADD_FORGET_POSTCODE))
    }
}

/** A synthetic 3S number: PostNL's, its postcode optional. */
private const val POSTNL_1115 = "3SABCD826405193"

/** Packs, whose lookup needs the postcode. */
private val PACKS_1115 = CarrierId("packs")
