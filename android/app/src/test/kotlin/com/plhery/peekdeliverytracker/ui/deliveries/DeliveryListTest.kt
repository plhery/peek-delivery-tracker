package com.plhery.peekdeliverytracker.ui.deliveries

import android.app.Application
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.view.KeyEvent
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.core.api.CarrierData
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.PeekTypography
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The deliveries' list on a tablet whose window a test narrows and widens: the arrangements and the turns between
 * them, the parcel chosen beside the list, back, the keys and the focus, a link's parcel, the demo's bar, the foot,
 * what is counted, and what is drawn again and when.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w1032dp-h1376dp-xhdpi")
class DeliveryListTest {
    @get:Rule
    val compose = createComposeRule()

    private var width by mutableStateOf(1032.dp)
    private lateinit var view: View
    private lateinit var back: OnBackPressedDispatcher
    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val views = mutableListOf<String>()
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11)))

    /** "On the way" as last laid out. */
    private var heading: TextLayoutResult? = null

    /**
     * The deliveries in a window `width` wide, the demo's parcels in `session` unless a test gives its own, at
     * `fontScale`, each card drawn told to `cardComposed`.
     */
    private fun show(
        width: Dp = 1032.dp,
        session: AuthenticationState = AuthenticationState.Demo,
        inbox: DeliveriesInbox? = null,
        recreation: Recreation? = null,
        fontScale: Float = 1f,
        cardComposed: ((UUID) -> Unit)? = null,
    ) {
        this.width = width
        source = FakeDeliveries(DemoCards.parcels(), session)
        val content = @androidx.compose.runtime.Composable {
            view = LocalView.current
            back = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalDensity provides Density(density.density, fontScale), LocalCardComposed provides cardComposed,
                LocalOnTheWayHeading provides { heading = it }, LocalDeliveriesState provides { deliveries = it },
            ) {
                Box(Modifier.width(this.width).fillMaxHeight()) {
                    RootHost(state = session, onView = { views += it }, source = source, inbox = inbox)
                }
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun resize(to: Dp) {
        width = to
        compose.waitForIdle()
    }

    /** The next frame, while the clock doesn't run by itself. */
    private fun frame() {
        compose.runOnUiThread { Snapshot.sendApplyNotifications() }
        compose.mainClock.advanceTimeByFrame()
    }

    private fun sideBySide(): Boolean = compose.onAllNodes(hasTestTag(PAGE_COLUMN)).fetchSemanticsNodes().isNotEmpty()

    /** The parcel's page shows the parcel named `name`. */
    private fun assertPageShows(name: String) {
        compose.onNode(hasText(name, substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).assertExists()
    }

    /** No page, nor one on its way back into its card, which TalkBack no longer reaches. */
    private fun assertNoPage() {
        compose.onNodeWithTag(PARCEL_PAGE, useUnmergedTree = true).assertDoesNotExist()
    }

    /** A key pressed and let go on a hardware keyboard, through the window as the system sends it. */
    private fun press(key: Int) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, 0))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, 0))
        }
        compose.waitForIdle()
    }

    private fun focused(tag: String): Boolean = compose.onNodeWithTag(tag).fetchSemanticsNode().config.getOrNull(SemanticsProperties.Focused) == true

    /** Tab pressed until the control tagged `tag` has the focus. */
    private fun tabTo(tag: String) {
        repeat(6) {
            if (focused(tag)) return
            press(KeyEvent.KEYCODE_TAB)
        }
        assertTrue("$tag never took the focus", focused(tag))
    }

    /** What holds the focus without being a control: a column that takes the keys. */
    private fun focusTargets(): List<SemanticsNode> = compose.onAllNodes(
        SemanticsMatcher.keyIsDefined(SemanticsProperties.Focused) and SemanticsMatcher.keyNotDefined(SemanticsActions.OnClick),
        useUnmergedTree = true,
    ).fetchSemanticsNodes()

    /** Whether the window has work waiting for a frame, such as an animation running. */
    private fun waitsForAFrame(): Boolean {
        var waits = false
        compose.runOnUiThread { waits = Recomposer.runningRecomposers.value.any { it.hasPendingWork } }
        return waits
    }

    @Test
    fun theWindowTurnsSideBySidePastTheLineAndBackWithSomeSlack() {
        // The list and the parcel need 320 + 390; the window goes 20 past that before it turns either way.
        show(width = 700.dp)
        assertEquals(false, sideBySide())
        assertNoPage()
        resize(729.dp)
        assertEquals(false, sideBySide())

        resize(730.dp)
        assertEquals(true, sideBySide())
        // Nobody chose a parcel: the first card's shows beside the list, ringed.
        assertPageShows("New trainers")
        compose.card("New trainers").assertIsSelected()
        compose.card("Birthday gift").assertIsNotSelected()
        resize(700.dp)
        assertEquals(true, sideBySide())

        resize(689.dp)
        assertEquals(false, sideBySide())
        // The parcel nobody chose goes back into the list.
        assertNoPage()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        // Showing a parcel nobody chose is no screen.
        assertEquals(emptyList<String>(), views)
    }

    /** How far the list is scrolled, in pixels. */
    private fun scrolled(): Float =
        compose.onNodeWithTag(PARCEL_LIST).fetchSemanticsNode().config[SemanticsProperties.VerticalScrollAxisRange].value()

    @Test
    fun turningKeepsTheListAtItsTopOrTheCardAtItsTopThere() {
        show(width = 700.dp)
        resize(730.dp)
        // At its top, the list stays there rather than hide the overview under the bar for the first card.
        assertEquals(0f, scrolled(), 0f)

        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey(cardKey("remaining", DemoCards.parcel("Trail weekend kit").id))
        compose.waitForIdle()
        val top = compose.card("Trail weekend kit").getBoundsInRoot().top.value
        resize(689.dp)
        // The card at the list's top comes back there in the list laid out anew.
        assertEquals(top, compose.card("Trail weekend kit").getBoundsInRoot().top.value, 1f)
        assertEquals(true, scrolled() > 0f)
    }

    @Test
    fun aParcelChosenBesideTheListComesOverItAsTheWindowNarrows() {
        show()
        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        compose.card("New trainers").assertIsNotSelected()
        assertEquals(listOf("parcel"), views)

        resize(600.dp)
        assertEquals(false, sideBySide())
        assertPageShows("Birthday gift")
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()

        resize(1032.dp)
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        // The same parcel all along: counted once.
        assertEquals(listOf("parcel"), views)
    }

    /**
     * Gone from the deliveries elsewhere, a parcel shown by itself gives way to the first card, and one someone chose
     * stays to say it is gone, as the iPhone's `settle` has them; a new carrier, under a new id, keeps it shown.
     */
    @Test
    fun aParcelShownByItselfBesideTheListGivesWayAsItGoesAndOneChosenStays() {
        show()
        assertPageShows("New trainers")
        source.parcels.value = source.parcels.value.filterNot { it.label.startsWith("New trainers") }
        compose.waitForIdle()
        // Next up now, the first card.
        assertPageShows("Belgian chocolate")
        compose.card("Belgian chocolate").assertIsSelected()

        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        source.parcels.value = source.parcels.value.map {
            if (!it.label.startsWith("Birthday gift")) it
            else it.copy(id = UUID.randomUUID(), carrierData = (it.carrierData ?: CarrierData()).copy(originalPackageId = it.id))
        }
        compose.waitForIdle()
        assertPageShows("Birthday gift")
        source.parcels.value = source.parcels.value.filterNot { it.label.startsWith("Birthday gift") }
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_MISSING).assertExists()
        compose.onNode(hasText("Belgian chocolate", substring = true) and hasAnyAncestor(hasTestTag(PARCEL_PAGE))).assertDoesNotExist()
    }

    @Test
    fun upAndDownStepThroughTheCardsBesideTheListAndReturnChoosesTheOneShown() {
        show()
        assertPageShows("New trainers")
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Birthday gift")
        compose.card("Birthday gift").assertIsSelected()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Belgian chocolate")
        press(KeyEvent.KEYCODE_DPAD_UP)
        assertPageShows("Birthday gift")
        assertEquals(listOf("parcel", "parcel", "parcel"), views)
        // Past the first card ↑ does nothing.
        press(KeyEvent.KEYCODE_DPAD_UP)
        press(KeyEvent.KEYCODE_DPAD_UP)
        assertPageShows("New trainers")
        assertEquals(4, views.size)
    }

    @Test
    fun backRightAfterTurningStackedGoesBackToTheList() {
        show()
        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        resize(600.dp)
        assertPageShows("Birthday gift")

        compose.runOnUiThread { back.onBackPressed() }
        compose.waitForIdle()
        assertNoPage()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertEquals(listOf("parcel", "deliveries"), views)
    }

    @Test
    fun besideAParcelBackIsNotTheDeliveries() {
        // As the iPhone's split view: the list's column has no back, and the system's leaves the deliveries rather
        // than put the parcel shown by itself away.
        show()
        assertPageShows("New trainers")
        assertFalse(back.hasEnabledCallbacks())
        compose.card("Birthday gift").performClick()
        compose.waitForIdle()
        assertFalse(back.hasEnabledCallbacks())
    }

    @Test
    fun theParcelShowsBesideTheListFromTheFirstFrame() {
        compose.mainClock.autoAdvance = false
        show()
        assertPageShows("New trainers")

        width = 600.dp
        frame()
        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(false, sideBySide())
        width = 1032.dp
        // From the first frame the column beside the list shows, it shows the parcel.
        repeat(10) {
            if (sideBySide()) return@repeat
            frame()
        }
        assertPageShows("New trainers")
    }

    /**
     * A-AX04: at the accessibility sizes "On the way" keeps to its one line, whole, a little smaller where it must
     * (`.lineLimit(1).minimumScaleFactor(0.75)`).
     */
    @Test
    @Config(qualifiers = "w320dp-h640dp-xhdpi")
    fun atTheAccessibilitySizesOnTheWayShrinksRatherThanBeCutOff() {
        show(width = 320.dp, fontScale = 2f)
        // Where it fits, at its size.
        val full = PeekTypography(DynamicTypeSize.of(2f), Density(compose.density.density, 2f)).headline.fontSize.value
        assertEquals(full, checkNotNull(heading).layoutInput.style.fontSize.value, 0.001f)
        // Narrower, as a window beside another: too narrow for the heading at its size, wide enough at 0.75 of it.
        resize(250.dp)
        val layout = checkNotNull(heading)
        assertEquals("Whole", layout.layoutInput.text.length, layout.getLineEnd(0))
        assertFalse(layout.isLineEllipsized(0))
        assertFalse("Clipped: ${layout.multiParagraph.width} in ${layout.size.width}", layout.hasVisualOverflow)
        val size = layout.layoutInput.style.fontSize.value
        assertTrue("$size of $full", size < full && size >= full * 0.75f - 0.01f)
    }

    @Test
    fun atTheLargestTextTheBarsTitleStaysAtTheDefaultSize() {
        show(width = 600.dp, fontScale = 2f)
        val layouts = mutableListOf<TextLayoutResult>()
        compose.onNode(isHeading() and hasText("Deliveries")).fetchSemanticsNode().config[SemanticsActions.GetTextLayoutResult].action!!.invoke(layouts)
        val layout = layouts.single()
        assertFalse(layout.isLineEllipsized(0))
        // Headline's 17 points, whatever the reader's size, as UIKit's inline title.
        assertEquals(17f, with(layout.layoutInput.density) { layout.layoutInput.style.fontSize.toDp().value }, 0.01f)
    }

    @Test
    fun theListStartsUnderTheDemosBarFromTheFirstFrameAtLargeText() {
        compose.mainClock.autoAdvance = false
        show(width = 600.dp, fontScale = 2f)
        val bar = compose.onNodeWithTag(DEMO_EXIT).getBoundsInRoot()
        // Taller than at the usual sizes, which the list would start under before the bar was measured.
        assertTrue(bar.bottom - bar.top > DEMO_BAR_HEIGHT)
        val top = compose.onNodeWithTag(SEARCH_BUTTON).getBoundsInRoot().top
        assertTrue(top >= bar.bottom)

        compose.mainClock.advanceTimeBy(1_000)
        assertEquals(top, compose.onNodeWithTag(SEARCH_BUTTON).getBoundsInRoot().top)
    }

    @Test
    fun theDemosBarStaysOverAParcelOpenedOverTheList() {
        show(width = 600.dp)
        compose.openCard("New trainers")
        compose.onNodeWithTag(PARCEL_LIST).assertDoesNotExist()
        // The page starts under it.
        val bar = compose.onNodeWithTag(DEMO_EXIT).getBoundsInRoot()
        assertEquals(bar.bottom.value, compose.onNodeWithTag(PARCEL_PAGE).getBoundsInRoot().top.value, 0.5f)
        compose.onNodeWithTag(DEMO_EXIT).performClick()
        compose.waitForIdle()
        assertEquals(1, source.welcomes)

        // Beside the list, it stays over the list alone.
        resize(1032.dp)
        assertPageShows("New trainers")
        compose.onNode(hasTestTag(DEMO_EXIT) and hasAnyAncestor(hasTestTag(PAGE_COLUMN))).assertDoesNotExist()
        assertEquals(1, compose.onAllNodes(hasTestTag(DEMO_EXIT)).fetchSemanticsNodes().size)
    }

    @Test
    fun returnPressesTheButtonOrOpensTheCardThatHasTheFocus() {
        show(session = AuthenticationState.Welcome)
        assertPageShows("New trainers")
        tabTo(SEARCH_BUTTON)
        press(KeyEvent.KEYCODE_ENTER)
        compose.onNodeWithTag(SEARCH_ROW).assertExists()
        // The field takes the focus, and Return there ends the typing, as Escape closes the search.
        assertTrue(focused(SEARCH_FIELD))
        press(KeyEvent.KEYCODE_ENTER)
        assertFalse(focused(SEARCH_FIELD))
        compose.onNodeWithTag(SEARCH_ROW).assertExists()
        press(KeyEvent.KEYCODE_ESCAPE)
        compose.onNodeWithTag(SEARCH_ROW).assertDoesNotExist()
        assertEquals(emptyList<String>(), views)

        // Past the refresh button, the first card and then the second.
        tabTo(REFRESH_BUTTON)
        press(KeyEvent.KEYCODE_TAB)
        press(KeyEvent.KEYCODE_TAB)
        press(KeyEvent.KEYCODE_ENTER)
        assertPageShows("Birthday gift")
        assertEquals(listOf("parcel"), views)

        // A card with the focus, ↓ still steps through the cards.
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Belgian chocolate")
    }

    @Test
    fun onlyTheListBesideAParcelTakesTheFocusForItsKeys() {
        show(width = 600.dp)
        assertEquals(emptyList<SemanticsNode>(), focusTargets())

        resize(1032.dp)
        val targets = focusTargets()
        assertEquals(1, targets.size)
        assertEquals(LIST_COLUMN, targets.single().config[SemanticsProperties.TestTag])
        assertEquals(true, targets.single().config[SemanticsProperties.Focused])
    }

    @Test
    fun theRefreshButtonAsksForFramesOnlyWhileTheParcelsAreChecked() {
        compose.mainClock.autoAdvance = false
        // The list alone, its button there for TalkBack: beside it, the page's map and Pip ask for frames of their own.
        show(width = 600.dp)
        val manager = shadowOf(view.context.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread { manager.setTouchExplorationEnabled(true) }
        compose.mainClock.advanceTimeBy(5_000)
        compose.onNodeWithTag(REFRESH_BUTTON).assertExists()
        assertFalse(waitsForAFrame())

        source.refreshing.value = true
        compose.mainClock.advanceTimeBy(1_000)
        assertTrue(waitsForAFrame())

        source.refreshing.value = false
        compose.mainClock.advanceTimeBy(1_000)
        assertFalse(waitsForAFrame())
    }

    @Test
    fun aStepOrAChangedParcelDrawsAgainOnlyTheCardsItChanges() {
        val composed = mutableListOf<UUID>()
        show(cardComposed = { composed += it })
        compose.card("Belgian chocolate").assertExists()
        composed.clear()

        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertPageShows("Birthday gift")
        assertEquals(setOf(DemoCards.parcel("New trainers").id, DemoCards.parcel("Birthday gift").id), composed.toSet())

        composed.clear()
        val chocolate = DemoCards.parcel("Belgian chocolate").id
        source.parcels.value = source.parcels.value.map { if (it.id == chocolate) it.copy(label = "Belgian chocolate, dark") else it }
        compose.waitForIdle()
        compose.card("Belgian chocolate, dark").assertExists()
        assertEquals(setOf(chocolate), composed.toSet())
    }

    @Test
    fun theOpenParcelAndTheArchiveComeBackAfterTheWindowIsMadeAgainCountedOnce() {
        val recreation = Recreation(compose)
        show(width = 600.dp, recreation = recreation)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNodeWithContentDescription("Archived", substring = true).performClick()
        compose.waitForIdle()
        compose.openCard("Moon lamp")
        assertPageShows("Moon lamp")
        assertEquals(listOf("parcel"), views)
        assertEquals(listOf("archive-open"), source.actions)

        recreation.recreate()
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertPageShows("Moon lamp")
        assertEquals(listOf("parcel"), views)

        // Back on the list, the archive is still open.
        compose.runOnUiThread { back.onBackPressed() }
        compose.waitForIdle()
        assertNoPage()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToNode(cardNamed("Linen weekend bag"))
        assertEquals(listOf("parcel", "deliveries"), views)
        assertEquals(listOf("archive-open"), source.actions)
    }

    @Test
    fun aParcelsLinkOpensItOnTheDeliveries() {
        val inbox = DeliveriesInbox()
        show(width = 600.dp, inbox = inbox)
        compose.onNodeWithContentDescription("Friends").performClick()
        compose.waitForIdle()
        views.clear()

        compose.runOnUiThread { inbox.open(DemoCards.parcel("Kind of Blue").id) }
        compose.waitForIdle()
        // Under the page, which hides the tab bar.
        compose.onNode(hasContentDescription("Deliveries") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab), useUnmergedTree = true)
            .assertIsSelected()
        assertPageShows("Kind of Blue")
        assertEquals(null, inbox.parcel.value)
        assertEquals(listOf("deliveries", "parcel"), views)
    }

    @Test
    fun exitingTheDemoShowsTheWelcome() {
        show(width = 600.dp)
        compose.onNodeWithTag(DEMO_EXIT).performClick()
        compose.waitForIdle()
        assertEquals(1, source.welcomes)
    }

    @Test
    fun besideAParcelTheRefreshButtonRefreshes() {
        show()
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        compose.waitForIdle()
        assertEquals(1, source.refreshes)
        resize(600.dp)
        compose.onNodeWithTag(REFRESH_BUTTON).assertDoesNotExist()
    }

    /**
     * A-AX10: on a phone, the button shows as TalkBack is turned on, as the iPhone's does for VoiceOver, labelled
     * "Refresh tracking", and goes as it is turned off.
     */
    @Test
    @Config(qualifiers = PHONE)
    fun onAPhoneTheRefreshButtonShowsForTalkBack() {
        show(width = 390.dp)
        compose.onNodeWithTag(REFRESH_BUTTON).assertDoesNotExist()
        val manager = shadowOf(view.context.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread { manager.setTouchExplorationEnabled(true) }
        compose.waitForIdle()
        compose.onNodeWithTag(REFRESH_BUTTON).assertContentDescriptionEquals("Refresh tracking").performClick()
        compose.waitForIdle()
        assertEquals(1, source.refreshes)
        compose.runOnUiThread { manager.setTouchExplorationEnabled(false) }
        compose.waitForIdle()
        compose.onNodeWithTag(REFRESH_BUTTON).assertDoesNotExist()
    }

    @Test
    fun theSearchButtonShowsAndHidesTheSearch() {
        show(width = 600.dp)
        compose.onNodeWithTag(SEARCH_ROW).assertDoesNotExist()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_ROW).assertExists()
        compose.onNodeWithTag(SEARCH_BUTTON).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(SEARCH_ROW).assertDoesNotExist()
    }

    /** Where no browser offers Custom Tabs (none here), the foot's links open as the app's other links do. */
    @Test
    fun theFootsLinksOpenInTheBrowser() {
        show(width = 600.dp)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.onNodeWithTag("$FOOT.privacy").performClick()
        compose.waitForIdle()
        val started = shadowOf(ApplicationProvider.getApplicationContext<Application>()).nextStartedActivity
        assertEquals(Intent.ACTION_VIEW, started.action)
        assertEquals("https://example.com/privacy", started.dataString)
        assertFalse("No Custom Tab", started.hasExtra(CustomTabsIntent.EXTRA_SESSION))
    }

    /**
     * A-S23: the foot's links open inside the app, as the list's `SafariPage` sheet: a Custom Tab of the phone's
     * default browser (a made-up one the package manager lists), in the app's appearance.
     */
    @Test
    fun theFootsLinksOpenInACustomTabInTheAppsAppearance() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val packages = shadowOf(app.packageManager)
        val page = ComponentName(BROWSER, "$BROWSER.Page")
        packages.addActivityIfNotPresent(page)
        packages.addIntentFilterForActivity(
            page,
            IntentFilter(Intent.ACTION_VIEW).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                addCategory(Intent.CATEGORY_BROWSABLE)
                addDataScheme("http")
                addDataScheme("https")
            },
        )
        val tabs = ComponentName(BROWSER, "$BROWSER.Tabs")
        packages.addServiceIfNotPresent(tabs)
        packages.addIntentFilterForService(tabs, IntentFilter(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION))
        val appearance = AppearanceStore.of(app)
        try {
            appearance.set(AppAppearance.Dark)
            show(width = 600.dp)
            compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
            for ((id, url) in listOf("privacy" to "https://example.com/privacy", "source" to AppConfiguration.sourceUrl)) {
                compose.onNodeWithTag("$FOOT.$id").performClick()
                compose.waitForIdle()
                val started = shadowOf(app).nextStartedActivity
                assertEquals(Intent.ACTION_VIEW, started.action)
                assertEquals(url, started.dataString)
                assertTrue("A Custom Tab", started.hasExtra(CustomTabsIntent.EXTRA_SESSION))
                assertEquals(BROWSER, started.`package`)
                assertEquals(CustomTabsIntent.COLOR_SCHEME_DARK, started.getIntExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, -1))
            }
        } finally {
            appearance.set(AppAppearance.DEFAULT)
        }
    }

    /** Whether the list's notice ([LIST_NOTICE]) says `text`. */
    private fun noticeSays(text: String): Boolean =
        compose.onAllNodes(hasText(text) and hasAnyAncestor(hasTestTag(LIST_NOTICE))).fetchSemanticsNodes().isNotEmpty()

    /**
     * A-ST11: the store's error shows under the list's chips (ParcelListView's `NoticeBanner`): the service out of reach,
     * why, and that the parcels kept on the phone still show; Try again asks for the parcels again. An expired sign-in
     * asks to sign in again, which loads again too. It goes with the error.
     */
    @Test
    fun theStoresErrorSaysWhatWentWrongAndItsButtonLoadsAgain() {
        show(width = 600.dp, session = reader)
        compose.onNodeWithTag(LIST_NOTICE).assertDoesNotExist()
        val offline = localizer.text("error.connection")
        compose.runOnUiThread {
            source.errorMessage.value = offline
            source.usingCachedData.value = true
        }
        compose.waitForIdle()
        assertTrue(noticeSays(localizer.text("app.trackingBreak")))
        assertTrue(noticeSays("$offline ${localizer.text("app.cachedData")}"))
        // Over the cards.
        val next = checkNotNull(deliveries.layout.next)
        assertTrue(compose.onNodeWithTag(LIST_NOTICE).getBoundsInRoot().bottom < compose.card(next.label).getBoundsInRoot().top)
        compose.onNode(hasText(localizer.text("app.tryAgain")) and hasAnyAncestor(hasTestTag(LIST_NOTICE))).assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)).performClick()
        compose.waitForIdle()
        assertEquals(1, source.reloads)

        val expired = localizer.text("native.error.authenticationExpired")
        compose.runOnUiThread {
            source.errorMessage.value = expired
            source.authenticationRequired.value = true
            source.usingCachedData.value = false
        }
        compose.waitForIdle()
        assertTrue(noticeSays(localizer.text("app.signInNeeded")))
        assertTrue(noticeSays(expired))
        assertFalse(noticeSays(localizer.text("app.trackingBreak")))
        compose.onNode(hasText(localizer.text("app.signInAgain")) and hasAnyAncestor(hasTestTag(LIST_NOTICE))).performClick()
        compose.waitForIdle()
        assertEquals(2, source.reloads)

        compose.runOnUiThread { source.errorMessage.value = null }
        compose.waitForIdle()
        compose.onNodeWithTag(LIST_NOTICE).assertDoesNotExist()
    }

    /**
     * A-ST15: a guest's offer of an account stands 24 under the last card on its way, outside the first group, and its
     * button at least 32 from its words (`HStack(spacing: 12)` round a `Spacer(minLength: 8)`), here where they wrap.
     */
    @Test
    @Config(qualifiers = "w320dp-h640dp-xhdpi")
    fun aGuestsOfferOfAnAccountStands24UnderTheCardsAndItsButton32FromItsWords() {
        show(width = 320.dp, session = AuthenticationState.Welcome)
        val list = compose.onNodeWithTag(PARCEL_LIST)
        list.performScrollToNode(hasTestTag(DEVICE_ACCOUNT_ROW))
        compose.waitForIdle()
        val last = deliveries.layout.remaining.last()
        val row = compose.onNodeWithTag(DEVICE_ACCOUNT_ROW).getBoundsInRoot()
        assertEquals(24f, (row.top - compose.card(last.label).getBoundsInRoot().bottom).value, 0.5f)

        fun bounds(key: String) = compose.onNode(hasText(localizer.text(key)), useUnmergedTree = true).getBoundsInRoot()
        val words = maxOf(bounds("link.account.title").right, bounds("native.guest.kept").right)
        // The capsule's left, 12 before its word.
        val capsule = bounds("arrival.signInTitle").left - 12.dp
        assertTrue("${capsule - words}", capsule - words >= 31.5.dp)
        // The words wrap, filling what the row leaves them.
        assertTrue(bounds("link.account.title").let { it.bottom - it.top } > 25.dp)
    }

    // A turn draws the list anew (`key(arrangement)`): what it keeps, and what goes with it.

    private fun top(name: String): Float = compose.card(name).getBoundsInRoot().top.value

    private fun toastShown(text: String): Boolean =
        compose.onAllNodes(hasTestTag(INLINE_TOAST) and hasContentDescription(text, substring = true)).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun aTurnClosesACardsMenuWithItsCardAndLeavesTheListReachable() {
        show()
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
        compose.onNodeWithTag(CARD_MENU).assertExists()
        resize(600.dp)
        compose.onNodeWithTag(CARD_MENU).assertDoesNotExist()
        compose.card("Birthday gift").assertExists()
        compose.onNodeWithContentDescription("Friends").assertExists()
        // The card drawn anew opens a menu of its own.
        compose.card("Birthday gift").performTouchInput { longClick() }
        compose.waitForIdle()
        compose.onNodeWithTag(CARD_MENU).assertExists()
    }

    @Test
    fun aTurnKeepsThePullsGapOpenUntilTheCheckIsOver() {
        show(width = 600.dp)
        val narrow = top("New trainers")
        resize(1032.dp)
        val wide = top("New trainers")
        resize(600.dp)
        val checked = CompletableDeferred<ParcelStore.RefreshResult?>()
        source.checking = checked
        compose.onNodeWithTag(PARCEL_LIST).performTouchInput {
            down(Offset(width / 2f, 200.dp.toPx()))
            repeat(30) { moveBy(Offset(0f, 700.dp.toPx() / 30)) }
            advanceEventTime(200)
            up()
        }
        compose.waitForIdle()
        assertEquals(1, source.refreshes)
        assertEquals(narrow + 60f, top("New trainers"), 1f)

        // Turned while checking, the gap stays open over the list drawn anew, and closes once the check is over.
        resize(1032.dp)
        assertEquals(wide + 60f, top("New trainers"), 1f)
        checked.complete(ParcelStore.RefreshResult.Updated)
        compose.waitForIdle()
        assertEquals(wide, top("New trainers"), 0.5f)
        assertEquals(1, source.refreshes)
    }

    @Test
    fun aTurnKeepsAMessageAndWhatIsLeftOfItsFourSeconds() {
        show()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(REFRESH_BUTTON).performClick()
        compose.mainClock.advanceTimeBy(100)
        assertTrue(toastShown("No new updates"))
        compose.mainClock.advanceTimeBy(3000)
        resize(600.dp)
        compose.mainClock.advanceTimeBy(500)
        assertTrue(toastShown("No new updates"))
        compose.mainClock.advanceTimeBy(500)
        assertFalse(toastShown("No new updates"))
    }
}

/** A made-up browser's package. */
private const val BROWSER = "com.example.browser"
