package com.plhery.peekdeliverytracker.platform

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.webkit.WebViewFeature
import com.plhery.peekdeliverytracker.ui.TurnstileEvents
import com.plhery.peekdeliverytracker.ui.request
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The check's WebView client by NativeTurnstilePresenter's rules (B-D41, X-AD45): where the main frame and the page's
 * frames may go, what a frame may load on its own, a form posted to the main frame, a redirect refused and the other
 * failures that end the check, the messages that count, and nothing once the page is torn down. A WebView without the
 * message listener and the document-start script can't hold the check. On a made-up service.
 */
@RunWith(AndroidJUnit4::class)
class TurnstileClientTest {
    private val page = "https://peek.example/api/public/native/turnstile?language=de"
    private val heard = mutableListOf<String>()
    private val client = TurnstileClient(
        page,
        object : TurnstileEvents {
            override fun token(token: String) {
                heard += "token $token"
            }

            override fun failed() {
                heard += "failed"
            }
        },
    )

    private fun refuses(url: String, mainFrame: Boolean) = client.shouldOverrideUrlLoading(null, request(url, mainFrame))

    @Test
    fun theMainFrameGoesOnlyToThePageAndItsFramesOnlyToTheChecks() {
        assertFalse(refuses(page, mainFrame = true))
        assertFalse(refuses("https://peek.example/api/public/native/turnstile?language=en", mainFrame = true))
        assertTrue(refuses("https://peek.example/api/public/native/other", mainFrame = true))
        assertTrue(refuses("https://peek.example:8443/api/public/native/turnstile", mainFrame = true))
        assertTrue(refuses("http://peek.example/api/public/native/turnstile", mainFrame = true))
        assertTrue(refuses("https://challenges.cloudflare.com/turnstile/v0/frame", mainFrame = true))
        assertFalse(refuses("https://challenges.cloudflare.com/turnstile/v0/frame", mainFrame = false))
        assertFalse(refuses("about:blank", mainFrame = false))
        assertTrue(refuses("https://elsewhere.example/frame", mainFrame = false))
        assertTrue(refuses("http://challenges.cloudflare.com/frame", mainFrame = false))
    }

    @Test
    fun aRedirectOfThePageElsewhereFailsTheCheckAtOnceAsWebKitFailsTheNavigation() {
        assertFalse(client.shouldOverrideUrlLoading(null, request("https://peek.example/api/public/native/turnstile?language=en", mainFrame = true, redirect = true)))
        assertTrue(client.shouldOverrideUrlLoading(null, request("https://elsewhere.example/", mainFrame = true)))
        assertTrue(client.shouldOverrideUrlLoading(null, request("https://elsewhere.example/frame", mainFrame = false, redirect = true)))
        assertEquals(emptyList<String>(), heard)
        assertTrue(client.shouldOverrideUrlLoading(null, request("https://elsewhere.example/", mainFrame = true, redirect = true)))
        assertEquals(listOf("failed"), heard)
    }

    @Test
    fun whatAFrameLoadsOnItsOwnComesFromTheCheckOrIsRefusedEmpty() {
        assertNull(client.shouldInterceptRequest(null, request(page, mainFrame = true)))
        assertNull(client.shouldInterceptRequest(null, request("https://challenges.cloudflare.com/turnstile/v0/api.js", mainFrame = false)))
        val refused = client.shouldInterceptRequest(null, request("https://elsewhere.example/script.js", mainFrame = false))!!
        assertEquals(403, refused.statusCode)
        assertEquals(-1, refused.data.read())
    }

    @Test
    fun aFormPostedToTheMainFrameGoesOnlyToThePageAndElsewhereLeavesThePageAsItIs() {
        assertNull(client.shouldInterceptRequest(null, request(page, mainFrame = true, method = "POST")))
        val kept = client.shouldInterceptRequest(null, request("https://elsewhere.example/form", mainFrame = true, method = "POST"))!!
        assertEquals(204, kept.statusCode)
        assertEquals(-1, kept.data.read())
        assertEquals(204, client.shouldInterceptRequest(null, request("https://challenges.cloudflare.com/turnstile/v0/frame", mainFrame = true, method = "POST"))!!.statusCode)
        assertEquals(emptyList<String>(), heard)
        // Redirected there, it fails the check as the navigation's redirect does.
        assertEquals(204, client.shouldInterceptRequest(null, request("https://elsewhere.example/", mainFrame = true, redirect = true, method = "POST"))!!.statusCode)
        assertEquals(listOf("failed"), heard)
    }

    @Test
    fun theMainFramesFailedLoadAndTheRendererGoneFailTheCheckAFramesLoadDoesNot() {
        client.onReceivedError(null, request("https://challenges.cloudflare.com/turnstile/v0/frame", mainFrame = false), null)
        assertEquals(emptyList<String>(), heard)
        client.onReceivedError(null, request(page, mainFrame = true), null)
        assertEquals(listOf("failed"), heard)
        assertFalse(client.gone)
        assertTrue(client.onRenderProcessGone(null, null))
        assertTrue(client.gone)
        assertEquals(listOf("failed", "failed"), heard)
    }

    @Test
    fun onlyTheMainFramesMessagesOnThePageCountAndNothingOnceTornDown() {
        val origin = Uri.parse("https://peek.example")
        client.message(true, Uri.parse("https://elsewhere.example"), page, """{"type":"token","token":"synthetic"}""")
        client.message(false, origin, page, """{"type":"token","token":"synthetic"}""")
        client.message(true, origin, "https://peek.example/elsewhere", """{"type":"token","token":"synthetic"}""")
        client.message(true, origin, page, """{"type":"token","token":"${"a".repeat(2049)}"}""")
        client.message(true, origin, page, """{"type":"other"}""")
        client.message(true, origin, page, "not json")
        assertEquals(emptyList<String>(), heard)
        client.message(true, origin, page, """{"type":"token","token":"synthetic"}""")
        client.message(true, origin, page, """{"type":"error"}""")
        assertEquals(listOf("token synthetic", "failed"), heard)

        client.ended = true
        client.message(true, origin, page, """{"type":"error"}""")
        client.onReceivedError(null, request(page, mainFrame = true), null)
        client.onRenderProcessGone(null, null)
        assertEquals(listOf("token synthetic", "failed"), heard)
    }

    @Test
    fun aWebViewWithoutTheMessageListenerAndTheScriptCannotHoldTheCheck() {
        assertTrue(WebViewTurnstilePages.supports { true })
        assertFalse(WebViewTurnstilePages.supports { it == WebViewFeature.WEB_MESSAGE_LISTENER })
        assertFalse(WebViewTurnstilePages.supports { it == WebViewFeature.DOCUMENT_START_SCRIPT })
        assertFalse(WebViewTurnstilePages.supports { throw IllegalStateException("No WebView package") })
        // Robolectric's WebView has neither feature, as an old WebView doesn't.
        assertFalse(WebViewTurnstilePages.supported())
    }
}
