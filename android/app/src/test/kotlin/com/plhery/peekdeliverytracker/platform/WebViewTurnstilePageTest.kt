package com.plhery.peekdeliverytracker.platform

import android.content.Context
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.ui.TurnstileEvents
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

/**
 * The check's WebView as [turnstilePage] sets it up (B-D41, X-AD45), on Robolectric's WebView and a bridge standing in
 * for WebViewCompat's listener and script: WKWebView's defaults, the page's origin alone for the listener and the script,
 * the script defining the handler the service's page posts through, the teardown in the iPhone's order, and a WebView
 * whose listener or script can't be added destroyed. On a made-up service.
 */
@RunWith(AndroidJUnit4::class)
class WebViewTurnstilePageTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val page = "https://peek.example:8443/api/public/native/turnstile?language=de"
    private val log = mutableListOf<String>()
    private val heard = mutableListOf<String>()
    private val events = object : TurnstileEvents {
        override fun token(token: String) {
            heard += "token $token"
        }

        override fun failed() {
            heard += "failed"
        }
    }
    private val bridge = RecordingBridge(log)
    private val web = LoggedWebView(context, log)

    @Test
    fun theWebViewTakesWKWebViewsDefaultsThePagesGroundAndTheChecksClientAndLoadsOnlyWhenAsked() {
        val made = turnstilePage(web, page, 0xFF121212.toInt(), events, bridge)
        val settings = web.settings
        assertTrue(settings.javaScriptEnabled)
        assertTrue(settings.domStorageEnabled)
        assertFalse(settings.allowFileAccess)
        assertFalse(settings.allowContentAccess)
        assertFalse(settings.javaScriptCanOpenWindowsAutomatically)
        assertFalse(settings.supportMultipleWindows())
        assertFalse(settings.supportZoom())
        assertFalse(settings.builtInZoomControls)
        assertFalse(settings.displayZoomControls)
        assertTrue(CookieManager.getInstance().acceptCookie())
        assertEquals(0xFF121212.toInt(), shadowOf(web).backgroundColor)
        assertTrue(shadowOf(web).webViewClient is TurnstileClient)
        assertNull(shadowOf(web).lastLoadedUrl)
        made.load()
        assertEquals(page, shadowOf(web).lastLoadedUrl)
    }

    @Test
    fun theListenerAndTheScriptAreForThePagesOriginAloneItsPortIncluded() {
        turnstilePage(web, page, 0, events, bridge)
        assertEquals(setOf("https://peek.example:8443"), bridge.listenedTo)
        assertEquals(setOf("https://peek.example:8443"), bridge.scriptedFor)
        assertEquals("https://peek.example", WebViewTurnstilePage.origin("https://peek.example/api/public/native/turnstile?language=en"))
    }

    @Test
    fun theScriptDefinesTheHandlerTheServicesPagePostsThroughAndForwardsEachMessageAsJson() {
        val made = turnstilePage(web, page, 0, events, bridge)
        // The page posts each message only there (route.ts's `send`).
        val route = File(System.getProperty("user.dir")).resolve("../../app/api/public/native/turnstile/route.ts").readText()
        assertTrue(route.contains("window.webkit?.messageHandlers?.peekVerification?.postMessage({type,token})"))
        assertEquals("peekBridge", bridge.name)
        assertEquals(
            "window.webkit={messageHandlers:{peekVerification:{postMessage:m=>peekBridge.postMessage(JSON.stringify(m))}}};",
            bridge.script,
        )
        // What the listener hears reaches the check, from the main frame of the page loaded.
        made.load()
        bridge.post(web, """{"type":"token","token":"synthetic-token"}""", Uri.parse("https://peek.example:8443"), isMainFrame = true)
        assertEquals(listOf("token synthetic-token"), heard)
    }

    @Test
    fun theTeardownStopsTheLoadTakesTheScriptAndTheListenerAwayThenLeavesTheSheetAndDestroysTheWebView() {
        val made = turnstilePage(web, page, 0, events, bridge)
        FrameLayout(context).addView(web)
        made.load()
        log.clear()
        made.tearDown()
        assertEquals(listOf("stop", "script removed", "listener removed", "destroyed"), log)
        // Nothing heard after reaches the check.
        bridge.post(web, """{"type":"error"}""", Uri.parse("https://peek.example:8443"), isMainFrame = true)
        assertEquals(emptyList<String>(), heard)
    }

    @Test
    fun aWebViewWhoseRendererHasGoneIsOnlyDestroyed() {
        val made = turnstilePage(web, page, 0, events, bridge)
        FrameLayout(context).addView(web)
        (shadowOf(web).webViewClient as TurnstileClient).onRenderProcessGone(web, null)
        assertEquals(listOf("failed"), heard)
        log.clear()
        made.tearDown()
        assertEquals(listOf("destroyed"), log)
    }

    @Test
    fun aWebViewWhoseListenerOrScriptCannotBeAddedIsDestroyedAndTheErrorThrown() {
        for (failing in listOf("listen", "script")) {
            val shown = LoggedWebView(context, log)
            log.clear()
            try {
                turnstilePage(shown, page, 0, events, RecordingBridge(log, failing = failing))
                fail("Expected $failing to fail")
            } catch (_: UnsupportedOperationException) {
            }
            assertTrue(failing, shadowOf(shown).wasDestroyCalled())
            assertEquals(failing, "destroyed", log.last())
        }
        // Robolectric's WebView has neither, as an old one doesn't: the page can't be made.
        try {
            WebViewTurnstilePages.page(context, page, dark = false, background = 0, events = events)
            fail("Expected no page")
        } catch (_: RuntimeException) {
        }
        assertEquals(emptyList<String>(), heard)
    }
}

/** A WebView that notes its load stopped and its destruction, once out of any parent. */
private class LoggedWebView(context: Context, private val log: MutableList<String>) : WebView(context) {
    override fun stopLoading() {
        log += "stop"
        super.stopLoading()
    }

    override fun destroy() {
        log += if (parent == null) "destroyed" else "destroyed in a parent"
        super.destroy()
    }
}

/** WebViewCompat's listener and script as a test holds them: what was asked, and a listener it can post to. */
private class RecordingBridge(private val log: MutableList<String>, private val failing: String? = null) : TurnstileBridge {
    var name: String? = null
    var listenedTo: Set<String>? = null
    var scriptedFor: Set<String>? = null
    var script: String? = null
    private var heard: ((WebView, String?, Uri, Boolean) -> Unit)? = null

    override fun listen(web: WebView, name: String, origins: Set<String>, heard: (WebView, String?, Uri, Boolean) -> Unit) {
        if (failing == "listen") throw UnsupportedOperationException("No message listener")
        log += "listen"
        this.name = name
        listenedTo = origins
        this.heard = heard
    }

    override fun stopListening(web: WebView, name: String) {
        log += "listener removed"
    }

    override fun script(web: WebView, script: String, origins: Set<String>): () -> Unit {
        if (failing == "script") throw UnsupportedOperationException("No document-start script")
        log += "script"
        this.script = script
        scriptedFor = origins
        return { log += "script removed" }
    }

    fun post(web: WebView, data: String, origin: Uri, isMainFrame: Boolean) {
        heard?.invoke(web, data, origin, isMainFrame)
    }
}
