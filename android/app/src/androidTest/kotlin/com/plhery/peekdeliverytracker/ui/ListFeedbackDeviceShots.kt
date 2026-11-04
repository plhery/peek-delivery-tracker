package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.UiModeManager
import android.content.Intent
import android.graphics.Bitmap
import android.view.accessibility.AccessibilityManager
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelSectionKind
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FOOT
import com.plhery.peekdeliverytracker.ui.deliveries.INLINE_TOAST
import com.plhery.peekdeliverytracker.ui.deliveries.LocalPaperSeeds
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PULL_INDICATOR
import com.plhery.peekdeliverytracker.ui.deliveries.REFRESH_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.cardKey
import com.plhery.peekdeliverytracker.ui.settings.SETTINGS_SHEET
import com.plhery.peekdeliverytracker.ui.settings.appearanceTag
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.PeekColors
import java.util.UUID
import kotlin.math.abs
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

/**
 * What the deliveries list says and does as it is used, on this device's own window (A-ST12, A-ST13, A-ST32,
 * A-ST35, A-S23, A-SE01): a pull checking every parcel, online and offline, what TalkBack is told of each and the
 * offline message under the list it isn't; the refresh button TalkBack users get, online and offline, its words while
 * it checks and the message each check leaves; a delivery under Remove animations, seen arriving or missed while the
 * app was away, taking its place at once without postmark, paper or flight; a parcel kept from its link while Add is
 * open, said and shown under the list, and revealed half a second after Add has closed; each of the foot's links
 * opening the site in a Custom Tab, the list at the same scroll when the window comes back; and Settings' appearance
 * set to Dark, then System, the open sheet and the bars following. Each moment saved as
 * liststates/<row>-<moment>.png, with -dark where the walk's own appearance is drawn, in the test package's external
 * files, which adb pulls. Signed in as the synthetic reader@example.com on fake deliveries
 * ([com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries], [ParcelWalk]) whose stores answer every read in
 * memory: nothing goes to the network, and the views the app asks for (the site's pages) are kept and not started.
 * What TalkBack would hear is read from the deliveries' announcements, TalkBack itself not running: the refresh button
 * shows where the window lays the list beside a page or touch exploration is on, which the test asks UiAutomation for
 * and turns off again after; where it can't be turned on and the list stands alone, that test is skipped. Remove
 * animations is the app's own input (RootHost's `reduceMotion`), not the phone's setting. Each test keeps Zurich's
 * time and the captures' moment as the JVM's do; the zone, the appearance a test set and the preferences files the
 * fakes write are put back after it.
 */
@RunWith(AndroidJUnit4::class)
class ListFeedbackDeviceShots {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val walk = ParcelWalk(compose, FOLDER)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(MadePreferences()).around(ZurichTime()).around(compose).around(walk)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val demo: List<Parcel> = DemoCards.parcels()
    private val button = SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)

    /** The bursts of paper thrown, each asking for its seed once. */
    @Volatile
    private var bursts = 0

    private fun advance(milliseconds: Long) = compose.mainClock.advanceTimeBy(milliseconds)

    /** Frame by frame, three seconds at most, until `done`. */
    private fun advanceUntil(done: () -> Boolean) {
        repeat(180) {
            if (done()) return
            compose.mainClock.advanceTimeByFrame()
        }
        assertTrue("Not done in 3 s", done())
    }

    /**
     * Whether the message under the list says `text`: read in the unmerged tree, as TalkBack meets it again once
     * nothing stands over the list (Add on its way down hides the list's controls from it until it has gone).
     */
    private fun toast(text: String): Boolean = compose.onAllNodes(
        hasTestTag(INLINE_TOAST) and hasContentDescription(text, substring = true),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    private fun warning(): Boolean? = walk.onUi { walk.deliveries.toast?.warning }

    private fun keys(): List<Any> = walk.onUi { walk.deliveries.itemKeys }

    /** Where the list stands: its first item showing, and how far it is scrolled past. */
    private fun standing(): Pair<Int, Int> =
        walk.onUi { walk.deliveries.listState.let { it.firstVisibleItemIndex to it.firstVisibleItemScrollOffset } }

    /**
     * A finger down a quarter of the way down the list, under its bar, drawn 0.6 of the list's height further down and
     * held a moment before it lifts: past the threshold, under the rubber band, whatever the window's height.
     */
    private fun pull() {
        walk.node(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, height * 0.25f))
            val steps = 30
            repeat(steps) { moveBy(Offset(0f, height * 0.6f / steps)) }
            advanceEventTime(200)
            up()
        }
    }

    // A-ST12, A-ST13: checking every parcel.

    /**
     * A-ST12: a pull checks every parcel as it passes the threshold. TalkBack doesn't read the arrow or its words; it
     * is told the check's result ("app.refreshComplete") and no message stays. Offline, it is told the check failed,
     * and the message comes under the list 1.1 s later, a warning TalkBack isn't told again.
     */
    @Test
    fun aPullSaysHowTheCheckWentOnlineAndOffline() {
        walk.show(demo)
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        walk.onUi { walk.source.checking = checked }
        compose.mainClock.autoAdvance = false
        pull()
        advance(2_000)
        assertEquals(1, walk.onUi { walk.source.refreshes })
        val indicator = compose.onNode(hasTestTag(PULL_INDICATOR), useUnmergedTree = true).fetchSemanticsNode().config
        assertNull(indicator.getOrNull(SemanticsProperties.ContentDescription))
        assertNull(indicator.getOrNull(SemanticsProperties.Text))
        val updated = walk.text("app.refreshComplete")
        assertFalse(walk.heard(updated))
        assertFalse(walk.heard(walk.text("app.refreshing")))
        walk.shot("A-ST12-pull-checking")
        checked.complete(ParcelStore.RefreshResult.Updated)
        advance(100)
        assertTrue(walk.heard(updated))
        walk.shot("A-ST12-pull-updated")
        advance(3_000)
        assertFalse(walk.exists(INLINE_TOAST))

        val offline = walk.text("error.connection")
        val failing = CompletableDeferred<ParcelStore.RefreshResult?>()
        walk.onUi { walk.source.checking = failing }
        pull()
        failing.complete(ParcelStore.RefreshResult.Failed(offline))
        advanceUntil { walk.heard(walk.text("detail.checkFailed")) }
        assertEquals(2, walk.onUi { walk.source.refreshes })
        assertFalse(toast(offline))
        advance(PULL_RESULT_MILLIS + 50)
        assertTrue(toast(offline))
        assertEquals(true, warning())
        assertFalse(walk.heard(offline))
        walk.shot("A-ST12-pull-offline")
    }

    /**
     * A-ST13: the refresh button, which touch exploration brings, is read "app.refresh", then "app.refreshing" while
     * the parcels are checked. Its check leaves its result under the list, and TalkBack is told it: no news online, the
     * service out of reach offline, as a warning.
     */
    @Test
    fun theRefreshButtonSaysHowTheCheckWentOnlineAndOffline() = exploringByTouch {
        walk.show(demo)
        assumeTrue("Touch exploration stayed off and the list stands alone: no refresh button", walk.exists(REFRESH_BUTTON))
        walk.node(REFRESH_BUTTON).assertContentDescriptionEquals(walk.text("app.refresh")).assert(button)
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        walk.onUi { walk.source.checking = checked }
        walk.tap(REFRESH_BUTTON)
        assertEquals(1, walk.onUi { walk.source.refreshes })
        compose.mainClock.autoAdvance = false
        walk.recompose()
        walk.node(REFRESH_BUTTON).assertContentDescriptionEquals(walk.text("app.refreshing"))
        walk.shot("A-ST13-refreshing")

        checked.complete(ParcelStore.RefreshResult.Unchanged)
        advance(200)
        val unchanged = walk.text("app.refreshUnchanged")
        assertTrue(toast(unchanged))
        assertEquals(false, warning())
        assertTrue(walk.heard(unchanged))
        walk.node(REFRESH_BUTTON).assertContentDescriptionEquals(walk.text("app.refresh"))
        walk.shot("A-ST13-online")
        advance(TOAST_MILLIS + 500)
        assertFalse(walk.exists(INLINE_TOAST))

        val offline = walk.text("error.connection")
        walk.onUi {
            walk.source.checking = null
            walk.source.checkResult = ParcelStore.RefreshResult.Failed(offline)
        }
        walk.node(REFRESH_BUTTON).performClick()
        advance(200)
        assertEquals(2, walk.onUi { walk.source.refreshes })
        assertTrue(toast(offline))
        assertEquals(true, warning())
        assertTrue(walk.heard(offline))
        walk.shot("A-ST13-offline")
    }

    private fun touchExploration(): Boolean =
        instrumentation.targetContext.getSystemService(AccessibilityManager::class.java)?.isTouchExplorationEnabled == true

    /**
     * `body` with touch exploration on, as TalkBack turns it on, asked of the system by the tests' own accessibility
     * connection (UiAutomation), which the shots share; off again after, as it was.
     */
    private fun exploringByTouch(body: () -> Unit) {
        val automation = instrumentation.uiAutomation
        val info = automation.serviceInfo
        val flags = info?.flags ?: 0
        if (info != null) {
            info.flags = flags or AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE
            runCatching { automation.serviceInfo = info }
            runCatching { compose.waitUntil(LONG_MILLIS) { touchExploration() } }
        }
        try {
            body()
        } finally {
            if (info != null) {
                info.flags = flags
                runCatching { automation.serviceInfo = info }
                runCatching { compose.waitUntil(LONG_MILLIS) { !touchExploration() } }
            }
        }
    }

    // A-ST32: Remove animations.

    /**
     * A-ST32: under Remove animations, a parcel delivered as the list is looked at takes its place among the past
     * deliveries the next frame: nothing held, no postmark or paper, no card flying, and it is no longer kept as on its
     * way. One delivered while the app was away is there at once when it comes back, seen as well.
     */
    @Test
    fun underRemoveAnimationsADeliveryTakesItsPlaceAtOnce() {
        walk.reduceMotion = true
        walk.show(demo, around = { content -> CompositionLocalProvider(LocalPaperSeeds provides { bursts++; SEED }) { content() } })
        val chocolate = DemoCards.parcel("Belgian chocolate")
        val lamp = DemoCards.parcel("Moon lamp")
        compose.waitUntil(LONG_MILLIS) { walk.onUi { walk.source.onTheWay.ids }.containsAll(listOf(chocolate.id, lamp.id)) }
        val past = "section-${ParcelSectionKind.Delivered.name}"

        compose.mainClock.autoAdvance = false
        deliver(chocolate)
        walk.recompose()
        assertTrue(keys().toString(), cardKey(past, chocolate.id) in keys())
        repeat(60) {
            compose.mainClock.advanceTimeByFrame()
            assertTrue(still())
        }
        advance(2_000)
        assertTrue(still())
        assertEquals(0, walk.onUi { walk.deliveries.postmarks })
        assertEquals(0, bursts)
        assertFalse(chocolate.id in walk.onUi { walk.source.onTheWay.ids })
        walk.shot("A-ST32-delivered-at-once")

        compose.mainClock.autoAdvance = true
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        deliver(lamp)
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        compose.waitUntil(LONG_MILLIS) { cardKey(past, lamp.id) in keys() }
        compose.waitUntil(LONG_MILLIS) { lamp.id !in walk.onUi { walk.source.onTheWay.ids } }
        walk.settle()
        assertTrue(still())
        assertEquals(0, walk.onUi { walk.deliveries.postmarks })
        assertEquals(0, bursts)
        walk.shot("A-ST32-missed-delivery")
    }

    /** Nothing held, flying or thrown on the list. */
    private fun still(): Boolean = walk.onUi { walk.deliveries.let { it.hold.isEmpty && it.flights.isEmpty() && it.paper == null } }

    /** `parcel` delivered at the captures' moment, in the stores. */
    private fun deliver(parcel: Parcel) = walk.onUi {
        walk.source.parcels.value = walk.source.parcels.value.map { if (it.id == parcel.id) it.delivered() else it }
    }

    private fun Parcel.delivered(): Parcel = copy(
        trackingEvents = trackingEvents + TrackingEvent(
            id = UUID.nameUUIDFromBytes("$id-delivered".toByteArray()), packageId = id, stage = TrackingStage.Delivered,
            description = "Delivered", location = null, occurredAt = DateParser.isoString(CAPTURED),
        ),
    )

    // A-ST35: a link while elsewhere.

    /**
     * A-ST35: with Add open, a parcel kept from its link: Add closes, "link.added" shows under the list and TalkBack is
     * told it. At 400 ms the list hasn't moved and nothing has arrived; by 600 ms the card has been revealed.
     */
    @Test
    fun aLinkKeptWhileAddIsOpenIsRevealedHalfASecondAfterAddCloses() {
        val inbox = DeliveriesInbox()
        val lamps = (1..20).map { syntheticLamp(it, due = it) } + (22..30).map { syntheticLamp(it) }
        val kept = syntheticLamp(21)
        walk.show(lamps, inbox = inbox)
        walk.tap(ADD_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(ADD_PARCEL) }
        walk.settle()
        walk.awaitKeyboard(required = false)
        walk.shot("A-ST35-add-open")

        compose.mainClock.autoAdvance = false
        walk.onUi { walk.source.parcels.value = lamps + kept }
        walk.recompose()
        val stood = standing()
        val start = walk.now()
        walk.onUi { inbox.added(kept.id) }
        walk.recompose()
        assertNull("Add closes", walk.onUi { walk.deliveries.addition })
        val added = walk.text("link.added")
        assertTrue(toast(added))
        assertTrue(walk.heard(added))

        walk.advanceTo(start + 400)
        assertEquals(kept.id, walk.onUi { walk.deliveries.revealParcel })
        assertNull(walk.onUi { walk.deliveries.parcelBurst })
        assertEquals(stood, standing())
        walk.shot("A-ST35-400ms")

        walk.advanceTo(start + 600)
        walk.recompose()
        assertEquals(kept.id, walk.onUi { walk.deliveries.parcelBurst })
        walk.shot("A-ST35-revealed")
    }

    // A-S23: the foot's links.

    /**
     * A-S23: each of the foot's links asks for the site's page in a Custom Tab of the phone's browser, in the app's
     * appearance (light here), kept and not started; where the browser has no Custom Tabs, a plain view. The window
     * back, the list stands where it was, at its foot.
     */
    @Test
    fun eachFootLinkOpensACustomTabAndTheListKeepsItsScroll() = withAppearance(AppAppearance.Light) {
        walk.show(demo)
        walk.node(PARCEL_LIST).performScrollToKey("foot")
        walk.settle()
        val stood = standing()
        walk.shot("A-S23-foot")
        val browser = CustomTabsClient.getPackageName(compose.activity, null)
        val links = listOf("home" to walk.source.homePageUrl, "privacy" to walk.source.privacyUrl, "source" to AppConfiguration.sourceUrl)
        for ((id, url) in links) {
            val asked = walk.asked().size
            walk.tap("$FOOT.$id")
            compose.waitUntil(LONG_MILLIS) { walk.asked().size > asked }
            val view = walk.asked().last()
            assertEquals(Intent.ACTION_VIEW, view.action)
            assertEquals(url, view.dataString)
            if (browser != null) {
                assertTrue("A Custom Tab", view.hasExtra(CustomTabsIntent.EXTRA_SESSION))
                assertEquals(browser, view.`package`)
                assertEquals(CustomTabsIntent.COLOR_SCHEME_LIGHT, view.getIntExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, -1))
            } else {
                assertFalse("No Custom Tab", view.hasExtra(CustomTabsIntent.EXTRA_SESSION))
            }
            walk.awayAndBack()
            compose.mainClock.autoAdvance = true
            walk.settle()
            assertEquals(id, stood, standing())
        }
        walk.shot("A-S23-back")
    }

    // A-SE01: the appearance.

    /**
     * A-SE01: Settings' appearance set to Dark draws the window dark, the open sheet and the bars' icons with it; set
     * to System, they follow the phone's own.
     */
    @Test
    fun theAppearanceGoesDarkThenFollowsThePhone() = withAppearance(AppAppearance.Light) { store ->
        walk.followsAppearance = true
        walk.show(demo)
        walk.tap(ACCOUNT_BUTTON)
        compose.waitUntil(LONG_MILLIS) { walk.exists(SETTINGS_SHEET) }
        walk.awaitStill()
        assertEquals(true, lightBars())
        assertGround(walk.shot("A-SE01-light", alsoDark = false), dark = false)

        walk.tap(appearanceTag(AppAppearance.Dark))
        assertEquals(AppAppearance.Dark, store.appearance.value)
        compose.waitUntil(LONG_MILLIS) { lightBars() == false }
        walk.settle()
        assertTrue(walk.exists(SETTINGS_SHEET))
        assertGround(walk.shot("A-SE01-dark", alsoDark = false), dark = true)

        walk.tap(appearanceTag(AppAppearance.System))
        assertEquals(AppAppearance.System, store.appearance.value)
        val phone = when (instrumentation.targetContext.getSystemService(UiModeManager::class.java)?.nightMode) {
            UiModeManager.MODE_NIGHT_YES -> true
            UiModeManager.MODE_NIGHT_NO -> false
            else -> null
        }
        compose.waitUntil(LONG_MILLIS) { configuredDark().let { (phone == null || it == phone) && lightBars() == !it } }
        walk.settle()
        assertTrue(walk.exists(SETTINGS_SHEET))
        assertGround(walk.shot("A-SE01-system", alsoDark = false), dark = configuredDark())
    }

    /** `body` with the appearance `appearance`, the one it found put back after. */
    private fun withAppearance(appearance: AppAppearance, body: (AppearanceStore) -> Unit) {
        val store = AppearanceStore.of(instrumentation.targetContext)
        val before = store.appearance.value
        store.set(appearance)
        try {
            body(store)
        } finally {
            store.set(before)
        }
    }

    private fun lightBars(): Boolean = walk.onUi {
        val window = compose.activity.window
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars
    }

    private fun configuredDark(): Boolean = walk.onUi { compose.activity.resources.configuration.isNightModeActive }

    /** Settings' ground in `screen`, at its leading edge halfway down, is the `dark` theme's, as Settings' own test reads it. */
    private fun assertGround(screen: Bitmap, dark: Boolean) {
        val sheet = walk.node(SETTINGS_SHEET).fetchSemanticsNode().boundsInWindow
        val x = sheet.left.toInt() + 4
        val y = ((sheet.top + sheet.bottom) / 2).toInt()
        val ground = PeekColors(dark).background
        val drawn = Color(screen.getPixel(x, y))
        val apart = maxOf(abs(drawn.red - ground.red), abs(drawn.green - ground.green), abs(drawn.blue - ground.blue))
        assertTrue("Settings' ground at $x, $y: $drawn for $ground", apart < 0.06f)
    }

    private companion object {
        const val FOLDER = "liststates"
        const val SEED = 0x5EED_CAFEuL

        /** How long a failed pull's cross shows before its message comes under the list. */
        const val PULL_RESULT_MILLIS = 1_100L

        /** How long a message stays under the list. */
        const val TOAST_MILLIS = 4_000L
    }
}
