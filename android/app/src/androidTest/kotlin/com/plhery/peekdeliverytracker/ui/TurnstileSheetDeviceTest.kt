package com.plhery.peekdeliverytracker.ui

import android.content.Context
import android.os.SystemClock
import android.view.InputDevice
import android.view.MotionEvent
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.platform.DEVICE_PAGE
import com.plhery.peekdeliverytracker.platform.WebViewTurnstilePages
import com.plhery.peekdeliverytracker.platform.answerOnDevice
import com.plhery.peekdeliverytracker.platform.checkPage
import com.plhery.peekdeliverytracker.ui.arrival.signInConfiguration
import com.plhery.peekdeliverytracker.ui.arrival.signInSession
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import java.util.Collections
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The browser check's sheet over the tabs with this device's own WebView in it (B-S69), which the JVM tests stand in
 * for, under real touches through the window: a drag down on the page at its top takes the sheet down with the finger
 * and ends the check as Cancel does; a page scrolled down scrolls up under it first, the sheet staying; the token the
 * page posts ends the check and the sheet goes with its page; the bar and the page stand on one ground. The page is
 * answered on the device (PageOnDevice.kt).
 */
@RunWith(AndroidJUnit4::class)
class TurnstileSheetDeviceTest {
    private lateinit var scenario: ActivityScenario<ComponentActivity>
    private val scope = MainScope()
    private val requests: MutableList<String> = Collections.synchronizedList(mutableListOf())
    private val made = mutableListOf<TurnstilePage>()
    private var body = ""
    private val pages = object : TurnstilePages {
        override fun supported(): Boolean = WebViewTurnstilePages.supported()

        override fun page(context: Context, url: String, dark: Boolean, background: Int, events: TurnstileEvents): TurnstilePage =
            WebViewTurnstilePages.page(context, url, dark, background, events).also {
                answerOnDevice(it, checkPage(body), requests)
                made += it
            }
    }
    private val sheet = TurnstileSheet(pages)

    /** The session the tabs are shown with, on a fake auth service no test reaches. */
    private val session = signInSession(scope, signInConfiguration(configured = false))

    @Before
    fun open() {
        scenario = ActivityScenario.launch(ComponentActivity::class.java)
    }

    @After
    fun close() {
        scope.cancel()
        scenario.close()
    }

    /** The tabs, dark where `dark`, and a check asked for whose page holds `page`, once its page has loaded. */
    private fun ask(page: String, dark: Boolean = false): Deferred<Result<String>> {
        body = page
        val localizer = Localizer(AppLanguage.En, variant = "android")
        scenario.onActivity {
            it.setContent {
                PeekTheme(dark = dark, highContrast = false, reduceMotion = false, reduceTransparency = false, carrierIdentities = null) {
                    PeekRoot(AuthenticationState.Welcome, localizer, session, onView = {}, browserCheck = sheet) { Box(Modifier.fillMaxSize()) }
                }
            }
        }
        // The tabs' window in front, which the check needs.
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        val verification = scope.async { runCatching { sheet.token(DEVICE_PAGE) } }
        // A WebView that hasn't loaded anything says 100 too.
        until("the page loaded") {
            main { made.size == 1 && (made[0].view as WebView).let { it.url == DEVICE_PAGE && it.progress == 100 } }
        }
        // The sheet at rest: up before the page loads, and still for a few frames.
        var last: Pair<Int, Int>? = null
        var still = 0
        until("the sheet at rest") {
            val at = pageAt()
            still = if (at == last) still + 1 else 0
            last = at
            still >= 5
        }
        return verification
    }

    private fun <T> main(read: () -> T): T {
        var value: T? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync { value = read() }
        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    private fun until(what: String, seconds: Int = 10, condition: () -> Boolean) {
        val end = SystemClock.uptimeMillis() + seconds * 1000
        while (!condition()) {
            check(SystemClock.uptimeMillis() < end) { "Not $what in $seconds s" }
            SystemClock.sleep(50)
        }
    }

    private fun outcome(check: Deferred<Result<String>>): Result<String> = runBlocking { check.await() }

    /** Where the page stands on the screen, its top-left corner. */
    private fun pageAt(): Pair<Int, Int> = main {
        val at = IntArray(2)
        made[0].view.getLocationOnScreen(at)
        at[0] to at[1]
    }

    private fun scrollY(): Int = main { made[0].view.scrollY }

    /**
     * From `from` below the page's top down to near the window's foot: far enough on any screen that the sheet, come
     * down with the finger, is nearer gone than at rest, where a release held still sends it away.
     */
    private fun toTheFoot(from: Int = 100): Int = main {
        val at = IntArray(2)
        val root = made[0].view.rootView
        root.getLocationOnScreen(at)
        at[1] + root.height - FOOT_MARGIN
    } - pageAt().second - from

    /**
     * A finger put on the page `from` below its top, down `by` in 15 moves a frame apart, held still, then lifted;
     * how far the page had come down with it before it was lifted.
     */
    private fun drag(from: Int = 100, by: Int = 600): Int {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val (left, top) = pageAt()
        val x = left + main { made[0].view.width } / 2f
        val y = (top + from).toFloat()
        val down = SystemClock.uptimeMillis()
        var time = down
        // A finger's, as a screen's touches are: WebView 91 (Android 12's image) didn't scroll under the default, no tool.
        val finger = MotionEvent.PointerProperties().apply { id = 0; toolType = MotionEvent.TOOL_TYPE_FINGER }
        fun send(action: Int, at: Float) {
            val where = MotionEvent.PointerCoords().apply { this.x = x; this.y = at; pressure = 1f; size = 1f }
            val event = MotionEvent.obtain(
                down, time, action, 1, arrayOf(finger), arrayOf(where), 0, 0, 1f, 1f, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0,
            )
            instrumentation.sendPointerSync(event)
            event.recycle()
        }
        send(MotionEvent.ACTION_DOWN, y)
        for (step in 1..15) {
            SystemClock.sleep(16)
            time += 16
            send(MotionEvent.ACTION_MOVE, y + by * step / 15f)
        }
        // Held still, so that nothing flings.
        repeat(8) {
            SystemClock.sleep(16)
            time += 16
            send(MotionEvent.ACTION_MOVE, y + by)
        }
        val moved = pageAt().second - top
        send(MotionEvent.ACTION_UP, y + by)
        return moved
    }

    @Test
    fun aDragDownOnThePageAtItsTopTakesTheSheetDownWithTheFingerAsCancelDoes() {
        val verification = ask("<div id=\"check\"></div>")
        val at = pageAt()
        val by = toTheFoot()
        val moved = drag(by = by)
        // With the finger, past its slop (the first of its moves).
        assertTrue("moved $moved of $by from $at", moved in by * 3 / 4..by)
        until("cancelled") { verification.isCompleted }
        assertTrue(outcome(verification).exceptionOrNull() is CancellationException)
        until("the page gone") { main { made[0].view.parent } == null }
    }

    @Test
    fun aPageScrolledDownScrollsUpFirstUnderADragDownAndTheSheetStays() {
        val verification = ask("<style>#tall{height:4000px}</style><div id=\"tall\"></div>")
        main { (made[0].view as WebView).evaluateJavascript("scrollTo(0,1000)", null) }
        until("scrolled down") { scrollY() > 0 }
        val scrolled = scrollY()
        val at = pageAt()
        val moved = drag()
        SystemClock.sleep(600)
        assertFalse(verification.isCompleted)
        assertEquals(0, moved)
        assertEquals(at, pageAt())
        assertTrue("from $scrolled to ${scrollY()}", scrollY() in 1 until scrolled - 400)

        // At its top, the next drag takes the sheet down.
        main { (made[0].view as WebView).evaluateJavascript("scrollTo(0,0)", null) }
        until("at the top") { scrollY() == 0 }
        drag(by = toTheFoot())
        until("cancelled") { verification.isCompleted }
        assertTrue(outcome(verification).exceptionOrNull() is CancellationException)
    }

    @Test
    fun theTokenThePagePostsEndsTheCheckAndTheSheetGoesWithItsPage() {
        val verification = ask("<div id=\"check\"></div><script>addEventListener('load',()=>setTimeout(()=>send('token','synthetic-token'),1500))</script>")
        until("answered") { verification.isCompleted }
        assertEquals("synthetic-token", outcome(verification).getOrNull())
        until("the page gone") { main { made[0].view.parent } == null }
        // Nothing but the page let through (a favicon asked for is refused).
        assertEquals(requests.toString(), listOf("GET $DEVICE_PAGE through"), requests.filter { it.endsWith(" through") })
    }

    @Test
    fun theBarAndThePageStandOnOneGroundInLightAndDark() {
        for (dark in listOf(false, true)) {
            made.clear()
            val verification = ask("<div id=\"check\"></div>", dark)
            val (left, top) = pageAt()
            val width = main { made[0].view.width }
            val height = main { made[0].view.height }
            val shot = InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()
            val ground = Integer.toHexString(pageGround(dark).toArgb())
            // The bar's trailing end, clear of its title and Cancel, and the page below its middle.
            assertEquals("bar, dark $dark", ground, Integer.toHexString(shot.getPixel(left + width - 8, top - 8)))
            assertEquals("page, dark $dark", ground, Integer.toHexString(shot.getPixel(left + width / 2, top + height * 9 / 10)))
            main { sheet.check?.cancel() }
            until("cancelled") { verification.isCompleted }
            until("the page gone") { main { made[0].view.parent } == null }
        }
    }

    private companion object {
        /** How far above the window's foot a drag to it ends, clear of the gesture bar's edge. */
        const val FOOT_MARGIN = 60
    }
}
