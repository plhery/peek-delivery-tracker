package com.plhery.peekdeliverytracker.ui

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.deliveries.ACCOUNT_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.openCard
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The order TalkBack reads the main screens in, as VoiceOver reads the iPhone's and the iPad's: each bar before the
 * content that scrolls under it, the demo's bar after the bar at the top, a sheet's buttons before its title, and
 * nothing TalkBack stops on without a label.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class TalkBackOrderTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    @Config(qualifiers = PHONE)
    fun onAPhone() {
        compose.setContent { RootHost(state = AuthenticationState.Demo) }
        val list = stops()
        assertStartsWith(listOf("Add a parcel", "Deliveries", "Account", DEMO, "On the way"), list)
        assertEquals(listOf("Deliveries", "Passport", "Friends"), list.takeLast(3))

        compose.openCard("New trainers")
        val page = stops()
        assertStartsWith(listOf("Deliveries", "Parcel actions", DEMO), page)
        assertInARow(listOf("Tracking number", "1234567899", "Copy tracking number"), page)
        back()

        compose.onNodeWithTag(ADD_BUTTON).performClick()
        assertStartsWith(listOf("Add a parcel", "Close", "Tracking number or link"), stops())
        back()

        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        assertStartsWith(listOf("Drag handle", "Close", "Settings"), stops())
        back()

        tab("Passport")
        assertStartsWith(listOf("Passport", "Account", DEMO), stops())
        tab("Friends")
        assertStartsWith(listOf("Friends", "Account", DEMO), stops())
    }

    @Test
    @Config(qualifiers = "w1280dp-h800dp-land-xhdpi")
    fun onATablet() {
        compose.setContent { RootHost(state = AuthenticationState.Demo) }
        val split = stops()
        // The tabs at the top, the list's column, adding and the account over the page, then the page.
        assertStartsWith(listOf("Deliveries", "Passport", "Friends", "Deliveries", DEMO, "On the way"), split)
        val bar = split.indexOf("Add a parcel")
        assertEquals(listOf("Add a parcel", "Account", "Parcel actions"), split.subList(bar, bar + 3))
        assertTrue("The cards before the page's bar: $split", split.indexOfFirst { it.startsWith("FedEx") } in 0 until bar)
        assertTrue("The page after its bar: $split", split.indexOf("Tracking number") > bar)
        assertInARow(listOf("Tracking number", "1234567899", "Copy tracking number"), split)
        assertEquals("Check now", split.last())

        compose.onNodeWithTag(ACCOUNT_BUTTON).performClick()
        assertStartsWith(listOf("Drag handle", "Close", "Settings"), stops())
        back()

        tab("Passport")
        assertStartsWith(listOf("Deliveries", "Passport", "Friends", "Account", DEMO), stops())
        tab("Friends")
        assertStartsWith(listOf("Deliveries", "Passport", "Friends", "Account", DEMO), stops())
    }

    /**
     * What TalkBack stops on, in order: the traversal Compose hands it, from the window down, of what has a label or an
     * action. Fails on anything focusable or pressable without a label.
     */
    private fun stops(): List<String> {
        compose.waitForIdle()
        val merged = mutableMapOf<Int, SemanticsNode>()
        fun collect(node: SemanticsNode) {
            merged[node.id] = node
            node.children.forEach(::collect)
        }
        collect(compose.onRoot().fetchSemanticsNode())
        val root = compose.onRoot(useUnmergedTree = true).fetchSemanticsNode()
        val all = mutableListOf<SemanticsNode>()
        fun collectUnmerged(node: SemanticsNode) {
            all += node
            node.children.forEach(::collectUnmerged)
        }
        collectUnmerged(root)

        // Compose hands TalkBack its order only while TalkBack is on, as the traversal-before of each node.
        val view = compose.activity.window.decorView.composeView()
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
                if (label != null) stops += label
            }
            id = before[id]
        }
        return stops
    }

    private fun View.composeView(): View {
        if (javaClass.name == "androidx.compose.ui.platform.AndroidComposeView") return this
        if (this is ViewGroup) for (i in 0 until childCount) runCatching { return getChildAt(i).composeView() }
        error("No Compose view")
    }

    private fun back() {
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
    }

    private fun tab(label: String) =
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()

    private fun assertStartsWith(expected: List<String>, stops: List<String>) =
        assertEquals("TalkBack's first stops in $stops", expected, stops.take(expected.size).map { label -> expected.firstOrNull { label.startsWith(it) } ?: label })

    private fun assertInARow(expected: List<String>, stops: List<String>) {
        val at = stops.indexOf(expected.first())
        assertEquals("In a row in $stops", expected, stops.subList(at, minOf(stops.size, at + expected.size)))
    }

    private companion object {
        const val DEMO = "Demo mode"
        const val TRAVERSAL_BEFORE = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL"
    }
}
