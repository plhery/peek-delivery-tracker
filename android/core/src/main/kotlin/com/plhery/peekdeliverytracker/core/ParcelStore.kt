package com.plhery.peekdeliverytracker.core

import com.plhery.peekdeliverytracker.core.api.CarrierDetectionResponse
import com.plhery.peekdeliverytracker.core.api.CarrierId
import com.plhery.peekdeliverytracker.core.api.ClaimParcelResultOutcome
import com.plhery.peekdeliverytracker.core.api.ClaimParcelLink
import com.plhery.peekdeliverytracker.core.api.ClaimParcelsRequest
import com.plhery.peekdeliverytracker.core.api.CreatePackageRequest
import com.plhery.peekdeliverytracker.core.api.NotificationPreferences
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.core.api.PublicParcelResponse
import com.plhery.peekdeliverytracker.core.api.ShareParcelRequest
import com.plhery.peekdeliverytracker.core.api.SyncStatus
import com.plhery.peekdeliverytracker.core.api.TrackingStage
import java.io.File
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.pow
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

// From ParcelStore.swift: the parcels on screen, and everything done to them, for an account, the demo or this phone
// alone. The offline cache is in ParcelCache.kt and the demo's parcels in DemoRepository.kt.

/**
 * The phone's notifications as the store reaches them: UNUserNotificationCenter, UIApplication's registration and
 * AppDelegate's token on the iPhone. Called on the main thread.
 *
 * On Android, the permission is POST_NOTIFICATIONS with NotificationManagerCompat, and the token Firebase Cloud
 * Messaging's. The service takes APNs tokens alone for now (`/api/push/devices` and the parcels' app alerts): FCM
 * tokens need it to send through FCM first.
 */
interface PushNotifications {
    /** `notificationSettings().authorizationStatus`: `NotificationAuthorizationStatus.fromAndroid` reads it. */
    suspend fun authorizationStatus(): NotificationAuthorizationStatus

    /**
     * `requestAuthorization(options: [.alert, .badge, .sound])`: asks once, and says whether notifications may be
     * shown. From Android 13 the POST_NOTIFICATIONS request, from the activity on screen; on Android 12, which asks
     * nothing, whether the app's notifications are on. Throws when it cannot ask, as when no activity is on screen.
     */
    suspend fun requestAuthorization(): Boolean

    /**
     * `registerForRemoteNotifications()`: asks for this launch's token (`FirebaseMessaging.getInstance().token`) and
     * returns at once. When the token arrives, and on each `FirebaseMessagingService.onNewToken`, the app keeps it as
     * `currentDeviceToken` and then, on the main thread, calls `forwardNativePushToken` and `registerDeviceAlerts`
     * with its language, in that order, as the iPhone's app does with each APNs token. It calls both again when the
     * app's language changes.
     */
    fun registerForRemoteNotifications()

    /** `unregisterForRemoteNotifications()`: this phone hears no more pushes (`FirebaseMessaging.deleteToken()`). */
    fun unregisterForRemoteNotifications()

    /** AppDelegate's `currentDeviceToken`: the token this launch was given, kept in memory alone; nil until then. */
    val currentDeviceToken: String?

    /** AppDelegate's `clearDeviceToken()`: forgets `currentDeviceToken`. */
    fun clearDeviceToken()
}

/**
 * WidgetKit's `WidgetCenter`: told that what the widget shows changed (DeliveryWidgetSharedStore holds it), so it
 * draws again. On Android an update of the widget's AppWidgetProvider, or nothing while the app has no widget.
 */
fun interface WidgetCenter {
    fun reloadTimelines(ofKind: String)
}

/**
 * Where an account's export is written for the share sheet: the iPhone's temporary folder. On Android a file in the
 * app's cache (`cacheDir/exports/<name>`, replacing one of the same name) that a FileProvider hands to the share
 * intent. Throws when it cannot be written.
 */
fun interface ExportFiles {
    suspend fun write(name: String, data: ByteArray): File
}

/**
 * The Live Activities of the parcels out for delivery, which the iPhone keeps on its Lock Screen. Android has none
 * (`None`): the store keeps their setting and withdraws this phone's registration for them, so the service starts
 * none, as the iPhone does while they are switched off.
 */
interface DeliveryLiveActivities {
    /** `ActivityAuthorizationInfo().areActivitiesEnabled`. */
    val areActivitiesEnabled: Boolean

    /** Ends at once every one still showing. */
    suspend fun endAll()

    object None : DeliveryLiveActivities {
        override val areActivitiesEnabled: Boolean = false

        override suspend fun endAll() {}
    }
}

/**
 * How the store reaches the phone for the notifications of the parcels it follows itself: the permission, the
 * question for it, the registration and the token. Tests answer for the phone.
 */
class DeviceNotificationAccess(
    val status: suspend () -> NotificationAuthorizationStatus,
    val request: suspend () -> Unit,
    val register: () -> Unit,
    val token: () -> String?,
) {
    companion object {
        /** `DeviceNotificationAccess.system`: the phone's own, whose question never fails the caller. */
        fun system(notifications: PushNotifications): DeviceNotificationAccess = DeviceNotificationAccess(
            status = { notifications.authorizationStatus() },
            request = { tryQuietly { notifications.requestAuthorization() } },
            register = { notifications.registerForRemoteNotifications() },
            token = { notifications.currentDeviceToken },
        )
    }
}

/**
 * The parcels on screen: an account's, the demo's, or those this phone follows without an account. Each change is
 * shown at once and kept for an offline launch; work begun for one account never lands in the next one's.
 *
 * Used from the main thread, as the iPhone's store is `@MainActor`; each `…Flow` publishes its property as it
 * changes. What the iPhone leaves to a `Task` runs in `scope`, the app's own on the main thread, which nothing cancels
 * while the app runs (a `MainScope()` the application keeps). Its dispatcher must not be `Main.immediate`: a Swift
 * `Task` starts once the code that made it has returned, never inside it.
 *
 * The app makes one, as the iPhone's app makes its ParcelStore, from the session, the account's client, the device's
 * parcels and the Live Activity revocations it gives everything else. `defaults` is the standard UserDefaults
 * stand-in, which also keeps the demo's parcels; `deliveryWidgetStore` the preferences the widget reads;
 * `cacheFiles` the offline lists. `region` is the device's region, whose country an added parcel's lookup is hinted
 * with (`Resources.getSystem().configuration.locales[0]`, never `Locale.getDefault()`, which follows the app's
 * language); `analytics` counts the catalog's actions (`DeliveryAnalytics.shared.action`); `importCharges` is the
 * debug build's import charges setting for the demo (`ImportChargesPreview`), nil in a release build.
 */
class ParcelStore(
    val configuration: AppConfiguration,
    // Background delivery cleanup can outlive the screen that supplied the session.
    private val session: SessionStore,
    private val localizer: Localizer,
    private val api: DeliveryApiClient,
    private val device: DeviceParcels,
    private val liveActivityRevocations: LiveActivityRevocations,
    private val defaults: UserDefaults,
    private val deliveryWidgetStore: DeliveryWidgetSharedStore,
    cacheFiles: ParcelCacheFiles,
    private val notifications: PushNotifications,
    private val widgetCenter: WidgetCenter,
    private val exportFiles: ExportFiles,
    private val region: () -> Locale,
    private val scope: CoroutineScope,
    private val deviceNotifications: DeviceNotificationAccess = DeviceNotificationAccess.system(notifications),
    private val liveActivities: DeliveryLiveActivities = DeliveryLiveActivities.None,
    private val analytics: (event: String, outcome: DeliveryAnalytics.Outcome?) -> Unit = { _, _ -> },
    private val now: () -> Instant = Instant::now,
    importCharges: () -> String? = { null },
) {
    sealed class RefreshStart {
        data object Queued : RefreshStart()

        data class Completed(val result: RefreshResult) : RefreshStart()

        data object AlreadyRunning : RefreshStart()
    }

    /** How a check ended; a failure's message is shown as is. */
    sealed class RefreshResult {
        data object Updated : RefreshResult()

        data object Unchanged : RefreshResult()

        data object StillChecking : RefreshResult()

        data class Failed(val message: String) : RefreshResult()

        /** Shared with the web app's pull, refresh button and parcel check. */
        val messageKey: String
            get() = when (this) {
                Updated -> "app.refreshComplete"
                Unchanged -> "app.refreshUnchanged"
                StillChecking -> "app.refreshTimeout"
                is Failed -> "detail.checkFailed"
            }
    }

    /** How a background check of every parcel ended. */
    data class RefreshOutcome(val result: RefreshResult, val id: UUID = UUID.randomUUID())

    private val parcelsState = MutableStateFlow<List<Parcel>>(emptyList())
    private val loadingState = MutableStateFlow(false)
    private val refreshingState = MutableStateFlow(false)
    private val refreshOutcomeState = MutableStateFlow<RefreshOutcome?>(null)
    private val errorMessageState = MutableStateFlow<String?>(null)
    private val authenticationRequiredState = MutableStateFlow(false)
    private val usingCachedDataState = MutableStateFlow(false)
    private val notificationEnableInProgressState = MutableStateFlow(false)
    private val notificationStateLoadedState = MutableStateFlow(false)
    private val notificationInvitationDismissedState = MutableStateFlow(false)
    private val notificationStatusState = MutableStateFlow(NotificationAuthorizationStatus.NotDetermined)
    private val notificationsEnabledOnDeviceState = MutableStateFlow(false)
    private val notificationPreferencesState = MutableStateFlow<NotificationPreferences?>(null)
    private val notificationErrorState = MutableStateFlow<String?>(null)
    private val deliveryWidgetEnabledState = MutableStateFlow(deliveryWidgetStore.isEnabled)
    private val deliveryLiveActivitiesEnabledState = MutableStateFlow(offersLiveActivities && deliveryWidgetStore.liveActivitiesEnabled)
    private val deliveryLiveActivityErrorState = MutableStateFlow<String?>(null)
    private val undoParcelState = MutableStateFlow<Parcel?>(null)

    val parcelsFlow: StateFlow<List<Parcel>> = parcelsState.asStateFlow()
    val loadingFlow: StateFlow<Boolean> = loadingState.asStateFlow()
    val refreshingFlow: StateFlow<Boolean> = refreshingState.asStateFlow()
    val refreshOutcomeFlow: StateFlow<RefreshOutcome?> = refreshOutcomeState.asStateFlow()
    val errorMessageFlow: StateFlow<String?> = errorMessageState.asStateFlow()
    val authenticationRequiredFlow: StateFlow<Boolean> = authenticationRequiredState.asStateFlow()
    val usingCachedDataFlow: StateFlow<Boolean> = usingCachedDataState.asStateFlow()
    val notificationEnableInProgressFlow: StateFlow<Boolean> = notificationEnableInProgressState.asStateFlow()
    val notificationStateLoadedFlow: StateFlow<Boolean> = notificationStateLoadedState.asStateFlow()
    val notificationInvitationDismissedFlow: StateFlow<Boolean> = notificationInvitationDismissedState.asStateFlow()
    val notificationStatusFlow: StateFlow<NotificationAuthorizationStatus> = notificationStatusState.asStateFlow()
    val notificationsEnabledOnDeviceFlow: StateFlow<Boolean> = notificationsEnabledOnDeviceState.asStateFlow()
    val notificationPreferencesFlow: StateFlow<NotificationPreferences?> = notificationPreferencesState.asStateFlow()
    val notificationErrorFlow: StateFlow<String?> = notificationErrorState.asStateFlow()
    val deliveryWidgetEnabledFlow: StateFlow<Boolean> = deliveryWidgetEnabledState.asStateFlow()
    val deliveryLiveActivitiesEnabledFlow: StateFlow<Boolean> = deliveryLiveActivitiesEnabledState.asStateFlow()
    val deliveryLiveActivityErrorFlow: StateFlow<String?> = deliveryLiveActivityErrorState.asStateFlow()
    val undoParcelFlow: StateFlow<Parcel?> = undoParcelState.asStateFlow()

    /** `objectWillChange`, for what no flow publishes: the postcodes this phone suggests. */
    val objectWillChange = PassthroughSignal()

    var parcels: List<Parcel>
        get() = parcelsState.value
        private set(value) {
            parcelsState.value = value
            if (!holdingDeliverySurfaces) publishDeliverySurfaces()
        }

    var loading: Boolean
        get() = loadingState.value
        private set(value) {
            loadingState.value = value
        }

    var refreshing: Boolean
        get() = refreshingState.value
        private set(value) {
            refreshingState.value = value
        }

    var refreshOutcome: RefreshOutcome?
        get() = refreshOutcomeState.value
        private set(value) {
            refreshOutcomeState.value = value
        }

    var errorMessage: String?
        get() = errorMessageState.value
        private set(value) {
            errorMessageState.value = value
        }

    var authenticationRequired: Boolean
        get() = authenticationRequiredState.value
        private set(value) {
            authenticationRequiredState.value = value
        }

    var usingCachedData: Boolean
        get() = usingCachedDataState.value
        private set(value) {
            usingCachedDataState.value = value
        }

    var notificationEnableInProgress: Boolean
        get() = notificationEnableInProgressState.value
        private set(value) {
            notificationEnableInProgressState.value = value
        }

    var notificationStateLoaded: Boolean
        get() = notificationStateLoadedState.value
        private set(value) {
            notificationStateLoadedState.value = value
        }

    var notificationInvitationDismissed: Boolean
        get() = notificationInvitationDismissedState.value
        private set(value) {
            notificationInvitationDismissedState.value = value
        }

    var notificationStatus: NotificationAuthorizationStatus
        get() = notificationStatusState.value
        private set(value) {
            notificationStatusState.value = value
        }

    var notificationsEnabledOnDevice: Boolean
        get() = notificationsEnabledOnDeviceState.value
        private set(value) {
            notificationsEnabledOnDeviceState.value = value
        }

    var notificationPreferences: NotificationPreferences?
        get() = notificationPreferencesState.value
        private set(value) {
            notificationPreferencesState.value = value
        }

    var notificationError: String?
        get() = notificationErrorState.value
        private set(value) {
            notificationErrorState.value = value
        }

    // Set through its state alone: `setDeliveryWidgetEnabled` is the switch's own function.
    val deliveryWidgetEnabled: Boolean
        get() = deliveryWidgetEnabledState.value

    // Set through its state alone: `setDeliveryLiveActivitiesEnabled` is the switch's own function.
    val deliveryLiveActivitiesEnabled: Boolean
        get() = deliveryLiveActivitiesEnabledState.value

    var deliveryLiveActivityError: String?
        get() = deliveryLiveActivityErrorState.value
        private set(value) {
            deliveryLiveActivityErrorState.value = value
        }

    var undoParcel: Parcel?
        get() = undoParcelState.value
        set(value) {
            undoParcelState.value = value
        }

    private val demo = DemoRepository(defaults, { localizer.language }, now, importCharges)
    private val demoShares = DemoParcelShares(defaults)
    private val shareNotes = ParcelShareNotes(defaults)
    private val cache = ParcelCache(cacheFiles)
    private var pollingTask: Job? = null
    private var jobMonitoringTask: Job? = null
    private var refreshTask: Job? = null

    /** Optimistic changes in flight; a list loaded meanwhile could undo them on screen. */
    private val pendingMutations = mutableSetOf<UUID>()

    /**
     * A reopened account shows its saved list at once. Until the service confirms it, the widget and Live Activities
     * keep what they last showed.
     */
    private var holdingDeliverySurfaces = false
    private var showingSavedParcels = false
    private var cacheCurrent = false
    private var publishedWidgetState: DeliveryWidgetState? = null
    private var deliveryActivityTask: Job? = null

    // A Set in Swift, whose order is its own each launch; here the order the jobs were queued in.
    private val pendingJobIds = LinkedHashSet<UUID>()

    /** Swift's `isActive`: the app is in front. */
    private var appActive = true
    private val identityObservation: AutoCloseable
    private var cacheOwnerId: UUID? = null
    private var mutationRevision = 0
    private var loadSequence = 0
    private var nativePushGeneration = 0

    /** Counts saved preferences: the answer to a read that began before a save is older than it. */
    private var notificationPreferenceSaves = 0

    // The iPhone's Live Activity generation and pending removal guard the registration of their tokens, which Android,
    // without Live Activities, never makes.
    private var deliveryLiveActivitySystemDisabled = false
    private val installationId: UUID = installationIdentifier(defaults)
    private val notificationOptOutKey = "sdt.notificationsDeviceOptOut"
    private val nativePushRegisteredKey = "sdt.notificationsNativePushRegistered.v1"
    private val demoNotificationsKey = "sdt.demoNotificationsEnabled"

    init {
        deliveryWidgetStore.setLiveActivitiesEnabled(deliveryLiveActivitiesEnabled)
        cacheOwnerId = session.user?.id
        notificationInvitationDismissed = session.user?.let { NotificationInvitationPreference.isDismissed(it.id, defaults) } ?: false
        identityObservation = session.identityChanges.sink { resetForSessionChange() }
    }

    private fun resetForSessionChange() {
        val reopening = session.reopeningSavedAccount
        pollingTask?.cancel()
        pollingTask = null
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        refreshTask?.cancel()
        refreshTask = null
        pendingMutations.clear()
        pendingJobIds.clear()
        nativePushGeneration += 1
        stopDeliveryLiveActivityObservers()
        cacheOwnerId?.let { owner ->
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
            cache.delete(owner)
            // The words its links carried leave the device with the account.
            shareNotes.forgetAll()
            // So does what it answered about its parcels.
            ParcelFeedbackMemory(defaults, now).forgetAll()
            notifications.unregisterForRemoteNotifications()
            notifications.clearDeviceToken()
            defaults.set(true, forKey = notificationOptOutKey)
            defaults.set(false, forKey = nativePushRegisteredKey)
        }
        cacheOwnerId = session.user?.id
        notificationInvitationDismissed = session.user?.let { NotificationInvitationPreference.isDismissed(it.id, defaults) } ?: false
        mutationRevision += 1
        loadSequence += 1
        holdingDeliverySurfaces = reopening
        cacheCurrent = false
        val saved = if (reopening) session.user?.let { cache.load(it.id) } else null
        showingSavedParcels = saved != null
        // Open on content: the saved list, or the demo box, instead of an empty frame.
        parcels = saved ?: if (session.isDemo) demo.list() else if (session.isGuest) device.list() else emptyList()
        undoParcel = null
        loading = session.isAuthenticated && parcels.isEmpty()
        refreshing = false
        errorMessage = null
        authenticationRequired = false
        usingCachedData = false
        notificationPreferences = null
        notificationError = null
        notificationEnableInProgress = false
        notificationsEnabledOnDevice = false
        notificationStateLoaded = false
        notificationStatus = NotificationAuthorizationStatus.NotDetermined
        deliveryLiveActivityError = null
        if (!reopening) clearDeliverySurfaces()
    }

    val isDemo: Boolean
        get() = session.isDemo

    /** Nobody is signed in: the parcels are the ones this phone follows itself. */
    val isGuest: Boolean
        get() = session.isGuest

    /** Whether tracking without an account works at all: a build without a server only has its demo. */
    val tracksWithoutAccount: Boolean
        get() = configuration.mode == AppConfiguration.Mode.Api

    /**
     * The postcodes given before, for the add sheet to suggest: the parcels' own, then the ones this phone remembers
     * from lookups without an account.
     */
    val givenPostcodes: List<GivenPostcode>
        get() = GivenPostcode.candidates(parcels, device.postcodes.entries)

    /** This phone stops suggesting the postcode, for every carrier. */
    fun forgetPostcode(postcode: String) {
        objectWillChange.send()
        device.postcodes.forget(postcode)
    }

    suspend fun detectCarrier(trackingNumber: String): CarrierDetectionResponse {
        if (isGuest) return device.detect(trackingNumber)
        return api.detectCarrier(trackingNumber)
    }

    val activeCount: Int
        get() = parcels.count { it.isActive }

    val isSynchronizing: Boolean
        get() = parcels.any { it.syncStatus == SyncStatus.Pending || it.syncStatus == SyncStatus.Syncing }

    fun setDeliveryWidgetEnabled(enabled: Boolean) {
        analytics("widget-change", null)
        if (deliveryWidgetEnabled == enabled) return
        deliveryWidgetEnabledState.value = enabled
        deliveryWidgetStore.setEnabled(enabled)
        publishDeliveryWidget()
    }

    fun setDeliveryLiveActivitiesEnabled(enabled: Boolean) {
        analytics("live-activities-change", null)
        if (deliveryLiveActivitiesEnabled == enabled) return
        deliveryLiveActivitiesEnabledState.value = enabled
        deliveryWidgetStore.setLiveActivitiesEnabled(enabled)
        deliveryLiveActivitySystemDisabled = false
        deliveryLiveActivityError = null
        if (enabled) {
            startDeliveryLiveActivityObservers()
        } else {
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
            stopDeliveryLiveActivityObservers()
        }
        scheduleDeliveryLiveActivities()
    }

    /** The widget and Live Activities again, as after a change of language. */
    fun refreshDeliverySurfaces() {
        publishDeliverySurfaces()
    }

    fun clearDeliverySurfaces() {
        if (!session.isAuthenticated) {
            quietly { liveActivityRevocations.queue() }
            liveActivityRevocations.retry(scope)
        }
        deliveryWidgetStore.setLanguageCode(localizer.language.rawValue)
        deliveryWidgetStore.clearSnapshot()
        publishedWidgetState = null
        widgetCenter.reloadTimelines(DeliveryWidgetSharedStore.kind)
        scheduleDeliveryLiveActivities(forceEnd = true)
    }

    suspend fun start() {
        quietly { liveActivityRevocations.queue(except = session.user?.id) }
        liveActivityRevocations.retry(scope)
        pollingTask?.cancel()
        jobMonitoringTask?.cancel()
        jobMonitoringTask = null
        pendingJobIds.clear()
        val generation = session.generation
        if (errorMessage != null) errorMessage = null
        if (authenticationRequired) authenticationRequired = false
        if (usingCachedData) usingCachedData = false
        if (!session.isAuthenticated) {
            if (loading) loading = false
            if (!isGuest) {
                if (parcels.isNotEmpty()) parcels = emptyList()
                return
            }
            load()
            if (!stillCurrent(generation)) return
            beginDevicePolling()
            resumeDeviceAlerts()
            return
        }
        if (!isDemo) claimDeviceParcels()
        if (!stillCurrent(generation)) return
        load(showSpinner = true)
        if (!stillCurrent(generation)) return
        refreshNotificationState()
        if (!stillCurrent(generation)) return
        startDeliveryLiveActivityObservers()
        if (!isDemo) {
            loadNotificationPreferences()
            if (!stillCurrent(generation)) return
            beginPolling()
        }
    }

    fun setActive(active: Boolean) {
        val returning = active && !appActive
        appActive = active
        if (active) liveActivityRevocations.retry(scope)
        if (active && session.isAuthenticated) {
            scope.launch {
                refreshNotificationState()
                load(showSpinner = false)
            }
            // The launch loads the preferences itself. A return reads them again: the email can be switched off
            // outside the app, from a link in an email.
            if (returning && !isDemo) scope.launch { refreshNotificationPreferences() }
        } else if (active && isGuest) {
            scope.launch { load() }
        }
    }

    suspend fun load(showSpinner: Boolean = false) {
        if (isGuest) {
            loadDeviceParcels()
            return
        }
        if (!session.isAuthenticated) return
        val generation = session.generation
        val revision = mutationRevision
        val ownerId = session.user?.id
        loadSequence += 1
        val sequence = loadSequence
        if (showSpinner && parcels.isEmpty() && !loading) loading = true
        try {
            val next = list()
            session.checkGeneration(generation)
            if (revision != mutationRevision || sequence != loadSequence || pendingMutations.isNotEmpty()) return
            holdingDeliverySurfaces = false
            showingSavedParcels = false
            val changed = next != parcels
            if (changed) {
                parcels = next
            } else {
                // An unchanged poll re-renders nothing; the widget and Live Activities still update if their wording
                // changed, such as "tomorrow" becoming "today".
                publishDeliverySurfaces()
            }
            if ((changed || !cacheCurrent) && ownerId != null) {
                cache.save(next, ownerId)
                cacheCurrent = true
            }
            if (errorMessage != null) errorMessage = null
            if (authenticationRequired) authenticationRequired = false
            if (usingCachedData) usingCachedData = false
        } catch (error: Exception) {
            if (!stillCurrent(generation) || revision != mutationRevision || sequence != loadSequence ||
                error is CancellationException
            ) {
                return
            }
            val message = localizer.errorMessage(error)
            if (errorMessage != message) errorMessage = message
            val expired = error is DeliveryApiError.AuthenticationExpired
            if (authenticationRequired != expired) authenticationRequired = expired
            val cached = if (parcels.isEmpty() && ownerId != null) cache.load(ownerId) else null
            if (cached != null) {
                parcels = cached
                usingCachedData = true
            } else if (showingSavedParcels && !usingCachedData) {
                usingCachedData = true
            }
        } finally {
            if (generation == session.generation && sequence == loadSequence && loading) loading = false
        }
    }

    suspend fun add(
        trackingNumber: String,
        label: String,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String? = null,
    ): Parcel {
        val generation = session.generation
        val request = CreatePackageRequest(
            trackingNumber = CarrierCatalog.normalize(trackingNumber),
            label = label.trimmingWhitespacesAndNewlines(),
            carrier = carrier,
            trackingUrl = trackingUrl?.trimmingWhitespacesAndNewlines()?.nonEmpty,
            dpdPostcode = dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty,
            dpdPostcodeCountry = dpdPostcodeCountry,
            lookupCountryHint = ParcelLookupCountry.hint(region()),
        )
        val parcel: Parcel
        if (isDemo) {
            parcel = demo.add(request)
        } else if (isGuest) {
            parcel = device.add(request)
            session.checkGeneration(generation)
            // Its carrier answers in a moment: ask again soon.
            beginDevicePolling()
            val language = localizer.language
            scope.launch { askForDeviceAlerts(language) }
        } else {
            val response = api.add(request)
            parcel = response.parcel
            session.checkGeneration(generation)
            monitorJobs(response.jobIds)
        }
        session.checkGeneration(generation)
        upsert(parcel)
        return parcel
    }

    /**
     * Keeps a parcel shared through a link in this account, under the name the link suggests. The list is then
     * reloaded, so the parcel is on it before the sheet closes. A link the site handed over with its owner key goes
     * with the key, as a lookup made on this phone would.
     */
    suspend fun keep(link: ParcelLinkRoute): ParcelLinkClaim {
        if (session.user == null) throw DeliveryApiError.AuthenticationExpired()
        val generation = session.generation
        val response = api.claimParcels(ClaimParcelsRequest(listOf(ClaimParcelLink(id = link.id, key = link.key, label = link.name))))
        session.checkGeneration(generation)
        val claim = ParcelLinkClaim(response, link.id) ?: throw DeliveryApiError.InvalidResponse()
        when (claim) {
            is ParcelLinkClaim.Added, is ParcelLinkClaim.Already -> load()
            ParcelLinkClaim.Full, ParcelLinkClaim.Unavailable -> {}
        }
        return claim
    }

    /**
     * Follows the parcel behind a link on this phone, without an account: as its own when the site handed its owner
     * key over, else as its reader. The demo or the sign-in screen gives way to the device's parcels, and
     * notifications are asked for. `Already` when it was here.
     */
    fun follow(link: ParcelLinkRoute, response: PublicParcelResponse): ParcelLinkClaim {
        if (!tracksWithoutAccount || session.user != null) throw DeliveryApiError.ServiceFailed(503)
        if (!isGuest) session.showWelcome()
        // A wrapped gift's name is what is inside.
        val followed = device.follow(response, key = link.key, label = if (response.gift == ParcelLinkGift.Wrapped) null else link.name)
        mutationRevision += 1
        parcels = device.list()
        beginDevicePolling()
        val language = localizer.language
        scope.launch { askForDeviceAlerts(language) }
        return if (followed.added) ParcelLinkClaim.Added(followed.id) else ParcelLinkClaim.Already(followed.id)
    }

    /** The parcel this phone follows itself behind a link, by the link or by the parcel. */
    fun followedHere(response: PublicParcelResponse): UUID? {
        if (!tracksWithoutAccount || session.user != null) return null
        return device.parcelId(response.link.id) ?: device.list().firstOrNull { it.id == response.parcel.id }?.id
    }

    /** The parcel this phone follows through a link, for a notification about it. */
    fun followedHere(linkId: String): UUID? = device.parcelId(linkId)

    /** Shows the parcels this phone follows itself, leaving the demo or the sign-in screen. */
    fun showDeviceParcels() {
        if (session.user != null || isGuest) return
        session.showWelcome()
    }

    /**
     * The link of a parcel followed here through someone else's link: all this phone may share of it, and the sign
     * that its carrier and details are not this phone's to change.
     */
    fun viewerLink(parcel: Parcel): String? = if (isGuest) device.viewerLink(parcel.id) else null

    // Notifications without an account

    /**
     * Asks for notifications once a parcel comes to this phone without an account: the phone asks for permission the
     * first time, and the device's parcels are signed up once the token is known.
     */
    suspend fun askForDeviceAlerts(language: AppLanguage) {
        if (!tracksWithoutAccount || isDemo || !device.hasParcelsOnTheirWay) return
        var status = deviceNotifications.status()
        if (status == NotificationAuthorizationStatus.NotDetermined) {
            deviceNotifications.request()
            status = deviceNotifications.status()
        }
        if (!NotificationDevicePolicy.isAuthorized(status)) return
        deviceNotifications.register()
        deviceNotifications.token()?.let { registerDeviceAlerts(it, language) }
    }

    /** At launch, with permission given already, the phone hands the token over again and the parcels are signed up on it. Nothing is asked. */
    private suspend fun resumeDeviceAlerts() {
        if (!tracksWithoutAccount || isDemo || !device.hasParcelsOnTheirWay ||
            !NotificationDevicePolicy.isAuthorized(deviceNotifications.status())
        ) {
            return
        }
        deviceNotifications.register()
    }

    /**
     * Signs the device's own parcels up for notifications on this token, in this language. Signed in, these are the
     * parcels the account could not take over; the account's own registration stays as it is, and so does its choice
     * to have no notifications on this phone.
     */
    suspend fun registerDeviceAlerts(token: String, language: AppLanguage) {
        if (!tracksWithoutAccount || isDemo) return
        if (session.user != null && defaults.bool(forKey = notificationOptOutKey)) return
        device.registerAlerts(token, NativePushLocale(language))
    }

    /** How the share sheet reaches this parcel's link: through the service, or the demo's own made-up links, which lead nowhere. */
    fun shareClient(parcel: Parcel): ParcelShareModel.Client {
        val id = parcel.id
        return ParcelShareModel.Client(
            current = {
                if (isDemo) {
                    demoShares.link(id)
                } else if (isGuest) {
                    device.share(id)
                } else {
                    val generation = session.generation
                    val link = api.parcelShare(id)
                    session.checkGeneration(generation)
                    link
                }
            },
            share = { settings ->
                if (isDemo) {
                    demoShares.share(id, showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords, now = now())
                } else if (isGuest) {
                    device.setShare(id, showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords)
                        ?: throw DeliveryApiError.InvalidResponse()
                } else {
                    val generation = session.generation
                    val link = api.shareParcel(id, ShareParcelRequest(showNumber = settings.showNumber, gift = settings.gift, giftWords = settings.giftWords))
                    session.checkGeneration(generation)
                    link
                }
            },
            stop = {
                if (isDemo) {
                    demoShares.stop(id)
                } else if (isGuest) {
                    device.setShare(id, showNumber = false, gift = false, shared = false)
                } else {
                    val generation = session.generation
                    api.stopSharingParcel(id)
                    session.checkGeneration(generation)
                }
            },
        )
    }

    /**
     * How a parcel's page sends what its reader says of it. The demo's pages ask nothing; an answer for one of its
     * parcels would go nowhere.
     */
    fun feedbackClient(parcel: Parcel): ParcelFeedbackClient {
        val id = parcel.id
        return ParcelFeedbackClient { feedback ->
            if (isDemo) return@ParcelFeedbackClient
            if (isGuest) {
                device.sendFeedback(feedback, id)
                return@ParcelFeedbackClient
            }
            val generation = session.generation
            api.sendFeedback(id, feedback)
            session.checkGeneration(generation)
        }
    }

    /** The same for a parcel opened through a link, which is read with or without an account. */
    fun feedbackClient(linkId: String): ParcelFeedbackClient = ParcelFeedbackClient { feedback ->
        // A build without a server reads no link: what it shows is made up.
        if (!tracksWithoutAccount) return@ParcelFeedbackClient
        device.sendFeedback(feedback, linkId)
    }

    suspend fun rename(parcel: Parcel, label: String) {
        val generation = session.generation
        val cleaned = label.trimmingWhitespacesAndNewlines()
        val updated = if (isDemo) {
            demo.rename(parcel.id, cleaned)
        } else if (isGuest) {
            device.rename(parcel.id, cleaned)
        } else {
            api.rename(parcel.id, cleaned)
        }
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun changeCarrier(
        parcel: Parcel,
        carrier: CarrierId,
        trackingUrl: String?,
        dpdPostcode: String?,
        dpdPostcodeCountry: String?,
        providerPostcode: String? = null,
    ) {
        val generation = session.generation
        val cleanedUrl = trackingUrl?.trimmingWhitespacesAndNewlines()?.nonEmpty
        val cleanedPostcode = dpdPostcode?.trimmingWhitespacesAndNewlines()?.nonEmpty
        val updated: Parcel
        if (isDemo) {
            updated = demo.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry)
        } else if (isGuest) {
            // A new lookup takes the old one's place, under a new id.
            device.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry)
            session.checkGeneration(generation)
            mutationRevision += 1
            parcels = device.list()
            beginDevicePolling()
            return
        } else {
            val response = api.changeCarrier(parcel.id, carrier, cleanedUrl, cleanedPostcode, dpdPostcodeCountry, providerPostcode)
            updated = response.parcel
            session.checkGeneration(generation)
            monitorJobs(response.jobIds)
        }
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun setMuted(parcel: Parcel, muted: Boolean) {
        val generation = session.generation
        // A parcel followed without an account has no alerts to mute.
        if (isGuest) return
        val updated = if (isDemo) demo.setMuted(parcel.id, muted) else api.setMuted(parcel.id, muted)
        session.checkGeneration(generation)
        upsert(updated)
    }

    /** Switches the delivery email off, or back on, for this parcel only. */
    suspend fun setEmailMuted(parcel: Parcel, muted: Boolean) {
        val generation = session.generation
        val updated = if (isDemo) demo.setEmailMuted(parcel.id, muted) else api.setEmailMuted(parcel.id, muted)
        session.checkGeneration(generation)
        upsert(updated)
    }

    suspend fun archive(parcel: Parcel) {
        val generation = session.generation
        val updated = parcel.copy(archivedAt = DateParser.isoString(now()))
        // A swiped card leaves at once; it returns only if the service refuses.
        val mutation = UUID.randomUUID()
        pendingMutations += mutation
        upsert(updated)
        try {
            if (isDemo) {
                demo.archive(parcel.id)
            } else if (isGuest) {
                device.setArchived(parcel.id, true)
            } else {
                api.archive(parcel.id)
            }
            session.checkGeneration(generation)
        } catch (error: Exception) {
            if (!pendingMutations.remove(mutation)) throw error
            if (parcels.firstOrNull { it.id == parcel.id } == updated) upsert(parcel)
            throw error
        }
        if (!pendingMutations.remove(mutation)) return
        // Lists requested while the change was in flight may predate it.
        mutationRevision += 1
        undoParcel = parcel
    }

    suspend fun restore(parcel: Parcel) {
        val generation = session.generation
        val restored = if (isDemo) {
            demo.restore(parcel.id)
        } else if (isGuest) {
            device.setArchived(parcel.id, false)
        } else {
            api.restore(parcel.id)
        }
        session.checkGeneration(generation)
        upsert(restored)
        if (undoParcel?.id == parcel.id) undoParcel = null
    }

    suspend fun permanentlyDelete(parcel: Parcel) {
        val generation = session.generation
        if (isDemo) {
            demo.permanentlyDelete(parcel.id)
            demoShares.stop(parcel.id)
        } else if (isGuest) {
            device.forget(parcel.id)
        } else {
            api.permanentlyDelete(parcel.id)
        }
        session.checkGeneration(generation)
        // What its link carried goes with the parcel.
        shareNotes.forget(parcel.id)
        mutationRevision += 1
        parcels = parcels.filter { it.id != parcel.id }
        persistCache()
        if (undoParcel?.id == parcel.id) undoParcel = null
    }

    /**
     * Asks the service to check every parcel and returns once the check is queued, so pull-to-refresh ends quickly.
     * `refreshing` stays on until the results are loaded, and `refreshOutcome` then reports how the check ended.
     */
    suspend fun refreshAll(): RefreshStart {
        val generation = session.generation
        if (refreshing) return RefreshStart.AlreadyRunning
        refreshing = true
        // Compared with the list as the check began: a push may reload it first.
        val before = parcels
        if (isDemo) {
            parcels = demo.refreshAll()
            refreshing = false
            return RefreshStart.Completed(if (Parcel.trackingChanged(before, parcels)) RefreshResult.Updated else RefreshResult.Unchanged)
        }
        if (isGuest) {
            var trouble: Exception? = null
            try {
                device.refresh(all = true)
            } catch (error: Exception) {
                trouble = error
            }
            if (generation != session.generation) throw CancellationException("The signed-in account changed.")
            parcels = device.list()
            refreshing = false
            // The caller's own cancellation goes on, once the list is shown as it is.
            if (trouble is CancellationException) currentCoroutineContext().ensureActive()
            if (trouble != null) return RefreshStart.Completed(RefreshResult.Failed(localizer.errorMessage(trouble)))
            return RefreshStart.Completed(if (Parcel.trackingChanged(before, parcels)) RefreshResult.Updated else RefreshResult.Unchanged)
        }
        val jobIds = try {
            api.queueRefreshAll().also { session.checkGeneration(generation) }
        } catch (error: Exception) {
            if (generation == session.generation) refreshing = false
            throw error
        }
        refreshTask = scope.launch { finishRefresh(jobIds, before, generation) }
        return RefreshStart.Queued
    }

    private suspend fun finishRefresh(jobIds: List<UUID>, before: List<Parcel>, generation: UUID) {
        val result: RefreshResult = try {
            api.waitForJobs(jobIds)
            session.checkGeneration(generation)
            load(showSpinner = false)
            if (Parcel.trackingChanged(before, parcels)) RefreshResult.Updated else RefreshResult.Unchanged
        } catch (_: DeliveryApiError.RefreshTimeout) {
            // The checks keep running; their results arrive with a later load.
            RefreshResult.StillChecking
        } catch (error: Exception) {
            if (error is CancellationException) return
            RefreshResult.Failed(localizer.errorMessage(error))
        }
        if (!stillCurrent(generation)) return
        refreshing = false
        refreshTask = null
        refreshOutcome = RefreshOutcome(result)
    }

    suspend fun refresh(parcel: Parcel) {
        val generation = session.generation
        if (isDemo) {
            session.checkGeneration(generation)
            upsert(demo.refresh(parcel.id))
        } else if (isGuest) {
            device.refresh(only = parcel.id)
            session.checkGeneration(generation)
            parcels = device.list()
        } else {
            api.refresh(parcel.id)
            session.checkGeneration(generation)
            load(showSpinner = false)
        }
    }

    suspend fun exportAccount(): File {
        val data = if (isDemo || isGuest) {
            exportEncoder.encode(
                DemoExport.serializer(),
                DemoExport(exportedAt = DateParser.isoString(now()), mode = if (isDemo) "demo" else "device", packages = parcels),
            )
        } else {
            api.exportAccount()
        }
        val day = ParcelOrganizer.dayKey(now())
        return exportFiles.write("peek-export-$day.json", data)
    }

    /** The demo writes its sample parcels in the app's language. */
    fun demoLanguageChanged() {
        if (!isDemo) return
        parcels = demo.list()
    }

    suspend fun resetDemoData() {
        analytics("demo-reset", null)
        if (!isDemo) return
        demo.reset()
        demoShares.reset()
        shareNotes.forgetAll()
        undoParcel = null
        endAllDeliveryLiveActivities()
        parcels = demo.list()
    }

    /** Forgets every parcel followed on this phone without an account, here and on the service. */
    suspend fun forgetDeviceParcels() {
        if (!isGuest) return
        val generation = session.generation
        try {
            device.forgetAll()
            undoParcel = null
        } finally {
            if (generation == session.generation && isGuest) {
                mutationRevision += 1
                parcels = device.list()
            }
        }
    }

    suspend fun deleteAccount(confirmation: String) {
        val generation = session.generation
        if (isDemo) {
            resetDemoData()
            return
        }
        val appleAuthorizationCode = session.reauthenticateWithAppleBeforeDeletion()
        session.checkGeneration(generation)
        nativePushGeneration += 1
        stopDeliveryLiveActivityObservers()
        jobMonitoringTask?.cancel()
        pendingJobIds.clear()
        api.deleteAccount(confirmation, appleAuthorizationCode)
        session.checkGeneration(generation)
        endAllDeliveryLiveActivities()
        session.checkGeneration(generation)
        notifications.unregisterForRemoteNotifications()
        notifications.clearDeviceToken()
        defaults.set(true, forKey = notificationOptOutKey)
        defaults.set(false, forKey = nativePushRegisteredKey)
        notificationsEnabledOnDevice = false
        notificationPreferences = null
        notificationError = null
        session.user?.id?.let { cache.delete(it) }
        session.forceSignOut()
        parcels = emptyList()
    }

    suspend fun signOut() {
        // Save cleanup intent before discarding the account session.
        liveActivityRevocations.queue()
        liveActivityRevocations.retry(scope)
        val generation = session.generation
        nativePushGeneration += 1
        jobMonitoringTask?.cancel()
        pendingJobIds.clear()
        val token = notifications.currentDeviceToken
        if (token != null && !isDemo) tryQuietly { api.unregisterNativePushToken(token) }
        session.checkGeneration(generation)
        if (!isDemo) liveActivityRevocations.drain()
        stopDeliveryLiveActivityObservers()
        session.checkGeneration(generation)
        endAllDeliveryLiveActivities()
        session.checkGeneration(generation)
        notifications.unregisterForRemoteNotifications()
        notifications.clearDeviceToken()
        defaults.set(true, forKey = notificationOptOutKey)
        defaults.set(false, forKey = nativePushRegisteredKey)
        notificationsEnabledOnDevice = false
        notificationPreferences = null
        notificationError = null
        session.user?.id?.let { cache.delete(it) }
        session.signOut()
    }

    suspend fun refreshNotificationState() {
        val generation = session.generation
        val status = notifications.authorizationStatus()
        if (!stillCurrent(generation)) return
        // Every foreground checks this; publish only what changed.
        if (notificationStatus != status) notificationStatus = status
        val optedOut = defaults.bool(forKey = notificationOptOutKey)
        val enabled = NotificationDevicePolicy.isEnabled(
            isDemo = isDemo,
            status = notificationStatus,
            optedOut = optedOut,
            nativePushRegistered = defaults.bool(forKey = nativePushRegisteredKey),
            demoNotificationsEnabled = defaults.bool(forKey = demoNotificationsKey),
        )
        if (notificationsEnabledOnDevice != enabled) notificationsEnabledOnDevice = enabled
        if (!notificationStateLoaded) notificationStateLoaded = true
        if (NotificationDevicePolicy.shouldRegisterForRemoteNotifications(notificationStatus, optedOut)) {
            notifications.registerForRemoteNotifications()
        }
    }

    suspend fun enableNotifications(language: AppLanguage): Boolean {
        analytics("notifications-enable", DeliveryAnalytics.Outcome.Started)
        var succeeded = false
        try {
            val generation = session.generation
            notificationEnableInProgress = true
            try {
                val granted = notifications.requestAuthorization()
                session.checkGeneration(generation)
                refreshNotificationState()
                session.checkGeneration(generation)
                if (!granted) {
                    dismissNotificationInvitation()
                    throw DeliveryApiError.NotificationsDenied()
                }
                defaults.set(false, forKey = notificationOptOutKey)
                notifications.registerForRemoteNotifications()
                if (isDemo) {
                    defaults.set(true, forKey = demoNotificationsKey)
                    notificationsEnabledOnDevice = true
                    succeeded = true
                    return true
                }
                val token = waitForApnsToken() ?: throw DeliveryApiError.PushTokenUnavailable()
                session.checkGeneration(generation)
                val sent = api.registerNativePushToken(token, installationId, language, sendTest = true)
                session.checkGeneration(generation)
                defaults.set(true, forKey = nativePushRegisteredKey)
                notificationsEnabledOnDevice = true
                dismissNotificationInvitation()
                succeeded = true
                return sent
            } finally {
                // As the iPhone's `checkGeneration`: a cancelled call leaves it on too.
                if (generation == session.generation && currentCoroutineContext().isActive) notificationEnableInProgress = false
            }
        } finally {
            analytics("notifications-enable", if (succeeded) DeliveryAnalytics.Outcome.Success else DeliveryAnalytics.Outcome.Error)
        }
    }

    suspend fun disableNotifications() {
        analytics("notifications-disable", DeliveryAnalytics.Outcome.Started)
        dismissNotificationInvitation()
        nativePushGeneration += 1
        val token = notifications.currentDeviceToken
        if (token != null && !isDemo) api.unregisterNativePushToken(token)
        notifications.unregisterForRemoteNotifications()
        notifications.clearDeviceToken()
        defaults.set(true, forKey = notificationOptOutKey)
        defaults.set(false, forKey = nativePushRegisteredKey)
        defaults.set(false, forKey = demoNotificationsKey)
        notificationsEnabledOnDevice = false
        refreshNotificationState()
        analytics("notifications-disable", DeliveryAnalytics.Outcome.Success)
    }

    val shouldInviteNotifications: Boolean
        get() = !loading && errorMessage == null && !authenticationRequired &&
            NotificationInvitationPolicy.shouldPresent(
                isAuthenticated = session.isAuthenticated,
                isDemo = isDemo,
                hasParcels = parcels.isNotEmpty(),
                status = if (notificationStateLoaded) notificationStatus else null,
                enabled = notificationsEnabledOnDevice,
                optedOut = defaults.bool(forKey = notificationOptOutKey),
                dismissed = notificationInvitationDismissed,
            )

    fun dismissNotificationInvitation() {
        if (isDemo) return
        val userId = session.user?.id ?: return
        NotificationInvitationPreference.dismiss(userId, defaults)
        notificationInvitationDismissed = true
    }

    suspend fun loadNotificationPreferences() {
        // Alerts belong to an account.
        if (isGuest) return
        val saves = notificationPreferenceSaves
        try {
            val loaded = if (isDemo) demo.notificationPreferences else api.notificationPreferences()
            // A save was answered meanwhile: its answer is the newer one.
            if (saves != notificationPreferenceSaves) return
            notificationPreferences = loaded
            notificationError = null
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            notificationError = localizer.errorMessage(error)
        }
    }

    suspend fun saveNotificationPreferences(value: NotificationPreferences) {
        val saved = if (isDemo) demo.saveNotificationPreferences(value) else api.saveNotificationPreferences(value)
        notificationPreferenceSaves += 1
        notificationPreferences = saved
        notificationError = null
    }

    /** The account's delivery email, when the server can write to the address it signs in with. */
    val deliveryEmail: DeliveryEmail?
        get() = DeliveryEmail(isDemo = isDemo, preferences = notificationPreferences, address = session.user?.email)

    /**
     * Switches the account's delivery email at once and apart from the preset: the preferences go back as they were
     * last saved, with the new choice and this device's time zone, and the server's answer becomes the state.
     */
    suspend fun setEmailOnDelivery(enabled: Boolean) {
        // Nothing to switch in the demo, or while the server cannot email the account.
        if (deliveryEmail == null) return
        val value = notificationPreferences ?: return
        saveNotificationPreferences(value.copy(emailOnDelivery = enabled, timezone = ZoneId.systemDefault().id))
    }

    /** Reads the preferences again without a word when that fails: what is loaded stays. */
    private suspend fun refreshNotificationPreferences() {
        val saves = notificationPreferenceSaves
        val loaded = try {
            api.notificationPreferences()
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            return
        }
        if (saves != notificationPreferenceSaves || loaded == notificationPreferences) return
        notificationPreferences = loaded
    }

    suspend fun forwardNativePushToken(token: String, language: AppLanguage) {
        if (isDemo || !session.isAuthenticated || notificationEnableInProgress || defaults.bool(forKey = notificationOptOutKey)) return
        val generation = nativePushGeneration
        try {
            api.registerNativePushToken(token, installationId, language, sendTest = false)
            if (generation != nativePushGeneration || !session.isAuthenticated || defaults.bool(forKey = notificationOptOutKey)) {
                tryQuietly { api.unregisterNativePushToken(token) }
                return
            }
            defaults.set(true, forKey = nativePushRegisteredKey)
            notificationsEnabledOnDevice = session.isAuthenticated
            notificationError = null
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            notificationError = localizer.errorMessage(error)
        }
    }

    private suspend fun list(): List<Parcel> {
        if (isDemo) return demo.list()
        return api.listPackages()
    }

    private fun upsert(parcel: Parcel) {
        mutationRevision += 1
        val index = parcels.indexOfFirst { it.id == parcel.id }
        parcels = if (index >= 0) parcels.toMutableList().also { it[index] = parcel } else parcels + parcel
        persistCache()
    }

    private fun persistCache() {
        val userId = session.user?.id ?: return
        cache.save(parcels, userId)
    }

    private fun publishDeliverySurfaces() {
        publishDeliveryWidget()
        scheduleDeliveryLiveActivities()
    }

    /** The parcels on their way, the most pressing first, as the widget picks from them. */
    val nextDeliveries: List<DeliveryWidgetParcel>
        get() = nextDeliveries(parcels, localizer, now())

    private fun publishDeliveryWidget() {
        val snapshot = if (deliveryWidgetEnabled && (session.isAuthenticated || isGuest)) {
            DeliveryWidgetSnapshot(
                generatedAt = now(),
                languageCode = localizer.language.rawValue,
                parcels = DeliveryWidgetSelection.displayParcels(nextDeliveries),
            )
        } else {
            null
        }
        // The widget's cards come with its port, and with them the wait for their pictures.

        // Reloads spend the widget's system budget; skip them when nothing it shows changed.
        val state = DeliveryWidgetState(enabled = deliveryWidgetEnabled, languageCode = localizer.language.rawValue, parcels = snapshot?.parcels)
        if (state == publishedWidgetState) return
        publishedWidgetState = state
        deliveryWidgetStore.setLanguageCode(localizer.language.rawValue)
        deliveryWidgetStore.setEnabled(deliveryWidgetEnabled)
        if (snapshot != null) {
            deliveryWidgetStore.save(snapshot)
        } else {
            deliveryWidgetStore.clearSnapshot()
        }
        widgetCenter.reloadTimelines(DeliveryWidgetSharedStore.kind)
    }

    // Live Activities. Android has none to start, update or end: what is left is their setting, the message when the
    // system turns them off, and the withdrawal of this phone's registration while they are off.

    private fun scheduleDeliveryLiveActivities(forceEnd: Boolean = false) {
        deliveryActivityTask?.cancel()
        deliveryActivityTask = scope.launch {
            if (forceEnd) endAllDeliveryLiveActivities() else updateDeliveryLiveActivities()
        }
    }

    private suspend fun updateDeliveryLiveActivities() {
        if (!currentCoroutineContext().isActive) return
        // A Live Activity is kept up to date by pushes, which need an account.
        if (isGuest) return
        if (!deliveryLiveActivitiesEnabled) {
            endAllDeliveryLiveActivities()
            if (!currentCoroutineContext().isActive || deliveryLiveActivitiesEnabled) return
            unregisterDeliveryLiveActivityDevice()
            return
        }
        if (!liveActivities.areActivitiesEnabled) {
            deliveryLiveActivitySystemDisabled = true
            val message = localizer.text("liveActivity.systemDisabled")
            if (deliveryLiveActivityError != message) deliveryLiveActivityError = message
            return
        }
        if (deliveryLiveActivitySystemDisabled) {
            deliveryLiveActivitySystemDisabled = false
            deliveryLiveActivityError = null
        }
    }

    private suspend fun endAllDeliveryLiveActivities() {
        liveActivities.endAll()
    }

    // The iPhone observes its Live Activities and their push tokens here, and registers them with the service.
    private fun startDeliveryLiveActivityObservers() {
        stopDeliveryLiveActivityObservers()
    }

    private fun stopDeliveryLiveActivityObservers() {}

    private suspend fun unregisterDeliveryLiveActivityDevice() {
        if (!currentCoroutineContext().isActive || deliveryLiveActivitiesEnabled) return
        try {
            liveActivityRevocations.queue()
            liveActivityRevocations.drain()
            liveActivityRevocations.retry(scope)
            if (deliveryLiveActivityError != null) deliveryLiveActivityError = null
        } catch (error: Exception) {
            if (error is CancellationException) currentCoroutineContext().ensureActive()
            deliveryLiveActivityError = localizer.errorMessage(error)
        }
    }

    private fun beginPolling() {
        pollingTask?.cancel()
        pollingTask = scope.launch {
            while (true) {
                delay(30.seconds)
                if (appActive) load(showSpinner = false)
            }
        }
    }

    /** Brings the device's own parcels up to date, quietly: trouble leaves the list as it is. */
    private suspend fun loadDeviceParcels() {
        val generation = session.generation
        tryQuietly { device.refresh() }
        if (generation != session.generation || !isGuest || pendingMutations.isNotEmpty()) return
        val next = device.list()
        if (next != parcels) parcels = next else publishDeliverySurfaces()
    }

    /**
     * Asks again while the list is on screen: soon and a few times while a parcel waits for its carrier's first
     * answer, then at the pace of the signed-in list.
     */
    private fun beginDevicePolling() {
        pollingTask?.cancel()
        pollingTask = scope.launch {
            var round = 0
            while (true) {
                val soon = device.isWaiting && round < 6
                delay((if (soon) 2 * 1.5.pow(round) else 30.0).seconds)
                if (!isGuest) return@launch
                round = if (soon) round + 1 else 0
                if (appActive) loadDeviceParcels()
            }
        }
    }

    /**
     * The parcels followed on this phone before signing in join the account, under the names they had here. The
     * device lets go of each one the account now has.
     */
    private suspend fun claimDeviceParcels() {
        val claims = device.claims
        if (session.user == null || claims.isEmpty()) return
        val generation = session.generation
        for (start in claims.indices step 20) {
            val batch = claims.subList(start, minOf(start + 20, claims.size)).toList()
            val response = try {
                api.claimParcels(ClaimParcelsRequest(batch))
            } catch (error: Exception) {
                if (error is CancellationException) currentCoroutineContext().ensureActive()
                return
            }
            if (generation != session.generation) return
            for (result in response.results) {
                if (result.outcome != ClaimParcelResultOutcome.Kept) continue
                // What was archived here stays archived there.
                val id = result.packageId
                if (id != null && device.isArchived(result.id)) tryQuietly { api.archive(id) }
            }
            // A parcel the account has no room for stays on the device, and so does someone else's that it could not keep.
            device.release(
                response.results.filter { result ->
                    when (result.outcome) {
                        ClaimParcelResultOutcome.Kept, ClaimParcelResultOutcome.Already -> true
                        ClaimParcelResultOutcome.Quota -> false
                        ClaimParcelResultOutcome.Unavailable -> !device.isViewer(result.id)
                    }
                }.map { it.id }.toSet(),
            )
        }
    }

    private fun monitorJobs(jobIds: List<UUID>) {
        pendingJobIds += jobIds
        if (jobMonitoringTask != null || pendingJobIds.isEmpty()) return
        val generation = session.generation
        jobMonitoringTask = scope.launch {
            while (isActive && pendingJobIds.isNotEmpty()) {
                val current = pendingJobIds.toList()
                try {
                    api.waitForJobs(current)
                    pendingJobIds -= current.toSet()
                    load(showSpinner = false)
                } catch (error: Exception) {
                    if (!stillCurrent(generation)) return@launch
                    pendingJobIds -= current.toSet()
                    errorMessage = localizer.errorMessage(error)
                }
            }
            if (!stillCurrent(generation)) return@launch
            jobMonitoringTask = null
            if (pendingJobIds.isNotEmpty()) monitorJobs(emptyList())
        }
    }

    private suspend fun waitForApnsToken(): String? {
        notifications.currentDeviceToken?.let { return it }
        repeat(80) {
            delay(100.milliseconds)
            notifications.currentDeviceToken?.let { return it }
        }
        return null
    }

    /**
     * Swift's `(try? session.checkGeneration(generation)) != nil`: the identity is still the one the work began for.
     * The caller's own cancellation goes on.
     */
    private suspend fun stillCurrent(generation: UUID): Boolean {
        currentCoroutineContext().ensureActive()
        return session.generation == generation
    }

    companion object {
        /**
         * Live Activities are switched off: the store ends any still showing and withdraws this phone's registration,
         * so the server starts no more. The iPhone's Info.plist has them off with it.
         */
        const val offersLiveActivities = false

        private const val installationIdKey = "sdt.installationID.v1"

        // As JSONEncoder.deliveryTracker writes the iPhone's models.
        private val exportEncoder = JsonEncoder(sortedKeys = true, swiftKeys = true)

        fun nextDeliveries(parcels: List<Parcel>, localizer: Localizer, now: Instant = Instant.now()): List<DeliveryWidgetParcel> {
            val ordered = ParcelOrganizer.visible(parcels, query = "", status = ParcelStatusFilter.Active, carrier = null, sort = ParcelSort.Priority, now = now)
            return ordered.map { parcel ->
                DeliveryWidgetParcel(
                    id = parcel.id,
                    label = parcel.label.nonEmpty ?: localizer.text("common.parcel"),
                    carrier = CarrierCatalog.shared.info(parcel.displayedCarrier, localizer.language).displayName,
                    trackingNumber = CarrierCatalog.format(parcel.trackingNumber, parcel.carrier),
                    detail = localizer.parcelDeliveryEstimate(parcel, now) ?: localizer.text(parcel.displayStatus.key),
                    isOutForDelivery = parcel.currentStage == TrackingStage.OutForDelivery,
                )
            }
        }

        private fun installationIdentifier(defaults: UserDefaults): UUID {
            defaults.string(forKey = installationIdKey)?.let(::uuid)?.let { return it }
            val value = UUID.randomUUID()
            defaults.set(value.uuidString, forKey = installationIdKey)
            return value
        }
    }
}

/** What the widget displays; the snapshot's timestamp alone never needs a reload. */
private data class DeliveryWidgetState(val enabled: Boolean, val languageCode: String, val parcels: List<DeliveryWidgetParcel>?)

@Serializable
private class DemoExport(val exportedAt: String, val mode: String, val packages: List<Parcel>)

/**
 * Swift's `try?` (or an empty `catch`) on the caller's path. The caller's own cancellation goes on, as every catch on
 * its path lets it; any other CancellationException is the account changing under the call (`checkGeneration`),
 * which ends here as the Swift ends it.
 */
private suspend inline fun tryQuietly(block: () -> Unit) {
    try {
        block()
    } catch (_: CancellationException) {
        currentCoroutineContext().ensureActive()
    } catch (_: Exception) {
    }
}

/** Swift's `try?` on a platform store, which never stops the caller. */
private inline fun <T> quietly(block: () -> T): T? = try {
    block()
} catch (_: Exception) {
    null
}
