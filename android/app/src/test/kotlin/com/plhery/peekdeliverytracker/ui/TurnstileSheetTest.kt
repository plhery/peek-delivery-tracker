package com.plhery.peekdeliverytracker.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import android.webkit.WebResourceRequest
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.click
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isHeading
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.platform.TurnstileClient
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_BUTTON
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_ERROR
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_PARCEL
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_SAVE
import com.plhery.peekdeliverytracker.ui.deliveries.ADD_TRACKING
import com.plhery.peekdeliverytracker.ui.deliveries.CAPTURED
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LocalDeliveriesState
import com.plhery.peekdeliverytracker.ui.deliveries.VERIFICATION_PAGE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.sheet.SHEET_PANEL
import java.time.Duration
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * The browser check's sheet (B-S69, B-D41, B-L09) in the app's window, over the tabs and over Add, on pages that stand
 * in for the WebView and hear what a real one would tell its client (platform/WebViewTurnstilePages.kt's
 * [TurnstileClient]): the token, an error, messages to ignore, a load that fails and a renderer gone; Cancel, the sheet
 * sent away (the back over the tabs and over Add, a drag on the bar or on the page at its top) and the caller's
 * cancellation; the timeout; one check at a time, an https page, a WebView able to hold it and a window in front; a
 * window made again and one gone for good; the page under the bar; TalkBack; and Add saving through it. Each page is torn
 * down once. On a made-up service.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = PHONE)
class TurnstileSheetTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val pages = FakePages()
    private val sheet = TurnstileSheet(pages)
    private val localizer = Localizer(AppLanguage.En, variant = "android")
    private val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
    private var session by mutableStateOf<AuthenticationState>(reader)
    private lateinit var source: FakeDeliveries
    private lateinit var deliveries: DeliveriesState

    private val lamp = Parcel(
        id = UUID.fromString("00000000-0000-4000-8000-000000000701"), trackingNumber = "TESTLAMP0001", label = "Desk lamp",
        carrier = CarrierId.Unknown, createdAt = DateParser.isoString(CAPTURED.minus(Duration.ofHours(1))),
        syncStatus = SyncStatus.Ok, notificationsMuted = false,
    )

    private fun show(dark: Boolean = false, recreation: Recreation? = null) {
        source = FakeDeliveries(listOf(lamp), reader).apply {
            verification = sheet
            localizer = this@TurnstileSheetTest.localizer
        }
        val content: @Composable () -> Unit = {
            CompositionLocalProvider(LocalDeliveriesState provides { deliveries = it }) {
                RootHost(state = session, source = source, dark = dark, browserCheck = sheet)
            }
        }
        if (recreation != null) recreation.setContent(content) else compose.setContent(content)
        compose.waitForIdle()
    }

    private fun settle() {
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
    }

    /** A check asked for on the app's scope, as a lookup asks for it, and its outcome. */
    private fun ask(url: String = VERIFICATION_PAGE): Deferred<Result<String>> = source.scope.async { runCatching { sheet.token(url) } }

    private fun exists(tag: String): Boolean = compose.onAllNodesWithTag(tag, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun outcome(asked: Deferred<Result<String>>): Result<String> {
        assertTrue("The check is still up", asked.isCompleted)
        return asked.getCompleted()
    }

    private fun assertFailed(asked: Deferred<Result<String>>) =
        assertTrue(outcome(asked).exceptionOrNull().toString(), outcome(asked).exceptionOrNull() is NativeVerificationFailed)

    private fun assertCancelled(asked: Deferred<Result<String>>) =
        assertTrue(outcome(asked).exceptionOrNull().toString(), outcome(asked).exceptionOrNull() is CancellationException)

    /** The sheet has gone, and each page it held was torn down once. */
    private fun assertGone() {
        assertNull(sheet.check)
        assertFalse(exists(TURNSTILE_PAGE))
        assertFalse(exists(TURNSTILE_CANCEL))
        assertTrue(pages.made.isNotEmpty())
        pages.made.forEach { assertEquals(1, it.teardowns) }
    }

    /** Add from the list's +, a number typed, and Add tapped: the addition asks for the check. */
    private fun saveFromAdd() {
        compose.onNodeWithTag(ADD_BUTTON).performClick()
        settle()
        compose.onNodeWithTag(ADD_TRACKING).performTextReplacement("1Z999AA10123456784")
        compose.waitForIdle()
        compose.onNodeWithTag(ADD_SAVE).performClick()
        settle()
    }

    @Test
    fun theTokenThePagePostsEndsTheCheckAndTheSheetGoesThePageLoadingOnlyOnceTheSheetIsUp() {
        show()
        compose.mainClock.autoAdvance = false
        val asked = ask()
        compose.mainClock.advanceTimeBy(150)
        val page = pages.made.single()
        assertEquals(VERIFICATION_PAGE, page.url)
        assertEquals(0, page.loads)
        compose.mainClock.autoAdvance = true
        settle()
        assertEquals(1, page.loads)
        assertTrue(exists(TURNSTILE_PAGE))

        page.post("""{"type":"token","token":"synthetic-token"}""")
        settle()
        assertEquals("synthetic-token", outcome(asked).getOrThrow())
        assertGone()
        assertEquals(1, page.loads)
    }

    @Test
    fun anErrorThePagePostsFailsTheCheck() {
        show()
        val asked = ask()
        settle()
        pages.made.single().post("""{"type":"error"}""")
        settle()
        assertFailed(asked)
        assertGone()
    }

    @Test
    fun aMessageFromAnotherOriginAFrameOrAnotherPathIsIgnored() {
        show()
        val asked = ask()
        settle()
        val page = pages.made.single()
        val token = """{"type":"token","token":"synthetic-token"}"""
        page.post(token, origin = "https://elsewhere.example")
        page.post(token, mainFrame = false)
        page.post(token, frameUrl = "https://peek.example/another/page")
        page.post(token, frameUrl = null)
        page.post("""{"type":"token","token":""}""")
        page.post("""["token"]""")
        settle()
        assertFalse(asked.isCompleted)
        assertTrue(exists(TURNSTILE_PAGE))
        page.post(token)
        settle()
        assertEquals("synthetic-token", outcome(asked).getOrThrow())
        assertGone()
    }

    @Test
    fun aPageThatDoesNotLoadOrLosesItsRendererFailsTheCheck() {
        show()
        val first = ask()
        settle()
        pages.made.last().client.onReceivedError(null, request(VERIFICATION_PAGE, mainFrame = true), null)
        settle()
        assertFailed(first)
        assertGone()

        val second = ask()
        settle()
        // A frame's load that fails is nothing to the check.
        pages.made.last().client.onReceivedError(null, request("https://challenges.cloudflare.com/frame", mainFrame = false), null)
        settle()
        assertFalse(second.isCompleted)
        pages.made.last().client.onRenderProcessGone(null, null)
        settle()
        assertFailed(second)
        assertGone()
    }

    @Test
    fun cancelEndsTheCheckAsACancellation() {
        show()
        val asked = ask()
        settle()
        compose.onNodeWithTag(TURNSTILE_CANCEL).performClick()
        settle()
        assertCancelled(asked)
        assertGone()
    }

    @Test
    fun theBackATapOnTheDimAndADragDownSendTheSheetAwayAsCancelDoes() {
        show()
        val back = ask()
        settle()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        assertCancelled(back)

        val tap = ask()
        settle()
        // Above the sheet, at its large detent under the status bar.
        compose.onRoot().performTouchInput { click(center.copy(y = 2f)) }
        settle()
        assertCancelled(tap)

        val drag = ask()
        settle()
        val bar = compose.onNodeWithTag(TURNSTILE_CANCEL).getUnclippedBoundsInRoot()
        compose.onRoot().performTouchInput {
            val start = center.copy(y = bar.top.toPx() + 4)
            down(start)
            repeat(10) { moveBy(Offset(0f, 60f)) }
            up()
        }
        settle()
        assertCancelled(drag)
        assertGone()
        assertEquals(3, pages.made.size)
    }

    /** A drag down on the page, from `from` below its top, 600 in all at 60 a frame. */
    private fun dragOnThePage(from: Float = 100f) {
        val page = compose.onNodeWithTag(TURNSTILE_PAGE).getUnclippedBoundsInRoot()
        compose.onRoot().performTouchInput {
            down(Offset(center.x, page.top.toPx() + from))
            repeat(10) { moveBy(Offset(0f, 60f)) }
            up()
        }
    }

    @Test
    fun aDragDownOnThePageWhileItCannotScrollUpTakesTheSheetDownAsCancelDoes() {
        show()
        val asked = ask()
        settle()
        dragOnThePage()
        settle()
        assertCancelled(asked)
        assertGone()
    }

    @Test
    fun aPageScrolledDownScrollsUpFirstUnderADragDownAndTheSheetStays() {
        show()
        val asked = ask()
        settle()
        val view = pages.made.single().view
        compose.runOnUiThread { view.scrollY = 2_000 }
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        dragOnThePage()
        settle()
        assertFalse(asked.isCompleted)
        // 600 up, the finger's way.
        assertEquals(1_400, view.scrollY)
        assertEquals(panel, compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot())

        // At its top, the next one takes the sheet down.
        compose.runOnUiThread { view.scrollY = 0 }
        dragOnThePage()
        settle()
        assertCancelled(asked)
        assertGone()
    }

    @Test
    fun thePageLiesUnderTheBarsRowNotUnderTheBar() {
        show()
        ask()
        settle()
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()
        val cancel = compose.onNodeWithTag(TURNSTILE_CANCEL).getUnclippedBoundsInRoot()
        val page = compose.onNodeWithTag(TURNSTILE_PAGE).getUnclippedBoundsInRoot()
        assertTrue("$page under $cancel", page.top >= cancel.bottom)
        // The bar's row: 19 under the sheet's top, 44 tall.
        assertEquals((panel.top + 63.dp).value, page.top.value, 0.5f)
        assertEquals(panel.bottom.value, page.bottom.value, 0.5f)
    }

    @Test
    fun overAddTheBackSendsTheSheetAwayAsCancelDoesAndLeavesAddUp() {
        show()
        saveFromAdd()
        assertTrue(exists(TURNSTILE_PAGE))
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        assertGone()
        assertAddUpWithoutAnError()
    }

    @Test
    fun overAddInAWindowMadeAgainTheBackSendsTheSheetAwayAsCancelDoesAndLeavesAddUp() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        saveFromAdd()
        assertTrue(exists(TURNSTILE_PAGE))
        recreation.recreate()
        settle()
        assertEquals(2, pages.made.size)
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        settle()
        assertGone()
        assertAddUpWithoutAnError()
    }

    /** Add still up after the check it asked for was cancelled, not saving, with no error and nothing added. */
    private fun assertAddUpWithoutAnError() {
        assertNotNull(deliveries.addition)
        assertTrue(exists(ADD_SAVE))
        assertFalse(exists(ADD_ERROR))
        compose.onNodeWithTag(ADD_SAVE).assertIsEnabled()
        assertTrue(source.additions.isEmpty())
    }

    @Test
    fun theCallerCancelledTakesTheSheetAway() {
        show()
        val asked = ask()
        settle()
        asked.cancel()
        settle()
        assertGone()
    }

    @Test
    fun afterNinetySecondsTheCheckFails() {
        show()
        val asked = ask()
        settle()
        compose.mainClock.advanceTimeBy(88_000)
        compose.waitForIdle()
        assertFalse(asked.isCompleted)
        assertTrue(exists(TURNSTILE_PAGE))
        compose.mainClock.advanceTimeBy(2_000)
        compose.waitForIdle()
        settle()
        assertFailed(asked)
        assertGone()
    }

    @Test
    fun aSecondCheckWhileOneIsUpFailsAndLeavesTheFirstUp() {
        show()
        val first = ask()
        settle()
        val second = ask()
        settle()
        assertFailed(second)
        assertFalse(first.isCompleted)
        assertEquals(1, pages.made.size)
        pages.made.single().post("""{"type":"token","token":"synthetic-token"}""")
        settle()
        assertEquals("synthetic-token", outcome(first).getOrThrow())
        assertGone()
    }

    @Test
    fun withoutTheWebViewsListenerAndScriptTheCheckFailsAtOnceWithNoSheet() {
        pages.supported = false
        show()
        compose.mainClock.autoAdvance = false
        val asked = ask()
        compose.mainClock.advanceTimeByFrame()
        assertFailed(asked)
        assertNull(sheet.check)
        assertFalse(exists(SHEET_PANEL))
        assertTrue(pages.made.isEmpty())
    }

    @Test
    fun onlyAnHttpsPageOpens() {
        show()
        val asked = ask("http://peek.example/api/public/native/turnstile?language=en")
        settle()
        assertFailed(asked)
        assertFalse(exists(SHEET_PANEL))
        assertTrue(pages.made.isEmpty())
    }

    @Test
    fun aPageThatCannotBeMadeFailsTheCheck() {
        pages.fails = true
        show()
        val asked = ask()
        settle()
        assertFailed(asked)
        assertNull(sheet.check)
        assertFalse(exists(SHEET_PANEL))
    }

    @Test
    fun withNoWindowInFrontTheCheckFailsAtOnce() {
        session = AuthenticationState.SignedOut
        show()
        val asked = ask()
        settle()
        assertFailed(asked)
        assertTrue(pages.made.isEmpty())
    }

    @Test
    fun theWindowGoneForGoodFailsTheCheckAsTheIPhonesSceneTakesItsSheetAndLookup() {
        show()
        val asked = ask()
        settle()
        assertEquals(1, pages.made.single().loads)
        // The tabs leave this window for good, not made again.
        session = AuthenticationState.SignedOut
        settle()
        assertFailed(asked)
        assertGone()
    }

    @Test
    fun aWindowMadeAgainKeepsTheSheetUpLoadsThePageAfreshAndAddSavesWithItsToken() {
        val recreation = Recreation(compose)
        show(recreation = recreation)
        saveFromAdd()
        val first = pages.made.single()
        assertEquals(1, first.loads)
        val panel = compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot()

        recreation.recreate()
        assertEquals(1, first.teardowns)
        assertNotNull(sheet.check)
        // Up already, it doesn't come up again: where it was from its layer's first frame.
        compose.mainClock.advanceTimeByFrame()
        assertEquals(panel, compose.onNodeWithTag(SHEET_PANEL).getUnclippedBoundsInRoot())
        settle()
        val second = pages.made.last()
        assertEquals(2, pages.made.size)
        assertEquals(1, second.loads)
        assertEquals(0, second.teardowns)

        second.post("""{"type":"token","token":"synthetic-token"}""")
        settle()
        assertGone()
        assertNull(deliveries.addition)
        assertEquals(1, source.additions.size)
    }

    @Test
    fun overAddTalkBackReachesTheBarThenThePageInAPaneNamedForTheTitleAndNothingUnder() {
        show()
        saveFromAdd()
        val title = localizer.text("native.verification.title")
        val panes = compose.onAllNodesWithTag(SHEET_PANEL, useUnmergedTree = true).fetchSemanticsNodes()
            .mapNotNull { it.config.getOrNull(SemanticsProperties.PaneTitle) }
        assertEquals(listOf(title), panes)
        compose.onNode(hasText(title) and isHeading()).assertExists()
        val cancel = compose.onNodeWithTag(TURNSTILE_CANCEL).fetchSemanticsNode().config
        assertEquals(Role.Button, cancel.getOrNull(SemanticsProperties.Role))
        assertEquals(listOf(localizer.text("common.cancel")), cancel.getOrNull(SemanticsProperties.Text)?.map { it.text })
        assertTrue(exists(TURNSTILE_PAGE))
        // Add's cover, under the sheet, is out of TalkBack's reach.
        assertTrue(compose.onAllNodesWithTag(ADD_PARCEL).fetchSemanticsNodes().isEmpty())
        assertTrue(compose.onAllNodesWithTag(ADD_SAVE).fetchSemanticsNodes().isEmpty())
        // The bar goes before the page.
        val bar = compose.onNode(hasText(title) and isHeading(), useUnmergedTree = true).fetchSemanticsNode()
        assertEquals(-1f, generateSequence(bar) { it.parent }.firstNotNullOfOrNull { it.config.getOrNull(SemanticsProperties.TraversalIndex) })
    }

    @Test
    fun thePageFollowsTheAppsAppearance() {
        show(dark = true)
        ask()
        settle()
        assertTrue(pages.made.single().dark)
    }

    @Test
    fun aCancelledCheckLeavesAddUpNotSavingAndWithoutAnError() {
        show()
        saveFromAdd()
        assertTrue(exists(TURNSTILE_PAGE))
        compose.onNodeWithTag(TURNSTILE_CANCEL).performClick()
        settle()
        assertGone()
        assertNotNull(deliveries.addition)
        assertFalse(exists(ADD_ERROR))
        compose.onNodeWithTag(ADD_SAVE).assertIsEnabled()
        assertEquals(localizer.text("app.addParcel"), compose.onNodeWithTag(ADD_SAVE).fetchSemanticsNode().config.getOrNull(SemanticsProperties.ContentDescription)?.joinToString())
        assertTrue(source.additions.isEmpty())
    }

    @Test
    fun aCheckThatFailsShowsTheIPhonesMessageOnAdd() {
        show()
        saveFromAdd()
        pages.made.single().post("""{"type":"error"}""")
        settle()
        assertGone()
        assertNotNull(deliveries.addition)
        assertTrue(exists(ADD_ERROR))
        compose.onNode(hasText(localizer.text("door.verification.body"))).assertExists()
        compose.onNodeWithTag(ADD_SAVE).assertIsEnabled()
        assertTrue(source.additions.isEmpty())
    }
}

/** A main frame's request, or a frame's, as the WebView hands its client one: a redirect where `redirect`. */
internal fun request(url: String, mainFrame: Boolean, redirect: Boolean = false, method: String = "GET"): WebResourceRequest = object : WebResourceRequest {
    override fun getUrl(): Uri = Uri.parse(url)

    override fun isForMainFrame(): Boolean = mainFrame

    override fun isRedirect(): Boolean = redirect

    override fun hasGesture(): Boolean = false

    override fun getMethod(): String = method

    override fun getRequestHeaders(): Map<String, String> = emptyMap()
}

/**
 * Pages a test holds, each a view that takes touches as a WebView does ([TouchedPage]) unless drawn otherwise, whose
 * WebView client the test speaks for; or none, as a device without one.
 */
internal class FakePages(private val draw: ((Context, Boolean) -> View)? = null) : TurnstilePages {
    var supported = true
    var fails = false
    val made = mutableListOf<FakePage>()

    override fun supported(): Boolean = supported

    override fun page(context: Context, url: String, dark: Boolean, background: Int, events: TurnstileEvents): TurnstilePage {
        if (fails) throw IllegalStateException("No WebView on this device")
        return FakePage(draw?.invoke(context, dark) ?: TouchedPage(context), url, dark, events).also { made += it }
    }
}

/** A page: what the sheet asked of it, and the client its WebView would tell what it hears. */
internal class FakePage(override val view: View, val url: String, val dark: Boolean, events: TurnstileEvents) : TurnstilePage {
    val client = TurnstileClient(url, events)
    var loads = 0
    var teardowns = 0

    override fun load() {
        loads++
    }

    override fun tearDown() {
        teardowns++
        client.ended = true
    }

    /** A message posted, from the page's main frame unless said otherwise, as the message listener hears it. */
    fun post(data: String, origin: String = "https://peek.example", mainFrame: Boolean = true, frameUrl: String? = url) {
        client.message(mainFrame, Uri.parse(origin), frameUrl, data)
    }
}

/** A page's view as a WebView takes the touches on it: every one, scrolling the page up with the finger while it is scrolled down. */
internal class TouchedPage(context: Context) : View(context) {
    private var lastY = 0f

    override fun canScrollVertically(direction: Int): Boolean = direction > 0 || scrollY > 0

    @SuppressLint("ClickableViewAccessibility") // A stand-in for the WebView, which takes its own touches.
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> lastY = event.rawY
            MotionEvent.ACTION_MOVE -> {
                scrollY = (scrollY - (event.rawY - lastY).toInt()).coerceAtLeast(0)
                lastY = event.rawY
            }
        }
        return true
    }
}
