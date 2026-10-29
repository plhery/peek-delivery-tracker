package com.plhery.peekdeliverytracker.ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.util.UUID
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** RootView's routing, the launch screen's wait, and what the window keeps through process death. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class PeekRootTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun eachStateShowsWhatRootViewShowsAndReplacesTheOneBeforeAtOnce() {
        var state by mutableStateOf<AuthenticationState>(AuthenticationState.Loading)
        compose.setContent { RootHost(state = state) }
        compose.mainClock.autoAdvance = false
        val shown = listOf(
            AuthenticationState.Loading to LAUNCH,
            AuthenticationState.Unconfigured to ARRIVAL,
            AuthenticationState.Welcome to TAB_BAR,
            AuthenticationState.SignedOut to ARRIVAL,
            AuthenticationState.Demo to TAB_BAR,
            AuthenticationState.Loading to LAUNCH,
            AuthenticationState.SignedIn(AuthUser(UUID(0, 1), email = "reader@example.com")) to TAB_BAR,
        )
        for ((next, tag) in shown) {
            state = next
            Snapshot.sendApplyNotifications()
            compose.mainClock.advanceTimeByFrame()
            compose.onNodeWithTag(tag).assertExists()
            for (other in listOf(LAUNCH, ARRIVAL, TAB_BAR) - tag) compose.onNodeWithTag(other).assertDoesNotExist()
        }
    }

    /**
     * How much the launch screen draws over its background, drawn now in software: each pixel's largest difference
     * from the background in any channel, summed. Under an opacity it scales with it.
     */
    private fun launchInk(): Long {
        val view = (compose.onRoot().fetchSemanticsNode().root as ViewRootForTest).view
        val bounds = compose.onNodeWithTag(LAUNCH).fetchSemanticsNode().boundsInRoot
        val whole = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        compose.runOnUiThread { view.draw(Canvas(whole)) }
        val pixels = IntArray(bounds.width.toInt() * bounds.height.toInt())
        whole.getPixels(pixels, 0, bounds.width.toInt(), bounds.left.toInt(), bounds.top.toInt(), bounds.width.toInt(), bounds.height.toInt())
        val background = pixels[0]
        return pixels.sumOf { pixel ->
            (0 until 3).maxOf { channel -> abs((pixel shr (8 * channel) and 0xFF) - (background shr (8 * channel) and 0xFF)) }.toLong()
        }
    }

    @Test
    fun theLaunchScreenShowsNothingFor600MillisecondsThenFadesItsProgressInOverAFifthOfASecond() {
        compose.mainClock.autoAdvance = false
        compose.setContent { RootHost(state = AuthenticationState.Loading) }
        compose.mainClock.advanceTimeBy(590)
        compose.onNodeWithTag(LAUNCH).assertExists()
        assertEquals(0L, launchInk())
        compose.mainClock.advanceTimeBy(110)
        val partway = launchInk()
        compose.mainClock.advanceTimeBy(150)
        val faded = launchInk()
        compose.mainClock.advanceTimeBy(150)
        val shown = launchInk()
        compose.onNodeWithText("Opening your parcels…").assertExists()
        assertTrue("The mark, the spinner and the line are drawn", shown > 10_000)
        // 100 ms into the fade, an ease out of 0.2 s, partway; done by 250 ms.
        assertTrue("Partway at 700 ms: ${partway.toDouble() / shown}", partway > 0.2 * shown && partway < 0.95 * shown)
        assertEquals(1.0, faded.toDouble() / shown, 0.03)
    }

    @Test
    fun progressOnceShownShowsAtOnceWhenTheWindowIsMadeAgain() {
        val recreation = Recreation(compose)
        compose.mainClock.autoAdvance = false
        recreation.setContent { RootHost(state = AuthenticationState.Loading) }
        compose.mainClock.advanceTimeBy(1000)
        val shown = launchInk()
        assertTrue(shown > 10_000)

        recreation.recreate()
        // In full from the first frame: neither the wait nor the fade again.
        assertEquals(1.0, launchInk().toDouble() / shown, 0.03)
    }

    @Test
    fun theTabAndTheOpenParcelComeBackAfterProcessDeathWithNothingCountedOrFelt() {
        var session = mutableStateOf<AuthenticationState>(AuthenticationState.Loading)
        val views = mutableListOf<String>()
        lateinit var view: View
        val restoration = StateRestorationTester(compose)
        restoration.setContent {
            view = LocalView.current
            RootHost(state = session.value, onView = { views += it })
        }
        session.value = AuthenticationState.Welcome
        compose.onNodeWithText("Parcel 3").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()

        /** A new process: its session is read again behind the launch screen before the tabs come back. */
        fun newProcess() {
            session = mutableStateOf(AuthenticationState.Loading)
            restoration.emulateSavedInstanceStateRestore()
            compose.onNodeWithTag(LAUNCH).assertExists()
            views.clear()
            // A haptic no tab plays, to tell whether one plays as the tabs come back.
            compose.runOnUiThread { view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK) }
            session.value = AuthenticationState.Welcome
            compose.waitForIdle()
            assertEquals(emptyList<String>(), views)
            assertEquals(HapticFeedbackConstants.CLOCK_TICK, shadowOf(view).lastHapticFeedbackPerformed())
        }

        newProcess()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        compose.onNodeWithText("Parcel 3").assertExists()

        compose.onNodeWithContentDescription("Friends").performClick()
        compose.waitForIdle()
        assertEquals(listOf("friends"), views)
        newProcess()
        compose.onNodeWithContentDescription("Friends").assertIsSelected()
        compose.onNodeWithTag("friends").assertExists()

        // The deliveries kept their stack while the friends were shown.
        compose.onNodeWithContentDescription("Deliveries").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
    }

    @Test
    fun backLeavesTheParcelForTheListAndThenTheApp() {
        compose.setContent { RootHost() }
        val back = compose.activity.onBackPressedDispatcher
        compose.waitForIdle()
        assertFalse(back.hasEnabledCallbacks())

        compose.onNodeWithText("Parcel 3").performClick()
        compose.waitForIdle()
        assertTrue(back.hasEnabledCallbacks())
        compose.runOnUiThread { back.onBackPressed() }
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertDoesNotExist()
        compose.onNodeWithTag(PARCEL_LIST).assertExists()
        assertFalse(back.hasEnabledCallbacks())
    }
}
