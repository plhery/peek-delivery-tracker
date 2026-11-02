package com.plhery.peekdeliverytracker.ui

import android.Manifest
import android.accessibilityservice.AccessibilityService
import android.app.ActivityOptions
import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import android.service.notification.StatusBarNotification
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.core.app.NotificationManagerCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.plhery.peekdeliverytracker.AppContainer
import com.plhery.peekdeliverytracker.MainActivity
import com.plhery.peekdeliverytracker.PeekApplication
import com.plhery.peekdeliverytracker.R
import com.plhery.peekdeliverytracker.core.AppConfiguration
import com.plhery.peekdeliverytracker.core.AppLanguage
import com.plhery.peekdeliverytracker.core.AuthUser
import com.plhery.peekdeliverytracker.core.AuthenticationState
import com.plhery.peekdeliverytracker.core.DateParser
import com.plhery.peekdeliverytracker.core.DeliveryApiError
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NotificationAuthorizationStatus
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ParcelLink
import com.plhery.peekdeliverytracker.core.api.ParcelLinkKind
import com.plhery.peekdeliverytracker.core.api.ParcelLinkRole
import com.plhery.peekdeliverytracker.core.api.ParcelNumberHint
import com.plhery.peekdeliverytracker.core.api.PublicPackage
import com.plhery.peekdeliverytracker.core.api.PublicPackageCarrierData
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import com.plhery.peekdeliverytracker.platform.AndroidPushNotifications
import com.plhery.peekdeliverytracker.platform.AppNotifications
import com.plhery.peekdeliverytracker.platform.BuildSettings
import com.plhery.peekdeliverytracker.platform.Device
import com.plhery.peekdeliverytracker.platform.PreferencesDefaults
import com.plhery.peekdeliverytracker.ui.deliveries.DemoCards
import com.plhery.peekdeliverytracker.ui.deliveries.FakeDeliveries
import com.plhery.peekdeliverytracker.ui.deliveries.LINK_CARD
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT
import com.plhery.peekdeliverytracker.ui.deliveries.NOTIFICATIONS_PROMPT_ENABLE
import com.plhery.peekdeliverytracker.ui.deliveries.ZurichTime
import com.plhery.peekdeliverytracker.ui.deliveries.waitsForMaps
import java.io.IOException
import java.time.Duration
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestName
import org.junit.runner.RunWith

/**
 * The app's notifications on this device (C-P01, C-ST20, C-W12 to C-W15, C-W17, C-W18, C-W20, A-W01, A-L10), which the
 * JVM tests stand in for with Robolectric's: POST_NOTIFICATIONS granted through UiAutomation (Android 13 and later) and
 * read as the store reads it, and the card's Turn on answered by it without a question; each kind posted from a push's
 * data, as Firebase's `onMessageReceived` will hand it, and read back from `activeNotifications` (channel, tag, group,
 * the mark's eyes, a later one for the same parcel in place of the first, a friend's quiet while the app is on screen
 * and taken away once acknowledged), the shade saved; the channels named in the app's language; a notification's own
 * content intent sent, which opens the app's window on its route, on the app's stores made offline. Each saved as
 * notifications/<name>.png in the test package's external files, which adb pulls. Nothing reaches a server. Run the
 * class on its own (`-e class`), as the stores are made once a process.
 *
 * Not revoked: Android ends an app's process as one of its runtime permissions is taken away, the test's with it. By
 * hand, on Android 13 and later, after `adb shell pm revoke <package> android.permission.POST_NOTIFICATIONS`: the
 * card's Turn on shows the system's question; Don't allow sends the card away and Settings › Notifications says they
 * may still be asked for; a second refusal, and Settings sends to the phone's settings.
 */
@RunWith(AndroidJUnit4::class)
class NotificationsDeviceTest {
    private val compose = createAndroidComposeRule<ComponentActivity>()
    private val name = TestName()

    @get:Rule
    val rules: RuleChain = RuleChain.outerRule(name).around(MadePreferences()).around(ZurichTime()).around(compose)

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val app = context.applicationContext as PeekApplication
    private val manager = context.getSystemService(NotificationManager::class.java)
    private val shots = DeviceShots(compose, "notifications")

    private val parcel = UUID.nameUUIDFromBytes("notifications-device-parcel".toByteArray())
    private val friend = UUID.nameUUIDFromBytes("notifications-device-friend".toByteArray())

    @After
    fun clear() {
        manager.cancelAll()
        // The channels back in the app's language.
        AppNotifications(context).updateChannels(appLocalizer())
        val open = windows()
        instrumentation.runOnMainSync { open.forEach { it.finishAndRemoveTask() } }
    }

    private fun appLocalizer(): Localizer =
        Localizer(AppLanguage.current(PreferencesDefaults.standard(context).string(AppLanguage.storageKey), Device.preferredLanguages()), variant = "android")

    /** POST_NOTIFICATIONS granted, as the user grants it, on Android 13 and later; Android 12 has none. */
    private fun allow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            instrumentation.uiAutomation.grantRuntimePermission(context.packageName, Manifest.permission.POST_NOTIFICATIONS)
        }
        assumeTrue("The app's notifications are on in the phone's settings", NotificationManagerCompat.from(context).areNotificationsEnabled())
    }

    private fun exists(tag: String) = compose.onAllNodes(hasTestTag(tag), useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()

    /** The app's notifications in the shade, by tag. */
    private fun shade(): Map<String?, StatusBarNotification> = manager.activeNotifications.associateBy { it.tag }

    private fun text(notification: Notification): String = notification.extras.getCharSequence(Notification.EXTRA_TEXT).toString()

    private fun parcelPush(body: String) = mapOf("title" to "Books", "body" to body, "parcel_id" to "$parcel")

    private fun friendPush() = mapOf("title" to "Peek", "body" to "Sam accepted your invitation", "kind" to "friend_accepted", "friend_id" to "$friend")

    private fun linkPush() = mapOf("title" to "Parcel", "body" to "Delivered", "link_id" to LINK)

    /** The shade pulled down and saved as `name`, then put away. */
    private fun shadeShot(name: String) {
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS)
        Thread.sleep(1_500)
        shots.take(name, ownWindow = false)
        instrumentation.uiAutomation.performGlobalAction(AccessibilityService.GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE)
        Thread.sleep(500)
    }

    /** The app's windows in front. */
    private fun windows(): List<MainActivity> {
        var found = emptyList<MainActivity>()
        instrumentation.runOnMainSync {
            found = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).filterIsInstance<MainActivity>()
        }
        return found
    }

    @Test
    fun thePermissionGrantedIsReadAsTheStoreReadsItAndTheCardsTurnOnAsksNothing() {
        allow()
        val notifications = AndroidPushNotifications(context, app.notificationPermission)
        assertEquals(NotificationAuthorizationStatus.Authorized, runBlocking { notifications.authorizationStatus() })

        val reader = AuthenticationState.SignedIn(AuthUser(UUID(0, 11), email = "reader@example.com"))
        val source = FakeDeliveries(DemoCards.parcels(), reader).apply {
            shouldInviteNotifications.value = true
            askPhone = { if (!notifications.requestAuthorization()) throw DeliveryApiError.NotificationsDenied() }
        }
        compose.waitsForMaps()
        compose.setContent { RootHost(state = reader, reduceMotion = true, source = source) }
        compose.waitUntil(5_000) { exists(NOTIFICATIONS_PROMPT) }
        shots.take("card")
        compose.onNodeWithTag(NOTIFICATIONS_PROMPT_ENABLE).performClick()
        compose.waitUntil(5_000) { !exists(NOTIFICATIONS_PROMPT) }
        assertTrue(source.notificationsEnabledOnDevice.value)
        shots.take("card_answered")
    }

    @Test
    fun eachKindIsPostedAndReadBackFromTheShade() {
        allow()
        val away = AppNotifications(context)
        away.post(parcelPush("In transit"))
        away.post(linkPush())
        away.post(friendPush())

        val posted = shade()
        val first = posted.getValue("$parcel")
        assertEquals(AppNotifications.ID, first.id)
        assertEquals(AppNotifications.PARCELS, first.notification.channelId)
        assertEquals("$parcel", first.notification.group)
        assertEquals(R.drawable.ic_notification, first.notification.smallIcon.resId)
        assertEquals(context.getColor(R.color.mark_yellow), first.notification.color)
        assertEquals(Notification.GROUP_ALERT_ALL, first.notification.groupAlertBehavior)
        val link = posted.getValue("link-$LINK")
        assertEquals(AppNotifications.PARCELS, link.notification.channelId)
        assertEquals("link-$LINK", link.notification.group)
        val accepted = posted.getValue("friend-$friend")
        assertEquals(AppNotifications.FRIENDS, accepted.notification.channelId)
        assertEquals("friends", accepted.notification.group)
        shadeShot("shade")

        // A later one for the same parcel takes the first's place.
        away.post(parcelPush("Delivered"))
        assertEquals("Delivered", text(shade().getValue("$parcel").notification))
        assertEquals(3, shade().keys.count { it == "$parcel" || it == "link-$LINK" || it == "friend-$friend" })

        // A friend's while the app is on screen: in the shade, without a sound or a banner.
        var refreshed = 0
        AppNotifications(context, onScreen = { true }, friendActivity = { refreshed += 1 }).post(friendPush())
        assertEquals(Notification.GROUP_ALERT_SUMMARY, shade().getValue("friend-$friend").notification.groupAlertBehavior)
        assertEquals(1, refreshed)

        // Acknowledged, the friend's goes, and only theirs.
        away.removeFriend(friend)
        assertFalse(shade().containsKey("friend-$friend"))
        assertTrue(shade().containsKey("$parcel"))
    }

    @Test
    fun theChannelsAreNamedInTheAppsLanguage() {
        val notifications = AppNotifications(context)
        notifications.updateChannels(Localizer(AppLanguage.De, variant = "android"))
        assertEquals("Sendungen", manager.getNotificationChannel(AppNotifications.PARCELS).name)
        assertEquals("Freunde", manager.getNotificationChannel(AppNotifications.FRIENDS).name)
        notifications.updateChannels(Localizer(AppLanguage.En, variant = "android"))
        assertEquals("Deliveries", manager.getNotificationChannel(AppNotifications.PARCELS).name)
        assertEquals("Friends", manager.getNotificationChannel(AppNotifications.FRIENDS).name)
        assertEquals(NotificationManager.IMPORTANCE_HIGH, manager.getNotificationChannel(AppNotifications.PARCELS).importance)
    }

    /** A link's notification opened: the app's window starts on the link's sheet, and its intent forgets the push. */
    @Test
    fun aNotificationsContentIntentOpensTheWindowOnItsRoute() {
        assumeTrue("The app's stores were made before this test, on the network: run the class on its own", app.container.value == null)
        app.makeStores = { application ->
            withContext(Dispatchers.IO) {
                // Offline, in demo mode, which prepares no browser check: nothing leaves the device.
                AppContainer(
                    application, BuildSettings.configuration.copy(mode = AppConfiguration.Mode.Demo),
                    http = OkHttpClient.Builder().addInterceptor { throw IOException("No network in this test") }.build(),
                    linkReader = { id, _ -> response(id) },
                )
            }
        }
        allow()
        AppNotifications(context).post(linkPush())
        val opening = checkNotNull(shade()["link-$LINK"]).notification.contentIntent
        val options = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            @Suppress("DEPRECATION")
            ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(ActivityOptions.MODE_BACKGROUND_ACTIVITY_START_ALLOWED).toBundle()
        } else {
            null
        }
        opening.send(context, 0, null, null, null, null, options)
        compose.waitUntil(20_000) { exists(LINK_CARD) }
        assertEquals(LINK, checkNotNull(app.container.value).links.route?.id)
        assertNull("The push's data forgotten", AppNotifications.payload(windows().single().intent))
        compose.waitForIdle()
        Thread.sleep(500)
        shots.take("opened", ownWindow = false)
    }
}

/** A synthetic parcel link's id. */
private const val LINK = "n4Tp7wKc2HzQ"

private fun ago(minutes: Long): String = DateParser.isoString(Instant.now().minus(Duration.ofMinutes(minutes)))

/** What the link `id` reads: a synthetic DPD parcel on its way. */
private fun response(id: String): PublicParcelResponse {
    val parcel = UUID.nameUUIDFromBytes(id.toByteArray())
    val scan = TrackingEvent(
        id = UUID.nameUUIDFromBytes("$id-scan".toByteArray()), packageId = parcel, stage = TrackingStage.InTransit,
        description = "In transit", location = "Zürich, CH", occurredAt = ago(95),
    )
    return PublicParcelResponse(
        link = ParcelLink(
            id = id, role = ParcelLinkRole.Viewer, kind = ParcelLinkKind.Lookup, createdAt = ago(600),
            numberShown = false, canKeep = false, gift = false, shared = false,
        ),
        parcel = PublicPackage(
            id = parcel, numberHint = ParcelNumberHint(head = "", tail = "3456"), label = "",
            carrier = CarrierId.Dpd, createdAt = ago(600), lastSyncedAt = ago(2), syncStatus = SyncStatus.Ok,
            carrierData = PublicPackageCarrierData(senderName = "Example Shop", destinationCountry = "CH"),
            notificationsMuted = false, trackingEvents = listOf(scan),
        ),
    )
}
