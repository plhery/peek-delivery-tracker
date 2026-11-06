package com.plhery.peekdeliverytracker.ui

import android.content.ClipboardManager
import android.os.Bundle
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.ParcelFeedbackReason
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.core
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.deliveryUpdatesPlace
import com.plhery.peekdeliverytracker.core.localizationKey
import com.plhery.peekdeliverytracker.core.metadata
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CARRIER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_COUNTRY
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_FOUND
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PICKER
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SCAN
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SEVERAL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.ANNOUNCEMENT
import com.plhery.peekdeliverytracker.ui.deliveries.CARRIER_PICKER_AUTOMATIC
import com.plhery.peekdeliverytracker.ui.deliveries.CARRIER_PICKER_SEARCH
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_COUNTRY
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.CHANGE_CARRIER_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_ADD_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_CHIP
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOTE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_NOT_QUITE
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_QUESTION
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_REASON
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_WORDS_SHEET
import com.plhery.peekdeliverytracker.ui.deliveries.FEEDBACK_YES
import com.plhery.peekdeliverytracker.ui.deliveries.JOURNAL_DAY
import com.plhery.peekdeliverytracker.ui.deliveries.JOURNAL_ROW
import com.plhery.peekdeliverytracker.ui.deliveries.JOURNAL_TOGGLE
import com.plhery.peekdeliverytracker.ui.deliveries.LETTER_RAIL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_BELL
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_COPY_NUMBER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_NOT_NOW
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_EMAIL_OFFER_TURN_ON
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_GLOBE
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_MARKS
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_REFRESH
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_SHARE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_EMAIL
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_NOTIFICATIONS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_ALERTS_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_ADDRESS
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_CLOSE
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_COPY
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_GIFT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_NUMBER
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_SHARE_TITLE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.carrierPickerRowTag
import com.plhery.peekdeliverytracker.ui.deliveries.numberRowTag
import java.io.IOException
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What TalkBack receives from a parcel's page and what opens over it (B-AX01 to B-AX19), read on this device's own
 * window from the accessibility tree a service is given ([AccessTree]): the pass's carrier, globe, share and bell named
 * for what a tap does, the bell's drawing silent, the name's rename said as its double tap; the carrier's row in its
 * editor; the number's copy saying it copied, the journey's rail read as one with its step, the history's header
 * reading its state, its days headings and its rows one element each; Add's heading, close, the number it found, its
 * carrier line and its numbers; the carrier picker's chosen row, its letter rail moved a letter at a time and the
 * postcode's country as a menu; the share sheet's address read whole and its switches; the alerts' switches; the
 * question's answers and chips; the email's offer and what it says. What the app says through the window's polite live
 * region is read frame by frame on the test's clock, held for it. Signed in as the synthetic reader@example.com on fake
 * deliveries whose stores answer every read in memory, the page beside the list or over it as the window lays them out;
 * the clipboard a copy changes is put back. Each test keeps Zurich's time and the captures' moment; the preferences files
 * the fakes write are put back.
 */
@RunWith(AndroidJUnit4::class)
class PageAccessDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose)
    private val tree = AccessTree(compose)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk).around(tree.logsTheWindowOnFailure)

    /** A parcel its carrier answers for, with its history and one number. */
    private val gift: Parcel get() = DemoCards.parcel("Birthday gift")

    /** A parcel with places on its map. */
    private val lamp: Parcel get() = DemoCards.parcel("Moon lamp")

    private val delivered: Parcel get() = DemoCards.parcels().first { it.currentStage == TrackingStage.Delivered && it.archivedAt == null }

    private fun fullName(parcel: Parcel): String = walk.identities.shown(parcel, walk.localizer).fullName

    /** The node `id` once `check` holds of it, as TalkBack reads it ([spoken]) or as it stands. */
    private fun awaitReading(id: String, what: String, check: (AccessibilityNodeInfo) -> Boolean): AccessibilityNodeInfo =
        tree.await("$id $what") { nodes -> nodes.firstOrNull { it.viewIdResourceName == id && check(it) } }

    /** `body` with whatever the clipboard held put back after it, as the copies it makes leave it. */
    private fun <T> keepingTheClipboard(body: () -> T): T {
        val clipboard = compose.activity.getSystemService(ClipboardManager::class.java)
        val before = walk.onUi { clipboard.primaryClip }
        try {
            return body()
        } finally {
            walk.onUi { if (before != null) clipboard.setPrimaryClip(before) else clipboard.clearPrimaryClip() }
        }
    }

    // B-AX01, B-AX02, B-AX11, B-AX14, B-AX19: the pass's header.

    /**
     * B-AX01, B-AX02, B-AX11, B-AX19, B-AX14: the pass's carrier is one button read "detail.changeCarrierFrom" with the
     * carrier's full name, then the delivery; the globe, share and bell are buttons named "map.open", "link.shareAria"
     * and "detail.mute" (or "detail.unmute") alone, each a finger's 44 across, the bell's drawing no stop; the bell's
     * double tap mutes and it then says the other. The name is a button whose double tap is said as "detail.editTitle".
     * Nothing on the page stops without saying something. The carrier's button opens its editor, whose carrier row is
     * one button read "add.carrier" with the carrier as its state.
     */
    @Test
    fun theHeaderNamesItsButtonsByWhatATapDoesAndTheBellSaysItsOther() {
        walk.show(DemoCards.parcels())
        val lamp = lamp
        walk.open(lamp)
        val marks = tree.awaitId(PAGE_MARKS)
        assertTrue(marks.isButton)
        assertEquals("Nothing in the carrier's button is a stop of its own", 0, marks.childCount)
        val from = walk.text("detail.changeCarrierFrom", "carrier" to fullName(lamp))
        assertTrue(marks.spoken, marks.spoken.startsWith(from))
        for ((id, key) in listOf(PAGE_GLOBE to "map.open", PAGE_SHARE to "link.shareAria")) {
            val button = tree.awaitId(id)
            assertEquals(walk.text(key), button.spoken)
            assertTrue(button.isButton)
            button.assertTarget(id)
        }
        val (mute, unmute) = listOf("detail.mute", "detail.unmute").map(walk::text).let { if (lamp.notificationsMuted) it.reversed() else it }
        val bell = tree.awaitId(PAGE_BELL)
        assertEquals(mute, bell.spoken)
        assertTrue(bell.isButton)
        bell.assertTarget(PAGE_BELL)
        assertTrue("The bell's drawing is a stop", tree.under(bell).drop(1).none { it.isScreenReaderFocusable })
        assertTrue(bell.click())
        awaitReading(PAGE_BELL, "saying \"$unmute\"") { it.spoken == unmute }

        val name = tree.awaitId(PAGE_NAME)
        assertEquals(lamp.label, name.spoken)
        assertTrue(name.isButton)
        assertEquals(walk.text("detail.editTitle"), name.clickLabel)
        tree.assertNoBlankStops(tree.within(PARCEL_PAGE))

        assertTrue(tree.awaitId(PAGE_MARKS).click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(CHANGE_CARRIER_SHEET) }
        compose.reveal(CHANGE_CARRIER_ROW)
        val row = tree.awaitId(CHANGE_CARRIER_ROW)
        assertEquals(walk.text("add.carrier"), row.spoken)
        assertTrue(row.isButton)
        assertEquals(0, row.childCount)
        assertFalse("The carrier row reads no carrier", row.state.isNullOrEmpty())
    }

    // B-AX03, B-AX04, A-AX15, A-AX09, B-AX19: the number, the journey and the history.

    /**
     * B-AX03, A-AX09, B-AX19, A-AX15, B-AX04: the number's copy is one button read "detail.copyTracking", 44 across;
     * its double tap makes it read "detail.copied" for two seconds, which up to Android 12L the window's live region says
     * too (from 13 the system's overlay says the copy). The journey's rail is one element read "native.deliveryProgress"
     * with its step as its state. Check now is the button "detail.checkNow". The history's header is a button reading
     * "design.lessJourney" while the days show and "design.fullJourney" once its double tap hides them; each day is a
     * heading, read as written rather than in the capitals drawn, and each row one element.
     */
    @Test
    fun theNumbersCopySaysItCopiedAndTheJourneyAndHistoryReadAsOne() {
        walk.show(DemoCards.parcels())
        val gift = gift
        walk.open(gift)
        val stage = gift.currentStage
        val steps = TrackingStage.core.size
        val current = (stage?.metadata?.progress ?: 0).coerceIn(0, steps - 1)
        val step = walk.text("progress.step", "step" to current + 1, "total" to steps, "stage" to walk.text(stage?.localizationKey ?: "status.pending"))
        val progress = walk.text("native.deliveryProgress")
        val rail = tree.await("The journey's rail", read = { tree.within(PARCEL_PAGE) }) { nodes -> nodes.firstOrNull { it.isStop && it.spoken == progress } }
        assertEquals(step, rail.state)
        assertEquals(0, rail.childCount)

        compose.reveal(PAGE_REFRESH)
        val refresh = tree.awaitId(PAGE_REFRESH)
        assertEquals(walk.text("detail.checkNow"), refresh.spoken)
        assertTrue(refresh.isButton)

        compose.reveal(PAGE_COPY_NUMBER)
        val copyTracking = walk.text("detail.copyTracking")
        val copied = walk.text("detail.copied")
        val copy = tree.awaitId(PAGE_COPY_NUMBER)
        assertTrue(copy.spoken, copy.spoken.startsWith(copyTracking))
        assertTrue(copy.isButton)
        assertEquals(0, copy.childCount)
        copy.assertTarget(PAGE_COPY_NUMBER)
        keepingTheClipboard {
            tree.holdingTheClock {
                assertTrue(copy.click())
                tree.await("The copy saying \"$copied\"", read = tree::framed) { nodes ->
                    nodes.firstOrNull { it.viewIdResourceName == PAGE_COPY_NUMBER && it.spoken.startsWith(copied) }
                }
                if (!Device.confirmsCopies) tree.awaitSaid(ANNOUNCEMENT, copied)
                compose.mainClock.advanceTimeBy(COPIED_MILLIS)
                tree.await("The copy saying \"$copyTracking\" again", read = tree::framed) { nodes ->
                    nodes.firstOrNull { it.viewIdResourceName == PAGE_COPY_NUMBER && it.spoken.startsWith(copyTracking) }
                }
            }
        }

        compose.reveal(JOURNAL_TOGGLE)
        val toggle = tree.awaitId(JOURNAL_TOGGLE)
        assertTrue(toggle.isButton)
        assertEquals(walk.text("design.lessJourney"), toggle.state)
        compose.reveal(compose.onAllNodes(hasTestTag(JOURNAL_ROW), useUnmergedTree = true).onFirst())
        val shown = tree.nodes()
        val days = shown.filter { it.viewIdResourceName == JOURNAL_DAY }
        assertTrue("No day of the history shown\n${tree.dump(shown)}", days.isNotEmpty())
        for (day in days) {
            assertTrue(day.isHeading)
            assertEquals(0, day.childCount)
            assertTrue(day.spoken.isNotEmpty())
        }
        assertTrue("Each day is read in capitals: ${days.map { it.spoken }}", days.any { day -> day.spoken.any(Char::isLowerCase) })
        val rows = shown.filter { it.viewIdResourceName == JOURNAL_ROW }
        assertTrue("No row of the history shown\n${tree.dump(shown)}", rows.isNotEmpty())
        for (row in rows) assertTrue("A row of the history isn't read as one\n${tree.dump(tree.under(row))}", tree.oneElement(row))

        compose.reveal(JOURNAL_TOGGLE)
        assertTrue(tree.awaitId(JOURNAL_TOGGLE).click())
        awaitReading(JOURNAL_TOGGLE, "reading the full journey") { it.state == walk.text("design.fullJourney") }
        tree.awaitGone("The history's days") { nodes -> nodes.none { it.viewIdResourceName == JOURNAL_DAY || it.viewIdResourceName == JOURNAL_ROW } }
    }

    // B-AX07, B-AX08, B-AX19, B-AX20: Add.

    /** Add, opened from the list's button. */
    private fun openAdd() {
        assertTrue(tree.awaitId(ADD_BUTTON).click())
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
    }

    /** `text` in Add's tracking field, settled. */
    private fun type(text: String) {
        walk.node(ADD_TRACKING).performTextReplacement(text)
        walk.settle()
    }

    /**
     * B-AX07, B-AX19, B-AX20, B-AX08: Add's title is a heading, "add.title"; its close is the button "common.close", a
     * finger's 44 across, as scan is where it shows. A number found in a sentence is read "add.foundText" with the number
     * as printed; the carrier line is one button read with the carrier, "add.detectedCarrier" and "add.line.change",
     * which its double tap is said as. An Amazon number's carrier line is off, nothing said of a change.
     */
    @Test
    fun addHeadsItselfAndItsCarrierLineSaysWhatATapChanges() {
        walk.show(DemoCards.parcels())
        openAdd()
        val title = tree.awaitId(ADD_TITLE)
        assertTrue(title.isHeading)
        assertEquals(walk.text("add.title"), title.spoken)
        val close = checkNotNull(tree.byId(ADD_CLOSE))
        assertEquals(walk.text("common.close"), close.spoken)
        assertTrue(close.isButton)
        close.assertTarget(ADD_CLOSE)
        tree.byId(ADD_SCAN)?.let { scan ->
            assertTrue(scan.isButton)
            scan.assertTarget(ADD_SCAN)
        }

        type("Your parcel $NUMBER is on its way")
        val foundText = walk.text("add.foundText", "number" to CarrierCatalog.format(NUMBER, CarrierId.Ups))
        val found = awaitReading(ADD_FOUND, "saying \"$foundText\"") { it.spoken == foundText }
        assertEquals(0, found.childCount)
        val change = walk.text("add.line.change")
        val detected = "UPS, ${walk.text("add.detectedCarrier")}, $change"
        val line = awaitReading(ADD_CARRIER, "saying \"$detected\"") { it.spoken == detected }
        assertTrue(line.isButton)
        assertEquals(0, line.childCount)
        assertEquals(change, line.clickLabel)

        type(AMAZON)
        val amazon = awaitReading(ADD_CARRIER, "off for an Amazon number") { !it.isEnabled }
        assertNull(amazon.action(AccessibilityNodeInfo.ACTION_CLICK))
        assertFalse(amazon.spoken, amazon.spoken.endsWith(change))
    }

    /**
     * B-AX08: several numbers are listed under a heading, each a button read by its carrier and its number as printed;
     * the chosen one alone is heard selected, the others with nothing of a choice; another chosen takes it.
     */
    @Test
    fun severalNumbersAreButtonsAndOnlyTheChosenOneIsHeardSelected() {
        walk.show(DemoCards.parcels())
        openAdd()
        type("UPS $NUMBER\nUPS $SECOND")
        val several = tree.awaitId(ADD_SEVERAL)
        assertTrue(several.isHeading)
        assertEquals(walk.text("door.several.title.many", "count" to 2), several.spoken)
        val first = tree.awaitId(numberRowTag(NUMBER))
        val second = checkNotNull(tree.byId(numberRowTag(SECOND)))
        assertEquals("UPS, ${CarrierCatalog.format(NUMBER, CarrierId.Ups)}", first.spoken)
        assertTrue(first.isButton && second.isButton)
        assertEquals(0, first.childCount)
        assertTrue(first.chosen)
        assertFalse("A number not chosen is read as a choice", second.isCheckable)
        assertTrue(second.click())
        awaitReading(numberRowTag(SECOND), "chosen") { it.chosen }
        assertFalse(checkNotNull(tree.byId(numberRowTag(NUMBER))).isCheckable)
    }

    // B-AX10, B-AX12, B-AX13, B-AX19: the carrier picker.

    /**
     * B-AX08, B-AX12, B-AX13, B-AX19, B-AX10: for a number no carrier is found for, the carrier line's double tap is said
     * as "add.line.change" ("add.line.choose" is for a number several carriers know, as on the iPhone) and opens the
     * picker, where the automatic row is the one heard selected and the carriers'
     * rows are buttons, each one element, none heard as a choice. The letter rail is one element 44 wide read
     * "picker.jump" with its letter, a range from the first letter that setting its progress moves. A carrier chosen
     * from a search brings the postcode's country, a drop-down list read "add.requirement.postcodeCountry" with the
     * country, whose menu has that country selected; the picker opened again hears that carrier selected, the automatic
     * row no longer.
     */
    @Test
    fun thePickerHearsTheChosenRowItsRailMovesByLettersAndTheCountryIsAMenu() {
        walk.show(DemoCards.parcels())
        openAdd()
        type(MONDIAL_NUMBER)
        val change = walk.text("add.line.change")
        val line = awaitReading(ADD_CARRIER, "saying \"$change\"") { it.clickLabel == change }
        assertTrue(line.spoken, line.spoken.endsWith(change))
        assertTrue(line.click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PICKER) }
        walk.awaitStill()
        val automatic = tree.awaitId(CARRIER_PICKER_AUTOMATIC)
        assertTrue(automatic.isButton)
        assertTrue(automatic.chosen)
        val rows = tree.nodes().filter { it.viewIdResourceName?.startsWith(PICKER_ROW) == true }
        assertTrue("No carrier's row shown", rows.isNotEmpty())
        for (row in rows) {
            assertTrue(row.isButton)
            assertEquals(0, row.childCount)
            assertFalse("${row.spoken} is read as a choice", row.isCheckable)
        }

        val rail = tree.awaitId(LETTER_RAIL)
        assertEquals(walk.text("picker.jump"), rail.spoken)
        assertEquals(0, rail.childCount)
        rail.assertTarget(LETTER_RAIL, along = false)
        assertEquals(0f, checkNotNull(rail.rangeInfo) { "The rail has no range" }.current, 0f)
        val letter = rail.state
        assertFalse(letter.isNullOrEmpty())
        val progress = Bundle().apply { putFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, 2f) }
        assertTrue(rail.performAction(AccessibilityAction.ACTION_SET_PROGRESS.id, progress))
        val moved = awaitReading(LETTER_RAIL, "two letters on") { node -> node.rangeInfo?.current?.let { abs(it - 2f) < 0.01f } == true }
        assertNotEquals(letter, moved.state)

        walk.node(CARRIER_PICKER_SEARCH).performTextInput("Mondial")
        walk.settle()
        val mondial = walk.identities.catalog.info(CarrierId.MondialRelay, walk.language).displayName
        val row = tree.awaitId(carrierPickerRowTag(CarrierId.MondialRelay))
        assertTrue(row.spoken, row.spoken.startsWith(mondial))
        assertTrue(row.click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { !walk.exists(ADD_PICKER) }

        compose.reveal(ADD_COUNTRY)
        val country = tree.awaitId(ADD_COUNTRY)
        assertEquals("android.widget.Spinner", country.className)
        assertEquals(walk.text("add.requirement.postcodeCountry"), country.spoken)
        assertEquals(0, country.childCount)
        val name = country.state.orEmpty()
        assertTrue("The menu reads no country", name.isNotEmpty())
        assertTrue(country.click())
        walk.settle()
        val options = tree.await("The countries' menu") { nodes -> nodes.filter { it.viewIdResourceName?.startsWith("$CHANGE_CARRIER_COUNTRY.") == true }.ifEmpty { null } }
        val shown = options.firstOrNull { option -> option.spokenParts.any { it.endsWith(name) } }
        assertTrue("$name is not selected in its menu\n${tree.dump(options)}", shown?.chosen == true)
        walk.back()
        tree.awaitGone("The countries' menu") { nodes -> nodes.none { it.viewIdResourceName?.startsWith("$CHANGE_CARRIER_COUNTRY.") == true } }

        compose.reveal(ADD_CARRIER)
        assertTrue(tree.awaitId(ADD_CARRIER).click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PICKER) }
        awaitReading(CARRIER_PICKER_AUTOMATIC, "no longer selected") { !it.chosen }
        walk.node(CARRIER_PICKER_SEARCH).performTextInput("Mondial")
        walk.settle()
        awaitReading(carrierPickerRowTag(CarrierId.MondialRelay), "selected") { it.chosen }
    }

    // B-AX16, A-AX22, A-AX09: the share sheet and the alerts.

    /**
     * B-AX16, A-AX22, A-AX09, B-AX19: the share sheet's title is a heading and its close the button "common.close", 44
     * across; its address is read "share.address" with the whole address, scheme and all, as its state, where the words
     * shown leave the scheme out. Each switch is one switch, its state read; a double tap flips it. Copy is a button a
     * finger's 44 tall that reads "detail.copied" once it has copied, which up to Android 12L the window's live region
     * says as "link.copied".
     */
    @Test
    fun theShareSheetReadsItsWholeAddressItsSwitchesAndItsCopy() {
        val gift = gift
        walk.show(DemoCards.parcels()) { shareLinks[gift.id] = ParcelShare(id = LINK_ID, showNumber = false, gift = false, createdAt = SHARED_AT) }
        walk.open(gift)
        walk.tap(PAGE_SHARE)
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_SHARE_ADDRESS) }
        walk.awaitStill()
        assertTrue(tree.awaitId(PARCEL_SHARE_TITLE).isHeading)
        val close = checkNotNull(tree.byId(PARCEL_SHARE_CLOSE))
        assertEquals(walk.text("common.close"), close.spoken)
        assertTrue(close.isButton)
        close.assertTarget(PARCEL_SHARE_CLOSE)

        compose.reveal(PARCEL_SHARE_ADDRESS)
        val address = tree.awaitId(PARCEL_SHARE_ADDRESS)
        assertEquals(walk.text("share.address"), address.spoken)
        val whole = address.state.orEmpty()
        assertTrue(whole, Regex("^https?://").containsMatchIn(whole) && whole.contains(LINK_ID))
        assertTrue("$whole doesn't end with the words shown, ${address.text}", whole.endsWith(address.text?.toString().orEmpty()))

        for (id in listOf(PARCEL_SHARE_NUMBER, PARCEL_SHARE_GIFT)) {
            compose.reveal(id)
            val switch = tree.awaitId(id)
            assertTrue(switch.isSwitch)
            assertTrue("$id isn't read as one switch", tree.oneElement(switch))
            assertFalse(switch.ticked)
        }
        compose.reveal(PARCEL_SHARE_NUMBER)
        assertTrue(tree.awaitId(PARCEL_SHARE_NUMBER).click())
        awaitReading(PARCEL_SHARE_NUMBER, "on") { it.ticked }
        walk.settle()

        compose.reveal(PARCEL_SHARE_COPY)
        val copy = tree.awaitId(PARCEL_SHARE_COPY)
        assertTrue(copy.isButton)
        copy.assertTarget(PARCEL_SHARE_COPY)
        val copied = walk.text("detail.copied")
        keepingTheClipboard {
            tree.holdingTheClock {
                assertTrue(copy.click())
                tree.await("Copy saying \"$copied\"", read = tree::framed) { nodes ->
                    nodes.firstOrNull { it.viewIdResourceName == PARCEL_SHARE_COPY && it.spoken == copied }
                }
                if (!Device.confirmsCopies) tree.awaitSaid(ANNOUNCEMENT, walk.text("link.copied"))
            }
        }
    }

    /**
     * A-AX22, B-AX01, B-AX19: with the account's delivery email on, the bell is read "email.parcel.open" and opens the
     * parcel's alerts, headed "email.parcel.title" with the parcel's name, closed by the button "common.close" 44 across;
     * its notifications and email are each one switch, its state read, which a double tap flips.
     */
    @Test
    fun withTheEmailTheBellOpensAlertsHeadedByNameWithTwoSwitches() {
        walk.show(DemoCards.parcels()) { signedInWithEmail(true) }
        val gift = gift
        walk.open(gift)
        val bell = awaitReading(PAGE_BELL, "opening the alerts") { it.spoken == walk.text("email.parcel.open") }
        assertTrue(bell.click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_ALERTS) }
        walk.awaitStill()
        val title = tree.awaitId(PARCEL_ALERTS_TITLE)
        assertTrue(title.isHeading)
        assertEquals(walk.text("email.parcel.title", "name" to gift.label), title.spoken)
        val close = checkNotNull(tree.byId(PARCEL_ALERTS_CLOSE))
        assertEquals(walk.text("common.close"), close.spoken)
        assertTrue(close.isButton)
        close.assertTarget(PARCEL_ALERTS_CLOSE)
        for (id in listOf(PARCEL_ALERTS_NOTIFICATIONS, PARCEL_ALERTS_EMAIL)) {
            compose.reveal(id)
            val switch = tree.awaitId(id)
            assertTrue(switch.isSwitch)
            assertTrue("$id isn't read as one switch", tree.oneElement(switch))
        }
        val email = tree.awaitId(PARCEL_ALERTS_EMAIL)
        val was = email.ticked
        assertTrue(email.click())
        awaitReading(PARCEL_ALERTS_EMAIL, "flipped") { it.ticked != was }
    }

    // B-AX06: the email's offer.

    /**
     * B-AX06: on a delivered parcel of an account that never chose, the email's offer has its title a heading and Turn
     * on and Not now as buttons found by their ids, each one element. Turn on failing offline is said through the
     * window's live region as "email.setting.failed" and the offer shows it; Turn on again is said as "email.offer.on"
     * with where to change it, and the offer gives way to its line.
     */
    @Test
    fun theEmailOfferIsHeadedAndSaysWhetherTurningItOnWorked() {
        walk.show(DemoCards.parcels()) { signedInWithEmail(null) }
        walk.open(delivered)
        compose.reveal(PAGE_EMAIL_OFFER)
        val offer = tree.await("The offer", read = { tree.within(PAGE_EMAIL_OFFER) }) { it.takeIf(List<AccessibilityNodeInfo>::isNotEmpty) }
        assertTrue("The offer's title is no heading\n${tree.dump(offer)}", offer.any { it.isHeading && it.spoken.isNotEmpty() })
        for (id in listOf(PAGE_EMAIL_OFFER_TURN_ON, PAGE_EMAIL_OFFER_NOT_NOW)) {
            val button = checkNotNull(offer.firstOrNull { it.viewIdResourceName == id }) { "No $id\n${tree.dump(offer)}" }
            assertTrue(button.isButton)
            assertTrue("$id isn't read as one", tree.oneElement(button))
            assertTrue("$id says nothing", button.spoken.isNotEmpty())
        }

        walk.onUi { walk.source.failure = IOException("offline") }
        tree.holdingTheClock {
            assertTrue(tree.awaitId(PAGE_EMAIL_OFFER_TURN_ON).click())
            tree.awaitSaid(ANNOUNCEMENT, walk.text("email.setting.failed"))
        }
        tree.awaitId(PAGE_EMAIL_OFFER_ERROR)

        walk.onUi { walk.source.failure = null }
        compose.reveal(PAGE_EMAIL_OFFER_TURN_ON)
        tree.holdingTheClock {
            assertTrue(tree.awaitId(PAGE_EMAIL_OFFER_TURN_ON).click())
            tree.awaitSaid(ANNOUNCEMENT, walk.text("email.offer.on", "place" to walk.localizer.deliveryUpdatesPlace))
        }
        tree.awaitId(PAGE_EMAIL_OFFER_ON)
    }

    // B-AX17: the question.

    /**
     * B-AX17, B-AX19: the question's answers are buttons a finger's 44 tall, as each reason is; a reason given is
     * thanked through the window's live region, "feedback.pip.sent". In the words' sheet the reason given is the chip
     * heard selected, the others with nothing of a choice until chosen, each 44 tall; the note's field is read with its
     * label "feedback.note" alone, which no other stop of the sheet reads.
     */
    @Test
    fun theQuestionsAnswersAreFingerSizedItsThanksAreSaidAndItsChipsHeardSelected() {
        walk.show(listOf(gift, DemoCards.probable()))
        walk.open(gift)
        compose.reveal(FEEDBACK_QUESTION)
        walk.settle()
        for (id in listOf(FEEDBACK_YES, FEEDBACK_NOT_QUITE)) {
            compose.reveal(id)
            val pill = tree.awaitId(id)
            assertTrue(pill.isButton)
            pill.assertTarget(id, across = false)
        }
        assertTrue(tree.awaitId(FEEDBACK_NOT_QUITE).click())
        walk.settle()
        val status = FEEDBACK_REASON + ParcelFeedbackReason.Status.name
        compose.reveal(status)
        val reason = tree.awaitId(status)
        assertTrue(reason.isButton)
        reason.assertTarget(status, across = false)
        tree.holdingTheClock {
            assertTrue(reason.click())
            tree.awaitSaid(ANNOUNCEMENT, walk.text("feedback.pip.sent"))
        }
        walk.settle()

        compose.reveal(FEEDBACK_ADD_NOTE)
        assertTrue(tree.awaitId(FEEDBACK_ADD_NOTE).click())
        walk.settle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FEEDBACK_WORDS_SHEET) }
        walk.awaitStill()
        val given = tree.awaitId(FEEDBACK_CHIP + ParcelFeedbackReason.Status.name)
        assertTrue(given.isButton)
        assertTrue(given.chosen)
        given.assertTarget("The chip given", across = false)
        val arrived = FEEDBACK_CHIP + ParcelFeedbackReason.Arrived.name
        val other = checkNotNull(tree.byId(arrived))
        assertFalse("A chip not chosen is read as a choice", other.isCheckable)
        assertTrue(other.click())
        awaitReading(arrived, "chosen") { it.chosen }
        val note = tree.awaitId(FEEDBACK_NOTE)
        assertTrue(note.isEditable)
        val label = walk.text("feedback.note")
        assertEquals("The note's field isn't read with its label alone", label, note.spoken)
        val apart = tree.within(FEEDBACK_WORDS_SHEET).filter { it.isStop && it.viewIdResourceName != FEEDBACK_NOTE && label in it.spokenParts }
        assertTrue("The note's label is read apart from its field\n${tree.dump(apart)}", apart.isEmpty())
    }

    private companion object {
        /** A UPS number of the carrier's own examples, and the next one. */
        const val NUMBER = "1Z999AA10123456784"
        const val SECOND = "1Z999AA10123456793"

        /** A number no carrier is found for, which Mondial Relay can carry with a postcode. */
        const val MONDIAL_NUMBER = "76434219"

        /** A made-up Amazon number. */
        const val AMAZON = "FR4829173650"

        /** The prefix of the carrier picker's rows' ids. */
        const val PICKER_ROW = "carrierPicker.carrier."

        /** A made-up link of the share service's. */
        const val LINK_ID = "TestLinkAccess"
        const val SHARED_AT = "2026-05-01T09:00:00Z"

        /** Past the two seconds a copy reads "detail.copied". */
        const val COPIED_MILLIS = 2_500L
    }
}
