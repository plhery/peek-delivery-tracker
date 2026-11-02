package com.plhery.peekdeliverytracker.platform

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.ParcelLinkActivity
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.OAuthFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController

/**
 * Web sign-in in a Custom Tab (A-L07, C-L03), as ASWebAuthenticationSession signs in on the iPhone: the page opens in a
 * Custom Tab over a window that shows nothing, and the call ends with the address the callback brings, or cancelled
 * when the tab's window comes back without one, or is closed for good; no browser, no window on screen and a second call
 * end as an invalid response. The callback answers nothing while no call waits, and a call cancelled waits for nothing.
 * The tab's window made again doesn't open the page twice. On a made-up authorize address that nothing opens: Robolectric
 * only records the intent.
 */
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CustomTabSignInTest {
    private val app = ApplicationProvider.getApplicationContext<PeekApplication>()
    private val signIn get() = app.signInTab
    private val scope: CoroutineScope = MainScope()
    private lateinit var window: ActivityController<ComponentActivity>

    @Before
    fun window() {
        window = Robolectric.buildActivity(ComponentActivity::class.java).setup()
        signIn.attach(window.get())
    }

    @After
    fun stop() {
        scope.cancel()
    }

    private fun idle() = shadowOf(Looper.getMainLooper()).idle()

    /** A sign-in under way, as SessionStore starts one. */
    private fun call(): Deferred<Result<String>> =
        scope.async { runCatching { signIn.authenticate(AUTHORIZE, OAuthFlow.callbackScheme) } }.also { idle() }

    /**
     * The tab's window the call started from the window in front, made and on top; where a browser [opens] the page,
     * what it started: the page in a Custom Tab, with nothing to share it, once. (Robolectric keeps one list of the
     * activities started, whoever started them.)
     */
    private fun tab(opens: Boolean = true): ActivityController<SignInTabActivity> {
        val started = checkNotNull(shadowOf(window.get()).nextStartedActivity) { "No window started" }
        assertEquals(ComponentName(app, SignInTabActivity::class.java), started.component)
        val tab = Robolectric.buildActivity(SignInTabActivity::class.java, started).setup()
        if (opens) {
            val page = checkNotNull(shadowOf(tab.get()).nextStartedActivity) { "The page wasn't opened" }
            assertEquals(Intent.ACTION_VIEW, page.action)
            assertEquals(AUTHORIZE, page.dataString)
            assertTrue("A Custom Tab", page.hasExtra(CustomTabsIntent.EXTRA_SESSION))
            assertEquals(CustomTabsIntent.SHARE_STATE_OFF, page.getIntExtra(CustomTabsIntent.EXTRA_SHARE_STATE, -1))
        }
        assertNull("Opened once", shadowOf(tab.get()).nextStartedActivity)
        return tab
    }

    /** The browser sending `address` back to the app: what the callback started, once it has gone. */
    private fun callback(address: String = CALLBACK): Intent? {
        val callback = Robolectric.buildActivity(SignInCallbackActivity::class.java, Intent(Intent.ACTION_VIEW, Uri.parse(address))).create()
        assertTrue("It finishes as it starts", callback.get().isFinishing)
        return shadowOf(callback.get()).nextStartedActivity
    }

    /**
     * The tab's window back on top, as Android brings it back over the tab it closes: paused under the tab, handed
     * `intent` if one came (Robolectric's `newIntent` alone calls `onNewIntent`), and resumed.
     */
    private fun ActivityController<SignInTabActivity>.backOnTop(intent: Intent? = null) {
        pause()
        if (intent != null) newIntent(intent)
        resume()
    }

    private fun Deferred<Result<String>>.outcome(): Result<String> {
        idle()
        assertTrue("The call is over", isCompleted)
        return getCompleted()
    }

    @Test
    fun theCallbacksAddressEndsTheCallAndClosesTheTab() {
        val call = call()
        val tab = tab()
        assertFalse(call.isCompleted)

        val back = checkNotNull(callback())
        assertEquals(ComponentName(app, SignInTabActivity::class.java), back.component)
        assertEquals(CALLBACK, back.dataString)
        val flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        assertEquals("The tab above it closed, the window brought back", flags, back.flags and flags)
        tab.backOnTop(back)
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertTrue(tab.get().isFinishing)
        assertNull(signIn.waiting)
    }

    /** Back, or the tab's close button: the tab's window comes back to the top without an address (`.canceledLogin`). */
    @Test
    fun theTabClosedWithoutAnAddressCancels() {
        val call = call()
        val tab = tab()
        tab.backOnTop()
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
        assertTrue(tab.get().isFinishing)
    }

    /** A notification opened clears the task above the app's window: the call ends cancelled. */
    @Test
    fun theTabsWindowClosedForGoodUnderTheTabCancels() {
        val call = call()
        val tab = tab()
        tab.pause().stop()
        tab.get().finish()
        tab.destroy()
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
    }

    /** Made again after a turn, it comes back to the top having opened the page once, and cancels without an address. */
    @Test
    fun theTabsWindowMadeAgainDoesNotOpenThePageAgain() {
        val call = call()
        val tab = tab()
        tab.recreate()
        assertNull(shadowOf(tab.get()).nextStartedActivity)
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
    }

    /** `start()` false: no browser to open the page in. */
    @Test
    fun noBrowserIsAnInvalidResponse() {
        shadowOf(app).checkActivities(true)
        val call = call()
        val tab = tab(opens = false)
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.InvalidResponse)
        assertTrue(tab.get().isFinishing)
    }

    @Test
    fun noWindowOnScreenIsAnInvalidResponse() {
        window.pause().stop()
        val call = call()
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.InvalidResponse)
        assertNull(shadowOf(window.get()).nextStartedActivity)
    }

    /** The iPhone starts no second session over the first: the second fails, and the first goes on to its address. */
    @Test
    fun aSecondCallWhileOneIsUnderWayIsAnInvalidResponseAndTheFirstGoesOn() {
        val first = call()
        val tab = tab()
        val second = call()
        assertTrue(second.outcome().exceptionOrNull() is AuthenticationError.InvalidResponse)
        assertNull("Nothing more opened", shadowOf(window.get()).nextStartedActivity)
        assertFalse(first.isCompleted)

        tab.backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, first.outcome().getOrNull())
    }

    /** An address that comes while no call waits, or that isn't the callback's, is dropped quietly. */
    @Test
    fun aStrayCallbackIsDropped() {
        assertNull(callback())

        val call = call()
        val tab = tab()
        assertNull("Not the callback's host", callback("peekdeliverytracker://elsewhere?code=synthetic"))
        assertNull("Not the callback's scheme", callback("https://auth.example.test/callback?code=synthetic"))
        tab.backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertNull("The call is over", callback())
    }

    /** A call whose coroutine is cancelled waits for nothing more: the tab's window then ends nothing. */
    @Test
    fun aCancelledCallWaitsForNothing() {
        val call = call()
        val tab = tab()
        call.cancel()
        idle()
        assertNull(signIn.waiting)
        assertNull(callback())
        tab.backOnTop()
        assertTrue(tab.get().isFinishing)

        // The next call starts afresh.
        val next = call()
        tab().backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, next.outcome().getOrNull())
    }

    /**
     * The callback's address goes to its own activity alone, never to the window or the links' trampoline, which
     * would follow or count it; that activity shows nothing and stays out of Recents. The tab's window is the app's alone.
     */
    @Test
    fun theCallbackAloneTakesItsAddressShowingNothingAndOutOfRecents() {
        val takers = app.packageManager.queryIntentActivities(
            Intent(Intent.ACTION_VIEW, Uri.parse(CALLBACK)).addCategory(Intent.CATEGORY_BROWSABLE).setPackage(app.packageName), 0,
        ).map { it.activityInfo.name }
        assertEquals(listOf(SignInCallbackActivity::class.java.name), takers)
        assertFalse(MainActivity::class.java.name in takers || ParcelLinkActivity::class.java.name in takers)

        val callback = app.packageManager.getActivityInfo(ComponentName(app, SignInCallbackActivity::class.java), 0)
        assertTrue(callback.exported)
        assertEquals(android.R.style.Theme_NoDisplay, callback.theme)
        assertTrue("Left out of Recents", callback.flags and ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS != 0)
        assertTrue("Gone once left", callback.flags and ActivityInfo.FLAG_NO_HISTORY != 0)
        val tab = app.packageManager.getActivityInfo(ComponentName(app, SignInTabActivity::class.java), 0)
        assertFalse(tab.exported)
        assertNotNull(tab)
    }

    private companion object {
        /** A made-up authorize address, on a host that resolves nowhere. */
        const val AUTHORIZE = "https://auth.example.test/auth/v1/authorize?provider=google&redirect_to=peekdeliverytracker%3A%2F%2Fauth-callback"

        /** What the made-up sign-in sends back. */
        const val CALLBACK = "peekdeliverytracker://auth-callback?code=synthetic-code"
    }
}
