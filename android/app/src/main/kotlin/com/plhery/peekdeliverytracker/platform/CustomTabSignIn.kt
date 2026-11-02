package com.plhery.peekdeliverytracker.platform

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsIntent
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
 * ASWebAuthenticationSession's stand-in (`startWebAuthentication`): the sign-in page in a Custom Tab, opened from the
 * window in front over a window of its own that shows nothing ([SignInTabActivity]), which ends the call as it comes
 * back to the top: with the address the page sent the browser to (`peekdeliverytracker://auth-callback`, which
 * [SignInCallbackActivity] brings it), or cancelled (`.canceledLogin`) when the tab closed without one. The browser
 * keeps its cookies, as the iPhone's session does (`prefersEphemeralWebBrowserSession = false`). No browser to open the
 * page in, or no window on screen to open it from, ends the call as an invalid response, as a session that can't
 * `start()` does; so does a second call while one is under way, as the iPhone starts no second session over the first.
 * Each window registers as it is made ([attach]).
 */
class CustomTabSignIn : WebAuthenticating {
    private class Call(val id: String, val answer: CompletableDeferred<String>)

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

    override suspend fun authenticate(url: String, callbackScheme: String): String = withContext(Dispatchers.Main.immediate) {
        if (call != null) throw AuthenticationError.InvalidResponse()
        val window = windows.lastOrNull { it.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) }
            ?: throw AuthenticationError.InvalidResponse()
        val call = Call(UUID.randomUUID().toString(), CompletableDeferred())
        this@CustomTabSignIn.call = call
        try {
            window.startActivity(SignInTabActivity.opening(window, url, call.id))
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

    /** No browser could open the call `id`'s page: the session couldn't `start()`. */
    internal fun failed(id: String) = end(id) { completeExceptionally(AuthenticationError.InvalidResponse()) }

    private fun end(id: String, outcome: CompletableDeferred<String>.() -> Unit) {
        val call = call?.takeIf { it.id == id } ?: return
        this.call = null
        call.answer.outcome()
    }
}

/**
 * Web sign-in's window ([CustomTabSignIn]), in the app's task over the window that signs in, showing nothing of its own:
 * as it first comes to the top it opens the sign-in page in a Custom Tab above itself, and as it comes back to the top
 * it ends the call, with the address [SignInCallbackActivity] brought it, or cancelled when the tab closed without one
 * (Back, the tab's close button). Made again after a turn, or after the process ended under the tab, it doesn't open the
 * page again. Closed for good under the tab (a notification opened clears the task above the app's window), it ends the
 * call as cancelled too.
 */
class SignInTabActivity : Activity() {
    private val signIn: CustomTabSignIn get() = (application as PeekApplication).signInTab

    /** Whether the page has been opened, once for the window, kept as it is made again. */
    private var opened = false

    /** The address the sign-in ended on, which the callback brought. */
    private var address: String? = null

    /** The call this window opened the page for. */
    private val call: String? get() = intent.getStringExtra(CALL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        opened = savedInstanceState?.getBoolean(OPENED) ?: false
        // Made by the callback itself, when no window of the call's was left to bring forward.
        take(intent)
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
            else -> signIn.closed(call)
        }
        finish()
    }

    private fun open(url: String): Boolean = try {
        CustomTabsIntent.Builder()
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .build()
            .launchUrl(this, Uri.parse(url))
        true
    } catch (_: ActivityNotFoundException) {
        false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(OPENED, opened)
    }

    override fun onDestroy() {
        if (isFinishing) call?.let(signIn::closed)
        super.onDestroy()
    }

    internal companion object {
        private const val URL = "url"
        private const val CALL = "call"
        private const val OPENED = "opened"

        /** The window that opens `url` for the call `call`. */
        fun opening(context: Context, url: String, call: String): Intent =
            Intent(context, SignInTabActivity::class.java).putExtra(URL, url).putExtra(CALL, call)

        /**
         * The call's window brought back to the top with the address the sign-in ended on, the tab above it closed
         * (`FLAG_ACTIVITY_CLEAR_TOP`), in the app's task wherever the browser sent the address from.
         */
        fun answering(context: Context, address: Uri, call: String): Intent =
            Intent(context, SignInTabActivity::class.java).setData(address).putExtra(CALL, call).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK,
            )
    }
}

/**
 * Where the sign-in page sends the browser back (`OAuthFlow.callbackUrl`, ASWebAuthenticationSession's
 * `callbackURLScheme`): it hands the address to the call under way's window ([SignInTabActivity]) and closes, showing
 * nothing and leaving nothing in Recents. An address that comes while no call waits is dropped, and none is a link the
 * app opens or counts.
 */
class SignInCallbackActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val address = intent?.takeIf { it.action == Intent.ACTION_VIEW }?.data
            ?.takeIf { it.scheme.equals(OAuthFlow.callbackScheme, ignoreCase = true) && it.host.equals(OAuthFlow.callbackHost, ignoreCase = true) }
        val call = (application as PeekApplication).signInTab.waiting
        if (savedInstanceState == null && address != null && call != null) {
            startActivity(SignInTabActivity.answering(this, address, call))
        }
        finish()
    }
}
