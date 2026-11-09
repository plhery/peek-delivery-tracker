package com.plhery.peekdeliverytracker.ui

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.core.content.IntentCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.FirstOpenJourney
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierPostcodeRefused
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_BACK
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_SAYS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CONTENT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MARKS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MENU_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_POSTCODE_MORE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_REFRESH
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SYNC_LINE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_MISSING
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_DEMO
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.menuItemTag
import java.time.Duration
import java.util.UUID
import java.util.concurrent.atomic.AtomicReference
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What a parcel's page offers in each of its states, on this device's own window (B-ST01 to B-ST05): a parcel gone from
 * the deliveries, the sample, a parcel followed through someone's link, an account against a guest and the demo, and an
 * archived parcel (ParcelDetailView's `parcel`, `sample`, `readerLink`, `editable` and `isArchived`). Over the list on a
 * phone, beside it on a tablet or an unfolded window. Signed in as the synthetic reader@example.com unless said, on fake
 * deliveries ([ParcelWalk]) whose stores answer every read in memory; nothing goes to the network, and the system's share
 * sheet is never shown. Each saved as pagestates/<row>-<moment>.png in the test package's external files, which adb pulls.
 */
@RunWith(AndroidJUnit4::class)
class PageStatesDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    /** What has `tag` on the parcel's page. */
    private fun onPage(tag: String): SemanticsMatcher = hasTestTag(tag) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))

    private fun pageHas(tag: String): Boolean = compose.onAllNodes(onPage(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** Whether the page's part tagged `tag`, or anything in it, takes a tap. */
    private fun takesATap(tag: String): Boolean = compose.onAllNodes(
        (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)) and hasClickAction(),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** Whether the question of the page with `pageId` asks something; null while the page has made none. */
    private fun asks(pageId: UUID): Boolean? = walk.onUi { walk.source.feedbacks.of(pageId)?.let { it.standing != null } }

    /** The page's ••• menu, opened from its button, and the items it offers. */
    private fun openMenu() {
        walk.tap(PAGE_MENU_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_MENU) }
        walk.settle()
    }

    private fun offers(item: ParcelMenuItem): Boolean = walk.exists(menuItemTag(item))

    private fun closeMenu() {
        walk.back()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PAGE_MENU) }
        walk.settle()
    }

    // B-ST01: a parcel gone.

    /**
     * B-ST01: a parcel that leaves the deliveries while its page shows says so where the page stood, "common.parcel" over
     * "native.parcelMissing" (ParcelDetailView's `ContentUnavailableView` when `parcel` is nil), and its ••• goes with it
     * (`if let parcel, sample == nil`). Over the list ‹ stays and takes the page back.
     */
    @Test
    fun aParcelGoneSaysItIsNoLongerInTheListAndItsMenuGoes() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels())
        walk.open(gift)
        assertTrue(walk.exists(PAGE_MENU_BUTTON))

        walk.onUi { walk.source.parcels.value = walk.source.parcels.value.filterNot { it.id == gift.id } }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { pageHas(PARCEL_MISSING) }
        walk.settle()
        assertEquals(gift.id, walk.shown())
        assertTrue(
            compose.onAllNodes(hasText(walk.text("common.parcel")) and hasAnyAncestor(hasTestTag(PARCEL_MISSING)), useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty(),
        )
        assertTrue(walk.shows(PARCEL_MISSING, walk.text("native.parcelMissing")))
        assertFalse(walk.exists(PAGE_CONTENT))
        assertFalse(walk.exists(PAGE_MENU_BUTTON))
        assertEquals("‹ over the list, none beside it", walk.stacked(), walk.exists(PAGE_BACK))
        walk.shot("B-ST01-missing", alsoDark = false)

        if (walk.stacked()) {
            walk.tap(PAGE_BACK)
            walk.settle()
            compose.waitUntil(LONG_MILLIS) { walk.shown() == null }
        }
    }

    // B-ST02: the sample.

    /**
     * B-ST02: the sample can't be changed, shared or checked and asks nothing (ParcelDetailView's `sample`): no ••• menu,
     * bell, share or refresh, no delivery email offered, its name and marks take no tap, its question is made up
     * (`feedbackPage`'s `madeUp`) and it asks for no rating (`reviewReady`). A delivered parcel of the account's, with
     * the email never chosen, shows each of those first.
     */
    @Test
    fun theSampleCanBeNeitherChangedNorSharedNorCheckedAndAsksNothing() {
        val beans = DemoCards.parcel("Coffee beans")
        val sampleId = FirstOpenJourney.parcelId
        walk.show(DemoCards.parcels()) { signedInWithEmail(choice = null) }
        walk.open(beans)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PAGE_EMAIL_OFFER) }
        assertTrue(walk.exists(PAGE_MENU_BUTTON))
        assertTrue(walk.exists(PAGE_BELL))
        assertTrue(walk.exists(PAGE_SHARE))
        assertTrue(walk.exists(PAGE_REFRESH))

        if (walk.stacked()) walk.home()
        walk.onUi { walk.deliveries.openSample() }
        compose.waitUntil(LONG_MILLIS) { walk.shown() == sampleId }
        val sample = checkNotNull(walk.onUi { walk.deliveries.sample(sampleId) }) { "No sample shown" }
        compose.waitUntil(LONG_MILLIS) {
            walk.count(PARCEL_PAGE) == 1 &&
                compose.onAllNodes(onPage(PAGE_NAME) and hasText(sample.label), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { asks(sampleId) != null }

        assertTrue(walk.exists(PAGE_CONTENT))
        assertFalse(walk.exists(PARCEL_MISSING))
        assertFalse(walk.exists(PAGE_MENU_BUTTON))
        assertFalse(walk.exists(PAGE_BELL))
        assertFalse(walk.exists(PAGE_SHARE))
        assertFalse(walk.exists(PAGE_REFRESH))
        assertFalse("Delivered, but the sample", walk.exists(PAGE_EMAIL_OFFER))
        assertFalse(takesATap(PAGE_NAME))
        assertFalse(takesATap(PAGE_MARKS))
        assertEquals(false, asks(sampleId))
        assertFalse(walk.exists(FEEDBACK_QUESTION))
        assertFalse(walk.exists(FEEDBACK_ROW))
        assertEquals(false, walk.onUi { walk.source.feedbacks.of(sampleId)?.reviewDue })
        assertTrue(walk.onUi { walk.source.reviewCalls.isEmpty() })
        walk.shot("B-ST02-sample", alsoDark = false)
    }

    // B-ST03: a parcel followed through someone's link.

    /** The demo's DPD kit with a postcode its carrier refused, which the page says with its edit where it can be changed. */
    private fun refused(parcel: Parcel): Parcel = parcel.copy(
        dpdPostcode = REFUSED,
        carrierData = (parcel.carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(postcodeRefused = CarrierPostcodeRefused(CarrierId.Dpd))),
    )

    /**
     * B-ST03: a parcel followed through someone else's link can't be changed (ParcelDetailView's `editable`): its marks
     * take no tap, its refused postcode is said without "detail.editPostcode", nothing asks for more, and its menu has no
     * Change carrier. Its share, read "link.shareAria", hands the link's own address straight to the system's share sheet
     * (`ShareLink(item: ParcelLinkRoute.address(id: readerLink))`), with no share sheet of Peek's.
     */
    @Test
    fun aParcelFollowedThroughALinkSharesItsAddressAndEditsNoPostcode() {
        val kit = refused(DemoCards.parcel("Trail weekend kit"))
        val parcels = DemoCards.parcels().map { if (it.id == kit.id) kit else it }
        walk.show(parcels) { viewerLinks = mapOf(kit.id to LINK) }
        walk.open(kit)
        val dpd = walk.identities.displayName(CarrierId.Dpd, walk.language)
        assertTrue(walk.shows(PAGE_POSTCODE, walk.text("detail.postcodeRefused", "carrier" to dpd, "postcode" to REFUSED)))
        assertEquals(0, walk.pageButtons(walk.text("detail.editPostcode")))
        assertFalse(walk.exists(PAGE_POSTCODE_MORE))
        assertFalse(takesATap(PAGE_MARKS))
        assertTrue(
            compose.onAllNodes(onPage(PAGE_SHARE) and hasContentDescription(walk.text("link.shareAria")) and hasClickAction(), useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty(),
        )
        walk.shot("B-ST03-reader", alsoDark = false)

        val chooser = AtomicReference<Intent?>(null)
        val monitor = object : Instrumentation.ActivityMonitor() {
            override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
                if (intent.action != Intent.ACTION_CHOOSER) return null
                chooser.set(intent)
                return Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
            }
        }
        instrumentation.addMonitor(monitor)
        try {
            walk.tap(PAGE_SHARE)
            walk.settle()
            compose.waitUntil(LONG_MILLIS) { chooser.get() != null }
        } finally {
            instrumentation.removeMonitor(monitor)
        }
        val send = IntentCompat.getParcelableExtra(checkNotNull(chooser.get()), Intent.EXTRA_INTENT, Intent::class.java)
        assertNotNull(send)
        assertEquals(Intent.ACTION_SEND, send!!.action)
        assertEquals("text/plain", send.type)
        val address = send.getStringExtra(Intent.EXTRA_TEXT)
        assertEquals(ParcelLinkRoute.address(id = LINK, baseUrl = walk.source.linkBaseUrl), address)
        assertTrue("$address", address.orEmpty().startsWith(walk.source.linkBaseUrl) && LINK in address.orEmpty())
        assertFalse(walk.exists(PARCEL_SHARE))
        assertNull(walk.onUi { walk.deliveries.sharing })

        openMenu()
        assertTrue(offers(ParcelMenuItem.Rename))
        assertTrue(offers(ParcelMenuItem.CopyTrackingNumber))
        assertFalse(offers(ParcelMenuItem.ChangeCarrier))
        closeMenu()
    }

    // B-ST04: an account, a guest, the demo.

    /**
     * B-ST04: an account has the whole page: the bell, the share, the refresh, the ••• menu and the question, and its
     * share opens Peek's share sheet without the demo's line (ParcelDetailView's `ParcelShareSheet(..., demo: store.isDemo)`).
     */
    @Test
    fun anAccountHasTheBellTheShareSheetAndTheQuestion() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels())
        walk.open(gift)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_QUESTION) }
        assertTrue(walk.exists(PAGE_BELL))
        assertTrue(walk.exists(PAGE_SHARE))
        assertTrue(walk.exists(PAGE_REFRESH))
        assertTrue(walk.exists(PAGE_MENU_BUTTON))
        assertEquals(true, asks(gift.id))
        walk.shot("B-ST04-account", alsoDark = false)

        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE) }
        assertNotNull(walk.onUi { walk.deliveries.sharing })
        assertFalse(walk.exists(PARCEL_SHARE_DEMO))
    }

    /**
     * B-ST04: a guest's page has no bell, as alerts need an account (ParcelDetailView's `if !store.isGuest, sample == nil`),
     * and keeps the share, the refresh and the ••• menu.
     */
    @Test
    fun aGuestHasNoBell() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels(), session = AuthenticationState.Welcome) { isGuest = true }
        walk.open(gift)
        assertFalse(walk.exists(PAGE_BELL))
        assertTrue(walk.exists(PAGE_SHARE))
        assertTrue(walk.exists(PAGE_REFRESH))
        assertTrue(walk.exists(PAGE_MENU_BUTTON))
        walk.shot("B-ST04-guest", alsoDark = false)
    }

    /**
     * B-ST04: the demo's parcels are made up, so the page asks nothing of them (ParcelDetailView's `feedbackPage`,
     * `madeUp = store.isDemo || sample != nil`), and its share sheet says the demo's links are made up
     * (`ParcelShareSheet(..., demo: store.isDemo)`). The bell stays, as the demo is no guest.
     */
    @Test
    fun theDemoAsksNothingAndItsShareSheetSaysItIsTheDemo() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels(), session = AuthenticationState.Demo)
        walk.open(gift)
        compose.waitUntil(LONG_MILLIS) { asks(gift.id) != null }
        assertEquals(false, asks(gift.id))
        assertFalse(walk.exists(FEEDBACK_QUESTION))
        assertFalse(walk.exists(FEEDBACK_ROW))
        assertTrue(walk.exists(PAGE_BELL))

        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_DEMO) }
        assertTrue(walk.shows(PARCEL_SHARE_DEMO, walk.text("alerts.demo")))
        walk.awaitStill()
        walk.shot("B-ST04-demo-share", alsoDark = false)
    }

    // B-ST05: an archived parcel.

    /** `parcel` with its postcode refused, its next check due in three hours and a missed delivery as its last scan. */
    private fun troubled(parcel: Parcel): Parcel = parcel.copy(
        dpdPostcode = REFUSED,
        carrierData = (parcel.carrierData ?: CarrierData()).copy(
            routing = CarrierDataRouting(
                postcodeRefused = CarrierPostcodeRefused(CarrierId.Dpd),
                nextCheckAt = DateParser.isoString(CAPTURED.plus(Duration.ofHours(3))),
            ),
        ),
        trackingEvents = parcel.trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("${parcel.id}-latest".toByteArray()), packageId = parcel.id, stage = TrackingStage.FailedAttempt,
            description = "Nobody was home. A notice was left.", occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        ),
    )

    /** `this` as another parcel of the reader's, archived half an hour ago, its number and its scans its own. */
    private fun Parcel.archivedCopy(): Parcel {
        val id = UUID(0, 9_001)
        return copy(
            id = id, label = "Camping stove", trackingNumber = "DEMODPD20260002",
            archivedAt = DateParser.isoString(CAPTURED.minus(Duration.ofMinutes(30))),
            trackingEvents = trackingEvents.map { it.copy(id = UUID.nameUUIDFromBytes("$id-${it.id}".toByteArray()), packageId = id) },
        )
    }

    /**
     * B-ST05: archived, a parcel's page no longer checks or asks (ParcelDetailView's `isArchived`): no next check
     * (`syncStatus`), no refresh, no "detail.editPostcode" (`if parcel.archivedAt == nil, editable`), no quote of what
     * the carrier says (`carrierWaits`) and no question, and its ••• menu offers Restore where it offered Archive. The
     * same parcel not archived shows each of those first.
     */
    @Test
    fun anArchivedPageNeitherChecksNorAsksAndItsMenuOffersRestore() {
        val kit = troubled(DemoCards.parcel("Trail weekend kit"))
        val stove = kit.archivedCopy()
        val parcels = DemoCards.parcels().map { if (it.id == kit.id) kit else it } + stove
        val nextCheck = walk.text("detail.nextCheckAfter", "date" to MARK).substringBefore(MARK)
        val editPostcode = walk.text("detail.editPostcode")
        walk.show(parcels)

        walk.open(kit)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_QUESTION) }
        assertTrue(walk.shows(PAGE_SYNC_LINE, nextCheck))
        assertTrue(walk.exists(PAGE_REFRESH))
        assertEquals(1, walk.pageButtons(editPostcode))
        assertTrue(walk.exists(PAGE_CARRIER_SAYS))
        assertEquals(true, asks(kit.id))
        openMenu()
        assertTrue(offers(ParcelMenuItem.Archive))
        assertFalse(offers(ParcelMenuItem.Restore))
        closeMenu()

        walk.openArchive()
        walk.open(stove)
        compose.waitUntil(LONG_MILLIS) { asks(stove.id) != null }
        assertTrue("The refused postcode is still said", walk.exists(PAGE_POSTCODE))
        assertFalse(walk.inPage(nextCheck))
        assertFalse(walk.exists(PAGE_REFRESH))
        assertEquals(0, walk.pageButtons(editPostcode))
        assertFalse(walk.exists(PAGE_CARRIER_SAYS))
        assertEquals(false, asks(stove.id))
        assertFalse(walk.exists(FEEDBACK_QUESTION))
        assertFalse(walk.exists(FEEDBACK_ROW))
        walk.shot("B-ST05-archived", alsoDark = false)

        openMenu()
        assertTrue(offers(ParcelMenuItem.Restore))
        assertFalse(offers(ParcelMenuItem.Archive))
        walk.shot("B-ST05-menu", alsoDark = false)
        closeMenu()
    }

    private companion object {
        const val FOLDER = "pagestates"

        /** The synthetic id of the link the reader follows someone's parcel through. */
        const val LINK = "link-0001"

        /** A synthetic postcode DPD refuses. */
        const val REFUSED = "0000"

        /** What stands in for the date while the next check's words are cut there. */
        const val MARK = "\u0001"
    }
}
