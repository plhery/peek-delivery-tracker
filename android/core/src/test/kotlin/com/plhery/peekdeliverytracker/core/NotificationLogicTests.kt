package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.ApiJson
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationStage
import com.plhery.peekdeliverytracker.core.api.PackageNotificationRequest
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingEvent
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.util.UUID
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

/** NotificationLogicTests.swift. The SwiftUI layouts it renders are the app's to test. */
class NotificationLogicTests {
    companion object {
        @JvmStatic
        @BeforeClass
        fun installIcu() = TestIcu.install()
    }

    /** A UserDefaults suite in memory: a second instance on the same values reads what the first one saved. */
    private class MemoryDefaults(val values: MutableMap<String, Boolean> = mutableMapOf()) : UserDefaults {
        override fun bool(forKey: String): Boolean = values[forKey] ?: false

        override fun set(value: Boolean, forKey: String) {
            values[forKey] = value
        }
    }

    @Test
    fun presetsMatchStageSetsRegardlessOfOrder() {
        assertEquals(NotificationPreset.All, NotificationPreset.matching(NotificationPreset.All.stages.reversed()))
        assertEquals(NotificationPreset.Important, NotificationPreset.matching(NotificationPreset.Important.stages.shuffled()))
        assertEquals(NotificationPreset.DeliveryDay, NotificationPreset.matching(NotificationPreset.DeliveryDay.stages.reversed()))
    }

    @Test
    fun unknownStageCombinationFallsBackToAllPreset() {
        assertEquals(NotificationPreset.All, NotificationPreset.matching(listOf(NotificationStage.Customs, NotificationStage.Delivered)))
    }

    @Test
    fun savingPresetClearsRetiredQuietHours() {
        val original = NotificationPreferences(
            enabledStages = NotificationPreset.Important.stages,
            quietHoursStart = "23:15", quietHoursEnd = "06:45", timezone = "Europe/Zurich",
        )
        var draft = NotificationPreferencesDraft(preferences = original)
        assertEquals(NotificationPreset.Important, draft.preset)
        draft = draft.copy(preset = NotificationPreset.DeliveryDay)
        val saved = draft.preferences(timezone = original.timezone)
        assertEquals(NotificationPreset.DeliveryDay.stages, saved.enabledStages)
        assertNull(saved.quietHoursStart)
        assertNull(saved.quietHoursEnd)
        assertEquals(original.timezone, saved.timezone)
    }

    @Test
    fun deviceNotificationStateRequiresPermissionRegistrationAndNoOptOut() {
        assertTrue(
            NotificationDevicePolicy.isEnabled(
                isDemo = false,
                status = NotificationAuthorizationStatus.Authorized,
                optedOut = false,
                nativePushRegistered = true,
                demoNotificationsEnabled = false,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.isEnabled(
                isDemo = false,
                status = NotificationAuthorizationStatus.Denied,
                optedOut = false,
                nativePushRegistered = true,
                demoNotificationsEnabled = false,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.isEnabled(
                isDemo = false,
                status = NotificationAuthorizationStatus.Authorized,
                optedOut = true,
                nativePushRegistered = true,
                demoNotificationsEnabled = false,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.isEnabled(
                isDemo = false,
                status = NotificationAuthorizationStatus.Authorized,
                optedOut = false,
                nativePushRegistered = false,
                demoNotificationsEnabled = false,
            ),
        )
    }

    @Test
    fun demoNotificationStateDoesNotDependOnApns() {
        assertTrue(
            NotificationDevicePolicy.isEnabled(
                isDemo = true,
                status = NotificationAuthorizationStatus.Denied,
                optedOut = true,
                nativePushRegistered = false,
                demoNotificationsEnabled = true,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.isEnabled(
                isDemo = true,
                status = NotificationAuthorizationStatus.Authorized,
                optedOut = false,
                nativePushRegistered = true,
                demoNotificationsEnabled = false,
            ),
        )
    }

    @Test
    fun remoteRegistrationPolicyAcceptsProvisionalPermission() {
        assertTrue(
            NotificationDevicePolicy.shouldRegisterForRemoteNotifications(
                status = NotificationAuthorizationStatus.Provisional,
                optedOut = false,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.shouldRegisterForRemoteNotifications(
                status = NotificationAuthorizationStatus.NotDetermined,
                optedOut = false,
            ),
        )
        assertFalse(
            NotificationDevicePolicy.shouldRegisterForRemoteNotifications(
                status = NotificationAuthorizationStatus.Authorized,
                optedOut = true,
            ),
        )
    }

    @Test
    fun invitationWaitsForPermissionCheckAndARealAccountWithParcels() {
        assertTrue(invitation())
        assertFalse(invitation(status = null))
        assertFalse(invitation(isAuthenticated = false))
        assertFalse(invitation(isDemo = true))
        assertFalse(invitation(hasParcels = false))
    }

    @Test
    fun invitationRespectsPermissionAndPreviousChoices() {
        assertFalse(invitation(status = NotificationAuthorizationStatus.Denied))
        assertFalse(invitation(status = NotificationAuthorizationStatus.Ephemeral))
        assertFalse(invitation(enabled = true))
        assertFalse(invitation(optedOut = true))
        assertFalse(invitation(dismissed = true))
        // Permission alone does not mean the server has a working push registration.
        assertTrue(invitation(status = NotificationAuthorizationStatus.Authorized))
        assertTrue(invitation(status = NotificationAuthorizationStatus.Provisional))
    }

    @Test
    fun invitationDismissalPersistsPerAccount() {
        val defaults = MemoryDefaults()
        val user = UUID.randomUUID()
        assertFalse(NotificationInvitationPreference.isDismissed(user, defaults))
        NotificationInvitationPreference.dismiss(user, defaults)
        assertTrue(NotificationInvitationPreference.isDismissed(user, MemoryDefaults(defaults.values)))
        assertFalse(NotificationInvitationPreference.isDismissed(UUID.randomUUID(), defaults))
    }

    @Test
    fun invitationPreservesPreviousOnboardingChoice() {
        val defaults = MemoryDefaults()
        defaults.set(true, forKey = "sdt.notificationOnboardingCompleted.v1")
        assertTrue(NotificationInvitationPreference.isDismissed(UUID.randomUUID(), defaults))
    }

    @Test
    fun savingPresetKeepsTheEmailChoice() {
        for (choice in listOf(true, false, null)) {
            val saved = preferences(email = choice)
            var draft = NotificationPreferencesDraft(preferences = saved)
            assertEquals(NotificationPreset.Important, draft.preset)
            draft = draft.copy(preset = NotificationPreset.DeliveryDay)
            val next = draft.preferences(timezone = saved.timezone)
            assertEquals(NotificationPreset.DeliveryDay.stages, next.enabledStages)
            assertEquals(choice, next.emailOnDelivery)
            // Whether the server can email the account is not the app's to send.
            assertNull(next.emailAvailable)
        }
        assertNull(NotificationPreferencesDraft().preferences(timezone = "Europe/Zurich").emailOnDelivery)
    }

    @Test
    fun requestsLeaveOutWhatTheyDoNotChange() {
        val notifications = ApiJson.encodeToJsonElement(PackageNotificationRequest.serializer(), PackageNotificationRequest(muted = true)).jsonObject
        assertEquals(setOf("muted"), notifications.keys)
        assertEquals(true, notifications.boolean("muted"))
        val email = ApiJson.encodeToJsonElement(PackageNotificationRequest.serializer(), PackageNotificationRequest(emailMuted = false)).jsonObject
        assertEquals(setOf("emailMuted"), email.keys)
        assertEquals(false, email.boolean("emailMuted"))

        // An account that never chose sends no choice, as a released app does.
        val unchosen = preferencesObject(NotificationPreferencesDraft(preferences = preferences(email = null)).preferences(timezone = "Europe/Zurich"))
        assertEquals(setOf("enabledStages", "timezone"), unchosen.keys)
        val declined = preferencesObject(NotificationPreferencesDraft(preferences = preferences(email = false)).preferences(timezone = "Europe/Zurich"))
        assertEquals(setOf("enabledStages", "timezone", "emailOnDelivery"), declined.keys)
        assertEquals(false, declined.boolean("emailOnDelivery"))
    }

    private fun preferencesObject(value: NotificationPreferences): JsonObject =
        ApiJson.encodeToJsonElement(NotificationPreferences.serializer(), value).jsonObject

    private fun JsonObject.boolean(key: String): Boolean? = (this[key] as? JsonPrimitive)?.boolean

    @Test
    fun nothingAboutEmailShowsInTheDemoOrUntilTheServerCanWriteToTheAccount() {
        val address = "alex@example.com"
        val email = DeliveryEmail(isDemo = false, preferences = preferences(email = true), address = " $address\n")
        assertEquals(address, email?.address)
        assertEquals(true, email?.isOn)
        assertNull(DeliveryEmail(isDemo = true, preferences = preferences(email = true), address = address))
        assertNull(DeliveryEmail(isDemo = false, preferences = null, address = address))
        assertNull(DeliveryEmail(isDemo = false, preferences = preferences(email = true, available = false), address = address))
        // A server from before the email says nothing about it.
        assertNull(DeliveryEmail(isDemo = false, preferences = preferences(email = null, available = null), address = address))
        assertNull(DeliveryEmail(isDemo = false, preferences = preferences(email = true), address = null))
        assertNull(DeliveryEmail(isDemo = false, preferences = preferences(email = true), address = "  "))
    }

    @Test
    fun bellOpensAlertsOnlyWhileTheEmailIsOnAndTheParcelIsOnItsWay() {
        val on = email(true)
        val off = email(false)
        val unchosen = email(null)
        for (stage in TrackingStage.entries) {
            val parcel = parcel(stage)
            assertEquals(stage.rawValue, stage != TrackingStage.Delivered && stage != TrackingStage.Returned, on.bellOpensAlerts(parcel))
            assertFalse(stage.rawValue, off.bellOpensAlerts(parcel))
            assertFalse(stage.rawValue, unchosen.bellOpensAlerts(parcel))
        }
        // A parcel no carrier has announced yet is still on its way.
        assertTrue(on.bellOpensAlerts(parcel(null)))
    }

    @Test
    fun emailIsOfferedOnlyOnADeliveredParcelWhileTheAccountHasNeverChosen() {
        val on = email(true)
        val off = email(false)
        val unchosen = email(null)
        for (stage in TrackingStage.entries) {
            val parcel = parcel(stage)
            assertEquals(stage.rawValue, stage == TrackingStage.Delivered, unchosen.isOffered(parcel))
            assertFalse(stage.rawValue, on.isOffered(parcel))
            assertFalse(stage.rawValue, off.isOffered(parcel))
        }
        assertFalse(unchosen.isOffered(parcel(null)))
    }

    @Test
    fun bellOfAParcelWithAlertsIsStruckThroughOnlyWhenBothAreOff() {
        var parcel = parcel(TrackingStage.InTransit)
        assertFalse(parcel.allAlertsMuted)
        parcel = parcel.copy(notificationsMuted = true)
        // No email mute from the server or a saved list counts as not muted.
        assertNull(parcel.emailMuted)
        assertFalse(parcel.allAlertsMuted)
        parcel = parcel.copy(emailMuted = false)
        assertFalse(parcel.allAlertsMuted)
        parcel = parcel.copy(emailMuted = true)
        assertTrue(parcel.allAlertsMuted)
        parcel = parcel.copy(notificationsMuted = false)
        assertFalse(parcel.allAlertsMuted)
    }

    @Test
    fun exampleEmailOpensOnTheSiteInTheAppLanguage() {
        val site = "https://peek.example"
        assertEquals("https://peek.example/email/example?lang=de", DeliveryEmail.exampleUrl(site = site, language = AppLanguage.De))
        assertEquals(
            "http://localhost:3000/email/example?lang=en",
            DeliveryEmail.exampleUrl(site = "http://localhost:3000/", language = AppLanguage.En),
        )
        // The in-app browser opens web addresses only.
        for (other in listOf("peek.example", "file:///private/example", "peekdeliverytracker://p/example")) {
            assertNotNull(other, UrlComponents.parse(other)?.url)
            assertNull(other, DeliveryEmail.exampleUrl(site = other, language = AppLanguage.En))
        }
    }

    /**
     * The JSON is ApiJson's, which writes the API's own keys (`email_muted`) where `JSONEncoder.deliveryTracker`
     * writes the property's name (`emailMuted`): the saved list leaves out both.
     */
    @Test
    fun parcelsSavedBeforeTheEmailKeepDecoding() {
        val list = ListSerializer(Parcel.serializer())
        var parcel = parcel(TrackingStage.InTransit)
        val saved = ApiJson.encodeToString(list, listOf(parcel))
        assertFalse(saved.contains("emailMuted"))
        assertFalse(saved.contains("email_muted"))
        val restored = ApiJson.decodeFromString(list, saved).first()
        assertNull(restored.emailMuted)
        assertFalse(restored.isEmailMuted)
        assertEquals(parcel, restored)

        parcel = parcel.copy(emailMuted = true)
        val muted = ApiJson.decodeFromString(list, ApiJson.encodeToString(list, listOf(parcel)))
        assertEquals(true, muted.firstOrNull()?.emailMuted)

        val row = """
        {"id":"${parcel.id.uuidString}","tracking_number":"1Z999AA10123456784","label":"Test parcel","carrier":"ups",
         "created_at":"2026-08-01T10:00:00Z","sync_status":"ok","notifications_muted":false,"email_muted":true}
        """
        assertTrue(ApiJson.decodeFromString(Parcel.serializer(), row).isEmailMuted)
    }

    private fun preferences(email: Boolean?, available: Boolean? = true): NotificationPreferences = NotificationPreferences(
        enabledStages = NotificationPreset.Important.stages, timezone = "Europe/Zurich",
        emailOnDelivery = email, emailAvailable = available,
    )

    private fun email(choice: Boolean?): DeliveryEmail {
        val email = DeliveryEmail(isDemo = false, preferences = preferences(email = choice), address = "alex@example.com")
        assertNotNull(email)
        return email!!
    }

    /** A made-up parcel whose latest scan is at the given stage, or with no scan at all. */
    private fun parcel(stage: TrackingStage?, label: String = "Test parcel"): Parcel {
        val id = UUID.randomUUID()
        val events = stage?.let {
            listOf(
                TrackingEvent(
                    id = UUID.randomUUID(), packageId = id, stage = it, description = "Update", occurredAt = "2026-09-06T10:00:00Z",
                ),
            )
        } ?: emptyList()
        return Parcel(
            id = id, trackingNumber = "1Z999AA10123456784", label = label, carrier = CarrierId.Ups,
            createdAt = "2026-08-01T10:00:00Z", syncStatus = SyncStatus.Ok, notificationsMuted = false, trackingEvents = events,
        )
    }

    private fun invitation(
        isAuthenticated: Boolean = true,
        isDemo: Boolean = false,
        hasParcels: Boolean = true,
        status: NotificationAuthorizationStatus? = NotificationAuthorizationStatus.NotDetermined,
        enabled: Boolean = false,
        optedOut: Boolean = false,
        dismissed: Boolean = false,
    ): Boolean = NotificationInvitationPolicy.shouldPresent(
        isAuthenticated = isAuthenticated, isDemo = isDemo, hasParcels = hasParcels,
        status = status, enabled = enabled, optedOut = optedOut, dismissed = dismissed,
    )
}
