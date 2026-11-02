package com.plhery.peekdeliverytracker.platform

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.RenderProcessGoneDetail
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
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
 * (core/NativeVerification.kt): it needs the WebView's message listener, without which the check fails at once, and runs
 * the script that defines the iPhone's handler over it at each document's start where the WebView can, else as each of
 * the page's documents starts.
 */
object WebViewTurnstilePages : TurnstilePages {
    override fun supported(): Boolean = supports(WebViewFeature::isFeatureSupported)

    /** Whether a WebView with `feature`s holds the check: its message listener. */
    internal fun supports(feature: (String) -> Boolean): Boolean = runCatching {
        feature(WebViewFeature.WEB_MESSAGE_LISTENER)
    }.getOrDefault(false)

    // A WebView takes light or dark for the page from its context's theme (`isLightTheme`): the one for the check's
    // `dark` here, whatever the night of the context it is made in; one from before Android 13's model from force dark
    // (turnstilePage).
    override fun page(context: Context, url: String, dark: Boolean, background: Int, events: TurnstileEvents): TurnstilePage {
        val theme = if (dark) android.R.style.Theme_Material_NoActionBar else android.R.style.Theme_Material_Light_NoActionBar
        return turnstilePage(WebView(ContextThemeWrapper(context, theme)), url, dark, background, events, WebViewTurnstileBridge)
    }
}

/**
 * What the page needs of its WebView beyond the WebView's own: WebViewCompat's message listener and document-start
 * script, which a test stands in for (Robolectric's WebView has neither).
 */
internal interface TurnstileBridge {
    /** Hears what the frames at `origins` post to `name`: the data, its frame's origin and whether that is the main frame. */
    fun listen(web: WebView, name: String, origins: Set<String>, heard: (WebView, String?, Uri, Boolean) -> Unit)

    /** Takes the listener `name` away. */
    fun stopListening(web: WebView, name: String)

    /**
     * Runs `script` at the start of each document at `origins`; what takes it away. Null where this WebView can't
     * (WebView 91, Android 12's own, has no document-start script): the page's client runs it then.
     */
    fun script(web: WebView, script: String, origins: Set<String>): (() -> Unit)?
}

// The page is made only where WebViewTurnstilePages.supported(): the listener is there.
@SuppressLint("RequiresFeature")
private object WebViewTurnstileBridge : TurnstileBridge {
    override fun listen(web: WebView, name: String, origins: Set<String>, heard: (WebView, String?, Uri, Boolean) -> Unit) =
        WebViewCompat.addWebMessageListener(web, name, origins) { view, message, sourceOrigin, isMainFrame, _ ->
            heard(view, message.data, sourceOrigin, isMainFrame)
        }

    override fun stopListening(web: WebView, name: String) = WebViewCompat.removeWebMessageListener(web, name)

    override fun script(web: WebView, script: String, origins: Set<String>): (() -> Unit)? =
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            WebViewCompat.addDocumentStartJavaScript(web, script, origins)::remove
        } else {
            null
        }
}

/**
 * The page at `url` in `web`, with WKWebView's defaults where they matter: JavaScript and DOM storage on, cookies kept
 * as `WKWebsiteDataStore.default()` keeps them, no file or content access, no other window and no zoom, on
 * `background` until it draws. It follows the app's appearance, dark where `dark`, as the iPhone's web view follows its
 * window: the page's own `prefers-color-scheme` says how, and no darkening of the WebView's stands in for a page without
 * a dark look. A WebView from before Android 13's model (Android 12's own) says dark to the page only under force dark,
 * which asks it for the page's own dark look alone. The page posts through
 * `window.webkit.messageHandlers.peekVerification`, which a script defines over the listener, both for the page's
 * origin alone: at the start of each of its documents, or where the WebView can't, as the page's client starts and
 * finishes each ([TurnstileClient.pageScript]). Where adding the listener or the script throws, `web` is destroyed,
 * rather than left holding the window, and the error thrown.
 */
// The check is a script, and the WebView loads the service's page and the check's frames alone.
@SuppressLint("SetJavaScriptEnabled")
internal fun turnstilePage(
    web: WebView,
    url: String,
    dark: Boolean,
    background: Int,
    events: TurnstileEvents,
    bridge: TurnstileBridge,
): TurnstilePage {
    val client = TurnstileClient(url, events)
    try {
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
        // The framework's setting from Android 13, which WebSettingsCompat's leaves to the WebView's own.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            web.settings.isAlgorithmicDarkeningAllowed = false
        } else if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
            WebSettingsCompat.setAlgorithmicDarkeningAllowed(web.settings, false)
        } else {
            // A WebView from before that model (Android 12's own): dark under force dark, the page's own dark look alone.
            @Suppress("DEPRECATION")
            web.settings.forceDark = if (dark) WebSettings.FORCE_DARK_ON else WebSettings.FORCE_DARK_OFF
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK_STRATEGY)) {
                @Suppress("DEPRECATION")
                WebSettingsCompat.setForceDarkStrategy(web.settings, WebSettingsCompat.DARK_STRATEGY_WEB_THEME_DARKENING_ONLY)
            }
        }
        CookieManager.getInstance().setAcceptCookie(true)
        web.setBackgroundColor(background)
        web.webViewClient = client
        val origins = setOf(WebViewTurnstilePage.origin(url))
        bridge.listen(web, WebViewTurnstilePage.BRIDGE, origins) { view, data, sourceOrigin, isMainFrame ->
            // The frame's document, which Android doesn't give: the main frame's, the only one whose messages count.
            client.message(isMainFrame, sourceOrigin, view.url, data)
        }
        val removeScript = bridge.script(web, WebViewTurnstilePage.BRIDGE_SCRIPT, origins)
        if (removeScript == null) client.pageScript = WebViewTurnstilePage.BRIDGE_SCRIPT
        return WebViewTurnstilePage(web, url, client, bridge, removeScript)
    } catch (error: RuntimeException) {
        client.ended = true
        web.destroy()
        throw error
    }
}

/** The page in its WebView, made by [turnstilePage]. */
internal class WebViewTurnstilePage(
    private val web: WebView,
    private val url: String,
    private val client: TurnstileClient,
    private val bridge: TurnstileBridge,
    private val removeScript: (() -> Unit)?,
) : TurnstilePage {
    override val view: View get() = web

    override fun load() {
        web.loadUrl(url)
    }

    /** As the iPhone's `finish`: the load stopped, the script and the listener taken away, then out of the sheet and destroyed. */
    override fun tearDown() {
        client.ended = true
        // A WebView whose renderer has gone takes nothing but its destruction.
        if (!client.gone) {
            web.stopLoading()
            removeScript?.invoke()
            bridge.stopListening(web, BRIDGE)
        }
        (web.parent as? ViewGroup)?.removeView(web)
        CookieManager.getInstance().flush()
        web.destroy()
    }

    internal companion object {
        /** The listener's name, the object the script posts through. */
        const val BRIDGE = "peekBridge"

        /** The handler the page posts through (route.ts's `send`), forwarding each message to the listener as JSON. */
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

    /**
     * The script that defines the handler in the page where the WebView can't run it at each document's start: run in
     * the page's main frame as each of its documents starts (`onPageStarted`, which for the load the page asks of it
     * comes once it is committed) and again once it has loaded, before the page posts anything (only the check's
     * callbacks do, once Cloudflare's script has come and the check has run).
     */
    var pageScript: String? = null

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

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) = define(view, url)

    override fun onPageFinished(view: WebView?, url: String?) = define(view, url)

    /** [pageScript] run in the page's document at `url`, the page's own alone. */
    private fun define(view: WebView?, url: String?) {
        val script = pageScript ?: return
        if (!ended && url != null && NativeTurnstilePresenter.allows(page, url, true)) view?.evaluateJavascript(script, null)
    }

    /** The main frame goes only to the page; a frame of the page only to the check's frames. */
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean = refuses(request)

    /**
     * What [shouldOverrideUrlLoading] doesn't see: a frame's own loads (its first document, for one), refused with an
     * empty 403 unless a frame of the page may load them, and a navigation of the main frame by POST (a form posted to
     * it), refused with an empty 204 unless it goes to the page, which keeps the page as the iPhone's `.cancel` does.
     */
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse? {
        if (request.isForMainFrame) return if (refuses(request)) empty(NO_CONTENT, "No Content") else null
        if (NativeTurnstilePresenter.allows(page, request.url.toString(), false)) return null
        return empty(REFUSED, "Forbidden")
    }

    /**
     * Whether NativeTurnstilePresenter's rules refuse the request. A redirect of the main frame refused fails the check at
     * once, as WebKit fails the navigation whose redirect it cancels (`didFailProvisionalNavigation`), where the WebView
     * would report nothing and leave the sheet blank until the timeout.
     */
    private fun refuses(request: WebResourceRequest): Boolean {
        if (NativeTurnstilePresenter.allows(page, request.url.toString(), request.isForMainFrame)) return false
        if (request.isForMainFrame && request.isRedirect) fail()
        return true
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
        const val NO_CONTENT = 204
        const val REFUSED = 403

        fun empty(status: Int, reason: String) =
            WebResourceResponse("text/plain", "utf-8", status, reason, emptyMap(), ByteArrayInputStream(ByteArray(0)))
    }
}
