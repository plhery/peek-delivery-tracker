package com.plhery.peekdeliverytracker.ui.arrival

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextRange
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
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.ContinuousRoundedShape
import com.plhery.peekdeliverytracker.ui.draw.NoticeBanner
import com.plhery.peekdeliverytracker.ui.sheet.KeyboardScrollColumn
import com.plhery.peekdeliverytracker.ui.sheet.MenuOption
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenu
import com.plhery.peekdeliverytracker.ui.sheet.SheetMenus
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.theme.FontDesign
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/** `SignInView.Step`: the ways in, then the code sent. */
internal enum class SignInStep { Methods, Code }

/**
 * SignInView's state: the step, what is typed, whether the email form is open among other ways in, the run under way
 * (`working`, with the provider it opened) and the error the last one ended on. What is typed, the step and the error
 * live on through the window being made again, as the iPhone's view lives on through a rotation; a run doesn't.
 */
@Stable
private class SignInForm(step: SignInStep, email: String, code: String, expanded: Boolean, error: String?) {
    var step by mutableStateOf(step)
    var email by mutableStateOf(email)
    var code by mutableStateOf(code)
    var emailExpanded by mutableStateOf(expanded)
    var errorMessage by mutableStateOf(error)
    var working by mutableStateOf(false)
    var activeProvider: String? by mutableStateOf(null)

    companion object {
        val Saver = listSaver<SignInForm, Any?>(
            save = { listOf(it.step.name, it.email, it.code, it.emailExpanded, it.errorMessage) },
            restore = { SignInForm(SignInStep.valueOf(it[0] as String), it[1] as String, it[2] as String, it[3] as Boolean, it[4] as String?) },
        )
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
 * email" and the demo are disabled, as the iPhone's are; the back and the language menu are not. A cancelled run
 * says nothing; any other failure says `Localizer.errorMessage`'s words, an OIDC one with only the generic message its
 * own. The system's back does what the back does ([onBack]), so it never closes the app from here.
 */
@Composable
internal fun SignInView(
    session: SessionStore,
    scroll: ScrollState,
    menus: SheetMenus,
    onBack: () -> Unit,
    onAnchor: (LayoutCoordinates) -> Unit,
) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val density = LocalDensity.current
    val configuration = session.configuration
    val form = rememberSaveable(saver = SignInForm.Saver) { SignInForm(SignInStep.Methods, "", "", false, null) }
    val scope = rememberCoroutineScope()

    /** `run(provider:_:)`. */
    fun run(provider: String? = null, operation: suspend () -> Unit) {
        if (form.working) return
        form.working = true
        form.activeProvider = provider
        form.errorMessage = null
        scope.launch {
            try {
                operation()
            } catch (_: AuthenticationError.OauthCancelled) {
            } catch (cancelled: CancellationException) {
                // A change of account under way (`checkGeneration`) says nothing; the screen going stops the run.
                currentCoroutineContext().ensureActive()
            } catch (error: Exception) {
                val message = localizer.errorMessage(error)
                form.errorMessage = if (provider == OIDC && message == localizer.text("error.generic")) {
                    localizer.text("auth.oidcFailed", mapOf("provider" to configuration.oidcName))
                } else {
                    message
                }
            }
            form.working = false
            form.activeProvider = null
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
            Box(Modifier.size(160.dp, 165.dp).onGloballyPositioned(onAnchor))
            val card = ContinuousRoundedShape(24.dp)
            Column(
                Modifier
                    .widthIn(max = CARD_WIDTH)
                    .fillMaxWidth()
                    .testTag(AUTH_CARD)
                    .background(colors.paper, card)
                    .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.35f), card)
                    .padding(22.dp),
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        localized("arrival.signInTitle"), color = colors.label, textAlign = TextAlign.Center,
                        style = type.tracking(type.style(IosTextStyle.Title, FontWeight.SemiBold), -0.6f).sfLines(),
                    )
                    Text(localized("arrival.signInSubtitle"), color = colors.secondaryLabel, textAlign = TextAlign.Center, style = type.footnote.sfLines())
                }
                Box(Modifier.padding(top = 23.dp)) {
                    when {
                        !configuration.authenticationConfigured -> NoticeBanner(
                            symbol = "wrench.and.screwdriver.fill",
                            title = localized("auth.configTitle"),
                            message = localized("native.configurationHelp"),
                            tint = colors.orange,
                        )
                        form.step == SignInStep.Code -> CodeForm(form, onVerify = {
                            run { session.verifyCode(email = form.email.cleanedEmail, code = form.code) }
                        })
                        else -> Methods(session, form, ::run)
                    }
                }
                PrivacyLine(configuration.privacyUrl, Modifier.padding(top = 18.dp))
            }
            DemoLink(enabled = !form.working, Modifier.padding(top = 12.dp)) { session.enterDemo() }
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
                .clickable(interactionSource = null, indication = PlainPress, role = Role.Button, onClick = onBack)
                .semantics { contentDescription = label },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol("chevron.left", font = PeekTheme.type.body, tint = colors.ink)
        }
    }
}

/**
 * `AuthenticationLanguageMenu`: the globe and the language's code in upper case (semibold caption) in the secondary
 * grey, 10 in, at least 44 high, read as `language.label` with the language's own name as its value. It opens iOS's
 * menu of the languages by their own names, the one in use checked, its top at the label's and its trailing edge at
 * the label's; a choice sets the app's language (`localizer.language`), which the app saves and passes on.
 */
@Composable
private fun LanguageMenu(menus: SheetMenus) {
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
            ) { localizer.language = AppLanguage.entries[it] }
        }
    }
    Row(
        Modifier
            .onGloballyPositioned { bounds = it.boundsInWindow() }
            .testTag(LANGUAGE_MENU)
            .accessibilityFocus(returnFocus)
            .clickable(interactionSource = null, indication = PlainPress, role = Role.DropdownList, onClick = open)
            .clearAndSetSemantics {
                contentDescription = label
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
 * form, or, among other ways in, "Sign in with email", which opens it (`.easeOut(duration: 0.2)`, none under Reduce
 * Motion) rising into place as it fades in, under a rule with "or" in it; the error under them while the form is shut.
 */
@Composable
private fun Methods(session: SessionStore, form: SignInForm, run: (String?, suspend () -> Unit) -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val reduceMotion = PeekTheme.reduceMotion
    val configuration = session.configuration
    val social = configuration.googleAuthEnabled || configuration.oidcAuthEnabled
    val formVisible = configuration.emailOtpEnabled && (!social || form.emailExpanded)
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(9.dp)) {
        if (configuration.googleAuthEnabled) {
            val active = form.activeProvider == GOOGLE
            MethodButton(GOOGLE_TAG, enabled = !form.working, onClick = { run(GOOGLE) { session.signInWithGoogle() } }) {
                Image(painterResource(R.drawable.google_g), contentDescription = null, Modifier.size(20.dp))
                val style = type.fixed(18f, FontWeight.Medium).sfLines()
                Text(
                    localized(if (active) "auth.googleOpening" else "auth.google"), color = colors.ink, style = style, maxLines = 1, softWrap = false,
                    autoSize = TextAutoSize.StepBased(minFontSize = style.fontSize * 0.7f, maxFontSize = style.fontSize),
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
            MethodButton(OIDC_TAG, enabled = !form.working, onClick = { run(OIDC) { session.signInWithOidc() } }) {
                PeekSymbol("person.badge.key.fill", Modifier.clearAndSetSemantics {}, font = type.style(IosTextStyle.Subheadline, FontWeight.Medium), tint = colors.ink)
                Text(title, color = colors.ink, style = type.style(IosTextStyle.Subheadline, FontWeight.Medium).sfLines())
                if (active) ActivityIndicator(Modifier.clearAndSetSemantics {}, size = SMALL_SPINNER)
            }
        }
        val expanding = if (reduceMotion) EnterTransition.None else {
            fadeIn(Motion.easeOut(0.2)) + slideInVertically(Motion.easeOut(0.2)) { it } +
                expandVertically(Motion.easeOut(0.2), expandFrom = Alignment.Top, clip = false)
        }
        AnimatedVisibility(social && formVisible, enter = if (reduceMotion) EnterTransition.None else fadeIn(Motion.easeOut(0.2)), exit = ExitTransition.None) {
            OrRule()
        }
        AnimatedVisibility(formVisible, enter = expanding, exit = ExitTransition.None) {
            val localizer = LocalLocalizer.current
            EmailForm(form, onSend = {
                run(null) {
                    session.sendCode(to = form.email.cleanedEmail, language = localizer.language)
                    form.step = SignInStep.Code
                }
            })
        }
        if (!formVisible && configuration.emailOtpEnabled) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .testTag(EMAIL_OPTION)
                    .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = !form.working, role = Role.Button) {
                        form.errorMessage = null
                        form.emailExpanded = true
                    }
                    .heightIn(min = 48.dp)
                    .background(colors.cream, RoundedCornerShape(13.dp)),
                horizontalArrangement = Arrangement.spacedBy(LABEL_SPACING, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val style = type.style(IosTextStyle.Subheadline, FontWeight.Medium)
                PeekSymbol("envelope", font = style, tint = colors.ink)
                Text(localized("auth.emailOption"), color = colors.ink, style = style.sfLines())
            }
        }
        if (!formVisible) form.errorMessage?.let { ErrorLabel(it) }
    }
}

/**
 * `emailForm`: what happens (`auth.emailIntroShort`, secondary footnote), the address's field, the error, and "Email me
 * a code", which sends the code to the address as it is cleaned and turns to the code step once it is sent; it waits
 * for an address with an "@" in it. The keyboard's action ends the typing, as the iPhone's return does.
 */
@Composable
private fun EmailForm(form: SignInForm, onSend: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(localized("auth.emailIntroShort"), color = colors.secondaryLabel, style = type.footnote.sfLines())
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
 * number keyboard, offered by autofill as a one-time code, in medium monospaced title 2 spaced 10 and centred), the
 * error, "View my parcels", which waits for six digits, and "Use a different email", back to the ways in with the
 * address kept and the code and the error cleared.
 */
@Composable
private fun CodeForm(form: SignInForm, onVerify: () -> Unit) {
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val localizer = LocalLocalizer.current
    val language = LocalLanguage.current
    val email = form.email.cleanedEmail
    val intro = remember(email, language) { localizer.text("auth.codeIntro", mapOf("email" to email)) }
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(intro, color = colors.secondaryLabel, style = type.footnote.sfLines())
        val label = localized("auth.code")
        SignInField(label) { modifier ->
            SignInTextField(
                form.code, { form.code = signInCode(it) }, label, CODE_FIELD, modifier,
                placeholder = "",
                keyboard = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
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
                .clickable(interactionSource = null, indication = PlainPress, enabled = !form.working, role = Role.Button) {
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
 * of 16 and an outline of the separator at 70 %.
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
                .border(1.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.7f), shape)
                .padding(horizontal = 15.dp),
        )
    }
}

/**
 * A field of the form: one line, centred in its 54, the [placeholder] in the placeholder grey while it is empty, the
 * cursor in the accent, read by TalkBack as [label] and offered by autofill as [autofill]. The cursor stays where the
 * reader left it, at the end of what came otherwise.
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
                    Text(placeholder, Modifier.clearAndSetSemantics {}, color = colors.tertiaryLabel, style = style, maxLines = 1)
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
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
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
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
            .heightIn(min = 48.dp)
            .background(colors.paper, shape)
            .border(0.75.dp, colors.separator.copy(alpha = colors.separator.alpha * 0.35f), shape)
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
            .clickable(interactionSource = null, indication = PressStyle.Tactile, enabled = enabled, role = Role.Button, onClick = onClick)
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

/** The menu's option for [language]. */
internal fun languageTag(language: AppLanguage) = "language.${language.rawValue}"

private const val GOOGLE = "google"
private const val OIDC = "oidc"
private val COLUMN_WIDTH: Dp = 520.dp
private val CARD_WIDTH: Dp = 365.dp
private val BACK_SIZE: Dp = 42.dp
private val SMALL_SPINNER: Dp = 16.dp

/** A `Label`'s symbol to its title. */
private val LABEL_SPACING: Dp = 8.dp

/** The test tags: the iPhone's accessibility identifiers where it sets them. */
internal const val SIGN_IN = "signIn"
internal const val AUTH_CARD = "auth.card"
internal const val GOOGLE_TAG = "auth.google"
internal const val OIDC_TAG = "auth.oidc"
internal const val AUTH_DEMO = "auth.demo"
internal const val WELCOME_BACK = "welcome.back"
internal const val EMAIL_OPTION = "auth.emailOption"
internal const val EMAIL_FIELD = "auth.email"
internal const val EMAIL_SEND = "auth.send"
internal const val CODE_FIELD = "auth.code"
internal const val CODE_VERIFY = "signInVerify"
internal const val DIFFERENT_EMAIL = "auth.differentEmail"
internal const val SIGN_IN_ERROR = "signInError"
internal const val PRIVACY_LINE = "auth.privacy"
internal const val LANGUAGE_MENU = "languageMenu"
