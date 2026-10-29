package com.plhery.peekdeliverytracker.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_LIST
import com.plhery.peekdeliverytracker.ui.deliveries.PARCEL_PAGE
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import java.util.UUID
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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

    @Test
    fun theLaunchScreenShowsNothingForItsFirst600Milliseconds() {
        compose.mainClock.autoAdvance = false
        compose.setContent { RootHost(state = AuthenticationState.Loading) }
        compose.mainClock.advanceTimeBy(590)
        compose.onNodeWithTag(LAUNCH).assertExists()
        compose.onNodeWithText("Opening your parcels…").assertDoesNotExist()
        compose.mainClock.advanceTimeBy(30)
        compose.onNodeWithText("Opening your parcels…").assertExists()
    }

    @Test
    fun theTabAndTheOpenParcelComeBackAfterProcessDeath() {
        val restoration = StateRestorationTester(compose)
        restoration.setContent { RootHost() }
        compose.onNodeWithText("Parcel 3").performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()

        restoration.emulateSavedInstanceStateRestore()
        compose.onNodeWithTag(PARCEL_PAGE).assertExists()
        compose.onNodeWithText("Parcel 3").assertExists()

        compose.onNodeWithContentDescription("Friends").performClick()
        compose.waitForIdle()
        restoration.emulateSavedInstanceStateRestore()
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
