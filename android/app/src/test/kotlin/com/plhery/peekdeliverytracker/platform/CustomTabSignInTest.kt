package com.plhery.peekdeliverytracker.platform

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.ParcelLinkActivity
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.OAuthFlow
import com.plhery.peekdeliverytracker.ui.theme.AppAppearance
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowActivity.IntentForResult

/**
 * Web sign-in (A-L07, C-L03), as ASWebAuthenticationSession signs in on the iPhone: the page opens over a window that
 * shows nothing, in the browser's Auth Tab where it has one, which answers that window itself, and otherwise in a
 * Custom Tab, after which the call ends with the address the callback brings, or cancelled when the tab's window comes
 * back without one, or is closed for good; no browser, no window on screen and a second call end as an invalid
 * response. The callback brings the call's window forward with or without a call waiting, which closes the tab, and
 * makes one when none is left; a call cancelled waits for nothing. The tab's window made again doesn't open the page
 * twice, and still takes the Auth Tab's answer. The page opens over the window that came to the top last. On a made-up
 * authorize address that nothing opens, and a made-up browser that the package manager lists as the library looks it
 * up: Robolectric only records the intents.
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

    /**
     * A browser installed and the phone's default, as `CustomTabsClient.getPackageName` and `isAuthTabSupported` find
     * one: it opens web addresses and offers Custom Tabs, and an Auth Tab where [authTab].
     */
    private fun browser(authTab: Boolean) {
        val packages = shadowOf(app.packageManager)
        val page = ComponentName(BROWSER, "$BROWSER.Page")
        packages.addActivityIfNotPresent(page)
        packages.addIntentFilterForActivity(
            page,
            IntentFilter(Intent.ACTION_VIEW).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                addCategory(Intent.CATEGORY_BROWSABLE)
                addDataScheme("http")
                addDataScheme("https")
            },
        )
        val tabs = ComponentName(BROWSER, "$BROWSER.Tabs")
        packages.addServiceIfNotPresent(tabs)
        packages.addIntentFilterForService(
            tabs,
            IntentFilter(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION).apply { if (authTab) addCategory(CustomTabsService.CATEGORY_AUTH_TAB) },
        )
    }

    /** A sign-in under way, as SessionStore starts one. */
    private fun call(): Deferred<Result<String>> =
        scope.async { runCatching { signIn.authenticate(AUTHORIZE, OAuthFlow.callbackScheme) } }.also { idle() }

    /** The tab's window the call started, made and on top. */
    private fun tabsWindow(): ActivityController<SignInTabActivity> {
        val started = checkNotNull(shadowOf(window.get()).nextStartedActivity) { "No window started" }
        assertEquals(ComponentName(app, SignInTabActivity::class.java), started.component)
        return Robolectric.buildActivity(SignInTabActivity::class.java, started).setup()
    }

    /**
     * The tab's window the call started from the window in front, made and on top; where a browser [opens] the page,
     * what it started: the page in a Custom Tab, with nothing to share it, once. (Robolectric keeps one list of the
     * activities started, whoever started them.)
     */
    private fun tab(opens: Boolean = true): ActivityController<SignInTabActivity> {
        val tab = tabsWindow()
        if (opens) {
            val page = checkNotNull(shadowOf(tab.get()).nextStartedActivity) { "The page wasn't opened" }
            assertEquals(Intent.ACTION_VIEW, page.action)
            assertEquals(AUTHORIZE, page.dataString)
            assertTrue("A Custom Tab", page.hasExtra(CustomTabsIntent.EXTRA_SESSION))
            assertFalse("Not an Auth Tab", page.hasExtra(AuthTabIntent.EXTRA_LAUNCH_AUTH_TAB))
            assertEquals(CustomTabsIntent.SHARE_STATE_OFF, page.getIntExtra(CustomTabsIntent.EXTRA_SHARE_STATE, -1))
            assertFalse(tab.get().inAuthTab)
        }
        assertNull("Opened once", shadowOf(tab.get()).nextStartedActivity)
        return tab
    }

    /**
     * The tab's window the call started, made and on top, and the page it opened in the browser's Auth Tab, for its
     * answer, once: what the library's `launch` sends, aimed at the browser it checked. (Robolectric lists each start
     * twice, with its request code and without.)
     */
    private fun authTab(): Pair<ActivityController<SignInTabActivity>, IntentForResult> {
        val tab = tabsWindow()
        val page = checkNotNull(shadowOf(tab.get()).nextStartedActivityForResult) { "The page wasn't opened" }
        assertSame(page.intent, shadowOf(tab.get()).nextStartedActivity)
        assertNull("Opened once", shadowOf(tab.get()).nextStartedActivity)
        assertTrue("For its answer", page.requestCode >= 0)
        assertEquals(Intent.ACTION_VIEW, page.intent.action)
        assertEquals(AUTHORIZE, page.intent.dataString)
        assertEquals(BROWSER, page.intent.`package`)
        assertTrue("An Auth Tab", page.intent.getBooleanExtra(AuthTabIntent.EXTRA_LAUNCH_AUTH_TAB, false))
        assertEquals(OAuthFlow.callbackScheme, page.intent.getStringExtra(AuthTabIntent.EXTRA_REDIRECT_SCHEME))
        assertFalse("Not ephemeral, as the iPhone's session", page.intent.getBooleanExtra(EPHEMERAL, false))
        assertTrue(tab.get().inAuthTab)
        return tab to page
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

    /**
     * The Auth Tab over the window closing with `code` and `address`: its answer comes to the window, stopped under it,
     * as it starts again, before it resumes, as Android hands an activity's result.
     */
    private fun ActivityController<SignInTabActivity>.answered(page: IntentForResult, code: Int, address: String? = null) {
        if (get().lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED)) pause().stop()
        shadowOf(get()).receiveResult(page.intent, code, address?.let { Intent().setData(Uri.parse(it)) })
        restart().resume()
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
        val flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        assertEquals("The tab above it closed, the window brought back in the app's task", flags, back.flags and flags)
        tab.backOnTop(back)
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertTrue(tab.get().isFinishing)
        assertNull(signIn.waiting)
    }

    /**
     * The call's window in another task than the one the browser sends the address to, so that none is brought
     * forward: the callback makes a window of the call's, which ends it with the address and goes.
     */
    @Test
    fun theCallbackMakesTheCallsWindowWhereNoneIsBroughtForward() {
        val call = call()
        tab()
        val made = Robolectric.buildActivity(SignInTabActivity::class.java, checkNotNull(callback())).setup()
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertTrue(made.get().isFinishing)
        assertNull("It opens no page", shadowOf(made.get()).nextStartedActivity)
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

    /**
     * An address that isn't the callback's is dropped. One that comes while no call waits (the process ended under the
     * tab) still brings a window of the sign-in's forward, with no call, which closes the tab over it; made by it, that
     * window goes at once, ending nothing.
     */
    @Test
    fun aCallbackWithNoCallWaitingEndsNothingAndGoes() {
        val stray = checkNotNull(callback())
        assertEquals(ComponentName(app, SignInTabActivity::class.java), stray.component)
        val flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        assertEquals(flags, stray.flags and flags)
        val made = Robolectric.buildActivity(SignInTabActivity::class.java, stray).create()
        assertTrue("Gone at once", made.get().isFinishing)
        assertNull(shadowOf(made.get()).nextStartedActivity)

        val call = call()
        val tab = tab()
        assertNull("Not the callback's host", callback("peekdeliverytracker://elsewhere?code=synthetic"))
        assertNull("Not the callback's scheme", callback("https://auth.example.test/callback?code=synthetic"))
        tab.backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, call.outcome().getOrNull())
    }

    /**
     * A call whose coroutine is cancelled waits for nothing more. Its tab, still open, sends the address back after:
     * its window comes back to the top, ends nothing and goes, closing the tab.
     */
    @Test
    fun aCancelledCallWaitsForNothingAndItsTabStillCloses() {
        val call = call()
        val tab = tab()
        call.cancel()
        idle()
        assertNull(signIn.waiting)
        tab.backOnTop(checkNotNull(callback()))
        assertTrue(tab.get().isFinishing)

        // The next call starts afresh.
        val next = call()
        tab().backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, next.outcome().getOrNull())
    }

    /** Where the browser has an Auth Tab, the page opens there, and the address it catches is the call's answer. */
    @Test
    fun aBrowserWithAnAuthTabOpensThePageThereAndItsAddressEndsTheCall() {
        browser(authTab = true)
        assertEquals(BROWSER, CustomTabSignIn.authTabBrowser(app))
        val call = call()
        val (tab, page) = authTab()
        assertFalse(call.isCompleted)
        tab.answered(page, AuthTabIntent.RESULT_OK, CALLBACK)
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertTrue(tab.get().isFinishing)
        assertNull(signIn.waiting)
    }

    /** Back or the Auth Tab's close button: `.canceledLogin`. */
    @Test
    fun theAuthTabClosedWithoutAnAddressCancels() {
        browser(authTab = true)
        val call = call()
        val (tab, page) = authTab()
        tab.answered(page, AuthTabIntent.RESULT_CANCELED)
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
        assertTrue(tab.get().isFinishing)
    }

    /** Any other answer is an invalid response: a verification's (https addresses only), an unknown one, or no address of the call's. */
    @Test
    fun theAuthTabsOtherAnswersAreInvalidResponses() {
        browser(authTab = true)
        val answers = listOf(
            AuthTabIntent.RESULT_VERIFICATION_FAILED to null,
            AuthTabIntent.RESULT_VERIFICATION_TIMED_OUT to null,
            AuthTabIntent.RESULT_UNKNOWN_CODE to null,
            AuthTabIntent.RESULT_OK to null,
            AuthTabIntent.RESULT_OK to "https://auth.example.test/callback?code=synthetic",
            AuthTabIntent.RESULT_OK to "peekdeliverytracker://elsewhere?code=synthetic",
        )
        for ((code, address) in answers) {
            val call = call()
            val (tab, page) = authTab()
            tab.answered(page, code, address)
            assertTrue("$code $address", call.outcome().exceptionOrNull() is AuthenticationError.InvalidResponse)
            assertTrue(tab.get().isFinishing)
        }
    }

    /**
     * The window made again while the Auth Tab is open over it (a turn, a fold) opens nothing more, and takes the
     * answer, which Android hands the window in its place.
     */
    @Test
    fun theAuthTabsAnswerComesToTheWindowMadeAgain() {
        browser(authTab = true)
        val call = call()
        val (tab, page) = authTab()
        tab.pause().stop()
        tab.recreate()
        assertNull("Nothing opened again", shadowOf(tab.get()).nextStartedActivity)
        assertTrue(tab.get().inAuthTab)
        assertFalse(call.isCompleted)
        tab.answered(page, AuthTabIntent.RESULT_OK, CALLBACK)
        assertEquals(CALLBACK, call.outcome().getOrNull())
        assertTrue(tab.get().isFinishing)
    }

    /** Resumed under the Auth Tab before its answer (made again while it showed), the window waits for it. */
    @Test
    fun theWindowResumedUnderTheAuthTabWaitsForItsAnswer() {
        browser(authTab = true)
        val call = call()
        val (tab, page) = authTab()
        tab.backOnTop()
        idle()
        assertFalse(call.isCompleted)
        assertFalse(tab.get().isFinishing)
        tab.answered(page, AuthTabIntent.RESULT_CANCELED)
        assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
    }

    /** A browser with Custom Tabs and no Auth Tab (an older Chrome) opens a Custom Tab, as with no browser listed. */
    @Test
    fun aBrowserWithoutAnAuthTabOpensACustomTab() {
        browser(authTab = false)
        assertNull(CustomTabSignIn.authTabBrowser(app))
        val call = call()
        val tab = tab()
        tab.backOnTop(checkNotNull(callback()))
        assertEquals(CALLBACK, call.outcome().getOrNull())
    }

    /**
     * The page opens over the window a tap last reached, the top resumed one, rather than the one made last; one that
     * has left the screen gives way to the others.
     */
    @Test
    fun thePageOpensOverTheWindowThatCameToTheTopLast() {
        val other = Robolectric.buildActivity(ComponentActivity::class.java).setup()
        signIn.attach(other.get())
        assertSame("The one made last", other.get(), signIn.front)
        signIn.cameToTop(window.get())
        assertSame(window.get(), signIn.front)
        signIn.cameToTop(other.get())
        assertSame(other.get(), signIn.front)
        other.pause().stop()
        assertSame(window.get(), signIn.front)
        other.destroy()
        signIn.cameToTop(other.get())
        assertSame("Gone for good", window.get(), signIn.front)
    }

    /**
     * The callback's address goes to its own activity alone, never to the window or the links' trampoline, which
     * would follow or count it; that activity shows nothing and stays out of Recents, and its one filter names the
     * callback's scheme and host and nothing else. The tab's window is the app's alone, kept in the task under the tab
     * (it must come back to the top as the tab closes) and see-through.
     */
    @Test
    fun theCallbackAloneTakesItsAddressShowingNothingAndOutOfRecents() {
        val takers = app.packageManager.queryIntentActivities(
            Intent(Intent.ACTION_VIEW, Uri.parse(CALLBACK)).addCategory(Intent.CATEGORY_BROWSABLE).setPackage(app.packageName), 0,
        ).map { it.activityInfo.name }
        assertEquals(listOf(SignInCallbackActivity::class.java.name), takers)
        assertFalse(MainActivity::class.java.name in takers || ParcelLinkActivity::class.java.name in takers)

        val component = ComponentName(app, SignInCallbackActivity::class.java)
        val callback = app.packageManager.getActivityInfo(component, 0)
        assertTrue(callback.exported)
        assertEquals(android.R.style.Theme_NoDisplay, callback.theme)
        assertTrue("Left out of Recents", callback.flags and ActivityInfo.FLAG_EXCLUDE_FROM_RECENTS != 0)
        assertTrue("Gone once left", callback.flags and ActivityInfo.FLAG_NO_HISTORY != 0)
        val filter = shadowOf(app.packageManager).getIntentFiltersForActivity(component).single()
        assertEquals(listOf(Intent.ACTION_VIEW), filter.actionsIterator().asSequence().toList())
        assertEquals(setOf(Intent.CATEGORY_DEFAULT, Intent.CATEGORY_BROWSABLE), filter.categoriesIterator().asSequence().toSet())
        assertEquals(listOf(OAuthFlow.callbackScheme), filter.schemesIterator().asSequence().toList())
        assertEquals(listOf(OAuthFlow.callbackHost), filter.authoritiesIterator().asSequence().map { it.host }.toList())
        assertEquals(0, filter.countDataPaths())
        assertEquals(0, filter.countDataTypes())

        val tab = app.packageManager.getActivityInfo(ComponentName(app, SignInTabActivity::class.java), 0)
        assertFalse(tab.exported)
        assertTrue("Kept under the tab", tab.flags and ActivityInfo.FLAG_NO_HISTORY == 0)
        val theme = app.obtainStyledAttributes(tab.theme, intArrayOf(android.R.attr.windowIsTranslucent))
        try {
            assertTrue("See-through", theme.getBoolean(0, false))
        } finally {
            theme.recycle()
        }
    }

    /**
     * The tab in the app's appearance, as the iPhone's session takes its window's style: light or dark as picked, the
     * phone's for System, in a Custom Tab and in the Auth Tab alike.
     */
    @Test
    fun theTabsTakeTheAppsAppearance() {
        val store = AppearanceStore.of(app)
        val schemes = listOf(
            AppAppearance.Light to CustomTabsIntent.COLOR_SCHEME_LIGHT,
            AppAppearance.Dark to CustomTabsIntent.COLOR_SCHEME_DARK,
            AppAppearance.System to CustomTabsIntent.COLOR_SCHEME_SYSTEM,
        )
        try {
            for ((appearance, scheme) in schemes) {
                store.set(appearance)
                val call = call()
                val tab = tabsWindow()
                val page = checkNotNull(shadowOf(tab.get()).nextStartedActivity) { "The page wasn't opened" }
                assertTrue("A Custom Tab", page.hasExtra(CustomTabsIntent.EXTRA_SESSION))
                assertEquals("$appearance", scheme, page.getIntExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, -1))
                tab.backOnTop()
                assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
            }
            browser(authTab = true)
            for ((appearance, scheme) in schemes) {
                store.set(appearance)
                val call = call()
                val (tab, page) = authTab()
                assertEquals("$appearance", scheme, page.intent.getIntExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, -1))
                tab.answered(page, AuthTabIntent.RESULT_CANCELED)
                assertTrue(call.outcome().exceptionOrNull() is AuthenticationError.OauthCancelled)
            }
        } finally {
            store.set(AppAppearance.DEFAULT)
        }
    }

    private companion object {
        /** A made-up authorize address, on a host that resolves nowhere. */
        const val AUTHORIZE = "https://auth.example.test/auth/v1/authorize?provider=google&redirect_to=peekdeliverytracker%3A%2F%2Fauth-callback"

        /** What the made-up sign-in sends back. */
        const val CALLBACK = "peekdeliverytracker://auth-callback?code=synthetic-code"

        /** A made-up browser's package. */
        const val BROWSER = "com.example.browser"

        /** `setEphemeralBrowsingEnabled`'s extra, which the sign-in leaves off. */
        const val EPHEMERAL = "androidx.browser.customtabs.extra.ENABLE_EPHEMERAL_BROWSING"
    }
}
