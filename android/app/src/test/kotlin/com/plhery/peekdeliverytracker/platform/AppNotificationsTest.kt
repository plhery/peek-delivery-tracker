package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NativeRoute
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf

/**
 * The notifications posted from a push's data (C-W12, C-W13, C-W14, C-W18, C-W20): the channel, the tag and the group as
 * the service's collapse id and thread, the mark's eyes in the accent, the window opened with the push's data, a
 * later one for the same parcel in place of the first, and a friend who accepted while the app is on screen posted
 * without a sound or a banner, the friends' notices refreshed. Synthetic ids only.
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

    private fun friendPush() = mapOf("title" to "Sam", "body" to "Sam accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$friend")

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
        assertEquals(ComponentName(context, MainActivity::class.java), intent.component)
        assertEquals(mapOf("title" to "Books", "body" to "Out for delivery", "parcel_id" to "$parcel"), AppNotifications.payload(intent))
        assertEquals(NativeRoute.Parcel(parcel), NativeRoute(remoteNotification = AppNotifications.payload(intent)!!))
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
        assertEquals(0, friendActivity)
        assertEquals(NativeRoute.Friend(friend), NativeRoute(remoteNotification = AppNotifications.payload(opened(notification))!!))
    }

    /** `willPresent`: `[.list]` for a friend's while the app is on screen, and `.didReceiveFriendActivity`. */
    @Test
    fun aFriendsNotificationWhileTheAppIsOnScreenIsSilentAndRefreshesTheNotices() {
        onScreen = true
        notifications.post(friendPush())
        val notification = posted("friend-$friend")
        assertEquals("No heads-up, no sound", NotificationCompat.GROUP_ALERT_SUMMARY, NotificationCompat.getGroupAlertBehavior(notification))
        assertNull(notification.sound)
        assertEquals(1, friendActivity)
    }

    /** `willPresent`: `[.banner, .list, .sound]` for every other one. */
    @Test
    fun aParcelsNotificationWhileTheAppIsOnScreenShowsAsUsual() {
        onScreen = true
        notifications.post(parcelPush())
        assertEquals(NotificationCompat.GROUP_ALERT_ALL, NotificationCompat.getGroupAlertBehavior(posted("$parcel")))
        assertEquals(0, friendActivity)
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
