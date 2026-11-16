package com.plhery.peekdeliverytracker.ui

import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsConfiguration
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import org.junit.Assert.assertTrue

/**
 * What TalkBack stops on in `window`, in order: the traversal Compose hands it, from the window down, of what has a
 * label or an action. Each stop is its label; with [spoken], also what TalkBack says after it, in brackets: its role,
 * whether it is a field or a heading, and its state (on or off, checked, selected, unavailable). Fails on anything
 * focusable or pressable without a label.
 */
fun ComposeContentTestRule.talkBackStops(window: View, spoken: Boolean = false): List<String> {
    waitForIdle()
    val merged = mutableMapOf<Int, SemanticsNode>()
    fun collect(node: SemanticsNode) {
        merged[node.id] = node
        node.children.forEach(::collect)
    }
    collect(onRoot().fetchSemanticsNode())
    val root = onRoot(useUnmergedTree = true).fetchSemanticsNode()
    val all = mutableListOf<SemanticsNode>()
    fun collectUnmerged(node: SemanticsNode) {
        all += node
        node.children.forEach(::collectUnmerged)
    }
    collectUnmerged(root)

    // Compose hands TalkBack its order only while TalkBack is on, as the traversal-before of each node.
    val view = window.composeView()
    val delegate = view.javaClass.getDeclaredField("composeAccessibilityDelegate").apply { isAccessible = true }.get(view)
    val forced = delegate.javaClass.getDeclaredField("accessibilityForceEnabledForTesting").apply { isAccessible = true }
    val before = mutableMapOf<Int, Int>()
    forced.setBoolean(delegate, true)
    try {
        for (node in all) {
            val info = view.accessibilityNodeProvider.createAccessibilityNodeInfo(if (node.id == root.id) View.NO_ID else node.id) ?: continue
            val next = info.extras.getInt(TRAVERSAL_BEFORE, -1)
            if (next != -1) before[node.id] = next
        }
    } finally {
        forced.setBoolean(delegate, false)
    }

    val first = before.keys.single { it !in before.values }
    val stops = mutableListOf<String>()
    var id: Int? = first
    val seen = mutableSetOf<Int>()
    while (id != null && seen.add(id)) {
        val config = merged[id]?.config
        if (config != null && !config.contains(SemanticsProperties.HideFromAccessibility)) {
            val label = (config.getOrNull(SemanticsProperties.ContentDescription) ?: config.getOrNull(SemanticsProperties.Text)?.map { it.text })
                ?.joinToString(", ")?.takeIf { it.isNotBlank() }
            val actionable = config.contains(SemanticsActions.OnClick) || config.contains(SemanticsProperties.Focused)
            assertTrue("Unlabelled for TalkBack: ${config.getOrNull(SemanticsProperties.TestTag)} in $stops", label != null || !actionable)
            if (label != null) stops += if (spoken) label + config.said() else label
        }
        id = before[id]
    }
    return stops
}

/** What TalkBack says after a stop's label, in brackets, where it says anything. */
private fun SemanticsConfiguration.said(): String {
    val role = getOrNull(SemanticsProperties.Role)?.toString()?.lowercase()
    val words = listOfNotNull(
        role,
        "edit box".takeIf { contains(SemanticsActions.SetText) },
        "heading".takeIf { contains(SemanticsProperties.Heading) },
        getOrNull(SemanticsProperties.StateDescription) ?: getOrNull(SemanticsProperties.ToggleableState)?.let { state ->
            when (state) {
                ToggleableState.On -> if (role == "switch") "on" else "checked"
                ToggleableState.Off -> if (role == "switch") "off" else "not checked"
                ToggleableState.Indeterminate -> "partly checked"
            }
        },
        "selected".takeIf { getOrNull(SemanticsProperties.Selected) == true },
        "unavailable".takeIf { contains(SemanticsProperties.Disabled) },
    )
    return if (words.isEmpty()) "" else words.joinToString(", ", prefix = " [", postfix = "]")
}

private fun View.composeView(): View {
    if (javaClass.name == "androidx.compose.ui.platform.AndroidComposeView") return this
    if (this is ViewGroup) for (i in 0 until childCount) runCatching { return getChildAt(i).composeView() }
    error("No Compose view")
}

private const val TRAVERSAL_BEFORE = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL"
