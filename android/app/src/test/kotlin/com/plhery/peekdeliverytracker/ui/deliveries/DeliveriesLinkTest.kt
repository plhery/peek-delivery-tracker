package com.plhery.peekdeliverytracker.ui.deliveries

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** A window of the app as far as its links go: its own inbox, and the demo's deliveries counting their screens. */
class LinkedWindow : ComponentActivity() {
    val views = mutableListOf<String>()
    lateinit var inbox: DeliveriesInbox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inbox = DeliveriesInbox.of(this)
        setContent { RootHost(state = AuthenticationState.Demo, onView = { views += it }, inbox = inbox) }
    }
}

/** A parcel's link opens it in the window it came to (A-L14), once. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class DeliveriesLinkTest {
    /** Draws the windows' content on the test's clock, as the other tests' is. */
    @get:Rule
    val compose = createEmptyComposeRule()

    private val link get() = Intent(Intent.ACTION_VIEW, Uri.parse("peekdeliverytracker://parcel/${DemoCards.parcel("Kind of Blue").id}"))

    private fun window(intent: Intent? = null): ActivityController<LinkedWindow> =
        (if (intent == null) Robolectric.buildActivity(LinkedWindow::class.java) else Robolectric.buildActivity(LinkedWindow::class.java, intent))
            .setup()
            .also { settle() }

    /** The windows' frames, until they have done what they were told. */
    private fun settle() {
        compose.waitForIdle()
    }

    @Test
    fun aLinkOpensInTheWindowThatStartedWithItAndNoOther() {
        val behind = window()
        behind.pause().stop()
        val front = window(link)

        assertEquals(listOf("parcel"), front.get().views)
        assertNull(front.get().inbox.parcel.value)
        assertEquals(emptyList<String>(), behind.get().views)
    }

    @Test
    fun aLinkThatComesToAWindowOpensThere() {
        val behind = window()
        behind.pause().stop()
        val front = window()
        front.newIntent(link)
        settle()

        assertEquals(listOf("parcel"), front.get().views)
        assertEquals(emptyList<String>(), behind.get().views)
    }

    @Test
    fun theWindowMadeAgainDoesNotOpenItsLinkAgain() {
        val window = window(link)
        assertEquals(listOf("parcel"), window.get().views)
        window.get().onBackPressedDispatcher.onBackPressed()
        settle()
        assertEquals(listOf("parcel", "deliveries"), window.get().views)

        window.recreate()
        settle()
        assertEquals(emptyList<String>(), window.get().views)
        assertNull(window.get().inbox.parcel.value)
    }

    @Test
    fun aParcelStillWaitingOpensInTheWindowMadeAgain() {
        val window = Robolectric.buildActivity(LinkedWindow::class.java, link).create()
        // Made again before its list was there to show the parcel.
        window.recreate()
        window.start().resume().visible()
        settle()
        assertEquals(listOf("parcel"), window.get().views)
        assertNull(window.get().inbox.parcel.value)
    }
}
