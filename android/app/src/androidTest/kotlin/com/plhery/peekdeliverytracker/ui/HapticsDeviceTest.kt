package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.os.Vibrator
import android.os.VibratorManager
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
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
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
import com.plhery.peekdeliverytracker.core.api.FriendStamp
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
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
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PAGE_BACK
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_POPOVER
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARE_ARRIVAL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARE_STATS
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARING
import com.plhery.peekdeliverytracker.ui.friends.FriendFixtures
import com.plhery.peekdeliverytracker.ui.friends.friendCardTag
import com.plhery.peekdeliverytracker.ui.friends.friendStampTag
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_AVERAGE
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COUNTRIES
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_PERSONAL_BEST
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_POPOVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_STAMP
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.appearanceTag
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
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
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the phone's vibrator is asked to play where the iPhone gives feedback (A-H01, A-H02, A-H03, A-H04, A-H05, A-H06,
 * A-H08, C-H04, C-H05, C-H06, X-AD17), on this device's own window: the tabs and a link bringing the deliveries
 * forward, a delivery's postmark, a parcel added reaching the list's middle, a card's swipe arming archive, Archive
 * from the open card's button and from TalkBack, the archive's shelf, the passport's items, Settings' appearance, the
 * profile panel's sharing switches, a friend's stamp popover, and a tab with the phone's touch feedback off (the
 * invitation's are InvitationDeviceTest's). Emulators don't vibrate, but the vibrator service keeps each request in its
 * history: each step reads `dumpsys vibrator_manager` through UiAutomation before and after, and takes the records of
 * the app's package it added once nothing new has come for a second ([Vibrations], [VibratorRecords]). Android 12 names
 * what was asked as prebaked effects (TICK, CLICK, as the window manager maps a constant) or a composition's primitives
 * with their scale; Android 17 gives the constant too (`performHapticFeedback(constant=N)`); [VibratorDump] reads both,
 * and a record it can't read fails the test with its lines. Each step's added lines are saved as
 * hapticswalk/<row>-<step>.txt in the test package's external files, which adb pulls. An impact below full intensity is
 * a primitive where the device's vibrator has it ([Vibrator.areAllPrimitivesSupported]), as Haptics.kt chooses. Signed
 * in as the synthetic reader@example.com (the demo's session for the passport) on fake deliveries and made-up friends
 * ([ParcelWalk]), which reach no server; each test keeps Zurich's time and the captures' moment, and the zone, the
 * phone's touch feedback, the appearance and the preferences files the fakes write are put back after it. Skipped on a
 * device without a vibrator.
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
    private val vibrations = Vibrations(compose, DeviceShots(compose, FOLDER).folder, vibrator)
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
        vibrations.mark()
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
        vibrations.mark()

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
        vibrations.mark()

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
        vibrations.mark()
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
        vibrations.mark()

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
        vibrations.mark()

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
        vibrations.mark()

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
        vibrations.mark()
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
        vibrations.mark()

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

    // C-H04 to C-H06: Settings and the friends.

    /**
     * C-H04: `.sensoryFeedback(.selection, trigger: appearance)`: each appearance chosen in Settings asks for one
     * selection; the one already chosen, tapped again, nothing. The appearance the phone had is put back.
     */
    @Test
    fun eachAppearanceChosenAsksForOneSelection() {
        val appearances = AppearanceStore.of(instrumentation.targetContext)
        val before = appearances.appearance.value
        try {
            appearances.set(AppAppearance.Light)
            walk.show(demo)
            compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
            compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
            walk.settle()
            vibrations.mark()

            listOf(AppAppearance.Dark, AppAppearance.System, AppAppearance.Light).forEach { option ->
                tapIn(appearanceTag(option))
                compose.waitUntil(LONG_MILLIS) { appearances.appearance.value == option }
                assertOne("C-H04-${option.rawValue}", selection())
            }
            tapIn(appearanceTag(AppAppearance.Light))
            assertNone("C-H04-light-again")
        } finally {
            appearances.set(before)
        }
    }

    /**
     * C-H05: `.impact(weight: .light, intensity: 0.5)` on each change of the profile panel's two sharing switches: one
     * light impact at 0.5 for each flip, either switch, either way (PRIMITIVE_TICK at 0.5 where the vibrator has it,
     * CONTEXT_CLICK otherwise). The made-up circle's friends client answers the saves.
     */
    @Test
    fun eachSharingSwitchFlippedAsksForOneLightImpact() {
        walk.show(demo)
        tapTab(PeekTab.Friends)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_SHARING) }
        tapIn(FRIENDS_SHARING)
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_SHARE_STATS) && walk.exists(FRIENDS_SHARE_ARRIVAL) }
        walk.settle()
        vibrations.mark()

        val flips = listOf("stats" to FRIENDS_SHARE_STATS, "arrival" to FRIENDS_SHARE_ARRIVAL, "stats-back" to FRIENDS_SHARE_STATS)
        flips.forEach { (step, tag) ->
            val was = toggled(tag)
            compose.onNodeWithTag(tag, useUnmergedTree = true).performSemanticsAction(SemanticsActions.OnClick)
            compose.waitUntil(LONG_MILLIS) { toggled(tag) != was }
            assertOne("C-H05-$step", light(0.5f))
        }
    }

    /**
     * C-H06: `.impact(weight: .light, intensity: 0.6)` whenever a friend's selected stamp changes: one light impact at
     * 0.6 as a stamp's popover opens, and one as the system's back closes it.
     */
    @Test
    fun aStampsPopoverOpenedAndClosedAsksForOneLightImpactEach() {
        walk.show(demo)
        tapTab(PeekTab.Friends)
        compose.waitUntil(LONG_MILLIS) { walk.exists(friendCardTag(FriendFixtures.ada.id)) }
        compose.onNodeWithTag(friendCardTag(FriendFixtures.ada.id)).performScrollTo().performClick()
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_PAGE_BACK) }
        walk.settle()
        vibrations.mark()

        tapIn(friendStampTag(FriendStamp.Ten))
        compose.waitUntil(LONG_MILLIS) { walk.exists(FRIENDS_POPOVER) }
        compose.waitForIdle()
        assertOne("C-H06-open", light(0.6f))
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        compose.waitUntil(LONG_MILLIS) { !walk.exists(FRIENDS_POPOVER) }
        compose.waitForIdle()
        assertTrue(walk.exists(FRIENDS_PAGE_BACK))
        assertOne("C-H06-back", light(0.6f))
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
        vibrations.mark()
        tapTab(PeekTab.Passport)
        assertOne("X-AD17-feedback-on", selection())

        touchFeedback.switch(on = false)
        vibrations.mark()
        tapTab(PeekTab.Deliveries)
        assertNothingPlays("X-AD17-feedback-off")
    }

    // What each moment asks for, by what the device has ([Vibrations]).

    private fun selection(): Asked = vibrations.selection()

    private fun success(): Asked = vibrations.success()

    private fun medium(intensity: Float): Asked = vibrations.medium(intensity)

    private fun light(intensity: Float): Asked = vibrations.light(intensity)

    private fun assertNone(step: String) = vibrations.assertNone(step)

    private fun assertOne(step: String, asked: Asked) = vibrations.assertOne(step, asked)

    private fun assertNothingPlays(step: String) = vibrations.assertNothingPlays(step)

    // The window.

    private fun tabItem(tab: PeekTab): SemanticsMatcher =
        hasContentDescription(walk.text(tab.copyKey)) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)

    private fun selected(tab: PeekTab): Boolean =
        compose.onAllNodes(tabItem(tab)).fetchSemanticsNodes().any { it.config.getOrNull(SemanticsProperties.Selected) == true }

    /** A finger on what has `tag`, scrolled into the window first. */
    private fun tapIn(tag: String) {
        compose.onNodeWithTag(tag).performScrollTo().performClick()
        compose.waitForIdle()
    }

    /** Whether the switch tagged `tag` is on. */
    private fun toggled(tag: String): Boolean = compose.onNodeWithTag(tag, useUnmergedTree = true).fetchSemanticsNode()
        .config.getOrNull(SemanticsProperties.ToggleableState) == ToggleableState.On

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
