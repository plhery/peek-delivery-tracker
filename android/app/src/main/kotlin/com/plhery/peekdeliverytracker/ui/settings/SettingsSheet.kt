package com.plhery.peekdeliverytracker.ui.settings

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.NotificationPreset
import com.plhery.peekdeliverytracker.platform.CacheExportFiles
import com.plhery.peekdeliverytracker.platform.PlayListing
import com.plhery.peekdeliverytracker.platform.SitePage
import com.plhery.peekdeliverytracker.ui.AccessibilityFocus
import com.plhery.peekdeliverytracker.ui.LocalLocalizer
import com.plhery.peekdeliverytracker.ui.accessibilityFocus
import com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesSource
import com.plhery.peekdeliverytracker.ui.deliveries.PlainPress
import com.plhery.peekdeliverytracker.ui.deliveries.REGULAR_SPINNER
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.deliveries.rememberForLaunch
import com.plhery.peekdeliverytracker.ui.draw.ActivityIndicator
import com.plhery.peekdeliverytracker.ui.draw.CapsuleShape
import com.plhery.peekdeliverytracker.ui.sheet.BAR_ROW
import com.plhery.peekdeliverytracker.ui.sheet.BarButton
import com.plhery.peekdeliverytracker.ui.sheet.FormScrollEdge
import com.plhery.peekdeliverytracker.ui.sheet.InlineBar
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetMenus
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.sheet.sheetBottomInset
import com.plhery.peekdeliverytracker.ui.symbols.PeekSymbol
import com.plhery.peekdeliverytracker.ui.symbols.SymbolScale
import com.plhery.peekdeliverytracker.ui.theme.AppearanceStore
import com.plhery.peekdeliverytracker.ui.theme.DynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.GlassSurface
import com.plhery.peekdeliverytracker.ui.theme.IosTextStyle
import com.plhery.peekdeliverytracker.ui.theme.Motion
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.PressStyle
import com.plhery.peekdeliverytracker.ui.theme.ProvideDynamicTypeSize
import com.plhery.peekdeliverytracker.ui.theme.appearanceSetting
import com.plhery.peekdeliverytracker.ui.theme.button
import com.plhery.peekdeliverytracker.ui.theme.materialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.rememberHaptics
import com.plhery.peekdeliverytracker.ui.theme.rememberMaterialBackdrop
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import java.io.File
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.launch

// SettingsViews.swift's `AccountView` as a phone shows it (C-S38 to C-S44): Settings in a sheet from the account
// button, its data page and Delivery updates pushed in it, and the data page's alerts.

/**
 * What Settings asks of the tab whose account button opened it: iOS gives the deliveries and the passport each its own
 * `showingAccount` and sheet ([com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesState],
 * [com.plhery.peekdeliverytracker.ui.passport.PassportState]).
 */
internal interface AccountHost {
    val source: DeliveriesSource

    /** The sheet is open over the tab. */
    val showingAccount: Boolean

    /** The sheet shows as the iPad's form, as the tab settled it when it opened. */
    val sheetsAsForms: Boolean

    fun closeAccount()

    /** Words for TalkBack to say, where Settings says what it did. */
    fun announce(text: String)
}

/**
 * `.sheet(isPresented: $showingAccount) { AccountView() }`: Settings over the deliveries at the large detent with its
 * drag indicator; a form in the middle of the window where the list stands beside the parcel's page
 * (`FormSizing(applies: sheetsAsForms)`), its bar 10 from the top and the side as the iPad's form has it (ipad-01), and
 * the iPad's page sheet in any other regular window, an unfolded foldable's or a tablet's at the accessibility text
 * sizes, as `.presentationSizing` is left alone there. Close, a drag down, a tap outside and the system's back close
 * it, none of them while the data page waits for what it asked (`interactiveDismissDisabled(working)`); the back pops
 * a pushed page first. What it asked runs in the app's scope ([com.plhery.peekdeliverytracker.ui.deliveries.PageWork]),
 * so a window made again still waits for it, and the pushed page, the alert open and the address typed stay too; it
 * opens afresh each time.
 */
@Composable
internal fun AccountSheetHost(state: AccountHost) {
    val work = state.source.pageWork
    val launch = state.source.launch
    val nav = SettingsNav(
        pushed = rememberForLaunch<String?>(launch, null),
        alert = rememberForLaunch<String?>(launch, null),
        confirmation = rememberForLaunch(launch, ""),
    )
    // Each opening scrolls from the top, as a new AccountView does; a change of language keeps the place.
    var openings by remember { mutableIntStateOf(0) }
    val scrolls = remember(openings) { SettingsScrolls(ScrollState(0), ScrollState(0), ScrollState(0)) }
    Sheet(
        visible = state.showingAccount,
        onDismiss = state::closeAccount,
        paneTitle = localized("settings.title"),
        detents = listOf(SheetDetent.Large),
        onClosed = {
            nav.pushed.value = null
            nav.alert.value = null
            nav.confirmation.value = ""
            work.forget(SETTINGS_PAGE)
            work.forget(DELIVERY_UPDATES_PAGE)
            openings++
        },
        dismissible = !work.working(SETTINGS_PAGE),
        page = !state.sheetsAsForms,
        lit = true,
    ) {
        val insets = LocalSheetInsets.current
        val form = if (!state.sheetsAsForms) insets else insets.copy(barTop = FORM_BAR_TOP, barSide = FORM_BAR_SIDE, formSide = FORM_TITLE_SIDE)
        CompositionLocalProvider(LocalSheetInsets provides form) { AccountView(state, nav, scrolls) }
    }
}

/** What AccountView keeps through a window made again: the page pushed, the alert open and the address typed in it. */
internal class SettingsNav(val pushed: MutableState<String?>, val alert: MutableState<String?>, val confirmation: MutableState<String>)

private class SettingsScrolls(val root: ScrollState, val data: ScrollState, val updates: ScrollState)

/**
 * AccountView's `NavigationStack`: Settings, and the data page or Delivery updates pushed over it, coming in from the
 * trailing side as Settings goes a third of the way out to the leading one, and back the same way from the bar's ‹, the
 * system's back (whose gesture it follows) or a drag from the leading edge; under Reduce Motion one fades into the
 * other. What it asked going well closes the sheet (`finish()`), and the export, once written, goes to the share sheet.
 */
@Composable
private fun AccountView(state: AccountHost, nav: SettingsNav, scrolls: SettingsScrolls) {
    val source = state.source
    val work = source.pageWork
    val working = work.working(SETTINGS_PAGE)
    val reduceMotion = PeekTheme.reduceMotion
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pushed = nav.pushed.value
    var drawn by remember { mutableStateOf(pushed) }
    if (pushed != null) drawn = pushed
    val push = remember { Animatable(if (pushed != null) 1f else 0f) }
    LaunchedEffect(pushed, reduceMotion) {
        push.animateTo(if (pushed != null) 1f else 0f, if (reduceMotion) Motion.easeOut(FADE_DURATION) else Motion.smooth(PUSH_DURATION))
        if (pushed == null) drawn = null
    }
    LaunchedEffect(Unit) {
        source.refreshNotificationState()
        source.loadNotificationPreferences()
    }
    val leaving = work.leaving[SETTINGS_PAGE]
    LaunchedEffect(leaving) {
        if (leaving == null) return@LaunchedEffect
        work.left(SETTINGS_PAGE)
        state.closeAccount()
    }
    val exported = work.exported(SETTINGS_PAGE)
    LaunchedEffect(exported) {
        if (exported == null) return@LaunchedEffect
        work.shared(SETTINGS_PAGE)
        shareExport(context, exported)
    }
    val rowFocus = remember { AccessibilityFocus() }
    val pop = {
        nav.pushed.value = null
        rowFocus.request()
    }
    PredictiveBackHandler(enabled = pushed != null && !working) { progress ->
        try {
            progress.collect { if (!reduceMotion) push.snapTo(1f - it.progress) }
            pop()
        } catch (cancelled: CancellationException) {
            scope.launch { push.animateTo(1f, Motion.smooth(PUSH_DURATION)) }
            throw cancelled
        }
    }
    val shownPage by remember { derivedStateOf { push.value > 0f } }
    val covered by remember { derivedStateOf { push.value >= 1f } }
    Layout(
        content = {
            Box(
                Modifier.graphicsLayer {
                    if (reduceMotion) alpha = 1f - push.value else translationX = -push.value * size.width * PUSH_PARALLAX
                },
            ) {
                SettingsRootPage(state, scrolls.root, working, rowFocus) { nav.pushed.value = it }
            }
            val page = drawn
            if (page != null && (pushed != null || shownPage)) {
                Box(
                    Modifier
                        .graphicsLayer { if (reduceMotion) alpha = push.value else translationX = (1f - push.value) * size.width }
                        .edgeSwipeBack(enabled = !working, push = push, onBack = pop),
                ) {
                    when (page) {
                        PAGE_DATA -> AccountDataPage(state, nav, scrolls.data, working, onBack = pop)
                        else -> DeliveryUpdatesPage(state, scrolls.updates, onBack = pop)
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize().testTag(SETTINGS_SHEET).clipToBounds(),
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints) }
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Under the page pushed once it is in, Settings is kept as it was, out of sight and of TalkBack's reach.
            if (!covered) placeables[0].place(0, 0)
            placeables.getOrNull(1)?.place(0, 0)
        }
    }
    SettingsAlerts(state, nav)
}

/**
 * UIKit's interactive pop: a drag from the page's leading 20 takes it back toward the trailing side with the finger,
 * and it goes ([onBack]) where the release leaves it past half way or flicks it on, else it comes back.
 */
private fun Modifier.edgeSwipeBack(enabled: Boolean, push: Animatable<Float, *>, onBack: () -> Unit): Modifier =
    if (!enabled) this else pointerInput(push) {
        val edge = EDGE_WIDTH.toPx()
        kotlinx.coroutines.coroutineScope {
            var tracking = false
            val tracker = VelocityTracker()
            detectHorizontalDragGestures(
                onDragStart = {
                    tracking = it.x <= edge
                    tracker.resetTracking()
                },
                onDragEnd = {
                    if (!tracking) return@detectHorizontalDragGestures
                    tracking = false
                    val speed = tracker.calculateVelocity().x
                    val gone = push.value < 0.5f || speed > FLICK.toPx()
                    launch {
                        if (gone) {
                            onBack()
                        } else {
                            push.animateTo(1f, Motion.smooth(PUSH_DURATION))
                        }
                    }
                },
                onDragCancel = {
                    if (!tracking) return@detectHorizontalDragGestures
                    tracking = false
                    launch { push.animateTo(1f, Motion.smooth(PUSH_DURATION)) }
                },
            ) { change, amount ->
                if (!tracking) return@detectHorizontalDragGestures
                change.consume()
                tracker.addPosition(change.uptimeMillis, change.position)
                launch { push.snapTo((push.value - amount / size.width).coerceIn(0f, 1f)) }
            }
        }
    }

/**
 * AccountView's `page`: "settings.title" as a large title under the bar, which goes under it as the page scrolls and
 * comes back as the bar's inline title, Close at the bar's leading end, off while the data page waits; then the
 * profile (the account, or the way to one), the deliveries' updates for an account and the widget's switch, the
 * appearance and the language, and the data's row over the site's, 24 apart and 24 in, 520 wide at most, on the app's
 * ground. It loads the notifications' state as it opens (`.task`).
 */
@Composable
private fun SettingsRootPage(
    state: AccountHost,
    scroll: ScrollState,
    working: Boolean,
    rowFocus: AccessibilityFocus,
    onPush: (String) -> Unit,
) {
    val source = state.source
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val insets = LocalSheetInsets.current
    val density = LocalDensity.current
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val haptics = rememberHaptics()
    val session by source.session.collectAsState()
    val enabledOnDevice by source.notificationsEnabledOnDevice.collectAsState()
    val preferences by source.notificationPreferences.collectAsState()
    val deliveryEmail by source.deliveryEmail.collectAsState()
    val widgetEnabled by source.deliveryWidgetEnabled.collectAsState()
    val appearance = appearanceSetting()
    val demo = source.isDemo
    val guest = source.isGuest
    val email = (session as? AuthenticationState.SignedIn)?.user?.email
    val backdrop = rememberMaterialBackdrop()
    val barTop = insets.barTop + NAV_BAR_DROP
    val barHeight = barTop + BAR_ROW
    val large = with(density) { LARGE_TITLE.toPx() }
    val fade = with(density) { TITLE_FADE.toPx() }
    val title = localized("settings.title")
    val summary = when {
        // What the summary says is the pushes' preset, which a phone without them has none of.
        !source.pushAvailable -> null
        !enabledOnDevice -> localized("settings.off")
        else -> preferences?.let { localized(NotificationPreset.matching(it.enabledStages).titleKey) } ?: ""
    }
    Box(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().materialBackdrop(backdrop).background(colors.sheet)) {
            Column(Modifier.fillMaxSize().verticalScroll(scroll).padding(top = barHeight + NAV_BAR_FOOT, bottom = sheetBottomInset())) {
                Box(Modifier.fillMaxWidth().heightIn(min = LARGE_TITLE).padding(horizontal = insets.formSide), contentAlignment = Alignment.BottomStart) {
                    Text(
                        title, Modifier.padding(bottom = 8.dp).semantics { heading() },
                        color = colors.label, style = type.style(IosTextStyle.LargeTitle, FontWeight.Bold).sfLines(),
                    )
                }
                SettingsColumn {
                    if (session is AuthenticationState.SignedIn || demo) {
                        AccountRow(email, demo)
                    } else {
                        SignInOffer {
                            source.showSignIn()
                            state.closeAccount()
                        }
                    }
                    SettingsGroup(localized("native.deliveries")) {
                        // Alerts are kept up to date by pushes, which need an account; the widget's switch is for every
                        // mode. Android has no Live Activities, the iPhone's row after it. A phone without pushes keeps
                        // the row for the delivery email alone, where the account has one.
                        if (!guest && (source.pushAvailable || deliveryEmail != null)) {
                            SettingsButtonRow(
                                localized("settings.deliveryUpdates"), SETTINGS_DELIVERY_UPDATES, { onPush(PAGE_UPDATES) },
                                symbol = "bell", value = summary, chevron = true,
                            )
                            SettingsDivider()
                        }
                        SettingsToggleRow(
                            widgetEnabled, source::setDeliveryWidgetEnabled, localized("widget.settingTitle"), SETTINGS_WIDGETS,
                            symbol = "rectangle.3.group", detail = localized("widget.settingDescription"),
                        )
                    }
                    SettingsGroup(localized("settings.preferences")) {
                        SettingsAppearancePicker(appearance) { option ->
                            if (option != appearance) {
                                AppearanceStore.of(context).set(option)
                                haptics.selection()
                            }
                            source.action("appearance-change")
                        }
                        SettingsDivider()
                        SettingsLanguageRow()
                        SettingsDivider()
                        val localizer = LocalLocalizer.current
                        SettingsToggleRow(
                            localizer.use24HourTime, { localizer.use24HourTime = it },
                            localized("settings.use24HourTime"), SETTINGS_CLOCK,
                        )
                        SettingsDivider()
                        SettingsDistanceRow()
                    }
                    SettingsGroup {
                        Box(Modifier.accessibilityFocus(rowFocus)) {
                            SettingsButtonRow(accountTitle(source.isDemo, source.isGuest), SETTINGS_ACCOUNT_DATA, { onPush(PAGE_DATA) }, chevron = true)
                        }
                        SettingsDivider()
                        // The site's landing, which the app itself never shows, inside the app as the iPhone's
                        // SafariPage sheet ([SitePage]), the store's page for a review once Peek is listed there
                        // ([PlayListing]), and a mail to Peek under the version it would be about.
                        SettingsButtonRow(
                            localized("app.homePage"), SETTINGS_HOME_PAGE, { SitePage.open(context, source.homePageUrl, uriHandler) },
                            value = hostOf(source.homePageUrl), chevron = true,
                        )
                        SettingsDivider()
                        if (PlayListing.listed) {
                            SettingsButtonRow(
                                localized("settings.rate"), SETTINGS_RATE,
                                {
                                    source.action("review-link-open")
                                    PlayListing.open(context, uriHandler)
                                },
                                chevron = true,
                            )
                            SettingsDivider()
                        }
                        SettingsButtonRow(
                            localized("settings.sendFeedback"), SETTINGS_FEEDBACK,
                            { uriHandler.openUri(AppConfiguration.feedbackUrl(source.version)) },
                            detail = LocalLocalizer.current.text("settings.version", mapOf("version" to source.version)), chevron = true,
                        )
                    }
                }
            }
        }
        FormScrollEdge(barHeight) { scroll.value - large }
        ProvideDynamicTypeSize(DynamicTypeSize.Large) {
            InlineBar(
                title,
                leading = { BarButton(localized("common.close"), state::closeAccount, backdrop, FontWeight.Normal, !working, SETTINGS_CLOSE) },
                modifier = Modifier.padding(top = barTop),
                titleAlpha = { ((scroll.value - (large - fade)) / fade).coerceIn(0f, 1f) },
            )
        }
    }
}

/**
 * AccountView's `accountPage` (C-S41): who it is about, the guest's "native.guest.kept", the address or "app.demo", in
 * subheadline secondary, over `accountData`: the export and the privacy notice; the demo's reset, which asks first,
 * and its way out; a guest's forget all, which asks first; an account's sign out, then its delete, which asks for the
 * address first; and why what it asked failed, in subheadline red. Its title is inline, a spinner at the bar's trailing
 * end while it waits, when nothing on it can be pressed.
 */
@Composable
private fun AccountDataPage(state: AccountHost, nav: SettingsNav, scroll: ScrollState, working: Boolean, onBack: () -> Unit) {
    val source = state.source
    val colors = PeekTheme.colors
    val type = PeekTheme.type
    val uriHandler = LocalUriHandler.current
    val work = source.pageWork
    val session by source.session.collectAsState()
    val parcels by source.parcels.collectAsState()
    val unlisted by source.unlistedDeviceParcels.collectAsState()
    val demo = source.isDemo
    val guest = source.isGuest
    val email = (session as? AuthenticationState.SignedIn)?.user?.email
    val failure = work.failure(SETTINGS_PAGE)
    val run = { leaves: Boolean, job: suspend () -> Unit ->
        work.run(SETTINGS_PAGE, source.scope, source::errorMessage, if (leaves) emptyList() else null, job)
    }
    val ask = { alert: String ->
        if (alert == ALERT_DELETE) nav.confirmation.value = ""
        nav.alert.value = alert
    }
    PushedPage(
        accountTitle(demo, guest), scroll, backEnabled = !working, onBack = onBack,
        trailing = if (!working) null else {
            { GlassSurface(CapsuleShape, Modifier.size(BAR_ROW), backdrop = null, behind = colors.sheet) {
                ActivityIndicator(Modifier.align(Alignment.Center).testTag(SETTINGS_WORKING), size = REGULAR_SPINNER)
            } }
        },
        enabled = !working,
    ) {
        Text(
            if (guest) localized("native.guest.kept") else email ?: localized("app.demo"),
            color = colors.secondaryLabel, style = type.subheadline.sfLines(),
        )
        SettingsGroup(localized("settings.yourData")) {
            SettingsButtonRow(
                localized("account.export"), SETTINGS_EXPORT,
                { run(false) { work.export(SETTINGS_PAGE, source.exportAccount()) } },
                symbol = "square.and.arrow.up",
            )
            SettingsDivider()
            SettingsButtonRow(localized("account.privacy"), SETTINGS_PRIVACY, { uriHandler.openUri(source.privacyUrl) }, symbol = "hand.raised", chevron = true)
        }
        if (demo) {
            SettingsGroup(localized("app.demo")) {
                SettingsButtonRow(localized("native.resetDemo"), SETTINGS_RESET_DEMO, { ask(ALERT_RESET) }, chevron = true)
                SettingsDivider()
                SettingsButtonRow(localized("native.exitDemo"), SETTINGS_EXIT_DEMO, {
                    source.showWelcome()
                    state.closeAccount()
                })
            }
        } else if (guest) {
            // Parcels whose saved copy is lost and still to be read again are this phone's too: Forget all forgets them.
            if (parcels.isNotEmpty() || unlisted > 0) {
                SettingsGroup {
                    SettingsButtonRow(localized("door.recents.forgetAll"), SETTINGS_FORGET_ALL, { ask(ALERT_FORGET) })
                }
            }
        } else {
            SettingsGroup {
                SettingsButtonRow(localized("account.signOut"), SETTINGS_SIGN_OUT, { run(true) { source.signOut() } })
            }
            Text(
                localized("account.delete"),
                Modifier
                    .heightIn(min = 44.dp)
                    .testTag(SETTINGS_DELETE)
                    .button(interactionSource = null, indication = PlainPress, role = Role.Button) { ask(ALERT_DELETE) }
                    .padding(vertical = 13.dp),
                color = colors.warning, style = type.caption.sfLines(),
            )
        }
        if (failure != null) {
            Text(failure, Modifier.testTag(SETTINGS_ERROR), color = colors.red, style = type.subheadline.sfLines())
        }
    }
}

/**
 * A page pushed in Settings' stack: [title] inline in the bar with the stack's ‹ at its leading end, read by TalkBack as
 * "Settings", the page it goes back to, and [trailing], if any, at its trailing end; what it holds scrolls under the
 * bar as Settings' does, 24 apart and 24 in, 520 wide at most, on the app's ground. While it isn't [enabled]
 * (`.disabled(working)`) nothing on it takes a tap.
 */
@Composable
internal fun PushedPage(
    title: String,
    scroll: ScrollState,
    backEnabled: Boolean,
    onBack: () -> Unit,
    trailing: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = PeekTheme.colors
    val insets = LocalSheetInsets.current
    val barTop = insets.barTop + NAV_BAR_DROP
    val barHeight = barTop + BAR_ROW
    Box(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize().background(colors.sheet)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(top = barHeight + NAV_BAR_FOOT, bottom = sheetBottomInset())
                    .then(if (enabled) Modifier else Modifier.semantics { disabled() }.pointerInput(Unit) { swallowTaps() }),
            ) {
                SettingsColumn(content)
            }
        }
        FormScrollEdge(barHeight) { scroll.value.toFloat() }
        Box(Modifier.padding(top = barTop).fillMaxWidth().height(BAR_ROW)) {
            ProvideDynamicTypeSize(DynamicTypeSize.Large) {
                InlineBar(title, leading = { SettingsBack(backEnabled, onBack) })
            }
            if (trailing != null) Box(Modifier.align(Alignment.CenterEnd).padding(end = insets.barSide)) { trailing() }
        }
    }
}

/** Takes every press on what it covers before what is under it can, as SwiftUI's `.disabled` keeps taps from it. */
private suspend fun androidx.compose.ui.input.pointer.PointerInputScope.swallowTaps() {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent(androidx.compose.ui.input.pointer.PointerEventPass.Initial)
            event.changes.forEach { it.consume() }
        }
    }
}

/**
 * The stack's ‹ on its glass circle. TalkBack reads it by the page it goes back to, "Settings", as VoiceOver reads
 * UIKit's back button, and goes there as the page comes in. Off while the data page waits, as the back is: a
 * deliberate difference from the iPhone, whose back button still pops the page while AccountView's run goes on
 * (`.disabled(working)` covers the page, not the bar).
 */
@Composable
private fun SettingsBack(enabled: Boolean, onBack: () -> Unit) {
    val colors = PeekTheme.colors
    val focus = remember { AccessibilityFocus() }
    LaunchedEffect(Unit) { focus.request() }
    val label = localized("settings.title")
    GlassSurface(CapsuleShape, Modifier.size(BAR_ROW), backdrop = null, behind = colors.sheet) {
        Box(
            Modifier
                .size(BAR_ROW)
                .testTag(SETTINGS_BACK)
                .accessibilityFocus(focus)
                .button(interactionSource = null, indication = PressStyle.tactile(0.94f), enabled = enabled, onClick = onBack)
                .semantics {
                    contentDescription = label
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            PeekSymbol(
                "chevron.left", font = PeekTheme.type.fixed(17f, FontWeight.SemiBold), scale = SymbolScale.Large,
                tint = if (enabled) colors.ink else colors.tertiaryLabel,
            )
        }
    }
}

/** A page's ScrollView: what it holds 24 apart and 24 in, 520 wide at most, in the middle (`.frame(maxWidth: 520)`). */
@Composable
private fun SettingsColumn(content: @Composable ColumnScope.() -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            Modifier.widthIn(max = 520.dp).fillMaxWidth().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            content = content,
        )
    }
}

/**
 * AccountView's alerts over the sheet (C-S42): forget all, the demo's reset and the account's delete, the last with the
 * address to type, which Permanently delete waits for, as `cleaned` compares them, but in the demo, which asks for
 * none. Cancel and the back close them; their action runs as the data page's does, and the reset and the delete close
 * the sheet once done.
 */
@Composable
private fun SettingsAlerts(state: AccountHost, nav: SettingsNav) {
    val source = state.source
    val work = source.pageWork
    val localizer = LocalLocalizer.current
    val menus = LocalSheetMenus.current
    val kind = nav.alert.value
    val close = { nav.alert.value = null }
    val run = { leaves: Boolean, job: suspend () -> Unit ->
        close()
        work.run(SETTINGS_PAGE, source.scope, source::errorMessage, if (leaves) emptyList() else null, job)
    }
    DisposableEffect(menus, kind) {
        if (kind != null) {
            menus?.content = {
                when (kind) {
                    ALERT_FORGET -> SettingsAlert(
                        title = localizer.text(
                            "door.recents.forget.many",
                            mapOf("count" to source.parcels.value.size + source.unlistedDeviceParcels.value),
                        ),
                        message = null,
                        action = localizer.text("door.recents.forget.yes"),
                        actionTag = SETTINGS_ALERT_ACTION,
                        destructive = true,
                        onCancel = close,
                    ) { run(false) { source.forgetDeviceParcels() } }
                    ALERT_RESET -> SettingsAlert(
                        title = localizer.text("native.resetDemoQuestion"),
                        message = localizer.text("native.resetDemoDescription"),
                        action = localizer.text("native.resetDemo"),
                        actionTag = SETTINGS_ALERT_ACTION,
                        onCancel = close,
                    ) { run(true) { source.resetDemoData() } }
                    else -> {
                        val demo = source.isDemo
                        val email = (source.session.value as? AuthenticationState.SignedIn)?.user?.email ?: ""
                        val typed = nav.confirmation.value
                        SettingsAlert(
                            title = localizer.text("account.deleteQuestion"),
                            message = localizer.text("account.deleteDescription"),
                            action = localizer.text("account.deletePermanent"),
                            actionTag = SETTINGS_ALERT_ACTION,
                            destructive = true,
                            actionEnabled = demo || typed.cleaned() == email.cleaned(),
                            field = if (demo) null else AlertField(localizer.text("account.typeToConfirm", mapOf("email" to email)), typed) {
                                nav.confirmation.value = it
                            },
                            onCancel = close,
                        ) { run(true) { source.deleteAccount(typed) } }
                    }
                }
            }
        }
        onDispose { if (kind != null) menus?.content = null }
    }
}

/** AccountView's `cleaned`: without the spaces and line ends around it, in lower case. */
private fun String.cleaned() = trim().lowercase()

/** The data page's title (`accountTitle`): the demo's, the phone's own parcels' or the account's. */
@Composable
private fun accountTitle(demo: Boolean, guest: Boolean) =
    localized(if (demo) "settings.demoData" else if (guest) "peek.onThisDevice" else "settings.accountData")

/** `homePageURL.host()`: the home page's host, as the row shows it. */
private fun hostOf(url: String): String? = runCatching { java.net.URI(url).host }.getOrNull()

/**
 * `ActivityShareSheet(items: [exportURL])`: the export through the system's share sheet, read through the app's
 * FileProvider, which the chosen app may read. The file stays in the cache's exports, as iOS leaves its own in the
 * temporary folder, and the next export of the day takes its place.
 */
private fun shareExport(context: Context, file: File) {
    val uri = CacheExportFiles.uri(context, file)
    val send = Intent(Intent.ACTION_SEND)
        .setType("application/json")
        .putExtra(Intent.EXTRA_STREAM, uri)
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    send.clipData = ClipData.newRawUri(file.name, uri)
    try {
        context.startActivity(Intent.createChooser(send, null).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
    } catch (_: ActivityNotFoundException) {
        // No app to take it: nothing opens, as an empty share sheet would offer nothing.
    }
}

/** The page AccountView's own run (`working`, `errorMessage`) is held for in the app's scope. */
internal val SETTINGS_PAGE: UUID = UUID.fromString("5e771465-0000-4000-8000-000000000001")

/** The page Delivery updates' own run is held for. */
internal val DELIVERY_UPDATES_PAGE: UUID = UUID.fromString("5e771465-0000-4000-8000-000000000002")

internal const val PAGE_DATA = "data"
internal const val PAGE_UPDATES = "deliveryUpdates"
private const val ALERT_FORGET = "forget"
private const val ALERT_RESET = "reset"
private const val ALERT_DELETE = "delete"

private const val PUSH_DURATION = 0.5
private const val FADE_DURATION = 0.35
/** How far Settings goes out as a page comes in, of its width, as UIKit's push moves the page under. */
private const val PUSH_PARALLAX = 0.3f
/**
 * A NavigationStack's bar in a sheet stands a point lower than a sheet's own bar row (`SheetInsets.barTop`), and what
 * scrolls under it starts 10 under the bar, as UIKit's navigation bar insets its scroll view.
 */
internal val NAV_BAR_DROP = 1.dp
internal val NAV_BAR_FOOT = 10.dp
/**
 * The iPad's form holds the stack's bar nearer its corner than a page sheet does (ipad-01): its row 10 from the top, its
 * items 10 from the side, the large title 16 in, and so what scrolls under it 10 higher.
 */
internal val FORM_BAR_TOP = 9.dp
internal val FORM_BAR_SIDE = 10.dp
internal val FORM_TITLE_SIDE = 16.dp
/** UIKit's large title's room under the bar, its words 8 over its foot. */
private val LARGE_TITLE = 52.dp
/** How far the scroll goes, once the large title is under the bar, while the inline title fades in. */
private val TITLE_FADE = 12.dp
private val EDGE_WIDTH = 20.dp
private val FLICK = 500.dp

/**
 * Settings' test tags, iOS's accessibility identifiers where it has them. Each is made from this one, so that the
 * search for copy keys in the sources (DeviceWordsTest) doesn't take them for copy, as iOS's generator skips
 * `.accessibilityIdentifier`.
 */
internal const val SETTINGS_SHEET = "settings"
internal const val SETTINGS_CLOSE = "$SETTINGS_SHEET.close"
internal const val SETTINGS_BACK = "$SETTINGS_SHEET.back"
internal const val SETTINGS_WORKING = "$SETTINGS_SHEET.working"
internal const val SETTINGS_DELIVERY_UPDATES = "$SETTINGS_SHEET.deliveryUpdates"
internal const val SETTINGS_DISTANCE = "$SETTINGS_SHEET.distance"
internal const val SETTINGS_CLOCK = "$SETTINGS_SHEET.clock"
internal const val SETTINGS_WIDGETS = "$SETTINGS_SHEET.widgets"
internal const val SETTINGS_ACCOUNT_DATA = "$SETTINGS_SHEET.accountData"
internal const val SETTINGS_HOME_PAGE = "$SETTINGS_SHEET.homePage"
internal const val SETTINGS_RATE = "$SETTINGS_SHEET.rate"
internal const val SETTINGS_FEEDBACK = "$SETTINGS_SHEET.feedback"
internal const val SETTINGS_EXPORT = "$SETTINGS_SHEET.export"
internal const val SETTINGS_PRIVACY = "$SETTINGS_SHEET.privacy"
internal const val SETTINGS_RESET_DEMO = "$SETTINGS_SHEET.resetDemo"
internal const val SETTINGS_EXIT_DEMO = "$SETTINGS_SHEET.exitDemo"
internal const val SETTINGS_FORGET_ALL = "$SETTINGS_SHEET.forgetAll"
internal const val SETTINGS_SIGN_OUT = "$SETTINGS_SHEET.signOut"
internal const val SETTINGS_DELETE = "$SETTINGS_SHEET.delete"
internal const val SETTINGS_ERROR = "$SETTINGS_SHEET.error"
internal const val SETTINGS_ALERT_ACTION = "$SETTINGS_SHEET.alert.action"
