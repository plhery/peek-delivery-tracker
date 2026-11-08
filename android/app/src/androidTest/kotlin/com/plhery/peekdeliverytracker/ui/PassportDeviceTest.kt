package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_AVERAGE
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COUNTRIES
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_PERSONAL_BEST
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_POPOVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_SHOW_ALL
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_STAMP
import com.plhery.peekdeliverytracker.ui.passport.PassportFixtures
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.tabs.PeekTab
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import java.util.UUID
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * The passport (A-S87 to A-S92, A-ST29, A-G15) on this device's own window, its bars and density the device's, on fake
 * deliveries, which reach no server: the tab opened on the demo's parcels; a stamp's popover, and a tap on another stamp
 * closing it without opening that one; another opened and closed by the system's back; each item's popover opened by a
 * finger through the system's input ([ScreenTouches]) and closed by a tap on the bare page or on the item again;
 * nothing delivered yet; the stamps earned and the next three; all the stamps shown, and fewer again; the account button
 * opening Settings. Each saved as passport/<test>.png in the test package's external files, which adb pulls.
 * Under Reduce Motion, as the other device tests are; each test puts back the appearance it found.
 */
@RunWith(AndroidJUnit4::class)
class PassportDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, "passport")
    private val store = AppearanceStore.of(instrumentation.targetContext)
    private val appearanceBefore = store.appearance.value
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
    private val touches = ScreenTouches(compose)

    /** The app's words, as the root shows them: English, the Android copy. */
    private val localizer = Localizer(AppLanguage.En, variant = "android")

    @After
    fun putBack() {
        store.set(appearanceBefore)
    }

    /** The passport of [session] on [parcels], edge to edge as the app's window, chosen from the tab bar. */
    private fun show(parcels: List<Parcel> = DemoCards.parcels(), session: AuthenticationState = AuthenticationState.Demo) {
        store.set(AppAppearance.Light)
        val source = FakeDeliveries(parcels, session)
        compose.waitsForMaps()
        compose.setContent {
            val dark = appearanceSetting().isDark(isSystemInDarkTheme())
            DisposableEffect(dark) {
                compose.activity.edgeToEdge(dark)
                onDispose {}
            }
            RootHost(state = session, dark = dark, reduceMotion = true, source = source)
        }
        compose.waitForIdle()
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitUntil(5_000) { exists(PASSPORT_COVER) }
        compose.waitForIdle()
    }

    // While a popover is open TalkBack reaches only it, so the passport is looked for in the unmerged tree.
    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun popoverOpen() = compose.onAllNodesWithTag(PASSPORT_POPOVER, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    private fun stamp(id: String) = "$PASSPORT_STAMP.$id"

    /** A finger on what has [tag], scrolled into the window and up clear of the tab bar floating over the page first. */
    private fun tap(tag: String) {
        reveal(compose.onNodeWithTag(tag)).performClick()
        compose.waitForIdle()
    }

    /** What [node] finds, scrolled into the window and up clear of the tab bar floating over the page. */
    private fun reveal(node: SemanticsNodeInteraction): SemanticsNodeInteraction = compose.reveal(node)

    /** A finger where [tag] shows, on whatever stands over it there. */
    private fun tapWhereItShows(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        compose.waitForIdle()
    }

    /**
     * A finger on the screen at the middle of what has [tag], sent through the system's input as a person's
     * ([ScreenTouches]), scrolled into the window and up clear of the tab bar first.
     */
    private fun touch(tag: String) {
        reveal(compose.onNodeWithTag(tag))
        compose.waitForIdle()
        touches.tap(touches.point(tag))
    }

    /** The items whose explanation is open, by tag, as TalkBack hears it in their value. */
    private fun explained(): List<String> {
        val expanded = localizer.text("passport.expansionState.expanded")
        return compose.onAllNodes(
            SemanticsMatcher("explained") { it.config.getOrNull(SemanticsProperties.StateDescription)?.startsWith(expanded) == true },
            useUnmergedTree = true,
        ).fetchSemanticsNodes().map { it.config.getOrNull(SemanticsProperties.TestTag).orEmpty() }
    }

    private fun popovers(): Int = compose.onAllNodesWithTag(PASSPORT_POPOVER, useUnmergedTree = true).fetchSemanticsNodes().size

    /** Where the open popover's paper stands in the window, its arrow included. */
    private fun popover(): Rect = checkNotNull(
        compose.onAllNodes(
            SemanticsMatcher.keyIsDefined(SemanticsProperties.PaneTitle) and hasAnyDescendant(hasTestTag(PASSPORT_POPOVER)), useUnmergedTree = true,
        ).fetchSemanticsNodes().minByOrNull { it.size.width * it.size.height },
    ) { "No popover open" }.boundsInWindow

    /**
     * A point on the screen on the page's bare ground, level with the middle of what has [tag]: halfway into the page's
     * 20-point margin before the column whose left edge what has [column] stands on, on none of the passport's buttons
     * and off the popover.
     */
    private fun onThePage(tag: String, column: String): Offset {
        val item = touches.node(hasTestTag(tag)).boundsInWindow
        val left = touches.node(hasTestTag(column)).boundsInWindow.left
        val point = Offset(left - with(compose.density) { 10.dp.toPx() }, item.center.y)
        val buttons = compose.onAllNodes(hasClickAction() and hasAnyAncestor(hasTestTag(PeekTab.Passport.screen)), useUnmergedTree = true)
            .fetchSemanticsNodes().map { it.boundsInWindow }
        assertTrue("$point in the window", point.x > 0f)
        assertTrue("$point on no button: $buttons", buttons.none { it.contains(point) })
        val paper = popover()
        assertFalse("$point off the popover: $paper", paper.contains(point))
        return touches.origin() + point
    }

    /** The passport still the tab shown, with no popover over it and nothing opened from it. */
    private fun assertStillThePassport(message: String) {
        assertFalse(message, popoverOpen())
        assertFalse(message, exists(SETTINGS_SHEET))
        assertTrue(message, exists(PASSPORT_COVER))
        assertFalse(message, compose.activity.isFinishing)
    }

    /** The stamps' toggle, scrolled into the window, saying the words of [key] and at least 44 tall. */
    private fun assertToggle(key: String) {
        reveal(compose.onNodeWithTag(PASSPORT_SHOW_ALL))
            .assertTextEquals(localizer.text(key))
            .assertHeightIsAtLeast(44.dp)
    }

    private fun shot() {
        shots.take(name.methodName)
    }

    @Test
    fun theTabOpens() {
        show()
        shot()
    }

    /** ipad-03: with a stamp's popover open, a tap on another stamp only closes it. */
    @Test
    fun aTapOnAnotherStampOnlyClosesThePopover() {
        show()
        tap(stamp("carriers"))
        compose.waitUntil(5_000) { popoverOpen() }
        shots.take("${name.methodName}-open")
        tapWhereItShows(stamp("first"))
        compose.waitUntil(5_000) { !popoverOpen() }
        compose.waitForIdle()
        assertFalse(popoverOpen())
        shot()
    }

    @Test
    fun theSystemBackClosesThePopover() {
        show()
        tap(stamp("speed"))
        compose.waitUntil(5_000) { popoverOpen() }
        shots.take("${name.methodName}-open")
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        compose.waitUntil(5_000) { !popoverOpen() }
        compose.waitForIdle()
        assertFalse(compose.activity.isFinishing)
        assertTrue(exists(PASSPORT_COVER))
        shot()
    }

    @Test
    fun allTheStamps() {
        show(PassportFixtures.delivered(3), reader)
        tap(PASSPORT_SHOW_ALL)
        compose.waitUntil(5_000) { exists(stamp("homeForHolidays")) }
        assertEquals(12, compose.onAllNodes(SemanticsMatcher("a stamp") { node ->
            node.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("$PASSPORT_STAMP.") == true
        }, useUnmergedTree = true).fetchSemanticsNodes().size)
        shot()
    }

    /** The stamps shown, in the tree's order, by id. */
    private fun stamps(): List<String> = compose.onAllNodes(
        SemanticsMatcher("a stamp") { it.config.getOrNull(SemanticsProperties.TestTag)?.startsWith("$PASSPORT_STAMP.") == true }, useUnmergedTree = true,
    )
        .fetchSemanticsNodes()
        .map { it.config[SemanticsProperties.TestTag].removePrefix("$PASSPORT_STAMP.") }

    /**
     * A-ST29: with nothing delivered the times wait for a whole journey, no country shows, and the first three stamps are
     * next, the rest behind All stamps.
     */
    @Test
    fun withNothingDeliveredTheTimesWaitAndTheFirstThreeStampsAreNext() {
        show(emptyList(), reader)
        assertEquals(listOf("first", "ten", "carriers"), stamps())
        assertTrue(exists(PASSPORT_SHOW_ALL))
        assertFalse(exists(PASSPORT_AVERAGE))
        assertFalse(exists(PASSPORT_COUNTRIES))
        reveal(compose.onNodeWithText("Waiting for a complete journey"))
        shot()
    }

    /** A-ST29: the stamps earned and the next three, the rest behind All stamps. */
    @Test
    fun theStampsEarnedAndTheNextThreeShowUntilAllStamps() {
        show(PassportFixtures.delivered(3), reader)
        assertEquals(listOf("first", "ten", "carriers", "speed", "acrossBorders", "rightNextDoor"), stamps())
        reveal(compose.onNodeWithTag(PASSPORT_SHOW_ALL))
        shot()
    }

    @Test
    fun theAccountButtonOpensSettings() {
        show(session = reader)
        compose.onNodeWithTag(PASSPORT_ACCOUNT_BUTTON).performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_SHEET) }
        compose.waitForIdle()
        shot()
    }

    /**
     * A-G15: a finger on the cover, the average, the personal best or the countries opens that item's popover and no
     * other; a tap on the bare page beside it closes it (`expandedCard = nil`), with nothing else opened and the passport
     * still showing. Tapped again while its popover is open, the item closes it as well: the popover takes that tap as
     * iOS's takes any outside it (ipad-03), so it doesn't open again.
     */
    @Test
    fun eachItemOpensItsPopoverAndATapOutsideOrOnTheItemAgainClosesIt() {
        show(PassportFixtures.delivered(3), reader)
        // Top to bottom, so that each reveal only scrolls down; each with the item whose left edge is its page's column.
        listOf(
            Triple("cover", PASSPORT_COVER, stamp("first")),
            Triple("average", PASSPORT_AVERAGE, PASSPORT_AVERAGE),
            Triple("personal-best", PASSPORT_PERSONAL_BEST, PASSPORT_AVERAGE),
            Triple("countries", PASSPORT_COUNTRIES, PASSPORT_COUNTRIES),
        ).forEach { (item, tag, column) ->
            touch(tag)
            compose.waitUntil(5_000) { popoverOpen() }
            compose.waitForIdle()
            assertEquals(item, listOf(tag), explained())
            assertEquals(item, 1, popovers())
            shots.take("${name.methodName}-$item")

            touches.tap(onThePage(tag, column))
            compose.waitUntil(5_000) { !popoverOpen() }
            compose.waitForIdle()
            assertEquals("$item, a tap outside", emptyList<String>(), explained())
            assertStillThePassport("$item, a tap outside")

            touch(tag)
            compose.waitUntil(5_000) { popoverOpen() }
            compose.waitForIdle()
            assertEquals("$item again", listOf(tag), explained())
            val again = touches.point(tag)
            assertFalse("$item uncovered by its popover", popover().contains(again - touches.origin()))
            touches.tap(again)
            compose.waitUntil(5_000) { !popoverOpen() }
            compose.mainClock.advanceTimeBy(1_000)
            compose.waitForIdle()
            assertEquals("$item, tapped again", emptyList<String>(), explained())
            assertStillThePassport("$item, tapped again")
        }
        shot()
    }

    /**
     * A-G15: All stamps, tapped, shows the twelve in their order and turns to Show less, which puts back the stamps
     * earned and the next three and turns to All stamps again (`showAllStamps.toggle()`); the button at least 44 tall
     * either way (`.frame(minHeight: 44)`), and no popover opened.
     */
    @Test
    fun allStampsThenShowLessPutsBackTheStampsEarnedAndTheNextThree() {
        show(PassportFixtures.delivered(3), reader)
        val few = listOf("first", "ten", "carriers", "speed", "acrossBorders", "rightNextDoor")
        val all = listOf(
            "first", "ten", "carriers", "speed", "acrossBorders", "aroundWorld", "theRegular", "rightNextDoor", "worthTheWait",
            "busyDoorstep", "pickedUp", "homeForHolidays",
        )
        assertEquals(few, stamps())
        assertToggle("passport.showAll")

        touch(PASSPORT_SHOW_ALL)
        compose.waitUntil(5_000) { stamps().size == all.size }
        assertEquals(all, stamps())
        assertToggle("passport.showLess")
        assertFalse(popoverOpen())
        shots.take("${name.methodName}-all")

        touch(PASSPORT_SHOW_ALL)
        compose.waitUntil(5_000) { stamps().size == few.size }
        assertEquals(few, stamps())
        assertToggle("passport.showAll")
        assertFalse(popoverOpen())
        shot()
    }
}
