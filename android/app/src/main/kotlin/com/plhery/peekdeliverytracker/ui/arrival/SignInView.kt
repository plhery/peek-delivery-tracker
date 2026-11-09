package com.plhery.peekdeliverytracker.ui.arrival

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthenticationError
import com.plhery.peekdeliverytracker.core.SessionStore
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLanguage
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.NoSuggestions
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.deliveries.Announcement
import com.plhery.peekdeliverytracker.ui.deliveries.Announcements
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.plainDisabled
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.NoticeBanner
import com.plhery.peekdeliverytracker.ui.draw.stroke
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.MenuOption
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenu
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenus
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.LocalMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.MinimumScale
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.TextLines
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import com.plhery.peekdeliverytracker.ui.theme.widestLine
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

/** `SignInView.Step`: the ways in, then the code sent. */
internal enum class SignInStep { Methods, Code }

/**
 * SignInView's state: the step, what is typed, whether the email form is open among other ways in, the run under way
 * (`working`, with the provider it opened, [SignInRun]) and the error the last one ended on. What is typed, the step and
 * the error live on through the window being made again and after the process ended; the run lives on through the
 * window being made again, as the iPhone's view lives on through a rotation.
 */
@Stable
private class SignInForm(private val run: SignInRun, step: SignInStep, email: String, code: String, expanded: Boolean, error: String?) {
    var step by mutableStateOf(step)
    var email by mutableStateOf(email)
    var code by mutableStateOf(code)
    var emailExpanded by mutableStateOf(expanded)
    var errorMessage by mutableStateOf(error)
    val working: Boolean get() = run.working
    val activeProvider: String? get() = run.activeProvider

    companion object {
        /** The form saved without its run, and made again around `run`, the window's. */
        fun saver(run: SignInRun) = listSaver<SignInForm, Any?>(
            save = { listOf(it.step.name, it.email, it.code, it.emailExpanded, it.errorMessage) },
            restore = { SignInForm(run, SignInStep.valueOf(it[0] as String), it[1] as String, it[2] as String, it[3] as Boolean, it[4] as String?) },
        )
    }
}

/**
 * `run(provider:_:)`'s run under way, with the provider it opened. It goes on in the app's scope and outlives the window
 * (`retain`): the window made again during a rotation or a fold shows it still under way, its buttons held back, and
 * then how it ended, its error or its next step, which go to the form on screen, or to the next one shown when it ends
 * while none is. The process ending ends it.
 */
@Stable
private class SignInRun {
    var working by mutableStateOf(false)
        private set
    var activeProvider: String? by mutableStateOf(null)
        private set

    /** The form on screen, which the run's end goes to. */
    private var form: SignInForm? = null

    /** How a run ended while no form was on screen, for the next one shown. */
    private var ended: (SignInForm.() -> Unit)? = null

    fun start(provider: String?) {
        working = true
        activeProvider = provider
    }

    /** `form` is on screen, and takes how a run ended meanwhile. */
    fun show(form: SignInForm) {
        this.form = form
        ended?.let { form.it() }
        ended = null
    }

    /** `form` has left the screen. */
    fun hide(form: SignInForm) {
        if (this.form === form) this.form = null
    }

    /** The run is over: `outcome` goes to the form on screen, or to the next one. */
    fun end(outcome: SignInForm.() -> Unit) {
        working = false
        activeProvider = null
        val form = form
        if (form != null) form.outcome() else ended = outcome
    }
}

/** `cleanedEmail`: the address as it is sent, without the spaces around it and in lower case. */
internal val String.cleanedEmail: String get() = trim().lowercase()

/**
 * `codeForm`'s `onChange(of: code)`: the numbers typed or pasted, the first six of them, so that "123 456" or
 * "123-456" pasted from the email keeps its six digits (Swift's `isNumber`).
 */
internal fun signInCode(typed: String): String =
    typed.filter { it.category in NUMBERS }.take(6)

private val NUMBERS = setOf(CharCategory.DECIMAL_DIGIT_NUMBER, CharCategory.LETTER_NUMBER, CharCategory.OTHER_NUMBER)

/** What the sign-in shows of a friend's invitation: who sent it (null until known), what went wrong asking, and Retry. */
internal class SignInInvitation(val nickname: String?, val errorKey: String?, val retry: () -> Unit)

/**
 * `SignInView` (C-S04), without an invitation: the header (the back's glass circle and the language menu), 12 over the
 * parcel's anchor (160 × 165), which [onAnchor] is told of; the card (paper, corners of 24, a hairline of the separator
 * at 35 %, 22 in, 365 wide at most) with the title and subtitle, then the configuration notice where sign-in isn't set
 * up, or the code step, or the ways in; the short privacy line; and Explore the demo under it. All of it scrolls in a
 * column 520 wide at most, 24 in from the sides and 20 from the safe area's top and bottom, centred; the keyboard
 * leaves room under it, keeping the field with the focus in view, and a drag of the page takes it down with the finger
 * (`.scrollDismissesKeyboard(.interactively)`). The language menu opens in [menus].
 *
 * One run at a time (`run(provider:)`): while one is under way every way in, the form's buttons, "Use a different
 * email" and the demo are disabled, as the iPhone's are; the back and the language menu are not. A run goes on in
 * [work] once the screen has gone, as the iPhone's `Task` outlives its view: the email still goes and its outcome is
 * counted, and a code checked after the back is dropped by the session, whose account changed (`checkGeneration`).
 * The window made again while one runs shows it under way, and then how it ended ([SignInRun]).
 * A cancelled run says nothing; any other failure says `Localizer.errorMessage`'s words, an OIDC one with only the
 * generic message its own. The system's back does what the back does ([onBack]), so it never closes the app from
 * here.
 *
 * With an [invitation] (C-S02's sign-in), the anchor is 180 × 186, and the title and the ways in stand on the page
 * across the column, without the card: the invitation's heading ([InvitationHeading]) 8 down over "friends.signInToAccept",
 * then 28 down what went wrong asking who sent it, with Retry 28 under it, or a spinner while nobody is known yet;
 * "auth.configTitle" alone where sign-in isn't set up; or the code step or the ways in, 12 apart, the email option
 * without its cream, the email's longer intro, and each form on a card of its own. No short privacy line or demo:
 * the privacy notice stands 12 under it all ([PrivacyNotice]).
 */
@Composable
internal fun SignInView(
    session: SessionStore,
    scroll: ScrollState,
    menus: SheetMenus,
    work: CoroutineScope,
    onBack: () -> Unit,
    onAnchor: (LayoutCoordinates) -> Unit,
    invitation: SignInInvitation? = null,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val configuration = session.configuration
    val underWay = retain { SignInRun() }
    val form = rememberSaveable(saver = remember(underWay) { SignInForm.saver(underWay) }) {
        SignInForm(underWay, SignInStep.Methods, "", "", false, null)
    }
    DisposableEffect(underWay, form) {
        underWay.show(form)
        onDispose { underWay.hide(form) }
    }
    // What went wrong is read out as it appears (`onChange(of: errorMessage)`), and TalkBack goes to the code's field
    // once the code is sent, as the form it replaces goes (`.task(id: step)`).
    var announcement by remember { mutableStateOf<Announcement?>(null) }
    LaunchedEffect(form) {
        snapshotFlow { form.errorMessage }.drop(1).collect { message ->
            if (message != null) announcement = Announcement(message, (announcement?.count ?: 0) + 1)
        }
    }
    Announcements(announcement, SIGN_IN_ANNOUNCEMENT) { if (announcement == it) announcement = it.copy(text = "") }
    val codeFocus = remember { AccessibilityFocus() }
    LaunchedEffect(form.step) {
        if (form.step != SignInStep.Code) return@LaunchedEffect
        delay(CODE_FOCUS_DELAY)
        codeFocus.request()
    }

    /** `run(provider:_:)`, which on success does to the form on screen what [succeeded] says. */
    fun run(provider: String? = null, succeeded: SignInForm.() -> Unit = {}, operation: suspend () -> Unit) {
        if (underWay.working) return
        underWay.start(provider)
        form.errorMessage = null
        work.launch {
            var ended: SignInForm.() -> Unit = {}
            try {
                operation()
                ended = succeeded
            } catch (_: AuthenticationError.OauthCancelled) {
            } catch (cancelled: CancellationException) {
                // A change of account under way (`checkGeneration`) says nothing.
                currentCoroutineContext().ensureActive()
            } catch (error: Exception) {
                val message = localizer.errorMessage(error)
                val shown = if (provider == OIDC && message == localizer.text("error.generic")) {
                    localizer.text("auth.oidcFailed", mapOf("provider" to configuration.oidcName))
                } else {
                    message
                }
                ended = { errorMessage = shown }
            }
            underWay.end(ended)
        }
    }

    BackHandler(onBack = onBack)
    val safe = WindowInsets.systemBars.union(WindowInsets.displayCutout)
    KeyboardScrollColumn(
        modifier = Modifier.fillMaxSize().testTag(SIGN_IN),
        scroll = scroll,
        top = with(density) { safe.getTop(this).toDp() },
        bottom = with(density) { safe.getBottom(this).toDp() },
        dismissesKeyboard = true,
        contentModifier = Modifier.windowInsetsPadding(safe),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .widthIn(max = COLUMN_WIDTH)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth().padding(bottom = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                SignInBack(onBack)
                Spacer(Modifier.weight(1f))
                LanguageMenu(menus)
            }
            Box(Modifier.size(if (invitation == null) 160.dp else 180.dp, if (invitation == null) 165.dp else 186.dp).onGloballyPositioned(onAnchor))
            val card = ContinuousRoundedShape(24.dp)
            Column(
                if (invitation != null) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                        // At the accessibility sizes the card takes the width a word needs not to break.
                        .widthIn(max = if (PeekTheme.dynamicTypeSize.isAccessibilitySize) COLUMN_WIDTH else CARD_WIDTH)
                        .fillMaxWidth()
                        .testTag(AUTH_CARD)
                        .background(colors.paper, card)
                        .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.35f), card)
                        .padding(22.dp)
                },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    Modifier.fillMaxWidth().padding(top = if (invitation == null) 0.dp else 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (invitation != null) {
                        InvitationHeading(invitation.nickname)
                    } else {
                        Text(
                            localized("arrival.signInTitle"), Modifier.semantics { heading() }, color = colors.label, textAlign = TextAlign.Center,
                            style = type.tracking(type.style(IosTextStyle.Title, FontWeight.SemiBold), -0.6f).sfLines(),
                        )
                    }
                    Text(
                        localized(if (invitation == null) "arrival.signInSubtitle" else "friends.signInToAccept"), color = colors.secondaryText,
                        textAlign = TextAlign.Center, style = type.footnote.sfLines(),
                    )
                }
                val top = if (invitation == null) 23.dp else 28.dp
                when {
                    invitation != null && invitation.nickname == null -> {
                        val error = invitation.errorKey
                        if (error != null) {
                            LeadingFootnote(localized(error), Modifier.padding(top = top).testTag(SIGN_IN_INVITATION_ERROR))
                            PlainTextButton(localized("common.retry"), SIGN_IN_INVITATION_RETRY, Modifier.padding(top = top), onClick = invitation.retry)
                        } else {
                            ActivityIndicator(Modifier.padding(top = top).testTag(SIGN_IN_INVITATION_LOADING), size = 20.dp, tint = colors.secondaryLabel)
                        }
                    }
                    else -> Box(Modifier.padding(top = top)) {
                        when {
                            !configuration.authenticationConfigured -> if (invitation == null) {
                                NoticeBanner(
                                    symbol = "wrench.and.screwdriver.fill",
                                    title = localized("auth.configTitle"),
                                    message = localized("native.configurationHelp"),
                                    tint = colors.orange,
                                )
                            } else {
                                Text(localized("auth.configTitle"), color = colors.secondaryLabel, style = type.subheadline.sfLines())
                            }
                            form.step == SignInStep.Code -> CodeForm(form, invitation != null, codeFocus, onVerify = {
                                val email = form.email.cleanedEmail
                                val code = form.code
                                run { session.verifyCode(email = email, code = code) }
                            })
                            else -> Methods(session, form, invitation != null, ::run)
                        }
                    }
                }
                if (invitation == null) PrivacyLine(configuration.privacyUrl, Modifier.padding(top = 18.dp))
            }
            if (invitation == null) {
                DemoLink(enabled = !form.working, Modifier.padding(top = 12.dp)) { session.enterDemo() }
            } else {
                PrivacyNotice(configuration.privacyUrl, Modifier.padding(top = 12.dp))
            }
        }
    }
}

/**
 * The back: `chevron.left` in the body's size in the ink on a glass circle 42 across, read as `welcome.back`. The
 * chevron grows with the text past the circle, which doesn't.
 */
@Composable
private fun SignInBack(onBack: () -> Unit) {
    val colors = PeekTheme.colors
    val label = localized("welcome.back")
    GlassSurface(CircleShape, Modifier.size(BACK_SIZE), backdrop = null, behind = colors.background) {
        Box(
            Modifier
                .size(BACK_SIZE)
                .testTag(WELCOME_BACK)
                .button(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onBack)
                .semantics { contentDescription = label },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("chevron.left", font = PeekTheme.type.body, tint = colors.ink)
        }
    }
}

/**
 * `AuthenticationLanguageMenu`: the globe and the language's code in upper case (semibold caption) in the secondary
 * grey, 10 in, at least 44 high, read as the code and `language.label` with the language's own name as its value. It opens iOS's
 * menu of the languages by their own names, the one in use checked, its top at the label's and its trailing edge at
 * the label's, on glass that shows the arrival through it; a choice sets the app's language (`localizer.language`),
 * which the app saves and passes on.
 */
@Composable
internal fun LanguageMenu(menus: SheetMenus) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val label = localized("language.label")
    var bounds by remember { mutableStateOf(Rect.Zero) }
    val returnFocus = remember { AccessibilityFocus() }
    val background = colors.background
    val open = {
        menus.content = {
            SheetMenu(
                options = AppLanguage.entries.map { MenuOption(it.nativeName, tag = languageTag(it)) },
                selected = AppLanguage.entries.indexOf(language),
                row = bounds,
                trailing = bounds.right,
                onClose = {
                    menus.content = null
                    returnFocus.request()
                },
                drop = 0.dp,
                behind = background,
                backdrop = LocalMaterialBackdrop.current,
            ) { localizer.language = AppLanguage.entries[it] }
        }
    }
    Row(
        Modifier
            .onGloballyPositioned { bounds = it.boundsInWindow() }
            .testTag(LANGUAGE_MENU)
            .accessibilityFocus(returnFocus)
            .button(interactionSource = null, indication = PlainPress, role = Role.DropdownList, onClick = open)
            .clearAndSetSemantics {
                // Voice Access answers to the code it shows as well as to its name.
                contentDescription = "${language.rawValue.uppercase()}, $label"
                stateDescription = language.nativeName
                role = Role.DropdownList
                onClick {
                    open()
                    true
                }
            }
            .heightIn(min = 44.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeekSymbol("globe", font = type.subheadline, tint = colors.secondaryLabel)
        Text(language.rawValue.uppercase(), color = colors.secondaryLabel, style = type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines())
    }
}

/**
 * `methods`: Google's and the OIDC provider's buttons where they are set up (Apple's never on Android), then the email
 * form, or, among other ways in, "Sign in with email", which opens it in one layout animation (`.easeOut(duration:
 * 0.2)`, none under Reduce Motion): the option fades out where it stood as the form, under a rule with "or" in it,
 * rises into place fading in, and the room they take grows from the option's to the form's, so nothing under them
 * moves up; the error under them while the form is shut.
 */
@Composable
private fun Methods(
    session: SessionStore,
    form: SignInForm,
    invited: Boolean,
    run: (provider: String?, succeeded: SignInForm.() -> Unit, operation: suspend () -> Unit) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val configuration = session.configuration
    val social = configuration.googleAuthEnabled || configuration.oidcAuthEnabled
    val formVisible = configuration.emailOtpEnabled && (!social || form.emailExpanded)
    val spacing = if (invited) 12.dp else 9.dp
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(spacing)) {
        if (configuration.googleAuthEnabled) {
            val active = form.activeProvider == GOOGLE
            MethodButton(GOOGLE_TAG, enabled = !form.working, onClick = { run(GOOGLE, {}) { session.signInWithGoogle() } }) {
                Image(painterResource(R.drawable.google_g), contentDescription = null, Modifier.size(20.dp))
                val style = type.fixed(18f, FontWeight.Medium).sfLines()
                Text(
                    localized(if (active) "auth.googleOpening" else "auth.google"), color = colors.ink, style = style, maxLines = 1, softWrap = false,
                    autoSize = MinimumScale(style.fontSize, 0.7f),
                )
                if (active) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = SMALL_SPINNER)
            }
        }
        if (configuration.oidcAuthEnabled) {
            val active = form.activeProvider == OIDC
            val localizer = LocalLocalizer.current
            val language = LocalLanguage.current
            val title = remember(active, language, configuration.oidcName) {
                localizer.text(if (active) "auth.oidcOpening" else "auth.oidc", mapOf("provider" to configuration.oidcName))
            }
            MethodButton(OIDC_TAG, enabled = !form.working, onClick = { run(OIDC, {}) { session.signInWithOidc() } }) {
                PeekSymbol("person.badge.key.fill", Modifier.clearAndSetSemantics {}, font = type.style(IosTextStyle.Subheadline, FontWeight.Medium), tint = colors.ink)
                Text(title, color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
                if (active) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = SMALL_SPINNER)
            }
        }
        val expanding = if (reduceMotion) EnterTransition.None else {
            fadeIn(Motion.easeOut(0.2)) + slideInVertically(Motion.easeOut(0.2)) { it } +
                expandVertically(Motion.easeOut(0.2), expandFrom = Alignment.Top, clip = false)
        }
        // The option and the form share one place: the option fades out over the form's top while the form's room
        // grows past the option's, which the place keeps until then.
        if (configuration.emailOtpEnabled) Box(Modifier.fillMaxWidth()) {
            // The Box's, not the column's around it.
            androidx.compose.animation.AnimatedVisibility(
                !formVisible,
                enter = EnterTransition.None,
                exit = if (reduceMotion) ExitTransition.None else fadeOut(Motion.easeOut(0.2)),
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .testTag(EMAIL_OPTION)
                        .button(interactionSource = null, indication = PressStyle.Tactile, enabled = !form.working && !form.emailExpanded, role = Role.Button) {
                            form.errorMessage = null
                            form.emailExpanded = true
                        }
                        .heightIn(min = 48.dp)
                        .background(if (invited) Color.Transparent else colors.cream, ContinuousRoundedShape(13.dp)),
                    horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val style = type.style(IosTextStyle.Subheadline, FontWeight.Medium)
                    PeekSymbol("envelope", font = style, tint = colors.ink)
                    Text(localized("auth.emailOption"), color = colors.ink, style = style.sfLines())
                }
            }
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(spacing)) {
                AnimatedVisibility(social && formVisible, enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.easeOut(0.2)), exit = ExitTransition.None) {
                    OrRule()
                }
                AnimatedVisibility(formVisible, enter = expanding, exit = ExitTransition.None) {
                    val localizer = LocalLocalizer.current
                    EmailForm(form, invited, onSend = {
                        val email = form.email.cleanedEmail
                        val language = localizer.language
                        run(null, { step = SignInStep.Code }) { session.sendCode(to = email, language = language) }
                    })
                }
            }
        }
        if (!formVisible) form.errorMessage?.let { ErrorLabel(it) }
    }
}

/**
 * `emailForm`: what happens (`auth.emailIntroShort`, or `auth.emailIntro` [invited], secondary footnote), the address's field, the error, and "Email me
 * a code", which sends the code to the address as it is cleaned and turns to the code step once it is sent; it waits
 * for an address with an "@" in it. The keyboard's action ends the typing, as the iPhone's return does.
 */
@Composable
private fun EmailForm(form: SignInForm, invited: Boolean, onSend: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(Modifier.fillMaxWidth().formSurface(invited, 18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(localized(if (invited) "auth.emailIntro" else "auth.emailIntroShort"), color = colors.secondaryLabel, style = type.footnote.sfLines())
        val label = localized("auth.email")
        SignInField(label) { modifier ->
            NoSuggestions {
                SignInTextField(
                    form.email, { form.email = it }, label, EMAIL_FIELD, modifier,
                    placeholder = "you@example.com",
                    keyboard = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None, autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Done,
                    ),
                    autofill = ContentType.EmailAddress,
                    style = type.body.sfLines(),
                )
            }
        }
        form.errorMessage?.let { ErrorLabel(it) }
        SignInPrimaryButton(
            localized(if (form.working) "auth.sending" else "auth.send"), form.working,
            enabled = !form.working && "@" in form.email.cleanedEmail, tag = EMAIL_SEND, onClick = onSend,
        )
    }
}

/**
 * `codeForm`: where the code went (`auth.codeIntro`), the code's field (the numbers alone, six at most, on the
 * keyboard of digits alone, offered by autofill as a one-time code, in medium monospaced title 2 spaced 10 and centred), the
 * error, "View my parcels", which waits for six digits, and "Use a different email", back to the ways in with the
 * address kept and the code and the error cleared.
 */
@Composable
private fun CodeForm(form: SignInForm, invited: Boolean, focus: AccessibilityFocus, onVerify: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val email = form.email.cleanedEmail
    val intro = remember(email, language) { localizer.text("auth.codeIntro", mapOf("email" to email)) }
    Column(Modifier.fillMaxWidth().formSurface(invited, 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(intro, color = colors.secondaryLabel, style = type.footnote.sfLines())
        val label = localized("auth.code")
        SignInField(label) { modifier ->
            SignInTextField(
                form.code, { form.code = signInCode(it) }, label, CODE_FIELD, modifier.accessibilityFocus(focus),
                placeholder = "",
                // `.numberPad`'s digits alone: the number keyboard's password variation, with no password semantics or
                // transformation, so TalkBack reads each digit typed.
                keyboard = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                autofill = ContentType.SmsOtpCode,
                style = type.tracking(type.style(IosTextStyle.Title2, FontWeight.Medium, FontDesign.Monospaced), 10f).sfLines()
                    .copy(textAlign = TextAlign.Center),
            )
        }
        form.errorMessage?.let { ErrorLabel(it) }
        SignInPrimaryButton(
            localized(if (form.working) "auth.signingIn" else "auth.openBox"), form.working,
            enabled = !form.working && form.code.length == 6, tag = CODE_VERIFY, onClick = onVerify,
        )
        Text(
            localized("auth.differentEmail"),
            Modifier
                .fillMaxWidth()
                .testTag(DIFFERENT_EMAIL)
                .button(interactionSource = null, indication = PlainPress, enabled = !form.working, role = Role.Button) {
                    form.step = SignInStep.Methods
                    form.code = ""
                    form.errorMessage = null
                }
                .heightIn(min = 44.dp)
                .wrapContentHeight(),
            color = colors.ink, textAlign = TextAlign.Center,
            style = type.caption.copy(textDecoration = TextDecoration.Underline).sfLines(),
        )
    }
}

/**
 * `SignInField`: the field under its name (semibold caption), 8 apart; the name is the field's for TalkBack, which
 * doesn't read it apart. The field is 15 in from its sides and at least 54 high, on the system background with corners
 * of 16 and an outline of SwiftUI's `.separator` at 70 %, UIKit's separator as the iPhone draws it ([PeekColors.divider]).
 */
@Composable
private fun SignInField(label: String, field: @Composable (Modifier) -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(16.dp)
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, Modifier.clearAndSetSemantics {}, color = colors.label, style = PeekTheme.type.style(IosTextStyle.Caption, FontWeight.SemiBold).sfLines())
        field(
            Modifier
                .fillMaxWidth()
                .background(if (colors.dark) Color.Black else Color.White, shape)
                .stroke(1.dp, colors.divider.copy(alpha = colors.divider.alpha * 0.7f), shape)
                .padding(horizontal = 15.dp),
        )
    }
}

/**
 * A field of the form: one line, centred in its 54, the [placeholder] in the placeholder grey while it is empty, the
 * cursor in the accent, read by TalkBack as [label] and offered by autofill as [autofill]. The placeholder shrinks to
 * keep to its one line where the field is too narrow for it at the reader's size, as UITextField's does
 * (`you@example.com` in about 33 points at the largest size). The cursor stays where the reader left it, at the end of
 * what came otherwise.
 */
@Composable
private fun SignInTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    tag: String,
    modifier: Modifier,
    placeholder: String,
    keyboard: KeyboardOptions,
    autofill: ContentType,
    style: androidx.compose.ui.text.TextStyle,
) {
    val colors = PeekTheme.colors
    var edited by remember { mutableStateOf(TextFieldValue(value, TextRange(value.length))) }
    val shown = if (edited.text == value) edited else TextFieldValue(value, TextRange(value.length))
    BasicTextField(
        shown,
        { edit ->
            edited = edit
            if (edit.text != value) onValueChange(edit.text)
        },
        modifier
            .testTag(tag)
            .semantics {
                contentDescription = label
                contentType = autofill
            },
        textStyle = style.copy(color = colors.label),
        singleLine = true,
        cursorBrush = SolidColor(colors.accent),
        keyboardOptions = keyboard,
        decorationBox = { text ->
            Box(Modifier.fillMaxWidth().heightIn(min = 54.dp), contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    val probe = LocalSignInPlaceholder.current
                    Text(
                        placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = style, maxLines = 1, softWrap = false,
                        autoSize = MinimumScale(style.fontSize, PLACEHOLDER_SHRINK),
                        onTextLayout = { probe?.invoke(it) },
                    )
                }
                // The field as wide as its box, so a centred one centres in it rather than in its own least width.
                Box(Modifier.fillMaxWidth(), propagateMinConstraints = true) { text() }
            }
        },
    )
}

/**
 * `SignInPrimaryLabel` in `TactileButtonStyle`: the spinner in the ink on the yellow while [working], 9 from the
 * [title] in semibold body, centred, at least 52 high on the accent with corners of 16; faded to half, not greyed, while
 * it can't be pressed and nothing runs.
 */
@Composable
private fun SignInPrimaryButton(title: String, working: Boolean, enabled: Boolean, tag: String, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .graphicsLayer { alpha = if (enabled || working) 1f else 0.5f }
            .button(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
            .heightIn(min = 52.dp)
            .background(colors.accent, ContinuousRoundedShape(16.dp)),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (working) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = REGULAR_SPINNER, tint = colors.onAccent)
        Text(title, color = colors.onAccent, textAlign = TextAlign.Center, style = PeekTheme.type.style(IosTextStyle.Body, FontWeight.SemiBold).sfLines())
    }
}

/**
 * Google's and the OIDC provider's button (`TactileButtonStyle`): its content 10 apart, centred, 14 in from the sides
 * and 12 from the top and bottom, at least 48 high, on the paper with corners of 13 and a hairline of the separator at
 * 35 %.
 */
@Composable
private fun MethodButton(tag: String, enabled: Boolean, onClick: () -> Unit, content: @Composable () -> Unit) {
    val colors = PeekTheme.colors
    val shape = ContinuousRoundedShape(13.dp)
    Row(
        Modifier
            .fillMaxWidth()
            .testTag(tag)
            .button(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
            .heightIn(min = 48.dp)
            .background(colors.paper, shape)
            .stroke(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.35f), shape)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) { content() }
}

/** The rule between the other ways in and the email form: hairlines of the secondary grey at 35 % either side of "or". */
@Composable
private fun OrRule() {
    val colors = PeekTheme.colors
    val line = colors.secondaryLabel.copy(alpha = colors.secondaryLabel.alpha * 0.35f)
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.weight(1f).height(0.5.dp).background(line))
        Text(localized("auth.or"), color = colors.secondaryLabel, style = PeekTheme.type.caption.sfLines())
        Box(Modifier.weight(1f).height(0.5.dp).background(line))
    }
}

/**
 * What went wrong with the invitation, a secondary footnote with no `multilineTextAlignment`, as SwiftUI lays out such
 * a `Text` in a centred stack: as wide as its widest line ([widestLine]), its lines at its leading side. One line
 * stands in the middle; a wrapped one doesn't centre its lines.
 */
@Composable
internal fun LeadingFootnote(text: String, modifier: Modifier = Modifier) {
    val lines = remember { TextLines() }
    Text(
        text, modifier.widestLine(lines), color = PeekTheme.colors.secondaryLabel, style = PeekTheme.type.footnote.sfLines(),
        onTextLayout = { lines.result = it },
    )
}

/** `errorView`: `exclamationmark.triangle.fill` and the error, in red caption. */
@Composable
private fun ErrorLabel(message: String) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.caption
    Row(Modifier.testTag(SIGN_IN_ERROR).semantics(mergeDescendants = true) {}, horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING)) {
        PeekSymbol("exclamationmark.triangle.fill", font = style, tint = colors.red)
        Text(message, color = colors.red, style = style.sfLines())
    }
}

/**
 * `compactPrivacyNotice`: a lock (caption 2, 2 down) 7 from "Your tracking details stay private." and the underlined
 * link "Privacy", which opens the privacy page in the browser; all in the secondary grey caption 2.
 */
@Composable
private fun PrivacyLine(privacyUrl: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.caption2
    val short = localized("auth.privacyShort")
    val link = localized("auth.privacyLink")
    val text = remember(short, link, privacyUrl, colors) {
        buildAnnotatedString {
            append("$short ")
            withLink(LinkAnnotation.Url(privacyUrl, TextLinkStyles(SpanStyle(color = colors.secondaryLabel, textDecoration = TextDecoration.Underline)))) {
                append(link)
            }
        }
    }
    Row(modifier.fillMaxWidth().testTag(PRIVACY_LINE), horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.Top) {
        PeekSymbol("lock", Modifier.padding(top = 2.dp), font = style, tint = colors.secondaryLabel)
        // 7 short of the end, as the HStack spaces its closing `Spacer(minLength: 0)` from the words.
        Text(text, Modifier.weight(1f).padding(end = 7.dp), color = colors.secondaryLabel, style = style.sfLines())
    }
}

/**
 * Explore the demo (`TactileButtonStyle`): its words in medium subheadline and `arrow.up.right` in caption, 7 apart,
 * in the secondary grey, at least 44 high, its hint (`welcome.demoDescription`) said as what a tap does.
 */
@Composable
private fun DemoLink(enabled: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val title = localized("welcome.demo")
    val hint = localized("welcome.demoDescription")
    Row(
        modifier
            .testTag(AUTH_DEMO)
            .button(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
            .clearAndSetSemantics {
                contentDescription = title
                role = Role.Button
                if (enabled) {
                    onClick(label = hint) {
                        onClick()
                        true
                    }
                } else {
                    disabled()
                }
            }
            .heightIn(min = 44.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = colors.secondaryLabel, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
        PeekSymbol("arrow.up.right", font = type.caption, tint = colors.secondaryLabel)
    }
}

/**
 * `SignInFormSurface`: a form on the page of an invitation's sign-in ([invited]) stands [padding] in on a card of its
 * own (`parcelCardSurface()`, all behind the form: paper with continuous corners of 16, the accent's 2-point band
 * along its top clipped to them, and over both a 0.7 hairline of the separator at 62 % centred on the edge, as
 * `.stroke` draws it); in the card of the sign-in without one, it stands as it is.
 */
@Composable
private fun Modifier.formSurface(invited: Boolean, padding: Dp): Modifier {
    if (!invited) return this
    val colors = PeekTheme.colors
    val paper = colors.paper
    val band = colors.accent
    val hairline = colors.separator.copy(alpha = colors.separator.alpha * 0.62f)
    return this
        .drawWithCache {
            val outline = ContinuousRoundedShape(16.dp).createOutline(size, layoutDirection, this)
            val edge = Path().apply { addOutline(outline) }
            val line = Stroke(0.7.dp.toPx())
            onDrawBehind {
                drawOutline(outline, paper)
                clipPath(edge) { drawRect(band, size = Size(size.width, 2.dp.toPx())) }
                drawOutline(outline, hairline, style = line)
            }
        }
        .padding(padding)
}

/**
 * `privacyNotice`: under a divider, 18 down, the lock (semibold caption, 20 × 20) 10 from "auth.privacy" over the
 * underlined link "auth.readPrivacy" 4 under it, which opens the privacy page in the browser; all in the secondary
 * grey caption, the link's line in the tertiary grey.
 */
@Composable
private fun PrivacyNotice(privacyUrl: String, modifier: Modifier) {
    val colors = PeekTheme.colors
    val style = PeekTheme.type.caption
    val read = localized("auth.readPrivacy")
    val uri = LocalUriHandler.current
    Column(modifier.fillMaxWidth().testTag(PRIVACY_NOTICE)) {
        Box(Modifier.fillMaxWidth().height(1.dp / LocalDensity.current.density).background(colors.separator))
        Row(Modifier.fillMaxWidth().padding(top = 18.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
            Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                PeekSymbol("lock.fill", font = style.copy(fontWeight = FontWeight.SemiBold), tint = colors.secondaryLabel)
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(localized("auth.privacy"), color = colors.secondaryLabel, style = style.sfLines())
                Text(
                    read,
                    Modifier.button(interactionSource = null, indication = PlainPress, role = Role.Button) { uri.openUri(privacyUrl) },
                    color = colors.secondaryLabel,
                    style = style.sfLines().copy(textDecoration = TextDecoration.Underline),
                )
            }
        }
    }
}

/** A plain `Button` of SwiftUI's: its title in the accent's body, dimmed as it is pressed. */
@Composable
internal fun PlainTextButton(title: String, tag: String, modifier: Modifier = Modifier, enabled: Boolean = true, style: TextStyle? = null, onClick: () -> Unit) {
    val colors = PeekTheme.colors
    Text(
        title,
        modifier
            .testTag(tag)
            .button(interactionSource = null, indication = PlainPress, enabled = enabled, role = Role.Button, onClick = onClick)
            .plainDisabled(enabled),
        color = colors.accent,
        textAlign = TextAlign.Center,
        style = (style ?: PeekTheme.type.body).sfLines(),
    )
}

/** Told how the email's placeholder is laid out: the tests check it keeps to one line, whole. */
internal val LocalSignInPlaceholder = staticCompositionLocalOf<((TextLayoutResult) -> Unit)?> { null }

/** How far the placeholder may shrink, of its style's size. */
private const val PLACEHOLDER_SHRINK = 0.25f

/** The menu's option for [language]. */
internal fun languageTag(language: AppLanguage) = "$LANGUAGE_MENU.${language.rawValue}"

private const val GOOGLE = "google"
private const val OIDC = "oidc"
private val COLUMN_WIDTH: Dp = 520.dp
private val CARD_WIDTH: Dp = 365.dp

/** How long after the code is sent TalkBack goes to its field, in milliseconds. */
private const val CODE_FOCUS_DELAY = 300L
private val BACK_SIZE: Dp = 42.dp
private val SMALL_SPINNER: Dp = 16.dp

/** A `Label`'s symbol to its title. */
private val LABEL_SPACING: Dp = 8.dp

/**
 * The test tags: the iPhone's accessibility identifiers where it sets them (`auth.card`, `auth.google`, `auth.oidc`,
 * `auth.demo`); the others in the app's own style, which no copy key takes.
 */
internal const val SIGN_IN = "signIn"
internal const val AUTH_CARD = "auth.card"
internal const val GOOGLE_TAG = "auth.google"
internal const val OIDC_TAG = "auth.oidc"
internal const val AUTH_DEMO = "auth.demo"
internal const val WELCOME_BACK = "signInBack"
internal const val EMAIL_OPTION = "signInEmailOption"
internal const val EMAIL_FIELD = "signInEmail"
internal const val EMAIL_SEND = "signInSend"
internal const val CODE_FIELD = "signInCode"
internal const val CODE_VERIFY = "signInVerify"
internal const val DIFFERENT_EMAIL = "signInDifferentEmail"
internal const val SIGN_IN_ERROR = "signInError"
internal const val SIGN_IN_ANNOUNCEMENT = "signInAnnouncement"
internal const val PRIVACY_LINE = "signInPrivacy"
internal const val PRIVACY_NOTICE = "signInPrivacyNotice"
internal const val SIGN_IN_INVITATION_ERROR = "signInInvitationError"
internal const val SIGN_IN_INVITATION_RETRY = "signInInvitationRetry"
internal const val SIGN_IN_INVITATION_LOADING = "signInInvitationLoading"
internal const val LANGUAGE_MENU = "languageMenu"
