package com.plhery.peekdeliverytracker.platform

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.impl.WorkManagerImpl
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.unifiedpush.android.connector.data.PushMessage
import java.util.UUID

/**
 * F-Droid's pushes on a phone with no UnifiedPush distributor: the address is the inbox, under an installation token
 * kept until the address is deleted, and the inbox's check is scheduled (never run: WorkManager's test driver holds it
 * until its network is met, so nothing leaves the machine). A distributor's message is posted as a push's data is.
 */
@RunWith(AndroidJUnit4::class)
class UnifiedPushTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun start() {
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
    }

    /** The test driver goes with its database, so the tests after these run without WorkManager, as Google Play's do. */
    @After
    @SuppressLint("RestrictedApi")
    fun stop() {
        PushRoute.delete(context)
        WorkManagerTestInitHelper.closeWorkDatabase()
        WorkManagerImpl.setDelegate(null)
    }

    private fun address(tokens: PushTokens): String {
        var answer: Result<String>? = null
        tokens.token { answer = it }
        return requireNotNull(answer).getOrThrow()
    }

    private fun inboxCheck(): WorkInfo.State? =
        WorkManager.getInstance(context).getWorkInfosForUniqueWork("push-inbox").get().singleOrNull()?.state

    @Test
    fun restoredPreferencesDoNotOpenThePreviousInbox() {
        context.getSharedPreferences("unifiedpush", Context.MODE_PRIVATE).edit()
            .putString("installation", "previous").commit()
        assertNull(PushRoute.inboxToken(context))
        assertNotEquals("previous", PushRoute.address(context).token)
    }

    @Test
    fun withoutADistributorThePhoneIsReachedThroughItsInboxWhichItChecks() {
        // The tests' build has no service (Shared.xcconfig), where Store offers no pushes: its tokens are made here.
        val tokens = UnifiedPushTokens(context)
        val first = address(tokens)
        assertTrue(first, Regex("inbox [A-Za-z0-9_-]{43}").matches(first))
        assertEquals("The same phone, the same address", first, address(tokens))
        assertEquals(WorkInfo.State.ENQUEUED, inboxCheck())
        assertNull("Settings names no distributor", PushRoute.distributor.value)

        tokens.delete()
        assertEquals(WorkInfo.State.CANCELLED, inboxCheck())
        assertNotEquals("A deleted address is followed by a new one", first, address(tokens))
    }

    /**
     * Notifications turned off on the phone: the check takes nothing from the inbox, which keeps it, and stops until the
     * app asks for the address again with them on.
     */
    @Test
    fun notificationsTurnedOffOnThePhoneStopTheChecks() = runBlocking {
        val tokens = UnifiedPushTokens(context)
        address(tokens)
        assertEquals(WorkInfo.State.ENQUEUED, inboxCheck())
        shadowOf(context.getSystemService(NotificationManager::class.java)).setNotificationsEnabled(false)
        assertEquals(ListenableWorker.Result.success(), TestListenableWorkerBuilder<InboxCheck>(context).build().doWork())
        assertEquals(WorkInfo.State.CANCELLED, inboxCheck())

        shadowOf(context.getSystemService(NotificationManager::class.java)).setNotificationsEnabled(true)
        address(tokens)
        assertEquals(WorkInfo.State.ENQUEUED, inboxCheck())
    }

    @Test
    fun aDistributorsMessageIsPostedAsTheAppsNotification() {
        shadowOf(context as Application).grantPermissions(Manifest.permission.POST_NOTIFICATIONS)
        val parcel = UUID(0, 45)
        val service = Robolectric.buildService(PeekPushService::class.java).create().get()
        val data = """{"title":"Books","body":"Out for delivery","parcel_id":"$parcel"}""".encodeToByteArray()
        service.onMessage(PushMessage(data, decrypted = false), "default")
        val shade = shadowOf(context.getSystemService(NotificationManager::class.java))
        assertNull("A message that wasn't decrypted is dropped", shade.getNotification("$parcel", AppNotifications.ID))

        service.onMessage(PushMessage(data, decrypted = true), "default")
        val posted = requireNotNull(shade.getNotification("$parcel", AppNotifications.ID)) { "Nothing posted" }
        assertEquals("Books", posted.extras.getString(Notification.EXTRA_TITLE))
        assertEquals("Out for delivery", posted.extras.getCharSequence(Notification.EXTRA_TEXT)?.toString())
    }
}
