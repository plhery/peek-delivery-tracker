package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelStore
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.Recreation
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The message saying how many of this phone's parcels a full account left here outlives a window made again within its
 * eight seconds, which then gives it eight more; once it has run, its news is let go and no later window says it again.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class LeftOnDeviceRecreationTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private val words = "Your deliveries are full, so 2 parcels stayed on this device. Archive or delete a few, and Peek will offer them again."

    private fun toastShown(): Boolean = compose.onAllNodes(hasTestTag(INLINE_TOAST) and hasContentDescription(words, substring = true))
        .fetchSemanticsNodes().isNotEmpty()

    private fun start(): Pair<FakeDeliveries, Recreation> {
        val source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        val recreation = Recreation(compose)
        recreation.setContent { RootHost(state = AuthenticationState.Demo, source = source, reduceMotion = false) }
        compose.waitForIdle()
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.leftOnDevice.value = ParcelStore.LeftOnDevice(2) }
        compose.mainClock.advanceTimeBy(100)
        return source to recreation
    }

    @Test
    fun theMessageOutlivesAWindowMadeAgainAndIsSaidOnlyOnce() {
        val (source, recreation) = start()
        assertTrue("shown before the turn", toastShown())
        compose.mainClock.advanceTimeBy(2000)
        recreation.recreate()
        compose.mainClock.advanceTimeBy(500)
        assertTrue("kept by the window made again 2 s into it", toastShown())
        compose.mainClock.advanceTimeBy(8500)
        assertFalse("gone once it has run", toastShown())
        assertEquals("let go once it has run", null, source.leftOnDevice.value)
        recreation.recreate()
        compose.mainClock.advanceTimeBy(500)
        assertFalse("not said again by a later window", toastShown())
    }

    /** In one window the message runs its eight seconds and its news is let go. */
    @Test
    fun oneWindowSaysItForEightSecondsThenLetsGo() {
        val (source, _) = start()
        compose.mainClock.advanceTimeBy(7800)
        assertTrue(toastShown())
        compose.mainClock.advanceTimeBy(300)
        assertFalse(toastShown())
        assertEquals(null, source.leftOnDevice.value)
    }
}
