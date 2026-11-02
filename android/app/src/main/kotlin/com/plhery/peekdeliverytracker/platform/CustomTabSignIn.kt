package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.OAuthFlow
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import java.util.UUID
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ASWebAuthenticationSession's stand-in (`startWebAuthentication`): the sign-in page in the browser's Auth Tab, or in a
 * Custom Tab where the browser has none, opened from the window in front over a window of its own that shows nothing
 * ([SignInTabActivity]), which ends the call: with the address the page sent the browser to
 * (`peekdeliverytracker://auth-callback`, which the Auth Tab hands back itself and [SignInCallbackActivity] brings from
 * a Custom Tab), or cancelled (`.canceledLogin`) when the tab closed without one. The browser keeps its cookies, as the
 * iPhone's session does (`prefersEphemeralWebBrowserSession = false`). No browser to open the page in, or no window on
 * screen to open it from, ends the call as an invalid response, as a session that can't `start()` does; so does a
 * second call while one is under way, as the iPhone starts no second session over the first. Each window registers as
 * it is made ([attach]), and the page opens over the one that came to the top last ([cameToTop]).
 */
class CustomTabSignIn : WebAuthenticating {
    private class Call(val id: String, val answer: CompletableDeferred<String>)

    /** The windows on hand, the one that came to the top last at the end. */
    private val windows = mutableListOf<ComponentActivity>()
    private var call: Call? = null

    /** The window `activity`, which the sign-in can open over while it is on screen; in its `onCreate`. */
    fun attach(activity: ComponentActivity) {
        windows += activity
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                windows -= activity
            }
        })
    }

    /**
     * The window `activity` has become the top resumed one (`onTopResumedActivityChanged`): the one a tap reaches, so
     * the sign-in opens over it rather than over another window of the app's beside it (the iPhone's key window).
     */
    fun cameToTop(activity: ComponentActivity) {
        if (windows.remove(activity)) windows += activity
    }

    /** The window the sign-in opens over: the one on screen that came to the top last. */
    internal val front: ComponentActivity? get() = windows.lastOrNull { it.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) }

    override suspend fun authenticate(url: String, callbackScheme: String): String = withContext(Dispatchers.Main.immediate) {
        if (call != null) throw AuthenticationError.InvalidResponse()
        val window = front ?: throw AuthenticationError.InvalidResponse()
        val call = Call(UUID.randomUUID().toString(), CompletableDeferred())
        this@CustomTabSignIn.call = call
        try {
            window.startActivity(SignInTabActivity.opening(window, url, callbackScheme, call.id))
            call.answer.await()
        } finally {
            // Cancelled, the call is over even while its tab stays open: what comes back to it after is dropped.
            if (this@CustomTabSignIn.call === call) this@CustomTabSignIn.call = null
        }
    }

    /** The call under way, which an address that comes back is for; null while none waits ([SignInCallbackActivity]). */
    internal val waiting: String? get() = call?.id

    /** The call `id` ends on the address the sign-in page sent the browser to. */
    internal fun answered(id: String, address: String) = end(id) { complete(address) }

    /** The call `id`'s tab closed without the address: `.canceledLogin`. */
    internal fun closed(id: String) = end(id) { completeExceptionally(AuthenticationError.OauthCancelled()) }

    /** No browser could open the call `id`'s page, or its tab answered with no address of the call's. */
    internal fun failed(id: String) = end(id) { completeExceptionally(AuthenticationError.InvalidResponse()) }

    private fun end(id: String, outcome: CompletableDeferred<String>.() -> Unit) {
        val call = call?.takeIf { it.id == id } ?: return
        this.call = null
        call.answer.outcome()
    }

    internal companion object {
        /**
         * The browser that opens the sign-in page in an Auth Tab: the phone's default browser, where it offers Custom
         * Tabs and, among them, Auth Tabs; null where the page opens in a Custom Tab instead (an older browser, one
         * without them, or no default browser chosen).
         */
        fun authTabBrowser(context: Context): String? =
            CustomTabsClient.getPackageName(context, null)?.takeIf { CustomTabsClient.isAuthTabSupported(context, it) }
    }
}

/** Whether this is the address the sign-in ends on, in `scheme` (`OAuthFlow.callbackUrl`), in any case as iOS takes it. */
private fun Uri.isSignInCallback(scheme: String): Boolean =
    this.scheme.equals(scheme, ignoreCase = true) && host.equals(OAuthFlow.callbackHost, ignoreCase = true)

/**
 * Web sign-in's window ([CustomTabSignIn]), in the app's task over the window that signs in, showing nothing of its own:
 * as it first comes to the top it opens the sign-in page above itself, in an Auth Tab where the browser has one
 * ([CustomTabSignIn.authTabBrowser]), else in a Custom Tab. The Auth Tab takes the page's way back itself and answers
 * this window, with the address (the call's answer), cancelled (Back, its close button) or failed: the call ends so.
 * It can't be set aside the way a Custom Tab can, which Chrome minimises to a corner and which then brings this window
 * back to the top as if it had closed. Over a Custom Tab, the window ends the call as it comes back to the top, with the
 * address [SignInCallbackActivity] brought it, or cancelled when the tab closed without one (Back, the tab's close
 * button). Made again after a turn, or after the process ended under the tab, it doesn't open the page again; the Auth
 * Tab's answer comes to the window made again. Closed for good under the tab (a notification opened clears the task
 * above the app's window), it ends the call as cancelled too. Brought forward with no call (a way back that came after
 * its call ended), it ends nothing and goes, closing the tab above it.
 */
class SignInTabActivity : ComponentActivity() {
    private val signIn: CustomTabSignIn get() = (application as PeekApplication).signInTab

    /** Whether the page has been opened, once for the window, kept as it is made again. */
    private var opened = false

    /**
     * Whether the page opened in an Auth Tab, which answers the window itself, rather than in a Custom Tab; kept as the
     * window is made again. The device test reads it to say which the sign-in took.
     */
    internal var inAuthTab = false
        private set

    /** The address the sign-in ended on, which the callback brought. */
    private var address: String? = null

    /**
     * The Auth Tab's answer, registered before the window starts, as its result comes to the window that opened it or
     * to the one made again in its place, which registers under the same key.
     */
    private val authTab = AuthTabIntent.registerActivityResultLauncher(this, ::answered)

    /** The call this window opened the page for. */
    private val call: String? get() = intent.getStringExtra(CALL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        opened = savedInstanceState?.getBoolean(OPENED) ?: false
        inAuthTab = savedInstanceState?.getBoolean(IN_AUTH_TAB) ?: false
        // Made by the callback itself, when no window of the call's was left to bring forward.
        take(intent)
        // Made by a way back that came with no call waiting: nothing to end.
        if (call == null) finish()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        take(intent)
    }

    private fun take(intent: Intent) {
        val address = intent.dataString ?: return
        if (intent.getStringExtra(CALL) == call) this.address = address
    }

    override fun onResume() {
        super.onResume()
        if (isFinishing) return
        val call = call
        val address = address
        when {
            call == null -> Unit
            address != null -> signIn.answered(call, address)
            !opened -> {
                opened = true
                if (open(intent.getStringExtra(URL).orEmpty())) return
                signIn.failed(call)
            }
            // The Auth Tab answers before the window resumes ([answered]): until it has, it is still open.
            inAuthTab -> return
            else -> signIn.closed(call)
        }
        finish()
    }

    private fun open(url: String): Boolean = try {
        val page = url.toUri()
        val browser = CustomTabSignIn.authTabBrowser(this)
        if (browser != null) {
            val tab = AuthTabIntent.Builder().build()
            tab.intent.setPackage(browser)
            tab.launch(authTab, page, scheme)
            inAuthTab = true
        } else {
            CustomTabsIntent.Builder()
                .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
                .build()
                .launchUrl(this, page)
        }
        true
    } catch (_: ActivityNotFoundException) {
        false
    }

    /** The scheme the call's page sends the browser back in. */
    private val scheme: String get() = intent.getStringExtra(SCHEME) ?: OAuthFlow.callbackScheme

    /**
     * The Auth Tab's answer: the address it caught (the call's, `RESULT_OK`), the tab closed without one
     * (`RESULT_CANCELED`, `.canceledLogin`), or anything else, which is an invalid response, as iOS's session failing
     * otherwise is.
     */
    private fun answered(result: AuthTabIntent.AuthResult) {
        val call = call
        val address = result.resultUri?.takeIf { result.resultCode == AuthTabIntent.RESULT_OK && it.isSignInCallback(scheme) }
        when {
            call == null -> Unit
            address != null -> signIn.answered(call, address.toString())
            result.resultCode == AuthTabIntent.RESULT_CANCELED -> signIn.closed(call)
            else -> signIn.failed(call)
        }
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(OPENED, opened)
        outState.putBoolean(IN_AUTH_TAB, inAuthTab)
    }

    override fun onDestroy() {
        if (isFinishing) call?.let(signIn::closed)
        super.onDestroy()
    }

    internal companion object {
        private const val URL = "url"
        private const val SCHEME = "scheme"
        private const val CALL = "call"
        private const val OPENED = "opened"
        private const val IN_AUTH_TAB = "inAuthTab"

        /** The window that opens `url` for the call `call`, whose page sends the browser back in `scheme`. */
        fun opening(context: Context, url: String, scheme: String, call: String): Intent =
            Intent(context, SignInTabActivity::class.java).putExtra(URL, url).putExtra(SCHEME, scheme).putExtra(CALL, call)

        /**
         * The call's window brought back to the top with the address the sign-in ended on, the tab above it closed
         * (`FLAG_ACTIVITY_CLEAR_TOP`), in the app's task wherever the browser sent the address from; with no `call`
         * when none waits, which ends nothing and still closes the tab.
         */
        fun answering(context: Context, address: Uri, call: String?): Intent =
            Intent(context, SignInTabActivity::class.java).setData(address).apply { if (call != null) putExtra(CALL, call) }.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK,
            )
    }
}

/**
 * Where the sign-in page sends a Custom Tab back (`OAuthFlow.callbackUrl`, ASWebAuthenticationSession's
 * `callbackURLScheme`; an Auth Tab takes it itself): it brings the call's window ([SignInTabActivity]) forward with the
 * address, which closes the tab above it, and closes, showing nothing and leaving nothing in Recents. An address that
 * comes while no call waits ends nothing, but still closes the tab, and none is a link the app opens or counts.
 */
class SignInCallbackActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val address = intent?.takeIf { it.action == Intent.ACTION_VIEW }?.data?.takeIf { it.isSignInCallback(OAuthFlow.callbackScheme) }
        if (savedInstanceState == null && address != null) {
            startActivity(SignInTabActivity.answering(this, address, (application as PeekApplication).signInTab.waiting))
        }
        finish()
    }
}
