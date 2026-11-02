package com.plhery.peekdeliverytracker.ui.arrival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * A window that shows the sign-in on [session] to a guest with nothing followed ([guest]), its runs going on in [work],
 * which outlives it as the app's scope does.
 */
class SignInWindow : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RootHost(session = session, source = guest, work = work) }
    }

    companion object {
        lateinit var session: SessionStore
        lateinit var work: CoroutineScope
        lateinit var guest: FakeDeliveries
    }
}

/**
 * SignInView's run under way lives on through the window being made again (a rotation, a fold), as the iPhone's view
 * does: the window made again shows it under way, its buttons held back, and then how it ended, its error or its next
 * step. On the real session store and a fake auth service; nothing reaches a server or opens a browser.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class SignInRunTest {
    private val compose = createEmptyComposeRule()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val service = FakeAuthService()
    private val en = Localizer(AppLanguage.En, variant = "android")
    private var answer = CompletableDeferred<String>()

    @Before
    fun start() {
        SignInWindow.work = MainScope()
        SignInWindow.guest = FakeDeliveries(emptyList(), AuthenticationState.Welcome)
    }

    @After
    fun end() {
        SignInWindow.work.cancel()
    }

    private fun window(configuration: AppConfiguration): ActivityController<SignInWindow> {
        compose.waitsForMaps()
        SignInWindow.session = signInSession(SignInWindow.work, configuration, service, webAuthentication = { _, _ -> answer.await() })
            .also { it.showSignIn() }
        return Robolectric.buildActivity(SignInWindow::class.java).setup().also { compose.waitForIdle() }
    }

    private fun ActivityController<SignInWindow>.turned() {
        recreate()
        compose.waitForIdle()
    }

    private fun text(key: String) = en.text(key)

    private fun errorShown(): String? = compose.onAllNodes(hasTestTag(SIGN_IN_ERROR))
        .fetchSemanticsNodes().singleOrNull()?.config?.getOrNull(SemanticsProperties.Text)?.joinToString { it.text }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    @Test
    fun googleUnderWayAsTheWindowTurnsStillSaysSoThenEndsOnItsErrorOrSignsIn() {
        val window = window(signInConfiguration(google = true))
        compose.onNodeWithTag(GOOGLE_TAG).performClick()
        compose.waitForIdle()
        window.turned()
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.googleOpening"))).assertIsNotEnabled()
        compose.onNodeWithTag(EMAIL_OPTION).assertIsNotEnabled()
        compose.onNodeWithTag(AUTH_DEMO).assertIsNotEnabled()
        answer.completeExceptionally(IllegalStateException("synthetic"))
        compose.waitForIdle()
        assertEquals(text("error.generic"), errorShown())
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.google"))).assertIsEnabled()

        answer = CompletableDeferred()
        compose.onNodeWithTag(GOOGLE_TAG).performClick()
        compose.waitForIdle()
        assertNull("A new run says nothing of the last", errorShown())
        window.turned()
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.googleOpening"))).assertIsNotEnabled()
        answer.complete("peekdeliverytracker://auth-callback?code=synthetic-code")
        compose.waitForIdle()
        assertTrue(SignInWindow.session.state is AuthenticationState.SignedIn)
        assertTrue(exists(TAB_BAR))
    }

    @Test
    fun anEmailUnderWayAsTheWindowTurnsStillSaysSoThenEndsOnItsErrorOrTheCode() {
        service.otp = FakeAuthService.Answer.Offline
        service.held = CompletableDeferred()
        val window = window(signInConfiguration())
        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(READER_EMAIL)
        compose.onNodeWithTag(EMAIL_SEND).performClick()
        compose.waitForIdle()
        window.turned()
        compose.onNodeWithTag(EMAIL_SEND).assert(hasText(text("auth.sending"))).assertIsNotEnabled()
        compose.onNodeWithTag(AUTH_DEMO).assertIsNotEnabled()
        checkNotNull(service.held).complete(Unit)
        compose.waitForIdle()
        assertEquals(text("error.connection"), errorShown())
        compose.onNodeWithTag(EMAIL_SEND).assertIsEnabled()
        assertFalse(exists(CODE_FIELD))

        service.otp = FakeAuthService.Answer.Accepted
        service.held = CompletableDeferred()
        compose.onNodeWithTag(EMAIL_SEND).performClick()
        compose.waitForIdle()
        window.turned()
        compose.onNodeWithTag(EMAIL_SEND).assert(hasText(text("auth.sending"))).assertIsNotEnabled()
        checkNotNull(service.held).complete(Unit)
        compose.waitForIdle()
        assertTrue("The code step", exists(CODE_FIELD))
        assertNull(errorShown())
        assertEquals(2, service.requests.size)
    }
}
