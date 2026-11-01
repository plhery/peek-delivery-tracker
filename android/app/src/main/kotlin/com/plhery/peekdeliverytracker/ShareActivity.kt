package com.plhery.peekdeliverytracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.ExtensionLocalizer
import com.plhery.peekdeliverytracker.core.ShareInbox
import com.plhery.peekdeliverytracker.core.SharedContent
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.ui.ShareSheet
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme

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
 * none, as ParcelLinkActivity's. The app's stores aren't made for it: the extension runs without the app.
 */
class ShareActivity : ComponentActivity() {
    private val app: PeekApplication get() = application as PeekApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        // The bars' icons over the dimmed app, for the phone's appearance, which the app that shared most likely has.
        val bars = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = bars, navigationBarStyle = bars)
        super.onCreate(savedInstanceState)
        val defaults = app.shareDefaults()
        val copy = ExtensionLocalizer(defaults.string(AppLanguage.storageKey), Device.preferredLanguages())
        setContent {
            // The sheet dims what it stands over as the phone's appearance would have it; the card is its own.
            PeekTheme(dark = isSystemInDarkTheme(), carrierIdentities = null) {
                ShareSheet(
                    copy = copy,
                    read = { sharedContent(intent) },
                    save = { ShareInbox.save(it, defaults) },
                    onClosed = ::close,
                )
            }
        }
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
