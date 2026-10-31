package com.plhery.peekdeliverytracker.platform

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.SystemClock
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.RenderProcessGoneDetail
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.webkit.WebViewCompat
import com.plhery.peekdeliverytracker.ui.TurnstilePage
import java.io.ByteArrayInputStream

/** The check's address, on a made-up service: its page is answered on the device, and nothing leaves it. */
const val DEVICE_PAGE = "https://peek.example/api/public/native/turnstile?language=en"

/** A script at the page's address that comes 3 s late, holding the page's load back as Cloudflare's can. */
const val SLOW_SCRIPT = "/api/public/native/turnstile?slow"

private const val NONCE = "c3ludGhldGljLW5vbmNl"

/** route.ts's policy, with the page's nonce: its scripts and style alone run, and no form goes anywhere. */
const val PAGE_POLICY = "default-src 'none'; script-src 'nonce-$NONCE' 'strict-dynamic'; style-src 'nonce-$NONCE'; " +
    "frame-src https://challenges.cloudflare.com; connect-src 'self'; base-uri 'none'; frame-ancestors 'none'; form-action 'none'"

/**
 * A page as route.ts writes it, its style and its `send` included, with `body` where the check's widget would be, each
 * of its scripts and styles given the page's nonce (the policy takes no style attribute). The widget itself isn't
 * loaded: it comes from Cloudflare.
 */
fun checkPage(body: String): String =
    "<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\">" +
        "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><title>Peek</title>" +
        "<style nonce=\"$NONCE\">html{color-scheme:light dark}body{margin:0;display:grid;place-content:center;min-height:70vh;" +
        "font-family:system-ui}#check{min-height:65px}</style>" +
        "<script nonce=\"$NONCE\">function send(type,token){window.webkit?.messageHandlers?.peekVerification?.postMessage({type,token});}</script>" +
        "</head><body>${body.replace("<script", "<script nonce='$NONCE'").replace("<style", "<style nonce='$NONCE'")}</body></html>"

/**
 * `page`'s WebView answered on the device: its client hears what it would and decides what loads, the check's address
 * is answered with `html` under `policy`, [SLOW_SCRIPT] with nothing 3 s late, and what else it lets through gets an
 * empty 404. Each request its client is asked about lands in `asked` as "METHOD url" and what the client said: the
 * status it answered with, or "through".
 */
fun answerOnDevice(page: TurnstilePage, html: String, asked: MutableList<String>, policy: String? = PAGE_POLICY) {
    val web = page.view as WebView
    web.webViewClient = OnDevice(WebViewCompat.getWebViewClient(web), html, policy, asked)
    // What the page says in its console, in the device's log.
    web.webChromeClient = object : WebChromeClient() {
        override fun onConsoleMessage(message: ConsoleMessage): Boolean {
            Log.i("PageOnDevice", "${message.messageLevel()} ${message.message()} (${message.sourceId()}:${message.lineNumber()})")
            return true
        }
    }
}

private class OnDevice(
    private val client: WebViewClient,
    private val html: String,
    private val policy: String?,
    private val asked: MutableList<String>,
) : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) = client.onPageStarted(view, url, favicon)

    override fun onPageFinished(view: WebView?, url: String?) = client.onPageFinished(view, url)

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean =
        client.shouldOverrideUrlLoading(view, request)

    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest): WebResourceResponse? {
        client.shouldInterceptRequest(view, request)?.let {
            asked += "${request.method} ${request.url} ${it.statusCode}"
            return it
        }
        asked += "${request.method} ${request.url} through"
        if (request.isForMainFrame && request.method == "GET" && request.url.toString() == DEVICE_PAGE) {
            val headers = policy?.let { mapOf("Content-Security-Policy" to it) }.orEmpty()
            return WebResourceResponse("text/html", "utf-8", 200, "OK", headers, ByteArrayInputStream(html.toByteArray()))
        }
        // Asked off the main thread.
        if (request.url.toString().endsWith(SLOW_SCRIPT)) {
            SystemClock.sleep(3000)
            return WebResourceResponse("text/javascript", "utf-8", ByteArrayInputStream(ByteArray(0)))
        }
        return WebResourceResponse("text/plain", "utf-8", 404, "Not Found", emptyMap(), ByteArrayInputStream(ByteArray(0)))
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest, error: WebResourceError?) =
        client.onReceivedError(view, request, error)

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError) =
        client.onReceivedSslError(view, handler, error)

    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean =
        client.onRenderProcessGone(view, detail)
}
