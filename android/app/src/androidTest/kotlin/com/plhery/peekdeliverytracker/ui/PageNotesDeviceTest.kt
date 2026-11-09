package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.displayedCarrier
import com.plhery.peekdeliverytracker.core.showsMoreWithPhoneDigits
import com.plhery.peekdeliverytracker.core.showsMoreWithPostcode
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ACTION_ERROR_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_PHONE_DIGITS
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_GIFT_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PHONE_DIGITS_MORE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_POSTCODE_MORE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_REFRESH
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.pinnedIdentities
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.io.IOException
import java.time.Duration
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import com.plhery.peekdeliverytracker.core.Parcel as SharedParcel

/**
 * The notes and the alert of a parcel's page, and the quiet note of a parcel's link, on this device's own window
 * (B-S12, B-S76, B-S80): the alert that says why a change on the page failed (ParcelDetailView's `errorMessage`), the
 * note a link's sheet shows for a parcel gone quiet (ParcelLinkSheet's `ParcelQuietNote`), and the notes that ask for
 * the phone's last digits or the delivery postcode (ParcelDetailView's pass). Signed in as the synthetic
 * reader@example.com, or as a guest, on fake deliveries and links ([ParcelWalk], [FakeParcelLinks]) whose stores answer
 * every read in memory; nothing goes to the network. Each saved as pagenotes/<row>-<moment>.png in the test package's
 * external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class PageNotesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** `this` as another parcel of the reader's, numbered `n` and named `label`, its scans its own. */
    private fun Parcel.renumbered(n: Long, label: String): Parcel {
        val id = UUID(0, 8_000 + n)
        return copy(
            id = id, label = label,
            trackingEvents = trackingEvents.map { it.copy(id = UUID.nameUUIDFromBytes("$id-${it.id}".toByteArray()), packageId = id) },
        )
    }

    /** A J&T Mexico parcel like the demo's kit, numbered with the synthetic [PHONE], without the phone's last digits. */
    private fun jAndT(): Parcel = DemoCards.parcel("Trail weekend kit").copy(
        carrier = CarrierId.JAndTMx, trackingNumber = PHONE, dpdPostcode = null, phoneDigits = null,
    )

    /** The button of the note tagged `tag` that says `text`. */
    private fun noteButton(tag: String, text: String): SemanticsMatcher = hasText(text) and hasClickAction() and hasAnyAncestor(hasTestTag(tag))

    private fun editor(): Pair<UUID?, CarrierId?>? = walk.onUi { walk.deliveries.carrierEditor?.let { it.pageId to it.initialCarrier } }

    // B-S12: the page's alert.

    /**
     * B-S12: the page's refresh failing offline says why in an alert over the page, titled "native.errorTitle" with the
     * error's words and Close (ParcelDetailView's `.alert` on `errorMessage`, set by `run` around `store.refresh`);
     * Close dismisses it and the page stays.
     */
    @Test
    fun aFailedRefreshOnThePageSaysWhyInAnAlertThatCloseDismisses() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels())
        walk.open(gift)
        compose.reveal(PAGE_REFRESH)
        walk.onUi { walk.source.failure = IOException("offline") }
        walk.node(PAGE_REFRESH).performClick()
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ACTION_ERROR) }
        walk.settle()
        val message = walk.text("error.connection")
        assertTrue(walk.shows(ACTION_ERROR, walk.text("native.errorTitle")))
        assertTrue(walk.shows(ACTION_ERROR, message))
        assertTrue(walk.shows(ACTION_ERROR_CLOSE, walk.text("common.close")))
        assertEquals(message, walk.onUi { walk.deliveries.pageError })
        assertNull("The page's, not a card's", walk.onUi { walk.deliveries.actionError })
        walk.shot("B-S12-error-alert", alsoDark = false)

        walk.tap(ACTION_ERROR_CLOSE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ACTION_ERROR) }
        assertNull(walk.onUi { walk.deliveries.pageError })
        assertEquals(gift.id, walk.shown())
    }

    // B-S76: the link's quiet note.

    /** `this` with its scans a week older and none out for delivery: gone quiet at the captures' moment. */
    private fun PublicParcelResponse.quiet(): PublicParcelResponse = copy(
        parcel = parcel.copy(
            trackingEvents = parcel.trackingEvents.filter { it.stage != TrackingStage.OutForDelivery }.map {
                it.copy(occurredAt = DateParser.isoString(checkNotNull(DateParser.date(it.occurredAt)).minus(QUIET)))
            },
        ),
    )

    /**
     * The link that reads as `response` in its sheet over the list, signed out, edge to edge as the app's window, once
     * its card shows: the root drawn here, not by [ParcelWalk.show], which has no links.
     */
    private fun showLink(response: PublicParcelResponse) {
        assertEquals("Gone quiet", ParcelAttention.Stalled, SharedParcel(shared = response.parcel).attention(CAPTURED))
        val links = FakeParcelLinks(answer = response)
        compose.waitsForMaps()
        compose.runOnUiThread { compose.activity.edgeToEdge(false) }
        compose.setContent { RootHost(state = AuthenticationState.Welcome, identities = pinnedIdentities, links = links) }
        compose.waitForIdle()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = response.link.id)) }
        compose.waitForIdle()
        compose.mainClock.advanceTimeBy(SETTLE_MILLIS)
        compose.waitUntil(LONG_MILLIS) { inSheet(hasTestTag(LINK_CARD)) || inSheet(hasTestTag(LINK_GIFT_CARD)) }
        walk.settle()
    }

    private fun inSheet(matcher: SemanticsMatcher): Boolean =
        compose.onAllNodes(matcher and hasAnyAncestor(hasTestTag(LINK_SHEET)), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The quiet note's words up to the carrier's name, which any carrier's note starts with. */
    private fun quietWords(): String = walk.text("link.quiet", "carrier" to MARK).substringBefore(MARK)

    /**
     * B-S76: a link's parcel gone quiet, its last scan in transit a week before, says so in a note naming its carrier
     * (ParcelLinkSheet: `!wrapped`, a known carrier, `attention() == .stalled`).
     */
    @Test
    fun aQuietParcelsLinkSaysParcelsOftenGoQuiet() {
        val response = LinkFixtures.viewer().quiet()
        showLink(response)
        val carrier = pinnedIdentities.displayName(SharedParcel(shared = response.parcel).displayedCarrier, AppLanguage.En)
        val note = hasText(walk.text("link.quiet", "carrier" to carrier), substring = true)
        assertTrue(inSheet(note))
        // Into view where the sheet scrolls: below the card on a short window.
        if (inSheet(note and hasAnyAncestor(hasScrollAction()))) {
            compose.onNode(note and hasAnyAncestor(hasTestTag(LINK_SHEET)), useUnmergedTree = true).performScrollTo()
            walk.settle()
        }
        walk.shot("B-S76-quiet", alsoDark = false)
    }

    /** B-S76: a wrapped gift gone quiet shows no quiet note, which would say who carries it. */
    @Test
    fun aWrappedGiftGoneQuietShowsNoQuietNote() {
        val response = LinkFixtures.wrappedGift().quiet()
        assertTrue(response.link.gift == true)
        showLink(response)
        assertTrue(inSheet(hasTestTag(LINK_GIFT_CARD)))
        assertFalse(inSheet(hasText(quietWords(), substring = true)))
        walk.shot("B-S76-quiet-gift", alsoDark = false)
    }

    /** B-S76: a parcel gone quiet with no carrier known shows no quiet note: there is no carrier to keep asking. */
    @Test
    fun aQuietParcelWithNoCarrierShowsNoQuietNote() {
        val viewer = LinkFixtures.viewer().quiet()
        val response = viewer.copy(parcel = viewer.parcel.copy(carrier = CarrierId.Unknown))
        assertEquals(CarrierId.Unknown, SharedParcel(shared = response.parcel).displayedCarrier)
        showLink(response)
        assertTrue(inSheet(hasTestTag(LINK_CARD)))
        assertFalse(inSheet(hasText(quietWords(), substring = true)))
        walk.shot("B-S76-quiet-unknown", alsoDark = false)
    }

    // B-S80: the page's notes asking for more.

    /**
     * B-S80: a J&T Mexico parcel of the reader's without the phone's last digits says its carrier shows more with them
     * ("detail.phoneDigitsShowsMore"), and its "detail.phoneDigitsAction" opens Change carrier on the parcel as it is,
     * with the digits' field (ParcelDetailView's pass, `showsMoreWithPhoneDigits`, `carrierEditor = CarrierEditorRequest()`).
     */
    @Test
    fun thePhoneDigitsNoteSaysWhatTheyShowAndItsActionOpensTheCarrierEditor() {
        val parcel = jAndT()
        assertTrue(parcel.showsMoreWithPhoneDigits(CarrierCatalog.shared))
        walk.show(listOf(parcel))
        walk.open(parcel)
        compose.reveal(PAGE_PHONE_DIGITS_MORE)
        val carrier = walk.identities.displayName(CarrierId.JAndTMx, walk.language)
        assertTrue(walk.shows(PAGE_PHONE_DIGITS_MORE, walk.text("detail.phoneDigitsShowsMore", "carrier" to carrier)))
        val action = noteButton(PAGE_PHONE_DIGITS_MORE, walk.text("detail.phoneDigitsAction"))
        assertEquals(1, compose.onAllNodes(action).fetchSemanticsNodes().size)
        assertFalse(walk.exists(PAGE_POSTCODE_MORE))

        walk.tap(action)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) }
        walk.awaitStill()
        assertEquals(parcel.id to null, editor())
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_PHONE_DIGITS) }
        assertEquals("", walk.editable(CHANGE_CARRIER_PHONE_DIGITS))
        walk.tap(CHANGE_CARRIER_CANCEL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(CHANGE_CARRIER_SHEET) }

        compose.reveal(PAGE_PHONE_DIGITS_MORE)
        walk.shot("B-S80-phone-digits", alsoDark = false)
    }

    /**
     * B-S80: a guest's DPD parcel whose lookup had the postcode shows no note asking for it, while one whose lookup had
     * none does ("detail.postcodeShowsMore", `showsMoreWithPostcode(catalog, postcodeGiven:)`); a guest's J&T parcel is
     * not asked for the phone's last digits, which its link never shows (`knowsInputs: !store.isGuest`).
     */
    @Test
    fun aGuestsPageAsksForNoPostcodeItsLookupHad() {
        val given = DemoCards.parcel("Trail weekend kit").copy(dpdPostcode = null)
        val asked = given.renumbered(1, "Camping stove")
        val phone = jAndT().renumbered(2, "Phone case")
        assertTrue(given.showsMoreWithPostcode(CarrierCatalog.shared))
        assertFalse(given.showsMoreWithPostcode(CarrierCatalog.shared, postcodeGiven = true))
        assertTrue(phone.showsMoreWithPhoneDigits(CarrierCatalog.shared))
        walk.show(listOf(given, asked, phone), session = AuthenticationState.Welcome) {
            isGuest = true
            postcodesGiven = setOf(given.id)
        }
        val dpd = walk.identities.displayName(CarrierId.Dpd, walk.language)

        walk.open(asked)
        assertTrue("Asked where its lookup had no postcode", walk.exists(PAGE_POSTCODE_MORE))
        assertTrue(walk.shows(PAGE_POSTCODE_MORE, walk.text("detail.postcodeShowsMore", "carrier" to dpd)))

        walk.open(phone)
        assertFalse("A guest isn't asked for the phone's digits", walk.exists(PAGE_PHONE_DIGITS_MORE))

        walk.open(given)
        assertFalse("Not asked for the postcode its lookup had", walk.exists(PAGE_POSTCODE_MORE))
        walk.shot("B-S80-postcode-given", alsoDark = false)
    }

    private companion object {
        const val FOLDER = "pagenotes"

        /** A synthetic J&T Mexico number, which takes the phone's last digits. */
        const val PHONE = "JMX000000000001"

        /** What stands in for the carrier's name while the quiet note's words are cut there. */
        const val MARK = "\u0001"

        /** How long the sheet's rise and its card's entrance take to play out. */
        const val SETTLE_MILLIS = 3_000L

        /** How much older a quiet parcel's scans are than the fixture's. */
        val QUIET: Duration = Duration.ofDays(7)
    }
}
