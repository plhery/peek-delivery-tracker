package com.plhery.peekdeliverytracker.ui.tabs

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.KeyEvent
import android.view.View
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.window.layout.FoldingFeature.Orientation
import androidx.window.testing.layout.FoldingFeature
import androidx.window.testing.layout.TestWindowLayoutInfo
import androidx.window.testing.layout.WindowLayoutInfoPublisherRule
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.FOOT
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.openCard
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** The three tabs, their bar and what choosing one does. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class TabShellTest {
    private val compose = createComposeRule()
    private val fold = WindowLayoutInfoPublisherRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(fold).around(compose)

    private lateinit var view: View
    private lateinit var back: OnBackPressedDispatcher
    private val views = mutableListOf<String>()

    private fun show(language: AppLanguage = AppLanguage.En) {
        compose.setContent {
            view = LocalView.current
            back = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
            RootHost(language = language, onView = { views += it })
        }
        compose.waitForIdle()
    }

    /** A tab by its name; not the page's ‹, which reads the list's name too, as UIKit's back button does. */
    private fun tab(label: String): SemanticsNodeInteraction =
        compose.onNode(hasContentDescription(label) and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab))

    private fun bounds(tag: String): DpRect = compose.onNodeWithTag(tag).getBoundsInRoot()

    private fun window(): DpRect = compose.onRoot().getBoundsInRoot()

    @Test
    fun choosingATabCountsItsScreenAndPlaysTheSelectionHaptic() {
        show()
        // Neither as the tabs first show.
        assertEquals(emptyList<String>(), views)
        assertNotEquals(hapticFeedbackConstant(SensoryFeedback.Selection), shadowOf(view).lastHapticFeedbackPerformed())

        tab("Passport").performClick()
        compose.waitForIdle()
        tab("Passport").assertIsSelected()
        tab("Deliveries").assertIsNotSelected()
        compose.onNodeWithTag("passport").assertExists()
        assertEquals(listOf("passport"), views)
        assertEquals(hapticFeedbackConstant(SensoryFeedback.Selection), shadowOf(view).lastHapticFeedbackPerformed())

        tab("Friends").performClick()
        tab("Deliveries").performClick()
        compose.waitForIdle()
        assertEquals(listOf("passport", "friends", "deliveries"), views)
    }

    @Test
    fun overAParcelsPageTheBarHasGoneAndTappingTheChosenTabScrollsTheListToTheTop() {
        show()
        compose.openCard("New trainers")
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        // The page has the screen to itself, as the iPhone's over the list.
        tab("Deliveries").assertDoesNotExist()
        compose.onNodeWithTag(TAB_BAR).assertDoesNotExist()
        compose.runOnUiThread { back.onBackPressed() }
        compose.waitForIdle()
        tab("Deliveries").assertIsSelected()
        views.clear()

        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.waitForIdle()
        compose.onNodeWithText("On the way").assertDoesNotExist()
        tab("Deliveries").performClick()
        compose.waitForIdle()
        compose.onNodeWithText("On the way").assertExists()
        // Tapping the chosen tab is no change of tab: it counts nothing.
        assertEquals(emptyList<String>(), views)
    }

    @Test
    fun ctrlOrMetaWithADigitChoosesATab() {
        show()
        press(KeyEvent.KEYCODE_2, KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON)
        tab("Passport").assertIsSelected()
        press(KeyEvent.KEYCODE_3, KeyEvent.META_META_ON or KeyEvent.META_META_LEFT_ON)
        tab("Friends").assertIsSelected()
        press(KeyEvent.KEYCODE_1, KeyEvent.META_CTRL_ON)
        tab("Deliveries").assertIsSelected()
        // Not with another modifier, nor without one.
        press(KeyEvent.KEYCODE_2, KeyEvent.META_CTRL_ON or KeyEvent.META_SHIFT_ON)
        press(KeyEvent.KEYCODE_2, 0)
        tab("Deliveries").assertIsSelected()
        assertEquals(listOf("passport", "friends", "deliveries"), views)
    }

    /**
     * A key pressed and let go on a hardware keyboard, through the window's input stages after the keyboard's, as the
     * system delivers it: the views first, then what none of them used.
     */
    private fun press(key: Int, meta: Int) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        compose.waitForIdle()
    }

    @Test
    fun eachItemIsATabOfThreeWhoseWholeFrameTakesTheTap() {
        show()
        val capsule = bounds(TAB_BAR)
        for ((index, label) in listOf("Deliveries", "Passport", "Friends").withIndex()) {
            val node = tab(label).fetchSemanticsNode()
            assertEquals(Role.Tab, node.config.getOrNull(SemanticsProperties.Role))
            assertEquals(index == 0, node.config.getOrNull(SemanticsProperties.Selected))
            assertEquals(index, node.config.getOrNull(SemanticsProperties.CollectionItemInfo)?.columnIndex)
            assertEquals(3, node.parent?.config?.getOrNull(SemanticsProperties.CollectionInfo)?.columnCount)
            val item = tab(label).getBoundsInRoot()
            assertEquals(capsule.height.value, item.height.value, 0.5f)
            assertEquals(86f, item.width.value, 0.5f)
        }
    }

    @Test
    fun theBarOnAPhoneFloatsOverTheGestureBarAsOnTheIPhone() = assertBottomBar()

    @Test
    @Config(qualifiers = "w800dp-h360dp-xhdpi")
    fun aPhoneOnItsSideKeepsTheBarAtTheBottom() = assertBottomBar()

    @Test
    @Config(qualifiers = "w500dp-h900dp-xhdpi")
    fun aNarrowTallWindowKeepsTheBarAtTheBottom() = assertBottomBar()

    /** 48 pixels of status bar at 2×: the bar 24 + 7.5 under the window's top. */
    @Test
    @Config(qualifiers = "w840dp-h900dp-xhdpi")
    fun aTabletPutsTheBarAtTheTopUnderTheStatusBar() = assertTopBar(WindowInsetsCompat.Type.statusBars(), top = 48)

    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun anUnfoldedFoldablePutsTheBarAtTheTop() = assertTopBar()

    /** A desktop window has a caption bar and no status bar: the bar stands under the caption bar. */
    @Test
    @Config(qualifiers = "w840dp-h900dp-xhdpi")
    fun inADesktopWindowTheTopBarStandsUnderTheCaptionBar() = assertTopBar(WindowInsetsCompat.Type.captionBar(), top = 64)

    private fun assertBottomBar() {
        show()
        val capsule = bounds(TAB_BAR)
        val window = window()
        assertEquals(62f, capsule.height.value, 0.5f)
        assertEquals(window.bottom.value - 21, capsule.bottom.value, 0.5f)
        assertEquals(window.width.value / 2, (capsule.left.value + capsule.right.value) / 2, 0.5f)
    }

    /** The top bar 7.5 under `top` pixels of the `types` of insets at the window's top. */
    private fun assertTopBar(types: Int = 0, top: Int = 0) {
        var footprint = 0f
        compose.setContent {
            view = LocalView.current
            RootHost(deliveries = { footprint = LocalTabBarPadding.current.calculateTopPadding().value })
        }
        compose.waitForIdle()
        if (types != 0) insets(types, top = top)
        val inset = top / 2f
        val capsule = bounds(TAB_BAR)
        assertEquals(44f, capsule.height.value, 0.5f)
        assertEquals(inset + 7.5f, capsule.top.value, 0.5f)
        assertEquals(window().width.value / 2, (capsule.left.value + capsule.right.value) / 2, 0.5f)
        assertEquals(inset + 7.5f + 44, footprint, 0.01f)
    }

    /** With the bar at the top, the list still ends above the navigation buttons or a taskbar at the bottom. */
    @Test
    @Config(qualifiers = "w840dp-h900dp-xhdpi")
    fun underATopBarTheListEndsAboveTheNavigationButtons() {
        show()
        insets(WindowInsetsCompat.Type.tappableElement() or WindowInsetsCompat.Type.navigationBars(), bottom = 96)
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.waitForIdle()
        // The list's own 36 under its foot.
        assertEquals(window().bottom.value - 48 - 36, bounds(FOOT).bottom.value, 0.5f)
    }

    @Test
    fun underABottomBarTheListEndsAboveTheBar() {
        show()
        compose.onNodeWithTag(PARCEL_LIST).performScrollToKey("foot")
        compose.waitForIdle()
        assertEquals(window().bottom.value - 21 - 62 - 36, bounds(FOOT).bottom.value, 0.5f)
    }

    @Test
    fun theCapsuleAndThePillInEnglishAreTheIPhones() {
        show()
        // The iPhone's 3× screenshot: the glass 822 pixels wide inside its dark edge, the pill 282, 12 in from its end.
        assertCapsuleAndPill(capsuleWidth = 274f, pillWidth = 94f)
        tab("Passport").performClick()
        compose.waitForIdle()
        val capsule = bounds(TAB_BAR)
        assertEquals(capsule.left.value + 8 + 86 - 4, bounds(TAB_PILL).left.value, 0.5f)
    }

    @Test
    fun theCapsuleAndThePillInGermanAreTheIPhones() {
        show(AppLanguage.De)
        // 840 pixels on the iPhone, its items as wide as "Sendungen" makes them.
        assertCapsuleAndPill(capsuleWidth = 280f, pillWidth = 96f)
    }

    private fun assertCapsuleAndPill(capsuleWidth: Float, pillWidth: Float) {
        val capsule = bounds(TAB_BAR)
        val pill = bounds(TAB_PILL)
        assertEquals(capsuleWidth, capsule.width.value, 0.5f)
        assertEquals(62f, capsule.height.value, 0.5f)
        assertEquals(capsule.left.value + 4, pill.left.value, 0.5f)
        assertEquals(pillWidth, pill.width.value, 0.5f)
        assertEquals(capsule.top.value + 4, pill.top.value, 0.5f)
        assertEquals(54f, pill.height.value, 0.5f)
    }

    @Test
    fun theKeyboardDoesNotLiftTheBar() {
        var ime = 0
        compose.setContent {
            view = LocalView.current
            ime = WindowInsets.ime.getBottom(LocalDensity.current)
            RootHost()
        }
        compose.waitForIdle()
        val before = bounds(TAB_BAR)
        insets(WindowInsetsCompat.Type.ime(), bottom = 600)
        assertTrue("The keyboard's insets reached the window", ime == 600)
        assertEquals(before, bounds(TAB_BAR))
    }

    @Test
    fun withNavigationButtonsTheBarStandsJustAboveThem() {
        var footprint = 0f
        compose.setContent {
            view = LocalView.current
            RootHost(deliveries = { footprint = LocalTabBarPadding.current.calculateBottomPadding().value })
        }
        compose.waitForIdle()
        // Over the gesture bar, the content makes room for the bar and the 21 under it.
        assertEquals(21f + 62, footprint, 0.01f)
        insets(WindowInsetsCompat.Type.tappableElement() or WindowInsetsCompat.Type.navigationBars(), bottom = 96)
        // 96 pixels at 2× are 48 points: the bar's bottom 8 above them.
        assertEquals(window().bottom.value - 48 - 8, bounds(TAB_BAR).bottom.value, 0.5f)
        assertEquals(48f + 8 + 62, footprint, 0.01f)
    }

    /** The window's insets as the system sends them, `top` and `bottom` pixels of the `types` at its top and bottom. */
    private fun insets(types: Int, top: Int = 0, bottom: Int = 0) {
        compose.runOnUiThread {
            val insets = WindowInsetsCompat.Builder()
                .setInsets(types, Insets.of(0, top, 0, bottom))
                .setVisible(types, true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun inBookPostureTheBarKeepsToTheStartHalf() {
        var activity by mutableStateOf<Activity?>(null)
        compose.setContent {
            view = LocalView.current
            activity = LocalActivity.current
            RootHost()
        }
        compose.waitForIdle()
        val hinge = FoldingFeature(activity!!, center = 700, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        val capsule = bounds(TAB_BAR)
        // The hinge at 350 points: the bar centred on 175.
        assertEquals(175f, (capsule.left.value + capsule.right.value) / 2, 0.5f)
        assertEquals(7.5f, capsule.top.value, 0.5f)
    }

    /** Half open on a table, the hinge level across the window: the bar stays centred, as it is told only of an upright one. */
    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun aLevelHingeLeavesTheBarCentred() {
        var activity by mutableStateOf<Activity?>(null)
        compose.setContent {
            view = LocalView.current
            activity = LocalActivity.current
            RootHost()
        }
        compose.waitForIdle()
        val hinge = FoldingFeature(activity!!, center = 840, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED, orientation = Orientation.HORIZONTAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        val capsule = bounds(TAB_BAR)
        assertEquals(350f, (capsule.left.value + capsule.right.value) / 2, 0.5f)
        assertEquals(7.5f, capsule.top.value, 0.5f)
    }

    /** A dual screen spanned by a window too narrow for the top bar: the bottom bar keeps to the start screen. */
    @Test
    @Config(qualifiers = "w580dp-h900dp-xhdpi")
    fun spanningAHingeTheBottomBarKeepsToTheStartHalf() {
        var activity by mutableStateOf<Activity?>(null)
        compose.setContent {
            view = LocalView.current
            activity = LocalActivity.current
            RootHost()
        }
        compose.waitForIdle()
        val hinge = FoldingFeature(activity!!, center = 580, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        val capsule = bounds(TAB_BAR)
        // The hinge at 290 points: the bar centred on 145, 21 over the window's bottom as ever.
        assertEquals(145f, (capsule.left.value + capsule.right.value) / 2, 0.5f)
        assertEquals(window().bottom.value - 21, capsule.bottom.value, 0.5f)
    }

    /** Made again in book posture (a change of theme), the window's first frame keeps the bar clear of the hinge. */
    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun inBookPostureTheBarIsClearOfTheHingeFromTheFirstFrameAfterARecreation() {
        var activity by mutableStateOf<Activity?>(null)
        val recreation = Recreation(compose)
        recreation.setContent {
            activity = LocalActivity.current
            RootHost()
        }
        compose.waitForIdle()
        val hinge = FoldingFeature(activity!!, center = 700, size = 0, state = androidx.window.layout.FoldingFeature.State.HALF_OPENED, orientation = Orientation.VERTICAL)
        fold.overrideWindowLayoutInfo(TestWindowLayoutInfo(listOf(hinge)))
        compose.waitForIdle()
        assertEquals(175f, bounds(TAB_BAR).let { (it.left.value + it.right.value) / 2 }, 0.5f)

        recreation.recreate()
        assertEquals(175f, bounds(TAB_BAR).let { (it.left.value + it.right.value) / 2 }, 0.5f)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        assertEquals(175f, bounds(TAB_BAR).let { (it.left.value + it.right.value) / 2 }, 0.5f)
    }

    /** The iOS 26 tab bar neither dims nor shrinks an item under the finger. */
    @Test
    fun anItemUnderTheFingerLooksAsItDidBefore() {
        show()
        compose.mainClock.autoAdvance = false
        val before = drawn("Passport")
        tab("Passport").performTouchInput { down(center) }
        compose.mainClock.advanceTimeBy(400)
        assertArrayEquals(before, drawn("Passport"))
        tab("Passport").performTouchInput { up() }
        compose.mainClock.advanceTimeBy(400)
    }

    /** The pixels of the tab labelled `label`, drawn now in software. */
    private fun drawn(label: String): IntArray {
        val root = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        val bounds = tab(label).fetchSemanticsNode().boundsInRoot
        val whole = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { root.draw(Canvas(whole)) }
        val pixels = IntArray(bounds.width.toInt() * bounds.height.toInt())
        whole.getPixels(pixels, 0, bounds.width.toInt(), bounds.left.toInt(), bounds.top.toInt(), bounds.width.toInt(), bounds.height.toInt())
        return pixels
    }

    /**
     * A phone set to Arabic lays out right to left, while the app, in English there as on the iPhone, keeps left to
     * right: the bar in the iPhone's order, the pill on the item chosen.
     */
    @Test
    @Config(qualifiers = "ar-ldrtl-w390dp-h844dp-xhdpi")
    fun aRightToLeftLanguageKeepsTheBarInOrderWithThePillOnItsItem() {
        show()
        assertEquals(View.LAYOUT_DIRECTION_RTL, view.resources.configuration.layoutDirection)
        val labels = listOf("Deliveries", "Passport", "Friends")
        val lefts = labels.map { tab(it).getBoundsInRoot().left.value }
        assertEquals(lefts.sorted(), lefts)
        for (label in labels) {
            tab(label).performClick()
            compose.waitForIdle()
            assertPillOn(label)
        }
    }

    /** Laid out right to left all the same, the bar keeps the pill on its item, the items' order mirrored. */
    @Test
    fun inARightToLeftLayoutThePillStaysOnItsItem() {
        var selection by mutableIntStateOf(0)
        compose.setContent {
            PeekTheme(dark = false, highContrast = false, reduceMotion = true, reduceTransparency = false, carrierIdentities = null) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    BottomTabBar(listOf("Deliveries", "Passport", "Friends"), selection, { selection = it }, backdrop = null)
                }
            }
        }
        compose.waitForIdle()
        assertTrue(tab("Deliveries").getBoundsInRoot().left > tab("Friends").getBoundsInRoot().left)
        for ((index, label) in listOf("Deliveries", "Passport", "Friends").withIndex()) {
            selection = index
            compose.waitForIdle()
            assertPillOn(label)
        }
    }

    /** The pill 4 past either side of the item labelled `label`, as wide as the item and those 8. */
    private fun assertPillOn(label: String) {
        val item = tab(label).getBoundsInRoot()
        val pill = bounds(TAB_PILL)
        assertEquals(item.left.value - 4, pill.left.value, 0.5f)
        assertEquals(item.right.value + 4, pill.right.value, 0.5f)
    }
}
