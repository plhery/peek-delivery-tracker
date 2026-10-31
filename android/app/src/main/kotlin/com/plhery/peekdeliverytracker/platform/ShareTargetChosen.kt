package com.plhery.peekdeliverytracker.platform

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Build
import android.service.chooser.ChooserResult
import androidx.core.content.IntentCompat

/**
 * Hears where the system's share sheet sent something: the sheet opened with a [sender]
 * (`Intent.createChooser(send, title, sender)`) sends it back here with the app chosen (`Intent.EXTRA_CHOSEN_COMPONENT`),
 * or from Android 15 its own Copy or Edit (`Intent.EXTRA_CHOOSER_RESULT`), as the iPhone's Copy completes a share, and
 * that share's `chosen` runs, even once the window that asked has gone. Backing out of the share sheet sends nothing,
 * and nothing runs. Android tells the target chosen, not whether it went on to send, where the iPhone's activity sheet
 * tells whether the share was completed. Not exported: only the share sheet, through the mutable PendingIntent it was
 * given, reaches it.
 */
class ShareTargetChosen : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val share = intent.getIntExtra(EXTRA_SHARE, -1)
        val chosen = synchronized(waiting) { waiting.remove(share) } ?: return
        if (went(intent)) chosen()
    }

    private fun went(intent: Intent): Boolean {
        if (IntentCompat.getParcelableExtra(intent, Intent.EXTRA_CHOSEN_COMPONENT, ComponentName::class.java) != null) return true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return false
        val result = intent.getParcelableExtra(Intent.EXTRA_CHOOSER_RESULT, ChooserResult::class.java) ?: return false
        return result.type != ChooserResult.CHOOSER_RESULT_UNKNOWN
    }

    companion object {
        private const val EXTRA_SHARE = "com.plhery.peekdeliverytracker.extra.SHARE"

        /** The latest shares whose target hasn't come yet, by their number; a share sheet backed out of never sends. */
        private val waiting = LinkedHashMap<Int, () -> Unit>()
        private var shares = 0
        private const val KEPT = 16

        /** What a share sheet sends the target chosen to, for `chosen`, once. */
        fun sender(context: Context, chosen: () -> Unit): IntentSender {
            val share = synchronized(waiting) {
                val share = ++shares
                waiting[share] = chosen
                while (waiting.size > KEPT) waiting.remove(waiting.keys.first())
                share
            }
            val intent = Intent(context, ShareTargetChosen::class.java).putExtra(EXTRA_SHARE, share)
            val flags = PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT
            return PendingIntent.getBroadcast(context, share, intent, flags).intentSender
        }
    }
}
