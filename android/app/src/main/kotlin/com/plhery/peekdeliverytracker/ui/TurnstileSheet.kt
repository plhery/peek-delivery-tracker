package com.plhery.peekdeliverytracker.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.plhery.peekdeliverytracker.core.NativeTurnstilePresenter
import com.plhery.peekdeliverytracker.core.NativeVerificationFailed
import com.plhery.peekdeliverytracker.core.TurnstilePresenter
import com.plhery.peekdeliverytracker.ui.deliveries.localized
import com.plhery.peekdeliverytracker.ui.sheet.BarCancel
import com.plhery.peekdeliverytracker.ui.sheet.InlineBar
import com.plhery.peekdeliverytracker.ui.sheet.LocalSheetInsets
import com.plhery.peekdeliverytracker.ui.sheet.Sheet
import com.plhery.peekdeliverytracker.ui.sheet.SheetDetent
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

/**
 * `NativeTurnstilePresenter` (B-S69, B-D41): the browser check in a sheet over whatever is in front, Add's cover
 * included, as the iPhone presents it on its topmost presented controller; the window's [TurnstileSheetHost] draws it.
 * By NativeTurnstilePresenter's rules (core/NativeVerification.kt), one check at a time and only at an https page:
 * [token] gives the token the page posts. It throws NativeVerificationFailed where the page posts an error, doesn't load
 * or loses its renderer, after 90 seconds, and at once where another check is up, no window is in front (the iPhone's
 * foreground active scene) or this device's WebView can't hold the check ([TurnstilePages.supported]). Cancel, the
 * sheet sent away and the caller's own cancellation end it in a CancellationException. Either way the sheet goes, and
 * the page is torn down once it has left it.
 *
 * A window made again (a rotation, a change of theme) keeps the sheet up and loads the page afresh in it: the iPhone's
 * scene lives through a rotation with its sheet and its page, while Android's WebView goes with its window, so the check
 * starts again where the iPhone's goes on. The window gone for good is the iPhone's scene destroyed, whose sheet and
 * lookup don't outlive it: the check fails, at once where the iPhone's waits out its timeout, and whatever asked (Add's
 * save) ends as its catch does.
 */
@Stable
class TurnstileSheet(internal val pages: TurnstilePages) : TurnstilePresenter {
    /** The check the sheet shows, while one runs. */
    internal var check by mutableStateOf<TurnstileCheck?>(null)
        private set

    /** The windows that can show the sheet, which a lookup reads from its own thread. */
    private val windows = mutableListOf<TurnstileWindow>()

    override suspend fun token(url: String): String {
        currentCoroutineContext().ensureActive()
        val made = TurnstileCheck(url)
        synchronized(windows) {
            if (check != null || !NativeTurnstilePresenter.opens(url) || windows.none { it.active } || !pages.supported()) {
                throw NativeVerificationFailed()
            }
            check = made
        }
        try {
            return coroutineScope {
                val timeout = launch {
                    delay(NativeTurnstilePresenter.timeoutSeconds.seconds)
                    made.failed()
                }
                try {
                    made.answer.await()
                } finally {
                    timeout.cancel()
                }
            }
        } finally {
            synchronized(windows) { if (check === made) check = null }
        }
    }

    /** A window that can show the sheet came. */
    internal fun opened(): TurnstileWindow = TurnstileWindow().also { synchronized(windows) { windows += it } }

    /** [window] went: made again where it [remains], else for good, which fails the check once no window is left. */
    internal fun closed(window: TurnstileWindow, remains: Boolean) {
        val ended = synchronized(windows) {
            windows -= window
            check.takeIf { !remains && windows.isEmpty() }
        }
        ended?.failed()
    }
}

/** A window [TurnstileSheet] can present in, [active] while it is in front (resumed). */
internal class TurnstileWindow {
    @Volatile var active = false
}

/** One check: its page's address, and what ends it. Whatever comes once it has ended is ignored. */
internal class TurnstileCheck(val url: String) : TurnstileEvents {
    val answer = CompletableDeferred<String>()

    override fun token(token: String) {
        answer.complete(token)
    }

    override fun failed() {
        answer.completeExceptionally(NativeVerificationFailed())
    }

    /** Cancel, or the sheet sent away (`cancel()`, `presentationControllerDidDismiss`). */
    fun cancel() {
        answer.completeExceptionally(CancellationException("The browser check was cancelled."))
    }
}

/**
 * What a page tells its check, by NativeTurnstilePresenter's rules: the token its main frame posted, or that the check
 * failed (an error it posted, a load that failed, its renderer gone).
 */
interface TurnstileEvents {
    fun token(token: String)

    fun failed()
}

/** The check's page as the sheet holds it: a WebView (platform/WebViewTurnstilePages.kt), a fake one in tests. */
interface TurnstilePage {
    /** What the sheet shows. */
    val view: View

    /** Loads the check, once the sheet is up. */
    fun load()

    /** Stops loading, takes its listener and script away and destroys it, once it has left the sheet: once. */
    fun tearDown()
}

/** Where the sheet's pages come from. */
interface TurnstilePages {
    /** Whether this device's WebView can hold the check. Never throws. */
    fun supported(): Boolean

    /**
     * The page at `url` in `context`, dark where `dark`, on `background` until it draws, telling `events` what it hears.
     * Throws where no WebView can be made.
     */
    fun page(context: Context, url: String, dark: Boolean, background: Int, events: TurnstileEvents): TurnstilePage
}

/**
 * The check's sheet in this window, a page sheet with its bar (`.pageSheet` in a UINavigationController): the title
 * inline, Cancel at the bar's leading end, the page under it, and no grabber (B-L09). On a phone it is the app's sheet
 * at its large detent, which a drag down, a tap on the dim or the back sends away as Cancel does. In a regular window it
 * is the sheet's form in the window's middle for now, until the iPad's page sheet, larger than the form, is measured.
 * The page loads once the sheet is up (`present`'s completion). TalkBack reaches the bar, then the page, in the pane
 * named for the sheet's title.
 */
@Composable
internal fun TurnstileSheetHost(sheet: TurnstileSheet) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val activity = LocalActivity.current
    DisposableEffect(sheet, lifecycle) {
        val window = sheet.opened()
        val observer = LifecycleEventObserver { _, _ -> window.active = lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED) }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
            sheet.closed(window, remains = activity?.isChangingConfigurations == true)
        }
    }
    val check = sheet.check
    // The check the sheet holds, kept while it goes down once the check has ended.
    var shown by remember { mutableStateOf(check) }
    if (check != null) shown = check
    // Up since the check it holds came, which the page waits for.
    var up by remember(shown) { mutableStateOf(false) }
    val title = localized("native.verification.title")
    Sheet(
        visible = check != null,
        onDismiss = { sheet.check?.cancel() },
        paneTitle = title,
        detents = listOf(SheetDetent.Large),
        onClosed = { if (sheet.check == null) shown = null },
        grabber = false,
        onShown = { up = true },
    ) {
        shown?.let { TurnstileSheetPage(it, up, title, sheet.pages) { sheet.check?.cancel() } }
    }
}

@Composable
private fun TurnstileSheetPage(check: TurnstileCheck, up: Boolean, title: String, pages: TurnstilePages, cancel: () -> Unit) {
    val colors = PeekTheme.colors
    val insets = LocalSheetInsets.current
    // The app's appearance, which the iPhone's window takes and its web view follows.
    val dark = colors.dark
    // A web view's background in a sheet, under the page until it draws: the elevated system background.
    val background = (if (dark) colors.groupedBackground else Color.White).toArgb()
    Box(Modifier.fillMaxSize().semantics { isTraversalGroup = true }) {
        key(check, dark) {
            AndroidView(
                factory = { context -> ShownPage(context, check, pages, dark, background) },
                modifier = Modifier.fillMaxSize().testTag(TURNSTILE_PAGE),
                update = { if (up) it.load() },
                onRelease = { it.release() },
            )
        }
        // Over the page, which shows under it as under the iPhone's bar, and goes first for TalkBack. A drag there moves
        // the sheet rather than the page, as on the iPhone's bar.
        InlineBar(
            title,
            leading = { BarCancel(cancel, TURNSTILE_CANCEL) },
            Modifier
                .semantics { traversalIndex = -1f }
                .pointerInput(Unit) { awaitPointerEventScope { while (true) awaitPointerEvent() } }
                .padding(top = insets.barTop),
        )
    }
}

/** The check's page made in this window: loaded once, torn down as it leaves. A page that can't be made fails the check. */
@SuppressLint("ViewConstructor") // Made in code alone.
private class ShownPage(context: Context, check: TurnstileCheck, pages: TurnstilePages, dark: Boolean, background: Int) : FrameLayout(context) {
    private val page: TurnstilePage? = try {
        pages.page(context, check.url, dark, background, check)
    } catch (_: RuntimeException) {
        check.failed()
        null
    }
    private var loaded = false

    init {
        setBackgroundColor(background)
        page?.let { addView(it.view, LayoutParams(MATCH_PARENT, MATCH_PARENT)) }
    }

    fun load() {
        if (loaded) return
        loaded = true
        page?.load()
    }

    fun release() {
        val page = page ?: return
        removeView(page.view)
        page.tearDown()
    }
}

/** The test tags of the browser check's page and its Cancel. */
const val TURNSTILE_PAGE = "verification.page"
const val TURNSTILE_CANCEL = "verification.cancel"
