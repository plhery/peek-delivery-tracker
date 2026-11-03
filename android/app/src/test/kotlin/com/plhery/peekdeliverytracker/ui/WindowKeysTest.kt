package com.plhery.peekdeliverytracker.ui

import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelLinkRoute
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.FakeParcelLinks
import com.plhery.peekdeliverytracker.ui.deliveries.LinkFixtures
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_SHEET
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The window's keys on a hardware keyboard (A-G10, A-G11): the deliveries' and the tabs', and what stops them, as the
 * iPad's `keyboardFree` and `windowCovered` do.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = TABLET)
class WindowKeysTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    private lateinit var view: View
    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState
    private val links = FakeParcelLinks(answer = LinkFixtures.viewer(), session = AuthenticationState.Demo)

    /** The demo's deliveries beside a parcel, a parcel's link able to open over them. */
    private fun show() {
        source = FakeDeliveries(DemoCards.parcels(), AuthenticationState.Demo)
        compose.setContent {
            view = LocalView.current
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = AuthenticationState.Demo, source = source, links = links)
            }
        }
        compose.waitForIdle()
    }

    /**
     * A key pressed and let go on a hardware keyboard, through the window's input stages after the keyboard's, as the
     * system delivers it: the views first, then what none of them used.
     */
    private fun press(key: Int, meta: Int = 0) {
        val window = view.rootView.parent
        val deliver = window.javaClass.getMethod("dispatchKeyFromIme", KeyEvent::class.java)
        compose.runOnUiThread {
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_DOWN, key, 0, meta))
            deliver.invoke(window, KeyEvent(0, 0, KeyEvent.ACTION_UP, key, 0, meta))
        }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    private fun shown() = deliveries.navigator.navigation.shown

    /** `links.route == nil` is part of `keyboardFree`: under a parcel's link's sheet, ↓ and Ctrl+N do nothing. */
    @Test
    fun theDeliveriesKeysRestUnderALinksSheet() {
        show()
        val first = shown()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        val second = shown()
        assertNotNull(second)
        assertEquals(false, second == first)

        compose.runOnUiThread { links.links.open(ParcelLinkRoute(id = LinkFixtures.viewer().link.id)) }
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        compose.onNodeWithTag(LINK_SHEET).assertExists()
        press(KeyEvent.KEYCODE_DPAD_DOWN)
        assertEquals(second, shown())
        press(KeyEvent.KEYCODE_N, CTRL)
        assertNull(deliveries.addition)
    }
}

private const val TABLET = "w1032dp-h1376dp-xhdpi"
private const val CTRL = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
