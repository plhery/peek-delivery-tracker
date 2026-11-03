package com.plhery.peekdeliverytracker.ui.friends

import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.ui.PHONE
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.time.Duration
import java.util.UUID
import kotlin.math.abs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The friends tab with the phone's animations removed, Android's Reduce Motion (C-AX15): what iOS keeps under Reduce
 * Motion still moves (KeepMotion), the friend revealed fading in over 0.15 s (C-AN16) and the invitation's parcel
 * opening over 0.1 s (C-AN18). Made-up friends; nothing leaves the test.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class FriendsKeptMotionTest {
    /** "Remove animations": the animator duration scale at 0. */
    private object AnimationsOff : MotionDurationScale {
        override val scaleFactor: Float get() = 0f
    }

    private val compose = createAndroidComposeRule<ComponentActivity>(AnimationsOff)

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private lateinit var source: FakeDeliveries

    private val owner = object : LifecycleOwner {
        val registry = LifecycleRegistry.createUnsafe(this).apply { currentState = Lifecycle.State.RESUMED }
        override val lifecycle: Lifecycle get() = registry
    }

    /** The friends tab of a signed-in reader, under Reduce Motion, chosen from the tab bar. */
    private fun show() {
        val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 7), email = "reader@example.com"))
        source = FakeDeliveries(DemoCards.parcels(), reader)
        compose.waitsForMaps()
        compose.setContent {
            CompositionLocalProvider(LocalLifecycleOwner provides owner) {
                RootHost(state = reader, dark = false, reduceMotion = true, source = source)
            }
        }
        settle()
        compose.onNode(hasContentDescription("Friends") and SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Tab)).performClick()
        settle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** What the node tagged [tag] shows as the window draws the last frame. */
    private fun capture(tag: String): PixelMap {
        compose.waitForIdle()
        shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(17))
        return compose.onNodeWithTag(tag, useUnmergedTree = true).captureToImage().toPixelMap()
    }

    /** How far apart two captures of one node are: their channels' mean difference. */
    private fun difference(a: PixelMap, b: PixelMap): Float {
        assertEquals(a.width to a.height, b.width to b.height)
        var sum = 0f
        for (y in 0 until a.height) for (x in 0 until a.width) {
            val p = a[x, y]
            val q = b[x, y]
            sum += abs(p.red - q.red) + abs(p.green - q.green) + abs(p.blue - q.blue)
        }
        return sum / (3f * a.width * a.height)
    }

    @Test
    fun aFriendRevealedFadesInWithAnimationsRemoved() {
        show()
        val tag = friendCardTag(FriendFixtures.di.id)
        compose.mainClock.autoAdvance = false
        compose.runOnUiThread { source.friendsActivity.reveal(FriendFixtures.di.id) }
        // First and in the middle, still out of sight: it lands 120 ms after the reveal.
        compose.mainClock.advanceTimeBy(90)
        val unseen = capture(tag)
        // 75 ms into the 0.15 s ease-out.
        compose.mainClock.advanceTimeBy(105)
        val landing = capture(tag)
        compose.mainClock.advanceTimeBy(1_000)
        val landed = capture(tag)
        val shown = difference(landing, unseen) / difference(landed, unseen)
        assertTrue("Partway in: $shown", shown > 0.2f && shown < 0.95f)
    }

    @Test
    fun theInvitationsParcelOpensWithAnimationsRemoved() {
        show()
        compose.onNodeWithTag(FRIENDS_INVITE).performScrollTo().performClick()
        settle()
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(FRIENDS_REPLAY).performClick()
        // Shut again, for the 80 ms before it opens.
        compose.mainClock.advanceTimeBy(40)
        val shut = capture(FRIENDS_REPLAY)
        // 50 ms into the 0.1 s linear opening.
        compose.mainClock.advanceTimeBy(90)
        val opening = capture(FRIENDS_REPLAY)
        compose.mainClock.advanceTimeBy(500)
        val open = capture(FRIENDS_REPLAY)
        val way = difference(shut, open)
        assertTrue("Opened at all: $way", way > 0f)
        assertTrue(
            "Partway open: ${difference(opening, shut)} from shut, ${difference(opening, open)} from open of $way",
            difference(opening, shut) > way * 0.1f && difference(opening, open) > way * 0.1f,
        )
    }
}
