package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityServiceInfo
import android.graphics.Rect
import android.os.Build
import android.os.SystemClock
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * The window as an accessibility service receives it, which is what TalkBack reads: UiAutomation's root of the active
 * window and every node under it, each before what it holds, in the order the app lists them. The service keeps what it
 * read until the app tells it of a change, which Compose doesn't while no screen reader is on, so every node is read
 * again from the app ([AccessibilityNodeInfo.refresh]) rather than taken from that cache. The tree is read from the
 * test's thread: the app answers on its main thread. UiAutomation's own service reports the views' ids, which carry the
 * app's test tags (`testTagsAsResourceId` at the root, as UI Automator and Maestro find them), and it receives the nodes
 * not important for accessibility too, which a test tells apart by [AccessibilityNodeInfo.isImportantForAccessibility]
 * and [AccessibilityNodeInfo.isScreenReaderFocusable], the stops TalkBack makes.
 */
internal class AccessTree(private val compose: ComposeTestRule) {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    /** Every node of the active window, read again. */
    fun nodes(): List<AccessibilityNodeInfo> {
        val found = mutableListOf<AccessibilityNodeInfo>()
        val root = instrumentation.uiAutomation.rootInActiveWindow ?: return found
        if (root.refresh()) collect(root, found)
        return found
    }

    /** The node whose id is `id` and everything under it, read again; nothing where the window shows no such node. */
    fun within(id: String): List<AccessibilityNodeInfo> {
        val root = instrumentation.uiAutomation.rootInActiveWindow ?: return emptyList()
        return if (root.refresh()) find(root, id).orEmpty() else emptyList()
    }

    private fun find(node: AccessibilityNodeInfo, id: String): List<AccessibilityNodeInfo>? {
        if (node.viewIdResourceName == id) return mutableListOf<AccessibilityNodeInfo>().also { collect(node, it) }
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            if (child.refresh()) find(child, id)?.let { return it }
        }
        return null
    }

    /** `node` and everything under it, read again. */
    fun under(node: AccessibilityNodeInfo): List<AccessibilityNodeInfo> {
        val found = mutableListOf<AccessibilityNodeInfo>()
        if (node.refresh()) collect(node, found)
        return found
    }

    private fun collect(node: AccessibilityNodeInfo, into: MutableList<AccessibilityNodeInfo>) {
        into += node
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            if (child.refresh()) collect(child, into)
        }
    }

    /** The node whose id is `id`, if the window shows one. */
    fun byId(id: String): AccessibilityNodeInfo? = nodes().firstOrNull { it.viewIdResourceName == id }

    /**
     * What `find` makes of the window's nodes (or of those `read` reads), once it makes something of them, within
     * `millis`; those nodes in the failure where it never does. The app's clock runs on between two reads where the test
     * lets it advance by itself.
     */
    fun <T : Any> await(
        what: String,
        millis: Long = AWAIT_MILLIS,
        read: () -> List<AccessibilityNodeInfo> = ::nodes,
        find: (List<AccessibilityNodeInfo>) -> T?,
    ): T {
        val end = SystemClock.uptimeMillis() + millis
        while (true) {
            val nodes = read()
            find(nodes)?.let { return it }
            if (SystemClock.uptimeMillis() > end) throw AssertionError("$what: not in the window's accessibility tree\n${dump(nodes)}")
            if (compose.mainClock.autoAdvance) compose.waitForIdle()
            SystemClock.sleep(POLL_MILLIS)
        }
    }

    /** The node whose id is `id`, once the window shows it. */
    fun awaitId(id: String, millis: Long = AWAIT_MILLIS): AccessibilityNodeInfo =
        await("The node $id", millis) { nodes -> nodes.firstOrNull { it.viewIdResourceName == id } }

    /** Waits until `gone` holds of the window's nodes. */
    fun awaitGone(
        what: String,
        millis: Long = AWAIT_MILLIS,
        read: () -> List<AccessibilityNodeInfo> = ::nodes,
        gone: (List<AccessibilityNodeInfo>) -> Boolean,
    ) {
        await(what, millis, read) { nodes -> Unit.takeIf { gone(nodes) } }
    }

    /**
     * `body` with the app's clock held, moved on a frame before each read of [framed] and as the test moves it: a live
     * region's news is emptied a second after it comes, which a clock running by itself passes before the tree is read.
     */
    fun <T> holdingTheClock(body: () -> T): T {
        compose.mainClock.autoAdvance = false
        try {
            return body()
        } finally {
            compose.mainClock.autoAdvance = true
        }
    }

    /** The window's nodes, read once the app has drawn another frame of the clock [holdingTheClock] holds. */
    fun framed(): List<AccessibilityNodeInfo> {
        compose.waitForIdle()
        compose.mainClock.advanceTimeByFrame()
        compose.waitForIdle()
        return nodes()
    }

    /**
     * The live region `id` once it says `words`, read frame by frame on the clock [holdingTheClock] holds: TalkBack
     * hears a polite region's words as they change.
     */
    fun awaitSaid(id: String, words: String): AccessibilityNodeInfo {
        val said = await("$id saying \"$words\"", read = ::framed) { nodes -> nodes.firstOrNull { it.viewIdResourceName == id && it.description == words } }
        assertEquals("$id is no polite live region", View.ACCESSIBILITY_LIVE_REGION_POLITE, said.liveRegion)
        return said
    }

    /**
     * Whether TalkBack reads `node` as one element: a stop itself, with nothing under it that a finger or a double tap
     * acts on apart from it. The words under a node that merges them stay in the tree as its children, which TalkBack
     * reads with it.
     */
    fun oneElement(node: AccessibilityNodeInfo): Boolean =
        node.isScreenReaderFocusable && under(node).drop(1).none { it.isClickable || it.isLongClickable || it.isCheckable || it.isEditable }

    /**
     * The stops that would say nothing: a node TalkBack stops on or a finger can press, shown, with no words of its own
     * nor any under it. A live region's node is left out: it is heard as its words change, not stopped on, and stands
     * empty between two announcements.
     */
    fun blankStops(nodes: List<AccessibilityNodeInfo> = nodes()): List<AccessibilityNodeInfo> =
        nodes.filter { node ->
            node.isVisibleToUser && (node.isScreenReaderFocusable || node.isClickable) && node.liveRegion == 0 &&
                under(node).all { it.words.isEmpty() && it.state.isNullOrEmpty() }
        }

    /** Asserts nothing TalkBack stops on or a finger presses in `nodes` says nothing. */
    fun assertNoBlankStops(nodes: List<AccessibilityNodeInfo> = nodes()) {
        val blank = blankStops(nodes)
        assertTrue("Stops that say nothing:\n${dump(blank)}", blank.isEmpty())
    }

    /** The window's nodes as lines, for a failure's message. */
    fun dump(nodes: List<AccessibilityNodeInfo> = nodes()): String = nodes.take(DUMP_LINES).joinToString("\n") { it.line() }

    companion object {
        /** How long a test waits for the tree to show something: each read of it asks the app for every node. */
        const val AWAIT_MILLIS = 10_000L

        private const val POLL_MILLIS = 50L
        private const val DUMP_LINES = 400
    }
}

/** What TalkBack says a node is called: its description, or its text where it has none. */
internal val AccessibilityNodeInfo.words: String get() = (contentDescription ?: text)?.toString().orEmpty()

internal val AccessibilityNodeInfo.description: String? get() = contentDescription?.toString()

/** The state TalkBack reads after a node's name. */
internal val AccessibilityNodeInfo.state: String? get() = stateDescription?.toString()

internal fun AccessibilityNodeInfo.action(id: Int): AccessibilityAction? = actionList.firstOrNull { it.id == id }

/** What TalkBack says a double tap does, the click's label. */
internal val AccessibilityNodeInfo.clickLabel: String? get() = action(AccessibilityNodeInfo.ACTION_CLICK)?.label?.toString()

/** The action TalkBack's actions menu lists as `label`, a custom action of the app's. */
internal fun AccessibilityNodeInfo.actionNamed(label: String): AccessibilityAction? = actionList.firstOrNull { it.label?.toString() == label }

/** Whether TalkBack calls the node a button, from the class Compose gives `Role.Button`. */
internal val AccessibilityNodeInfo.isButton: Boolean get() = className == "android.widget.Button"

/** Whether TalkBack calls the node a switch, from the class Compose gives `Role.Switch`. */
internal val AccessibilityNodeInfo.isSwitch: Boolean get() = className == "android.widget.Switch"

/**
 * Whether TalkBack hears the node as selected: Compose sets a tab's selection as the node's, and any other's as its
 * being checked, with "Selected" as its state.
 */
internal val AccessibilityNodeInfo.chosen: Boolean get() = isSelected || ticked

/** Whether the node is checked, which Android 16 reads as one of three states and the phones before it as a yes or no. */
internal val AccessibilityNodeInfo.ticked: Boolean
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA) checked == AccessibilityNodeInfo.CHECKED_STATE_TRUE else @Suppress("DEPRECATION") isChecked

internal fun AccessibilityNodeInfo.click(): Boolean = performAction(AccessibilityNodeInfo.ACTION_CLICK)

/** The node's bounds on the screen, in pixels. */
internal val AccessibilityNodeInfo.bounds: Rect get() = Rect().also(::getBoundsInScreen)

/**
 * Asserts the node `what` is at least `side` dp tall, and as wide unless `across` is false (a row or a strip that a
 * finger finds by its height or its width alone), as a finger needs.
 */
internal fun AccessibilityNodeInfo.assertTarget(what: String, side: Float = 44f, across: Boolean = true, along: Boolean = true) {
    val density = InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
    val least = side * density - 1
    val bounds = bounds
    val wide = !across || bounds.width() >= least
    val tall = !along || bounds.height() >= least
    assertTrue("$what is ${bounds.width()}x${bounds.height()} px, under $side dp", wide && tall)
}

/** A pattern matching the words of `key` whatever its `variables` are. */
internal fun ParcelWalk.pattern(key: String, vararg variables: String): Regex =
    text(key, *variables.map { it to PATTERN_MARK }.toTypedArray()).split(PATTERN_MARK).joinToString(".+") { Regex.escape(it) }.toRegex()

/** A placeholder no copy holds, standing for a variable while a pattern is made of a key's words. */
private const val PATTERN_MARK = "\u0001"

private fun AccessibilityNodeInfo.line(): String = listOfNotNull(
    className?.toString()?.substringAfterLast('.'),
    viewIdResourceName?.let { "#$it" },
    contentDescription?.let { "desc=\"$it\"" },
    text?.let { "text=\"$it\"" },
    stateDescription?.let { "state=\"$it\"" },
    "focusable".takeIf { isScreenReaderFocusable },
    "clickable".takeIf { isClickable },
    "heading".takeIf { isHeading },
    "checked".takeIf { ticked },
    "selected".takeIf { isSelected },
    "hidden".takeIf { !isVisibleToUser },
    "children=$childCount",
).joinToString(" ")

/** Whether touch exploration is on, as TalkBack turns it on and the app reads it. */
internal fun touchExploration(): Boolean =
    InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(AccessibilityManager::class.java)?.isTouchExplorationEnabled == true

/**
 * `body` with touch exploration on, as TalkBack has it, asked of UiAutomation's service and turned off again after;
 * where the device doesn't turn it on, `body` runs without it and says what it does then.
 */
internal fun ComposeTestRule.exploringByTouch(body: () -> Unit) {
    val automation = InstrumentationRegistry.getInstrumentation().uiAutomation
    val info = automation.serviceInfo
    val flags = info?.flags ?: 0
    if (info != null) {
        info.flags = flags or AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE
        runCatching { automation.serviceInfo = info }
        runCatching { waitUntil(LONG_MILLIS) { touchExploration() } }
    }
    try {
        body()
    } finally {
        if (info != null) {
            info.flags = flags
            runCatching { automation.serviceInfo = info }
            runCatching { waitUntil(LONG_MILLIS) { !touchExploration() } }
        }
    }
}

/**
 * What `node` finds, scrolled into the window and up clear of the tab bar floating over the foot of a phone's window
 * (a tablet's stands at its top, over nothing that scrolls), by the innermost of the `scrollable` nodes.
 */
internal fun ComposeTestRule.reveal(node: SemanticsNodeInteraction, scrollable: SemanticsMatcher = hasScrollAction()): SemanticsNodeInteraction {
    node.performScrollTo()
    val bounds = node.fetchSemanticsNode().boundsInRoot
    val bar = onAllNodes(hasTestTag(TAB_BAR)).fetchSemanticsNodes().firstOrNull()?.boundsInRoot
    val window = onRoot().fetchSemanticsNode().boundsInRoot
    val hidden = if (bar != null && bar.top > window.height / 2) bounds.bottom - bar.top else 0f
    if (hidden > 0f) {
        onAllNodes(scrollable, useUnmergedTree = true).onLast().performSemanticsAction(SemanticsActions.ScrollBy) { scroll ->
            scroll(0f, hidden + with(density) { 16.dp.toPx() })
        }
        waitForIdle()
    }
    return node
}

/** What has `tag`, revealed as [reveal] does where something scrolls it, by the innermost scroll that holds it. */
internal fun ComposeTestRule.reveal(tag: String) {
    val scrolled = onAllNodes(hasTestTag(tag) and hasAnyAncestor(hasScrollAction()), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
    if (scrolled) reveal(onNode(hasTestTag(tag), useUnmergedTree = true), hasScrollAction() and hasAnyDescendant(hasTestTag(tag)))
    waitForIdle()
}
