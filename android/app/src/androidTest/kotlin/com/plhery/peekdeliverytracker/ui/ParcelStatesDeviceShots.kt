package com.plhery.peekdeliverytracker.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.NonTrackingShape
import com.plhery.peekdeliverytracker.core.ParcelAttention
import com.plhery.peekdeliverytracker.core.SharedResources
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.core.api.CarrierDataRouting
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeeded
import com.plhery.peekdeliverytracker.core.api.CarrierInputNeededField
import com.plhery.peekdeliverytracker.core.api.CarrierInputSuggested
import com.plhery.peekdeliverytracker.core.api.CarrierInputSuggestedField
import com.plhery.peekdeliverytracker.core.api.CarrierPostcodeRefused
import com.plhery.peekdeliverytracker.core.api.LikelyCarrier
import com.plhery.peekdeliverytracker.core.api.NumberRefusalRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeeded
import com.plhery.peekdeliverytracker.core.api.ProviderInputNeededField
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.attention
import com.plhery.peekdeliverytracker.core.nonTrackingShape
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_CANCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_REFUSED
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_HINT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_CARRIER_SAYS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_JOURNAL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MARKS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PICKUP
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PICKUP_POINT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_POSTCODE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_QUIET
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SYNC_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_ADDRESS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_GIFT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_ALERT
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_FIELD
import com.plhery.peekdeliverytracker.ui.deliveries.RENAME_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.carrierPrompt
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.io.IOException
import java.time.Duration
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * The parcel states the demo doesn't reach, on this device's own window (B-S04, B-S17, B-S72-B-S75, B-ST06-B-ST11,
 * B-ST27, B-ST38): what the carrier says of a failed attempt and an exception, read as one element; an automatic
 * carrier swap read again each minute until its 12 hours are up; the likely carrier on its first day and after a week;
 * a shop's order number for each shop and a short number; a stalled parcel, with and without the carrier's hidden
 * journey abroad, and its help link; a carrier without automatic updates and Amazon's two; each failed check, a refused
 * number with the carrier that claims it, in the editor too; the carrier's and the provider's prompts opening the editor
 * on their carrier; the postcode not verified and refused, its edit's error line offline and no edit once archived; made
 * up scans; and the long texts: a name cut to 80, a gift note to 280, a long pickup address and the link's address on
 * one line, three at the largest text. Each moment saved as stateswalk/<row>-<moment>.png, with -dark, and -largest
 * at the largest text, in the test package's external files, which adb pulls. Signed in on fake deliveries
 * ([com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries], [ParcelWalk]) whose stores answer every read in
 * memory: nothing goes to the network, and no carrier's page opens. Each test keeps Zurich's time and the captures'
 * moment as the JVM's do, under Reduce Motion; the largest text is the composition's own Density at font scale 2,
 * Android's largest step, which the app reads as iOS's accessibility2, not the phone's setting. The zone is put back
 * after each test, the bundled catalog where a test installed another, and the preferences files the fakes write go
 * once its window has.
 */
@RunWith(AndroidJUnit4::class)
class ParcelStatesDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val en = AppLanguage.En

    /** When a parcel was archived, two days before the captures. */
    private val archivedTime = DateParser.isoString(CAPTURED.minus(Duration.ofDays(2)))

    private fun name(carrier: CarrierId): String = walk.identities.displayName(carrier, en)

    /** The demo, each parcel whose name starts with a key changed by its change, then `extra`. */
    private fun demo(vararg changes: Pair<String, (Parcel) -> Parcel>, extra: List<Parcel> = emptyList()): List<Parcel> {
        val changed = changes.associate { (name, change) -> DemoCards.parcel(name).id to change }
        return DemoCards.parcels().map { changed[it.id]?.invoke(it) ?: it } + extra
    }

    private fun List<Parcel>.named(name: String): Parcel = first { it.label.startsWith(name) }

    /** An unfiled parcel like the demo's probable one, numbered `n`, its carrier data `data`. */
    private fun unfiled(n: Int, number: String, label: String, data: CarrierData? = null): Parcel = DemoCards.probable().copy(
        id = UUID.fromString("00000000-0000-4000-8000-0000000009%02d".format(n)), trackingNumber = number, label = label, carrierData = data,
    )

    /** `parcel` with a newer scan of `stage` saying `words`, an hour before the captures. */
    private fun Parcel.scanned(stage: TrackingStage, words: String): Parcel = copy(
        trackingEvents = trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("$id-latest".toByteArray()), packageId = id, stage = stage, description = words,
            occurredAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        ),
    )

    private fun Parcel.failing(code: String): Parcel = copy(syncStatus = SyncStatus.Error, syncError = code)

    /** What the page asks of `parcel`'s carrier, as the pass words it. */
    private fun prompt(parcel: Parcel) = checkNotNull(carrierPrompt(parcel, CAPTURED, walk.localizer, walk.identities, en)) { "No prompt for ${parcel.label}" }

    /** A button of the part tagged `tag` that says `text`. */
    private fun buttonIn(tag: String, text: String): SemanticsMatcher = hasText(text) and hasClickAction() and hasAnyAncestor(hasTestTag(tag))

    private fun editor(): Pair<UUID?, CarrierId?>? = walk.onUi { walk.deliveries.carrierEditor?.let { it.pageId to it.initialCarrier } }

    private fun awaitEditor() {
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) }
        walk.awaitStill()
    }

    private fun cancelEditor() {
        walk.tap(CHANGE_CARRIER_CANCEL)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(CHANGE_CARRIER_SHEET) }
    }

    /** The part tagged `tag` scrolled into view, where it scrolls, and still. */
    private fun scrollTo(tag: String) {
        if (compose.onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            walk.node(tag).performScrollTo()
        }
        walk.settle()
    }

    /** The parcel's page at the part tagged `tag`, held still for its shot. */
    private fun openAt(parcel: Parcel, tag: String) {
        walk.open(parcel)
        scrollTo(tag)
    }

    // What the carrier says.

    /** B-S04, B-ST10: what the carrier says of a failed attempt and of an exception, under its name, read as one. */
    @Test
    fun whatTheCarrierSaysOfAFailedAttemptAndAnExceptionIsQuotedAndReadAsOne() {
        val parcels = demo(
            "Birthday gift" to { it.scanned(TrackingStage.FailedAttempt, "  Nobody was home. A notice was left.\n") },
            "Kind of Blue" to { it.scanned(TrackingStage.Exception, "Address label damaged. Held at the depot.") },
        )
        walk.show(parcels)
        openAt(parcels.named("Birthday gift"), PAGE_CARRIER_SAYS)
        assertTrue(walk.inPage(walk.text("attention.failed_attempt")))
        assertCarrierSays("Chronopost", "Nobody was home. A notice was left.")
        walk.shot("B-S04-failed-attempt")

        openAt(parcels.named("Kind of Blue"), PAGE_CARRIER_SAYS)
        assertTrue(walk.inPage(walk.text("attention.exception")))
        assertCarrierSays(name(CarrierId.Ups), "Address label damaged. Held at the depot.")
        walk.shot("B-ST10-exception")
    }

    /** One element to TalkBack: the carrier's name and its words, no description over them, nothing to tap. */
    private fun assertCarrierSays(carrier: String, words: String) {
        val note = walk.node(PAGE_CARRIER_SAYS).fetchSemanticsNode()
        assertEquals(listOf("What $carrier says", "“$words”"), walk.texts(PAGE_CARRIER_SAYS))
        assertNull(note.config.getOrNull(SemanticsProperties.ContentDescription))
        assertFalse(SemanticsActions.OnClick in note.config)
    }

    /** B-S04, B-ST10: the carrier's words at the largest text. */
    @Test
    fun whatTheCarrierSaysAtTheLargestText() {
        walk.fontScale = 2f
        val parcels = demo("Birthday gift" to { it.scanned(TrackingStage.FailedAttempt, "  Nobody was home. A notice was left.\n") })
        walk.show(parcels)
        openAt(parcels.named("Birthday gift"), PAGE_CARRIER_SAYS)
        assertCarrierSays("Chronopost", "Nobody was home. A notice was left.")
        walk.shot("B-S04-failed-attempt")
    }

    // The automatic swap.

    /** B-S17: a carrier swapped five minutes ago is said, read again each minute, and goes once its 12 hours are up. */
    @Test
    fun anAutomaticSwapIsSaidAndGoesTheMinuteItsTwelveHoursAreUp() {
        val changed = CAPTURED.minus(Duration.ofMinutes(5))
        val parcels = demo(
            "Birthday gift" to {
                it.copy(
                    carrierData = (it.carrierData ?: CarrierData()).copy(
                        autoChangedFrom = CarrierId.SwissPost, autoChangedTo = it.carrier, autoChangedAt = DateParser.isoString(changed),
                    ),
                )
            },
        )
        val words = walk.text("parcel.autoChangedCarrier", "carrier" to name(CarrierId.SwissPost))
        walk.show(parcels)
        walk.open(parcels.named("Birthday gift"))
        assertTrue(walk.inPage(words))
        walk.shot("B-S17-swapped")

        // The wall clock a minute on, read as the minute comes round: still within its 12 hours.
        compose.mainClock.autoAdvance = false
        walk.clock = CAPTURED.plusSeconds(60)
        compose.mainClock.advanceTimeBy(61_000)
        walk.recompose()
        assertTrue(walk.inPage(words))
        // Past its 12 hours, it stays until its minute comes round, then goes.
        walk.clock = changed.plus(Duration.ofHours(12)).plusSeconds(30)
        walk.recompose()
        assertTrue(walk.inPage(words))
        compose.mainClock.advanceTimeBy(61_000)
        walk.recompose()
        assertFalse(walk.inPage(words))
        walk.shot("B-S17-gone")
    }

    // What the page asks of the carrier.

    /**
     * B-S72, B-S73: the likely carrier said on its first day, the marks reading it as probable; after a week the reader
     * may choose another, with a button 44 tall that opens the editor.
     */
    @Test
    fun theLikelyCarrierOnItsFirstDayAndAfterAWeek() {
        val since = DateParser.isoString(CAPTURED.minus(Duration.ofDays(8)))
        val day1 = DemoCards.probable()
        val day8 = DemoCards.probable().copy(
            id = UUID.fromString("00000000-0000-4000-8000-000000000908"), trackingNumber = "DEMOLIKELY0008", label = "Reading lamp 💡", createdAt = since,
            carrierData = CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.GlsDe, since))),
        )
        walk.show(DemoCards.parcels() + listOf(day1, day8))
        openAt(day1, PAGE_PROMPT)
        assertTrue(walk.shows(PAGE_PROMPT, prompt(day1).text))
        assertTrue(walk.shows(PAGE_PROMPT, "doesn’t list it yet"))
        assertEquals(0, walk.pageButtons(walk.text("link.likely.choose")))
        val marks = compose.onNode(hasTestTag(PAGE_MARKS) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.ContentDescription).orEmpty().joinToString()
        val probably = walk.text("link.likely.carrier", "carrier" to name(CarrierId.GlsDe))
        assertTrue(marks, probably in marks)
        walk.shot("B-S72-day-one")

        openAt(day8, PAGE_PROMPT)
        assertTrue(walk.shows(PAGE_PROMPT, prompt(day8).text))
        assertTrue(walk.shows(PAGE_PROMPT, "still doesn’t list it after a week"))
        val choose = buttonIn(PAGE_PROMPT, walk.text("link.likely.choose"))
        val bounds = compose.onNode(choose).getUnclippedBoundsInRoot()
        assertTrue("$bounds is 44 tall at least", (bounds.bottom - bounds.top).value >= 43.5f)
        walk.shot("B-S73-day-eight")

        walk.tap(choose)
        walk.settle()
        awaitEditor()
        assertEquals(day8.id, editor()?.first)
        walk.shot("B-S73-choose", alsoDark = false)
        cancelEditor()
    }

    /** B-S74: an order number of each shop, and a short number, say where the tracking number is. */
    @Test
    fun aShopsOrderNumberAndAShortNumberSayWhereTheTrackingNumberIs() {
        val numbers = listOf(
            "amazon" to "111-0000000-0000001", "aliexpress" to "8100000000000001", "shein" to "GSH0A1B2C3D4E5F",
            "shopee" to "261025ABCD1234", "temu" to "PO-123-45678901234567890", "short" to "AB123",
        )
        val parcels = numbers.mapIndexed { index, (shop, number) -> unfiled(index + 1, number, if (shop == "short") "Short number" else "Order from $shop") }
        for ((parcel, shop) in parcels.zip(numbers.map { it.first })) {
            val expected = if (shop == "short") NonTrackingShape.Short else NonTrackingShape.Order(shop)
            assertEquals(parcel.trackingNumber, expected, parcel.nonTrackingShape)
        }
        walk.show(parcels)
        for ((parcel, shop) in parcels.zip(numbers.map { it.first })) {
            openAt(parcel, PAGE_PROMPT)
            val asked = prompt(parcel)
            assertTrue(shop, walk.shows(PAGE_PROMPT, asked.text))
            assertEquals(walk.text("link.chooseCarrier"), asked.action)
            assertEquals(1, compose.onAllNodes(buttonIn(PAGE_PROMPT, walk.text("link.chooseCarrier"))).fetchSemanticsNodes().size)
            walk.shot("B-S74-$shop")
        }
        assertTrue(walk.shows(PAGE_PROMPT, "shorter than most tracking numbers"))
    }

    // The quiet note.

    /** Cat tree as it was when its scans stopped, a week before the captures: still in transit, and quiet since. */
    private fun quiet(change: (Parcel) -> Parcel = { it }): List<Parcel> {
        val cutoff = CAPTURED.minus(Duration.ofHours(170))
        return demo("Cat tree" to { parcel -> change(parcel.copy(trackingEvents = parcel.trackingEvents.filter { DateParser.date(it.occurredAt)!! <= cutoff })) })
    }

    /** B-S75: a stalled parcel says why, and its help link opens the carrier's page and is counted. */
    @Test
    fun aStalledParcelSaysWhyAndItsHelpLinkIsCounted() {
        val parcels = quiet()
        val cat = parcels.named("Cat tree")
        assertEquals(ParcelAttention.Stalled, cat.attention(CAPTURED))
        walk.show(parcels)
        openAt(cat, PAGE_QUIET)
        assertTrue(walk.shows(PAGE_QUIET, "Parcels often go quiet"))
        assertFalse(walk.shows(PAGE_QUIET, "destination country"))
        walk.shot("B-S75-stalled")

        walk.tap(hasText("Get help from", substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PAGE_QUIET)))
        compose.waitUntil(LONG_MILLIS) { walk.asked().isNotEmpty() }
        assertEquals(1, walk.onUi { walk.source.actions.count { it == "parcel-carrier-help" } })
        val asked = walk.asked().single()
        assertEquals(Intent.ACTION_VIEW, asked.action)
        assertTrue(asked.dataString, asked.dataString!!.startsWith("https://"))
    }

    /** B-S75: a carrier that leaves out the journey abroad is said to, and its help is offered. */
    @Test
    fun aStalledParcelWhoseCarrierHidesTheJourneyAbroadSaysSo() {
        val parcels = quiet { it.copy(carrier = CarrierId.SpringGDS, trackingNumber = "100000000000000001", carrierData = null) }
        val cat = parcels.named("Cat tree")
        val catalog = walk.identities.catalog
        assertTrue(catalog.hidesDestinationLeg(cat))
        assertEquals(ParcelAttention.Stalled, cat.attention(CAPTURED))
        walk.show(parcels)
        openAt(cat, PAGE_QUIET)
        assertTrue(walk.shows(PAGE_QUIET, "Parcels often go quiet"))
        assertTrue(walk.shows(PAGE_QUIET, "doesn’t show this parcel’s journey in the destination country"))
        assertTrue(walk.shows(PAGE_QUIET, "Get help from ${catalog.helpLink(cat, en)!!.name}"))
        walk.shot("B-S75-abroad")
    }

    // Where no carrier is asked.

    /** The app's catalog with UPS followed through its site only, which none is but Amazon Logistics. */
    private fun linkOnlyUps(): CarrierIdentities {
        val bundled = SharedResources.text("CarrierCatalog.json")
        val ups = checkNotNull(Regex("\"ups\": \\{[^}]*?\"tracking\": \\{\\s*\"mode\": \"automatic\"").find(bundled))
        val changed = bundled.replaceRange(ups.range, ups.value.replace("\"automatic\"", "\"link-only\""))
        return runBlocking { CarrierIdentities.load(CarrierCatalog(changed.encodeToByteArray())) }
    }

    /**
     * B-ST06: a carrier without automatic updates says so and offers another, with no history; Amazon Logistics says
     * where it is tracked and Amazon Shipping that its history is gone, neither offering another carrier.
     */
    @Test
    fun aCarrierWithoutAutomaticUpdatesAndAmazonsSaySoAndOfferAnotherOnlyWhereItHelps() {
        val linkOnly = linkOnlyUps()
        assertFalse(linkOnly.catalog.tracksAutomatically(CarrierId.Ups))
        walk.onUi { CarrierCatalog.install(linkOnly.catalog) }
        val parcels = demo(
            "Kind of Blue" to { it.copy(trackingEvents = emptyList()) },
            "Moon lamp" to { it.copy(carrier = CarrierId.AmazonLogistics).failing("carrier:not_found") },
            "35mm film rolls" to { it.copy(carrier = CarrierId.AmazonShipping).failing("amazon_shipping_history_expired") },
        )
        walk.show(parcels, identities = linkOnly)
        val vinyl = parcels.named("Kind of Blue")
        openAt(vinyl, PAGE_CARRIER_HINT)
        assertTrue(walk.shows(PAGE_CARRIER_HINT, "Automatic updates aren’t available for UPS yet."))
        assertFalse(walk.exists(PAGE_JOURNAL))
        walk.shot("B-ST06-not-automatic")
        walk.tap(buttonIn(PAGE_CARRIER_HINT, "Change carrier"))
        walk.settle()
        awaitEditor()
        assertEquals(vinyl.id, editor()?.first)
        cancelEditor()

        openAt(parcels.named("Moon lamp"), PAGE_CARRIER_HINT)
        assertTrue(walk.shows(PAGE_CARRIER_HINT, "Amazon Logistics deliveries are usually tracked in Your Orders on Amazon."))
        assertEquals(0, walk.pageButtons("Change carrier"))
        assertFalse(walk.exists(PAGE_SYNC_ERROR))
        walk.shot("B-ST06-amazon-logistics")

        openAt(parcels.named("35mm film rolls"), PAGE_CARRIER_HINT)
        assertTrue(walk.shows(PAGE_CARRIER_HINT, "This is an Amazon Shipping parcel, but Amazon no longer provides its tracking history."))
        assertEquals(0, walk.pageButtons("Change carrier"))
        assertFalse(walk.exists(PAGE_SYNC_ERROR))
        walk.shot("B-ST06-history-expired")
    }

    // Why the last check failed.

    /** UPS's example number, whose shape and check digit are UPS's alone. */
    private val upsNumber = "1Z999AA10123456784"

    /**
     * B-ST07, B-ST27: each failed check in the app's words: details asked for, updated in the editor; details refused;
     * the carrier too busy, with nothing to do; a number refused, with the carrier that claims it, refused in the editor
     * too, and the switch to it.
     */
    @Test
    fun eachFailedCheckSaysWhyAndARefusedNumberOffersTheCarrierThatClaimsIt() {
        assertEquals(CarrierId.Ups, CarrierCatalog.shared.claimingCarrier(upsNumber, besides = CarrierId.Chronopost))
        val parcels = demo(
            "Trail weekend kit" to { it.failing("carrier:input_required") },
            "35mm film rolls" to { it.failing("carrier:invalid_input") },
            "Kind of Blue" to { it.failing("carrier:rate_limited") },
            "Birthday gift" to { it.copy(trackingNumber = upsNumber).failing("carrier:refused_number") },
        )
        walk.show(parcels) { refusals = setOf(NumberRefusalRequest(upsNumber, CarrierId.Chronopost)) }

        val kit = parcels.named("Trail weekend kit")
        openAt(kit, PAGE_SYNC_ERROR)
        assertEquals(listOf(walk.localizer.trackingFailureMessage("carrier:input_required")), walk.texts(PAGE_SYNC_ERROR))
        walk.shot("B-ST07-input-required")
        walk.tap(walk.pageButton("Update tracking details"))
        walk.settle()
        awaitEditor()
        assertEquals(kit.id to null, editor())
        cancelEditor()

        openAt(parcels.named("35mm film rolls"), PAGE_SYNC_ERROR)
        assertEquals(listOf(walk.localizer.trackingFailureMessage("carrier:invalid_input")), walk.texts(PAGE_SYNC_ERROR))
        walk.shot("B-ST07-invalid-input")

        openAt(parcels.named("Kind of Blue"), PAGE_SYNC_ERROR)
        assertEquals(listOf(walk.localizer.trackingFailureMessage("carrier:rate_limited")), walk.texts(PAGE_SYNC_ERROR))
        for (text in listOf("Update tracking details", "Change carrier")) assertEquals(text, 0, walk.pageButtons(text))
        walk.shot("B-ST07-rate-limited")

        val gift = parcels.named("Birthday gift")
        openAt(gift, PAGE_SYNC_ERROR)
        assertEquals(listOf("This carrier doesn’t issue tracking numbers like this one. It looks like one from UPS."), walk.texts(PAGE_SYNC_ERROR))
        assertEquals(0, walk.pageButtons("Update tracking details"))
        walk.shot("B-ST07-refused-number")
        walk.tap(walk.pageButton("Change carrier"))
        walk.settle()
        awaitEditor()
        assertEquals(gift.id to null, editor())
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_REFUSED) }
        assertTrue(walk.shows(CHANGE_CARRIER_REFUSED, "doesn’t issue numbers like this one"))
        walk.shot("B-ST27-refused")
        cancelEditor()

        walk.tap(walk.pageButton("Switch to UPS"))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.carrierChanges.toList() } == listOf(gift.id to CarrierId.Ups) }
        compose.waitUntil(LONG_MILLIS) { !walk.exists(PAGE_SYNC_ERROR) }
    }

    /**
     * B-ST08: the carrier's prompt for the postcode it needs, and the one it suggests, open the editor on that carrier;
     * the provider's opens it as it is.
     */
    @Test
    fun theCarriersAndTheProvidersPromptsOpenTheEditor() {
        val suggested = unfiled(
            11, "DEMOFAN0001", "Desk fan 🌀",
            CarrierData(routing = CarrierDataRouting(inputSuggested = CarrierInputSuggested(CarrierId.Dpd, CarrierInputSuggestedField.DpdPostcode))),
        )
        val parcels = demo(
            "Birthday gift" to {
                it.copy(carrierData = CarrierData(routing = CarrierDataRouting(inputNeeded = CarrierInputNeeded(CarrierId.Dpd, CarrierInputNeededField.DpdPostcode))))
            },
            "Kind of Blue" to {
                it.copy(carrierData = CarrierData(routing = CarrierDataRouting(providerInputNeeded = ProviderInputNeeded("Example Post", ProviderInputNeededField.DpdPostcode))))
            },
            extra = listOf(suggested),
        )
        walk.show(parcels)
        val addPostcode = walk.text("detail.inputNeededAction")

        val gift = parcels.named("Birthday gift")
        openAt(gift, PAGE_PROMPT)
        assertTrue(walk.shows(PAGE_PROMPT, prompt(gift).text))
        assertTrue(walk.shows(PAGE_PROMPT, "has this parcel. Add the delivery postcode to track it."))
        walk.shot("B-ST08-input-needed")
        walk.tap(buttonIn(PAGE_PROMPT, addPostcode))
        walk.settle()
        awaitEditor()
        assertEquals(gift.id to CarrierId.Dpd, editor())
        cancelEditor()

        openAt(suggested, PAGE_PROMPT)
        assertTrue(walk.shows(PAGE_PROMPT, prompt(suggested).text))
        walk.shot("B-ST08-input-suggested")
        walk.tap(buttonIn(PAGE_PROMPT, addPostcode))
        walk.settle()
        awaitEditor()
        assertEquals(suggested.id to CarrierId.Dpd, editor())
        cancelEditor()

        val vinyl = parcels.named("Kind of Blue")
        walk.open(vinyl)
        assertFalse(walk.exists(PAGE_PROMPT))
        val asked = hasText("Example Post needs the delivery postcode to look up this parcel.")
        compose.onNode(asked and hasAnyAncestor(hasTestTag(PARCEL_PAGE)), useUnmergedTree = true).performScrollTo()
        walk.settle()
        walk.shot("B-ST08-provider-input")
        walk.tap(walk.pageButton(addPostcode) and hasAnySibling(asked))
        walk.settle()
        awaitEditor()
        assertEquals(vinyl.id to null, editor())
        cancelEditor()
    }

    // The postcode's notes.

    private val dpd get() = name(CarrierId.Dpd)

    /** Trail weekend kit, with DPD, as `change` leaves its postcode. */
    private fun kit(change: (Parcel) -> Parcel): List<Parcel> = demo("Trail weekend kit" to change)

    private fun refused(parcel: Parcel): Parcel = parcel.copy(
        dpdPostcode = "0000",
        carrierData = (parcel.carrierData ?: CarrierData()).copy(routing = CarrierDataRouting(postcodeRefused = CarrierPostcodeRefused(CarrierId.Dpd))),
    )

    /** B-ST09: a postcode DPD couldn't check is said, with its edit. */
    @Test
    fun aPostcodeTheCarrierDidNotAcceptIsSaidWithItsEdit() {
        val parcels = kit { it.copy(dpdPostcode = "0000", carrierData = (it.carrierData ?: CarrierData()).copy(dpdPostcodeVerified = false)) }
        walk.show(parcels)
        openAt(parcels.named("Trail weekend kit"), PAGE_POSTCODE)
        assertTrue(walk.shows(PAGE_POSTCODE, "$dpd didn’t accept postcode 0000, so it shows fewer details."))
        assertEquals(1, walk.pageButtons(walk.text("detail.editPostcode")))
        walk.shot("B-ST09-not-verified")
    }

    /**
     * B-ST09, B-ST27: a postcode DPD refused is said, its edit opening the editor, whose save fails offline with the
     * error line.
     */
    @Test
    fun aRefusedPostcodeIsSaidAndItsEditsSaveSaysWhyItFailedOffline() {
        val given = checkNotNull(DemoCards.parcel("Trail weekend kit").dpdPostcode) { "The demo's kit has a postcode" }
        val parcels = kit(::refused)
        val kit = parcels.named("Trail weekend kit")
        walk.show(parcels)
        openAt(kit, PAGE_POSTCODE)
        assertTrue(walk.shows(PAGE_POSTCODE, "$dpd didn’t find this parcel with postcode 0000."))
        walk.shot("B-ST09-refused")

        walk.tap(walk.pageButton(walk.text("detail.editPostcode")))
        walk.settle()
        awaitEditor()
        assertEquals(kit.id to null, editor())
        walk.node(CHANGE_CARRIER_POSTCODE).performTextReplacement(given)
        compose.waitForIdle()
        walk.node(CHANGE_CARRIER_SAVE).assertIsEnabled()
        walk.onUi { walk.source.failure = IOException("offline") }
        walk.tap(CHANGE_CARRIER_SAVE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_ERROR) }
        assertTrue(walk.shows(CHANGE_CARRIER_ERROR, walk.text("error.connection")))
        assertTrue(walk.exists(CHANGE_CARRIER_SHEET))
        walk.awaitKeyboard(required = false)
        walk.shot("B-ST27-error")
    }

    /** B-ST09: archived, the refused postcode is still said, without its edit. */
    @Test
    fun archivedTheRefusedPostcodeIsSaidWithoutItsEdit() {
        val parcels = kit { refused(it).copy(archivedAt = archivedTime) }
        walk.show(parcels)
        walk.openArchive()
        openAt(parcels.named("Trail weekend kit"), PAGE_POSTCODE)
        assertTrue(walk.shows(PAGE_POSTCODE, "$dpd didn’t find this parcel with postcode 0000."))
        assertEquals(0, walk.pageButtons(walk.text("detail.editPostcode")))
        walk.shot("B-ST09-archived")
    }

    // Made-up scans.

    /** Moon lamp, its scans made up: each a day and 11 s after the last, each in a new place. */
    private fun generated(lamp: Parcel): Parcel {
        val start = CAPTURED.minus(Duration.ofDays(4))
        val moves = (0 until 4).map { step ->
            TrackingEvent(
                id = UUID.nameUUIDFromBytes("move-$step".toByteArray()), packageId = lamp.id, stage = TrackingStage.InTransit,
                description = "In transit", location = "Place $step", occurredAt = DateParser.isoString(start.plusSeconds(step * 86_111L)),
            )
        }
        return lamp.copy(trackingEvents = moves)
    }

    /** B-ST11: made-up scans are flagged, and the page says the carrier is still asked, by its name. */
    @Test
    fun madeUpScansAreFlaggedAndTheCarrierIsStillAsked() {
        val parcels = demo("Moon lamp" to ::generated)
        val lamp = parcels.named("Moon lamp")
        assertEquals(ParcelAttention.Generated, lamp.attention(CAPTURED))
        walk.show(parcels)
        walk.open(lamp)
        assertTrue(walk.inPage(walk.text("attention.generated")))
        val asking = "Peek keeps asking ${name(lamp.carrier)} and shows the next real scan here."
        compose.onNode(hasText(asking, substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)), useUnmergedTree = true).performScrollTo()
        walk.settle()
        assertTrue(walk.inPage(asking))
        walk.shot("B-ST11-generated")
    }

    // Long texts.

    /** B-ST38: a name of 100 characters is cut to 80 once trimmed, and the page takes it. */
    @Test
    fun aLongNameIsCutToEighty() {
        val gift = DemoCards.parcel("Birthday gift")
        walk.show(DemoCards.parcels())
        walk.open(gift)
        walk.tap(hasTestTag(PAGE_NAME) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)))
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(RENAME_ALERT) }
        val typed = "  " + "Lamp ".repeat(20) + "\n"
        walk.node(RENAME_FIELD).performTextReplacement(typed)
        compose.waitForIdle()
        walk.awaitKeyboard(required = false)
        walk.shot("B-ST38-rename")
        walk.tap(RENAME_SAVE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(RENAME_ALERT) }
        val kept = typed.trim().take(80)
        assertEquals(listOf(gift.id to kept), walk.onUi { walk.source.renames.toList() })
        compose.waitUntil(LONG_MILLIS) {
            compose.onAllNodes(hasTestTag(PAGE_NAME) and hasText(kept) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)), useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        walk.awaitStill()
        walk.shot("B-ST38-renamed", alsoDark = false)
    }

    /** B-ST38: a gift's note keeps to 280 characters. */
    @Test
    fun aGiftsNoteKeepsToTwoHundredAndEighty() {
        walk.show(DemoCards.parcels())
        walk.open(DemoCards.parcel("Birthday gift"))
        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_GIFT) }
        scrollTo(PARCEL_SHARE_GIFT)
        walk.flip(PARCEL_SHARE_GIFT)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_NOTE) }
        walk.node(PARCEL_SHARE_NOTE).performTextReplacement("n".repeat(300))
        compose.waitForIdle()
        assertEquals("n".repeat(280), walk.editable(PARCEL_SHARE_NOTE))
        walk.awaitKeyboard(required = false)
        scrollTo(PARCEL_SHARE_NOTE)
        walk.shot("B-ST38-gift-note")
    }

    /** B-ST38: a long pickup address is shown whole, wrapping, at the default text and the largest. */
    @Test
    fun aLongPickupAddressIsShownWhole() {
        val point = "Example Kiosk at the Central Station"
        val address = "Sample Street 1, South entrance by the travel centre, opposite the luggage lockers, 8001 Sample Town"
        val parcels = demo("New trainers" to { it.copy(carrierData = (it.carrierData ?: CarrierData()).copy(pickupPoint = "$point\n$address")) })
        walk.show(parcels)
        openAt(parcels.named("New trainers"), PAGE_PICKUP)
        assertEquals(listOf(walk.text("detail.pickupPoint"), point, address), walk.texts(PAGE_PICKUP_POINT))
        walk.shot("B-ST38-pickup")

        walk.fontScale = 2f
        walk.recompose()
        walk.settle()
        scrollTo(PAGE_PICKUP)
        assertEquals(listOf(walk.text("detail.pickupPoint"), point, address), walk.texts(PAGE_PICKUP_POINT))
        walk.shot("B-ST38-pickup")
    }

    /**
     * The share sheet's link address as laid out: the node found first (a search that waits for the app to be idle,
     * which it can't be from the main thread), then its layout read on the main thread.
     */
    private fun addressLayout(): TextLayoutResult {
        val node = compose.onNode(hasTestTag(PARCEL_SHARE_ADDRESS), useUnmergedTree = true).fetchSemanticsNode()
        return walk.onUi {
            val laid = mutableListOf<TextLayoutResult>()
            node.config[SemanticsActions.GetTextLayoutResult].action?.invoke(laid)
            laid.single()
        }
    }

    /** B-ST38: a long link address takes one line, cut short, and three at the largest text. */
    @Test
    fun aLongLinkAddressTakesOneLineAndThreeAtTheLargestText() {
        val gift = DemoCards.parcel("Birthday gift")
        val link = ParcelShare(id = "TestLink" + "0123456789".repeat(40), showNumber = false, gift = false, createdAt = "2026-05-01T09:00:00Z")
        walk.show(DemoCards.parcels()) { shareLinks[gift.id] = link }
        walk.open(gift)
        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_ADDRESS) }
        walk.awaitStill()
        var laid = addressLayout()
        assertEquals(1, laid.lineCount)
        assertTrue(laid.isLineEllipsized(0))
        walk.shot("B-ST38-link-address")

        walk.fontScale = 2f
        walk.recompose()
        walk.settle()
        laid = addressLayout()
        assertEquals(3, laid.lineCount)
        assertTrue(laid.isLineEllipsized(2))
        walk.shot("B-ST38-link-address")
    }

    // The largest text.

    /** B-ST07, B-S73, B-S74, B-S75, B-ST09, B-ST11: the states that say the most, at the largest text. */
    @Test
    fun theStatesThatSayTheMostAtTheLargestText() {
        walk.fontScale = 2f
        val since = DateParser.isoString(CAPTURED.minus(Duration.ofDays(8)))
        val day8 = unfiled(
            8, "DEMOLIKELY0008", "Reading lamp 💡",
            CarrierData(routing = CarrierDataRouting(likelyCarrier = LikelyCarrier(CarrierId.GlsDe, since))),
        ).copy(createdAt = since)
        val order = unfiled(1, "111-0000000-0000001", "Order from amazon")
        val cutoff = CAPTURED.minus(Duration.ofHours(170))
        val parcels = demo(
            "Birthday gift" to { it.copy(trackingNumber = upsNumber).failing("carrier:refused_number") },
            "Trail weekend kit" to ::refused,
            "Moon lamp" to ::generated,
            "Cat tree" to { cat -> cat.copy(trackingEvents = cat.trackingEvents.filter { DateParser.date(it.occurredAt)!! <= cutoff }) },
            extra = listOf(day8, order),
        )
        walk.show(parcels)
        openAt(parcels.named("Birthday gift"), PAGE_SYNC_ERROR)
        walk.shot("B-ST07-refused-number")
        openAt(day8, PAGE_PROMPT)
        val choose = compose.onNode(buttonIn(PAGE_PROMPT, walk.text("link.likely.choose"))).getUnclippedBoundsInRoot()
        assertTrue("$choose is 44 tall at least", (choose.bottom - choose.top).value >= 43.5f)
        walk.shot("B-S73-day-eight")
        openAt(order, PAGE_PROMPT)
        walk.shot("B-S74-amazon")
        openAt(parcels.named("Cat tree"), PAGE_QUIET)
        walk.shot("B-S75-stalled")
        openAt(parcels.named("Trail weekend kit"), PAGE_POSTCODE)
        walk.shot("B-ST09-refused")
        walk.open(parcels.named("Moon lamp"))
        walk.shot("B-ST11-generated")
    }
}
