package com.plhery.peekdeliverytracker.ui

import android.accessibilityservice.AccessibilityServiceInfo
import android.graphics.Rect
import android.os.Build
import android.os.SystemClock
import android.util.Log
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
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.ui.ParcelWalk.Companion.LONG_MILLIS
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * The window as an accessibility service receives it, which is what TalkBack reads: UiAutomation's root of the active
 * window and every node under it, each before what it holds, in the order the app lists them. The service keeps what it
 * read until the app tells it of a change, which Compose doesn't while no screen reader is on, so every node is read
 * again from the app ([AccessibilityNodeInfo.refresh]) rather than taken from that cache. The tree is read from the
 * test's thread: the app answers on its main thread. UiAutomation's own service reports the views' ids, which carry the
 * app's test tags (`testTagsAsResourceId` at the root, as UI Automator and Maestro find them), and it receives the nodes
 * not important for accessibility too, which a test tells apart by [AccessibilityNodeInfo.isImportantForAccessibility]
 * and [AccessibilityNodeInfo.isScreenReaderFocusable], the stops TalkBack makes.
 *
 * Compose hands the service its nodes unmerged and leaves the merging to TalkBack. A node that merges children of its
 * own (anything clickable, or a row made one element) carries the actions, the state and the focus, but rarely its
 * words: the description it was given goes to a first child Compose makes up for it, the words drawn inside stay on
 * their own children, and its role goes to a last child made up the same way, the node itself keeping a plain class. A
 * test reads what TalkBack says for a node with [spoken] and its role with [isButton] or [isSwitch], and reads the node
 * alone ([words], [description], [state], [clickLabel]) only where it means the node itself.
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
        val said = await("$id saying \"$words\"", read = ::framed) { nodes -> nodes.firstOrNull { it.viewIdResourceName == id && it.spoken == words } }
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
     * The stops that would say nothing: a node TalkBack stops on or a finger can press, shown, that TalkBack reads no
     * words nor any state for, its own or those of the children it reads with it ([spoken]). A live region's node is
     * left out: it is heard as its words change, not stopped on, and stands empty between two announcements.
     */
    fun blankStops(nodes: List<AccessibilityNodeInfo> = nodes()): List<AccessibilityNodeInfo> =
        nodes.filter { node ->
            node.isVisibleToUser && node.isStop && node.liveRegion == 0 && node.readWith().all { it.ownWords.isEmpty() && it.state.isNullOrEmpty() }
        }

    /** Asserts nothing TalkBack stops on or a finger presses in `nodes` says nothing. */
    fun assertNoBlankStops(nodes: List<AccessibilityNodeInfo> = nodes()) {
        val blank = blankStops(nodes)
        assertTrue("Stops that say nothing:\n${dump(blank)}", blank.isEmpty())
    }

    /** The window's nodes as lines, for a failure's message. */
    fun dump(nodes: List<AccessibilityNodeInfo> = nodes()): String = nodes.take(DUMP_LINES).joinToString("\n") { it.line() }

    /**
     * A rule that logs the whole window to logcat under [LOG_TAG] when the test it wraps fails, before the test's other
     * rules take the window down: every node on a line of its own, indented under the node that holds it, so that a run
     * on a device shows what the window had. The innermost of a test's rules.
     */
    val logsTheWindowOnFailure: TestRule = object : TestWatcher() {
        override fun failed(e: Throwable, description: Description) {
            runCatching { log("${description.className}.${description.methodName} failed: ${e.message?.lineSequence()?.firstOrNull()}") }
        }
    }

    /** The whole window logged under [LOG_TAG] as [logsTheWindowOnFailure] logs it, after `heading`. */
    fun log(heading: String) {
        Log.i(LOG_TAG, heading)
        val root = instrumentation.uiAutomation.rootInActiveWindow
        if (root == null || !root.refresh()) {
            Log.i(LOG_TAG, "No active window")
            return
        }
        log(root, 0)
    }

    private fun log(node: AccessibilityNodeInfo, depth: Int) {
        Log.i(LOG_TAG, "  ".repeat(depth) + node.fullLine())
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            if (child.refresh()) log(child, depth + 1)
        }
    }

    companion object {
        /** How long a test waits for the tree to show something: each read of it asks the app for every node. */
        const val AWAIT_MILLIS = 10_000L

        /** The logcat tag a failed test's window is logged under ([logsTheWindowOnFailure]). */
        const val LOG_TAG = "AccessTree"

        private const val POLL_MILLIS = 50L
        private const val DUMP_LINES = 400
    }
}

/** The node's own words: its description, or its text where it has none. What TalkBack says is [spoken]. */
internal val AccessibilityNodeInfo.words: String get() = (contentDescription ?: text)?.toString().orEmpty()

/** The node's own description, which Compose moves off a node that merges children of its own ([AccessTree]). */
internal val AccessibilityNodeInfo.description: String? get() = contentDescription?.toString()

/**
 * What TalkBack says for the node on a stop, part by part in order: its description where it has one; otherwise its
 * text, then what each of its children shown says, by the same rule, past those TalkBack stops on by themselves (a
 * child that can be focused, clicked or long-pressed). The words Compose moves off a node that merges children of its
 * own are read from those children this way, as TalkBack reads them. The node's role and state are read apart
 * ([isButton], [isSwitch], [state]).
 */
internal val AccessibilityNodeInfo.spokenParts: List<String> get() = readWith().map { it.ownWords }.filter(String::isNotEmpty)

/** What TalkBack says for the node on a stop: its [spokenParts], joined as TalkBack joins them. */
internal val AccessibilityNodeInfo.spoken: String get() = spokenParts.joinToString(", ")

/** Whether TalkBack stops on the node or a finger can press it. */
internal val AccessibilityNodeInfo.isStop: Boolean get() = isScreenReaderFocusable || isClickable

/** The node and, where it has no description, the children shown that TalkBack reads with it, theirs after each. */
private fun AccessibilityNodeInfo.readWith(): List<AccessibilityNodeInfo> {
    val read = mutableListOf(this)
    if (contentDescription.isNullOrEmpty()) {
        for (index in 0 until childCount) {
            val child = getChild(index) ?: continue
            if (child.refresh() && child.isVisibleToUser && !child.standsAlone) read += child.readWith()
        }
    }
    return read
}

/** What TalkBack takes from the node itself: its description, or its text where it has none or an empty one. */
private val AccessibilityNodeInfo.ownWords: String get() = contentDescription?.toString()?.ifEmpty { null } ?: text?.toString().orEmpty()

/** Whether TalkBack stops on the node by itself rather than reading it with the stop that holds it. */
private val AccessibilityNodeInfo.standsAlone: Boolean get() = isScreenReaderFocusable || isClickable || isLongClickable || isFocusable

/** The state TalkBack reads after a node's name. */
internal val AccessibilityNodeInfo.state: String? get() = stateDescription?.toString()

internal fun AccessibilityNodeInfo.action(id: Int): AccessibilityAction? = actionList.firstOrNull { it.id == id }

/** What TalkBack says a double tap does, the click's label. */
internal val AccessibilityNodeInfo.clickLabel: String? get() = action(AccessibilityNodeInfo.ACTION_CLICK)?.label?.toString()

/** The action TalkBack's actions menu lists as `label`, a custom action of the app's. */
internal fun AccessibilityNodeInfo.actionNamed(label: String): AccessibilityAction? = actionList.firstOrNull { it.label?.toString() == label }

/** The role TalkBack says in place of the node's class, where it has one: Compose gives one to a switch and a tab. */
internal val AccessibilityNodeInfo.roleDescription: String? get() = AccessibilityNodeInfoCompat.wrap(this).roleDescription?.toString()

/**
 * The child Compose makes up to carry the role of a node that merges children of its own, the last of them: no id,
 * words, children or action of its own, and the role's class or role description. The node itself keeps a plain class,
 * and TalkBack says the role as it reads that child with it. Null where the node carries its role itself.
 */
private val AccessibilityNodeInfo.roleChild: AccessibilityNodeInfo?
    get() {
        if (childCount == 0 || !isScreenReaderFocusable) return null
        val last = getChild(childCount - 1)?.takeIf { it.refresh() } ?: return null
        val bare = last.childCount == 0 && last.viewIdResourceName == null && last.contentDescription.isNullOrEmpty() &&
            last.text.isNullOrEmpty() && !last.standsAlone
        val role = last.className?.toString() != "android.view.View" || !last.roleDescription.isNullOrEmpty()
        return last.takeIf { bare && role }
    }

/** What carries the node's role: its [roleChild] where Compose made one up, else the node itself. */
private val AccessibilityNodeInfo.roleBearer: AccessibilityNodeInfo get() = roleChild ?: this

/** Whether TalkBack calls the node a button, from the class Compose gives `Role.Button` on it or its [roleChild]. */
internal val AccessibilityNodeInfo.isButton: Boolean get() = roleBearer.className == "android.widget.Button"

/**
 * Whether TalkBack calls the node a switch: Compose gives `Role.Switch` no class but its role description (its string
 * `switch_role`, in the app's language), on the node or its [roleChild].
 */
internal val AccessibilityNodeInfo.isSwitch: Boolean
    get() = roleBearer.let { it.className == "android.widget.Switch" || it.roleDescription == switchRole }

/**
 * The role description Compose gives `Role.Switch`: its string `switch_role`, looked up by name among the app's own
 * resources, where Compose's are merged, in the app's words.
 */
private val switchRole: String by lazy {
    val app = InstrumentationRegistry.getInstrumentation().targetContext
    @Suppress("DiscouragedApi")
    val id = app.resources.getIdentifier("switch_role", "string", app.packageName)
    check(id != 0) { "The app has no string switch_role of Compose's" }
    app.getString(id)
}

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
    roleDescription?.let { "role=\"$it\"" },
    "focusable".takeIf { isScreenReaderFocusable },
    "clickable".takeIf { isClickable },
    "heading".takeIf { isHeading },
    "checked".takeIf { ticked },
    "selected".takeIf { isSelected },
    "hidden".takeIf { !isVisibleToUser },
    "children=$childCount",
).joinToString(" ")

/**
 * The node as a line of the window's log ([AccessTree.logsTheWindowOnFailure]): its class, id, description, text, state
 * and role description, whether it is checked, selected, a heading, focusable, focusable by a screen reader, clickable
 * and shown, its actions' labels and its bounds.
 */
private fun AccessibilityNodeInfo.fullLine(): String = listOfNotNull(
    className?.toString()?.substringAfterLast('.'),
    viewIdResourceName?.let { "#$it" },
    contentDescription?.let { "desc=\"$it\"" },
    text?.let { "text=\"$it\"" },
    stateDescription?.let { "state=\"$it\"" },
    roleDescription?.let { "role=\"$it\"" },
    "checked".takeIf { ticked },
    "selected".takeIf { isSelected },
    "heading".takeIf { isHeading },
    "focusable".takeIf { isFocusable },
    "screenReaderFocusable".takeIf { isScreenReaderFocusable },
    "clickable".takeIf { isClickable },
    "live".takeIf { liveRegion != View.ACCESSIBILITY_LIVE_REGION_NONE },
    "hidden".takeIf { !isVisibleToUser },
    actionList.mapNotNull { action -> action.label?.let { "${actionName(action.id)}=\"$it\"" } }.ifEmpty { null }?.joinToString(" ", "actions[", "]"),
    bounds.toShortString(),
).joinToString(" ")

/** The name of a standard action TalkBack labels, or the id of one of the app's own. */
private fun actionName(id: Int): String = when (id) {
    AccessibilityNodeInfo.ACTION_CLICK -> "click"
    AccessibilityNodeInfo.ACTION_LONG_CLICK -> "longClick"
    else -> id.toString()
}

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
