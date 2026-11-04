package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.HapticFeedbackConstants
import android.view.InputDevice
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.ArchiveSwipeState
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.OAuthFlow
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.ARCHIVE_ACTION
import com.plhery.peekdeliverytracker.ui.deliveries.ARCHIVE_KEY
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardId
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_AVERAGE
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COUNTRIES
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_PERSONAL_BEST
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_POPOVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_STAMP
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.io.File
import java.util.UUID
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the phone's vibrator is asked to play where the iPhone gives feedback (A-H01, A-H02, A-H03, A-H04, A-H05, A-H06,
 * A-H08, X-AD17), on this device's own window: the tabs and a link bringing the deliveries forward, a delivery's
 * postmark, a parcel added reaching the list's middle, a card's swipe arming archive, Archive from the open card's
 * button and from TalkBack, the archive's shelf, the passport's items, and a tab with the phone's touch feedback off.
 * Emulators don't vibrate, but the vibrator service keeps each request in its history: each step reads
 * `dumpsys vibrator_manager` through UiAutomation before and after, and takes the records of the app's package it added
 * once nothing new has come for a second ([VibratorRecords]). Android 12 names what was asked as prebaked effects (TICK,
 * CLICK, as the window manager maps a constant) or a composition's primitives with their scale; Android 17 gives the
 * constant too (`performHapticFeedback(constant=N)`); [VibratorDump] reads both, and a record it can't read fails the
 * test with its lines. Each step's added lines are saved as hapticswalk/<row>-<step>.txt in the test package's external
 * files, which adb pulls. An impact below full intensity is a primitive where the device's vibrator has it
 * ([Vibrator.areAllPrimitivesSupported]), as Haptics.kt chooses. Signed in as the synthetic reader@example.com (the
 * demo's session for the passport) on fake deliveries ([ParcelWalk]), which reach no server; each test keeps Zurich's
 * time and the captures' moment, and the zone, the phone's touch feedback and the preferences files the fakes write are
 * put back after it. Skipped on a device without a vibrator.
 */
@RunWith(AndroidJUnit4::class)
class HapticsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)
    private val touchFeedback = TouchFeedback(compose)

    @get:Rule
    val rules: RuleChain =
        RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(touchFeedback).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val vibrator: Vibrator = instrumentation.targetContext.getSystemService(VibratorManager::class.java).defaultVibrator
    private val records = VibratorRecords(compose, DeviceShots(compose, FOLDER).folder, vibrator)
    private val demo: List<Parcel> = DemoCards.parcels()

    @Before
    fun aVibrator() {
        assumeTrue("No vibrator to ask", vibrator.hasVibrator())
    }

    // A-H01: the tabs.

    /**
     * A-H01: `.sensoryFeedback(.selection, trigger: selection)` on the tabs: nothing as the window shows, one selection
     * for each tab tapped, and one as a parcel's link brings the deliveries forward from the passport.
     */
    @Test
    fun eachTabAsksForOneSelectionAndALinkOneAsTheDeliveriesComeForward() {
        val inbox = DeliveriesInbox()
        records.mark()
        walk.show(demo, inbox = inbox)
        assertNone("A-H01-launch")

        listOf(PeekTab.Passport, PeekTab.Friends, PeekTab.Deliveries, PeekTab.Passport).forEachIndexed { index, tab ->
            tapTab(tab)
            assertOne("A-H01-tap${index + 1}-${tab.screen}", selection())
        }

        val lamp = DemoCards.parcel("Moon lamp")
        walk.onUi { inbox.follow(Intent(Intent.ACTION_VIEW, Uri.parse("${OAuthFlow.callbackScheme}://parcel/${lamp.id}"))) }
        // Opened over the list, the parcel hides the bar, which TalkBack then doesn't reach: what shows says the tab.
        compose.waitUntil(LONG_MILLIS) { walk.shown() == lamp.id && !walk.exists(PeekTab.Passport.screen) }
        compose.waitForIdle()
        assertOne("A-H01-link", selection())
    }

    // A-H02: the postmark.

    /**
     * A-H02: `.sensoryFeedback(.success, trigger: postmarks)`: parcels delivered as the list is looked at are held, and
     * 0.3 s after the hold starts its postmark lands with one success, CONFIRM: nothing at 250 ms, one by 350 ms, nothing
     * more as the cards fly to their places. Two parcels delivered together are one hold, one more another.
     */
    @Test
    fun aDeliveryArrivingInViewAsksForOneConfirmPerHoldAsItsPostmarkLands() {
        walk.reduceMotion = false
        walk.show(demo)
        val chocolate = DemoCards.parcel("Belgian chocolate")
        val lamp = DemoCards.parcel("Moon lamp")
        val trainers = DemoCards.parcel("New trainers")
        compose.waitUntil(LONG_MILLIS) {
            walk.onUi { walk.source.onTheWay.ids }.containsAll(listOf(chocolate.id, lamp.id, trainers.id))
        }
        records.mark()

        compose.mainClock.autoAdvance = false
        for ((hold, parcels) in listOf("together" to listOf(chocolate, lamp), "alone" to listOf(trainers))) {
            val postmarks = walk.onUi { walk.deliveries.postmarks }
            deliver(parcels)
            framesUntil(1.0) { holding() }
            val start = walk.now()
            walk.advanceTo(start + 250)
            walk.recompose()
            assertEquals(postmarks, walk.onUi { walk.deliveries.postmarks })
            assertNone("A-H02-$hold-250ms")

            walk.advanceTo(start + 350)
            walk.recompose()
            assertEquals(postmarks + 1, walk.onUi { walk.deliveries.postmarks })
            assertOne("A-H02-$hold-postmark", success())

            compose.mainClock.advanceTimeBy(3_000)
            walk.recompose()
            assertFalse(holding())
            assertNone("A-H02-$hold-after")
        }
        compose.mainClock.autoAdvance = true
    }

    /**
     * A-H02 under Remove animations (the app's own input, RootHost's `reduceMotion`): a parcel delivered as the list is
     * looked at takes its place at once, seen, with no postmark: nothing is asked of the vibrator.
     */
    @Test
    fun underRemoveAnimationsADeliveryAsksForNothing() {
        walk.reduceMotion = true
        walk.show(demo)
        val chocolate = DemoCards.parcel("Belgian chocolate")
        compose.waitUntil(LONG_MILLIS) { chocolate.id in walk.onUi { walk.source.onTheWay.ids } }
        records.mark()

        compose.mainClock.autoAdvance = false
        deliver(listOf(chocolate))
        walk.recompose()
        compose.mainClock.advanceTimeBy(2_000)
        walk.recompose()
        assertTrue(still())
        assertEquals(0, walk.onUi { walk.deliveries.postmarks })
        assertFalse(chocolate.id in walk.onUi { walk.source.onTheWay.ids })
        assertNone("A-H02-remove-animations")
        compose.mainClock.autoAdvance = true
    }

    /**
     * A-H02 at a cold start: Belgian chocolate, delivered before the app starts, asks for nothing, nor at the start after,
     * the window's content made again on the same stores. (One delivered while the app was closed plays once at the next
     * start: [DeliveredCatchUpDeviceTest].)
     */
    @Test
    fun parcelsAlreadyDeliveredAskForNothingAtAColdStart() {
        walk.reduceMotion = false
        val chocolate = DemoCards.parcel("Belgian chocolate")
        var process by mutableIntStateOf(0)
        records.mark()
        walk.show(demo.map { if (it.id == chocolate.id) it.delivered() else it }, around = { content -> key(process) { content() } })
        walk.settle()
        assertEquals(0, walk.onUi { walk.deliveries.postmarks })
        assertNone("A-H02-cold-start")

        walk.onUi { process++ }
        compose.waitForIdle()
        compose.waitUntil(LONG_MILLIS) { walk.exists(PARCEL_LIST) }
        walk.settle()
        assertEquals(0, walk.onUi { walk.deliveries.postmarks })
        assertNone("A-H02-started-again")
    }

    // A-H03: a parcel added.

    /**
     * A-H03: `.sensoryFeedback(.success, trigger: parcelBurstID) { _, next in next != nil }`: a parcel saved in Add is
     * brought to the list's middle, then its arrival plays with one success, CONFIRM; none as the arrival ends.
     */
    @Test
    fun aParcelAddedAsksForOneConfirmAsItsCardReachesTheMiddle() {
        walk.reduceMotion = false
        val lamps = (1..20).map { syntheticLamp(it, due = it) } + (21..40).map { syntheticLamp(it) }
        walk.show(lamps) { addedId = ADDED }
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        // The carrier found as the number is typed has its own selection, before the mark.
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement(NUMBER)
        compose.onNodeWithTag(ADD_NAME).performClick()
        walk.settle()
        compose.onNodeWithTag(ADD_NAME).performTextInput(NAME)
        compose.waitForIdle()
        records.mark()

        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(ADD_SAVE).performClick()
        walk.recompose()
        assertNull(walk.onUi { walk.deliveries.parcelBurst })
        assertNone("A-H03-saved")

        framesUntil(5.0) { walk.onUi { walk.deliveries.parcelBurst } != null }
        assertEquals(ADDED, walk.onUi { walk.deliveries.parcelBurst })
        assertNull(walk.onUi { walk.deliveries.revealParcel })
        repeat(2) { frame() }
        compose.waitForIdle()
        assertOne("A-H03-middle", success())

        framesUntil(3.0) { walk.onUi { walk.deliveries.parcelBurst } == null }
        repeat(2) { frame() }
        compose.waitForIdle()
        assertNone("A-H03-arrival-ended")
        compose.mainClock.autoAdvance = true
    }

    // A-H04 and A-H05: the swipe to the archive.

    /**
     * A-H04: `.impact(weight: .medium, intensity: 0.9)` as the swipe arms: a finger drawing a card past its commit point
     * asks for one medium impact at 0.9 (PRIMITIVE_CLICK at 0.9 where the vibrator has it, VIRTUAL_KEY otherwise);
     * back under it, nothing; past again, another; let go under it, nothing, and the card stays open.
     */
    @Test
    fun aCardDraggedPastTheCommitPointAsksForOneMediumImpactEachTime() {
        walk.reduceMotion = false
        val lamps = (1..12).map { syntheticLamp(it, due = it) }
        walk.show(lamps)
        val dragged = lamps[3]
        val finger = Finger(dragged)
        records.mark()

        finger.down()
        finger.decide()
        finger.reveal(finger.commit + MARGIN)
        assertOne("A-H04-past", medium(0.9f))
        finger.reveal(finger.commit - MARGIN)
        assertNone("A-H04-back")
        finger.reveal(finger.commit + MARGIN)
        assertOne("A-H04-past-again", medium(0.9f))
        finger.reveal(finger.commit - MARGIN)
        finger.lift()
        walk.settle()
        assertNone("A-H04-let-go")
        assertFalse(dragged.id in archived())
    }

    /**
     * A-H05: `.impact(weight: .medium, intensity: 0.8)` as the open card's Archive button is tapped, and as TalkBack's
     * Archive action on the card is used: one medium impact at 0.8 each; a swipe through the commit point asks only for
     * its arming one at 0.9, nothing as it lets go and archives.
     */
    @Test
    fun archiveFromTheButtonOrTalkBackAsksForOneMediumImpactAndAFullSwipeOnlyForItsArming() {
        walk.reduceMotion = false
        val lamps = (1..12).map { syntheticLamp(it, due = it) }
        walk.show(lamps)
        records.mark()

        val opened = lamps[2]
        Finger(opened).run {
            down()
            decide()
            reveal(OPEN_REVEAL)
            lift()
        }
        val button = hasTestTag(ARCHIVE_ACTION) and hasClickAction()
        compose.waitUntil(LONG_MILLIS) { compose.onAllNodes(button).fetchSemanticsNodes().size == 1 }
        walk.settle()
        assertNone("A-H05-revealed")
        compose.onNode(button).performClick()
        compose.waitForIdle()
        assertOne("A-H05-button", medium(0.8f))
        compose.waitUntil(LONG_MILLIS) { opened.id in archived() }
        awaitNoToast()

        val swiped = lamps[4]
        val finger = Finger(swiped)
        finger.down()
        finger.decide()
        finger.reveal(finger.commit + MARGIN)
        assertOne("A-H05-swipe-armed", medium(0.9f))
        finger.lift()
        compose.waitUntil(LONG_MILLIS) { swiped.id in archived() }
        walk.settle()
        assertNone("A-H05-swipe-let-go")
        awaitNoToast()

        val told = lamps[5]
        scrollTo(told)
        val archive = walk.text("parcel.archive")
        val action = compose.onAllNodes(card(told.label)).onFirst().fetchSemanticsNode().config[SemanticsActions.CustomActions]
            .single { it.label == archive }
        walk.onUi { action.action() }
        compose.waitForIdle()
        assertOne("A-H05-talkback", medium(0.8f))
        compose.waitUntil(LONG_MILLIS) { told.id in archived() }
    }

    // A-H06: the archive's shelf.

    /** A-H06: `.sensoryFeedback(.selection, trigger: isExpanded)`: Archived tapped open, then closed, one selection each. */
    @Test
    fun theArchivedShelfOpenedAndClosedAsksForOneSelectionEach() {
        walk.show(demo)
        records.mark()
        val header = hasContentDescription("Archived", substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))
        // Opened from the list's foot, as the walk opens it; closed from its own item, which then has the list below it.
        for ((step, item, open) in listOf(Triple("open", "foot", true), Triple("closed", ARCHIVE_KEY, false))) {
            compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(item)
            compose.waitForIdle()
            compose.onAllNodes(header).onFirst().performClick()
            compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.deliveries.archiveOpen } == open }
            walk.settle()
            assertOne("A-H06-$step", selection())
        }
    }

    // A-H08: the passport.

    /**
     * A-H08: `.impact(weight: .light, intensity: 0.65)` on each toggle of a passport item's explanation: one light impact
     * at 0.65 for each item tapped (PRIMITIVE_TICK at 0.65 where the vibrator has it, CONTEXT_CLICK otherwise); its
     * popover dismissed by the system's back or a tap outside it (where the item shows, under the popover's layer), none.
     */
    @Test
    fun eachPassportItemAsksForOneLightImpactAndItsDismissalNothing() {
        walk.show(demo, session = AuthenticationState.Demo)
        tapTab(PeekTab.Passport)
        compose.waitUntil(LONG_MILLIS) { walk.exists(PASSPORT_COVER) }
        walk.settle()
        records.mark()

        val items = listOf(
            "cover" to PASSPORT_COVER, "average" to PASSPORT_AVERAGE, "personal-best" to PASSPORT_PERSONAL_BEST,
            "countries" to PASSPORT_COUNTRIES, "stamp-carriers" to "$PASSPORT_STAMP.carriers", "stamp-first" to "$PASSPORT_STAMP.first",
        ).filter { (_, tag) -> walk.exists(tag) }
        assertTrue(items.toString(), items.size >= 3)
        items.forEachIndexed { index, (name, tag) ->
            passportTap(tag)
            compose.waitUntil(LONG_MILLIS) { walk.exists(PASSPORT_POPOVER) }
            compose.waitForIdle()
            assertOne("A-H08-$name", light(0.65f))

            val back = index % 2 == 0
            if (back) {
                instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
            } else {
                compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
            }
            compose.waitUntil(LONG_MILLIS) { !walk.exists(PASSPORT_POPOVER) }
            compose.waitForIdle()
            assertNone("A-H08-$name-${if (back) "back" else "tap-outside"}")
        }
    }

    // X-AD17: the phone's touch feedback off.

    /**
     * X-AD17: with the phone's touch feedback switched off (`haptic_feedback_enabled` 0), a tab tapped asks for nothing
     * that plays, as Haptics.kt documents: on Android 12 nothing at all, the window manager dropping the constant; from
     * Android 13 at most the tap's one request, which the vibrator service records as ignored for the settings. Switched
     * on, the same tab's selection is asked for and played.
     */
    @Test
    fun withTouchFeedbackOffATabAsksForNothingThatPlays() {
        walk.show(demo)
        records.mark()
        tapTab(PeekTab.Passport)
        assertOne("X-AD17-feedback-on", selection())

        touchFeedback.switch(on = false)
        records.mark()
        tapTab(PeekTab.Deliveries)
        assertNothingPlays("X-AD17-feedback-off")
    }

    // What each moment asks for, by what the device has.

    /** `.selection`: SEGMENT_TICK from Android 14, CONTEXT_CLICK before; a prebaked tick on Android 12. */
    private fun selection(): Asked = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        Asked.Constant(HapticFeedbackConstants.SEGMENT_TICK, "SEGMENT_TICK", "TICK")
    } else {
        Asked.Constant(HapticFeedbackConstants.CONTEXT_CLICK, "CONTEXT_CLICK", "TICK")
    }

    /** `Haptics.success()`: CONFIRM, a prebaked click on Android 12. */
    private fun success(): Asked = Asked.Constant(HapticFeedbackConstants.CONFIRM, "CONFIRM", "CLICK")

    /** A medium impact below full intensity: a click at that scale where the vibrator has it, VIRTUAL_KEY otherwise. */
    private fun medium(intensity: Float): Asked =
        if (vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_CLICK)) {
            Asked.Primitive("CLICK", intensity)
        } else {
            Asked.Constant(HapticFeedbackConstants.VIRTUAL_KEY, "VIRTUAL_KEY", "CLICK")
        }

    /** A light impact below full intensity: a tick at that scale where the vibrator has it, CONTEXT_CLICK otherwise. */
    private fun light(intensity: Float): Asked =
        if (vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_TICK)) {
            Asked.Primitive("TICK", intensity)
        } else {
            Asked.Constant(HapticFeedbackConstants.CONTEXT_CLICK, "CONTEXT_CLICK", "TICK")
        }

    private fun raw(added: List<RecordedVibration>): String = added.joinToString("\n") { it.line }.ifEmpty { "(nothing)" }

    /** The step asked the vibrator for nothing. */
    private fun assertNone(step: String) {
        val added = records.step(step, "nothing", 0)
        assertTrue("$step: nothing asked for, but:\n${raw(added)}", added.isEmpty())
    }

    /** The step asked the vibrator for `asked`, once, in a record this test can read. */
    private fun assertOne(step: String, asked: Asked) {
        val added = records.step(step, "one ${asked.description}", 1)
        assertEquals("$step: one vibration for ${asked.description}, not:\n${raw(added)}", 1, added.size)
        val vibration = added.single()
        assertTrue("$step: a record this test can't read:\n${raw(added)}", vibration.read)
        assertTrue("$step: ${asked.description}, not:\n${raw(added)}", asked.playedBy(vibration))
    }

    /** Nothing the step asked for plays: no request, or one the vibrator service ignored. */
    private fun assertNothingPlays(step: String) {
        val added = records.step(step, "nothing that plays: no request, or one ignored for the settings", 0)
        assertTrue("$step: at most the tap's request, not:\n${raw(added)}", added.size <= 1)
        assertTrue("$step: nothing played, but:\n${raw(added)}", added.all { it.ignored })
    }

    // The window.

    private fun tabItem(tab: PeekTab): SemanticsMatcher =
        hasContentDescription(walk.text(tab.copyKey)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun selected(tab: PeekTab): Boolean =
        compose.onAllNodes(tabItem(tab)).fetchSemanticsNodes().any { it.config.getOrNull(SemanticsProperties.Selected) == true }

    /** A finger on the tab bar's item for `tab`, and the tab chosen. */
    private fun tapTab(tab: PeekTab) {
        compose.onNode(tabItem(tab)).performClick()
        compose.waitUntil(LONG_MILLIS) { selected(tab) }
        compose.waitForIdle()
    }

    /** The card of the parcel named `label` in the list: a button read with its name. */
    private fun card(label: String): SemanticsMatcher =
        hasContentDescription(label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))

    /** The list scrolled to the item of `parcel`'s card, which puts it below the top bar. */
    private fun scrollTo(parcel: Parcel) {
        val item = walk.onUi { walk.deliveries.itemKeys.first { cardId(it) == parcel.id } }
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(item)
        compose.waitForIdle()
    }

    /** `parcels` delivered at the captures' moment, in the stores. */
    private fun deliver(parcels: List<Parcel>) {
        val ids = parcels.map { it.id }.toSet()
        walk.onUi { walk.source.parcels.value = walk.source.parcels.value.map { if (it.id in ids) it.delivered() else it } }
    }

    private fun Parcel.delivered(): Parcel = copy(
        trackingEvents = trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("$id-delivered".toByteArray()), packageId = id, stage = TrackingStage.Delivered,
            description = "Delivered", location = null, occurredAt = DateParser.isoString(CAPTURED),
        ),
    )

    private fun holding(): Boolean = walk.onUi { !walk.deliveries.hold.isEmpty }

    /** Nothing held, flying or thrown on the list. */
    private fun still(): Boolean = walk.onUi { walk.deliveries.let { it.hold.isEmpty && it.flights.isEmpty() && it.paper == null } }

    private fun archived(): List<UUID> = walk.onUi { walk.source.archived.toList() }

    /** A frame of the test's clock, the state written since sent first. */
    private fun frame() {
        walk.onUi { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    /** The test's clock moved a frame at a time until `done`, within `seconds`. */
    private fun framesUntil(seconds: Double, done: () -> Boolean) {
        repeat((seconds * 60).toInt()) {
            if (done()) return
            frame()
        }
        assertTrue("Not done in $seconds s", done())
    }

    /** The archive's Undo gone from under the list, so that nothing stands over the cards. */
    private fun awaitNoToast() {
        compose.mainClock.advanceTimeBy(UNDO_MILLIS + 1_000)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(INLINE_TOAST) }
        compose.waitForIdle()
    }

    /** A finger on what has `tag` in the passport, scrolled into the window and up clear of a tab bar at its foot. */
    private fun passportTap(tag: String) {
        val node = compose.onNodeWithTag(tag).performScrollTo()
        val bounds = node.getBoundsInRoot()
        // A phone's tab bar, at the foot of the window; a tablet's stands at its top, over nothing it scrolls.
        val bar = compose.onAllNodes(hasTestTag(TAB_BAR)).fetchSemanticsNodes().firstOrNull()?.boundsInRoot
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot
        val hidden = if (bar != null && bar.top > window.height / 2) bounds.bottom - with(compose.density) { bar.top.toDp() } else 0.dp
        if (hidden > 0.dp) {
            compose.onAllNodes(hasScrollAction() and hasAnyDescendant(hasTestTag(tag))).onFirst()
                .performSemanticsAction(SemanticsActions.ScrollBy) { scroll -> scroll(0f, with(compose.density) { (hidden + 16.dp).toPx() }) }
            compose.waitForIdle()
        }
        node.performClick()
        compose.waitForIdle()
    }

    /**
     * A finger on the card of `parcel`, its touches sent as a screen's are (a finger's PointerProperties through
     * Instrumentation.sendPointerSync, as TurnstileSheetDeviceTest's drag), a frame apart: down 40 dp in from the card's
     * trailing edge at its middle, the list scrolled to the card first. The card's width, the row's, gives where the
     * swipe commits ([ArchiveSwipeState.commitPoint]) and how far a finger travels for what the card shows.
     */
    private inner class Finger(parcel: Parcel) {
        private val density = compose.density.density
        private val width: Double
        val commit: Double
        private var x: Float
        private val y: Float

        /** Where the drag was decided, from which the row measures it. */
        private var from = 0f
        private var downTime = 0L
        private var time = 0L
        private val finger = MotionEvent.PointerProperties().apply {
            id = 0
            toolType = MotionEvent.TOOL_TYPE_FINGER
        }

        init {
            scrollTo(parcel)
            val bounds = compose.onAllNodes(card(parcel.label)).onFirst().fetchSemanticsNode().boundsInWindow
            val screen = walk.onUi { IntArray(2).also { compose.activity.window.decorView.getLocationOnScreen(it) } }
            width = (bounds.width / density).toDouble()
            commit = ArchiveSwipeState.commitPoint(width)
            x = screen[0] + bounds.right - 40 * density
            y = screen[1] + bounds.center.y
        }

        fun down() {
            downTime = SystemClock.uptimeMillis()
            time = downTime
            send(MotionEvent.ACTION_DOWN)
        }

        /** 12 dp towards the leading edge in one move: past the 10 dp the row judges a drag by, straight across. */
        fun decide() {
            x -= 12 * density
            send(MotionEvent.ACTION_MOVE)
            from = x
        }

        /** Moved, 6 dp a frame, to where the card shows `points` of its action. */
        fun reveal(points: Double) {
            val target = from - (ArchiveSwipeState.travel(points, width) * density).toFloat()
            val start = x
            val steps = max(1, ceil(abs(target - start) / (6 * density)).toInt())
            for (step in 1..steps) {
                x = start + (target - start) * step / steps
                send(MotionEvent.ACTION_MOVE)
            }
            compose.waitForIdle()
        }

        /** Held still for eight frames, so that nothing is thrown, then lifted. */
        fun lift() {
            repeat(8) { send(MotionEvent.ACTION_MOVE) }
            send(MotionEvent.ACTION_UP)
            compose.waitForIdle()
        }

        private fun send(action: Int) {
            if (action != MotionEvent.ACTION_DOWN) time = max(time + 16, SystemClock.uptimeMillis())
            val at = x
            val height = y
            val where = MotionEvent.PointerCoords().apply {
                this.x = at
                this.y = height
                pressure = 1f
                size = 1f
            }
            val event = MotionEvent.obtain(
                downTime, time, action, 1, arrayOf(finger), arrayOf(where), 0, 0, 1f, 1f, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            instrumentation.sendPointerSync(event)
            event.recycle()
        }
    }

    private companion object {
        const val FOLDER = "hapticswalk"

        /** How far past the commit point, and back under it, a drag goes, in points. */
        const val MARGIN = 32.0

        /** How far a card is drawn open for its button: past half the action and short of it, so it settles open. */
        const val OPEN_REVEAL = 64.0

        /** How long the archive's Undo stays under the list. */
        const val UNDO_MILLIS = 7_000L

        val ADDED: UUID = UUID(0, 5_799)
        const val NUMBER = "1Z999AA10123456784"
        const val NAME = "Running shoes"
    }
}

/** How long the vibrator's history stands still before a step's vibrations are taken as all there. */
private const val QUIET_MILLIS = 1_000L

/** `command`'s output, run by the shell through UiAutomation, once it has ended. */
private fun shell(command: String): String = ParcelFileDescriptor.AutoCloseInputStream(
    InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(command),
).use { it.readBytes().decodeToString() }

/** What a step should have asked the vibrator for. */
private sealed interface Asked {
    val description: String

    fun playedBy(vibration: RecordedVibration): Boolean

    /**
     * `View.performHapticFeedback(constant)`: the constant where the record names it (Android 17), else, with no
     * primitive, the prebaked `effect` the window manager plays for it (Android 12).
     */
    data class Constant(val constant: Int, val name: String, val effect: String) : Asked {
        override val description: String get() = "$name (constant $constant, prebaked $effect)"

        override fun playedBy(vibration: RecordedVibration): Boolean =
            vibration.constant?.let { it == constant } ?: (vibration.primitives.isEmpty() && effect in vibration.effects)
    }

    /** A composition of the primitive `name` at `scale`, the scale asked for being in the record. */
    data class Primitive(val name: String, val scale: Float) : Asked {
        override val description: String get() = "PRIMITIVE_$name at $scale"

        override fun playedBy(vibration: RecordedVibration): Boolean =
            vibration.constant == null && vibration.primitives.any { (primitive, at) -> primitive == name && abs(at - scale) < 0.01f }
    }
}

/**
 * One record of the vibrator's history, as [VibratorDump.read] makes it out: the constant it names, the prebaked
 * effects and the primitives with their scale in what was played and what was asked (both, where the touch-feedback
 * strength scaled it), and its status (finished, ignored_for_settings...).
 */
private data class RecordedVibration(
    val line: String,
    val constant: Int?,
    val effects: Set<String>,
    val primitives: List<Pair<String, Float>>,
    val status: String?,
) {
    val read: Boolean get() = constant != null || effects.isNotEmpty() || primitives.isNotEmpty()
    val ignored: Boolean get() = status?.startsWith("ignored") == true
}

/**
 * `dumpsys vibrator_manager` read without knowing each release's wording: the lines under a history's header
 * ("Previous vibrations for usage TOUCH:" on Android 12, "Recent vibrations:" and a usage's on 17, not the aggregated
 * history, which repeats them), kept where they name the app's package, each by its time; and in each, the names and
 * numbers both forms give: `Prebaked{effect=TICK` or `Prebaked=TICK(`, `Primitive{primitive=PRIMITIVE_CLICK, scale=0.9`
 * or `Primitive=CLICK(scale=0.90`, an effect's or primitive's id in place of its name, and `constant=N`.
 */
private object VibratorDump {
    private val time = Regex("""\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}""")
    private val history = Regex("""(?i)^m?(previous|recent)\b""")
    private val constant = Regex("""\bconstant=(-?\d+)""")
    private val prebaked = Regex("""Prebaked(?:Segment)?\W{1,2}(?:m?[Ee]ffect(?:Id)?=)?(?:EFFECT_)?([A-Z][A-Z_0-9]*|\d+)""")
    private val primitive =
        Regex("""Primitive(?:Segment)?\W{1,2}(?:primitive=|mPrimitiveId=|id=)?(?:PRIMITIVE_)?([A-Z][A-Z_]*|\d+)\W{1,2}(?:m?[sS]cale[=:]\s*)?(\d+(?:[.,]\d+)?)""")

    /** A primitive named as the vibrator's info lists them, `CLICK(0.90`, where no segment says so. */
    private val shortPrimitive = Regex("""\b(NOOP|CLICK|THUD|SPIN|QUICK_RISE|SLOW_RISE|QUICK_FALL|TICK|LOW_TICK)\(\s*(?:scale=)?(\d+(?:\.\d+)?)""")
    private val status = Regex("""(?i)\b(running|finished\w*|forwarded\w*|cancelled\w*|ignored\w*)\b""")
    private val effects = mapOf(0 to "CLICK", 1 to "DOUBLE_CLICK", 2 to "TICK", 3 to "THUD", 4 to "POP", 5 to "HEAVY_CLICK", 21 to "TEXTURE_TICK")
    private val primitives =
        mapOf(0 to "NOOP", 1 to "CLICK", 2 to "THUD", 3 to "SPIN", 4 to "QUICK_RISE", 5 to "SLOW_RISE", 6 to "QUICK_FALL", 7 to "TICK", 8 to "LOW_TICK")

    /** The records of `app`'s vibrations in the histories of `dump`, by their time (the whole line where none shows). */
    fun records(dump: String, app: String): Map<String, String> {
        val own = Regex("""(?<![\w.])${Regex.escape(app)}(?![\w.])""")
        val headers = mutableListOf<Pair<Int, String>>()
        val found = LinkedHashMap<String, String>()
        for (line in dump.lineSequence()) {
            val text = line.trim()
            if (text.isEmpty()) continue
            val indent = line.length - line.trimStart().length
            val header = text.endsWith(":") && text.none { it in "|{},=" }
            while (headers.isNotEmpty() && (headers.last().first > indent || (headers.last().first == indent && header))) {
                headers.removeAt(headers.lastIndex)
            }
            if (header) {
                headers += indent to text
                continue
            }
            val inHistory = headers.any { history.containsMatchIn(it.second) } && headers.none { it.second.contains("aggregat", ignoreCase = true) }
            if (inHistory && own.containsMatchIn(text)) found[time.find(text)?.value ?: text] = text
        }
        return found
    }

    fun read(line: String): RecordedVibration {
        val asked = constant.find(line)?.groupValues?.get(1)?.toIntOrNull()
        val named = prebaked.findAll(line).map { name(it.groupValues[1], effects) }.toSet()
        var composed = primitive.findAll(line).mapNotNull { match ->
            match.groupValues[2].replace(',', '.').toFloatOrNull()?.let { name(match.groupValues[1], primitives) to it }
        }.toList()
        if (composed.isEmpty()) {
            composed = shortPrimitive.findAll(line).mapNotNull { match -> match.groupValues[2].toFloatOrNull()?.let { match.groupValues[1] to it } }.toList()
        }
        return RecordedVibration(line, asked, named, composed, status.find(line)?.groupValues?.get(1)?.lowercase())
    }

    private fun name(value: String, ids: Map<Int, String>): String = value.toIntOrNull()?.let { ids[it] ?: value } ?: value
}

/**
 * The app's vibrations in the vibrator service's history, read around each step of a test: [mark] takes what the
 * history holds once nothing new has come for [QUIET_MILLIS]; [step] waits, up to the device tests' time, for the
 * vibrations the step should have asked for, then for the history to stand still, and returns the records it added,
 * their lines saved as [folder]/<step>.txt after what was asked and what the device has. Written by the shell, as
 * [DeviceShots] writes its shots.
 */
private class VibratorRecords(
    private val compose: AndroidComposeTestRule<*, *>,
    private val folder: File,
    private val vibrator: Vibrator,
) {
    private val app = InstrumentationRegistry.getInstrumentation().targetContext.packageName
    private var marked: Set<String>? = null

    private fun records(): Map<String, String> = VibratorDump.records(shell("dumpsys vibrator_manager"), app)

    private fun quiet(): Map<String, String> {
        var last = records()
        var since = SystemClock.uptimeMillis()
        runCatching {
            compose.waitUntil(LONG_MILLIS) {
                val now = records()
                if (now.keys != last.keys) {
                    last = now
                    since = SystemClock.uptimeMillis()
                }
                SystemClock.uptimeMillis() - since >= QUIET_MILLIS
            }
        }
        return last
    }

    fun mark() {
        marked = quiet().keys
    }

    fun step(name: String, asked: String, expected: Int): List<RecordedVibration> {
        val before = checkNotNull(marked) { "No mark before $name" }
        if (expected > 0) runCatching { compose.waitUntil(LONG_MILLIS) { (records().keys - before).size >= expected } }
        val now = quiet()
        marked = now.keys
        val added = now.filterKeys { it !in before }.values.toList()
        save(name, asked, added)
        return added.map(VibratorDump::read)
    }

    private fun save(name: String, asked: String, lines: List<String>) {
        val click = vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_CLICK)
        val tick = vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_TICK)
        val text = buildString {
            appendLine("# $name: asked for $asked")
            appendLine("# Android SDK ${Build.VERSION.SDK_INT}; PRIMITIVE_CLICK ${if (click) "supported" else "missing"}, PRIMITIVE_TICK ${if (tick) "supported" else "missing"}")
            lines.forEach { appendLine(it) }
        }
        val bytes = text.toByteArray()
        val file = File(folder, "$name.txt")
        shell("mkdir -p $folder")
        val (output, input) = InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommandRw("dd of=$file")
        ParcelFileDescriptor.AutoCloseOutputStream(input).use { it.write(bytes) }
        // dd has ended once its output closes.
        ParcelFileDescriptor.AutoCloseInputStream(output).use { it.readBytes() }
        assertEquals("$file written", bytes.size.toString(), shell("stat -c %s $file").trim())
    }
}

/**
 * The phone's touch feedback (`haptic_feedback_enabled`) on for each test, as phones come, a test switching it off as it
 * needs, and what the phone had put back after it; set by the shell, which may write the system's settings. From
 * Android 13 the vibrator service follows the setting a moment later (touch vibrations OFF in its dump), which [switch]
 * waits for, as far as the dump says.
 */
private class TouchFeedback(private val compose: AndroidComposeTestRule<*, *>) : ExternalResource() {
    private var before = ""

    override fun before() {
        before = shell("settings get system $SETTING").trim()
        switch(on = true)
    }

    override fun after() {
        if (before.isEmpty() || before == "null") shell("settings delete system $SETTING") else shell("settings put system $SETTING $before")
        if (before != "0") awaitTouch(on = true)
    }

    fun switch(on: Boolean) {
        shell("settings put system $SETTING ${if (on) 1 else 0}")
        awaitTouch(on)
    }

    private fun awaitTouch(on: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        runCatching { compose.waitUntil(LONG_MILLIS) { TOUCH_OFF.containsMatchIn(shell("dumpsys vibrator_manager")) != on } }
    }

    private companion object {
        const val SETTING = "haptic_feedback_enabled"
        val TOUCH_OFF = Regex("""\bTOUCH\s*=\s*OFF\b""")
    }
}
