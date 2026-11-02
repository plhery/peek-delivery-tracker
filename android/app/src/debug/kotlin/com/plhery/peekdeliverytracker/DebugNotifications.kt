package com.plhery.peekdeliverytracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.plhery.peekdeliverytracker.platform.AppNotifications

/**
 * The debug build's stand-in for a push, until Firebase delivers them: posts the notification a push with the
 * broadcast's string extras would ([AppNotifications.post], the same keys as the service's data), as the app on the
 * phone would, in front or not, so opening it goes through the app's own alias as a push's does. Only a sender
 * holding DUMP reaches it (its manifest), as adb's shell does:
 *
 * ```
 * adb shell am broadcast -a com.plhery.peekdeliverytracker.debug.POST_NOTIFICATION \
 *   -n com.plhery.peekdeliverytracker/.DebugNotifications \
 *   --es title "Out for delivery" --es body "Your parcel arrives today" --es parcel_id <a parcel's id>
 * ```
 *
 * `--es link_id <link id>` posts a link's, `--es kind friend_accepted --es friend_id <friend's id>` a friend's.
 */
class DebugNotifications : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras ?: return
        val data = AppNotifications.KEYS.mapNotNull { key -> extras.getString(key)?.let { key to it } }.toMap()
        (context.applicationContext as PeekApplication).posted.post(data)
    }
}
