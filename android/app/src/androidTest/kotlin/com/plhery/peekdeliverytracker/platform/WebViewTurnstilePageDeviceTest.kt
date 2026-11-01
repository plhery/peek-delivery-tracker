package com.plhery.peekdeliverytracker.platform

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.SystemClock
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.toArgb
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.webkit.WebViewCompat
import com.plhery.peekdeliverytracker.ui.TurnstileEvents
import com.plhery.peekdeliverytracker.ui.TurnstilePage
import com.plhery.peekdeliverytracker.ui.pageGround
import java.util.Collections
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The browser check's page in this device's own WebView (B-D41, X-AD45), under the server's policy, which the JVM tests
 * can only stand in for: its message listener carries what the page posts through the iPhone's
 * `window.webkit.messageHandlers.peekVerification`, from its main frame alone, defined by the document-start script or,
 * where the WebView has none (Android 12's own), by its client, before the page has loaded; its client keeps the page
 * where it is sent elsewhere, by a link or by a form; it hears nothing once torn down; it follows the app's appearance;
 * and it shows the ground the sheet's bar stands on. The page is answered on the device (PageOnDevice.kt).
 */
@RunWith(AndroidJUnit4::class)
class WebViewTurnstilePageDeviceTest {
    private val heard = LinkedBlockingQueue<String>()
    private val events = object : TurnstileEvents {
        override fun token(token: String) {
            heard += "token $token"
        }

        override fun failed() {
            heard += "failed"
        }
    }
    private val asked: MutableList<String> = Collections.synchronizedList(mutableListOf())
    private lateinit var scenario: ActivityScenario<ComponentActivity>
    private var page: TurnstilePage? = null

    @Before
    fun open() {
        scenario = ActivityScenario.launch(ComponentActivity::class.java)
    }

    @After
    fun close() {
        tearDown()
        scenario.close()
    }

    /** The check's page with `body` under `policy`, filling the window on `background`, dark where `dark`, loaded. */
    private fun show(
        body: String,
        dark: Boolean = false,
        background: Int = Color.WHITE,
        policy: String? = PAGE_POLICY,
        make: (Activity) -> TurnstilePage = { WebViewTurnstilePages.page(it, DEVICE_PAGE, dark, background, events) },
    ) {
        scenario.onActivity { activity ->
            val made = make(activity)
            answerOnDevice(made, checkPage(body), asked, policy)
            activity.setContentView(made.view)
            page = made
            made.load()
        }
    }

    private fun tearDown() {
        scenario.onActivity {
            page?.tearDown()
            page = null
        }
    }

    /**
     * `script` run a moment after the page has loaded, as the check's page posts only from its widget's callbacks, once
     * Cloudflare's script has come and the check has run.
     */
    private fun onceLoaded(script: String) = "<script>addEventListener('load',()=>setTimeout(()=>{$script},300))</script>"

    private fun next(): String? = heard.poll(10, TimeUnit.SECONDS)

    /** What is heard next, against what was expected, and where the page stands where it isn't. */
    private fun assertHeard(expected: String) {
        val got = next()
        var shown = ""
        scenario.onActivity {
            val web = page?.view as WebView?
            shown = "url ${web?.url}, title ${web?.title}, progress ${web?.progress}, asked $asked"
        }
        assertEquals(shown, expected, got)
    }

    private fun url(): String? {
        var url: String? = null
        scenario.onActivity { url = (page?.view as WebView?)?.url }
        return url
    }

    @Test
    fun thisDevicesWebViewHoldsTheCheck() {
        assertTrue(WebViewTurnstilePages.supported())
    }

    @Test
    fun aTokenThePagePostsReachesTheCheck() {
        show(onceLoaded("send('token','synthetic-token')"))
        assertHeard("token synthetic-token")
    }

    @Test
    fun aTokenPostedBeforeThePageHasLoadedReachesTheCheck() {
        // Cloudflare's callback can come before the page's load, which a script of its own holds back here.
        show("<script>setTimeout(()=>send('token',document.readyState),500)</script><script src='$SLOW_SCRIPT'></script>")
        assertHeard("token loading")
    }

    @Test
    fun whereTheWebViewCantRunAScriptAtEachDocumentsStartItsClientDefinesTheHandlerBeforeThePageHasLoaded() {
        // Android 12's own WebView, on any device: the client runs the script, before the page's load and after it.
        show(
            "<script>setTimeout(()=>send('token',document.readyState),500)</script><script src='$SLOW_SCRIPT'></script>" +
                onceLoaded("send('token','loaded')"),
        ) { turnstilePage(WebView(it), DEVICE_PAGE, false, Color.WHITE, events, ListenerAlone) }
        assertHeard("token loading")
        assertHeard("token loaded")
    }

    @Test
    fun thePageFollowsTheAppsAppearance() {
        for (dark in listOf(false, true)) {
            show(onceLoaded("send('token',matchMedia('(prefers-color-scheme: dark)').matches?'dark':'light')"), dark)
            assertHeard(if (dark) "token dark" else "token light")
            tearDown()
        }
    }

    @Test
    fun anErrorThePagePostsFailsTheCheck() {
        show(onceLoaded("send('error')"))
        assertHeard("failed")
    }

    @Test
    fun whatAFrameOfThePagePostsIsNotHeard() {
        // A frame of the page, at its origin, has the listener's object: it tells the page so, which the page posts once
        // loaded, then posts an error and a token through it, which don't count.
        val frame = "<script>parent.postMessage(typeof peekBridge,'*');" +
            "const h=window.webkit?.messageHandlers?.peekVerification??{postMessage:m=>peekBridge.postMessage(JSON.stringify(m))};" +
            "h.postMessage({type:'error'});h.postMessage({type:'token',token:'from-a-frame'})</script>"
        show("<script>let had='nothing';onmessage=e=>had=e.data</script>" + onceLoaded("send('token','frame-had-'+had)") +
            "<iframe srcdoc=\"$frame\"></iframe>")
        assertHeard("token frame-had-object")
    }

    @Test
    fun aPageSentElsewhereStaysAndIsStillHeard() {
        show("<script>location.href='https://elsewhere.example/';setTimeout(()=>send('token','still-here'),1000)</script>")
        assertHeard("token still-here")
        assertEquals(DEVICE_PAGE, url())
        assertTrue(asked.toString(), asked.none { "elsewhere" in it })
    }

    @Test
    fun aFormPostedToItsWindowKeepsThePage() {
        // Without the page's policy, whose `form-action 'none'` would stop it first: the client is the second guard.
        show(
            "<form method=\"post\" action=\"https://elsewhere.example/\"></form>" +
                "<script>document.forms[0].submit();setTimeout(()=>send('token','still-here'),1500)</script>",
            policy = null,
        )
        assertHeard("token still-here")
        assertEquals(DEVICE_PAGE, url())
        // Its client was asked, and answered it with nothing to show.
        assertTrue(asked.toString(), "POST https://elsewhere.example/ 204" in asked)
    }

    @Test
    fun aPageTornDownTellsTheCheckNothingMore() {
        show(onceLoaded("send('token','first');setTimeout(()=>send('token','after'),1500)"))
        assertHeard("token first")
        tearDown()
        assertNull(heard.poll(3, TimeUnit.SECONDS))
    }

    @Test
    fun thePagesGroundIsTheOneTheSheetsBarStandsOnInLightAndDark() {
        // A window of neither ground, so that a page not drawn yet never passes for one.
        scenario.onActivity { it.window.setBackgroundDrawable(ColorDrawable(Color.MAGENTA)) }
        for (dark in listOf(false, true)) {
            // On the ground the sheet gives it, which the page shows, or covers with a canvas of its own the same.
            show(onceLoaded("requestAnimationFrame(()=>requestAnimationFrame(()=>send('token','drawn')))"), dark, pageGround(dark).toArgb())
            assertHeard("token drawn")
            // Drawn by the page; on the screen a frame or two later, or a few more on a busy emulator.
            val expected = pageGround(dark).toArgb()
            val until = SystemClock.uptimeMillis() + 3_000
            var ground: Int
            do {
                SystemClock.sleep(200)
                ground = groundOnScreen()
            } while (ground != expected && SystemClock.uptimeMillis() < until)
            assertEquals("dark $dark", Integer.toHexString(expected), Integer.toHexString(ground))
            tearDown()
        }
    }

    /** The screen's colour below the page's middle, where nothing of it is drawn. */
    private fun groundOnScreen(): Int {
        val at = IntArray(2)
        var size = 0 to 0
        scenario.onActivity {
            page!!.view.getLocationOnScreen(at)
            size = page!!.view.width to page!!.view.height
        }
        val shot = InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()
        return shot.getPixel(at[0] + size.first / 2, at[1] + size.second * 9 / 10)
    }

    /** The bridge of a WebView with the message listener but no document-start script, as Android 12's own. */
    // Only where the listener is there: thisDevicesWebViewHoldsTheCheck.
    @SuppressLint("RequiresFeature")
    private object ListenerAlone : TurnstileBridge {
        override fun listen(web: WebView, name: String, origins: Set<String>, heard: (WebView, String?, Uri, Boolean) -> Unit) =
            WebViewCompat.addWebMessageListener(web, name, origins) { view, message, sourceOrigin, isMainFrame, _ ->
                heard(view, message.data, sourceOrigin, isMainFrame)
            }

        override fun stopListening(web: WebView, name: String) = WebViewCompat.removeWebMessageListener(web, name)

        override fun script(web: WebView, script: String, origins: Set<String>): (() -> Unit)? = null
    }
}
