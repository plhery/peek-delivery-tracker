package com.plhery.peekdeliverytracker.ui

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.res.Configuration
import android.provider.Settings
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.DeliveriesArrangement
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.PAGE_NAME
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.theme.CarrierIdentities
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.rules.ExternalResource

/**
 * What the fixture walks share ([ParcelStatesDeviceShots], [ParcelFeedbackDeviceShots], [ParcelAlertsDeviceShots]):
 * the synthetic reader@example.com's deliveries on [FakeDeliveries], whose stores answer every read in memory, shown on
 * this device's own window edge to edge, its keyboard resizing the room as the app's does; each parcel opened from its
 * card in the list, beside it where the window lays them out side by side and over it where it stacks them; and each
 * moment saved, once still, as [FOLDER]/<row>-<moment>.png in the test package's external files, in light and dark.
 * The app's clock is [clock] (the captures' [CAPTURED] unless a test moves it on), its words English as the app's
 * Android copy has them, under Reduce Motion. [fontScale] is the composition's own Density, not the phone's setting:
 * 2, Android's largest step, which the app reads as iOS's accessibility2 (DynamicTypeSize), a shot at it named
 * <row>-<moment>-largest. What TalkBack would hear is read from the deliveries' announcements ([heard]). The views the
 * app asks the system for (a carrier's page in the browser) are kept here and not started, so nothing leaves the
 * device; the catalog a test installs is put back after it, with the monitor that keeps them.
 */
internal class ParcelWalk(
    private val compose: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>,
) : ExternalResource() {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val shots = DeviceShots(compose, FOLDER)

    val localizer = Localizer(AppLanguage.En, variant = "android")

    /** The bundled catalog's carriers, made before any test installs another catalog. */
    val identities: CarrierIdentities = DemoCards.identities
    private val catalog: CarrierCatalog = CarrierCatalog.shared

    lateinit var source: FakeDeliveries
        private set

    @Volatile
    private var state: DeliveriesState? = null

    /** The window's deliveries, as their tab last told them. */
    val deliveries: DeliveriesState get() = checkNotNull(state) { "No deliveries shown" }

    /** The app's wall clock, which the composition reads through `LocalNow`. */
    @Volatile
    var clock: Instant = CAPTURED

    var dark by mutableStateOf(false)
    var fontScale by mutableFloatStateOf(1f)

    private val asked = mutableListOf<Intent>()

    /** The views the app asks the system for, kept here: nothing opens a browser over the window or leaves the device. */
    private val monitor = object : Instrumentation.ActivityMonitor() {
        override fun onStartActivity(intent: Intent): Instrumentation.ActivityResult? {
            if (intent.action != Intent.ACTION_VIEW) return null
            synchronized(asked) { asked += intent }
            return Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null)
        }
    }

    /** What TalkBack was told, in order, through the deliveries' announcements. */
    private val said = mutableListOf<String>()
    private var heard: DeliveriesState? = null
    private var hearing: Job? = null

    /** The keyboard's slides in or out still playing, as the window hears of them. */
    private val keyboardSlides = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
        var playing = 0

        override fun onPrepare(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) playing++
        }

        override fun onProgress(insets: WindowInsetsCompat, runningAnimations: List<WindowInsetsAnimationCompat>) = insets

        override fun onEnd(animation: WindowInsetsAnimationCompat) {
            if (animation.typeMask and WindowInsetsCompat.Type.ime() != 0) playing--
        }
    }

    override fun before() {
        instrumentation.addMonitor(monitor)
    }

    override fun after() {
        instrumentation.removeMonitor(monitor)
        instrumentation.runOnMainSync {
            hearing?.cancel()
            CarrierCatalog.install(catalog)
        }
    }

    /** The views asked of the system so far. */
    fun asked(): List<Intent> = synchronized(asked) { asked.toList() }

    /** Whether TalkBack was told `text`: told before, or being told now. */
    fun heard(text: String): Boolean = synchronized(said) { text in said } || onUi { state?.announcement?.text } == text

    fun text(key: String, vararg variables: Pair<String, Any>): String = localizer.text(key, mapOf(*variables))

    /**
     * `parcels` in the deliveries of `session`, the reader's unless said, their carriers `identities`, the stores set
     * up by `prepare`; the list once it shows.
     */
    fun show(
        parcels: List<Parcel>,
        session: AuthenticationState = READER,
        identities: CarrierIdentities = this.identities,
        prepare: FakeDeliveries.() -> Unit = {},
    ) {
        val shown = FakeDeliveries(parcels, session).apply {
            localizer = this@ParcelWalk.localizer
            prepare()
        }
        source = shown
        compose.waitsForMaps()
        compose.runOnUiThread {
            val window = compose.activity.window
            @Suppress("DEPRECATION")
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            ViewCompat.setWindowInsetsAnimationCallback(window.decorView, keyboardSlides)
        }
        compose.setContent {
            val inDark = dark
            DisposableEffect(inDark) {
                compose.activity.edgeToEdge(inDark)
                onDispose {}
            }
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides ::hear) {
                RootHost(state = session, dark = inDark, reduceMotion = true, source = shown, identities = identities, now = { clock })
            }
        }
        compose.waitUntil(LONG_MILLIS) { exists(PARCEL_LIST) }
        settle()
    }

    private fun hear(state: DeliveriesState) {
        this.state = state
        if (heard === state) return
        heard = state
        hearing?.cancel()
        hearing = source.scope.launch {
            snapshotFlow { state.announcement?.text }.collect { text -> if (!text.isNullOrEmpty()) synchronized(said) { said += text } }
        }
    }

    fun <T> onUi(read: () -> T): T = compose.runOnUiThread(read)

    /** Whether the window lays the list and the page out one over the other. */
    fun stacked(): Boolean = onUi { deliveries.arrangement } == DeliveriesArrangement.Stacked

    fun shown(): UUID? = onUi { deliveries.navigator.navigation.shown }

    /** The card of the parcel named `label` in the list: a button read with its name. */
    private fun card(label: String): SemanticsMatcher =
        hasContentDescription(label, substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST))

    /**
     * The page of `parcel`, opened from its card as a reader does: in a window that stacks them, from the list, back to
     * it first; beside the list in one that lays them out side by side.
     */
    fun open(parcel: Parcel) {
        if (stacked() && shown() != null) home()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(card(parcel.label))
        compose.onAllNodes(card(parcel.label)).onFirst().performClick()
        compose.waitUntil(LONG_MILLIS) { shown() == parcel.id }
        compose.waitUntil(LONG_MILLIS) {
            count(PARCEL_PAGE) == 1 &&
                compose.onAllNodes(hasTestTag(PAGE_NAME) and hasText(parcel.label) and hasAnyAncestor(hasTestTag(PARCEL_PAGE)), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
        }
        settle()
    }

    /** The archive's shelf at the list's foot, opened from its header. */
    fun openArchive() {
        if (stacked() && shown() != null) home()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.waitForIdle()
        compose.onAllNodes(hasContentDescription("Archived", substring = true) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_LIST)))
            .onFirst().performClick()
        settle()
    }

    /** The system's back, a gesture or the button. */
    fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
    }

    /** Back to the list in a window that stacks the page over it, whatever stands over the page first. */
    fun home() {
        repeat(4) {
            if (shown() == null) return
            back()
        }
        compose.waitUntil(LONG_MILLIS) { shown() == null }
    }

    /** A second of the app's time, and the UI idle. */
    fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** What the state written since asks drawn, on a clock the test moves itself or not. */
    fun recompose() {
        compose.waitForIdle()
        if (!compose.mainClock.autoAdvance) {
            compose.mainClock.advanceTimeByFrame()
            compose.mainClock.advanceTimeByFrame()
            compose.waitForIdle()
        }
    }

    /** The test's clock moved on to `millis`, as [now] read it. */
    fun advanceTo(millis: Long) {
        val left = millis - compose.mainClock.currentTime
        if (left > 0) compose.mainClock.advanceTimeBy(left)
    }

    /** The test's clock now. */
    fun now(): Long = compose.mainClock.currentTime

    fun count(tag: String): Int = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().size

    fun exists(tag: String): Boolean = count(tag) > 0

    fun node(tag: String): SemanticsNodeInteraction = compose.onNodeWithTag(tag)

    /** Whether the part tagged `tag`, or something in it, shows `text`. */
    fun shows(tag: String, text: String): Boolean = compose.onAllNodes(
        hasText(text, substring = true) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** Whether the page shows `text`. */
    fun inPage(text: String): Boolean = shows(PARCEL_PAGE, text)

    /** The buttons of the page that say `text`. */
    fun pageButtons(text: String): Int =
        compose.onAllNodes(hasText(text) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).fetchSemanticsNodes().size

    fun pageButton(text: String): SemanticsMatcher = hasText(text) and hasClickAction() and hasAnyAncestor(hasTestTag(PARCEL_PAGE))

    /** The words of the part tagged `tag`, merged as TalkBack reads them. */
    fun texts(tag: String): List<String> =
        node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Text).orEmpty().map { it.text }

    /** A finger on what `matcher` finds, scrolled into view where it scrolls. */
    fun tap(matcher: SemanticsMatcher) {
        if (compose.onAllNodes(matcher and hasAnyAncestor(hasScrollAction())).fetchSemanticsNodes().isNotEmpty()) {
            compose.onNode(matcher).performScrollTo()
        }
        compose.onNode(matcher).performClick()
        compose.waitForIdle()
    }

    fun tap(tag: String) = tap(hasTestTag(tag))

    /** A finger on the row's switch, at its trailing end. */
    fun flip(tag: String) {
        node(tag).performTouchInput { click(Offset(width - 45.dp.toPx(), height / 2f)) }
        compose.waitForIdle()
    }

    fun toggle(tag: String): ToggleableState? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ToggleableState)

    fun editable(tag: String): String? = node(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)?.text

    /** Whether the part tagged `tag`, or something in it, spins. */
    fun spins(tag: String): Boolean = compose.onAllNodes(
        hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate) and (hasTestTag(tag) or hasAnyAncestor(hasTestTag(tag))),
        useUnmergedTree = true,
    ).fetchSemanticsNodes().isNotEmpty()

    /** What the focused field asks of the keyboard, as the window hands it to the keyboard. */
    fun editorInfo(): EditorInfo {
        compose.waitForIdle()
        val view = (compose.onNode(isFocused()).fetchSemanticsNode().root as ViewRootForTest).view
        return EditorInfo().also { info -> compose.runOnUiThread { view.onCreateInputConnection(info) } }
    }

    private fun keyboardShown() =
        onUi { ViewCompat.getRootWindowInsets(compose.activity.window.decorView)?.isVisible(WindowInsetsCompat.Type.ime()) == true }

    /** Whether the device shows a keyboard on the screen for a field, as the sign-in's device test asks. */
    private fun showsAKeyboard(): Boolean {
        val context = compose.activity
        val hardware = context.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
        return !hardware || Settings.Secure.getInt(context.contentResolver, "show_ime_with_hard_keyboard", 0) != 0
    }

    /**
     * The keyboard up where the device shows one, when `required` (a field tapped as a finger does); where a hardware
     * keyboard is open, or a field took the focus by itself, the system may still bring the soft one up, late, so that
     * is given the same time without failing where it stays down.
     */
    fun awaitKeyboard(required: Boolean = true) {
        if (required && showsAKeyboard()) {
            compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() }
        } else {
            runCatching { compose.waitUntil(KEYBOARD_MILLIS) { keyboardShown() } }
        }
        awaitStill()
    }

    /** No keyboard slide playing, and the UI idle. */
    fun awaitStill() {
        compose.waitUntil(LONG_MILLIS) { onUi { keyboardSlides.playing == 0 } }
        compose.waitForIdle()
    }

    /**
     * The window leaves the screen for `seconds` (the carrier's page in the browser over it, which [monitor] kept from
     * opening) and comes back, the questions' clock moved on as much; the test's own clock then stands still, which
     * [advanceTo] moves on.
     */
    fun awayAndBack(seconds: Long = 2) {
        compose.activityRule.scenario.moveToState(Lifecycle.State.CREATED)
        source.now = source.now.plusSeconds(seconds)
        compose.mainClock.autoAdvance = false
        compose.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    /**
     * The whole screen once still as [FOLDER]/`moment`.png, -largest at the largest text, then, unless `alsoDark` is
     * false, again in the dark appearance as `moment`-dark, the light one put back after.
     */
    fun shot(moment: String, alsoDark: Boolean = true) {
        val name = if (fontScale >= 2f) "$moment-largest" else moment
        awaitStill()
        shots.take(name)
        if (!alsoDark) return
        dark = true
        recompose()
        awaitStill()
        shots.take("$name-dark")
        dark = false
        recompose()
    }

    companion object {
        /** The shots' folder in the test package's external files. */
        const val FOLDER = "stateswalk"

        /** How long the device tests wait for the window to show what was asked. */
        const val LONG_MILLIS = 5_000L

        /** How long a keyboard takes to start coming up, where the device shows one. */
        const val KEYBOARD_MILLIS = 3_000L

        /** The synthetic account the walks sign in with. */
        val READER = AuthenticationState.SignedIn(AuthUser(UUID(0, 13), email = "reader@example.com"))
    }
}
