package com.plhery.peekdeliverytracker.core

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/** The phone's own access for the guest's notifications: asked while never answered, and never failing its caller. */
class DeviceNotificationAccessTest {
    @Test
    fun theGuestIsAskedWhileThePhoneWasNeverAnswered() = runTest {
        val notifications = StubPushNotifications(granted = true)
        DeviceNotificationAccess.system(notifications).request()
        assertEquals(listOf("request"), notifications.calls)
    }

    /** Android 13's second chance (a rationale, `NotDetermined`) is the account's to use, as iOS asks a guest once. */
    @Test
    fun theGuestIsNotAskedAgainOnceAnswered() = runTest {
        val notifications = StubPushNotifications(status = NotificationAuthorizationStatus.NotDetermined, answeredBefore = true)
        val access = DeviceNotificationAccess.system(notifications)
        access.request()
        assertEquals(NotificationAuthorizationStatus.NotDetermined, access.status())
        assertEquals(listOf("status"), notifications.calls)
    }

    @Test
    fun aQuestionThatCannotBeAskedFailsNobody() = runTest {
        val notifications = object : PushNotifications by StubPushNotifications() {
            override suspend fun requestAuthorization(): Boolean = throw IllegalStateException("No activity on screen")
        }
        DeviceNotificationAccess.system(notifications).request()
    }
}
