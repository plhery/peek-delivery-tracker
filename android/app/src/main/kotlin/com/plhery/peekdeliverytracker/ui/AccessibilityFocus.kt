package com.plhery.peekdeliverytracker.ui

import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import androidx.compose.runtime.Stable
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.RootForTest
import androidx.compose.ui.node.SemanticsModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.getOrNull
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Where TalkBack's focus can be sent ([request]): the element [accessibilityFocus] is on, which TalkBack reads as one
 * (or the first such in it), as VoiceOver moves to a menu or an alert as it opens and back to its card as it closes.
 */
@Stable
internal class AccessibilityFocus {
    internal var node: AccessibilityFocusNode? = null

    /** Moves TalkBack's focus there once it is laid out and TalkBack can reach it; nothing while TalkBack is off. */
    fun request() {
        node?.request()
    }
}

internal fun Modifier.accessibilityFocus(focus: AccessibilityFocus): Modifier = this then AccessibilityFocusElement(focus)

private data class AccessibilityFocusElement(val focus: AccessibilityFocus) : ModifierNodeElement<AccessibilityFocusNode>() {
    override fun create() = AccessibilityFocusNode(focus)

    override fun update(node: AccessibilityFocusNode) {
        node.focus = focus
    }
}

internal class AccessibilityFocusNode(focus: AccessibilityFocus) :
    Modifier.Node(), SemanticsModifierNode, CompositionLocalConsumerModifierNode {
    var focus: AccessibilityFocus = focus
        set(value) {
            if (field.node === this) field.node = null
            field = value
            if (isAttached) value.node = this
        }

    private var pending: Job? = null

    override fun onAttach() {
        focus.node = this
    }

    override fun onDetach() {
        if (focus.node === this) focus.node = null
    }

    // Marks the element, without adding one, so that it can be found among the window's.
    override fun SemanticsPropertyReceiver.applySemantics() {
        this[FocusMark] = this@AccessibilityFocusNode
    }

    fun request() {
        val view = currentValueOf(LocalView)
        val manager = view.context.getSystemService(AccessibilityManager::class.java)
        if (manager == null || !manager.isEnabled || !manager.isTouchExplorationEnabled) return
        pending?.cancel()
        pending = coroutineScope.launch {
            // Once laid out, and once what covered it from TalkBack has gone: within a few frames.
            repeat(FOCUS_FRAMES) {
                withFrameNanos {}
                val id = target(view)?.id ?: return@repeat
                val provider = view.accessibilityNodeProvider ?: return@launch
                if (provider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true) return@launch
                if (provider.performAction(id, AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)) return@launch
            }
        }
    }

    /**
     * What TalkBack stops on, while it can reach it: the element it reads the marked one in (the marked one itself, or
     * the one it is merged into) when that says something, else the first in it that does. The window's elements are
     * found through its merged semantics, the elements TalkBack reads.
     */
    private fun target(view: View): SemanticsNode? {
        val root = (view as? RootForTest)?.semanticsOwner?.rootSemanticsNode ?: return null
        val marked = find(root) { it.config.getOrNull(FocusMark) === this } ?: return null
        return find(marked) { it.speaks() }
    }

    private fun SemanticsNode.speaks(): Boolean = config.isMergingSemanticsOfDescendants || config.isClearingSemantics ||
        SemanticsProperties.ContentDescription in config || SemanticsProperties.Text in config

    private fun find(from: SemanticsNode, matches: (SemanticsNode) -> Boolean): SemanticsNode? {
        val queue = ArrayDeque(listOf(from))
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (matches(node)) return node
            queue.addAll(node.children)
        }
        return null
    }
}

private val FocusMark = SemanticsPropertyKey<AccessibilityFocusNode>("AccessibilityFocus", mergePolicy = { parent, child -> parent ?: child })

private const val FOCUS_FRAMES = 10
