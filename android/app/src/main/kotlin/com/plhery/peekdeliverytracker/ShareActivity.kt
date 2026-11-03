package com.plhery.peekdeliverytracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.ShareSheet
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_HEIGHT
import com.plhery.peekdeliverytracker.ui.sheet.REGULAR_WIDTH
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.isPhoneInDarkTheme
import com.plhery.peekdeliverytracker.ui.theme.phoneIsDark

/**
 * "Add to Peek" in the share sheet (B-S70, A-L08): the iPhone's share extension (`ShareViewController`). Text another
 * app shares, a web link among it, comes here as plain text; its card ([ShareSheet]) comes up over the app that shared
 * in a window that is otherwise clear, reads it ([sharedContent]) and saves it for Add ([ShareInbox], in
 * [PeekApplication.shareDefaults]), which the app's window takes once it comes to the front. The card speaks the
 * app's own language (`deliveryTrackerLocale` in those defaults, B-SE10), else the first of the phone's it speaks.
 *
 * Cancel and Done finish it, back to the app that shared, as the extension completes its request (saved) or cancels
 * it ([RESULT_OK] or [RESULT_CANCELED] to an app that waits for one). It stands in the sharing app's task; where the
 * share started a task for it, that task (an empty `taskAffinity`, `excludeFromRecents`) goes with it, so Recents keeps
 * none, as ParcelLinkActivity's. The app's stores aren't made for it, and where they are, it doesn't bring the app to
 * the front for them ([AppForeground]): the extension runs without the app.
 */
class ShareActivity : ComponentActivity() {
    private val app: PeekApplication get() = application as PeekApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        systemBars()
        super.onCreate(savedInstanceState)
        val defaults = app.shareDefaults()
        val copy = ExtensionLocalizer(defaults.string(AppLanguage.storageKey), Device.preferredLanguages())
        // The window's name, which TalkBack reads as it comes, in the card's language rather than the label's.
        title = copy.text("shareExtension.title")
        setContent {
            // The night mode changed (the phone's appearance, or Peek's own night set as its appearance store is made,
            // which may be here, before the app's window ever was): the card takes it as it is, without starting again
            // (`configChanges="uiMode"`), its window's theme light whatever the night. Its dim follows the configuration,
            // and the bars are set again once androidx has set the window's first ones again, as it does on each change.
            DisposableEffect(LocalConfiguration.current) {
                systemBars()
                onDispose {}
            }
            // The sheet dims what it stands over as the phone's appearance would have it; the card is its own.
            PeekTheme(dark = isPhoneInDarkTheme(), carrierIdentities = null) {
                ShareSheet(
                    copy = copy,
                    read = { sharedContent(intent) },
                    save = { ShareInbox.save(it, defaults) },
                    onClosed = ::close,
                )
            }
        }
    }

    /**
     * The bars with no scrim, as the iPhone's sheet runs under the home indicator. The status bar's icons are over the
     * dimmed app, for the phone's appearance, which the app that shared most likely has, rather than for Peek's own,
     * which its night mode follows. The navigation bar's are over the card where its sheet reaches the window's foot (a
     * compact window): dark, the card light in either appearance; over the dimmed app beside a regular window's form
     * sheet, for the phone's appearance too.
     */
    private fun systemBars() {
        val window = windowManager.currentWindowMetrics.bounds
        val density = resources.displayMetrics.density
        val regular = window.width() / density >= REGULAR_WIDTH.value && window.height() / density >= REGULAR_HEIGHT.value
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { phoneIsDark(this) },
            navigationBarStyle = if (regular && phoneIsDark(this)) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        )
    }

    private fun close(saved: Boolean) {
        if (isFinishing) return
        setResult(if (saved) RESULT_OK else RESULT_CANCELED)
        if (isTaskRoot) finishAndRemoveTask() else finish()
    }

    companion object {
        /**
         * What `SharedContent.read` finds in the shared text: [Intent.EXTRA_SUBJECT], where an app names what it shares
         * (a page's title), as the item's title; [Intent.EXTRA_TEXT] as its text, a link included. Null for anything
         * but a share, or extras that don't unparcel.
         */
        fun sharedContent(intent: Intent?): SharedContent.Read? {
            if (intent?.action != Intent.ACTION_SEND) return null
            return try {
                SharedContent.read(
                    titles = listOf(intent.getCharSequenceExtra(Intent.EXTRA_SUBJECT)?.toString()),
                    texts = listOf(intent.getCharSequenceExtra(Intent.EXTRA_TEXT)?.toString()),
                )
            } catch (_: RuntimeException) {
                null
            }
        }
    }
}
