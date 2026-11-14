package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.ParcelMenuItem
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.ui.RootHost
import java.time.Duration
import java.util.UUID
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Each bar is read before what scrolls under it (readFirst) on the list, the tabs, a sheet's inline bar and a form
 * sheet's bar ([com.plhery.peekdeliverytracker.ui.sheet.FormBar]: Cancel, the title, Save, drawn over a form that starts
 * at the sheet's top), as VoiceOver reads a navigation bar's items first.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class FormBarOrderTest {
    private val compose = createComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var deliveries: DeliveriesState
    private lateinit var view: View

    /** A synthetic parcel filed under automatic detection. */
    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000000601"), trackingNumber = "TESTLAMP0001", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun showList() {
        val source = FakeDeliveries(DemoCards.parcels() + lamp, AuthenticationState.Demo)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize()) { RootHost(state = AuthenticationState.Demo, source = source, identities = pinnedIdentities) }
            }
        }
        compose.waitForIdle()
    }

    /** The Change carrier sheet (a SheetPage with a FormBar): Cancel, the title and Save come before the form. */
    @Test
    fun changeCarriersBarIsReadBeforeItsForm() {
        showList()
        compose.runOnUiThread { deliveries.chose(ParcelMenuItem.ChangeCarrier, lamp) }
        compose.waitForIdle()
        val stops = stops()
        val cancel = stops.indexOfFirst { it.second == CHANGE_CARRIER_CANCEL }
        val save = stops.indexOfFirst { it.second == CHANGE_CARRIER_SAVE }
        val number = stops.indexOfFirst { it.second == CHANGE_CARRIER_NUMBER }
        val carrier = stops.indexOfFirst { it.second == CHANGE_CARRIER_ROW }
        val labels = stops.map { it.first }
        assertTrue("Cancel, Save, the number and the carrier's row are stops: $labels", cancel >= 0 && save >= 0 && number >= 0 && carrier >= 0)
        assertTrue(
            "TalkBack reads Change carrier's form (its number at $number, its carrier at $carrier) before the bar's " +
                "Cancel ($cancel) and Save ($save), where VoiceOver reads the navigation bar first: $labels",
            cancel < number && save < number && cancel < carrier && save < carrier,
        )
    }

    /** The Add sheet's inline bar comes before its form. */
    @Test
    fun addsInlineBarIsReadBeforeItsForm() {
        showList()
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        compose.waitForIdle()
        val labels = stops().map { it.first }
        val close = labels.indexOf("Close")
        val field = labels.indexOfFirst { it.startsWith("Tracking number or link") }
        assertTrue("Add's Close before its field: $labels", close in 0 until field)
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
        val composeView = view.rootView.composeView()
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
