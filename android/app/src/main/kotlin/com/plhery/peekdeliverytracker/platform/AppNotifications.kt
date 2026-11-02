package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeRoute
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger

/**
 * The notifications the app posts from a push, where the iPhone's come from APNs and `UNUserNotificationCenter`
 * shows them (AppDelegate).
 *
 * Two channels, the parcels' and the friends', both of high importance (a banner with sound, as iOS shows a push) and
 * no actions, as the iPhone's notifications have none: named in the app's language ([updateChannels]), as the app
 * makes its stores and whenever its language changes.
 *
 * [post] takes a push's data as the service sends it (`title`, `body`, `kind`, `friend_id`, `link_id`, `parcel_id`):
 * what Firebase's `onMessageReceived` will hand it, and the debug build's receiver until then. The parcel's, the
 * link's or the friend's notification replaces the one before for the same (its tag the push's `apns-collapse-id`:
 * the parcel's id, `link-<id>`, `friend-<id>`), and is grouped as iOS threads it (the parcel's id, `link-<id>`,
 * `friends`). Opening it opens the app's window with the push's data, whose route the window follows
 * ([com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox]). A friend who accepted while the app is on screen
 * comes into the shade without a sound or a banner (`willPresent`'s `[.list]`) and [friendActivity] refreshes the
 * friends' notices (`.didReceiveFriendActivity`).
 *
 * The iPhone's badge, which the service sets to 1 and the app clears at launch and on coming back: Android's launcher
 * shows a dot for the notifications in the shade, which opening one or clearing it takes away, and the app sets no count.
 */
class AppNotifications(
    private val context: Context,
    /** The app is on screen (`willPresent` comes only then). */
    private val onScreen: () -> Boolean = { false },
    /** `.didReceiveFriendActivity`: a friend accepted while the app is on screen. */
    private val friendActivity: () -> Unit = {},
) {
    private val manager = NotificationManagerCompat.from(context)

    /** The channels in the language of `localizer`: made, or renamed in place, which keeps what the user set on them. */
    fun updateChannels(localizer: Localizer) {
        val system = context.getSystemService(NotificationManager::class.java)
        system.createNotificationChannels(
            listOf(
                NotificationChannel(PARCELS, localizer.text("native.deliveries"), NotificationManager.IMPORTANCE_HIGH),
                NotificationChannel(FRIENDS, localizer.text("friends.title"), NotificationManager.IMPORTANCE_HIGH),
            ),
        )
    }

    /**
     * Posts the push with data `data`: nothing without a title or a body, nor where the phone doesn't allow it. The
     * channels are made first if the app never made them, in the language last saved.
     */
    fun post(data: Map<String, String>) {
        val title = data["title"].orEmpty()
        val body = data["body"].orEmpty()
        if (title.isEmpty() && body.isEmpty()) return
        if (!manager.areNotificationsEnabled()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (manager.getNotificationChannel(PARCELS) == null || manager.getNotificationChannel(FRIENDS) == null) {
            val defaults = PreferencesDefaults.standard(context)
            updateChannels(Localizer(AppLanguage.current(defaults.string(AppLanguage.storageKey), Device.preferredLanguages()), variant = "android"))
        }
        val route = NativeRoute(remoteNotification = data)
        val friend = route is NativeRoute.Friend
        val tag = tag(route)
        val quiet = friend && onScreen()
        val notification = NotificationCompat.Builder(context, if (friend) FRIENDS else PARCELS)
            .setSmallIcon(R.drawable.ic_notification)
            .setColor(ContextCompat.getColor(context, R.color.mark_yellow))
            .setContentTitle(title.ifEmpty { null })
            .setContentText(body.ifEmpty { null })
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setGroup(group(route))
            .setSilent(quiet)
            .setContentIntent(opening(data, tag))
            .build()
        manager.notify(tag, if (tag != null) ID else nextId.incrementAndGet(), notification)
        if (quiet) friendActivity()
    }

    /** `removeDeliveredNotifications` for a friend acknowledged (`FriendsStore.acknowledge`): their notification goes. */
    fun removeFriend(friendId: UUID) {
        manager.cancel(friendTag(friendId), ID)
    }

    /** What opens the window with the push's data, one of its own for each tag, so none takes another's place. */
    private fun opening(data: Map<String, String>, tag: String?): PendingIntent {
        val extras = Bundle().apply { KEYS.forEach { key -> data[key]?.let { putString(key, it) } } }
        val intent = Intent(context, MainActivity::class.java)
            .setAction(ACTION_OPEN)
            .setIdentifier(tag ?: UUID.randomUUID().toString())
            .putExtras(extras)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object {
        /** The parcels' channel: their updates and those of the parcels followed through a link. */
        const val PARCELS = "parcels"

        /** The friends' channel: an invitation accepted. */
        const val FRIENDS = "friends"

        /** What a notification opens the window with ([payload]). */
        const val ACTION_OPEN = "com.plhery.peekdeliverytracker.action.OPEN_NOTIFICATION"

        /** The id of every notification with a tag: the tag tells them apart. */
        const val ID = 1

        /** The push's keys the service sends, all strings. */
        val KEYS = listOf("title", "body", "kind", "friend_id", "link_id", "parcel_id")

        private val nextId = AtomicInteger(ID)

        /** The push's data a notification opened the window with, or null for any other intent. */
        fun payload(intent: Intent?): Map<String, String>? {
            if (intent?.action != ACTION_OPEN) return null
            val extras = intent.extras ?: return emptyMap()
            return KEYS.mapNotNull { key -> extras.getString(key)?.let { key to it } }.toMap()
        }

        /** The push's `apns-collapse-id`: the tag of the notification it replaces. */
        fun tag(route: NativeRoute?): String? = when (route) {
            is NativeRoute.Friend -> friendTag(route.id)
            is NativeRoute.Link -> "link-${route.id}"
            is NativeRoute.Parcel -> route.id.toString()
            else -> null
        }

        /** The push's `thread-id`. */
        fun group(route: NativeRoute?): String? = when (route) {
            is NativeRoute.Friend -> "friends"
            is NativeRoute.Link -> "link-${route.id}"
            is NativeRoute.Parcel -> route.id.toString()
            else -> null
        }

        private fun friendTag(friendId: UUID): String = "friend-$friendId"
    }
}
