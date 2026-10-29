package com.plhery.peekdeliverytracker.ui.tabs

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.unit.DpRect
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
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.theme.SensoryFeedback
import com.plhery.peekdeliverytracker.ui.theme.hapticFeedbackConstant
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
    private val views = mutableListOf<String>()

    private fun show(language: AppLanguage = AppLanguage.En) {
        compose.setContent {
            view = LocalView.current
            RootHost(language = language, onView = { views += it })
        }
        compose.waitForIdle()
    }

    private fun tab(label: String): SemanticsNodeInteraction = compose.onNodeWithContentDescription(label)

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
    fun tappingTheChosenTabGoesBackToTheListThenScrollsItToTheTop() {
        show()
        compose.onNodeWithText("Parcel 3").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()

        tab("Deliveries").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()

        compose.onNodeWithTag(PARCEL_LIST).performScrollToIndex(35)
        compose.waitForIdle()
        compose.onNodeWithText("Parcel 1").assertDoesNotExist()
        tab("Deliveries").performClick()
        compose.waitForIdle()
        compose.onNodeWithText("Parcel 1").assertExists()
        // Tapping the chosen tab is no change of tab.
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

    @Test
    @Config(qualifiers = "w840dp-h900dp-xhdpi")
    fun aTabletPutsTheBarAtTheTop() = assertTopBar()

    @Test
    @Config(qualifiers = "w700dp-h840dp-xhdpi")
    fun anUnfoldedFoldablePutsTheBarAtTheTop() = assertTopBar()

    private fun assertBottomBar() {
        show()
        val capsule = bounds(TAB_BAR)
        val window = window()
        assertEquals(62f, capsule.height.value, 0.5f)
        assertEquals(window.bottom.value - 21, capsule.bottom.value, 0.5f)
        assertEquals(window.width.value / 2, (capsule.left.value + capsule.right.value) / 2, 0.5f)
    }

    private fun assertTopBar() {
        show()
        val capsule = bounds(TAB_BAR)
        assertEquals(44f, capsule.height.value, 0.5f)
        assertEquals(7.5f, capsule.top.value, 0.5f)
        assertEquals(window().width.value / 2, (capsule.left.value + capsule.right.value) / 2, 0.5f)
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

    /** The window's insets as the system sends them, `bottom` pixels of the `types` at its bottom. */
    private fun insets(types: Int, bottom: Int) {
        compose.runOnUiThread {
            val insets = WindowInsetsCompat.Builder()
                .setInsets(types, Insets.of(0, 0, 0, bottom))
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
            activity = LocalContext.current as Activity
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
}
