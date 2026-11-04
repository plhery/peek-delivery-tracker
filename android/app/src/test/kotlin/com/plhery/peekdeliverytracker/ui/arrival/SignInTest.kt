package com.plhery.peekdeliverytracker.ui.arrival

import android.os.Looper
import android.text.InputType
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.platform.ViewRootForTest
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performFirstLinkClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.width
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DeliveryAnalytics.Outcome
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.core.WebAuthenticating
import com.plhery.peekdeliverytracker.platform.NoWebAuthentication
import com.plhery.peekdeliverytracker.ui.LocalRootFade
import com.plhery.peekdeliverytracker.ui.RootHost
import com.plhery.peekdeliverytracker.ui.RootScreen
import com.plhery.peekdeliverytracker.ui.deliveries.assertAnnounced
import com.plhery.peekdeliverytracker.ui.deliveries.editorInfo
import com.plhery.peekdeliverytracker.ui.deliveries.CARD_PHONE
import com.plhery.peekdeliverytracker.ui.deliveries.FIRST_OPEN
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.MemoryDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import com.plhery.peekdeliverytracker.ui.tabs.TAB_BAR
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import java.time.Duration
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * SignInView on the real session store, its auth service a fake that answers as Supabase Auth does: the ways in each
 * build shows, the email and code steps and what they send, their errors, one run at a time, the language menu, the
 * back to the first open with the root's fade, and the demo. Nothing reaches a server.
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = CARD_PHONE)
class SignInTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(ZurichTime()).around(compose)

    private val service = FakeAuthService()
    private val defaults = MemoryDefaults()
    private val events = mutableListOf<Pair<String, Outcome?>>()
    private val opened = mutableListOf<String>()
    private val placed = mutableListOf<Rect>()
    private val opens = mutableListOf<Float>()

    /** Each screen's opacity as the root last drew it while one faded under another. */
    private val fades = mutableMapOf<RootScreen, Float>()
    private var placeholder: TextLayoutResult? = null
    private lateinit var view: View
    private val en = Localizer(AppLanguage.En, variant = "android")
    private lateinit var session: SessionStore

    /** The sign-in, as a guest with nothing followed reaches it from the first open, its text at [fontScale]. */
    private fun show(
        configuration: AppConfiguration = signInConfiguration(),
        reduceMotion: Boolean = true,
        web: WebAuthenticating = NoWebAuthentication,
        fontScale: Float = 1f,
    ) {
        compose.waitsForMaps()
        val guest = FakeDeliveries(emptyList(), AuthenticationState.Welcome)
        compose.setContent {
            val scope = rememberCoroutineScope()
            session = remember {
                signInSession(scope, configuration, service, { event, outcome -> events += event to outcome }, web, defaults)
                    .also { it.showSignIn() }
            }
            val links = remember {
                object : UriHandler {
                    override fun openUri(uri: String) {
                        opened += uri
                    }
                }
            }
            view = LocalView.current
            val density = LocalDensity.current
            CompositionLocalProvider(
                LocalUriHandler provides links,
                LocalArrivalParcel provides { rect, open ->
                    placed += rect
                    opens += open
                },
                LocalRootFade provides { screen, alpha -> fades[screen] = alpha },
                LocalSignInPlaceholder provides { placeholder = it },
                LocalDensity provides Density(density.density, fontScale),
            ) {
                RootHost(session = session, source = guest, reduceMotion = reduceMotion)
            }
        }
        compose.waitForIdle()
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag)).fetchSemanticsNodes().isNotEmpty()

    private fun text(key: String, variables: Map<String, Any> = emptyMap()) = en.text(key, variables)

    private fun typeEmail(email: String = READER_EMAIL) {
        compose.onNodeWithTag(EMAIL_FIELD).performTextInput(email)
    }

    private fun sendCode() {
        typeEmail()
        compose.onNodeWithTag(EMAIL_SEND).performClick()
        compose.waitForIdle()
    }

    private fun errorShown(): String? = compose.onAllNodes(hasTestTag(SIGN_IN_ERROR))
        .fetchSemanticsNodes().singleOrNull()?.config?.getOrNull(SemanticsProperties.Text)?.joinToString { it.text }

    private fun dp(value: Float) = value * compose.activity.resources.displayMetrics.density

    @Test
    fun aBuildWithoutAServerShowsItsNoticeInPlaceOfTheForm() {
        show(signInConfiguration(configured = false))
        assertEquals(AuthenticationState.Unconfigured, session.state)
        compose.onNodeWithText(text("auth.configTitle")).assertExists()
        compose.onNodeWithText(text("native.configurationHelp")).assertExists()
        for (tag in listOf(EMAIL_FIELD, EMAIL_OPTION, GOOGLE_TAG, OIDC_TAG)) assertFalse(tag, exists(tag))
        for (tag in listOf(AUTH_CARD, PRIVACY_LINE, AUTH_DEMO, WELCOME_BACK, LANGUAGE_MENU)) assertTrue(tag, exists(tag))
    }

    @Test
    fun emailAloneOpensItsFormAtOnce() {
        show()
        assertEquals(AuthenticationState.SignedOut, session.state)
        assertTrue(exists(EMAIL_FIELD))
        for (tag in listOf(EMAIL_OPTION, GOOGLE_TAG, OIDC_TAG)) assertFalse(tag, exists(tag))
        compose.onNodeWithText(text("auth.emailIntroShort")).assertExists()
        compose.onNodeWithText(text("auth.or")).assertDoesNotExist()
    }

    /** C-AN15: among other ways in, the form waits behind its option and rises into place as it fades in. */
    @Test
    fun otherWaysInKeepTheFormBehindItsOptionWhichOpensIt() {
        show(signInConfiguration(google = true, oidc = true), reduceMotion = false)
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.google")))
        compose.onNodeWithTag(OIDC_TAG).assert(hasText(text("auth.oidc", mapOf("provider" to "Example SSO"))))
        assertFalse(exists(EMAIL_FIELD))
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(EMAIL_OPTION).performClick()
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeBy(64)
        val rising = compose.onNodeWithTag(EMAIL_FIELD).fetchSemanticsNode().boundsInRoot.top
        compose.mainClock.advanceTimeBy(400)
        val risen = compose.onNodeWithTag(EMAIL_FIELD).fetchSemanticsNode().boundsInRoot.top
        assertTrue("Rising from below: $rising, then $risen", rising > risen + dp(4f))
        assertFalse(exists(EMAIL_OPTION))
        compose.onNodeWithText(text("auth.or")).assertExists()
    }

    /**
     * C-AN15: the option fades out where it stood as the form opens over it, in one layout animation: the room they
     * take grows from the option's, so the privacy line under them never moves up, and the option goes once faded.
     */
    @Test
    fun theOptionFadesAsTheFormOpensAndNothingUnderThemMovesUp() {
        show(signInConfiguration(google = true), reduceMotion = false)
        val start = compose.onNodeWithTag(PRIVACY_LINE).fetchSemanticsNode().boundsInRoot.top
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(EMAIL_OPTION).performClick()
        val tops = (1..20).map { frame ->
            compose.mainClock.advanceTimeByFrame()
            if (frame == 3) assertTrue("The option fading where it stood", exists(EMAIL_OPTION))
            compose.onNodeWithTag(PRIVACY_LINE).fetchSemanticsNode().boundsInRoot.top
        }
        assertTrue("Never above where it stood: $start, then $tops", tops.all { it >= start - 0.5f })
        assertTrue("Lower, the form open: $start, then $tops", tops.last() > start + dp(40f))
        assertFalse(exists(EMAIL_OPTION))
    }

    @Test
    fun theSendWaitsForAnAddressWithAnAt() {
        show()
        compose.onNodeWithTag(EMAIL_SEND).assertIsNotEnabled()
        typeEmail("reader")
        compose.onNodeWithTag(EMAIL_SEND).assertIsNotEnabled()
        compose.onNodeWithTag(EMAIL_FIELD).performTextReplacement("  Reader@Example.com ")
        compose.onNodeWithTag(EMAIL_SEND).assertIsEnabled().performClick()
        compose.waitForIdle()
        assertEquals("""{"email":"reader@example.com","create_user":true,"data":{"locale":"en"}}""", service.body("otp"))
    }

    @Test
    fun aCodeSentShowsTheCodeStepWithTheAddress() {
        show()
        sendCode()
        assertEquals("https://auth.example.test/auth/v1/otp", service.requests.single().url)
        compose.onNodeWithText(text("auth.codeIntro", mapOf("email" to READER_EMAIL))).assertExists()
        assertTrue(exists(CODE_FIELD))
        assertFalse(exists(EMAIL_FIELD))
        compose.onNodeWithTag(CODE_VERIFY).assert(hasText(text("auth.openBox"))).assertIsNotEnabled()
        assertEquals(listOf("sign-in-code-send" to Outcome.Started, "sign-in-code-send" to Outcome.Success), events)
    }

    /** C-G12: the code's numbers alone, six at most, as typed or pasted from the email. */
    @Test
    fun theCodeKeepsItsFirstSixNumbers() {
        show()
        sendCode()
        val field = compose.onNodeWithTag(CODE_FIELD)
        field.performTextInput("12a3")
        field.assertTextEquals("123", includeEditableText = true)
        compose.onNodeWithTag(CODE_VERIFY).assertIsNotEnabled()
        field.performTextReplacement("123 456")
        field.assertTextEquals("123456", includeEditableText = true)
        compose.onNodeWithTag(CODE_VERIFY).assertIsEnabled()
        field.performTextReplacement("654-321")
        field.assertTextEquals("654321", includeEditableText = true)
        field.performTextReplacement("1234567890")
        field.assertTextEquals("123456", includeEditableText = true)
    }

    /** The sign-in switches to the tabs at once: the frame after the session signs in shows the tabs where the card was. */
    /**
     * The code's keyboard is the number keyboard's password variation, digits alone as `.numberPad`'s, with no password
     * semantics, so TalkBack reads the digits typed, and autofill still offers the code. Neither field lets the
     * keyboard go fullscreen and cover the page, which the iPhone has no such mode to do.
     */
    @Test
    fun theCodeTakesDigitsAloneAndTalkBackReadsThem() {
        show()
        compose.onNodeWithTag(EMAIL_FIELD).performClick()
        assertEquals(EditorInfo.IME_FLAG_NO_FULLSCREEN, compose.editorInfo().imeOptions and EditorInfo.IME_FLAG_NO_FULLSCREEN)
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performClick()
        val info = compose.editorInfo()
        assertEquals(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD, info.inputType)
        assertEquals(EditorInfo.IME_FLAG_NO_FULLSCREEN, info.imeOptions and EditorInfo.IME_FLAG_NO_FULLSCREEN)
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        val field = compose.onNodeWithTag(CODE_FIELD).fetchSemanticsNode().config
        assertFalse("No password semantics", SemanticsProperties.Password in field)
        assertEquals(READER_CODE, field[SemanticsProperties.EditableText].text)
        assertEquals(ContentType.SmsOtpCode, field.getOrNull(SemanticsProperties.ContentType))
    }

    @Test
    fun aCodeTakenSignsInToTheTabs() {
        show()
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        val inCard = inTheCard()
        val card = pixel(inCard.first, inCard.second)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(CODE_VERIFY).performClick()
        repeat(10) { if (session.state !is AuthenticationState.SignedIn) compose.mainClock.advanceTimeByFrame() }
        compose.mainClock.advanceTimeByFrame()
        val next = pixel(inCard.first, inCard.second)
        compose.mainClock.autoAdvance = true
        compose.waitForIdle()
        val tabs = pixel(inCard.first, inCard.second)
        assertTrue("The card was there: $card, $tabs", distance(card, tabs) > 0.02f)
        assertTrue("The tabs from the frame after: $next, $tabs", distance(next, tabs) < 0.02f)
        assertEquals(READER_EMAIL, (session.state as AuthenticationState.SignedIn).user.email)
        assertEquals("""{"email":"reader@example.com","token":"123456","type":"email"}""", service.body("verify"))
        assertTrue(exists(TAB_BAR))
        assertFalse(exists(SIGN_IN))
        assertEquals(
            listOf("sign-in-code-verify" to Outcome.Started, "sign-in-complete" to Outcome.Success, "sign-in-code-verify" to Outcome.Success),
            events.drop(2),
        )
    }

    @Test
    fun aCodeExpiredOrWrongSaysSoUnderTheField() {
        service.verify = FakeAuthService.Answer.Refused()
        show()
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.onNodeWithTag(CODE_VERIFY).performClick()
        compose.waitForIdle()
        assertEquals(text("error.invalidCode"), errorShown())
        assertEquals(AuthenticationState.SignedOut, session.state)
        assertTrue(exists(CODE_FIELD))
        val field = compose.onNodeWithTag(CODE_FIELD).fetchSemanticsNode().boundsInRoot
        val error = compose.onNodeWithTag(SIGN_IN_ERROR).fetchSemanticsNode().boundsInRoot
        val button = compose.onNodeWithTag(CODE_VERIFY).fetchSemanticsNode().boundsInRoot
        assertTrue("Between the field and the button", error.top >= field.bottom && error.bottom <= button.top)
        assertEquals("sign-in-code-verify" to Outcome.Error, events.last())
    }

    @Test
    fun noConnectionSaysSoAndStaysOnTheAddress() {
        service.otp = FakeAuthService.Answer.Offline
        show()
        sendCode()
        assertEquals(text("error.connection"), errorShown())
        assertTrue(exists(EMAIL_FIELD))
        compose.onNodeWithTag(EMAIL_SEND).assertIsEnabled()
    }

    /**
     * C-ST02: one run at a time, even for two taps before the button has redrawn disabled; the keyboard's action starts
     * none. The back and the language menu stay, as the iPhone leaves them enabled.
     */
    @Test
    fun aRunUnderWayHoldsEveryOtherOneBack() {
        val held = CompletableDeferred<Unit>()
        service.held = held
        show()
        typeEmail()
        tapTwiceInOneFrame(EMAIL_SEND)
        assertEquals("Two taps, one send", 1, service.requests.size)
        compose.onNodeWithTag(EMAIL_SEND).assertIsNotEnabled().assert(hasText(text("auth.sending")))
        compose.onNodeWithTag(AUTH_DEMO).assertIsNotEnabled()
        compose.onNodeWithTag(WELCOME_BACK).assertIsEnabled()
        compose.onNodeWithTag(LANGUAGE_MENU).assertIsEnabled()
        compose.onNodeWithTag(EMAIL_FIELD).performImeAction()
        compose.waitForIdle()
        assertEquals(1, service.requests.size)
        held.complete(Unit)
        compose.waitForIdle()
        assertEquals(1, service.requests.size)
        assertTrue(exists(CODE_FIELD))
        val again = CompletableDeferred<Unit>()
        service.held = again
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        tapTwiceInOneFrame(CODE_VERIFY)
        assertEquals("Two taps, one check", 2, service.requests.size)
        compose.onNodeWithTag(CODE_VERIFY).assertIsNotEnabled().assert(hasText(text("auth.signingIn")))
        compose.onNodeWithTag(DIFFERENT_EMAIL).assertIsNotEnabled()
        compose.onNodeWithTag(AUTH_DEMO).assertIsNotEnabled()
        compose.onNodeWithTag(CODE_FIELD).performImeAction()
        compose.waitForIdle()
        assertEquals(2, service.requests.size)
        again.complete(Unit)
        compose.waitForIdle()
        assertTrue(exists(TAB_BAR))
    }

    /**
     * A send the back cuts off still goes and counts its outcome, as the iPhone's `Task` outlives its view; the
     * first open is shown meanwhile.
     */
    @Test
    fun aSendCutOffByTheBackStillGoes() {
        val held = CompletableDeferred<Unit>()
        service.held = held
        show()
        sendCode()
        compose.onNodeWithTag(WELCOME_BACK).performClick()
        compose.waitForIdle()
        assertEquals(AuthenticationState.Welcome, session.state)
        assertFalse(exists(SIGN_IN))
        held.complete(Unit)
        compose.waitForIdle()
        assertEquals(1, service.requests.size)
        assertEquals(
            listOf("sign-in-code-send" to Outcome.Started, "sign-in-code-send" to Outcome.Success),
            events.filter { it.first == "sign-in-code-send" },
        )
        assertTrue(exists(FIRST_OPEN))
    }

    /** A code the service takes after the back signs nobody in: the session's account changed (`checkGeneration`). */
    @Test
    fun aCodeTakenAfterTheBackSignsNobodyIn() {
        show()
        sendCode()
        val held = CompletableDeferred<Unit>()
        service.held = held
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.onNodeWithTag(CODE_VERIFY).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(WELCOME_BACK).performClick()
        compose.waitForIdle()
        held.complete(Unit)
        compose.waitForIdle()
        assertEquals("https://auth.example.test/auth/v1/verify", service.requests.last().url)
        assertEquals(AuthenticationState.Welcome, session.state)
        assertTrue(exists(FIRST_OPEN))
        assertFalse(exists(SIGN_IN))
    }

    /** Google's sign-in under way says so on its button; a cancel says nothing, and a failure what the store says. */
    @Test
    fun googleSaysWhereItIsAndWhatWentWrong() {
        var answer = CompletableDeferred<String>()
        show(signInConfiguration(google = true, oidc = true), web = { _, _ -> answer.await() })
        compose.onNodeWithTag(GOOGLE_TAG).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.googleOpening"))).assertIsNotEnabled()
        compose.onNodeWithTag(OIDC_TAG).assertIsNotEnabled()
        compose.onNodeWithTag(EMAIL_OPTION).assertIsNotEnabled()
        compose.onNodeWithTag(AUTH_DEMO).assertIsNotEnabled()
        answer.completeExceptionally(AuthenticationError.OauthCancelled())
        compose.waitForIdle()
        assertNull(errorShown())
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.google"))).assertIsEnabled()
        answer = CompletableDeferred()
        compose.onNodeWithTag(GOOGLE_TAG).performClick()
        answer.completeExceptionally(IllegalStateException("synthetic"))
        compose.waitForIdle()
        assertEquals(text("error.generic"), errorShown())
        assertEquals(listOf("sign-in-google" to Outcome.Started, "sign-in-google" to Outcome.Error), events.takeLast(2))
    }

    /** An OIDC failure with only the generic message names its provider; the stand-in's failure says its own. */
    @Test
    fun theOidcProvidersFailureNamesIt() {
        var web: WebAuthenticating = WebAuthenticating { _, _ -> throw IllegalStateException("synthetic") }
        show(signInConfiguration(oidc = true), web = { url, scheme -> web.authenticate(url, scheme) })
        compose.onNodeWithTag(OIDC_TAG).performClick()
        compose.waitForIdle()
        assertEquals(text("auth.oidcFailed", mapOf("provider" to "Example SSO")), errorShown())
        web = NoWebAuthentication
        compose.onNodeWithTag(OIDC_TAG).performClick()
        compose.waitForIdle()
        assertEquals(text("native.auth.invalidResponse"), errorShown())
    }

    @Test
    fun aDifferentEmailGoesBackWithTheAddressKept() {
        service.verify = FakeAuthService.Answer.Refused()
        show()
        sendCode()
        compose.onNodeWithTag(CODE_FIELD).performTextInput(READER_CODE)
        compose.onNodeWithTag(CODE_VERIFY).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(DIFFERENT_EMAIL).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(EMAIL_FIELD).assertTextEquals(READER_EMAIL, includeEditableText = true)
        assertNull(errorShown())
        compose.onNodeWithTag(EMAIL_SEND).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(CODE_FIELD).assertTextEquals("", includeEditableText = true)
    }

    /** C-S11, C-AX06: the languages by their own names, the one in use checked; a choice changes the app's. */
    @Test
    fun theLanguageMenuChangesTheLanguage() {
        show()
        compose.onNodeWithTag(LANGUAGE_MENU)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, listOf(text("language.label"))))
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, "English"))
            .performClick()
        compose.waitForIdle()
        for (language in AppLanguage.entries) compose.onNodeWithTag(languageTag(language)).assert(hasText(language.nativeName))
        compose.onNodeWithTag(languageTag(AppLanguage.En)).assertIsSelected()
        compose.onNodeWithTag(languageTag(AppLanguage.Fr)).performClick()
        compose.waitForIdle()
        assertFalse(exists(languageTag(AppLanguage.De)))
        val fr = Localizer(AppLanguage.Fr, variant = "android")
        compose.onNodeWithText(fr.text("arrival.signInTitle")).assertExists()
        compose.onNodeWithTag(LANGUAGE_MENU).assert(SemanticsMatcher.expectValue(SemanticsProperties.StateDescription, "Français"))
        sendCode()
        assertEquals("""{"email":"reader@example.com","create_user":true,"data":{"locale":"fr"}}""", service.body("otp"))
    }

    /** C-AX02, C-AX04, C-AX05: what TalkBack reads, and what it doesn't. */
    @Test
    fun talkBackReadsTheControlsByTheirNames() {
        show(signInConfiguration(google = true))
        compose.onNodeWithTag(WELCOME_BACK)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, listOf(text("welcome.back"))))
        compose.onNodeWithTag(GOOGLE_TAG).assert(hasText(text("auth.google")))
        compose.onNodeWithTag(EMAIL_OPTION).performClick()
        compose.waitForIdle()
        compose.onNodeWithTag(EMAIL_FIELD)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.ContentDescription, listOf(text("auth.email"))))
        compose.onNodeWithText(text("auth.email")).assertDoesNotExist()
        val demo = compose.onNodeWithTag(AUTH_DEMO).fetchSemanticsNode().config
        assertEquals(listOf(text("welcome.demo")), demo[SemanticsProperties.ContentDescription])
        assertEquals(text("welcome.demoDescription"), demo[SemanticsActions.OnClick].label)
        assertEquals(0, compose.onAllNodes(hasAnyAncestor(hasTestTag(ARRIVAL_PARCEL))).fetchSemanticsNodes().size)
    }

    /** As the title's `.isHeader`: TalkBack's headings reach it. */
    @Test
    fun theTitleIsAHeading() {
        show()
        val title = compose.onNode(hasText(text("arrival.signInTitle"))).fetchSemanticsNode().config
        assertTrue(SemanticsProperties.Heading in title)
    }

    /** As `onChange(of: errorMessage)`: what went wrong is read out as it appears, where it shows under the form. */
    @Test
    fun whatWentWrongIsReadOut() {
        service.otp = FakeAuthService.Answer.Offline
        show()
        typeEmail()
        compose.assertAnnounced(text("error.connection"), SIGN_IN_ANNOUNCEMENT) {
            compose.onNodeWithTag(EMAIL_SEND).performClick()
        }
        assertEquals(text("error.connection"), errorShown())
    }

    /** As `.task(id: step)`: once the code is sent, TalkBack goes from the address's form to the code's field. */
    @Test
    fun talkBackGoesToTheCodesFieldOnceTheCodeIsSent() {
        show()
        val view = talkBack()
        sendCode()
        compose.mainClock.advanceTimeBy(1_000)
        compose.waitForIdle()
        assertTrue(compose.onNodeWithTag(CODE_FIELD).hasTalkBackFocus(view))
    }

    private fun talkBack(): View {
        val root = compose.onRoot().fetchSemanticsNode().root as ViewRootForTest
        val manager = shadowOf(compose.activity.getSystemService(AccessibilityManager::class.java))
        compose.runOnUiThread {
            manager.setEnabled(true)
            manager.setTouchExplorationEnabled(true)
            root.forceAccessibilityForTesting(true)
        }
        return root.view
    }

    private fun SemanticsNodeInteraction.hasTalkBackFocus(view: View): Boolean {
        val id = fetchSemanticsNode().id
        var focused = false
        compose.runOnUiThread { focused = view.accessibilityNodeProvider.createAccessibilityNodeInfo(id)?.isAccessibilityFocused == true }
        return focused
    }

    /** The card's width on a tablet: 365, or at the accessibility sizes the column's (520 less its margins). */
    private fun cardWidth(): Float = compose.onNodeWithTag(AUTH_CARD).getBoundsInRoot().width.value

    @Test
    @Config(qualifiers = TABLET)
    fun theCardIsItsOwnWidth() {
        show()
        assertEquals(365f, cardWidth(), 0.5f)
    }

    /** As iOS's since its largest text broke a word on the card: the width a word needs not to break. */
    @Test
    @Config(qualifiers = TABLET)
    fun atTheAccessibilitySizesTheCardTakesTheColumnsWidth() {
        show(fontScale = 2f)
        assertEquals(472f, cardWidth(), 0.5f)
    }

    /** C-L08: the privacy page in the browser. */
    @Test
    fun thePrivacyLinkOpensThePrivacyPage() {
        show()
        compose.onNodeWithTag(PRIVACY_LINE).onChildren().filterToOne(hasText(text("auth.privacyLink"), substring = true))
            .performFirstLinkClick()
        assertEquals(listOf("https://peek.example.test/privacy.html"), opened)
    }

    /** The demo comes at once: the frame after the tap shows the tabs where the card was. */
    @Test
    fun theDemoLinkEntersTheDemoAtOnce() {
        show()
        val inCard = inTheCard()
        val card = pixel(inCard.first, inCard.second)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(AUTH_DEMO).performClick()
        compose.mainClock.advanceTimeByFrame()
        val next = pixel(inCard.first, inCard.second)
        assertEquals(AuthenticationState.Demo, session.state)
        assertTrue(exists(TAB_BAR))
        assertTrue(placed.isNotEmpty())
        val drawn = placed.size
        compose.mainClock.advanceTimeByFrame()
        assertEquals("The arrival went at once", drawn, placed.size)
        assertEquals("demo-start" to null, events.last())
        assertEquals("demo", defaults.string("sdt.native.experience.v1"))
        compose.mainClock.advanceTimeBy(1_000)
        val tabs = pixel(inCard.first, inCard.second)
        assertTrue("The card was there: $card, $tabs", distance(card, tabs) > 0.02f)
        assertTrue("The tabs from the frame after: $next, $tabs", distance(next, tabs) < 0.02f)
    }

    /**
     * C-AN11: the back's fade (an ease in and out of 0.45 s): the arrival half gone and the first open half in at
     * 0.225 s, the arrival gone at 0.45 s. The sign-in goes in the first frame; the parcel springs from the sign-in's
     * anchor toward the welcome's, larger and lower, and shuts on the same spring, as the arrival fades out under the
     * first open, and goes with it.
     */
    @Test
    fun theBackFadesToTheFirstOpenAsTheParcelSpringsTowardTheWelcome() {
        show(reduceMotion = false)
        assertEquals("account", defaults.string("sdt.native.experience.v1"))
        val anchor = placed.last()
        val card = compose.onNodeWithTag(AUTH_CARD).fetchSemanticsNode().boundsInRoot
        val inCard = Pair(card.left + dp(6f), card.center.y)
        val page = pixel(card.left - dp(6f), card.center.y)
        assertTrue("The card's paper", distance(pixel(inCard.first, inCard.second), page) > 0.02f)
        assertEquals(dp(160f), anchor.width, 1f)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(WELCOME_BACK).performClick()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(AuthenticationState.Welcome, session.state)
        assertNull(defaults.string("sdt.native.experience.v1"))
        assertTrue(exists(FIRST_OPEN))
        assertFalse(exists(SIGN_IN))
        assertTrue("The card is gone at once", distance(pixel(inCard.first, inCard.second), page) < 0.02f)
        val start = placed.last()
        assertEquals(anchor.center.y, start.center.y, dp(8f))
        assertEquals("Open as it leaves", 1f, opens.last(), 0.02f)
        compose.mainClock.advanceTimeBy(64)
        val rising = fades.getValue(RootScreen.Tabs)
        compose.mainClock.advanceTimeBy(176)
        val mid = placed.last()
        assertTrue("Growing toward the welcome's 300: ${mid.width}", mid.width > anchor.width + dp(20f) && mid.width < dp(296f))
        assertTrue("Lower", mid.center.y > anchor.center.y + dp(10f))
        assertEquals("The arrival half gone", 0.5f, fades.getValue(RootScreen.Arrival), 0.08f)
        val half = fades.getValue(RootScreen.Tabs)
        assertEquals("The first open half in", 0.5f, half, 0.08f)
        assertTrue("Rising: $rising, then $half", rising < half - 0.2f)
        assertTrue("Shutting: ${opens.last()}", opens.last() in 0.25f..0.65f)
        compose.mainClock.advanceTimeBy(240)
        assertTrue("Faded out: ${fades[RootScreen.Arrival]}", fades.getValue(RootScreen.Arrival) < 0.03f)
        assertTrue("All but shut: ${opens.last()}", opens.last() < 0.1f)
        val drawn = placed.size
        compose.mainClock.advanceTimeByFrame()
        compose.mainClock.advanceTimeByFrame()
        assertEquals("The arrival has gone", drawn, placed.size)
        assertTrue(exists(FIRST_OPEN))
    }

    /**
     * Under Reduce Motion the fade takes 0.15 s, half way at 0.075 s, and the parcel stands at the welcome's anchor,
     * shut, from its first frame, as the overlay's animation is none there.
     */
    @Test
    fun theBackUnderReduceMotionFadesFasterWithoutTheSpring() {
        show(reduceMotion = true)
        compose.mainClock.autoAdvance = false
        compose.onNodeWithTag(WELCOME_BACK).performClick()
        compose.mainClock.advanceTimeByFrame()
        assertEquals(dp(300f), placed.last().width, 1f)
        assertEquals("Shut at once", 0f, opens.last(), 0.001f)
        compose.mainClock.advanceTimeBy(80)
        assertEquals("The arrival half gone", 0.5f, fades.getValue(RootScreen.Arrival), 0.15f)
        assertEquals("The first open half in", 0.5f, fades.getValue(RootScreen.Tabs), 0.15f)
        compose.mainClock.advanceTimeBy(80)
        assertTrue("Faded out: ${fades[RootScreen.Arrival]}", fades.getValue(RootScreen.Arrival) < 0.03f)
        assertEquals(0f, opens.last(), 0.001f)
        compose.mainClock.advanceTimeBy(48)
        val drawn = placed.size
        compose.mainClock.advanceTimeByFrame()
        assertEquals(drawn, placed.size)
    }

    /** The system's back does what the back does, and never closes the app from here. */
    @Test
    fun theSystemBackGoesBackToTheFirstOpen() {
        show()
        compose.runOnUiThread { compose.activity.onBackPressedDispatcher.onBackPressed() }
        compose.waitForIdle()
        assertEquals(AuthenticationState.Welcome, session.state)
        assertTrue(exists(FIRST_OPEN))
        assertFalse(compose.activity.isFinishing)
    }

    /** Two taps on the enabled button with [tag] before it has redrawn: its action, twice in one go on the main thread. */
    private fun tapTwiceInOneFrame(tag: String) {
        val tap = checkNotNull(compose.onNodeWithTag(tag).assertIsEnabled().fetchSemanticsNode().config[SemanticsActions.OnClick].action)
        compose.runOnUiThread {
            tap()
            tap()
        }
        compose.waitForIdle()
    }

    /** A point in the card, inside its left edge, which the tabs don't draw the same. */
    private fun inTheCard(): Pair<Float, Float> {
        val card = compose.onNodeWithTag(AUTH_CARD).fetchSemanticsNode().boundsInRoot
        return Pair(card.left + dp(6f), card.center.y)
    }

    /** The window's bars: the status bar [top] points tall, the navigation bar [bottom]. */
    private fun bars(top: Int, bottom: Int) {
        compose.runOnUiThread {
            fun px(points: Int) = (points * view.resources.displayMetrics.density).toInt()
            val insets = WindowInsetsCompat.Builder()
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, px(top), 0, 0))
                .setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, px(bottom)))
                .setVisible(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars(), true)
                .build()
            ViewCompat.dispatchApplyWindowInsets(view, insets)
        }
        compose.waitForIdle()
    }

    /**
     * As iphone-03-methods-ax5: on a narrow phone at the largest text the address's placeholder shrinks to keep to
     * its one line, whole, as UITextField's does.
     */
    @Test
    @Config(qualifiers = "w360dp-h640dp-xxhdpi")
    fun atTheLargestTextThePlaceholderShrinksToItsLine() {
        show(fontScale = 2f)
        val layout = checkNotNull(placeholder)
        assertEquals("you@example.com", layout.layoutInput.text.text)
        assertEquals(1, layout.lineCount)
        assertEquals("Whole", layout.layoutInput.text.length, layout.getLineEnd(0))
        assertFalse("Clipped: ${layout.multiParagraph.width} in ${layout.size.width}", layout.hasVisualOverflow)
    }

    /**
     * On a phone with three-button navigation, the page scrolled to its end clears the navigation bar, as the iPhone's
     * ScrollView clears the home indicator: the demo, the page's last item, ends above it.
     */
    @Test
    @Config(qualifiers = "w360dp-h640dp-xxhdpi")
    fun scrolledToItsEndThePageClearsTheNavigationBar() {
        show(fontScale = 2f)
        bars(top = 24, bottom = 48)
        compose.onNodeWithTag(SIGN_IN).performSemanticsAction(SemanticsActions.ScrollBy) { it(0f, 100_000f) }
        compose.waitForIdle()
        val window = compose.onRoot().fetchSemanticsNode().boundsInRoot
        val demo = compose.onNodeWithTag(AUTH_DEMO).fetchSemanticsNode().boundsInRoot
        assertTrue("Scrolled: the window is shorter than the page", demo.bottom > dp(200f))
        assertTrue("Above the bar: ${demo.bottom} in ${window.bottom}", demo.bottom <= window.bottom - dp(48f) + 0.5f)
    }

    /** The window's pixel at [x], [y], as it draws the last frame (on its looper's clock, which a frame's length brings up). */
    private fun pixel(x: Float, y: Float): Color {
        shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(17))
        return compose.onRoot().captureToImage().toPixelMap()[x.toInt(), y.toInt()]
    }

    private companion object {
        const val TABLET = "w800dp-h1100dp-xhdpi"
    }

    /** The largest difference between two colours in any channel. */
    private fun distance(a: Color, b: Color) = maxOf(kotlin.math.abs(a.red - b.red), kotlin.math.abs(a.green - b.green), kotlin.math.abs(a.blue - b.blue))
}
