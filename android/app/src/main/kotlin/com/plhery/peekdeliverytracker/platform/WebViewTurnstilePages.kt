package com.plhery.peekdeliverytracker.platform

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.net.http.SslError
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.RenderProcessGoneDetail
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.webkit.ScriptHandler
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewCompat
import androidx.webkit.WebViewFeature
import com.plhery.peekdeliverytracker.core.NativeTurnstilePresenter
import com.plhery.peekdeliverytracker.ui.TurnstileEvents
import com.plhery.peekdeliverytracker.ui.TurnstilePage
import com.plhery.peekdeliverytracker.ui.TurnstilePages
import java.io.ByteArrayInputStream

/**
 * The browser check's page in a WebView, as the iPhone's WKWebView, by NativeTurnstilePresenter's rules
 * (core/NativeVerification.kt): it needs the WebView's message listener and document-start script, without which the
 * check fails at once.
 */
object WebViewTurnstilePages : TurnstilePages {
    override fun supported(): Boolean = runCatching {
        WebViewFeature.isFeatureSupported(WebViewFeature.WEB_MESSAGE_LISTENER) &&
            WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)
    }.getOrDefault(false)

    override fun page(context: Context, url: String, dark: Boolean, background: Int, events: TurnstileEvents): TurnstilePage =
        WebViewTurnstilePage(context, url, dark, background, events)
}

/**
 * The page in a WebView with WKWebView's defaults where they matter: JavaScript and DOM storage on, cookies kept as
 * `WKWebsiteDataStore.default()` keeps them, no file or content access, no other window and no zoom. It follows the
 * app's appearance as the iPhone's web view follows its window: the page's own `prefers-color-scheme` says how, and no
 * darkening of the WebView's stands in for a page without a dark look. The page posts through
 * `window.webkit.messageHandlers.peekVerification`, which a script defines at the start of each of its documents over
 * the listener, both for the page's origin alone.
 */
// The check is a script, and the WebView loads the service's page and the check's frames alone. The listener and the
// script are there: TurnstileSheet makes a page only where WebViewTurnstilePages.supported().
@SuppressLint("SetJavaScriptEnabled", "RequiresFeature")
private class WebViewTurnstilePage(context: Context, private val url: String, dark: Boolean, background: Int, events: TurnstileEvents) : TurnstilePage {
    // A WebView takes light or dark for the page from its context's theme (`isLightTheme`), which the app's says light.
    private val web = WebView(ContextThemeWrapper(context, if (dark) android.R.style.Theme_Material_NoActionBar else android.R.style.Theme_Material_Light_NoActionBar))
    private val client = TurnstileClient(url, events)
    private val script: ScriptHandler

    override val view: View get() = web

    init {
        web.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = false
            allowContentAccess = false
            javaScriptCanOpenWindowsAutomatically = false
            setSupportMultipleWindows(false)
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
        }
        if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
            WebSettingsCompat.setAlgorithmicDarkeningAllowed(web.settings, false)
        }
        CookieManager.getInstance().setAcceptCookie(true)
        web.setBackgroundColor(background)
        web.webViewClient = client
        val origins = setOf(origin(url))
        WebViewCompat.addWebMessageListener(web, BRIDGE, origins) { view, message, sourceOrigin, isMainFrame, _ ->
            // The frame's document, which Android doesn't give: the main frame's, the only one whose messages count.
            client.message(isMainFrame, sourceOrigin, view.url, message.data)
        }
        script = WebViewCompat.addDocumentStartJavaScript(web, BRIDGE_SCRIPT, origins)
    }

    override fun load() {
        web.loadUrl(url)
    }

    override fun tearDown() {
        client.ended = true
        // A WebView whose renderer has gone takes nothing but its destruction.
        if (!client.gone) {
            web.stopLoading()
            script.remove()
            WebViewCompat.removeWebMessageListener(web, BRIDGE)
        }
        (web.parent as? ViewGroup)?.removeView(web)
        CookieManager.getInstance().flush()
        web.destroy()
    }

    private companion object {
        const val BRIDGE = "peekBridge"
        const val BRIDGE_SCRIPT =
            "window.webkit={messageHandlers:{peekVerification:{postMessage:m=>$BRIDGE.postMessage(JSON.stringify(m))}}};"

        /** The page's origin, as the listener and the script are allowed it: `https://host[:port]`. */
        fun origin(page: String): String {
            val uri = page.toUri()
            return "${uri.scheme}://${uri.host}" + if (uri.port != -1) ":${uri.port}" else ""
        }
    }
}

/**
 * The navigation delegate and message handler of the check's WebView (`decidePolicyFor`, `didFailProvisionalNavigation`,
 * `didFail`, `webViewWebContentProcessDidTerminate`, `userContentController(_:didReceive:)`): what it may load, the
 * messages that count and what fails the check, by NativeTurnstilePresenter's rules. Nothing it hears once the page is
 * torn down ([ended]) reaches the check.
 */
// It handles its renderer gone; the check takes the header's call to WebViewClient() for a client that doesn't.
@SuppressLint("MissingOnRenderProcessGone")
internal class TurnstileClient(private val page: String, private val events: TurnstileEvents) : WebViewClient() {
    /** The page has been torn down. */
    @Volatile var ended = false

    /** The page's renderer has gone. */
    var gone = false
        private set

    /** A message the page posted, from a frame at `sourceOrigin`, while its main frame's document is at `frameUrl`. */
    fun message(isMainFrame: Boolean, sourceOrigin: Uri, frameUrl: String?, data: String?) {
        if (ended) return
        val answer = NativeTurnstilePresenter.answer(
            page, isMainFrame, sourceOrigin.scheme.orEmpty(), sourceOrigin.host.orEmpty(), frameUrl, NativeTurnstilePresenter.body(data),
        )
        when (answer) {
            is NativeTurnstilePresenter.Answer.Token -> events.token(answer.token)
            NativeTurnstilePresenter.Answer.Failed -> events.failed()
            null -> Unit
        }
    }

    /** The main frame goes only to the page; a frame of the page only to the check's frames. */
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean =
        !NativeTurnstilePresenter.allows(page, request.url.toString(), request.isForMainFrame)

    /**
     * What a frame loads on its own, which [shouldOverrideUrlLoading] doesn't see (a frame's first document, for one):
     * refused with an empty 403 unless a frame of the page may load it.
     */
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse? {
        if (request.isForMainFrame || NativeTurnstilePresenter.allows(page, request.url.toString(), false)) return null
        return WebResourceResponse("text/plain", "utf-8", REFUSED, "Forbidden", emptyMap(), ByteArrayInputStream(ByteArray(0)))
    }

    /** The main frame's load failed, before its document came or after. */
    override fun onReceivedError(view: WebView?, request: WebResourceRequest, error: WebResourceError?) {
        if (request.isForMainFrame) fail()
    }

    /** A certificate refused, as WebKit refuses it, fails the check where it is the page's. */
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError) {
        handler.cancel()
        if (NativeTurnstilePresenter.allows(page, error.url, true)) fail()
    }

    /** The renderer gone: the WebView leaves the sheet at once, as it may no longer draw, and the check fails. */
    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        gone = true
        (view?.parent as? ViewGroup)?.removeView(view)
        fail()
        return true
    }

    private fun fail() {
        if (!ended) events.failed()
    }

    private companion object {
        const val REFUSED = 403
    }
}
