package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeRoute
import java.time.Instant
import java.time.ZoneId
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

/**
 * What tapping the notification the app posts for the push with data `data` starts: its own content intent, as posted,
 * which only the app can send.
 */
internal fun notificationTap(data: Map<String, String>): Intent {
    val context = ApplicationProvider.getApplicationContext<Context>()
    shadowOf(context as Application).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
    AppNotifications(context).post(data)
    val tag = AppNotifications.tag(NativeRoute(remoteNotification = data))
    val shade = shadowOf(context.getSystemService(NotificationManager::class.java))
    return shadowOf(requireNotNull(shade.getNotification(tag, AppNotifications.ID)) { "Nothing posted as $tag" }.contentIntent).savedIntent
}

/**
 * Whether Android keeps `notification` from sounding and showing a banner for its group's sake, as the system and the
 * shade both ask it (`Notification.suppressAlertingDueToGrouping`, hidden from apps): a child that leaves the alerts to
 * its summary, or a summary that leaves them to its children.
 */
private fun mutedByItsGroup(notification: Notification): Boolean =
    Notification::class.java.getMethod("suppressAlertingDueToGrouping").invoke(notification) as Boolean

/**
 * The notifications posted from a push's data (C-W12, C-W13, C-W14, C-W18, C-W20): the channel, the tag and the group as
 * the service's collapse id and thread, the mark's eyes in the accent, the window opened with the push's data through
 * an alias no other app can start, a later one for the same parcel in place of the first, one without a parcel or a
 * friend beside those before, the friends' stacked under a summary that never alerts, and a friend who accepted while
 * the app is on screen posted without a sound or a banner, the friends' notices refreshed on the main thread.
 * Synthetic ids only.
 */
@RunWith(AndroidJUnit4::class)
class AppNotificationsTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val manager = context.getSystemService(NotificationManager::class.java)
    private val shade get() = shadowOf(manager)
    private var onScreen = false
    private var friendActivity = 0
    private val notifications = AppNotifications(context, onScreen = { onScreen }, friendActivity = { friendActivity += 1 })

    private val parcel = UUID(0, 101)
    private val friend = UUID(0, 202)
    private val link = "33333333333A"

    @Before
    fun allow() {
        shadowOf(context as Application).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun parcelPush(body: String = "Out for delivery") = mapOf("title" to "Books", "body" to body, "parcel_id" to "$parcel")

    private fun friendPush(id: UUID = friend) = mapOf("title" to "Sam", "body" to "Sam accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$id")

    private fun posted(tag: String): Notification = requireNotNull(shade.getNotification(tag, AppNotifications.ID)) { "Nothing posted as $tag" }

    /** What opening `notification` starts. */
    private fun opened(notification: Notification): Intent = shadowOf(notification.contentIntent).savedIntent

    @Test
    fun aParcelsNotificationGoesInTheParcelsChannelGroupedAndTaggedByItsParcel() {
        notifications.post(parcelPush())
        val notification = posted("$parcel")
        assertEquals(AppNotifications.PARCELS, notification.channelId)
        assertEquals("$parcel", notification.group)
        assertEquals("Books", notification.extras.getCharSequence(Notification.EXTRA_TITLE).toString())
        assertEquals("Out for delivery", notification.extras.getCharSequence(Notification.EXTRA_TEXT).toString())
        assertEquals(R.drawable.ic_notification, notification.smallIcon.resId)
        assertEquals(ContextCompat.getColor(context, R.color.mark_yellow), notification.color)
        assertTrue("Opening it takes it away", notification.flags and Notification.FLAG_AUTO_CANCEL != 0)
        assertTrue("No actions", notification.actions.isNullOrEmpty())
        assertEquals(NotificationCompat.GROUP_ALERT_ALL, NotificationCompat.getGroupAlertBehavior(notification))

        val intent = opened(notification)
        assertEquals(ComponentName(context, AppNotifications.OPENING), intent.component)
        assertEquals(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to "$parcel"), AppNotifications.payload(intent))
        assertEquals(NativeRoute.Parcel(parcel), NativeRoute(remoteNotification = AppNotifications.payload(intent)!!))
    }

    /** A delivery as the service sends it, with its time left for the phone to write (`deviceClockFields`). */
    private fun deliveredPush(scanAt: String, locale: String = "en-CH") = parcelPush("Books · delivered at 14:32") + mapOf(
        "scan_at" to scanAt,
        "body_same_day" to if (locale == "de-CH") "Books · um {{time}} Uhr zugestellt" else "Books · delivered at {{time}}",
        "body_other_day" to if (locale == "de-CH") "Books · am {{date}} um {{time}} Uhr zugestellt" else "Books · delivered on {{date}} at {{time}}",
        "date_locale" to locale,
    )

    @Test
    fun aDeliveryIsToldOnThePhonesClockWhereverItIs() {
        val now = Instant.parse("2026-09-07T12:40:00Z")
        val pacific = ZoneId.of("America/Los_Angeles")
        fun told(data: Map<String, String>, zone: ZoneId = pacific) = AppNotifications.deviceClockBody(data, now, zone)
        assertEquals("Books · delivered at 05:32", told(deliveredPush("2026-09-07T12:32:00.000Z")))
        assertEquals("Books · delivered at 14:32", told(deliveredPush("2026-09-07T12:32:00.000Z"), ZoneId.of("Europe/Zurich")))
        // The evening before, there, though it is the same day in Zurich.
        assertEquals("Books · delivered on 6 September at 16:51", told(deliveredPush("2026-09-06T23:51:00.000Z")))
        assertEquals("Books · am 6. September um 16:51 Uhr zugestellt", told(deliveredPush("2026-09-06T23:51:00.000Z", "de-CH")))
        assertEquals(
            "Books · delivered on 6 September at 4:51 PM",
            AppNotifications.deviceClockBody(deliveredPush("2026-09-06T23:51:00.000Z"), now, pacific, use24HourTime = false),
        )
        // A scan this clock calls the future, or a push without the fields, keeps the service's body.
        assertNull(told(deliveredPush("2026-09-07T12:50:00.000Z")))
        assertNull(told(deliveredPush("not a time")))
        assertNull(told(parcelPush()))

        notifications.post(deliveredPush(now.minusSeconds(60).toString()))
        val written = AppNotifications.deviceClockBody(deliveredPush(now.minusSeconds(60).toString()))
        assertEquals(written, posted("$parcel").extras.getCharSequence(Notification.EXTRA_TEXT).toString())
    }

    @Test
    fun aTimedEstimateUsesTheClockPreferenceWhenPosted() {
        val defaults = PreferencesDefaults.standard(context)
        val push = parcelPush("Arriving at 16:00") + ("body_12_hour" to "Arriving at 4:00 pm")
        notifications.post(push)
        assertEquals("Arriving at 16:00", posted("$parcel").extras.getCharSequence(Notification.EXTRA_TEXT).toString())
        try {
            defaults.set(true, forKey = PreferencesDefaults.USE_12_HOUR_TIME)
            notifications.post(push)
            assertEquals("Arriving at 4:00 pm", posted("$parcel").extras.getCharSequence(Notification.EXTRA_TEXT).toString())
        } finally {
            defaults.removeObject(forKey = PreferencesDefaults.USE_12_HOUR_TIME)
        }
    }

    @Test
    fun aLaterOneForTheSameParcelTakesThePlaceOfTheFirst() {
        notifications.post(parcelPush("In transit"))
        notifications.post(parcelPush("Delivered"))
        assertEquals(1, shade.size())
        assertEquals("Delivered", posted("$parcel").extras.getCharSequence(Notification.EXTRA_TEXT).toString())
    }

    @Test
    fun aLinksNotificationIsTaggedAndGroupedByItsLink() {
        notifications.post(mapOf("title" to "Books", "body" to "Delivered", "link_id" to link))
        val notification = posted("link-$link")
        assertEquals(AppNotifications.PARCELS, notification.channelId)
        assertEquals("link-$link", notification.group)
        assertEquals(NativeRoute.Link(link), NativeRoute(remoteNotification = AppNotifications.payload(opened(notification))!!))
    }

    @Test
    fun aFriendsNotificationGoesInTheFriendsChannelWithASoundWhileTheAppIsAway() {
        notifications.post(friendPush())
        val notification = posted("friend-$friend")
        assertEquals(AppNotifications.FRIENDS, notification.channelId)
        assertEquals("friends", notification.group)
        assertEquals(NotificationCompat.GROUP_ALERT_ALL, NotificationCompat.getGroupAlertBehavior(notification))
        assertFalse("A sound and a banner", mutedByItsGroup(notification))
        assertEquals(0, friendActivity)
        assertEquals(NativeRoute.Friend(friend), NativeRoute(remoteNotification = AppNotifications.payload(opened(notification))!!))
    }

    /** `willPresent`: `[.list]` for a friend's while the app is on screen, and `.didReceiveFriendActivity`. */
    @Test
    fun aFriendsNotificationWhileTheAppIsOnScreenIsSilentAndRefreshesTheNotices() {
        onScreen = true
        notifications.post(friendPush())
        val notification = posted("friend-$friend")
        // A child of the friends' group that leaves its alerts to the summary, which leaves them to its children.
        assertEquals("friends", notification.group)
        assertEquals(NotificationCompat.GROUP_ALERT_SUMMARY, NotificationCompat.getGroupAlertBehavior(notification))
        assertTrue("No heads-up, no sound", mutedByItsGroup(notification))
        assertTrue("Nor from its summary", manager.activeNotifications.filter { it.notification.group == "friends" }.all { mutedByItsGroup(it.notification) })
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(1, friendActivity)
    }

    /** Firebase hands a push over on a worker thread: the friends' notices are refreshed on the main thread. */
    @Test
    fun aPushHandedOverOnAWorkerThreadRefreshesTheNoticesOnTheMainThread() {
        var refreshedOn: Thread? = null
        val handed = AppNotifications(context, onScreen = { true }, friendActivity = { refreshedOn = Thread.currentThread() })
        Thread { handed.post(friendPush()) }.apply {
            start()
            join()
        }
        assertNotNull("Posted where it came", shade.getNotification("friend-$friend", AppNotifications.ID))
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(Looper.getMainLooper().thread, refreshedOn)
    }

    /** `willPresent`: `[.banner, .list, .sound]` for every other one. */
    @Test
    fun aParcelsNotificationWhileTheAppIsOnScreenShowsAsUsual() {
        onScreen = true
        notifications.post(parcelPush())
        assertEquals(NotificationCompat.GROUP_ALERT_ALL, NotificationCompat.getGroupAlertBehavior(posted("$parcel")))
        assertFalse(mutedByItsGroup(posted("$parcel")))
        shadowOf(Looper.getMainLooper()).idle()
        assertEquals(0, friendActivity)
    }

    /**
     * Only the app opens a notification's window: its content intent names an alias of MainActivity that no other app
     * can start and that takes no implicit intent, and an intent any app could send the window, with the same action
     * and data, is no notification's.
     */
    @Test
    fun aNotificationOpensTheWindowThroughAnAliasNoOtherAppCanStart() {
        notifications.post(parcelPush())
        val intent = opened(posted("$parcel"))
        assertEquals(ComponentName(context, AppNotifications.OPENING), intent.component)
        val alias = context.packageManager.getActivityInfo(ComponentName(context, AppNotifications.OPENING), 0)
        assertEquals(MainActivity::class.java.name, alias.targetActivity)
        assertFalse("Not exported", alias.exported)
        assertTrue(context.packageManager.queryIntentActivities(Intent(AppNotifications.ACTION_OPEN).setPackage(context.packageName), 0).isEmpty())
        assertEquals(NativeRoute.Parcel(parcel), NativeRoute(remoteNotification = AppNotifications.payload(intent)!!))

        val forged = Intent(AppNotifications.ACTION_OPEN).putExtras(requireNotNull(intent.extras))
        assertNull("To the window", AppNotifications.payload(Intent(forged).setClass(context, MainActivity::class.java)))
        assertNull("To no window in particular", AppNotifications.payload(forged))
    }

    /**
     * The service's welcome has neither a parcel nor a link nor a friend, so no collapse id: each such one stays beside
     * those before, an earlier process's included. A process counts from its start again, so nothing it counts can
     * tell them apart: each has a tag of its own.
     */
    @Test
    fun oneWithoutAParcelStaysBesideThoseOfAnEarlierProcess() {
        val welcome = mapOf("title" to "Peek", "body" to "Welcome to Peek")
        notifications.post(welcome)
        // Another process: the notifications made again, the shade as the earlier one left it.
        AppNotifications(context).post(welcome)
        val posted = manager.activeNotifications.toList()
        assertEquals(2, posted.size)
        assertEquals("A tag of its own each", 2, posted.mapNotNull { it.tag }.distinct().size)
        posted.forEach { assertEquals(AppNotifications.PARCELS, it.notification.channelId) }
    }

    /**
     * iOS stacks the friends' thread: on Android their notifications are bundled under a summary of their own in the
     * friends' channel, which never sounds or shows a banner itself, and goes with the last of them.
     */
    @Test
    fun twoFriendsStackUnderASilentSummaryThatGoesWithTheLast() {
        val other = UUID(0, 203)
        notifications.post(parcelPush())
        notifications.post(friendPush())
        notifications.post(friendPush(other))
        val friends = manager.activeNotifications.filter { it.notification.group == "friends" }
        val (summaries, children) = friends.partition { it.notification.flags and Notification.FLAG_GROUP_SUMMARY != 0 }
        assertEquals(setOf("friend-$friend", "friend-$other"), children.map { it.tag }.toSet())
        val summary = summaries.single().notification
        assertEquals(AppNotifications.FRIENDS, summary.channelId)
        assertEquals(NotificationCompat.GROUP_ALERT_CHILDREN, NotificationCompat.getGroupAlertBehavior(summary))
        assertTrue("No sound or banner of its own", mutedByItsGroup(summary))
        children.forEach { assertFalse("Each friend's own sound and banner", mutedByItsGroup(it.notification)) }
        assertEquals(4, shade.size())

        notifications.removeFriend(friend)
        assertEquals("The other friend's and the summary stay", 3, shade.size())
        notifications.removeFriend(other)
        assertEquals(listOf("$parcel"), manager.activeNotifications.map { it.tag })
    }

    @Test
    fun eachOpensWithItsOwnData() {
        notifications.post(parcelPush())
        notifications.post(friendPush())
        val first = posted("$parcel").contentIntent
        val second = posted("friend-$friend").contentIntent
        assertNotEquals(first, second)
        assertEquals("$parcel", AppNotifications.payload(opened(posted("$parcel")))?.get("parcel_id"))
    }

    @Test
    fun aFriendAcknowledgedTakesOnlyTheirsAway() {
        notifications.post(parcelPush())
        notifications.post(friendPush())
        notifications.removeFriend(friend)
        assertNull(shade.getNotification("friend-$friend", AppNotifications.ID))
        assertNotNull(shade.getNotification("$parcel", AppNotifications.ID))
    }

    @Test
    fun nothingIsPostedWithoutWordsOrPermission() {
        notifications.post(mapOf("parcel_id" to "$parcel"))
        assertEquals(0, shade.size())
        shadowOf(context as Application).denyPermissions(Manifest.permission.POST_NOTIFICATIONS)
        notifications.post(parcelPush())
        assertEquals(0, shade.size())
    }

    /** Posted before the app ever made them, the channels are made in the language last saved. */
    @Test
    fun theChannelsAreMadeForAPushThatComesFirst() {
        PreferencesDefaults.standard(context).set(AppLanguage.Fr.rawValue, forKey = AppLanguage.storageKey)
        notifications.post(parcelPush())
        assertEquals("Livraisons", manager.getNotificationChannel(AppNotifications.PARCELS).name)
        assertEquals("Amis", manager.getNotificationChannel(AppNotifications.FRIENDS).name)
    }

    /** C-W20: high importance, named in each of the app's languages, renamed in place. */
    @Test
    fun theChannelsAreNamedInEachLanguage() {
        val names = AppLanguage.entries.associateWith { language ->
            notifications.updateChannels(Localizer(language, variant = "android"))
            manager.getNotificationChannel(AppNotifications.PARCELS).name.toString() to manager.getNotificationChannel(AppNotifications.FRIENDS).name.toString()
        }
        assertEquals("Deliveries" to "Friends", names[AppLanguage.En])
        assertEquals("Sendungen" to "Freunde", names[AppLanguage.De])
        assertEquals("Livraisons" to "Amis", names[AppLanguage.Fr])
        for ((language, pair) in names) {
            val localizer = Localizer(language, variant = "android")
            assertEquals(localizer.text("native.deliveries") to localizer.text("friends.title"), pair)
        }
        assertEquals(2, manager.notificationChannels.size)
        manager.notificationChannels.forEach { assertEquals(NotificationManager.IMPORTANCE_HIGH, it.importance) }
    }
}
