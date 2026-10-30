package com.plhery.peekdeliverytracker.ui.deliveries

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Density
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.ParcelShareModel
import com.plhery.peekdeliverytracker.core.ParcelShareWords
import com.plhery.peekdeliverytracker.core.api.ParcelShare
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.SHOT
import com.plhery.peekdeliverytracker.ui.screenshot
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A parcel's share sheet over its page, with the iPhone's bars: reading the link, with its link, light and dark and at
 * the largest Android text size, wrapped as a gift with its words, once sharing was stopped, in the demo, and for a
 * parcel that has arrived, as it is and as a gift, under Reduce Motion so the delivered box on its page is at rest.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class ParcelShareScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    private lateinit var view: View
    private lateinit var deliveries: DeliveriesState
    private lateinit var source: FakeDeliveries

    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 8), email = "reader@example.com"))

    /** A link as the service would have it, showing nothing more than the journey. */
    private val link = ParcelShare(id = "TestLink2345", showNumber = false, gift = false, createdAt = "2026-05-01T09:00:00Z")

    /** The page of the parcel named `name` with the window's bars, then its share sheet. */
    private fun show(
        name: String = "Birthday gift",
        session: AuthenticationState = reader,
        dark: Boolean = false,
        fontScale: Float = 1f,
        reduceMotion: Boolean = false,
        prepare: FakeDeliveries.() -> Unit = { shareLinks[DemoCards.parcel(name).id] = link },
    ) {
        source = FakeDeliveries(DemoCards.parcels(), session).apply(prepare)
        compose.waitsForMaps()
        compose.setContent {
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(LocalDensity provides Density(density.density, fontScale), LocalDeliveriesState provides { deliveries = it }) {
                Box(Modifier.fillMaxSize().testTag(SHOT)) {
                    RootHost(state = session, dark = dark, reduceMotion = reduceMotion, source = source, identities = pinnedIdentities)
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread {
            val scale = view.resources.displayMetrics.density
            fun px(points: Int) = (points * scale).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(62), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(24)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
        compose.openCard(DemoCards.parcel(name).label)
        settle()
        compose.onNodeWithTag(PAGE_SHARE).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** The gift switch on, saved with the link, and its words as the reader wrote them, without the keyboard. */
    private fun wrapped(words: ParcelShareWords) {
        val work = source.shares.sheet(deliveries.sharing!!.id)!!
        compose.runOnUiThread {
            work.set(ParcelShareModel.Field.Gift, true)
            work.type(words)
        }
        settle()
    }

    @Test
    fun readingTheLink() {
        show { shareLoading = CompletableDeferred() }
        compose.screenshot("parcel_share_loading")
    }

    @Test
    fun withItsLink() {
        show()
        compose.screenshot("parcel_share_linked_light")
    }

    @Test
    fun withItsLinkInDark() {
        show(dark = true)
        compose.screenshot("parcel_share_linked_dark")
    }

    @Test
    fun withItsLinkAtTheLargestText() {
        show(fontScale = 2f)
        compose.screenshot("parcel_share_linked_text2")
    }

    @Test
    fun wrappedAsAGiftWithItsWords() {
        show()
        wrapped(ParcelShareWords(note = "Happy birthday! Open it after dinner.", from = "Sam"))
        compose.screenshot("parcel_share_gift_light")
    }

    @Test
    fun wrappedAsAGiftWithItsWordsInDark() {
        show(dark = true)
        wrapped(ParcelShareWords(note = "Happy birthday! Open it after dinner.", from = "Sam"))
        compose.screenshot("parcel_share_gift_dark")
    }

    @Test
    fun onceSharingWasStopped() {
        show()
        compose.onNodeWithTag(PARCEL_SHARE_STOP).performClick()
        settle()
        compose.screenshot("parcel_share_stopped")
    }

    @Test
    fun inTheDemo() {
        show(session = AuthenticationState.Demo, prepare = {})
        compose.screenshot("parcel_share_demo")
    }

    @Test
    fun aParcelThatHasArrived() {
        show(name = "Coffee beans", reduceMotion = true)
        compose.screenshot("parcel_share_delivered")
    }

    @Test
    fun aGiftThatHasArrived() {
        show(name = "Coffee beans", reduceMotion = true)
        wrapped(ParcelShareWords(note = "Enjoy the beans!", from = "Sam"))
        compose.screenshot("parcel_share_delivered_gift")
    }
}
