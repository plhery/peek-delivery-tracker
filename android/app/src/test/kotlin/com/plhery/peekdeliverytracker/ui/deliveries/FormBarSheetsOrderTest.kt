package com.plhery.peekdeliverytracker.ui.deliveries

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
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_CLOSE
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_NICKNAME
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_PANEL
import com.plhery.peekdeliverytracker.ui.friends.FRIENDS_SHARING
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * FormBarOrderTest on the other sheets whose bar is a FormBar over a full-height scroll column: Sort & filter's Done
 * and the friends' profile panel's Close come before their form. A parcel link's sheet, whose page doesn't start at the
 * sheet's top, reads its title and Close first as well.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FormBarSheetsOrderTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val links = FakeParcelLinks(answer = LinkFixtures.viewer(), session = AuthenticationState.Demo)

    private fun show() {
        compose.setContent { RootHost(state = AuthenticationState.Demo, source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo), links = links) }
        compose.waitForIdle()
    }

    /** Sort & filter (SheetPage, Done alone): Done before the first status row. */
    @Test
    fun filtersDoneIsReadBeforeItsForm() {
        show()
        compose.onNodeWithTag(FILTERS_BUTTON).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(FILTERS_SHEET).assertExists()
        val stops = stops()
        val labels = stops.map { it.first }
        val done = stops.indexOfFirst { it.second == FILTERS_DONE }
        val firstRow = stops.indexOfFirst { it.second?.startsWith(FILTERS_STATUS) == true }
        assertTrue("Done and a status row are stops: $labels", done >= 0 && firstRow >= 0)
        assertTrue("TalkBack reads Sort & filter's rows (first at $firstRow) before Done ($done): $labels", done < firstRow)
    }

    /** A parcel's link (LinkSheetPage, Close alone) reads Close before the page under the bar. */
    @Test
    fun linksCloseIsReadBeforeItsPage() {
        show()
        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(LINK_SHEET).assertExists()
        val stops = stops()
        val labels = stops.map { it.first }
        val close = stops.indexOfFirst { it.second == LINK_CLOSE }
        val number = labels.indexOfFirst { it.startsWith("Tracking number") }
        assertTrue("Close ($close) and the tracking number ($number) are stops: $stops", close >= 0 && number >= 0)
        assertTrue("TalkBack reads the link's page (its tracking number at $number) before its Close ($close): $labels", close < number)
    }

    /** The friends' profile panel (PanelPage, Close alone): Close before the nickname field. */
    @Test
    fun friendsPanelsCloseIsReadBeforeItsForm() {
        show()
        compose.onNode(hasContentDescription("Friends") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(FRIENDS_SHARING).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(FRIENDS_PANEL).assertExists()
        val stops = stops()
        val labels = stops.map { it.first }
        val close = stops.indexOfFirst { it.second == FRIENDS_CLOSE }
        val nickname = stops.indexOfFirst { it.second == FRIENDS_NICKNAME }
        assertTrue("Close ($close) and the nickname ($nickname) are stops: $stops", close >= 0 && nickname >= 0)
        assertTrue("TalkBack reads the profile's form (its nickname at $nickname) before Close ($close): $labels", close < nickname)
    }

    /** What TalkBack stops on, in order, with each stop's test tag, as TalkBackOrderTest reads it. */
    private fun stops(): List<Pair<String, String?>> {
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
        val composeView = compose.activity.window.decorView.composeView()
        val delegate = composeView.javaClass.getDeclaredField("composeAccessibilityDelegate").apply { isAccessible = true }.get(composeView)
        val forced = delegate.javaClass.getDeclaredField("accessibilityForceEnabledForTesting").apply { isAccessible = true }
        val before = mutableMapOf<Int, Int>()
        forced.setBoolean(delegate, true)
        try {
            for (node in all) {
                val info = composeView.accessibilityNodeProvider.createAccessibilityNodeInfo(if (node.id == root.id) View.NO_ID else node.id) ?: continue
                val next = info.extras.getInt(TRAVERSAL_BEFORE, -1)
                if (next != -1) before[node.id] = next
            }
        } finally {
            forced.setBoolean(delegate, false)
        }
        val first = before.keys.single { it !in before.values }
        val stops = mutableListOf<Pair<String, String?>>()
        var id: Int? = first
        val seen = mutableSetOf<Int>()
        while (id != null && seen.add(id)) {
            val config = merged[id]?.config
            if (config != null && !config.contains(SemanticsProperties.HideFromAccessibility)) {
                val label = (config.getOrNull(SemanticsProperties.ContentDescription) ?: config.getOrNull(SemanticsProperties.Text)?.map { it.text })
                    ?.joinToString(", ")?.takeIf { it.isNotBlank() }
                val actionable = config.contains(SemanticsActions.OnClick)
                if (label != null || actionable) stops += (label ?: "?") to config.getOrNull(SemanticsProperties.TestTag)
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

    private companion object {
        const val TRAVERSAL_BEFORE = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL"
    }
}
