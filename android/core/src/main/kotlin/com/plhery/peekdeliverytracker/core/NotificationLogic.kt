package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.NotificationStage
import com.plhery.peekdeliverytracker.core.api.Parcel
import java.util.UUID

// NotificationLogic.swift. What it asks UserNotifications and UserDefaults comes in as plain values: the permission
// as a NotificationAuthorizationStatus and the saved choices through UserDefaults, both of which the app supplies.

enum class NotificationPreset(val rawValue: String) {
    All("all"), Important("important"), DeliveryDay("deliveryDay");

    val id: String get() = rawValue

    val stages: List<NotificationStage>
        get() = when (this) {
            All -> listOf(
                NotificationStage.Registered, NotificationStage.Accepted, NotificationStage.InTransit,
                NotificationStage.Customs, NotificationStage.Exception, NotificationStage.OutForDelivery,
                NotificationStage.FailedAttempt, NotificationStage.ReadyForPickup, NotificationStage.Delivered,
                NotificationStage.Returned,
            )
            Important -> listOf(
                NotificationStage.Customs, NotificationStage.Exception, NotificationStage.OutForDelivery,
                NotificationStage.FailedAttempt, NotificationStage.ReadyForPickup, NotificationStage.Delivered,
                NotificationStage.Returned,
            )
            DeliveryDay -> listOf(NotificationStage.OutForDelivery, NotificationStage.Delivered)
        }

    val titleKey: String
        get() = when (this) {
            All -> "notifications.preset.all"
            Important -> "notifications.preset.important"
            DeliveryDay -> "notifications.preset.deliveryDay"
        }

    val descriptionKey: String get() = "${titleKey}Description"

    companion object {
        fun matching(stages: List<NotificationStage>): NotificationPreset {
            val enabled = stages.toSet()
            return entries.firstOrNull { it.stages.toSet() == enabled } ?: All
        }
    }
}

data class NotificationPreferencesDraft(
    val preset: NotificationPreset,
    /**
     * The account's email choice rides along as it was saved: the email switch saves on its own, so saving a preset
     * never changes it.
     */
    val emailOnDelivery: Boolean?,
) {
    fun preferences(timezone: String): NotificationPreferences = NotificationPreferences(
        enabledStages = preset.stages,
        quietHoursStart = null,
        quietHoursEnd = null,
        timezone = timezone,
        emailOnDelivery = emailOnDelivery,
    )

    companion object {
        /** Swift's `init(preferences:)`. */
        operator fun invoke(preferences: NotificationPreferences? = null): NotificationPreferencesDraft =
            NotificationPreferencesDraft(
                preset = preferences?.let { NotificationPreset.matching(it.enabledStages) } ?: NotificationPreset.All,
                emailOnDelivery = preferences?.emailOnDelivery,
            )
    }
}

/**
 * The account's delivery email: a short email when a parcel is ready to collect and when it is delivered, to the
 * address it signs in with. There is none in the demo, or unless the server says it can write to the account, and
 * nothing about email shows then.
 */
@ConsistentCopyVisibility
data class DeliveryEmail private constructor(
    val address: String,
    /** True when on, false when off or declined, nil while the account has never chosen. */
    val choice: Boolean?,
) {
    val isOn: Boolean get() = choice == true

    /**
     * While the email is on and the parcel is still on its way, the parcel's bell opens its alerts. Otherwise one
     * tap mutes or unmutes its notifications.
     */
    fun bellOpensAlerts(parcel: Parcel): Boolean = isOn && parcel.currentStage?.isFinal != true

    /** Offered once, on a delivered parcel, while the account has never chosen. */
    fun isOffered(parcel: Parcel): Boolean = choice == null && parcel.isDelivered

    companion object {
        /** Swift's failable `init?(isDemo:preferences:address:)`. */
        operator fun invoke(isDemo: Boolean, preferences: NotificationPreferences?, address: String?): DeliveryEmail? {
            if (isDemo || preferences == null || preferences.emailAvailable != true) return null
            val trimmed = address?.trimmingWhitespacesAndNewlines()?.nonEmpty ?: return null
            return DeliveryEmail(trimmed, preferences.emailOnDelivery)
        }

        /**
         * What "See an example" opens: the example email in the app's language. Nil when the site is not a web
         * address, which the in-app browser cannot open.
         */
        fun exampleUrl(site: String, language: AppLanguage): String? {
            val url = Url.parse(site) ?: return null
            val scheme = url.scheme?.lowercased()
            if ((scheme != "https" && scheme != "http") || url.host == null) return null
            return site.appendingPath("email/example").appendingQueryItems(listOf(UrlComponents.QueryItem("lang", language.rawValue)))
        }
    }
}

/** Parcels saved or served before the email existed carry no mute for it. */
val Parcel.isEmailMuted: Boolean get() = emailMuted == true

/** The bell of a parcel whose alerts open in a sheet is struck through only when both its notifications and its email are off. */
val Parcel.allAlertsMuted: Boolean get() = notificationsMuted && isEmailMuted

/** Where the account's defaults are, as the alerts sheet and the offer name it: "Settings › Notifications". */
val Localizer.deliveryUpdatesPlace: String
    get() = "${text("settings.title")} › ${text("settings.deliveryUpdates")}"

/**
 * UNAuthorizationStatus. On Android the app reads it from the notification permission with [fromAndroid].
 * `Provisional` (quiet notifications iOS delivers before the user has been asked) and `Ephemeral` (an App Clip's, for
 * a while) are iOS's own, and Android never has them; the policies below count `Provisional` as authorized and
 * `Ephemeral` as not, as the iPhone app does.
 */
enum class NotificationAuthorizationStatus {
    NotDetermined, Denied, Authorized, Provisional, Ephemeral;

    companion object {
        /**
         * What iOS would report, from what Android tells the app about its notifications.
         *
         * From Android 13 (API 33), posting notifications is a permission the app asks for (POST_NOTIFICATIONS):
         * - granted, with the app's notifications on, is `Authorized`;
         * - never asked is `NotDetermined`, as on iOS before the first request;
         * - refused while the system would still ask, which it shows by asking for a rationale after a first
         *   refusal, is `NotDetermined` too, so the app asks again rather than sending the user to Settings;
         * - refused for good, asked before and no rationale (the system stops asking after a second refusal), is
         *   `Denied`, as is granted with the app's notifications turned off: only Settings can turn them on, as on iOS.
         *
         * Android 12 (API 31 and 32) has no such permission: an app's notifications are on until the user turns them
         * off, so on is `Authorized` and off is `Denied`, and the permission's facts are not read.
         *
         * @param sdkInt `Build.VERSION.SDK_INT`.
         * @param granted Whether POST_NOTIFICATIONS is granted.
         * @param askedBefore Whether the app has asked for POST_NOTIFICATIONS before, which Android does not keep.
         * @param shouldShowRationale `shouldShowRequestPermissionRationale(POST_NOTIFICATIONS)`.
         * @param areNotificationsEnabled `NotificationManagerCompat.areNotificationsEnabled()`.
         */
        fun fromAndroid(
            sdkInt: Int,
            granted: Boolean,
            askedBefore: Boolean,
            shouldShowRationale: Boolean,
            areNotificationsEnabled: Boolean,
        ): NotificationAuthorizationStatus = when {
            sdkInt < 33 -> if (areNotificationsEnabled) Authorized else Denied
            granted -> if (areNotificationsEnabled) Authorized else Denied
            !askedBefore || shouldShowRationale -> NotDetermined
            else -> Denied
        }
    }
}

object NotificationDevicePolicy {
    fun isAuthorized(status: NotificationAuthorizationStatus): Boolean =
        status == NotificationAuthorizationStatus.Authorized || status == NotificationAuthorizationStatus.Provisional

    fun isEnabled(
        isDemo: Boolean,
        status: NotificationAuthorizationStatus,
        optedOut: Boolean,
        nativePushRegistered: Boolean,
        demoNotificationsEnabled: Boolean,
    ): Boolean {
        if (isDemo) return demoNotificationsEnabled
        return isAuthorized(status) && !optedOut && nativePushRegistered
    }

    fun shouldRegisterForRemoteNotifications(status: NotificationAuthorizationStatus, optedOut: Boolean): Boolean =
        isAuthorized(status) && !optedOut
}

object NotificationInvitationPolicy {
    fun shouldPresent(
        isAuthenticated: Boolean,
        isDemo: Boolean,
        hasParcels: Boolean,
        status: NotificationAuthorizationStatus?,
        enabled: Boolean,
        optedOut: Boolean,
        dismissed: Boolean,
    ): Boolean {
        if (!isAuthenticated || isDemo || !hasParcels || enabled || optedOut || dismissed || status == null) return false
        return status == NotificationAuthorizationStatus.NotDetermined || status == NotificationAuthorizationStatus.Authorized ||
            status == NotificationAuthorizationStatus.Provisional
    }
}

/** The UserDefaults calls the logic makes, under the iPhone app's keys. The app keeps them in its own preferences. */
interface UserDefaults {
    /** False when nothing is saved under the key. */
    fun bool(forKey: String): Boolean

    fun set(value: Boolean, forKey: String)

    /** The bytes saved under the key; nil when nothing, or something else, is. */
    fun data(forKey: String): ByteArray?

    fun set(value: ByteArray, forKey: String)

    /** The text saved under the key; nil when nothing, or something else, is. */
    fun string(forKey: String): String?

    fun set(value: String, forKey: String)

    fun removeObject(forKey: String)
}

object NotificationInvitationPreference {
    private fun key(userId: UUID): String = "sdt.notificationInvitationDismissed.v1.${userId.uuidString}"

    fun isDismissed(userId: UUID, defaults: UserDefaults): Boolean =
        // Preserve choices made in the previous full-screen onboarding.
        defaults.bool(forKey = "sdt.notificationOnboardingCompleted.v1") || defaults.bool(forKey = key(userId))

    fun dismiss(userId: UUID, defaults: UserDefaults) {
        defaults.set(true, forKey = key(userId))
    }
}
