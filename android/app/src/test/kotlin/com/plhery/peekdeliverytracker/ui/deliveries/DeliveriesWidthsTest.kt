package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Density
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.core.currentStage
import com.plhery.peekdeliverytracker.core.isActive
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.passport.PASSPORT_COVER
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * iOS's `testTheDeliveriesScreensDrawInEveryWidthTheyAreGiven` (C-T135): each of the deliveries' screens over the demo,
 * in a window of the size a phone, a tablet's panes, its narrow windows and its whole screen give it, at Large in light
 * and at the largest text Android has in dark, asks for no more width than the window has: nothing on it reaches past
 * its edges by more than half a point, but what scrolls sideways or stands off the edge on purpose. Android's largest
 * text is font scale 2.0, which reads as accessibility2; iOS's accessibility3 has no Android match.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class DeliveriesWidthsTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private var large by mutableStateOf(false)
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    /**
     * The deliveries of [session] on [parcels] in [language], at Large in light, then again at the largest text in dark,
     * once [open] has gone where the screen tagged [shows] is.
     */
    private fun draw(
        name: String,
        shows: String,
        language: AppLanguage = AppLanguage.En,
        session: AuthenticationState = AuthenticationState.Demo,
        parcels: List<Parcel> = DemoCards.parcels(language),
        open: (FakeDeliveries) -> Unit = {},
    ) {
        source = FakeDeliveries(parcels, session).apply { tracksWithoutAccount = true }
        compose.waitsForMaps()
        compose.setContent {
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }, LocalDensity provides Density(density.density, if (large) 2f else 1f)) {
                RootHost(state = session, language = language, dark = large, source = source)
            }
        }
        settle()
        open(source)
        settle()
        for (largeType in listOf(false, true)) {
            compose.runOnIdle { large = largeType }
            settle()
            val label = "$name-${language.rawValue}-${if (largeType) "large-dark" else "regular-light"}"
            assertTrue(label, compose.onAllNodes(hasTestTag(shows), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty())
            assertFits(label)
        }
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /**
     * `sizeThatFits(in: size).width <= size.width + 0.5`: no node of the window's reaches past its edges, where what is
     * laid out wider than its room stands, centred on it.
     */
    private fun assertFits(label: String) {
        val window = compose.activity.window.decorView
        val edge = window.width / window.resources.displayMetrics.density + 0.5f
        val over = mutableListOf<String>()
        fun walk(node: SemanticsNode) {
            // A row that scrolls sideways is laid out wider than the window on purpose.
            if (node.config.getOrNull(SemanticsProperties.HorizontalScrollAxisRange) != null) return
            // What stands off the window on purpose: a card's swipe action, off its right edge until it is swiped, and
            // the first open's rolling fleet, clipped to its lane.
            if (node.config.getOrNull(SemanticsProperties.TestTag) in OFF_THE_EDGE) return
            // As drawn, a page's zoom and the list's shrinking under it included, but unclipped.
            val laid = node.layoutInfo.coordinates
            val drawn = laid.findRootCoordinates().localBoundingBoxOf(laid, clipBounds = false)
            val left = with(compose.density) { drawn.left.toDp().value }
            val right = with(compose.density) { drawn.right.toDp().value }
            if (left < -0.5f || right > edge) {
                over += "${node.config.getOrNull(SemanticsProperties.TestTag) ?: node.config.getOrNull(SemanticsProperties.Text) ?: node.id} from $left to $right"
            }
            node.children.forEach(::walk)
        }
        compose.onAllNodes(isRoot(), useUnmergedTree = true).fetchSemanticsNodes().forEach(::walk)
        assertTrue("$label, $edge wide: $over", over.isEmpty())
    }

    private fun openParcel(where: (Parcel) -> Boolean): (FakeDeliveries) -> Unit = { source ->
        val parcel = source.parcels.value.first(where)
        compose.runOnUiThread { deliveries.show(parcel.id) }
    }

    private val active: (Parcel) -> Boolean = { it.isActive }
    private val waitingAtPickup: (Parcel) -> Boolean = { it.currentStage == TrackingStage.ReadyForPickup }

    @Test
    @Config(qualifiers = "w320dp-h700dp-xhdpi")
    fun theListIn320() = draw("list", PARCEL_LIST)

    @Test
    @Config(qualifiers = "w380dp-h800dp-xhdpi")
    fun theListIn380() = draw("list", PARCEL_LIST)

    @Test
    @Config(qualifiers = "w330dp-h700dp-xhdpi")
    fun theListIn330InGerman() = draw("list", PARCEL_LIST, AppLanguage.De)

    @Test
    @Config(qualifiers = "w390dp-h844dp-xhdpi")
    fun aPageIn390() = draw("detail", PAGE_CONTENT, open = openParcel(active))

    @Test
    @Config(qualifiers = "w640dp-h900dp-xhdpi")
    fun aPageIn640() = draw("detail", PAGE_CONTENT, open = openParcel(active))

    /**
     * Beside the list, the parcel waiting at its pickup point, the fullest page, where the window has room for both
     * columns: at Large from 800 wide, at the largest text from 996; over the list otherwise, as in 652.
     */
    @Test
    @Config(qualifiers = "w652dp-h1000dp-xhdpi")
    fun aPageBesideTheListIn652() = draw("detail-beside", PAGE_PICKUP, open = openParcel(waitingAtPickup))

    @Test
    @Config(qualifiers = "w800dp-h800dp-xhdpi")
    fun aPageBesideTheListIn800() = draw("detail-beside", PAGE_PICKUP, open = openParcel(waitingAtPickup))

    @Test
    @Config(qualifiers = "w996dp-h1000dp-xhdpi")
    fun aPageBesideTheListIn996() = draw("detail-beside", PAGE_PICKUP, open = openParcel(waitingAtPickup))

    @Test
    @Config(qualifiers = "w800dp-h800dp-xhdpi")
    fun aPageBesideTheListIn800InGerman() = draw("detail-beside", PAGE_PICKUP, AppLanguage.De, open = openParcel(waitingAtPickup))

    /** Its pickup point's buttons in German, whose "Copy address" is the longest, on a phone. */
    @Test
    @Config(qualifiers = "w390dp-h844dp-xhdpi")
    fun aPickupPointIn390InGerman() = draw("detail-pickup", PAGE_PICKUP, AppLanguage.De, open = openParcel(waitingAtPickup))

    @Test
    @Config(qualifiers = "w820dp-h1180dp-xhdpi")
    fun thePassportIn820() = draw("passport", PASSPORT_COVER, open = { passport() })

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun thePassportIn1032() = draw("passport", PASSPORT_COVER, open = { passport() })

    /** A phone on its side. */
    @Test
    @Config(qualifiers = "w956dp-h440dp-land-xhdpi")
    fun thePassportOnAPhoneOnItsSide() = draw("passport-phone", PASSPORT_COVER, open = { passport() })

    @Test
    @Config(qualifiers = "w466dp-h678dp-xhdpi")
    fun theFirstOpenIn466x678() = draw("first-open", FIRST_OPEN, session = AuthenticationState.Welcome, parcels = emptyList())

    @Test
    @Config(qualifiers = "w678dp-h466dp-land-xhdpi")
    fun theFirstOpenIn678x466() = draw("first-open", FIRST_OPEN, session = AuthenticationState.Welcome, parcels = emptyList())

    @Test
    @Config(qualifiers = "w1032dp-h1376dp-xhdpi")
    fun theFirstOpenIn1032x1376() = draw("first-open", FIRST_OPEN, session = AuthenticationState.Welcome, parcels = emptyList())

    @Test
    @Config(qualifiers = "w1376dp-h1032dp-land-xhdpi")
    fun theFirstOpenIn1376x1032() = draw("first-open", FIRST_OPEN, session = AuthenticationState.Welcome, parcels = emptyList())

    private companion object {
        val OFF_THE_EDGE = setOf(ARCHIVE_ACTION, FIRST_OPEN_RIBBON)
    }

    private fun passport() {
        compose.onNode(hasContentDescription("Passport") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
    }
}
