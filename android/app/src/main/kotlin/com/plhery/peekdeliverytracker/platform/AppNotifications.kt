package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppClock
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeRoute
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import java.util.UUID

/**
 * The notifications the app posts from a push, where the iPhone's come from APNs and `UNUserNotificationCenter`
 * shows them (AppDelegate).
 *
 * Two channels, the parcels' and the friends', both of high importance (a banner with sound, as iOS shows a push) and
 * no actions, as the iPhone's notifications have none: named in the app's language ([updateChannels]), as the app
 * makes its stores and whenever its language changes.
 *
 * [post] takes a push's data as the service sends it (`title`, `body`, `kind`, `friend_id`, `link_id`, `parcel_id`):
 * what Firebase's `onMessageReceived` hands it on its own thread ([PeekMessagingService]), and the debug build's
 * receiver without a server. The parcel's, the link's or the friend's notification replaces the one before for the
 * same (its tag the push's `apns-collapse-id`: the parcel's id, `link-<id>`, `friend-<id>`), and is grouped as iOS
 * threads it (the parcel's id, `link-<id>`, `friends`). One without a collapse id, such as the service's welcome, has a
 * tag of its own, so it stays beside every other, an earlier process's included, as each stays on the iPhone. The
 * friends' are bundled under a summary of their own ([FRIENDS_SUMMARY]), as iOS stacks their thread; a parcel's or a
 * link's group only ever holds one. A friend who accepted while the app is on screen comes into the shade without a
 * sound or a banner (`willPresent`'s `[.list]`) and [friendActivity] refreshes the friends' notices on the main thread
 * (`.didReceiveFriendActivity`).
 *
 * Opening one opens the app's window with the push's data, whose route the window follows
 * ([com.plhery.peekdeliverytracker.ui.deliveries.DeliveriesInbox]), through [OPENING], an alias of MainActivity that
 * only the app can start: [payload] reads no other intent, so no other app can open a route in the window. The
 * alias's intent reaches the window already open (`onNewIntent`), as Android matches `FLAG_ACTIVITY_CLEAR_TOP` and
 * `singleTop` on the activity an alias stands for.
 *
 * The iPhone's badge, which the service sets to 1 and the app clears at launch and on coming back: Android's launcher
 * shows a dot for the notifications in the shade, which opening one or clearing it takes away, and the app sets no count.
 */
class AppNotifications(
    private val context: Context,
    /** The app is on screen (`willPresent` comes only then). */
    private val onScreen: () -> Boolean = { false },
    /** `.didReceiveFriendActivity`: a friend accepted while the app is on screen. On the main thread. */
    private val friendActivity: () -> Unit = {},
) {
    private val manager = NotificationManagerCompat.from(context)
    private val main = Handler(Looper.getMainLooper())

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
     * Posts the push with data `data`, on the thread it comes on: nothing without a title or a body, nor where the phone
     * doesn't allow it. The channels are made first if the app never made them, in the language last saved.
     */
    fun post(data: Map<String, String>) {
        val title = data["title"].orEmpty()
        val defaults = PreferencesDefaults.standard(context)
        val use24HourTime = !defaults.bool(PreferencesDefaults.USE_12_HOUR_TIME)
        val body = deviceClockBody(data, use24HourTime = use24HourTime) ?: data["body"].orEmpty()
        if (title.isEmpty() && body.isEmpty()) return
        if (!manager.areNotificationsEnabled()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (manager.getNotificationChannel(PARCELS) == null || manager.getNotificationChannel(FRIENDS) == null) {
            updateChannels(Localizer(AppLanguage.current(defaults.string(AppLanguage.storageKey), Device.preferredLanguages()), variant = "android"))
        }
        val route = NativeRoute(remoteNotification = data)
        val friend = route is NativeRoute.Friend
        val tag = tag(route) ?: "$OWN_TAG${UUID.randomUUID()}"
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
        manager.notify(tag, ID, notification)
        if (friend) manager.notify(FRIENDS_SUMMARY, ID, friendsSummary())
        if (quiet) {
            if (Looper.myLooper() == Looper.getMainLooper()) friendActivity() else main.post { friendActivity() }
        }
    }

    /**
     * `removeDeliveredNotifications` for a friend acknowledged (`FriendsStore.acknowledge`): their notification goes,
     * and the friends' summary with the last of them. The system cancels in its own time, so the shade read straight
     * after may still hold the one just cancelled: it doesn't count.
     */
    fun removeFriend(friendId: UUID) {
        val tag = friendTag(friendId)
        manager.cancel(tag, ID)
        val shown = context.getSystemService(NotificationManager::class.java).activeNotifications
        if (shown.none { it.tag != FRIENDS_SUMMARY && it.tag != tag && it.notification.group == FRIENDS_GROUP }) manager.cancel(FRIENDS_SUMMARY, ID)
    }

    /**
     * The friends' group's summary, which bundles their notifications as iOS stacks the `friends` thread: silent itself
     * (`GROUP_ALERT_CHILDREN`), each friend's sounding and showing its banner as it would alone. It goes with the last
     * friend's: the shade takes it as that one is opened (auto-cancel), the app once the friend is acknowledged
     * ([removeFriend]).
     */
    private fun friendsSummary(): Notification = NotificationCompat.Builder(context, FRIENDS)
        .setSmallIcon(R.drawable.ic_notification)
        .setColor(ContextCompat.getColor(context, R.color.mark_yellow))
        .setContentTitle(manager.getNotificationChannel(FRIENDS)?.name)
        .setGroup(FRIENDS_GROUP)
        .setGroupSummary(true)
        .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
        .setAutoCancel(true)
        .build()

    /**
     * What opens the window with the push's data, through [OPENING], one of its own for each tag, so none takes
     * another's place.
     */
    private fun opening(data: Map<String, String>, tag: String): PendingIntent {
        val extras = Bundle().apply { KEYS.forEach { key -> data[key]?.let { putString(key, it) } } }
        val intent = Intent()
            .setComponent(ComponentName(context, OPENING))
            .setAction(ACTION_OPEN)
            .setIdentifier(tag)
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

        /**
         * The alias of MainActivity a notification opens (the manifest's): not exported and without an intent filter,
         * so only the app's own PendingIntents start it. Android hands the window the intent with the alias as its
         * component.
         */
        const val OPENING = "com.plhery.peekdeliverytracker.NotificationOpening"

        /** The id of every notification: the tag tells them apart. */
        const val ID = 1

        /** The tag of the friends' group's summary. */
        const val FRIENDS_SUMMARY = "friends"

        /** The push's keys the service sends, all strings. */
        val KEYS = listOf("title", "body", "kind", "friend_id", "link_id", "parcel_id")

        /** How the tag of a notification without a collapse id begins, a random UUID after it. */
        private const val OWN_TAG = "push-"

        private const val FRIENDS_GROUP = "friends"

        /**
         * The push's data a notification the app posted opened the window with, or null for any other intent: one that
         * came through [OPENING] alone, which no other app can start.
         */
        fun payload(intent: Intent?): Map<String, String>? {
            if (intent?.action != ACTION_OPEN || intent.component?.className != OPENING) return null
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
            is NativeRoute.Friend -> FRIENDS_GROUP
            is NativeRoute.Link -> "link-${route.id}"
            is NativeRoute.Parcel -> route.id.toString()
            else -> null
        }

        private fun friendTag(friendId: UUID): String = "friend-$friendId"

        /**
         * A delivery's time on the phone's clock, wherever it is now: the service sends the scan's instant (`scan_at`)
         * and the sentence with the time left out, for a delivery on the phone's day and on another (`body_same_day`,
         * `body_other_day`), and the language tag its day is written in (`date_locale`), as `deviceClockFields` in
         * src/server/push.ts sends them. Estimates and deliveries without a usable scan time use the server's
         * alternate body when the app uses a 12 hour clock.
         */
        fun deviceClockBody(
            data: Map<String, String>,
            now: Instant = AppClock.now(),
            zone: ZoneId = ZoneId.systemDefault(),
            use24HourTime: Boolean = true,
        ): String? {
            val alternate = if (use24HourTime) null else data["body_12_hour"]
            val sameDay = data["body_same_day"] ?: return alternate
            val otherDay = data["body_other_day"] ?: return alternate
            val scanned = data["scan_at"]?.let { runCatching { Instant.parse(it) }.getOrNull() } ?: return alternate
            if (scanned.isAfter(now)) return alternate
            val local = scanned.atZone(zone)
            val locale = data["date_locale"]?.let(Locale::forLanguageTag)?.takeIf { it.language.isNotEmpty() } ?: Locale.getDefault()
            val time = if (use24HourTime) "%02d:%02d".format(Locale.ROOT, local.hour, local.minute)
                else Localizer(AppLanguage.fromTag(locale.toLanguageTag()) ?: AppLanguage.En, use24HourTime = false).clockTime(scanned, zone)
            val day = android.icu.text.DateFormat.getInstanceForSkeleton("dMMMM", locale).apply {
                timeZone = android.icu.util.TimeZone.getTimeZone(zone.id)
            }.format(Date.from(scanned))
            val today = local.toLocalDate() == now.atZone(zone).toLocalDate()
            return (if (today) sameDay else otherDay).replace("{{time}}", time).replace("{{date}}", day)
        }
    }
}
