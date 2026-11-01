package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityService
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_POPOVER
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_SHOW_ALL
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_STAMP
import com.plhery.peekdeliverytracker.ui.passport.PassportFixtures
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
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
 * The passport (A-S87 to A-S92) on this device's own window, its bars and density the device's, on fake deliveries,
 * which reach no server: the tab opened on the demo's parcels; a stamp's popover, and a tap on another stamp closing it
 * without opening that one; another opened and closed by the system's back; all the stamps shown; the account button
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
        val node = compose.onNodeWithTag(tag).performScrollTo()
        val bounds = node.getBoundsInRoot()
        // A phone's tab bar, at the foot of the window; a tablet's stands at its top, over nothing it scrolls.
        val bar = compose.onAllNodes(hasTestTag(TAB_BAR)).fetchSemanticsNodes().firstOrNull()?.boundsInRoot
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot
        val hidden = if (bar != null && bar.top > window.height / 2) {
            bounds.bottom - with(compose.density) { bar.top.toDp() }
        } else {
            0.dp
        }
        if (hidden > 0.dp) {
            compose.onNode(hasScrollAction()).performSemanticsAction(SemanticsActions.ScrollBy) { scroll ->
                scroll(0f, with(compose.density) { (hidden + 16.dp).toPx() })
            }
            compose.waitForIdle()
        }
        node.performClick()
        compose.waitForIdle()
    }

    /** A finger where [tag] shows, on whatever stands over it there. */
    private fun tapWhereItShows(tag: String) {
        compose.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        compose.waitForIdle()
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

    @Test
    fun theAccountButtonOpensSettings() {
        show(session = reader)
        compose.onNodeWithTag(PASSPORT_ACCOUNT_BUTTON).performClick()
        compose.waitUntil(5_000) { exists(SETTINGS_SHEET) }
        compose.waitForIdle()
        shot()
    }
}
